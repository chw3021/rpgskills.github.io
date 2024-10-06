package io.github.chw3021.classes.cook;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.party.Party;

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
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
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
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
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

public class Cookskills extends Pak implements Listener, Serializable {
	
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
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<Player, Integer> sat = new HashMap<Player, Integer>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private CookSkillsData esd;

	

	private static final Cookskills instance = new Cookskills ();
	public static Cookskills getInstance()
	{
		return instance;
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

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		CookSkillsData e = new CookSkillsData(CookSkillsData.loadData(path +"/plugins/RPGskills/CookSkillsData.data"));
		esd = e;
		
	}

		
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		CookSkillsData e = new CookSkillsData(CookSkillsData.loadData(path +"/plugins/RPGskills/CookSkillsData.data"));
		esd = e;
	}
	
	
	public void GrilledDishes(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);
        
		
		
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
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use GrilledDishes").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
	                    Location tl = gettargetblock(p,4).clone();
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
				                	p.getWorld().spawnParticle(Particle.SMOKE, l, 2, 4, 4, 4, 0.2);
									p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
									p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
									p.playSound(meat.getLocation(), Sound.BLOCK_FURNACE_FIRE_CRACKLE, 0.8f, 2f);
									sat.putIfAbsent(p, 0);
									sat.computeIfPresent(p, (k,v) -> v+1);
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
											if(Party.hasParty(p) && Party.hasParty(p1))	{
											if(Party.getParty(p).equals(Party.getParty(p1)))
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
				                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
				                			LivingEntity le = (LivingEntity)e;

											atk0(0.15, esd.GrilledDishes.get(p.getUniqueId())*0.15, p, le);
											le.setFireTicks(2);
											
				    						
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
                    Location tl = gettargetblock(p,4).clone();
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
			                	p.getWorld().spawnParticle(Particle.SMOKE, l, 2, 4, 4, 4, 0.2);
								p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
								p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
								p.playSound(meat.getLocation(), Sound.BLOCK_FURNACE_FIRE_CRACKLE, 0.8f, 2f);
								sat.putIfAbsent(p, 0);
								sat.computeIfPresent(p, (k,v) -> v+1);
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
										if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
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
			                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
			                			LivingEntity le = (LivingEntity)e;

										atk0(0.15, esd.GrilledDishes.get(p.getUniqueId())*0.15, p, le);
										le.setFireTicks(2);
										
			    						
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



	
	public void MushSpa(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 12*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);
		
		if(playerclass.get(p.getUniqueId()) == 18) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL"))
			{

				if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use MushSpa").create());
	                }
	                else // if timer is done
	                {
	                    gdcooldown.remove(p.getName()); // removing player from HashMap

	                    Snowball firstarrow = p.launchProjectile(Snowball.class);
	                    firstarrow.setItem(new ItemStack(Material.MUSHROOM_STEW));
	                    firstarrow.remove();
	                    AreaEffectCloud cloud = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
	                    cloud.setParticle(Particle.BLOCK, Material.RED_MUSHROOM_BLOCK.createBlockData());
						cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						cloud.setMetadata("spa of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    cloud.setRadius(3f);
	                    cloud.setSource(p);
	                    cloud.setSilent(false);
	                    cloud.setColor(Color.YELLOW);
	                    cloud.setDuration(100);
	                    AreaEffectCloud cloud2 = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
	                    cloud2.setParticle(Particle.BLOCK, Material.BROWN_MUSHROOM_BLOCK.createBlockData());
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
							p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,0,false,false));
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
											if(Party.hasParty(p) && Party.hasParty(p1))	{
											if(Party.getParty(p).equals(Party.getParty(p1)))
												{
													p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
													p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10,4,false,false));
													p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,0,false,false));
													p1.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.5f, 2f);
													p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
													p1.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
													sat.computeIfPresent(p1, (k,v) -> v+1);
													sat.putIfAbsent(p1, 0);
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
					            		if(e instanceof LivingEntity && !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
					            			LivingEntity le = (LivingEntity)e;

											atk0(0.3, esd.MushSpa.get(p.getUniqueId())*0.3, p, le);
											le.setFireTicks(2);
											
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
                    cloud.setParticle(Particle.BLOCK, Material.RED_MUSHROOM_BLOCK.createBlockData());
					cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					cloud.setMetadata("spa of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    cloud.setRadius(3f);
                    cloud.setSource(p);
                    cloud.setSilent(false);
                    cloud.setColor(Color.YELLOW);
                    cloud.setDuration(100);
                    AreaEffectCloud cloud2 = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
                    cloud2.setParticle(Particle.BLOCK, Material.BROWN_MUSHROOM_BLOCK.createBlockData());
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
						p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,0,false,false));
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
										if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
											{
												p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10,4,false,false));
												p1.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,0,false,false));
												p1.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.5f, 2f);
												p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
												p1.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
												sat.computeIfPresent(p1, (k,v) -> v+1);
												sat.putIfAbsent(p1, 0);
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
				            		if(e instanceof LivingEntity && !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
				            			LivingEntity le = (LivingEntity)e;
										
										le.setFireTicks(2);
										atk0(0.3, esd.MushSpa.get(p.getUniqueId())*0.3, p, le);
										
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

	
	public void DessertRain(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		double sec = 13*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);

        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 18) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL")) && !p.isSneaking())
			{
				ev.setCancelled(true);
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use DessertRain").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap

		                    Location l = gettargetblock(p,6).clone();
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
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 2,2,false,false));
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
												if(Party.hasParty(p) && Party.hasParty(p1))	{
													if(Party.getParty(p).equals(Party.getParty(p1)))
													{
														p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
														p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2,2,false,false));
														p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 2,2,false,false));
														p1.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2,2,false,false));
														sat.computeIfPresent(p1, (k,v) -> v+1);
														sat.putIfAbsent(p1, 0);
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
					                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
					                			LivingEntity le = (LivingEntity)e;

												atk0(0.07, esd.DessertRain.get(p.getUniqueId())*0.08, p, le);
												le.teleport(dl.add(0, -3.5, 0));
					    						
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

	                    Location l = gettargetblock(p,6).clone();
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
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 2,2,false,false));
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
											if(Party.hasParty(p) && Party.hasParty(p1))	{
												if(Party.getParty(p).equals(Party.getParty(p1)))
												{
													p1.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,0,false,false));
													p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2,2,false,false));
													p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 2,2,false,false));
													p1.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2,2,false,false));
													sat.computeIfPresent(p1, (k,v) -> v+1);
													sat.putIfAbsent(p1, 0);
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
				                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
				                			LivingEntity le = (LivingEntity)e;

											atk0(0.07, esd.DessertRain.get(p.getUniqueId())*0.08, p, le);
											le.teleport(dl.add(0, -3.5, 0));
				    						
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
	

	
	public void BerrySalad(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);
		
		if(playerclass.get(p.getUniqueId()) == 18) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR||ac == Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL"))
			{

				if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use BerrySalad").create());
	                }
	                else // if timer is done
	                {
	                    gdcooldown.remove(p.getName()); // removing player from HashMap

	    				if(p.hasPotionEffect(PotionEffectType.BLINDNESS))
	            		{
	            			p.removePotionEffect(PotionEffectType.BLINDNESS);
	            		}
	            		if(p.hasPotionEffect(PotionEffectType.NAUSEA))
	            		{
	            			p.removePotionEffect(PotionEffectType.NAUSEA);
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
            		if(p.hasPotionEffect(PotionEffectType.NAUSEA))
            		{
            			p.removePotionEffect(PotionEffectType.NAUSEA);
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
	
	
	public void BerrySalad(ProjectileHitEvent ev) 
	{
		if(ev.getEntity().hasMetadata("berry")) {
			Projectile pr = ev.getEntity();
			if(pr.getShooter() instanceof Player && ev.getHitEntity() instanceof LivingEntity) {
				Player p = (Player) pr.getShooter();
				LivingEntity e = (LivingEntity) ev.getHitEntity();
				
        		if (e instanceof Player) 
				{
        			
					Player p1 = (Player) e;
					if(Party.hasParty(p) && Party.hasParty(p1))	{
					if(Party.getParty(p).equals(Party.getParty(p1)))
						{
						p.playSound(p1.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PLACE, 1, 2);
		        		if(p1.hasPotionEffect(PotionEffectType.BLINDNESS))
		        		{
		        			p1.removePotionEffect(PotionEffectType.BLINDNESS);
		        		}
		        		if(p1.hasPotionEffect(PotionEffectType.NAUSEA))
		        		{
		        			p1.removePotionEffect(PotionEffectType.NAUSEA);
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
        		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
				{
					LivingEntity le = (LivingEntity)e;
						{

							atk0(0.35, esd.DessertRain.get(p.getUniqueId())*0.3, p, le);
							p.playSound(le.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PLACE, 1, 2);
							p.spawnParticle(Particle.BLOCK, le.getLocation(), 30,1,1,1, 1, Material.SWEET_BERRY_BUSH.createBlockData());
							
						}
				}
			}
		}
	}
	

	
	public void MelonWall(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 12*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);

        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 18 && p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL") && esd.MelonWall.get(p.getUniqueId())>=1) {
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) &&(ac!= Action.RIGHT_CLICK_AIR)&&(ac!= Action.RIGHT_CLICK_AIR))
			{
				ev.setCancelled(true);
				if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            double timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use MelonWall").create());
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
												{
													Holding.holding(p, le, 5l);
												}
										}
			                	});
			                	hon.getNearbyEntities(1, 1, 1).forEach(e ->{
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
												{
													Holding.holding(p, le, 5l);
												}
										}
			                	});
			                	pum.getNearbyEntities(1, 1, 1).forEach(e ->{
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
												{
													Holding.holding(p, le, 5l);
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
											{
												Holding.holding(p, le, 5l);
											}
									}
		                	});
		                	hon.getNearbyEntities(1, 1, 1).forEach(e ->{
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
											{
												Holding.holding(p, le, 5l);
											}
									}
		                	});
		                	pum.getNearbyEntities(1, 1, 1).forEach(e ->{
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
											{
												Holding.holding(p, le, 5l);
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
	

	
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 18 && ((is.getType().name().contains("SHOVEL"))) && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sultcooldown.get(p.getName())/1000 + 50) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Special Menu").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    if(Party.hasParty(p)) {
	                    	Party.getMembers(Party.getParty(p)).forEach(pu->{
	                    		Player pp = Bukkit.getPlayer(pu);
	                    		pp.playSound(pp.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1, 2);
	                            pp.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,200,5,false,false));
	                            pp.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,200,0,false,false));
	                            pp.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,5,50,false,false));
								sat.computeIfPresent(pp, (k,v) -> v+1);
	                    		sat.putIfAbsent(pp, 0);
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
	        	                    		p.getWorld().spawnParticle(Particle.ITEM, pp.getLocation(), 120,1,1,1,1, new ItemStack(Material.RABBIT_STEW));
	        	                    		p.getWorld().spawnParticle(Particle.ITEM, pp.getLocation(), 120,1,1,1,1, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
	        	                    		p.getWorld().spawnParticle(Particle.BLOCK, pp.getLocation(), 100,3,3,3,1,Material.GOLD_BLOCK.createBlockData());
	        	                    		p.getWorld().spawnParticle(Particle.COMPOSTER, pp.getLocation(), 50,3,3,3,1);
	        	                    		p.getWorld().spawnParticle(Particle.HEART, pp.getLocation(), 10,1,1,1,1);
	                                		 for(Entity e : pp.getNearbyEntities(3, 3, 3)) {
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
	 														atk0(0.43, 0d, p, le);
	 														
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
                    		sat.putIfAbsent(p, 0);
							sat.computeIfPresent(p, (k,v) -> v+1);
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
        	                    		p.getWorld().spawnParticle(Particle.ITEM, p.getLocation(), 120,1,1,1,1, new ItemStack(Material.RABBIT_STEW));
        	                    		p.getWorld().spawnParticle(Particle.ITEM, p.getLocation(), 120,1,1,1,1, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        	                    		p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100,3,3,3,1,Material.GOLD_BLOCK.createBlockData());
        	                    		p.getWorld().spawnParticle(Particle.COMPOSTER, p.getLocation(), 50,3,3,3,1);
        	                    		p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 10,1,1,1,1);
                                		 for(Entity e : p.getNearbyEntities(3, 3, 3)) {
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
													atk0(0.43, 0d, p, le);
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
	            	if(Party.hasParty(p)) {
                    	Party.getMembers(Party.getParty(p)).forEach(pu->{
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
        	                    		p.getWorld().spawnParticle(Particle.ITEM, pp.getLocation(), 120,1,1,1,1, new ItemStack(Material.RABBIT_STEW));
        	                    		p.getWorld().spawnParticle(Particle.ITEM, pp.getLocation(), 120,1,1,1,1, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        	                    		p.getWorld().spawnParticle(Particle.BLOCK, pp.getLocation(), 100,3,3,3,1,Material.GOLD_BLOCK.createBlockData());
        	                    		p.getWorld().spawnParticle(Particle.COMPOSTER, pp.getLocation(), 50,3,3,3,1);
        	                    		p.getWorld().spawnParticle(Particle.HEART, pp.getLocation(), 10,1,1,1,1);
                                		 for(Entity e : pp.getNearbyEntities(3, 3, 3)) {
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
													atk0(0.43, 0d, p, le);
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
    	                    		p.getWorld().spawnParticle(Particle.ITEM, p.getLocation(), 120,1,1,1,1, new ItemStack(Material.RABBIT_STEW));
    	                    		p.getWorld().spawnParticle(Particle.ITEM, p.getLocation(), 120,1,1,1,1, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
    	                    		p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100,3,3,3,1,Material.GOLD_BLOCK.createBlockData());
    	                    		p.getWorld().spawnParticle(Particle.COMPOSTER, p.getLocation(), 50,3,3,3,1);
    	                    		p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 10,1,1,1,1);
                            		 for(Entity e : p.getNearbyEntities(3, 3, 3)) {
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
													atk0(0.43, 0d, p, le);
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
	
	public void Cookery(EntityDamageByEntityEvent d) 
	{		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		
		
		if(playerclass.get(p.getUniqueId()) == 18) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL")) {
					if(d.getDamage()>0) {
						d.setDamage(d.getDamage()+esd.Saturation.get(p.getUniqueId())*0.43);
					}
				}
			}
		}

		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
		        
				

				
				

				if(playerclass.get(p.getUniqueId()) == 18) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("SHOVEL")) {
						if(d.getDamage()>0) {
							d.setDamage(d.getDamage()+esd.Saturation.get(p.getUniqueId())*0.43);
						}
					}
				}
			}
		}
	}

}



