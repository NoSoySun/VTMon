package com.nosoysun.vtmon.world;

import com.nosoysun.vtmon.VTMon;
import com.nosoysun.vtmon.item.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.UUID;

public final class InterworldTravel {
	public static final RegistryKey<World> DIMENSION = RegistryKey.of(
			RegistryKeys.WORLD,
			Identifier.of("vtmon", "interworld")
	);
	private static final long MAX_STAY_TICKS = 30L * 60L * 20L;

	private InterworldTravel() {
	}

	public static void enter(ServerPlayerEntity player) {
		if (!World.OVERWORLD.equals(player.getWorld().getRegistryKey())) {
			return;
		}
		MinecraftServer server = player.getServer();
		if (server == null) {
			return;
		}
		ServerWorld overworld = server.getOverworld();
		ServerWorld destination = server.getWorld(DIMENSION);
		if (destination == null) {
			return;
		}

		InterworldTravelState data = InterworldTravelState.get(overworld);
		UUID playerId = player.getUuid();
		rememberOverworldRespawn(player, data);
		if (!data.hasTrip(playerId)) {
			data.beginTrip(playerId, overworld.getTime());
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

		player.teleport(destination, safeFeet.getX() + 0.5, safeFeet.getY(), safeFeet.getZ() + 0.5,
				player.getYaw(), player.getPitch());
		player.sendMessage(Text.translatable("message.vtmon.interworld.entered"), false);
	}

	public static void tick(MinecraftServer server) {
		ServerWorld overworld = server.getOverworld();
		InterworldTravelState data = InterworldTravelState.get(overworld);
		long now = overworld.getTime();

		for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
			UUID playerId = player.getUuid();
			if (!DIMENSION.equals(player.getWorld().getRegistryKey())) {
				data.clearTrip(playerId);
				continue;
			}

			if (!data.hasTrip(playerId)) {
				rememberOverworldRespawn(player, data);
				data.beginTrip(playerId, now);
				player.sendMessage(Text.translatable("message.vtmon.interworld.entered"), false);
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

	private static void rememberOverworldRespawn(ServerPlayerEntity player, InterworldTravelState data) {
		UUID playerId = player.getUuid();
		BlockPos spawn = player.getSpawnPointPosition();
		if (spawn != null && World.OVERWORLD.equals(player.getSpawnPointDimension())) {
			data.setOverworldRespawn(playerId, spawn, player.getSpawnAngle());
		} else if (!data.hasOverworldRespawn(playerId)) {
			data.clearOverworldRespawn(playerId);
		}
	}

	private static BlockPos getActiveAnchor(ServerPlayerEntity player, ServerWorld destination) {
		BlockPos spawn = player.getSpawnPointPosition();
		if (spawn == null || !DIMENSION.equals(player.getSpawnPointDimension())) {
			return null;
		}
		BlockState state = destination.getBlockState(spawn);
		return state.isOf(Blocks.RESPAWN_ANCHOR)
				&& state.get(RespawnAnchorBlock.CHARGES) > 0
			? spawn
			: null;
	}

	private static void returnToOverworld(ServerPlayerEntity player, ServerWorld overworld, InterworldTravelState data) {
		UUID playerId = player.getUuid();
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
			BlockPos worldSpawn = overworld.getSpawnPos();
			x = worldSpawn.getX() + 0.5;
			y = overworld.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, worldSpawn.getX(), worldSpawn.getZ());
			z = worldSpawn.getZ() + 0.5;
			yaw = overworld.getSpawnAngle();
		}

		player.teleport(overworld, x, y, z, yaw, 0.0F);
		player.sendMessage(Text.translatable("message.vtmon.interworld.timeout"), false);
	}

	private static BlockPos getSafeAnchorPosition(ServerWorld world, BlockPos anchor) {
		BlockPos feet = anchor.up();
		return isSafeFeetPosition(world, feet) ? feet : null;
	}

	private static BlockPos findSafeSurface(ServerWorld world, int centerX, int centerZ) {
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
					world.getChunk(chunkX, chunkZ);
					for (int[] offset : localOffsets) {
						int x = (chunkX << 4) + offset[0];
						int z = (chunkZ << 4) + offset[1];
						int feetY = world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, x, z);
						if (feetY <= world.getBottomY() || feetY >= world.getTopY() - 1) {
							continue;
						}
						BlockPos feet = new BlockPos(x, feetY, z);
						if (isSafeFeetPosition(world, feet)) {
							return feet;
						}
					}
				}
			}
		}
		return null;
	}

	private static boolean isSafeFeetPosition(ServerWorld world, BlockPos feet) {
		BlockPos head = feet.up();
		BlockPos floor = feet.down();
		BlockState feetState = world.getBlockState(feet);
		BlockState headState = world.getBlockState(head);
		BlockState floorState = world.getBlockState(floor);
		return world.getWorldBorder().contains(feet)
				&& feetState.getFluidState().isEmpty()
				&& headState.getFluidState().isEmpty()
				&& feetState.getCollisionShape(world, feet).isEmpty()
				&& headState.getCollisionShape(world, head).isEmpty()
				&& !floorState.getCollisionShape(world, floor).isEmpty();
	}

	private static BlockPos createEmergencyPlatform(ServerWorld world) {
		world.getChunk(0, 0);
		int floorY = Math.max(world.getBottomY() + 2, Math.min(world.getSeaLevel(), world.getTopY() - 4));
		for (int x = -3; x <= 3; x++) {
			for (int z = -3; z <= 3; z++) {
				BlockPos floor = new BlockPos(x, floorY, z);
				world.setBlockState(floor, ModBlocks.DISTORTED_SAND.getDefaultState(), Block.NOTIFY_ALL);
				world.setBlockState(floor.up(), Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
				world.setBlockState(floor.up(2), Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
			}
		}
		return new BlockPos(0, floorY + 1, 0);
	}

	private static int safeFeetY(ServerWorld world, int x, int y, int z) {
		world.getChunk(x >> 4, z >> 4);
		BlockPos feet = new BlockPos(x, y, z);
		if (world.getBlockState(feet).isAir() && world.getBlockState(feet.up()).isAir()) {
			return y;
		}
		return world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, x, z);
	}
}
