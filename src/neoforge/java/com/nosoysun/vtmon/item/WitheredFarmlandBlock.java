package com.nosoysun.vtmon.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.CommonHooks;

public class WitheredFarmlandBlock extends FarmBlock {
	public WitheredFarmlandBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!state.canSurvive(level, pos)) {
			turnToWitheredDirt(null, state, level, pos);
		}
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int moisture = state.getValue(MOISTURE);
		if (isWaterNearby(level, pos) || level.isRainingAt(pos.above())) {
			if (moisture < MAX_MOISTURE) {
				level.setBlock(pos, state.setValue(MOISTURE, MAX_MOISTURE), Block.UPDATE_CLIENTS);
			}
		} else if (moisture > 0) {
			level.setBlock(pos, state.setValue(MOISTURE, moisture - 1), Block.UPDATE_CLIENTS);
		} else if (!level.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND)) {
			turnToWitheredDirt(null, state, level, pos);
		}
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if (!level.isClientSide
				&& CommonHooks.onFarmlandTrample(
						level,
						pos,
						ModBlocks.WITHERED_DIRT.get().defaultBlockState(),
						fallDistance,
						entity
				)) {
			turnToWitheredDirt(entity, state, level, pos);
		}
		entity.causeFallDamage(fallDistance, 1.0F, entity.damageSources().fall());
	}

	private static boolean isWaterNearby(LevelReader level, BlockPos pos) {
		for (BlockPos waterPos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
			if (level.getFluidState(waterPos).is(FluidTags.WATER)) {
				return true;
			}
		}
		return false;
	}

	private static void turnToWitheredDirt(Entity entity, BlockState farmlandState, LevelAccessor level, BlockPos pos) {
		BlockState dirtState = Block.pushEntitiesUp(
				farmlandState,
				ModBlocks.WITHERED_DIRT.get().defaultBlockState(),
				level,
				pos
		);
		level.setBlock(pos, dirtState, Block.UPDATE_ALL);
		if (level instanceof Level serverLevel) {
			serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, dirtState));
		}
	}
}
