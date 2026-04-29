{
    name: "Clock",
    spritenum: -580,

    fling: {
        basePower: 30
    },
    
    onStart(pokemon) {
        this.add('-message', `${pokemon.name} está usando ${pokemon.getItem().name}`);
    },

    onResidual(pokemon) {
        if (this.turn % 2 === 0) {
            if (this.field.getPseudoWeather('trickroom')) {
                this.boost({spe: -1}, pokemon);
            } else {
                this.boost({spe: 1}, pokemon);
            }
        } else {
            this.heal(pokemon.baseMaxhp / 12, pokemon);
        }

        if (!this.field.getPseudoWeather('trickroom') && pokemon.boosts.spe < 0) {
            this.boost({spe: -pokemon.boosts.spe}, pokemon);
        }
    },

    num: -1022,
    gen: 9,
    desc: "Alternates effects each turn. In Trick Room, lowers Speed, which is reversed when Trick Room ends."
}