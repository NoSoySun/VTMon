package com.nosoysun.vtmon.mixin;

import com.nosoysun.vtmon.item.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin {
	@Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
	private void vtmon$allowWitheredFarmland(BlockState soil, BlockView world, BlockPos pos,
			CallbackInfoReturnable<Boolean> callback) {
		if (soil.isOf(ModBlocks.WITHERED_FARMLAND)) {
			callback.setReturnValue(true);
		}
	}

	@Redirect(
			method = "getAvailableMoisture",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z",
					ordinal = 0
			)
	)
	private static boolean vtmon$recognizeWitheredFarmland(BlockState state, Block block) {
		return state.isOf(block) || state.isOf(ModBlocks.WITHERED_FARMLAND);
	}

	@Inject(method = "getAvailableMoisture", at = @At("RETURN"), cancellable = true)
	private static void vtmon$boostWitheredFarmlandGrowth(Block crop, BlockView world, BlockPos cropPos,
			CallbackInfoReturnable<Float> callback) {
		if (world.getBlockState(cropPos.down()).isOf(ModBlocks.WITHERED_FARMLAND)) {
			callback.setReturnValue(callback.getReturnValue() * 1.75F);
		}
	}
}
