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

    onModifySTAB(stab, source, target, move) {
        if (source.hasType("Fairy")) {
            move.willCrit = true;
            return stab * 1.5;
        }
    },

    shortDesc: "Always hits. High critical-hit ratio. Gets STAB from Grass- and Fairy-types.",
    desc: "Always hits and has a high critical-hit ratio. This move receives STAB from Grass- and Fairy-type Pokémon.",
}