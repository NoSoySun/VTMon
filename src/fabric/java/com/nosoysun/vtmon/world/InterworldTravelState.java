package com.nosoysun.vtmon.world;

import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class InterworldTravelState extends PersistentState {
	public static final String KEY = "vtmon_interworld_travel";
	public static final Type<InterworldTravelState> TYPE = new Type<>(
			InterworldTravelState::new,
			InterworldTravelState::fromNbt,
			DataFixTypes.SAVED_DATA_MAP_DATA
	);

	private final Map<UUID, PlayerRecord> players = new HashMap<>();

	public static InterworldTravelState get(ServerWorld overworld) {
		return overworld.getPersistentStateManager().getOrCreate(TYPE, KEY);
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
		markDirty();
	}

	public void clearTrip(UUID playerId) {
		PlayerRecord record = players.get(playerId);
		if (record != null && record.enteredAt >= 0) {
			record.enteredAt = -1;
			removeIfEmpty(playerId, record);
			markDirty();
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
		markDirty();
	}

	public void clearOverworldRespawn(UUID playerId) {
		PlayerRecord record = players.get(playerId);
		if (record != null && record.hasOverworldRespawn) {
			record.hasOverworldRespawn = false;
			removeIfEmpty(playerId, record);
			markDirty();
		}
	}

	@Override
	public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
		NbtCompound serializedPlayers = new NbtCompound();
		for (Map.Entry<UUID, PlayerRecord> entry : players.entrySet()) {
			PlayerRecord record = entry.getValue();
			NbtCompound playerNbt = new NbtCompound();
			playerNbt.putLong("entered_at", record.enteredAt);
			playerNbt.putBoolean("has_overworld_respawn", record.hasOverworldRespawn);
			if (record.hasOverworldRespawn) {
				playerNbt.putInt("spawn_x", record.spawnX);
				playerNbt.putInt("spawn_y", record.spawnY);
				playerNbt.putInt("spawn_z", record.spawnZ);
				playerNbt.putFloat("spawn_angle", record.spawnAngle);
			}
			serializedPlayers.put(entry.getKey().toString(), playerNbt);
		}
		nbt.put("players", serializedPlayers);
		return nbt;
	}

	public static InterworldTravelState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
		InterworldTravelState state = new InterworldTravelState();
		NbtCompound serializedPlayers = nbt.getCompound("players");
		for (String key : serializedPlayers.getKeys()) {
			try {
				UUID playerId = UUID.fromString(key);
				NbtCompound playerNbt = serializedPlayers.getCompound(key);
				PlayerRecord record = new PlayerRecord();
				record.enteredAt = playerNbt.getLong("entered_at");
				record.hasOverworldRespawn = playerNbt.getBoolean("has_overworld_respawn");
				if (record.hasOverworldRespawn) {
					record.spawnX = playerNbt.getInt("spawn_x");
					record.spawnY = playerNbt.getInt("spawn_y");
					record.spawnZ = playerNbt.getInt("spawn_z");
					record.spawnAngle = playerNbt.getFloat("spawn_angle");
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
