package io.github.chw3021.monsters.wither;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.WitherRaids;
import io.github.chw3021.rmain.RMain;



public class WitherSkills3 extends WitherRaids{

	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> shcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> aicooldown = new HashMap<UUID, Long>();
	

	
	private static final WitherSkills3 instance = new WitherSkills3 ();
	public static WitherSkills3 getInstance()
	{
		return instance;
	}
	
	public void bowshoot(ProjectileLaunchEvent ev) 
	{
		if(ev.getEntity().getShooter() instanceof LivingEntity){
			
		    LivingEntity p = (LivingEntity) ev.getEntity().getShooter();
		    
		    if(p.hasMetadata("witherboss")) {
			    if(getPhase(p) !=3) {
			    	return;
			    }
		        String rn = gethero(p);
		        Projectile sn = ev.getEntity();
				sn.setMetadata("witherthrow", new FixedMetadataValue(RMain.getInstance(), true));
				sn.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), true));
				sn.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
				sn.setMetadata("witherseed", new FixedMetadataValue(RMain.getInstance(), true));
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
				if(po.hasMetadata("witherseed")) {
            		final Location l = d.getHitEntity() != null ? d.getHitEntity().getLocation() : d.getHitBlock().getLocation();
				
					tracking(p,l);
				}
			}
			
		}
	}
	
	
	private void tracking(LivingEntity shooter, Location hit) {
	    World world = shooter.getWorld();

		String rn = gethero(shooter);

        Location hitLocation = hit.clone().add(0, 0.16, 0); // 시작 지점
        
        for(int i = 0; i<6; i++) {
        	Arrow ar = world.spawnArrow(hitLocation, new Vector(0,1.2,0), 1.6f, 16);
        	world.spawn(hitLocation, WitherSkull.class, ws -> {
        		ws.setVelocity(ar.getVelocity().add(new Vector(0,-0.66,0)));
        		ws.setShooter(shooter);
                ws.setYield(0.0f);
                ws.setIsIncendiary(false);
            	ws.setCharged(true);
				ws.setMetadata("witherthrow", new FixedMetadataValue(RMain.getInstance(), true));
				ws.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
        	});
        	ar.remove();
        }
	}

	
	private Location findGroundBlock(Location startLocation) {
	    World world = startLocation.getWorld();
	    if (world == null) return null;
	
	    Location current = startLocation.clone();
	
	    // Y축 아래로 탐색
	    while (current.getY() > world.getMinHeight()) {
	        current.subtract(0, 1, 0); // 아래로 1블록 이동
	        if (!current.getBlock().isPassable()) { // Solid 블록인지 확인
	            return current; // Solid 블록이면 반환
	        }
	    }
	    return null; // 바닥을 찾지 못한 경우
	}
	
	public void createGrowingCircle(LivingEntity p, double maxRadius, long interval, double damage) {
		String rn = gethero(p);
		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BIG_DRIPLEAF_TILT_DOWN, 1.0f, 0f);
		p.getWorld().spawnParticle(Particle.WITCH, p.getLocation(), 150, 2,2,2);
		
		World world = p.getWorld();
		final Location startLocation = p.getLocation().clone();
		Location groundLocation = findGroundBlock(startLocation);
		if (groundLocation == null) {
		    return; // 바닥 블록을 찾지 못하면 중지
		}

		double totalDistance = startLocation.getY() - groundLocation.getY(); // 총 Y축 이동 거리
		int totalTicks = 20; // 1초(20틱)
		double distancePerTick = totalDistance / totalTicks; // 매 틱 이동해야 할 거리

		ordt.put(rn, new BukkitRunnable() {
		    int tickCount = 0; // 현재 틱 수
		    double yOffset = 0; // 현재까지 이동한 거리

		    @Override
		    public void run() {
		        if (tickCount >= totalTicks) {
		            this.cancel(); // 1초(20틱) 경과 후 태스크 종료
		            p.teleport(groundLocation.add(0.5, 0, 0.5)); // 목표 지점 정렬
		            world.playSound(groundLocation, Sound.BLOCK_SCULK_SPREAD, 1.0f, 0f);
		            world.spawnParticle(Particle.WITCH, groundLocation, 150, 2, 2, 2);
		            return;
		        }

		        yOffset += distancePerTick; // 매 틱 이동 거리 추가
		        Location newLocation = startLocation.clone().subtract(0, yOffset, 0); // 새로운 위치 계산
		        p.teleport(newLocation); // 위더 위치 업데이트
		        world.spawnParticle(Particle.SMOKE, newLocation, 10, 0.3, 0.3, 0.3, 0.01);
		        world.playSound(newLocation, Sound.ENTITY_WITHER_HURT, 0.5f, 0.8f);

		        tickCount++; // 틱 수 증가
		    }
		}.runTaskTimer(RMain.getInstance(), 0L, 1L).getTaskId()); // 0틱 후 시작, 1틱 간격으로 실행

		ordt.put(rn, new BukkitRunnable() {
	        double currentRadius = 0.5; // 초기 반지름

	        @Override
	        public void run() {
	            if (currentRadius > maxRadius) {
	                this.cancel(); // 최대 반지름에 도달하면 태스크 중지
	                return;
	            }

	            // 현재 반지름에 대한 원의 위치 계산
	            HashSet<Location> circleLocations = calculateCircleLocations(p.getLocation().add(0,-1.1,0), currentRadius);

	            // 파티클 생성 및 선 밟은 엔티티 처리
	            for (Location loc : circleLocations) {
	                // 검은색 파티클 생성
	                loc.getWorld().spawnParticle(Particle.FALLING_DUST, loc, 150,0,1.5,0,getBd(Material.BLACKSTONE));

	                // 해당 위치를 밟은 엔티티에게 데미지
	                for (Entity e : loc.getWorld().getNearbyEntities(loc, 1, 1.5, 1)) {
	                    if (p!=e && e instanceof LivingEntity le && !(e.hasMetadata("fake"))) {
	                    	le.damage(damage, p);
	                	    le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 66, 1));
	                    }
	                }
	            }

	            currentRadius += 1; // 반지름 점진적으로 증가
	        }
	    }.runTaskTimer(RMain.getInstance(), 20, interval).getTaskId()); // 매 interval 틱마다 실행
	}
	
	private HashSet<Location> calculateCircleLocations(Location center, double radius) {
	    HashSet<Location> locations = new HashSet<>();
	    World world = center.getWorld();
	    if (world == null) return locations;

	    int points = 12; // 12개의 각도로 분할
	    double offsetAngle = Math.random() * 2 * Math.PI; // 0~2π 사이의 랜덤 오프셋 각도 생성

	    for (int i = 0; i < points; i++) {
	        double angle = offsetAngle + 2 * Math.PI * i / points; // 랜덤 오프셋 각도 추가
	        double x = center.getX() + radius * Math.cos(angle);
	        double z = center.getZ() + radius * Math.sin(angle);
	        locations.add(new Location(world, x, center.getY(), z));
	    }
	    return locations;
	}

	private HashMap<UUID, Integer> cursable = new HashMap<UUID, Integer>();
	
	final private void cursed(LivingEntity p) {

    	cursable.remove(p.getUniqueId());

		String rn = gethero(p);
	    ordt.put(rn, new BukkitRunnable() {
	        int count = 0; // 초기 반지름

	        @Override
	        public void run() {
	            if (count++ > 2) {
	                this.cancel();
	                return;
	            }
	            createGrowingCircle(p, 16, 16, 16);
	        }
	    }.runTaskTimer(RMain.getInstance(), 0, 22).getTaskId()); 
		
        
	}
	
	
	public void cursed(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 13;
		if(d.getEntity().hasMetadata("witherboss") && cursable.containsKey(d.getEntity().getUniqueId())) 
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
    	world.playSound(location, Sound.ENTITY_WITHER_AMBIENT, 0.6f, 1.6f);
        return world.spawn(location, ArmorStand.class, stand -> {
            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setInvisible(true);
            stand.setBasePlate(false);
            stand.setCollidable(false);
            stand.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
            stand.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
            stand.getEquipment().setHelmet(new ItemStack(Material.WITHER_SKELETON_SKULL));
            stand.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
            stand.getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
            stand.getEquipment().setItemInMainHand(new ItemStack(Material.NETHERITE_SWORD));
        });
    }
    
    private void summonWoodPillar(LivingEntity boss, Location startLoc) {
        World world = startLoc.getWorld();
        String rn = gethero(boss);

        WitherRaids.getheroes(boss).stream()
            .filter(p -> p.isValid() && p.getWorld().equals(world)).toList().forEach(target ->{
            	
            	ArmorStand shadow = spawnArmorStand(world, target.getLocation().clone(), rn);
        	
            	
            	BukkitTask bt = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
                int tick = 0;

                @Override
                public void run() {

                    if (target == null || tick >= 6) { 
                        removeFingers(shadow, boss);
                        this.cancel();
                        return;
                    }

                    movePillarTowardsPlayer(shadow, target.getLocation(), rn, boss);


                    tick++;
                }

                private void cancel() {
                    Bukkit.getScheduler().cancelTask(this.hashCode());
                }
            }, 0L, 26L); 
            blockt.put(boss.getUniqueId(), bt.getTaskId());
            ordt.put(rn, bt.getTaskId());
        });
    }

    private void movePillarTowardsPlayer(ArmorStand block, Location targetLoc, String rn, LivingEntity p) {
    	targetLoc.getWorld().spawnParticle(Particle.WITCH, targetLoc, 30, 1.5, 1.5, 1.5, 0.1);
    	targetLoc.getWorld().playSound(targetLoc, Sound.ENTITY_BREEZE_IDLE_GROUND, 0.4f, 1.6f);

        ordt.put(rn,new BukkitRunnable() {

            @Override
            public void run() {

            	block.teleport(targetLoc);
            	Location bl = block.getLocation();
            	bl.getWorld().spawnParticle(Particle.SWEEP_ATTACK, bl, 6, 1.7, 1.7, 1.7, 0.1);
            	bl.getWorld().spawnParticle(Particle.PORTAL, bl, 6, 1.7, 1.7, 1.7, 0.1);
            	bl.getWorld().playSound(bl, Sound.ENTITY_BREEZE_SHOOT, 0.5f, 1.6f);
            	block.swingMainHand();
            	block.swingOffHand();
            	block.playEffect(EntityEffect.ARMOR_STAND_HIT);
            	
            	for(Entity e : bl.getWorld().getNearbyEntities(bl, 1.6,1.6,1.6)) {
            		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
            			LivingEntity le = (LivingEntity)e;
    					le.damage(6.66, p);
            		}
            	}
            }
        }.runTaskLater(RMain.getInstance(), 16).getTaskId());
        
    }

    private void removeFingers(ArmorStand shadow, LivingEntity p) {
    	if(shadow.isDead() || shadow.isEmpty() || !shadow.isValid()) {
    		return;
    	}
    	Location bl = shadow.getLocation();
    	bl.getWorld().spawnParticle(Particle.SQUID_INK, bl, 60);
    	shadow.setRiptiding(true);
    	bl.getWorld().playSound(bl, Sound.ENTITY_TNT_PRIMED, 0.4f, 0.6f);
		ordt.put(gethero(p), Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     	    	bl.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, bl, 1, 3.6, 3.6, 3.6);
     	    	bl.getWorld().spawnParticle(Particle.WITCH, bl, 600, 3.6, 3.6, 3.6, 0.1);
     	    	bl.getWorld().playSound(bl, Sound.ENTITY_WITHER_BREAK_BLOCK, 0.4f, 0.6f);
     	    	
     	    	for(Entity e : bl.getWorld().getNearbyEntities(bl, 3.6,3.6,3.6)) {
     	    		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
     	    			LivingEntity le = (LivingEntity)e;
     					le.damage(16.66, p);
     	    		}
     	    	}
            }
	   	}, 20));
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     	    	shadow.remove();
            }
	   	}, 20);
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
		if(d.getEntity().hasMetadata("witherboss")) 
		{
			Mob p = (Mob)d.getEntity();
			p.getWorld().playSound(p, Sound.ENTITY_BREEZE_CHARGE, 0.6f, 0.6f);
			p.getWorld().playSound(p, Sound.PARTICLE_SOUL_ESCAPE, 0.6f, 1.5f);
			
			int sec = 15;
	
	
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
	

    private Map<String, List<BlockDisplay>> prisonDisplays = new HashMap<>();

	final private void prison(LivingEntity p, Player pe, String rn) {
		if(!pe.getWorld().equals(p.getWorld()) || pe.isDead()) {
			return;
		}
	    Location center = pe.getLocation().clone().add(0,0.1,0); // 감옥 중심 좌표
	    World world = pe.getWorld();

	    Holding.holding(null, pe, 10l);
	    
	    // 감옥 생성에 사용될 블록
	    Material blackBlockMaterial = Material.BLACK_STAINED_GLASS;
	    Material grayBlockMaterial = Material.WHITE_CONCRETE;

	    // 감옥 블록의 위치를 저장
	    List<Location> prisonLocs = new ArrayList<Location>();
	    List<BlockDisplay> displays = new ArrayList<>();
	    // 감옥 블록 총 개수 계산 (바닥 제외)
	    int totalBlocks = 0;
	    for (int x = -2; x <= 2; x=x+4) {
	        for (int y = 1; y <= 3; y++) { // y == 0 (바닥)는 제외
	            for (int z = -2; z <= 2; z=z+4) {
	                totalBlocks++;
	                prisonLocs.add(center.clone().add(x, y, z));
	            }
	        }
	    }

	    // 랜덤으로 회색 블록의 위치 선택
	    Random random = new Random();
	    int randomIndex = random.nextInt(totalBlocks); // 총 블록 개수를 인자값으로 사용

	    for(int i = 0; i< totalBlocks; i++) {
	    	Location blockLoc = prisonLocs.get(i);
            BlockDisplay display = (BlockDisplay) world.spawn(blockLoc, BlockDisplay.class);

            // 진한 회색 블록과 검은 블록 설정
            if (i == randomIndex) {
            	display.setGlowing(true);
                display.setBlock(Bukkit.createBlockData(grayBlockMaterial));
            } else {
                display.setBlock(Bukkit.createBlockData(blackBlockMaterial));
            }

            // 블록 고정
            display.setGravity(false);
            display.setPersistent(false);
            display.setBillboard(Billboard.FIXED);
            display.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
            display.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
            displays.add(display);
            prisonDisplays.put(rn, displays);
	    	
	    }

	    // 2초 내에 회색 블록을 파괴하지 못할 시 피해
	    ordt.put(rn ,Bukkit.getScheduler().runTaskLater(RMain.getInstance(), () -> {
	        if (!displays.isEmpty()) { // 감옥이 아직 존재하면
	            pe.damage(66.0,p); // 대량 피해
	        }
	        // 감옥 블록 제거
	        for (BlockDisplay display : displays) {
	            display.remove();
                prisonDisplays.remove(rn);
	        }
	    }, 60).getTaskId()); 

	    // 감옥 외부로 나갈 시 강제 텔레포트 및 피해
	    ordt.put(rn ,new BukkitRunnable() {
	        @Override
	        public void run() {
		        if (displays.isEmpty() || !prisonDisplays.containsKey(rn)) {
		        	this.cancel();
		        	return;
		        }
	            if (pe.getLocation().distance(center) > 2.2) { // 감옥 범위 밖으로 나갔을 경우
	                pe.damage(66.0,p); // 대량 피해
	                pe.teleport(center); // 다시 감옥으로 텔레포트
	            }
	        }
	    }.runTaskTimer(RMain.getInstance(), 0, 5).getTaskId());
	}
	
	public void escapePrison(PlayerInteractEvent event) {
        if (event.getAction().name().contains("CLICK")) {
            Player player = event.getPlayer();
            if(!heroes.containsValue(player.getUniqueId())) {
            	return;
            }
            if(player.rayTraceBlocks(2) == null || player.rayTraceBlocks(2).getHitEntity() == null) {
            	return;
            }
            Entity targetBlock = player.rayTraceBlocks(2).getHitEntity();
            World w = player.getWorld();
            if (targetBlock instanceof BlockDisplay display) {
            	String rn = getheroname(player);
            	if(prisonDisplays.containsKey(rn)) {
                    List<BlockDisplay> displays = prisonDisplays.get(rn);
                    if (displays.contains(display)) {
                        // 감옥의 회색 블록인지 확인
                        if (display.getBlock().getMaterial() == Material.WHITE_CONCRETE) {
                            // 감옥 탈출 처리
                            for (BlockDisplay d : displays) {
                                d.remove();
                            }
                            displays.clear();
                            prisonDisplays.remove(rn);
                    		w.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0f, 2f);
                    		w.spawnParticle(Particle.FLASH, player.getLocation(), 5, 1,0.2,1);
                        }
                        event.setCancelled(true);
                        return;
                    }
            	}
            }
            if (targetBlock instanceof ArmorStand stand) {

	            if (stand.hasMetadata("lightsphere")) {
	                UUID uid = UUID.fromString(stand.getMetadata("lightsphere").get(0).asString());
	                Entity entity = Bukkit.getEntity(uid);
	                if(entity == null) {
	                	return;
	                }
	                moveLightSphere(stand, player, (LivingEntity) entity);
	                stand.addPassenger(player);
	            }
            }
        }
    }
	
	final private void prison(LivingEntity p) {

    	final World w = p.getWorld();
        for(Player pe : WitherRaids.getheroes(p)) {
    		Location pfl = pe.getEyeLocation().clone();
    		w.playSound(pfl, Sound.BLOCK_VAULT_ACTIVATE, 1.0f, 0f);
    		w.playSound(pfl, Sound.BLOCK_VAULT_OPEN_SHUTTER, 1.0f, 0f);
    		w.spawnParticle(Particle.SCULK_SOUL, pfl, 350, 2,2,2);
        }
		
		String rn = gethero(p);
		

        for(Player pe : WitherRaids.getheroes(p)) {
        	prison(p,pe,rn);
        }
        
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			cursable.putIfAbsent(p.getUniqueId(), 1);
            }
	   	}, 180);
	}
	
	public void prison(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("witherboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 18;
            if (checkAndApplyCharge(p, d)) return;
			

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
		                	prison(p);
		                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

		            	prison(p);
	                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	

	private HashMap<UUID, Boolean> waveable = new HashMap<UUID, Boolean>();
	public void wave(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("witherboss")) 
		{
			final LivingEntity p = (LivingEntity)d.getEntity();
            if (checkAndApplyCharge(p, d)) return;

			if(p.hasMetadata("raid")) {
				if(!WitherRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}
				wave(p);
			}
		}
	}
	
	private HashMap<UUID, Integer> whirlTaskMap = new HashMap<>();
	
	private void tentacleGrab(LivingEntity p, World world, Location tl) {

        if (whirlTaskMap.containsKey(p.getUniqueId())) {
            Bukkit.getScheduler().cancelTask(whirlTaskMap.remove(p.getUniqueId()));
        }
    	p.getWorld().spawnParticle(Particle.DUST_PILLAR, tl, 666, 3, 3, 3, 0.2, getBd(Material.POLISHED_BLACKSTONE));
		p.getWorld().playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1f, 2f);

        // 솟아오르는 파티클 효과
        
        String rn = gethero(p);

		int t= Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
                world.playSound(tl, Sound.ENTITY_EVOKER_FANGS_ATTACK, 1.0f, 0.2f);

                ArrayList<Location> meats = new ArrayList<>();
                for(int i=0; i<10; i++) {
                    Random random=new Random();
                	double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
                	double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
                	meats.add(tl.clone().add(number, 1, number2));
                }
                meats.forEach(l ->{
                	EvokerFangs ef = (EvokerFangs)p.getWorld().spawn(l, EvokerFangs.class);
                    ef.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                    ef.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
                	ef.setOwner(p);
                	ef.setAttackDelay(1);
                	ef.setInvulnerable(true);
                });
                // 범위 내 적들에게 피해
                for (Entity entity : world.getNearbyEntities(tl, 3, 3, 3)) {
                    if (entity instanceof LivingEntity && entity != p&& !entity.hasMetadata("fake")&& !entity.hasMetadata("portal")) {
                        LivingEntity target = (LivingEntity) entity;

                        // 피해 및 디버프 적용
                        target.damage(8.0, p);
                        target.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 200,3,false,false,false));
                        target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200,6,false,false,false));
                        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 200,3,false,false,false));
                        target.setVelocity(new Vector(0, -10, 0));
                    }
                }

                
            }
		}, 20);
    	ordt.put(rn, t);
    	
        return;
	}
	
	final private void witherTentacle(LivingEntity p) {
	    final World world = p.getWorld();
	    final Location startLocation = p.getLocation().clone();
	    WitherRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).forEach(pe ->{
	    	final Location tl = pe.getLocation();
	    	
		    world.playSound(tl, Sound.ENTITY_WITHER_HURT, 1.0f, 0f);
		    world.playSound(tl, Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1.0f, 0f);
		    world.spawnParticle(Particle.CLOUD, tl, 200, 2, 2, 2, 0);
		    world.spawnParticle(Particle.PORTAL, tl, 200, 1, 1, 1, 0);
		    
		    final Vector direction = tl.clone().toVector().subtract(startLocation.toVector()).normalize();



		    // 회오리 생성 및 확장
		    int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		    	int tick = 0;
		        @Override
		        public void run() {
				    pe.playSound(pe, Sound.ENTITY_WITHER_SKELETON_STEP, 0.06f, 0f);

	                Location particleLocation = startLocation.clone()
	                        .add(direction.clone().multiply(tick++ * 0.36));

                    if (p.isDead() || particleLocation.distance(tl) <1|| tick>=30) { // 촉수가 도달하거나 시간이 종료되면
                        tentacleGrab(p, world, startLocation);
                    }
	                world.spawnParticle(Particle.SQUID_INK, particleLocation, 30, 0.2, 0.5 ,0.2,0);
	                pe.setVelocity(new Vector(0, -2, 0));


		        }
		    }, 20L, 3L);

		    whirlTaskMap.put(p.getUniqueId(), taskId);
		    ordt.put(gethero(p), taskId);
	    });

	}

	final private void wave(LivingEntity p) {
	    if (p.hasMetadata("failed") || ordeal.containsKey(p.getUniqueId()) || !waveable.containsKey(p.getUniqueId())) {
	        return;
	    }

	    if (rb8cooldown.containsKey(p.getUniqueId())) {
	        long timer = (rb8cooldown.get(p.getUniqueId()) / 1000 + 13) - System.currentTimeMillis() / 1000;
	        if (timer < 0) {
	            rb8cooldown.remove(p.getUniqueId());
	            witherTentacle(p);
	            rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
	    } else {
	        witherTentacle(p);
	        rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	    }
	}

	
	final private void jumpAndHit(final Location tl, LivingEntity p) {
		
		Holding.holding(null, p, 22l);
	    Location fl = p.getLocation().clone(); // 시작 위치
	    Location jl = tl.clone().add(0, 4, 0); // 목표 도약 위치
	    World world = p.getWorld();
        world.spawnParticle(Particle.LARGE_SMOKE, jl, 100, 2, 0.2, 2, 0);
        world.spawnParticle(Particle.LARGE_SMOKE, fl, 100, 2, 0.2, 2, 0);
	    world.playSound(fl, Sound.ENTITY_ENDER_DRAGON_FLAP, 1f, 0f);
	    world.playSound(jl, Sound.ITEM_CHORUS_FRUIT_TELEPORT, 1f, 0f);
	    world.playSound(jl, Sound.ENTITY_ENDER_DRAGON_FLAP, 1f, 0f);
	    

	    AtomicInteger j = new AtomicInteger();
	    j.set(Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {
	        	performAttack(tl, p);
	        }
	    }, 22L)); 

	    // 태스크 저장 (필요 시 추가 관리)
	    ordt.put(gethero(p), j.get());
	}

	private void performAttack(Location tl, LivingEntity p) {
	    World world = p.getWorld();

	    p.teleport(tl);
	    // 내려찍기 공격 효과
	    world.playSound(tl, Sound.ENTITY_WITHER_BREAK_BLOCK, 1.0f, 0f);
	    world.spawnParticle(Particle.EXPLOSION, tl, 10, 3, 1, 3, 0);
	    world.spawnParticle(Particle.SONIC_BOOM, tl, 10, 3, 1, 3, 0);

	    // 피해 판정
	    for (Entity entity : world.getNearbyEntities(tl, 3, 3, 3)) {
	        if (entity instanceof LivingEntity && entity != p && !entity.hasMetadata("fake") && !entity.hasMetadata("portal")) {
	            LivingEntity target = (LivingEntity) entity;
	            target.damage(6.66, p);
	            target.setVelocity(BlockFace.DOWN.getDirection().multiply(5));
	        }
	    }
	}
	
	final private void storm(LivingEntity p) {


	    Player pe = WitherRaids.getheroes(p).stream().filter(pf -> pf.getWorld().equals(p.getWorld()) && !pf.isDead()).findAny().orElse(null);
	    if(pe == null) {
	    	return;
	    }
    	pe.setVelocity(new Vector(0,-25,0));
    	Location tl = pe.getLocation().clone();
		World w = tl.getWorld();
		w.playSound(pe, Sound.ENTITY_WITHER_SPAWN, 0.8f, 2f);
		w.spawnParticle(Particle.OMINOUS_SPAWNING, tl, 150, 2,0.5,2,0.1);
		
		jumpAndHit(tl, pe);
	}
	
	public void storm(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Mob) && d.getEntity().hasMetadata("witherboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 10;
	        

            if (checkAndApplyCharge(p, d)) return;
			if(ordeal.containsKey(p.getUniqueId())||p.hasMetadata("failed")) {
				return;
			}
			if(!WitherRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
				return;
			}
			
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

	                storm(p);
                    
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                 		@Override
                    	public void run() 
                        {	
                 			handable.put(p.getUniqueId(), true);
        	            }
                    }, 65); 
                    
         			
					rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
                }
            }
            else 
            {


                Holding.holding(null, p, 10l);

                storm(p);
                
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
                    {	
             			handable.put(p.getUniqueId(), true);
    	            }
                }, 65); 
                
				rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
			}
		}
	}



	final private void bossfailed(LivingEntity p, String rn) {
		if(ordt.containsKey(rn)) {
			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
		}
		ordeal.remove(p.getUniqueId());
    	successCount.remove(rn);
    	darkSphereMap.remove(rn);
    	darkSphereSize.remove(rn);
    	
    	p.playHurtAnimation(0);
		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 2);
		p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 10, 2,2,2);
    	Holding.reset(Holding.ale(p));
    	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
		Bukkit.getWorld("WitherRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
        for(Player pe : WitherRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"[더 위더]: ");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"[The Wither]: ");
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
		if(d.getEntity().hasMetadata("witherboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
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
	                executeLightAndDarknessPattern(p,d);
		            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	            }
	        }
	        else 
	        {
	        	executeLightAndDarknessPattern(p,d);
	            rb6cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
		}
	}
	
	private HashMap<String, Integer> successCount = new HashMap<String, Integer>();

	final long ORDEALTIME = 1200;
	private void executeLightAndDarknessPattern(LivingEntity p, EntityDamageByEntityEvent d) {
		

    	ordeal.put(p.getUniqueId(), true);
		String rn = gethero(p);
        final Location rl = getraidloc(p).clone();

		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }

		
        d.setCancelled(true);
        p.teleport(rl.clone().add(0, 2, 0));
        Holding.holding(null, p, ORDEALTIME);
        Holding.invur(p, 100l);
        Holding.untouchable(p, ORDEALTIME);
        
        
	    spawnDarkSphere(p, rn, rl.clone().add(0, -2, 0));
	    
	    successCount.put(rn, 0);

	    getheroes(p).forEach(pe ->{
        	Holding.invur(pe, 60l);
        	AtomicReference<String> msg = new AtomicReference<String>(ChatColor.BOLD+"");
        	if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		msg.set(ChatColor.BOLD+"");
			}
    		pe.sendTitle(ChatColor.DARK_PURPLE + (ChatColor.MAGIC + "123456"), ChatColor.DARK_PURPLE + (ChatColor.MAGIC + "123456"), 20, 35, 20);
				Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {	
            		pe.sendMessage(msg.get());
                }
            }, 20);
        });
        
		p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation().clone(), 200,2,2,2,0.1);
		p.getWorld().spawnParticle(Particle.DRAGON_BREATH, p.getLocation().clone(), 200,2,2,2,0.1);
        int i1 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
    	        Location randomLocation = getRandomLocation(rl.clone(), 6, 10);
    	        spawnLightSphere(p, rn, rl.getWorld(), randomLocation);
            }
        }, 60, 100);
		ordt.put(rn, i1); 

			int i2 = Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {	
				killAll(p, rn);
            }
        }, ORDEALTIME+1); 
		ordt.put(rn, i2); 
	}

	private void killAll(LivingEntity p, String rn) {

		

        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }
    	ordeal.remove(p.getUniqueId());
    	successCount.remove(rn);
    	darkSphereMap.remove(rn);
    	darkSphereSize.remove(rn);
    	rb6cooldown.remove(p.getUniqueId()); 
		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
        for(Player pe : getheroes(p)) {
    		if(pe.getWorld().equals(p.getWorld()) && pe.getWorld() == p.getWorld()) {
				if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
					pe.sendMessage(ChatColor.BOLD + "[더 위더]: " + ChatColor.RED + "죽어라..");
				}
				else {
					pe.sendMessage(ChatColor.BOLD + "[The Wither]: " + ChatColor.RED + "Die...");
				}
    			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1, 0);
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
        		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
        		p.removeMetadata("fake", RMain.getInstance());
        		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
                p.setInvulnerable(false);
                Holding.ale(p).setInvulnerable(false);
				p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation(), 3000, 10,10,10);	
                for(Player pe : getheroes(p)) {
            		p.removeMetadata("fake", RMain.getInstance());
            		pe.setHealth(0);
                }
            }
        }, 5); 
	}
	private HashMap<String, ArmorStand> darkSphereMap = new HashMap<String, ArmorStand>();
	// 암흑의 구체 생성 메서드
	private ArmorStand spawnDarkSphere(LivingEntity p, String rn, Location center) {
		ArmorStand darkSphere = center.getWorld().spawn(center, ArmorStand.class, stand -> {
	        stand.setGravity(false);
	        stand.setInvulnerable(true);
            stand.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
            stand.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
            stand.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
            stand.setInvisible(true);
            stand.getEquipment().setHelmet(new ItemStack(Material.CRYING_OBSIDIAN));
            darkSphereMap.put(rn, stand);
	    });

	    // 암흑의 구체 크기 증가
	    BukkitRunnable darkSphereTask = new BukkitRunnable() {
	    	double currentRadius = 2.0d;
	    	final double increase = 0.1d;

	        @Override
	        public void run() {
	            darkSphereSizeUp(rn, darkSphere, currentRadius, increase);
	            darkSphere.getWorld().spawnParticle(Particle.SMOKE, darkSphere.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);
	            Double radius = darkSphereSize.get(rn)*0.5;
        	    for (Entity entity : center.getWorld().getNearbyEntities(center, radius, radius, radius)) {
        	        if (entity instanceof LivingEntity && entity != p && !entity.hasMetadata("fake") && !entity.hasMetadata("portal")) {
        	            LivingEntity target = (LivingEntity) entity;
        	            target.damage(6.66, p);
        	        }
        	    }
	            currentRadius = radius;
	        }
	    };
	    darkSphereTask.runTaskTimer(RMain.getInstance(), 0, 2); // 매 2틱마다 크기 증가
	    ordt.put(rn, darkSphereTask.getTaskId());

	    return darkSphere;
	}
	private HashMap<String, Double> darkSphereSize = new HashMap<String, Double>();
	
	private double darkSphereSizeUp(String rn, final ArmorStand display, double radius, double inc) {
        display.getAttribute(Attribute.SCALE).setBaseValue(radius+inc);
        darkSphereSize.put(rn, radius+inc);
        return radius+inc;
	}

	
	
	
	private void spawnLightSphere(LivingEntity p, String rn, World world, Location spawnLocation) {
	    // 빛의 구체 생성 (ArmorStand)
	    ArmorStand lightSphere = world.spawn(spawnLocation, ArmorStand.class, stand -> {
	        stand.setInvisible(true);
	        stand.setGravity(false);
	        stand.setInvulnerable(true);
            stand.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
            stand.setMetadata("lightsphere", new FixedMetadataValue(RMain.getInstance(), p.getUniqueId()));
            stand.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
            stand.getEquipment().setHelmet(new ItemStack(Material.PEARLESCENT_FROGLIGHT));
            stand.setCustomName("10");
            stand.setCustomNameVisible(true);
	    });
	    world.playSound(spawnLocation, Sound.BLOCK_FROGLIGHT_PLACE, 1.0f, 0f);
	    world.spawnParticle(Particle.GLOW, spawnLocation, 10, 3, 1, 3, 0);

	    // 빛의 구체 주변 방해 몬스터 소환
	    for (int i = 0; i < 3; i++) {
	        world.spawn(spawnLocation, WitherSkeleton.class, mob -> {
	            mob.setTarget(WitherRaids.getheroes(p).stream().filter(pe -> pe.isValid()).findAny().get());
	            mob.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
	            mob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
	            mob.setInvulnerable(true);
	        });
	    }
	    BukkitRunnable task = new BukkitRunnable() {

	    	int count = 0;
	        @Override
	        public void run() {
	        	lightSphere.setCustomName(String.valueOf(10 - (count++)));
	        	if(count >= 10) {
	        		this.cancel();
	        		lightSphere.remove();
	        		
	        		Location tl = lightSphere.getEyeLocation().clone();
	        		
	        	    world.playSound(tl, Sound.BLOCK_GLASS_BREAK, 1.0f, 0f);
	        	    world.spawnParticle(Particle.GLOW, tl, 10, 3, 1, 3, 0);
	        	    world.spawnParticle(Particle.SONIC_BOOM, tl, 10, 3, 1, 3, 0);

	        	    // 피해 판정
	        	    for (Entity entity : world.getNearbyEntities(tl, 2.5, 2.5, 2.5)) {
	        	        if (entity instanceof LivingEntity && entity != p && !entity.hasMetadata("fake") && !entity.hasMetadata("portal")) {
	        	            LivingEntity target = (LivingEntity) entity;
	        	            target.damage(6.66, p);
	        	            Holding.holding(null, target, 20l);
	        	        }
	        	    }
	                return;
	        		
	        	}
	        }
	    };
	    task.runTaskTimer(RMain.getInstance(), 0, 20); // 매 2틱마다 크기 증가
	    ordt.put(rn, task.getTaskId());
	}

	// 빛의 구체 움직임
	private void moveLightSphere(ArmorStand lightSphere, Player player, LivingEntity p) {
        String rn = gethero(lightSphere);
        successCount.compute(rn, (k,v) -> v + 1);
        darkSphereSizeUp(rn, darkSphereMap.get(rn), darkSphereSize.get(rn), -0.5f);
        lightSphere.remove(); // 빛의 구체 제거
        lightSphere.getWorld().playSound(lightSphere.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 0f);
        if(successCount.get(rn)>=5) {
        	bossfailed(p, rn);
        }
        return;
	}

	// 랜덤한 위치 반환
	private Location getRandomLocation(Location center, int minDistance, int maxDistance) {
	    Random random = new Random();
	    double angle = random.nextDouble() * 2 * Math.PI;
	    double distance = minDistance + (maxDistance - minDistance) * random.nextDouble();
	    double x = center.getX() + distance * Math.cos(angle);
	    double z = center.getZ() + distance * Math.sin(angle);
	    double y = center.getY(); // 동일한 높이
	    return new Location(center.getWorld(), x, y, z);
	}


	public boolean checkAndApplyCharge(LivingEntity p, EntityDamageByEntityEvent d) {
	    // 체력 조건 확인 및 적용
		if(p.hasMetadata("witherboss") && !p.hasMetadata("ruined")) {
			return true;
		}
	    if ((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue() * 0.2) 
	            && !ordealable.containsKey(p.getUniqueId()) 
	            && p.hasMetadata("ruined")) {
	        p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue() * 0.2);
	        d.setCancelled(true);
	        ordealable.put(p.getUniqueId(), true);
	        return true;
	    }
	    return false;
	}
}