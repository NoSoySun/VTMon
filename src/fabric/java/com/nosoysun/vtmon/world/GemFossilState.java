package com.nosoysun.vtmon.world;

import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.PersistentState;

public class GemFossilState extends PersistentState {
	public static final String KEY = "vtmon_gem_fossil";
	public static final Type<GemFossilState> TYPE = new Type<>(
			GemFossilState::new,
			GemFossilState::fromNbt,
			DataFixTypes.SAVED_DATA_MAP_DATA
	);

	public boolean gemFossilSpawned;

	@Override
	public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
		nbt.putBoolean("gem_fossil_spawned", gemFossilSpawned);
		return nbt;
	}

	public static GemFossilState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
		GemFossilState state = new GemFossilState();
		state.gemFossilSpawned = nbt.getBoolean("gem_fossil_spawned");
		return state;
	}
}
