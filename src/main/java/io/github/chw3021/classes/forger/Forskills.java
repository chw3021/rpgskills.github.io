package io.github.chw3021.classes.forger;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.commons.SkillUseEvent;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.google.common.collect.HashMultimap;
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

import org.bukkit.attribute.Attribute;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Particle.TargetColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Forskills extends Pak {

	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();

	private HashMap<Player, Integer> mga = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> mgar = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> mgat = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> mgaovercount = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> mgaover = new HashMap<Player, Integer>();
	private HashMap<UUID, Integer> mgaovercountt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> mgaovert = new HashMap<UUID, Integer>();

	private HashMultimap<UUID, UUID> heshpair = HashMultimap.create();
	private HashMap<UUID, Integer> heshc = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> heshtask = new HashMap<UUID, Integer>();



	private HashMap<UUID, Integer> railsc = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> railsct = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> railcan = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> railcant = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> spctr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> spctrt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> bmwv = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> bmwvt = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> compr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> comprt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> plzgrd = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> plzgrdt = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> prmig = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> prmigt = new HashMap<UUID, Integer>();

	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private ForSkillsData fsd;


	private static final Forskills instance = new Forskills ();
	public static Forskills getInstance()
	{
		return instance;
	}

	private HashMap<UUID, Location> rayLoc = new HashMap<UUID, Location>();
	private HashMap<UUID, Location> rayfl = new HashMap<UUID, Location>();
	private HashMap<UUID, GameMode> raygm = new HashMap<UUID, GameMode>();
	private HashMap<UUID, Integer> rayt1 = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> rayt2 = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> rayt3 = new HashMap<UUID, Integer>();


	public void er(PluginEnableEvent ev)
	{
		ForSkillsData f = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
		fsd =f;
	}


	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Forskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				ForSkillsData f = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
				fsd =f;
			}

		}
	}


	public void nepreventer(PlayerJoinEvent ev)
	{
		ForSkillsData f = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
		fsd =f;

	}


	public void MachineGun(PlayerItemHeldEvent ev)
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 16 && (p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && p.isSneaking()) && !p.hasCooldown(Material.ARROW)){
			ev.setCancelled(true);
			p.setCooldown(Material.ARROW, 3);
			if(ev.getPreviousSlot()==0) {
				if(ev.getNewSlot()!=8) {
					mgat.put(p, 0);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"화살 선택").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Arrow Selected").create());
					}
					return;

				}
				else {
					mgat.put(p, 1);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"탄알 선택").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Bullet Selected").create());
					}
					return;
				}
			}
			else if(ev.getPreviousSlot()==8) {
				if(ev.getNewSlot()==0) {
					mgat.put(p, 0);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"화살 선택").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Arrow Selected").create());
					}
					return;

				}
				else {
					mgat.put(p, 1);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"탄알 선택").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Bullet Selected").create());
					}
					return;
				}
			}
			else {
				if(ev.getNewSlot()>ev.getPreviousSlot()) {
					mgat.put(p, 0);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"화살 선택").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Arrow Selected").create());
					}
					return;

				}
				else {
					mgat.put(p, 1);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"탄알 선택").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.AQUA+"Bullet Selected").create());
					}
					return;
				}
			}
		}
	}


	public void MachineGun(ProjectileHitEvent ev)
	{

		if(ev.getEntity().hasMetadata("mgbul"))
		{
			Projectile sn = ev.getEntity();
			Player p = (Player) sn.getShooter();
			Location l = ev.getHitBlock() != null ? ev.getHitBlock().getLocation() : ev.getHitEntity().getLocation();

			for (Entity a : l.getWorld().getNearbyEntities(l, 0.5, 0.5, 0.5))
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
					p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
					atk1(0.0658*(1+fsd.MachineGun.get(p.getUniqueId())*0.064)*(1+(mgaovercount.getOrDefault(p, 1)/100d))* (mgaover.containsKey(p)?2:1), p, le,14);
				}
			}

		}
	}

	final private void overHeating(Player p) {
		mgaovercount.computeIfPresent(p, (k,v) -> v+5);
		mgaovercount.putIfAbsent(p, 0);
		if(mgaovercount.get(p)>=100) {
			if(mgaovert.containsKey(p.getUniqueId())) {
				Bukkit.getScheduler().cancelTask(mgaovert.get(p.getUniqueId()));
			}
			mgaovercount.put(p, 100);
			mgaover.putIfAbsent(p, 0);
			int task2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					mgaover.remove(p);
				}
			}, 75);
			mgaovert.put(p.getUniqueId(), task2);
		}
		if(mgaovercountt.containsKey(p.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(mgaovercountt.get(p.getUniqueId()));
		}
		int task1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run()
			{
				mgaovercount.remove(p);
			}
		}, 75);
		mgaovercountt.put(p.getUniqueId(), task1);
	}

	public void MachineGun(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking())
		{




			if(ClassData.pc.get(p.getUniqueId()) == 16 && fsd.MachineGun.getOrDefault(p.getUniqueId(), 0) >=1)
			{
				ev.setCancelled(true);
				if(mga.containsKey(p)) {
					if(Proficiency.getpro(p)>=1) {
						mgaovercount.computeIfPresent(p, (k,v) -> v+1);
						mgaovercount.putIfAbsent(p, 0);
						if(mgaovercount.get(p)>=100) {
							if(mgaovert.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(mgaovert.get(p.getUniqueId()));
							}
							mgaovercount.put(p, 100);
							mgaover.putIfAbsent(p, 0);
							int task2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									mgaover.remove(p);
								}
							}, 75);
							mgaovert.put(p.getUniqueId(), task2);
						}
						if(mgaovercountt.containsKey(p.getUniqueId())) {
							Bukkit.getScheduler().cancelTask(mgaovercountt.get(p.getUniqueId()));
						}
						int task1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								mgaovercount.remove(p);
							}
						}, 75);
						mgaovercountt.put(p.getUniqueId(), task1);
					}
					if(mgaover.containsKey(p)) {
						mga.compute(p, (k,v) -> v-2);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("기관총("+mga.get(p).toString()+"/200)" + ChatColor.RED+" [과열!]").create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("MachineGun("+mga.get(p).toString()+"/200)" + ChatColor.RED+" [OVERHEATED!]").create());
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.6f, 1.7f);
								p.playEffect(EntityEffect.HURT_BERRY_BUSH);
							}
						}, 2);
					}
					else {
						mga.compute(p, (k,v) -> --v);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("기관총("+mga.get(p).toString()+"/200)").create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("MachineGun("+mga.get(p).toString()+"/200)").create());
						}
					}
					p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.76f, 1.6f);
					p.playEffect(EntityEffect.HURT_BERRY_BUSH);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,20,3,false,false));
					if(!mgat.containsKey(p) || mgat.get(p) == 0) {
						Arrow sn = (Arrow) p.launchProjectile(Arrow.class);
						ar1(sn, p, 0.0013*(1+fsd.MachineGun.get(p.getUniqueId())*0.0125)*(1+(mgaovercount.getOrDefault(p, 1)/100d) * (mgaover.containsKey(p)?2:1)));
						sn.setShooter(p);
						sn.setWeapon(p.getInventory().getItemInMainHand());
						sn.setPickupStatus(PickupStatus.DISALLOWED);
						sn.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(100));
						sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						sn.setMetadata("mg of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						sn.setInvulnerable(true);
						sn.setPierceLevel(127);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								sn.remove();
							}
						}, 5);
					}
					else if(mgat.get(p) == 1) {
						Snowball sn = (Snowball) p.launchProjectile(Snowball.class);
						sn.setItem(new ItemStack(Material.IRON_NUGGET));
						sn.setShooter(p);
						sn.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(90));
						sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						sn.setMetadata("mgbul", new FixedMetadataValue(RMain.getInstance(), true));
						sn.setInvulnerable(true);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								sn.remove();
							}
						}, 5);
					}
					if(mga.get(p)<=0) {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("기관총 재장전중...").create());
						}
						else {
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("MachineGun Reloading...").create());
						}
						Bukkit.getPluginManager().callEvent(new SkillUseEvent(p,(60-fsd.Development.get(p.getUniqueId())/3)/20d,0,ChatColor.BLUE+"기관총",ChatColor.BLUE+"MachineGun"));

						mga.remove(p);
						mgar.put(p, 1);
						p.playSound(p.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 0.76f, 1.6f);
						p.playSound(p.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 1f, 0f);
						p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_START, 1f, 0f);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_OFF, 1f, 0f);
								p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1f, 0f);
								mgar.remove(p);
								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("재장전 완료!").create());
								}
								else {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Reloaded!").create());
								}

							}
						}, 60-fsd.Development.get(p.getUniqueId())/3);
					}
				}
				else if(!mga.containsKey(p)&&mgar.containsKey(p)){
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("기관총 재장전중...").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("MachineGun Reloading...").create());
					}
				}
				else if(!mga.containsKey(p)&&!mgar.containsKey(p)){
					mga.put(p, 200);
				}
			}
		}
	}



	public void LightningCannon(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 16 && fsd.LightningCannon.getOrDefault(p.getUniqueId(), 0) >=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
			{
				if(rayt1.containsKey(p.getUniqueId())){
					return;
				}
				Action ac = ev.getAction();
				double sec = 10*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

				if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
				{
					p.setCooldown(CAREFUL, 2);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("천둥포")
							.ename("LightningCannon")
							.slot(1)
							.hm(gdcooldown)
							.skillUse(() -> {

								if(Proficiency.getpro(p)>=2) {
									overHeating(p);
								}
								
								if(spctrt.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(spctrt.get(p.getUniqueId()));
									spctrt.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=2) {
											spctr.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										spctr.remove(p.getUniqueId());
									}
								}, 40);
								spctrt.put(p.getUniqueId(), task);

								p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);
								final World w = p.getWorld();

								ArrayList<Location> line1 = new ArrayList<Location>();
								HashSet<LivingEntity> les = new HashSet<LivingEntity>();
								for(double d = 0.1; d <= 45.1; d += 0.2) {
									Location pl = p.getEyeLocation().clone();
									pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
									line1.add(pl);
								}
								for(Location l : line1) {
									w.spawnParticle(Particle.FLASH, l, 1,0.1,0.1,0.1,0);
								}
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
										ArrayList<Location> line = new ArrayList<Location>();
										for(double d = 0.1; d <= 45.1; d += 0.2) {
											Location pl = p.getEyeLocation().clone();
											pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
											line.add(pl);
										}
										p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 2);
										for(Location l : line) {
											w.spawnParticle(Particle.BLOCK, l,5, 0.25,0.25,0.25,0, getBd(Material.WHITE_GLAZED_TERRACOTTA));
											w.spawnParticle(Particle.ITEM_SNOWBALL, l,5, 0.25,0.25,0.25,0);
											w.spawnParticle(Particle.WAX_ON, l,5, 0.25,0.25,0.25,1);

											for (Entity a : p.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
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
										}

									}
								}, 10);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										for(LivingEntity le: les) {
											p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
											w.spawn(le.getLocation(), BlockDisplay.class, lightning ->{
												lightning.setSilent(true);
												lightning.setBlock(getBd(Material.WHITE_GLAZED_TERRACOTTA));
												lightning.setGlowColorOverride(Color.YELLOW);
												lightning.setGlowing(true);
												Bukkit.getScheduler().runTaskLater(RMain.getInstance(), () -> {
												    lightning.remove();
												}, 10L);
											});
											atk1(0.9*(1+fsd.LightningCannon.get(p.getUniqueId())*0.06), p, le,9);
											le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,60,1,false,false));

										}
									}
								}, 10);
							});
					bd.execute();


				} // adding players name + current system time in miliseconds

			}}
	}




	public void Spectral(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
			Action ac = ev.getAction();

			if(ClassData.pc.get(p.getUniqueId()) == 16) {
				if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&& spctr.containsKey(p.getUniqueId()))
				{
					if(Proficiency.getpro(p)>=2) {
						overHeating(p);
					}

					if(spctrt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(spctrt.get(p.getUniqueId()));
						spctrt.remove(p.getUniqueId());
					}
					spctr.remove(p.getUniqueId());



					if(bmwvt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(bmwvt.get(p.getUniqueId()));
						bmwvt.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								bmwv.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 3);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							bmwv.remove(p.getUniqueId());
						}
					}, 25);
					bmwvt.put(p.getUniqueId(), task);

					p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, 1, 2);
					p.playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1, 2);

					HashSet<Location> line = new HashSet<Location>();
					HashSet<LivingEntity> les = new HashSet<LivingEntity>();
					for(double d = 0.1; d <= 45.1; d += 0.2) {
						Location pl = p.getEyeLocation().clone();
						pl.add(p.getEyeLocation().getDirection().normalize().multiply(d));
						line.add(pl);
					}
					for(Location l : line) {
						p.getWorld().spawnParticle(Particle.BLOCK, l,5, 0.25,0.25,0.25,0, Material.PINK_GLAZED_TERRACOTTA.createBlockData());

						for (Entity a : p.getWorld().getNearbyEntities(l, 2, 2, 2))
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
								if(l.getBlock().isPassable() && le.hasAI()) {
									le.teleport(l);
								}
								Holding.holding(p, le, 15l);
							}
						}
					}
					for(LivingEntity le: les) {
						atk1(0.5*(1+fsd.LightningCannon.get(p.getUniqueId())*0.05), p, le);
						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,60,1,false,false));

					}


				}

			}
		}
	}

	final private ArrayList<Location> BeamWave(Location il, int a) {

		ArrayList<Location> line = new ArrayList<Location>();
		for(double an = Math.PI/6; an>-Math.PI/6; an-=Math.PI/180) {
			Location al = il.clone().add(il.clone().getDirection().multiply(a));
			al.add(al.getDirection().clone().normalize().multiply(3).rotateAroundY(an));
			line.add(al);
		}
		line.forEach(l -> {
			l.getWorld().spawnParticle(Particle.GLOW, l,1,0.1,0.1,0.1,0);
			l.getWorld().spawnParticle(Particle.BLOCK, l,3,0.1,0.1,0.1,0, Material.PURPLE_GLAZED_TERRACOTTA.createBlockData());

		});
		return line;
	}


	public void BeamWave(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
			Action ac = ev.getAction();

			if(ClassData.pc.get(p.getUniqueId()) == 16) {
				if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&&bmwv.containsKey(p.getUniqueId()))
				{
					if(bmwvt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(bmwvt.get(p.getUniqueId()));
						bmwvt.remove(p.getUniqueId());
					}
					bmwv.remove(p.getUniqueId());

					if(Proficiency.getpro(p)>=2) {
						overHeating(p);
					}

					final Location pl = p.getEyeLocation().clone();
					p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, 1, 2);
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 2);

					ArrayList<Location> line = new ArrayList<Location>();
					AtomicInteger j = new AtomicInteger();
					for(int i =0; i<45; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								HashSet<LivingEntity> les = new HashSet<LivingEntity>();
								BeamWave(pl,j.incrementAndGet()).forEach(bl -> line.add(bl));
								for(Location l : line) {

									for (Entity a : l.getWorld().getNearbyEntities(l, 2, 3, 2))
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
								}
								for(LivingEntity le: les) {
									p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
									atk1(0.5*(1+fsd.LightningCannon.get(p.getUniqueId())*0.03), p, le);
								}
							}
						}, i);
					}
				}

			}
		}
	}



	public void TNTLauncher(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 16&& fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)>=1) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking())
			{
				double sec = 10*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("TNT발사기")
						.ename("TNTLauncher")
						.slot(2)
						.hm(cdcooldown)
						.skillUse(() -> {
							if(comprt.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(comprt.get(p.getUniqueId()));
								comprt.remove(p.getUniqueId());
							}
							if(Proficiency.getpro(p)>=2) {
								overHeating(p);
							}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									if(Proficiency.getpro(p)>=1) {
										compr.putIfAbsent(p.getUniqueId(), 0);
									}
								}
							}, 3);

							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									compr.remove(p.getUniqueId());
								}
							}, 50);
							comprt.put(p.getUniqueId(), task);

							ItemStack is = new ItemStack(Material.TNT);
							for(int i =1; i<5; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										p.playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1.0f, 2f);
										p.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1.0f, 0f);
										Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
										solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
										solid.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										solid.setMetadata("tnt of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										solid.setGlowing(true);
										solid.setPickupDelay(9999);
										solid.setVelocity(p.getLocation().getDirection().normalize().multiply(3));
										
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 100, false, false));
										Vector v = p.getLocation().getDirection().clone().normalize().multiply(-0.4);
										p.setVelocity(v);
									}
								}, i*2);
							}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("tnt of"+p.getName())).forEach(t ->{
										p.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, t.getLocation(), 1,0.1,0.1,0.1,0);
										p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 0f);
										for(Entity e : t.getNearbyEntities(5, 5, 5)) {
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
												{
													p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
													atk1(0.35*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.0312), p, le,14);
												}
											}

										}
										t.remove();
									});
								}
							}, 12);
						});
				bd.execute();
			}
		}

	}



	public void Compressor(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking()&& compr.containsKey(p.getUniqueId()))
		{
			if(ClassData.pc.get(p.getUniqueId()) == 16) {
				ev.setCancelled(true);

				if(Proficiency.getpro(p)>=2) {
					overHeating(p);
				}

				if(comprt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(comprt.get(p.getUniqueId()));
					comprt.remove(p.getUniqueId());
				}
				compr.remove(p.getUniqueId());



				if(plzgrdt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(plzgrdt.get(p.getUniqueId()));
					plzgrdt.remove(p.getUniqueId());
				}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						if(Proficiency.getpro(p)>=2) {
							plzgrd.putIfAbsent(p.getUniqueId(), 0);
						}
					}
				}, 3);

				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						plzgrd.remove(p.getUniqueId());
					}
				}, 50);
				plzgrdt.put(p.getUniqueId(), task);


                Location tl = gettargetblock(p,9).clone();
				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2);
				p.playSound(p.getLocation(), Sound.BLOCK_METAL_HIT, 1, 0);
				p.playSound(p.getLocation(), Sound.BLOCK_DRIPSTONE_BLOCK_BREAK, 1, 0);
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 0);

				tl.getWorld().spawnParticle(Particle.BLOCK, tl,2000, 6,2,6,0, Material.LIGHT_GRAY_GLAZED_TERRACOTTA.createBlockData());
				tl.getWorld().spawnParticle(Particle.WHITE_ASH, tl,2000, 6,1,6,0);
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
					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
					{
						LivingEntity le = (LivingEntity)e;
						atk1(0.42*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.04), p, le);
						Holding.holding(p, le, 10l);

					}

				}
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 100, false, false));
				Vector v = p.getLocation().getDirection().clone().normalize().multiply(-3.2);
				p.setVelocity(v);
			}
		}

	}



	public void PlazmaGrenade(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking() && plzgrd.containsKey(p.getUniqueId()))
		{

			if(ClassData.pc.get(p.getUniqueId()) == 16) {
				ev.setCancelled(true);

				if(Proficiency.getpro(p)>=2) {
					overHeating(p);
				}

				if(plzgrdt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(plzgrdt.get(p.getUniqueId()));
					plzgrdt.remove(p.getUniqueId());
				}
				plzgrd.remove(p.getUniqueId());


				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 100, false, false));
				Vector v = p.getLocation().getDirection().clone().normalize().multiply(-2.1);
				p.setVelocity(v);

				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 0);
				p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST_FAR, 1, 0);


				Snowball sn = p.launchProjectile(Snowball.class);
				sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				sn.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				sn.setMetadata("plzg", new FixedMetadataValue(RMain.getInstance(), true));
				sn.setShooter(p);
				sn.setItem(new ItemStack(Material.CYAN_GLAZED_TERRACOTTA));
				sn.setGlowing(true);
				sn.setVelocity(sn.getVelocity().multiply(2.5));
			}
		}

	}


	public void PlazmaGrenade(ProjectileHitEvent d)
	{
		if(d.getEntity().hasMetadata("plzg")) {
			Player p = (Player) d.getEntity().getShooter();
			
			Location tl = d.getHitBlock() == null ? d.getEntity().getLocation() : d.getHitBlock().getLocation();
			
			p.getWorld().spawnParticle(Particle.END_ROD, tl, 100, 5,5,5);
			p.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, tl, 100, 5,5,5);
			p.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, tl, 200, 5,5,5);
			p.playSound(tl, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 2);
			p.playSound(tl, Sound.BLOCK_LODESTONE_HIT, 1, 0);
			p.playSound(tl, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
			p.playSound(tl, Sound.BLOCK_SCULK_SENSOR_BREAK, 1, 2);
			p.playSound(tl, Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1, 0);
			for (Entity e : tl.getWorld().getNearbyEntities(tl, 7, 6, 7))
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
					{
						p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
						atk1(1.42*(1+fsd.TNTLauncher.get(p.getUniqueId())*0.1), p, le,9);
						Holding.holding(p, le, 50l);
						le.teleport(tl);

					}
				}
			}
		}
	}

	final private ArrayList<Location> RailSMG(Location il){
		ArrayList<Location> line = new ArrayList<Location>();
		for(double d = 0.1; d <= 26.1; d += 0.2) {
			Location pl = il.clone();
			pl.add(il.getDirection().normalize().multiply(d));
			line.add(pl);
		}
		return line;
	}

	final private void RailSMG(Player p) {

		if(railsct.containsKey(p.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(railsct.get(p.getUniqueId()));
			railsct.remove(p.getUniqueId());
		}

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run() {
				if(Proficiency.getpro(p)>=1) {
					railsc.putIfAbsent(p.getUniqueId(), 0);
				}
			}
		}, 3);

		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run() {
				railsc.remove(p.getUniqueId());
			}
		}, 35);
		railsct.put(p.getUniqueId(), task);

		p.getLocation();
		HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		for(int i =1; i<10; i++) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 2f);
					p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1f, 2f);
					ArrayList<Location> line = RailSMG(p.getEyeLocation().clone());
					line.forEach(l -> {

						p.getWorld().spawnParticle(Particle.BLOCK, l.add(0, -0.289, 0),4, 0.005,0.005,0.005,0, getBd(Material.CYAN_GLAZED_TERRACOTTA));
						p.getWorld().spawnParticle(Particle.GLOW, l.add(0, -0.289, 0),1, 0.005,0.005,0.005,0);

						for (Entity a : p.getWorld().getNearbyEntities(l, 0.5, 0.5, 0.5))
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
				}
			}, i);
		}
		for(int i =1; i<10; i++) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					for(LivingEntity le : les) {
						p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
						atk1(0.113*(1+fsd.RailSMG.get(p.getUniqueId())*0.042), p, le,9);
						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,15,1,false,false));
					}

				}
			}, i+1/10);
		
		}

	}


	public void RailSMG(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 16 && fsd.RailSMG.getOrDefault(p.getUniqueId(), 0) >=1) {
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
			{
				if(rayt1.containsKey(p.getUniqueId())){
					return;
				}
				Action ac = ev.getAction();
				double sec =4*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
				if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
				{
					p.setCooldown(CAREFUL, 2);

					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("전자기관단총")
							.ename("RailSMG")
							.slot(3)
							.hm(frcooldown)
							.skillUse(() -> {
								if(Proficiency.getpro(p)>=2) {
									overHeating(p);
								}
								RailSMG(p);
							});
					bd.execute();


				} // adding players name + current system time in miliseconds

			}}
	}

	private void screw(Location il) {
	    World w = il.getWorld();
	    double maxDistance = 25.0; // 드릴 길이 최대값
	    double initialMultiplier = 0.2; // 시작 크기
	    double angleStep = Math.PI / 60;

	    // 25블럭까지 확장되도록 설정
	    for (double angle = 0; angle < Math.PI * 4; angle += angleStep) {
	        double distanceFactor = (angle / (Math.PI * 4)) * maxDistance;
	        
	        for (double directionAngle : new double[]{Math.PI / 6, -Math.PI / 6}) {
	            Location particleLocation = il.clone();
	            particleLocation.add(
	                particleLocation.getDirection()
	                    .clone()
	                    .rotateAroundY(directionAngle)
	                    .rotateAroundAxis(il.getDirection(), angle)
	                    .normalize()
	                    .multiply(initialMultiplier + distanceFactor)
	            );
	            w.spawnParticle(Particle.BLOCK, particleLocation, 2, 0.1, 0.1, 0.1, 0.1,getBd(Material.CYAN_GLAZED_TERRACOTTA));
	        }
	    }
	}


	public void RailScrew(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
			Action ac = ev.getAction();

			if(ClassData.pc.get(p.getUniqueId()) == 16) {
				if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&&railsc.containsKey(p.getUniqueId()))
				{

					if(railsct.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(railsct.get(p.getUniqueId()));
						railsct.remove(p.getUniqueId());
					}
					railsc.remove(p.getUniqueId());
					

					if(railcant.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(railcant.get(p.getUniqueId()));
						railcant.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								railcan.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 5);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							railcan.remove(p.getUniqueId());
						}
					}, 35);
					railcant.put(p.getUniqueId(), task);
					
					
					if(Proficiency.getpro(p)>=2) {
						overHeating(p);
					}
					
					
					HashSet<LivingEntity> les = new HashSet<LivingEntity>();
					for(int i =1; i<5; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 2f);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_GRINDSTONE_USE, 1f, 2f);

								HashSet<Location> line = new HashSet<Location>();
								final Location pl = p.getEyeLocation().clone();
								for(double d = 0; d <= 26; d += 0.5) {
									line.add(pl.clone().add(p.getEyeLocation().getDirection().normalize().multiply(d)));
								}
								screw(pl.clone().add(0, -0.2, 0));
								line.forEach(l ->{
									for (Entity a : p.getWorld().getNearbyEntities(l, 1, 1, 1))
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
											if(l.getBlock().isPassable() && le.hasAI()) {
												le.teleport(l);
											}
											Holding.holding(p, le, 3l);
										}
									}
								});
								les.forEach(le ->{
									p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
									atk1(0.32*(1+fsd.RailSMG.get(p.getUniqueId())*0.092), p, le);
								});
							}
						}, i*3);
					}

				}

			}}
	}


	public void RailCannon(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
		{
			Action ac = ev.getAction();

			if(ClassData.pc.get(p.getUniqueId()) == 16) {
				if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&&railcan.containsKey(p.getUniqueId()))
				{

					if(railcant.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(railcant.get(p.getUniqueId()));
						railcant.remove(p.getUniqueId());
					}
					railcan.remove(p.getUniqueId());
					if(Proficiency.getpro(p)>=2) {
						overHeating(p);
					}
					
					HashSet<LivingEntity> les = new HashSet<LivingEntity>();

					p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1f, 2f);
					p.playSound(p.getLocation(), Sound.BLOCK_TRIAL_SPAWNER_OMINOUS_ACTIVATE, 1f, 2f);

					HashSet<Location> line = new HashSet<Location>();
					
					final Location pl = p.getEyeLocation().clone();
					for(double d = 0; d <= 26; d += 0.5) {
						line.add(pl.clone().add(p.getEyeLocation().getDirection().normalize().multiply(d)));
					}
					line.forEach(l ->{
						final Location parl = l.clone().add(0, -0.289, 0);
						p.getWorld().spawnParticle(Particle.BLOCK, parl,6, 0.05,0.05,0.05,0, getBd(Material.CYAN_GLAZED_TERRACOTTA));
						p.getWorld().spawnParticle(Particle.BLOCK_CRUMBLE, parl,6, 0.05,0.05,0.05,0, getBd(Material.CYAN_GLAZED_TERRACOTTA));
						p.getWorld().spawnParticle(Particle.TRAIL, pl,4, 0.05,0.05,0.05,0, new TargetColor(parl, Color.AQUA));
						for (Entity a : p.getWorld().getNearbyEntities(l, 1, 1, 1))
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
								if(l.getBlock().isPassable() && le.hasAI()) {
									le.teleport(l);
								}
								Holding.holding(p, le, 3l);
							}
						}
					});
					les.forEach(le ->{
						p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
						atk1(1.66*(1+fsd.RailSMG.get(p.getUniqueId())*0.06), p, le);
						Holding.holding(p, le, 10l);
					});

				}

			}}
	}
	public void Shockwave(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{
			Player p = (Player)d.getDamager();
			double sec =7*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			d.getEntity();





			if(ClassData.pc.get(p.getUniqueId()) == 16 && fsd.DoubleBarrel.getOrDefault(p.getUniqueId(),0)>=1) {
				if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && (p.isSneaking()) && !(p.hasCooldown(Material.YELLOW_TERRACOTTA)))
				{
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("충격파")
							.ename("Shockwave")
							.slot(4)
							.hm(stcooldown)
							.skillUse(() -> {
								if(Proficiency.getpro(p)>=2) {
									overHeating(p);
								}
								
								
								
								for(int i = 0; i<2; i++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											Location t = gettargetblock(p,3);
											
											for(int i = 0; i < 15; i++) {
												Arrow ar = p.getWorld().spawnArrow(p.getEyeLocation().add(0, -0.8, 0), p.getEyeLocation().getDirection(), 2f, 20f);
												ar.remove();
												Item barrel = p.getWorld().dropItemNaturally(p.getEyeLocation(), new ItemStack(Material.BEACON));
												barrel.setVelocity(ar.getVelocity());
												barrel.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
												barrel.setMetadata("barrel of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
												barrel.setGlowing(true);
												barrel.setPickupDelay(9999);
											}
											
											p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 0.8f, 0.2f);
											p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 0.8f, 1.4f);

											for(Entity e: t.getWorld().getNearbyEntities(t,4,4,4)) {
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
													le.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(2.5));
													le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 5, 5, false, false));

													p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
													atk1(0.135, p, le);

													if(Proficiency.getpro(p)>=1) {
														Holding.holding(p, le, 30l);
													}
												}

											}
										}
									}, i*5);

								}
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										p.getWorld().getEntities().stream().filter(e -> e.hasMetadata("barrel of"+p.getName())).forEach(t ->t.remove());
									}
								}, 13);
							});
					bd.execute();

				}
			}
		}
	}



	public void Barrier(EntityDamageByEntityEvent d)
	{
	
		if(d.getEntity() instanceof Player)
		{
			Player p = (Player)d.getEntity();
	
			if(ClassData.pc.get(p.getUniqueId()) == 16 && Proficiency.getpro(p) >=2 && fsd.DoubleBarrel.getOrDefault(p.getUniqueId(),0)>=1) {
				if(p.hasLineOfSight(d.getDamager())) {
					d.setDamage(d.getDamage()*0.5);
				}
			}
		}
	}


	@SuppressWarnings("deprecation")
	public void HoneyMissile(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec = 20*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);


		if(ClassData.pc.get(p.getUniqueId()) == 16 && fsd.HoneyMissile.getOrDefault(p.getUniqueId(), 0) >=1 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && !p.hasCooldown(CAREFUL)) {
			if(rayt1.containsKey(p.getUniqueId())){
				ult2ActivateReady(p,raygm.get(p.getUniqueId()), rayfl.get(p.getUniqueId()));
				ult2Activate(p);
				return;
			}
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK))
			{
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("점착미사일")
						.ename("HoneyMissile")
						.slot(5)
						.hm(smcooldown)
						.skillUse(() -> {
							if(Proficiency.getpro(p)>=2) {
								overHeating(p);
							}
							if(prmigt.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(prmigt.get(p.getUniqueId()));
								prmigt.remove(p.getUniqueId());
							}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									if(Proficiency.getpro(p)>=2) {
										prmig.putIfAbsent(p.getUniqueId(), 0);
									}
								}
							}, 3);

							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									prmig.remove(p.getUniqueId());
								}
							}, 80);
							prmigt.put(p.getUniqueId(), task);

							Location l = p.getEyeLocation().clone();
							l.add(l.getDirection().normalize().multiply(25.1));
							
							Firework hesh = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK_ROCKET);
							hesh.setShotAtAngle(true);
							hesh.setMetadata("hesh of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							hesh.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							hesh.setShooter(p);
							hesh.setVelocity(l.toVector().subtract(p.getLocation().toVector()).normalize().multiply(1));
							FireworkMeta fm = hesh.getFireworkMeta();

							FireworkEffect effect = FireworkEffect.builder()
									.with(Type.BURST)
									.withColor(Color.ORANGE)
									.flicker(true)
									.trail(true)
									.build();
							fm.setPower(3);
							fm.addEffect(effect);
							hesh.setFireworkMeta(fm);
						});
				bd.execute();

			}
		}
	}



	public void HESH(EntityDamageByEntityEvent d)
	{



		if(d.getDamager() instanceof Firework)
		{
			Firework fw = (Firework) d.getDamager();
			if(fw.getShooter() instanceof Player) {
				Player p = (Player) fw.getShooter();
				if (fw.hasMetadata("hesh of"+p.getName())) {
					d.setCancelled(true);
				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && d.getDamage()>=0)
		{
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(heshpair.containsEntry(p.getUniqueId(), le.getUniqueId())) {
				p.getWorld().playSound(le.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1, 0);
				le.getWorld().spawnParticle(Particle.EXPLOSION, le.getLocation(), 1,0.1,0.1,0.1);
				le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 35, 2,2,2,3);
				dset0(d, p, 0.085*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.01),le,9);
				heshc.computeIfPresent(le.getUniqueId(), (k,v) -> v-1);
				if(heshc.containsKey(le.getUniqueId()) && heshc.get(le.getUniqueId())<=0) {
					p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					dset0(d, p, 0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.32)*(Proficiency.getpro(p)>=1 ? 2:1),le,9);
					le.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, le.getLocation(), 1,0.1,0.1,0.1);
					le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
					heshc.remove(le.getUniqueId());
					heshpair.remove(p.getUniqueId(), le.getUniqueId());
					Bukkit.getScheduler().cancelTask(heshtask.get(le.getUniqueId()));
					heshtask.remove(le.getUniqueId());
				}
				if(heshc.containsKey(le.getUniqueId()) && d.getDamage()>=le.getHealth()) {
					p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
					dset0(d, p, 0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.32)*(Proficiency.getpro(p)>=1 ? 2:1),le,9);
					le.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, le.getLocation(), 1,0.1,0.1,0.1);
					le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
					heshc.remove(le.getUniqueId());
					heshpair.remove(p.getUniqueId(), le.getUniqueId());
					Bukkit.getScheduler().cancelTask(heshtask.get(le.getUniqueId()));
					heshtask.remove(le.getUniqueId());

				}
			}
		}
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity&& d.getDamage()>=0)
		{
			Arrow arrow = (Arrow) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(arrow.getShooter() instanceof Player) {
				Player p = (Player) arrow.getShooter();
				if(heshpair.containsEntry(p.getUniqueId(), le.getUniqueId())) {
					p.getWorld().playSound(le.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1, 0);
					le.getWorld().spawnParticle(Particle.EXPLOSION, le.getLocation(), 1,0.1,0.1,0.1);
					le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 35, 2,2,2,3);
					dset0(d, p, 0.085*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.01),le,9);
					heshc.computeIfPresent(le.getUniqueId(), (k,v) -> v-1);
					if(heshc.containsKey(le.getUniqueId()) && heshc.get(le.getUniqueId())<=0) {
						p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
						dset0(d, p, 0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.32)*(Proficiency.getpro(p)>=1 ? 2:1),le,9);
						le.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, le.getLocation(), 1,0.1,0.1,0.1);
						le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
						heshc.remove(le.getUniqueId());
						heshpair.remove(p.getUniqueId(), le.getUniqueId());
						Bukkit.getScheduler().cancelTask(heshtask.get(le.getUniqueId()));
						heshtask.remove(le.getUniqueId());
					}
					if(heshc.containsKey(le.getUniqueId()) && d.getDamage()>=le.getHealth()) {
						p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
						dset0(d, p, 0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.32)*(Proficiency.getpro(p)>=1 ? 2:1),le,9);
						le.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, le.getLocation(), 1,0.1,0.1,0.1);
						le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
						heshc.remove(le.getUniqueId());
						heshpair.remove(p.getUniqueId(), le.getUniqueId());
						Bukkit.getScheduler().cancelTask(heshtask.get(le.getUniqueId()));
						heshtask.remove(le.getUniqueId());

					}
				}

			}
		}
	}


	public void HESH(FireworkExplodeEvent f)
	{



		if(f.getEntity().getShooter() instanceof Player) {
			Firework fw = f.getEntity();
			Player p = (Player) fw.getShooter();
			if (fw.hasMetadata("hesh of"+p.getName())) {

				fw.getWorld().spawnParticle(Particle.LANDING_HONEY, fw.getLocation(), 50, 2,2,2);
				for(Entity e : fw.getNearbyEntities(6.5, 6.5, 6.5)) {
					if (e instanceof Player)
					{

						Player p1 = (Player) e;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								return;
							}
						}
					}
					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && !heshc.containsKey(e.getUniqueId())&& !heshpair.containsEntry(p.getUniqueId(), e.getUniqueId()))
					{
						LivingEntity le = (LivingEntity)e;
						heshpair.put(p.getUniqueId(), le.getUniqueId());
						heshc.put(le.getUniqueId(), 20);
						int tass = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								if(heshc.containsKey(le.getUniqueId())) {

									p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
									atk1(0.53*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.042)*(Proficiency.getpro(p)>=1 ? 2:1), p, le,9);
									le.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, le.getLocation(), 1,0.1,0.1,0.1);
									le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 100, 2,2,2,3);
									heshc.remove(le.getUniqueId());
								}
								heshpair.remove(p.getUniqueId(), le.getUniqueId());
							}
						}, 400);
						heshtask.put(le.getUniqueId(), tass);
						le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,100,1,false,false));
						le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,100,1,false,false));
					}

				}
			}

		}
	}



	@SuppressWarnings("deprecation")
	public void Detonator(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 16 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) &&(ac!= Action.RIGHT_CLICK_AIR)&&(ac!= Action.RIGHT_CLICK_AIR)&& prmig.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);
				if(!heshpair.containsKey(p.getUniqueId())) {
					return;
				}

				if(prmigt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(prmigt.get(p.getUniqueId()));
					prmigt.remove(p.getUniqueId());
				}
				prmig.remove(p.getUniqueId());

				HashSet<UUID> leus = new HashSet<>();
				heshpair.get(p.getUniqueId()).forEach(leu -> {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							if(Bukkit.getEntity(leu) != null && !Bukkit.getEntity(leu).isDead()) {
								LivingEntity le = (LivingEntity) Bukkit.getEntity(leu);

								p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
								atk1(1.1*(1+fsd.HoneyMissile.get(p.getUniqueId())*0.0789)*(Proficiency.getpro(p)), p, le,9);
								p.getWorld().playSound(le.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
								le.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, le.getLocation(), 1,0.1,0.1,0.1);
								le.getWorld().spawnParticle(Particle.LANDING_HONEY, le.getLocation(), 50, 2,2,2,3);
								leus.add(le.getUniqueId());
								for(UUID leu : leus) {
									if(heshtask.containsKey(leu)) {
										Bukkit.getScheduler().cancelTask(heshtask.remove(leu));
									}
									if(heshc.containsKey(le.getUniqueId())) {
										heshc.remove(leu);
									}
								}
								heshpair.removeAll(p.getUniqueId());
							}
						}
					}, 5);
				});


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
		if(ClassData.pc.get(p.getUniqueId()) == 16 && ((is.getType().name().contains("PICKAXE"))) && ev.getNewSlot()==3 && p.isSneaking()&& Proficiency.getpro(p) >=1)
		{
			ev.setCancelled(true);
			p.setCooldown(CAREFUL, 2);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(70/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("용의숨결발사기")
					.ename("DragonBreather")
					.slot(6)
					.hm(sultcooldown)
					.skillUse(() -> {
						if(Proficiency.getpro(p)>=2) {
							overHeating(p);
						}
						HashSet<LivingEntity> les = new HashSet<>();
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1, 2);
						Holding.invur(p, 80l);
						for(int c=0;c<30;c++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									HashSet<Location> line = new HashSet<>();
									for(double point = 0.1; point<60.1; point+=0.5) {
										line.add(p.getEyeLocation().add(p.getLocation().getDirection().normalize().multiply(point)));
									}
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 2);

									line.forEach(l -> {
										for(Entity e: l.getWorld().getNearbyEntities(l,1,1,1)) {
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
											if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
												les.add(le);
											}
										}
										l.getWorld().spawnParticle(Particle.DRAGON_BREATH, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0.1);
										l.getWorld().spawnParticle(Particle.END_ROD, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0);
										l.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, l.add(0, -1, 0), 5, 0.3,0.3,0.3,0.1);
									});
								}
							}, c*2+20);
						}

						for(int c=0;c<30;c++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{

									for(LivingEntity le : les) {

										p.setCooldown(Material.YELLOW_TERRACOTTA, 2);
										atk1(1.05, p, le);
										le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 2, false, false));

									}
								}
							}, c*2+21);
						}
					});
			bd.execute();
		}
	}



	final private void ult2ActivateReady(Player p,GameMode pgm, Location fl) {

		Bukkit.getScheduler().cancelTask(rayt1.remove(p.getUniqueId()));
		p.setGameMode(pgm);
		p.teleport(fl);
		p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 0f);
		p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, rayLoc.get(p.getUniqueId()), 500, 6, 2, 6);
		for(Entity e: p.getWorld().getNearbyEntities(rayLoc.get(p.getUniqueId()),6,300,6)) {
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
			if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) {
				LivingEntity le = (LivingEntity)e;
				Holding.holding(p, le, 70l);
			}
		}
	}
	final private void ult2Activate(Player p) {

		p.setCooldown(CAREFUL, 10);
		final Location rayloc = rayLoc.get(p.getUniqueId()).clone();
		for(int i=0; i<50; i++) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					p.playSound(rayloc, Sound.BLOCK_BEACON_ACTIVATE, 0.1f, 2f);
					p.playSound(rayloc, Sound.BLOCK_NOTE_BLOCK_BASS, 0.3f, 0f);
					p.getWorld().spawnParticle(Particle.FLASH, rayloc, 6000, 6, 200, 6);
					ult2cir(p,1, rayloc);
					for(Entity e: rayloc.getWorld().getNearbyEntities(rayloc.clone(),6,300,6)) {
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
						if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) {
							LivingEntity le = (LivingEntity)e;
							Holding.holding(p, le, 10l);
							atk1(0.8, p, le);
						}
					}
				}
			}, i*2);
		}
		rayfl.remove(p.getUniqueId());
		raygm.remove(p.getUniqueId());
		rayLoc.remove(p.getUniqueId());
		Bukkit.getScheduler().cancelTask(rayt2.remove(p.getUniqueId()));
		Bukkit.getScheduler().cancelTask(rayt3.remove(p.getUniqueId()));
	}

	final private Location floorFinder(Location inloc){
		Location rl = inloc.clone();
		for(int i = 0; i<384; i++){
			if(!rl.getBlock().isPassable()){
				break;
			}
			else{
				rl.add(0,-1,0);
			}
			if(rl.getY()==-64){
				break;
			}
		}
		return rl;

	}

	final private ArrayList<Location> ult2cir(Player p, Integer parm, Location rayloc) {

		ArrayList<Location> draw = new ArrayList<Location>();
		Location pl = p.getLocation().clone().add(0, 1, 0);
		Vector pv = pl.clone().add(1, 0, 0).toVector().subtract(pl.clone().toVector());

		if(parm==0) {
			for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
				Location inloc = pl.clone().add(pv.rotateAroundY(an).normalize().multiply(6));
				draw.add(floorFinder(inloc));
			}
		}
		else {
			for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
				for(int in = 0; in<6; in++) {
					Location inloc = rayloc.clone().add(pv.rotateAroundY(an).normalize().multiply(in));
					draw.add(floorFinder(inloc));
				}
			}
		}
		draw.forEach(l -> {
			if(parm==0) {
				p.spawnParticle(Particle.DUST, l.clone().add(0, 0.5, 0),1, new Particle.DustOptions(Color.LIME, 1) );
			}
			else {
				p.spawnParticle(Particle.FLAME, l.clone().add(0, 0.5, 0),1);
			}
		});
		return draw;
	}

	public void ULT2(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();

		if(ClassData.pc.get(p.getUniqueId()) == 16 && ((is.getType().name().contains("PICKAXE"))) && p.isSneaking() && ev.getNewSlot()==4&& Proficiency.getpro(p) >=2)
		{
			p.setCooldown(CAREFUL, 2);
			ev.setCancelled(true);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(65*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("천공광선")
					.ename("Ray Of Heaven")
					.slot(7)
					.hm(sult2cooldown)
					.skillUse(() -> {
						if(Proficiency.getpro(p)>=2) {
							overHeating(p);
						}
						final GameMode pgm = p.getGameMode();
						final Location fl = p.getLocation().clone();
						raygm.put(p.getUniqueId(),pgm);
						rayfl.put(p.getUniqueId(),fl);
						p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 1.0f, 1.8f);
						p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1.0f, 0f);
						p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 100, 2, 2, 2);
						p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, p.getLocation(), 100, 2, 2, 2);
						p.setGameMode(GameMode.SPECTATOR);
						p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,50,1,false,false));
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,50,1,false,false));
						Holding.fly(p, 51l);
						AtomicInteger j = new AtomicInteger();
						int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
									p.sendTitle(org.bukkit.ChatColor.RED+"클릭시 즉시 호출",j.incrementAndGet()*2 +"%", 5, 5, 5);
								}
								else {
									p.sendTitle(org.bukkit.ChatColor.RED+"Click to Call",j.incrementAndGet()*2 +"%", 5, 5, 5);
								}
								ult2cir(p,0,null);
								rayLoc.put(p.getUniqueId(),p.getLocation().clone());
							}
						}, 0,1);
						rayt1.put(p.getUniqueId(),task);
						int task2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								ult2ActivateReady(p,pgm,fl);
							}
						}, 51);
						rayt2.put(p.getUniqueId(),task2);
						int task3 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								ult2Activate(p);
							}
						}, 56);
						rayt3.put(p.getUniqueId(),task3);
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

		if(ClassData.pc.get(p.getUniqueId()) == 16 && ((is.getType().name().contains("PICKAXE"))) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
		{
			ev.setCancelled(true);
		}

	}



	public void Equipment(PlayerItemDamageEvent e)
	{
		Player p = e.getPlayer();
		if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") )
		{




			if(ClassData.pc.get(p.getUniqueId()) == 16)
			{
				e.setCancelled(true);
			}
		}

	}
/*

	public void Damagegetter(ProjectileLaunchEvent e)
	{

		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();





			if(ClassData.pc.get(p.getUniqueId()) == 16) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
				{
						player_damage.put(p.getName(), p.getAttribute(Attribute.ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);

						if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						{
							player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
						}
				}
				else {
					player_damage.put(p.getName(), 0d);
				}
			}
		}
	}
	*/


	public void ArmorDecrease(EntityDamageByEntityEvent d)
	{
		if(d.getEntity() instanceof Player && d.getDamager() instanceof LivingEntity)
		{
			if(ClassData.pc.get(d.getEntity().getUniqueId()) == 16) {
				d.setDamage(d.getDamage()*1.6);
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof Player)
		{
			Projectile ar = (Projectile)d.getDamager();

			if(ar.getShooter() instanceof LivingEntity) {
				if(ClassData.pc.get(d.getEntity().getUniqueId()) == 16) {
					d.setDamage(d.getDamage()*1.6);
				}
			}
		}
	}


	public void Damagegetter(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity)
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();





			if(ClassData.pc.get(p.getUniqueId()) == 16) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
				{
					if(Proficiency.getpro(p)>=1) {
						dset2(d, p, (1+fsd.Development.get(p.getUniqueId())*0.057),le, 16);
					}
					else {
						dset2(d, p, (1+fsd.Development.get(p.getUniqueId())*0.057),le, 0);
					}
					if(le.hasMetadata("leader") || le.hasMetadata("boss")) {
						d.setDamage(d.getDamage()*2.5);
					}
				}
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity)
		{
			Projectile ar = (Projectile)d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();

			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();





				if(ClassData.pc.get(p.getUniqueId()) == 16) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")&& !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
					{
						if(Proficiency.getpro(p)>=1) {
							dset2(d, p, (1+fsd.Development.get(p.getUniqueId())*0.057),le, 16);
						}
						else {
							dset2(d, p, (1+fsd.Development.get(p.getUniqueId())*0.057),le, 14);
						}
						if(le.hasMetadata("leader") || le.hasMetadata("boss")) {
							d.setDamage(d.getDamage()*2.5);
						}
							/*player_damage.put(p.getName(), p.getAttribute(Attribute.ATTACK_DAMAGE).getValue() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)*0.5 + p.getLevel()/10);

							if (e.getType() == EntityType.ZOMBIE || e.getType() == EntityType.ZOMBIE_HORSE || e.getType() ==EntityType.ZOMBIE_VILLAGER || e.getType() == EntityType.ZOMBIFIED_PIGLIN|| e.getType() == EntityType.SKELETON || e.getType() == EntityType.SKELETON_HORSE || e.getType() == EntityType.WITHER_SKELETON || e.getType() == EntityType.HUSK || e.getType() == EntityType.WITHER || e.getType() == EntityType.STRAY || e.getType() == EntityType.PHANTOM || e.getType() == EntityType.DROWNED)
							{
								player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD)*2.5);
							}
							if (e.getType() == EntityType.SPIDER || e.getType() == EntityType.CAVE_SPIDER || e.getType() == EntityType.BEE || e.getType() == EntityType.SILVERFISH || e.getType() == EntityType.ENDERMITE)
							{
								player_damage.put(p.getName(),player_damage.get(p.getName())+ p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS)*2.5);

							}
							if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
							{
								player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier()*3);
							}*/
					}
					/*else {
						player_damage.put(p.getName(), 0d);
					}*/
				}
			}
		}
	}

}