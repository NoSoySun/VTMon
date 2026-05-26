package com.nosoysun.vtmon.item;

import com.nosoysun.vtmon.VTMon;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {
	public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
			DeferredRegister.create(Registries.ARMOR_MATERIAL, VTMon.MOD_ID);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> EMERALD_ARMOR_MATERIAL =
			ARMOR_MATERIALS.register("emerald", () -> new ArmorMaterial(
					Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
						map.put(ArmorItem.Type.BOOTS, 4);
						map.put(ArmorItem.Type.LEGGINGS, 10);
						map.put(ArmorItem.Type.CHESTPLATE, 12);
						map.put(ArmorItem.Type.HELMET, 4);
						map.put(ArmorItem.Type.BODY, 4);
					}),
					20,
					SoundEvents.ARMOR_EQUIP_DIAMOND,
					() -> Ingredient.of(Items.EMERALD),
					List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(VTMon.MOD_ID, "emerald"))),
					3.5F,
					0.3F
			));

	public static void register(IEventBus modEventBus) {
		ARMOR_MATERIALS.register(modEventBus);
	}
}
