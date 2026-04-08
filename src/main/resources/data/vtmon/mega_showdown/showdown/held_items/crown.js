{
    name: "Omni Boost Orb",
        spritenum: -577,
    fling: {
    basePower: 90
},
    onModifyAtk(atk, pokemon) {
    return this.chainModify(1.5);
},
    onModifySpA(spa, pokemon) {
    return this.chainModify(1.5);
},
    onModifyDef(def, pokemon) {
    return this.chainModify(1.5);
},
    onModifySpD(spd, pokemon) {
    return this.chainModify(1.5);
},
    onModifySpe(spe, pokemon) {
    return this.chainModify(1.25);
},
    itemUser: ["Tokomand", "Tokarmy"],
        num: -1001,
    gen: 9,
    desc: "Boost all stats by 50%."
}