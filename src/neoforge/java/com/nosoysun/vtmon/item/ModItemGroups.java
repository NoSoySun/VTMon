package com.nosoysun.vtmon.item;

import com.nosoysun.vtmon.VTMon;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItemGroups {
	public static final DeferredRegister<CreativeModeTab> ITEM_GROUPS =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VTMon.MOD_ID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> VTMON_ITEMS =
			ITEM_GROUPS.register("vtmon_items", () -> CreativeModeTab.builder()
					.icon(() -> new ItemStack(ModItems.KEIRIDOLITE.get()))
					.title(Component.translatable("itemgroup.vtmon.vtmon_items"))
					.displayItems((displayContext, entries) -> {
						entries.accept(ModItems.BYOHMITE.get());
						entries.accept(ModItems.BYOHMITE_Z.get());
						entries.accept(ModItems.KEIRIDOLITE.get());
						entries.accept(ModItems.SCHLANGHITE.get());
						entries.accept(ModItems.EMERALD_HELMET.get());
						entries.accept(ModItems.EMERALD_CHESTPLATE.get());
						entries.accept(ModItems.EMERALD_LEGGINGS.get());
						entries.accept(ModItems.EMERALD_BOOTS.get());
						entries.accept(ModItems.EMERALD_SWORD.get());
						entries.accept(ModItems.EMERALD_KNIFE.get());
						entries.accept(ModItems.EMERALD_SHOVEL.get());
						entries.accept(ModItems.EMERALD_AXE.get());
						entries.accept(ModItems.EMERALD_PICKAXE.get());
						entries.accept(ModItems.EMERALD_HOE.get());
						entries.accept(ModItems.EMERALD_MACE.get());
						entries.accept(ModItems.CROWN.get());
						entries.accept(Items.CLOCK);
					}).build());

	public static void register(IEventBus modEventBus) {
		ITEM_GROUPS.register(modEventBus);
		VTMon.LOGGER.info("Registering Item Groups for " + VTMon.MOD_ID);
	}
}
