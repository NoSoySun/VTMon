{
	num: -4656,
	accuracy: true,
	basePower: 240,
	category: "Special",
	isNonstandard: "Gigantamax",
	name: "G-Max Flood",
	pp: 5,
	priority: 0,
	flags: {},
	isMax: "Kurperor",
	ignoreAbility: true,
	ignoreDefensive: true,
	secondary: null,
	target: "allAdjacentFoes",
	type: "Water",
	contestType: "Beautiful",
	self: {
		boosts: {
			def: 2,
			evasion: 2,
			spd: 2
		}
	},

	onTry(source, target, move) {
		this.field.setWeather('raindance');
		source.addVolatile('aquaring');
	},

	onHit(target, source, move) {
		if (!target.volatiles['whirlpool']) {
			target.addVolatile('whirlpool');
			this.add('-activate', target, 'move: Whirlpool');
		}
	},
}
