package io.github.chw3021.classes.paladin;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.obtains.Obtained;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;
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
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Palskills extends Pak implements Serializable, Listener {
	

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
	private HashMap<String, Long> ault2cooldown = new HashMap<String, Long>();




	private HashMap<UUID, Integer> illm = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> illmt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> doom = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> doomt = new HashMap<UUID, Integer>();
	

	private HashMap<UUID, Integer> aria = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> ariat = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> scry = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> scryt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> aspg = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> aspgt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> grff = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> grfft = new HashMap<UUID, Integer>();

	private HashMap<UUID, Horse> griffon = new HashMap<UUID, Horse>();

	private HashMap<UUID, Integer> hlsm = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> hlsmt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> pltl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> pltlt = new HashMap<UUID, Integer>();
	
	
	private HashMap<UUID, Integer> judge = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Integer> judget = new HashMap<UUID, Integer>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private PalSkillsData psd;

	

	private static final Palskills instance = new Palskills ();
	public static Palskills getInstance()
	{
		return instance;
	}

		
	public void er(PluginEnableEvent ev) 
	{
		PalSkillsData p = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
		psd = p;
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
				Player p = (Player) e.getWhoClicked();
                if(judget.containsKey(p.getUniqueId())) {
                	Bukkit.getServer().getScheduler().cancelTask(judget.get(p.getUniqueId()));
                }
                judge.remove(p.getUniqueId());
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

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		PalSkillsData p = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
		psd = p;
		
	}
	
	
	public void Restraint(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Restraint.getOrDefault(p.getUniqueId(), 0)>=1) {
			double sec =8*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD") && p.isBlocking() && p.isSneaking())
			{
				
				ev.setCancelled(true);
				if(rscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (rscooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("결박 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Restraint").create());
	                	}
	                }
	                else // if timer is done
	                {
	                    rscooldown.remove(p.getName()); // removing player from HashMap
	                    
	                	if(illmt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(illmt.get(p.getUniqueId()));
	                		illmt.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							illm.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						illm.remove(p.getUniqueId());
	    	                }
	    	            }, 45); 
	                	illmt.put(p.getUniqueId(), task);
	                	
	                	
	                    Location l = p.getLocation();
						
						p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 12, 1, 1, 1);
						p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.6f);
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.8f);
						for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
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
								final LivingEntity le = (LivingEntity)e;
								le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 4, false, false));
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	

										atks(0.7, psd.Restraint.get(p.getUniqueId())*0.69, p, le,14);
										p.playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1.0f, 2f);
										p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 12, 1, 1, 1);
										le.teleport(p.getLocation().add(0, 2, 0));
					                	
					                }
					            }, 10); 
							}
						}
		                rscooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    
                	if(illmt.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(illmt.get(p.getUniqueId()));
                		illmt.remove(p.getUniqueId());
                	}

    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						if(Proficiency.getpro(p)>=1) {
    							illm.putIfAbsent(p.getUniqueId(), 0);
    						}
    	                }
    	            }, 13); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						illm.remove(p.getUniqueId());
    	                }
    	            }, 35); 
                	illmt.put(p.getUniqueId(), task);
                	
	            	Location l = p.getLocation();
					p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 12, 1, 1, 1);
					p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.6f);
					p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.8f);
					for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
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
							final LivingEntity le = (LivingEntity)e;
							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 4, false, false));
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {

									atks(0.7, psd.Restraint.get(p.getUniqueId())*0.69, p, le,14);
									p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 12, 1, 1, 1);
									le.teleport(p.getLocation().add(0, 2, 0));
				                	
				                }
				            }, 10); 
						}
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							p.playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1.0f, 2f);
		                }
		            }, 10); 
	                rscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
		}
	}

	
	public void Illumination(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && illm.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD") && p.isBlocking() && p.isSneaking())
			{
				ev.setCancelled(true);

            	if(illmt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(illmt.get(p.getUniqueId()));
            		illmt.remove(p.getUniqueId());
            	}
				illm.remove(p.getUniqueId());

            	if(doomt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(doomt.get(p.getUniqueId()));
            		doomt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							doom.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						doom.remove(p.getUniqueId());
	                }
	            }, 45); 
            	doomt.put(p.getUniqueId(), task);

            	Location l = p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(2.2));
				
				HashSet<Location> cross = new HashSet<>();
				
				for(int i = 0; i<5; i++) {
					cross.add(l.clone().add(0, i, 0));
				}
				for(int i = -2; i<2; i++) {
					cross.add(l.clone().add(i, 3, 0));
				}
				p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 10,4,2,4,0);

            	cross.forEach(cl -> {
    				p.getWorld().spawnParticle(Particle.COMPOSTER, cl, 100, 0.25,0.25,0.25,0);
    				p.getWorld().spawnParticle(Particle.WHITE_ASH, cl, 100, 0.25,0.25,0.25,0);
    				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, cl, 100, 0.25,0.25,0.25,0, Material.CHISELED_QUARTZ_BLOCK.createBlockData());
            	});
            	
				p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 0.8f, 0f);
				p.playSound(p.getLocation(), Sound.ENTITY_GLOW_SQUID_AMBIENT, 0.8f, 0f);
				p.playSound(p.getLocation(), Sound.ITEM_GLOW_INK_SAC_USE, 0.8f, 2f);
                
				for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
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
						final LivingEntity le = (LivingEntity)e;
						le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 4, false, false));
						atks(0.9, psd.Restraint.get(p.getUniqueId())*0.9, p, le,9);
						Holding.holding(p, le, 30l);
					}
				}
				
			}
		}
			
	}

	
	public void Doom(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && doom.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD") && p.isBlocking() && p.isSneaking())
			{
				ev.setCancelled(true);

            	if(doomt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(doomt.get(p.getUniqueId()));
            		doomt.remove(p.getUniqueId());
            	}
				doom.remove(p.getUniqueId());

            	Location l = p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(2.2));
				HashSet<Location> cross = new HashSet<>();
				
				for(int i = -1; i<7; i++) {
					cross.add(l.clone().add(0, i, 0));
				}
				for(int i = -3; i<3; i++) {
					cross.add(l.clone().add(i, 4, 0));
					cross.add(l.clone().add(0, 4, i));
				}

            	cross.forEach(tl -> {
    				p.getWorld().spawnParticle(Particle.CRIT_MAGIC, tl, 300, 0.5, 0.5, 0.5);
    				p.getWorld().spawnParticle(Particle.WAX_ON, tl, 300, 0.5, 0.5, 0.5,0);
    				p.getWorld().spawnParticle(Particle.SPELL_INSTANT, tl, 300, 0.5, 0.5, 0.5,0);
    				p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, tl, 300, 1, 1, 1,0.5);
            	});
				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE,0.3f, 0.3f);
				p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.8f, 2f);
				
                
				for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
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
						final LivingEntity le = (LivingEntity)e;
						atks(1.5, psd.Restraint.get(p.getUniqueId())*1.68, p, le,9);
						le.teleport(l);
						Holding.holding(p, le, 30l);
					}
				}

				
			}
		}
			
	}
	
	@SuppressWarnings("deprecation")
	public void Pray(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec = 11*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);;
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Pray.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD") && p.isSneaking() && !p.isBlocking())
			{
				
				ev.setCancelled(true);
				{
					if(prcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (prcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("기도 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Pray").create());
		                	}
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
							
		                    prcooldown.remove(p.getName()); // removing player from HashMap
			            	p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 1.0f, 0.1f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 0.1f);
			        		p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 100, 1, 1, 1);
			        		if(p.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1) <= p.getMaxHealth())
			        		{
				        		p.setHealth(p.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1));
			        		}
			        		else
			        		{
			        			p.setHealth(p.getMaxHealth());
			        		}
			        		if(Proficiency.getpro(p)>=1) {
			        			Holding.invur(p, psd.Pray.get(p.getUniqueId())*4l);
			        		}
			        		if(Proficiency.getpro(p)>=2) {
			        			p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 2, false, false));
			        		}
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
							if(Party.hasParty(p)) {
								for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),6, 5, 6))
								{
									if (e instanceof Player) 
									{
										Player le = (Player) e;
										if(Party.hasParty(le) && Party.getParty(p).equals(Party.getParty(le)))
										{
											if(le.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1) <= le.getMaxHealth())
							        		{
								        		le.setHealth(le.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1));
							        		}
							        		else
							        		{
							        			le.setHealth(le.getMaxHealth());
							        		}
							        		if(Proficiency.getpro(p)>=1) {
							        			Holding.invur(le, psd.Pray.get(p.getUniqueId())*4l);
							        		}
							        		if(Proficiency.getpro(p)>=2) {
							        			le.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 2, false, false));
							        		}
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
									}
								} 
							} 
							prcooldown.put(p.getName(), System.currentTimeMillis());
		               
							}
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 1.0f, 0.1f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 0.1f);
		        		p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 100, 1, 1, 1);
		        		if(p.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1) <= p.getMaxHealth())
		        		{
			        		p.setHealth(p.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1));
		        		}
		        		else
		        		{
		        			p.setHealth(p.getMaxHealth());
		        		}
		        		if(Proficiency.getpro(p)>=1) {
		        			Holding.invur(p, psd.Pray.get(p.getUniqueId())*4l);
		        		}
		        		if(Proficiency.getpro(p)>=2) {
		        			p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 2, false, false));
		        		}
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
						if(Party.hasParty(p)) {
							for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),6, 5, 6))
							{
								if (e instanceof Player) 
								{
									Player le = (Player) e;
									if(Party.hasParty(le) && Party.getParty(p).equals(Party.getParty(le)))
									{
										if(le.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1) <= le.getMaxHealth())
						        		{
							        		le.setHealth(le.getHealth()+1 + p.getLevel() *0.5* (1+ psd.Pray.get(p.getUniqueId())*0.1));
						        		}
						        		else
						        		{
						        			le.setHealth(le.getMaxHealth());
						        		}
						        		if(Proficiency.getpro(p)>=1) {
						        			Holding.invur(le, psd.Pray.get(p.getUniqueId())*4l);
						        		}
						        		if(Proficiency.getpro(p)>=2) {
						        			le.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 2, false, false));
						        		}
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
								}
							} 
						} 
		                prcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
					}
				}
			}
		}
	}
	
	public void Encourage(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec = 16*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);;
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Encourge.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD") && !p.isSneaking() && !p.isBlocking())
			{
				ev.setCancelled(true);
				
				{
					if(eccooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (eccooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("격려 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Encourage").create());
		                	}
		                    ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    eccooldown.remove(p.getName()); // removing player from HashMap
		                    
		                	if(ariat.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(ariat.get(p.getUniqueId()));
		                		ariat.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							aria.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						aria.remove(p.getUniqueId());
		    	                }
		    	            }, 45); 
		                	ariat.put(p.getUniqueId(), task);
		                	
			                Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
		                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1.0f, 0f);
			        		p.getWorld().spawnParticle(Particle.END_ROD, l, 400, 4, 2, 4);
			        		p.getWorld().spawnParticle(Particle.GLOW, l, 100, 4, 2, 4);
							p.setAbsorptionAmount(p.getAbsorptionAmount()+2 + psd.Encourge.get(p.getUniqueId())*0.5);
							for (Entity e : p.getWorld().getNearbyEntities(l ,4, 5, 4))
							{
								if (e instanceof Player) 
								{
									
									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
									if(Party.getParty(p).equals(Party.getParty(p1)))
										{
										p1.setAbsorptionAmount(p1.getAbsorptionAmount()+2 +psd.Encourge.get(p.getUniqueId())*0.5);
										}
									}
									continue;
								}
								if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
								{
									LivingEntity le = (LivingEntity) e;
									atks(0.8, psd.Encourge.get(p.getUniqueId())*0.8, p, le,14);
								}
							}
							eccooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    
	                	if(ariat.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(ariat.get(p.getUniqueId()));
	                		ariat.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							aria.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						aria.remove(p.getUniqueId());
	    	                }
	    	            }, 45); 
	                	ariat.put(p.getUniqueId(), task);
	                	
		            	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
	                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1.0f, 0f);
		        		p.getWorld().spawnParticle(Particle.END_ROD, l, 400, 4, 2, 4);
		        		p.getWorld().spawnParticle(Particle.GLOW, l, 100, 4, 2, 4);
						p.setAbsorptionAmount(p.getAbsorptionAmount()+2 + psd.Encourge.get(p.getUniqueId())*0.5);
						for (Entity e : p.getWorld().getNearbyEntities(l ,4, 5, 4))
						{
							if (e instanceof Player) 
							{
								
								Player p1 = (Player) e;
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
										p1.setAbsorptionAmount(p1.getAbsorptionAmount()+2 +psd.Encourge.get(p.getUniqueId())*0.5);
									}
								}
								continue;
							}
							if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
							{
								LivingEntity le = (LivingEntity) e;
								atks(0.8, psd.Encourge.get(p.getUniqueId())*0.8, p, le,14);
							}
						}
		                eccooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
			}
		}
	}
	
	public void Aria(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && aria.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD") && !p.isSneaking() && !p.isBlocking())
			{
				ev.setCancelled(true);
                
            	if(ariat.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(ariat.get(p.getUniqueId()));
            		ariat.remove(p.getUniqueId());
            	}
				aria.remove(p.getUniqueId());
				


            	if(scryt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(scryt.get(p.getUniqueId()));
            		scryt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							scry.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 5); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						scry.remove(p.getUniqueId());
	                }
	            }, 50); 
            	scryt.put(p.getUniqueId(), task);

            	
            	final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
                p.playNote(l, Instrument.CHIME, Note.sharp(1, Tone.C));
                p.playNote(l, Instrument.CHIME, Note.sharp(1, Tone.G));
                p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.B));;
        		p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, l, 400, 4, 4, 4);
        		p.getWorld().spawnParticle(Particle.WAX_ON, l, 400, 4, 4, 4);
				for (Entity e : p.getWorld().getNearbyEntities(l ,5, 5, 5))
				{
					if (e instanceof Player) 
					{
						if(e==p) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30, 2, false, false));
    						continue;
						}
						
						Player p1 = (Player) e;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
								p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30, 2, false, false));
							}
						}
						continue;
					}
					if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
					{
						LivingEntity le = (LivingEntity) e;
						atks(0.5, psd.Encourge.get(p.getUniqueId())*0.5, p, le,14);
						Holding.holding(p, le, 20l);
					}
				}

        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                    p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.A));
	                    p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.E));
	                    p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.B));
	            		p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, l, 400, 4, 0, 4);
	            		p.getWorld().spawnParticle(Particle.WAX_ON, l, 400, 4, 0, 4);
	    				for (Entity e : p.getWorld().getNearbyEntities(l ,5, 5, 5))
	    				{
	    					if (e instanceof Player) 
	    					{

	    						if(e==p) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30, 2, false, false));
		    						continue;
	    						}
	    						Player p1 = (Player) e;
	    						if(Party.hasParty(p) && Party.hasParty(p1))	{
	    						if(Party.getParty(p).equals(Party.getParty(p1)))
	    							{
									p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
									p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30, 2, false, false));
	    							}
	    						}
	    						continue;
	    					}
	    					if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
	    					{
	    						LivingEntity le = (LivingEntity) e;
	    						atks(0.5, psd.Encourge.get(p.getUniqueId())*0.5, p, le,14);
	    					}
	    				}
	                	
	                }
	            }, 5); 
        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                    p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.E));
	                    p.playNote(l, Instrument.CHIME, Note.natural(1, Tone.B));
	                    p.playNote(l, Instrument.CHIME, Note.sharp(1, Tone.G));
	            		p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, l, 400, 4, 0, 4);
	            		p.getWorld().spawnParticle(Particle.WAX_ON, l, 400, 4, 0, 4);
	    				for (Entity e : p.getWorld().getNearbyEntities(l ,5, 5, 5))
	    				{
	    					if (e instanceof Player) 
	    					{

	    						if(e==p) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30, 2, false, false));
		    						continue;
	    						}
	    						Player p1 = (Player) e;
	    						if(Party.hasParty(p) && Party.hasParty(p1))	{
	    						if(Party.getParty(p).equals(Party.getParty(p1)))
	    							{
									p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
									p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30, 2, false, false));
	    							}
	    						}
	    						continue;
	    					}
	    					if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
	    					{
	    						LivingEntity le = (LivingEntity) e;
	    						atks(0.5, psd.Encourge.get(p.getUniqueId())*0.5, p, le,14);
	    					}
	    				}
	                	
	                }
	            }, 10); 
			}
		}
	}

	public void Sanctuary(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && scry.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD") && !p.isSneaking() && !p.isBlocking())
			{
				ev.setCancelled(true);

            	if(scryt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(scryt.get(p.getUniqueId()));
            		scryt.remove(p.getUniqueId());
            	}
				scry.remove(p.getUniqueId());

                
				final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();
				
                HashSet<ArmorStand> remove = new HashSet<>();
                for(double vi = -1; vi<4; vi+=0.5) {
                    ArmorStand v1 = p.getWorld().spawn(l.clone().add(0, vi, 0), ArmorStand.class);
                    v1.setCollidable(false);
                    v1.setGravity(false);
                    v1.setCanPickupItems(false);
                    v1.setSmall(true);
                    v1.setInvisible(true);
                    v1.getEquipment().setHelmet(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setChestplate(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setBoots(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setLeggings(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    v1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    v1.setBasePlate(false);
                    remove.add(v1);
                	
                }
                for(double vi = -1; vi<=1; vi+=0.5) {
                    ArmorStand v1 = p.getWorld().spawn(l.clone().add(vi, 2.5, 0), ArmorStand.class);
                    v1.setCollidable(false);
                    v1.setGravity(false);
                    v1.setCanPickupItems(false);
                    v1.setSmall(true);
                    v1.setInvisible(true);
                    v1.getEquipment().setHelmet(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setChestplate(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setBoots(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.getEquipment().setLeggings(new ItemStack(Material.QUARTZ_PILLAR));
                    v1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    v1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    v1.setBasePlate(false);
                    remove.add(v1);
                	
                }

				for(int co = 0 ; co<10; co++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                    p.playSound(l, Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1f);
		                    p.playSound(l, Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.122462f);
		                    p.playSound(l, Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.259921f);
		            		p.getWorld().spawnParticle(Particle.COMPOSTER, l, 400, 4, 0, 4);
		            		p.getWorld().spawnParticle(Particle.TOWN_AURA, l, 400, 4, 0, 4);
		    				for (Entity e : l.getWorld().getNearbyEntities(l ,4, 5, 4))
		    				{
		    					if (e instanceof Player) 
		    					{
		    						if(e==p) {
		    							p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
			    						continue;
		    						}
		    						Player p1 = (Player) e;
		    						if(Party.hasParty(p) && Party.hasParty(p1))	{
		    						if(Party.getParty(p).equals(Party.getParty(p1)))
		    							{
		    							p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
		    							}
		    						}
		    						continue;
		    					}
		    					if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
		    					{
		    						LivingEntity le = (LivingEntity) e;
		    						atks(0.55, psd.Encourge.get(p.getUniqueId())*0.68, p, le,14);
		    					}
		    				}
						}
		            }, co*10); 
				}
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            		@Override
                	public void run() 
	                {	
            			remove.forEach(r -> r.remove());
     				}
            	}, 110);
				
			}
		}
	}
	
	
	public void Judgement(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 10*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);;
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Judgement.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD")  && p.isSneaking() && !p.isBlocking()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(Material.ARMOR_STAND))
				{
					ev.setCancelled(true);
					
					if(jmcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (jmcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("심판 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Judgement").create());
		                	}
		                }
		                else // if timer is done
		                {
		                    jmcooldown.remove(p.getName()); // removing player from HashMap

		                	if(aspgt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(aspgt.get(p.getUniqueId()));
		                		aspgt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							aspg.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						aspg.remove(p.getUniqueId());
		    	                }
		    	            }, 45); 
		                	aspgt.put(p.getUniqueId(), task);
		                	
		                	

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
		                    as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1.0f, 2.0f);

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				        			if(judge.containsKey(p.getUniqueId())) {
				        				if(judge.get(p.getUniqueId())<=psd.Judgement.get(p.getUniqueId())) {
						        			judge.put(p.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
				        				}
				        			}
				        			judge.putIfAbsent(p.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
				        			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
				        			p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
				                	for (Entity a : l.getWorld().getNearbyEntities(l, 4, 4, 4))
									{
										if (a instanceof Player) 
										{
											
											Player le = (Player)a;
											if(Party.hasParty(p)&&Party.hasParty(le)) {
											if(Party.getParty(p).equals(Party.getParty(le))) {
								        			le.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
								        			le.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
								                    if(judget.containsKey(le.getUniqueId())) {
								                    	Bukkit.getServer().getScheduler().cancelTask(judget.get(le.getUniqueId()));
								                    }
								        			if(judge.containsKey(le.getUniqueId())) {
								        				if(judge.get(le.getUniqueId())<=psd.Judgement.get(p.getUniqueId())) {
										        			judge.put(le.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
								        				}
								        			}
								        			judge.putIfAbsent(le.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
													int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() {
															judge.remove(le.getUniqueId());
										                }
										            }, 210); 
													judget.put(le.getUniqueId(), task);
												}
											}
											continue;
										}
										if ((!(a == p))&& a instanceof LivingEntity && a!=as&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
										{
											final LivingEntity le = (LivingEntity)a;
											atks(0.8, psd.Judgement.get(p.getUniqueId())*0.8, p, le);
													p.getWorld().strikeLightningEffect(l);
													p.getWorld().strikeLightningEffect(le.getLocation());
													 
										}
									}
									as.remove();
				                }
				            }, 20);
							jmcooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {

	                	if(aspgt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(aspgt.get(p.getUniqueId()));
	                		aspgt.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							aspg.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						aspg.remove(p.getUniqueId());
	    	                }
	    	            }, 45); 
	                	aspgt.put(p.getUniqueId(), task);
	                	

	                    if(judget.containsKey(p.getUniqueId())) {
	                    	Bukkit.getServer().getScheduler().cancelTask(judget.get(p.getUniqueId()));
	                    }
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
	                    as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1.0f, 2.0f);

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			        			if(judge.containsKey(p.getUniqueId())) {
			        				if(judge.get(p.getUniqueId())<=psd.Judgement.get(p.getUniqueId())) {
					        			judge.put(p.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
			        				}
			        			}
			        			judge.putIfAbsent(p.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
			        			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
			        			p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
			                	for (Entity a : l.getWorld().getNearbyEntities(l, 4, 4, 4))
								{
									if (a instanceof Player) 
									{
										
										Player le = (Player)a;
										if(Party.hasParty(p)&&Party.hasParty(le)) {
										if(Party.getParty(p).equals(Party.getParty(le))) {
							        			le.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
							        			le.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1 + (int)Math.floor(psd.Judgement.get(p.getUniqueId())*0.1), false, false, true));
							                    if(judget.containsKey(le.getUniqueId())) {
							                    	Bukkit.getServer().getScheduler().cancelTask(judget.get(le.getUniqueId()));
							                    }
							        			if(judge.containsKey(le.getUniqueId())) {
							        				if(judge.get(le.getUniqueId())<=psd.Judgement.get(p.getUniqueId())) {
									        			judge.put(le.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
							        				}
							        			}
							        			judge.putIfAbsent(le.getUniqueId(), psd.Judgement.get(p.getUniqueId()));
												int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() {
														judge.remove(le.getUniqueId());
									                }
									            }, 210); 
												judget.put(le.getUniqueId(), task);
											}
										}
										continue;
									}
									if ((!(a == p))&& a instanceof LivingEntity && a!=as&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
									{
										final LivingEntity le = (LivingEntity)a;
										atks(0.8, psd.Judgement.get(p.getUniqueId())*0.8, p, le);
												p.getWorld().strikeLightningEffect(l);
												p.getWorld().strikeLightningEffect(le.getLocation());
												 
									}
								}
								as.remove();
			                }
			            }, 20);
						jmcooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
			}
		}
	}

	
	public void Asperges(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3&& aspg.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD")  && p.isSneaking() && !p.isBlocking()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(Material.ARMOR_STAND))
				{
					ev.setCancelled(true);

	            	if(aspgt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(aspgt.get(p.getUniqueId()));
	            		aspgt.remove(p.getUniqueId());
	            	}
					aspg.remove(p.getUniqueId());
					


	            	if(grfft.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(grfft.get(p.getUniqueId()));
	            		grfft.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								grff.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 3); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							grff.remove(p.getUniqueId());
		                }
		            }, 45); 
	            	grfft.put(p.getUniqueId(), task);
	            	
	            	
	            	final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 3).getLocation();							
                    

					ItemStack jack = new ItemStack(Material.CHISELED_QUARTZ_BLOCK, 1);	
					Item jo = p.getWorld().dropItemNaturally(l, jack);
					jo.setGlowing(true);
					jo.setPickupDelay(5000);
					jo.setInvulnerable(true);
					jo.setThrower(p.getUniqueId());
					jo.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					jo.setMetadata("jo of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					jo.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                    p.playSound(p.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1.0f, 2f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1.0f, 2f);
		                    p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 2f);
			        		jo.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 200,4,4,4);
			        		jo.getWorld().spawnParticle(Particle.WATER_SPLASH, l, 200,4,4,4);
			        		jo.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 200,4,4,4,Material.CHISELED_QUARTZ_BLOCK.createBlockData());
		                	for (Entity e : jo.getWorld().getNearbyEntities(l, 4, 4, 4))
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
									atks(1.1, psd.Judgement.get(p.getUniqueId())*1.2, p, le,9);
									Holding.holding(p, le, 10l);
								}
							}
		                	Stream<Entity> jos = p.getWorld().getEntities().stream().filter(i -> i.hasMetadata("jo of"+p.getName()));
		                	jos.forEach(i -> i.remove());
		                }
            	   }, 13); 
					
				}
			}
		}
	}

	
	public void Griffon(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && grff.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD")  && p.isSneaking() && !p.isBlocking()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(Material.ARMOR_STAND))
				{
					ev.setCancelled(true);
					
	            	if(grfft.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(grfft.get(p.getUniqueId()));
	            		grfft.remove(p.getUniqueId());
	            	}
					grff.remove(p.getUniqueId());

					
	                if(griffon.containsKey(p.getUniqueId())) {
	                	Holding.ale(griffon.get(p.getUniqueId())).remove();
	                	griffon.remove(p.getUniqueId());
	                	
	                }
                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_AMBIENT, 1.0f, 1f);
                    Horse h = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
					h.setOwner(null);
                    h.setDomestication(h.getMaxDomestication());
					h.setTamed(true);
					h.setOwner(p);
                    h.setCustomName(p.getName());
					h.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));	
					h.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					h.setColor(Color.WHITE);
					h.setStyle(Style.WHITE);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
					h.getInventory().setArmor(new ItemStack(Material.DIAMOND_HORSE_ARMOR));
					griffon.put(p.getUniqueId(), h);
					
					
				}
			}
		}
	}

	
	public void Griffon(VehicleExitEvent ev) 
	{
		if(ev.getVehicle() instanceof Horse && ev.getExited() instanceof Player) {
			Player p = (Player) ev.getExited();
            if(griffon.containsKey(p.getUniqueId())) {
            	Holding.ale(griffon.get(p.getUniqueId())).remove();
            	griffon.remove(p.getUniqueId());
            	
            }
		}
	}

	public void Griffon(HorseJumpEvent ev) 
	{
            if(griffon.containsValue(ev.getEntity())) {
            	Horse h = (Horse) ev.getEntity();
            	Player p = (Player) h.getOwner();
            	h.getWorld().spawnParticle(Particle.WHITE_ASH, h.getLocation(), 100,2,2,2);
            	p.playSound(h.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 2);
            	for (Entity e : h.getWorld().getNearbyEntities(h.getLocation(), 4, 4, 4))
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
						atks(1.3, psd.Judgement.get(p.getUniqueId())*1.3*ev.getPower(), p, le,9);
		            	le.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 1);
					}
				}
            	
            }
	}
	
	
	public void Thrust(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =3*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Thrust.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD")&& !p.isSneaking() && p.isBlocking())
			{
				ev.setCancelled(true);
				
                final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS, Material.ARMOR_STAND)), 4).getLocation();
                Location l = tl.setDirection(p.getEyeLocation().getDirection());
					if(thcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (thcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("진압 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Thrust").create());
		                	}
		                }
		                else // if timer is done
		                {
		                    thcooldown.remove(p.getName()); // removing player from HashMap
		                    
		                	if(hlsmt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(hlsmt.get(p.getUniqueId()));
		                		hlsmt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							hlsm.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						hlsm.remove(p.getUniqueId());
		    	                }
		    	            }, 45); 
		                	hlsmt.put(p.getUniqueId(), task);
		                	
							for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 2, 2, 2))
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
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
								{
									LivingEntity le = (LivingEntity)a;
									atks(0.5, psd.Thrust.get(p.getUniqueId())*0.485,p, le,14);
									
									le.teleport(l);
									
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
									if(Party.hasParty(p) && Party.hasParty(p1))	{
									if(Party.getParty(p).equals(Party.getParty(p1)))
										{
										continue;
										}
									}
								}
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
								{
									LivingEntity le = (LivingEntity)a;
										{
											atks(0.5, psd.Thrust.get(p.getUniqueId())*0.485,p, le,14);
											
											le.teleport(l);
											
										}
								}
							}
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 2f);

							thcooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    
	                	if(hlsmt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(hlsmt.get(p.getUniqueId()));
	                		hlsmt.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							hlsm.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						hlsm.remove(p.getUniqueId());
	    	                }
	    	            }, 45); 
	                	hlsmt.put(p.getUniqueId(), task);
	                	
						for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 2, 2, 2))
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
							if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
							{
								LivingEntity le = (LivingEntity)a;
									{
										atks(0.5, psd.Thrust.get(p.getUniqueId())*0.485,p, le,14);
									le.teleport(l);
									
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
								if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
									{
									continue;
									}
								}
							}
							if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
							{
								LivingEntity le = (LivingEntity)a;
									{
										atks(0.5, psd.Thrust.get(p.getUniqueId())*0.485,p, le,14);
									le.teleport(l);
									
									}
							}
						}
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 2f);

						thcooldown.put(p.getName(), System.currentTimeMillis());  
					}
			}
		}
	}

	public void HolySmash(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && hlsm.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD")&& !p.isSneaking() && p.isBlocking())
			{
				ev.setCancelled(true);
				
            	if(hlsmt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(hlsmt.get(p.getUniqueId()));
            		hlsmt.remove(p.getUniqueId());
            	}
				hlsm.remove(p.getUniqueId());
				


            	if(pltlt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(pltlt.get(p.getUniqueId()));
            		pltlt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							pltl.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						pltl.remove(p.getUniqueId());
	                }
	            }, 45); 
            	pltlt.put(p.getUniqueId(), task);
            	
            	
                Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 1).getLocation();
                
                ArrayList<Location> line = new ArrayList<Location>();
                AtomicInteger j = new AtomicInteger();
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 0.5f);
                p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.6f, 1.5f);
                for(double an = Math.PI/36; an>-Math.PI/36; an-=Math.PI/180) {
                	Location pl = p.getEyeLocation();
                	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(2.5));
                	line.add(pl);
                }
                line.forEach(l -> {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
			        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,10,2,2,2,0);
			        		p.getWorld().spawnParticle(Particle.COMPOSTER, l,10,2,2,2,0);	          
		                }
					}, j.incrementAndGet()/900); 
                	
                });
        		p.getWorld().spawnParticle(Particle.WHITE_ASH, tl,200,2,2,2,0);

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
						for (Entity a : p.getWorld().getNearbyEntities(tl, 3.8, 2.65, 3.8))
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
							if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
							{
								LivingEntity le = (LivingEntity)a;
								atks(0.5, psd.Thrust.get(p.getUniqueId())*0.485,p, le,14);
								le.teleport(tl);
							}
						}
	                    
	                }
				}, j.incrementAndGet()/900); 
            	
				
			}
		}
	}

	public void HolyPile(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 3&&pltl.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD")&& !p.isSneaking() && p.isBlocking())
			{
				ev.setCancelled(true);

            	if(pltlt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(pltlt.get(p.getUniqueId()));
            		pltlt.remove(p.getUniqueId());
            	}
				pltl.remove(p.getUniqueId());

            	
                Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation();
                

                ItemStack head = new ItemStack(Material.QUARTZ_PILLAR);
                HashSet<ArmorStand> ash = new HashSet<>();
                for(double d = -1; d<2; d+=0.3) {
                    ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(tl.clone().add(0, d, 0), EntityType.ARMOR_STAND);
                    as.setCustomName(p.getName());
                    as.setBasePlate(false);
                    as.setGravity(true);
                    as.setInvulnerable(true);
                    as.setInvisible(true);
                    as.getEquipment().setHelmet(head);
                    as.setCanPickupItems(false);
                    as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    ash.add(as);
                }

				for(int co = 0 ; co<4; co++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 0.6f, 1.2f);
		                    p.playSound(p.getLocation(), Sound.BLOCK_COPPER_BREAK, 1.0f, 0.3f); 
		            		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl,100,1,1,1, Material.END_ROD.createBlockData());	   
		            		p.getWorld().spawnParticle(Particle.ITEM_CRACK, tl,100,1,1,1, new ItemStack(Material.END_ROD));	  
		    				for (Entity a : p.getWorld().getNearbyEntities(tl, 4, 4, 4))
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
		    					if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
		    					{
		    						LivingEntity le = (LivingEntity)a;
		    						atks(0.4, psd.Thrust.get(p.getUniqueId())*0.455,p, le,14);
		    						le.teleport(tl);
		    						Holding.superholding(p, le, 20l);
		    					}
		    				}
						}
		            }, co*2); 
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                    ash.forEach(r -> r.remove());
	                }
				}, 40); 
				
			}
		}
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void Punish(EntityDamageByEntityEvent d) 
	{
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&& !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		if((le.hasMetadata("fake")) || (le.hasMetadata("portal"))) {
			return;
		}
		if (le instanceof Player) 
		{
			
			Player p1 = (Player) le;
			if(Party.hasParty(p) && Party.hasParty(p1))	{
			if(Party.getParty(p).equals(Party.getParty(p1)))
				{
				return;
				}
			}
		}
	    
		
		
			if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Punish.getOrDefault(p.getUniqueId(), 0)>=1 && (!griffon.containsKey(p.getUniqueId()) || (griffon.containsKey(p.getUniqueId()) && p.getVehicle() != griffon.get(p.getUniqueId())))) {				
				double sec =4*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				if(p.getAttackCooldown()==1) 
				{	
				
					if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD")&& !(p.hasCooldown(Material.YELLOW_TERRACOTTA)) && !(p.isSneaking()) && !(p.isOnGround()) && !(p.hasCooldown(Material.YELLOW_TERRACOTTA)))
						{
						if(pncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            double timer = (pncooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
				            	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("징벌 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
				            	}
				            	else {
					            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Punish").create());
				            	}
					        }
				            else // if timer is done
				            {
				                pncooldown.remove(p.getName()); // removing player from HashMap
								d.setDamage(d.getDamage()*1.5 + psd.Punish.get(p.getUniqueId())*1.5 + player_damage.get(p.getName())*0.5);
								if(Proficiency.getpro(p) >=1) {
									Holding.superholding(p, le, 20l);
								}
			                	p.getWorld().strikeLightningEffect(le.getLocation());
					            pncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {

							d.setDamage(d.getDamage()*1.5 + psd.Punish.get(p.getUniqueId())*1.5 + player_damage.get(p.getName())*0.5);
							if(Proficiency.getpro(p) >=1) {
								Holding.superholding(p, le, 20l);
							}
		                	p.getWorld().strikeLightningEffect(le.getLocation());
				            pncooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
						}
				}
			}
		}
	}
		

	
	@SuppressWarnings("deprecation")
	public void Punish(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 4*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);;
        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && psd.Punish.getOrDefault(p.getUniqueId(), 0)>=1 && griffon.containsKey(p.getUniqueId()) && p.getVehicle() == griffon.get(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType().name().contains("SHIELD")  && !p.isSneaking() && !p.isBlocking() && !p.isOnGround()) 
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(Material.ARMOR_STAND))
				{
					ev.setCancelled(true);
					
					if(pncooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (pncooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("징벌 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Punish").create());
		                	}
		                }
		                else // if timer is done
		                {
		                	pncooldown.remove(p.getName()); // removing player from HashMap
			                Location l = p.getEyeLocation().add(p.getEyeLocation().clone().getDirection().normalize().multiply(1.5));
			                AtomicInteger j = new AtomicInteger();
			                
		    				for(int co = 0 ; co<3; co++) {
		    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    		                @Override
		    		                public void run() {
		    		                	l.add(l.clone().getDirection().multiply(j.incrementAndGet()));
		    		                	
		    		                	l.getWorld().strikeLightningEffect(l);
		    		                	l.getWorld().strikeLightningEffect(l.clone().add(l.clone().getDirection().rotateAroundY(Math.PI/2).normalize().multiply(2)));
		    		                	l.getWorld().strikeLightningEffect(l.clone().add(l.clone().getDirection().rotateAroundY(-Math.PI/2).normalize().multiply(2)));
		    		    				for (Entity e : l.getWorld().getNearbyEntities(l ,4, 5, 4))
		    		    				{
		    		    					if (e instanceof Player) 
		    		    					{
		    		    						
		    		    						Player p1 = (Player) e;
		    		    						if(Party.hasParty(p) && Party.hasParty(p1))	{
		    		    						if(Party.getParty(p).equals(Party.getParty(p1)))
		    		    							{
		    		    							p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
		    		    							}
		    		    						}
		    		    						continue;
		    		    					}
		    		    					if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
		    		    					{
		    		    						LivingEntity le = (LivingEntity) e;
		    		    						atk1(0.8*(1+ psd.Punish.get(p.getUniqueId())*0.05),p, le,9);
												Holding.superholding(p, le, 20l);
		    		    					}
		    		    				}
		    						}
		    		            }, co*10); 
		    				}
							pncooldown.put(p.getName(), System.currentTimeMillis());  
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		                Location l = p.getEyeLocation().add(p.getEyeLocation().clone().getDirection().normalize().multiply(1.5));

		                AtomicInteger j = new AtomicInteger();
		                
	    				for(int co = 0 ; co<3; co++) {
	    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    		                @Override
	    		                public void run() {
	    		                	l.add(l.clone().getDirection().multiply(j.incrementAndGet()));
	    		                	l.getWorld().strikeLightningEffect(l);
	    		                	l.getWorld().strikeLightningEffect(l.clone().add(l.clone().getDirection().rotateAroundY(Math.PI/2).normalize().multiply(2)));
	    		                	l.getWorld().strikeLightningEffect(l.clone().add(l.clone().getDirection().rotateAroundY(-Math.PI/2).normalize().multiply(2)));
	    		    				for (Entity e : l.getWorld().getNearbyEntities(l ,4, 5, 4))
	    		    				{
	    		    					if (e instanceof Player) 
	    		    					{
	    		    						
	    		    						Player p1 = (Player) e;
	    		    						if(Party.hasParty(p) && Party.hasParty(p1))	{
	    		    						if(Party.getParty(p).equals(Party.getParty(p1)))
	    		    							{
	    		    							p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, false));
	    		    							}
	    		    						}
	    		    						continue;
	    		    					}
	    		    					if (e instanceof LivingEntity && (e!=p)&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
	    		    					{
	    		    						LivingEntity le = (LivingEntity) e;
	    		    						atk1(0.8*(1+ psd.Punish.get(p.getUniqueId())*0.05),p, le,9);
											Holding.superholding(p, le, 20l);
	    		    					}
	    		    				}
	    						}
	    		            }, co*10); 
	    				}
						pncooldown.put(p.getName(), System.currentTimeMillis());  
					}
				} 
			}
		}
	}

	
	
	
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		final Location l = p.getLocation();
		Item i = ev.getItemDrop();
		ItemStack is = i.getItemStack();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && (is.getType().name().contains("AXE")) && p.isSneaking() &&Proficiency.getpro(p)>=1)
			{
				ev.setCancelled(true);
				p.setCooldown(Material.ARMOR_STAND, 1);
				if(aultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (aultcooldown.get(p.getName())/1000d + 45/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("최후의 심판 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use FinalJudgement").create());
	                	}
		            }
	                else // if timer is done
	                {
	                    aultcooldown.remove(p.getName()); // removing player from HashMap
	                    
	                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 0.5f);
	                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 0f);
						p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 500, 10,1,10);
						p.getWorld().spawnParticle(Particle.TOTEM, p.getLocation(), 500, 4,1,4);
						
						for(int co = 0 ; co<10; co++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									for (Entity e : p.getNearbyEntities(10, 10, 10))
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
										if ((e!=p)&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
										{
											final LivingEntity le = (LivingEntity)e; 	
						                	Holding.superholding(p, le, 15l);
											atk1(1.5, p, le,9);
											le.getWorld().strikeLightningEffect(le.getLocation());
											le.getWorld().strikeLightningEffect(l);
										}
									}
								}
				            }, co*10+10); 
						}
						
		                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

                    
                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 0.5f);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 0f);
					p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, p.getLocation(), 500, 10,1,10);
					p.getWorld().spawnParticle(Particle.TOTEM, p.getLocation(), 500, 4,1,4);
					
					for(int co = 0 ; co<10; co++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								for (Entity e : p.getNearbyEntities(10, 10, 10))
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
									if ((e!=p)&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
									{
										final LivingEntity le = (LivingEntity)e; 	
					                	Holding.superholding(p, le, 15l);
										atk1(1.5, p, le,9);
										le.getWorld().strikeLightningEffect(le.getLocation());
										le.getWorld().strikeLightningEffect(l);
									}
								}
							}
			            }, co*10+10); 
					}
	                aultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	

	
	public void ULT2(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		final Location l = p.getLocation();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3 && (is.getType().name().contains("AXE")) && !p.isSneaking()&& p.isSprinting() &&Proficiency.getpro(p)>=2)
			{
				ev.setCancelled(true);
				p.setCooldown(Material.ARMOR_STAND, 1);
				if(ault2cooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (ault2cooldown.get(p.getName())/1000d + 45*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("참회 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Penance").create());
	                	}
		            }
	                else // if timer is done
	                {
	                    ault2cooldown.remove(p.getName()); // removing player from HashMap

	                    
	                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 0.5f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1.0f, 0f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1.0f, 0f);
						p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 300, 10,10,10);
						p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 1000, 10,10,10);
						Holding.invur(p, 120l);
						p.teleport(p.getLocation().add(0, 3, 0));
						
						
						for (Entity e : p.getNearbyEntities(10, 10, 10))
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
							if ((e!=p)&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
							{
								final LivingEntity le = (LivingEntity)e; 	
			                	le.teleport(l);
							}
						}
						
						HashSet<Location> cross = new HashSet<>();
						
						for(int i = 0; i<10; i++) {
							cross.add(l.clone().add(0, i, 0));
						}
						for(int i = -4; i<4; i++) {
							cross.add(l.clone().add(i, 6, 0));
						}
						for(int i = -6; i<6; i++) {
							cross.add(l.clone().add(i, 1, 0));
							cross.add(l.clone().add(0, 1, i));
						}
						
						for(int co = 0 ; co<10; co++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									l.getWorld().strikeLightningEffect(l);
									
				                    p.playSound(p.getLocation(), Sound.BLOCK_SOUL_SOIL_BREAK, 1.0f, 0f);
				                    p.playSound(p.getLocation(), Sound.BLOCK_SOUL_SAND_BREAK, 1.0f, 0f);
				                    p.playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1.0f, 0f);
				                	cross.forEach(cl -> {
					    				cl.getWorld().spawnParticle(Particle.CLOUD, cl, 100, 0.5,0.5,0.5,0);
					    				cl.getWorld().spawnParticle(Particle.TOWN_AURA, cl, 100, 0.5,0.5,0.5,0);
					    				cl.getWorld().spawnParticle(Particle.BLOCK_CRACK, cl, 100, 0.5,0.5,0.5,0, Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
					    				cl.getWorld().spawnParticle(Particle.BLOCK_CRACK, cl, 100, 0.5,0.5,0.5,0, Material.CHISELED_QUARTZ_BLOCK.createBlockData());
				                	});
									for (Entity e : l.getWorld().getNearbyEntities(l,10, 10, 10))
									{
			    						if(e==p) {
											p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, false, false));
				    						continue;
			    						}
			                    		if (e instanceof Player) 
										{
											Player p1 = (Player) e;
											if(Party.hasParty(p) && Party.hasParty(p1))	{
											if(Party.getParty(p).equals(Party.getParty(p1)))
												{
													p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, false, false));
													continue;
												}
											}
										}
										if ((e!=p)&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
										{
											final LivingEntity le = (LivingEntity)e; 	
						                	Holding.superholding(p, le, 15l);
											atk1(3d, p, le,9);
										}
									}
								}
				            }, co*10+10); 
						}
		                ault2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {


                    
                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 0.5f);
                    p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1.0f, 0f);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1.0f, 0f);
					p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 300, 10,10,10);
					p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 1000, 10,10,10);
					Holding.invur(p, 120l);
					p.teleport(p.getLocation().add(0, 3, 0));
					
					
					for (Entity e : p.getNearbyEntities(10, 10, 10))
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
						if ((e!=p)&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
						{
							final LivingEntity le = (LivingEntity)e; 	
		                	le.teleport(l);
						}
					}
					
					HashSet<Location> cross = new HashSet<>();
					
					for(int i = 0; i<10; i++) {
						cross.add(l.clone().add(0, i, 0));
					}
					for(int i = -4; i<4; i++) {
						cross.add(l.clone().add(i, 6, 0));
					}
					for(int i = -6; i<6; i++) {
						cross.add(l.clone().add(i, 1, 0));
						cross.add(l.clone().add(0, 1, i));
					}
					
					for(int co = 0 ; co<10; co++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								l.getWorld().strikeLightningEffect(l);
								
			                    p.playSound(p.getLocation(), Sound.BLOCK_SOUL_SOIL_BREAK, 1.0f, 0f);
			                    p.playSound(p.getLocation(), Sound.BLOCK_SOUL_SAND_BREAK, 1.0f, 0f);
			                    p.playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1.0f, 0f);
			                	cross.forEach(cl -> {
				    				cl.getWorld().spawnParticle(Particle.CLOUD, cl, 100, 0.5,0.5,0.5,0);
				    				cl.getWorld().spawnParticle(Particle.TOWN_AURA, cl, 100, 0.5,0.5,0.5,0);
				    				cl.getWorld().spawnParticle(Particle.BLOCK_CRACK, cl, 100, 0.5,0.5,0.5,0, Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
				    				cl.getWorld().spawnParticle(Particle.BLOCK_CRACK, cl, 100, 0.5,0.5,0.5,0, Material.CHISELED_QUARTZ_BLOCK.createBlockData());
			                	});
								for (Entity e : l.getWorld().getNearbyEntities(l,10, 10, 10))
								{
		    						if(e==p) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, false, false));
			    						continue;
		    						}
		                    		if (e instanceof Player) 
									{
										Player p1 = (Player) e;
										if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
											{
												p1.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, false, false));
												continue;
											}
										}
									}
									if ((e!=p)&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
									{
										final LivingEntity le = (LivingEntity)e; 	
					                	Holding.superholding(p, le, 15l);
										atk1(3d, p, le,9);
									}
								}
							}
			            }, co*10+10); 
					}
	                ault2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	

	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 3 && ((is.getType().name().contains("AXE") || is.getType().name().contains("SHIELD"))) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	
	public void Protection(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player&&!d.isCancelled()) 
		{
			Player p = (Player)d.getEntity();
			   
			if(Party.hasParty(p))	{
					if(Party.getMembers(Party.getParty(p)).anyMatch(pal -> ClassData.pc.get(pal)==3 && Bukkit.getPlayer(pal).isBlocking() && Proficiency.getpro(Bukkit.getPlayer(pal)) <1 )) {
						p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 0.6f, 1.46f);
						d.setDamage(d.getDamage()*0.5);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.YELLOW +"[보호]").create());
						}
						else {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.YELLOW +"[Protection]").create());
						}
					}
					else if(Party.getMembers(Party.getParty(p)).anyMatch(pal -> ClassData.pc.get(pal)==3 && Bukkit.getPlayer(pal).isBlocking() && Proficiency.getpro(Bukkit.getPlayer(pal)) >= 1 )) {
						p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 0.6f, 1.46f);
						d.setDamage(d.getDamage()*0.2);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.YELLOW +"[보호]").create());
						}
						else {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.YELLOW +"[Protection]").create());
						}
					}
			}   
			if(ClassData.pc.get(p.getUniqueId()) == 3 && p.isBlocking())	{
				d.setDamage(d.getDamage()*0.5);
				p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 0.6f, 1.46f);
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.YELLOW +"[보호]").create());
				}
				else {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(org.bukkit.ChatColor.YELLOW +"[Protection]").create());
				}
			}
			
			if(ClassData.pc.get(p.getUniqueId()) == 3&& Proficiency.getpro(p)>=1)
			{
				d.setDamage(d.getDamage()*0.8);
				if(p.isBlocking()) {
					d.setCancelled(true);
				}
			}
		}
	}
	
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 3)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType()== Material.SHIELD)
			{
				e.setCancelled(true);
			}
		}
		
	}
	
	
	public void Faith(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
    		if (le instanceof Player) 
			{
				Player p1 = (Player) le;
				if(Party.hasParty(p) && Party.hasParty(p1))	{
				if(Party.getParty(p).equals(Party.getParty(p1)))
					{
						return;
					}
				}
			}
		    
			
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 3) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType()== Material.SHIELD)	{
					d.setDamage(d.getDamage()*1.3+psd.Protection.get(p.getUniqueId())*0.4383);
				}
				
			}
			if(judge.containsKey(p.getUniqueId())) {
				d.setDamage(d.getDamage()*(1+0.01*judge.get(p.getUniqueId())));
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
					if(Party.hasParty(p) && Party.hasParty(p1))	{
					if(Party.getParty(p).equals(Party.getParty(p1)))
						{
						return;
						}
					}
				}
			    
				
				
				
				if(ClassData.pc.get(p.getUniqueId()) == 3) {		
					if(p.getInventory().getItemInMainHand().getType().name().contains("AXE") && p.getInventory().getItemInOffHand().getType()== Material.SHIELD)	{
						d.setDamage(d.getDamage()*1.3+psd.Protection.get(p.getUniqueId())*0.4383);
					}
					
				}
				if(judge.containsKey(p.getUniqueId())) {
					d.setDamage(d.getDamage()*(1+0.01*judge.get(p.getUniqueId())));
				}
			}
		}
	}
	

}



