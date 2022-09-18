package io.github.chw3021.classes.forger;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.obtains.Obtained;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.google.common.collect.HashMultimap;
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
import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Forskills extends Pak implements Serializable {
	
	/**
	 * 
	 */

	
	private static transient final long serialVersionUID = 6779511048929362121L;
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();
	
	private HashMap<Player, Integer> mga = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> mgar = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> mgat = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> mgaovercount = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> mgaover = new HashMap<Player, Integer>();
	private HashMap<UUID, Integer> mgaovercountt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> mgaovert = new HashMap<UUID, Integer>();
	
	private HashMultimap<UUID, UUID> heshpair = HashMultimap.create();
	private HashMap<UUID, Integer> heshc = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> heshtask = new HashMap<UUID, Integer>();
	


	private HashMap<UUID, Integer> railsc = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> railsct = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> spctr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> spctrt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> bmwv = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> bmwvt = new HashMap<UUID, Integer>();
	

	private HashMap<UUID, Integer> compr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> comprt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> plzgrd = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> plzgrdt = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> prmig = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> prmigt = new HashMap<UUID, Integer>();
	
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private ForSkillsData fsd;

	
	private static final Forskills instance = new Forskills ();
	public static Forskills getInstance()
	{
		return instance;
	}


		
	public void er(PluginEnableEvent ev) 
	{
		ForSkillsData f = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
		fsd =f;
	}
	
	
	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Forskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				ForSkillsData f = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
				fsd =f;
			}
			
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ForSkillsData f = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
		fsd =f;
		
	}

	
	public void MachineGun(PlayerItemHeldEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 16 && (p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && p.isSneaking()) && !p.hasCooldown(Material.ARROW)){
			ev.setCancelled(true);
			p.setCooldown(Material.ARROW, 3);
				if(ev.getPreviousSlot()==0) {
					if(ev.getNewSlot()!=8) {
						mgat.put(p, 0);
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"화살 선택").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Arrow Selected").create());
		        		}
	                	return;
						
					}
					else {
						mgat.put(p, 1);
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"탄알 선택").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Bullet Selected").create());
		        		}
	                	return;
					}
				}
				else if(ev.getPreviousSlot()==8) {
					if(ev.getNewSlot()==0) {
						mgat.put(p, 0);
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"화살 선택").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Arrow Selected").create());
		        		}
	                	return;
						
					}
					else {
						mgat.put(p, 1);
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"탄알 선택").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Bullet Selected").create());
		        		}
	                	return;
					}
				}
				else {
					if(ev.getNewSlot()>ev.getPreviousSlot()) {
						mgat.put(p, 0);
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"화살 선택").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Arrow Selected").create());
		        		}
	                	return;
						
					}
					else {
						mgat.put(p, 1);
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"탄알 선택").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Bullet Selected").create());
		        		}
	                	return;
					}
				}
		}
	}

	
	public void MachineGun(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().hasMetadata("mgbul") && ev.getHitEntity() instanceof LivingEntity)
		{
			Projectile sn = ev.getEntity();
			Player p = (Player) sn.getShooter();
			LivingEntity le = (LivingEntity) ev.getHitEntity();
			
			p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
			atk1(0.0658*(1+fsd.MachineGun.get(p.getUniqueId())*0.064)*(1+(mgaovercount.getOrDefault(p, 1)/100d))* (mgaover.containsKey(p)?2:1), p, le,14);
			/*
        	le.damage(player_damage.get(p.getName())*0.0586*(1+fsd.MachineGun.get(p.getUniqueId())*0.05)*(1+(mgaovercount.getOrDefault(p, 1)/100))* (mgaover.containsKey(p)?2:1), p);
			*/
			
		}
	}

	
	public void MachineGun(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking())
		{
        
		
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 16 && fsd.MachineGun.getOrDefault(p.getUniqueId(), 0) >=1)
		{
				ev.setCancelled(true);
                if(mga.containsKey(p)) {
                	if(Proficiency.getpro(p)>=1) {
                		mgaovercount.computeIfPresent(p, (k,v) -> v+1);
                		mgaovercount.putIfAbsent(p, 0);
                		if(mgaovercountt.containsKey(p.getUniqueId())) {
                			Bukkit.getScheduler().cancelTask(mgaovercountt.get(p.getUniqueId()));
                		}
                		if(mgaovercount.get(p)>=100) {
                    		if(mgaovert.containsKey(p.getUniqueId())) {
                    			Bukkit.getScheduler().cancelTask(mgaovert.get(p.getUniqueId()));
                    		}
                			mgaovercount.put(p, 100);
                    		mgaover.putIfAbsent(p, 0);
                    		int task2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    			                @Override
    			                public void run() 
    			                {
		                			mgaover.remove(p);
    			                }
    	            	   }, 75);
    						mgaovert.put(p.getUniqueId(), task2);
                		}
						int task1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
	                			mgaovercount.remove(p);
			                }
						}, 75);
						mgaovercountt.put(p.getUniqueId(), task1);
                	}
                	if(mgaover.containsKey(p)) {
                    	mga.compute(p, (k,v) -> v-2);
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                    	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("기관총("+mga.get(p).toString()+"/200)" + ChatColor.RED+" [과열!]").create());
					    }
		        		else {
	                    	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("MachineGun("+mga.get(p).toString()+"/200)" + ChatColor.RED+" [OVERHEATED!]").create());
		        		}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.6f, 1.7f);
			    				p.playEffect(EntityEffect.HURT_BERRY_BUSH);
			                }
	            	   }, 2);
                	}
                	else {
                    	mga.compute(p, (k,v) -> --v);
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                    	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("기관총("+mga.get(p).toString()+"/200)").create());
					    }
		        		else {
	                    	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("MachineGun("+mga.get(p).toString()+"/200)").create());
		        		}
                	}
                	p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.76f, 1.6f);
    				p.playEffect(EntityEffect.HURT_BERRY_BUSH);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20,3,false,false));
					if(!mgat.containsKey(p) || mgat.get(p) == 0) {
	                	Arrow sn = (Arrow) p.launchProjectile(Arrow.class);
	                	ar1(sn, p, 0.0013*(1+fsd.MachineGun.get(p.getUniqueId())*0.0125)*(1+(mgaovercount.getOrDefault(p, 1)/100d) * (mgaover.containsKey(p)?2:1)));
	                	sn.setShooter(p);
	                	sn.setKnockbackStrength(0);
	                	sn.setPickupStatus(PickupStatus.DISALLOWED);
	                	sn.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(100));
						sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						sn.setMetadata("mg of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						sn.setInvulnerable(true);
						sn.setPierceLevel(3);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	sn.remove();
			                }
	            	   }, 5);
					}
					else if(mgat.get(p) == 1) {
	                	Snowball sn = (Snowball) p.launchProjectile(Snowball.class);
	                	sn.setItem(new ItemStack(Material.IRON_NUGGET));
	                	sn.setShooter(p);
	                	sn.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(90));
						sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						sn.setMetadata("mgbul", new FixedMetadataValue(RMain.getInstance(), true));
						sn.setInvulnerable(true);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	sn.remove();
			                }
	            	   }, 5);
					}
                	if(mga.get(p)<=0) {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("기관총 재장전중...").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("MachineGun Reloading...").create());
		        		}
                		mga.remove(p);
                		mgar.put(p, 1);
	                	p.playSound(p.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 0.76f, 1.6f);
	                	p.playSound(p.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 1f, 0f);
	                	p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_START, 1f, 0f);
    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    		                @Override
    		                public void run() 
    		                {
			                	p.playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1f, 2f);
			                	p.playSound(p.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_OFF, 1f, 0f);
			                	p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1f, 0f);
    		                	mgar.remove(p);
    			        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    				            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("재장전 완료!").create());
    						    }
    			        		else {
    			                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Reloaded!").create());
    			        		}
    			        		
    		                }
                	   }, 60-fsd.Development.get(p.getUniqueId())/2);
                	}
                }
                else if(!mga.containsKey(p)&&mgar.containsKey(p)){
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("기관총 재장전중...").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("MachineGun Reloading...").create());
	        		}
                }
                else if(!mga.containsKey(p)&&!mgar.containsKey(p)){
                	mga.put(p, 200);
                }
	        }
		}
	}


	
	public void Barrier(EntityDamageByEntityEvent d) 
	{
		
		if(d.getEntity() instanceof Player) 
		{
		Player p = (Player)d.getEntity();

			if(ClassData.pc.get(p.getUniqueId()) == 16 && Proficiency.getpro(p) >=2 && mgaovercount.containsKey(p)) {
				if(p.hasLineOfSight(d.getDamager())) {
					d.setDamage(d.getDamage()*0.35);
				}
			}
		}
	}

	
	public void LightningCannon(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 16 && fsd.LightningCannon.getOrDefault(p.getUniqueId(), 0) >=1) {
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
		Action ac = ev.getAction();
		double sec = 10*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{

				if(gdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (gdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("천둥포 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use LightningCannon").create());
	                	}
	                }
	                else // if timer is done
	                {
	                    gdcooldown.remove(p.getName()); // removing player from HashMap
	                    
	                	if(spctrt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(spctrt.get(p.getUniqueId()));
	                		spctrt.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=2) {
	    							spctr.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						spctr.remove(p.getUniqueId());
	    	                }
	    	            }, 40); 
	                	spctrt.put(p.getUniqueId(), task);
	                	
	                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);

	                    ArrayList<Location> line1 = new ArrayList<Location>();
	                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    for(double d = 0.1; d <= 45.1; d += 0.2) {
		                    Location pl = p.getEyeLocation().clone();
							pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
							line1.add(pl);
	                    }
	                    for(Location l : line1) {
	                    	p.getWorld().spawnParticle(Particle.FLASH, l, 1,0.1,0.1,0.1,0);
	                    }
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
			                    ArrayList<Location> line = new ArrayList<Location>();
			                    for(double d = 0.1; d <= 45.1; d += 0.2) {
				                    Location pl = p.getEyeLocation().clone();
									pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
									line.add(pl);
			                    }
			                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 2);
			                    for(Location l : line) {
			                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,5, 0.25,0.25,0.25,0, Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
			                    	p.getWorld().spawnParticle(Particle.SNOWBALL, l,5, 0.25,0.25,0.25,0);
			                    	p.getWorld().spawnParticle(Particle.WAX_ON, l,5, 0.25,0.25,0.25,1);

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
			                    }
											
			                }
						}, 10);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								for(LivingEntity le: les) {
									p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
									p.getWorld().spigot().strikeLightningEffect(le.getLocation(), true);
									atk1(0.9*(1+fsd.LightningCannon.get(p.getUniqueId())*0.06), p, le,9);
									le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,60,1,false,false));
									
								}	
			                }
						}, 10);
						gdcooldown.put(p.getName(), System.currentTimeMillis());
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
                    
                	if(spctrt.containsKey(p.getUniqueId())) {
                		Bukkit.getScheduler().cancelTask(spctrt.get(p.getUniqueId()));
                		spctrt.remove(p.getUniqueId());
                	}

    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						if(Proficiency.getpro(p)>=2) {
    							spctr.putIfAbsent(p.getUniqueId(), 0);
    						}
    	                }
    	            }, 3); 

            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() {
    						spctr.remove(p.getUniqueId());
    	                }
    	            }, 40); 
                	spctrt.put(p.getUniqueId(), task);
                	
                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);

                    ArrayList<Location> line1 = new ArrayList<Location>();
                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(double d = 0.1; d <= 45.1; d += 0.2) {
	                    Location pl = p.getEyeLocation().clone();
						pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
						line1.add(pl);
                    }
                    for(Location l : line1) {
                    	p.getWorld().spawnParticle(Particle.FLASH, l, 1,0.1,0.1,0.1,0);
                    }
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    for(double d = 0.1; d <= 45.1; d += 0.2) {
			                    Location pl = p.getEyeLocation().clone();
								pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
								line.add(pl);
		                    }
		                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 2);
		                    for(Location l : line) {
		                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,5, 0.25,0.25,0.25,0, Material.WHITE_GLAZED_TERRACOTTA.createBlockData());
		                    	p.getWorld().spawnParticle(Particle.SNOWBALL, l,5, 0.25,0.25,0.25,0);
		                    	p.getWorld().spawnParticle(Particle.WAX_ON, l,5, 0.25,0.25,0.25,1);

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
		                    }
										
		                }
					}, 10);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
							for(LivingEntity le: les) {
								p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
								p.getWorld().spigot().strikeLightningEffect(le.getLocation(), true);
								atk1(0.9*(1+fsd.LightningCannon.get(p.getUniqueId())*0.06), p, le,9);
								le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,60,1,false,false));
								
											
							}	
		                }
					}, 10);
					gdcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}



	
	public void Spectral(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
			Action ac = ev.getAction();
			
			if(ClassData.pc.get(p.getUniqueId()) == 16) {
				if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&& spctr.containsKey(p.getUniqueId()))
				{

	            	if(spctrt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(spctrt.get(p.getUniqueId()));
	            		spctrt.remove(p.getUniqueId());
	            	}
					spctr.remove(p.getUniqueId());
					


	            	if(bmwvt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(bmwvt.get(p.getUniqueId()));
	            		bmwvt.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								bmwv.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 3); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							bmwv.remove(p.getUniqueId());
		                }
		            }, 25); 
	            	bmwvt.put(p.getUniqueId(), task);

                    p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, 1, 2);
                    p.playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1, 2);

                    HashSet<Location> line = new HashSet<Location>();
                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(double d = 0.1; d <= 45.1; d += 0.2) {
	                    Location pl = p.getEyeLocation().clone();
						pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
						line.add(pl);
                    }
                    for(Location l : line) {
                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,5, 0.25,0.25,0.25,0, Material.PINK_GLAZED_TERRACOTTA.createBlockData());

                    	for (Entity a : p.getWorld().getNearbyEntities(l, 2, 2, 2))
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
								if(l.getBlock().isPassable() && le.hasAI()) {
									le.teleport(l);
								}
								Holding.holding(p, le, 15l);
							}
						}
                    }
					for(LivingEntity le: les) {
						atk1(0.5*(1+fsd.LightningCannon.get(p.getUniqueId())*0.05), p, le);
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
							enar.setDamage(player_damage.get(p.getName())*0.5*(1+fsd.LightningCannon.get(p.getUniqueId())*0.05));								
						}
						p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
						le.damage(0,p);
						le.damage(player_damage.get(p.getName())*0.5*(1+fsd.LightningCannon.get(p.getUniqueId())*0.05),p);
						*/
						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,60,1,false,false));
									
					}	
						
	            
				} 
            
			}
		}
	}
	
	final private ArrayList<Location> BeamWave(Location il, int a) {

		ArrayList<Location> line = new ArrayList<Location>();
        for(double an = Math.PI/6; an>-Math.PI/6; an-=Math.PI/180) {
        	Location al = il.clone().add(il.clone().getDirection().multiply(a));
        	al.add(al.getDirection().clone().normalize().multiply(3).rotateAroundY(an));
        	line.add(al);
        }
        line.forEach(l -> {
    		l.getWorld().spawnParticle(Particle.GLOW, l,1,0.1,0.1,0.1,0);
    		l.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,3,0.1,0.1,0.1,0, Material.PURPLE_GLAZED_TERRACOTTA.createBlockData());
        	
        });
        return line;
	}

	
	public void BeamWave(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
			Action ac = ev.getAction();
			
			if(ClassData.pc.get(p.getUniqueId()) == 16) {
				if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&&bmwv.containsKey(p.getUniqueId()))
				{
	            	if(bmwvt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(bmwvt.get(p.getUniqueId()));
	            		bmwvt.remove(p.getUniqueId());
	            	}
					bmwv.remove(p.getUniqueId());

                	
                    final Location pl = p.getEyeLocation().clone();
                    p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, 1, 2);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 2);

                    ArrayList<Location> line = new ArrayList<Location>();
                	AtomicInteger j = new AtomicInteger();
                	for(int i =0; i<45; i++) {
	                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
				                	BeamWave(pl,j.incrementAndGet()).forEach(bl -> line.add(bl));
				                    for(Location l : line) {

				                    	for (Entity a : l.getWorld().getNearbyEntities(l, 2, 3, 2))
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
				                    }
									for(LivingEntity le: les) {
										p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
										atk1(0.5*(1+fsd.LightningCannon.get(p.getUniqueId())*0.03), p, le);
									}
				                }
	                	   }, i); 
                	}
				} 
            
			}
		}
	}
	
	
	
	public void TNTLauncher(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 16&& fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)>=1) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking())
			{
				double sec = 10*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			
				ev.setCancelled(true);
					if(cdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		            {
		                double timer = (cdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		                if(!(timer < 0)) // if timer is still more then 0 or 0
		                {
		                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("TNT발사기 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
		                	}
		                	else {
		                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use TNTLauncher").create());
		                	}
		                	ev.setCancelled(true);
		                }
		                else // if timer is done
		                {
		                    cdcooldown.remove(p.getName()); // removing player from HashMap
		                    
		                	if(comprt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(comprt.get(p.getUniqueId()));
		                		comprt.remove(p.getUniqueId());
		                	}

		    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						if(Proficiency.getpro(p)>=1) {
		    							compr.putIfAbsent(p.getUniqueId(), 0);
		    						}
		    	                }
		    	            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    	                @Override
		    	                public void run() {
		    						compr.remove(p.getUniqueId());
		    	                }
		    	            }, 25); 
		                	comprt.put(p.getUniqueId(), task);
		                	
							ItemStack is = new ItemStack(Material.TNT);
	                    	for(int i =1; i<5; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
							            	p.playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1.0f, 2f);
							            	p.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0f, 0f);
											Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
											solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
											solid.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
											solid.setMetadata("tnt of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
											solid.setGlowing(true);
											solid.setPickupDelay(9999);
						                    solid.setVelocity(p.getLocation().getDirection().normalize().multiply(3));
						                }
			                	   }, i*2); 
	                    	}
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("tnt of"+p.getName())).forEach(t ->{
				                    	p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, t.getLocation(), 1,0.1,0.1,0.1,0);
						            	p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 0f);
										for(Entity e : t.getNearbyEntities(5, 5, 5)) {
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
														p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
														atk1(0.35*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.0312), p, le,14);
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
															enar.setDamage(player_damage.get(p.getName())*0.35*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.0312));								
														}
									                    le.damage(0,p);
									                    le.damage(player_damage.get(p.getName())*0.35*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.0312),p);
														*/
													}
											}
											
										}
										t.remove();
									});
				                }
	                    	}, 12); 
							cdcooldown.put(p.getName(), System.currentTimeMillis()); 
		                }
		            }
		            else // if cooldown doesn't have players name in it
		            {
	                    
	                	if(comprt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(comprt.get(p.getUniqueId()));
	                		comprt.remove(p.getUniqueId());
	                	}

	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						if(Proficiency.getpro(p)>=1) {
	    							compr.putIfAbsent(p.getUniqueId(), 0);
	    						}
	    	                }
	    	            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() {
	    						compr.remove(p.getUniqueId());
	    	                }
	    	            }, 25); 
	                	comprt.put(p.getUniqueId(), task);
	                	
						ItemStack is = new ItemStack(Material.TNT);
                    	for(int i =1; i<5; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
						            	p.playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1.0f, 2f);
						            	p.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0f, 0f);
										Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
										solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
										solid.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										solid.setMetadata("tnt of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										solid.setGlowing(true);
										solid.setPickupDelay(9999);
					                    solid.setVelocity(p.getLocation().getDirection().normalize().multiply(3));
					                }
		                	   }, i*2); 
                    	}
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("tnt of"+p.getName())).forEach(t ->{
			                    	t.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, t.getLocation(), 1,0.1,0.1,0.1,0);
					            	p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 0f);
									for(Entity e : t.getNearbyEntities(5, 5, 5)) {
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
													p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
													atk1(0.35*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.0312), p, le,14);
												}
										}
										
									}
									t.remove();
								});
			                }
                    	}, 12); 
		                cdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
				}
		}
	
	}

	
	
	public void Compressor(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking()&& compr.containsKey(p.getUniqueId()))
		{
		if(ClassData.pc.get(p.getUniqueId()) == 16) {
				ev.setCancelled(true);

                
            	if(comprt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(comprt.get(p.getUniqueId()));
            		comprt.remove(p.getUniqueId());
            	}
				compr.remove(p.getUniqueId());
				


            	if(plzgrdt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(plzgrdt.get(p.getUniqueId()));
            		plzgrdt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							plzgrd.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						plzgrd.remove(p.getUniqueId());
	                }
	            }, 25); 
            	plzgrdt.put(p.getUniqueId(), task);

            	
				final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 9).getLocation();
            	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 80, 100, false, false));
                p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2);
                p.playSound(p.getLocation(), Sound.BLOCK_METAL_HIT, 1, 0);
                p.playSound(p.getLocation(), Sound.BLOCK_DRIPSTONE_BLOCK_BREAK, 1, 0);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 0);

            	tl.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl,2000, 6,2,6,0, Material.LIGHT_GRAY_GLAZED_TERRACOTTA.createBlockData());
            	tl.getWorld().spawnParticle(Particle.WHITE_ASH, tl,2000, 6,1,6,0);
				for(Entity e : tl.getWorld().getNearbyEntities(tl,6, 6, 6)) {
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
						atk1(0.42*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.04), p, le);
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
							enar.setDamage(player_damage.get(p.getName())*0.42*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.04));								
						}
						p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
	                    le.damage(0,p);
	                    le.damage(player_damage.get(p.getName())*0.42*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.04),p);
	                    */
	                    Holding.holding(p, le, 10l);
						
					}
					
				}
            	for(int i =0; i<10; i++) {
                	Location knockback = p.getEyeLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(-0.32));
                	knockback.setDirection(tl.clone().toVector().subtract(knockback.clone().toVector()));
                	if(knockback.getBlock().isPassable()) {
                		p.teleport(knockback);
                	}
            	}
                
			}
		}
	
	}

	
	
	public void PlazmaGrenade(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking() && plzgrd.containsKey(p.getUniqueId()))
		{
		
		if(ClassData.pc.get(p.getUniqueId()) == 16) {
				ev.setCancelled(true);


            	if(plzgrdt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(plzgrdt.get(p.getUniqueId()));
            		plzgrdt.remove(p.getUniqueId());
            	}
				plzgrd.remove(p.getUniqueId());

            	
                p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2);
            	p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 0.5f);
            	p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 0.5f);
				

	            Snowball sn = p.launchProjectile(Snowball.class);
	            sn.setBounce(false);
	            sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				sn.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
	            sn.setMetadata("plzg", new FixedMetadataValue(RMain.getInstance(), true));
	            sn.setShooter(p);
	            sn.setItem(new ItemStack(Material.CYAN_GLAZED_TERRACOTTA));
	            sn.setGlowing(true);
	            sn.setVelocity(sn.getVelocity().multiply(2.5));
			}
		}
	
	}

	
	public void PlazmaGrenade(ProjectileHitEvent d) 
	{
		if(d.getEntity().hasMetadata("plzg")) {
			Player p = (Player) d.getEntity().getShooter();
			p.getWorld().spawnParticle(Particle.END_ROD, d.getEntity().getLocation(), 100, 5,5,14);
			p.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, d.getEntity().getLocation(), 100, 5,5,14);
			p.getWorld().spawnParticle(Particle.CRIT_MAGIC, d.getEntity().getLocation(), 200, 5,5,14);
            p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 2);
            p.playSound(p.getLocation(), Sound.BLOCK_LODESTONE_HIT, 1, 0);
            p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
            p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SENSOR_BREAK, 1, 2);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1, 0);
         	for (Entity e : d.getEntity().getLocation().getWorld().getNearbyEntities(d.getEntity().getLocation(), 6, 5, 6))
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
								/*
								if(le instanceof EnderDragon) {
									Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
										a.setShooter(p);
										a.setCritical(false);
										a.setSilent(true);
										a.setPickupStatus(PickupStatus.DISALLOWED);
										a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
									});
									enar.setDamage(player_damage.get(p.getName())*1.43*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.1));								
								}
			                    le.damage(0,p);
			                    le.damage(player_damage.get(p.getName())*1.43*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.1),p);
			                    */
								p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
								atk1(1.42*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.1), p, le,9);
								Holding.holding(p, le, 30l);
								
							}
					}
				}
		}
	}
	
	final private ArrayList<Location> RailSMG(Location il){
    	ArrayList<Location> line = new ArrayList<Location>();
        for(double d = 0.1; d <= 26.1; d += 0.2) {
            Location pl = il.clone();
			pl.add(il.getDirection().normalize().multiply(d));
			line.add(pl);
        }
        return line;
	}
	
	final private void RailSMG(Player p) {

    	if(railsct.containsKey(p.getUniqueId())) {
    		Bukkit.getScheduler().cancelTask(railsct.get(p.getUniqueId()));
    		railsct.remove(p.getUniqueId());
    	}

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
				if(Proficiency.getpro(p)>=2) {
					railsc.putIfAbsent(p.getUniqueId(), 0);
				}
            }
        }, 3); 

		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
				railsc.remove(p.getUniqueId());
            }
        }, 35); 
    	railsct.put(p.getUniqueId(), task);
    	
    	p.getLocation();
    	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
    	if(Proficiency.getpro(p)<1) {
            for(int i =1; i<10; i++) {
            	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 2f);
		                	p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 2f);
		                	ArrayList<Location> line = RailSMG(p.getEyeLocation().clone());
		                    line.forEach(l -> {
		                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),4, 0.005,0.005,0.005,0, Material.LIGHT_BLUE_GLAZED_TERRACOTTA.createBlockData());
		                    	p.getWorld().spawnParticle(Particle.GLOW, l.add(0, -0.289, 0),1, 0.005,0.005,0.005,0);

		                    	for (Entity a : p.getWorld().getNearbyEntities(l, 0.5, 0.5, 0.5))
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
		                }
            	   }, i); 
			}
            for(int i =1; i<10; i++) {
            	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	for(LivingEntity le : les) {
								p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
								atk1(0.073*(1+fsd.RailSMG.get(p.getUniqueId())*0.012), p, le,9);
								le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,15,1,false,false));
		                	}
		                	
		                }
            	   }, i+1/10); 
				}
    	}
    	else {
            for(int i =0; i<3; i++) {
            	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 2f);
		                	p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 2f);
		                	p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1f, 2f);
		                	ArrayList<Location> line = RailSMG(p.getEyeLocation().clone());
		                    line.forEach(l -> {
		                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.005,0.005,0.005,0, Material.LIGHT_BLUE_GLAZED_TERRACOTTA.createBlockData());
		                    	p.getWorld().spawnParticle(Particle.GLOW, l.add(0, -0.289, 0),1, 0.005,0.005,0.005,0);

		                    	for (Entity a : p.getWorld().getNearbyEntities(l, 0.5, 0.5, 0.5))
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
		                }
            	   }, i); 
			}
            for(int i =0; i<3; i++) {
            	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	for(LivingEntity le : les) {
								p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
								atk1(0.45*(1+fsd.RailSMG.get(p.getUniqueId())*0.05), p, le,9);
								le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,15,1,false,false));
		                	}
		                	
		                }
            	   }, i+1/10); 
				}
    	}

	}
	
	
	public void RailSMG(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 16 && fsd.RailSMG.getOrDefault(p.getUniqueId(), 0) >=1) {
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
		Action ac = ev.getAction();
		double sec =4*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
				

				if(frcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (frcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {

						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("전자기관단총 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use RailSMG").create());
						}
	                }
	                else // if timer is done
	                {
	                    frcooldown.remove(p.getName()); // removing player from HashMap
	                    
	                    RailSMG(p);
	                    
						frcooldown.put(p.getName(), System.currentTimeMillis());  
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

                    RailSMG(p);
                    
					frcooldown.put(p.getName(), System.currentTimeMillis());  
				}
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	
	public void RailScrew(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 16) {
		if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&&railsc.containsKey(p.getUniqueId()))
		{

        	if(railsct.containsKey(p.getUniqueId())) {
        		Bukkit.getScheduler().cancelTask(railsct.get(p.getUniqueId()));
        		railsct.remove(p.getUniqueId());
        	}
			railsc.remove(p.getUniqueId());


            Arrow firstarrow = p.launchProjectile(Arrow.class);
            firstarrow.setDamage(0);
            firstarrow.remove();
        	p.getLocation();
        	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                for(int i =1; i<5; i++) {
                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 2f);
			                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 2f);
			                	p.playSound(p.getLocation(), Sound.BLOCK_GRINDSTONE_USE, 1f, 2f);
			                	
			                	HashSet<Location> line = new HashSet<Location>();
			                	AtomicDouble j = new AtomicDouble(1.2);
			                    for(double d = 0; d <= 26; d += 0.5) {
				                    Location pl = p.getEyeLocation().clone();
									pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
									line.add(pl);
			                    }
			                    line.forEach(l ->{
			                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.clone().add(0, -0.35, 0),45, j.addAndGet(-0.023),j.get(),j.get(),0, Material.CYAN_GLAZED_TERRACOTTA.createBlockData());
			                    	p.getWorld().spawnParticle(Particle.GLOW, l.clone().add(0, -0.35, 0),3);

			                    	for (Entity a : p.getWorld().getNearbyEntities(l, 1, 1, 1))
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
											if(l.getBlock().isPassable() && le.hasAI()) {
												le.teleport(l);
											}
											Holding.holding(p, le, 3l);
										}
									}
			                    });
			                	les.forEach(le ->{
									p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
									atk1(0.32*(1+fsd.RailSMG.get(p.getUniqueId())*0.092), p, le);
									/*
									if(le instanceof EnderDragon) {
										Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
											ar.setShooter(p);
											ar.setCritical(false);
											ar.setSilent(true);
											ar.setPickupStatus(PickupStatus.DISALLOWED);
											ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
										});
										enar.setDamage(player_damage.get(p.getName())*0.42*(1+fsd.RailSMG.get(p.getUniqueId())*0.0912));								
									}
									p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
									le.damage(0,p);
									le.damage(player_damage.get(p.getName())*0.42*(1+fsd.RailSMG.get(p.getUniqueId())*0.0912),p);
									le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,15,1,false,false));
										*/
			                	});
			                }
                	   }, i*3); 
				}
    
			} 
            
		}}
	}
	
	
	public void Shockwave(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
		Player p = (Player)d.getDamager();
		double sec =7*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		d.getEntity();
        
		

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 16 && fsd.DoubleBarrel.getOrDefault(p.getUniqueId(),0)>=1) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && (p.isSneaking()) && !(p.hasCooldown(Material.YELLOW_TERRACOTTA)))
				{
				if(stcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            double timer = (stcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {

						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("충격파 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Shockwave").create());
						}

			        }
		            else // if timer is done
		            {
		                stcooldown.remove(p.getName()); // removing player from HashMap
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    ArrayList<Location> pie = new ArrayList<Location>();
	                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    for(double dou = -Math.PI/6; dou<= Math.PI/6; dou += Math.PI/180) {
		                    Location l = p.getLocation();
		                    l.setDirection(l.getDirection().normalize().rotateAroundY(dou));
		                    l.add(l.getDirection().normalize().multiply(5.1));
		                    line.add(l);
	                    	
	                    }
	                    for(double dou = 0.1; dou <= 5.1; dou += 1) {
	                    	  for (Location l : line){
			                    Location pl = p.getLocation();
								Vector ltr = l.toVector().subtract(pl.toVector());
								pl.add(ltr.normalize().multiply(dou));
								pie.add(pl);
								}
	                    }
						for(int i = 0; i<2; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
				                	public void run() 
					                {
			                    	  	for (Location l : line){
			                    	  		Item barrel = p.getWorld().dropItemNaturally(p.getEyeLocation(), new ItemStack(Material.STRUCTURE_VOID));
			                    	  		barrel.setVelocity(l.toVector().subtract(p.getEyeLocation().toVector()));
			                    	  		barrel.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			                    	  		barrel.setMetadata("barrel of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			                    	  		barrel.setGlowing(true);
			                    	  		barrel.setPickupDelay(9999);
			                    	  	}
										p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.8f, 0.2f);
										p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.8f, 1.4f);
										p.playSound(p.getLocation(), Sound.ENTITY_SQUID_HURT, 1.0f, 1.1f);
										p.playSound(p.getLocation(), Sound.ENTITY_SQUID_SQUIRT, 1.0f, 1.1f);
										pie.forEach(t -> {
											for(Entity e: t.getWorld().getNearbyEntities(t,1,1,1)) {
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
													les.add(le);
												}
												
											}
										});
						            }
				            }, i*5); 
							
						}
						for(int i = 0; i<2; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
				                	public void run() 
					                {
			             				for(LivingEntity le: les) {
				    						le.teleport(le.getLocation().add(p.getLocation().getDirection().normalize().multiply(5)));
				    						
											p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
											atk1(0.135, p, le);
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
													enar.setDamage(player_damage.get(p.getName())*0.135);								
												}
				             					p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
				             					le.damage(0, p);
				             					le.damage(player_damage.get(p.getName())*0.135, p);
				             					*/

				    							if(Proficiency.getpro(p)>=1) {
				    								Holding.holding(p, le, 20l);
				    							}
			             				}
						            }
				            }, i*5); 
							
						}
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
				                	public void run() 
					                {
		             					p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("barrel of"+p.getName())).forEach(t ->t.remove());
						            }
			             }, 13); 
			            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {
		        	ArrayList<Location> line = new ArrayList<Location>();
                    ArrayList<Location> pie = new ArrayList<Location>();
                	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                    for(double dou = -Math.PI/6; dou<= Math.PI/6; dou += Math.PI/180) {
	                    Location l = p.getLocation();
	                    l.setDirection(l.getDirection().normalize().rotateAroundY(dou));
	                    l.add(l.getDirection().normalize().multiply(5.1));
	                    line.add(l);
                    	
                    }
                    for(double dou = 0.1; dou <= 5.1; dou += 1) {
                    	  for (Location l : line){
		                    Location pl = p.getLocation();
							Vector ltr = l.toVector().subtract(pl.toVector());
							pl.add(ltr.normalize().multiply(dou));
							pie.add(pl);
							}
                    }
					for(int i = 0; i<2; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
			                	public void run() 
				                {
		                    	  	for (Location l : line){
		                    	  		Item barrel = p.getWorld().dropItemNaturally(p.getEyeLocation(), new ItemStack(Material.STRUCTURE_VOID));
		                    	  		barrel.setVelocity(l.toVector().subtract(p.getEyeLocation().toVector()));
		                    	  		barrel.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		                    	  		barrel.setMetadata("barrel of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		                    	  		barrel.setGlowing(true);
		                    	  		barrel.setPickupDelay(9999);
		                    	  	}
									p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.8f, 0.2f);
									p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.8f, 1.4f);
									p.playSound(p.getLocation(), Sound.ENTITY_SQUID_HURT, 1.0f, 1.1f);
									p.playSound(p.getLocation(), Sound.ENTITY_SQUID_SQUIRT, 1.0f, 1.1f);
									pie.forEach(t -> {
										for(Entity e: t.getWorld().getNearbyEntities(t,1,1,1)) {
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
												les.add(le);
											}
											
										}
									});
					            }
			            }, i*5); 
						
					}
					for(int i = 0; i<2; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
			                	public void run() 
				                {
		             				for(LivingEntity le: les) {
				    						le.teleport(le.getLocation().add(p.getLocation().getDirection().normalize().multiply(5)));

											p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
											atk1(0.135, p, le);

			    							if(Proficiency.getpro(p)>=1) {
			    								Holding.holding(p, le, 20l);
			    							}
		             				}
					            }
			            }, i*5); 
						
					}
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
			                	public void run() 
				                {
	             					p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("barrel of"+p.getName())).forEach(t ->t.remove());
					            }
		             }, 13); 
		            stcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
		}
	}
	

	
	@SuppressWarnings("deprecation")
	public void HoneyMissile(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 20*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

        
		if(ClassData.pc.get(p.getUniqueId()) == 16 && fsd.HoneyMissile.getOrDefault(p.getUniqueId(), 0) >=1 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && !p.hasCooldown(Material.HONEYCOMB)) {
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) &&(ac!= Action.RIGHT_CLICK_AIR)&&(ac!= Action.RIGHT_CLICK_AIR))
			{
				ev.setCancelled(true);
				if(smcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
		        {
		            double timer = (smcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
		            if(!(timer < 0)) // if timer is still more then 0 or 0
		            {

if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("점착미사일 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
}
else {
	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use HoneyMissile").create());
}
			        }
		            else // if timer is done
		            {
		                smcooldown.remove(p.getName()); // removing player from HashMapLocation dam = p.getLocation();

		            	if(prmigt.containsKey(p.getUniqueId())) {
		            		Bukkit.getScheduler().cancelTask(prmigt.get(p.getUniqueId()));
		            		prmigt.remove(p.getUniqueId());
		            	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=2) {
									prmig.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 

		        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								prmig.remove(p.getUniqueId());
			                }
			            }, 80); 
		            	prmigt.put(p.getUniqueId(), task);
		            	
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    for(double dou = -Math.PI/12; dou<= Math.PI/12; dou += Math.PI/60) {
		                    Location l = p.getEyeLocation().clone();
		                    l.setDirection(l.getDirection().rotateAroundY(dou));
		                    l.add(l.getDirection().normalize().multiply(25.1));
		                    line.add(l);
	                    }
						line.forEach(l -> {
							Firework hesh = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK);
	     					hesh.setShotAtAngle(true);
							hesh.setMetadata("hesh of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							hesh.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							hesh.setShooter(p);
		                	hesh.setVelocity(l.toVector().subtract(p.getLocation().toVector()).normalize().multiply(1));
							FireworkMeta fm = hesh.getFireworkMeta();
			            	
		        			FireworkEffect effect = FireworkEffect.builder()
		                                .with(Type.BURST)
		                                .withColor(Color.ORANGE)
		                                .flicker(true)
		                                .trail(true)
		                                .build();
							fm.setPower(3);
							fm.addEffect(effect);
							hesh.setFireworkMeta(fm);
						});
			            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else // if cooldown doesn't have players name in it
		        {

	            	if(prmigt.containsKey(p.getUniqueId())) {
	            		Bukkit.getScheduler().cancelTask(prmigt.get(p.getUniqueId()));
	            		prmigt.remove(p.getUniqueId());
	            	}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							if(Proficiency.getpro(p)>=2) {
								prmig.putIfAbsent(p.getUniqueId(), 0);
							}
		                }
		            }, 3); 

	        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							prmig.remove(p.getUniqueId());
		                }
		            }, 80); 
	            	prmigt.put(p.getUniqueId(), task);
	            	
                	
                    ArrayList<Location> line = new ArrayList<Location>();
                    for(double dou = -Math.PI/12; dou<= Math.PI/12; dou += Math.PI/60) {
	                    Location l = p.getEyeLocation().clone();
	                    l.setDirection(l.getDirection().rotateAroundY(dou));
	                    l.add(l.getDirection().normalize().multiply(25.1));
	                    line.add(l);
                    }
					line.forEach(l -> {
						Firework hesh = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK);
     					hesh.setShotAtAngle(true);
						hesh.setMetadata("hesh of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						hesh.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						hesh.setShooter(p);
	                	hesh.setVelocity(l.toVector().subtract(p.getLocation().toVector()).normalize().multiply(1));
						FireworkMeta fm = hesh.getFireworkMeta();
		            	
	        			FireworkEffect effect = FireworkEffect.builder()
	                                .with(Type.BURST)
	                                .withColor(Color.ORANGE)
	                                .flicker(true)
	                                .trail(true)
	                                .build();
						fm.setPower(3);
						fm.addEffect(effect);
						hesh.setFireworkMeta(fm);
					});
		            smcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
		}
	}


	
	public void HESH(EntityDamageByEntityEvent d) 
	{
        
		
		
		if(d.getDamager() instanceof Firework) 
		{
			Firework fw = (Firework) d.getDamager();
			if(fw.getShooter() instanceof Player) {
				Player p = (Player) fw.getShooter();
			    if (fw.hasMetadata("hesh of"+p.getName())) {
			        d.setCancelled(true);
			    }
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && d.getDamage()>=0) 
		{
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(heshpair.containsEntry(p.getUniqueId(), le.getUniqueId())) {
				p.getWorld().playSound(le.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1, 0);
				le.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, le.getLocation(), 1,0.1,0.1,0.1);
		    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 35, 2,2,2,3);
				dset0(d, p, 0.085*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.01),le,9);
				heshc.computeIfPresent(le.getUniqueId(), (k,v) -> v-1);
				if(heshc.containsKey(le.getUniqueId()) && heshc.get(le.getUniqueId())<=0) {
					p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					dset0(d, p, 0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.32)*(Proficiency.getpro(p)>=1 ? 2:1),le,9);
 					le.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, le.getLocation(), 1,0.1,0.1,0.1);
 			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
 			    	heshc.remove(le.getUniqueId());
 			    	heshpair.remove(p.getUniqueId(), le.getUniqueId());
 			    	Bukkit.getScheduler().cancelTask(heshtask.get(le.getUniqueId()));
 			    	heshtask.remove(le.getUniqueId());
				}
				if(heshc.containsKey(le.getUniqueId()) && d.getDamage()>=le.getHealth()) {
					p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					dset0(d, p, 0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.32)*(Proficiency.getpro(p)>=1 ? 2:1),le,9);
 					le.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, le.getLocation(), 1,0.1,0.1,0.1);
 			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
 			    	heshc.remove(le.getUniqueId());
 			    	heshpair.remove(p.getUniqueId(), le.getUniqueId());
 			    	Bukkit.getScheduler().cancelTask(heshtask.get(le.getUniqueId()));
 			    	heshtask.remove(le.getUniqueId());
					
				}
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity&& d.getDamage()>=0) 
		{
			Arrow arrow = (Arrow) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(arrow.getShooter() instanceof Player) {
				Player p = (Player) arrow.getShooter();
				if(heshpair.containsEntry(p.getUniqueId(), le.getUniqueId())) {
					p.getWorld().playSound(le.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1, 0);
					le.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, le.getLocation(), 1,0.1,0.1,0.1);
			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 35, 2,2,2,3);
					dset0(d, p, 0.085*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.01),le,9);
					heshc.computeIfPresent(le.getUniqueId(), (k,v) -> v-1);
					if(heshc.containsKey(le.getUniqueId()) && heshc.get(le.getUniqueId())<=0) {
						p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
						dset0(d, p, 0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.32)*(Proficiency.getpro(p)>=1 ? 2:1),le,9);
	 					le.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, le.getLocation(), 1,0.1,0.1,0.1);
	 			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
	 			    	heshc.remove(le.getUniqueId());
	 			    	heshpair.remove(p.getUniqueId(), le.getUniqueId());
	 			    	Bukkit.getScheduler().cancelTask(heshtask.get(le.getUniqueId()));
	 			    	heshtask.remove(le.getUniqueId());
					}
					if(heshc.containsKey(le.getUniqueId()) && d.getDamage()>=le.getHealth()) {
						p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
						dset0(d, p, 0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.32)*(Proficiency.getpro(p)>=1 ? 2:1),le,9);
	 					le.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, le.getLocation(), 1,0.1,0.1,0.1);
	 			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
	 			    	heshc.remove(le.getUniqueId());
	 			    	heshpair.remove(p.getUniqueId(), le.getUniqueId());
	 			    	Bukkit.getScheduler().cancelTask(heshtask.get(le.getUniqueId()));
	 			    	heshtask.remove(le.getUniqueId());
						
					}
				}
				
			}
		}
	}
	
	
	public void HESH(FireworkExplodeEvent f) 
	{
        
		
		
		if(f.getEntity().getShooter() instanceof Player) {
			Firework fw = f.getEntity();
			Player p = (Player) fw.getShooter();
		    if (fw.hasMetadata("hesh of"+p.getName())) {
            	
		    	fw.getWorld().spawnParticle(Particle.LANDING_HONEY, fw.getLocation(), 50, 2,2,2);
		    	for(Entity e : fw.getNearbyEntities(4.5, 4.5, 4.5)) {
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
	        		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && !heshc.containsKey(e.getUniqueId())&& !heshpair.containsEntry(p.getUniqueId(), e.getUniqueId())) 
					{
						LivingEntity le = (LivingEntity)e;
						heshpair.put(p.getUniqueId(), le.getUniqueId());
						heshc.put(le.getUniqueId(), 20);
	                    int tass = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
			                	public void run() 
				                {
		             				if(heshc.containsKey(le.getUniqueId())) {
		             					
		            					p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
		             					atk1(0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.042)*(Proficiency.getpro(p)>=1 ? 2:1), p, le,9);
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
											enar.setDamage(player_damage.get(p.getName())*0.653*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.042)*(Proficiency.getpro(p)));								
										}
		             					le.damage(0, p);
		             					le.damage(player_damage.get(p.getName())*0.653*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.042)*(Proficiency.getpro(p)), p);
		            					*/
		             					le.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, le.getLocation(), 1,0.1,0.1,0.1);
		             			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
		             			    	heshc.remove(le.getUniqueId());
		             				}
		             				heshpair.remove(p.getUniqueId(), le.getUniqueId());
					            }
		               	}, 400); 
	                    heshtask.put(le.getUniqueId(), tass);
						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,100,1,false,false));
						le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,100,1,false,false));
					}
		    		
		    	}
		    }
			
		}
	}
	

	
	@SuppressWarnings("deprecation")
	public void Detonator(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 16 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) &&(ac!= Action.RIGHT_CLICK_AIR)&&(ac!= Action.RIGHT_CLICK_AIR)&& prmig.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				if(!heshpair.containsKey(p.getUniqueId())) {
					return;
				}

            	if(prmigt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(prmigt.get(p.getUniqueId()));
            		prmigt.remove(p.getUniqueId());
            	}
				prmig.remove(p.getUniqueId());
				
				HashSet<UUID> leus = new HashSet<>();
				heshpair.get(p.getUniqueId()).forEach(leu -> {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
		                	public void run() 
			                {
		    					if(Bukkit.getEntity(leu) != null && !Bukkit.getEntity(leu).isDead()) {
		    						LivingEntity le = (LivingEntity) Bukkit.getEntity(leu);
		    						
	            					p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
	             					atk1(1.1*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.0789)*(Proficiency.getpro(p)), p, le,9);
		    						p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
		         					le.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, le.getLocation(), 1,0.1,0.1,0.1);
		         			    	le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 50, 2,2,2,3);
		    	 			    	leus.add(le.getUniqueId());
		    					}
				            }
	               	}, 5); 
				});
				for(UUID leu : leus) {
 			    	Bukkit.getScheduler().cancelTask(heshtask.get(leu));
 			    	heshc.remove(leu);
				}
				heshpair.removeAll(p.getUniqueId());
				

			}
		}
	}
	
	
	
	public void ULT(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
			if(ClassData.pc.get(p.getUniqueId()) == 16 && ((is.getType().name().contains("PICKAXE"))) && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				if(sultcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sultcooldown.get(p.getName())/1000d + 70/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("용의숨결발사기 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use DragonBreather").create());
	                	}

	                }
	                else // if timer is done
	                {
	                    sultcooldown.remove(p.getName()); // removing player from HashMap
		     	        HashSet<LivingEntity> les = new HashSet<>();
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1, 2);
						Holding.invur(p, 80l);
	                    Arrow firstarrow = p.launchProjectile(Arrow.class);
	                    firstarrow.setDamage(0);
	                    firstarrow.remove();
	 					for(int c=0;c<30;c++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
				                	public void run() 
					                {
			             			HashSet<Location> line = new HashSet<>();
		     	                	for(double point = 0.1; point<60.1; point+=0.5) {
		     	                		line.add(p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(point)));
		     	                	}
			    					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 2);
			    					
				                    line.forEach(l -> {
				                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,1,1)) {
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
		             						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) {
		             							LivingEntity le = (LivingEntity)e;
												les.add(le);
		             						}
		             					}
		             					l.getWorld().spawnParticle(Particle.DRAGON_BREATH, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0.1);
		             					l.getWorld().spawnParticle(Particle.END_ROD, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0);
		             					l.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0.1);
				                    });
						            }
			               	}, c*2+20); 
	 					}

	 					for(int c=0;c<30;c++) {
	 	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	 		             		@Override
	 		             			public void run() 
	 				                {

	 	             					for(LivingEntity le : les) {
	 	             						
	 		            					p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
	 		             					atk1(1.05, p, le);
	 	             						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 2, false, false));
	 		             					/*
	 											if(le instanceof EnderDragon) {
	 												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
	 													ar.setShooter(p);
	 													ar.setCritical(false);
	 													ar.setSilent(true);
	 													ar.setPickupStatus(PickupStatus.DISALLOWED);
	 													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
	 												});
	 												enar.setDamage(player_damage.get(p.getName()));								
	 											}
	 	             							p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
	 	             							le.damage(0, p);
	 	             							le.damage(player_damage.get(p.getName()), p);
	 	             							*/
	 	             					}
	 				                }
	 	                    	}, c*2+21); 
	 					}
		                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	     	        HashSet<LivingEntity> les = new HashSet<>();
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1, 2);
					Holding.invur(p,80l);
                    Arrow firstarrow = p.launchProjectile(Arrow.class);
                    firstarrow.setDamage(0);
                    firstarrow.remove();
 					for(int c=0;c<30;c++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
			                	public void run() 
				                {
		             			HashSet<Location> line = new HashSet<>();
	     	                	for(double point = 0.1; point<60.1; point+=0.5) {
	     	                		line.add(p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(point)));
	     	                	}
		    					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 2);
		    					
			                    line.forEach(l -> {
			                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,1,1)) {
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
	             						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) {
	             							LivingEntity le = (LivingEntity)e;
											les.add(le);
	             						}
	             					}
	             					l.getWorld().spawnParticle(Particle.DRAGON_BREATH, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0.1);
	             					l.getWorld().spawnParticle(Particle.END_ROD, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0);
	             					l.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0.1);
			                    });
					            }
		               	}, c*2+20); 
 					}

 					for(int c=0;c<30;c++) {
 	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
 		             		@Override
 		             			public void run() 
 				                {

 	             					for(LivingEntity le : les) {

 		            					p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
 		             					atk1(1.05, p, le);
 	             						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 2, false, false));
 	             					}
 				                }
 	                    	}, c*2+21); 
 					}
	                sultcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }
	

	
	final private HashSet<LivingEntity> ULT2(Player p) {

		ArrayList<Location> line1 = new ArrayList<Location>();
    	HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		
        AtomicInteger j = new AtomicInteger();
    	
        for(int d = 0; d <= 70; d += 1) {
            Location pl = p.getLocation().clone();
			pl.add(p.getLocation().getDirection().clone().normalize().multiply(d));
			line1.add(pl);
        }
        
        line1.forEach(l -> {
             Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
          		@Override
          			public void run() 
		                {
							p.playSound(l, Sound.BLOCK_CONDUIT_DEACTIVATE, 0.02f, 2);
          				p.getWorld().spawnParticle(Particle.FLASH, l, 15,1,2,1,0);
	                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 200,1,1,1, Material.POLISHED_BASALT.createBlockData());
	                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 250,1.5,1.5,1.5, Material.STRIPPED_WARPED_HYPHAE.createBlockData());
	                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 300,2,2,2, Material.CHISELED_QUARTZ_BLOCK.createBlockData());
	                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 300,0,2,0, Material.SOUL_LANTERN.createBlockData());
                    	for(Entity e: l.getWorld().getNearbyEntities(l,3,3,3)) {
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
     						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) {
     							LivingEntity le = (LivingEntity)e;
								les.add(le);
								Holding.holding(p, le, 70l);
     						}
     					}
          				
		                }
             	}, j.incrementAndGet()/2); 
        });
        return les;
	}

	
	public void ULT2(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
        
			if(ClassData.pc.get(p.getUniqueId()) == 16 && ((is.getType().name().contains("PICKAXE"))) && !p.isSneaking()&& p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				p.setCooldown(Material.HONEYCOMB, 1);
				ev.setCancelled(true);
				if(sult2cooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sult2cooldown.get(p.getName())/1000d + 50*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
	                	if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("영혼절단기 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
	                	}
	                	else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use SoulDivider").create());
	                	}
	                }
	                else // if timer is done
	                {
	                    sult2cooldown.remove(p.getName()); // removing player from HashMap

						p.getWorld().playSound(p.getLocation(), Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.5f, 2);
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SOUL_SOIL_BREAK, 0.5f, 2);
						
						Holding.invur(p,50l);
	                	HashSet<LivingEntity> les = ULT2(p);
 	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
 		             		@Override
 		             			public void run() 
 				                {

 	             					les.forEach(le -> {

 		            					p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
 		             					atk1(26.5, p, le);
 	             						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 2, false, false));

 	             							le.getWorld().playSound(le.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1, 2);
 	             							le.getWorld().playSound(le.getLocation(), Sound.BLOCK_CONDUIT_DEACTIVATE, 1, 2);
 	        	       	                    for(int d = 0; d <= 10; d += 1) {
 	        	       	                    	le.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, le.getLocation().clone().add(0, d, 0), 100,1.2,1.2,1.2,0.05);
	        	       	                    	le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation().clone().add(0, d, 0), 100,1.2,1.2,1.2,0.05, Material.BLACK_GLAZED_TERRACOTTA.createBlockData());
 	        	       	                    }
 	             					});
 	   	                    
 				                }
 	                    	}, 45); 
		                sult2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {

	            	p.getWorld().playSound(p.getLocation(), Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.5f, 2);
					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SOUL_SOIL_BREAK, 0.5f, 2);
					
					Holding.invur(p,50l);

                	HashSet<LivingEntity> les = ULT2(p);
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
		             			public void run() 
				                {

	             					les.forEach(le -> {

 		            					p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
 		             					atk1(26.5, p, le);
 	             						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 2, false, false));

	             							le.getWorld().playSound(le.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1, 2);
	             							le.getWorld().playSound(le.getLocation(), Sound.BLOCK_CONDUIT_DEACTIVATE, 1, 2);
	        	       	                    for(int d = 0; d <= 10; d += 1) {
	        	       	                    	le.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, le.getLocation().clone().add(0, d, 0), 100,1.2,1.2,1.2,0.05);
        	       	                    	le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation().clone().add(0, d, 0), 100,1.2,1.2,1.2,0.05, Material.BLACK_GLAZED_TERRACOTTA.createBlockData());
	        	       	                    }
	             					});
	   	                    
				                }
	                    	}, 45); 
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
		
			if(ClassData.pc.get(p.getUniqueId()) == 16 && ((is.getType().name().contains("PICKAXE"))) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") )
		{
	        
	
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 16)
			{
					e.setCancelled(true);
			}
		}
		
	}
/*
	
	public void Damagegetter(ProjectileLaunchEvent e) 
	{
		
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
		    
			
			
			

			if(ClassData.pc.get(p.getUniqueId()) == 16) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
				{
						player_damage.put(p.getName(), p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
						
						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				else {
					player_damage.put(p.getName(), 0d);
				}
			}
		}
	}
	*/

	
	public void ArmorDecrease(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity() instanceof Player && d.getDamager() instanceof LivingEntity) 
		{
			if(ClassData.pc.get(d.getEntity().getUniqueId()) == 16) {
				d.setDamage(d.getDamage()*1.6);
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof Player) 
		{
			Projectile ar = (Projectile)d.getDamager();
	
			if(ar.getShooter() instanceof LivingEntity) {
				if(ClassData.pc.get(d.getEntity().getUniqueId()) == 16) {
					d.setDamage(d.getDamage()*1.6);
				}
			}
		}
	}
	
	
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity) d.getEntity();
        
		

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 16) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
			{
				if(Proficiency.getpro(p)>=1) {
					dset2(d, p, (1+fsd.Development.get(p.getUniqueId())*0.057),le, 16);
				}
				else {
					dset2(d, p, (1+fsd.Development.get(p.getUniqueId())*0.057),le, 0);
				}
				if(le.hasMetadata("leader") || le.hasMetadata("boss")) {
					d.setDamage(d.getDamage()*2.5);
				}
			}
		}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
			Projectile ar = (Projectile)d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
	
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
			    
				
				
				

				if(ClassData.pc.get(p.getUniqueId()) == 16) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
					{
						if(Proficiency.getpro(p)>=1) {
							dset2(d, p, (1+fsd.Development.get(p.getUniqueId())*0.057),le, 16);
						}
						else {
							dset2(d, p, (1+fsd.Development.get(p.getUniqueId())*0.057),le, 14);
						}
						if(le.hasMetadata("leader") || le.hasMetadata("boss")) {
							d.setDamage(d.getDamage()*2.5);
						}
							/*player_damage.put(p.getName(), p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
							
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
							}*/
					}
					/*else {
						player_damage.put(p.getName(), 0d);
					}*/
				}
			}
		}
	}
	
}



