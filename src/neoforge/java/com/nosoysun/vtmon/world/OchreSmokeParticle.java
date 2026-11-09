package com.nosoysun.vtmon.world;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;

public final class OchreSmokeParticle extends TextureSheetParticle {
	private final SpriteSet sprites;
	private final float initialScale;

	private OchreSmokeParticle(ClientLevel level, double x, double y, double z, double velocityX, double velocityY,
			double velocityZ, SpriteSet sprites) {
		super(level, x, y, z, velocityX, velocityY, velocityZ);
		this.sprites = sprites;
		this.initialScale = 0.85F + this.random.nextFloat() * 0.8F;
		this.quadSize = this.initialScale;
		this.lifetime = 34 + this.random.nextInt(25);
		this.friction = 0.96F;
		this.setColor(0.78F, 0.48F, 0.18F);
		this.setAlpha(0.62F);
		this.setSpriteFromAge(sprites);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.isAlive()) {
			float progress = (float) this.age / this.lifetime;
			this.setAlpha(0.62F * (1.0F - progress));
			this.quadSize = this.initialScale * (1.0F + 0.45F * progress);
			this.setSpriteFromAge(this.sprites);
		}
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	public static final class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet sprites) {
			this.sprites = sprites;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
				double velocityX, double velocityY, double velocityZ) {
			return new OchreSmokeParticle(level, x, y, z, velocityX, velocityY, velocityZ, this.sprites);
		}
	}
}
