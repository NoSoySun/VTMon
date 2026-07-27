{
  inherit: false,
  isNonstandard: null,
  name: "Divine Flame",
  shortDesc: "Normal moves become Fire and gain 1.2x power. Also grants Flash Fire, Flame Body, and Adaptability.",

  onModifyTypePriority: -1,
  onModifyType(move, pokemon) {
    const noModifyType = [
      "judgment",
      "multiattack",
      "naturalgift",
      "revelationdance",
      "technoblast",
      "terrainpulse",
      "weatherball",
    ];

    if (
      move.type === "Normal" &&
      !noModifyType.includes(move.id) &&
      !(move.isZ && move.category !== "Status") &&
      !(move.name === "Tera Blast" && pokemon.terastallized)
    ) {
      move.type = "Fire";
      move.typeChangerBoosted = this.effect;
    }
  },

  onBasePowerPriority: 23,
  onBasePower(basePower, pokemon, target, move) {
    if (move.typeChangerBoosted === this.effect) {
      return this.chainModify([4915, 4096]);
    }
  },

  onModifySTAB(stab, source, target, move) {
    if (move.forceSTAB || source.hasType(move.type)) {
      if (stab === 2) {
        return 2.25;
      }
      return 2;
    }
  },

  onTryHit(target, source, move) {
    if (target !== source && move.type === "Fire") {
      move.accuracy = true;
      if (!target.addVolatile("divineflame")) {
        this.add("-immune", target, "[from] ability: Divine Flame");
      }
      return null;
    }
  },

  onEnd(pokemon) {
    pokemon.removeVolatile("divineflame");
  },

  condition: {
    noCopy: true,

    onStart(target) {
      this.add("-start", target, "ability: Divine Flame");
    },

    onModifyAtkPriority: 5,
    onModifyAtk(atk, attacker, defender, move) {
      if (move.type === "Fire" && attacker.hasAbility("divineflame")) {
        return this.chainModify(1.5);
      }
    },

    onModifySpAPriority: 5,
    onModifySpA(spa, attacker, defender, move) {
      if (move.type === "Fire" && attacker.hasAbility("divineflame")) {
        return this.chainModify(1.5);
      }
    },

    onEnd(target) {
      this.add("-end", target, "ability: Divine Flame", "[silent]");
    },
  },

  onDamagingHit(damage, target, source, move) {
    if (this.checkMoveMakesContact(move, source, target) && this.randomChance(4, 10)) {
      source.trySetStatus("brn", target);
    }
  },

  flags: {},
  rating: 5,
  num: -1500,
}
