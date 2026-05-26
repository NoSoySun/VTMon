{
  inherit: false,
  isNonstandard: null,
  name: "Divine Voice",
  desc: "Al entrar en batalla, sube Ataque Especial y Defensa Especial de los aliados. Los movimientos de tipo Hada o Sonido del usuario curan al equipo un 10% de sus PS máximos tras usarse y tienen 50% más de potencia. Además, los movimientos dañinos del usuario tienen un 40% de chance de golpear dos veces (el segundo golpe con 50% de potencia).",
  shortDesc: "Aliados +1 SpA/SpD al entrar; Fairy/Sound +50% y curan equipo 10%; 40% multi-hit.",

  onStart(pokemon) {
    if (this.suppressingAbility(pokemon)) return;
    this.add('-ability', pokemon, 'Divine Voice');

    const allies = pokemon.side.pokemon;
    for (const ally of allies) {
      if (ally !== pokemon && !ally.fainted) {
        this.boost({ spa: 1, spd: 1 }, ally, pokemon, null, true);
      }
    }
  },

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
    let modifier = 1;

    const isSound = move.flags?.sound;
    const isFairy = move.type === 'Fairy';

    if (isSound || isFairy) {
      this.debug('Divine Voice boost for Fairy/Sound move');
      modifier *= 1.5;
    }

    if (move.multihitType === 'greatencore' && move.hit > 1) {
      this.debug('Great Encore second hit weakening');
      modifier *= 0.5;
    }

    return this.chainModify(modifier);
  },

  onAfterMove(source, target, move) {
    if (!move) return;
    const isSound = move.flags?.sound;
    const isFairy = move.type === 'Fairy';

    if (isSound || isFairy) {
      for (const ally of source.side.active) {
        if (ally && !ally.fainted) {
          this.heal(Math.floor(ally.maxhp / 8), ally, source);
        }
      }
    }
  },

  rating: 4.5,
  num: -256,
}