{
  name: "byohmite",
  spritenum: -450,
  megaStone: "Byohm-Mega",
  megaEvolves: ["Byohm"],
  itemUser: ["Byohm"],
  onTakeItem(item, source) {
    if (item.megaEvolves === source.baseSpecies.baseSpecies) return false;
    return true;
  },
  num: -456220,
  gen: 5,
  isNonstandard: "Past",
}