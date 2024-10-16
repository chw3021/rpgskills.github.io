package io.github.chw3021.classes.wreltler;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
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

	
	private HashMap<UUID, Integer> pounding = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> poundingt = new HashMap<UUID, Integer>();
	
	
	private Multimap<String, Integer> gst = ArrayListMultimap.create();
	private HashMap<UUID, UUID> gs = new HashMap<UUID, UUID>();
	
	private String path = new File("").getAbsolutePath();
	private WreSkillsData wsd;


	Pak pak = new Pak();


	private static final Wreskills instance = new Wreskills ();
	public static Wreskills getInstance(){
		return instance;
	}


		
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

    	if(!p.hasCooldown(CAREFUL) && tackledt.containsKey(p.getUniqueId())) {

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
        	if(tackledt.containsKey(p.getUniqueId())) {
        		Bukkit.getScheduler().cancelTask(tackledt.get(p.getUniqueId()));
        		tackledt.remove(p.getUniqueId());
        	}

        	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(0.1));
        	Location tl = p.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(1.3));

			tl.getWorld().spawnParticle(Particle.BLOCK, tl, 100,1.5,1.5,1.5,1,getBd(Material.DIRT_PATH));
			tl.getWorld().spawnParticle(Particle.ASH, tl, 100,1,1,1);
        	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1f, 0.2f);
        	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1f, 1.9f);
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
                	atk1(1.0, p, le);
                	Holding.holding(p, le, 20l);
                	le.setRotation(180, 0);
                	le.playEffect(EntityEffect.ENTITY_DEATH);
        			//le가 누워있는 부분 구현
				}
			}
            return true;
    	}
		return false;
	}

	private HashMap<UUID, Integer> tacklet = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> tackledt = new HashMap<UUID, Integer>();
	
	public void Tackle(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

		
		if(ClassData.pc.get(p.getUniqueId()) == 8 && wsd.Tackle.getOrDefault(p.getUniqueId(), 0)>=1) {
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
		 			    	p.setCooldown(CAREFUL, 5);
		 					
		 					
		                    int q =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	Holding.invur(p, 10l);
				                	p.playSound(p.getLocation(), Sound.BLOCK_HANGING_ROOTS_STEP, 0.1f, 1.5f);
				                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.25f, 1.9f);
				                	p.setGliding(true);
				                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(0.6));
				                	
									
				                	Location tl = p.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(0.6));

									tl.getWorld().spawnParticle(Particle.BLOCK, tl, 35,1,1,1,1,getBd(Material.DIRT_PATH));

			                    	
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
						                	Holding.superholding(p, le, 20l);
				    					}
				    				}
				                }
		                	 }, 0,2);
		                    tacklet.put(p.getUniqueId(), q);
		                    
		                    int t2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(0.1));
				                	quitTackle(p);
				                }
				            }, 35); 
		                    tackledt.put(p.getUniqueId(), t2);
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
	
	final private void sit(LivingEntity p, Integer tick) {
        if(sitdown.containsKey(p.getUniqueId())) {
        	Holding.ale(sitdown.get(p.getUniqueId())).remove();
        	sitdown.remove(p.getUniqueId());
        }
        
		p.getWorld().spawn(p.getLocation().clone(), ArmorStand.class, pa ->{
			pa.setRemoveWhenFarAway(true);
			pa.setInvisible(true);
			pa.setInvulnerable(true);
			pa.setSmall(true);
			pa.setMarker(true);
			pa.setBasePlate(false);
			pa.setCollidable(false);
			pa.setGravity(false);
			pa.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
			pa.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(),p.getName()));
			pa.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(),p.getName()));
			pa.setBodyPose(new EulerAngle(Math.toRadians(90), 0, 0));
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
		double sec =4*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		
		if(ClassData.pc.get(p.getUniqueId()) == 8 && wsd.ArmThrow.getOrDefault(p.getUniqueId(), 0)>=1) {
		if(p.isSneaking())
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
			        		
		                    
		                    for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 2, 2, 2)) {
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
		             								atk0(0.97, wsd.ArmThrow.getOrDefault(p.getUniqueId(),0)*0.915, p, le);
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
		double sec =4*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		
		if(ClassData.pc.get(p.getUniqueId()) == 8 && wsd.ChokeSlam.getOrDefault(p.getUniqueId(), 0)>=1) {
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
		                    final Location fl =p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().multiply(1.1));
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 0);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.2f, 2.0f);
			        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, fl, 10, 1.5, 0.5, 1.5);

		                    HashSet<LivingEntity> les = new HashSet<>();

		                    for(Entity e : p.getWorld().getNearbyEntities(fl, 1.5, 1.5, 1.5)) {
			                    
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
				                    Location l = gettargetblock(p,2).clone().add(0, -0.5, 0);
				                	if(!les.isEmpty()) {
				                		les.forEach(le ->{
											le.teleport(l);
											atk0(1.02, wsd.ChokeSlam.get(p.getUniqueId())*1.1, p, le);
				                		});
										p.playSound(l, Sound.ENTITY_HOSTILE_BIG_FALL, 1f, 0);
										p.playSound(l, Sound.ENTITY_WIND_CHARGE_THROW, 1f, 0);
										p.getWorld().spawnParticle(Particle.BLOCK, l, 100, 2,2,2,0 ,getBd(Material.DIRT));
				                	}
				                }
							}, 10);
						});
				bd.execute();
			}
		}
		}
	}

	
	public void Suplex(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8 && wsd.Suplex.getOrDefault(p.getUniqueId(), 0)>=1 && !p.isSneaking()&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
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
		        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 15, 2, 0.2, 2);
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
	             								atk0(1.27, wsd.Suplex.getOrDefault(p.getUniqueId(),0)*1.25, p, le);
								            }
					            }, j.incrementAndGet()/30+5); 
	                    	});
	                    }
					});
				bd.execute();
			}
		}
	
	}
	
	
	@SuppressWarnings("deprecation")
	public void GuillotineChoke(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

        
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8 && wsd.GuillotineChoke.getOrDefault(p.getUniqueId(), 0)>=1 && !p.hasCooldown(CAREFUL)) {
		if(!(p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
			{

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("앞목조르기")
						.ename("GuillotineChoke")
						.slot(4)
						.hm(gdcooldown)
						.skillUse(() -> {
							GiantSwingThrow(p);
							p.setGliding(true);
							p.swingMainHand();
							p.swingOffHand();
							p.playSound(p.getLocation(), Sound.ENTITY_HORSE_JUMP, 1.0f, 0f);
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    final Location tl = gettargetblock(p,2);
		                    AtomicInteger j = new AtomicInteger(0);
		                    final Location pl = p.getLocation().add(0, 1, 0);
		                    for(double d = 0.1; d <= tl.distance(pl); d += 0.1) {
								Vector ltr = tl.clone().toVector().subtract(pl.clone().toVector());
								if(pl.clone().add(ltr.normalize().multiply(d)).getBlock().isPassable()) {
									line.add(pl.clone().add(ltr.normalize().multiply(d)));
								}
		                    }
		                    AtomicBoolean ab = new AtomicBoolean(false);
		                    for(Location l : line) {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                    		
		                    		@Override
				                	public void run() 
					                {
										p.setGliding(true);	
				                    	p.teleport(l);
										p.setGliding(true);

					                    p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_START, 1.0f, 0f);
						        		p.getWorld().spawnParticle(Particle.SNEEZE, l, 4);
					                    Location gl = l.clone();

										for (Entity e : l.getWorld().getNearbyEntities(l, 0.5, 0.5, 0.5))
										{
				                    		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) 
											{
												LivingEntity le = (LivingEntity)e;
												le.teleport(l);
												Holding.superholding(p, le, 55l);
												gl = le.getEyeLocation().clone().add(0, -0.2, 0).setDirection(l.getDirection());
												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
												{
								             	@Override
										                public void run() 
								             			{	
				             								atk0(1.6, wsd.GuillotineChoke.getOrDefault(p.getUniqueId(),0)*1.65, p, le);
				             								le.playHurtAnimation(0);
												        }
									            }, 35);
												ab.set(true);
											}
										}
										if(ab.get()) {
											p.teleport(gl);
											Holding.holding(null, p, 35l);
											sit(p,35);
											return;
										}
					                }
		                    	}, j.incrementAndGet()/10); 
	                    		if(ab.get()) {
	                    			break;
	                    		}
		                    }
		                    
		                    
						});
				bd.execute();
				
	            
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
		
		if(!le.isValid() || le.isDead()) {
			return;
		}
		
		p.playSound(p.getLocation(), Sound.ENTITY_EGG_THROW, 0.8f, 0);
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.6f, 0);
        le.setGliding(true);
        le.setVelocity(BlockFace.DOWN.getDirection().multiply(1));
		p.getWorld().spawnParticle(Particle.ASH, le.getLocation(), 50);
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
	        atk0(2.2, wsd.GiantSwing.get(p.getUniqueId())*1.99, p, lle);
     		}
     	}
         	
                
	}
	
	
	public void GiantSwing(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{
			
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity)d.getEntity();
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 8 && p.isSneaking() && wsd.GiantSwing.getOrDefault(p.getUniqueId(), 0)>=1 && !p.hasCooldown(Material.YELLOW_TERRACOTTA)) {
			double sec = 26*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

			SkillBuilder bd = new SkillBuilder()
				.player(p)
				.cooldown(sec)
				.kname("풍차돌리기")
				.ename("GiantSwing")
				.slot(5)
				.hm(smcooldown)
				.skillUse(() -> {
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
					p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_2, 1.0f,0f);
					ArrayList<Location> swing = new ArrayList<Location>();
					ArrayList<Location> eye = new ArrayList<Location>();
					AtomicInteger j = new AtomicInteger(0);
					AtomicInteger k = new AtomicInteger(0);
					final Location l1 = p.getLocation().clone().add(0, -1, 0);
					final Location l2 = p.getLocation().clone();
					for(double i = Math.PI/90; i<Math.PI*8; i += Math.PI/90) {
						Location s = l1.clone().setDirection(l1.getDirection().clone().rotateAroundY(i).normalize().multiply(2));
						Location e = l2.clone().setDirection(l2.getDirection().clone().rotateAroundY(i).normalize());
						swing.add(s);
						eye.add(e);
					}
					p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, le.getLocation(), 5, 1, 0, 1);
					swing.forEach(l -> {
						int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
						{
				     	@Override
		                public void run() 
		     				{	
					    		if(!le.isValid() || le.isDead()) {
					    			GiantSwingThrow(p);
					    		}
		                     	le.teleport(l);
		    					Holding.superholding(p, le, 40l);
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
					
				});
				bd.execute();
					
				
			}
		}
	}

	final private Integer roll(Player p) {
		
		final Location pfl = p.getLocation().clone().add(0, 0.3, 0);
		final Vector pv = p.getEyeLocation().getDirection().clone().normalize();
		final Vector axis = pv.rotateAroundY(Math.PI/2);
		double rotationStep = (2 * Math.PI) / 40d;
		ArrayList<Location> line = new ArrayList<>();
		
		double angle = 0;
		double blocked = 0;
		for(double doub = 0; doub <4; doub+=0.1) {
			if(!pfl.clone().add(pv.clone().multiply(blocked)).getBlock().isPassable()){
				final Location inl = pfl.clone().add(pv.clone().multiply(blocked));
				line.add(inl.clone().setDirection(pv.rotateAroundY(angle).rotateAroundAxis(axis, angle)));
			}
			else {
				blocked = doub;
				final Location inl = pfl.clone().add(pv.clone().multiply(blocked));
				line.add(inl.clone().setDirection(pv.rotateAroundY(angle).rotateAroundAxis(axis, angle)));
			}
			angle =+ rotationStep;
		}
		
		AtomicInteger j = new AtomicInteger();

		p.getWorld().spawn(p.getLocation().clone(), Snowball.class, pa ->{
			//pa.setInvisible(true);
			pa.setInvulnerable(true);
			pa.setGravity(false);
			pa.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
			pa.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(),p.getName()));
			pa.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(),p.getName()));

			
			line.forEach(l ->{
		        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		            @Override
		            public void run() {
	                    p.playSound(p.getLocation(), Sound.BLOCK_HEAVY_CORE_STEP, 0.1f, 0);
	                    p.playSound(p.getLocation(), Sound.BLOCK_BASALT_STEP, 0.1f, 0);
						p.getWorld().spawnParticle(Particle.WARPED_SPORE, l, 5);
						p.getWorld().spawnParticle(Particle.SPORE_BLOSSOM_AIR, l, 5);
						p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, l, 5);
						p.getWorld().spawnParticle(Particle.CHERRY_LEAVES, l, 5);
						p.getWorld().spawnParticle(Particle.MYCELIUM, l, 5);
		            	pa.teleport(l);
		    			pa.setRotation(l.getYaw(), l.getPitch());
		            	
		            }
		        }, j.getAndIncrement()/2); 
			});
	        
	        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	            	pa.remove();
	            }
	        }, j.get()/2); 
		});
		
		return j.get()/2;
		
	}


	public void ULT(PlayerItemHeldEvent ev)
    {
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();
		
			if(ClassData.pc.get(p.getUniqueId()) == 8 && ev.getNewSlot()==3 && (is.getType().name().contains("BANNER_PATTERN"))&& (is.hasItemMeta()) && is.getItemMeta().hasCustomModelData()  && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(10/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("다리얽어비틀기")
						.ename("HeelHook")
						.slot(6)
						.hm(sultcooldown)
						.skillUse(() -> {
							
							int delay = roll(p);

		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_WEAK, 1f, 0);
		                    HashSet<LivingEntity> les = new HashSet<>();
		                    
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
							{
								@Override
				                public void run() 
		             				{
										for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 1.5, 1.5, 1.5)) {
		                            		if(e instanceof Player) {
		        								Player p1 = (Player) e;
		        								if(Party.hasParty(p) && Party.hasParty(p1))	{
		        									if(Party.getParty(p).equals(Party.getParty(p1)))
	        										{
	        											continue;
	        										}
	        									}
		                            		}
			                            	if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
			                        			LivingEntity le = (LivingEntity)e;
			                        			les.add(le);
			                        			Holding.superholding(p, le, 100l);
			                        		}
			                        	}
	                	            	if(!les.isEmpty()) {
	            		                    p.playSound(p.getLocation(), Sound.BLOCK_VAULT_ACTIVATE, 0.8f, 2);
	            		                    p.playSound(p.getLocation(), Sound.BLOCK_VAULT_CLOSE_SHUTTER, 0.8f, 0);
	            							p.getWorld().spawnParticle(Particle.VAULT_CONNECTION, p.getLocation(), 100,1,1,1);
	                	            	}
						            }
				            }, delay);

                	        
                	        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                	            @Override
                	            public void run() {
                	            	if(les.isEmpty()) {
                	            		return;
                	            	}
        		                    p.playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 0.8f, 2);
        		                    p.playSound(p.getLocation(), Sound.ENTITY_SKELETON_STEP, 0.34f, 0);
        		                    p.playSound(p.getLocation(), Sound.ENTITY_SKELETON_HURT, 0.34f, 0.6f);
        		                    p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_HURT, 0.34f, 0.6f);
        							p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 100,1,1,1);
        							p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, p.getLocation(), 100,1,1,1);
        							p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 100,1,1,1);
                	            	les.forEach(le ->{
    	        						atk1(25.5, p, le);
                	            	});
                	            }
                	        }, delay+20); 
						});
				bd.execute();
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


	public void TakeDown(EntityDamageByEntityEvent d) 
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
					dset2(d,p, 1.43 * (1+wsd.TakeDown.get(p.getUniqueId())*0.041),le,14);
					p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
				}
		}
		}
	
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity && !d.isCancelled()) 
		{
			Projectile ar = (Projectile)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player)ar.getShooter();
				
				if(ClassData.pc.get(p.getUniqueId()) == 8) {
					
						if(p.getInventory().getItemInMainHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getType().name().contains("BANNER_PATTERN") && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
						{
							dset2(d,p, 1.43 * (1+wsd.TakeDown.get(p.getUniqueId())*0.041),le,14);
							p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
						}
				}
			}
		}
	}
}



