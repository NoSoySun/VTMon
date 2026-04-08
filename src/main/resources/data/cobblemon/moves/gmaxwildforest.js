{
	num: 2000,
	accuracy: true,
	basePower: 240,
	category: "Physical",
	isNonstandard: "Gigantamax",
	name: "G-Max Wildforest",
	pp: 5,
	priority: 0,
	flags: {},
	isMax: "Zarinanjo",
	ignoreAbility: true,
	ignoreDefensive: true,
	drain: [2, 3],
	secondary: null,
	target: "allAdjacentFoes",
	type: "Grass",
	contestType: "Beautiful",
	self: {
		boosts: {
			atk: 2,
			evasion: 2,
			spe: 2
		}
	},

	onTry(source, target, move) {
		this.field.setTerrain('grassyterrain');
	},

	onHit(target, source, move) {
		if (target && !target.volatiles['leechseed']) {
			target.addVolatile('leechseed', source);
			this.add('-start', target, 'move: Leech Seed', '[from] move: G-Max Wildforest', '[of] ' + source);
		}

		if (target && !target.volatiles['partiallytrapped']) {
			target.addVolatile('partiallytrapped');
			this.add('-activate', target, 'move: Bind');
		}

		if (this.random(100) < 50) {
			const effects = ['slp', 'par', 'psn'];
			const chosen = this.sample(effects);

			if (chosen === 'slp') {
				target.trySetStatus('slp', source, move);
			} else if (chosen === 'par') {
				target.trySetStatus('par', source, move);
			} else if (chosen === 'psn') {
				target.trySetStatus('psn', source, move);
			}
		}

		if (target && target.side) {
			target.side.addSideCondition('spikes');
		}
	},

	self: {
		onHit(source) {
			let healAmount = 0.5;
			if (this.field.isTerrain('grassyterrain')) {
				if (this.field.isWeather('raindance')) {
					healAmount = 4/9;
				} else {
					healAmount = (2/3);
				}
			} else if (
				this.field.isWeather('sandstorm') ||
				this.field.isWeather('hail')
			) {
				healAmount = 0.25;
			}
			this.heal(Math.floor(source.maxhp * healAmount), source, source);
		},
	},
}
