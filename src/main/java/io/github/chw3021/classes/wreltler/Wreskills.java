package io.github.chw3021.classes.wreltler;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.party.Party;

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
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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


public class Wreskills extends Pak implements Serializable {
	

	
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
	

		
	public void er(PluginEnableEvent ev) 
	{
		WreSkillsData w = new WreSkillsData(WreSkillsData.loadData(path +"/plugins/RPGskills/WreSkillsData.data"));
		wsd = w;
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

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		WreSkillsData w = new WreSkillsData(WreSkillsData.loadData(path +"/plugins/RPGskills/WreSkillsData.data"));
		wsd = w;
		
	}
	
	final private boolean quitTackle(Player p) {

    	if(tacklet.containsKey(p.getUniqueId()) && !p.hasCooldown(CAREFUL)) {

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
            }, 30); 
        	poundingt.put(p.getUniqueId(), task);

        	if(tacklet.containsKey(p.getUniqueId())) {
        		Bukkit.getScheduler().cancelTask(tacklet.get(p.getUniqueId()));
        		tacklet.remove(p.getUniqueId());
        	}

        	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(0.1));
        	Location tl = p.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(1.3));

			tl.getWorld().spawnParticle(Particle.BLOCK, tl, 200,3,3,3,1,getBd(Material.DIRT_PATH));
			tl.getWorld().spawnParticle(Particle.FALLING_DUST, tl, 200,3,3,3,0.1,getBd(Material.DIRT_PATH));
			tl.getWorld().spawnParticle(Particle.ASH, tl, 100,3,3,3);
        	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1f, 0.2f);
        	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1f, 1.9f);
            for (Entity e : tl.getWorld().getNearbyEntities(tl, 2, 2, 2))
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
        			le.teleport(tl.clone().add(0, -1, 0));
                	atk1(1.0, p, le);
                	Holding.holding(p, le, 20l);
                	le.playEffect(EntityEffect.ENTITY_DEATH);
        			
				}
			}
            return true;
    	}
    	p.setCooldown(CAREFUL, 3);
		return false;
	}
	
	
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
				if(quitTackle(p)) {
					return;
				}
            	
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("태클")
						.ename("Tackle")
						.slot(0)
						.hm(sdcooldown)
						.skillUse(() -> {

		 					GiantSwingThrow(p);
		 					
		 					
		                    int q =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	Holding.invur(p, 10l);
				                	p.playSound(p.getLocation(), Sound.BLOCK_HANGING_ROOTS_STEP, 0.1f, 1.5f);
				                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.25f, 1.9f);
				                	p.setGliding(true);
				                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(1.3));
				                	
									
				                	Location tl = p.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(1.3));

									tl.getWorld().spawnParticle(Particle.BLOCK, tl, 35,1,1,1,1,getBd(Material.DIRT_PATH));

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
						                	le.teleport(tl.clone().add(0, -0.5, 0));
						                	Holding.holding(p, le, 25l);
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
				                	quitTackle(p);
				                }
				            }, 8); 
						});
				bd.execute();
			}
	            
			} // adding players name + current system time in miliseconds
            
		}
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
        
		p.getWorld().spawn(p.getLocation().clone(), ArmorStand.class, pa ->{
			pa.setRemoveWhenFarAway(true);
			pa.setInvisible(true);
			pa.setInvulnerable(true);
			pa.setSmall(true);
			pa.setCollidable(false);
			pa.setGravity(false);
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
		});
		
	}
	
	
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
					
                    final Location tl = gettargetblock(p,2).clone().add(0, -1, 0);
					
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
    					}
    				}
                    
					for(int n = 0; n<5; n++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
								sit(p,6);

								p.setCooldown(CAREFUL, 3);
								p.swingMainHand();
								p.getWorld().spawnParticle(Particle.CRIT, tl, 50, 2,2,2,0);
								p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, tl, 50, 2,2,2,0);
								p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.35f, 1.5f);
								p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.7f, 0);
								p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.7f, 0.1f);
								p.playSound(tl, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 0.5f, 0.8f);
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

	
	public void ArmThrow(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =4*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);
		
		if(ClassData.pc.get(p.getUniqueId()) == 8) {
		if(!p.isSneaking())
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("업어치기")
						.ename("ArmThrow")
						.slot(1)
						.hm(swcooldown)
						.skillUse(() -> {
		 					GiantSwingThrow(p);
		 					
		 					p.setCooldown(CAREFUL, 2);
		 					p.swingMainHand();

		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 2);
							p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 10, 2,2,2,0);
		                    ArrayList<Location> air = new ArrayList<Location>();
		                    ArrayList<Location> sight = new ArrayList<Location>();
	                    	final Location eye = p.getEyeLocation().clone();
		                    final Location l = eye.clone().add(eye.clone().getDirection().normalize().multiply(-1.5));
	                    	Vector eyev = eye.getDirection().clone().rotateAroundY(-Math.PI/2);
	                    	for(double i = Math.PI; i > 0; i -= Math.PI/90) {
	                    	    Location a = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, i).normalize());
	                    	    
	                    	    // 포물선 효과를 적용하기 위한 Y축 변동만 추가
	                    	    double heightOffset = Math.sin(i) * 2;  // 높이 변화를 주어 포물선 효과 적용
	                    	    a.setY(eye.getY() + heightOffset);  // 원래 높이에 대한 Y 변화
	                    	    sight.add(a);
	                    	    
	                    	    air.add(a.clone().add(a.clone().getDirection().normalize().multiply(1.8)));  // 적을 던지는 거리 계산
	                    	}
		                    HashSet<LivingEntity> lehs = new HashSet<>();
			        		
		                    
		                    for(Entity e : p.getWorld().getNearbyEntities(l, 2, 2, 2)) {
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
		                    		lehs.add(le);
									Holding.superholding(p, le, (long) 45);
				                    le.teleport(p.getLocation());
		                    	}
		                    }
							
		                    if(!lehs.isEmpty()) {
			                    AtomicInteger k = new AtomicInteger(0);
                    			sight.forEach(lel ->  {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
									{
					             	@Override
							                public void run() 
					             				{	
							                    	p.teleport(lel);
							                    	
									            }
						            }, k.incrementAndGet()/30+5); 
								});
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
								{
				             	@Override
						                public void run() 
				             				{	
												p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation().clone(), 120, 2,0.5,2,0 ,getBd(Material.DIRT));
							                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_BIG_FALL, 1f, 0);
												
								            }
					            }, k.incrementAndGet()/30+5);
								
		                    	lehs.forEach(le -> {
				                    AtomicInteger j = new AtomicInteger(0);

	                    			air.forEach(lel ->  {
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
										{
						             	@Override
								                public void run() 
						             				{	
								                    	le.teleport(lel);
								                    	
										            }
							            }, j.incrementAndGet()/30+5); 
									});
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
									{
					             	@Override
							                public void run() 
					             				{	
		             								atk0(0.97, wsd.ArmThrow.getOrDefault(p.getUniqueId(),0)*1.15, p, le);
									            }
						            }, j.incrementAndGet()/30+5); 
		                    	});
		                    }
						});
				bd.execute();

			}
		}
		}
	}
	
	
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
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("메치기")
						.ename("ChokeSlam")
						.slot(2)
						.hm(frcooldown)
						.skillUse(() -> {
							GiantSwingThrow(p);
		                    final Location l = gettargetblock(p,2).clone().add(0, -0.5, 0);
		                    final Location fl =p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().multiply(1.1));
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 0);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.2f, 2.0f);
			        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 10, 1.5, 0.5, 1.5);

		                    HashSet<LivingEntity> les = new HashSet<>();

		                    for(Entity e : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5)) {
			                    
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
									le.teleport(fl);
									Holding.superholding(p, le, (long) 45);
									les.add(le);
								} 
		                    }
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	if(!les.isEmpty()) {
				                		les.forEach(le ->{
											le.teleport(l);
											atk0(0.9, wsd.ChokeSlam.get(p.getUniqueId())*0.9, p, le);
				                		});
										p.playSound(l, Sound.ENTITY_HOSTILE_BIG_FALL, 1f, 0);
										p.playSound(l, Sound.ENTITY_WIND_CHARGE_THROW, 1f, 0);
										p.getWorld().spawnParticle(Particle.BLOCK, l, 100, 2,2,2,0 ,Material.DIRT.createBlockData());
				                	}
				                }
							}, 10);
						});
				bd.execute();
			}
		}
		}
	}

	
	public void Suplex(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024);

        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.isSneaking())
			{
				ev.setCancelled(true);

				SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(sec)
					.kname("안아넘기기")
					.ename("Suplex")
					.slot(3)
					.hm(cdcooldown)
					.skillUse(() -> {
						GiantSwingThrow(p);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2.0f);
		        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 4, 2, 0, 2);
	                    ArrayList<Location> air = new ArrayList<Location>();
	                    ArrayList<Location> sight = new ArrayList<Location>();
	                    final Location l = gettargetblock(p,2);
                    	final Location eye = p.getEyeLocation().clone();
                    	Vector eyev = eye.getDirection().clone().rotateAroundY(Math.PI/2);
	                    for(double i=Math.PI/180; i > -Math.PI; i -= Math.PI/180) {
	                    	Location a = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, i).normalize());
	                    	sight.add(a);
	                    	air.add(a.clone().add(a.clone().getDirection().normalize().multiply(2)));
	                    }
	 					
	 					p.setCooldown(CAREFUL, 2);
	 					p.swingMainHand();
	                    HashSet<LivingEntity> lehs = new HashSet<>();
		        		
	                    
	                    for(Entity e : p.getWorld().getNearbyEntities(l, 2, 2, 2)) {
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
	                    		lehs.add(le);
								Holding.superholding(p, le, (long) 45);
			                    le.teleport(p.getLocation());
	                    	}
	                    }
						
	                    if(!lehs.isEmpty()) {
		                    AtomicInteger k = new AtomicInteger(0);
                			sight.forEach(lel ->  {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
								{
				             	@Override
						                public void run() 
				             				{	
						                    	p.teleport(lel);
						                    	
								            }
					            }, k.incrementAndGet()/30+5); 
							});
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
							{
			             	@Override
					                public void run() 
			             				{	
											p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation().clone(), 120, 2,0.5,2,0 ,getBd(Material.DIRT));
						                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_BIG_FALL, 1f, 0);
											
							            }
				            }, k.incrementAndGet()/30+5);
							
	                    	lehs.forEach(le -> {
			                    AtomicInteger j = new AtomicInteger(0);

                    			air.forEach(lel ->  {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
									{
					             	@Override
							                public void run() 
					             				{	
							                    	le.teleport(lel);
							                    	
									            }
						            }, j.incrementAndGet()/30+5); 
								});
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
								{
				             	@Override
						                public void run() 
				             				{	
	             								atk0(0.97, wsd.Suplex.getOrDefault(p.getUniqueId(),0)*1.15, p, le);
								            }
					            }, j.incrementAndGet()/30+5); 
	                    	});
	                    }
					});
				bd.execute();
			}
		}
	
	}
	
	
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
	                    Location l = gettargetblock(p,4);
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
				        		p.getWorld().spawnParticle(Particle.LARGE_SMOKE, p.getEyeLocation(), 100,4,4,4);
								p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 50, 2,2,2,0 ,Material.DIRT.createBlockData());
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
	                sdcooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	
	
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
							p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, le.getLocation(), 5, 1, 0, 1);
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
						p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, le.getLocation(), 5, 1, 0, 1);
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
		        		p.getWorld().spawnParticle(Particle.LARGE_SMOKE, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 40, 2, 0, 2);
		        		AtomicInteger j = new AtomicInteger();
		        		ArrayList<Location> drop = new ArrayList<>();
		        		for(int i=50; i>0; i -= 1) {
		        			Location plc = p.getLocation().clone();
		        			drop.add(plc.add(0, i, 0));
		        		}
	                    Location l = gettargetblock(p,2);

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
				                    p.spawnParticle(Particle.LARGE_SMOKE, l, 500,6,2,6);
				                    p.spawnParticle(Particle.BLOCK, l, 500,6,2,6, p.getLocation().getBlock().getBlockData());
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
	        		p.getWorld().spawnParticle(Particle.LARGE_SMOKE, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 40, 2, 0, 2);
	        		AtomicInteger j = new AtomicInteger();
	        		ArrayList<Location> drop = new ArrayList<>();
	        		for(int i=50; i>0; i -= 1) {
	        			Location plc = p.getLocation().clone();
	        			drop.add(plc.add(0, i, 0));
	        		}
                    Location l = gettargetblock(p,2);

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
			                    p.spawnParticle(Particle.LARGE_SMOKE, l, 500,6,2,6);
			                    p.spawnParticle(Particle.BLOCK, l, 500,6,2,6, p.getLocation().getBlock().getBlockData());
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



