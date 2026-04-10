{
    name: "Clock",
        spritenum: -580,

    fling: {
    basePower: 30
},

    onResidual(pokemon) {

    if (this.turn % 2 === 0) {

        if (this.field.isTrickRoom) {
            this.boost({spe: -1}, pokemon);
        } else {
            this.boost({spe: 1}, pokemon);
        }

    } else {
        this.heal(pokemon.baseMaxhp / 16, pokemon);
    }
},

    onFieldEnd(field, effect) {

    if (effect.id === 'trickroom') {

        for (const pokemon of this.getAllActive()) {

            // Si quedó con Speed negativa, la invertimos
            if (pokemon.boosts.spe < 0) {
                this.boost({spe: -pokemon.boosts.spe}, pokemon);
            }
        }
    }
},

    num: -1022,
        gen: 9,
    desc: "Alternates effects each turn. In Trick Room, lowers Speed, which is reversed when Trick Room ends."
}