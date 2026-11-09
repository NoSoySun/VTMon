package com.nosoysun.vtmon.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public final class InterworldPortal {
	private InterworldPortal() {
	}

	public static void onStarItemTick(ItemEntity item, ServerWorld world) {
		if (!World.OVERWORLD.equals(world.getRegistryKey()) || !item.getStack().isOf(Items.NETHER_STAR)) {
			return;
		}

		BlockPos portalBase = findSoulFire(world, item.getBlockPos());
		if (portalBase == null) {
			return;
		}

		Vec3d velocity = item.getVelocity();
		float yaw = velocity.horizontalLengthSquared() < 0.0001
				? item.getYaw()
				: (float) Math.toDegrees(Math.atan2(velocity.x, velocity.z));
		InterworldPortalEntity portal = new InterworldPortalEntity(ModEntities.INTERWORLD_PORTAL, world);
		portal.refreshPositionAndAngles(portalBase.getX() + 0.5, portalBase.getY(), portalBase.getZ() + 0.5, yaw, 0.0F);

		if (world.spawnEntity(portal)) {
			ItemStack offering = item.getStack();
			offering.decrement(1);
			if (offering.isEmpty()) {
				item.discard();
			} else {
				item.setStack(offering);
			}
		}
	}

	private static BlockPos findSoulFire(World world, BlockPos droppedAt) {
		for (int yOffset = -1; yOffset <= 0; yOffset++) {
			BlockPos candidate = droppedAt.add(0, yOffset, 0);
			BlockState state = world.getBlockState(candidate);
			if (state.isOf(Blocks.SOUL_FIRE) && world.getBlockState(candidate.down()).isOf(Blocks.SOUL_SAND)) {
				return candidate;
			}
			if (state.isOf(Blocks.SOUL_CAMPFIRE) && state.get(CampfireBlock.LIT)) {
				return candidate.up();
			}
		}
		return null;
	}
}
