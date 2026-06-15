{
  name: "Kretsunite",
  spritenum: -457,
  megaStone: "Kretsune-Mega",
  megaEvolves: ["Kretsune"],
  itemUser: ["Kretsune"],
  onTakeItem(item, source) {
    if (item.megaEvolves.includes(source.baseSpecies.baseSpecies)) return false;
    return true;
  },
  num: -456224,
  gen: 9,
  isNonstandard: "Future",
}
