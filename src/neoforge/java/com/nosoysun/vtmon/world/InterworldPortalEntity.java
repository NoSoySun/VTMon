package com.nosoysun.vtmon.world;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public final class InterworldPortalEntity extends Entity {
	public InterworldPortalEntity(EntityType<? extends InterworldPortalEntity> type, Level level) {
		super(type, level);
		this.noPhysics = true;
		this.setNoGravity(true);
		this.setInvulnerable(true);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag tag) {
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag tag) {
	}

	@Override
	public void tick() {
		super.tick();
		if (!hasValidAnchor()) {
			this.discard();
			return;
		}

		this.noPhysics = true;
		this.setNoGravity(true);
		this.setDeltaMovement(Vec3.ZERO);
		updatePortalHitbox();
		spawnAmbientParticles();

		if (this.level() instanceof ServerLevel level) {
			AABB hitbox = this.getBoundingBox();
			for (ServerPlayer player : level.getServer().getPlayerList().getPlayers()) {
				if (player.level() == level && hitbox.intersects(player.getBoundingBox())) {
					InterworldTravel.enter(player);
				}
			}
		}
	}

	private boolean hasValidAnchor() {
		BlockPos portalBase = this.blockPosition();
		BlockState baseState = this.level().getBlockState(portalBase);
		if (baseState.is(Blocks.SOUL_FIRE)
				&& this.level().getBlockState(portalBase.below()).is(Blocks.SOUL_SAND)) {
			return true;
		}

		BlockState campfireState = this.level().getBlockState(portalBase.below());
		return campfireState.is(Blocks.SOUL_CAMPFIRE) && campfireState.getValue(CampfireBlock.LIT);
	}

	private void updatePortalHitbox() {
		double yaw = Math.toRadians(this.getYRot());
		double cos = Math.abs(Math.cos(yaw));
		double sin = Math.abs(Math.sin(yaw));
		double halfX = cos + sin * 0.12;
		double halfZ = sin + cos * 0.12;
		this.setBoundingBox(new AABB(this.getX() - halfX, this.getY(), this.getZ() - halfZ,
				this.getX() + halfX, this.getY() + this.getBbHeight(), this.getZ() + halfZ));
	}

	private void spawnAmbientParticles() {
		if (!this.level().isClientSide) {
			return;
		}

		double yaw = Math.toRadians(this.getYRot());
		double rightX = Math.cos(yaw);
		double rightZ = -Math.sin(yaw);
		double normalX = Math.sin(yaw);
		double normalZ = Math.cos(yaw);
		if (this.tickCount % 2 == 0) {
			for (int i = 0; i < 10; i++) {
				double depth = this.random.nextDouble() * 2.0 - 1.0;
				double angle = this.random.nextDouble() * Math.PI * 2.0;
				double waistRadius = 0.14;
				double outerRadius = 1.0;
				double radius = Math.sqrt(waistRadius * waistRadius
						+ (outerRadius * outerRadius - waistRadius * waistRadius) * depth * depth);
				double fill = i < 5 ? 0.72 + this.random.nextDouble() * 0.28 : Math.sqrt(this.random.nextDouble());
				double localX = Math.cos(angle) * radius * fill * 0.82;
				double localY = Math.sin(angle) * radius * fill * 1.12;
				double tangent = 0.008 + this.random.nextDouble() * 0.006;
				double axisDrift = -depth * 0.005;
				this.level().addParticle(ModParticles.OCHRE_SMOKE.get(),
						this.getX() + rightX * localX + normalX * depth * 0.58,
						this.getY() + 1.5 + localY,
						this.getZ() + rightZ * localX + normalZ * depth * 0.58,
						rightX * (-Math.sin(angle) * tangent) + normalX * axisDrift,
						Math.cos(angle) * tangent + 0.002,
						rightZ * (-Math.sin(angle) * tangent) + normalZ * axisDrift);
			}
		}

		if (this.tickCount % 3 == 0) {
			double phase = this.tickCount * 0.14;
			for (int i = 0; i < 3; i++) {
				double angle = phase + i * Math.PI * 2.0 / 3.0;
				double radius = 0.72 + (i * 0.12);
				double unitX = Math.cos(angle);
				double unitY = Math.sin(angle);
				double localX = unitX * radius;
				double localY = unitY * radius;
				double inward = 0.012;
				double tangent = 0.018;
				this.level().addParticle(ParticleTypes.WAX_ON, this.getX() + rightX * localX,
						this.getY() + 1.5 + localY, this.getZ() + rightZ * localX,
						rightX * (-unitX * inward - unitY * tangent), -unitY * inward + unitX * tangent,
						rightZ * (-unitX * inward - unitY * tangent));
			}
		}

		if (this.tickCount % 7 == 0) {
			double angle = this.random.nextDouble() * Math.PI * 2.0;
			double radius = 0.45 + this.random.nextDouble() * 0.45;
			double unitX = Math.cos(angle);
			double unitY = Math.sin(angle);
			double localX = unitX * radius;
			double localY = unitY * radius;
			this.level().addParticle(ParticleTypes.ASH, this.getX() + rightX * localX,
					this.getY() + 1.5 + localY, this.getZ() + rightZ * localX,
					rightX * -unitX * 0.008, -unitY * 0.008, rightZ * -unitX * 0.008);
		}
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isPickable() {
		return false;
	}

	@Override
	public boolean hurt(net.minecraft.world.damagesource.DamageSource source, float amount) {
		return false;
	}
}
