package io.github.chw3021.monsters.mountains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
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
	private HashMap<UUID, Long> rb5cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Integer> throwable = new HashMap<UUID, Integer>();
	
	private HashMap<UUID, Integer> count = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Boolean> counterable = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, Integer> countt = new HashMap<UUID, Integer>();
	
	
	private HashMap<UUID, Integer> strong = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> strongt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();
	static private HashMap<UUID, Boolean> ordeal = new HashMap<UUID, Boolean>();

	
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
        	Location loc = p.getLocation().clone();
        	eye.setDirection(eye.clone().getDirection().multiply(-1));
        	Vector eyev = eye.getDirection().clone().rotateAroundY(Math.PI/2);
        	Location a = loc.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, i).normalize());
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
                	
					p.getWorld().spawnParticle(Particle.BLOCK, l, 5, 0.3,0.3,0.3,0 ,Material.STONE.createBlockData());
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
		p.getWorld().spawnParticle(Particle.BLOCK, tl, 100, 2,2,2,0 ,Material.STONE.createBlockData());
		
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
					if(p.hasMetadata("raid")) {
						le.damage(25,p);
					}
					le.damage(7,p);
				}
        	}
    		p.getWorld().spawnParticle(Particle.BLOCK_MARKER, l, 20, 2,2,2,0 ,Material.GRAVEL.createBlockData());
    		p.getWorld().spawnParticle(Particle.BLOCK_MARKER, l, 20, 2,2,2,0 ,Material.COBBLESTONE.createBlockData());
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
			IronGolem p = (IronGolem)Holding.ale(d.getEntity());
			if(ordeal.containsKey(p.getUniqueId()) || p.hasMetadata("failed")) {
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
	            	throwable.remove(p.getUniqueId());
	            	
	                final Location pl = p.getLocation().clone();
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_REPAIR, 0.5f, 0.1f);
					p.getWorld().playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1.0f, 0.2f);
					p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100, 1,1,1,Material.COBBLESTONE.createBlockData());
					p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, p.getLocation(), 100, 1,1,1);
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
				p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 100, 1,1,1,Material.COBBLESTONE.createBlockData());
				p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, p.getLocation(), 100, 1,1,1);
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
			Snowball po = (Snowball)Holding.ale(d.getEntity());
			if(po.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) po.getShooter();
				if(po.hasMetadata("stonebossthrow")) {
					po.getWorld().playSound(po.getLocation(), Sound.BLOCK_STONE_BREAK, 0.4f, 0.1f);
					po.getWorld().playSound(po.getLocation(), Sound.BLOCK_STONE_HIT, 0.689f, 0.2f);
					po.getWorld().playSound(po.getLocation(), Sound.BLOCK_STONE_FALL, 0.6f, 0.2f);
					p.getWorld().spawnParticle(Particle.BLOCK, po.getLocation(), 300, 1.5,1.5,1.5,Material.COBBLESTONE.createBlockData());
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
	        

			if(ordeal.containsKey(p.getUniqueId()) || p.hasMetadata("failed")) {
				return;
			}
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
    			p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, pl, 20, 2,2,2);
    			Holding.invur(p, 10l);
    			
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
                    {	
    					p.getWorld().spawnParticle(Particle.CLOUD, tl, 100,3,2,3,0);
                        p.getWorld().playSound(tl, Sound.ENTITY_IRON_GOLEM_ATTACK, 1.0f, 0f);
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
        	    					p.getWorld().spawnParticle(Particle.BLOCK, tl, 100,2.1,2,2.1,1, Material.STONE.createBlockData());
        	    					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 100,2.1,2,2.1,1);
        	    					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 0);

        	    					for(Entity e : p.getWorld().getNearbyEntities(tl,2, 2, 2)) {
        	    						if(p!=e && e instanceof LivingEntity) {
        	    							LivingEntity le = (LivingEntity)e;
        	    							if(p.hasMetadata("raid")) {
        	    								le.damage(20,p);
        	    							}
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
        	   	}, 21);
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
			p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, pl, 20, 2,2,2);
			Holding.invur(p, 10l);
			
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
					p.getWorld().spawnParticle(Particle.CLOUD, tl, 100,3,2,3,0);
                    p.getWorld().playSound(tl, Sound.ENTITY_IRON_GOLEM_ATTACK, 1.0f, 0f);
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
    	    					p.getWorld().spawnParticle(Particle.BLOCK, tl, 100,3,2,3,1, Material.STONE.createBlockData());
    	    					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 100,3,2,3,1);
    	    					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 0);

    	    					for(Entity e : p.getWorld().getNearbyEntities(tl,3, 2, 3)) {
    	    						if(p!=e && e instanceof LivingEntity) {
    	    							LivingEntity le = (LivingEntity)e;
    	    							if(p.hasMetadata("raid")) {
    	    								le.damage(20,p);
    	    							}
    	    							le.damage(2.5,p);
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
    	   	}, 21);
			rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}
	

	public void Smash(EntityRegainHealthEvent d)
	{
		if((d.getEntity() instanceof IronGolem) && !d.isCancelled() &&d.getEntity().hasMetadata("stoneboss")) 
		{
			IronGolem p = (IronGolem)Holding.ale(d.getEntity());
			if(ordeal.containsKey(p.getUniqueId()) || p.hasMetadata("failed")) {
				d.setCancelled(true);
				return;
			}
			final Location tl = p.getLocation().clone();

			p.getWorld().spawnParticle(Particle.ASH, tl, 400,2,1,2,0.05);
			p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 50,2,2,2,1);
			p.getWorld().playSound(tl, Sound.BLOCK_STONE_BREAK, 1, 0);
			p.getWorld().playSound(tl, Sound.ENTITY_IRON_GOLEM_REPAIR, 1, 0);
			
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
        			p.getWorld().spawnParticle(Particle.BLOCK, tl, 300,2,2,2,1, Material.GRANITE.createBlockData());
        			p.getWorld().spawnParticle(Particle.EXPLOSION, tl, 10,2,1,2,1);
        			p.getWorld().playSound(tl, Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 0);
        			p.getWorld().playSound(tl, Sound.BLOCK_STONE_BREAK, 1, 2);
        			for(Entity e : p.getWorld().getNearbyEntities(tl,2, 2, 2)) {
        				if(p!=e && e instanceof LivingEntity) {
        					LivingEntity le = (LivingEntity)e;
        					le.damage(5,p);
        				}
        			}
	            }
    	   	}, 20);

		}
		
	}


	public void GolemSmash(EntityDamageByEntityEvent d) 
	{
	    
		if((d.getDamager() instanceof IronGolem) && d.getEntity() instanceof LivingEntity&& !d.isCancelled() &&d.getDamager().hasMetadata("stoneboss")) 
		{
			int sec =1;
			IronGolem p = (IronGolem)d.getDamager();
			if(ordeal.containsKey(p.getUniqueId()) || p.hasMetadata("failed")) {
				return;
			}
			LivingEntity he = (LivingEntity)Holding.ale(d.getEntity());
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

					p.getWorld().spawnParticle(Particle.BLOCK, tl, 100,3,2,3,1, Material.STONE.createBlockData());
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

				p.getWorld().spawnParticle(Particle.BLOCK, tl, 100,3,2,3,1, Material.STONE.createBlockData());
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
		//ironbody
		
		if((d.getDamager() instanceof LivingEntity) && strong.containsKey(d.getEntity().getUniqueId())) 
		{
			IronGolem p = (IronGolem)Holding.ale(d.getEntity());
			if(ordeal.containsKey(p.getUniqueId()) || p.hasMetadata("failed")) {
				return;
			}
			
            d.setCancelled(true);
			strong.compute(p.getUniqueId(), (k,v) -> v-1);
			if(strong.get(p.getUniqueId()) <0) {
				Bukkit.getScheduler().cancelTask(strongt.get(p.getUniqueId()));
				strong.remove(p.getUniqueId());


				p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, p.getLocation(), 50,1,1,1,1);
				p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 300,1,1,1,1, Material.STONE.createBlockData());
				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_DAMAGE, 1, 0);
				p.getWorld().playSound(p.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 0);
				
				ordealable.put(p.getUniqueId(),true);
			}
		}
		if((d.getDamager() instanceof Projectile) && strong.containsKey(d.getEntity().getUniqueId())) 
		{
			IronGolem p = (IronGolem)Holding.ale(d.getEntity());
			if(ordeal.containsKey(p.getUniqueId()) || p.hasMetadata("failed")) {
				return;
			}
			Projectile pr = (Projectile)d.getDamager();
			
			if(pr.getShooter() instanceof LivingEntity) {
                d.setCancelled(true);
				strong.compute(p.getUniqueId(), (k,v) -> v-1);
				if(strong.get(p.getUniqueId()) <0) {
					Bukkit.getScheduler().cancelTask(strongt.get(p.getUniqueId()));
					strong.remove(p.getUniqueId());


					p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, p.getLocation(), 50,1,1,1,1);
					p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 300,1,1,1,1, Material.STONE.createBlockData());
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_DAMAGE, 1, 0);
					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 0);
					ordealable.put(p.getUniqueId(),true);
				}
			}
		}
	}
	

	public void IronBody(EntityDamageByEntityEvent d) 
	{
	    
		int sec =50;
		if(d.getEntity() instanceof IronGolem && !d.isCancelled() && d.getEntity().hasMetadata("stoneboss")&& d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("ruined")  && !d.getEntity().hasMetadata("failed")) 
		{
			IronGolem p = (IronGolem) Holding.ale(d.getEntity());

			if(ordeal.containsKey(p.getUniqueId()) || p.hasMetadata("failed")) {
				return;
			}
			if((p.hasMetadata("ruined") && p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
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
		                
		                strong.put(p.getUniqueId(), 4*OverworldRaids.getheroes(p).size());
						for(Player pe : OverworldRaids.getheroes(p)) {
	    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
		                		pe.sendMessage(ChatColor.BOLD+"죽지 않는다!");
	    					}
	    					else {
		                		pe.sendMessage(ChatColor.BOLD+"I don't die!");
	    					}
		                }
		    			String rn = gethero(p);
	                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() {
								p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 200, 2,1,2,Material.STONE.createBlockData());
								p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 50, 2,1,2);
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_REPAIR, 1, 1);
								p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 5,5,false,false));
			                }
	                    },20,20);
						ordt.put(rn, t1);    
						strongt.put(p.getUniqueId(), t1);
						
			            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		            }
		        }
		        else 
		        {

	                strong.put(p.getUniqueId(), 4*OverworldRaids.getheroes(p).size());
					for(Player pe : OverworldRaids.getheroes(p)) {
    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
	                		pe.sendMessage(ChatColor.BOLD+"나는 죽지 않는다!");
    					}
    					else {
	                		pe.sendMessage(ChatColor.BOLD+"I will never die!");
    					}
	                }
	    			String rn = gethero(p);
                    int t1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {
							p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 200, 2,1,2,Material.STONE.createBlockData());
							p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 50, 2,1,2);
							p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_REPAIR, 1, 1);
							p.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 6,5,false,false));
		                }
                    },20,20);
					ordt.put(rn, t1);    
					strongt.put(p.getUniqueId(), t1);
					
		            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		        }
			}
	}

	final private void mantle(LivingEntity p, Location rl, String rn) {

        p.teleport(rl.clone());
		Smotion(p);
		counterable.put(p.getUniqueId(), true);
		
		HashSet<Location> lhs = new HashSet<>();
            OverworldRaids.getheroes(p).stream().filter(pe ->!pe.isDead()&&pe.getWorld().equals(p.getWorld())).forEach(tpe->{
            final Location tpel = tpe.getLocation().clone();
            
        	Random random=new Random();
        	double number = 2.2 * (random.nextBoolean() ? -1 : 1);
        	double number2 = 2.2 * (random.nextBoolean() ? -1 : 1);
        	Location esl = tpel.clone().add(number, 0.25, number2);
        	lhs.add(esl);


        	int i1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	Holding.holding(null, tpe, 25l);
                }
    		},6);
    		ordt.put(rn, i1);
    		
        	tpe.playSound(tpe, Sound.ENTITY_IRON_GOLEM_ATTACK, 0.8f, 0.5f);
        	tpe.playSound(tpe, Sound.ENTITY_RAVAGER_STUNNED, 0.6f, 0.5f);
        	tpe.playSound(tpe, Sound.BLOCK_GILDED_BLACKSTONE_BREAK, 1f, 0.5f);

        	int i2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	tpe.setGliding(true);
                	tpe.setFlying(false);
                	tpe.removePotionEffect(PotionEffectType.SLOW_FALLING);
                	tpe.teleport(esl);
                	tpe.setVelocity(BlockFace.UP.getDirection().normalize().multiply(2));
                }
    		},32);
    		ordt.put(rn, i2);

            });

            int i2 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	
                	lhs.forEach(l ->{
            			p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l.clone(), 1000,1,15,1,0.1);
                    	p.swingMainHand();
                    	l.getWorld().playSound(l, Sound.ENTITY_IRON_GOLEM_REPAIR, 0.9f, 0.6f);
                    	
                		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                            @Override
                            public void run() {

                    			p.getWorld().spawnParticle(Particle.BLOCK, l.clone(), 1000,1,15,1,0.1,Material.STONE.createBlockData());
                    			p.getWorld().spawnParticle(Particle.BLOCK, l.clone(), 1000,1,15,1,0.1,Material.ANDESITE.createBlockData());
                            	p.swingMainHand();
                            	l.getWorld().playSound(l, Sound.ENTITY_WITHER_BREAK_BLOCK, 0.6f, 0.6f);
                            	p.getWorld().playSound(p.getLocation().clone(), Sound.ENTITY_IRON_GOLEM_ATTACK, 0.8f, 0.6f);
                            	
                        		for(Entity e : l.getWorld().getNearbyEntities(l, 1, 15, 1)) {
            						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
            							Player le = (Player)e;
            							le.damage(400,p);
            						}
                            	}
                            }
                        }, 10);
                	});

        			p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation().clone(), 1000,2.5,10,2.5,0.1);
                	p.swingMainHand();
                	p.getWorld().playSound(p.getLocation().clone(), Sound.ENTITY_IRON_GOLEM_REPAIR, 0.9f, 0.6f);
                	
            		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                        @Override
                        public void run() {

                			p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation().clone(), 1000,1.5,10,1.5,0.1,Material.STONE.createBlockData());
                			p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation().clone(), 1000,1.5,10,1.5,0.1,Material.ANDESITE.createBlockData());
                        	p.swingMainHand();
                        	p.getWorld().playSound(p.getLocation().clone(), Sound.ENTITY_WITHER_BREAK_BLOCK, 0.6f, 0.6f);
                        	p.getWorld().playSound(p.getLocation().clone(), Sound.ENTITY_IRON_GOLEM_ATTACK, 0.8f, 0.6f);
                        	
                    		for(Entity e : p.getNearbyEntities(1.2, 10, 1.2)) {
        						if(p!=e && e instanceof Player&& !(e.hasMetadata("fake"))) {
        							Player le = (Player)e;
        							le.damage(400,p);
        						}
                        	}
                        }
                    }, 10);
                	counterable.remove(p.getUniqueId());
                }
            }, 83);
    		ordt.put(rn, i2);
    		countt.put(p.getUniqueId(), i2);
	}

//	final private void dropping(LivingEntity p, Location pl, Location tl) 
//	{
//
//        List<Location> line = new ArrayList<>();
//        
//        for(double an =0; an<10; an+=0.5) {
//        	line.add(pl.clone().add(0,-an,0));
//        }
//
//    	AtomicInteger u = new AtomicInteger();
//        line.forEach(l -> {
//    		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
//                @Override
//                public void run() 
//                {
//                	p.setGliding(true);
//    				p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 3, 1, 1, 1);
//    				p.teleport(l);
//                }
//    		},u.getAndIncrement()*2);
//    	});
//	}
//	

	public void mCounter(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("stoneboss") && d.getEntity() instanceof IronGolem&& !d.isCancelled() && d.getEntity().hasMetadata("ruined") && d.getDamager() instanceof Player) 
		{
			IronGolem p = (IronGolem)d.getEntity();
			Player dp = (Player) d.getDamager();
			String rn = gethero(p);
			if(ordeal.containsKey(p.getUniqueId())) {
				d.setCancelled(true);

				if(dp.getFallDistance()>=3 && dp.getWorld() == p.getWorld() && dp.getLocation().distance(p.getEyeLocation())<=2.63 && dp.getLocation().getY()>p.getLocation().getY()) {
					if(counterable.containsKey(p.getUniqueId())) {
		    			p.getWorld().spawnParticle(Particle.SNEEZE, p.getLocation().clone(), 500,2,2,2);

		                for(Player pe : OverworldRaids.getheroes(p)) {
							pe.setFallDistance(0);
		                }
		    			
						count.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
						count.putIfAbsent(p.getUniqueId(), 1);
						
		                Location rl = OverworldRaids.getraidloc(p).clone();
				        p.playHurtAnimation(0);
				        Bukkit.getScheduler().cancelTask(countt.get(p.getUniqueId()));
	                	p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,10,false,false));
	                	counterable.remove(p.getUniqueId());
		                p.teleport(rl);
		                p.setPortalCooldown(15);

				        OverworldRaids.getheroes(p).forEach(pe ->{
				        	pe.playSound(pe, Sound.ENTITY_IRON_GOLEM_HURT, 1, 0.1f);
				        	pe.sendMessage(ChatColor.AQUA + "[" + count.get(p.getUniqueId()) + "/5]");
				        	Random random1=new Random();
				        	double number1 = (random1.nextDouble()+1) * 4 * (random1.nextBoolean() ? -1 : 1);
				        	double number21 = (random1.nextDouble()+1) * 4 * (random1.nextBoolean() ? -1 : 1);
				        	Location esl1 = rl.clone().add(number1, 0.5, number21);
				        	pe.teleport(esl1);
				        });
				        
				        if(count.get(p.getUniqueId()) == 5) {
			                p.teleport(rl);
				        	
				        	p.setGlowing(true);

			        		if(ordt.containsKey(rn)) {
			        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
			        		}

	                		Holding.reset(p);
	                		Holding.ale(p).setInvisible(false);
		                	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
	                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
	                		Holding.ale(p).setInvulnerable(false);
	                		Holding.holding(null, Holding.ale(p), 300l);
	    		            rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	    		            
			            	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
			                for(Player pe : OverworldRaids.getheroes(p)) {
								if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
			                		pe.sendMessage(ChatColor.BLUE+"골렘: 크아아악..머리 아프다!");
								}
								else {
			                		pe.sendMessage(ChatColor.BLUE+"Golem: My Head!!!");
								}
			            		Holding.holding(pe, p, 300l);
			                }
				        	ordeal.remove(p.getUniqueId());
							
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
				        }
					}
				}
				else {

					d.setCancelled(true);
					if(p.getPortalCooldown()>0) {
						return;
					}
                    for(Player pe : OverworldRaids.getheroes(p)) {
                		if(pe.getWorld().equals(p.getWorld()) && pe.getWorld() == p.getWorld()) {
                			pe.setVelocity(BlockFace.NORTH_NORTH_WEST.getDirection().normalize().multiply(2.5));
                			pe.setVelocity(BlockFace.UP.getDirection().normalize().multiply(1.5));
        					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
    	                		pe.sendMessage(ChatColor.BOLD+"약해...");
        					}
        					else {
    	                		pe.sendMessage(ChatColor.BOLD+"Weak...");
        					}
                		}
                    }
				}
			}
			
		}
	}
	final private void mantleevent(LivingEntity p, EntityDamageByEntityEvent d) {

    	ordeal.put(p.getUniqueId(), true);
		strong.remove(p.getUniqueId());
		Bukkit.getScheduler().cancelTask(strongt.get(p.getUniqueId()));
		String rn = gethero(p);
        final Location rl = OverworldRaids.getraidloc(p).clone().add(-2, -0.5, 0);

        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }
        d.setCancelled(true);
		p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*0.2);
        p.teleport(rl.clone());
        Holding.holding(null, p, 1500l);
        Holding.invur(p, 100l);
        Holding.untouchable(p, 1500l);
        
        OverworldRaids.getheroes(p).forEach(pe ->{
        	Holding.invur(pe, 60l);
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendTitle(ChatColor.DARK_GRAY + (ChatColor.MAGIC + "123456"), ChatColor.DARK_GRAY + "전부 죽인다!!", 20, 35, 20);
 				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {	
                		pe.sendMessage(ChatColor.BOLD+"낙하 공격으로 적의 머리를 공격하세요");
	                }
	            }, 20); 
			}
			else {
        		pe.sendTitle(ChatColor.DARK_GRAY + (ChatColor.MAGIC + "123456"), ChatColor.DARK_GRAY + "Kill you all!!", 20, 35, 20);
 				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {	
                		pe.sendMessage(ChatColor.BOLD+"Hit the enemy's Head with falling attack");
	                }
	            }, 20); 
			}
        });
        
		p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation().clone(), 200,2,2,2,0.1);
		p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation().clone(), 200,2,2,2,0.1);
		
        int i1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
                mantle(Holding.ale(p),rl,rn);
            }
        }, 100, 105);
		ordt.put(rn, i1); 

			int i2 = Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {	

                if(ordt.containsKey(rn)) {
                	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
                	ordt.removeAll(rn);
                }
    			count.remove(p.getUniqueId());
                for(Player pe : OverworldRaids.getheroes(p)) {
            		if(pe.getWorld().equals(p.getWorld()) && pe.getWorld() == p.getWorld()) {
    					if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
	                		pe.sendMessage(ChatColor.BOLD+"골렘: 죽어라!");
    					}
    					else {
	                		pe.sendMessage(ChatColor.BOLD+"Golem: Die!");
    					}
            			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_HOGLIN_ANGRY, 1, 0);
                		Holding.invur(pe, 10l);
			        	p.setGlowing(true);
	            		pe.teleport(p);
						pe.removePotionEffect(PotionEffectType.BLINDNESS);
	            		pe.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 255 ,false,false));
	            		pe.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 60, 255 ,false,false));
            		}
                }
 				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {	
	                	ordeal.remove(p.getUniqueId());
	                	rb5cooldown.remove(p.getUniqueId()); 
                		p.removeMetadata("fake", RMain.getInstance());
                		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
    	                p.setInvulnerable(false);
    	                Holding.ale(p).setInvulnerable(false);
						p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 3000, 10,10,10);	
	                    for(Player pe : OverworldRaids.getheroes(p)) {
	                		p.removeMetadata("fake", RMain.getInstance());
	                		pe.setHealth(0);
	                    }
	                }
	            }, 5); 
            }
        }, 1505); 
		ordt.put(rn, i2); 
		
	}


	
	@SuppressWarnings("deprecation")
	public void mantle(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 100;
		if(d.getEntity().hasMetadata("stoneboss") && d.getEntity() instanceof IronGolem&& !d.isCancelled() && d.getEntity().hasMetadata("ruined")) 
		{
			IronGolem p = (IronGolem)d.getEntity();
			if(p.hasMetadata("failed")) {
				return;
			}
			if(!(p.getHealth() - d.getDamage() <= p.getMaxHealth()*0.2)|| !ordealable.containsKey(p.getUniqueId())|| ordeal.containsKey(p.getUniqueId()) || !OverworldRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))) {
				return;
			}
			if(rb5cooldown.containsKey(p.getUniqueId()))
	        {
	            long timer = (rb5cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
	            }
	            else 
	            {
	            	rb5cooldown.remove(p.getUniqueId()); // removing player from HashMap
	            	mantleevent(Holding.ale(p), d);
		            rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	            }
	        }
	        else 
	        {

            	mantleevent(Holding.ale(p), d);
	            rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
		
		}
	}
}