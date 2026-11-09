package com.nosoysun.vtmon.world;

import com.nosoysun.vtmon.VTMon;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModParticles {
	private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
			DeferredRegister.create(Registries.PARTICLE_TYPE, VTMon.MOD_ID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> OCHRE_SMOKE =
			PARTICLE_TYPES.register("ochre_smoke", () -> new SimpleParticleType(false));

	private ModParticles() {
	}

	public static void register(IEventBus modEventBus) {
		PARTICLE_TYPES.register(modEventBus);
	}
}
