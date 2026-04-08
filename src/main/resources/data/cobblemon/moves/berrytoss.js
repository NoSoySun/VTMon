{
    num: -190,
    accuracy: true,
    basePower: 0,
    basePowerCallback(pokemon, target, move) {
    const hpRatio = target.hp / target.maxhp;

    let base = 95 * (1 + (1 - hpRatio));

    if (move.hit === move.multihit) {
        base *= 1.5;
    }

    return Math.ceil(base);
},
    category: "Physical",
        name: "Berry Toss",
            pp: 10,
                priority: 0,
                    flags: {
        mirror: 1,
            metronome: 1,
                contact: 1
    },
    critRatio: 1,
        multihit: 3,
            multiaccuracy: true,
                secondary: {
        chance: 20,
            onHit(target) {
            if (this.randomChance(3, 5)) {
                this.boost({ def: -1 }, target);
            } else {
                this.boost({ spd: -1 }, target);
            }
        }
    },
    target: "allAdjacentFoes",
        type: "Grass",
            zMove: { basePower: 180 },
    maxMove: { basePower: 140 },
    contestType: "Cool"
}
