{
  num: -175,
  accuracy: 100,
  basePower: 80,
  category: "Physical",
  name: "Electro Ears",
  pp: 10,
  priority: 0,
  type: "Electric",
  target: "normal",
  contestType: "Cute",
  flags: { protect: 1, mirror: 1, contact: 1 },

  onBasePower(basePower, pokemon) {
    if (pokemon.status === "par") {
      const newPower = basePower * 2;
      return newPower;
    }
  },
  onEffectiveness(typeMod, target, type, move) {
    return typeMod + this.dex.getEffectiveness("Fighting", type);
  }
}
