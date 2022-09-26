package io.github.chw3021.monsters.hyper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class HyperSkills extends Summoned{

	/**
	 * 
	 */
	private transient static final long serialVersionUID = 4263132774482281418L;
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb1cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb2cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb5cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Boolean> chargable = new HashMap<UUID, Boolean>();
	private HashMap<UUID, Boolean> scratchable = new HashMap<UUID, Boolean>();
	private HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();
	static public Multimap<String, Integer> ordt = ArrayListMultimap.create();
	private HashMap<UUID, Integer> guud = new HashMap<UUID, Integer>();
	

	
	private static final HyperSkills instance = new HyperSkills ();
	public static HyperSkills getInstance()
	{
		return instance;
	}
	

	public void RayCannon(EntitySpellCastEvent ev) 
	{

		if(ev.getEntity() instanceof Evoker  && ev.getEntity().hasMetadata("hyper")) 
		{
			Evoker p = (Evoker)ev.getEntity();
			
			ev.setCancelled(true);
			
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);

            final ArrayList<Location> line1 = new ArrayList<Location>();
            for(double d = 0.1; d <= 6.1; d += 0.5) {
                Location pl = p.getEyeLocation().clone();
				pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
				line1.add(pl);
            }
            for(final Location l : line1) {
            	p.getWorld().spawnParticle(Particle.FLASH, l, 1,0.1,0.1,0.1,0);
    			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() 
                    {
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.36f, 0.3f);
                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,10, 0.5,0.5,0.5,0, Material.GREEN_GLAZED_TERRACOTTA.createBlockData());
                    	p.getWorld().spawnParticle(Particle.GLOW, l,10, 0.5,0.5,0.5,0);

    					for(Entity e : p.getWorld().getNearbyEntities(l,1.25, 1.25, 1.25)) {
    						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
    							LivingEntity le = (LivingEntity)e;
    							le.damage(2,p);
    						}
    					}
    								
                    }
    			}, 40);
            }
		}
		
		if(ev.getEntity() instanceof Illusioner  && ev.getEntity().hasMetadata("hyperboss")) 
		{
			Illusioner p = (Illusioner)ev.getEntity();
			
			ev.setCancelled(true);
			
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);

            final ArrayList<Location> line1 = new ArrayList<Location>();
            for(double d = 0.1; d <= 35.1; d += 0.5) {
                Location pl = p.getEyeLocation().clone();
				pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
				line1.add(pl);
            }
            for(final Location l : line1) {
            	p.getWorld().spawnParticle(Particle.FLASH, l, 1,0.1,0.1,0.1,0);
    			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() 
                    {
                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,10, 0.5,0.5,0.5,0, Material.YELLOW_GLAZED_TERRACOTTA.createBlockData());
                    	p.getWorld().spawnParticle(Particle.WAX_ON, l,10, 0.5,0.5,0.5,0);

    					for(Entity e : p.getWorld().getNearbyEntities(l,1.5, 1.5, 1.5)) {
    						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
    							LivingEntity le = (LivingEntity)e;
    							le.damage(3,p);
    						}
    					}
    								
                    }
    			}, 20);
            }
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 2);
								
                }
			}, 20);
		}
	}
	
	public void CreepJumper(EntityTargetEvent ev) 
	{
		if((ev.getEntity() instanceof Creeper) && ev.getEntity().hasMetadata("hyper") && ev.getEntity().hasMetadata("CreepJumper")  && ev.getTarget() instanceof Player) 
		{
			Creeper p = (Creeper)ev.getEntity();
			final Player pe = (Player) ev.getTarget();

			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1f, 0.2f);
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1f, 0.2f);
			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 10,20,false,false));
			p.setVelocity(pe.getLocation().clone().toVector().subtract(p.getEyeLocation().clone().toVector()).normalize().multiply(1.45));
			
		}
	}

	public void Stalker(EntityTargetEvent ev) 
	{
		if((ev.getEntity() instanceof Husk) && ev.getEntity().hasMetadata("hyper") && ev.getEntity().hasMetadata("Stalker")  && ev.getTarget() instanceof Player) 
		{
			final Husk p = (Husk)ev.getEntity();
			final Player pe = (Player) ev.getTarget();
			final Location pel = pe.getLocation().clone();
			

			Holding.holding(null, p, 34l);
			p.getWorld().spawnParticle(Particle.PORTAL, pel, 100,1,1,1);
			p.getWorld().playSound(pel, Sound.ENTITY_ILLUSIONER_CAST_SPELL, 0.8f, 0f);
			
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	if(p.hasAI()) {
            			p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, pel, 100,1,1,1);
            			p.getWorld().playSound(pel, Sound.ENTITY_SHULKER_TELEPORT, 0.8f, 0.5f);
            			p.teleport(pel);
    					for(Entity e : p.getWorld().getNearbyEntities(pel,2, 2, 2)) {
    						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
    							LivingEntity le = (LivingEntity)e;
    							le.damage(2,p);
    						}
    					}
                	}
                }
            }, 35); 
			
			
		}
	}
	public void Granadier(ProjectileLaunchEvent d) 
	{
		if(d.getEntity().getShooter() instanceof Witch && d.getEntity() instanceof ThrownPotion) {
			Witch p = (Witch) d.getEntity().getShooter();
			if(p.hasMetadata("hyper") && p.hasMetadata("MadScientist")) {
				Snowball fr = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
				fr.setShooter(p);
                fr.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(0.68));
				fr.setMetadata("MadScientist", new FixedMetadataValue(RMain.getInstance(), true));
                fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                fr.setItem(new ItemStack(Material.SCULK_CATALYST));
				d.setCancelled(true);
				
			}
		}
	}
	public void Granadier(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof Snowball) 
		{
			Snowball po = (Snowball)d.getEntity();
			if(po.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) po.getShooter();
				if(po.hasMetadata("MadScientist")) {
					po.getWorld().playSound(po.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 0.1f);
					po.getWorld().spawnParticle(Particle.SCRAPE, po.getLocation(), 200,2,2,2,0.2);
					po.getWorld().spawnParticle(Particle.SONIC_BOOM, po.getLocation(), 5,2,2,2);
            		for(Entity e : p.getWorld().getNearbyEntities(po.getLocation(), 2.2, 2.2, 2.2)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(4,p);
							le.teleport(po.getLocation());
						}
                	}
				}
				
			}
			
		}
	}
	

	final private ArrayList<Location> RayBow(Location il){
    	ArrayList<Location> line = new ArrayList<Location>();
        for(double d = 0.1; d <= 13.1; d += 0.2) {
            Location pl = il.clone();
			pl.add(il.getDirection().normalize().multiply(d));
			line.add(pl);
        }
        return line;
	}
	
	
	public void RayBow(EntityShootBowEvent ev) 
	{
		if(ev.getEntity().hasMetadata("hyper") && ev.getEntity().hasMetadata("RayArcher")){

			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
        	final ArrayList<Location> line = RayBow(p.getEyeLocation().clone());

        	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1f, 2f);
            line.forEach(l -> {
            	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.005,0.005,0.005,0, Material.LIGHT.createBlockData());

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.005,0.005,0.005,0, Material.MAGENTA_STAINED_GLASS.createBlockData());

                        	for (Entity e : p.getWorld().getNearbyEntities(l, 0.25, 0.25, 0.25))
            				{
            					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
            						LivingEntity le = (LivingEntity)e;
            						le.damage(1,p);
            					}
            				}
                    }
                }, 10); 
            });
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 2f);
                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1f, 2f);
                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1f, 2f);
                }
            }, 10); 

		 }
	}

	public void RayBow(ProjectileLaunchEvent d) 
	{
		if(d.getEntity().getShooter() instanceof Illusioner && d.getEntity() instanceof Arrow) {
			Illusioner p = (Illusioner) d.getEntity().getShooter();
			if(p.hasMetadata("hyperboss")) {
	        	d.setCancelled(true);
	        	final ArrayList<Location> line = RayBow(p.getEyeLocation().clone());
                for(double an = -Math.PI/3; an<=Math.PI/3; an += Math.PI/6) {
    	        	line.addAll(RayBow(p.getEyeLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().rotateAroundY(an))));
                }
	        	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1f, 2f);
	            line.forEach(l -> {
	            	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.005,0.005,0.005,0, Material.LIGHT.createBlockData());

	            });

	            for(int i = 0; i<3; i++) {
		            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 2f);
		                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1f, 2f);
		                	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1f, 2f);
		                    line.forEach(l -> {
		                    	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.005,0.005,0.005,0, Material.MAGENTA_STAINED_GLASS.createBlockData());

		                    	for (Entity e : p.getWorld().getNearbyEntities(l, 0.25, 0.25, 0.25))
		        				{
		        					if(p!=e && e instanceof LivingEntity) {
		        						LivingEntity le = (LivingEntity)e;
		        						le.damage(2.35,p);
		        					}
		        				}
		                    });
		                }
		            }, 10+i*2); 
	            }
				
			}
		}
	}


	public void EnergyBall(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 5;
		if(d.getEntity() instanceof Illusioner && d.getEntity().hasMetadata("hyperboss") ) 
		{
			Illusioner p = (Illusioner)d.getEntity();
			if(rb3cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb3cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	            	rb3cooldown.remove(p.getUniqueId()); 
					p.getWorld().playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 2f);
					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SCULK_SENSOR_CLICKING, 1.0f, 0.6f);
					p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 100, 1,1,1);
					p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, p.getLocation(), 100, 1,1,1);
	                Holding.holding(null, p, 20l);
	                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
	                	public void run() 
		                {	
	        	            Snowball sn = p.launchProjectile(Snowball.class);
	        	            sn.setBounce(false);
	        	            sn.setGravity(false);
	        	            sn.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.16));
	        	            sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	        	            sn.setShooter(p);
	        	            ItemStack eb = new ItemStack(Material.RAW_IRON_BLOCK);
	        	            eb.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
	        	            sn.setItem(eb);
	        	            sn.setGlowing(true);
	        	            
	        	                    	for(int i =0; i<20; i++) {
	        			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	        						                @Override
	        						                public void run() 
	        						                {
	        						                    p.getWorld().playSound(sn.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 0.055f, 2.0f);
	        						                    sn.getWorld().spawnParticle(Particle.PORTAL, sn.getLocation(), 10,1,1,1);
	        						                    sn.getWorld().spawnParticle(Particle.FLASH, sn.getLocation(), 10,1,1,1);
	        						                	for (Entity e : p.getWorld().getNearbyEntities(sn.getLocation(), 2.5, 2.5, 2.5))
	        											{
        						        					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
        						        						LivingEntity le = (LivingEntity)e;
        						        						le.damage(2.2,p);
        						        					}
	        											}
	        						                }
	        			                	   }, i*3); 
	        							}
	        	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	        				                @Override
	        				                public void run() 
	        				                {
	        				                	Holding.ale(sn).remove();
	        				                }
	        	                    	}, 62);
			            }
	        	   	}, 20);
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {
				p.getWorld().playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 2f);
				p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SCULK_SENSOR_CLICKING, 1.0f, 0.6f);
				p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 100, 1,1,1);
				p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, p.getLocation(), 100, 1,1,1);
                Holding.holding(null, p, 20l);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
        	            Snowball sn = p.launchProjectile(Snowball.class);
        	            sn.setBounce(false);
        	            sn.setGravity(false);
        	            sn.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.16));
        	            sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
        	            sn.setShooter(p);
        	            ItemStack eb = new ItemStack(Material.RAW_IRON_BLOCK);
        	            eb.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
        	            sn.setItem(eb);
        	            sn.setGlowing(true);
        	            
        	                    	for(int i =0; i<20; i++) {
        			                	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
        						                @Override
        						                public void run() 
        						                {
        						                    p.getWorld().playSound(sn.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 0.055f, 2.0f);
        						                    sn.getWorld().spawnParticle(Particle.PORTAL, sn.getLocation(), 10,1,1,1);
        						                    sn.getWorld().spawnParticle(Particle.FLASH, sn.getLocation(), 10,1,1,1);
        						                	for (Entity e : p.getWorld().getNearbyEntities(sn.getLocation(), 2.5, 2.5, 2.5))
        											{
    						        					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
    						        						LivingEntity le = (LivingEntity)e;
    						        						le.damage(2.2,p);
    						        					}
        											}
        						                }
        			                	   }, i*3); 
        							}
        	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
        				                @Override
        				                public void run() 
        				                {
        				                	Holding.ale(sn).remove();
        				                }
        	                    	}, 62);
		            }
        	   	}, 20);
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}
	


	public void JetPack(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 8;
		if(d.getEntity() instanceof Illusioner && d.getEntity().hasMetadata("hyperboss") ) 
		{
			Illusioner p = (Illusioner)d.getEntity();
			if(rb4cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb4cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	            	rb4cooldown.remove(p.getUniqueId()); 
		        	p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, p.getLocation(), 100, 1, 2, 1);
	                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 0);
	            	p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 11, false, false));
					rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {
	        	p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, p.getLocation(), 100, 1, 2, 1);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 0);
            	p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 11, false, false));
				rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}


	public void JetPack(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Illusioner && d.getEntity().hasMetadata("hyperboss") ) 
		{
			Illusioner p = (Illusioner)d.getEntity();
			if(d.getCause().equals(DamageCause.FALL)) 
			{
	        	p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 65*(int)p.getFallDistance(), p.getFallDistance(), 1, p.getFallDistance(), p.getLocation().add(0, -1, 0).getBlock().getBlockData());
	        	for(Entity e: p.getNearbyEntities(p.getFallDistance()/2, 2, p.getFallDistance()/2)) {

					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
						LivingEntity le = (LivingEntity)e;
						le.damage(0.1*p.getFallDistance(),p);
					}
	        	}
	        	
	        	d.setCancelled(true);
			}		
		}
	
	}
	

	final private void GravityShift(LivingEntity p, Location tl) {
		if(rb5cooldown.containsKey(p.getUniqueId()))
        {
            long timer = (rb5cooldown.get(p.getUniqueId())/1000 + 12) - System.currentTimeMillis()/1000; 
            if(!(timer < 0))
            {
            }
            else 
            {
            	rb5cooldown.remove(p.getUniqueId()); 

    			p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, tl, 200, 1,1,1);
    			p.getWorld().spawnParticle(Particle.PORTAL, tl, 200, 1,1,1);
    			p.getWorld().playSound(tl, Sound.BLOCK_PORTAL_TRAVEL, 0.35f, 2f);
    			p.getWorld().playSound(tl, Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 0.85f, 2f);
    			Holding.holding(null, p, 30l);
    			Holding.invur(p, 30l);
    			
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                    	p.teleport(tl);
            			Holding.holding(null, p, 10l);
        				p.getWorld().playSound(tl, Sound.ENTITY_SHULKER_TELEPORT, 0.85f, 0.15f);
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                    			p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, tl, 200, 1,1,1);
                    			p.getWorld().spawnParticle(Particle.FLASH, tl, 200, 5,5,5);
                				p.getWorld().playSound(tl, Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 0.85f, 2f);
            					for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),5, 5, 5)) {
            						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
            							LivingEntity le = (LivingEntity)e;
            							le.damage(4);
            		                    le.teleport(p);
            		                    Holding.holding(null, le, 40l);
            						}
            					}
                            }
                        }, 10);
                    }
                }, 30);
				rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
            }
        }
        else 
        {

			p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, tl, 200, 1,1,1);
			p.getWorld().spawnParticle(Particle.PORTAL, tl, 200, 1,1,1);
			p.getWorld().playSound(tl, Sound.BLOCK_PORTAL_TRAVEL, 0.35f, 2f);
			p.getWorld().playSound(tl, Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 0.85f, 2f);
			Holding.holding(null, p, 30l);
			Holding.invur(p, 30l);
			
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	p.teleport(tl);
        			Holding.holding(null, p, 10l);
    				p.getWorld().playSound(tl, Sound.ENTITY_SHULKER_TELEPORT, 0.85f, 0.15f);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                			p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, tl, 200, 1,1,1);
                			p.getWorld().spawnParticle(Particle.FLASH, tl, 200, 5,5,5);
            				p.getWorld().playSound(tl, Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 0.85f, 2f);
        					for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),5, 5, 5)) {
        						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
        							LivingEntity le = (LivingEntity)e;
        							le.damage(4);
        		                    le.teleport(p);
        		                    Holding.holding(null, le, 40l);
        						}
        					}
                        }
                    }, 10);
                }
            }, 30);
			rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
        }
		
	}

	@SuppressWarnings("unchecked")
	public void GravityShift(EntityDamageByEntityEvent d) 
	{
	   
		if(d.getEntity() instanceof Illusioner && d.getEntity().hasMetadata("hyperboss") ) 
		{
			final Illusioner p = (Illusioner)d.getEntity();
			if(p.hasMetadata("raid")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				GravityShift(p,tl);
			}
			else if(p.hasMetadata("summoned")) {
				final Object hero = getherotype(gethero(p));
				if(hero instanceof Player) {
					final Player hp = (Player)hero;
					GravityShift(p,hp.getLocation());
				}
				else if(hero instanceof HashSet) {
					HashSet<Player> par = (HashSet<Player>) hero;
					GravityShift(p,par.stream().findAny().get().getLocation());
				}

			}
		}					
	}


	
	public void SecurityRobot(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Vindicator)&& !d.isCancelled() &&d.getEntity().hasMetadata("hyper")) 
		{
			d.getEntity().getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_ARMOR_STAND_BREAK, 1, 0);
		}

			
	}

	@SuppressWarnings("deprecation")
	public void Throwing(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 9;
		if(d.getEntity() instanceof Husk && d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("hyperboss") ) 
		{
			Husk p = (Husk)d.getEntity();
			if(p.hasMetadata("failed")) {
				return;
			}
			if((p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getMaxHealth()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
				return;
			}
			if(rb3cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb3cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	            	rb3cooldown.remove(p.getUniqueId()); 
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HUSK_AMBIENT, 1.0f, 2f);
					p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 100, 1,1,1);
	                Holding.holding(null, p, 20l);
	                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
	                	public void run() 
		                {	
	    	     			chargable.putIfAbsent(p.getUniqueId(), true);
	                        for(double an = -Math.PI/3; an<=Math.PI/3; an += Math.PI/9) {
	            				Snowball fr = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
	            				fr.setShooter(p);
	                            fr.setVelocity(p.getEyeLocation().getDirection().clone().normalize().rotateAroundY(an).multiply(0.5));
	            				fr.setMetadata("grenadier", new FixedMetadataValue(RMain.getInstance(), true));
	                            fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                            fr.setItem(new ItemStack(Material.COBBLED_DEEPSLATE));                	
	                        }
			            }
	        	   	}, 20);
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HUSK_AMBIENT, 1.0f, 2f);
				p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 100, 1,1,1);
                Holding.holding(null, p, 20l);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
             			chargable.putIfAbsent(p.getUniqueId(), true);
                        for(double an = -Math.PI/3; an<=Math.PI/3; an += Math.PI/9) {
            				Snowball fr = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
            				fr.setShooter(p);
                            fr.setVelocity(p.getEyeLocation().getDirection().clone().normalize().rotateAroundY(an).multiply(0.5));
            				fr.setMetadata("grenadier", new FixedMetadataValue(RMain.getInstance(), true));
                            fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                            fr.setItem(new ItemStack(Material.COBBLED_DEEPSLATE));                	
                        }
		            }
        	   	}, 20);
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}
	
	
	@SuppressWarnings("deprecation")
	public void Charging(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 6;
		if(d.getEntity() instanceof Husk && d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("hyperboss") ) 
		{
			Husk p = (Husk)d.getEntity();
			String rn = p.getMetadata("raid").get(0).asString();
			if(p.hasMetadata("failed")) {
				return;
			}
			if((p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getMaxHealth()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
				return;
			}
			if(!chargable.containsKey(p.getUniqueId()) || !OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))) {
				return;
			}
			if(rb2cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb2cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	            	rb2cooldown.remove(p.getUniqueId()); 
	    			Location pl = p.getEyeLocation().clone();
	    			final float py = p.getEyeLocation().getYaw();
	    			final float pp = p.getEyeLocation().getPitch();
	            	chargable.remove(p.getUniqueId());
	                scratchable.remove(p.getUniqueId());
					p.getWorld().playSound(pl, Sound.ENTITY_HUSK_AMBIENT, 1.0f, 0f);
					p.getWorld().playSound(pl, Sound.ENTITY_ZOGLIN_ANGRY, 1.0f, 0f);
					p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, pl, 100, 2,2,2);
	                Holding.holding(null, p,20l);
					for(int i = 0; i<10; i++) {
	                    int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		             		@Override
		                	public void run() 
			                {	
		    	    			Location pl = p.getEyeLocation().clone();
		             			p.setRotation(py, pp);
		             			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1,1,false,false));
			                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(1.2));
			                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
			                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, 1.0f, 0f);
			                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HUSK_STEP, 1.0f, 0f);
	    	                    p.getWorld().playSound(pl, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.1f, 1.5f);
	             				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl, 5, 1.5,1.5,1.5);
	             				p.getWorld().spawnParticle(Particle.PORTAL, pl, 30, 1,1,1);
								for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2.5, 2.5, 2.5)) {
									if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
										LivingEntity le = (LivingEntity)e;
										le.damage(5,p);
										le.teleport(p);
									}
								}
			                	
				            }
                	   	}, i*2 + 20);
						ordt.put(rn, t);
					}
	                int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		         		@Override
		            	public void run() 
		                {
		         			ordealable.put(p.getUniqueId(), true);
			            }
	        	   	}, 20);
	                int t2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		         		@Override
		            	public void run() 
		                {
		         			scratchable.putIfAbsent(p.getUniqueId(), true);
			            }
	        	   	}, 150);
					ordt.put(rn, t);
					ordt.put(rn, t2);
					rb2cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {
    			Location pl = p.getEyeLocation().clone();
    			final float py = p.getEyeLocation().getYaw();
    			final float pp = p.getEyeLocation().getPitch();
            	chargable.remove(p.getUniqueId());
                scratchable.remove(p.getUniqueId());
				p.getWorld().playSound(pl, Sound.ENTITY_HUSK_AMBIENT, 1.0f, 0f);
				p.getWorld().playSound(pl, Sound.ENTITY_ZOGLIN_ANGRY, 1.0f, 0f);
				p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, pl, 100, 2,2,2);
                Holding.holding(null, p,20l);
				for(int i = 0; i<10; i++) {
                    int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
	                	public void run() 
		                {	
	    	    			Location pl = p.getEyeLocation().clone();
	             			p.setRotation(py, pp);
	             			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1,1,false,false));
		                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(1.2));
		                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
		                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, 1.0f, 0f);
		                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HUSK_STEP, 1.0f, 0f);
    	                    p.getWorld().playSound(pl, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.1f, 1.5f);
             				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl, 5, 1.5,1.5,1.5);
             				p.getWorld().spawnParticle(Particle.PORTAL, pl, 30, 1,1,1);
							for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2.5, 2.5, 2.5)) {
								if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
									LivingEntity le = (LivingEntity)e;
									le.damage(5,p);
									le.teleport(p);
								}
							}
		                	
			            }
            	   	}, i*2 + 20);
					ordt.put(rn, t);
				}
                int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	         		@Override
	            	public void run() 
	                {
	         			ordealable.put(p.getUniqueId(), true);
		            }
        	   	}, 20);
                int t2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	         		@Override
	            	public void run() 
	                {
	         			scratchable.putIfAbsent(p.getUniqueId(), true);
		            }
        	   	}, 150);
				ordt.put(rn, t);
				ordt.put(rn, t2);
	            rb2cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}

	@SuppressWarnings("deprecation")
	public void Scraching(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 18;
		if(d.getEntity() instanceof Husk && d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("hyperboss")) 
		{
			Husk p = (Husk)d.getEntity();
			if(p.hasMetadata("failed")) {
				return;
			}
			if((p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getMaxHealth()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
				return;
			}
			if(!scratchable.containsKey(p.getUniqueId()) || chargable.containsKey(p.getUniqueId()) || !OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))) {
				return;
			}
			String rn = p.getMetadata("raid").get(0).asString();
			final Location tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation();
			if(rb1cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb1cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	                rb1cooldown.remove(p.getUniqueId()); 
	                chargable.remove(p.getUniqueId());
	                scratchable.remove(p.getUniqueId());
					p.getWorld().playSound(tl, Sound.ENTITY_HUSK_AMBIENT, 1.0f, 0f);
					p.getWorld().playSound(tl, Sound.ENTITY_HUSK_CONVERTED_TO_ZOMBIE, 1.0f, 0f);
					p.getWorld().spawnParticle(Particle.PORTAL, tl, 300, 2,2,2);
					p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, tl, 300, 1,1,1);
	                Holding.holding(null, p, 25l);
					for(int i = 0; i<20; i++) {
	                    int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             		@Override
	                	public void run() 
		                {	
	             			p.teleport(tl);
							p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HUSK_AMBIENT, 0.15f, 2f);
							p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.15f, 2f);
							p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 50, 2.5,2.5,2.5);
             					for(Entity e: tl.getWorld().getNearbyEntities(tl,2.8, 2.8, 2.8)) {
            						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
            							LivingEntity le = (LivingEntity)e;
            							le.damage(2,p);
            							le.teleport(tl);
        				                Holding.holding(null, le, 3l);
            						}
             					}
				            }
                	   	}, i*3 + 25);
						ordt.put(rn, t);
					}
		            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {
                chargable.remove(p.getUniqueId());
                scratchable.remove(p.getUniqueId());
				p.getWorld().playSound(tl, Sound.ENTITY_HUSK_AMBIENT, 1.0f, 0f);
				p.getWorld().playSound(tl, Sound.ENTITY_HUSK_CONVERTED_TO_ZOMBIE, 1.0f, 0f);
				p.getWorld().spawnParticle(Particle.PORTAL, tl, 300, 2,2,2);
				p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, tl, 300, 1,1,1);
                Holding.holding(null, p, 30l);
				for(int i = 0; i<20; i++) {
                    int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
             			p.teleport(tl);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HUSK_AMBIENT, 0.15f, 2f);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.15f, 2f);
						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 50, 2.5,2.5,2.5);
         					for(Entity e: tl.getWorld().getNearbyEntities(tl,2.8, 2.8, 2.8)) {
        						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
        							LivingEntity le = (LivingEntity)e;
        							le.damage(2,p);
        							le.teleport(tl);
    				                Holding.holding(null, le, 3l);
        						}
         					}
			            }
            	   	}, i*3 + 30);
					ordt.put(rn, t);
				}
	            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}
	
	
	final private void Ordeal(final Husk h, Location rl, Integer f, String rn) {
		Husk p = (Husk) Holding.ale(h);
		HashSet<Location> mls1 = new HashSet<>();
		if(f%2 ==0) {
			for(int i = 0; i<30; i++) {
				mls1.add(rl.clone().add(i, 0, 0));
				mls1.add(rl.clone().add(-i, 0, 0));
				mls1.add(rl.clone().add(0, 0, i));
				mls1.add(rl.clone().add(0, 0, -i));
			}
		}
		else {
			for(int i = 0; i<30; i++) {
				mls1.add(rl.clone().add(i, 0, i));
				mls1.add(rl.clone().add(-i, 0, i));
				mls1.add(rl.clone().add(-i, 0, i));
				mls1.add(rl.clone().add(-i, 0, -i));
			}
		}
    	rl.getWorld().playSound(rl, Sound.BLOCK_BEACON_ACTIVATE, 1f, 2f);
    	mls1.forEach(l -> {
    		rl.getWorld().spawnParticle(Particle.FLASH, l,20, 0.5,0.5,0.5,0);

        });

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	rl.getWorld().playSound(rl, Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 2f);
            	rl.getWorld().playSound(rl, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1f, 2f);
            	rl.getWorld().playSound(rl, Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1f, 2f);
            	mls1.forEach(l -> {
                	rl.getWorld().spawnParticle(Particle.BLOCK_CRACK, l,35, 0.5,0.5,0.5,0, Material.LIME_STAINED_GLASS.createBlockData());
            		rl.getWorld().spawnParticle(Particle.GLOW, l,20, 0.5,0.5,0.5,0);

                	for (Entity e : rl.getWorld().getNearbyEntities(l, 1, 2,1))
    				{
                		if(e == Holding.ale(p) || e.getUniqueId() == p.getUniqueId()) {
			                guud.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
		                	p.playEffect(EntityEffect.HURT);
			                for(Player pe : OverworldRaids.getheroes(p)) {
		    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
			                		pe.sendMessage(ChatColor.BOLD+"타격횟수: "+(10-guud.getOrDefault(p.getUniqueId(),0) + "/10"));
		    					}
		    					else {
			                		pe.sendMessage(ChatColor.BOLD+"Hit: "+(10-guud.getOrDefault(p.getUniqueId(),0) + "/10"));
		    					}
			                }
			                if(guud.getOrDefault(p.getUniqueId(),0) <=0) {
			                    if(ordt.containsKey(rn)) {
			                    	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
			                    	ordt.removeAll(rn);
			                    }
			                    guud.remove(p.getUniqueId());
			                	p.playEffect(EntityEffect.HURT);
		                		Holding.reset(p);
		                		Holding.ale(p).setInvisible(false);
			                	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
		                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
		                		Holding.ale(p).setInvulnerable(false);
		                		Holding.holding(null, Holding.ale(p), 300l);
		    		            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
				                for(Player pe : OverworldRaids.getheroes(p)) {
			    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
				                		pe.sendMessage(ChatColor.BOLD+"더비스트: 이런 건방진 것들!");
			    					}
			    					else {
				                		pe.sendMessage(ChatColor.BOLD+"TheBeast: How dare are you!");
			    					}
			                		Holding.holding(pe, Holding.ale(p), 300l);
			                		p.removeMetadata("fake", RMain.getInstance());
			                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
				                }
					            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
					            int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	p.removeMetadata("failed", RMain.getInstance());
				                		Holding.ale(p).setInvisible(false);
				                		Holding.ale(p).setInvulnerable(false);
				                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
					                }
					            }, 300);
								ordt.put(rn, t3);
	                			break;
			                }
			                continue;
                		}
    					if(p!=e && e instanceof LivingEntity) {
    						LivingEntity le = (LivingEntity)e;
    						le.damage(2.5,p);
    					}
    				}
                });
            }
        }, 10); 
	}

	@SuppressWarnings("deprecation")
	public void Ordeal(EntityDamageByEntityEvent d) 
	{
	    
		int sec =70;
		if(d.getEntity() instanceof Husk && !d.isCancelled() && d.getEntity().hasMetadata("hyperboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
		{
			Husk p = (Husk)d.getEntity();
			if(!(p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2) || !ordealable.containsKey(p.getUniqueId())|| !OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))) {
				return;
			}
            final Location rl = OverworldRaids.getraidloc(p).clone();
				if(rb6cooldown.containsKey(p.getUniqueId()))
		        {
		            long timer = (rb6cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		            if(!(timer < 0))
		            {
		            }
		            else 
		            {
		                rb6cooldown.remove(p.getUniqueId()); 
		                d.setCancelled(true);
						p.setHealth(p.getMaxHealth()*0.2);
		                scratchable.remove(p.getUniqueId());
		            	chargable.remove(p.getUniqueId());
		                

		                Holding.untouchable(p, 40l);
		                Holding.invur(p, 40l);
		                guud.put(p.getUniqueId(), 10);
		                
		    			String rn = p.getMetadata("raid").get(0).asString();
	                    if(ordt.containsKey(rn)) {
	                    	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
	                    	ordt.removeAll(rn);
	                    }
	                    p.teleport(rl.clone());
		                
		                for(Entity e : OverworldRaids.getheroes(p)) {
		                	if(e instanceof Player ) {
		                		Player pe = (Player) e;
		                		Holding.invur(pe, 40l);
		    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
			                		pe.sendMessage(ChatColor.BOLD+"더비스트: 이것도 막아보아라!");
		    					}
		    					else {
			                		pe.sendMessage(ChatColor.BOLD+"TheBeast: You can't stop me!");
		    					}
								pe.getWorld().playSound(pe.getLocation(), Sound.ENTITY_WOLF_GROWL, 0.3f, 0);
								if(pe.getWorld() == p.getWorld()) {
									pe.teleport(rl.clone());
								}
		                	}
		                }
	                    AtomicInteger j = new AtomicInteger();
	                    int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {

								for(int i = 0; i <30; i++) {
									int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
							                Holding.untouchable(p, 20l);
							                Holding.invur(p, 20l);

							                for(Player pe : OverworldRaids.getheroes(p)) {
						                		if(pe.getWorld().equals(p.getWorld()) && rl.clone().distance(p.getLocation()) > 30 && pe.getWorld() == p.getWorld()) {
							    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
								                		pe.sendMessage(ChatColor.BOLD+"더비스트: 원위치!");
							    					}
							    					else {
								                		pe.sendMessage(ChatColor.BOLD+"TheBeast: You Can't Get Away!");
							    					}
						                    		pe.teleport(rl);
						                		}
							                }
							                Ordeal(p,rl.clone(),j.getAndIncrement(),rn);
						                }
						            }, i*20); 	 
									ordt.put(rn, t1);
			                    }
								for(int i = 0; i <20; i++) {
									int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
							                if(OverworldRaids.getheroes(Holding.ale(p)).stream().anyMatch(pe -> pe.getWorld().equals(Holding.ale(p).getWorld()))) {
							        			final Location tl = OverworldRaids.getheroes(Holding.ale(p)).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getEyeLocation();

												p.getWorld().playSound(tl, Sound.ENTITY_HUSK_AMBIENT, 1.0f, 0f);
												p.getWorld().playSound(tl, Sound.ENTITY_HUSK_CONVERTED_TO_ZOMBIE, 1.0f, 0f);
												p.getWorld().spawnParticle(Particle.PORTAL, tl, 100, 2,2,2);
												for(int i = 0; i<10; i++) {
								                    int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								             		@Override
								                	public void run() 
									                {	
								             			Holding.ale(p).teleport(tl);
														p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HUSK_AMBIENT, 0.15f, 2f);
														p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.15f, 2f);
														p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 50, 2.5,2.5,2.5);
							             					for(Entity e: tl.getWorld().getNearbyEntities(tl,2.8, 2.8, 2.8)) {
							            						if(Holding.ale(p)!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
							            							LivingEntity le = (LivingEntity)e;
							            							le.damage(10,Holding.ale(p));
							            							le.teleport(tl);
							        				                Holding.holding(null, le, 3l);
							            						}
							             					}
											            }
							                	   	}, i*2 + 25);
													ordt.put(rn, t);
												}
							                }
						                }
						            }, i*30); 	 
									ordt.put(rn, t1);
			                    
			                                	
								}
			                }
			            }, 40);
						ordt.put(rn, t1);
	                    int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				                for(Player pe : OverworldRaids.getheroes(p)) {
			                		if(pe.getWorld().equals(p.getWorld()) && pe.getWorld() == p.getWorld()) {
				    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
					                		pe.sendMessage(ChatColor.BOLD+"더비스트: 진화는 필연적인 것이다..");
				    					}
				    					else {
					                		pe.sendMessage(ChatColor.BOLD+"TheBeast: Evolution is inevitable..");
				    					}
				            			p.getWorld().playSound(pe.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 0);
				                		Holding.invur(pe, 60l);
					            		pe.teleport(p);
					            		pe.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 255 ,false,false));
					            		pe.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 60, 255 ,false,false));
			                		}
				                }
				 				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {	
						                rb6cooldown.remove(p.getUniqueId()); 
				                		p.removeMetadata("fake", RMain.getInstance());
				                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
				    	                p.setInvulnerable(false);
				    	                Holding.ale(p).setInvulnerable(false);
										p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 100, 10,10,10);	
										p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 3000, 10,10,10);	
					                    for(Player pe : OverworldRaids.getheroes(p)) {
					                		p.removeMetadata("fake", RMain.getInstance());
					            			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
					                		pe.setHealth(0);
					                    }
					                }
					            }, 60); 
				 				
			                }
			            }, 640);
						ordt.put(rn, t3);
			            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		            }
		        }
		        else 
		        {
	                d.setCancelled(true);
					p.setHealth(p.getMaxHealth()*0.2);
	                scratchable.remove(p.getUniqueId());
	            	chargable.remove(p.getUniqueId());
	                

	                Holding.untouchable(p, 40l);
	                Holding.invur(p, 40l);
	                guud.put(p.getUniqueId(), 10);
	                
	    			String rn = p.getMetadata("raid").get(0).asString();
                    if(ordt.containsKey(rn)) {
                    	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
                    	ordt.removeAll(rn);
                    }
                    p.teleport(rl.clone());
	                for(Entity e : OverworldRaids.getheroes(p)) {
	                	if(e instanceof Player) {
	                		Player pe = (Player) e;
	                		Holding.invur(pe, 40l);
	    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
		                		pe.sendMessage(ChatColor.BOLD+"더비스트: 이것도 막아보아라!");
	    					}
	    					else {
		                		pe.sendMessage(ChatColor.BOLD+"TheBeast: You can't stop me!");
	    					}
							pe.getWorld().playSound(pe.getLocation(), Sound.ENTITY_WOLF_GROWL, 0.3f, 0);
							if(pe.getWorld() == p.getWorld()) {
								pe.teleport(rl.clone());
							}
	                	}
	                }
                    AtomicInteger j = new AtomicInteger();
                    int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

							for(int i = 0; i <30; i++) {
								int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						                Holding.untouchable(p, 20l);
						                Holding.invur(p, 20l);

						                for(Player pe : OverworldRaids.getheroes(p)) {
					                		if(pe.getWorld().equals(p.getWorld()) && rl.clone().distance(p.getLocation()) > 30 && pe.getWorld() == p.getWorld()) {
						    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
							                		pe.sendMessage(ChatColor.BOLD+"더비스트: 원위치!");
						    					}
						    					else {
							                		pe.sendMessage(ChatColor.BOLD+"TheBeast: You Can't Get Away!");
						    					}
					                    		pe.teleport(rl);
					                		}
						                }
						                Ordeal(p,rl.clone(),j.getAndIncrement(),rn);
					                }
					            }, i*20); 	 
								ordt.put(rn, t1);
		                    }
							for(int i = 0; i <20; i++) {
								int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						                if(OverworldRaids.getheroes(Holding.ale(p)).stream().anyMatch(pe -> pe.getWorld().equals(Holding.ale(p).getWorld()))) {
						        			final Location tl = OverworldRaids.getheroes(Holding.ale(p)).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getEyeLocation();

											p.getWorld().playSound(tl, Sound.ENTITY_HUSK_AMBIENT, 1.0f, 0f);
											p.getWorld().playSound(tl, Sound.ENTITY_HUSK_CONVERTED_TO_ZOMBIE, 1.0f, 0f);
											p.getWorld().spawnParticle(Particle.PORTAL, tl, 100, 2,2,2);
											for(int i = 0; i<10; i++) {
							                    int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							             		@Override
							                	public void run() 
								                {	
							             			Holding.ale(p).teleport(tl);
													p.getWorld().playSound(p.getLocation(), Sound.ENTITY_HUSK_AMBIENT, 0.15f, 2f);
													p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.15f, 2f);
													p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 50, 2.5,2.5,2.5);
						             					for(Entity e: tl.getWorld().getNearbyEntities(tl,2.8, 2.8, 2.8)) {
						            						if(Holding.ale(p)!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
						            							LivingEntity le = (LivingEntity)e;
						            							le.damage(10,Holding.ale(p));
						            							le.teleport(tl);
						        				                Holding.holding(null, le, 3l);
						            						}
						             					}
										            }
						                	   	}, i*2 + 25);
												ordt.put(rn, t);
											}
						                }
					                }
					            }, i*30); 	 
								ordt.put(rn, t1);
		                    
		                                	
							}
		                }
		            }, 40);
					ordt.put(rn, t1);
                    int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
			                for(Player pe : OverworldRaids.getheroes(p)) {
		                		if(pe.getWorld().equals(p.getWorld()) && pe.getWorld() == p.getWorld()) {
			    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
				                		pe.sendMessage(ChatColor.BOLD+"더비스트: 진화는 필연적인 것이다..");
			    					}
			    					else {
				                		pe.sendMessage(ChatColor.BOLD+"TheBeast: Evolution is inevitable..");
			    					}
			            			p.getWorld().playSound(pe.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 0);
			                		Holding.invur(pe, 60l);
				            		pe.teleport(p);
				            		pe.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 255 ,false,false));
				            		pe.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 60, 255 ,false,false));
		                		}
			                }
			 				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {	
					                rb6cooldown.remove(p.getUniqueId()); 
			                		p.removeMetadata("fake", RMain.getInstance());
			                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
			    	                p.setInvulnerable(false);
			    	                Holding.ale(p).setInvulnerable(false);
									p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 100, 10,10,10);	
									p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 3000, 10,10,10);	
				                    for(Player pe : OverworldRaids.getheroes(p)) {
				                		p.removeMetadata("fake", RMain.getInstance());
				            			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
				                		pe.setHealth(0);
				                    }
				                }
				            }, 60); 
			 				
		                }
		            }, 640);
					ordt.put(rn, t3);
		            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		        }
			}
	}
		
	
	/*
	
	public void Ring(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 5;
		if((d.getDamager() instanceof Skeleton||d.getDamager() instanceof ElderGuardian) && d.getEntity() instanceof LivingEntity&& !d.isCancelled() &&d.getDamager().hasMetadata("redboss")) 
		{
			LivingEntity p = (LivingEntity)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			final Location tl = le.getLocation().clone();
						if(rb1cooldown.containsKey(p.getUniqueId()))
				        {
				            long timer = (rb1cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
				            if(!(timer < 0))
				            {
				            }
				            else 
				            {
				                rb1cooldown.remove(p.getUniqueId()); 
				                p.getWorld().strikeLightningEffect(le.getLocation());
								for(int i = 0; i <10; i++) {
				                    AtomicInteger j = new AtomicInteger();	
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
							            	ArrayList<Location> ring = new ArrayList<Location>();
											p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
						                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
						                    	Location one = tl.clone();
						                    	one.setDirection(one.getDirection().rotateAroundY(angle));
						                    	one.add(one.getDirection().normalize().multiply(3.5));
						                    	ring.add(one);
						                	} 
						                	ring.forEach(l -> {
						                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() {
														p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 6, 0.5,0.5,0.5,0);		
									                }
									            }, j.incrementAndGet()/60); 
						                		
						                	});
						                }
						            }, i*5); 	                    	
			                    }
								for(int i = 0; i <10; i++) {
				                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											for(Entity e : p.getWorld().getNearbyEntities(tl,3.5, 3.5, 3.5)) {
												if(p!=e && e instanceof Player) {
													LivingEntity le = (LivingEntity)e;
													le.damage(3,p);	
													if(le.getLocation().distance(tl)>=3.4) {
														le.teleport(le.getLocation());
													}	
												}
											}
						                }
						            }, i*5); 	                    	
			                    }
					            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				            }
				        }
				        else 
				        {
				        	p.getWorld().strikeLightningEffect(le.getLocation());
							for(int i = 0; i <10; i++) {
			                    AtomicInteger j = new AtomicInteger();	
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
						            	ArrayList<Location> ring = new ArrayList<Location>();
										p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
					                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/90) {
					                    	Location one = tl.clone();
					                    	one.setDirection(one.getDirection().rotateAroundY(angle));
					                    	one.add(one.getDirection().normalize().multiply(3.5));
					                    	ring.add(one);
					                	} 
					                	ring.forEach(l -> {
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 6, 0.5,0.5,0.5,0);				                    
								                }
								            }, j.incrementAndGet()/60); 
					                		
					                	});
					                }
					            }, i*5); 	                    	
		                    }
							for(int i = 0; i <10; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(Entity e : p.getWorld().getNearbyEntities(tl,3.5, 3.5, 3.5)) {
											if(p!=e && e instanceof Player) {
												LivingEntity le = (LivingEntity)e;
												le.damage(3,p);	
												if(le.getLocation().distance(tl)>=3.4) {
													le.teleport(le.getLocation());
												}	
											}
										}
					                }
					            }, i*5); 	                    	
		                    }
				            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
				        }
		}					
	}
	


	
	public void Eruption(EntityTargetEvent ev) 
	{
		if((ev.getEntity() instanceof Skeleton||ev.getEntity() instanceof ElderGuardian) && ev.getEntity().hasMetadata("redboss")  && ev.getTarget() instanceof Player) 
		{
			LivingEntity p = (LivingEntity)ev.getEntity();
			Player le = (Player)ev.getTarget();
			int sec = 9;
	        
			
			
				final Location tl = le.getLocation();
					
					if(rb3cooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (rb3cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                    rb3cooldown.remove(p.getUniqueId()); 
			            	ArrayList<Location> line = new ArrayList<Location>();
		                    le.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
		                    le.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
		                    for(double d = 0.1; d <= 2.5; d += 0.2) {
			                    for(double an = 0; an <= Math.PI*2; an += Math.PI/90) {
				                    Location pl = tl.clone();
									pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(d));
									line.add(pl);
			                    }
		                    }
		                    line.forEach(l ->  {	
	             				p.getWorld().spawnParticle(Particle.SOUL, l, 1, 0.1,0.1,0.1,0);
		                    });
	                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                		@Override
			                	public void run() 
				                {	
	    		                    line.forEach(l ->  {	
			             				p.getWorld().spawnParticle(Particle.LAVA, l, 1, 1,1,1,5);
	    		                    });
		    	                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
		    	                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_MAGMA_CUBE_SQUISH, 1.0f, 0f);
		    	                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 0f);
		    	                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1.0f, 0f);
					            }
	                    	}, 30); 

							for(int i = 0; i <8; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										for(Entity e : p.getWorld().getNearbyEntities(tl,3, 3, 3)) {
											if(e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
						                    	le.teleport(le.getLocation().clone().add(0, 0.5, 0));
												le.damage(1,p);		
											}
										}
					                }
					            }, i*2+30);
		                    }
							ev.setCancelled(true);
							rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
		            	ArrayList<Location> line = new ArrayList<Location>();
	                    le.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0f, 2f);
	                    le.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
	                    for(double d = 0.1; d <= 2.5; d += 0.2) {
		                    for(double an = 0; an <= Math.PI*2; an += Math.PI/90) {
			                    Location pl = tl.clone();
								pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(d));
								line.add(pl);
		                    }
	                    }
	                    line.forEach(l ->  {	
             				p.getWorld().spawnParticle(Particle.SOUL, l, 1, 0.1,0.1,0.1,0);
	                    });
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                		@Override
		                	public void run() 
			                {	
    		                    line.forEach(l ->  {	
		             				p.getWorld().spawnParticle(Particle.LAVA, l, 1, 1,1,1,5);
    		                    });
	    	                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
	    	                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_MAGMA_CUBE_SQUISH, 1.0f, 0f);
	    	                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 0f);
	    	                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1.0f, 0f);
				            }
                    	}, 30); 

						for(int i = 0; i <8; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									for(Entity e : p.getWorld().getNearbyEntities(tl,3, 3, 3)) {
										if(e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
											LivingEntity le = (LivingEntity)e;
					                    	le.teleport(le.getLocation().clone().add(0, 0.5, 0));	
											le.damage(1,p);		
										}
									}
				                }
				            }, i*2+30);
	                    }
						ev.setCancelled(true);
						rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}
	

	

	
	public void Fireball(EntityTargetEvent ev) 
	{
		if((ev.getEntity() instanceof Skeleton||ev.getEntity() instanceof ElderGuardian) && ev.getEntity().hasMetadata("redboss") && ev.getTarget() instanceof Player) 
		{
			LivingEntity p = (LivingEntity)ev.getEntity();
			int sec = 4;

		
			
					
					if(rb4cooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (rb4cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                    rb4cooldown.remove(p.getUniqueId()); 
		                    if(fbt.containsKey(p.getUniqueId())) {
		                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
		                    }
							int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        	                @Override
	        	                public void run() 
	        	                {
	        	                	if(p.isValid()) {
		        	                    Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
		        	                    fb.setYield(0.1f);
		        	                    fb.setGlowing(true);
		        	                    fb.setBounce(false);
		        	                    fb.setShooter(p);
		        	                    fb.setVelocity(p.getLocation().getDirection().normalize().multiply(1.3));
		        	                    fb.setIsIncendiary(false);
		        						fb.setMetadata("redbfb", new FixedMetadataValue(RMain.getInstance(), true));
	        	                	}
	        	                }
	        				}, 0, 40);
							fbt.put(p.getUniqueId(), task);
							rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
	                    if(fbt.containsKey(p.getUniqueId())) {
	                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
	                    }
						int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
        	                @Override
        	                public void run() 
        	                {
        	                	if(p.isValid()) {
	        	                    Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
	        	                    fb.setYield(0.1f);
	        	                    fb.setGlowing(true);
	        	                    fb.setBounce(false);
	        	                    fb.setShooter(p);
	        	                    fb.setVelocity(p.getLocation().getDirection().normalize().multiply(1.3));
	        	                    fb.setIsIncendiary(false);
	        						fb.setMetadata("redbfb", new FixedMetadataValue(RMain.getInstance(), true));
        	                	}
        	                }
        				}, 0, 40);
						fbt.put(p.getUniqueId(), task);
						
						rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	
	public void MageFB(EntitySpellCastEvent ev) 
	{
		if(ev.getEntity() instanceof Illusioner && ev.getEntity().hasMetadata("red") && ev.getEntity().hasMetadata("mage")) 
		{
			Illusioner p = (Illusioner)ev.getEntity();
			
			ev.setCancelled(true);
            Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
            fb.setYield(0.1f);
            fb.setGlowing(true);
            fb.setShooter(p);
            fb.setVelocity(p.getLocation().getDirection().normalize().multiply(1.5));
            fb.setIsIncendiary(false);
			fb.setMetadata("redfb", new FixedMetadataValue(RMain.getInstance(), true));
							
		}
		if(ev.getEntity() instanceof Evoker && ev.getEntity().hasMetadata("red") && ev.getEntity().hasMetadata("mage")) 
		{
			Evoker p = (Evoker)ev.getEntity();
			
			ev.setCancelled(true);
        	if(p.hasMetadata("leader")) {
                Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
                fb.setYield(0.1f);
                fb.setGlowing(true);
                fb.setShooter(p);
                fb.setVelocity(p.getLocation().getDirection().normalize().multiply(3));
                fb.setIsIncendiary(false);
				fb.setMetadata("redfb", new FixedMetadataValue(RMain.getInstance(), true));
        	}
            Fireball fb = (Fireball) p.launchProjectile(Fireball.class);
            fb.setYield(0.1f);
            fb.setGlowing(true);
            fb.setShooter(p);
            fb.setVelocity(p.getLocation().getDirection().normalize().multiply(1.5));
            fb.setIsIncendiary(false);
			fb.setMetadata("redfb", new FixedMetadataValue(RMain.getInstance(), true));
							
		}
	}
	
	
	public void Fireball(EntityExplodeEvent ev) 
	{
        
		
		if(ev.getEntity() instanceof Fireball) {
			Fireball fb = (Fireball)ev.getEntity();
			if(fb.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) fb.getShooter();
				if(fb.hasMetadata("redfb")) {
					ev.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, ev.getLocation(), 2,1,1,1);
					p.getWorld().playSound(ev.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					for(Entity n : fb.getNearbyEntities(2.3, 2.3, 2.3)) {
						if(n!=p && n instanceof LivingEntity&& !(n.hasMetadata("raid"))) {
							LivingEntity le = (LivingEntity)n;
							le.damage(3,p);	
						}
					}
					ev.setCancelled(true);
				}
				if(fb.hasMetadata("redbfb")) {
					ev.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, ev.getLocation(), 2,1,1,1);
					p.getWorld().playSound(ev.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					for(Entity n : fb.getNearbyEntities(3, 3, 3)) {
						if(n!=p && n instanceof LivingEntity&& !(n.hasMetadata("raid"))) {
							LivingEntity le = (LivingEntity)n;
							le.damage(3,p);	
						}
					}
					ev.setCancelled(true);
				}
			}
			
		}
	}

	
	public void Fireball(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("redboss")) {
			if(fbt.containsKey(d.getEntity().getUniqueId())) {
				Bukkit.getScheduler().cancelTask(fbt.get(d.getEntity().getUniqueId()));
			}
		}
	}
	

	
	public void Breath(EntityTargetEvent ev) 
	{
		if(ev.getEntity() instanceof ElderGuardian && ev.getEntity().hasMetadata("redboss") && ev.getEntity().hasMetadata("ruined") && ev.getTarget() instanceof Player) 
		{
			ElderGuardian p = (ElderGuardian)ev.getEntity();
			int sec = 2;
	        
			
					if(rb5cooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (rb5cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                    rb5cooldown.remove(p.getUniqueId()); 


							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 3, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, false, false));
							for(int i = 0; i <6; i++) {
			                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                    ArrayList<Location> cir = new ArrayList<Location>();
					                    AtomicInteger j = new AtomicInteger();
					                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
				                    	for(double angley= -Math.PI/5; angley<Math.PI/5; angley += Math.PI/45) {
				                        	Location one = p.getLocation();
					                    	one.setDirection(one.getDirection().rotateAroundY(angley));
						                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/45) {
						                    	one.setDirection(one.getDirection().rotateAroundAxis(p.getLocation().getDirection(),angle));
					                    		for(double i = 0.1; i<4.4;i+=0.5) {
					                    			Location two = one.clone();
						                    		two.add(two.getDirection().normalize().multiply(i));
							                    	cir.add(two);		                    			
					                    		}
						                    }
				                    	}	
										p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1.2f);
										p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 0);
					                	cir.forEach(l -> {
					                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
													p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l.add(0, -0.3, 0), 1, 0.2,0.2,0.2,0.5);
													for(Entity e : p.getWorld().getNearbyEntities(l,1, 1, 1)) {
														if(p!=e && e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
															LivingEntity le = (LivingEntity)e;
															les.add(le);	
														}
													}
								                }
								            }, j.incrementAndGet()/2000); 
					                	});
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
							                    for(LivingEntity le: les) {
													le.damage(3,p);			                    	
							                    }
							                }
							            }, j.incrementAndGet()/2000); 
					                }
					            }, i*5); 	                    	
		                    }
							ev.setCancelled(true);
							rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {


						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 3, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 3, false, false));
						for(int i = 0; i <6; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                    ArrayList<Location> cir = new ArrayList<Location>();
				                    AtomicInteger j = new AtomicInteger();
				                    HashSet<LivingEntity> les = new HashSet<LivingEntity>();
			                    	for(double angley= -Math.PI/5; angley<Math.PI/5; angley += Math.PI/45) {
			                        	Location one = p.getLocation();
				                    	one.setDirection(one.getDirection().rotateAroundY(angley));
					                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/45) {
					                    	one.setDirection(one.getDirection().rotateAroundAxis(p.getLocation().getDirection(),angle));
				                    		for(double i = 0.1; i<4.4;i+=0.5) {
				                    			Location two = one.clone();
					                    		two.add(two.getDirection().normalize().multiply(i));
						                    	cir.add(two);		                    			
				                    		}
					                    }
			                    	}	
									p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1.2f);
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 0);
				                	cir.forEach(l -> {
				                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l.add(0, -0.3, 0), 1, 0.2,0.2,0.2,0.5);
												for(Entity e : p.getWorld().getNearbyEntities(l,1, 1, 1)) {
													if(p!=e && e instanceof LivingEntity&&  !(e.hasMetadata("portal"))) {
														LivingEntity le = (LivingEntity)e;
														les.add(le);	
													}
												}
							                }
							            }, j.incrementAndGet()/2000); 
				                	});
			                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
						                    for(LivingEntity le: les) {
												le.damage(3,p);			                    	
						                    }
						                }
						            }, j.incrementAndGet()/2000); 
				                }
				            }, i*5); 	                    	
	                    }
						ev.setCancelled(true);
						rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}
	
	@SuppressWarnings("deprecation")
	
	public void Meteor(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 35;
		if(d.getEntity() instanceof ElderGuardian&& !d.isCancelled() && d.getEntity().hasMetadata("redboss") && d.getEntity().hasMetadata("ruined")) 
		{
			ElderGuardian p = (ElderGuardian)d.getEntity();
			if(!rb2cooldown.containsKey(p.getUniqueId()) ||(p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2)) {
				return;
			}
			if(p.hasMetadata("failed")) {
				return;
			}
				if(rb7cooldown.containsKey(p.getUniqueId()))
		        {
		            long timer = (rb7cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		            if(!(timer < 0))
		            {
		            }
		            else 
		            {
		                rb7cooldown.remove(p.getUniqueId()); 
			        	d.setCancelled(true);
	                    if(fbt.containsKey(p.getUniqueId())) {
	                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
	                    }
		                Location rl = OceanRaids.getraidloc(p).clone();
		    			String rn = p.getMetadata("raid").get(0).asString();
	                    p.teleport(rl.add(0, 1, 0));
	                    final Location tl = p.getLocation();
		                
		                for(Entity e : OceanRaids.getheroes(p)) {
		                	if(e instanceof Player && !e.hasMetadata("fake")) {
		                		Player pe = (Player) e;
		                		Holding.invur(pe, 40l);
		                		pe.sendMessage("RedKnight: You can't change your destiny.");
								pe.getWorld().playSound(pe.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.3f, 2);
								if(pe.getWorld() == p.getWorld()) {
									pe.teleport(tl);
								}
		                	}
		                }
	                    AtomicInteger j = new AtomicInteger();	
						for(int i = 0; i <5; i++) {
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
					            	ArrayList<Location> ring = new ArrayList<Location>();
									p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);

				                	for(double an = 0; an<Math.PI*2; an +=Math.PI/180) {
				                		ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an).multiply(an)).add(0, an+j.get(), 0));
				                	}
				                	ring.forEach(l -> {
										tl.getWorld().spawnParticle(Particle.ASH, l, 5, 0.5,0.5,0.5,0);
										tl.getWorld().spawnParticle(Particle.SMOKE_NORMAL, l, 2, 0.5,0.5,0.5,0);
										tl.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 2, 0.5,0.5,0.5,0);
				                		
				                	});
				                	j.incrementAndGet();
				                }
				            }, i*5); 	                    	
	                    }
						ArrayList<Location> mls1 = new ArrayList<>();
						ArrayList<Location> mls2 = new ArrayList<>();
						ArrayList<Location> mls3 = new ArrayList<>();
						ArrayList<Location> mls4 = new ArrayList<>();
						for(int i = 0; i<15; i++) {
							mls1.add(rl.clone().add(i, 7, 0));
							mls2.add(rl.clone().add(-i, 7, 0));
							mls3.add(rl.clone().add(0, 7, i));
							mls4.add(rl.clone().add(0, 7, -i));
						}

						for(int i = 0; i<15; i++) {
							mls1.add(rl.clone().add(i, 7, i));
							mls2.add(rl.clone().add(-i, 7, i));
							mls3.add(rl.clone().add(-i, 7, -i));
							mls4.add(rl.clone().add(i, 7, -i));
						}


						for(int i = 0; i<15; i++) {
		                	Random r1 = new Random();
		                	Random r2 = new Random();
		                	Random r3 = new Random();
		                	Random r4 = new Random();
		                	double rd1 = r1.nextDouble()*20 *(r3.nextBoolean()?1:-1);
		                	double rd2 = r2.nextDouble()*20*(r4.nextBoolean()?1:-1);
							mls1.add(rl.clone().add(rd1, 7, rd2));
							mls2.add(rl.clone().add(rd1, 7, rd2));
							mls3.add(rl.clone().add(rd1, 7, rd2));
							mls4.add(rl.clone().add(rd1, 7, rd2));
						}
		                Holding.holding(null, p, 60l);
		                Holding.invur(p, 60l);
						
						int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				                ordealable.put(p.getUniqueId(), true);
				                AtomicInteger j1 = new AtomicInteger();
				                AtomicInteger j2 = new AtomicInteger();
				                AtomicInteger j3 = new AtomicInteger();
				                AtomicInteger j4 = new AtomicInteger();
			                	mls1.forEach(l -> {
			                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
											fallingb.setInvulnerable(true);
											fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setVisualFire(true);
											fallingb.setDropItem(true);
											fallingb.setHurtEntities(true);
											magma.put(fallingb.getUniqueId(), p);
						                }
			                		}, j1.getAndIncrement()*4);
									ordt.put(rn, t1);
			                	});
			                	mls2.forEach(l -> {
			                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
											fallingb.setInvulnerable(true);
											fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setVisualFire(true);
											fallingb.setDropItem(true);
											fallingb.setHurtEntities(true);
											magma.put(fallingb.getUniqueId(), p);
						                }
			                		},j2.getAndIncrement()*4);
									ordt.put(rn, t1);
			                	});
			                	mls3.forEach(l -> {
			                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
											fallingb.setInvulnerable(true);
											fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setVisualFire(true);
											fallingb.setDropItem(true);
											fallingb.setHurtEntities(true);
											magma.put(fallingb.getUniqueId(), p);
						                }
			                		},j3.getAndIncrement()*4);
									ordt.put(rn, t1);
			                	});
			                	mls4.forEach(l -> {
			                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
											fallingb.setInvulnerable(true);
											fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
											fallingb.setVisualFire(true);
											fallingb.setDropItem(true);
											fallingb.setHurtEntities(true);
											magma.put(fallingb.getUniqueId(), p);
						                }
			                		},j4.getAndIncrement()*4);
									ordt.put(rn, t1);
			                	});
								for(int i = 0; i <45; i++) {
				                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
							                for(Player pe : OceanRaids.getheroes(p)) {
						                		if(pe.getWorld().equals(p.getWorld()) && rl.clone().distance(p.getLocation()) > 20 && pe.getWorld() == p.getWorld()) {
						                    		pe.sendMessage(ChatColor.DARK_RED+"You Can't Get Away!");
						                    		pe.teleport(rl);
						                		}
							                }
							            	ArrayList<Location> ring = new ArrayList<Location>();
							            	HashSet<Player> hp = new HashSet<>();
						                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/180) {
						                    	Location one = rl.setDirection(rl.getDirection()).clone();
						                    	one.setDirection(one.getDirection().rotateAroundY(angle));
						                    	one.add(one.getDirection().normalize().multiply(15));
						                    	ring.add(one);
						                	} 
						                	ring.forEach(l -> {
												p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 15, 0.5,0.7,0.5,0);
												for(Entity e : p.getWorld().getNearbyEntities(l,0.5, 0.67, 0.5)) {
													if(e instanceof Player && e!=p) {
														Player pe = (Player)e;
														hp.add(pe);
													}
												}
						                		
						                	});
											hp.forEach(pe -> pe.damage(5,p));
						                }
						            }, i*4); 	 
									ordt.put(rn, t1);                   	
			                    }
			                }
			            }, 40);
						ordt.put(rn, t1);  
	                    rb7cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		            }
		        }
		        else 
		        {
		        	d.setCancelled(true);
                    if(fbt.containsKey(p.getUniqueId())) {
                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
                    }
	                Location rl = OceanRaids.getraidloc(p).clone();
	    			String rn = p.getMetadata("raid").get(0).asString();
                    p.teleport(rl.add(0, 1, 0));
                    final Location tl = p.getLocation();

	                for(Entity e : OceanRaids.getheroes(p)) {
	                	if(e instanceof Player && !e.hasMetadata("fake")) {
	                		Player pe = (Player) e;
	                		Holding.invur(pe, 40l);
	                		pe.sendMessage("RedKnight: You can't change your destiny.");
							pe.getWorld().playSound(pe.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.3f, 2);
							if(pe.getWorld() == p.getWorld()) {
								
							pe.teleport(tl);
							}
	                	}
	                }
                    AtomicInteger j = new AtomicInteger();	
					for(int i = 0; i <5; i++) {
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				            	ArrayList<Location> ring = new ArrayList<Location>();
								p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);

			                	for(double an = 0; an<Math.PI*2; an +=Math.PI/180) {
			                		ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an).multiply(an)).add(0, an+j.get(), 0));
			                	}
			                	ring.forEach(l -> {
									tl.getWorld().spawnParticle(Particle.ASH, l, 5, 0.5,0.5,0.5,0);
									tl.getWorld().spawnParticle(Particle.SMOKE_NORMAL, l, 2, 0.5,0.5,0.5,0);
									tl.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 2, 0.5,0.5,0.5,0);
			                		
			                	});
			                	j.incrementAndGet();
			                }
			            }, i*5); 	                    	
                    }
					ArrayList<Location> mls1 = new ArrayList<>();
					ArrayList<Location> mls2 = new ArrayList<>();
					ArrayList<Location> mls3 = new ArrayList<>();
					ArrayList<Location> mls4 = new ArrayList<>();
					for(int i = 0; i<15; i++) {
						mls1.add(rl.clone().add(i, 7, 0));
						mls2.add(rl.clone().add(-i, 7, 0));
						mls3.add(rl.clone().add(0, 7, i));
						mls4.add(rl.clone().add(0, 7, -i));
					}

					for(int i = 0; i<15; i++) {
						mls1.add(rl.clone().add(i, 7, i));
						mls2.add(rl.clone().add(-i, 7, i));
						mls3.add(rl.clone().add(-i, 7, -i));
						mls4.add(rl.clone().add(i, 7, -i));
					}


					for(int i = 0; i<15; i++) {
	                	Random r1 = new Random();
	                	Random r2 = new Random();
	                	Random r3 = new Random();
	                	Random r4 = new Random();
	                	double rd1 = r1.nextDouble()*20 *(r3.nextBoolean()?1:-1);
	                	double rd2 = r2.nextDouble()*20*(r4.nextBoolean()?1:-1);
						mls1.add(rl.clone().add(rd1, 7, rd2));
						mls2.add(rl.clone().add(rd1, 7, rd2));
						mls3.add(rl.clone().add(rd1, 7, rd2));
						mls4.add(rl.clone().add(rd1, 7, rd2));
					}
	                Holding.holding(null, p, 60l);
	                Holding.invur(p, 60l);
					
					int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
			                ordealable.put(p.getUniqueId(), true);
			                AtomicInteger j1 = new AtomicInteger();
			                AtomicInteger j2 = new AtomicInteger();
			                AtomicInteger j3 = new AtomicInteger();
			                AtomicInteger j4 = new AtomicInteger();
		                	mls1.forEach(l -> {
		                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
										fallingb.setInvulnerable(true);
										fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setVisualFire(true);
										fallingb.setDropItem(true);
										fallingb.setHurtEntities(true);
										magma.put(fallingb.getUniqueId(), p);
					                }
		                		}, j1.getAndIncrement()*4);
								ordt.put(rn, t1);
		                	});
		                	mls2.forEach(l -> {
		                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
										fallingb.setInvulnerable(true);
										fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setVisualFire(true);
										fallingb.setDropItem(true);
										fallingb.setHurtEntities(true);
										magma.put(fallingb.getUniqueId(), p);
					                }
		                		},j2.getAndIncrement()*4);
								ordt.put(rn, t1);
		                	});
		                	mls3.forEach(l -> {
		                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
										fallingb.setInvulnerable(true);
										fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setVisualFire(true);
										fallingb.setDropItem(true);
										fallingb.setHurtEntities(true);
										magma.put(fallingb.getUniqueId(), p);
					                }
		                		},j3.getAndIncrement()*4);
								ordt.put(rn, t1);
		                	});
		                	mls4.forEach(l -> {
		                		int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										FallingBlock fallingb = p.getWorld().spawnFallingBlock(l, Material.RED_GLAZED_TERRACOTTA.createBlockData());
										fallingb.setInvulnerable(true);
										fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("redknightmagma"+rn, new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setVisualFire(true);
										fallingb.setDropItem(true);
										fallingb.setHurtEntities(true);
										magma.put(fallingb.getUniqueId(), p);
					                }
		                		},j4.getAndIncrement()*4);
								ordt.put(rn, t1);
		                	});
							for(int i = 0; i <45; i++) {
			                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
										p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
						                for(Player pe : OceanRaids.getheroes(p)) {
					                		if(pe.getWorld().equals(p.getWorld()) && rl.clone().distance(p.getLocation()) > 20 && pe.getWorld() == p.getWorld()) {
					                    		pe.sendMessage(ChatColor.DARK_RED+"You Can't Get Away!");
					                    		pe.teleport(rl);
					                		}
						                }
						            	ArrayList<Location> ring = new ArrayList<Location>();
						            	HashSet<Player> hp = new HashSet<>();
					                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/180) {
					                    	Location one = rl.setDirection(rl.getDirection()).clone();
					                    	one.setDirection(one.getDirection().rotateAroundY(angle));
					                    	one.add(one.getDirection().normalize().multiply(15));
					                    	ring.add(one);
					                	} 
					                	ring.forEach(l -> {
											p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 15, 0.5,0.7,0.5,0);
											for(Entity e : p.getWorld().getNearbyEntities(l,0.5, 0.67, 0.5)) {
												if(e instanceof Player && e!=p) {
													Player pe = (Player)e;
													hp.add(pe);
												}
											}
					                		
					                	});
										hp.forEach(pe -> pe.damage(5,p));
					                }
					            }, i*4); 	 
								ordt.put(rn, t1);                   	
		                    }
		                }
		            }, 40);
					ordt.put(rn, t1);  
		            rb7cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
		        }
			}
	}

	
	public void MagmaBlock(EntityDropItemEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(magma.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
				LivingEntity p = (LivingEntity) Holding.ale(magma.get(fallingb.getUniqueId()));
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tl, 1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
				{
					if(p!=e && e instanceof Player) {
						Player le = (Player)e;
                		le.damage(6, p);
					}
					
				}
				p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
				fallingb.remove();
	        }
		 }
	}



	
	public void MagmaBlock(EntityDamageByEntityEvent ev) 
	{
		if(ev.getDamager() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getDamager();
	        if(magma.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
				LivingEntity p = (LivingEntity) Holding.ale(magma.get(fallingb.getUniqueId()));
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tl, 1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
				{
					if(p!=e && e instanceof Player) {
						Player le = (Player)e;
                		le.damage(6, p);
					}
					
				}
				p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
				fallingb.remove();
	        }
		 }
	}


	
	public void MagmaBlock(EntityChangeBlockEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(magma.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
				LivingEntity p = (LivingEntity) Holding.ale(magma.get(fallingb.getUniqueId()));
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tl, 1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
				{
					if(p!=e && e instanceof Player) {
						Player le = (Player)e;
                		le.damage(6, p);
					}
					
				}
				p.getWorld().playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
				fallingb.remove();
	        }
		 }
	}
	
	
	

	@SuppressWarnings("deprecation")
	
	public void Ordeal(EntityDamageByEntityEvent d) 
	{
	    
		int sec =70;
		if(d.getEntity() instanceof ElderGuardian && !d.isCancelled() && d.getEntity().hasMetadata("redboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
		{
			ElderGuardian p = (ElderGuardian)d.getEntity();
			if(!(p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2) || !rb2cooldown.containsKey(p.getUniqueId())|| !rb7cooldown.containsKey(p.getUniqueId())|| !ordealable.containsKey(p.getUniqueId())) {
				return;
			}
				if(rb6cooldown.containsKey(p.getUniqueId()))
		        {
		            long timer = (rb6cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		            if(!(timer < 0))
		            {
		            }
		            else 
		            {
		                rb6cooldown.remove(p.getUniqueId()); 
	                    if(fbt.containsKey(p.getUniqueId())) {
	                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
	                    }
		    			String rn = p.getMetadata("raid").get(0).asString();
		                Location rl = OceanRaids.getraidloc(p).clone();
						p.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						p.setHealth(p.getMaxHealth()*0.2);
		                d.setCancelled(true);
	                	p.teleport(rl.clone().add(0, 0, 1));
		                Holding.holding(null, p, 1000l);
		                Holding.invur(p, 1000l);
		                for(Player pe : OceanRaids.getheroes(p)) {
	                		pe.sendMessage(ChatColor.DARK_RED+"Time To Ordeal.");
	                		pe.teleport(rl);
	                		Holding.invur(pe, 40l);
		                }
	                    int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {

								for(int i = 0; i <10; i++) {
				                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
						                	p.teleport(rl.clone().add(0, 0.5, 1));
											p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
						                    AtomicInteger j = new AtomicInteger(10);
											for(int i = 0; i <20; i++) {
												int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() {
									                	Random r1 = new Random();
									                	Random r2 = new Random();
									                	Random r3 = new Random();
									                	Random r4 = new Random();
									                	double rd1 = r1.nextDouble()*10 *(r3.nextBoolean()?1:-1);
									                	double rd2 = r2.nextDouble()*10*(r4.nextBoolean()?1:-1);
									                	Location frl = new Location(p.getWorld(), p.getLocation().getX()+rd1, p.getEyeLocation().getY(), p.getLocation().getZ()+rd2);
									                	
									                	Item sn = p.getWorld().dropItem(frl, new ItemStack(Material.FIRE_CORAL_BLOCK));
									                	sn.setMetadata("redknightcharge", new FixedMetadataValue(RMain.getInstance(), true));
									                	sn.setMetadata("redknightcharge"+rn, new FixedMetadataValue(RMain.getInstance(), true));
									                	sn.setGlowing(true);
									                	sn.setGravity(false);
									                	sn.setVisualFire(true);
									                	sn.setVelocity(p.getLocation().toVector().subtract(frl.toVector()).normalize().multiply(0.18));

										                for(Player pe : OceanRaids.getheroes(p)) {
									                		if(pe.getWorld().equals(p.getWorld()) && rl.clone().distance(p.getLocation()) > 20 && pe.getWorld() == p.getWorld()) {
									                    		pe.sendMessage(ChatColor.DARK_RED+"You Can't Get Away!");
									                    		pe.teleport(rl);
									                		}
										                }
										            	ArrayList<Location> ring = new ArrayList<Location>();
										            	HashSet<Player> hp = new HashSet<>();
									                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/180) {
									                    	Location one = rl.setDirection(rl.getDirection()).clone();
									                    	one.setDirection(one.getDirection().rotateAroundY(angle));
									                    	one.add(one.getDirection().normalize().multiply(j.get()*2));
									                    	ring.add(one);
									                	} 
														j.getAndDecrement();
														if(j.get()<=0) {
															j.set(10);
														}
									                	ring.forEach(l -> {
															p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 15, 0.7,1.1,0.7,0);
															for(Entity e : p.getWorld().getNearbyEntities(l,0.7, 1.3, 0.7)) {
																if(e instanceof Player && e!=p) {
																	Player pe = (Player)e;
																	hp.add(pe);
																}
															}
									                		
									                	});
														hp.forEach(pe -> pe.damage(1,p));
									                }
									            }, i*10); 	 
												ordt.put(rn, t1);
						                    }
											for(int i = 0; i <100; i++) {
												int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									                @Override
									                public void run() {
									                	for(Entity e : p.getNearbyEntities(1, 1, 1)) {
									                		if(e.hasMetadata("redknightcharge"+rn)) {
									                			Item sn = (Item) e;
									                			Bukkit.getServer().getPluginManager().callEvent(new EntityPickupItemEvent(p,sn, 1));
									                			sn.remove();
									                			
									                		}
									                	}
									                }
									            }, i*2); 	 
												ordt.put(rn, t1);
						                    }
						                }
						            }, i*40); 	 
									ordt.put(rn, t1);                   	
			                    }
			                }
			            }, 20);
						ordt.put(rn, t1);
	                    int t2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	if(redcharge.containsKey(p.getUniqueId())) {
				                	redcharge.remove(p.getUniqueId());
			                	}
			    				p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
				                for(Player pe : OceanRaids.getheroes(p)) {

									for(int i = 0; i <30; i++) {
										int t2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							                @Override
							                public void run() {
												if(pe.getWorld() == p.getWorld()) {
								                	final Location pel = pe.getLocation();
								                    p.getWorld().playSound(pel, Sound.BLOCK_LAVA_POP, 1.0f, 2f);
								                    p.getWorld().playSound(pel, Sound.BLOCK_LAVA_POP, 1.0f, 0f);
						             				p.getWorld().spawnParticle(Particle.LANDING_LAVA, pel, 500, 1.5,0.1,1.5,0);
						             				int t2 =Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											                @Override
											                public void run() {	
											                		p.getWorld().playSound(pel, Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
											                		p.getWorld().playSound(pel, Sound.ENTITY_PLAYER_SPLASH, 1.0f, 0f);
												                    p.getWorld().playSound(pel, Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
																	p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, pel, 200, 1.5,1,1.5,0);	
																	p.getWorld().spawnParticle(Particle.LAVA, pel, 200, 1.5,1,1.5,0);	
																for(Entity e : p.getWorld().getNearbyEntities(pel,1.5, 1.5, 1.5)) {
																	if(e instanceof Player && e!=p) {
																		Player pe = (Player)e;
																		pe.damage(15,p);
																	}
																}
											                }
											            }, 10); 
						        					ordt.put(rn, t2);
												}
							                }
							            }, i*10); 	 
										ordt.put(rn, t2);               	
				                    }
				                }
			                }
			            }, 650);
						ordt.put(rn, t2);
	                    int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	p.playEffect(EntityEffect.HURT);
			                	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
		                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
				                for(Player pe : OceanRaids.getheroes(p)) {
			                		pe.sendMessage("How dare you!..");
			                		Holding.holding(pe, p, 300l);
			                		p.removeMetadata("fake", RMain.getInstance());
			                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
				                }
					            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
					            int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	p.removeMetadata("failed", RMain.getInstance());
				                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
					                }
					            }, 300);
								ordt.put(rn, t3);
			                }
			            }, 1000);
						ordt.put(rn, t3);
			            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		            }
		        }
		        else 
		        {
                    if(fbt.containsKey(p.getUniqueId())) {
                    	Bukkit.getScheduler().cancelTask(fbt.get(p.getUniqueId()));
                    }
	    			String rn = p.getMetadata("raid").get(0).asString();
	                Location rl = OceanRaids.getraidloc(p).clone();
					p.setHealth(p.getMaxHealth()*0.2);
					p.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                d.setCancelled(true);
                	p.teleport(rl.clone().add(0, 0, 1));
	                Holding.holding(null, p, 1000l);
	                Holding.invur(p, 1000l);
	                for(Player pe : OceanRaids.getheroes(p)) {
                		pe.sendMessage(ChatColor.DARK_RED+"Time To Ordeal.");
                		pe.teleport(rl);
                		Holding.invur(pe, 40l);
	                }
                    int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

							for(int i = 0; i <10; i++) {
			                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() {
					                	p.teleport(rl.clone().add(0, 0.5, 1));
										p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
					                    AtomicInteger j = new AtomicInteger(20);
										for(int i = 0; i <20; i++) {
											int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
								                	Random r1 = new Random();
								                	Random r2 = new Random();
								                	Random r3 = new Random();
								                	Random r4 = new Random();
								                	double rd1 = r1.nextDouble()*10 *(r3.nextBoolean()?1:-1);
								                	double rd2 = r2.nextDouble()*10*(r4.nextBoolean()?1:-1);
								                	Location frl = new Location(p.getWorld(), p.getLocation().getX()+rd1, p.getEyeLocation().getY(), p.getLocation().getZ()+rd2);
								                	
								                	Item sn = p.getWorld().dropItem(frl, new ItemStack(Material.FIRE_CORAL_BLOCK));
								                	sn.setMetadata("redknightcharge", new FixedMetadataValue(RMain.getInstance(), true));
								                	sn.setMetadata("redknightcharge"+rn, new FixedMetadataValue(RMain.getInstance(), true));
								                	sn.setGlowing(true);
								                	sn.setGravity(false);
								                	sn.setVisualFire(true);
								                	sn.setVelocity(p.getLocation().toVector().subtract(frl.toVector()).normalize().multiply(0.18));
								                	
									                for(Player pe : OceanRaids.getheroes(p)) {
								                		if(pe.getWorld().equals(p.getWorld()) && rl.clone().distance(p.getLocation()) > 20 && pe.getWorld() == p.getWorld()) {
								                    		pe.sendMessage(ChatColor.DARK_RED+"You Can't Get Away!");
								                    		pe.teleport(rl);
								                		}
									                }
									            	ArrayList<Location> ring = new ArrayList<Location>();
									            	HashSet<Player> hp = new HashSet<>();
								                    for(double angle=0.1; angle<Math.PI*2; angle += Math.PI/180) {
								                    	Location one = rl.setDirection(rl.getDirection()).clone();
								                    	one.setDirection(one.getDirection().rotateAroundY(angle));
								                    	one.add(one.getDirection().normalize().multiply(j.get()*2));
								                    	ring.add(one);
								                	} 
													j.getAndDecrement();
													if(j.get()<=0) {
														j.set(10);
													}
								                	ring.forEach(l -> {
														p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 15, 0.7,1.1,0.7,0);
														for(Entity e : p.getWorld().getNearbyEntities(l,0.7, 1.3, 0.7)) {
															if(e instanceof Player && e!=p) {
																Player pe = (Player)e;
																hp.add(pe);
															}
														}
								                		
								                	});
													hp.forEach(pe -> pe.damage(1,p));
								                }
								            }, i*10); 	 
											ordt.put(rn, t1);
					                    }
										for(int i = 0; i <100; i++) {
											int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								                @Override
								                public void run() {
								                	for(Entity e : p.getNearbyEntities(1, 1, 1)) {
								                		if(e.hasMetadata("redknightcharge"+rn)) {
								                			Item sn = (Item) e;
								                			Bukkit.getServer().getPluginManager().callEvent(new EntityPickupItemEvent(p,sn, 1));
								                			sn.remove();
								            				p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 8, false, false));
								                		}
								                	}
								                }
								            }, i*2); 	 
											ordt.put(rn, t1);
					                    }
					                }
					            }, i*40); 	 
								ordt.put(rn, t1);                   	
		                    }
		                }
		            }, 20);
					ordt.put(rn, t1);
                    int t2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	if(redcharge.containsKey(p.getUniqueId())) {
			                	redcharge.remove(p.getUniqueId());
		                	}
		    				p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
			                for(Player pe : OceanRaids.getheroes(p)) {

								for(int i = 0; i <30; i++) {
									int t2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						                @Override
						                public void run() {
											if(pe.getWorld() == p.getWorld()) {
							                	final Location pel = pe.getLocation();
							                    p.getWorld().playSound(pel, Sound.BLOCK_LAVA_POP, 1.0f, 2f);
							                    p.getWorld().playSound(pel, Sound.BLOCK_LAVA_POP, 1.0f, 0f);
					             				p.getWorld().spawnParticle(Particle.LANDING_LAVA, pel, 500, 1.5,0.1,1.5,0);
					             				int t2 =Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										                @Override
										                public void run() {	
										                		p.getWorld().playSound(pel, Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 0f);
										                		p.getWorld().playSound(pel, Sound.ENTITY_PLAYER_SPLASH, 1.0f, 0f);
											                    p.getWorld().playSound(pel, Sound.ITEM_FIRECHARGE_USE, 1.0f, 0f);
																p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, pel, 200, 1.5,1,1.5,0);	
																p.getWorld().spawnParticle(Particle.LAVA, pel, 200, 1.5,1,1.5,0);	
															for(Entity e : p.getWorld().getNearbyEntities(pel,1.5, 1.5, 1.5)) {
																if(e instanceof Player && e!=p) {
																	Player pe = (Player)e;
																	pe.damage(15,p);
																}
															}
										                }
										            }, 10); 
					        					ordt.put(rn, t2);
											}
						                }
						            }, i*10); 	 
									ordt.put(rn, t2);               	
			                    }
			                }
		                }
		            }, 650);
					ordt.put(rn, t2);
                    int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	p.playEffect(EntityEffect.HURT);
		                	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
	                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
			                for(Player pe : OceanRaids.getheroes(p)) {
		                		pe.sendMessage("How dare you!..");
		                		Holding.holding(pe, p, 300l);
		                		p.removeMetadata("fake", RMain.getInstance());
		                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
			                }
				            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
				            int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
				                	p.removeMetadata("failed", RMain.getInstance());
			                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
				                }
				            }, 300);
							ordt.put(rn, t3);
		                }
		            }, 1000);
					ordt.put(rn, t3);
		            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		        }
			}
	}
	

	
	public void Ordeal(EntityPickupItemEvent d) 
	{
			if(d.getItem().hasMetadata("redknightcharge")) {

				if((d.getEntity().hasMetadata("redboss"))) {
					d.setCancelled(true);
					LivingEntity p = (LivingEntity) d.getEntity();
	    			String rn = p.getMetadata("raid").get(0).asString();
	    			
					redcharge.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
					redcharge.putIfAbsent(p.getUniqueId(), 1);
					p.getWorld().playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
		            for(Player pe : OceanRaids.getheroes(p)) {
		        		pe.sendMessage(ChatColor.DARK_RED+"RedKnight's Charge (" + redcharge.get(p.getUniqueId()) + " / 20)");
		            }
		            if(redcharge.get(p.getUniqueId()) >= 20) {
		            	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
		            	redcharge.remove(p.getUniqueId());
		            	Bukkit.getWorld("RedKnightRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
						Bukkit.getWorld("RedKnightRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightmagma"+rn)).forEach(e -> e.remove());
		            	Holding.ale(p).setAI(true);
		                rb6cooldown.remove(p.getUniqueId());
		                for(Player pe : OceanRaids.getheroes(p)) {
		            		pe.sendMessage(ChatColor.DARK_RED+"You Can Never Beat Me!");
	                		Holding.invur(pe, 60l);
		            		pe.teleport(p);
		            		pe.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 255 ,false,false));
		            		pe.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 60, 255 ,false,false));
		                }
		 				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {	
		                		p.removeMetadata("fake", RMain.getInstance());
		                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
		    	                p.setInvulnerable(false);
		    	                Holding.ale(p).setInvulnerable(false);
								p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 3000, 10,10,10);	
								p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 3000, 10,10,10);	
			                    for(Player pe : OceanRaids.getheroes(p)) {
			                		p.removeMetadata("fake", RMain.getInstance());
			            			p.getWorld().playSound(pe.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 0);
			                		pe.setHealth(0);
			                    }
			                }
			            }, 60); 
		            }
				}
				else if(d.getEntity() instanceof Player) {
					
					d.setCancelled(true);
					d.getEntity().getWorld().playSound(d.getEntity().getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 0);
					d.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1 ,false,false));
					d.getItem().remove();
				}
			}
	}*/
}
