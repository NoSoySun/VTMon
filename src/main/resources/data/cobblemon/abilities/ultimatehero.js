{
    name: "Ultimate Hero",

    rating: 5,
    num: -1502,

    onModifyAtk() {
        return this.chainModify([77, 36]);
    },
    onModifyDef() {
        return this.chainModify([77, 36]);
    },
    onModifySpA() {
        return this.chainModify([77, 36]);
    },
    onModifySpD() {
        return this.chainModify([77, 36]);
    },
    onModifySpe() {
        return this.chainModify([77, 36]);
    },

    onBasePower(basePower, attacker, defender, move) {
        if (move.category !== "Status") {
            return this.chainModify(2);
        }
    },

    onModifyMove(move) {
        if (move.category !== "Status") {
            move.forceSTAB = true;
        }
    },

    onStart(pokemon) {
        this.add('-activate', pokemon, 'ability: Ultimate Hero');

        if (pokemon.hp < pokemon.maxHp) {
            this.heal(pokemon.maxHp - pokemon.hp);
        }
    },
    
  onResidual(pokemon) {
    if (pokemon.hp < pokemon.maxHp) {
            this.heal(Math.floor(pokemon.maxHp/8));
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