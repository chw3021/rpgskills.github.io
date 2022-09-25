package io.github.chw3021.monsters.raids;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Stray;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager.Type;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.items.Elements;
import io.github.chw3021.monsters.hyper.HyperSkills;
import io.github.chw3021.monsters.mountains.MountainsSkills;
import io.github.chw3021.monsters.ocean.OceanSkills;
import io.github.chw3021.monsters.red.RedSkills;
import io.github.chw3021.monsters.snow.SnowSkills;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class OverworldRaids extends Summoned implements Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8118960217265734839L;
	private HashMap<UUID, Location> beforepl = new HashMap<UUID, Location>();
	private Multimap<String, UUID> raider = ArrayListMultimap.create();
	private static Multimap<String, UUID> heroes = ArrayListMultimap.create();
	private static HashMap<String, Location> raidloc = new HashMap<String, Location>();
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


	private HashMap<String, Integer> difen = new HashMap<String, Integer>();
	private HashMap<String, Integer> difent = new HashMap<String, Integer>();

	private HashMap<String, UUID> inhibitor = new HashMap<String, UUID>();
	private HashMap<String, Integer> inhibitorhp = new HashMap<String, Integer>();
	
	Integer DelayTime =  100;
	Integer LIVES = 5;
	
	
	private static final OverworldRaids instance = new OverworldRaids ();
	public static OverworldRaids getInstance()
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
		else if(le.hasMetadata("raidvil")) {
			String rn = le.getMetadata("raidvil").get(0).asString();
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
			return raidloc.get(rn);
		}
		else{
			return null;
		}
	}
	
	protected final void OverworldRaidFinish(String rn, String title, String sub, Integer factor) {
		RaidFinish(rn,title,sub,factor);

		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("mirror"+rn)).forEach(e -> e.remove());

		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightmagma"+rn)).forEach(e -> e.remove());

		if(MountainsSkills.ordt.containsKey(rn)) {
			MountainsSkills.ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
			MountainsSkills.ordt.removeAll(rn);
		}
		if(OceanSkills.ordt.containsKey(rn)) {
			OceanSkills.ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
			OceanSkills.ordt.removeAll(rn);
		}
		if(SnowSkills.ordt.containsKey(rn)) {
			SnowSkills.ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
			SnowSkills.ordt.removeAll(rn);
		}
        if(HyperSkills.ordt.containsKey(rn)) {
        	HyperSkills.ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
        	HyperSkills.ordt.removeAll(rn);
        }
		if(RedSkills.ordt.containsKey(rn)) {
			RedSkills.ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
			RedSkills.ordt.removeAll(rn);
		}
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
		
		Location spl = raidloc.get(rn);
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
		
		
		vil.remove(rn);
		lives.remove(rn);
		timeout.remove(rn);
		if(raidpor.containsKey(rn)) {
			if(Bukkit.getEntity(raidpor.get(rn)) !=null) {
				Bukkit.getEntity(raidpor.get(rn)).remove();
			}
		}
		
    	heroes.get(rn).forEach(pu -> {
    		Player p = Bukkit.getPlayer(pu);
    		if(factor ==0) {
        		p.sendTitle(ChatColor.BOLD +(ChatColor.DARK_GRAY + title), ChatColor.BOLD +sub, 5, 60, 5);
        		p.playSound(spl, Sound.ENTITY_WITCH_AMBIENT, 1, 0);
        		p.playSound(spl, Sound.ENTITY_RAVAGER_DEATH, 1, 0);
            	p.spawnParticle(Particle.VILLAGER_ANGRY, spl, 1000,6,6,6);
    		}
    		else {
    			RaidDifficulties.saver(p, RaidCategory.OVERWORLD, difen.get(rn)+2);
    			
        		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "�¸�":"Victory!";
        		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + reg), null, 5, 60, 5);
        		p.playSound(spl, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
        		p.playSound(spl, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
            	spl.getWorld().spawn(spl, Firework.class);
            	raidcool.put(pu, System.currentTimeMillis());
            	
            	Elements.give(Elements.getstel(12,p), 5*(int)(1+ 0.05*difen.get(rn)*(1 - 0.1*heroes.get(rn).size())), p);
            	p.spawnParticle(Particle.COMPOSTER, spl, 1000,6,6,6);
            	p.spawnParticle(Particle.HEART, spl, 1000,6,6,6);
            	
    		}
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
					p.teleport(beforepl.get(p.getUniqueId()));
					beforepl.remove(p.getUniqueId());
					raidbart.remove(rn);
                }
            }, 160); 
    	});
		heroes.removeAll(rn);

		difen.remove(rn);
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
        			if(p.getWorld().equals(rl.getWorld()) && p.getLocation().clone().distance(rl) >90) {
        				p.teleport(rl.clone().add(1, 1, 0));
        				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.sendMessage("�ʹ� �ָ��Խ��ϴ�");
        				}
        				else {
							p.sendMessage("Too Far");
        				}
        			}
            	});
        		raider.get(rn).forEach(re -> {
        			Mob rm = (Mob)Bukkit.getEntity(re);
        			if(rm!=null) {
            			rm.setTarget((LivingEntity) Bukkit.getEntity(vil.get(rn)));
            			Location rml = rm.getLocation().clone();
            			rml.setY(rl.getY());
            			if(rml.clone().distance(rl) >80) {
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
                	raidbar.get(rn).setProgress(Holding.ale(newmob).getHealth()/Holding.ale(newmob).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
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
	
	final private LivingEntity bossgen(Location spl, Player p, String rn, Integer in, Double dif) {
		
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
		targeting(rn);
		
		//Double dif = 100000 * BigDecimal.valueOf(1 + 0.1*RaidDifficulties.getPlayerDifficulty(p, RaidCategory.OVERWORLD)).setScale(1, RoundingMode.HALF_EVEN).doubleValue();

		
		if(in == 0) {
			ItemStack main = new ItemStack(Material.STONE);
			main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
			ItemStack off = new ItemStack(Material.STONE);
			off.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "�����":"StoneGolem";
			IronGolem newmob = (IronGolem) MobspawnLoc(esl, ChatColor.GRAY + reg, dif, null,
					null, null, null, main, off, EntityType.IRON_GOLEM);
			newmob.setGlowing(true);
			newmob.setPlayerCreated(false);
    		newmob.setLootTable(null);

    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		newmob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4);
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
			newmob.setMetadata("stoneboss", new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
			
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		bossbargen("stoneboss", rn, newmob);

    		return newmob;
		}
		else if(in == 1) {
    		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
			hem.setLore(Arrays.asList("RedKnight Helmet"));
			hem.setCustomModelData(100);
			head.setItemMeta(hem);
			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
			chm.setColor(Color.RED);
			chest.setItemMeta(chm);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
			lem.setColor(Color.RED);
			leg.setItemMeta(lem);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
			bom.setColor(Color.RED);
			boots.setItemMeta(bom);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER,5);
    		ItemStack main = new ItemStack(Material.NETHERITE_AXE);
    		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
    		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		ItemStack off = new ItemStack(Material.SHIELD);
    		off.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
    		off.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "�������":"RedKnight";
    		Skeleton newmob = (Skeleton) MobspawnLoc(esl, ChatColor.RED+reg, dif, head, chest, leg, boots, main, off, EntityType.SKELETON);
    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    		newmob.setGlowing(true);
    		newmob.getEquipment().setBootsDropChance(0);
    		newmob.getEquipment().setChestplateDropChance(0);
    		newmob.getEquipment().setHelmetDropChance(0);
    		newmob.getEquipment().setItemInMainHandDropChance(0);
    		newmob.getEquipment().setItemInOffHandDropChance(0);
    		newmob.getEquipment().setLeggingsDropChance(0);
    		newmob.setMetadata("redboss", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setLootTable(null);
    		
    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
    		

    		bossbargen("redboss", rn, newmob);

    		return newmob;
		}
		else if(in == 2) {
			ItemStack head = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
			head.setItemMeta(hem);
			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
			chm.setColor(Color.RED);
			chest.setItemMeta(chm);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
			lem.setColor(Color.RED);
			leg.setItemMeta(lem);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
			bom.setColor(Color.WHITE);
			boots.setItemMeta(bom);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    		ItemStack main = new ItemStack(Material.STICK);
    		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		ItemStack off = new ItemStack(Material.ICE);
    		off.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "���긶��":"SnowWitch";
    		Witch newmob = (Witch) MobspawnLoc(esl, ChatColor.BLUE+reg, dif, null, null, null, boots, main, off, EntityType.WITCH);
    		newmob.setGlowing(true);
    		newmob.getEquipment().setBootsDropChance(0);
    		newmob.getEquipment().setChestplateDropChance(0);
    		newmob.getEquipment().setHelmetDropChance(0);
    		newmob.getEquipment().setItemInMainHandDropChance(0);
    		newmob.getEquipment().setItemInOffHandDropChance(0);
    		newmob.getEquipment().setLeggingsDropChance(0);
    		newmob.setCanJoinRaid(false);
    		newmob.setPatrolTarget(null);
    		newmob.setPatrolLeader(false);
    		newmob.setSilent(true);
    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.9);
    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999, 3, false, false));
    		
    		newmob.setMetadata("snowboss", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setLootTable(null);
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());

    		bossbargen("snowboss", rn, newmob);


    		return newmob;
		}
		else if(in == 3) {
    		ArmorStand inhb = (ArmorStand) spl.getWorld().spawn(spl.clone().add(20, 0,20), ArmorStand.class);
    		inhb.setMetadata("portal", new FixedMetadataValue(RMain.getInstance(), true));
    		inhb.setMetadata("inhibitor", new FixedMetadataValue(RMain.getInstance(), rn));
    		inhb.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		inhb.setRemoveWhenFarAway(false);
    		inhb.setGlowing(true);
    		inhb.getEquipment().setHelmet(new ItemStack(Material.BEACON));
    		inhb.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(dif);;
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		inhb.setCustomName(rn + " ��������� ������");
			}
			else {
        		inhb.setCustomName(rn + "'s ElderGuardian Inhibitor");
			}
    		inhb.setCustomNameVisible(true);
    		inhb.setCollidable(false);
    		inhibitor.put(rn, inhb.getUniqueId());
    		inhibitorhp.put(rn, 20);
    		

    		return inhb;
		}
		else if(in == 4) {

    		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "����B":"Dr.B";
    		Illusioner newmob = (Illusioner) MobspawnLoc(esl.clone().add(0, -20, 0), ChatColor.DARK_PURPLE+reg, dif, new ItemStack(Material.BLACK_STAINED_GLASS), null, null, null, null, null, EntityType.ILLUSIONER);
			newmob.setCanJoinRaid(false);
			newmob.setPatrolTarget(null);
			newmob.setPatrolLeader(false);
    		newmob.setGlowing(true);
    		newmob.getEquipment().setBootsDropChance(0);
    		newmob.getEquipment().setChestplateDropChance(0);
    		newmob.getEquipment().setHelmetDropChance(0);
    		newmob.getEquipment().setItemInMainHandDropChance(0);
    		newmob.getEquipment().setItemInOffHandDropChance(0);
    		newmob.getEquipment().setLeggingsDropChance(0);
    		newmob.setMetadata("hyperboss", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setLootTable(null);
    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
    		

    		bossbargen("hyperboss", rn, newmob);

    		return newmob;
		}
		else {
			return null;
		}
		
	}
	
	@EventHandler
	public void Escape(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();
		if(heroes.containsValue(p.getUniqueId())) {
	
			if(Party.hasParty(p)) {
				if(Party.isOwner(p)) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						OverworldRaidFinish(getheroname(p), "Ż��","��Ƽ���� �������ϴ�",0);
					}
					else {
						OverworldRaidFinish(getheroname(p), "Escaped","Party Owner Left",0);
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
					OverworldRaidFinish(getheroname(p), "Ż��","��Ƽ���� �������ϴ�",0);
				}
				else {
					OverworldRaidFinish(getheroname(p), "Escaped","Party Owner Left",0);
				}
			}
			
		}
	}
	@SuppressWarnings("deprecation")

	
	@EventHandler
	public void OverworldRaidStart(PlayerInteractEvent d)
	{
		if(d.getClickedBlock() != null && d.getClickedBlock().hasMetadata("OverworldRaidPortal") && d.getAction() == Action.RIGHT_CLICK_BLOCK && d.getAction() != Action.LEFT_CLICK_BLOCK) {

			Player p = (Player) d.getPlayer();
			if(!p.getInventory().getItemInMainHand().getType().isAir() || heroes.containsValue(p.getUniqueId())|| raider.containsKey(getheroname(p)) || beforepl.containsKey(p.getUniqueId()) || p.hasCooldown(Material.RAIL)) {
				return;
			}
			if(raidcool.containsKey(p.getUniqueId())) {

	            double timer = (raidcool.get(p.getUniqueId())/1000 + 10) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("���� ���� ħ������ ������� " + timer + "�� ��ٷ��� �˴ϴ�").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("You have to wait for " + timer + " seconds to Start Next Boss Raid").create());
					}
	            	return;
	            }
	            else { 
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("���� ���� ħ�������� �����ϽǼ� �ֽ��ϴ�").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("Now You Can Start Boss Raid").create());
					}
	            	raidcool.remove(p.getUniqueId());
	            }
			}
    		String rn = d.getClickedBlock().getMetadata("OverworldRaidPortal").get(0).asString();
    		World rw = Bukkit.getServer().getWorld("OverworldRaid");
    		int fix = p.getEntityId()*50-29999900;
    		int fiz = p.getEntityId()*50-29999900;
    		if(fix >= 29999900) {
    			fix = fix - 29999900*2;
    			fiz = 29999900*2 - fiz;
    		}
    		for(int in = 0; in<20; in++) {
	    		if(rw.getHighestBlockAt(fix, fiz).getType().name().contains("LEAVES")) {
	    			fix++;
	    		}
	    		else {
	    			break;
	    		}
    		}
        	p.setCooldown(Material.RAIL, 100);
    		Location spl = rw.getHighestBlockAt(fix, fiz).getLocation().clone().add(0, 1, 0);
    		if(Party.hasParty(p)) {
				if(Party.isOwner(p)) {
					Party.getMembers(Party.getParty(p)).forEach(pu -> {
						if(Bukkit.getPlayer(pu).getInventory().firstEmpty() == -1) {
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("�κ��丮�� ��ĭ�� ���� ��Ƽ���� �ֽ��ϴ�").create());
							}
							else {
				            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("Players should empty inventory least one space").create());
							}
							return;
						}
		        		heroes.put(rn, pu);
		        		
						final Location pl = Bukkit.getPlayer(pu).getLocation();
						beforepl.put(pu, pl);
        				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20,1,false,false));
						Bukkit.getPlayer(pu).teleport(spl.clone().add(0,0.5,0));
						Holding.invur(Bukkit.getPlayer(pu), 40l);
					});
				}
				else {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage("��Ƽ�常 �����մϴ�");
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
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("�κ��丮�� ��ĭ�� �����ϴ�").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("You should empty inventory least one space").create());
					}
					return;
				}
        		heroes.put(rn, p.getUniqueId());
				final Location pl = p.getLocation();
				beforepl.put(p.getUniqueId(), pl);
				p.teleport(spl.clone().add(0,0.5,0));
				Holding.invur(p, 40l);
			}
    		RaidFinish(rn,"","",0,"wild");
			d.getClickedBlock().setType(Material.VOID_AIR);
    		ArmorStand portal = (ArmorStand) spl.getWorld().spawn(spl, ArmorStand.class);
    		portal.setMetadata("portal", new FixedMetadataValue(RMain.getInstance(), true));
    		portal.setMetadata("OverworldRaidExit", new FixedMetadataValue(RMain.getInstance(), true));
    		portal.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		portal.setRemoveWhenFarAway(false);
    		portal.setGlowing(true);
    		portal.setGravity(false);
    		
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		portal.setCustomName(rn + "��Ƽ�� �ⱸ (��ũ�����¿��� �Ǽ����� ����)");
			}
			else {
        		portal.setCustomName(rn + "'s Party Exit (Sneaking + Hit with Fist)");
			}
    		portal.setCustomNameVisible(true);
    		portal.setCollidable(false);
    		raidloc.put(rn, spl);
    		raidpor.put(rn, portal.getUniqueId());

    		Villager v = (Villager) spl.getWorld().spawn(spl.clone().add(1,1,0), Villager.class);
    		v.setMetadata("raidvil", new FixedMetadataValue(RMain.getInstance(), rn));
    		v.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), rn));
    		v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
    		v.setAdult();
    		v.setAgeLock(true);
    		v.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
    		v.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		v.setGravity(true);
    		v.setNoDamageTicks(0);
    		v.setMaxHealth(10000);
    		v.setHealth(10000);
    		v.setRemoveWhenFarAway(false);
    		v.setGlowing(true);
    		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? rn + " �������":rn + "'s Archaeologist";
    		v.setVillagerType(Type.JUNGLE);
    		v.setProfession(Profession.LIBRARIAN);
    		v.setCustomName(reg);
    		v.setCustomNameVisible(true);
    		timeout.put(rn, 420);
    		vil.put(rn, v.getUniqueId());
    		
    		
    		if(Party.hasParty(p)) {
				if(Party.isOwner(p)) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "���̵��� �Է��ϼ��� (�ּ�: 0, �ִ�: "+RaidDifficulties.getMaxDifficulty(p, RaidCategory.OVERWORLD)+")").create());
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "5�ʾȿ� ���Է� �Ǵ� �ùٸ� �Է��� �ƴ� �� ���� ������ ���� ���� ���̵��� �ڵ� �����˴ϴ�").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "Enter difficulty level (min: 0, MAX: "+RaidDifficulties.getMaxDifficulty(p, RaidCategory.OVERWORLD)+")").create());
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "If you do not enter anything in 5 second or enter invalid inputs").create());
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "It will be automatically set to the highest level of difficulty you can challenge").create());

					}
					difen.put(rn, RaidDifficulties.getMaxDifficulty(p, RaidCategory.OVERWORLD));
					int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			            @Override
			            public void run() 
			            {
			            	OverworldRaidStart(p,rn,difen.get(rn));
			            	difent.remove(rn);
			            }
					}, 160); 
					difent.put(rn, task);

					Party.getMembers(Party.getParty(p)).forEach(pu -> {
						if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
							Bukkit.getPlayer(pu).spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "��Ƽ���� ���̵��� �������Դϴ�").create());
						}
						else {
							Bukkit.getPlayer(pu).spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "Owner is selecting difficulty now").create());
						}
					});
				}
			}
			else {
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "���̵��� �Է��ϼ��� (�ּ�: 0, �ִ�: "+RaidDifficulties.getMaxDifficulty(p, RaidCategory.OVERWORLD)+")").create());
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "5�ʾȿ� ���Է� �Ǵ� �ùٸ� �Է��� �ƴ� �� ���� ������ ���� ���� ���̵��� �ڵ� �����˴ϴ�").create());
				}
				else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "Enter difficulty level (min: 0, MAX: "+RaidDifficulties.getMaxDifficulty(p, RaidCategory.OVERWORLD)+")").create());
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "If you do not enter anything in 5 second or enter invalid inputs").create());
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "It will be automatically set to the highest level of difficulty you can challenge").create());

				}
				difen.put(rn, RaidDifficulties.getMaxDifficulty(p, RaidCategory.OVERWORLD));
				int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		            @Override
		            public void run() 
		            {
		            	OverworldRaidStart(p,rn,difen.get(rn));
		            	difent.remove(rn);
		            }
				}, 160); 
				difent.put(rn, task);
			}
    		
		}
	}
	
	final private void OverworldRaidStart(Player p, String rn, Integer endif) {

		
		final Location spl = raidloc.get(rn);

		if(Party.hasParty(p)) {
			if(Party.isOwner(p)) {
				Party.getMembers(Party.getParty(p)).forEach(pu -> {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        		Bukkit.getPlayer(pu).sendMessage(ChatColor.AQUA + "���̵��� "+endif+"�� ���� �Ǿ����ϴ�");
					}
					else {
		        		Bukkit.getPlayer(pu).sendMessage(ChatColor.AQUA + "Difficulty level set to "+endif);
					}
				});
			}
		}
		else {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		p.sendMessage(ChatColor.AQUA + "���̵��� "+endif+"�� ���� �Ǿ����ϴ�");
			}
			else {
        		p.sendMessage(ChatColor.AQUA + "Difficulty level set to "+endif);
			}
		}
		
		Double dif = 100000 * BigDecimal.valueOf(1 + 0.1*endif).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
		
		if(Party.hasParty(p)) {
			if(Party.isOwner(p)) {
				Party.getMembers(Party.getParty(p)).forEach(pu -> {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GRAY + "������ ���۵˴ϴ�", "�ֹ��� ��ȣ�ϰ� ���� ��� �����Ͻʽÿ�", 5, 69, 5);
					}
					else {
		        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GRAY + "Raid Will Start", "Protect Villager & Sweep All Enemies", 5, 69, 5);
					}
				});
			}
		}
		else {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		p.sendTitle(ChatColor.GRAY + "������ ���۵˴ϴ�", "�ֹ��� ��ȣ�ϰ� ���� ��� �����Ͻʽÿ�", 5, 69, 5);
			}
			else {
        		p.sendTitle(ChatColor.GRAY + "Raid Will Start", "Protect Villager & Sweep All Enemies", 5, 69, 5);
			}
		}
		lives.put(rn, LIVES);
		
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
	                    	
	                    	int ri = random.nextInt(4);
	                    	
	                    	bossgen(esl,p, rn, ri,dif);
	                    	
		                	heroes.get(rn).forEach(pu -> {
		                		Player pa = Bukkit.getPlayer(pu);
								if(pa.getLocale().equalsIgnoreCase("ko_kr")) {
    		                		pa.sendTitle(ChatColor.GRAY + "���� ����", null, 5, 69, 5);
    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
    		                		pa.sendMessage("���� ���: " +ChatColor.BOLD + String.valueOf(lives.get(rn)));
								}
								else {
    		                		pa.sendTitle(ChatColor.GRAY + "Raid Start", null, 5, 69, 5);
    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
    		                		pa.sendMessage(ChatColor.BOLD + String.valueOf(lives.get(rn)) + "lives Left");
								}
		                	});

	        	    		targeting(rn);

		        			String t = p.getLocale().equalsIgnoreCase("ko_kr") ? "�����ð� - ":"TimeLeft - ";
	        	    		BossBar	timeb = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn+"OverworldRaidTime"),t + String.valueOf((int)timeout.get(rn)/20/60) + ":" + String.valueOf((int)(timeout.get(rn)/20)%60), BarColor.WHITE, BarStyle.SEGMENTED_6);
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
    	        	            			OverworldRaidFinish(rn,"�й�..", "�ð��ʰ�",0);
	    								}
	    								else {
    	        	            			OverworldRaidFinish(rn,"Defeated..", "TimeOut",0);
	    								}
	        	                    	
	        	                    	spl.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, spl, 1000,6,6,6);
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
	public void OverworldRaidStart(AsyncPlayerChatEvent d) 
	{	
			Player p = (Player) d.getPlayer();

			final String rn = getheroname(p);

			if(!difen.containsKey(rn)) {
				return;
			}
			if(difent.containsKey(rn)) {
				Bukkit.getScheduler().cancelTask(difent.remove(rn));
			}
			Integer endif = 0;
			
			try {
				endif = Integer.parseInt(d.getMessage());
				if(endif > RaidDifficulties.getMaxDifficulty(p,RaidCategory.OVERWORLD)) {
					endif = RaidDifficulties.getMaxDifficulty(p,RaidCategory.OVERWORLD);
				}
			}
			catch(NumberFormatException e) {
				endif = RaidDifficulties.getMaxDifficulty(p,RaidCategory.OVERWORLD);
			}

			difen.put(rn, endif);
			OverworldRaidStart(p,rn,endif);
		
	}

	@EventHandler
	public void Victory(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("finalboss") && raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
	        	OverworldRaidFinish(rn, "", "",1);
			}
		}
	}




	@EventHandler
	public void Defeat(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("raidvil")) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raidvil").get(0).asString();

			if(Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr")) {
				OverworldRaidFinish(rn, "�й�..", "�ֹ� ��ȣ ����",0);
			}
			else {
				OverworldRaidFinish(rn, "Defeated..", "Fail to Protect the Villager",0);
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
        	if(lives.getOrDefault(rn, 0)<=0) {

    			if(Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr")) {
    				OverworldRaidFinish(rn, "�й�..", "��� ��� ����",0);
    			}
    			else {
                	OverworldRaidFinish(rn, "Defeated..", "All Lives Exhausted", 0);
    			}
        	}
		}
	}

	@EventHandler
	public void OverworldRaidExit(EntityDamageByEntityEvent d) 
	{	
		if(d.getEntity().hasMetadata("OverworldRaidExit")) {
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
								OverworldRaidFinish(getheroname(p), "�׺�","",0);
			    			}
			    			else {
								OverworldRaidFinish(getheroname(p), "Surrender","",0);
			    			}
						}
						else {
			    			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.sendMessage("��Ƽ�常 �����մϴ�");
			    			}
			    			else {
								p.sendMessage("You Should Be Owner");
			    			}
							return;
						}
					}
					else {
		    			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							OverworldRaidFinish(getheroname(p), "�׺�","",0);
		    			}
		    			else {
							OverworldRaidFinish(getheroname(p), "Surrender","",0);
		    			}
					}
					
				}
				return;
			}
		}
	}


	@EventHandler
	public void OverworldRaidExit(PlayerJoinEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(!heroes.containsValue(p.getUniqueId()) && p.getWorld().getName().contains("Raid")) {
			p.teleport(p.getBedSpawnLocation());
		}
	}


	@EventHandler
	public void OverworldRaidExit(PluginEnableEvent ev) 
	{
		List<World> worlds = Bukkit.getServer().getWorlds();
		worlds.forEach(w -> w.getPlayers().forEach(b -> {
				Player p = (Player) b;
				if(!heroes.containsValue(p.getUniqueId()) && p.getWorld().getName().contains("Raid")) {
					p.teleport(p.getBedSpawnLocation());
				}
		}));
	}

	@EventHandler
	public void OverworldRaidExit(PluginDisableEvent ev) 
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
		if(d.getEntity().getWorld().getName().contains("Raid")) {
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
			if(d.getDamager() instanceof Player &&  heroes.containsValue(d.getDamager().getUniqueId()) && d.getEntity().hasMetadata("raidvil")) {
				d.setCancelled(true);
			}
			if(d.getDamager() instanceof Projectile &&  d.getEntity().hasMetadata("raidvil")) {

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
		if(d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("mountains") && !d.isCancelled()) {

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

            Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
        			if(Party.hasParty(p)) {
        				if(Party.isOwner(p)) {
        					Holding.invur(p, 40l);
        					p.teleport(raidloc.get(getheroname(p)));
        				}
        				else {
        					Holding.invur(p, 40l);
        					p.teleport(raidloc.get(Party.getOwner(Party.getParty(p)).getName()));
        				}
        			}
        			else {
    					Holding.invur(p, 40l);
        				p.teleport(raidloc.get(getheroname(p)));
        			}
                }
            }, 25); 
		}
	}
	

	@EventHandler
	public void Teleport(PlayerTeleportEvent e)
	{
		Player p = e.getPlayer();
		if(e.getTo().getWorld().getName().contains("OverworldRaid")) {
			if(!heroes.containsValue(p.getUniqueId())) {
				e.setCancelled(true);
			}
		}
		if(e.getFrom().getWorld().getName().contains("OverworldRaid") && !e.getTo().getWorld().getName().contains("OverworldRaid") && !e.getTo().getWorld().getName().contains("FakeDimension")) {
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
	public void Inhibitordamage(EntityDamageByEntityEvent d) 
	{	

		if(d.getEntity().hasMetadata("inhibitor") && inhibitor.containsValue(d.getEntity().getUniqueId())) {
			Entity le = d.getEntity();
			String rn = le.getMetadata("inhibitor").get(0).asString();
			Bukkit.getPluginManager().callEvent(new EntityDeathEvent((LivingEntity) Bukkit.getEntity(inhibitor.get(rn)) , null));
		}
	}
	

	@EventHandler
	public void ElderGuardianRaidBoss(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("inhibitor") && inhibitor.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("inhibitor").get(0).asString();
			Location spl = le.getLocation().clone().add(0, -60, 0);
			spl.setY(30);
			raidloc.put(rn, spl);
			le.remove();
			if(Bukkit.getEntity(inhibitor.get(rn)) !=null) {
				Bukkit.getEntity(inhibitor.get(rn)).remove();
			}
			inhibitor.remove(rn);
	
			if(inhibitorhp.containsKey(rn)) {
				inhibitorhp.remove(rn);
			}
	
			if(tart.containsKey(rn)) {
				Bukkit.getServer().getScheduler().cancelTask(tart.get(rn));
				tart.remove(rn);
			}
			Bukkit.getServer().getScheduler().cancelTask(raidt.get(rn));
			
			if(leadert.containsKey(rn)) {
				Bukkit.getServer().getScheduler().cancelTask(leadert.get(rn));
				leadert.remove(rn);
			}
			if(raider.containsKey(rn)) {
				raider.get(rn).forEach(re -> {
					if(Bukkit.getEntity(re) != null) {
						Bukkit.getEntity(re).remove();
		    		}
				});
				raider.removeAll(rn);
			}
			
	    	heroes.get(rn).forEach(pu -> {
	    		Bukkit.getPlayer(pu).playSound(spl, Sound.ENTITY_ELDER_GUARDIAN_AMBIENT, 1, 0);
	    		Bukkit.getPlayer(pu).playSound(spl, Sound.BLOCK_BEACON_DEACTIVATE, 1, 0);
	    		Bukkit.getPlayer(pu).playSound(spl, Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 0);
	    		Bukkit.getPlayer(pu).playSound(spl, Sound.BLOCK_CONDUIT_DEACTIVATE, 1, 0);
	    		for(ItemStack is : Bukkit.getPlayer(pu).getInventory().getStorageContents()) {
	    			if(is != null && is.getItemMeta().hasLocalizedName() && is.getItemMeta().getLocalizedName().equals(rn + "Cursed shard"));
	    		}
	    		
				if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
	        		Bukkit.getPlayer(pu).sendTitle(ChatColor.BLUE + "�����Ⱑ �ı��Ǿ����ϴ�.", "����������� ����ϴ�.", 5, 69, 5);
				}
				else {
	        		Bukkit.getPlayer(pu).sendTitle(ChatColor.BLUE + "Inhibitor Destroyed", "ElderGuardian will Wake up", 5, 69, 5);
				}
	    		Bukkit.getPlayer(pu).teleport(spl.clone().add(0,2.5,0));
	    	});
	    	Bukkit.getEntity(vil.get(rn)).teleport(spl.clone().add(1,1,1));
	    	Bukkit.getEntity(raidpor.get(rn)).teleport(spl.clone().add(1,1,1));
	        int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	
	            	Random random=new Random();
	            	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	            	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	            	Location esl = spl.clone().add(number, -10, number2);
	
	        		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "���������":"ElderGuardian";
	        		ElderGuardian newmob = (ElderGuardian) MobspawnLoc(esl, ChatColor.BLUE+reg, le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*1.2, null, null, null, null, null, null, EntityType.ELDER_GUARDIAN);
		    		newmob.setGlowing(true);
		    		newmob.getEquipment().setBootsDropChance(0);
		    		newmob.getEquipment().setChestplateDropChance(0);
		    		newmob.getEquipment().setHelmetDropChance(0);
		    		newmob.getEquipment().setItemInMainHandDropChance(0);
		    		newmob.getEquipment().setItemInOffHandDropChance(0);
		    		newmob.getEquipment().setLeggingsDropChance(0);
		    		newmob.setMetadata("oceanboss", new FixedMetadataValue(RMain.getInstance(), true));
		    		
		    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
		    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
		    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
		    		newmob.setLootTable(null);
		    		
		    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		    		newmob.setRemoveWhenFarAway(false);
		    		raider.put(rn, newmob.getUniqueId());
		    		

    	    		bossbargen("ElderGuardian", rn, newmob);
	        		
	            	heroes.get(rn).forEach(pu -> {
						if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
	                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.GRAY + "���� �ܰ�"), null, 5, 69, 5);
			        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
				    		Bukkit.getPlayer(pu).sendMessage("���� ��� "+ ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)));
						}
						else {
	                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.GRAY + "BOSS WAVE"), null, 5, 69, 5);
			        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
	                		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)) + "lives Left");
						}
	            	});
	            }
	        }, DelayTime); 
	        raidt.put(rn, rat);
			
		}
	}

	@EventHandler
	public void ElderGuardianRaidBoss2(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("bosswave1") && d.getEntity().hasMetadata("oceanboss") &&raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			final Location lel = le.getLocation().clone();
			
			String rn = le.getMetadata("raid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
				Bukkit.getServer().getScheduler().cancelTask(raidbart.get(rn));
				Bukkit.getServer().getScheduler().cancelTask(raidt.get(rn));
	    		BossBar	newbar = raidbar.get(rn);
	    		newbar.setVisible(false);
				Location spl = raidloc.get(rn);
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
	        	spl.getWorld().spawnParticle(Particle.SOUL, lel, 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, lel, 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.NAUTILUS, lel, 1000,1,1,1);
	        	spl.getWorld().playSound(lel, Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, 1, 0);
	        	spl.getWorld().playSound(lel, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1, 0);
	        	spl.getWorld().playSound(lel, Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 1, 0);
	        	Bukkit.getEntity(vil.get(rn)).teleport(spl.clone().add(1,2.5,1));
	            int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	
	                	Location esl = lel.clone().add(0, -10, 0);
	
	    	    		
		        		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "��ĵ� ���������":"Encroached ElderGuardian";
		        		ElderGuardian newmob = (ElderGuardian) MobspawnLoc(esl, ChatColor.BLUE+reg, le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*1.2, null, null, null, null, null, null, EntityType.ELDER_GUARDIAN);
	    	    		newmob.setGlowing(true);
	    	    		newmob.getEquipment().setBootsDropChance(0);
	    	    		newmob.getEquipment().setChestplateDropChance(0);
	    	    		newmob.getEquipment().setHelmetDropChance(0);
	    	    		newmob.getEquipment().setItemInMainHandDropChance(0);
	    	    		newmob.getEquipment().setItemInOffHandDropChance(0);
	    	    		newmob.getEquipment().setLeggingsDropChance(0);
	    	    		newmob.setMetadata("oceanboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("ruined", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setLootTable(null);
	    	    		
	    	    		
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		

	    	    		bossbargen("EncroachedElderGuardian", rn, newmob);
	            		
	                	heroes.get(rn).forEach(pu -> {
							if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.BLUE + "������� 2�ܰ�"), null, 5, 69, 5);
				        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
					    		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + "���� ��� "+ String.valueOf(lives.getOrDefault(rn, 0)));
							}
							else {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.BLUE + "PHASE 2"), null, 5, 69, 5);
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

	@EventHandler
	public void DrBRaidBoss2(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("bosswave1") && d.getEntity().hasMetadata("hyperboss") && raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
				Bukkit.getServer().getScheduler().cancelTask(raidbart.get(rn));
				Bukkit.getServer().getScheduler().cancelTask(raidt.get(rn));
	    		BossBar	newbar = raidbar.get(rn);
	    		newbar.setVisible(false);
				Location spl = raidloc.get(rn);
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
	        	spl.getWorld().spawnParticle(Particle.BLOCK_MARKER, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.SPELL_INSTANT, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.CLOUD, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_GOAT_SCREAMING_AMBIENT, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_HUSK_CONVERTED_TO_ZOMBIE, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_HUSK_AMBIENT, 1, 0);
	            int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	
	
	        			ItemStack head = new ItemStack(Material.BLACK_STAINED_GLASS);
	        			ItemStack main = new ItemStack(Material.NETHERITE_PICKAXE);
	        			ItemMeta mm = main.getItemMeta();
	        			mm.setCustomModelData(7009);
	        			main.setItemMeta(mm);
		        		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "����Ʈ":"TheBeast";
		        		Husk newmob = (Husk) MobspawnLoc(le.getLocation().add(0, -20, 0), ChatColor.DARK_PURPLE+reg, le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*1.2,  head, null, null, null, main, null, EntityType.HUSK);
	    	    		newmob.setGlowing(true);
	    	    		newmob.setMetadata("hyperboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("ruined", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setLootTable(null);
	    	    		newmob.setAdult();
	    	    		newmob.setConversionTime(-1);
	    	    		
	    	    		
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		
	

	    	    		bossbargen("TheBeast", rn, newmob);
	    	    		
	    	    		targeting(rn);
	            		
	                	heroes.get(rn).forEach(pu -> {
							if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.DARK_PURPLE + "������� 2�ܰ�"), null, 5, 69, 5);
				        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
					    		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + "���� ��� "+ String.valueOf(lives.getOrDefault(rn, 0)));
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

	@EventHandler
	public void RedKnightRaidBoss2(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("bosswave1") && d.getEntity().hasMetadata("redboss") && raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
				Bukkit.getServer().getScheduler().cancelTask(raidbart.get(rn));
				Bukkit.getServer().getScheduler().cancelTask(raidt.get(rn));
	    		BossBar	newbar = raidbar.get(rn);
	    		newbar.setVisible(false);
				Location spl = raidloc.get(rn);
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
	        	spl.getWorld().spawnParticle(Particle.SOUL, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.FLAME, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_SKELETON_AMBIENT, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1, 2);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_GENERIC_EXTINGUISH_FIRE, 1, 0);
	        	Bukkit.getEntity(vil.get(rn)).teleport(spl.clone().add(1,2.5,1));
	            int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	
	                	Location esl = d.getEntity().getLocation().clone().add(0,0.5, 0);
	
	    	    		
	    	    		ItemStack head = le.getEquipment().getHelmet();
	    				ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
	    				chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
	    				chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    				chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    				ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
	    				leg.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
	    				leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    				leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    				ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
	    				boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
	    				boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    				boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    				boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER,5);
	    	    		ItemStack main = new ItemStack(Material.NETHERITE_AXE);
	    	    		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
	    	    		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
	    	    		ItemStack off = new ItemStack(Material.SHIELD);
	    	    		off.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
	    	    		off.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		        		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "������ �������":"Ruined RedKnight";
	    	    		Stray newmob = (Stray) MobspawnLoc(esl, ChatColor.RED+reg, le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*1.2, head, chest, leg, boots, main, main, EntityType.STRAY);
	    	    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
	    	    		newmob.setGlowing(true);
	    	    		newmob.getEquipment().setBootsDropChance(0);
	    	    		newmob.getEquipment().setChestplateDropChance(0);
	    	    		newmob.getEquipment().setHelmetDropChance(0);
	    	    		newmob.getEquipment().setItemInMainHandDropChance(0);
	    	    		newmob.getEquipment().setItemInOffHandDropChance(0);
	    	    		newmob.getEquipment().setLeggingsDropChance(0);
	    	    		newmob.setMetadata("redboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("ruined", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setLootTable(null);
	    	    		
	    	    		
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		

	    	    		bossbargen("RuinedRedKnight", rn, newmob);
	
	    	    		targeting(rn);
	                	heroes.get(rn).forEach(pu -> {
							if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.RED + "������� 2�ܰ�"), null, 5, 69, 5);
				        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
					    		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + "���� ��� "+ String.valueOf(lives.getOrDefault(rn, 0)));
							}
							else {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.RED + "PHASE 2"), null, 5, 69, 5);
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
