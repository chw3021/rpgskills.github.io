package io.github.chw3021.monsters.nether;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Spellcaster.Spell;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import com.google.common.util.concurrent.AtomicDouble;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.NethercoreRaids;
import io.github.chw3021.rmain.RMain;



public class HarvesterSkills extends NethercoreRaids{

	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> shcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> aicooldown = new HashMap<UUID, Long>();
	

	
	private static final HarvesterSkills instance = new HarvesterSkills ();
	public static HarvesterSkills getInstance()
	{
		return instance;
	}
	
	
	final private ArrayList<Location> raytrace(Location il, Double dis){
    	ArrayList<Location> line = new ArrayList<Location>();
        for(double d = 0.1; d <= dis; d += 0.3) {
            Location pl = il.clone();
			pl.add(il.getDirection().normalize().multiply(d));
			line.add(pl);
        }
        return line;
	}

	public void bowshoot(EntityShootBowEvent ev) 
	{
		if(ev.getEntity().hasMetadata("soulboss")){

			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
		    
        	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EVOKER_FANGS_ATTACK, 1f, 0f);

			p.getWorld().spawnParticle(Particle.SOUL, p.getLocation(), 10);
			AtomicInteger j = new AtomicInteger();
			
			Location tl = gettargetblock(p,15);
			if(((Mob)p).getTarget() != null) {
				tl = ((Mob)p).getTarget().getLocation();
			}
            String rn = gethero(p);
            
			raytrace(p.getLocation(),tl.distance(p.getLocation())).forEach(l ->{
        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                	p.swingMainHand();
	                	EvokerFangs ef = (EvokerFangs)p.getWorld().spawnEntity(l, EntityType.EVOKER_FANGS);
	                	ef.setVelocity(l.getDirection().normalize().multiply(1.5));
	                    ef.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	                    ef.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
	                    ef.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), true));
	                    ef.setMetadata("soul", new FixedMetadataValue(RMain.getInstance(), true));
	                	ef.setOwner(p);
	                	ef.setAttackDelay(0);
	                	ef.setInvulnerable(true);
	                	
	                }
	            }, j.getAndIncrement()*2); 
			});


		 }
	}



	private HashMap<UUID, Integer> cursable = new HashMap<UUID, Integer>();
	
	final private void cursed(LivingEntity p) {

    	cursable.remove(p.getUniqueId());
        Holding.holding(null, p, 20l);

        String rn = gethero(p);
		
        Location tl = gettargetblock(p,4).clone();
        
		p.getWorld().playSound(tl, Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1f, 0f);
    	p.getWorld().spawnParticle(Particle.FLAME, tl, 500, 4, 1, 4, 0);
    	p.getWorld().spawnParticle(Particle.SMOKE, tl, 500, 4, 1, 4, 0);
    	
    	
        ArrayList<Location> meats = new ArrayList<>();
        AtomicInteger j = new AtomicInteger();
        for(int i=0; i<30; i++) {
            Random random=new Random();
        	double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
        	double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
        	meats.add(tl.clone().add(number, 1, number2));
        }
        
        meats.forEach(l ->{
			int t= Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	EvokerFangs ef = (EvokerFangs)p.getWorld().spawnEntity(l, EntityType.EVOKER_FANGS);
                	ef.setVelocity(l.getDirection().normalize().multiply(1.5));
                    ef.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    ef.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
                    ef.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), true));
                    ef.setMetadata("soul", new FixedMetadataValue(RMain.getInstance(), true));
                	ef.setOwner(p);
                	ef.setAttackDelay(1);
                	ef.setInvulnerable(true);
                	p.getWorld().spawnParticle(Particle.SOUL, l, 10, 4, 0.2, 4, 0.2);
                	p.getWorld().spawnParticle(Particle.ASH, l, 50, 4, 0.2, 4, 0);
                	p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, l, 50, 4, 0.2, 4, 0.2);
					p.getWorld().playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 0.1f, 2f);
                	for(Entity e : p.getWorld().getNearbyEntities(l, 4,4,4)) {
                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
                			LivingEntity le = (LivingEntity)e;
                			le.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 200,1,false,false,false));
                			le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200,2,false,false,false));
                			le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 200,2,false,false,false));
							le.damage(3, p);
							
    						
                		}
                	}
                }
			}, j.incrementAndGet()*2+40);
        	ordt.put(rn, t);
        });
	}
	
	
	public void cursed(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 4;
		if(d.getEntity().hasMetadata("soulboss") && cursable.containsKey(d.getEntity().getUniqueId())) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
			if(ordeal.containsKey(p.getUniqueId())) {
				return;
			}
			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2);
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

	            	cursed(p);
	            	
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {

            	cursed(p);
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}

	private HashMap<UUID, Integer> blockt = new HashMap<>();
	
	final private void createHand(LivingEntity p,Location startLoc) {
	    World world = startLoc.getWorld();


	    List<BlockDisplay> thumb = new ArrayList<>();
	    List<BlockDisplay> indexFinger = new ArrayList<>();
	    List<BlockDisplay> middleFinger = new ArrayList<>();
	    List<BlockDisplay> ringFinger = new ArrayList<>();
	    List<BlockDisplay> pinkyFinger = new ArrayList<>();

	    for (int i = 0; i < 5; i++) {
	    	if(i<3) {
		        thumb.add(world.spawn(startLoc.clone().add(0.3, i * 0.2, 0), BlockDisplay.class, bd ->{
		        	bd.setBlock(getBd(Material.SOUL_FIRE));
		        }));
		        pinkyFinger.add(world.spawn(startLoc.clone().add(-1.2, i * 0.2, 0), BlockDisplay.class, bd ->{
		        	bd.setBlock(getBd(Material.SOUL_FIRE));
		        }));
	    	}
	    	if(i<4) {
	    		indexFinger.add(world.spawn(startLoc.clone().add(-0.3, i * 0.2, 0), BlockDisplay.class, bd ->{
		        	bd.setBlock(getBd(Material.SOUL_FIRE));
		        }));
	    		ringFinger.add(world.spawn(startLoc.clone().add(-0.9, i * 0.2, 0), BlockDisplay.class, bd ->{
		        	bd.setBlock(getBd(Material.SOUL_FIRE));
		        }));
	    	}
	    	middleFinger.add(world.spawn(startLoc.clone().add(-0.6, i * 0.2, 0), BlockDisplay.class, bd ->{
	        	bd.setBlock(getBd(Material.SOUL_FIRE));
	        }));
	    }
	    
	    BukkitTask bt = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
	        int step = 0;

	        @Override
	        public void run() {
	            moveFingerToCenter(thumb, startLoc);
	            moveFingerToCenter(indexFinger, startLoc);
	            moveFingerToCenter(middleFinger, startLoc);
	            moveFingerToCenter(ringFinger, startLoc);
	            moveFingerToCenter(pinkyFinger, startLoc);

	            world.playSound(startLoc, Sound.BLOCK_SOUL_SAND_HIT, 1.0f, 0f);
	            world.spawnParticle(Particle.SOUL_FIRE_FLAME, startLoc, 150, 2,0.2,2,0.1);

                for(Entity e : world.getNearbyEntities(startLoc,2,5,2)) {
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
						LivingEntity le = (LivingEntity)e;
						le.damage(7,p);
						le.teleport(p);
						Holding.holding(null, le, 50l);
					}
                }
                
	            if (step++ > 10) {
	                removeFingers(thumb);
	                removeFingers(indexFinger);
	                removeFingers(middleFinger);
	                removeFingers(ringFinger);
	                removeFingers(pinkyFinger);
	                
	                
	                if(blockt.containsKey(p.getUniqueId())) {
	                	Bukkit.getScheduler().cancelTask(blockt.remove(p.getUniqueId()));
	                }
	            }
	        }
	    }, 0L, 2L);
	    blockt.put(p.getUniqueId(), bt.getTaskId());
	    ordt.put(gethero(p), bt.getTaskId());
	}
	final private void moveFingerToCenter(List<BlockDisplay> finger, Location center) {
	    for (BlockDisplay block : finger) {
	        Vector moveDirection = center.toVector().subtract(block.getLocation().toVector());
	        double distance = moveDirection.length();  // 손가락과 중앙 사이의 거리 계산

	        if (distance > 0) {  // 충분히 멀리 있을 때만 이동
	            // 거리가 멀수록 빠르게, 가까워질수록 느리게: log(distance) 이용
	            double speed = Math.log(distance + 1) * 0.2;  // 거리 기반으로 속도 설정
	            moveDirection.normalize().multiply(speed);  // 속도 적용하여 이동
	            block.teleport(block.getLocation().add(moveDirection));
	        }
	    }
	}

	// 손가락 블록 제거 메서드
	final private void removeFingers(List<BlockDisplay> finger) {
	    for (BlockDisplay block : finger) {
	        block.remove();
	    }
	}
	
	
	private HashMap<UUID, Boolean> handable = new HashMap<UUID, Boolean>();
	
	final private void hand(LivingEntity p) {

    	final World w = p.getWorld();
        Holding.holding(null, p, 25l);
		Location pl = gettargetblock(p,6);
		w.playSound(pl, Sound.ENTITY_SHULKER_TELEPORT, 1.0f, 0f);
		w.spawnParticle(Particle.SOUL, pl, 150, 2,2,2);
		w.spawnParticle(Particle.SCULK_SOUL, pl, 150, 2,2,2);
		

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			createHand(p,pl);
            }
	   	}, 25);
	   	
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	handable.remove(p.getUniqueId());
            }
	   	}, 60);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			waveable.putIfAbsent(p.getUniqueId(), true);
            }
	   	}, 80);
	}
	

	public void hand(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("soulboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 8;
	
	
			if(p.hasMetadata("failed")|| ordeal.containsKey(p.getUniqueId()) || !handable.containsKey(p.getUniqueId())) {
				return;
			}
			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2);
	            d.setCancelled(true);
	            ordealable.put(p.getUniqueId(), true);
				return;
			}
					if(shcooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (shcooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                	shcooldown.remove(p.getUniqueId()); // removing player from HashMap
		                	hand(p);
		                	shcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
		            	hand(p);
		            	shcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}


	private HashMap<UUID, Boolean> phantom = new HashMap<UUID, Boolean>();
	
	final private void phantom(LivingEntity p) {

    	final World w = p.getWorld();
        Holding.holding(null, p, 40l);
        for(Player pe : NethercoreRaids.getheroes(p)) {
    		Location pfl = pe.getEyeLocation().clone();
    		w.playSound(pfl, Sound.ENTITY_PHANTOM_FLAP, 1.0f, 0f);
    		w.playSound(pfl, Sound.ENTITY_PHANTOM_SWOOP, 1.0f, 0f);
    		w.spawnParticle(Particle.SCULK_SOUL, pfl, 150, 2,2,2);
    		w.spawnParticle(Particle.BLOCK_MARKER, pfl, 20,1,1,1, getBd(Material.TRIAL_SPAWNER));
        }
		
		String rn = gethero(p);
        
		int t =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {

		        for(Player pe : NethercoreRaids.getheroes(p)) {
	        		Location pfl = pe.getEyeLocation().clone();
	        		w.playSound(pfl, Sound.ENTITY_PHANTOM_BITE, 1.0f, 0f);
                    Phantom phant = pe.getWorld().spawn(pe.getLocation().add(0, 5, 0), Phantom.class);
                    phant.setTarget(pe);
                    phant.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    phant.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
                    phant.setSize(25);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                        @Override
                        public void run() 
                        {
            		        phant.remove();
                        }
            		}, 100);
		        }
		        
            }
		}, 40);
        ordt.put(gethero(p), t);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	phantom.remove(p.getUniqueId());
            }
	   	}, 60);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			cursable.putIfAbsent(p.getUniqueId(), 1);
            }
	   	}, 180);
	}
	
	public void phantom(EntitySpellCastEvent d) 
	{
		if(d.getEntity().hasMetadata("soulboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 8;
			
			if(d.getSpell() != Spell.BLINDNESS) {
				return;
			}

			d.setCancelled(true);

			if(p.hasMetadata("failed")|| ordeal.containsKey(p.getUniqueId()) || !phantom.containsKey(p.getUniqueId())) {
				return;
			}
					if(aicooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (aicooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                	aicooldown.remove(p.getUniqueId()); // removing player from HashMap
		                	phantom(p);
		                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

		            	phantom(p);
	                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	

	private HashMap<UUID, Boolean> waveable = new HashMap<UUID, Boolean>();
	public void wave(EntitySpellCastEvent d) 
	{
		if(d.getEntity().hasMetadata("soulboss")) 
		{
			final LivingEntity p = (LivingEntity)d.getEntity();
			if(d.getSpell() != Spell.DISAPPEAR) {
				return;
			}
			d.setCancelled(true);

			if(p.hasMetadata("raid")) {
				if(!NethercoreRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = NethercoreRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				wave(p,tl);
			}
		}
	}


	private HashMap<UUID, Integer> hookt1 = new HashMap<UUID, Integer>();
	
	final private void waveStart(LivingEntity p, Location tl) {

        final Location pfl = p.getLocation().clone();
        
        final Vector pv = tl.clone().toVector().subtract(pfl.clone().toVector()).normalize();
        

		p.swingMainHand();
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1.0f, 0f);
			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME ,p.getLocation(), 200, 0.2,1,0.2,1);
			p.getWorld().spawnParticle(Particle.ENTITY_EFFECT ,p.getEyeLocation(), 10,Color.TEAL);
			Holding.holding(null, p, 5l);

		AtomicDouble jd = new AtomicDouble(1);

    	int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    		@Override
        	public void run() 
            {
    			if(p.isDead()) {
    				return;
    			}
    			Location pl = pfl.clone().add(pv.clone().normalize().multiply(jd.getAndAdd(0.5)));
    			
    			p.getWorld().playSound(pl, Sound.ENTITY_PIGLIN_ANGRY, 0.1f, 0f);
    			p.getWorld().spawnParticle(Particle.SCULK_SOUL ,pl, 50, 3,4,3,1);
    			p.getWorld().spawnParticle(Particle.ENTITY_EFFECT ,pl, 50, 3,4,3,1,Color.BLUE);
    			p.getWorld().spawnParticle(Particle.INSTANT_EFFECT ,pl, 50, 3,4,3,1);
    			p.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION_OMINOUS ,pl, 50, 3,4,3,1);
                
    			
    			if(pl.getBlock().isPassable()) {
					p.teleport(pl);
    			}

                for(Entity e : pl.getWorld().getNearbyEntities(pl,3,4,3)) {
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
						LivingEntity le = (LivingEntity)e;
						le.damage(3.5,p);
						le.teleport(p);
					}
                }
    			
            }
    	},5,2);
        hookt1.put(p.getUniqueId(), task);
        ordt.put(gethero(p), task);
        
    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    		@Override
        	public void run() 
            {
    			if(hookt1.containsKey(p.getUniqueId())) {
    				Bukkit.getScheduler().cancelTask(hookt1.get(p.getUniqueId()));
    				hookt1.remove(p.getUniqueId());
    			}
            }
    	},20);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			phantom.put(p.getUniqueId(), true);
            }
        }, 150); 
	}
	
	final private void wave(LivingEntity p, Location tl) {

		if(p.hasMetadata("failed")|| ordeal.containsKey(p.getUniqueId()) || !waveable.containsKey(p.getUniqueId())) {
			return;
		}
		if(rb8cooldown.containsKey(p.getUniqueId()))
        {
            long timer = (rb8cooldown.get(p.getUniqueId())/1000 + 9) - System.currentTimeMillis()/1000; 
            if(!(timer < 0))
            {
            }
            else 
            {
            	rb8cooldown.remove(p.getUniqueId()); // removing player from HashMap
            	waveStart(p, tl);
                
				rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
            }
        }
        else 
        {
        	waveStart(p, tl);
			rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}


	final private double whirl(Location tl, Integer j, World w) {
    	ArrayList<Location> ring = new ArrayList<Location>();

    	w.playSound(tl, Sound.ENTITY_BREEZE_WHIRL, 0.2f, 2f);
    	double an = 0;
    	for(; an<Math.PI*2; an +=Math.PI/90) {
    		ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an+j*0.25).multiply(an+j*0.5)));
    	}
    	ring.forEach(l -> {
			tl.getWorld().spawnParticle(Particle.TRIAL_OMEN, l, 2, 0.5,0.5,0.5,0);
			tl.getWorld().spawnParticle(Particle.OMINOUS_SPAWNING, l, 2, 0.5,0.5,0.5,0.1);
    		
    	});
    	
    	return an+j*0.5;
	}

	
	//private HashMap<UUID, Boolean> stormable = new HashMap<UUID, Boolean>();
	
	final private void storm(Location tl, LivingEntity p) {
		
		World w = tl.getWorld();
		tl.getWorld().playSound(tl, Sound.ENTITY_BREEZE_INHALE, 1.0f, 0f);
		tl.getWorld().spawnParticle(Particle.OMINOUS_SPAWNING, tl, 150, 2,0.5,2,0.1);
		
    	AtomicInteger j = new AtomicInteger();	
		for(int i = 0; i <15; i++) {
            int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	
                	double off = whirl(tl.clone(),j.getAndIncrement(), w);
					for(Entity e : tl.getWorld().getNearbyEntities(tl,off, 7, off)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(3.5,p);
							le.teleport(tl);
						}
					}
                }
            }, i*3+25); 	   
            ordt.put(gethero(p), t);                 	
        }
	}
	
	public void storm(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Mob) && d.getEntity().hasMetadata("soulboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 4;
	        

			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
				return;
			}
			if(p.getTarget() == null|| !(p.getTarget() instanceof Player)|| ordeal.containsKey(p.getUniqueId())||p.hasMetadata("failed")) {
				return;
			}
			final Player tar = (Player) p.getTarget();
			final Location ptl = tar.getLocation().clone();
			
					if(rb4cooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (rb4cooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                	rb4cooldown.remove(p.getUniqueId()); // removing player from HashMap
		                	
			                Holding.holding(null, p, 10l);

			                storm(ptl, p);
		                    
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		                    	public void run() 
		                        {	
		                 			handable.put(p.getUniqueId(), true);
		        	            }
		                    }, 46); 
		                    
	             			
							rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {


		                Holding.holding(null, p, 10l);

		                storm(ptl, p);
	                    
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                 		@Override
	                    	public void run() 
	                        {	
	                 			handable.put(p.getUniqueId(), true);
	        	            }
	                    }, 46); 
	                    
						rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	private HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, Boolean> ordeal = new HashMap<UUID, Boolean>();//soulboss 패턴 시작 여부 저장

	public HashMap<UUID, Integer> ast = new HashMap<UUID, Integer>();//ArmorStands damage 태스크 저장
	public HashMap<UUID, Integer> asdt = new HashMap<UUID, Integer>();//ArmorStands remove 태스크 저장
	
	final private void itemSpawn(String rn, LivingEntity p) {


		Stream<Player> ps = NethercoreRaids.getheroes(p).stream();
		Player pe = ps.findAny().get();
    	if(!pe.getWorld().equals(p.getWorld()) || pe.isDead()) {
    		return;
    	}

        final Location fl = pe.getLocation().clone().add(0, 2, 2);

		final World w = fl.getWorld();
		w.spawn(fl, Item.class, newmob -> {

			newmob.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setItemStack(new ItemStack(Material.SKELETON_SKULL));
			newmob.setGravity(false);
    		newmob.setCustomNameVisible(false);
    		newmob.setInvulnerable(true);
			newmob.setMetadata("harvesterskull", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
			
			int t2 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				int tick=0;
                @Override
                public void run() {
            		ps.filter(rp -> pe!=rp && rp.isValid()).forEach(rp ->{
            			Holding.holding(null, rp, 10l);
            		});
                	
                	if(tick++%40==0) {
                		changeFace(newmob, tick);
                	}
                	
                	Location pel = Holding.ale(pe).getLocation();
                	Location arl = Holding.ale(newmob).getLocation();

        	        Vector moveDirection = pel.toVector().subtract(arl.toVector());
        	        double distance = moveDirection.length();  // 손가락과 중앙 사이의 거리 계산

        	        if (distance > 0) {  // 충분히 멀리 있을 때만 이동
        	            // 거리가 멀수록 빠르게, 가까워질수록 느리게: log(distance) 이용
        	            double speed = Math.log(distance + 1) * 0.2;  // 거리 기반으로 속도 설정
        	            moveDirection.normalize().multiply(speed);  // 속도 적용하여 이동
        	            Holding.ale(newmob).setVelocity(moveDirection);
        	        }
                }
            }, 0,3);
			ordt.put(rn, t2);
			ast.put(newmob.getUniqueId(), t2);
		});
		
	}
	
	final private void changeFace(Item i, int tick) {
		i.getWorld().playSound(i.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1, 2);
		i.getWorld().spawnParticle(Particle.SOUL, i.getLocation(), 5);
		if((tick/40)%4==0) {
			i.setItemStack(new ItemStack(Material.WITHER_SKELETON_SKULL));
		}
		else if((tick/40)%3==0) {
			i.setItemStack(new ItemStack(Material.ZOMBIE_HEAD));
		}
		else if((tick/40)%2==0) {
			i.setItemStack(new ItemStack(Material.PIGLIN_HEAD));
		}
		else {
			i.setItemStack(new ItemStack(Material.SKELETON_SKULL));
		}
	}

	
	private HashMap<UUID,UUID> getbossbytotem = new HashMap<>();
	
	final private void totems(LivingEntity p) {
		String rn = p.getMetadata("raid").get(0).asString();
		Bukkit.getWorld("NethercoreRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
		

            Location rl = NethercoreRaids.getraidloc(p).clone();
            World w = rl.getWorld();
            
			
			for(int i = 0; i<4; i++) {

				ItemStack head = new ItemStack(Material.SKELETON_SKULL);
				
				if(i==0) {
					rl.add(4, 0.1, 4);
					head.setType(Material.WITHER_SKELETON_SKULL);
				}
				else if(i==1) {
					rl.add(-4, 0.1, 4);
					head.setType(Material.ZOMBIE_HEAD);
				}
				else if(i==2) {
					rl.add(-4, 0.1, -4);
					head.setType(Material.PIGLIN_HEAD);
				}
				else if(i==3) {
					rl.add(4, 0.1, -4);
				}
				
				w.spawn(rl, Illusioner.class, newmob -> {

					newmob.getEquipment().setHelmet(head);
		    		newmob.setCustomNameVisible(false);
					newmob.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
					newmob.setMetadata("harvestertotem", new FixedMetadataValue(RMain.getInstance(), rn));
		    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
		    		newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
		    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
					newmob.setGlowing(true);
		    		newmob.setCanJoinRaid(false);
		    		newmob.setPatrolTarget(null);
		    		newmob.setPatrolLeader(false);
		    		newmob.setSilent(true);
		    		newmob.setAI(false);
		    		newmob.setInvulnerable(true);
		    		newmob.setCanPickupItems(true);
		    		getbossbytotem.put(newmob.getUniqueId(), p.getUniqueId());
				});
			}
	}
	
	final private boolean judge(LivingEntity p, String rn) {
		Boolean bool = Bukkit.getWorld("NethercoreRaid").getEntities().stream().anyMatch(e -> e.hasMetadata("totem"+rn));
		if(bool) {
            for(Player pe : NethercoreRaids.getheroes(p)) {
    			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
            		pe.sendMessage(ChatColor.BOLD+"혼령의군주: 그들의 외침을 들어라...");
    			}
    			else {
            		pe.sendMessage(ChatColor.BOLD+"LordOfPhantoms: Listen to them...");
    			}
        		Holding.invur(pe, 60l);
    			p.getWorld().playSound(pe.getLocation(), Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1, 2);
        	}
			Bukkit.getWorld("NethercoreRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(newmob -> {
            	if(ast.containsKey(newmob.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(ast.remove(newmob.getUniqueId()));
            	}
				if(getbossbytotem.containsKey(newmob.getUniqueId())) {
					getbossbytotem.remove(newmob.getUniqueId());
				}
            	Holding.ale(newmob).remove();
			});

    		if(ordt.containsKey(rn)) {
    			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
    		}
            rb6cooldown.remove(p.getUniqueId());
	        int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	        		
	        		ordeal.remove(p.getUniqueId());
	                for(Player pe : NethercoreRaids.getheroes(p)) {
	            		pe.setHealth(0);
	            	}
	            }
	        }, 20);
			ordt.put(rn, t3);
		}
		else {
			bossfailed(p,rn);
		}
		return bool;
	}

	final long OrdealTime = 450;
	
	final private void ordeal(LivingEntity p, EntityDamageByEntityEvent d) {
		String rn = p.getMetadata("raid").get(0).asString();
        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }
        ordeal.put(p.getUniqueId(), true);
        Location rl = NethercoreRaids.getraidloc(p).clone();
		p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2);
        d.setCancelled(true);
    	p.teleport(rl.clone().add(0, 1, 0));
        Holding.holding(null, p, OrdealTime);
        Holding.untouchable(p, OrdealTime);
        for(Player pe : NethercoreRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"혼령의군주: 원혼들이 날뛰는 구나..");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"LordOfPhantoms: The vengeful spirits are running wild...");
			}
    		pe.teleport(rl.clone().add(2, 1.5, 2));
    		Holding.invur(pe, 40l);
        }

		p.getWorld().playSound(p.getLocation(), Sound.AMBIENT_SOUL_SAND_VALLEY_MOOD, 1, 2);
		
		
        int t1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
        		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_TRIAL_SPAWNER_SPAWN_MOB, 1, 0);
        		p.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION, p.getLocation(), 100, 3,2,3);
        		p.getWorld().spawnParticle(Particle.OMINOUS_SPAWNING, p.getLocation(), 100, 3,2,3);
        		p.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION_OMINOUS, p.getLocation(), 100, 3,2,3);
        		p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 100, 3,2,3);
        		totems(p);
        		itemSpawn(rn,p);
            }
        }, 40);
		ordt.put(rn, t1);
		
        int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	judge(p,rn);
            }
        }, OrdealTime);
		ordt.put(rn, t3);
	}
	

	final private void bossfailed(LivingEntity p, String rn) {
		if(ordt.containsKey(rn)) {
			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
		}
		ordeal.remove(p.getUniqueId());
    	p.playHurtAnimation(0);
    	Holding.reset(Holding.ale(p));
    	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 2);
		p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 10, 2,2,2);

		Bukkit.getWorld("NethercoreRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(newmob -> {
        	if(ast.containsKey(newmob.getUniqueId())) {
				Bukkit.getScheduler().cancelTask(ast.remove(newmob.getUniqueId()));
        	}
			if(getbossbytotem.containsKey(newmob.getUniqueId())) {
				getbossbytotem.remove(newmob.getUniqueId());
			}
        	Holding.ale(newmob).remove();
		});
		for(Player pe : NethercoreRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"혼령의군주: 네 운명이... 달라지지 않을 거다...!");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"LordOfPhantoms: Your fate... will not change...!");
			}
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
	    
	
	public void Ordeal(EntityDamageByEntityEvent d) 
	{
	    
		int sec =70;
		if(d.getEntity().hasMetadata("soulboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
			if(!(p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2)|| !ordealable.containsKey(p.getUniqueId())) {
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
		                ordeal(p,d);
			            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		            }
		        }
		        else 
		        {
	                ordeal(p,d);
		            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		        }
			}
	}
	

	public void Ordeal(EntityPickupItemEvent d) 
	{
			if(d.getItem().hasMetadata("harvesterskull")) {
				Item it = d.getItem();
				d.setCancelled(true);
				if(d.getEntity() instanceof Player) {
					Player p = (Player) d.getEntity();
					String rn =  getheroname(p);
					if(rn.equals(it.getMetadata("harvesterskull").get(0).asString())) {
						it.remove();
						p.playSound(p, Sound.ENTITY_PHANTOM_SWOOP, 1, 0);
						p.setHealth(0);
						if(ast.containsKey(it.getUniqueId())) {
							Bukkit.getScheduler().cancelTask(ast.remove(it.getUniqueId()));
	                	}
					}
				}
				else if(d.getEntity().hasMetadata("harvestertotem")){
					LivingEntity le = d.getEntity();
					LivingEntity p = (LivingEntity) Bukkit.getEntity(getbossbytotem.get(le.getUniqueId()));
					String rn = le.getMetadata("harvestertotem").get(0).asString();
					if(rn.equals(it.getMetadata("harvesterskull").get(0).asString())) {
						it.remove();
						if(ast.containsKey(it.getUniqueId())) {
							Bukkit.getScheduler().cancelTask(ast.remove(it.getUniqueId()));
	                	}
						itemSpawn(rn,p);
						
						if(it.getItemStack().getType() == le.getEquipment().getHelmet().getType()) {
							le.remove();
							le.getWorld().playSound(le.getLocation(), Sound.ENTITY_BREEZE_INHALE, 1.0f, 0f);
							le.getWorld().spawnParticle(Particle.TRIAL_OMEN, le.getLocation(), 150, 2,2,2);
							getbossbytotem.remove(le.getUniqueId());
							if(!getbossbytotem.containsValue(p.getUniqueId())) {
								bossfailed(p,rn);
							}
						}
						else {
							le.getWorld().playSound(le.getLocation(), Sound.ENTITY_BREEZE_WIND_BURST, 1.0f, 0f);
							le.getWorld().spawnParticle(Particle.SCULK_SOUL, le.getLocation(), 150, 2,2,2);
							for(Entity e : le.getNearbyEntities(3, 3, 3)) {
								if(le!=e && e instanceof LivingEntity) {
									LivingEntity pe = (LivingEntity)e;
									pe.damage(15,p);
									Holding.holding(null, pe, 40l);
								}
							}
						}
						
						
					}
				}
			}
	}
	
	
}