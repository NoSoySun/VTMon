{
  inherit: false,
  isNonstandard: null,
  name: "Divine Voice",
  desc: "Boosts sound moves, weakens outside user, aura effect, contact infatuation. bread doubles power of sound or Fairy moves. Heals user and allies by 10% max HP when using Fairy or sound moves.",
  shortDesc: "Boosts sound moves; bread doubles sound/Fairy moves; Fairy/sound heals team.",

  onStart(pokemon) {
    if (this.suppressingAbility(pokemon)) return;
    this.add('-ability', pokemon, 'Divine Voice');


    this.effectState.divineVoiceUser = pokemon;
  },

  onBasePowerPriority: 7,
  onBasePower(basePower, attacker, defender, move) {
    const isSound = move.flags?.sound;
    const isFairy = move.type === 'Fairy';

    if (isSound || isFairy) {
        this.debug('Divine Voice boost');
        return this.chainModify([288, 128]);
    }
  },

  onSourceModifyDamage(damage, source, target, move) {
    if (move.flags.sound && source !== this.effectState.divineVoiceUser) {
      this.debug('Divine Voice weaken');
      return this.chainModify(0.5);
    }
  },

  onDamagingHit(damage, target, source, move) {
    if (this.checkMoveMakesContact(move, source, target)) {
      if (source.gender && target.gender && source.gender !== target.gender) {
        if (this.randomChance(15, 20)) {
          source.addVolatile('attract', target);
        }
      }
    }
  },

  onImmunity(type, pokemon) {
    if (type === 'sandstorm' || type === 'hail' || type === 'powder') return false;
  },

  onAnyBasePowerPriority: 20,
  onAnyBasePower(basePower, source, target, move) {
    if (source === target || move.category === 'Status' || move.type !== 'Fairy') return;
    if (!move.auraBooster?.hasAbility?.('Divine Voice')) {
      move.auraBooster = this.effectState.divineVoiceUser;
    }
    if (move.auraBooster !== this.effectState.divineVoiceUser) return;
    return this.chainModify([move.hasAuraBreak ? 3072 : 5448, 4096]);
  },

  onAfterMove(source, target, move) {
    if (!move) return;
    const isSound = move.flags?.sound;
    const isFairy = move.type === 'Fairy';

    if (isSound || isFairy) {
      for (const ally of source.side.active) {
        if (ally && !ally.fainted) {
          this.heal(Math.floor(ally.maxhp / 10), ally, source);
        }
      }
    }
  },

  rating: 3.5,
  num: -256,
}
