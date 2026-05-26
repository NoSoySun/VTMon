{
  inherit: false,
  isNonstandard: null,
  name: "Great Encore",
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
        move.multihitType = 'greatencore';
      }
    }
  },

  onBasePower(basePower, attacker, defender, move) {
    if (move.multihitType === 'greatencore' && move.hit > 1) {
      this.debug('Great Encore second hit weakening');
      return this.chainModify(0.5);
    }
  },

  rating: 4.5,
  num: -1014,
}
