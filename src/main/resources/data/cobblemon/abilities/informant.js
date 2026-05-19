{
    onStart(source) {
        this.field.setTerrain('psychicterrain', source);
        this.add('-ability', source, 'Informant');
    },

    onModifyMove(move, pokemon, target) {
        if (this.field.isTerrain('psychicterrain') && target) {
            move.ignoreEvasion = true;
            move.ignoreDefensive = true;
        }
    },

    onResidualOrder: 28,
    onResidualSubOrder: 2,
    onResidual(pokemon) {
        if (!this.field.isTerrain('psychicterrain')) return;

        if (!pokemon.volatiles['informantacc']) {
            pokemon.addVolatile('informantacc');
        }
        const volatile = pokemon.volatiles['informantacc'];
        if (volatile.stages < 3) {
            this.boost({ accuracy: 1 }, pokemon, pokemon, this.effect);
            volatile.stages = (volatile.stages || 0) + 1;
        }
    },

    onSourceDamagingHit(damage, target, source, move) {
        if (move.type === 'Dark' && this.field.isTerrain('psychicterrain') && this.randomChance(20, 100)) {
            this.add('-ability', source, 'Informant');
            this.boost({ accuracy: -1 }, target, source, this.effect);
        }
    },

    onAfterMove(source, target, move) {
        if (!this.field.isTerrain('psychicterrain')) return;
        if ((move.type === 'Psychic' || move.type === 'Dark') &&
            move.category !== 'Status' &&
            target && target.hp > 0) {

            const terrain = this.field.terrainState;
            if (terrain && terrain.duration < 8) {
                terrain.duration += 1;
                this.add('-ability', source, 'Informant');
                this.add('-message', source, 'cobblemon.ability.informant.extend');
            }
        }
    },

    flags: {
        failroleplay: 1,
        noreceiver: 1,
        noentrain: 1,
        failskillswap: 1,
        breakable: 1,
        notrace: 1,
    },

    name: "Informant",
    rating: 4,
    num: -19457,
}