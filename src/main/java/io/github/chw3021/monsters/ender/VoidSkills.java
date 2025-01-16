package io.github.chw3021.monsters.ender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.google.common.collect.HashMultimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.EndercoreRaids;
import io.github.chw3021.rmain.RMain;



public class VoidSkills extends EndercoreRaids{

	
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb5cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> shcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> aicooldown = new HashMap<UUID, Long>();
	

	
	private static final VoidSkills instance = new VoidSkills ();
	public static VoidSkills getInstance()
	{
		return instance;
	}

	private Boolean isRuined(LivingEntity p) {
		if (p.hasMetadata("ruined")) {
			return true;
		}
		return false;
	}
	final Material DHEAD = Material.LIGHT_BLUE_CONCRETE;
	final Material SHEAD = Material.MAGENTA_CONCRETE;

    // 물감 색상에 따른 몬스터 맵핑
    private final Map<Material, MonsterData> monsterMapping = Map.of(
        Material.LIGHT_BLUE_DYE, new MonsterData(EntityType.DROWNED, Color.AQUA, DHEAD),
        Material.GREEN_DYE, new MonsterData(EntityType.CREEPER, null, null),
        Material.MAGENTA_DYE, new MonsterData(EntityType.SKELETON, Color.FUCHSIA, SHEAD)
    );

    // 몬스터 소환 메서드
    public void summonMonster(LivingEntity summoner, Location spawnLocation, Material dye) {

        MonsterData monsterData = monsterMapping.getOrDefault(dye, monsterMapping.values().stream().findAny().get());
        int task = new BukkitRunnable() {
            int step = 0;

            @Override
            public void run() {
                if (step >= 10) { // 20번 반복 후 종료
                    cancel();
                } else {
                    createPaintEffect(spawnLocation.clone().add(0, 1, 0), dye); // 페인트 웅덩이 생성
                    for (Entity e : spawnLocation.getWorld().getNearbyEntities(spawnLocation, 1.5, 1.5, 1.5)) {
                        if (summoner != e && e instanceof LivingEntity && !e.hasMetadata("portal") && !e.hasMetadata("fake")) {
                            LivingEntity le = (LivingEntity) e;
                            le.damage(2, summoner); 
                        }
                    }
                    step++;
                }
            }
        }.runTaskTimer(RMain.getInstance(), 0, 2).getTaskId();
        ordt.put(gethero(summoner), task);
        
        ordt.put(gethero(summoner), 
        new BukkitRunnable() {
            @Override
            public void run() {
                spawnMonsterAtLocation(spawnLocation, monsterData,summoner); // 1초 뒤 몬스터 소환
            }
        }.runTaskLater(RMain.getInstance(), 20L).getTaskId()); // 1초 후 실행 (20 ticks)
    }

    // 페인트 웅덩이 생성
    private void createPaintEffect(Location location, Material dye) {
        World world = location.getWorld();
        if (world == null) return;

        // 해당 위치에 파티클 효과 생성
        world.spawnParticle(Particle.ITEM, location, 150, 1.5, 0.3, 1.5, 0, new ItemStack(dye));
        world.playSound(location, Sound.ENTITY_SLIME_SQUISH, 0.1f, 0.1f);
    }

    // 몬스터 소환 및 커스터마이징
    private void spawnMonsterAtLocation(Location location, MonsterData monsterData, LivingEntity summoner) {
        World world = location.getWorld();
        if (world == null) return;
        
        String rn = gethero(summoner);

        LivingEntity monster = (LivingEntity) world.spawnEntity(location, monsterData.entityType);
        monster.setAI(false);
        monster.setInvulnerable(true);
        monster.setCollidable(false);
        monster.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
        monster.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
        monster.setMetadata("voidSummoned", new FixedMetadataValue(RMain.getInstance(), summoner.getUniqueId().toString()));
        

        // 가죽 갑옷 세트 커스터마이징
        if (monsterData.armorColor != null) {
            monster.getEquipment().setArmorContents(createColoredArmor(monsterData.armorColor));
        }

        // 헬멧 아이템 설정
        if (monsterData.helmetMaterial != null) {
            monster.getEquipment().setHelmet(new ItemStack(monsterData.helmetMaterial));
        }

        monster.teleport(location.clone().subtract(0, 2, 0)); 
        int task = new BukkitRunnable() {
            int step = 0;

            @Override
            public void run() {
                if (step >= 20) { // 20번 반복 후 종료
                    cancel();
                } else {
                    world.playSound(monster.getLocation(), Sound.BLOCK_SLIME_BLOCK_STEP, 0.2f, 0.2f);
                    monster.teleport(monster.getLocation().add(0, 0.2, 0)); // 조금씩 위로 이동
                    step++;
                }
            }
        }.runTaskTimer(RMain.getInstance(), 0, 1).getTaskId();
        ordt.put(rn, task);

        int task2 = new BukkitRunnable() {
            @Override
            public void run() {
            	switch (monsterData.entityType) {
					case EntityType.DROWNED: {
						activateDrownedSkill(monster, summoner);
					}
					case EntityType.SKELETON: {
						activateSkeletonSkill(monster, summoner);
					}
					case EntityType.CREEPER: {
						activateCreeperSkill(monster);
					}
					default:
						break;
				}
            }
        }.runTaskLater(RMain.getInstance(), 30).getTaskId();
        ordt.put(rn, task2);
        
    }
    private void activateDrownedSkill(LivingEntity monster, LivingEntity summoner) {
        final Location currentLocation = monster.getLocation().clone();
        Vector forwardVector = currentLocation.getDirection().normalize().clone();
        monster.getWorld().playSound(monster.getLocation(), Sound.ENTITY_DROWNED_AMBIENT_WATER, 1.1f, 0.8f); // 효과음

        // 1틱마다 몬스터를 전방으로 이동시키고, 그 위치에서 적을 공격합니다.
        ordt.put(gethero(summoner), 
        new BukkitRunnable() {
            double distanceTraveled = 0.0;
            final double maxDistance = 8; // 이동할 최대 거리 (2.5블록)

            @Override
            public void run() {
                // 몬스터가 최대 이동 거리를 넘지 않으면 이동시키기
            	monster.setRiptiding(true);
                monster.getWorld().playSound(monster.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 0.078f, 0.8f);
                if (distanceTraveled < maxDistance) {
                    distanceTraveled += 0.5; // 매 1틱마다 2블록씩 이동
                    Location newLocation = currentLocation.clone().add(forwardVector.clone().multiply(distanceTraveled));
                    if(newLocation.getBlock().isPassable()) {
                        monster.teleport(newLocation); // 몬스터를 새 위치로 텔레포트
                    }

                    // 해당 위치에서 근처 적을 찾아서 공격
                    for (Entity e : monster.getWorld().getNearbyEntities(newLocation, 1.5, 1.5, 1.5)) {
                        if (summoner != e && e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
                            LivingEntity le = (LivingEntity) e;
                            le.damage(2.5, summoner); // 공격
                        }
                    }

                } else {
                    // 이동이 완료되면 작업 종료
                	monster.remove();
                	monster.getWorld().spawnParticle(Particle.DUST_PLUME, monster.getLocation(), 200,1,1,1);
                    this.cancel();
                }
            }
        }.runTaskTimer(RMain.getInstance(), 5L, 2L).getTaskId()); // 1틱에 하나씩 실행
    }

    // 크리퍼 스킬 (1초 뒤 폭발)
    private void activateCreeperSkill(LivingEntity monster) {
        if (monster instanceof Creeper) {
            Creeper creeper = (Creeper) monster;
            creeper.setPowered(true); // 크리퍼 전기충격 활성화 (폭발 효과 강해짐)
            creeper.setFuseTicks(20);
            creeper.ignite();
        }
    }
	public void activateCreeperSkill(ExplosionPrimeEvent d) 
	{
		Entity ex = d.getEntity();
		
		
		if(ex instanceof Creeper) {
			if(ex.hasMetadata("voidSummoned")) {
				d.setRadius(0);
				ex.getWorld().playSound(ex.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.8f, 0.8f);
				ex.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, ex.getLocation(), 1);
				String uidString = ex.getMetadata("voidSummoned").get(0).asString();
				UUID leUUID = UUID.fromString(uidString);
				LivingEntity p = (LivingEntity) Bukkit.getEntity(leUUID);
				if(p != null) {
                    for (Entity e : ex.getWorld().getNearbyEntities(ex.getLocation(), 2.8, 2.8, 2.8)) {
                        if (p != e && e instanceof LivingEntity&& !e.hasMetadata("fake") && !(e.hasMetadata("portal"))) {
                            LivingEntity le = (LivingEntity) e;
                            le.damage(5, p); // 공격
                        }
                    }
				}
				ex.remove();
			}
		}
	}

    // 스켈레톤 스킬 (예시로 스켈레톤의 스킬을 다룰 수 있음)
    private void activateSkeletonSkill(LivingEntity monster, LivingEntity summoner) {
        // 예시로 스켈레톤에게 화살을 발사하는 스킬
        if (monster instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) monster;
            HashSet<Arrow> arrows = new HashSet<Arrow>();
            for(int i = 0; i <9; i++) {
                Arrow arrow = skeleton.getWorld().spawnArrow(skeleton.getEyeLocation(),
                		skeleton.getEyeLocation().getDirection(), 3.5f, 12);
                arrow.setColor(Color.FUCHSIA);
                arrow.setShooter(summoner);
                arrow.setPersistent(false);
                arrow.setBasePotionType(PotionType.HARMING);
                arrow.setMetadata("stuff"+gethero(summoner), new FixedMetadataValue(RMain.getInstance(), gethero(summoner)));
                arrows.add(arrow);
            }
            skeleton.getWorld().playSound(skeleton.getLocation(), Sound.ENTITY_SKELETON_SHOOT, 1.0f, 1.0f);
            new BukkitRunnable() {
                @Override
                public void run() {
                	monster.remove();
                	monster.getWorld().spawnParticle(Particle.DUST_PLUME, monster.getLocation(), 200,1,2,1);
                	
                	arrows.forEach(Arrow::remove);
                }
            }.runTaskLater(RMain.getInstance(), 10);
        }
    }

    // 가죽 갑옷 세트 생성
    private ItemStack[] createColoredArmor(Color color) {
        ItemStack[] armor = new ItemStack[4];
        Material[] armorMaterials = {Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET};

        for (int i = 0; i < armor.length-1; i++) {
            ItemStack item = new ItemStack(armorMaterials[i]);
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
            if (meta != null) {
                meta.setColor(color);
                item.setItemMeta(meta);
            }
            armor[i] = item;
        }
        return armor;
    }

    // 몬스터 데이터를 담는 클래스
    private static class MonsterData {
        final EntityType entityType;
        final Color armorColor;
        final Material helmetMaterial;

        public MonsterData(EntityType entityType, Color armorColor, Material helmetMaterial) {
            this.entityType = entityType;
            this.armorColor = armorColor;
            this.helmetMaterial = helmetMaterial;
        }
    }
	
	

	public void bowshoot(EntityShootBowEvent ev) {
	    if (ev.getEntity().hasMetadata("voidboss")) {
	        ev.setCancelled(true);
	        LivingEntity boss = (LivingEntity) ev.getEntity();

	        // 붓의 휘두름 방향 계산
	        Vector direction = boss.getEyeLocation().getDirection().normalize();
	        Location startLocation = boss.getEyeLocation().add(direction.clone().multiply(1.5)); // 붓의 시작 위치
	        String rn = gethero(boss);

	        // 파티클 색상 정의 (붉은 물감)
	        Particle.DustOptions paintColor = new Particle.DustOptions(Color.OLIVE, 1.5f);

	        // 물감 궤적 저장
	        List<Location> paintLocations = new ArrayList<>();
	        World world = boss.getWorld();

	        // 부채꼴 형태로 물감 생성
	        double angle = 60; // 부채꼴 각도 (30도)
	        int pointsPerLine = 10; // 한 곡선당 점 개수
	        for (int i = 0; i < pointsPerLine; i++) {
	            double progress = (double) i / pointsPerLine; // 진행도 (0 ~ 1)
	            double currentAngle = -angle / 2 + angle * progress; // 부채꼴 각도 내 분배
	            Vector rotatedDir = direction.clone().rotateAroundY(Math.toRadians(currentAngle)); // Y축 회전

	            Location paintLoc = startLocation.clone().add(rotatedDir.multiply(4)); 
	            paintLocations.add(paintLoc);
	        }

            scheduleExplosion(rn, paintLocations, paintColor, world, boss);

	        // 보스 붓 휘두름 효과
	        boss.swingMainHand();
	        boss.getWorld().playSound(boss.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 1.2f);

    		final Object ht = getherotype(rn);

    		
    		ItemStack mainf = new ItemStack(Material.BRUSH);
			if(ht instanceof Player) {
				Player pe = (Player) ht;
				pe.sendEquipmentChange(boss, EquipmentSlot.HAND, mainf);
			}
			else if(getherotype(rn) instanceof HashSet){
				@SuppressWarnings("unchecked")
				HashSet<Player> par = (HashSet<Player>) ht;
	    		par.forEach(pe -> {
	    			pe.sendEquipmentChange(boss, EquipmentSlot.HAND, mainf);
	    		});
			}
	    }
	}
	
	private void scheduleExplosion(String rn, List<Location> locations, Particle.DustOptions paintColor, World world, LivingEntity p) {
	
	    // BukkitRunnable을 사용하여 틱당 3개의 위치를 처리
	    new BukkitRunnable() {
	        private int index = 0; // 현재 처리 중인 인덱스
	
	        @Override
	        public void run() {
	            // 리스트가 끝났으면 작업 종료
	            if (index >= locations.size()) {
	                this.cancel();
	                return;
	            }
	    	    p.getWorld().playSound(locations.get(index), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.05f, 1.5f);

	            // 현재 틱에서 처리할 위치들을 가져옴 (최대 3개)
	            for (int i = 0; i < 3 && index < locations.size(); i++, index++) {
	                Location location = locations.get(index);
	                
	                // 물감 파티클 생성
	                world.spawnParticle(Particle.DUST, location, 10, 0.1, 0.1, 0.1, 0, paintColor);
	
	                // 지연된 폭발 처리
	                int task = Bukkit.getScheduler().runTaskLater(RMain.getInstance(), () -> {
	                    // 폭발 효과
	                    p.getWorld().spawnParticle(Particle.EXPLOSION, location, 1);
	
	                    // 근처 엔티티에게 피해
	                    for (Entity e : world.getNearbyEntities(location, 1.5, 1.5, 1.5)) {
	                        if (e instanceof LivingEntity && !e.hasMetadata("portal") && e != p) {
	                            LivingEntity le = (LivingEntity) e;
	                            le.damage(2.5, p);
	                        }
	                    }
	                }, 15L).getTaskId(); // 15틱 (0.75초) 후 폭발
	
	                ordt.put(rn, task); // 작업 ID 저장
	            }
	        }
	    }.runTaskTimer(RMain.getInstance(), 0L, 1L); // 매 틱마다 실행
	}

	public void hit(ProjectileHitEvent d) 
	{
		Projectile po = d.getEntity();
		if(po.getShooter() instanceof LivingEntity) {
			LivingEntity p = (LivingEntity) po.getShooter();
			if(po.hasMetadata("voidPaint")) {
        		Location l = d.getHitEntity() != null ? d.getHitEntity().getLocation() : d.getHitBlock().getLocation();
        		
        		Snowball sn = (Snowball) po;
        		l.setDirection(p.getEyeLocation().getDirection());
        		
        		summonMonster(p, l.clone().add(0, 0.2, 0), sn.getItem().getType());
			}
		}
	}
	
	
	Material[] paints = {Material.LIGHT_BLUE_DYE,Material.GREEN_DYE,Material.MAGENTA_DYE};
	
	private void paintRain(LivingEntity p) {


    	grillable.remove(p.getUniqueId());
        Holding.holding(null, p, 25l);

        String rn = gethero(p);
		
        Location tl = p.getLocation().clone();
        

    	p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 20,2,1,2);
		p.getWorld().playSound(tl, Sound.ITEM_BUCKET_FILL_LAVA, 1.1f, 0f);
		p.getWorld().playSound(tl, Sound.ITEM_BOOK_PAGE_TURN, 0.6f, 0f);
    	
    	
        ArrayList<Location> meats = new ArrayList<>();
        AtomicInteger j = new AtomicInteger();
        int max = isRuined(p) ? 6 : 10;
        for(int i=0; i<max; i++) {
            Random random=new Random();
        	double number = random.nextDouble() * 4 * (random.nextBoolean() ? -1 : 1);
        	double number2 = random.nextDouble() * 4 * (random.nextBoolean() ? -1 : 1);
        	meats.add(tl.clone().add(number, 0.5, number2));
        }
        
        
        meats.forEach(l ->{
			int t= Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                Random random=new Random();
                @Override
                public void run() 
                {

                	p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 10,1,1,1);

    				Snowball sn = p.getWorld().spawn(l,Snowball.class);
    				sn.setVelocity(new Vector(0,0.5,0)); 
    				sn.setGravity(true);
    				sn.setGlowing(true);
    				sn.setShooter(p);
    				sn.setItem(new ItemStack(paints[random.nextInt(paints.length)]));
    				sn.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), true));
    				sn.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
    				sn.setMetadata("voidPaint", new FixedMetadataValue(RMain.getInstance(), true));
					p.getWorld().playSound(p.getLocation(), Sound.ITEM_DYE_USE, 0.1f, 1f);
                }
			}, j.incrementAndGet()*7+25);
        	ordt.put(rn, t);
        });
	}



	private HashMap<UUID, Integer> grillable = new HashMap<UUID, Integer>();
	public void grilled(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 12;
		if(d.getEntity().hasMetadata("voidboss") && grillable.containsKey(d.getEntity().getUniqueId())) 
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
	            	paintRain(p);
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {

            	paintRain(p);
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
            stand.getEquipment().setHelmet(new ItemStack(Material.BRUSH));
            stand.setHeadPose(new EulerAngle(Math.toRadians(180), 0, Math.toRadians(45)));
            stand.getAttribute(Attribute.SCALE).setBaseValue(3.7);
        });
    }

    Material[] carpets = {Material.RED_CARPET, Material.BLUE_CARPET, Material.GREEN_CARPET, Material.YELLOW_CARPET, Material.PURPLE_CARPET};
    
    private BlockDisplay spawnBlockDisplay(World world, Location location, String rn) {
    	Random random = new Random();
        return world.spawn(location, BlockDisplay.class, stand -> {
            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setBlock(getBd(carpets[random.nextInt(carpets.length)]));
            stand.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
            stand.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
            carpetsGet.put(rn, stand);
        });
    }

    
    HashMultimap<String, BlockDisplay> carpetsGet = HashMultimap.create();
	
    private void summonWoodPillar(LivingEntity boss, Location startLoc) {
        World world = startLoc.getWorld();
        String rn = gethero(boss);

        ArmorStand block = spawnArmorStand(world, startLoc.clone().add(0, -5, 0), rn);
        
        
        BukkitTask bt = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
            int tick = 0;

            @Override
            public void run() {
                Player target = getheroes(boss).stream()
                        .filter(p -> p.isValid() && p.getWorld().equals(world))
                        .min((p1, p2) -> Double.compare(p1.getLocation().distance(startLoc), p2.getLocation().distance(startLoc)))
                        .orElse(null);

                if (target == null || tick >= 180) {
                    removeFingers(block,boss);
                    this.cancel();
                    return;
                }

                movePillarTowardsPlayer(block, target.getLocation(), world, rn);
                
                tick = tick +35;
            }

            private void cancel() {
                Bukkit.getScheduler().cancelTask(blockt.remove(boss.getUniqueId()));
            }
        }, 0L, 35L); 

        blockt.put(boss.getUniqueId(), bt.getTaskId());
        ordt.put(rn, bt.getTaskId());
    }
    
    
    private Location movePillarTowardsPlayer(ArmorStand block, Location targetLoc, World world, String rn) {
        world.spawnParticle(Particle.DRIPPING_WATER, block.getLocation(), 10, 1.5, 1.5, 1.5, 0.1);
        world.playSound(block.getLocation(), Sound.ITEM_DYE_USE, 1f, 0f);
        world.playSound(block.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 0.5f, 1f);

        Vector moveDirection = targetLoc.toVector().subtract(block.getLocation().toVector());
        double distance = moveDirection.length() + 2;

        ArrayList<Location> line = new ArrayList<>();
        for (double i = 0; i < distance; i += 0.5) {
            Location l = block.getLocation().clone().add(moveDirection.clone().normalize().multiply(i));
            line.add(l);
        }

        long totalTick = 22; // 총 틱 수
        int totalSteps = line.size(); // 총 이동 단계
        int interval = (int) (totalTick / totalSteps); // 기본 인터벌
        double batchSize = Math.ceil((double) totalSteps / totalTick); // 틱당 처리할 요소 개수

        AtomicInteger task = new AtomicInteger();
        task.set(Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
            int index = 0;

            @Override
            public void run() {
                if (index >= line.size()) {
                    Bukkit.getScheduler().cancelTask(task.get());
                    return;
                }

                // batchSize만큼 처리
                for (int i = 0; i < batchSize && index < line.size(); i++) {
                    Location l = line.get(index);
                    block.teleport(l);
                    spawnBlockDisplay(world, l.clone().add(0, 0.1, 0), rn);
                    index++;
                }
            }
        }, 0, interval > 0 ? interval : 1).getTaskId()); // interval이 1 미만일 경우 최소 1로 설정

        ordt.put(rn, task.get());
        
        
        return block.getLocation();
    }

    private void removeFingers(ArmorStand pillar, LivingEntity p) {
    	pillar.getWorld().playSound(pillar.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 0f);
    	pillar.getWorld().playSound(pillar.getLocation(), Sound.BLOCK_CHERRY_WOOD_BREAK, 1f, 0f);
    	pillar.remove();
        carpetsGet.get(gethero(p)).forEach(bd ->{

        	bd.getWorld().spawnParticle(Particle.DUST_PILLAR, bd.getLocation(), 30, 2, 4, 2, bd.getBlock());
        	
            for(Entity e : bd.getWorld().getNearbyEntities(bd.getLocation(), 2, 5, 2)) {
                if(p != e && e instanceof LivingEntity && !(e.hasMetadata("fake")) && !(e.hasMetadata("portal"))) {
                    LivingEntity le = (LivingEntity)e;

                    le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 10, 10));
                    le.damage(4.0, p);
                }
            }
            
            bd.remove();
        });
    }

	
	
	private HashMap<UUID, Boolean> handable = new HashMap<UUID, Boolean>();
	
	final private void hand(LivingEntity p) {
		p.getWorld().playSound(p, Sound.ENTITY_ENDER_EYE_DEATH, 1f, 0.2f);

    	final World w = p.getWorld();
		Location pl = gettargetblock(p,6);
		w.playSound(pl, Sound.ENTITY_SHULKER_TELEPORT, 1.0f, 0f);
		w.spawnParticle(Particle.END_ROD, pl, 150, 2,2,2);
		w.spawnParticle(Particle.REVERSE_PORTAL, pl, 150, 2,2,2);
		w.spawnParticle(Particle.ENCHANT, pl, 150, 2,2,2);
		

		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			summonWoodPillar(p,pl);
            }
	   	}, 25);
		ordt.put(gethero(p),task);
	   	
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
		if(d.getEntity().hasMetadata("voidboss")) 
		{
			Mob p = (Mob)d.getEntity();
			
			int sec = 19;
	
	
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

    Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.GREEN};
    
	
	private Location summonBall(LivingEntity boss, final Location randomLocation) {
	    World world = randomLocation.getWorld();
	    if (world == null) return randomLocation;

	    // 반지름과 성장 속도 초기화
	    final double maxRadius = isRuined(boss) ? 6.5 : 4 ; // 최대 반지름
	    final double growthRate = 0.5; // 1틱당 반지름 증가량
	    Random random = new Random();
	    final DustOptions dop = new Particle.DustOptions(colors[random.nextInt(carpets.length)], 2f);

	    // 반복 작업으로 구형 파티클 소환
	    new BukkitRunnable() {
	        double radius = 0.0; // 현재 반지름

	        @Override
	        public void run() {
	            if (radius > maxRadius) {
	                this.cancel(); // 최대 반지름에 도달하면 작업 종료
	                return;
	            }

	            // 구 형태의 파티클 소환
	            for (double theta = 0; theta < Math.PI * 2; theta += Math.PI / 6) { // 수평면 각도
	                for (double phi = 0; phi < Math.PI; phi += Math.PI / 6) { // 수직면 각도
	                    double x = radius * Math.sin(phi) * Math.cos(theta);
	                    double y = radius * Math.cos(phi);
	                    double z = radius * Math.sin(phi) * Math.sin(theta);

	                    Location particleLocation = randomLocation.clone().add(x, y, z);
	                    world.spawnParticle(Particle.DUST, particleLocation, 3, 0.1, 0.1, 0.1, 0,dop); // 파티클
	                }
	            }

	            for(Entity e : world.getNearbyEntities(randomLocation, radius,radius,radius)) {
	                if(boss != e && e instanceof LivingEntity && !(e.hasMetadata("fake")) && !(e.hasMetadata("portal"))) {
	                    LivingEntity le = (LivingEntity)e;

	                    le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 10, 10));
	                    le.damage(2.0, boss);
	                }
	            }

	            // 반지름 증가
	            radius += growthRate;
	        }
	    }.runTaskTimer(RMain.getInstance(), 0L, 5L);

	    return randomLocation;
	}


	public void paintBallSpread(LivingEntity boss) {
	    List<Player> heroes = getheroes(boss).stream()
	            .filter(hero -> hero.getWorld().equals(boss.getWorld())) // 같은 월드의 플레이어만 필터링
	            .collect(Collectors.toList());

	    if (heroes.isEmpty()) return;

	    List<Location> randomLocations = new ArrayList<>();
	    Random random = new Random();

	    for (int i = 0; i < 7; i++) {
	        Player randomPlayer = heroes.get(random.nextInt(heroes.size()));
	        Location baseLocation = randomPlayer.getLocation();
	        double offsetX = random.nextDouble() * 7 - 3.5;
	        double offsetZ = random.nextDouble() * 7 - 3.5;
	        double offsetY = random.nextDouble() * 3; 
	        Location randomLocation = baseLocation.clone().add(offsetX, offsetY, offsetZ);
	        randomLocations.add(randomLocation);
	    }

	    int t = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
	        private int currentIndex = 0;

	        @Override
	        public void run() {
	            World world = boss.getWorld();
	            if (currentIndex >= randomLocations.size()) {
	                Bukkit.getScheduler().cancelTask(this.hashCode());
	                return;
	            }

	            Location randomLocation = randomLocations.get(currentIndex);
	            world.playSound(randomLocation, Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1.0f, 0f);
	            world.playSound(randomLocation, Sound.BLOCK_LAVA_POP, 1.0f, 0f);
	            
	            summonBall(boss, randomLocation);

	            currentIndex++;
	        }
	    }, 5L, 5L).getTaskId(); 
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
	
	
	public void paintBallSpread(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("voidboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 6;

			if(ordeal.containsKey(p.getUniqueId())) {
				return;
			}

			if(p.hasMetadata("failed") || !porkable.containsKey(p.getUniqueId())) {
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
		                	paintBallSpread(p);
		                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

		            	paintBallSpread(p);
	                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	

	private HashMap<UUID, Boolean> chargable = new HashMap<UUID, Boolean>();
	private HashMap<UUID, Integer> illusionTask = new HashMap<UUID, Integer>();

	public void illusionCharge(EntityDamageByEntityEvent d) {
	    if(d.getEntity().hasMetadata("voidboss")) {
	        final LivingEntity p = (LivingEntity)d.getEntity();

	        if (checkAndApplyCharge(p, d)) return;

	        if(p.hasMetadata("raid")) {
	            Optional<Player> hero = getheroes(p).stream()
	                .filter(pe -> pe.getWorld().equals(p.getWorld()))
	                .findAny();

	            if(!hero.isPresent() || p.hasMetadata("failed")) {
	                return;
	            }

	            final Location tl = hero.get().getLocation().clone().add(0, 0.2, 0);
	            illusionCharge(p, tl);
	        }
	    }
	}
	private void illusionStart(LivingEntity p, final Location tl) {
	    final Location cl = tl.clone();
	    final Location fl = p.getLocation().clone();
	    final World w = cl.getWorld();
	    final Vector v = tl.toVector().subtract(fl.toVector()).clone().normalize();
	    String rn = gethero(p);

	    p.swingMainHand();
	    p.getWorld().playSound(cl, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
	    p.getWorld().playSound(fl, Sound.ENTITY_MINECART_RIDING, 0.1f, 2f);

	    List<Location> line = new ArrayList<>();
	    for (int i = 0; i < 15 + cl.distance(fl); i++) {
	        line.add(fl.clone().add(v.clone().multiply(i)));
	    }
	    if(isRuined(p)) {
	    	List<Location> reversed = new ArrayList<>(line);
	    	Collections.reverse(reversed);
	    	line.addAll(reversed);
	    }
	    
	    HashSet<BlockDisplay> displays = new HashSet<BlockDisplay>();

	    int railSpawn = new BukkitRunnable() {
	        int tick = 0;

	        @Override
	        public void run() {
	            if (tick >= line.size()) {
	                this.cancel();
	                return;
	            }

	            Location l = line.get(tick++);
	            BlockDisplay display = (BlockDisplay) w.spawnEntity(l, EntityType.BLOCK_DISPLAY);
	            display.setBlock(getBd(Material.GRAY_CARPET));
	            display.setTransformation(new Transformation(
	                new Vector3f(0, 0, 0),
	                new Quaternionf(),
	                new Vector3f(2f, 0.1f, 2f),
	                new Quaternionf()
	            ));
	            display.setInterpolationDelay(40);
	            display.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
	            display.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
	            displays.add(display);
	        }
	    }.runTaskTimer(RMain.getInstance(), 0, 2).getTaskId();
	    ordt.put(rn, railSpawn);

	    int cart = Bukkit.getScheduler().runTaskLater(RMain.getInstance(), () -> {
	        Minecart minecart = (Minecart) w.spawnEntity(fl, EntityType.MINECART);
	        minecart.setGravity(false);
	        minecart.setInvulnerable(true);
	        minecart.setDisplayBlockData(getBd(Material.JUKEBOX));
	        minecart.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
	        minecart.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
		    p.getWorld().playSound(minecart.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1.4f, 0f);

	        new BukkitRunnable() {
	            @Override
	            public void run() {
	            	minecart.remove();
	            }
	        }.runTaskLater(RMain.getInstance(), line.size()*2).getTaskId();
	        
	        int cartRide = new BukkitRunnable() {
	            int currentIndex = 0;

	            @Override
	            public void run() {
	                if (currentIndex >= line.size() || minecart.isDead()) {
	                    minecart.remove();
	                    this.cancel();
	                    return;
	                }

	                Location target = line.get(currentIndex++);
	                minecart.teleport(target);
	    	        minecart.removePassenger(p);
	    		    p.getWorld().playSound(minecart.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 0.1f, 0.53f);
	    		    p.getWorld().playSound(minecart.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 0.1f, 0.6f);
	        		w.spawnParticle(Particle.NOTE, target, 35, 2,2,2);

	                w.getNearbyEntities(target, 2.5, 2.5, 2.5).stream()
	                        .filter(e -> e instanceof Player && !e.hasMetadata("portal") && !e.hasMetadata("fake") && !e.equals(p))
	                        .forEach(e -> {
	                        	Player pe = (Player) e;
	                        	pe.damage(11.5, p);
	                            pe.setVelocity(pe.getEyeLocation().getDirection().multiply(-3));
	                        });
	            }
	        }.runTaskTimer(RMain.getInstance(), 0, 2).getTaskId();
	        ordt.put(rn, cartRide);

	        illusionTask.put(p.getUniqueId(), cartRide);
	    }, 40L).getTaskId();
	    ordt.put(rn, cart);

	    Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), () -> {
	        if (illusionTask.containsKey(p.getUniqueId())) {
	            Bukkit.getScheduler().cancelTask(illusionTask.get(p.getUniqueId()));
	            illusionTask.remove(p.getUniqueId());
	        }
	        displays.forEach(d -> d.remove());
	        chargable.remove(p.getUniqueId());
	    }, line.size()*2+50);

	    Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), () -> porkable.put(p.getUniqueId(), true), 180);
	}



	private void illusionCharge(LivingEntity p, Location tl) {
	    if(ordeal.containsKey(p.getUniqueId())) {
	        return;
	    }
	    if(p.hasMetadata("failed") || !chargable.containsKey(p.getUniqueId())) {
	        return;
	    }
	    if(rb8cooldown.containsKey(p.getUniqueId())) {
	        long timer = (rb8cooldown.get(p.getUniqueId()) / 1000 + 7) - System.currentTimeMillis() / 1000;
	        if (timer < 0) {
	            rb8cooldown.remove(p.getUniqueId()); 
	            illusionStart(p, tl);
	            rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
	    } else {
	        illusionStart(p, tl);
	        rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	    }
	}
	
	private List<Location> generateSoundWavePattern(Location center, double radius, int points) {
	    List<Location> waveLocations = new ArrayList<>();
	    World world = center.getWorld();
	    double increment = (2 * Math.PI) / points;

	    for (int i = 0; i < points; i++) {
	        double angle = i * increment;
	        double x = center.getX() + radius * Math.cos(angle);
	        double z = center.getZ() + radius * Math.sin(angle);
	        double y = center.getY();
	        waveLocations.add(new Location(world, x, y, z));
	    }

	    return waveLocations;
	}


	
	final private void noteBlock(Location ptl, LivingEntity p) {
		
		String rn = gethero(p);
		ptl.getWorld().playSound(ptl, Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 0.8f, 0.3f);
        BlockDisplay display = (BlockDisplay)  ptl.getWorld().spawnEntity( ptl, EntityType.BLOCK_DISPLAY);
        display.setBlock(getBd(Material.NOTE_BLOCK));
        display.setTransformation(new Transformation(
            new Vector3f(0, 0, 0),
            new Quaternionf(),
            new Vector3f(2.1f, 2.1f, 2.1f),
            new Quaternionf()
        ));
        display.setInterpolationDelay(60);
        display.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
        display.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));

        List<Location> wavePattern = generateSoundWavePattern(ptl, 2.5, 30);
        
	    int railSpawn = new BukkitRunnable() {
	        int tick = 0;

	        @Override
	        public void run() {
	            if (tick++ > 6) {
	                this.cancel();
	                display.remove();
	                return;
	            }
	            ptl.getWorld().playSound(ptl, Sound.BLOCK_NOTE_BLOCK_BASS, 0.8f, 0f);
	            ptl.getWorld().playSound(ptl, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 0.8f, 0.3f);
	            ptl.getWorld().spawnParticle(Particle.NOTE, ptl, 100, 2,2,2);
	            for (Location loc : wavePattern) {
	                ptl.getWorld().spawnParticle(Particle.END_ROD, loc, 4, 0.1, 0.1, 0.1, 0.05);
	            }
	            ptl.getWorld().getNearbyEntities(ptl, 2.5, 2.5, 2.5).stream()
                .filter(e -> e instanceof Player && !e.hasMetadata("portal") && !e.hasMetadata("fake") && !e.equals(p))
                .forEach(e -> {
                	Player pe = (Player) e;
                	pe.damage(2.5, p);
                    pe.setVelocity(pe.getEyeLocation().getDirection().multiply(-1));
                });
        	}
	    }.runTaskTimer(RMain.getInstance(), 20, 10).getTaskId();
	    ordt.put(rn, railSpawn);
	}
	
	public void teleportEx(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Mob) && d.getEntity().hasMetadata("voidboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 9;
	        

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
		                	

			                noteBlock(ptl, p);
		                    
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


		                noteBlock(ptl, p);
	                    
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

	private static HashMap<UUID, Boolean> counterable = new HashMap<UUID, Boolean>();
	
	private HashMap<UUID, Integer> count = new HashMap<UUID, Integer>();

	public static HashMap<UUID, Integer> countt = new HashMap<UUID, Integer>();
	

	final private void nightmare(LivingEntity p, Location rl, String rn) {
        try {

            Player tpe = getheroes(p).stream().filter(pe ->!pe.isDead()&&pe.getWorld().equals(p.getWorld())).findAny().get();
            p.teleport(rl.clone().add(2, 0, -2));
            final Location fl = p.getLocation().clone();
			p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation().clone(), 50,0.5,0.5,0.5,0.02);
			
    		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
			
			
            getheroes(p).forEach(pe ->{
            	pe.playSound(p, Sound.ENTITY_EVOKER_CAST_SPELL, 0.6f, 0.66f);
            });
    		setColorHead(p);
    		// 삼각형의 변 길이 (삼각형 크기 조절)
    		
    		double radius = 6.2; 
    		int vertexCount = paints.length;
    		Random random = new Random();

    		// 리스트를 만들어서 셔플
    		List<Material> shuffledPaints = new ArrayList<>(Arrays.asList(paints));
    		Collections.shuffle(shuffledPaints, random);

    		// 삼각형 꼭짓점 계산
    		for (int i = 0; i < vertexCount; i++) {
    		    // 각도를 계산 (각 꼭짓점은 360도를 3등분한 각도에 위치)
    		    double angle = 2 * Math.PI * i / vertexCount;

    		    // 삼각형 꼭짓점의 x, z 좌표 계산
    		    double offsetX = radius * Math.cos(angle);
    		    double offsetZ = radius * Math.sin(angle);

    		    // 꼭짓점 위치
    		    Location vertex = fl.clone().add(offsetX, 0, offsetZ);

    		    // 셔플된 순서대로 몬스터 소환
    		    summonMonster(p, vertex, shuffledPaints.get(i));
    		}

    		swoopWarning(p, fl, tpe);

        	p.swingMainHand();
        
        }
        catch(NullPointerException | NoSuchElementException | IllegalArgumentException e) {
        	return;
        }
	}
	
	final private void swoopWarning(LivingEntity p, Location pl, Player tpe) {
	    World w = tpe.getWorld();

	    double increasement = 0.5; // 이동 거리 증가값

	    AtomicInteger task = new AtomicInteger();

	    int t = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
	        int index = 0;
    	    List<Location> line = new ArrayList<>();

	        @Override
	        public void run() {
	            if (index++ >= 10) {
	                pl.getWorld().playSound(pl, Sound.BLOCK_TRIAL_SPAWNER_OMINOUS_ACTIVATE, 1f, 0f);
	                Bukkit.getScheduler().cancelTask(task.get());
	                nightswoop(p, pl, tpe, 22, line);
	                return;
	            }
	    	    line = new ArrayList<>();

	            Vector vec = tpe.getLocation().clone().toVector().subtract(pl.clone().toVector());
	            for (double an = 0; an <= tpe.getLocation().distance(pl) + 5; an += increasement) {
	                line.add(pl.clone().add(vec.clone().normalize().multiply(an)));
	            }

	            line.forEach(l -> w.spawnParticle(Particle.NOTE, l, 1));
	            w.playSound(tpe, Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1);
	            w.playSound(pl, Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1);
	        }
	    }, 0, 3).getTaskId();
	    task.set(t);
	    ordt.put(gethero(p), t);
	}
	
	
	final private void nightswoop(LivingEntity p, Location pl, Player tpe, Integer totalTick, List<Location> line) {
		
        int totalSteps = line.size();
        int interval = (int) (totalTick / totalSteps);
        double batchSize = Math.ceil((double) totalSteps / totalTick);

        AtomicInteger task = new AtomicInteger();

        int t = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
            int index = 0;

            @Override
            public void run() {
                if (index >= line.size()) {
                    Bukkit.getScheduler().cancelTask(task.get());
                    return;
                }

                for (int i = 0; i < batchSize && index < line.size(); i++) {
                    Location l = line.get(index);

                    p.setRotation(l.getYaw(), l.getPitch());
                    p.setGliding(true);
                    p.getWorld().spawnParticle(Particle.END_ROD, l, 3, 1, 1, 1);
                    p.teleport(l);

                    for (Entity e : l.getWorld().getNearbyEntities(l, 1.8, 2.5, 1.8)) {
                        if (e!=p && e.hasMetadata("voidSummoned")) {
                            nightCounter(p, e);
                            Bukkit.getScheduler().cancelTask(task.get());
                            return;
                        }

    	                if(p != e && e instanceof LivingEntity && !(e.hasMetadata("fake")) && !(e.hasMetadata("portal"))) {
    	                    LivingEntity le = (LivingEntity)e;

    	                    le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 12, 12));
    	                    le.damage(1.1, p);
    	                }
                    }
                    index++;
                }
            }
        }, 0, interval).getTaskId();
        task.set(t);
        countt.put(p.getUniqueId(), t);
        ordt.put(gethero(p), t);
	}
	
	private void killAll(LivingEntity p, String rn) {

		

        if(ordt.containsKey(rn)) {
        	ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
        }
    	ordeal.remove(p.getUniqueId());
    	colorHead.remove(p.getUniqueId());
    	rb5cooldown.remove(p.getUniqueId()); 
		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
        for(Player pe : getheroes(p)) {
    		if(pe.getWorld().equals(p.getWorld()) && pe.getWorld() == p.getWorld()) {
    			count.remove(p.getUniqueId());
				if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
					pe.sendMessage(ChatColor.BOLD + "공허의화신: " + ChatColor.RED + "내 걸작이 되거라..");
				}
				else {
					pe.sendMessage(ChatColor.BOLD + "VoidKing: " + ChatColor.RED + "Become my masterpiece...");
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
	
	
	
	public void nightCounter(LivingEntity p, Entity entity) 
	{
		String rn = gethero(p);
		
		if(ordeal.containsKey(p.getUniqueId())) {
    		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
			
			Boolean counter = false;
			
			if(entity instanceof Skeleton) {
				if(colorHead.getOrDefault(p.getUniqueId(), Material.PLAYER_HEAD) == SHEAD) {
					counter = true;
				}
			}
			if(entity instanceof Drowned) {
				if(colorHead.getOrDefault(p.getUniqueId(), Material.PLAYER_HEAD) == DHEAD) {
					counter = true;
				}
			}
			if(entity instanceof Creeper) {
				if(colorHead.getOrDefault(p.getUniqueId(), Material.PLAYER_HEAD) == Material.CREEPER_HEAD) {
					counter = true;
				}
			}
			if(counter) {

				p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation().clone(), 150,2,2,2);

				
				count.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
				count.putIfAbsent(p.getUniqueId(), 1);
				
	            Location rl = getraidloc(p).clone();
		        p.playHurtAnimation(0);
		        Bukkit.getScheduler().cancelTask(countt.get(p.getUniqueId()));
	        	counterable.remove(p.getUniqueId());
	            p.teleport(rl);

		        getheroes(p).forEach(pe ->{
		        	pe.playSound(pe, Sound.BLOCK_ENDER_CHEST_OPEN, 1, 0.1f);
		        	pe.sendMessage(ChatColor.AQUA + "[" + count.get(p.getUniqueId()) + "/5]");
		        	Random random1=new Random();
		        	double number1 = (random1.nextDouble()+1) * 5 * (random1.nextBoolean() ? -1 : 1);
		        	double number21 = (random1.nextDouble()+1) * 5 * (random1.nextBoolean() ? -1 : 1);
		        	Location esl1 = rl.clone().add(number1, 0.5, number21);
		        	pe.teleport(esl1);
		        });
		        
		        if(count.get(p.getUniqueId()) == 5) {
	                p.teleport(rl);
		        	
		        	ordeal.remove(p.getUniqueId());
		        	colorHead.remove(p.getUniqueId());

		    		p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
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
	                for(Player pe : getheroes(p)) {
						if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
							pe.sendMessage(ChatColor.BOLD + "공허의화신: " + ChatColor.GRAY + "내 작품들이...");
						}
						else {
							pe.sendMessage(ChatColor.BOLD + "VoidKing: " + ChatColor.GRAY + "My art pieces...");
						}
	            		Holding.holding(pe, p, 300l);
	                }
					
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
	        else {
                for(Player pe : getheroes(p)) {
                	pe.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 25, 25));
                	pe.damage(25, p);
                }
	        }
			
		}
	
	}
	
	private HashMap<UUID, Material> colorHead = new HashMap<UUID, Material>();
	Material[] colorHeads = new Material[] {DHEAD, SHEAD, Material.CREEPER_HEAD};
	
	private Material setColorHead(LivingEntity p) {

		Random ran = new Random();
		final Material m = colorHeads[ran.nextInt(colorHeads.length)];
		
		colorHead.put(p.getUniqueId(), m);
		
        for(Player pe : getheroes(p)) {
        	pe.sendEquipmentChange(p, EquipmentSlot.HEAD, new ItemStack(m));
        	pe.sendEquipmentChange(p, EquipmentSlot.HAND, new ItemStack(m));
        	pe.sendEquipmentChange(p, EquipmentSlot.OFF_HAND, new ItemStack(m));
        }
        
        return m;
	}
	    
	final long ORDEALTIME = 1200;
		
	final private void nightmareevent(LivingEntity p, EntityDamageByEntityEvent d) {

    	ordeal.put(p.getUniqueId(), true);
		String rn = gethero(p);
        Location rl = getraidloc(p).clone();

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
        p.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));

		ItemStack mainf = new ItemStack(Material.BRUSH);
        getheroes(p).forEach(pe ->{
			pe.sendEquipmentChange(p, EquipmentSlot.HAND, mainf);
        	Holding.invur(pe, 60l);
        	AtomicReference<String> msg = new AtomicReference<String>(ChatColor.BOLD+"Lead the boss toward the monsters");
        	if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		msg.set(ChatColor.BOLD+"보스를 몬스터쪽으로 유도하세요");
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
                nightmare(Holding.ale(p),rl,rn);
            }
        }, 100, 80);
		ordt.put(rn, i1); 

			int i2 = Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {	
				killAll(p, rn);
            }
        }, ORDEALTIME+1); 
		ordt.put(rn, i2); 
		
	}
	
	public void nightMare(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 70;
		if(d.getEntity().hasMetadata("voidboss") && d.getEntity() instanceof LivingEntity&& !d.isCancelled() && d.getEntity().hasMetadata("ruined")) 
		{
			LivingEntity p = (LivingEntity)d.getEntity();
			if(p.hasMetadata("failed")) {
				return;
			}
			if(!(p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2)|| !ordealable.containsKey(p.getUniqueId())) {
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
	            	nightmareevent(p,d);
		            rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	            }
	        }
	        else 
	        {

	        	nightmareevent(p,d);
	            rb5cooldown.put(p.getUniqueId(), System.currentTimeMillis());
	        }
		
		}
	}
	
}