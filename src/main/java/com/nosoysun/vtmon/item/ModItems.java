package com.nosoysun.vtmon.item;

import com.github.yajatkaul.mega_showdown.components.MegaShowdownDataComponents;
import com.nosoysun.vtmon.VTMon;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

public class ModItems {

    // =========================
    // 🔥 SHOWDOWN ITEMS
    // =========================

    public static final Item BYOHMITE = registerItem(
            "byohmite",
            new Item(new Item.Settings()
                    .rarity(Rarity.UNCOMMON)
                    .component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "mega")
                    .component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(), Identifier.of("vtmon","byohmite"))
            ) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.vtmon.byohmite.tooltip"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            }
    );

    public static final Item BYOHMITE_Z = registerItem(
            "byohmite_z",
            new Item(new Item.Settings()
                    .rarity(Rarity.RARE)
                    .component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "mega")
                    .component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(), Identifier.of("vtmon","byohmite_z"))
            ) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.vtmon.byohmite_z.tooltip"));
                    super.appendTooltip(stack, context, tooltip, type);
                }

                @Override
                public boolean hasGlint(ItemStack stack) {
                    return true;
                }
            }
    );

    public static final Item KEIRIDOLITE = registerItem(
            "keiridolite",
            new Item(new Item.Settings()
                    .rarity(Rarity.UNCOMMON)
                    .component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "mega")
                    .component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(), Identifier.of("vtmon","keiridolite"))
            ) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.vtmon.keiridolite.tooltip"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            }
    );

    public static final Item CROWN = registerItem(
            "crown",
            new Item(new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "showdown_item")
                    .component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(), Identifier.of("vtmon","crown"))
            ) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.vtmon.crown.tooltip"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            }
    );

    public static final Item SCHLANGHITE = registerItem(
            "schlanghite",
            new Item(new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "mega")
                    .component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(), Identifier.of("vtmon","schlanghite"))
            ) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.vtmon.schlanghite.tooltip"));
                    super.appendTooltip(stack, context, tooltip, type);
                }

                @Override
                public boolean hasGlint(ItemStack stack) {
                    return true;
                }
            }
    );

    // =========================
    // ⏱️ CLOCK (VANILLA INTEGRATION)
    // =========================

    /**
     * Convierte minecraft:clock en un Showdown Item (vtmon:clock)
     * SOLO en contexto de batalla.
     */
    public static void applyClockShowdownComponent(ItemStack stack) {
        if (stack != null && stack.isOf(Items.CLOCK)) {

            stack.set(
                    MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(),
                    "showdown_item"
            );

            stack.set(
                    MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(),
                    Identifier.of("vtmon", "clock")
            );
        }
    }

    // =========================
    // ⚔️ ARMOR & TOOLS
    // =========================

    public static final Item EMERALD_HELMET = registerItem(
            "emerald_helmet",
            new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Settings().rarity(Rarity.RARE).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(35)))
    );

    public static final Item EMERALD_CHESTPLATE = registerItem(
            "emerald_chestplate",
            new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().rarity(Rarity.RARE).maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(35)))
    );

    public static final Item EMERALD_LEGGINGS = registerItem(
            "emerald_leggings",
            new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Settings().rarity(Rarity.EPIC).maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(35)))
    );

    public static final Item EMERALD_BOOTS = registerItem(
            "emerald_boots",
            new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL,
                    ArmorItem.Type.BOOTS,
                    new Item.Settings().rarity(Rarity.RARE)
                            .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(35)))
    );

    public static final Item EMERALD_SWORD = registerItem(
            "emerald_sword",
            new SwordItem(ModToolMaterials.EMERALD, new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.EMERALD, 3, 1.2F))) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.vtmon.emerald_sword.tooltip"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            }
    );

    public static final Item EMERALD_PICKAXE = registerItem(
            "emerald_pickaxe",
            new PickaxeItem(ModToolMaterials.EMERALD, new Item.Settings()
                    .rarity(Rarity.RARE)
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.EMERALD, 1.0F, 1.4f)))
    );

    public static final Item EMERALD_SHOVEL = registerItem(
            "emerald_shovel",
            new ShovelItem(ModToolMaterials.EMERALD, new Item.Settings()
                    .rarity(Rarity.RARE)
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.EMERALD, 1.5f, 1.5f)))
    );

    public static final Item EMERALD_AXE = registerItem(
            "emerald_axe",
            new AxeItem(ModToolMaterials.EMERALD, new Item.Settings()
                    .rarity(Rarity.RARE)
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.EMERALD, 5.0F, 1.5f)))
    );

    public static final Item EMERALD_HOE = registerItem(
            "emerald_hoe",
            new HoeItem(ModToolMaterials.EMERALD, new Item.Settings()
                    .rarity(Rarity.RARE)
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.EMERALD, 3.0F, 0.0F))) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.vtmon.emerald_hoe.tooltip"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            }
    );

    // =========================
    // 🧩 REGISTRO
    // =========================

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(VTMon.MOD_ID, name), item);
    }

    public static void registerModItems() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(EMERALD_AXE);
            entries.add(EMERALD_PICKAXE);
            entries.add(EMERALD_SHOVEL);
            entries.add(EMERALD_HOE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(EMERALD_SWORD);
            entries.add(EMERALD_AXE);
            entries.add(EMERALD_HELMET);
            entries.add(EMERALD_CHESTPLATE);
            entries.add(EMERALD_LEGGINGS);
            entries.add(EMERALD_BOOTS);
        });

        VTMon.LOGGER.info("Registering Mod Items for " + VTMon.MOD_ID);
    }
}