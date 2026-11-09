package com.nosoysun.vtmon.mixin;

import com.nosoysun.vtmon.world.InterworldPortal;
import net.minecraft.entity.ItemEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
	@Inject(method = "tick", at = @At("TAIL"))
	private void vtmon$activateInterworldPortal(CallbackInfo callbackInfo) {
		ItemEntity item = (ItemEntity) (Object) this;
		if (item.getWorld() instanceof ServerWorld world) {
			InterworldPortal.onStarItemTick(item, world);
		}
	}
}
