package com.nosoysun.vtmon.world;

import com.nosoysun.vtmon.VTMon;
import com.nosoysun.vtmon.item.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Set;
import java.util.UUID;

public final class InterworldTravel {
	public static final ResourceKey<Level> DIMENSION = ResourceKey.create(
			Registries.DIMENSION,
			ResourceLocation.fromNamespaceAndPath("vtmon", "interworld")
	);
	private static final long MAX_STAY_TICKS = 30L * 60L * 20L;

	private InterworldTravel() {
	}

	public static void enter(ServerPlayer player) {
		if (!Level.OVERWORLD.equals(player.level().dimension())) {
			return;
		}
		MinecraftServer server = player.getServer();
		if (server == null) {
			return;
		}
		ServerLevel overworld = server.overworld();
		ServerLevel destination = server.getLevel(DIMENSION);
		if (destination == null) {
			return;
		}

		InterworldTravelState data = InterworldTravelState.get(overworld);
		UUID playerId = player.getUUID();
		rememberOverworldRespawn(player, data);
		if (!data.hasTrip(playerId)) {
			data.beginTrip(playerId, overworld.getGameTime());
		}

		BlockPos anchor = getActiveAnchor(player, destination);
		BlockPos safeFeet = anchor == null ? null : getSafeAnchorPosition(destination, anchor);
		if (safeFeet == null) {
			safeFeet = findSafeSurface(destination, anchor == null ? 0 : anchor.getX(), anchor == null ? 0 : anchor.getZ());
		}
		if (safeFeet == null) {
			safeFeet = createEmergencyPlatform(destination);
			VTMon.LOGGER.warn("No safe natural surface found in the Interworld; created an emergency arrival platform at {}", safeFeet);
		}

		player.teleportTo(destination, safeFeet.getX() + 0.5, safeFeet.getY(), safeFeet.getZ() + 0.5,
				Set.of(), player.getYRot(), player.getXRot());
		player.sendSystemMessage(Component.translatable("message.vtmon.interworld.entered"));
	}

	public static void tick(MinecraftServer server) {
		ServerLevel overworld = server.overworld();
		InterworldTravelState data = InterworldTravelState.get(overworld);
		long now = overworld.getGameTime();

		for (ServerPlayer player : server.getPlayerList().getPlayers()) {
			UUID playerId = player.getUUID();
			if (!DIMENSION.equals(player.level().dimension())) {
				data.clearTrip(playerId);
				continue;
			}

			if (!data.hasTrip(playerId)) {
				rememberOverworldRespawn(player, data);
				data.beginTrip(playerId, now);
				player.sendSystemMessage(Component.translatable("message.vtmon.interworld.entered"));
			}

			long enteredAt = data.getEnteredAt(playerId);
			if (now < enteredAt) {
				data.beginTrip(playerId, now);
				continue;
			}
			if (now - enteredAt >= MAX_STAY_TICKS) {
				returnToOverworld(player, overworld, data);
				data.clearTrip(playerId);
			}
		}
	}

	private static void rememberOverworldRespawn(ServerPlayer player, InterworldTravelState data) {
		UUID playerId = player.getUUID();
		BlockPos spawn = player.getRespawnPosition();
		if (spawn != null && Level.OVERWORLD.equals(player.getRespawnDimension())) {
			data.setOverworldRespawn(playerId, spawn, player.getRespawnAngle());
		} else if (!data.hasOverworldRespawn(playerId)) {
			data.clearOverworldRespawn(playerId);
		}
	}

	private static BlockPos getActiveAnchor(ServerPlayer player, ServerLevel destination) {
		BlockPos spawn = player.getRespawnPosition();
		if (spawn == null || !DIMENSION.equals(player.getRespawnDimension())) {
			return null;
		}
		var state = destination.getBlockState(spawn);
		return state.is(Blocks.RESPAWN_ANCHOR)
				&& state.getValue(RespawnAnchorBlock.CHARGE) > 0
			? spawn
			: null;
	}

	private static void returnToOverworld(ServerPlayer player, ServerLevel overworld, InterworldTravelState data) {
		UUID playerId = player.getUUID();
		BlockPos personalSpawn = data.getOverworldRespawn(playerId);
		double x;
		double y;
		double z;
		float yaw;
		if (personalSpawn != null) {
			x = personalSpawn.getX() + 0.5;
			y = safeFeetY(overworld, personalSpawn.getX(), personalSpawn.getY() + 1, personalSpawn.getZ());
			z = personalSpawn.getZ() + 0.5;
			yaw = data.getOverworldRespawnAngle(playerId);
		} else {
			BlockPos worldSpawn = overworld.getSharedSpawnPos();
			x = worldSpawn.getX() + 0.5;
			y = overworld.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, worldSpawn.getX(), worldSpawn.getZ());
			z = worldSpawn.getZ() + 0.5;
			yaw = overworld.getSharedSpawnAngle();
		}

		player.teleportTo(overworld, x, y, z, Set.of(), yaw, 0.0F);
		player.sendSystemMessage(Component.translatable("message.vtmon.interworld.timeout"));
	}

	private static BlockPos getSafeAnchorPosition(ServerLevel level, BlockPos anchor) {
		BlockPos feet = anchor.above();
		return isSafeFeetPosition(level, feet) ? feet : null;
	}

	private static BlockPos findSafeSurface(ServerLevel level, int centerX, int centerZ) {
		int centerChunkX = centerX >> 4;
		int centerChunkZ = centerZ >> 4;
		int[][] localOffsets = {
				{centerX & 15, centerZ & 15}, {8, 8}, {4, 4}, {12, 4}, {4, 12}, {12, 12}
		};

		for (int radius = 0; radius <= 4; radius++) {
			for (int chunkOffsetX = -radius; chunkOffsetX <= radius; chunkOffsetX++) {
				for (int chunkOffsetZ = -radius; chunkOffsetZ <= radius; chunkOffsetZ++) {
					if (radius > 0 && Math.abs(chunkOffsetX) != radius && Math.abs(chunkOffsetZ) != radius) {
						continue;
					}

					int chunkX = centerChunkX + chunkOffsetX;
					int chunkZ = centerChunkZ + chunkOffsetZ;
					level.getChunk(chunkX, chunkZ);
					for (int[] offset : localOffsets) {
						int x = (chunkX << 4) + offset[0];
						int z = (chunkZ << 4) + offset[1];
						int feetY = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
						if (feetY <= level.getMinBuildHeight() || feetY >= level.getMaxBuildHeight() - 1) {
							continue;
						}
						BlockPos feet = new BlockPos(x, feetY, z);
						if (isSafeFeetPosition(level, feet)) {
							return feet;
						}
					}
				}
			}
		}
		return null;
	}

	private static boolean isSafeFeetPosition(ServerLevel level, BlockPos feet) {
		BlockPos head = feet.above();
		BlockPos floor = feet.below();
		var feetState = level.getBlockState(feet);
		var headState = level.getBlockState(head);
		var floorState = level.getBlockState(floor);
		return level.getWorldBorder().isWithinBounds(feet)
				&& feetState.getFluidState().isEmpty()
				&& headState.getFluidState().isEmpty()
				&& feetState.getCollisionShape(level, feet).isEmpty()
				&& headState.getCollisionShape(level, head).isEmpty()
				&& !floorState.getCollisionShape(level, floor).isEmpty();
	}

	private static BlockPos createEmergencyPlatform(ServerLevel level) {
		level.getChunk(0, 0);
		int floorY = Math.max(level.getMinBuildHeight() + 2, Math.min(level.getSeaLevel(), level.getMaxBuildHeight() - 4));
		for (int x = -3; x <= 3; x++) {
			for (int z = -3; z <= 3; z++) {
				BlockPos floor = new BlockPos(x, floorY, z);
				level.setBlock(floor, ModBlocks.DISTORTED_SAND.get().defaultBlockState(), 3);
				level.setBlock(floor.above(), Blocks.AIR.defaultBlockState(), 3);
				level.setBlock(floor.above(2), Blocks.AIR.defaultBlockState(), 3);
			}
		}
		return new BlockPos(0, floorY + 1, 0);
	}

	private static int safeFeetY(ServerLevel level, int x, int y, int z) {
		level.getChunk(x >> 4, z >> 4);
		BlockPos feet = new BlockPos(x, y, z);
		if (level.getBlockState(feet).isAir() && level.getBlockState(feet.above()).isAir()) {
			return y;
		}
		return level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
	}
}
