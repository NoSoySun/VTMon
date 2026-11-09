{
	num: -1008,
	accuracy: true,
	basePower: 150,
	category: "Physical",
	name: "Shining Emerald",
	pp: 5,
	priority: 0,
	flags: {
		contact: 1,
		mirror: 1,
		slicing: 1,
	},
	drain: [3, 4],
	breaksProtect: true,
	breaksSubstitute: true,
	ignoreDefensive: true,
	infiltrates: true,
	willCrit: true,
	target: "normal",
	type: "Dragon",
	contestType: "Cool",
	onModifyMove(move, source, target) {
		if (!target) return;
		move.category = target.getStat("spd") < target.getStat("def") ? "Special" : "Physical";
	},
	self: {
		boosts: {
			atk: 1,
			spa: 1,
		},
	},
	onEffectiveness(typeMod, target, type, move) {
		for (const attackingType of [
			"Normal", "Fire", "Water", "Electric", "Grass", "Ice", "Fighting", "Poison", "Ground",
			"Flying", "Psychic", "Bug", "Rock", "Ghost", "Dark", "Steel", "Fairy",
		]) {
			typeMod += this.dex.getEffectiveness(attackingType, type);
		}
		return typeMod;
	},
	desc: "Shining Emerald has 150 base power, is Dragon-type, combines the type effectiveness of all 18 types, always lands a critical hit, ignores defensive boosts, Protect, and Substitute, and drains 75% of the damage dealt. It is Special if the target's Special Defense is lower than its Defense, and Physical if its Defense is lower; ties are Physical. It raises the user's Attack and Special Attack by one stage.",
	shortDesc: "150 BP; all 18 type effectiveness; category targets lower Defense; always crits; drains 75%; +1 Atk/SpA.",
}
