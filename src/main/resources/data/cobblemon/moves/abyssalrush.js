{
	num: -4657,
	accuracy: true,
	basePower: 130,
	category: "Physical",
	name: "Abyssal Rush",
	pp: 10,
	priority: 1,
	willCrit: true,
	flags: {
		contact: 1,
	},
	target: "normal",
	type: "Water",
	contestType: "Beautiful",
	self: {
		boosts: {
				atk: 2,
				def: -1,
				spa: 2,
				spd: -1,
				spe: 2
			}

	},

	ignoreAbility: true,

	ignoreDefensive: true,
	

	onTryHit(target, source, move) {
		if (target.volatiles['protect'] || target.volatiles['detect']) {
			this.add('-activate', target, 'move: Abyssal Rush');
			delete target.volatiles['protect'];
			delete target.volatiles['detect'];
		}
	},

	recoil: [1, 8],

	secondary: {
		chance: 50,
		volatileStatus: 'flinch',
	},

	breaksProtect: true,
	breaksSubstitute: true,
	zMove: { basePower: 240 },
	maxMove: { basePower: 250 },
}
