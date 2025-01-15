package io.github.chw3021.monsters;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import io.github.chw3021.items.Elements;
import io.github.chw3021.rmain.RMain;



public class Drops implements Listener {


	Integer CHANCE = 1;

	@EventHandler
	public void ItemDrops(ItemSpawnEvent d) 
	{

		for(Entity e : d.getEntity().getNearbyEntities(2, 2, 2)) {
			if (e instanceof Item item) {
				item.setVelocity(new Vector(0,0,0));
				Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {
        				item.setVelocity(new Vector(0,0,0));
                    }
				}, 20);
			}
		}
	}
	@EventHandler
	public void ItemDrops(EntityChangeBlockEvent d) 
	{

		for(Entity e : d.getEntity().getNearbyEntities(2, 2, 2)) {
			if (e instanceof Item item) {
				item.setVelocity(new Vector(0,0,0));
				Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {
        				item.setVelocity(new Vector(0,0,0));
                    }
				}, 20);
			}
		}
	}
	
	@EventHandler
	public void ItemDrops(EntityDropItemEvent d) 
	{

		for(Entity e : d.getItemDrop().getNearbyEntities(2, 2, 2)) {
			if (e instanceof Item item) {
				item.setVelocity(new Vector(0,0,0));
				Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {
        				item.setVelocity(new Vector(0,0,0));
                    }
				}, 20);
			}
		}
		if(d.getEntity().hasMetadata("raid")||d.getEntity().hasMetadata("summoned")) {
			d.setCancelled(true);
		}
	}
	
	@EventHandler
	public void ItemDrops(EntityPickupItemEvent d) 
    {
		LivingEntity le = d.getEntity();
		d.getItem().setVelocity(new Vector(0,0,0));
		if(d.isCancelled()) {
			Item item = d.getItem();
			item.setVelocity(new Vector(0,0,0));
			Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
    				item.setVelocity(new Vector(0,0,0));
                }
			}, 20);
		}

		for(Entity e : le.getNearbyEntities(2, 2, 2)) {
			if (e instanceof Item item) {
				item.setVelocity(new Vector(0,0,0));
				Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {
        				item.setVelocity(new Vector(0,0,0));
                    }
				}, 20);
			}
		}
    }
	
	@EventHandler
	public void ItemDrops(EntityDeathEvent d) 
	{
		LivingEntity le = d.getEntity();
		for(Entity e : le.getNearbyEntities(2, 2, 2)) {
			if (e instanceof Item item) {
				item.setVelocity(new Vector(0,0,0));
				Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {
        				item.setVelocity(new Vector(0,0,0));
                    }
				}, 20);
			}
		}
		
		if(d.getEntity().hasMetadata("raid")||d.getEntity().hasMetadata("summoned")) {
			d.getDrops().clear();
			d.setDroppedExp(0);
			return;
		}
		if(d.getEntity().getKiller() != null) {
			Player p = d.getEntity().getKiller();
			if(d.getEntity().hasMetadata("treasurepig")) {
				
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
			if(d.getEntity().hasMetadata("plain")) {
				
				ItemStack ii = Elements.getel(14, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("mountains")) {
				
				ItemStack ii = Elements.getel(5, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("snowy")) {
				
				ItemStack ii = Elements.getel(6,p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("ocean")) {
				
				ItemStack ii = Elements.getel(7, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("dark")) {
				
				ItemStack ii = Elements.getel(8, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("hyper")) {
				
				ItemStack ii = Elements.getel(9, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("red")) {
				
				ItemStack ii = Elements.getel(10, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("poison")) {
				
				ItemStack ii = Elements.getel(11, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}

			if(d.getEntity().hasMetadata("wild")) {
				
				ItemStack ii = Elements.getel(12, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}
			if(d.getEntity().hasMetadata("soul")) {
				
				ItemStack ii = Elements.getel(-2, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}	
			if(d.getEntity().hasMetadata("crimson")) {
				
				ItemStack ii = Elements.getel(-3, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}	
			if(d.getEntity().hasMetadata("warped")) {
				
				ItemStack ii = Elements.getel(-4, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}	
			if(d.getEntity().hasMetadata("volcanic")) {
				
				ItemStack ii = Elements.getel(-5, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
			}	

			if(d.getEntity().hasMetadata("ender")) {
				

				ItemStack ii = Elements.getel(-6, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
				
			}
			if(d.getEntity().hasMetadata("void")) {
				

				ItemStack ii = Elements.getel(-7, p);
				ii.setAmount(1);

	    		Random random=new Random();
	        	int ri = random.nextInt(100);
	        	if(ri <= CHANCE) {
	    			le.getWorld().dropItem(le.getLocation(), ii);
	    			
	        	}
				
			}
			if(d.getEntity().hasMetadata("wither")) {
//				
//
//				ItemStack ii = Elements.getel(-8, p);
//				ii.setAmount(1);
//
//    			le.getWorld().dropItem(le.getLocation(), ii);
//	            if(le.hasMetadata("boss")) {
//	            	ii.setAmount(5);
//	    			le.getWorld().dropItem(le.getLocation(), ii);
//	    			
//	            }
			}
		}
	}
	

}
