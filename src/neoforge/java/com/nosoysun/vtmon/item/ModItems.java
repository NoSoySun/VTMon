package com.nosoysun.vtmon.item;

import com.github.yajatkaul.mega_showdown.components.MegaShowdownDataComponents;
import com.nosoysun.vtmon.VTMon;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
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
					return true;
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

	public static void register(IEventBus modEventBus) {
		ITEMS.register(modEventBus);
		modEventBus.addListener(ModItems::addCreativeTabItems);
		VTMon.LOGGER.info("Registering Mod Items for " + VTMon.MOD_ID);
	}

	private static void addCreativeTabItems(BuildCreativeModeTabContentsEvent event) {
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
