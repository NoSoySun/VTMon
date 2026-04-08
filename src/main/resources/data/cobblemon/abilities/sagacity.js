{
  inherit: false,
  isNonstandard: null,
  name: "Sagacity",
  desc: "When this Pokémon enters battle, it lowers the Special Attack of all adjacent opposing Pokémon by 1 stage.",
  shortDesc: "Lowers foes' Sp. Atk on switch-in.",

  onStart(pokemon) {
    if (this.suppressingAbility(pokemon)) return;
    this.add('-ability', pokemon, 'Sagacity');

    for (const target of pokemon.adjacentFoes()) {
      if (target.volatiles['substitute']) {
        this.add('-immune', target);
      } else {
        this.boost({spa: -1}, target, pokemon, null, true);
      }
    }
  },

  rating: 3.5,
  num: -301,
}
