package io.github.chw3021.monsters.nether;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.NethercoreRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;



public class PiglinSkills extends Summoned{

	
	Holding hold = Holding.getInstance();
	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb6cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> shcooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, Long> aicooldown = new HashMap<UUID, Long>();
	

	
	private static final PiglinSkills instance = new PiglinSkills ();
	public static PiglinSkills getInstance()
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
		if(ev.getEntity().hasMetadata("volcanicboss")){

			ev.setCancelled(true);
			
		    LivingEntity p = ev.getEntity();
		    
        	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_SMOKER_SMOKE, 1f, 0f);

			p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 10);
			p.getWorld().spawnParticle(Particle.SMOKE, p.getLocation(), 100, 0.1,0.1,0.1,2);
			AtomicInteger j = new AtomicInteger();


			for(Material c : cookeds) {
        		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	                	p.swingMainHand();
	                	p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FURNACE_FIRE_CRACKLE, 1f, 2f);
	                    Snowball ws = (Snowball) p.launchProjectile(Snowball.class);
	                    ws.setItem(new ItemStack(c));
	                    ws.setShooter(p);
	                    ws.setGlowing(true);
	                    ws.setVelocity(p.getLocation().getDirection().normalize().multiply(1.1));
	        			ws.setMetadata("cookedFoods", new FixedMetadataValue(RMain.getInstance(), true));
	                }
	            }, j.getAndIncrement()*3+10); 
			}


		 }
	}



	private HashMap<UUID, Integer> grillable = new HashMap<UUID, Integer>();
	public void grilled(EntityDamageByEntityEvent d) 
	{
	    
		int sec = 4;
		if(d.getEntity().hasMetadata("volcanicboss") && grillable.containsKey(d.getEntity().getUniqueId())) 
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

	            	grillable.remove(p.getUniqueId());
	                Holding.holding(null, p, 20l);

	                String rn = gethero(p);
					
	                Location tl = gettargetblock(p,4).clone();
	                
					p.getWorld().playSound(tl, Sound.BLOCK_FURNACE_FIRE_CRACKLE, 1f, 0f);
                	p.getWorld().spawnParticle(Particle.ASH, tl, 500, 4, 1, 4, 0);
                	p.getWorld().spawnParticle(Particle.SMOKE, tl, 500, 4, 1, 4, 0);
                	p.getWorld().spawnParticle(Particle.INSTANT_EFFECT, tl, 500, 4, 1, 4, 0);
                	
                	
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
			                	Random random=new Random();
			                	int ri = random.nextInt(7);
			                	Item meat = p.getWorld().dropItemNaturally(l, new ItemStack(Material.RABBIT_FOOT));
			                	meat.setPickupDelay(9999);
			                	meat.setItemStack(new ItemStack(cookeds[ri]));
			                	meat.setOwner(p.getUniqueId());
			                	meat.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
			                	meat.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
			                	meat.setMetadata("stuff", new FixedMetadataValue(RMain.getInstance(), rn));
			                	p.getWorld().spawnParticle(Particle.FLAME, tl, 150, 4, 1, 4, 0.2);
			                	p.getWorld().spawnParticle(Particle.WHITE_SMOKE, tl, 50, 4, 1, 4, 0.2);
			                	p.getWorld().spawnParticle(Particle.DUST_PILLAR, tl, 150, 4, 1, 4, 0.2,getBd(Material.WHITE_WOOL));
			                	p.getWorld().spawnParticle(Particle.WHITE_ASH, tl, 150, 4, 1, 4, 0.2);
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
								p.getWorld().playSound(meat.getLocation(), Sound.BLOCK_FURNACE_FIRE_CRACKLE, 0.8f, 2f);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					                @Override
					                public void run() 
					                {
					                	meat.remove();
					                }
								}, 10);
			                	for(Entity e : p.getWorld().getNearbyEntities(tl, 4,4,4)) {
			                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
			                			LivingEntity le = (LivingEntity)e;

										le.damage(4, p);
										le.setFireTicks(100);
			    						
			                		}
			                	}
			                }
						}, j.incrementAndGet()*2+40);
	                	ordt.put(rn, t);
                    });
					rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	            }
	        }
	        else 
	        {


            	grillable.remove(p.getUniqueId());
                Holding.holding(null, p, 20l);

                String rn = gethero(p);
				
                Location tl = gettargetblock(p,4).clone();
                
				p.getWorld().playSound(tl, Sound.BLOCK_FURNACE_FIRE_CRACKLE, 1f, 0f);
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
					int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		                	Random random=new Random();
		                	int ri = random.nextInt(7);
		                	Item meat = p.getWorld().dropItemNaturally(l, new ItemStack(Material.RABBIT_FOOT));
		                	meat.setPickupDelay(9999);
		                	meat.setItemStack(new ItemStack(cookeds[ri]));
		                	meat.setOwner(p.getUniqueId());
		                	meat.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
		                	meat.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
		                	meat.setMetadata("stuff", new FixedMetadataValue(RMain.getInstance(), rn));
		                	p.getWorld().spawnParticle(Particle.FLAME, l, 50, 4, 4, 4, 0.2);
		                	p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, l, 50, 4, 4, 4, 0.2);
							p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 2f);
							p.getWorld().playSound(meat.getLocation(), Sound.BLOCK_FURNACE_FIRE_CRACKLE, 0.8f, 2f);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				                	meat.remove();
				                }
							}, 10);
		                	for(Entity e : p.getWorld().getNearbyEntities(l, 4,4,4)) {
		                		if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
		                			LivingEntity le = (LivingEntity)e;

									le.damage(4, p);
									le.setFireTicks(100);
		    						
		                		}
		                	}
		                }
					}, j.incrementAndGet()*2+40);
                	ordt.put(rn, t);
                });
				rb3cooldown.put(p.getUniqueId(), System.currentTimeMillis()); // adding players name + current system time in miliseconds
	        }
		}					
	}

	private HashMap<UUID, LivingEntity> blockToPiglin = new HashMap<>();
	private HashMap<UUID, Integer> blockt = new HashMap<>();
	final private void createFallingRod(LivingEntity p, final Location startLocation, final Location targetLocation, String rn) {
	    World world = startLocation.getWorld();
	    Material rodMaterial = Material.FURNACE;
	    int rodHeight = 8;
	    List<FallingBlock> fallingBlocks = new ArrayList<>();
	    List<Double> offsets = new ArrayList<>();

	    // 세워진 막대기 생성
	    for (int i = 0; i < rodHeight; i++) {
	        Location blockLoc = startLocation.clone().add(0, i * 1.5, 0);
	        FallingBlock fallingBlock = world.spawnFallingBlock(blockLoc, getBd(rodMaterial));
	        fallingBlock.setDropItem(false);
	        fallingBlock.setHurtEntities(false);
	        fallingBlock.setGravity(false); // 중력 비활성화
	        fallingBlock.setCancelDrop(true);
	        fallingBlock.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
	        fallingBlocks.add(fallingBlock);
	        offsets.add(i * 1.5); // 각 블록의 초기 높이 저장
	        blockToPiglin.put(fallingBlock.getUniqueId(), p);
	    }

	    Vector startVector = targetLocation.clone().subtract(startLocation.clone()).toVector().normalize();

	    // 스케줄러를 사용해 넘어지는 동작 구현
	    int taskId = Bukkit.getScheduler().runTaskTimer(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {

	            for (int i = 0; i < fallingBlocks.size(); i++) {
	                FallingBlock block = fallingBlocks.get(i);
	                Location tbl = targetLocation.clone().add(startVector.clone().multiply(i));
	                tbl.setY(startLocation.getY()-1);
		    	    Vector direction = tbl.clone().subtract(block.getLocation().clone()).toVector().normalize();

	                // 계단 효과를 위한 개별 블록 타이밍 계산

	                // 넘어지는 위치 계산
	                double heightFactor = offsets.get(i); // 블록의 높이에 비례한 이동량

	                Vector velocity = direction.clone().normalize().multiply(heightFactor*0.1);

	                // 블록이 넘어지는 듯한 물리적 움직임 적용
	                block.setVelocity(velocity);
	            }

	        }
	    }, 4L, 1L).getTaskId();

	    blockt.put(p.getUniqueId(), taskId);
	    ordt.put(rn, taskId);
	    
	    int taskId1 = Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
	        @Override
	        public void run() {

                for (FallingBlock block : fallingBlocks) {
                    blockexplode(block); // 블록 폭발 처리 메서드 호출
                }
                if (blockt.containsKey(p.getUniqueId())) {
                    Bukkit.getScheduler().cancelTask(blockt.remove(p.getUniqueId()));
                }
	        }
	    }, 20).getTaskId();
	    ordt.put(rn, taskId1);
	}


	final private void blockexplode(FallingBlock fallingb) {
		LivingEntity p = (LivingEntity) Holding.ale(blockToPiglin.get(fallingb.getUniqueId()));
		
		if(blockt.containsKey(p.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(blockt.remove(p.getUniqueId()));
		}

		if(ast.containsKey(fallingb.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(ast.remove(fallingb.getUniqueId()));
		}
		Location tl = fallingb.getLocation();
		
		if(fallingb.hasMetadata("yellowcake"+gethero(p))) {
			fallingb.remove();
			return;
		}
			
		
		tl.getWorld().spawnParticle(Particle.FLAME, tl, 1);
		tl.getWorld().spawnParticle(Particle.BLOCK, tl, 400,3,3,3,getBd(Material.FURNACE));

		for (Entity e : p.getWorld().getNearbyEntities(tl, 3, 3, 3))
		{
			if(p!=e && e instanceof Player) {
				Player le = (Player)e;
        		le.damage(6, p);
			}
			
		}
		
		p.getWorld().playSound(tl, Sound.BLOCK_BLASTFURNACE_FIRE_CRACKLE, 1, 0);
		
		fallingb.remove();
	}
	

	public void Block(EntityDropItemEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(blockToPiglin.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
	        	//blockexplode(fallingb);
	        }
		 }
	}



	
	public void Block(EntityDamageByEntityEvent ev) 
	{
		if(ev.getDamager() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getDamager();
	        if(blockToPiglin.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
	        	//blockexplode(fallingb);
	        }
		 }
	}


	
	public void Block(EntityChangeBlockEvent ev) 
	{
		if(ev.getEntity() instanceof FallingBlock){
		    FallingBlock fallingb = (FallingBlock) ev.getEntity();
	        if(blockToPiglin.containsKey(fallingb.getUniqueId())){
	        	ev.setCancelled(true);
	        	//blockexplode(fallingb);
	        }
		 }
	}


	
	
	private HashMap<UUID, Boolean> furable = new HashMap<UUID, Boolean>();
	
	final private void furnace(LivingEntity p) {

    	final World w = p.getWorld();
        Holding.holding(null, p, 25l);
		Location pl = p.getEyeLocation().clone();
		w.playSound(pl, Sound.BLOCK_SMOKER_SMOKE, 1.0f, 2f);
		w.playSound(pl, Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0f);
		w.spawnParticle(Particle.LARGE_SMOKE, pl, 150, 2,2,2);
		w.spawnParticle(Particle.GUST, pl, 150, 2,2,2);
		
		createFallingRod(p,p.getEyeLocation(),gettargetblock(p,5), gethero(p));
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	furable.remove(p.getUniqueId());
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
	

	public void furnace(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("volcanicboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 8;

			if(ordeal.containsKey(p.getUniqueId())) {
				return;
			}
	
			if(p.hasMetadata("failed") || !furable.containsKey(p.getUniqueId())) {
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
		                	furnace(p);
		                	shcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {
		            	furnace(p);
		            	shcooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}


	private HashMap<UUID, Boolean> porkable = new HashMap<UUID, Boolean>();
	
	final private void porkchop(LivingEntity p) {

    	final World w = p.getWorld();
        Holding.holding(null, p, 40l);
		Location pfl = p.getEyeLocation().clone();
		w.playSound(pfl, Sound.ENTITY_PIGLIN_BRUTE_DEATH, 1.0f, 0f);
		w.playSound(pfl, Sound.ENTITY_PIGLIN_BRUTE_DEATH, 1.0f, 2f);
		w.spawnParticle(Particle.SMOKE, pfl, 150, 2,2,2);
		w.spawnParticle(Particle.BLOCK_MARKER, pfl, 20,1,1,1, getBd(Material.FURNACE));
		

        
		int t =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {

        		w.playSound(pfl, Sound.ENTITY_ZOMBIFIED_PIGLIN_HURT, 1.0f, 0f);
        		w.playSound(pfl, Sound.ENTITY_PIG_DEATH, 1.0f, 0f);
		        for(Player pe : NethercoreRaids.getheroes(p)) {
					for(int i = 0; i <50; i++) {
						Arrow ar =p.getWorld().spawnArrow(pe.getLocation(), BlockFace.UP.getDirection() , 0.5f, 60);
						ar.setShooter(p);
	                    Snowball ws = (Snowball) p.launchProjectile(Snowball.class);
	                    ws.setItem(new ItemStack(Material.COOKED_PORKCHOP));
	                    ws.setShooter(p);
	                    ws.setVelocity(ar.getVelocity());
	        			ws.setMetadata("cookedFoods", new FixedMetadataValue(RMain.getInstance(), true));
						ar.remove();
					}
		        }
		        
            }
		}, 40);
        ordt.put(gethero(p), t);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
            	porkable.remove(p.getUniqueId());
            }
	   	}, 60);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			grillable.putIfAbsent(p.getUniqueId(), 1);
            }
	   	}, 80);
	}
	
	public void porkchop(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("volcanicboss") && (d.getEntity() instanceof Mob)) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 10;

			if(ordeal.containsKey(p.getUniqueId())) {
				return;
			}

			if(p.hasMetadata("failed") || !porkable.containsKey(p.getUniqueId())) {
				return;
			}
			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
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
		                	porkchop(p);
		                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

		            	porkchop(p);
	                	aicooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	

	private HashMap<UUID, Boolean> chargable = new HashMap<UUID, Boolean>();
	public void charge(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("volcanicboss")) 
		{
			final LivingEntity p = (LivingEntity)d.getEntity();

			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
				return;
			}

			if(p.hasMetadata("raid")) {
				if(!NethercoreRaids.getheroes(p).stream().anyMatch(pe -> pe.getWorld().equals(p.getWorld()))|| p.hasMetadata("failed")) {
					return;
				}

				final Location tl = NethercoreRaids.getheroes(p).stream().filter(pe -> pe.getWorld().equals(p.getWorld())).findAny().get().getLocation().clone().add(0,0.2,0);
				charge(p,tl);
			}
		}
	}


	private HashMap<UUID, Integer> hookt1 = new HashMap<UUID, Integer>();
	
	final private void chargeStart(LivingEntity p, Location tl) {

        final Location pfl = p.getLocation().clone();
        
        final Vector pv = tl.clone().toVector().subtract(pfl.clone().toVector()).normalize();
        

		p.swingMainHand();
		p.getWorld().playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 0f);
		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BLASTFURNACE_FIRE_CRACKLE, 1.0f, 0f);
			p.getWorld().spawnParticle(Particle.WHITE_ASH ,p.getLocation(), 100, 0.2,1,0.2,1);
			p.getWorld().spawnParticle(Particle.WHITE_SMOKE ,p.getLocation(), 100, 0.2,1,0.2,1);
			p.getWorld().spawnParticle(Particle.BLOCK_MARKER, pfl, 20,1,1,1, getBd(Material.PIGLIN_HEAD));
			Holding.holding(null, p, 25l);


    	int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    		@Override
        	public void run() 
            {
    			if(p.isDead()) {
    				return;
    			}
    			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PIGLIN_ANGRY, 0.1f, 0f);
    			p.getWorld().spawnParticle(Particle.SWEEP_ATTACK ,p.getLocation(), 30, 2,2,2,1);
    			p.getWorld().spawnParticle(Particle.WHITE_SMOKE ,p.getLocation(), 30, 2,2,2,1);
                
                final Location cl = p.getLocation().clone();
                
    			p.setVelocity(pv.normalize().multiply(0.33));

                for(Entity e : cl.getWorld().getNearbyEntities(cl,2,2,2)) {
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
						le.damage(3.5,p);
					}
                }
    			
            }
    	},25,2);
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
     			chargable.remove(p.getUniqueId());
            }
    	},65);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
     		@Override
        	public void run() 
            {	
     			porkable.put(p.getUniqueId(), true);
            }
        }, 180); 
	}
	
	final private void charge(LivingEntity p, Location tl) {

		if(ordeal.containsKey(p.getUniqueId())) {
			return;
		}
		if(p.hasMetadata("failed") || !chargable.containsKey(p.getUniqueId())) {
			return;
		}
		if(rb8cooldown.containsKey(p.getUniqueId()))
        {
            long timer = (rb8cooldown.get(p.getUniqueId())/1000 + 8) - System.currentTimeMillis()/1000; 
            if(!(timer < 0))
            {
            }
            else 
            {
            	rb8cooldown.remove(p.getUniqueId()); // removing player from HashMap
            	chargeStart(p, tl);
                
				rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
            }
        }
        else 
        {
        	chargeStart(p, tl);
			rb8cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		}
	}

	private HashMap<UUID, Boolean> smkable = new HashMap<UUID, Boolean>();
	
	final private void smoker(Location ptl, LivingEntity p, Integer dur) {
		
		String rn = gethero(p);
		World w = ptl.getWorld();
		p.getWorld().playSound(ptl, Sound.BLOCK_SMOKER_SMOKE, 1.0f, 0f);
    	p.getWorld().playSound(ptl, Sound.BLOCK_FIRE_AMBIENT, 1.0f, 2f);
    	
    	w.spawnParticle(Particle.DUST_PILLAR, ptl, 300,2,1,2, getBd(Material.WHITE_WOOL));
    	
        int t = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
                AreaEffectCloud cloud = (AreaEffectCloud) w.spawnEntity(ptl, EntityType.AREA_EFFECT_CLOUD);
                cloud.setParticle(Particle.WHITE_SMOKE);
                cloud.addCustomEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 1, false, false, false), false);
        		cloud.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
        		cloud.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
        		cloud.setMetadata("piglincloud", new FixedMetadataValue(RMain.getInstance(), rn));
                cloud.setRadius(2f);
                cloud.setSource(p);
                cloud.setSilent(false);
                cloud.setColor(Color.WHITE);
                cloud.setDuration(dur);

            	smkable.remove(p.getUniqueId());
            }
        }, 35); 
        ordt.put(gethero(p), t);
	}
	
	public void cloudApplied(AreaEffectCloudApplyEvent ev) {
		if(ev.getEntity().hasMetadata("piglincloud")) {
			 AreaEffectCloud cloud = ev.getEntity();
			 LivingEntity p = (LivingEntity) cloud.getSource();

             for(Entity e : cloud.getWorld().getNearbyEntities(cloud.getLocation().clone(),2,2,2)) {
						if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))) {
							LivingEntity le = (LivingEntity)e;
							le.damage(3.5,p);
						}
                 }
			 
		}
	}
	
	public void smoker(EntityDamageByEntityEvent d) 
	{
		if((d.getEntity() instanceof Mob) && d.getEntity().hasMetadata("volcanicboss")) 
		{
			Mob p = (Mob)d.getEntity();
			int sec = 4;
	        

			if(ordeal.containsKey(p.getUniqueId())) {
				return;
			}
			if((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2) && !ordealable.containsKey(p.getUniqueId())) {
				p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.2);
                d.setCancelled(true);
                ordealable.put(p.getUniqueId(), true);
				return;
			}
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
		                	
			                Holding.holding(null, p, 10l);

			                smoker(ptl, p,300);
		                    
		                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                 		@Override
		                    	public void run() 
		                        {	
		                 			furable.put(p.getUniqueId(), true);
		        	            }
		                    }, 46); 
		                    
	             			
							rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
		                }
		            }
		            else 
		            {

	                	
		                Holding.holding(null, p, 10l);

		                smoker(ptl, p,300);
	                    
	                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                 		@Override
	                    	public void run() 
	                        {	
	                 			furable.put(p.getUniqueId(), true);
	        	            }
	                    }, 46); 
	                    
						rb4cooldown.put(p.getUniqueId(), System.currentTimeMillis());  
					}
		}
	}

	public static HashMap<UUID, Integer> count = new HashMap<UUID, Integer>();
	private HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, Boolean> ordeal = new HashMap<UUID, Boolean>();//volcanicboss 패턴 시작 여부 저장

	public HashMap<UUID, Integer> ast = new HashMap<UUID, Integer>();//ArmorStands damage 태스크 저장
	public HashMap<UUID, Integer> asdt = new HashMap<UUID, Integer>();//ArmorStands remove 태스크 저장
	
	final private void asSpawn(Player pe, String rn, LivingEntity p, final Location fl, int a) {

		final World w = fl.getWorld();
		
		if(a%4==0) {
			FallingBlock newmob = w.spawnFallingBlock(fl, getBd(Material.YELLOW_CANDLE_CAKE));
    		newmob.setMetadata("yellowcake"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setGravity(false);
    		newmob.setCustomNameVisible(false);
    		newmob.setInvulnerable(true);
    		newmob.setMetadata("cake"+rn, new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	        blockToPiglin.put(newmob.getUniqueId(), p);
			int t2 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	            	
	            	Location pel = Holding.ale(pe).getLocation();
	            	Location arl = Holding.ale(newmob).getLocation();
	            	
	            	if(pel.getWorld().equals(arl.getWorld())) {
	            		Vector v = pel.clone().toVector().subtract(arl.clone().toVector()).clone().normalize().multiply(0.025);
	            		if(pel.distance(arl)>10) {
	            			v.multiply(10);
	            		}
	            		Holding.ale(newmob).setVelocity(v);
	            	}
	            	
	        		for(Entity e : newmob.getWorld().getNearbyEntities(newmob.getLocation().clone(), 1, 5, 1)) {
						if(p!=e && e instanceof Player) {
							if(ast.containsKey(newmob.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(ast.remove(newmob.getUniqueId()));
							}
		                	Holding.ale(newmob).remove();
		                	newmob.getWorld().spawnParticle(Particle.HEART, newmob.getLocation(), 20,1,1,1);
		                	pe.getWorld().playSound(pe.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 2);
		                	pe.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20, 20, false,false,false));
		                	return;
						}
	            	}
	            }
	        }, 0,3);
			ordt.put(rn, t2);
			ast.put(newmob.getUniqueId(), t2);
		}
		else {
			

			FallingBlock newmob = w.spawnFallingBlock(fl, getBd(Material.RED_CANDLE_CAKE));

			newmob.setMetadata("stuff"+rn, new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setGravity(false);
			newmob.setCustomNameVisible(false);
			newmob.setInvulnerable(true);
			newmob.setMetadata("cake"+rn, new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
			newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
			newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	        blockToPiglin.put(newmob.getUniqueId(), p);
			
			int t2 =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	            	
	            	Location pel = Holding.ale(pe).getLocation();
	            	Location arl = Holding.ale(newmob).getLocation();

	            	if(pel.getWorld().equals(arl.getWorld())) {
	            		Vector v = pel.clone().toVector().subtract(arl.clone().toVector()).clone().normalize().multiply(0.04);
	            		if(pel.distance(arl)>10) {
	            			v.multiply(10);
	            		}
	            		Holding.ale(newmob).setVelocity(v);
	            	}
	            	
	            	
	        		for(Entity e : newmob.getWorld().getNearbyEntities(newmob.getLocation().clone(), 1, 5, 1)) {
						if(p!=e && e instanceof Player) {
							Player le = (Player)e;
							le.damage(6,p);
						}
	            	}
	            }
	        }, 0,3);
			ordt.put(rn, t2);
			ast.put(newmob.getUniqueId(), t2);
		}
		
		
	}

	final private void cake(LivingEntity p, int a) {
		String rn = p.getMetadata("raid").get(0).asString();
        for(Player pe : NethercoreRaids.getheroes(p)) {
        	
        	if(!pe.getWorld().equals(p.getWorld()) || pe.isDead()) {
        		continue;
        	}
        	
            smoker(pe.getLocation(), p, 40);
            
            final Location fl = pe.getLocation().clone();

			p.getWorld().playSound(pe.getLocation(), Sound.BLOCK_SMOKER_SMOKE, 1, 0);
			p.getWorld().playSound(pe.getLocation(), Sound.BLOCK_HONEY_BLOCK_BREAK, 1, 0);
			p.getWorld().spawnParticle(Particle.FALLING_HONEY, fl, 50,1,1,1);
            
			int t1 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                	pe.playSound(p, Sound.BLOCK_CAKE_ADD_CANDLE, 1, 0);
            		asSpawn(pe, rn, p, fl, a);
                }
            }, 20); 
			ordt.put(rn, t1);  
        }
	}
	
	final private boolean judge(LivingEntity p, String rn) {
		Boolean bool = Bukkit.getWorld("NethercoreRaid").getEntities().stream().anyMatch(e -> e.hasMetadata("yellowcake"+rn));
		if(bool) {
            for(Player pe : NethercoreRaids.getheroes(p)) {
    			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
            		pe.sendMessage(ChatColor.BOLD+"피글린요리사: 그렇게는 배불리 먹지 못해!!!");
    			}
    			else {
            		pe.sendMessage(ChatColor.BOLD+"PiglinCook: That won't fill you up!!!");
    			}
        		Holding.invur(pe, 60l);
    			p.getWorld().playSound(pe.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 0);
        	}
	        int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
					Bukkit.getWorld("NethercoreRaid").getEntities().stream().filter(e -> e.hasMetadata("cake"+rn)).forEach(newmob -> {
	                	Bukkit.getScheduler().cancelTask(ast.get(newmob.getUniqueId()));
	                	ast.remove(newmob.getUniqueId());
	                	Holding.ale(newmob).remove();
					});

	        		if(ordt.containsKey(rn)) {
	        			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
	        		}
	        		
	        		ordeal.remove(p.getUniqueId());
	                for(Player pe : NethercoreRaids.getheroes(p)) {
	            		pe.setHealth(0);
	            	}
	                rb6cooldown.remove(p.getUniqueId());
	            }
	        }, 20);
			ordt.put(rn, t3);
		}
		else {
			bossfailed(p,rn);
		}
		return bool;
	}

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
        
        Long ordealTime = 480L;
        
        
    	p.teleport(rl.clone().add(0, 1, 0));
        Holding.holding(null, p, ordealTime);
        Holding.untouchable(p, ordealTime);
        for(Player pe : NethercoreRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"피글린요리사: 후식 시간이다!");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"PiglinCook: Dessert Time!");
			}
    		pe.teleport(rl.clone().add(0, 1.5, 0));
    		Holding.invur(pe, 40l);
        }
        
        AtomicInteger j = new AtomicInteger(1);
        int t1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	cake(p, j.getAndIncrement());
            }
        }, 20, 20);
		ordt.put(rn, t1);
		
        int t3 =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {
            	judge(p,rn);
            }
        }, ordealTime);
		ordt.put(rn, t3);
	}
	

	final private void bossfailed(LivingEntity p, String rn) {
		if(ordt.containsKey(rn)) {
			ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	ordt.removeAll(rn);
		}
		ordeal.remove(p.getUniqueId());
    	p.playHurtAnimation(0);
		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 2);
		p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 10, 2,2,2);
    	Holding.reset(Holding.ale(p));
    	Holding.ale(p).setMetadata("failed", new FixedMetadataValue(RMain.getInstance(),true));
		Holding.ale(p).removeMetadata("fake", RMain.getInstance());
		Bukkit.getWorld("NethercoreRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());
        for(Player pe : NethercoreRaids.getheroes(p)) {
			if(pe.getLocale().equalsIgnoreCase("ko_kr")) {
        		pe.sendMessage(ChatColor.BOLD+"피글린요리사: 계산은... 필요 없다...");
			}
			else {
        		pe.sendMessage(ChatColor.BOLD+"PiglinCook: No need for payment... it's on the house...");
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
		if(d.getEntity().hasMetadata("volcanicboss") && d.getEntity().hasMetadata("ruined")&& !d.getEntity().hasMetadata("failed")) 
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
	
	
	
	
}