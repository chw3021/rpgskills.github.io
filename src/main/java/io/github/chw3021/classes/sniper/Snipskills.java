package io.github.chw3021.classes.sniper;




import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.data.BlockData;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Breeze;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Illager;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PiglinAbstract;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Snipskills extends Pak {

	private HashMap<String, Long> wrcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> arcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> dpcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cwcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> bultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> bult2cooldown = new HashMap<String, Long>();

	private HashMap<UUID, Integer> shckarw = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> shckarwt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> dtyr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> dtyrt = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> smkshl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> smkshlt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> dgcl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> dgclt = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> sz = new HashMap<UUID, Integer>();
	private String path = new File("").getAbsolutePath();
	Holding hold = Holding.getInstance();
	private SnipSkillsData ssd;

	private static final Snipskills instance = new Snipskills ();
	public static Snipskills getInstance()
	{
		return instance;
	}


	public void er(PluginEnableEvent ev)
	{
		SnipSkillsData s = new SnipSkillsData(SnipSkillsData.loadData(path +"/plugins/RPGskills/SnipskillsData.data"));
		ssd = s;
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
				if(sz.containsKey(p.getUniqueId())) {
					Bukkit.getServer().getScheduler().cancelTask(sz.get(p.getUniqueId()));
					sz.remove(p.getUniqueId());
				}
			}

		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Snipskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				SnipSkillsData s = new SnipSkillsData(SnipSkillsData.loadData(path +"/plugins/RPGskills/SnipskillsData.data"));
				ssd = s;
			}

		}
	}


	public void nepreventer(PlayerJoinEvent ev)
	{
		SnipSkillsData s = new SnipSkillsData(SnipSkillsData.loadData(path +"/plugins/RPGskills/SnipskillsData.data"));
		ssd = s;
	}


	@SuppressWarnings("deprecation")
	public void Rope(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =2*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);



		if(ClassData.pc.get(p.getUniqueId()) == 4 && ssd.Rope.getOrDefault(p.getUniqueId(), 0)>=1 && !p.hasCooldown(CAREFUL)) {
			if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.isOnGround() && !p.isSneaking())
			{


				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW && ssd.Rope.get(p.getUniqueId())>=1)
				{
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("밧줄타기")
							.ename("Rope")
							.slot(0)
							.hm(wrcooldown)
							.skillUse(() -> {
								ev.setCancelled(true);


								p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_START, 1.0f, 0.5f);
								p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 1.0f, 0.5f);
								ArrayList<Location> line = new ArrayList<Location>();
								AtomicInteger j = new AtomicInteger(0);
								Boolean lb = false;
								for(double d = 0.1; d <= 18; d += 0.1) {
									Location pl = p.getEyeLocation().clone();
									pl.add(pl.getDirection().normalize().multiply(d));
									if(!pl.getBlock().isPassable()) {
										lb = true;
										break;
									}
									line.add(pl);
								}
								line.forEach(i -> {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											p.getWorld().spawnParticle(Particle.BLOCK ,i, 3, 0.2,0.2,0.2,0, Bukkit.createBlockData(Material.BIRCH_FENCE));
										}
									}, j.getAndIncrement()/15);
								});
								if(lb) {

									if(Proficiency.getpro(p)>=1) {
										List<ItemStack> arrows = new ArrayList<ItemStack>();
										ItemStack far = new ItemStack(Material.ARROW);
										ItemStack cb = p.getInventory().getItemInMainHand();
										CrossbowMeta cbm = (CrossbowMeta) cb.getItemMeta();
										p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_QUICK_CHARGE_3, 1, 2);
										if(!cbm.hasChargedProjectiles() && p.getInventory().contains(Material.ARROW,1)) {
											arrows.add(far);
											if(cb.containsEnchantment(Enchantment.MULTISHOT)) {
												arrows.add(far);
												arrows.add(far);
											}
											cbm.setChargedProjectiles(arrows);
											cb.setItemMeta(cbm);
											p.getInventory().removeItem( new ItemStack(Material.ARROW,1));
											p.updateInventory();
										}
									}
									line.forEach(i -> {
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run()
											{
												p.teleport(i);
												p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 123, false, false));
											}
										}, j.getAndIncrement()/15);
									});
								}

							});
					bd.execute();
				}
			}
		}
	}



	public void swcancle(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();



		if(ClassData.pc.get(p.getUniqueId()) == 4) {
			if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
			{
				ev.setCancelled(true);
			}
		}
	}

	public void ArmourPiercingArrow(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();



		if(ClassData.pc.get(p.getUniqueId()) == 4 && ssd.ArmourPiercingArrow.getOrDefault(p.getUniqueId(), 0)>=1) {
			double sec = 6*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			if(((p.isSneaking()) && !(p.isSprinting())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("철갑화살")
							.ename("APA")
							.slot(1)
							.hm(arcooldown)
							.skillUse(() -> {
								if(shckarwt.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(shckarwt.get(p.getUniqueId()));
									shckarwt.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											shckarw.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										shckarw.remove(p.getUniqueId());
									}
								}, 25);
								shckarwt.put(p.getUniqueId(), task);


								p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 2f);
								p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 2f);
								Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 20, 1);
								ar.setShooter(p);
								ar.setWeapon(p.getInventory().getItemInMainHand());
								ar.setCritical(true);
								ar.setPierceLevel(127);
								ar.setMetadata("APA", new FixedMetadataValue(RMain.getInstance(), true));
								p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
								ar.setDamage(ar.getDamage()*(1+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.2));
							});
					bd.execute();

				}
			}
		}
	}


	public void APA(ProjectileHitEvent ev)
	{

		if(ev.getEntity().getShooter() instanceof Player && ev.getEntity() instanceof Arrow)
		{
			Player p = (Player)ev.getEntity().getShooter();

			if(ClassData.pc.get(p.getUniqueId()) == 4) {
				if(ev.getHitEntity() instanceof LivingEntity) {
					LivingEntity le =(LivingEntity) ev.getHitEntity();
					if (le instanceof Player)
					{

						Player p1 = (Player) le;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								return;
							}
						}
					}
					if(le.hasMetadata("fake") || le.hasMetadata("portal")) {
						return;
					}
					Arrow ar = (Arrow) ev.getEntity();
					if(ar.hasMetadata("APA") && !ar.hasMetadata("ShockArrow")) {
						le.setHealth(le.getHealth()*(1-(0.01+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.001)));
						atkab0(0d,le.getHealth()*0.01+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.001,p,le);
					}
				}
			}
		}
	}


	public void ShockArrow(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 4 && shckarw.containsKey(p.getUniqueId())) {
			if(((p.isSneaking()) && !(p.isSprinting())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					ev.setCancelled(true);

					if(shckarwt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(shckarwt.get(p.getUniqueId()));
						shckarwt.remove(p.getUniqueId());
					}
					shckarw.remove(p.getUniqueId());

					if(dtyrt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(dtyrt.get(p.getUniqueId()));
						dtyrt.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								dtyr.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 3);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							dtyr.remove(p.getUniqueId());
						}
					}, 25);
					dtyrt.put(p.getUniqueId(), task);


					p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 1.5f);
					p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1.5f);
					Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 20, 1);
					ar.setShooter(p);
					ItemStack bow = p.getInventory().getItemInMainHand().clone();
					bow.addUnsafeEnchantment(Enchantment.PUNCH, 5);
					ar.setWeapon(bow);
					ar.setCritical(true);
					ar.setPierceLevel(0);
					ar.setMetadata("APA", new FixedMetadataValue(RMain.getInstance(), true));
					ar.setMetadata("ShockArrow", new FixedMetadataValue(RMain.getInstance(), true));
					p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
					ar1(ar, p,0.3*(1+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.06));
				}
			}
		}
	}

	public void ShockArrow(ProjectileHitEvent ev)
	{

		if(ev.getEntity().getShooter() instanceof Player && ev.getEntity() instanceof Arrow)
		{
			Player p = (Player)ev.getEntity().getShooter();

			if(ClassData.pc.get(p.getUniqueId()) == 4) {
				if(ev.getHitEntity() instanceof LivingEntity) {
					LivingEntity le =(LivingEntity) ev.getHitEntity();
					if (le instanceof Player)
					{

						Player p1 = (Player) le;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								return;
							}
						}
					}
					if(le.hasMetadata("fake") || le.hasMetadata("portal")) {
						return;
					}
					Arrow ar = (Arrow) ev.getEntity();
					if(ar.hasMetadata("ShockArrow")) {
						Holding.holding(p, le, 30l);
					}
				}
			}
		}
	}



	public void Destroyer(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 4 && dtyr.containsKey(p.getUniqueId())) {
			if(((p.isSneaking()) && !(p.isSprinting())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					ev.setCancelled(true);

					if(dtyrt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(dtyrt.get(p.getUniqueId()));
						dtyrt.remove(p.getUniqueId());
					}
					dtyr.remove(p.getUniqueId());

					p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 2f);
					Firework fr = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK_ROCKET);
					FireworkEffect effect = FireworkEffect.builder()
							.with(Type.BALL)
							.withColor(Color.GRAY)
							.flicker(false)
							.trail(true)
							.build();
					fr.setShooter(p);
					fr.setShotAtAngle(true);
					fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					fr.setMetadata("destroyer", new FixedMetadataValue(RMain.getInstance(), true));
					FireworkMeta meta = fr.getFireworkMeta();
					meta.setPower(3);
					meta.addEffect(effect);
					fr.setFireworkMeta(meta);
					fr.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(3));
					fr.setPersistent(true);
				}
			}
		}
	}

	public void Destroyer(FireworkExplodeEvent f)
	{
		if(f.getEntity().getShooter() instanceof Player && f.getEntity().hasMetadata("destroyer")) {
			Firework fr = f.getEntity();
			Player p = (Player)fr.getShooter();
			Location frl = fr.getLocation();
			World w = frl.getWorld();
			w.spawnParticle(Particle.EXPLOSION, frl, 10,3,3,3);
			w.spawnParticle(Particle.SMOKE, frl, 100,3,3,3);
			w.playSound(frl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1f, 0.32f);

			for(Entity e : fr.getWorld().getNearbyEntities(fr.getLocation(), 3,3, 3)) {
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
				if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
					LivingEntity le = (LivingEntity)e;
					atk1(1.3*(1+ssd.ArmourPiercingArrow.get(p.getUniqueId())*0.1)*(1+le.getAttribute(Attribute.ARMOR).getValue()*0.01), p, le);
					Holding.holding(p, le, 30l);
				}
			}
			f.setCancelled(true);
			fr.remove();
		}
	}

	@SuppressWarnings("deprecation")
	public void FlashBomb(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();



		if(ClassData.pc.get(p.getUniqueId()) == 4 && ssd.FlashBomb.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(!p.isOnGround() && !p.isSneaking())
			{
				double sec =10*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("섬광탄")
							.ename("FlashBomb")
							.slot(2)
							.hm(dpcooldown)
							.skillUse(() -> {
								if(smkshlt.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(smkshlt.get(p.getUniqueId()));
									smkshlt.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											smkshl.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										smkshl.remove(p.getUniqueId());
									}
								}, 30);
								smkshlt.put(p.getUniqueId(), task);

								Snowball sn = (Snowball) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.SNOWBALL);
								sn.setItem(new ItemStack(Material.GLOW_BERRIES));
								sn.setShooter(p);
								sn.setVelocity(sn.getVelocity().add(p.getLocation().getDirection().normalize().multiply(0.75)));
								sn.setPersistent(true);
								Location tl = p.getLocation();
								tl.setDirection(tl.getDirection().normalize().rotateAroundY(Math.PI));
								tl.add(tl.getDirection().normalize().multiply(4));
								tl.setDirection(p.getLocation().getDirection());
								if(!tl.getBlock().isPassable())
								{tl.add(0, 1, 0);}
								if(tl.getBlock().isPassable())
								{p.teleport(tl);}
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										p.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, sn.getLocation(), 2,1,1,1);
										p.getWorld().spawnParticle(Particle.FLASH, sn.getLocation(), 20,5,5,5);
										p.playSound(sn.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 2);
										for(Entity e : sn.getNearbyEntities(5, 5, 5)) {
											if(e instanceof LivingEntity && e!=p) {
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
												LivingEntity le = (LivingEntity) e;
												atk1(0.5*(1+ssd.FlashBomb.get(p.getUniqueId())*0.0687), p, le);
												le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 56, 4, false, false));
												Holding.holding(p, le, 10l);
											}
										}
										sn.setPersistent(false);
										sn.remove();
									}
								}, 35);
							});
					bd.execute();
				}
			}
		}
	}


	@SuppressWarnings("deprecation")
	public void SmokeShell(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 4 && smkshl.containsKey(p.getUniqueId())) {
			if(!p.isOnGround() && !p.isSneaking())
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					ev.setCancelled(true);

					if(smkshlt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(smkshlt.get(p.getUniqueId()));
						smkshlt.remove(p.getUniqueId());
					}
					smkshl.remove(p.getUniqueId());



					if(dgclt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(dgclt.get(p.getUniqueId()));
						dgclt.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								dgcl.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 5);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							dgcl.remove(p.getUniqueId());
						}
					}, 40);
					dgclt.put(p.getUniqueId(), task);


					Snowball sn = (Snowball) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.SNOWBALL);
					sn.setItem(new ItemStack(Material.SMOKER));
					sn.setShooter(p);
					sn.setVelocity(sn.getVelocity().add(p.getLocation().getDirection().normalize().multiply(0.75)));
					sn.setPersistent(true);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							p.playSound(sn.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 0.4f, 0);
							p.playSound(sn.getLocation(), Sound.BLOCK_SMOKER_SMOKE, 0.4f, 2);

							for(int count = 0 ; count <40+ssd.FlashBomb.get(p.getUniqueId())*0.1; count++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										p.getWorld().spawnParticle(Particle.WHITE_ASH, sn.getLocation(), 300,5,5,5,0);
										p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, sn.getLocation(), 100,5,0.1,5,0.1);


										for (Entity e : sn.getWorld().getNearbyEntities(sn.getLocation().clone(), 6, 6, 6))
										{
											if (e ==p && p.isSneaking())
											{
												Holding.invur(p, 12l);
											}
										}
									}
								}, count*2);
							}
						}
					}, 40);


				}
			}
		}
	}


	@SuppressWarnings("deprecation")
	public void DangerClose(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 4 && dgcl.containsKey(p.getUniqueId())) {
			if(!p.isOnGround() && !p.isSneaking())
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					ev.setCancelled(true);

					if(dgclt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(dgclt.get(p.getUniqueId()));
						dgclt.remove(p.getUniqueId());
					}
					dgcl.remove(p.getUniqueId());


					Item sn = (Item) p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.REDSTONE_TORCH));
					sn.setOwner(p.getUniqueId());
					sn.setGlowing(true);
					sn.setPickupDelay(999999);
					sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					sn.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					sn.setVelocity(sn.getVelocity().add(p.getLocation().getDirection().normalize().multiply(0.75)));

					p.playSound(sn.getLocation(), Sound.BLOCK_CALCITE_FALL, 1, 0);
					p.playSound(sn.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1, 2);

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							p.playSound(sn.getLocation().clone().add(0, 5, 0), Sound.ENTITY_ENDERMAN_STARE, 1f, 2);
							for(int count = 0 ; count <12; count++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{

										Random ran = new Random();
										double ri1 = ran.nextDouble()*3 * (ran.nextBoolean() ? 1:-1);
										double ri2 = ran.nextDouble()*3 * (ran.nextBoolean() ? 1:-1);


										FallingBlock fallingb = p.getWorld().spawnFallingBlock(sn.getLocation().clone().add(ri1, 6, ri2), Material.LANTERN.createBlockData());
										fallingb.setInvulnerable(true);
										fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
										fallingb.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(),p.getName()));
										fallingb.setMetadata("dangerclose", new FixedMetadataValue(RMain.getInstance(),p.getName()));
										fallingb.setVisualFire(true);
										fallingb.setGlowing(true);
										fallingb.setDropItem(true);
										fallingb.setHurtEntities(true);
									}
								}, count*5);
							}
							sn.remove();
						}
					}, 40);
				}
			}
		}
	}





	public void DangerClose(EntityDropItemEvent ev)
	{
		if(ev.getEntity() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getEntity();
			if(fallingb.hasMetadata("dangerclose")){
				ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("dangerclose").get(0).asString());
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION, tl, 2,1,1,1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 4, 4, 4))
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
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
						atk1(0.34*(1 + ssd.FlashBomb.get(p.getUniqueId())*0.04), p, le);
						Holding.holding(p, le, 10l);
					}

				}
				p.playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.5f, 0);
				fallingb.remove();
			}
		}
	}



	public void DangerClose(EntityDamageByEntityEvent ev)
	{
		if(ev.getDamager() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getDamager();
			if(fallingb.hasMetadata("dangerclose")){
				ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("dangerclose").get(0).asString());
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION, tl, 2,1,1,1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 4, 4, 4))
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
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
						atk1(0.34*(1 + ssd.FlashBomb.get(p.getUniqueId())*0.04), p, le);
						Holding.holding(p, le, 10l);
					}

				}
				p.playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.5f, 0);
				fallingb.remove();
			}
		}
	}


	public void DangerClose(EntityChangeBlockEvent ev)
	{
		if(ev.getEntity() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getEntity();
			if(fallingb.hasMetadata("dangerclose")){
				ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("dangerclose").get(0).asString());
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION, tl, 2,1,1,1);

				for (Entity e : p.getWorld().getNearbyEntities(tl, 4, 4, 4))
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
					if(p!=e && e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
						LivingEntity le = (LivingEntity)e;
						atk1(0.34*(1 + ssd.FlashBomb.get(p.getUniqueId())*0.04), p, le);
						Holding.holding(p, le, 10l);
					}

				}
				p.playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.5f, 0);
				fallingb.remove();
			}
		}
	}


	public void Flare(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();



		if(ClassData.pc.get(p.getUniqueId()) == 4 && ssd.Flare.getOrDefault(p.getUniqueId(),0)>=1 && !p.hasCooldown(CAREFUL)) {
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) && p.isSneaking())
			{

				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{
					double sec = 35*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("조명지뢰")
							.ename("Flare")
							.slot(3)
							.hm(cscooldown)
							.skillUse(() -> {
								Firework fr = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK_ROCKET);
								fr.setShotAtAngle(true);
								fr.setVelocity(p.getLocation().getDirection().multiply(2));
								fr.setShooter(p);
								fr.setMetadata("flare", new FixedMetadataValue(RMain.getInstance(), true));
								fr.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								ev.setCancelled(true);

								FireworkEffect effect = FireworkEffect.builder()
										.with(Type.BALL_LARGE)
										.withColor(Color.YELLOW)
										.flicker(true)
										.trail(true)
										.build();
								FireworkMeta meta = fr.getFireworkMeta();
								meta.setPower(1);
								meta.addEffect(effect);
								fr.setFireworkMeta(meta);
							});
					bd.execute();
				}
			}
		}
	}




	public void Camouflage(PlayerToggleSneakEvent e)
	{

		Player p = (Player)e.getPlayer();
		Location l = p.getLocation();



		if(ClassData.pc.get(p.getUniqueId()) == 4){

			if(p.getInventory().getItemInMainHand().getType().name().contains("CROSSBOW") && ssd.Camouflage.getOrDefault(p.getUniqueId(),0)>=1 && e.isSneaking())
			{
				p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 10, 1, 1, 1);
				int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						p.setSprinting(false);
						p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 6, 0, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 6, 150, false, false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 6, 0, false, false));
						if(Proficiency.getpro(p)>=2) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 6, 128, false, false));
						}
					}
				}, 0, 5);
				sz.put(p.getUniqueId(), task);
			}
			else
			{
				if(sz.containsKey(p.getUniqueId())) {
					Bukkit.getServer().getScheduler().cancelTask(sz.get(p.getUniqueId()));
					sz.remove(p.getUniqueId());
				}

			}
		}

	}


	public void Camouflage(PlayerItemHeldEvent ev)
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 4 && p.getInventory().getItemInMainHand().getType().name().contains("CROSSBOW") && sz.containsKey(p.getUniqueId())){

			if(Proficiency.getpro(p)>=1 && ev.getNewSlot()!=3&&ev.getNewSlot()!=4) {
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(1d)
						.kname("포복")
						.ename("Crawl")
						.slot(4)
						.hm(cwcooldown)
						.skillUse(() -> {
							p.setGliding(true);
							final Location pfel = p.getEyeLocation().clone();
							final Location pfl = p.getLocation().clone().add(0, 0.25, 0);
							if(ev.getPreviousSlot()==0) {
								if(ev.getNewSlot()==8) {
									for(int i = 0; i<20; i++) {
										if(pfl.clone().add(pfel.clone().getDirection().multiply(0.1*i)).getBlock().isPassable()) {
											p.teleport(pfl.clone().add(pfel.clone().getDirection().multiply(0.1*i)).setDirection(pfel.getDirection()));
										}
									}
								}
							}
							else if(ev.getPreviousSlot()==8) {
								if(ev.getNewSlot()!=0) {
									for(int i = 0; i<20; i++) {
										if(pfl.clone().add(pfel.clone().getDirection().multiply(0.1*i)).getBlock().isPassable()) {
											p.teleport(pfl.clone().add(pfel.clone().getDirection().multiply(0.1*i)).setDirection(pfel.getDirection()));
										}
									}
								}
							}
							else {
								if(ev.getNewSlot()<ev.getPreviousSlot()) {
									for(int i = 0; i<20; i++) {
										if(pfl.clone().add(pfel.clone().getDirection().multiply(0.1*i)).getBlock().isPassable()) {
											p.teleport(pfl.clone().add(pfel.clone().getDirection().multiply(0.1*i)).setDirection(pfel.getDirection()));
										}
									}
								}
							}
							p.setGliding(true);

							if(Proficiency.getpro(p)>=2) {
								List<ItemStack> arrows = new ArrayList<ItemStack>();
								ItemStack far = new ItemStack(Material.ARROW);
								ItemStack cb = p.getInventory().getItemInMainHand();
								CrossbowMeta cbm = (CrossbowMeta) cb.getItemMeta();
								p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_QUICK_CHARGE_3, 1, 2);
								if(!cbm.hasChargedProjectiles() && p.getInventory().contains(Material.ARROW,1)) {
									if(cb.containsEnchantment(Enchantment.MULTISHOT)) {
										arrows.add(far);
										arrows.add(far);
									}
									arrows.add(far);
									cbm.setChargedProjectiles(arrows);
									cb.setItemMeta(cbm);
									p.getInventory().removeItem( new ItemStack(Material.ARROW,1));
									p.updateInventory();
								}
							}

						});
				bd.execute();
			}
			else {
				Bukkit.getServer().getScheduler().cancelTask(sz.get(p.getUniqueId()));
				sz.remove(p.getUniqueId());
			}
		}
	}


	@SuppressWarnings("deprecation")
	public void AirStrike(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{
			Projectile a = (Projectile) d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			final Location lel = le.getLocation();
			if(a.getShooter() instanceof Player && !le.hasMetadata("fake")&& !le.hasMetadata("portal"))
			{

				Player p = (Player) a.getShooter();


				if(ClassData.pc.get(p.getUniqueId()) == 4 && ssd.AirStrike.getOrDefault(p.getUniqueId(), 0)>=1) {
					if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
					{

						if (le instanceof Player)
						{
							Player p1 = (Player) le;
							if(Party.hasParty(p) && Party.hasParty(p1))	{
								if(Party.getParty(p).equals(Party.getParty(p1)))
								{
									return;
								}
							}
						}
						Location el = le.getLocation().add(0,3.5,0);
						ArrayList<Location> des = new ArrayList<>();
						for(int i=0; i<3; i++) {
							Random random=new Random();
							double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
							double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
							Location ell = el.clone().add(number, 0.5, number2);
							des.add(ell.setDirection(lel.toVector().subtract(ell.toVector())));
						}
						des.forEach(l -> {
							Firework fr = (Firework) p.getWorld().spawnEntity(l, EntityType.FIREWORK_ROCKET);
							fr.setVelocity(l.getDirection().multiply(0.6));
							FireworkEffect effect = FireworkEffect.builder()
									.with(Type.BALL)
									.withColor(Color.RED)
									.flicker(true)
									.trail(true)
									.build();
							fr.setShooter(p);
							fr.setShotAtAngle(true);
							fr.setMetadata("airstrike", new FixedMetadataValue(RMain.getInstance(), true));
							FireworkMeta meta = fr.getFireworkMeta();
							meta.setPower(5);
							meta.addEffect(effect);
							fr.setFireworkMeta(meta);
						});

						if(Proficiency.getpro(p)>=1) {

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									p.playSound(p.getLocation().clone().add(3, 3, 3), Sound.ITEM_CROSSBOW_SHOOT, 1f, 2f);
									p.playSound(p.getLocation().clone().add(3, 3, 3), Sound.ENTITY_ARROW_SHOOT, 1f, 2f);
									p.playSound(le.getLocation(), Sound.ENTITY_ARROW_HIT, 1f, 2f);
									atk1(0.7*ssd.AirStrike.get(p.getUniqueId())*0.05, p, le);
									Holding.holding(p, le, 2l);
								}
							},	 5);
						}
					}
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{

			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			final Location lel = le.getLocation();


			if(ClassData.pc.get(p.getUniqueId()) == 4 && ssd.AirStrike.getOrDefault(p.getUniqueId(), 0)>=1 && (le.getType()==EntityType.ENDERMAN || (le.getType() == EntityType.WITHER && le.getHealth()<=le.getMaxHealth()*0.5))) {
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW && !p.hasCooldown(MELEE))
				{

					if (le instanceof Player)
					{
						Player p1 = (Player) le;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								return;
							}
						}
					}
					Location el = le.getLocation().add(0,3.5,0);
					ArrayList<Location> des = new ArrayList<>();
					for(int i=0; i<3; i++) {
						Random random=new Random();
						double number = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
						double number2 = random.nextDouble() * 2 * (random.nextBoolean() ? -1 : 1);
						Location ell = el.clone().add(number, 0.5, number2);
						des.add(ell.setDirection(lel.toVector().subtract(ell.toVector())));
					}
					des.forEach(l -> {
						Firework fr = (Firework) p.getWorld().spawnEntity(l, EntityType.FIREWORK_ROCKET);
						fr.setVelocity(l.getDirection().multiply(0.6));
						FireworkEffect effect = FireworkEffect.builder()
								.with(Type.BALL)
								.withColor(Color.RED)
								.flicker(true)
								.trail(true)
								.build();
						fr.setShooter(p);
						fr.setShotAtAngle(true);
						fr.setMetadata("airstrike", new FixedMetadataValue(RMain.getInstance(), true));
						FireworkMeta meta = fr.getFireworkMeta();
						meta.setPower(5);
						meta.addEffect(effect);
						fr.setFireworkMeta(meta);
					});

					if(Proficiency.getpro(p)>=1) {

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.playSound(p.getLocation().clone().add(3, 3, 3), Sound.ITEM_CROSSBOW_SHOOT, 1f, 2f);
								p.playSound(p.getLocation().clone().add(3, 3, 3), Sound.ENTITY_ARROW_SHOOT, 1f, 2f);
								p.playSound(le.getLocation(), Sound.ENTITY_ARROW_HIT, 1f, 2f);
								atk1(0.7*ssd.AirStrike.get(p.getUniqueId())*0.05, p, le);
								Holding.holding(p, le, 2l);
							}
						},	 5);
					}
				}
			}
		}
	}



	public void FireworkExplode(FireworkExplodeEvent f)
	{
		if(f.getEntity().getShooter() instanceof Player && f.getEntity().hasMetadata("airstrike")) {
			Firework fr = f.getEntity();
			Player p = (Player)fr.getShooter();
			Location frl = fr.getLocation();
			World w = frl.getWorld();
			w.spawnParticle(Particle.EXPLOSION, frl, 1);
			w.spawnParticle(Particle.SMOKE, frl, 30,1,1,1);
			w.playSound(frl, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1f, 0.32f);
			w.playSound(frl, Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1f, 0.2f);
			f.setCancelled(true);
			fr.remove();

			for(Entity e : w.getNearbyEntities(frl, 1.82,1.82, 1.82)) {
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
				if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
					LivingEntity le = (LivingEntity)e;
					atk1(0.4*(1+ssd.AirStrike.get(p.getUniqueId())*0.0321), p, le);
				}
			}
		}

		if (f.getEntity().getShooter() instanceof Player && f.getEntity().hasMetadata("flare")) {
		    Firework fr = f.getEntity();
		    Player p = (Player) fr.getShooter();
		    for (Entity e : f.getEntity().getNearbyEntities(30, 30, 30)) {
		        if (e instanceof Player) {
		            Player p1 = (Player) e;
		            if (Party.hasParty(p) && Party.hasParty(p1)) {
		                if (Party.getParty(p).equals(Party.getParty(p1))) {
		                    continue;
		                }
		            }
		        }
		        if (e instanceof LivingEntity && e != p && !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
		            LivingEntity le = (LivingEntity) e;
		            le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 0, false, false));
		            if (Proficiency.getpro(p) >= 1) {
		                Holding.holding(p, le, 40L);
		            }
		        }
		    }

		    HashSet<Location> hs = new HashSet<>();
		    HashMap<Location, BlockData> hls = new HashMap<>();

		    for (int i = -20; i < 20; i++) {
		        for (int j = -20; j < 20; j++) {
		            for (int k = -20; k < 20; k++) {
		                Location hl = fr.getLocation().clone().add(i, j, k);
		                if ((hl.getBlock().getType() == Material.VOID_AIR || hl.getBlock().getType() == Material.AIR) && hl.getBlock().getType() != Material.LIGHT) {
		                    hls.put(hl, hl.getBlock().getBlockData());
		                    hs.add(hl);
		                    hl.getBlock().setType(Material.LIGHT);  // 라이트 블록으로 변경
		                }
		            }
		        }
		    }

		    // 20초 후에 블록을 원래 상태로 복구
		    Bukkit.getScheduler().runTaskLater(RMain.getInstance(), () -> {
		        for (Location loc : hs) {
		            loc.getBlock().setBlockData(hls.get(loc));  // 원래 블록으로 복구
		        }
		    }, 20 * 20); // 20초 후
		}
	}



	public void Equipment(PlayerItemDamageEvent e)
	{
		Player p = e.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 4)
		{
			if(p.getInventory().getItemInMainHand().getType().name().equals("CROSSBOW"))
			{
				e.setCancelled(true);
			}
		}

	}


	public void ULT(PlayerItemHeldEvent ev)
    {
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();

		if(ClassData.pc.get(p.getUniqueId()) == 4 && ev.getNewSlot()==3 && (is.getType().name().equals("CROSSBOW")) && p.isSneaking()&& Proficiency.getpro(p) >=1)
		{
			ev.setCancelled(true);
			p.setCooldown(CAREFUL, 2);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(45/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("최후의 한발")
					.ename("THE LAST ONE")
					.slot(6)
					.hm(bultcooldown)
					.skillUse(() -> {

						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 13,0,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 30,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 30,255,false,false));
						p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
						p.getWorld().playSound(p.getLocation(),Sound.BLOCK_BEACON_ACTIVATE, 1,0.3f);
						p.getWorld().playSound(p.getLocation(),Sound.BLOCK_CHEST_LOCKED, 1,0f);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_ARMOR_STAND_PLACE, 1,0.23f);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_PLAYER_BREATH, 1,0.23f);
						AtomicInteger j = new AtomicInteger();
						for(int i=0; i<25; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									if(p.getLocale().equalsIgnoreCase("ko_kr")) {
										p.sendTitle(org.bukkit.ChatColor.RED+"충전중...", j.incrementAndGet()*4 +"%", 5, 5, 5);
									}
									else {
										p.sendTitle(org.bukkit.ChatColor.RED+"Charging...", j.incrementAndGet()*4 +"%", 5, 5, 5);
									}
								}
							}, i);
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 600, 4, 4, 4);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 0.7f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_SQUID_DEATH, 1f, 1.3f);
								p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 1.6f);
								p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1.5f);
								p.playSound(p.getLocation(), Sound.BLOCK_BAMBOO_BREAK, 1.0f, 0.35f);
								Arrow ar = p.launchProjectile(Arrow.class);
								ar.setMetadata("APA", new FixedMetadataValue(RMain.getInstance(), true));
								ar.setVelocity(ar.getVelocity().normalize().multiply(200));
								ar.setShooter(p);
								ar.setWeapon(p.getInventory().getItemInMainHand());
								ar.setCritical(true);
								ar.setPierceLevel(127);
								p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
								arsn(ar, p, 10d, 10d);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										ar.remove();
									}
								}, 30);
							}
						}, 30);
					});
			bd.execute();

		}
	}

	
	public void ULT2(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}

		ItemStack is = p.getInventory().getItemInMainHand();

		if(ClassData.pc.get(p.getUniqueId()) == 4 && ev.getNewSlot()==4 && (is.getType().name().equals("CROSSBOW")) && p.isSneaking() && Proficiency.getpro(p) >=2)
		{
			ev.setCancelled(true);
			p.setCooldown(CAREFUL, 2);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(80*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("독수리분대")
					.ename("Team Eagle")
					.slot(7)
					.hm(bult2cooldown)
					.skillUse(() -> {

						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 13,0,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 50,20,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 30,255,false,false));
						p.getWorld().spawnParticle(Particle.WHITE_ASH, p.getEyeLocation(), 40, 1, 1, 1);
						p.getWorld().playSound(p.getLocation(),Sound.BLOCK_BEACON_ACTIVATE, 1,0.3f);
						p.getWorld().playSound(p.getLocation(),Sound.BLOCK_CONDUIT_ACTIVATE, 1,0f);
						p.getWorld().playSound(p.getLocation(),Sound.ENTITY_ENDERMAN_STARE, 1,0f);
						HashSet<LivingEntity> tar = new HashSet<>();
						HashSet<Location> ls = new HashSet<Location>();
						for(int i=0; i<30; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									tar.clear();
									Collection<Entity> ce = new HashSet<>();
									HashSet<Location> line = new HashSet<Location>();
									for(int ss = 0; ss <= 60; ss++) {
										Location pl = p.getEyeLocation().clone();
										pl.add(p.getEyeLocation().getDirection().normalize().multiply(ss));
										line.add(pl);
									}
									line.forEach(l -> {

										ce.addAll(l.getWorld().getNearbyEntities(l,3, 3, 3));
										if(ce.stream().anyMatch(e -> e.hasMetadata("boss"))) {
											ce.removeIf(e -> !e.hasMetadata("boss"));
										}
										else if(ce.stream().anyMatch(e -> e.hasMetadata("leader"))) {
											ce.removeIf(e -> !e.hasMetadata("leader"));
										}
									});
									for(Entity e : ce) {

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
										if ((!(e == p))&& e instanceof LivingEntity&& !e.hasMetadata("fake") && !e.hasMetadata("portal"))
										{
											LivingEntity le = (LivingEntity)e;
											tar.add(le);
											break;

										}
									}
									if(tar.stream().findFirst().isPresent()) {
										if(p.getLocale().equalsIgnoreCase("ko_kr")) {
											p.sendTitle(org.bukkit.ChatColor.RED+"목표물 탐색중...",tar.stream().findFirst().get().getName(), 5, 5, 5);
										}
										else {
											p.sendTitle(org.bukkit.ChatColor.RED+"Searching Target...",tar.stream().findFirst().get().getName(), 5, 5, 5);
										}
									}
									else {
										if(p.getLocale().equalsIgnoreCase("ko_kr")) {
											p.sendTitle(org.bukkit.ChatColor.RED+"목표물 탐색중...",null, 5, 5, 5);
										}
										else {
											p.sendTitle(org.bukkit.ChatColor.RED+"Searching Target...",null, 5, 5, 5);
										}
									}
								}
							}, i);
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								if(tar.stream().findFirst().isPresent()) {
									LivingEntity le = tar.stream().findFirst().get();
									p.getWorld().playSound(p.getLocation(),Sound.BLOCK_CHEST_LOCKED, 1,0f);
									if(p.getLocale().equalsIgnoreCase("ko_kr")) {
										p.sendTitle(org.bukkit.ChatColor.DARK_GREEN+"탐색완료!",le.getName(), 5, 5, 5);
									}
									else {
										p.sendTitle(org.bukkit.ChatColor.DARK_GREEN+"Targeted!",le.getName(), 5, 5, 5);
									}
									for(int i =-20; i<20; i++) {
										ls.add(le.getEyeLocation().clone().add(i, i/2, i));
									}

									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											for(int i=0; i<40; i++) {
												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
													@Override
													public void run()
													{
														p.getWorld().spawnParticle(Particle.EXPLOSION, le.getLocation(), 3, 1, 1, 1);
														p.getWorld().spawnParticle(Particle.CRIT, le.getLocation(), 40, 2, 2, 2,0.1);
														p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le.getLocation(), 10, 3,3, 3);
														p.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, le.getLocation(), 3, 1, 1, 1);
														p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 10, 3, 3, 3);
														p.getWorld().spawnParticle(Particle.INSTANT_EFFECT, le.getLocation(), 30, 1, 1, 1);
														p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, le.getLocation(), 30, 1, 1, 1);

														p.getWorld().playSound(le.getLocation(),Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.3f,2f);
														p.getWorld().playSound(le.getLocation(),Sound.ENTITY_ARROW_HIT, 0.3f,2f);
														p.getWorld().playSound(le.getLocation(),Sound.ENTITY_GENERIC_EXPLODE, 0.3f,2f);
														for(Entity e : le.getWorld().getNearbyEntities(le.getLocation(),4, 4, 4)) {
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
															if(e instanceof LivingEntity && e!=p&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
																LivingEntity le = (LivingEntity)e;
																atk1(0.5, p, le);
																Holding.holding(p, le, 10l);

															}

														}
													}
												}, i*2);
											}
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
												@Override
												public void run()
												{
													p.getWorld().playSound(le.getLocation(),Sound.BLOCK_BEACON_ACTIVATE, 0.3f,1.5f);
												}
											}, 82);
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
												@Override
												public void run()
												{
													ls.forEach(l -> {
														l.getWorld().spawnParticle(Particle.ASH, l, 10, 0.5,0.5,0.5,0);
														l.getWorld().spawnParticle(Particle.WHITE_ASH, l, 10, 0.5,0.5,0.5,0);
														l.getWorld().spawnParticle(Particle.FLASH, l, 3, 0.5, 0.5, 0.5);
														l.getWorld().spawnParticle(Particle.CRIT, l, 3, 0.5, 0.5, 0.5,0);

													});

													p.getWorld().playSound(le.getLocation(),Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1f,1.5f);
													p.getWorld().playSound(le.getLocation(),Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1f,0.5f);
													for(Entity e : le.getWorld().getNearbyEntities(le.getLocation(),4, 4, 4)) {
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
														if(e instanceof LivingEntity && e!=p&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
															LivingEntity le = (LivingEntity)e;
															atk1(6.5, p, le);
															Holding.holding(p, le, 10l);

														}

													}
												}
											}, 90);

										}
									}, 15);
								}
								else {
									p.getWorld().playSound(p.getLocation(),Sound.BLOCK_CHEST_LOCKED, 1,0f);
									if(p.getLocale().equalsIgnoreCase("ko_kr")) {
										p.sendTitle(org.bukkit.ChatColor.DARK_GREEN+"탐색 실패!","무차별 난사", 5, 5, 5);
									}
									else {
										p.sendTitle(org.bukkit.ChatColor.DARK_GREEN+"Failed to Target.","Random Fire", 5, 5, 5);
									}

									Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(10));
									for(int i =-20; i<20; i++) {
										ls.add(tl.clone().add(i, i/2, i));
									}
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											for(int i=0; i<40; i++) {
												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
													@Override
													public void run()
													{
														p.getWorld().spawnParticle(Particle.EXPLOSION, tl, 3, 1, 1, 1);
														p.getWorld().spawnParticle(Particle.CRIT, tl, 40, 2, 2, 2,0.1);
														p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl, 10, 3,3, 3);
														p.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, tl, 3, 1, 1, 1);
														p.getWorld().spawnParticle(Particle.FLASH, tl, 10, 3, 3, 3);
														p.getWorld().spawnParticle(Particle.INSTANT_EFFECT, tl, 30, 1, 1, 1);
														p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, tl, 30, 1, 1, 1);

														p.getWorld().playSound(tl,Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.3f,2f);
														p.getWorld().playSound(tl,Sound.ENTITY_ARROW_HIT, 0.3f,2f);
														p.getWorld().playSound(tl,Sound.ENTITY_GENERIC_EXPLODE, 0.3f,2f);
														for(Entity e : tl.getWorld().getNearbyEntities(tl,4, 4, 4)) {
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
															if(e instanceof LivingEntity && e!=p&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
																LivingEntity le = (LivingEntity)e;
																atk1(0.5, p, le);
																Holding.holding(p, le, 10l);

															}

														}
													}
												}, i*2);
											}
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
												@Override
												public void run()
												{
													p.getWorld().playSound(tl,Sound.BLOCK_BEACON_ACTIVATE, 0.3f,1.5f);
													for(int i =-20; i<20; i++) {
														ls.add(tl.clone().add(i, i/2, i));
													}
												}
											}, 82);
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
												@Override
												public void run()
												{
													ls.forEach(l -> {
														l.getWorld().spawnParticle(Particle.ASH, l, 10, 0.5,0.5,0.5,0);
														l.getWorld().spawnParticle(Particle.WHITE_ASH, l, 10, 0.5,0.5,0.5,0);
														l.getWorld().spawnParticle(Particle.FLASH, l, 3, 0.5, 0.5, 0.5);
														l.getWorld().spawnParticle(Particle.CRIT, l, 3, 0.5, 0.5, 0.5,0);

													});

													p.getWorld().playSound(tl,Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1f,1.5f);
													p.getWorld().playSound(tl,Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1f,0.5f);
													for(Entity e : tl.getWorld().getNearbyEntities(tl,4, 4, 4)) {
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
														if(e instanceof LivingEntity && e!=p&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
															LivingEntity le = (LivingEntity)e;
															atk1(6.5, p, le);
															Holding.holding(p, le, 10l);

														}

													}
												}
											}, 90);

										}
									}, 15);
								}
							}
						}, 30);
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

		if(ClassData.pc.get(p.getUniqueId()) == 4 && (is.getType().name().contains("CROSSBOW")) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
		{
			ev.setCancelled(true);
		}

	}

	public void Fireworkairstrike(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Firework && d.getEntity() instanceof LivingEntity)
		{
			Firework fw = (Firework) d.getDamager();
			if (fw.hasMetadata("airstrike")) {
				d.setCancelled(true);
			}
			if (fw.hasMetadata("flare")) {
				d.setCancelled(true);
			}
			if (fw.hasMetadata("fake")) {
				d.setCancelled(true);
			}
		}
	}

	public void Remodeling(EntityShootBowEvent a)
	{

		if(a.getEntity() instanceof Player)
		{
			Player p = (Player) a.getEntity();

			if(ClassData.pc.get(p.getUniqueId()) == 4) {
				if(p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW)
				{

					if(a.getProjectile().getType() == EntityType.ARROW)
					{
						Arrow ar = (Arrow) a.getProjectile();
						if(ar.isShotFromCrossbow()) {
							Arrow bolt = ar;
							bolt.setVelocity(p.getEyeLocation().getDirection().clone().normalize().multiply(30));
							p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 1f, 2f);
							bolt.setCritical(true);
							ar1(bolt, p,0.123*(1+ssd.Remodeling.getOrDefault(p.getUniqueId(),0)*0.06));
							if(a.getBow().containsEnchantment(Enchantment.MULTISHOT)) {
								bolt.setDamage(bolt.getDamage()/2);
							}

							if(bolt.getPierceLevel()+ssd.Remodeling.getOrDefault(p.getUniqueId(),0)/2<=127) {
								bolt.setPierceLevel(ar.getPierceLevel()+ssd.Remodeling.getOrDefault(p.getUniqueId(),0)/2);
							}
							else {
								bolt.setPierceLevel(127);
							}
							a.setProjectile(bolt);
							if(Proficiency.getpro(p)>=1) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 1, false, false));
							}
						}
					}
				}
			}
		}
	}


	public void ArmorDecrease(EntityDamageByEntityEvent d)
	{
		if(d.getEntity() instanceof Player && d.getDamager() instanceof LivingEntity)
		{
			if(ClassData.pc.get(d.getEntity().getUniqueId()) == 4) {
				d.setDamage(d.getDamage()*1.6);
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof Player)
		{
			Arrow ar = (Arrow)d.getDamager();

			if(ar.getShooter() instanceof LivingEntity) {
				if(ClassData.pc.get(d.getEntity().getUniqueId()) == 4) {
					d.setDamage(d.getDamage()*1.6);
				}
			}
		}
	}


	public void Damagegetter(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity)
		{
			Projectile a = (Projectile)d.getDamager();
			if(a.getShooter() instanceof Player) {
				Player p = (Player)a.getShooter();
				if(ClassData.pc.get(p.getUniqueId()) == 4) {
					if(a.hasMetadata("airstrike") || a.hasMetadata("flare")) {
						d.setCancelled(true);
					}
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity)
		{
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();




			if(ClassData.pc.get(p.getUniqueId()) == 4) {
				dset2(d, p, 1d, le, 5);
				if(le.hasMetadata("leader") || le.hasMetadata("boss")) {
					d.setDamage(d.getDamage()*2.35);
				}
			}
		}

	}



	public void WitherHunter(ProjectileHitEvent ev)
	{

		if(ev.getEntity().getShooter() instanceof Player && ev.getEntity() instanceof Arrow)
		{
			Player p = (Player)ev.getEntity().getShooter();




			if(ClassData.pc.get(p.getUniqueId()) == 4) {
				if(ev.getHitEntity() instanceof Wither) {
					Wither e =(Wither) ev.getHitEntity();
					Arrow ar = (Arrow) ev.getEntity();
					if(e.getHealth() <= e.getAttribute(Attribute.MAX_HEALTH).getValue()/2)
					{

						if(Math.abs(ar.getLocation().getY() - (e.getEyeLocation().getY())) <= (0.5+ssd.HeadShot.get(p.getUniqueId())*0.01)|| Proficiency.getpro(p)>=2) {
							e.damage(bbArrow(ar)*1.36*(1+ssd.HeadShot.get(p.getUniqueId())*0.036), p);
							e.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
							p.getWorld().spawnParticle(Particle.CRIT, e.getEyeLocation(), 50, 1,1,1);
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[헤드샷]").bold(true).color(ChatColor.DARK_GREEN).create());
							}
							else {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[HEADSHOT]").bold(true).color(ChatColor.DARK_GREEN).create());
							}
						}
						else {
							e.damage(bbArrow(ar), p);
						}
					}
				}
				if(ev.getHitEntity() instanceof Enderman || ev.getHitEntity() instanceof Breeze) {
					LivingEntity e =(LivingEntity) ev.getHitEntity();
					Arrow ar = (Arrow) ev.getEntity();

					if(Math.abs(ar.getLocation().getY() - (e.getEyeLocation().getY())) <= (0.5+ssd.HeadShot.get(p.getUniqueId())*0.01)|| Proficiency.getpro(p)>=2) {
						e.damage(bbArrow(ar)*1.36*(1+ssd.HeadShot.get(p.getUniqueId())*0.036), p);
						e.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
						p.getWorld().spawnParticle(Particle.CRIT, e.getEyeLocation(), 50, 1,1,1);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[헤드샷]").bold(true).color(ChatColor.DARK_GREEN).create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[HEADSHOT]").bold(true).color(ChatColor.DARK_GREEN).create());
						}
					}
					else {
						e.damage(bbArrow(ar), p);
					}
				}
			}
		}
	}

	public void HeadShot(EntityDamageByEntityEvent d) {
	    if (!(d.getDamager() instanceof Arrow) || !(d.getEntity() instanceof LivingEntity) || d.isCancelled()) {
	        return;
	    }

	    Projectile a = (Projectile) d.getDamager();
	    LivingEntity e = (LivingEntity) d.getEntity();

	    if (a.getShooter() instanceof Player && !a.hasMetadata("APA")) {
	        Player p = (Player) a.getShooter();
	        if (ClassData.pc.get(p.getUniqueId()) == 4 && p.getInventory().getItemInMainHand().getType() == Material.CROSSBOW) {
	            boolean isHeadshot = isHeadshot(e, a, p);
	            if (isHeadshot) {
	                handleHeadshot(p, e, d);
	            }
	        }
	    }
	}

	private boolean isHeadshot(LivingEntity e, Projectile a, Player p) {
	    BoundingBox al = a.getBoundingBox();
	    if (e instanceof Illager || e instanceof PiglinAbstract || e instanceof Witch || e instanceof AbstractVillager||
	        (Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(e.getType())&&e.getType()!=EntityType.PHANTOM) || 
	        e.getType() == EntityType.CREEPER || 
	        e.getType() == EntityType.BREEZE || 
	        e.getType() == EntityType.PLAYER || 
	        e.getType() == EntityType.BLAZE || 
	        e.getType() == EntityType.ENDERMAN) {
	        
	        return Math.abs((e.getBoundingBox().getMaxY()) - al.getCenterY()) <= (0.25 + ssd.HeadShot.get(p.getUniqueId()) * 0.1) || Proficiency.getpro(p) >= 2;
	    } else {
	        Location pl = p.getLocation();
	        return Math.abs(e.getLocation().getDirection().angle(pl.getDirection())) <= (Math.PI + ssd.HeadShot.get(p.getUniqueId()) * 0.1) &&
	               Math.abs(e.getLocation().getDirection().angle(pl.getDirection())) > (Math.PI / 2 - ssd.HeadShot.get(p.getUniqueId()) * 0.1) || Proficiency.getpro(p) >= 2;
	    }
	}

	private void handleHeadshot(Player p, LivingEntity e, EntityDamageByEntityEvent d) {
	    e.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
	    dset2(d, p, 1.36 * (1 + ssd.HeadShot.get(p.getUniqueId()) * 0.036), e, 5);
	    if (e.hasMetadata("leader") || e.hasMetadata("boss")) {
	        d.setDamage(d.getDamage() * 2.5);
	    }
	    p.getWorld().spawnParticle(Particle.CRIT, e.getEyeLocation(), 50, 1, 1, 1);
	    String message = p.getLocale().equalsIgnoreCase("ko_kr") ? "[헤드샷]" : "[HEADSHOT]";
	    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(message).bold(true).color(ChatColor.DARK_GREEN).create());
	}
}