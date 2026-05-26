package com.nosoysun.vtmon;

import com.github.yajatkaul.mega_showdown.components.MegaShowdownDataComponents;
import com.nosoysun.vtmon.item.ModItemGroups;
import com.nosoysun.vtmon.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.item.Items;                    // Cambia por el ítem que quieras modificar
import net.minecraft.util.Identifier;

public class VTMon implements ModInitializer {
	public static final String MOD_ID = "vtmon";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();

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