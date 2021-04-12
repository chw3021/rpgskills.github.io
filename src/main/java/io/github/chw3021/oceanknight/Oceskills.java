package io.github.chw3021.oceanknight;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.party.PartyData;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.chw3021.rmain.RMain;
import io.github.chw3021.swordman.SwordSkillsData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Oceskills implements Serializable, Listener {
	

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 1343715876136938259L;
	private HashMap<String, Long> rscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> prcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> jmcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> thcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> pncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> eccooldown = new HashMap<String, Long>();
	private HashMap<String, Long> aultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private OceSkillsData fsd = new OceSkillsData(OceSkillsData.loadData(path +"/plugins/RPGskills/OceSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		OceSkillsData f = new OceSkillsData(OceSkillsData.loadData(path +"/plugins/RPGskills/OceSkillsData.data"));
		fsd = f;
	}
	
	@EventHandler
	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Classes"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
				playerclass = cdata.playerclass;
			}
			
		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Oceskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				OceSkillsData f = new OceSkillsData(OceSkillsData.loadData(path +"/plugins/RPGskills/OceSkillsData.data"));
				fsd = f;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		OceSkillsData f = new OceSkillsData(OceSkillsData.loadData(path +"/plugins/RPGskills/OceSkillsData.data"));
		fsd = f;
		
	}
	
	@EventHandler
	public void Splash(EntityDamageEvent d) 
	{		
        if (!(d.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) d.getEntity();
        
		
		
		if(d.getCause().equals(DamageCause.FALL)) 
		{
	        if(playerclass.get(p.getUniqueId()) == 20) {
	        	p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 65*(int)p.getFallDistance(), p.getFallDistance(), 1, p.getFallDistance());
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1, 0);
	        	for(Entity e: p.getNearbyEntities(3, 2, 3)) {
	        		if(e instanceof LivingEntity) {
	        			LivingEntity le = (LivingEntity)e;
						if(le instanceof EnderDragon) {
		                    Arrow firstarrow = p.launchProjectile(Arrow.class);
		                    firstarrow.setDamage(0);
		                    firstarrow.remove();
							Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
								ar.setShooter(p);
								ar.setCritical(false);
								ar.setSilent(true);
								ar.setPickupStatus(PickupStatus.DISALLOWED);
								ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
							});
							enar.setDamage(player_damage.get(p.getName())*0.005*p.getFallDistance());								
						}
	        			p.setSprinting(true);
	        			le.damage(0,p);
	        			le.damage(player_damage.get(p.getName())*0.005*p.getFallDistance(),p);
	        			p.setSprinting(false);
	        		}
	        	}
	        	d.setCancelled(true);
	        }
		}
	}
	

	@EventHandler
	public void Swimer(EntityToggleSwimEvent ev) 
	{
		if(ev.getEntity() instanceof Player) {
			Player p = (Player) ev.getEntity();
			if(playerclass.get(p.getUniqueId()) == 20 || playerclass.get(p.getUniqueId()) == 19) {
				if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 30, false, false));
				}
				if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
				}
				if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 30, false, false));
				}
			}
		}
	}

	
	@EventHandler
	public void WaterBarrior(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player) 
		{
		Player p = (Player)d.getEntity();
			if(playerclass.get(p.getUniqueId()) == 20) {
				if(p.getLocation().getBlock().hasMetadata("wtb of "+p.getName()) || p.getLocation().add(0,1,0).getBlock().hasMetadata("wtb of "+p.getName())|| p.getLocation().add(0,-1,0).getBlock().hasMetadata("wtb of "+p.getName()))
					{
						d.setCancelled(true);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Barrior]").color(ChatColor.BOLD).color(ChatColor.AQUA).create());
					
					}
			}
		}
	}
	
	@EventHandler
	public void WaterBarrior(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 20) {
		final Location l  = p.getLocation().add(0, 1, 0);
		final Material m  = l.getBlock().getType();
		final Location l1  = p.getLocation().add(1, 1, 0);
		final Material m1  = l1.getBlock().getType();
		final Location l2  = p.getLocation().add(0, 1, 1);
		final Material m2  = l2.getBlock().getType();
		final Location l3  = p.getLocation().add(0, 1, -1);
		final Material m3  = l3.getBlock().getType();
		final Location l4  = p.getLocation().add(-1, 1, 0);
		final Material m4  = l4.getBlock().getType();
		final Location l5  = p.getLocation().add(-1, 1, -1);
		final Material m5  = l5.getBlock().getType();
		final Location l6  = p.getLocation().add(-1, 1, 1);
		final Material m6  = l6.getBlock().getType();
		final Location l7  = p.getLocation().add(1, 1, 1);
		final Material m7  = l7.getBlock().getType();
		final Location l8  = p.getLocation().add(1, 1, -1);
		final Material m8  = l8.getBlock().getType();
		int sec = 6;
			if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 30, false, false));
			}
			if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
			}
			if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 30, false, false));
			}
			if(p.isBlocking() && p.isSneaking() && !p.isRiptiding())
			{
				
				ev.setCancelled(true);
				if(rscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (rscooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use WaterBarrior").create());
	                }
	                else // if timer is done
	                {
	                    rscooldown.remove(p.getName()); // removing player from HashMap
		            	ArrayList<Location> line = new ArrayList<Location>();
	                    AtomicInteger j = new AtomicInteger();
						p.playSound(p.getLocation(), Sound.ENTITY_BOAT_PADDLE_WATER, 1, 0);
						p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1, 0);
	    				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2, false, false));
	                	Location pl = p.getLocation();
	                    for(double i = 0.1; i<6;i+=0.5) {
	                    	for(double an = 0; an<=2*Math.PI; an+=Math.PI/180) {
			                    	line.add(pl.clone().add(pl.getDirection().normalize().rotateAroundY(an).multiply(4.5)).add(0, i, 0));
		                    }
	                    }
	                    	
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    line.forEach(l ->{
					        		p.getWorld().spawnParticle(Particle.FALLING_WATER, l,5,0.1,0.1,0.1);
			                    });
			                }
						}, j.incrementAndGet()/50); 

	                	for(Entity e: p.getNearbyEntities(3.6,6,3.6)) {
							if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
								if(le instanceof EnderDragon) {
				                    Arrow firstarrow = p.launchProjectile(Arrow.class);
				                    firstarrow.setDamage(0);
				                    firstarrow.remove();
									Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
										a.setShooter(p);
										a.setCritical(false);
										a.setSilent(true);
										a.setPickupStatus(PickupStatus.DISALLOWED);
										a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
									});
									enar.setDamage(player_damage.get(p.getName())*0.65*(1+fsd.WaterBarrior.get(p.getUniqueId())*0.015));								
								}
								p.setSprinting(true);
								le.damage(0, p);
								le.damage(player_damage.get(p.getName())*0.65*(1+fsd.WaterBarrior.get(p.getUniqueId())*0.015), p);
								p.setSprinting(false);
							}
	                	}

	    				if(l.getBlock().isPassable()) {
	                        l.getBlock().setType(Material.WATER,false);
	                        l.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	l.getBlock().setType(Material.VOID_AIR);
				                	l.getBlock().setType(m);
		                            l.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
				                }
				            }, 80); 
	    				}
	    				if(l1.getBlock().isPassable()) {
	                        l1.getBlock().setType(Material.WATER,false);
	                        l1.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	l1.getBlock().setType(Material.VOID_AIR);
				                	l1.getBlock().setType(m1);
		                            l1.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
				                }
				            }, 80); 
	    				}
	    				if(l2.getBlock().isPassable()) {
	                        l2.getBlock().setType(Material.WATER,false);
	                        l2.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	l2.getBlock().setType(Material.VOID_AIR);
				                	l2.getBlock().setType(m2);
		                            l2.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
				                }
				            }, 80); 
	    				}
	    				if(l3.getBlock().isPassable()) {
	                        l3.getBlock().setType(Material.WATER,false);
	                        l3.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	l3.getBlock().setType(Material.VOID_AIR);
				                	l3.getBlock().setType(m3);
		                            l3.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
				                }
				            }, 80); 
	    				}
	    				if(l4.getBlock().isPassable()) {
	                        l4.getBlock().setType(Material.WATER,false);
	                        l4.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	l4.getBlock().setType(Material.VOID_AIR);
				                	l4.getBlock().setType(m4);
		                            l4.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
				                }
				            }, 80); 
	    				}
	    				if(l5.getBlock().isPassable()) {
	                        l5.getBlock().setType(Material.WATER,false);
	                        l5.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	l5.getBlock().setType(Material.VOID_AIR);
				                	l5.getBlock().setType(m5);
		                            l5.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
				                }
				            }, 80); 
	    				}
	    				if(l6.getBlock().isPassable()) {
	                        l6.getBlock().setType(Material.WATER,false);
	                        l6.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	l6.getBlock().setType(Material.VOID_AIR);
				                	l6.getBlock().setType(m6);
		                            l6.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
				                }
				            }, 80); 
	    				}
	    				if(l7.getBlock().isPassable()) {
	                        l7.getBlock().setType(Material.WATER,false);
	                        l7.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	l7.getBlock().setType(Material.VOID_AIR);
				                	l7.getBlock().setType(m7);
		                            l7.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
				                }
				            }, 80); 
	    				}
	    				if(l8.getBlock().isPassable()) {
	                        l8.getBlock().setType(Material.WATER,false);
	                        l8.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	l8.getBlock().setType(Material.VOID_AIR);
				                	l8.getBlock().setType(m8);
		                            l8.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
				                }
				            }, 80); 
	    				}
		                rscooldown.put(p.getName(), System.currentTimeMillis());
	                }
	                    
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	ArrayList<Location> line = new ArrayList<Location>();
                    AtomicInteger j = new AtomicInteger();
					p.playSound(p.getLocation(), Sound.ENTITY_BOAT_PADDLE_WATER, 1, 0);
					p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1, 0);
    				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2, false, false));
                	Location pl = p.getLocation();
                    for(double i = 0.1; i<6;i+=0.5) {
                    	for(double an = 0; an<=2*Math.PI; an+=Math.PI/180) {
		                    	line.add(pl.clone().add(pl.getDirection().normalize().rotateAroundY(an).multiply(4.5)).add(0, i, 0));
	                    }
                    }
                    	
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                    line.forEach(l ->{
				        		p.getWorld().spawnParticle(Particle.FALLING_WATER, l,5,0.1,0.1,0.1);
		                    });
		                }
					}, j.incrementAndGet()/50); 

                	for(Entity e: p.getNearbyEntities(3.6,6,3.6)) {
						if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)e;
							if(le instanceof EnderDragon) {
			                    Arrow firstarrow = p.launchProjectile(Arrow.class);
			                    firstarrow.setDamage(0);
			                    firstarrow.remove();
								Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
									a.setShooter(p);
									a.setCritical(false);
									a.setSilent(true);
									a.setPickupStatus(PickupStatus.DISALLOWED);
									a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
								});
								enar.setDamage(player_damage.get(p.getName())*0.65*(1+fsd.WaterBarrior.get(p.getUniqueId())*0.015));								
							}
							p.setSprinting(true);
							le.damage(0, p);
							le.damage(player_damage.get(p.getName())*0.65*(1+fsd.WaterBarrior.get(p.getUniqueId())*0.015), p);
							p.setSprinting(false);
						}
                	}

    				if(l.getBlock().isPassable()) {
                        l.getBlock().setType(Material.WATER,false);
                        l.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	l.getBlock().setType(Material.VOID_AIR);
			                	l.getBlock().setType(m);
	                            l.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
			                }
			            }, 80); 
    				}
    				if(l1.getBlock().isPassable()) {
                        l1.getBlock().setType(Material.WATER,false);
                        l1.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	l1.getBlock().setType(Material.VOID_AIR);
			                	l1.getBlock().setType(m1);
	                            l1.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
			                }
			            }, 80); 
    				}
    				if(l2.getBlock().isPassable()) {
                        l2.getBlock().setType(Material.WATER,false);
                        l2.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	l2.getBlock().setType(Material.VOID_AIR);
			                	l2.getBlock().setType(m2);
	                            l2.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
			                }
			            }, 80); 
    				}
    				if(l3.getBlock().isPassable()) {
                        l3.getBlock().setType(Material.WATER,false);
                        l3.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	l3.getBlock().setType(Material.VOID_AIR);
			                	l3.getBlock().setType(m3);
	                            l3.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
			                }
			            }, 80); 
    				}
    				if(l4.getBlock().isPassable()) {
                        l4.getBlock().setType(Material.WATER,false);
                        l4.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	l4.getBlock().setType(Material.VOID_AIR);
			                	l4.getBlock().setType(m4);
	                            l4.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
			                }
			            }, 80); 
    				}
    				if(l5.getBlock().isPassable()) {
                        l5.getBlock().setType(Material.WATER,false);
                        l5.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	l5.getBlock().setType(Material.VOID_AIR);
			                	l5.getBlock().setType(m5);
	                            l5.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
			                }
			            }, 80); 
    				}
    				if(l6.getBlock().isPassable()) {
                        l6.getBlock().setType(Material.WATER,false);
                        l6.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	l6.getBlock().setType(Material.VOID_AIR);
			                	l6.getBlock().setType(m6);
	                            l6.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
			                }
			            }, 80); 
    				}
    				if(l7.getBlock().isPassable()) {
                        l7.getBlock().setType(Material.WATER,false);
                        l7.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	l7.getBlock().setType(Material.VOID_AIR);
			                	l7.getBlock().setType(m7);
	                            l7.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
			                }
			            }, 80); 
    				}
    				if(l8.getBlock().isPassable()) {
                        l8.getBlock().setType(Material.WATER,false);
                        l8.getBlock().setMetadata("wtb of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	l8.getBlock().setType(Material.VOID_AIR);
			                	l8.getBlock().setType(m8);
	                            l8.getBlock().removeMetadata("wtb of " +p.getName(), RMain.getInstance());
			                }
			            }, 80); 
    				}
	                rscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
		}
	

	@EventHandler
	public void WetSwing(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 5;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 20) {
			if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD) && p.isSneaking()&& !p.isRiptiding() && !p.isBlocking())
			{
				
				ev.setCancelled(true);
				{
					if(prcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (prcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use WetSwing").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {							
		                    prcooldown.remove(p.getName()); // removing player from HashMap
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    ArrayList<Location> fill = new ArrayList<Location>();
		                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    AtomicInteger j = new AtomicInteger();
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.5f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_HURT_WATER, 1.0f, 1.5f);
		                    for(double an = Math.PI/2.5; an>-Math.PI/2.5; an-=Math.PI/180) {
		                    	Location pl = p.getLocation();
		                    	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(7.5));
		                    	line.add(pl);
		                    }
		                    line.forEach(l -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
						        		p.getWorld().spawnParticle(Particle.WATER_SPLASH, l,5,0.1,0.1,0.1,0);
						        		p.getWorld().spawnParticle(Particle.WATER_DROP, l,5,0.1,0.1,0.1,0);
						        		p.getWorld().spawnParticle(Particle.WATER_BUBBLE, l,5,0.1,0.1,0.1,0);
					                    for(double i = 0; i<6.5;i+=0.1) {
					                    	Location pl = p.getLocation();
					                    	Vector v = l.clone().toVector().subtract(pl.toVector()).normalize();
					                    	fill.add(pl.add(v.multiply(i)));
					                    }			          
					                }
								}, j.incrementAndGet()/900); 
		                    	
		                    });
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    fill.forEach(l ->{
				                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,2,1)) {
											if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
											{
												LivingEntity le = (LivingEntity)e;
												les.add(le);
											}
				                    	}
				                    });
				                }
							}, j.incrementAndGet()/900); 

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									for (LivingEntity le : les)
									{
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.935*(1+fsd.WetSwing.get(p.getUniqueId())*0.0433));								
										}
												p.setSprinting(true);
												le.damage(0, p);
												le.damage(player_damage.get(p.getName())*0.935*(1+fsd.WetSwing.get(p.getUniqueId())*0.0433), p);
												p.setSprinting(false);
									}
				                    
				                }
							}, j.incrementAndGet()/900); 
							prcooldown.put(p.getName(), System.currentTimeMillis());
						}
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	ArrayList<Location> line = new ArrayList<Location>();
	                    ArrayList<Location> fill = new ArrayList<Location>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    AtomicInteger j = new AtomicInteger();
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.5f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_HURT_WATER, 1.0f, 1.5f);
	                    for(double an = Math.PI/2.5; an>-Math.PI/2.5; an-=Math.PI/180) {
	                    	Location pl = p.getLocation();
	                    	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(7.5));
	                    	line.add(pl);
	                    }
	                    line.forEach(l -> {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					        		p.getWorld().spawnParticle(Particle.WATER_SPLASH, l,5,0.1,0.1,0.1,0);
					        		p.getWorld().spawnParticle(Particle.WATER_DROP, l,5,0.1,0.1,0.1,0);
					        		p.getWorld().spawnParticle(Particle.WATER_BUBBLE, l,5,0.1,0.1,0.1,0);
				                    for(double i = 0; i<6.5;i+=0.1) {
				                    	Location pl = p.getLocation();
				                    	Vector v = l.clone().toVector().subtract(pl.toVector()).normalize();
				                    	fill.add(pl.add(v.multiply(i)));
				                    }			          
				                }
							}, j.incrementAndGet()/900); 
	                    	
	                    });
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    fill.forEach(l ->{
			                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,2,1)) {
										if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
										{
											LivingEntity le = (LivingEntity)e;
											les.add(le);
										}
			                    	}
			                    });
			                }
						}, j.incrementAndGet()/900); 

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								for (LivingEntity le : les)
								{
									if(le instanceof EnderDragon) {
					                    Arrow firstarrow = p.launchProjectile(Arrow.class);
					                    firstarrow.setDamage(0);
					                    firstarrow.remove();
										Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
											a.setShooter(p);
											a.setCritical(false);
											a.setSilent(true);
											a.setPickupStatus(PickupStatus.DISALLOWED);
											a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
										});
										enar.setDamage(player_damage.get(p.getName())*0.935*(1+fsd.WetSwing.get(p.getUniqueId())*0.0433));								
									}
											p.setSprinting(true);
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*0.935*(1+fsd.WetSwing.get(p.getUniqueId())*0.0433), p);
											p.setSprinting(false);
								}
			                    
			                }
						}, j.incrementAndGet()/900); 
		                prcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					}
				}}
		}
	}
	

	@EventHandler
	public void RipCurrent(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 1;
	    
		
		
		
		if(playerclass.get(p.getUniqueId()) == 20 && fsd.RipCurrent.get(p.getUniqueId())>=1) {
			if(p.isRiptiding())
			{
				ev.setCancelled(true);
				{
					if(jmcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (jmcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use RipCurrent").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {							
		                	jmcooldown.remove(p.getName()); // removing player from HashMap

		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.5f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_HURT_WATER, 1.0f, 1.5f);
	                    	for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(),6,6,6)) {

	                    		if (e instanceof Player) 
								{
									
									Player p1 = (Player) e;
									try {
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
											continue;
										}
									}}
									catch(NullPointerException ne) {
										continue;
									}
								}
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
								{
									LivingEntity le = (LivingEntity)e;
									
									le.teleport(p.getLocation().add(p.getVelocity().normalize().multiply(5)));
									break;
								}
	                    	}
							jmcooldown.put(p.getName(), System.currentTimeMillis());
						}
		            }
		            else // if cooldown doesn't have players name in it
		            {


	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.5f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_HURT_WATER, 1.0f, 1.5f);
                    	for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(),6,6,6)) {

                    		if (e instanceof Player) 
							{
								
								Player p1 = (Player) e;
								try {
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										continue;
									}
								}}
								catch(NullPointerException ne) {
									continue;
								}
							}
							if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
								
								le.teleport(p.getLocation().add(p.getVelocity().normalize().multiply(5)));
								break;
							}
                    	}
						jmcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					}
				}}
		}
	}

	
	@EventHandler
	public void Javelin(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 8;
        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 20) {
			if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD)&&!p.isSneaking()&& !p.isOnGround() &&!p.isBlocking()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					if(eccooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (eccooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Javelin").create());
		                }
		                else // if timer is done
		                {
		                	eccooldown.remove(p.getName()); // removing player from HashMap

			            	Trident firstarrow = p.launchProjectile(Trident.class);
		                    firstarrow.setPickupStatus(PickupStatus.ALLOWED);
		                    firstarrow.setMetadata("jav of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    firstarrow.setGlowing(true);
		                    firstarrow.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 1f, 0.1f);
		                	p.playSound(p.getLocation(), Sound.ENTITY_SQUID_HURT, 0.8f, 2f);
		                	ArrayList<Location> line = new ArrayList<Location>();
		                    for(double d = 0.1; d <= 13; d += 0.2) {
			                    Location pl = p.getEyeLocation().clone();
								pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
								line.add(pl);
		                    }
		                    for(Location l : line) {
		                    	p.getWorld().spawnParticle(Particle.WATER_WAKE, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);
		                    	p.getWorld().spawnParticle(Particle.WATER_SPLASH, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);

		                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
								{
									if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
									{
										if (a instanceof Player) 
										{
											Player p1 = (Player) a;
											try {
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
													continue;
												}
											}}
											catch(NullPointerException ne) {
												continue;
											}
										}
										LivingEntity le = (LivingEntity)a;
										les.add(le);										
									}
								}
		                    }
		                	for(LivingEntity le : les) {
								if(le instanceof EnderDragon) {
									Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
										ar.setShooter(p);
										ar.setCritical(false);
										ar.setSilent(true);
										ar.setPickupStatus(PickupStatus.DISALLOWED);
										ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
									});
									enar.setDamage(player_damage.get(p.getName())*0.735*(1+fsd.Javelin.get(p.getUniqueId())*0.0453));								
								}
								p.setSprinting(true);
								le.damage(0,p);
								le.damage(player_damage.get(p.getName())*0.735*(1+fsd.Javelin.get(p.getUniqueId())*0.0453),p);
								p.setSprinting(false);	
		                	}
	                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {

				                    firstarrow.remove();
				                    
				                }
				            }, 160); 
						                	
		                    eccooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	Trident firstarrow = p.launchProjectile(Trident.class);
	                    firstarrow.setMetadata("jav of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    firstarrow.setGlowing(true);
	                    firstarrow.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 1f, 0.1f);
	                	p.playSound(p.getLocation(), Sound.ENTITY_SQUID_HURT, 0.8f, 2f);
	                	ArrayList<Location> line = new ArrayList<Location>();
	                    for(double d = 0.1; d <= 13; d += 0.2) {
		                    Location pl = p.getEyeLocation().clone();
							pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
							line.add(pl);
	                    }
	                    for(Location l : line) {
	                    	p.getWorld().spawnParticle(Particle.WATER_WAKE, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);
	                    	p.getWorld().spawnParticle(Particle.WATER_SPLASH, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);

	                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
							{
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
								{
									if (a instanceof Player) 
									{
										Player p1 = (Player) a;
										try {
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
												continue;
											}
										}}
										catch(NullPointerException ne) {
											continue;
										}
									}
									LivingEntity le = (LivingEntity)a;
									les.add(le);										
								}
							}
	                    }
	                	for(LivingEntity le : les) {
							if(le instanceof EnderDragon) {
								Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
									ar.setShooter(p);
									ar.setCritical(false);
									ar.setSilent(true);
									ar.setPickupStatus(PickupStatus.DISALLOWED);
									ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
								});
								enar.setDamage(player_damage.get(p.getName())*0.735*(1+fsd.Javelin.get(p.getUniqueId())*0.0453));								
							}
							p.setSprinting(true);
							le.damage(0,p);
							le.damage(player_damage.get(p.getName())*0.735*(1+fsd.Javelin.get(p.getUniqueId())*0.0453),p);
							p.setSprinting(false);	
	                	}
                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {

			                    firstarrow.remove();
			                    
			                }
			            }, 160); 
	                    eccooldown.put(p.getName(), System.currentTimeMillis());  
					}
		    					
				} 
			}
		}
	}

	@EventHandler
	public void Javelin(PlayerPickupArrowEvent ev) 
	{
		Player p = ev.getPlayer();
			if(ev.getArrow().hasMetadata("jav of "+p.getName())) {
					eccooldown.computeIfPresent(p.getName(), (k,v) -> v-5600);
					p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RETURN, 1f, 2f);
					ev.getArrow().remove();
					ev.setCancelled(true);
			}
			else if (ev.getArrow().getShooter() != p && ev.getArrow().hasMetadata("fake")){
				ev.setCancelled(true);
			}
	}
	
	@EventHandler
	public void WaterSpear(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 8;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 20) {
			if(!p.isSneaking() && p.isBlocking()&& !p.isRiptiding())
			{
				ev.setCancelled(true);
				
				if(thcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (thcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use WaterSpear").create());
	                }
	                else // if timer is done
	                {
	                    thcooldown.remove(p.getName()); // removing player from HashMap

	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.setPickupStatus(PickupStatus.DISALLOWED);
	                    firstarrow.remove();
		            	Location l = p.getLocation();
	                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    for(int i =0; i<6; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.3f, 2f);
					                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 1f, 2f);
					                	ArrayList<Location> line = new ArrayList<Location>();
					                    for(double d = 0.1; d <= 6.1; d += 0.2) {
						                    Location pl = p.getEyeLocation().clone();
											pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
											line.add(pl);
					                    }
					                    for(Location l : line) {
					                    	p.getWorld().spawnParticle(Particle.WATER_WAKE, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);
					                    	p.getWorld().spawnParticle(Particle.WATER_SPLASH, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);
					                    	p.getWorld().spawnParticle(Particle.CRIT, l.add(0, -0.289, 0),1, 0.05,0.05,0.05,0);

					                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
											{
												if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
												{
													if (a instanceof Player) 
													{
														Player p1 = (Player) a;
														try {
														if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
														if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
															{
																continue;
															}
														}}
														catch(NullPointerException ne) {
															continue;
														}
													}
													LivingEntity le = (LivingEntity)a;
													les.add(le);										
												}
											}
					                    }
					                }
		                	   }, i*3); 
						}
	                    for(int i =0; i<6; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	for(LivingEntity le : les) {
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
													ar.setShooter(p);
													ar.setCritical(false);
													ar.setSilent(true);
													ar.setPickupStatus(PickupStatus.DISALLOWED);
													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.453*(1+fsd.WaterSpear.get(p.getUniqueId())*0.026));								
											}
											p.setSprinting(true);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName())*0.453*(1+fsd.WaterSpear.get(p.getUniqueId())*0.026),p);
											p.setSprinting(false);	
					                	}
					                	
					                }
		                	   }, i*3+1/10); 
	   					}

						thcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    Arrow firstarrow = p.launchProjectile(Arrow.class);
                    firstarrow.setDamage(0);
                    firstarrow.setPickupStatus(PickupStatus.DISALLOWED);
                    firstarrow.remove();
	            	Location l = p.getLocation();
                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(int i =0; i<6; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.3f, 2f);
				                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 1f, 2f);
				                	ArrayList<Location> line = new ArrayList<Location>();
				                    for(double d = 0.1; d <= 6.1; d += 0.2) {
					                    Location pl = p.getEyeLocation().clone();
										pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
										line.add(pl);
				                    }
				                    for(Location l : line) {
				                    	p.getWorld().spawnParticle(Particle.WATER_WAKE, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);
				                    	p.getWorld().spawnParticle(Particle.WATER_SPLASH, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0);
				                    	p.getWorld().spawnParticle(Particle.CRIT, l.add(0, -0.289, 0),1, 0.05,0.05,0.05,0);

				                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
										{
											if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
											{
												if (a instanceof Player) 
												{
													Player p1 = (Player) a;
													try {
													if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
													if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
														{
															continue;
														}
													}}
													catch(NullPointerException ne) {
														continue;
													}
												}
												LivingEntity le = (LivingEntity)a;
												les.add(le);										
											}
										}
				                    }
				                }
	                	   }, i*3); 
					}
                    for(int i =0; i<6; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	for(LivingEntity le : les) {
										if(le instanceof EnderDragon) {
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
												ar.setShooter(p);
												ar.setCritical(false);
												ar.setSilent(true);
												ar.setPickupStatus(PickupStatus.DISALLOWED);
												ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.453*(1+fsd.WaterSpear.get(p.getUniqueId())*0.026));								
										}
										p.setSprinting(true);
										le.damage(0,p);
										le.damage(player_damage.get(p.getName())*0.453*(1+fsd.WaterSpear.get(p.getUniqueId())*0.026),p);
										p.setSprinting(false);	
				                	}
				                	
				                }
	                	   }, i*3+1/10); 
   					}

					thcooldown.put(p.getName(), System.currentTimeMillis());  
				}
			
			}
		}
	}
	
		
	
	
	@EventHandler
	public void TripleHit(EntityDamageByEntityEvent d) 
	{
		int sec = 3;
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&& !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity fle = (LivingEntity) d.getEntity();
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 20) {				
		if(p.getAttackCooldown()>=0) 
			{	
			
				if((p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD) && (p.isSneaking()) && !(p.isSprinting()) && (p.getCooldown(Material.BARRIER) <=0))
					{
					if(pncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (pncooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use TripleHit").create());
				        }
			            else // if timer is done
			            {
			                pncooldown.remove(p.getName()); // removing player from HashMap
			                Arrow firstarrow = p.launchProjectile(Arrow.class);
		                    firstarrow.setDamage(0);
		                    firstarrow.setPickupStatus(PickupStatus.DISALLOWED);
		                    firstarrow.remove();
							p.setCooldown(Material.BARRIER, 1);
		                    for(int i =0; i<2; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
					                    	p.getWorld().spawnParticle(Particle.WATER_WAKE, fle.getLocation(),50, 1,1,1,0);
					                    	p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, fle.getLocation(),10, 1,1,1,0);
					                    	p.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, fle.getLocation(),20, 1,1,1,1);
						                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.3f, 2f);
						                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 2f);
				                			LivingEntity le = (LivingEntity)fle;
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
													ar.setShooter(p);
													ar.setCritical(false);
													ar.setSilent(true);
													ar.setPickupStatus(PickupStatus.DISALLOWED);
													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.66*(1+fsd.TripleHit.get(p.getUniqueId())*0.0435));								
											}
											le.setNoDamageTicks(0);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName())*0.66*(1+fsd.TripleHit.get(p.getUniqueId())*0.0435),p);
					                		}
			                	   }, i*3+3); 
							}
				            pncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {

	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.setPickupStatus(PickupStatus.DISALLOWED);
	                    firstarrow.remove();
						p.setCooldown(Material.BARRIER, 1);
	                    for(int i =0; i<3; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
				                    	p.getWorld().spawnParticle(Particle.WATER_WAKE, fle.getLocation(),50, 1,1,1,0);
				                    	p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, fle.getLocation(),10, 1,1,1,0);
				                    	p.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, fle.getLocation(),20, 1,1,1,1);
					                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.3f, 2f);
					                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 2f);
			                			LivingEntity le = (LivingEntity)fle;
										if(le instanceof EnderDragon) {
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
												ar.setShooter(p);
												ar.setCritical(false);
												ar.setSilent(true);
												ar.setPickupStatus(PickupStatus.DISALLOWED);
												ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.66*(1+fsd.TripleHit.get(p.getUniqueId())*0.0435));								
										}
										le.setNoDamageTicks(0);
										le.damage(0,p);
										le.damage(player_damage.get(p.getName())*0.66*(1+fsd.TripleHit.get(p.getUniqueId())*0.0435),p);
				                		}
		                	   }, i*3); 
						}
			            pncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
					}
			}
		}}
	}
		
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		final Location l = p.getLocation();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 20 && ((is.getType()==Material.TRIDENT)||(is.getType()==Material.SHIELD))&& p.isSneaking())
			{
				ev.setCancelled(true);
				if(aultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (aultcooldown.get(p.getName())/1000 + 35) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Grand Waves").create());
		            }
	                else // if timer is done
	                {
	                    aultcooldown.remove(p.getName()); // removing player from HashMap
						p.setSneaking(false);
	                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_AMBIENT, 1.0f, 2f);
						p.setInvulnerable(true);
						ArrayList<Location> s1 = new ArrayList<Location>();
						ArrayList<Location> s2 = new ArrayList<Location>();
						ArrayList<Location> fj = new ArrayList<Location>();
	                    AtomicInteger j = new AtomicInteger();	
	                    AtomicInteger k = new AtomicInteger();	
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    for(double angle= -Math.PI/5; angle<Math.PI/5; angle += Math.PI/90) {
	                    	for(double dou = 0.1; dou <10.1; dou+=0.2) {
	                    		Location pl = p.getLocation().clone();
	                    		s1.add(pl.add(p.getLocation().getDirection().normalize().rotateAroundY(angle).multiply(dou)));   
	                    	}
	                    }
	                    for(double angle= Math.PI/5; angle>-Math.PI/5; angle -= Math.PI/90) {
	                    	for(double dou = 0.1; dou <10.1; dou+=0.2) {
	                    		Location pl = p.getLocation().clone();
	                    		s2.add(pl.add(p.getLocation().getDirection().normalize().rotateAroundY(angle).multiply(dou)));   
	                    	}
	                    }
						for(int i = 0; i <2; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 0);
				                    p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_SPLASH, 1.0f, 0f);
									p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 2);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
				                    s1.forEach(l ->{
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												p.getWorld().spawnParticle(Particle.FALLING_WATER, l, 2, 1,1,1,0);
												p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 1, 1,1,1,0.1);
													for(Entity e : p.getWorld().getNearbyEntities(l,2, 2, 2)) {
														if(p!=e && e instanceof LivingEntity) {
															LivingEntity le = (LivingEntity)e;
															les.add(le);
														}
													}
							                    
							                }
							            }, j.incrementAndGet()/4000);  
				                    });
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
						                    for(LivingEntity le: les) {
												if(le instanceof EnderDragon) {
								                    Arrow firstarrow = p.launchProjectile(Arrow.class);
								                    firstarrow.setDamage(0);
								                    firstarrow.remove();
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
														a.setShooter(p);
														a.setCritical(false);
														a.setSilent(true);
														a.setPickupStatus(PickupStatus.DISALLOWED);
														a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(player_damage.get(p.getName())*0.3);								
												}
												p.setSprinting(true);
												le.damage(0,p);
												le.damage(player_damage.get(p.getName())*0.3,p);	
												p.setSprinting(false);					                    	
						                    }
						                    
						                }
						            }, j.incrementAndGet()/4000);  
				                }
				            }, i*8);
		                    j.set(0);
	                    }
						for(int i = 0; i <2; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 0);
				                    p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_SPLASH, 1.0f, 0f);
									p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 2);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
				                    s2.forEach(l ->{
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												p.getWorld().spawnParticle(Particle.FALLING_WATER, l, 2, 1,1,1,0);
												p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 1, 1,1,1,0.1);
													for(Entity e : p.getWorld().getNearbyEntities(l,2, 2, 2)) {
														if(p!=e && e instanceof LivingEntity) {
															LivingEntity le = (LivingEntity)e;
															les.add(le);
														}
													}
							                    
							                }
							            }, k.incrementAndGet()/4000);  
				                    });
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
						                    for(LivingEntity le: les) {
												if(le instanceof EnderDragon) {
								                    Arrow firstarrow = p.launchProjectile(Arrow.class);
								                    firstarrow.setDamage(0);
								                    firstarrow.remove();
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
														a.setShooter(p);
														a.setCritical(false);
														a.setSilent(true);
														a.setPickupStatus(PickupStatus.DISALLOWED);
														a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(player_damage.get(p.getName())*0.3);								
												}
												p.setSprinting(true);
												le.damage(0,p);
												le.damage(player_damage.get(p.getName())*0.3,p);	
												p.setSprinting(false);					                    	
						                    }
						                    
						                }
						            }, k.incrementAndGet()/4000);  
				                }
				            }, i*8 +4);
		                    k.set(0);
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 0);
			                    p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_SPLASH, 1.0f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 2);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_DEATH_LAND, 1.0f, 0f);
		                    	for(double dou = 0.1; dou <13.1; dou+=0.2) {
		                    		Location pl = p.getLocation().clone();
		                    		fj.add(pl.add(p.getLocation().getDirection().normalize().multiply(dou)));   
				                    fj.forEach(l ->{
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												p.getWorld().spawnParticle(Particle.FALLING_WATER, l, 2, 1,1,1,0);
												p.getWorld().spawnParticle(Particle.WATER_BUBBLE, l, 1, 1,1,1,0.1);
													for(Entity e : p.getWorld().getNearbyEntities(l,3.5, 3.5, 3.5)) {
														if(p!=e && e instanceof LivingEntity) {
															LivingEntity le = (LivingEntity)e;
															les.add(le);
														}
													}
							                    
							                }
							            }, j.incrementAndGet()/4000);  
				                    });
		                    	}
		                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                    for(LivingEntity le: les) {
											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*3);								
											}
											p.setSprinting(true);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName())*3,p);	
											p.setSprinting(false);					                    	
					                    }
					                    
					                }
					            }, k.incrementAndGet()/4000);  
								
			                }
			            }, 22);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								p.setInvulnerable(false);
								
			                }
			            }, 25);
		                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.setSneaking(false);
                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_AMBIENT, 1.0f, 2f);
					p.setInvulnerable(true);
					ArrayList<Location> s1 = new ArrayList<Location>();
					ArrayList<Location> s2 = new ArrayList<Location>();
					ArrayList<Location> fj = new ArrayList<Location>();
                    AtomicInteger j = new AtomicInteger();	
                    AtomicInteger k = new AtomicInteger();	
                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(double angle= -Math.PI/5; angle<Math.PI/5; angle += Math.PI/90) {
                    	for(double dou = 0.1; dou <10.1; dou+=0.2) {
                    		Location pl = p.getLocation().clone();
                    		s1.add(pl.add(p.getLocation().getDirection().normalize().rotateAroundY(angle).multiply(dou)));   
                    	}
                    }
                    for(double angle= Math.PI/5; angle>-Math.PI/5; angle -= Math.PI/90) {
                    	for(double dou = 0.1; dou <10.1; dou+=0.2) {
                    		Location pl = p.getLocation().clone();
                    		s2.add(pl.add(p.getLocation().getDirection().normalize().rotateAroundY(angle).multiply(dou)));   
                    	}
                    }
					for(int i = 0; i <2; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 0);
			                    p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_SPLASH, 1.0f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 2);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
			                    s1.forEach(l ->{
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											p.getWorld().spawnParticle(Particle.FALLING_WATER, l, 2, 1,1,1,0);
											p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 1, 1,1,1,0.1);
												for(Entity e : p.getWorld().getNearbyEntities(l,2, 2, 2)) {
													if(p!=e && e instanceof LivingEntity) {
														LivingEntity le = (LivingEntity)e;
														les.add(le);
													}
												}
						                    
						                }
						            }, j.incrementAndGet()/4000);  
			                    });
		                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                    for(LivingEntity le: les) {
											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName()));								
											}
											p.setSprinting(true);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName()),p);	
											p.setSprinting(false);					                    	
					                    }
					                    
					                }
					            }, j.incrementAndGet()/4000);  
			                }
			            }, i*8);
	                    j.set(0);
                    }
					for(int i = 0; i <2; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 0);
			                    p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_SPLASH, 1.0f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 2);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
			                    s2.forEach(l ->{
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											p.getWorld().spawnParticle(Particle.FALLING_WATER, l, 2, 1,1,1,0);
											p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 1, 1,1,1,0.1);
												for(Entity e : p.getWorld().getNearbyEntities(l,2, 2, 2)) {
													if(p!=e && e instanceof LivingEntity) {
														LivingEntity le = (LivingEntity)e;
														les.add(le);
													}
												}
						                    
						                }
						            }, k.incrementAndGet()/4000);  
			                    });
		                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                    for(LivingEntity le: les) {
											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName()));								
											}
											p.setSprinting(true);
											le.damage(0,p);
											le.damage(player_damage.get(p.getName()),p);	
											p.setSprinting(false);					                    	
					                    }
					                    
					                }
					            }, k.incrementAndGet()/4000);  
			                }
			            }, i*8 +4);
	                    k.set(0);
                    }
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 0);
		                    p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_SPLASH, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 2);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_DEATH_LAND, 1.0f, 0f);
	                    	for(double dou = 0.1; dou <13.1; dou+=0.2) {
	                    		Location pl = p.getLocation().clone();
	                    		fj.add(pl.add(p.getLocation().getDirection().normalize().multiply(dou)));   
			                    fj.forEach(l ->{
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											p.getWorld().spawnParticle(Particle.FALLING_WATER, l, 2, 1,1,1,0);
											p.getWorld().spawnParticle(Particle.WATER_BUBBLE, l, 1, 1,1,1,0.1);
												for(Entity e : p.getWorld().getNearbyEntities(l,3.5, 3.5, 3.5)) {
													if(p!=e && e instanceof LivingEntity) {
														LivingEntity le = (LivingEntity)e;
														les.add(le);
													}
												}
						                    
						                }
						            }, j.incrementAndGet()/4000);  
			                    });
	                    	}
	                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                    for(LivingEntity le: les) {
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*6);								
										}
										p.setSprinting(true);
										le.damage(0,p);
										le.damage(player_damage.get(p.getName())*6,p);	
										p.setSprinting(false);					                    	
				                    }
				                    
				                }
				            }, k.incrementAndGet()/4000);  
							
		                }
		            }, 22);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							p.setInvulnerable(false);
							
		                }
		            }, 25);
	                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}	
			
			
    }


	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

	    
		
		
		if(playerclass.get(p.getUniqueId()) == 20)
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD)
			{
					e.setCancelled(true);
			}
		}
		
	}

	@EventHandler
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 20) {
				if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD)
				{
						player_damage.put(p.getName(),10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
			}
			
		}
	}

	@EventHandler
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		Entity e = d.getEntity();
        

		
		
		
		if(playerclass.get(p.getUniqueId()) == 20) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD)
			{
					player_damage.put(p.getName(), 10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					if (e.getType() == EntityType.ZOMBIE || e.getType() == EntityType.ZOMBIE_HORSE || e.getType() ==EntityType.ZOMBIE_VILLAGER || e.getType() == EntityType.ZOMBIFIED_PIGLIN|| e.getType() == EntityType.SKELETON || e.getType() == EntityType.SKELETON_HORSE || e.getType() == EntityType.WITHER_SKELETON || e.getType() == EntityType.HUSK || e.getType() == EntityType.WITHER || e.getType() == EntityType.STRAY || e.getType() == EntityType.PHANTOM || e.getType() == EntityType.DROWNED)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD)*2.5);
					}
					if (e.getType() == EntityType.SPIDER || e.getType() == EntityType.CAVE_SPIDER || e.getType() == EntityType.BEE || e.getType() == EntityType.SILVERFISH || e.getType() == EntityType.ENDERMITE)
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS)*2.5);
												}
					if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					{
						player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
					}
			}
		}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow ar = (Arrow)d.getDamager();
			Entity e = d.getEntity();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
			    
				
				
				
				if(playerclass.get(p.getUniqueId()) == 20) {

					if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT || p.getInventory().getItemInMainHand().getType()==Material.SHIELD)
					{
							player_damage.put(p.getName(), 10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							if (e.getType() == EntityType.ZOMBIE || e.getType() == EntityType.ZOMBIE_HORSE || e.getType() ==EntityType.ZOMBIE_VILLAGER || e.getType() == EntityType.ZOMBIFIED_PIGLIN|| e.getType() == EntityType.SKELETON || e.getType() == EntityType.SKELETON_HORSE || e.getType() == EntityType.WITHER_SKELETON || e.getType() == EntityType.HUSK || e.getType() == EntityType.WITHER || e.getType() == EntityType.STRAY || e.getType() == EntityType.PHANTOM || e.getType() == EntityType.DROWNED)
							{
								player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD)*2.5);
							}
							if (e.getType() == EntityType.SPIDER || e.getType() == EntityType.CAVE_SPIDER || e.getType() == EntityType.BEE || e.getType() == EntityType.SILVERFISH || e.getType() == EntityType.ENDERMITE)
							{
								player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS)*2.5);
														}
							if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
							{
								player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
							}
					}
				}
			}
		}
	}
	@EventHandler
	public void Splash(EntityDamageByEntityEvent d)
	{
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
        
		

		
		
		if(playerclass.get(p.getUniqueId()) == 20) {
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT) {
				if(p.getInventory().getItemInMainHand().getEnchantments().isEmpty()) {
					p.getInventory().getItemInMainHand().addEnchantment(Enchantment.RIPTIDE, 3);
				}
			}
			if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT|| p.getInventory().getItemInMainHand().getType()==Material.SHIELD)
			{
				if(p==le) {d.setCancelled(true);}
				p.getWorld().spawnParticle(Particle.WATER_SPLASH, le.getLocation(), 50, 3,3,3,0);
				p.getWorld().playSound(le.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.356f,1);
				if(p.getCooldown(Material.STRUCTURE_VOID)<=0) {
    				p.setCooldown(Material.STRUCTURE_VOID, 1);
					le.damage(0,p);
					for(Entity e: le.getNearbyEntities(2.5, 2.5, 2.5)) {
	            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
	            			LivingEntity lle = (LivingEntity)e;
							if(lle instanceof EnderDragon) {
			                    Arrow firstarrow = p.launchProjectile(Arrow.class);
			                    firstarrow.setDamage(0);
			                    firstarrow.remove();
								Arrow enar = (Arrow) p.getWorld().spawn(lle.getLocation().add(0, 5.163, 0), Arrow.class, a->{
									a.setShooter(p);
									a.setCritical(false);
									a.setSilent(true);
									a.setPickupStatus(PickupStatus.DISALLOWED);
									a.setVelocity(lle.getLocation().clone().add(0, -1, 0).toVector().subtract(lle.getLocation().toVector()).normalize().multiply(2.6));
								});
								enar.setDamage(player_damage.get(p.getName())*0.8);								
							}
	            			lle.damage(d.getDamage()*0.8,p);
						}
						
					}
				}
				d.setDamage(d.getDamage()*1.1*(1+fsd.Splash.get(p.getUniqueId())*0.035));
			}
			}
		}
		
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
		Projectile ar = (Projectile)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
        
		

		
		
		if(ar.getShooter() instanceof Player) {
			Player p = (Player) ar.getShooter();
			if(playerclass.get(p.getUniqueId()) == 20) {
				if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT) {
					if(p.getInventory().getItemInMainHand().getEnchantments() == null) {
						p.getInventory().getItemInMainHand().addEnchantment(Enchantment.RIPTIDE, 3);
					}
				}
				if(p.getInventory().getItemInMainHand().getType()==Material.TRIDENT|| p.getInventory().getItemInMainHand().getType()==Material.SHIELD)
				{
					if(p==le) {d.setCancelled(true);}
					p.getWorld().spawnParticle(Particle.WATER_SPLASH, le.getLocation(), 50, 3,3,3,0);
					p.getWorld().playSound(le.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.356f,1);
					if(p.getCooldown(Material.STRUCTURE_VOID)<=0) {
        				p.setCooldown(Material.STRUCTURE_VOID, 1);
						le.damage(0,p);
						for(Entity e: le.getNearbyEntities(2.5, 2.5, 2.5)) {
		            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
		            			LivingEntity lle = (LivingEntity)e;
								if(lle instanceof EnderDragon) {
				                    Arrow firstarrow = p.launchProjectile(Arrow.class);
				                    firstarrow.setDamage(0);
				                    firstarrow.remove();
									Arrow enar = (Arrow) p.getWorld().spawn(lle.getLocation().add(0, 5.163, 0), Arrow.class, a->{
										a.setShooter(p);
										a.setCritical(false);
										a.setSilent(true);
										a.setPickupStatus(PickupStatus.DISALLOWED);
										a.setVelocity(lle.getLocation().clone().add(0, -1, 0).toVector().subtract(lle.getLocation().toVector()).normalize().multiply(2.6));
									});
									enar.setDamage(player_damage.get(p.getName())*0.8);								
								}
		            			lle.damage(d.getDamage()*0.8,p);
							}
							
						}
					}
					d.setDamage(d.getDamage()*1.1*(1+fsd.Splash.get(p.getUniqueId())*0.035));
				}
				}
		}
		}
		if(d.getDamager() instanceof Trident && d.getEntity() instanceof LivingEntity) 
		{
			Trident ar = (Trident)d.getDamager();
	        
			
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				if(ar.hasMetadata("jav of "+p.getName())) {
					d.setDamage(player_damage.get(p.getName())*0.535*(1+fsd.Javelin.get(p.getUniqueId())*0.0233)*(1+fsd.Splash.get(p.getUniqueId())*0.035));
				}
			}
		}
	}
}



