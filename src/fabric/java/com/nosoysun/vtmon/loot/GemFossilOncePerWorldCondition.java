package com.nosoysun.vtmon.loot;

import com.mojang.serialization.MapCodec;
import com.nosoysun.vtmon.world.GemFossilState;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class GemFossilOncePerWorldCondition implements LootCondition {
	public static final Identifier ID = Identifier.of("vtmon", "gem_fossil_once_per_world");
	public static final GemFossilOncePerWorldCondition INSTANCE = new GemFossilOncePerWorldCondition();
	public static final LootConditionType TYPE = new LootConditionType(MapCodec.unit(INSTANCE));

	private GemFossilOncePerWorldCondition() {
	}

	@Override
	public LootConditionType getType() {
		return TYPE;
	}

	@Override
	public boolean test(LootContext context) {
		ServerWorld world = context.getWorld();
		if (world == null || world.getServer() == null) {
			return false;
		}
		ServerWorld overworld = world.getServer().getOverworld();
		if (overworld == null) {
			return false;
		}
		GemFossilState state = overworld.getPersistentStateManager().getOrCreate(GemFossilState.TYPE, GemFossilState.KEY);
		if (state.gemFossilSpawned) {
			return false;
		}
		state.gemFossilSpawned = true;
		state.markDirty();
		return true;
	}
}
