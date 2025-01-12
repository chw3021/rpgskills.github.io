package io.github.chw3021.classes.launcher;




import io.github.chw3021.commons.SkillBuilder;
import io.github.chw3021.commons.SkillUseEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.google.common.collect.HashMultimap;
import com.google.common.util.concurrent.AtomicDouble;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
import org.bukkit.Particle.DustOptions;
import org.bukkit.Particle.TargetColor;
//import org.bukkit.Particle.Trail;
import org.bukkit.Sound;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Breeze;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Raider;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Wither;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
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

public class Launskills extends Pak {

	private HashMap<String, Long> cscooldown = new HashMap<String, Long>();
	private HashMap<String, Long> excooldown = new HashMap<String, Long>();
	private HashMap<String, Long> arcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> gacooldown = new HashMap<String, Long>();
	private HashMap<String, Long> dpcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> bultcooldown = new HashMap<String, Long>();
	private HashMap<String, Long> bult2cooldown = new HashMap<String, Long>();

	private HashMap<String, Integer> arrowtype = new HashMap<String, Integer>();

	private HashMap<UUID, Integer> absrb = new HashMap<UUID, Integer>();
	private HashMap<UUID, Location> absrbl = new HashMap<UUID, Location>();


	private HashMultimap<UUID, Firework> rockh = HashMultimap.create();
	private HashMap<UUID, Integer> rockht = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> arrftn = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> arrftnt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> glxy = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> glxyt = new HashMap<UUID, Integer>();


	private HashMap<UUID, Integer> metr = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> metrt = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> nebl = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> neblt = new HashMap<UUID, Integer>();

	Holding hold = Holding.getInstance();
	private String path = new File("").getAbsolutePath();
	private LaunSkillsData lsd;

	private static final Launskills instance = new Launskills ();
	public static Launskills getInstance()
	{
		return instance;
	}



	public void er(PluginEnableEvent ev)
	{
		LaunSkillsData l = new LaunSkillsData(LaunSkillsData.loadData(path +"/plugins/RPGskills/LaunskillsData.data"));
		lsd = l;
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
			}

		}
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Launskills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				LaunSkillsData l = new LaunSkillsData(LaunSkillsData.loadData(path +"/plugins/RPGskills/LaunskillsData.data"));
				lsd = l;
			}

		}
	}


	public void nepreventer(PlayerJoinEvent ev)
	{
		LaunSkillsData l = new LaunSkillsData(LaunSkillsData.loadData(path +"/plugins/RPGskills/LaunskillsData.data"));
		lsd = l;

	}


	final private void ArrowChange(Player p, Integer in, Integer prof) {
		if(in == 1) {

			if(p.getLocale().equalsIgnoreCase("ko_kr")) {

				switch (arrowtype.getOrDefault(p.getName(),0))
				{
					case 0:
						if(prof>=1) {
							arrowtype.put(p.getName(), 4);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("달의 화살 준비완료").color(ChatColor.DARK_AQUA).create());
							break;
						}
						else {
							arrowtype.put(p.getName(), 1);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("물의 화살 준비완료").color(ChatColor.AQUA).create());
							break;
						}
					case 1:
						arrowtype.put(p.getName(), 2);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("불의 화살 준비완료").color(ChatColor.RED).create());
						break;
					case 2:
						arrowtype.put(p.getName(), 3);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("엔더 화살 준비완료").color(ChatColor.GRAY).create());
						break;
					case 3:
						arrowtype.put(p.getName(), 0);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("번개의 화살 준비완료").color(ChatColor.YELLOW).create());
						break;
					case 4:
						arrowtype.put(p.getName(), 5);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("태양의 화살 준비완료").color(ChatColor.GOLD).create());
						break;
					case 5:
						arrowtype.put(p.getName(), 4);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("달의 화살 준비완료").color(ChatColor.DARK_AQUA).create());
						break;
				}
			}
			else {
				switch (arrowtype.getOrDefault(p.getName(),0))
				{
					case 0:
						if(prof>=1) {
							arrowtype.put(p.getName(), 4);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Lunar Arrow Loaded").color(ChatColor.DARK_AQUA).create());
							break;
						}
						else {
							arrowtype.put(p.getName(), 1);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Aqua Arrow Loaded").color(ChatColor.AQUA).create());
							break;
						}
					case 1:
						arrowtype.put(p.getName(), 2);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Flame Arrow Loaded").color(ChatColor.RED).create());
						break;
					case 2:
						arrowtype.put(p.getName(), 3);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Ender Arrow Loaded").color(ChatColor.GRAY).create());
						break;
					case 3:
						arrowtype.put(p.getName(), 0);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Lighting Arrow Loaded").color(ChatColor.YELLOW).create());
						break;
					case 4:
						arrowtype.put(p.getName(), 5);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Solar Arrow Loaded").color(ChatColor.GOLD).create());
						break;
					case 5:
						arrowtype.put(p.getName(), 4);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Lunar Arrow Loaded").color(ChatColor.DARK_AQUA).create());
						break;
				}
			}
		}
		else {

			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				switch (arrowtype.getOrDefault(p.getName(),0))
				{
					case 0:
						if(prof>=1) {
							arrowtype.put(p.getName(), 4);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("달의 화살 준비완료").color(ChatColor.DARK_AQUA).create());
							break;
						}
						else {
							arrowtype.put(p.getName(), 3);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("엔더 화살 준비완료").color(ChatColor.GRAY).create());
						}
						break;
					case 1:
						arrowtype.put(p.getName(), 0);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("번개의 화살 준비완료").color(ChatColor.YELLOW).create());
						break;
					case 2:
						arrowtype.put(p.getName(), 1);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("물의 화살 준비완료").color(ChatColor.AQUA).create());
						break;
					case 3:
						arrowtype.put(p.getName(), 2);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("불의 화살 준비완료").color(ChatColor.RED).create());
						break;
					case 4:
						arrowtype.put(p.getName(), 5);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("태양의 화살 준비완료").color(ChatColor.GOLD).create());
						break;
					case 5:
						arrowtype.put(p.getName(), 4);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("달의 화살 준비완료").color(ChatColor.DARK_AQUA).create());
						break;
				}
			}
			else {
				switch (arrowtype.getOrDefault(p.getName(),0))
				{
					case 0:
						if(prof>=1) {
							arrowtype.put(p.getName(), 4);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Lunar ArrowLoaded").color(ChatColor.DARK_AQUA).create());
							break;
						}
						else {
							arrowtype.put(p.getName(), 3);
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Ender Arrow Loaded").color(ChatColor.GRAY).create());
						}
						break;
					case 1:
						arrowtype.put(p.getName(), 0);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Lighting Arrow Loaded").color(ChatColor.YELLOW).create());
						break;
					case 2:
						arrowtype.put(p.getName(), 1);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Aqua Arrow Loaded").color(ChatColor.AQUA).create());
						break;
					case 3:
						arrowtype.put(p.getName(), 2);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Flame Arrow Loaded").color(ChatColor.RED).create());
						break;
					case 4:
						arrowtype.put(p.getName(), 5);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Solar Arrow Loaded").color(ChatColor.GOLD).create());
						break;
					case 5:
						arrowtype.put(p.getName(), 4);
						p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Lunar Arrow Loaded").color(ChatColor.DARK_AQUA).create());
						break;
				}
			}
		}
	}

	public void ArrowChange(PlayerItemHeldEvent ev)
	{
		Player p = ev.getPlayer();
		if(ClassData.pc.get(p.getUniqueId()) == 5 && lsd.ArrowChange.getOrDefault(p.getUniqueId(), 0)>=1 && (p.getInventory().getItemInMainHand().getType().name().contains("BOW") && p.isSneaking()) && !p.hasCooldown(Material.ARROW)){
			ev.setCancelled(true);
			p.setCooldown(Material.ARROW, 2);
			final int prof = Proficiency.getpro(p);
			if(prof>=1) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80 + 20*(1+prof), (int) prof, false, false));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 80 + 20*(1+prof), (int) prof, false, false));
			}
			if(prof>=2) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 80 + 20*(1+prof), (int) prof, false, false));
			}
			if(ev.getNewSlot()==3 || ev.getNewSlot() == 4) {
				return;
			}
			
			if(ev.getPreviousSlot()==0) {
				if(ev.getNewSlot()!=8) {
					ArrowChange(p,1,prof);
					return;
				}
				else {
					ArrowChange(p,0,prof);
					return;
				}
			}
			else if(ev.getPreviousSlot()==8) {
				if(ev.getNewSlot()==0) {
					ArrowChange(p,0,prof);
					return;

				}
				else {
					ArrowChange(p,1,prof);
					return;
				}
			}
			else {
				if(ev.getNewSlot()>ev.getPreviousSlot()) {
					ArrowChange(p,1,prof);
					return;

				}
				else if(ev.getNewSlot()<ev.getPreviousSlot()){
					ArrowChange(p,0,prof);
					return;
				}
			}
		}
	}


	public void ArrowChange(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity &&!d.isCancelled())
		{
			Projectile a = (Projectile) d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();
			if(a.getShooter() instanceof Player && !a.hasMetadata("fake"))
			{
				Player p = (Player) a.getShooter();
				Location el =le.getLocation();



				if(ClassData.pc.get(p.getUniqueId()) == 5)
				{
					if (le instanceof Player)
					{

						Player p1 = (Player) le;
						if(Party.hasParty(p) && Party.hasParty(p1))	{
							if(Party.getParty(p).equals(Party.getParty(p1)))
							{
								d.setCancelled(true);
								return;
							}
						}
					}


					if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
					{
						if(arrowtype.containsKey(p.getName())) {

							if(arrowtype.get(p.getName()) == 0 || arrowtype.get(p.getName()) == 5)
							{
								dset2(d, p, 1d, le, 9);
								if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType()))
								{
									d.setDamage(d.getDamage()*(1.1));
								}
								p.getWorld().spawnParticle(Particle.FLASH, el, 3, 1, 1, 1);
								p.getWorld().spawnParticle(Particle.WAX_ON, el, 3, 1, 1, 1);
								le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
							}
							if(arrowtype.get(p.getName()) == 1 || arrowtype.get(p.getName()) == 4)
							{
								dset2(d, p, 1d, le, 7);
								if(le.getType().name().contains("ENDER") || Tag.ENTITY_TYPES_FREEZE_HURTS_EXTRA_TYPES.isTagged(le.getType()) || le instanceof Raider|| le.getType() == EntityType.GHAST || le.getType() == EntityType.PIGLIN_BRUTE || le.getType() == EntityType.PLAYER)
								{
									d.setDamage(d.getDamage()*(1.1));
								}
								p.getWorld().spawnParticle(Particle.FALLING_WATER, el, 10, 1, 1, 1);
								le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 0, false, false));
								le.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 40, 0, false, false));
							}
							if(arrowtype.get(p.getName()) == 2 || arrowtype.get(p.getName()) == 5)
							{
								dset2(d, p, 1d, le, 10);
								if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType()))
								{
									d.setDamage(d.getDamage()*(1.1));
								}
								p.getWorld().spawnParticle(Particle.FLAME, el, 10, 1, 1, 1, 0);
								le.setFireTicks(40);
							}
							if(arrowtype.get(p.getName()) == 3 || arrowtype.get(p.getName()) == 4)
							{
								dset2(d, p, 1d, le, 5);
								p.getWorld().spawnParticle(Particle.END_ROD, el, 10, 1, 1, 1);
								le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 10, 0, false, false));
							}
						}
						else {
							arrowtype.put(p.getName(), 0);
							if(Proficiency.getpro(p)>=1) {
								arrowtype.put(p.getName(), 4);
							}
						}

					}

				}
			}
		}

		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity &&!d.isCancelled())
		{
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity)d.getEntity();

			if(ClassData.pc.get(p.getUniqueId()) == 5)
			{
				if (le instanceof Player)
				{

					Player p1 = (Player) le;
					if(Party.hasParty(p) && Party.hasParty(p1))	{
						if(Party.getParty(p).equals(Party.getParty(p1)))
						{
							d.setCancelled(true);
							return;
						}
					}
				}

				Location el =le.getLocation();

				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					if(arrowtype.containsKey(p.getName())) {

						if(arrowtype.get(p.getName()) == 0 || arrowtype.get(p.getName()) == 5)
						{
							dset2(d, p, 1d, le, 9);
							if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType()))
							{
								d.setDamage(d.getDamage()*(1.1));
							}
							p.getWorld().spawnParticle(Particle.FLASH, el, 3, 1, 1, 1);
							p.getWorld().spawnParticle(Particle.WAX_ON, el, 3, 1, 1, 1);
							le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
						}
						if(arrowtype.get(p.getName()) == 1 || arrowtype.get(p.getName()) == 4)
						{
							dset2(d, p, 1d, le, 7);
							if(le.getType().name().contains("ENDER") || Tag.ENTITY_TYPES_FREEZE_HURTS_EXTRA_TYPES.isTagged(le.getType()) || le instanceof Raider|| le.getType() == EntityType.GHAST || le.getType() == EntityType.PIGLIN_BRUTE || le.getType() == EntityType.PLAYER)
							{
								d.setDamage(d.getDamage()*(1.1));
							}
							p.getWorld().spawnParticle(Particle.FALLING_WATER, el, 10, 1, 1, 1);
							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 0, false, false));
							le.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 40, 0, false, false));
						}
						if(arrowtype.get(p.getName()) == 2 || arrowtype.get(p.getName()) == 5)
						{
							dset2(d, p, 1d, le, 10);
							if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType()))
							{
								d.setDamage(d.getDamage()*(1.1));
							}
							p.getWorld().spawnParticle(Particle.FLAME, el, 10, 1, 1, 1, 0);
							le.setFireTicks(40);
						}
						if(arrowtype.get(p.getName()) == 3 || arrowtype.get(p.getName()) == 4)
						{
							dset2(d, p, 1d, le, 5);
							p.getWorld().spawnParticle(Particle.END_ROD, el, 10, 1, 1, 1);
							le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 10, 0, false, false));
						}
					}
					else {
						arrowtype.put(p.getName(), 0);
						if(Proficiency.getpro(p)>=1) {
							arrowtype.put(p.getName(), 4);
						}
					}

				}

			}
		}
	}


	public void EnderArrow(ProjectileHitEvent d)
	{
		if(d.getEntity() instanceof Arrow)
		{
			Projectile a = (Projectile) d.getEntity();
			if(a.getShooter() instanceof Player)
			{
				Player p = (Player) a.getShooter();



				if(ClassData.pc.get(p.getUniqueId()) == 5 && p.getCooldown(Material.ENDER_PEARL)<=0 && !a.hasMetadata("fake")&& !a.hasMetadata("abar of"+p.getName())&& !a.hasMetadata("arrowrain "+p.getName())&& !a.hasMetadata("Explosion of"+p.getName())&& !a.hasMetadata("ChargingShot of"+p.getName())) {
					if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
					{
						if(d.getHitBlock() !=null && d.getHitEntity()==null) {
							a.remove();
							Location el = d.getHitBlock().getLocation();
							if(arrowtype.containsKey(p.getName()) && (arrowtype.get(p.getName()) == 3 || arrowtype.get(p.getName()) == 4))
							{
								p.getWorld().spawnParticle(Particle.END_ROD, el, 40, 3, 1, 3, 0);
								p.getWorld().spawnParticle(Particle.PORTAL, el, 40, 3, 1, 3, 0);
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 90, 0, false, false));
								if(el.clone().add(0, 1.869, 0).getBlock().isPassable()){
									p.teleport(el);
									p.setCooldown(Material.ENDER_PEARL, 80);
									Bukkit.getPluginManager().callEvent(new SkillUseEvent(p,4d,0,"엔더화살","EnderArrow"));
								}
							}

						}

					}
				}
			}
		}

	}


	public void Discharge(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		double sec = 3*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);



		if(ClassData.pc.get(p.getUniqueId()) == 5 && lsd.Discharge.getOrDefault(p.getUniqueId(), 0)>=1) {
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) && !p.isSneaking()&& !p.hasCooldown(CAREFUL))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					ev.setCancelled(true);
					if(!arrowtype.containsKey(p.getName())) {
						arrowtype.put(p.getName(), 0);
						if(Proficiency.getpro(p)>=1) {
							arrowtype.put(p.getName(), 4);
						}
						return;
					}

					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("방출")
							.ename("Discharge")
							.slot(2)
							.hm(dpcooldown)
							.skillUse(() -> {
								HashSet<Firework> fws = new HashSet<>();
								for(int i = 0; i <3; i++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{

											Firework fr = (Firework) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREWORK_ROCKET);
											fr.setShooter(p);
											fr.setShotAtAngle(true);
											fr.setVelocity(p.getEyeLocation().getDirection().clone().multiply(1.6));
											fr.setMetadata("discharge", new FixedMetadataValue(RMain.getInstance(), true));
											ev.setCancelled(true);
											try {
												if(arrowtype.get(p.getName()) == 0)
												{
													FireworkEffect effect = FireworkEffect.builder()
															.with(Type.STAR)
															.withColor(Color.YELLOW)
															.build();
													FireworkMeta meta = fr.getFireworkMeta();
													meta.setPower(1);
													meta.addEffect(effect);
													fr.setFireworkMeta(meta);
												}
												if(arrowtype.get(p.getName()) == 1)
												{
													FireworkEffect effect = FireworkEffect.builder()
															.with(Type.BALL_LARGE)
															.withColor(Color.AQUA)
															.build();
													FireworkMeta meta = fr.getFireworkMeta();
													meta.setPower(1);
													meta.addEffect(effect);
													fr.setFireworkMeta(meta);
												}
												if(arrowtype.get(p.getName()) == 2)
												{

													FireworkEffect effect = FireworkEffect.builder()
															.with(Type.BURST)
															.withColor(Color.RED)
															.build();
													FireworkMeta meta = fr.getFireworkMeta();
													meta.setPower(1);
													meta.addEffect(effect);
													fr.setFireworkMeta(meta);
												}
												if(arrowtype.get(p.getName()) == 3)
												{
													FireworkEffect effect = FireworkEffect.builder()
															.with(Type.CREEPER)
															.withColor(Color.BLACK)
															.build();
													FireworkMeta meta = fr.getFireworkMeta();
													meta.setPower(1);
													meta.addEffect(effect);
													fr.setFireworkMeta(meta);
												}
												if(arrowtype.get(p.getName()) == 4)
												{
													FireworkEffect effect = FireworkEffect.builder()
															.with(Type.CREEPER)
															.withColor(Color.TEAL)
															.build();
													FireworkMeta meta = fr.getFireworkMeta();
													meta.setPower(1);
													meta.addEffect(effect);
													fr.setFireworkMeta(meta);
												}
												if(arrowtype.get(p.getName()) == 5)
												{
													FireworkEffect effect = FireworkEffect.builder()
															.with(Type.BALL_LARGE)
															.withColor(Color.ORANGE)
															.build();
													FireworkMeta meta = fr.getFireworkMeta();
													meta.setPower(1);
													meta.addEffect(effect);
													fr.setFireworkMeta(meta);
												}
												fws.add(fr);
											}

											catch(NullPointerException e1)
											{
												p.sendMessage("You should Choose ArrowType First");
												return;
											}
										}
									}, i*2);

								}

								if(rockht.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(rockht.get(p.getUniqueId()));
									rockht.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											rockh.putAll(p.getUniqueId(), fws);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										rockh.removeAll(p.getUniqueId());
									}
								}, 35);
								rockht.put(p.getUniqueId(), task);

							});
					bd.execute();

				}
			}}
	}


	public void Fireworkdischarge(FireworkExplodeEvent d)
	{
		Firework fw = (Firework) d.getEntity();
		if (fw.hasMetadata("discharge") && fw.getShooter() instanceof Player) {
			Player p = (Player) fw.getShooter();
			final Location fl = fw.getLocation();

			absorbing(p,fl);

			for (Entity e : fw.getNearbyEntities(5, 5, 5))
			{
				if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
				{
					LivingEntity le = (LivingEntity)e;
					{
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
						atk0(0.7, lsd.Discharge.get(p.getUniqueId())*0.76, p, le);
					}
				}
			}
			if(Proficiency.getpro(p)>=2) {

				for(int i =0; i<3; i++) {
					Random ran = new Random();
					double ri1 = ran.nextDouble()*2 * (ran.nextBoolean() ? 1:-1);
					double ri2 = ran.nextDouble()*2 * (ran.nextBoolean() ? 1:-1);
					Snowball sn = p.getWorld().spawn(fl.clone().add(ri1, 3, ri2), Snowball.class);
					sn.setGravity(true);
					sn.setGlowing(true);
					sn.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
					sn.setMetadata("comet", new FixedMetadataValue(RMain.getInstance(), true));
					sn.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
					sn.setShooter(p);

					if(arrowtype.get(p.getName()) == 4)
					{
						sn.setItem(new ItemStack(Material.END_STONE));
					}
					if(arrowtype.get(p.getName()) == 5)
					{
						sn.setItem(new ItemStack(Material.SHROOMLIGHT));
					}
				}
			}
		}
	}


	public void Fireworkdischarge(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Firework && d.getEntity() instanceof LivingEntity)
		{
			Firework fw = (Firework) d.getDamager();
			if (fw.hasMetadata("discharge")) {
				d.setCancelled(true);
			}
		}
	}


	public void RocketHit(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();

		if(ClassData.pc.get(p.getUniqueId()) == 5&&rockh.containsKey(p.getUniqueId())) {
			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) && !p.isSneaking())
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					ev.setCancelled(true);

					if(rockht.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(rockht.get(p.getUniqueId()));
						rockht.remove(p.getUniqueId());
					}

					rockh.get(p.getUniqueId()).forEach(fr -> {

						Arrow ar = p.launchProjectile(Arrow.class);
						ar.setDamage(0);
						ar.setVelocity(fr.getLocation().toVector().subtract(ar.getLocation().toVector()).multiply(30));
						ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
						ar.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								ar.remove();
							}
						}, 3);
						fr.detonate();
					});

					rockh.removeAll(p.getUniqueId());


				}
			}
		}
	}


	public void Comet(ProjectileHitEvent d)
	{
		final Projectile pr = d.getEntity();
		if(d.getEntity().hasMetadata("comet")) {
			Player p = (Player) d.getEntity().getShooter();

			absorbing(p,pr.getLocation().clone());

			if(arrowtype.get(p.getName()) == 4)
			{
				pr.getWorld().spawnParticle(Particle.END_ROD, pr.getLocation(), 50, 2,2,2);
				pr.getWorld().spawnParticle(Particle.ITEM_SNOWBALL, pr.getLocation(), 50, 2,2,2);
				pr.getWorld().spawnParticle(Particle.WARPED_SPORE, pr.getLocation(), 50, 2,2,2);
			}
			if(arrowtype.get(p.getName()) == 5)
			{
				pr.getWorld().spawnParticle(Particle.FLAME, pr.getLocation(), 50, 2,2,2);
				pr.getWorld().spawnParticle(Particle.DRIPPING_LAVA, pr.getLocation(), 50, 2,2,2);
				pr.getWorld().spawnParticle(Particle.FLASH, pr.getLocation(), 50, 2,2,2);
				pr.getWorld().spawnParticle(Particle.CRIMSON_SPORE, pr.getLocation(), 50, 2,2,2);
			}
			pr.getWorld().playSound(pr.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_HIT, 1, 0);
			pr.getWorld().playSound(pr.getLocation(), Sound.BLOCK_BASALT_BREAK, 1, 2);
			for (Entity e : pr.getLocation().getWorld().getNearbyEntities(pr.getLocation(), 3, 3, 3))
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
					atk0(0.36, lsd.Discharge.get(p.getUniqueId())*0.46, p, le);
				}
			}
		}
	}

	public void swcancle(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();



		if(ClassData.pc.get(p.getUniqueId()) == 5) {
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
			{
				ev.setCancelled(true);
			}
		}
	}



	public void ArrowRain(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();
		double sec =9*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);



		if(ClassData.pc.get(p.getUniqueId()) == 5 && lsd.ArrowRain.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(((p.isSneaking())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					ev.setCancelled(true);

					if(!arrowtype.containsKey(p.getName())) {
						arrowtype.put(p.getName(), 0);
						if(Proficiency.getpro(p)>=1) {
							arrowtype.put(p.getName(), 4);
						}
						return;
					}
					final Location l = getTargetEntity(p,30);

					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("화살세례")
							.ename("ArrowRain")
							.slot(1)
							.hm(arcooldown)
							.skillUse(() -> {

								if(arrftnt.containsKey(p.getUniqueId())) {
									Bukkit.getScheduler().cancelTask(arrftnt.get(p.getUniqueId()));
									arrftnt.remove(p.getUniqueId());
								}

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										if(Proficiency.getpro(p)>=1) {
											arrftn.putIfAbsent(p.getUniqueId(), 0);
										}
									}
								}, 3);

								int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										arrftn.remove(p.getUniqueId());
									}
								}, 40);
								arrftnt.put(p.getUniqueId(), task);


								Location el = new Location(p.getWorld(), l.getX(), l.getY()+7, l.getZ());
								p.getWorld().spawnParticle(Particle.CLOUD, el, 100,5,1,5);

								if(arrowtype.get(p.getName()) == 0 || arrowtype.get(p.getName()) == 5)
								{
									p.getWorld().spawnParticle(Particle.WAX_ON, el.clone(), 300, 3, 2, 3);
									p.getWorld().spawnParticle(Particle.FLASH, el.clone(), 300, 3, 2, 3);
								}
								if(arrowtype.get(p.getName()) == 1 || arrowtype.get(p.getName()) == 4)
								{
									p.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, el.clone(), 300, 3, 2, 3);
									p.getWorld().spawnParticle(Particle.DRIPPING_WATER, el.clone(), 300, 3, 2, 3);
								}
								if(arrowtype.get(p.getName()) == 2 || arrowtype.get(p.getName()) == 5)
								{
									p.getWorld().spawnParticle(Particle.FLAME, el.clone(), 300, 3, 2, 3);
									p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, el.clone(), 300, 3, 2, 3);
								}
								if(arrowtype.get(p.getName()) == 3 || arrowtype.get(p.getName()) == 4)
								{
									p.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, el.clone(), 300, 3, 2, 3);
									p.getWorld().spawnParticle(Particle.END_ROD, el.clone(), 300, 3, 2, 3);
								}
								for(int i = 0; i <10; i++) {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											absorbing(p,l);
											Arrow ar =p.getWorld().spawnArrow(el, l.toVector().subtract(el.toVector()), 0.5f, 60);
											ar.setShooter(p);
											ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
											ar.setMetadata("rob"+ p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
											ar.setMetadata("arrowrain "+ p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
											ar.setDamage(0);
											ar.setPickupStatus(PickupStatus.DISALLOWED);
									        new BukkitRunnable() {
									            @Override
									            public void run() {
									            	ar.remove();
									            }
									        }.runTaskLater(RMain.getInstance(), 70); // 매 틱마다 실행
											for (Entity e : l.getWorld().getNearbyEntities(l, 5, 10, 5))
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
													atk0(0.08, lsd.ArrowRain.get(p.getUniqueId())*0.08, p, le);
													Holding.holding(p, le, (long) 40);
												}
											}
										}
									}, i*5);

								}
								p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 2f);
								p.playSound(p.getLocation(), Sound.WEATHER_RAIN_ABOVE, 1.0f, 2f);
							});
					bd.execute();
				}
			}
		}
	}



	public void ArrowFountain(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 5 && arrftn.containsKey(p.getUniqueId())) {
			if(((p.isSneaking())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					ev.setCancelled(true);

					final Location l = getTargetEntity(p,30);

					if(arrftnt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(arrftnt.get(p.getUniqueId()));
						arrftnt.remove(p.getUniqueId());
					}
					arrftn.remove(p.getUniqueId());



					if(glxyt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(glxyt.get(p.getUniqueId()));
						glxyt.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								glxy.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 3);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							glxy.remove(p.getUniqueId());
						}
					}, 40);
					glxyt.put(p.getUniqueId(), task);



					Location el = new Location(p.getWorld(), l.getX(), l.getY()+6, l.getZ());

					p.getWorld().spawnParticle(Particle.CRIMSON_SPORE, l, 100,1,1,1);
					p.playSound(p.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1.0f, 2f);
					p.playSound(p.getLocation(), Sound.BLOCK_POINTED_DRIPSTONE_PLACE, 1.0f, 2f);
					if(arrowtype.getOrDefault(p.getName(), 0)==4) {
						p.getWorld().spawnParticle(Particle.WARPED_SPORE, l, 100,1,1,1);
						p.getWorld().spawnParticle(Particle.DRIPPING_WATER, l, 100,1,1,1);
						p.getWorld().spawnParticle(Particle.END_ROD, l, 100,1,1,1);
					};
					if(arrowtype.getOrDefault(p.getName(), 0)==5) {
						p.getWorld().spawnParticle(Particle.SPORE_BLOSSOM_AIR, l, 100,1,1,1);
						p.getWorld().spawnParticle(Particle.DRIPPING_LAVA, l, 100,1,1,1);
						p.getWorld().spawnParticle(Particle.FLASH, l, 100,1,1,1);
					}

					for(int i = 0; i <5; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								absorbing(p,l);
								for(int i = 0; i <25; i++) {
									Arrow ar =p.getWorld().spawnArrow(l, el.toVector().subtract(l.toVector()), 0.5f, 60);
									ar.setShooter(p);
									ar.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), p.getName()));
									ar.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
									ar.setMetadata("arrowfountain"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									ar.setDamage(0);
									ar.setPickupStatus(PickupStatus.DISALLOWED);

							        new BukkitRunnable() {
							            @Override
							            public void run() {
							            	ar.remove();
							            }
							        }.runTaskLater(RMain.getInstance(), 70); // 매 틱마다 실행
								}
								for (Entity e : l.getWorld().getNearbyEntities(l, 5, 10, 5))
								{
									if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
									{
										LivingEntity le = (LivingEntity)e;
										{
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

											atk0(0.28, lsd.ArrowRain.get(p.getUniqueId())*0.34, p, le);
											Holding.holding(p, le, (long) 30);
												/*
												if(le instanceof EnderDragon) {
								                    Arrow firstarrow = p.launchProjectile(Arrow.class);
								                    firstarrow.setDamage(0);
								                    firstarrow.remove();
													Arrow enar = (Arrow) p.getWorld().spawn(le.getLocation().add(0, 5.163, 0), Arrow.class, a->{
														a.setShooter(p);
														a.setCritical(false);
														a.setSilent(true);
														a.setPickupStatus(PickupStatus.DISALLOWED);
														a.setVelocity(le.getLocation().clone().add(0, -1, 0).toVector().subtract(le.getLocation().toVector()).normalize().multiply(2.6));
													});
													enar.setDamage(lsd.ArrowRain.get(p.getUniqueId())*0.24 + player_damage.get(p.getName())*0.34);
												}
												le.damage(0, p);
												le.damage(lsd.ArrowRain.get(p.getUniqueId())*0.24 + player_damage.get(p.getName())*0.34, p);
									             */
										}
									}
								}
							}
						}, i*10);

					}



				}
			}
		}
	}



	public void Galaxy(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();

		if(ClassData.pc.get(p.getUniqueId()) == 5 && glxy.containsKey(p.getUniqueId())) {
			if(((p.isSneaking())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					ev.setCancelled(true);


					final Location tl = getTargetEntity(p,30).clone();
					if(glxyt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(glxyt.get(p.getUniqueId()));
						glxyt.remove(p.getUniqueId());
					}
					glxy.remove(p.getUniqueId());



					AtomicInteger k = new AtomicInteger();
					AtomicDouble j = new AtomicDouble(0);


					for(int i = 0; i <3; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								absorbing(p,tl);
								ArrayList<Location> ring = new ArrayList<Location>();

								k.incrementAndGet();
								for(double an = 0; an<Math.PI*3; an +=Math.PI/90) {
									ring.add(tl.clone().add(tl.getDirection().normalize().rotateAroundY(an+ k.get()).multiply(j.getAndAdd(0.01))));
								}

								p.playSound(p.getLocation(), Sound.ENTITY_GLOW_SQUID_AMBIENT, 1.0f, 2f);
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 2f);
								ring.forEach(l -> {
									p.getWorld().spawnParticle(Particle.END_ROD, l.clone().add(0, k.get() ,0), 2,0.35,0.35,0.35,0);
									if(arrowtype.getOrDefault(p.getName(), 0)==4) {
										p.getWorld().spawnParticle(Particle.GLOW, l.clone().add(0, k.get(),0), 3,0.2,0.2,0.2,0.12);
									};
									if(arrowtype.getOrDefault(p.getName(), 0)==5) {
										p.getWorld().spawnParticle(Particle.WAX_ON, l.clone().add(0, k.get() ,0), 1,0.2,0.2,0.2,0.12);
										p.getWorld().spawnParticle(Particle.FLAME, l.clone().add(0, k.get() ,0), 2,0.2,0.2,0.2,0.12);
									}

								});
								for (Entity e : tl.getWorld().getNearbyEntities(tl, 5, 10, 5))
								{
									if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
									{
										LivingEntity le = (LivingEntity)e;
										{
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
											atk0(0.5, lsd.ArrowRain.get(p.getUniqueId())*0.5, p, le);
											Holding.holding(p, le, (long) 30);
											le.teleport(tl);
										}
									}
								}
							}
						}, i*20);

					}
				}
			}
		}
	}

	final private void GiantArrow(Location el, Player p) {

		HashSet<Location> els = new HashSet<>();

		for(int i = 1; i<=9; i ++) {
			els.add(el.clone().add(0,i,0));
		}
		els.forEach(l -> {
//			p.getWorld().spawnParticle(Particle.TRAIL, l.clone(), 100, 3,1,3,1, new Trail(el, Color.FUCHSIA,20));
//			p.getWorld().spawnParticle(Particle.TRAIL, l.clone(), 100, 3,1,3,1, new Trail(el, Color.TEAL,20));
			p.getWorld().spawnParticle(Particle.TRAIL, l.clone(), 100, 3,1,3,1, new TargetColor(el, Color.FUCHSIA));
			p.getWorld().spawnParticle(Particle.TRAIL, l.clone(), 100, 3,1,3,1, new TargetColor(el, Color.TEAL));
			p.getWorld().spawnParticle(Particle.BLOCK_MARKER, l, 50, 0.1, 1, 0.1,1,Material.OAK_LOG.createBlockData());
		});
		p.getWorld().spawnParticle(Particle.BLOCK_MARKER, el.clone().add(0, 10, 0), 600, 0.6, 1, 0.6,1,Material.WHITE_WOOL.createBlockData());
		if(arrowtype.get(p.getName()) == 0 || arrowtype.get(p.getName()) == 5)
		{
			p.getWorld().spawnParticle(Particle.BLOCK_MARKER, el.clone(), 200, 0.43, 0.6, 0.43,1,Material.SHROOMLIGHT.createBlockData());
		}
		if(arrowtype.get(p.getName()) == 1 || arrowtype.get(p.getName()) == 4)
		{
			p.getWorld().spawnParticle(Particle.BLOCK_MARKER, el.clone(), 200, 0.43, 0.6, 0.43,1,Material.BLUE_GLAZED_TERRACOTTA.createBlockData());
		}
		if(arrowtype.get(p.getName()) == 2 || arrowtype.get(p.getName()) == 5)
		{
			p.getWorld().spawnParticle(Particle.BLOCK_MARKER, el.clone(), 200, 0.43, 0.6, 0.43,1,Material.RED_GLAZED_TERRACOTTA.createBlockData());
		}
		if(arrowtype.get(p.getName()) == 3 || arrowtype.get(p.getName()) == 4)
		{
			p.getWorld().spawnParticle(Particle.BLOCK_MARKER, el.clone(), 200, 0.43, 0.6, 0.43,1,Material.PURPLE_GLAZED_TERRACOTTA.createBlockData());
		}

	}



	public void GiantArrow(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();
		double sec = 12*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);



		if(ClassData.pc.get(p.getUniqueId()) == 5 && lsd.GiantArrow.getOrDefault(p.getUniqueId(), 0)>=1) {
			if(!p.isSneaking())
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{

					ev.setCancelled(true);

					if(!arrowtype.containsKey(p.getName())) {
						arrowtype.put(p.getName(), 0);
						if(Proficiency.getpro(p)>=1) {
							arrowtype.put(p.getName(), 4);
						}
						return;
					}
					final Location l = getTargetEntity(p,30);
					SkillBuilder bd = new SkillBuilder()
							.player(p)
							.cooldown(sec)
							.kname("거대화살")
							.ename("GiantArrow")
							.slot(3)
							.hm(gacooldown)
							.skillUse(() -> {
								p.playSound(l, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0f);
								p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, l, 2000, 0.1, 3, 0.1,0.5);

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run() {
										GiantArrow(l,p);
										absorbing(p,l);
										p.playSound(l, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0f);
										for (Entity e : p.getWorld().getNearbyEntities(l, 5,15, 5))
										{
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
												atk0(1.3, lsd.GiantArrow.get(p.getUniqueId())*1.56, p, le);
												Holding.holding(p, le, (long) 85);
											}
										}

										if(metrt.containsKey(p.getUniqueId())) {
											Bukkit.getScheduler().cancelTask(metrt.get(p.getUniqueId()));
											metrt.remove(p.getUniqueId());
										}

										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run() {
												if(Proficiency.getpro(p)>=1) {
													metr.putIfAbsent(p.getUniqueId(), 0);
												}
											}
										}, 3);

										int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
											@Override
											public void run() {
												metr.remove(p.getUniqueId());
											}
										}, 40);
										metrt.put(p.getUniqueId(), task);

									}
								}, 10);

							});
					bd.execute();

				}
			}
		}
	}



	@SuppressWarnings("deprecation")
	public void Meteor(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();


		if(ClassData.pc.get(p.getUniqueId()) == 5&& metr.containsKey(p.getUniqueId())) {
			if((!(p.isSneaking()) && !(p.isOnGround())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					ev.setCancelled(true);

					final Location tl = getTargetEntity(p,30);

					if(metrt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(metrt.get(p.getUniqueId()));
						metrt.remove(p.getUniqueId());
					}
					metr.remove(p.getUniqueId());



					if(neblt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(neblt.get(p.getUniqueId()));
						neblt.remove(p.getUniqueId());
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							if(Proficiency.getpro(p)>=2) {
								nebl.putIfAbsent(p.getUniqueId(), 0);
							}
						}
					}, 3);

					int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {
							nebl.remove(p.getUniqueId());
						}
					}, 40);
					neblt.put(p.getUniqueId(), task);


					for(int i = 0; i <6; i++) {
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{

								Random ran = new Random();
								int ri =ran.nextInt(3);
								double ri1 = ran.nextDouble()*2 * (ran.nextBoolean() ? 1:-1);
								double ri2 = ran.nextDouble()*2 * (ran.nextBoolean() ? 1:-1);
								Material type = Material.ANDESITE;

								if(arrowtype.getOrDefault(p.getName(), 0)==4) {
									if(ri == 0) {
										type = Material.SEA_LANTERN;
									}
									else if(ri == 1) {
										type = Material.END_STONE_BRICKS;
									}
									else {
										type = Material.PRISMARINE;
									}
								}
								else if(arrowtype.getOrDefault(p.getName(), 0)==5) {
									if(ri == 0) {
										type = Material.SHROOMLIGHT;
									}
									else if(ri == 1) {
										type = Material.GLOWSTONE;
									}
									else  {
										type = Material.NETHER_GOLD_ORE;
									}
								}

								FallingBlock fallingb = p.getWorld().spawnFallingBlock(tl.clone().add(ri1, 6, ri2), type.createBlockData());
								fallingb.setInvulnerable(true);
								fallingb.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
								fallingb.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(),p.getName()));
								fallingb.setMetadata("meteor", new FixedMetadataValue(RMain.getInstance(),p.getName()));
								fallingb.setVisualFire(true);
								fallingb.setGlowing(true);
								fallingb.setDropItem(true);
								fallingb.setHurtEntities(true);

							}
						}, i*3);

					}
				}
			}
		}
	}

	final private void meteor(Location tl, Player p) {
		tl.getWorld().spawnParticle(Particle.EXPLOSION, tl, 1);
		absorbing(p,tl);

		if(arrowtype.getOrDefault(p.getName(), 0)==4) {
			tl.getWorld().spawnParticle(Particle.BLOCK, tl, 100,5,5,5, Material.END_STONE.createBlockData());
			tl.getWorld().spawnParticle(Particle.BLOCK, tl, 100,5,5,5, Material.PRISMARINE.createBlockData());
			tl.getWorld().spawnParticle(Particle.SPLASH, tl, 100,5,5,5);
			tl.getWorld().spawnParticle(Particle.END_ROD, tl, 100,5,5,5);
			tl.getWorld().spawnParticle(Particle.GLOW, tl, 50,5,5,5);
		}
		else if(arrowtype.getOrDefault(p.getName(), 0)==5) {
			tl.getWorld().spawnParticle(Particle.BLOCK, tl, 100,5,5,5, Material.SHROOMLIGHT.createBlockData());
			tl.getWorld().spawnParticle(Particle.BLOCK, tl, 100,5,5,5, Material.GLOWSTONE.createBlockData());
			tl.getWorld().spawnParticle(Particle.FLAME, tl, 100,5,5,5);
			tl.getWorld().spawnParticle(Particle.WAX_ON, tl, 50,5,5,5);
			tl.getWorld().spawnParticle(Particle.FLASH, tl, 10,5,5,5);
		}
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
				atk0(0.34, lsd.GiantArrow.get(p.getUniqueId())*0.4, p, le);
				Holding.holding(p, le, 30l);
			}

		}
		p.playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0);
	}



	public void Meteor(EntityDropItemEvent ev)
	{
		if(ev.getEntity() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getEntity();
			if(fallingb.hasMetadata("meteor")){
				ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("meteor").get(0).asString());
				Location tl = fallingb.getLocation();

				meteor(tl,p);

				fallingb.remove();
			}
		}
	}



	public void Meteor(EntityDamageByEntityEvent ev)
	{
		if(ev.getDamager() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getDamager();
			if(fallingb.hasMetadata("meteor")){
				ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("meteor").get(0).asString());
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, tl, 2,1,1,1);
				meteor(tl,p);
				fallingb.remove();
			}
		}
	}


	public void Meteor(EntityChangeBlockEvent ev)
	{
		if(ev.getEntity() instanceof FallingBlock){
			FallingBlock fallingb = (FallingBlock) ev.getEntity();
			if(fallingb.hasMetadata("meteor")){
				ev.setCancelled(true);
				Player p = Bukkit.getPlayer(fallingb.getMetadata("meteor").get(0).asString());
				Location tl = fallingb.getLocation();
				tl.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, tl, 2,1,1,1);
				meteor(tl,p);
				fallingb.remove();
			}
		}
	}


	final private void neb(Location el, Player p) {
		ArrayList<Location> line = new ArrayList<>();
		final Vector l1 = el.clone().getDirection().normalize().rotateAroundY(Math.PI/2);
		final Vector l2 = el.clone().getDirection().normalize().rotateAroundY(Math.PI);
		final Vector l3 = el.clone().getDirection().normalize().rotateAroundY(-Math.PI/2);
		final Vector l4 = el.clone().getDirection().normalize();

		AtomicDouble k = new AtomicDouble();

		for(double an = 0; an<Math.PI*3; an +=Math.PI/45) {
			line.add(el.clone().add(l1.clone().multiply(k.addAndGet(0.1)).rotateAroundY(an).rotateAroundNonUnitAxis(l1.clone(), an)));
			line.add(el.clone().add(l2.clone().multiply(k.get()).rotateAroundY(an).rotateAroundNonUnitAxis(l2.clone(), an)));
			line.add(el.clone().add(l3.clone().multiply(k.get()).rotateAroundY(an).rotateAroundNonUnitAxis(l3.clone(), an)));
			line.add(el.clone().add(l4.clone().multiply(k.get()).rotateAroundY(an).rotateAroundNonUnitAxis(l4.clone(), an)));
		}
		Collections.reverse(line);

		final World w = el.getWorld();

		line.forEach(l -> {
			if(arrowtype.getOrDefault(p.getName(),0) == 4)
			{
				w.spawnParticle(Particle.GLOW_SQUID_INK, l, 1, 0.1,0.1, 0.1,0.1);
				w.spawnParticle(Particle.DUST, l, 1, 0.1,0.1, 0.1,0, new DustOptions(Color.AQUA, 2));
				w.spawnParticle(Particle.DUST, l, 1, 0.1, 0.1, 0.1,0, new DustOptions(Color.TEAL, 2));
			}
			else if(arrowtype.getOrDefault(p.getName(),0) == 5)
			{
				w.spawnParticle(Particle.WAX_ON, l, 1, 0.1, 0.1, 0.1,0.1);
				w.spawnParticle(Particle.FLAME, l, 1, 0.1,0.1, 0.01,0.1);
				w.spawnParticle(Particle.DUST, l, 1,0.1, 0.1, 0.1,0, new DustOptions(Color.RED, 2));
				w.spawnParticle(Particle.DUST, l, 1, 0.1, 0.1, 0.1,0, new DustOptions(Color.YELLOW, 2));
			}

		});
	}


	@SuppressWarnings("deprecation")
	public void Nebula(PlayerSwapHandItemsEvent ev)
	{
		Player p = ev.getPlayer();


		if(ClassData.pc.get(p.getUniqueId()) == 5 && nebl.containsKey(p.getUniqueId())) {
			if((!(p.isSneaking()) && !(p.isOnGround())))
			{
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					ev.setCancelled(true);

					final Location el = getTargetEntity(p,30).clone().add(0, 3, 0);

					if(neblt.containsKey(p.getUniqueId())) {
						Bukkit.getScheduler().cancelTask(neblt.get(p.getUniqueId()));
						neblt.remove(p.getUniqueId());
					}
					nebl.remove(p.getUniqueId());


					p.playSound(el, Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 1.0f, 2f);
					p.playSound(el, Sound.ENTITY_FOX_TELEPORT, 1.0f, 0f);

					for (Entity e : p.getWorld().getNearbyEntities(el, 6,6, 6))
					{
						if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
						{
							LivingEntity le = (LivingEntity)e;
							{
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
								Holding.holding(p, le, (long) 45);
								le.teleport(el);
							}
						}
					}

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run() {

							absorbing(p,el);
							neb(el.clone(),p);
							el.getWorld().spawnParticle(Particle.CLOUD, el.clone(), 300, 5, 5, 5,0);
							p.playSound(el, Sound.ENTITY_GLOW_SQUID_AMBIENT, 1.0f, 2f);
							p.playSound(el, Sound.BLOCK_END_GATEWAY_SPAWN, 0.3f, 2f);
							p.playSound(el, Sound.ITEM_GLOW_INK_SAC_USE, 1.0f, 0f);
							for (Entity e : el.getWorld().getNearbyEntities(el, 6,6, 6))
							{
								if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
								{
									LivingEntity le = (LivingEntity)e;
									{
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
										atk0(1.5, lsd.GiantArrow.get(p.getUniqueId())*2.2, p, le);
										Holding.holding(p, le, 30l);
									}
								}
							}
						}
					}, 25);


				}
			}
		}
	}


	public void Explosion(EntityShootBowEvent a)
	{

		if(a.getEntity() instanceof Player)
		{
			Player p = (Player) a.getEntity();

			double sec =4*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);


			if(ClassData.pc.get(p.getUniqueId()) == 5 && lsd.Explosion.getOrDefault(p.getUniqueId(), 0)>=1 && p.isSneaking()) {
				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					if(a.getProjectile().getType() == EntityType.ARROW)
					{
						if(!arrowtype.containsKey(p.getName())) {
							arrowtype.put(p.getName(), 0);
							if(Proficiency.getpro(p)>=1) {
								arrowtype.put(p.getName(), 4);
							}
							return;
						}
						SkillBuilder bd = new SkillBuilder()
								.player(p)
								.cooldown(sec)
								.kname("폭발")
								.ename("Explosion")
								.slot(4)
								.hm(excooldown)
								.skillUse(() -> {
									a.getProjectile().setMetadata("Explosion of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
									if(Proficiency.getpro(p)>=2) {
										Arrow far = (Arrow) a.getProjectile();
										HashSet<Vector> line = new HashSet<Vector>();
										for(double dou = -Math.PI/6; dou<= Math.PI/6; dou += Math.PI/6) {
											Vector l = far.getVelocity().clone();
											l.rotateAroundY(dou);
											line.add(l);
										}
										line.forEach(l -> {
											Arrow ar = (Arrow) p.launchProjectile(Arrow.class);
											ar.setShooter(p);
											ar.setDamage(far.getDamage());
											ar.setVelocity(l);
											ar.setMetadata("Explosion of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
											ar.setPickupStatus(PickupStatus.DISALLOWED);
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
												@Override
												public void run()
												{
													ar.remove();
												}
											}, 20);
										});
									}
								});
						bd.execute();

					}
				}
			}
		}
	}


	public void Explosion(ProjectileHitEvent d)
	{
		if(d.getEntity() instanceof Arrow)
		{
			Projectile a = (Projectile) d.getEntity();
			if(a.getShooter() instanceof Player)
			{
				Player p = (Player) a.getShooter();



				if(ClassData.pc.get(p.getUniqueId()) == 5 && a.hasMetadata("Explosion of"+p.getName())) {
					if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
					{

						Location el = a.getLocation();
						absorbing(p,el);

						if(arrowtype.get(p.getName()) == 0 || arrowtype.get(p.getName()) == 5)
						{
							el.getWorld().spawnParticle(Particle.FLASH, el, 30, 3, 1, 3);
						}
						if(arrowtype.get(p.getName()) == 1 || arrowtype.get(p.getName()) == 4)
						{
							el.getWorld().spawnParticle(Particle.BUBBLE_POP, el, 40, 3, 1, 3);
							el.getWorld().spawnParticle(Particle.WHITE_ASH, el, 40, 3, 1, 3);
							el.getWorld().spawnParticle(Particle.FALLING_WATER, el, 40, 3, 1, 3);
							el.getWorld().spawnParticle(Particle.DRIPPING_WATER, el, 40, 3, 1, 3);
							p.playSound(el, Sound.ENTITY_PLAYER_SPLASH, 1, 1);
						}
						if(arrowtype.get(p.getName()) == 2 || arrowtype.get(p.getName()) == 5)
						{
							p.getWorld().spawnParticle(Particle.FLAME, el, 40, 3, 1, 3, 0);
							p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, el, 40, 3, 1, 3, 0);
							p.getWorld().spawnParticle(Particle.LAVA, el, 40, 3, 1, 3, 0);
						}
						if(arrowtype.get(p.getName()) == 3 || arrowtype.get(p.getName()) == 4)
						{
							p.getWorld().spawnParticle(Particle.EFFECT, el, 40, 3, 1, 3, 0);
							p.getWorld().spawnParticle(Particle.END_ROD, el, 40, 3, 1, 3, 0);
							p.getWorld().spawnParticle(Particle.PORTAL, el, 40, 3, 1, 3, 0);
						}
						p.playSound(el, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
						p.getWorld().spawnParticle(Particle.EXPLOSION, el, 1, 1, 1, 1);

						for (Entity a1 : p.getWorld().getNearbyEntities(el, 4, 4, 4))
						{
							if ((!(a1 == p))&& a1 instanceof LivingEntity&& !(a1.hasMetadata("fake"))&& !(a1.hasMetadata("portal")))
							{
								LivingEntity le = (LivingEntity)a1;
								{
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
									atk0(0.7, lsd.Explosion.get(p.getUniqueId())*0.76, p, le);
									Holding.holding(p, le, 35l);
								}
							}
						}


						if(Proficiency.getpro(p)>=1) {
							p.playSound(el, Sound.ENTITY_HORSE_BREATHE, 1, 2);
							p.playSound(el, Sound.ENTITY_FOX_SNIFF, 1, 2);
							el.getWorld().spawnParticle(Particle.ASH, el, 200, 1, 2, 1,0.1);
							el.getWorld().spawnParticle(Particle.WHITE_ASH, el, 200, 1, 2, 1,0.1);
							el.getWorld().spawnParticle(Particle.SPORE_BLOSSOM_AIR, el, 200, 1, 2, 1,0.1);
							el.getWorld().spawnParticle(Particle.CLOUD, el, 200, 1, 2, 1,0.1);
							absorbing(p,el);


							for (Entity a1 : el.getWorld().getNearbyEntities(el, 4, 4, 4))
							{
								if ((!(a1 == p))&& a1 instanceof LivingEntity&& !(a1.hasMetadata("fake"))&& !(a1.hasMetadata("portal")))
								{
									LivingEntity le = (LivingEntity)a1;
									{
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
										atk0(0.87, lsd.Explosion.get(p.getUniqueId())*0.9, p, le);
										le.teleport(el);
									}
								}



							}
						}

					}
				}
			}
		}

	}

	public void ChargingShot(PlayerInteractEvent ev)
	{
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		double sec = 10*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);

		if(ClassData.pc.get(p.getUniqueId()) == 5 && lsd.ChargingShot.getOrDefault(p.getUniqueId(), 0)>=1) {

			if((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) && p.isSneaking()&& !p.hasCooldown(CAREFUL))
			{

				if(p.getInventory().getItemInMainHand().getType() == Material.BOW)
				{
					if(cscooldown.containsKey(p.getName())) // if cooldown has players name in it (on first trow cooldown is empty)
					{
						double timer = (cscooldown.get(p.getName())/1000d + sec) - System.currentTimeMillis()/1000d; // geting time in seconds
						if(!(timer < 0)) // if timer is still more then 0 or 0
						{
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("응집 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
							}
							else {
								p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use ChargingShot").create());
							}
						}
						else // if timer is done
						{
							cscooldown.remove(p.getName()); // removing player from HashMap
							AbstractArrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 5, 0, Arrow.class);
							ar.setShooter(p);
							ar.setCritical(true);
							ar.setDamage(0);
							p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 0.1f, 1.6f);
							p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 0.1f, 1.6f);
							p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.1f, 1.6f);
							ar.setPickupStatus(PickupStatus.DISALLOWED);
							ar.setMetadata("ChargingShot of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
							if(Proficiency.getpro(p) >= 1) {
								ar.setPierceLevel(5);
							}
							Bukkit.getPluginManager().callEvent(new ProjectileLaunchEvent(ar));

							for(int i = 0; i <10; i++) {
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										ar.getWorld().spawnParticle(Particle.END_ROD, ar.getLocation(), 5,0.1,0.1,0.1,0.05);
										ar.getWorld().spawnParticle(Particle.FLAME, ar.getLocation(), 5,0.1,0.1,0.1,0.05);
										ar.getWorld().spawnParticle(Particle.SPLASH, ar.getLocation(), 5,0.1,0.1,0.1,0.05);
										ar.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, ar.getLocation(), 5,0.1,0.1,0.1,0.05);
									}
								}, i*2);

							}

						}
					}
					else // if cooldown doesn't have players name in it
					{
						AbstractArrow ar = p.getWorld().spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), 5, 0, Arrow.class);
						ar.setShooter(p);
						ar.setCritical(true);
						ar.setDamage(0);
						p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 0.1f, 1.6f);
						p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 0.1f, 1.6f);
						p.playSound(p.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.1f, 1.6f);
						ar.setPickupStatus(PickupStatus.DISALLOWED);
						ar.setMetadata("ChargingShot of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						if(Proficiency.getpro(p) >= 1) {
							ar.setPierceLevel(5);
						}
						Bukkit.getPluginManager().callEvent(new ProjectileLaunchEvent(ar));

						for(int i = 0; i <10; i++) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
								@Override
								public void run()
								{
									ar.getWorld().spawnParticle(Particle.END_ROD, ar.getLocation(), 5,0.1,0.1,0.1,0.05);
									ar.getWorld().spawnParticle(Particle.FLAME, ar.getLocation(), 5,0.1,0.1,0.1,0.05);
									ar.getWorld().spawnParticle(Particle.SPLASH, ar.getLocation(), 5,0.1,0.1,0.1,0.05);
									ar.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, ar.getLocation(), 5,0.1,0.1,0.1,0.05);
								}
							}, i*2);

						}

					}

				}

			}
		}
	}


	public void ChargingShot(ProjectileHitEvent d)
	{
			Projectile a = d.getEntity();
			if(a.getShooter() instanceof Player)
			{
				Player p = (Player) a.getShooter();

				if(ClassData.pc.get(p.getUniqueId()) == 5 && a.hasMetadata("ChargingShot of"+p.getName()))
				{
					Location el = a.getLocation();

					if(d.getHitEntity()!=null) {
						el = d.getHitEntity().getLocation();
					}

					cscooldown.put(p.getName(), System.currentTimeMillis());
					double sec = 10*(1-p.getAttribute(Attribute.LUCK).getValue()/1024d)*Obtained.ncd.getOrDefault(p.getUniqueId(), 1d);
					Bukkit.getPluginManager().callEvent(new SkillUseEvent(p,sec,5,"응집","ChargingShot"));

					for (Entity e : el.getWorld().getNearbyEntities(el, 3, 3, 3))
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
							atk0(1.8, lsd.ChargingShot.get(p.getUniqueId())*1.8, p, le);
							absorbing(p,el);
							Holding.holding(p, le, 55l);

							Holding.superholding(p, le, 20l);
							p.playSound(el, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
							p.getWorld().spawnParticle(Particle.FLASH, el, 10, 3, 1, 3);
							le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
							p.getWorld().spawnParticle(Particle.FALLING_WATER, el, 40, 3, 1, 3);
							p.getWorld().spawnParticle(Particle.DRIPPING_WATER, el, 40, 3, 1, 3);
							p.playSound(le.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1, 1);
							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 0, false, false));
							le.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 40, 0, false, false));
							p.getWorld().spawnParticle(Particle.FLAME, el, 40, 3, 1, 3, 0);
							p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, el, 40, 3, 1, 3, 0);
							p.getWorld().spawnParticle(Particle.LAVA, el, 40, 3, 1, 3, 0);
							le.setFireTicks(40);
							p.getWorld().spawnParticle(Particle.EFFECT, el, 40, 3, 1, 3, 0);
							p.getWorld().spawnParticle(Particle.END_ROD, el, 40, 3, 1, 3, 0);
							p.getWorld().spawnParticle(Particle.PORTAL, el, 40, 3, 1, 3, 0);
							le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 15, 0, false, false));
						}
					}
				}
			}
		
	}


	public void EnderWitherHunter(ProjectileHitEvent ev)
	{

		if(ev.getEntity().getShooter() instanceof Player && ev.getEntity() instanceof Arrow)
		{
			Player p = (Player)ev.getEntity().getShooter();
			Arrow ar = (Arrow) ev.getEntity();




			if(ClassData.pc.get(p.getUniqueId()) == 5)  {
				if(ev.getHitEntity() instanceof Wither) {
					Wither e =(Wither) ev.getHitEntity();
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
						p.setCooldown(Material.YELLOW_TERRACOTTA, 1);
						e.damage(bbArrow(ar), p);
						ev.getEntity().remove();
					}
				}
			}
		}
	}


	public void Equipment(PlayerItemDamageEvent e)
	{
		Player p = e.getPlayer();




		if(ClassData.pc.get(p.getUniqueId()) == 5)
		{
			if(p.getInventory().getItemInMainHand().getType().name().equals("BOW"))
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


		if(ClassData.pc.get(p.getUniqueId()) == 5 && (is.getType().name().equals("BOW"))  && ev.getNewSlot()==3 && p.isSneaking()&& Proficiency.getpro(p) >=1)
		{
			ev.setCancelled(true);
			p.setCooldown(CAREFUL, 1);
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(75/Proficiency.getpro(p)*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("흡수의화살")
					.ename("AbsorbingArrow")
					.slot(6)
					.hm(bultcooldown)
					.skillUse(() -> {
						p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0f, 2f);
						p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 1.0f, 2f);
						Arrow abar = (Arrow)p.launchProjectile(Arrow.class);
						abar.setShooter(p);
						abar.setMetadata("abar of" + p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
						abar.setCritical(true);
						abar.setVelocity(p.getLocation().getDirection().normalize().multiply(10));
						abar.setDamage(0);
						abar.setGlowing(true);
						p.getWorld().spawnParticle(Particle.ASH, p.getLocation(), 100, 2, 2, 2);
					});
			bd.execute();
		}
	}

	final private void ult(Location el, Player p) {
		ArrayList<Location> line = new ArrayList<>();
		final Vector l1 = el.clone().getDirection().normalize().rotateAroundY(Math.PI/2);
		final Vector l2 = el.clone().getDirection().normalize().rotateAroundY(Math.PI);
		final Vector l3 = el.clone().getDirection().normalize().rotateAroundY(-Math.PI/2);
		final Vector l4 = el.clone().getDirection().normalize();

		AtomicInteger j = new AtomicInteger();
		AtomicDouble k = new AtomicDouble();

		for(double an = 0; an<Math.PI*3; an +=Math.PI/45) {
			line.add(el.clone().add(l1.clone().multiply(k.addAndGet(0.03)).rotateAroundY(an).rotateAroundAxis(l1.clone(), an)));
			line.add(el.clone().add(l2.clone().multiply(k.get()).rotateAroundY(an).rotateAroundAxis(l2.clone(), an)));
			line.add(el.clone().add(l3.clone().multiply(k.get()).rotateAroundY(an).rotateAroundAxis(l3.clone(), an)));
			line.add(el.clone().add(l4.clone().multiply(k.get()).rotateAroundY(an).rotateAroundAxis(l4.clone(), an)));
			line.add(el.clone().add(l1.clone().multiply(k.addAndGet(0.03)).rotateAroundY(an)).add(0, an, 0));
			line.add(el.clone().add(l2.clone().multiply(k.get()).rotateAroundY(an)).add(0, an, 0));
			line.add(el.clone().add(l3.clone().multiply(k.get()).rotateAroundY(an)).add(0, an, 0));
			line.add(el.clone().add(l4.clone().multiply(k.get()).rotateAroundY(an)).add(0, an, 0));
		}
		Collections.reverse(line);

		final World w = el.getWorld();

		if(absrb.containsKey(p.getUniqueId())) {
			final int count = absrb.getOrDefault(p.getUniqueId(), 1);
			w.spawnParticle(Particle.FLAME, el, 50*count,1,8,1,0.2);
			w.spawnParticle(Particle.SPLASH, el, 50*count,1,8,1,0.2);
			w.spawnParticle(Particle.PORTAL, el, 50*count,1,8,1,0.2);
			w.spawnParticle(Particle.WAX_ON, el, 50*count,1,8,1,0.2);
		}

		line.forEach(l -> {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run()
				{
                	/*w.spawn(l, Chicken.class, ch ->{
                        ch.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
                        ch.setAI(false);
                        ch.setGravity(false);
                        ch.setInvulnerable(true);
                        ch.setCollidable(false);
                        ch.setSilent(true);
                	});*/
					w.spawnParticle(Particle.SWEEP_ATTACK, l, 1);
				}
			}, j.incrementAndGet()/54);

		});
	}

	final private void absorbing(Player p, Location tl) {
		if(!absrbl.containsKey(p.getUniqueId())) {
			return;
		}
		if(tl.distance(absrbl.get(p.getUniqueId())) <=15) {
			StringBuffer sb = new StringBuffer();
			absrb.computeIfPresent(p.getUniqueId(), (k,v)->v+1);
			for(int i = 0; i < absrb.get(p.getUniqueId()); i++) {
				sb.append(ChatColor.LIGHT_PURPLE + "|");
			}
			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(sb.toString()).create());

		}
	}

	public void ULT(ProjectileHitEvent ev)
	{

		if(ev.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)ev.getEntity().getShooter();



			if(ClassData.pc.get(p.getUniqueId()) == 5 && ev.getEntity().hasMetadata("abar of"+p.getName())) {
				Location el = ev.getEntity().getLocation();
				ev.getEntity().remove();
				final World ew = el.getWorld();

				absrb.putIfAbsent(p.getUniqueId(), 1);
				absrbl.putIfAbsent(p.getUniqueId(), el);

				for(int i =0; i<16; i++) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
						@Override
						public void run()
						{
							ult(el,p);

							p.playSound(el, Sound.ITEM_ARMOR_EQUIP_ELYTRA, 0.3f, 0.0f);
							p.playSound(el, Sound.ITEM_ARMOR_EQUIP_ELYTRA, 0.3f, 2.0f);
							for (Entity e : ew.getNearbyEntities(el, 15, 13, 15))
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
										le.teleport(el);
										Holding.holding(p, le, (long)10);
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
						final int count = absrb.getOrDefault(p.getUniqueId(), 1);
						absrb.remove(p.getUniqueId());
						absrbl.remove(p.getUniqueId());
						p.playSound(el, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
						ew.spawnParticle(Particle.EXPLOSION_EMITTER, el, count,8,8,8);
						p.playSound(el, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
						ew.spawnParticle(Particle.FLASH, el, 20*count, 5, 5, 5);
						ew.spawnParticle(Particle.DRIPPING_WATER, el, 20*count, 5, 5, 5);
						ew.spawnParticle(Particle.FLAME, el, 20*count, 5, 5, 5);
						ew.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, el, 10, 5, 5, 5);
						ew.spawnParticle(Particle.LAVA, el, 40, 3, 1, 3, 0);
						ew.spawnParticle(Particle.EFFECT, el,20*count, 10, 10, 10);
						ew.spawnParticle(Particle.END_ROD, el, 20*count, 10, 10, 10, 0);
						ew.spawnParticle(Particle.PORTAL, el, 20*count, 10, 10, 10, 0);
						for (Entity e : ew.getNearbyEntities(el, 10, 10, 10))
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
									atk1(0.28 * count, p, le);
									le.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0, false, false));
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 0, false, false));
									le.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 40, 0, false, false));
									le.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 15, 0, false, false));
									le.setFireTicks(40);

								}
							}
						}
					}
				}, 180);


			}
		}
	}


	public void ULT2(PlayerItemHeldEvent ev)
	{
		Player p = (Player)ev.getPlayer();
		if(!isCombat(p)) {
			return;
		}
		ItemStack is = p.getInventory().getItemInMainHand();

		if(ClassData.pc.get(p.getUniqueId()) == 5 && (is.getType().name().equals("BOW")) && p.isSneaking()  && ev.getNewSlot()==4&& Proficiency.getpro(p) >=2 )
		{
			ev.setCancelled(true);
			p.setCooldown(CAREFUL, 1);

			final Location tl = getTargetEntity(p,30);

			if(!arrowtype.containsKey(p.getName())) {
				arrowtype.put(p.getName(), 0);
				if(Proficiency.getpro(p)>=1) {
					arrowtype.put(p.getName(), 4);
				}
				return;
			}
			SkillBuilder bd = new SkillBuilder()
					.player(p)
					.cooldown(70*Obtained.ucd.getOrDefault(p.getUniqueId(), 1d))
					.kname("행성화살")
					.ename("Planet Arrow")
					.slot(7)
					.hm(bult2cooldown)
					.skillUse(() -> {
						final Location pfl = p.getLocation();
						Location arfl = p.getLocation().clone().add(0,12,0);
						Vector arvec = tl.clone().toVector().subtract(arfl.clone().toVector());
						arfl.setDirection(arvec.clone());

						ArrayList<Location> arls = new ArrayList<>();

						for(int i = 0; i < tl.distance(arfl); i++) {
							arls.add(arfl.clone().add(arvec.clone().normalize().multiply(i)));
						}


						AtomicInteger j = new AtomicInteger();


						p.teleport(arfl);
						p.getWorld().spawnParticle(Particle.PORTAL, p.getLocation(), 100, 2, 2, 2);
						p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 0);
						p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_LOADING_START, 1, 0);
						Holding.superholding(p, p, 20l);
						p.setGravity(false);

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
							@Override
							public void run()
							{
								p.teleport(pfl);
								p.setGravity(true);
								arls.forEach(l -> {
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
										@Override
										public void run()
										{
											absorbing(p,tl);
											HashSet<Location> spl = new HashSet<>();
											HashMap<Location, Block> sph = new HashMap<Location, Block>();
											for(int ix = -4; ix<4; ix++) {
												for(int iy = -4; iy<4; iy++) {
													for(int iz = -4; iz<4; iz++) {
														if((ix*ix + iy*iy + iz*iz<=16)){
															spl.add(l.clone().add(ix, iy, iz));
															sph.put(l.clone().add(ix, iy, iz), l.clone().add(ix, iy, iz).getBlock());
														}
													}
												}
											}
											spl.forEach(l -> {
												Random ran = new Random();
												int ri =ran.nextInt(3);
												Material type = Material.ANDESITE;

												if(arrowtype.getOrDefault(p.getName(), 0)==4) {
													if(ri == 0) {
														type = Material.BLUE_GLAZED_TERRACOTTA;
													}
													else if(ri == 1) {
														type = Material.CYAN_GLAZED_TERRACOTTA;
													}
													else {
														type = Material.LIGHT_GRAY_GLAZED_TERRACOTTA;
													}
												}
												else if(arrowtype.getOrDefault(p.getName(), 0)==5) {
													if(ri == 0) {
														type = Material.RED_GLAZED_TERRACOTTA;
													}
													else if(ri == 1) {
														type = Material.YELLOW_GLAZED_TERRACOTTA;
													}
													else  {
														type = Material.ORANGE_GLAZED_TERRACOTTA;
													}
												}
												p.sendBlockChange(l, type.createBlockData());

												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
													@Override
													public void run() {
														p.sendBlockChange(l,sph.get(l).getBlockData());
													}
												}, 6);
											});
											p.playSound(l, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 0);
											p.playSound(l, Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 0.3f, 2);
											p.getWorld().spawnParticle(Particle.CLOUD, l, 500, 8, 8, 8);

											for (Entity e : p.getWorld().getNearbyEntities(l, 9, 9, 9))
											{
												if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
												{
													LivingEntity le = (LivingEntity)e;
													{
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

														atk1(1.0, p, le);
														Holding.holding(p, le, (long) 50);
													}
												}
											}

										}
									},j.incrementAndGet()*4);
								});
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
									@Override
									public void run()
									{
										absorbing(p,tl);

										p.playSound(tl, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1f, 2);
										p.playSound(tl, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1f, 0);
										p.getWorld().spawnParticle(Particle.CLOUD, tl, 1000, 8, 8, 8);
										p.getWorld().spawnParticle(Particle.FLASH, tl, 100, 8, 8, 8);
										p.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, tl, 100, 8, 8, 8);
										for (Entity e : p.getWorld().getNearbyEntities(tl, 9, 9, 9))
										{
											if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
											{
												LivingEntity le = (LivingEntity)e;
												{
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

													atk1(13.0, p, le);
													Holding.holding(p, le, (long) 50);
												}
											}
										}
									}
								}, j.incrementAndGet()*4+1);
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

		if(ClassData.pc.get(p.getUniqueId()) == 5 && (is.getType().name().contains("BOW")) && !p.isSneaking()&& !p.isOnGround()&& !p.isSwimming()&& !p.isSprinting()&& Proficiency.getpro(p) >=2)
		{
			ev.setCancelled(true);
		}

	}

	public void Damagegetter(EntityDamageByEntityEvent d)
	{
		if(d.getDamager() instanceof Arrow && d.getEntity() instanceof LivingEntity)
		{
			Projectile a = (Projectile)d.getDamager();
			if(a.getShooter() instanceof Player) {
				Player p = (Player)a.getShooter();
				if(ClassData.pc.get(p.getUniqueId()) == 5) {

					if(p.getInventory().getItemInMainHand().getType()==Material.BOW && !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
					{
						d.setDamage(d.getDamage()*(1+lsd.MagicArrow.get(p.getUniqueId())*0.0453));
					}

				}
			}
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity)
		{
			Player p = (Player) d.getDamager();

			if(ClassData.pc.get(p.getUniqueId()) == 5) {

				if(p.getInventory().getItemInMainHand().getType()==Material.BOW && !p.getInventory().getItemInOffHand().getType().name().contains("NUGGET")&& !(p.getInventory().getItemInOffHand().getType()==Material.TRIDENT)&& !(p.getInventory().getItemInOffHand().getType()==Material.SHIELD))
				{
					d.setDamage(d.getDamage()*(1+lsd.MagicArrow.get(p.getUniqueId())*0.0453));
				}
			}
		}

	}
}