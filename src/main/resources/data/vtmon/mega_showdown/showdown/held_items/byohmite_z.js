{
  name: "Byohmite Z",
  spritenum: -666,
  megaStone: "Byohm-Mega-Z",
  megaEvolves: ["Byohm"],
  itemUser: ["Byohm"],
  onTakeItem(item, source) {
    if (item.megaEvolves.includes(source.baseSpecies.baseSpecies)) return false;
    return true;
  },
  num: -9992,
  gen: 5,
  isNonstandard: "Past"
}