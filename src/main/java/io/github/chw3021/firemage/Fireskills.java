package io.github.chw3021.firemage;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.party.PartyData;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Fireskills implements Serializable, Listener {
	

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
	private FireSkillsData fsd = new FireSkillsData(FireSkillsData.loadData(path +"/plugins/RPGskills/FireSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		FireSkillsData f = new FireSkillsData(FireSkillsData.loadData(path +"/plugins/RPGskills/FireSkillsData.data"));
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("FIreskills"))
		{
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

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		FireSkillsData f = new FireSkillsData(FireSkillsData.loadData(path +"/plugins/RPGskills/FireSkillsData.data"));
		fsd = f;
		
	}
	
	@EventHandler
	public void Ring(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && p.isSneaking()) 
		{
			final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation();
			Action ac = ev.getAction();
			int sec = 6;
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 12) {
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					
					if(rscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (rscooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Ring").create());
		                }
		                else // if timer is done
		                {
		                    rscooldown.remove(p.getName()); // removing player from HashMap
							for(int i = 0; i <15; i++) {
			                    AtomicInteger j = new AtomicInteger();	
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						            	ArrayList<Location> ring = new ArrayList<Location>();
										p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
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
					            }, i*4); 	                    	
		                    }
							for(int i = 0; i <15; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(Entity e : p.getWorld().getNearbyEntities(tl,3.75, 3.75, 3.75)) {
											if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
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
													enar.setDamage(1*player_damage.get(p.getName())*0.05*(1+fsd.Ring.get(p.getUniqueId())*0.045));								
												}
												p.setSprinting(true);
												le.damage(0,p);
												le.damage(1*player_damage.get(p.getName())*0.05*(1+fsd.Ring.get(p.getUniqueId())*0.045),p);	
												if(le.getLocation().distance(tl)>=2.5) {
													le.teleport(tl);
												}
												p.setSprinting(false);		
											}
										}
					                }
					            }, i*4); 	                    	
		                    }
			                rscooldown.put(p.getName(), System.currentTimeMillis());
		                }
		                    
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	for(int i = 0; i <15; i++) {
		                    AtomicInteger j = new AtomicInteger();	
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
					            	ArrayList<Location> ring = new ArrayList<Location>();
									p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
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
				            }, i*1); 	                    	
	                    }
						for(int i = 0; i <15; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									for(Entity e : p.getWorld().getNearbyEntities(tl,3.75, 3.75, 3.75)) {
										if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
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
												enar.setDamage(1*player_damage.get(p.getName())*0.05*(1+fsd.Ring.get(p.getUniqueId())*0.045));								
											}
											p.setSprinting(true);
											le.damage(0,p);
											le.damage(1*player_damage.get(p.getName())*0.05*(1+fsd.Ring.get(p.getUniqueId())*0.045),p);	
											if(le.getLocation().distance(tl)>=2.5) {
												le.teleport(tl);
											}
											p.setSprinting(false);		
										}
									}
				                }
				            }, i*4); 	                    	
	                    }
		                rscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				} 
				}
		}
	}
	
	@EventHandler
	public void AliveFlame(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && p.isSneaking())
		{
			int sec = 6;
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 12) {
			final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation();
				
				ev.setCancelled(true);
				{
					if(prcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (prcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use AliveFlame").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {							
		                    prcooldown.remove(p.getName()); // removing player from HashMap

							p.playSound(tl, Sound.BLOCK_FIRE_AMBIENT, 1, 0.5f);
							p.playSound(tl, Sound.BLOCK_BLASTFURNACE_FIRE_CRACKLE, 1, 0);
							for(int i = 0; i <20; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										p.playSound(tl, Sound.ITEM_FIRECHARGE_USE, 0.3f, 0.346f);
						            	p.getWorld().spawnParticle(Particle.FLAME, tl, 60, 0.1,0.1,0.1,0.1);
						            	p.getWorld().spawnParticle(Particle.WHITE_ASH, tl, 20, 1.1,1.1,1.1,0.1);
					                }
					            }, i*2); 	                    	
		                    }
							for(int i = 0; i <20; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(Entity e : p.getWorld().getNearbyEntities(tl,4, 4, 4)) {
											if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
												LivingEntity le = (LivingEntity)e;
								            	ArrayList<Location> line = new ArrayList<Location>();
							                    for(double d=0.1; d<tl.distance(le.getLocation()); d += 0.1) {
							                    	Location one = tl.clone();
							                    	one.add(le.getLocation().toVector().subtract(one.toVector()).normalize().multiply(d));
							                    	line.add(one);
							                	} 
							                    line.forEach(fl -> p.getWorld().spawnParticle(Particle.FLAME, fl, 1, 0.1,0.1,0.1,0));
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
													enar.setDamage(0.57*player_damage.get(p.getName())*0.0665*(1+fsd.AliveFlame.get(p.getUniqueId())*0.0545));								
												}
												p.setSprinting(true);
												le.damage(0,p);
												le.damage(0.57*player_damage.get(p.getName())*0.0665*(1+fsd.AliveFlame.get(p.getUniqueId())*0.0545),p);	
												p.setSprinting(false);		
											}
										}
					                }
					            }, i*2);
		                    }
							prcooldown.put(p.getName(), System.currentTimeMillis());
						}
		            }
		            else // if cooldown doesn't have players name in it
		            {

						p.playSound(tl, Sound.BLOCK_FIRE_AMBIENT, 1, 0.5f);
						p.playSound(tl, Sound.BLOCK_BLASTFURNACE_FIRE_CRACKLE, 1, 0);
						for(int i = 0; i <20; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									p.playSound(tl, Sound.ITEM_FIRECHARGE_USE, 0.3f, 0.346f);
					            	p.getWorld().spawnParticle(Particle.FLAME, tl, 60, 0.1,0.1,0.1,0.1);
					            	p.getWorld().spawnParticle(Particle.WHITE_ASH, tl, 20, 1.1,1.1,1.1,0.1);
				                }
				            }, i*2); 	                    	
	                    }
						for(int i = 0; i <20; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									for(Entity e : p.getWorld().getNearbyEntities(tl,4, 4, 4)) {
										if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
											LivingEntity le = (LivingEntity)e;
							            	ArrayList<Location> line = new ArrayList<Location>();
						                    for(double d=0.1; d<tl.distance(le.getLocation()); d += 0.1) {
						                    	Location one = tl.clone();
						                    	one.add(le.getLocation().toVector().subtract(one.toVector()).normalize().multiply(d));
						                    	line.add(one);
						                	} 
						                    line.forEach(fl -> p.getWorld().spawnParticle(Particle.FLAME, fl, 1, 0.1,0.1,0.1,0));
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
												enar.setDamage(0.57*player_damage.get(p.getName())*0.0665*(1+fsd.AliveFlame.get(p.getUniqueId())*0.0545));								
											}
											p.setSprinting(true);
											le.damage(0,p);
											le.damage(0.57*player_damage.get(p.getName())*0.0665*(1+fsd.AliveFlame.get(p.getUniqueId())*0.0545),p);	
											p.setSprinting(false);		
										}
									}
				                }
				            }, i*2);
	                    }
		                prcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					}
				}}
		}
		}
	
	@EventHandler
	public void Breath(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !p.isSneaking())
		{
			int sec = 7;
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 12) {
				ev.setCancelled(true);
				
				{
					if(eccooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (eccooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Breath").create());
		                }
		                else // if timer is done
		                {
		                    eccooldown.remove(p.getName()); // removing player from HashMap

							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 3, false, false));
							for(int i = 0; i <6; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                    ArrayList<Location> cir = new ArrayList<Location>();
					                    AtomicInteger j = new AtomicInteger();
					                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
				                    	for(double angley= -Math.PI/9; angley<Math.PI/9; angley += Math.PI/45) {
				                        	Location one = p.getLocation();
					                    	one.setDirection(one.getDirection().rotateAroundY(angley));
						                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/45) {
						                    	one.setDirection(one.getDirection().rotateAroundAxis(p.getLocation().getDirection(),angle));
					                    		for(double i = 0.1; i<7.1;i+=0.5) {
					                    			Location two = one.clone();
						                    		two.add(two.getDirection().normalize().multiply(i));
							                    	cir.add(two);		                    			
					                    		}
						                    }
				                    	}	
										p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1.2f);
										p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 0);
					                	cir.forEach(l -> {
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													p.getWorld().spawnParticle(Particle.FLAME, l.add(0, -0.3, 0), 1, 0.2,0.2,0.2,0.5);
													for(Entity e : p.getWorld().getNearbyEntities(l,1, 1, 1)) {
														if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
															LivingEntity le = (LivingEntity)e;
															les.add(le);	
														}
													}
								                }
								            }, j.incrementAndGet()/2000); 
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
														enar.setDamage(2*player_damage.get(p.getName())*0.08*(1+fsd.Breath.get(p.getUniqueId())*0.1));								
													}
													p.setSprinting(true);
													le.damage(0,p);
													le.damage(2*player_damage.get(p.getName())*0.08*(1+fsd.Breath.get(p.getUniqueId())*0.1),p);	
													p.setSprinting(false);				                    	
							                    }
							                }
							            }, j.incrementAndGet()/2000); 
					                }
					            }, i*5); 	                    	
		                    }
							eccooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {

						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 3, false, false));
						for(int i = 0; i <6; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                    ArrayList<Location> cir = new ArrayList<Location>();
				                    AtomicInteger j = new AtomicInteger();
				                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
			                    	for(double angley= -Math.PI/9; angley<Math.PI/9; angley += Math.PI/45) {
			                        	Location one = p.getLocation();
				                    	one.setDirection(one.getDirection().rotateAroundY(angley));
					                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/45) {
					                    	one.setDirection(one.getDirection().rotateAroundAxis(p.getLocation().getDirection(),angle));
				                    		for(double i = 0.1; i<7.1;i+=0.5) {
				                    			Location two = one.clone();
					                    		two.add(two.getDirection().normalize().multiply(i));
						                    	cir.add(two);		                    			
				                    		}
					                    }
			                    	}	
									p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1.2f);
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 0);
				                	cir.forEach(l -> {
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												p.getWorld().spawnParticle(Particle.FLAME, l.add(0, -0.3, 0), 1, 0.2,0.2,0.2,0.5);
												for(Entity e : p.getWorld().getNearbyEntities(l,1, 1, 1)) {
													if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
														LivingEntity le = (LivingEntity)e;
														les.add(le);	
													}
												}
							                }
							            }, j.incrementAndGet()/2000); 
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
													enar.setDamage(2*player_damage.get(p.getName())*0.08*(1+fsd.Breath.get(p.getUniqueId())*0.1));								
												}
												p.setSprinting(true);
												le.damage(0,p);
												le.damage(2*player_damage.get(p.getName())*0.08*(1+fsd.Breath.get(p.getUniqueId())*0.1),p);	
												p.setSprinting(false);				                    	
						                    }
						                }
						            }, j.incrementAndGet()/2000); 
				                }
				            }, i*5); 	                    	
	                    }
		                eccooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
				}
		}
		}
	
	@EventHandler
	public void Fireball(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !p.isOnGround() && !p.isSneaking()) 
		{
			Action ac = ev.getAction();
			int sec = 1;
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 12) {
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					
					if(jmcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (jmcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Fireball").create());
		                }
		                else // if timer is done
		                {
		                    jmcooldown.remove(p.getName()); // removing player from HashMap
		                    Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
		                    fb.setYield(0);
		                    fb.setBounce(false);
		                    fb.setShooter(p);
		                    fb.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
		                    fb.setIsIncendiary(false);
							fb.setMetadata("fb of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    
							jmcooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
	                    fb.setYield(0);
	                    fb.setBounce(false);
	                    fb.setShooter(p);
	                    fb.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
	                    fb.setIsIncendiary(false);
						fb.setMetadata("fb of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						jmcooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
				}
		}
	}
	
	@EventHandler
	public void Fireball(EntityExplodeEvent ev) 
	{
        
		
		if(ev.getEntity() instanceof Fireball) {
			Fireball fb = (Fireball)ev.getEntity();
			if(fb.getShooter() instanceof Player) {
				Player p = (Player) fb.getShooter();
				if(fb.hasMetadata("fb of"+p.getName())) {
					ev.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, ev.getLocation(), 2);
					p.playSound(ev.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					for(Entity n : ev.getLocation().getWorld().getNearbyEntities(ev.getLocation(), 4, 4, 4)) {
						if(n!=p && n instanceof LivingEntity&& !(n.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)n;
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
								enar.setDamage(3*player_damage.get(p.getName())*0.03*(1+fsd.Fireball.get(p.getUniqueId())*0.035));								
							}
							p.setSprinting(true);
							le.damage(0,p);
							le.damage(3*player_damage.get(p.getName())*0.03*(1+fsd.Fireball.get(p.getUniqueId())*0.035),p);	
							p.setSprinting(false);			
						}
					}
					ev.setCancelled(true);
				}
			}
			
		}
	}

	@EventHandler
	public void Spurt(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && !p.isSneaking()) 
		{
			Action ac = ev.getAction();
			int sec = 6;
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 12) {
				final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation();
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					
					if(thcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (thcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Spurt").create());
		                }
		                else // if timer is done
		                {
		                    thcooldown.remove(p.getName()); // removing player from HashMap
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    p.playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
		                    p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
		                    AtomicInteger j = new AtomicInteger(0);
		                    for(double d = 0.1; d <= 2.5; d += 0.2) {
			                    for(double an = 0; an <= Math.PI*2; an += Math.PI/90) {
				                    Location pl = tl.clone();
									pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(d));
									line.add(pl);
			                    }
		                    }
		                    line.forEach(l ->  {	
	             				p.getWorld().spawnParticle(Particle.LANDING_LAVA, l, 1, 0.1,0.1,0.1,0);
		                    });
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    		@Override
			                	public void run() 
				                {	
	    		                    line.forEach(l ->  {	
			             				p.getWorld().spawnParticle(Particle.LAVA, l, 1, 1,1,1,5);
	    		                    });
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
										for(Entity e : p.getWorld().getNearbyEntities(tl,2.5, +6, 2.5)) {
											if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
												LivingEntity le = (LivingEntity)e;
					                    		if (le==p && p.getLocation().add(0, 0.5, 0).getBlock().isPassable()) 
												{
							                    	p.teleport(p.getLocation().clone().add(0, 0.5, 0));	
							                    	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40,1,false,false));
							                    	continue;
												}
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
				    								enar.setDamage(0.6*player_damage.get(p.getName())*0.032*(1+fsd.FlowingLava.get(p.getUniqueId())*0.04));								
				    							}
				             					p.setSprinting(true);
												le.damage(0,p);
												le.damage(0.6*player_damage.get(p.getName())*0.032*(1+fsd.FlowingLava.get(p.getUniqueId())*0.04),p);		
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
		            	ArrayList<Location> line = new ArrayList<Location>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    p.playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
	                    p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
	                    AtomicInteger j = new AtomicInteger(0);
	                    for(double d = 0.1; d <= 2.5; d += 0.2) {
		                    for(double an = 0; an <= Math.PI*2; an += Math.PI/90) {
			                    Location pl = tl.clone();
								pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(d));
								line.add(pl);
		                    }
	                    }
	                    line.forEach(l ->  {	
             				p.getWorld().spawnParticle(Particle.LANDING_LAVA, l, 1, 0.1,0.1,0.1,0);
	                    });
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                		@Override
		                	public void run() 
			                {	
    		                    line.forEach(l ->  {	
		             				p.getWorld().spawnParticle(Particle.LAVA, l, 1, 1,1,1,5);
    		                    });
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
									for(Entity e : p.getWorld().getNearbyEntities(tl,2.5, +6, 2.5)) {
										if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
											LivingEntity le = (LivingEntity)e;
				                    		if (le==p && p.getLocation().add(0, 0.5, 0).getBlock().isPassable()) 
											{
						                    	p.teleport(p.getLocation().clone().add(0, 0.5, 0));	
						                    	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40,1,false,false));
						                    	continue;
											}
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
			    								enar.setDamage(0.6*player_damage.get(p.getName())*0.032*(1+fsd.FlowingLava.get(p.getUniqueId())*0.04));								
			    							}
			             					p.setSprinting(true);
											le.damage(0,p);
											le.damage(0.6*player_damage.get(p.getName())*0.032*(1+fsd.FlowingLava.get(p.getUniqueId())*0.04),p);		
			             					p.setSprinting(false);
										}
									}
				                }
				            }, i*2);
	                    }
						thcooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
				}
		}
	}
	
	
	@EventHandler
	public void Spread(EntityDamageByEntityEvent d) 
	{
		int sec = 4;
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&& !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		if((p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD) && (p.isSneaking()) && !(p.isSprinting()))
		{
		LivingEntity le = (LivingEntity)d.getEntity();
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 12) {			
			
					if(pncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (pncooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Spread").create());
				        }
			            else // if timer is done
			            {
			                pncooldown.remove(p.getName()); // removing player from HashMap
			                ItemStack ifire = new ItemStack(Material.FIRE_CHARGE, 3);
							p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
							Item fire = p.getWorld().dropItemNaturally(le.getLocation(), ifire);
							fire.setPickupDelay(9999);
							fire.setInvulnerable(true);
							fire.setThrower(p.getUniqueId());
							fire.setMetadata("af of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							fire.setFireTicks(1000);
							fire.setGlowing(true);
		                    AtomicInteger j = new AtomicInteger();
		                    AtomicInteger k = new AtomicInteger();

							for(Entity e : fire.getNearbyEntities(8, 4, 8)) {
				        		if (e instanceof Player) 
								{
									PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
									Player p1 = (Player) e;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
										return;
										}
									}
								}
								if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											LivingEntity le = (LivingEntity)e;
											fire.teleport(le);
											p.playSound(fire.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
											p.getWorld().spawnParticle(Particle.FLAME, fire.getLocation(), 10, 1,1,1,0);
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
												enar.setDamage(5.5*player_damage.get(p.getName())*0.05*(1+fsd.Spread.get(p.getUniqueId())*0.08));								
											}
											p.setSprinting(true);
											le.damage(0,p);
											le.damage(5.5*player_damage.get(p.getName())*0.05*(1+fsd.Spread.get(p.getUniqueId())*0.08),p);	
											p.setSprinting(false);	
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
				            pncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
			        	ItemStack ifire = new ItemStack(Material.FIRE_CHARGE, 3);
						p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
						Item fire = p.getWorld().dropItemNaturally(le.getLocation(), ifire);
						fire.setPickupDelay(9999);
						fire.setInvulnerable(true);
						fire.setThrower(p.getUniqueId());
						fire.setMetadata("af of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						fire.setFireTicks(1000);
						fire.setGlowing(true);
	                    AtomicInteger j = new AtomicInteger();
	                    AtomicInteger k = new AtomicInteger();

						for(Entity e : fire.getNearbyEntities(8, 4, 8)) {
			        		if (e instanceof Player) 
							{
								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
								Player p1 = (Player) e;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
									return;
									}
								}
							}
							if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										LivingEntity le = (LivingEntity)e;
										fire.teleport(le);
										p.playSound(fire.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
										p.getWorld().spawnParticle(Particle.FLAME, fire.getLocation(), 10, 1,1,1,0);
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
											enar.setDamage(5.5*player_damage.get(p.getName())*0.05*(1+fsd.Spread.get(p.getUniqueId())*0.08));								
										}
										p.setSprinting(true);
										le.damage(0,p);
										le.damage(5.5*player_damage.get(p.getName())*0.05*(1+fsd.Spread.get(p.getUniqueId())*0.08),p);	
										p.setSprinting(false);	
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
			            pncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
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
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 12 && ((is.getType()==Material.STICK)||(is.getType()==Material.BLAZE_ROD)||(is.getType()==Material.BOOK)) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(aultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (aultcooldown.get(p.getName())/1000 + 75) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Phoenix Flap").create());
		            }
	                else // if timer is done
	                {
	                    aultcooldown.remove(p.getName()); // removing player from HashMap
						p.setSneaking(false);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_AMBIENT, 1.0f, 0.0f);
						p.setInvulnerable(true);
						ArrayList<Location> flap = new ArrayList<Location>();
	                    AtomicInteger j = new AtomicInteger();	
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    for(double angle= -Math.PI/2; angle<Math.PI/2; angle += Math.PI/90) {
	                    	Location one = p.getLocation();
	                    	one.setDirection(one.getDirection().rotateAroundY(angle));
	                    	for(double dou = 0.1; dou <15.1; dou+=0.2) {
		                    	Location two = one.clone();
	                    		two.add(two.getDirection().normalize().multiply(dou));	    
		                    	flap.add(two);                		
	                    	}
	                    }
						for(int i = 0; i <5; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 1.0f, 0f);
				                    p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 1f);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
				                    flap.forEach(l ->{
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												p.getWorld().spawnParticle(Particle.FLAME, l, 2, 1,1,1,0.1);
													for(Entity e : p.getWorld().getNearbyEntities(l,2, 2, 2)) {
														if(p!=e && e instanceof LivingEntity) {
															LivingEntity le = (LivingEntity)e;
															les.add(le);	
														}
													}
							                    
							                }
							            }, j.incrementAndGet()/6750);  
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
													enar.setDamage(18*player_damage.get(p.getName())*0.03);								
												}
												p.setSprinting(true);
												le.damage(0,p);
												le.damage(18*player_damage.get(p.getName())*0.03,p);	
												p.setSprinting(false);					                    	
						                    }
						                    
						                }
						            }, j.incrementAndGet()/6750);  
				                }
				            }, i*20+15); 	                    	
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								p.setInvulnerable(false);
			                }
			            }, 100); 
		                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
					p.setSneaking(false);
                    p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_AMBIENT, 1.0f, 0.0f);
					p.setInvulnerable(true);
					ArrayList<Location> flap = new ArrayList<Location>();
                    AtomicInteger j = new AtomicInteger();	
                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(double angle= -Math.PI/2; angle<Math.PI/2; angle += Math.PI/90) {
                    	Location one = p.getLocation();
                    	one.setDirection(one.getDirection().rotateAroundY(angle));
                    	for(double dou = 0.1; dou <15.1; dou+=0.2) {
	                    	Location two = one.clone();
                    		two.add(two.getDirection().normalize().multiply(dou));	    
	                    	flap.add(two);                		
                    	}
                    }
					for(int i = 0; i <5; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 1.0f, 0f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 1f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
			                    flap.forEach(l ->{
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											p.getWorld().spawnParticle(Particle.FLAME, l, 2, 1,1,1,0.1);
												for(Entity e : p.getWorld().getNearbyEntities(l,2, 2, 2)) {
													if(p!=e && e instanceof LivingEntity) {
														LivingEntity le = (LivingEntity)e;
														les.add(le);	
													}
												}
						                    
						                }
						            }, j.incrementAndGet()/6750);  
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
												enar.setDamage(18*player_damage.get(p.getName())*0.03);								
											}
											p.setSprinting(true);
											le.damage(0,p);
											le.damage(18*player_damage.get(p.getName())*0.03,p);	
											p.setSprinting(false);					                    	
					                    }
					                    
					                }
					            }, j.incrementAndGet()/6750);  
			                }
			            }, i*20+15); 	                    	
                    }
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							p.setInvulnerable(false);
		                }
		            }, 100); 
	                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}	
			
			
    }

	@EventHandler
	public void FireStrike(EntityDamageByEntityEvent d) 
	{
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		if(p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
		{
			LivingEntity le = (LivingEntity)d.getEntity();
	        
	
			
			
			if(playerclass.get(p.getUniqueId()) == 12) {
            		if (le instanceof Player) 
					{
						PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
						Player p1 = (Player) le;
						if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
						if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
							{
							d.setCancelled(true);
							return;
							}
						}
					}
					if(p==le) {d.setCancelled(true);}
					p.getWorld().spawnParticle(Particle.FLAME, le.getLocation(), 5, 1,1,1,0);
					if(!d.isCancelled()) {
						le.setFireTicks(50);}
					
				}
		}
		}
	}
	@EventHandler
	public void HotBody(EntityDamageByEntityEvent d) 
	{		
	    
		if(d.getEntity() instanceof Player && d.getDamager() instanceof LivingEntity) 
		{
		Player p = (Player)d.getEntity();
		LivingEntity le = (LivingEntity)d.getDamager();
		
		
			if(playerclass.get(p.getUniqueId()) == 12) 
			{	
				if(!d.isCancelled()) {
					if(le instanceof Player) {
						p.setSprinting(true);
						le.damage(d.getDamage()*0.01,p);
						p.setSprinting(false);
					}
					else {
						p.setSprinting(true);
						le.damage(d.getDamage()*0.05,p);
						p.setSprinting(false);						
					}
				}
			}	
		}	
		if(d.getEntity() instanceof Fireball && d.getDamager() instanceof LivingEntity) 
		{
			Fireball fb = (Fireball)d.getEntity();
			LivingEntity le = (LivingEntity)d.getDamager();
			if(fb.getShooter() instanceof Player) {
				Player p = (Player)fb.getShooter();
				if(fb.hasMetadata("fb of"+ p.getName())) {
					
					
					if(playerclass.get(p.getUniqueId()) == 12) 
					{	
						d.setCancelled(true);
					}	
					
				}
			}
		}	
		if(d.getEntity() instanceof Player && d.getDamager() instanceof Projectile) 
		{
		Player p = (Player)d.getEntity();
		Projectile pr = (Projectile)d.getDamager();
		if(pr.getShooter() instanceof LivingEntity) {
			LivingEntity le = (LivingEntity) pr.getShooter();
			
			
				if(playerclass.get(p.getUniqueId()) == 12) 
				{	
					if(!d.isCancelled()) {
						if(le instanceof Player) {
							p.setSprinting(true);
							le.damage(d.getDamage()*0.01,p);
							p.setSprinting(false);
						}
						else {
							p.setSprinting(true);
							le.damage(d.getDamage()*0.05,p);
							p.setSprinting(false);						
						}
					}
				}	
			}	
			
		}
	}

	@EventHandler
	public void HotBody(EntityCombustEvent d) 
	{		
	    
		if(d.getEntity() instanceof Player) 
		{
		Player p = (Player)d.getEntity();
		
		
			if(playerclass.get(p.getUniqueId()) == 12) 
			{
				d.setCancelled(true);
				p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
			}	
		}	
	}
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

		if((p.getInventory().getItemInMainHand().getType()==Material.STICK || p.getInventory().getItemInMainHand().getType()==Material.BOOK || p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD))
		{
	    
		
		
			if(playerclass.get(p.getUniqueId()) == 12)
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
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 12) {
				if(p.getInventory().getItemInMainHand().getType()==Material.STICK)
				{
						player_damage.put(p.getName(), fsd.HotBody.get(p.getUniqueId()) + 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.BOOK)
				{
						player_damage.put(p.getName(), fsd.HotBody.get(p.getUniqueId()) + 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
				{
						player_damage.put(p.getName(), fsd.HotBody.get(p.getUniqueId()) + 10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
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
        

		
		
		
		if(playerclass.get(p.getUniqueId()) == 12) {
			if(p.getInventory().getItemInMainHand().getType()==Material.STICK)
			{
					player_damage.put(p.getName(), fsd.HotBody.get(p.getUniqueId())*6.5 + 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.BOOK)
			{
					player_damage.put(p.getName(), fsd.HotBody.get(p.getUniqueId())*6.5+ 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
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
			if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
			{
					player_damage.put(p.getName(), fsd.HotBody.get(p.getUniqueId())*6.5+ 10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
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
			    
				
				
				
				if(playerclass.get(p.getUniqueId()) == 12) {
					if(p.getInventory().getItemInMainHand().getType()==Material.STICK)
					{
							player_damage.put(p.getName(), fsd.HotBody.get(p.getUniqueId())*6.5+ 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.BOOK)
					{
							player_damage.put(p.getName(), fsd.HotBody.get(p.getUniqueId())*6.5+ 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
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
					if(p.getInventory().getItemInMainHand().getType()==Material.BLAZE_ROD)
					{
							player_damage.put(p.getName(), fsd.HotBody.get(p.getUniqueId())*6.5+ 10 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
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
}



