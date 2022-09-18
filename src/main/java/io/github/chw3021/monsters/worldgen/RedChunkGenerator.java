package io.github.chw3021.monsters.worldgen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.jetbrains.annotations.NotNull;

public class RedChunkGenerator extends ChunkGenerator {

    int currentHeight = 4;
    @Override
    @NotNull
    public ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int chunkX, int chunkZ, @NotNull BiomeGrid biome) {
    	ChunkData chunk = createChunkData(world);
    	SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
    	generator.setScale(0.005D);
    	for (int x = 0; x < 16; x++) {
    	    for (int z = 0; z < 16; z++) {
    	    	currentHeight = 6;
                chunk.setBlock(x, currentHeight, z, Material.RED_TERRACOTTA);
                chunk.setBlock(x, currentHeight-1, z, Material.BLACK_GLAZED_TERRACOTTA);
                biome.setBiome(x, currentHeight, z, Biome.BADLANDS);
                for (int i = currentHeight-2; i > 0; i--)
                    chunk.setBlock(x, i, z, Material.BLACK_GLAZED_TERRACOTTA);
                chunk.setBlock(x, 0, z, Material.BEDROCK);
    	    }
    	}
        return chunk;
    }
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList((BlockPopulator)new RedTrees());
    }
    
}
