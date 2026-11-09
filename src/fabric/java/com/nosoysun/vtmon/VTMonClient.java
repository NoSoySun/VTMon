package com.nosoysun.vtmon;

import com.nosoysun.vtmon.item.ModBlocks;
import com.nosoysun.vtmon.world.InterworldPortalEntityRenderer;
import com.nosoysun.vtmon.world.ModEntities;
import com.nosoysun.vtmon.world.ModParticles;
import com.nosoysun.vtmon.world.OchreSmokeParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.render.RenderLayer;

public class VTMonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INTERWORLD_PORTAL, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DISTORTED_GLASS, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DISTORTED_GLASS_PANE, RenderLayer.getTranslucent());
		EntityRendererRegistry.register(ModEntities.INTERWORLD_PORTAL, InterworldPortalEntityRenderer::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.OCHRE_SMOKE, OchreSmokeParticle.Factory::new);
    }
}
