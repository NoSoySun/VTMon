package com.nosoysun.vtmon.loot;

import com.mojang.serialization.MapCodec;
import com.nosoysun.vtmon.world.GemFossilState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class GemFossilOncePerWorldCondition implements LootItemCondition {
	public static final ResourceLocation ID = ResourceLocation.parse("vtmon:gem_fossil_once_per_world");
	public static final GemFossilOncePerWorldCondition INSTANCE = new GemFossilOncePerWorldCondition();
	public static final LootItemConditionType TYPE = new LootItemConditionType(MapCodec.unit(INSTANCE));

	private GemFossilOncePerWorldCondition() {
	}

	@Override
	public LootItemConditionType getType() {
		return TYPE;
	}

	@Override
	public boolean test(LootContext context) {
		if (!(context.getLevel() instanceof ServerLevel level) || level.getServer() == null) {
			return false;
		}
		ServerLevel overworld = level.getServer().overworld();
		if (overworld == null) {
			return false;
		}
		GemFossilState state = overworld.getDataStorage().computeIfAbsent(GemFossilState.FACTORY, GemFossilState.KEY);
		if (state.gemFossilSpawned) {
			return false;
		}
		state.gemFossilSpawned = true;
		state.setDirty();
		return true;
	}
}
