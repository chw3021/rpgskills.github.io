package io.github.chw3021.classes.berserker;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Berskills extends Pak {
	

	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> lncooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cacooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();
	private HashMap<UUID, Boolean> ulton = new HashMap<UUID, Boolean>();
	private HashMap<UUID, Integer> ultont = new HashMap<UUID, Integer>();
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	
	private HashMap<UUID, Integer> squirt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> squirtt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> rave = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> ravet = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Integer> smite = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> smitet = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> onsl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> onslt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> scratch = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> scratcht = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> nom = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> nomt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> crs = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> crst = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Location> burst = new HashMap<UUID, Location>();
	private HashMap<UUID, Integer> burstt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> conv = new HashMap<UUID, Integer>();
	
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private BerSkillsData bsd;


	private static final Berskills instance = new Berskills ();
	public static Berskills getInstance()
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
				Player p = (Player) e.getWhoClicked();
				if(ultont.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(ultont.get(p.getUniqueId()));
					ultont.remove(p.getUniqueId());
				}
				ulton.remove(p.getUniqueId());
			}
			
		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Berskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				BerSkillsData b = new BerSkillsData(BerSkillsData.loadData(path +"/plugins/RPGskills/BerSkillsData.data"));
				bsd = b;
			}
		}
	}

		
	public void nepreventer(PlayerJoinEvent ev) 
	{
		BerSkillsData b = new BerSkillsData(BerSkillsData.loadData(path +"/plugins/RPGskills/BerSkillsData.data"));
		bsd = b;
	}

		
	public void er(PluginEnableEvent ev) 
	{
		BerSkillsData b = new BerSkillsData(BerSkillsData.loadData(path +"/plugins/RPGskills/BerSkillsData.data"));
		bsd = b;
	}
	
	public void Swipe(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(ClassData.pc.get(p.getUniqueId()) == 1 && bsd.Swipe.getOrDefault(p.getUniqueId(), 0)>=1) {
		
		if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !p.hasCooldown(CAREFUL))
		{
		double sec = 8*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);


		if((p.isSneaking()) && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK))
		{
        	final Location dl = p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(1.7));

			ev.setCancelled(true);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(sec)
					.kname("휩쓸기")
					.ename("Swipe")
					.slot(0)
					.hm(sdcooldown)
					.skillUse(() -> {

	                    if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
	                    	p.damage(2);
	                    }
	                	
		            	if(scratcht.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(scratcht.get(p.getUniqueId()));
	                		scratcht.remove(p.getUniqueId());
	                	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									scratch.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								scratch.remove(p.getUniqueId());
			                }
			            }, 25); 
	                	scratcht.put(p.getUniqueId(), task);
	                    
	                    ArrayList<Location> line = new ArrayList<Location>();
	                    ArrayList<Location> pie = new ArrayList<Location>();
	                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
	                    new AtomicInteger(0);
	                    for(double d = -Math.PI/6; d<= Math.PI/6; d += Math.PI/180) {
		                    Location l = p.getLocation();
		                    l.setDirection(l.getDirection().normalize().rotateAroundY(d));
		                    l.add(l.getDirection().normalize().multiply(7.1));
		                    line.add(l);
	                    	
	                    }
	                    for(double d = 0.1; d <= 7.1; d += 1) {
	                    	  for (Location l : line){
			                    Location pl = p.getLocation();
								Vector ltr = l.toVector().subtract(pl.toVector());
								pl.add(ltr.normalize().multiply(d));
								pie.add(pl);
								}
	                    }
	                    
	                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
	                    pie.forEach(i -> {
								p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, i, 1, 0.5,0.5,0.5,0);

		                    	for (Entity a : p.getWorld().getNearbyEntities(i, 1.5, 1.5, 1.5))
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
									if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake")) && !(a.hasMetadata("portal"))) 
									{
										LivingEntity le = (LivingEntity)a;
										les.add(le);
									}
								}
						}); 
	                    les.forEach(le ->
						{
							atk0(0.5d, bsd.Swipe.get(p.getUniqueId())*0.8, p, le);
							le.teleport(dl);
							
							Holding.holding(p, le, 9l);
						});
					});
			bd.execute();
			
			}
		}
		}
	}
	private ArrayList<Location> Scratch(Location il) {

    	ArrayList<Location> line = new ArrayList<Location>();
        Location pl = il.clone();
        Vector pfv = il.clone().add(0, 1, 0).toVector().subtract(il.clone().toVector());
        Vector pv = pfv.clone().rotateAroundAxis(pl.clone().getDirection(), -Math.PI/4);
        Vector pv2 = pfv.clone().rotateAroundAxis(pl.clone().getDirection(), Math.PI/4);

        for(double an = 0; an > -Math.PI*2; an -= Math.PI/90) {
        	Location sw = pl.clone().add(pl.clone().getDirection().normalize().rotateAroundAxis(pv, an).multiply(4));
        	line.add(sw);
        }
        for(double an = 0; an > -Math.PI*2; an -= Math.PI/90) {
        	Location sw = pl.clone().add(pl.clone().getDirection().normalize().rotateAroundAxis(pv2, an).multiply(4));
        	line.add(sw);
        }

	    line.forEach(l -> {
			l.getWorld().spawnParticle(Particle.CRIT, l,2,0.1,0.1,0.1,0.2);
			l.getWorld().spawnParticle(Particle.CRIMSON_SPORE, l,2,0.1,0.1,0.1,0.2);
			l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,5,0.3,0.3,0.3,0.1);
	    	
	    });
	    return line;
	}


	public void Scratch(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();
	
		if(ClassData.pc.get(p.getUniqueId()) == 1 && !p.hasCooldown(CAREFUL)) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD")&& (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) && p.isSneaking() &&scratch.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				
                if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
                	p.damage(2);
                }
                
	        	if(scratcht.containsKey(p.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(scratcht.get(p.getUniqueId()));
	        		scratcht.remove(p.getUniqueId());
	        	}
	        	
	        	scratch.remove(p.getUniqueId());

				final Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(2)).add(0, -0.6, 0);

                Scratch(p.getEyeLocation().clone());
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 2f);
                p.playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1f, 2f);	
                tl.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl ,40,3,3,3);
                
				for(Entity e : tl.getWorld().getNearbyEntities(tl,4, 4, 4)) {
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
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
						atk0(0.7d, bsd.Swipe.get(p.getUniqueId())*0.9, p, le);
						Holding.holding(p, le, 9l);
	                    le.teleport(tl);
					}
				}
				
			}
		
		}
	}
	
	final private void Conversion(Player p) {

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			switch (conv.getOrDefault(p.getUniqueId(),0))
			{
				case 1:
					conv.put(p.getUniqueId(), 0);
					p.playSound(p, Sound.ENTITY_SKELETON_CONVERTED_TO_STRAY, 0.5f, 1.3f);
					p.spawnParticle(Particle.ENCHANTED_HIT, p.getLocation(), 20, 1, 1, 1);
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{갈망}").color(ChatColor.RED).create());
					break;
				case 0:
					conv.put(p.getUniqueId(), 1);
					p.playSound(p, Sound.ENTITY_HUSK_CONVERTED_TO_ZOMBIE, 0.5f, 1.3f);
					p.spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 20, 1, 1, 1);
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{광란}").color(ChatColor.DARK_RED).create());
					break;
			}
		}
		else {
			switch (conv.getOrDefault(p.getUniqueId(),0))
			{
				case 1:
					conv.put(p.getUniqueId(), 0);
					p.playSound(p, Sound.ENTITY_SKELETON_CONVERTED_TO_STRAY, 0.5f, 1.3f);
					p.spawnParticle(Particle.ENCHANTED_HIT, p.getLocation(), 20, 1, 1, 1);
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Eager}").color(ChatColor.RED).create());
					break;
				case 0:
					conv.put(p.getUniqueId(), 1);
					p.playSound(p, Sound.ENTITY_HUSK_CONVERTED_TO_ZOMBIE, 0.5f, 1.3f);
					p.spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 20, 1, 1, 1);
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("{Frenzy}").color(ChatColor.DARK_RED).create());
					break;
			}
		}
	}

	public void Conversion(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 1&& bsd.Conversion.getOrDefault(p.getUniqueId(), 0)>=1 && !p.isSneaking()) {
			ev.setCancelled(true);
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !p.hasCooldown(Material.CRIMSON_STEM))
			{
				p.setCooldown(Material.CRIMSON_STEM, 10);

				Conversion(p);
			}
		}
	}
	
	public void Spray(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(ClassData.pc.get(p.getUniqueId()) == 1&& bsd.Spray.getOrDefault(p.getUniqueId(), 0)>=1) {
		if(p.isSneaking()&&p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
		{
		double sec = 3*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

	    
		
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("분사")
						.ename("Spray")
						.slot(1)
						.hm(cdcooldown)
						.skillUse(() -> {

		                    if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
		                    	p.damage(3);
		                    }
		                    
			            	if(squirtt.containsKey(p.getUniqueId())) {
		                		Bukkit.getScheduler().cancelTask(squirtt.get(p.getUniqueId()));
		                		squirtt.remove(p.getUniqueId());
		                	}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									if(Proficiency.getpro(p)>=1) {
										squirt.putIfAbsent(p.getUniqueId(), 0);
									}
				                }
				            }, 3); 

		            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									squirt.remove(p.getUniqueId());
				                }
				            }, 25); 
		                	squirtt.put(p.getUniqueId(), task);
		                	p.playSound(p.getLocation(), Sound.ENTITY_LLAMA_SPIT, 1.0f, 0.1f);
		                	p.playSound(p.getLocation(), Sound.ENTITY_FOX_SPIT, 1.0f, 0.1f);
		                	p.playSound(p.getLocation(), Sound.ITEM_BONE_MEAL_USE, 1.0f, 0.1f);
		                	p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 1.0f, 1.5f);
		                    
		                    ArrayList<Location> line = new ArrayList<Location>();
		                    ArrayList<Location> pie = new ArrayList<Location>();
		                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    AtomicInteger j = new AtomicInteger(0);
		                    for(double d = -Math.PI/6; d<= Math.PI/6; d += Math.PI/180) {
			                    Location l = p.getLocation();
			                    l.setDirection(l.getDirection().normalize().rotateAroundY(d));
			                    l.add(l.getDirection().normalize().multiply(10));
			                    line.add(l);
		                    	
		                    }
		                    for(double d = 0.1; d <= 10; d += 0.3) {
		                    	  for (Location l : line){
				                    Location pl = p.getLocation();
									Vector ltr = l.toVector().subtract(pl.toVector());
									pl.add(ltr.normalize().multiply(d));
									pie.add(pl);
									}
		                    }
		                    

		                    pie.forEach(i -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                    		@Override
				                	public void run() 
					                {	
									p.getWorld().spawnParticle(Particle.BLOCK, i, 2, 0.5,0.5,0.5,0 ,Material.CRIMSON_NYLIUM.createBlockData());
										if(ulton.containsKey(p.getUniqueId())) {
											p.getWorld().spawnParticle(Particle.FALLING_LAVA, i, 2, 1, 1, 1);
											}
						                }
			                	   }, j.getAndIncrement()/230); 
			                    	for (Entity a : p.getWorld().getNearbyEntities(i, 1, 1, 1))
									{
										if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
										{
											LivingEntity le = (LivingEntity)a;
											les.add(le);
										}
									}
							});
		                    les.forEach(le ->
							{

								atk0(0.8, bsd.Spray.get(p.getUniqueId())*0.7, p, le);
							});
						});
				bd.execute();
					
				}
		}
	}
	
	

	
	public void Squirt(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && p.isSneaking() &&squirt.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

                if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
                	p.damage(3);
                }
                
				
            	if(squirtt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(squirtt.get(p.getUniqueId()));
            		squirtt.remove(p.getUniqueId());
            	}
				squirt.remove(p.getUniqueId());
				
				
            	if(ravet.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(ravet.get(p.getUniqueId()));
            		ravet.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							rave.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 6); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						rave.remove(p.getUniqueId());
	                }
	            }, 30); 
            	ravet.put(p.getUniqueId(), task);
            	
            	
				final Location tl = gettargetblock(p,4).clone();
				
				ArrayList<Location> line = new ArrayList<Location>();
                AtomicInteger j = new AtomicInteger(0);
                for(double d = 0.1; d <= 5; d += 0.3) {
						line.add(tl.clone().add(0, d, 0).setDirection(BlockFace.UP.getDirection()));
                }
                
                line.forEach(l ->  {	
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            		@Override
	                	public void run() 
		                {	
						tl.getWorld().spawnParticle(Particle.DUST_PILLAR, l, 500, 4,0.5,4,0 ,getBd(Material.CRIMSON_HYPHAE));
							if(ulton.containsKey(p.getUniqueId())) {
								tl.getWorld().spawnParticle(Particle.DRIPPING_LAVA, l, 2, 1, 1, 1);
								}
			                }
                	   }, j.getAndIncrement()/230); 
                });

                p.playSound(p.getLocation(), Sound.ENTITY_FOX_SPIT, 1.0f, 2f);
                p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1.0f, 0f);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, 1.0f, 2f);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2f);
				for(int i = 0; i <5; i++) {
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							for(Entity e : tl.getWorld().getNearbyEntities(tl,5, 8, 5)) {
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
			                    	atk0(0.31, bsd.Spray.get(p.getUniqueId())*0.3, p, le);
	    							Holding.holding(p, le, 2l);
								}
							}
		                }
		            }, i*2);
                }
			}
		}
	}
	private void spawnDonutRings(Player p) {
	    Location startLocation = p.getEyeLocation().clone();
	    Vector direction = p.getEyeLocation().getDirection().normalize();
	    
	    int ringCount = 5; // 고리 개수
	    double ringDistance = 1.2; // 고리 간의 거리
	    double initialRadius = 1.0; // 첫 번째 고리의 반지름
	    double radiusIncrement = 0.6; // 고리 반지름 증가량
	    int particlesPerRing = 200; // 각 고리 당 파티클 개수

	    for (int ring = 0; ring < ringCount; ring++) {
	        // 각 고리의 중심을 계산 (시선 방향으로 점점 멀어짐)
	        Location ringCenter = startLocation.clone().add(direction.clone().multiply(ring * ringDistance));
	        double radius = initialRadius + (ring * radiusIncrement);
	        
	        // 현재 고리에서 파티클 위치 계산
	        for (int i = 0; i < particlesPerRing; i++) {
	            double angle = 2 * Math.PI * i / particlesPerRing;
	            
	            // 플레이어 시선 기준의 좌표 계산
	            double xOffset = Math.cos(angle) * radius;
	            double yOffset = Math.sin(angle) * radius;
	            
	            // 시선 벡터를 기준으로 회전된 평면에 파티클 위치 추가
	            Vector offset = rotateAroundDirection(direction, xOffset, yOffset);
	            Location particleLocation = ringCenter.clone().add(offset);
	            
	            // 파티클 생성
	            p.getWorld().spawnParticle(Particle.BLOCK_CRUMBLE, particleLocation, 1, 0, 0, 0, 0.05, getBd(Material.NETHER_GOLD_ORE));
	        }
	    }
	}

	// 방향 벡터를 기준으로 주어진 X, Y 오프셋을 회전시키는 메서드
	private Vector rotateAroundDirection(Vector direction, double xOffset, double yOffset) {
	    // 시선 벡터의 직교 벡터를 구함
	    Vector perpendicular = new Vector(-direction.getZ(), 0, direction.getX()).normalize();
	    Vector up = direction.clone().crossProduct(perpendicular).normalize();
	    
	    // 직교 벡터와 시선 벡터의 수직 벡터를 사용해 평면상 위치 계산
	    Vector result = perpendicular.multiply(xOffset).add(up.multiply(yOffset));
	    return result;
	}

	
	public void Rave(PlayerSwapHandItemsEvent ev) 
	{
		Player p = ev.getPlayer();
	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && p.isSneaking() &&rave.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

                if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
                	p.damage(3);
                }
                
				
            	if(ravet.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(ravet.get(p.getUniqueId()));
            		ravet.remove(p.getUniqueId());
            	}
				rave.remove(p.getUniqueId());
            	
                p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 1.0f, 2f);

            	final Location tl = p.getEyeLocation().clone().add(0, -1, 0);
				
            	spawnDonutRings(p);
            	
				for(Entity e :tl.getWorld().getNearbyEntities(tl.clone().add(tl.getDirection().clone().normalize().multiply(3)),6, 6, 6)) {
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
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
                    	atk0(1.1, bsd.Spray.get(p.getUniqueId())*0.9, p, le);
						Holding.holding(p, le, 10l);
							
					}
				}
			}
		}
	}

	final private void Ray(Location as, LivingEntity le) {
		final World snw = as.getWorld();
		final Location lel = le.getEyeLocation().clone();
		final Location snl = as.clone();
		final Vector v = lel.clone().toVector().subtract(snl.clone().toVector());
		final double dis = lel.distance(snl);
		
		HashSet<Location> line = new HashSet<>();
		for(double d = 0; d<dis; d += 0.1) {
			line.add(snl.clone().add(v.clone().normalize().multiply(d)));
		}
		line.forEach(l -> {
			snw.spawnParticle(Particle.BLOCK, l, 3, 0.01,0.01,0.01,0, Material.RED_GLAZED_TERRACOTTA.createBlockData());
		});
	}

	
	
	@SuppressWarnings("deprecation")
	public void Inhale(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
		{

	    
		if(ClassData.pc.get(p.getUniqueId()) == 1&& bsd.Inhale.getOrDefault(p.getUniqueId(), 0)>=1) {
		if(!(p.isSneaking()) && !p.isOnGround() && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			double sec = 6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

			final Location pl = p.getLocation().add(0, 0.35, 0).clone();
        	p.setCooldown(CAREFUL, 3);
        	
        	

			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(sec)
					.kname("흡입")
					.ename("Inhale")
					.slot(2)
					.hm(frcooldown)
					.skillUse(() -> {

		                if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
		                	p.damage(3);
		                }
		                
		                
		                
		                if(burstt.containsKey(p.getUniqueId())) {
		            		Bukkit.getScheduler().cancelTask(burstt.get(p.getUniqueId()));
		            		burstt.remove(p.getUniqueId());
		            	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									burst.putIfAbsent(p.getUniqueId(), pl);
								}
			                }
			            }, 3); 

		        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								burst.remove(p.getUniqueId());
			                }
			            }, 35); 
		            	burstt.put(p.getUniqueId(), task);
		            	p.setCooldown(CAREFUL, 3);
		                p.swingMainHand();
						p.playSound(pl.clone(), Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 0.5f, 0.9f);
						p.getWorld().spawnParticle(Particle.BLOCK_MARKER, pl.clone(), 5, 0.31,0.31,0.31,1,getBd(Material.REDSTONE_BLOCK));
						for(int i =0; i<8; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
										p.playSound(pl.clone(), Sound.ENTITY_GENERIC_DRINK, 0.15f, 2f);
										
										for (Entity a : p.getWorld().getNearbyEntities(pl.clone(), 5, 5, 5))
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
											if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")&& !(a.hasMetadata("portal"))) 
											{
												LivingEntity le = (LivingEntity)a;
												Holding.holding(p, le, (long) 5); 
												Ray(pl.clone(),le);
												p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
												p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 0, false, false));
												atk0(0.2d, bsd.Inhale.get(p.getUniqueId())*0.2d, p, le);
							                }					
										}
									}
				            }, i*2); 
						}
					});
			bd.execute();
		
	            
			} 
            
		}
		}
	}

	
	@SuppressWarnings("deprecation")
	public void Burstout(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();
	
		if(ClassData.pc.get(p.getUniqueId()) == 1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD")&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK) &&!p.isOnGround() && !p.isSneaking() &&burst.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				
                if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
                	p.damage(4);
                }
                
            	p.setCooldown(CAREFUL, 3);
	        	if(burstt.containsKey(p.getUniqueId())) {
	        		Bukkit.getScheduler().cancelTask(burstt.get(p.getUniqueId()));
	        		burstt.remove(p.getUniqueId());
	        	}
	        	
	        	final Location pl = burst.remove(p.getUniqueId()).clone();
	        	
				
				p.getWorld().spawnParticle(Particle.BLOCK, pl, 700, 6,6,6,Material.CRIMSON_NYLIUM.createBlockData()); 
				p.getWorld().spawnParticle(Particle.BLOCK, pl, 700, 6,6,6,Material.NETHER_QUARTZ_ORE.createBlockData()); 
				p.getWorld().spawnParticle(Particle.EXPLOSION, pl, 150, 6,6,6); 
				if(ulton.containsKey(p.getUniqueId())) {
					p.getWorld().spawnParticle(Particle.LAVA, pl, 50, 6, 3, 6);
					p.getWorld().spawnParticle(Particle.ASH, pl, 100, 6, 3, 6);
				}
				p.playSound(pl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 2f);
				p.playSound(pl, Sound.ENTITY_WITHER_HURT, 1.0f, 0f);
				for (Entity a : pl.getWorld().getNearbyEntities(pl, 6.5, 6.5, 6.5))
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
					if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")&& !(a.hasMetadata("portal"))) 
					{
						
								LivingEntity le = (LivingEntity)a;
								
		                    	atk0(1.2, bsd.Inhale.get(p.getUniqueId())*1.3, p, le);
		                    	Holding.holding(p, le, 10l);
	                }							
				}
			}
		
		}
	}



	public void CrimsonAdvance(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
		{
		Action ac = ev.getAction();
		double sec = 4*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

	    
		
		
		if(ClassData.pc.get(p.getUniqueId()) == 1&& bsd.Crimpson.getOrDefault(p.getUniqueId(), 0)>=1) {
		if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{

        	p.setCooldown(CAREFUL, 3);
        	ev.setCancelled(true);
        	
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(sec)
					.kname("진홍빛전진")
					.ename("CrimsonAdvance")
					.slot(3)
					.hm(cacooldown)
					.skillUse(() -> {

	                    if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
	                    	p.damage(3);
	                    }
	                    
	                	
	                	
	                	if(smitet.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(smitet.get(p.getUniqueId()));
	                		smitet.remove(p.getUniqueId());
	                	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									smite.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 6); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								smite.remove(p.getUniqueId());
			                }
			            }, 45); 
	                	smitet.put(p.getUniqueId(), task);
						for(int i =0; i<3; i++) {
		                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	p.setCooldown(CAREFUL, 3);
					                    p.swingMainHand();
					                	ArrayList<Location> line = new ArrayList<Location>();
					                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
					                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
										if(ulton.containsKey(p.getUniqueId())) {
											p.getLocation().getWorld().spawnParticle(Particle.FLAME, p.getLocation(),50);
										}
					                    AtomicInteger j = new AtomicInteger(0);
					                    for(double d = 0; d <= 2.2; d += 0.1) {
						                    Location pl = p.getEyeLocation();
											pl.add(pl.clone().getDirection().normalize().multiply(d));
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
							             			p.teleport(i);
													p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 20, 3,3,3,0);
													p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, p.getLocation(), 20, 3,3,3,0);
							                    	for (Entity e : p.getNearbyEntities(3, 3, 3))
													{
							                    		if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) 
														{
															LivingEntity le = (LivingEntity)e;
															les.add(le);                    			
														}
													}
									            }
						                	   }, j.incrementAndGet()/50); 
										 });
				                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					             		@Override
							                	public void run() 
								                {	

					             					Location dl = p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(1.7));
						             				for(LivingEntity le: les) {
								                    	atk0(0.65, bsd.Crimpson.get(p.getUniqueId())*0.53, p, le);
														le.teleport(dl);
														Holding.superholding(p, le, 10l);
						             				}

									            }
				                	   }, j.incrementAndGet()/50); 
					                }
					            }, i*5); 
		                }
					});
			bd.execute();
        	

	            
			} // adding players name + current system time in miliseconds
            
		}}
	}
	

	
	public void Smite(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD")&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK) && p.isSneaking() &&smite.containsKey(p.getUniqueId()))
			{
                if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
                	p.damage(3);
                }
                
	        	p.setCooldown(CAREFUL, 3);
	        	ev.setCancelled(true);
            	if(smitet.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(smitet.get(p.getUniqueId()));
            		smitet.remove(p.getUniqueId());
            	}
				smite.remove(p.getUniqueId());

            	if(onslt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(onslt.get(p.getUniqueId()));
            		onslt.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							onsl.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 6); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						onsl.remove(p.getUniqueId());
	                }
	            }, 45); 
        		onslt.put(p.getUniqueId(), task);
				
				for(int fl = 0; fl <15; fl +=1) {
					if(p.getLocation().clone().add(0, -0.25, 0).getBlock().isPassable()) {
						p.teleport(p.getLocation().add(0, -0.25, 0));
					}
					else {
						break;
					}
				}
	        	p.setCooldown(CAREFUL, 3);
                p.swingMainHand();
            	p.setFallDistance(0);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 0.1f);
				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 0f);
				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1.0f, 2f);
				p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, p.getLocation(), 250, 3, 2, 3);
				p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 250, 3, 2, 3, Material.CRIMSON_HYPHAE.createBlockData());
				for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 4, 4, 4))
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
		                    	atk0(1.36, bsd.Crimpson.get(p.getUniqueId())*1.47, p, le);
								Holding.superholding(p, le, 25l);
								
							}
					}
				}
			}
		
		}
	}

	
	public void Onslaught(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD")&& (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK) && p.isSneaking() &&onsl.containsKey(p.getUniqueId()))
			{
                if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
                	p.damage(3);
                }
                
            	p.setCooldown(CAREFUL, 3);
				ev.setCancelled(true);
            	if(onslt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(onslt.get(p.getUniqueId()));
            		onslt.remove(p.getUniqueId());
            	}
				onsl.remove(p.getUniqueId());

	        	p.setCooldown(CAREFUL, 3);
                p.swingMainHand();
            	p.setFallDistance(0);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 0.1f);
				p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, p.getLocation(), 250, 3, 2, 3);
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GOAT_SCREAMING_LONG_JUMP, 0.5f, 2f);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 13, 20, false, false));

            	final Location tl = p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(1.7));

				for(int i =0; i<13; i++) {
                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.setNoDamageTicks(0);
			                	p.teleport(p.getLocation());
			    	        	p.setCooldown(CAREFUL, 3);
			                    p.swingMainHand();
			    				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.5f,2f);
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 2f);
								p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, tl, 100, 3, 2, 3);
								p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 50, 3,3,3); 
								for (Entity a : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
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
									if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")&& !(a.hasMetadata("portal"))) 
									{
										LivingEntity le = (LivingEntity)a;
				                    	atk0(0.26, bsd.Crimpson.get(p.getUniqueId())*0.28, p, le);
										le.teleport(tl);
									}
								}
			                }
			            }, i); 
                }
			}
		
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void Flurry(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 1&& bsd.Flurry.getOrDefault(p.getUniqueId(), 0)>=1) {
			
		if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !p.isSneaking() && !p.hasCooldown(CAREFUL) )
		{
		Action ac = ev.getAction();
		double sec = 9*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		if(!p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK))
		{

			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(sec)
					.kname("난무")
					.ename("Flurry")
					.slot(4)
					.hm(smcooldown)
					.skillUse(() -> {

	                    if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
	                    	p.damage(3);
	                    }
	                    
		            	if(nomt.containsKey(p.getUniqueId())) {
	                		Bukkit.getScheduler().cancelTask(nomt.get(p.getUniqueId()));
	                		nomt.remove(p.getUniqueId());
	                	}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								if(Proficiency.getpro(p)>=1) {
									nom.putIfAbsent(p.getUniqueId(), 0);
								}
			                }
			            }, 3); 

	            		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								nom.remove(p.getUniqueId());
			                }
			            }, 45); 
	                	nomt.put(p.getUniqueId(), task);
	                	
	                    ev.setCancelled(true);

		    			Location pl = p.getEyeLocation().clone();
		    			
						p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, pl, 100, 2,2,2);
						for(int i = 0; i<10; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			             		@Override
			                	public void run() 
				                {	
			                    	Location tl = p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(1.8));
			    	    			Location pl = p.getEyeLocation().clone();
				                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(0.6));
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.2f, 0f);
				                    p.playSound(p.getLocation(), Sound.ENTITY_HUSK_STEP, 0.1f, 1.5f);
		             				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl, 25, 1.5,1.5,1.5);
									for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2.5, 2.5, 2.5)) {
										if(e instanceof LivingEntity&&  !(e.hasMetadata("portal"))&&  !(e.hasMetadata("fake")) && e!=p) {
											LivingEntity le = (LivingEntity)e;
					                    	atk0(0.18, bsd.Flurry.get(p.getUniqueId())*0.2, p, le);
											le.teleport(tl);
										}
									}
				                	
					            }
	                	   	}, i*2);
						}
						
					});
			bd.execute();

	            
			} // adding players name + current system time in miliseconds
            
		}}
	}

	
	public void Merciless(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 1 && !p.hasCooldown(CAREFUL) ) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD")&& (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) && !p.isSneaking() &&nom.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
                if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
                	p.damage(3);
                }
                
            	
            	if(nomt.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(nomt.get(p.getUniqueId()));
            		nomt.remove(p.getUniqueId());
            	}
				nom.remove(p.getUniqueId());

            	if(crst.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(crst.get(p.getUniqueId()));
            		crst.remove(p.getUniqueId());
            	}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						if(Proficiency.getpro(p)>=2) {
							crs.putIfAbsent(p.getUniqueId(), 0);
						}
	                }
	            }, 6); 

        		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
						crs.remove(p.getUniqueId());
	                }
	            }, 45); 
        		crst.put(p.getUniqueId(), task);

        		final Location pl = p.getLocation();
        		
				for(int i =0; i<8; i++) {
                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {
			                	p.setNoDamageTicks(0);
			                	p.teleport(pl);
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 2f);
								p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 666, 6,6,6, 0); 
								for (Entity a : p.getWorld().getNearbyEntities(p.getLocation(), 6 , 6 , 6))
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
									if ((!(a == p))&& a instanceof LivingEntity&& !a.hasMetadata("fake")&& !(a.hasMetadata("portal"))) 
									{
										LivingEntity le = (LivingEntity)a;
										
				                    	atk0(0.44, bsd.Flurry.get(p.getUniqueId())*0.47, p, le);
				                    	try {
											le.teleport(le.getLocation().clone().add(pl.toVector().normalize().subtract(le.getLocation().clone().toVector().normalize()).normalize().multiply(1.4)));
				                    	}
				                    	catch(IllegalArgumentException e) {
											le.teleport(pl);
				                    	}
				                    	Holding.holding(p, le, 2l);
									}
								}
			                }
			            }, i*2); 
                }

			}
		
		}
	}

	public void CrimpsonSlash(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
	    Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 1 && !p.hasCooldown(CAREFUL) ) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD")&& (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) && !p.isSneaking() &&crs.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

                if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
                	p.damage(3);
                }
                
            	if(crst.containsKey(p.getUniqueId())) {
            		Bukkit.getScheduler().cancelTask(crst.get(p.getUniqueId()));
            		crst.remove(p.getUniqueId());
            	}
				crs.remove(p.getUniqueId());

            	final Location pl = p.getEyeLocation().clone().add(0, -0.45, 0);
            	
                ArrayList<Location> fill = new ArrayList<Location>();
                
                HashSet<LivingEntity> les = new HashSet<LivingEntity>();
                AtomicInteger j = new AtomicInteger();
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, 0.5f);
                p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.6f, 0.5f);
                p.playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 0.6f, 0.5f);
                for(double an = Math.PI; an>-Math.PI; an-=Math.PI/90) {
                    for(double i = 0.75; i<8.5;i+=0.3) {
                    	fill.add(pl.clone().add(pl.clone().getDirection().normalize().rotateAroundY(an).multiply(i)));
                    }
                }
                fill.forEach(l -> {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
			        		p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, l,5,0.1,0,0.1,0);
			        		p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,1,0.1,0,0.1,0);
		                }
					}, j.incrementAndGet()/900); 
                	
                });
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                    fill.forEach(l ->{
	                    	for(Entity e: l.getWorld().getNearbyEntities(l,1,4,1)) {
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
									les.add(le);
								}
	                    	}
	                    });
	                }
				}, j.incrementAndGet()/900); 

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
						for (LivingEntity le : les)
						{
    	        			atk1(1.535*(1+bsd.Flurry.get(p.getUniqueId())*0.0933), p, le);
									
						}
	                    
	                }
				}, j.incrementAndGet()/900); 

			}
		
		}
	}
	
	@SuppressWarnings("deprecation")
	public void Undying(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player && !d.isCancelled()) 
		{
			Player p1 = (Player)d.getEntity();
			if(ClassData.pc.get(p1.getUniqueId()) == 1) {
				double sec = 60*(1-p1.getAttribute(Attribute.LUCK).getValue()/1024d)- (Proficiency.getpro(p1)>=2 ? 40 : 0);

				if(Proficiency.getpro(p1)>=2) {
					if(p1.hasPotionEffect(PotionEffectType.REGENERATION)) {

						p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, p1.getPotionEffect(PotionEffectType.REGENERATION).getAmplifier()+1, false, false));
					}
					else {
						p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 0, false, false));
					}
				}
				
						
						if((p1.getHealth()<=d.getDamage() || d.getDamage()>= p1.getMaxHealth()) && d.getCause() != DamageCause.VOID) {
							
							SkillBuilder bd = new SkillBuilder()
									.player(p1)
									.cooldown(sec)
									.kname("불사")
									.ename("Undying")
									.slot(5)
									.hm(lncooldown)
									.skillUse(() -> {
						                d.setCancelled(true);
						                p1.playSound(p1.getLocation(), Sound.ITEM_BUCKET_FILL_LAVA, 1f, 0f);
						                p1.setHealth(p1.getMaxHealth()*0.05);
										p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 255, false, false));
										p1.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 80, 4, false, false));
										p1.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[UNDYING]").color(ChatColor.BOLD).color(ChatColor.RED).create());
									});
							bd.execute();
						}
					}
		}
	}
	

	

	public void ULT(PlayerItemHeldEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
	    if(!isCombat(p)) {
	    	return;
	    }

		ItemStack is = p.getInventory().getItemInMainHand();
		
			if(ClassData.pc.get(p.getUniqueId()) == 1&& ev.getNewSlot()==3&&
					(is.getType().name().contains("SWORD")) && p.isSneaking()&& Proficiency.getpro(p) >=1)
			{
				ev.setCancelled(true);
            	p.setCooldown(CAREFUL, 3);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(45/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("들끓는 심장")
						.ename("BloodBoil")
						.slot(6)
						.hm(sultcooldown)
						.skillUse(() -> {
							final Location l = p.getLocation();
		                    if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
		                    	p.damage(4);
		                    }
		                    
		                    if(ultont.containsKey(p.getUniqueId())) {
		                    	Bukkit.getScheduler().cancelTask(ultont.get(p.getUniqueId()));
		                    }
							for(int i =0; i<15; i++) {
			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() 
						                {
											p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 0.3f, 0.5f);
											p.getWorld().spawnParticle(Particle.LAVA, p.getLocation(), 50, 1,1,1); 
						                }
						            }, i*20); 
			                }
		                    Holding.invur(p, 40l);
		                    p.playSound(p.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1.0f, 0.1f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0f, 0.1f);
							p.getWorld().spawnParticle(Particle.FLASH, l, 10, 8, 2, 8);
							p.getWorld().spawnParticle(Particle.LAVA, l, 10, 8, 2, 8);
							p.getWorld().spawnParticle(Particle.DRIPPING_LAVA, l, 10, 8, 2, 8);
							p.getWorld().spawnParticle(Particle.FALLING_LAVA, l, 10, 8, 2, 8);
							ulton.put(p.getUniqueId(), true);
							p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 4, false, false));
							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	ulton.remove(p.getUniqueId());
				                	ultont.remove(p.getUniqueId());
				                }
				            }, 300); 
							ultont.put(p.getUniqueId(), task);
						});
				bd.execute();
				
			}
    }
	

	public void ULT2(PlayerItemHeldEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
	    if(!isCombat(p)) {
	    	return;
	    }
		ItemStack is = p.getInventory().getItemInMainHand();
			if(ClassData.pc.get(p.getUniqueId()) == 1 &&  ev.getNewSlot()==4&&(is.getType().name().contains("SWORD")) && p.isSneaking()&& Proficiency.getpro(p) >=2)
			{
				final Location psl = p.getLocation();
				final Location tl = gettargetblock(p,6).clone();
				
            	p.setCooldown(CAREFUL, 3);
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(60*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
						.kname("말살")
						.ename("Genocide")
						.slot(7)
						.hm(sult2cooldown)
						.skillUse(() -> {
							if(conv.getOrDefault(p.getUniqueId(), 0) == 1) {
		                    	p.damage(4);
		                    }
		                    
		                    Holding.invur(p, 100l);

		                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_MOOD, 1.0f, 2f);
		                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 2f);
		    				ArrayList<Location> line = new ArrayList<Location>();
		    				HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		                    for(double d = 0.1; d <= 5; d += 0.2) {
	    	                    Location pl = tl.clone();
	    						pl.add(0,d,0);
	    						line.add(pl);
		                    }
		    				for(int i = 0; i <22; i++) {
		                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    		                @Override
		    		                public void run() {
		    		                	p.teleport(psl);
		    		                	p.setCooldown(CAREFUL, 3);
					                    p.swingMainHand();
			    						p.getWorld().spawnParticle(Particle.BLOCK, tl, 1520, 8,0.3,8,0 ,Material.CRIMSON_NYLIUM.createBlockData());
			    						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 100, 8,0.3,8,0);
		    							if(ulton.containsKey(p.getUniqueId())) {
		    								p.getWorld().spawnParticle(Particle.DRIPPING_LAVA, tl, 2, 1, 1, 1);
		    							}
		    		                    p.playSound(p.getLocation(), Sound.ENTITY_FOX_SPIT, 1.0f, 2f);
		    		                    p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1.0f, 0f);
		    		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, 1.0f, 2f);
		    		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
		    		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2f);
		    							for(Entity e : p.getWorld().getNearbyEntities(tl,10, 10, 10)) {
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
		    			                    	atk1(0.3,  p, le);
		    	    							Holding.holding(p, le, 100l);
		    									les.add(le);
		    								}
		    							}
		    		                }
		    		            }, i*2);
		                    }
		    				
		    				ArrayList<Location> air = new ArrayList<Location>();
		    				ArrayList<Location> sight = new ArrayList<Location>();
		                    AtomicInteger j = new AtomicInteger(0);
		                    AtomicInteger k = new AtomicInteger(0);
		                    for(double i=Math.PI/180; i > -Math.PI; i -= Math.PI/180) {
		                    	Location eye = p.getEyeLocation().clone();
		                    	eye.setDirection(eye.clone().getDirection().multiply(-1));
		                    	Vector eyev = eye.getDirection().clone().rotateAroundY(Math.PI/2);
		                    	Location a = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, i).normalize());
		                    	Location b = eye.clone().add(eye.getDirection().rotateAroundAxis(eyev, i).normalize().multiply(6));
		                    	sight.add(a);
		                    	air.add(b);
		                    }
		                    air.forEach(l -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    		                @Override
		    		                public void run() {
			    						p.getWorld().spawnParticle(Particle.BLOCK, l, 5, 0.3,0.3,0.3,0 ,Material.CRIMSON_NYLIUM.createBlockData());
			    						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 2, 0.3,0.3,0.3,0);
		    							if(ulton.containsKey(p.getUniqueId())) {
		    								p.getWorld().spawnParticle(Particle.DRIPPING_LAVA, l, 1, 1, 1, 1);
		    							}
		    		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.05f, 2f);
					                    for(LivingEntity le: les) {
				    							le.teleport(l);
										}
		    		                }
		    		            }, j.incrementAndGet()/10+50);
		                    });
		                    sight.forEach(l -> {
		                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		    		                @Override
		    		                public void run() {
			    						p.teleport(l);
		    		                }
		    		            }, k.incrementAndGet()/10+50);
		                    });
		                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	p.setCooldown(CAREFUL, 3);
				                    p.swingMainHand();
				                	p.teleport(psl);
				    				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 0.1f);
				    				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 0f);
				    				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 2f);
				    				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1.0f, 2f);
				                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.35f, 2f);
				    				p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 2f);
				    				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 1.0f, 0f);
		    						p.getWorld().spawnParticle(Particle.BLOCK, tl, 5000, 8,8,8,0 ,Material.CRIMSON_NYLIUM.createBlockData());
		    						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 5000, 8,8,8,0);
		    						p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, tl, 5000, 8,8,8,0);
				                    for(LivingEntity le: les) {
				                    	atk1(11.5,  p, le);
		    							Holding.holding(p, le, 20l);
		    							le.teleport(tl);	
									}
				                }
				            }, j.incrementAndGet()/10+50);
						});
				bd.execute();
				
			}
    }
	

	
	public void ULT22(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 1 && (is.getType().name().contains("HOE")) && !p.isSneaking()&& p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				final Location psl = p.getLocation();
				final Location tl = gettargetblock(p,6).clone();
				p.setSneaking(true);
				ev.setCancelled(true);
         	   	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {			
		    				p.setSneaking(false);
		                }
		            }, 1); 
				if(sult2cooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
	            {
	                double timer = (sult2cooldown.get(p.getName())/1000d + 60*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d)) - System.currentTimeMillis()/1000d; // geting time in seconds
	                if(!(timer < 0)) // if timer is still more then 0 or 0
	                {
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("흡혈 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					    }
		        		else {
		                	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use Vampire").create());
		        		}
	                }
	                else // if timer is done
	                {
	                    sult2cooldown.remove(p.getName()); // removing player from HashMap
	                    Holding.invur(p, 100l);

	                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_MOOD, 1.0f, 2f);
	                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 2f);
	                    AtomicInteger j = new AtomicInteger(0);
	    				ArrayList<Location> line = new ArrayList<Location>();
	    				HashSet<LivingEntity> les = new HashSet<>();
	                    for(double d = 0.1; d <= 5; d += 0.2) {
		                    Location pl = tl.clone();
							pl.add(0,d,0);
							line.add(pl);
	                    }
	    				for(int i = 0; i <20; i++) {
	                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    		                @Override
	    		                public void run() {
	    		                	p.teleport(psl);
	    		                	p.setCooldown(CAREFUL, 3);
				                    p.swingMainHand();
		    						p.getWorld().spawnParticle(Particle.BLOCK, tl, 1520, 2,2,2,0 ,Material.CRIMSON_NYLIUM.createBlockData());
	    							if(ulton.containsKey(p.getUniqueId())) {
	    								p.getWorld().spawnParticle(Particle.DRIPPING_LAVA, tl, 2, 1, 1, 1);
	    							}
	    		                    p.playSound(p.getLocation(), Sound.ENTITY_FOX_SPIT, 1.0f, 2f);
	    		                    p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1.0f, 0f);
	    							for(Entity e : p.getWorld().getNearbyEntities(tl,10, 10, 10)) {
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
	    	    							Holding.holding(p, le, 100l);
	    									le.teleport(tl);
	    	             					les.add(le);
	    								}
	    							}
	    		                }
	    		            }, i*2);
	                    }

	                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                    les.forEach(le -> {
				                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2f);
			    							p.playSound(tl.clone(), Sound.BLOCK_PORTAL_TRAVEL, 0.35f, 2f);
					                    	le.teleport(tl);
				         					Item sn = tl.getWorld().dropItem(tl, new ItemStack(Material.RED_DYE));
				         					sn.setOwner(p.getUniqueId());
				         					sn.setGlowing(true);
				         					sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
				         					sn.setMetadata("vampire", new FixedMetadataValue(RMain.getInstance(),true));
				         					sn.setVelocity(p.getLocation().clone().toVector().subtract(tl.toVector()).normalize().multiply(0.5));
				         					p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,1, false,false));
						                }
						            }, j.incrementAndGet());
			                    });
			                }
			            }, 41);
	                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	p.setCooldown(CAREFUL, 3);
			                    p.swingMainHand();
			                	p.teleport(psl);
			    				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 0.1f);
			    				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 0f);
			    				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 2f);
			    				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1.0f, 2f);
			                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.35f, 2f);
			    				p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 2f);
			    				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 1.0f, 0f);
	    						p.getWorld().spawnParticle(Particle.BLOCK, tl, 5000, 8,8,8,0 ,Material.CRIMSON_NYLIUM.createBlockData());
	    						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 5000, 8,8,8,0);
	    						p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, tl, 5000, 8,8,8,0);
	    	                    les.forEach(le -> {
									atk0(0d,p.getLevel()*1.1, p, le);
	    							Holding.holding(p, le, 20l);
	    							le.teleport(tl);
	        	                    p.setAbsorptionAmount(p.getAbsorptionAmount()+0.5);
	    	                    });
			                }
			            }, 50+j.incrementAndGet());
		                sult2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            
	                }
	            }
	            else // if cooldown doesn't have players name in it
	            {
	            	Holding.invur(p, 100l);

                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_MOOD, 1.0f, 2f);
                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1.0f, 2f);
                    AtomicInteger j = new AtomicInteger(0);
    				ArrayList<Location> line = new ArrayList<Location>();
    				HashSet<LivingEntity> les = new HashSet<>();
                    for(double d = 0.1; d <= 5; d += 0.2) {
	                    Location pl = tl.clone();
						pl.add(0,d,0);
						line.add(pl);
                    }
    				for(int i = 0; i <20; i++) {
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    		                @Override
    		                public void run() {
    		                	p.teleport(psl);
    		                	p.setCooldown(CAREFUL, 3);
			                    p.swingMainHand();
	    						p.getWorld().spawnParticle(Particle.BLOCK, tl, 1520, 2,2,2,0 ,Material.CRIMSON_NYLIUM.createBlockData());
    							if(ulton.containsKey(p.getUniqueId())) {
    								p.getWorld().spawnParticle(Particle.DRIPPING_LAVA, tl, 2, 1, 1, 1);
    							}
    		                    p.playSound(p.getLocation(), Sound.ENTITY_FOX_SPIT, 1.0f, 2f);
    		                    p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1.0f, 0f);
    							for(Entity e : p.getWorld().getNearbyEntities(tl,10, 10, 10)) {
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
    	    							Holding.holding(p, le, 100l);
    									le.teleport(tl);
    	             					les.add(le);
    								}
    							}
    		                }
    		            }, i*2);
                    }

                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                    les.forEach(le -> {
			                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2f);
		    							p.playSound(tl.clone(), Sound.BLOCK_PORTAL_TRAVEL, 0.35f, 2f);
				                    	le.teleport(tl);
			         					Item sn = tl.getWorld().dropItem(tl, new ItemStack(Material.RED_DYE));
			         					sn.setOwner(p.getUniqueId());
			         					sn.setGlowing(true);
			         					sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
			         					sn.setMetadata("vampire", new FixedMetadataValue(RMain.getInstance(),true));
			         					sn.setVelocity(p.getLocation().clone().toVector().subtract(tl.toVector()).normalize().multiply(0.5));
			         					p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,1, false,false));
					                }
					            }, j.incrementAndGet());
		                    });
		                }
		            }, 41);
                	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	p.setCooldown(CAREFUL, 3);
		                    p.swingMainHand();
		                	p.teleport(psl);
		    				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 0.1f);
		    				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 0f);
		    				p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 2f);
		    				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1.0f, 2f);
		                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.35f, 2f);
		    				p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 2f);
		    				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 1.0f, 0f);
    						p.getWorld().spawnParticle(Particle.BLOCK, tl, 5000, 8,8,8,0 ,Material.CRIMSON_NYLIUM.createBlockData());
    						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 5000, 8,8,8,0);
    						p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, tl, 5000, 8,8,8,0);
    	                    les.forEach(le -> {
	                			atk0(0d,p.getLevel()*1.1, p, le);
								Holding.holding(p, le, 20l);
								le.teleport(tl);
	    	                    p.setAbsorptionAmount(p.getAbsorptionAmount()+0.5);
    	                    });
		                }
		            }, 50+j.incrementAndGet());
	                sult2cooldown.put(p.getName(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
			}
    }

	@SuppressWarnings("deprecation")
	public void Vampire(EntityPickupItemEvent ev) 
	{
		if(ev.getEntity() instanceof Player) {
			Player p = (Player) ev.getEntity();
			if(ev.getItem().hasMetadata("vampire") && ev.getItem().getOwner() == p.getUniqueId()) {
				p.playSound(p.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_MILK, 1.0f, 2f);
				for(int i = 0; i<ev.getItem().getItemStack().getAmount(); i++) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1,1, false,false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1,1, false,false));
					if(p.getHealth() >= p.getMaxHealth()) {
	                    p.setAbsorptionAmount(p.getAbsorptionAmount()+1.5);
					}
				}
				ev.setCancelled(true);
				ev.getItem().remove();
		}
		}
	}

	
	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();
		
			if(ClassData.pc.get(p.getUniqueId()) == 1 && (is.getType().name().contains("HOE") || is.getType().name().contains("SWORD")) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
			{
				ev.setCancelled(true);
			}
	
    }
	
	
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Player p = e.getPlayer();

		if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") || (p.getInventory().getItemInMainHand().getType().name().contains("HOE")))
		{
		    
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 1)
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
		    
			
			
			if(ClassData.pc.get(p.getUniqueId()) == 1) {

				if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD")&& p.getInventory().getItemInOffHand().getType().name().contains("HOE")&&!p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD)&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD)&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
				{
					player_damage.put(p.getName(), p.getAttribute(Attribute.ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
				}
				else if(p.getInventory().getItemInMainHand().getType().name().contains("HOE")&&p.getInventory().getItemInOffHand().getType().name().contains("SWORD")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
				{
					player_damage.put(p.getName(), p.getAttribute(Attribute.ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);
				}
				else {
					player_damage.put(p.getName(),0d);
				}
			}
		}
	}
	*/
	
	public void Lunacy(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) 
		{
		Player p = (Player)d.getDamager();
		LivingEntity le = (LivingEntity) d.getEntity();
		
		Integer con = conv.getOrDefault(p.getUniqueId(),0);
		
		if(ClassData.pc.get(p.getUniqueId()) == 1) {
			
			dset2(d, p,(1+bsd.Lunacy.get(p.getUniqueId())*0.04435) * (1 + 0.2*(con)),le,14);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 30, 1, false, false));
			if(con == 0) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
			}
			if(Proficiency.getpro(p)>=1) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
			}
			if(ulton.containsKey(p.getUniqueId())) {
				dset1(d, p, 1.3, 0.005,le,10);
				p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 1, false, false));
			}
		}
		}

		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) 
		{
			Projectile ar = (Projectile)d.getDamager();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				LivingEntity le = (LivingEntity) d.getEntity();
			    

				Integer con = conv.getOrDefault(p.getUniqueId(),0);
				
				if(ClassData.pc.get(p.getUniqueId()) == 1) {

					dset2(d, p,(1+bsd.Lunacy.get(p.getUniqueId())*0.04435) * (1 + 0.2*(con)),le,14);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 30, 1, false, false));
					if(con == 0) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
					}
					if(Proficiency.getpro(p)>=1) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, false, false));
					}
					if(ulton.containsKey(p.getUniqueId())) {
						dset1(d, p, 1.3, 0.005,le,10);
						p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 1, false, false));
					}
				}
			}
		}
	}
	
	
}