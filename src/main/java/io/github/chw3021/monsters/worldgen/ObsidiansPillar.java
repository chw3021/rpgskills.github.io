package io.github.chw3021.monsters.worldgen;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class ObsidiansPillar extends BlockPopulator {
	/*@Override
	public void populate(WorldInfo world, Random random, int chunkX, int chunkZ, LimitedRegion lr) {
		if (random.nextBoolean()) {
		    int amount = random.nextInt(5)+1;  // Amount of trees
		    for (int i = 1; i < amount; i++) {
		        int Y = 100;
		        //for (Y = world.getMaxHeight()-1; limitedRegion.getBlockState(chunkX, Y, chunkZ).getType() == Material.AIR; Y--);
		        if(lr.isInRegion(chunkX, Y, chunkZ)) {
			        lr.getBlockState(chunkX, Y, chunkZ).setType(Material.GRASS_BLOCK);	
			        lr.generateTree(lr.getBlockState(chunkX, Y+1, chunkZ).getLocation(), random, TreeType.JUNGLE);
		        }
		    }
		}
	}*/
	
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		if (random.nextBoolean()) {
		    int amount = random.nextInt(3)+1;  // Amount of trees
		    for (int i = 1; i < amount; i++) {
		        int X = random.nextInt(15);
		        int Z = random.nextInt(15);
		        int Y = 98;
		        for(int yi = 1; yi<5;yi++) {
			        chunk.getBlock(X, Y+yi, Z).setType(Material.OBSIDIAN);
		        }
		        
		    }
		}
	}
}
