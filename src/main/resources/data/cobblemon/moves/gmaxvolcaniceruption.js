{
	num: -3000,
	accuracy: true,
	basePower: 180,
	category: "Physical",
	isNonstandard: "Gigantamax",
	name: "G-Max Volcanic Eruption",
	pp: 5,
	priority: 0,
	flags: {},
	isMax: "Lylaze",
	ignoreAbility: true,
	ignoreDefensive: true,
	secondary: null,
	target: "allAdjacentFoes",
	type: "Fire",
	contestType: "Cool",

	self: {
		boosts: {
			atk: 2,
			evasion: 2,
			spa: 2
		}
	},

	onTry(target, source, move){
		this.field.setWeather('sunnyday');

	},

	onHit(target, source, move) {

		if (target && !target.volatiles['partiallytrapped']) {
			target.addVolatile('partiallytrapped');
			this.add('-activate', target, 'move: Fire Spin');
		}

		if (target) {
			target.trySetStatus('brn', source, move);
		}

		if (target && target.side) {
			target.side.addSideCondition('spikes');
			target.side.addSideCondition('stealthrock');
		}
	},
}