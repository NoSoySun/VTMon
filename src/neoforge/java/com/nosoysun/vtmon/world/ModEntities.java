package com.nosoysun.vtmon.world;

import com.nosoysun.vtmon.VTMon;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEntities {
	private static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
			DeferredRegister.create(Registries.ENTITY_TYPE, VTMon.MOD_ID);

	public static final DeferredHolder<EntityType<?>, EntityType<InterworldPortalEntity>> INTERWORLD_PORTAL =
			ENTITY_TYPES.register("interworld_portal_entity", () -> EntityType.Builder
					.of(InterworldPortalEntity::new, MobCategory.MISC)
					.sized(2.0F, 3.0F)
					.clientTrackingRange(64)
					.updateInterval(1)
					.build("vtmon:interworld_portal_entity"));

	private ModEntities() {
	}

	public static void register(IEventBus modEventBus) {
		ENTITY_TYPES.register(modEventBus);
		VTMon.LOGGER.info("Registering portal entity for {}", VTMon.MOD_ID);
	}
}
