package io.github.chw3021.boxer;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.nobility.NobSkillsData;
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

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;

public class Boxskills implements Listener, Serializable {
	

	
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
	private HashMap<UUID, Integer> fistforce = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> parrying = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> counter = new HashMap<UUID, Integer>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private static HashMap<UUID, Integer> sz = new HashMap<UUID, Integer>();
	static private HashMap<UUID, Integer> fistforcet = new HashMap<UUID, Integer>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private BoxSkillsData bsd;

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

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;

		BoxSkillsData b = new BoxSkillsData(BoxSkillsData.loadData(path +"/plugins/RPGskills/BoxSkillsData.data"));
		bsd = b;
		
	}

	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		BoxSkillsData b = new BoxSkillsData(BoxSkillsData.loadData(path +"/plugins/RPGskills/BoxSkillsData.data"));
		bsd = b;
	}
	
	@EventHandler
	public void FistForce(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && fistforce.containsKey(p.getUniqueId()))
		{
			int sec = 5;
			if(playerclass.get(p.getUniqueId()) == 7)
			{
				
				if(!(p.isSneaking())&& bsd.Parrying.get(p.getUniqueId())>=1)
				{
					ev.setCancelled(true);
					if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use FistForce").create());
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
									p.playSound(p.getLocation(), Sound.ENTITY_SQUID_DEATH, 1f, 1.3f);
				                	for(Entity e : p.getWorld().getNearbyEntities(l, 4,4,4)) {

			                    		if (e instanceof Player) 
										{
											new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
														enar.setDamage(fistforce.get(p.getUniqueId())*1.5);
													}
													p.setSprinting(true);
													le.damage(0, p);
													le.damage(fistforce.get(p.getUniqueId())*1.5, p);
								                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*2+1));
													
													p.setSprinting(false);
														
												}
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
								p.playSound(p.getLocation(), Sound.ENTITY_SQUID_DEATH, 1f, 1.3f);
			                	for(Entity e : p.getWorld().getNearbyEntities(l, 4,4,4)) {

		                    		if (e instanceof Player) 
									{
										new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
													enar.setDamage(fistforce.get(p.getUniqueId())*1.5);
												}
												p.setSprinting(true);
												le.damage(0, p);
												le.damage(fistforce.get(p.getUniqueId())*1.5, p);
							                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*2+1));
												
												p.setSprinting(false);
													
											}
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
		

	@EventHandler
	public void FistForce(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player && d.getCause() != DamageCause.SUICIDE&& d.getCause() != DamageCause.VOID && d.getCause() != DamageCause.STARVATION) {
			Player p = (Player) d.getEntity();
			if(playerclass.get(p.getUniqueId()) == 7)
			{
            	if(fistforcet.containsKey(p.getUniqueId())) {
	            	Bukkit.getServer().getScheduler().cancelTask(fistforcet.get(p.getUniqueId()));
	            	fistforcet.remove(p.getUniqueId());
            	}
				fistforce.computeIfPresent(p.getUniqueId(), (k,v) -> v+(int) (d.getDamage()*3));
				fistforce.putIfAbsent(p.getUniqueId(), (int) (d.getDamage()*3));
				if(fistforce.get(p.getUniqueId())>=200) {
					fistforce.put(p.getUniqueId(), 200);
					d.setDamage(d.getDamage()*0.5);
					if(!fistforcet.containsKey(p.getUniqueId())) {
						int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1,0,false,false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1,0,false,false));
								p.getWorld().spawnParticle(Particle.WARPED_SPORE, p.getLocation(), 10, 0.1, 1, 0.1);
							}
			            }, 3,3); 
						fistforcet.put(p.getUniqueId(), task);
					}
	            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.AQUA + "[FistForce "+ fistforce.get(p.getUniqueId()) + "] (FULL CHARGED)").create());
				}
				else {
	            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.AQUA + "[FistForce "+ fistforce.get(p.getUniqueId()) + "]").create());
				}
			}
			
		}
	}
	@EventHandler
	public void BodyBlow(PlayerInteractAtEntityEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
		{
	    
		Entity e = ev.getRightClicked();
		int sec = 2;

		
		
		if(playerclass.get(p.getUniqueId()) == 7) {
		if((p.isSneaking()) && !p.isSprinting())
		{
				

				if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (swcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use BodyBlow").create());
	                }
	                else // if timer is done
	                {
	                    swcooldown.remove(p.getName()); // removing player from HashMap
	                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)e;
							{
								
		                    	Vector dr = p.getLocation().getDirection().normalize();
								Location w = p.getLocation().setDirection(dr.clone().rotateAroundY(Math.PI/8).normalize().rotateAroundX(-Math.PI/8).normalize());
								p.teleport(w);
								p.setInvulnerable(true);
								Location str  =le.getLocation().setDirection(dr.clone());
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
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
										p.teleport(str);
										p.setInvulnerable(false);
					                	for (Entity e : p.getWorld().getNearbyEntities(str, 3, 3, 3))
										{
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
															enar.setDamage((player_damage.get(p.getName())*1.15*(1+ bsd.BodyBlow.get(p.getUniqueId())*0.035)));
														}
														p.setSprinting(true);
														le.damage(0, p);
														le.damage(player_damage.get(p.getName())*1.15*(1+ bsd.BodyBlow.get(p.getUniqueId())*0.035), p);
									                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*2+1));
														
														le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
														le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
														p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
														p.playSound(p.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1f, 0f);
														p.playSound(p.getLocation(), Sound.BLOCK_METAL_HIT, 1f, 0f);
														p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1f, 2f);
														p.getWorld().spawnParticle(Particle.ASH, e.getLocation(), 20, 1, 1, 1);
														p.setSprinting(false);
															
													}
											}
				                    		else if (e instanceof Player) 
											{
												new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
										}
					                }
		                	   }, 1); 
							}
						}
		                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
					{
						LivingEntity le = (LivingEntity)e;
						{
							
	                    	Vector dr = p.getLocation().getDirection().normalize();
							Location w = p.getLocation().setDirection(dr.clone().rotateAroundY(Math.PI/8).normalize().rotateAroundX(-Math.PI/8).normalize());
							p.teleport(w);
							p.setInvulnerable(true);
							Location str  =le.getLocation().setDirection(dr.clone());
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
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
									p.teleport(str);
									p.setInvulnerable(false);
				                	for (Entity e : p.getWorld().getNearbyEntities(str, 3, 3, 3))
									{
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
														enar.setDamage((player_damage.get(p.getName())*1.15*(1+ bsd.BodyBlow.get(p.getUniqueId())*0.035)));
													}
													p.setSprinting(true);
													le.damage(0, p);
													le.damage(player_damage.get(p.getName())*1.15*(1+ bsd.BodyBlow.get(p.getUniqueId())*0.035), p);
								                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*2+1));
													
													le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
													le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
													p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
													p.playSound(p.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1f, 0f);
													p.playSound(p.getLocation(), Sound.BLOCK_METAL_HIT, 1f, 0f);
													p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1f, 2f);
													p.getWorld().spawnParticle(Particle.ASH, e.getLocation(), 20, 1, 1, 1);
													p.setSprinting(false);
														
												}
										}
			                    		else if (e instanceof Player) 
										{
											new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
									}
				                }
	                	   }, 1); 
						}
					}
	                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                
	            }
			}
		}
		}
	}

	@EventHandler
	public void DempseyRoll(PlayerSwapHandItemsEvent ev) 
	{
	    
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
		{
		int sec = 6;

		
		
		if(playerclass.get(p.getUniqueId()) == 7 && p.isSneaking()) {
				ev.setCancelled(true);
				
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use DempsyRoll").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
			        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);
	                    	Vector dr = p.getLocation().getDirection().normalize();
		                    Location st = p.getLocation();
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
							        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
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
					                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
												{
						                    		if (e instanceof Player) 
													{
														new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
														enar.setDamage((player_damage.get(p.getName())*0.3*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05)));
													}
														{
								                    		p.setSprinting(true);															
										                    le.teleport(l);
										                    le.damage(0,p);
										                    le.damage(player_damage.get(p.getName())*0.3*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05),p);
										                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
															p.setSprinting(false);
																
														}
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
							        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
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
					                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
												{
						                    		if (e instanceof Player) 
													{
														new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
														enar.setDamage((player_damage.get(p.getName())*0.3*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05)));
													}
														{
															p.setSprinting(true);
										                    le.teleport(l);
										                    le.damage(0,p);
										                    le.damage(player_damage.get(p.getName())*0.3*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05),p);
										                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
															p.setSprinting(false);
																
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
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0.0f);
				                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 2.0f);
					        		p.teleport(st);
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
					        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
					        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
					        		p.getWorld().spawnParticle(Particle.CRIT, l, 50,1,1,1);
					        		for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
									{
			                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
										{
				                    		if (e instanceof Player) 
											{
												new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
											LivingEntity le = (LivingEntity)e;
											if(le instanceof EnderDragon) {
							                    Arrow firstarrow = p.launchProjectile(Arrow.class);
							                    firstarrow.setDamage(0);
							                    firstarrow.remove();
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
													ar.setShooter(p);
													ar.setCritical(false);
													ar.setSilent(true);
													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.1));
											}
												{
													p.setSprinting(true);
									        		p.getWorld().spawnParticle(Particle.CRIT, p.getEyeLocation(), 10,1,1,1);
									        		p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 10,1,1,1);
								                    le.damage(player_damage.get(p.getName())*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.1),p);
								                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())));
													p.setSprinting(false);
														
												}
										}
									}
				                }
	                	   }, 17); 
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
		        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);
                    	Vector dr = p.getLocation().getDirection().normalize();
	                    Location st = p.getLocation();
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
						        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
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
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
											{
					                    		if (e instanceof Player) 
												{
													new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
													enar.setDamage((player_damage.get(p.getName())*0.3*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05)));
												}
													{
							                    		p.setSprinting(true);															
									                    le.teleport(l);
									                    le.damage(0,p);
									                    le.damage(player_damage.get(p.getName())*0.3*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05),p);
									                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
														p.setSprinting(false);
															
													}
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
						        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
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
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
											{
					                    		if (e instanceof Player) 
												{
													new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
													enar.setDamage((player_damage.get(p.getName())*0.3*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05)));
												}
													{
														p.setSprinting(true);
									                    le.teleport(l);
									                    le.damage(0,p);
									                    le.damage(player_damage.get(p.getName())*0.3*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.05),p);
									                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
														p.setSprinting(false);
															
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
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0.0f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 2.0f);
				        		p.teleport(st);
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
				        		Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
				        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 5,1,1,1);
				        		p.getWorld().spawnParticle(Particle.CRIT, l, 50,1,1,1);
				        		for (Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3))
								{
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
									{
			                    		if (e instanceof Player) 
										{
											new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
										LivingEntity le = (LivingEntity)e;
										if(le instanceof EnderDragon) {
						                    Arrow firstarrow = p.launchProjectile(Arrow.class);
						                    firstarrow.setDamage(0);
						                    firstarrow.remove();
											Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
												ar.setShooter(p);
												ar.setCritical(false);
												ar.setSilent(true);
												ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
											});
											enar.setDamage(player_damage.get(p.getName())*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.1));
										}
											{
												p.setSprinting(true);
								        		p.getWorld().spawnParticle(Particle.CRIT, p.getEyeLocation(), 10,1,1,1);
								        		p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 10,1,1,1);
							                    le.damage(player_damage.get(p.getName())*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.1),p);
							                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())));
												p.setSprinting(false);
													
											}
									}
								}
			                }
                	   }, 17); 
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}
	
	@EventHandler
	public void Straight(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
		{
		Action ac = ev.getAction();
		int sec = 3;

	    
		
		
		if(playerclass.get(p.getUniqueId()) == 7) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
				

				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Straight").create());
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1.0f, 2f);
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
	                    AtomicInteger j = new AtomicInteger(0);
	                    AtomicInteger b = new AtomicInteger(0);
	                    for(double d = 0.1; d <= 4; d += 0.05) {
		                    Location pl = p.getEyeLocation();
							pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
							line.add(pl);
	                    }
	                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
		                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
		                    for(int k=0; k<line.indexOf(block); k++) {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
			             					p.getWorld().spawnParticle(Particle.CRIT,line.get(b.getAndIncrement()), 2,1,1,1);
			             					p.getWorld().spawnParticle(Particle.FLASH,line.get(b.get()), 2,1,1,1);
					                    	p.teleport(line.get(b.get()));
					                    	for (Entity e : p.getNearbyEntities(2, 2, 2))
											{
					                    		if (e instanceof Player) 
												{
													new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
													le.teleport(p);
												}
											}
							            }
				                	   }, j.incrementAndGet()/17); 
								 }}
	                    else {
		                    	line.forEach(i -> {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
	             					p.getWorld().spawnParticle(Particle.CRIT,i, 2,1,1,1);
	             					p.getWorld().spawnParticle(Particle.FLASH,i, 2,1,1,1);
			                    	p.teleport(i);
					                    	for (Entity e : p.getNearbyEntities(2, 2, 2))
											{
					                    		if (e instanceof Player) 
												{
													new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
						                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
													{
														LivingEntity le = (LivingEntity)e;
														le.teleport(p);
													}
												}
											}
							            }
				                	   }, j.incrementAndGet()/17); 
								}); 
	                    	}
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 2, 2, 2))
								{
		                    		if (e instanceof Player) 
									{
										new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
											enar.setDamage((player_damage.get(p.getName())*0.7*(1+bsd.Straight.get(p.getUniqueId())*0.03)));
										}
										p.setSprinting(true);
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
						        		p.getWorld().spawnParticle(Particle.CRIT, p.getEyeLocation(), 10,1,1,1);
					                    p.swingMainHand();
					                    le.damage(0,p);
					                    le.damage(player_damage.get(p.getName())*0.7*(1+bsd.Straight.get(p.getUniqueId())*0.03),p);
					                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())/2+1));
										p.setSprinting(false);
									}
								}
			                }
	            	   }, j.incrementAndGet()/17); 
						frcooldown.put(p.getName(), System.currentTimeMillis()); 
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	ArrayList<Location> line = new ArrayList<Location>();
                    p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1.0f, 2f);
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
                    AtomicInteger j = new AtomicInteger(0);
                    AtomicInteger b = new AtomicInteger(0);
                    for(double d = 0.1; d <= 4; d += 0.05) {
	                    Location pl = p.getEyeLocation();
						pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
						line.add(pl);
                    }
                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
	                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
	                    for(int k=0; k<line.indexOf(block); k++) {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
             					p.getWorld().spawnParticle(Particle.CRIT,line.get(b.getAndIncrement()), 2,1,1,1);
             					p.getWorld().spawnParticle(Particle.FLASH,line.get(b.get()), 2,1,1,1);
		                    	p.teleport(line.get(b.get()));
				                    	for (Entity e : p.getNearbyEntities(2, 2, 2))
										{
				                    		if (e instanceof Player) 
											{
												new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
												le.teleport(p);
											}
										}
						            }
			                	   }, j.incrementAndGet()/17); 
							 }}
                    else {
	                    	line.forEach(i -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
             					p.getWorld().spawnParticle(Particle.CRIT,i, 2,1,1,1);
             					p.getWorld().spawnParticle(Particle.FLASH,i, 2,1,1,1);
		                    	p.teleport(i);
				                    	for (Entity e : p.getNearbyEntities(2, 2, 2))
										{
				                    		if (e instanceof Player) 
											{
												new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
					                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
												{
													LivingEntity le = (LivingEntity)e;
													le.teleport(p);
												}
											}
										}
						            }
			                	   }, j.incrementAndGet()/17); 
							}); 
                    	}
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 2, 2, 2))
							{
	                    		if (e instanceof Player) 
								{
									new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
										enar.setDamage((player_damage.get(p.getName())*0.7*(1+bsd.Straight.get(p.getUniqueId())*0.03)));
									}
									p.setSprinting(true);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
					        		p.getWorld().spawnParticle(Particle.CRIT, p.getEyeLocation(), 10,1,1,1);
				                    p.swingMainHand();
				                    le.damage(0,p);
				                    le.damage(player_damage.get(p.getName())*0.7*(1+bsd.Straight.get(p.getUniqueId())*0.03),p);
				                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())/2+1));
									p.setSprinting(false);
								}
							}
		                }
            	   }, j.incrementAndGet()/17); 
					frcooldown.put(p.getName(), System.currentTimeMillis());
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	@EventHandler
	public void Training(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
		{
		LivingEntity le = (LivingEntity)d.getEntity();
		Location el =le.getLocation();
		el.setY(el.getY()+1);

		
		
			if(playerclass.get(p.getUniqueId()) == 7) {
				
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4+p.getLevel()*0.03+bsd.Training.get(p.getUniqueId())*0.2);
						p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(1+p.getLevel()*0.002+bsd.Training.get(p.getUniqueId())*0.05);
						if(p.getAttackCooldown()==1) 
						{	
							if(p.isSneaking() && !p.isSprinting()) {
								d.setDamage(d.getDamage()*(1.25+bsd.Training.get(p.getUniqueId())*0.01));
								le.teleport(el);	
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.8f);		
								p.playSound(p.getLocation(), Sound.ENTITY_RABBIT_JUMP, 1.0f, 0.8f);						
							}
							if(!p.isSneaking() &&!p.isOnGround() && !p.isSprinting()) {
								d.setDamage(d.getDamage()*(1.25+bsd.Training.get(p.getUniqueId())*0.01));
								p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1.0f, 2.0f);		
							}
						}
				}
		}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof EnderDragon) 
		{
			Arrow ar = (Arrow)d.getDamager();
			d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
				{
					if(playerclass.get(p.getUniqueId()) == 7) {
						
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4*(bsd.Training.get(p.getUniqueId())*0.2));
					}
				}
			}
		}
	}
	

	
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
	    
		Player p = (Player)ev.getPlayer();
		p.getLocation();
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 7 && (is.getType().name().contains("NUGGET")) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 35) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Adrenaline").create());
	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 2f);
						p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200+p.getLevel()*3, 10, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200+p.getLevel()*3, 10, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200+p.getLevel()*3, 3, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200+p.getLevel()*3, 1, false, false));						
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 2f);
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200+p.getLevel()*3, 10, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200+p.getLevel()*3, 10, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200+p.getLevel()*3, 3, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200+p.getLevel()*3, 1, false, false));	
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
	    
		Player p = e.getPlayer();

		
		
		if(playerclass.get(p.getUniqueId()) == 7)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
			{
					e.setCancelled(true);
			}
		}
		
	}
		
	@EventHandler
	public void Weaving(EntityDamageByEntityEvent d) 
	{
	    
		double sec = 1.25;
		if(d.getEntity() instanceof Player && (d.getDamager() instanceof LivingEntity || d.getDamager() instanceof Projectile) && d.getDamage()>0 ) 
		{
		Player p = (Player)d.getEntity();

		
		
		if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && playerclass.get(p.getUniqueId()) == 7) {
			
				if(p.isSneaking()&&bsd.Weaving.get(p.getUniqueId())>=1)
					{
					if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
						double timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to weaving").create());
			            }
			            else // if timer is done
			            {
			                gdcooldown.remove(p.getName()); // removing player from HashMap
							d.setCancelled(true);
							p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0f, 0.3f);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Weaving]").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
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
				            }, 32); 
							gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
			        	d.setCancelled(true);
						p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0f, 0.3f);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Weaving").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
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
			            }, 32); 
						gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
					
			}
		}}
	}

	@EventHandler
	public void Parrying(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
	    
		
		
		new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));
		if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET")) 
			{
			if(playerclass.get(p.getUniqueId()) == 7 && p.getAttackCooldown()>=1) {
	
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
	@EventHandler
	public void Parrying(EntityDamageByEntityEvent d) 
	{		
	    
		if(d.getEntity() instanceof Player) 
		{
		Player p = (Player)d.getEntity();
		if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET")) {

			
			
			if(playerclass.get(p.getUniqueId()) == 7) {
					if(parrying.containsKey(p.getUniqueId()))
						{

							if(d.getDamager() instanceof LivingEntity) {
								d.setDamage(d.getDamage()*0.3);
								p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1.0f, 2f);
								p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0f, 0.3f);
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Parrying").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
							}
							if(d.getDamager() instanceof Projectile && d.getDamage()>0) {
								d.setDamage(d.getDamage()*0.3);
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Parrying").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
								Projectile pr = (Projectile) d.getDamager();
								p.launchProjectile(pr.getClass());
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
				            }, 32); 
						
						}
				}	
			}
		}	
	}
	
	
	@EventHandler
	public void Rest(PlayerToggleSneakEvent e) 
	{
	    
		
		Player p = (Player)e.getPlayer();
		
		
		
		if(playerclass.get(p.getUniqueId()) == 7 && bsd.Rest.get(p.getUniqueId())>=1)			{	
					if(e.isSneaking())
					{
						int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 1, false, false));
				        		p.getWorld().spawnParticle(Particle.SNEEZE, p.getEyeLocation(), 4,1,1,1);
			                }
						}, 0, 30); 
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
	
	@EventHandler
	public void Counter(EntityDamageByEntityEvent d) 
	{
	    
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			Player p = (Player)d.getDamager();
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
			{
				LivingEntity le = (LivingEntity)d.getEntity();
		
				
				
				if(playerclass.get(p.getUniqueId()) == 7) {
					
						
						if(counter.containsKey(p.getUniqueId())) 
						{
							d.setDamage(d.getDamage()*(1.35+bsd.Counter.get(p.getUniqueId())*0.043));
			            	p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 2);
			        		p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0f, 2f);
			        		p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 2f);
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
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
				{
					
					
					if(playerclass.get(p.getUniqueId()) == 7) {
					
						
						if(counter.containsKey(p.getUniqueId())) 
						{
							d.setDamage(d.getDamage()*(1.35+bsd.Counter.get(p.getUniqueId())*0.043));
			            	p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 2);
			        		p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0f, 2f);
			        		p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 2f);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void dyeCancel(SheepDyeWoolEvent e) 
	{
		e.setCancelled(true);
	}

	@EventHandler
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
			{
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 7) {
					if(playerclass.get(p.getUniqueId()) == 7) {
							player_damage.put(p.getName(), 4+ p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
							if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
							{
								player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
							}
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
	
			
			
				if(playerclass.get(p.getUniqueId()) == 7) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
							player_damage.put(p.getName(), p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
			Arrow a = (Arrow)d.getDamager();
			Entity e = d.getEntity();
	
			
			
			if(a.getShooter() instanceof Player) {
				Player p = (Player) a.getShooter();
				if(playerclass.get(p.getUniqueId()) == 7) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
							player_damage.put(p.getName(), p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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



