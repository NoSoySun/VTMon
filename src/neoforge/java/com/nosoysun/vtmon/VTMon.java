package com.nosoysun.vtmon;

import com.github.yajatkaul.mega_showdown.components.MegaShowdownDataComponents;
import com.nosoysun.vtmon.item.ModArmorMaterials;
import com.nosoysun.vtmon.item.ModBlocks;
import com.nosoysun.vtmon.item.ModItemGroups;
import com.nosoysun.vtmon.item.ModItems;
import com.nosoysun.vtmon.loot.GemFossilOncePerWorldCondition;
import com.nosoysun.vtmon.world.InterworldTravel;
import com.nosoysun.vtmon.world.ModEntities;
import com.nosoysun.vtmon.world.ModParticles;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(VTMon.MOD_ID)
public class VTMon {
	public static final String MOD_ID = "vtmon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public VTMon(IEventBus modEventBus) {
		ModArmorMaterials.register(modEventBus);
		ModBlocks.register(modEventBus);
		ModItems.register(modEventBus);
		ModItemGroups.register(modEventBus);
		ModEntities.register(modEventBus);
		ModParticles.register(modEventBus);
		NeoForge.EVENT_BUS.addListener((ServerTickEvent.Post event) -> InterworldTravel.tick(event.getServer()));
		modEventBus.addListener(this::clientSetup);
		if (FMLEnvironment.dist == Dist.CLIENT) {
			modEventBus.addListener(InterworldClient::registerPortalEntityRenderer);
			modEventBus.addListener(InterworldClient::registerParticleProviders);
		}

		Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, GemFossilOncePerWorldCondition.ID, GemFossilOncePerWorldCondition.TYPE);

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

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(com.nosoysun.vtmon.InterworldClient::registerPortalRenderLayer);
	}
}
