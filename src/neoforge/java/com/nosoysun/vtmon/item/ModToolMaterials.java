package com.nosoysun.vtmon.item;

import com.google.common.base.Suppliers;
import com.nosoysun.vtmon.util.ModTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum ModToolMaterials implements Tier {
	EMERALD(ModTags.Blocks.INCORRECT_FOR_EMERALD_TOOL,
			1796, 20.0F, 3.5F, 20, () -> Ingredient.of(Items.EMERALD));

	private final TagKey<Block> incorrectBlocksForDrops;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Supplier<Ingredient> repairIngredient;

	ModToolMaterials(final TagKey<Block> incorrectBlocksForDrops, final int itemDurability, final float miningSpeed,
					 final float attackDamage, final int enchantability, final Supplier<Ingredient> repairIngredient) {
		this.incorrectBlocksForDrops = incorrectBlocksForDrops;
		this.itemDurability = itemDurability;
		this.miningSpeed = miningSpeed;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairIngredient = Suppliers.memoize(repairIngredient::get);
	}

	@Override
	public int getUses() {
		return this.itemDurability;
	}

	@Override
	public float getSpeed() {
		return this.miningSpeed;
	}

	@Override
	public float getAttackDamageBonus() {
		return this.attackDamage;
	}

	@Override
	public TagKey<Block> getIncorrectBlocksForDrops() {
		return this.incorrectBlocksForDrops;
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}
