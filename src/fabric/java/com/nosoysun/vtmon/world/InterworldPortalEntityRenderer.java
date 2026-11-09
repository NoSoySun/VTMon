package com.nosoysun.vtmon.world;

import com.nosoysun.vtmon.item.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.Identifier;

public final class InterworldPortalEntityRenderer extends EntityRenderer<InterworldPortalEntity> {
	public InterworldPortalEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
		shadowRadius = 0.0F;
	}

	@Override
	public void render(InterworldPortalEntity entity, float entityYaw, float tickDelta, MatrixStack matrices,
			VertexConsumerProvider vertexConsumers, int light) {
		matrices.push();
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getYaw()));
		BlockRenderManager blockRenderer = MinecraftClient.getInstance().getBlockRenderManager();
		BlockState baseState = ModBlocks.INTERWORLD_PORTAL.getDefaultState()
				.with(NetherPortalBlock.AXIS, Direction.Axis.X);

		for (int x = -1; x <= 0; x++) {
			for (int y = 0; y < 3; y++) {
				int segment = (2 - y) * 2 + (x + 1);
				BlockState portalState = baseState.with(InterworldPortalBlock.SEGMENT, segment);
				matrices.push();
				matrices.translate(x, y, -0.5);
				blockRenderer.renderBlockAsEntity(portalState, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV);
				matrices.pop();
			}
		}

		matrices.pop();
		super.render(entity, entityYaw, tickDelta, matrices, vertexConsumers, light);
	}

	@Override
	public Identifier getTexture(InterworldPortalEntity entity) {
		return Identifier.of("vtmon", "textures/block/interworld_wormhole_0.png");
	}
}
