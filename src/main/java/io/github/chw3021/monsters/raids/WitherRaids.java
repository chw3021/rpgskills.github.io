package io.github.chw3021.monsters.raids;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager.Type;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.items.Elements;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class WitherRaids extends Summoned implements Listener {

	public static HashMap<UUID, Location> beforepl = new HashMap<UUID, Location>();
	public Multimap<String, UUID> raider = ArrayListMultimap.create();
	public static Multimap<String, UUID> heroes = ArrayListMultimap.create();
	public static HashMap<String, Location> raidloc = new HashMap<String, Location>();
	private HashMap<String, UUID> raidpor = new HashMap<String, UUID>();
	private HashMap<String, Integer> raidt = new HashMap<String, Integer>();
	private HashMap<String, BossBar> raidbar = new HashMap<String, BossBar>();
	private HashMap<String, Integer> raidbart = new HashMap<String, Integer>();
	private HashMap<String, Integer> leadert = new HashMap<String, Integer>();
	
	private HashMap<String, Integer> tart = new HashMap<String, Integer>();
	private HashMap<String, Integer> tardt = new HashMap<String, Integer>();
	private HashMap<String, Integer> ptart = new HashMap<String, Integer>();
	
	private HashMap<String, Integer> lives = new HashMap<String, Integer>();
	private HashMap<String, UUID> vil = new HashMap<String, UUID>();
	private HashMap<UUID, Long> raidcool = new HashMap<UUID, Long>();
	private HashMap<String, Integer> timeout = new HashMap<String, Integer>();
	private HashMap<String, Integer> timet = new HashMap<String, Integer>();
	private HashMap<String, BossBar> timebar = new HashMap<String, BossBar>();


	public HashMap<String, Integer> difen = new HashMap<String, Integer>();
	private HashMap<String, Integer> difent = new HashMap<String, Integer>();

	private HashMap<String, UUID> inhibitor = new HashMap<String, UUID>();
	private HashMap<String, Integer> inhibitorhp = new HashMap<String, Integer>();


	public HashMap<String, String> language = new HashMap<String, String>();
	
	Integer DelayTime =  100;
	Integer LIVES = 5;
	final public Double BOSSHP = 2500000d;
	
	Integer BOSSNUM = -5;
	
	
	private static final WitherRaids instance = new WitherRaids ();
	public static WitherRaids getInstance()
	{
		return instance;
	}
	
	public static Collection<Player> getheroes(LivingEntity le)
	{
		if(le.hasMetadata("raid")) {
			String rn = le.getMetadata("raid").get(0).asString();
			Collection<Player> pc = new ArrayList<Player>();
			heroes.get(rn).forEach(pu -> pc.add(Bukkit.getPlayer(pu)));
			return pc;
		}
		else if(le.hasMetadata("witherRaidVil")) {
			String rn = le.getMetadata("witherRaidVil").get(0).asString();
			Collection<Player> pc = new ArrayList<Player>();
			heroes.get(rn).forEach(pu -> pc.add(Bukkit.getPlayer(pu)));
			return pc;
		}
		else{
			return null;
		}
	}

	public static Location getraidloc(LivingEntity le)
	{
		if(le.hasMetadata("raid")) {
			String rn = le.getMetadata("raid").get(0).asString();
			return raidloc.get(rn).clone();
		}
		else{
			return null;
		}
	}
	
	public final void WitherRaidFinish(String rn, String title, String sub, Integer factor) {
		RaidFinish(rn,title,sub,factor);

		Bukkit.getWorld("WitherRaid").getEntities().stream().filter(e -> e.hasMetadata("mirror"+rn)).forEach(e -> e.remove());
		Bukkit.getWorld("WitherRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());


		
		ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
		ordt.removeAll(rn);
		
		
		if(raidbart.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(raidbart.get(rn));
			raidbart.remove(rn);
		}
		if(raidt.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(raidt.get(rn));
			raidt.remove(rn);
		}
		if(timet.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(timet.get(rn));
			timet.remove(rn);
		}
		if(timebar.get(rn) != null) {
			timebar.get(rn).removeAll();
			timebar.get(rn).setVisible(false);
		}

		if(raidbar.get(rn) != null) {
			raidbar.get(rn).removeAll();
			raidbar.get(rn).setVisible(false);
		}
		if(raider.containsKey(rn)) {
			raider.get(rn).forEach(re -> {
				if(Bukkit.getEntity(re) != null) {
					Bukkit.getEntity(re).remove();
	    		}
			});
			raider.removeAll(rn);
		}
		
		if(leadert.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(leadert.get(rn));
			leadert.remove(rn);
		}
		
		if(tart.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(tart.get(rn));
			tart.remove(rn);
		}
		if(ptart.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(ptart.get(rn));
			ptart.remove(rn);
		}
		if(tardt.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(tardt.get(rn));
			tardt.remove(rn);
		}
		if(vil.containsKey(rn)) {
			if(Bukkit.getEntity(vil.get(rn)) !=null) {
				Bukkit.getEntity(vil.get(rn)).remove();
			}
		}

		if(inhibitor.containsKey(rn)) {
			if(Bukkit.getEntity(inhibitor.get(rn)) !=null) {
				Bukkit.getEntity(inhibitor.get(rn)).remove();
			}
		}

		if(inhibitorhp.containsKey(rn)) {
			inhibitorhp.remove(rn);
		}
		
		language.remove(rn);
		
		vil.remove(rn);
		lives.remove(rn);
		timeout.remove(rn);
		if(raidpor.containsKey(rn)) {
			if(Bukkit.getEntity(raidpor.get(rn)) !=null) {
				Bukkit.getEntity(raidpor.get(rn)).remove();
			}
		}

		if(heroes.containsKey(rn)) {

	    	heroes.get(rn).forEach(pu -> {
	    		Player p = Bukkit.getPlayer(pu);
	    		Location spl = p.getLocation();
	    		if(raidloc.containsKey(rn)) {
	        		spl = raidloc.get(rn).clone();
	    		}
	    		if(factor ==0) {
	        		p.sendTitle(ChatColor.BOLD +(ChatColor.DARK_GRAY + title), ChatColor.BOLD +sub, 5, 60, 5);
	        		p.playSound(spl, Sound.ENTITY_WITCH_AMBIENT, 1, 0);
	        		p.playSound(spl, Sound.ENTITY_RAVAGER_DEATH, 1, 0);
	            	p.spawnParticle(Particle.ANGRY_VILLAGER, spl, 1000,6,6,6);
	    		}
	    		else {
	    			if(RaidDifficulties.getMaxDifficulty(p, RaidCategory.ENDER) <= difen.get(rn)) {
	        			RaidDifficulties.saver(p, RaidCategory.ENDER, difen.get(rn)+2);
	    			}
	    			
	        		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "승리":"Victory!";
	        		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + reg), null, 5, 60, 5);
	        		p.playSound(spl, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
	        		p.playSound(spl, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
	            	spl.getWorld().spawn(spl, Firework.class);
	            	raidcool.put(pu, System.currentTimeMillis());
	            	
	            	Elements.give(Elements.getel(-8,p), 6*(int)(1+ 0.05*difen.get(rn)*(1 - 0.1*heroes.get(rn).size())), p);
	            	p.spawnParticle(Particle.COMPOSTER, spl, 1000,6,6,6);
	            	p.spawnParticle(Particle.HEART, spl, 1000,6,6,6);
	            	
	    		}
	            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	        			Holding.ale(p).teleport(beforepl.get(p.getUniqueId()));
						beforepl.remove(p.getUniqueId());
						raidbart.remove(rn);
	                }
	            }, 160); 
	    	});
			heroes.removeAll(rn);

			difen.remove(rn);
		}
	}
	

	final private void targeting(String rn) {


		final Location rl = raidloc.get(rn).clone();
		if(tart.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(tart.get(rn));
			tart.remove(rn);
		}
		int tt = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	heroes.get(rn).forEach(pu -> {
            		Player p = Bukkit.getPlayer(pu);
        			if(p.getWorld().equals(rl.getWorld()) && (p.getLocation().clone().distance(rl) >90 ||p.getLocation().getY()<-100)) {
        				p.teleport(rl.clone().add(1, 1, 0));
        				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.sendMessage("너무 멀리왔습니다");
        				}
        				else {
							p.sendMessage("Too Far");
        				}
        			}
            	});
        		raider.get(rn).forEach(re -> {
        			Mob rm = (Mob)Bukkit.getEntity(re);
        			if(rm!=null) {
        				if(vil.containsKey(rn)) {
                			rm.setTarget((LivingEntity) Bukkit.getEntity(vil.get(rn)));
        				}
            			Location rml = rm.getLocation().clone();
            			rml.setY(rl.getY());
            			if(rml.getWorld()!=rl.getWorld() || rml.clone().distance(rl) >80 || rml.getY()<-100) {
            				rm.teleport(rl.clone().add(1, 1, 0));
            			}
        			}
        		});
            }
		}, 0, 1);
		tart.put(rn, tt);
	}
	
	final private void bossbargen(String name, String rn, LivingEntity newmob) {

		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +name+"ow"),newmob.getName(), BarColor.WHITE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
        newbar.setVisible(true);
		raidbar.put(rn, newbar);
        newbar.setVisible(true);
        
		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {

            	
				if(Holding.ale(newmob)!=null) {
					final double pr = Holding.ale(newmob).getHealth()/Holding.ale(newmob).getAttribute(Attribute.MAX_HEALTH).getValue();
					if(pr>=0 && pr<=1) {
	                	raidbar.get(rn).setProgress(pr);
					}
					else if(pr>1){
	                	raidbar.get(rn).setProgress(1d);
					}
                	raidbar.get(rn).setTitle(Holding.ale(newmob).getName());
            		heroes.get(rn).forEach(pu -> {
        				Player p = (Player) Bukkit.getPlayer(pu);
        				raidbar.get(rn).addPlayer(p);
        				
        				p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,20,0,false,false));
        				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,20,0,false,false));
        				p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,20,0,false,false));
        				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,20,0,false,false));
            		});
				}
            }
		}, 0, 1);
		raidbart.put(rn, task);
	}


	
	
	private Integer getPhase(LivingEntity p) {
		if(!p.hasMetadata("ruined")) {
			if(p.getHealth()>= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.5) {
				return 1;
			}
			else {
				return 2;
			}
		}
		return 3;
	}
	

	
	final private ItemStack bloodTrim2(ItemStack eq, TrimPattern pat) {
		ItemStack pe = eq.clone();

		ArmorMeta arm = (ArmorMeta) pe.getItemMeta();
		ArmorTrim t1 = new ArmorTrim(TrimMaterial.NETHERITE, pat);
		arm.setTrim(t1);
		pe.setItemMeta(arm);
		return pe;
	}

	private ItemStack createArmor(Material material) {
	    ItemStack armor = new ItemStack(material);
	    return armor;
	}
	private void witherThrone(LivingEntity p, Location center, String rn) {
	    Location throneLocation = p.getLocation();
	    World world = throneLocation.getWorld();
	    if (world == null) return;

	    // 중심을 기준으로 방향 계산
	    Vector direction = center.toVector().subtract(throneLocation.toVector()).normalize();
	    double scale = 2.5;

	    // 기본 거리 계산 (왕좌의 구성 요소 위치)
	    Vector baseOffset = direction.clone().multiply(0.5); // Base는 중심에서 0.5m 거리
	    Vector seatOffset = direction.clone().multiply(0.5).add(new Vector(0, scale * 0.5, 0)); // Seat는 위로 높임
	    Vector backrestOffset = direction.clone().multiply(0.2).add(new Vector(0, scale, -0.5 * scale)); // Backrest는 뒤쪽으로

	    // Base BlockDisplay
	    Location baseLocation = throneLocation.clone().add(baseOffset);
	    BlockDisplay base = (BlockDisplay) world.spawn(baseLocation, BlockDisplay.class);
	    base.setBlock(getBd(Material.QUARTZ_BRICKS));
	    base.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	    base.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
	    base.setMetadata("throne" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
	    setDisplayScale(base, scale);

	    // Seat BlockDisplay
	    Location seatLocation = throneLocation.clone().add(seatOffset);
	    BlockDisplay seat = (BlockDisplay) world.spawn(seatLocation, BlockDisplay.class);
	    seat.setBlock(Material.QUARTZ_STAIRS.createBlockData(blockData -> {
	        if (blockData instanceof Stairs stairs) {
	            stairs.setShape(Stairs.Shape.STRAIGHT);
	            stairs.setFacing(BlockFace.NORTH);
	        }
	    }));
	    seat.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	    seat.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
	    seat.setMetadata("throne" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
	    setDisplayScale(seat, scale);
	    seat.addPassenger(p);

	    // Backrest BlockDisplay
	    Location backrestLocation = throneLocation.clone().add(backrestOffset);
	    BlockDisplay backrest = (BlockDisplay) world.spawn(backrestLocation, BlockDisplay.class);
	    backrest.setBlock(getBd(Material.QUARTZ_PILLAR));
	    backrest.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	    backrest.setMetadata("stuff" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
	    backrest.setMetadata("throne" + rn, new FixedMetadataValue(RMain.getInstance(), rn));
	    setDisplayScale(backrest, scale);

	    // 각 BlockDisplay의 방향 설정
	    float yaw = (float) Math.toDegrees(Math.atan2(direction.getZ(), direction.getX())) - 90;
	    base.setRotation(yaw, 0);
	    seat.setRotation(yaw, 0);
	    backrest.setRotation(yaw, 0);

	    // 물리적 상호작용 차단
	    base.setGravity(false);
	    seat.setGravity(false);
	    backrest.setGravity(false);

	    base.setPersistent(false);
	    seat.setPersistent(false);
	    backrest.setPersistent(false);
	}

	@EventHandler
	public void throne(VehicleExitEvent d) 
	{
		if(d.getExited().hasMetadata("witherboss")) {
			LivingEntity p = d.getExited();
			if(p.getHealth()>= p.getAttribute(Attribute.MAX_HEALTH).getValue()*0.5) {
				d.setCancelled(true);
			}
		}
	}

	// BlockDisplay의 크기를 설정하는 헬퍼 메서드
	private void setDisplayScale(BlockDisplay display, double scale) {
	    // 스케일 매트릭스 생성
	    Transformation transformation = new Transformation(
	        new Vector3f(0, 0, 0), // 위치 오프셋
            new Quaternionf(),
	        new Vector3f((float) scale, (float) scale, (float) scale), // 스케일
            new Quaternionf()
	    );
	    display.setTransformation(transformation);
	}


	
	final public LivingEntity bossgen(Location spl, Player pm, String rn, Integer in, Double dif) {

		
    	Random random=new Random();
    	double number = 3 * (random.nextBoolean() ? -1 : 1);
    	double number2 = 3 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 1.5, number2);
    	for(;!esl.getBlock().isPassable(); esl.add(1, 0, 0));
    	
		targeting(rn);
		double armor = 1;
		double dif1 = dif;
		
		final Double maxh = Bukkit.spigot().getConfig().getDouble("settings.attribute.maxHealth.max");
		if(dif1 >= maxh) {
			dif1 = maxh;
			armor = (dif-maxh)/BOSSHP;
		}
		
		
		//Double dif = 100000 * BigDecimal.valueOf(1 + 0.1*RaidDifficulties.getPlayerDifficulty(p, RaidCategory.ENDER)).setScale(1, RoundingMode.HALF_EVEN).doubleValue();

		
		if(in == -8) {
			ItemStack main = new ItemStack(Material.BOW);
			ItemMeta mm = main.getItemMeta();
			mm.setCustomModelData(4008);
			main.setItemMeta(mm);

		    ItemStack hel = createArmor(Material.NETHERITE_HELMET);
		    ItemStack chest = createArmor(Material.NETHERITE_CHESTPLATE);
		    ItemStack leg = createArmor(Material.NETHERITE_LEGGINGS);
		    ItemStack boots = createArmor(Material.NETHERITE_BOOTS);
    		String reg = pm.getLocale().equalsIgnoreCase("ko_kr") ? "[더 위더]":"[The Wither]";
    		WitherSkeleton newmob = (WitherSkeleton) phaseChange(esl, ChatColor.GRAY + reg, dif1, bloodTrim2(hel, TrimPattern.DUNE),
    				bloodTrim2(chest, TrimPattern.DUNE), bloodTrim2(leg,TrimPattern.DUNE), bloodTrim2(boots,TrimPattern.DUNE), main, null, EntityType.WITHER_SKELETON);
			newmob.setGlowing(true);
    		newmob.setLootTable(null);
    		newmob.setItemInUseTicks(5);

    		newmob.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.36);
    		newmob.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(1);
    		newmob.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(4);
    		newmob.getAttribute(Attribute.SCALE).setBaseValue(2.5);
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
			newmob.setMetadata("witherboss", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("wither", new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), armor));
			newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
			
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		witherThrone(newmob, spl, rn);
    		
    		bossbargen("witherboss", rn, newmob);
    		

    		final Object ht = getherotype(rn);

    		ItemStack mainf = new ItemStack(Material.BLAZE_ROD);
    		ItemMeta mmf = mainf.getItemMeta();
    		mmf.setCustomModelData(3008);
    		mainf.setItemMeta(mmf);

    		Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
    		    @Override
    		    public void run() {
    				if(ht instanceof Player) {
    					Player p = (Player) ht;
    					p.sendEquipmentChange(newmob, EquipmentSlot.HAND, mainf);
    				}
    				else if(getherotype(rn) instanceof HashSet){
    					@SuppressWarnings("unchecked")
    					HashSet<Player> par = (HashSet<Player>) ht;
    		    		par.forEach(p -> {
    		    			p.sendEquipmentChange(newmob, EquipmentSlot.HAND, mainf);
    		    		});
    				}
    		    }
    		}, 2L); 

    		return newmob;
		}
		return pm;
		
	}
	
	final private void bossphase2(LivingEntity le) {
		String rn = le.getMetadata("raid").get(0).asString();
		raider.remove(rn, le.getUniqueId());
		if(raidbart.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(raidbart.get(rn));
			raidbart.remove(rn);
		}
		if(raidt.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(raidt.get(rn));
			raidt.remove(rn);
		}
		BossBar	newbar = raidbar.get(rn);
		newbar.setVisible(false);
		if(leadert.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(leadert.get(rn));
			leadert.remove(rn);
		}
		if(tart.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(tart.get(rn));
			tart.remove(rn);
		}
    	heroes.get(rn).forEach(pu -> {
    		Bukkit.getPlayer(pu).sendTitle(ChatColor.MAGIC + "aaaaaa",ChatColor.MAGIC + "aaaaaa", 5, 20, 5);
    	});
        
		return;
	}

	
	
	@EventHandler
	public void Escape(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();
		if(heroes.containsValue(p.getUniqueId())) {
	
			if(Party.hasParty(p)) {
				if(Party.isOwner(p)) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						WitherRaidFinish(getheroname(p), "탈주","파티장이 떠났습니다",0);
					}
					else {
						WitherRaidFinish(getheroname(p), "Escaped","Party Owner Left",0);
					}
				}
				else {
	        		heroes.remove(Party.getOwner(Party.getParty(p)).getName(), p.getUniqueId());
	        		p.teleport(beforepl.get(p.getUniqueId()));
	    			beforepl.remove(p.getUniqueId());
	    			return;
				}
			}
			else {
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					WitherRaidFinish(getheroname(p), "탈주","파티장이 떠났습니다",0);
				}
				else {
					WitherRaidFinish(getheroname(p), "Escaped","Party Owner Left",0);
				}
			}
			
		}
	}
	

	final private void playerTP(Player p, Location spl, String rn) {
		final Location pl = p.getLocation();
		beforepl.put(p.getUniqueId(), pl);
		Holding.invur(p, 100l);
		int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
        		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20,1,false,false));
        		try {
            		p.teleport(spl.clone().add(0,0.5,0));
        		}
        		catch(Exception e) {
        			Holding.ale(p).setPersistent(true);
        			Holding.ale(p).setRemoveWhenFarAway(false);
        			Holding.ale(p).teleport(spl.clone().add(0,0.5,0));
        		}
            }
		}, 25); 
		ordt.put(rn, task);
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void WitherRaidStart(PlayerInteractEvent d)
	{
		if(d.getClickedBlock() != null && d.getClickedBlock().hasMetadata("WitherRaidPortal") && d.getAction() == Action.RIGHT_CLICK_BLOCK && d.getAction() != Action.LEFT_CLICK_BLOCK) {

			Player p = (Player) d.getPlayer();
			if(!p.getInventory().getItemInMainHand().getType().isAir() || heroes.containsValue(p.getUniqueId())|| raider.containsKey(getheroname(p)) || beforepl.containsKey(p.getUniqueId()) || p.hasCooldown(Material.RAIL)) {
				return;
			}
			if(raidcool.containsKey(p.getUniqueId())) {

	            double timer = (raidcool.get(p.getUniqueId())/1000 + 5) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("다음 보스 침공작전 입장까지 " + timer + "초 기다려야 됩니다").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("You have to wait for " + timer + " seconds to Start Next Boss Raid").create());
					}
	            	return;
	            }
	            else { 
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("이제 보스 침공작전에 입장하실수 있습니다").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("Now You Can Start Boss Raid").create());
					}
	            	raidcool.remove(p.getUniqueId());
	            }
			}
    		String rn = d.getClickedBlock().getMetadata("WitherRaidPortal").get(0).asString();
    		World rw = Bukkit.getServer().getWorld("WitherRaid");
    		int fix = p.getEntityId()*100-29999984;
    		int fiz = p.getEntityId()*100-29999984;
    		if(fix >= 29999984) {
    			fix = fix - 29999984*2;
    			fiz = 29999984*2 - fiz;
    		}
        	p.setCooldown(Material.RAIL, 100);
    		Location spl = rw.getHighestBlockAt(fix, fiz).getLocation().clone().add(0, 1, 0);
    		if(Party.hasParty(p)) {
				if(Party.isOwner(p)) {
					Party.getMembers(Party.getParty(p)).forEach(pu -> {
						if(Bukkit.getPlayer(pu).getInventory().firstEmpty() == -1) {
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("인벤토리에 빈칸이 없는 파티원이 있습니다").create());
							}
							else {
				            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("Players should empty inventory least one space").create());
							}
							return;
						}
		        		heroes.put(rn, pu);
		        		
						playerTP(Bukkit.getPlayer(pu),spl,rn);
					});
				}
				else {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage("파티장만 가능합니다");
					}
					else {
						p.sendMessage("You Should Be Owner");
					}
					return;
				}
			}
			else {
				if(p.getInventory().firstEmpty() == -1) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("인벤토리에 빈칸이 없습니다").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("You should empty inventory least one space").create());
					}
					return;
				}
        		heroes.put(rn, p.getUniqueId());
        		
        		playerTP(p,spl,rn);
			}
    		RaidFinish(rn,"","",0);
			d.getClickedBlock().setType(Material.VOID_AIR);
    		ArmorStand portal = (ArmorStand) spl.getWorld().spawn(spl, ArmorStand.class);
    		portal.setMetadata("portal", new FixedMetadataValue(RMain.getInstance(), true));
    		portal.setMetadata("WitherRaidExit", new FixedMetadataValue(RMain.getInstance(), true));
    		portal.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		portal.setRemoveWhenFarAway(false);
    		portal.setGlowing(true);
    		portal.setGravity(false);
    		portal.getEquipment().setHelmet(new ItemStack(Material.OBSIDIAN));
    		portal.setCollidable(false);
    		portal.getAttribute(Attribute.SCALE).setBaseValue(3);
    		
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		portal.setCustomName(rn + "파티의 출구 (웅크린상태에서 맨손으로 가격)");
        		language.put(rn, p.getLocale());
			}
			else {
        		portal.setCustomName(rn + "'s Party Exit (Sneaking + Hit with Fist)");
        		language.put(rn, p.getLocale());
			}
    		portal.setCustomNameVisible(true);
    		portal.setCollidable(false);
    		raidloc.put(rn, spl);
    		raidpor.put(rn, portal.getUniqueId());

    		Villager v = (Villager) spl.getWorld().spawn(spl.clone().add(3,1,3), Villager.class);
    		v.setMetadata("witherRaidVil", new FixedMetadataValue(RMain.getInstance(), rn));
    		v.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
    		v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
    		v.setAdult();
    		v.setAgeLock(true);
    		v.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0);
    		v.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(1);
    		v.getAttribute(Attribute.GRAVITY).setBaseValue(10000);
    		v.getAttribute(Attribute.SCALE).setBaseValue(0.75);
    		v.setGravity(true);
    		v.setNoDamageTicks(0);
    		v.setMaxHealth(100000);
    		v.setHealth(100000);
    		v.setRemoveWhenFarAway(false);
    		v.setGlowing(true);
    		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? rn + " 차원탐험가":rn + "'s Dimension Travler";
    		v.setVillagerType(Type.SAVANNA);
    		v.setProfession(Profession.CARTOGRAPHER);
    		v.setCustomName(reg);
    		v.setCustomNameVisible(true);
    		timeout.put(rn, 420);
    		vil.put(rn, v.getUniqueId());
    		
    		
    		if(Party.hasParty(p)) {
				if(Party.isOwner(p)) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "난이도를 입력하세요 (최소: 0, 최대: "+RaidDifficulties.getMaxDifficulty(p, RaidCategory.ENDER)+")").create());
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "5초안에 미입력 또는 올바른 입력이 아닐 시 도전 가능한 가장 높은 난이도로 자동 설정됩니다").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "Enter difficulty level (min: 0, MAX: "+RaidDifficulties.getMaxDifficulty(p, RaidCategory.ENDER)+")").create());
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "If you do not enter anything in 5 second or enter invalid inputs").create());
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "It will be automatically set to the highest level of difficulty you can challenge").create());

					}
					difen.put(rn, RaidDifficulties.getMaxDifficulty(p, RaidCategory.ENDER));
					int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			            @Override
			            public void run() 
			            {
			            	WitherRaidStart(p,rn,difen.get(rn));
			            	difent.remove(rn);
			            }
					}, 160); 
					difent.put(rn, task);

					Party.getMembers(Party.getParty(p)).forEach(pu -> {
						if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
							Bukkit.getPlayer(pu).spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "파티장이 난이도를 선택중입니다").create());
						}
						else {
							Bukkit.getPlayer(pu).spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "Owner is selecting difficulty now").create());
						}
					});
				}
			}
			else {
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "난이도를 입력하세요 (최소: 0, 최대: "+RaidDifficulties.getMaxDifficulty(p, RaidCategory.ENDER)+")").create());
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "5초안에 미입력 또는 올바른 입력이 아닐 시 도전 가능한 가장 높은 난이도로 자동 설정됩니다").create());
				}
				else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "Enter difficulty level (min: 0, MAX: "+RaidDifficulties.getMaxDifficulty(p, RaidCategory.ENDER)+")").create());
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "If you do not enter anything in 5 second or enter invalid inputs").create());
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "It will be automatically set to the highest level of difficulty you can challenge").create());

				}
				difen.put(rn, RaidDifficulties.getMaxDifficulty(p, RaidCategory.ENDER));
				int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		            @Override
		            public void run() 
		            {
		            	WitherRaidStart(p,rn,difen.get(rn));
		            	difent.remove(rn);
		            }
				}, 160); 
				difent.put(rn, task);
			}
    		
		}
	}
	
	final private void WitherRaidStart(Player p, String rn, Integer endif) {

		
		final Location spl = raidloc.get(rn).clone();

		if(Party.hasParty(p)) {
			if(Party.isOwner(p)) {
				Party.getMembers(Party.getParty(p)).forEach(pu -> {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        		Bukkit.getPlayer(pu).sendMessage(ChatColor.AQUA + "난이도가 "+endif+"로 설정 되었습니다");
					}
					else {
		        		Bukkit.getPlayer(pu).sendMessage(ChatColor.AQUA + "Difficulty level set to "+endif);
					}
				});
			}
		}
		else {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		p.sendMessage(ChatColor.AQUA + "난이도가 "+endif+"로 설정 되었습니다");
			}
			else {
        		p.sendMessage(ChatColor.AQUA + "Difficulty level set to "+endif);
			}
		}
		
		Double dif = BOSSHP * BigDecimal.valueOf(1 + 0.1*endif).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
		if(Party.hasParty(p)) {
			if(Party.isOwner(p)) {
				Party.getMembers(Party.getParty(p)).forEach(pu -> {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GRAY + "작전이 시작됩니다", "주민을 보호하고 적을 모두 섬멸하십시오", 5, 69, 5);
					}
					else {
		        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GRAY + "Raid Will Start", "Protect Villager & Sweep All Enemies", 5, 69, 5);
					}
				});
			}
		}
		else {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		p.sendTitle(ChatColor.GRAY + "작전이 시작됩니다", "주민을 보호하고 적을 모두 섬멸하십시오", 5, 69, 5);
			}
			else {
        		p.sendTitle(ChatColor.GRAY + "Raid Will Start", "Protect Villager & Sweep All Enemies", 5, 69, 5);
			}
		}
		lives.put(rn, LIVES+heroes.get(rn).size());
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() {

	                int rat = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

		                	Random random=new Random();
		                	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
		                	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
		                	Location esl = spl.clone().add(number, 1, number2);
	                    	
		                	
	                    	int ri = -8;
	                    	if(BOSSNUM<0) {
		                    	ri=BOSSNUM;
	                    	}
	                    	bossgen(esl,p, rn, ri,dif);
	                    	
		                	heroes.get(rn).forEach(pu -> {
		                		Player pa = Bukkit.getPlayer(pu);
								if(pa.getLocale().equalsIgnoreCase("ko_kr")) {
    		                		pa.sendTitle(ChatColor.GRAY + "작전 시작", null, 5, 69, 5);
    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
    		                		pa.sendMessage("남은 목숨: " +ChatColor.BOLD + String.valueOf(lives.get(rn)));
								}
								else {
    		                		pa.sendTitle(ChatColor.GRAY + "Raid Start", null, 5, 69, 5);
    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
    		                		pa.sendMessage(ChatColor.BOLD + String.valueOf(lives.get(rn)) + "lives Left");
								}
		                	});

	        	    		targeting(rn);

		        			String t = p.getLocale().equalsIgnoreCase("ko_kr") ? "남은시간 - ":"TimeLeft - ";
	        	    		BossBar	timeb = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn+"WitherRaidTime"),t + String.valueOf((int)timeout.get(rn)/20/60) + ":" + String.valueOf((int)(timeout.get(rn)/20)%60), BarColor.WHITE, BarStyle.SEGMENTED_6);
	        	    		timeb.setVisible(true);
	        	    		timebar.put(rn, timeb);
	        	    		int timetask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	        	                @Override
	        	                public void run() 
	        	                {
	        	            		timebar.get(rn).setProgress((double)timeout.get(rn)/420d);
	        	            		timebar.get(rn).setTitle(t +"[" + String.valueOf((int)timeout.get(rn)/60) + ":" + String.valueOf((int)(timeout.get(rn))%60)+"]");
	        	            		heroes.get(rn).forEach(pu -> {
	    	            				Player p1 = (Player) Bukkit.getPlayer(pu);
	    	            				if(p1 != null) {
		    	            				timebar.get(rn).addPlayer(p1);
	    	            				}
	        	            		});
	        	            		timeout.computeIfPresent(rn, (k,v) -> v-1);
	        	            		if(timeout.get(rn) <=0) {

	    								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    	        	            			WitherRaidFinish(rn,"패배..", "시간초과",0);
	    								}
	    								else {
    	        	            			WitherRaidFinish(rn,"Defeated..", "TimeOut",0);
	    								}
	        	                    	
	        	                    	spl.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, spl, 1000,6,6,6);
	        	            		}
	        	                }
	        				}, 0, 20);
	        	    		timet.put(rn, timetask);
		                }
		            }, DelayTime); 
	                raidt.put(rn, rat);
            }
		},5);
        return;
	
	}
	
	
	@EventHandler
	public void WitherRaidStart(AsyncPlayerChatEvent d) 
	{	
			Player p = (Player) d.getPlayer();

			final String rn = getheroname(p);

			if(!difent.containsKey(rn)) {
				return;
			}
			else {
				Bukkit.getScheduler().cancelTask(difent.remove(rn));
			}
			Integer endif = 0;
			d.setCancelled(true);
			try {
				endif = Integer.parseInt(d.getMessage());
				if(endif > RaidDifficulties.getMaxDifficulty(p,RaidCategory.ENDER)) {
					endif = RaidDifficulties.getMaxDifficulty(p,RaidCategory.ENDER);
				}
			}
			catch(NumberFormatException e) {
				endif = RaidDifficulties.getMaxDifficulty(p,RaidCategory.ENDER);
			}

			difen.put(rn, endif);
			WitherRaidStart(p,rn,endif);
		
	}

	@EventHandler
	public void Victory(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("finalboss") && raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			Location lel = le.getLocation();
        	for(int i =0; i<30; i++) {
         	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {

		        			le.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, lel, 300,1,1,1);
		        			le.getWorld().spawnParticle(Particle.CRIMSON_SPORE, lel, 100,1,1,1);
		        			le.getWorld().spawnParticle(Particle.REVERSE_PORTAL, lel, 300,1,1,1);
		        			le.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, lel, 300,1,1,1);
		        			le.getWorld().spawnParticle(Particle.FLAME, lel, 100,1,1,1);
		        			le.getWorld().spawnParticle(Particle.SOUL, lel, 100,1,1,1);
		                }
         	   }, i*3); 
			}
			String rn = le.getMetadata("raid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
	        	WitherRaidFinish(rn, "", "",1);
			}
		}
	}




	@EventHandler
	public void Defeat(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("witherRaidVil")) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("witherRaidVil").get(0).asString();

			if(language.get(rn).equalsIgnoreCase("ko_kr")) {
				WitherRaidFinish(rn, "패배..", "주민 보호 실패",0);
			}
			else {
				WitherRaidFinish(rn, "Defeated..", "Fail to Protect the Villager",0);
			}
		}
	}
	

	@EventHandler
	public void Defeat(PlayerDeathEvent ev) 
	{
		Player dp = ev.getEntity();
		if(heroes.containsValue(dp.getUniqueId())) {
			String rn = heroes.keySet().stream().filter(k -> heroes.containsEntry(k, dp.getUniqueId())).findFirst().get();
			lives.computeIfPresent(rn, (k,v) -> v-1);
        	heroes.get(rn).forEach(pu -> {
        		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)) + "lives Left");
        	});
        	if (lives.computeIfAbsent(rn, key -> 1) <= 0) {
        	    WitherRaidFinish(rn, 
        	        language.getOrDefault(rn, "en_us").equalsIgnoreCase("ko_kr") ? "패배.." : "Defeated..", 
        	        language.getOrDefault(rn, "en_us").equalsIgnoreCase("ko_kr") ? "모든 목숨 소진" : "All Lives Exhausted", 
        	        0
        	    );
        	}
		}
	}

	@EventHandler
	public void WitherRaidExit(EntityDamageByEntityEvent d) 
	{	
		if(d.getEntity().hasMetadata("WitherRaidExit")) {
			d.setCancelled(true);
			if(d.getDamager() instanceof Player) {
	
				Player p = (Player) d.getDamager();
				if(!heroes.containsValue(p.getUniqueId()) || !p.getInventory().getItemInMainHand().getType().isAir()) {
					return;
				}
				if(p.isSneaking() && !p.isSprinting()) {
					if(Party.hasParty(p)) {
						if(Party.isOwner(p)) {
			    			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								WitherRaidFinish(getheroname(p), "항복","",0);
			    			}
			    			else {
								WitherRaidFinish(getheroname(p), "Surrwither","",0);
			    			}
						}
						else {
			    			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.sendMessage("파티장만 가능합니다");
			    			}
			    			else {
								p.sendMessage("You Should Be Owner");
			    			}
							return;
						}
					}
					else {
		    			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							WitherRaidFinish(getheroname(p), "항복","",0);
		    			}
		    			else {
							WitherRaidFinish(getheroname(p), "Surrwither","",0);
		    			}
					}
					
				}
				return;
			}
		}
	}


	@EventHandler
	public void WitherRaidExit(PlayerJoinEvent ev) 
	{
		Player p = ev.getPlayer();

		try {
			if(!heroes.containsValue(p.getUniqueId()) && p.getWorld().getName().contains("WitherRaid")) {

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		            @Override
		            public void run() 
		            {
						Location l = Bukkit.getWorlds().stream().filter(w -> !w.hasMetadata("rpgraidworld") && w.getEnvironment() == Environment.NETHER).findAny().get().getSpawnLocation();
						p.teleport(l);
		            }
				}, 50); 
			}
		}
		catch(Exception e) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	    			p.getWorld().addEntity(p);
	    			Location l = Bukkit.getWorlds().stream().filter(w -> !w.hasMetadata("rpgraidworld") && w.getEnvironment() == Environment.NETHER).findAny().get().getSpawnLocation();
	    			p.teleport(l);
	            }
			}, 50); 
		}
	}


	@EventHandler
	public void WitherRaidExit(PluginEnableEvent ev) 
	{
		List<World> worlds = Bukkit.getServer().getWorlds();
		worlds.forEach(w -> w.getPlayers().forEach(b -> {
				Player p = (Player) b;
				if(!heroes.containsValue(p.getUniqueId()) && p.getWorld().getName().contains("WitherRaid")) {
					p.teleport(w.getSpawnLocation());
				}
		}));
	}

	@EventHandler
	public void WitherRaidExit(PluginDisableEvent ev) 
	{
		List<World> worlds = Bukkit.getServer().getWorlds();
		worlds.forEach(w -> w.getPlayers().forEach(b -> {
				Player p = (Player) b;
				if(beforepl.containsKey(p.getUniqueId())) {
					p.teleport(beforepl.get(p.getUniqueId()));
				}
		}));
		raider.clear();
		heroes.clear();
	}


	@EventHandler
	public void RaiderHeoresDam(EntityDamageByEntityEvent d) 
	{	
		if(d.getEntity().getWorld().hasMetadata("rpgraidworld")) {
			if(d.getEntity() instanceof Player &&  heroes.containsValue(d.getEntity().getUniqueId()) && d.getDamager().hasMetadata("raid")) {
				
				String rn1 = heroes.keySet().stream().filter(k -> heroes.containsEntry(k, d.getEntity().getUniqueId())).findFirst().get();
				String rn2 = d.getDamager().getMetadata("raid").get(0).asString();
				if(rn1 != rn2) {
					d.setCancelled(true);
				}
			}
			if(d.getDamager() instanceof Projectile &&  d.getEntity() instanceof Player &&  heroes.containsValue(d.getEntity().getUniqueId())) {

				Projectile pr = (Projectile) d.getDamager();
				if(pr.getShooter() instanceof LivingEntity ) {
					LivingEntity le = (LivingEntity) pr.getShooter();
					if(le.hasMetadata("raid")) {
						String rn1 = heroes.keySet().stream().filter(k -> heroes.containsEntry(k, d.getEntity().getUniqueId())).findFirst().get();
						String rn2 = le.getMetadata("raid").get(0).asString();
						if(rn1 != rn2) {
							d.setCancelled(true);
						}
					}
				}
			}
			if(d.getDamager() instanceof Player &&  heroes.containsValue(d.getDamager().getUniqueId()) && d.getEntity().hasMetadata("raid")) {
				
				String rn1 = heroes.keySet().stream().filter(k -> heroes.containsEntry(k, d.getDamager().getUniqueId())).findFirst().get();
				String rn2 = d.getEntity().getMetadata("raid").get(0).asString();
				if(rn1 != rn2) {
					d.setCancelled(true);
				}
			}
			if(d.getDamager() instanceof Projectile &&  d.getEntity().hasMetadata("raid")) {

				Projectile pr = (Projectile) d.getDamager();
				if(pr.getShooter() instanceof Player ) {
					Player p = (Player) pr.getShooter();
					if(heroes.containsValue(p.getUniqueId())) {
						String rn1 = heroes.keySet().stream().filter(k -> heroes.containsEntry(k, p.getUniqueId())).findFirst().get();
						String rn2 = d.getEntity().getMetadata("raid").get(0).asString();
						if(rn1 != rn2) {
							d.setCancelled(true);
						}
					}
				}
			}
			if(d.getDamager() instanceof Player &&  heroes.containsValue(d.getDamager().getUniqueId()) && d.getEntity().hasMetadata("witherRaidVil")) {
				d.setCancelled(true);
			}
			if(d.getDamager() instanceof Projectile &&  d.getEntity().hasMetadata("witherRaidVil")) {

				Projectile pr = (Projectile) d.getDamager();
				if(pr.getShooter() instanceof Player ) {
					Player p = (Player) pr.getShooter();
					if(heroes.containsValue(p.getUniqueId())) {
						d.setCancelled(true);
					}
					
				}
			}
		}
	}


	@EventHandler
	public void Targetchange(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("boss") && !d.isCancelled()) {

			String rn = d.getEntity().getMetadata("raid").get(0).asString();
			if(tart.containsKey(rn) && !ptart.containsKey(rn)) {

				if(d.getDamager() instanceof Player && heroes.containsValue(d.getDamager().getUniqueId())) {

					Player p = (Player) d.getDamager();
	        		if(tart.containsKey(rn)) {
	        			Bukkit.getServer().getScheduler().cancelTask(tart.get(rn));
	        			tart.remove(rn);
	        		}
	        		if(tardt.containsKey(rn)) {
	        			Bukkit.getServer().getScheduler().cancelTask(tardt.get(rn));
	        			tardt.remove(rn);
	        		}
		    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		            		raider.get(rn).forEach(re -> {
		            			Mob rm = (Mob)Bukkit.getEntity(re);
    	            			if(rm!=null) {
    	            				rm.setTarget(p);
    	            			}
		            		});
	            			if(p.getEquipment().getItemInMainHand().getType() == Material.AIR) {
	        	        		if(ptart.containsKey(rn)) {
	        	        			Bukkit.getServer().getScheduler().cancelTask(ptart.get(rn));
	        	        			ptart.remove(rn);
	        	        		}
	        	        		if(tardt.containsKey(rn)) {
	        	        			Bukkit.getServer().getScheduler().cancelTask(tardt.get(rn));
	        	        			tardt.remove(rn);
	        	        		}
	        	        		targeting(rn);
	            			}
		                }
					}, 0, 1);
		    		ptart.put(rn, task);
		            int del = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() {

        	        		if(ptart.containsKey(rn)) {
        	        			Bukkit.getServer().getScheduler().cancelTask(ptart.get(rn));
        	        			ptart.remove(rn);
        	        		}
        	        		targeting(rn);
		                }
		            }, 200); 
		            tardt.put(rn, del);
				}
				else if(d.getDamager() instanceof Projectile ) {

					Projectile pr = (Projectile) d.getDamager();
					if(pr.getShooter() instanceof Player ) {
						Player p = (Player) pr.getShooter();
						if(heroes.containsValue(p.getUniqueId())) {

			        		if(tart.containsKey(rn)) {
			        			Bukkit.getServer().getScheduler().cancelTask(tart.get(rn));
			        			tart.remove(rn);
			        		}
			        		if(tardt.containsKey(rn)) {
			        			Bukkit.getServer().getScheduler().cancelTask(tardt.get(rn));
			        			tardt.remove(rn);
			        		}
				    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
				            		raider.get(rn).forEach(re -> {
				            			Mob rm = (Mob)Bukkit.getEntity(re);
		    	            			if(rm!=null) {
		    	            				rm.setTarget(p);
		    	            			}
				            		});
			            			if(p.getEquipment().getItemInMainHand().getType() == Material.AIR) {
			        	        		if(ptart.containsKey(rn)) {
			        	        			Bukkit.getServer().getScheduler().cancelTask(ptart.get(rn));
			        	        			ptart.remove(rn);
			        	        		}
			        	        		if(tardt.containsKey(rn)) {
			        	        			Bukkit.getServer().getScheduler().cancelTask(tardt.get(rn));
			        	        			tardt.remove(rn);
			        	        		}
			        	        		targeting(rn);
			            			}
				                }
							}, 0, 1);
				    		ptart.put(rn, task);
				            int del = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() {

		        	        		if(ptart.containsKey(rn)) {
		        	        			Bukkit.getServer().getScheduler().cancelTask(ptart.get(rn));
		        	        			ptart.remove(rn);
		        	        		}
		        	        		targeting(rn);
				                }
				            }, 200); 
				            tardt.put(rn, del);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void RaidBack(PlayerRespawnEvent ev) 
	{
		Player p = ev.getPlayer();
		if(heroes.containsValue(p.getUniqueId())) {

			Holding.invur(p, 40l);
            Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
    				p.teleport(raidloc.get(getheroname(p)));
    				Holding.invur(p, 60l);
    				Holding.untouchable(p, 60l);
                }
            }, 25); 
		}
	}
	

	@EventHandler
	public void Teleport(PlayerTeleportEvent e)
	{
		Player p = e.getPlayer();
		if(e.getTo().getWorld().getName().contains("WitherRaid")) {
			if(!heroes.containsValue(p.getUniqueId())) {
				e.setCancelled(true);
			}
		}
		if(e.getFrom().getWorld().getName().contains("WitherRaid") && !e.getTo().getWorld().getName().contains("WitherRaid") && !e.getTo().getWorld().getName().contains("FakeDimension")) {
			if(heroes.containsValue(p.getUniqueId())) {
				e.setCancelled(true);
			}
		}
	}

	
	
	@EventHandler
	public void BlockPlace(BlockPlaceEvent d) 
	{
		Player p = d.getPlayer();
		if(heroes.containsValue(p.getUniqueId())|| raider.containsKey(getheroname(p)) || beforepl.containsKey(p.getUniqueId())) {
			d.setCancelled(true);
		}
	}

	@EventHandler
	public void BlockBreak(BlockBreakEvent d) 
	{
		Player p = d.getPlayer();
		if(heroes.containsValue(p.getUniqueId())|| raider.containsKey(getheroname(p)) || beforepl.containsKey(p.getUniqueId())) {
			d.setCancelled(true);
		}
	}
	

	@EventHandler
	public void EnderBoss2(EntityDeathEvent d) 
	{	

		if(d.getEntity().hasMetadata("bosswave1") && d.getEntity().hasMetadata("witherboss") && raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
				bossphase2(le);
				Location spl = raidloc.get(rn).clone();
	        	spl.getWorld().spawnParticle(Particle.PORTAL, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.REVERSE_PORTAL, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.END_ROD, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.SMOKE, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_SKELETON_CONVERTED_TO_STRAY, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 0.2f, 2);
	            int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {

	                	Location esl = d.getEntity().getLocation().clone().add(0,0.5, 0);

	            		String reg = language.get(rn).equalsIgnoreCase("ko_kr") ? "[더 위더: 트루폼]":"[The Wither: True Form]";
	            		Wither newmob = (Wither) phaseChange(esl, ChatColor.DARK_GRAY + reg, le.getAttribute(Attribute.MAX_HEALTH).getValue()*1.2,null,null,null,null,null,null, EntityType.WITHER);
	        			newmob.setGlowing(true);
	            		newmob.setLootTable(null);
	            		newmob.setItemInUseTicks(5);

	            		newmob.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(1);
	            		newmob.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(4);
	            			            		

	    	    		newmob.setGlowing(true);
	    	    		newmob.setMetadata("witherboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("ruined", new FixedMetadataValue(RMain.getInstance(), true));
	            		newmob.setMetadata("wither", new FixedMetadataValue(RMain.getInstance(), true));
	
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), le.getMetadata("boss").get(0).asDouble()));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setLootTable(null);
	    	    		
	    	    		
	    	    		newmob.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.SCALE).setBaseValue(1.1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		
	
	
	    	    		bossbargen("Distroyer", rn, newmob);
	    	    		
	    	    		targeting(rn);
	            		
	                	heroes.get(rn).forEach(pu -> {
							if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.DARK_PURPLE + "토벌작전 2단계"), null, 5, 69, 5);
				        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
					    		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + "남은 목숨 "+ String.valueOf(lives.getOrDefault(rn, 0)));
							}
							else {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.DARK_PURPLE + "PHASE 2"), null, 5, 69, 5);
				        		Bukkit.getPlayer(pu).playSound(spl, Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1, 1);
		                		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)) + "lives Left");
							}
	                	});
	                }
	            }, 50); 
	            raidt.put(rn, rat);
			}
		}
	}

}