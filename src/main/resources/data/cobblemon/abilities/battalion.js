{
    onStart(pokemon) {
        if (pokemon.baseSpecies.baseSpecies !== 'Tokarmy' || pokemon.level < 20 || pokemon.transformed) return;

        if (pokemon.hp > pokemon.maxhp / 4) {
            if (pokemon.species.id === 'tokarmy') {
                pokemon.formeChange('Tokarmy-Battalion');
            }
        } else {
            if (pokemon.species.id === 'tokarmy-battalion' || 
                pokemon.species.id === 'tokarmybattalion' || 
                pokemon.species.id === 'tokarmy_battalion') {
                pokemon.formeChange('Tokarmy');
            }
        }
    },

    onResidualOrder: 29,
    onResidual(pokemon) {
        if (
            pokemon.baseSpecies.baseSpecies !== 'Tokarmy' || 
            pokemon.level < 20 ||
            pokemon.transformed || 
            !pokemon.hp
        ) return;

        if (pokemon.hp > pokemon.maxhp / 4) {
            if (pokemon.species.id === 'tokarmy') {
                pokemon.formeChange('Tokarmy-Battalion');
            }
        } else {
            if (pokemon.species.id === 'tokarmy-battalion' || 
                pokemon.species.id === 'tokarmybattalion' || 
                pokemon.species.id === 'tokarmy_battalion') {
                pokemon.formeChange('Tokarmy');
            }
        }
    },
    
    flags: { failroleplay: 1, noreceiver: 1, noentrain: 1, notrace: 1, failskillswap: 1, cantsuppress: 1 },
    name: "Battalion",
    rating: 3,
    num: -4208
}