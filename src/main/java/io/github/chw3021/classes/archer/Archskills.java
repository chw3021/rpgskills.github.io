package io.github.chw3021.classes.archer;




import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.commons.SkillUseEvent;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;
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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Breeze;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Wither;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Archskills extends Pak{


	private HashMap<String, Integer> sa = new HashMap<String, Integer>();
	private HashMap<String, Long> hscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> arcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gacooldown = new HashMap<String, Long>();
	private HashMap<String, Long> dpcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> bultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> bult2cooldown = new HashMap<String, Long>();


	private HashMap<Arrow, UUID> pitched = new HashMap<Arrow, UUID>();

	private HashMap<UUID, Integer> spins = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> spinst = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> inshot = new HashMap<UUID, Integer>();

	private HashMap<UUID, Vector> spv = new HashMap<UUID, Vector>();
	private HashMap<UUID, Integer> spvt = new HashMap<UUID, Integer>();


	private HashMap<Player, Projectile> fArrow = new HashMap<Player, Projectile>();
	private HashMap<Player, LivingEntity> grabbed = new HashMap<Player, LivingEntity>();
	private HashMap<UUID, Integer> grabt = new HashMap<UUID, Integer>();

	private HashMap<Player, Integer> indure = new HashMap<Player, Integer>();
	private String path = new File("").getAbsolutePath();
	private ArchSkillsData asd;

	private ItemStack knockbackBow = new ItemStack(Material.BOW);


	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Archskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				Player p = (Player) e.getWhoClicked();
				p.setCooldown(CAREFUL, 2);
				ArchSkillsData a = new ArchSkillsData(ArchSkillsData.loadData(path +"/plugins/RPGskills/ArchSkillsData.data"));
				asd = a;
			}

		}
	}


	public void nepreventer(PlayerJoinEvent ev)
	{
		ArchSkillsData a = new ArchSkillsData(ArchSkillsData.loadData(path +"/plugins/RPGskills/ArchSkillsData.data"));
		asd = a;
	}


	public void er(PluginEnableEvent ev)
	{
		ArchSkillsData a = new ArchSkillsData(ArchSkillsData.loadData(path +"/plugins/RPGskills/ArchSkillsData.data"));
		asd = a;
	}



	public void XXX(ProjectileHitEvent ev)
	{

		if(ev.getEntity().getShooter() instanceof Player && ev.getEntity() instanceof Projectile)
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
				}
			}
		}
	}

	private static final Archskills instance = new Archskills ();
	public static Archskills getInstance()
	{
		return instance;
	}

	public void TripleShot(EntityShootBowEvent a)
	{

		if(a.getEntity() instanceof Player)
		{
			Player p = (Player) a.getEntity();
			if(ClassData.pc.get(p.getUniqueId()) == 6) {
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW && asd.TripleShot.getOrDefault(p.getUniqueId(),0)>=1)
				{
					spv.remove(p.getUniqueId());
					if(a.getProjectile() instanceof AbstractArrow)
					{
						AbstractArrow far = (AbstractArrow)a.getProjectile();
						fArrow.put(p, far);
						ar2(far, p, far.getDamage() , 0.05* (1+asd.TripleShot.get(p.getUniqueId())*0.035));
						if(Proficiency.getpro(p) >=1 && Proficiency.getpro(p)<2) {
							for(int i =0; i<3; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										//Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 2, 2);
										AbstractArrow ar = p.launchProjectile(far.getClass(),far.getVelocity().multiply(0.9));
										ar.setShooter(p);

										
										p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
										ar.setCritical(true);
										ar2(ar, p, far.getDamage()*0.62 , 0.015);
										ar.setPickupStatus(PickupStatus.DISALLOWED);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run()
											{
												ar.remove();
											}
										}, 20);
									}
								}, i);
							}
						}
						else if(Proficiency.getpro(p) >=2) {
							for(int i =0; i<6; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										AbstractArrow ar = p.launchProjectile(far.getClass(),far.getVelocity().multiply(0.9));
										ar.setShooter(p);

										
										p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
										ar.setCritical(true);
										ar2(ar, p, far.getDamage()*0.62 , 0.015);
										ar.setPickupStatus(PickupStatus.DISALLOWED);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run()
											{
												ar.remove();
											}
										}, 20);
									}
								}, i/2);
							}
						}
						else {
							for(int i =0; i<2; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										AbstractArrow ar = p.launchProjectile(far.getClass(),far.getVelocity().multiply(0.9));
										ar.setShooter(p);

										p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
										ar.setCritical(true);
										ar2(ar, p, far.getDamage()*0.62 , 0.015);
										ar.setPickupStatus(PickupStatus.DISALLOWED);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run()
											{
												ar.remove();
											}
										}, 20);
									}
								}, i*2);
							}
						}
						if(grabbed.containsKey(p))
						{
							Shotmob(p);
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("샷!").color(ChatColor.DARK_RED).create());
							}
							else {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Shot!").color(ChatColor.DARK_RED).create());
							}
						}

					}
				}
			}
		}
	}

	final private void SpreadingArrows(Player p) {

		sa.computeIfPresent(p.getName(), (k,v) -> v-1);
		Arrow firstarrow = p.launchProjectile(Arrow.class);
		firstarrow.setDamage(0);
		firstarrow.remove();
		ArrayList<Location> line = new ArrayList<Location>();
		final Location fl = p.getEyeLocation().clone();

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run()
			{
				Location sl = p.getEyeLocation().clone();
				Vector pv = sl.clone().toVector().subtract(fl.toVector()).normalize();
				try {
					pv.checkFinite();
				}
				catch(IllegalArgumentException e) {
					pv = fl.getDirection();
				}

				for(double d = 0.1; d <= 3; d += 0.1) {
					Location pl = fl.clone();
					pl.add(pv.clone().normalize().multiply(d));
					if(!pl.getBlock().isPassable()) {
						break;
					}
					line.add(pl);
				}
				if(spv.containsKey(p.getUniqueId())) {
					pv = spv.get(p.getUniqueId());
					Bukkit.getScheduler().cancelTask(spvt.get(p.getUniqueId()));
				}
				else {
					spv.put(p.getUniqueId(), pv);
				}
				int t =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						spv.remove(p.getUniqueId());
					}

				},10);
				spvt.put(p.getUniqueId(), t);
				HashSet<LivingEntity> les = new HashSet<LivingEntity>();
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0f);

				if(Proficiency.getpro(p) >=1) {
					inshot.putIfAbsent(p.getUniqueId(), 1);
				}

				AtomicInteger j = new AtomicInteger(0);
				line.forEach(i -> {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							try {
								p.teleport(i);
							}
							catch(IllegalArgumentException e) {
								Location tl = p.getLocation().clone().add(fl.clone().getDirection().multiply(0.1));
								spv.put(p.getUniqueId(), tl.clone().getDirection());
								if(tl.getBlock().isPassable()) {
									p.teleport(tl);
								}
							}
							p.getWorld().spawnParticle(Particle.CRIT, p.getLocation(), 6, 0.1,0.1,0.1,0);
							for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),2.5, 2.5, 2.5))
							{
								if (e instanceof Player)
								{

									Player p1 = (Player) e;
									if(Party.isInSameParty(p,p1))	{
										continue;
									}
								}
								if ((!(e == p))&& e instanceof LivingEntity && !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
								{
									LivingEntity le = (LivingEntity)e;
									les.add(le);
								}
							}
							p.setFallDistance(0);
						}
					}, j.incrementAndGet()/50);
				});
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{

						for(LivingEntity le: les) {
							Location pl = p.getEyeLocation();
							line.add(pl);
							Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), le.getLocation().toVector().subtract(pl.toVector()), 1, 0);
							ar.setCritical(false);
							ar.setDamage(0);
							ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							ar.setPickupStatus(PickupStatus.DISALLOWED);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									ar.remove();
								}

							}, 20);

							atk1(0.65, p, le,5);

							if(Proficiency.getpro(p) >=2) {
								Holding.superholding(p, le, 10l);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{

										Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), le.getLocation().toVector().subtract(pl.toVector()), 1, 0);
										ar.setCritical(false);
										ar.setDamage(0);
										ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
										ar.setPickupStatus(PickupStatus.DISALLOWED);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run()
											{
												ar.remove();
											}

										}, 20);

										atk1(1.1, p, le,5);

										p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1,168f);
										p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 10,2,2,2);
									}

								}, 7);

							}
						}

					}
				}, j.incrementAndGet()/50);
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if(Proficiency.getpro(p) >=1) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"화살뿌리기 "+sa.get(p.getName()) + "/6").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"화살뿌리기 "+sa.get(p.getName()) + "/3").create());
					}
				}
				else {
					if(Proficiency.getpro(p) >=1) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"SpreadingArrows "+sa.get(p.getName()) + "/6").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"SpreadingArrows "+sa.get(p.getName()) + "/3").create());
					}
				}
			}
		}, 1);
	}

	public void SpreadingArrows(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 6 && !p.hasCooldown(CAREFUL))	{
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW && asd.SpreadingArrows.getOrDefault(p.getUniqueId(),0)>=1)
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK) && !p.isSneaking())
				{
					p.setCooldown(CAREFUL, 2);
					if(!sa.containsKey(p.getName())) {
						Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
									if(Proficiency.getpro(p) >=1) {
										if(sa.get(p.getName())<6) {
											sa.computeIfPresent(p.getName(), (k,v) -> v+1);
											p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"화살뿌리기 "+sa.get(p.getName()) + "/6").create());
											if(sa.get(p.getName())<5) {
												Bukkit.getPluginManager().callEvent(new SkillUseEvent(p,5.0,0,ChatColor.BLUE+"화살뿌리기",ChatColor.BLUE+"SpreadingArrows"));
											}
										}
									}
									else {
										if(sa.get(p.getName())<3) {
											sa.computeIfPresent(p.getName(), (k,v) -> v+1);
											p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"화살뿌리기 "+sa.get(p.getName()) + "/3").create());
											if(sa.get(p.getName())<5) {
												Bukkit.getPluginManager().callEvent(new SkillUseEvent(p,5.0,0,ChatColor.BLUE+"화살뿌리기",ChatColor.BLUE+"SpreadingArrows"));
											}
										}
									}
								}
								else {
									if(Proficiency.getpro(p) >=1) {
										if(sa.get(p.getName())<6) {
											sa.computeIfPresent(p.getName(), (k,v) -> v+1);
											p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"SpreadingArrows "+sa.get(p.getName()) + "/6").create());
											if(sa.get(p.getName())<5) {
												Bukkit.getPluginManager().callEvent(new SkillUseEvent(p,5.0,0,ChatColor.BLUE+"화살뿌리기",ChatColor.BLUE+"SpreadingArrows"));
											}
										}
									}
									else {
										if(sa.get(p.getName())<3) {
											sa.computeIfPresent(p.getName(), (k,v) -> v+1);
											p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"SpreadingArrows "+sa.get(p.getName()) + "/3").create());
											if(sa.get(p.getName())<5) {
												Bukkit.getPluginManager().callEvent(new SkillUseEvent(p,5.0,0,ChatColor.BLUE+"화살뿌리기",ChatColor.BLUE+"SpreadingArrows"));
											}
										}
									}
								}
							}
						}, 100, 100);
						if(Proficiency.getpro(p) >=1) {
							sa.putIfAbsent(p.getName(), 6);
						}
						else {
							sa.putIfAbsent(p.getName(), 3);
						}

						SpreadingArrows(p);

					}
					else if(sa.get(p.getName())>0) {
						SpreadingArrows(p);
					}

				}
			}
		}
	}

	public void Indure(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		if(ClassData.pc.get(p.getUniqueId()) == 6)	{
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
			{
				if(ac == Action.RIGHT_CLICK_AIR || ac == Action.RIGHT_CLICK_BLOCK)
				{
					indure.computeIfPresent(p, (k,v) -> v+1);
					indure.putIfAbsent(p, 0);
					if(Proficiency.getpro(p) >=1) {
						if(inshot.containsKey(p.getUniqueId())) {
							inshot.remove(p.getUniqueId());
							Arrow far = p.launchProjectile(Arrow.class);
							ar2(far, p, far.getDamage() , 0.05);
							Bukkit.getPluginManager().callEvent(new EntityShootBowEvent(p, null, new ItemStack(CAREFUL), far, EquipmentSlot.HAND, 1f, true));
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								indure.computeIfPresent(p, (k,v) -> v-1);
								if(indure.get(p)<0) {
									indure.remove(p);
								}
							}
						}, 40);
					}
					else {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								indure.computeIfPresent(p, (k,v) -> v-1);
								if(indure.get(p)<0) {
									indure.remove(p);
								}
							}
						}, 20);
					}
				}
			}
		}
	}


	public void Indure(EntityDamageEvent d)
	{
		if(d.getEntity() instanceof Player)
		{
			Player p = (Player)d.getEntity();
			if(ClassData.pc.get(p.getUniqueId()) == 6 && indure.containsKey(p)) {
				d.setDamage(d.getDamage()*(0.68 - Proficiency.getpro(p)*0.2));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"[버티기]").create());
				}
				else {
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"[Indure]").create());
				}
			}
		}
	}
	final private void retrieve(Player p, LivingEntity le) {
		if(!le.getWorld().equals(p.getWorld())) {
			return;
		}
		Location lel = le.getLocation().clone().add(0, 0.3, 0);
		Location pl = p.getEyeLocation().clone().add(0, 0.2, 0);
		Vector v = pl.clone().toVector().subtract(lel.clone().toVector()).normalize();
		Arrow ar = p.getWorld().spawnArrow(lel, v, 2, 1);
		ar.setCritical(false);
		
		ar.setDamage(0);
		ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		ar.setPickupStatus(PickupStatus.DISALLOWED);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run()
			{
				ar.remove();
			}

		}, 20);
	}

	public void Retrieve(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 6 && !p.isSneaking()) {
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW && asd.Retrieve.getOrDefault(p.getUniqueId(),0)>=1)
			{
				double sec = 3*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("회수")
						.ename("Retrieve")
						.slot(1)
						.hm(gacooldown)
						.skillUse(() -> {
							Location l = p.getLocation();
							p.playSound(p.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, 1, 2);
							p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 0);
							for (Entity e : p.getWorld().getNearbyEntities(l, 15, 15, 15))
							{
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
								{
									LivingEntity le = (LivingEntity)e;
									{
										if(le.getArrowsInBody()>=1) {

											final int arc = le.getArrowsInBody();
											atk1(0.5*(1+arc*0.23)*(asd.Retrieve.get(p.getUniqueId())), p, le,5);
											le.getWorld().spawnParticle(Particle.CRIT, le.getLocation(), 80);
											le.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le.getLocation(), 3,1,1,1);

											if(Proficiency.getpro(p) >=1) {

												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
													@Override
													public void run()
													{
														atk1(0.5*(1+arc*0.23)*(asd.Retrieve.get(p.getUniqueId())), p, le,5);
														le.getWorld().spawnParticle(Particle.BLOCK, le.getLocation(), 40, Material.REDSTONE_BLOCK.createBlockData());
														Holding.holding(p, le, 10l);
													}
												}, 10);

											}
											le.setArrowsInBody(0);
											retrieve(p,le);
										}
									}
								}
								if (e instanceof AbstractArrow)
								{
									AbstractArrow le = (AbstractArrow)e;
									if(le.getShooter() == p)
									{
										le.teleport(p);
									}
								}
							}
						});
				bd.execute();


			}
		}
	}



	public void swcancle(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 6) {
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
			{
				ev.setCancelled(true);
			}}

	}


	public void RapidFire(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();



		if(ClassData.pc.get(p.getUniqueId()) == 6 && p.isSneaking() && asd.RapidFire.getOrDefault(p.getUniqueId(),0)>=1) {
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW )
			{
				double sec = 9*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("속사")
						.ename("RapidFire")
						.slot(2)
						.hm(arcooldown)
						.skillUse(() -> {
							if(spinst.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(spinst.get(p.getUniqueId()));
								spinst.remove(p.getUniqueId());
							}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									if(Proficiency.getpro(p)>=2) {
										spins.putIfAbsent(p.getUniqueId(), 0);
									}
								}
							}, 3);

							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									spins.remove(p.getUniqueId());
								}
							}, 35);
							spinst.put(p.getUniqueId(), task);

							Arrow firstarrow = p.launchProjectile(Arrow.class);
							firstarrow.setDamage(0);
							firstarrow.remove();
							ArrayList<AbstractArrow> arar = new ArrayList<AbstractArrow>();
							for(int i =0; i<10+(Proficiency.getpro(p)>=1 ? 10:0); i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										if(fArrow.containsKey(p)) {
											AbstractArrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 10, 1, ((AbstractArrow)fArrow.get(p)).getClass());
											ar.setShooter(p);
											if(Proficiency.getpro(p) >=1) {
												ar.setPierceLevel(10);
											}
											ar.setWeapon(knockbackBow);
											ar1(ar, p, 0.034*(1+asd.RapidFire.get(p.getUniqueId())*0.01));
											p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
											arar.add(ar);
											ar.setPickupStatus(PickupStatus.DISALLOWED);
										}
										else {
											AbstractArrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 10, 1);
											ar.setShooter(p);
											if(Proficiency.getpro(p) >=1) {
												ar.setPierceLevel(10);
											}
											ar.setWeapon(knockbackBow);
											ar1(ar, p, 0.034*(1+asd.RapidFire.get(p.getUniqueId())*0.01));
											p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
											arar.add(ar);
											ar.setPickupStatus(PickupStatus.DISALLOWED);
										}
									}
								}, i*3/(Proficiency.getpro(p)>=1 ? 2:1));
							}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									for(AbstractArrow a : arar)
									{
										a.remove();
									}
								}
							}, 40);
						});
				bd.execute();
			}
		}
	}



	public void SpinShots(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 6&&spins.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
			{
				if((p.isSneaking()))
				{
					ev.setCancelled(true);

					if(spinst.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(spinst.get(p.getUniqueId()));
						spinst.remove(p.getUniqueId());
					}
					spins.remove(p.getUniqueId());

					Location pl = p.getEyeLocation();
					Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), pl.clone().getDirection(), 0.5f, 0);
					ar.setShooter(p);
					ar.setCritical(false);
					ar.setDamage(0);
					ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					ar.setPickupStatus(PickupStatus.DISALLOWED);
					ar.setGravity(false);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							ar.remove();
						}

					}, 31);

					for(int i =0; i<10; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								ar.getWorld().spawnParticle(Particle.SWEEP_ATTACK, ar.getLocation(), 3, 0.1,0.1,0.1,1);
								ar.getWorld().playSound(ar.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1,2);
								for (Entity e : p.getWorld().getNearbyEntities(ar.getLocation(), 3, 3, 3))
								{
									if (e instanceof Player)
									{

										Player p1 = (Player) e;
										if(Party.isInSameParty(p,p1))	{
											continue;
										}
									}
									if ((!(e == p))&& e instanceof LivingEntity && !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
									{
										LivingEntity le = (LivingEntity)e;
										atk1(0.34*(1+asd.RapidFire.get(p.getUniqueId())*0.2), p, le,5);
										le.teleport(ar.getLocation());
										Holding.holding(p, le, 20l);
									}
								}
							}
						}, i*3);
					}

				}
			}
		}
	}



	public void MultiShot(EntityShootBowEvent a)
	{

		if(a.getEntity() instanceof Player)
		{
			Player p = (Player) a.getEntity();



			if(ClassData.pc.get(p.getUniqueId()) == 6 && p.isSneaking() && asd.MultiShot.getOrDefault(p.getUniqueId(),0)>=1) {

				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					if(a.getProjectile() instanceof AbstractArrow)
					{
						double sec = 4*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
						SkillBuilder bd = new SkillBuilder()
								.player(p)
								.cooldown(sec)
								.kname("다중사격")
								.ename("MultiShot")
								.slot(3)
								.hm(dpcooldown)
								.skillUse(() -> {
									Arrow firstarrow = p.launchProjectile(Arrow.class);
									firstarrow.setDamage(0);
									firstarrow.remove();
									AbstractArrow fa = (AbstractArrow) a.getProjectile();
									ArrayList<AbstractArrow> arar = new ArrayList<>();

									if(Proficiency.getpro(p) >=1) {
										for(int i =1; i<3; i++) {
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
												@Override
												public void run()
												{
													for(int i = 0; i<25; i++) {
														AbstractArrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 4, 20, fa.getClass());
														ar.setShooter(p);
														ar.setWeapon(knockbackBow);
														ar.setCritical(true);
														
														ar1(ar, p, 0.01*(1+asd.MultiShot.get(p.getUniqueId())*0.0065));
														p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 0.1f, 1.6f);
														p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 0.1f, 1.6f);
														arar.add(ar);
														ar.setPickupStatus(PickupStatus.DISALLOWED);
													}
												}
											}, i*2);
										}
									}
									else {
										for(int i = 0; i<25; i++) {
											AbstractArrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 4, 20, fa.getClass());
											ar.setShooter(p);
											ar.setWeapon(knockbackBow);
											ar.setCritical(true);
											
											ar1(ar, p, 0.01*(1+asd.MultiShot.get(p.getUniqueId())*0.0065));
											p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 0.1f, 1.6f);
											p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 0.1f, 1.6f);
											arar.add(ar);
											ar.setPickupStatus(PickupStatus.DISALLOWED);
										}
									}
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											for(AbstractArrow a : arar)
											{
												a.remove();
											}
										}
									}, 10);

								});
						bd.execute();
					}
				}
			}
		}
	}

	public void HookAndShot(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity)
		{
			Player p = (Player)d.getDamager();
			LivingEntity e = (LivingEntity) d.getEntity();

			if(e.hasMetadata("fake") || e.hasMetadata("portal")) {
				d.setCancelled(true);
				return;
			}
			if (e instanceof Player)
			{

				Player p1 = (Player) e;
				if(Party.hasParty(p) && Party.hasParty(p1))	{
					if(Party.getParty(p).equals(Party.getParty(p1)))
					{
						d.setCancelled(true);
						return;
					}
				}
			}


			if(ClassData.pc.get(p.getUniqueId()) == 6 && asd.HookAndShot.getOrDefault(p.getUniqueId(),0)>=1 && (p.isSneaking()) && !(p.hasCooldown(Material.YELLOW_TERRACOTTA))) {
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{

					double sec = 9*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("훅앤샷")
							.ename("HookAndShot")
							.slot(4)
							.hm(hscooldown)
							.skillUse(() -> {
								Arrow firstarrow = p.launchProjectile(Arrow.class);
								firstarrow.setDamage(0);
								firstarrow.remove();
								Grabmob(p, e);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(grabbed.containsKey(p))
											Shotmob(p);
									}
								}, 40);

							});
					bd.execute();

				}
			}
		}
	}

	private void Grabmob(Player p, LivingEntity e)
	{


		Holding.getInstance();

		if(ClassData.pc.get(p.getUniqueId()) == 6) {
			grabbed.put(p, e);
			int t =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run() {
					Location pl = p.getLocation().clone().add(p.getEyeLocation().getDirection().clone().normalize().multiply(1.35));
					e.teleport(pl);
				}
			}, 0,1);
			grabt.put(p.getUniqueId(), t);
			p.getWorld().spawnParticle(Particle.SQUID_INK, e.getLocation(), 50, 2,2,2);
			p.playSound(e.getLocation(), Sound.ENTITY_ARROW_HIT, 1, 1);
			p.playSound(e.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1, 0);
			Holding.superholding(p, e, (long) 40);
		}
	}

	private void Shotmob(Player p)
	{
		if(grabbed.containsKey(p))
		{

			Bukkit.getScheduler().cancelTask(grabt.get(p.getUniqueId()));


			if(ClassData.pc.get(p.getUniqueId()) == 6) {

				LivingEntity e = grabbed.get(p);
				p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 40, 1, false, false));
				e.setCollidable(true);
				Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 40, 0);
				ar.setShooter(p);
				if(Proficiency.getpro(p) >=1) {
					ar.setMetadata("holdshot", new FixedMetadataValue(RMain.getInstance(), true));
				}
				ar.setPierceLevel(1);
				ar.setMetadata("knockback", new FixedMetadataValue(RMain.getInstance(), true));
				p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1.6f);
				ar.setCritical(true);
				grabbed.remove(p);
			}
		}

	}


	public void Equipment(PlayerItemDamageEvent e)
	{
		Player p = e.getPlayer();




		if(p.getInventory().getItemInMainHand().getType().name().equals("BOW"))
		{
			if(ClassData.pc.get(p.getUniqueId()) == 6)
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


		if(ClassData.pc.get(p.getUniqueId()) == 6&& ev.getNewSlot()==3  && p.isSneaking() && Proficiency.getpro(p) >=1)
		{
			ev.setCancelled(true);
			p.setCooldown(CAREFUL, 1);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(80/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("광란의화살")
					.ename("CrazyArrows")
					.slot(6)
					.hm(bultcooldown)
					.skillUse(() -> {
						Arrow firstarrow = p.launchProjectile(Arrow.class);
						firstarrow.setDamage(0);
						firstarrow.remove();
						p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 0.3f, 0.3f);
						p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 70, 3, false,false));
						ArrayList<AbstractArrow> arar = new ArrayList<>();
						for(int i1 =0; i1<20; i1++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									for(int i = 0; i<30; i++)
									{
										if(fArrow.containsKey(p)) {
											AbstractArrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 9, 48, ((AbstractArrow)fArrow.get(p)).getClass());
											ar.setShooter(p);
											ar.setWeapon(knockbackBow);
											ar.setPickupStatus(PickupStatus.DISALLOWED);
											ar.setCritical(true);
											
											p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 0.05f, 1.6f);
											ar1(ar, p, 0.028);
											arar.add(ar);
										}
										else {
											Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 9, 48);
											ar.setShooter(p);
											ar.setWeapon(knockbackBow);
											ar.setPickupStatus(PickupStatus.DISALLOWED);
											ar.setCritical(true);
											
											p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 0.05f, 1.6f);
											ar1(ar, p, 0.028);
											arar.add(ar);
										}
									}
								}
							}, i1*3);
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								for(AbstractArrow a : arar)
								{
									a.remove();
								}
							}
						}, 65);
					});
			bd.execute();


		}


	}


	public void ULT2(PlayerItemHeldEvent ev)
	{
		final Player p = (Player)ev.getPlayer();
		ItemStack is = p.getInventory().getItemInMainHand();


		if(!isCombat(p)) {
			return;
		}

		if(ClassData.pc.get(p.getUniqueId()) == 6&& ev.getNewSlot()==4  && (is.getType().name().equals("BOW")) && p.isSneaking() && Proficiency.getpro(p) >=2)
		{
			ev.setCancelled(true);
			p.setCooldown(CAREFUL, 1);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(80*Obtained.ucd.getOrDefault(p.getUniqueId(),1d))
					.kname("화살의춤")
					.ename("Arrow Arts")
					.slot(7)
					.hm(bult2cooldown)
					.skillUse(() -> {
						Arrow firstarrow = p.launchProjectile(Arrow.class);
						firstarrow.setDamage(0);
						firstarrow.remove();
						p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 0.3f, 0.3f);
						p.playSound(p.getLocation(), Sound.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.3f, 1.3f);
						p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 70, 5, false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 70, 2, false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 70, 2, false,false));
						for(int i1 =2; i1<60; i1++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									for(Entity e : p.getNearbyEntities(10, 10, 10)) {

										if (e instanceof Player)
										{

											Player p1 = (Player) e;
											if(Party.isInSameParty(p,p1))	{
												continue;
											}
										}
										if ((!(e == p))&& e instanceof LivingEntity && !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
										{
											LivingEntity le = (LivingEntity)e;

											Holding.superholding(p, le, 2l);
											Location pl = p.getEyeLocation();
											Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), le.getLocation().toVector().subtract(pl.toVector()), 1, 0);
											ar.setCritical(false);
											ar.setDamage(0);
											ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
											ar.setPickupStatus(PickupStatus.DISALLOWED);
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
												@Override
												public void run()
												{
													ar.remove();
												}

											}, 20);
											atk1(0.35, p, le,5);

										}
									}
								}
							}, i1);
						}
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

		if(ClassData.pc.get(p.getUniqueId()) == 6 && (is.getType().name().contains("BOW")) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
		{
			ev.setCancelled(true);
		}

	}


	@SuppressWarnings("deprecation")
	public void EnderWitherHunter(ProjectileHitEvent ev)
	{

		if(ev.getEntity().getShooter() instanceof Player && ev.getEntity() instanceof Arrow)
		{
			Player p = (Player)ev.getEntity().getShooter();
			Arrow ar = (Arrow) ev.getEntity();



			if(ar.hasMetadata("knockback") && ev.getHitEntity() instanceof LivingEntity) {
				LivingEntity e =(LivingEntity) ev.getHitEntity();
				if (e instanceof Player)
				{

					Player p1 = (Player) e;
					if(Party.isInSameParty(p,p1))	{
						ev.setCancelled(true);
						return;

					}
				}
				pitched.put(ar, e.getUniqueId());
				if(ar.hasMetadata("holdshot")) {
					Holding.holding(p, e, 40l);
				}
			}

			if(ev.getHitBlock() !=null && pitched.containsKey(ar)) {

				Bukkit.getEntity(pitched.get(ar)).teleport(ev.getHitBlock().getLocation());
				pitched.remove(ar);
			}
			if(ClassData.pc.get(p.getUniqueId()) == 6 && Proficiency.getpro(p)>=2)  {
				if(ev.getHitEntity() instanceof Wither) {
					Wither e =(Wither) ev.getHitEntity();
					if(ar.hasMetadata("holdshot")) {
						Holding.holding(p, e, 40l);
					}
					if(Proficiency.getpro(p) >=1 && !p.hasCooldown(Material.CROSSBOW)) {
						if(sa.getOrDefault(p.getName() , 7)<6) {
							sa.computeIfPresent(p.getName(), (k,v) -> v+1);
							p.setCooldown(Material.CROSSBOW, 4);
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"화살뿌리기 "+sa.get(p.getName()) + "/6").create());
							}
							else {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"SpreadingArrows "+sa.get(p.getName()) + "/6").create());
							}
						}
					}
					if(e.getHealth() <= e.getAttribute(Attribute.MAX_HEALTH).getValue()/2)
					{
						p.setCooldown(Material.YELLOW_TERRACOTTA, 1);
						e.damage(bbArrow(ar), p);
						ev.getEntity().remove();
					}
				}
				if(ev.getHitEntity() instanceof Enderman || ev.getHitEntity() instanceof Breeze) {
					LivingEntity e =(LivingEntity) ev.getHitEntity();
					{
						if(ar.hasMetadata("holdshot")) {
							Holding.holding(p, e, 40l);
						}
						if(Proficiency.getpro(p) >=1 && !p.hasCooldown(Material.CROSSBOW)) {
							if(sa.getOrDefault(p.getName() , 7)<6) {
								sa.computeIfPresent(p.getName(), (k,v) -> v+1);
								p.setCooldown(Material.CROSSBOW, 4);
								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"화살뿌리기 "+sa.get(p.getName()) + "/6").create());
								}
								else {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"SpreadingArrows "+sa.get(p.getName()) + "/6").create());
								}
							}
						}
						p.setCooldown(Material.YELLOW_TERRACOTTA, 1);
						e.damage(bbArrow(ar), p);
						Holding.holding(p, e, 2l);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								e.teleport(ev.getEntity().getLocation().clone().add(0, -0.5, 0));
								Holding.holding(p, e, 2l);
							}

						}, 1);
						ev.getEntity().remove();
					}
				}
			}
		}
	}

	public void Archery(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity)
		{
			Projectile a = (Projectile)d.getDamager();
			if(a.getShooter() instanceof Player) {
				Player p = (Player)a.getShooter();
				if(ClassData.pc.get(p.getUniqueId()) == 6)
				{
					LivingEntity le = (LivingEntity) d.getEntity();

					if(p.getInventory().getItemInMainHand().getType()==Material.BOW && !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
					{
						dset2(d, p, (1+asd.Archery.get(p.getUniqueId())*0.035), le, 14);
						le.setArrowsInBody(le.getArrowsInBody()+1);
						le.setArrowCooldown(600);
						if(Proficiency.getpro(p) >=1 && !p.hasCooldown(Material.CROSSBOW)) {
							if(sa.getOrDefault(p.getName() , 7)<6) {
								sa.computeIfPresent(p.getName(), (k,v) -> v+1);
								p.setCooldown(Material.CROSSBOW, 4);
								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"화살뿌리기 "+sa.get(p.getName()) + "/6").create());
								}
								else {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.BLUE+"SpreadingArrows "+sa.get(p.getName()) + "/6").create());
								}
							}
						}
					}
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity)
		{
			{
				Player p = (Player)d.getDamager();
				LivingEntity le = (LivingEntity) d.getEntity();
				if(ClassData.pc.get(p.getUniqueId()) == 6)
				{

					if(p.getInventory().getItemInMainHand().getType()==Material.BOW && !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
					{
						if(d.getDamage()>0) {
							le.setArrowsInBody(le.getArrowsInBody()+1);
							le.setArrowCooldown(600);
							dset2(d, p, (1+asd.Archery.get(p.getUniqueId())*0.035), le, 14);
						}
					}
				}
			}
		}
	}
}