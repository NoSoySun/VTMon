package com.nosoysun.vtmon.item;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

public class WitheredDirtBlock extends Block {
	public WitheredDirtBlock(AbstractBlock.Settings settings) {
		super(settings);
	}

	@Override
	protected ItemActionResult onUseWithItem(
			ItemStack stack,
			BlockState state,
			World world,
			BlockPos pos,
			PlayerEntity player,
			Hand hand,
			BlockHitResult hit
	) {
		if (stack.getItem() instanceof HoeItem && hit.getSide() != Direction.DOWN
				&& world.getBlockState(pos.up()).isAir()) {
			if (!world.isClient) {
				world.setBlockState(pos, ModBlocks.WITHERED_FARMLAND.getDefaultState(), Block.NOTIFY_ALL);
				world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				stack.damage(1, player, LivingEntity.getSlotForHand(hand));
			}
			return ItemActionResult.success(world.isClient);
		}

		if (!isWaterBottle(stack)) {
			return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
		}

		if (!world.isClient) {
			world.setBlockState(pos, ModBlocks.WITHERED_MUD.getDefaultState(), Block.NOTIFY_ALL);
			world.playSound(null, pos, SoundEvents.BLOCK_MUD_PLACE, SoundCategory.BLOCKS, 1.0F, 0.9F);
			if (!player.isCreative()) {
				stack.decrement(1);
				ItemStack glassBottle = new ItemStack(Items.GLASS_BOTTLE);
				if (!player.giveItemStack(glassBottle)) {
					player.dropItem(glassBottle, false);
				}
			}
		}

		return ItemActionResult.success(world.isClient);
	}

	private static boolean isWaterBottle(ItemStack stack) {
		if (!stack.isOf(Items.POTION)) {
			return false;
		}

		PotionContentsComponent contents = stack.get(DataComponentTypes.POTION_CONTENTS);
		return contents != null && contents.matches(Potions.WATER);
	}
}
