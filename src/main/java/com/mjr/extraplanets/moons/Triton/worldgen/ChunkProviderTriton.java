package com.mjr.extraplanets.moons.Triton.worldgen;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.blocks.fluid.ExtraPlanets_Fluids;
import com.mjr.extraplanets.blocks.planetAndMoonBlocks.BlockBasicTriton;
import com.mjr.mjrlegendslib.world.ChunkProviderMultiBiomeSpace;
import com.mjr.mjrlegendslib.world.gen.MapGenBaseMeta;
import com.mjr.mjrlegendslib.world.gen.MapGenCaveGen;
import com.mjr.mjrlegendslib.world.gen.MapGenRavineGen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkProviderTriton extends ChunkProviderMultiBiomeSpace {
	private final BiomeDecoratorTriton ceresBiomeDecorator = new BiomeDecoratorTriton();
	private final BiomeDecoratorTritonOther ceresBiomeDecorator2 = new BiomeDecoratorTritonOther();
	private final MapGenRavineGen ravineGenerator = new MapGenRavineGen();
	private final MapGenCaveGen caveGenerator = new MapGenCaveGen(ExtraPlanets_Blocks.TRITON_BLOCKS, 0, 1, 2);

	public ChunkProviderTriton(World par1World, long seed, boolean mapFeaturesEnabled) {
		super(par1World, seed, mapFeaturesEnabled);
		this.stoneBlock = ExtraPlanets_Blocks.TRITON_BLOCKS.getDefaultState().withProperty(BlockBasicTriton.BASIC_TYPE, BlockBasicTriton.EnumBlockBasic.STONE);
		this.waterBlock = ExtraPlanets_Fluids.NITROGEN_ICE.getDefaultState();
		this.seaIceLayer = true;
	}

	@Override
	protected List<MapGenBaseMeta> getWorldGenerators() {
		List<MapGenBaseMeta> generators = Lists.newArrayList();
		generators.add(this.caveGenerator);
		return generators;
	}

	@Override
	public int getCraterProbability() {
		return 0;
	}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {
	}

	@Override
	protected void decoratePlanet(World world, Random rand, int x, int z) {
		this.ceresBiomeDecorator.decorate(world, rand, x, z);
		this.ceresBiomeDecorator2.decorate(world, rand, null, new BlockPos(x, 0, z));
	}

	@Override
	protected void onChunkProvide(int cX, int cZ, ChunkPrimer primer) {
		this.ravineGenerator.generate(this.worldObj, cX, cZ, primer);
	}

	@Override
	public void onPopulate(int cX, int cZ) {
	}
}
