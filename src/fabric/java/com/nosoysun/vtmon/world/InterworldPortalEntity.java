package com.nosoysun.vtmon.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public final class InterworldPortalEntity extends Entity {
	public InterworldPortalEntity(EntityType<? extends InterworldPortalEntity> type, World world) {
		super(type, world);
		this.noClip = true;
		this.setNoGravity(true);
		this.setInvulnerable(true);
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder) {
	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {
	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {
	}

	@Override
	public void tick() {
		super.tick();
		if (!hasValidAnchor()) {
			discard();
			return;
		}

		this.noClip = true;
		this.setNoGravity(true);
		this.setVelocity(Vec3d.ZERO);
		updatePortalHitbox();
		spawnAmbientParticles();

		if (getWorld() instanceof ServerWorld world) {
			Box hitbox = getBoundingBox();
			for (ServerPlayerEntity player : world.getServer().getPlayerManager().getPlayerList()) {
				if (player.getWorld() == world && hitbox.intersects(player.getBoundingBox())) {
					InterworldTravel.enter(player);
				}
			}
		}
	}

	private boolean hasValidAnchor() {
		BlockPos portalBase = getBlockPos();
		BlockState baseState = getWorld().getBlockState(portalBase);
		if (baseState.isOf(Blocks.SOUL_FIRE)
				&& getWorld().getBlockState(portalBase.down()).isOf(Blocks.SOUL_SAND)) {
			return true;
		}

		BlockState campfireState = getWorld().getBlockState(portalBase.down());
		return campfireState.isOf(Blocks.SOUL_CAMPFIRE) && campfireState.get(CampfireBlock.LIT);
	}

	private void updatePortalHitbox() {
		double yaw = Math.toRadians(getYaw());
		double cos = Math.abs(Math.cos(yaw));
		double sin = Math.abs(Math.sin(yaw));
		double halfX = cos + sin * 0.12;
		double halfZ = sin + cos * 0.12;
		setBoundingBox(new Box(getX() - halfX, getY(), getZ() - halfZ,
				getX() + halfX, getY() + getHeight(), getZ() + halfZ));
	}

	private void spawnAmbientParticles() {
		if (!getWorld().isClient) {
			return;
		}

		double yaw = Math.toRadians(getYaw());
		double rightX = Math.cos(yaw);
		double rightZ = -Math.sin(yaw);
		double normalX = Math.sin(yaw);
		double normalZ = Math.cos(yaw);
		if (age % 2 == 0) {
			for (int i = 0; i < 10; i++) {
				double depth = getRandom().nextDouble() * 2.0 - 1.0;
				double angle = getRandom().nextDouble() * Math.PI * 2.0;
				double waistRadius = 0.14;
				double outerRadius = 1.0;
				double radius = Math.sqrt(waistRadius * waistRadius
						+ (outerRadius * outerRadius - waistRadius * waistRadius) * depth * depth);
				double fill = i < 5 ? 0.72 + getRandom().nextDouble() * 0.28 : Math.sqrt(getRandom().nextDouble());
				double localX = Math.cos(angle) * radius * fill * 0.82;
				double localY = Math.sin(angle) * radius * fill * 1.12;
				double tangent = 0.008 + getRandom().nextDouble() * 0.006;
				double axisDrift = -depth * 0.005;
				getWorld().addParticle(ModParticles.OCHRE_SMOKE,
						getX() + rightX * localX + normalX * depth * 0.58,
						getY() + 1.5 + localY,
						getZ() + rightZ * localX + normalZ * depth * 0.58,
						rightX * (-Math.sin(angle) * tangent) + normalX * axisDrift,
						Math.cos(angle) * tangent + 0.002,
						rightZ * (-Math.sin(angle) * tangent) + normalZ * axisDrift);
			}
		}

		if (age % 3 == 0) {
			double phase = age * 0.14;
			for (int i = 0; i < 3; i++) {
				double angle = phase + i * Math.PI * 2.0 / 3.0;
				double radius = 0.72 + (i * 0.12);
				double unitX = Math.cos(angle);
				double unitY = Math.sin(angle);
				double localX = unitX * radius;
				double localY = unitY * radius;
				double inward = 0.012;
				double tangent = 0.018;
				getWorld().addParticle(ParticleTypes.WAX_ON, getX() + rightX * localX,
						getY() + 1.5 + localY, getZ() + rightZ * localX,
						rightX * (-unitX * inward - unitY * tangent), -unitY * inward + unitX * tangent,
						rightZ * (-unitX * inward - unitY * tangent));
			}
		}

		if (age % 7 == 0) {
			double angle = getRandom().nextDouble() * Math.PI * 2.0;
			double radius = 0.45 + getRandom().nextDouble() * 0.45;
			double unitX = Math.cos(angle);
			double unitY = Math.sin(angle);
			double localX = unitX * radius;
			double localY = unitY * radius;
			getWorld().addParticle(ParticleTypes.ASH, getX() + rightX * localX, getY() + 1.5 + localY,
					getZ() + rightZ * localX, rightX * -unitX * 0.008, -unitY * 0.008,
					rightZ * -unitX * 0.008);
		}
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

	@Override
	public boolean canHit() {
		return false;
	}
}
