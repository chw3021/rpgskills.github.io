package io.github.chw3021.monsters.worldgen;

import org.bukkit.generator.ChunkGenerator;

public class IllChunkGenerator extends ChunkGenerator {

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
    
}
