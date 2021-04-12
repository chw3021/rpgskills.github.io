package io.github.chw3021.paladin;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.party.PartyData;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;

import io.github.chw3021.rmain.RMain;
import io.github.chw3021.witchdoctor.Wdcskills;
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
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
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
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Palskills implements Serializable, Listener {
	

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -5692924289334744517L;
	private HashMap<String, Long> rscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> prcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> jmcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> thcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> pncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> eccooldown = new HashMap<String, Long>();
	private HashMap<String, Long> aultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private PalSkillsData psd = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		PalSkillsData p = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
		psd = p;
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Palskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				PalSkillsData p = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
				psd = p;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		PalSkillsData p = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
		psd = p;
		
	}
	
	@EventHandler
	public void Restraint(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 10;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 3) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD") && p.isBlocking() && p.isSneaking())
			{
				
				ev.setCancelled(true);
				if(rscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (rscooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Restraint").create());
	                }
	                else // if timer is done
	                {
	                    rscooldown.remove(p.getName()); // removing player from HashMap
	                    Location l = p.getLocation();
						new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
						p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 12, 1, 1, 1);
						p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.6f);
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.8f);
						for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
						{
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
								final LivingEntity le = (LivingEntity)e;
								le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 4, false, false));
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	p.setSprinting(true);
					                	
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
											enar.setDamage(player_damage.get(p.getName())*0.5 + psd.Restraint.get(p.getUniqueId())*0.35);								
										}
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.5 + psd.Restraint.get(p.getUniqueId())*0.35, p);
										le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
										p.playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1.0f, 2f);
										p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 12, 1, 1, 1);
										le.teleport(p.getLocation().add(0, 2, 0));
					                	p.setSprinting(false);
					                }
					            }, 10); 
							}
						}
		                rscooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	Location l = p.getLocation();
					new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
					p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 12, 1, 1, 1);
					p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.6f);
					p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.8f);
					for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
					{
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
							final LivingEntity le = (LivingEntity)e;
							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 4, false, false));
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	p.setSprinting(true);
				                	
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
										enar.setDamage(player_damage.get(p.getName())*0.5 + psd.Restraint.get(p.getUniqueId())*0.35);								
									}
									le.damage(0, p);
									le.damage(player_damage.get(p.getName())*0.5 + psd.Restraint.get(p.getUniqueId())*0.35, p);
									le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
									p.playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1.0f, 2f);
									p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 12, 1, 1, 1);
									le.teleport(p.getLocation().add(0, 2, 0));
				                	p.setSprinting(false);
				                }
				            }, 10); 
						}
					}
	                rscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
		}
	

	@EventHandler
	public void Pray(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 13;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 3) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD") && p.isSneaking() && !p.isBlocking())
			{
				
				ev.setCancelled(true);
				{
					if(prcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (prcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Pray").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
							
		                    prcooldown.remove(p.getName()); // removing player from HashMap
		                    try {p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 1.0f, 0.1f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 0.1f);
			        		p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 100, 1, 1, 1);
			        		if(p.getHealth() + 2 + p.getLevel()*0.1 + psd.Pray.get(p.getUniqueId()) <= p.getMaxHealth())
			        		{
				        		p.setHealth(p.getHealth() + 2 + p.getLevel()*0.1 + psd.Pray.get(p.getUniqueId()));
								p.playEffect(org.bukkit.EntityEffect.VILLAGER_HEART);
				        		if(p.hasPotionEffect(PotionEffectType.BAD_OMEN))
				        		{
				        			p.removePotionEffect(PotionEffectType.BAD_OMEN);
				        		}
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
				        		if(p.hasPotionEffect(PotionEffectType.SLOW))
				        		{
				        			p.removePotionEffect(PotionEffectType.SLOW);
				        		}
				        		if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
				        		{
				        			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
				        		}
				        		if(p.hasPotionEffect(PotionEffectType.UNLUCK))
				        		{
				        			p.removePotionEffect(PotionEffectType.UNLUCK);
				        		}
				        		if(p.hasPotionEffect(PotionEffectType.WEAKNESS))
				        		{
				        			p.removePotionEffect(PotionEffectType.WEAKNESS);
				        		}
				        		if(p.hasPotionEffect(PotionEffectType.WITHER))
				        		{
				        			p.removePotionEffect(PotionEffectType.WITHER);
				        		}
				        		p.setFireTicks(0);
			        		}
			        		else
			        		{
			        			p.setHealth(p.getMaxHealth());
			        			if(p.hasPotionEffect(PotionEffectType.BAD_OMEN))
				        		{
				        			p.removePotionEffect(PotionEffectType.BAD_OMEN);
				        		}
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
				        		if(p.hasPotionEffect(PotionEffectType.SLOW))
				        		{
				        			p.removePotionEffect(PotionEffectType.SLOW);
				        		}
				        		if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
				        		{
				        			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
				        		}
				        		if(p.hasPotionEffect(PotionEffectType.UNLUCK))
				        		{
				        			p.removePotionEffect(PotionEffectType.UNLUCK);
				        		}
				        		if(p.hasPotionEffect(PotionEffectType.WEAKNESS))
				        		{
				        			p.removePotionEffect(PotionEffectType.WEAKNESS);
				        		}
				        		if(p.hasPotionEffect(PotionEffectType.WITHER))
				        		{
				        			p.removePotionEffect(PotionEffectType.WITHER);
				        		}
				        		p.setFireTicks(0);
			        		}

							new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
							for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),6, 5, 6))
							{
								if (e instanceof Player) 
								{
									Player le = (Player) e;
									if(PartyData.hasParty(p) && PartyData.hasParty(le)) {
									if(PartyData.getParty(p).equals(PartyData.getParty(le)))
										{
											if(le.getHealth() + p.getLevel()*0.1 + psd.Pray.get(p.getUniqueId()) <= le.getMaxHealth())
							        		{
								        		le.setHealth(le.getHealth() + p.getLevel()*0.1 + psd.Pray.get(p.getUniqueId()));
												le.playEffect(org.bukkit.EntityEffect.VILLAGER_HEART);
												if(le.hasPotionEffect(PotionEffectType.BAD_OMEN))
								        		{
								        			le.removePotionEffect(PotionEffectType.BAD_OMEN);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.BLINDNESS))
								        		{
								        			le.removePotionEffect(PotionEffectType.BLINDNESS);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.CONFUSION))
								        		{
								        			le.removePotionEffect(PotionEffectType.CONFUSION);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.HUNGER))
								        		{
								        			le.removePotionEffect(PotionEffectType.HUNGER);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.POISON))
								        		{
								        			le.removePotionEffect(PotionEffectType.POISON);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.SLOW))
								        		{
								        			le.removePotionEffect(PotionEffectType.SLOW);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
								        		{
								        			le.removePotionEffect(PotionEffectType.SLOW_DIGGING);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.UNLUCK))
								        		{
								        			le.removePotionEffect(PotionEffectType.UNLUCK);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.WEAKNESS))
								        		{
								        			le.removePotionEffect(PotionEffectType.WEAKNESS);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.WITHER))
								        		{
								        			le.removePotionEffect(PotionEffectType.WITHER);
								        		}
								        		le.setFireTicks(0);
							        		}
							        		else
							        		{
							        			le.setHealth(le.getMaxHealth());
							        			if(le.hasPotionEffect(PotionEffectType.BAD_OMEN))
								        		{
								        			le.removePotionEffect(PotionEffectType.BAD_OMEN);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.BLINDNESS))
								        		{
								        			le.removePotionEffect(PotionEffectType.BLINDNESS);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.CONFUSION))
								        		{
								        			le.removePotionEffect(PotionEffectType.CONFUSION);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.HUNGER))
								        		{
								        			le.removePotionEffect(PotionEffectType.HUNGER);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.POISON))
								        		{
								        			le.removePotionEffect(PotionEffectType.POISON);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.SLOW))
								        		{
								        			le.removePotionEffect(PotionEffectType.SLOW);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
								        		{
								        			le.removePotionEffect(PotionEffectType.SLOW_DIGGING);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.UNLUCK))
								        		{
								        			le.removePotionEffect(PotionEffectType.UNLUCK);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.WEAKNESS))
								        		{
								        			le.removePotionEffect(PotionEffectType.WEAKNESS);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.WITHER))
								        		{
								        			le.removePotionEffect(PotionEffectType.WITHER);
								        		}
								        		le.setFireTicks(0);
							        		}
										}
								}
							} } 
							}
		                catch(NullPointerException e){p.setHealth(p.getMaxHealth());}
							prcooldown.put(p.getName(), System.currentTimeMillis());
		               
							}
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	try {
							
		            	p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 1.0f, 0.1f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 0.1f);
		        		p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 100, 1, 1, 1);
		        		if(p.getHealth() + 2 + p.getLevel()*0.1 + psd.Pray.get(p.getUniqueId()) <= p.getMaxHealth())
		        		{
			        		p.setHealth(p.getHealth() + 2 + p.getLevel()*0.1 + psd.Pray.get(p.getUniqueId()));
							p.playEffect(org.bukkit.EntityEffect.VILLAGER_HEART);
			        		if(p.hasPotionEffect(PotionEffectType.BAD_OMEN))
			        		{
			        			p.removePotionEffect(PotionEffectType.BAD_OMEN);
			        		}
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
			        		if(p.hasPotionEffect(PotionEffectType.SLOW))
			        		{
			        			p.removePotionEffect(PotionEffectType.SLOW);
			        		}
			        		if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
			        		{
			        			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
			        		}
			        		if(p.hasPotionEffect(PotionEffectType.UNLUCK))
			        		{
			        			p.removePotionEffect(PotionEffectType.UNLUCK);
			        		}
			        		if(p.hasPotionEffect(PotionEffectType.WEAKNESS))
			        		{
			        			p.removePotionEffect(PotionEffectType.WEAKNESS);
			        		}
			        		if(p.hasPotionEffect(PotionEffectType.WITHER))
			        		{
			        			p.removePotionEffect(PotionEffectType.WITHER);
			        		}
			        		p.setFireTicks(0);
		        		}
		        		else
		        		{
		        			p.setHealth(p.getMaxHealth());
		        			if(p.hasPotionEffect(PotionEffectType.BAD_OMEN))
			        		{
			        			p.removePotionEffect(PotionEffectType.BAD_OMEN);
			        		}
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
			        		if(p.hasPotionEffect(PotionEffectType.SLOW))
			        		{
			        			p.removePotionEffect(PotionEffectType.SLOW);
			        		}
			        		if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
			        		{
			        			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
			        		}
			        		if(p.hasPotionEffect(PotionEffectType.UNLUCK))
			        		{
			        			p.removePotionEffect(PotionEffectType.UNLUCK);
			        		}
			        		if(p.hasPotionEffect(PotionEffectType.WEAKNESS))
			        		{
			        			p.removePotionEffect(PotionEffectType.WEAKNESS);
			        		}
			        		if(p.hasPotionEffect(PotionEffectType.WITHER))
			        		{
			        			p.removePotionEffect(PotionEffectType.WITHER);
			        		}
			        		p.setFireTicks(0);
		        		}
						new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
						if(PartyData.hasParty(p)) {
							for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),6, 5, 6))
							{
								if (e instanceof Player) 
								{
									Player le = (Player) e;
									if(PartyData.getParty(p).equals(PartyData.getParty(le)))
										{
											if(le.getHealth() + p.getLevel()*0.1 + psd.Pray.get(p.getUniqueId()) <= le.getMaxHealth())
							        		{
								        		le.setHealth(le.getHealth() + p.getLevel()*0.1 + psd.Pray.get(p.getUniqueId()));
												le.playEffect(org.bukkit.EntityEffect.VILLAGER_HEART);
												if(le.hasPotionEffect(PotionEffectType.BAD_OMEN))
								        		{
								        			le.removePotionEffect(PotionEffectType.BAD_OMEN);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.BLINDNESS))
								        		{
								        			le.removePotionEffect(PotionEffectType.BLINDNESS);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.CONFUSION))
								        		{
								        			le.removePotionEffect(PotionEffectType.CONFUSION);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.HUNGER))
								        		{
								        			le.removePotionEffect(PotionEffectType.HUNGER);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.POISON))
								        		{
								        			le.removePotionEffect(PotionEffectType.POISON);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.SLOW))
								        		{
								        			le.removePotionEffect(PotionEffectType.SLOW);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
								        		{
								        			le.removePotionEffect(PotionEffectType.SLOW_DIGGING);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.UNLUCK))
								        		{
								        			le.removePotionEffect(PotionEffectType.UNLUCK);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.WEAKNESS))
								        		{
								        			le.removePotionEffect(PotionEffectType.WEAKNESS);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.WITHER))
								        		{
								        			le.removePotionEffect(PotionEffectType.WITHER);
								        		}
								        		le.setFireTicks(0);
							        		}
							        		else
							        		{
							        			le.setHealth(le.getMaxHealth());
							        			if(le.hasPotionEffect(PotionEffectType.BAD_OMEN))
								        		{
								        			le.removePotionEffect(PotionEffectType.BAD_OMEN);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.BLINDNESS))
								        		{
								        			le.removePotionEffect(PotionEffectType.BLINDNESS);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.CONFUSION))
								        		{
								        			le.removePotionEffect(PotionEffectType.CONFUSION);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.HUNGER))
								        		{
								        			le.removePotionEffect(PotionEffectType.HUNGER);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.POISON))
								        		{
								        			le.removePotionEffect(PotionEffectType.POISON);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.SLOW))
								        		{
								        			le.removePotionEffect(PotionEffectType.SLOW);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
								        		{
								        			le.removePotionEffect(PotionEffectType.SLOW_DIGGING);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.UNLUCK))
								        		{
								        			le.removePotionEffect(PotionEffectType.UNLUCK);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.WEAKNESS))
								        		{
								        			le.removePotionEffect(PotionEffectType.WEAKNESS);
								        		}
								        		if(le.hasPotionEffect(PotionEffectType.WITHER))
								        		{
								        			le.removePotionEffect(PotionEffectType.WITHER);
								        		}
								        		le.setFireTicks(0);
							        		}
										}
								}
							} } 
							}
						catch(NullPointerException e)
						{p.setHealth(p.getMaxHealth());
						}
		                prcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
					}
				}}
		}
		}
	
	@EventHandler
	public void Encourage(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 16;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 3) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD") && !p.isSneaking() && !p.isBlocking())
			{
				ev.setCancelled(true);
				
				{
					if(eccooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (eccooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Encourage").create());
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    eccooldown.remove(p.getName()); // removing player from HashMap
			                Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
		                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 2f);
		                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1f);
		                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.122462f);
		                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.259921f);
			        		p.getWorld().spawnParticle(Particle.TOTEM, l, 400, 4, 0, 4);
			        		p.getWorld().spawnParticle(Particle.END_ROD, l, 400, 4, 0, 4);
							p.setAbsorptionAmount(p.getAbsorptionAmount()+2 + (int)Math.floor(psd.Encourge.get(p.getUniqueId())*0.5));
							for (Entity e : p.getWorld().getNearbyEntities(l ,4, 5, 4))
							{
								if (e instanceof Player) 
								{
									new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
									Player p1 = (Player) e;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
											p1.setAbsorptionAmount(p1.getAbsorptionAmount()+2 + (int)Math.floor(psd.Encourge.get(p.getUniqueId())*0.5));
										}
									}
									continue;
								}
								if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))) 
								{
									LivingEntity le = (LivingEntity) e;
										{
										p.setSprinting(true);
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
											enar.setDamage(player_damage.get(p.getName()) * 0.6 +  + psd.Encourge.get(p.getUniqueId())*0.5);								
										}
										le.damage(0, p);
										le.damage(player_damage.get(p.getName()) * 0.6 +  + psd.Encourge.get(p.getUniqueId())*0.5, p);
										p.setSprinting(false);
										}
								}
							}
							eccooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
	                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 2f);
	                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1f);
	                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.122462f);
	                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.259921f);
		        		p.getWorld().spawnParticle(Particle.TOTEM, l, 400, 4, 0, 4);
		        		p.getWorld().spawnParticle(Particle.END_ROD, l, 400, 4, 0, 4);
						p.setAbsorptionAmount(p.getAbsorptionAmount()+2 + (int)Math.floor(psd.Encourge.get(p.getUniqueId())*0.5));
						for (Entity e : p.getWorld().getNearbyEntities(l ,4, 5, 4))
						{
							if (e instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
								Player p1 = (Player) e;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
										p1.setAbsorptionAmount(p1.getAbsorptionAmount()+2 + (int)Math.floor(psd.Encourge.get(p.getUniqueId())*0.5));
									}
								}
								continue;
							}
							if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity) e;
									{
									p.setSprinting(true);
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
										enar.setDamage(player_damage.get(p.getName()) * 0.5 +  + psd.Encourge.get(p.getUniqueId())*0.3);								
									}
									le.damage(0, p);
									le.damage(player_damage.get(p.getName()) * 0.5 +  + psd.Encourge.get(p.getUniqueId())*0.3, p);
									p.setSprinting(false);
									}
							}
						}
		                eccooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}}
		}
		}
	
	@EventHandler
	public void Judgement(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 11;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 3) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD")  && p.isSneaking() && !p.isBlocking() && !p.isSprinting()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					
					if(jmcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (jmcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Judgement").create());
		                }
		                else // if timer is done
		                {
		                    jmcooldown.remove(p.getName()); // removing player from HashMap

		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();							
		                    p.getWorld().spawnParticle(Particle.CRIT_MAGIC, l, 400, 4, 0, 4);
		                    final ArmorStand as = p.getWorld().spawn(l, ArmorStand.class);
		                    as.setArms(true);
		                    as.setLeftArmPose(new EulerAngle(0 , 0, Math.toRadians( -90 )));
		                    as.setRightArmPose(new EulerAngle(0 , 0, Math.toRadians( 90 )));
		                    as.setMarker(true);
		                    as.setGravity(false);
		                    as.setCanPickupItems(false);
		                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1.0f, 2.0f);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									as.remove();
				                }
				            }, 20); 

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					        		{
					        			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier() + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
					        			
					        		}
					        		else
					        		{
					        			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
					        			
					        		}
									
									if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING))
					        		{
					        			p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, p.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier() + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
					        			
					        		}
					        		else
					        		{
					        			p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
					        			
					        		}
				                }
				            }, 20);
		                	for (Entity a : p.getWorld().getNearbyEntities(l, 4, 4, 4))
							{
								if (a instanceof Player) 
								{
									new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
									Player le = (Player)a;
									if(PartyData.hasParty(p)&&PartyData.hasParty(le)) {
									if(PartyData.getParty(p).equals(PartyData.getParty(le))) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											if(le.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
							        		{
							        			le.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, le.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier() + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
							        			
							        		}
							        		else
							        		{
							        			le.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
							        			
							        		}
											
											if(le.hasPotionEffect(PotionEffectType.FAST_DIGGING))
							        		{
							        			le.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, le.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier() + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
							        			
							        		}
							        		else
							        		{
							        			le.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
							        			
							        		}
						                }
						            }, 20); }
									}
									continue;
								}
								if ((!(a == p))&& a instanceof LivingEntity && a!=as&& !(a.hasMetadata("fake"))) 
								{
									final LivingEntity le = (LivingEntity)a;
									le.damage(0, p);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
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
												enar.setDamage(player_damage.get(p.getName())*0.8 + psd.Judgement.get(p.getUniqueId())*0.4);								
											}
											p.setSprinting(true);
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*0.8 + psd.Judgement.get(p.getUniqueId())*0.4, p);
											p.getWorld().spawnParticle(Particle.SNEEZE, le.getLocation(), 7, 1,1,1);
											p.getWorld().strikeLightningEffect(l);
											p.getWorld().strikeLightningEffect(le.getLocation());
											p.setSprinting(false);
						                }
						            }, 19); 
								}
							}
							jmcooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();							
	                    p.getWorld().spawnParticle(Particle.CRIT_MAGIC, l, 400, 4, 0, 4);
	                    final ArmorStand as = p.getWorld().spawn(l, ArmorStand.class);
	                    as.setArms(true);
	                    as.setLeftArmPose(new EulerAngle(0 , 0, Math.toRadians( -90 )));
	                    as.setRightArmPose(new EulerAngle(0 , 0, Math.toRadians( 90 )));
	                    as.setMarker(true);
	                    as.setGravity(false);
	                    as.setCanPickupItems(false);
	                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1.0f, 2.0f);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								as.remove();
			                }
			            }, 20); 
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
				        		{
				        			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier() + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
				        			
				        		}
				        		else
				        		{
				        			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
				        			
				        		}
								
								if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING))
				        		{
				        			p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, p.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier() + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
				        			
				        		}
				        		else
				        		{
				        			p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
				        			
				        		}
			                }
			            }, 20);
	                	for (Entity a : p.getWorld().getNearbyEntities(l, 4, 4, 4))
						{
							if (a instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
								Player le = (Player)a;
								if(PartyData.hasParty(p)&&PartyData.hasParty(le)) {
								if(PartyData.getParty(p).equals(PartyData.getParty(le))) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										if(le.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						        		{
						        			le.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, le.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier() + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
						        			
						        		}
						        		else
						        		{
						        			le.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
						        			
						        		}
										
										if(le.hasPotionEffect(PotionEffectType.FAST_DIGGING))
						        		{
						        			le.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, le.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier() + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
						        			
						        		}
						        		else
						        		{
						        			le.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
						        			
						        		}
					                }
					            }, 20); }
								}

								continue;
							}
							if ((!(a == p))&& a instanceof LivingEntity && a!=as&& !(a.hasMetadata("fake"))) 
							{
								final LivingEntity le = (LivingEntity)a;
								le.damage(0, p);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
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
											enar.setDamage(player_damage.get(p.getName())*0.8 + psd.Judgement.get(p.getUniqueId())*0.4);								
										}
										p.setSprinting(true);
										le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.8 + psd.Judgement.get(p.getUniqueId())*0.4, p);
										p.getWorld().spawnParticle(Particle.SNEEZE, le.getLocation(), 7, 1,1,1);
										p.getWorld().strikeLightningEffect(l);
										p.getWorld().strikeLightningEffect(le.getLocation());
										p.setSprinting(false);
					                }
					            }, 19); 
							}
						}
						jmcooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
				}
		}
		}
	@EventHandler
	public void Thrust(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		int sec = 3;
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 3) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD")&& !p.isSneaking() && p.isBlocking())
			{
				ev.setCancelled(true);
				
                Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
                if(!l.getBlock().isPassable())
                {l.setY(l.getY() + 1);}
				l.setDirection(p.getLocation().getDirection());
				if(l.getBlock().isPassable())
				{
					if(thcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                long timer = (thcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Thrust").create());
		                }
		                else // if timer is done
		                {
		                    thcooldown.remove(p.getName()); // removing player from HashMap
							for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 2, 2, 2))
							{
	                    		if (a instanceof Player) 
								{
									Player p1 = (Player) a;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
										continue;
										}
									}
								}
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
								{
									LivingEntity le = (LivingEntity)a;
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
										enar.setDamage(player_damage.get(p.getName())*0.5 + psd.Thrust.get(p.getUniqueId())*0.3);								
									}
									p.setSprinting(true);
									le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
									le.damage(0, p);
									le.damage(player_damage.get(p.getName())*0.5 + psd.Thrust.get(p.getUniqueId())*0.3, p);
									
									p.getWorld().spawnParticle(Particle.SNEEZE, le.getLocation(), 7, 1,1,1); 
									le.teleport(l);
									p.setSprinting(false);
								}
							}
							p.teleport(l);
							p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1.0f, 1.6f);
							p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1.0f, 1.6f);
							p.getWorld().spawnParticle(Particle.ASH, l, 80, 3, 0, 3);

							
							for (Entity a : p.getWorld().getNearbyEntities(l, 2, 2, 2))
							{
	                    		if (a instanceof Player) 
								{
									Player p1 = (Player) a;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
										continue;
										}
									}
								}
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
								{
									LivingEntity le = (LivingEntity)a;
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
												enar.setDamage(player_damage.get(p.getName())*0.5 + psd.Thrust.get(p.getUniqueId())*0.3);								
											}
											p.setSprinting(true);
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*0.5 + psd.Thrust.get(p.getUniqueId())*0.3, p);
											
											p.getWorld().spawnParticle(Particle.SNEEZE, le.getLocation(), 7, 1,1,1);
											le.teleport(l);
											p.setSprinting(false);
										}
								}
							}
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 2f);

							thcooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
						for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 2, 2, 2))
						{
                    		if (a instanceof Player) 
							{
								Player p1 = (Player) a;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
									continue;
									}
								}
							}
							if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)a;
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
											enar.setDamage(player_damage.get(p.getName())*0.3 + psd.Thrust.get(p.getUniqueId())*0.15);								
										}
									p.setSprinting(true);
									le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
									le.damage(0, p);
									le.damage(player_damage.get(p.getName())*0.3 + psd.Thrust.get(p.getUniqueId())*0.15, p);
									
									p.getWorld().spawnParticle(Particle.SNEEZE, le.getLocation(), 7, 1,1,1); 
									le.teleport(l);
									p.setSprinting(false);
									}
							}
						}
						p.teleport(l);
						p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1.0f, 1.6f);
						p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1.0f, 1.6f);
						p.getWorld().spawnParticle(Particle.ASH, l, 80, 3, 0, 3);

						
						for (Entity a : p.getWorld().getNearbyEntities(l, 2, 2, 2))
						{
                    		if (a instanceof Player) 
							{
								Player p1 = (Player) a;
								if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
								if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
									{
									continue;
									}
								}
							}
							if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)a;
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
											enar.setDamage(player_damage.get(p.getName())*0.3 + psd.Thrust.get(p.getUniqueId())*0.15);								
										}
									p.setSprinting(true);
									le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
									le.damage(0, p);
									le.damage(player_damage.get(p.getName())*0.3 + psd.Thrust.get(p.getUniqueId())*0.15, p);;
									
									p.getWorld().spawnParticle(Particle.SNEEZE, le.getLocation(), 7, 1,1,1);
									le.teleport(l);
									p.setSprinting(false);
									}
							}
						}
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 2f);

						thcooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
				}
		}
		}
	
		
	
	
	@EventHandler
	public void Punish(EntityDamageByEntityEvent d) 
	{
		int sec = 6;
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&& !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 3) {				
		if(p.getAttackCooldown()==1) 
			{	
			
				if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD") && !(p.isSneaking()) && !(p.isOnGround()) && !(p.isSprinting()))
					{
					if(pncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (pncooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Punish").create());
				        }
			            else // if timer is done
			            {
			                pncooldown.remove(p.getName()); // removing player from HashMap
							d.setDamage(d.getDamage()*2.53 + psd.Punish.get(p.getUniqueId())*0.96);
							le.playEffect(org.bukkit.EntityEffect.THORNS_HURT);
							p.getWorld().strikeLightningEffect(le.getLocation());
				            pncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {

						d.setDamage(d.getDamage()*2.53 + psd.Punish.get(p.getUniqueId())*0.96);
						le.playEffect(org.bukkit.EntityEffect.THORNS_HURT);
						p.getWorld().strikeLightningEffect(le.getLocation());
			            pncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
					}
			}
		}}
	}
		
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		final Location l = p.getLocation();
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 3 && (is.getType().name().contains("AXE")) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(aultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (aultcooldown.get(p.getName())/1000 + 45) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use FinalJudgement").create());
		            }
	                else // if timer is done
	                {
	                    aultcooldown.remove(p.getName()); // removing player from HashMap
						p.setSneaking(false);
	                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 0.5f);
	                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 0f);
						p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 500, 10,1,10);
						p.getWorld().spawnParticle(Particle.TOTEM, p.getLocation(), 500, 4,1,4);
						p.sendTitle(ChatColor.GOLD + "FinalJudgement", null);
						for (Entity e : p.getWorld().getNearbyEntities(l, 10, 10, 10))
						{
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
							if ((e!=p)&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								final LivingEntity le = (LivingEntity)e;
									{
										for(int co = 0 ; co<10; co++) {
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
								                	p.setSprinting(true);
								                	
								                	hold.superholding(p, le, 10l);
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
								                	le.damage(0, p);
													le.damage(player_damage.get(p.getName())*0.3, p);
													le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
													le.playEffect(EntityEffect.HURT_EXPLOSION);
													le.getWorld().strikeLightningEffect(le.getLocation());
													le.getWorld().strikeLightningEffect(l);
													le.playEffect(EntityEffect.HURT_BERRY_BUSH);
								                	p.setSprinting(false);
								                }
								            }, co*10); 
											
										}
									}
							}
						}
						
		                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

					p.setSneaking(false);
                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 0.5f);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 0f);
					p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 500, 10,1,10);
					p.getWorld().spawnParticle(Particle.TOTEM, p.getLocation(), 500, 4,1,4);
					p.sendTitle(ChatColor.GOLD + "FinalJudgement", null);
					for (Entity e : p.getWorld().getNearbyEntities(l, 10, 10, 10))
					{
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
						if ((e!=p)&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							final LivingEntity le = (LivingEntity)e;
								{
									for(int co = 0 ; co<10; co++) {
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
							                	p.setSprinting(true);
							                	
							                	hold.superholding(p, le, 10l);
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
							                	le.damage(0, p);
												le.damage(player_damage.get(p.getName())*0.3, p);
												le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));
												le.playEffect(EntityEffect.HURT_EXPLOSION);
												le.getWorld().strikeLightningEffect(le.getLocation());
												le.getWorld().strikeLightningEffect(l);
												le.playEffect(EntityEffect.HURT_BERRY_BUSH);
							                	p.setSprinting(false);
							                }
							            }, co*10); 
										
									}
								}
						}
					}
	                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}	
			
			
    }
	@EventHandler
	public void Protection(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player&&!d.isCancelled()) 
		{
			Player p = (Player)d.getEntity();
			   
			if(PartyData.hasParty(p))	{
					if(PartyData.getMembers(PartyData.getParty(p)).filter(par -> playerclass.get(par)==3).anyMatch(pal -> Bukkit.getPlayer(pal).isBlocking())) {
						p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 0.6f, 1.46f);
						d.setDamage(d.getDamage()/2);
	                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.YELLOW +"[Protection]").create());
					}
			}   
			if(!PartyData.hasParty(p) && playerclass.get(p.getUniqueId()) == 3 && p.isBlocking())	{
				d.setDamage(d.getDamage()/2);
				p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 0.6f, 1.46f);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.YELLOW +"[Protection]").create());
			}
		}
	}
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

	    
		
		
		if(playerclass.get(p.getUniqueId()) == 3)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType()== Material.SHIELD)
			{
					e.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void Faith(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
    		if (le instanceof Player) 
			{
				Player p1 = (Player) le;
				if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
				if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
					{
					return;
					}
				}
			}
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 3) {		
				if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType()== Material.SHIELD)	{
					d.setDamage(d.getDamage()*1.1+psd.Protection.get(p.getUniqueId())*0.43);
				}
				
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow ar = (Arrow)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
	    		if (le instanceof Player) 
				{
					Player p1 = (Player) le;
					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
					if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
						{
						return;
						}
					}
				}
			    
				
				
				
				if(playerclass.get(p.getUniqueId()) == 3) {		
					if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType()== Material.SHIELD)	{
						d.setDamage(d.getDamage()*1.1+psd.Protection.get(p.getUniqueId())*0.43);
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
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 3) {						
				if(p.getInventory().getItemInMainHand().getType()==Material.IRON_AXE)
				{
						player_damage.put(p.getName(), 4 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_AXE)
				{
						player_damage.put(p.getName(), 5 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_AXE)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STONE_AXE)
				{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_AXE)
				{
						player_damage.put(p.getName(), 2 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				
				if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_AXE)
				{
						player_damage.put(p.getName(), 7 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
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
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 3) {						
				if(p.getInventory().getItemInMainHand().getType()==Material.IRON_AXE)
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
				
				if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_AXE)
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
				
				if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_AXE)
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
				
				if(p.getInventory().getItemInMainHand().getType()==Material.STONE_AXE)
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
				
				if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_AXE)
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
				
				if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_AXE)
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
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
			Arrow ar = (Arrow)d.getDamager();
			Entity e = d.getEntity();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
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
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 3) {						
					if(p.getInventory().getItemInMainHand().getType()==Material.IRON_AXE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND_AXE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.GOLDEN_AXE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.STONE_AXE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.WOODEN_AXE)
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
					
					if(p.getInventory().getItemInMainHand().getType()==Material.NETHERITE_AXE)
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
				}
			}
		}
	}
}



