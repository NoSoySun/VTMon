package com.nosoysun.vtmon.item;

import com.nosoysun.vtmon.VTMon;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup VTMON_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(VTMon.MOD_ID, "vtmon_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.KEIRIDOLITE))
                    .displayName(Text.translatable("itemgroup.vtmon.vtmon_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.BYOHMITE);
                        entries.add(ModItems.BYOHMITE_Z);
                        entries.add(ModItems.MEIKIUM_Z);
                        entries.add(ModItems.KEIRIDOLITE);
                        entries.add(ModItems.KRETSUNITE);
                        entries.add(ModItems.SCHLANGHITE);
                        entries.add(ModItems.SUNTHERITE);
                        entries.add(ModItems.EMERALD_HELMET);
                        entries.add(ModItems.EMERALD_CHESTPLATE);
                        entries.add(ModItems.EMERALD_LEGGINGS);
                        entries.add(ModItems.EMERALD_BOOTS);
                        entries.add(ModItems.EMERALD_SWORD);
                        entries.add(ModItems.EMERALD_KNIFE);
                        entries.add(ModItems.EMERALD_SHOVEL);
                        entries.add(ModItems.EMERALD_AXE);
                        entries.add(ModItems.EMERALD_PICKAXE);
                        entries.add(ModItems.EMERALD_HOE);
                        entries.add(ModItems.EMERALD_MACE);
                        entries.add(ModItems.CROWN);
                        entries.add(Items.CLOCK);
                        entries.add(ModItems.GEM_FOSSIL);
                    }).build());

    public static final ItemGroup VTMON_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(VTMon.MOD_ID, "vtmon_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.DISTORTED_SAND_ITEM))
                    .displayName(Text.translatable("itemgroup.vtmon.vtmon_blocks"))
                    .entries((displayContext, entries) -> Registries.ITEM.stream()
                            .filter(item -> item instanceof BlockItem)
                            .filter(item -> VTMon.MOD_ID.equals(Registries.ITEM.getId(item).getNamespace()))
                            .forEach(entries::add))
                    .build());
    public static void registerItemGroups() {
        VTMon.LOGGER.info("Registering Item Groups for " + VTMon.MOD_ID);
    }
}
