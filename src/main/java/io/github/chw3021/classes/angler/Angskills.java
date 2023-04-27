package io.github.chw3021.classes.angler;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
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
import org.bukkit.World;
import org.bukkit.Particle.DustOptions;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.PufferFish;
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
import org.bukkit.event.player.PlayerItemHeldEvent;
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
	private HashMap<String, Long> rscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> prcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> jmcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> thcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> pncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> aultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> ault2cooldown = new HashMap<String, Long>();
	private HashMap<String, Integer> drunk = new HashMap<String, Integer>();
	private HashMap<String, Integer> drunkt = new HashMap<String, Integer>();
	
	private HashMap<UUID, Integer> spout = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> spoutt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> giantc = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> giantct = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> puf = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> puft = new HashMap<UUID, Integer>();
	
	
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
				p.setCooldown(Material.STICK, 1);
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
					p.setCooldown(Material.STICK, 9);
					if(p.isSneaking()) {
						
						SkillBuilder bd = new SkillBuilder()
								.player(p)
								.cooldown(sec)
								.kname("미끼")
								.ename("Bait")
								.slot(0)
								.hm(pncooldown)
								.skillUse(() -> {
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
								});
						bd.execute();
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
			p.setCooldown(Material.STICK, 13);
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
					le.teleport(tl);
					Holding.superholding(p, le, 10l);
				});
				baited.removeAll(p.getUniqueId());
			}
			if(d.getCaught() instanceof LivingEntity) {

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("낚시")
						.ename("Fishing")
						.slot(1)
						.hm(rscooldown)
						.skillUse(() -> {
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
						});
				bd.execute();
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


	public void CoralRoots(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
	    
		
		if(ClassData.pc.get(p.getUniqueId()) == 22 && p.getCooldown(Material.STRING)<=0 && fsd.Whipping.getOrDefault(p.getUniqueId(),0)>=1) {
			if((ac == Action.LEFT_CLICK_AIR || ac==Action.LEFT_CLICK_BLOCK) &&p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && !p.isSneaking()) {
	
			if(p.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD && (p.isSneaking()))
			{
				final Location lel = gettargetblock(p,3);
				double sec = 9*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("산호뿌리")
						.ename("CoralRoots")
						.slot(2)
						.hm(prcooldown)
						.skillUse(()->{

		                	if(giantct.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(giantct.get(p.getUniqueId()));
		                		giantct.remove(p.getUniqueId());
		                	}
		                	

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(Proficiency.getpro(p)>=1) {
										giantc.putIfAbsent(p.getUniqueId(), 0);
									}
				                }
				            }, 3); 
		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									giantc.remove(p.getUniqueId());
				                }
				            }, 25); 
		                	giantct.put(p.getUniqueId(), task);
							
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
						});
				bd.execute();
					
				
				}	
			}
		}
	}
	

	public void CoralPrison(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 22 && p.getCooldown(Material.STRING)<=0  && (ac == Action.LEFT_CLICK_AIR || ac==Action.LEFT_CLICK_BLOCK)) {
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && p.isSneaking() &&giantc.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
            	if(giantct.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(giantct.get(p.getUniqueId()));
            		giantct.remove(p.getUniqueId());
            	}
				giantc.remove(p.getUniqueId());
				

            	if(puft.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(puft.get(p.getUniqueId()));
            		puft.remove(p.getUniqueId());
            	}
            	

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							puf.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 
        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						puf.remove(p.getUniqueId());
	                }
	            }, 25); 
            	puft.put(p.getUniqueId(), task);

            	final Location pl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(2.68)).clone();
            	final World pw = pl.getWorld();
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 0.8f, 1.2f);
				p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 0.8f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.8f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.1f, 1.5f);
				p.getWorld().spawnParticle(Particle.WATER_BUBBLE, pl, 600,2,2,2);
				p.getWorld().spawnParticle(Particle.WATER_SPLASH, pl, 600,2,2,2);
				

            	HashSet<Location> sph = new HashSet<>();
        		for(int ix = -4; ix<=4; ix+=1) {
        			for(int iy = -4; iy<=4; iy+=1) {
        				for(int iz = -4; iz<=4; iz+=1) {
        					if(ix*ix + iy*iy + iz*iz <= 16 && ix*ix + iy*iy + iz*iz >= 16){
        						sph.add(pl.clone().add(ix, iy, iz));
        					}
        				}
        			}
        		}
            	sph.forEach(l -> {
					FallingBlock fallingb = pw.spawnFallingBlock(l, Material.DARK_OAK_WOOD.createBlockData());
        			fallingb.setInvulnerable(true);
					fallingb.setDropItem(false);
					fallingb.setHurtEntities(false);
					fallingb.setGravity(false);
					fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					fallingb.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					fallingb.setMetadata("wrose", new FixedMetadataValue(RMain.getInstance(), p.getName()));
						
            	});
				
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
						atks(1.1,fsd.CoralRoots.get(p.getUniqueId())*1.5, p, le,7);
						Holding.holding(p, le, 40l);
					}
				}
			}
		}
	}
	

	public void PufferBomb(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 22 && p.getCooldown(Material.STRING)<=0  && (ac == Action.LEFT_CLICK_AIR || ac==Action.LEFT_CLICK_BLOCK)) {
			if(p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD && p.isSneaking() &&puf.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
            	if(puft.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(puft.get(p.getUniqueId()));
            		puft.remove(p.getUniqueId());
            	}
				puf.remove(p.getUniqueId());
				

            	final Location pl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(2.68)).clone();
            	final World pw = pl.getWorld();
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 0.8f, 1.2f);
				p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 0.8f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 0.8f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_PUFFER_FISH_BLOW_UP, 0.8f, 1.5f);
				p.getWorld().spawnParticle(Particle.WATER_BUBBLE, pl, 600,2,2,2);
				p.getWorld().spawnParticle(Particle.WATER_SPLASH, pl, 600,2,2,2);
				

				pw.spawn(pl, PufferFish.class, pf->{
					pf.setAI(false);
					pf.setGravity(true);
					pf.setCollidable(false);
					pf.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					pf.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(), p.getName()));
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		    				p.playSound(pf.getLocation(), Sound.ENTITY_PUFFER_FISH_BLOW_OUT, 0.8f, 1.5f);
		    				p.getWorld().spawnParticle(Particle.WHITE_ASH, pl, 600,2,2,2);
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
		    						atks(1.3,fsd.CoralRoots.get(p.getUniqueId())*1.75, p, le,7);
		    						Holding.holding(p, le, 40l);
		    					}
		    				}
		                	pf.remove();
		                }
		            }, 28); 
				});

				
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

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("산호주")
						.ename("CoralLiquor")
						.slot(3)
						.hm(jmcooldown)
						.skillUse(()->{

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
						});
				bd.execute();
				
			}
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
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("음주가무")
						.ename("DrunkenDance")
						.slot(4)
						.hm(thcooldown)
						.skillUse(()->{
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
												}
											}
						                }
						            }, i*2); 
			                }
						});
				bd.execute();
			}
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
	
	
	public void ULT(PlayerItemHeldEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		final Location tl = gettargetblock(p,5).clone();
	    if(!isCombat(p)) {
	    	return;
	    }
		
		if(ClassData.pc.get(p.getUniqueId()) == 22&& ev.getNewSlot()==3 && (p.getInventory().getItemInMainHand().getType()==Material.FISHING_ROD)&& p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
			p.setCooldown(Material.STICK, 9);
				ev.setCancelled(true);
				double sec= 50/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(),1d);

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("바다신의 배")
						.ename("Boat Of The Sea God")
						.hm(aultcooldown)
						.skillUse(()->{

							HashSet<Location> loc = new HashSet<>();
							for(int i =0; i<9; i++) {
								loc.add(tl.clone().add(15, 13+i, 6));
								loc.add(tl.clone().add(15, 13+i, 28));
							}
							for(int i=0; i<3; i++) {
								for(int j=0; j<11;j++) {
									for(int k=0; k<11;k++) {
										loc.add(tl.clone().add(9+k, 14+i, 11+j));
										loc.add(tl.clone().add(9+k, 11+i, 11+j));
										loc.add(tl.clone().add(10+k, 10+i, 11+j));
										loc.add(tl.clone().add(10+k, 11+i, 12+j));
										
										loc.add(tl.clone().add(9+k, 3+i, 11+j));
										loc.add(tl.clone().add(9+k, 0+i, 11+j));
										loc.add(tl.clone().add(10+k, 3+i, 11+j));
										loc.add(tl.clone().add(10+k, 0+i, 11+j));
										loc.add(tl.clone().add(10+k, 2+i, 12+j));
										loc.add(tl.clone().add(10+k, 0+i, 12+j));
									}

								}
							}
							
							loc.forEach(l -> {
								l.getWorld().spawn(l, ArmorStand.class, ar->{
									ar.setInvisible(true);
									ar.getEquipment().setHelmet(new ItemStack(Material.DARK_OAK_WOOD));
									ar.setInvulnerable(true);
									ar.setCollidable(false);
									ar.setGravity(false);
									ar.setAI(false);
									ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
									ar.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(), p.getName()));

				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	ar.remove();
							                }
						            }, 100); 
								});
							});
							
							Holding.invur(p, 100l);
	     					p.playSound(tl, Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1, 2f);
	     					cleans(p);
			        		
		                    p.getNearbyEntities(6, 6, 6).forEach(e -> {
		                    	if (e instanceof Player) 
		        				{
		        					Player p1 = (Player) e;
		        					if(Party.hasParty(p) && Party.hasParty(p1))	{
		        					if(Party.getParty(p).equals(Party.getParty(p1)))
		        						{
		        							cleans(p1);
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
		        	                    p.getNearbyEntities(6, 6, 6).forEach(e -> {
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
					                }
				            }, 100); 
						});
				bd.execute();
			}	
			
			
    }


	
	public void ULT2(PlayerItemHeldEvent ev)        
    {
		final Player p = (Player)ev.getPlayer();
		ItemStack is = p.getInventory().getItemInMainHand();
	    

	    if(!isCombat(p)) {
	    	return;
	    }
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 22&& ev.getNewSlot()==4 && (is.getType()==Material.FISHING_ROD)&& !p.isSneaking()&& p.isSprinting() && Proficiency.getpro(p) >=2)
			{
			p.setCooldown(Material.STICK, 9);
				ev.setCancelled(true);
				double sec = 70*Obtained.ucd.getOrDefault(p.getUniqueId(),1d);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("물아일체")
						.ename("Self and Other Oneness")
						.hm(ault2cooldown)
						.skillUse(()->{
							HashSet<LivingEntity> les = new HashSet<>();
	     					p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_MOOD, 1, 2f);
	     					p.playSound(p.getLocation(), Sound.AMBIENT_WARPED_FOREST_MOOD, 1, 2f);
	     					p.setGravity(false);
	     					Holding.invur(p, 140l);
	     					cleans(p);
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
			        	        							cleans(p1);
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
						});
				bd.execute();
				
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



