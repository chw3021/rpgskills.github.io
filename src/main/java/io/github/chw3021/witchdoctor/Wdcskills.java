package io.github.chw3021.witchdoctor;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Event;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.party.PartyData;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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
import java.util.IdentityHashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Vex;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Wdcskills implements Serializable, Listener {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 2112330324524706135L;
	private HashMap<String, Long> rscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> prcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> jmcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> thcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> pncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> eccooldown = new HashMap<String, Long>();
	private HashMap<String, Long> lncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> aultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<Player, Integer> Bosou = new HashMap<Player, Integer>();
	private HashMap<LivingEntity, Integer> Hex = new HashMap<LivingEntity, Integer>();
	static private HashMap<LivingEntity, Player> chickdam = new HashMap<LivingEntity, Player>();
	static private HashMap<LivingEntity, LivingEntity> chickenget = new HashMap<LivingEntity, LivingEntity>();
	public HashMap<Player, Integer> Baron = new HashMap<Player, Integer>();
	public HashMap<Player, Integer> res = new HashMap<Player, Integer>();
	Holding hold = Holding.getInstance();
	

	private static final Wdcskills instance = new Wdcskills ();
	public static Wdcskills getInstance(){
		return instance;		
	}
	
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private WdcSkillsData wsd = new WdcSkillsData(WdcSkillsData.loadData(path +"/plugins/RPGskills/WdcSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		WdcSkillsData w = new WdcSkillsData(WdcSkillsData.loadData(path +"/plugins/RPGskills/WdcSkillsData.data"));
		wsd = w;
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Wdcskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				WdcSkillsData w = new WdcSkillsData(WdcSkillsData.loadData(path +"/plugins/RPGskills/WdcSkillsData.data"));
				wsd = w;
			}
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		WdcSkillsData w = new WdcSkillsData(WdcSkillsData.loadData(path +"/plugins/RPGskills/WdcSkillsData.data"));
		wsd = w;
		
	}
	
	@EventHandler
	public void Fangs(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 1;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 14) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.getInventory().getItemInOffHand().getType().name().contains("TOTEM") && p.isSneaking())
			{
				
				ev.setCancelled(true);
				if(rscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (rscooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Fangs").create());
	                }
	                else // if timer is done
	                {
	                    rscooldown.remove(p.getName()); // removing player from HashMap	
						p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_FANGS_ATTACK, 1, 0);
	                    for(Entity e : p.getNearbyEntities(5, 5, 5)) {
	                    	if(e instanceof LivingEntity && !(e.hasMetadata("fake"))) {
	                    		LivingEntity le = (LivingEntity) e;
	                    		if (le instanceof Player) 
	                			{
	                				PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
	                				Player p1 = (Player) le;
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
		                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	EvokerFangs ef = (EvokerFangs)p.getWorld().spawnEntity(le.getLocation(), EntityType.EVOKER_FANGS);
					                    ef.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					                	ef.setOwner(p);
					                }
					            }, 1); 
	                    	}
	                	} 	                    		                    
		                rscooldown.put(p.getName(), System.currentTimeMillis());
	                }
	                    
	            }
	            else // if cooldown doesn't have players name in it
	            {
					p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_FANGS_ATTACK, 1, 0);
                    for(Entity e : p.getNearbyEntities(5, 5, 5)) {
                    	if(e instanceof LivingEntity) {
                    		LivingEntity le = (LivingEntity) e;
                    		if (le instanceof Player && !(e.hasMetadata("fake"))) 
                			{
                				PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
                				Player p1 = (Player) le;
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
	                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	EvokerFangs ef = (EvokerFangs)p.getWorld().spawnEntity(le.getLocation(), EntityType.EVOKER_FANGS);
				                    ef.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				                	ef.setOwner(p);
				                }
				            }, 1); 
                    	}
                	} 	
	                rscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
		}
	

	@EventHandler
	public void Bosou(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 9;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 14) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("TOTEM") && p.getInventory().getItemInOffHand().getType().name().contains("HOE") && p.isSneaking())
			{
				
				ev.setCancelled(true);
				{
					if(prcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (prcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Bosou").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    prcooldown.remove(p.getName()); // removing player from HashMap
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
		                    p.playSound(p.getLocation(), Sound.ENTITY_HOGLIN_AMBIENT, 1.0f, 0f);
			        		p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 400, 4, 2, 4);
			        		p.getWorld().spawnParticle(Particle.ASH, l, 400, 4, 2, 4);
			        		Hoglin h = (Hoglin)p.getWorld().spawnEntity(l, EntityType.HOGLIN);
			        		h.setAdult();
			        		h.setAgeLock(true);
			        		h.setImmuneToZombification(true);
			        		h.setAI(false);
			        		h.setInvulnerable(true);
		                    h.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							for(int i = 0; i <20; i++) {
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
							        		p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 100, 4, 2, 4);
											for (Entity e : h.getNearbyEntities(5, 5, 5))
											{
												if (e instanceof Player) 
												{
													PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
													Player p1 = (Player) e;
													if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
													if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
														{
										        			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p1.getLocation(), 40, 4, 2, 4);
															if(Bosou.containsKey(p1)) {
																Bosou.computeIfPresent(p1, (k,v) -> v+1);
															}
															else {
																Bosou.put(p1, 0);
															}
															Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
												                @Override
												                public void run() 
												                {
												                	Bosou.computeIfPresent(p1, (k, v) -> v-1);
												                	if(Bosou.get(p1)<0) {
																	Bosou.remove(p1);
												                	}
												                }
												    	   }, 5);
														}
													}
								        			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p1.getLocation(), 40, 4, 2, 4);
													if(Bosou.containsKey(p)) {
														Bosou.computeIfPresent(p, (k,v) -> v+1);
													}
													else {
														Bosou.put(p, 0);
													}
													Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() 
										                {
										                	Bosou.computeIfPresent(p, (k, v) -> v-1);
										                	if(Bosou.get(p)<0) {
															Bosou.remove(p);
										                	}
										                }
													}, 5);
												}
											}
						                }
						            }, i*5); 
		                	}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	h.remove();
				                }
				    	   }, 101);
							prcooldown.put(p.getName(), System.currentTimeMillis());
						}
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
	                    p.playSound(p.getLocation(), Sound.ENTITY_HOGLIN_AMBIENT, 1.0f, 0f);
		        		p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 400, 4, 2, 4);
		        		p.getWorld().spawnParticle(Particle.ASH, l, 400, 4, 2, 4);
		        		Hoglin h = (Hoglin)p.getWorld().spawnEntity(l, EntityType.HOGLIN);
		        		h.setAdult();
		        		h.setAgeLock(true);
		        		h.setImmuneToZombification(true);
		        		h.setAI(false);
		        		h.setInvulnerable(true);
	                    h.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						for(int i = 0; i <20; i++) {
		                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						        		p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 100, 4, 2, 4);
										for (Entity e : h.getNearbyEntities(5, 5, 5))
										{
											if (e instanceof Player) 
											{
												PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
												Player p1 = (Player) e;
												if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
												if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
													{
									        			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p1.getLocation(), 40, 4, 2, 4);
														if(Bosou.containsKey(p1)) {
															Bosou.computeIfPresent(p1, (k,v) -> v+1);
														}
														else {
															Bosou.put(p1, 0);
														}
														Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											                @Override
											                public void run() 
											                {
											                	Bosou.computeIfPresent(p1, (k, v) -> v-1);
											                	if(Bosou.get(p1)<0) {
																Bosou.remove(p1);
											                	}
											                }
											    	   }, 5);
													}
												}
							        			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p1.getLocation(), 40, 4, 2, 4);
												if(Bosou.containsKey(p)) {
													Bosou.computeIfPresent(p, (k,v) -> v+1);
												}
												else {
													Bosou.put(p, 0);
												}
												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() 
									                {
									                	Bosou.computeIfPresent(p, (k, v) -> v-1);
									                	if(Bosou.get(p)<0) {
														Bosou.remove(p);
									                	}
									                }
												}, 5);
											}
										}
					                }
					            }, i*5); 
	                	}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	h.remove();
			                }
			    	   }, 101);
						prcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					}
				}}
		}
		}
	
	@EventHandler
	public void Harvest(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 3;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 14) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.getInventory().getItemInOffHand().getType().name().contains("TOTEM")  &&!p.isSneaking()) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					
					if(jmcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (jmcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Harvest").create());
		                }
		                else // if timer is done
		                {
		                    jmcooldown.remove(p.getName()); // removing player from HashMap
                    		ArrayList<Location> souls = new ArrayList<Location>();
                    		ArrayList<Location> soule = new ArrayList<Location>();
                    		AtomicInteger ai = new AtomicInteger();
		                	p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1f, 2);
		                	p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1f, 0);
		                	p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 10,2,2,2,0);
		                    for(Entity e: p.getNearbyEntities(5, 5, 5)) {
		                    	if(e instanceof LivingEntity && !(e.hasMetadata("fake"))) {
		                    		LivingEntity le = (LivingEntity)e;
		                    		if (le instanceof Player) 
		                			{
		                				PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
		                				Player p1 = (Player) le;
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
									p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 1, false, false));
		                    		souls.add(le.getLocation());
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
										enar.setDamage(player_damage.get(p.getName())*0.43+wsd.Harvest.get(p.getUniqueId())*0.3);								
									}
		                    		le.damage(0, p);
		                    		le.damage(player_damage.get(p.getName())*0.43+wsd.Harvest.get(p.getUniqueId())*0.3, p);
		                    		if(Hex.containsKey(le)) {
		                    			Hex.computeIfPresent(le, (k,v) -> v+1);
									}
									else {
										Hex.put(le, 0);
									}
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	Hex.computeIfPresent(le, (k, v) -> v-1);
						                	if(Hex.get(le)<0) {
						                		Hex.remove(le);
						                	}
						                }
									}, 100);
		                    	}
		                    }
		                    souls.forEach(s -> {
		                    	Vector spv = p.getLocation().toVector().subtract(s.toVector());
		                    	Double spd = p.getLocation().distance(s);
		                    	for(double di = 0.01; di < spd; di += 0.05) {
		                    		Location sc = s.clone();
		                    		sc.add(spv.normalize().multiply(di));
		                    		soule.add(sc);
		                    	}
		                    	
		                    });
		                    soule.forEach(s -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	s.getWorld().spawnParticle(Particle.SOUL, s, 2,0.2,0.2,0.2,0);
					                	s.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, s, 2,0.2,0.2,0.2,0);
					                }
					    	   }, ai.incrementAndGet()/15);	
		                    	
		                    });
							jmcooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {                
		            	ArrayList<Location> souls = new ArrayList<Location>();
                		ArrayList<Location> soule = new ArrayList<Location>();
                		AtomicInteger ai = new AtomicInteger();
	                	p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1f, 2);
	                	p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1f, 0);
	                	p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 10,2,2,2,0);
	                    for(Entity e: p.getNearbyEntities(5, 5, 5)) {
	                    	if(e instanceof LivingEntity && !(e.hasMetadata("fake"))) {
	                    		LivingEntity le = (LivingEntity)e;
	                    		if (le instanceof Player) 
	                			{
	                				PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
	                				Player p1 = (Player) le;
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
	    						p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, false, false));
	    						p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 1, false, false));
	                    		souls.add(le.getLocation());
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
									enar.setDamage(player_damage.get(p.getName())*0.43+wsd.Harvest.get(p.getUniqueId())*0.3);								
								}
	                    		le.damage(0, p);
	                    		le.damage(player_damage.get(p.getName())*0.43+wsd.Harvest.get(p.getUniqueId())*0.3, p);
	                    		if(Hex.containsKey(le)) {
	                    			Hex.computeIfPresent(le, (k,v) -> v+1);
								}
								else {
									Hex.put(le, 0);
								}
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	Hex.computeIfPresent(le, (k, v) -> v-1);
					                	if(Hex.get(le)<0) {
					                		Hex.remove(le);
					                	}
					                }
								}, 100);
	                    	}
	                    }
	                    souls.forEach(s -> {
	                    	Vector spv = p.getLocation().toVector().subtract(s.toVector());
	                    	Double spd = p.getLocation().distance(s);
	                    	for(double di = 0.01; di < spd; di += 0.05) {
	                    		Location sc = s.clone();
	                    		sc.add(spv.normalize().multiply(di));
	                    		soule.add(sc);
	                    	}
	                    	
	                    });
	                    soule.forEach(s -> {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	s.getWorld().spawnParticle(Particle.SOUL, s, 2,0.2,0.2,0.2,0);
				                	s.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, s, 2,0.2,0.2,0.2,0);
				                }
				    	   }, ai.incrementAndGet()/15);	
	                    	
	                    });
						jmcooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
				}
		}
	}
	
	@EventHandler
	public void Wraith(PlayerInteractEvent ev) 
	{		
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 5;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 14) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") && p.getInventory().getItemInOffHand().getType().name().contains("TOTEM")  && p.isSneaking()) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					
					if(thcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (thcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Wraith").create());
		                }
		                else // if timer is done
		                {
		                    thcooldown.remove(p.getName()); // removing player from HashMap
		                    Vex v = (Vex)p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.VEX);
			        		v.setCharging(true);
			        		v.setAI(false);
			        		v.setInvulnerable(true);
		                    v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			        		for(int i =0; i<20; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
						        		v.teleport(p.getLocation().add(p.getLocation().getDirection().normalize().multiply(0.3)));
					                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 20,1,1,1);
					                	p.playSound(v.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.5f, 2);
					                	for(Entity e: v.getNearbyEntities(3, 3, 3)) {
					                		if(e instanceof LivingEntity && p!=e && !(e.hasMetadata("fake"))) {
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
													enar.setDamage(player_damage.get(p.getName())*0.11+wsd.Wraith.get(p.getUniqueId())*0.068);								
												}
					                			le.damage(0, p);
					                			le.damage(player_damage.get(p.getName())*0.11+wsd.Wraith.get(p.getUniqueId())*0.068, p);
					                		}
					                	}
					                }
					    	   }, i*3);				        			
			        		}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 50,1,1,1);
				                	v.getWorld().spawnParticle(Particle.SOUL, v.getLocation(), 50,1,1,1);
				                	p.playSound(v.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1, 0);
				                	v.remove();
				                }
							}, 61);	
							thcooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    Vex v = (Vex)p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.VEX);
		        		v.setCharging(true);
		        		v.setAI(false);
		        		v.setInvulnerable(true);
	                    v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		        		for(int i =0; i<20; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					        		v.teleport(p.getLocation().add(p.getLocation().getDirection().normalize().multiply(0.3)));
				                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 20,1,1,1);
				                	p.playSound(v.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.5f, 2);
				                	for(Entity e: v.getNearbyEntities(3, 3, 3)) {
				                		if(e instanceof LivingEntity && p!=e && !(e.hasMetadata("fake"))) {
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
												enar.setDamage(player_damage.get(p.getName())*0.11+wsd.Wraith.get(p.getUniqueId())*0.068);								
											}
				                			le.damage(0, p);
				                			le.damage(player_damage.get(p.getName())*0.11+wsd.Wraith.get(p.getUniqueId())*0.068, p);
				                		}
				                	}
				                }
				    	   }, i*3);				        			
		        		}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 50,1,1,1);
			                	v.getWorld().spawnParticle(Particle.SOUL, v.getLocation(), 50,1,1,1);
			                	p.playSound(v.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1, 0);
			                	v.remove();
			                }
						}, 31);	
						thcooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
				}
		}
	}
	
	@EventHandler
	public void AstralProjection(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		GameMode gm = p.getGameMode();
		Action ac = ev.getAction();
		int sec = 8;
        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 14 && wsd.AstralProjection.get(p.getUniqueId())>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("TOTEM") && p.getInventory().getItemInOffHand().getType().name().contains("HOE") && !p.isSneaking()) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					if(pncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (pncooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use AstralProjection").create());
		                }
		                else // if timer is done
		                {
		                    pncooldown.remove(p.getName()); // removing player from HashMap
		                    Enderman v = (Enderman)p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.ENDERMAN);
		                	p.playSound(v.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1, 2);
			        		v.setInvisible(true);
			        		v.setAI(false);
			        		v.setInvulnerable(true);
			        		v.setSilent(true);
		                    v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			        		p.setGameMode(GameMode.SPECTATOR);
			        		p.setSpectatorTarget(v);
			        		for(int i =0; i<25; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
						        		p.setSpectatorTarget(v);
					                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 10,1,1,1,0);
					                	p.playSound(v.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.45f, 0);
					                	if(p.getLocation().add(p.getLocation().getDirection().normalize().multiply(0.6)).getBlock().isPassable()) {
					                	v.teleport(p.getLocation().add(p.getLocation().getDirection().normalize().multiply(0.6)));
					                	}
					                }
					    	   }, i*2);				        			
			        		}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					        		p.setSpectatorTarget(null);
				                	p.setGameMode(gm);
				                	p.teleport(v);
				                	p.playSound(v.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1f, 0);
									p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 1, false, false));
				                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 50,1,1,1);
				                	v.remove();
				                }
							}, 51);	
		                    
							pncooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    Enderman v = (Enderman)p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.ENDERMAN);
	                	p.playSound(v.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1, 2);
		        		v.setInvisible(true);
		        		v.setAI(false);
		        		v.setInvulnerable(true);
		        		v.setSilent(true);
	                    v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		        		p.setGameMode(GameMode.SPECTATOR);
		        		p.setSpectatorTarget(v);
		        		for(int i =0; i<25; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					        		p.setSpectatorTarget(v);
				                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 10,1,1,1,0);
				                	p.playSound(v.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.45f, 0);
				                	if(p.getLocation().add(p.getLocation().getDirection().normalize().multiply(0.6)).getBlock().isPassable()) {
				                	v.teleport(p.getLocation().add(p.getLocation().getDirection().normalize().multiply(0.6)));
				                	}
				                }
				    	   }, i*2);				        			
		        		}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
				        		p.setSpectatorTarget(null);
			                	p.setGameMode(gm);
			                	p.teleport(v);
			                	p.playSound(v.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1f, 0);
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 1, false, false));
			                	v.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, v.getLocation(), 50,1,1,1);
			                	v.remove();
			                }
						}, 51);	
						pncooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
				}
		}
	}
	
	@EventHandler
	public void Incantation(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 13;
        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 14 && wsd.Incantation.get(p.getUniqueId())>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("TOTEM") && p.getInventory().getItemInOffHand().getType().name().contains("HOE")  &&  p.isSneaking()) 
			{
				if((ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					if(eccooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (eccooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Incantation").create());
		                }
		                else // if timer is done
		                {
		                    eccooldown.remove(p.getName()); // removing player from HashMap
			            	ArmorStand v = (ArmorStand)p.getWorld().spawnEntity(p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation(), EntityType.ARMOR_STAND);
		                	p.playSound(v.getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 1, 0);
			        		ItemStack head = new ItemStack(Material.SKELETON_SKULL);
			        		v.setHelmet(head);
			        		v.setInvulnerable(true);
			        		v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
			        		for(Entity e: v.getNearbyEntities(5, 5, 5)) {
			        			if(e instanceof LivingEntity && e!=p && !e.hasMetadata("chickened") && !e.hasMetadata("untargetable") && !e.hasMetadata("fake") &&!(e instanceof Player) && e!=v&& !e.isDead()) {
			        				LivingEntity le = (LivingEntity)e;
		                    		if (le instanceof Player) 
		                			{
		                				PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
		                				Player p1 = (Player) le;
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
				                    Chicken ch = (Chicken)p.getWorld().spawnEntity(le.getLocation(), EntityType.CHICKEN);
				                    if(Event.damaged.containsKey(le.getUniqueId())){
					                    ch.setCustomName(Event.damaged.get(le.getUniqueId()));ch.setCustomNameVisible(false);
				                    }
				                    else{
					                    ch.setCustomName(le.getName());ch.setCustomNameVisible(false);
				                    }
				                    ch.setAI(false);
				                    ch.setAdult();
				                    ch.setMaxHealth(le.getHealth());
				                    ch.setHealth(le.getHealth());
				                    ch.setMetadata("chickened", new FixedMetadataValue(RMain.getInstance(), true));
				                    ch.setMetadata("chickened"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				                    for(PotionEffect potioneffect: le.getActivePotionEffects()) {
				                    	ch.addPotionEffect(potioneffect);
				                    }
			        				chickdam.put(le, p);	
			        				le.setRemoveWhenFarAway(false);
			        				le.teleport(le.getLocation().add(0, 100, 0));
			        				chickenget.put(ch, le);
			        				hold.holding(p, le, 60l);
			        				le.setInvisible(true);
			        				le.setInvulnerable(true);
			        			}
			        		}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
					        		for(Entity e: p.getWorld().getEntities()) {
					        			if(e.hasMetadata("chickened"+p.getName()) && e instanceof LivingEntity) {
					        				LivingEntity ch = (LivingEntity)e;
					        				LivingEntity le = chickenget.get(ch);
					        				le.setInvisible(false);
					        				le.setInvulnerable(false);
					        				le.teleport(le.getLocation().add(0, -100, 0));
					        				le.setFallDistance(0);
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
												enar.setDamage(ch.getMaxHealth()-ch.getHealth());								
											}
					        				le.damage(ch.getMaxHealth()-ch.getHealth(), p);
						                    for(PotionEffect potioneffect: ch.getActivePotionEffects()) {
						                    	le.addPotionEffect(potioneffect);
						                    }
						                    ch.remove();
					        			}
					        		}
					        		v.remove();
				                }
							}, 60);	
							eccooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	ArmorStand v = (ArmorStand)p.getWorld().spawnEntity(p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation(), EntityType.ARMOR_STAND);
	                	p.playSound(v.getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 1, 0);
		        		ItemStack head = new ItemStack(Material.SKELETON_SKULL);
		        		v.setHelmet(head);
		        		v.setInvulnerable(true);
		        		v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
		        		for(Entity e: v.getNearbyEntities(5, 5, 5)) {
		        			if(e instanceof LivingEntity && e!=p && !e.hasMetadata("chickened") && !e.hasMetadata("untargetable") && !e.hasMetadata("fake") &&!(e instanceof Player) && e!=v && !e.isDead()) {
		        				LivingEntity le = (LivingEntity)e;
	                    		if (le instanceof Player) 
	                			{
	                				PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
	                				Player p1 = (Player) le;
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
			                    Chicken ch = (Chicken)p.getWorld().spawnEntity(le.getLocation(), EntityType.CHICKEN);
			                    if(Event.damaged.containsKey(le.getUniqueId())){
				                    ch.setCustomName(Event.damaged.get(le.getUniqueId()));ch.setCustomNameVisible(false);
			                    }
			                    else{
				                    ch.setCustomName(le.getName());ch.setCustomNameVisible(false);
			                    }
			                    ch.setAI(false);
			                    ch.setAdult();
			                    ch.setMaxHealth(le.getHealth());
			                    ch.setHealth(le.getHealth());
			                    ch.setMetadata("chickened", new FixedMetadataValue(RMain.getInstance(), true));
			                    ch.setMetadata("chickened"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                    for(PotionEffect potioneffect: le.getActivePotionEffects()) {
			                    	ch.addPotionEffect(potioneffect);
			                    }
		        				chickdam.put(le, p);	
		        				le.setRemoveWhenFarAway(false);
		        				le.teleport(le.getLocation().add(0, 100, 0));
		        				chickenget.put(ch, le);
		        				hold.holding(p, le, 60l);
		        				le.setInvisible(true);
		        				le.setInvulnerable(true);
		        			}
		        		}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
				        		for(Entity e: p.getWorld().getEntities()) {
				        			if(e.hasMetadata("chickened"+p.getName()) && e instanceof LivingEntity) {
				        				LivingEntity ch = (LivingEntity)e;
				        				LivingEntity le = chickenget.get(ch);
				        				le.setInvisible(false);
				        				le.setInvulnerable(false);
				        				le.teleport(le.getLocation().add(0, -100, 0));
				        				le.setFallDistance(0);
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
											enar.setDamage(ch.getMaxHealth()-ch.getHealth());								
										}
				        				le.damage(ch.getMaxHealth()-ch.getHealth(), p);
					                    for(PotionEffect potioneffect: ch.getActivePotionEffects()) {
					                    	le.addPotionEffect(potioneffect);
					                    }
					                    ch.remove();
				        			}
				        		}
				        		v.remove();
			                }
						}, 60);	
						eccooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
				}
		}
	}
	
	@EventHandler
	public void Incantation(EntityDeathEvent ev)        
    {
		if(ev.getEntity().hasMetadata("chickened")) {
			LivingEntity ch = ev.getEntity();
			ev.setDroppedExp(0);
			ev.getDrops().clear();
			ch.setInvisible(true);
			LivingEntity le = chickenget.get(ch);
			le.teleport(le.getLocation().add(0, -100, 0));
			le.setFallDistance(0);
			le.setInvulnerable(false);
			le.setInvisible(false);
			if(le.hasMetadata("fake")) {
				le.removeMetadata("fake", RMain.getInstance());
			}
			le.damage(le.getMaxHealth(),chickdam.get(ch));
			ch.remove();
		}
    }

	
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		final Location l = p.getLocation();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 14 && (is.getType().name().contains("HOE") || is.getType().name().contains("TOTEM")) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(aultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (aultcooldown.get(p.getName())/1000 + 80) - System.currentTimeMillis()/1000 - p.getLevel()/10; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Baron Samedi").create());
		            }
	                else // if timer is done
	                {
	                    aultcooldown.remove(p.getName()); // removing player from HashMap
	                    
	                    ItemStack whel = new ItemStack(Material.LEATHER_HELMET);
	                    LeatherArmorMeta whelm = (LeatherArmorMeta) whel.getItemMeta();
	                   	whelm.setColor(Color.BLACK);
	                   	whel.setItemMeta(whelm);
	                    ItemStack wb = new ItemStack(Material.LEATHER_BOOTS);
	                    LeatherArmorMeta wbm = (LeatherArmorMeta) wb.getItemMeta();
	                   	wbm.setColor(Color.WHITE);
	                   	wb.setItemMeta(wbm);
	                    ItemStack wc = new ItemStack(Material.LEATHER_CHESTPLATE);
	                    LeatherArmorMeta wcm = (LeatherArmorMeta) wc.getItemMeta();
	                   	wcm.setColor(Color.fromRGB(80, 0, 68));
	                   	wc.setItemMeta(wcm);
	                    ItemStack wl = new ItemStack(Material.LEATHER_LEGGINGS);
	                    LeatherArmorMeta wlm = (LeatherArmorMeta) wl.getItemMeta();
	                   	wlm.setColor(Color.PURPLE);
	                   	wl.setItemMeta(wlm);
	                    ItemStack wmh = new ItemStack(Material.DEBUG_STICK);
	                    ItemStack wmo = new ItemStack(Material.DRAGON_BREATH);
	                    
	                    Skeleton w = (Skeleton)p.getWorld().spawnEntity(p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().add(0, -2, 0), EntityType.SKELETON);
	                    w.setAI(false);
	                    w.getEquipment().clear();
	                    w.setGlowing(true);
	                    w.setRemoveWhenFarAway(false);
	                    w.setAbsorptionAmount(100);
						w.getEquipment().setHelmet(whel);
						w.getEquipment().setLeggings(wl);
						w.getEquipment().setBoots(wb);
						w.getEquipment().setChestplate(wc);
						w.getEquipment().setItemInOffHand(wmo);
						w.getEquipment().setItemInMainHand(wmh);
						w.getEquipment().setBootsDropChance(0);
						w.getEquipment().setChestplateDropChance(0);
						w.getEquipment().setHelmetDropChance(0);
						w.getEquipment().setItemInMainHandDropChance(0);
						w.getEquipment().setItemInOffHandDropChance(0);
						w.getEquipment().setLeggingsDropChance(0);
						w.setInvulnerable(true);
	                    w.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						p.playSound(l, Sound.AMBIENT_WARPED_FOREST_ADDITIONS, 1, 0);
						p.playSound(l, Sound.ENTITY_SKELETON_AMBIENT, 1, 0);
						p.playSound(l, Sound.ENTITY_VINDICATOR_CELEBRATE, 1, 0);
						for(int dou = 0; dou < 10; dou++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									w.teleport(w.getLocation().add(w.getLocation().getDirection().rotateAroundX(-Math.PI/2).normalize().multiply(0.1)));
									p.playSound(l, Sound.ENTITY_WITHER_SKELETON_STEP, 0.8f, 0);
				                }
							}, dou);
						}
						for(int i = 0; i <40; i++) {
	                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
					        		p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, w.getLocation(), 100, 4, 2, 4);
					        		p.getWorld().spawnParticle(Particle.SOUL, w.getLocation(), 100, 4, 2, 4);
									for (Entity e : w.getNearbyEntities(9, 9, 9))
									{
										if (e instanceof Player) 
										{
											PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
											Player p1 = (Player) e;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
								        			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p1.getLocation(), 40, 4, 2, 4);
													if(Baron.containsKey(p1)) {
														Baron.computeIfPresent(p1, (k,v) -> v+1);
													}
													else {
														Baron.put(p1, 0);
													}
													Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() 
										                {
										                	Baron.computeIfPresent(p1, (k, v) -> v-1);
										                	if(Baron.get(p1)<0) {
															Baron.remove(p1);
										                	}
										                }
										    	   }, 5);
												}
											}
						        			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p1.getLocation(), 40, 4, 2, 4);
											if(Baron.containsKey(p)) {
												Baron.computeIfPresent(p, (k,v) -> v+1);
											}
											else {
												Baron.put(p, 0);
											}
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
								                	Baron.computeIfPresent(p, (k, v) -> v-1);
								                	if(Baron.get(p)<0) {
													Baron.remove(p);
								                	}
								                }
											}, 5);
										}
									}
				                }
				            }, i*5+3); 
                	}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	w.remove();
		                }
					}, 204);
		            aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	ItemStack whel = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta whelm = (LeatherArmorMeta) whel.getItemMeta();
                   	whelm.setColor(Color.BLACK);
                   	whel.setItemMeta(whelm);
                    ItemStack wb = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta wbm = (LeatherArmorMeta) wb.getItemMeta();
                   	wbm.setColor(Color.WHITE);
                   	wb.setItemMeta(wbm);
                    ItemStack wc = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta wcm = (LeatherArmorMeta) wc.getItemMeta();
                   	wcm.setColor(Color.fromRGB(80, 0, 68));
                   	wc.setItemMeta(wcm);
                    ItemStack wl = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta wlm = (LeatherArmorMeta) wl.getItemMeta();
                   	wlm.setColor(Color.PURPLE);
                   	wl.setItemMeta(wlm);
                    ItemStack wmh = new ItemStack(Material.DEBUG_STICK);
                    ItemStack wmo = new ItemStack(Material.DRAGON_BREATH);
                    
                    Skeleton w = (Skeleton)p.getWorld().spawnEntity(p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().add(0, -2, 0), EntityType.SKELETON);
                    w.setAI(false);
                    w.getEquipment().clear();
                    w.setGlowing(true);
                    w.setRemoveWhenFarAway(false);
                    w.setAbsorptionAmount(100);
					w.getEquipment().setHelmet(whel);
					w.getEquipment().setLeggings(wl);
					w.getEquipment().setBoots(wb);
					w.getEquipment().setChestplate(wc);
					w.getEquipment().setItemInOffHand(wmo);
					w.getEquipment().setItemInMainHand(wmh);
					w.getEquipment().setBootsDropChance(0);
					w.getEquipment().setChestplateDropChance(0);
					w.getEquipment().setHelmetDropChance(0);
					w.getEquipment().setItemInMainHandDropChance(0);
					w.getEquipment().setItemInOffHandDropChance(0);
					w.getEquipment().setLeggingsDropChance(0);
					w.setInvulnerable(true);
                    w.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					p.playSound(l, Sound.AMBIENT_WARPED_FOREST_ADDITIONS, 1, 0);
					p.playSound(l, Sound.ENTITY_SKELETON_AMBIENT, 1, 0);
					p.playSound(l, Sound.ENTITY_VINDICATOR_CELEBRATE, 1, 0);
					for(int dou = 0; dou < 30; dou++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								w.teleport(w.getLocation().add(w.getLocation().getDirection().rotateAroundX(-Math.PI/2).normalize().multiply(0.1)));
								p.playSound(l, Sound.ENTITY_WITHER_SKELETON_STEP, 0.8f, 0);
			                }
						}, dou);
					}
					for(int i = 0; i <40; i++) {
                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				        		p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, w.getLocation(), 100, 4, 2, 4);
				        		p.getWorld().spawnParticle(Particle.SOUL, w.getLocation(), 100, 4, 2, 4);
								for (Entity e : w.getNearbyEntities(9, 9, 9))
								{
									if (e instanceof Player) 
									{
										PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
										Player p1 = (Player) e;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
							        			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p1.getLocation(), 40, 4, 2, 4);
												if(Baron.containsKey(p1)) {
													Baron.computeIfPresent(p1, (k,v) -> v+1);
												}
												else {
													Baron.put(p1, 0);
												}
												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() 
									                {
									                	Baron.computeIfPresent(p1, (k, v) -> v-1);
									                	if(Baron.get(p1)<0) {
														Baron.remove(p1);
									                	}
									                }
									    	   }, 5);
											}
										}
					        			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p1.getLocation(), 40, 4, 2, 4);
										if(Baron.containsKey(p)) {
											Baron.computeIfPresent(p, (k,v) -> v+1);
										}
										else {
											Baron.put(p, 0);
										}
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	Baron.computeIfPresent(p, (k, v) -> v-1);
							                	if(Baron.get(p)<0) {
												Baron.remove(p);
							                	}
							                }
										}, 5);
									}
								}
			                }
			            }, i*5+3); 
            	}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	w.remove();
	                }
				}, 204);
	                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}	
    }
	

	@EventHandler
	public void ULT(EntityDamageEvent d) 
	{
	    
		
		
		if(d.getEntity() instanceof Player) 
		{
			Player p = (Player)d.getEntity();
			if((p.getHealth()-d.getDamage())<=0 && Baron.containsKey(p)) {
				PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
				if(PartyData.hasParty(p) && playerclass.get(p.getUniqueId()) != 14)	{
					if(PartyData.getMembers(PartyData.getParty(p)).anyMatch(par -> playerclass.get(Bukkit.getPlayer(par).getUniqueId())==14))
						{						
			                d.setCancelled(true);
			        		p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, p.getLocation(), 100, 4, 2, 4);
			        		p.getWorld().spawnParticle(Particle.SOUL, p.getLocation(), 100, 4, 2, 4);
			        		p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 1, 0);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Baron Samedi]").color(ChatColor.BOLD).color(ChatColor.DARK_PURPLE).create());
						}
					}
				
				
				if(playerclass.get(p.getUniqueId()) == 14) {						
	                d.setCancelled(true);
	        		p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, p.getLocation(), 100, 4, 2, 4);
	        		p.getWorld().spawnParticle(Particle.SOUL, p.getLocation(), 100, 4, 2, 4);
	        		p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 1, 0);
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Baron Samedi]").color(ChatColor.BOLD).color(ChatColor.DARK_PURPLE).create());
				}
			}
		}
	}
	

	@EventHandler
	public void Legba(EntityDamageByEntityEvent d) 
	{
        
		
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && d.getDamage()>0) 
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
				
				if(playerclass.get(p.getUniqueId()) == 14) {
						if(p.getInventory().getItemInMainHand().getType().name().contains("HOE")||p.getInventory().getItemInMainHand().getType().name().contains("TOTEM"))
						{
							if(p==le) {d.setCancelled(true);}
							d.setDamage(d.getDamage()+wsd.Legba.get(p.getUniqueId())*0.515);
						}
				}
				
				if(Bosou.containsKey(p)) {
					d.setDamage(d.getDamage()*1.2*(1+wsd.Bosou.get(p.getUniqueId())*0.05));
					p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, le.getLocation(), 5, 1,1,1,0);
				}
				if(Baron.containsKey(p)) {
					d.setDamage(d.getDamage()*1.32);
					p.getWorld().spawnParticle(Particle.SOUL, le.getLocation(), 5, 1,1,1,0);
				}
		}

		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity&& d.getDamage()>0) 
		{
			Arrow ar = (Arrow)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				
				
					if(playerclass.get(p.getUniqueId()) == 14) {
							if(p.getInventory().getItemInMainHand().getType().name().contains("HOE")||p.getInventory().getItemInMainHand().getType().name().contains("TOTEM"))
							{
								if(p==le) {d.setCancelled(true);}
								d.setDamage(d.getDamage()+wsd.Legba.get(p.getUniqueId())*0.515);
							}
					}
					if(Bosou.containsKey(p)) {
						d.setDamage(d.getDamage()*1.2*(1+wsd.Bosou.get(p.getUniqueId())*0.05));
						p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, le.getLocation(), 5, 1,1,1,0);
					}
					if(Baron.containsKey(p)) {
						d.setDamage(d.getDamage()*1.32);
						p.getWorld().spawnParticle(Particle.SOUL, le.getLocation(), 5, 1,1,1,0);
					}
			}
		}

		if(d.getDamager() instanceof EvokerFangs && d.getEntity() instanceof LivingEntity) 
		{
			EvokerFangs f = (EvokerFangs)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
	
			
			
			if(f.getOwner() instanceof Player) {
				Player p = (Player) f.getOwner();
				if(playerclass.get(p.getUniqueId()) == 14) {
						if(p.getInventory().getItemInMainHand().getType().name().contains("HOE")||p.getInventory().getItemInMainHand().getType().name().contains("TOTEM"))
						{
							if(p==le) {d.setCancelled(true);}
							d.setCancelled(true);
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
								enar.setDamage(player_damage.get(p.getName())*0.4+wsd.Fangs.get(p.getUniqueId())*0.3);								
							}
							le.damage(0, p);
							le.damage(player_damage.get(p.getName())*0.4+wsd.Fangs.get(p.getUniqueId())*0.3, p);
						}
				}
			}
		}
		
		if(d.getDamager() instanceof LivingEntity && d.getEntity() instanceof Player) 
		{
		Player p = (Player)d.getEntity();
		LivingEntity le = (LivingEntity)d.getDamager();

		if(Bosou.containsKey(p)) {
			d.setDamage(d.getDamage()*(0.7-wsd.Bosou.get(p.getUniqueId())*0.04));
			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, le.getLocation(), 5, 1,1,1,0);
		}
		}
	}
	
	@EventHandler
	public void Legba2(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof LivingEntity) 
		{
		LivingEntity le = (LivingEntity)d.getEntity();
			if(Hex.containsKey(le)) {
				d.setDamage(d.getDamage()*1.35);
				le.getWorld().spawnParticle(Particle.SOUL, le.getLocation(), 5, 1,1,1,0);
			}
		}
	}
	

	@EventHandler
	public void Resurrect(EntityDamageEvent d) 
	{
	    
		
		
		if(d.getEntity() instanceof Player) 
		{
			Player p = (Player)d.getEntity();
			int sec = 60;
			if((p.getHealth()-d.getDamage())<=0 && !Baron.containsKey(p)) {
				PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
				if(PartyData.hasParty(p) && playerclass.get(p.getUniqueId()) != 14)	{
					if(PartyData.getMembers(PartyData.getParty(p)).anyMatch(par -> playerclass.get(Bukkit.getPlayer(par).getUniqueId())==14))
						{
							if(lncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
					        {
					            long timer = (lncooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
					            if(!(timer < 0)) // if timer is still more then 0 or 0
					            {
					            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to Resurrect").create());
					            }
					            else // if timer is done
					            {
					                lncooldown.remove(p.getName()); // removing player from HashMap
					                res.computeIfAbsent(p, k -> 0);
					                res.computeIfPresent(p, (k,v) -> v+1);
					                d.setCancelled(true);
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 3, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 4, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 3, false, false));
									p.playEffect(EntityEffect.TOTEM_RESURRECT);
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Resurrect]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                     		@Override
	        		                	public void run() 
	        			                {	
	                     					res.computeIfPresent(p, (k,v) -> v-1);
	                     					if(res.get(p)<0) {
	                     						res.remove(p);
	                     					}
	                     				}
	        	                	}, 80);
									lncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
						        
					            }
					        }
					        else // if cooldown doesn't have players name in it
					        {
				                res.computeIfAbsent(p, k -> 0);
				                res.computeIfPresent(p, (k,v) -> v+1);
				                d.setCancelled(true);
								p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 3, false, false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 4, false, false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 3, false, false));
								p.playEffect(EntityEffect.TOTEM_RESURRECT);
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Resurrect]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                     		@Override
        		                	public void run() 
        			                {	
                     					res.computeIfPresent(p, (k,v) -> v-1);
                     					if(res.get(p)<0) {
                     						res.remove(p);
                     					}
                     				}
		        	            }, 80);
								lncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					        }
						}
					}
				
				
				if(playerclass.get(p.getUniqueId()) == 14) {
					if(lncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (lncooldown.get(p.getName())/1000 + sec+10) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to Resurrect").create());
			            }
			            else // if timer is done
			            {
			                lncooldown.remove(p.getName()); // removing player from HashMap
			                d.setCancelled(true);
							p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 3, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 4, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 3, false, false));
							p.playEffect(EntityEffect.TOTEM_RESURRECT);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Resurrect]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
							lncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
		                d.setCancelled(true);
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 3, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 4, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 3, false, false));
						p.playEffect(EntityEffect.TOTEM_RESURRECT);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Resurrect]").color(ChatColor.BOLD).color(ChatColor.GREEN).create());
						lncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
				}
			}
		}
	}
	
	@EventHandler
	public void Resurrect(EntityResurrectEvent e) 
	{
		if(e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();

		    
			
			
			if(playerclass.get(p.getUniqueId()) == 14)
			{
				e.setCancelled(true);
			}
		}
		
	}

	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

	    
		
		
		if(playerclass.get(p.getUniqueId()) == 14)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") || p.getInventory().getItemInMainHand().getType()== Material.TOTEM_OF_UNDYING)
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
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 14) {
				if(p.getInventory().getItemInOffHand().getType()==Material.IRON_HOE || p.getInventory().getItemInMainHand().getType()==Material.IRON_HOE)
				{
						player_damage.put(p.getName(), 5 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInOffHand().getType()==Material.DIAMOND_HOE || p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_HOE)
				{
						player_damage.put(p.getName(), 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInOffHand().getType()==Material.GOLDEN_HOE|| p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_HOE)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE|| p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInOffHand().getType()==Material.WOODEN_HOE|| p.getInventory().getItemInMainHand().getType()==Material.WOODEN_HOE)
				{
						player_damage.put(p.getName(), 2 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInOffHand().getType()==Material.NETHERITE_HOE|| p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_HOE)
				{
						player_damage.put(p.getName(), 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
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
        

		
		
		if(playerclass.get(p.getUniqueId()) == 14) {
			if(p.getInventory().getItemInOffHand().getType()==Material.IRON_HOE || p.getInventory().getItemInMainHand().getType()==Material.IRON_HOE)
			{
					player_damage.put(p.getName(), 5 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInOffHand().getType()==Material.DIAMOND_HOE || p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_HOE)
			{
					player_damage.put(p.getName(), 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInOffHand().getType()==Material.GOLDEN_HOE|| p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_HOE)
			{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE|| p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE)
			{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInOffHand().getType()==Material.WOODEN_HOE|| p.getInventory().getItemInMainHand().getType()==Material.WOODEN_HOE)
			{
					player_damage.put(p.getName(), 2 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInOffHand().getType()==Material.NETHERITE_HOE|| p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_HOE)
			{
					player_damage.put(p.getName(), 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 14) {
					if(p.getInventory().getItemInOffHand().getType()==Material.IRON_HOE || p.getInventory().getItemInMainHand().getType()==Material.IRON_HOE)
					{
							player_damage.put(p.getName(), 5 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInOffHand().getType()==Material.DIAMOND_HOE || p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_HOE)
					{
							player_damage.put(p.getName(), 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInOffHand().getType()==Material.GOLDEN_HOE|| p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_HOE)
					{
							player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE|| p.getInventory().getItemInMainHand().getType()==Material.STONE_HOE)
					{
							player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInOffHand().getType()==Material.WOODEN_HOE|| p.getInventory().getItemInMainHand().getType()==Material.WOODEN_HOE)
					{
							player_damage.put(p.getName(), 2 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInOffHand().getType()==Material.NETHERITE_HOE|| p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_HOE)
					{
							player_damage.put(p.getName(), 8 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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



