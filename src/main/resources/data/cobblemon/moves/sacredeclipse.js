{
	num: -1007,
	accuracy: 100,
	basePower: 50,
    basePowerCallback(pokemon, target, move) {
        if (move.hit !== move.multihit) {
			return move.basePower * move.hit;
		}else {
			return 250;
		}
    },
	category: "Special",
	name: "Sacred Eclipse",
	pp: 10,
	priority: 0,
	flags: {protect: 1, mirror: 1, metronome: 1},
	multihit: [5, 6],
	onTryHit(target, source, move) {
		move.willCrit = move.hit === move.multihit ? true : undefined;
	},
	onAfterMove(pokemon, target, move) {
		const boost = () => this.random(1, move.hit + 1);
		this.boost({
			atk: boost(),
			def: boost(),
			spa: boost(),
			spd: boost(),
			spe: boost(),
			accuracy: boost(),
			evasion: boost(),
		}, pokemon);
	},
	smartTarget: true,
	secondary: null,
	target: "normal",
	type: "Dragon",
	contestType: "Cool",
}
