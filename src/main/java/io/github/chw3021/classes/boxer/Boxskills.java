package io.github.chw3021.classes.boxer;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.event.player.*;
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
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
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
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Boxskills extends Pak implements Listener, Serializable {
	

	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 8481741046360618590L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> jbcooldown = new HashMap<String, Long>();
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

	private HashMap<UUID, Integer> jr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> jrt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> op = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> opt = new HashMap<UUID, Integer>();
	
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
	
	final private Boolean condition(Player p) {
		return p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData();
	}
	
	
	public void Adrenaline(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 7)
		{
			if(condition(p) && fistforce.containsKey(p.getUniqueId()))
			{
				double sec = 5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				
				if(!(p.isSneaking())&& bsd.Parrying.get(p.getUniqueId())>=1)
				{
					ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("권기방출")
						.ename("FistForce")
						.slot(0)
						.hm(sdcooldown)
						.skillUse(() -> {
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
											atk2(fistforce.get(p.getUniqueId())*1.5,0d, p, le);
										}
				                	}
				                    fistforce.remove(p.getUniqueId());
								}
				            }, 20); 
						});
				bd.execute();
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
								p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 40, 1, false, false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 1, false, false));
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
			if(condition(p))
			{
		    
			Action ac = ev.getAction();
			double sec = 4*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	
			
			
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("철산고")
						.ename("BodyBlow")
						.slot(1)
						.hm(swcooldown)
						.skillUse(() -> {
						
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
						p.setCooldown(Material.TORCH, 3); p.swingMainHand();
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
						});
				bd.execute();
				
			}
		}
		}
	}
	

	
	public void UnderHook(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7) {
			if(condition(p) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)  && p.isSneaking() &&uh.containsKey(p.getUniqueId()))
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

				
				final Location tl = gettargetblock(p,2);

                ArrayList<Location> line = new ArrayList<Location>();
                AtomicInteger j = new AtomicInteger();
            	p.setCooldown(Material.BONE, 3);
				p.swingMainHand();
                for(double an = Math.PI/3; an>-Math.PI/3; an-=Math.PI/90) {
                	Location pl = p.getEyeLocation().clone().add(0, -0.68, 0);
                	pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(2.26));
                	line.add(pl);
                }
                line.forEach(l -> {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
			        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,1,0.1,0.1,0.1,0);
							p.getWorld().spawnParticle(Particle.CLOUD, l, 1);
			        		p.getWorld().spawnParticle(Particle.SCRAPE, l,2,0.1,0.1,0.1,0.1);
		                }
					}, j.incrementAndGet()/600); 
                	
                });

                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 0.5f);
                p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.3f, 1.5f);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
				p.playSound(p.getLocation(), Sound.BLOCK_METAL_HIT, 1f, 0f);
				
				p.getWorld().spawnParticle(Particle.CRIT, tl, 100, 1, 1, 1);
				p.getWorld().spawnParticle(Particle.CLOUD, tl, 30, 1, 1, 1);
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
						atk1(0.5*(1+ bsd.BodyBlow.get(p.getUniqueId())*0.035), p, le);
	                    le.teleport(tl);
						Holding.superholding(p, le, 10l);
	                    p.setCooldown(Material.TORCH, 3); p.swingMainHand();
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
			if(condition(p) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&& p.isSneaking() &&fs.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
	        	if(fst.containsKey(p.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(fst.get(p.getUniqueId()));
	        		fst.remove(p.getUniqueId());
	        	}
				fs.remove(p.getUniqueId());
				
	

                p.setCooldown(Material.TORCH, 3); p.swingMainHand();
	
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
			if(condition(p))
			{
			double sec = 7*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	
			
		
				ev.setCancelled(true);
				final Location pfl = p.getLocation().clone();
				
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("뎀프시롤")
						.ename("DempsyRoll")
						.slot(2)
						.hm(cdcooldown)
						.skillUse(() -> {
						

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
							        		Location l = gettargetblock(p, 3);
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
						                    p.setCooldown(Material.TORCH, 3); p.swingOffHand();
							        		p.teleport(w2);

							        		Location l = gettargetblock(p, 3);
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
				                    p.setCooldown(Material.TORCH, 3); p.swingMainHand();
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
										}
									}
				                }
	                    	}, 17); 
						});
				bd.execute();
					
				}
		}
	
	}
	
	

	
	public void DempseyRoll1(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") &&dr1.containsKey(p.getUniqueId()) && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.isSneaking())
			{
				ev.setCancelled(true);
				final Location pfl = dr1.get(p.getUniqueId());
				
            	if(dr1t.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(dr1t.get(p.getUniqueId()));
            		dr1t.remove(p.getUniqueId());
            	}
				dr1.remove(p.getUniqueId());

            	
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
			                    p.setCooldown(Material.TORCH, 3); p.swingOffHand();
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
	                }
        	   }, 13); 
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0.0f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 2.0f);
		        		p.teleport(pfl);
	                    p.setCooldown(Material.TORCH, 3); p.swingMainHand();
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
			if(condition(p) && p.isSneaking() &&dr2.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				
            	if(dr2t.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(dr2t.get(p.getUniqueId()));
            		dr2t.remove(p.getUniqueId());
            	}
				final Location pfl = dr2.remove(p.getUniqueId()).clone();
        		p.teleport(pfl);
        		Holding.holding(p, p, 25L);

				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 25,20,false,false));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 25,20,false,false));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 25,999999,false,false));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 25,20,false,false));
				p.setCooldown(Material.TORCH, 3); p.swingMainHand();

				ArrayList<Location> line = new ArrayList<Location>();
                AtomicInteger j = new AtomicInteger(0);
                for(double d = 0.1; d <= 2.5; d += 0.1) {
                    Location pl = pfl.clone().add(0, 0.32, 0);
					pl.add(p.getEyeLocation().clone().getDirection().normalize().multiply(d));
					line.add(pl);
                }

                for(int i=0; i<2; i++ ) {
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                 		@Override
                            	public void run() 
            	                {	
    	                    p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_HEARTBEAT, 0.8f, 1.1f);
            		            }
                        	   }, i*9); 
                }
                
                
            	line.forEach(i -> {
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
	                	public void run() 
		                {	
 					p.getWorld().spawnParticle(Particle.WHITE_ASH,i, 30,1,1,1);
 					p.getWorld().spawnParticle(Particle.FLASH,i, 2,1,1,1);
 					
	                    	for (Entity e : p.getNearbyEntities(3.5, 3.5, 3.5))
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
										le.teleport(i);
								}
							}
			            }
                	   }, j.incrementAndGet()); 
				}); 
            	
            	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
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
	    	            }, 15); 
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 0.7f, 0.0f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.8f, 0f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 0.8f, 0f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.4f, 2f);
	                    p.setCooldown(Material.TORCH, 3); 
	                    p.swingMainHand();
	                    p.swingOffHand();
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
			            }, 15); 
		        		Location l = line.get(line.size()-1);
		        		p.getWorld().spawnParticle(Particle.CRIT, l, 200,1,1,1);
		        		p.getWorld().spawnParticle(Particle.CRIT_MAGIC, l, 400,1,1,1);
		        		p.getWorld().spawnParticle(Particle.SONIC_BOOM, l, 50,2,2,2);
						p.setCooldown(Material.TORCH, 3); p.swingMainHand();
		        		
		        		for (Entity e : l.getWorld().getNearbyEntities(l, 3, 3, 3))
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
								atk1(1.5*(1+bsd.DempseyRoll.get(p.getUniqueId())*0.09), p, le);		
							}
						}
	                }
        	   }, j.incrementAndGet()+3); 
							
			}
		
		}
	}
	
	
	public void Straight(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 7&& bsd.Straight.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(condition(p))
			{
			Action ac = ev.getAction();
			double sec = 3*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	
		    
			
			
			if(!(p.isSneaking()) && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) && !p.hasCooldown(Material.TORCH))
			{
					
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("붕권")
						.ename("Straight")
						.slot(3)
						.hm(frcooldown)
						.skillUse(() -> {
						ArrayList<Location> line = new ArrayList<Location>();
	                    p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_STEP, 0.6f, 2f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_STEP, 0.6f, 0f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, 0.7f, 0f);
	                    p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 0.7f, 2f);
	                    
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
			            }, 30); 
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
									}
								}
				        		p.getWorld().spawnParticle(Particle.CRIT, p.getEyeLocation(), 50,1,1,1);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2.0f);
			                }
	                    }, j.incrementAndGet()/17); 
						});
				bd.execute();

	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	

	
	public void SkyCrasher(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7 && !p.hasCooldown(Material.TORCH)) {
			if(condition(p)&& (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)  && !p.isSneaking() &&sc.containsKey(p.getUniqueId()))
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
	            }, 30); 
            	eqt.put(p.getUniqueId(), task);

                HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                AtomicInteger j = new AtomicInteger();

				for(int i = 0; i <10; i++) {
					p.teleport(p.getLocation().clone().add(0, 0.2, 0));
					if(!p.getEyeLocation().getBlock().isPassable()) {
						break;
					}
				}
                p.playSound(p.getLocation(), Sound.ENTITY_RABBIT_JUMP, 1.0f, 2f);
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 0.25f, 2f);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, 1.0f, 1.6f);
                ArrayList<Location> line = new ArrayList<Location>();
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

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7 && !p.hasCooldown(Material.TORCH)) {
			if(condition(p)&& (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)  && !p.isSneaking() &&eq.containsKey(p.getUniqueId()))
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
				

				
				final Location tl = gettargetblock(p,2);
				
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 0.1f);
				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 0f);
				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.6f, 0.5f);
				for(int i = 0; i <10; i++) {
					p.teleport(p.getLocation().clone().add(0, -0.1, 0));
					p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 50, 0.5,2,0.5,0.05);
					if(!p.getLocation().getBlock().isPassable()) {
						break;
					}
				}
				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 500, 3,0.5,3,0 ,Material.GRASS_BLOCK.createBlockData());
				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 500, 3,0.5,3,0,Material.IRON_BLOCK.createBlockData());
				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 500, 3,0.5,3,0,Material.BASALT.createBlockData());
				p.getWorld().spawnParticle(Particle.CRIT, tl, 500, 0.5,3,0.5,0.05);
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
     					
					}
				}
							
			}
		
		}
	}
	
	
	
	public void FlickerJab(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 7&& bsd.Straight.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(condition(p))
			{
			Action ac = ev.getAction();
			double sec = 3*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
	
		    
			
			
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
					
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("질풍권")
						.ename("FlickerJab")
						.slot(4)
						.hm(jbcooldown)
						.skillUse(() -> {
						
	                	if(jrt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(jrt.get(p.getUniqueId()));
	                		jrt.remove(p.getUniqueId());
	                	}
	
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									jr.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 
	
	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								jr.remove(p.getUniqueId());
			                }
			            }, 30); 
	                	jrt.put(p.getUniqueId(), task);
	                	
	                	
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    HashSet<LivingEntity> les = new HashSet<>();
	    				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1,1,false,false));
	                    
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, 0.7f, 2f);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 2f);
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
	                	
	                	p.setCooldown(Material.TORCH, 3); p.swingOffHand();
	                	
	                    for(double d = 0.1; d <= 2; d += 0.2) {
		                    Location pl = p.getEyeLocation().clone().add(0, -0.2, 0);
							pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
							line.add(pl);
	                    }
	                	line.forEach(i -> {
	     					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK,i, 2,1,1,1);
	     					p.getWorld().spawnParticle(Particle.CRIT_MAGIC,i, 20,0.1,0.1,0.1,0);
	     					
	                    	for (Entity e : p.getNearbyEntities(1.5, 1.5, 1.5))
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
										les.add(le);
										Holding.holding(p, le, 5l);
								}
							}
						}); 
	                	
	                	les.forEach(le ->
						{
							atk1(0.4*(1+bsd.Straight.get(p.getUniqueId())*0.0431), p, le);
		                    
						});
	                	
						});
				bd.execute();
	
	            
			} // adding players name + current system time in miliseconds
	        
		}}
	}


	public void JabRush(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();
	
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7) {
			if(condition(p)
					&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&& !p.isSneaking() &&jr.containsKey(p.getUniqueId()))
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
				
				
	        	if(jrt.containsKey(p.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(jrt.get(p.getUniqueId()));
	        		jrt.remove(p.getUniqueId());
	        	}
				jr.remove(p.getUniqueId());
				
	
	        	if(opt.containsKey(p.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(opt.get(p.getUniqueId()));
	        		opt.remove(p.getUniqueId());
	        	}
	
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							op.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 3); 
	
	    		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						op.remove(p.getUniqueId());
	                }
	            }, 30); 
	        	opt.put(p.getUniqueId(), task);

            	

            	for(int i =0; i<4; i++) {
             	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
        	                	Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation().clone().add(0, -0.2, 0), p.getEyeLocation().clone().getDirection().normalize().multiply(2.5), 30, 5);
        	                	ar.remove();
        	                	
        	                	Item it = p.getWorld().dropItem(p.getEyeLocation().clone().add(0, -0.2, 0), p.getEquipment().getItemInMainHand());
        	                	it.setVelocity(ar.getVelocity());
        	                	it.setThrower(p.getUniqueId());
        	                	it.setOwner(p.getUniqueId());
        	                	it.setPickupDelay(999999);
        	        			it.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
        	        			it.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
        	    				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1,1,false,false));
        	        			
        	        			
        	    				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
        	    	                @Override
        	    	                public void run() 
        	    	                {
        	    	                	it.remove();
        	    	                }
        	    				}, 3);
        	    				
			                    ArrayList<Location> line = new ArrayList<Location>();
			                    HashSet<LivingEntity> les = new HashSet<>();
			                    
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, 0.7f, 2f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 2f);
			                	
			                	p.setCooldown(Material.TORCH, 3); p.swingOffHand();
			                	
			                    for(double d = 0.1; d <= 2; d += 0.2) {
			                        Location pl = p.getEyeLocation().clone().add(0, -0.2, 0);
			    					pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
			    					line.add(pl);
			                    }
			                	line.forEach(i -> {
			     					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK,i, 2,1,1,1);
			     					p.getWorld().spawnParticle(Particle.CRIT_MAGIC,i, 3,0.3,0.3,0.3,0.01);
			     					
			                    	for (Entity e : p.getNearbyEntities(1.5, 1.5, 1.5))
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
			    								les.add(le);
			    								Holding.holding(p, le, 5l);
			    						}
			    					}
			    				}); 
			                	
			                	les.forEach(le ->
			    				{
			    					atk1(0.33*(1+bsd.Jab.get(p.getUniqueId())*0.033), p, le);
			                        
			    				});
			                	
			                }
             	   },i*2);
            	}
							
			}
		
		}
	}


	public void oneInchPunch(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();
	
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7) {
			if(condition(p)
					&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&& !p.isSneaking() &&op.containsKey(p.getUniqueId()))
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
				
				
	        	if(opt.containsKey(p.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(opt.get(p.getUniqueId()));
	        		opt.remove(p.getUniqueId());
	        	}
				op.remove(p.getUniqueId());
				
	
				
				final Location tl = gettargetblock(p,2);
				
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0.1f);
				p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_DIAMOND, 0.25f, 0f);
				p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, 0.5f, 2f);
				p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 0.5f, 2f);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3,20,false,false));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 3,100,false,false));
				p.setCooldown(Material.TORCH, 3); p.swingOffHand();
				
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	    				p.setCooldown(Material.TORCH, 3); p.swingOffHand();
	    				p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.7f, 1.6f);
	    				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.5f, 0.1f);
	    				
	            		final Location pl = p.getLocation().clone();
	            		final World w = pl.getWorld();
	                    ArrayList<Location> cir = new ArrayList<Location>();
	                	for(double angley= -Math.PI/4; angley<Math.PI/4; angley += Math.PI/45) {
	                        for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
	                        	Location one =pl.clone();
	                        	one.add(one.getDirection().clone().rotateAroundY(angley).rotateAroundAxis(pl.clone().getDirection(),angle).normalize().multiply(angle*0.6));
	                        	cir.add(one);
	                        }
	                	}	
	                	cir.forEach(l -> {
	            			w.spawnParticle(Particle.CLOUD, l,1,0.02,0.02,0.02,0.3);
	            			w.spawnParticle(Particle.CRIT_MAGIC, l,5,0.02,0.02,0.02,0.1);
	            	    });
	    				
	    				for(Entity e : p.getWorld().getNearbyEntities(tl,2.5, 2.5, 2.5)) {
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
	    	
	    						atk1(0.91*(1+bsd.Straight.get(p.getUniqueId())*0.08), p, le);
	    						Holding.superholding(p, le, 10l);
	    	 					
	    					}
	    				}
	                }
				}, 3);

							
			}
		
		}
	}


	public void Training(EntityDamageByEntityEvent d) 
	{
	    
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		if(condition(p))
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
				if(condition(p))
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
	
	
	final private void ult(Player p) {
		final Location pl = p.getEyeLocation().clone().add(0, -0.3, 0);
		Location rl = pl.clone().add(pl.clone().getDirection().rotateAroundY(-Math.PI/2).normalize().multiply(2.5));
		Location ll = pl.clone().add(pl.clone().getDirection().rotateAroundY(Math.PI/2).normalize().multiply(2.5));
		
		Vector rv = pl.clone().getDirection().rotateAroundY(-Math.PI/2).normalize();
		Vector lv = pl.clone().getDirection().rotateAroundY(Math.PI/2).normalize();
		
		rl.setDirection(pl.clone().getDirection());
		ll.setDirection(pl.clone().getDirection());

        HashSet<Location> line = new HashSet<Location>();
        for(int d = 0; d <= 11; d++) {
        	Location ri = rl.clone().add(rl.clone().getDirection().normalize().multiply(d));
        	Location li = ll.clone().add(ll.clone().getDirection().normalize().multiply(d));
        	line.add(ri);
        	line.add(li);
            for(int d1 = 0; d1 <= d; d1++) {
            	line.add(ri.clone().add(rv.clone().normalize().multiply(d1)));
            	line.add(li.clone().add(lv.clone().normalize().multiply(d1)));
            }
        }
        final World w = pl.getWorld();
        line.forEach(l ->{
        	w.spawnParticle(Particle.CLOUD, l, 50,1,1,1,0);
        });
        
        

        HashSet<Location> cir = new HashSet<Location>();
        Vector plv = pl.getDirection().clone();
        for(double angle=0.1; angle<Math.PI*3; angle += Math.PI/90) {
        	Location one = pl.clone();
        	one.add(plv.clone().rotateAroundY(Math.PI/2).rotateAroundAxis(plv.clone(),angle).normalize().multiply(2).add(plv.clone().normalize().multiply(angle)));
        	cir.add(one);
        }
    	cir.forEach(l -> {
			w.spawnParticle(Particle.SWEEP_ATTACK, l,2,0.1,0.1,0.1,0);
        	w.spawnParticle(Particle.CLOUD, l, 10,0.2,0.2,0.2,0);
			w.spawnParticle(Particle.CRIT, l,2,0.1,0.1,0.1,0);
	    });
	}



	public void ULT(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}

		ItemStack is = p.getInventory().getItemInMainHand();



		if(ClassData.pc.get(p.getUniqueId()) == 7 && (is.getType().name().contains("BANNER_PATTERN"))&& (is.hasItemMeta()) && is.getItemMeta().hasCustomModelData()  && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(60/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("일격권")
						.ename("ONE PUNCH")
						.slot(6)
						.hm(sultcooldown)
						.skillUse(() -> {
						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 35,0,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 35,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 35,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 35,20,false,false));
						p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
	                    p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_HEARTBEAT, 0.8f, 0.2f);
						p.playSound(p.getLocation(),Sound.ENTITY_WARDEN_SONIC_CHARGE, 0.2f,0.8f);
						p.playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,1.8f);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			    				p.setCooldown(Material.TORCH, 3); p.swingMainHand();
			    		    	final Location l = gettargetblock(p,6);
			    				p.getWorld().spawnParticle(Particle.FLASH, l, 800, 10, 10, 10);
								p.getWorld().spawnParticle(Particle.CRIT, l, 600, 10, 10, 10);
								p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 600, 10, 10, 10);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
								p.playSound(l, Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 0.3f);
								p.playSound(l, Sound.ENTITY_WARDEN_SONIC_BOOM, 0.6f, 0.6f);
								p.playSound(l, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.8f, 2f);
								ult(p);
			                	for(Entity e : p.getWorld().getNearbyEntities(l, 16,14,16)) {

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

												atk1(17.1, p, le);
													
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
						});
				bd.execute();
				
			}
    }
	
	

	final private List<Location> ult2(Location tl, Vector tv) {
        List<Location> circle = new ArrayList<>();
        AtomicBoolean ab = new AtomicBoolean();
        
        for(double an =0; an<=Math.PI*2; an+=Math.PI/20) {
        	Location il =  tl.clone().add(tv.clone().normalize().rotateAroundY(an*(ab.getAndSet(!ab.get()) ? 1:-1)).multiply(6));
        	il.setDirection(tl.clone().toVector().subtract(il.clone().toVector()));
        	circle.add(il);
        }
        return circle;
	}

	final private void storm(Location il) {

        ArrayList<Location> a1 = new ArrayList<Location>();
        ArrayList<Location> a3 = new ArrayList<Location>();

    	for(double angley= 0; angley<Math.PI*6; angley += Math.PI/45) {
        	a1.add(il.clone().add(il.clone().getDirection().normalize().rotateAroundY(angley).multiply(1.5)).add(0, angley*0.5, 0));
    	}
    	for(double angley= 0; angley<Math.PI*6; angley += Math.PI/45) {
    		a1.add(il.clone().add(il.clone().getDirection().normalize().rotateAroundY(-angley).multiply(1.5)).add(0, angley*0.5, 0));
    	}
    	for(int d= 0; d<6; d++) {
        	a3.add(il.clone().add(0, d, 0));
    	}
    	
		a1.forEach(l -> {
    		l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,5,0.1,0.1,0.1,0);
    		l.getWorld().spawnParticle(Particle.CLOUD, l,5,0.1,0.1,0.1,0);
    		l.getWorld().spawnParticle(Particle.CRIT_MAGIC, l,5,0.1,0.1,0.1,0);
		});
		a3.forEach(l -> {
    		l.getWorld().spawnParticle(Particle.CLOUD, l,40,0.65,1,0.65,0);
		});
		
	    return ;
	}

	final private List<Location> upperstep(Location tl, Location pl) {
        List<Location> line = new ArrayList<>();
        
        for(double an =0; an<tl.distance(pl)-0.1; an+=0.4) {
        	line.add(pl.clone().add(pl.clone().getDirection().normalize().multiply(an)).add(0, Math.sin((an-5.5)/2)+0.2, 0));
        }
        return line;
	}

	final private void uppersweep(Location pl) {
        ArrayList<Location> line = new ArrayList<Location>();
        for(double an = Math.PI/3; an>-Math.PI/3; an-=Math.PI/180) {
        	Vector v = pl.clone().getDirection().rotateAroundY(Math.PI/2);
        	pl.add(pl.getDirection().rotateAroundAxis(v,an).normalize().multiply(2.5));
        	line.add(pl);
        }
        line.forEach(l -> {
    		l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,5,0.1,0.1,0.1,0);
        });
	}

	
	final private void ult2(Player p, Location tl, Vector tv) {

        
        List<Location> circle = ult2(tl,tv);

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
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100,20,false,false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100,4,false,false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100,4,false,false));
		p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
		p.getWorld().playSound(p.getLocation(),Sound.ENTITY_HORSE_BREATHE, 1,0.8f);
		p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,0.8f);
		p.getWorld().playSound(p.getLocation(),Sound.ENTITY_HORSE_BREATHE, 1,1.8f);
		p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,1.8f);
		
		AtomicInteger j = new AtomicInteger();
		
		circle.forEach(l -> {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
        			
					p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1,0);
					p.getWorld().spawnParticle(Particle.CLOUD, p.getEyeLocation(), 10, 1, 1, 1,0);
					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getEyeLocation(), 10, 1, 1, 1);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.3f,0.8f);
					p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.3f,1.8f);
					p.teleport(l);
					p.setCooldown(Material.TORCH, 3); p.swingOffHand();
                	
                	for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 3.5,8,3.5)) {
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
							le.teleport(tl);
							Holding.superholding(p,le,90l);
						}
                	}
                	for(Entity e : tl.getWorld().getNearbyEntities(tl, 8,8,8)) {
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
							atk1(0.16, p, le);	
							Holding.superholding(p,le,20l);
						}
                	}
				}
            }, j.getAndIncrement()); 
		});
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	Location jl = p.getLocation().clone().add(0, -0.1, 0);
            	jl.setDirection(tl.clone().toVector().subtract(jl.clone().toVector()));
            	List<Location> ll = upperstep(tl,jl);
            	AtomicInteger u = new AtomicInteger();
            	
            	ll.forEach(l -> {
            		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                        @Override
                        public void run() 
                        {
            				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 3, 1, 1, 1);
            				p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 40, 1, 1, 1);
            				p.playSound(l,Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.1f,0.1f);
            				p.playSound(l,Sound.ENTITY_PLAYER_ATTACK_WEAK, 0.1f,1.1f);
            				p.teleport(l);
                        }
            		},u.getAndIncrement());
            	});
        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() 
                    {
						p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 5,5,false,false));
        				p.setCooldown(Material.TORCH, 3); p.swingMainHand();
        				uppersweep(p.getLocation().clone());
        				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
        				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 1.5f);
        				p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 2f);
	    				p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 100, 2, 2, 2);
	    				p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 100, 1, 1, 1);
                    	
                    	for(int i =0; i<3; i++) {
                     	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
        			                @Override
        			                public void run() 
        			                {
        			                	storm(tl);
        			    				p.playSound(p.getLocation(), Sound.BLOCK_METAL_PLACE, 0.8f, 0f);
        			    				p.playSound(p.getLocation(), Sound.BLOCK_METAL_BREAK, 0.8f, 2f);
        			    				p.playSound(p.getLocation(), Sound.BLOCK_METAL_BREAK, 0.8f, 0f);
        			                	for(Entity e : p.getWorld().getNearbyEntities(tl, 5,5,5)) {

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
        			    								le.teleport(tl.clone().add(0, 5, 0));
        			    								le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 15,10,false,false));
        			    								atk1(2.1, p, le);	
        			    								Holding.superholding(p, le, 30l);
        			    							}
        			    					}
        			                	}
        			                }
                     	   },i);
                    	}
                    }
        		},u.getAndIncrement());
            	
			}
        }, j.getAndIncrement()); 
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
				tl.getWorld().spawnParticle(Particle.SONIC_BOOM, tl, 100, 5, 1, 5);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100,20,false,false));
				p.playSound(tl, Sound.ENTITY_HOSTILE_BIG_FALL, 0.7f, 0f);
				p.playSound(tl, Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.6f, 0.3f);
				p.playSound(tl, Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 0.1f);
				p.playSound(tl, Sound.ENTITY_GENERIC_BIG_FALL, 0.8f, 0f);
				p.playSound(tl, Sound.BLOCK_SCULK_CATALYST_BREAK, 0.8f, 0f);
                p.playSound(tl, Sound.ENTITY_IRON_GOLEM_ATTACK, 0.8f, 0f);
                p.playSound(tl, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.4f, 2f);
                
				tl.getWorld().spawnParticle(Particle.FLASH, tl, 400, 5, 1, 5);
				tl.getWorld().spawnParticle(Particle.CRIT, tl, 600, 1, 1, 1);
				tl.getWorld().spawnParticle(Particle.WHITE_ASH, tl, 800, 1, 1, 1);
				tl.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 600, 5, 1, 5);
				tl.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 600, 5, 1, 5, Material.IRON_BLOCK.createBlockData());
            	for(Entity e : tl.getWorld().getNearbyEntities(tl, 5,13,5)) {

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
								le.teleport(tl);
								atk1(17.5, p, le);	
									
							}
					}
            	}
			}
        }, j.getAndIncrement()+30); 
		
	}
	
	
	public void ULT2(PlayerItemHeldEvent ev)
    {

		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}

		ItemStack is = p.getInventory().getItemInMainHand();


		if(ClassData.pc.get(p.getUniqueId()) == 7 && (is.getType().name().contains("BANNER_PATTERN"))&& (is.hasItemMeta()) && is.getItemMeta().hasCustomModelData()  && !p.isSneaking()&& p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
		    	final Location tl = gettargetblock(p,3);
		    	final Vector tv = tl.clone().toVector().subtract(tl.clone().add(0, -0.01, 1).toVector());
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(75*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("철인의 의지")
						.ename("Will Of Ironman")
						.slot(7)
						.hm(sult2cooldown)
						.skillUse(() -> {
	                    	ult2(p,tl,tv);
						});
				bd.execute();
				
			}
    }
	
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
	    
		Player p = e.getPlayer();

		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7)
		{
			if(condition(p) && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
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

		
		
		if(condition(p) && ClassData.pc.get(p.getUniqueId()) == 7) {
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

		double sec = 0.5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 7&&condition(p)) {
			
				if(p.isSneaking())
					{
					final Vector pv = p.getEyeLocation().getDirection().clone();
					if(pv.getY() > -0.5) {
						return;
					}
					
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("위빙")
						.ename("Weaving")
						.slot(5)
						.hm(gdcooldown)
						.skillUse(() -> {
						
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
						});
				bd.execute();
					
					
			}
		}}
	}

	
	public void Parrying(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
	    
		if(condition(p)) 
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
		if(condition(p)) {

			
			
			if(ClassData.pc.get(p.getUniqueId()) == 7) {
					if(Proficiency.getpro(p) >=1) {
						d.setDamage(d.getDamage()*0.75);
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
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 2, false, false));
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
	    if(d.getEntity() instanceof LivingEntity) {
			LivingEntity le = (LivingEntity)d.getEntity();
			
			if(d.isCancelled() || le.hasMetadata("fake") || le.hasMetadata("portal")){
				return;
			}
			
			if(d.getDamager() instanceof Player) 
			{
				Player p = (Player)d.getDamager();
				if(p.getInventory().getItemInMainHand().hasItemMeta() && condition(p) && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
				{
			
					
					
					if(ClassData.pc.get(p.getUniqueId()) == 7) {
						
							
							if(counter.containsKey(p.getUniqueId())) 
							{
								dset2(d, p, 1.4+bsd.Counter.getOrDefault(p.getUniqueId(),0)*0.025,le,9);
				            	p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 2);
				        		p.playSound(le.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.3f, 2f);
							}
						}
				}
			}

			if(d.getDamager() instanceof Arrow) 
			{
				Arrow a = (Arrow)d.getDamager();
		
				if(a.getShooter() instanceof Player) {
					Player p = (Player) a.getShooter();
					if(condition(p) && (p.getInventory().getItemInOffHand().getType() == p.getInventory().getItemInMainHand().getType()))
					{
						
						
						if(ClassData.pc.get(p.getUniqueId()) == 7) {
						
							
							if(counter.containsKey(p.getUniqueId())) 
							{
								dset2(d, p, 1.35+bsd.Counter.getOrDefault(p.getUniqueId(),0)*0.02,le,9);
				            	p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 2);
				        		p.playSound(le.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.3f, 2f);
							}
						}
					}
				}
			}
	    }
	}
}



