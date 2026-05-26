package com.nosoysun.vtmon;

import com.github.yajatkaul.mega_showdown.components.MegaShowdownDataComponents;
import com.nosoysun.vtmon.item.ModArmorMaterials;
import com.nosoysun.vtmon.item.ModItemGroups;
import com.nosoysun.vtmon.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(VTMon.MOD_ID)
public class VTMon {
	public static final String MOD_ID = "vtmon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public VTMon(IEventBus modEventBus) {
		ModArmorMaterials.register(modEventBus);
		ModItems.register(modEventBus);
		ModItemGroups.register(modEventBus);

		modEventBus.addListener(this::modifyDefaultComponents);
	}

	private void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
		event.modify(Items.CLOCK, builder -> {
			builder.set(
					MegaShowdownDataComponents.REGISTRY_TYPE_COMPONENT.get(),
					"showdown_item"
			);

			builder.set(
					MegaShowdownDataComponents.RESOURCE_LOCATION_COMPONENT.get(),
					ResourceLocation.fromNamespaceAndPath(MOD_ID, "clock")
			);
		});
	}
}
