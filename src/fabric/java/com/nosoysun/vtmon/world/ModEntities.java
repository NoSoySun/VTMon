package com.nosoysun.vtmon.world;

import com.nosoysun.vtmon.VTMon;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModEntities {
	public static final EntityType<InterworldPortalEntity> INTERWORLD_PORTAL = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(VTMon.MOD_ID, "interworld_portal_entity"),
			EntityType.Builder.create(InterworldPortalEntity::new, SpawnGroup.MISC)
					.dimensions(2.0F, 3.0F)
					.maxTrackingRange(64)
					.trackingTickInterval(1)
					.build("vtmon:interworld_portal_entity")
	);

	private ModEntities() {
	}

	public static void register() {
		VTMon.LOGGER.info("Registering portal entity for {}", VTMon.MOD_ID);
	}
}
