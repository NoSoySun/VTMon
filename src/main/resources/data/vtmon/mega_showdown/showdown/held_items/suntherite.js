{
  name: "suntherite",
  spritenum: -424,
  megaStone: "Sunther-Mega",
  megaEvolves: ["Sunther"],
  itemUser: ["Sunther"],
  onTakeItem(item, source) {
    if (item.megaEvolves === source.baseSpecies.baseSpecies) return false;
    return true;
  },
  num: -5362022,
  gen: 5,
  isNonstandard: "Past",
}