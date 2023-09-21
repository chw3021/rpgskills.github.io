package io.github.chw3021.classes.engineer;



import com.google.common.collect.ArrayListMultimap;
import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;

import org.bukkit.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import com.google.common.util.concurrent.AtomicDouble;

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
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Vibration;
import org.bukkit.Vibration.Destination;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Engskills extends Pak implements Listener, Serializable {

	/**
	 *
	 */
	private static transient final long serialVersionUID = 6779511048929362121L;
	private HashMap<String, Long> sdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> cdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> frcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> smcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> stcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gdcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> sult2cooldown = new HashMap<String, Long>();
	//private HashMap<String, Double> player_damage = new HashMap<String, Double>();
	private HashMap<LivingEntity, Integer> xray = new HashMap<LivingEntity, Integer>();
	private HashMap<LivingEntity, Player> xrayp = new HashMap<LivingEntity, Player>();


	private HashMap<UUID, Integer> obv = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> obvt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fcry = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> fcryt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> emp = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> empt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> grsh = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> grsht = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> engb = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> engbt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> orb = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> orbt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> thcr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> thcrt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> angv = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> angvt = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> propl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> proplt = new HashMap<UUID, Integer>();

	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private EngSkillsData esd;



	private static final Engskills instance = new Engskills ();
	public static Engskills getInstance()
	{
		return instance;
	}



	public void er(PluginEnableEvent ev)
	{
		EngSkillsData e = new EngSkillsData(EngSkillsData.loadData(path +"/plugins/RPGskills/EngSkillsData.data"));
		esd = e;
	}


	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Engskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				EngSkillsData ae = new EngSkillsData(EngSkillsData.loadData(path +"/plugins/RPGskills/EngSkillsData.data"));
				esd = ae;
			}

		}
	}


	public void nepreventer(PlayerJoinEvent ev)
	{
		EngSkillsData e = new EngSkillsData(EngSkillsData.loadData(path +"/plugins/RPGskills/EngSkillsData.data"));
		esd = e;

	}


	@SuppressWarnings("deprecation")
	public void Dispenser(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();



		if(ClassData.pc.get(p.getUniqueId()) == 17)
		{
			double sec =9*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking())
			{
				ev.setCancelled(true);

				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("발사기")
						.ename("Dispenser")
						.slot(0)
						.hm(sdcooldown)
						.skillUse(() -> {
							if(Proficiency.getpro(p)>=1) {
								sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
							}



							if(obvt.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(obvt.get(p.getUniqueId()));
								obvt.remove(p.getUniqueId());
							}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									if(Proficiency.getpro(p)>=1) {
										obv.putIfAbsent(p.getUniqueId(), 0);
									}
								}
							}, 3);

							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									obv.remove(p.getUniqueId());
								}
							}, 25);
							obvt.put(p.getUniqueId(), task);

							Location asl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().setDirection(p.getLocation().getDirection());
							ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(asl, EntityType.ARMOR_STAND);
							ItemStack head = new ItemStack(Material.DISPENSER);
							ItemStack ch = new ItemStack(Material.ELYTRA);
							ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
							ItemStack boot = new ItemStack(Material.IRON_BOOTS);
							ItemStack right = new ItemStack(Material.SNOWBALL);
							ItemStack left = new ItemStack(Material.BOW);
							as.setCustomName(p.getName());
							as.setBasePlate(false);
							if(!shipt.containsKey(p.getUniqueId())){
								as.setSmall(true);
							}
							as.setMarker(true);
							as.setInvulnerable(true);
							as.setInvisible(true);
							as.setArms(true);
							as.setHelmet(head);
							as.setChestplate(ch);
							as.setLeggings(leg);
							as.setBoots(boot);
							as.getEquipment().setItemInMainHand(left);
							as.getEquipment().setItemInOffHand(right);
							as.setCanPickupItems(false);
							as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							if(p.isDead()) {
								as.remove();
							}
							for(int i = 0; i<20; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										p.playSound(as.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 0.4f, 0);
										Double area = 5.5;
										if(shipt.containsKey(p.getUniqueId())){
											area = 11.5;
										}
										for(Entity e : as.getWorld().getNearbyEntities(as.getLocation(), area, area, area)) {
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
											if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
												LivingEntity le = (LivingEntity)e;
												if(shipt.containsKey(p.getUniqueId())){
													Factory(as,le,p);
												}
												else{
													Snowball sn = (Snowball) as.getWorld().spawnEntity(as.getEyeLocation(), EntityType.SNOWBALL);
													sn.setVelocity(le.getEyeLocation().clone().toVector().subtract(as.getEyeLocation().clone().toVector()).normalize().multiply(1.65));
													sn.setShooter(p);
													sn.setBounce(false);
													sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
													sn.setItem(new ItemStack(Material.RAW_IRON));
												}
												atk0(0.06, esd.Dispenser.get(p.getUniqueId())*0.06, p, le);
											}
										}
									}
								}, i*5);
							}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									as.remove();
								}
							}, 101);
						});
				bd.execute();

			}
		}
	}

	final private void observer(Location fl, Integer rot){

		HashSet<Location> pie = new HashSet<Location>();

		for(double d = -Math.PI/10; d<= Math.PI/10; d += Math.PI/60) {
			Location l = fl.clone();
			l.setDirection(l.getDirection().normalize().rotateAroundY(d*rot));
			for(double dis =0.1;dis<=6;dis+=0.21){
				l.add(l.getDirection().normalize().multiply(dis));
				pie.add(l);
			}

		}
	}


	@SuppressWarnings("deprecation")
	public void Observer(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();



		if(ClassData.pc.get(p.getUniqueId()) == 17)
		{
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking() &&obv.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

				if(Proficiency.getpro(p)>=1) {
					sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
				}

				if(obvt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(obvt.get(p.getUniqueId()));
					obvt.remove(p.getUniqueId());
				}
				obv.remove(p.getUniqueId());



				if(fcryt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(fcryt.get(p.getUniqueId()));
					fcryt.remove(p.getUniqueId());
				}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						if(Proficiency.getpro(p)>=2) {
							fcry.putIfAbsent(p.getUniqueId(), 0);
						}
					}
				}, 3);

				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						fcry.remove(p.getUniqueId());
					}
				}, 25);
				fcryt.put(p.getUniqueId(), task);


				Location asl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation().setDirection(p.getLocation().getDirection());
				ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(asl, EntityType.ARMOR_STAND);
				ItemStack head = new ItemStack(Material.OBSERVER);
				ItemStack ch = new ItemStack(Material.ELYTRA);
				ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
				ItemStack boot = new ItemStack(Material.IRON_BOOTS);
				ItemStack right = new ItemStack(Material.REDSTONE_TORCH);
				ItemStack left = new ItemStack(Material.REDSTONE_TORCH);
				as.setCustomName(p.getName());
				as.setBasePlate(false);
				as.setSmall(true);
				as.setMarker(true);
				as.setInvulnerable(true);
				as.setInvisible(true);
				as.setArms(true);
				as.setHelmet(head);
				as.setChestplate(ch);
				as.setLeggings(leg);
				as.setBoots(boot);
				as.getEquipment().setItemInMainHand(left);
				as.getEquipment().setItemInOffHand(right);
				as.setCanPickupItems(false);
				as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				if(p.isDead()) {
					as.remove();
				}
				for(int i = 0; i<5; i++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{

							AtomicInteger j = new AtomicInteger();
							for(int i = 0; i<10; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										observer(as.getLocation().clone(),j.incrementAndGet());
									}
								}, i);
							}
							p.playSound(as.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 0.5f, 2);
							as.getWorld().spawnParticle(Particle.GLOW, as.getLocation(), 50,1,1,1);
							as.getWorld().spawnParticle(Particle.WAX_ON, as.getLocation(), 50,1,1,1);
							for(Entity e : as.getWorld().getNearbyEntities(as.getLocation().clone(), 6, 6, 6)) {
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
								if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
									LivingEntity le = (LivingEntity)e;
									if(!le.isDead()) {
										le.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, le.getLocation(), 1);

										atk0(0.1, esd.Dispenser.get(p.getUniqueId())*0.06, p, le);
										le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20,20,false,false));
										Holding.holding(p, le, 10l);
									}
								}
							}
						}
					}, i*10);
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						as.remove();
					}
				}, 101);
			}
		}
	}

	final private void Factory(ArmorStand afs, LivingEntity le,Player p) {

		afs.getWorld().spawn(afs.getLocation(), ArmorStand.class, as ->{
			as.setGravity(false);
			as.setVelocity(le.getEyeLocation().clone().toVector().subtract(afs.getEyeLocation().clone().toVector()).normalize().multiply(0.9));
			ItemStack head = new ItemStack(Material.TNT);
			ItemStack ch = new ItemStack(Material.ELYTRA);
			ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
			ItemStack boot = new ItemStack(Material.IRON_BOOTS);
			ItemStack right = new ItemStack(Material.TNT);
			ItemStack left = new ItemStack(Material.TNT);
			as.setCustomName(p.getName());
			as.setBasePlate(false);
			as.setSmall(true);
			as.setInvulnerable(true);
			as.setInvisible(true);
			as.setArms(true);
			as.setHelmet(head);
			as.setChestplate(ch);
			as.setLeggings(leg);
			as.setBoots(boot);
			as.getEquipment().setItemInMainHand(left);
			as.getEquipment().setItemInOffHand(right);
			as.setCanPickupItems(false);
			as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
			as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			if(p.isDead()) {
				as.remove();
			}

			final Location snl = as.getEyeLocation().clone().add(0, 1, 0);
			final Location lel = le.getEyeLocation().clone();
			final Vector v = lel.clone().toVector().subtract(snl.clone().toVector());
			final double dis = lel.distance(snl);
			HashSet<Location> line = new HashSet<>();
			for(double d = 0; d<dis; d += 0.05) {
				line.add(as.getLocation().clone().add(v.clone().normalize().multiply(d)));
			}
			AtomicInteger j = new AtomicInteger();
			line.forEach(l -> {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						as.teleport(l);
					}
				}, j.getAndIncrement());
			});

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
					as.teleport(le);
					p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, as.getLocation(),1);
					as.remove();
					Holding.holding(p,le, 10L);
				}
			}, 6);
		});
	}


	public void Factory(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();



		if(ClassData.pc.get(p.getUniqueId()) == 17)
		{
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && p.isSneaking()&&fcry.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

				if(Proficiency.getpro(p)>=1) {
					sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
				}



				if(fcryt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(fcryt.get(p.getUniqueId()));
					fcryt.remove(p.getUniqueId());
				}
				fcry.remove(p.getUniqueId());


				Holding.invur(p, 20l);

				final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 2).getLocation().clone();
				ArmorStand as = tl.getWorld().spawn(tl, ArmorStand.class);

				as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				as.setInvisible(true);
				as.setMarker(true);
				as.getEquipment().setHelmet(new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA));
				as.setInvulnerable(true);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						as.remove();
					}
				},100);

				for(Entity e : as.getWorld().getNearbyEntities(as.getLocation(), 6, 6,6)) {
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
					if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
						LivingEntity le = (LivingEntity)e;
						Ray2(as,le);
					}
				}


				for(int i = 0; i<10; i++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							if(p.isDead()) {
								as.remove();
							}
							p.playSound(as.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 0.5f, 0.3f);
							p.playSound(as.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 0.5f, 0.3f);
							p.getWorld().playSound(as.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 0.1f, 2);

							for(Entity e : as.getWorld().getNearbyEntities(as.getLocation(), 6, 6,6)) {
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
								if(e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")) && e!=p) {
									LivingEntity le = (LivingEntity)e;
									Ray(as,le);
									atk0(0.34, esd.Dispenser.get(p.getUniqueId())*0.4, p, le);
								}
							}
						}
					}, i*10);
				}
//



			}
		}
	}




	final private void Ray(ArmorStand as, LivingEntity le) {
		final World snw = as.getWorld();
		final Location lel = le.getEyeLocation().clone();
		final Location snl = as.getEyeLocation().clone().add(0, 1, 0);
		final Vector v = lel.clone().toVector().subtract(snl.clone().toVector());
		final double dis = lel.distance(snl);

		HashSet<Location> line = new HashSet<>();
		for(double d = 0; d<dis; d += 0.2) {
			line.add(snl.clone().add(v.clone().normalize().multiply(d)));
		}
		line.forEach(l -> {
			snw.spawnParticle(Particle.BLOCK_CRACK, l, 1, 0.3,0.3,0.3,0, Material.PURPLE_GLAZED_TERRACOTTA.createBlockData());
			snw.spawnParticle(Particle.GLOW, l, 1, 0.1,0.1,0.1);
		});
	}



	final private void Ray2(ArmorStand as, LivingEntity le) {
		final World snw = as.getWorld();
		final Location lel = le.getEyeLocation().clone();
		final Location snl = as.getEyeLocation().clone().add(0, 1, 0);
		final Vector v = lel.clone().toVector().subtract(snl.clone().toVector());
		final double dis = lel.distance(snl);

		HashSet<Location> line = new HashSet<>();
		for(double d = 0; d<dis; d += 0.6) {
			line.add(snl.clone().add(v.clone().normalize().multiply(d)));
		}
		line.forEach(l -> {
			snw.spawnParticle(Particle.BLOCK_MARKER, l, 1, 0.3,0.3,0.3,0, Material.DISPENSER.createBlockData());
		});
	}



	public void X_ray(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =5*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
				{
					p.setCooldown(CAREFUL, 2);

					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("엑스선")
							.ename("X_ray")
							.slot(1)
							.hm(gdcooldown)
							.skillUse(() -> {if(Proficiency.getpro(p)>=1) {
								sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
							}

								if(empt.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(empt.get(p.getUniqueId()));
									empt.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											emp.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										emp.remove(p.getUniqueId());
									}
								}, 25);
								empt.put(p.getUniqueId(), task);

								HashSet<Location> pie = new HashSet<Location>();
								HashSet<LivingEntity> les = new HashSet<LivingEntity>();
								final Location pl = p.getLocation().clone();

								Double area = 6.1;
								if(shipt.containsKey(p.getUniqueId())){
									area = 12.1;
								}
								for(double d = 0.1; d <= area; d += 1) {
									for(double d1 = -Math.PI/6; d1<= Math.PI/6; d1 += Math.PI/60) {
										pie.add(pl.clone().add(pl.clone().getDirection().normalize().rotateAroundY(d1).multiply(d)));
									}
								}

								p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 2);
								pie.forEach(l ->{
									p.getWorld().spawnParticle(Particle.FLASH, l.add(0, -0.56, 0), 1, 0.5,0.5,0.5,0);

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
								});
								for(LivingEntity le: les)
								{
									atk0(0.32, esd.X_ray.get(p.getUniqueId())*0.24, p, le);
									le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,40,40,false,false));
									Holding.holding(p, le, (long) 30);

									if(xray.containsKey(le)) {
										xray.computeIfPresent(le, (k,v) -> v+1);
									}
									else {
										xray.put(le, 0);
										xrayp.put(le, p);
									}
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											xray.computeIfPresent(le, (k, v) -> v-1);
											if(xray.get(le)<0) {
												xray.remove(le);
												xrayp.remove(le);
											}
										}
									}, 50);
								}
							});
					bd.execute();


				} // adding players name + current system time in miliseconds

			}}
	}


	public void X_ray(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof LivingEntity)
		{
			LivingEntity le = (LivingEntity)d.getDamager();
			if(xray.containsKey(le)) {
				d.setDamage(d.getDamage()*0.65);
			}
		}
	}




	public void EMP(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK) &&emp.containsKey(p.getUniqueId()))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
				{
					if(Proficiency.getpro(p)>=1) {
						sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					}

					if(empt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(empt.get(p.getUniqueId()));
						empt.remove(p.getUniqueId());
					}
					emp.remove(p.getUniqueId());



					if(grsht.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(grsht.get(p.getUniqueId()));
						grsht.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								grsh.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 3);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							grsh.remove(p.getUniqueId());
						}
					}, 25);
					grsht.put(p.getUniqueId(), task);

					Snowball sn = p.launchProjectile(Snowball.class);
					sn.setBounce(true);
					sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					sn.setMetadata("emp", new FixedMetadataValue(RMain.getInstance(), true));
					sn.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					sn.setShooter(p);
					sn.setItem(new ItemStack(Material.EMERALD_BLOCK));
					sn.setGlowing(true);
					sn.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.6));
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							if(sn.isValid()) {
								sn.remove();
								Bukkit.getPluginManager().callEvent(new ProjectileHitEvent(sn, sn.getLocation().getBlock()));
							}
						}
					}, 15);

				} // adding players name + current system time in miliseconds

			}
		}
	}


	public void EMP(ProjectileHitEvent d)
	{
		if(d.getEntity().hasMetadata("emp")) {
			Player p = (Player) d.getEntity().getShooter();
			p.getWorld().spawnParticle(Particle.COMPOSTER, d.getEntity().getLocation(), 200, 2,2,2,1);
			p.getWorld().spawnParticle(Particle.GLOW, d.getEntity().getLocation(), 400, 2,2,2,1);
			p.getWorld().spawnParticle(Particle.WAX_ON, d.getEntity().getLocation(), 400, 2,2,2,1);
			p.getWorld().spawnParticle(Particle.WARPED_SPORE, d.getEntity().getLocation(), 400, 2,2,2,1);
			p.playSound(d.getEntity().getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 0.85f, 1.15f);
			for (Entity e : d.getEntity().getLocation().getWorld().getNearbyEntities(d.getEntity().getLocation(), 3, 3, 3))
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
					atk0(0.7, esd.X_ray.get(p.getUniqueId())*0.57, p, le);
					Holding.holding(p, le, 30l);
					p.getWorld().spawnParticle(Particle.VIBRATION, le.getLocation(), 10, 1,1,1, new Vibration(d.getEntity().getLocation(), new Destination.EntityDestination(le), 10));
				}
			}
		}
	}



	public void GravityShift(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if((p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK) &&grsh.containsKey(p.getUniqueId()))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
				{

					if(Proficiency.getpro(p)>=1) {
						sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					}

					if(grsht.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(grsht.get(p.getUniqueId()));
						grsht.remove(p.getUniqueId());
					}
					grsh.remove(p.getUniqueId());

					p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, p.getLocation(), 50, 1,1,1);
					final Location pfel = p.getEyeLocation().clone();
					Location tl = pfel.clone();

					for(int i = 0 ; i<25; i++) {
						Location tell = pfel.clone().add(pfel.clone().getDirection().normalize().multiply(i*0.2));
						if(tell.getBlock().isPassable()) {
							tl = tell;
						}
					}

					p.teleport(tl);
					p.playSound(tl, Sound.ENTITY_SHULKER_TELEPORT, 0.85f, 1.15f);
					p.playSound(tl, Sound.ENTITY_SLIME_JUMP, 0.85f, 1.6f);
					Holding.superholding(p, p, 8l);

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 0.85f, 2f);
							p.playSound(p.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 0.85f, 2f);
							for(Entity e : p.getWorld().getNearbyEntities(p.getLocation(),5, 5, 5)) {
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
									atk0(0.76, esd.X_ray.get(p.getUniqueId())*0.45, p, le);
									le.teleport(p);
									Holding.superholding(p, le, 40l);
								}
							}
						}
					}, 7);

				} // adding players name + current system time in miliseconds

			}
		}
	}



	public void Graviton(EntityDamageEvent d)
	{
		if(d.getEntity().hasMetadata("graviton")) {
			d.setCancelled(true);
		}
	}

	public void Graviton(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();
		double sec =8*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking())
			{
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("중력자")
						.ename("Graviton")
						.slot(2)
						.hm(cdcooldown)
						.skillUse(() -> {
							if(Proficiency.getpro(p)>=1) {
								sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
							}

							if(engbt.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(engbt.get(p.getUniqueId()));
								engbt.remove(p.getUniqueId());
							}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									if(Proficiency.getpro(p)>=1) {
										engb.putIfAbsent(p.getUniqueId(), 0);
									}
								}
							}, 3);

							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									engb.remove(p.getUniqueId());
								}
							}, 25);
							engbt.put(p.getUniqueId(), task);

							final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();
							EnderCrystal gravit = (EnderCrystal) p.getWorld().spawnEntity(l, EntityType.ENDER_CRYSTAL);
							gravit.setInvulnerable(true);
							gravit.setShowingBottom(false);
							gravit.setMetadata("graviton of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							gravit.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
							gravit.setMetadata("graviton", new FixedMetadataValue(RMain.getInstance(), true));
							gravit.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
							gravit.setGlowing(true);

							if(shipt.containsKey(p.getUniqueId())){
								for(int i=-1; i<=1; i++){
									p.getWorld().spawn(l.clone().add(0,i,0), EnderCrystal.class, graviton -> {
										graviton.setInvulnerable(true);
										graviton.setShowingBottom(false);
										graviton.setMetadata("graviton of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										graviton.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
										graviton.setMetadata("graviton", new FixedMetadataValue(RMain.getInstance(), true));
										graviton.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
										graviton.setGlowing(true);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run()
											{
												graviton.remove();
												p.playSound(graviton.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
												p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, graviton.getLocation(), 1,1,1,1);
											}
										}, 42);
									});
									p.getWorld().spawn(l.clone().add(i,0,0), EnderCrystal.class, graviton -> {
										graviton.setInvulnerable(true);
										graviton.setShowingBottom(false);
										graviton.setMetadata("graviton of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										graviton.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
										graviton.setMetadata("graviton", new FixedMetadataValue(RMain.getInstance(), true));
										graviton.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
										graviton.setGlowing(true);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run()
											{
												graviton.remove();
												p.playSound(graviton.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
												p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, graviton.getLocation(), 1,1,1,1);
											}
										}, 42);
									});
									p.getWorld().spawn(l.clone().add(0,0,i), EnderCrystal.class, graviton -> {
										graviton.setInvulnerable(true);
										graviton.setShowingBottom(false);
										graviton.setMetadata("graviton of"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
										graviton.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
										graviton.setMetadata("graviton", new FixedMetadataValue(RMain.getInstance(), true));
										graviton.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
										graviton.setGlowing(true);
										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run()
											{
												graviton.remove();
												p.playSound(graviton.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
												p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, graviton.getLocation(), 1,1,1,1);
											}
										}, 42);
									});
								}
							}
							for(int i =0; i<9; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										gravit.setBeamTarget(p.getLocation().add(0, -0.5, 0));
										p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 0.5f, 2.0f);
										Double area = 5.2;
										if(shipt.containsKey(p.getUniqueId())){
											area = 11.5;
										}
										for (Entity e : p.getWorld().getNearbyEntities(l, area, area, area))
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
													if(le instanceof EnderDragon) {
														le.teleport(l.clone().add(0, 1.23, 0));
													}
													else {
														le.teleport(l);
													}

													Holding.holding(p, le, 6l);
													atk0(0.12, esd.Graviton.get(p.getUniqueId())*0.085, p, le);
												}
											}
										}
									}
								}, i*4);
							}
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									gravit.remove();
									p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
									p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1,1,1,1);
									Double area = 5.2;
									if(shipt.containsKey(p.getUniqueId())){
										area = 11.5;
									}
									for (Entity e : p.getWorld().getNearbyEntities(l, area, area, area))
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
												atk0(0.52, esd.Graviton.get(p.getUniqueId())*0.5, p, le);

											}
										}
									}
								}
							}, 42);
						});
				bd.execute();

			}
		}

	}


	public void EnergyBall(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking() && engb.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

				if(Proficiency.getpro(p)>=1) {
					sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
				}

				if(engbt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(engbt.get(p.getUniqueId()));
					engbt.remove(p.getUniqueId());
				}
				engb.remove(p.getUniqueId());



				if(orbt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(orbt.get(p.getUniqueId()));
					orbt.remove(p.getUniqueId());
				}

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						if(Proficiency.getpro(p)>=2) {
							orb.putIfAbsent(p.getUniqueId(), 0);
						}
					}
				}, 3);

				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						orb.remove(p.getUniqueId());
					}
				}, 25);
				orbt.put(p.getUniqueId(), task);

				Snowball sn = p.launchProjectile(Snowball.class);
				sn.setBounce(false);
				sn.setGravity(false);
				sn.setVelocity(p.getEyeLocation().clone().getDirection().normalize().multiply(0.076));
				sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				sn.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
				sn.setShooter(p);
				ItemStack eb = new ItemStack(Material.RESPAWN_ANCHOR);
				eb.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
				sn.setItem(eb);
				sn.setGlowing(true);

				for(int i =0; i<20; i++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							p.playSound(sn.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 0.055f, 2.0f);
							sn.getWorld().spawnParticle(Particle.END_ROD, sn.getLocation(), 10,1,1,1);
							sn.getWorld().spawnParticle(Particle.FLASH, sn.getLocation(), 10,1,1,1);
							for (Entity e : p.getWorld().getNearbyEntities(sn.getLocation(), 5, 5, 5))
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
									atk0(0.25, esd.Graviton.get(p.getUniqueId())*0.25, p, le);
									Holding.holding(p, le, 6l);
								}
							}
						}
					}, i*3);
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						sn.remove();
					}
				}, 62);
			}
		}

	}

	private void Orbital(Location il, World w) {

		ArrayList<Location> line = new ArrayList<Location>();
		ArrayList<Location> line2 = new ArrayList<Location>();
		ArrayList<Location> line3 = new ArrayList<Location>();
		ArrayList<Location> line4 = new ArrayList<Location>();

		Location pl = il.clone();
		Vector pfv = il.clone().add(0, 1, 0).toVector().subtract(il.clone().toVector());
		Vector pv = pfv.clone().rotateAroundAxis(pl.clone().getDirection(), -Math.PI/4);
		Vector pv2 = pfv.clone().rotateAroundAxis(pl.clone().getDirection(), Math.PI/4);
		Vector pv3 = pfv.clone().rotateAroundAxis(pl.clone().getDirection(), Math.PI/2);

		for(double an = 0; an > -Math.PI*2; an -= Math.PI/90) {
			Location sw = pl.clone().add(pl.clone().getDirection().normalize().rotateAroundAxis(pv, an).multiply(2.6));
			line.add(sw);
			Location sw1 = pl.clone().add(pl.clone().getDirection().normalize().rotateAroundAxis(pv2, an).multiply(2.6));
			line2.add(sw1);
			Location sw11 = pl.clone().add(pl.clone().getDirection().normalize().rotateAroundAxis(pv3, an).multiply(2.6));
			line4.add(sw11);
		}
		for(double an = Math.PI/2.5; an>-Math.PI/2.5; an-=Math.PI/90) {
			line3.add(pl.clone().add(pl.getDirection().rotateAroundY(an).normalize().multiply(2.6)));
		}
		line.forEach(l -> {
			w.spawnParticle(Particle.WAX_ON, l,2,0.1,0.1,0.1,0.1);
		});
		line2.forEach(l -> {
			w.spawnParticle(Particle.WAX_OFF, l,2,0.1,0.1,0.1,0.1);
		});
		line3.forEach(l -> {
			w.spawnParticle(Particle.TOWN_AURA, l,2,0.1,0.1,0.1,0.1);
		});
		line4.forEach(l -> {
			w.spawnParticle(Particle.GLOW, l,2,0.1,0.1,0.1,0);
		});
	}




	public void Orbital(PlayerSwapHandItemsEvent ev) //https://www.spigotmc.org/members/beefystick.28035/
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && !p.isSneaking() && orb.containsKey(p.getUniqueId()))
			{
				ev.setCancelled(true);

				if(Proficiency.getpro(p)>=1) {
					sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
				}


				if(orbt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(orbt.get(p.getUniqueId()));
					orbt.remove(p.getUniqueId());
				}
				orb.remove(p.getUniqueId());

				final Location tl = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 5).getLocation();
				final World tw = tl.getWorld();

				AtomicInteger j = new AtomicInteger();
				AtomicDouble ad = new AtomicDouble();

				Item sn1 = p.getWorld().dropItemNaturally(tl.add(1, 0, 1), new ItemStack(Material.RAW_COPPER_BLOCK));
				Item sn2 = p.getWorld().dropItemNaturally(tl.add(-1, 0, -1), new ItemStack(Material.RAW_IRON_BLOCK));

				sn1.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				sn1.setOwner(p.getUniqueId());
				sn1.setGlowing(true);
				sn1.setGravity(false);
				sn1.setPickupDelay(999999);

				sn2.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				sn2.setOwner(p.getUniqueId());
				sn2.setGlowing(true);
				sn2.setGravity(false);
				sn2.setPickupDelay(999999);
				sn1.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
				sn2.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));

				Vector w = sn1.getLocation().getDirection().clone().normalize().rotateAroundAxis(p.getLocation().getDirection().clone().rotateAroundY(Math.PI/2), Math.PI/2);


				for(double an = 0; an <Math.PI*4; an += Math.PI/90) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							Vector r = sn1.getLocation().getDirection().clone().rotateAroundY(ad.getAndAdd(Math.PI/90)).normalize().multiply(2);
							Vector v = w.clone().multiply(r.clone());
							sn1.setVelocity(v);
							sn2.setVelocity(v);
						}
					}, j.getAndIncrement()/9);
				}

				for(int i = 0; i <13; i++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							Orbital(tl,tw);
							for(Entity e : p.getWorld().getNearbyEntities(tl,5, 5, 5)) {
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
									atk0(0.2, esd.Graviton.get(p.getUniqueId())*0.45, p, le);
								}
							}
						}
					}, i*3);
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						sn1.remove();
						sn2.remove();
					}
				}, 40);
			}
		}

	}


	public void Electrostatic(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =7*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);




		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
				{
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("전자기장")
							.ename("Electrostatic")
							.slot(3)
							.hm(frcooldown)
							.skillUse(() -> {
								final Location l = gettargetblock(p,4);
								if(Proficiency.getpro(p)>=1) {
									sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
									cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
									frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
									smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
									stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
									gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								}

								if(thcrt.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(thcrt.get(p.getUniqueId()));
									thcrt.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											thcr.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										thcr.remove(p.getUniqueId());
									}
								}, 25);
								thcrt.put(p.getUniqueId(), task);

								p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1, 0);
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1, 2);
								for(int i =0; i<8; i++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											int area = 4;
											if(shipt.containsKey(p.getUniqueId())){
												area = 8;
											}
											p.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, l, area*50, area,1,area,0.3);
											p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l, area*50, area,1,area,0.3);
											p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 0.8f, 2);
											for (Entity a : p.getWorld().getNearbyEntities(l, area, 4, area))
											{
												if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal")))
												{
													LivingEntity le = (LivingEntity)a;

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

													atk0(0.1, esd.Electrostatic.get(p.getUniqueId())*0.078, p, le);
													Holding.holding(p, le, 40l);
													if(shipt.containsKey(p.getUniqueId())){
														try {
															le.teleport(le.getLocation().clone().add(l.toVector().normalize().subtract(le.getLocation().clone().toVector().normalize()).normalize().multiply(1.4)));
														}
														catch(IllegalArgumentException e) {
															le.teleport(l);
														}
													}
												}
											}
										}
									}, i*4);
								}

							});
					bd.execute();


				} // adding players name + current system time in miliseconds

			}}
	}



	@SuppressWarnings("deprecation")
	public void ThunderCaller(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&& thcr.containsKey(p.getUniqueId()))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
				{

					if(Proficiency.getpro(p)>=1) {
						sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					}

					if(thcrt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(thcrt.get(p.getUniqueId()));
						thcrt.remove(p.getUniqueId());
					}
					thcr.remove(p.getUniqueId());



					if(angvt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(angvt.get(p.getUniqueId()));
						angvt.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								angv.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 3);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							angv.remove(p.getUniqueId());
						}
					}, 25);
					angvt.put(p.getUniqueId(), task);

					final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();

					p.getWorld().playSound(l, Sound.BLOCK_BEACON_ACTIVATE, 0.15f, 0);
					p.getWorld().playSound(l, Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, 1, 0);

					ArmorStand as = (ArmorStand)p.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
					ItemStack head = new ItemStack(Material.LIGHTNING_ROD);
					ItemStack ch = new ItemStack(Material.ELYTRA);
					ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
					ItemStack boot = new ItemStack(Material.IRON_BOOTS);
					ItemStack right = new ItemStack(Material.END_ROD);
					ItemStack left = new ItemStack(Material.END_ROD);
					as.setCustomName(p.getName());
					as.setBasePlate(true);
					as.setMarker(true);
					as.setInvulnerable(true);
					as.setInvisible(true);
					as.setArms(true);
					as.setHelmet(head);
					as.setChestplate(ch);
					as.setLeggings(leg);
					as.setBoots(boot);
					as.getEquipment().setItemInMainHand(left);
					as.getEquipment().setItemInOffHand(right);
					as.setCanPickupItems(false);
					as.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					as.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					if(p.isDead()) {
						as.remove();
					}

					for(int i =1; i<20; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								as.getWorld().spawnParticle(Particle.FLASH, l, 10, 2,2,2);
								as.getWorld().playSound(l, Sound.BLOCK_BEACON_ACTIVATE, 0.8f, 2);
							}
						}, i*3);
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							as.remove();
							for (Entity a : l.getWorld().getNearbyEntities(l, 5, 10, 5))
							{
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal")))
								{
									LivingEntity le = (LivingEntity)a;
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

									atk0(2.1, esd.Electrostatic.get(p.getUniqueId())*2.0, p, le);
									le.getWorld().strikeLightningEffect(le.getLocation());

						                    /*
											Holding.holding(p, le, 40l);
											if(le instanceof EnderDragon) {
												Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, ar->{
													ar.setShooter(p);
													ar.setCritical(false);
													ar.setSilent(true);
													ar.setPickupStatus(PickupStatus.DISALLOWED);
													ar.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
												});
												enar.setDamage(player_damage.get(p.getName())*3 + esd.Electrostatic.get(p.getUniqueId())*3);
											}
											p.setCooldown(Material.YELLOW_TERRACOTTA, 1);
											le.damage(0, p);
											le.damage(player_damage.get(p.getName())*3 + esd.Electrostatic.get(p.getUniqueId())*3, p);
											*/
								}
							}
						}
					}, 60);
				}
			}
		}
	}


	public void Anti_gravity(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		if(ClassData.pc.get(p.getUniqueId()) == 17) {
			if(!(p.isSneaking()) && (ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK)&& angv.containsKey(p.getUniqueId()))
			{
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE"))
				{

					if(Proficiency.getpro(p)>=1) {
						sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
						gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					}

					if(angvt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(angvt.get(p.getUniqueId()));
						angvt.remove(p.getUniqueId());
					}
					angv.remove(p.getUniqueId());

					final Location l = p.getTargetBlock(new HashSet<>(Arrays.asList(Material.WATER, Material.LAVA, Material.AIR, Material.VOID_AIR, Material.GRASS)), 4).getLocation();

					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 0);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 0.5f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 0.7f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 0.9f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 1.1f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 1.4f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 1.8f);
					p.getWorld().playSound(l, Sound.BLOCK_BEACON_DEACTIVATE, 0.4f, 2f);

					p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, l, 200, 5,1,5);
					p.getWorld().spawnParticle(Particle.WARPED_SPORE, l, 400, 5,1,5);

					for (Entity a : p.getWorld().getNearbyEntities(l, 5, 3, 5))
					{
						if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal")))
						{
							LivingEntity le = (LivingEntity)a;
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

							Holding.holding(p, le, 20l);
							for(int h=0; h<3; h++) {
								if(le.getLocation().clone().add(0, 1, 0).getBlock().isPassable()) {
									le.teleport(le.getLocation().clone().add(0, 1, 0));
								}
								else {
									break;
								}
							}

						}
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							l.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, l.clone().add(0,3,0), 200, 5,1,5);
							l.getWorld().spawnParticle(Particle.WARPED_SPORE, l.clone().add(0,3,0), 400, 5,1,5);
							l.getWorld().spawnParticle(Particle.END_ROD, l.clone().add(0,3,0), 400, 5,1,5);

							l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 0);
							l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 0.5f);
							l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 0.7f);
							l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 0.9f);
							l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 1.1f);
							l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 1.4f);
							l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 1.8f);
							l.getWorld().playSound(l, Sound.BLOCK_CONDUIT_ACTIVATE, 0.4f, 2f);
							for (Entity a : l.getWorld().getNearbyEntities(l, 5, 10, 5))
							{
								if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal")))
								{
									LivingEntity le = (LivingEntity)a;
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
									le.setFallDistance(10);
									for(int h=0; h<3; h++) {
										if(le.getLocation().clone().add(0, -1, 0).getBlock().isPassable()) {
											le.teleport(le.getLocation().clone().add(0, -1, 0));
										}
										else {
											break;
										}
									}
									atk0(1.1, esd.Electrostatic.get(p.getUniqueId())*1.0, p, le);
								}
							}
						}
					}, 20);
				}
			}
		}
	}


	public void Magnetic(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled())
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			Location el =le.getLocation();


			double sec =20*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);



			if(ClassData.pc.get(p.getUniqueId()) == 17 && esd.Magnetic.get(p.getUniqueId())>=1) {
				if((p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) && (p.isSneaking()) && !(p.hasCooldown(Material.YELLOW_TERRACOTTA)))
				{
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("자기장")
							.ename("Magnetic")
							.slot(4)
							.hm(stcooldown)
							.skillUse(() -> {
								if(Proficiency.getpro(p)>=1) {
									sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
									cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
									frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
									smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
									stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
									gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								}

								Holding.superholding(p, le, 52l);
								p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 0f);
								p.getWorld().spawnParticle(Particle.BLOCK_CRACK, el, 50, 1,1,1, Material.IRON_BLOCK.createBlockData());
								for(int i = 0; i<25; i++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											le.teleport(p);
											le.getWorld().playSound(le.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 0.5f, 0);
											if(Proficiency.getpro(p)>=1) {
												for (Entity a : p.getNearbyEntities(2, 2, 2))
												{
													if ((!(a == p))&& a instanceof LivingEntity&& !(a.hasMetadata("fake"))&& !(a.hasMetadata("portal")))
													{
														LivingEntity le = (LivingEntity)a;
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

														le.teleport(p);
													}
												}
											}
										}
									}, i*2);

								}
							});
					bd.execute();

				}
			}
		}
	}



	@SuppressWarnings("deprecation")
	public void Jetpack(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		double sec =6*(1-p.getAttribute(Attribute.GENERIC_LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);





		if(ClassData.pc.get(p.getUniqueId()) == 17 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && esd.Jetpack.get(p.getUniqueId())>=1) {
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK)&& p.getCooldown(CAREFUL) <=0)
			{
				ev.setCancelled(true);
				SkillBuilder bd = new SkillBuilder()
						.player(p)
						.cooldown(sec)
						.kname("제트팩")
						.ename("Jetpack")
						.slot(5)
						.hm(smcooldown)
						.skillUse(() -> {
							if(Proficiency.getpro(p)>=1) {
								sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
								gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
							}

							if(proplt.containsKey(p.getUniqueId())) {
								Bukkit.getScheduler().cancelTask(proplt.get(p.getUniqueId()));
								proplt.remove(p.getUniqueId());
							}

							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									if(Proficiency.getpro(p)>=1) {
										propl.putIfAbsent(p.getUniqueId(), 0);
									}
								}
							}, 3);

							int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run() {
									propl.remove(p.getUniqueId());
								}
							}, 25);
							proplt.put(p.getUniqueId(), task);

							p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, p.getLocation(), 100, 1, 2, 1);
							p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 0);
							p.setVelocity(BlockFace.UP.getDirection().normalize().multiply(1.5));
						});
				bd.execute();

			}
		}
	}


	@SuppressWarnings("deprecation")
	public void Propellant(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 17 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && esd.Jetpack.get(p.getUniqueId())>=1) {
			if((!p.isSneaking())&& !p.isOnGround() && (ac == Action.LEFT_CLICK_AIR || ac== Action.LEFT_CLICK_BLOCK) &&(ac!= Action.RIGHT_CLICK_AIR)&&(ac!= Action.RIGHT_CLICK_AIR) && propl.containsKey(p.getUniqueId()))
			{
				if(Proficiency.getpro(p)>=1) {
					sdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					cdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					frcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					smcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					stcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
					gdcooldown.computeIfPresent(p.getName(), (k,v)-> v-500);
				}

				if(proplt.containsKey(p.getUniqueId())) {
					Bukkit.getScheduler().cancelTask(proplt.get(p.getUniqueId()));
					proplt.remove(p.getUniqueId());
				}
				propl.remove(p.getUniqueId());

				p.getWorld().spawnParticle(Particle.FLASH, p.getLocation(), 10, 1, 2, 1);
				p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 2);
				p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 2);
				p.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(1.8));
			}
		}
	}


	@SuppressWarnings("deprecation")
	public void Jetpack(EntityDamageEvent d)
	{
		if (!(d.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) d.getEntity();



		if(ClassData.pc.get(p.getUniqueId()) == 17 && p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") && esd.Jetpack.get(p.getUniqueId())>=1) {
			if(d.getCause().equals(DamageCause.FALL))
			{
				p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 65*(int)p.getFallDistance(), p.getFallDistance(), 1, p.getFallDistance(), p.getLocation().add(0, -1, 0).getBlock().getBlockData());
				for(Entity e: p.getNearbyEntities(p.getFallDistance()/2, 2, p.getFallDistance()/2)) {
					if(e instanceof LivingEntity) {
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
						atk1(0.03*p.getFallDistance(), p, le);

					}
				}
				d.setCancelled(true);
			}
			if(d.getCause() != DamageCause.VOID && d.getDamage() >= p.getMaxHealth()*0.20) {
				d.setDamage(p.getMaxHealth()*0.20);

			}
		}
	}


	private ArrayListMultimap<UUID, LivingEntity> ars = ArrayListMultimap.create();

	private HashMap<UUID, Integer> shipt = new HashMap<UUID, Integer>();

	public EulerAngle conv(float pit) {

		float angle = (float)Math.toRadians(pit);
		if(pit<0) {
			angle = (float)Math.toRadians(360+pit);
		}

		return new EulerAngle(angle,0,0);
	}
	final private void battleCrus(Player p) {


		endRiding(p);

		final World pw = p.getWorld();

		p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 0.2f, 0.5f);
		p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.2f, 0f);


		final Location pfl = p.getLocation().clone().add(0, -0.25, 0);
		final Location pel = p.getEyeLocation().clone();
		final Vector rr = p.getFacing().getDirection().clone().rotateAroundY(-Math.PI/2);
		final Vector rot = pel.clone().getDirection().getCrossProduct(rr);
		final Vector pl1 = pel.clone().getDirection().normalize().rotateAroundAxis(rot.clone(),-Math.PI/2);
		final Vector pl2 = pel.clone().clone().getDirection().normalize().rotateAroundAxis(rot.clone(),Math.PI/2);

		ArrayList<Location> iron = new ArrayList<>();
		ArrayList<Location> glass = new ArrayList<>();
		ArrayList<Location> beacon = new ArrayList<>();
		for(double y=-1; y<=1;y+=0.5) {
			if(y==0){
				continue;
			}
			for(double z=-2.5; z<=1;z+=0.5) {
				iron.add(pfl.clone().add(pl1.clone().multiply(1)).add(pel.clone().getDirection().normalize().multiply(z)).add(rot.clone().normalize().multiply(y)));
				iron.add(pfl.clone().add(pl2.clone().multiply(1)).add(pel.clone().getDirection().normalize().multiply(z)).add(rot.clone().normalize().multiply(y)));
			}
		}
		for(double z=-2.5; z<=1;z+=0.5) {
			glass.add(pfl.clone().add(pl1.clone().multiply(1)).add(pel.clone().getDirection().normalize().multiply(z)));
			glass.add(pfl.clone().add(pl2.clone().multiply(1)).add(pel.clone().getDirection().normalize().multiply(z)));
		}
		//wall


		for(double x=-1; x<=1;x+=0.5) {
			for(double z=-2.5; z<=2;z+=0.5) {
				glass.add(pfl.clone().add(pl1.clone().multiply(x)).add(pel.clone().getDirection().normalize().multiply(z)).add(rot.clone().normalize().multiply(1)));
				glass.add(pfl.clone().add(pl2.clone().multiply(x)).add(pel.clone().getDirection().normalize().multiply(z)).add(rot.clone().normalize().multiply(-1)));
			}
		}
		for(double y=-1; y<=1;y+=0.5) {

			for(double x=-1; x<=1;x+=0.5) {
				glass.add(pfl.clone().add(pl1.clone().multiply(x)).add(pel.clone().getDirection().normalize().multiply(-2.5)).add(rot.clone().normalize().multiply(y)));
				beacon.add(pfl.clone().add(pl1.clone().multiply(x)).add(pel.clone().getDirection().normalize().multiply(-3)).add(rot.clone().normalize().multiply(y)));
				if(x==0){
					continue;
				}
				glass.add(pfl.clone().add(pl2.clone().multiply(x)).add(pel.clone().getDirection().normalize().multiply(2)).add(rot.clone().normalize().multiply(y)));
			}
		}
		//glass

		for(double z=-2.5; z<=1;z+=0.5) {
			beacon.add(pfl.clone().add(pel.clone().getDirection().normalize().multiply(z)).add(rot.clone().normalize().multiply(-1.5)));
		}
		for(double z=-2.5; z<=1;z+=0.5) {
			beacon.add(pfl.clone().add(pl1.clone().multiply(1.5)).add(pel.clone().getDirection().normalize().multiply(z)));
			beacon.add(pfl.clone().add(pl2.clone().multiply(1.5)).add(pel.clone().getDirection().normalize().multiply(z)));
		}
		//beacon


		iron.add(pfl.clone().add(pl1.clone().multiply(0.5)).add(rot.clone().normalize().multiply(-1)));
		iron.add(pfl.clone().add(pl2.clone().multiply(0.5)).add(rot.clone().normalize().multiply(-1)));
		iron.add(pfl.clone().add(rot.clone().normalize().multiply(-1)).add(pel.clone().getDirection().normalize().multiply(1)));
		iron.add(pfl.clone().add(rot.clone().normalize().multiply(-1)).add(pel.clone().getDirection().normalize().multiply(-1)));
		iron.add(pfl.clone().add(rot.clone().normalize().multiply(-1)));
		iron.add(pfl.clone().add(rot.clone().normalize().multiply(-2)));
		//seat

		iron.forEach(l ->{
					pw.spawn(l.clone(), ArmorStand.class, ar -> {
						ar.setGravity(false);
						ar.setInvisible(true);
						ar.setCollidable(false);
						ar.setAI(true);

						ar.setBasePlate(false);
						ar.setInvulnerable(true);
						ar.getEquipment().setHelmet(new ItemStack(Material.CYAN_STAINED_GLASS));
						ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						ar.setMetadata("rob" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						ar.setHeadPose(conv(pel.getPitch()));
						ars.put(p.getUniqueId(), ar);
					});
				}
		);

		glass.forEach(l ->{
					pw.spawn(l.clone(), ArmorStand.class, ar -> {
						ar.setGravity(false);
						ar.setInvisible(true);
						ar.setCollidable(false);
						ar.setAI(true);

						ar.setBasePlate(false);
						ar.setInvulnerable(true);
						ar.getEquipment().setHelmet(new ItemStack(Material.GLASS_PANE));
						ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						ar.setMetadata("rob" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						ar.setHeadPose(conv(pel.getPitch()));
						ars.put(p.getUniqueId(), ar);
					});
				}
		);


		beacon.forEach(l ->{
					pw.spawn(l.clone(), ArmorStand.class, ar -> {
						ar.setGravity(false);
						ar.setInvisible(true);
						ar.setCollidable(false);
						ar.setAI(true);

						ar.setBasePlate(false);
						ar.setInvulnerable(true);
						ar.getEquipment().setHelmet(new ItemStack(Material.BEACON));
						ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						ar.setMetadata("rob" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						ar.setHeadPose(conv(pel.getPitch()));
						ars.put(p.getUniqueId(), ar);
					});
				}
		);

		int q =Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
			@Override
			public void run()
			{
				if(p.isDead() || !p.isOnline()){
					endRiding(p);
				}
				p.playSound(p.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 0.02f, 2f);

				final Location pfl = p.getLocation().clone().add(0, -0.25, 0);
				final Location pel = p.getEyeLocation().clone();
				final Vector rr = p.getFacing().getDirection().clone().rotateAroundY(-Math.PI/2);
				final Vector rot = pel.clone().getDirection().getCrossProduct(rr);
				final Vector pl1 = pel.clone().getDirection().normalize().rotateAroundAxis(rot.clone(),-Math.PI/2);
				final Vector pl2 = pel.clone().clone().getDirection().normalize().rotateAroundAxis(rot.clone(),Math.PI/2);

				ArrayList<Location> iron = new ArrayList<>();
				ArrayList<Location> glass = new ArrayList<>();
				ArrayList<Location> beacon = new ArrayList<>();
				for(double y=-1; y<=1;y+=0.5) {
					if(y==0){
						continue;
					}
					for(double z=-2.5; z<=1;z+=0.5) {
						iron.add(pfl.clone().add(pl1.clone().multiply(1)).add(pel.clone().getDirection().normalize().multiply(z)).add(rot.clone().normalize().multiply(y)));
						iron.add(pfl.clone().add(pl2.clone().multiply(1)).add(pel.clone().getDirection().normalize().multiply(z)).add(rot.clone().normalize().multiply(y)));
					}
				}
				for(double z=-2.5; z<=1;z+=0.5) {
					glass.add(pfl.clone().add(pl1.clone().multiply(1)).add(pel.clone().getDirection().normalize().multiply(z)));
					glass.add(pfl.clone().add(pl2.clone().multiply(1)).add(pel.clone().getDirection().normalize().multiply(z)));
				}
				//wall


				for(double x=-1; x<=1;x+=0.5) {
					for(double z=-2.5; z<=2;z+=0.5) {
						glass.add(pfl.clone().add(pl1.clone().multiply(x)).add(pel.clone().getDirection().normalize().multiply(z)).add(rot.clone().normalize().multiply(1)));
						glass.add(pfl.clone().add(pl2.clone().multiply(x)).add(pel.clone().getDirection().normalize().multiply(z)).add(rot.clone().normalize().multiply(-1)));
					}
				}
				for(double y=-1; y<=1;y+=0.5) {

					for(double x=-1; x<=1;x+=0.5) {
						glass.add(pfl.clone().add(pl1.clone().multiply(x)).add(pel.clone().getDirection().normalize().multiply(-2.5)).add(rot.clone().normalize().multiply(y)));
						beacon.add(pfl.clone().add(pl1.clone().multiply(x)).add(pel.clone().getDirection().normalize().multiply(-3)).add(rot.clone().normalize().multiply(y)));
						if(x==0){
							continue;
						}
						glass.add(pfl.clone().add(pl2.clone().multiply(x)).add(pel.clone().getDirection().normalize().multiply(1.5)).add(rot.clone().normalize().multiply(y)));
					}
				}
				//glass

				for(double z=-2.5; z<=1;z+=0.5) {
					beacon.add(pfl.clone().add(pel.clone().getDirection().normalize().multiply(z)).add(rot.clone().normalize().multiply(-1.5)));
				}
				for(double z=-2.5; z<=1;z+=0.5) {
					beacon.add(pfl.clone().add(pl1.clone().multiply(1.5)).add(pel.clone().getDirection().normalize().multiply(z)));
					beacon.add(pfl.clone().add(pl2.clone().multiply(1.5)).add(pel.clone().getDirection().normalize().multiply(z)));
				}
				//beacon


				iron.add(pfl.clone().add(pl1.clone().multiply(0.5)).add(rot.clone().normalize().multiply(-1)));
				iron.add(pfl.clone().add(pl2.clone().multiply(0.5)).add(rot.clone().normalize().multiply(-1)));
				iron.add(pfl.clone().add(rot.clone().normalize().multiply(-1)).add(pel.clone().getDirection().normalize().multiply(1)));
				iron.add(pfl.clone().add(rot.clone().normalize().multiply(-1)).add(pel.clone().getDirection().normalize().multiply(-1)));
				iron.add(pfl.clone().add(rot.clone().normalize().multiply(-1)));
				iron.add(pfl.clone().add(rot.clone().normalize().multiply(-2)));
				//seat

				final Location fl = p.getLocation().clone();
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run()
					{
						if(!shipt.containsKey(p.getUniqueId())) {
							return;
						}
						AtomicInteger j  = new AtomicInteger();
						Location sl = p.getLocation().clone();
						Vector v =  sl.toVector().subtract(fl.toVector()).clone();
						iron.forEach(l ->{
							try {
								ars.get(p.getUniqueId()).get(j.getAndIncrement()).teleport(l.clone().add(v.clone().multiply(3.8)));
							}
							catch(IllegalArgumentException e){
								ars.get(p.getUniqueId()).get(j.getAndIncrement()).teleport(l);
							}
						});
						glass.forEach(l ->{
							try {
								ars.get(p.getUniqueId()).get(j.getAndIncrement()).teleport(l.clone().add(v.clone().multiply(3.8)));
							}
							catch(IllegalArgumentException e){
								ars.get(p.getUniqueId()).get(j.getAndIncrement()).teleport(l);
							}
						});
						beacon.forEach(l ->{
							try {
								ars.get(p.getUniqueId()).get(j.getAndIncrement()).teleport(l.clone().add(v.clone().multiply(3.8)));
							}
							catch(IllegalArgumentException e){
								ars.get(p.getUniqueId()).get(j.getAndIncrement()).teleport(l);
							}
						});
					}
				}, 1);
				ars.get(p.getUniqueId()).forEach(ar -> {
					((ArmorStand) ar).setHeadPose(conv(pel.getPitch()));
				});
			}

		}, 0,1);
		shipt.put(p.getUniqueId(), q);


	}


	private void endRiding(Player p){

		if(ars.containsKey(p.getUniqueId())) {
			ars.get(p.getUniqueId()).forEach(ar -> Holding.ale(ar).remove());
			ars.removeAll(p.getUniqueId());
		}

		if(shipt.containsKey(p.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(shipt.get(p.getUniqueId()));
			shipt.remove(p.getUniqueId());
		}
	}


	public void ULT(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();

		if(ClassData.pc.get(p.getUniqueId()) == 17 && ev.getNewSlot()==3  && ((is.getType().name().contains("PICKAXE"))) && p.isSneaking()&& Proficiency.getpro(p) >=1)
		{
			ev.setCancelled(true);
			p.setCooldown(CAREFUL, 2);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(110/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("전투 순양함")
					.ename("Battle Cruiser")
					.slot(6)
					.hm(sultcooldown)
					.skillUse(() -> {
						Holding.fly(p,300L);
						Holding.invur(p, 300L);
						battleCrus(p);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								endRiding(p);
							}
						}, 299);

					});
			bd.execute();

		}
	}

	final private void BlackHole(Player p, LivingEntity le, World tlw, Location tl) {

		tlw.spawnParticle(Particle.VIBRATION, le.getLocation(), 20, 3,3,3, new Vibration(le.getLocation(), new Destination.BlockDestination(tl), 10));
		tlw.spawnParticle(Particle.VIBRATION, le.getLocation(), 20, 3,3,3, new Vibration(le.getLocation(), new Destination.BlockDestination(tl), 20));
		tlw.spawnParticle(Particle.VIBRATION, le.getLocation(), 20, 3,3,3, new Vibration(le.getLocation(), new Destination.BlockDestination(tl), 40));
		tlw.spawnParticle(Particle.VIBRATION, le.getLocation(), 20, 3,3,3, new Vibration(le.getLocation(), new Destination.BlockDestination(tl), 80));
		tlw.spawnParticle(Particle.VIBRATION, le.getLocation(), 20, 3,3,3, new Vibration(le.getLocation(), new Destination.BlockDestination(tl), 160));
	}


	public void ULT2(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();



		if(ClassData.pc.get(p.getUniqueId()) == 17 && ev.getNewSlot()==4  && ((is.getType().name().contains("PICKAXE"))) && p.isSneaking() && Proficiency.getpro(p) >=2)
		{
			p.setCooldown(CAREFUL, 9);
			ev.setCancelled(true);

			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(50*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("블랙홀")
					.ename("BlackHole")
					.slot(7)
					.hm(sult2cooldown)
					.skillUse(() -> {
						Holding.invur(p, 120l);

						final Location tl = gettargetblock(p,2);
						final World tlw = tl.getWorld();

						ArrayList<Location> line = new ArrayList<>();
						HashSet<LivingEntity> les = new HashSet<LivingEntity>();
						for(double ang = -Math.PI/3; ang<Math.PI/3; ang += Math.PI/60) {
							for(double point = 0.1; point<30.1; point+=0.5) {
								line.add(tl.clone().add(p.getLocation().getDirection().normalize().rotateAroundY(ang).multiply(point)));
							}
						}

						line.forEach(l -> {
							tlw.spawnParticle(Particle.REVERSE_PORTAL, l, 10, 1,1,1,0);
							tlw.spawnParticle(Particle.SHRIEK, l, 1, 1,1,1,0,10);
							for(Entity e: l.getWorld().getNearbyEntities(l,4,7,4)) {
								if(e instanceof LivingEntity && e!=p && !e.hasMetadata("fake")&& !(e.hasMetadata("portal"))) {
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
									Holding.holding(p, le, 120l);
									les.add(le);
								}
							}

						});
						tlw.playSound(tl, Sound.AMBIENT_CAVE, 1f, 2);
						tlw.playSound(tl, Sound.BLOCK_END_GATEWAY_SPAWN, 1f, 0f);
						p.sendBlockChange(tl, Material.END_GATEWAY.createBlockData());

						tlw.spawnParticle(Particle.BLOCK_MARKER, tl, 150, 2,2,2,0, Material.END_GATEWAY.createBlockData());

						for(int i =1; i<20; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									p.playSound(tl, Sound.BLOCK_BEACON_DEACTIVATE, 0.1f, 2);
									p.playSound(tl, Sound.BLOCK_PORTAL_TRIGGER, 0.1f, 2);
									tlw.spawnParticle(Particle.PORTAL, tl, 100, 1,1,1,0);
									tlw.spawnParticle(Particle.REVERSE_PORTAL, tl, 100, 1,1,1,0);
									tlw.spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, tl, 200, 1,1,1,0);
									for(LivingEntity le: les) {
										BlackHole(p,le,tlw,tl);
									}
								}
							}, i*5);
						}
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								for(LivingEntity le: les) {
									le.teleport(tl);
								}
							}
						}, 100);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.playSound(tl, Sound.BLOCK_GLASS_BREAK, 1, 0);
								p.playSound(tl, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
								p.playSound(tl, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 0);
								p.playSound(tl, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 0);
								p.playSound(tl, Sound.BLOCK_END_PORTAL_FRAME_FILL, 1, 0);
								p.playSound(tl, Sound.ITEM_TRIDENT_THUNDER, 0.8f, 0);
								tlw.spawnParticle(Particle.ENCHANTMENT_TABLE, tl, 2000, 3,3,3,1);
								tlw.spawnParticle(Particle.END_ROD, tl, 500, 3,3,3,1);

								for(LivingEntity le: les) {
									atk1(16.4, p, le);
									le.teleport(tl);

								}
							}
						}, 120);
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

		if(ClassData.pc.get(p.getUniqueId()) == 17 && ((is.getType().name().contains("PICKAXE"))) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
		{
			ev.setCancelled(true);
		}

	}


	public void Equipment(PlayerItemDamageEvent e)
	{
		Player p = e.getPlayer();




		if(ClassData.pc.get(p.getUniqueId()) == 17)
		{
			if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") )
			{
				e.setCancelled(true);
			}
		}

	}


	public void CombatSuit(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity)
		{
			Player p = (Player)d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();





			if(ClassData.pc.get(p.getUniqueId()) == 17) {
				if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
					dset2(d, p, 1.2*(1+esd.CombatSuit.get(p.getUniqueId())*0.03935), le, 9);
					if(shipt.containsKey(p.getUniqueId())){
						dset0(d,p,0.1);
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

				if(ClassData.pc.get(p.getUniqueId()) == 17) {
					if(p.getInventory().getItemInMainHand().getType().name().contains("PICKAXE")) {
						dset2(d, p, 1.2*(1+esd.CombatSuit.get(p.getUniqueId())*0.03935), le, 9);
						if(shipt.containsKey(p.getUniqueId())){
							dset0(d,p,0.1);
						}
					}
				}
			}
		}
	}
}