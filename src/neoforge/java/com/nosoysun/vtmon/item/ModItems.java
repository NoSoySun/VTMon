package com.nosoysun.vtmon.item;

import com.github.yajatkaul.mega_showdown.components.MegaShowdownDataComponents;
import com.nosoysun.vtmon.VTMon;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VTMon.MOD_ID);

	public static final DeferredItem<BlockItem> DISTORTED_SAND = ITEMS.register(
			"distorted_sand",
			() -> new BlockItem(ModBlocks.DISTORTED_SAND.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> BLIGHTED_GRASS_BLOCK = ITEMS.register(
			"blighted_grass_block",
			() -> new BlockItem(ModBlocks.BLIGHTED_GRASS_BLOCK.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> WITHERED_DIRT = ITEMS.register(
			"withered_dirt",
			() -> new BlockItem(ModBlocks.WITHERED_DIRT.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> WITHERED_FARMLAND = ITEMS.register(
			"withered_farmland",
			() -> new BlockItem(ModBlocks.WITHERED_FARMLAND.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> DISTORTED_NETHERRACK = ITEMS.register(
			"distorted_netherrack",
			() -> new BlockItem(ModBlocks.DISTORTED_NETHERRACK.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> DISTORTED_BASALT = ITEMS.register(
			"distorted_basalt",
			() -> new BlockItem(ModBlocks.DISTORTED_BASALT.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> DISTORTED_NETHERRACK_BRICKS = ITEMS.register(
			"distorted_netherrack_bricks",
			() -> new BlockItem(ModBlocks.DISTORTED_NETHERRACK_BRICKS.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CHISELED_DISTORTED_NETHERRACK_BRICKS = ITEMS.register(
			"chiseled_distorted_netherrack_bricks",
			() -> new BlockItem(ModBlocks.CHISELED_DISTORTED_NETHERRACK_BRICKS.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CRACKED_DISTORTED_NETHERRACK_BRICKS = ITEMS.register(
			"cracked_distorted_netherrack_bricks",
			() -> new BlockItem(ModBlocks.CRACKED_DISTORTED_NETHERRACK_BRICKS.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> DISTORTED_SANDSTONE = ITEMS.register(
			"distorted_sandstone",
			() -> new BlockItem(ModBlocks.DISTORTED_SANDSTONE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CUT_DISTORTED_SANDSTONE = ITEMS.register(
			"cut_distorted_sandstone",
			() -> new BlockItem(ModBlocks.CUT_DISTORTED_SANDSTONE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> SMOOTH_DISTORTED_SANDSTONE = ITEMS.register(
			"smooth_distorted_sandstone",
			() -> new BlockItem(ModBlocks.SMOOTH_DISTORTED_SANDSTONE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CHISELED_DISTORTED_SANDSTONE = ITEMS.register(
			"chiseled_distorted_sandstone",
			() -> new BlockItem(ModBlocks.CHISELED_DISTORTED_SANDSTONE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> WITHERED_MUD = ITEMS.register(
			"withered_mud",
			() -> new BlockItem(ModBlocks.WITHERED_MUD.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> WITHERED_MUD_BRICKS = ITEMS.register(
			"withered_mud_bricks",
			() -> new BlockItem(ModBlocks.WITHERED_MUD_BRICKS.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CRACKED_WITHERED_MUD_BRICKS = ITEMS.register(
			"cracked_withered_mud_bricks",
			() -> new BlockItem(ModBlocks.CRACKED_WITHERED_MUD_BRICKS.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CHISELED_WITHERED_MUD_BRICKS = ITEMS.register(
			"chiseled_withered_mud_bricks",
			() -> new BlockItem(ModBlocks.CHISELED_WITHERED_MUD_BRICKS.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> POLISHED_DISTORTED_BASALT = ITEMS.register(
			"polished_distorted_basalt",
			() -> new BlockItem(ModBlocks.POLISHED_DISTORTED_BASALT.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> SMOOTH_DISTORTED_BASALT = ITEMS.register(
			"smooth_distorted_basalt",
			() -> new BlockItem(ModBlocks.SMOOTH_DISTORTED_BASALT.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CORRODED_STONE = ITEMS.register(
			"corroded_stone",
			() -> new BlockItem(ModBlocks.CORRODED_STONE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> COBBLED_CORRODED_STONE = ITEMS.register(
			"cobbled_corroded_stone",
			() -> new BlockItem(ModBlocks.COBBLED_CORRODED_STONE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> POLISHED_CORRODED_STONE = ITEMS.register(
			"polished_corroded_stone",
			() -> new BlockItem(ModBlocks.POLISHED_CORRODED_STONE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CORRODED_STONE_BRICKS = ITEMS.register(
			"corroded_stone_bricks",
			() -> new BlockItem(ModBlocks.CORRODED_STONE_BRICKS.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CRACKED_CORRODED_STONE_BRICKS = ITEMS.register(
			"cracked_corroded_stone_bricks",
			() -> new BlockItem(ModBlocks.CRACKED_CORRODED_STONE_BRICKS.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CHISELED_CORRODED_STONE_BRICKS = ITEMS.register(
			"chiseled_corroded_stone_bricks",
			() -> new BlockItem(ModBlocks.CHISELED_CORRODED_STONE_BRICKS.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CORRODED_COAL_ORE = ITEMS.register(
			"corroded_coal_ore", () -> new BlockItem(ModBlocks.CORRODED_COAL_ORE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CORRODED_COPPER_ORE = ITEMS.register(
			"corroded_copper_ore", () -> new BlockItem(ModBlocks.CORRODED_COPPER_ORE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CORRODED_IRON_ORE = ITEMS.register(
			"corroded_iron_ore", () -> new BlockItem(ModBlocks.CORRODED_IRON_ORE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CORRODED_GOLD_ORE = ITEMS.register(
			"corroded_gold_ore", () -> new BlockItem(ModBlocks.CORRODED_GOLD_ORE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CORRODED_REDSTONE_ORE = ITEMS.register(
			"corroded_redstone_ore", () -> new BlockItem(ModBlocks.CORRODED_REDSTONE_ORE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CORRODED_LAPIS_ORE = ITEMS.register(
			"corroded_lapis_ore", () -> new BlockItem(ModBlocks.CORRODED_LAPIS_ORE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CORRODED_DIAMOND_ORE = ITEMS.register(
			"corroded_diamond_ore", () -> new BlockItem(ModBlocks.CORRODED_DIAMOND_ORE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CORRODED_EMERALD_ORE = ITEMS.register(
			"corroded_emerald_ore", () -> new BlockItem(ModBlocks.CORRODED_EMERALD_ORE.get(), new Item.Properties())
	);
	public static final DeferredItem<BlockItem> CORRODED_NETHER_QUARTZ_ORE = ITEMS.register(
			"corroded_nether_quartz_ore", () -> new BlockItem(ModBlocks.CORRODED_NETHER_QUARTZ_ORE.get(), new Item.Properties())
	);

	public static final List<DeferredItem<BlockItem>> ADDITIONAL_VARIANT_ITEMS = registerAdditionalVariantItems();

	public static final DeferredItem<Item> BYOHMITE = registerItem(
			"byohmite",
			() -> new Item(new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "mega")
					.component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(),
							ResourceLocation.fromNamespaceAndPath("vtmon", "byohmite"))
			) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
					tooltip.add(Component.translatable("tooltip.vtmon.byohmite.tooltip"));
					super.appendHoverText(stack, context, tooltip, flag);
				}
			}
	);

	public static final DeferredItem<Item> BYOHMITE_Z = registerItem(
			"byohmite_z",
			() -> new Item(new Item.Properties()
					.rarity(Rarity.RARE)
					.component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "mega")
					.component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(),
							ResourceLocation.fromNamespaceAndPath("vtmon", "byohmite_z"))
			) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
					tooltip.add(Component.translatable("tooltip.vtmon.byohmite_z.tooltip"));
					super.appendHoverText(stack, context, tooltip, flag);
				}

				@Override
				public boolean isFoil(ItemStack stack) {
					Minecraft client = Minecraft.getInstance();
					return client.level != null && client.level.isNight();
				}
			}
	);

	public static final DeferredItem<Item> KEIRIDOLITE = registerItem(
			"keiridolite",
			() -> new Item(new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "mega")
					.component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(),
							ResourceLocation.fromNamespaceAndPath("vtmon", "keiridolite"))
			) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
					tooltip.add(Component.translatable("tooltip.vtmon.keiridolite.tooltip"));
					super.appendHoverText(stack, context, tooltip, flag);
				}
			}
	);

	public static final DeferredItem<Item> KRETSUNITE = registerItem(
			"kretsunite",
			() -> new Item(new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "mega")
					.component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(),
							ResourceLocation.fromNamespaceAndPath("vtmon", "kretsunite"))
			) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
					tooltip.add(Component.translatable("tooltip.vtmon.kretsunite.tooltip"));
					super.appendHoverText(stack, context, tooltip, flag);
				}
			}
	);

	public static final DeferredItem<Item> CROWN = registerItem(
			"crown",
			() -> new Item(new Item.Properties()
					.rarity(Rarity.EPIC)
					.component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "showdown_item")
					.component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(),
							ResourceLocation.fromNamespaceAndPath("vtmon", "crown"))
			) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
					tooltip.add(Component.translatable("tooltip.vtmon.crown.tooltip"));
					super.appendHoverText(stack, context, tooltip, flag);
				}
			}
	);

	public static final DeferredItem<Item> SCHLANGHITE = registerItem(
			"schlanghite",
			() -> new Item(new Item.Properties()
					.rarity(Rarity.EPIC)
					.component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "mega")
					.component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(),
							ResourceLocation.fromNamespaceAndPath("vtmon", "schlanghite"))
			) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
					tooltip.add(Component.translatable("tooltip.vtmon.schlanghite.tooltip"));
					super.appendHoverText(stack, context, tooltip, flag);
				}

				@Override
				public boolean isFoil(ItemStack stack) {
					return true;
				}
			}
	);

	public static final DeferredItem<Item> SUNTHERITE = registerItem(
			"suntherite",
			() -> new Item(new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "mega")
					.component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(),
							ResourceLocation.fromNamespaceAndPath("vtmon", "suntherite"))
			) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
					tooltip.add(Component.translatable("tooltip.vtmon.suntherite.tooltip"));
					super.appendHoverText(stack, context, tooltip, flag);
				}
			}
	);

	public static final DeferredItem<Item> MEIKIUM_Z = registerItem(
			"meikium_z",
			() -> new Item(new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.component(MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(), "z_crystal_item")
					.component(MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(),
							ResourceLocation.fromNamespaceAndPath("vtmon", "meikium_z"))
			) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
					tooltip.add(Component.translatable("tooltip.vtmon.meikium_z.tooltip"));
					super.appendHoverText(stack, context, tooltip, flag);
				}
			}
	);

	public static final DeferredItem<Item> GEM_FOSSIL = registerItem(
			"gem_fossil",
			() -> new Item(new Item.Properties().rarity(Rarity.EPIC)) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
					tooltip.add(Component.translatable("tooltip.vtmon.gem_fossil.tooltip"));
					super.appendHoverText(stack, context, tooltip, flag);
				}
			}
	);

	public static final DeferredItem<Item> EMERALD_HELMET = registerItem(
			"emerald_helmet",
			() -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL,
					ArmorItem.Type.HELMET,
					new Item.Properties().rarity(Rarity.RARE).durability(ArmorItem.Type.HELMET.getDurability(35)))
	);

	public static final DeferredItem<Item> EMERALD_CHESTPLATE = registerItem(
			"emerald_chestplate",
			() -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL,
					ArmorItem.Type.CHESTPLATE,
					new Item.Properties().rarity(Rarity.RARE).durability(ArmorItem.Type.CHESTPLATE.getDurability(35)))
	);

	public static final DeferredItem<Item> EMERALD_LEGGINGS = registerItem(
			"emerald_leggings",
			() -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL,
					ArmorItem.Type.LEGGINGS,
					new Item.Properties().rarity(Rarity.EPIC).durability(ArmorItem.Type.LEGGINGS.getDurability(35)))
	);

	public static final DeferredItem<Item> EMERALD_BOOTS = registerItem(
			"emerald_boots",
			() -> new ArmorItem(ModArmorMaterials.EMERALD_ARMOR_MATERIAL,
					ArmorItem.Type.BOOTS,
					new Item.Properties().rarity(Rarity.RARE).durability(ArmorItem.Type.BOOTS.getDurability(35)))
	);

	public static final DeferredItem<Item> EMERALD_SWORD = registerItem(
			"emerald_sword",
			() -> new SwordItem(ModToolMaterials.EMERALD, new Item.Properties()
					.rarity(Rarity.EPIC)
					.attributes(SwordItem.createAttributes(ModToolMaterials.EMERALD, 3, 1.2F))) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
					tooltip.add(Component.translatable("tooltip.vtmon.emerald_sword.tooltip"));
					super.appendHoverText(stack, context, tooltip, flag);
				}
			}
	);

	private static final ItemAbility KNIFE_DIG = ItemAbility.get("knife_dig");
	private static final ItemAbility KNIFE_HARVEST = ItemAbility.get("knife_harvest");
	private static final Set<ItemAbility> EMERALD_KNIFE_ABILITIES = Set.of(KNIFE_DIG, KNIFE_HARVEST);

	public static final DeferredItem<Item> EMERALD_KNIFE = registerItem(
			"emerald_knife",
			() -> new SwordItem(ModToolMaterials.EMERALD, new Item.Properties()
					.rarity(Rarity.RARE)
					.attributes(SwordItem.createAttributes(ModToolMaterials.EMERALD, 1, 2.0F))) {
				@Override
				public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
					return EMERALD_KNIFE_ABILITIES.contains(itemAbility) || super.canPerformAction(stack, itemAbility);
				}
			}
	);

	public static final DeferredItem<Item> EMERALD_PICKAXE = registerItem(
			"emerald_pickaxe",
			() -> new PickaxeItem(ModToolMaterials.EMERALD, new Item.Properties()
					.rarity(Rarity.RARE)
					.attributes(PickaxeItem.createAttributes(ModToolMaterials.EMERALD, 1.0F, 1.4f)))
	);

	public static final DeferredItem<Item> EMERALD_SHOVEL = registerItem(
			"emerald_shovel",
			() -> new ShovelItem(ModToolMaterials.EMERALD, new Item.Properties()
					.rarity(Rarity.RARE)
					.attributes(ShovelItem.createAttributes(ModToolMaterials.EMERALD, 1.5f, 1.5f)))
	);

	public static final DeferredItem<Item> EMERALD_AXE = registerItem(
			"emerald_axe",
			() -> new AxeItem(ModToolMaterials.EMERALD, new Item.Properties()
					.rarity(Rarity.RARE)
					.attributes(AxeItem.createAttributes(ModToolMaterials.EMERALD, 5.0F, 1.5f)))
	);

	public static final DeferredItem<Item> EMERALD_HOE = registerItem(
			"emerald_hoe",
			() -> new HoeItem(ModToolMaterials.EMERALD, new Item.Properties()
					.rarity(Rarity.RARE)
					.attributes(HoeItem.createAttributes(ModToolMaterials.EMERALD, 3.0F, 0.0F))) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
					tooltip.add(Component.translatable("tooltip.vtmon.emerald_hoe.tooltip"));
					super.appendHoverText(stack, context, tooltip, flag);
				}
			}
	);

	public static final DeferredItem<Item> EMERALD_MACE = registerItem("emerald_mace", () -> new MaceItem(new Item.Properties()
			.rarity(Rarity.EPIC)
			.durability(1000)
			.fireResistant()
			.component(DataComponents.TOOL, MaceItem.createToolProperties())
			.attributes(createEmeraldMaceAttributes())
	) {
		@Override
		public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
			tooltip.add(Component.translatable("tooltip.vtmon.emerald_mace.tooltip").withStyle(ChatFormatting.ITALIC));
			super.appendHoverText(stack, context, tooltip, flag);
		}
	});

	private static ItemAttributeModifiers createEmeraldMaceAttributes() {
		return ItemAttributeModifiers.builder()
				.add(
						Attributes.ATTACK_DAMAGE,
						new AttributeModifier(
								Item.BASE_ATTACK_DAMAGE_ID,
								11.0,
								AttributeModifier.Operation.ADD_VALUE
						),
						EquipmentSlotGroup.MAINHAND
				)
				.add(
						Attributes.ATTACK_SPEED,
						new AttributeModifier(
								Item.BASE_ATTACK_SPEED_ID,
								-1.7F,
								AttributeModifier.Operation.ADD_VALUE
						),
						EquipmentSlotGroup.MAINHAND
				)
				.build();
	}

	private static <T extends Item> DeferredItem<T> registerItem(String name, Supplier<T> item) {
		return ITEMS.register(name, item);
	}

	private static List<DeferredItem<BlockItem>> registerAdditionalVariantItems() {
		return ModBlocks.ADDITIONAL_VARIANT_BLOCKS.stream()
				.map(block -> ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties())))
				.toList();
	}

	public static void register(IEventBus modEventBus) {
		ITEMS.register(modEventBus);
		modEventBus.addListener(ModItems::addCreativeTabItems);
		VTMon.LOGGER.info("Registering Mod Items for " + VTMon.MOD_ID);
	}

	private static void addCreativeTabItems(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			event.accept(DISTORTED_SAND.get());
			event.accept(BLIGHTED_GRASS_BLOCK.get());
			event.accept(WITHERED_DIRT.get());
			event.accept(WITHERED_FARMLAND.get());
			event.accept(DISTORTED_NETHERRACK.get());
			event.accept(DISTORTED_BASALT.get());
			event.accept(DISTORTED_NETHERRACK_BRICKS.get());
			event.accept(CHISELED_DISTORTED_NETHERRACK_BRICKS.get());
			event.accept(CRACKED_DISTORTED_NETHERRACK_BRICKS.get());
			event.accept(DISTORTED_SANDSTONE.get());
			event.accept(CUT_DISTORTED_SANDSTONE.get());
			event.accept(SMOOTH_DISTORTED_SANDSTONE.get());
			event.accept(CHISELED_DISTORTED_SANDSTONE.get());
			event.accept(WITHERED_MUD.get());
			event.accept(WITHERED_MUD_BRICKS.get());
			event.accept(CRACKED_WITHERED_MUD_BRICKS.get());
			event.accept(CHISELED_WITHERED_MUD_BRICKS.get());
			event.accept(POLISHED_DISTORTED_BASALT.get());
			event.accept(SMOOTH_DISTORTED_BASALT.get());
						event.accept(CORRODED_STONE.get());
						event.accept(COBBLED_CORRODED_STONE.get());
						event.accept(POLISHED_CORRODED_STONE.get());
						event.accept(CORRODED_STONE_BRICKS.get());
						event.accept(CRACKED_CORRODED_STONE_BRICKS.get());
						event.accept(CHISELED_CORRODED_STONE_BRICKS.get());
				event.accept(CORRODED_COAL_ORE.get());
				event.accept(CORRODED_COPPER_ORE.get());
				event.accept(CORRODED_IRON_ORE.get());
				event.accept(CORRODED_GOLD_ORE.get());
				event.accept(CORRODED_REDSTONE_ORE.get());
				event.accept(CORRODED_LAPIS_ORE.get());
				event.accept(CORRODED_DIAMOND_ORE.get());
				event.accept(CORRODED_EMERALD_ORE.get());
				event.accept(CORRODED_NETHER_QUARTZ_ORE.get());
			ADDITIONAL_VARIANT_ITEMS.forEach(event::accept);
		}

		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			event.accept(EMERALD_AXE.get());
			event.accept(EMERALD_PICKAXE.get());
			event.accept(EMERALD_SHOVEL.get());
			event.accept(EMERALD_HOE.get());
			event.accept(EMERALD_KNIFE.get());
		}

		if (event.getTabKey() == CreativeModeTabs.COMBAT) {
			event.accept(EMERALD_SWORD.get());
			event.accept(EMERALD_KNIFE.get());
			event.accept(EMERALD_AXE.get());
			event.accept(EMERALD_MACE.get());
			event.accept(EMERALD_HELMET.get());
			event.accept(EMERALD_CHESTPLATE.get());
			event.accept(EMERALD_LEGGINGS.get());
			event.accept(EMERALD_BOOTS.get());
		}
	}
}
