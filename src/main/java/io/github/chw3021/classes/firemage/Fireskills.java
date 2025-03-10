package io.github.chw3021.classes.firemage;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.items.armors.ArmorSet;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.util.concurrent.AtomicDouble;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
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
import org.bukkit.Particle.DustTransition;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Fireskills extends Pak {
	
	private HashMap<String, Long> rscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> prcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> jmcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> thcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> pncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> eccooldown = new HashMap<String, Long>();
	private HashMap<String, Long> aultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> ault2cooldown = new HashMap<String, Long>();
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();

	

	private HashMap<UUID, Integer> lavaf = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> lavaft = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> sunc = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sunct = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> vcls = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> vclst = new HashMap<UUID, Integer>();
	

	private HashMap<UUID, Integer> frs = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> frst = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sunls = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sunlst = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> lavaball = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> lavaballt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> lavsh = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> lavsht = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> magblk = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> magblkt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> hotbody = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> hotbodyt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> casted = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> castedt = new HashMap<UUID, Integer>();
	
	private String path = new File("").getAbsolutePath();
	private FireSkillsData fsd;

	private static final Fireskills instance = new Fireskills ();
	public static Fireskills getInstance()
	{
		return instance;
	}


		
	public void er(PluginEnableEvent ev) 
	{
		FireSkillsData f = new FireSkillsData(FireSkillsData.loadData(path +"/plugins/RPGskills/FireSkillsData.data"));
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
				casted.remove(p.getUniqueId());
        		if(castedt.containsKey(p.getUniqueId())) {
        			Bukkit.getServer().getScheduler().cancelTask(castedt.remove(p.getUniqueId()));
        		}
			}
			
		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("FIreskills"))
		{
            e.getWhoClicked().setCooldown(CAREFUL, 2);
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				FireSkillsData f = new FireSkillsData(FireSkillsData.loadData(path +"/plugins/RPGskills/FireSkillsData.data"));
				fsd = f;
			}
			
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		FireSkillsData f = new FireSkillsData(FireSkillsData.loadData(path +"/plugins/RPGskills/FireSkillsData.data"));
		fsd = f;
		
	}
	
	
	public void Eruption(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && !p.isSneaking()) 
		{
			Action ac = ev.getAction();
			double sec =6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	        
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 12 && fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)>=1) {
                Location tl = gettargetblock(p,5).clone();
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
                    p.setCooldown(CAREFUL, 1);
					
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("분화구")
						.ename("Eruption")
						.slot(0)
						.hm(thcooldown)
						.skillUse(() -> {
						
		                    Casting(p);
		                    
		                	if(lavaft.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(lavaft.get(p.getUniqueId()));
		                		lavaft.remove(p.getUniqueId());
		                	}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(Proficiency.getpro(p)>=1) {
										lavaf.putIfAbsent(p.getUniqueId(), 0);
									}
				                }
				            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									lavaf.remove(p.getUniqueId());
				                }
				            }, 50); 
		                	lavaft.put(p.getUniqueId(), task);
		                	
		                    p.playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
		                    p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
             				tl.getWorld().spawnParticle(Particle.LANDING_LAVA, tl, 300, 2,0.2,2,0);
             				tl.getWorld().spawnParticle(Particle.ASH, tl, 300, 2,0.2,2,0);
             				tl.getWorld().spawnParticle(Particle.SQUID_INK, tl, 300, 2,0.2,2,0);
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                		@Override
			                	public void run() 
				                {	
	         					tl.getWorld().spawnParticle(Particle.FALLING_LAVA, tl, 1000, 2,5,2);
	             					tl.getWorld().spawnParticle(Particle.LAVA, tl, 300, 1,2,1,0);
	                 				tl.getWorld().spawnParticle(Particle.ASH, tl, 400, 2,3,2);
		    	                    p.playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
		    	                    p.playSound(p.getLocation(), Sound.ENTITY_MAGMA_CUBE_SQUISH, 1.0f, 0f);
		    	                    p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 0f);
		    	                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1.0f, 0f);
					            }
	                    	}, 10); 
	
							for(int i = 0; i <8; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(Entity e : tl.getWorld().getNearbyEntities(tl,2, 6, 2)) {
											if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
					                    		if (le==p && p.getLocation().add(0, 0.5, 0).getBlock().isPassable()) 
												{
							                    	p.teleport(p.getLocation().clone().add(0, 0.5, 0));	
							                    	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 50,1,false,false));
							                    	continue;
												}
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
					                    		atk1(0.032*(1+fsd.FlowingLava.get(p.getUniqueId())*0.04), p, le);
						                    	le.teleport(le.getLocation().clone().add(0, 0.5, 0));		
				             					
											}
										}
					                }
					            }, i*2+10);
		                    }
						});
				bd.execute();
					
				} 
				}
		}
	}
	
	

	
	public void FlowingLava(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && !p.isSneaking()&&lavaf.containsKey(p.getUniqueId())) 
		{
			Action ac = ev.getAction();
			
			if(ClassData.pc.get(p.getUniqueId()) == 12) {
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
                    p.setCooldown(CAREFUL, 1);
                    Casting(p);
					
	            	if(lavaft.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(lavaft.get(p.getUniqueId()));
	            		lavaft.remove(p.getUniqueId());
	            	}
					lavaf.remove(p.getUniqueId());

						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 2, false, false));
							HashSet<Location> line = new HashSet<Location>();
		                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    p.playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
		                    p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
		                    for(double d = 0.1; d <= 4.5; d += 0.1) {
								Location pl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(d));
								if(!pl.getBlock().isPassable()) {
									break;
								}
								line.add(pl);
		                    }
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                    line.forEach(l ->  {	
			             				p.getWorld().spawnParticle(Particle.FALLING_LAVA, l, 10,3,0.1,3,0);
			             				p.getWorld().spawnParticle(Particle.SMALL_FLAME, l, 10,3,0.1,3,0);
		             					p.teleport(l);
										for(Entity e : l.getWorld().getNearbyEntities(l,3.5, 4.5, 3.5)) {
											if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
					                    		les.add(le)	;
				             					
											}
										}
				                    });
				                }
				            }, 1);
							for(int i = 0; i <8; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(Entity e : les) {
											if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;

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
					                    		atk1(0.02*(1+fsd.FlowingLava.get(p.getUniqueId())*0.035), p, le);	
				             					
											}
										}
					                }
					            }, i*2+1);
		                    }
				} 
				}
		}
	}

	
	public void Ring(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && p.isSneaking()) 
		{
			final Location tl = gettargetblock(p,5);
			Action ac = ev.getAction();
			double sec =6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	        
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 12 && fsd.Ring.getOrDefault(p.getUniqueId(),0)>=1) {
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
                    p.setCooldown(CAREFUL, 1);
					
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("불의고리")
						.ename("Ring")
						.slot(1)
						.hm(rscooldown)
						.skillUse(() -> {
		                    Casting(p);
							
		                	if(sunct.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(sunct.get(p.getUniqueId()));
		                		sunct.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							sunc.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						sunc.remove(p.getUniqueId());
		    	                }
		    	            }, 40); 
		                	sunct.put(p.getUniqueId(), task);

			            	ArrayList<Location> ring = new ArrayList<Location>();
	                    	final Location one = tl.clone().add(0, -0.2, 0);
		                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
		                    	ring.add(one.clone().add(one.getDirection().normalize().rotateAroundY(angle).multiply(3.5)));
		                	} 
			            	for(int i = 0; i <5; i++) {
			                    AtomicInteger j = new AtomicInteger();	
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 0.1f, 0.5f);
					                	ring.forEach(l -> {
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													p.getWorld().spawnParticle(Particle.FLAME, l, 3, 0.35,0.2,0.35,0);								                    
								                }
								            }, j.incrementAndGet()/20); 
					                		
					                	});
					                }
					            }, i*4); 	                    	
		                    }
							for(int i = 0; i <15; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(Entity e : tl.getWorld().getNearbyEntities(tl,3.75, 3.75, 3.75)) {
											if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
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
					                    		atk1(0.2*(1+fsd.Ring.get(p.getUniqueId())*0.045), p, le);
					                    		
												if(le.getLocation().distance(tl)>=2.5) {
													le.teleport(tl);
												}
													
											}
										}
					                }
					            }, i*4); 	                    	
		                    }
						});
				bd.execute();
				} 
				}
		}
	}

	
	final private void SunClutch(Location tl, Integer k) {
    	ArrayList<Location> ring = new ArrayList<Location>();
        AtomicDouble j = new AtomicDouble(0);

    	for(double an = 0; an<Math.PI*2; an +=Math.PI/90) {
    		ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an+j.getAndAdd(0.01*k)).multiply(j.getAndAdd(0.01*k))));
    	}
    	ring.forEach(l ->{
    		l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,1, 0.1,0.1,0.1);
    		l.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, l,1, 0.1,0.1,0.1,0, new DustTransition(Color.ORANGE, Color.RED, k*1f));
    	});
	}
	
	public void SunClutch(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && p.isSneaking() && sunc.containsKey(p.getUniqueId())) 
		{

			final Location tl = gettargetblock(p,5);
			Action ac = ev.getAction();
			
			if(ClassData.pc.get(p.getUniqueId()) == 12) {
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
                    p.setCooldown(CAREFUL, 1);

                    Casting(p);
					
	                
	            	if(sunct.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(sunct.get(p.getUniqueId()));
	            		sunct.remove(p.getUniqueId());
	            	}
					sunc.remove(p.getUniqueId());
					


	            	if(vclst.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(vclst.get(p.getUniqueId()));
	            		vclst.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								vcls.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 3); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							vcls.remove(p.getUniqueId());
		                }
		            }, 40); 
	            	vclst.put(p.getUniqueId(), task);

                    		AtomicInteger k = new AtomicInteger(3);	
							for(int i = 0; i <3; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                    p.playSound(tl, Sound.ENTITY_BLAZE_BURN, 0.5f, 0f);
					                    p.playSound(tl, Sound.ENTITY_BLAZE_BURN, 0.5f, 2f);
					                    p.playSound(tl, Sound.ENTITY_SHULKER_CLOSE, 0.8f, 0f);
					                    p.playSound(tl, Sound.ENTITY_SHULKER_TELEPORT, 0.8f, 0f);
										SunClutch(tl,k.getAndDecrement());
										
										for(Entity e : tl.getWorld().getNearbyEntities(tl,5, 5, 5)) {
											if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
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
					                    		atk1(0.6*(1+fsd.Ring.get(p.getUniqueId())*0.057), p, le);
					                    		
												Holding.superholding(p,le,20l);
												le.teleport(tl);
											}
										}
					                }
					            }, i*9+20); 	                    	
		                    }
				}
				}
		}
	}
	

	final private void Volc(Location tl, Integer j, Player p) {
    	ArrayList<Location> ring = new ArrayList<Location>();
		p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 0.7f, 0);

    	for(double an = 0; an<Math.PI*2; an +=Math.PI/180) {
    		ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an+j*0.25).multiply(an)).add(0, an+j*0.06, 0));
    	}
    	ring.forEach(l -> {
			tl.getWorld().spawnParticle(Particle.ASH, l, 2, 0.5,0.5,0.5,0);
			tl.getWorld().spawnParticle(Particle.SMALL_FLAME, l, 2, 0.5,0.5,0.5,0.1);
    		
    	});
	}

	
	public void VolcanicStorm(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 12) {
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && p.isSneaking()&&vcls.containsKey(p.getUniqueId())) 
		{

			final Location tl = gettargetblock(p,5);
			Action ac = ev.getAction();
			
			
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
                    p.setCooldown(CAREFUL, 1);

                    Casting(p);
					
	            	if(vclst.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(vclst.get(p.getUniqueId()));
	            		vclst.remove(p.getUniqueId());
	            	}
					vcls.remove(p.getUniqueId());

                	tl.getWorld().playSound(tl, Sound.ENTITY_HORSE_BREATHE, 0.8f, 0);
                	tl.getWorld().playSound(tl, Sound.BLOCK_POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON, 1, 0);
                	tl.getWorld().playSound(tl, Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1, 0);
                    AtomicInteger j = new AtomicInteger();	
							for(int i = 0; i <15; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	Volc(tl.clone(),j.getAndIncrement(), p);
										for(Entity e : tl.getWorld().getNearbyEntities(tl,6, 7, 6)) {
											if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
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
					                    		atk1(0.25*(1+fsd.Ring.get(p.getUniqueId())*0.047), p, le);
					                    		
												le.teleport(tl);
													
											}
										}
					                }
					            }, i*3); 	                    	
		                    }
				}
				}
		}
	}
	
	final private void AliveFlame(Location tl, LivingEntity le) {
    	ArrayList<Location> line = new ArrayList<Location>();
        for(double d=0.1; d<tl.distance(le.getLocation()); d += 0.1) {
        	Location one = tl.clone();
        	one.add(le.getLocation().toVector().subtract(one.toVector()).normalize().multiply(d));
        	line.add(one);
    	} 
        line.forEach(fl -> tl.getWorld().spawnParticle(Particle.FLAME, fl, 3, 0.1,0.1,0.1,0));
        
	}
	
	
	public void AliveFlame(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 12 && fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)>=1) {
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && p.isSneaking())
		{
			double sec =6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);


			

			final Location tl = gettargetblock(p,5);
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("살아있는불꽃")
						.ename("AliveFlame")
						.slot(5)
						.hm(prcooldown)
						.skillUse(() -> {
		                    Casting(p);
							
		                    
		                	if(frst.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(frst.get(p.getUniqueId()));
		                		frst.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							frs.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						frs.remove(p.getUniqueId());
		    	                }
		    	            }, 40); 
		                	frst.put(p.getUniqueId(), task);
		                	
		                	
							p.playSound(tl, Sound.BLOCK_FIRE_AMBIENT, 1, 0.5f);
							p.playSound(tl, Sound.BLOCK_BLASTFURNACE_FIRE_CRACKLE, 1, 0);
							tl.getWorld().spawnParticle(Particle.BLOCK_MARKER, tl, 4, 0.1,0.1,0.1,getBd(Material.FIRE));
							for(int i = 0; i <20; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										p.playSound(tl, Sound.ITEM_FIRECHARGE_USE, 0.3f, 0.346f);
										for(Entity e : tl.getWorld().getNearbyEntities(tl,4, 4, 4)) {
											if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
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
												LivingEntity le = (LivingEntity)e;
												AliveFlame(tl,le);
					                    		atk1(0.07*(1+fsd.AliveFlame.get(p.getUniqueId())*0.016), p, le);
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
	
	final private void FireStrike(Location il, int a) {

		ArrayList<Location> line = new ArrayList<Location>();
        for(double an = Math.PI/5+Math.PI/18*a; an>-Math.PI/5-Math.PI/18*a; an-=Math.PI/90) {
        	Location al = il.clone().add(il.clone().getDirection().multiply(a/2));
        	al.add(al.getDirection().clone().normalize().multiply(4.5+a).rotateAroundY(an));
        	line.add(al);
        }
        line.forEach(l -> {
			l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 1, 0.1,0.1,0.1, 0); 
			l.getWorld().spawnParticle(Particle.FLAME, l, 5, 0.15,0.15,0.15, 0); 
			l.getWorld().spawnParticle(Particle.DRIPPING_LAVA, l, 2, 0.15,0.15,0.15, 0); 
        	
        });
	}

	
	
	public void FireStrike(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 12) {
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && p.isSneaking() && frs.containsKey(p.getUniqueId()))
		{
			
				
				ev.setCancelled(true);
                Casting(p);
				
                
            	if(frst.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(frst.get(p.getUniqueId()));
            		frst.remove(p.getUniqueId());
            	}
				frs.remove(p.getUniqueId());
				


            	if(sunlst.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(sunlst.get(p.getUniqueId()));
            		sunlst.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							sunls.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						sunls.remove(p.getUniqueId());
	                }
	            }, 40); 
            	sunlst.put(p.getUniqueId(), task);

            	Location al = p.getEyeLocation().clone();
            	Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(3)).clone();

                p.setCooldown(CAREFUL, 1);
                p.swingMainHand();
            	AtomicInteger j = new AtomicInteger();
                
				for(int i =0; i<4; i++) {
                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.getWorld().playSound(tl, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.35f, 0f);
								p.getWorld().playSound(tl, Sound.ITEM_FIRECHARGE_USE, 0.5f, 0f);
								FireStrike(al,j.getAndIncrement());
								
								for (Entity a : tl.getWorld().getNearbyEntities(tl, 5.75, 5, 5.75))
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
										
			                    		atk1(0.36*(1+fsd.AliveFlame.get(p.getUniqueId())*0.034), p, le);
										
									}
								}
			                }
			            }, i*4); 
                }

				}
		}
	}

	
	public void SunLightSpear(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 12) {
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && p.isSneaking() && sunls.containsKey(p.getUniqueId()))
		{
			
				
				ev.setCancelled(true);
                Casting(p);
				

            	if(sunlst.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(sunlst.get(p.getUniqueId()));
            		sunlst.remove(p.getUniqueId());
            	}
				sunls.remove(p.getUniqueId());
                p.setCooldown(CAREFUL, 1);


    			final Location tl = gettargetblock(p,5);
    			
            	ArrayList<Location> line = new ArrayList<Location>();
				HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                for(double d = 0.1; d <= 6.3; d += 0.2) {
                    Location pl = p.getEyeLocation().add(0, 3, 0).clone();
					pl.add(tl.toVector().subtract(pl.clone().toVector()).normalize().multiply(d));
					line.add(pl);
                }
                for(int i =0; i<6; i++) {
             	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {

		                    	p.getWorld().spawnParticle(Particle.FLAME,tl,50, 3,0.1,3,0);
			                	p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, 0.5f, 2f);
			                	p.playSound(p.getLocation(), Sound.ITEM_GLOW_INK_SAC_USE,0.5f, 2f);
			                	p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_QUICK_CHARGE_2, 0.3f, 2f);
			                }
             	   }, i*5); 
				}
            	
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 0.8f, 2f);
	                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.8f, 2f);
	                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 0f);
	                	p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_THROW, 0.7f, 0f);
	                	AtomicInteger j = new AtomicInteger();
	                    for(Location l : line) {
	                  	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
			                    	tl.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l.clone().add(0, -0.289, 0),5, 0.1,0.1,0.1,0);
			                    	tl.getWorld().spawnParticle(Particle.GLOW, l.clone().add(0, -0.289, 0),5, 0.1,0.1,0.1,0);
				                }
	                  	   	}, j.incrementAndGet()/6); 

	                    	for (Entity a : tl.getWorld().getNearbyEntities(l, 5, 5, 5))
							{
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
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
									LivingEntity le = (LivingEntity)a;
									les.add(le);
								}
							}
	                    }
	                }
				}, 30); 
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	for(LivingEntity le : les) {
	                		
                    		atk1(1.4*(1+fsd.AliveFlame.get(p.getUniqueId())*0.05), p, le);
                    		
							le.teleport(line.get(line.size()-1));
							Holding.holding(p, le, 18l);
	                	}
		                for(int i =0; i<6; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {

				                    	p.getWorld().spawnParticle(Particle.FLAME,line.get(line.size()-1),10, 5,0.1,5,0);
				                    	p.getWorld().spawnParticle(Particle.GLOW,line.get(line.size()-1),10, 5,0.1,5,0);
					                	for(LivingEntity le : les) {
				                    		atk1(0.153*(1+fsd.AliveFlame.get(p.getUniqueId())*0.015), p, le);
				                    		
											
					                	}
					                }
		                	   }, i*3); 
						}
	                	
	                }
				}, 30); 
			}
		}
	}
	
	final private void Breath1(Location il) {
	    World w = il.getWorld();
	    for (double angle = 0; angle < Math.PI * 4; angle += Math.PI / 60) {
	        for (double directionAngle : new double[]{Math.PI / 6, -Math.PI / 6}) {
	            Location particleLocation = il.clone();
	            particleLocation.add(
	                particleLocation.getDirection()
	                    .clone()
	                    .rotateAroundY(directionAngle)
	                    .rotateAroundAxis(il.getDirection(), angle)
	                    .normalize()
	                    .multiply(angle + 0.2)
	            );
	            w.spawnParticle(Particle.FLAME, particleLocation, 2, 0.1, 0.1, 0.1, 0.1);
	        }
	    }
	}

	
	public void Breath(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 12 && fsd.Breath.getOrDefault(p.getUniqueId(),0)>=1) {
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && !p.isSneaking())
		{
			double sec =7*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		    
			
			
				ev.setCancelled(true);
				
				
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("화염의숨결")
						.ename("Breath")
						.slot(4)
						.hm(eccooldown)
						.skillUse(() -> {
						p.setCooldown(CAREFUL, 1);
		                    Casting(p);
		                	if(lavsht.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(lavsht.get(p.getUniqueId()));
		                		lavsht.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							lavsh.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						lavsh.remove(p.getUniqueId());
		    	                }
		    	            }, 40); 
		                	lavsht.put(p.getUniqueId(), task);
		                	
		                	final Location tl = p.getEyeLocation().clone().add(0, -0.65, 0);
		                	Breath1(tl);
		                	
							p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 40, 4, false, false));
							for(int i = 0; i <3; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 0.8f, 1.2f);
										p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 0.7f, 0);
												for(Entity e :tl.getWorld().getNearbyEntities(tl.clone().add(tl.getDirection().clone().normalize().multiply(3)),4, 4, 4)) {
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
													if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
														LivingEntity le = (LivingEntity)e;
														
							                    		atk1(0.4*(1+fsd.Breath.get(p.getUniqueId())*0.05), p, le);
															
													}
												}
					                }
					            }, i*3); 	                    	
		                    }
						});
				bd.execute();
			
				}
		}
	}
	

	
	public void LavaShower(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && !p.isSneaking()&& lavsh.containsKey(p.getUniqueId()))
		{
			
			if(ClassData.pc.get(p.getUniqueId()) == 12) {
				ev.setCancelled(true);
	            Casting(p);
				
				
	        	if(lavsht.containsKey(p.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(lavsht.get(p.getUniqueId()));
	        		lavsht.remove(p.getUniqueId());
	        	}
				lavsh.remove(p.getUniqueId());
	
	        	if(lavaballt.containsKey(p.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(lavaballt.get(p.getUniqueId()));
	        		lavaballt.remove(p.getUniqueId());
	        	}
	
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							lavaball.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 
	
	    		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						lavaball.remove(p.getUniqueId());
	                }
	            }, 40); 
	        	lavaballt.put(p.getUniqueId(), task);
	

				final Location tl = gettargetblock(p,5);				
				p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 30, 4, false, false));
				
				for(int i = 0; i <10; i++) {
	                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 0.8f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_POINTED_DRIPSTONE_DRIP_LAVA, 0.8f, 1.5f);
							p.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.7f, 02);
							tl.getWorld().spawnParticle(Particle.FALLING_LAVA, tl.clone().add(0, 5, 0), 100, 6,0.1,6,0.5);
							tl.getWorld().spawnParticle(Particle.LANDING_LAVA, tl.clone().add(0, 5, 0), 100, 6,0.1,6,0.5);
							tl.getWorld().spawnParticle(Particle.LARGE_SMOKE, tl.clone().add(0, 5, 0), 100, 6,0.1,6,0.5);
	
							for (Entity e :tl.getWorld().getNearbyEntities(tl, 6, 10, 6))
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
								if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
									LivingEntity le = (LivingEntity)e;
		                    		atk1(0.3*(1+fsd.Breath.get(p.getUniqueId())*0.05), p, le);
								}
								
							}
		                }
		            }, i*3); 	                    	
	            }
				}
		}
	}



	public void LavaBoom(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && !p.isSneaking()&& lavaball.containsKey(p.getUniqueId()))
		{
			
			if(ClassData.pc.get(p.getUniqueId()) == 12) {
				ev.setCancelled(true);
                Casting(p);
				

            	if(lavaballt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(lavaballt.get(p.getUniqueId()));
            		lavaballt.remove(p.getUniqueId());
            	}
				lavaball.remove(p.getUniqueId());
				


                Holding.invur(p,40l);
            	

                HashSet<Location> cir = new HashSet<>();

                ArrayList<Location> line = new ArrayList<>();
                
                final Location pel = p.getEyeLocation().clone().add(0, -0.6, 0).clone();
				final Location tl =	pel.clone().add(pel.getDirection().clone().normalize().multiply(5));
                
        		for(double ix = 0; ix<5; ix += 0.5) {
        			line.add(pel.clone().add(pel.getDirection().normalize().multiply(ix)).add(0, -0.5, 0));
        		}
                
        		for(int ix = -3; ix<3; ix++) {
        			for(int iy = -3; iy<3; iy++) {
        				for(int iz = -3; iz<3; iz++) {
        					if(ix*ix + iy*iy + iz*iz <= 9){
        						cir.add(tl.clone().add(ix, iy, iz));
        					}
        				}
        			}
        		}
        		AtomicInteger j = new AtomicInteger();

				p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 0.5f, 1.2f);
				p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 0.5f, 0.6f);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 0.5f, 0.6f);
				
        		line.forEach(l -> {
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

							p.playSound(l, Sound.BLOCK_LAVA_POP, 0.5f, 2);
							p.playSound(l, Sound.ITEM_BUCKET_FILL_LAVA, 0.5f, 0);
							p.playSound(l, Sound.ITEM_BUCKET_FILL_LAVA, 0.5f, 2);
							p.playSound(l, Sound.BLOCK_LAVA_AMBIENT, 0.5f, 2);
							l.getWorld().spawnParticle(Particle.DRIPPING_LAVA, l, 30, 0.51,0.51,0.51,0);
							l.getWorld().spawnParticle(Particle.FLAME, l, 25, 0.51,0.51,0.51,0);
							l.getWorld().spawnParticle(Particle.LAVA, l, 5, 0.3,0.3,0.3,0);
		                }
		            }, j.incrementAndGet()*2); 	
        		});
        		

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {

	            		

						p.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.7f, 0.2f);
						p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 0.2f, 0.6f);
						p.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.2f, 0);
						tl.getWorld().spawnParticle(Particle.ASH, tl, 25, 0.7,0.7,0.7,0);
						tl.getWorld().spawnParticle(Particle.FLAME, tl, 70, 0.7,0.7,0.7,0);
						tl.getWorld().spawnParticle(Particle.LAVA, tl, 2, 0.7,0.7,0.7,0);
						
	                }
	            }, 22); 	

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {

	            		

						p.playSound(tl, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.78f, 0.8f);
						p.playSound(tl, Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 0.6f, 0.3f);
						p.playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.6f, 0.2f);

	                	cir.forEach(l -> {
							l.getWorld().spawnParticle(Particle.FLAME, l, 10, 3,3,3,0.4);
							l.getWorld().spawnParticle(Particle.ASH, l, 5, 3,3,3,0.2);
							
	                	});
	                }
	            }, 30); 	
        		
				for(int i = 0; i <6; i++) {
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

							p.playSound(tl, Sound.BLOCK_LAVA_EXTINGUISH, 0.2f, 0);
							
		                	
							for(Entity e : tl.getWorld().getNearbyEntities(tl,6, 6, 6)) {
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
								if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
									LivingEntity le = (LivingEntity)e;
		                    		atk1(0.24*(1+fsd.Breath.get(p.getUniqueId())*0.05), p, le);
								}
							}
		                }
		            }, i+30); 	                    	
                }
				}
		}
	}
	

	
	public void Fireball(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && !p.isSneaking()) 
		{
			Action ac = ev.getAction();
			double sec =2*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	        
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 12 && fsd.Fireball.getOrDefault(p.getUniqueId(),0)>=1 && !p.hasCooldown(CAREFUL)) {
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);

					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("화염구")
							.ename("Fireball")
							.slot(2)
							.hm(jmcooldown)
							.skillUse(() -> {
								Casting(p);
								if(magblkt.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(magblkt.get(p.getUniqueId()));
									magblkt.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=2) {
											magblk.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										magblk.remove(p.getUniqueId());
									}
								}, 40);
								magblkt.put(p.getUniqueId(), task);

								Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
								fb.setYield(0.1f);
								fb.setShooter(p);
								fb.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
								fb.setIsIncendiary(false);
								fb.setMetadata("fb of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								if(Proficiency.getpro(p)>=1) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run() {
											Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
											fb.setYield(0.1f);
											fb.setShooter(p);
											fb.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
											fb.setIsIncendiary(false);
											fb.setMetadata("fb of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										}
									}, 3);
								}
							});
					bd.execute();
					
				} 
				}
		}
	}

	public void Fireball(ProjectileHitEvent ev) 
	{

        
		
		if(ev.getEntity() instanceof Fireball) {
			Fireball fb = (Fireball)ev.getEntity();
			if(fb.getShooter() instanceof Player) {
				Player p = (Player) fb.getShooter();
				if(fb.hasMetadata("fb of"+p.getName())) {
					fb.getWorld().spawnParticle(Particle.EXPLOSION, fb.getLocation(),1);
					p.playSound(fb.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					for(Entity n : fb.getNearbyEntities(4, 4, 4)) {
						if(n!=p && n instanceof LivingEntity&& !(n.hasMetadata("fake"))&& !(n.hasMetadata("portal"))) {
                    		if (n instanceof Player) 
							{
								
								Player p1 = (Player) n;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										continue;
									}
								}
							}
							LivingEntity le = (LivingEntity)n;
                    		atk1(0.25*(1+fsd.Fireball.get(p.getUniqueId())*0.04), p, le);
									
						}
					}
				}
			}
			
		}
	}
        
		
	
	public void Fireball(EntityExplodeEvent ev) 
	{
        
		
		if(ev.getEntity() instanceof Fireball) {
			Fireball fb = (Fireball)ev.getEntity();
			if(fb.getShooter() instanceof Player) {
				Player p = (Player) fb.getShooter();
				if(fb.hasMetadata("fb of"+p.getName())) {
					ev.setCancelled(true);
				}
			}
			
		}
	}
	

	
	
	public void MagmaBlock(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && !p.isSneaking()&& magblk.containsKey(p.getUniqueId())) 
		{
			Action ac = ev.getAction();
			
			if(ClassData.pc.get(p.getUniqueId()) == 12 && !p.hasCooldown(CAREFUL)) {
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
                    Casting(p);
					

	            	if(magblkt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(magblkt.get(p.getUniqueId()));
	            		magblkt.remove(p.getUniqueId());
	            	}
					magblk.remove(p.getUniqueId());

					final Location tl = p.getEyeLocation().clone();

					p.playSound(tl, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.3f, 2);
					p.playSound(tl, Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1f, 0);

					FallingBlock fallingb = p.getWorld().spawnFallingBlock(tl, Material.MAGMA_BLOCK.createBlockData());
					fallingb.setVelocity(tl.clone().getDirection().multiply(2));
					fallingb.setInvulnerable(true);
					fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
					fallingb.setMetadata("magblk", new FixedMetadataValue(RMain.getInstance(),p.getName()));
					fallingb.setVisualFire(true);
					fallingb.setDropItem(true);
					fallingb.setHurtEntities(true);
				}
			}
		}
	}


	final private void MagmaBlock(Player p, FallingBlock fallingb) {
		Location tl = fallingb.getLocation();
		tl.getWorld().spawnParticle(Particle.FLAME, tl, 100,5,5,5);
		tl.getWorld().spawnParticle(Particle.EXPLOSION, tl, 2,1,1,1);

		for (Entity e : tl.getWorld().getNearbyEntities(tl, 5, 5, 5))
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
			if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
				LivingEntity le = (LivingEntity)e;
        		atk1(0.39*(1+fsd.Fireball.get(p.getUniqueId())*0.054), p, le);
			}
			
		}
		p.playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
		fallingb.remove();
	}
	
	
	public void MagmaBlock(EntityDropItemEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(fallingb.hasMetadata("magblk")){
	        	ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("magblk").get(0).asString());
				MagmaBlock(p,fallingb);
	        }
		 }
	}


	
	public void MagmaBlock(EntityDamageByEntityEvent ev) 
	{
		if(ev.getDamager() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getDamager();
	        if(fallingb.hasMetadata("magblk")){
	        	ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("magblk").get(0).asString());
				MagmaBlock(p,fallingb);
	        }
		 }
	}

	
	public void MagmaBlock(EntityChangeBlockEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(fallingb.hasMetadata("magblk")){
	        	ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("magblk").get(0).asString());
				MagmaBlock(p,fallingb);
	        }
		 }
	}
	

	
	
	
	public void Spread(EntityDamageByEntityEvent d) 
	{
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&& !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		double sec =4*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD && (p.isSneaking()) && !(p.hasCooldown(MELEE)))
		{
		LivingEntity le = (LivingEntity)d.getEntity();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 12 && fsd.Spread.getOrDefault(p.getUniqueId(),0)>=1) {

			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(sec)
					.kname("확산")
					.ename("Spread")
					.slot(3)
					.hm(pncooldown)
					.skillUse(() -> {Casting(p);

						ItemStack ifire = new ItemStack(Material.FIRE_CHARGE, 3);
						p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
						Item fire = p.getWorld().dropItemNaturally(le.getLocation(), ifire);
						fire.setPickupDelay(9999);
						fire.setInvulnerable(true);
						fire.setThrower(p.getUniqueId());
						fire.setMetadata("af of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						fire.setFireTicks(1000);
						fire.setGlowing(true);
						AtomicInteger k = new AtomicInteger();

						for(Entity e : fire.getNearbyEntities(4, 4, 4)) {
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
							if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										LivingEntity le = (LivingEntity)e;
										fire.teleport(le);
										p.playSound(fire.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
										p.getWorld().spawnParticle(Particle.FLAME, fire.getLocation(), 10, 1,1,1,0);
										atk1(0.5*(1+fsd.Spread.get(p.getUniqueId())*0.08), p, le);

										if(Proficiency.getpro(p)>=1) {

											ItemStack ifire = new ItemStack(Material.FIRE_CHARGE, 3);
											p.playSound(le.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
											Item fire = p.getWorld().dropItemNaturally(le.getLocation(), ifire);
											fire.setPickupDelay(9999);
											fire.setInvulnerable(true);
											fire.setThrower(p.getUniqueId());
											fire.setMetadata("af of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
											fire.setFireTicks(1000);
											fire.setGlowing(true);
											AtomicInteger k = new AtomicInteger();

											for(Entity e : fire.getNearbyEntities(4, 4, 4)) {
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
												if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
													Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
														@Override
														public void run() {
															LivingEntity le = (LivingEntity)e;
															fire.teleport(le);
															p.playSound(fire.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
															p.getWorld().spawnParticle(Particle.FLAME, fire.getLocation(), 10, 1,1,1,0);
															atk1(0.5*(1+fsd.Spread.get(p.getUniqueId())*0.08), p, le);
														}
													}, k.incrementAndGet()*4);
												}
											}
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
												@Override
												public void run() {
													fire.remove();
												}
											}, k.incrementAndGet()*4);
										}
									}
								}, k.incrementAndGet()*4);
							}
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run() {
								fire.remove();
							}
						}, k.incrementAndGet()*4);
					});
			bd.execute();
					
					}
			
		}}
	}


	public void ULT(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();
		
		if(ClassData.pc.get(p.getUniqueId()) == 12 && ((is.getType()==Material.BLAZE_ROD)) && ev.getNewSlot()==3 && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				p.setCooldown(CAREFUL, 2);
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(70/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("불사조의 날갯짓")
						.ename("Flapping of Phoenix")
						.slot(6)
						.hm(aultcooldown)
						.skillUse(() -> {
							Casting(p);

							final Location one = p.getLocation().clone();
							p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_AMBIENT, 1.0f, 0.0f);
							Holding.invur(p, 120l);
							for(int i = 0; i <5; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										HashSet<Location> flap = new HashSet<Location>();
										HashSet<LivingEntity> les = new HashSet<LivingEntity>();
										for(double angle= -Math.PI; angle<0; angle += Math.PI/90) {
											for(int dou = 0; dou <15; dou+=1) {
												flap.add(one.clone().add(one.getDirection().clone().normalize().rotateAroundY(angle).multiply(dou)));
											}
										}
										for(double angle= Math.PI/2; angle>0; angle -= Math.PI/90) {
											for(int dou = 0; dou <15; dou+=1) {
												flap.add(one.clone().add(one.getDirection().clone().normalize().rotateAroundY(angle).multiply(dou)));
											}
										}
										p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
										p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 1.0f, 0f);
										p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 1f);
										p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
										flap.forEach(l -> {
											p.getWorld().spawnParticle(Particle.FLAME, l, 10, 2,2,2,0.1);
											for(Entity e : l.getWorld().getNearbyEntities(l,2, 7, 2)) {
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
												if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
													LivingEntity le = (LivingEntity)e;
													les.add(le);
												}
											}

										});
										for(LivingEntity le: les) {
											atk1(3.5, p, le);

										}

									}
								}, i*20+15);
							}
						});
				bd.execute();

			}	
			
			
    }



	public void ULT2(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();
		
		if(ClassData.pc.get(p.getUniqueId()) == 12 && ((is.getType()==Material.BLAZE_ROD)) && p.isSneaking() && ev.getNewSlot()==4 && Proficiency.getpro(p) >=2)
			{
			p.setCooldown(CAREFUL, 2);
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(80*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("두번째 태양")
						.ename("New SunRise")
						.slot(7)
						.hm(ault2cooldown)
						.skillUse(() -> {
							Casting(p);

							final World pw = p.getWorld();
							final Location sl = p.getLocation();
							final Location pl = p.getLocation().clone().add(0, 3, 0);
							HashSet<Location> spl = new HashSet<>();
							Holding.invur(p,120l);
							p.teleport(pl);
							final Location tl = pl.clone().add(0, 10, 0);
							p.playSound(pl, Sound.BLOCK_FIRE_AMBIENT, 1.0f, 0f);
							p.playSound(pl, Sound.BLOCK_LAVA_AMBIENT, 1.0f, 0f);
							p.playSound(pl, Sound.AMBIENT_BASALT_DELTAS_ADDITIONS, 1.0f, 0f);
							p.playSound(pl, Sound.ENTITY_GENERIC_BURN, 1.0f, 0f);
							p.playSound(pl, Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1.0f, 0f);
							p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,120,120,false,false));

							for(int ix = -4; ix<4; ix++) {
								for(int iy = -4; iy<4; iy++) {
									for(int iz = -4; iz<4; iz++) {
										if((ix*ix + iy*iy + iz*iz)<=16 && (ix*ix + iy*iy + iz*iz)>=14){
											spl.add(tl.clone().add(ix, iy, iz));
										}
									}
								}
							}
							spl.forEach(l -> {
								FallingBlock fallingb = pw.spawnFallingBlock(l,  Material.SHROOMLIGHT.createBlockData());
								fallingb.setInvulnerable(true);
								fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
								fallingb.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								fallingb.setMetadata("wrose", new FixedMetadataValue(RMain.getInstance(),p.getName()));
								fallingb.setDropItem(false);
								fallingb.setHurtEntities(false);
								fallingb.setGravity(false);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										fallingb.setMetadata("magblk", new FixedMetadataValue(RMain.getInstance(),p.getName()));
										fallingb.setDropItem(true);
										fallingb.setHurtEntities(true);
										fallingb.setGravity(true);
									}
								}, 120);
							});

							for(int i = 0; i <10; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {

										for(Entity e : pl.getWorld().getNearbyEntities(pl,20, 20, 20)) {
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
											if(p!=e &&e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
												atk1(0.1, p, le);
												le.teleport(sl);
											}
										}
										pl.getWorld().spawnParticle(Particle.FLAME, pl, 2000, 20,20,20,1);
										pl.getWorld().spawnParticle(Particle.WAX_ON, pl, 500, 20,20,20,1);
										pl.getWorld().spawnParticle(Particle.FLAME, tl, 500, 1,1,1,1);
										pl.getWorld().spawnParticle(Particle.FLASH, tl, 10, 5,5,5,1);

										p.playSound(pl, Sound.ITEM_FIRECHARGE_USE, 1, 0);
										p.playSound(pl, Sound.ENTITY_PHANTOM_FLAP, 1.0f, 0f);
										p.playSound(pl, Sound.BLOCK_POINTED_DRIPSTONE_DRIP_LAVA, 1.0f, 0f);
										p.playSound(pl, Sound.BLOCK_FIRE_AMBIENT, 1.0f, 2f);
										p.playSound(pl, Sound.ENTITY_GENERIC_BURN, 1.0f, 2f);
									}
								}, i*10+20);
							}
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
		
			if(ClassData.pc.get(p.getUniqueId()) == 12  && ((is.getType()==Material.BLAZE_ROD)) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	final private void Hotbody(Player p) {

    	if(hotbody.containsKey(p.getUniqueId())) {
    		Bukkit.getScheduler().cancelTask(hotbody.remove(p.getUniqueId()));
    	}
    	if(hotbodyt.containsKey(p.getUniqueId())) {
    		Bukkit.getScheduler().cancelTask(hotbodyt.remove(p.getUniqueId()));
    	}
        AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
        cloud.setParticle(Particle.LANDING_LAVA);
		cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		cloud.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(), p.getName()));
        cloud.setRadius(3f);
        cloud.setSource(p);
        cloud.setSilent(false);
        cloud.setColor(Color.RED);
        cloud.setDuration(60);
		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
        		Location pl = p.getLocation().clone();
        		cloud.teleport(pl);
                p.playSound(pl, Sound.BLOCK_POINTED_DRIPSTONE_DRIP_LAVA, 1.0f, 0f);
				for(Entity e : pl.getWorld().getNearbyEntities(pl,3.5, 2, 3.5)) {
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
					if(p!=e &&e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
                		atk1(0.03*casted.getOrDefault(p.getUniqueId(),0), p, le);
					}
				}
            }
		}, 0,5); 
		hotbody.put(p.getUniqueId(), task);
		int ht =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	if(hotbody.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(hotbody.remove(p.getUniqueId()));
            	}
            	cloud.remove();
            }
		}, 60);
		hotbodyt.put(p.getUniqueId(), ht);
	}

	final private void Casting(Player p) {
		
		if(casted.containsKey(p.getUniqueId())) {
	    	if(castedt.containsKey(p.getUniqueId())) {
	    		Bukkit.getScheduler().cancelTask(castedt.remove(p.getUniqueId()));
	    	}
	    	if(casted.get(p.getUniqueId())>5) {
				casted.put(p.getUniqueId(), 6);
	    	}
	    	else {
				casted.compute(p.getUniqueId(), (k,v) -> v+1);
	    	}
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.RED + "열기 : ("+casted.get(p.getUniqueId()) + "/6)").create());
		    }
    		else {
            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.RED + "HotBody : ("+casted.get(p.getUniqueId()) + "/6)").create());
    		}
			int ct = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	            	casted.remove(p.getUniqueId());
	            	castedt.remove(p.getUniqueId());
	            }
			}, 60); 
			castedt.put(p.getUniqueId(), ct);

			Hotbody(p);
		}
		else {
	    	if(castedt.containsKey(p.getUniqueId())) {
	    		Bukkit.getScheduler().cancelTask(castedt.remove(p.getUniqueId()));
	    	}
			casted.put(p.getUniqueId(), 1);
			int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	            	casted.remove(p.getUniqueId());
	            	castedt.remove(p.getUniqueId());
	            }
			}, 60); 
			castedt.put(p.getUniqueId(), task);
			Hotbody(p);
			return;
		}
	}
	
	
	public void HotBody(EntityDamageByEntityEvent d) 
	{
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
		{
			LivingEntity le = (LivingEntity)d.getEntity();
	        
	
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 12) {
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
					if(p==le) {d.setCancelled(true);}
					if(!d.isCancelled() && !d.getEntity().hasMetadata("fake")&& !d.getEntity().hasMetadata("portal")) {
						le.setFireTicks(le.getFireTicks()+50);
						dset2(d, p, (1+fsd.HotBody.get(p.getUniqueId())*0.044), le, 10);
					}
					
				}
		}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
			Projectile pr = (Projectile)d.getDamager();
	        if(pr.getShooter() instanceof Player) {
	        	Player p = (Player) pr.getShooter();
				if(ClassData.pc.get(p.getUniqueId()) == 12) {
					if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
					{
						LivingEntity le = (LivingEntity)d.getEntity();
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
						if(p==le) {d.setCancelled(true);}
						if(!d.isCancelled()&& !d.getEntity().hasMetadata("fake")&& !d.getEntity().hasMetadata("portal")) {
							le.setFireTicks(le.getFireTicks()+50);
							dset2(d, p, (1+fsd.HotBody.get(p.getUniqueId())*0.044), le, 10);
						}
						
					}
	        }
	
			
			
		}
		}
	}
	
	
	public void HotBodyre(EntityDamageByEntityEvent d) 
	{		
		if(d.getCause() == DamageCause.MAGIC || d.getCause() == DamageCause.THORNS) {
			return;
		}
		
	    if(d.getEntity() instanceof Player) {
			Player p = (Player)d.getEntity();
			if(p.getInventory().getItemInMainHand().getType()!=Material.BLAZE_ROD)
			{
				return;
			}

			if(ClassData.pc.get(p.getUniqueId()) == 12) 
			{
				if(d.getDamager() instanceof LivingEntity) 
				{
				LivingEntity le = (LivingEntity)d.getDamager();

						if(!d.isCancelled() && le!=p) {
							if(le instanceof Player) {
								if(ClassData.pc.getOrDefault(le.getUniqueId(),-1) == 12) {
									return;
								}
								le.damage(d.getDamage()*0.01,p);
								
							}
							else {
								le.damage(d.getDamage()*0.05,p);
							}
						}
					
				}
				if(d.getDamager() instanceof Projectile) 
				{
				Projectile pr = (Projectile)d.getDamager();
				if(pr.getShooter() instanceof LivingEntity) {
					LivingEntity le = (LivingEntity) pr.getShooter();
					
					
							if(!d.isCancelled() && le!=p) {
								if(ClassData.pc.getOrDefault(le.getUniqueId(),-1) == 12) {
									return;
								}
								if(le instanceof Player) {
									le.damage(d.getDamage()*(0.01),p);
									
								}
								else {
									le.damage(d.getDamage()*0.05,p);
									
								}
							}
						
					}	
					
				}
			}
				
	    }
	}

	public void HotBody(EntityDamageEvent d) 
	{		
	    
		if(d.getEntity() instanceof Player ) 
		{
			Player p = (Player)d.getEntity();
			if(ClassData.pc.get(p.getUniqueId()) == 12 && casted.containsKey(p.getUniqueId())) 
			{
				d.setDamage(d.getDamage()*(1-casted.get(p.getUniqueId())*0.12 ));
				if(Proficiency.getpro(p)>=1) {
					d.setDamage(d.getDamage()*0.6);
				}
			}	
			if((d.getCause() == DamageCause.FIRE|| d.getCause() == DamageCause.FIRE_TICK|| d.getCause() == DamageCause.LAVA|| d.getCause() == DamageCause.HOT_FLOOR)) {
				if(ClassData.pc.get(p.getUniqueId()) == 12 || ArmorSet.setnum(p) == 10) 
				{
					d.setCancelled(true);
					p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000, 4, false, false));
				}	
			}
		
		}	
	}
	
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

		if( p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
		{
	    
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 12)
			{
					e.setCancelled(true);
			}
		}
		
	}

}



