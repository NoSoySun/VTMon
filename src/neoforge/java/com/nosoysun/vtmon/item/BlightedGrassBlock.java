package com.nosoysun.vtmon.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class BlightedGrassBlock extends Block {
	public BlightedGrassBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
			LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP && neighborState.isCollisionShapeFullBlock(level, neighborPos)) {
			return ModBlocks.WITHERED_DIRT.get().defaultBlockState();
		}
		return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}
}
