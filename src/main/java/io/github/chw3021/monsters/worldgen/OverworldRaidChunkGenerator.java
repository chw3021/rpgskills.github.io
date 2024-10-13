package io.github.chw3021.monsters.worldgen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.jetbrains.annotations.NotNull;

public class OverworldRaidChunkGenerator extends ChunkGenerator {
	@Override
	public void generateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {
	    SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(worldInfo.getSeed()), 6);
	    generator.setScale(0.008);

	    Material material = Material.CRACKED_STONE_BRICKS;

	    int worldX = chunkX * 16;
	    int worldZ = chunkZ * 16;

	    for (int x = 0; x < 16; x++) {
	        for (int z = 0; z < 16; z++) {
	            double noise = generator.noise(worldX + x, worldZ + z, 0.5, 0.5, true);
	            int height =98 + (int)(noise * 4); // 노이즈 기반의 동적 높이

	            for (int y = 42; y < height; y++) {
	                chunkData.setBlock(x, y, z, material);
	            }
	            for (int y = chunkData.getMinHeight() + 2; y < 42; y++) {
	                chunkData.setBlock(x, y, z, Material.WATER);
	            }
	        }
	    }
	}

    @Override
    public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {
        if (chunkData.getMinHeight() == worldInfo.getMinHeight()) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    chunkData.setBlock(x, chunkData.getMinHeight(), z, Material.BEDROCK);
                }
            }
        }
    }

    @Override
    public boolean shouldGenerateSurface() {
        return false;
    }

    @Override
    public boolean shouldGenerateCaves() {
        return false;
    }

    @Override
    public boolean shouldGenerateMobs() {
        return false;
    }

    @Override
    public boolean shouldGenerateDecorations() {
        return false;
    }

    @Override
    public boolean shouldGenerateStructures() {
        return false;
    }
    
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList((BlockPopulator)new MountainsTrees());
    }
    
}
