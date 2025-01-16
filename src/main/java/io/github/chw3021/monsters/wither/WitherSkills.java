package io.github.chw3021.monsters.wither;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.WitherRaids;
import io.github.chw3021.rmain.RMain;



public class WitherSkills extends WitherRaids{

	
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> shcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> aicooldown = new HashMap<UUID, Long>();
	

	
	private static final WitherSkills instance = new WitherSkills ();
	public static WitherSkills getInstance()
	{
		return instance;
	}
	HashMap<UUID, LivingEntity> bossDoll = new HashMap<UUID, LivingEntity>();	
	
	public void bowshoot(EntityShootBowEvent ev) 
	{
		if(ev.getEntity().hasMetadata("witherboss")){

			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
		    
        	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 0f);

			p.getWorld().spawnParticle(Particle.END_ROD, p.getLocation(), 10);

			String rn = gethero(p);


    		ItemStack mainf = new ItemStack(Material.BLAZE_ROD);
    		ItemMeta mmf = mainf.getItemMeta();
    		mmf.setCustomModelData(9008);
    		mainf.setItemMeta(mmf);

    		sendItemChange(p, getherotype(rn), mainf);
			
            for(int i = 0; i<6; i++) {
	            int ta = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	            		p.swingMainHand();
	            		Arrow ar = p.getWorld().spawnArrow(p.getLocation(), p.getLocation().getDirection(), 2.2f, 6.66f);
						ar.remove();
	            		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.666f, 0.666f);
	                    WitherSkull ws = (WitherSkull) p.launchProjectile(WitherSkull.class);
	                    ws.setYield(0.0f);
	                    ws.setShooter(p);
	                    ws.setVelocity(ar.getVelocity());
	                    ws.setIsIncendiary(false);
	                	ws.setCharged(true);
						ws.setMetadata("witherthrow", new FixedMetadataValue(RMain.getInstance(), true));
						ws.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
	                }
	            }, 20+i*2); 
                ordt.put(rn, ta);
            }


		 }
	}

	public void witherskullthrow(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof WitherSkull) 
		{
			WitherSkull po = (WitherSkull)d.getEntity();
			if(po.hasMetadata("witherthrow") && po.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) po.getShooter();
    			if(p.isDead()) {
    				return;
    			}
				po.getWorld().playSound(po.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.666f, 0.666f);
				p.getWorld().spawnParticle(Particle.EXPLOSION, po.getLocation(), 4, 2,2,2);
        		for(Entity e : p.getWorld().getNearbyEntities(po.getLocation(), 3.6, 3.6, 3.6)) {
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
						le.damage(6.66,p);
					}
            	}
				
			}
			
		}
	}
	public void witherskullthrow(EntityExplodeEvent ev) 
	{
	    
		
		if(ev.getEntity() instanceof WitherSkull) {
			WitherSkull fb = (WitherSkull)ev.getEntity();
			if(fb.hasMetadata("witherthrow")) {
				ev.setCancelled(true);
			}
			
		}
	}
	public void createGrowingCircle(LivingEntity p, double maxRadius, long interval, double damage) {
	    new BukkitRunnable() {
	        double currentRadius = 0.5; // 초기 반지름

	        @Override
	        public void run() {
	            if (currentRadius > maxRadius) {
	                this.cancel(); // 최대 반지름에 도달하면 태스크 중지
	                return;
	            }

	            // 현재 반지름에 대한 원의 위치 계산
	            HashSet<Location> circleLocations = calculateCircleLocations(p.getLocation(), currentRadius);

	            // 파티클 생성 및 선 밟은 엔티티 처리
	            for (Location loc : circleLocations) {
	                // 검은색 파티클 생성
	                loc.getWorld().spawnParticle(Particle.DUST, loc, 1, new Particle.DustOptions(Color.BLACK, 1));

	                // 해당 위치를 밟은 엔티티에게 데미지
	                for (Entity e : loc.getWorld().getNearbyEntities(loc, 0.5, 4, 0.5)) {
	                    if (p!=e && e instanceof LivingEntity le && !(e.hasMetadata("fake"))) {
	                    	le.damage(damage, p);
	                	    le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 66, 6));
	                    }
	                }
	            }

	            currentRadius += 0.5; // 반지름 점진적으로 증가
	        }
	    }.runTaskTimer(RMain.getInstance(), 0, interval); // 매 interval 틱마다 실행
	}
	private HashSet<Location> calculateCircleLocations(Location center, double radius) {
	    HashSet<Location> locations = new HashSet<>();
	    World world = center.getWorld();
	    if (world == null) return locations;

	    int points = (int) (radius * 15); // 원의 해상도(반지름에 비례)
	    for (int i = 0; i < points; i++) {
	        double angle = 2 * Math.PI * i / points; // 각도 계산
	        double x = center.getX() + radius * Math.cos(angle);
	        double z = center.getZ() + radius * Math.sin(angle);
	        locations.add(new Location(world, x, center.getY(), z));
	    }
	    return locations;
	}


	private HashMap<UUID, Integer> grillable = new HashMap<UUID, Integer>();
	public void grilled(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 9;
		if(d.getEntity().hasMetadata("witherboss") && grillable.containsKey(d.getEntity().getUniqueId())) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
			if(ordeal.containsKey(p.getUniqueId()) || p.hasMetadata("failed") || getPhase(p) != 1) {
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
	            	createGrowingCircle(p, 16.66, 6, 6.66);
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {
            	createGrowingCircle(p, 16.66, 6, 6.66);
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
            stand.getEquipment().setHelmet(new ItemStack(Material.WITHER_SKELETON_SKULL));
            stand.getEquipment().setItemInMainHand(new ItemStack(Material.WITHER_SKELETON_SKULL));
            stand.getEquipment().setItemInOffHand(new ItemStack(Material.WITHER_SKELETON_SKULL));
            stand.getAttribute(Attribute.SCALE).setBaseValue(3.6);
            stand.setLeftArmPose(stand.getLeftArmPose().setX(270).setY(330));
            stand.setRightArmPose(stand.getRightArmPose().setX(270).setY(30));
        });
    }
    

	
    private void summonWoodPillar(LivingEntity boss, Location startLoc) {
        World world = startLoc.getWorld();
        String rn = gethero(boss);

        List<ArmorStand> pillars = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Location pillarLoc = startLoc.clone().add(0, i * 2, 0);
            pillars.add(spawnArmorStand(world, pillarLoc, rn));
        }

        BukkitTask bt = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
            int tick = 0;

            @Override
            public void run() {
                Player target = WitherRaids.getheroes(boss).stream()
                        .filter(p -> p.isValid() && p.getWorld().equals(world))
                        .min((p1, p2) -> Double.compare(p1.getLocation().distance(startLoc), p2.getLocation().distance(startLoc)))
                        .orElse(null);

                if (target == null || tick >= 100) {
                    removeFingers(pillars,boss);
                    this.cancel();
                    return;
                }

                movePillarTowardsPlayer(pillars, target.getLocation(),tick);
                
                if(tick%10==0) {
                	shotWitherSkull(pillars, startLoc, boss);
                }

                tick = tick +2;
            }

            private void cancel() {
                Bukkit.getScheduler().cancelTask(blockt.remove(boss.getUniqueId()));
            }
        }, 0L, 2L); // 0틱 지연, 2틱 간격으로 실행

        blockt.put(boss.getUniqueId(), bt.getTaskId());
        ordt.put(rn, bt.getTaskId());
    }

    private void shotWitherSkull(List<ArmorStand> pillars, Location targetLoc, LivingEntity p) {
        for (ArmorStand block : pillars) {
        	block.getWorld().playSound(block.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.6f, 1f);
            Vector moveDirection = targetLoc.toVector().subtract(block.getEyeLocation().toVector());

            WitherSkull ws = (WitherSkull) p.launchProjectile(WitherSkull.class);
            ws.setYield(0.0f);
            ws.setShooter(p);
            ws.setVelocity(moveDirection.multiply(1.5));
            ws.setIsIncendiary(false);
        	ws.setCharged(false);
			ws.setMetadata("witherthrow", new FixedMetadataValue(RMain.getInstance(), true));
			ws.setMetadata("stuff"+gethero(p), new FixedMetadataValue(RMain.getInstance(), gethero(p)));
        }
    }
    
    private void movePillarTowardsPlayer(List<ArmorStand> pillars, Location targetLoc, int tick) {
        for (ArmorStand block : pillars) {
        	block.getWorld().spawnParticle(Particle.PORTAL, block.getLocation(), 10, 1.5, 1.5, 1.5, 0.1);
            Vector moveDirection = targetLoc.toVector().subtract(block.getLocation().toVector());
            double distance = moveDirection.length();

            if (distance > 0.4) {
                double speed = Math.log(distance + 0.4) * 0.2;
                moveDirection.normalize().multiply(speed);
                block.teleport(block.getLocation().add(moveDirection));
            }
        }
    }
    
    private void removeFingers(List<ArmorStand> pillars, LivingEntity p) {
        for (ArmorStand pillar : pillars) {
            pillar.remove();
            Location tl = pillar.getLocation().clone();
    		p.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, tl,1);
        	p.getWorld().playSound(tl, Sound.ENTITY_WITHER_BREAK_BLOCK, 0.5f, 0.2f);
        	for(Entity e : pillar.getWorld().getNearbyEntities(tl, 4,4,4)) {
        		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
        			LivingEntity le = (LivingEntity)e;
					le.damage(6.66, p);
					le.teleport(tl);
        		}
        	}
        }
    }

	
	
	private HashMap<UUID, Boolean> handable = new HashMap<UUID, Boolean>();
	
	final private void hand(LivingEntity p) {
		p.getWorld().playSound(p, Sound.ENTITY_BREEZE_CHARGE, 0.6f, 0.6f);
		p.getWorld().playSound(p, Sound.BLOCK_END_PORTAL_SPAWN, 0.3f, 1.5f);

    	final World w = p.getWorld();
		final Location pl = gettargetblock(p,6).clone().add(0,-6,0);
		w.playSound(pl, Sound.ENTITY_SHULKER_TELEPORT, 1.0f, 0f);
		w.spawnParticle(Particle.END_ROD, pl, 150, 2,2,2);
		w.spawnParticle(Particle.REVERSE_PORTAL, pl, 150, 2,2,2);
		w.spawnParticle(Particle.ENCHANT, pl, 150, 2,2,2);
		
		String rn = gethero(p);
		

		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			summonWoodPillar(p,pl);
            }
	   	}, 35);
		ordt.put(rn, task);
	   	
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
     			chargable.putIfAbsent(p.getUniqueId(), true);
            }
	   	}, 80);
	}
	

	public void hand(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("witherboss")) 
		{
			Mob p = (Mob)d.getEntity();
			
			int sec = 10;
	
	
			if(p.hasMetadata("failed")|| getPhase(p) != 1|| ordeal.containsKey(p.getUniqueId()) || !handable.containsKey(p.getUniqueId())) {
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
	

	private HashMap<UUID, LivingEntity> witherblock = new HashMap<UUID, LivingEntity>();
	
	final private void blockexplode(FallingBlock fallingb) {
		LivingEntity p = (LivingEntity) Holding.ale(witherblock.get(fallingb.getUniqueId()));
		Location tl = fallingb.getLocation();
		tl.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, tl, 1);
		tl.getWorld().spawnParticle(Particle.SQUID_INK, tl, 666,3,3,3);
		p.getWorld().playSound(tl, Sound.ENTITY_WITHER_BREAK_BLOCK, 0.36f, 0);

		for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
		{
			if(p!=e && e instanceof Player) {
				Player le = (Player)e;
        		le.damage(6, p);
        	    le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 66, 6));
			}
			
		}
		fallingb.remove();
	}
	
	public void rose(EntityDropItemEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(witherblock.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
	        	blockexplode(fallingb);
	        }
		 }
	}



	
	public void rose(EntityDamageByEntityEvent ev) 
	{
		if(ev.getDamager() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getDamager();
	        if(witherblock.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
	        	blockexplode(fallingb);
	        }
		 }
	}


	
	public void rose(EntityChangeBlockEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(witherblock.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
	        	blockexplode(fallingb);
	        }
		 }
	}


	public void summonRose(LivingEntity boss) {
	    List<Player> heroes = WitherRaids.getheroes(boss).stream()
	            .filter(hero -> hero.getWorld().equals(boss.getWorld())) // 같은 월드의 플레이어만 필터링
	            .collect(Collectors.toList());

	    if (heroes.isEmpty()) return;

	    List<Location> rLocations = new ArrayList<>();
	    Random random = new Random();
	    

	    for (int i = 0; i < 20; i++) {
	        Player randomPlayer = heroes.get(random.nextInt(heroes.size()));
	        Location baseLocation = randomPlayer.getLocation();
	        double offsetX = random.nextDouble() * 5 - 2; // 
	        double offsetZ = random.nextDouble() * 5 - 2;
	        Location randomLocation = baseLocation.clone().add(offsetX, 1.5, offsetZ);
	        rLocations.add(randomLocation);
	    }

        World world = boss.getWorld();
	    int t = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
	        private int currentIndex = 0;

	        @Override
	        public void run() {
	            if (currentIndex >= rLocations.size()) {
	                Bukkit.getScheduler().cancelTask(this.hashCode());
	                return;
	            }

	            boss.setNoActionTicks(20);
	            Location rLocation = rLocations.get(currentIndex);
	            boss.teleport(rLocation); // 보스 텔레포트
	            world.playSound(rLocation, Sound.ENTITY_WITHER_AMBIENT, 0.3f, 2f);
	            world.spawnParticle(Particle.FALLING_OBSIDIAN_TEAR, rLocation, 50, 1,1,1);

				FallingBlock fallingb = boss.getWorld().spawnFallingBlock(rLocation.clone().add(0, 8.5, 0), getBd(Material.POTTED_WITHER_ROSE));
				fallingb.setInvulnerable(true);
				fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
				fallingb.setMetadata("stuff"+gethero(boss), new FixedMetadataValue(RMain.getInstance(),gethero(boss)));
				fallingb.setDropItem(true);
				fallingb.setHurtEntities(true);
				witherblock.put(fallingb.getUniqueId(), boss);

	            // 다음 위치로 이동
	            currentIndex++;
	        }
	    }, 20L, 6L).getTaskId(); 
        ordt.put(gethero(boss), t);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	porkable.remove(boss.getUniqueId());
            }
	   	}, 60);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			grillable.putIfAbsent(boss.getUniqueId(), 1);
            }
	   	}, 80);
	}

	private HashMap<UUID, Boolean> porkable = new HashMap<UUID, Boolean>();
	
	
	public void summonRose(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("witherboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 13;

			if(ordeal.containsKey(p.getUniqueId())) {
				return;
			}

			if(p.hasMetadata("failed") || getPhase(p) != 1|| !porkable.containsKey(p.getUniqueId())) {
				return;
			}
            if (checkAndApplyCharge(p, d)) return;
					if(aicooldown.containsKey(p.getUniqueId()))
		            {
		                long timer = (aicooldown.get(p.getUniqueId())/1000 + sec) - System.currentTimeMillis()/1000; 
		                if(!(timer < 0))
		                {
		                }
		                else 
		                {
		                	aicooldown.remove(p.getUniqueId()); // removing player from HashMap
		                	summonRose(p);
		                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

		            	summonRose(p);
	                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	

	private HashMap<UUID, Boolean> chargable = new HashMap<UUID, Boolean>();
	private HashMap<UUID, Integer> illusionTask = new HashMap<UUID, Integer>();

	// 원형 파티클 위치를 미리 계산하는 메서드
	private HashSet<Location> calculateParticleLocations(Location center, double radius, int particleCount) {
	    HashSet<Location> locations = new HashSet<>();
	    World world = center.getWorld();
	    for (int i = 0; i < particleCount; i++) {
	        double angle = (2 * Math.PI / particleCount) * i;
	        double x = center.getX() + (radius * Math.cos(angle));
	        double z = center.getZ() + (radius * Math.sin(angle));
	        locations.add(new Location(world, x, center.getY() + 0.5, z));
	    }
	    return locations;
	}
	public void witherPrison(EntityDamageByEntityEvent d) {
	    if(d.getEntity().hasMetadata("witherboss")) {
	        final LivingEntity p = (LivingEntity)d.getEntity();

	        if (checkAndApplyCharge(p, d)) return;

	        if(p.hasMetadata("raid")) {
	            Optional<Player> hero = WitherRaids.getheroes(p).stream()
	                .filter(pe -> pe.getWorld().equals(p.getWorld()))
	                .findAny();

	            if(!hero.isPresent() || p.hasMetadata("failed")) {
	                return;
	            }

	            final Location tl = hero.get().getLocation().clone().add(0, 0.2, 0);
	            witherPrison(p, tl, hero);
	        }
	    }
	}

	private void prisonStart(LivingEntity p, final Location tl, Optional<Player> hero) {
	    final Location cl = tl.clone();
	    final World w = cl.getWorld();
	    
	    Player pe = hero.get();

	    p.swingMainHand();
	    pe.getWorld().playSound(pe.getLocation(), Sound.AMBIENT_NETHER_WASTES_ADDITIONS, 0.4f, 2f);
	    pe.getWorld().playSound(pe.getLocation(), Sound.ENTITY_WITHER_HURT, 0.8f, 0.6f);
	    p.getWorld().spawnParticle(Particle.ENTITY_EFFECT, cl, 500, 4, 1, 4, 0, Color.BLACK);
	    p.getWorld().spawnParticle(Particle.DUST_PILLAR, cl, 500, 4, 1, 4, 0.2, getBd(Material.BLACK_GLAZED_TERRACOTTA));

	    HashSet<Location> particleLocations = calculateParticleLocations(cl, 4, 66);
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(0, 0, 0), 1.5f); 

	    int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {
	            if(p.isDead() || pe.isDead()) {
	                return;
	            }

	    	    pe.playSound(pe.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.01f, 0.2f);
	            for (Location particleLocation : particleLocations) {
	                w.spawnParticle(Particle.DUST, particleLocation, 4,0,2,0, dustOptions);
	            }
	            if(cl.distance(pe.getLocation())>4) {
	            	pe.setVelocity(new Vector(0,0,0));
	            	getheroes(p).forEach(par ->{
		            	par.damage(6.66,p);
	            	});
	            }

	        }
	    }, 20, 1);

	    illusionTask.put(p.getUniqueId(), task);
		ordt.put(gethero(p), task);
        chargable.remove(p.getUniqueId());

	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {
	            if(illusionTask.containsKey(p.getUniqueId())) {
	                Bukkit.getScheduler().cancelTask(illusionTask.get(p.getUniqueId()));
	                illusionTask.remove(p.getUniqueId());
	            }
	        }
	    }, 100);

	    // 일정 시간 후 복원 가능 상태로 변경
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {
	            porkable.put(p.getUniqueId(), true);
	        }
	    }, 180);
	}


	private void witherPrison(LivingEntity p, Location tl, Optional<Player> hero) {
	    if(ordeal.containsKey(p.getUniqueId())) {
	        return;
	    }
	    if(p.hasMetadata("failed") || getPhase(p) != 1|| !chargable.containsKey(p.getUniqueId())) {
	        return;
	    }
	    if(rb8cooldown.containsKey(p.getUniqueId())) {
	        long timer = (rb8cooldown.get(p.getUniqueId()) / 1000 + 12) - System.currentTimeMillis() / 1000;
	        if (timer < 0) {
	            rb8cooldown.remove(p.getUniqueId()); 
	            prisonStart(p, tl, hero);
	            rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
	    } else {
            prisonStart(p, tl, hero);
	        rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	    }
	}
	


	
	final private void pillar(final Location ptl, LivingEntity p, Integer dur) {

		ptl.getWorld().playSound(ptl, Sound.ENTITY_WITHER_SKELETON_DEATH, 0.8f, 0f);
		ptl.getWorld().playSound(ptl, Sound.BLOCK_LAVA_AMBIENT, 0.2f, 0f);
    	ptl.getWorld().spawnParticle(Particle.BLOCK_CRUMBLE, ptl, 666,2.5,0.2,2.5, getBd(Material.CHISELED_POLISHED_BLACKSTONE));
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {

	    		for (Entity e : p.getWorld().getNearbyEntities(ptl, 2.5, 5, 2.5))
	    		{
	    			if(p!=e && e instanceof Player) {
	    				Player le = (Player)e;
	            		le.damage(6.66, p);
	            	    le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 66, 6));
	            	    le.setVelocity(new Vector(0,2,0));
	    			}
	    			
	    		}
	    		ptl.getWorld().playSound(ptl, Sound.ENTITY_WITHER_HURT, 0.9f, 0f);
	        	ptl.getWorld().spawnParticle(Particle.DUST_PILLAR, ptl, 555,2.5,3,2.5, getBd(Material.CHISELED_POLISHED_BLACKSTONE));
	        }
	    }, dur);
	}
	public void pillar(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Mob) && d.getEntity().hasMetadata("witherboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 4;
	        

			if(ordeal.containsKey(p.getUniqueId())) {
				return;
			}
            if (checkAndApplyCharge(p, d)) return;
			if(p.getTarget() == null|| !(p.getTarget() instanceof Player)||p.hasMetadata("failed")) {
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
		                	

			                pillar(ptl, p,25);
		                    
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


		                pillar(ptl, p,25);
	                    
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

	public boolean checkAndApplyCharge(LivingEntity p, EntityDamageByEntityEvent d) {
		if(!p.hasMetadata("witherboss")) {
			return true;
		}
	    if ((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue() * 0.5)
	            && !p.hasMetadata("ruined")&& !p.hasMetadata("ejected")) {
	        p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue() * 0.5);
	        d.setCancelled(true);
	        p.setMetadata("ejected", new FixedMetadataValue(RMain.getInstance(), true));
	        throne.remove(gethero(p));
	        p.getWorld().getEntities().stream().filter(ent -> ent.hasMetadata("stuff"+gethero(p))).forEach(block ->{
	        	Location tl = block.getLocation().clone();
	    		tl.getWorld().spawnParticle(Particle.GUST_EMITTER_LARGE, tl, 20,2,2,2);
	    		p.getWorld().playSound(tl, Sound.ENTITY_GENERIC_EXPLODE, 0.06f, 0);
	    		p.getWorld().playSound(tl, Sound.BLOCK_DEEPSLATE_TILES_BREAK, 0.2f, 0);
	        });;
	        return true;
	    }
	    return false;
	}
		
	
	
}