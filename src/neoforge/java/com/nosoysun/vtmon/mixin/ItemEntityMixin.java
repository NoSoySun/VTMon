package com.nosoysun.vtmon.mixin;

import com.nosoysun.vtmon.world.InterworldPortal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
	@Inject(method = "tick", at = @At("TAIL"))
	private void vtmon$activateInterworldPortal(CallbackInfo callbackInfo) {
		ItemEntity item = (ItemEntity) (Object) this;
		if (item.level() instanceof ServerLevel level) {
			InterworldPortal.onStarItemTick(item, level);
		}
	}
}
