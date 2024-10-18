package io.github.chw3021.classes.swordman;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.io.Serializable;
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
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Swordskills extends Pak implements Listener, Serializable {


	/**
	 *
	 */

	private static transient final long serialVersionUID = -4553653005963571208L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> rscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();
	private HashMap<UUID, Integer> guard = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> guardcool = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> str2 = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> str2t = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> str3 = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> str3t = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> sdnc = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sdnct = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sdncu = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> sfsh = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> sfsht = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> falls = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fallst = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> divau = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> diva = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> divat = new HashMap<UUID, Integer>();

	private HashMap<UUID, Location> afts = new HashMap<UUID, Location>();
	private HashMap<UUID, Integer> aftst = new HashMap<UUID, Integer>();


	private String path = new File("").getAbsolutePath();
	private SwordSkillsData ssd;


	private static final Swordskills instance = new Swordskills ();
	public static Swordskills getInstance()
	{
		return instance;
	}


	public void er(PluginEnableEvent ev)
	{
		SwordSkillsData s = new SwordSkillsData(SwordSkillsData.loadData(path +"/plugins/RPGskills/SwordSkillsData.data"));
		ssd = s;
	}


	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Swordskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				SwordSkillsData s = new SwordSkillsData(SwordSkillsData.loadData(path +"/plugins/RPGskills/SwordSkillsData.data"));
				ssd = s;
			}

		}
	}


	public void nepreventer(PlayerJoinEvent ev)
	{
		SwordSkillsData s = new SwordSkillsData(SwordSkillsData.loadData(path +"/plugins/RPGskills/SwordSkillsData.data"));
		ssd = s;

	}


	public void SwordDrive(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();



		if(ClassData.pc.get(p.getUniqueId()) == 0 && ssd.SwordDrive.getOrDefault(p.getUniqueId(), 0)>=1)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !(p.isSneaking()))
			{
				double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				ev.setCancelled(true);

				final Location l = gettargetblock(p,4);
				if(!l.getBlock().isPassable()) {
					l.setY(l.getY()+1);}
				l.setDirection(p.getLocation().getDirection());
				if(l.getBlock().isPassable()) {
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("지진")
							.ename("SwordDrive")
							.slot(0)
							.hm(sdcooldown)
							.skillUse(() -> {
								if(Proficiency.getpro(p)>=2) {
									frcooldown.computeIfPresent(p.getName(), (k,v)-> v-1000);
								}

								p.teleport(l);
								p.setFallDistance(0);
								p.playSound(p.getLocation(), Sound.ENTITY_HOSTILE_BIG_FALL, 1.0f, 0.1f);
								p.playSound(p.getLocation(), Sound.BLOCK_BASALT_BREAK, 1.0f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1.0f, 2f);
								p.getWorld().spawnParticle(Particle.CRIT, l, 300, 3+Proficiency.getpro(p), 1, 3+Proficiency.getpro(p));
								p.getWorld().spawnParticle(Particle.ENCHANTED_HIT, l, 300, 3+Proficiency.getpro(p), 1, 3+Proficiency.getpro(p));
								p.getWorld().spawnParticle(Particle.WHITE_ASH, l, 300, 3+Proficiency.getpro(p), 1, 3+Proficiency.getpro(p));
								p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20+(int) (ssd.SwordDrive.get(p.getUniqueId())*2), (int) (ssd.SwordDrive.get(p.getUniqueId())*0.1), false, false));
								for (Entity e : p.getWorld().getNearbyEntities(l, 3+Proficiency.getpro(p), 2+Proficiency.getpro(p), 3+Proficiency.getpro(p)))
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
										atk1(0.65*(1 + ssd.SwordDrive.get(p.getUniqueId())*0.0536), p, le);
									}
								}

								if(aftst.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(aftst.get(p.getUniqueId()));
									aftst.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											afts.putIfAbsent(p.getUniqueId(), l);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										afts.remove(p.getUniqueId());
									}
								}, 25);
								aftst.put(p.getUniqueId(), task);

							});
					bd.execute();
				}
			}
		}
	}


	public void Spike(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();


		if(ClassData.pc.get(p.getUniqueId()) == 0 && afts.containsKey(p.getUniqueId()))
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !(p.isSneaking()))
			{
				ev.setCancelled(true);

				afts.get(p.getUniqueId());
				if(Proficiency.getpro(p)>=2) {
					frcooldown.computeIfPresent(p.getName(), (k,v)-> v-1000);
				}

				if(aftst.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(aftst.get(p.getUniqueId()));
					aftst.remove(p.getUniqueId());
				}
				afts.remove(p.getUniqueId());

				ArrayList<Location> line = new ArrayList<Location>();
				HashSet<LivingEntity> les = new HashSet<LivingEntity>();
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2f);
				AtomicInteger j = new AtomicInteger(0);
				for(double d = 0; d <= 5; d += 0.1) {
					Location pl = p.getEyeLocation();
					pl.add(pl.clone().getDirection().normalize().multiply(d));
					if(!pl.getBlock().isPassable()) {
						break;
					}
					line.add(pl);
				}
				line.forEach(i -> {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP,0.1f, 2f);
							p.setCooldown(CAREFUL, 3);
							p.teleport(i);
							p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 20, 3,3,3,0);
							p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 20, 1,1,1,0);
							for (Entity e : p.getWorld().getNearbyEntities(i,3, 3, 3))
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
								if ((!(e == p))&& e instanceof LivingEntity && !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
								{
									LivingEntity le = (LivingEntity)e;
									les.add(le);
									le.teleport(i);
									Holding.holding(p, le, 5l);
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

						p.setCooldown(CAREFUL, 3);
						p.swingMainHand();
						for(LivingEntity le: les) {
							atk1(0.73 *(1+ ssd.SwordDrive.get(p.getUniqueId())*0.042), p, le);

						}

					}
				}, j.incrementAndGet()/50);

			}
		}
	}

	public void Swoop(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{
			Player p = (Player)d.getDamager();
			LivingEntity e = (LivingEntity)d.getEntity();
			final Location fl = p.getLocation().clone();

			if(ClassData.pc.get(p.getUniqueId()) == 0 && ssd.Swoop.getOrDefault(p.getUniqueId(), 0)>=1) {
				if((p.isSneaking()) && !p.hasCooldown(Material.YELLOW_TERRACOTTA))
				{
					if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
					{

						double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
						SkillBuilder bd = new SkillBuilder()
								.player(p)
								.cooldown(sec)
								.kname("급습")
								.ename("Swoop")
								.slot(1)
								.hm(swcooldown)
								.skillUse(() -> {
									if(Proficiency.getpro(p)>=2) {
										frcooldown.computeIfPresent(p.getName(), (k,v)-> v-1000);
									}

									HashSet<LivingEntity> les = new HashSet<>();
									AtomicInteger j = new AtomicInteger();
									if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
									{
										LivingEntity le = (LivingEntity)e;

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
										les.add(le);
										Location l = le.getLocation();
										l.setDirection(p.getLocation().getDirection());
										p.teleport(l);
										for (Entity e1 : p.getWorld().getNearbyEntities(l, 5+Proficiency.getpro(p), 5+Proficiency.getpro(p), 5+Proficiency.getpro(p)))
										{
											if ((!(e1 == p))&& e1 instanceof LivingEntity&& !(e1.hasMetadata("fake"))&& !(e1.hasMetadata("portal")))
											{
												LivingEntity le1 = (LivingEntity)e1;
												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
													@Override
													public void run()
													{
														Holding.invur(p, 6l);
														Location l = le1.getLocation();
														l.setDirection(p.getLocation().getDirection());
														p.teleport(l);
														p.setCooldown(CAREFUL, 3);
														p.swingMainHand();
														p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le1.getLocation(), 1);
														atk1(0.87*(1+ssd.Swoop.get(p.getUniqueId())*0.067), p, le1);
														p.getWorld().spawnParticle(Particle.FLASH, le1.getLocation(), 1);
														p.playSound(le1.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
														les.add(le1);
														if(j.get()>4+Proficiency.getpro(p)) {
															return;
														}
													}

												}, j.incrementAndGet());
											}
										}
										p.getWorld().strikeLightningEffect(l);

										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run()
											{
												Holding.invur(p, 6l);
												p.teleport(fl);
												p.setCooldown(CAREFUL, 3);
												p.swingMainHand();
												if(Proficiency.getpro(p)>=1) {
													les.forEach(le1 ->{
														Holding.superholding(p, le1, 20l);
														Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
															@Override
															public void run()
															{
																atk1(0.87*(1+ssd.Swoop.get(p.getUniqueId())*0.067), p, le1);
																p.getWorld().spawnParticle(Particle.FLASH, le1.getLocation(), 1);
																p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le1.getLocation(), 5,0.1,1,0.1);
																p.playSound(le1.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
															}

														}, 20);
													});
												}
											}

										}, j.incrementAndGet()+1);

									}
								});
						bd.execute();

					}
				}
			}
		}

	}
	final private ArrayList<Location> RisingBlades(Location il, double a) {


		ArrayList<Location> draw = new ArrayList<Location>();
		final Location pl = il.clone();
		for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
			draw.add(pl.clone().add(pl.clone().getDirection().rotateAroundY(an).normalize().multiply(a)));
		}
		draw.forEach(l -> {
			il.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,1,0.6,0.3,0.6);

		});
		return draw;
	}


	public void RisingBlades(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();


		if(ClassData.pc.get(p.getUniqueId()) == 0 && ssd.Rising.getOrDefault(p.getUniqueId(), 0)>=1) {
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") )
				{

					double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

					ev.setCancelled(true);
					if(sdnc.containsKey(p.getUniqueId())|| sdncu.containsKey(p.getUniqueId())) {
						return;
					}
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("올려베기")
							.ename("RisingBlades")
							.slot(2)
							.hm(rscooldown)
							.skillUse(() -> {
								if(Proficiency.getpro(p)>=2) {
									frcooldown.computeIfPresent(p.getName(), (k,v)-> v-1000);
								}
								if(fallst.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(fallst.get(p.getUniqueId()));
									fallst.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											falls.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 8);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										falls.remove(p.getUniqueId());
									}
								}, 40);
								fallst.put(p.getUniqueId(), task);

								AtomicInteger f = new AtomicInteger();
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 0, false,false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 50, 3, false,false));
								final double pro = 3.5+Proficiency.getpro(p);
								for(int i =0; i<3; i++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{

											p.setCooldown(CAREFUL, 4);
											p.swingOffHand();
											Location fl = p.getEyeLocation().clone();
											for(double d = 0; d <= 2.5; d += 0.1) {
												Location pl = fl.clone();
												pl.add(pl.clone().getDirection().normalize().multiply(d));
												pl.add(0, d*0.05, 0);
												if(!pl.getBlock().isPassable()) {
													break;
												}
												p.teleport(pl);
											}
											RisingBlades(fl, pro);
											p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.6f, f.incrementAndGet()*0.5f);
											p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.4f, f.get()*0.5f);
											for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),pro, pro, pro))
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
													atk1(0.45*(1+ssd.Rising.get(p.getUniqueId())*0.036), p, le,5);
													le.teleport(p);

												}
											}
										}
									}, i*3);
								}
							});
					bd.execute();

				} 

			}}
	}
	final private ArrayList<Location> FallenLeaves(Location il, double a) {


		ArrayList<Location> draw = new ArrayList<Location>();
		final Location pl = il.clone();
		for(double an = 0; an<Math.PI*2; an+=Math.PI/60) {
			draw.add(pl.clone().add(pl.clone().getDirection().rotateAroundY(an).normalize().multiply(a)).add(0, -a*0.3, 0));
		}
		draw.forEach(l -> {
			il.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,3,1,0.3,1);
			il.getWorld().spawnParticle(Particle.FALLING_SPORE_BLOSSOM, l,3,0.1,0.1,0.1);

		});
		return draw;
	}


	public void FallenLeaves(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 0 && falls.containsKey(p.getUniqueId())) {
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
				{
					ev.setCancelled(true);

					if(sdnc.containsKey(p.getUniqueId())|| sdncu.containsKey(p.getUniqueId())) {
						return;
					}

					if(fallst.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(fallst.get(p.getUniqueId()));
						fallst.remove(p.getUniqueId());
					}
					falls.remove(p.getUniqueId());



					if(divat.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(divat.get(p.getUniqueId()));
						divat.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								diva.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 9);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							diva.remove(p.getUniqueId());
						}
					}, 40);
					divat.put(p.getUniqueId(), task);


					if(Proficiency.getpro(p)>=2) {
						frcooldown.computeIfPresent(p.getName(), (k,v)-> v-1000);
					}


					p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 50, 3, false,false));
					for(int i =0; i<5; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.setCooldown(CAREFUL, 3);
								p.swingOffHand();
								ArrayList<Location> line = new ArrayList<Location>();
								p.playSound(p.getLocation(), Sound.BLOCK_AZALEA_LEAVES_FALL, 0.5f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_AZALEA_LEAVES_FALL, 0.5f, 1f);
								p.playSound(p.getLocation(), Sound.BLOCK_AZALEA_LEAVES_FALL, 0.5f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 2f);

								Location fl = p.getEyeLocation().clone();
								for(double d = 0; d <= 2; d += 0.1) {
									Location pl = fl.clone();
									pl.add(0, -d, 0);
									if(!pl.getBlock().isPassable()) {
										break;
									}
									line.add(pl);
								}

								line.forEach(i -> {
									p.teleport(i);
								});
								FallenLeaves(fl,4+Proficiency.getpro(p));
								for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),4+Proficiency.getpro(p), 4+Proficiency.getpro(p), 4+Proficiency.getpro(p)))
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
										atk1(0.45*(1+ssd.Rising.get(p.getUniqueId())*0.04), p, le);
										le.teleport(p);

									}
								}
							}
						}, i*2);
					}


				}

			}
		}
	}

	public void DividingAir(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 0 && diva.containsKey(p.getUniqueId())) {
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
				{
					ev.setCancelled(true);

					if(sdnc.containsKey(p.getUniqueId())|| sdncu.containsKey(p.getUniqueId())) {
						return;
					}

					if(divat.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(divat.get(p.getUniqueId()));
						divat.remove(p.getUniqueId());
					}
					diva.remove(p.getUniqueId());
					divau.put(p.getUniqueId(), 1);

					if(Proficiency.getpro(p)>=2) {
						frcooldown.computeIfPresent(p.getName(), (k,v)-> v-1000);
					}

					p.swingMainHand();
					ArrayList<Location> line1 = new ArrayList<Location>();
					ArrayList<Location> line2 = new ArrayList<Location>();

					Holding.superholding(p, p,20l);
					Holding.invur(p, 30l);

					Location fl = p.getLocation().clone();
					final Location pl = p.getEyeLocation().clone();
					final Location sl = pl.clone().add(pl.clone().getDirection().normalize().multiply(-7)).setDirection(pl.clone().getDirection()).clone().add(0, 3, 0);
					final World w = sl.getWorld();

					p.teleport(sl);
					p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1.0f, 2f);
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1.0f, 2f);
					p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 2f);
					p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0f);


					for(int d = 0; d <= 12; d ++) {
						line1.add(sl.clone().add(pl.clone().getDirection().normalize().multiply(d)));
						line2.add(sl.clone().add(0, -0.52, 0).add(pl.clone().getDirection().normalize().multiply(d)));
					}
					HashSet<LivingEntity> les = new HashSet<LivingEntity>();

					AtomicInteger j = new AtomicInteger();
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							line1.forEach(l -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										w.spawnParticle(Particle.SWEEP_ATTACK, l,100,4,4,4);
										w.spawnParticle(Particle.ENCHANTED_HIT, l,200,4,4,4);
										p.teleport(l);

										p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.3f, 1.5f);
										p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.3f, 2f);
										for (Entity e : w.getNearbyEntities(p.getLocation() ,4+Proficiency.getpro(p), 6+Proficiency.getpro(p), 4+Proficiency.getpro(p)))
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
												le.teleport(p);
												Holding.holding(p, le, 10l);
												les.add(le);

											}
										}
									}
								}, j.incrementAndGet()/12);
							});
							p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.ENTITY_GOAT_LONG_JUMP, 1.0f, 0f);
						}
					}, 5);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 10, 255, false,false));
							Holding.holding(p, p, 20l);
							w.spawnParticle(Particle.SWEEP_ATTACK, fl,400,4,4,4);
							w.spawnParticle(Particle.ENCHANTED_HIT, fl,500,4,4,4);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT_SHORT, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.ENTITY_GOAT_LONG_JUMP, 1.0f, 0f);
							p.teleport(sl);
							les.forEach(le -> {
								le.teleport(fl);
								Holding.holding(p, le, 20l);
							});
						}
					}, 10);

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_DEACTIVATE, 0.6f, 0.5f);
							line2.forEach(l -> {
								w.spawnParticle(Particle.FLASH, l,100,3,0.8,3,0);
								w.spawnParticle(Particle.CLOUD, l,300,5,0.1,5,0);
								w.spawnParticle(Particle.EXPLOSION, l,3,0.1,0.1,0.1,0);
							});
						}
					}, 15);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							line2.forEach(l -> {
								w.spawnParticle(Particle.SWEEP_ATTACK, l,50,1,1,1,0);
								w.spawnParticle(Particle.BLOCK, l,400,2,2,2,0, Material.GLOWSTONE.createBlockData());
								w.spawnParticle(Particle.CRIT, l,100,1,1,1,0);
								w.spawnParticle(Particle.CLOUD, l,60,0.8,3,0.8,0);
							});
							p.setCooldown(CAREFUL, 2);
							p.swingOffHand();
							p.setGliding(true);
							w.spawnParticle(Particle.SWEEP_ATTACK, fl,20,4,4,4);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 0f);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 2f);
							p.playSound(p.getLocation(), Sound.ENTITY_GOAT_LONG_JUMP, 1.0f, 0f);
							les.forEach(le -> {
								atk1(1.5*(1+ssd.Rising.get(p.getUniqueId())*0.09), p, le);
								le.teleport(fl);
							});

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									p.teleport(fl);
									divau.remove(p.getUniqueId());
								}
							}, 3);

						}
					}, 21);

				}

			}
		}
	}

	public void CriticalDraw(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 0 && ssd.CriticalDraw.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && p.isSneaking())
			{
				ev.setCancelled(true);
				if(diva.containsKey(p.getUniqueId())|| divau.containsKey(p.getUniqueId())|| falls.containsKey(p.getUniqueId())) {
					return;
				}
				double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("발도")
						.ename("CriticalDraw")
						.slot(3)
						.hm(cdcooldown)
						.skillUse(() -> {
							if(sdnct.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(sdnct.get(p.getUniqueId()));
								sdnct.remove(p.getUniqueId());
							}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									if(Proficiency.getpro(p)>=1) {
										sdnc.putIfAbsent(p.getUniqueId(), 0);
									}
								}
							}, 3);

							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									sdnc.remove(p.getUniqueId());
								}
							}, 25);
							sdnct.put(p.getUniqueId(), task);

							p.setCooldown(CAREFUL, 1);
							p.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 3, 255, false, false));
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.1f);
							ArrayList<Location> draw = new ArrayList<Location>();
							AtomicInteger j = new AtomicInteger();
							Location pl = p.getLocation().clone().add(0, 1, 0);
							Vector pv = pl.clone().add(1, 0, 0).toVector().subtract(pl.clone().toVector());

							for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
								draw.add(pl.clone().add(pv.rotateAroundY(an).normalize().multiply(6+Proficiency.getpro(p))));
							}
							draw.forEach(l -> {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l.clone().add(0, 0.2, 0),1);
									}
								}, j.incrementAndGet()/900);

							});
							for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(),6+Proficiency.getpro(p), 2+Proficiency.getpro(p), 6+Proficiency.getpro(p)))
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
									atk1(1.23*(1+ ssd.CriticalDraw.get(p.getUniqueId())*0.065), p, le);
								}
							}
						});
				bd.execute();
			}
		}

	}


	final private ArrayList<Location> SwordDance1(Location il, double a) {

		ArrayList<Location> line1 = new ArrayList<Location>();

		Location pl = il.clone();
		Vector pfv = il.clone().add(0, 1, 0).toVector().subtract(il.clone().toVector());
		Vector pv = pfv.clone().rotateAroundAxis(pl.clone().getDirection(), Math.PI/4);
		for(double an = 0; an > -Math.PI*2; an -= Math.PI/90) {
			Location sw = pl.clone().add(pl.clone().getDirection().normalize().rotateAroundAxis(pv, an).multiply(a));
			line1.add(sw);
		}
		return line1;
	}

	final private ArrayList<Location> SwordDance2(Location il, double a) {

		ArrayList<Location> line2 = new ArrayList<Location>();
		Location pl2 = il.clone();
		Vector pfv2 = il.clone().add(0, 1, 0).toVector().subtract(il.clone().toVector());
		Vector pv2 = pfv2.clone().rotateAroundAxis(pl2.clone().getDirection(), -Math.PI/4);
		for(double an = 0; an > -Math.PI*2; an -= Math.PI/90) {
			Location sw2 = pl2.clone().add(pl2.clone().getDirection().normalize().rotateAroundAxis(pv2, an).multiply(a));
			line2.add(sw2);
		}
		return line2;
	}

	final private ArrayList<Location> SwordDancesight(Location il) {

		ArrayList<Location> sight = new ArrayList<Location>();
		Location eye = il.clone();
		for(double an = 0; an > -Math.PI*2; an -= Math.PI/90) {
			sight.add(eye.clone().setDirection(eye.clone().getDirection().rotateAroundY(an)));
		}
		return sight;
	}

	final private ArrayList<Location> SwordDancesight2(Location il) {

		ArrayList<Location> sight = new ArrayList<Location>();
		Location eye = il.clone();
		for(double an = 0; an < Math.PI*2; an += Math.PI/90) {
			sight.add(eye.clone().setDirection(eye.clone().getDirection().rotateAroundY(an)));
		}
		return sight;
	}

	public void SwordDance(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 0&& sdnc.containsKey(p.getUniqueId())&& !diva.containsKey(p.getUniqueId())&& !divau.containsKey(p.getUniqueId())&& !falls.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && p.isSneaking())
			{
				ev.setCancelled(true);

				if(sfsht.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(sfsht.get(p.getUniqueId()));
					sfsht.remove(p.getUniqueId());
				}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						if(Proficiency.getpro(p)>=2) {
							sfsh.putIfAbsent(p.getUniqueId(), 0);
						}
					}
				}, 20);

				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						sfsh.remove(p.getUniqueId());
					}
				}, 60);
				sfsht.put(p.getUniqueId(), task);

				if(sdnct.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(sdnct.get(p.getUniqueId()));
					sdnct.remove(p.getUniqueId());
				}
				sdnc.remove(p.getUniqueId());

				sdncu.put(p.getUniqueId(), 1);


				if(Proficiency.getpro(p)>=2) {
					frcooldown.computeIfPresent(p.getName(), (k,v)-> v-1000);
				}

				final World w = p.getWorld();

				final Location eye = p.getEyeLocation().clone();

				final Location pl = p.getEyeLocation().clone();

				ArrayList<Location> line1 = SwordDance1(pl, 4+Proficiency.getpro(p));

				ArrayList<Location> line2 = SwordDance2(pl, 4+Proficiency.getpro(p));

				ArrayList<Location> sight = SwordDancesight(pl);
				ArrayList<Location> sight2 = SwordDancesight2(pl);

				p.setCooldown(CAREFUL, 3);
				p.playSound(eye, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.82f, 1.3f);
				p.swingMainHand();
				line1.forEach(l -> {
					w.spawnParticle(Particle.SWEEP_ATTACK, l,1,0.5,0.5,0.5);
					w.spawnParticle(Particle.FLASH, l,1,1,1,1);
				});

				for (Entity e : w.getNearbyEntities(p.getLocation() ,5+Proficiency.getpro(p), 5+Proficiency.getpro(p), 5+Proficiency.getpro(p)))
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
						atk1(0.5*(1+ ssd.CriticalDraw.get(p.getUniqueId())*0.045), p, le);
						Holding.holding(p, le, 10l);

					}
				}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						AtomicInteger j = new AtomicInteger();
						p.teleport(eye);

						p.setCooldown(CAREFUL, 3);
						p.playSound(eye, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.82f, 0.8f);
						p.swingOffHand();

						line2.forEach(l -> {
							w.spawnParticle(Particle.SWEEP_ATTACK, l,1,0.5,0.5,0.5);
							w.spawnParticle(Particle.FLASH, l,1,1,1,1);
						});
						sight2.forEach(l -> {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									p.teleport(l);
								}
							}, j.incrementAndGet()/120);
						});
						for (Entity e : w.getNearbyEntities(p.getLocation() ,6+Proficiency.getpro(p), 6+Proficiency.getpro(p), 6+Proficiency.getpro(p)))
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
								atk1(0.2*(1+ ssd.CriticalDraw.get(p.getUniqueId())*0.0335), p, le);
								Holding.holding(p, le, 10l);

							}
						}
						p.teleport(pl.clone().add(0, 0.5, 0));
						Holding.superholding(p, p, 6l);
					}
				}, 3);


				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						AtomicInteger j = new AtomicInteger();
						p.teleport(eye);

						p.setCooldown(CAREFUL, 3);
						p.swingOffHand();

						p.playSound(eye, Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 0.7f, 2f);
						p.playSound(eye, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.7f, 0.436f);
						line2.forEach(l -> {
							w.spawnParticle(Particle.SWEEP_ATTACK, l,1,0.5,0.5,0.5);
							w.spawnParticle(Particle.FLASH, l,1,1,1,1);
						});
						sight.forEach(l -> {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									p.teleport(l);
								}
							}, j.incrementAndGet()/120);
						});
						for (Entity e : w.getNearbyEntities(p.getLocation() ,4+Proficiency.getpro(p), 4+Proficiency.getpro(p), 4+Proficiency.getpro(p)))
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
								atk1(0.2*(1+ ssd.CriticalDraw.get(p.getUniqueId())*0.0335), p, le);
								Holding.holding(p, le, 10l);

							}
						}
						p.teleport(pl.clone().add(0, 0.5, 0));
						Holding.superholding(p, p, 6l);
					}
				}, 6);


				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						AtomicInteger j = new AtomicInteger();
						p.teleport(eye);

						p.setCooldown(CAREFUL, 3);
						p.swingOffHand();

						p.playSound(eye, Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 0.7f, 2f);
						p.playSound(eye, Sound.BLOCK_BEACON_ACTIVATE, 0.7f, 1.1f);
						p.playSound(eye, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.7f, 1.67f);
						line1.forEach(l -> {
							w.spawnParticle(Particle.SWEEP_ATTACK, l,1,0.5,0.5,0.5);
							w.spawnParticle(Particle.FLASH, l,1,1,1,1);
						});
						sight2.forEach(l -> {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									p.teleport(l);
								}
							}, j.incrementAndGet()/120);
						});
						for (Entity e : w.getNearbyEntities(p.getLocation() ,4+Proficiency.getpro(p), 4+Proficiency.getpro(p), 4+Proficiency.getpro(p)))
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
								atk1(0.2*(1+ ssd.CriticalDraw.get(p.getUniqueId())*0.0335), p, le);
								Holding.holding(p, le, 10l);

							}
						}
						p.teleport(pl.clone().add(0, 0.5, 0));
						Holding.superholding(p, p, 7l);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 10, 255, false,false));
					}
				}, 10);

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						sdncu.remove(p.getUniqueId());
						p.setCooldown(CAREFUL, 3);
						p.swingOffHand();
						p.setCooldown(CAREFUL, 2);
						p.swingMainHand();
						p.setGliding(true);

						p.teleport(eye);

						p.playSound(eye, Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.7f, 1.8f);
						p.playSound(eye, Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.7f, 0.1f);
						p.playSound(eye, Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 0.7f, 1.5f);
						p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.1f,0f);
						line1.forEach(l -> {
							w.spawnParticle(Particle.SWEEP_ATTACK, l,1,0.5,0.5,0.5);
							w.spawnParticle(Particle.FLASH, l,1,1,1,1);
						});

						line2.forEach(l -> {
							w.spawnParticle(Particle.SWEEP_ATTACK, l,1,0.5,0.5,0.5);
							w.spawnParticle(Particle.CLOUD, l,1,1,1,1);
						});
						for (Entity e : w.getNearbyEntities(p.getLocation() ,4+Proficiency.getpro(p), 4+Proficiency.getpro(p), 4+Proficiency.getpro(p)))
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
								atk1(0.6*(1+ ssd.CriticalDraw.get(p.getUniqueId())*0.045), p, le);
								le.teleport(p);
								Holding.superholding(p, le, 10l);

							}
						}
					}
				}, 17);
			}
		}

	}

	private ArrayList<Location> SoulFlourish(Location il, double a) {

		ArrayList<Location> line = new ArrayList<Location>();
		Location pl = il.clone();
		Vector pfv = il.clone().add(0, 1, 0).toVector().subtract(il.clone().toVector());
		Vector pv = pfv.clone().rotateAroundAxis(pl.clone().getDirection(), -Math.PI/4);
		Vector pv2 = pfv.clone().rotateAroundAxis(pl.clone().getDirection(), Math.PI/4);

		if(a%3 == 0) {
			for(double an = 0; an > -Math.PI*2; an -= Math.PI/90) {
				Location sw = pl.clone().add(pl.clone().getDirection().normalize().rotateAroundAxis(pv, an).multiply(a));
				line.add(sw);
			}
		}
		else if (a%3 == 1) {
			for(double an = 0; an > -Math.PI*2; an -= Math.PI/90) {
				Location sw = pl.clone().add(pl.clone().getDirection().normalize().rotateAroundAxis(pv2, an).multiply(a));
				line.add(sw);
			}
		}
		else if (a%3 ==2) {
			for(double an = Math.PI/2.5; an>-Math.PI/2.5; an-=Math.PI/90) {
				line.add(pl.clone().add(pl.getDirection().rotateAroundY(an).normalize().multiply(a)));
			}
		}
		else {
			for(double an=0; an <Math.PI*2; an += Math.PI) {
				Location eye = il.clone().add(il.clone().getDirection().multiply(a));
				Vector eyev = eye.getDirection().clone().rotateAroundY(Math.PI/2);
				Location al = eye.clone().setDirection(eye.getDirection().rotateAroundAxis(eyev, an).normalize());
				line.add(al.add(al.getDirection().clone().normalize().multiply(2)));
			}
		}

		line.forEach(l -> {
			l.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l,2,0.1,0.1,0.1,0.2);
			l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,2,0.1,0.1,0.1,0.1);

		});
		return line;
	}


	public void SoulFlourish(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 0 && sfsh.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && p.isSneaking())
			{
				ev.setCancelled(true);


				if(Proficiency.getpro(p)>=2) {
					frcooldown.computeIfPresent(p.getName(), (k,v)-> v-1000);
				}


				if(sfsht.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(sfsht.get(p.getUniqueId()));
					sfsht.remove(p.getUniqueId());
				}
				sfsh.remove(p.getUniqueId());

				final Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(2)).add(0, -0.6, 0);



				p.setCooldown(CAREFUL, 2);
				AtomicInteger j = new AtomicInteger(5);
				for(int i = 0; i <4; i++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							p.setCooldown(CAREFUL, 2);
							p.swingOffHand();
							SoulFlourish(p.getEyeLocation().clone(),j.getAndDecrement());
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 2f);
							p.playSound(p.getLocation(), Sound.PARTICLE_SOUL_ESCAPE, 1f, 2f);
							tl.getWorld().spawnParticle(Particle.SWEEP_ATTACK, tl ,30,5,5,5);
							for(Entity e : tl.getWorld().getNearbyEntities(tl,6, 6, 6)) {
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
									atk1(0.43*(1+ ssd.CriticalDraw.get(p.getUniqueId())*0.05), p, le);
									le.teleport(tl);
								}
							}
						}
					}, i*2);
				}
			}
		}

	}

	public void FlashyRush(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =3.5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);




		if(ClassData.pc.get(p.getUniqueId()) == 0) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
				{

					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("섬격")
							.ename("FlashyRush")
							.slot(5)
							.hm(frcooldown)
							.skillUse(() -> {
								p.setCooldown(CAREFUL, 3);
								p.swingOffHand();
								ArrayList<Location> line = new ArrayList<Location>();
								HashSet<LivingEntity> les = new HashSet<LivingEntity>();
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 2f);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0f);
								AtomicInteger j = new AtomicInteger(0);
								for(double d = 0; d <= 5; d += 0.1) {
									Location pl = p.getEyeLocation();
									pl.add(pl.clone().getDirection().normalize().multiply(d));
									if(!pl.getBlock().isPassable()) {
										break;
									}
									line.add(pl);
								}
								line.forEach(i -> {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											p.setCooldown(CAREFUL, 3);
											p.teleport(i);
											p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 6, 0.1,0.1,0.1,0);
											for (Entity e : p.getWorld().getNearbyEntities(i,1.5, 1.5, 1.5))
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
												if ((!(e == p))&& e instanceof LivingEntity && !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && !e.isDead())
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
	
										p.setCooldown(CAREFUL, 3);
										p.swingMainHand();
										for(LivingEntity le: les) {
											atk1(0.73 *(1+ ssd.FlashyRush.get(p.getUniqueId())*0.042), p, le);
											if(Proficiency.getpro(p)>=1) {
												Holding.holding(p, le, 10l);
												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
													@Override
													public void run()
													{
														atk1(0.73 *(1+ ssd.FlashyRush.get(p.getUniqueId())*0.042), p, le);
														Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
															@Override
															public void run()
															{
																if(le.isDead() && frcooldown.containsKey(p.getName())) {
																	frcooldown.remove(p.getName());
																}
															}
														}, 1/5);
													}
												}, 9);
											}
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
												@Override
												public void run()
												{
													if(le.isDead() && frcooldown.containsKey(p.getName())) {
														frcooldown.remove(p.getName());
													}
												}
											}, 1/5);
	
										}
	
									}
								}, j.incrementAndGet()/50);
							});
					bd.execute();

				} // adding players name + current system time in miliseconds

			}}
	}



	@SuppressWarnings("deprecation")
	public void strike(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 0 && ssd.Strike.getOrDefault(p.getUniqueId(), 0)>=1 && !p.hasCooldown(CAREFUL)) {

			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !(p.isSneaking()) && !(p.isOnGround()))
			{
				if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK))
				{
					double sec =3*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("일격")
							.ename("Strike")
							.slot(4)
							.hm(smcooldown)
							.skillUse(() -> {
								if(Proficiency.getpro(p)>=2) {
									frcooldown.computeIfPresent(p.getName(), (k,v)-> v-1000);
								}


								if(str2t.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(str2t.get(p.getUniqueId()));
									str2t.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											str2.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										str2.remove(p.getUniqueId());
									}
								}, 25);
								str2t.put(p.getUniqueId(), task);

								HashSet<Location> line = new HashSet<Location>();
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.8f, 1.5f);
								p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.6f, 1.5f);

								final Location tl = p.getEyeLocation().add(p.getEyeLocation().getDirection().clone().multiply(2.2));
								final Location fl = p.getLocation().clone();

								Vector v = fl.clone().getDirection().rotateAroundY(Math.PI/2);

								final double pro = 4+Proficiency.getpro(p);

								for(double an = -Math.PI/3; an<Math.PI/3; an+=Math.PI/90) {
									line.add(fl.clone().add(fl.clone().getDirection().rotateAroundAxis(v,an).normalize().multiply(pro)));
								}

								line.forEach(l -> {
									p.getWorld().spawnParticle(Particle.FLASH, l,5,0.1,0.1,0.1,0);
									p.getWorld().spawnParticle(Particle.CRIT, l,5,0.1,0.1,0.1,0);
									p.getWorld().spawnParticle(Particle.BLOCK, l,5,0.1,0.1,0.1,0, Material.GLOWSTONE.createBlockData());
								});

								for(Entity e: tl.getWorld().getNearbyEntities(tl,pro,pro,pro)) {
									if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
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
										LivingEntity le = (LivingEntity)e;
										atk1(0.8 *(1+ ssd.Strike.get(p.getUniqueId())*0.0669), p, le,5);
									}
								}
							});
					bd.execute();
				}
			}
		}
	}

	final private ArrayList<Location> strike2(Location il, int a) {

		ArrayList<Location> line = new ArrayList<Location>();
		for(double an = Math.PI/3; an>-Math.PI/3; an-=Math.PI/180) {
			Location pl = il.clone();
			Vector v = pl.clone().getDirection().rotateAroundY(Math.PI/2);
			pl.add(pl.getDirection().rotateAroundAxis(v,an).normalize().multiply(a));
			line.add(pl);
		}
		line.forEach(l -> {
			l.getWorld().spawnParticle(Particle.FLASH, l,5,0.1,0.1,0.1,0);
			l.getWorld().spawnParticle(Particle.CRIT, l,5,0.1,0.1,0.1,0);
			l.getWorld().spawnParticle(Particle.BLOCK, l,5,0.1,0.1,0.1,0, Material.GLOWSTONE.createBlockData());

		});
		return line;
	}


	@SuppressWarnings("deprecation")
	public void strike2(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 0 && str2.containsKey(p.getUniqueId()) && Proficiency.getpro(p)>=1&& !p.hasCooldown(CAREFUL)) {

			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !(p.isSneaking()) && !(p.isOnGround()))
			{
				if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);


					if(Proficiency.getpro(p)>=2) {
						frcooldown.computeIfPresent(p.getName(), (k,v)-> v-1000);
					}

					if(str2t.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(str2t.get(p.getUniqueId()));
						str2t.remove(p.getUniqueId());
					}
					str2.remove(p.getUniqueId());

					if(str3t.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(str3t.get(p.getUniqueId()));
						str3t.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=1) {
								str3.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 3);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							str3.remove(p.getUniqueId());
						}
					}, 30);
					str3t.put(p.getUniqueId(), task);

					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.9f, 0f);
					p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.7f, 0.5f);
					Location pl = p.getLocation().clone();
					AtomicInteger j = new AtomicInteger();
					HashSet<LivingEntity> les = new HashSet<LivingEntity>();
					for(int i =0; i<15; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								strike2(pl,j.incrementAndGet()).forEach(l -> {
									for (Entity a : l.getWorld().getNearbyEntities(l, 4, 4, 4))
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
											if(!les.contains(le)) {
												atk1(0.92 *(1+ ssd.Strike.get(p.getUniqueId())*0.078), p, le,5);
												les.add(le);
											}
										}
									}
								});
							}
						}, i);
					}
				}
			}
		}
	}



	final private ArrayList<Location> strike3(Location il, int a) {

		ArrayList<Location> line = new ArrayList<Location>();
		for(double an = Math.PI/3; an>-Math.PI/3; an-=Math.PI/180) {
			Location pl = il.clone();
			pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(a));
			line.add(pl);
		}
		line.forEach(l -> {
			l.getWorld().spawnParticle(Particle.FLASH, l,5,0.1,0.1,0.1,0);
			l.getWorld().spawnParticle(Particle.CRIT, l,5,0.1,0.1,0.1,0);
			l.getWorld().spawnParticle(Particle.BLOCK, l,5,0.1,0.1,0.1,0, Material.GLOWSTONE.createBlockData());

		});
		return line;
	}


	@SuppressWarnings("deprecation")
	public void strike3(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 0 && str3.containsKey(p.getUniqueId()) && Proficiency.getpro(p)>=2&& !p.hasCooldown(CAREFUL)) {

			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !(p.isSneaking()) && !(p.isOnGround()))
			{
				if((ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);


					if(Proficiency.getpro(p)>=2) {
						frcooldown.computeIfPresent(p.getName(), (k,v)-> v-1000);
					}

					if(str3t.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(str3t.get(p.getUniqueId()));
						str3t.remove(p.getUniqueId());
					}
					str3.remove(p.getUniqueId());

					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.8f, 0.6f);
					p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 1.3f);
					Location pl = p.getLocation().clone();
					AtomicInteger j = new AtomicInteger();
					HashSet<LivingEntity> les = new HashSet<LivingEntity>();
					for(int i =0; i<15; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								strike3(pl,j.incrementAndGet()).forEach(l -> {
									for (Entity a : l.getWorld().getNearbyEntities(l, 4, 4, 4))
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
											if(!les.contains(le)) {
												atk1(0.94 *(1+ ssd.Strike.get(p.getUniqueId())*0.078), p, le,5);
												les.add(le);
											}
										}
									}
								});
							}
						}, i);
					}
				}
			}
		}
	}

	final private HashSet<Location> ultline(Location l, Location l2) {
		HashSet<Location> line = new HashSet<Location>();
		for(double d = 0; d<l.distance(l2); d+=0.2) {
			line.add(l.clone().add(l2.clone().toVector().subtract(l.clone().toVector()).normalize().multiply(d)));
		}
		return line;
	}

	final private void ult(ArrayList<Location> lel, World w) {
		AtomicInteger j = new AtomicInteger();
		lel.forEach(l -> {

			if(j.get()+1>=lel.size()) {
				ultline(l,lel.get(0)).forEach(a ->{
					w.spawnParticle(Particle.CLOUD, a, 10,0.2,0.2,0.2,0);
				});
			}
			else {
				ultline(l,lel.get(j.incrementAndGet())).forEach(a ->{
					w.spawnParticle(Particle.CLOUD, a, 10,0.2,0.2,0.2,0);
				});
			}
		});
	}

	public void ULT(PlayerItemHeldEvent ev)
    {
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();

		if(ClassData.pc.get(p.getUniqueId()) == 0 && ev.getNewSlot()==3 && (is.getType().name().contains("SWORD")) && p.isSneaking()&& Proficiency.getpro(p) >=1)
		{
			p.setCooldown(CAREFUL, 1);
			ev.setCancelled(true);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(60/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("비검")
					.ename("SwordStorm")
					.slot(6)
					.hm(sultcooldown)
					.skillUse(()->{
						
						final Location l = p.getLocation().clone().add(0, 0.6, 0);
						p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.4f, 0.1f);
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.6f);
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 0.6f, 0.6f);
						p.getWorld().spawnParticle(Particle.CLOUD, l, 800, 8, 2, 8);
						final Location pl = p.getLocation();
						ArrayList<Location> lel = new ArrayList<>();
						lel.add(l);
						pl.setY(pl.getY()+3);
						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 255, false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20, 255, false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20, 255, false,false));
						Holding.superholding(p,p, 20l);

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								for (Entity e : p.getWorld().getNearbyEntities(l, 20, 20, 20))
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
										lel.add(le.getLocation().clone().add(0, 0.7, 0));
										p.teleport(le);
										p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le.getLocation(), 1);
										p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
										Holding.superholding(p, le, 30l);
									}

								}
								lel.add(l);
							}
						}, 20);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								ult(lel, p.getWorld());
								p.teleport(pl);
							}
						}, 21);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								ult2(pl, 20);
								for (Entity e : p.getWorld().getNearbyEntities(l, 20, 20, 20))
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
									if ((!(e instanceof Player))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
									{
										LivingEntity le = (LivingEntity)e;
										atk1(16.9, p, le);
										p.getWorld().spawnParticle(Particle.FLASH, le.getLocation(), 1);
										p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 0.8f);
									}
								}
								p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1f);
							}
						}, 30);
					});
			bd.execute();

		}
	}

	private ArrayList<Location> ULT2(Location l, int a) {

		ArrayList<Location> line = new ArrayList<Location>();

		for(int i = -20; i<20; i++) {
			if(a == 1) {
				line.add(l.clone().add(i, 1, i));
			}
			else if(a==2) {
				line.add(l.clone().add(i, 1, 0));
			}
			else if(a==3) {
				line.add(l.clone().add(0, 1, i));
			}
			else if(a==4) {
				line.add(l.clone().add(i, 1, -i));
			}
			else if(a==5) {
				line.add(l.clone().add(-i, 1, i));
			}
			else if(a==6) {
				line.add(l.clone().add(-i, 1, 0));
			}
			else if(a==7) {
				line.add(l.clone().add(0, 1, -i));
			}
			else if(a==8) {
				line.add(l.clone().add(-i, 1, -i));
			}
			else if(a==9) {
				line.add(l.clone().add(-i, i, -i));
			}
			else if(a==10) {
				line.add(l.clone().add(i, i, i));
			}
			else if(a==11) {
				line.add(l.clone().add(-i, i, i));
			}
			else if(a==12) {
				line.add(l.clone().add(i, i, -i));
			}
			else {
				line.add(l.clone().add(i, 1, i));
				line.add(l.clone().add(i, 1, 0));
				line.add(l.clone().add(0, 1, i));
				line.add(l.clone().add(i, 1, -i));
				line.add(l.clone().add(-i, 1, i));
				line.add(l.clone().add(-i, 1, 0));
				line.add(l.clone().add(0, 1, -i));
				line.add(l.clone().add(-i, 1, -i));
				line.add(l.clone().add(-i, i, -i));
				line.add(l.clone().add(i, i, i));
				line.add(l.clone().add(-i, i, i));
				line.add(l.clone().add(i, i, -i));
			}
		}
		line.forEach(il -> {
			if(a==0) {
				il.getWorld().spawnParticle(Particle.FLASH, il,10,0.5,0.5,0.5,0);
			}
			il.getWorld().spawnParticle(Particle.SWEEP_ATTACK, il,50,0.5,0.5,0.5,0);
			il.getWorld().spawnParticle(Particle.BLOCK, il,25,0.5,0.5,0.5,0, Material.GLOWSTONE.createBlockData());

		});
		return line;
	}

	private HashSet<Location> ult2(Location il, int a) {

		HashSet<Location> line = new HashSet<Location>();
		Vector v = il.clone().add(0, 0, 1).toVector().subtract(il.toVector());
		for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
			Location pl = il.clone().add(0, 1, 0);
			pl.add(v.clone().rotateAroundY(an).normalize().multiply(a/2));
			line.add(pl);
		}
		line.forEach(l -> {
			l.getWorld().spawnParticle(Particle.FLASH, l,4,0.2,0.2,0.2,0);
			l.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,4,0.2,0.2,0.2,1);

		});

		return line;
	}


	private ArrayList<Location> Mindswords(Location l, Player p, ItemStack is) {

		ArrayList<Location> line = new ArrayList<Location>();

		line.add(l.clone().add(3, 1, 3));
		line.add(l.clone().add(3, 1, 0));
		line.add(l.clone().add(0, 1, 3));
		line.add(l.clone().add(3, 1, -3));
		line.add(l.clone().add(-3, 1, 3));
		line.add(l.clone().add(-3, 1, 0));
		line.add(l.clone().add(0, 1, -3));
		line.add(l.clone().add(-3, 1, -3));
		line.forEach(il -> {
			Item it = il.getWorld().dropItem(il, is);
			it.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			it.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
			it.setPickupDelay(999999);
			it.setOwner(p.getUniqueId());
			it.setThrower(p.getUniqueId());
			it.setGravity(false);
			it.setInvulnerable(true);
			it.setGlowing(true);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					it.remove();
				}
			}, 20);
		});
		return line;
	}


	public void ULT2(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}

		ItemStack is = p.getInventory().getItemInMainHand();

		if(ClassData.pc.get(p.getUniqueId()) == 0 && ev.getNewSlot()==4 && (is.getType().name().contains("SWORD")) && p.isSneaking() && Proficiency.getpro(p) >=2)
		{
			p.setCooldown(CAREFUL, 1);
			ev.setCancelled(true);
			final Location fl = p.getLocation().clone();
			final Location l = p.getLocation().clone().add(0, 4, 0);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(80*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("심검")
					.ename("MindSword")
					.slot(7)
					.hm(sult2cooldown)
					.skillUse(() -> {

						p.getWorld().spawnParticle(Particle.CLOUD, l, 800, 8, 2, 8);
						AtomicInteger j = new AtomicInteger();
						p.playSound(l, Sound.WEATHER_RAIN_ABOVE, 0.5f, 0f);
						p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, p.getLocation(), 10,2,1,2);
						p.playSound(l, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.5f, 0f);
						p.playSound(l, Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 0f);

						for(int i =0; i<45; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									ult2(fl.clone(), j.incrementAndGet());
									p.teleport(l.clone().add(0, 3, 0));
									Holding.holding(p, p, 30l);

									for (Entity a : l.getWorld().getNearbyEntities(l, 35, 35, 35))
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
											Holding.superholding(p, le, 30l);
										}
									}
								}
							}, i/2);
						}

						AtomicInteger j1 = new AtomicInteger();

						for(int i =0; i<12; i++) {

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									p.teleport(l.clone().add(0, 2, 0));
									Holding.holding(p, p, 30l);
									p.playSound(l, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.76f, 1.5f);
									p.playSound(l, Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 0.35f, 1.5f);
									ULT2(l.clone(), j1.incrementAndGet());

									for (Entity a : l.getWorld().getNearbyEntities(l, 25, 25, 25))
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
											Holding.superholding(p, le, 30l);
										}
									}
								}
							}, 35 + i *2);
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.teleport(l.clone().add(0, 2, 0));
								Holding.holding(p, p, 30l);
								p.playSound(l, Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 1.5f);
								p.playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 1.0f, 1.5f);
								Mindswords(l,p,is);
							}
						}, 62);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.playSound(l, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
								p.playSound(l, Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.5f);
								p.playSound(l, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1.3f);
								p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, l,2000,25,25,25,0);

								ULT2(l.clone(),0);
								Holding.invur(p, 30l);
								for (Entity a : l.getWorld().getNearbyEntities(l, 25, 25, 25))
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
										atk1(45.1, p, le);
										p.getWorld().spawnParticle(Particle.SWEEP_ATTACK, le.getLocation(), 1);
										p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
										p.playSound(le.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0f, 1.5f);
									}
								}
							}
						}, 80);
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

		if(ClassData.pc.get(p.getUniqueId()) == 0 && (is.getType().name().contains("SWORD")) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
		{
			ev.setCancelled(true);
		}

	}



	public void Equipment(PlayerItemDamageEvent e)
	{
		Player p = e.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 0)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD") && (p.getInventory().getItemInOffHand().getType().name().contains("SWORD")))
			{
				e.setCancelled(true);
			}
		}

	}


	public void guard1(EntityDamageEvent d)
	{
		if(d.getEntity() instanceof Player)
		{
			Player p = (Player)d.getEntity();

			if(ClassData.pc.get(p.getUniqueId()) == 0 && d.getCause() != DamageCause.FLY_INTO_WALL&& d.getCause() != DamageCause.VOID && ssd.Guard.getOrDefault(p.getUniqueId(), 0)>=1) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD")&& (p.isSneaking()))
				{
					double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
					guard.putIfAbsent(p.getUniqueId(), 7*(Proficiency.getpro(p)+1));
					if(guard.containsKey(p.getUniqueId()) && guard.get(p.getUniqueId()) >0) {
						d.setDamage(d.getDamage()*(0.2 - ssd.Guard.get(p.getUniqueId())*0.016));
						p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 0.6f, 1.3f);
						p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 0.5f, 0.3f);
						p.getWorld().spawnParticle(Particle.ITEM, p.getLocation(), 30,0.2,1,0.2, new ItemStack(Material.SHIELD));

						guard.computeIfPresent(p.getUniqueId(), (k,v) -> v-1);
						if(guard.get(p.getUniqueId())<=0) {
							guard.put(p.getUniqueId(), 0);
						}
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[막기] (" + guard.get(p.getUniqueId()) + "/" + (int)7*(Proficiency.getpro(p)+1) + ")").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Guard] (" + guard.get(p.getUniqueId()) + "/" + (int)7*(Proficiency.getpro(p)+1) + ")").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
						}

						if(guardcool.containsKey(p.getUniqueId())) {
							Bukkit.getScheduler().cancelTask(guardcool.get(p.getUniqueId()));
						}
						int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								if(guard.get(p.getUniqueId())<(int)7*(Proficiency.getpro(p)+1)) {
									guard.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);

									if(p.getLocale().equalsIgnoreCase("ko_kr")) {
										p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[막기] (" + guard.get(p.getUniqueId()) + "/" + (int)7*(Proficiency.getpro(p)+1) + ")").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
									}
									else {
										p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("[Guard] (" + guard.get(p.getUniqueId()) + "/" + (int)7*(Proficiency.getpro(p)+1) + ")").color(ChatColor.BOLD).color(ChatColor.BLUE).create());
									}
								}
								else {
									Bukkit.getScheduler().cancelTask(guardcool.get(p.getUniqueId()));
								}
							}
						}, (long) (sec*20)/(Proficiency.getpro(p)+1),3);
						guardcool.put(p.getUniqueId(), task);
					}

				}
			}
		}
	}

	public void Dualbladed(EntityDamageByEntityEvent d)
	{

		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();

			if(ClassData.pc.get(p.getUniqueId()) == 0) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
				{
					dset2(d, p, 1.6+ssd.Dualbladed.get(p.getUniqueId())*0.03896, le, 14);
				}
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{
			Projectile ar = (Projectile)d.getDamager();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				LivingEntity le = (LivingEntity)d.getEntity();
				if(ClassData.pc.get(p.getUniqueId()) == 0) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("SWORD"))
					{
						dset2(d, p, 1.6+ssd.Dualbladed.get(p.getUniqueId())*0.03896, le, 14);
					}
				}
			}
		}
	}

}