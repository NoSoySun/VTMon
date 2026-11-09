package com.nosoysun.vtmon.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class InterworldTravelState extends SavedData {
	public static final String KEY = "vtmon_interworld_travel";
	public static final Factory<InterworldTravelState> FACTORY = new Factory<>(
			InterworldTravelState::new,
			InterworldTravelState::load,
			DataFixTypes.SAVED_DATA_MAP_DATA
	);

	private final Map<UUID, PlayerRecord> players = new HashMap<>();

	public static InterworldTravelState get(ServerLevel overworld) {
		return overworld.getDataStorage().computeIfAbsent(FACTORY, KEY);
	}

	public boolean hasTrip(UUID playerId) {
		PlayerRecord record = players.get(playerId);
		return record != null && record.enteredAt >= 0;
	}

	public long getEnteredAt(UUID playerId) {
		PlayerRecord record = players.get(playerId);
		return record == null ? -1 : record.enteredAt;
	}

	public void beginTrip(UUID playerId, long gameTime) {
		PlayerRecord record = players.computeIfAbsent(playerId, ignored -> new PlayerRecord());
		record.enteredAt = gameTime;
		setDirty();
	}

	public void clearTrip(UUID playerId) {
		PlayerRecord record = players.get(playerId);
		if (record != null && record.enteredAt >= 0) {
			record.enteredAt = -1;
			removeIfEmpty(playerId, record);
			setDirty();
		}
	}

	public boolean hasOverworldRespawn(UUID playerId) {
		PlayerRecord record = players.get(playerId);
		return record != null && record.hasOverworldRespawn;
	}

	public BlockPos getOverworldRespawn(UUID playerId) {
		PlayerRecord record = players.get(playerId);
		return record == null || !record.hasOverworldRespawn
				? null
				: new BlockPos(record.spawnX, record.spawnY, record.spawnZ);
	}

	public float getOverworldRespawnAngle(UUID playerId) {
		PlayerRecord record = players.get(playerId);
		return record == null ? 0.0F : record.spawnAngle;
	}

	public void setOverworldRespawn(UUID playerId, BlockPos pos, float angle) {
		PlayerRecord record = players.computeIfAbsent(playerId, ignored -> new PlayerRecord());
		record.hasOverworldRespawn = true;
		record.spawnX = pos.getX();
		record.spawnY = pos.getY();
		record.spawnZ = pos.getZ();
		record.spawnAngle = angle;
		setDirty();
	}

	public void clearOverworldRespawn(UUID playerId) {
		PlayerRecord record = players.get(playerId);
		if (record != null && record.hasOverworldRespawn) {
			record.hasOverworldRespawn = false;
			removeIfEmpty(playerId, record);
			setDirty();
		}
	}

	public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
		CompoundTag serializedPlayers = new CompoundTag();
		for (Map.Entry<UUID, PlayerRecord> entry : players.entrySet()) {
			PlayerRecord record = entry.getValue();
			CompoundTag playerTag = new CompoundTag();
			playerTag.putLong("entered_at", record.enteredAt);
			playerTag.putBoolean("has_overworld_respawn", record.hasOverworldRespawn);
			if (record.hasOverworldRespawn) {
				playerTag.putInt("spawn_x", record.spawnX);
				playerTag.putInt("spawn_y", record.spawnY);
				playerTag.putInt("spawn_z", record.spawnZ);
				playerTag.putFloat("spawn_angle", record.spawnAngle);
			}
			serializedPlayers.put(entry.getKey().toString(), playerTag);
		}
		tag.put("players", serializedPlayers);
		return tag;
	}

	public static InterworldTravelState load(CompoundTag tag, HolderLookup.Provider registries) {
		InterworldTravelState state = new InterworldTravelState();
		CompoundTag serializedPlayers = tag.getCompound("players");
		for (String key : serializedPlayers.getAllKeys()) {
			try {
				UUID playerId = UUID.fromString(key);
				CompoundTag playerTag = serializedPlayers.getCompound(key);
				PlayerRecord record = new PlayerRecord();
				record.enteredAt = playerTag.getLong("entered_at");
				record.hasOverworldRespawn = playerTag.getBoolean("has_overworld_respawn");
				if (record.hasOverworldRespawn) {
					record.spawnX = playerTag.getInt("spawn_x");
					record.spawnY = playerTag.getInt("spawn_y");
					record.spawnZ = playerTag.getInt("spawn_z");
					record.spawnAngle = playerTag.getFloat("spawn_angle");
				}
				if (record.enteredAt >= 0 || record.hasOverworldRespawn) {
					state.players.put(playerId, record);
				}
			} catch (IllegalArgumentException ignored) {
				// Ignore malformed player keys so one bad entry cannot invalidate all travel data.
			}
		}
		return state;
	}

	private void removeIfEmpty(UUID playerId, PlayerRecord record) {
		if (record.enteredAt < 0 && !record.hasOverworldRespawn) {
			players.remove(playerId);
		}
	}

	private static final class PlayerRecord {
		private long enteredAt = -1;
		private boolean hasOverworldRespawn;
		private int spawnX;
		private int spawnY;
		private int spawnZ;
		private float spawnAngle;
	}
}
