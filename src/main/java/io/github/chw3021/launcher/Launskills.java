package io.github.chw3021.launcher;




import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.party.PartyData;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Launskills implements Serializable, Listener {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 4490550598006463433L;
	private HashMap<String, Long> cscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> excooldown = new HashMap<String, Long>();
	private HashMap<String, Long> arcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gacooldown = new HashMap<String, Long>();
	private HashMap<String, Long> dpcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> bultcooldown = new HashMap<String, Long>();
	private HashMap<String, Integer> arrowtype = new HashMap<String, Integer>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	private LaunSkillsData lsd = new LaunSkillsData(LaunSkillsData.loadData(path +"/plugins/RPGskills/LaunskillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		LaunSkillsData l = new LaunSkillsData(LaunSkillsData.loadData(path +"/plugins/RPGskills/LaunskillsData.data"));
		lsd = l;
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Launskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				LaunSkillsData l = new LaunSkillsData(LaunSkillsData.loadData(path +"/plugins/RPGskills/LaunskillsData.data"));
				lsd = l;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		LaunSkillsData l = new LaunSkillsData(LaunSkillsData.loadData(path +"/plugins/RPGskills/LaunskillsData.data"));
		lsd = l;
		
	}
	
	@EventHandler
	public void ArrowChange(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 5) {	
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) && !p.isSneaking()) 
			{	
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
	        	p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_QUICK_CHARGE_1, 1.0f, 1.5f);
	        	ev.setCancelled(true);
	        	try {
	        		switch (arrowtype.get(p.getName()))
					{
						case 0:
							arrowtype.replace(p.getName(), 1);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("AquaArrows Loaded").color(ChatColor.AQUA).create());
							break;
						case 1:
							arrowtype.replace(p.getName(), 2);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("FlameArrows Loaded").color(ChatColor.RED).create());
							break;
						case 2:
							arrowtype.replace(p.getName(), 3);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("EnderArrowLoaded").color(ChatColor.GRAY).create());
							break;
						case 3:
							arrowtype.replace(p.getName(), 0);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("LightingArrowLoaded").color(ChatColor.YELLOW).create());
							break;
					}
	        	}
	        	catch(NullPointerException e) {
	        		arrowtype.put(p.getName(), 0);
	        		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("LightingArrowLoaded").color(ChatColor.YELLOW).create());
	        		ev.setCancelled(true);
	        	}
				}
		}}
	}

	
	@EventHandler
	public void EnderArrow(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof Arrow) 
		{
		Projectile a = (Projectile) d.getEntity();
		if(a.getShooter() instanceof Player)
			{
			Player p = (Player) a.getShooter();
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 5 && p.getCooldown(Material.ARROW)<=0 && !a.hasMetadata("fake")&& !a.hasMetadata("Explosion of"+p.getName())&& !a.hasMetadata("ChargingShot of"+p.getName())) {
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
					{
						if(d.getHitEntity() instanceof LivingEntity && d.getHitBlock() == null) {
							LivingEntity he = (LivingEntity)d.getHitEntity();
							Location el = he.getLocation();
							if(arrowtype.containsKey(p.getName()) && arrowtype.get(p.getName()) == 3)
							{
								p.getWorld().spawnParticle(Particle.END_ROD, el, 40, 3, 1, 3, 0);
								p.getWorld().spawnParticle(Particle.PORTAL, el, 40, 3, 1, 3, 0);
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 90, 0, false, false));
								if(el.clone().add(0, 1.869, 0).getBlock().isPassable()){
									p.teleport(el);
									p.setCooldown(Material.ARROW, 60);
								}
							}
							
						}
						
	
						else if(d.getHitBlock() !=null) {
							Location el = d.getHitBlock().getLocation();
								if(arrowtype.containsKey(p.getName()) && arrowtype.get(p.getName()) == 3)
								{
									p.getWorld().spawnParticle(Particle.END_ROD, el, 40, 3, 1, 3, 0);
									p.getWorld().spawnParticle(Particle.PORTAL, el, 40, 3, 1, 3, 0);
									p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 90, 0, false, false));
									if(el.clone().add(0, 1.869, 0).getBlock().isPassable()){
										p.teleport(el);
										p.setCooldown(Material.ARROW, 60);
									}
								}
							
						}
					
					}	
				}
			}
		}
		
	}
	@EventHandler
	public void Arrowshootmanagement(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity &&!d.isCancelled()) 
		{
		Projectile a = (Projectile) d.getDamager();
		LivingEntity e = (LivingEntity)d.getEntity();
		if(a.getShooter() instanceof Player)
			{
			Player p = (Player) a.getShooter();
			Location el =e.getLocation();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 5)			
			{
					if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
					{
						
						try {
						if(arrowtype.get(p.getName()) == 0)
						{
							if (e instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							else {
								p.getWorld().spawnParticle(Particle.FLASH, el, 5, 1, 1, 1);
							p.getWorld().strikeLightningEffect(el);
							e.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
							if(e.getCategory() == EntityCategory.UNDEAD)
							{
								d.setDamage(d.getDamage()*(1+lsd.ArrowChange.get(p.getUniqueId())*0.35));
							}
							else
							{
								d.setDamage(d.getDamage());
							}
							}
							
						}
						if(arrowtype.get(p.getName()) == 1)
						{
							if (e instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							else {
								p.getWorld().spawnParticle(Particle.BUBBLE_POP, el, 5, 1, 1, 1);
							p.getWorld().spawnParticle(Particle.WATER_SPLASH, el, 5, 1, 1, 1);
							p.getWorld().spawnParticle(Particle.FALLING_WATER, el, 10, 1, 1, 1);
							e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
							e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
							if(e.getType() == EntityType.BLAZE || e.getType() == EntityType.ENDERMAN || e.getType() == EntityType.GHAST || e.getType() == EntityType.MAGMA_CUBE || e.getType() == EntityType.WITCH || e.getType() == EntityType.EVOKER || e.getType() == EntityType.PILLAGER || e.getType() == EntityType.PIGLIN_BRUTE || e.getType() == EntityType.RAVAGER || e.getType() == EntityType.PLAYER)
							{
								d.setDamage(d.getDamage()*(1+lsd.ArrowChange.get(p.getUniqueId())*0.35));
							}
							else
							{
								d.setDamage(d.getDamage());
							}
							}
							
						}
						if(arrowtype.get(p.getName()) == 2)
						{
							if (e instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							else {
								p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, el, 5, 1, 1, 1);
							p.getWorld().spawnParticle(Particle.FLAME, el, 40, 1, 1, 1, 0);
							e.setFireTicks(40);
							if(e.getType().name().contains("SPIDER") || e.getType().name().contains("SILVER") || e.getType().name().contains("GUARDIAN") || e.getType() == EntityType.BAT || e.getType() == EntityType.BEE  || e.getType() == EntityType.ENDERMITE)
							{
								d.setDamage(d.getDamage()*(1+lsd.ArrowChange.get(p.getUniqueId())*0.35));
							}
							else
							{
								d.setDamage(d.getDamage());
							}
							}
							
						}
						if(arrowtype.get(p.getName()) == 3)
						{
							if (e instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							else {
								p.getWorld().spawnParticle(Particle.END_ROD, el, 10, 1, 1, 1);
							p.getWorld().spawnParticle(Particle.PORTAL, el, 40, 1, 1, 1, 0);
							e.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0, false, false));
							if(e.getType().name().contains("ENDER"))
							{
								if (e instanceof Player) 
								{
									new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							}
							
								d.setCancelled(false);
								d.setDamage(d.getDamage()*(1+lsd.ArrowChange.get(p.getUniqueId())*0.35));
							}
						}
							else
							{
								d.setDamage(d.getDamage());
							}
						}
						catch(NullPointerException e1)
						{
							d.setDamage(d.getDamage());
							p.sendMessage("You should Choose ArrowType First");
						}
					}
			}
			}
		}
		
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity &&!d.isCancelled()) 
		{
		Player p = (Player) d.getDamager();
		LivingEntity e = (LivingEntity)d.getEntity();
			Location el =e.getLocation();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 5)			
			{
					if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
					{
						
						try {
						if(arrowtype.get(p.getName()) == 0)
						{
							if (e instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							else {
							if(e.getCategory() == EntityCategory.UNDEAD)
							{
								d.setDamage(d.getDamage()*(1.1));
							}
							else
							{
								d.setDamage(d.getDamage());
							}
							}
							
						}
						if(arrowtype.get(p.getName()) == 1)
						{
							if (e instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							else {
							if(e.getType() == EntityType.BLAZE || e.getType() == EntityType.ENDERMAN || e.getType() == EntityType.GHAST || e.getType() == EntityType.MAGMA_CUBE || e.getType() == EntityType.WITCH || e.getType() == EntityType.EVOKER || e.getType() == EntityType.PILLAGER || e.getType() == EntityType.PIGLIN_BRUTE || e.getType() == EntityType.RAVAGER || e.getType() == EntityType.PLAYER)
							{
								d.setDamage(d.getDamage()*(1.1));
							}
							else
							{
								d.setDamage(d.getDamage());
							}
							}
							
						}
						if(arrowtype.get(p.getName()) == 2)
						{
							if (e instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							else {
							if(e.getType().name().contains("SPIDER") || e.getType().name().contains("SILVER") || e.getType().name().contains("GUARDIAN") || e.getType() == EntityType.BAT || e.getType() == EntityType.BEE  || e.getType() == EntityType.ENDERMITE)
							{
								d.setDamage(d.getDamage()*(1.1));
							}
							else
							{
								d.setDamage(d.getDamage());
							}
							}
							
						}
						if(arrowtype.get(p.getName()) == 3)
						{
							if (e instanceof Player) 
							{
								new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							else {
							if(e.getType().name().contains("ENDER"))
							{
								if (e instanceof Player) 
								{
									new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
							}
							
								d.setCancelled(false);
								d.setDamage(d.getDamage()*(1.1));
							}
						}
							else
							{
								d.setDamage(d.getDamage());
							}
						}
						catch(NullPointerException e1)
						{
							d.setDamage(d.getDamage());
							p.sendMessage("You should Choose ArrowType First");
						}
					}
			}
		}
	}
	@EventHandler
	public void swcancle(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(playerclass.get(p.getUniqueId()) == 5) {
		if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
		{
        	ev.setCancelled(true);
		}
		}
	}
	@EventHandler
	public void ArrowRain(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			int sec = 9;
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 5) {
			if(((p.isSneaking()) && !(p.isSprinting())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
		        	ev.setCancelled(true);
					

					if(arcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
	                    
		                long timer = (arcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use ArrowRain").create());
		                }
		                else // if timer is done
		                {
		                    arcooldown.remove(p.getName()); // removing player from HashMap
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 20).getLocation();
			            	Location el = new Location(p.getWorld(), l.getX(), l.getY()+7, l.getZ());
			            	for(int i = 0; i <25; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
										Arrow ar =p.getWorld().spawnArrow(el, l.toVector().subtract(el.toVector()), 0.5f, 60);
										ar.setShooter(p);
										ar.setMetadata("arrowrain "+ p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										ar.setDamage(0);
										ar.setPickupStatus(PickupStatus.CREATIVE_ONLY);
										for (Entity e : p.getWorld().getNearbyEntities(l, 5, 4, 5))
										{
											if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
											{
												final LivingEntity le = (LivingEntity)e;
													{
														if (le instanceof Player) 
														{
															new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
														else {
														le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));

														hold.holding(p, le, (long) 3);
														try {
														if(arrowtype.get(p.getName()) == 0)
														{
															p.getWorld().spawnParticle(Particle.FLASH, el, 40, 3, 1, 3);
															le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
														}
														if(arrowtype.get(p.getName()) == 1)
														{
															p.getWorld().spawnParticle(Particle.FALLING_WATER, el, 40, 3, 1, 3);
															p.getWorld().spawnParticle(Particle.DRIP_WATER, el, 40, 3, 1, 3);
															le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
															le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
														}
														if(arrowtype.get(p.getName()) == 2)
														{
															p.getWorld().spawnParticle(Particle.FLAME, el, 40, 3, 1, 3, 0);
															p.getWorld().spawnParticle(Particle.FALLING_LAVA, el, 40, 3, 1, 3, 0);
															le.setFireTicks(40);
														}
														if(arrowtype.get(p.getName()) == 3)
														{
															p.getWorld().spawnParticle(Particle.SPELL_MOB_AMBIENT, el, 40, 3, 1, 3, 0);
															p.getWorld().spawnParticle(Particle.END_ROD, el, 40, 3, 1, 3, 0);
															p.getWorld().spawnParticle(Particle.PORTAL, el, 40, 3, 1, 3, 0);
															le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0, false, false));
														}
														}
														catch(NullPointerException e1)
														{
															p.sendMessage("You should Choose ArrowType First");
														}
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
															enar.setDamage(lsd.ArrowRain.get(p.getUniqueId())*0.0812 + player_damage.get(p.getName())*0.1201);								
														}
														le.damage(0, p);
														le.damage(lsd.ArrowRain.get(p.getUniqueId())*0.0812 + player_damage.get(p.getName())*0.1201, p);
														p.getWorld().getEntities().stream().filter(a -> a.hasMetadata("arrowrain "+p.getName())).forEach(a -> a.remove());
											             
													}}
											}
										}
					                }
					            }, i*2); 
			            		
			            	}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									p.getWorld().getEntities().stream().filter(a -> a.hasMetadata("arrowrain "+p.getName())).forEach(a -> a.remove());
				                }
				            }, 71); 
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.WEATHER_RAIN_ABOVE, 1.0f, 2f);
			                arcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 20).getLocation();
		            	Location el = new Location(p.getWorld(), l.getX(), l.getY()+7, l.getZ());
		            	for(int i = 0; i <25; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									Arrow ar =p.getWorld().spawnArrow(el, l.toVector().subtract(el.toVector()), 0.5f, 60);
									ar.setShooter(p);
									ar.setMetadata("arrowrain "+ p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									ar.setDamage(0);
									ar.setPickupStatus(PickupStatus.CREATIVE_ONLY);
									for (Entity e : p.getWorld().getNearbyEntities(l, 5, 4, 5))
									{
										if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
										{
											final LivingEntity le = (LivingEntity)e;
												{
													if (le instanceof Player) 
													{
														new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
													else {
													le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.CUSTOM, p.getLevel()));

													hold.holding(p, le, (long) 3);
													try {
													if(arrowtype.get(p.getName()) == 0)
													{
														p.getWorld().spawnParticle(Particle.FLASH, el, 40, 3, 1, 3);
														le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
													}
													if(arrowtype.get(p.getName()) == 1)
													{
														p.getWorld().spawnParticle(Particle.FALLING_WATER, el, 40, 3, 1, 3);
														p.getWorld().spawnParticle(Particle.DRIP_WATER, el, 40, 3, 1, 3);
														le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
														le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
													}
													if(arrowtype.get(p.getName()) == 2)
													{
														p.getWorld().spawnParticle(Particle.FLAME, el, 40, 3, 1, 3, 0);
														p.getWorld().spawnParticle(Particle.FALLING_LAVA, el, 40, 3, 1, 3, 0);
														le.setFireTicks(40);
													}
													if(arrowtype.get(p.getName()) == 3)
													{
														p.getWorld().spawnParticle(Particle.SPELL_MOB_AMBIENT, el, 40, 3, 1, 3, 0);
														p.getWorld().spawnParticle(Particle.END_ROD, el, 40, 3, 1, 3, 0);
														p.getWorld().spawnParticle(Particle.PORTAL, el, 40, 3, 1, 3, 0);
														le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0, false, false));
													}
													}
													catch(NullPointerException e1)
													{
														p.sendMessage("You should Choose ArrowType First");
													}
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
														enar.setDamage(lsd.ArrowRain.get(p.getUniqueId())*0.0812 + player_damage.get(p.getName())*0.1201);								
													}
													le.damage(0, p);
													le.damage(lsd.ArrowRain.get(p.getUniqueId())*0.0812 + player_damage.get(p.getName())*0.1201, p);
													p.getWorld().getEntities().stream().filter(a -> a.hasMetadata("arrowrain "+p.getName())).forEach(a -> a.remove());
										             
												}}
										}
									}
				                }
				            }, i*2); 
		            		
		            	}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.getWorld().getEntities().stream().filter(a -> a.hasMetadata("arrowrain "+p.getName())).forEach(a -> a.remove());
			                }
			            }, 71); 
						p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.WEATHER_RAIN_ABOVE, 1.0f, 2f);
		                arcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
					ev.setCancelled(true);
				}
			}
			}
	}
	
	@EventHandler
	public void Discharge(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			double sec = 2.5;
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 5) {
			if((p.isSprinting()))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{

					ev.setCancelled(true);

					if(dpcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
	                    
		                double timer = ((dpcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000); // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + (int)timer + " seconds to use Discharge").create());
		                }
		                else // if timer is done
		                {
		                    dpcooldown.remove(p.getName()); // removing player from HashMap
			            	for(int i = 0; i <6; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {

					                    Firework fr = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK);
						            	fr.setShooter(p);
					                    fr.setShotAtAngle(true);
					                    fr.setVelocity(p.getLocation().getDirection().multiply(2));
					                    fr.setMetadata("discharge", new FixedMetadataValue(RMain.getInstance(), true));
							        	ev.setCancelled(true);
					                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 40).getLocation();
						            	new Location(p.getWorld(), l.getX(), l.getY()+7, l.getZ());
						            	try {
						            	if(arrowtype.get(p.getName()) == 0)
										{
						        			FireworkEffect effect = FireworkEffect.builder()
						                                .with(Type.STAR)
						                                .withColor(Color.YELLOW)
						                                .build();
						                    FireworkMeta meta = fr.getFireworkMeta();
						                    meta.setPower(1);
						                    meta.addEffect(effect);
						                    fr.setFireworkMeta(meta);
										}
										if(arrowtype.get(p.getName()) == 1)
										{
						        			FireworkEffect effect = FireworkEffect.builder()
						                                .with(Type.BALL_LARGE)
						                                .withColor(Color.AQUA)
						                                .build();
						                    FireworkMeta meta = fr.getFireworkMeta();
						                    meta.setPower(1);
						                    meta.addEffect(effect);
						                    fr.setFireworkMeta(meta);
										}
										if(arrowtype.get(p.getName()) == 2)
										{
											
						        			FireworkEffect effect = FireworkEffect.builder()
						                                .with(Type.BURST)
						                                .withColor(Color.RED)
						                                .build();
						                    FireworkMeta meta = fr.getFireworkMeta();
						                    meta.setPower(1);
						                    meta.addEffect(effect);
						                    fr.setFireworkMeta(meta);
										}
										if(arrowtype.get(p.getName()) == 3)
										{
						        			FireworkEffect effect = FireworkEffect.builder()
						                                .with(Type.CREEPER)
						                                .withColor(Color.BLACK)
						                                .build();
						                    FireworkMeta meta = fr.getFireworkMeta();
						                    meta.setPower(1);
						                    meta.addEffect(effect);
						                    fr.setFireworkMeta(meta);
										}}
						            	
										catch(NullPointerException e1)
										{
											p.sendMessage("You should Choose ArrowType First");
										}
										p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1.0f, 0f);
										
					                }
					            }, i*2); 
			            		
			            	}
			                dpcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	for(int i = 0; i <6; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {

				                    Firework fr = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK);
					            	fr.setShooter(p);
				                    fr.setShotAtAngle(true);
				                    fr.setVelocity(p.getLocation().getDirection().multiply(2));
				                    fr.setMetadata("discharge", new FixedMetadataValue(RMain.getInstance(), true));
						        	ev.setCancelled(true);
				                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 40).getLocation();
					            	new Location(p.getWorld(), l.getX(), l.getY()+7, l.getZ());
					            	try {
					            	if(arrowtype.get(p.getName()) == 0)
									{
					        			FireworkEffect effect = FireworkEffect.builder()
					                                .with(Type.STAR)
					                                .withColor(Color.YELLOW)
					                                .build();
					                    FireworkMeta meta = fr.getFireworkMeta();
					                    meta.setPower(1);
					                    meta.addEffect(effect);
					                    fr.setFireworkMeta(meta);
									}
									if(arrowtype.get(p.getName()) == 1)
									{
					        			FireworkEffect effect = FireworkEffect.builder()
					                                .with(Type.BALL_LARGE)
					                                .withColor(Color.AQUA)
					                                .build();
					                    FireworkMeta meta = fr.getFireworkMeta();
					                    meta.setPower(1);
					                    meta.addEffect(effect);
					                    fr.setFireworkMeta(meta);
									}
									if(arrowtype.get(p.getName()) == 2)
									{
										
					        			FireworkEffect effect = FireworkEffect.builder()
					                                .with(Type.BURST)
					                                .withColor(Color.RED)
					                                .build();
					                    FireworkMeta meta = fr.getFireworkMeta();
					                    meta.setPower(1);
					                    meta.addEffect(effect);
					                    fr.setFireworkMeta(meta);
									}
									if(arrowtype.get(p.getName()) == 3)
									{
					        			FireworkEffect effect = FireworkEffect.builder()
					                                .with(Type.CREEPER)
					                                .withColor(Color.BLACK)
					                                .build();
					                    FireworkMeta meta = fr.getFireworkMeta();
					                    meta.setPower(1);
					                    meta.addEffect(effect);
					                    fr.setFireworkMeta(meta);
									}}
					            	
									catch(NullPointerException e1)
									{
										p.sendMessage("You should Choose ArrowType First");
									}
									p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1.0f, 0f);
									
				                }
				            }, i*2); 
		            		
		            	}
		                dpcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
					}
		            }
				}
			}
			
	}
	

	@EventHandler
	public void Fireworkdischarge(FireworkExplodeEvent d) 
	{
			Firework fw = (Firework) d.getEntity();
		    if (fw.hasMetadata("discharge") && fw.getShooter() instanceof Player) {
		    	Player p = (Player) fw.getShooter();
		        
				
		    	for (Entity e : fw.getNearbyEntities(5, 5, 5))
				{
					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
					{
							LivingEntity le = (LivingEntity)e;
							{
								if (le instanceof Player) 
								{
									new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
									Player p1 = (Player) le;
									if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
									if(PartyData.getParty(p).equals(PartyData.getParty(p1)))
										{
											continue;
										}
									}
								}
								Location el = le.getLocation();
								try {
								if(arrowtype.get(p.getName()) == 0)
								{
									p.getWorld().strikeLightningEffect(el);
									le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
								}
								if(arrowtype.get(p.getName()) == 1)
								{
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
								}
								if(arrowtype.get(p.getName()) == 2)
								{
									le.setFireTicks(40);
								}
								if(arrowtype.get(p.getName()) == 3)
								{
									le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0, false, false));
								}}

								catch(NullPointerException e1)
								{
									p.sendMessage("You should Choose ArrowType First");
								}
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
									enar.setDamage(lsd.Discharge.get(p.getUniqueId())*0.25 + player_damage.get(p.getName())*0.25);
								}
								le.damage(0, p);
								le.damage(lsd.Discharge.get(p.getUniqueId())*0.25 + player_damage.get(p.getName())*0.25, p);
								le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
								}
					}
				}
		    }
	}
	
	@EventHandler
	public void Fireworkdischarge(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Firework && d.getEntity() instanceof LivingEntity) 
		{
			Firework fw = (Firework) d.getDamager();
		    if (fw.hasMetadata("discharge")) {
		        d.setCancelled(true);
		    }
		}
	}
	
	@EventHandler
	public void GiantArrow(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			int sec = 13;
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 5) {
			if((!(p.isSneaking()) && !(p.isOnGround()) && !(p.isSprinting())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					
		        	ev.setCancelled(true);

					if(gacooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
	                    
		                long timer = (gacooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use GiantArrow").create());
			            }
		                else // if timer is done
		                {
		                    gacooldown.remove(p.getName()); // removing player from HashMap
		                    Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 30).getLocation();
			            	Location el = new Location(p.getWorld(), l.getX(), l.getY()+7, l.getZ());
			            	try {
			            	if(arrowtype.get(p.getName()) == 0)
							{
			            		p.getWorld().spawnParticle(Particle.FALLING_HONEY, el, 300, 0.2, 2, 0.2);
								p.getWorld().spawnParticle(Particle.FALLING_HONEY, el.add(0, 1, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_HONEY, el.add(0, 2, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_HONEY, el.add(0, 3, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_HONEY, el.add(0, 4, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_HONEY, el.add(0, 5, 0), 500, 1, 1, 1);
							}
							if(arrowtype.get(p.getName()) == 1)
							{
								p.getWorld().spawnParticle(Particle.FALLING_WATER, el, 300, 0.2, 2, 0.2);
								p.getWorld().spawnParticle(Particle.FALLING_WATER, el.add(0, 1, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_WATER, el.add(0, 2, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_WATER, el.add(0, 3, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_WATER, el.add(0, 4, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_WATER, el.add(0, 5, 0), 500, 1, 1, 1);
							}
							if(arrowtype.get(p.getName()) == 2)
							{
								p.getWorld().spawnParticle(Particle.FALLING_LAVA, el, 300, 0.2, 2, 0.2);
								p.getWorld().spawnParticle(Particle.FALLING_LAVA, el.add(0, 1, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_LAVA, el.add(0, 2, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_LAVA, el.add(0, 3, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_LAVA, el.add(0, 4, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_LAVA, el.add(0, 5, 0), 500, 1, 1, 1);
							}
							if(arrowtype.get(p.getName()) == 3)
							{
								p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el, 300, 0.2, 2, 0.2);
								p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el.add(0, 1, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el.add(0, 2, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el.add(0, 3, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el.add(0, 4, 0), 300, 0.3, 2, 0.3);
								p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el.add(0, 5, 0), 500, 1, 1, 1);
							}}

							catch(NullPointerException e1)
							{
								p.sendMessage("You should Choose ArrowType First");
							}
							p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0f);
							for (Entity e : p.getWorld().getNearbyEntities(l, 5, 5, 5))
							{
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
								{
									LivingEntity le = (LivingEntity)e;
										{
											if (le instanceof Player) 
											{
												new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
											else {
											hold.holding(p, le, (long) 60);
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
												enar.setDamage(lsd.GiantArrow.get(p.getUniqueId())*1.1 + player_damage.get(p.getName())*1.1);								
											}
											le.damage(0, p);
											le.damage(lsd.GiantArrow.get(p.getUniqueId())*1.1 + player_damage.get(p.getName())*1.1, p);
											p.getWorld().spawnEntity(el, EntityType.ARROW);
											le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
											try {
											if(arrowtype.get(p.getName()) == 0)
											{
												p.getWorld().strikeLightningEffect(el);
												le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
											}
											if(arrowtype.get(p.getName()) == 1)
											{
												le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
												le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
											}
											if(arrowtype.get(p.getName()) == 2)
											{
												le.setFireTicks(40);
											}
											if(arrowtype.get(p.getName()) == 3)
											{
												le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0, false, false));
											}}

											catch(NullPointerException e1)
											{
												p.sendMessage("You should Choose ArrowType First");
											}
										}
										}
								}
							}
			                gacooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 30).getLocation();
		            	Location el = new Location(p.getWorld(), l.getX(), l.getY()+7, l.getZ());
		            	try {
		            	if(arrowtype.get(p.getName()) == 0)
						{
		            		p.getWorld().spawnParticle(Particle.FALLING_HONEY, el, 300, 0.2, 2, 0.2);
							p.getWorld().spawnParticle(Particle.FALLING_HONEY, el.add(0, 1, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_HONEY, el.add(0, 2, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_HONEY, el.add(0, 3, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_HONEY, el.add(0, 4, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_HONEY, el.add(0, 5, 0), 500, 1, 1, 1);
						}
						if(arrowtype.get(p.getName()) == 1)
						{
							p.getWorld().spawnParticle(Particle.FALLING_WATER, el, 300, 0.2, 2, 0.2);
							p.getWorld().spawnParticle(Particle.FALLING_WATER, el.add(0, 1, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_WATER, el.add(0, 2, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_WATER, el.add(0, 3, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_WATER, el.add(0, 4, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_WATER, el.add(0, 5, 0), 500, 1, 1, 1);
						}
						if(arrowtype.get(p.getName()) == 2)
						{
							p.getWorld().spawnParticle(Particle.FALLING_LAVA, el, 300, 0.2, 2, 0.2);
							p.getWorld().spawnParticle(Particle.FALLING_LAVA, el.add(0, 1, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_LAVA, el.add(0, 2, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_LAVA, el.add(0, 3, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_LAVA, el.add(0, 4, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_LAVA, el.add(0, 5, 0), 500, 1, 1, 1);
						}
						if(arrowtype.get(p.getName()) == 3)
						{
							p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el, 300, 0.2, 2, 0.2);
							p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el.add(0, 1, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el.add(0, 2, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el.add(0, 3, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el.add(0, 4, 0), 300, 0.3, 2, 0.3);
							p.getWorld().spawnParticle(Particle.FALLING_NECTAR, el.add(0, 5, 0), 500, 1, 1, 1);
						}}

						catch(NullPointerException e1)
						{
							p.sendMessage("You should Choose ArrowType First");
						}
						p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0f);
						for (Entity e : p.getWorld().getNearbyEntities(l, 5, 5, 5))
						{
							if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
									{
										if (le instanceof Player) 
										{
											new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
										else {
										hold.holding(p, le, (long) 60);
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
											enar.setDamage(lsd.GiantArrow.get(p.getUniqueId())*1.1 + player_damage.get(p.getName())*1.1);								
										}
										le.damage(0, p);
										le.damage(lsd.GiantArrow.get(p.getUniqueId())*1.1 + player_damage.get(p.getName())*1.1, p);
										p.getWorld().spawnEntity(el, EntityType.ARROW);
										le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
										try {
										if(arrowtype.get(p.getName()) == 0)
										{
											p.getWorld().strikeLightningEffect(el);
											le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
										}
										if(arrowtype.get(p.getName()) == 1)
										{
											le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
											le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
										}
										if(arrowtype.get(p.getName()) == 2)
										{
											le.setFireTicks(40);
										}
										if(arrowtype.get(p.getName()) == 3)
										{
											le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0, false, false));
										}}

										catch(NullPointerException e1)
										{
											p.sendMessage("You should Choose ArrowType First");
										}
									}
									}
							}
						}
		                gacooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
					ev.setCancelled(true);
				}
			}
			}
	}
	
	@EventHandler
	public void Explosion(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof Arrow) 
		{
		Projectile a = (Projectile) d.getEntity();
		if(a.getShooter() instanceof Player)
			{
			Player p = (Player) a.getShooter();
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 5 && a.hasMetadata("Explosion of"+p.getName())) {
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
					{
					
					
					if(d.getHitEntity() instanceof LivingEntity && d.getHitBlock() == null) {
						LivingEntity he = (LivingEntity)d.getHitEntity();
						Location el = he.getLocation();
		                for (Entity a1 : p.getWorld().getNearbyEntities(el, 4, 4, 4))
						{
							if ((!(a1 == p))&& a1 instanceof LivingEntity&& !(a1.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)a1;
									{
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
										else {
										le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.ENTITY_EXPLOSION, player_damage.get(p.getName())));
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
											enar.setDamage(player_damage.get(p.getName())*0.876 + lsd.Explosion.get(p.getUniqueId())*0.68);								
										}
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.876 + lsd.Explosion.get(p.getUniqueId())*0.68, p);
										le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
										p.playSound(el, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
										try {
						                if(arrowtype.get(p.getName()) == 0)
										{
											p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, el, 1, 1, 1, 1);
											p.getWorld().spawnParticle(Particle.FLASH, el, 30, 3, 1, 3);
											le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
										}
										if(arrowtype.get(p.getName()) == 1)
										{
											p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, el, 1, 1, 1, 1);
											p.getWorld().spawnParticle(Particle.CLOUD, el, 40, 3, 1, 3);
											p.getWorld().spawnParticle(Particle.WATER_DROP, el, 40, 3, 1, 3);
											p.getWorld().spawnParticle(Particle.DRIP_WATER, el, 40, 3, 1, 3);
											p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1, 1);
											le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
											le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
										}
										if(arrowtype.get(p.getName()) == 2)
										{
											p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, el, 1, 1, 1, 1);
											p.getWorld().spawnParticle(Particle.FLAME, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.LAVA, el, 40, 3, 1, 3, 0);
											le.setFireTicks(40);
										}
										if(arrowtype.get(p.getName()) == 3)
										{
											p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, el, 1, 1, 1, 1);
											p.getWorld().spawnParticle(Particle.SPELL_MOB_AMBIENT, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.END_ROD, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.PORTAL, el, 40, 3, 1, 3, 0);
											le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0, false, false));
										}}

										catch(NullPointerException e1)
										{
											p.sendMessage("You should Choose ArrowType First");
										}
									}
									}
							}
						}
						
					}
					

					else if(d.getHitBlock() !=null) {
						Location el = d.getHitBlock().getLocation();
						p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, el, 1, 1, 1, 1);
						p.playSound(el, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
		                for (Entity a1 : p.getWorld().getNearbyEntities(el, 4, 4, 4))
						{
							if ((!(a1 == p))&& a1 instanceof LivingEntity&& !(a1.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)a1;
									{
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
										else {
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
											enar.setDamage(player_damage.get(p.getName())*0.876 + lsd.Explosion.get(p.getUniqueId())*0.68);								
										}
										le.damage(0, p);
										le.damage(player_damage.get(p.getName())*0.876 + lsd.Explosion.get(p.getUniqueId())*0.68, p);
										try {
						                if(arrowtype.get(p.getName()) == 0)
										{
											p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, el, 1, 1, 1, 1);
											p.getWorld().spawnParticle(Particle.FLASH, el, 30, 3, 1, 3);
											le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
										}
										if(arrowtype.get(p.getName()) == 1)
										{
											p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, el, 1, 1, 1, 1);
											p.getWorld().spawnParticle(Particle.CLOUD, el, 40, 3, 1, 3);
											p.getWorld().spawnParticle(Particle.WATER_DROP, el, 40, 3, 1, 3);
											p.getWorld().spawnParticle(Particle.DRIP_WATER, el, 40, 3, 1, 3);
											p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1, 1);
											le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
											le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
										}
										if(arrowtype.get(p.getName()) == 2)
										{
											p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, el, 1, 1, 1, 1);
											p.getWorld().spawnParticle(Particle.FLAME, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.LAVA, el, 40, 3, 1, 3, 0);
											le.setFireTicks(40);
										}
										if(arrowtype.get(p.getName()) == 3)
										{
											p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, el, 1, 1, 1, 1);
											p.getWorld().spawnParticle(Particle.SPELL_MOB_AMBIENT, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.END_ROD, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.PORTAL, el, 40, 3, 1, 3, 0);
											le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0, false, false));
										}}

										catch(NullPointerException e1)
										{
											p.sendMessage("You should Choose ArrowType First");
										}
									}
									}
							}
						}
						
					}
					
					}	
				}
			}
		}
		
	}
		

	@EventHandler
	public void Explosion(EntityShootBowEvent a) 
	{
		int sec = 4;
		
		if(a.getEntity() instanceof Player)
		{
			Player p = (Player) a.getEntity();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 5 && p.isSneaking()) {
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					if(a.getProjectile().getType() == EntityType.ARROW)
					{
						if(excooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            long timer = (excooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
			                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Explosion").create());
				            }
				            else // if timer is done
				            {
				                excooldown.remove(p.getName()); // removing player from HashMap
			                    a.getProjectile().setMetadata("Explosion of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				                excooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {
		                    a.getProjectile().setMetadata("Explosion of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				            excooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
						
					}
				}
			}
		}
	}
	
	@EventHandler
	public void ChargingShot(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity&& !d.isCancelled()) 
		{
		Projectile a = (Projectile) d.getDamager();
		if(a.getShooter() instanceof Player)
			{
			Player p = (Player) a.getShooter();
				Location el = d.getEntity().getLocation();
				d.setDamage(d.getDamage()*1.1);
			    
				
				
				if(playerclass.get(p.getUniqueId()) == 5 && a.hasMetadata("ChargingShot of"+p.getName())) {
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW )
					{
					
					
			                for (Entity a1 : p.getWorld().getNearbyEntities(el, 4, 4, 4))
							{
								if ((!(a1 == p))&& a1 instanceof LivingEntity&& !(a1.hasMetadata("fake"))) 
								{
									LivingEntity le = (LivingEntity)a1;
										{
											if (le instanceof Player) 
											{
												new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
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
											else {
											le.setLastDamageCause(new EntityDamageEvent(le, DamageCause.ENTITY_EXPLOSION, player_damage.get(p.getName())));
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
												enar.setDamage(d.getDamage()*1.2+player_damage.get(p.getName())*(1.3+ lsd.ChargingShot.get(p.getUniqueId())*0.1));								
											}
											le.damage(d.getDamage()*1.2, p);
											le.damage(player_damage.get(p.getName())*(1.3+ lsd.ChargingShot.get(p.getUniqueId())*0.1), p);
											le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
											p.playSound(el, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
											p.getWorld().spawnParticle(Particle.FLASH, el, 10, 3, 1, 3);
											le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
											p.getWorld().spawnParticle(Particle.WATER_DROP, el, 40, 3, 1, 3);
											p.getWorld().spawnParticle(Particle.DRIP_WATER, el, 40, 3, 1, 3);
											p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1, 1);
											le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
											le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
											p.getWorld().spawnParticle(Particle.FLAME, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.LAVA, el, 40, 3, 1, 3, 0);
											le.setFireTicks(40);									
											p.getWorld().spawnParticle(Particle.SPELL_MOB_AMBIENT, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.END_ROD, el, 40, 3, 1, 3, 0);
											p.getWorld().spawnParticle(Particle.PORTAL, el, 40, 3, 1, 3, 0);
											le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0, false, false));
											}
										}
								}
							}
							
					}	
				}
			}
		}
	}

	@EventHandler
	public void ChargingShot(EntityShootBowEvent a) 
	{
		int sec = 14;
		
		if(a.getEntity() instanceof Player)
		{
			Player p = (Player) a.getEntity();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 5 && !p.isSneaking() && p.isSprinting()) {
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					if(a.getProjectile().getType() == EntityType.ARROW)
					{
						if(cscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
				        {
				            long timer = (cscooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
				            if(!(timer < 0)) // if timer is still more then 0 or 0
				            {
			                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use ChargingShot").create());
				            }
				            else // if timer is done
				            {
				            	cscooldown.remove(p.getName()); // removing player from HashMap
			                    a.getProjectile().setMetadata("ChargingShot of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                    cscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else // if cooldown doesn't have players name in it
				        {
		                    a.getProjectile().setMetadata("ChargingShot of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    cscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
						
					}
				}
			}
		}
	}
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

	    
		
		
		if(playerclass.get(p.getUniqueId()) == 5)
		{
			if(p.getInventory().getItemInMainHand().getType().name().equals("BOW"))
			{
					e.setCancelled(true);
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
	    
		
		
			
			if(playerclass.get(p.getUniqueId()) == 5 && (is.getType().name().equals("BOW")) && p.isSneaking())
			{
				ev.setCancelled(true);
				if(bultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (bultcooldown.get(p.getName())/1000 + 45) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use AbsorbingArrow").create());
			        }
	                else // if timer is done
	                {
	                    bultcooldown.remove(p.getName()); // removing player from HashMap
	                    p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0f, 2f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 1.0f, 2f);
	                    Arrow abar = (Arrow)p.launchProjectile(Arrow.class);
	                    abar.setShooter(p);
	                    abar.setMetadata("abar of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	                    abar.setCritical(true);
	                    abar.setVelocity(p.getLocation().getDirection().normalize().multiply(10));
	                    abar.setDamage(0);
	                    abar.setGlowing(true);
						p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 100, 2, 2, 2);
						p.sendTitle(ChatColor.MAGIC + "WING", null);
						bultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0f, 2f);
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 1.0f, 2f);
                    Arrow abar = (Arrow)p.launchProjectile(Arrow.class);
                    abar.setShooter(p);
                    abar.setMetadata("abar of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
                    abar.setCritical(true);
                    abar.setVelocity(p.getLocation().getDirection().normalize().multiply(10));
	                abar.setDamage(0);
                    abar.setGlowing(true);
					p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 100, 2, 2, 2);
					p.sendTitle(ChatColor.MAGIC + "WING", null);
					bultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	                
	            }
			}	
	}
	
	

	@EventHandler
	public void ULT(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)ev.getEntity().getShooter();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 5 && ev.getEntity().hasMetadata("abar of"+p.getName())) {
				if(ev.getHitEntity()!=null) {
					Entity e = ev.getHitEntity();
					Location el = e.getLocation();
					for(int i =1; i<40; i++) {
                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.playSound(el, Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1.0f, 0.0f);
				        		p.getWorld().spawnParticle(Particle.CLOUD, el, 500,15,13,15);
								p.getWorld().spawnParticle(Particle.ASH, el, 500, 15,13,15,0);
			                	for (Entity e : p.getWorld().getNearbyEntities(el, 15, 13, 15))
								{
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
									{
										LivingEntity le = (LivingEntity)e;
											{
							                    le.teleport(le);
												hold.holding(p, le, (long) 2);
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
													enar.setDamage(player_damage.get(p.getName())*0.1);								
												}
							                    le.damage(0,p);
							                    le.damage(player_damage.get(p.getName())*0.1,p);
													
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
                	   }, i*2); 
				}
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                    p.playSound(el, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
		        		p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, el, 10,8,8,8);
						p.playSound(el, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
						p.getWorld().spawnParticle(Particle.FLASH, el, 100, 10, 10, 10);
						p.getWorld().spawnParticle(Particle.WATER_DROP, el,100, 10, 10, 10);
						p.getWorld().spawnParticle(Particle.DRIP_WATER, el, 100, 10, 10, 10);
						p.getWorld().spawnParticle(Particle.FLAME, el, 40, 100, 10, 10);
						p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, el, 100, 10, 10, 10);
						p.getWorld().spawnParticle(Particle.LAVA, el, 40, 3, 1, 3, 0);
						p.getWorld().spawnParticle(Particle.SPELL_MOB_AMBIENT, el,100, 10, 10, 10);
						p.getWorld().spawnParticle(Particle.END_ROD, el, 100, 10, 10, 10, 0);
						p.getWorld().spawnParticle(Particle.PORTAL, el, 100, 10, 10, 10, 0);
	                	for (Entity e : p.getWorld().getNearbyEntities(el, 10, 10, 10))
						{
                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
									{
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
											enar.setDamage(player_damage.get(p.getName())*5);								
										}
										le.damage(player_damage.get(p.getName())*5, p);
										le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
										le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
										le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0, false, false));
										p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1, 1);
										le.setFireTicks(40);									
											
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
        	   }, 82); 
			}
				else if(ev.getHitBlock()!=null) {
					Block e = ev.getHitBlock();
					Location el = e.getLocation();
					for(int i =1; i<40; i++) {
                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                    p.playSound(el, Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1.0f, 0.0f);
				        		p.getWorld().spawnParticle(Particle.CLOUD, el, 500,15,13,15);
								p.getWorld().spawnParticle(Particle.ASH,el, 500, 15,13,15,0);
			                	for (Entity e : p.getWorld().getNearbyEntities(el, 15, 13, 15))
								{
		                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
									{
										LivingEntity le = (LivingEntity)e;
											{
							                    le.teleport(el);
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
													enar.setDamage(player_damage.get(p.getName())*0.1);								
												}
												hold.holding(p, le, (long) 2);
							                    le.damage(0,p);
							                    le.damage(player_damage.get(p.getName())*0.1,p);
													
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
                	   }, i*2); 
				}
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                    p.playSound(el, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
		        		p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, el, 10,8,8,8);
						p.playSound(el, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
						p.getWorld().spawnParticle(Particle.FLASH, el, 100, 10, 10, 10);
						p.getWorld().spawnParticle(Particle.WATER_DROP, el,100, 10, 10, 10);
						p.getWorld().spawnParticle(Particle.DRIP_WATER, el, 100, 10, 10, 10);
						p.getWorld().spawnParticle(Particle.FLAME, el, 40, 100, 10, 10);
						p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, el, 100, 10, 10, 10);
						p.getWorld().spawnParticle(Particle.LAVA, el, 40, 3, 1, 3, 0);
						p.getWorld().spawnParticle(Particle.SPELL_MOB_AMBIENT, el,100, 10, 10, 10);
						p.getWorld().spawnParticle(Particle.END_ROD, el, 100, 10, 10, 10, 0);
						p.getWorld().spawnParticle(Particle.PORTAL, el, 100, 10, 10, 10, 0);
	                	for (Entity e : p.getWorld().getNearbyEntities(el, 10, 10, 10))
						{
                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
							{
								LivingEntity le = (LivingEntity)e;
									{
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
											enar.setDamage(player_damage.get(p.getName())*5);								
										}
										le.damage(player_damage.get(p.getName())*5, p);
										le.playEffect(org.bukkit.EntityEffect.HURT_EXPLOSION);
										le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, false, false));
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0, false, false));
										le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0, false, false));
										p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1, 1);
										le.setFireTicks(40);									
											
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
        	   }, 82); 
			}
			}
		}
	}
		
	
	
	@EventHandler
	public void Arrowshoot(EntityShootBowEvent a) 
	{
		
		if(a.getEntity() instanceof Player)
		{
			Player p = (Player) a.getEntity();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 5) {
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
				player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*0.5 + p.getLevel()/10);
				
				if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
				{
					player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
				}
				}
			}
		}
	}
		
	@EventHandler
	public void Arrowshoot(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			if(playerclass.get(p.getUniqueId()) == 5) {
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
				player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*0.5 + p.getLevel()/10);
				
				if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
				{
					player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
				}
				}
			}
		}
	}
	
	@EventHandler
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
		Projectile a = (Projectile)d.getDamager();
			if(a.getShooter() instanceof Player) {
			Player p = (Player)a.getShooter();
				if(playerclass.get(p.getUniqueId()) == 5) {			
					
					if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
					{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
					}
					d.setDamage(d.getDamage()*(1+lsd.MagicArrow.get(p.getUniqueId())*0.03025));
						
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			Player p = (Player) d.getDamager();
			
			if(playerclass.get(p.getUniqueId()) == 5) {		
				
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*0.5 + p.getLevel()/10);
					
					if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					{
						player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
					}
				}
				d.setDamage(d.getDamage()*(1+lsd.MagicArrow.get(p.getUniqueId())*0.03025));
			}
		}
		
	}
}



