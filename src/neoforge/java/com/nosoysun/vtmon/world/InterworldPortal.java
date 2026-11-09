package com.nosoysun.vtmon.world;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public final class InterworldPortal {
	private InterworldPortal() {
	}

	public static void onStarItemTick(ItemEntity item, ServerLevel level) {
		if (!Level.OVERWORLD.equals(level.dimension()) || !item.getItem().is(Items.NETHER_STAR)) {
			return;
		}

		BlockPos portalBase = findSoulFire(level, item.blockPosition());
		if (portalBase == null) {
			return;
		}

		Vec3 velocity = item.getDeltaMovement();
		float yaw = velocity.horizontalDistanceSqr() < 0.0001
				? item.getYRot()
				: (float) Math.toDegrees(Math.atan2(velocity.x, velocity.z));
		InterworldPortalEntity portal = new InterworldPortalEntity(ModEntities.INTERWORLD_PORTAL.get(), level);
		portal.moveTo(portalBase.getX() + 0.5, portalBase.getY(), portalBase.getZ() + 0.5, yaw, 0.0F);

		if (level.addFreshEntity(portal)) {
			ItemStack offering = item.getItem();
			offering.shrink(1);
			if (offering.isEmpty()) {
				item.discard();
			} else {
				item.setItem(offering);
			}
		}
	}

	private static BlockPos findSoulFire(Level level, BlockPos droppedAt) {
		for (int yOffset = -1; yOffset <= 0; yOffset++) {
			BlockPos candidate = droppedAt.offset(0, yOffset, 0);
			BlockState state = level.getBlockState(candidate);
			if (state.is(Blocks.SOUL_FIRE) && level.getBlockState(candidate.below()).is(Blocks.SOUL_SAND)) {
				return candidate;
			}
			if (state.is(Blocks.SOUL_CAMPFIRE) && state.getValue(CampfireBlock.LIT)) {
				return candidate.above();
			}
		}
		return null;
	}
}
