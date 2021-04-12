package io.github.chw3021.cook;



import io.github.chw3021.chemist.CheSkillsData;
import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.party.PartyData;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import io.github.chw3021.rmain.RMain;
import io.github.chw3021.witherist.WitSkillsData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Cookskills implements Listener, Serializable {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 6779511048929362121L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<Player, Integer> sat = new HashMap<Player, Integer>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private CookSkillsData esd;
	

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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Cookskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				CookSkillsData v = new CookSkillsData(CookSkillsData.loadData(path +"/plugins/RPGskills/CookSkillsData.data"));
				esd = v;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		CookSkillsData e = new CookSkillsData(CookSkillsData.loadData(path +"/plugins/RPGskills/CookSkillsData.data"));
		esd = e;
		
	}

	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		CookSkillsData e = new CookSkillsData(CookSkillsData.loadData(path +"/plugins/RPGskills/CookSkillsData.data"));
		esd = e;
	}
	
	@EventHandler
	public void GrilledDishes(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 8;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 18)
		{
			if((p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL")) && p.isSneaking())
			{
				ev.setCancelled(true);
				
				
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use GrilledDishes").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
	                    Snowball firstarrow = p.launchProjectile(Snowball.class);
	                    firstarrow.setItem(new ItemStack(Material.FURNACE));
	                    firstarrow.remove();
	                    Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
	                    ArrayList<Location> meats = new ArrayList<>();
	                    AtomicInteger j = new AtomicInteger();
	                    for(int i=0; i<30; i++) {
				            Random random=new Random();
	                    	double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
	                    	double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
	                    	meats.add(tl.clone().add(number, 1, number2));
	                    }
	                    
	                    meats.forEach(l ->{
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	Random random=new Random();
				                	int ri = random.nextInt(7);
				                	Material type = null;
				                	if(ri == 0) {
				                		type = Material.COOKED_BEEF;
				                	}
				                	else if(ri == 1) {
				                		type = Material.COOKED_PORKCHOP;
				                	}
				                	else if(ri == 2) {
				                		type = Material.COOKED_CHICKEN;
				                	}
				                	else if(ri == 3) {
				                		type = Material.COOKED_COD;
				                	}
				                	else if(ri == 4) {
				                		type = Material.COOKED_MUTTON;
				                	}
				                	else if(ri == 5) {
				                		type = Material.COOKED_RABBIT;
				                	}
				                	else if(ri == 6) {
				                		type = Material.COOKED_SALMON;
				                	}
				                	Item meat = p.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_BEEF));
				                	meat.setPickupDelay(9999);
				                	meat.setItemStack(new ItemStack(type));
				                	meat.setOwner(p.getUniqueId());
				                	meat.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				                	meat.setMetadata("meat of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				                	p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, l, 2, 4, 4, 4, 0.2);
				                	p.getWorld().spawnParticle(Particle.FLAME, l, 1, 4, 4, 4, 0.2);
				                	p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 2, 4, 4, 4, 0.2);
				                	p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, l, 2, 4, 4, 4, 0.2);
									p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
									p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
									p.playSound(meat.getLocation(), Sound.BLOCK_FURNACE_FIRE_CRACKLE, 0.8f, 2f);
									sat.computeIfPresent(p, (k,v) -> v+1);
									sat.putIfAbsent(p, 0);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	meat.remove();
						                }
									}, 10);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
											sat.computeIfPresent(p, (k,v) -> v-1);
											if(sat.get(p)<0) {
												sat.remove(p);
											}
						                }
									}, 60);
				                	for(Entity e : p.getWorld().getNearbyEntities(l, 4,4,4)) {
				                		if (e instanceof Player) 
										{
											Player p1 = (Player) e;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
													p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
													p1.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
													sat.putIfAbsent(p1, 0);
													sat.computeIfPresent(p1, (k,v) -> v+1);
													Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() 
										                {
															sat.computeIfPresent(p1, (k,v) -> v-1);
															if(sat.get(p1)<0) {
																sat.remove(p1);
															}
										                }
													}, 60);
													continue;
												}
											}
										}
				                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
				                			LivingEntity le = (LivingEntity)e;
				    						p.setSprinting(true);
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.15 + esd.GrilledDishes.get(p.getUniqueId())*0.15);								
											}
											le.setFireTicks(2);
				    						le.damage(0, p);
				    						le.damage(player_damage.get(p.getName())*0.15 + esd.GrilledDishes.get(p.getUniqueId())*0.15, p);
				    						p.setSprinting(false);
				                		}
				                	}
				                }
							}, j.incrementAndGet()*2);
	                    	
	                    });
						sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	Snowball firstarrow = p.launchProjectile(Snowball.class);
                    firstarrow.setItem(new ItemStack(Material.FURNACE));
                    firstarrow.remove();
                    Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
                    ArrayList<Location> meats = new ArrayList<>();
                    AtomicInteger j = new AtomicInteger();
                    for(int i=0; i<30; i++) {
			            Random random=new Random();
                    	double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
                    	double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
                    	meats.add(tl.clone().add(number, 1, number2));
                    }
                    
                    meats.forEach(l ->{
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	Random random=new Random();
			                	int ri = random.nextInt(7);
			                	Material type = null;
			                	if(ri == 0) {
			                		type = Material.COOKED_BEEF;
			                	}
			                	else if(ri == 1) {
			                		type = Material.COOKED_PORKCHOP;
			                	}
			                	else if(ri == 2) {
			                		type = Material.COOKED_CHICKEN;
			                	}
			                	else if(ri == 3) {
			                		type = Material.COOKED_COD;
			                	}
			                	else if(ri == 4) {
			                		type = Material.COOKED_MUTTON;
			                	}
			                	else if(ri == 5) {
			                		type = Material.COOKED_RABBIT;
			                	}
			                	else if(ri == 6) {
			                		type = Material.COOKED_SALMON;
			                	}
			                	Item meat = p.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_BEEF));
			                	meat.setPickupDelay(9999);
			                	meat.setItemStack(new ItemStack(type));
			                	meat.setOwner(p.getUniqueId());
			                	meat.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                	meat.setMetadata("meat of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                	p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, l, 2, 4, 4, 4, 0.2);
			                	p.getWorld().spawnParticle(Particle.FLAME, l, 1, 4, 4, 4, 0.2);
			                	p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 2, 4, 4, 4, 0.2);
			                	p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, l, 2, 4, 4, 4, 0.2);
								p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
								p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
								p.playSound(meat.getLocation(), Sound.BLOCK_FURNACE_FIRE_CRACKLE, 0.8f, 2f);
								sat.computeIfPresent(p, (k,v) -> v+1);
								sat.putIfAbsent(p, 0);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	meat.remove();
					                }
								}, 10);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
										sat.computeIfPresent(p, (k,v) -> v-1);
										if(sat.get(p)<0) {
											sat.remove(p);
										}
					                }
								}, 40);
			                	for(Entity e : p.getWorld().getNearbyEntities(l, 4,4,4)) {
			                		if (e instanceof Player) 
									{
										Player p1 = (Player) e;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
												p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
												p1.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
												sat.putIfAbsent(p1, 0);
												sat.computeIfPresent(p1, (k,v) -> v+1);
												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() 
									                {
														sat.computeIfPresent(p1, (k,v) -> v-1);
														if(sat.get(p1)<0) {
															sat.remove(p1);
														}
									                }
												}, 40);
												continue;
											}
										}
									}
			                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
			                			LivingEntity le = (LivingEntity)e;
			    						p.setSprinting(true);
										if(le instanceof EnderDragon) {
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.15 + esd.GrilledDishes.get(p.getUniqueId())*0.15);								
										}
										le.setFireTicks(2);
			    						le.damage(0, p);
			    						le.damage(player_damage.get(p.getName())*0.15 + esd.GrilledDishes.get(p.getUniqueId())*0.15, p);
			    						p.setSprinting(false);
			                		}
			                	}
			                }
						}, j.incrementAndGet()*2);
                    	
                    });
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
	}



	@EventHandler
	public void MushSpa(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 12;
		
		if(playerclass.get(p.getUniqueId()) == 18) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL"))
			{

				if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use MushSpa").create());
	                }
	                else // if timer is done
	                {
	                    gdcooldown.remove(p.getName()); // removing player from HashMap

	                    Snowball firstarrow = p.launchProjectile(Snowball.class);
	                    firstarrow.setItem(new ItemStack(Material.MUSHROOM_STEW));
	                    firstarrow.remove();
	                    AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
	                    cloud.setParticle(Particle.BLOCK_CRACK, Material.RED_MUSHROOM_BLOCK.createBlockData());
						cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						cloud.setMetadata("spa of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    cloud.setRadius(3f);
	                    cloud.setSource(p);
	                    cloud.setSilent(false);
	                    cloud.setColor(Color.YELLOW);
	                    cloud.setDuration(100);
	                    AreaEffectCloud cloud2 = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
	                    cloud2.setParticle(Particle.BLOCK_CRACK, Material.BROWN_MUSHROOM_BLOCK.createBlockData());
						cloud2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						cloud2.setMetadata("spa of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    cloud2.setRadius(3f);
	                    cloud2.setSource(p);
	                    cloud2.setSilent(false);
	                    cloud2.setColor(Color.YELLOW);
	                    cloud2.setDuration(100);
	                    for(int count = 0 ; count <20; count++) {
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {	
							p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10,4,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
							p.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.5f, 2f);
							p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
							p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
							sat.computeIfPresent(p, (k,v) -> v+1);
							sat.putIfAbsent(p, 0);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									sat.computeIfPresent(p, (k,v) -> v-1);
									if(sat.get(p)<0) {
										sat.remove(p);
									}
				                }
							}, 60);
									for (Entity e : p.getWorld().getNearbyEntities(cloud.getLocation(), 3.5, 3.5, 3.5))
									{
										if (e instanceof Player) 
										{
											Player p1 = (Player) e;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
													p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
													p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10,4,false,false));
													p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
													p1.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.5f, 2f);
													p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
													p1.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
													sat.putIfAbsent(p1, 0);
													sat.computeIfPresent(p1, (k,v) -> v+1);
													Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() 
										                {
															sat.computeIfPresent(p1, (k,v) -> v-1);
															if(sat.get(p1)<0) {
																sat.remove(p1);
															}
										                }
													}, 60);
													continue;
												}
											}
										}
					            		if(e instanceof LivingEntity && !(e.hasMetadata("fake")) && e!=p) {
					            			LivingEntity le = (LivingEntity)e;
											p.setSprinting(true);
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.2+esd.MushSpa.get(p.getUniqueId())*0.2);								
											}
											le.setFireTicks(2);
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*0.2+esd.MushSpa.get(p.getUniqueId())*0.2, p);
											p.setSprinting(false);
					            		}
									}
					                }
		                	   }, count*5); 
							
						}
						gdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

                    Snowball firstarrow = p.launchProjectile(Snowball.class);
                    firstarrow.setItem(new ItemStack(Material.MUSHROOM_STEW));
                    firstarrow.remove();
                    AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
                    cloud.setParticle(Particle.BLOCK_CRACK, Material.RED_MUSHROOM_BLOCK.createBlockData());
					cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					cloud.setMetadata("spa of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    cloud.setRadius(3f);
                    cloud.setSource(p);
                    cloud.setSilent(false);
                    cloud.setColor(Color.YELLOW);
                    cloud.setDuration(100);
                    AreaEffectCloud cloud2 = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
                    cloud2.setParticle(Particle.BLOCK_CRACK, Material.BROWN_MUSHROOM_BLOCK.createBlockData());
					cloud2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					cloud2.setMetadata("spa of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    cloud2.setRadius(3f);
                    cloud2.setSource(p);
                    cloud2.setSilent(false);
                    cloud2.setColor(Color.YELLOW);
                    cloud2.setDuration(100);
                    for(int count = 0 ; count <20; count++) {
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
		                	public void run() 
			                {	
						p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10,4,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
						p.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.5f, 2f);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
						p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
						sat.computeIfPresent(p, (k,v) -> v+1);
						sat.putIfAbsent(p, 0);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								sat.computeIfPresent(p, (k,v) -> v-1);
								if(sat.get(p)<0) {
									sat.remove(p);
								}
			                }
						}, 60);
								for (Entity e : p.getWorld().getNearbyEntities(cloud.getLocation(), 3.5, 3.5, 3.5))
								{
									if (e instanceof Player) 
									{
										Player p1 = (Player) e;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
												p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10,4,false,false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1,0,false,false));
												p1.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.5f, 2f);
												p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
												p1.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
												sat.putIfAbsent(p1, 0);
												sat.computeIfPresent(p1, (k,v) -> v+1);
												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() 
									                {
														sat.computeIfPresent(p1, (k,v) -> v-1);
														if(sat.get(p1)<0) {
															sat.remove(p1);
														}
									                }
												}, 60);
												continue;
											}
										}
									}
				            		if(e instanceof LivingEntity && !(e.hasMetadata("fake")) && e!=p) {
				            			LivingEntity le = (LivingEntity)e;
										p.setSprinting(true);
										if(le instanceof EnderDragon) {
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.2+esd.MushSpa.get(p.getUniqueId())*0.2);								
										}
										le.setFireTicks(2);
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.2+esd.MushSpa.get(p.getUniqueId())*0.2, p);
										p.setSprinting(false);
				            		}
								}
				                }
	                	   }, count*5); 
						
					}
					gdcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}

	@EventHandler
	public void DessertRain(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		int sec = 15;

        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 18) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL")) && !p.isSneaking())
			{
				ev.setCancelled(true);
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use DessertRain").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().setDirection(p.getLocation().getDirection());
			            	Location el = new Location(p.getWorld(), l.getX(), l.getY()+4.5, l.getZ());
		                    ArrayList<Location> des = new ArrayList<>();
		                    AtomicInteger j = new AtomicInteger();
		                    for(int i=0; i<60; i++) {
		                    	Random random=new Random();
		                    	double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
		                    	double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
		                    	des.add(el.clone().add(number, 0.5, number2));
		                    }
		                    Snowball firstarrow = p.launchProjectile(Snowball.class);
		                    firstarrow.setItem(new ItemStack(Material.SUGAR));
		                    firstarrow.remove();
		                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
		                    ItemStack wb = new ItemStack(Material.LEATHER_BOOTS);
		                    LeatherArmorMeta wbm = (LeatherArmorMeta) wb.getItemMeta();
		                   	wbm.setColor(Color.WHITE);
		                   	wb.setItemMeta(wbm);
		                    ItemStack wc = new ItemStack(Material.LEATHER_CHESTPLATE);
		                    LeatherArmorMeta wcm = (LeatherArmorMeta) wc.getItemMeta();
		                   	wcm.setColor(Color.BLACK);
		                   	wc.setItemMeta(wcm);
		                    ItemStack wl = new ItemStack(Material.LEATHER_LEGGINGS);
		                    LeatherArmorMeta wlm = (LeatherArmorMeta) wl.getItemMeta();
		                   	wlm.setColor(Color.BLACK);
		                   	wl.setItemMeta(wlm);
		                    ItemStack head = new ItemStack(Material.CAKE);
		                    ItemStack right = new ItemStack(Material.BOWL);
		                    ItemStack left = new ItemStack(Material.PAPER);
		                    as.setCustomName(p.getName());
		                    as.setBasePlate(true);
		                    as.setMarker(true);
		                    as.setCollidable(false);
		                    as.setInvulnerable(true);
		                    as.setArms(true);
		                    as.getEquipment().setHelmet(head);
		                    as.setChestplate(wc);
		                    as.setLeggings(wl);
		                    as.setBoots(wb);
		                    as.getEquipment().setItemInMainHand(left);
		                    as.getEquipment().setItemInOffHand(right);
		                    as.setCanPickupItems(false);
		                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 0);
							if(p.isDead()) {
								as.remove();
							}
							des.forEach(dl ->{
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
				                    	Random random=new Random();
					                	int ri = random.nextInt(6);
					                	Material type = null;
					                	if(ri == 0) {
					                		type = Material.COOKIE;
					                	}
					                	else if(ri == 1) {
					                		type = Material.CAKE;
					                	}
					                	else if(ri == 2) {
					                		type = Material.PUMPKIN_PIE;
					                	}
					                	else if(ri == 3) {
					                		type = Material.APPLE;
					                	}
					                	else if(ri == 4) {
					                		type = Material.MELON_SLICE;
					                	}
					                	else if(ri == 5) {
					                		type = Material.HONEY_BOTTLE;
					                	}
					                	Item des = p.getWorld().dropItem(dl, new ItemStack(Material.COOKIE));
					                	des.setItemStack(new ItemStack(type));
					                	des.setPickupDelay(9999);
					                	des.setOwner(p.getUniqueId());
					                	des.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					                	des.setMetadata("des of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					                	p.getWorld().spawnParticle(Particle.FALLING_HONEY, el, 2, 4, 4, 4, 0.2);
										p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2,2,false,false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2,2,false,false));
										p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2,2,false,false));
										p.playSound(des.getLocation(), Sound.BLOCK_HONEY_BLOCK_SLIDE, 0.8f, 2f);
										sat.computeIfPresent(p, (k,v) -> v+1);
										sat.putIfAbsent(p, 0);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
												sat.computeIfPresent(p, (k,v) -> v-1);
												if(sat.get(p)<0) {
													sat.remove(p);
												}
							                }
										}, 60);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	des.remove();
							                }
										}, 60);
					                	for(Entity e : as.getWorld().getNearbyEntities(as.getLocation(), 4, 4, 4)) {
											if (e instanceof Player) 
											{
												Player p1 = (Player) e;
												if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
													if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
													{
														p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
														p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2,2,false,false));
														p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2,2,false,false));
														p1.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2,2,false,false));
														sat.putIfAbsent(p1, 0);
														sat.computeIfPresent(p1, (k,v) -> v+1);
														Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											                @Override
											                public void run() 
											                {
																sat.computeIfPresent(p1, (k,v) -> v-1);
																if(sat.get(p1)<0) {
																	sat.remove(p1);
																}
											                }
														}, 60);
														continue;
													}
												}
											}
					                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
					                			LivingEntity le = (LivingEntity)e;
					    						p.setSprinting(true);
												if(le instanceof EnderDragon) {
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
														a.setShooter(p);
														a.setCritical(false);
														a.setSilent(true);
														a.setPickupStatus(PickupStatus.DISALLOWED);
														a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(player_damage.get(p.getName())*0.065+esd.DessertRain.get(p.getUniqueId())*0.085);								
												}
												le.teleport(dl.add(0, -3.5, 0));
					    						le.damage(0, p);
					    						le.damage(player_damage.get(p.getName())*0.065+esd.DessertRain.get(p.getUniqueId())*0.085, p);
					    						p.setSprinting(false);
					                		}
					                	}
					                }
		                	   }, j.incrementAndGet());
							});
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	as.remove();
				                }
		            	   }, 63);
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().setDirection(p.getLocation().getDirection());
		            	Location el = new Location(p.getWorld(), l.getX(), l.getY()+4.5, l.getZ());
	                    ArrayList<Location> des = new ArrayList<>();
	                    AtomicInteger j = new AtomicInteger();
	                    for(int i=0; i<60; i++) {
	                    	Random random=new Random();
	                    	double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
	                    	double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
	                    	des.add(el.clone().add(number, 0.5, number2));
	                    }
	                    Snowball firstarrow = p.launchProjectile(Snowball.class);
	                    firstarrow.setItem(new ItemStack(Material.SUGAR));
	                    firstarrow.remove();
	                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
	                    ItemStack wb = new ItemStack(Material.LEATHER_BOOTS);
	                    LeatherArmorMeta wbm = (LeatherArmorMeta) wb.getItemMeta();
	                   	wbm.setColor(Color.WHITE);
	                   	wb.setItemMeta(wbm);
	                    ItemStack wc = new ItemStack(Material.LEATHER_CHESTPLATE);
	                    LeatherArmorMeta wcm = (LeatherArmorMeta) wc.getItemMeta();
	                   	wcm.setColor(Color.BLACK);
	                   	wc.setItemMeta(wcm);
	                    ItemStack wl = new ItemStack(Material.LEATHER_LEGGINGS);
	                    LeatherArmorMeta wlm = (LeatherArmorMeta) wl.getItemMeta();
	                   	wlm.setColor(Color.BLACK);
	                   	wl.setItemMeta(wlm);
	                    ItemStack head = new ItemStack(Material.CAKE);
	                    ItemStack right = new ItemStack(Material.BOWL);
	                    ItemStack left = new ItemStack(Material.PAPER);
	                    as.setCustomName(p.getName());
	                    as.setBasePlate(true);
	                    as.setMarker(true);
	                    as.setCollidable(false);
	                    as.setInvulnerable(true);
	                    as.setArms(true);
	                    as.getEquipment().setHelmet(head);
	                    as.setChestplate(wc);
	                    as.setLeggings(wl);
	                    as.setBoots(wb);
	                    as.getEquipment().setItemInMainHand(left);
	                    as.getEquipment().setItemInOffHand(right);
	                    as.setCanPickupItems(false);
	                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 0);
						if(p.isDead()) {
							as.remove();
						}
						des.forEach(dl ->{
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
			                    	Random random=new Random();
				                	int ri = random.nextInt(6);
				                	Material type = null;
				                	if(ri == 0) {
				                		type = Material.COOKIE;
				                	}
				                	else if(ri == 1) {
				                		type = Material.CAKE;
				                	}
				                	else if(ri == 2) {
				                		type = Material.PUMPKIN_PIE;
				                	}
				                	else if(ri == 3) {
				                		type = Material.APPLE;
				                	}
				                	else if(ri == 4) {
				                		type = Material.MELON_SLICE;
				                	}
				                	else if(ri == 5) {
				                		type = Material.HONEY_BOTTLE;
				                	}
				                	Item des = p.getWorld().dropItem(dl, new ItemStack(Material.COOKIE));
				                	des.setItemStack(new ItemStack(type));
				                	des.setPickupDelay(9999);
				                	des.setOwner(p.getUniqueId());
				                	des.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				                	des.setMetadata("des of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				                	p.getWorld().spawnParticle(Particle.FALLING_HONEY, el, 2, 4, 4, 4, 0.2);
									p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2,2,false,false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2,2,false,false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2,2,false,false));
									p.playSound(des.getLocation(), Sound.BLOCK_HONEY_BLOCK_SLIDE, 0.8f, 2f);
									sat.computeIfPresent(p, (k,v) -> v+1);
									sat.putIfAbsent(p, 0);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
											sat.computeIfPresent(p, (k,v) -> v-1);
											if(sat.get(p)<0) {
												sat.remove(p);
											}
						                }
									}, 60);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	des.remove();
						                }
									}, 60);
				                	for(Entity e : as.getWorld().getNearbyEntities(as.getLocation(), 4, 4, 4)) {
										if (e instanceof Player) 
										{
											Player p1 = (Player) e;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
												if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
													p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
													p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2,2,false,false));
													p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2,2,false,false));
													p1.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2,2,false,false));
													sat.putIfAbsent(p1, 0);
													sat.computeIfPresent(p1, (k,v) -> v+1);
													Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() 
										                {
															sat.computeIfPresent(p1, (k,v) -> v-1);
															if(sat.get(p1)<0) {
																sat.remove(p1);
															}
										                }
													}, 60);
													continue;
												}
											}
										}
				                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
				                			LivingEntity le = (LivingEntity)e;
				    						p.setSprinting(true);
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.065+esd.DessertRain.get(p.getUniqueId())*0.085);								
											}
											le.teleport(dl.add(0, -3.5, 0));
				    						le.damage(0, p);
				    						le.damage(player_damage.get(p.getName())*0.065+esd.DessertRain.get(p.getUniqueId())*0.085, p);
				    						p.setSprinting(false);
				                		}
				                	}
				                }
	                	   }, j.incrementAndGet());
						});
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	as.remove();
			                }
	            	   }, 63);
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	

	@EventHandler
	public void BerrySalad(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 6;
		
		if(playerclass.get(p.getUniqueId()) == 18) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR||ac == Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL"))
			{

				if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use BerrySalad").create());
	                }
	                else // if timer is done
	                {
	                    gdcooldown.remove(p.getName()); // removing player from HashMap

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
	            		if(p.hasPotionEffect(PotionEffectType.WITHER))
	            		{
	            			p.removePotionEffect(PotionEffectType.WITHER);
	            		}
	            		
	            		Snowball sn = p.launchProjectile(Snowball.class);
	            		sn.setItem(new ItemStack(Material.SWEET_BERRIES));
	            		sn.setVelocity(sn.getVelocity().multiply(1.325));
	            		sn.setMetadata("berry", new FixedMetadataValue(RMain.getInstance(), true));
	            		sn.setShooter(p);
	            		sn.setGlowing(true);
						gdcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

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
            		if(p.hasPotionEffect(PotionEffectType.WITHER))
            		{
            			p.removePotionEffect(PotionEffectType.WITHER);
            		}
            		
            		Snowball sn = p.launchProjectile(Snowball.class);
            		sn.setItem(new ItemStack(Material.SWEET_BERRIES));
            		sn.setVelocity(sn.getVelocity().multiply(1.325));
            		sn.setMetadata("berry", new FixedMetadataValue(RMain.getInstance(), true));
            		sn.setShooter(p);
            		sn.setGlowing(true);
					gdcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} 
            
		}}
	}
	
	@EventHandler
	public void BerrySalad(ProjectileHitEvent ev) 
	{
		if(ev.getEntity().hasMetadata("berry")) {
			Projectile pr = ev.getEntity();
			if(pr.getShooter() instanceof Player && ev.getHitEntity() instanceof LivingEntity) {
				Player p = (Player) pr.getShooter();
				LivingEntity e = (LivingEntity) ev.getHitEntity();
				
        		if (e instanceof Player) 
				{
        			new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
					Player p1 = (Player) e;
					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
						{
						p.playSound(p1.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PLACE, 1, 2);
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
		        		if(p1.hasPotionEffect(PotionEffectType.WITHER))
		        		{
		        			p1.removePotionEffect(PotionEffectType.WITHER);
		        		}
						return;
						}
					}
				}
        		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
				{
					LivingEntity le = (LivingEntity)e;
						{
							if(le instanceof EnderDragon) {
			                    Snowball firstarrow = p.launchProjectile(Snowball.class);
			                    firstarrow.setItem(new ItemStack(Material.SUGAR));
			                    firstarrow.remove();
								Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
									ar.setShooter(p);
									ar.setCritical(false);
									ar.setSilent(true);
									ar.setPickupStatus(PickupStatus.DISALLOWED);
									ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
								});
								enar.setDamage(player_damage.get(p.getName())*0.35 + esd.BerrySalad.get(p.getUniqueId())*0.3);
							}	
							p.setSprinting(true);
							p.playSound(le.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PLACE, 1, 2);
							p.spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 30,1,1,1, 1, Material.SWEET_BERRY_BUSH.createBlockData());
		                    le.damage(0,p);
		                    le.damage(player_damage.get(p.getName())*0.35 + esd.BerrySalad.get(p.getUniqueId())*0.3,p);
							p.setSprinting(false);
						}
				}
			}
		}
	}
	

	@EventHandler
	public void MelonWall(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 12;

        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 18 && p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL") && esd.MelonWall.get(p.getUniqueId())>=1) {
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) &&(ac!= Action.RIGHT_CLICK_AIR)&&(ac!= Action.RIGHT_CLICK_AIR))
			{
				ev.setCancelled(true);
				if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use MelonWall").create());
			        }
		            else // if timer is done
		            {
		                smcooldown.remove(p.getName()); // removing player from HashMapLocation dam = p.getLocation();
		                Location tl = p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(3.5));
			        	ArmorStand mel = (ArmorStand) p.getWorld().spawnEntity(tl, EntityType.ARMOR_STAND);
			        	ArmorStand pum = (ArmorStand) p.getWorld().spawnEntity(tl.add(tl.clone().getDirection().rotateAroundY(Math.PI/2).normalize().multiply(2.1)), EntityType.ARMOR_STAND);
			        	ArmorStand hon = (ArmorStand) p.getWorld().spawnEntity(tl.add(tl.clone().getDirection().rotateAroundY(Math.PI/2).normalize().multiply(-4.1)), EntityType.ARMOR_STAND);
	                    mel.setCustomName(p.getName());
	                    mel.setBasePlate(false);
	                    mel.setCollidable(true);
	                    mel.setInvulnerable(true);
	                    mel.setMarker(false);
	                    mel.getEquipment().setItemInOffHand(new ItemStack(Material.MELON));
	                    mel.getEquipment().setItemInMainHand(new ItemStack(Material.MELON));
	                    mel.getEquipment().setHelmet(new ItemStack(Material.MELON));
	                    mel.setCanPickupItems(false);
	                    mel.setVisible(false);
	                    mel.setArms(true);
	                    mel.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			        	mel.setMetadata("wall of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			        	
			        	pum.setCustomName(p.getName());
	                    pum.setBasePlate(false);
	                    pum.setMarker(false);
	                    pum.setCollidable(true);
	                    pum.setInvulnerable(true);
	                    pum.getEquipment().setItemInOffHand(new ItemStack(Material.PUMPKIN));
	                    pum.getEquipment().setItemInMainHand(new ItemStack(Material.PUMPKIN));
	                    pum.getEquipment().setHelmet(new ItemStack(Material.PUMPKIN));
	                    pum.setCanPickupItems(false);
	                    pum.setVisible(false);
	                    pum.setArms(true);
	                    pum.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			        	pum.setMetadata("wall of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			        	
			        	hon.setCustomName(p.getName());
	                    hon.setBasePlate(false);
	                    hon.setMarker(false);
	                    hon.setCollidable(true);
	                    hon.setInvulnerable(true);
	                    hon.getEquipment().setItemInOffHand(new ItemStack(Material.HONEYCOMB_BLOCK));
	                    hon.getEquipment().setItemInMainHand(new ItemStack(Material.HONEYCOMB_BLOCK));
	                    hon.getEquipment().setHelmet(new ItemStack(Material.HONEYCOMB_BLOCK));
	                    hon.setCanPickupItems(false);
	                    hon.setVisible(false);
	                    hon.setArms(true);
	                    hon.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			        	hon.setMetadata("wall of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			        	int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	mel.getNearbyEntities(1, 1, 1).forEach(e ->{
			                		 if (e instanceof Player) 
										{
											Player p1 = (Player) e;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
												return;
												}
											}
										}
			                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
										{
											LivingEntity le = (LivingEntity)e;
												{
													hold.holding(p, le, 5l);
												}
										}
			                	});
			                	hon.getNearbyEntities(1, 1, 1).forEach(e ->{
			                		 if (e instanceof Player) 
										{
											Player p1 = (Player) e;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
												return;
												}
											}
										}
			                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
										{
											LivingEntity le = (LivingEntity)e;
												{
													hold.holding(p, le, 5l);
												}
										}
			                	});
			                	pum.getNearbyEntities(1, 1, 1).forEach(e ->{
			                		 if (e instanceof Player) 
										{
											Player p1 = (Player) e;
											if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
												return;
												}
											}
										}
			                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
										{
											LivingEntity le = (LivingEntity)e;
												{
													hold.holding(p, le, 5l);
												}
										}
			                	});
			                }
						}, 0, 1/5); 
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	Bukkit.getServer().getScheduler().cancelTask(task);
			                	mel.remove();
			                	pum.remove();
			                	hon.remove();
			                }
	            	   }, 90);
			            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
		        	Location tl = p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(3.5));
		        	ArmorStand mel = (ArmorStand) p.getWorld().spawnEntity(tl, EntityType.ARMOR_STAND);
		        	ArmorStand pum = (ArmorStand) p.getWorld().spawnEntity(tl.add(tl.clone().getDirection().rotateAroundY(Math.PI/2).normalize().multiply(2.1)), EntityType.ARMOR_STAND);
		        	ArmorStand hon = (ArmorStand) p.getWorld().spawnEntity(tl.add(tl.clone().getDirection().rotateAroundY(Math.PI/2).normalize().multiply(-4.1)), EntityType.ARMOR_STAND);
		        	mel.setCustomName(p.getName());
                    mel.setBasePlate(false);
                    mel.setCollidable(true);
                    mel.setInvulnerable(true);
                    mel.setMarker(false);
                    mel.getEquipment().setItemInOffHand(new ItemStack(Material.MELON));
                    mel.getEquipment().setItemInMainHand(new ItemStack(Material.MELON));
                    mel.getEquipment().setHelmet(new ItemStack(Material.MELON));
                    mel.setCanPickupItems(false);
                    mel.setVisible(false);
                    mel.setArms(true);
                    mel.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		        	mel.setMetadata("wall of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		        	
		        	pum.setCustomName(p.getName());
                    pum.setBasePlate(false);
                    pum.setMarker(false);
                    pum.setCollidable(true);
                    pum.setInvulnerable(true);
                    pum.getEquipment().setItemInOffHand(new ItemStack(Material.PUMPKIN));
                    pum.getEquipment().setItemInMainHand(new ItemStack(Material.PUMPKIN));
                    pum.getEquipment().setHelmet(new ItemStack(Material.PUMPKIN));
                    pum.setCanPickupItems(false);
                    pum.setVisible(false);
                    pum.setArms(true);
                    pum.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		        	pum.setMetadata("wall of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		        	
		        	hon.setCustomName(p.getName());
                    hon.setBasePlate(false);
                    hon.setMarker(false);
                    hon.setCollidable(true);
                    hon.setInvulnerable(true);
                    hon.getEquipment().setItemInOffHand(new ItemStack(Material.HONEYCOMB_BLOCK));
                    hon.getEquipment().setItemInMainHand(new ItemStack(Material.HONEYCOMB_BLOCK));
                    hon.getEquipment().setHelmet(new ItemStack(Material.HONEYCOMB_BLOCK));
                    hon.setCanPickupItems(false);
                    hon.setVisible(false);
                    hon.setArms(true);
                    hon.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		        	hon.setMetadata("wall of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		        	int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	mel.getNearbyEntities(1, 1, 1).forEach(e ->{
		                		 if (e instanceof Player) 
									{
										Player p1 = (Player) e;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
											return;
											}
										}
									}
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
									{
										LivingEntity le = (LivingEntity)e;
											{
												hold.holding(p, le, 5l);
											}
									}
		                	});
		                	hon.getNearbyEntities(1, 1, 1).forEach(e ->{
		                		 if (e instanceof Player) 
									{
										Player p1 = (Player) e;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
											return;
											}
										}
									}
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
									{
										LivingEntity le = (LivingEntity)e;
											{
												hold.holding(p, le, 5l);
											}
									}
		                	});
		                	pum.getNearbyEntities(1, 1, 1).forEach(e ->{
		                		 if (e instanceof Player) 
									{
										Player p1 = (Player) e;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
											return;
											}
										}
									}
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
									{
										LivingEntity le = (LivingEntity)e;
											{
												hold.holding(p, le, 5l);
											}
									}
		                	});
		                }
					}, 0, 1/5); 
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	Bukkit.getServer().getScheduler().cancelTask(task);
		                	mel.remove();
		                	pum.remove();
		                	hon.remove();
		                }
            	   }, 90);
		            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
	}
	

	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 18 && ((is.getType().name().contains("SHOVEL"))) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 50) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Special Menu").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    if(PartyData.hasParty(p)) {
	                    	PartyData.getMembers(PartyData.getParty(p)).forEach(pu->{
	                    		Player pp = Bukkit.getPlayer(pu);
	                    		pp.playSound(pp.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1, 2);
	                            pp.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,200,5,false,false));
	                            pp.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,200,0,false,false));
	                            pp.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,5,50,false,false));
	                    		sat.putIfAbsent(pp, 0);
								sat.computeIfPresent(pp, (k,v) -> v+1);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
										sat.computeIfPresent(pp, (k,v) -> v-1);
										if(sat.get(pp)<0) {
											sat.remove(pp);
										}
					                }
								}, 60);
	                            for(int ip = 0; ip<10; ip++) {
	                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                                	@Override
	            	                	public void run() 
	            		                {
	        	                    		pp.playSound(pp.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1, 2);
	        	                    		p.getWorld().spawnParticle(Particle.ITEM_CRACK, pp.getLocation(), 120,1,1,1,1, new ItemStack(Material.RABBIT_STEW));
	        	                    		p.getWorld().spawnParticle(Particle.ITEM_CRACK, pp.getLocation(), 120,1,1,1,1, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
	        	                    		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, pp.getLocation(), 100,3,3,3,1,Material.GOLD_BLOCK.createBlockData());
	        	                    		p.getWorld().spawnParticle(Particle.COMPOSTER, pp.getLocation(), 50,3,3,3,1);
	        	                    		p.getWorld().spawnParticle(Particle.HEART, pp.getLocation(), 10,1,1,1,1);
	                                		 for(Entity e : pp.getNearbyEntities(3, 3, 3)) {
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
	 													{
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
	 															enar.setDamage(player_damage.get(p.getName())*0.3);								
	 														}
	 														p.setSprinting(true);
	 									                    le.damage(0,p);
	 									                    le.damage(player_damage.get(p.getName())*0.3,p);
	 														p.setSprinting(false);
	 													}
	 											}
	                                		 }
	            			            }
	                                }, ip*20); 
	                            }
	                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                            	@Override
	        	                	public void run() 
	        		                {
	                            		 pp.setHealth(pp.getHealth());
	        			            }
	                            }, 200); 
	                    	});
	                    }
	                    else {
	                    	p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1, 2);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,200,5,false,false));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,200,0,false,false));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,5,50,false,false));
							sat.computeIfPresent(p, (k,v) -> v+1);
                    		sat.putIfAbsent(p, 0);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									sat.computeIfPresent(p, (k,v) -> v-1);
									if(sat.get(p)<0) {
										sat.remove(p);
									}
				                }
							}, 60);
                            for(int ip = 0; ip<10; ip++) {
                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                                	@Override
            	                	public void run() 
            		                {
        	                    		p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1, 2);
        	                    		p.getWorld().spawnParticle(Particle.ITEM_CRACK, p.getLocation(), 120,1,1,1,1, new ItemStack(Material.RABBIT_STEW));
        	                    		p.getWorld().spawnParticle(Particle.ITEM_CRACK, p.getLocation(), 120,1,1,1,1, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        	                    		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 100,3,3,3,1,Material.GOLD_BLOCK.createBlockData());
        	                    		p.getWorld().spawnParticle(Particle.COMPOSTER, p.getLocation(), 50,3,3,3,1);
        	                    		p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 10,1,1,1,1);
                                		 for(Entity e : p.getNearbyEntities(3, 3, 3)) {
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
 													{
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
 															enar.setDamage(player_damage.get(p.getName())*0.3);								
 														}
 														p.setSprinting(true);
 									                    le.damage(0,p);
 									                    le.damage(player_damage.get(p.getName())*0.3,p);
 														p.setSprinting(false);
 													}
 											}
                                		 }
            			            }
                                }, ip*20); 
                            }
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                            	@Override
        	                	public void run() 
        		                {
                            		 p.setHealth(p.getHealth());
        			            }
                            }, 200); 
	                    	
	                    }
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	if(PartyData.hasParty(p)) {
                    	PartyData.getMembers(PartyData.getParty(p)).forEach(pu->{
                    		Player pp = Bukkit.getPlayer(pu);
                    		pp.playSound(pp.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1, 2);
                            pp.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,200,5,false,false));
                            pp.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,200,0,false,false));
                            pp.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,5,50,false,false));
                    		sat.putIfAbsent(pp, 0);
							sat.computeIfPresent(pp, (k,v) -> v+1);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									sat.computeIfPresent(pp, (k,v) -> v-1);
									if(sat.get(pp)<0) {
										sat.remove(pp);
									}
				                }
							}, 60);
                            for(int ip = 0; ip<10; ip++) {
                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                                	@Override
            	                	public void run() 
            		                {
        	                    		pp.playSound(pp.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1, 2);
        	                    		p.getWorld().spawnParticle(Particle.ITEM_CRACK, pp.getLocation(), 120,1,1,1,1, new ItemStack(Material.RABBIT_STEW));
        	                    		p.getWorld().spawnParticle(Particle.ITEM_CRACK, pp.getLocation(), 120,1,1,1,1, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        	                    		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, pp.getLocation(), 100,3,3,3,1,Material.GOLD_BLOCK.createBlockData());
        	                    		p.getWorld().spawnParticle(Particle.COMPOSTER, pp.getLocation(), 50,3,3,3,1);
        	                    		p.getWorld().spawnParticle(Particle.HEART, pp.getLocation(), 10,1,1,1,1);
                                		 for(Entity e : pp.getNearbyEntities(3, 3, 3)) {
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
 													{
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
 															enar.setDamage(player_damage.get(p.getName())*0.3);								
 														}
 														p.setSprinting(true);
 									                    le.damage(0,p);
 									                    le.damage(player_damage.get(p.getName())*0.3,p);
 														p.setSprinting(false);
 													}
 											}
                                		 }
            			            }
                                }, ip*20); 
                            }
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                            	@Override
        	                	public void run() 
        		                {
                            		 pp.setHealth(pp.getHealth());
        			            }
                            }, 200); 
                    	});
                    }
                    else {
                    	p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1, 2);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,200,5,false,false));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,200,0,false,false));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,5,50,false,false));
						sat.computeIfPresent(p, (k,v) -> v+1);
                		sat.putIfAbsent(p, 0);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								sat.computeIfPresent(p, (k,v) -> v-1);
								if(sat.get(p)<0) {
									sat.remove(p);
								}
			                }
						}, 60);
                        for(int ip = 0; ip<10; ip++) {
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                            	@Override
        	                	public void run() 
        		                {
    	                    		p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1, 2);
    	                    		p.getWorld().spawnParticle(Particle.ITEM_CRACK, p.getLocation(), 120,1,1,1,1, new ItemStack(Material.RABBIT_STEW));
    	                    		p.getWorld().spawnParticle(Particle.ITEM_CRACK, p.getLocation(), 120,1,1,1,1, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
    	                    		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 100,3,3,3,1,Material.GOLD_BLOCK.createBlockData());
    	                    		p.getWorld().spawnParticle(Particle.COMPOSTER, p.getLocation(), 50,3,3,3,1);
    	                    		p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 10,1,1,1,1);
                            		 for(Entity e : p.getNearbyEntities(3, 3, 3)) {
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
													{
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
															enar.setDamage(player_damage.get(p.getName())*0.3);								
														}
														p.setSprinting(true);
									                    le.damage(0,p);
									                    le.damage(player_damage.get(p.getName())*0.3,p);
														p.setSprinting(false);
													}
											}
                            		 }
        			            }
                            }, ip*20); 
                        }
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                        	@Override
    	                	public void run() 
    		                {
                        		 p.setHealth(p.getHealth());
    			            }
                        }, 200); 
                    	
                    }
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
        

		
		
		if(playerclass.get(p.getUniqueId()) == 18)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL") )
			{
					e.setCancelled(true);
			}
		}
		
	}

	@EventHandler
	public void Saturation(EntityDamageByEntityEvent d) 
	{		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
			if(sat.containsKey(p)) {
				d.setDamage(d.getDamage()*1.25*(1));
			}
		}

		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
			Projectile ar = (Projectile)d.getDamager();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();

				if(sat.containsKey(p)) {
					d.setDamage(d.getDamage()*1.25*(1));
				}
			}
		}
	}
	@EventHandler
	public void Cookery(EntityDamageByEntityEvent d) 
	{		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
        
		

		
		
		if(playerclass.get(p.getUniqueId()) == 18) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL")) {
					d.setDamage(d.getDamage()+esd.Saturation.get(p.getUniqueId())*0.56);
				}
			}
		}

		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			EnderDragon le = (EnderDragon)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
		        
				

				
				

				if(playerclass.get(p.getUniqueId()) == 18) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL")) {
						d.setDamage(d.getDamage()+esd.Saturation.get(p.getUniqueId())*0.56);
					}
				}
			}
		}
	}

	@EventHandler
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 18) {
				if(p.getInventory().getItemInMainHand().getType()==Material.IRON_SHOVEL)
				{
						player_damage.put(p.getName(), 4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_SHOVEL)
				{
						player_damage.put(p.getName(), 5 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_SHOVEL)
				{
						player_damage.put(p.getName(), 2 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STONE_SHOVEL)
				{
						player_damage.put(p.getName(),3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_SHOVEL)
				{
						player_damage.put(p.getName(), 2 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_SHOVEL)
				{
						player_damage.put(p.getName(), 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
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
        

		
		
		if(playerclass.get(p.getUniqueId()) == 18) {
			if(p.getInventory().getItemInMainHand().getType()==Material.IRON_SHOVEL)
			{
					player_damage.put(p.getName(), 4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_SHOVEL)
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_SHOVEL)
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.STONE_SHOVEL)
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_SHOVEL)
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_SHOVEL)
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
		}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow ar = (Arrow)d.getDamager();
			Entity e = d.getEntity();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 18) {
					if(p.getInventory().getItemInMainHand().getType()==Material.IRON_SHOVEL)
					{
							player_damage.put(p.getName(), 4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_SHOVEL)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_SHOVEL)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.STONE_SHOVEL)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_SHOVEL)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_SHOVEL)
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
				}
			}
		}
	}
}



