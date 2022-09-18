package io.github.chw3021.monsters.worldgen;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

public class OceanChunkGenerator extends ChunkGenerator {

    @Override
    @NotNull
    public ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int chunkX, int chunkZ, @NotNull BiomeGrid biome) {
    	ChunkData chunk = createChunkData(world);
    	for (int x = 0; x < 16; x++) {
    	    for (int z = 0; z < 16; z++) {
                for (int i = 16; i > 2; i--) {
                    chunk.setBlock(x, i, z, Material.WATER);
                    biome.setBiome(x, i, z, Biome.DEEP_OCEAN);
                }
                chunk.setBlock(x, 1, z, Material.PRISMARINE_BRICKS);
                chunk.setBlock(x, 0, z, Material.BEDROCK);
    	    }
    	}
        return chunk;
    }
    
}
