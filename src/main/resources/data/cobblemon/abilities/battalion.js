{
    getSpeciesId(pokemon) {
      return pokemon.species.id;
    },
    isBattalionForm(pokemon) {
      const speciesId = this.getSpeciesId(pokemon);
      return speciesId === "tokarmybattalion" || speciesId === "tokarmy_battalion" || speciesId === "tokarmy-battalion";
    },

    onStart(pokemon) {
      if (pokemon.baseSpecies.baseSpecies !== "Tokarmy" || pokemon.level < 20 || pokemon.transformed)
        return;
      const speciesId = this.getSpeciesId(pokemon);
      if (pokemon.hp > pokemon.maxhp / 4) {
        if (speciesId === "tokarmy") {
          pokemon.formeChange("Tokarmy-Battalion");
        }
      } else {
        if (this.isBattalionForm(pokemon)) {
          pokemon.formeChange("Tokarmy");
        }
      }
    },
    
    flags: { failroleplay: 1, noreceiver: 1, noentrain: 1, notrace: 1, failskillswap: 1, cantsuppress: 1 },
    name: "Battalion",
    rating: 3,
    num: -4208
  }