package com.nosoysun.vtmon;

import com.nosoysun.vtmon.item.ModBlocks;
import com.nosoysun.vtmon.world.InterworldPortalEntityRenderer;
import com.nosoysun.vtmon.world.ModEntities;
import com.nosoysun.vtmon.world.ModParticles;
import com.nosoysun.vtmon.world.OchreSmokeParticle;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public final class InterworldClient {
	private InterworldClient() {
	}

	public static void registerPortalRenderLayer() {
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INTERWORLD_PORTAL.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.DISTORTED_GLASS.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.DISTORTED_GLASS_PANE.get(), RenderType.translucent());
	}

	public static void registerPortalEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.INTERWORLD_PORTAL.get(), InterworldPortalEntityRenderer::new);
	}

	public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ModParticles.OCHRE_SMOKE.get(), OchreSmokeParticle.Provider::new);
	}
}
