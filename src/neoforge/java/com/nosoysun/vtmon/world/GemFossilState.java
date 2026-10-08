package com.nosoysun.vtmon.world;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;

public class GemFossilState extends SavedData {
	public static final String KEY = "vtmon_gem_fossil";
	public static final Factory<GemFossilState> FACTORY = new Factory<>(
			GemFossilState::new,
			GemFossilState::load,
			DataFixTypes.SAVED_DATA_MAP_DATA
	);

	public boolean gemFossilSpawned;

	@Override
	public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
		tag.putBoolean("gem_fossil_spawned", gemFossilSpawned);
		return tag;
	}

	public static GemFossilState load(CompoundTag tag, HolderLookup.Provider registries) {
		GemFossilState state = new GemFossilState();
		state.gemFossilSpawned = tag.getBoolean("gem_fossil_spawned");
		return state;
	}
}
