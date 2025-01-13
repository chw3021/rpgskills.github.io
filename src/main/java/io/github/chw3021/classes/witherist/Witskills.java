package io.github.chw3021.classes.witherist;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Wither;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Witskills extends Pak {

	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();


	private HashMap<UUID, Integer> demol = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> demolt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> pfbm = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> pfbmt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> bola = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> bolat = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> whtqs = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> whtqst = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> blckspin = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> blckspint = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> cryscg = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> cryscgt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> bolahit = new HashMap<UUID, Integer>();


	private HashMap<UUID, Item> hooki = new HashMap<UUID, Item>();
	private HashMap<UUID, Integer> hookt1 = new HashMap<UUID, Integer>();
	private HashMap<UUID, LivingEntity> hookl = new HashMap<UUID, LivingEntity>();

	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<String, Integer> hovert = new HashMap<String, Integer>();
	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private WitSkillsData wsd;




	private static final Witskills instance = new Witskills ();
	public static Witskills getInstance(){
		return instance;
	}


	public void er(PluginEnableEvent ev)
	{
		WitSkillsData w = new WitSkillsData(WitSkillsData.loadData(path +"/plugins/RPGskills/WitSkillsData.data"));
		wsd = w;
	}


	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Classes"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				Player p = (Player) e.getWhoClicked();
				if(hovert.containsKey(p.getName())) {
					Bukkit.getScheduler().cancelTask(hovert.get(p.getName()));
					hovert.remove(p.getName());
				}
			}

		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Witskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				WitSkillsData w = new WitSkillsData(WitSkillsData.loadData(path +"/plugins/RPGskills/WitSkillsData.data"));
				wsd = w;
			}

		}
	}


	public void nepreventer(PlayerJoinEvent ev)
	{
		WitSkillsData w = new WitSkillsData(WitSkillsData.loadData(path +"/plugins/RPGskills/WitSkillsData.data"));
		wsd = w;

	}


	public void Roses(EntityDropItemEvent ev)
	{
		if(ev.getEntity() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getEntity();
			if(fallingb.hasMetadata("wrose")){
				ev.setCancelled(true);
			}
		}
	}



	public void Roses(EntityDamageByEntityEvent ev)
	{
		if(ev.getDamager() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getDamager();
			if(fallingb.hasMetadata("wrose")){
				ev.setCancelled(true);
			}
		}
	}


	public void Roses(EntityChangeBlockEvent ev)
	{
		if(ev.getEntity() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getEntity();
			if(fallingb.hasMetadata("wrose")){
				ev.setCancelled(true);
			}
		}
	}



	public void Roses(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();
		double sec =7*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);



		if(ClassData.pc.get(p.getUniqueId()) == 13)
		{
			if((p.getInventory().getItemInMainHand().getType().name().contains("HOE")) && (p.isSneaking()))
			{
				ev.setCancelled(true);

				final Location tl = p.getEyeLocation().clone();
				
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("위더장미")
						.ename("Roses")
						.slot(5)
						.hm(sdcooldown)
						.skillUse(() -> {
							if(demolt.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(demolt.get(p.getUniqueId()));
								demolt.remove(p.getUniqueId());
							}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									if(Proficiency.getpro(p)>=1) {
										demol.putIfAbsent(p.getUniqueId(), 0);
									}
								}
							}, 3);

							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									demol.remove(p.getUniqueId());
								}
							}, 45);
							demolt.put(p.getUniqueId(), task);

							ItemStack rose = new ItemStack(Material.WITHER_ROSE);

							FallingBlock fallingb = p.getWorld().spawnFallingBlock(tl, getBd(Material.POTTED_WITHER_ROSE));
							fallingb.setInvulnerable(true);
							fallingb.setVelocity(p.getEyeLocation().clone().getDirection().multiply(0.25));
							fallingb.setDropItem(false);
							fallingb.setHurtEntities(false);
							fallingb.setGravity(false);
							fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							fallingb.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							fallingb.setMetadata("wrose", new FixedMetadataValue(RMain.getInstance(), p.getName()));

							for(int i = 0; i<10; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()

									{
										final Location l = fallingb.getLocation().clone();

										p.getWorld().spawnParticle(Particle.ITEM, l, 100,2,2,2, rose);
										p.getWorld().spawnParticle(Particle.DRAGON_BREATH, l, 30,1.2,1.2,1.2);
										p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.3f, 2f);
										p.playSound(p.getLocation(), Sound.ITEM_NETHER_WART_PLANT, 0.6f, 2f);

										for(Entity e: fallingb.getNearbyEntities(3.5, 3.5, 3.5)) {
											if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
											{
												LivingEntity le = (LivingEntity)e;
												if (le instanceof Player)
												{

													Player p1 = (Player) le;
													if(Party.hasParty(p) && Party.hasParty(p1))	{
														if(Party.getParty(p).equals(Party.getParty(p1)))
														{
															continue;
														}
													}
												}

												atk0(0.06, wsd.Roses.get(p.getUniqueId())*0.05, p, le);
												Holding.holding(p, le, 5l);
												le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 3, false, false));
												le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 2, false, false));
											}
										}
									}
								}, i*4);
							}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()

								{
									fallingb.remove();
								}
							}, 50);

						});
				bd.execute();

			}
		}
	}



	public void Demolition(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 13)
		{
			if((p.getInventory().getItemInMainHand().getType().name().contains("HOE")) && (p.isSneaking()) && demol.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

				if(demolt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(demolt.get(p.getUniqueId()));
					demolt.remove(p.getUniqueId());
				}
				demol.remove(p.getUniqueId());

				if(pfbmt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(pfbmt.get(p.getUniqueId()));
					pfbmt.remove(p.getUniqueId());
				}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						if(Proficiency.getpro(p)>=2) {
							pfbm.putIfAbsent(p.getUniqueId(), 0);
						}
					}
				}, 6);

				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						pfbm.remove(p.getUniqueId());
					}
				}, 35);
				pfbmt.put(p.getUniqueId(), task);

				Location l = gettargetblock(p,3);
				final World lw = l.getWorld();

				AtomicInteger j = new AtomicInteger();

				for(int i =0; i<5; i++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						BlockData bd1 = getBd(Material.QUARTZ_BLOCK);
						BlockData bd2 = getBd(Material.CRYING_OBSIDIAN);
						@Override
						public void run()
						{
							final int ji = j.incrementAndGet();

							if(Proficiency.getpro(p)>=2) {
								lw.spawnParticle(Particle.BLOCK, l, 100*ji,ji,ji,ji,1, bd1);
							}
							lw.spawnParticle(Particle.BLOCK, l, 50*ji,ji,ji,ji,1, bd2);
							lw.spawnParticle(Particle.SWEEP_ATTACK, l, 4*ji,ji,ji,ji,1);
							lw.spawnParticle(Particle.WITCH, l, 200*ji,ji,ji,ji,1);
							lw.spawnParticle(Particle.SONIC_BOOM, l, 2*ji,ji*0.8,ji*0.8,ji*0.8);

							p.setCooldown(CAREFUL, 4);
							p.swingMainHand();

							p.playSound(p.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 0.3f, 2f);
							p.playSound(p.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 0.3f, 0f);
							p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 0.23f, 0f);

							for (Entity e : p.getWorld().getNearbyEntities(l, 4, 4, 4))
							{
								if (e instanceof Player)
								{

									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											continue;
										}
									}
								}
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
								{
									LivingEntity le = (LivingEntity)e;
									atk0(0.56, wsd.Roses.get(p.getUniqueId())*0.48, p, le);
									Holding.holding(p, le, 25l);
									le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 3, false, false));
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 2, false, false));
								}
							}
						}
					}, i*3);
				}
			}
		}
	}

	final private ArrayList<Location> PurifierBeam(Player p) {

		ArrayList<Location> line = new ArrayList<Location>();
		HashSet<LivingEntity> les = new HashSet<LivingEntity>();

		final Location eye = p.getEyeLocation().clone().add(0, 0.5, 0);
		final World ew = eye.getWorld();


		for(double ang = -Math.PI/3; ang<Math.PI/3; ang += Math.PI/5) {
			for(double point = 0.1; point<10.1; point+=0.5) {
				line.add(eye.clone().add(eye.clone().getDirection().normalize().rotateAroundY(ang).multiply(point)));
			}
		}
		line.forEach(l -> {
			ew.spawnParticle(Particle.FLASH,l, 1);
			ew.spawnParticle(Particle.ENCHANT,l, 20,0.53,0.1,0.53,0);
			ew.spawnParticle(Particle.SHRIEK,l, 5,0.1,0.1,0.1,0,5);

		});
		line.forEach(l -> {

			for (Entity a : l.getWorld().getNearbyEntities(l, 2.5, 5, 2.5))
			{
				if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal")))
				{
					if (a instanceof Player)
					{

						Player p1 = (Player) a;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								continue;
							}
						}
					}
					LivingEntity le = (LivingEntity)a;
					les.add(le);
				}
			}
		});

		les.forEach(le ->{
			atk0(0.8, wsd.Roses.get(p.getUniqueId())*0.9, p, le);
			Holding.holding(p, le, 15l);
		});

		return line;
	}




	public void PurifierBeam(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 13 &&pfbm.containsKey(p.getUniqueId())) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("HOE")) && p.isSneaking())
			{
				ev.setCancelled(true);

				if(pfbmt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(pfbmt.get(p.getUniqueId()));
					pfbmt.remove(p.getUniqueId());
				}
				pfbm.remove(p.getUniqueId());

				final Location eye = p.getEyeLocation().clone().add(0, 1, 0);
				final World ew = eye.getWorld();
				ew.spawnParticle(Particle.REVERSE_PORTAL,eye, 100,1,1,1,0.1);
				ew.spawnParticle(Particle.WITCH,eye, 100,1,1,1,0.1);
				ew.spawnParticle(Particle.FLASH,eye, 5,1,1,1,0.1);

				p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1f, 0f);
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 0.2f, 2f);

				for(int i = 0; i<3; i++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable()
					{
						@Override
						public void run()
						{
							p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.1f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1f, 2f);
							PurifierBeam(p);
						}
					}, i*2+10);
				}


			}
		}
	}




	public void ReapingHook(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

		if(ClassData.pc.get(p.getUniqueId()) == 13 && wsd.ReapingHook.get(p.getUniqueId())>=1) {
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
				{
					p.setCooldown(CAREFUL, 2);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("어둠의갈고리")
							.ename("ReapingHook")
							.slot(1)
							.hm(gdcooldown)
							.skillUse(() -> {
								final Location pfl = p.getLocation().clone();


								p.setCooldown(CAREFUL, 4);
								p.swingMainHand();
								ItemStack reap = p.getInventory().getItemInMainHand();
								p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1.0f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_AMBIENT, 1.0f, 0f);

								Location pl = pfl.clone();

								Item hook = p.getWorld().dropItem(p.getLocation(), reap);
								hook.setPickupDelay(9999);
								hook.setGravity(false);
								hook.setInvulnerable(true);
								hook.setOwner(p.getUniqueId());
								hook.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								hook.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								hook.setMetadata("reap of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
								hook.setVelocity(pl.getDirection().clone().normalize().multiply(1.2));
								hooki.put(p.getUniqueId(), hook);

								int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										pl.add(pl.getDirection().clone().normalize().multiply(1.2));
										hook.teleport(pl);
										p.getWorld().spawnParticle(Particle.WITCH ,pl, 10, 0.2,0.2,0.2,0);
										p.getWorld().spawnParticle(Particle.END_ROD ,pl, 10, 0.2,0.2,0.2,0);
										for(Entity e : pl.getWorld().getNearbyEntities(pl, 1.2,1.2,1.2)) {
											if (e instanceof Player)
											{

												Player p1 = (Player) e;
												if(Party.hasParty(p) && Party.hasParty(p1))	{
													if(Party.getParty(p).equals(Party.getParty(p1)))
													{
														continue;
													}
												}
											}
											if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
											{
												LivingEntity le = (LivingEntity)e;
												hookl.put(p.getUniqueId(), le);
												atk0(0d, 1d, p, le);
												Holding.superholding(p, le, 20l);
												break;
											}
										}

									}
								},0,1);
								hookt1.put(p.getUniqueId(), task);

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										if(hookt1.containsKey(p.getUniqueId())) {
											Bukkit.getScheduler().cancelTask(hookt1.get(p.getUniqueId()));
											hookt1.remove(p.getUniqueId());
										}
									}
								},15);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										hook.remove();
									}
								}, 16);

							});
					bd.execute();


				} // adding players name + current system time in miliseconds

			}}
	}


	public void ReapingHook(EntityDamageByEntityEvent d)
	{
		if(d.getEntity() instanceof LivingEntity && d.getDamager() instanceof Player)
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if(hookl.containsKey(p.getUniqueId()) && hookl.getOrDefault(p.getUniqueId(), p)==le) {

				if(hookt1.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(hookt1.get(p.getUniqueId()));
					hookt1.remove(p.getUniqueId());
				}
				AtomicInteger j = new AtomicInteger(0);
				Location hl = le.getLocation();
				Location pal = p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(0.5));
				ArrayList<Location> line = new ArrayList<Location>();
				for(double da = 0.1; da < pal.distance(hl); da += 0.1) {
					Location hlc = hl.clone();
					hlc.add(pal.clone().toVector().subtract(hlc.clone().toVector()).normalize().multiply(da));
					line.add(hlc);
				}
				hookl.remove(p.getUniqueId());
				if(hooki.containsKey(p.getUniqueId())) {
					hooki.get(p.getUniqueId()).setVelocity(pal.clone().toVector().subtract(hl.clone().toVector()).normalize().multiply(1.1));
				}
				line.forEach(l -> {

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							le.teleport(l);
							Holding.superholding(p, le, 10l);
							if(Proficiency.getpro(p)>=1) {
								for(Entity e : le.getWorld().getNearbyEntities(le.getLocation(), 2.5,2.5,2.5)) {
									if (e instanceof Player)
									{

										Player p1 = (Player) e;
										if(Party.hasParty(p) && Party.hasParty(p1))	{
											if(Party.getParty(p).equals(Party.getParty(p1)))
											{
												continue;
											}
										}
									}
									if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
									{

										e.teleport(l);
										Holding.superholding(p, (LivingEntity) e, 10l);
									}
								}
							}

						}
					}, j.getAndIncrement()/20);
				});
			}
		}
	}




	public void WitherSkull(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		double sec =4*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

		if(ClassData.pc.get(p.getUniqueId()) == 13) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("HOE")) && !p.isSneaking())
			{
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("위더해골")
						.ename("WitherSkull")
						.slot(0)
						.hm(cdcooldown)
						.skillUse(() -> {
							p.setCooldown(CAREFUL, 4);
							if(bolat.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(bolat.get(p.getUniqueId()));
								bolat.remove(p.getUniqueId());
							}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									if(Proficiency.getpro(p)>=1) {
										bola.putIfAbsent(p.getUniqueId(), 0);
									}
								}
							}, 5);

							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									bola.remove(p.getUniqueId());
								}
							}, 35);
							bolat.put(p.getUniqueId(), task);

							p.swingMainHand();
							p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 1);
							WitherSkull ws = (WitherSkull) p.launchProjectile(WitherSkull.class);
							ws.setYield(0.0f);
							ws.setShooter(p);
							ws.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
							ws.setIsIncendiary(false);
							if(p.isFlying()) {
								ws.setCharged(true);
							}
							ws.setMetadata("ws of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));

						});
				bd.execute();
			}
		}
	}

	public void WitherSkull(ProjectileHitEvent ev)
	{
		if(ev.getEntity() instanceof WitherSkull) {
			WitherSkull fb = (WitherSkull)ev.getEntity();
			if(fb.getShooter() instanceof Player) {
				Player p = (Player) fb.getShooter();
				if(fb.hasMetadata("ws of"+p.getName())) {
					fb.getWorld().spawnParticle(Particle.EXPLOSION, fb.getLocation(), 2);
					p.playSound(fb.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					if(Proficiency.getpro(p)>=2) {
						BlockData bd1 = getBd(Material.CHISELED_QUARTZ_BLOCK);
						BlockData bd2 = getBd(Material.QUARTZ_PILLAR);
						
						p.getWorld().spawnParticle(Particle.ITEM, fb.getLocation(), 100,3,3,3, new ItemStack(Material.QUARTZ));
						p.getWorld().spawnParticle(Particle.BLOCK, fb.getLocation(), 100,3,3,3, bd1);
						p.getWorld().spawnParticle(Particle.BLOCK, fb.getLocation(), 100,3,3,3, bd2);
						p.playSound(fb.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1.0f, 0f);
					}
					for(Entity n : fb.getLocation().getWorld().getNearbyEntities(fb.getLocation(), 4, 4, 4)) {
						if(n!=p && n instanceof LivingEntity&& !(n.hasMetadata("fake"))&& !(n.hasMetadata("portal"))) {
							LivingEntity le = (LivingEntity)n;

							if(fb.isCharged()) {
								atk0(0.684, wsd.WitherSkull.get(p.getUniqueId())*0.684, p, le);
							}
							else {
								atk0(0.34, wsd.WitherSkull.get(p.getUniqueId())*0.34, p, le);
							}

						}
					}
				}
			}

		}
	}



	public void WitherSkull(EntityExplodeEvent ev)
	{


		if(ev.getEntity() instanceof WitherSkull) {
			WitherSkull fb = (WitherSkull)ev.getEntity();
			if(fb.getShooter() instanceof Player) {
				Player p = (Player) fb.getShooter();
				if(fb.hasMetadata("ws of"+p.getName())) {
					ev.setCancelled(true);
				}
			}

		}
	}


	public void WitherBola(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 13 &&bola.containsKey(p.getUniqueId())) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("HOE")) && !p.isSneaking())
			{
				ev.setCancelled(true);

				if(bolat.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(bolat.get(p.getUniqueId()));
					bolat.remove(p.getUniqueId());
				}
				bola.remove(p.getUniqueId());


				if(whtqst.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(whtqst.get(p.getUniqueId()));
					whtqst.remove(p.getUniqueId());
				}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						if(Proficiency.getpro(p)>=1) {
							whtqs.putIfAbsent(p.getUniqueId(), 0);
						}
					}
				}, 3);

				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						whtqs.remove(p.getUniqueId());
					}
				}, 35);
				whtqst.put(p.getUniqueId(), task);

				if(bolahit.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(bolahit.get(p.getUniqueId()));
					bolahit.remove(p.getUniqueId());
				}

				p.setCooldown(CAREFUL, 4);
				p.swingMainHand();
				p.playSound(p.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 1, 0f);
				Snowball ws = (Snowball) p.launchProjectile(Snowball.class);
				ws.setItem(new ItemStack(Material.LEAD));
				ws.setShooter(p);
				ws.setVelocity(p.getLocation().getDirection().normalize().multiply(1.6));
				ws.setMetadata("witherbola", new FixedMetadataValue(RMain.getInstance(), true));
				ws.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
				ws.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				ws.setInvulnerable(true);
				int bolt = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						ws.getWorld().spawnParticle(Particle.SWEEP_ATTACK, ws.getLocation(), 10,2,0.5,2);
						ws.getWorld().spawnParticle(Particle.PORTAL, ws.getLocation(), 50,2,0.5,2);
						p.playSound(ws.getLocation(), Sound.ENTITY_FISHING_BOBBER_THROW, 0.2f, 0f);
					}
				}, 2,2);
				bolahit.put(p.getUniqueId(), bolt);

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						if(ws.isValid()) {
							Bukkit.getPluginManager().callEvent(new ProjectileHitEvent(ws));
						}
					}
				}, 60);
			}
		}
	}

	public void WitherBola(ProjectileHitEvent d)
	{
		if(d.getEntity().hasMetadata("witherbola")) {
			Player p = (Player) d.getEntity().getShooter();
			final Location l = d.getEntity().getLocation();

			if(bolahit.containsKey(p.getUniqueId())) {
				Bukkit.getScheduler().cancelTask(bolahit.get(p.getUniqueId()));
				bolahit.remove(p.getUniqueId());
			}
			p.playSound(l, Sound.ENTITY_FISHING_BOBBER_RETRIEVE, 0.69f, 0f);
			p.playSound(l, Sound.ENTITY_FISHING_BOBBER_RETRIEVE, 0.69f, 1.8f);
			l.getWorld().spawnParticle(Particle.FALLING_OBSIDIAN_TEAR, l, 200,3,3,3);
			l.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, l, 200,3,3,3);
			l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 10,3,1,3);
			for(Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3)) {
				if (e instanceof Player)
				{

					Player p1 = (Player) e;
					if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
						{
							continue;
						}
					}
				}
				if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
					LivingEntity le = (LivingEntity) e;
					atk0(0.3, wsd.WitherSkull.get(p.getUniqueId())*0.3, p, le);
					le.teleport(l);
					Holding.holding(p, le, 1l);
				}
			}
		}
	}


	public void WhiteQuarts(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 13)
		{
			if((p.getInventory().getItemInMainHand().getType().name().contains("HOE")) && !(p.isSneaking())&& whtqs.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

				if(whtqst.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(whtqst.get(p.getUniqueId()));
					whtqst.remove(p.getUniqueId());
				}
				whtqs.remove(p.getUniqueId());

				for(int i =0; i<6; i++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							p.setCooldown(CAREFUL, 3);
							p.swingMainHand();
							p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 2f);
							Snowball ws = (Snowball) p.launchProjectile(Snowball.class);
							ws.setItem(new ItemStack(Material.QUARTZ_PILLAR));
							ws.setShooter(p);
							ws.setVelocity(p.getLocation().getDirection().normalize().multiply(1.2));
							ws.setMetadata("whitequarts", new FixedMetadataValue(RMain.getInstance(), true));
							ws.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
							ws.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							ws.setInvulnerable(true);
						}
					}, i*2);
				}
			}
		}
	}

	public void WhiteQuarts(ProjectileHitEvent d)
	{
		if(d.getEntity().hasMetadata("whitequarts")) {
			Player p = (Player) d.getEntity().getShooter();
			Location l = d.getEntity().getLocation();

			p.playSound(l, Sound.BLOCK_METAL_BREAK, 0.69f, 0f);
			p.playSound(l, Sound.BLOCK_NETHER_GOLD_ORE_BREAK, 0.69f, 0f);
			p.getWorld().spawnParticle(Particle.BLOCK, l, 100,3,3,3, getBd(Material.SMOOTH_QUARTZ));
			p.getWorld().spawnParticle(Particle.BLOCK, l, 100,3,3,3, getBd(Material.QUARTZ_PILLAR));
			l.getWorld().spawnParticle(Particle.WHITE_ASH, l, 100,3,3,3);
			l.getWorld().spawnParticle(Particle.FLASH, l, 3,3,3,3);
			for(Entity e : p.getWorld().getNearbyEntities(l, 3, 3, 3)) {
				if (e instanceof Player)
				{

					Player p1 = (Player) e;
					if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
						{
							continue;
						}
					}
				}
				if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
					LivingEntity le = (LivingEntity) e;
					atk0(0.36, wsd.WitherSkull.get(p.getUniqueId())*0.4, p, le);
					Holding.holding(p, le, 6l);
				}
			}
		}
	}


	public void Curse(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);




		if(ClassData.pc.get(p.getUniqueId()) == 13) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
				{
					final Location pl = p.getLocation().clone().add(p.getLocation().clone().getDirection().normalize().multiply(3.5)).clone().add(0, 1, 0);

					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("저주")
							.ename("Curse")
							.slot(3)
							.hm(frcooldown)
							.skillUse(() -> {
								if(blckspint.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(blckspint.get(p.getUniqueId()));
									blckspint.remove(p.getUniqueId());
								}

								p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, p.getLocation(), 200, 4,2,4);
								p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 500);
								p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, p.getLocation(), 500,2,1,2);

								final Vector v = pl.clone().add(1, 0, 0).toVector().subtract(pl.toVector());

								ArrayList<Location> draw = new ArrayList<Location>();
								AtomicInteger j = new AtomicInteger();
								for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
									draw.add(pl.clone().add(v.clone().rotateAroundY(an).normalize().multiply(4)));
								}
								draw.forEach(l -> {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											p.getWorld().spawnParticle(Particle.WITCH, l.clone().add(0, 0.2, 0),10,0.1,0.3,0.1);
											p.getWorld().spawnParticle(Particle.PORTAL, l.clone().add(0, 0.2, 0),10,0.1,0.3,0.1);
											p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, l.clone().add(0, 0.2, 0),10,0.1,0.3,0.1);
											p.getWorld().spawnParticle(Particle.ENCHANT, l.clone().add(0, 0.2, 0),10,0.1,0.3,0.1);
										}
									}, j.incrementAndGet()/100);

								});

								for (Entity a : pl.getWorld().getNearbyEntities(pl, 4.2, 3.5, 4.2))
								{
									if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal")))
									{
										final LivingEntity le = (LivingEntity)a;
										if (le instanceof Player)
										{

											Player p1 = (Player) le;
											if(Party.hasParty(p) && Party.hasParty(p1))	{
												if(Party.getParty(p).equals(Party.getParty(p1)))
												{
													continue;
												}
											}
										}
										atk0(0.68, wsd.Curse.get(p.getUniqueId())*0.7, p, le);
										le.teleport(pl);
										Holding.holding(p, le, 35l);
										le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,35,wsd.Curse.get(p.getUniqueId()),false,false));
										le.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,35,2,false,false));
										le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,35,2,false,false));
										p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, le.getLocation(), 50,0,0,0, 2);
										p.playSound(le.getLocation(), Sound.ENTITY_WITHER_HURT, 0.5f, 0.6f);
									}
								}
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											blckspin.putIfAbsent(p.getUniqueId(), 0);
											if(p.getLocale().equalsIgnoreCase("ko_kr")) {
												StringBuffer sb = new StringBuffer();
												sb.append(ChatColor.GRAY + (ChatColor.STRIKETHROUGH + "[저주]"));
												sb.append(ChatColor.RESET + (ChatColor.BOLD + " ▶▶▶ "));
												sb.append(ChatColor.AQUA + (ChatColor.ITALIC + "[밤의 고리]"));
												p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(sb.toString()).create());
											}
											else {
												StringBuffer sb = new StringBuffer();
												sb.append(ChatColor.GRAY + (ChatColor.STRIKETHROUGH + "[Curse]"));
												sb.append(ChatColor.RESET + (ChatColor.BOLD + " ▶▶▶ "));
												sb.append(ChatColor.AQUA + (ChatColor.ITALIC + "[CircleOfNight]"));
												p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(sb.toString()).create());
											}
										}
									}
								}, 6);
								p.playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 0.3f, 0.3f);


								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										blckspin.remove(p.getUniqueId());
									}
								}, 35);
								blckspint.put(p.getUniqueId(), task);

							});
					bd.execute();


				} // adding players name + current system time in miliseconds

			}}
	}



	final private Location BlackSpins(Location pl, AtomicInteger j) {

		ArrayList<Location> swing = new ArrayList<Location>();
		AtomicInteger k = new AtomicInteger(0);
		final double dis = j.get()*0.5+0.12;
		final int size = 11-j.incrementAndGet();
		final Location l1 = pl.clone().add(pl.clone().getDirection().normalize().multiply(dis));
		for(double i = 0; i<Math.PI*2; i += Math.PI/90) {
			Location s = l1.clone().setDirection(l1.clone().getDirection().rotateAroundY(i).normalize());
			s.add(s.getDirection().multiply(3.5*(size*0.1)+0.2));
			swing.add(s);
		}
		swing.forEach(l ->{
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					pl.getWorld().spawnParticle(Particle.PORTAL,l, 3,0,0,0,0);
					pl.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR,l, 4, 0.1,0.1,0.1);
					pl.getWorld().spawnParticle(Particle.SWEEP_ATTACK,l, 3,0,0,0,0);
				}
			}, k.incrementAndGet()/90);
		});
		return l1;
	}


	/*final private void BlackSpin(LivingEntity le, Player p) {

		ArrayList<Location> swing = new ArrayList<Location>();
		AtomicInteger j = new AtomicInteger(0);
		for(double i = 0; i<Math.PI*2; i += Math.PI/90) {
			Location l1 = le.getEyeLocation().clone();
			Location s = l1.setDirection(l1.getDirection().rotateAroundY(i).normalize());
			s.add(s.getDirection().multiply(2));
			swing.add(s);
		}
		swing.forEach(l ->{
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable()
			{
	     	@Override
            public void run()
 				{
					p.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR,l, 4, 0.2,0.2,0.2);
	            }
	        }, j.incrementAndGet()/90);
		});
	}*/


	public void BlackSpin(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();


		if(ClassData.pc.get(p.getUniqueId()) == 13&&blckspin.containsKey(p.getUniqueId())) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
				{
					ev.setCancelled(true);

					p.setCooldown(CAREFUL, 4);
					p.swingMainHand();

					ArrayList<Location> eye = new ArrayList<Location>();
					for(double i = 0; i<Math.PI*2; i += Math.PI/90) {
						Location l2 = p.getEyeLocation().clone();
						Location e = l2.setDirection(l2.getDirection().rotateAroundY(i).normalize());
						eye.add(e);
					}

					AtomicInteger j = new AtomicInteger(0);
					AtomicInteger c = new AtomicInteger(0);

					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.3f, 2);
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.3f, 0.6f);
					p.playSound(p.getLocation(), Sound.BLOCK_NETHERITE_BLOCK_BREAK, 0.3f, 0.6f);
					eye.forEach(l ->{
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable()
						{
							@Override
							public void run()
							{
								p.teleport(l);
							}
						}, j.incrementAndGet()/150);
					});

					final Location pl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(3.5)).clone();

					for(int i = 0; i<10; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{


								Location ptl = BlackSpins(pl,c).clone();
								for (Entity a : ptl.getWorld().getNearbyEntities(ptl.clone(), 4.5, 4.5, 4.5))
								{
									if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal")))
									{
										final LivingEntity le = (LivingEntity)a;
										if (le instanceof Player)
										{

											Player p1 = (Player) le;
											if(Party.hasParty(p) && Party.hasParty(p1))	{
												if(Party.getParty(p).equals(Party.getParty(p1)))
												{
													continue;
												}
											}
										}
										atk0(0.168, wsd.Curse.get(p.getUniqueId())*0.2, p, le);
										Holding.superholding(p, le, 20l);
										le.teleport(ptl);
									}
								}
							}
						}, i*2);

					}

					if(blckspint.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(blckspint.get(p.getUniqueId()));
						blckspint.remove(p.getUniqueId());
					}
					blckspin.remove(p.getUniqueId());

					if(cryscgt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(cryscgt.get(p.getUniqueId()));
						cryscgt.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								cryscg.putIfAbsent(p.getUniqueId(), 0);
								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
									StringBuffer sb = new StringBuffer();
									sb.append(ChatColor.GRAY + (ChatColor.STRIKETHROUGH + "[저주]"));
									sb.append(ChatColor.RESET + (ChatColor.BOLD + " ▶▶▶ "));
									sb.append(ChatColor.GRAY + (ChatColor.STRIKETHROUGH + "[밤의 고리]"));
									sb.append(ChatColor.RESET + (ChatColor.BOLD + " ▶▶▶ "));
									sb.append(ChatColor.AQUA + (ChatColor.ITALIC + "[수정 감옥]"));
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(sb.toString()).create());
								}
								else {
									StringBuffer sb = new StringBuffer();
									sb.append(ChatColor.GRAY + (ChatColor.STRIKETHROUGH + "[Curse]"));
									sb.append(ChatColor.RESET + (ChatColor.BOLD + " ▶▶▶ "));
									sb.append(ChatColor.GRAY + (ChatColor.STRIKETHROUGH + "[CircleOfNight]"));
									sb.append(ChatColor.RESET + (ChatColor.BOLD + " ▶▶▶ "));
									sb.append(ChatColor.AQUA + (ChatColor.ITALIC + "[CrystalCage]"));
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(sb.toString()).create());
								}
							}
						}
					}, 5);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							cryscg.remove(p.getUniqueId());
						}
					}, 45);
					cryscgt.put(p.getUniqueId(), task);

				}
			}
		}
	}



	public void CrystalCage(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();


		if(ClassData.pc.get(p.getUniqueId()) == 13 &&cryscg.containsKey(p.getUniqueId())) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
				{
					ev.setCancelled(true);

					if(cryscgt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(cryscgt.get(p.getUniqueId()));
						cryscgt.remove(p.getUniqueId());
					}
					cryscg.remove(p.getUniqueId());

					Location ptl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(3.5)).clone().add(0, -1, 0);
					HashSet<Location> lss = new HashSet<>();
					for(int ix =-2; ix<=2; ix+=1) {
						lss.add(ptl.clone().add(ix, 0, 2));
						lss.add(ptl.clone().add(ix, 0, -2));
						lss.add(ptl.clone().add(-2, 0, ix));
						lss.add(ptl.clone().add(2, 0, ix));
					}
					p.playSound(ptl, Sound.BLOCK_CHAIN_PLACE, 0.3f, 2);
					p.playSound(ptl, Sound.BLOCK_CHAIN_PLACE, 0.3f, 0);

					ArmorStand afs = ptl.getWorld().spawn(ptl, ArmorStand.class);
					afs.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					afs.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					afs.setInvisible(true);
					afs.setCollidable(false);
					afs.getEquipment().setHelmet(new ItemStack(Material.AMETHYST_BLOCK));
					afs.setInvulnerable(true);
					afs.setHeadPose(new EulerAngle(Math.PI/60 , 0, 0));
					afs.setGravity(false);


					p.playSound(p.getLocation(), Sound.BLOCK_NETHER_SPROUTS_PLACE, 1f, 2);
					lss.forEach(l -> {
						ArmorStand as = l.getWorld().spawn(l, ArmorStand.class);
						as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						as.setInvisible(true);
						as.setCollidable(false);
						as.getEquipment().setHelmet(new ItemStack(Material.CHISELED_QUARTZ_BLOCK));
						as.setInvulnerable(true);
						as.setHeadPose(new EulerAngle(Math.PI/60 , 0, 0));
						as.setGravity(false);

						ArmorStand as1 = l.getWorld().spawn(l.clone().add(0,1,0), ArmorStand.class);
						as1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						as1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						as1.setInvisible(true);
						as1.setCollidable(false);
						as1.getEquipment().setHelmet(new ItemStack(Material.BLACKSTONE_WALL));
						as1.setInvulnerable(true);
						as1.setGravity(false);

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								as.remove();
								as1.remove();
								afs.remove();
							}
						},80);
					});

					int bolt = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							p.getWorld().spawnParticle(Particle.ENCHANT, ptl, 200, 3,1.5,3);
							p.getWorld().spawnParticle(Particle.BLOCK, ptl, 300, 1.5,1.5,1.5,getBd(Material.QUARTZ_BRICKS));
							p.getWorld().spawnParticle(Particle.BLOCK, ptl, 300, 4,0.1,4,getBd(Material.BUDDING_AMETHYST));
							p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_PLACE, 0.3f, 0);
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 0.3f, 0);
							for (Entity e : ptl.getWorld().getNearbyEntities(ptl.clone(), 5.5, 4.5, 5.5))
							{
								if (e instanceof Player)
								{
									Player p1 = (Player) e;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											continue;
										}
									}
								}
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
								{
									LivingEntity le = (LivingEntity)e;
									atk0(0.4, wsd.Curse.get(p.getUniqueId())*0.38, p, le);
									le.teleport(ptl);
									Holding.holding(p, le, 40l);
								}
							}

						}
					}, 0,10);

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							Bukkit.getScheduler().cancelTask(bolt);
						}
					}, 80);
				}
			}
		}
	}




	public void WitherScythe(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			final Location el =le.getLocation();




			if(ClassData.pc.get(p.getUniqueId()) == 13 && p.isSneaking() && !p.hasCooldown(Material.YELLOW_TERRACOTTA)) {
				double sec =8*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("구속의낫")
						.ename("WitherScythe")
						.slot(2)
						.hm(stcooldown)
						.skillUse(() -> {
							Holding.superholding(p, le, 60l);
							le.teleport(el);
							p.playSound(p.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 1.0f, 0f);
							p.getWorld().spawnParticle(Particle.WITCH, el, 50, 1,1,1);
							for(int i = 0; i<25; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										atk0(0.181, wsd.WitherScythe.get(p.getUniqueId())*0.17, p, le);
										int addd = Proficiency.getpro(p)>=1 ? 2:0;
										p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.15f, 2f);
										p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, el, 1+addd, 0.5+addd,0.5+addd,0.5+addd);
										p.getWorld().spawnParticle(Particle.WITCH, el, 50, 1,1,1);
										for(Entity e: le.getNearbyEntities(2.5+addd, 2.5+addd, 2.5+addd)) {
											if(e instanceof LivingEntity && e!=p&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
												if (le instanceof Player)
												{

													Player p1 = (Player) le;
													if(Party.hasParty(p) && Party.hasParty(p1))	{
														if(Party.getParty(p).equals(Party.getParty(p1)))
														{
															continue;
														}
													}
												}
												atk0(0.181, wsd.WitherScythe.get(p.getUniqueId())*0.17, p, le);

												le.teleport(el);


											}
										}
									}
								}, i*2+10);

							}
						});
				bd.execute();
				
				
			}
		}
	}



	public void Hover(PlayerDeathEvent d)
	{
		Player p = (Player) d.getEntity();
		if(hovert.containsKey(p.getName())) {
			Bukkit.getScheduler().cancelTask(hovert.get(p.getName()));
			hovert.remove(p.getName());
		}

	}

	public void Hover(PlayerQuitEvent d)
	{
		Player p = d.getPlayer();
		if(hovert.containsKey(p.getName())) {
			Bukkit.getScheduler().cancelTask(hovert.get(p.getName()));
			hovert.remove(p.getName());
		}


	}


	@SuppressWarnings("deprecation")
	public void Hover(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 12*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);


		if(ClassData.pc.get(p.getUniqueId()) == 13 && wsd.Hover.get(p.getUniqueId())>=1 && !p.hasCooldown(CAREFUL)) {
			if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)&& ac != Action.RIGHT_CLICK_AIR&& ac != Action.RIGHT_CLICK_BLOCK)
			{
				if((p.getInventory().getItemInMainHand().getType().name().contains("HOE")) && !(p.isSneaking()) && !(p.isOnGround()) && p.getGameMode()!=GameMode.CREATIVE)
				{
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("부유")
							.ename("Hover")
							.slot(4)
							.hm(smcooldown)
							.skillUse(() -> {
								if(hovert.containsKey(p.getName())) {
									Bukkit.getScheduler().cancelTask(hovert.get(p.getName()));
									hovert.remove(p.getName());
								}

								Long delay = 100l;
								
								p.playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.6f, 1.6f);
								if(Proficiency.getpro(p)>=1) {
									delay = 200l;
								}
								if(Proficiency.getpro(p)>=2) {
									Holding.invur(p, 40l);
								}
								Holding.fly(p, delay);
								int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										p.playSound(p.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 0.005f, 2f);
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 2, false, false));
										p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, p.getLocation(), 5, 0.3,0.3,0.3);
									}
								}, 1,2);
								hovert.put(p.getName(), task);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(hovert.containsKey(p.getName())) {
											Bukkit.getScheduler().cancelTask(hovert.get(p.getName()));
											hovert.remove(p.getName());
										}
									}
								}, delay);
							});
					bd.execute();
				}
			}

		}
	}


	public void ULT(PlayerItemHeldEvent ev)
    {
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		final Location l = p.getLocation();
		final GameMode pgm = p.getGameMode();
		ItemStack is = p.getInventory().getItemInMainHand();


		if(ClassData.pc.get(p.getUniqueId()) == 13 && ev.getNewSlot()==3 && ((is.getType().name().contains("HOE"))) && p.isSneaking()&& Proficiency.getpro(p) >=1)
		{
			ev.setCancelled(true);
			p.setCooldown(CAREFUL, 2);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(66/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("극복")
					.ename("Overcome")
					.slot(6)
					.hm(sultcooldown)
					.skillUse(() -> {
						p.playSound(l, Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1,2);
						p.sendTitle(ChatColor.MAGIC + "SWORDSTORM", null,10,10, 10);
						p.getWorld().spawnParticle(Particle.ENCHANT, p.getLocation(), 200, 6,3,6);
						p.getWorld().spawnParticle(Particle.ITEM_SNOWBALL, p.getLocation(), 200, 6,3,6);
						p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getLocation(), 200, 6,3,6);
						p.getWorld().spawnParticle(Particle.WARPED_SPORE, p.getLocation(), 200, 6,3,6);
						for(Entity e: p.getWorld().getNearbyEntities(l,15, 30, 15)) {
							if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p&& !(e.hasMetadata("portal"))) {
								LivingEntity le = (LivingEntity)e;
								if (le instanceof Player)
								{
									Player p1 = (Player) le;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											continue;
										}
									}
								}
								le.setRemoveWhenFarAway(false);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										if(!le.hasMetadata("raid")) {
											le.setRemoveWhenFarAway(true);
										}
									}
								}, 120);
								Holding.superholding(p, le, 80l);
							}
						}
						p.teleport(p.getLocation().add(0, 300, 0));
						p.playSound(l, Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1,2);
						p.playSound(l, Sound.AMBIENT_SOUL_SAND_VALLEY_LOOP, 1,2);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 255, false, false));
						Wither overcome = (Wither) p.getWorld().spawnEntity(p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(3)), EntityType.WITHER);
						overcome.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						overcome.setAI(false);
						overcome.getBossBar().setVisible(false);
						overcome.setGlowing(true);
						for(int i=0;i<30;i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								BlockData bd = getBd(Material.CHISELED_QUARTZ_BLOCK);
								@Override
								public void run()
								{
									p.playSound(l, Sound.PARTICLE_SOUL_ESCAPE, 1,0);
									p.playSound(l, Sound.ENTITY_WITHER_HURT, 1,0);
									p.getWorld().spawnParticle(Particle.ENCHANT, p.getLocation(), 10, 2,2,2);
									p.getWorld().spawnParticle(Particle.BLOCK, p.getLocation(), 10, 2,2,2,1,bd);
									p.getWorld().spawnParticle(Particle.ITEM_SNOWBALL, overcome.getLocation(), 10, 2,2,2);
									p.getWorld().spawnParticle(Particle.WHITE_ASH, overcome.getLocation(), 10, 2,2,2);
									p.getWorld().spawnParticle(Particle.WARPED_SPORE, overcome.getLocation(), 10, 2,2,2);
								}
							}, i*2);

						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.setGameMode(GameMode.SPECTATOR);
								p.setSpectatorTarget(overcome);
							}
						}, 30);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.playSound(overcome.getLocation(), Sound.ENTITY_WITHER_DEATH, 1,1);
								p.playSound(overcome.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.8f,1);
								p.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, overcome.getLocation(), 1, 1,1,1);
								p.getWorld().spawnParticle(Particle.ITEM_SNOWBALL, overcome.getLocation(), 200, 6,3,6);
								p.getWorld().spawnParticle(Particle.WHITE_ASH, overcome.getLocation(), 200, 6,3,6);
								p.getWorld().spawnParticle(Particle.WARPED_SPORE, overcome.getLocation(), 200, 6,3,6);
								p.setSpectatorTarget(null);
								p.setGameMode(pgm);
								overcome.remove();
								p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 200, 6,3,6);

							}
						}, 60);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 200, 6,3,6);
								p.playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 0.8f,1);
								p.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, l, 1, 1,1,1);
								p.getWorld().spawnParticle(Particle.ITEM_SNOWBALL, l, 200, 6,3,6);
								p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 200, 6,3,6);
								p.getWorld().spawnParticle(Particle.WARPED_SPORE, l, 200, 6,3,6);
								p.teleport(l);
								for(Entity e: p.getWorld().getNearbyEntities(l,10, 30, 10)) {
									if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
										LivingEntity le = (LivingEntity)e;
										if (le instanceof Player)
										{
											Player p1 = (Player) le;
											if(Party.hasParty(p) && Party.hasParty(p1))	{
												if(Party.getParty(p).equals(Party.getParty(p1)))
												{
													continue;
												}
											}
										}
										atk1(16.0, p, le);

									}
								}

							}
						}, 65);

					});
			bd.execute();
			
		}
	}

	final private HashSet<Location> WitherShape(Location il) {

		HashSet<Location> lll = new HashSet<>();
		Location one = il.clone().add(0, -2, 0);
		Location two = il.clone().add(0, 11, 0);
		Location two1 = il.clone().add(-5.5, 11, 0);
		Location two2 = il.clone().add(5.5, 11, 0);
		for(int i = 0; i<10; i++ ) {
			for(double ii = 0; ii<i; ii+=0.25 ) {
				lll.add(one.clone().add(ii/1.5,i,0));
				lll.add(one.clone().add(-ii/1.5,i,0));
			}
		}
		for(double i = -2.5; i<2.5; i+=0.5 ) {
			for(double i1 = -2.5; i1<2.5; i1+=0.5 ) {
				for(double i11 = -2.5; i11<2.5; i11+=0.5 ) {
					lll.add(two.clone().add(i11,i,i1));
					lll.add(two1.clone().add(i11,i,i1));
					lll.add(two2.clone().add(i11,i,i1));
				}

			}
		}
		return lll;

	}


	public void ULT2(PlayerItemHeldEvent ev)
    {
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();

		if(ClassData.pc.get(p.getUniqueId()) == 13&& ev.getNewSlot()==4 && ((is.getType().name().contains("HOE"))) && p.isSneaking() && Proficiency.getpro(p) >=2)
		{
			ev.setCancelled(true);
			p.setCooldown(CAREFUL, 2);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(80/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("정복")
					.ename("OverThrow")
					.slot(7)
					.hm(sult2cooldown)
					.skillUse(() -> {

						Location ptl = gettargetblock(p,6);



						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 2, false, false));
						p.playSound(ptl, Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1,2);
						p.playSound(ptl, Sound.ENTITY_WITHER_AMBIENT, 1,2);

						Holding.superholding(p,p, 20l);
						Holding.invur(p, 60l);
						WitherShape(ptl.clone()).forEach(l -> {
							l.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, l, 20, 0.25,0.25,0.25);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									l.getWorld().spawnParticle(Particle.END_ROD, l, 10, 0.5,0.5,0.5);
									l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l, 3, 0.5,0.5,0.5);
								}
							}, 60);
						});

						for(Entity e: p.getWorld().getNearbyEntities(ptl,25, 25, 25)) {
							if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p&& !(e.hasMetadata("portal"))) {
								LivingEntity le = (LivingEntity)e;
								if (le instanceof Player)
								{
									Player p1 = (Player) le;
									if(Party.hasParty(p) && Party.hasParty(p1))	{
										if(Party.getParty(p).equals(Party.getParty(p1)))
										{
											continue;
										}
									}
								}
								le.setRemoveWhenFarAway(false);
								le.teleport(ptl);
								Holding.superholding(p, le, 100l);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										atk1(26.0, p, le);
									}
								}, 60);
							}
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.playSound(ptl, Sound.ENTITY_WITHER_DEATH, 1,0);
								p.playSound(ptl, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1,0);
								p.playSound(ptl, Sound.ENTITY_WITHER_HURT, 1,0);
							}
						}, 60);
					});
			bd.execute();
		}
	}




	@SuppressWarnings("deprecation")
	public void ThrowCancel(PlayerDropItemEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		Item it = ev.getItemDrop();
		ItemStack is = it.getItemStack();

		if(ClassData.pc.get(p.getUniqueId()) == 13 && (is.getType().name().contains("HOE")) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
		{
			ev.setCancelled(true);
		}

	}




	public void Equipment(PlayerItemDamageEvent e)
	{
		Player p = e.getPlayer();




		if(ClassData.pc.get(p.getUniqueId()) == 13)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("HOE") )
			{
				e.setCancelled(true);
			}
		}

	}



	public void Witherize(EntityDamageByEntityEvent d)
	{

		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && d.getDamage()>0)
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();




			if(ClassData.pc.get(p.getUniqueId()) == 13) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
				{
					if(le.getType().name().contains("WITHER")) {
						dset2(d, p, 2.5*(1+wsd.Witherize.get(p.getUniqueId())*0.0453), le, 8);
					}
					else {
						dset2(d, p, 1.25*(1+wsd.Witherize.get(p.getUniqueId())*0.0453), le, 8);
						le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 0, false, false));
					}

				}
			}
		}


		if(d.getDamager() instanceof WitherSkull && d.getEntity() instanceof LivingEntity)
		{
			WitherSkull ws = (WitherSkull)d.getDamager();

			if(ws.getShooter() instanceof Player) {
				Player p = (Player)ws.getShooter();


				if(ClassData.pc.get(p.getUniqueId()) == 13 && ws.hasMetadata("ws of"+p.getName())) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
					{

						d.setCancelled(true);


					}
				}
			}
		}

		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity)
		{
			Projectile ar = (Projectile)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player)ar.getShooter();




				if(ClassData.pc.get(p.getUniqueId()) == 13) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("HOE"))
					{

						if(le.getType().name().contains("WITHER")) {
							dset2(d, p, 2.5*(1+wsd.Witherize.get(p.getUniqueId())*0.0453), le, 8);
						}
						else {
							dset2(d, p, 1.25*(1+wsd.Witherize.get(p.getUniqueId())*0.0453), le, 8);
							le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 0, false, false));
						}

					}
				}
			}
		}
	}



	public void Witherize2(EntityDamageByEntityEvent d)
	{
		if(d.getEntity() instanceof Player && d.getDamager().getType().name().contains("WITHER"))
		{
			Player p = (Player)d.getEntity();



			if(ClassData.pc.get(p.getUniqueId()) == 13)
			{
				if(Proficiency.getpro(p)>=2) {
					d.setCancelled(true);
				}
				d.setDamage(d.getDamage()*0.25);
				if(p.hasPotionEffect(PotionEffectType.WITHER)) {
					p.removePotionEffect(PotionEffectType.WITHER);
				}
			}
		}
	}


	public void Witherized(EntityDamageEvent d)
	{
		if (!(d.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) d.getEntity();



		if(d.getCause().equals(DamageCause.WITHER))
		{
			if(ClassData.pc.get(p.getUniqueId()) == 13) {
				d.setCancelled(true);
			}
		}
	}

}