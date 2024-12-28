package io.github.chw3021.monsters.nether;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.NethercoreRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class CrimsonSkills extends Summoned{

	
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> shcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> aicooldown = new HashMap<UUID, Long>();
	

	
	private static final CrimsonSkills instance = new CrimsonSkills ();
	public static CrimsonSkills getInstance()
	{
		return instance;
	}
	
	
	public void hit(ProjectileHitEvent d) 
	{
		if(d.getEntity() instanceof Snowball) 
		{
			Snowball po = (Snowball)d.getEntity();
			if(po.getShooter() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) po.getShooter();
				if(po.hasMetadata("cookedFoods")) {
            		final Location l = d.getHitEntity() != null ? d.getHitEntity().getLocation() : d.getHitBlock().getLocation();

					l.getWorld().spawnParticle(Particle.SMALL_FLAME, l, 50);
					l.getWorld().spawnParticle(Particle.BLOCK, l, 200,1.5,1.5,1.5,getBd(Material.FIRE_CORAL_BLOCK));
					l.getWorld().playSound(l, Sound.BLOCK_CORAL_BLOCK_BREAK, 1f, 1.5f);
					
            		for(Entity e : l.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(2.5,p);
							Holding.holding(null, le, 1l);
						}
                	}
				}
			}
			
		}
	}
	

	Material[] cookeds = new Material[] {Material.ROTTEN_FLESH, 
			Material.SPIDER_EYE,
			Material.RABBIT_FOOT,
			Material.NETHER_WART, 
			Material.MAGMA_CREAM,
			Material.PHANTOM_MEMBRANE,
			Material.GLISTERING_MELON_SLICE
	};
	
	public void bowshoot(EntityShootBowEvent ev) 
	{
		if(ev.getEntity().hasMetadata("crimsonboss")){

		    LivingEntity p = ev.getEntity();
		    
		    String rn = gethero(p);
		    
		    Vector velocity = p.getEyeLocation().getDirection();
		    final Vector v;

		    // 벡터의 길이가 0인지 확인
		    if (velocity.lengthSquared() > 0) {
		        v = velocity.normalize().clone();
		    } else {
		        v = new Vector(0, 0, 0); // 기본값으로 설정 (이동이 발생하지 않도록)
		    }

			ev.setCancelled(true);
		    p.swingMainHand();
		    p.playEffect(EntityEffect.ZOGLIN_ATTACK);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_STEP, 1.0f, 0.2f);
			for(int i = 0; i<3; i++) {
                ordt.put(rn, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
            			for(int i = 0; i<3; i++) {
                            ordt.put(rn, Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                         		@Override
                            	public void run() 
            	                {	
                         			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1,0,false,false));
            	                	p.setVelocity(v.clone().multiply(0.6));
            						for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2, 2, 2)) {
            							if(e instanceof LivingEntity&&  !(e.hasMetadata("portal")) && e!=p) {
            								LivingEntity le = (LivingEntity)e;
            								le.damage(3,p);
            								Holding.holding(null, le, 5l);
            							}
            						}
            	                	
            		            }
                    	   	}, i));
            			}
    	    			Location pl = p.getLocation().clone();
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 0.6f, 0.4f);
    					p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, pl, 15, 2,1,2);
         				p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, pl, 30);
         				p.swingMainHand();
	                	
		            }
        	   	}, i*3+5));
			}

		 }
	}



	private void burst(LivingEntity p) {

		cursable.remove(p.getUniqueId());
        
        Holding.holding(null, p, 35l);

        Location tl = gettargetblock(p,4).clone();
        
		p.getWorld().playSound(tl, Sound.BLOCK_LAVA_AMBIENT, 1f, 2f);
    	p.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, tl, 100, 4, 1, 4, 0, new Particle.DustTransition(Color.ORANGE, Color.RED, 5f));

        
		ArrayList<Location> line = new ArrayList<Location>();
        AtomicInteger j = new AtomicInteger(0);
        for(double d = 0.1; d <= 7; d += 0.3) {
				line.add(tl.clone().add(0, d, 0).setDirection(BlockFace.UP.getDirection()));
        }
        
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1.0f, 0f);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2f);
		for(int i = 0; i <7; i++) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                    line.forEach(l ->  {	
                    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                		@Override
                        	public void run() 
                            {	
        						tl.getWorld().spawnParticle(Particle.DUST_PILLAR, l, 100, 4,0.5,4,0 ,getBd(Material.CRIMSON_PLANKS));
            					tl.getWorld().spawnParticle(Particle.DUST_PILLAR, l, 100, 4,0.5,4,0 ,getBd(Material.CRIMSON_STEM));
                            }
                	   }, j.getAndIncrement()/230); 
                    });
                	for(Entity e : p.getWorld().getNearbyEntities(tl, 4,7,4)) {
                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
                			LivingEntity le = (LivingEntity)e;

							le.damage(4, p);
							le.setVelocity(BlockFace.UP.getDirection());
    						
                		}
                	}
                }
            }, i*2+35);
        }
	}
	
	private HashMap<UUID, Integer> cursable = new HashMap<UUID, Integer>();
	public void curse(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 8;
		if(d.getEntity().hasMetadata("crimsonboss") && cursable.containsKey(d.getEntity().getUniqueId())) 
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
	            	burst(p);
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {

            	burst(p);
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
            stand.getEquipment().setHelmet(new ItemStack(Material.STRIPPED_CRIMSON_STEM));
            stand.getAttribute(Attribute.SCALE).setBaseValue(3);
        });
    }
    

	final private void Ray(Location as, LivingEntity le) {
		final World snw = as.getWorld();
		final Location lel = le.getEyeLocation().clone();
		final Location snl = as.clone();
		final Vector v = lel.clone().toVector().subtract(snl.clone().toVector());
		final double dis = lel.distance(snl);
		
		HashSet<Location> line = new HashSet<>();
		for(double d = 0; d<dis; d += 0.1) {
			line.add(snl.clone().add(v.clone().normalize().multiply(d)));
		}
		line.forEach(l -> {
			snw.spawnParticle(Particle.BLOCK, l, 3, 0.01,0.01,0.01,0, Material.NETHERRACK.createBlockData());
		});
	}
	
    private void summonWoodPillar(LivingEntity boss, Location startLoc) {
        World world = startLoc.getWorld();
        String rn = gethero(boss);

        List<ArmorStand> pillars = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
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

                if (target == null || tick >= 120) { // 3초 이후 또는 대상 없음
                    removeFingers(pillars);
                    this.cancel();
                    return;
                }

                movePillarTowardsPlayer(pillars, target.getLocation());

                for (Entity e : world.getNearbyEntities(pillars.get(0).getLocation(), 2, 2, 2)) {
                    if (e instanceof Player && e != boss) {
                        Player player = (Player) e;
                        player.damage(2, boss);
                        player.setVelocity(player.getVelocity().multiply(0.3));
                        player.playSound(player.getLocation(), Sound.ENTITY_WITCH_DRINK, 0.05f, 0.1f);
						Ray(pillars.get(0).getLocation(),player);
                        boss.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 5,5,false,false));
                    }
                }

                tick++;
            }

            private void cancel() {
                Bukkit.getScheduler().cancelTask(this.hashCode());
            }
        }, 0L, 2L); // 0틱 지연, 2틱 간격으로 실행

        blockt.put(boss.getUniqueId(), bt.getTaskId());
        ordt.put(rn, bt.getTaskId());
    }

    private void movePillarTowardsPlayer(List<ArmorStand> pillars, Location targetLoc) {
        for (ArmorStand block : pillars) {
        	block.getWorld().spawnParticle(Particle.CRIMSON_SPORE, block.getLocation(), 10, 1.5, 1.5, 1.5, 0.1);
        	block.getWorld().playSound(block.getLocation(), Sound.ENTITY_PLAYER_TELEPORT, 0.05f, 2f);
            Vector moveDirection = targetLoc.toVector().subtract(block.getLocation().toVector());
            double distance = moveDirection.length();

            if (distance > 0.5) {
                double speed = Math.log(distance + 0.5) * 0.1;
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
		p.getWorld().playSound(p, Sound.ENTITY_BREEZE_CHARGE, 0.6f, 0.6f);
		p.getWorld().playSound(p, Sound.BLOCK_END_PORTAL_SPAWN, 0.3f, 1.5f);

    	final World w = p.getWorld();
        Holding.holding(null, p, 25l);
		Location pl = gettargetblock(p,6);
		w.playSound(pl, Sound.ENTITY_SHULKER_TELEPORT, 1.0f, 0f);
		w.spawnParticle(Particle.FLAME, pl, 150, 2,2,2);
		w.spawnParticle(Particle.REVERSE_PORTAL, pl, 150, 2,2,2);
		w.spawnParticle(Particle.RAID_OMEN, pl, 150, 2,2,2);
		

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
		if(d.getEntity().hasMetadata("crimsonboss")) 
		{
			Mob p = (Mob)d.getEntity();
			
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



	private HashMap<UUID, Boolean> phantomable = new HashMap<UUID, Boolean>();
	final private void phantom(LivingEntity p) {

		World w = p.getWorld();
		
		Location pl = p.getEyeLocation().clone();

        w.playSound(p.getLocation(), Sound.ENTITY_ZOGLIN_ANGRY, 1.2f, 0.5f);
		p.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, pl, 100, 2,2,2);
		for(int i = 0; i<10; i++) {
            int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
         		@Override
            	public void run() 
                {	
                	Location tl = p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(1.8));
	    			Location pl = p.getEyeLocation().clone();
                	p.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(0.6));
            		w.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.2f, 0f);
                    w.playSound(p.getLocation(), Sound.ENTITY_HUSK_STEP, 0.1f, 1.5f);
     				w.spawnParticle(Particle.SWEEP_ATTACK, pl, 25, 4,4,4);
     				w.spawnParticle(Particle.CRIMSON_SPORE, pl, 25, 1.5,1.5,1.5);

                	for(Entity e : p.getWorld().getNearbyEntities(tl, 4,4,4)) {
                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
                			LivingEntity le = (LivingEntity)e;

							le.damage(2, p);
							le.teleport(tl);
    						
                		}
                	}
                	
	            }
    	   	}, i*2+25);
            ordt.put(gethero(p), task);
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			phantomable.putIfAbsent(p.getUniqueId(), true);
            }
	   	}, 80);
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
		if(d.getEntity().hasMetadata("crimsonboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 6;
			

			if(p.hasMetadata("failed")|| ordeal.containsKey(p.getUniqueId()) || !phantomable.containsKey(p.getUniqueId())) {
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
		if(d.getEntity().hasMetadata("crimsonboss")) 
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
	
	final private void waveStart(LivingEntity p, Location tl) {
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LLAMA_SPIT, 1.0f, 0.1f);

        HashSet<Location> line = new HashSet<Location>();
        AtomicInteger j = new AtomicInteger(0);
    	j.set(Bukkit.getServer().getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
    		
	    	int tick = 0;
			@Override
        	public void run() 
            {	
				if(tick>=50) {
					Bukkit.getScheduler().cancelTask(j.get());
				}
    	        for(double d = -Math.PI/6; d<= Math.PI/6; d += Math.PI/180) {
    	            Location l = p.getLocation();
    	            l.setDirection(l.getDirection().normalize().rotateAroundY(d));
    	            l.add(l.getDirection().normalize().multiply((tick++)*0.2));
    	            line.add(l);
    	        }
    	        line.forEach(l ->{
					p.getWorld().spawnParticle(Particle.BLOCK, l, 2, 0.5,0.5,0.5,0 ,Material.CRIMSON_PLANKS.createBlockData());

		        	for (Entity a : p.getWorld().getNearbyEntities(l, 1, 1, 1))
					{
						if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal"))) 
						{
							LivingEntity le = (LivingEntity)a;
							le.damage(1, p);
						}
					}
    	        });
			
            }
    	}, 30, 1).getTaskId());
    	ordt.put(gethero(p), j.get());
	}
	

	final private void wave(LivingEntity p, Location tl) {
	    if (p.hasMetadata("failed") || ordeal.containsKey(p.getUniqueId()) || !waveable.containsKey(p.getUniqueId())) {
	        return;
	    }

	    if (rb8cooldown.containsKey(p.getUniqueId())) {
	        long timer = (rb8cooldown.get(p.getUniqueId()) / 1000 + 6) - System.currentTimeMillis() / 1000;
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
	
	final private void jumpAndHit(final Location tl, LivingEntity p) {
		
		Holding.holding(null, p, 22l);
	    Location fl = p.getLocation().clone(); // 시작 위치
	    Location jl = tl.clone().add(0, 4, 0); // 목표 도약 위치
	    World world = p.getWorld();
        world.spawnParticle(Particle.WHITE_SMOKE, tl, 20, 2, 0.2, 2, 0);
	    world.playSound(tl, Sound.ENTITY_ENDER_DRAGON_GROWL, 1f, 2f);

	    // 초기 이동 설정
	    double totalTicks = 18; // 도약에 걸리는 전체 시간 (tick 단위)
	    double tickInterval = 1; // 각 tick 간격 (1tick = 50ms)
	    AtomicInteger currentTick = new AtomicInteger(0); // 현재 진행 중인 tick

	    // 방향 벡터 계산
	    Vector horizontalDirection = jl.toVector().subtract(fl.toVector()).normalize(); // 수평 이동 방향
	    double totalDistance = fl.distance(jl); // 총 이동 거리
	    double speed = totalDistance / totalTicks; // 수평 속도
	    

	    AtomicInteger j = new AtomicInteger();
	    j.set(Bukkit.getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {
	            int tick = currentTick.getAndIncrement();
	            if (tick > totalTicks) {
	                // 도착 후 공격 동작 실행
	                performAttack(tl, p);
	                Bukkit.getScheduler().cancelTask(j.get());
	                return;
	            }

	            // 로그 곡선 기반 높이 계산
	            double progress = (double) tick / totalTicks; // 진행 비율 (0 ~ 1)
	            double height = 4 * Math.log10(1 + 9 * progress); // 높이 값 (로그 함수: log10(1 + 9x))

	            // 새로운 위치 계산
	            Location newLocation = fl.clone().add(horizontalDirection.clone().multiply(speed * tick)); // 수평 이동
	            newLocation.setY(fl.getY() + height); // 높이 적용

	            // 보스몹 이동
	            p.teleport(newLocation);

	            // 이동 중 파티클 효과
	            world.spawnParticle(Particle.CRIMSON_SPORE, newLocation, 5, 0.2, 0.2, 0.2, 0);
	        }
	    }, 22L, (long) tickInterval)); 

	    // 태스크 저장 (필요 시 추가 관리)
	    ordt.put(gethero(p), j.get());
	}

	private void performAttack(Location tl, LivingEntity p) {
	    World world = p.getWorld();

	    p.teleport(tl);
	    // 내려찍기 공격 효과
	    world.playSound(tl, Sound.ENTITY_ELDER_GUARDIAN_HURT, 2.0f, 0f);
	    world.spawnParticle(Particle.EXPLOSION, tl, 10, 3, 1, 3, 0);

	    // 피해 판정
	    for (Entity entity : world.getNearbyEntities(tl, 3, 3, 3)) {
	        if (entity instanceof LivingEntity && entity != p) {
	            LivingEntity target = (LivingEntity) entity;
	            target.damage(6.0, p);
	            target.setVelocity(tl.toVector().subtract(target.getLocation().toVector()).normalize().multiply(0.5).setY(-5));
	        }
	    }
	}

	
	
	public void storm(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Mob) && d.getEntity().hasMetadata("crimsonboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 5;
	        

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

			                jumpAndHit(ptl, p);
		                    
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		                    	public void run() 
		                        {	
		                 			handable.put(p.getUniqueId(), true);
		        	            }
		                    }, 46); 

		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		                    	public void run() 
		                        {	
		                 			phantomable.put(p.getUniqueId(), true);
		        	            }
		                    }, 50); 
	             			
							rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {


		                Holding.holding(null, p, 10l);

		                jumpAndHit(ptl, p);
	                    
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                 		@Override
	                    	public void run() 
	                        {	
	                 			handable.put(p.getUniqueId(), true);
	        	            }
	                    }, 46); 

	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                 		@Override
	                    	public void run() 
	                        {	
	                 			phantomable.put(p.getUniqueId(), true);
	        	            }
	                    }, 50); 
             			
						rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}


	
}