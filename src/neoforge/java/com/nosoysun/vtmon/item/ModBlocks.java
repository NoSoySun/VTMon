package com.nosoysun.vtmon.item;

import com.nosoysun.vtmon.VTMon;
import com.nosoysun.vtmon.world.InterworldPortalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.valueproviders.UniformInt;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ModBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(VTMon.MOD_ID);

	public static final DeferredBlock<Block> DISTORTED_SAND = BLOCKS.register(
			"distorted_sand",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND))
	);

	public static final DeferredBlock<Block> BLIGHTED_GRASS_BLOCK = BLOCKS.register(
			"blighted_grass_block",
			() -> new BlightedGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK))
	);
	public static final DeferredBlock<Block> WITHERED_DIRT = BLOCKS.register(
			"withered_dirt",
			() -> new WitheredDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT))
	);
	public static final DeferredBlock<FarmBlock> WITHERED_FARMLAND = BLOCKS.register(
			"withered_farmland",
			() -> new WitheredFarmlandBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FARMLAND))
	);

	public static final DeferredBlock<Block> DISTORTED_NETHERRACK = BLOCKS.register(
			"distorted_netherrack",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK))
	);

	public static final DeferredBlock<Block> DISTORTED_BASALT = BLOCKS.register(
			"distorted_basalt",
			() -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT))
	);

	public static final DeferredBlock<Block> DISTORTED_NETHERRACK_BRICKS = BLOCKS.register(
			"distorted_netherrack_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK))
	);
	public static final DeferredBlock<Block> CHISELED_DISTORTED_NETHERRACK_BRICKS = BLOCKS.register(
			"chiseled_distorted_netherrack_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_NETHER_BRICKS))
	);
	public static final DeferredBlock<Block> CRACKED_DISTORTED_NETHERRACK_BRICKS = BLOCKS.register(
			"cracked_distorted_netherrack_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_NETHER_BRICKS))
	);
	public static final DeferredBlock<Block> DISTORTED_SANDSTONE = BLOCKS.register(
			"distorted_sandstone",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE))
	);
	public static final DeferredBlock<Block> CUT_DISTORTED_SANDSTONE = BLOCKS.register(
			"cut_distorted_sandstone",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE))
	);
	public static final DeferredBlock<Block> SMOOTH_DISTORTED_SANDSTONE = BLOCKS.register(
			"smooth_distorted_sandstone",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE))
	);
	public static final DeferredBlock<Block> CHISELED_DISTORTED_SANDSTONE = BLOCKS.register(
			"chiseled_distorted_sandstone",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_SANDSTONE))
	);
	public static final DeferredBlock<Block> WITHERED_MUD = BLOCKS.register(
			"withered_mud",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD))
	);
	public static final DeferredBlock<Block> WITHERED_MUD_BRICKS = BLOCKS.register(
			"withered_mud_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS))
	);
	public static final DeferredBlock<Block> CRACKED_WITHERED_MUD_BRICKS = BLOCKS.register(
			"cracked_withered_mud_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS))
	);
	public static final DeferredBlock<Block> CHISELED_WITHERED_MUD_BRICKS = BLOCKS.register(
			"chiseled_withered_mud_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS))
	);
	public static final DeferredBlock<Block> POLISHED_DISTORTED_BASALT = BLOCKS.register(
			"polished_distorted_basalt",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BASALT))
	);
	public static final DeferredBlock<Block> SMOOTH_DISTORTED_BASALT = BLOCKS.register(
			"smooth_distorted_basalt",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_BASALT))
	);
	public static final DeferredBlock<Block> CORRODED_STONE = BLOCKS.register(
			"corroded_stone",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE))
	);
	public static final DeferredBlock<Block> COBBLED_CORRODED_STONE = BLOCKS.register(
			"cobbled_corroded_stone",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE))
	);
	public static final DeferredBlock<Block> POLISHED_CORRODED_STONE = BLOCKS.register(
			"polished_corroded_stone",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE))
	);
	public static final DeferredBlock<Block> CORRODED_STONE_BRICKS = BLOCKS.register(
			"corroded_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS))
	);
	public static final DeferredBlock<Block> CRACKED_CORRODED_STONE_BRICKS = BLOCKS.register(
			"cracked_corroded_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS))
	);
	public static final DeferredBlock<Block> CHISELED_CORRODED_STONE_BRICKS = BLOCKS.register(
			"chiseled_corroded_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS))
	);
	public static final DeferredBlock<Block> CORRODED_COAL_ORE = registerCorrodedOre(
			"corroded_coal_ore", Blocks.COAL_ORE, UniformInt.of(0, 2)
	);
	public static final DeferredBlock<Block> CORRODED_COPPER_ORE = registerCorrodedOre(
			"corroded_copper_ore", Blocks.COPPER_ORE, UniformInt.of(0, 0)
	);
	public static final DeferredBlock<Block> CORRODED_IRON_ORE = registerCorrodedOre(
			"corroded_iron_ore", Blocks.IRON_ORE, UniformInt.of(0, 0)
	);
	public static final DeferredBlock<Block> CORRODED_GOLD_ORE = registerCorrodedOre(
			"corroded_gold_ore", Blocks.GOLD_ORE, UniformInt.of(0, 0)
	);
	public static final DeferredBlock<Block> CORRODED_REDSTONE_ORE = registerCorrodedOre(
			"corroded_redstone_ore", Blocks.DIAMOND_ORE, UniformInt.of(1, 5)
	);
	public static final DeferredBlock<Block> CORRODED_LAPIS_ORE = registerCorrodedOre(
			"corroded_lapis_ore", Blocks.LAPIS_ORE, UniformInt.of(2, 5)
	);
	public static final DeferredBlock<Block> CORRODED_DIAMOND_ORE = registerCorrodedOre(
			"corroded_diamond_ore", Blocks.DIAMOND_ORE, UniformInt.of(3, 7)
	);
	public static final DeferredBlock<Block> CORRODED_EMERALD_ORE = registerCorrodedOre(
			"corroded_emerald_ore", Blocks.EMERALD_ORE, UniformInt.of(3, 7)
	);
	public static final DeferredBlock<Block> CORRODED_NETHER_QUARTZ_ORE = registerCorrodedOre(
			"corroded_nether_quartz_ore", Blocks.NETHER_QUARTZ_ORE, UniformInt.of(2, 5)
	);

	public static final List<DeferredBlock<Block>> ADDITIONAL_VARIANT_BLOCKS = new ArrayList<>();
	public static final DeferredBlock<Block> DISTORTED_GLASS = registerVariant("distorted_glass", () -> new ConnectedGlassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).lightLevel(state -> 14)));
	public static final DeferredBlock<Block> DISTORTED_GLASS_PANE = registerVariant("distorted_glass_pane", () -> new InterworldPaneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE).lightLevel(state -> 14)));
	public static final DeferredBlock<Block> DISTORTED_NETHERRACK_BRICK_STAIRS = registerVariant("distorted_netherrack_brick_stairs", () -> new InterworldStairsBlock(DISTORTED_NETHERRACK_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_STAIRS)));
	public static final DeferredBlock<Block> DISTORTED_NETHERRACK_BRICK_SLAB = registerVariant("distorted_netherrack_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB)));
	public static final DeferredBlock<Block> DISTORTED_NETHERRACK_BRICK_WALL = registerVariant("distorted_netherrack_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_WALL)));
	public static final DeferredBlock<Block> DISTORTED_NETHERRACK_BRICK_FENCE = registerVariant("distorted_netherrack_brick_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_FENCE)));
	public static final DeferredBlock<Block> DISTORTED_NETHERRACK_BRICK_TILES = registerVariant("distorted_netherrack_brick_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DISTORTED_NETHERRACK_BRICKS.get())));
	public static final DeferredBlock<Block> DISTORTED_SANDSTONE_STAIRS = registerVariant("distorted_sandstone_stairs", () -> new InterworldStairsBlock(DISTORTED_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_STAIRS)));
	public static final DeferredBlock<Block> DISTORTED_SANDSTONE_SLAB = registerVariant("distorted_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB)));
	public static final DeferredBlock<Block> DISTORTED_SANDSTONE_WALL = registerVariant("distorted_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
	public static final DeferredBlock<Block> DISTORTED_SANDSTONE_TILES = registerVariant("distorted_sandstone_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DISTORTED_SANDSTONE.get())));
	public static final DeferredBlock<Block> CUT_DISTORTED_SANDSTONE_SLAB = registerVariant("cut_distorted_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE_SLAB)));
	public static final DeferredBlock<Block> SMOOTH_DISTORTED_SANDSTONE_STAIRS = registerVariant("smooth_distorted_sandstone_stairs", () -> new InterworldStairsBlock(SMOOTH_DISTORTED_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_STAIRS)));
	public static final DeferredBlock<Block> SMOOTH_DISTORTED_SANDSTONE_SLAB = registerVariant("smooth_distorted_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE_SLAB)));
	public static final DeferredBlock<Block> WITHERED_MUD_BRICK_STAIRS = registerVariant("withered_mud_brick_stairs", () -> new InterworldStairsBlock(WITHERED_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICK_STAIRS)));
	public static final DeferredBlock<Block> WITHERED_MUD_BRICK_SLAB = registerVariant("withered_mud_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICK_SLAB)));
	public static final DeferredBlock<Block> WITHERED_MUD_BRICK_WALL = registerVariant("withered_mud_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICK_WALL)));
	public static final DeferredBlock<Block> WITHERED_MUD_BRICK_FENCE = registerVariant("withered_mud_brick_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_FENCE)));
	public static final DeferredBlock<Block> WITHERED_MUD_BRICK_TILES = registerVariant("withered_mud_brick_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(WITHERED_MUD_BRICKS.get())));
	public static final DeferredBlock<Block> CORRODED_STONE_BRICK_STAIRS = registerVariant("corroded_stone_brick_stairs", () -> new InterworldStairsBlock(CORRODED_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS)));
	public static final DeferredBlock<Block> CORRODED_STONE_BRICK_SLAB = registerVariant("corroded_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB)));
	public static final DeferredBlock<Block> CORRODED_STONE_BRICK_WALL = registerVariant("corroded_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
	public static final DeferredBlock<Block> CORRODED_STONE_BRICK_FENCE = registerVariant("corroded_stone_brick_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_FENCE)));
	public static final DeferredBlock<Block> CORRODED_STONE_BRICK_TILES = registerVariant("corroded_stone_brick_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CORRODED_STONE_BRICKS.get())));
	public static final DeferredBlock<Block> POLISHED_CORRODED_STONE_STAIRS = registerVariant("polished_corroded_stone_stairs", () -> new InterworldStairsBlock(POLISHED_CORRODED_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE_STAIRS)));
	public static final DeferredBlock<Block> POLISHED_CORRODED_STONE_SLAB = registerVariant("polished_corroded_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE_SLAB)));
	public static final DeferredBlock<Block> POLISHED_CORRODED_STONE_WALL = registerVariant("polished_corroded_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_WALL)));
	public static final DeferredBlock<Block> POLISHED_DISTORTED_BASALT_STAIRS = registerVariant("polished_distorted_basalt_stairs", () -> new InterworldStairsBlock(POLISHED_DISTORTED_BASALT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS)));
	public static final DeferredBlock<Block> POLISHED_DISTORTED_BASALT_SLAB = registerVariant("polished_distorted_basalt_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB)));
	public static final DeferredBlock<Block> POLISHED_DISTORTED_BASALT_WALL = registerVariant("polished_distorted_basalt_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
	public static final DeferredBlock<Block> SMOOTH_DISTORTED_BASALT_STAIRS = registerVariant("smooth_distorted_basalt_stairs", () -> new InterworldStairsBlock(SMOOTH_DISTORTED_BASALT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS)));
	public static final DeferredBlock<Block> SMOOTH_DISTORTED_BASALT_SLAB = registerVariant("smooth_distorted_basalt_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB)));

	public static final DeferredBlock<Block> INTERWORLD_PORTAL = BLOCKS.register(
			"interworld_portal",
			() -> new InterworldPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_STAINED_GLASS)
					.noOcclusion()
					.strength(-1.0F))
	);

	private ModBlocks() {
	}

	private static DeferredBlock<Block> registerCorrodedOre(String name, Block vanillaOre, UniformInt experience) {
		return BLOCKS.register(name, () -> new DropExperienceBlock(
				experience,
				BlockBehaviour.Properties.ofFullCopy(vanillaOre)
		));
	}

	private static DeferredBlock<Block> registerVariant(String name, Supplier<Block> factory) {
		DeferredBlock<Block> block = BLOCKS.register(name, factory);
		ADDITIONAL_VARIANT_BLOCKS.add(block);
		return block;
	}

	private static final class InterworldStairsBlock extends StairBlock {
		private InterworldStairsBlock(BlockState baseState, BlockBehaviour.Properties properties) {
			super(baseState, properties);
		}
	}

	private static final class InterworldPaneBlock extends IronBarsBlock {
		private InterworldPaneBlock(BlockBehaviour.Properties properties) {
			super(properties);
		}
	}

	private static final class ConnectedGlassBlock extends TransparentBlock {
		private static final BooleanProperty NORTH = BooleanProperty.create("north");
		private static final BooleanProperty EAST = BooleanProperty.create("east");
		private static final BooleanProperty SOUTH = BooleanProperty.create("south");
		private static final BooleanProperty WEST = BooleanProperty.create("west");
		private static final BooleanProperty UP = BooleanProperty.create("up");
		private static final BooleanProperty DOWN = BooleanProperty.create("down");
		private static final Map<Direction, BooleanProperty> CONNECTIONS = Map.of(
				Direction.NORTH, NORTH,
				Direction.EAST, EAST,
				Direction.SOUTH, SOUTH,
				Direction.WEST, WEST,
				Direction.UP, UP,
				Direction.DOWN, DOWN
		);

		private ConnectedGlassBlock(BlockBehaviour.Properties properties) {
			super(properties);
			registerDefaultState(defaultBlockState()
					.setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false)
					.setValue(UP, false).setValue(DOWN, false));
		}

		@Override
		public BlockState getStateForPlacement(BlockPlaceContext context) {
			BlockPos pos = context.getClickedPos();
			return defaultBlockState()
					.setValue(NORTH, context.getLevel().getBlockState(pos.north()).is(this))
					.setValue(EAST, context.getLevel().getBlockState(pos.east()).is(this))
					.setValue(SOUTH, context.getLevel().getBlockState(pos.south()).is(this))
					.setValue(WEST, context.getLevel().getBlockState(pos.west()).is(this))
					.setValue(UP, context.getLevel().getBlockState(pos.above()).is(this))
					.setValue(DOWN, context.getLevel().getBlockState(pos.below()).is(this));
		}

		@Override
		protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
				LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
			BooleanProperty property = CONNECTIONS.get(direction);
			return property == null ? state : state.setValue(property, neighborState.is(this));
		}

		@Override
		protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
			builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
		}
	}

	public static void register(IEventBus modEventBus) {
		BLOCKS.register(modEventBus);
		VTMon.LOGGER.info("Registering Mod Blocks for " + VTMon.MOD_ID);
	}
}
