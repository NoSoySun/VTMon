package com.nosoysun.vtmon.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class WitheredDirtBlock extends Block {
	public WitheredDirtBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected ItemInteractionResult useItemOn(
			ItemStack stack,
			BlockState state,
			Level level,
			BlockPos pos,
			Player player,
			InteractionHand hand,
			BlockHitResult hitResult
	) {
		if (stack.getItem() instanceof HoeItem && hitResult.getDirection() != Direction.DOWN
				&& level.getBlockState(pos.above()).isAir()) {
			if (!level.isClientSide) {
				level.setBlock(pos, ModBlocks.WITHERED_FARMLAND.get().defaultBlockState(), Block.UPDATE_ALL);
				level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
			}
			return ItemInteractionResult.sidedSuccess(level.isClientSide);
		}

		if (!isWaterBottle(stack)) {
			return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
		}

		if (!level.isClientSide) {
			level.setBlock(pos, ModBlocks.WITHERED_MUD.get().defaultBlockState(), Block.UPDATE_ALL);
			level.playSound(null, pos, SoundEvents.MUD_PLACE, SoundSource.BLOCKS, 1.0F, 0.9F);
			if (!player.isCreative()) {
				stack.shrink(1);
				ItemStack glassBottle = new ItemStack(Items.GLASS_BOTTLE);
				if (!player.addItem(glassBottle)) {
					player.drop(glassBottle, false);
				}
			}
		}

		return ItemInteractionResult.sidedSuccess(level.isClientSide);
	}

	private static boolean isWaterBottle(ItemStack stack) {
		if (!stack.is(Items.POTION)) {
			return false;
		}

		PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
		return contents != null && contents.is(Potions.WATER);
	}
}
