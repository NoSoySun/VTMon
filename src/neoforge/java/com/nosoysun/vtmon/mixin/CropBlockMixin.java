package com.nosoysun.vtmon.mixin;

import com.nosoysun.vtmon.item.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin {
	@Inject(method = "getGrowthSpeed", at = @At("RETURN"), cancellable = true)
	private static void vtmon$boostWitheredFarmlandGrowth(BlockState crop, BlockGetter world, BlockPos cropPos,
			CallbackInfoReturnable<Float> callback) {
		if (world.getBlockState(cropPos.below()).is(ModBlocks.WITHERED_FARMLAND.get())) {
			callback.setReturnValue(callback.getReturnValue() * 1.75F);
		}
	}
}
