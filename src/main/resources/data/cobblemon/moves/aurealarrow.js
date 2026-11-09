{
		num: -457719,
		accuracy: true,
		basePower: 200,

    onModifySTAB(stab, source, target, move) {
        if (source.hasType("Grass") && source.hasType("Fairy")) {
            return stab * stab;
        } else if (source.hasType("Grass") || source.hasType("Fairy")) {
			return stab;
		}
    },

    onEffectiveness(typeMod, target, type, move) {
        if (move.forceSTAB) {
            return typeMod + this.dex.getEffectiveness("Fairy", type);
        }

        return typeMod;
    },
		category: "Special",
		isNonstandard: "Past",
		name: "Aureal Arrow",
		pp: 1,
		priority: 0,
		flags: {},
		isZ: "meikiumz",
		willCrit: true,
		secondary: null,
		target: "normal",
		type: "Grass",
		contestType: "Cool",
	}