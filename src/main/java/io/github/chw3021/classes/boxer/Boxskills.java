package io.github.chw3021.classes.boxer;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.nobility.NobSkillsData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.obtains.Obtained;

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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;

public class Boxskills extends Pak implements Listener, Serializable {
	

	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 8481741046360618590L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();
	private HashMap<UUID, Integer> fistforce = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> parrying = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> counter = new HashMap<UUID, Integer>();
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private static HashMap<UUID, Integer> sz = new HashMap<UUID, Integer>();
	static HashMap<UUID, Integer> fistforcet = new HashMap<UUID, Integer>();
	private String path = new File("").getAbsolutePath();
	private BoxSkillsData bsd;
	private HashMap<UUID, Integer> uh = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> uht = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fs = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fst = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Location> dr1 = new HashMap<UUID, Location>();
	private HashMap<UUID, Integer> dr1t = new HashMap<UUID, Integer>();
	private HashMap<UUID, Location> dr2 = new HashMap<UUID, Location>();
	private HashMap<UUID, Integer> dr2t = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Integer> sc = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sct = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> eq = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> eqt = new HashMap<UUID, Integer>();

	private static final Boxskills instance = new Boxskills ();
	public static Boxskills getInstance()
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
				if(e.getWhoClicked() instanceof Player) {
					Player p = (Player) e.getWhoClicked();
	            	if(fistforcet.containsKey(p.getUniqueId())) {
		            	Bukkit.getServer().getScheduler().cancelTask(fistforcet.get(p.getUniqueId()));
		            	fistforcet.remove(p.getUniqueId());
	            	}
	        		if(sz.containsKey(p.getUniqueId())) {
	        			Bukkit.getServer().getScheduler().cancelTask(sz.get(p.getUniqueId()));
	        			sz.remove(p.getUniqueId());
	        		}
				}
			}
			
		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("BoxSkills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				BoxSkillsData b = new BoxSkillsData(BoxSkillsData.loadData(path +"/plugins/RPGskills/BoxSkillsData.data"));
				bsd = b;
			}
			
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		BoxSkillsData b = new BoxSkillsData(BoxSkillsData.loadData(path +"/plugins/RPGskills/BoxSkillsData.data"));
		bsd = b;
		
	}

		
	public void er(PluginEnableEvent ev) 
	{
		BoxSkillsData b = new BoxSkillsData(BoxSkillsData.loadData(path +"/plugins/RPGskills/BoxSkillsData.data"));
		bsd = b;
	}
	
	
	public void Adrenaline(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 7)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && fistforce.containsKey(p.getUniqueId()))
			{
				double sec = 5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				
				if(!(p.isSneaking())&& bsd.Parrying.get(p.getUniqueId())>=1)
				{
					ev.setCancelled(true);
					if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (sdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
			        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("권기방출 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						    }
			        		else {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use FistForce").create());
			        		}
		                }
		                else // if timer is done
		                {
		                    sdcooldown.remove(p.getName()); // removing player from HashMap
			            	if(fistforcet.containsKey(p.getUniqueId())) {
				            	Bukkit.getServer().getScheduler().cancelTask(fistforcet.get(p.getUniqueId()));
				            	fistforcet.remove(p.getUniqueId());
			            	}
							p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20,0,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20,20,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20,20,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20,20,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20,20,false,false));
							p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
							p.getWorld().playSound(p.getLocation(),Sound.BLOCK_BEACON_ACTIVATE, 1,0.8f);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
				    				p.getWorld().spawnParticle(Particle.FLASH, l, 30, 2, 2, 2);
									p.getWorld().spawnParticle(Particle.COMPOSTER, l, 600, 4, 4, 4);
									p.getWorld().spawnParticle(Particle.CRIT, l, 600, 4, 4, 4);
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
									p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 1f, 2f);
				                	for(Entity e : p.getWorld().getNearbyEntities(l, 4,4,4)) {

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
											atk0(1d, fistforce.get(p.getUniqueId())*1.5, p, le);
										}
				                	}
				                    fistforce.remove(p.getUniqueId());
								}
				            }, 20); 
			                sdcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	if(fistforcet.containsKey(p.getUniqueId())) {
			            	Bukkit.getServer().getScheduler().cancelTask(fistforcet.get(p.getUniqueId()));
			            	fistforcet.remove(p.getUniqueId());
		            	}
		            	p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20,0,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20,20,false,false));
						p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
						p.getWorld().playSound(p.getLocation(),Sound.BLOCK_BEACON_ACTIVATE, 1,0.8f);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
			                	p.getWorld().spawnParticle(Particle.FLASH, l, 30, 2, 2, 2);
								p.getWorld().spawnParticle(Particle.COMPOSTER, l, 600, 4, 4, 4);
								p.getWorld().spawnParticle(Particle.CRIT, l, 600, 4, 4, 4);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 1f, 2f);
			                	for(Entity e : p.getWorld().getNearbyEntities(l, 4,4,4)) {

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
										atk0(1d, fistforce.get(p.getUniqueId())*1.5, p, le);
									}
			                	}
			                    fistforce.remove(p.getUniqueId());
							}
			            }, 20); 
		                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
			}
		}
	}
		

	
	public void Adrenaline(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player && d.getCause() != DamageCause.SUICIDE&& d.getCause() != DamageCause.VOID && d.getCause() != DamageCause.STARVATION) {
			Player p = (Player) d.getEntity();
			if(ClassData.pc.get(p.getUniqueId()) == 7&& bsd.Parrying.getOrDefault(p.getUniqueId(), 0)>=1)
			{
            	if(fistforcet.containsKey(p.getUniqueId())) {
	            	Bukkit.getServer().getScheduler().cancelTask(fistforcet.get(p.getUniqueId()));
	            	fistforcet.remove(p.getUniqueId());
            	}
				fistforce.computeIfPresent(p.getUniqueId(), (k,v) -> v+(int) (d.getDamage()*3));
				fistforce.putIfAbsent(p.getUniqueId(), (int) (d.getDamage()*3));
				if(Proficiency.getpro(p) >= 1) {
					fistforce.put(p.getUniqueId(), 200);
				}
				if(fistforce.get(p.getUniqueId())>=200) {
					fistforce.put(p.getUniqueId(), 200);
					d.setDamage(d.getDamage()*0.5);
					if(!fistforcet.containsKey(p.getUniqueId())) {
						int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 40, 10, false, false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 10, false, false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1, false, false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 1, false, false));	
								p.getWorld().spawnParticle(Particle.WARPED_SPORE, p.getLocation(), 10, 0.1, 1, 0.1);
							}
			            }, 3,3); 
						fistforcet.put(p.getUniqueId(), task);
					}
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.AQUA + "[권기 "+ fistforce.get(p.getUniqueId()) + "/200] (최대충전)").create());
				    }
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.AQUA + "[FistForce "+ fistforce.get(p.getUniqueId()) + "/200] (FULL CHARGED)").create());
	        		}
				}
				else {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.AQUA + "[권기 "+ fistforce.get(p.getUniqueId()) + "/200]").create());
				    }
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.AQUA + "[FistForce "+ fistforce.get(p.getUniqueId()) + "/200]").create());
	        		}
				}
			}
			
		}
	}
	
	
	
	public void BodyBlow(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 7&& bsd.BodyBlow.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{
		    
			Action ac = ev.getAction();
			double sec = 4*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	
			
			
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{

				if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (swcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("철산고 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use BodyBlow").create());
		        		}
	                }
	                else // if timer is done
	                {
	                    swcooldown.remove(p.getName()); // removing player from HashMap

	                	if(uht.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(uht.get(p.getUniqueId()));
	                		uht.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							uh.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						uh.remove(p.getUniqueId());
	    	                }
	    	            }, 25); 
	                	uht.put(p.getUniqueId(), task);
	                	
	                	
	                	
		        		Location str = p.getEyeLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(2));
						p.swingMainHand();
						parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
						parrying.putIfAbsent(p.getUniqueId(), 0);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
			                	if(parrying.get(p.getUniqueId())<0) {
									parrying.remove(p.getUniqueId());			                		
			                	}
							}
			            }, 9); 
						if(str.getBlock().isPassable()) {
							p.teleport(str);
						}
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
						p.playSound(p.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1f, 0f);
						p.getWorld().spawnParticle(Particle.CRIT_MAGIC, p.getLocation(), 20, 1, 1, 1);
						p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 20, 1, 1, 1);
	                	for (Entity e : p.getWorld().getNearbyEntities(str, 3.4, 3, 3.4))
						{
	                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
							{
								LivingEntity le = (LivingEntity)e;
									{
										atk1(0.55*(1+ bsd.BodyBlow.get(p.getUniqueId())*0.035), p, le);
										
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
										p.playSound(p.getLocation(), Sound.BLOCK_METAL_HIT, 0.2f, 0f);
										p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 0.2f, 2f);
										p.getWorld().spawnParticle(Particle.ASH, e.getLocation(), 20, 1, 1, 1);
										
											
									}
							}
	                		else if (e instanceof Player) 
							{
								
								Player p1 = (Player) e;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										continue;
									}
								}
							}
						}
		                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {


                	if(uht.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(uht.get(p.getUniqueId()));
                		uht.remove(p.getUniqueId());
                	}

    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						if(Proficiency.getpro(p)>=1) {
    							uh.putIfAbsent(p.getUniqueId(), 0);
    						}
    	                }
    	            }, 3); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						uh.remove(p.getUniqueId());
    	                }
    	            }, 25); 
                	uht.put(p.getUniqueId(), task);
                	
                	
                	
	        		Location str = p.getEyeLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(2));
					p.swingMainHand();
					parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
					parrying.putIfAbsent(p.getUniqueId(), 0);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
		                	if(parrying.get(p.getUniqueId())<0) {
								parrying.remove(p.getUniqueId());			                		
		                	}
						}
		            }, 9); 
					if(str.getBlock().isPassable()) {
						p.teleport(str);
					}
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
					p.playSound(p.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1f, 0f);
					p.getWorld().spawnParticle(Particle.CRIT_MAGIC, p.getLocation(), 20, 1, 1, 1);
					p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 20, 1, 1, 1);
                	for (Entity e : p.getWorld().getNearbyEntities(str, 3.4, 3, 3.4))
					{
                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
						{
							LivingEntity le = (LivingEntity)e;
								{
									atk1(0.55*(1+ bsd.BodyBlow.get(p.getUniqueId())*0.035), p, le);
									
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
									p.playSound(p.getLocation(), Sound.BLOCK_METAL_HIT, 0.2f, 0f);
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 0.2f, 2f);
									p.getWorld().spawnParticle(Particle.ASH, e.getLocation(), 20, 1, 1, 1);
									
										
								}
						}
                		else if (e instanceof Player) 
						{
							
							Player p1 = (Player) e;
							if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
								{
									continue;
								}
							}
						}
					}
	                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                
	            }
			}
		}
		}
	}
	

	
	public void UnderHook(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)  && p.isSneaking() &&uh.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
            	if(uht.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(uht.get(p.getUniqueId()));
            		uht.remove(p.getUniqueId());
            	}
				uh.remove(p.getUniqueId());


            	if(fst.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(fst.get(p.getUniqueId()));
            		fst.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=1) {
							fs.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						fs.remove(p.getUniqueId());
	                }
	            }, 25); 
            	fst.put(p.getUniqueId(), task);

				
				final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();

				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
				p.playSound(p.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1f, 0f);
				p.playSound(p.getLocation(), Sound.BLOCK_METAL_HIT, 1f, 0f);
				p.getWorld().spawnParticle(Particle.CRIT, tl, 100, 1, 1, 1);
				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 30, 1, 1, 1);
				for(Entity e : p.getWorld().getNearbyEntities(tl,4, 3, 4)) {
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
					if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
						atk1(0.35*(1+ bsd.BodyBlow.get(p.getUniqueId())*0.035), p, le);
						Holding.superholding(p, le, 10l);
	                    le.teleport(p);
	                    p.swingMainHand();
					}
				}
							
			}
		
		}
	}
	

	final private ArrayList<Location> FistStorm(Location il) {


        ArrayList<Location> cir = new ArrayList<Location>();
    	for(double angley= -Math.PI/5; angley<Math.PI/5; angley += Math.PI/45) {
            for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
            	Location one = il.clone();
            	one.add(one.getDirection().clone().rotateAroundY(angley).rotateAroundAxis(il.clone().getDirection(),angle).normalize().multiply(angle*0.8));
            	cir.add(one);
            }
    	}	
    	cir.forEach(l -> {
			l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,2,0.1,0.1,0.1,0);
			l.getWorld().spawnParticle(Particle.CRIT, l,2,0.1,0.1,0.1,0);
	    });
	    return cir;
	}
	
	
	public void FistStorm(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();
	
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)  && p.isSneaking() &&fs.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
	        	if(fst.containsKey(p.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(fst.get(p.getUniqueId()));
	        		fst.remove(p.getUniqueId());
	        	}
				fs.remove(p.getUniqueId());
				
	
				
	
            	for(int i =0; i<8; i++) {
             	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			            		Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(3));
			    				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.5f, 0.3f);
			    				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 1.73f);
			    				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 0.3f, 2f);
			    		        FistStorm(p.getLocation().clone());
			    				
	    	                    p.swingMainHand();

			                	for (Entity e : tl.getWorld().getNearbyEntities(tl, 4, 3, 4))
								{
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
										LivingEntity le = (LivingEntity)e;
										atk1(0.35*(1+ bsd.BodyBlow.get(p.getUniqueId())*0.035), p, le);
							            le.teleport(tl);
										Holding.superholding(p, le, 3l);
									}
								}
			                }
             	   },i*3);
            	}
							
			}
		
		}
	}


	public void DempseyRoll(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 7&& bsd.DempseyRoll.getOrDefault(p.getUniqueId(), 0)>=1 && p.isSneaking()) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{
			double sec = 7*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	
			
		
				ev.setCancelled(true);
				final Location pfl = p.getLocation().clone();
				
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (cdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
			        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("뎀프시롤 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						    }
			        		else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use DempsyRoll").create());
			        		}
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    

		                	if(dr1t.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(dr1t.get(p.getUniqueId()));
		                		dr1t.remove(p.getUniqueId());
		                	}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(Proficiency.getpro(p)>=1) {
										dr1.putIfAbsent(p.getUniqueId(), pfl);
									}
				                }
				            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									dr1.remove(p.getUniqueId());
				                }
				            }, 25); 
		                	dr1t.put(p.getUniqueId(), task);
		                    
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
			        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);
	                    	Vector dr = p.getLocation().getDirection().normalize();
		                    Location w1 = p.getLocation().setDirection(dr.clone().rotateAroundY(-Math.PI/6).normalize().rotateAroundX(-Math.PI/6).normalize());
		                    Location w2 = p.getLocation().setDirection(dr.clone().rotateAroundY(Math.PI/6).normalize().rotateAroundX(-Math.PI/6).normalize());
	                    	for(int i =0; i<8; i+=2) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.0f);
						                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.3f);
							        		p.teleport(w1);
							        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
							        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
											parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
											parrying.putIfAbsent(p.getUniqueId(), 0);
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
								                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
								                	if(parrying.get(p.getUniqueId())<0) {
														parrying.remove(p.getUniqueId());			                		
								                	}
												}
								            }, 9); 
						                	for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
											{
					                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
													LivingEntity le = (LivingEntity)e;
													atk1(0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025), p, le);												
								                    le.teleport(l);
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
														enar.setDamage((player_damage.get(p.getName())*0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025)));
													}
														{
								                    		p.setSprinting(true);			
										                    le.damage(0,p);
										                    le.damage(player_damage.get(p.getName())*0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025),p);
										                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
															
																
														}*/
												}
											}
						                }
			                	   }, i*2); 
							}
	                    	for(int i =1; i<8; i+=2) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.0f);
						                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.3f);
						                    p.swingOffHand();
							        		p.teleport(w2);
							        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
							        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
							        		parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
											parrying.putIfAbsent(p.getUniqueId(), 0);
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() 
								                {
								                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
								                	if(parrying.get(p.getUniqueId())<0) {
														parrying.remove(p.getUniqueId());			                		
								                	}
												}
								            }, 9); 
						                	for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
											{
					                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
													LivingEntity le = (LivingEntity)e;
													atk1(0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025), p, le);												
								                    le.teleport(l);
												}
											}
						                }
			                	   }, i*2); 
							}
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0.0f);
				                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 2.0f);
					        		p.teleport(pfl);
				                    p.swingMainHand();
									parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
									parrying.putIfAbsent(p.getUniqueId(), 0);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
						                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
						                	if(parrying.get(p.getUniqueId())<0) {
												parrying.remove(p.getUniqueId());			                		
						                	}
										}
						            }, 9); 
					        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
					        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
					        		p.getWorld().spawnParticle(Particle.CRIT, l, 50,1,1,1);
					        		for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
									{
			                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
											LivingEntity le = (LivingEntity)e;
											atk1(0.5*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05), p, le);												
						                    Holding.holding(p, le, 3l);
											/*if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
													ar.setShooter(p);
													ar.setCritical(false);
													ar.setSilent(true);
													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*0.5*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05));
											}
												{
													p.setSprinting(true);
									        		p.getWorld().spawnParticle(Particle.CRIT, p.getEyeLocation(), 10,1,1,1);
									        		p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 10,1,1,1);
								                    le.damage(player_damage.get(p.getName())*0.5*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05),p);
								                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())));
													
														
												}*/
										}
									}
				                }
	                	   }, 17); 
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {

	                	if(dr1t.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(dr1t.get(p.getUniqueId()));
	                		dr1t.remove(p.getUniqueId());
	                	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									dr1.putIfAbsent(p.getUniqueId(), pfl);
								}
			                }
			            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								dr1.remove(p.getUniqueId());
			                }
			            }, 25); 
	                	dr1t.put(p.getUniqueId(), task);
		            	
		            	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
		        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);
                    	Vector dr = p.getLocation().getDirection().normalize();
	                    Location w1 = p.getLocation().setDirection(dr.clone().rotateAroundY(-Math.PI/6).normalize().rotateAroundX(-Math.PI/6).normalize());
	                    Location w2 = p.getLocation().setDirection(dr.clone().rotateAroundY(Math.PI/6).normalize().rotateAroundX(-Math.PI/6).normalize());
	                    for(int i =0; i<8; i+=2) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.0f);
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.3f);
						        		p.teleport(w1);
						        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
						        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
										parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
										parrying.putIfAbsent(p.getUniqueId(), 0);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
							                	if(parrying.get(p.getUniqueId())<0) {
													parrying.remove(p.getUniqueId());			                		
							                	}
											}
							            }, 9); 
					                	for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
										{
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
												LivingEntity le = (LivingEntity)e;
												atk1(0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025), p, le);												
							                    le.teleport(l);
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
													enar.setDamage((player_damage.get(p.getName())*0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025)));
												}
													{
							                    		p.setSprinting(true);			
									                    le.damage(0,p);
									                    le.damage(player_damage.get(p.getName())*0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025),p);
									                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
														
															
													}*/
											}
										}
					                }
		                	   }, i*2); 
						}
                 	for(int i =1; i<8; i+=2) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.0f);
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.3f);
					                    p.swingOffHand();
						        		p.teleport(w2);
						        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
						        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
						        		parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
										parrying.putIfAbsent(p.getUniqueId(), 0);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() 
							                {
							                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
							                	if(parrying.get(p.getUniqueId())<0) {
													parrying.remove(p.getUniqueId());			                		
							                	}
											}
							            }, 9); 
					                	for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
										{
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
												LivingEntity le = (LivingEntity)e;
												atk1(0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025), p, le);												
							                    le.teleport(l);
											}
										}
					                }
		                	   }, i*2); 
						}
                 	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0.0f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 2.0f);
				        		p.teleport(pfl);
			                    p.swingMainHand();
								parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
								parrying.putIfAbsent(p.getUniqueId(), 0);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
					                	if(parrying.get(p.getUniqueId())<0) {
											parrying.remove(p.getUniqueId());			                		
					                	}
									}
					            }, 9); 
				        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
				        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
				        		p.getWorld().spawnParticle(Particle.CRIT, l, 50,1,1,1);
				        		for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
								{
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
										LivingEntity le = (LivingEntity)e;
										atk1(0.5*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05), p, le);												
					                    Holding.holding(p, le, 3l);
										/*if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
												ar.setShooter(p);
												ar.setCritical(false);
												ar.setSilent(true);
												ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*0.5*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05));
										}
											{
												p.setSprinting(true);
								        		p.getWorld().spawnParticle(Particle.CRIT, p.getEyeLocation(), 10,1,1,1);
								        		p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 10,1,1,1);
							                    le.damage(player_damage.get(p.getName())*0.5*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05),p);
							                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())));
												
													
											}*/
									}
								}
			                }
             	   }, 17); 
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	
	

	
	public void DempseyRoll1(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.isSneaking() &&dr1.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				final Location pfl = dr1.get(p.getUniqueId());
				
            	if(dr1t.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(dr1t.get(p.getUniqueId()));
            		dr1t.remove(p.getUniqueId());
            	}
				dr1.remove(p.getUniqueId());

            	if(dr2t.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(dr2t.get(p.getUniqueId()));
            		dr2t.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							dr2.putIfAbsent(p.getUniqueId(), pfl);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						dr2.remove(p.getUniqueId());
	                }
	            }, 25); 
            	dr2t.put(p.getUniqueId(), task);
            	
            	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);
            	Vector dr = p.getLocation().getDirection().normalize();
                Location w1 = p.getLocation().setDirection(dr.clone().rotateAroundY(-Math.PI/6).normalize().rotateAroundX(-Math.PI/6).normalize());
                Location w2 = p.getLocation().setDirection(dr.clone().rotateAroundY(Math.PI/6).normalize().rotateAroundX(-Math.PI/6).normalize());
            	for(int i =0; i<8; i+=2) {
                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.0f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.3f);
				        		p.teleport(w1);
				        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
				        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
								parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
								parrying.putIfAbsent(p.getUniqueId(), 0);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
					                	if(parrying.get(p.getUniqueId())<0) {
											parrying.remove(p.getUniqueId());			                		
					                	}
									}
					            }, 9); 
			                	for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
								{
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
										LivingEntity le = (LivingEntity)e;
										atk1(0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025), p, le);							
					                    le.teleport(l);	
									}
								}
			                }
                	   }, i*2); 
				}
            	for(int i =1; i<8; i+=2) {
                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.0f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.3f);
			                    p.swingOffHand();
				        		p.teleport(w2);
				        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
				        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
				        		parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
								parrying.putIfAbsent(p.getUniqueId(), 0);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
					                	if(parrying.get(p.getUniqueId())<0) {
											parrying.remove(p.getUniqueId());			                		
					                	}
									}
					            }, 9); 
			                	for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
								{
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
										LivingEntity le = (LivingEntity)e;
										atk1(0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025), p, le);								
					                    le.teleport(l);
									}
								}
			                }
                	   }, i*2); 
				}
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0.0f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 2.0f);
		        		p.teleport(pfl);
	                    p.swingMainHand();
						parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
						parrying.putIfAbsent(p.getUniqueId(), 0);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
			                	if(parrying.get(p.getUniqueId())<0) {
									parrying.remove(p.getUniqueId());			                		
			                	}
							}
			            }, 9); 
		        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
		        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
		        		p.getWorld().spawnParticle(Particle.CRIT, l, 50,1,1,1);
		        		for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
						{
                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
								LivingEntity le = (LivingEntity)e;
								atk1(0.5*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05), p, le);		
							}
						}
	                }
        	   }, 17); 
							
			}
		
		}
	}
	

	
	public void DempseyRoll2(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.isSneaking() &&dr2.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				final Location pfl = dr2.get(p.getUniqueId());
				
            	if(dr2t.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(dr2t.get(p.getUniqueId()));
            		dr2t.remove(p.getUniqueId());
            	}
				dr2.remove(p.getUniqueId());
            	
            	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);
            	Vector dr = p.getLocation().getDirection().normalize();
                Location w1 = p.getLocation().setDirection(dr.clone().rotateAroundY(-Math.PI/6).normalize().rotateAroundX(-Math.PI/6).normalize());
                Location w2 = p.getLocation().setDirection(dr.clone().rotateAroundY(Math.PI/6).normalize().rotateAroundX(-Math.PI/6).normalize());
            	for(int i =0; i<8; i+=2) {
                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.0f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.3f);
				        		p.teleport(w1);
				        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
				        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
								parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
								parrying.putIfAbsent(p.getUniqueId(), 0);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
					                	if(parrying.get(p.getUniqueId())<0) {
											parrying.remove(p.getUniqueId());			                		
					                	}
									}
					            }, 9); 
			                	for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
								{
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
										LivingEntity le = (LivingEntity)e;
										atk1(0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025), p, le);								
					                    le.teleport(l);
									}
								}
			                }
                	   }, i*2); 
				}
            	for(int i =1; i<8; i+=2) {
                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.0f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.3f);
			                    p.swingOffHand();
				        		p.teleport(w2);
				        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
				        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
				        		parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
								parrying.putIfAbsent(p.getUniqueId(), 0);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
					                	if(parrying.get(p.getUniqueId())<0) {
											parrying.remove(p.getUniqueId());			                		
					                	}
									}
					            }, 9); 
			                	for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
								{
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
										LivingEntity le = (LivingEntity)e;
										atk1(0.15*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.025), p, le);								
					                    le.teleport(l);
									}
								}
			                }
                	   }, i*2); 
				}
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0.0f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 2.0f);
		        		p.teleport(pfl);
	                    p.swingMainHand();
						parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
						parrying.putIfAbsent(p.getUniqueId(), 0);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
			                	if(parrying.get(p.getUniqueId())<0) {
									parrying.remove(p.getUniqueId());			                		
			                	}
							}
			            }, 9); 
		        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
		        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
		        		p.getWorld().spawnParticle(Particle.CRIT, l, 50,1,1,1);
		        		for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
						{
                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
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
								LivingEntity le = (LivingEntity)e;
								atk1(0.5*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05), p, le);		
							}
						}
	                }
        	   }, 17); 
							
			}
		
		}
	}
	
	
	public void Straight(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 7&& bsd.Straight.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{
			Action ac = ev.getAction();
			double sec = 3*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	
		    
			
			
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
					

				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (frcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("붕권 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Straight").create());
		        		}
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_STEP, 1.0f, 2f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_STEP, 1.0f, 0f);
	                    p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1.0f, 2f);
						parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
						parrying.putIfAbsent(p.getUniqueId(), 0);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
			                	if(parrying.get(p.getUniqueId())<0) {
									parrying.remove(p.getUniqueId());			                		
			                	}
							}
			            }, 9); 
						
	                	if(sct.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(sct.get(p.getUniqueId()));
	                		sct.remove(p.getUniqueId());
	                	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									sc.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								sc.remove(p.getUniqueId());
			                }
			            }, 25); 
	                	sct.put(p.getUniqueId(), task);
	                	
	                    AtomicInteger j = new AtomicInteger(0);
	                    for(double d = 0.1; d <= 4; d += 0.05) {
		                    Location pl = p.getEyeLocation();
							pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
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
         					p.getWorld().spawnParticle(Particle.CRIT,i, 2,1,1,1);
         					p.getWorld().spawnParticle(Particle.FLASH,i, 2,1,1,1);
         					if(i.getBlock().isPassable()) {
	                    	p.teleport(i);
         					}
			                    	for (Entity e : p.getNearbyEntities(2, 2, 2))
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
												le.teleport(p);
										}
									}
					            }
		                	   }, j.incrementAndGet()/17); 
						}); 
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 2, 2, 2))
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
										atk1(0.3*(1+bsd.Straight.get(p.getUniqueId())*0.035), p, le);		
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
											enar.setDamage((player_damage.get(p.getName())*0.3*(1+bsd.Straight.get(p.getUniqueId())*0.03)));
										}
					                    le.damage(0,p);
					                    le.damage(player_damage.get(p.getName())*0.3*(1+bsd.Straight.get(p.getUniqueId())*0.03),p);
					                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())/2+1));
										p.setSprinting(true);*/
					                    p.swingMainHand();
										
									}
								}
				        		p.getWorld().spawnParticle(Particle.CRIT, p.getEyeLocation(), 50,1,1,1);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
			                }
	                    }, j.incrementAndGet()/17); 
						frcooldown.put(p.getName(), System.currentTimeMillis()); 
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    ArrayList<Location> line = new ArrayList<Location>();
                    p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_STEP, 1.0f, 2f);
                    p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_STEP, 1.0f, 0f);
                    p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1.0f, 2f);
					parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
					parrying.putIfAbsent(p.getUniqueId(), 0);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
		                	if(parrying.get(p.getUniqueId())<0) {
								parrying.remove(p.getUniqueId());			                		
		                	}
						}
		            }, 9); 
					
                	if(sct.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(sct.get(p.getUniqueId()));
                		sct.remove(p.getUniqueId());
                	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=1) {
								sc.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 3); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							sc.remove(p.getUniqueId());
		                }
		            }, 25); 
                	sct.put(p.getUniqueId(), task);
                	
                    AtomicInteger j = new AtomicInteger(0);
                    for(double d = 0.1; d <= 4; d += 0.05) {
	                    Location pl = p.getEyeLocation();
						pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
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
     					p.getWorld().spawnParticle(Particle.CRIT,i, 2,1,1,1);
     					p.getWorld().spawnParticle(Particle.FLASH,i, 2,1,1,1);
     					if(i.getBlock().isPassable()) {
                    	p.teleport(i);
     					}
		                    	for (Entity e : p.getNearbyEntities(2, 2, 2))
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
											le.teleport(p);
									}
								}
				            }
	                	   }, j.incrementAndGet()/17); 
					}); 
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 2, 2, 2))
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
									atk1(0.3*(1+bsd.Straight.get(p.getUniqueId())*0.035), p, le);		
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
										enar.setDamage((player_damage.get(p.getName())*0.3*(1+bsd.Straight.get(p.getUniqueId())*0.03)));
									}
				                    le.damage(0,p);
				                    le.damage(player_damage.get(p.getName())*0.3*(1+bsd.Straight.get(p.getUniqueId())*0.03),p);
				                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())/2+1));
									p.setSprinting(true);*/
				                    p.swingMainHand();
									
								}
							}
			        		p.getWorld().spawnParticle(Particle.CRIT, p.getEyeLocation(), 50,1,1,1);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
		                }
                    }, j.incrementAndGet()/17); 
					frcooldown.put(p.getName(), System.currentTimeMillis());
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	

	
	public void SkyCrasher(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)  && !p.isSneaking() &&sc.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
				parrying.putIfAbsent(p.getUniqueId(), 0);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
	                	if(parrying.get(p.getUniqueId())<0) {
							parrying.remove(p.getUniqueId());			                		
	                	}
					}
	            }, 9); 
				
				
            	if(sct.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(sct.get(p.getUniqueId()));
            		sct.remove(p.getUniqueId());
            	}
				sc.remove(p.getUniqueId());
				

            	if(eqt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(eqt.get(p.getUniqueId()));
            		eqt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							eq.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						eq.remove(p.getUniqueId());
	                }
	            }, 25); 
            	eqt.put(p.getUniqueId(), task);

                ArrayList<Location> line = new ArrayList<Location>();
                HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                AtomicInteger j = new AtomicInteger();

				for(int i = 0; i <10; i++) {
					p.teleport(p.getLocation().clone().add(0, 0.2, 0));
					if(!p.getEyeLocation().getBlock().isPassable()) {
						break;
					}
				}
                p.playSound(p.getLocation(), Sound.ENTITY_DOLPHIN_JUMP, 1.0f, 2f);
                p.playSound(p.getLocation(), Sound.ENTITY_HORSE_JUMP, 1.0f, 0f);
                p.playSound(p.getLocation(), Sound.ENTITY_MAGMA_CUBE_JUMP, 1.0f, 2f);
                p.playSound(p.getLocation(), Sound.ENTITY_SKELETON_HORSE_JUMP_WATER, 1.0f, 0f);
                p.playSound(p.getLocation(), Sound.ENTITY_RABBIT_JUMP, 1.0f, 2f);
                for(double an = Math.PI/3; an>-Math.PI/3; an-=Math.PI/180) {
                	Location pl = p.getLocation();
                	Vector v = pl.clone().getDirection().rotateAroundY(Math.PI/2);
                	pl.add(pl.getDirection().rotateAroundAxis(v,an).normalize().multiply(2.5));
                	line.add(pl);
                }
                line.forEach(l -> {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
			        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,5,0.1,0.1,0.1,0);
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 2, 0.1,0.1,0.1,0 ,Material.LIGHT_BLUE_GLAZED_TERRACOTTA.createBlockData());
	                    	for(Entity e: l.getWorld().getNearbyEntities(l,2.75,2.5,2.75)) {
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
								{
									LivingEntity le = (LivingEntity)e;
									les.add(le);
								}
	                    	}
		                }
					}, j.incrementAndGet()/900); 
                	
                });

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
						for (LivingEntity le : les)
						{
							atk1(0.4*(1+bsd.Straight.get(p.getUniqueId())*0.046), p, le);		
							for(int i = 0; i <10; i++) {
								le.teleport(le.getLocation().clone().add(0, 0.2, 0));
								if(!le.getEyeLocation().getBlock().isPassable()) {
									break;
								}
							}
							Holding.holding(p, le, 5l);
		                    p.swingMainHand();
	     					
						}
	                    
	                }
				}, j.incrementAndGet()/900); 
            	
							
			}
		
		}
	}
	
	

	
	public void EarthQuaker(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)  && !p.isSneaking() &&eq.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

				parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
				parrying.putIfAbsent(p.getUniqueId(), 0);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
	                	if(parrying.get(p.getUniqueId())<0) {
							parrying.remove(p.getUniqueId());			                		
	                	}
					}
	            }, 9); 
				
				
            	if(eqt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(eqt.get(p.getUniqueId()));
            		eqt.remove(p.getUniqueId());
            	}
				eq.remove(p.getUniqueId());
				

				
				final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
				
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 0.1f);
				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 0f);
				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1.0f, 2f);
				for(int i = 0; i <10; i++) {
					p.teleport(p.getLocation().clone().add(0, -0.2, 0));
					if(!p.getLocation().getBlock().isPassable()) {
						break;
					}
				}
				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 500, 3,0.5,3,0 ,Material.GRASS_BLOCK.createBlockData());
				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 500, 3,0.5,3,0,Material.IRON_BLOCK.createBlockData());
				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 500, 3,0.5,3,0,Material.BASALT.createBlockData());
				p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 500, 0.5,3,0.5,0.05);
				for(Entity e : p.getWorld().getNearbyEntities(tl,3.5, 3, 3.5)) {
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
					if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;

						atk1(0.54*(1+bsd.Straight.get(p.getUniqueId())*0.05), p, le);	
						for(int i = 0; i <10; i++) {
							le.teleport(le.getLocation().clone().add(0, -0.2, 0));
							if(!le.getLocation().getBlock().isPassable()) {
								break;
							}
						}
						Holding.superholding(p, le, 10l);
	                    p.swingMainHand();
	                    le.damage(0,p);
     					
					}
				}
							
			}
		
		}
	}
	
	
	
	public void Training(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
		{
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 7) {
				LivingEntity le = (LivingEntity)d.getEntity();

				dset2(d, p, 1.25+bsd.Training.get(p.getUniqueId())*0.02405,le, 5);
			}
		}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
				{
					if(ClassData.pc.get(p.getUniqueId()) == 7) {

						LivingEntity le = (LivingEntity)d.getEntity();
						dset2(d, p, 1.25+bsd.Training.get(p.getUniqueId())*0.02405,le, 5);
					}
				}
			}
		}
	}
	

	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 7 && (is.getType().name().contains("BANNER_PATTERN"))&& (is.hasItemMeta()) && is.getItemMeta().hasCustomModelData() && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	
	public void ULT(PlayerDropItemEvent ev)        
    {
	    
		Player p = (Player)ev.getPlayer();
		p.getLocation();
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();

		
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 7 && (is.getType().name().contains("BANNER_PATTERN"))&& (is.hasItemMeta()) && is.getItemMeta().hasCustomModelData()  && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
		    	final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 6).getLocation();
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sultcooldown.get(p.getName())/1000d + 70/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("일격권 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use ONE PUNCH").create());
		        		}
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30,0,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 30,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 30,20,false,false));
						p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_HORSE_BREATHE, 1,0.8f);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,0.8f);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_HORSE_BREATHE, 1,1.8f);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,1.8f);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			    				p.getWorld().spawnParticle(Particle.FLASH, l, 800, 10, 10, 10);
								p.getWorld().spawnParticle(Particle.COMPOSTER, l, 600, 10, 10, 10);
								p.getWorld().spawnParticle(Particle.CRIT, l, 600, 10, 10, 10);
			    				p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 800, 10, 10, 10);
								p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l, 600, 10, 10, 10);
								p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 600, 10, 10, 10);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_SQUID_DEATH, 1f, 1.3f);
								p.playSound(l, Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 1.3f);
								p.playSound(l, Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 0.3f);
								p.playSound(l, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.8f, 2f);
								p.playSound(l, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 2f);
								p.playSound(l, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.8f, 2f);
			                	for(Entity e : p.getWorld().getNearbyEntities(l, 14,14,14)) {

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
												atk1(12.1, p, le);	
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
													enar.setDamage(player_damage.get(p.getName())*12);
												}
												p.setSprinting(true);
												le.damage(0, p);
												le.damage(player_damage.get(p.getName())*12, p);
							                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*2+1));
												*/
												
													
											}
									}
			                	}
							}
			            }, 35); 		
						

						counter.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
						counter.putIfAbsent(p.getUniqueId(), 0);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	counter.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
			                	if(counter.get(p.getUniqueId())<0) {
			                		counter.remove(p.getUniqueId());			                		
			                	}
							}
			            }, 200); 
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

	            	p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30,0,false,false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40,20,false,false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30,20,false,false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 30,20,false,false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 30,20,false,false));
					p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_HORSE_BREATHE, 1,0.8f);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,0.8f);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_HORSE_BREATHE, 1,1.8f);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,1.8f);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		    				p.getWorld().spawnParticle(Particle.FLASH, l, 800, 10, 10, 10);
							p.getWorld().spawnParticle(Particle.COMPOSTER, l, 600, 10, 10, 10);
							p.getWorld().spawnParticle(Particle.CRIT, l, 600, 10, 10, 10);
		    				p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 800, 10, 10, 10);
							p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l, 600, 10, 10, 10);
							p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 600, 10, 10, 10);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
							p.playSound(p.getLocation(), Sound.ENTITY_SQUID_DEATH, 1f, 1.3f);
							p.playSound(l, Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 1.3f);
							p.playSound(l, Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 0.3f);
							p.playSound(l, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.8f, 2f);
							p.playSound(l, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 2f);
							p.playSound(l, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.8f, 2f);
		                	for(Entity e : p.getWorld().getNearbyEntities(l, 14,14,14)) {

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

											atk1(12.1, p, le);
												
										}
								}
		                	}
						}
		            }, 35); 
					counter.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
					counter.putIfAbsent(p.getUniqueId(), 0);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	counter.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
		                	if(counter.get(p.getUniqueId())<0) {
		                		counter.remove(p.getUniqueId());			                		
		                	}
						}
		            }, 200); 
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	

	
	public void ULT2(PlayerDropItemEvent ev)        
    {
	    
		Player p = (Player)ev.getPlayer();
		p.getLocation();
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();

		
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 7 && (is.getType().name().contains("BANNER_PATTERN"))&& (is.hasItemMeta()) && is.getItemMeta().hasCustomModelData()  && !p.isSneaking()&& p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
				if(sult2cooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sult2cooldown.get(p.getName())/1000d + 70*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("철인의 의지 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Will Of Ironman").create());
		        		}
	                }
	                else // if timer is done
	                {
	                    sult2cooldown.remove(p.getName()); // removing player from HashMap
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 80,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80,4,false,false));
						p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_HORSE_BREATHE, 1,0.8f);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,0.8f);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_HORSE_BREATHE, 1,1.8f);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,1.8f);
	                	for(int c = 0; c <39; c ++) {

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 8,8,8)) {
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
											le.teleport(p);
										}
				                	}
								}
				            }, c*2); 
	                	}			
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20,0,false,false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20,20,false,false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20,20,false,false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20,20,false,false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20,20,false,false));
								p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
								p.getWorld().playSound(p.getLocation(),Sound.BLOCK_BEACON_ACTIVATE, 1,0.8f);
			                	for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 5,5,5)) {

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
										Holding.superholding(p,le,20l);
									}
			                	}
			                	
							}
			            }, 80); 
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			    				p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 800, 5, 5, 5);
								p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 600, 5, 5, 5);
			    				p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 800, 5, 5, 5);
								p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation(), 600, 5, 5, 5);
								p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 600, 5, 5, 5);
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 600, 5, 5, 5, Material.IRON_BLOCK.createBlockData());
								p.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, p.getLocation(), 30, 5, 5, 5);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_SQUID_DEATH, 1f, 1.3f);
								p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 1.3f);
								p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 0.3f);
								p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.8f, 2f);
								p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 2f);
								p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.8f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_METAL_PLACE, 0.8f, 0f);
								p.playSound(p.getLocation(), Sound.BLOCK_METAL_BREAK, 0.8f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_METAL_BREAK, 0.8f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.8f, 2f);
			                	for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 5,5,5)) {

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

												atk1(18.1, p, le);	
													
											}
									}
			                	}
							}
			            }, 100); 
						

						counter.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
						counter.putIfAbsent(p.getUniqueId(), 0);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	counter.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
			                	if(counter.get(p.getUniqueId())<0) {
			                		counter.remove(p.getUniqueId());			                		
			                	}
							}
			            }, 200); 
		                sult2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 80,20,false,false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80,4,false,false));
					p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_HORSE_BREATHE, 1,0.8f);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,0.8f);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_HORSE_BREATHE, 1,1.8f);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,1.8f);
                	for(int c = 0; c <39; c ++) {

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 8,8,8)) {
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
										le.teleport(p);
									}
			                	}
							}
			            }, c*2); 
                	}			
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20,0,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20,20,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20,20,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20,20,false,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20,20,false,false));
							p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
							p.getWorld().playSound(p.getLocation(),Sound.BLOCK_BEACON_ACTIVATE, 1,0.8f);
		                	for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 5,5,5)) {

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
									Holding.superholding(p,le,20l);
								}
		                	}
		                	
						}
		            }, 80); 
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		    				p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 800, 5, 5, 5);
							p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 600, 5, 5, 5);
		    				p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 800, 5, 5, 5);
							p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation(), 600, 5, 5, 5);
							p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 600, 5, 5, 5);
							p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 600, 5, 5, 5, Material.IRON_BLOCK.createBlockData());
							p.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, p.getLocation(), 30, 5, 5, 5);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
							p.playSound(p.getLocation(), Sound.ENTITY_SQUID_DEATH, 1f, 1.3f);
							p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 1.3f);
							p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 0.3f);
							p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.8f, 2f);
							p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 2f);
							p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.8f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_METAL_PLACE, 0.8f, 0f);
							p.playSound(p.getLocation(), Sound.BLOCK_METAL_BREAK, 0.8f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_METAL_BREAK, 0.8f, 0f);
							p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.8f, 2f);
		                	for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 5,5,5)) {

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

											atk1(18.1, p, le);	
												
										}
								}
		                	}
						}
		            }, 100); 
					

					counter.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
					counter.putIfAbsent(p.getUniqueId(), 0);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	counter.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
		                	if(counter.get(p.getUniqueId())<0) {
		                		counter.remove(p.getUniqueId());			                		
		                	}
						}
		            }, 200); 
	                sult2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
	    
		Player p = e.getPlayer();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
			{
					e.setCancelled(true);
			}
		}
		
	}

	
	public void CounterStack(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getEntity() instanceof Player && (d.getDamager() instanceof LivingEntity || d.getDamager() instanceof Projectile) && d.getDamage()>0 ) 
		{
		Player p = (Player)d.getEntity();

		
		
		if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && ClassData.pc.get(p.getUniqueId()) == 7) {
			if(Proficiency.getpro(p) >=1) {
				counter.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
				counter.putIfAbsent(p.getUniqueId(), 0);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	counter.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
	                	if(counter.get(p.getUniqueId())<0) {
	                		counter.remove(p.getUniqueId());			                		
	                	}
					}
	            }, 13+bsd.Counter.get(p.getUniqueId())); 
			}
		}}
	}
	
	
	public void Weaving(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getEntity() instanceof Player && (d.getDamager() instanceof LivingEntity || d.getDamager() instanceof Projectile) && d.getDamage()>0 ) 
		{
		Player p = (Player)d.getEntity();

		double sec = 2*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7&&p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
			
				if(p.isSneaking()&&bsd.Weaving.getOrDefault(p.getUniqueId(),0)>=1)
					{
					if(Proficiency.getpro(p) >=1) {
						d.setDamage(d.getDamage()*0.65);
					}
					if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
						double timer = (gdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("위빙 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						    }
			        		else {
				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to weaving").create());
			        		}
			            }
			            else // if timer is done
			            {
			                gdcooldown.remove(p.getName()); // removing player from HashMap
							d.setCancelled(true);
							p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0f, 0.3f);
			        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[위빙]").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
						    }
			        		else {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Weaving]").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
			        		}
							counter.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
							counter.putIfAbsent(p.getUniqueId(), 0);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	counter.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
				                	if(counter.get(p.getUniqueId())<0) {
				                		counter.remove(p.getUniqueId());			                		
				                	}
								}
				            }, 23+bsd.Counter.get(p.getUniqueId())); 
							gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
			        	d.setCancelled(true);
						p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0f, 0.3f);
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[위빙]").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
					    }
		        		else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Weaving]").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
		        		}
						counter.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
						counter.putIfAbsent(p.getUniqueId(), 0);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	counter.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
			                	if(counter.get(p.getUniqueId())<0) {
			                		counter.remove(p.getUniqueId());			                		
			                	}
							}
			            }, 23+bsd.Counter.get(p.getUniqueId())); 
						gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
					
			}
		}}
	}

	
	public void Parrying(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
	    
		
		
		new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));
		if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) 
			{
			if(ClassData.pc.get(p.getUniqueId()) == 7 && p.getAttackCooldown()>=1) {
	
				if(ac == Action.LEFT_CLICK_AIR || ac==Action.LEFT_CLICK_BLOCK)
				{

					parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
					parrying.putIfAbsent(p.getUniqueId(), 0);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	parrying.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
		                	if(parrying.get(p.getUniqueId())<0) {
								parrying.remove(p.getUniqueId());			                		
		                	}
						}
		            }, 3); 
				} 
			}
		}
	}
	
	public void Parrying(EntityDamageByEntityEvent d) 
	{		
	    
		if(d.getEntity() instanceof Player) 
		{
		Player p = (Player)d.getEntity();
		if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {

			
			
			if(ClassData.pc.get(p.getUniqueId()) == 7) {
					if(Proficiency.getpro(p) >=1) {
						d.setDamage(d.getDamage()*0.8);
					}
					if(parrying.containsKey(p.getUniqueId()))
						{

							if(d.getDamager() instanceof LivingEntity) {
								d.setDamage(d.getDamage()*0.3);
								p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1.0f, 2f);
								p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0f, 0.3f);
							}
							if(d.getDamager() instanceof Projectile && d.getDamage()>0) {
								d.setDamage(d.getDamage()*0.3);
								Projectile pr = (Projectile) d.getDamager();
								p.launchProjectile(pr.getClass());
							}
			        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[패링]").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
						    }
			        		else {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Parrying]").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
			        		}
							counter.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
							counter.putIfAbsent(p.getUniqueId(), 0);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	counter.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
				                	if(counter.get(p.getUniqueId())<0) {
				                		counter.remove(p.getUniqueId());			                		
				                	}
								}
				            }, 23+bsd.Counter.get(p.getUniqueId())); 
						
						}
				}	
			}
		}	
	}
	
	
	
	public void Rest(PlayerToggleSneakEvent e) 
	{
	    
		
		Player p = (Player)e.getPlayer();
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7 && bsd.Rest.getOrDefault(p.getUniqueId(),0)>=1)			{	
					if(e.isSneaking())
					{
						int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0, false, false));
				        		p.getWorld().spawnParticle(Particle.SNEEZE, p.getEyeLocation(), 4,1,1,1);
								if(Proficiency.getpro(p) >=1) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 1, false, false));
								}
			                }
						}, 0, 30); 
						if(Proficiency.getpro(p) >=2) {
							counter.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
							counter.putIfAbsent(p.getUniqueId(), 0);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	counter.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
				                	if(counter.get(p.getUniqueId())<0) {
				                		counter.remove(p.getUniqueId());			                		
				                	}
								}
				            }, 10+bsd.Counter.get(p.getUniqueId())); 
						}
						sz.put(p.getUniqueId(), task);
					}
					else
					{
		        		if(sz.containsKey(p.getUniqueId())) {
		        			Bukkit.getServer().getScheduler().cancelTask(sz.get(p.getUniqueId()));
		        			sz.remove(p.getUniqueId());
		        		}
						
					}
		}
	}
	
	
	public void Counter(EntityDamageByEntityEvent d) 
	{
	    
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			Player p = (Player)d.getDamager();
			if(p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
			{
				LivingEntity le = (LivingEntity)d.getEntity();
		
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 7) {
					
						
						if(counter.containsKey(p.getUniqueId())) 
						{
							dset2(d, p, 1.4+bsd.Counter.getOrDefault(p.getUniqueId(),0)*0.025,le,5);
			            	p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 2);
			        		p.playSound(le.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 2f);
						}
					}
			}
		}

		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow a = (Arrow)d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
	
			if(a.getShooter() instanceof Player) {
				Player p = (Player) a.getShooter();
				if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
				{
					
					
					if(ClassData.pc.get(p.getUniqueId()) == 7) {
					
						
						if(counter.containsKey(p.getUniqueId())) 
						{
							dset2(d, p, 1.35+bsd.Counter.getOrDefault(p.getUniqueId(),0)*0.02,le,5);
			            	p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 2);
			        		p.playSound(le.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 2f);
						}
					}
				}
			}
		}
	}
}



