{
    onStart(pokemon) {
        const allies = pokemon.side.pokemon;
        for (const ally of allies) {
            if (ally !== pokemon && !ally.fainted) {
                this.boost({ spa: 1, spd: 1 }, ally, pokemon);
            }
        }
    },

    flags: { failroleplay: 1, noreceiver: 1, noentrain: 1, failskillswap: 1 },
    name: "Divine Inspiration",
    rating: 4,
    num: -20002,
}