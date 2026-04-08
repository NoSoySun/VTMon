{
	onBeforeSwitchIn(pokemon) {
		pokemon.illusion = null;
		for (let i = pokemon.side.pokemon.length - 1; i > pokemon.position; i--) {
			const possibleTarget = pokemon.side.pokemon[i];
			if (!possibleTarget.fainted) {
				if (!pokemon.terastallized || possibleTarget.species.baseSpecies !== 'Ogerpon') {
					pokemon.illusion = possibleTarget;
				}
				break;
			}
		}
	},
	onDamagingHit(damage, target, source, move) {
		if (target.illusion) {
			this.singleEvent('End', this.dex.abilities.get('Illusion'), target.abilityState, target, source, move);
			if (this.checkMoveMakesContact(move, source, target)) {
				source.trySetStatus('brn', target);
			}
			const healing = target.maxhp - target.hp;
			this.heal(healing);
		}
	},
	onEnd(pokemon) {
		if (pokemon.illusion) {
			this.debug('illusion cleared');
			pokemon.illusion = null;
			const details = pokemon.species.name + (pokemon.level === 100 ? '' : ', L' + pokemon.level) +
				(pokemon.gender === '' ? '' : ', ' + pokemon.gender) + (pokemon.set.shiny ? ', shiny' : '');
			this.add('replace', pokemon, details);
			this.add('-end', pokemon, 'Illusion');

			if (this.ruleTable.has('illusionlevelmod')) {
				this.hint("Illusion Level Mod is active, so this Pok\u00e9mon's true level was hidden.", true);
			}
		}
	},
	onFaint(pokemon) {
		pokemon.illusion = null;
	},
	flags: { failroleplay: 1, noreceiver: 1, noentrain: 1, notrace: 1, failskillswap: 1 },
	name: "Illusory Ember",
		rating: 4.5,
			num: -1499,
}
