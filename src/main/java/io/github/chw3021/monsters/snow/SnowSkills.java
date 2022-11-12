package io.github.chw3021.monsters.snow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Stray;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Witch;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;

public class SnowSkills extends Summoned implements Listener{

	/**
	 * 
	 */
	private transient static final long serialVersionUID = -7048376562198783817L;
	Holding hold = Holding.getInstance();
	public HashMap<UUID, Long> rb1cooldown = new HashMap<UUID, Long>();
	public HashMap<UUID, Long> rb2cooldown = new HashMap<UUID, Long>();
	public HashMap<UUID, Long> brzc = new HashMap<UUID, Long>();
	public HashMap<UUID, Long> hunt = new HashMap<UUID, Long>();
	public HashMap<UUID, Long> icefall = new HashMap<UUID, Long>();
	public HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	public HashMap<UUID, Long> rb7cooldown = new HashMap<UUID, Long>();
	
	public static HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, UUID> ordeal = new HashMap<UUID, UUID>();
	public static HashMap<UUID, Integer> count = new HashMap<UUID, Integer>();

	private HashMap<UUID, LivingEntity> snowblock = new HashMap<UUID, LivingEntity>();
	

	private static final SnowSkills instance = new SnowSkills ();
	public static SnowSkills getInstance()
	{
		return instance;
	}

	
	public void Potion(ProjectileHitEvent d) 
	{
	    
		if(d.getEntity() instanceof ThrownPotion) 
		{
			ThrownPotion po = (ThrownPotion)d.getEntity();
			if(po.getShooter() instanceof Witch) {
				Witch p = (Witch) po.getShooter();
				if(ordeal.containsKey(p.getUniqueId())) {
					return;
				}
				if(p.hasMetadata("snowy")) {
					for(int d1=0; d1<6; d1++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.getWorld().spawnParticle(Particle.BLOCK_CRACK,po.getLocation(), 500, 2, 1, 2, Material.SNOW_BLOCK.createBlockData());
			            		for(Entity e : p.getWorld().getNearbyEntities(po.getLocation(), 2, 2, 2)) {
									if(p!=e && e instanceof LivingEntity) {
										LivingEntity le = (LivingEntity)e;
										le.damage(6,p);
									}
			                	}
			                }
						},d1*5);
					}
				}
				
			}
			
		}
		
		if(d.getEntity() instanceof Snowball) 
		{
			Snowball po = (Snowball)d.getEntity();
			if(po.getShooter() instanceof Snowman) {
				Snowman p = (Snowman) po.getShooter();
				if(p.hasMetadata("wsnowman")) {
                	p.getWorld().spawnParticle(Particle.BLOCK_CRACK,po.getLocation(), 60, 1, 1, 1, Material.SNOW_BLOCK.createBlockData());
            		for(Entity e : p.getWorld().getNearbyEntities(po.getLocation(), 1, 1, 1)) {
						if(p!=e && e instanceof LivingEntity) {
							LivingEntity le = (LivingEntity)e;
							le.damage(2,p);
						}
                	}
				}
				
			}
			
		}
	}
	
	final private Location Spike(final Location fl, World w, AtomicInteger j) {

		Location el = fl.clone().add(fl.clone().getDirection().normalize().multiply(j.get()));
		w.playSound(el.clone().add(0,4,0), Sound.BLOCK_GLASS_BREAK, 0.35f, j.get()*0.08f);
		w.playSound(el.clone().add(0,4,0), Sound.BLOCK_LANTERN_BREAK, 0.35f, j.get()*0.08f);
		w.playSound(el.clone().add(0,4,0), Sound.ITEM_TRIDENT_THROW, 0.35f, j.get()*0.08f);
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.incrementAndGet()/5, 0), 25, 1, 2, 1, Material.SNOW_BLOCK.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.incrementAndGet()/5, 0), 25, 0.5, 2, 0.5, Material.WHITE_WOOL.createBlockData());
		w.spawnParticle(Particle.REVERSE_PORTAL, el.clone().add(0, j.incrementAndGet()/5+0.1, 0), 25, 0.1, 2, 0.1);
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+1, 0), 25, 0.3, 2, 0.3, Material.SNOW_BLOCK.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+1, 0), 25, 0.2, 2, 0.2, Material.WHITE_WOOL.createBlockData());
		w.spawnParticle(Particle.REVERSE_PORTAL, el.clone().add(0, j.get()/5+1.1, 0), 25, 0.1, 2, 0.1);
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+2, 0), 25, 0.3, 2, 0.3, Material.SNOW_BLOCK.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+2, 0), 25, 0.2, 2, 0.2, Material.WHITE_WOOL.createBlockData());
		w.spawnParticle(Particle.REVERSE_PORTAL, el.clone().add(0, j.get()/5+2.1, 0), 25, 0.1, 2, 0.1);
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+3, 0), 25, 0.3, 2, 0.3, Material.SNOW_BLOCK.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+3, 0), 25, 0.2, 2, 0.2, Material.WHITE_WOOL.createBlockData());
		w.spawnParticle(Particle.REVERSE_PORTAL, el.clone().add(0, j.get()/5+3.1, 0), 25, 0.1, 2, 0.1);
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+4, 0), 25, 0.2, 2, 0.2, Material.SNOW_BLOCK.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+4, 0), 25, 0.1, 2, 0.1, Material.WHITE_WOOL.createBlockData());
		w.spawnParticle(Particle.REVERSE_PORTAL, el.clone().add(0, j.get()/5+4.1, 0), 25, 0.05, 2, 0.05);
		return el;
    	
	}
	
	public void Potion(ProjectileLaunchEvent d) 
	{
	    
		int sec = 3;
		if(d.getEntity() instanceof ThrownPotion) 
		{
			ThrownPotion po = (ThrownPotion)d.getEntity();
			if(po.getShooter() instanceof Witch) {
				Witch p = (Witch) po.getShooter();
				if(ordeal.containsKey(p.getUniqueId())) {
					d.setCancelled(true);
					return;
				}
				if(p.hasMetadata("snowyboss")) {
				p.getWorld().playSound(p.getEyeLocation(), Sound.ENTITY_WITCH_AMBIENT, 1,2);
	               PotionMeta pm = (PotionMeta) po.getItem().getItemMeta();
	               pm.removeCustomEffect(PotionEffectType.POISON);
	               pm.setBasePotionData(new PotionData(PotionType.WEAKNESS));
	               pm.setColor(Color.AQUA);
	               pm.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 200,1,true,true), true);
	               pm.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200,1,true,true), true);
	               ItemStack pi = po.getItem();
	               pi.setItemMeta(pm);
	               po.setItem(pi);
	               
					if(rb2cooldown.containsKey(p.getUniqueId())) 
			        {
			            long timer = (rb2cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) 
			            {
			            }
			            else
			            {
			            	rb2cooldown.remove(p.getUniqueId()); 
			            	

			            	Location el = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation().add(0, -4, 0).setDirection(p.getEyeLocation().clone().getDirection());
			            	
			            	final World w = el.getWorld();
			            	
			            	AtomicInteger j = new AtomicInteger();
							p.getWorld().playSound(el.clone(), Sound.BLOCK_SNOW_FALL, 0.8f, 2f);
							p.getWorld().playSound(el.clone(), Sound.AMBIENT_UNDERWATER_ENTER, 0.8f, 0f);
		            		p.getWorld().spawnParticle(Particle.WATER_WAKE, el.clone().add(0, 4, 0), 100, 2, 2, 2);
		            		p.getWorld().spawnParticle(Particle.WHITE_ASH, el.clone().add(0, 4, 0), 100, 2, 2, 2);
		            		p.getWorld().spawnParticle(Particle.SNOWBALL, el.clone().add(0, 4, 0), 100, 2, 2, 2);
							for(int d1=0; d1<25; d1++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	for(Entity e : el.getWorld().getNearbyEntities(Spike(el,w,j).clone(), 2, 15, 2)) {
											if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
												LivingEntity le = (LivingEntity)e;
												le.damage(3,p);
											}
					                	}
					                }
		                	   }, d1*2+25);
		                    }
		                    rb2cooldown.put(p.getUniqueId(), System.currentTimeMillis()); 
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
		            	

		            	Location el = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation().add(0, -4, 0).setDirection(p.getEyeLocation().clone().getDirection());
		            	
		            	final World w = el.getWorld();
		            	
		            	AtomicInteger j = new AtomicInteger();
						p.getWorld().playSound(el.clone(), Sound.BLOCK_SNOW_FALL, 0.8f, 2f);
						p.getWorld().playSound(el.clone(), Sound.AMBIENT_UNDERWATER_ENTER, 0.8f, 0f);
	            		p.getWorld().spawnParticle(Particle.WATER_WAKE, el.clone().add(0, 4, 0), 100, 2, 2, 2);
	            		p.getWorld().spawnParticle(Particle.WHITE_ASH, el.clone().add(0, 4, 0), 100, 2, 2, 2);
	            		p.getWorld().spawnParticle(Particle.SNOWBALL, el.clone().add(0, 4, 0), 100, 2, 2, 2);
						for(int d1=0; d1<25; d1++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	for(Entity e : el.getWorld().getNearbyEntities(Spike(el,w,j).clone(), 2, 15, 2)) {
										if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
											LivingEntity le = (LivingEntity)e;
											le.damage(3,p);
										}
				                	}
				                }
	                	   }, d1*2+25);
	                    }
	                    rb2cooldown.put(p.getUniqueId(), System.currentTimeMillis()); 
			        }
				}
			}
		}					
	}
	
	final private void breeze(LivingEntity p, LivingEntity t) {
		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SNOW_PLACE, 1.0f, 2f);
    	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1.0f, 0f);
		ItemStack is = new ItemStack(Material.SNOW_BLOCK);
		
		String rn = gethero(p);
		if(p.hasMetadata("ruined")) {

	    	for(double pi= -Math.PI/4.5; pi<=Math.PI/4.5; pi += Math.PI/6) {
				Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
				solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				solid.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
				solid.setMetadata("crystal of"+p.getUniqueId(), new FixedMetadataValue(RMain.getInstance(), true));
				solid.setPickupDelay(9999);
	            solid.setVelocity(p.getLocation().getDirection().normalize().multiply(0.05*t.getLocation().distance(p.getLocation())));
	            solid.setGravity(false);
	            solid.setGlowing(true);
	            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	solid.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 400,3,3,3,0.1,Material.SNOW.createBlockData());
	                	solid.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 300,3,3,3,0.1,Material.ICE.createBlockData());
	                	solid.getWorld().spawnParticle(Particle.SNOWFLAKE, solid.getLocation(), 300,3,3,3,0.1);
	                        p.getWorld().playSound(solid.getLocation(), Sound.BLOCK_POWDER_SNOW_BREAK, 1, 0);
		                    p.getWorld().playSound(solid.getLocation(), Sound.BLOCK_SNOW_BREAK, 1, 0);
		                    p.getWorld().playSound(solid.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2);

		                	for(Entity e : solid.getWorld().getNearbyEntities(solid.getLocation(), 3, 3, 3)) {
								if(p!=e && e instanceof LivingEntity) {
									LivingEntity le = (LivingEntity)e;
									le.damage(5,p);
									Holding.holding(null, le, 20l);
								}
		                	}
		                    p.getWorld().getEntities().stream().filter(en -> en.hasMetadata("crystal of"+p.getUniqueId())).forEach(n -> n.remove());
			                solid.remove();
	                }
	            }, 30); 	
	    	}
		}
		
		Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
		solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		solid.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
		solid.setMetadata("crystal of"+p.getUniqueId(), new FixedMetadataValue(RMain.getInstance(), true));
		solid.setPickupDelay(9999);
        solid.setVelocity(p.getLocation().getDirection().normalize().multiply(0.05*t.getLocation().distance(p.getLocation())));
        solid.setGravity(false);
        solid.setGlowing(true);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	solid.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 400,3,3,3,0.1,Material.SNOW.createBlockData());
            	solid.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 300,3,3,3,0.1,Material.ICE.createBlockData());
            	solid.getWorld().spawnParticle(Particle.SNOWFLAKE, solid.getLocation(), 300,3,3,3,0.1);
                    p.getWorld().playSound(solid.getLocation(), Sound.BLOCK_POWDER_SNOW_BREAK, 1, 0);
                    p.getWorld().playSound(solid.getLocation(), Sound.BLOCK_SNOW_BREAK, 1, 0);
                    p.getWorld().playSound(solid.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2);

                	for(Entity e : solid.getWorld().getNearbyEntities(solid.getLocation(), 3, 3, 3)) {
						if(p!=e && e instanceof LivingEntity) {
							LivingEntity le = (LivingEntity)e;
							le.damage(5,p);
							Holding.holding(null, le, 20l);
						}
                	}
                    p.getWorld().getEntities().stream().filter(en -> en.hasMetadata("crystal of"+p.getUniqueId())).forEach(n -> n.remove());
	                solid.remove();
            }
        }, 30); 	
	}
	
	public void Breeze(EntityPotionEffectEvent ev) 
	{
		if(ev.getEntity() instanceof Witch && ev.getEntity().hasMetadata("snowyboss") && !ev.getEntity().hasMetadata("failed")) 
		{
			Witch p = (Witch)ev.getEntity();
			if(hunt.containsKey(p.getUniqueId())) 
            {
                long timer = (hunt.get(p.getUniqueId())/1000 + 3) - System.currentTimeMillis()/1000; // geting time in seconds
                if(!(timer < 0)) 
                {
                }
                else
                {
                	hunt.remove(p.getUniqueId()); 
        			if(p.getTarget() == null) {
        				return;
        			}
        			LivingEntity t = p.getTarget();
        			
        			breeze(p,t);

                	hunt.put(p.getUniqueId(), System.currentTimeMillis());  
                }
            }
            else // if cooldown doesn't have players name in it
            {
    			if(p.getTarget() == null) {
    				return;
    			}
    			LivingEntity t = p.getTarget();
    			
    			breeze(p,t);

				hunt.put(p.getUniqueId(), System.currentTimeMillis());  
			}
		}
	}

	public void Breeze(EntitySpellCastEvent ev) 
	{
		if(ev.getEntity() instanceof Evoker && ev.getEntity().hasMetadata("snowy") && ev.getEntity().hasMetadata("mage")) 
		{
			ev.setCancelled(true);
			LivingEntity p = (LivingEntity)ev.getEntity();
			
        	final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR)), 3).getLocation().setDirection(p.getLocation().getDirection());
			ArrayList<Location> br = new ArrayList<>();
        	AtomicInteger j = new AtomicInteger();
        	for(double d1 = -3; d1<6 ; d1+=0.2) {
        		br.add(p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(d1)));
        	}
        	br.forEach(bl -> {

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, bl,30, 3,0.1,3,0,Material.POWDER_SNOW.createBlockData());
	                }
				}, j.incrementAndGet()/30);
        	});
        	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 1f, 0f);
        	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1f, 0f);
        	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1f, 0f);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	for(Entity e : p.getNearbyEntities(3, 2, 3)) {
                		if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(4,p);

							final Location lel = le.getLocation().clone();
							final Location pl = p.getLocation().clone();

    						for(double d1 = 0; d1<l.distance(lel); d1+=0.1) {
    							if(lel.clone().add(pl.clone().getDirection().normalize().multiply(0.1)).getBlock().isPassable()){
		    						le.teleport(lel.clone().add(pl.clone().getDirection().normalize().multiply(0.1)));
    							}
    						}
						}
                	}
                }
            }, 2); 		
		}
	}
	
	
	public void Breeze(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 4;
		if((d.getDamager() instanceof Stray) && d.getEntity() instanceof LivingEntity&& !d.isCancelled() &&d.getDamager().hasMetadata("frostmage")) 
		{
			LivingEntity p = (LivingEntity)d.getDamager();
						if(brzc.containsKey(p.getUniqueId())) 
				        {
				            long timer = (brzc.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) 
				            {
				            }
				            else
				            {
				            	brzc.remove(p.getUniqueId()); 
				            	final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR)), 3).getLocation().setDirection(p.getLocation().getDirection());
				    			ArrayList<Location> br = new ArrayList<>();
			                	AtomicInteger j = new AtomicInteger();
			                	for(double d1 = -3; d1<6 ; d1+=0.2) {
			                		br.add(p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(d1)));
			                	}
			                	br.forEach(bl -> {

									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
					                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, bl,30, 3,0.1,3,0,Material.POWDER_SNOW.createBlockData());
						                }
									}, j.incrementAndGet()/30);
			                	});
			                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 1f, 0f);
			                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1f, 0f);
			                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1f, 0f);
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	for(Entity e : p.getNearbyEntities(3, 2, 3)) {
					                		if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
												LivingEntity le = (LivingEntity)e;
												le.damage(4,p);

												final Location lel = le.getLocation().clone();
												final Location pl = p.getLocation().clone();

					    						for(double d1 = 0; d1<l.distance(lel); d1+=0.1) {
					    							if(lel.clone().add(pl.clone().getDirection().normalize().multiply(0.1)).getBlock().isPassable()){
							    						le.teleport(lel.clone().add(pl.clone().getDirection().normalize().multiply(0.1)));
					    							}
					    						}
											}
					                	}
					                }
					            }, 2); 	
			                	brzc.put(p.getUniqueId(), System.currentTimeMillis()); 
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {
			            	final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR)), 3).getLocation().setDirection(p.getLocation().getDirection());
			    			ArrayList<Location> br = new ArrayList<>();
		                	AtomicInteger j = new AtomicInteger();
		                	for(double d1 = -3; d1<6 ; d1+=0.2) {
		                		br.add(p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(d1)));
		                	}
		                	br.forEach(bl -> {

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
				                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, bl,30, 3,0.1,3,0,Material.POWDER_SNOW.createBlockData());
					                }
								}, j.incrementAndGet()/30);
		                	});
		                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 1f, 0f);
		                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1f, 0f);
		                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1f, 0f);
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	for(Entity e : p.getNearbyEntities(3, 2, 3)) {
				                		if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
											LivingEntity le = (LivingEntity)e;
											le.damage(4,p);
											
											final Location lel = le.getLocation().clone();
											final Location pl = p.getLocation().clone();

				    						for(double d1 = 0; d1<l.distance(lel); d1+=0.1) {
				    							if(lel.clone().add(pl.clone().getDirection().normalize().multiply(0.1)).getBlock().isPassable()){
						    						le.teleport(lel.clone().add(pl.clone().getDirection().normalize().multiply(0.1)));
				    							}
				    						}
										}
				                	}
				                }
				            }, 2); 	
				        	brzc.put(p.getUniqueId(), System.currentTimeMillis()); 
				        }
		}					
	}
	
	
	public void Hunting(EntityTargetEvent ev) 
	{
		if((ev.getEntity() instanceof Stray) && ev.getEntity().hasMetadata("frosthunter")) 
		{
			LivingEntity p = (LivingEntity)ev.getEntity();
			int sec = 4;

					
					if(hunt.containsKey(p.getUniqueId())) 
		            {
		                long timer = (hunt.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) 
		                {
		                }
		                else
		                {
		                	hunt.remove(p.getUniqueId()); 
		                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_CONVERTED_TO_STRAY, 1f, 0f);
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60,2,true,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60,2,true,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60,2,true,true));
		                	p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60,1,true,true));
							ev.setCancelled(true);
		                	hunt.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {

	                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_CONVERTED_TO_STRAY, 1f, 0f);
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60,2,true,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60,2,true,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60,2,true,true));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60,1,true,true));
						ev.setCancelled(true);
						hunt.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}
	

	final private void Eruption(LivingEntity p, Location tl) {
		if(icefall.containsKey(p.getUniqueId())) 
        {
            long timer = (icefall.get(p.getUniqueId())/1000 + 5) - System.currentTimeMillis()/1000; // geting time in seconds
            if(!(timer < 0)) 
            {
            }
            else
            {
            	icefall.remove(p.getUniqueId()); 


            	tl.getWorld().playSound(tl, Sound.ENTITY_DROWNED_SHOOT, 1f, 0f);
            	
				FallingBlock fallingb = p.getWorld().spawnFallingBlock(tl.clone().add(0, 8.5, 0), Material.SNOW_BLOCK.createBlockData());
				fallingb.setInvulnerable(true);
				fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
				fallingb.setMetadata("stuff", new FixedMetadataValue(RMain.getInstance(),gethero(p)));
				fallingb.setMetadata("snowblockfall", new FixedMetadataValue(RMain.getInstance(),p.getName()));
				fallingb.setDropItem(true);
				fallingb.setHurtEntities(true);
				snowblock.put(fallingb.getUniqueId(), p);
            	
				icefall.put(p.getUniqueId(), System.currentTimeMillis());  
            }
        }
        else // if cooldown doesn't have players name in it
        {

        	tl.getWorld().playSound(tl, Sound.ENTITY_DROWNED_SHOOT, 1f, 0f);
        	
			FallingBlock fallingb = p.getWorld().spawnFallingBlock(tl.clone().add(0, 8.5, 0), Material.SNOW_BLOCK.createBlockData());
			fallingb.setInvulnerable(true);
			fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
			fallingb.setMetadata("stuff", new FixedMetadataValue(RMain.getInstance(),gethero(p)));
			fallingb.setMetadata("snowblockfall", new FixedMetadataValue(RMain.getInstance(),p.getName()));
			fallingb.setDropItem(true);
			fallingb.setHurtEntities(true);
			snowblock.put(fallingb.getUniqueId(), p);
        	
			icefall.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}
	
	public void Icefall(EntityDamageByEntityEvent ev) 
	{
		if((ev.getEntity() instanceof Witch) && ev.getEntity().hasMetadata("snowyboss")) 
		{
			LivingEntity p = (LivingEntity)ev.getEntity();

			if(p.hasMetadata("raid")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")|| ordeal.containsKey(p.getUniqueId())) {
					return;
				}

				final Location tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				Eruption(p,tl);
			}
			else if(p.hasMetadata("summoned")) {
				final Object hero = getherotype(gethero(p));
				if(hero instanceof Player) {
					final Player hp = (Player)hero;
					Eruption(p,hp.getLocation());
				}
				else if(hero instanceof HashSet) {
					@SuppressWarnings("unchecked")
					HashSet<Player> par = (HashSet<Player>) hero;
					Eruption(p,par.stream().findAny().get().getLocation());
				}

			}

					
		}
	}

	final private void blockexplode(FallingBlock fallingb) {
		LivingEntity p = (LivingEntity) Holding.ale(snowblock.get(fallingb.getUniqueId()));
		Location tl = fallingb.getLocation();
		tl.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tl, 1);
		tl.getWorld().spawnParticle(Particle.SNOW_SHOVEL, tl, 400,3,3,3);

		for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
		{
			if(p!=e && e instanceof Player) {
				Player le = (Player)e;
        		le.damage(6, p);
			}
			
		}
		p.getWorld().playSound(tl, Sound.BLOCK_POWDER_SNOW_BREAK, 1, 0);
		fallingb.remove();
	}
	
	public void SnowBlock(EntityDropItemEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(snowblock.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
	        	blockexplode(fallingb);
	        }
		 }
	}



	
	public void SnowBlock(EntityDamageByEntityEvent ev) 
	{
		if(ev.getDamager() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getDamager();
	        if(snowblock.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
	        	blockexplode(fallingb);
	        }
		 }
	}


	
	public void SnowBlock(EntityChangeBlockEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(snowblock.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
	        	blockexplode(fallingb);
	        }
		 }
	}


	
	public void IceBow(EntityShootBowEvent ev) 
	{
		if(ev.getEntity() instanceof Stray && ev.getEntity().hasMetadata("arch") && ev.getEntity().hasMetadata("snowy")){
		    Projectile ar = (Projectile) ev.getProjectile();
		    Stray p = (Stray) ev.getEntity();
		    
			for(int i = 0; i <15; i++) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                    p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, ar.getLocation(), 20,2,2,2);
	                    p.getWorld().spawnParticle(Particle.SNOWFLAKE, ar.getLocation(), 20,2,2,2);
						for(Entity e : p.getWorld().getNearbyEntities(ar.getLocation(),2, 2, 2)) {
							if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
								LivingEntity le = (LivingEntity)e;
								le.damage(1.1,p);
							}
						}
	                }
	            }, i); 	                    	
            }
		 }
	}
	

	public void Freeze(EntityDamageByEntityEvent d) 
	{

		if(d.getDamager() instanceof LivingEntity && d.getEntity() instanceof Player&& !d.isCancelled()) 
		{
			LivingEntity p = (LivingEntity)d.getDamager();
			Player le = (Player)d.getEntity();
			if(p.hasMetadata("snowy")) {

                if(20+le.getFreezeTicks()>le.getMaxFreezeTicks()) {
                	le.setFreezeTicks(le.getMaxFreezeTicks());
                }
                else {
	                le.setFreezeTicks(20+le.getFreezeTicks());
                }
			}
		}
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void Cyclone(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 20;
		if(d.getEntity() instanceof Witch && d.getDamager() instanceof Player&& !d.isCancelled()) 
		{
			Witch p = (Witch)d.getEntity();
			if(p.hasMetadata("failed")||p.hasMetadata("summoned")) {
				return;
			}
			if(p.hasMetadata("ruined") && (p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getMaxHealth()*0.2);
                ordealable.put(p.getUniqueId(), true);
                d.setCancelled(true);
				return;
			}
			if(p.hasMetadata("snowyboss") &&  !ordeal.containsKey(p.getUniqueId())) {
				p.getWorld().playSound(p.getEyeLocation(), Sound.ENTITY_WITCH_AMBIENT, 1,2);
				p.getWorld().playSound(p.getEyeLocation(), Sound.ENTITY_WITCH_DRINK, 1,0);
						if(rb1cooldown.containsKey(p.getUniqueId())) 
				        {
				            long timer = (rb1cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) 
				            {
				            }
				            else
				            {
				                rb1cooldown.remove(p.getUniqueId()); 
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.6f, 0);
			                    p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 300,5,2,5);
			                    p.getWorld().spawnParticle(Particle.SOUL, p.getLocation(), 300,5,2,5);
			                    p.getWorld().spawnParticle(Particle.SNOWFLAKE, p.getLocation(), 300,5,2,5);
				    			String rn = gethero(p);
				                p.getNearbyEntities(25, 25, 25).forEach(ne -> {
				                	if(ne instanceof Player) {
				                		Player pe = (Player) ne;
				    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
					                		pe.sendMessage(ChatColor.BOLD+"이리 오너라!");
				    					}
				    					else {
					                		pe.sendMessage(ChatColor.BOLD+"Come Here!");
				    					}
				                	}
				                	
				                });
				                final World pw = p.getWorld();
				                Holding.holding(null, p, 40l);
				                Holding.invur(p, 40l);
				                AtomicInteger j = new AtomicInteger();	
			                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(int i = 0; i <10; i++) {
											int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
									            	ArrayList<Location> ring = new ArrayList<Location>();
													p.getWorld().playSound(p.getLocation(), Sound.BLOCK_POWDER_SNOW_PLACE, 1, 0);

								                	for(double an = 0; an<Math.PI*2; an +=Math.PI/180) {
								                		ring.add(p.getLocation().clone().add(p.getLocation().getDirection().normalize().rotateAroundY(an).multiply(an)).add(0, an+j.get(), 0));
								                	}
								                	ring.forEach(l -> {
								                		pw.spawnParticle(Particle.WHITE_ASH, l, 5, 0.5,0.5,0.5,0);
								                		pw.spawnParticle(Particle.SNOW_SHOVEL, l, 2, 0.5,0.5,0.5,0);
								                		pw.spawnParticle(Particle.SNOWFLAKE, l, 2, 0.5,0.5,0.5,0);
								                		
								                	});
													p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.1f, 2f);
								                    p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 100,5,2,5);
								                    p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 100,5,2,5);
													for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),5, 5, 5)) {
														if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
															LivingEntity le = (LivingEntity)e;
															le.damage(10);
															le.teleport(p);
														}
													}
								                }
								            }, i*2); 	
											ordt.put(rn, t1);
					                    }
					                }
					            }, 40);
								ordt.put(rn, t1);
					            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); 
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {
							p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.6f, 0);
		                    p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 300,5,2,5);
		                    p.getWorld().spawnParticle(Particle.SOUL, p.getLocation(), 300,5,2,5);
		                    p.getWorld().spawnParticle(Particle.SNOWFLAKE, p.getLocation(), 300,5,2,5);
			    			String rn = gethero(p);
			                p.getNearbyEntities(25, 25, 25).forEach(ne -> {
			                	if(ne instanceof Player) {
			                		Player pe = (Player) ne;
			    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
				                		pe.sendMessage(ChatColor.BOLD+"이리 오너라!");
			    					}
			    					else {
				                		pe.sendMessage(ChatColor.BOLD+"Come Here!");
			    					}
			                	}
			                	
			                });
			                final World pw = p.getWorld();
			                Holding.holding(null, p, 40l);
			                Holding.invur(p, 40l);
			                AtomicInteger j = new AtomicInteger();	
		                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									for(int i = 0; i <10; i++) {
										int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
								            	ArrayList<Location> ring = new ArrayList<Location>();
												p.getWorld().playSound(p.getLocation(), Sound.BLOCK_POWDER_SNOW_PLACE, 1, 0);

							                	for(double an = 0; an<Math.PI*2; an +=Math.PI/180) {
							                		ring.add(p.getLocation().clone().add(p.getLocation().getDirection().normalize().rotateAroundY(an).multiply(an)).add(0, an+j.get(), 0));
							                	}
							                	ring.forEach(l -> {
							                		pw.spawnParticle(Particle.WHITE_ASH, l, 5, 0.5,0.5,0.5,0);
							                		pw.spawnParticle(Particle.SNOW_SHOVEL, l, 2, 0.5,0.5,0.5,0);
							                		pw.spawnParticle(Particle.SNOWFLAKE, l, 2, 0.5,0.5,0.5,0);
							                		
							                	});
												p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.1f, 2f);
							                    p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 100,5,2,5);
							                    p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 100,5,2,5);
												for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),5, 5, 5)) {
													if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
														LivingEntity le = (LivingEntity)e;
														le.damage(10);
														le.teleport(p);
													}
												}
							                }
							            }, i*2); 	
										ordt.put(rn, t1);
				                    }
				                }
				            }, 40);
							ordt.put(rn, t1);
				            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); 
				        }
			}
		}					
	}
	
	final private void meteor(LivingEntity p) {

        Location rl = OverworldRaids.getraidloc(p).clone();
		String rn = gethero(p);
        p.teleport(rl.add(0, 1, 0));
        final Location tl = p.getLocation();
        
        for(Entity e : OverworldRaids.getheroes(p)) {
        	if(e instanceof Player && !e.hasMetadata("fake")) {
        		Player pe = (Player) e;
        		Holding.invur(pe, 40l);
				if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
            		pe.sendMessage(ChatColor.BOLD+"차가운 심장의 마녀: 운명에 저항하지마라.");
				}
				else {
            		pe.sendMessage(ChatColor.BOLD+"FrozenHeart: You can't change your destiny.");
				}
				pe.getWorld().playSound(pe.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.3f, 2);
				if(pe.getWorld() == p.getWorld()) {
					pe.teleport(tl);
				}
        	}
        }
        AtomicInteger j = new AtomicInteger();	
		for(int i = 0; i <5; i++) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
	            	ArrayList<Location> ring = new ArrayList<Location>();
					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_POWDER_SNOW_PLACE, 1, 0);

                	for(double an = 0; an<Math.PI*2; an +=Math.PI/180) {
                		ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an).multiply(an)).add(0, an+j.get(), 0));
                	}
                	ring.forEach(l -> {
						tl.getWorld().spawnParticle(Particle.WHITE_ASH, l, 5, 0.5,0.5,0.5,0);
						tl.getWorld().spawnParticle(Particle.SNOW_SHOVEL, l, 2, 0.5,0.5,0.5,0);
						tl.getWorld().spawnParticle(Particle.SNOWFLAKE, l, 2, 0.5,0.5,0.5,0);
                		
                	});
                	j.incrementAndGet();
                }
            }, i*5); 	                    	
        }
		ArrayList<Location> mls1 = new ArrayList<>();
		ArrayList<Location> mls2 = new ArrayList<>();
		ArrayList<Location> mls3 = new ArrayList<>();
		ArrayList<Location> mls4 = new ArrayList<>();
		for(int i = 0; i<15; i++) {
			mls1.add(rl.clone().add(i, 7, 0));
			mls2.add(rl.clone().add(-i, 7, 0));
			mls3.add(rl.clone().add(0, 7, i));
			mls4.add(rl.clone().add(0, 7, -i));
		}

		for(int i = 0; i<15; i++) {
			mls1.add(rl.clone().add(i, 7, i));
			mls2.add(rl.clone().add(-i, 7, i));
			mls3.add(rl.clone().add(-i, 7, -i));
			mls4.add(rl.clone().add(i, 7, -i));
		}


		for(int i = 0; i<15; i++) {
        	Random r1 = new Random();
        	Random r2 = new Random();
        	Random r3 = new Random();
        	Random r4 = new Random();
        	double rd1 = r1.nextDouble()*20 *(r3.nextBoolean()?1:-1);
        	double rd2 = r2.nextDouble()*20*(r4.nextBoolean()?1:-1);
			mls1.add(rl.clone().add(rd1, 7, rd2));
			mls2.add(rl.clone().add(rd1, 7, rd2));
			mls3.add(rl.clone().add(rd1, 7, rd2));
			mls4.add(rl.clone().add(rd1, 7, rd2));
		}
        Holding.holding(null, p, 60l);
        Holding.invur(p, 60l);
		
		int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
                AtomicInteger j1 = new AtomicInteger();
                AtomicInteger j2 = new AtomicInteger();
                AtomicInteger j3 = new AtomicInteger();
                AtomicInteger j4 = new AtomicInteger();
            	mls1.forEach(l -> {
            		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.SNOW_BLOCK.createBlockData());
							fallingb.setInvulnerable(true);
							fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
							fallingb.setMetadata("stuff", new FixedMetadataValue(RMain.getInstance(),true));
							fallingb.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(),true));
							
							fallingb.setDropItem(true);
							fallingb.setHurtEntities(true);
							snowblock.put(fallingb.getUniqueId(), p);
		                }
            		}, j1.getAndIncrement()*4);
					ordt.put(rn, t1);
            	});
            	mls2.forEach(l -> {
            		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.SNOW_BLOCK.createBlockData());
							fallingb.setInvulnerable(true);
							fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
							fallingb.setMetadata("stuff", new FixedMetadataValue(RMain.getInstance(),true));
							fallingb.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(),true));
							
							fallingb.setDropItem(true);
							fallingb.setHurtEntities(true);
							snowblock.put(fallingb.getUniqueId(), p);
		                }
            		},j2.getAndIncrement()*4);
					ordt.put(rn, t1);
            	});
            	mls3.forEach(l -> {
            		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.SNOW_BLOCK.createBlockData());
							fallingb.setInvulnerable(true);
							fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
							fallingb.setMetadata("stuff", new FixedMetadataValue(RMain.getInstance(),true));
							fallingb.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(),true));
							
							fallingb.setDropItem(true);
							fallingb.setHurtEntities(true);
							snowblock.put(fallingb.getUniqueId(), p);
		                }
            		},j3.getAndIncrement()*4);
					ordt.put(rn, t1);
            	});
            	mls4.forEach(l -> {
            		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.SNOW_BLOCK.createBlockData());
							fallingb.setInvulnerable(true);
							fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
							fallingb.setMetadata("stuff", new FixedMetadataValue(RMain.getInstance(),true));
							fallingb.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(),true));
							
							fallingb.setDropItem(true);
							fallingb.setHurtEntities(true);
							snowblock.put(fallingb.getUniqueId(), p);
		                }
            		},j4.getAndIncrement()*4);
					ordt.put(rn, t1);
            	});
            }
        }, 40);
		ordt.put(rn, t1);  
	}

	public void Meteor(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 35;
		if(d.getEntity() instanceof Witch && d.getEntity().hasMetadata("snowyboss") && d.getEntity().hasMetadata("ruined") ) 
		{
			Witch p = (Witch)d.getEntity();
			if(p.hasMetadata("failed") || ordeal.containsKey(p.getUniqueId())) {
				return;
			}
			if((p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getMaxHealth()*0.2);
                ordealable.put(p.getUniqueId(), true);
                d.setCancelled(true);
				return;
			}
				if(rb7cooldown.containsKey(p.getUniqueId()))
		        {
		            long timer = (rb7cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		            if(!(timer < 0))
		            {
		            }
		            else 
		            {
		                rb7cooldown.remove(p.getUniqueId()); // removing player from HashMap
		                meteor(p);
	                    rb7cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else 
		        {
	                meteor(p);
		            rb7cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
	}
	

	public void mirrored(EntityDamageByEntityEvent d) 
	{
		if(ordeal.containsKey(d.getEntity().getUniqueId())) {
			Witch p = (Witch)d.getEntity();
			d.setCancelled(true);
			if(d.getDamager() instanceof Witch) {
				return;
			}
			if(d.getDamager() instanceof Player) {
				Player dp = (Player) d.getDamager();
				if(dp.hasCooldown(Material.YELLOW_TERRACOTTA)) {
					return;
				}
			}
			String rn = gethero(p);
			if(p.hasMetadata("mirror")) {
        		ordeal.remove(ordeal.get(p.getUniqueId()));
                rb6cooldown.remove(ordeal.get(p.getUniqueId()));
				Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("mirror"+rn)).forEach(e -> e.remove());
        		if(ordt.containsKey(rn)) {
        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        		}
                for(Player pe : OverworldRaids.getheroes(p)) {
					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
                		pe.sendMessage(ChatColor.BOLD+"멍청한 것들!");
					}
					else {
                		pe.sendMessage(ChatColor.BOLD+"That's pathetic!");
					}
        			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_PLAYER_HURT_FREEZE, 1, 0);
            		pe.setHealth(0);
            	}
			}
			else {
				Holding.invur(p, 20l);
				if(count.getOrDefault(p.getUniqueId(), 0)>1) {
					count.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
			        Location rl = OverworldRaids.getraidloc(p).clone();
	                for(Player pe : OverworldRaids.getheroes(p)) {
	                	pe.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,50,20,false,false));
	                	pe.teleport(rl);
                		pe.sendMessage(ChatColor.BLUE+ "["+(4-count.getOrDefault(p.getUniqueId(), 0))+ "/4]");
	                	Holding.holding(pe, pe, 20l);
	                	Holding.invur(pe, 20l);
                		mirrorcreate(p);
	                }
				}
				else {

        			p.getWorld().playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1, 0);
        			p.getWorld().spawnParticle(Particle.SNOWFLAKE, p.getLocation(), 400,1,1,1);

	            	p.playEffect(EntityEffect.HURT);
					Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("mirror"+rn)).forEach(e -> e.remove());
	        		ordeal.remove(p.getUniqueId());

	        		if(ordt.containsKey(rn)) {
	        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
	        		}

	            	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
	                for(Player pe : OverworldRaids.getheroes(p)) {
						if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
	                		pe.sendMessage(ChatColor.BLUE+"네놈 따위들이 어떻게..");
						}
						else {
	                		pe.sendMessage(ChatColor.BLUE+"How did You do that..");
						}
	            		Holding.holding(pe, p, 300l);
	                }
		            int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	Holding.ale(p).removeMetadata("failed", RMain.getInstance());
		                }
		            }, 300);
					ordt.put(rn, t3);
				}
			}
		}
	}
	
	final private void mirrorcreate(LivingEntity p) {

		String rn = gethero(p);
		if(ordt.containsKey(rn)) {
			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
		}
		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("mirror"+rn)).forEach(e -> e.remove());

        Location rl = OverworldRaids.getraidloc(p).clone();

		p.setCustomNameVisible(false);
    	p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10,1,false,false));
        
    	Random random=new Random();
    	double number = (random.nextDouble()+0.65) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+0.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = rl.clone().add(number, 0.5, number2);
    	p.teleport(esl);
    	ordeal.put(p.getUniqueId(), p.getUniqueId());
    	
    	for(int i =0; i<9; i++) {
        	Random random1=new Random();
        	double number1 = (random1.nextDouble()) * 3 * (random1.nextBoolean() ? -1 : 1);
        	double number21 = (random1.nextDouble()) * 3 * (random1.nextBoolean() ? -1 : 1);
        	Location esl1 = rl.clone().add(number1, 0.5, number21);
    		ItemStack main = new ItemStack(Material.SNOW);
    		ItemStack off = new ItemStack(Material.ICE);
    		Witch newmob = (Witch) esl1.getWorld().spawnEntity(esl1, EntityType.WITCH);
    		newmob.setCustomName(p.getCustomName());
    		newmob.setCustomNameVisible(false);
    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10,1,false,false));
    		newmob.getEquipment().setItemInMainHand(main);
    		newmob.getEquipment().setItemInOffHand(off);
    		newmob.setMaxHealth(p.getMaxHealth());
    		newmob.setHealth(p.getHealth());
    		newmob.setCanJoinRaid(false);
    		newmob.setPatrolTarget(null);
    		newmob.setPatrolLeader(false);
    		newmob.setGlowing(true);
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("mirror", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("mirror"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
    		Holding.invur(newmob, 10l);
        	ordeal.put(newmob.getUniqueId(), p.getUniqueId());
    	}
            	

		
		
		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
        		ordeal.remove(p.getUniqueId());
				Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("mirror"+rn)).forEach(e -> {
					ordeal.remove(e.getUniqueId());
					e.remove();
				});

        		if(ordt.containsKey(rn)) {
        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        		}
                for(Player pe : OverworldRaids.getheroes(p)) {
					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
                		pe.sendMessage(ChatColor.BOLD+"멍청한 것들!");
					}
					else {
                		pe.sendMessage(ChatColor.BOLD+"That's pathetic!");
					}
            		Holding.invur(pe, 60l);
        			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_PLAYER_HURT_FREEZE, 1, 0);
            		pe.setHealth(0);
            	}
                rb6cooldown.remove(p.getUniqueId());
            }
        }, 300);
		ordt.put(rn, task);
	}
	    
	
	final private void mirrorstart(LivingEntity p, EntityDamageByEntityEvent d) {

		String rn = gethero(p);
		if(ordt.containsKey(rn)) {
			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
		}
		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
		
		count.put(p.getUniqueId(), 4);
		
        Location rl = OverworldRaids.getraidloc(p).clone();
		p.setHealth(p.getMaxHealth()*0.2);
        d.setCancelled(true);
    	p.teleport(rl.clone().add(0, 0, 1));
		Holding.invur(p, 60l);
		p.setCustomNameVisible(false);
		
        for(Player pe : OverworldRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BLUE+"한번 찾아보시지!");
			}
			else {
        		pe.sendMessage(ChatColor.BLUE+"Look it up!");
			}
    		pe.teleport(rl);
    		Holding.invur(pe, 60l);
    		Holding.untouchable(pe, 60l);
        }

		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	mirrorcreate(p);
            }
        }, 50); 	 
		ordt.put(rn, t1);
		
		return;
	}
		
	@SuppressWarnings("deprecation")
	public void Mirror(EntityDamageByEntityEvent d) 
	{
	    
		int sec =70;
		if(d.getEntity() instanceof Witch && !d.isCancelled() && d.getEntity().hasMetadata("snowyboss") && !d.getEntity().hasMetadata("failed") && d.getEntity().hasMetadata("ruined")) 
		{
			Witch p = (Witch)d.getEntity();
			p.getWorld().playSound(p.getEyeLocation(), Sound.ENTITY_WITCH_HURT, 1,2);
			if(p.hasMetadata("failed")||p.hasMetadata("summoned")) {
				return;
			}
			if(!(p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2) || !rb1cooldown.containsKey(p.getUniqueId())|| !ordealable.containsKey(p.getUniqueId()) || ordeal.containsKey(p.getUniqueId())) {
				return;
			}
				if(rb6cooldown.containsKey(p.getUniqueId()))
		        {
		            long timer = (rb6cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		            if(!(timer < 0))
		            {
		            }
		            else 
		            {
		                rb6cooldown.remove(p.getUniqueId()); // removing player from HashMap
		                mirrorstart(Holding.ale(p),d);
			            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		            }
		        }
		        else 
		        {

	                mirrorstart(Holding.ale(p),d);
		            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		        }
			}
	}
	

	
}
