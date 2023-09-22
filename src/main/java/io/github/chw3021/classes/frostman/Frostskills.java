package io.github.chw3021.classes.frostman;



import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.commons.SkillUseEvent;
import io.github.chw3021.items.armors.ArmorSet;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.entity.*;
import org.bukkit.event.player.*;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Frostskills extends Pak implements Listener, Serializable {


	/**
	 *
	 */


	private static transient final long serialVersionUID = -4738740919433352298L;
	private HashMap<String, Long> swcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();

	private HashMap<Player, Item> crystal = new HashMap<Player, Item>();
	private HashMap<UUID, Integer> frost = new HashMap<UUID, Integer>();
	private HashMap<UUID, Long> frostcooldown = new HashMap<UUID, Long>();
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();


	private HashMap<UUID, Integer> icefall = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> icefallt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> avlnch = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> avlncht = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> glcdr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> glcdrt = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> polarv = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> polarvt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> iccdp = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> iccdpt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> snball = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> snballt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> icebl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> iceblt = new HashMap<UUID, Integer>();

	private HashMap<UUID, UUID> frosted = new HashMap<UUID, UUID>();


	private String path = new File("").getAbsolutePath();
	private FrostSkillsData bsd;

	private static final Frostskills instance = new Frostskills ();
	public static Frostskills getInstance()
	{
		return instance;
	}


	public void er(PluginEnableEvent ev)
	{
		FrostSkillsData b = new FrostSkillsData(FrostSkillsData.loadData(path +"/plugins/RPGskills/FrostSkillsData.data"));
		bsd = b;
	}


	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("FrostSkills"))
		{
			e.setCancelled(true);
			FrostSkillsData b = new FrostSkillsData(FrostSkillsData.loadData(path +"/plugins/RPGskills/FrostSkillsData.data"));
			bsd = b;

		}
	}


	public void nepreventer(PlayerJoinEvent ev)
	{
		FrostSkillsData b = new FrostSkillsData(FrostSkillsData.loadData(path +"/plugins/RPGskills/FrostSkillsData.data"));
		bsd = b;

	}

	final private void crystalBreak(Player p){
		if(crystal.containsKey(p)) {
			Item solid = crystal.get(p);
			solid.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 400,3+(Proficiency.getpro(p)>=2?1.3:0),3+(Proficiency.getpro(p)>=2?1.3:0),3+(Proficiency.getpro(p)>=2?1.3:0),0.1,Material.PACKED_ICE.createBlockData());
			solid.getWorld().spawnParticle(Particle.BLOCK_CRACK, solid.getLocation(), 300,3+(Proficiency.getpro(p)>=2?1.3:0),3+(Proficiency.getpro(p)>=2?1.3:0),3+(Proficiency.getpro(p)>=2?1.3:0),0.1,Material.ICE.createBlockData());
			p.playSound(solid.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 0);
			p.playSound(solid.getLocation(), Sound.BLOCK_SNOW_BREAK, 1, 0);
			p.playSound(solid.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 1, 2);
			for (Entity e : solid.getNearbyEntities(3.5+(Proficiency.getpro(p)>=2?1.3:0), 3.5+(Proficiency.getpro(p)>=2?1.3:0), 3.5+(Proficiency.getpro(p)>=2?1.3:0)))
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
					atk0(0.765, bsd.FrozenCrystal.get(p.getUniqueId())*0.55, p, le);
					Holding.holding(p, le, 10l);
					if(Proficiency.getpro(p)>=1) {

						for(int i =0; i<4; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									atk0(0.5, bsd.FrozenCrystal.get(p.getUniqueId())*0.31, p, le);

									Holding.holding(p, le, 10l);
								}
							}, i);
						}
					}
				}
			}
			p.getWorld().getEntities().stream().filter(en -> en.hasMetadata("crystal of"+p.getName())).forEach(n -> n.remove());
			sdcooldown.put(p.getName(), System.currentTimeMillis());
			Bukkit.getPluginManager().callEvent(new SkillUseEvent(p,3*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d),0,"얼음수정","FrozenCrystal"));
			crystal.remove(p);
		}
	}

	public void FrozenCrystal(PlayerSwapHandItemsEvent ev)
	{

		Player p = ev.getPlayer();
		double sec =3*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);


		if(ClassData.pc.get(p.getUniqueId()) == 21&& bsd.FrozenCrystal.getOrDefault(p.getUniqueId(), 0)>=1 && p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && !(p.isSneaking()) && !crystal.containsKey(p))
		{

			ev.setCancelled(true);
			if(sdcooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
			{
				double timer = (sdcooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
				if(!(timer < 0)) // if timer is still more then 0 or 0
				{

					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("얼음수정 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
					}
					else {
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use FrozenCrystal").create());
					}
				}
				else // if timer is done
				{
					sdcooldown.remove(p.getName()); // removing player from HashMap
					p.setCooldown(Material.PACKED_ICE, 10);
					p.playSound(p.getLocation(), Sound.BLOCK_SNOW_PLACE, 1.0f, 2f);
					p.playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1.0f, 0f);
					ItemStack is = new ItemStack(Material.PACKED_ICE);
					Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
					solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					solid.setMetadata("crystal of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					solid.setPickupDelay(9999);
					solid.setVelocity(p.getLocation().getDirection().normalize().multiply(0.5));
					solid.setGravity(false);
					crystal.put(p, solid);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							crystalBreak(p);
						}
					}, 40);
				}
			}
			else // if cooldown doesn't have players name in it
			{
				p.setCooldown(Material.PACKED_ICE, 10);
				p.playSound(p.getLocation(), Sound.BLOCK_SNOW_PLACE, 1.0f, 2f);
				p.playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1.0f, 0f);
				ItemStack is = new ItemStack(Material.PACKED_ICE);
				Item solid = p.getWorld().dropItem(p.getEyeLocation(), is);
				solid.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				solid.setMetadata("crystal of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				solid.setPickupDelay(9999);
				solid.setVelocity(p.getLocation().getDirection().normalize().multiply(0.5));
				solid.setGravity(false);
				crystal.put(p, solid);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						crystalBreak(p);
					}
				}, 40);
			}
		}
	}



	public void FrozenCrystalbreak(PlayerSwapHandItemsEvent ev)
	{

		Player p = ev.getPlayer();


		if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && !(p.isSneaking()) && ClassData.pc.get(p.getUniqueId()) == 21)
		{
			ev.setCancelled(true);

			if(crystal.containsKey(p) && p.getCooldown(Material.PACKED_ICE)<=0)
			{
				crystalBreak(p);
			}
		}
	}


	public void Hailstones(PlayerSwapHandItemsEvent ev)
	{

		Player p = ev.getPlayer();
		double sec =9*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

		if(ClassData.pc.get(p.getUniqueId()) == 21&& bsd.Hailstones.getOrDefault(p.getUniqueId(), 0)>=1) {
			final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR)), 6).getLocation().setDirection(p.getLocation().getDirection());
			final Location el = new Location(p.getWorld(), l.getX(), l.getY()+5.5, l.getZ());
			if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && p.isSneaking())
			{
				ev.setCancelled(true);

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("우박")
						.ename("Hailstones")
						.slot(1)
						.hm(cdcooldown)
						.skillUse(() -> {

							if(avlncht.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(avlncht.get(p.getUniqueId()));
								avlncht.remove(p.getUniqueId());
							}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									if(Proficiency.getpro(p)>=1) {
										avlnch.putIfAbsent(p.getUniqueId(), 0);
									}
								}
							}, 3);

							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									avlnch.remove(p.getUniqueId());
								}
							}, 40);
							avlncht.put(p.getUniqueId(), task);

							ArrayList<Location> des = new ArrayList<>();
							AtomicInteger j = new AtomicInteger();
							for(int i=0; i<20; i++) {
								Random random=new Random();
								double number = random.nextDouble() * 1.5 * (random.nextBoolean() ? -1 : 1);
								double number2 = random.nextDouble() * 1.5 * (random.nextBoolean() ? -1 : 1);
								des.add(el.clone().add(number, 0.5, number2));
							}
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1, false, false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 1, false, false));
							p.getWorld().spawnParticle(Particle.CLOUD, el, 100, 2.5, 2.4, 2.4, 0.2);
							p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, el, 100, 2.4, 2.4, 2.4, 0.2);
							p.playSound(el.add(0,4,0), Sound.WEATHER_RAIN, 1f, 2f);
							Snowball firstarrow = p.launchProjectile(Snowball.class);
							firstarrow.setItem(new ItemStack(Material.FROSTED_ICE));
							firstarrow.remove();
							des.forEach(dl ->{
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										p.getWorld().spawnParticle(Particle.CLOUD, el, 10, 2.5, 2.4, 2.4, 0.2);
										Random random=new Random();
										int ri = random.nextInt(2);
										Material type = null;
										if(ri == 0) {
											type = Material.ICE;
										}
										else if(ri == 1) {
											type = Material.SNOW_BLOCK;
										}
										else if(ri == 2) {
											type = Material.PACKED_ICE;
										}
										else if(ri == 3) {
											type = Material.PRISMARINE_CRYSTALS;
										}
										Item hail = p.getWorld().dropItem(dl, new ItemStack(Material.ICE));
										hail.setItemStack(new ItemStack(type));
										hail.setPickupDelay(9999);
										hail.setOwner(p.getUniqueId());
										hail.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
										hail.setMetadata("hail of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										hail.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, el, 2, 2.4, 2.4, 2.4, 0.2);
										p.playSound(hail.getLocation(), Sound.BLOCK_SNOW_FALL, 0.8f, 2f);
										p.playSound(hail.getLocation(), Sound.BLOCK_SNOW_PLACE, 0.8f, 2f);
										p.playSound(hail.getLocation(), Sound.ENTITY_GENERIC_BIG_FALL, 0.8f, 2f);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run()
											{
												hail.remove();
											}
										}, 60);
										for(Entity e : l.getWorld().getNearbyEntities(l, 4.5, 4.5, 4.5)) {
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
											if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p&& !(e.hasMetadata("portal"))) {
												LivingEntity le = (LivingEntity)e;
												atk0(0.16, bsd.Hailstones.get(p.getUniqueId())*0.18, p, le);

											}
										}
									}
								}, j.incrementAndGet()*3+10);
							});
						});
				bd.execute();

			}
		}

	}


	public void Avalanche(PlayerSwapHandItemsEvent ev)
	{

		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 21 && avlnch.containsKey(p.getUniqueId())) {
			final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR)), 6).getLocation().setDirection(p.getLocation().getDirection());
			final Location el = new Location(p.getWorld(), tl.getX(), tl.getY()+1.5, tl.getZ());
			if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && p.isSneaking())
			{
				ev.setCancelled(true);

				if(avlncht.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(avlncht.get(p.getUniqueId()));
					avlncht.remove(p.getUniqueId());
				}
				avlnch.remove(p.getUniqueId());



				if(glcdrt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(glcdrt.get(p.getUniqueId()));
					glcdrt.remove(p.getUniqueId());
				}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						if(Proficiency.getpro(p)>=2) {
							glcdr.putIfAbsent(p.getUniqueId(), 0);
						}
					}
				}, 3);

				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						glcdr.remove(p.getUniqueId());
					}
				}, 40);
				glcdrt.put(p.getUniqueId(), task);

				HashSet<Location> des = new HashSet<>();
				for(double h=5; h>0; h-=0.5) {
					des.add(el.clone().add(0, h, 0));
				}
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1, false, false));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 1, false, false));
				p.getWorld().spawnParticle(Particle.CLOUD, el, 100, 2.5, 2.4, 2.4, 0.2);
				p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, el, 100, 2.4, 2.4, 2.4, 0.2);
				p.playSound(el.add(0,4,0), Sound.WEATHER_RAIN, 1f, 0f);
				AtomicInteger j = new AtomicInteger();
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						des.forEach(l-> {
							l.getWorld().spawnParticle(Particle.SNOWBALL, l, 400, 5, 1, 5, 0.2);
							l.getWorld().spawnParticle(Particle.SNOW_SHOVEL, l, 400, 5, 1, 5, 0.2);
						});
					}
				},j.incrementAndGet()*2);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						p.getWorld().spawnParticle(Particle.CLOUD, el, 100, 5, 0, 5, 0.2);
						p.getWorld().spawnParticle(Particle.SNOWFLAKE, el, 100, 5, 0, 5, 0.2);
						p.playSound(p.getLocation(), Sound.BLOCK_SNOW_FALL, 0.8f, 2f);
						p.playSound(p.getLocation(), Sound.BLOCK_SNOW_PLACE, 0.8f, 2f);
						p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_BIG_FALL, 0.8f, 2f);
						for(Entity e : tl.getWorld().getNearbyEntities(tl, 5, 5, 5)) {
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
							if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p&& !(e.hasMetadata("portal"))) {
								LivingEntity le = (LivingEntity)e;

								atk0(0.76, bsd.Hailstones.get(p.getUniqueId())*0.85, p, le);

								Holding.holding(p, le, 15l);

							}
						}
					}
				}, 20);
			}
		}

	}



	public void ExtremeCold(PlayerSwapHandItemsEvent ev)
	{

		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 21 && glcdr.containsKey(p.getUniqueId())) {
			if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && p.isSneaking())
			{
				ev.setCancelled(true);

				if(glcdrt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(glcdrt.get(p.getUniqueId()));
					glcdrt.remove(p.getUniqueId());
				}
				glcdr.remove(p.getUniqueId());

				final Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(5)).add(0, 5, 0);


				for(int d=0; d<6; d++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							p.playSound(tl, Sound.BLOCK_POWDER_SNOW_FALL, 1f, 0);
							p.playSound(tl, Sound.BLOCK_POWDER_SNOW_BREAK, 1f,2);
							p.playSound(tl, Sound.BLOCK_POWDER_SNOW_BREAK, 1f,0);
							p.playSound(tl, Sound.BLOCK_POWDER_SNOW_FALL, 1f, 1);
							p.playSound(tl, Sound.ENTITY_PLAYER_HURT_FREEZE, 1f, 0);
							p.playSound(tl, Sound.BLOCK_SNOW_FALL, 1f,0);
							p.getWorld().spawnParticle(Particle.SNOWFLAKE, tl, 100, 5, 1, 5, 0.2);
							for(Entity e : tl.getWorld().getNearbyEntities(tl, 6, 6, 6)) {
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
								if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p&& !(e.hasMetadata("portal"))) {
									LivingEntity le = (LivingEntity)e;

									p.getWorld().spawnParticle(Particle.SNEEZE, le.getEyeLocation(), 20, 1, 1, 1, 0.1);
									atk0(0.5, bsd.Hailstones.get(p.getUniqueId())*0.6, p, le);
									Holding.holding(p, le, 20l);

								}
							}
						}
					},d*5);
				}

			}
		}

	}

	final private void IceSpikes(Location el, World w, AtomicInteger j) {
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.incrementAndGet()/5, 0), 10, 1, 2, 1, Material.PACKED_ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.incrementAndGet()/5, 0), 10, 0.5, 2, 0.5, Material.ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.incrementAndGet()/5+0.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+1, 0), 10, 0.3, 2, 0.3, Material.PACKED_ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+1, 0), 10, 0.2, 2, 0.2, Material.ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+1.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+2, 0), 10, 0.3, 2, 0.3, Material.PACKED_ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+2, 0), 10, 0.2, 2, 0.2, Material.ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+2.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+3, 0), 10, 0.3, 2, 0.3, Material.PACKED_ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+3, 0), 10, 0.2, 2, 0.2, Material.ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+3.1, 0), 10, 0.1, 2, 0.1, Material.BLUE_ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+4, 0), 10, 0.2, 2, 0.2, Material.PACKED_ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+4, 0), 10, 0.1, 2, 0.1, Material.ICE.createBlockData());
		w.spawnParticle(Particle.BLOCK_CRACK, el.clone().add(0, j.get()/5+4.1, 0), 10, 0.05, 2, 0.05, Material.BLUE_ICE.createBlockData());
	}


	public void IceSpikes(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =8*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		if(ClassData.pc.get(p.getUniqueId()) == 21&& bsd.IceSpikes.getOrDefault(p.getUniqueId(), 0)>=1) {
			final Location el = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 6).getLocation().add(0, -4, 0);
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
				{
					p.setCooldown(CAREFUL, 2);

					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("거대고드름")
							.ename("IceSpikes")
							.slot(2)
							.hm(smcooldown)
							.skillUse(() -> {

								if(polarvt.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(polarvt.get(p.getUniqueId()));
									polarvt.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											polarv.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										polarv.remove(p.getUniqueId());
									}
								}, 40);
								polarvt.put(p.getUniqueId(), task);

								final World pw = p.getWorld();

								AtomicInteger j = new AtomicInteger();
								p.playSound(el.clone().add(0,4,0), Sound.BLOCK_SNOW_FALL, 0.8f, 2f);
								p.playSound(el.clone().add(0,4,0), Sound.AMBIENT_UNDERWATER_ENTER, 0.8f, 0f);
								pw.spawnParticle(Particle.WATER_WAKE, el.clone().add(0, 4, 0), 100, 2, 2, 2);
								pw.spawnParticle(Particle.WHITE_ASH, el.clone().add(0, 4, 0), 100, 2, 2, 2);
								pw.spawnParticle(Particle.SNOWBALL, el.clone().add(0, 4, 0), 100, 2, 2, 2);
								for(int d=0; d<25; d++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											IceSpikes(el, pw, j);
											p.playSound(el.clone().add(0,4,0), Sound.BLOCK_GLASS_BREAK, 0.35f, j.get()*0.08f);
											p.playSound(el.clone().add(0,4,0), Sound.BLOCK_LANTERN_BREAK, 0.35f, j.get()*0.08f);
											p.playSound(el.clone().add(0,4,0), Sound.ITEM_TRIDENT_THROW, 0.35f, j.get()*0.08f);
											for(Entity e : p.getWorld().getNearbyEntities(el.clone(), 2, 15, 2)) {
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
												if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p&& !(e.hasMetadata("portal"))) {
													final LivingEntity le = (LivingEntity)e;
													atk0(0.23, bsd.IceSpikes.get(p.getUniqueId())*0.25, p, le);

												}
											}
										}
									}, d/2+10);
								}
							});
					bd.execute();


				}

			}}
	}



	public void PolarVortex(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 21 && polarv.containsKey(p.getUniqueId())) {
			final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 6).getLocation();
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
				{

					if(polarvt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(polarvt.get(p.getUniqueId()));
						polarvt.remove(p.getUniqueId());
					}
					polarv.remove(p.getUniqueId());



					if(iccdpt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(iccdpt.get(p.getUniqueId()));
						iccdpt.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								iccdp.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 3);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							iccdp.remove(p.getUniqueId());
						}
					}, 40);
					iccdpt.put(p.getUniqueId(), task);


					p.playSound(tl, Sound.ENTITY_SNOW_GOLEM_AMBIENT, 1, 0);
					p.playSound(tl, Sound.AMBIENT_UNDERWATER_ENTER, 0.8f, 0f);

					final World tw = tl.getWorld();

					HashSet<Location> ring = new HashSet<Location>();
					for(double an = 0; an<Math.PI*2; an +=Math.PI/180) {
						ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an).multiply(an)));
					}

					ring.forEach(l -> {
						tw.spawnParticle(Particle.SNOWFLAKE, l, 5, 0.5,1,0.5,0.1);
						tw.spawnParticle(Particle.WHITE_ASH, l, 5, 0.5,1,0.5,0);
						tw.spawnParticle(Particle.SNOW_SHOVEL, l, 5, 0.5,1,0.5,0);
						tw.spawnParticle(Particle.NAUTILUS, l, 5, 0.5,1,0.5,0);

					});

					for(int d=0; d<5; d++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.playSound(tl, Sound.ENTITY_HORSE_BREATHE, 1, 0);
								p.playSound(tl, Sound.ENTITY_HORSE_BREATHE, 1, 2);
								p.playSound(tl, Sound.AMBIENT_UNDERWATER_ENTER, 0.3f, 0f);
								p.playSound(tl, Sound.BLOCK_SNOW_BREAK, 1f, 0f);
								p.playSound(tl, Sound.BLOCK_SNOW_BREAK, 1f, 2f);

								for(Entity e : tl.getWorld().getNearbyEntities(tl.clone(), 5, 5, 5)) {
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
									if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p&& !(e.hasMetadata("portal"))) {
										final LivingEntity le = (LivingEntity)e;
										atk0(0.34, bsd.IceSpikes.get(p.getUniqueId())*0.3, p, le);


										le.teleport(tl);
										Holding.holding(p, le, 6l);
									}
								}
							}
						}, d*6);
					}

				}
			}
		}
	}




	public void GlacialDrift(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 21 && iccdp.containsKey(p.getUniqueId())) {
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
				{

					if(iccdpt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(iccdpt.get(p.getUniqueId()));
						iccdpt.remove(p.getUniqueId());
					}
					iccdp.remove(p.getUniqueId());

					final Location tl = p.getEyeLocation().clone().add(p.getEyeLocation().clone().getDirection().normalize().multiply(5)).add(0, 5, 0);

					p.playSound(tl, Sound.ENTITY_SNOWBALL_THROW, 1f, 0);
					p.playSound(tl, Sound.BLOCK_POWDER_SNOW_BREAK, 1f,2);
					p.playSound(tl, Sound.BLOCK_POWDER_SNOW_BREAK, 1f,0);
					p.playSound(tl, Sound.BLOCK_POWDER_SNOW_FALL, 1f,0);
					p.playSound(tl, Sound.BLOCK_SNOW_FALL, 1f,0);


					for(int i = -1; i<1; i++) {
						for(int j = -1; j<1; j++) {
							for(int y = -1; y<4; y++) {
								Random random=new Random();
								int ri = random.nextInt(2);
								Material type = null;
								if(ri == 0) {
									type = Material.ICE;
								}
								else if(ri == 1) {
									type = Material.SNOW_BLOCK;
								}
								else if(ri == 2) {
									type = Material.PACKED_ICE;
								}

								FallingBlock fallingb = p.getWorld().spawnFallingBlock(tl.clone().add(i, y, j), type.createBlockData());
								fallingb.setInvulnerable(true);
								fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
								fallingb.setMetadata("glacial", new FixedMetadataValue(RMain.getInstance(),p.getName()));
								fallingb.setVisualFire(true);
								fallingb.setDropItem(true);
								fallingb.setHurtEntities(true);
							}

						}

					}

				}
			}
		}
	}


	public void GlacialDrift(EntityDropItemEvent ev)
	{
		if(ev.getEntity() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getEntity();
			if(fallingb.hasMetadata("glacial")){
				ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("glacial").get(0).asString());
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.SNOWFLAKE, tl, 20,5,5,5);
				tl.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100,5,5,5, Material.ICE.createBlockData());
				tl.getWorld().spawnParticle(Particle.SNOW_SHOVEL, tl, 100,5,5,5);


				for (Entity e : p.getWorld().getNearbyEntities(tl, 5, 5, 5))
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
						atk0(0.3, bsd.IceSpikes.get(p.getUniqueId())*0.45, p, le);
						le.teleport(tl);
					}

				}
				p.playSound(tl, Sound.BLOCK_SNOW_BREAK, 1, 0);
				p.playSound(tl, Sound.BLOCK_POWDER_SNOW_BREAK, 1, 0);
				p.playSound(tl, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
				fallingb.remove();
			}
		}
	}



	public void GlacialDrift(EntityDamageByEntityEvent ev)
	{
		if(ev.getDamager() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getDamager();
			if(fallingb.hasMetadata("glacial")){
				ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("glacial").get(0).asString());
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.SNOWFLAKE, tl, 20,5,5,5);
				tl.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100,5,5,5, Material.ICE.createBlockData());
				tl.getWorld().spawnParticle(Particle.SNOW_SHOVEL, tl, 100,5,5,5);


				for (Entity e : p.getWorld().getNearbyEntities(tl, 5, 5, 5))
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
						atk0(0.3, bsd.IceSpikes.get(p.getUniqueId())*0.45, p, le);
						le.teleport(tl);
					}

				}
				p.playSound(tl, Sound.BLOCK_SNOW_BREAK, 1, 0);
				p.playSound(tl, Sound.BLOCK_POWDER_SNOW_BREAK, 1, 0);
				p.playSound(tl, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
				fallingb.remove();
			}
		}
	}


	public void GlacialDrift(EntityChangeBlockEvent ev)
	{
		if(ev.getEntity() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getEntity();
			if(fallingb.hasMetadata("glacial")){
				ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("glacial").get(0).asString());
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.SNOWFLAKE, tl, 20,5,5,5);
				tl.getWorld().spawnParticle(Particle.BLOCK_CRACK, tl, 100,5,5,5, Material.ICE.createBlockData());
				tl.getWorld().spawnParticle(Particle.SNOW_SHOVEL, tl, 100,5,5,5);


				for (Entity e : p.getWorld().getNearbyEntities(tl, 5, 5, 5))
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
						atk0(0.3, bsd.IceSpikes.get(p.getUniqueId())*0.45, p, le);
						le.teleport(tl);
					}

				}
				p.playSound(tl, Sound.BLOCK_SNOW_BREAK, 1, 0);
				p.playSound(tl, Sound.BLOCK_POWDER_SNOW_BREAK, 1, 0);
				p.playSound(tl, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
				fallingb.remove();
			}
		}
	}


	@SuppressWarnings("deprecation")
	public void SnowBreeze(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		if(ClassData.pc.get(p.getUniqueId()) == 21&& bsd.SnowBreeze.getOrDefault(p.getUniqueId(), 0)>=1) {
			final Location l = p.getLocation().clone();
			if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD &&!p.isSneaking()&& !p.isOnGround() &&!p.hasCooldown(CAREFUL))
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("눈바람")
							.ename("SnowBreeze")
							.slot(3)
							.hm(gdcooldown)
							.skillUse(() -> {
								if(icefallt.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(icefallt.get(p.getUniqueId()));
									icefallt.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											icefall.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										icefall.remove(p.getUniqueId());
									}
								}, 35);
								icefallt.put(p.getUniqueId(), task);

								ArrayList<Location> br = new ArrayList<>();
								AtomicInteger j = new AtomicInteger();
								for(double d = -3; d<6 ; d+=0.1) {
									br.add(p.getLocation().clone().add(p.getLocation().getDirection().clone().normalize().multiply(d)));
								}
								br.forEach(bl -> {

									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											p.getWorld().spawnParticle(Particle.BLOCK_CRACK, bl,30, 3,0.1,3,0,Material.SNOW.createBlockData());
										}
									}, j.incrementAndGet()/30);
								});
								p.playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 1f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1f, 0f);
								for(Entity e : p.getNearbyEntities(4, 3, 4)) {
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
									if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p&& !(e.hasMetadata("portal"))) {
										LivingEntity le = (LivingEntity)e;
										p.setCooldown(Material.YELLOW_TERRACOTTA, 1);
										atk1(0.25, p, le);


										for(double d = 0; d<6-l.distance(le.getEyeLocation()); d+=0.1) {
											if(le.getEyeLocation().clone().add(p.getEyeLocation().getDirection().normalize().multiply(0.1)).getBlock().isPassable()){
												le.teleport(le.getLocation().add(p.getEyeLocation().getDirection().normalize().multiply(0.1)));
											}
											else {
												break;
											}
										}
									}
								}
							});
					bd.execute();

				}
			}

		}
	}


	@SuppressWarnings("deprecation")
	public void Icefall(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 21&&icefall.containsKey(p.getUniqueId())) {
			final Location hpl = p.getEyeLocation().clone().add(0,3,0);
			final Location tl = gettargetblock(p,6);
			if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD &&!p.isSneaking()&& !p.isOnGround() &&!p.hasCooldown(CAREFUL))
			{
				if((ac == Action.LEFT_CLICK_AIR || ac == Action.LEFT_CLICK_BLOCK))
				{
					ev.setCancelled(true);

					if(icefallt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(icefallt.get(p.getUniqueId()));
						icefallt.remove(p.getUniqueId());
					}
					icefall.remove(p.getUniqueId());


					ArrayList<Location> br = new ArrayList<>();
					AtomicInteger j = new AtomicInteger();
					Holding.invur(p, 20l);
					for(double d = 0; d<7 ; d+=0.3) {
						br.add(hpl.clone().add(tl.toVector().subtract(hpl.toVector()).normalize().multiply(d)));
					}
					br.forEach(bl -> {

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, bl,30, 4,0.1,3,0,Material.ICE.createBlockData());
								p.playSound(p.getLocation(), Sound.BLOCK_POWDER_SNOW_FALL, 0.2f, 2f);
								if(bl.getBlock().isPassable()) {
									p.teleport(bl);
								}
							}
						}, j.incrementAndGet()/2);
					});
					p.playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 1f, 0f);
					p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1f, 0f);
					p.playSound(p.getLocation(), Sound.BLOCK_POWDER_SNOW_FALL, 1f, 0f);
					p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1f, 2f);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 50,0,false,false));

				}
			}

		}
	}

	final private void icicleshot(Player p, World w) {

		p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
		p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 0.5f, 2f);
		p.playSound(p.getLocation(), Sound.ITEM_TRIDENT_HIT_GROUND, 1f, 2f);
		ArrayList<Location> line = new ArrayList<Location>();
		HashSet<LivingEntity> les = new HashSet<LivingEntity>();
		for(double pi= -Math.PI/3; pi<=Math.PI/4.5; pi += Math.PI/6) {
			for(double d = 0.1; d <= 9.1; d += 0.2) {
				Location pl = p.getEyeLocation().clone();
				pl.add(p.getEyeLocation().getDirection().normalize().rotateAroundY(pi).multiply(d));
				line.add(pl);
			}
		}
		line.forEach(l -> {
			w.spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.05,0.05,0.05,0,Material.ICE.createBlockData());
			w.spawnParticle(Particle.BLOCK_CRACK, l.add(0, -0.289, 0),5, 0.01,0.01,0.01,0,Material.PACKED_ICE.createBlockData());
			w.spawnParticle(Particle.SNOW_SHOVEL, l.add(0, -0.289, 0),1, 0.05,0.05,0.05,0.5);

			for (Entity a : l.getWorld().getNearbyEntities(l, 1.5, 1.5, 1.5))
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
		les.forEach(le -> {
			atk0(0.32,bsd.IcicleShot.get(p.getUniqueId())*0.31, p, le);
		});
	}

	public void IcicleShot(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =2*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

		if(ClassData.pc.get(p.getUniqueId()) == 21&& bsd.IcicleShot.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
				{

					p.setCooldown(CAREFUL, 2);
					ev.setCancelled(true);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("고드름화살")
							.ename("IcicleShot")
							.slot(4)
							.hm(frcooldown)
							.skillUse(() -> {
								if(snballt.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(snballt.get(p.getUniqueId()));
									snballt.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											snball.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										snball.remove(p.getUniqueId());
									}
								}, 40);
								snballt.put(p.getUniqueId(), task);

								p.playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 1f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_SNOW_STEP, 1f, 2f);
								p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_QUICK_CHARGE_3, 1f, 1f);

								final World w = p.getWorld();

								for(int i =0; i<3; i++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											icicleshot(p,w);
										}
									}, i+20);
								}
							});
					bd.execute();


				}

			}}
	}


	public void SnowBall(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 21 && snball.containsKey(p.getUniqueId())) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
				{
					ev.setCancelled(true);

					if(snballt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(snballt.get(p.getUniqueId()));
						snballt.remove(p.getUniqueId());
					}
					snball.remove(p.getUniqueId());



					if(iceblt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(iceblt.get(p.getUniqueId()));
						iceblt.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								icebl.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 3);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							icebl.remove(p.getUniqueId());
						}
					}, 40);
					iceblt.put(p.getUniqueId(), task);

					p.playSound(p.getLocation(), Sound.BLOCK_SNOW_HIT, 1f, 2f);
					p.playSound(p.getLocation(), Sound.BLOCK_POWDER_SNOW_HIT, 1f, 2f);
					p.playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1f, 2f);
					for(int i =0; i<4; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.playSound(p.getLocation(), Sound.BLOCK_SNOW_HIT, 1f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_POWDER_SNOW_HIT, 1f, 2f);
								Snowball sn = p.launchProjectile(Snowball.class);
								sn.setVelocity(sn.getVelocity().clone().normalize().multiply(2.5));
								sn.setBounce(false);
								sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
								sn.setMetadata("frostsnowball", new FixedMetadataValue(RMain.getInstance(), true));
								sn.setShooter(p);
								sn.setGlowing(true);
								sn.setGravity(true);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										if(sn.isValid()) {
											sn.remove();
											Bukkit.getPluginManager().callEvent(new ProjectileHitEvent(sn, sn.getLocation().getBlock()));
										}
									}
								}, 40);
							}
						}, i*2);
					}
				}
			}
		}
	}


	public void SnowBall(ProjectileHitEvent d)
	{
		if(d.getEntity().hasMetadata("frostsnowball")) {
			Player p = (Player) d.getEntity().getShooter();
			d.getEntity().getWorld().spawnParticle(Particle.SNOW_SHOVEL, d.getEntity().getLocation(), 50, 2,2,2);
			d.getEntity().getWorld().spawnParticle(Particle.SNOWBALL, d.getEntity().getLocation(), 50, 2,2,2);
			d.getEntity().getWorld().spawnParticle(Particle.SNOWFLAKE, d.getEntity().getLocation(), 50, 2,2,2);
			for (Entity e : d.getEntity().getLocation().getWorld().getNearbyEntities(d.getEntity().getLocation(), 2, 2, 2))
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
						atk0(0.54,bsd.IcicleShot.get(p.getUniqueId())*0.651, p, le);

					}
				}
			}
		}
	}

	final private void blade(Player p, Location tl, Integer j, World w) {

		HashSet<Location> line = new HashSet<Location>();
		HashSet<Location> fill = new HashSet<Location>();
		for(double an = Math.PI/2.5; an>-Math.PI/2.5; an-=Math.PI/180) {
			Location pl = tl.clone().add(tl.clone().getDirection().normalize().multiply(3.6));
			pl.add(pl.getDirection().clone().normalize().multiply(3).rotateAroundY(an+j*1.68));
			line.add(pl);
		}
		line.forEach(l -> {
			w.spawnParticle(Particle.BLOCK_CRACK, l,5,0.1,0.1,0.1,0, Material.PACKED_ICE.createBlockData());
			w.spawnParticle(Particle.SWEEP_ATTACK, l,1,0.1,0.1,0.1,0);
			w.spawnParticle(Particle.BLOCK_CRACK, l,5,0.1,0.1,0.1,0, Material.BLUE_ICE.createBlockData());
			for(double i = 0; i<6.5;i+=0.1) {
				Location pl = p.getEyeLocation().clone();
				Vector v = l.clone().toVector().subtract(pl.toVector()).normalize();
				fill.add(pl.add(v.multiply(i)));
			}

		});
		for (Entity e : tl.getWorld().getNearbyEntities(tl, 3.5, 3.5, 3.5))
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
				atk0(0.42,bsd.IcicleShot.get(p.getUniqueId())*0.551, p, le);
			}
		}
	}


	public void IceBlades(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 21) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&& icebl.containsKey(p.getUniqueId()))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
				{
					ev.setCancelled(true);

					if(iceblt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(iceblt.get(p.getUniqueId()));
						iceblt.remove(p.getUniqueId());
					}
					icebl.remove(p.getUniqueId());

					final World w = p.getWorld();

					p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1f, 2f);
					p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, 1f, 2f);
					p.playSound(p.getLocation(), Sound.BLOCK_SNOW_STEP, 1f, 2f);
					AtomicInteger j = new AtomicInteger();
					for(int i =0; i<5; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 0.5f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 0.5f, 2f);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 2f);
								blade(p,p.getEyeLocation().clone(),j.getAndIncrement(),w);

							}
						}, i*2+5);
					}
				}
			}
		}
	}




	public void Crack(EntityDamageByEntityEvent d)
	{

		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity&&!d.isCancelled() && d.getDamage()>0)
		{
			Player p = (Player)d.getDamager();
			double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			final LivingEntity le = (LivingEntity)d.getEntity();


			Holding.getInstance();
			if(ClassData.pc.get(p.getUniqueId()) == 21&& bsd.Crack.getOrDefault(p.getUniqueId(), 0)>=1 &&p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD && frostcooldown.containsKey(le.getUniqueId()))
			{

				if(p.isSneaking() && !(p.hasCooldown(Material.YELLOW_TERRACOTTA)))
				{
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("균열")
							.ename("Crack")
							.slot(5)
							.hm(swcooldown)
							.skillUse(() -> {
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
								le.setVelocity(p.getLocation().getDirection());
								d.setDamage(d.getDamage()*2.5 + bsd.Crack.get(p.getUniqueId())*2.6);
								p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);
								p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0f, 0f);
								p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 2.0f);
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 5, 1, 0, 1, Material.ICE.createBlockData());
								Holding.holding(p, le, (long) 20);
								if(Proficiency.getpro(p)>=1) {

									ArrayList<Location> line = new ArrayList<Location>();

									for(double an = Math.PI/6; an>-Math.PI/6; an-=Math.PI/90) {
										Location pl = p.getEyeLocation();
										pl.add(pl.getDirection().rotateAroundY(an).normalize().multiply(4.5));
										line.add(pl);
									}
									line.forEach(l -> {
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 5, 1, 0, 1, Material.INFESTED_CRACKED_STONE_BRICKS.createBlockData());
										p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 5, 1, 0, 1, Material.ICE.createBlockData());

									});

									for (Entity e : p.getWorld().getNearbyEntities(le.getLocation(), 3, 3, 3))
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
											LivingEntity lle = (LivingEntity)e;
											atk0(2.5, bsd.Crack.get(p.getUniqueId())*2.6, p, lle);
										}
									}
								}
								if(Proficiency.getpro(p)>2) {
									frostcooldown.remove(le.getUniqueId());
								}
							});
					bd.execute();

				}
			}
		}
	}


	final private void frostInd(LivingEntity le, Player p){
		if(ClassData.pc.get(p.getUniqueId()) == 21){
			int frc = frost.get(le.getUniqueId());
			for(int i=0;i<frc;i++){

				ArrayList<Location> draw = new ArrayList<Location>();
				Location pl = le.getLocation().clone().add(0, 0.5, 0);
				Vector pv = pl.clone().add(1, 0, 0).toVector().subtract(pl.clone().toVector());

				for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
					draw.add(pl.clone().add(pv.rotateAroundY(an).normalize().multiply(i*1.6)));
				}
				draw.forEach(l -> {
					p.spawnParticle(Particle.BLOCK_CRACK, l.clone().add(0, 0.2, 0),5,0.1,0.1,0.1,Material.BLUE_ICE.createBlockData());

				});
			}
		}
	}

	final private void frostCool(LivingEntity le, Player p, Double cool){
		if(ClassData.pc.get(p.getUniqueId()) == 21){
			ArrayList<Location> draw = new ArrayList<Location>();
			AtomicInteger j = new AtomicInteger();
			Location pl = le.getLocation().clone().add(0, 1, 0);
			Vector pv = pl.clone().add(1, 0, 0).toVector().subtract(pl.clone().toVector());

			for(int an = 0; an<cool*20; an++) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						for(double an = 0; an<Math.PI*2; an+=Math.PI/90) {
							draw.add(pl.clone().add(pv.rotateAroundY(an).normalize().multiply(1.1)));
						}
						Location particlel = pl.clone().add(pv.rotateAroundY((Math.PI*2/(cool*20))*j.getAndIncrement()).normalize().multiply(1.1));
						draw.forEach(l -> {
							p.spawnParticle(Particle.BLOCK_CRACK, l.clone().add(0, 0.2, 0),1,Material.SNOW_BLOCK.createBlockData());

						});
						p.spawnParticle(Particle.BLOCK_CRACK, particlel.clone().add(0, 0.2, 0),8,0.12,0.12,0.12,Material.BLUE_ICE.createBlockData());
					}
				}, an);
			}

		}
	}
	public void Frostbite(EntityDamageByEntityEvent d)
	{

		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity)
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if(ClassData.pc.get(p.getUniqueId()) == 21 && (d.getDamage() > 0)&& !(le == p)&&!(le.hasMetadata("fake"))&& !(le.hasMetadata("portal"))) {
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

				if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
				{
					if(45+le.getFreezeTicks()>le.getMaxFreezeTicks()) {
						le.setFreezeTicks(le.getMaxFreezeTicks());
					}
					else {
						le.setFreezeTicks(45+le.getFreezeTicks());
					}
					double sec = 5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*(ArmorSet.setnum(p) == 6? 0.5:1)*(frosted.containsKey(le.getUniqueId())? 0:1);

					d.setDamage(d.getDamage()*1.15*(1+bsd.Frostbite.get(p.getUniqueId())*0.045));
					if(frostcooldown.containsKey(le.getUniqueId())) // if cooldown has players name in it (on first trow cooldown is empty)
					{
						double timer = (frostcooldown.get(le.getUniqueId())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
						if(!(timer < 0)) // if timer is still more then 0 or 0
						{
						}
						else // if timer is done
						{
							frostcooldown.remove(le.getUniqueId()); // removing player from HashMap
							frost.computeIfPresent(le.getUniqueId(), (k,v) -> v+1);
							frost.putIfAbsent(le.getUniqueId(), 0);
							p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
							frostInd(le,p);
							if(frost.get(le.getUniqueId())>=3) {
								p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1f, 2f);
								Holding.holding(p, le, (long) (45l+Proficiency.getpro(p)*20l));
								le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
								le.getWorld().spawnParticle(Particle.SNOWFLAKE, le.getLocation(), 10, 1,1,1);
								frost.remove(le.getUniqueId());
								frostcooldown.put(le.getUniqueId(), System.currentTimeMillis());
								frostCool(le,p,sec);
							}

						}
					}
					else // if cooldown doesn't have players name in it
					{
						frost.computeIfPresent(le.getUniqueId(), (k,v) -> v+1);
						frost.putIfAbsent(le.getUniqueId(), 0);
						p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
						frostInd(le,p);
						if(frost.get(le.getUniqueId())>=3) {
							p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1f, 2f);
							Holding.holding(p, le, (long) (45l+Proficiency.getpro(p)*20l));
							le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
							le.getWorld().spawnParticle(Particle.SNOWFLAKE, le.getLocation(), 10, 1,1,1);
							frost.remove(le.getUniqueId());
							frostcooldown.put(le.getUniqueId(), System.currentTimeMillis());
							frostCool(le,p,sec);
						}
					}
				}
			}
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity)
		{
			Projectile ar = (Projectile)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if(ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();


				if(ClassData.pc.get(p.getUniqueId()) == 21 && (d.getDamage() > 0)&& !(le == p)&&!(le.hasMetadata("fake"))&& !(le.hasMetadata("portal"))) {
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
					if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
					{
						if(45+le.getFreezeTicks()>le.getMaxFreezeTicks()) {
							le.setFreezeTicks(le.getMaxFreezeTicks());
						}
						else {
							le.setFreezeTicks(45+le.getFreezeTicks());
						}
						double sec = 5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*(ArmorSet.setnum(p) == 6? 0.5:1)*(frosted.containsKey(le.getUniqueId())? 0:1);

						d.setDamage(d.getDamage()*1.15*(1+bsd.Frostbite.get(p.getUniqueId())*0.045));
						if(frostcooldown.containsKey(le.getUniqueId())) // if cooldown has players name in it (on first trow cooldown is empty)
						{
							double timer = (frostcooldown.get(le.getUniqueId())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
							if(!(timer < 0)) // if timer is still more then 0 or 0
							{
							}
							else // if timer is done
							{
								frostcooldown.remove(le.getUniqueId()); // removing player from HashMap
								frost.computeIfPresent(le.getUniqueId(), (k,v) -> v+1);
								frost.putIfAbsent(le.getUniqueId(), 0);
								p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
								frostInd(le,p);
								if(frost.get(le.getUniqueId())>=3) {
									p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1f, 2f);
									Holding.holding(p, le, (long) (45l+Proficiency.getpro(p)*20l));
									le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
									le.getWorld().spawnParticle(Particle.SNOWFLAKE, le.getLocation(), 10, 1,1,1);
									frost.remove(le.getUniqueId());
									frostcooldown.put(le.getUniqueId(), System.currentTimeMillis());
									frostCool(le,p,sec);
								}

							}
						}
						else // if cooldown doesn't have players name in it
						{
							frost.computeIfPresent(le.getUniqueId(), (k,v) -> v+1);
							frost.putIfAbsent(le.getUniqueId(), 0);
							p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1f, 2f);
							frostInd(le,p);
							if(frost.get(le.getUniqueId())>=3) {
								p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1f, 2f);
								Holding.holding(p, le, (long) (45l+Proficiency.getpro(p)*20l));
								le.getWorld().spawnParticle(Particle.BLOCK_CRACK, le.getLocation(), 10, 1,1,1,Material.ICE.createBlockData());
								le.getWorld().spawnParticle(Particle.SNOWFLAKE, le.getLocation(), 10, 1,1,1);
								frost.remove(le.getUniqueId());
								frostcooldown.put(le.getUniqueId(), System.currentTimeMillis());
								frostCool(le,p,sec);
							}
						}
					}
				}
			}
		}
	}



	public void CoolBody(EntityDamageEvent d)
	{

		if(d.getEntity() instanceof Player)
		{
			Player p = (Player)d.getEntity();


			if((ArmorSet.setnum(p) == 6|| ClassData.pc.get(p.getUniqueId()) == 21) && d.getCause() == DamageCause.FREEZE)
			{
				d.setCancelled(true);
				p.setFreezeTicks(0);
			}
		}
	}


	public void Frostbite(EntityPotionEffectEvent d)
	{
		if(!(d.getEntity() instanceof Player)) {return;
		}

		else if(d.getEntity() instanceof Player) {
			if(d.getNewEffect() != null) {
				Player p = (Player)d.getEntity();
				if((ArmorSet.setnum(p) == 6|| ClassData.pc.get(p.getUniqueId()) == 21))
				{
					if(d.getNewEffect().getType() == PotionEffectType.SLOW) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.removePotionEffect(PotionEffectType.SLOW);
							}
						}, 10);
					}
					if(d.getModifiedType() == PotionEffectType.SLOW) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.removePotionEffect(PotionEffectType.SLOW);
							}
						}, 10);
					}
					if(p.hasPotionEffect(PotionEffectType.SLOW)) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.removePotionEffect(PotionEffectType.SLOW);
							}
						}, 10);
					}
					if(d.getNewEffect().getType() == PotionEffectType.SLOW_DIGGING) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
							}
						}, 10);
					}
					if(d.getModifiedType() == PotionEffectType.SLOW_DIGGING) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
							}
						}, 10);
					}
					if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
							}
						}, 10);
					}
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
		ItemStack is = p.getInventory().getItemInMainHand();
		if(ClassData.pc.get(p.getUniqueId()) == 21 && (is.getType() == Material.PRISMARINE_SHARD && ev.getNewSlot()==4 && p.isSneaking() && Proficiency.getpro(p) >=1))
		{
			p.setCooldown(CAREFUL, 2);
			final Location el = gettargetblock(p,6);
			ev.setCancelled(true);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown( 50/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("서리폭풍")
					.ename("Blizzard")
					.slot(6)
					.hm(sultcooldown)
					.skillUse(() -> {
						ArrayList<Location> desl = new ArrayList<>();
						AtomicInteger j = new AtomicInteger();
						for(int i=0; i<60; i++) {
							Random random=new Random();
							double number = random.nextDouble() * 6.5 * (random.nextBoolean() ? -1 : 1);
							double number2 = random.nextDouble() * 6.5 * (random.nextBoolean() ? -1 : 1);
							double number3 = random.nextDouble() * 6.5;
							desl.add(el.clone().add(number, number3, number2));
						}
						Snowball firstarrow = p.launchProjectile(Snowball.class);
						firstarrow.remove();
						Snowman as = (Snowman)p.getWorld().spawnEntity(el, EntityType.SNOWMAN);
						ItemStack right = new ItemStack(Material.ICE);
						ItemStack left = new ItemStack(Material.BLUE_ICE);
						as.setCustomName(p.getName());
						as.setCollidable(false);
						as.setInvulnerable(true);
						as.getEquipment().setItemInMainHand(left);
						as.getEquipment().setItemInOffHand(right);
						as.setCanPickupItems(false);
						as.setAI(false);
						as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						p.playSound(p.getLocation(), Sound.WEATHER_RAIN, 1, 0);
						Holding.invur(p, 100l);
						if(p.isDead()) {
							as.remove();
						}
						desl.forEach(dl ->{
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									Random random=new Random();
									int ri = random.nextInt(2);
									Material type = null;
									if(ri == 0) {
										type = Material.SNOW_BLOCK;
									}
									else if(ri == 1) {
										type = Material.SNOWBALL;
									}
									else if(ri == 2) {
										type = Material.BLUE_ICE;
									}
									Item des = p.getWorld().dropItem(dl, new ItemStack(Material.SNOWBALL));
									des.setItemStack(new ItemStack(type));
									des.setPickupDelay(9999);
									des.setOwner(p.getUniqueId());
									des.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
									des.setMetadata("des of "+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, el, 100, 6.4, 6.4, 6.4, 0.2);
									p.getWorld().spawnParticle(Particle.WATER_SPLASH, el, 100, 6.4, 6.4, 6.4, 0.2);
									p.playSound(des.getLocation(), Sound.ENTITY_SNOW_GOLEM_SHEAR, 0.8f, 2f);
									p.playSound(des.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 0.8f, 2f);
									p.playSound(des.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.8f, 0f);
									p.playSound(des.getLocation(), Sound.ENTITY_PLAYER_BREATH, 0.8f, 2f);
									for(int i = 0; i<20; i++) {
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run()
											{
												desl.get(random.nextInt(60));
											}
										}, i*2);
									}
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											des.remove();
										}
									}, 120);
									for(Entity e : as.getWorld().getNearbyEntities(as.getLocation(), 6.4, 6.4, 6.4)) {
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
										if(e instanceof LivingEntity&& !(e.hasMetadata("fake")) && e!=p&& !(e.hasMetadata("portal"))) {
											LivingEntity le = (LivingEntity)e;
											p.setCooldown(Material.YELLOW_TERRACOTTA, 1);

											atk0(0.32, 0d, p, le);

											le.teleport(le.getLocation().add(as.getLocation().toVector().subtract(le.getLocation().toVector()).normalize().multiply(0.92)));

										}
									}
								}
							}, j.incrementAndGet()*2);
						});
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								as.remove();
							}
						}, 126);
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
		if(ClassData.pc.get(p.getUniqueId()) == 21 && (is.getType() == Material.PRISMARINE_SHARD && ev.getNewSlot()==5 && p.isSneaking() && Proficiency.getpro(p) >=2))
		{
			p.setCooldown(CAREFUL, 2);
			ev.setCancelled(true);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(55*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("빙하시대")
					.ename("Ice Age")
					.slot(7)
					.hm(sult2cooldown)
					.skillUse(() -> {
						HashSet<Location> spl = new HashSet<>();

						final Location tl = gettargetblock(p,5).clone().add(0,5,0);
						final World pw = tl.getWorld();
						for(int ix = -3; ix<3; ix++) {
							for(int iy = -3; iy<3; iy++) {
								for(int iz = -3; iz<3; iz++) {
									if((ix*ix + iy*iy + iz*iz<=9)){
										spl.add(tl.clone().add(ix, iy, iz));
									}
								}
							}
						}
						spl.forEach(l -> {
							FallingBlock fallingb = pw.spawnFallingBlock(l,  Material.FROSTED_ICE.createBlockData());
							fallingb.setInvulnerable(true);
							fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
							fallingb.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							fallingb.setMetadata("wrose", new FixedMetadataValue(RMain.getInstance(),p.getName()));
							fallingb.setDropItem(false);
							fallingb.setHurtEntities(false);
							fallingb.setGravity(false);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									fallingb.remove();
								}
							}, 20);
						});
						Holding.invur(p, 120l);
						p.playSound(p.getLocation(), Sound.WEATHER_RAIN, 1, 0);
						p.playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_SHEAR, 0.8f, 2f);
						p.playSound(p.getLocation(), Sound.ENTITY_SNOW_GOLEM_AMBIENT, 0.8f, 2f);
						p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.8f, 0f);
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 0.8f, 2f);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run() {
								pw.spawnParticle(Particle.BLOCK_CRACK, tl,1000,7,7,7,1, Material.PACKED_ICE.createBlockData());
								pw.spawnParticle(Particle.BLOCK_CRACK, tl,1000,7,7,7,1, Material.BLUE_ICE.createBlockData());
								pw.spawnParticle(Particle.SNOWFLAKE, tl,500,1,1,1,5);
								pw.spawnParticle(Particle.WHITE_ASH, tl,500,7,7,7,1);
								pw.spawnParticle(Particle.SNOW_SHOVEL, tl,500,1,1,1,5);
								pw.spawnParticle(Particle.SNOWBALL, tl,500,1,1,1,5);

								for(int i = 0; i<10; i++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											p.playSound(tl, Sound.BLOCK_SNOW_PLACE, 0.8f, 0.2f);
											pw.spawnParticle(Particle.BLOCK_MARKER, tl.clone().add(0,3,0),500,7,2,7,1, Material.PACKED_ICE.createBlockData());
											pw.spawnParticle(Particle.BLOCK_MARKER, tl.clone().add(0,3,0),500,7,2,7,1, Material.BLUE_ICE.createBlockData());
											pw.spawnParticle(Particle.BLOCK_MARKER, tl.clone().add(0,3,0),500,7,2,7,1, Material.SNOW_BLOCK.createBlockData());
											pw.spawnParticle(Particle.BLOCK_MARKER, tl.clone().add(0,3,0),500,7,2,7,1, Material.ICE.createBlockData());

										}
									}, i*5);
								}

								p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 0.8f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 0.8f, 1f);
								p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 0.8f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.8f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 0.8f, 0f);
								p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 0.8f, 0f);

								for(Entity e : tl.getWorld().getNearbyEntities(tl,7, 7, 7)) {
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
									if(p!=e &&e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal"))) {
										LivingEntity le = (LivingEntity)e;

										atk0(28.5, 0d, p, le);

										Holding.holding(p, le, 200l);
										frosted.put(p.getUniqueId(),le.getUniqueId());
									}
								}
							}
						}, 20);
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

		if(ClassData.pc.get(p.getUniqueId()) == 21  && ((is.getType()==Material.PRISMARINE_SHARD)) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
		{
			ev.setCancelled(true);
		}

	}


	public void Equipment(PlayerItemDamageEvent e)
	{

		Player p = e.getPlayer();



		if(ClassData.pc.get(p.getUniqueId()) == 21)
		{
			if(p.getInventory().getItemInMainHand().getType() == Material.PRISMARINE_SHARD)
			{
				e.setCancelled(true);
			}
		}

	}




}