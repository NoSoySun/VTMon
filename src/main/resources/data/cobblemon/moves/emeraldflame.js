{
    num: -1001,
    accuracy: true,
    basePower: 100,
    category: "Special",
    name: "Emerald Flame",
    type: "Fire",
    pp: 16,
    priority: 0,
    flags: {
        protect: 1,
        mirror: 1,
    },

    willCrit: true,

    onEffectiveness(typeMod, target, type, move) {
        if (typeMod < 1) return 1;
    },

    ignoreImmunity: {
        Fire: true,
    },

    secondary: {
        chance: 60,
        status: 'brn',
    },

    target: "normal",

    desc: "Always lands a critical hit. This move is always super effective regardless of the target's type. Has a 60% chance to burn the target.",
    shortDesc: "Always crits, is always super effective, 60% burn chance.",
}