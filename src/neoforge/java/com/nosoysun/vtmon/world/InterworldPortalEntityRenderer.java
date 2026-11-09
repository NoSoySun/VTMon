package com.nosoysun.vtmon.world;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nosoysun.vtmon.item.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;

public final class InterworldPortalEntityRenderer extends EntityRenderer<InterworldPortalEntity> {
	public InterworldPortalEntityRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.shadowRadius = 0.0F;
	}

	@Override
	public void render(InterworldPortalEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
			MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(entity.getYRot()));
		BlockState baseState = ModBlocks.INTERWORLD_PORTAL.get().defaultBlockState()
				.setValue(NetherPortalBlock.AXIS, Direction.Axis.X);
		for (int x = -1; x <= 0; x++) {
			for (int y = 0; y < 3; y++) {
				int segment = (2 - y) * 2 + (x + 1);
				BlockState portalState = baseState.setValue(InterworldPortalBlock.SEGMENT, segment);
				poseStack.pushPose();
				poseStack.translate(x, y, -0.5);
				Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
						portalState, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
				poseStack.popPose();
			}
		}
		poseStack.popPose();
		super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(InterworldPortalEntity entity) {
		return ResourceLocation.fromNamespaceAndPath("vtmon", "textures/block/interworld_wormhole_0.png");
	}
}
