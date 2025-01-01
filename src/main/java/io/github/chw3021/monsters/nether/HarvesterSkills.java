package io.github.chw3021.monsters.nether;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
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
        for(double d = 0.1; d <= dis+2; d += 0.6) {
            Location pl = il.clone();
			pl.add(il.getDirection().normalize().multiply(d));
			line.add(pl);
        }
        return line;
	}

	public void bowshoot(ProjectileLaunchEvent ev) 
	{
		if(ev.getEntity().getShooter() instanceof LivingEntity){
			
		    LivingEntity p = (LivingEntity) ev.getEntity().getShooter();
		    
		    if(p.hasMetadata("soulboss")) {
		    	ev.setCancelled(true);

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
		                	ef.setVelocity(l.getDirection().normalize().multiply(2));
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
                	p.getWorld().spawnParticle(Particle.SOUL, tl, 10, 4, 0.2, 4, 0.2);
                	p.getWorld().spawnParticle(Particle.ASH, tl, 50, 4, 0.2, 4, 0);
                	p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, tl, 50, 4, 0.2, 4, 0.2);
					p.getWorld().playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 0.1f, 2f);
                	for(Entity e : p.getWorld().getNearbyEntities(tl, 3,4,3)) {
                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
                			LivingEntity le = (LivingEntity)e;
                			le.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 200,1,false,false,false));
                			le.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200,2,false,false,false));
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
			if(ordeal.containsKey(p.getUniqueId()) || p.hasMetadata("failed")) {
				return;
			}
            if (checkAndApplyCharge(p, d)) return;
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

    private ArmorStand spawnArmorStand(World world, Location location, String rn) {
        return world.spawn(location, ArmorStand.class, stand -> {
            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setInvisible(true);
            stand.setBasePlate(false);
            stand.setCollidable(false);
            stand.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
            stand.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
            stand.getEquipment().setHelmet(new ItemStack(Material.VERDANT_FROGLIGHT));
        });
    }
	final private void createHand(LivingEntity p,Location startLoc) {
	    World world = startLoc.getWorld();

        List<ArmorStand> thumb = new ArrayList<>();
        List<ArmorStand> indexFinger = new ArrayList<>();
        List<ArmorStand> middleFinger = new ArrayList<>();
        List<ArmorStand> ringFinger = new ArrayList<>();
        List<ArmorStand> pinkyFinger = new ArrayList<>();
        String rn = gethero(p);

        Double ver = 4d;
        Double hor = 0.8d;
        
        for (int i = 0; i < 5; i++) {
            if (i < 3) {
                thumb.add(spawnArmorStand(world, startLoc.clone().add(0.3*ver, i * hor, 0.2*ver), rn));
                pinkyFinger.add(spawnArmorStand(world, startLoc.clone().add(-1.2*ver, i * hor, 0.2*ver), rn));
            }
            if (i < 4) {
                indexFinger.add(spawnArmorStand(world, startLoc.clone().add(-0.3*ver, i * hor, 0.1*ver), rn));
                ringFinger.add(spawnArmorStand(world, startLoc.clone().add(-0.9*ver, i * hor, 0.1*ver), rn));
            }
            middleFinger.add(spawnArmorStand(world, startLoc.clone().add(-0.6*ver, i * hor, -0.1*ver), rn));
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
						le.damage(4,p);
						le.teleport(startLoc);
						Holding.holding(null, le, 5l);
					}
                }
                
	            if (step++ > 20) {
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
	final private void moveFingerToCenter(List<ArmorStand> finger, Location center) {
	    for (ArmorStand block : finger) {
	        Vector moveDirection = center.toVector().subtract(block.getLocation().toVector());
	        double distance = moveDirection.length();  // 손가락과 중앙 사이의 거리 계산

	        if (distance > 0) {  // 충분히 멀리 있을 때만 이동
	            // 거리가 멀수록 빠르게, 가까워질수록 느리게: log(distance) 이용
	            double speed = Math.log(distance + 1) * 0.1;  // 거리 기반으로 속도 설정
	            moveDirection.normalize().multiply(speed);  // 속도 적용하여 이동
	            block.teleport(block.getLocation().add(moveDirection));
	        }
	    }
	}

	// 손가락 블록 제거 메서드
	final private void removeFingers(List<ArmorStand> finger) {
	    for (ArmorStand block : finger) {
	        block.remove();
	    }
	}
	
	
	private HashMap<UUID, Boolean> handable = new HashMap<UUID, Boolean>();
	
	final private void hand(LivingEntity p) {

    	final World w = p.getWorld();
        Holding.holding(null, p, 25l);
		Location pl = gettargetblock(p,6);
		w.playSound(pl, Sound.ENTITY_SHULKER_TELEPORT, 1.0f, 0f);
		w.spawnParticle(Particle.SOUL_FIRE_FLAME, pl, 150, 2,2,2);
		w.spawnParticle(Particle.WHITE_SMOKE, pl, 10, 2,2,2);
		w.spawnParticle(Particle.TRIAL_OMEN, pl, 150, 2,2,2);
		

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
			p.getWorld().playSound(p, Sound.ENTITY_BREEZE_CHARGE, 0.6f, 0.6f);
			p.getWorld().playSound(p, Sound.PARTICLE_SOUL_ESCAPE, 0.6f, 1.5f);
			
			int sec = 6;
	
	
			if(p.hasMetadata("failed")|| ordeal.containsKey(p.getUniqueId()) || !handable.containsKey(p.getUniqueId())) {
				return;
			}
            if (checkAndApplyCharge(p, d)) return;
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
			int sec = 4;
			
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
    			Location pl = pfl.clone().add(pv.clone().normalize().multiply(jd.getAndAdd(0.8)));
    			
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
    		ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an+j*0.25).multiply(an*0.45+j*0.06)));
    	}
    	ring.forEach(l -> {
			tl.getWorld().spawnParticle(Particle.TRIAL_OMEN, l, 1, 0.5,0.5,0.5,0);
			tl.getWorld().spawnParticle(Particle.OMINOUS_SPAWNING, l, 1, 0.5,0.5,0.5,0.1);
    		
    	});
    	
    	return an*0.35+j*0.05;
	}

	
	//private HashMap<UUID, Boolean> stormable = new HashMap<UUID, Boolean>();
	
	final private void storm(Location tl, LivingEntity p) {
		
		World w = tl.getWorld();
		tl.getWorld().playSound(tl, Sound.ENTITY_BREEZE_INHALE, 1.0f, 0f);
		tl.getWorld().spawnParticle(Particle.OMINOUS_SPAWNING, tl, 150, 2,0.5,2,0.1);
		
    	AtomicInteger j = new AtomicInteger();	
		for(int i = 0; i <12; i++) {
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
			int sec = 6;
	        

            if (checkAndApplyCharge(p, d)) return;
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


	public HashMap<UUID, Integer> ast = new HashMap<UUID, Integer>();//ArmorStands damage 태스크 저장
	public HashMap<UUID, Integer> asdt = new HashMap<UUID, Integer>();//ArmorStands remove 태스크 저장
	private void itemSpawn(String rn, LivingEntity p) {
	    Collection<Player> playerList = NethercoreRaids.getheroes(p);

	    // 조건을 filter로 처리
	    Player pe = playerList.stream()
	                          .filter(player -> player.getWorld().equals(p.getWorld()) && !player.isDead())
	                          .findAny()
	                          .orElse(null);

	    if (pe == null) {
	        // 조건에 맞는 플레이어가 없을 경우 재시도
	        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), () -> itemSpawn(rn, p), 20);
	        return;
	    }

	    Location fl = pe.getLocation().clone().add(0, 2.5, 0);
	    World w = fl.getWorld();

	    Item newmob = w.dropItem(fl.clone(), new ItemStack(Material.WITHER_SKELETON_SKULL));
	    newmob.setPickupDelay(20);
	    newmob.setMetadata("harvesterskull", new FixedMetadataValue(RMain.getInstance(), rn));
	    newmob.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), true));
	    newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    newmob.setThrower(p.getUniqueId());
	    newmob.setGravity(false);
	    newmob.setInvulnerable(true);
	    newmob.setGlowing(true);
	    newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	    changeFace(newmob, 0);

	    int t2 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        int tick = 0;

	        @Override
	        public void run() {
	            playerList.stream()
	                      .filter(rp -> pe != rp && rp.isValid())
	                      .forEach(rp -> Holding.holding(null, rp, 10L));
	            if ((tick += 2) % 80 == 0) {
	                changeFace(newmob, tick);
	            }

	            Location pel = Holding.ale(pe).getLocation().add(0, 1, 0);
	            Location arl = Holding.ale(newmob).getLocation();

	            if (!pel.getWorld().equals(arl.getWorld()) || pe.isDead()) {
	                Holding.ale(newmob).remove();
	                if (ast.containsKey(newmob.getUniqueId())) {
	                    Bukkit.getScheduler().cancelTask(ast.remove(newmob.getUniqueId()));
	                }
	                itemSpawn(rn, p);
	            }

	            Vector moveDirection = pel.toVector().subtract(arl.toVector());
	            double distance = moveDirection.length();

	            if (distance > 0) {
	                double speed = Math.log(distance + 0.8) * 0.06;
	                moveDirection.normalize().multiply(speed);
	                Holding.ale(newmob).setVelocity(moveDirection);
	            }
	        }
	    }, 0, 2);

	    ordt.put(rn, t2);
	    ast.put(newmob.getUniqueId(), t2);
	}

	
	
	final private void changeFace(Item i, int tick) {
	    i.getWorld().playSound(i.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1, 2);
	    i.getWorld().spawnParticle(Particle.SOUL, i.getLocation(), 5);

	    UUID targetValue = i.getThrower(); // 찾고자 하는 value
	    Set<UUID> keys = getbossbytotem.entrySet()
	        .stream()
	        .filter(entry -> entry.getValue().equals(targetValue))
	        .map(Map.Entry::getKey)
	        .collect(Collectors.toSet());

	    // 헬멧 아이템 리스트 생성
	    List<ItemStack> helmetItems = new ArrayList<>();
	    for (UUID key : keys) {
	        Entity entity = Bukkit.getEntity(key);
	        if (entity instanceof LivingEntity) {
	            ItemStack helmet = ((LivingEntity) entity).getEquipment().getHelmet();
	            if (helmet != null) {
	                helmetItems.add(helmet);
	            }
	        }
	    }

	    // 헬멧 아이템이 없으면 기본값 사용
	    if (helmetItems.isEmpty()) {
	    	return;
	    }

	    // 4초(80 ticks)마다 순차적으로 아이템을 변경
	    int index = (tick / 80) % helmetItems.size(); // 리스트 크기에 따라 순환
	    ItemStack newItem = helmetItems.get(index);

	    i.setItemStack(newItem);
	}

	
	private HashMap<UUID,UUID> getbossbytotem = new HashMap<>();
	
	private void totems(LivingEntity p) {
		String rn = p.getMetadata("raid").get(0).asString();
		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
		

            final Location rfl = NethercoreRaids.getraidloc(p).clone();
            final World w = rfl.getWorld();
            
            
			
			for(int i = 0; i<4; i++) {

				ItemStack head = new ItemStack(Material.SKELETON_SKULL);
	            Location rl = null;
				
				if(i==0) {
					rl = rfl.clone().add(4, 0.1, 4);
					head.setType(Material.WITHER_SKELETON_SKULL);
				}
				else if(i==1) {
					rl = rfl.clone().add(-4, 0.1, 4);
					head.setType(Material.ZOMBIE_HEAD);
				}
				else if(i==2) {
					rl = rfl.clone().add(-4, 0.1, -4);
					head.setType(Material.PIGLIN_HEAD);
				}
				else {
					rl = rfl.clone().add(4, 0.1, -4);
				}

	            
				w.spawn(rl, Illusioner.class, newmob -> {

					newmob.getEquipment().setHelmet(head);
		    		newmob.setCanPickupItems(true);
		    		newmob.setCustomNameVisible(false);
					newmob.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
					newmob.setMetadata("totem"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
					newmob.setMetadata("harvestertotem", new FixedMetadataValue(RMain.getInstance(), rn));
		    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
		    		newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
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
		Boolean bool = p.getWorld().getEntities().stream().anyMatch(e -> e.hasMetadata("totem"+rn));
		if(bool) {
    		Holding.invur(p, 20l);
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
			p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(newmob -> {
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
	        int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {

	                rb6cooldown.remove(p.getUniqueId());
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

	final long OrdealTime = 520;
	
	final private void ordeal(LivingEntity p, EntityDamageByEntityEvent d) {
		String rn = p.getMetadata("raid").get(0).asString();
        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }
        ordeal.put(p.getUniqueId(), true);
        final Location rl = NethercoreRaids.getraidloc(p).clone();
		p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2);
        d.setCancelled(true);
        p.playEffect(EntityEffect.WARDEN_TENDRIL_SHAKE);
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

		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(newmob -> {
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
			if(!(p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2)|| !ordealable.containsKey(p.getUniqueId())|| ordeal.containsKey(p.getUniqueId())) {
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
				if(d.getEntity() instanceof Player){
					Player p = (Player) d.getEntity();
					p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 0.1f, 0f);
					p.setVelocity(p.getVelocity().multiply(0.2));
				}
				if(d.getEntity().hasMetadata("harvestertotem")){
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
							le.getWorld().spawnParticle(Particle.TRIAL_OMEN, le.getLocation(), 300, 2,2,2);
							getbossbytotem.remove(le.getUniqueId());
							if(!getbossbytotem.containsValue(p.getUniqueId())) {
								bossfailed(p,rn);
							}
						}
						else {
							le.getWorld().playSound(le.getLocation(), Sound.ENTITY_BREEZE_WIND_BURST, 1.0f, 0f);
							le.getWorld().spawnParticle(Particle.SCULK_SOUL, le.getLocation(), 300, 2,2,2);
							for(Entity e : le.getNearbyEntities(3, 3, 3)) {
								if(le!=e && e instanceof LivingEntity) {
									LivingEntity pe = (LivingEntity)e;
									pe.damage(15,p);
									Holding.holding(null, pe, 20l);
							        final Location rl = NethercoreRaids.getraidloc(p).clone();
							        pe.teleport(rl);
								}
							}
						}
						
						
					}
				}
			}
	}
	
	
}