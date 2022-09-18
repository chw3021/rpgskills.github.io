package io.github.chw3021.monsters.plain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class PlainSkills extends Summoned implements Listener{

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -5797940787526746118L;
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Integer> burrowable = new HashMap<UUID, Integer>();
	static public Multimap<String, Integer> ordt = ArrayListMultimap.create();

	
	private static final PlainSkills instance = new PlainSkills ();
	public static PlainSkills getInstance()
	{
		return instance;
	}
	

	public void StoneThrow(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 4;
		if(d.getEntity() instanceof Creeper && d.getEntity().hasMetadata("plainboss") && !burrowable.containsKey(d.getEntity().getUniqueId())) 
		{
			Creeper p = (Creeper)d.getEntity();
			if(rb3cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb3cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	            	rb3cooldown.remove(p.getUniqueId()); 
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_REPAIR, 0.5f, 0.1f);
					p.getWorld().playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1.0f, 0.2f);
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 100, 1,1,1,Material.LIME_WOOL.createBlockData());
					p.getWorld().spawnParticle(Particle.SCULK_SOUL, p.getLocation(), 100, 1,1,1);
	                Holding.holding(null, p, 20l);
	                

		            for(int i = 0; i<2; i++) {
			            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
				            	burrowable.put(p.getUniqueId(), 1);
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EGG_THROW, 1.0f, 0.2f);
		                        for(double an = -Math.PI/2; an<=Math.PI/2; an += Math.PI/5) {
			        	            Snowball sn = p.launchProjectile(Snowball.class);
			        	            sn.setBounce(true);
			        	            sn.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.4));
			        	            sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			        	            sn.setMetadata("plainbossthrow", new FixedMetadataValue(RMain.getInstance(), true));
			        	            sn.setShooter(p);
			        	            ItemStack eb = new ItemStack(Material.CREEPER_HEAD);
			        	            sn.setItem(eb);             	
		                        }
			                }
			            }, 22+i*2); 
		            }
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_REPAIR, 0.5f, 0.1f);
				p.getWorld().playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1.0f, 0.2f);
				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 100, 1,1,1,Material.LIME_WOOL.createBlockData());
				p.getWorld().spawnParticle(Particle.SCULK_SOUL, p.getLocation(), 100, 1,1,1);
                Holding.holding(null, p, 20l);
                

	            for(int i = 0; i<2; i++) {
		            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
			            	burrowable.put(p.getUniqueId(), 1);
							p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EGG_THROW, 1.0f, 0.2f);
	                        for(double an = -Math.PI/2; an<=Math.PI/2; an += Math.PI/5) {
		        	            Snowball sn = p.launchProjectile(Snowball.class);
		        	            sn.setBounce(true);
		        	            sn.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.4));
		        	            sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		        	            sn.setMetadata("plainbossthrow", new FixedMetadataValue(RMain.getInstance(), true));
		        	            sn.setShooter(p);
		        	            ItemStack eb = new ItemStack(Material.CREEPER_HEAD);
		        	            sn.setItem(eb);             	
	                        }
		                }
		            }, 22+i*2); 
	            }
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}
	

	public void StoneThrow(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof Snowball) 
		{
			Snowball po = (Snowball)d.getEntity();
			if(po.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) po.getShooter();
				if(po.hasMetadata("plainbossthrow")) {
					po.getWorld().playSound(po.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.4f, 1.6f);
					p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, po.getLocation(), 4, 2,2,2);
            		for(Entity e : p.getWorld().getNearbyEntities(po.getLocation(), 1.7, 1.5, 1.7)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(3.5,p);
						}
                	}
				}
				
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	public void Burrow(EntityDamageByEntityEvent ev) 
	{
		if(ev.getEntity().hasMetadata("plainboss") && burrowable.containsKey(ev.getEntity().getUniqueId())) 
		{
			final Creeper p = (Creeper)ev.getEntity();
	        

			if(p.hasMetadata("raid")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				Burrow(p,tl);
			}
			else if(p.hasMetadata("summoned")) {
				final Object hero = getherotype(gethero(p));
				if(hero instanceof Player) {
					final Player hp = (Player)hero;
					Burrow(p,hp.getLocation().clone().add(0,0.2,0));
				}
				else if(hero instanceof HashSet) {
					HashSet<Player> par = (HashSet<Player>) hero;
					Burrow(p,par.stream().findAny().get().getLocation().clone().add(0,0.2,0));
				}

			}
		}
	}

	final private int BurrowMotion(LivingEntity p, Location tl, Location pel) {

    	final Vector v = tl.clone().toVector().subtract(pel.clone().toVector());
        final Double dis = tl.distance(pel);

    	ArrayList<Location> line = new ArrayList<Location>();
    	ArrayList<Location> line2 = new ArrayList<Location>();
        for(double d = 0; d > -5; d -= 0.5) {
			line.add(pel.clone().add(0,d,0));
        }
        for(double d = 0; d < dis; d += dis*0.1) {
        	line2.add(pel.clone().add(0,-5,0).add(v.clone().normalize().multiply(d)));
        }

        AtomicInteger j = new AtomicInteger();
        AtomicInteger j2 = new AtomicInteger();
        line.forEach(l ->  {	
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
         			Holding.holding(null, p, 5l);
         			Holding.invur(p, 5l);
         			Holding.untouchable(p, 5l);
         			p.teleport(l);
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 100,3,2,3,1, Material.LIME_TERRACOTTA.createBlockData());
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 100,3,2,3,1, p.getLocation().getBlock().getBlockData());
	            }
    	   	}, j.incrementAndGet());
        });
        
        line2.forEach(l ->  {	
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
         			Holding.holding(null, p, 5l);
         			Holding.invur(p, 5l);
         			Holding.untouchable(p, 5l);
         			p.teleport(l);
    				p.getWorld().playSound(l, Sound.ENTITY_CREEPER_HURT, 1, 0);
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.clone().add(0, 5, 0), 100,1,2,1, Material.LIME_TERRACOTTA.createBlockData());
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l.clone().add(0, 5, 0), 100,1,2,1, p.getLocation().getBlock().getBlockData());
	            }
    	   	}, 20+j2.incrementAndGet());
        });
        return 35;
	}

	final private void Burrow(Creeper p, Location tl) {
		if(rb8cooldown.containsKey(p.getUniqueId()))
        {
            long timer = (rb8cooldown.get(p.getUniqueId())/1000 + 9) - System.currentTimeMillis()/1000; 
            if(!(timer < 0))
            {
            }
            else 
            {
            	rb8cooldown.remove(p.getUniqueId()); // removing player from HashMap


     			Holding.holding(null, p, 21l);
     			Holding.invur(p, 21l);
     			Holding.untouchable(p, 21l);

				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WARDEN_DIG, 1, 2);
                final Location pel = p.getLocation().clone();
                
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
                    {	
             			p.teleport(tl);
        				p.getWorld().playSound(tl, Sound.ENTITY_CREEPER_HURT, 1, 0);
    					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100,3,2,3,1, Material.LIME_TERRACOTTA.createBlockData());
             			p.explode();
                    	burrowable.remove(p.getUniqueId());
    	            }
        	   	},  BurrowMotion(p,tl,pel));
				rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
            }
        }
        else 
        {

 			Holding.holding(null, p, 21l);
 			Holding.invur(p, 21l);
 			Holding.untouchable(p, 21l);


			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WARDEN_DIG, 1, 2);
            final Location pel = p.getLocation().clone();
            
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
         			p.teleport(tl);
    				p.getWorld().playSound(tl, Sound.ENTITY_CREEPER_HURT, 1, 0);
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100,3,2,3,1, Material.LIME_TERRACOTTA.createBlockData());
         			p.explode();
                	burrowable.remove(p.getUniqueId());
	            }
    	   	},  BurrowMotion(p,tl,pel));
			rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}
	

	public void Creeperboom(ExplosionPrimeEvent d) 
	{
		Entity p = d.getEntity();
		
		
		if(p instanceof Creeper) {
			if(p.hasMetadata("plainboss")) {
				Creeper p1 = (Creeper) p;
				p1.setFuseTicks(0);
				d.setCancelled(true);
				p1.setFuseTicks(0);
				p1.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, p1.getLocation(), 120,4.5,4.5,4.5,1);
				p1.getWorld().spawnParticle(Particle.REDSTONE, p1.getLocation(), 60,2.5,2.5,2.5,1, new Particle.DustOptions(Color.LIME, 3f));
				p1.getWorld().playSound(p1.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
				for(Entity e : p1.getWorld().getNearbyEntities(p1.getLocation(),2.5, 2.5, 2.5)) {
					if(p1!=e && e instanceof LivingEntity) {
						LivingEntity le = (LivingEntity)e;
						le.damage(15,p1);
					}
				}
				for(Entity e : p1.getWorld().getNearbyEntities(p1.getLocation(),4.5, 4.5, 4.5)) {
					if(p1!=e && e instanceof LivingEntity) {
						LivingEntity le = (LivingEntity)e;
						le.damage(3.5,p1);
					}
				}
			}
		}
	}
}
