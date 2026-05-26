package com.nosoysun.vtmon.util;

import com.nosoysun.vtmon.VTMon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
	public static class Blocks {
		public static final TagKey<Block> NEEDS_EMERALD_TOOL = createTag("needs_emerald_tool");
		public static final TagKey<Block> INCORRECT_FOR_EMERALD_TOOL = createTag("incorrect_for_emerald_tool");

		private static TagKey<Block> createTag(String name) {
			return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(VTMon.MOD_ID, name));
		}
	}

	public static class Items {
		public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

		private static TagKey<Item> createTag(String name) {
			return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(VTMon.MOD_ID, name));
		}
	}
}
