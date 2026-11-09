package com.nosoysun.vtmon.world;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public final class OchreSmokeParticle extends SpriteBillboardParticle {
	private final SpriteProvider sprites;
	private final float initialScale;

	private OchreSmokeParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY,
			double velocityZ, SpriteProvider sprites) {
		super(world, x, y, z, velocityX, velocityY, velocityZ);
		this.sprites = sprites;
		this.initialScale = 0.85F + this.random.nextFloat() * 0.8F;
		this.scale = this.initialScale;
		this.maxAge = 34 + this.random.nextInt(25);
		this.velocityMultiplier = 0.96F;
		this.setColor(0.78F, 0.48F, 0.18F);
		this.setAlpha(0.42F);
		this.setSpriteForAge(sprites);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.isAlive()) {
			float progress = (float) this.age / this.maxAge;
			this.setAlpha(0.62F * (1.0F - progress));
			this.scale = this.initialScale * (1.0F + 0.45F * progress);
			this.setSpriteForAge(this.sprites);
		}
	}

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}

	public static final class Factory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider sprites;

		public Factory(SpriteProvider sprites) {
			this.sprites = sprites;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z,
				double velocityX, double velocityY, double velocityZ) {
			return new OchreSmokeParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.sprites);
		}
	}
}
