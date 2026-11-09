package com.nosoysun.vtmon.item;

import com.nosoysun.vtmon.VTMon;
import com.nosoysun.vtmon.world.InterworldPortalBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.WorldAccess;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ModBlocks {
	public static final List<Item> ADDITIONAL_VARIANT_ITEMS = new ArrayList<>();
	public static final Block DISTORTED_SAND = Registry.register(
			Registries.BLOCK,
			Identifier.of(VTMon.MOD_ID, "distorted_sand"),
			new Block(AbstractBlock.Settings.copy(Blocks.SAND))
	);

	public static final Item DISTORTED_SAND_ITEM = Registry.register(
			Registries.ITEM,
			Identifier.of(VTMon.MOD_ID, "distorted_sand"),
			new BlockItem(DISTORTED_SAND, new Item.Settings())
	);

	public static final Block BLIGHTED_GRASS_BLOCK = Registry.register(
			Registries.BLOCK,
			Identifier.of(VTMon.MOD_ID, "blighted_grass_block"),
			new BlightedGrassBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK))
	);

	public static final Item BLIGHTED_GRASS_BLOCK_ITEM = Registry.register(
			Registries.ITEM,
			Identifier.of(VTMon.MOD_ID, "blighted_grass_block"),
			new BlockItem(BLIGHTED_GRASS_BLOCK, new Item.Settings())
	);

	public static final Block WITHERED_DIRT = registerBlock(
			"withered_dirt",
			new WitheredDirtBlock(AbstractBlock.Settings.copy(Blocks.DIRT))
	);
	public static final Item WITHERED_DIRT_ITEM = registerBlockItem("withered_dirt", WITHERED_DIRT);
	public static final Block WITHERED_FARMLAND = registerBlock(
			"withered_farmland",
			new WitheredFarmlandBlock(AbstractBlock.Settings.copy(Blocks.FARMLAND))
	);
	public static final Item WITHERED_FARMLAND_ITEM = registerBlockItem("withered_farmland", WITHERED_FARMLAND);

	public static final Block DISTORTED_NETHERRACK = Registry.register(
			Registries.BLOCK,
			Identifier.of(VTMon.MOD_ID, "distorted_netherrack"),
			new Block(AbstractBlock.Settings.copy(Blocks.NETHERRACK))
	);

	public static final Item DISTORTED_NETHERRACK_ITEM = Registry.register(
			Registries.ITEM,
			Identifier.of(VTMon.MOD_ID, "distorted_netherrack"),
			new BlockItem(DISTORTED_NETHERRACK, new Item.Settings())
	);

	public static final Block DISTORTED_BASALT = Registry.register(
			Registries.BLOCK,
			Identifier.of(VTMon.MOD_ID, "distorted_basalt"),
			new PillarBlock(AbstractBlock.Settings.copy(Blocks.BASALT))
	);

	public static final Item DISTORTED_BASALT_ITEM = Registry.register(
			Registries.ITEM,
			Identifier.of(VTMon.MOD_ID, "distorted_basalt"),
			new BlockItem(DISTORTED_BASALT, new Item.Settings())
	);

	public static final Block DISTORTED_NETHERRACK_BRICKS = registerBlock(
			"distorted_netherrack_bricks",
			new Block(AbstractBlock.Settings.copy(Blocks.NETHERRACK))
	);
	public static final Item DISTORTED_NETHERRACK_BRICKS_ITEM = registerBlockItem(
			"distorted_netherrack_bricks", DISTORTED_NETHERRACK_BRICKS
	);

	public static final Block DISTORTED_SANDSTONE = registerBlock(
			"distorted_sandstone",
			new Block(AbstractBlock.Settings.copy(Blocks.SANDSTONE))
	);
	public static final Item DISTORTED_SANDSTONE_ITEM = registerBlockItem(
			"distorted_sandstone", DISTORTED_SANDSTONE
	);
	public static final Block CUT_DISTORTED_SANDSTONE = registerBlock(
			"cut_distorted_sandstone",
			new Block(AbstractBlock.Settings.copy(Blocks.CUT_SANDSTONE))
	);
	public static final Item CUT_DISTORTED_SANDSTONE_ITEM = registerBlockItem(
			"cut_distorted_sandstone", CUT_DISTORTED_SANDSTONE
	);
	public static final Block SMOOTH_DISTORTED_SANDSTONE = registerBlock(
			"smooth_distorted_sandstone",
			new Block(AbstractBlock.Settings.copy(Blocks.SMOOTH_SANDSTONE))
	);
	public static final Item SMOOTH_DISTORTED_SANDSTONE_ITEM = registerBlockItem(
			"smooth_distorted_sandstone", SMOOTH_DISTORTED_SANDSTONE
	);
	public static final Block CHISELED_DISTORTED_SANDSTONE = registerBlock(
			"chiseled_distorted_sandstone",
			new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_SANDSTONE))
	);
	public static final Item CHISELED_DISTORTED_SANDSTONE_ITEM = registerBlockItem(
			"chiseled_distorted_sandstone", CHISELED_DISTORTED_SANDSTONE
	);
	public static final Block CHISELED_DISTORTED_NETHERRACK_BRICKS = registerBlock(
			"chiseled_distorted_netherrack_bricks",
			new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_NETHER_BRICKS))
	);
	public static final Item CHISELED_DISTORTED_NETHERRACK_BRICKS_ITEM = registerBlockItem(
			"chiseled_distorted_netherrack_bricks", CHISELED_DISTORTED_NETHERRACK_BRICKS
	);
	public static final Block CRACKED_DISTORTED_NETHERRACK_BRICKS = registerBlock(
			"cracked_distorted_netherrack_bricks",
			new Block(AbstractBlock.Settings.copy(Blocks.CRACKED_NETHER_BRICKS))
	);
	public static final Item CRACKED_DISTORTED_NETHERRACK_BRICKS_ITEM = registerBlockItem(
			"cracked_distorted_netherrack_bricks", CRACKED_DISTORTED_NETHERRACK_BRICKS
	);

	public static final Block WITHERED_MUD = registerBlock(
			"withered_mud",
			new Block(AbstractBlock.Settings.copy(Blocks.MUD))
	);
	public static final Item WITHERED_MUD_ITEM = registerBlockItem("withered_mud", WITHERED_MUD);

	public static final Block WITHERED_MUD_BRICKS = registerBlock(
			"withered_mud_bricks",
			new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS))
	);
	public static final Item WITHERED_MUD_BRICKS_ITEM = registerBlockItem(
			"withered_mud_bricks", WITHERED_MUD_BRICKS
	);
	public static final Block CRACKED_WITHERED_MUD_BRICKS = registerBlock(
			"cracked_withered_mud_bricks",
			new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS))
	);
	public static final Item CRACKED_WITHERED_MUD_BRICKS_ITEM = registerBlockItem(
			"cracked_withered_mud_bricks", CRACKED_WITHERED_MUD_BRICKS
	);
	public static final Block CHISELED_WITHERED_MUD_BRICKS = registerBlock(
			"chiseled_withered_mud_bricks",
			new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS))
	);
	public static final Item CHISELED_WITHERED_MUD_BRICKS_ITEM = registerBlockItem(
			"chiseled_withered_mud_bricks", CHISELED_WITHERED_MUD_BRICKS
	);

	public static final Block POLISHED_DISTORTED_BASALT = registerBlock(
			"polished_distorted_basalt",
			new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_BASALT))
	);
	public static final Item POLISHED_DISTORTED_BASALT_ITEM = registerBlockItem(
			"polished_distorted_basalt", POLISHED_DISTORTED_BASALT
	);
	public static final Block SMOOTH_DISTORTED_BASALT = registerBlock(
			"smooth_distorted_basalt",
			new Block(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT))
	);
	public static final Item SMOOTH_DISTORTED_BASALT_ITEM = registerBlockItem(
			"smooth_distorted_basalt", SMOOTH_DISTORTED_BASALT
	);
	public static final Block CORRODED_STONE = registerBlock(
			"corroded_stone",
			new Block(AbstractBlock.Settings.copy(Blocks.STONE))
	);
	public static final Item CORRODED_STONE_ITEM = registerBlockItem("corroded_stone", CORRODED_STONE);
	public static final Block COBBLED_CORRODED_STONE = registerBlock(
			"cobbled_corroded_stone",
			new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE))
	);
	public static final Item COBBLED_CORRODED_STONE_ITEM = registerBlockItem(
			"cobbled_corroded_stone", COBBLED_CORRODED_STONE
	);
	public static final Block POLISHED_CORRODED_STONE = registerBlock(
			"polished_corroded_stone",
			new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE))
	);
	public static final Item POLISHED_CORRODED_STONE_ITEM = registerBlockItem(
			"polished_corroded_stone", POLISHED_CORRODED_STONE
	);
	public static final Block CORRODED_STONE_BRICKS = registerBlock(
			"corroded_stone_bricks",
			new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS))
	);
	public static final Item CORRODED_STONE_BRICKS_ITEM = registerBlockItem(
			"corroded_stone_bricks", CORRODED_STONE_BRICKS
	);
	public static final Block CRACKED_CORRODED_STONE_BRICKS = registerBlock(
			"cracked_corroded_stone_bricks",
			new Block(AbstractBlock.Settings.copy(Blocks.CRACKED_STONE_BRICKS))
	);
	public static final Item CRACKED_CORRODED_STONE_BRICKS_ITEM = registerBlockItem(
			"cracked_corroded_stone_bricks", CRACKED_CORRODED_STONE_BRICKS
	);
	public static final Block CHISELED_CORRODED_STONE_BRICKS = registerBlock(
			"chiseled_corroded_stone_bricks",
			new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS))
	);
	public static final Item CHISELED_CORRODED_STONE_BRICKS_ITEM = registerBlockItem(
			"chiseled_corroded_stone_bricks", CHISELED_CORRODED_STONE_BRICKS
	);
	public static final Block CORRODED_COAL_ORE = registerCorrodedOre(
			"corroded_coal_ore", Blocks.COAL_ORE, UniformIntProvider.create(0, 2)
	);
	public static final Item CORRODED_COAL_ORE_ITEM = registerBlockItem("corroded_coal_ore", CORRODED_COAL_ORE);
	public static final Block CORRODED_COPPER_ORE = registerCorrodedOre(
			"corroded_copper_ore", Blocks.COPPER_ORE, UniformIntProvider.create(0, 0)
	);
	public static final Item CORRODED_COPPER_ORE_ITEM = registerBlockItem("corroded_copper_ore", CORRODED_COPPER_ORE);
	public static final Block CORRODED_IRON_ORE = registerCorrodedOre(
			"corroded_iron_ore", Blocks.IRON_ORE, UniformIntProvider.create(0, 0)
	);
	public static final Item CORRODED_IRON_ORE_ITEM = registerBlockItem("corroded_iron_ore", CORRODED_IRON_ORE);
	public static final Block CORRODED_GOLD_ORE = registerCorrodedOre(
			"corroded_gold_ore", Blocks.GOLD_ORE, UniformIntProvider.create(0, 0)
	);
	public static final Item CORRODED_GOLD_ORE_ITEM = registerBlockItem("corroded_gold_ore", CORRODED_GOLD_ORE);
	public static final Block CORRODED_REDSTONE_ORE = registerCorrodedOre(
			"corroded_redstone_ore", Blocks.DIAMOND_ORE, UniformIntProvider.create(1, 5)
	);
	public static final Item CORRODED_REDSTONE_ORE_ITEM = registerBlockItem("corroded_redstone_ore", CORRODED_REDSTONE_ORE);
	public static final Block CORRODED_LAPIS_ORE = registerCorrodedOre(
			"corroded_lapis_ore", Blocks.LAPIS_ORE, UniformIntProvider.create(2, 5)
	);
	public static final Item CORRODED_LAPIS_ORE_ITEM = registerBlockItem("corroded_lapis_ore", CORRODED_LAPIS_ORE);
	public static final Block CORRODED_DIAMOND_ORE = registerCorrodedOre(
			"corroded_diamond_ore", Blocks.DIAMOND_ORE, UniformIntProvider.create(3, 7)
	);
	public static final Item CORRODED_DIAMOND_ORE_ITEM = registerBlockItem("corroded_diamond_ore", CORRODED_DIAMOND_ORE);
	public static final Block CORRODED_EMERALD_ORE = registerCorrodedOre(
			"corroded_emerald_ore", Blocks.EMERALD_ORE, UniformIntProvider.create(3, 7)
	);
	public static final Item CORRODED_EMERALD_ORE_ITEM = registerBlockItem("corroded_emerald_ore", CORRODED_EMERALD_ORE);
	public static final Block CORRODED_NETHER_QUARTZ_ORE = registerCorrodedOre(
			"corroded_nether_quartz_ore", Blocks.NETHER_QUARTZ_ORE, UniformIntProvider.create(2, 5)
	);
	public static final Item CORRODED_NETHER_QUARTZ_ORE_ITEM = registerBlockItem(
			"corroded_nether_quartz_ore", CORRODED_NETHER_QUARTZ_ORE
	);

	public static final Block DISTORTED_GLASS = registerVariant("distorted_glass", new ConnectedGlassBlock(AbstractBlock.Settings.copy(Blocks.GLASS).luminance(state -> 14)));
	public static final Block DISTORTED_GLASS_PANE = registerVariant("distorted_glass_pane", new InterworldPaneBlock(AbstractBlock.Settings.copy(Blocks.GLASS_PANE).luminance(state -> 14)));
	public static final Block DISTORTED_NETHERRACK_BRICK_STAIRS = registerVariant("distorted_netherrack_brick_stairs", new InterworldStairsBlock(DISTORTED_NETHERRACK_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_STAIRS)));
	public static final Block DISTORTED_NETHERRACK_BRICK_SLAB = registerVariant("distorted_netherrack_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_SLAB)));
	public static final Block DISTORTED_NETHERRACK_BRICK_WALL = registerVariant("distorted_netherrack_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_WALL)));
	public static final Block DISTORTED_NETHERRACK_BRICK_FENCE = registerVariant("distorted_netherrack_brick_fence", new FenceBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE)));
	public static final Block DISTORTED_NETHERRACK_BRICK_TILES = registerVariant("distorted_netherrack_brick_tiles", new Block(AbstractBlock.Settings.copy(DISTORTED_NETHERRACK_BRICKS)));
	public static final Block DISTORTED_SANDSTONE_STAIRS = registerVariant("distorted_sandstone_stairs", new InterworldStairsBlock(DISTORTED_SANDSTONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SANDSTONE_STAIRS)));
	public static final Block DISTORTED_SANDSTONE_SLAB = registerVariant("distorted_sandstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE_SLAB)));
	public static final Block DISTORTED_SANDSTONE_WALL = registerVariant("distorted_sandstone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)));
	public static final Block DISTORTED_SANDSTONE_TILES = registerVariant("distorted_sandstone_tiles", new Block(AbstractBlock.Settings.copy(DISTORTED_SANDSTONE)));
	public static final Block CUT_DISTORTED_SANDSTONE_SLAB = registerVariant("cut_distorted_sandstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CUT_SANDSTONE_SLAB)));
	public static final Block SMOOTH_DISTORTED_SANDSTONE_STAIRS = registerVariant("smooth_distorted_sandstone_stairs", new InterworldStairsBlock(SMOOTH_DISTORTED_SANDSTONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SANDSTONE_STAIRS)));
	public static final Block SMOOTH_DISTORTED_SANDSTONE_SLAB = registerVariant("smooth_distorted_sandstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_SANDSTONE_SLAB)));
	public static final Block WITHERED_MUD_BRICK_STAIRS = registerVariant("withered_mud_brick_stairs", new InterworldStairsBlock(WITHERED_MUD_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.MUD_BRICK_STAIRS)));
	public static final Block WITHERED_MUD_BRICK_SLAB = registerVariant("withered_mud_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICK_SLAB)));
	public static final Block WITHERED_MUD_BRICK_WALL = registerVariant("withered_mud_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICK_WALL)));
	public static final Block WITHERED_MUD_BRICK_FENCE = registerVariant("withered_mud_brick_fence", new FenceBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE)));
	public static final Block WITHERED_MUD_BRICK_TILES = registerVariant("withered_mud_brick_tiles", new Block(AbstractBlock.Settings.copy(WITHERED_MUD_BRICKS)));
	public static final Block CORRODED_STONE_BRICK_STAIRS = registerVariant("corroded_stone_brick_stairs", new InterworldStairsBlock(CORRODED_STONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)));
	public static final Block CORRODED_STONE_BRICK_SLAB = registerVariant("corroded_stone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)));
	public static final Block CORRODED_STONE_BRICK_WALL = registerVariant("corroded_stone_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)));
	public static final Block CORRODED_STONE_BRICK_FENCE = registerVariant("corroded_stone_brick_fence", new FenceBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE)));
	public static final Block CORRODED_STONE_BRICK_TILES = registerVariant("corroded_stone_brick_tiles", new Block(AbstractBlock.Settings.copy(CORRODED_STONE_BRICKS)));
	public static final Block POLISHED_CORRODED_STONE_STAIRS = registerVariant("polished_corroded_stone_stairs", new InterworldStairsBlock(POLISHED_CORRODED_STONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE_STAIRS)));
	public static final Block POLISHED_CORRODED_STONE_SLAB = registerVariant("polished_corroded_stone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE_SLAB)));
	public static final Block POLISHED_CORRODED_STONE_WALL = registerVariant("polished_corroded_stone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE_WALL)));
	public static final Block POLISHED_DISTORTED_BASALT_STAIRS = registerVariant("polished_distorted_basalt_stairs", new InterworldStairsBlock(POLISHED_DISTORTED_BASALT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)));
	public static final Block POLISHED_DISTORTED_BASALT_SLAB = registerVariant("polished_distorted_basalt_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)));
	public static final Block POLISHED_DISTORTED_BASALT_WALL = registerVariant("polished_distorted_basalt_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL)));
	public static final Block SMOOTH_DISTORTED_BASALT_STAIRS = registerVariant("smooth_distorted_basalt_stairs", new InterworldStairsBlock(SMOOTH_DISTORTED_BASALT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE_BRICK_STAIRS)));
	public static final Block SMOOTH_DISTORTED_BASALT_SLAB = registerVariant("smooth_distorted_basalt_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB)));

	public static final Block INTERWORLD_PORTAL = Registry.register(
			Registries.BLOCK,
			Identifier.of(VTMon.MOD_ID, "interworld_portal"),
			new InterworldPortalBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_STAINED_GLASS)
					.nonOpaque()
					.strength(-1.0F))
	);

	private ModBlocks() {
	}

	private static Block registerBlock(String name, Block block) {
		return Registry.register(Registries.BLOCK, Identifier.of(VTMon.MOD_ID, name), block);
	}

	private static Item registerBlockItem(String name, Block block) {
		return Registry.register(Registries.ITEM, Identifier.of(VTMon.MOD_ID, name), new BlockItem(block, new Item.Settings()));
	}

	private static Block registerVariant(String name, Block block) {
		Block registered = registerBlock(name, block);
		ADDITIONAL_VARIANT_ITEMS.add(registerBlockItem(name, registered));
		return registered;
	}

	private static Block registerCorrodedOre(String name, Block vanillaOre, UniformIntProvider experience) {
		return registerBlock(name, new ExperienceDroppingBlock(experience, AbstractBlock.Settings.copy(vanillaOre)));
	}

	private static final class InterworldStairsBlock extends StairsBlock {
		private InterworldStairsBlock(net.minecraft.block.BlockState baseState, AbstractBlock.Settings settings) {
			super(baseState, settings);
		}
	}

	private static final class InterworldPaneBlock extends PaneBlock {
		private InterworldPaneBlock(AbstractBlock.Settings settings) {
			super(settings);
		}
	}

	private static final class ConnectedGlassBlock extends TransparentBlock {
		private static final BooleanProperty NORTH = BooleanProperty.of("north");
		private static final BooleanProperty EAST = BooleanProperty.of("east");
		private static final BooleanProperty SOUTH = BooleanProperty.of("south");
		private static final BooleanProperty WEST = BooleanProperty.of("west");
		private static final BooleanProperty UP = BooleanProperty.of("up");
		private static final BooleanProperty DOWN = BooleanProperty.of("down");
		private static final Map<Direction, BooleanProperty> CONNECTIONS = Map.of(
				Direction.NORTH, NORTH,
				Direction.EAST, EAST,
				Direction.SOUTH, SOUTH,
				Direction.WEST, WEST,
				Direction.UP, UP,
				Direction.DOWN, DOWN
		);

		private ConnectedGlassBlock(AbstractBlock.Settings settings) {
			super(settings);
			setDefaultState(getDefaultState()
					.with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false)
					.with(UP, false).with(DOWN, false));
		}

		@Override
		public BlockState getPlacementState(ItemPlacementContext context) {
			BlockPos pos = context.getBlockPos();
			return getDefaultState()
					.with(NORTH, context.getWorld().getBlockState(pos.north()).isOf(this))
					.with(EAST, context.getWorld().getBlockState(pos.east()).isOf(this))
					.with(SOUTH, context.getWorld().getBlockState(pos.south()).isOf(this))
					.with(WEST, context.getWorld().getBlockState(pos.west()).isOf(this))
					.with(UP, context.getWorld().getBlockState(pos.up()).isOf(this))
					.with(DOWN, context.getWorld().getBlockState(pos.down()).isOf(this));
		}

		@Override
		public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
				WorldAccess world, BlockPos pos, BlockPos neighborPos) {
			BooleanProperty property = CONNECTIONS.get(direction);
			return property == null ? state : state.with(property, neighborState.isOf(this));
		}

		@Override
		protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
			builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
		}
	}

	public static void registerModBlocks() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
			entries.add(DISTORTED_SAND_ITEM);
			entries.add(BLIGHTED_GRASS_BLOCK_ITEM);
			entries.add(WITHERED_DIRT_ITEM);
			entries.add(WITHERED_FARMLAND_ITEM);
			entries.add(DISTORTED_NETHERRACK_ITEM);
			entries.add(DISTORTED_BASALT_ITEM);
			entries.add(DISTORTED_NETHERRACK_BRICKS_ITEM);
			entries.add(CHISELED_DISTORTED_NETHERRACK_BRICKS_ITEM);
			entries.add(CRACKED_DISTORTED_NETHERRACK_BRICKS_ITEM);
			entries.add(DISTORTED_SANDSTONE_ITEM);
			entries.add(CUT_DISTORTED_SANDSTONE_ITEM);
			entries.add(SMOOTH_DISTORTED_SANDSTONE_ITEM);
			entries.add(CHISELED_DISTORTED_SANDSTONE_ITEM);
			entries.add(WITHERED_MUD_ITEM);
			entries.add(WITHERED_MUD_BRICKS_ITEM);
			entries.add(CRACKED_WITHERED_MUD_BRICKS_ITEM);
			entries.add(CHISELED_WITHERED_MUD_BRICKS_ITEM);
			entries.add(POLISHED_DISTORTED_BASALT_ITEM);
			entries.add(SMOOTH_DISTORTED_BASALT_ITEM);
			entries.add(CORRODED_STONE_ITEM);
			entries.add(COBBLED_CORRODED_STONE_ITEM);
			entries.add(POLISHED_CORRODED_STONE_ITEM);
			entries.add(CORRODED_STONE_BRICKS_ITEM);
			entries.add(CRACKED_CORRODED_STONE_BRICKS_ITEM);
			entries.add(CHISELED_CORRODED_STONE_BRICKS_ITEM);
			entries.add(CORRODED_COAL_ORE_ITEM);
			entries.add(CORRODED_COPPER_ORE_ITEM);
			entries.add(CORRODED_IRON_ORE_ITEM);
			entries.add(CORRODED_GOLD_ORE_ITEM);
			entries.add(CORRODED_REDSTONE_ORE_ITEM);
			entries.add(CORRODED_LAPIS_ORE_ITEM);
			entries.add(CORRODED_DIAMOND_ORE_ITEM);
			entries.add(CORRODED_EMERALD_ORE_ITEM);
			entries.add(CORRODED_NETHER_QUARTZ_ORE_ITEM);
			ADDITIONAL_VARIANT_ITEMS.forEach(entries::add);
		});
		VTMon.LOGGER.info("Registering Mod Blocks for " + VTMon.MOD_ID);
	}
}
