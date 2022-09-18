package io.github.chw3021.monsters.worldgen;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class SnowTrees extends BlockPopulator {
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		if (random.nextBoolean()) {
		    int amount = random.nextInt(3)+1;  // Amount of trees
		    for (int i = 1; i < amount; i++) {
		        int X = random.nextInt(15);
		        int Z = random.nextInt(15);
		        int Y;
		        for (Y = world.getMaxHeight()-1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--);// Find the highest block of the (X,Z) coordinate chosen.
		        chunk.getBlock(X, Y, Z).setType(Material.GRASS_BLOCK);	
		        world.generateTree(chunk.getBlock(X, Y+1, Z).getLocation(), TreeType.REDWOOD);
		    }
		}
	}
}
