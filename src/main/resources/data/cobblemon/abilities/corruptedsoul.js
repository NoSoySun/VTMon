{
  inherit: false,
    isNonstandard: null,
      name: "Corrupted Soul",
        shortDesc: "Poison moves get 1.95× power. Flinch & Intimidate immunity. Defiant effect.",

          onStart(pokemon) {
    if (this.suppressingAbility(pokemon)) return;
    this.add('-ability', pokemon, 'Corrupted Soul');
  },

  onAfterEachBoost(boost, target, source, effect) {
    if (!source || target.isAlly(source)) return;
    for (const stat in boost) {
      if (boost[stat] < 0) {
        this.boost({ atk: 2, spa: 2 }, target, target, null, false, true);
        break;
      }
    }
  },

  onTryAddVolatile(status, pokemon) {
    if (status.id === 'flinch') return null;
  },

  onTryBoost(boost, target, source, effect) {
    if (effect?.name === 'Intimidate' && boost.atk) {
      delete boost.atk;
      this.add('-fail', target, 'unboost', 'Attack', '[from] ability: Corrupted Soul', '[of] ' + target);
    }
  },

  onBasePowerPriority: 23,
    onBasePower(basePower, pokemon, target, move) {
    if (move.type === 'Poison' || move.type === 'Ghost') {
      return this.chainModify([39936, 20480]);
    }
  },

  onResidualOrder: 29,
  onResidual(pokemon) {
    if (pokemon.baseSpecies.baseSpecies !== 'Schlanghoul' || pokemon.transformed || !pokemon.hp) return;
    if (pokemon.species.id === 'schlanghoulphantom' || pokemon.species.id === 'schlanghoulmegap' || pokemon.species.isMega || pokemon.hp > (pokemon.maxhp * 0.75)) return;

    this.add('-activate', pokemon, 'ability: Corrupted Soul');
    pokemon.formeChange('Schlanghoul-Phantom', this.effect, true);
    pokemon.baseMaxhp = Math.floor(Math.floor(
      2 * pokemon.species.baseStats['hp'] + pokemon.set.ivs['hp'] + Math.floor(pokemon.set.evs['hp'] / 4) + 100
    ) * pokemon.level / 100 + 10);
    const newMaxHP = pokemon.volatiles['dynamax'] ? (2 * pokemon.baseMaxhp) : pokemon.baseMaxhp;
    pokemon.hp = newMaxHP - (pokemon.maxhp - pokemon.hp);
    pokemon.maxhp = newMaxHP;
    this.add('-heal', pokemon, pokemon.getHealth, '[silent]');

    if (pokemon.getItem().id === 'schlanghite') {
      if (pokemon.side.hasMegaEvolved) return;
      pokemon.canMegaEvo = 'Schlanghoul-MegaP';
    }

    const foe = pokemon.side.foe.active[0];
    if (!foe || foe.fainted) return;

    const drainGoal = Math.floor(pokemon.maxhp * 0.4);
    let actualDrain = foe.hp >= drainGoal
      ? drainGoal
      : Math.floor(foe.hp * 0.5);

    if (actualDrain > 0) {
      this.damage(actualDrain, foe, pokemon, this.effect);
      this.heal(actualDrain, pokemon, pokemon, this.effect);
    }
  },

  rating: 5,
    num: -1001,
}
