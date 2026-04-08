{
  num: -189,
  accuracy: true,
  basePower: 120,
	basePowerCallback(pokemon, target, move) {
		if (target.status === 'par') {
			return move.basePower * 1.5;
		}
		return move.basePower;
	},
  category: "Special",
  name: "Voltivore",
  pp: 16,
  priority: 1,
  drain: [3, 4],
  flags: { mirror: 1, metronome: 1 },
  critRatio: 2,
  secondary: { chance: 100, status: "par" },
	self: {
		boosts: {
			spa: 2,
			spe: 2
		}
	},

  onPrepareHit(target, source, move) {
    if (source.baseSpecies.baseSpecies === "Aaiper" && !source.transformed) {
      const newForme = source.species.id === "aaiperovercharge" ? "Aaiper" : "Aaiper-Overcharge";
      source.formeChange(newForme, this.effect, true, "[msg]");
    }
  },

  target: "normal",
  type: "Electric",
  zMove: { basePower: 240 },
  maxMove: { basePower: 200 },
  contestType: "Cute"
}
