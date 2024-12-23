package io.github.chw3021.commons;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.DragonBattle.RespawnPhase;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Armadillo;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragon.Phase;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityKnockbackEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Vector3f;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Classgui;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;


public class CommonEvents extends Mobs implements Listener{

	static public HashMap<UUID, String> damaged = new HashMap<UUID, String>();
	private Multimap<UUID, UUID> ind = ArrayListMultimap.create();

	static private HashMap<UUID, UUID> bar = new HashMap<UUID, UUID>();
	static private HashMap<UUID, Integer> trackt = new HashMap<UUID, Integer>();
	static private HashMap<UUID, Integer> bart = new HashMap<UUID, Integer>();


	Classgui Classgui = new Classgui();




	private static final CommonEvents instance = new CommonEvents ();
	public static CommonEvents getInstance()
	{
		return instance;
	}


	public void classinv(PlayerLocaleChangeEvent e)
	{
		Player p = e.getPlayer();

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run()
			{
				if(ClassData.pc.containsKey(p.getUniqueId())) {
					Classgui.LimitBreak(p);
				}
			}
		}, 1);

	}

	private static final ScoreboardManager manager = Bukkit.getScoreboardManager();


	@EventHandler
	public void mobKnockBackReducer(EntityKnockbackEvent d)
	{
		d.setFinalKnockback(d.getKnockback().multiply(0.25));
		Location lel = d.getEntity().getEyeLocation().clone();
		lel.getWorld().spawnParticle(Particle.DUST_PILLAR, lel, 6, 0.3,0.5,0.3, 0.5, getBd(Material.REDSTONE_BLOCK));
	}
	
	
	public void Thorndamcan(EntityDamageByEntityEvent d)
	{
		if(d.getCause() == DamageCause.THORNS && d.getDamager() == d.getEntity()) {
			d.setCancelled(true);
			return;
		}
	}

	final private void dinclickedremove(Entity re) {
		if(!bar.containsValue(re.getUniqueId())) {
			return;
		}
		ArmorStand din = (ArmorStand) re;
		UUID leud = bar.keySet().stream().filter(leu -> bar.get(leu)== re.getUniqueId()).findFirst().get();
		Bukkit.getScheduler().cancelTask(trackt.get(leud));
		Bukkit.getScheduler().cancelTask(bart.get(leud));
		if (din != null) {
			din.setCustomNameVisible(false);
			din.remove();
			bart.remove(leud);
			bar.remove(leud);
		}
	}
	@EventHandler
	public void leftClickTrigger(PlayerAnimationEvent d)
	{
		Player p = (Player) d.getPlayer();
		if(d.getAnimationType() == PlayerAnimationType.ARM_SWING && d.getAnimationType() != PlayerAnimationType.OFF_ARM_SWING) {
			Bukkit.getPluginManager().callEvent(new PlayerInteractEvent(p, Action.LEFT_CLICK_AIR, p.getEquipment().getItemInMainHand(), null, p.getFacing()));
		}
	}

	public void leftClickTrigger(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player){
			Player p = (Player) d.getDamager();
			if(!p.hasCooldown(Material.YELLOW_TERRACOTTA)) {
				Bukkit.getPluginManager().callEvent(new PlayerInteractEvent(p, Action.LEFT_CLICK_AIR, p.getEquipment().getItemInMainHand(), null, p.getFacing()));
			}
		}
	}

	public void namingAndBarRemove(PlayerInteractAtEntityEvent d)
	{
		final Entity e = d.getRightClicked();
		if(e.hasMetadata("fake")|| e.hasMetadata("din")) {
			d.setCancelled(true);
		}
		if(e instanceof ArmorStand && !e.hasGravity() && e.isInvulnerable() && !e.hasMetadata("fake")) {
			final Entity re = e;
			re.remove();
		}
		if(e.hasMetadata("fake") || e.hasMetadata("portal") || e.hasMetadata("din") || e instanceof Villager) {
			Player p = d.getPlayer();
			Bukkit.getPluginManager().callEvent(new PlayerInteractEvent(p, Action.RIGHT_CLICK_AIR, p.getEquipment().getItemInMainHand(), null, p.getFacing()));
		}
		if(d.getPlayer().getInventory().getItemInMainHand().getType() == Material.NAME_TAG && e.hasMetadata("rpgspawned")) {
			if(!e.hasMetadata("unmodified") || e.hasMetadata("fake") || e.hasMetadata("raid") || e.hasMetadata("boss") || e.hasMetadata("quest")){
				d.setCancelled(true);
				return;
			}
			if(damaged.containsKey(e.getUniqueId())) {
				damaged.remove(e.getUniqueId());
			}
		}
		if(d.getRightClicked().hasMetadata("din")) {
			final Entity re = d.getRightClicked();
			dinclickedremove(re);
			d.setCancelled(true);
			re.remove();
		}
	}

	public void namingAndBarRemove(PlayerInteractEntityEvent d)
	{
		final Entity e = d.getRightClicked();
		if(e.hasMetadata("fake")|| e.hasMetadata("din")) {
			d.setCancelled(true);
		}
		if(e.hasMetadata("fake") || e.hasMetadata("portal") || e.hasMetadata("din") || e instanceof Villager) {
			Player p = d.getPlayer();
			Bukkit.getPluginManager().callEvent(new PlayerInteractEvent(p, Action.RIGHT_CLICK_AIR, p.getEquipment().getItemInMainHand(), null, p.getFacing()));
		}
		if(d.getPlayer().getInventory().getItemInMainHand().getType() == Material.NAME_TAG && e.hasMetadata("rpgspawned")) {
			if(!e.hasMetadata("unmodified") || e.hasMetadata("fake") || e.hasMetadata("raid") || e.hasMetadata("boss") || e.hasMetadata("quest")){
				d.setCancelled(true);
				return;
			}
			if(damaged.containsKey(e.getUniqueId())) {
				damaged.remove(e.getUniqueId());
			}
		}
		if(d.getRightClicked().hasMetadata("din")) {
			final Entity re = d.getRightClicked();
			dinclickedremove(re);
			d.setCancelled(true);
			re.remove();
		}
	}


	public void Villdamcan(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && (d.getEntity().getType() == EntityType.VILLAGER ||  d.getEntity().getType() == EntityType.CAT ||  d.getEntity().getType() == EntityType.IRON_GOLEM) && !d.getEntity().hasMetadata("rpgspawned") && !d.getEntity().hasMetadata("fake"))
		{
			d.setCancelled(true);
		}
		if(d.getDamager() instanceof Projectile && (d.getEntity().getType() == EntityType.VILLAGER ||  d.getEntity().getType() == EntityType.CAT ||  d.getEntity().getType() == EntityType.IRON_GOLEM) && !d.getEntity().hasMetadata("rpgspawned") && !d.getEntity().hasMetadata("fake"))
		{
			Projectile p = (Projectile) d.getDamager();
			if(p.getShooter() instanceof Player) {
				d.setCancelled(true);
			}
		}
	}

	public void Teleport(PlayerTeleportEvent e)
	{
		Player p = (Player) e.getPlayer();
		p.setAI(false);
		p.setRemoveWhenFarAway(false);
	}

	final public Location BlankFinder(Location l) {

		HashSet<Location> lhs = new HashSet<>();
		Double dis = 7d;
		Location rl = l.clone();
		for(int ix = -5; ix<5; ix++) {
			for(int iy = -5; iy<5; iy++) {
				for(int iz = -5; iz<5; iz++) {
					lhs.add(l.clone().add(ix, iy, iz));
				}
			}
		}
		for(Location dl : lhs) {
			if(dis>=dl.distance(l) && dl.getBlock().isPassable()) {
				dis = dl.distance(l);
				rl = dl;
			}
		}
		return rl;
	}

	public void WallDamagecan(EntityDamageEvent d)
	{
		if(d.getEntity().hasMetadata("din")) {
			final Entity re = d.getEntity();
			dinclickedremove(re);
			re.remove();
		}
		if(d.getEntity() instanceof ArmorStand && !d.getEntity().hasGravity() && d.getEntity().isInvulnerable() && !d.getEntity().hasMetadata("fake")) {
			final Entity re = d.getEntity();
			re.remove();
		}
		if(d.getEntity() instanceof LivingEntity && (d.getCause() == DamageCause.SUFFOCATION))
		{
			LivingEntity le = (LivingEntity) d.getEntity();
			try {
				le.teleport(le.getLocation().clone().add(le.getVelocity().clone().multiply(-1.5)));
				le.setVelocity(le.getVelocity().clone().multiply(-0.5));
				le.teleport(BlankFinder(le.getLocation()));
				d.setCancelled(true);
			}
			catch(IllegalArgumentException ie) {
				if(le.getNearbyEntities(15, 15, 15).stream().filter(lel -> lel instanceof LivingEntity).findFirst().isPresent()) {
					LivingEntity ce = null;
					double dis = Double.MAX_VALUE;
					for (Entity t : le.getNearbyEntities(16, 16, 16)) {
						if(t instanceof LivingEntity) {
							LivingEntity let = (LivingEntity) t;
							double ed = t.getLocation().distance(le.getLocation());
							if (ed < dis) {
								dis = ed;
								ce = let;
							}
						}
					}
					le.teleport(ce);
					d.setCancelled(true);
				}
				else {
					le.teleport(BlankFinder(le.getLocation()));
					d.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void PVP(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof Player && !d.isCancelled() && !d.getEntity().hasMetadata("fake")) {
			d.setDamage(d.getDamage()*0.0025d);
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof Player && !d.isCancelled() && !d.getEntity().hasMetadata("fake")) {
			Projectile pr = (Projectile) d.getDamager();
			if(pr.getShooter() instanceof Player && !(pr instanceof Snowball)&& !(pr.hasMetadata("fake"))) {
				d.setDamage(d.getDamage()*0.0025d);
			}
		}
	}

	final private Location disloc(final Player p, final LivingEntity le, Location pl, Location elf) {

        double randomX = -0.5 + Math.random(); // -0.5 ~ 0.5
        double randomZ = -0.5 + Math.random(); // -0.5 ~ 0.5
		if(elf.getWorld() != pl.getWorld()) {
			if(p.hasPotionEffect(PotionEffectType.SLOWNESS)) {
				return pl.clone().add(pl.clone().getDirection().rotateAroundY(Math.PI/20).normalize().multiply(2.8)).add(randomX, 0.1, randomZ);
			}
			else {
				return pl.clone().add(pl.clone().getDirection().rotateAroundY(Math.PI/10).normalize().multiply(2.2)).add(randomX, 0.3, randomZ);
			}
		}
		final Double disd = elf.distance(pl);
		if(disd<12) {
			return elf.clone().add(le.getLocation().clone().getDirection().rotateAroundY(Math.PI/2).normalize().multiply(0.76)).add(randomX, 0.68, randomZ);
		}
		else {
			if(p.hasPotionEffect(PotionEffectType.SLOWNESS)) {
				return pl.clone().add(pl.clone().getDirection().rotateAroundY(Math.PI/20).normalize().multiply(2.8)).add(randomX, 0.1, randomZ);
			}
			else {
				return pl.clone().add(pl.clone().getDirection().rotateAroundY(Math.PI/10).normalize().multiply(2.2)).add(randomX, 0.3, randomZ);
			}
		}
		/*final double dis = (disd<12) ? (disd - 1.5*Math.log(disd)): 0;
		Location el = elf.clone().add(pl.clone().toVector().subtract(elf.clone().toVector()).normalize().multiply(0.2+dis)).clone();
		
		final Location pel = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().rotateAroundY(Math.PI/2).normalize().multiply(1.2));
		Vector dinv = pel.clone().toVector().subtract(el.clone().toVector()).normalize();
		Vector dinvv = dinv.clone();*/
		//return el.add(0, 1.1, 0).clone().add(dinvv.clone().multiply(0.1));
	}
	
	
	final private TextDisplay dinspawn(final Player p, Location l, Double d) {
	    // TextDisplay 엔티티 생성
	    final TextDisplay textDisplay = l.getWorld().spawn(l, TextDisplay.class);

	    // TextDisplay 기본 설정
	    textDisplay.setBillboard(Billboard.CENTER); // 텍스트가 플레이어를 따라오도록 설정
	    textDisplay.setViewRange(100); // 표시 거리 설정
	    textDisplay.setGlowing(true); // 글자에 발광 효과
	    textDisplay.setPersistent(false); // 임시 엔티티로 설정
	    textDisplay.setCustomNameVisible(false); // CustomName 사용 안 함
	    textDisplay.setInvulnerable(true); // 파괴되지 않도록 설정
	    textDisplay.setSeeThrough(true);

	    // 데미지에 따라 표시할 텍스트 설정
	    String damageText;
	    if (d < 0.1) {
	        damageText = ChatColor.AQUA + String.valueOf(Math.round(d * 1000) / 1000.000) + " [" + p.getName() + "]";
	    } else if (d >= 9999999) {
	        damageText = ChatColor.RED + "!9999999!" + " [" + p.getName() + "]";
	    } else {
	        damageText = ChatColor.AQUA + String.valueOf(Math.round(d * 10) / 10.0) + " [" + p.getName() + "]";
	    }
	    textDisplay.setText(damageText);

	    return textDisplay;
	}

	final private void damageind(final Player p, final LivingEntity le, Double d) {
		final Location elf = le.getLocation().clone().add(0, 0.05, 0);
		final Location pl = p.getLocation().clone().add(0, 0.1, 0);

	    // TextDisplay 생성
	    final TextDisplay din = dinspawn(p, disloc(p,le,pl,elf), d);

		ind.put(p.getUniqueId(), din.getUniqueId());
		if(ind.containsKey(p.getUniqueId()) && ind.get(p.getUniqueId()).size() > 5) {
			UUID enu = ind.get(p.getUniqueId()).stream().findFirst().get();
			if(Bukkit.getEntity(enu) != null) {
				Bukkit.getEntity(enu).remove();
			}
			ind.remove(p.getUniqueId(), enu);
		}

        // 현재 Transformation 가져오기
        Transformation currentTransform = din.getTransformation();

        // Y축으로 부드럽게 상승
        float newY = (float) (currentTransform.getTranslation().y() + 0.5); // 매 틱마다 0.05씩 상승
        Transformation updatedTransform = new Transformation(
            new Vector3f(currentTransform.getTranslation().x(), newY, currentTransform.getTranslation().z()), // 위치
            currentTransform.getLeftRotation(),
            currentTransform.getScale(),
            currentTransform.getRightRotation()
        );

        // 새 Transformation 적용
        din.setTransformation(updatedTransform);

        // 텔레포트 지속 시간 설정 (부드러운 이동)
        din.setInterpolationDuration(30); // 1틱(0.05초) 동안 부드럽게 이동

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				din.remove();
				ind.remove(p.getUniqueId(), din.getUniqueId());
			}
		}, 30);
	}

	
	/*
	final private ArmorStand dinspawn(final Player p,Location l, Double d) {

		final ArmorStand din = l.getWorld().spawn(l, ArmorStand.class, e -> e.setVisible(false));
		din.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 12,1,false,false));
		din.setMetadata("din", new FixedMetadataValue(RMain.getInstance(),true));
		din.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
		din.setMetadata("din of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
		din.setSilent(true);
		din.setSmall(true);
		din.setCollidable(false);
		din.setInvulnerable(true);
		if(d<0.1) {
			din.setCustomName( ChatColor.AQUA + String.valueOf(Math.round(d*1000)/1000.000) + " ["+ p.getName()+"]");
		}
		else if(d>=9999999) {
			din.setCustomName( ChatColor.RED + "!9999999!" + " ["+ p.getName()+"]");
		}
		else {
			din.setCustomName( ChatColor.AQUA + String.valueOf(Math.round(d*10)/10.0) + " ["+ p.getName()+"]");
		}
		din.setCustomNameVisible(true);
		return din;
	}

	final private void damageind(final Player p, final LivingEntity le, Double d) {
		final Location elf = le.getLocation().clone().add(0, 0.05, 0);
		final Location pl = p.getLocation().clone().add(0, 0.1, 0);
		
		final ArmorStand din = dinspawn(p, disloc(p,le,pl,elf), d);

		ind.put(p.getUniqueId(), din.getUniqueId());
		if(ind.containsKey(p.getUniqueId()) && ind.get(p.getUniqueId()).size() > 5) {
			UUID enu = ind.get(p.getUniqueId()).stream().findFirst().get();
			if(Bukkit.getEntity(enu) != null) {
				Bukkit.getEntity(enu).remove();
			}
			ind.remove(p.getUniqueId(), enu);
		}


		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				din.remove();
				ind.remove(p.getUniqueId(), din.getUniqueId());
			}
		}, 30);
	}*/

	final private void damagebar(Double max, Double cur, Double last, Double dam, final ArmorStand ar) {
		final double rat =  (cur/max)*40d;
		final double lr =  (last/max)*40d;
		double d = (dam/max)*40d;

		if(d >= 1) {
			StringBuffer bar = new StringBuffer();
			for(int i = 0; i<rat; i++) {
				bar.append(ChatColor.GREEN+"|");
			}
			for(int i = 0; i<d; i++) {
				bar.append(ChatColor.RED+"|");
				if(lr<i) {
					break;
				}
			}
			for(int i = 0; i<40-lr; i++) {
				bar.append(ChatColor.BLACK+"|");
			}
			ar.setCustomName(bar.toString());
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					StringBuffer bar = new StringBuffer();
					for(int i = 0; i<rat; i++) {
						bar.append(ChatColor.GREEN+"|");
					}
					for(int i = 0; i<40-rat; i++) {
						bar.append(ChatColor.BLACK+"|");
					}
					ar.setCustomName(bar.toString());
				}
			}, 10);
		}
		else {
			StringBuffer bar = new StringBuffer();
			for(int i = 0; i<rat; i++) {
				bar.append(ChatColor.GREEN+"|");
			}
			for(int i = 0; i<40-rat; i++) {
				bar.append(ChatColor.BLACK+"|");
			}
			ar.setCustomName(bar.toString());
		}
		return ;
	}

	final private void healbar(Double max, Double cur, Double last, Double dam, final ArmorStand ar) {
		final double rat =  (cur/max)*40d;

		StringBuffer bar = new StringBuffer();
		for(int i = 0; i<rat; i++) {
			bar.append(ChatColor.GREEN+"|");
		}
		for(int i = 0; i<40-rat; i++) {
			bar.append(ChatColor.BLACK+"|");
		}
		ar.setCustomName(bar.toString());
		return ;
	}

	final private boolean issmall(LivingEntity le) {
		if(le instanceof Chicken) {
			return true;
		}
		if(le instanceof Rabbit) {
			return true;
		}
		if(le instanceof Ageable) {
			Ageable al = (Ageable) le;
			if(!al.isAdult()) {
				return true;
			}
		}
		if (le instanceof Armadillo) {
			return true;
		}
		if (le instanceof Silverfish) {
			return true;
		}
		if (le instanceof Endermite) {
			return true;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			return true;
		}
		return false;
	}

	final private ArmorStand bardamaged(Double max, Double cur, Double last, Double dam, LivingEntity le) {

		if(!bar.containsKey(le.getUniqueId())) {

			if(le instanceof Player) {
				final ArmorStand din = le.getWorld().spawn(le.getEyeLocation().add(0, -0.05, 0), ArmorStand.class, e -> {
					e.setVisible(false);
					e.setSmall(true);
					e.setSilent(true);
					e.setGravity(false);
					e.setCollidable(false);
					e.setMetadata("din", new FixedMetadataValue(RMain.getInstance(),le.getName()));
					e.setMetadata("bar", new FixedMetadataValue(RMain.getInstance(),true));
					e.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
					e.setCustomNameVisible(true);
					e.setRemoveWhenFarAway(true);
					bar.put(le.getUniqueId(), e.getUniqueId());
					damagebar(max,cur, last ,dam,e);
					int track = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							e.teleport(le.getEyeLocation().clone().add(0, -0.05, 0));
							if(!le.isValid() || le.isInvisible()) {
								Bukkit.getScheduler().cancelTask(trackt.get(le.getUniqueId()));
								if (e != null) {
									e.remove();
									bart.remove(le.getUniqueId());
									bar.remove(le.getUniqueId());
								}
							}
						}
					}, 0,1);
					trackt.put(le.getUniqueId(), track);
				});

				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						Bukkit.getScheduler().cancelTask(trackt.get(le.getUniqueId()));
						if (din != null) {
							din.remove();
							bart.remove(le.getUniqueId());
							bar.remove(le.getUniqueId());
						}
					}
				}, 100);
				bart.put(le.getUniqueId(), task);
				return din;
			}
			else {
				Location lel = le.getEyeLocation().clone().add(0, -0.15, 0);
				if(issmall(le)) {
					lel = le.getEyeLocation().clone().add(0, 0.1, 0);
				}
				final ArmorStand din = le.getWorld().spawn(lel, ArmorStand.class, e -> {
					e.setVisible(false);
					e.setSilent(true);
					e.setGravity(false);
					e.setCollidable(false);
					e.setSmall(true);
				});
				din.setMetadata("din", new FixedMetadataValue(RMain.getInstance(),true));
				din.setMetadata("bar", new FixedMetadataValue(RMain.getInstance(),true));
				din.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
				din.setCustomNameVisible(true);
				din.setRemoveWhenFarAway(true);
				bar.put(le.getUniqueId(), din.getUniqueId());
				damagebar(max,cur, last ,dam,din);
				int track = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						din.teleport(le.getEyeLocation().clone().add(0, -0.15, 0));
						if(!le.isValid()|| le.isInvisible()) {
							Bukkit.getScheduler().cancelTask(trackt.get(le.getUniqueId()));
							if (din != null) {
								din.remove();
								bart.remove(le.getUniqueId());
								bar.remove(le.getUniqueId());
							}
						}
					}
				}, 0,1);
				trackt.put(le.getUniqueId(), track);

				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						Bukkit.getScheduler().cancelTask(trackt.get(le.getUniqueId()));
						if (din != null) {
							din.remove();
							bart.remove(le.getUniqueId());
							bar.remove(le.getUniqueId());
						}
					}
				}, 100);
				bart.put(le.getUniqueId(), task);
				return din;
			}
		}
		else if (Bukkit.getEntity(bar.get(le.getUniqueId())) != null){
			ArmorStand din = (ArmorStand) Bukkit.getEntity(bar.get(le.getUniqueId()));
			damagebar(max,cur,last,dam,din);

			if(bart.containsKey(le.getUniqueId())) {
				Bukkit.getScheduler().cancelTask(bart.get(le.getUniqueId()));

				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						Bukkit.getScheduler().cancelTask(trackt.get(le.getUniqueId()));
						if (din != null) {
							din.remove();
							bart.remove(le.getUniqueId());
							bar.remove(le.getUniqueId());
						}
					}
				}, 100);
				bart.put(le.getUniqueId(), task);
			}

			return din;
		}
		else {
			return null;
		}

	}


	final private ArmorStand barhealed(Double max, Double cur, Double last, Double dam, LivingEntity le) {

		if (Bukkit.getEntity(bar.get(le.getUniqueId())) != null){
			ArmorStand din = (ArmorStand) Bukkit.getEntity(bar.get(le.getUniqueId()));
			healbar(max,cur,last,dam,din);

			if(bart.containsKey(le.getUniqueId())) {
				Bukkit.getScheduler().cancelTask(bart.get(le.getUniqueId()));

				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						Bukkit.getScheduler().cancelTask(trackt.get(le.getUniqueId()));
						if (din != null) {
							din.remove();
							bart.remove(le.getUniqueId());
							bar.remove(le.getUniqueId());
						}
					}
				}, 100);
				bart.put(le.getUniqueId(), task);
			}

			return din;
		}
		else {
			return null;
		}

	}



	@EventHandler
	public void barremove(EntityPotionEffectEvent d)
	{
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run()
			{
				if(d.getEntity() instanceof LivingEntity && bar.containsKey(d.getEntity().getUniqueId())) {
					LivingEntity le = (LivingEntity) d.getEntity();
					if(le.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						ArmorStand din = (ArmorStand) Bukkit.getEntity(bar.get(le.getUniqueId()));
						Bukkit.getScheduler().cancelTask(trackt.get(le.getUniqueId()));
						if (din != null) {
							din.setCustomNameVisible(false);
							din.remove();
							bart.remove(le.getUniqueId());
							bar.remove(le.getUniqueId());
						}
					}

				}
			}
		}, 2);
	}



	@EventHandler
	public void barremove(EntityDeathEvent d)
	{
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run()
			{
				if(bar.containsKey(d.getEntity().getUniqueId())) {
					LivingEntity le = d.getEntity();
					ArmorStand din = (ArmorStand) Bukkit.getEntity(bar.get(le.getUniqueId()));
					Bukkit.getScheduler().cancelTask(trackt.get(le.getUniqueId()));
					if (din != null) {
						din.setCustomNameVisible(false);
						din.remove();
						bart.remove(le.getUniqueId());
						bar.remove(le.getUniqueId());
					}
				}
			}
		}, 2);
	}




	@EventHandler
	public void barremove(PlayerQuitEvent d)
	{
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run()
			{
				if(bar.containsKey(d.getPlayer().getUniqueId())) {
					Player le = d.getPlayer();
					ArmorStand din = (ArmorStand) Bukkit.getEntity(bar.get(le.getUniqueId()));
					Bukkit.getScheduler().cancelTask(trackt.get(le.getUniqueId()));
					if (din != null) {
						din.setCustomNameVisible(false);
						din.remove();
						bart.remove(le.getUniqueId());
						bar.remove(le.getUniqueId());
					}
				}
			}
		}, 2);
	}


	@EventHandler
	public void Damagegetter(EntityRegainHealthEvent d)
	{
		if(d.getEntity() instanceof LivingEntity)
		{
			LivingEntity le = (LivingEntity) d.getEntity();
			if(!damaged.containsKey(le.getUniqueId())) {
				return;
			}
			Double lh = Math.round((le.getHealth())*10)/10.0;
			final Double mh =Math.round(le.getAttribute(Attribute.MAX_HEALTH).getValue()*10)/10.0;
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					final Double ch = Math.round((le.getHealth())*10)/10.0;
					if(bar.containsKey(le.getUniqueId())) {
						barhealed(mh, ch, lh ,d.getAmount(), le);
					}
					if(damaged.containsKey(le.getUniqueId())) {
						le.setCustomName(damaged.get(le.getUniqueId()) + (ChatColor.UNDERLINE + " ("+ String.valueOf(ch) + "/" + String.valueOf(mh) + ")"));
						le.setCustomNameVisible(true);
						le.setMetadata("damaged", new FixedMetadataValue(RMain.getInstance(),true));
					}
				}
			}, 1);
		}
	}

	@EventHandler
	public void Damagegetter(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled()) {
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			le.setMaximumNoDamageTicks(0);
			le.setNoDamageTicks(0);
			if(d.getEntity().hasMetadata("fake") || d.getEntity().hasMetadata("portal"))
			{
				d.setCancelled(true);
				return;
			}

			final Double mh =Math.round(le.getAttribute(Attribute.MAX_HEALTH).getValue()*10)/10.0;

			if(!Holding.holded.containsKey(le.getUniqueId())) {
				if(!le.hasAI() && !(le instanceof Player)) {
					le.setAI(true);
				}
			}
			if(d.getDamage()>0) {
				Double lh = Math.round((le.getHealth())*10)/10.0;

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						final Double ch = Math.round((le.getHealth())*10)/10.0;
						bardamaged(mh, ch, lh ,d.getFinalDamage(), le);
						if(!damaged.containsKey(le.getUniqueId())) {
							damaged.put(le.getUniqueId(), le.getName());
							le.setCustomName((le.getName()) + (ChatColor.UNDERLINE + " ("+ String.valueOf(ch) + "/" + String.valueOf(mh) + ")"
							));
							le.setCustomNameVisible(true);
							le.setMetadata("damaged", new FixedMetadataValue(RMain.getInstance(),true));
						}
						else {
							le.setCustomName(damaged.get(le.getUniqueId()) + (ChatColor.UNDERLINE + " ("+ String.valueOf(ch) + "/" + String.valueOf(mh) + ")"));
							le.setCustomNameVisible(true);
							le.setMetadata("damaged", new FixedMetadataValue(RMain.getInstance(),true));
						}
					}
				}, 1);
				damageind(p, le, d.getFinalDamage());

			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity&& !d.isCancelled()) {
			if(d.getEntity().hasMetadata("fake") || d.getEntity().hasMetadata("portal"))
			{
				d.setCancelled(true);
				return;
			}

			Projectile pr = (Projectile) d.getDamager();
			if(pr.getShooter() instanceof Player && !(pr instanceof Snowball)) {
				Player p = (Player) pr.getShooter();
				LivingEntity le = (LivingEntity) d.getEntity();

				final Double mh =Math.round(le.getAttribute(Attribute.MAX_HEALTH).getValue()*10)/10.0;

				if(!Holding.holded.containsKey(le.getUniqueId())) {

					if(!le.hasAI() && !(le instanceof Player)) {
						le.setAI(true);
					}
				}
				if(d.getDamage()>0) {
					Double lh = Math.round((le.getHealth())*10)/10.0;

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							final Double ch = Math.round((le.getHealth())*10)/10.0 ;
							bardamaged(mh, ch, lh ,d.getFinalDamage(), le);
							if(!damaged.containsKey(le.getUniqueId())) {
								damaged.put(le.getUniqueId(), le.getName());
								le.setCustomName((le.getName()) + (ChatColor.UNDERLINE + " ("+ String.valueOf(ch) + "/" + String.valueOf(mh) + ")"
								));
								le.setCustomNameVisible(true);
								le.setMetadata("damaged", new FixedMetadataValue(RMain.getInstance(),true));
							}
							else {
								le.setCustomName(damaged.get(le.getUniqueId()) + (ChatColor.UNDERLINE + " ("+ String.valueOf(ch) + "/" + String.valueOf(mh) + ")"));
								le.setCustomNameVisible(true);
								le.setMetadata("damaged", new FixedMetadataValue(RMain.getInstance(),true));
							}
						}
					}, 1);
					damageind(p, le, d.getFinalDamage());

				}
			}

		}
	}


	@EventHandler
	public void Damagegetter(EntityDamageEvent d)
	{
		if(d.getEntity().hasMetadata("portal")) {
			d.setCancelled(true);
		}
		if(d.getEntity().hasMetadata("fake") && !d.getEntity().hasMetadata("raidvil") && d.getEntityType() != EntityType.PLAYER) {
			d.setCancelled(true);
		}
		if(d.getEntity() instanceof LivingEntity && !d.isCancelled()&& !d.getEntity().isInvulnerable()) {
			final LivingEntity le = (LivingEntity) d.getEntity();
			le.setMaximumNoDamageTicks(0);
			le.setNoDamageTicks(0);

			if(!le.hasMetadata("rpgspawned") && le.getCustomName() == null) {
				le.getAttribute(Attribute.MAX_HEALTH).setBaseValue(le.getAttribute(Attribute.MAX_HEALTH).getDefaultValue());
				le.setCustomName(trans(le));
				le.setCustomNameVisible(true);
				le.setMetadata("plain", new FixedMetadataValue(RMain.getInstance(),true));
			}

			final Double mh =Math.round(le.getAttribute(Attribute.MAX_HEALTH).getValue()*10)/10.0;

			if(!Holding.holded.containsKey(le.getUniqueId())) {

				if(!le.hasAI() && !(le instanceof Player)) {
            		le.setAI(true);
				}
			}
			if(d.getDamage()>0) {
				Double lh = Math.round((le.getHealth())*10)/10.0;

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						final Double ch = Math.round((le.getHealth())*10)/10.0 ;
						bardamaged(mh, ch, lh,d.getFinalDamage(), le);
						if(!damaged.containsKey(le.getUniqueId())) {
							damaged.put(le.getUniqueId(), le.getName());
							le.setCustomName((le.getName()) + (ChatColor.UNDERLINE + " ("+ String.valueOf(ch) + "/" + String.valueOf(mh) + ")"));
							le.setCustomNameVisible(true);
							le.setMetadata("damaged", new FixedMetadataValue(RMain.getInstance(),true));
						}
						else {
							le.setCustomName(damaged.get(le.getUniqueId()) + (ChatColor.UNDERLINE + " ("+ String.valueOf(ch) + "/" + String.valueOf(mh) + ")"));
							le.setCustomNameVisible(true);
							le.setMetadata("damaged", new FixedMetadataValue(RMain.getInstance(),true));
						}
					}
				}, 1);
			}
		}
	}

	@EventHandler
	public void Enderdragon(EntityDamageByEntityEvent d)
	{
		if (!(d.getEntity() instanceof EnderDragon)) {
			return;
		}
		EnderDragon ed = (EnderDragon) d.getEntity();
		if(ed.getHealth()<=d.getDamage()) {
			ed.setAI(true);
			ed.setCustomName(null);
			ed.setCustomNameVisible(false);
			if(ed.getDragonBattle() != null) {
				ed.getDragonBattle().getBossBar().setTitle(ed.getName());
			}
		}
	}

	@EventHandler
	public void Enderdragon(CreatureSpawnEvent ev) {
		if(ev.getEntityType() == EntityType.ENDER_DRAGON) {

			ev.getEntity().setCustomName(null);
			ev.getEntity().setCustomNameVisible(false);

		}
	}

	@EventHandler
	public void Enderdragon(EnderDragonChangePhaseEvent d)
	{
		EnderDragon ed = (EnderDragon) d.getEntity();
		d.getEntity().setCustomName(null);
		d.getEntity().setCustomNameVisible(false);
		if(ed.getLocation().getWorld().getEnvironment() == Environment.THE_END && ed.getDragonBattle().getRespawnPhase() == RespawnPhase.END) {
			ed.setCustomName(null);
			ed.setCustomNameVisible(false);
		}
		if(d.getNewPhase() == Phase.DYING) {
			if(!ed.hasAI()) {
				ed.setAI(true);
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					if(!ed.hasAI()) {
						ed.setAI(true);
					}
					ed.setCustomName(null);
					ed.setCustomNameVisible(false);
				}
			}, 1);
		}
		if(d.getCurrentPhase() == Phase.DYING) {
			if(!ed.hasAI()) {
				ed.setAI(true);
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					if(!ed.hasAI()) {
						ed.setAI(true);
					}
					ed.setCustomName(null);
					ed.setCustomNameVisible(false);
				}
			}, 1);
		}
		if(ed.getPhase() == Phase.DYING) {
			if(!ed.hasAI()) {
				ed.setAI(true);
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					if(!ed.hasAI()) {
						ed.setAI(true);
					}
					ed.setCustomName(null);
					ed.setCustomNameVisible(false);
				}
			}, 1);
		}
	}

	public void deleter(PluginDisableEvent ev)
	{
		Bukkit.getBossBars().forEachRemaining(b -> b.removeAll());
		Bukkit.getServer().getOnlinePlayers().forEach(p->{
			if(!p.isValid()) {
				return;
			}
			p.closeInventory();
			p.getScoreboard().getObjectives().forEach(o -> o.unregister());
			p.setAbsorptionAmount(0);
			p.getAttribute(Attribute.ARMOR).setBaseValue(0);
			p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(0);
			p.getAttribute(Attribute.LUCK).setBaseValue(0);
			p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(2);
			p.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(4);
			p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.1);
		});
	}

	@EventHandler
	public void deleter(WorldUnloadEvent d)
	{
		World w = d.getWorld();
		w.getEntities().forEach(b -> {
			b.setCustomName(CommonEvents.damaged.get(b.getUniqueId()));
			if((b.hasMetadata("obnpc") || b.hasMetadata("rpgspawned") || b.hasMetadata("untargetable")|| b.hasMetadata("fake")) && !(b instanceof Player)) {
				b.setPersistent(false);
				if(b instanceof LivingEntity) {
					LivingEntity le = (LivingEntity) b;
					le.setRemoveWhenFarAway(true);
				}
				b.remove();
			}
			if(b instanceof ArmorStand && !b.hasGravity() && b.isInvulnerable() && !b.hasMetadata("fake")) {
				b.remove();
			}
		});
	}

	public void er(PluginEnableEvent ev)
	{
		Bukkit.spigot().getConfig().set("settings.moved-wrongly-threshold", 0.1);
		Bukkit.spigot().getConfig().set("settings.moved-too-quickly-multiplier", 300);
		Bukkit.spigot().getConfig().set("settings.log-named-deaths", false);
		Bukkit.spigot().getConfig().set("settings.log-villager-deaths", false);
		Bukkit.spigot().getConfig().set("settings.attribute.maxHealth.max", 99999999);
		Bukkit.spigot().getConfig().set("settings.attribute.attackDamage.max", 99999999);

		Bukkit.getServer().getOnlinePlayers().forEach(p->{

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					if(ClassData.pc.containsKey(p.getUniqueId())) {
						Classgui.LimitBreak(p);
					}
				}
			}, 1);
			Scoreboard board = manager.getNewScoreboard();
			Objective objective = board.registerNewObjective("Hearts" ,Criteria.HEALTH, "[Health]", RenderType.HEARTS);
			objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
			p.setScoreboard(board);
			p.getAttribute(Attribute.ARMOR).setBaseValue(0);
			p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(0);
			p.getAttribute(Attribute.LUCK).setBaseValue(0);
			p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(2);
			p.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(4);
			p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.1);
		});
	}


	final private void guide(Player p) {
		final int lev = p.getLevel();
		StringBuffer sb = new StringBuffer();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run()
			{
				final String loc = p.getLocale();
				if(loc.equalsIgnoreCase("ko_kr")) {
					sb.append(ChatColor.GOLD + "현재 레벨: "+lev + ChatColor.DARK_AQUA + "  추천 군계: ");
					if(0<=lev && lev <=40) {
						sb.append(ChatColor.GREEN + "[평원]");
					}
					if(30<=lev && lev <=70) {
						sb.append(ChatColor.GRAY + "[산, 언덕, 타이가]");
					}
					if(60<=lev && lev <=100) {
						sb.append(ChatColor.AQUA + "[한대, 냉대 기후]");
					}
					if(90<=lev && lev <=130) {
						sb.append(ChatColor.BLUE + "[바다]");
					}
					if(120<=lev && lev <=160) {
						sb.append(ChatColor.DARK_GREEN + "[숲, 동굴]");
					}
					if(150<=lev && lev <=190) {
						sb.append(ChatColor.LIGHT_PURPLE + "[사막, 사바나]");
					}
					if(180<=lev && lev <=220) {
						sb.append(ChatColor.RED + "[악지]");
					}
					if(210<=lev && lev <=250) {
						sb.append(ChatColor.DARK_GRAY + "[늪, 버섯 들판]");
					}
					if(240<=lev && lev <=300) {
						sb.append(ChatColor.YELLOW + "[정글]");
					}
					if(300<=lev && lev <=400) {
						sb.append(ChatColor.DARK_RED + "[지옥]");
					}
					if(400<=lev) {
						sb.append(ChatColor.DARK_PURPLE + "[엔더]");
					}
				}
				else {
					sb.append(ChatColor.GOLD + "Current Level: "+lev + ChatColor.DARK_AQUA + "  Recommended Biome: ");
					if(0<=lev && lev <=40) {
						sb.append(ChatColor.GREEN + "[Plains]");
					}
					if(30<=lev && lev <=70) {
						sb.append(ChatColor.GRAY + "[Mountains, Hills, Taiga]");
					}
					if(60<=lev && lev <=100) {
						sb.append(ChatColor.AQUA + "[Snowy, Frozen]");
					}
					if(90<=lev && lev <=130) {
						sb.append(ChatColor.BLUE + "[Ocean]");
					}
					if(120<=lev && lev <=160) {
						sb.append(ChatColor.DARK_GREEN + "[Forest, Cave]");
					}
					if(150<=lev && lev <=190) {
						sb.append(ChatColor.LIGHT_PURPLE + "[Desert, Savana]");
					}
					if(180<=lev && lev <=220) {
						sb.append(ChatColor.RED + "[Badlands]");
					}
					if(210<=lev && lev <=250) {
						sb.append(ChatColor.DARK_GRAY + "[Swamp, Mushroom Fields]");
					}
					if(240<=lev && lev <=300) {
						sb.append(ChatColor.YELLOW + "[Jungle]");
					}
					if(300<=lev && lev <=400) {
						sb.append(ChatColor.DARK_RED + "[Nether]");
					}
					if(400<=lev) {
						sb.append(ChatColor.DARK_PURPLE + "[Ender]");
					}
				}
				p.sendMessage(sb.toString());
			}
		}, 20);

	}

	public void join(PlayerJoinEvent ev)
	{
		Player p = ev.getPlayer();

		p.setGravity(true);
		p.getAttribute(Attribute.ARMOR).setBaseValue(0);
		p.getAttribute(Attribute.ARMOR_TOUGHNESS).setBaseValue(0);
		p.getAttribute(Attribute.LUCK).setBaseValue(0);
		p.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(2);
		p.getAttribute(Attribute.ATTACK_SPEED).setBaseValue(4);
		p.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.1);

		Scoreboard board = manager.getNewScoreboard();
		Objective objective = board.registerNewObjective("Hearts" ,Criteria.HEALTH, "[Health]", RenderType.HEARTS);
		objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
		p.setScoreboard(board);

		if(p.getGameMode() == GameMode.CREATIVE) {
			p.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
		}
		if(p.getGameMode() != GameMode.CREATIVE) {
			if(p.isInvulnerable()) {
				p.setInvulnerable(false);
			}
		}

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run()
			{
				guide(p);
				if(ClassData.pc.containsKey(p.getUniqueId())) {
					Classgui.LimitBreak(p);
				}
			}
		}, 3);
	}

	public void nepreventer(PlayerQuitEvent ev)
	{
		Player p = ev.getPlayer();

		p.setAbsorptionAmount(0);
		Bukkit.getWorlds().forEach(w -> {
			w.getEntities().forEach(e -> {
				if(e.hasMetadata("din of "+p.getName())  || e.hasMetadata("rob"+p.getName()) || e.hasMetadata("rob of"+p.getName())) {
					e.remove();
				}
				if(Bukkit.getServer().getOnlinePlayers().stream().count() <= 1) {
					w.getEntities().forEach(b -> {
						b.setCustomName(CommonEvents.damaged.get(b.getUniqueId()));
						if((b.hasMetadata("obnpc") || b.hasMetadata("rpgspawned") || b.hasMetadata("untargetable")|| b.hasMetadata("fake")) && !(b instanceof Player)) {
							b.remove();
						}
					});
				}

			});
		});

		p.getScoreboard().getObjectives().forEach(o -> o.unregister());
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run()
			{
				if(Bukkit.getServer().getOnlinePlayers().stream().count() < 1 || Bukkit.getServer().getOnlinePlayers().isEmpty()) {
					List<World> worlds = Bukkit.getServer().getWorlds();
					worlds.forEach(w ->{
								w.getEntities().forEach(b -> {
									b.setCustomName(CommonEvents.damaged.get(b.getUniqueId()));
									if((b.hasMetadata("obnpc") || b.hasMetadata("rpgspawned") || b.hasMetadata("untargetable")|| b.hasMetadata("fake")) && !(b instanceof Player)) {
										b.remove();
									}
								});
							}
					);
				}
			}
		}, 3);

	}
	public void delete(PlayerTeleportEvent d)
	{
		Player p = (Player) d.getPlayer();
		if(d.getFrom().getWorld() != d.getTo().getWorld()) {
			Collection<PotionEffect> pes = p.getActivePotionEffects();
			pes.forEach(pe -> {
				p.removePotionEffect(pe.getType());
			});

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), () ->{
				pes.forEach(pe -> {
					p.addPotionEffect(pe);
				});
			}, 3);
			
			Bukkit.getWorlds().forEach(w -> {
				w.getEntities().forEach(e -> {
					if(e.hasMetadata("din of "+p.getName())  || e.hasMetadata("rob"+p.getName()) || e.hasMetadata("rob of"+p.getName())) {
						e.remove();
					}
				});
			});
		}
	}

	public void delete(PlayerDeathEvent ev)
	{
		Player p = ev.getEntity();
		Bukkit.getWorlds().forEach(w -> {
			w.getEntities().forEach(e -> {
				if(e.hasMetadata("din of "+p.getName())  || e.hasMetadata("rob"+p.getName()) || e.hasMetadata("rob of"+p.getName())) {
					e.remove();
				}
			});
		});
	}


	public void dinremove(PlayerRespawnEvent ev)
	{
		Player p = ev.getPlayer();

		Bukkit.getWorlds().forEach(w -> {
			w.getEntities().forEach(e -> {
				if(e.hasMetadata("din of "+p.getName())  || e.hasMetadata("rob"+p.getName()) || e.hasMetadata("rob of"+p.getName())) {
					e.remove();
				}
			});
		});
		if(p.getGameMode() != GameMode.CREATIVE) {
			if(p.isInvulnerable()) {
				p.setInvulnerable(false);
			}
		}
		p.setAbsorptionAmount(0);
	}

	@EventHandler
	public void nepreventer(PlayerGameModeChangeEvent ev)
	{
		Player p = ev.getPlayer();
		if(ev.getNewGameMode() == GameMode.CREATIVE) {
			p.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
		}
		else {
			p.removeMetadata("fake", RMain.getInstance());
		}
	}

	public void Bullet(ProjectileHitEvent ev)
	{

		if(ev.getEntity().hasMetadata("din")) {
			ev.setCancelled(true);
			return;
		}
		if(ev.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)ev.getEntity().getShooter();
			if(ev.getHitEntity()!=null) {
				Entity e =ev.getHitEntity();
				{
					if (e instanceof Player)
					{
						Player p1 = (Player) e;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p) == Party.getParty(p1))
							{
								return;
							}
						}
					}
					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake")))
					{
						LivingEntity le = (LivingEntity)e;
						if(!Holding.holded.containsKey(le.getUniqueId())) {
							if(!le.hasAI() && !(le instanceof Player)) {
								le.setAI(true);
							}
						}
						le.setMaximumNoDamageTicks(0);
						le.setNoDamageTicks(0);
					}
				}
			}

		}
	}


}