package io.github.chw3021.angler;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.party.PartyData;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.TreeSpecies;
import org.bukkit.Particle.DustOptions;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Cod;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.PufferFish;
import org.bukkit.entity.Salmon;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Trident;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.Turtle;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Angskills implements Serializable, Listener {
	

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
	private HashMap<String, Integer> drunk = new HashMap<String, Integer>();

	
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private AngSkillsData fsd = new AngSkillsData(AngSkillsData.loadData(path +"/plugins/RPGskills/AngSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		AngSkillsData f = new AngSkillsData(AngSkillsData.loadData(path +"/plugins/RPGskills/AngSkillsData.data"));
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Angskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				AngSkillsData f = new AngSkillsData(AngSkillsData.loadData(path +"/plugins/RPGskills/AngSkillsData.data"));
				fsd = f;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		AngSkillsData f = new AngSkillsData(AngSkillsData.loadData(path +"/plugins/RPGskills/AngSkillsData.data"));
		fsd = f;
		
	}




	@EventHandler
	public void Bait(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			int sec = 4;
			
			
			if(playerclass.get(p.getUniqueId()) == 22) {
				if(e.getEntity() instanceof FishHook)
				{
					FishHook fh = (FishHook) e.getEntity();
					fh.setMinWaitTime(0);
					fh.setMaxWaitTime(60);
					fh.setGravity(true);
					fh.setVelocity(fh.getVelocity().normalize().multiply(2));
					fh.setApplyLure(true);
					p.setCooldown(Material.STRUCTURE_VOID, 9);
					if(p.isSneaking()) {

						if(pncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            long timer = (pncooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Bait").create());
					        }
				            else // if timer is done
				            {
				            	pncooldown.remove(p.getName()); // removing player from HashMap
								fh.setVelocity(fh.getVelocity().normalize().multiply(0.5));
				            	for(int i =0; i<10; i++) {
				                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
												p.getWorld().playSound(fh.getLocation(), Sound.ENTITY_FISHING_BOBBER_SPLASH, 0.5f, 2f);
												p.getWorld().playSound(fh.getLocation(), Sound.ENTITY_SLIME_JUMP, 0.5f, 2f);
												p.getWorld().spawnParticle(Particle.WATER_BUBBLE, fh.getLocation(), 222, 2,2,2, 0); 
												p.getWorld().spawnParticle(Particle.WATER_SPLASH, fh.getLocation(), 100, 2,2,2, 0); 
												for (Entity a : p.getWorld().getNearbyEntities(fh.getLocation(), 2.5, 2.5, 2.5))
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
													if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
													{
														LivingEntity le = (LivingEntity)a;
														le.teleport(fh);
														Holding.holding(p, le, 20l);
														le.teleport(fh);
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
															enar.setDamage(player_damage.get(p.getName())*0.08);								
														}
														p.setSprinting(true);
														le.damage(0, p);
														le.damage(player_damage.get(p.getName())*0.08, p);
														p.setSprinting(false);
													}
												}
							                }
							            }, i*2); 
				                }
				                pncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {
							fh.setVelocity(fh.getVelocity().normalize().multiply(0.5));
				        	for(int i =0; i<10; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
											p.getWorld().playSound(fh.getLocation(), Sound.ENTITY_FISHING_BOBBER_SPLASH, 0.5f, 2f);
											p.getWorld().playSound(fh.getLocation(), Sound.ENTITY_SLIME_JUMP, 0.5f, 2f);
											p.getWorld().spawnParticle(Particle.WATER_BUBBLE, fh.getLocation(), 222, 2,2,2, 0); 
											p.getWorld().spawnParticle(Particle.WATER_SPLASH, fh.getLocation(), 100, 2,2,2, 0); 
											for (Entity a : p.getWorld().getNearbyEntities(fh.getLocation(), 2.5, 2.5, 2.5))
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
												if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
												{
													LivingEntity le = (LivingEntity)a;
													le.teleport(fh);
													Holding.holding(p, le, 20l);
													le.teleport(fh);
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
														enar.setDamage(player_damage.get(p.getName())*0.08);								
													}
													p.setSprinting(true);
													le.damage(0, p);
													le.damage(player_damage.get(p.getName())*0.08, p);
													p.setSprinting(false);
												}
											}
						                }
						            }, i*2); 
			                }
			                pncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
					}
				}
			}
			
		}
	}


	@EventHandler
	public void Fishing(PlayerFishEvent d) 
	{
		Player p = d.getPlayer();
		int sec = 4;
		
		
		if(playerclass.get(p.getUniqueId()) == 22) {
			p.setCooldown(Material.STRUCTURE_VOID, 13);
			if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
			}
			if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
			}
			if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 3, false, false));
			}
			if(d.getCaught() instanceof LivingEntity) {
				final Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(1.9));
					if(rscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (rscooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Fishing").create());
				        }
			            else // if timer is done
			            {
			            	rscooldown.remove(p.getName()); // removing player from HashMap
			                for(Entity e : d.getHook().getNearbyEntities(4.3, 4.32, 4.3)) {
			                	if (e instanceof Player) 
			    				{
			    					Player p1 = (Player) e;
			    					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
			    					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
			    						{
			    						continue;
			    						}
			    					}
			    				}
			            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
			    				{
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
			    							enar.setDamage(player_damage.get(p.getName())*0.56+(1));
			    						}		  
			    						p.setSprinting(true);
			    						le.damage(0, p);
			    						le.damage(player_damage.get(p.getName())*0.56+(1), p);	
			    						p.setSprinting(false);
			    						le.teleport(tl);
			    						Holding.superholding(p, le, 10l);
			    				}
			                }
			            	rscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
		                for(Entity e : d.getHook().getNearbyEntities(4.3, 4.32, 4.3)) {
		                	if (e instanceof Player) 
		    				{
		    					Player p1 = (Player) e;
		    					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
		    					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
		    						{
		    						continue;
		    						}
		    					}
		    				}
		            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
		    				{
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
		    							enar.setDamage(player_damage.get(p.getName())*0.56+(1));
		    						}		  
		    						p.setSprinting(true);
		    						le.damage(0, p);
		    						le.damage(player_damage.get(p.getName())*0.56+(1), p);	
		    						p.setSprinting(false);
		    						le.teleport(tl);
		    						Holding.superholding(p, le, 10l);
		    				}
		                }
			        	rscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
				
			}
		}
	}
	
	@EventHandler
	public void Whipping(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
	    
		
		
		if((ac == Action.LEFT_CLICK_AIR || ac==Action.LEFT_CLICK_BLOCK)  && playerclass.get(p.getUniqueId()) == 22 && p.getCooldown(Material.STRUCTURE_VOID)<=0 && !p.isSneaking()) {

				p.setCooldown(Material.STRUCTURE_VOID, 9);
				p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 100,2,2,2);
				final Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(2.7));
				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 10,2,1,2);
				p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SLIME_BLOCK_HIT, 0.5f, 2f);
				p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
				for(Entity e: p.getWorld().getNearbyEntities(tl, 2.5,2.5,2.5)) {
					
        		if (e instanceof Player) 
				{
					Player p1 = (Player) e;
					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
						{
						continue;
						}
					}
				}
        		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
				{
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
							enar.setDamage(player_damage.get(p.getName())*0.56*(1));
						}		  
						p.setSprinting(true);
						le.damage(0, p);
						le.damage(player_damage.get(p.getName())*0.56*(1), p);	
						p.setSprinting(false);
						Holding.holding(p, le, 3l);
						le.teleport(tl);
								
				}
			}
				for (Entity e : p.getNearbyEntities(6, 3, 6))
				{
					if (e instanceof Player) 
					{
						Player p1 = (Player) e;
						if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
						if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
							{
							p.getWorld().spawnParticle(Particle.WATER_SPLASH, p1.getLocation(), 60,2,2,2);
								p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
								continue;
							}
						}
					}
				}
		}
	}

	@EventHandler
	public void CoralRoots(EntityDamageByEntityEvent d) 
	{
		int sec = 4;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{	
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			final Location lel = le.getLocation();
			
			if(p.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD )
			{
			if(playerclass.get(p.getUniqueId()) == 22&& (p.isSneaking()) && !(p.isSprinting())) {
				
				if(prcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (prcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
	                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use CoralRoots").create());
		            }
		            else // if timer is done
		            {
		            	prcooldown.remove(p.getName()); // removing player from HashMap
		            	AtomicInteger j = new AtomicInteger();
		            	for(int i = 0 ; i <4 ; i++) {

			            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 2f);
									p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 1f);
									p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 0f);
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 0.5f, 2f);
									p.getWorld().spawnParticle(Particle.WATER_BUBBLE, lel, 100, 3,3,3, 0); 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.incrementAndGet(), 3,3,3, 0, Material.BUBBLE_CORAL_BLOCK.createBlockData()); 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,3,3, 0, Material.BRAIN_CORAL_BLOCK.createBlockData()); 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,3,3, 0, Material.FIRE_CORAL_BLOCK.createBlockData()); 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,3,3, 0, Material.HORN_CORAL_BLOCK.createBlockData()); 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,3,3, 0, Material.TUBE_CORAL_BLOCK.createBlockData()); 
									for (Entity a : p.getWorld().getNearbyEntities(lel,j.get(),j.get(),j.get()))
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
										if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
										{
											LivingEntity le = (LivingEntity)a;
											Holding.holding(p, le, 20l);
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
												enar.setDamage(player_damage.get(p.getName())*0.443);								
											}
											p.setSprinting(true);
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*0.443, p);
											p.setSprinting(false);
											Item root = p.getWorld().dropItem(le.getLocation(), new ItemStack(Material.BRAIN_CORAL));
											Item root2 = p.getWorld().dropItem(le.getLocation(), new ItemStack(Material.BUBBLE_CORAL));
											Item root3 = p.getWorld().dropItem(le.getLocation(), new ItemStack(Material.FIRE_CORAL));
											Item root4 = p.getWorld().dropItem(le.getLocation(), new ItemStack(Material.HORN_CORAL));
											Item root5 = p.getWorld().dropItem(le.getLocation(), new ItemStack(Material.TUBE_CORAL));
											root.setOwner(p.getUniqueId());
											root2.setOwner(p.getUniqueId());
											root3.setOwner(p.getUniqueId());
											root4.setOwner(p.getUniqueId());
											root5.setOwner(p.getUniqueId());
											root.setPickupDelay(999999);
											root2.setPickupDelay(999999);
											root3.setPickupDelay(999999);
											root4.setPickupDelay(999999);
											root5.setPickupDelay(999999);
							            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
													root.remove();
													root2.remove();
													root3.remove();
													root4.remove();
													root5.remove();
								                }
								            }, 20); 
										}
									}
				                }
				            }, 13*i); 
		            	}
			            prcooldown.put(p.getName(), System.currentTimeMillis());
		                
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
		        	AtomicInteger j = new AtomicInteger();
	            	for(int i = 0 ; i <4 ; i++) {

		            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 2f);
								p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 1f);
								p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 0f);
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 0.5f, 2f);
								p.getWorld().spawnParticle(Particle.WATER_BUBBLE, lel, 100, 3,3,3, 0); 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.incrementAndGet(), 3,3,3, 0, Material.BUBBLE_CORAL_BLOCK.createBlockData()); 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,3,3, 0, Material.BRAIN_CORAL_BLOCK.createBlockData()); 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,3,3, 0, Material.FIRE_CORAL_BLOCK.createBlockData()); 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,3,3, 0, Material.HORN_CORAL_BLOCK.createBlockData()); 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,3,3, 0, Material.TUBE_CORAL_BLOCK.createBlockData()); 
								for (Entity a : p.getWorld().getNearbyEntities(lel,j.get(),j.get(),j.get()))
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
									if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
									{
										LivingEntity le = (LivingEntity)a;
										Holding.holding(p, le, 20l);
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
											enar.setDamage(player_damage.get(p.getName())*0.443);								
										}
										p.setSprinting(true);
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.443, p);
										p.setSprinting(false);
										Item root = p.getWorld().dropItem(le.getLocation(), new ItemStack(Material.BRAIN_CORAL));
										Item root2 = p.getWorld().dropItem(le.getLocation(), new ItemStack(Material.BUBBLE_CORAL));
										Item root3 = p.getWorld().dropItem(le.getLocation(), new ItemStack(Material.FIRE_CORAL));
										Item root4 = p.getWorld().dropItem(le.getLocation(), new ItemStack(Material.HORN_CORAL));
										Item root5 = p.getWorld().dropItem(le.getLocation(), new ItemStack(Material.TUBE_CORAL));
										root.setOwner(p.getUniqueId());
										root2.setOwner(p.getUniqueId());
										root3.setOwner(p.getUniqueId());
										root4.setOwner(p.getUniqueId());
										root5.setOwner(p.getUniqueId());
										root.setPickupDelay(999999);
										root2.setPickupDelay(999999);
										root3.setPickupDelay(999999);
										root4.setPickupDelay(999999);
										root5.setPickupDelay(999999);
						            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
												root.remove();
												root2.remove();
												root3.remove();
												root4.remove();
												root5.remove();
							                }
							            }, 20); 
									}
								}
			                }
			            }, 13*i); 
	            	}
		            prcooldown.put(p.getName(), System.currentTimeMillis());
		        }
				}	
			}
		}
	}
	@EventHandler
	public void CoralLiquor(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 10;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 22) {
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && p.isSneaking())
			{
				
				ev.setCancelled(true);
				{
					if(jmcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (jmcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use CoralLiquor").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {							
		                	jmcooldown.remove(p.getName()); // removing player from HashMap
		                	Location l = p.getLocation();
		                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0f, 0f);
			        		p.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, l, 400, 4, 1, 4);
			        		p.getWorld().spawnParticle(Particle.WATER_WAKE, l, 400, 4, 1, 4);
							Holding.invur(p, 60l);
							drunk.computeIfPresent(p.getName(), (k,v) -> v+1);
							drunk.putIfAbsent(p.getName(), 0);
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60,0,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 160,0,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200,2,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200,2,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200,2,false,false));
							if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
							}
							if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
							}
							if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 3, false, false));
							}
	                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									drunk.computeIfPresent(p.getName(), (k,v) -> v-1);
									if(drunk.getOrDefault(p.getName() , -1)<0) {
										drunk.remove(p.getName());
									}
				                }
				            }, 300);  
							for (Entity e : p.getWorld().getNearbyEntities(l ,4, 4, 4))
							{
								if (e instanceof Player) 
								{
									new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
									Player p1 = (Player) e;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
											Holding.invur(p1, 60l);
											drunk.computeIfPresent(p1.getName(), (k,v) -> v+1);
											drunk.putIfAbsent(p1.getName(), 0);
											p1.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200,2,false,false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 200, 3, false, false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 200, 3, false, false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, 3, false, false));
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													drunk.computeIfPresent(p1.getName(), (k,v) -> v-1);
													if(drunk.getOrDefault(p1.getName() , -1)<0) {
														drunk.remove(p1.getName());
													}
								                }
								            }, 300);  
											continue;
										}
									}
								}
							}
							jmcooldown.put(p.getName(), System.currentTimeMillis());
						}
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	Location l = p.getLocation();
	                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0f, 0f);
		        		p.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, l, 400, 4, 1, 4);
		        		p.getWorld().spawnParticle(Particle.WATER_WAKE, l, 400, 4, 1, 4);
						Holding.invur(p, 60l);
						drunk.computeIfPresent(p.getName(), (k,v) -> v+1);
						drunk.putIfAbsent(p.getName(), 0);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60,0,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 160,0,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200,2,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200,2,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200,2,false,false));
						if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
						}
						if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
						}
						if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 3, false, false));
						}
                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								drunk.computeIfPresent(p.getName(), (k,v) -> v-1);
								if(drunk.getOrDefault(p.getName() , -1)<0) {
									drunk.remove(p.getName());
								}
			                }
			            }, 300);  
						for (Entity e : p.getWorld().getNearbyEntities(l ,4, 4, 4))
						{
							if (e instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
								Player p1 = (Player) e;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										Holding.invur(p1, 60l);
										drunk.computeIfPresent(p1.getName(), (k,v) -> v+1);
										drunk.putIfAbsent(p1.getName(), 0);
										p1.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200,2,false,false));
										p1.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 200, 3, false, false));
										p1.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 200, 3, false, false));
										p1.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, 3, false, false));
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												drunk.computeIfPresent(p1.getName(), (k,v) -> v-1);
												if(drunk.getOrDefault(p1.getName() , -1)<0) {
													drunk.remove(p1.getName());
												}
							                }
							            }, 300);  
										continue;
									}
								}
							}
						}
						jmcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					}
				}}
		}
	}


	@EventHandler
	public void Drink(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player&&!d.isCancelled()) 
		{
			Player p = (Player)d.getEntity();
			if(drunk.containsKey(p.getName())) {
				d.setDamage(d.getDamage()/2);
			}
		}
	}
	
	@EventHandler
	public void DrunkenDance(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 7;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 22) {
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && !p.isSneaking())
			{
				final Location pl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
				
				ev.setCancelled(true);
				{
					if(thcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (thcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use DrunkenDance").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {							
		                	thcooldown.remove(p.getName()); // removing player from HashMap
		                	for(int i =0; i<10; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	p.setNoDamageTicks(0);
						                	p.teleport(p.getLocation());
						                    p.swingMainHand();
											p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 2f);
											p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 0.5f, 2f);
											p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl, 10, 3,3,3, 0); 
											p.getWorld().spawnParticle(Particle.WATER_BUBBLE, pl, 222, 3,3,3, 0); 
											for (Entity a : p.getWorld().getNearbyEntities(pl, 5, 5, 5))
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
												if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
												{
													LivingEntity le = (LivingEntity)a;
													Holding.holding(p, le, 20l);
													le.teleport(pl);
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
														enar.setDamage(player_damage.get(p.getName())*0.443);								
													}
													p.setSprinting(true);
													le.damage(0, p);
													le.damage(player_damage.get(p.getName())*0.443, p);
													p.setSprinting(false);
												}
											}
						                }
						            }, i*2); 
			                }
		                	thcooldown.put(p.getName(), System.currentTimeMillis());
						}
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	for(int i =0; i<10; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	p.setNoDamageTicks(0);
					                	p.teleport(p.getLocation());
					                    p.swingMainHand();
										p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 2f);
										p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 0.5f, 2f);
										p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl, 10, 3,3,3, 0); 
										p.getWorld().spawnParticle(Particle.WATER_BUBBLE, pl, 222, 3,3,3, 0); 
										for (Entity a : p.getWorld().getNearbyEntities(pl, 5, 5, 5))
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
											if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")) 
											{
												LivingEntity le = (LivingEntity)a;
												Holding.holding(p, le, 20l);
												le.teleport(pl);
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
													enar.setDamage(player_damage.get(p.getName())*0.443);								
												}
												p.setSprinting(true);
												le.damage(0, p);
												le.damage(player_damage.get(p.getName())*0.443, p);
												p.setSprinting(false);
											}
										}
					                }
					            }, i*2); 
		                }
		            	thcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					}
				}}
		}
	}
		
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		final Location pl = p.getLocation();
		final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 22 && (is.getType()==Material.FISHING_ROD)&& p.isSneaking())
			{
				ev.setCancelled(true);
				if(aultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (aultcooldown.get(p.getName())/1000 + 30) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Boat Of The Sea God").create());
		            }
	                else // if timer is done
	                {
	                    aultcooldown.remove(p.getName()); // removing player from HashMap
	                    Boat bo = p.getWorld().spawn(tl, Boat.class);
						Holding.invur(p, 100l);
     					p.playSound(tl, Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1, 2f);
     					if(p.hasPotionEffect(PotionEffectType.BAD_OMEN))
		        		{
		        			p.removePotionEffect(PotionEffectType.BAD_OMEN);
		        		}
		        		if(p.hasPotionEffect(PotionEffectType.BLINDNESS))
		        		{
		        			p.removePotionEffect(PotionEffectType.BLINDNESS);
		        		}
		        		if(p.hasPotionEffect(PotionEffectType.CONFUSION))
		        		{
		        			p.removePotionEffect(PotionEffectType.CONFUSION);
		        		}
		        		if(p.hasPotionEffect(PotionEffectType.HUNGER))
		        		{
		        			p.removePotionEffect(PotionEffectType.HUNGER);
		        		}
		        		if(p.hasPotionEffect(PotionEffectType.POISON))
		        		{
		        			p.removePotionEffect(PotionEffectType.POISON);
		        		}
		        		if(p.hasPotionEffect(PotionEffectType.SLOW))
		        		{
		        			p.removePotionEffect(PotionEffectType.SLOW);
		        		}
		        		if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
		        		{
		        			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
		        		}
		        		if(p.hasPotionEffect(PotionEffectType.UNLUCK))
		        		{
		        			p.removePotionEffect(PotionEffectType.UNLUCK);
		        		}
		        		if(p.hasPotionEffect(PotionEffectType.WEAKNESS))
		        		{
		        			p.removePotionEffect(PotionEffectType.WEAKNESS);
		        		}
		        		if(p.hasPotionEffect(PotionEffectType.WITHER))
		        		{
		        			p.removePotionEffect(PotionEffectType.WITHER);
		        		}
		        		p.setFireTicks(0);
						bo.addPassenger(p);
	                    bo.setWoodType(TreeSpecies.DARK_OAK);
	                    bo.getNearbyEntities(6, 6, 6).forEach(e -> {
	                    	if (e instanceof Player) 
	        				{
	        					Player p1 = (Player) e;
	        					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
	        					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
	        						{
	        						if(p1.hasPotionEffect(PotionEffectType.BAD_OMEN))
					        		{
					        			p1.removePotionEffect(PotionEffectType.BAD_OMEN);
					        		}
					        		if(p1.hasPotionEffect(PotionEffectType.BLINDNESS))
					        		{
					        			p1.removePotionEffect(PotionEffectType.BLINDNESS);
					        		}
					        		if(p1.hasPotionEffect(PotionEffectType.CONFUSION))
					        		{
					        			p1.removePotionEffect(PotionEffectType.CONFUSION);
					        		}
					        		if(p1.hasPotionEffect(PotionEffectType.HUNGER))
					        		{
					        			p1.removePotionEffect(PotionEffectType.HUNGER);
					        		}
					        		if(p1.hasPotionEffect(PotionEffectType.POISON))
					        		{
					        			p1.removePotionEffect(PotionEffectType.POISON);
					        		}
					        		if(p1.hasPotionEffect(PotionEffectType.SLOW))
					        		{
					        			p1.removePotionEffect(PotionEffectType.SLOW);
					        		}
					        		if(p1.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
					        		{
					        			p1.removePotionEffect(PotionEffectType.SLOW_DIGGING);
					        		}
					        		if(p1.hasPotionEffect(PotionEffectType.UNLUCK))
					        		{
					        			p1.removePotionEffect(PotionEffectType.UNLUCK);
					        		}
					        		if(p1.hasPotionEffect(PotionEffectType.WEAKNESS))
					        		{
					        			p1.removePotionEffect(PotionEffectType.WEAKNESS);
					        		}
					        		if(p1.hasPotionEffect(PotionEffectType.WITHER))
					        		{
					        			p1.removePotionEffect(PotionEffectType.WITHER);
					        		}
					        		p.setFireTicks(0);
	        							Holding.invur(p1, 100l);
		        						bo.addPassenger(p1);
	        						}
	        					}
	        				}
	                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
	        				{
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
	        							enar.setDamage(player_damage.get(p.getName())*0.56*(1));
	        						}		  
	        						p.setSprinting(true);
	        						le.damage(0, p);
	        						le.damage(player_damage.get(p.getName())*5, p);	
	        						p.setSprinting(false);
	    		                	for(int i =0; i<20; i++) {
	 			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	 						                @Override
	 						                public void run() 
	 						                {
	 			        						bo.addPassenger(le);
	 						                }
	 						            }, i*5); 
	    		                	}
	        						Holding.holding(p, le, 100l);
	        								
	        				}
                		});

	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
        							Holding.invur(p, 30l);
        							p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100,100,false,false));
        							p.getWorld().spawnParticle(Particle.WATER_WAKE, tl, 1000,3,3,3);
	             					p.getWorld().spawnParticle(Particle.REDSTONE, tl, 1000, 3,2,3,0, new DustOptions(Color.AQUA, 2));
	             					p.playSound(tl, Sound.BLOCK_CONDUIT_ACTIVATE, 1, 2f);
	             					p.playSound(tl, Sound.BLOCK_CONDUIT_ATTACK_TARGET, 1, 2f);
				                	bo.getPassengers().forEach(e ->{
				                		if (e instanceof Player) 
				        				{
				        					Player p1 = (Player) e;
				        					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
				        					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
				        						{
				        							Holding.invur(p1, 30l);
				        							p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100,100,false,false));
				        						}
				        					}
				        				}
				                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
				        				{
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
				        							enar.setDamage(player_damage.get(p.getName())*0.56*(1));
				        						}		  
				        						p.setSprinting(true);
				        						le.damage(0, p);
				        						le.damage(player_damage.get(p.getName())*5, p);	
				        						p.setSprinting(false);
				        						bo.addPassenger(le);
				        						Holding.holding(p, le, 30l);
				        								
				        				}
				                	});
				                	bo.remove();
				                }
			            }, 100); 
		                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	Boat bo = p.getWorld().spawn(tl, Boat.class);
					Holding.invur(p, 100l);
 					p.playSound(tl, Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1, 2f);
 					if(p.hasPotionEffect(PotionEffectType.BAD_OMEN))
	        		{
	        			p.removePotionEffect(PotionEffectType.BAD_OMEN);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.BLINDNESS))
	        		{
	        			p.removePotionEffect(PotionEffectType.BLINDNESS);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.CONFUSION))
	        		{
	        			p.removePotionEffect(PotionEffectType.CONFUSION);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.HUNGER))
	        		{
	        			p.removePotionEffect(PotionEffectType.HUNGER);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.POISON))
	        		{
	        			p.removePotionEffect(PotionEffectType.POISON);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.SLOW))
	        		{
	        			p.removePotionEffect(PotionEffectType.SLOW);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
	        		{
	        			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.UNLUCK))
	        		{
	        			p.removePotionEffect(PotionEffectType.UNLUCK);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.WEAKNESS))
	        		{
	        			p.removePotionEffect(PotionEffectType.WEAKNESS);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.WITHER))
	        		{
	        			p.removePotionEffect(PotionEffectType.WITHER);
	        		}
	        		p.setFireTicks(0);
					bo.addPassenger(p);
                    bo.setWoodType(TreeSpecies.DARK_OAK);
                    bo.getNearbyEntities(6, 6, 6).forEach(e -> {
                    	if (e instanceof Player) 
        				{
        					Player p1 = (Player) e;
        					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
        					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
        						{
        						if(p1.hasPotionEffect(PotionEffectType.BAD_OMEN))
				        		{
				        			p1.removePotionEffect(PotionEffectType.BAD_OMEN);
				        		}
				        		if(p1.hasPotionEffect(PotionEffectType.BLINDNESS))
				        		{
				        			p1.removePotionEffect(PotionEffectType.BLINDNESS);
				        		}
				        		if(p1.hasPotionEffect(PotionEffectType.CONFUSION))
				        		{
				        			p1.removePotionEffect(PotionEffectType.CONFUSION);
				        		}
				        		if(p1.hasPotionEffect(PotionEffectType.HUNGER))
				        		{
				        			p1.removePotionEffect(PotionEffectType.HUNGER);
				        		}
				        		if(p1.hasPotionEffect(PotionEffectType.POISON))
				        		{
				        			p1.removePotionEffect(PotionEffectType.POISON);
				        		}
				        		if(p1.hasPotionEffect(PotionEffectType.SLOW))
				        		{
				        			p1.removePotionEffect(PotionEffectType.SLOW);
				        		}
				        		if(p1.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
				        		{
				        			p1.removePotionEffect(PotionEffectType.SLOW_DIGGING);
				        		}
				        		if(p1.hasPotionEffect(PotionEffectType.UNLUCK))
				        		{
				        			p1.removePotionEffect(PotionEffectType.UNLUCK);
				        		}
				        		if(p1.hasPotionEffect(PotionEffectType.WEAKNESS))
				        		{
				        			p1.removePotionEffect(PotionEffectType.WEAKNESS);
				        		}
				        		if(p1.hasPotionEffect(PotionEffectType.WITHER))
				        		{
				        			p1.removePotionEffect(PotionEffectType.WITHER);
				        		}
				        		p.setFireTicks(0);
        							Holding.invur(p1, 100l);
	        						bo.addPassenger(p1);
        						}
        					}
        				}
                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
        				{
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
        							enar.setDamage(player_damage.get(p.getName())*0.56*(1));
        						}		  
        						p.setSprinting(true);
        						le.damage(0, p);
        						le.damage(player_damage.get(p.getName())*5, p);	
        						p.setSprinting(false);
    		                	for(int i =0; i<20; i++) {
 			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
 						                @Override
 						                public void run() 
 						                {
 			        						bo.addPassenger(le);
 						                }
 						            }, i*5); 
    		                	}
        						Holding.holding(p, le, 100l);
        								
        				}
            		});

                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
    							Holding.invur(p, 30l);
    							p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100,100,false,false));
    							p.getWorld().spawnParticle(Particle.WATER_WAKE, tl, 1000,3,3,3);
             					p.getWorld().spawnParticle(Particle.REDSTONE, tl, 1000, 3,2,3,0, new DustOptions(Color.AQUA, 2));
             					p.playSound(tl, Sound.BLOCK_CONDUIT_ACTIVATE, 1, 2f);
             					p.playSound(tl, Sound.BLOCK_CONDUIT_ATTACK_TARGET, 1, 2f);
			                	bo.getPassengers().forEach(e ->{
			                		if (e instanceof Player) 
			        				{
			        					Player p1 = (Player) e;
			        					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
			        					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
			        						{
			        							Holding.invur(p1, 30l);
			        							p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100,100,false,false));
			        						}
			        					}
			        				}
			                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
			        				{
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
			        							enar.setDamage(player_damage.get(p.getName())*0.56*(1));
			        						}		  
			        						p.setSprinting(true);
			        						le.damage(0, p);
			        						le.damage(player_damage.get(p.getName())*5, p);	
			        						p.setSprinting(false);
			        						bo.addPassenger(le);
			        						Holding.holding(p, le, 30l);
			        								
			        				}
			                	});
			                	bo.remove();
			                }
		            }, 100); 
	                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}	
			
			
    }


	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

	    
		
		
		if(playerclass.get(p.getUniqueId()) == 22)
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD)
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
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 22) {
				if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD )
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
	public void Damagegetter(EntityToggleSwimEvent e) 
	{
		
		if(e.getEntity() instanceof Player)
		{
			Player p = (Player)e.getEntity();
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 22 || playerclass.get(p.getUniqueId()) == 21) {
				if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
				}
				if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
				}
				if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
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
        

		
		
		
		if(playerclass.get(p.getUniqueId()) == 22) {
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD)
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
			    
				
				
				
				if(playerclass.get(p.getUniqueId()) == 22) {

					if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD)
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
	public void Technic(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.getEntity().hasMetadata("fake")&& d.getDamage()>0) 
		{
	        
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			
	
			
			
			if(playerclass.get(p.getUniqueId()) == 22) {

				if(le == p) {
					d.setCancelled(true);
					return;
				}
				if (le instanceof Player) 
				{
					Player p1 = (Player) le;
					try {
					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
						{
							return;
						}
					}}
					catch(NullPointerException ne) {
						return;
					}
				}
					d.setDamage(d.getDamage()*20);
			}
			
			if(drunk.containsKey(p.getName())) {
				d.setDamage(d.getDamage()*1.5);
			}
		}
		
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity && !d.getEntity().hasMetadata("fake") && d.getDamage()>0) 
		{
		Projectile ar = (Projectile)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
        
		

		
		
		if(ar.getShooter() instanceof Player) {
			Player p = (Player) ar.getShooter();
			if (le instanceof Player) 
			{
				Player p1 = (Player) le;
				try {
				if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
				if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
					{
						return;
					}
				}}
				catch(NullPointerException ne) {
					return;
				}
			}
			if(playerclass.get(p.getUniqueId()) == 22) {
				if(le == p) {
					d.setCancelled(true);
					return;
				}
				d.setDamage(d.getDamage()*20);
			}
			if(drunk.containsKey(p.getName())) {
				d.setDamage(d.getDamage()*1.5);
			}
		}
		}
	}
}



