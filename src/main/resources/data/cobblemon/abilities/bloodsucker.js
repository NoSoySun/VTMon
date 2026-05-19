{
    onModifyMove(move, pokemon) {
        if (move.flags['bite']) {
            move.basePower = Math.ceil(move.basePower * 1.5);
            move.drain = [3, 4];
        }
    },

    onTryHit(target, source, move) {
        if (target === source) return;
        if (move.category === 'Status') return;

        if (move.type === 'Poison' || move.type === 'Dark') {
            this.add('-immune', target, '[from] ability: Bloodsucker');
            return null;
        }
    },

    onEffectiveness(typeMod, target, type, move) {
        
        if (move.type === 'Poison' || move.type === 'Dark') {
            return;
        }

        if (typeMod === 1 || typeMod >= 2) {
            return typeMod * -1;
        }
    },

    flags: {
        failroleplay: 1,
        noreceiver: 1,
        noentrain: 1,
        failskillswap: 1,
        breakable: 1,
    },

    name: "Bloodsucker",
    rating: 4,
    num: -19456,
}