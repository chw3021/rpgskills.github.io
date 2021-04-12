package io.github.chw3021.engineer;



import io.github.chw3021.classes.ClassData;
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
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
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
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
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

public class Engskills implements Listener, Serializable {
	
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
	private HashMap<LivingEntity, Integer> xray = new HashMap<LivingEntity, Integer>();
	private HashMap<LivingEntity, Player> xrayp = new HashMap<LivingEntity, Player>();
	private HashMap<Player, Integer> jet = new HashMap<Player, Integer>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private EngSkillsData esd = new EngSkillsData(EngSkillsData.loadData(path +"/plugins/RPGskills/EngSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		EngSkillsData e = new EngSkillsData(EngSkillsData.loadData(path +"/plugins/RPGskills/EngSkillsData.data"));
		esd = e;
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

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		EngSkillsData e = new EngSkillsData(EngSkillsData.loadData(path +"/plugins/RPGskills/EngSkillsData.data"));
		esd = e;
		
	}
	
	@EventHandler
	public void Dispenser(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 6;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 17)
		{
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking())
			{
				ev.setCancelled(true);
				
				
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Dispenser").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.remove();
	                    Location asl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().setDirection(p.getLocation().getDirection());
	                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(asl, EntityType.ARMOR_STAND);
	                    ItemStack head = new ItemStack(Material.DISPENSER);
	                    ItemStack ch = new ItemStack(Material.ELYTRA);
	                    ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
	                    ItemStack boot = new ItemStack(Material.IRON_BOOTS);
	                    ItemStack right = new ItemStack(Material.DROPPER);
	                    ItemStack left = new ItemStack(Material.OBSERVER);
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
	                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 1, 0);
						if(p.isDead()) {
							as.remove();
						}
	                    for(int i = 0; i<20; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	for(Entity e : as.getWorld().getNearbyEntities(as.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation(), 6, 6, 6)) {
										if (e instanceof Player) 
										{
											PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
				                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
				                			LivingEntity le = (LivingEntity)e;
				                			Snowball sn = as.launchProjectile(Snowball.class, le.getLocation().toVector().subtract(as.getEyeLocation().toVector()).multiply(2));
				                			sn.setShooter(p);
				                			sn.setBounce(false);
				    	                    sn.setMetadata("dispensed", new FixedMetadataValue(RMain.getInstance(), true));
				    						p.setSprinting(true);
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
													a.setShooter(p);
													a.setCritical(false);
													a.setSilent(true);
													a.setPickupStatus(PickupStatus.DISALLOWED);
													a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.06+esd.Dispenser.get(p.getUniqueId())*0.06);								
											}
				    						le.damage(0, p);
				    						le.damage(player_damage.get(p.getName())*0.06+esd.Dispenser.get(p.getUniqueId())*0.06, p);
				    						p.setSprinting(false);
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
	            	Location asl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().setDirection(p.getLocation().getDirection());
                    Arrow firstarrow = p.launchProjectile(Arrow.class);
                    firstarrow.setDamage(0);
                    firstarrow.remove();
                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(asl, EntityType.ARMOR_STAND);
                    ItemStack head = new ItemStack(Material.DISPENSER);
                    ItemStack ch = new ItemStack(Material.ELYTRA);
                    ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
                    ItemStack boot = new ItemStack(Material.IRON_BOOTS);
                    ItemStack right = new ItemStack(Material.DROPPER);
                    ItemStack left = new ItemStack(Material.OBSERVER);
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
                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 1, 0);
					if(p.isDead()) {
						as.remove();
					}
                    for(int i = 0; i<20; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	for(Entity e : as.getWorld().getNearbyEntities(as.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation(), 6, 6, 6)) {
									if (e instanceof Player) 
									{
										PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
			                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p) {
			                			LivingEntity le = (LivingEntity)e;
			                			Snowball sn = as.launchProjectile(Snowball.class, le.getLocation().toVector().subtract(as.getEyeLocation().toVector()).multiply(2));
			                			sn.setShooter(p);
			                			sn.setBounce(false);
			    	                    sn.setMetadata("dispensed", new FixedMetadataValue(RMain.getInstance(), true));
			    						p.setSprinting(true);
										if(le instanceof EnderDragon) {
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
												a.setShooter(p);
												a.setCritical(false);
												a.setSilent(true);
												a.setPickupStatus(PickupStatus.DISALLOWED);
												a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.06+esd.Dispenser.get(p.getUniqueId())*0.06);								
										}
			    						le.damage(0, p);
			    						le.damage(player_damage.get(p.getName())*0.06+esd.Dispenser.get(p.getUniqueId())*0.06, p);
			    						p.setSprinting(false);
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



	@EventHandler
	public void X_ray(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 5;

        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 17) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
			{

				if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use X_ray").create());
	                }
	                else // if timer is done
	                {
	                    gdcooldown.remove(p.getName()); // removing player from HashMap
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    ArrayList<Location> pie = new ArrayList<Location>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    AtomicInteger j = new AtomicInteger(0);
	                    for(double d = -Math.PI/6; d<= Math.PI/6; d += Math.PI/180) {
		                    Location l = p.getLocation();
		                    l.setDirection(l.getDirection().normalize().rotateAroundY(d));
		                    l.add(l.getDirection().normalize().multiply(6.1));
		                    line.add(l);
	                    	
	                    }
	                    for(double d = 0.1; d <= 6.1; d += 1) {
	                    	  for (Location l : line){
			                    Location pl = p.getLocation();
								Vector ltr = l.toVector().subtract(pl.toVector());
								pl.add(ltr.normalize().multiply(d));
								pie.add(pl);
								}
	                    }
	                    
	                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 2);
	                    pie.forEach(i -> {
							p.getWorld().spawnParticle(Particle.FLASH, i.add(0, -0.35, 0), 1, 0.5,0.5,0.5,0);
						}); 
	                    for(Location l : pie) {

	                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
							{
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
								{
									if (a instanceof Player) 
									{
										PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
	                    for(LivingEntity le: les) 
						{
							p.setSprinting(true);
							le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
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
								enar.setDamage(player_damage.get(p.getName())*0.3+esd.X_ray.get(p.getUniqueId())*0.23);								
							}
							le.damage(0, p);
							le.damage(player_damage.get(p.getName())*0.31+esd.X_ray.get(p.getUniqueId())*0.23, p);
							hold.holding(p, le, (long) 30);
							p.setSprinting(false);
	                		if(xray.containsKey(le)) {
	                			xray.computeIfPresent(le, (k,v) -> v+1);
							}
							else {
								xray.put(le, 0);
								xrayp.put(le, p);
								le.setGlowing(true);
							}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	xray.computeIfPresent(le, (k, v) -> v-1);
				                	if(xray.get(le)<0) {
				                		xray.remove(le);
				                		xrayp.remove(le);
										le.setGlowing(false);
				                	}
				                }
							}, 50);
						}
						gdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

                    ArrayList<Location> line = new ArrayList<Location>();
                    ArrayList<Location> pie = new ArrayList<Location>();
                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    AtomicInteger j = new AtomicInteger(0);
                    for(double d = -Math.PI/6; d<= Math.PI/6; d += Math.PI/180) {
	                    Location l = p.getLocation();
	                    l.setDirection(l.getDirection().normalize().rotateAroundY(d));
	                    l.add(l.getDirection().normalize().multiply(6.1));
	                    line.add(l);
                    	
                    }
                    for(double d = 0.1; d <= 6.1; d += 1) {
                    	  for (Location l : line){
		                    Location pl = p.getLocation();
							Vector ltr = l.toVector().subtract(pl.toVector());
							pl.add(ltr.normalize().multiply(d));
							pie.add(pl);
							}
                    }
                    
                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 2);
                    pie.forEach(i -> {
							p.getWorld().spawnParticle(Particle.FLASH, i.add(0, -0.35, 0), 1, 0.5,0.5,0.5,0);
					}); 
                    for(Location l : pie) {

                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
						{
							if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
							{
								if (a instanceof Player) 
								{
									PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
                    for(LivingEntity le: les) 
					{
						p.setSprinting(true);
						le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
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
							enar.setDamage(player_damage.get(p.getName())*0.3+esd.X_ray.get(p.getUniqueId())*0.23);								
						}
						le.damage(0, p);
						le.damage(player_damage.get(p.getName())*0.31+esd.X_ray.get(p.getUniqueId())*0.23, p);
						hold.holding(p, le, (long) 30);
						p.setSprinting(false);
                		if(xray.containsKey(le)) {
                			xray.computeIfPresent(le, (k,v) -> v+1);
						}
						else {
							xray.put(le, 0);
							xrayp.put(le, p);
							le.setGlowing(true);
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	xray.computeIfPresent(le, (k, v) -> v-1);
			                	if(xray.get(le)<0) {
			                		xray.remove(le);
			                		xrayp.remove(le);
									le.setGlowing(false);
			                	}
			                }
						}, 50);
					}
					gdcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}

	@EventHandler
	public void X_ray(EntityDamageByEntityEvent d) 
	{		
		if(d.getEntity() instanceof LivingEntity) 
		{
		LivingEntity le = (LivingEntity)d.getEntity();
	        if(xray.containsKey(le)) {
	        	d.setDamage(d.getDamage()*1.5);
	        }
		}
	}
	
	
	@EventHandler
	public void Graviton(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		int sec = 6;
		
		if(playerclass.get(p.getUniqueId()) == 17) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking())
			{
				ev.setCancelled(true);
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Graviton").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 2f);
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
							EnderCrystal graviton = (EnderCrystal) p.getWorld().spawnEntity(l, EntityType.ENDER_CRYSTAL);
							graviton.setInvulnerable(true);
							graviton.setShowingBottom(false);
							graviton.setMetadata("graviton of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							graviton.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							graviton.setGlowing(true);
		                    Arrow firstarrow = p.launchProjectile(Arrow.class);
		                    firstarrow.setDamage(0);
		                    firstarrow.remove();
	                    	for(int i =1; i<30; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	graviton.setBeamTarget(p.getLocation().add(0, -0.5, 0));
						                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 0.5f, 2.0f);
						                	for (Entity e : p.getWorld().getNearbyEntities(l, 5, 5, 5))
											{
					                    		if (e instanceof Player) 
												{
													PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
																Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
																	a.setShooter(p);
																	a.setCritical(false);
																	a.setSilent(true);
																	a.setPickupStatus(PickupStatus.DISALLOWED);
																	a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												                    le.teleport(l.clone().add(0, 1.23, 0));
																});
																enar.setDamage(player_damage.get(p.getName())*0.045 + esd.Graviton.get(p.getUniqueId())*0.033);								
															}
															else {
											                    le.teleport(l);																
															}
															p.setSprinting(true);
										                    le.damage(0,p);
										                    le.damage(player_damage.get(p.getName())*0.045 + esd.Graviton.get(p.getUniqueId())*0.033,p);
															p.setSprinting(false);
															hold.holding(p, le, 2l);
																
														}
												}
											}
						                }
			                	   }, i*2); 
							}
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	Stream<Entity> grav = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("graviton of"+p.getName()));
				                	grav.forEach(i -> i.remove());
				                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
					        		p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1,1,1,1);
				                	for (Entity e : p.getWorld().getNearbyEntities(l, 5, 5, 5))
									{
			                    		if (e instanceof Player) 
										{
											PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
														Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
															a.setShooter(p);
															a.setCritical(false);
															a.setSilent(true);
															a.setPickupStatus(PickupStatus.DISALLOWED);
															a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
														});
														enar.setDamage(player_damage.get(p.getName())*0.3+esd.Graviton.get(p.getUniqueId())*0.3);								
													}
													p.setSprinting(true);
								                    le.damage(0,p);
								                    le.damage(player_damage.get(p.getName())*0.3+esd.Graviton.get(p.getUniqueId())*0.3,p);
								                    le.playEffect(EntityEffect.HURT_EXPLOSION);
													p.setSprinting(false);
														
												}
										}
									}
				                }
	                    	}, 62); 
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 2f);
	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.remove();
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
						EnderCrystal graviton = (EnderCrystal) p.getWorld().spawnEntity(l, EntityType.ENDER_CRYSTAL);
						graviton.setInvulnerable(true);
						graviton.setShowingBottom(false);
						graviton.setInvulnerable(true);
						graviton.setMetadata("graviton of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						graviton.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						graviton.setGlowing(true);
                    	for(int i =1; i<30; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	graviton.setBeamTarget(p.getLocation().add(0, -0.5, 0));
					                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 0.5f, 2.0f);
					                	for (Entity e : p.getWorld().getNearbyEntities(l, 5, 5, 5))
										{
				                    		if (e instanceof Player) 
											{
												PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
															Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
																a.setShooter(p);
																a.setCritical(false);
																a.setSilent(true);
																a.setPickupStatus(PickupStatus.DISALLOWED);
																a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											                    le.teleport(l.clone().add(0, 1.23, 0));
															});
															enar.setDamage(player_damage.get(p.getName())*0.045 + esd.Graviton.get(p.getUniqueId())*0.033);								
														}
														else {
										                    le.teleport(l);																
														}
														p.setSprinting(true);
									                    le.damage(0,p);
									                    le.damage(player_damage.get(p.getName())*0.045 + esd.Graviton.get(p.getUniqueId())*0.033,p);
														p.setSprinting(false);
														hold.holding(p, le, 2l);
															
													}
											}
										}
					                }
		                	   }, i*2); 
						}
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	Stream<Entity> grav = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("graviton of"+p.getName()));
			                	grav.forEach(i -> i.remove());
			                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
				        		p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1,1,1,1);
			                	for (Entity e : p.getWorld().getNearbyEntities(l, 5, 5, 5))
								{
		                    		if (e instanceof Player) 
									{
										PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
														a.setShooter(p);
														a.setCritical(false);
														a.setSilent(true);
														a.setPickupStatus(PickupStatus.DISALLOWED);
														a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(player_damage.get(p.getName())*0.3+esd.Graviton.get(p.getUniqueId())*0.3);								
												}
												p.setSprinting(true);
							                    le.damage(0,p);
							                    le.damage(player_damage.get(p.getName())*0.3+esd.Graviton.get(p.getUniqueId())*0.3,p);
							                    le.playEffect(EntityEffect.HURT_EXPLOSION);
												p.setSprinting(false);
													
											}
									}
								}
			                }
                    	}, 62); 
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	
	
	@EventHandler
	public void Electrostatic(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 7;

        
		
		
		if(playerclass.get(p.getUniqueId()) == 17) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
			{
				

				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Electrostatic").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.remove();
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1, 0);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1, 2);
	                    for(int i =1; i<9; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                    p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l, 100, 6,2,6);
										p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 0.8f, 2);
										for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 6, 6, 6))
										{
											if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
											{
												LivingEntity le = (LivingEntity)a;
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
												hold.holding(p, le, 40l);
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
												p.setSprinting(true);
												le.getWorld().strikeLightningEffect(le.getLocation());
												le.getWorld().playSound(le.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 0);
												le.damage(0, p);
												le.damage(player_damage.get(p.getName())*0.09 + esd.Electrostatic.get(p.getUniqueId())*0.078, p);
												p.setSprinting(false);
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
	            	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
                    Arrow firstarrow = p.launchProjectile(Arrow.class);
                    firstarrow.setDamage(0);
                    firstarrow.remove();
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1, 2);
					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1, 0);
                    for(int i =1; i<9; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l, 100, 6,2,6);
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 0.8f, 2);
									for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 6, 6, 6))
									{
										if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
										{
											LivingEntity le = (LivingEntity)a;
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
											hold.holding(p, le, 40l);
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
											p.setSprinting(true);
											le.getWorld().strikeLightningEffect(le.getLocation());
											le.getWorld().playSound(le.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 0);
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*0.09 + esd.Electrostatic.get(p.getUniqueId())*0.078, p);
											p.setSprinting(false);
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
	
	@EventHandler
	public void Magnetic(EntityDamageByEntityEvent d) 
	{
		int sec = 7;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		Location l = p.getLocation();
		Location el =le.getLocation();
        
		

		
		
		if(playerclass.get(p.getUniqueId()) == 17 && esd.Magnetic.get(p.getUniqueId())>=1) {
				if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && (p.isSneaking()) && !(p.isSprinting()))
				{
				if(stcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (stcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Magnetic").create());
			        }
		            else // if timer is done
		            {
		                stcooldown.remove(p.getName()); // removing player from HashMap
		                hold.superholding(p, le, 52l);
						p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 0f);
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 50, 1,1,1, Material.IRON_BLOCK.createBlockData());
						for(int i = 0; i<25; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {
			             					le.teleport(p);
											le.getWorld().playSound(le.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 0.5f, 0);
							            }
				                	   }, i*2); 
							
						}
			            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {

	                hold.superholding(p, le, 52l);
					p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 0f);
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 50, 1,1,1, Material.IRON_BLOCK.createBlockData());
					for(int i = 0; i<25; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
		             					le.teleport(p);
										le.getWorld().playSound(le.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 0.5f, 0);
						            }
			                	   }, i*2); 
						
					}
		            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
		}
	}
	

	@EventHandler
	public void Jetpack(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 6;

        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 17 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && esd.Jetpack.get(p.getUniqueId())>=1) {
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) &&(ac!= Action.RIGHT_CLICK_AIR)&&(ac!= Action.RIGHT_CLICK_AIR))
			{
				ev.setCancelled(true);
				if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Jetpack").create());
			        }
		            else // if timer is done
		            {
		                smcooldown.remove(p.getName()); // removing player from HashMapLocation dam = p.getLocation();
			        	p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, p.getLocation(), 100, 1, 2, 1);
		                p.setSneaking(true);
		                p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 0);
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 11, false, false));
		                p.setSneaking(false);
			            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
		        	p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, p.getLocation(), 100, 1, 2, 1);
	                p.setSneaking(true);
	                p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 0);
                	p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 11, false, false));
	                p.setSneaking(false);
		            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
	}
	

	@EventHandler
	public void Jetpack(EntityDamageEvent d) 
	{		
        if (!(d.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) d.getEntity();
        
		
		
		if(d.getCause().equals(DamageCause.FALL)) 
		{
	        if(playerclass.get(p.getUniqueId()) == 17) {
	        	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 65*(int)p.getFallDistance(), p.getFallDistance(), 1, p.getFallDistance(), p.getLocation().add(0, -1, 0).getBlock().getBlockData());
	        	for(Entity e: p.getNearbyEntities(p.getFallDistance()/2, 2, p.getFallDistance()/2)) {
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
							enar.setDamage(player_damage.get(p.getName())*0.03*p.getFallDistance());								
						}
	        			p.setSprinting(true);
	        			le.damage(0,p);
	        			le.damage(player_damage.get(p.getName())*0.03*p.getFallDistance(),p);
	        			p.setSprinting(false);
	        		}
	        	}
	        	d.setCancelled(true);
	        }
		}
		else if(d.getDamage() >= p.getMaxHealth()*0.20) {
	        if(playerclass.get(p.getUniqueId()) == 17) {
	        	d.setDamage(p.getMaxHealth()*0.20);
	        }
			
		}
	}
	
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 17 && ((is.getType().name().contains("PICKAXE"))) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 50) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Gamma Ray").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    ArrayList<Location> line = new ArrayList<>();
	                    AtomicInteger j = new AtomicInteger();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,20,0,false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20,4,false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20,4,false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,20,4,false,false));
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,20,4,false,false));
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 0);
	                    for(double ang = -Math.PI/3; ang<Math.PI/3; ang += Math.PI/90) {
	                    	for(double point = 0.1; point<30.1; point+=0.5) {
	                    		line.add(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(ang).multiply(point)));
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
         					for(Entity e: l.getWorld().getNearbyEntities(l,1,1,1)) {
         						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")) {
         							LivingEntity le = (LivingEntity)e;
         							hold.holding(p, le, 20l);
         							les.add(le);
         						}
         					}
	                    	
	                    });
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
					                    for(LivingEntity le: les) {
				 							p.setSprinting(true);
				 							le.damage(0, p);
				 							le.damage(player_damage.get(p.getName())*6, p);
				 		                    le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,100,4,false,false));
				 							p.setSprinting(false);	                    	
					                    }
						            }
			                	   }, j.incrementAndGet()/600+20); 

		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	ArrayList<Location> line = new ArrayList<>();
                    AtomicInteger j = new AtomicInteger();
                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,20,0,false,false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20,4,false,false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20,4,false,false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,20,4,false,false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,20,4,false,false));
					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 0);
                    for(double ang = -Math.PI/3; ang<Math.PI/3; ang += Math.PI/90) {
                    	for(double point = 0.1; point<30.1; point+=0.5) {
                    		line.add(p.getEyeLocation().add(p.getLocation().getDirection().normalize().rotateAroundY(ang).multiply(point)));
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
     					for(Entity e: l.getWorld().getNearbyEntities(l,1,1,1)) {
     						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")) {
     							LivingEntity le = (LivingEntity)e;
     							hold.holding(p, le, 20l);
     							les.add(le);
     						}
     					}
                    	
                    });
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {
				                    for(LivingEntity le: les) {
			 							p.setSprinting(true);
			 							le.damage(0, p);
			 							le.damage(player_damage.get(p.getName())*6, p);
			 		                    le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,100,4,false,false));
			 							p.setSprinting(false);	                    	
				                    }
					            }
		                	   }, j.incrementAndGet()/600+20); 
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
        

		
		
		if(playerclass.get(p.getUniqueId()) == 17)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") )
			{
					e.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void CombatSuit(EntityDamageByEntityEvent d) 
	{		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
        
		

		
		
		if(playerclass.get(p.getUniqueId()) == 17) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
					d.setDamage(d.getDamage()*1.2*(1+esd.CombatSuit.get(p.getUniqueId())*0.025));
				}
			}
		}

		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			EnderDragon le = (EnderDragon)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
		        
				

				
				

				if(playerclass.get(p.getUniqueId()) == 17) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
						d.setDamage(d.getDamage()*1.2*(1+esd.CombatSuit.get(p.getUniqueId())*0.025));
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
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 17) {
				if(p.getInventory().getItemInMainHand().getType()==Material.IRON_PICKAXE)
				{
						player_damage.put(p.getName(), 6 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_PICKAXE)
				{
						player_damage.put(p.getName(), 7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_PICKAXE)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STONE_PICKAXE)
				{
						player_damage.put(p.getName(),4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_PICKAXE)
				{
						player_damage.put(p.getName(), 2 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_PICKAXE)
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
        

		
		
		if(playerclass.get(p.getUniqueId()) == 17) {
			if(p.getInventory().getItemInMainHand().getType()==Material.IRON_PICKAXE)
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
			
			if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_PICKAXE)
			{
					player_damage.put(p.getName(), 7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
					
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_PICKAXE)
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.STONE_PICKAXE)
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_PICKAXE)
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
			
			if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_PICKAXE)
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
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 17) {
					if(p.getInventory().getItemInMainHand().getType()==Material.IRON_PICKAXE)
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
					
					if( p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_PICKAXE)
					{
							player_damage.put(p.getName(), 7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_PICKAXE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.STONE_PICKAXE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_PICKAXE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_PICKAXE)
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



