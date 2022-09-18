package io.github.chw3021.classes.angler;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.obtains.Obtained;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.rmain.RMain;
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
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Boat.Type;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Angskills extends Pak implements Serializable, Listener{
	

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 1343715876136938259L;
	private HashMap<String, Double> rscooldown = new HashMap<String, Double>();
	private HashMap<String, Double> prcooldown = new HashMap<String, Double>();
	private HashMap<String, Double> jmcooldown = new HashMap<String, Double>();
	private HashMap<String, Double> thcooldown = new HashMap<String, Double>();
	private HashMap<String, Double> pncooldown = new HashMap<String, Double>();
	private HashMap<String, Double> aultcooldown = new HashMap<String, Double>();
	private HashMap<String, Double> ault2cooldown = new HashMap<String, Double>();
	private HashMap<String, Integer> drunk = new HashMap<String, Integer>();
	private HashMap<String, Integer> drunkt = new HashMap<String, Integer>();
	
	private HashMap<UUID, Integer> spout = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> spoutt = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Integer> ddash = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> ddasht = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> dsmash = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> dsmasht = new HashMap<UUID, Integer>();
	
	private Multimap<UUID, LivingEntity> baited = ArrayListMultimap.create();

	
	private String path = new File("").getAbsolutePath();
	private AngSkillsData fsd;
	
	
	
	private static final Angskills instance = new Angskills ();
	public static Angskills getInstance()
	{
		return instance;
	}
	
		
	public void er(PluginEnableEvent ev) 
	{
		AngSkillsData f = new AngSkillsData(AngSkillsData.loadData(path +"/plugins/RPGskills/AngSkillsData.data"));
		fsd = f;
		
	}
	
	
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
				Player p = (Player) e.getWhoClicked();
				if(drunkt.containsKey(p.getName())) {
					Bukkit.getScheduler().cancelTask(drunkt.get(p.getName()));
					drunkt.remove(p.getName());
				}
				drunk.remove(p.getName());
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
				Player p = (Player) e.getWhoClicked();
				p.setCooldown(Material.STRUCTURE_VOID, 1);
				AngSkillsData f = new AngSkillsData(AngSkillsData.loadData(path +"/plugins/RPGskills/AngSkillsData.data"));
				fsd = f;
			}
			
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		AngSkillsData f = new AngSkillsData(AngSkillsData.loadData(path +"/plugins/RPGskills/AngSkillsData.data"));
		fsd = f;
	}




	
	public void Bait(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 22) {
				double sec = 6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				if(e.getEntity() instanceof FishHook && fsd.Bait.getOrDefault(p.getUniqueId(),0)>=1)
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
				            double timer = (pncooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
				        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("미끼 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
							    }
				        		else {
					            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Bait").create());
				        		}
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
												p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
												if(Proficiency.getpro(p)>=1) {
													p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
												}
												p.getWorld().playSound(fh.getLocation(), Sound.ENTITY_FISHING_BOBBER_SPLASH, 0.5f, 2f);
												p.getWorld().playSound(fh.getLocation(), Sound.ENTITY_SLIME_JUMP, 0.5f, 2f);
												p.getWorld().spawnParticle(Particle.WATER_BUBBLE, fh.getLocation(), 222, 2,2,2, 0); 
												p.getWorld().spawnParticle(Particle.WATER_SPLASH, fh.getLocation(), 100, 2,2,2, 0); 
												for (Entity e : p.getNearbyEntities(6, 3, 6))
												{
													if (e instanceof Player) 
													{
														Player p1 = (Player) e;
														if(Party.hasParty(p) && Party.hasParty(p1))	{
														if(Party.getParty(p).equals(Party.getParty(p1)))
															{
															p.getWorld().spawnParticle(Particle.WATER_SPLASH, p1.getLocation(), 60,2,2,2);
																p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
																if(Proficiency.getpro(p)>=1) {
																	p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
																}
																continue;
															}
														}
													}
												}
												for (Entity a : fh.getWorld().getNearbyEntities(fh.getLocation(), 2.5, 2.5, 2.5))
												{
						                    		if (a instanceof Player) 
													{
														
														Player p1 = (Player) a;
														if(Party.hasParty(p) && Party.hasParty(p1))	{
														if(Party.getParty(p).equals(Party.getParty(p1)))
															{
																continue;
															}
														}
													}
													if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")&& !(a.hasMetadata("portal"))) 
													{
														LivingEntity le = (LivingEntity)a;
														le.teleport(fh);
														Holding.holding(p, le, 20l);
														le.teleport(fh);
														atks(0.3, fsd.Bait.get(p.getUniqueId())*0.35, p, le,7);
														if(Proficiency.getpro(p)>=1) {
															baited.put(p.getUniqueId(), le);

										                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
													                @Override
													                public void run() 
													                {
													        			if(baited.containsKey(p.getUniqueId())) {
																			baited.removeAll(p.getUniqueId());
													        			}
													                }
													            }, 33); 
														}
														
													}
												}
							                }
							            }, i*2); 
				                }
				                pncooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
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
											p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
											if(Proficiency.getpro(p)>=1) {
												p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
											}
											p.getWorld().playSound(fh.getLocation(), Sound.ENTITY_FISHING_BOBBER_SPLASH, 0.5f, 2f);
											p.getWorld().playSound(fh.getLocation(), Sound.ENTITY_SLIME_JUMP, 0.5f, 2f);
											p.getWorld().spawnParticle(Particle.WATER_BUBBLE, fh.getLocation(), 222, 2,2,2, 0); 
											p.getWorld().spawnParticle(Particle.WATER_SPLASH, fh.getLocation(), 100, 2,2,2, 0); 
											for (Entity e : p.getNearbyEntities(6, 3, 6))
											{
												if (e instanceof Player) 
												{
													Player p1 = (Player) e;
													if(Party.hasParty(p) && Party.hasParty(p1))	{
													if(Party.getParty(p).equals(Party.getParty(p1)))
														{
														p.getWorld().spawnParticle(Particle.WATER_SPLASH, p1.getLocation(), 60,2,2,2);
															p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
															if(Proficiency.getpro(p)>=1) {
																p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
															}
															continue;
														}
													}
												}
											}
											for (Entity a : fh.getWorld().getNearbyEntities(fh.getLocation(), 2.5, 2.5, 2.5))
											{
					                    		if (a instanceof Player) 
												{
													
													Player p1 = (Player) a;
													if(Party.hasParty(p) && Party.hasParty(p1))	{
													if(Party.getParty(p).equals(Party.getParty(p1)))
														{
															continue;
														}
													}
												}
												if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")&& !(a.hasMetadata("portal"))) 
												{
													LivingEntity le = (LivingEntity)a;
													le.teleport(fh);
													Holding.holding(p, le, 20l);
													le.teleport(fh);
													atks(0.3, fsd.Bait.get(p.getUniqueId())*0.35, p, le,7);
													if(Proficiency.getpro(p)>=1) {
														baited.put(p.getUniqueId(), le);

									                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
												                @Override
												                public void run() 
												                {
												        			if(baited.containsKey(p.getUniqueId())) {
																		baited.removeAll(p.getUniqueId());
												        			}
												                }
												            }, 33); 
													}
													
												}
											}
						                }
						            }, i*2); 
			                }
			                pncooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
				        }
					}
				}
			}
			
		}
	}


	public void Fishing(PlayerFishEvent d) 
	{
		Player p = d.getPlayer();
		double sec = (int) (4/(Proficiency.getpro(p)+1))*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 22 && (fsd.Fishing.getOrDefault(p.getUniqueId(),0) >= 1)) {
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
			final Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(1.9));
			if(baited.containsKey(p.getUniqueId())) {
				baited.get(p.getUniqueId()).forEach(le -> {
					atks(0.8, fsd.Bait.get(p.getUniqueId())*1.1, p, le,5);
					/*
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
						enar.setDamage(player_damage.get(p.getName())*0.46);
					}		  
					p.setCooldown(Material.YELLOW_TERRACOTTA,1);
					le.damage(0, p);
					le.damage(player_damage.get(p.getName())*0.46+(1), p);	
					*/
					le.teleport(tl);
					Holding.superholding(p, le, 10l);
				});
				baited.removeAll(p.getUniqueId());
			}
			if(d.getCaught() instanceof LivingEntity) {
					if(rscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            double timer = (rscooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("낚시 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						    }
			        		else {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Fishing").create());
			        		}
				        }
			            else // if timer is done
			            {
			            	rscooldown.remove(p.getName()); // removing player from HashMap
			                for(Entity e : d.getHook().getNearbyEntities(4.3, 4.32, 4.3)) {
			                	if (e instanceof Player) 
			    				{
			    					Player p1 = (Player) e;
			    					if(Party.hasParty(p) && Party.hasParty(p1))	{
			    					if(Party.getParty(p).equals(Party.getParty(p1)))
			    						{
			    						continue;
			    						}
			    					}
			    				}
			            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
			    				{
			    						LivingEntity le = (LivingEntity)e;
			    						atk1(0.46, p, le,5);
			    						le.teleport(tl);
			    						Holding.superholding(p, le, 10l);
			    				}
			                }
			            	rscooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
		                for(Entity e : d.getHook().getNearbyEntities(4.3, 4.32, 4.3)) {
		                	if (e instanceof Player) 
		    				{
		    					Player p1 = (Player) e;
		    					if(Party.hasParty(p) && Party.hasParty(p1))	{
		    					if(Party.getParty(p).equals(Party.getParty(p1)))
		    						{
		    						continue;
		    						}
		    					}
		    				}
		            		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
		    				{
		    						LivingEntity le = (LivingEntity)e;
		    						atk1(0.46, p, le,5);
		    						le.teleport(tl);
		    						Holding.superholding(p, le, 10l);
		    				}
		                }
			        	rscooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
			        }
				
			}
		}
	}
	
	
	public void Whipping(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
	    
		
		if(ClassData.pc.get(p.getUniqueId()) == 22 && p.getCooldown(Material.STRING)<=0 && fsd.Whipping.getOrDefault(p.getUniqueId(),0)>=1) {
			if((ac == Action.LEFT_CLICK_AIR || ac==Action.LEFT_CLICK_BLOCK) &&p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && !p.isSneaking()) {
	
					p.setCooldown(Material.STRING, 10);
					p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 100,2,2,2);
					final Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(2.7));
					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 10,2,1,2);
					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SLIME_BLOCK_HIT, 0.5f, 2f);
					if(Proficiency.getpro(p)>=1) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
					}
					for(Entity e: p.getWorld().getNearbyEntities(tl, 2.5,2.5,2.5)) {
						
	        		if (e instanceof Player) 
					{
						Player p1 = (Player) e;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
							{
							if(Proficiency.getpro(p)>=1) {
								p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
							}
							continue;
							}
						}
					}
	        		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake")) && !(e.hasMetadata("portal"))) 
					{
							LivingEntity le = (LivingEntity)e;
							atks(0.65,fsd.Whipping.get(p.getUniqueId())*0.35, p, le,5);
							Holding.holding(p, le, 3l);
							le.teleport(tl);
									
					}
				}
			}
			
		}
	}

	
	public void CoralRoots(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{	
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			
			if(ClassData.pc.get(p.getUniqueId()) == 22 && fsd.CoralRoots.getOrDefault(p.getUniqueId(),0)>=1 && !(p.hasCooldown(Material.YELLOW_TERRACOTTA))) {
			if(p.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD && (p.isSneaking()))
			{
				final Location lel = le.getLocation();
				double sec = 9*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				
				if(prcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            double timer = (prcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("산호뿌리 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use CoralRoots").create());
		        		}
	                }
		            else // if timer is done
		            {
		            	prcooldown.remove(p.getName()); // removing player from HashMap
                		if (le instanceof Player) 
						{
							
							Player p1 = (Player) le;
							if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
								{
									d.setCancelled(true);
									return;
								}
							}
						}
                		if(le.hasMetadata("fake") || le.hasMetadata("portal")) {
							d.setCancelled(true);
                			return;
                		}
		            	AtomicInteger j = new AtomicInteger();
		            	for(int i = 0 ; i <4 ; i++) {

			            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {

									if(Proficiency.getpro(p)>=1) {
										j.set(4);
									}
									p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 2f);
									p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 1f);
									p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 0f);
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 0.5f, 2f);
									p.getWorld().spawnParticle(Particle.WATER_SPLASH, lel, 100, 3,1,3, 0); 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.incrementAndGet(), 3,1,3, 0, Material.BUBBLE_CORAL_BLOCK.createBlockData()); 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,1,3, 0, Material.BRAIN_CORAL_BLOCK.createBlockData()); 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,1,3, 0, Material.FIRE_CORAL_BLOCK.createBlockData()); 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,1,3, 0, Material.HORN_CORAL_BLOCK.createBlockData()); 
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,1,3, 0, Material.TUBE_CORAL_BLOCK.createBlockData()); 
									for (Entity a : p.getWorld().getNearbyEntities(lel,j.get(),j.get(),j.get()))
									{
			                    		if (a instanceof Player) 
										{
											
											Player p1 = (Player) a;
											if(Party.hasParty(p) && Party.hasParty(p1))	{
											if(Party.getParty(p).equals(Party.getParty(p1)))
												{
													continue;
												}
											}
										}
										if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")&& !(a.hasMetadata("portal"))) 
										{
											LivingEntity le = (LivingEntity)a;
											atks(0.4443,fsd.CoralRoots.get(p.getUniqueId())*0.43, p, le,7);
											/*
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
												enar.setDamage(player_damage.get(p.getName())*0.4443+fsd.CoralRoots.get(p.getUniqueId())*0.43);								
											}
											p.setCooldown(Material.YELLOW_TERRACOTTA,1);
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*0.4443+fsd.CoralRoots.get(p.getUniqueId())*0.43, p);
											*/
											Holding.holding(p, le, 20l);
											
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
			            prcooldown.put(p.getName(), System.currentTimeMillis()*1.0);
		                
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
            		if (le instanceof Player) 
					{
						
						Player p1 = (Player) le;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								d.setCancelled(true);
								return;
							}
						}
					}
            		if(le.hasMetadata("fake") || le.hasMetadata("portal")) {
						d.setCancelled(true);
            			return;
            		}
		        	AtomicInteger j = new AtomicInteger();
	            	for(int i = 0 ; i <4 ; i++) {

		            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								if(Proficiency.getpro(p)>=1) {
									j.set(4);
								}
								p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 2f);
								p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 1f);
								p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CORAL_BLOCK_PLACE, 0.5f, 0f);
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 0.5f, 2f);
								p.getWorld().spawnParticle(Particle.WATER_SPLASH, lel, 100, 3,1,3, 0); 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.incrementAndGet(), 3,1,3, 0, Material.BUBBLE_CORAL_BLOCK.createBlockData()); 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,1,3, 0, Material.BRAIN_CORAL_BLOCK.createBlockData()); 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,1,3, 0, Material.FIRE_CORAL_BLOCK.createBlockData()); 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,1,3, 0, Material.HORN_CORAL_BLOCK.createBlockData()); 
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, lel, 50*j.get(), 3,1,3, 0, Material.TUBE_CORAL_BLOCK.createBlockData()); 
								for (Entity a : p.getWorld().getNearbyEntities(lel,j.get(),j.get(),j.get()))
								{
		                    		if (a instanceof Player) 
									{
										
										Player p1 = (Player) a;
										if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
											{
												continue;
											}
										}
									}
									if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")&& !(a.hasMetadata("portal"))) 
									{
										LivingEntity le = (LivingEntity)a;
										atks(0.4443,fsd.CoralRoots.get(p.getUniqueId())*0.43, p, le,7);
										Holding.holding(p, le, 20l);
										
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
		            prcooldown.put(p.getName(), System.currentTimeMillis()*1.0);
		        }
				}	
			}
		}
	}
	
	
	public void Drink(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player&&!d.isCancelled()) 
		{
			Player p = (Player)d.getEntity();
			if(drunk.containsKey(p.getName())) {
				d.setDamage(d.getDamage()*(1-drunk.get(p.getName())*0.01525));
			}
		}
	}


	public void CoralLiquor(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 22 && fsd.CoralLiquor.getOrDefault(p.getUniqueId(),0)>=1) {
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && p.isSneaking())
			{
				
				double sec = 8*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				ev.setCancelled(true);
				{
					if(jmcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (jmcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
			        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("산호주 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						    }
			        		else {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use CoralLiquor").create());
			        		}
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {							
		                	jmcooldown.remove(p.getName()); // removing player from HashMap

		                	if(spoutt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(spoutt.get(p.getUniqueId()));
		                		spoutt.remove(p.getUniqueId());
		                	}
		                	

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(Proficiency.getpro(p)>=2) {
										spout.putIfAbsent(p.getUniqueId(), 0);
									}
				                }
				            }, 3); 
		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									spout.remove(p.getUniqueId());
				                }
				            }, 25); 
		                	spoutt.put(p.getUniqueId(), task);
		            		
		                	Location l = p.getLocation();
		                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0f, 0f);
			        		p.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, l, 400, 4, 1, 4);
			        		p.getWorld().spawnParticle(Particle.WATER_WAKE, l, 400, 4, 1, 4);
							Holding.invur(p, fsd.CoralLiquor.get(p.getUniqueId())*4l);
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60,0,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 160,0,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200,2,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200,2,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200,2,false,false));
							if(Proficiency.getpro(p)>=1) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200,1,false,false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200,2,false,false));
							}
							if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
							}
							if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
							}
							if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 3, false, false));
							}

							if(drunkt.containsKey(p.getName())) {
								Bukkit.getScheduler().cancelTask(drunkt.get(p.getName()));
								drunkt.remove(p.getName());
							}
							if(drunk.getOrDefault(p.getName(), 0) <= fsd.CoralLiquor.get(p.getUniqueId()) * (Proficiency.getpro(p)>=2? 2:1)) {
								drunk.put(p.getName(), fsd.CoralLiquor.get(p.getUniqueId()) * (Proficiency.getpro(p)>=2? 2:1));
							}
	                		int dt = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									drunk.remove(p.getName());
									drunkt.remove(p.getName());
				                }
				            }, 300);  
	                		drunkt.put(p.getName(), dt);
							for (Entity e : p.getWorld().getNearbyEntities(l ,4, 4, 4))
							{
								if (e instanceof Player) 
								{
									
									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
									if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											Holding.invur(p1, fsd.CoralLiquor.get(p.getUniqueId())*4l);
											p1.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200,2,false,false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 200, 3, false, false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 200, 3, false, false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, 3, false, false));
												if(Proficiency.getpro(p)>=1) {
													p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200,1,false,false));
													p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200,2,false,false));
												}
												if(drunkt.containsKey(p1.getName())) {
													Bukkit.getScheduler().cancelTask(drunkt.get(p1.getName()));
												}
												if(drunk.getOrDefault(p1.getName(), 0) <= fsd.CoralLiquor.get(p.getUniqueId()) * (Proficiency.getpro(p)>=2? 2:1)) {
													drunk.put(p1.getName(), fsd.CoralLiquor.get(p.getUniqueId()) * (Proficiency.getpro(p)>=2? 2:1));
												}
						                		int dt1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() {
														drunk.remove(p1.getName());
									                }
									            }, 300);  
						                		drunkt.put(p1.getName(), dt1);
											continue;
										}
									}
								}
							}
							jmcooldown.put(p.getName(), System.currentTimeMillis()*1.0);
						}
		            }
		            else // if cooldown doesn't have players name in it
		            {


	                	if(spoutt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(spoutt.get(p.getUniqueId()));
	                		spoutt.remove(p.getUniqueId());
	                	}
	                	

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=2) {
									spout.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 
	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								spout.remove(p.getUniqueId());
			                }
			            }, 25); 
	                	spoutt.put(p.getUniqueId(), task);
	            		
	                	Location l = p.getLocation();
	                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0f, 0f);
		        		p.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, l, 400, 4, 1, 4);
		        		p.getWorld().spawnParticle(Particle.WATER_WAKE, l, 400, 4, 1, 4);
						Holding.invur(p, fsd.CoralLiquor.get(p.getUniqueId())*4l);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60,0,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 160,0,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200,2,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200,2,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200,2,false,false));
						if(Proficiency.getpro(p)>=1) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200,1,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200,2,false,false));
						}
						if(!p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
						}
						if(!p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
						}
						if(!p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 3, false, false));
						}

						if(drunkt.containsKey(p.getName())) {
							Bukkit.getScheduler().cancelTask(drunkt.get(p.getName()));
							drunkt.remove(p.getName());
						}
						if(drunk.getOrDefault(p.getName(), 0) <= fsd.CoralLiquor.get(p.getUniqueId()) * (Proficiency.getpro(p)>=2? 2:1)) {
							drunk.put(p.getName(), fsd.CoralLiquor.get(p.getUniqueId()) * (Proficiency.getpro(p)>=2? 2:1));
						}
                		int dt = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								drunk.remove(p.getName());
								drunkt.remove(p.getName());
			                }
			            }, 300);  
                		drunkt.put(p.getName(), dt);
						for (Entity e : p.getWorld().getNearbyEntities(l ,4, 4, 4))
						{
							if (e instanceof Player) 
							{
								
								Player p1 = (Player) e;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										Holding.invur(p1, fsd.CoralLiquor.get(p.getUniqueId())*4l);
										p1.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200,2,false,false));
											p1.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 200, 3, false, false));
											p1.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 200, 3, false, false));
											p1.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, 3, false, false));
											if(Proficiency.getpro(p)>=1) {
												p1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200,1,false,false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200,2,false,false));
											}
											if(drunkt.containsKey(p1.getName())) {
												Bukkit.getScheduler().cancelTask(drunkt.get(p1.getName()));
											}
											if(drunk.getOrDefault(p1.getName(), 0) <= fsd.CoralLiquor.get(p.getUniqueId()) * (Proficiency.getpro(p)>=2? 2:1)) {
												drunk.put(p1.getName(), fsd.CoralLiquor.get(p.getUniqueId()) * (Proficiency.getpro(p)>=2? 2:1));
											}
					                		int dt1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													drunk.remove(p1.getName());
								                }
								            }, 300);  
					                		drunkt.put(p1.getName(), dt1);
										continue;
									}
								}
							}
						}
						jmcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
					}
				}}
		}
	}


	
	public void Spout(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 22) {
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && p.isSneaking() &&spout.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
            	if(spoutt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(spoutt.get(p.getUniqueId()));
            		spoutt.remove(p.getUniqueId());
            	}
				spout.remove(p.getUniqueId());

            	Location pl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(2.68));
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 0.8f, 1.2f);
				p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 0.8f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.8f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 0);
				p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, pl, 600,2,2,2);
				p.getWorld().spawnParticle(Particle.WATER_BUBBLE, pl, 600,2,2,2);
				p.getWorld().spawnParticle(Particle.WATER_SPLASH, pl, 600,2,2,2);
				for(Entity e : pl.getWorld().getNearbyEntities(pl, 4,4,4)) {
					if (e instanceof Player) 
					{
						
						Player p1 = (Player) e;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								continue;
							}
						}
					}
					if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) 
					{
						LivingEntity le = (LivingEntity) e;
						atks(2.1,fsd.CoralLiquor.get(p.getUniqueId())*2.5, p, le,5);
						le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 10, false, false));
						Holding.holding(p, le, 10l);
						/*
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
							enar.setDamage(player_damage.get(p.getName())*1.2+fsd.CoralLiquor.get(p.getUniqueId())*3);								
						}
						p.setCooldown(Material.YELLOW_TERRACOTTA,1);
						le.damage(0,p);
						le.damage(player_damage.get(p.getName())*1.2+fsd.CoralLiquor.get(p.getUniqueId())*3,p);	
						*/
					}
				}
			}
		}
	}
	
	
	public void DrunkenDance(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec = 7*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 22 && fsd.DrunkenDance.getOrDefault(p.getUniqueId(),0)>=1) {
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && !p.isSneaking() && !ddash.containsKey(p.getUniqueId()))
			{
				
				ev.setCancelled(true);
				{
					if(thcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (thcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
			        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("음주가무 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						    }
			        		else {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use DrunkenDance").create());
			        		}
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {							
		                	thcooldown.remove(p.getName()); // removing player from HashMap

		                	if(ddasht.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(ddasht.get(p.getUniqueId()));
		                		ddasht.remove(p.getUniqueId());
		                	}
		                	

		            		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(Proficiency.getpro(p)>=1) {
										ddash.put(p.getUniqueId(), 0);
									}
				                }
				            }, 3); 
		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									ddash.remove(p.getUniqueId());
				                }
				            }, 25); 
		            		ddasht.put(p.getUniqueId(), task);
		                	for(int i =0; i<10; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	Location pl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(1.9));
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
													if(Party.hasParty(p) && Party.hasParty(p1))	{
													if(Party.getParty(p).equals(Party.getParty(p1)))
														{
															continue;
														}
													}
												}
												if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")&& !(a.hasMetadata("portal"))) 
												{
													LivingEntity le = (LivingEntity)a;
													Holding.holding(p, le, 20l);
													le.teleport(pl);
													atks(0.3, fsd.DrunkenDance.get(p.getUniqueId())*0.33, p, le,5);
													/*
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
														enar.setDamage(player_damage.get(p.getName())*0.21 + fsd.DrunkenDance.get(p.getUniqueId())*0.23);								
													}
													p.setCooldown(Material.YELLOW_TERRACOTTA,1);
													le.damage(0, p);
													le.damage(player_damage.get(p.getName())*0.21 + fsd.DrunkenDance.get(p.getUniqueId())*0.23, p);
													*/
												}
											}
						                }
						            }, i*2); 
			                }
		                	thcooldown.put(p.getName(), System.currentTimeMillis()*1.0);
						}
		            }
		            else // if cooldown doesn't have players name in it
		            {

	                	if(ddasht.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(ddasht.get(p.getUniqueId()));
	                		ddasht.remove(p.getUniqueId());
	                	}


	            		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									ddash.put(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								ddash.remove(p.getUniqueId());
			                }
			            }, 25); 
	            		ddasht.put(p.getUniqueId(), task);
		            	for(int i =0; i<10; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	Location pl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(1.9));
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
												if(Party.hasParty(p) && Party.hasParty(p1))	{
												if(Party.getParty(p).equals(Party.getParty(p1)))
													{
														continue;
													}
												}
											}
											if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")&& !(a.hasMetadata("portal"))) 
											{
												LivingEntity le = (LivingEntity)a;
												Holding.holding(p, le, 20l);
												le.teleport(pl);
												atks(0.3, fsd.DrunkenDance.get(p.getUniqueId())*0.33, p, le,5);
												/*
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
													enar.setDamage(player_damage.get(p.getName())*0.21 + fsd.DrunkenDance.get(p.getUniqueId())*0.23);								
												}
												p.setCooldown(Material.YELLOW_TERRACOTTA,1);
												le.damage(0, p);
												le.damage(player_damage.get(p.getName())*0.21 + fsd.DrunkenDance.get(p.getUniqueId())*0.23, p);
												*/
											}
										}
					                }
					            }, i*2); 
		                }
		            	thcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
					}
				}}
		}
	}
		

	
	public void Ddash(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 22) {
			if(ddash.containsKey(p.getUniqueId()) && p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && !p.isSneaking() && thcooldown.containsKey(p.getName()))
			{
				ev.setCancelled(true);
				
            	if(ddasht.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(ddasht.get(p.getUniqueId()));
            		ddasht.remove(p.getUniqueId());
            	}
				ddash.remove(p.getUniqueId());

            	if(dsmasht.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(dsmasht.get(p.getUniqueId()));
            		dsmasht.remove(p.getUniqueId());
            	}


        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							dsmash.put(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						dsmash.remove(p.getUniqueId());
	                }
	            }, 25); 
        		dsmasht.put(p.getUniqueId(), task);
				
				ArrayList<Location> line = new ArrayList<Location>();
                HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
                AtomicInteger j = new AtomicInteger(0);
                for(double d = 0; d <= 4.3; d += 0.3) {
                    Location pl = p.getEyeLocation();
					pl.add(pl.clone().getDirection().normalize().multiply(d));
					if(!pl.getBlock().isPassable()) {
						break;
					}
					line.add(pl);
                }
            	line.forEach(i -> {
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
	                	public void run() 
		                {	
	             			p.teleport(i);
							p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 20, 3,3,3,0);
							p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 20, 3,3,3,0);
	                    	for (Entity e : p.getNearbyEntities(3, 3, 3))
							{
	                    		if ((!(e == p))&& e instanceof LivingEntity) 
								{
									LivingEntity le = (LivingEntity)e;
									les.add(le);		                    			
								}
							}
			            }
                	   }, j.incrementAndGet()/50); 
				 });
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
	                	public void run() 
		                {	

         				for(LivingEntity le: les) {
							atks(0.8, fsd.DrunkenDance.get(p.getUniqueId())*0.8, p, le,5);
         					
         				}

			            }
        	   }, j.incrementAndGet()/50); 
        	}
		                
			
		}
	}

	
	public void DSmash(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 22) {
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && !p.isSneaking() && dsmash.containsKey(p.getUniqueId()) && thcooldown.containsKey(p.getName()))
			{
				ev.setCancelled(true);
            	if(dsmasht.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(dsmasht.get(p.getUniqueId()));
            		dsmasht.remove(p.getUniqueId());
            	}
				dsmash.remove(p.getUniqueId());
				
				
				for(int fl = 0; fl <15; fl +=1) {
					if(p.getLocation().clone().add(0, -0.25, 0).getBlock().isPassable()) {
						p.teleport(p.getLocation().add(0, -0.25, 0));
					}
					else {
						break;
					}
				}
                p.swingMainHand();
            	p.setFallDistance(0);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 0.1f);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_SPLASH, 1.0f, 0f);
				p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1.0f, 0f);
				p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 250, 3, 2, 3);
				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 250, 3, 2, 3, Material.PRISMARINE.createBlockData());
				for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 5, 5, 5))
				{
            		if (e instanceof Player) 
					{
						
						Player p1 = (Player) e;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								continue;
							}
						}
					}
					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
					{
						LivingEntity le = (LivingEntity)e;
							{
								atks(1.3, fsd.DrunkenDance.get(p.getUniqueId())*1.3, p, le,5);
								Holding.holding(p, le, 40l);
							}
					}
				}
		                
			
			}
		}
	}
	
	
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		p.getLocation();
		final Location tl = gettargetblock(p,5);
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 22 && (is.getType()==Material.FISHING_ROD)&& p.isSneaking()&& !p.isSprinting() && Proficiency.getpro(p) >=1)
			{
			p.setCooldown(Material.STRUCTURE_VOID, 9);
				ev.setCancelled(true);
				if(aultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (long) ((aultcooldown.get(p.getName())/1000d + 50/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(),1d)) - System.currentTimeMillis()/1000d); // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("바다신의 배 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Boat Of The Sea God").create());
		        		}
		            }
	                else // if timer is done
	                {
	                    aultcooldown.remove(p.getName()); // removing player from HashMap
	                    Boat bo = p.getWorld().spawn(tl, Boat.class);
	                    bo.setInvulnerable(true);
	                    bo.setGravity(false);
	                    bo.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.1));
	                    bo.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    bo.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(), p.getName()));
						Holding.invur(p, 100l);
						p.setCollidable(false);
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
		        		p.setFreezeTicks(0);
		        		
	                    bo.setBoatType(Type.DARK_OAK);
	                    bo.getNearbyEntities(6, 6, 6).forEach(e -> {
	                    	if (e instanceof Player) 
	        				{
	        					Player p1 = (Player) e;
	        					if(Party.hasParty(p) && Party.hasParty(p1))	{
	        					if(Party.getParty(p).equals(Party.getParty(p1)))
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
					        		p1.setFireTicks(0);
					        		p1.setFreezeTicks(0);
	        							Holding.invur(p1, 100l);
	        							return;
	        						}
	        					}
	        				}
	                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
	        				{
	        						LivingEntity le = (LivingEntity)e;
									atk1(4.0, p, le,5);
	    		                	for(int i =0; i<50; i++) {
	 			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	 						                @Override
	 						                public void run() 
	 						                {
	 			        						bo.eject();
	 			             					p.playSound(tl, Sound.BLOCK_CONDUIT_ATTACK_TARGET, 0.05f, 2f);
	 		        							p.getWorld().spawnParticle(Particle.WATER_WAKE, tl, 10,3,3,3);
	 			             					p.getWorld().spawnParticle(Particle.REDSTONE, tl, 10, 3,2,3,0, new DustOptions(Color.AQUA, 2));
												atk1(0.1, p, le,5);
	 						                }
	 						            }, i*2); 
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
	        	                    bo.getNearbyEntities(6, 6, 6).forEach(e -> {
				                		if (e instanceof Player) 
				        				{
				        					Player p1 = (Player) e;
				        					if(Party.hasParty(p) && Party.hasParty(p1))	{
				        					if(Party.getParty(p).equals(Party.getParty(p1)))
				        						{
				        							Holding.invur(p1, 30l);
				        							p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100,100,false,false));
				        						}
				        					}
				        				}
				                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
				        				{
				        						LivingEntity le = (LivingEntity)e;
												atk1(4.0, p, le,5);
				        						Holding.holding(p, le, 30l);
				        								
				        				}
				                	});
				                	bo.remove();
									p.setCollidable(true);
				                }
			            }, 100); 
		                aultcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    Boat bo = p.getWorld().spawn(tl, Boat.class);
                    bo.setInvulnerable(true);
                    bo.setGravity(false);
                    bo.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.1));
                    bo.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    bo.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(), p.getName()));
					Holding.invur(p, 100l);
					p.setCollidable(false);
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
	        		p.setFreezeTicks(0);

                    bo.setBoatType(Type.DARK_OAK);
                    bo.getNearbyEntities(6, 6, 6).forEach(e -> {
                    	if (e instanceof Player) 
        				{
        					Player p1 = (Player) e;
        					if(Party.hasParty(p) && Party.hasParty(p1))	{
        					if(Party.getParty(p).equals(Party.getParty(p1)))
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
				        		p1.setFireTicks(0);
				        		p1.setFreezeTicks(0);
        							Holding.invur(p1, 100l);
        							return;
        						}
        					}
        				}
                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
        				{
        						LivingEntity le = (LivingEntity)e;
								atk1(4.0, p, le,5);
    		                	for(int i =0; i<50; i++) {
 			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
 						                @Override
 						                public void run() 
 						                {
 			        						bo.eject();
 			             					p.playSound(tl, Sound.BLOCK_CONDUIT_ATTACK_TARGET, 0.05f, 2f);
 		        							p.getWorld().spawnParticle(Particle.WATER_WAKE, tl, 10,3,3,3);
 			             					p.getWorld().spawnParticle(Particle.REDSTONE, tl, 10, 3,2,3,0, new DustOptions(Color.AQUA, 2));
											atk1(0.1, p, le,5);
 						                }
 						            }, i*2); 
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
        	                    bo.getNearbyEntities(6, 6, 6).forEach(e -> {
			                		if (e instanceof Player) 
			        				{
			        					Player p1 = (Player) e;
			        					if(Party.hasParty(p) && Party.hasParty(p1))	{
			        					if(Party.getParty(p).equals(Party.getParty(p1)))
			        						{
			        							Holding.invur(p1, 30l);
			        							p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100,100,false,false));
			        						}
			        					}
			        				}
			                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
			        				{
			        						LivingEntity le = (LivingEntity)e;
											atk1(4.0, p, le,5);
			        						Holding.holding(p, le, 30l);
			        								
			        				}
			                	});
			                	bo.remove();
								p.setCollidable(true);
			                }
		            }, 100); 
	                aultcooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
	            }
			}	
			
			
    }


	
	public void ULT2(PlayerDropItemEvent ev)        
    {
		final Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 22 && (is.getType()==Material.FISHING_ROD)&& !p.isSneaking()&& p.isSprinting() && Proficiency.getpro(p) >=2)
			{
			p.setCooldown(Material.STRUCTURE_VOID, 9);
				ev.setCancelled(true);
				if(ault2cooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (ault2cooldown.get(p.getName())/1000d + 70*Obtained.ucd.getOrDefault(p.getUniqueId(),1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("물아일체 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Self and Other Oneness").create());
		        		}
	                }
	                else // if timer is done
	                {
	                    ault2cooldown.remove(p.getName()); // removing player from HashMap
	                    HashSet<LivingEntity> les = new HashSet<>();
     					p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_MOOD, 1, 2f);
     					p.playSound(p.getLocation(), Sound.AMBIENT_WARPED_FOREST_MOOD, 1, 2f);
     					p.setGravity(false);
     					Holding.invur(p, 140l);
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
						AtomicInteger j = new AtomicInteger();
	                	for(int i =0; i<10; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	j.incrementAndGet();
		    							p.getWorld().spawnParticle(Particle.WATER_WAKE, p.getLocation(), 1000,13,13,13);
		    							p.getWorld().spawnParticle(Particle.COMPOSTER, p.getLocation(), 1000,13,13,13);
		    							p.getWorld().spawnParticle(Particle.WATER_BUBBLE, p.getLocation(), 1000,13,13,13);
		    							p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 1000,13,13,13);
		    							p.getWorld().spawnParticle(Particle.WARPED_SPORE, p.getLocation(), 1000,13,13,13);
		             					p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 2f);
		             					p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2f);
		        	                    p.getNearbyEntities(13, 13, 13).forEach(e -> {
		        	                    	if (e instanceof Player) 
		        	        				{
		        	        					Player p1 = (Player) e;
		        	        					if(Party.hasParty(p) && Party.hasParty(p1))	{
		        	        					if(Party.getParty(p).equals(Party.getParty(p1)))
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
		        					        		p1.setFireTicks(0);
		        	        							Holding.invur(p1, 100l);
		        	        							return;
		        	        						}
		        	        					}
		        	        				}
		        	                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
		        	        				{
	        	        						LivingEntity le = (LivingEntity)e;
	        				                	les.add(le);
	        				                	final Location lel = le.getLocation().clone().add(0,j.get(),0);
	        				                	if(lel.getBlock().isPassable() && lel.getBlock().getType() != Material.WATER) {
	        		        						p.sendBlockChange(lel, Material.WATER.createBlockData());
	        				                	}
	        	        						Holding.holding(p, le, 100l);
		        	        								
		        	        				}
		        	            		});
					                }
					            }, i*10); 
		                	}

	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {

				                	p.setGravity(true);
        							p.getWorld().spawnParticle(Particle.WATER_WAKE, p.getLocation(), 1000,13,13,13);
        							p.getWorld().spawnParticle(Particle.COMPOSTER, p.getLocation(), 1000,13,13,13);
        							p.getWorld().spawnParticle(Particle.WATER_BUBBLE, p.getLocation(), 1000,13,13,13);
        							p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 1000,13,13,13);
        							p.getWorld().spawnParticle(Particle.WARPED_SPORE, p.getLocation(), 1000,13,13,13);
	             					p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 1000, 13,12,13,0, new DustOptions(Color.AQUA, 2));
	             					p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, 1, 2f);
	             					p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ATTACK_TARGET, 1, 2f);
	             					p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 2f);
	             					p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2f);
	             					p.playSound(p.getLocation(), Sound.AMBIENT_BASALT_DELTAS_MOOD, 1, 2f);
				                    les.forEach(e -> {
				                    	if (e instanceof Player) 
				        				{
				        					Player p1 = (Player) e;
				        					if(Party.hasParty(p) && Party.hasParty(p1))	{
				        					if(Party.getParty(p).equals(Party.getParty(p1)))
				        						{
				        							return;
				        						}
				        					}
				        				}
				                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
				        				{
				        						LivingEntity le = (LivingEntity)e;
												atk1(40.0, p, le, 5);
				        								
				        				}
			                		});
				                }
			            }, 100); 
		                ault2cooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    HashSet<LivingEntity> les = new HashSet<>();
 					p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_MOOD, 1, 2f);
 					p.playSound(p.getLocation(), Sound.AMBIENT_WARPED_FOREST_MOOD, 1, 2f);
 					p.setGravity(false);
 					Holding.invur(p, 140l);
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
					AtomicInteger j = new AtomicInteger();
                	for(int i =0; i<10; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	j.incrementAndGet();
	    							p.getWorld().spawnParticle(Particle.WATER_WAKE, p.getLocation(), 1000,13,13,13);
	    							p.getWorld().spawnParticle(Particle.COMPOSTER, p.getLocation(), 1000,13,13,13);
	    							p.getWorld().spawnParticle(Particle.WATER_BUBBLE, p.getLocation(), 1000,13,13,13);
	    							p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 1000,13,13,13);
	    							p.getWorld().spawnParticle(Particle.WARPED_SPORE, p.getLocation(), 1000,13,13,13);
	             					p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 2f);
	             					p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2f);
	        	                    p.getNearbyEntities(13, 13, 13).forEach(e -> {
	        	                    	if (e instanceof Player) 
	        	        				{
	        	        					Player p1 = (Player) e;
	        	        					if(Party.hasParty(p) && Party.hasParty(p1))	{
	        	        					if(Party.getParty(p).equals(Party.getParty(p1)))
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
	        					        		p1.setFireTicks(0);
	        	        							Holding.invur(p1, 100l);
	        	        							return;
	        	        						}
	        	        					}
	        	        				}
	        	                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
	        	        				{
        	        						LivingEntity le = (LivingEntity)e;
        				                	les.add(le);
        				                	final Location lel = le.getLocation().clone().add(0,j.get(),0);
        				                	if(lel.getBlock().isPassable() && lel.getBlock().getType() != Material.WATER) {
        		        						p.sendBlockChange(lel, Material.WATER.createBlockData());
        				                	}
        	        						Holding.holding(p, le, 100l);
	        	        								
	        	        				}
	        	            		});
				                }
				            }, i*10); 
	                	}

                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {

			                	p.setGravity(true);
    							p.getWorld().spawnParticle(Particle.WATER_WAKE, p.getLocation(), 1000,13,13,13);
    							p.getWorld().spawnParticle(Particle.COMPOSTER, p.getLocation(), 1000,13,13,13);
    							p.getWorld().spawnParticle(Particle.WATER_BUBBLE, p.getLocation(), 1000,13,13,13);
    							p.getWorld().spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 1000,13,13,13);
    							p.getWorld().spawnParticle(Particle.WARPED_SPORE, p.getLocation(), 1000,13,13,13);
             					p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 1000, 13,12,13,0, new DustOptions(Color.AQUA, 2));
             					p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, 1, 2f);
             					p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ATTACK_TARGET, 1, 2f);
             					p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 2f);
             					p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2f);
             					p.playSound(p.getLocation(), Sound.AMBIENT_BASALT_DELTAS_MOOD, 1, 2f);
			                    les.forEach(e -> {
			                    	if (e instanceof Player) 
			        				{
			        					Player p1 = (Player) e;
			        					if(Party.hasParty(p) && Party.hasParty(p1))	{
			        					if(Party.getParty(p).equals(Party.getParty(p1)))
			        						{
			        							return;
			        						}
			        					}
			        				}
			                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
			        				{
			        						LivingEntity le = (LivingEntity)e;
											atk1(40.0, p, le,5);
			        								
			        				}
		                		});
			                }
		            }, 100); 
	                ault2cooldown.put(p.getName(), System.currentTimeMillis()*1.0); // adding players name + current system time in miliseconds
	            }
			}
    }
	

	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 22 && (is.getType()==Material.FISHING_ROD) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
		
		if(e.getDamage() >= 10) {
			e.setDamage(10);
		}
		
		if(ClassData.pc.get(p.getUniqueId()) == 22)
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD)
			{
					e.setCancelled(true);
			}
		}
		
	}
	/*
	
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 22) {
				if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
				{
						player_damage.put(p.getName(), p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				else {
					player_damage.put(p.getName(), 0d);
				}
			}
			
		}
	}
*/
	/*
	
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity e = (LivingEntity) d.getEntity();
        

		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 22) {
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
			{
					player_damage.put(p.getName(), p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow ar = (Arrow)d.getDamager();
			Entity e = d.getEntity();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
			    
				
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 22) {

					if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
					{
							player_damage.put(p.getName(), p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					else {
						player_damage.put(p.getName(), 0d);
					}
				}
			}
		}
	}
	
	*/
	
	
	public void Technic(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.getEntity().hasMetadata("fake")) 
		{
	        
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			
	
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 22 && p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD) {

				if(le == p) {
					d.setCancelled(true);
					return;
				}
				if (le instanceof Player) 
				{
					Player p1 = (Player) le;
					if(Party.hasParty(p) && Party.hasParty(p1))	{
					if(Party.getParty(p).equals(Party.getParty(p1)))
						{
						d.setCancelled(true);
							return;
						}
					}
				}
				if(d.getDamage()>0) {
					d.setDamage(d.getDamage() + fsd.Technic.get(p.getUniqueId())*0.55);
				}
			}
			
			if(drunk.containsKey(p.getName())) {
				d.setDamage(d.getDamage()*(1+drunk.get(p.getName())*0.016));
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
				if(Party.hasParty(p) && Party.hasParty(p1))	{
				if(Party.getParty(p).equals(Party.getParty(p1)))
					{
					d.setCancelled(true);
						return;
					}
				}
			}
			if(ClassData.pc.get(p.getUniqueId()) == 22 && p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD) {
				if(le == p) {
					d.setCancelled(true);
					return;
				}
				if(d.getDamage()>0) {
					d.setDamage(d.getDamage() + fsd.Technic.get(p.getUniqueId())*0.58);
				}
			}
			if(drunk.containsKey(p.getName())) {
				d.setDamage(d.getDamage()*(1+drunk.get(p.getName())*0.016));
			}
		}
		}
	}
}



