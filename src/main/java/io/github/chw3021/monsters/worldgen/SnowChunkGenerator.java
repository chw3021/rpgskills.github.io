package io.github.chw3021.monsters.worldgen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

public class SnowChunkGenerator extends ChunkGenerator {

    int currentHeight = 4;
    @Override
    @NotNull
    public ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int chunkX, int chunkZ, @NotNull BiomeGrid biome) {
    	ChunkData chunk = createChunkData(world);
    	for (int x = 0; x < 16; x++) {
    	    for (int z = 0; z < 16; z++) {
    	    	currentHeight = 6;
                chunk.setBlock(x, currentHeight, z, Material.SNOW_BLOCK);
                chunk.setBlock(x, currentHeight-1, z, Material.SNOW_BLOCK);
                biome.setBiome(x, currentHeight-1, z, Biome.SNOWY_SLOPES);
                biome.setBiome(x, currentHeight+2, z, Biome.SNOWY_SLOPES);
                biome.setBiome(x, currentHeight, z, Biome.SNOWY_SLOPES);
                biome.setBiome(x, currentHeight+1, z, Biome.SNOWY_SLOPES);
                for (int i = currentHeight-2; i > 0; i--)
                chunk.setBlock(x, i, z, Material.SNOW_BLOCK);
                chunk.setBlock(x, 0, z, Material.BEDROCK);
    	    }
    	}
        return chunk;
    }
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList((BlockPopulator)new SnowTrees());
    }
    
}
