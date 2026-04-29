{
    onModifyMove(move, pokemon) {
        if (move.flags['bite']) {
            move.basePower = Math.floor(move.basePower * 1.5);
            move.drain = [3, 5];
        }
    },

    onTryHit(target, source, move) {
        if (target === source || move.category === 'Status' || move.type === '???' || move.id === 'struggle') return;
        if (move.id === 'skydrop' && !source.volatiles['skydrop']) return;

        this.debug('Bloodsucker immunity: ' + move.id);

        const effectiveness = target.runEffectiveness(move);

        if (effectiveness >= 1) {
            if (move.smartTarget) {
                move.smartTarget = false;
            } else {
                this.add('-immune', target, '[from] ability: Bloodsucker');
            }
            return null;
        }
    },

    flags: { failroleplay: 1, noreceiver: 1, noentrain: 1, failskillswap: 1, breakable: 1 },
    name: "Bloodsucker",
    rating: 4,
    num: -19456,
}