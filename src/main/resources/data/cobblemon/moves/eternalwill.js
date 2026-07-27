{
	num: -1006,
	accuracy: true,
	basePower: 0,
	category: "Status",
	name: "Eternal Will",
	pp: 5,
	priority: 0,
	flags: {snatch: 1, metronome: 1, bypasssub: 1},
	self: {
		onHit(source) {
			this.boost({atk: 2, spa: 2, spe: 2, accuracy: 2}, source);
		},
	},
	onHit(target, source, move) {
		for (const ally of source.side.active) {
			if (ally && !ally.fainted && ally !== source) {
				this.boost({atk: 1, spa: 1, spe: 1, accuracy: 1}, ally);
			}
		}
	},
	secondary: null,
	target: "self",
	type: "Fairy",
	contestType: "Beautiful",
}
