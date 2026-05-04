peloton: {
	name: "Pelotón",
	shortDesc: "Cambia a forma Battalion si tiene >25% PS. Al volver a la forma base recupera todos los PS.",
	onStart(pokemon) {
		if (this.suppressingAbility(pokemon)) return;
		this.add('-ability', pokemon, 'Pelotón');

		this.tryFormChange(pokemon);
	},
	onResidualOrder: 29,
	onResidual(pokemon) {
		this.tryFormChange(pokemon);
	},
	tryFormChange(pokemon) {
		if (pokemon.baseSpecies.baseSpecies !== 'Tokarmy' || pokemon.transformed || !pokemon.hp) return;

		if (pokemon.hp > pokemon.maxhp / 4) {
			// → Forma Pelotón (Battalion)
			if (pokemon.species.id === 'tokarmy') {
				this.add('-activate', pokemon, 'ability: Pelotón');
				pokemon.formeChange('Tokarmy-Battalion', this.effect, true);

				// Recálculo exacto de PS (como en Corrupted Soul)
				pokemon.baseMaxhp = Math.floor(Math.floor(
					2 * pokemon.species.baseStats['hp'] + pokemon.set.ivs['hp'] + Math.floor(pokemon.set.evs['hp'] / 4) + 100
				) * pokemon.level / 100 + 10);
				const newMaxHP = pokemon.volatiles['dynamax'] ? (2 * pokemon.baseMaxhp) : pokemon.baseMaxhp;
				pokemon.hp = newMaxHP - (pokemon.maxhp - pokemon.hp);
				pokemon.maxhp = newMaxHP;
				this.add('-heal', pokemon, pokemon.getHealth, '[silent]');
			}
		} else {
			// → Vuelta a forma base + cura al 100% (lo que pediste)
			if (pokemon.species.id === 'tokarmybatallion') {
				this.add('-activate', pokemon, 'ability: Pelotón');
				pokemon.formeChange('Tokarmy', this.effect, true);

				// Recálculo de PS (mantiene el daño recibido)
				pokemon.baseMaxhp = Math.floor(Math.floor(
					2 * pokemon.species.baseStats['hp'] + pokemon.set.ivs['hp'] + Math.floor(pokemon.set.evs['hp'] / 4) + 100
				) * pokemon.level / 100 + 10);
				const newMaxHP = pokemon.volatiles['dynamax'] ? (2 * pokemon.baseMaxhp) : pokemon.baseMaxhp;
				pokemon.hp = newMaxHP - (pokemon.maxhp - pokemon.hp);
				pokemon.maxhp = newMaxHP;
				this.add('-heal', pokemon, pokemon.getHealth, '[silent]');

				// Cura total al deshacer la transformación
				pokemon.hp = pokemon.maxhp;
				this.add('-heal', pokemon, pokemon.getHealth);
			}
		}
	},
	flags: { failroleplay: 1, noreceiver: 1, noentrain: 1, notrace: 1, failskillswap: 1, cantsuppress: 1 },
	rating: 3,
	num: -1442, // número libre (puedes cambiarlo)
},