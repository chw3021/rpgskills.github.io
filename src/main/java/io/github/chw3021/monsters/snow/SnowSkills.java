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
	
	public static HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, UUID> ordeal = new HashMap<UUID, UUID>();

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
	
	public void Breeze(EntityPotionEffectEvent ev) 
	{
		if(ev.getEntity() instanceof Witch && ev.getEntity().hasMetadata("snowyboss")) 
		{
			Witch p = (Witch)ev.getEntity();
			if(p.getTarget() == null) {
				return;
			}
			LivingEntity t = p.getTarget();
			p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SNOW_PLACE, 1.0f, 2f);
        	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1.0f, 0f);
			ItemStack is = new ItemStack(Material.SNOW_BLOCK);
			
			if(p.hasMetadata("ruined")) {

		    	for(double pi= -Math.PI/3; pi<=Math.PI/4.5; pi += Math.PI/6) {
					Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
					solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
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
									if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
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
							if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
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
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
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

	
	public void SnowBlock(EntityDropItemEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(snowblock.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
				LivingEntity p = (LivingEntity) Holding.ale(snowblock.get(fallingb.getUniqueId()));
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tl, 1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
				{
					if(p!=e && e instanceof Player) {
						Player le = (Player)e;
                		le.damage(6, p);
					}
					
				}
				p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
				fallingb.remove();
	        }
		 }
	}



	
	public void SnowBlock(EntityDamageByEntityEvent ev) 
	{
		if(ev.getDamager() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getDamager();
	        if(snowblock.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
				LivingEntity p = (LivingEntity) Holding.ale(snowblock.get(fallingb.getUniqueId()));
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tl, 1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
				{
					if(p!=e && e instanceof Player) {
						Player le = (Player)e;
                		le.damage(6, p);
					}
					
				}
				p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
				fallingb.remove();
	        }
		 }
	}


	
	public void SnowBlock(EntityChangeBlockEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(snowblock.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
				LivingEntity p = (LivingEntity) Holding.ale(snowblock.get(fallingb.getUniqueId()));
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tl, 1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
				{
					if(p!=e && e instanceof Player) {
						Player le = (Player)e;
                		le.damage(6, p);
					}
					
				}
				p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
				fallingb.remove();
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
								le.damage(1,p);
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
			if((p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
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
			                    p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 100,5,2,5);
			                    p.getWorld().spawnParticle(Particle.SNOWFLAKE, p.getLocation(), 100,5,2,5);
				    			String rn = p.getMetadata("raid").get(0).asString();
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
				                Holding.holding(null, p, 40l);
				                Holding.invur(p, 40l);
			                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(int i = 0; i <10; i++) {
											int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
								                    p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 100,5,2,5);
								                    p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 100,5,2,5);
													for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),5, 5, 5)) {
														if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
															LivingEntity le = (LivingEntity)e;
															le.damage(3);
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
		                    p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 100,5,2,5);
		                    p.getWorld().spawnParticle(Particle.SNOWFLAKE, p.getLocation(), 100,5,2,5);
			    			String rn = p.getMetadata("raid").get(0).asString();
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
			                Holding.holding(null, p, 40l);
			                Holding.invur(p, 40l);
		                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									for(int i = 0; i <10; i++) {
										int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
							                    p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 100,5,2,5);
							                    p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 100,5,2,5);
												for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),5, 5, 5)) {
													if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
														LivingEntity le = (LivingEntity)e;
														le.damage(3);
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

	public void mirrored(EntityDamageByEntityEvent d) 
	{
		if(ordeal.containsKey(d.getEntity().getUniqueId())) {
			Witch p = (Witch)d.getEntity();
			d.setCancelled(true);
			String rn = p.getMetadata("raid").get(0).asString();
			if(p.hasMetadata("mirror")) {
        		ordeal.remove(ordeal.get(p.getUniqueId()));
                rb6cooldown.remove(ordeal.get(p.getUniqueId()));
				Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("mirror"+rn)).forEach(e -> e.remove());
        		if(ordt.containsKey(rn)) {
        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        		}
                for(Player pe : OverworldRaids.getheroes(p)) {
					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
                		pe.sendMessage(ChatColor.BOLD+"실패!");
					}
					else {
                		pe.sendMessage(ChatColor.BOLD+"Failed!");
					}
        			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_PLAYER_HURT_FREEZE, 1, 0);
            		pe.setHealth(0);
            	}
			}
			else {

            	p.playEffect(EntityEffect.HURT);
				Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("mirror"+rn)).forEach(e -> e.remove());
        		ordeal.remove(p.getUniqueId());

        		if(ordt.containsKey(rn)) {
        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        		}

            	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
                for(Player pe : OverworldRaids.getheroes(p)) {
					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
                		pe.sendMessage(ChatColor.BLUE+"이걸 맞추다니..");
					}
					else {
                		pe.sendMessage(ChatColor.BLUE+"How Dare Are You..");
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
	    
	
	final private void mirror(LivingEntity p, EntityDamageByEntityEvent d) {

		String rn = p.getMetadata("raid").get(0).asString();
		if(ordt.containsKey(rn)) {
			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
		}

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
    		Holding.invur(pe, 30l);
        }

		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	Random random=new Random();
            	double number = (random.nextDouble()+1) * 2 * (random.nextBoolean() ? -1 : 1);
            	double number2 = (random.nextDouble()+1) * 2 * (random.nextBoolean() ? -1 : 1);
            	Location esl = rl.clone().add(number, 0.5, number2);
            	p.teleport(esl);
            	ordeal.put(p.getUniqueId(), p.getUniqueId());
            	
            	for(int i =0; i<5; i++) {
                	Random random1=new Random();
                	double number1 = (random1.nextDouble()+1) * 3 * (random1.nextBoolean() ? -1 : 1);
                	double number21 = (random1.nextDouble()+1) * 3 * (random1.nextBoolean() ? -1 : 1);
                	Location esl1 = rl.clone().add(number1, 0.5, number21);
            		ItemStack main = new ItemStack(Material.SNOW);
            		ItemStack off = new ItemStack(Material.ICE);
            		Witch newmob = (Witch) esl1.getWorld().spawnEntity(esl1, EntityType.WITCH);
            		newmob.setCustomName(p.getCustomName());
            		newmob.setCustomNameVisible(false);
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
            }
        }, 50); 	 
		ordt.put(rn, t1);
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
                		pe.sendMessage(ChatColor.BOLD+"네놈들은 절대 이길수 없다.");
					}
					else {
                		pe.sendMessage(ChatColor.BOLD+"You Can Never Beat Me!");
					}
            		Holding.invur(pe, 60l);
        			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_PLAYER_HURT_FREEZE, 1, 0);
            		pe.setHealth(0);
            	}
                rb6cooldown.remove(p.getUniqueId());
            }
        }, 200);
		ordt.put(rn, task);
	}
		
	@SuppressWarnings("deprecation")
	public void Mirror(EntityDamageByEntityEvent d) 
	{
	    
		int sec =70;
		if(d.getEntity() instanceof Witch && !d.isCancelled() && d.getEntity().hasMetadata("snowyboss") && !d.getEntity().hasMetadata("failed") && !d.getEntity().hasMetadata("ruined")) 
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
		                mirror(p,d);
			            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		            }
		        }
		        else 
		        {

	                mirror(p,d);
		            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		        }
			}
	}
	

	
}
