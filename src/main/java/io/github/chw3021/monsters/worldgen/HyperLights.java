package io.github.chw3021.monsters.worldgen;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class HyperLights extends BlockPopulator {
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		if (random.nextBoolean()) {
		    int amount = random.nextInt(5)+2;  // Amount of trees
		    for (int i = 1; i < amount; i++) {
		        int X = random.nextInt(15);
		        int Z = random.nextInt(15);
		        int Y = 14;
		        chunk.getBlock(X, Y, Z).setType(Material.SHROOMLIGHT);	
		        chunk.getBlock(X, 2, Z).setType(Material.SHROOMLIGHT);	
		    }
		}
	}
}
