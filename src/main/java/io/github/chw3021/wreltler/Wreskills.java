package io.github.chw3021.wreltler;



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

import org.bukkit.Bukkit;
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
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
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
import org.bukkit.inventory.ItemStack;

public class Wreskills implements Listener, Serializable {
	

	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 8481741046360618590L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Integer> locking = new HashMap<String, Integer>();
	private HashMap<String, Integer> lockingt = new HashMap<String, Integer>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<Player, Integer> tr = new HashMap<Player, Integer>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private WreSkillsData wsd = new WreSkillsData(WreSkillsData.loadData(path +"/plugins/RPGskills/WreSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
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
				ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
				playerclass = cdata.playerclass;
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
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		WreSkillsData w = new WreSkillsData(WreSkillsData.loadData(path +"/plugins/RPGskills/WreSkillsData.data"));
		wsd = w;
		
	}
	
	@EventHandler
	public void Lockcool(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 8;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 8)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && !(p.isSneaking()) && !locking.containsKey(p.getName()))
			{
				ev.setCancelled(true);
				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (frcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Lock").create());
	                }
	                else // if timer is done
	                {
	                	frcooldown.remove(p.getName()); // removing player from HashMap
	                	locking.computeIfPresent(p.getName(), (k,v) -> v+1);
	                	locking.putIfAbsent(p.getName(), 0);
	                    frcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                	locking.computeIfPresent(p.getName(), (k,v) -> v+1);
                	locking.putIfAbsent(p.getName(), 0);
                    frcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
	}
		

	@EventHandler
	public void Lock(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		
		if(playerclass.get(p.getUniqueId()) == 8)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && !(p.isSneaking()))
			{
				ev.setCancelled(true);
				if(locking.containsKey(p.getName())) {
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.3f, 1.5f);
					Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 1).getLocation();
                    
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_BIG_FALL, 0.5f, 0.6f);
                	for (Entity e : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
					{
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
										enar.setDamage(player_damage.get(p.getName())*0.1+wsd.LegDrop.get(p.getUniqueId())*0.1);								
									}
									Holding.superholding(p, le, 20l);
									le.teleport(p);
									p.teleport(le);
									p.setSprinting(true);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
				                    le.damage(0,p);
				                    le.damage(player_damage.get(p.getName())*0.1+wsd.LegDrop.get(p.getUniqueId())*0.1,p);
				                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())/2+1));
									p.setSprinting(false);
										
								}
						}
					}
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	locking.computeIfPresent(p.getName(), (k,v) -> v-1);
		                	if(locking.getOrDefault(p.getName(), -1) <0) {
			                	locking.remove(p.getName());
		                	}
		                }
                    }, 60); 
				}
			}
		}
	}
	@EventHandler
	public void ChokeSlam(PlayerInteractAtEntityEvent ev) 
	{
		Player p = ev.getPlayer();
		Entity e = ev.getRightClicked();
		int sec = 4;
        

		
		
		if(playerclass.get(p.getUniqueId()) == 8) {
		if((p.isSneaking()) && !p.isSprinting())
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
			{
				

				if(swcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (swcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use ChokeSlam").create());
	                }
	                else // if timer is done
	                {
	                    swcooldown.remove(p.getName()); // removing player from HashMap
	                    if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)e;
							if(le.getType()!=EntityType.PLAYER && le.getType()!=EntityType.ENDER_DRAGON)
							{
								Holding.superholding(p, le, (long) 45);
								p.swingOffHand();
								p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1, 0);
								p.setGliding(true);
								le.teleport(p.getEyeLocation());
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	if((le instanceof Player)) {
											le.teleport(le.getLocation().add(0, -0.5, 0));
					                	}
					                	else {
											le.teleport(le.getLocation().add(0, -1.5, 0));
					                	}
										p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 1, 0);
										p.playSound(p.getLocation(), Sound.BLOCK_WEEPING_VINES_PLACE, 1, 0);
										for (Entity e : le.getWorld().getNearbyEntities(le.getLocation(),4, 4, 4))
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
															enar.setDamage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7);								
														}
							                    		p.setSprinting(true);															
									                    le.teleport(p);
														p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
									                    le.damage(0,p);
									                    le.damage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7,p);
									                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
														p.setSprinting(false);
															
													}
											}
										}
					                }
		                	   }, 10); 
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
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
											enar.setDamage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7);								
										}
										p.playSound(p.getLocation(), Sound.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, 1, 0);
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
										p.setSprinting(true);	
					                    le.damage(0,p);
					                    le.damage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7,p);
										p.setSprinting(false);
					                }
		                	   }, 30); 
							}
							else {
								if (e instanceof Player) 
								{
									PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
									Player p1 = (Player) e;
									try {
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
											return;
										}
									}}
									catch(NullPointerException ne) {
										return;
									}
								}
								Holding.superholding(p, le, (long) 45);
								p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1, 0);
								p.setGliding(true);
								le.teleport(p.getEyeLocation());
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	if((le instanceof Player)) {
											le.teleport(le.getLocation().add(0, -0.5, 0));
					                	}
					                	else {
											le.teleport(le.getLocation().add(0, -1.5, 0));
					                	}
										p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 1, 0);
										p.playSound(p.getLocation(), Sound.BLOCK_WEEPING_VINES_PLACE, 1, 0);
										for (Entity e : le.getWorld().getNearbyEntities(le.getLocation(),4, 4, 4))
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
															enar.setDamage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7);								
														}
							                    		p.setSprinting(true);												
														p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());			
									                    le.teleport(p);
									                    le.damage(0,p);
									                    le.damage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7,p);
									                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
														p.setSprinting(false);
															
													}
											}
										}
					                }
		                	   }, 10); 
								
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
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
											enar.setDamage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7);								
										}
					                	p.setSprinting(true);
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
										p.playSound(p.getLocation(), Sound.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, 1, 0);
					                    le.damage(0,p);
					                    le.damage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7,p);
										p.setSprinting(false);
					                }
		                	   }, 30); 
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
						if(le.getType()!=EntityType.PLAYER && le.getType()!=EntityType.ENDER_DRAGON)
						{
							Holding.superholding(p, le, (long) 45);
							p.swingOffHand();
							p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1, 0);
							p.setGliding(true);
							le.teleport(p.getEyeLocation());
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	if((le instanceof Player)) {
										le.teleport(le.getLocation().add(0, -0.5, 0));
				                	}
				                	else {
										le.teleport(le.getLocation().add(0, -1.5, 0));
				                	}
									p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 1, 0);
									p.playSound(p.getLocation(), Sound.BLOCK_WEEPING_VINES_PLACE, 1, 0);
									for (Entity e : le.getWorld().getNearbyEntities(le.getLocation(),4, 4, 4))
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
														enar.setDamage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7);								
													}
						                    		p.setSprinting(true);															
								                    le.teleport(p);
													p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
								                    le.damage(0,p);
								                    le.damage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7,p);
								                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
													p.setSprinting(false);
														
												}
										}
									}
				                }
	                	   }, 10); 
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
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
										enar.setDamage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7);								
									}
									p.playSound(p.getLocation(), Sound.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, 1, 0);
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
									p.setSprinting(true);	
				                    le.damage(0,p);
				                    le.damage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7,p);
									p.setSprinting(false);
				                }
	                	   }, 30); 
						}
						else {
							if (e instanceof Player) 
							{
								PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
								Player p1 = (Player) e;
								try {
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										return;
									}
								}}
								catch(NullPointerException ne) {
									return;
								}
							}
							Holding.superholding(p, le, (long) 45);
							p.playSound(p.getLocation(), Sound.BLOCK_SAND_BREAK, 1, 0);
							p.setGliding(true);
							le.teleport(p.getEyeLocation());
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	if((le instanceof Player)) {
										le.teleport(le.getLocation().add(0, -0.5, 0));
				                	}
				                	else {
										le.teleport(le.getLocation().add(0, -1.5, 0));
				                	}
									p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 1, 0);
									p.playSound(p.getLocation(), Sound.BLOCK_WEEPING_VINES_PLACE, 1, 0);
									for (Entity e : le.getWorld().getNearbyEntities(le.getLocation(),4, 4, 4))
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
														enar.setDamage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7);								
													}
						                    		p.setSprinting(true);												
													p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());			
								                    le.teleport(p);
								                    le.damage(0,p);
								                    le.damage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7,p);
								                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())*0.8+0.5));
													p.setSprinting(false);
														
												}
										}
									}
				                }
	                	   }, 10); 
							
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
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
										enar.setDamage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7);								
									}
				                	p.setSprinting(true);
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
									p.playSound(p.getLocation(), Sound.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, 1, 0);
				                    le.damage(0,p);
				                    le.damage(player_damage.get(p.getName())*1.8+wsd.ChokeSlam.get(p.getUniqueId())*0.7,p);
									p.setSprinting(false);
				                }
	                	   }, 30); 
						}
					}
	                swcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                
	            }
			}
		}
		}
	}

	@EventHandler
	public void ChainingSuplex(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 6;

        
		
		
		if(playerclass.get(p.getUniqueId()) == 8) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && p.isSneaking())
			{
				ev.setCancelled(true);
				
				
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (cdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use ChainingSuplex").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    p.swingOffHand();
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
			        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);
		                    p.setGliding(true);
		                    ArrayList<Location> air = new ArrayList<Location>();
		                    ArrayList<Location> sight = new ArrayList<Location>();
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
		                    AtomicInteger j = new AtomicInteger(0);
		                    AtomicInteger k = new AtomicInteger(0);
		                    for(double i=Math.PI/180; i > -Math.PI; i -= Math.PI/180) {
		                    	Location eye = p.getEyeLocation().clone();
		                    	Vector eyev = eye.getDirection().clone().rotateAroundY(Math.PI/2);
		                    	Location a = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, i).normalize());
		                    	Location b = eye.clone().add(eye.getDirection().rotateAroundAxis(eyev, i).normalize().multiply(2));
		                    	sight.add(a);
		                    	air.add(b);
		                    }
		                    for(Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3)) {
		                    	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
		                    		LivingEntity le = (LivingEntity)e;
		                    		if(le instanceof Player) {
										Player p1 = (Player) e;
										if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
											if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
												{
													continue;
												}
											}
										else {
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
																enar.setDamage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95);								
															}
							             					p.setSprinting(true);	
									                    	le.damage(0,p);
															p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
									                    	le.damage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95,p);
										                    p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_MIDDLE, 1, 0);
															p.setSprinting(false);
									                    	le.teleport(p.getLocation().add(0, -1, 0));
											            }
								            }, j.incrementAndGet()/30+10);
											}
		                    		}
		                    		else if(le.getType() == EntityType.ENDER_DRAGON) {
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
														enar.setDamage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95);								
														}
						             					p.setSprinting(true);	
								                    	le.damage(0,p);
														p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
														le.damage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95,p);
								                    	le.teleport(p.getLocation().add(0, -1, 0));
									                    p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_MIDDLE, 1, 0);
														p.setSprinting(false);
										            }
							            }, j.incrementAndGet()/30+10); 		
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
										{
						             	@Override
								                public void run() 
						             				{	
								                    	le.setCollidable(true);
										            }
							            }, j.incrementAndGet()/30+20); 	
		                    		}
		                    		else {
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
															enar.setDamage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95);								
														}
						             					p.setSprinting(true);	
								                    	le.damage(0,p);
														p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
								                    	le.damage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95,p);
								                    	le.teleport(p.getLocation().add(0, -1, 0));
									                    p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_MIDDLE, 1, 0);
														p.setSprinting(false);
										            }
							            }, j.incrementAndGet()/30+10); 		
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
										{
						             	@Override
								                public void run() 
						             				{	
								                    	le.setCollidable(true);
										            }
							            }, j.incrementAndGet()/30+20); 	
		                    		}
		                    	}
		                    }
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	p.swingOffHand();
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
		        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);
	                    p.setGliding(true);
	                    ArrayList<Location> air = new ArrayList<Location>();
	                    ArrayList<Location> sight = new ArrayList<Location>();
	                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
	                    AtomicInteger j = new AtomicInteger(0);
	                    AtomicInteger k = new AtomicInteger(0);
	                    for(double i=Math.PI/180; i > -Math.PI; i -= Math.PI/180) {
	                    	Location eye = p.getEyeLocation().clone();
	                    	Vector eyev = eye.getDirection().clone().rotateAroundY(Math.PI/2);
	                    	Location a = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, i).normalize());
	                    	Location b = eye.clone().add(eye.getDirection().rotateAroundAxis(eyev, i).normalize().multiply(2));
	                    	sight.add(a);
	                    	air.add(b);
	                    }
	                    for(Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3)) {
	                    	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
	                    		LivingEntity le = (LivingEntity)e;
	                    		if(le instanceof Player) {
									Player p1 = (Player) e;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
											{
												continue;
											}
										}
									else {
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
															enar.setDamage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95);								
														}
						             					p.setSprinting(true);	
								                    	le.damage(0,p);
														p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
								                    	le.damage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95,p);
									                    p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_MIDDLE, 1, 0);
														p.setSprinting(false);
								                    	le.teleport(p.getLocation().add(0, -1, 0));
										            }
							            }, j.incrementAndGet()/30+10);
										}
	                    		}
	                    		else if(le.getType() == EntityType.ENDER_DRAGON) {
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
													enar.setDamage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95);								
													}
					             					p.setSprinting(true);	
							                    	le.damage(0,p);
													p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
													le.damage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95,p);
							                    	le.teleport(p.getLocation().add(0, -1, 0));
								                    p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_MIDDLE, 1, 0);
													p.setSprinting(false);
									            }
						            }, j.incrementAndGet()/30+10); 		
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
									{
					             	@Override
							                public void run() 
					             				{	
							                    	le.setCollidable(true);
									            }
						            }, j.incrementAndGet()/30+20); 	
	                    		}
	                    		else {
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
														enar.setDamage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95);								
													}
					             					p.setSprinting(true);	
							                    	le.damage(0,p);
													p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 2,2,2,0 ,Material.DIRT.createBlockData());
							                    	le.damage(player_damage.get(p.getName())*1.34 + wsd.ChainingSuplex.get(p.getUniqueId())*0.95,p);
							                    	le.teleport(p.getLocation().add(0, -1, 0));
								                    p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_MIDDLE, 1, 0);
													p.setSprinting(false);
									            }
						            }, j.incrementAndGet()/30+10); 		
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
									{
					             	@Override
							                public void run() 
					             				{	
							                    	le.setCollidable(true);
									            }
						            }, j.incrementAndGet()/30+20); 	
	                    		}
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
		int sec = 5;

        
		
		
		if(playerclass.get(p.getUniqueId()) == 8) {
		if(!(p.isSneaking())&& !p.isOnGround() && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
			{

				
				if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use BodyPress").create());
	                }
	                else // if timer is done
	                {
	                    sdcooldown.remove(p.getName()); // removing player from HashMap
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
													enar.setDamage(player_damage.get(p.getName())*1.78+wsd.BodyPress.get(p.getUniqueId())*1.64);								
												}
												p.setSprinting(true);
							                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
							                    le.damage(0,p);
							                    le.damage(player_damage.get(p.getName())*1.78+wsd.BodyPress.get(p.getUniqueId())*1.64,p);
							                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())/2+1));
												p.setSprinting(false);
													
											}
									}
								}
			                }
	            	   }, j.incrementAndGet()/10); 
		                sdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.swingMainHand();
					p.swingOffHand();
					p.playSound(p.getLocation(), Sound.ENTITY_HORSE_JUMP, 1.0f, 0f);
					p.setGliding(true);
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
												enar.setDamage(player_damage.get(p.getName())*1.78+wsd.BodyPress.get(p.getUniqueId())*1.64);								
											}
											p.setSprinting(true);
						                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
						                    le.damage(0,p);
						                    le.damage(player_damage.get(p.getName())*1.78+wsd.BodyPress.get(p.getUniqueId())*1.64,p);
						                    le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, player_damage.get(p.getName())/2+1));
											p.setSprinting(false);
												
										}
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

        
		
		
		
		if(playerclass.get(p.getUniqueId()) == 8) {
			
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
				{
					d.setDamage(d.getDamage()*(wsd.Chopping.get(p.getUniqueId())*0.035));
					p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1+wsd.Chopping.get(p.getUniqueId())*0.1);
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

		        
				
				
				
				if(playerclass.get(p.getUniqueId()) == 8) {
					
						if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
						{
							d.setDamage(d.getDamage()*(wsd.Chopping.get(p.getUniqueId())*0.035));
							p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1+wsd.Chopping.get(p.getUniqueId())*0.1);
							p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
							p.playSound(p.getLocation(), Sound.ENTITY_SLIME_JUMP, 1.0f, 0f);
						}
				}
			}
		}
	}
	
	
	@EventHandler
	public void GiantSwing(EntityDamageByEntityEvent d) 
	{
		int sec = 15;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{
			
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		

        
		
		
		if(playerclass.get(p.getUniqueId()) == 8) {
			if(p.getAttackCooldown()==1) 
			{	
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.isSneaking()) && !(p.isSprinting()) && !d.isCancelled())
					{
					if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (gdcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use GiantSwing").create());
				        }
			            else // if timer is done
			            {
			                gdcooldown.remove(p.getName()); // removing player from HashMap
			                d.setDamage(d.getDamage()*1.6);
							le.playEffect(org.bukkit.EntityEffect.THORNS_HURT);
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
							if(le instanceof Player) {
                    			PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
								Player p1 = (Player) le;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
											return;
										}
									}
								else {
									Holding.superholding(p, le, (long) 120);
					                 p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 5, 1, 0, 1);
					                 for(int i=0; i<swing.size()-1;i++) {
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
											{
							             	@Override
									                public void run() 
							             				{	
									                     	le.teleport(swing.get(k.getAndIncrement()));
									                    	p.teleport(eye.get(k.get()));
										                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 0);
											            }
								            }, j.incrementAndGet()/10); 
											}
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
										{
						             	@Override
								                public void run() 
						             				{	
								                     	Location thr = p.getLocation();
									                    p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1, 0);
								                     	thr.add(thr.getDirection().normalize().multiply(5));
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
															enar.setDamage(player_damage.get(p.getName())*2.1+wsd.GiantSwing.get(p.getUniqueId())*1.6);								
														}
										                p.setSprinting(true);
								                     	le.damage(0, p);
								                     	le.damage(player_damage.get(p.getName())*2.1+wsd.GiantSwing.get(p.getUniqueId())*1.6, p);
										                p.setSprinting(false);
								                     	for(Entity e: le.getWorld().getNearbyEntities(le.getLocation(),4, 4, 4)) {
								                     		if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
								                     			LivingEntity lle = (LivingEntity) e;
																if(lle instanceof EnderDragon) {
												                    Arrow firstarrow = p.launchProjectile(Arrow.class);
												                    firstarrow.setDamage(0);
												                    firstarrow.remove();
																	Arrow enar = (Arrow) p.getWorld().spawn(lle.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
																		ar.setShooter(p);
																		ar.setCritical(false);
																		ar.setSilent(true);
																		ar.setPickupStatus(PickupStatus.DISALLOWED);
																		ar.setVelocity(lle.getLocation().clone().add(0, -1, 0).toVector().subtract(lle.getLocation().toVector()).normalize().multiply(2.6));
																	});
																	enar.setDamage(player_damage.get(p.getName())*2.6+wsd.GiantSwing.get(p.getUniqueId())*2);								
																}
												                p.setSprinting(true);
										                     	lle.damage(0, p);
										                     	lle.damage(player_damage.get(p.getName())*2.6+wsd.GiantSwing.get(p.getUniqueId())*2, p);
												                p.setSprinting(false);
								                     		}
								                     	}
										            }
							            }, j.incrementAndGet()/10);
								}
							}
							
	                    	else {                    			
					                 Holding.superholding(p, le, (long) 120);
					                 p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 5, 1, 0, 1);
					                 for(int i=0; i<swing.size()-1;i++) {
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
											{
							             	@Override
									                public void run() 
							             				{	
									                     	le.teleport(swing.get(k.getAndIncrement()));
									                    	p.teleport(eye.get(k.get()));
										                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 0);
											            }
								            }, j.incrementAndGet()/10); 
											}
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
										{
						             	@Override
								                public void run() 
						             				{	
								                     	Location thr = p.getLocation();
									                    p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1, 0);
								                     	thr.add(thr.getDirection().normalize().multiply(5));
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
															enar.setDamage(player_damage.get(p.getName())*2.1+wsd.GiantSwing.get(p.getUniqueId())*1.6);								
														}
										                p.setSprinting(true);
								                     	le.damage(0, p);
								                     	le.damage(player_damage.get(p.getName())*2.1+wsd.GiantSwing.get(p.getUniqueId())*1.6, p);
										                p.setSprinting(false);
								                     	for(Entity e: le.getWorld().getNearbyEntities(le.getLocation(),4, 4, 4)) {
								                     		if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
								                     			LivingEntity lle = (LivingEntity) e;
																if(lle instanceof EnderDragon) {
												                    Arrow firstarrow = p.launchProjectile(Arrow.class);
												                    firstarrow.setDamage(0);
												                    firstarrow.remove();
																	Arrow enar = (Arrow) p.getWorld().spawn(lle.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
																		ar.setShooter(p);
																		ar.setCritical(false);
																		ar.setSilent(true);
																		ar.setPickupStatus(PickupStatus.DISALLOWED);
																		ar.setVelocity(lle.getLocation().clone().add(0, -1, 0).toVector().subtract(lle.getLocation().toVector()).normalize().multiply(2.6));
																	});
																	enar.setDamage(player_damage.get(p.getName())*2.6+wsd.GiantSwing.get(p.getUniqueId())*2);								
																}
												                p.setSprinting(true);
										                     	lle.damage(0, p);
										                     	lle.damage(player_damage.get(p.getName())*2.6+wsd.GiantSwing.get(p.getUniqueId())*2, p);
												                p.setSprinting(false);
								                     		}
								                     	}
										            }
							            }, j.incrementAndGet()/10);
								}
								gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
			        	d.setDamage(d.getDamage()*1.6);
						le.playEffect(org.bukkit.EntityEffect.THORNS_HURT);
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
						if(le instanceof Player) {
                			PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
							Player p1 = (Player) le;
							if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										return;
									}
								}
							else {
								Holding.superholding(p, le, (long) 100);
				                 p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 5, 1, 0, 1);
				                 for(int i=0; i<swing.size()-1;i++) {
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
										{
						             	@Override
								                public void run() 
						             				{	
								                     	le.teleport(swing.get(k.getAndIncrement()));
								                    	p.teleport(eye.get(k.get()));
									                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
										            }
							            }, j.incrementAndGet()/10); 
										}
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
									{
					             	@Override
							                public void run() 
					             				{	
							                     	Location thr = p.getLocation();
								                    p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1, 0);
							                     	thr.add(thr.getDirection().normalize().multiply(5));
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
														enar.setDamage(player_damage.get(p.getName())*2.1+wsd.GiantSwing.get(p.getUniqueId())*1.6);								
													}
									                p.setSprinting(true);
							                     	le.damage(0, p);
							                     	le.damage(player_damage.get(p.getName())*2.1+wsd.GiantSwing.get(p.getUniqueId())*1.6, p);
									                p.setSprinting(false);
							                     	for(Entity e: le.getWorld().getNearbyEntities(le.getLocation(),4, 4, 4)) {
							                     		if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							                     			LivingEntity lle = (LivingEntity) e;
															if(lle instanceof EnderDragon) {
											                    Arrow firstarrow = p.launchProjectile(Arrow.class);
											                    firstarrow.setDamage(0);
											                    firstarrow.remove();
																Arrow enar = (Arrow) p.getWorld().spawn(lle.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
																	ar.setShooter(p);
																	ar.setCritical(false);
																	ar.setSilent(true);
																	ar.setPickupStatus(PickupStatus.DISALLOWED);
																	ar.setVelocity(lle.getLocation().clone().add(0, -1, 0).toVector().subtract(lle.getLocation().toVector()).normalize().multiply(2.6));
																});
																enar.setDamage(player_damage.get(p.getName())*2.6+wsd.GiantSwing.get(p.getUniqueId())*2);								
															}
											                p.setSprinting(true);
									                     	lle.damage(0, p);
									                     	lle.damage(player_damage.get(p.getName())*2.6+wsd.GiantSwing.get(p.getUniqueId())*2, p);
											                p.setSprinting(false);
							                     		}
							                     	}
									            }
						            }, j.incrementAndGet()/10);
							}
						}
						
                    	else {                    			
				                 Holding.superholding(p, le, (long) 100);
				                 p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 5, 1, 0, 1);
				                 for(int i=0; i<swing.size()-1;i++) {
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
										{
						             	@Override
								                public void run() 
						             				{	
								                     	le.teleport(swing.get(k.getAndIncrement()));
								                    	p.teleport(eye.get(k.get()));
									                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
										            }
							            }, j.incrementAndGet()/10); 
										}
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
									{
					             	@Override
							                public void run() 
					             				{	
							                     	Location thr = p.getLocation();
								                    p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1, 0);
							                     	thr.add(thr.getDirection().normalize().multiply(5));
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
														enar.setDamage(player_damage.get(p.getName())*2.1+wsd.GiantSwing.get(p.getUniqueId())*1.6);								
													}
									                p.setSprinting(true);
							                     	le.damage(0, p);
							                     	le.damage(player_damage.get(p.getName())*2.1+wsd.GiantSwing.get(p.getUniqueId())*1.6, p);
									                p.setSprinting(false);
							                     	for(Entity e: le.getWorld().getNearbyEntities(le.getLocation(),4, 4, 4)) {
							                     		if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							                     			LivingEntity lle = (LivingEntity) e;
															if(lle instanceof EnderDragon) {
											                    Arrow firstarrow = p.launchProjectile(Arrow.class);
											                    firstarrow.setDamage(0);
											                    firstarrow.remove();
																Arrow enar = (Arrow) p.getWorld().spawn(lle.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
																	ar.setShooter(p);
																	ar.setCritical(false);
																	ar.setSilent(true);
																	ar.setPickupStatus(PickupStatus.DISALLOWED);
																	ar.setVelocity(lle.getLocation().clone().add(0, -1, 0).toVector().subtract(lle.getLocation().toVector()).normalize().multiply(2.6));
																});
																enar.setDamage(player_damage.get(p.getName())*2.6+wsd.GiantSwing.get(p.getUniqueId())*2);								
															}
											                p.setSprinting(true);
									                     	lle.damage(0, p);
									                     	lle.damage(player_damage.get(p.getName())*2.6+wsd.GiantSwing.get(p.getUniqueId())*2, p);
											                p.setSprinting(false);
							                     		}
							                     	}
									            }
						            }, j.incrementAndGet()/10);
							}
			            gdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
					}
			}
		}}
	}

	@EventHandler
	public void ForeArmSmash(EntityDamageByEntityEvent d) 
	{
		int sec = 5;
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
	        
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		

		
		
		if(playerclass.get(p.getUniqueId()) == 8) {
		if(p.getAttackCooldown()==1 && wsd.ForeArmSmash.get(p.getUniqueId())>=1) 
			{	
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && !(p.isSneaking()) && !(p.isOnGround()) && !(p.isSprinting()))
					{
					if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (smcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use ForeArmSmash").create());
				        }
			            else // if timer is done
			            {
			                smcooldown.remove(p.getName()); // removing player from HashMap
							d.setDamage(d.getDamage()*1.5*(1+p.getFallDistance()*0.2));
							le.playEffect(org.bukkit.EntityEffect.THORNS_HURT);
							le.teleport(le.getLocation().add(0, -1, 0));
							p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 0f);
				            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
						d.setDamage(d.getDamage()*1.5*(1+p.getFallDistance()*0.2));
						le.playEffect(org.bukkit.EntityEffect.THORNS_HURT);
						le.teleport(le.getLocation().add(0, -1, 0));
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
		
		
        
		
		
		if(playerclass.get(p.getUniqueId()) == 8 && wsd.TackleRush.get(p.getUniqueId())>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET"))
				{
				if(e.isSprinting())
				{
					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 1, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10, 1, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 1, false, false));
	                     	for(Entity e: p.getWorld().getNearbyEntities(p.getLocation(),1.1, 1.1, 1.1)) {
	                     		if(e!=p && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
	                     			LivingEntity le = (LivingEntity) e;
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
										enar.setDamage(player_damage.get(p.getName())*0.25);								
									}
					                p.setSprinting(true);
			                     	le.damage(0, p);
			                     	le.damage(player_damage.get(p.getName())*0.25, p);
					                p.setSprinting(false);
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
	}
	
	
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item ii = ev.getItemDrop();
		ItemStack is = ii.getItemStack();
        
		
		
		
		
			if(playerclass.get(p.getUniqueId()) == 8 && (is.getType().name().contains("NUGGET")) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (sultcooldown.get(p.getName())/1000 + 60) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use FinishMove").create());
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
	                    for(Entity e : p.getWorld().getNearbyEntities(l, 6, 5, 6)) {
	                    	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
	                			LivingEntity le = (LivingEntity)e;
	                    		if(le instanceof Player) {
									Player p1 = (Player) e;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
										if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
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
			            }, 19);
	                    drop.forEach(dl -> {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
							{
			             	@Override
					                public void run() 
			             				{	
			             					p.teleport(dl);
						                    p.playSound(p.getLocation(), Sound.BLOCK_SNOW_FALL, 0.3f, 2f);
						                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1, 2);
						                    p.spawnParticle(Particle.CRIT, l, 1);
							            }
				            }, j.incrementAndGet()+20);
	                    });
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
						{
		             	@Override
				                public void run() 
		             				{	for(Entity e : p.getWorld().getNearbyEntities(l, 6, 5, 6)) {
		                            	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
		                        			LivingEntity le = (LivingEntity)e;
		                            		if(le instanceof Player) {
		        								Player p1 = (Player) e;
		        								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
		        									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
		        										{
		        											continue;
		        										}
		        									}
		        	                    		else {
		        	             					p.setSprinting(true);
		        	             					p.swingMainHand();
		        	             					
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
		        										enar.setDamage(10+player_damage.get(p.getName())*10);								
		        									}
		        			                    	le.damage(0,p);
		        			                    	le.damage(10+player_damage.get(p.getName())*10,p);
		        				                    p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
			    				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1, 0);
			    				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1, 0);
		        				                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 0);
		        									p.setSprinting(false);
		        	                    			p.leaveVehicle();
		        	                			}
		                            		}
		                            		else {
		    	             					p.setSprinting(true);
		    	             					p.swingMainHand();
		    	             					
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
		    										enar.setDamage(10+player_damage.get(p.getName())*10);								
		    									}
		    			                    	le.damage(0,p);
		    			                    	le.damage(10+player_damage.get(p.getName())*10,p);
	        				                    p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
		    				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1, 0);
		    				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1, 0);
	        				                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 0);
		    									p.setSprinting(false);
		    	                    			p.leaveVehicle();
		                            		}
		                        		}
		                        	}
						            }
			            }, j.incrementAndGet()+20);
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
                    for(Entity e : p.getWorld().getNearbyEntities(l, 6, 5, 6)) {
                    	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
                			LivingEntity le = (LivingEntity)e;
                    		if(le instanceof Player) {
								Player p1 = (Player) e;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
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
				                    p.playSound(p.getLocation(), Sound.ENTITY_MAGMA_CUBE_JUMP, 1, 2);
					            }
		            }, 19);
                    drop.forEach(dl -> {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
						{
		             	@Override
				                public void run() 
		             				{	
		             					p.teleport(dl);
					                    p.playSound(p.getLocation(), Sound.BLOCK_SNOW_FALL, 0.3f, 2f);
					                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1, 2);
					                    p.spawnParticle(Particle.CRIT, l, 1);
						            }
			            }, j.incrementAndGet()+20);
                    });
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
					{
	             	@Override
			                public void run() 
	             				{	for(Entity e : p.getWorld().getNearbyEntities(l, 6, 5, 6)) {
	                            	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
	                        			LivingEntity le = (LivingEntity)e;
	                            		if(le instanceof Player) {
	        								Player p1 = (Player) e;
	        								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
	        									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
	        										{
	        											continue;
	        										}
	        									}
	        	                    		else {
	        	             					p.setSprinting(true);
	        	             					p.swingMainHand();
	        	             					
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
	        										enar.setDamage(10+player_damage.get(p.getName())*10);								
	        									}
	        			                    	le.damage(0,p);
	        			                    	le.damage(10+player_damage.get(p.getName())*10,p);
	        				                    p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
		    				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1, 0);
		    				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1, 0);
	        				                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 0);
	        									p.setSprinting(false);
	        	                    			p.leaveVehicle();
	        	                			}
	                            		}
	                            		else {
	    	             					p.setSprinting(true);
	    	             					p.swingMainHand();
	    	             					
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
	    										enar.setDamage(10+player_damage.get(p.getName())*10);								
	    									}
	    			                    	le.damage(0,p);
	    			                    	le.damage(10+player_damage.get(p.getName())*10,p);
	    				                    p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
	    				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1, 0);
	    				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1, 0);
	    				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1, 0);
	    									p.setSprinting(false);
	    	                    			p.leaveVehicle();
	                            		}
	                        		}
	                        	}
					            }
		            }, j.incrementAndGet()+20);
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

        
		
		
		if(playerclass.get(p.getUniqueId()) == 8)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
			{
					e.setCancelled(true);
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
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 8) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
				{
						player_damage.put(p.getName(), 4+ p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
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

	        
			
			
				if(playerclass.get(p.getUniqueId()) == 8) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
							player_damage.put(p.getName(), 4+  p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 8) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("NUGGET") && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
							player_damage.put(p.getName(), 4+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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



