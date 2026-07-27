{
	num: -1005,
	accuracy: true,
	basePower: 100,
	category: "Special",
	name: "Balance Pulse",
	pp: 8,
	priority: 0,
	flags: {
		protect: 1,
		mirror: 1,
		pulse: 1,
	},
	secondary: {
			chance: 100,
			onHit(target, source, move) {
				const boosts = {};
				const stats = ['atk', 'def', 'spa', 'spd', 'spe', 'evasion', 'accuracy'];
				for (const stat of stats) {
					const reduction = this.random(1, 3);
					if (reduction > 0) {
						boosts[stat] = -reduction;
					}
				}
				this.boost(boosts, target);
			},
		},
	target: "normal",
    willCrit: true,
	type: "Fairy",
	contestType: "Beautiful",
}