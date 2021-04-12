package io.github.chw3021.monsters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class SnowBoss implements Listener{

	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb1cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb2cooldown = new HashMap<UUID, Long>();

	@EventHandler
	public void Potion(ProjectileLaunchEvent d) 
	{
	    
		int sec = 16;
		if(d.getEntity() instanceof ThrownPotion) 
		{
			ThrownPotion po = (ThrownPotion)d.getEntity();
			if(po.getShooter() instanceof Witch) {
				Witch p = (Witch) po.getShooter();
				if(p.hasMetadata("snowboss")) {
	               PotionMeta pm = (PotionMeta) po.getItem().getItemMeta();
	               pm.removeCustomEffect(PotionEffectType.POISON);
	               pm.setBasePotionData(new PotionData(PotionType.WEAKNESS));
	               pm.setColor(Color.AQUA);
	               pm.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 200,1,true,true), true);
	               pm.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200,1,true,true), true);
	               ItemStack pi = po.getItem();
	               pi.setItemMeta(pm);
	               po.setItem(pi);
				}
			}
		}					
	}
	
	@EventHandler
	public void Skill1(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 12;
		if(d.getEntity() instanceof Witch && d.getDamager() instanceof Player&& !d.isCancelled()) 
		{
			Witch p = (Witch)d.getEntity();
			Player le = (Player)d.getDamager();
			if(p.hasMetadata("snowboss")) {
						if(rb1cooldown.containsKey(p.getUniqueId())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            long timer = (rb1cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
				            }
				            else // if timer is done
				            {
				                rb1cooldown.remove(p.getUniqueId()); // removing player from HashMap
				                p.getNearbyEntities(25, 25, 25).forEach(pe -> pe.sendMessage(ChatColor.BLUE + "SnowWitch: Come Here!"));
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(int i = 0; i <10; i++) {
						                    AtomicInteger j = new AtomicInteger();	
						                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
								                    p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 100,4,4,4);
								                    p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 100,4,4,4);
													for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),4, 4, 4)) {
														if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
															LivingEntity le = (LivingEntity)e;
															le.damage(3);
															le.teleport(p);
														}
													}
								                }
								            }, i*2); 	                    	
					                    }
					                }
					            }, 40);
					            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {
			                p.getNearbyEntities(25, 25, 25).forEach(pe -> pe.sendMessage(ChatColor.BLUE + "SnowWitch: Come Here!"));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									for(int i = 0; i <10; i++) {
					                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
							                    p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 100,4,4,4);
							                    p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 100,4,4,4);
												for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),4, 4, 4)) {
													if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
														LivingEntity le = (LivingEntity)e;
														le.damage(3);
														le.teleport(p);
													}
												}
							                }
							            }, i*2); 	                    	
				                    }
				                }
				            }, 40);
				            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
			}
		}					
	}
	
	
	@EventHandler
	public void Targeting(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getEntity() instanceof Witch && d.getDamager() instanceof Player&& !d.isCancelled()) 
		{
			Witch p = (Witch)d.getEntity();
			Player pe = (Player) d.getDamager();
			if(p.hasMetadata("snowboss")) {
				p.setTarget(pe);
        	}
		}
		if(d.getDamager() instanceof Witch && d.getEntity() instanceof Player&& !d.isCancelled()) 
		{
			Witch p = (Witch)d.getDamager();
			Player pe = (Player) d.getEntity();
			if(p.hasMetadata("snowboss")) {
				p.setTarget(pe);
        	}
		}
	}
	
	
}
