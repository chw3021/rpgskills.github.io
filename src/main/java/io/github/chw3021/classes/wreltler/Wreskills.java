package io.github.chw3021.classes.wreltler;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.CommonEvents;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;


public class Wreskills extends Pak implements Listener, Serializable {
	

	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 8481741046360618590L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();

	private HashMap<UUID, Integer> tacklet = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Integer> pounding = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> poundingt = new HashMap<UUID, Integer>();
	
	
	private Multimap<String, Integer> gst = ArrayListMultimap.create();
	private HashMap<UUID, UUID> gs = new HashMap<UUID, UUID>();
	
	private HashMap<Player, Integer> tr = new HashMap<Player, Integer>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private WreSkillsData wsd;


	Pak pak = new Pak();
	

	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		WreSkillsData w = new WreSkillsData(WreSkillsData.loadData(path +"/plugins/RPGskills/WreSkillsData.data"));
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
			}
			
		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("WreSkills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				WreSkillsData w = new WreSkillsData(WreSkillsData.loadData(path +"/plugins/RPGskills/WreSkillsData.data"));
				wsd = w;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		WreSkillsData w = new WreSkillsData(WreSkillsData.loadData(path +"/plugins/RPGskills/WreSkillsData.data"));
		wsd = w;
		
	}
	
	@EventHandler
	public void Tackle(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);

        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8) {
		if((p.isSneaking())&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{


            	if(tacklet.containsKey(p.getUniqueId()) && !p.hasCooldown(Material.APPLE)) {
                    
                	if(poundingt.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(poundingt.get(p.getUniqueId()));
                		poundingt.remove(p.getUniqueId());
                	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=1) {
								pounding.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 6); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							pounding.remove(p.getUniqueId());
		                }
		            }, 50); 
                	poundingt.put(p.getUniqueId(), task);
                	
            		Bukkit.getScheduler().cancelTask(tacklet.get(p.getUniqueId()));
            		tacklet.remove(p.getUniqueId());
            		
                	Location tl = p.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(1.3));
					tl.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 200,3,3,3,1,Material.DIRT_PATH.createBlockData());
					tl.getWorld().spawnParticle(Particle.CRIT, tl, 200,3,3,3);
					tl.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, tl, 100,3,3,3);
                	p.playSound(p.getLocation(), Sound.ENTITY_RAVAGER_STUNNED, 1f, 0.2f);
                	p.playSound(p.getLocation(), Sound.ENTITY_RAVAGER_STUNNED, 1f, 1.9f);
                    for (Entity e : tl.getWorld().getNearbyEntities(tl, 4, 4, 4))
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
		                	atk1(1.0, p, le);
		                	Holding.holding(p, le, 20l);
                			
    					}
    				}
            	}
            	p.setCooldown(Material.APPLE, 3);
				
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Tackle").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
                    	if(poundingt.containsKey(p.getUniqueId())) {
                    		Bukkit.getScheduler().cancelTask(poundingt.get(p.getUniqueId()));
                    		poundingt.remove(p.getUniqueId());
                    	}
	 					GiantSwingThrow(p);
	 					

	                    int q =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	Holding.invur(p, 10l);
			                	p.playSound(p.getLocation(), Sound.BLOCK_HANGING_ROOTS_STEP, 0.1f, 1.5f);
			                	p.setGliding(true);
			                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(1.3));
			                	
								p.getWorld().spawnParticle(Particle.BLOCK_DUST, p.getLocation(), 20,1,1,1, Material.DIRT.createBlockData());
								
			                	Location tl = p.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(1.3));
			                	if(tl.getWorld().getNearbyEntities(tl, 1.2,1.2,1.2).stream().anyMatch(e -> (!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))) {

									tl.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 200,3,3,3,1,Material.DIRT_PATH.createBlockData());
									tl.getWorld().spawnParticle(Particle.CRIT, tl, 200,3,3,3,0.1);
									tl.getWorld().spawnParticle(Particle.ASH, tl, 100,3,3,3);
				                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1f, 0.2f);
				                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1f, 1.9f);
				                	if(tacklet.containsKey(p.getUniqueId())) {
				                		Bukkit.getScheduler().cancelTask(tacklet.get(p.getUniqueId()));
				                		tacklet.remove(p.getUniqueId());
				                	}
			                    	
				                    for (Entity e : tl.getWorld().getNearbyEntities(tl, 1.5, 1.5, 1.5))
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
						                	atk1(1.0, p, le);
						                	le.teleport(le.getLocation().clone().add(0, -1, 0));
						                	Holding.holding(p, le, 25l);
						                	le.setSwimming(true);


					    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					    		                @Override
					    		                public void run() {
					    							if(Proficiency.getpro(p)>=1) {
					    								pounding.putIfAbsent(p.getUniqueId(), 0);
					    							}
					    		                }
					    		            }, 6); 

					                		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					    		                @Override
					    		                public void run() {
					    							pounding.remove(p.getUniqueId());
					    		                }
					    		            }, 50); 
					                    	poundingt.put(p.getUniqueId(), task);
				    					}
				    				}
			                	}
			                }
	                	 }, 0,2);
	                    tacklet.put(p.getUniqueId(), q);
	                    
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(0.1));
			                	if(tacklet.containsKey(p.getUniqueId())) {
			                		Bukkit.getScheduler().cancelTask(tacklet.get(p.getUniqueId()));
			                		tacklet.remove(p.getUniqueId());
			                	}
			                }
			            }, 8); 
		                sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
 					GiantSwingThrow(p);
                	if(poundingt.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(poundingt.get(p.getUniqueId()));
                		poundingt.remove(p.getUniqueId());
                	}
 					

                    int q =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	Holding.invur(p, 10l);
		                	p.playSound(p.getLocation(), Sound.BLOCK_HANGING_ROOTS_STEP, 0.1f, 1.5f);
		                	p.setGliding(true);
		                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(1.3));
		                	
							p.getWorld().spawnParticle(Particle.BLOCK_DUST, p.getLocation(), 20,1,1,1, Material.DIRT.createBlockData());
							
		                	Location tl = p.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(1.3));
		                	if(tl.getWorld().getNearbyEntities(tl, 1.2,1.2,1.2).stream().anyMatch(e -> (!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))) {

								tl.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 200,3,3,3,1,Material.DIRT_PATH.createBlockData());
								tl.getWorld().spawnParticle(Particle.CRIT, tl, 200,3,3,3,0.1);
								tl.getWorld().spawnParticle(Particle.ASH, tl, 100,3,3,3);
			                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1f, 0.2f);
			                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1f, 1.9f);
			                	if(tacklet.containsKey(p.getUniqueId())) {
			                		Bukkit.getScheduler().cancelTask(tacklet.get(p.getUniqueId()));
			                		tacklet.remove(p.getUniqueId());
			                	}
		                        
			                    for (Entity e : tl.getWorld().getNearbyEntities(tl, 1.5, 1.5, 1.5))
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
					                	atk1(1.0, p, le);
					                	le.teleport(le.getLocation().clone().add(0, -1, 0));
					                	Holding.holding(p, le, 25l);
					                	le.setSwimming(true);

				    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				    		                @Override
				    		                public void run() {
				    							if(Proficiency.getpro(p)>=1) {
				    								pounding.putIfAbsent(p.getUniqueId(), 0);
				    							}
				    		                }
				    		            }, 6); 

				                		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				    		                @Override
				    		                public void run() {
				    							pounding.remove(p.getUniqueId());
				    		                }
				    		            }, 50); 
				                    	poundingt.put(p.getUniqueId(), task);
				                    	
			                			
			    					}
			    				}
		                	}
		                }
                	 }, 0,2);
                    tacklet.put(p.getUniqueId(), q);
                    
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(0.1));
		                	if(tacklet.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(tacklet.get(p.getUniqueId()));
		                		tacklet.remove(p.getUniqueId());
		                	}
		                }
		            }, 8); 
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	
	private HashMap<UUID, ArmorStand> sitdown = new HashMap<UUID, ArmorStand>();
	
	public void sit(VehicleExitEvent ev) 
	{
		if(ev.getVehicle() instanceof ArmorStand && ev.getExited() instanceof Player) {
			Player p = (Player) ev.getExited();
            if(sitdown.containsKey(p.getUniqueId())) {
            	ev.setCancelled(true);
            }
		}
	}
	
	final private void sit(Player p, Integer tick) {
        if(sitdown.containsKey(p.getUniqueId())) {
        	Holding.ale(sitdown.get(p.getUniqueId())).remove();
        	sitdown.remove(p.getUniqueId());
        }
        
		ArmorStand pa = p.getWorld().spawn(p.getLocation().clone(), ArmorStand.class);
		pa.setRemoveWhenFarAway(true);
		pa.setInvisible(true);
		pa.setInvulnerable(true);
		pa.setSmall(true);
		pa.setCollidable(false);
		pa.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
		pa.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(),p.getName()));
		pa.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(),p.getName()));
		pa.addPassenger(p);
		
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(sitdown.containsKey(p.getUniqueId())) {
                	Holding.ale(sitdown.get(p.getUniqueId())).remove();
                	sitdown.remove(p.getUniqueId());
                }
            	pa.remove();
            }
        }, tick); 
		
	}
	
	@EventHandler
	public void Pounding(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8 && pounding.containsKey(p.getUniqueId())) {
			if((p.isSneaking())&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					ev.setCancelled(true);

	            	if(poundingt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(poundingt.get(p.getUniqueId()));
	            		poundingt.remove(p.getUniqueId());
	            	}
					pounding.remove(p.getUniqueId());
					
                    final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation().clone().add(0, -1, 0);
					
					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 50, 2,2,2,0);
					p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);

					HashSet<LivingEntity> les = new HashSet<>();
					sit(p,25);
                    
                    for (Entity e : p.getWorld().getNearbyEntities(p.getLocation().clone(), 4, 2.5, 4))
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
		                	le.teleport(tl);
		                	les.add(le);
		                	Holding.holding(p, le, 25l);
							sit(p,25);
    					}
    				}
                    
					for(int n = 0; n<5; n++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {

								p.getWorld().spawnParticle(Particle.CRIT, tl, 50, 2,2,2,0);
								p.getWorld().spawnParticle(Particle.CRIT_MAGIC, tl, 50, 2,2,2,0);
								p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.35f, 1.5f);
								p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.7f, 0);
								p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.7f, 0.1f);
								p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_WEAK, 0.7f, 0.6f);
			                	les.forEach(le -> {
				                	atk1(1.0, p, le);
			                	});
			                }
	                	 }, n*3+10); 														
					}
				}
			}
		}
	}

	@EventHandler
	public void ArmThrow(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =4*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);
		
		if(ClassData.pc.get(p.getUniqueId()) == 8) {
		if(!p.isSneaking())
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{
				

				if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (swcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use ArmThrow").create());
	                }
	                else // if timer is done
	                {
	                    swcooldown.remove(p.getName()); // removing player from HashMap
	 					GiantSwingThrow(p);

		        		ArrayList<Location> drop = new ArrayList<>();
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
		        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK,l, 40, 2, 0.1, 2);
		        		
	                    AtomicInteger j = new AtomicInteger();
	                    

	                    for(Entity e : p.getWorld().getNearbyEntities(l, 3.5, 3.5, 3.5)) {
		                    
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
									le.teleport(p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().multiply(1.1)));
									Holding.superholding(p, le, (long) 45);
									p.swingOffHand();
									p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1, 0);
									p.setGliding(true);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
											le.teleport(le.getLocation().add(0, -1.25, 0));
											p.playSound(le.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 0.1f, 0);
											p.playSound(le.getLocation(), Sound.BLOCK_WEEPING_VINES_PLACE, 0.1f, 0);
											atk0(0.9, wsd.ArmThrow.get(p.getUniqueId())*0.9, p, le);
											Holding.superholding(p, le, (long) 45);
											p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
											p.getWorld().spawnParticle(Particle.CRIT_MAGIC, le.getLocation(), 10, 2,2,2,0);
											p.playSound(p.getLocation(), Sound.ENTITY_GOAT_LONG_JUMP, 0.1f, 0);
						                	p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,5,30,false,false));
						                }
			                	   }, 10); 
								} 
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.setGliding(true);
				        		for(int i=0; i<50; i++) {
				        			Location plc = p.getLocation().clone().add(0, -i, 0);
				        			if(!plc.getBlock().isPassable()) {
				        				break;
				        			}
				        			drop.add(plc);
				        		}
								drop.forEach(dl -> {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
									{
					             	@Override
							                public void run() 
					             				{	
					             					p.teleport(dl);
								                    p.playSound(p.getLocation(), Sound.BLOCK_SNOW_FALL, 0.3f, 2f);
								                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.3f, 1.1f);
								                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.3f, 2);
								                    p.spawnParticle(Particle.CRIT, p.getLocation(), 3);
									            }
						            }, j.incrementAndGet()/2+1);
			                    });
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
								{
				             	@Override
						                public void run() 
				             				{	
											p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 100, 2,2,2,0 ,Material.DIRT.createBlockData());
											p.playSound(p.getLocation(), Sound.BLOCK_CAVE_VINES_PICK_BERRIES, 1, 0);
											p.playSound(p.getLocation(), Sound.BLOCK_GRASS_BREAK, 1, 0);
						                    p.getWorld().spawnParticle(Particle.CRIT, l, 500,4,2,4);
						                    p.getWorld().spawnParticle(Particle.CRIT_MAGIC, l, 500,4,2,4);
						                    p.getWorld().spawnParticle(Particle.SMOKE_LARGE, l, 500,4,2,4);
						                    p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 500,4,2,4, p.getLocation().clone().add(0, -1, 0).getBlock().getBlockData());
											for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),4, 4, 4))
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
													atk0(0.9, wsd.ArmThrow.get(p.getUniqueId())*0.9, p, le);
													Holding.superholding(p, le, (long) 45);
												}
											}
								            }
					            }, j.incrementAndGet()/2+1);
			                }
						}, 20); 
						
		                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
 					GiantSwingThrow(p);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
	        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);

	        		ArrayList<Location> drop = new ArrayList<>();
                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
                    AtomicInteger j = new AtomicInteger();
                    

                    for(Entity e : p.getWorld().getNearbyEntities(l, 3.5, 3.5, 3.5)) {
	                    
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
								le.teleport(p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().multiply(1.1)));
								Holding.superholding(p, le, (long) 45);
								p.swingOffHand();
								p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1, 0);
								p.setGliding(true);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
										le.teleport(le.getLocation().add(0, -1.25, 0));
										p.playSound(le.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 0.1f, 0);
										p.playSound(le.getLocation(), Sound.BLOCK_WEEPING_VINES_PLACE, 0.1f, 0);
										atk0(0.9, wsd.ArmThrow.get(p.getUniqueId())*0.9, p, le);
										Holding.superholding(p, le, (long) 45);
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
										p.getWorld().spawnParticle(Particle.CRIT_MAGIC, le.getLocation(), 10, 2,2,2,0);
										p.playSound(p.getLocation(), Sound.ENTITY_GOAT_LONG_JUMP, 0.1f, 0);
					                	p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,5,30,false,false));
					                }
		                	   }, 10); 
							} 
                    }
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	p.setGliding(true);
			        		for(int i=0; i<50; i++) {
			        			Location plc = p.getLocation().clone().add(0, -i, 0);
			        			if(!plc.getBlock().isPassable()) {
			        				break;
			        			}
			        			drop.add(plc);
			        		}
							drop.forEach(dl -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
								{
				             	@Override
						                public void run() 
				             				{	
				             					p.teleport(dl);
							                    p.playSound(p.getLocation(), Sound.BLOCK_SNOW_FALL, 0.3f, 2f);
							                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.3f, 1.1f);
							                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.3f, 2);
							                    p.spawnParticle(Particle.CRIT, p.getLocation(), 3);
								            }
					            }, j.incrementAndGet()/2+1);
		                    });
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
							{
			             	@Override
					                public void run() 
			             				{	
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 100, 2,2,2,0 ,Material.DIRT.createBlockData());
										p.playSound(p.getLocation(), Sound.BLOCK_CAVE_VINES_PICK_BERRIES, 1, 0);
										p.playSound(p.getLocation(), Sound.BLOCK_GRASS_BREAK, 1, 0);
					                    p.getWorld().spawnParticle(Particle.CRIT, l, 500,4,2,4);
					                    p.getWorld().spawnParticle(Particle.CRIT_MAGIC, l, 500,4,2,4);
					                    p.getWorld().spawnParticle(Particle.SMOKE_LARGE, l, 500,4,2,4);
					                    p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 500,4,2,4, p.getLocation().clone().add(0, -1, 0).getBlock().getBlockData());
										for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),4, 4, 4))
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
												atk0(0.9, wsd.ArmThrow.get(p.getUniqueId())*0.9, p, le);
												Holding.superholding(p, le, (long) 45);
											}
										}
							            }
				            }, j.incrementAndGet()/2+1);
		                }
					}, 20); 
					
	                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                
	            }
			}
		}
		}
	}
	
	@EventHandler
	public void ChokeSlam(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =4*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);
		
		if(ClassData.pc.get(p.getUniqueId()) == 8) {
		if(!p.isSneaking())
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{
				
				ev.setCancelled(true);
				if(p.hasCooldown(Material.REPEATER)) {
					return;
				}
				if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (swcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use ChokeSlam").create());
	                }
	                else // if timer is done
	                {
	                    swcooldown.remove(p.getName()); // removing player from HashMap
	 					GiantSwingThrow(p);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
		        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);

		        		ArrayList<Location> drop = new ArrayList<>();
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
	                    AtomicInteger j = new AtomicInteger();
	                    

	                    for(Entity e : p.getWorld().getNearbyEntities(l, 3.5, 3.5, 3.5)) {
		                    
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
									le.teleport(p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().multiply(1.1)));
									Holding.superholding(p, le, (long) 45);
									p.swingOffHand();
									p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1, 0);
									p.setGliding(true);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
										le.teleport(le.getLocation().add(0, -1.25, 0));
										p.playSound(le.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 0.1f, 0);
										p.playSound(le.getLocation(), Sound.BLOCK_WEEPING_VINES_PLACE, 0.1f, 0);
										atk0(0.9, wsd.ArmThrow.get(p.getUniqueId())*0.9, p, le);
										Holding.superholding(p, le, (long) 45);
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
										p.getWorld().spawnParticle(Particle.CRIT_MAGIC, le.getLocation(), 10, 2,2,2,0);
										p.playSound(p.getLocation(), Sound.ENTITY_GOAT_LONG_JUMP, 0.1f, 0);
					                	p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,5,30,false,false));
					                }
								}, 10); 
								p.setCooldown(Material.REPEATER, 10);
							} 
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.setGliding(true);
				        		for(int i=0; i<50; i++) {
				        			Location plc = p.getLocation().clone().add(0, -i, 0);
				        			if(!plc.getBlock().isPassable()) {
				        				break;
				        			}
				        			drop.add(plc);
				        		}
								drop.forEach(dl -> {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
									{
					             	@Override
							                public void run() 
					             				{	
					             					p.teleport(dl);
								                    p.playSound(p.getLocation(), Sound.BLOCK_SNOW_FALL, 0.3f, 2f);
								                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.3f, 1.1f);
								                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.3f, 2);
								                    p.spawnParticle(Particle.CRIT, p.getLocation(), 3);
									            }
						            }, j.incrementAndGet()/2+1);
			                    });
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
								{
				             	@Override
						                public void run() 
				             				{	
											p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 100, 2,2,2,0 ,Material.DIRT.createBlockData());
											p.playSound(p.getLocation(), Sound.BLOCK_CAVE_VINES_PICK_BERRIES, 1, 0);
											p.playSound(p.getLocation(), Sound.BLOCK_GRASS_BREAK, 1, 0);
						                    p.getWorld().spawnParticle(Particle.CRIT, l, 500,4,2,4);
						                    p.getWorld().spawnParticle(Particle.CRIT_MAGIC, l, 500,4,2,4);
						                    p.getWorld().spawnParticle(Particle.SMOKE_LARGE, l, 500,4,2,4);
						                    p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 500,4,2,4, p.getLocation().clone().add(0, -1, 0).getBlock().getBlockData());
											for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),4, 4, 4))
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
													atk0(0.9, wsd.ArmThrow.get(p.getUniqueId())*0.9, p, le);
													Holding.superholding(p, le, (long) 45);
												}
											}
								            }
					            }, j.incrementAndGet()/2+1);
			                }
						}, 20); 
						
		                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
 					GiantSwingThrow(p);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
	        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);

	        		ArrayList<Location> drop = new ArrayList<>();
                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
                    AtomicInteger j = new AtomicInteger();
                    

                    for(Entity e : p.getWorld().getNearbyEntities(l, 3.5, 3.5, 3.5)) {
	                    
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
								le.teleport(p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().multiply(1.1)));
								Holding.superholding(p, le, (long) 45);
								p.swingOffHand();
								p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1, 0);
								p.setGliding(true);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
										le.teleport(le.getLocation().add(0, -1.25, 0));
										p.playSound(le.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 0.1f, 0);
										p.playSound(le.getLocation(), Sound.BLOCK_WEEPING_VINES_PLACE, 0.1f, 0);
										atk0(0.9, wsd.ArmThrow.get(p.getUniqueId())*0.9, p, le);
										Holding.superholding(p, le, (long) 45);
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
										p.getWorld().spawnParticle(Particle.CRIT_MAGIC, le.getLocation(), 10, 2,2,2,0);
										p.playSound(p.getLocation(), Sound.ENTITY_GOAT_LONG_JUMP, 0.1f, 0);
					                	p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,5,30,false,false));
					                }
		                	   }, 10); 
								p.setCooldown(Material.REPEATER, 10);
							} 
                    }
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	p.setGliding(true);
			        		for(int i=0; i<50; i++) {
			        			Location plc = p.getLocation().clone().add(0, -i, 0);
			        			if(!plc.getBlock().isPassable()) {
			        				break;
			        			}
			        			drop.add(plc);
			        		}
							drop.forEach(dl -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
								{
				             	@Override
						                public void run() 
				             				{	
				             					p.teleport(dl);
							                    p.playSound(p.getLocation(), Sound.BLOCK_SNOW_FALL, 0.3f, 2f);
							                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.3f, 1.1f);
							                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.3f, 2);
							                    p.spawnParticle(Particle.CRIT, p.getLocation(), 3);
								            }
					            }, j.incrementAndGet()/2+1);
		                    });
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
							{
			             	@Override
					                public void run() 
			             				{	
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 100, 2,2,2,0 ,Material.DIRT.createBlockData());
										p.playSound(p.getLocation(), Sound.BLOCK_CAVE_VINES_PICK_BERRIES, 1, 0);
										p.playSound(p.getLocation(), Sound.BLOCK_GRASS_BREAK, 1, 0);
					                    p.getWorld().spawnParticle(Particle.CRIT, l, 500,4,2,4);
					                    p.getWorld().spawnParticle(Particle.CRIT_MAGIC, l, 500,4,2,4);
					                    p.getWorld().spawnParticle(Particle.SMOKE_LARGE, l, 500,4,2,4);
					                    p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 500,4,2,4, p.getLocation().clone().add(0, -1, 0).getBlock().getBlockData());
										for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),4, 4, 4))
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
												atk0(0.9, wsd.ArmThrow.get(p.getUniqueId())*0.9, p, le);
												Holding.superholding(p, le, (long) 45);
											}
										}
							            }
				            }, j.incrementAndGet()/2+1);
		                }
					}, 20); 
					
	                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                
	            }
			}
		}
		}
	}

	@EventHandler
	public void Suplex(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);

        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.isSneaking())
			{
				ev.setCancelled(true);
				
				
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Suplex").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		 					GiantSwingThrow(p);
		                    p.swingOffHand();
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
			        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);
		                    p.setGliding(true);
		                    ArrayList<Location> air = new ArrayList<Location>();
		                    ArrayList<Location> sight = new ArrayList<Location>();
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
		                    for(double i=Math.PI/180; i > -Math.PI; i -= Math.PI/180) {
		                    	Location eye = p.getEyeLocation().clone();
		                    	Vector eyev = eye.getDirection().clone().rotateAroundY(Math.PI/2);
		                    	Location a = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, i).normalize());
		                    	Location b = eye.clone().add(eye.getDirection().rotateAroundAxis(eyev, i).normalize().multiply(2));
		                    	sight.add(a);
		                    	air.add(b);
		                    }
		                    for(Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3)) {
			                    AtomicInteger j = new AtomicInteger(0);
			                    AtomicInteger k = new AtomicInteger(0);
		                    	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
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
										Holding.superholding(p, le, (long) 45);
					                    le.teleport(p.getLocation());
		                    			air.forEach(lel ->  {
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
											{
							             	@Override
									                public void run() 
							             				{	
									                    	le.teleport(lel);
									                    	
											            }
								            }, j.incrementAndGet()/30+10); 
											});
		                    			sight.forEach(lel ->  {
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
											{
							             	@Override
									                public void run() 
							             				{	
									                    	p.teleport(lel);
									                    	
											            }
								            }, k.incrementAndGet()/30+10); 
											});
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
										{
						             	@Override
								                public void run() 
						             				{	
						             					atk0(0.97, wsd.Suplex.get(p.getUniqueId())*1.15, p, le);
														p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
								                    	le.teleport(p.getLocation().add(0, -1, 0));
									                    p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_MIDDLE, 1, 0);
														
										            }
							            }, j.incrementAndGet()/30+10);
		                    		}
		                    }
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	 					GiantSwingThrow(p);
	                    p.swingOffHand();
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
		        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);
	                    p.setGliding(true);
	                    ArrayList<Location> air = new ArrayList<Location>();
	                    ArrayList<Location> sight = new ArrayList<Location>();
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
	                    for(double i=Math.PI/180; i > -Math.PI; i -= Math.PI/180) {
	                    	Location eye = p.getEyeLocation().clone();
	                    	Vector eyev = eye.getDirection().clone().rotateAroundY(Math.PI/2);
	                    	Location a = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, i).normalize());
	                    	Location b = eye.clone().add(eye.getDirection().rotateAroundAxis(eyev, i).normalize().multiply(2));
	                    	sight.add(a);
	                    	air.add(b);
	                    }
	                    for(Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3)) {
		                    AtomicInteger j = new AtomicInteger(0);
		                    AtomicInteger k = new AtomicInteger(0);
	                    	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
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
									Holding.superholding(p, le, (long) 45);
				                    le.teleport(p.getLocation());
	                    			air.forEach(lel ->  {
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
										{
						             	@Override
								                public void run() 
						             				{	
								                    	le.teleport(lel);
								                    	
										            }
							            }, j.incrementAndGet()/30+10); 
										});
	                    			sight.forEach(lel ->  {
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
										{
						             	@Override
								                public void run() 
						             				{	
								                    	p.teleport(lel);
								                    	
										            }
							            }, k.incrementAndGet()/30+10); 
										});
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
									{
					             	@Override
							                public void run() 
					             				{	
					             					atk0(0.97, wsd.Suplex.get(p.getUniqueId())*1.15, p, le);
													p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
							                    	le.teleport(p.getLocation().add(0, -1, 0));
								                    p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_MIDDLE, 1, 0);
													
									            }
						            }, j.incrementAndGet()/30+10);
	                    		}
	                    }
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	
	@EventHandler
	public void BodyPress(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);

        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8) {
		if(!(p.isSneaking())&& !p.isOnGround() && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{

				
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use BodyPress").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
	 					GiantSwingThrow(p);
						p.setGliding(true);
						p.swingMainHand();
						p.swingOffHand();
						p.playSound(p.getLocation(), Sound.ENTITY_HORSE_JUMP, 1.0f, 0f);
	                    ArrayList<Location> line = new ArrayList<Location>();
						Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
	                    AtomicInteger j = new AtomicInteger(0);
	                    AtomicInteger b = new AtomicInteger(0);
	                    for(double d = 0.1; d <= 4; d += 0.1) {
		                    Location pl = p.getLocation().add(0, 1, 0);
							Vector ltr = l.toVector().subtract(pl.toVector());
							pl.add(ltr.normalize().multiply(d));
							line.add(pl);
	                    }
	                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
		                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
		                    for(int k=0; k<line.indexOf(block); k++) {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {
									p.setGliding(true);	
					                    	p.teleport(line.get(b.getAndIncrement()));
					                    	p.setGliding(true);
							            }
				                	   }, j.incrementAndGet()/10); 
								 }}
	                    else {
		                    	line.forEach(i -> {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
									p.setGliding(true);
			             					p.teleport(line.get(b.getAndIncrement()));
					                    	p.setGliding(true);
							            }
				                	   }, j.incrementAndGet()/10); 
								}); 
	                    	}
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_BIG_FALL, 1.0f, 0f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 1.0f, 0f);
				        		p.getWorld().spawnParticle(Particle.SMOKE_LARGE, p.getEyeLocation(), 100,4,4,4);
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 50, 2,2,2,0 ,Material.DIRT.createBlockData());
			                	for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
								{
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
									{
										LivingEntity le = (LivingEntity)e;
										atk0(1.78, 1.64, p, le);
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
									}
								}
			                }
	            	   }, j.incrementAndGet()/10); 
		                sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
 					GiantSwingThrow(p);
					p.setGliding(true);
					p.swingMainHand();
					p.swingOffHand();
					p.playSound(p.getLocation(), Sound.ENTITY_HORSE_JUMP, 1.0f, 0f);
                    ArrayList<Location> line = new ArrayList<Location>();
					Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
                    AtomicInteger j = new AtomicInteger(0);
                    AtomicInteger b = new AtomicInteger(0);
                    for(double d = 0.1; d <= 4; d += 0.1) {
	                    Location pl = p.getLocation().add(0, 1, 0);
						Vector ltr = l.toVector().subtract(pl.toVector());
						pl.add(ltr.normalize().multiply(d));
						line.add(pl);
                    }
                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
	                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
	                    for(int k=0; k<line.indexOf(block); k++) {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
								p.setGliding(true);	
				                    	p.teleport(line.get(b.getAndIncrement()));
				                    	p.setGliding(true);
						            }
			                	   }, j.incrementAndGet()/10); 
							 }}
                    else {
	                    	line.forEach(i -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
								p.setGliding(true);
		             					p.teleport(line.get(b.getAndIncrement()));
				                    	p.setGliding(true);
						            }
			                	   }, j.incrementAndGet()/10); 
							}); 
                    	}
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_BIG_FALL, 1.0f, 0f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 1.0f, 0f);
			        		p.getWorld().spawnParticle(Particle.SMOKE_LARGE, p.getEyeLocation(), 100,4,4,4);
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 50, 2,2,2,0 ,Material.DIRT.createBlockData());
		                	for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
							{
	                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
								{
									LivingEntity le = (LivingEntity)e;
									atk0(1.78, 1.64, p, le);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
								}
							}
		                }
            	   }, j.incrementAndGet()/10); 
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	@EventHandler
	public void Chopping(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		Location el =le.getLocation();
		el.setY(el.getY()+1);

        
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8) {
			
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					d.setDamage(d.getDamage()*(1+wsd.Chopping.get(p.getUniqueId())*0.035));
					p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
					p.playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 1.0f, 0f);
				}
		}
		}

		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			EnderDragon le = (EnderDragon)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player)ar.getShooter();
				Location el =le.getLocation();
				el.setY(el.getY()+1);

		        
				
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 8) {
					
						if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
						{
							d.setDamage(d.getDamage()*(1+wsd.Chopping.get(p.getUniqueId())*0.035));
							p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
							p.playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 1.0f, 0f);
						}
				}
			}
		}
	}
	final private void GiantSwingThrow(Player p) {
		if(!gs.containsKey(p.getUniqueId())) {
			return;
		}
		
		final LivingEntity le = (LivingEntity) Bukkit.getEntity(gs.get(p.getUniqueId()));
		if(gst.containsKey(p.getName())) {
			gst.get(p.getName()).forEach(t ->{
				Bukkit.getScheduler().cancelTask(t);
			});
		}
		gst.removeAll(p.getName());
		gs.remove(p.getUniqueId());
		
		p.playSound(p.getLocation(), Sound.ENTITY_EGG_THROW, 1, 0);
        p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1, 0);
        atk0(2.1, wsd.GiantSwing.get(p.getUniqueId())*1.6, p, le);
        le.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.6));
     	for(Entity e: le.getWorld().getNearbyEntities(le.getLocation(),4, 4, 4)) {
     		if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
 			LivingEntity lle = (LivingEntity) e;
			if(lle instanceof Player) {
				Player p1 = (Player) le;
				if(Party.hasParty(p) && Party.hasParty(p1))	{
					if(Party.getParty(p).equals(Party.getParty(p1)))
						{
							continue;
						}
					}
			}
            atk0(2.1, wsd.GiantSwing.get(p.getUniqueId())*1.6, p, lle);
     		}
     	}
         	
                
	}
	
	@EventHandler
	public void GiantSwing(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{
			
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8) {
		double sec = 15*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);;
			if(p.getAttackCooldown()==1) 
			{	
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && (p.isSneaking()) && !(p.hasCooldown(Material.YELLOW_TERRACOTTA)) && !d.isCancelled())
					{
					if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            double timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use GiantSwing").create());
				        }
			            else // if timer is done
			            {
							gdcooldown.remove(p.getName()); // removing player from HashMap
							
		 					GiantSwingThrow(p);
							
							if(le instanceof Player) {
								Player p1 = (Player) le;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
									if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											return;
										}
									}
							}
							gs.put(p.getUniqueId(), le.getUniqueId());
							d.setDamage(d.getDamage()*1.6);
							p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_2, 1.0f,0f);
							ArrayList<Location> swing = new ArrayList<Location>();
							ArrayList<Location> eye = new ArrayList<Location>();
							AtomicInteger j = new AtomicInteger(0);
							AtomicInteger k = new AtomicInteger(0);
							for(double i = 0; i<Math.PI*7; i += Math.PI/90) {
								Location l1 = p.getLocation().add(0, -1, 0);
								Location l2 = p.getLocation();
								Location e = l2.setDirection(l2.getDirection().rotateAroundY(i).normalize());
								Location s = l1.setDirection(l1.getDirection().rotateAroundY(i).normalize());
								s.add(s.getDirection().multiply(2));
								swing.add(s);
								eye.add(e);
							}
							Holding.superholding(p, le, (long) 100);
							p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 5, 1, 0, 1);
							swing.forEach(l -> {
								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
								{
						     	@Override
				                public void run() 
				     				{	
				                     	le.teleport(l);
				                    	p.teleport(eye.get(k.getAndIncrement()));
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.2f, 0);
						            }
						        }, j.incrementAndGet()/10); 
								gst.put(p.getName(), task);
							});
							int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
							{
						 	@Override
						            public void run() 
						 				{	
						 					GiantSwingThrow(p);
						 				}
							}, j.incrementAndGet()/10+1);
							gst.put(p.getName(), task);
							
							gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
	 					GiantSwingThrow(p);
						
						if(le instanceof Player) {
							Player p1 = (Player) le;
							if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										return;
									}
								}
						}
						gs.put(p.getUniqueId(), le.getUniqueId());
						d.setDamage(d.getDamage()*1.6);
						p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_2, 1.0f,0f);
						ArrayList<Location> swing = new ArrayList<Location>();
						ArrayList<Location> eye = new ArrayList<Location>();
						AtomicInteger j = new AtomicInteger(0);
						AtomicInteger k = new AtomicInteger(0);
						for(double i = 0; i<Math.PI*7; i += Math.PI/90) {
							Location l1 = p.getLocation().add(0, -1, 0);
							Location l2 = p.getLocation();
							Location e = l2.setDirection(l2.getDirection().rotateAroundY(i).normalize());
							Location s = l1.setDirection(l1.getDirection().rotateAroundY(i).normalize());
							s.add(s.getDirection().multiply(2));
							swing.add(s);
							eye.add(e);
						}
						Holding.superholding(p, le, (long) 100);
						p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 5, 1, 0, 1);
						swing.forEach(l -> {
							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
							{
					     	@Override
			                public void run() 
			     				{	
			                     	le.teleport(l);
			                    	p.teleport(eye.get(k.getAndIncrement()));
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.2f, 0);
					            }
					        }, j.incrementAndGet()/10); 
							gst.put(p.getName(), task);
						});
						int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
						{
					 	@Override
					            public void run() 
					 				{	
					 					GiantSwingThrow(p);
					 				}
						}, j.incrementAndGet()/10+1);
						gst.put(p.getName(), task);
						
			            gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
					}
				}
			}
		}
	}

	@EventHandler
	public void ForeArmSmash(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
	        
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8) {
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);
		if(p.getAttackCooldown()==1 && wsd.ForeArmSmash.getOrDefault(p.getUniqueId(),0)>=1) 
			{	
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && !(p.isSneaking()) && !(p.isOnGround()) &&  !(p.hasCooldown(Material.YELLOW_TERRACOTTA)) )
					{
					if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            double timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use ForeArmSmash").create());
				        }
			            else // if timer is done
			            {
			                smcooldown.remove(p.getName()); // removing player from HashMap
							d.setDamage(d.getDamage()*1.5*(1+p.getFallDistance()*0.2));
							le.teleport(le.getLocation().add(0, -1, 0));
							Holding.holding(p, le, 30l);
							p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 0f);
				            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
						d.setDamage(d.getDamage()*1.5*(1+p.getFallDistance()*0.2));
						le.teleport(le.getLocation().add(0, -1, 0));
						Holding.holding(p, le, 30l);
						p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1.0f, 0f);
						p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 0f);
			            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
					}
			}
		}}
	}

	@EventHandler	
	public void TackleRush(PlayerDeathEvent ev) 
	{
		Player p = ev.getEntity();


		if(tr.containsKey(p)) {
			Bukkit.getServer().getScheduler().cancelTask(tr.get(p));
			tr.remove(p);
		}
	}

	@EventHandler	
	public void TackleRush(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();


		if(tr.containsKey(p)) {
			Bukkit.getServer().getScheduler().cancelTask(tr.get(p));
			tr.remove(p);
		}
	}
	
	@EventHandler
	public void TackleRush(PlayerToggleSprintEvent e) 
	{
		Player p = (Player)e.getPlayer();
		
		
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8 && wsd.Squall.get(p.getUniqueId())>=1) {
				if(e.isSprinting() && p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 1, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10, 1, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 1, false, false));
	                     	for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(),1.1, 1.1, 1.1)) {
	                     		if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
	                     			LivingEntity le = (LivingEntity) e;
	        						if(le instanceof Player) {
	        							Player p1 = (Player) le;
	        							if(Party.hasParty(p) && Party.hasParty(p1))	{
	        								if(Party.getParty(p).equals(Party.getParty(p1)))
	        									{
	        										continue;
	        									}
	        								}
	        						}
	        						atk1(0.25, p, le);
					                
	                     		}
	                     	}
		                }
					}, 0, 5); 
					tr.put(p, task);
				}
				else
				{
	        		if(tr.containsKey(p)) {
	        			Bukkit.getServer().getScheduler().cancelTask(tr.get(p));
	        			tr.remove(p);
	        		}
					
				}
		}

			
	}
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item ii = ev.getItemDrop();
		ItemStack is = ii.getItemStack();
        
		
		
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 8 && (is.getType().name().contains("BANNER_PATTERN"))&& (is.hasItemMeta()) && is.getItemMeta().hasCustomModelData()  && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sultcooldown.get(p.getName())/1000 + 60) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use FinishMove").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
	                    p.playSound(p.getLocation(), Sound.AMBIENT_WARPED_FOREST_ADDITIONS, 1.0f, 2.0f);
		        		p.getWorld().spawnParticle(Particle.SMOKE_LARGE, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 40, 2, 0, 2);
		        		AtomicInteger j = new AtomicInteger();
		        		ArrayList<Location> drop = new ArrayList<>();
		        		for(int i=50; i>0; i -= 1) {
		        			Location plc = p.getLocation().clone();
		        			drop.add(plc.add(0, i, 0));
		        		}
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation().add(0, -1, 0);

		        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 500, 6, 2, 6);
	                    for(Entity e : p.getWorld().getNearbyEntities(l, 6, 5, 6)) {
	                    	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
	                			LivingEntity le = (LivingEntity)e;
	                    		if(le instanceof Player) {
									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
											{
												continue;
											}
										}
		                    		else {
		                    				Holding.superholding(p, le, (long) 100);
						                    le.teleport(l);
		                			}
	                    		}
	                    		else {
	                				Holding.superholding(p, le, (long) 100);
				                    le.teleport(l);
	                    		}
	                		}
	                	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
						{
		             	@Override
				                public void run() 
		             				{	
					                    p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_JUMP, 1, 2);
					                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_JUMP, 1, 2);
						            }
			            }, 10);
	                    drop.forEach(dl -> {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
							{
			             	@Override
					                public void run() 
			             				{	
			             					p.teleport(dl);
						                    p.playSound(p.getLocation(), Sound.BLOCK_SNOW_FALL, 0.3f, 2f);
						                    p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 0.3f, 2f);
						                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1, 2);
						                    p.spawnParticle(Particle.CRIT, p.getLocation(), 1);
							            }
				            }, j.incrementAndGet()/2+10);
	                    });
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
						{
		             	@Override
				                public void run() 
		             				{	
				                    p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1, 0);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1, 0);
				                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 0);
				                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 0);
				                    p.spawnParticle(Particle.CRIT, l, 500,6,2,6);
				                    p.spawnParticle(Particle.SMOKE_LARGE, l, 500,6,2,6);
				                    p.spawnParticle(Particle.BLOCK_CRACK, l, 500,6,2,6, p.getLocation().getBlock().getBlockData());
		             					for(Entity e : p.getWorld().getNearbyEntities(l, 6, 5, 6)) {
		                            	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
		                        			LivingEntity le = (LivingEntity)e;
		                            		if(le instanceof Player) {
		        								Player p1 = (Player) e;
		        								if(Party.hasParty(p) && Party.hasParty(p1))	{
		        									if(Party.getParty(p).equals(Party.getParty(p1)))
		        										{
		        											continue;
		        										}
		        									}
		        	                    		else {
		        	             					
		        	             					p.swingMainHand();

		        	        						atk1(19.8, p, le);
		        									
		        	                    			p.leaveVehicle();
		        	                			}
		                            		}
		                            		else {
		    	             					
		    	             					p.swingMainHand();

	        	        						atk1(19.8, p, le);
		    	                    			p.leaveVehicle();
		                            		}
		                        		}
		                        	}
						            }
			            }, j.incrementAndGet()/2+10);
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
                    p.playSound(p.getLocation(), Sound.AMBIENT_WARPED_FOREST_ADDITIONS, 1.0f, 2.0f);
	        		p.getWorld().spawnParticle(Particle.SMOKE_LARGE, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 40, 2, 0, 2);
	        		AtomicInteger j = new AtomicInteger();
	        		ArrayList<Location> drop = new ArrayList<>();
	        		for(int i=50; i>0; i -= 1) {
	        			Location plc = p.getLocation().clone();
	        			drop.add(plc.add(0, i, 0));
	        		}
                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation().add(0, -1, 0);

	        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 500, 6, 2, 6);
                    for(Entity e : p.getWorld().getNearbyEntities(l, 6, 5, 6)) {
                    	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
                			LivingEntity le = (LivingEntity)e;
                    		if(le instanceof Player) {
								Player p1 = (Player) e;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
									if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											continue;
										}
									}
	                    		else {
	                    				Holding.superholding(p, le, (long) 100);
					                    le.teleport(l);
	                			}
                    		}
                    		else {
                				Holding.superholding(p, le, (long) 100);
			                    le.teleport(l);
                    		}
                		}
                	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
					{
	             	@Override
			                public void run() 
	             				{	
				                    p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_JUMP, 1, 2);
				                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_JUMP, 1, 2);
					            }
		            }, 10);
                    drop.forEach(dl -> {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
						{
		             	@Override
				                public void run() 
		             				{	
		             					p.teleport(dl);
					                    p.playSound(p.getLocation(), Sound.BLOCK_SNOW_FALL, 0.3f, 2f);
					                    p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 0.3f, 2f);
					                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1, 2);
					                    p.spawnParticle(Particle.CRIT, p.getLocation(), 1);
						            }
			            }, j.incrementAndGet()/2+10);
                    });
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
					{
	             	@Override
			                public void run() 
	             				{	
			                    p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1, 0);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1, 0);
			                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 0);
			                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 0);
			                    p.spawnParticle(Particle.CRIT, l, 500,6,2,6);
			                    p.spawnParticle(Particle.SMOKE_LARGE, l, 500,6,2,6);
			                    p.spawnParticle(Particle.BLOCK_CRACK, l, 500,6,2,6, p.getLocation().getBlock().getBlockData());
	             					for(Entity e : p.getWorld().getNearbyEntities(l, 6, 5, 6)) {
	                            	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
	                        			LivingEntity le = (LivingEntity)e;
	                            		if(le instanceof Player) {
	        								Player p1 = (Player) e;
	        								if(Party.hasParty(p) && Party.hasParty(p1))	{
	        									if(Party.getParty(p).equals(Party.getParty(p1)))
	        										{
	        											continue;
	        										}
	        									}
	        	                    		else {
	        	             					
	        	             					p.swingMainHand();

	        	        						atk1(19.8, p, le);
	        									
	        	                    			p.leaveVehicle();
	        	                			}
	                            		}
	                            		else {
	    	             					
	    	             					p.swingMainHand();

        	        						atk1(19.8, p, le);
	    	                    			p.leaveVehicle();
	                            		}
	                        		}
	                        	}
					            }
		            }, j.incrementAndGet()/2+10);
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
			{
					e.setCancelled(true);
			}
		}
		
	}
}



