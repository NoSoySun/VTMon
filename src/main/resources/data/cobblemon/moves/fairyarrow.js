{
    num: -426992,
    accuracy: true,
    basePower: 25,
    category: "Special",
    name: "Fairy Arrow",
    pp: 25,
    priority: 1,
    multihit: [5, 10],

    flags: {
        protect: 1,
        mirror: 1,
        distance: 1,
    },

    critRatio: 4,
    target: "normal",
    type: "Grass",

    onModifyMove(move, source) {
        move.forceSTAB = source.hasType("Fairy");

        if (source.hasType("Fairy")) {
            move.willCrit = true;
        }
    },

    onModifySTAB(stab, source, target, move) {
        if (source.hasType("Grass") && source.hasType("Fairy")) {
            return stab * 1.5;
        }
    },

    onEffectiveness(typeMod, target, type, move) {
        if (move.forceSTAB) {
            return typeMod + this.dex.getEffectiveness("Fairy", type);
        }

        return typeMod;
    },

    shortDesc: "Always hits. High critical-hit ratio; Fairy users always crit. Grass and Fairy STAB stack.",

    desc: "Always hits and has a high critical-hit ratio. Fairy-type users always land critical hits, gain Fairy-type effectiveness, and receive STAB. Grass and Fairy STAB bonuses stack when the user has both types.",
}