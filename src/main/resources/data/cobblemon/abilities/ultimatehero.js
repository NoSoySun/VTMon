{
    name: "Ultimate Hero",

    rating: 5,
    num: -1502,

    onModifyAtkPriority: 5,
    onModifyAtk(atk, pokemon) {
        const stats = pokemon.species.baseStats;
        const total = stats.hp + stats.atk + stats.def + stats.spa + stats.spd + stats.spe;
        const hp = stats.hp;

        const k = ((2 * total) - hp) / (total - hp);

        return this.chainModify(k);
    },

    onModifyDefPriority: 5,
    onModifyDef(def, pokemon) {
        const stats = pokemon.species.baseStats;
        const total = stats.hp + stats.atk + stats.def + stats.spa + stats.spd + stats.spe;
        const hp = stats.hp;

        const k = ((2 * total) - hp) / (total - hp);

        return this.chainModify(k);
    },

    onModifySpAPriority: 5,
    onModifySpA(spa, pokemon) {
        const stats = pokemon.species.baseStats;
        const total = stats.hp + stats.atk + stats.def + stats.spa + stats.spd + stats.spe;
        const hp = stats.hp;

        const k = ((2 * total) - hp) / (total - hp);

        return this.chainModify(k);
    },

    onModifySpDPriority: 5,
    onModifySpD(spd, pokemon) {
        const stats = pokemon.species.baseStats;
        const total = stats.hp + stats.atk + stats.def + stats.spa + stats.spd + stats.spe;
        const hp = stats.hp;

        const k = ((2 * total) - hp) / (total - hp);

        return this.chainModify(k);
    },

    onModifySpePriority: 5,
    onModifySpe(spe, pokemon) {
        const stats = pokemon.species.baseStats;
        const total = stats.hp + stats.atk + stats.def + stats.spa + stats.spd + stats.spe;
        const hp = stats.hp;

        const k = ((2 * total) - hp) / (total - hp);

        return this.chainModify(k);
    },

    onBasePowerPriority: 21,
    onBasePower(basePower, attacker, defender, move) {
        if (move.category !== "Status") {
            return this.chainModify([5, 2]);
        }
    },

    onModifyMove(move) {
        if (move.category !== "Status") {
            move.forceSTAB = true;
        }
    },

    onStart(pokemon) {
        this.add("-activate", pokemon, "ability: Ultimate Hero");

        if (pokemon.hp < pokemon.maxhp) {
            this.heal(pokemon.maxhp - pokemon.hp, pokemon);
        }
    },

    onResidualOrder: 5,
    onResidualSubOrder: 2,
    onResidual(pokemon) {
        if (pokemon.hp && pokemon.hp < pokemon.maxhp) {
            this.heal(pokemon.baseMaxhp / 8, pokemon);
        }
    },

    flags: {
        failroleplay: 1,
        noreceiver: 1,
        noentrain: 1,
        notrace: 1,
        failskillswap: 1,
    },
}