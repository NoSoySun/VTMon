{
    num: -1000,
    accuracy: true,
    basePower: 0,
    category: "Status",
    name: "Comando",
    pp: 10,
    priority: 0,
    flags: {},
    target: "self",
    type: "Dark",
    onHit(target, source, move) {
        const leadershipAbilities = ['supremeoverlord', 'commanding', 'friendguard'];
        const isLeader = leadershipAbilities.some(ability => source.hasAbility(ability));

        if (this.turn === 1) {
            const boostAmount = isLeader ? 2 : 1;
            for (const ally of source.side.active) {
                if (ally && !ally.fainted) {
                    this.boost({atk: boostAmount, spa: boostAmount}, ally, source);
                }
            }

            let boost = 0;
            if (this.gameType !== 'singles') {
                for (const ally of source.side.active) {
                    if (ally && !ally.fainted) boost++;
                }
            } else {
                for (const mon of source.side.pokemon) {
                    if (!mon.fainted) boost++;
                }
            }
            if (boost > 0) {
                const evasionAmount = isLeader ? boost + 1 : boost;
                this.boost({evasion: evasionAmount}, source, source);
            }
        } else {
            for (const foe of source.side.foe.active) {
                if (!foe || foe.fainted) continue;
                const lastMove = foe.lastMove;
                if (!lastMove) continue;

                foe.addVolatile('disable', source, move);
            }
        }
    },
    secondary: null,
    contestType: "Clever",
}