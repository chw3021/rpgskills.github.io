package io.github.chw3021.monsters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class RedBoss implements Listener{

	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb1cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb2cooldown = new HashMap<UUID, Long>();

	@EventHandler
	public void RedbossSkill1(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 6;
		if(d.getDamager() instanceof Skeleton && d.getEntity() instanceof LivingEntity&& !d.isCancelled()) 
		{
			Skeleton p = (Skeleton)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 1).getLocation();
			if(p.hasMetadata("redboss")) {
						if(rb1cooldown.containsKey(p.getUniqueId())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            long timer = (rb1cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
				            	p.spigot().sendMessage(new ComponentBuilder("You have to wait for " + timer + " seconds to use Daze").create());
				            }
				            else // if timer is done
				            {
				                rb1cooldown.remove(p.getUniqueId()); // removing player from HashMap
				                p.getWorld().strikeLightningEffect(le.getLocation());
								for(int i = 0; i <10; i++) {
				                    AtomicInteger j = new AtomicInteger();	
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
							            	ArrayList<Location> ring = new ArrayList<Location>();
											p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
						                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
						                    	Location one = tl.clone();
						                    	one.setDirection(one.getDirection().rotateAroundY(angle));
						                    	one.add(one.getDirection().normalize().multiply(3.5));
						                    	ring.add(one);
						                	} 
						                	ring.forEach(l -> {
						                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() {
														p.getWorld().spawnParticle(Particle.FLAME, l, 6, 0.5,0.5,0.5,0);								                    
									                }
									            }, j.incrementAndGet()/60); 
						                		
						                	});
						                }
						            }, i*5); 	                    	
			                    }
								for(int i = 0; i <10; i++) {
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											for(Entity e : p.getWorld().getNearbyEntities(tl,3.5, 3.5, 3.5)) {
												if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
													LivingEntity le = (LivingEntity)e;
													le.damage(3);	
													if(le.getLocation().distance(tl)>=3.4) {
														le.teleport(le.getLocation());
													}	
												}
											}
						                }
						            }, i*5); 	                    	
			                    }
					            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {
				        	p.getWorld().strikeLightningEffect(le.getLocation());
							for(int i = 0; i <10; i++) {
			                    AtomicInteger j = new AtomicInteger();	
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						            	ArrayList<Location> ring = new ArrayList<Location>();
										p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
					                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
					                    	Location one = tl.clone();
					                    	one.setDirection(one.getDirection().rotateAroundY(angle));
					                    	one.add(one.getDirection().normalize().multiply(3.5));
					                    	ring.add(one);
					                	} 
					                	ring.forEach(l -> {
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													p.getWorld().spawnParticle(Particle.FLAME, l, 6, 0.5,0.5,0.5,0);								                    
								                }
								            }, j.incrementAndGet()/60); 
					                		
					                	});
					                }
					            }, i*5); 	                    	
		                    }
							for(int i = 0; i <10; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(Entity e : p.getWorld().getNearbyEntities(tl,3.5, 3.5, 3.5)) {
											if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
												LivingEntity le = (LivingEntity)e;
												le.damage(3);	
												if(le.getLocation().distance(tl)>=3.4) {
													le.teleport(le.getLocation());
												}	
											}
										}
					                }
					            }, i*5); 	                    	
		                    }
				            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
			}
		}					
	}
	@EventHandler
	public void RedbossSkill2(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 20;
		if(d.getEntity() instanceof Skeleton && d.getDamager() instanceof Player&& !d.isCancelled()) 
		{
			Skeleton p = (Skeleton)d.getEntity();
			final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 10).getLocation();
			if(p.hasMetadata("redboss")) {
						if(rb2cooldown.containsKey(p.getUniqueId())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            long timer = (rb2cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
				            	p.spigot().sendMessage(new ComponentBuilder("You have to wait for " + timer + " seconds to use Daze").create());
				            }
				            else // if timer is done
				            {
				                rb2cooldown.remove(p.getUniqueId()); // removing player from HashMap
				                p.setInvulnerable(true);
				                hold.holding(null, p, 100l);
				                for(Entity e : p.getWorld().getNearbyEntities(tl, 15, 15, 15)) {
				                	if(e instanceof Player && !e.hasMetadata("fake")) {
				                		Player pe = (Player) e;
				                		pe.sendMessage("RedKnight: Look At My Eyes....");
				                	}
				                }
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						                for(Entity e : p.getWorld().getNearbyEntities(tl, 15, 15, 15)) {
						                	if(e instanceof Player && !e.hasMetadata("fake")) {
						                		Player pe = (Player) e;
												p.getWorld().playSound(pe.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
												if(pe.getLocation().getDirection().angle(p.getLocation().getDirection()) <Math.PI+Math.PI/18 && pe.getLocation().getDirection().angle(p.getLocation().getDirection()) >Math.PI-Math.PI/18) {
													pe.damage(30);
													pe.getWorld().spawnParticle(Particle.FLAME, pe.getLocation(), 100,2,2,2,1);
												}
						                	}
						                }
						                p.setInvulnerable(false);
					                }
					            }, 100);
			                    rb2cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {
			                p.setInvulnerable(true);
			                hold.holding(null, p, 100l);
			                for(Entity e : p.getWorld().getNearbyEntities(tl, 25, 25, 25)) {
			                	if(e instanceof Player && !e.hasMetadata("fake")) {
			                		Player pe = (Player) e;
			                		pe.sendMessage("RedKnight: Look At My Eyes....");
			                	}
			                }
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
					                for(Entity e : p.getWorld().getNearbyEntities(tl, 15, 15, 15)) {
					                	if(e instanceof Player && !e.hasMetadata("fake")) {
					                		Player pe = (Player) e;
											p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 0);
											if(Math.abs(pe.getLocation().getDirection().angle(p.getLocation().getDirection())) >Math.PI-Math.PI/10) {
												p.getWorld().playSound(pe.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
												pe.damage(30);
												pe.getWorld().spawnParticle(Particle.FLAME, pe.getLocation(), 100,2,2,2,1);
											}
					                	}
					                }
					                p.setInvulnerable(false);
				                }
				            }, 100);
				            rb2cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
		        	}
			}
		}
}
