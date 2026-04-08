{
  inherit: false,
  isNonstandard: null,
  name: "Divine Judgement",
  shortDesc: "Deals 2× damage to Poison, Ghost, or Dark-type Pokémon.",

  onStart(pokemon) {
    if (this.suppressingAbility(pokemon)) return;
    this.add('-ability', pokemon, "Heaven's Judgment");
  },

  onModifyDamage(damage, source, target, move) {
    if (
      target.hasType('Poison') ||
      target.hasType('Ghost') ||
      target.hasType('Dark')
    ) {
      this.debug("Divine Judgement: Damage x2 against Poison, Ghost, or Dark");
      return this.chainModify(2);
    }
  },

  rating: 4,
  num: -1013,
}
