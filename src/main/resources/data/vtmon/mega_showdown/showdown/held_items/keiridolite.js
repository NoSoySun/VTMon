{
  name: "keiridolite",
  spritenum: -456,
  megaStone: "Keiridol-Mega",
  megaEvolves: ["Keiridol"],
  itemUser: ["Keiridol"],
  onTakeItem(item, source) {
    if (item.megaEvolves === source.baseSpecies.baseSpecies) return false;
    return true;
  },
  num: -456222,
  gen: 5,
  isNonstandard: "Past",
}