package io.github.chw3021.monsters;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.inventory.ItemStack;
import io.github.chw3021.items.Elements;



public class Drops implements Listener {


	Integer CHANCE = 1;
	
	@EventHandler
	public void ItemDrops(EntityDropItemEvent d) 
	{

		if(d.getEntity().hasMetadata("raid")||d.getEntity().hasMetadata("summoned")) {
			d.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void ItemDrops(EntityDeathEvent d) 
	{

		if(d.getEntity().hasMetadata("raid")||d.getEntity().hasMetadata("summoned")) {
			d.getDrops().clear();
			d.setDroppedExp(0);
		}
		if(d.getEntity().getKiller() != null) {
			Player p = d.getEntity().getKiller();
			if(d.getEntity().hasMetadata("treasurepig")) {
				LivingEntity le = d.getEntity();
				ItemStack i6 = new ItemStack(Material.GOLD_BLOCK,1);
				ItemStack ii = new ItemStack(Material.DIAMOND_BLOCK,1);
				ItemStack i2 = Elements.getscroll(p);
				ItemStack i3 = new ItemStack(Material.NETHERITE_INGOT,2);
				ItemStack i4 = new ItemStack(Material.LAPIS_BLOCK,1);
				ItemStack i5 = new ItemStack(Material.EMERALD_BLOCK,1);
    			le.getWorld().dropItemNaturally(le.getLocation(), ii);
    			le.getWorld().dropItemNaturally(le.getLocation(), i2);
    			le.getWorld().dropItemNaturally(le.getLocation(), i3);
    			le.getWorld().dropItemNaturally(le.getLocation(), i4);
    			le.getWorld().dropItemNaturally(le.getLocation(), i5);
    			le.getWorld().dropItemNaturally(le.getLocation(), i6);
    			d.setDroppedExp(200);
				
			}
			/*if(d.getEntity().hasMetadata("treasureraid")) {
				LivingEntity le = d.getEntity();
				ItemStack i2 = new ItemStack(Material.EXPERIENCE_BOTTLE,1);
				ItemStack i4 = new ItemStack(Material.LAPIS_LAZULI,1);
				ItemStack i5 = new ItemStack(Material.EMERALD,1);
				ItemStack i3 = new ItemStack(Material.NETHERITE_SCRAP,1);
    			le.getWorld().dropItemNaturally(le.getLocation(), i2);
    			le.getWorld().dropItemNaturally(le.getLocation(), i4);
    			le.getWorld().dropItemNaturally(le.getLocation(), i3);
    			le.getWorld().dropItemNaturally(le.getLocation(), i5);
			}*/
			if(d.getEntity().hasMetadata("plain")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(14, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("mountains")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(5, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("snowy")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(6,p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("ocean")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(7, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("dark")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(8, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("hyper")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(9, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("red")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(10, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("poison")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(11, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}

			if(d.getEntity().hasMetadata("wild")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(12, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("soul")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(-2, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}	
			if(d.getEntity().hasMetadata("crimson")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(-3, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}	
			if(d.getEntity().hasMetadata("warped")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(-4, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}	
			if(d.getEntity().hasMetadata("volcanic")) {
				LivingEntity le = d.getEntity();
				ItemStack ii = Elements.getel(-5, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}	

			if(d.getEntity().hasMetadata("ender")) {
				LivingEntity le = d.getEntity();

				ItemStack ii = Elements.getel(-6, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
				
			}
			if(d.getEntity().hasMetadata("void")) {
				LivingEntity le = d.getEntity();

				ItemStack ii = Elements.getel(-7, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
				
			}
			if(d.getEntity().hasMetadata("wither")) {
				LivingEntity le = d.getEntity();

				ItemStack ii = Elements.getel(-8, p);
				ii.setAmount(1);

    			le.getWorld().dropItem(le.getLocation(), ii);
	            if(le.hasMetadata("boss")) {
	            	ii.setAmount(5);
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	            }
			}
		}
	}
	

}
