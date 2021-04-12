package io.github.chw3021.sniper;




import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.party.PartyData;
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
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Wither;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
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
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Snipskills implements Serializable, Listener {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 4721520680967536186L;
	private HashMap<String, Long> wrcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> arcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> dpcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> bultcooldown = new HashMap<String, Long>();
	private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<Player, Integer> sz = new HashMap<Player, Integer>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;
	Holding hold = Holding.getInstance();
	private SnipSkillsData ssd = new SnipSkillsData(SnipSkillsData.loadData(path +"/plugins/RPGskills/SnipskillsData.data"));


	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		SnipSkillsData s = new SnipSkillsData(SnipSkillsData.loadData(path +"/plugins/RPGskills/SnipskillsData.data"));
		ssd = s;
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
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Snipskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				SnipSkillsData s = new SnipSkillsData(SnipSkillsData.loadData(path +"/plugins/RPGskills/SnipskillsData.data"));
				ssd = s;
			}
			
		}
	}

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		SnipSkillsData s = new SnipSkillsData(SnipSkillsData.loadData(path +"/plugins/RPGskills/SnipskillsData.data"));
		ssd = s;
	}
	
	@EventHandler
	public void Wire(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		int sec = 2;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 4) {	
			if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.isOnGround() && !p.isSneaking())
			{	
				
				
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW && ssd.Wire.get(p.getUniqueId())>=1)
				{
					ev.setCancelled(true);
					if(wrcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
	                    
		                long timer = (wrcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Wire").create());
		                }
		                else // if timer is done
		                {
		                    wrcooldown.remove(p.getName()); // removing player from HashMap
		                    p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_START, 1.0f, 0.5f);
				        	p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 1.0f, 0.5f);
				        	ev.setCancelled(true);
				        	ArrayList<Location> line = new ArrayList<Location>();
		                    AtomicInteger j = new AtomicInteger(0);
		                    AtomicInteger a = new AtomicInteger(0);
		                    AtomicInteger b = new AtomicInteger(0);
		                    for(double d = 0.1; d <= 15; d += 0.1) {
			                    Location pl = p.getEyeLocation().clone();
								pl.add(pl.getDirection().normalize().multiply(d));
								line.add(pl);
		                    }
		                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
			                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
			                    for(int k=0; k<line.indexOf(block); k++) {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
			             			p.getWorld().spawnParticle(Particle.CRIT ,line.get(a.getAndIncrement()).add(0, -0.7, 0), 3, 0.2,0.2,0.2,0);
							            }
				                	   }, j.getAndIncrement()/15); 
								 }
			                    for(int k=0; k<line.indexOf(block); k++) {
				                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				             		@Override
						                	public void run() 
							                {	
						                    	p.teleport(line.get(b.getAndIncrement()));
						                    	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 80, 2, false, false));
								            }
					                	   }, j.getAndIncrement()/15); 
									 }}
		                    else {
		                    	line.forEach(i -> {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
			             			p.getWorld().spawnParticle(Particle.CRIT ,line.get(a.getAndIncrement()), 3, 0.2,0.2,0.2,0);
							            }
				                	   }, j.getAndIncrement()/15); 
								}); 
		                    }
		                	wrcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_START, 1.0f, 0.5f);
			        	p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 1.0f, 0.5f);
			        	ev.setCancelled(true);
			        	ArrayList<Location> line = new ArrayList<Location>();
	                    AtomicInteger j = new AtomicInteger(0);
	                    AtomicInteger a = new AtomicInteger(0);
	                    AtomicInteger b = new AtomicInteger(0);
	                    for(double d = 0.1; d <= 15; d += 0.1) {
		                    Location pl = p.getEyeLocation().clone();
							pl.add(pl.getDirection().normalize().multiply(d));
							line.add(pl);
	                    }
	                    if(line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().isPresent()) {
		                    Location block =line.stream().filter(o -> !o.getBlock().isPassable()).findFirst().get();
		                    for(int k=0; k<line.indexOf(block); k++) {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             			p.getWorld().spawnParticle(Particle.CRIT ,line.get(a.getAndIncrement()).add(0, -0.7, 0), 3, 0.2,0.2,0.2,0);
						            }
			                	   }, j.getAndIncrement()/15); 
							 }
		                    for(int k=0; k<line.indexOf(block); k++) {
			                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
					                	public void run() 
						                {	
					                    	p.teleport(line.get(b.getAndIncrement()));
					                    	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 80, 2, false, false));
							            }
				                	   }, j.getAndIncrement()/15); 
								 }}
	                    else {
	                    	line.forEach(i -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {	
		             			p.getWorld().spawnParticle(Particle.CRIT ,line.get(a.getAndIncrement()), 3, 0.2,0.2,0.2,0);
						            }
			                	   }, j.getAndIncrement()/15); 
							}); 
	                    }
	                	wrcooldown.put(p.getName(), System.currentTimeMillis());
		            }
				}
			}
		}
	}

	@EventHandler
	public void Arrowshootmanagement(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity) 
		{
		Projectile a = (Projectile) d.getDamager();
		LivingEntity e = (LivingEntity)d.getEntity();
		if(a.getShooter() instanceof Player)
			{
			Player p = (Player) a.getShooter();
			Location el =e.getLocation();
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 4)			
			{
					if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{
						
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*0.5 + p.getLevel()/10);
							
					}
			}
			}
		}
	}
	@EventHandler
	public void swcancle(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
        
		
		
		if(playerclass.get(p.getUniqueId()) == 4) {
		if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
		{
        	ev.setCancelled(true);
		}
		}
	}
	@EventHandler
	public void ArmourPiercingArrow(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			int sec = 10;
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 4) {
			if(((p.isSneaking()) && !(p.isSprinting())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
		        	ev.setCancelled(true);
					
					if(arcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
	                    
		                long timer = (arcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use APA").create());
		                }
		                else // if timer is done
		                {
		                    arcooldown.remove(p.getName()); // removing player from HashMap
			            	
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 2f);
							Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 20, 1);
		                	ar.setShooter(p);
		                	ar.setShotFromCrossbow(true);
		                	ar.setCritical(true);
		                	ar.setPierceLevel(127);
		                    ar.setMetadata("APA", new FixedMetadataValue(RMain.getInstance(), true));
							p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
		                	ar.setDamage(ar.getDamage()*(1+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.2));
		                	arcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 2f);
						Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 20, 1);
	                	ar.setShooter(p);
	                	ar.setShotFromCrossbow(true);
	                	ar.setCritical(true);
	                	ar.setPierceLevel(127);
	                    ar.setMetadata("APA", new FixedMetadataValue(RMain.getInstance(), true));
						p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
	                	ar.setDamage(ar.getDamage()*(1+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.2));
	                	arcooldown.put(p.getName(), System.currentTimeMillis());
		            }
				}
			}
			}
	}

	@EventHandler
	public void APA2(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().getShooter() instanceof Player && ev.getEntity() instanceof Arrow)
		{
			Player p = (Player)ev.getEntity().getShooter();
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 4) {
				if(ev.getHitEntity() instanceof LivingEntity) {
					LivingEntity le =(LivingEntity) ev.getHitEntity();
					Arrow ar = (Arrow) ev.getEntity();
					{
            			if(ar.hasMetadata("APA")) {
            				le.damage(le.getHealth()*(0.06+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.001),ar);
            				Location el = le.getLocation().add(0, 0.1, 0);
            				if(!el.getBlock().isPassable()) {
            					el.add(0, 1.1, 0);
            				}
            				Vector dinv = p.getEyeLocation().toVector().subtract(el.toVector()).normalize();
            				Vector dinvv = dinv.clone();
            				if(le.getLocation().getY()<p.getEyeLocation().getY()) {
            					dinvv.rotateAroundAxis(dinv.clone().rotateAroundY(Math.PI/2), -Math.PI/6).normalize();
            				}
            				else {
            					dinvv.rotateAroundAxis(dinv.clone().rotateAroundY(Math.PI/2), -Math.PI/45).normalize();					
            				}
            				
            				
            				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
            				{
            	         	@Override
            		                public void run() 
        	         				{	
		            					Location el = le.getLocation().add(0, 0.1, 0);
		            					if(!el.getBlock().isPassable()) {
		            						el.add(0, 1.1, 0);
		            					}
		            	         		Snowball din = (Snowball) le.getWorld().spawnEntity(el.clone().add(dinvv.clone().multiply(0.385)), EntityType.SNOWBALL);
		                				din.setSilent(true);
		        						din.setItem(new ItemStack(Material.CROSSBOW));
		                				din.setBounce(false);
		                				din.setGravity(false);
		                				din.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
		                				din.setMetadata("din of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		                				if(le.getHealth()*(0.06+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.001)<0.1) {
		                					din.setCustomName(ChatColor.AQUA + String.valueOf(Math.round(le.getHealth()*(0.06+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.001)*1000)/1000.000) + " ["+ p.getName()+"](APA)");				
		                				}
		                				else if(le.getHealth()*(0.06+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.001)>=999999) {
		                					din.setCustomName(ChatColor.RED + "!999999!" + " ["+ p.getName()+"]");	
		                					din.setGlowing(true);
		                					
		                				}
		                				else {
		                					din.setCustomName(ChatColor.AQUA + String.valueOf(Math.round(le.getHealth()*(0.06+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.001)*10)/10.0) + " ["+ p.getName()+"](APA)");				
		                				}
		                				din.setCustomNameVisible(true);
		                				din.setVelocity((el.clone().add(dinvv.clone().multiply(0.15))).toVector().subtract(le.getLocation().toVector()).normalize().multiply(0.12));
		                				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
		                				{
		                	         	@Override
		                		                public void run() 
		                	         				{	
		                	         					din.remove();
		                				            }
		                	            }, 30);
        				            }
            	            }, 3);
            			}
					}
				}
			}			
		}
	}
	
	@EventHandler
	public void APA(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().getShooter() instanceof Player && ev.getEntity() instanceof Arrow)
		{
			Player p = (Player)ev.getEntity().getShooter();
		    
			
			
			
			if(playerclass.get(p.getUniqueId()) == 4) {
				if(ev.getHitEntity() instanceof Wither) {
					Wither e =(Wither) ev.getHitEntity();
					Arrow ar = (Arrow) ev.getEntity();
					{
                		if (e.getHealth()<=e.getMaxHealth()/2) 
						{
                			if(ar.hasMetadata("APA")) {
    		                    e.damage(ar.getDamage()*16, p);
                			}
                			else {
                				
            					if(Math.abs(ar.getLocation().getY() - (e.getEyeLocation().getY())) <= (0.5+ssd.HeadShot.get(p.getUniqueId())*0.01)) {
                    				e.damage(ar.getDamage()/4*1.36*(1+ssd.HeadShot.get(p.getUniqueId())*0.086), p);
			                		e.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
			                		p.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, e.getEyeLocation(), 50, 1,1,1);
			                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("HEADSHOT").create());
            					}
            					else {
        		                    e.damage(ar.getDamage()/4, p);
            						
            					}
                			}
						}
					}
				}
				if(ev.getHitEntity() instanceof Enderman) {
					Enderman e =(Enderman) ev.getHitEntity();
					Arrow ar = (Arrow) ev.getEntity();
					{
            			if(ar.hasMetadata("APA")) {
		                    e.damage(ar.getDamage()*16, p);
            			}
            			else {
            				
        					if(Math.abs(ar.getLocation().getY() - (e.getEyeLocation().getY())) <= (0.5+ssd.HeadShot.get(p.getUniqueId())*0.01)) {
                				e.damage(ar.getDamage()/4*1.36*(1+ssd.HeadShot.get(p.getUniqueId())*0.086), p);
		                		e.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
		                		p.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, e.getEyeLocation(), 50, 1,1,1);
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("HEADSHOT").create());
        					}
        					else {
    		                    e.damage(ar.getDamage()/4, p);
        						
        					}
            			}
					}
				}
			}			
		}
	}
	
	@EventHandler
	public void FlashBomb(PlayerSwapHandItemsEvent ev) 
	{
			Player p = ev.getPlayer();
			int sec = 6;
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 4) {
			if(!p.isOnGround())
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{

					
					if(dpcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
	                    
		                double timer = ((dpcooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000); // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + (int)timer + " seconds to use FlashBomb").create());
		                }
		                else // if timer is done
		                {
		                    dpcooldown.remove(p.getName()); // removing player from HashMap
		                    Snowball sn = (Snowball) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.SNOWBALL);
		                    sn.setShooter(p);
		                    sn.setVelocity(sn.getVelocity().add(p.getLocation().getDirection().normalize().multiply(0.5)));
		                    sn.setPersistent(true);
		                    Location tl = p.getLocation();
		                    tl.setDirection(tl.getDirection().normalize().rotateAroundY(Math.PI));
		                    tl.add(tl.getDirection().normalize().multiply(4));
		                    tl.setDirection(p.getLocation().getDirection());
		                    if(!tl.getBlock().isPassable())
		                    {tl.add(0, 1, 0);}
		                    if(tl.getBlock().isPassable())
		                    {p.teleport(tl);}
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		    		                	public void run() 
		    			                {	
		                 					p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, sn.getLocation(), 2,1,1,1);
		                 					p.getWorld().spawnParticle(Particle.FLASH, sn.getLocation(), 20,5,5,5);
		                 					p.playSound(sn.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 2);
		                 					for(Entity e : sn.getNearbyEntities(5, 5, 5)) {
		                 						if(e instanceof LivingEntity && e!=p) {
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
		                 							LivingEntity le = (LivingEntity) e;
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
														enar.setDamage(player_damage.get(p.getName())+ssd.FlashBomb.get(p.getUniqueId())*0.1);								
													}
		                 							le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 56, 4, false, false));
		                 							le.damage(0, p);
		                 							le.damage(player_damage.get(p.getName())*0.5*(1+ssd.FlashBomb.get(p.getUniqueId())*0.0687), p);
		                 							hold.holding(p, le, 10l);
		                 						}
		                 					}
		    								sn.setPersistent(false);
		    								sn.remove();
		    				            }
		    	                	   }, 35); 
			                dpcooldown.put(p.getName(), System.currentTimeMillis());
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
		            	Snowball sn = (Snowball) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.SNOWBALL);
	                    sn.setShooter(p);
	                    sn.setVelocity(sn.getVelocity().add(p.getLocation().getDirection().normalize().multiply(0.5)));
	                    sn.setPersistent(true);
	                    Location tl = p.getLocation();
	                    tl.setDirection(tl.getDirection().normalize().rotateAroundY(Math.PI));
	                    tl.add(tl.getDirection().normalize().multiply(4));
	                    tl.setDirection(p.getLocation().getDirection());
	                    if(!tl.getBlock().isPassable())
	                    {tl.add(0, 1, 0);}
	                    if(tl.getBlock().isPassable())
	                    {p.teleport(tl);}
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                 		@Override
	    		                	public void run() 
	    			                {	
	                 					p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, sn.getLocation(), 2,1,1,1);
	                 					p.getWorld().spawnParticle(Particle.FLASH, sn.getLocation(), 20,5,5,5);
	                 					p.playSound(sn.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 2);
	                 					for(Entity e : sn.getNearbyEntities(5, 5, 5)) {
	                 						if(e instanceof LivingEntity && e!=p) {
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
	                 							LivingEntity le = (LivingEntity) e;
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
													enar.setDamage(player_damage.get(p.getName())+ssd.FlashBomb.get(p.getUniqueId())*0.1);								
												}
	                 							le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 56, 4, false, false));
	                 							le.damage(0, p);
	                 							le.damage(player_damage.get(p.getName())*0.5*(1+ssd.FlashBomb.get(p.getUniqueId())*0.0687), p);
	                 							hold.holding(p, le, 10l);
	                 						}
	                 					}
	    								sn.setPersistent(false);
	    								sn.remove();
	    				            }
	    	                	   }, 35); 
		                dpcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
					ev.setCancelled(true);
				}
			}
			}
	}
	

	
	@EventHandler
	public void Flare(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		int sec = 35;
        
		
		
		if(playerclass.get(p.getUniqueId()) == 4) {	
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) && !p.isSprinting()&& p.isSneaking())
			{	
				
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW && ssd.Flare.get(p.getUniqueId())>=1)
				{
					ev.setCancelled(true);
				    if(cscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			        {
			            long timer = (cscooldown.get(p.getName())/1000 + sec) - System.currentTimeMillis()/1000; // geting time in seconds
			            if(!(timer < 0)) // if timer is still more then 0 or 0
			            {
		                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use Flare").create());
			            }
			            else // if timer is done
			            {
			            	cscooldown.remove(p.getName()); // removing player from HashMap
			                Firework fr = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK);
		                    fr.setShotAtAngle(true);
		                    fr.setVelocity(p.getLocation().getDirection().multiply(2));
		                    fr.setShooter(p);
		                    fr.setMetadata("flare", new FixedMetadataValue(RMain.getInstance(), true));
				        	ev.setCancelled(true);
		            	
		        			FireworkEffect effect = FireworkEffect.builder()
		                                .with(Type.BALL_LARGE)
		                                .withColor(Color.YELLOW)
		                                .flicker(true)
		                                .trail(true)
		                                .build();
		                    FireworkMeta meta = fr.getFireworkMeta();
		                    meta.setPower(1);
		                    meta.addEffect(effect);
		                    fr.setFireworkMeta(meta);
			                cscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			            }
			        }
			        else // if cooldown doesn't have players name in it
			        {
			        	Firework fr = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK);
	                    fr.setShotAtAngle(true);
	                    fr.setVelocity(p.getLocation().getDirection().multiply(2));
	                    fr.setShooter(p);
	                    fr.setMetadata("flare", new FixedMetadataValue(RMain.getInstance(), true));
			        	ev.setCancelled(true);
	            	
	        			FireworkEffect effect = FireworkEffect.builder()
	                                .with(Type.BALL_LARGE)
	                                .withColor(Color.YELLOW)
	                                .flicker(true)
	                                .trail(true)
	                                .build();
	                    FireworkMeta meta = fr.getFireworkMeta();
	                    meta.setPower(1);
	                    meta.addEffect(effect);
	                    fr.setFireworkMeta(meta);
			            cscooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
			        }
				}
			}
		}
	}
	
	
		
	@EventHandler
	public void Camouflage(PlayerToggleSneakEvent e) 
	{
		
		Player p = (Player)e.getPlayer();
		Location l = p.getLocation();
        
		
		
		if(playerclass.get(p.getUniqueId()) == 4)			{	
			
			if(p.getInventory().getItemInMainHand().getType().name().contains("CROSSBOW") && ssd.Camouflage.get(p.getUniqueId())>=1)
			{
				if(e.isSneaking())
				{
					p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 10, 1, 1, 1);
					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.setSprinting(false);
							p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 6, 0, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 6, 150, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 6, 0, false, false));
		                }
					}, 0, 5); 
					sz.put(p, task);
				}
				else
				{
	        		if(sz.containsKey(p)) {
	        			Bukkit.getServer().getScheduler().cancelTask(sz.get(p));
	        			sz.remove(p);
	        		}
					
				}
			}
		}
			
	}

	@EventHandler
	public void DangerClose(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Projectile a = (Projectile) d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
    	final Location lel = le.getLocation();
		if(a.getShooter() instanceof Player && !le.hasMetadata("fake"))
			{
	        
			Player p = (Player) a.getShooter();
				
				
				if(playerclass.get(p.getUniqueId()) == 4) {
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{
						
	                	if (le instanceof Player) 
						{
							Player p1 = (Player) le;
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
	                	Location el = le.getLocation().add(0,3.5,0);
	                    ArrayList<Location> des = new ArrayList<>();
	                    for(int i=0; i<3; i++) {
	                    	Random random=new Random();
	                    	double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
	                    	double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
	                    	Location ell = el.clone().add(number, 0.5, number2);
	                    	des.add(ell.setDirection(lel.toVector().subtract(ell.toVector())));
	                    }
	                	des.forEach(l -> {
                			Firework fr = (Firework) p.getWorld().spawnEntity(l, EntityType.FIREWORK);
		                    fr.setVelocity(l.getDirection().multiply(0.6));
		        			FireworkEffect effect = FireworkEffect.builder()
	                                .with(Type.BALL)
	                                .withColor(Color.RED)
	                                .flicker(true)
	                                .trail(true)
	                                .build();
		                    fr.setShooter(p);
		                    fr.setShotAtAngle(true);
		                    fr.setMetadata("dangerclose", new FixedMetadataValue(RMain.getInstance(), true));
		                    FireworkMeta meta = fr.getFireworkMeta();
		                    meta.setPower(5);
		                    meta.addEffect(effect);
		                    fr.setFireworkMeta(meta);
	                	});
					
					}	
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
	        
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
	    	final Location lel = le.getLocation();
				
				if(playerclass.get(p.getUniqueId()) == 4 && p.getCooldown(Material.ARROW)<=0) {
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{
						p.setCooldown(Material.ARROW, 16);
	                	if (le instanceof Player) 
						{
							Player p1 = (Player) le;
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
	                	Location el = le.getLocation().add(0,3.5,0);
	                    ArrayList<Location> des = new ArrayList<>();
	                    for(int i=0; i<3; i++) {
	                    	Random random=new Random();
	                    	double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
	                    	double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
	                    	Location ell = el.clone().add(number, 0.5, number2);
	                    	des.add(ell.setDirection(lel.toVector().subtract(ell.toVector())));
	                    }
	                	des.forEach(l -> {
                			Firework fr = (Firework) p.getWorld().spawnEntity(l, EntityType.FIREWORK);
		                    fr.setVelocity(l.getDirection().multiply(0.6));
		        			FireworkEffect effect = FireworkEffect.builder()
	                                .with(Type.BALL)
	                                .withColor(Color.RED)
	                                .flicker(true)
	                                .trail(true)
	                                .build();
		                    fr.setShooter(p);
		                    fr.setShotAtAngle(true);
		                    fr.setMetadata("dangerclose", new FixedMetadataValue(RMain.getInstance(), true));
		                    FireworkMeta meta = fr.getFireworkMeta();
		                    meta.setPower(5);
		                    meta.addEffect(effect);
		                    fr.setFireworkMeta(meta);
	                	});
					
					}	
				}
		}
	}
	

	@EventHandler
	public void FireworkExplode(FireworkExplodeEvent f) 
	{
		if(f.getEntity().getShooter() instanceof Player && f.getEntity().hasMetadata("dangerclose")) {
			Firework fr = f.getEntity();
			Player p = (Player)fr.getShooter();
			Location frl = fr.getLocation();
			frl.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, frl, 1);
			frl.getWorld().spawnParticle(Particle.SMOKE_LARGE, frl, 30,1,1,1);
			frl.getWorld().playSound(frl, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1f, 0.32f);
			frl.getWorld().playSound(frl, Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1f, 0.2f);
			f.setCancelled(true);
			fr.remove();
			
			for(Entity e : frl.getWorld().getNearbyEntities(frl, 2.3,2.3, 2.3)) {
				if(e instanceof LivingEntity && e!=p && !(e.hasMetadata("fake"))) {
					LivingEntity le = (LivingEntity)e;
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
						enar.setDamage(player_damage.get(p.getName())*0.23*(1+ssd.DangerClose.get(p.getUniqueId())*0.0321));								
					}
					le.damage(0, p);
					le.damage(player_damage.get(p.getName())*0.23*(1+ssd.DangerClose.get(p.getUniqueId())*0.0321), p);
				}
			}
		}
		
		if(f.getEntity().getShooter() instanceof Player && f.getEntity().hasMetadata("flare")) {
			Player p = (Player)f.getEntity().getShooter();
			for(Entity e : f.getEntity().getNearbyEntities(30, 30, 30)) {
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
				if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))) {
					LivingEntity le = (LivingEntity)e;
					le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 0, false, false));

					int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	le.getWorld().spawnParticle(Particle.FLASH, f.getEntity().getLocation(), 400,30,30,30);
						}
					}, 5, 10);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
		                	public void run() 
			                {	
             				Bukkit.getServer().getScheduler().cancelTask(task);			                	
				            }
                		},	 400); 
				}
			}
		}
	}
	
	@EventHandler
	public void HeadShot(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Projectile a = (Projectile) d.getDamager();
		final BoundingBox al =  a.getBoundingBox();
		LivingEntity e = (LivingEntity)d.getEntity();
		if(a.getShooter() instanceof Player && !a.hasMetadata("APA"))
			{
	        
			Player p = (Player) a.getShooter();
			final Location pl =  p.getLocation();
				
				
				if(playerclass.get(p.getUniqueId()) == 4) {
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{
					
					
							if(e.getCategory() == EntityCategory.ILLAGER || e.getCategory() == EntityCategory.UNDEAD ||e.getType()==EntityType.CREEPER||e.getType()==EntityType.PLAYER||e.getType()==EntityType.BLAZE||e.getType()==EntityType.ENDERMAN) {
				                if(Math.abs((e.getBoundingBox().getMaxY())-al.getCenterY()) <= (0.25+ssd.HeadShot.get(p.getUniqueId())*0.1)) {
									
				                	if (e instanceof Player) 
									{
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
				                		e.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
									d.setDamage(d.getDamage()*1.36*(1+ssd.HeadShot.get(p.getUniqueId())*0.086));
									p.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, e.getEyeLocation(), 50, 1,1,1);
				                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("HEADSHOT").create());
				                	}
				                }
							}
							else {
				                if(Math.abs(e.getLocation().getDirection().angle(pl.getDirection())) <= (Math.PI+ssd.HeadShot.get(p.getUniqueId())*0.1) && Math.abs(e.getLocation().getDirection().angle(pl.getDirection())) > (Math.PI/2-ssd.HeadShot.get(p.getUniqueId())*0.1)) {
									e.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
									d.setDamage(d.getDamage()*1.36*(1+ssd.HeadShot.get(p.getUniqueId())*0.086));
									p.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, e.getEyeLocation(), 50, 1,1,1);
				                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("HEADSHOT").create());
									
				                }
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
        

		
		
		if(playerclass.get(p.getUniqueId()) == 4)
		{
			if(p.getInventory().getItemInMainHand().getType().name().equals("CROSSBOW"))
			{
					e.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
		
		
			
			if(playerclass.get(p.getUniqueId()) == 4 && (is.getType().name().equals("CROSSBOW")) && p.isSneaking())
			{
				p.setSprinting(true);
				ev.setCancelled(true);
				p.setSprinting(true);
				if(bultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                long timer = (bultcooldown.get(p.getName())/1000 + 70) - System.currentTimeMillis()/1000; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + timer + " seconds to use THE LAST ONE").create());
			        }
	                else // if timer is done
	                {
	                    bultcooldown.remove(p.getName()); // removing player from HashMap
	                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 13,0,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 30,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30,255,false,false));
						p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
						p.getWorld().playSound(p.getLocation(),Sound.BLOCK_BEACON_ACTIVATE, 1,0.3f);
						p.getWorld().playSound(p.getLocation(),Sound.BLOCK_CHEST_LOCKED, 1,0f);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_ARMOR_STAND_PLACE, 1,0.23f);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,0.23f);
						AtomicInteger j = new AtomicInteger();
						for(int i=0; i<25; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	p.sendTitle(org.bukkit.ChatColor.RED+"Charging...", j.incrementAndGet()*4 +"%", 5, 5, 5);
								}
				            }, i); 
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 600, 4, 4, 4);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_SQUID_DEATH, 1f, 1.3f);
								p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 1.6f);
								p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1.5f);
								p.playSound(p.getLocation(), Sound.BLOCK_BAMBOO_BREAK, 1.0f, 0.35f);
								Arrow ar = p.launchProjectile(Arrow.class);
			                    ar.setMetadata("APA", new FixedMetadataValue(RMain.getInstance(), true));
								ar.setVelocity(ar.getVelocity().normalize().multiply(200));
			                	ar.setShooter(p);
			                	ar.setShotFromCrossbow(true);
			                	ar.setCritical(true);
			                	ar.setPierceLevel(127);
								p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
			                	ar.setDamage(ar.getDamage()*10+player_damage.get(p.getName())*10);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
										ar.remove();
									}
					            }, 30); 
							}
			            }, 30); 
						bultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		                
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 13,0,false,false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 30,20,false,false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30,255,false,false));
					p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
					p.getWorld().playSound(p.getLocation(),Sound.BLOCK_BEACON_ACTIVATE, 1,0.3f);
					p.getWorld().playSound(p.getLocation(),Sound.BLOCK_CHEST_LOCKED, 1,0f);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_ARMOR_STAND_PLACE, 1,0.23f);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,0.23f);
					AtomicInteger j = new AtomicInteger();
					for(int i=0; i<25; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.sendTitle(org.bukkit.ChatColor.RED+"Charging...", j.incrementAndGet()*4 +"%", 5, 5, 5);
							}
			            }, i); 
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 600, 4, 4, 4);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
							p.playSound(p.getLocation(), Sound.ENTITY_SQUID_DEATH, 1f, 1.3f);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 1.6f);
							p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1.5f);
							p.playSound(p.getLocation(), Sound.BLOCK_BAMBOO_BREAK, 1.0f, 0.35f);
							Arrow ar = p.launchProjectile(Arrow.class);
		                    ar.setMetadata("APA", new FixedMetadataValue(RMain.getInstance(), true));
							ar.setVelocity(ar.getVelocity().normalize().multiply(200));
		                	ar.setShooter(p);
		                	ar.setShotFromCrossbow(true);
		                	ar.setCritical(true);
		                	ar.setPierceLevel(127);
							p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
		                	ar.setDamage(ar.getDamage()*10+(player_damage.get(p.getName())*10));
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									ar.remove();
								}
				            }, 30); 
						}
		            }, 30); 
					bultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}	
			
			
		}
	
	@EventHandler
	public void Fireworkdangerclose(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Firework && d.getEntity() instanceof LivingEntity) 
		{
			Firework fw = (Firework) d.getDamager();
		    if (fw.hasMetadata("dangerclose")) {
		        d.setCancelled(true);
		    }
		    if (fw.hasMetadata("flare")) {
		        d.setCancelled(true);
		    }
		}
	}
	
	
	@EventHandler
	public void Remodeling(EntityShootBowEvent a) 
	{
		
		if(a.getEntity() instanceof Player)
		{
			Player p = (Player) a.getEntity();
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 4) {
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					
					if(a.getProjectile().getType() == EntityType.ARROW)
					{
						Arrow ar = (Arrow) a.getProjectile();
						if(ar.isShotFromCrossbow()) {
							Arrow bolt = p.launchProjectile(Arrow.class);
							bolt.setVelocity(bolt.getVelocity().normalize().multiply(20));
							bolt.setShooter(p);
							player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.PIERCING)*0.5 + p.getLevel()/10);
							
							if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
							{
								player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
							}
							p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 1f, 2f);
							bolt.setCritical(true);
							bolt.setDamage(ar.getDamage()*(1+player_damage.get(p.getName())*0.025)*(1+ssd.Remodeling.get(p.getUniqueId())*0.05));
							if(bolt.getPierceLevel()+ssd.Remodeling.get(p.getUniqueId())<=127) {
							bolt.setPierceLevel(bolt.getPierceLevel()+ssd.Remodeling.get(p.getUniqueId()));}
							a.setProjectile(bolt);
							bolt.setPickupStatus(PickupStatus.ALLOWED);
						}
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
	        
			
			
			if(playerclass.get(p.getUniqueId()) == 4 && p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW) {
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.PIERCING)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
					}
				
		}
	}
	
	@EventHandler
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
			Projectile a = (Projectile)d.getDamager();
			if(a.getShooter() instanceof Player) {
			Player p = (Player)a.getShooter();
				if(playerclass.get(p.getUniqueId()) == 4) {
					if(a.hasMetadata("dangerclose") || a.hasMetadata("flare")) {
						d.setCancelled(true);
					}
					if(player_damage.containsKey(p.getName()))
					{
						player_damage.remove(p.getName());
						if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
						{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.PIERCING)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
						}
					}
					else
					{
						if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
						{
						player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.PIERCING)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
						}
						
					}
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
			{
			Player p = (Player) d.getDamager();

			
			
			
			if(playerclass.get(p.getUniqueId()) == 4 && p.getCooldown(Material.ARROW)<=0) {
					if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{
					player_damage.put(p.getName(), 3 + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.PIERCING)*0.5 + p.getLevel()/10);
					
					if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					{
						player_damage.put(p.getName(),player_damage.get(p.getName()) + p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
					}
					d.setDamage(d.getDamage()*(1+ssd.Remodeling.get(p.getUniqueId())*0.05)*(1+player_damage.get(p.getName())));
					}
			}
			}
		}
		
	}
}



