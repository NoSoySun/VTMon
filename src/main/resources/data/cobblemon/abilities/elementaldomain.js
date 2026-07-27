{
    name: "Elemental Domain",
    shortDesc: "This Pokémon gains STAB on moves of every type.",
    rating: 5,
    num: -1501,

    onModifyMove(move) {
        if (move.category !== "Status") {
            move.forceSTAB = true;
        }
    },

    onStart(pokemon) {
        this.add(`-activate`, pokemon, 'ability: Elemental Domain');
    },

    flags: { failroleplay: 1, noreceiver: 1, noentrain: 1, notrace: 1, failskillswap: 1 },
}