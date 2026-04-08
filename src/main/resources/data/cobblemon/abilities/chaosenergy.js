{
  inherit: false,
  isNonstandard: null,
  name: "Chaos Energy",
  shortDesc: "Boosts Atk, SpA and Spe on entry and every turn. Electric moves deal 1.5x damage.",
  desc: "Upon entering the battle and at the end of every turn, this Pokémon gains +1 Attack, Special Attack, and Speed. Additionally, its Electric-type moves deal 50% more damage.",

  onStart(pokemon) {
    if (this.suppressingAbility(pokemon)) return;
    this.add('-ability', pokemon, 'Chaos Energy');

    this.boost(
      { atk: 1, spa: 1, spe: 1 },
      pokemon,
      pokemon
    );
  },

  onResidualOrder: 5,
  onResidual(pokemon) {
    if (pokemon.fainted) return;

    this.boost(
      { atk: 1, spa: 1, spe: 1 },
      pokemon,
      pokemon
    );
  },

  onBasePowerPriority: 6,
  onBasePower(basePower, attacker, defender, move) {
    if (attacker.hasAbility('Chaos Energy') && move.type === 'Electric') {
      this.debug('Chaos Energy Electric boost');
      return this.chainModify([3, 2]);
    }
  },

  rating: 5,
  num: -300,
}
