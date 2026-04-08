{
  name: "Schlanghite",
      spritenum: 568,
    megaStone: "Schlanghoul-MegaP",
    megaEvolves: "Schlanghoul-Phantom",
    itemUser: ["Schlanghoul-Phantom"],
    onTakeItem(item, source) {
  if (item.megaEvolves.includes(source.baseSpecies.baseSpecies)) return false;
  return true;
},
  num: 2584,
      gen: 9,
    isNonstandard: "Future",
}