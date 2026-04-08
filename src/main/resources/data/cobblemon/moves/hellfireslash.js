{
	num: -189,
	accuracy: true,
	basePower: 80,
	basePowerCallback(pokemon, target, move) {
	  return 50 + (15 * move.hit);
	},
	category: "Physical",
	name: "Hellfire Slash",
	pp: 10,
	priority: 1,
	drain: [1, 2],
	flags: {
	  mirror: 1,
	  metronome: 1,
	  contact: 1,
	  slicing: 1
	},
	critRatio: 2,
	multihit: [2, 3],
	multiaccuracy: true,
	secondary: {
	  chance: 75,
	  status: "brn"
	},
	onAfterMove(pokemon, target, move) {
	  if (move.hit > 1) {
		this.boost({atk: 2, spa: 2, spe: 2}, pokemon);
	  }
	},
	target: "normal",
	type: "Fire",
	zMove: { basePower: 200 },
	maxMove: { basePower: 210 },
	contestType: "Cute"
  }
  