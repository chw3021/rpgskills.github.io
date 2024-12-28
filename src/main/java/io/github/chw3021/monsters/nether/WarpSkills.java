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
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Vex;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import com.google.common.util.concurrent.AtomicDouble;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.NethercoreRaids;
import io.github.chw3021.rmain.RMain;



public class WarpSkills extends NethercoreRaids{

	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> shcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> aicooldown = new HashMap<UUID, Long>();
	

	
	private static final WarpSkills instance = new WarpSkills ();
	public static WarpSkills getInstance()
	{
		return instance;
	}
	
	public void bowshoot(ProjectileLaunchEvent ev) 
	{
		if(ev.getEntity().getShooter() instanceof LivingEntity){
			
		    LivingEntity p = (LivingEntity) ev.getEntity().getShooter();
		    
		    if(p.hasMetadata("warpedboss") && ev.getEntity().getType() == EntityType.BREEZE_WIND_CHARGE) {
		        String rn = gethero(p);

	        	p.getWorld().playSound(p.getLocation(), Sound.ITEM_NETHER_WART_PLANT, 1f, 0f);

				p.getWorld().spawnParticle(Particle.WARPED_SPORE, p.getLocation(), 10);
				
				Snowball sn = p.launchProjectile(Snowball.class);
				sn.setShooter(p);
				sn.setItem(new ItemStack(Material.WARPED_NYLIUM));
				sn.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), true));
				sn.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
				sn.setMetadata("warpedSeed", new FixedMetadataValue(RMain.getInstance(), true));
		    	ev.setCancelled(true);
		    }
		    

		 }
	}


	public void hit(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof Snowball) 
		{
			Snowball po = (Snowball)d.getEntity();
			if(po.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) po.getShooter();
				if(po.hasMetadata("warpedSeed")) {
            		final Location l = d.getHitEntity() != null ? d.getHitEntity().getLocation() : d.getHitBlock().getLocation();

					l.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l, 50);
					l.getWorld().spawnParticle(Particle.BLOCK, l, 100,1.5,1.5,1.5,getBd(Material.WARPED_PLANKS));
					l.getWorld().playSound(l, Sound.ITEM_CROP_PLANT, 1f, 0.5f);
					
					tracking(p,l);
				}
			}
			
		}
	}
	
	
	private void tracking(LivingEntity shooter, Location hit) {
	    World world = shooter.getWorld();
	    Collection<Player> players = NethercoreRaids.getheroes(shooter);
	    String rn = gethero(shooter);

	    // 플레이어 추적
	    for (Player target : players) {
	        if (!target.isValid() || !target.getWorld().equals(world)) continue;

	        Location hitLocation = hit.clone().add(0, 1, 0); // 시작 지점
	        final Location targetEyeLoc = target.getLocation().clone().add(0, 1, 0);
	        Vector direction = targetEyeLoc.toVector().subtract(hitLocation.toVector()).normalize();

	        AtomicInteger task = new AtomicInteger(); // AtomicInteger로 태스크 ID 저장

	        task.set(Bukkit.getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            int tick = 0; // Tick counter
	            Location currentLocation = hitLocation.clone(); // 현재 위치를 저장

	            @Override
	            public void run() {
	                if (tick >= 50) { 
	                    Bukkit.getScheduler().cancelTask(task.get()); // AtomicInteger에서 ID 가져오기
	                    return;
	                }

	                // 파티클 생성
	    	        Location targetEyeLoc2 = target.getLocation().clone().add(0, 1, 0);
	    	        Vector direction2 = targetEyeLoc2.toVector().subtract(currentLocation.clone().toVector()).normalize();
	                currentLocation.add(direction.clone().multiply(0.23));
    				if(shooter.hasMetadata("ruined")) {
    					currentLocation.add(direction2.clone().multiply(0.1));
    				}
	                world.spawnParticle(Particle.DUST, currentLocation, 1, new Particle.DustOptions(Color.TEAL, 2f));
	                world.spawnParticle(Particle.DUST, currentLocation, 5,0.2,0.2,0.2, new Particle.DustOptions(Color.fromRGB(200, 200, 250), 2f));
	                world.spawnParticle(Particle.WARPED_SPORE, currentLocation, 5);

	                world.playSound(currentLocation, Sound.ITEM_NETHER_WART_PLANT, 0.1f, 1.2f);

	                // 플레이어와 충돌 체크
	                if (currentLocation.distance(target.getLocation().clone().add(0, 1, 0)) <= 1) {
	                    // 피해 입히기
	                    Holding.holding(null, target, 20L);
	                    target.damage(5, shooter);

	                    // 추적 중단
	                    Bukkit.getScheduler().cancelTask(task.get()); // AtomicInteger에서 ID 가져오기
	                    return;
	                }

	                tick++;
	            }
	        }, 0, 1)); // 반복 실행 (0 delay, 2 tick 간격)

	        ordt.put(rn, task.get()); // AtomicInteger에서 ID 가져오기
	    }
	}



	private HashMap<UUID, Integer> cursable = new HashMap<UUID, Integer>();
	
	final private void cursed(LivingEntity p) {

    	cursable.remove(p.getUniqueId());
        Holding.holding(null, p, 20l);

        String rn = gethero(p);
		
        Location tl = gettargetblock(p,5).clone();
        
		p.getWorld().playSound(tl, Sound.ENTITY_BREEZE_CHARGE, 1f, 0f);
    	p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, tl, 500, 4, 1, 4, 0);
    	p.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION_OMINOUS, tl, 500, 4, 1, 4, 0);
    	p.getWorld().spawnParticle(Particle.WHITE_SMOKE, tl, 50, 1, 1, 1);
    	
    	
        ArrayList<Location> meats = new ArrayList<>();
        AtomicInteger j = new AtomicInteger();
        for(int i=0; i<12; i++) {
            Random random=new Random();
        	double number = random.nextDouble() * 3 * (random.nextBoolean() ? -1 : 1);
        	double number2 = random.nextDouble() * 3 * (random.nextBoolean() ? -1 : 1);
        	meats.add(tl.clone().add(number, 1, number2));
        }
        
        meats.forEach(l ->{
			int t= Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
    				Snowball sn = p.getWorld().spawn(l.clone().add(0, 6, 0),Snowball.class);
    				sn.setVelocity(new Vector(0,-0.4,0)); 
    				sn.setGravity(true);
    				sn.setGlowing(true);
    				sn.setShooter(p);
    				sn.setItem(new ItemStack(Material.STRIPPED_WARPED_STEM));
    				sn.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), true));
    				sn.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
    				sn.setMetadata("warpedSeed", new FixedMetadataValue(RMain.getInstance(), true));
                	p.getWorld().spawnParticle(Particle.COMPOSTER, tl.add(0, 6, 0), 300, 4, 0.2, 4, 0.2);
                	p.getWorld().spawnParticle(Particle.WARPED_SPORE, tl.add(0, 6, 0), 300, 4, 0.2, 4, 0.2);
					p.getWorld().playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 0.1f, 2f);
                }
			}, j.incrementAndGet()*2+40);
        	ordt.put(rn, t);
        });
	}
	
	
	public void cursed(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 7;
		if(d.getEntity().hasMetadata("warpedboss") && cursable.containsKey(d.getEntity().getUniqueId())) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
			if(ordeal.containsKey(p.getUniqueId())) {
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
            stand.getEquipment().setHelmet(new ItemStack(Material.WARPED_STEM));
            stand.getAttribute(Attribute.SCALE).setBaseValue(2);
        });
    }
    private void summonWoodPillar(LivingEntity boss, Location startLoc) {
        World world = startLoc.getWorld();
        String rn = gethero(boss);

        List<ArmorStand> pillars = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Location pillarLoc = startLoc.clone().add(0, i * 1.3, 0); // 기둥 블록 간격 1
            pillars.add(spawnArmorStand(world, pillarLoc, rn));
        }

        BukkitTask bt = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
            int tick = 0;

            @Override
            public void run() {
                Player target = NethercoreRaids.getheroes(boss).stream()
                        .filter(p -> p.isValid() && p.getWorld().equals(world))
                        .min((p1, p2) -> Double.compare(p1.getLocation().distance(startLoc), p2.getLocation().distance(startLoc)))
                        .orElse(null);

                if (target == null || tick >= 60) { // 3초 이후 또는 대상 없음
                    removeFingers(pillars);
                    this.cancel();
                    return;
                }

                movePillarTowardsPlayer(pillars, target.getLocation());

                for (Entity e : world.getNearbyEntities(pillars.get(0).getLocation(), 1.5, 8, 1.5)) {
                    if (e instanceof Player && e != boss) {
                        Player player = (Player) e;
                        player.damage(3.4, boss);
                        player.setVelocity(new Vector(0, -0.5, 0)); 
                        removeFingers(pillars);
                        this.cancel();
                        return;
                    }
                }

                tick++;
            }

            private void cancel() {
                Bukkit.getScheduler().cancelTask(this.hashCode());
            }
        }, 0L, 2L); // 0틱 지연, 2틱 간격으로 실행

        blockt.put(boss.getUniqueId(), bt.getTaskId());
    }

    private void movePillarTowardsPlayer(List<ArmorStand> pillars, Location targetLoc) {
        for (ArmorStand block : pillars) {
        	block.getWorld().spawnParticle(Particle.WARPED_SPORE, block.getLocation(), 10, 1.5, 1.5, 1.5, 0.1);
            Vector moveDirection = targetLoc.toVector().subtract(block.getLocation().toVector());
            double distance = moveDirection.length();

            if (distance > 0.5) {
                double speed = Math.log(distance + 1) * 0.2;
                moveDirection.normalize().multiply(speed);
                block.teleport(block.getLocation().add(moveDirection));
            }
        }
    }

    private void removeFingers(List<ArmorStand> pillars) {
        for (ArmorStand pillar : pillars) {
            pillar.remove();
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
     			summonWoodPillar(p,pl);
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
		if(d.getEntity().hasMetadata("warpedboss")) 
		{
			Mob p = (Mob)d.getEntity();
			p.getWorld().playSound(p, Sound.ENTITY_BREEZE_CHARGE, 0.6f, 0.6f);
			p.getWorld().playSound(p, Sound.PARTICLE_SOUL_ESCAPE, 0.6f, 1.5f);
			
			int sec = 9;
	
	
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


	
	final private void phantom(LivingEntity p) {

    	final World w = p.getWorld();
        Holding.holding(null, p, 40l);
        for(Player pe : NethercoreRaids.getheroes(p)) {
    		Location pfl = pe.getEyeLocation().clone();
    		w.playSound(pfl, Sound.ENTITY_PHANTOM_FLAP, 1.0f, 2f);
    		w.playSound(pfl, Sound.ENTITY_VEX_AMBIENT, 1.0f, 0f);
    		w.spawnParticle(Particle.SCULK_SOUL, pfl, 150, 2,2,2);
    		w.spawnParticle(Particle.BLOCK_MARKER, pfl, 20,1,1,1, getBd(Material.WARPED_SLAB));
        }
		
		String rn = gethero(p);
        
		int t =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {

		        for(Player pe : NethercoreRaids.getheroes(p)) {
	        		Location pfl = pe.getEyeLocation().clone();
	        		w.playSound(pfl, Sound.ENTITY_VEX_CHARGE, 1.0f, 0f);
	        		Vex phant = pe.getWorld().spawn(pe.getLocation().add(0, 5, 0), Vex.class);
                    phant.setTarget(pe);
                    phant.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    phant.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
                    phant.getAttribute(Attribute.SCALE).setBaseValue(2.5);
                    phant.getEquipment().setHelmet(new ItemStack(Material.WARPED_WART_BLOCK));
                    phant.getEquipment().setItemInMainHand(new ItemStack(Material.WARPED_FUNGUS_ON_A_STICK));
                    phant.setInvulnerable(true);
                    phant.setCharging(true);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                        @Override
                        public void run() 
                        {
            		        phant.remove();
                        }
            		}, 120);
		        }
		        
            }
		}, 40);
        ordt.put(gethero(p), t);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			cursable.putIfAbsent(p.getUniqueId(), 1);
            }
	   	}, 180);
	}
	
	public void phantom(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("warpedboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 15;
			

			if(p.hasMetadata("failed")|| ordeal.containsKey(p.getUniqueId())) {
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
	public void wave(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("warpedboss")) 
		{
			final LivingEntity p = (LivingEntity)d.getEntity();

			if(p.hasMetadata("raid")) {
				if(!NethercoreRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = NethercoreRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				wave(p,tl);
			}
		}
	}
	
	private HashMap<UUID, Integer> whirlTaskMap = new HashMap<>();
	final private void waveStart(LivingEntity p, Location tl) {
	    final World world = p.getWorld();
	    final Location startLocation = p.getLocation().clone();
	    final Vector direction = tl.clone().toVector().subtract(startLocation.toVector()).normalize();

	    // 초기 효과음 및 이펙트
	    p.swingMainHand();
	    world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
	    world.playSound(p.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1.0f, 0f);
	    world.spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation(), 200, 0.2, 1, 0.2, 1);
	    world.spawnParticle(Particle.WHITE_ASH, p.getLocation(), 200, 0.2, 1, 0.2, 1);

	    AtomicInteger step = new AtomicInteger(0);
	    AtomicDouble radius = new AtomicDouble(0.5);

	    // 회오리 생성 및 확장
	    int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {
	            if (p.isDead() || step.get() > 50) {
	                // 회오리 제거 및 태스크 종료
	                if (whirlTaskMap.containsKey(p.getUniqueId())) {
	                    Bukkit.getScheduler().cancelTask(whirlTaskMap.remove(p.getUniqueId()));
	                }
	                return;
	            }

	            // 현재 위치에서 회오리 생성
	            double angleStep = Math.PI / 90; // 회오리 각도 간격
	            for (double angle = 0; angle < Math.PI * 2; angle += angleStep) {
	                Location particleLocation = startLocation.clone()
	                        .add(direction.clone().multiply(step.get() * 0.2)) // 진행 방향으로 이동
	                        .add(Math.cos(angle) * radius.get(), 0, Math.sin(angle) * radius.get()); // 반지름 기준 회전
	                world.spawnParticle(Particle.TRIAL_OMEN, particleLocation, (int) (20 * (angle / 2d)), 0.1, angle / 2d, 0.1, 0);
	            }

	            // 회오리 크기 증가
	            radius.addAndGet(0.1);

	            // 피해 판정
	            Location currentLocation = startLocation.clone().add(direction.clone().multiply(step.get() * 0.2));
	            for (Entity entity : world.getNearbyEntities(currentLocation, radius.get(), 2, radius.get())) {
	                if (entity instanceof LivingEntity && entity != p && !entity.hasMetadata("fake")) {
	                    LivingEntity target = (LivingEntity) entity;

	                    // 중앙으로의 방향 벡터 계산
	                    Vector pullDirection = currentLocation.clone()
	                            .subtract(target.getLocation()) // 중앙에서 타겟까지의 방향
	                            .toVector().normalize(); // 단위 벡터로 정규화

	                    // 휩쓸림 효과 적용
	                    target.setVelocity(pullDirection.multiply(0.5).setY(0.5)); // 위로 약간 띄우며 중앙으로 당김

	                    target.damage(5.0, p);
	                }
	            }

	            step.incrementAndGet(); // 다음 단계
	        }
	    }, 20L, 2L);

	    whirlTaskMap.put(p.getUniqueId(), taskId);
	    ordt.put(gethero(p), taskId);
	}

	final private void wave(LivingEntity p, Location tl) {
	    if (p.hasMetadata("failed") || ordeal.containsKey(p.getUniqueId()) || !waveable.containsKey(p.getUniqueId())) {
	        return;
	    }

	    if (rb8cooldown.containsKey(p.getUniqueId())) {
	        long timer = (rb8cooldown.get(p.getUniqueId()) / 1000 + 11) - System.currentTimeMillis() / 1000;
	        if (timer < 0) {
	            rb8cooldown.remove(p.getUniqueId());
	            waveStart(p, tl);
	            rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
	    } else {
	        waveStart(p, tl);
	        rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	    }
	}
	final private double whirl(final Location tl, Integer j, World w) {
	    ArrayList<Location> treeLocations = new ArrayList<>();

	    w.playSound(tl, Sound.BLOCK_END_PORTAL_FRAME_FILL, 0.2f, 0.5f);

	    // 트렁크(나무 줄기) 생성
	    double trunkHeight = 4 + j; // 줄기의 높이는 j 값에 따라 증가
	    for (double y = 0; y < trunkHeight; y += 0.2) {
	        treeLocations.add(tl.clone().add(0, y, 0)); // 줄기는 y 축으로 올라감
	    }

	    // 브랜치(나뭇가지) 생성
	    double branchLength = 2 + j * 0.5; // 나뭇가지 길이
	    double angleStep = Math.PI / 4; // 각도 간격
	    for (double angle = 0; angle < Math.PI * 2; angle += angleStep) {
	        for (double length = 0; length < branchLength; length += 0.3) {
	            // 나뭇가지는 줄기 상단에서 시작
	            Location branchBase = tl.clone().add(0, trunkHeight, 0);
	            treeLocations.add(branchBase.clone().add(
	                Math.cos(angle) * length, // x 방향
	                length * 0.5,             // 가지는 위로 살짝 기울어짐
	                Math.sin(angle) * length  // z 방향
	            ));
	        }
	    }

	    // 각 위치에 파티클 생성
	    treeLocations.forEach(location -> {
	        w.spawnParticle(Particle.BLOCK, location, 60, 0.1, 0.1, 0.1, 0, getBd(Material.WARPED_STEM));
	    });
        w.spawnParticle(Particle.BLOCK, tl.clone().add(0, trunkHeight+3, 0), 350*j, j, j, j, 0.05, getBd(Material.WARPED_WART_BLOCK));

	    return 2 + j * 0.5;
	}

	
	
	//private HashMap<UUID, Boolean> stormable = new HashMap<UUID, Boolean>();
	
	final private void storm(Location tl, LivingEntity p) {
		
		World w = tl.getWorld();
		tl.getWorld().playSound(tl, Sound.ENTITY_BREEZE_INHALE, 1.0f, 0f);
		tl.getWorld().spawnParticle(Particle.OMINOUS_SPAWNING, tl, 150, 2,0.5,2,0.1);
		
    	AtomicInteger j = new AtomicInteger();	
		for(int i = 0; i <4; i++) {
            int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	
                	double off = whirl(tl.clone(),j.getAndIncrement(), w);
					for(Entity e : tl.getWorld().getNearbyEntities(tl,off, 7, off)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(2.5,p);
							Holding.holding(null, le, 10l);
						}
					}
                }
            }, i*3+28); 	   
            ordt.put(gethero(p), t);                 	
        }
	}
	
	public void storm(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Mob) && d.getEntity().hasMetadata("warpedboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 7;
	        

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
		if(d.getEntity().hasMetadata("warpedboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
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
					p.setVelocity(p.getVelocity().multiply(0.5));
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