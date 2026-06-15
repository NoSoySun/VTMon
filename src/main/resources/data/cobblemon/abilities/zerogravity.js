{
  isNonstandard: null,
  shortDesc: "Sets Gravity for five turns when this Pokemon enters battle.",

  onStart(pokemon) {
    if (this.field.addPseudoWeather('gravity', pokemon, this.effect)) {
      this.add('-ability', pokemon, 'Zero Gravity');
    }
  },

  flags: {
    failroleplay: 1,
    noreceiver: 1,
    noentrain: 1,
    failskillswap: 1,
  },

  name: "Zero Gravity",
  rating: 3,
  num: -19458,
}
