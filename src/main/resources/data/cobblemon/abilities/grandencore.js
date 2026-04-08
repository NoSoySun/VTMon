{
  inherit: false,
  isNonstandard: null,
  name: "Grand Encore",
  shortDesc: "40% chance: User's damaging moves hit twice (2nd hit at 50%).",

  onPrepareHit(source, target, move) {
    if (
      move.category !== 'Status' &&
      move.multihit == null &&
      !move.flags['noparentalbond'] &&
      !move.spreadHit
    ) {
      if (this.randomChance(40, 100)) {
        move.multihit = 2;
        move.multihitType = 'grandencore';
      }
    }
  },

  onBasePower(basePower, attacker, defender, move) {
    if (move.multihitType === 'grandencore' && move.hit > 1) {
      this.debug('Grand Encore second hit weakening');
      return this.chainModify(0.5);
    }
  },

  rating: 4.5,
  num: -1014,
}
