package com.nosoysun.vtmon.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.minecraft.registry.tag.BlockTags;

public class WitheredFarmlandBlock extends FarmlandBlock {
	public WitheredFarmlandBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!state.canPlaceAt(world, pos)) {
			turnToWitheredDirt(null, state, world, pos);
		}
	}

	@Override
	protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		int moisture = state.get(MOISTURE);
		if (isWaterNearby(world, pos) || world.hasRain(pos.up())) {
			if (moisture < MAX_MOISTURE) {
				world.setBlockState(pos, state.with(MOISTURE, MAX_MOISTURE), Block.NOTIFY_LISTENERS);
			}
		} else if (moisture > 0) {
			world.setBlockState(pos, state.with(MOISTURE, moisture - 1), Block.NOTIFY_LISTENERS);
		} else if (!world.getBlockState(pos.up()).isIn(BlockTags.MAINTAINS_FARMLAND)) {
			turnToWitheredDirt(null, state, world, pos);
		}
	}

	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if (!world.isClient
				&& world.random.nextFloat() < fallDistance - 0.5F
				&& entity instanceof LivingEntity
				&& (entity instanceof PlayerEntity || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING))
				&& entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F) {
			turnToWitheredDirt(entity, state, world, pos);
		}
		super.onLandedUpon(world, state, pos, entity, fallDistance);
	}

	private static boolean isWaterNearby(WorldView world, BlockPos pos) {
		for (BlockPos waterPos : BlockPos.iterate(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
			if (world.getFluidState(waterPos).isIn(FluidTags.WATER)) {
				return true;
			}
		}
		return false;
	}

	private static void turnToWitheredDirt(Entity entity, BlockState farmlandState, WorldAccess world, BlockPos pos) {
		BlockState dirtState = Block.pushEntitiesUpBeforeBlockChange(
				farmlandState,
				ModBlocks.WITHERED_DIRT.getDefaultState(),
				world,
				pos
		);
		world.setBlockState(pos, dirtState, Block.NOTIFY_ALL);
		if (world instanceof World serverWorld) {
			serverWorld.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(entity, dirtState));
		}
	}
}
