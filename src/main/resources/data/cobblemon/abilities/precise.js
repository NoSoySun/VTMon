{
  inherit: false,
  isNonstandard: null,
  name: "Precise",
  shortDesc: "Multi-hit moves strike max hits. +3 crit stage.",

  onStart(pokemon) {
    if (this.suppressingAbility(pokemon)) return;
    this.add('-ability', pokemon, 'Precise');
  },

  onModifyMove(move) {
    if (Array.isArray(move.multihit)) {
      move.multihit = move.multihit[1];
      this.debug(`${move.name} multihit forced to ${move.multihit} by Precise`);
    }

    move.willCrit = true;
  },


  rating: 4,
  num: -1011,
}
