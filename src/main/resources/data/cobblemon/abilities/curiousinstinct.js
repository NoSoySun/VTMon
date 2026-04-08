{
		name: "Curious Instinct",
		rating: 2.5,
		num: -4736,

		onStart(pokemon) {
			if (pokemon.hasItem('Ability Shield')) {
				this.add('-block', pokemon, 'item: Ability Shield');
				return;
			}

			const foes = pokemon.side.foe.active.filter(
				foe =>
					foe &&
					!foe.fainted &&
					foe.ability !== 'noability' &&
					!foe.getAbility().flags?.notrace
			);

			if (!foes.length) return;

			const target = this.sample(foes);
			const ability = target.getAbility();

			if (pokemon.setAbility(ability)) {
				this.add(
					'-ability',
					pokemon,
					ability,
					'[from] ability: Curious Instinct',
					'[of] ' + target
				);
			}
		},

		flags: {
			failroleplay: 1,
			noreceiver: 1,
			noentrain: 1,
			notrace: 1,
		},
	}