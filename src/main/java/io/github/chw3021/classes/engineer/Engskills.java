package io.github.chw3021.classes.engineer;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.obtains.Obtained;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.google.common.util.concurrent.AtomicDouble;

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
import org.bukkit.Vibration;
import org.bukkit.Vibration.Destination;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Engskills extends Pak implements Listener, Serializable {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 6779511048929362121L;
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<LivingEntity, Integer> xray = new HashMap<LivingEntity, Integer>();
	private HashMap<LivingEntity, Player> xrayp = new HashMap<LivingEntity, Player>();
	

	private HashMap<UUID, Integer> obv = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> obvt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fcry = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fcryt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> emp = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> empt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> grsh = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> grsht = new HashMap<UUID, Integer>();
	

	private HashMap<UUID, Integer> engb = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> engbt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> orb = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> orbt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> thcr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> thcrt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> angv = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> angvt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> propl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> proplt = new HashMap<UUID, Integer>();
	
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private EngSkillsData esd;


	
	private static final Engskills instance = new Engskills ();
	public static Engskills getInstance()
	{
		return instance;
	}


		
	public void er(PluginEnableEvent ev) 
	{
		EngSkillsData e = new EngSkillsData(EngSkillsData.loadData(path +"/plugins/RPGskills/EngSkillsData.data"));
		esd = e;
	}
	
	
	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Engskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				EngSkillsData ae = new EngSkillsData(EngSkillsData.loadData(path +"/plugins/RPGskills/EngSkillsData.data"));
				esd = ae;
			}
			
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		EngSkillsData e = new EngSkillsData(EngSkillsData.loadData(path +"/plugins/RPGskills/EngSkillsData.data"));
		esd = e;
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void Dispenser(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 17)
		{
		double sec =9*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking())
			{
				ev.setCancelled(true);
				
				
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("발사기 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Dispenser").create());
		        		}
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
	                    
	                    
	                    if(Proficiency.getpro(p)>=1) {
	                    	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    }

	                	if(obvt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(obvt.get(p.getUniqueId()));
	                		obvt.remove(p.getUniqueId());
	                	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									obv.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								obv.remove(p.getUniqueId());
			                }
			            }, 25); 
	                	obvt.put(p.getUniqueId(), task);
	                    
	                    Location asl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().setDirection(p.getLocation().getDirection());
	                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(asl, EntityType.ARMOR_STAND);
	                    ItemStack head = new ItemStack(Material.DISPENSER);
	                    ItemStack ch = new ItemStack(Material.ELYTRA);
	                    ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
	                    ItemStack boot = new ItemStack(Material.IRON_BOOTS);
	                    ItemStack right = new ItemStack(Material.SNOWBALL);
	                    ItemStack left = new ItemStack(Material.BOW);
	                    as.setCustomName(p.getName());
	                    as.setBasePlate(false);
	                    as.setSmall(true);
	                    as.setMarker(true);
	                    as.setInvulnerable(true);
	                    as.setInvisible(true);
	                    as.setArms(true);
	                    as.setHelmet(head);
	                    as.setChestplate(ch);
	                    as.setLeggings(leg);
	                    as.setBoots(boot);
	                    as.getEquipment().setItemInMainHand(left);
	                    as.getEquipment().setItemInOffHand(right);
	                    as.setCanPickupItems(false);
	                    as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						if(p.isDead()) {
							as.remove();
						}
	                    for(int i = 0; i<20; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									p.playSound(as.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 0.4f, 0);
				                	for(Entity e : as.getWorld().getNearbyEntities(as.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation(), 6, 6, 6)) {
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
				                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
				                			LivingEntity le = (LivingEntity)e;
				                			Snowball sn = (Snowball) as.getWorld().spawnEntity(as.getEyeLocation(), EntityType.SNOWBALL);
				                			sn.setVelocity(le.getEyeLocation().clone().toVector().subtract(as.getEyeLocation().clone().toVector()).normalize().multiply(1.65));
				                			sn.setShooter(p);
				                			sn.setBounce(false);
				    	                    sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				    	                    sn.setItem(new ItemStack(Material.RAW_IRON));
				    	                    atk0(0.06, esd.Dispenser.get(p.getUniqueId())*0.06, p, le);
				                		}
				                	}
				                }
	                	   }, i*5);
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	as.remove();
			                }
                	   }, 101);
						sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    if(Proficiency.getpro(p)>=1) {
                    	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                    	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                    	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                    	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                    	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                    	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                    }
                    
                	if(obvt.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(obvt.get(p.getUniqueId()));
                		obvt.remove(p.getUniqueId());
                	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=1) {
								obv.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 3); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							obv.remove(p.getUniqueId());
		                }
		            }, 25); 
                	obvt.put(p.getUniqueId(), task);

                    Location asl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().setDirection(p.getLocation().getDirection());
                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(asl, EntityType.ARMOR_STAND);
                    ItemStack head = new ItemStack(Material.DISPENSER);
                    ItemStack ch = new ItemStack(Material.ELYTRA);
                    ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
                    ItemStack boot = new ItemStack(Material.IRON_BOOTS);
                    ItemStack right = new ItemStack(Material.SNOWBALL);
                    ItemStack left = new ItemStack(Material.BOW);
                    as.setCustomName(p.getName());
                    as.setBasePlate(false);
                    as.setSmall(true);
                    as.setMarker(true);
                    as.setInvulnerable(true);
                    as.setInvisible(true);
                    as.setArms(true);
                    as.setHelmet(head);
                    as.setChestplate(ch);
                    as.setLeggings(leg);
                    as.setBoots(boot);
                    as.getEquipment().setItemInMainHand(left);
                    as.getEquipment().setItemInOffHand(right);
                    as.setCanPickupItems(false);
                    as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					if(p.isDead()) {
						as.remove();
					}
                    for(int i = 0; i<20; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.playSound(as.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 0.4f, 0);
			                	for(Entity e : as.getWorld().getNearbyEntities(as.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation(), 6, 6, 6)) {
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
			                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
			                			LivingEntity le = (LivingEntity)e;
			                			Snowball sn = (Snowball) as.getWorld().spawnEntity(as.getEyeLocation(), EntityType.SNOWBALL);
			                			sn.setVelocity(le.getEyeLocation().clone().toVector().subtract(as.getEyeLocation().clone().toVector()).normalize().multiply(1.65));
			                			sn.setShooter(p);
			                			sn.setBounce(false);
			    	                    sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			    	                    sn.setItem(new ItemStack(Material.RAW_IRON));
			    	                    atk0(0.06, esd.Dispenser.get(p.getUniqueId())*0.06, p, le);
			                		}
			                	}
			                }
                	   }, i*5);
                    }
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	as.remove();
		                }
            	   }, 101);
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
	}


	
	@SuppressWarnings("deprecation")
	public void Observer(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 17)
		{
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking() &&obv.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

                if(Proficiency.getpro(p)>=1) {
                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                }
                
            	if(obvt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(obvt.get(p.getUniqueId()));
            		obvt.remove(p.getUniqueId());
            	}
				obv.remove(p.getUniqueId());
				


            	if(fcryt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(fcryt.get(p.getUniqueId()));
            		fcryt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							fcry.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						fcry.remove(p.getUniqueId());
	                }
	            }, 25); 
            	fcryt.put(p.getUniqueId(), task);

        		
	                    Location asl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().setDirection(p.getLocation().getDirection());
	                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(asl, EntityType.ARMOR_STAND);
	                    ItemStack head = new ItemStack(Material.OBSERVER);
	                    ItemStack ch = new ItemStack(Material.ELYTRA);
	                    ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
	                    ItemStack boot = new ItemStack(Material.IRON_BOOTS);
	                    ItemStack right = new ItemStack(Material.REDSTONE_TORCH);
	                    ItemStack left = new ItemStack(Material.REDSTONE_TORCH);
	                    as.setCustomName(p.getName());
	                    as.setBasePlate(false);
	                    as.setSmall(true);
	                    as.setMarker(true);
	                    as.setInvulnerable(true);
	                    as.setInvisible(true);
	                    as.setArms(true);
	                    as.setHelmet(head);
	                    as.setChestplate(ch);
	                    as.setLeggings(leg);
	                    as.setBoots(boot);
	                    as.getEquipment().setItemInMainHand(left);
	                    as.getEquipment().setItemInOffHand(right);
	                    as.setCanPickupItems(false);
	                    as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						if(p.isDead()) {
							as.remove();
						}
	                    for(int i = 0; i<5; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	p.playSound(as.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 0.5f, 2);
		                			as.getWorld().spawnParticle(Particle.GLOW, as.getLocation(), 50,1,1,1);
		                			as.getWorld().spawnParticle(Particle.WAX_ON, as.getLocation(), 50,1,1,1);
				                	for(Entity e : as.getWorld().getNearbyEntities(as.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation(), 6, 6, 6)) {
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
				                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
				                			LivingEntity le = (LivingEntity)e;
				                			if(!le.isDead()) {
					                			le.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 1);

					    	                    atk0(0.1, esd.Dispenser.get(p.getUniqueId())*0.06, p, le);
					    	                    /*
												if(le instanceof EnderDragon) {
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
														a.setShooter(p);
														a.setCritical(false);
														a.setSilent(true);
														a.setPickupStatus(PickupStatus.DISALLOWED);
														a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(player_damage.get(p.getName())*0.1+esd.Dispenser.get(p.getUniqueId())*0.06);								
												}
					    						p.setCooldown(Material.YELLOW_TERRACOTTA, 1);
					    						le.damage(0, p);
					    						le.damage(player_damage.get(p.getName())*0.1+esd.Dispenser.get(p.getUniqueId())*0.06, p);
					    						*/
					    						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,20,false,false));
					    						Holding.holding(p, le, 10l);
				                			}
				                		}
				                	}
				                }
	                	   }, i*10);
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	as.remove();
			                }
                	   }, 101);
			}
		}
	}

	final private void Factory(ArmorStand as, LivingEntity le,Player p) {

		Snowball sn = (Snowball) as.getWorld().spawnEntity(as.getEyeLocation(), EntityType.SNOWBALL);
		sn.setVelocity(le.getEyeLocation().clone().toVector().subtract(as.getEyeLocation().clone().toVector()).normalize().multiply(0.9));
		sn.setShooter(p);
		sn.setBounce(false);
        sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
        sn.setItem(new ItemStack(Material.OBSERVER));
		Snowball sn1 = (Snowball) as.getWorld().spawnEntity(as.getEyeLocation().clone().add(0, -0.2, 0), EntityType.SNOWBALL);
		sn1.setVelocity(le.getEyeLocation().clone().toVector().subtract(as.getEyeLocation().clone().toVector()).normalize().multiply(0.75));
		sn1.setShooter(p);
		sn1.setBounce(false);
        sn1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
        sn1.setItem(new ItemStack(Material.DROPPER));
	}

	
	@SuppressWarnings("deprecation")
	public void Factory(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 17)
		{
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking()&&fcry.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

                if(Proficiency.getpro(p)>=1) {
                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                }
                
            	if(fcryt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(fcryt.get(p.getUniqueId()));
            		fcryt.remove(p.getUniqueId());
            	}
				fcry.remove(p.getUniqueId());


        		AtomicInteger ai = new AtomicInteger();
        		
	                    Location asl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().setDirection(p.getLocation().getDirection());
	                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(asl, EntityType.ARMOR_STAND);
	                    ItemStack head = new ItemStack(Material.DISPENSER);
	                    ItemStack ch = new ItemStack(Material.ELYTRA);
	                    ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
	                    ItemStack boot = new ItemStack(Material.IRON_BOOTS);
	                    ItemStack right = new ItemStack(Material.DROPPER);
	                    ItemStack left = new ItemStack(Material.OBSERVER);
	                    as.setCustomName(p.getName());
	                    as.setBasePlate(true);
	                    as.setMarker(true);
	                    as.setInvulnerable(true);
	                    as.setInvisible(true);
	                    as.setArms(true);
	                    as.setHelmet(head);
	                    as.setChestplate(ch);
	                    as.setLeggings(leg);
	                    as.setBoots(boot);
	                    as.getEquipment().setItemInMainHand(left);
	                    as.getEquipment().setItemInOffHand(right);
	                    as.setCanPickupItems(false);
	                    as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						if(p.isDead()) {
							as.remove();
						}
	                    for(int i = 0; i<10; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									p.playSound(as.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 0.5f, 0);
				                	for(Entity e : as.getWorld().getNearbyEntities(as.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation(), 6, 6, 6)) {
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
				                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
				                			LivingEntity le = (LivingEntity)e;

				                			Factory(as,le,p);
				    						p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, le.getLocation(), 1);
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
								                	for(Entity e : le.getWorld().getNearbyEntities(le.getLocation(), 2, 2,2)) {
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
								                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
								                			LivingEntity le = (LivingEntity)e;
								    	                    atk0(0.34, esd.Dispenser.get(p.getUniqueId())*0.4, p, le);
								                		}
								                	}
						    						
								                }
								    	   }, ai.incrementAndGet()/15+1);	
				                		}
				                	}
				                }
	                	   }, i*10);
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	as.remove();
			                }
                	   }, 101);
			}
		}
	}

	

	
	public void X_ray(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
			{
				p.setCooldown(Material.STRUCTURE_VOID, 2);

				if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (gdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("엑스선 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use X_ray").create());
		        		}
	                }
	                else // if timer is done
	                {
	                    gdcooldown.remove(p.getName()); // removing player from HashMap
	                    if(Proficiency.getpro(p)>=1) {
	                    	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    }
	                    
	                	if(empt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(empt.get(p.getUniqueId()));
	                		empt.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							emp.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						emp.remove(p.getUniqueId());
	    	                }
	    	            }, 25); 
	                	empt.put(p.getUniqueId(), task);
	                	
	                    HashSet<Location> pie = new HashSet<Location>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    final Location pl = p.getLocation().clone();
	                    for(double d = 0.1; d <= 6.1; d += 1) {
		                    for(double d1 = -Math.PI/6; d1<= Math.PI/6; d1 += Math.PI/60) {
		                    	pie.add(pl.clone().add(pl.clone().getDirection().normalize().rotateAroundY(d1).multiply(d)));
		                    }
	                    }
	                    
	                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 2);
	                    pie.forEach(l ->{
							p.getWorld().spawnParticle(Particle.FLASH, l.add(0, -0.56, 0), 1, 0.5,0.5,0.5,0);

	                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
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
	                    });
	                    for(LivingEntity le: les) 
						{
    	                    atk0(0.32, esd.X_ray.get(p.getUniqueId())*0.24, p, le);
							le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,40,40,false,false));
							Holding.holding(p, le, (long) 30);
							
	                		if(xray.containsKey(le)) {
	                			xray.computeIfPresent(le, (k,v) -> v+1);
							}
							else {
								xray.put(le, 0);
								xrayp.put(le, p);
							}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	xray.computeIfPresent(le, (k, v) -> v-1);
				                	if(xray.get(le)<0) {
				                		xray.remove(le);
				                		xrayp.remove(le);
				                	}
				                }
							}, 50);
						}
						gdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	                if(Proficiency.getpro(p)>=1) {
	                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                }
	                
                	if(empt.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(empt.get(p.getUniqueId()));
                		empt.remove(p.getUniqueId());
                	}

    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						if(Proficiency.getpro(p)>=1) {
    							emp.putIfAbsent(p.getUniqueId(), 0);
    						}
    	                }
    	            }, 3); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						emp.remove(p.getUniqueId());
    	                }
    	            }, 25); 
                	empt.put(p.getUniqueId(), task);

                    HashSet<Location> pie = new HashSet<Location>();
                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    final Location pl = p.getLocation().clone();
                    for(double d = 0.1; d <= 6.1; d += 1) {
	                    for(double d1 = -Math.PI/6; d1<= Math.PI/6; d1 += Math.PI/60) {
	                    	pie.add(pl.clone().add(pl.clone().getDirection().normalize().rotateAroundY(d1).multiply(d)));
	                    }
                    }
                    
                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 2);
                    pie.forEach(l ->{
						p.getWorld().spawnParticle(Particle.FLASH, l.add(0, -0.56, 0), 1, 0.5,0.5,0.5,0);

                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
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
                    });
                    for(LivingEntity le: les) 
					{
	                    atk0(0.32, esd.X_ray.get(p.getUniqueId())*0.24, p, le);
						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,40,40,false,false));
						Holding.holding(p, le, (long) 30);
						
                		if(xray.containsKey(le)) {
                			xray.computeIfPresent(le, (k,v) -> v+1);
						}
						else {
							xray.put(le, 0);
							xrayp.put(le, p);
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	xray.computeIfPresent(le, (k, v) -> v-1);
			                	if(xray.get(le)<0) {
			                		xray.remove(le);
			                		xrayp.remove(le);
			                	}
			                }
						}, 50);
					}
					gdcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}

	
	public void X_ray(EntityDamageByEntityEvent d) 
	{		
		if(d.getDamager() instanceof LivingEntity) 
		{
		LivingEntity le = (LivingEntity)d.getDamager();
	        if(xray.containsKey(le)) {
	        	d.setDamage(d.getDamage()*0.65);
	        }
		}
	}
	


	
	public void EMP(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK) &&emp.containsKey(p.getUniqueId()))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
			{
                if(Proficiency.getpro(p)>=1) {
                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                }
                
            	if(empt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(empt.get(p.getUniqueId()));
            		empt.remove(p.getUniqueId());
            	}
				emp.remove(p.getUniqueId());
				


            	if(grsht.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(grsht.get(p.getUniqueId()));
            		grsht.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							grsh.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						grsh.remove(p.getUniqueId());
	                }
	            }, 25); 
            	grsht.put(p.getUniqueId(), task);
				
	            Snowball sn = p.launchProjectile(Snowball.class);
	            sn.setBounce(true);
	            sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	            sn.setMetadata("emp", new FixedMetadataValue(RMain.getInstance(), true));
	            sn.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	            sn.setShooter(p);
	            sn.setItem(new ItemStack(Material.EMERALD_BLOCK));
	            sn.setGlowing(true);
	            sn.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.6));
	            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            	@Override
                	public void run() 
	                {
	                	if(sn.isValid()) {
			            	sn.remove();
			            	Bukkit.getPluginManager().callEvent(new ProjectileHitEvent(sn, sn.getLocation().getBlock()));
	                	}
	                }
         	   	}, 15);
	            
			} // adding players name + current system time in miliseconds
            
		}
		}
	}

	
	public void EMP(ProjectileHitEvent d) 
	{
		if(d.getEntity().hasMetadata("emp")) {
			Player p = (Player) d.getEntity().getShooter();
			p.getWorld().spawnParticle(Particle.COMPOSTER, d.getEntity().getLocation(), 200, 2,2,2,1);
			p.getWorld().spawnParticle(Particle.GLOW, d.getEntity().getLocation(), 400, 2,2,2,1);
			p.getWorld().spawnParticle(Particle.WAX_ON, d.getEntity().getLocation(), 400, 2,2,2,1);
			p.getWorld().spawnParticle(Particle.WARPED_SPORE, d.getEntity().getLocation(), 400, 2,2,2,1);
			p.playSound(d.getEntity().getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.85f, 1.15f);
			for (Entity e : d.getEntity().getLocation().getWorld().getNearbyEntities(d.getEntity().getLocation(), 3, 3, 3))
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
                    atk0(0.7, esd.X_ray.get(p.getUniqueId())*0.57, p, le);
					Holding.holding(p, le, 30l);
					p.getWorld().spawnParticle(Particle.VIBRATION, le.getLocation(), 10, 1,1,1, new Vibration(d.getEntity().getLocation(), new Destination.EntityDestination(le), 10));
				}
			}
		}
	}


	
	public void GravityShift(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK) &&grsh.containsKey(p.getUniqueId()))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
			{

                if(Proficiency.getpro(p)>=1) {
                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                }
                
            	if(grsht.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(grsht.get(p.getUniqueId()));
            		grsht.remove(p.getUniqueId());
            	}
				grsh.remove(p.getUniqueId());

				p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, p.getLocation(), 50, 1,1,1);
				final Location pfel = p.getEyeLocation().clone();
				Location tl = pfel.clone();

				for(int i = 0 ; i<25; i++) {
					Location tell = pfel.clone().add(pfel.clone().getDirection().normalize().multiply(i*0.2));
					if(tell.getBlock().isPassable()) {
						tl = tell;
					}
				}
				
				p.teleport(tl);
				p.playSound(tl, Sound.ENTITY_SHULKER_TELEPORT, 0.85f, 1.15f);
				p.playSound(tl, Sound.ENTITY_SLIME_JUMP, 0.85f, 1.6f);
				Holding.superholding(p, p, 8l);
				
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	    				p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 0.85f, 2f);
	    				p.playSound(p.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 0.85f, 2f);
						for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),5, 5, 5)) {
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
			                    atk0(0.76, esd.X_ray.get(p.getUniqueId())*0.45, p, le);
			                    le.teleport(p);
			                    Holding.superholding(p, le, 40l);
							}
						}
	                }
	            }, 7); 	           
	            
			} // adding players name + current system time in miliseconds
            
		}
		}
	}


	
	public void Graviton(EntityDamageEvent d) 
	{
		if(d.getEntity().hasMetadata("graviton")) {
			d.setCancelled(true);
		}
	}
	
	public void Graviton(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		double sec =8*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking())
			{
				ev.setCancelled(true);
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (cdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {

			        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("중력자 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						    }
			        		else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Graviton").create());
			        		}
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    if(Proficiency.getpro(p)>=1) {
		                    	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                    	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                    	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                    	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                    	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                    	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                    }
		                    
		                	if(engbt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(engbt.get(p.getUniqueId()));
		                		engbt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							engb.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						engb.remove(p.getUniqueId());
		    	                }
		    	            }, 25); 
		                	engbt.put(p.getUniqueId(), task);
		                	
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
							EnderCrystal graviton = (EnderCrystal) p.getWorld().spawnEntity(l, EntityType.ENDER_CRYSTAL);
							graviton.setInvulnerable(true);
							graviton.setShowingBottom(false);
							graviton.setMetadata("graviton of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							graviton.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
							graviton.setMetadata("graviton", new FixedMetadataValue(RMain.getInstance(), true));
							graviton.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							graviton.setGlowing(true);
	                    	for(int i =0; i<9; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	graviton.setBeamTarget(p.getLocation().add(0, -0.5, 0));
						                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 0.5f, 2.0f);
						                	for (Entity e : p.getWorld().getNearbyEntities(l, 5.2, 5.2, 5.2))
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
															if(le instanceof EnderDragon) {
																le.teleport(l.clone().add(0, 1.23, 0));							
															}
															else {
											                    le.teleport(l);																
															}
															
															Holding.holding(p, le, 6l);
										                    atk0(0.12, esd.Graviton.get(p.getUniqueId())*0.085, p, le);
														}
												}
											}
						                }
			                	   }, i*4); 
							}
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	graviton.remove();
				                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
					        		p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1,1,1,1);
				                	for (Entity e : p.getWorld().getNearbyEntities(l, 5.2, 5.2, 5.2))
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
								                    atk0(0.52, esd.Graviton.get(p.getUniqueId())*0.5, p, le);
														
												}
										}
									}
				                }
	                    	}, 42); 
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    if(Proficiency.getpro(p)>=1) {
	                    	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    }
	                    
	                	if(engbt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(engbt.get(p.getUniqueId()));
	                		engbt.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							engb.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						engb.remove(p.getUniqueId());
	    	                }
	    	            }, 25); 
	                	engbt.put(p.getUniqueId(), task);
	                	
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
						EnderCrystal graviton = (EnderCrystal) p.getWorld().spawnEntity(l, EntityType.ENDER_CRYSTAL);
						graviton.setInvulnerable(true);
						graviton.setShowingBottom(false);
						graviton.setMetadata("graviton of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						graviton.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
						graviton.setMetadata("graviton", new FixedMetadataValue(RMain.getInstance(), true));
						graviton.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						graviton.setGlowing(true);
                    	for(int i =0; i<9; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	graviton.setBeamTarget(p.getLocation().add(0, -0.5, 0));
					                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 0.5f, 2.0f);
					                	for (Entity e : p.getWorld().getNearbyEntities(l, 5.2, 5.2, 5.2))
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
														if(le instanceof EnderDragon) {
															le.teleport(l.clone().add(0, 1.23, 0));							
														}
														else {
										                    le.teleport(l);																
														}
														
														Holding.holding(p, le, 6l);
									                    atk0(0.12, esd.Graviton.get(p.getUniqueId())*0.085, p, le);
													}
											}
										}
					                }
		                	   }, i*4); 
						}
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	graviton.remove();
			                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
				        		p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1,1,1,1);
			                	for (Entity e : p.getWorld().getNearbyEntities(l, 5.2, 5.2, 5.2))
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
							                    atk0(0.52, esd.Graviton.get(p.getUniqueId())*0.5, p, le);
													
											}
									}
								}
			                }
                    	}, 42); 
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	
	
	public void EnergyBall(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking() && engb.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

                if(Proficiency.getpro(p)>=1) {
                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                }
                
            	if(engbt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(engbt.get(p.getUniqueId()));
            		engbt.remove(p.getUniqueId());
            	}
				engb.remove(p.getUniqueId());
				


            	if(orbt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(orbt.get(p.getUniqueId()));
            		orbt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							orb.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						orb.remove(p.getUniqueId());
	                }
	            }, 25); 
            	orbt.put(p.getUniqueId(), task);

	            Snowball sn = p.launchProjectile(Snowball.class);
	            sn.setBounce(false);
	            sn.setGravity(false);
	            sn.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.076));
	            sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	            sn.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
	            sn.setShooter(p);
	            ItemStack eb = new ItemStack(Material.RESPAWN_ANCHOR);
	            eb.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
	            sn.setItem(eb);
	            sn.setGlowing(true);
	            
	                    	for(int i =0; i<20; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                    p.playSound(sn.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 0.055f, 2.0f);
						                    sn.getWorld().spawnParticle(Particle.END_ROD, sn.getLocation(), 10,1,1,1);
						                    sn.getWorld().spawnParticle(Particle.FLASH, sn.getLocation(), 10,1,1,1);
						                	for (Entity e : p.getWorld().getNearbyEntities(sn.getLocation(), 5, 5, 5))
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
								                    atk0(0.25, esd.Graviton.get(p.getUniqueId())*0.25, p, le);
													Holding.holding(p, le, 6l);
												}
											}
						                }
			                	   }, i*3); 
							}
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	sn.remove();
				                }
	                    	}, 62);
				}
		}
	
	}

	private void Orbital(Location il, World w) {

    	ArrayList<Location> line = new ArrayList<Location>();
    	ArrayList<Location> line2 = new ArrayList<Location>();
    	ArrayList<Location> line3 = new ArrayList<Location>();
    	ArrayList<Location> line4 = new ArrayList<Location>();
    	
        Location pl = il.clone();
        Vector pfv = il.clone().add(0, 1, 0).toVector().subtract(il.clone().toVector());
        Vector pv = pfv.clone().rotateAroundAxis(pl.clone().getDirection(), -Math.PI/4);
        Vector pv2 = pfv.clone().rotateAroundAxis(pl.clone().getDirection(), Math.PI/4);
        Vector pv3 = pfv.clone().rotateAroundAxis(pl.clone().getDirection(), Math.PI/2);

        for(double an = 0; an > -Math.PI*2; an -= Math.PI/90) {
        	Location sw = pl.clone().add(pl.clone().getDirection().normalize().rotateAroundAxis(pv, an).multiply(2.6));
        	line.add(sw);
        	Location sw1 = pl.clone().add(pl.clone().getDirection().normalize().rotateAroundAxis(pv2, an).multiply(2.6));
        	line2.add(sw1);
        	Location sw11 = pl.clone().add(pl.clone().getDirection().normalize().rotateAroundAxis(pv3, an).multiply(2.6));
        	line4.add(sw11);
        }
        for(double an = Math.PI/2.5; an>-Math.PI/2.5; an-=Math.PI/90) {
        	line3.add(pl.clone().add(pl.getDirection().rotateAroundY(an).normalize().multiply(2.6)));
        }
	    line.forEach(l -> {
			w.spawnParticle(Particle.WAX_ON, l,2,0.1,0.1,0.1,0.1);
	    });
	    line2.forEach(l -> {
			w.spawnParticle(Particle.WAX_OFF, l,2,0.1,0.1,0.1,0.1);
	    });
	    line3.forEach(l -> {
			w.spawnParticle(Particle.TOWN_AURA, l,2,0.1,0.1,0.1,0.1);
	    });
	    line4.forEach(l -> {
			w.spawnParticle(Particle.SLIME, l,2,0.1,0.1,0.1,0.1);
	    });
	}

	

	
	public void Orbital(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking() && orb.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

                if(Proficiency.getpro(p)>=1) {
                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                }
                

            	if(orbt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(orbt.get(p.getUniqueId()));
            		orbt.remove(p.getUniqueId());
            	}
				orb.remove(p.getUniqueId());

				final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation();
				final World tw = tl.getWorld();

				AtomicInteger j = new AtomicInteger();
				AtomicDouble ad = new AtomicDouble();
				
				Item sn1 = p.getWorld().dropItemNaturally(tl.add(1, 0, 1), new ItemStack(Material.RAW_COPPER_BLOCK));
				Item sn2 = p.getWorld().dropItemNaturally(tl.add(-1, 0, -1), new ItemStack(Material.RAW_IRON_BLOCK));

	            sn1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	            sn1.setOwner(p.getUniqueId());
	            sn1.setGlowing(true);
	            sn1.setGravity(false);
	            sn1.setPickupDelay(999999);

	            sn2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	            sn2.setOwner(p.getUniqueId());
	            sn2.setGlowing(true);
	            sn2.setGravity(false);
	            sn2.setPickupDelay(999999);
                sn1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                sn2.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	     
                Vector w = sn1.getLocation().getDirection().clone().normalize().rotateAroundAxis(p.getLocation().getDirection().clone().rotateAroundY(Math.PI/2), Math.PI/2);
                

				for(double an = 0; an <Math.PI*4; an += Math.PI/90) {
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	Vector r = sn1.getLocation().getDirection().clone().rotateAroundY(ad.getAndAdd(Math.PI/90)).normalize().multiply(2);
		                    Vector v = w.clone().multiply(r.clone());
		                    sn1.setVelocity(v);
		                    sn2.setVelocity(v);
		                }
		            }, j.getAndIncrement()/9); 	                    	
                }
                
				for(int i = 0; i <13; i++) {
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	Orbital(tl,tw);
							for(Entity e : p.getWorld().getNearbyEntities(tl,5, 5, 5)) {
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
				                    atk0(0.2, esd.Graviton.get(p.getUniqueId())*0.45, p, le);
								}
							}
		                }
		            }, i*3); 	                    	
                }
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
		            	sn1.remove();
		            	sn2.remove();
	                }
	            }, 40); 	
				}
		}
	
	}
	
	
	public void Electrostatic(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =7*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
			{
				
				final Location l = gettargetblock(p,4);
				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (frcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {

		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("정전기장 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Electrostatic").create());
		        		}
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap

	                    if(Proficiency.getpro(p)>=1) {
	                    	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                    }
	                    
	                	if(thcrt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(thcrt.get(p.getUniqueId()));
	                		thcrt.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							thcr.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						thcr.remove(p.getUniqueId());
	    	                }
	    	            }, 25); 
	                	thcrt.put(p.getUniqueId(), task);
	                    
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1, 0);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1, 2);
	                    for(int i =0; i<8; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                    p.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, l, 200, 4,1,4,0.3);
					                    p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l, 200, 4,1,4,0.3);
										p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 0.8f, 2);
										for (Entity a : p.getWorld().getNearbyEntities(l, 4, 4, 4))
										{
											if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
											{
												LivingEntity le = (LivingEntity)a;
							                    
												if (le instanceof Player) 
				    							{
				    								
				    								Player p1 = (Player) le;
				    								if(Party.hasParty(p) && Party.hasParty(p1))	{
				    								if(Party.getParty(p).equals(Party.getParty(p1)))
				    									{
				    										continue;
				    									}
				    								}
				    							}
												
							                    atk0(0.1, esd.Electrostatic.get(p.getUniqueId())*0.078, p, le);
												Holding.holding(p, le, 40l);
												/*
												if(le instanceof EnderDragon) {
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
														ar.setShooter(p);
														ar.setCritical(false);
														ar.setSilent(true);
														ar.setPickupStatus(PickupStatus.DISALLOWED);
														ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(player_damage.get(p.getName())*0.09 + esd.Electrostatic.get(p.getUniqueId())*0.078);								
												}
												p.setCooldown(Material.YELLOW_TERRACOTTA, 1);
												le.damage(0, p);
												le.damage(player_damage.get(p.getName())*0.09 + esd.Electrostatic.get(p.getUniqueId())*0.078, p);
												*/
											}
										}
					                }
		                	   }, i*4); 
						}

						frcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	                if(Proficiency.getpro(p)>=1) {
	                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                }
	                
                	if(thcrt.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(thcrt.get(p.getUniqueId()));
                		thcrt.remove(p.getUniqueId());
                	}

    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						if(Proficiency.getpro(p)>=1) {
    							thcr.putIfAbsent(p.getUniqueId(), 0);
    						}
    	                }
    	            }, 3); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						thcr.remove(p.getUniqueId());
    	                }
    	            }, 25); 
                	thcrt.put(p.getUniqueId(), task);
                	
					l.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1, 0);
					l.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1, 2);
                    for(int i =0; i<8; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    l.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, l, 200, 4,1,4,0.3);
				                    l.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l, 200, 4,1,4,0.3);
									l.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 0.8f, 2);
									for (Entity a : l.getWorld().getNearbyEntities(l,4, 4, 4))
									{
										if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
										{
											LivingEntity le = (LivingEntity)a;
											if (le instanceof Player) 
			    							{
			    								
			    								Player p1 = (Player) le;
			    								if(Party.hasParty(p) && Party.hasParty(p1))	{
			    								if(Party.getParty(p).equals(Party.getParty(p1)))
			    									{
			    										continue;
			    									}
			    								}
			    							}

						                    atk0(0.1, esd.Electrostatic.get(p.getUniqueId())*0.078, p, le);
											Holding.holding(p, le, 40l);
										}
									}
				                }
	                	   }, i*4); 
					}
					frcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	

	
	@SuppressWarnings("deprecation")
	public void ThunderCaller(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&& thcr.containsKey(p.getUniqueId()))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
			{

                if(Proficiency.getpro(p)>=1) {
                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                }
                
            	if(thcrt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(thcrt.get(p.getUniqueId()));
            		thcrt.remove(p.getUniqueId());
            	}
				thcr.remove(p.getUniqueId());
				


            	if(angvt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(angvt.get(p.getUniqueId()));
            		angvt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							angv.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						angv.remove(p.getUniqueId());
	                }
	            }, 25); 
            	angvt.put(p.getUniqueId(), task);
            	
				final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
	                    
						p.getWorld().playSound(l, Sound.BLOCK_BEACON_ACTIVATE, 0.15f, 0);
						p.getWorld().playSound(l, Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, 1, 0);
						
	                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
	                    ItemStack head = new ItemStack(Material.LIGHTNING_ROD);
	                    ItemStack ch = new ItemStack(Material.ELYTRA);
	                    ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
	                    ItemStack boot = new ItemStack(Material.IRON_BOOTS);
	                    ItemStack right = new ItemStack(Material.END_ROD);
	                    ItemStack left = new ItemStack(Material.END_ROD);
	                    as.setCustomName(p.getName());
	                    as.setBasePlate(true);
	                    as.setMarker(true);
	                    as.setInvulnerable(true);
	                    as.setInvisible(true);
	                    as.setArms(true);
	                    as.setHelmet(head);
	                    as.setChestplate(ch);
	                    as.setLeggings(leg);
	                    as.setBoots(boot);
	                    as.getEquipment().setItemInMainHand(left);
	                    as.getEquipment().setItemInOffHand(right);
	                    as.setCanPickupItems(false);
	                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						if(p.isDead()) {
							as.remove();
						}
						
	                    for(int i =1; i<20; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	as.getWorld().spawnParticle(Particle.FLASH, l, 10, 2,2,2);
					                	as.getWorld().playSound(l, Sound.BLOCK_BEACON_ACTIVATE, 0.8f, 2);
					                }
		                	   }, i*3); 
						}
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	as.remove();
									for (Entity a : l.getWorld().getNearbyEntities(l, 5, 10, 5))
									{
										if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
										{
											LivingEntity le = (LivingEntity)a;
											if (le instanceof Player) 
			    							{
			    								
			    								Player p1 = (Player) le;
			    								if(Party.hasParty(p) && Party.hasParty(p1))	{
			    								if(Party.getParty(p).equals(Party.getParty(p1)))
			    									{
			    										continue;
			    									}
			    								}
			    							}
											
						                    atk0(2.1, esd.Electrostatic.get(p.getUniqueId())*2.0, p, le);
						                    le.getWorld().strikeLightningEffect(le.getLocation());
						                    
						                    /*
											Holding.holding(p, le, 40l);
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
													ar.setShooter(p);
													ar.setCritical(false);
													ar.setSilent(true);
													ar.setPickupStatus(PickupStatus.DISALLOWED);
													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*3 + esd.Electrostatic.get(p.getUniqueId())*3);								
											}
											p.setCooldown(Material.YELLOW_TERRACOTTA, 1);
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*3 + esd.Electrostatic.get(p.getUniqueId())*3, p);
											*/
										}
									}
				                }
	                	   }, 60); 
				}
			}
		}
	}
	
	
	public void Anti_gravity(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&& angv.containsKey(p.getUniqueId()))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
			{

                if(Proficiency.getpro(p)>=1) {
                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                }
                
            	if(angvt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(angvt.get(p.getUniqueId()));
            		angvt.remove(p.getUniqueId());
            	}
				angv.remove(p.getUniqueId());

				final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();

					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 0);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 0.5f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 0.7f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 0.9f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 1.1f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 1.4f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 1.8f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 2f);

                    p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, l, 200, 5,1,5);
                    p.getWorld().spawnParticle(Particle.WARPED_SPORE, l, 400, 5,1,5);
                    
					for (Entity a : p.getWorld().getNearbyEntities(l, 5, 3, 5))
					{
						if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
						{
							LivingEntity le = (LivingEntity)a;
							if (le instanceof Player) 
							{
								Player p1 = (Player) le;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										continue;
									}
								}
							}
							
							Holding.holding(p, le, 20l);
							for(int h=0; h<3; h++) {
								if(le.getLocation().clone().add(0, 1, 0).getBlock().isPassable()) {
									le.teleport(le.getLocation().clone().add(0, 1, 0));
								}
								else {
									break;
								}
							}
							
						}
					}
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    l.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, l.clone().add(0,3,0), 200, 5,1,5);
				                    l.getWorld().spawnParticle(Particle.WARPED_SPORE, l.clone().add(0,3,0), 400, 5,1,5);
				                    l.getWorld().spawnParticle(Particle.END_ROD, l.clone().add(0,3,0), 400, 5,1,5);
				                    
									l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 0);
									l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 0.5f);
									l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 0.7f);
									l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 0.9f);
									l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 1.1f);
									l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 1.4f);
									l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 1.8f);
									l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 2f);
									for (Entity a : l.getWorld().getNearbyEntities(l, 5, 10, 5))
									{
										if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
										{
											LivingEntity le = (LivingEntity)a;
											if (le instanceof Player) 
			    							{
			    								Player p1 = (Player) le;
			    								if(Party.hasParty(p) && Party.hasParty(p1))	{
			    								if(Party.getParty(p).equals(Party.getParty(p1)))
			    									{
			    										continue;
			    									}
			    								}
			    							}
											le.setFallDistance(10);
											for(int h=0; h<3; h++) {
												if(le.getLocation().clone().add(0, -1, 0).getBlock().isPassable()) {
													le.teleport(le.getLocation().clone().add(0, -1, 0));
												}
												else {
													break;
												}
											}
						                    atk0(1.1, esd.Electrostatic.get(p.getUniqueId())*1.0, p, le);
										}
									}
				                }
	                	   }, 20); 
				}
			}
		}
	}
	
	
	public void Magnetic(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		Location el =le.getLocation();
        

		double sec =20*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 17 && esd.Magnetic.get(p.getUniqueId())>=1) {
				if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && (p.isSneaking()) && !(p.hasCooldown(Material.YELLOW_TERRACOTTA)))
				{
				if(stcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            double timer = (stcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("자성 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Magnetic").create());
		        		}
			        }
		            else // if timer is done
		            {
		                stcooldown.remove(p.getName()); // removing player from HashMap
		                if(Proficiency.getpro(p)>=1) {
		                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                }
		                
		                Holding.superholding(p, le, 52l);
						p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 0f);
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 50, 1,1,1, Material.IRON_BLOCK.createBlockData());
						for(int i = 0; i<25; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {
			             					le.teleport(p);
											le.getWorld().playSound(le.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 0.5f, 0);
											if(Proficiency.getpro(p)>=1) {
												for (Entity a : p.getNearbyEntities(2, 2, 2))
												{
													if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
													{
														LivingEntity le = (LivingEntity)a;
														if (le instanceof Player) 
						    							{
						    								Player p1 = (Player) le;
						    								if(Party.hasParty(p) && Party.hasParty(p1))	{
						    								if(Party.getParty(p).equals(Party.getParty(p1)))
						    									{
						    										continue;
						    									}
						    								}
						    							}

						             					le.teleport(p);
													}
												}
											}
							            }
				                	   }, i*2); 
							
						}
			            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
	                if(Proficiency.getpro(p)>=1) {
	                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                }
	                

	                Holding.superholding(p, le, 52l);
					p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 0f);
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 50, 1,1,1, Material.IRON_BLOCK.createBlockData());
					for(int i = 0; i<25; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
		             					le.teleport(p);
										le.getWorld().playSound(le.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 0.5f, 0);
										if(Proficiency.getpro(p)>=1) {
											for (Entity a : p.getNearbyEntities(2, 2, 2))
											{
												if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
												{
													LivingEntity le = (LivingEntity)a;
													if (le instanceof Player) 
					    							{
					    								Player p1 = (Player) le;
					    								if(Party.hasParty(p) && Party.hasParty(p1))	{
					    								if(Party.getParty(p).equals(Party.getParty(p1)))
					    									{
					    										continue;
					    									}
					    								}
					    							}

					             					le.teleport(p);
												}
											}
										}
						            }
			                	   }, i*2); 
						
					}
		            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
		}
	}
	

	
	@SuppressWarnings("deprecation")
	public void Jetpack(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

        
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 17 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && esd.Jetpack.get(p.getUniqueId())>=1) {
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)&& p.getCooldown(Material.STRUCTURE_VOID) <=0)
			{
				ev.setCancelled(true);
				if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            double timer = (smcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("제트팩 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Jetpack").create());
		        		}
			        }
		            else // if timer is done
		            {
		                smcooldown.remove(p.getName()); // removing player from HashMapLocation dam = p.getLocation();
		                if(Proficiency.getpro(p)>=1) {
		                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
		                }
		                
	                	if(proplt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(proplt.get(p.getUniqueId()));
	                		proplt.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							propl.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						propl.remove(p.getUniqueId());
	    	                }
	    	            }, 25); 
	                	proplt.put(p.getUniqueId(), task);
	                	
			        	p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, p.getLocation(), 100, 1, 2, 1);
		                p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 0);
	                	p.setVelocity(BlockFace.UP.getDirection().normalize().multiply(1.5));
			            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
	                if(Proficiency.getpro(p)>=1) {
	                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
	                }
	                
                	if(proplt.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(proplt.get(p.getUniqueId()));
                		proplt.remove(p.getUniqueId());
                	}

    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						if(Proficiency.getpro(p)>=1) {
    							propl.putIfAbsent(p.getUniqueId(), 0);
    						}
    	                }
    	            }, 3); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						propl.remove(p.getUniqueId());
    	                }
    	            }, 25); 
                	proplt.put(p.getUniqueId(), task);
                	
		        	p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, p.getLocation(), 100, 1, 2, 1);
	                p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 0);
                	p.setVelocity(BlockFace.UP.getDirection().normalize().multiply(1.5));
		            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
	}

	
	@SuppressWarnings("deprecation")
	public void Propellant(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 17 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && esd.Jetpack.get(p.getUniqueId())>=1) {
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) &&(ac!= Action.RIGHT_CLICK_AIR)&&(ac!= Action.RIGHT_CLICK_AIR) && propl.containsKey(p.getUniqueId()))
			{
                if(Proficiency.getpro(p)>=1) {
                	sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                	gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
                }
                
            	if(proplt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(proplt.get(p.getUniqueId()));
            		proplt.remove(p.getUniqueId());
            	}
				propl.remove(p.getUniqueId());

	        	p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 10, 1, 2, 1);
                p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 2);
                p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 2);
                p.removePotionEffect(PotionEffectType.LEVITATION);
				for(int h=0; h<70; h++) {
					if(p.getLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(0.1)).getBlock().isPassable()) {
			        	p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 3, 1, 2, 1);
						p.teleport(p.getLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(0.1)));
					}
					else {
						break;
					}
				}
			}
		}
	}

	
	@SuppressWarnings("deprecation")
	public void Jetpack(EntityDamageEvent d) 
	{		
        if (!(d.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) d.getEntity();
        
		

        if(ClassData.pc.get(p.getUniqueId()) == 17 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && esd.Jetpack.get(p.getUniqueId())>=1) {
			if(d.getCause().equals(DamageCause.FALL)) 
			{
		        	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 65*(int)p.getFallDistance(), p.getFallDistance(), 1, p.getFallDistance(), p.getLocation().add(0, -1, 0).getBlock().getBlockData());
		        	for(Entity e: p.getNearbyEntities(p.getFallDistance()/2, 2, p.getFallDistance()/2)) {
		        		if(e instanceof LivingEntity) {
		        			LivingEntity le = (LivingEntity)e;
							if (le instanceof Player) 
							{
								Player p1 = (Player) le;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										continue;
									}
								}
							}
							atk1(0.03*p.getFallDistance(), p, le);
		        			
		        		}
		        	}
		        	d.setCancelled(true);
			}
			if(d.getCause() != DamageCause.VOID && d.getDamage() >= p.getMaxHealth()*0.20) {
		        	d.setDamage(p.getMaxHealth()*0.20);
				
			}
        }
	}

	
	final private void Ray(ArmorStand as, LivingEntity le) {
		final World snw = as.getWorld();
		final Location lel = le.getEyeLocation().clone();
		final Location snl = as.getEyeLocation().clone().add(0, 1, 0);
		final Vector v = lel.clone().toVector().subtract(snl.clone().toVector());
		final double dis = lel.distance(snl);
		
		HashSet<Location> line = new HashSet<>();
		for(double d = 0; d<dis; d += 0.2) {
			line.add(snl.clone().add(v.clone().normalize().multiply(d)));
		}
		line.forEach(l -> {
			snw.spawnParticle(Particle.BLOCK_CRACK, l, 1, 0.3,0.3,0.3,0, Material.PURPLE_GLAZED_TERRACOTTA.createBlockData());
			snw.spawnParticle(Particle.FLASH, l, 1);
			snw.spawnParticle(Particle.GLOW, l, 1, 0.1,0.1,0.1);
		});
	}

	
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
		
		
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 17 && ((is.getType().name().contains("PICKAXE"))) && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				p.setCooldown(Material.STRUCTURE_VOID, 2);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sultcooldown.get(p.getName())/1000d + 60/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("감마선 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Gamma Ray").create());
		        		}
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap

						Holding.invur(p, 20l);
						
			            final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation().clone();
	            		ArmorStand as = tl.getWorld().spawn(tl, ArmorStand.class);
	            		
	            		as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	            		as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	            		as.setInvisible(true);
	            		as.setMarker(true);
	            		as.getEquipment().setHelmet(new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA));
	            		as.setInvulnerable(true);
	                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            		@Override
	         				public void run() 
			                {	
	         					as.remove();
			                }
	                	},40);
	                    
	                    ArrayList<Location> line = new ArrayList<>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,20,0,false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20,4,false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20,4,false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,20,4,false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,20,4,false,false));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 0);
	                    for(double ang = -Math.PI/3; ang<Math.PI/3; ang += Math.PI/90) {
	                    	for(double point = 0.1; point<16.1; point+=0.5) {
	                    		line.add(p.getEyeLocation().add(p.getEyeLocation().getDirection().normalize().rotateAroundY(ang).multiply(point)));
	                    	}
	                    }
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
								p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1, 2);
						            }
			                	   }, 20); 
	                    
	                    line.forEach(l -> {
         					l.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 1, 0.3,0.3,0.3,0, Material.PURPLE_GLAZED_TERRACOTTA.createBlockData());
         					l.getWorld().spawnParticle(Particle.FLASH, l, 1, 0.3,0.3,0.3);
         					for(Entity e: l.getWorld().getNearbyEntities(l,3,5,3)) {
         						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) {
         							LivingEntity le = (LivingEntity)e;
									if (le instanceof Player) 
	    							{
	    								Player p1 = (Player) le;
	    								if(Party.hasParty(p) && Party.hasParty(p1))	{
	    								if(Party.getParty(p).equals(Party.getParty(p1)))
	    									{
	    										continue;
	    									}
	    								}
	    							}
         							Holding.holding(p, le, 80l);
         							les.add(le);
         						}
         					}
	                    	
	                    });
	                    for(int i =0; i<5; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
				                {
									p.getWorld().playSound(as.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 0.1f, 2);
									p.getWorld().playSound(as.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 0.1f, 2);
									p.getWorld().playSound(as.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.1f, 1.6f);
				                    for(LivingEntity le: les) {
				                    	Ray(as,le);
				                    	atk1(2.2, p, le);
			 		                    le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,100,4,false,false));
	         							Holding.holding(p, le, 15l);
			 								                    	
				                    }
					            }
	                	   }, i*10+20); 
	                    }

		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

					Holding.invur(p, 20l);

		            final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation().clone();
            		ArmorStand as = tl.getWorld().spawn(tl, ArmorStand.class);
            		
            		as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
            		as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
            		as.setInvisible(true);
            		as.setMarker(true);
            		as.getEquipment().setHelmet(new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA));
            		as.setInvulnerable(true);
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            		@Override
         				public void run() 
		                {	
         					as.remove();
		                }
                	},40);
                    
                    ArrayList<Location> line = new ArrayList<>();
                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,20,0,false,false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20,4,false,false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20,4,false,false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,20,4,false,false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,20,4,false,false));
					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 0);
                    for(double ang = -Math.PI/3; ang<Math.PI/3; ang += Math.PI/90) {
                    	for(double point = 0.1; point<16.1; point+=0.5) {
                    		line.add(p.getEyeLocation().add(p.getEyeLocation().getDirection().normalize().rotateAroundY(ang).multiply(point)));
                    	}
                    }
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {
							p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1, 2);
					            }
		                	   }, 20); 
                    
                    line.forEach(l -> {
     					l.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 1, 0.3,0.3,0.3,0, Material.PURPLE_GLAZED_TERRACOTTA.createBlockData());
     					l.getWorld().spawnParticle(Particle.FLASH, l, 1, 0.3,0.3,0.3);
     					for(Entity e: l.getWorld().getNearbyEntities(l,3,5,3)) {
     						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) {
     							LivingEntity le = (LivingEntity)e;
								if (le instanceof Player) 
    							{
    								Player p1 = (Player) le;
    								if(Party.hasParty(p) && Party.hasParty(p1))	{
    								if(Party.getParty(p).equals(Party.getParty(p1)))
    									{
    										continue;
    									}
    								}
    							}
     							Holding.holding(p, le, 80l);
     							les.add(le);
     						}
     					}
                    	
                    });
                    for(int i =0; i<5; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
		                	public void run() 
			                {
								p.getWorld().playSound(as.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 0.1f, 2);
								p.getWorld().playSound(as.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 0.1f, 2);
								p.getWorld().playSound(as.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.1f, 1.6f);
			                    for(LivingEntity le: les) {
			                    	Ray(as,le);
			                    	atk1(2.2, p, le);
		 		                    le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,100,4,false,false));
         							Holding.holding(p, le, 15l);
		 								                    	
			                    }
				            }
                	   }, i*10+20); 
                    }
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	final private void BlackHole(Player p, LivingEntity le, World tlw, Location tl) {

    	tlw.spawnParticle(Particle.VIBRATION, le.getLocation(), 20, 3,3,3, new Vibration(le.getLocation(), new Destination.BlockDestination(tl), 10));
    	tlw.spawnParticle(Particle.VIBRATION, le.getLocation(), 20, 3,3,3, new Vibration(le.getLocation(), new Destination.BlockDestination(tl), 20));
    	tlw.spawnParticle(Particle.VIBRATION, le.getLocation(), 20, 3,3,3, new Vibration(le.getLocation(), new Destination.BlockDestination(tl), 40));
    	tlw.spawnParticle(Particle.VIBRATION, le.getLocation(), 20, 3,3,3, new Vibration(le.getLocation(), new Destination.BlockDestination(tl), 80));
    	tlw.spawnParticle(Particle.VIBRATION, le.getLocation(), 20, 3,3,3, new Vibration(le.getLocation(), new Destination.BlockDestination(tl), 160));
	}
	

	
	public void ULT2(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
		
		
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 17 && ((is.getType().name().contains("PICKAXE"))) && !p.isSneaking()&& p.isSprinting() && Proficiency.getpro(p) >=2)
			{
				p.setCooldown(Material.STRUCTURE_VOID, 9);
				ev.setCancelled(true);
				if(sult2cooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sult2cooldown.get(p.getName())/1000d + 50*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("블랙홀 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use BlackHole").create());
		        		}
	                }
	                else // if timer is done
	                {
	                    sult2cooldown.remove(p.getName()); // removing player from HashMap
	                    
						Holding.invur(p, 120l);
							
	    				final Location tl = gettargetblock(p,2);
	    				final World tlw = tl.getWorld();
	    				
	                    ArrayList<Location> line = new ArrayList<>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    for(double ang = -Math.PI/3; ang<Math.PI/3; ang += Math.PI/60) {
	                    	for(double point = 0.1; point<30.1; point+=0.5) {
	                    		line.add(tl.clone().add(p.getLocation().getDirection().normalize().rotateAroundY(ang).multiply(point)));
	                    	}
	                    }
	                    
	                    line.forEach(l -> {
							tlw.spawnParticle(Particle.REVERSE_PORTAL, l, 10, 1,1,1,0);
							tlw.spawnParticle(Particle.SHRIEK, l, 1, 1,1,1,0,10);
	     					for(Entity e: l.getWorld().getNearbyEntities(l,4,7,4)) {
	     						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) {
	     							LivingEntity le = (LivingEntity)e;
									if (le instanceof Player) 
	    							{
	    								Player p1 = (Player) le;
	    								if(Party.hasParty(p) && Party.hasParty(p1))	{
	    								if(Party.getParty(p).equals(Party.getParty(p1)))
	    									{
	    										continue;
	    									}
	    								}
	    							}
	     							Holding.holding(p, le, 120l);
	     							les.add(le);
	     						}
	     					}
	                    	
	                    });
						tlw.playSound(tl, Sound.AMBIENT_CAVE, 1f, 2);
						tlw.playSound(tl, Sound.BLOCK_END_GATEWAY_SPAWN, 1f, 0f);

	                    for(int i =1; i<20; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	p.playSound(tl, Sound.BLOCK_BEACON_DEACTIVATE, 0.1f, 2);
					                	p.playSound(tl, Sound.BLOCK_PORTAL_TRIGGER, 0.1f, 2);
					                	tlw.spawnParticle(Particle.PORTAL, tl, 100, 1,1,1,0);
					                	tlw.spawnParticle(Particle.REVERSE_PORTAL, tl, 100, 1,1,1,0);
					                	tlw.spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, tl, 200, 1,1,1,0);
					                    for(LivingEntity le: les) {
					                    	BlackHole(p,le,tlw,tl);
	        							}
					                }
		                	   }, i*5); 
						}
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
					                    for(LivingEntity le: les) {
				 							le.teleport(tl);               	
					                    }
						            }
			                	   }, 100); 
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
		             				p.playSound(tl, Sound.BLOCK_GLASS_BREAK, 1, 0);
		             				p.playSound(tl, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
		             				p.playSound(tl, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 0);
		             				p.playSound(tl, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 0);
		             				p.playSound(tl, Sound.BLOCK_END_PORTAL_FRAME_FILL, 1, 0);
		             				p.playSound(tl, Sound.ITEM_TRIDENT_THUNDER, 0.8f, 0);
									tlw.spawnParticle(Particle.ENCHANTMENT_TABLE, tl, 2000, 3,3,3,1);
									tlw.spawnParticle(Particle.END_ROD, tl, 500, 3,3,3,1);

					                    for(LivingEntity le: les) {
					                    	atk1(16.4, p, le);
				 							le.teleport(tl);
				 								                    	
					                    }
						            }
			                	   }, 120); 
		                sult2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

					Holding.invur(p, 120l);
    				final Location tl = gettargetblock(p,2);
    				final World tlw = tl.getWorld();
    				
                    ArrayList<Location> line = new ArrayList<>();
                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(double ang = -Math.PI/3; ang<Math.PI/3; ang += Math.PI/60) {
                    	for(double point = 0.1; point<30.1; point+=0.5) {
                    		line.add(tl.clone().add(p.getLocation().getDirection().normalize().rotateAroundY(ang).multiply(point)));
                    	}
                    }
                    
                    line.forEach(l -> {
						tlw.spawnParticle(Particle.REVERSE_PORTAL, l, 10, 1,1,1,0);
						tlw.spawnParticle(Particle.SHRIEK, l, 1, 1,1,1,0,10);
     					for(Entity e: l.getWorld().getNearbyEntities(l,4,7,4)) {
     						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) {
     							LivingEntity le = (LivingEntity)e;
								if (le instanceof Player) 
    							{
    								Player p1 = (Player) le;
    								if(Party.hasParty(p) && Party.hasParty(p1))	{
    								if(Party.getParty(p).equals(Party.getParty(p1)))
    									{
    										continue;
    									}
    								}
    							}
     							Holding.holding(p, le, 120l);
     							les.add(le);
     						}
     					}
                    	
                    });
					tlw.playSound(tl, Sound.AMBIENT_CAVE, 1f, 2);
					tlw.playSound(tl, Sound.BLOCK_END_GATEWAY_SPAWN, 1f, 0f);

                    for(int i =1; i<20; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	p.playSound(tl, Sound.BLOCK_BEACON_DEACTIVATE, 0.1f, 2);
				                	p.playSound(tl, Sound.BLOCK_PORTAL_TRIGGER, 0.1f, 2);
				                	tlw.spawnParticle(Particle.PORTAL, tl, 100, 1,1,1,0);
				                	tlw.spawnParticle(Particle.REVERSE_PORTAL, tl, 100, 1,1,1,0);
				                	tlw.spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, tl, 200, 1,1,1,0);
				                    for(LivingEntity le: les) {
				                    	BlackHole(p,le,tlw,tl);
        							}
				                }
	                	   }, i*5); 
					}
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {
				                    for(LivingEntity le: les) {
			 							le.teleport(tl);               	
				                    }
					            }
		                	   }, 100); 
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {
	             				p.playSound(tl, Sound.BLOCK_GLASS_BREAK, 1, 0);
	             				p.playSound(tl, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
	             				p.playSound(tl, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 0);
	             				p.playSound(tl, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 0);
	             				p.playSound(tl, Sound.BLOCK_END_PORTAL_FRAME_FILL, 1, 0);
	             				p.playSound(tl, Sound.ITEM_TRIDENT_THUNDER, 0.8f, 0);
								tlw.spawnParticle(Particle.ENCHANTMENT_TABLE, tl, 2000, 3,3,3,1);
								tlw.spawnParticle(Particle.END_ROD, tl, 500, 3,3,3,1);

				                    for(LivingEntity le: les) {
				                    	atk1(16.4, p, le);
			 							le.teleport(tl);
			 								                    	
				                    }
					            }
		                	   }, 120); 
	                sult2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	

	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 17 && ((is.getType().name().contains("PICKAXE"))) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
        

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 17)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") )
			{
					e.setCancelled(true);
			}
		}
		
	}
	
	
	public void CombatSuit(EntityDamageByEntityEvent d) 
	{		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity) d.getEntity();
        
		

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
					dset2(d, p, 1.2*(1+esd.CombatSuit.get(p.getUniqueId())*0.03935), le, 9);
				}
			}
		}

		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();

				if(ClassData.pc.get(p.getUniqueId()) == 17) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
						dset2(d, p, 1.2*(1+esd.CombatSuit.get(p.getUniqueId())*0.03935), le, 9);
					}
				}
			}
		}
	}
}



