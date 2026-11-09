package com.nosoysun.vtmon;

import com.github.yajatkaul.mega_showdown.components.MegaShowdownDataComponents;
import com.nosoysun.vtmon.item.ModItemGroups;
import com.nosoysun.vtmon.item.ModBlocks;
import com.nosoysun.vtmon.item.ModItems;
import com.nosoysun.vtmon.loot.GemFossilOncePerWorldCondition;
import com.nosoysun.vtmon.world.InterworldTravel;
import com.nosoysun.vtmon.world.ModEntities;
import com.nosoysun.vtmon.world.ModParticles;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.item.Items;                    // Cambia por el ítem que quieras modificar
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class VTMon implements ModInitializer {
	public static final String MOD_ID = "vtmon";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final RegistryKey<LootTable> SOL_HENGE_RUINS_KEY =
			RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of("cobblemon", "ruins/common/sol_henge_ruins"));

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModEntities.register();
		ModParticles.register();
		ServerTickEvents.END_SERVER_TICK.register(InterworldTravel::tick);

		Registry.register(Registries.LOOT_CONDITION_TYPE, GemFossilOncePerWorldCondition.ID, GemFossilOncePerWorldCondition.TYPE);

		LootTableEvents.MODIFY.register((key, builder, source, registries) -> {
			if (!key.equals(SOL_HENGE_RUINS_KEY)) {
				return;
			}
			LootPool.Builder pool = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(ModItems.GEM_FOSSIL))
					.conditionally(() -> GemFossilOncePerWorldCondition.INSTANCE);
			builder.pool(pool);
		});

		DefaultItemComponentEvents.MODIFY.register(context -> {

			context.modify(Items.CLOCK, builder -> {   // builder = ComponentMap.Builder

				builder.add(
						MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(),
						"showdown_item"
				);

				builder.add(
						MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(),
						Identifier.of("vtmon", "clock")
				);

			});

		});
	}
}
