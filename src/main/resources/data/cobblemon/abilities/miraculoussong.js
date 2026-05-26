{
    onAfterMove(source, target, move) {
        if (move.type === 'Fairy' || move.flags['sound']) {
            const allies = source.side.pokemon;
            for (const ally of allies) {
                if (ally.hp && !ally.fainted) {
                    this.heal(ally.maxhp / 10, ally, source);
                }
            }
        }
    },

    flags: { failroleplay: 1, noreceiver: 1, noentrain: 1, failskillswap: 1 },
    name: "Miraculous Song",
    rating: 4,
    num: -20001,
}