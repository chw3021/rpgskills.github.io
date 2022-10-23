package io.github.chw3021.monsters.mountains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class MountainsSkills extends Summoned implements Listener{

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -5797940787526746118L;
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb1cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Integer> throwable = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> strong = new HashMap<UUID, Integer>();

	
	private static final MountainsSkills instance = new MountainsSkills ();
	public static MountainsSkills getInstance()
	{
		return instance;
	}
	
	
	final private void Smotion(LivingEntity p) {

		ArrayList<Location> air = new ArrayList<Location>();
		ArrayList<Location> sight = new ArrayList<Location>();
        AtomicInteger j = new AtomicInteger(0);
        AtomicInteger k = new AtomicInteger(0);
        for(double i=Math.PI/180; i > -Math.PI; i -= Math.PI/90) {
        	Location eye = p.getEyeLocation().clone();
        	eye.setDirection(eye.clone().getDirection().multiply(-1));
        	Vector eyev = eye.getDirection().clone().rotateAroundY(Math.PI/2);
        	Location a = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, i).normalize());
        	Location b = eye.clone().add(eye.getDirection().rotateAroundAxis(eyev, i).normalize().multiply(6));
        	sight.add(a);
        	air.add(b);
        }
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 0.6f, 0.2f);
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_STEP, 0.4f, 0.2f);
        air.forEach(l -> {
        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 5, 0.3,0.3,0.3,0 ,Material.STONE.createBlockData());
					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 10, 2,2,2,0);
                }
            }, j.incrementAndGet()/3);
        });
        sight.forEach(l -> {
        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
            		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 0.10f, 0.2f);
					p.teleport(l);
	        		p.swingMainHand();
                }
            }, k.incrementAndGet()/3);
        });
	}
	
	final private void EarthQuake(LivingEntity p, Location tl, Integer co) {

		HashSet<Location> eq = new HashSet<Location>();
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_DAMAGE, 0.6f, 0.2f);
		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_GILDED_BLACKSTONE_BREAK, 0.7f, 0.2f);
		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SCULK_CATALYST_BREAK, 0.23f, 0.2f);
		p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100, 2,2,2,0 ,Material.STONE.createBlockData());
		
		for(int i = 0; i < co; i ++) {
			for(double d = -Math.PI/6; d<Math.PI/6; d += Math.PI/3/co) {
				final Location al = tl.clone().add(tl.clone().getDirection().normalize().rotateAroundY(d).multiply(co)).add(0, i, 0);
				if(al.getBlock().getType().isAir()) {
					eq.add(al);
				}
			}
		}
		
		eq.forEach(l -> {
    		for(Entity e : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5)) {
				if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
					LivingEntity le = (LivingEntity)e;
					le.damage(5,p);
				}
        	}
			FallingBlock fallingb = l.getWorld().spawnFallingBlock(l, Material.GRAVEL.createBlockData());
			fallingb.setDropItem(true);
			fallingb.setHurtEntities(true);
			fallingb.setGravity(false);
			fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			fallingb.setMetadata("stonebossthrow", new FixedMetadataValue(RMain.getInstance(), true));

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	fallingb.remove();
                }
            }, 40);
		});
		
	}


	public void EarthBlock(EntityDropItemEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(fallingb.hasMetadata("stonebossthrow")){
	        	ev.setCancelled(true);
				fallingb.remove();
	        }
		 }
	}


	public void EarthBlock(EntityDamageByEntityEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(fallingb.hasMetadata("stonebossthrow")){
	        	ev.setCancelled(true);
				fallingb.remove();
	        }
		 }
	}


	public void EarthBlock(EntityChangeBlockEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(fallingb.hasMetadata("stonebossthrow")){
	        	ev.setCancelled(true);
				fallingb.remove();
	        }
		 }
	}


	public void EarthQuake(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 4;
		if(d.getEntity() instanceof IronGolem && d.getEntity().hasMetadata("stoneboss") && throwable.containsKey(d.getEntity().getUniqueId())) 
		{
			IronGolem p = (IronGolem)d.getEntity();
			if(rb3cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb3cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	            	rb3cooldown.remove(p.getUniqueId()); 
	            	throwable.remove(p.getUniqueId());
	            	
	                final Location pl = p.getLocation().clone();
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_REPAIR, 0.5f, 0.1f);
					p.getWorld().playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1.0f, 0.2f);
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 100, 1,1,1,Material.COBBLESTONE.createBlockData());
					p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, p.getLocation(), 100, 1,1,1);
	                Holding.holding(null, p, 32l);
	                
	                Smotion(p);

	                AtomicInteger j = new AtomicInteger(0);


		            for(int i = 0; i<8; i++) {
			            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	EarthQuake(p,pl,j.incrementAndGet());
			                }
			            }, 32+i*2); 
		            }
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {
            	throwable.remove(p.getUniqueId());
            	
                final Location pl = p.getLocation().clone();
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_REPAIR, 0.5f, 0.1f);
				p.getWorld().playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1.0f, 0.2f);
				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 100, 1,1,1,Material.COBBLESTONE.createBlockData());
				p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, p.getLocation(), 100, 1,1,1);
                Holding.holding(null, p, 32l);
                
                Smotion(p);

                AtomicInteger j = new AtomicInteger(0);


	            for(int i = 0; i<8; i++) {
		            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	EarthQuake(p,pl,j.incrementAndGet());
		                }
		            }, 32+i*2); 
	            }
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}
	

	public void EarthQuake(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof Snowball) 
		{
			Snowball po = (Snowball)d.getEntity();
			if(po.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) po.getShooter();
				if(po.hasMetadata("stonebossthrow")) {
					po.getWorld().playSound(po.getLocation(), Sound.BLOCK_STONE_BREAK, 0.4f, 0.1f);
					po.getWorld().playSound(po.getLocation(), Sound.BLOCK_STONE_HIT, 0.689f, 0.2f);
					po.getWorld().playSound(po.getLocation(), Sound.BLOCK_STONE_FALL, 0.6f, 0.2f);
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, po.getLocation(), 300, 1.5,1.5,1.5,Material.COBBLESTONE.createBlockData());
            		for(Entity e : p.getWorld().getNearbyEntities(po.getLocation(), 1.5, 1.5, 1.5)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(5,p);
						}
                	}
				}
				
			}
			
		}
	}


	@SuppressWarnings("unchecked")
	public void Drop(EntityDamageByEntityEvent ev) 
	{
		if(ev.getEntity().hasMetadata("stoneboss")) 
		{
			final LivingEntity p = (LivingEntity)ev.getEntity();
	        

			if(p.hasMetadata("raid")) {
				if(!OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = OverworldRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				Drop(p,tl);
			}
			else if(p.hasMetadata("summoned")) {
				final Object hero = getherotype(gethero(p));
				if(hero instanceof Player) {
					final Player hp = (Player)hero;
					Drop(p,hp.getLocation());
				}
				else if(hero instanceof HashSet) {
					HashSet<Player> par = (HashSet<Player>) hero;
					Drop(p,par.stream().findAny().get().getLocation());
				}

			}
		}
	}


	final private void Drop(LivingEntity p, Location tl) {
		if(rb8cooldown.containsKey(p.getUniqueId()))
        {
            long timer = (rb8cooldown.get(p.getUniqueId())/1000 + 8) - System.currentTimeMillis()/1000; 
            if(!(timer < 0))
            {
            }
            else 
            {
            	rb8cooldown.remove(p.getUniqueId()); // removing player from HashMap

            	ArrayList<Location> line = new ArrayList<Location>();
            	
    			Location pl = p.getEyeLocation().clone();
    			p.getWorld().playSound(pl, Sound.ENTITY_IRON_GOLEM_STEP, 1.0f, 0f);
    			p.getWorld().playSound(pl, Sound.ENTITY_IRON_GOLEM_REPAIR, 1.0f, 0f);
    			p.getWorld().playSound(pl, Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0f);
    			p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, pl, 20, 2,2,2);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
                    {	
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1.0f, 0f);
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1.0f, 2f);
    	    			final Location pel = tl.clone();
    	    			Location pl = p.getLocation().clone();
    	    			try {
    	        			p.teleport(pl.setDirection(pel.toVector().subtract(pl.clone().toVector()).normalize()));
    	    			}
    	    			catch(IllegalArgumentException e){
    	        			p.teleport(p.getLocation());
    	    			}
             			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 15,15,false,false));
                    	p.setVelocity(p.getEyeLocation().clone().getDirection().clone().normalize().multiply(1.4));
                    	
    	            }
        	   	}, 6);

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
                    {	
                        final Location pel = p.getLocation().clone();
                        for(int d = 10; d > 0; d -= 1) {
        					line.add(pel.clone().add(0,d,0));
                        }
                        AtomicInteger j = new AtomicInteger();
                        line.forEach(l ->  {	
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                         		@Override
                            	public void run() 
                                {	
                         			p.teleport(l);
        	    					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100,3,2,3,1, Material.STONE.createBlockData());
        	    					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 100,3,2,3,1);
        	    					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 0);

        	    					for(Entity e : p.getWorld().getNearbyEntities(tl,3, 2, 3)) {
        	    						if(p!=e && e instanceof LivingEntity) {
        	    							LivingEntity le = (LivingEntity)e;
        	    							le.damage(5,p);
        	    						}
        	    					}
                	            }
                    	   	}, 10+j.incrementAndGet()*2);
                        });
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                     		@Override
                        	public void run() 
                            {	
                     			throwable.put(p.getUniqueId(), 1);
            	            }
                	   	}, 50);
                    	
    	            }
        	   	}, 8);
				rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
            }
        }
        else 
        {

        	ArrayList<Location> line = new ArrayList<Location>();
        	
			Location pl = p.getEyeLocation().clone();
			p.getWorld().playSound(pl, Sound.ENTITY_IRON_GOLEM_STEP, 1.0f, 0f);
			p.getWorld().playSound(pl, Sound.ENTITY_IRON_GOLEM_REPAIR, 1.0f, 0f);
			p.getWorld().playSound(pl, Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0f);
			p.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, pl, 20, 2,2,2);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1.0f, 0f);
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1.0f, 2f);
	    			final Location pel = tl.clone();
	    			Location pl = p.getLocation().clone();
	    			try {
	        			p.teleport(pl.setDirection(pel.toVector().subtract(pl.clone().toVector()).normalize()));
	    			}
	    			catch(IllegalArgumentException e){
	        			p.teleport(p.getLocation());
	    			}
         			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 15,15,false,false));
                	p.setVelocity(p.getEyeLocation().clone().getDirection().clone().normalize().multiply(1.4));
                	
	            }
    	   	}, 6);

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
                    final Location pel = p.getLocation().clone();
                    for(int d = 10; d > 0; d -= 1) {
    					line.add(pel.clone().add(0,d,0));
                    }
                    AtomicInteger j = new AtomicInteger();
                    line.forEach(l ->  {	
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                     		@Override
                        	public void run() 
                            {	
                     			p.teleport(l);
    	    					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100,3,2,3,1, Material.STONE.createBlockData());
    	    					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 100,3,2,3,1);
    	    					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 0);

    	    					for(Entity e : p.getWorld().getNearbyEntities(tl,3, 2, 3)) {
    	    						if(p!=e && e instanceof LivingEntity) {
    	    							LivingEntity le = (LivingEntity)e;
    	    							le.damage(5,p);
    	    						}
    	    					}
            	            }
                	   	}, 10+j.incrementAndGet()*2);
                    });
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                 		@Override
                    	public void run() 
                        {	
                 			throwable.put(p.getUniqueId(), 1);
        	            }
            	   	}, 50);
                	
	            }
    	   	}, 8);
			rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}
	

	public void GolemSmash(EntityDamageByEntityEvent d) 
	{
	    
		if((d.getDamager() instanceof IronGolem) && d.getEntity() instanceof LivingEntity&& !d.isCancelled() &&d.getDamager().hasMetadata("stoneboss")) 
		{
			int sec =1;
			IronGolem p = (IronGolem)d.getDamager();
			LivingEntity he = (LivingEntity)d.getEntity();
			final Location tl = he.getLocation().clone();
			if(rb1cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb1cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	                rb1cooldown.remove(p.getUniqueId()); // removing player from HashMap

					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100,3,2,3,1, Material.STONE.createBlockData());
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 0);

					for(Entity e : p.getWorld().getNearbyEntities(tl,3, 2, 3)) {
						if(p!=e && e instanceof LivingEntity) {
							LivingEntity le = (LivingEntity)e;
							le.damage(d.getDamage());
						}
					}
		            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	            }
	        }
	        else 
	        {

				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100,3,2,3,1, Material.STONE.createBlockData());
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 0);

				for(Entity e : p.getWorld().getNearbyEntities(tl,3, 2, 3)) {
					if(p!=e && e instanceof LivingEntity) {
						LivingEntity le = (LivingEntity)e;
						le.damage(d.getDamage());
					}
				}
	            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
		}
		
		if((d.getDamager() instanceof LivingEntity) && d.getEntity() instanceof IronGolem&& !d.isCancelled() &&d.getEntity().hasMetadata("stoneboss") && strong.containsKey(d.getEntity().getUniqueId())) 
		{
			IronGolem p = (IronGolem)d.getEntity();
			LivingEntity le = (LivingEntity)d.getDamager();
			
			le.damage(d.getDamage()*0.03,p);
            d.setCancelled(true);
			strong.compute(p.getUniqueId(), (k,v) -> v-1);
			if(strong.get(p.getUniqueId()) <0) {
    			String rn = p.getMetadata("raid").get(0).asString();
				ordt.get(rn).forEach(i -> {
					Bukkit.getScheduler().cancelTask(i);
				});
				strong.remove(p.getUniqueId());
			}
		}
		if((d.getDamager() instanceof Projectile) && d.getEntity() instanceof IronGolem&& !d.isCancelled() &&d.getEntity().hasMetadata("stoneboss") && strong.containsKey(d.getEntity().getUniqueId())) 
		{
			IronGolem p = (IronGolem)d.getEntity();
			Projectile pr = (Projectile)d.getDamager();
			
			if(pr.getShooter() instanceof LivingEntity) {
				LivingEntity le = (LivingEntity) pr.getShooter();
				le.damage(d.getDamage()*0.03,p);
                d.setCancelled(true);
				strong.compute(p.getUniqueId(), (k,v) -> v-1);
				if(strong.get(p.getUniqueId()) <0) {
	    			String rn = p.getMetadata("raid").get(0).asString();
					ordt.get(rn).forEach(i -> {
						Bukkit.getScheduler().cancelTask(i);
					});
					strong.remove(p.getUniqueId());
				}
			}
		}
	}
	

	public void Smash(EntityRegainHealthEvent d)
	{
		if((d.getEntity() instanceof IronGolem) && !d.isCancelled() &&d.getEntity().hasMetadata("stoneboss")) 
		{
			int sec =1;
			IronGolem p = (IronGolem)d.getEntity();
			final Location tl = p.getLocation().clone();
			if(rb1cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb1cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	                rb1cooldown.remove(p.getUniqueId()); // removing player from HashMap

					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100,3,2,3,1, Material.STONE.createBlockData());
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 0);

					for(Entity e : p.getWorld().getNearbyEntities(tl,3, 2, 3)) {
						if(p!=e && e instanceof LivingEntity) {
							LivingEntity le = (LivingEntity)e;
							le.damage(3.5);
						}
					}
		            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	            }
	        }
	        else 
	        {

				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100,3,2,3,1, Material.STONE.createBlockData());
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 0);

				for(Entity e : p.getWorld().getNearbyEntities(tl,3, 2, 3)) {
					if(p!=e && e instanceof LivingEntity) {
						LivingEntity le = (LivingEntity)e;
						le.damage(3.5);
					}
				}
	            rb1cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
		}
		
	}

	@SuppressWarnings("deprecation")
	
	public void IronBody(EntityDamageByEntityEvent d) 
	{
	    
		int sec =50;
		if(d.getEntity() instanceof IronGolem && !d.isCancelled() && d.getEntity().hasMetadata("stoneboss")&& d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("ruined")  && !d.getEntity().hasMetadata("failed")) 
		{
			IronGolem p = (IronGolem)d.getEntity();
			if(!(p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2)) {
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
		                rb6cooldown.remove(p.getUniqueId()); // removing player from HashMap
		                d.setCancelled(true);
		                p.setHealth(p.getMaxHealth()*0.2);
		                strong.put(p.getUniqueId(), 50);
						for(Player pe : OverworldRaids.getheroes(p)) {
	                		pe.sendMessage(ChatColor.DARK_RED+"I will never die!");
		                }
		                Holding.holding(null, p, 200l);
		    			String rn = p.getMetadata("raid").get(0).asString();
						for(int i = 0; i <10; i++) {
		                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 200, 2,1,2,Material.STONE.createBlockData());
									p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 200, 2,1,2,Material.DEEPSLATE.createBlockData());
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_REPAIR, 1, 1);
									p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 2,2,false,false));
				                }
		                    },i*20);
							ordt.put(rn, t1);                   	
	                    }
						int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	strong.remove(p.getUniqueId());
			                }
	                    },200);
						ordt.put(rn, task);    
			            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		            }
		        }
		        else 
		        {
	                d.setCancelled(true);
	                p.setHealth(p.getMaxHealth()*0.2);
	                strong.put(p.getUniqueId(), 50);
					for(Player pe : OverworldRaids.getheroes(p)) {
                		pe.sendMessage(ChatColor.DARK_RED+"I will never die!");
	                }
	                Holding.holding(null, p, 200l);
	    			String rn = p.getMetadata("raid").get(0).asString();
					for(int i = 0; i <10; i++) {
	                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 200, 2,1,2,Material.STONE.createBlockData());
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 200, 2,1,2,Material.DEEPSLATE.createBlockData());
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_REPAIR, 1, 1);
								p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 10,2,false,false));
			                }
	                    },i*20);
						ordt.put(rn, t1);                   	
                    }
					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
		                	strong.remove(p.getUniqueId());
		                }
                    },200);
					ordt.put(rn, task); 
		            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		        }
			}
	}
}
