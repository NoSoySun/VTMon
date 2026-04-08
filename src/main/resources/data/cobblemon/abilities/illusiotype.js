{
	onBeforeSwitchIn(pokemon) {
	pokemon.illusion = null;
	if (!pokemon.abilityState.originalTypes) {
		pokemon.abilityState.originalTypes = pokemon.getTypes().slice();
	}

	for (let i = pokemon.side.pokemon.length - 1; i > pokemon.position; i--) {
		const possibleTarget = pokemon.side.pokemon[i];
		if (!possibleTarget.fainted) {
			if (!pokemon.terastallized || !['Ogerpon', 'Terapagos'].includes(possibleTarget.species.baseSpecies)) {
				pokemon.illusion = possibleTarget;
				pokemon.setType(possibleTarget.getTypes());
			}
			break;
		}
	}
},

	onDamagingHit(damage, target, source, move) {
	if (target.illusion) {
		this.singleEvent('End', this.dex.abilities.get('ilusiotipo'), target.abilityState, target, source, move);

		if (target.abilityState.originalTypes) {
			target.setType(target.abilityState.originalTypes);
		}

		if (source && this.checkMoveMakesContact(move, source, target)) {
			source.addVolatile('confusion', target);
		}

		const healing = target.maxhp - target.hp;
		if (healing > 0) {
			this.heal(healing, target, target, { from: 'ability: Ilusiotipo' });
		}
	}
},

	onEnd(pokemon) {
	if (pokemon.illusion && !pokemon.beingCalledBack) {
		this.debug('illusion cleared');

		if (pokemon.abilityState.originalTypes) {
			pokemon.setType(pokemon.abilityState.originalTypes);
		}

		pokemon.illusion = null;

		const details = pokemon.getUpdatedDetails();
		this.add('replace', pokemon, details);
		this.add('-end', pokemon, 'Ilusiotipo');

		if (this.ruleTable.has('illusionlevelmod')) {
			this.hint("Illusion Level Mod is active, so this Pokémon's true level was hidden.", true);
		}
	}
},

	onFaint(pokemon) {
	pokemon.illusion = null;
	if (pokemon.abilityState.originalTypes) {
		pokemon.setType(pokemon.abilityState.originalTypes);
	}
},

	flags: { failroleplay: 1, noreceiver: 1, noentrain: 1, notrace: 1, failskillswap: 1 },
	name: "Illusiotype",
		rating: 4.5,
	num: -1546,
}