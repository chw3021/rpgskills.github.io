package io.github.chw3021.monsters.raids;

import java.util.ArrayList;
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
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
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
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.monsters.hyper.HyperSkills;
import io.github.chw3021.monsters.mountains.MountainsSkills;
import io.github.chw3021.monsters.ocean.OceanSkills;
import io.github.chw3021.monsters.red.RedSkills;
import io.github.chw3021.monsters.snow.SnowSkills;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class OverworldRaids extends Mobs implements Listener {

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

	

	private HashMap<String, UUID> inhibitor = new HashMap<String, UUID>();
	private HashMap<String, Integer> inhibitorhp = new HashMap<String, Integer>();
	
	Integer DelayTime =  200;
	
	
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
	
	private void RaidFinish(String rn, String title, String sub, Integer factor) {

		Bukkit.getWorld("SnowWitchRaid").getEntities().stream().filter(e -> e.hasMetadata("mirror"+rn)).forEach(e -> e.remove());

		Bukkit.getWorld("RedKnightRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
		Bukkit.getWorld("RedKnightRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightmagma"+rn)).forEach(e -> e.remove());

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
        		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "승리":"Victory!";
        		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + reg), null, 5, 60, 5);
        		p.playSound(spl, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
        		p.playSound(spl, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
            	spl.getWorld().spawn(spl, Firework.class);
            	raidcool.put(pu, System.currentTimeMillis());
            	
            	Elements.give(Elements.getel(5,p), 30/(heroes.get(rn).size()), p);
            	p.spawnParticle(Particle.COMPOSTER, spl, 1000,6,6,6);
            	p.spawnParticle(Particle.HEART, spl, 1000,6,6,6);
            	
    		}
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
					p.teleport(beforepl.get(p.getUniqueId()));
					beforepl.remove(p.getUniqueId());
					raidbart.remove(p.getName());
                }
            }, 160); 
    	});
		heroes.removeAll(rn);
        
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
							p.sendMessage("너무 멀리왔습니다");
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
	
	final private LivingEntity bossgen(Location spl, Player p, String rn, Integer in) {
		
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
		targeting(rn);

		
		if(in == 0) {
			ItemStack main = new ItemStack(Material.STONE);
			main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
			ItemStack off = new ItemStack(Material.STONE);
			off.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "스톤골렘":"StoneGolem";
			IronGolem newmob = (IronGolem) MobspawnLoc(esl, ChatColor.GRAY + reg, 100000.0, null,
					null, null, null, main, off, EntityType.IRON_GOLEM);
			newmob.setGlowing(true);
			newmob.setPlayerCreated(false);
    		newmob.setLootTable(null);

    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), p.getName() +"StoneGolem"),newmob.getName(), BarColor.WHITE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
            newbar.setVisible(true);
    		raidbar.put(rn, newbar);
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
    		
    		
    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {

					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn).setProgress(Holding.ale(newmob).getHealth()/100000d);
	                	raidbar.get(rn).setTitle(Holding.ale(newmob).getName());
	            		heroes.get(rn).forEach(pu -> {
            				Player p = (Player) Bukkit.getPlayer(pu);
            				raidbar.get(rn).addPlayer(p);
	            		});
					}
                }
			}, 0, 1);
    		raidbart.put(rn, task);
            newbar.setVisible(true);

    		return newmob;
		}
		else if(in == 1) {
    		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
			hem.setDisplayName("RedKnight Helmet");
			hem.setLocalizedName("RedKnight Helmet");
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
    		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "붉은기사":"RedKnight";
    		Skeleton newmob = (Skeleton) MobspawnLoc(esl, ChatColor.RED+reg, 80000.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
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
    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +"RedKnight"),newmob.getName(), BarColor.RED, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
            newbar.setVisible(true);
    		raidbar.put(rn, newbar);
    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
    		

    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {

					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn).setProgress(Holding.ale(newmob).getHealth()/80000d);
	                	raidbar.get(rn).setTitle(Holding.ale(newmob).getName());
	            		heroes.get(rn).forEach(pu -> {
            				Player p = (Player) Bukkit.getPlayer(pu);
            				raidbar.get(rn).addPlayer(p);
	            		});
					}
                }
			}, 0, 1);
    		raidbart.put(rn, task);
            newbar.setVisible(true);

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
    		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "설산마녀":"SnowWitch";
    		Witch newmob = (Witch) MobspawnLoc(esl, ChatColor.BLUE+reg, 70000.0, null, null, null, boots, main, off, EntityType.WITCH);
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
    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +"SnowWitch"),newmob.getName(), BarColor.WHITE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
            newbar.setVisible(true);
    		raidbar.put(rn, newbar);
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
    		

    		
    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {

					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn).setProgress(Holding.ale(newmob).getHealth()/70000d);
	                	raidbar.get(rn).setTitle(Holding.ale(newmob).getName());
	            		heroes.get(rn).forEach(pu -> {
            				Player p = (Player) Bukkit.getPlayer(pu);
            				raidbar.get(rn).addPlayer(p);
	            		});
					}
                }
			}, 0, 1);
    		raidbart.put(rn, task);
            newbar.setVisible(true);

    		return newmob;
		}
		else if(in == 3) {
    		ArmorStand inhb = (ArmorStand) spl.getWorld().spawn(spl.clone().add(20, 0,20), ArmorStand.class);
    		inhb.setMetadata("portal", new FixedMetadataValue(RMain.getInstance(), true));
    		inhb.setMetadata("inhibitor", new FixedMetadataValue(RMain.getInstance(), p.getName()));
    		inhb.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		inhb.setRemoveWhenFarAway(false);
    		inhb.setGlowing(true);
    		inhb.getEquipment().setHelmet(new ItemStack(Material.BEACON));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		inhb.setCustomName(p.getName() + " 엘더가디언 억제기");
			}
			else {
        		inhb.setCustomName(p.getName() + "'s ElderGuardian Inhibitor");
			}
    		inhb.setCustomNameVisible(true);
    		inhb.setCollidable(false);
    		inhibitor.put(p.getName(), inhb.getUniqueId());
    		inhibitorhp.put(p.getName(), 20);
    		
			String w = p.getLocale().equalsIgnoreCase("ko_kr") ? "억제기 내구도 ":"Inhibitor Durabity";
    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), p.getName()+"ElderGuardianRaid"),w, BarColor.BLUE, BarStyle.SEGMENTED_20, BarFlag.PLAY_BOSS_MUSIC);
            newbar.setVisible(true);
    		raidbar.put(p.getName(), newbar);
    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
            		raidbar.get(p.getName()).setProgress((double)inhibitorhp.get(p.getName()) / 20d);
            		raidbar.get(p.getName()).setTitle(w + (double)inhibitorhp.get(p.getName()) + "/" + 20);
            		heroes.get(p.getName()).forEach(pu -> {
        				Player p1 = (Player) Bukkit.getPlayer(pu);
        				if(p1 != null) {
        					raidbar.get(p.getName()).addPlayer(p1);
        				}
            		});
                }
			}, 0, 1);
    		raidbart.put(p.getName(), task);

    		return inhb;
		}
		else if(in == 4) {

    		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "닥터B":"Dr.B";
    		Illusioner newmob = (Illusioner) MobspawnLoc(esl.clone().add(0, -20, 0), ChatColor.DARK_PURPLE+reg, 20000.0, new ItemStack(Material.BLACK_STAINED_GLASS), null, null, null, null, null, EntityType.ILLUSIONER);
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
    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +"DrB"),newmob.getName(), BarColor.PURPLE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
            newbar.setVisible(true);
    		raidbar.put(rn, newbar);
    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
    		

    		
    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {

					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn).setProgress(Holding.ale(newmob).getHealth()/20000d);
	                	raidbar.get(rn).setTitle(Holding.ale(newmob).getName());
	            		heroes.get(rn).forEach(pu -> {
            				Player p = (Player) Bukkit.getPlayer(pu);
        					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 50, 1, false, false));
            				raidbar.get(rn).addPlayer(p);
	            		});
					}
                }
			}, 0, 1);
    		raidbart.put(rn, task);

    		return newmob;
		}
		else {
			return null;
		}
		
	}
	
	public void Escape(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();
		if(heroes.containsValue(p.getUniqueId())) {
	
			if(Party.hasParty(p)) {
				if(Party.isOwner(p) == true) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						RaidFinish(p.getName(), "탈주","파티장이 떠났습니다",0);
					}
					else {
						RaidFinish(p.getName(), "Escaped","Party Owner Left",0);
					}
				}
				else if(Party.isOwner(p) == false){
	        		heroes.remove(Party.getOwner(Party.getParty(p)).getName(), p.getUniqueId());
	        		p.teleport(beforepl.get(p.getUniqueId()));
	    			beforepl.remove(p.getUniqueId());
	    			return;
				}
			}
			else {
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					RaidFinish(p.getName(), "탈주","파티장이 떠났습니다",0);
				}
				else {
					RaidFinish(p.getName(), "Escaped","Party Owner Left",0);
				}
			}
			
		}
	}
	@SuppressWarnings("deprecation")
	
	public void OverworldRaidStart(PlayerPortalEvent d) 
	{	
		//if(d.getClickedBlock() != null && d.getClickedBlock().hasMetadata("OverworldRaidPortal") && d.getAction() == Action.RIGHT_CLICK_BLOCK && d.getAction() != Action.LEFT_CLICK_BLOCK) {
				if(d.getFrom().getBlock().getState().hasMetadata("overworldraidpor")) {

				Player p = (Player) d.getPlayer();
				if(heroes.containsValue(p.getUniqueId())|| raider.containsKey(p.getName()) || beforepl.containsKey(p.getUniqueId()) || p.hasCooldown(Material.RAIL)) {
					return;
				}
				if(p.getInventory().firstEmpty() == -1) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("인벤토리에 빈칸이 없습니다").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("You should empty inventory least one space").create());
					}
					return;
				}
				if(raidcool.containsKey(p.getUniqueId())) {

		            double timer = (raidcool.get(p.getUniqueId())/1000 + 600) - System.currentTimeMillis()/1000; 
		            if(!(timer < 0))
		            {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("보스 침공작전 입장까지 " + timer + "초 기다려야 됩니다").create());
						}
						else {
			            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("You have to wait for " + timer + " seconds to Start Boss Raid").create());
						}
		            	return;
		            }
		            else { 
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("이제 보스 침공작전에 입장하실수 있습니다").create());
						}
						else {
			            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("Now You Can Start Boss Raid").create());
						}
		            	raidcool.remove(p.getUniqueId());
		            }
				}
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
	    		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {

	    	    		if(Party.hasParty(p)) {
	    					if(Party.isOwner(p)) {
	    						Party.getMembers(Party.getParty(p)).forEach(pu -> {
	    			        		heroes.put(p.getName(), pu);
	    						});
	    					}
	    				}
	    				else {
	    	        		heroes.put(p.getName(), p.getUniqueId());
	    				}
	    		    		if(Party.hasParty(p)) {
	    						if(Party.isOwner(p)) {
	    							Party.getMembers(Party.getParty(p)).forEach(pu -> {
	    								final Location pl = Bukkit.getPlayer(pu).getLocation();
	    								beforepl.put(pu, pl);
	    	            				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20,1,false,false));
	    								Bukkit.getPlayer(pu).teleport(spl.clone().add(0,0.5,0));
	    								Holding.invur(Bukkit.getPlayer(pu), 40l);
	    								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		    				        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GRAY + "작전이 시작됩니다", "주민을 보호하고 적을 모두 섬멸하십시오", 5, 69, 5);
	    								}
	    								else {
		    				        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GRAY + "Raid Will Start", "Protect Villager & Sweep All Enemies", 5, 69, 5);
	    								}
	    				            
	    							});
	    						}
	    						else {
	    							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		    							p.sendMessage("파티장만 가능합니다");
	    							}
	    							else {
		    							p.sendMessage("You Should Be Owner");
	    							}
	    							return;
	    						}
	    					}
	    					else {
	    						final Location pl = p.getLocation();
	    						beforepl.put(p.getUniqueId(), pl);
	    						p.teleport(spl.clone().add(0,0.5,0));
	    						Holding.invur(p, 40l);
								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    				        		p.sendTitle(ChatColor.GRAY + "작전이 시작됩니다", "주민을 보호하고 적을 모두 섬멸하십시오", 5, 69, 5);
								}
								else {
		    		        		p.sendTitle(ChatColor.GRAY + "Raid Will Start", "Protect Villager & Sweep All Enemies", 5, 69, 5);
								}
	    		        		p.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
	    					}
	    					d.getFrom().getBlock().setType(Material.VOID_AIR);
	    	        		ArmorStand portal = (ArmorStand) spl.getWorld().spawn(spl, ArmorStand.class);
	    	        		portal.setMetadata("portal", new FixedMetadataValue(RMain.getInstance(), true));
	    	        		portal.setMetadata("OverworldRaidExit", new FixedMetadataValue(RMain.getInstance(), true));
	    	        		portal.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	        		portal.setRemoveWhenFarAway(false);
	    	        		portal.setGlowing(true);
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		    	        		portal.setCustomName(p.getName() + "파티의 출구 (웅크린상태에서 맨손으로 가격)");
							}
							else {
		    	        		portal.setCustomName(p.getName() + "'s Party Exit (Sneaking + Hit with Fist)");
							}
	    	        		portal.setCustomNameVisible(true);
	                		portal.setCollidable(false);
	    	        		raidloc.put(p.getName(), spl);
	    	        		raidpor.put(p.getName(), portal.getUniqueId());
	    	        		
	        	    		lives.put(p.getName(), 5);
	    	        		Villager v = (Villager) spl.getWorld().spawn(spl.clone().add(1,1,0), Villager.class);
	    	        		v.setMetadata("raidvil", new FixedMetadataValue(RMain.getInstance(), p.getName()));
	    	        		v.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), p.getName()));
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
	    	        		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? p.getName() + " 고고학자":p.getName() + "'s Archaeologist";
	    	        		v.setVillagerType(Type.JUNGLE);
	    	        		v.setProfession(Profession.LIBRARIAN);
	    	        		v.setCustomName(reg);
	    	        		v.setCustomNameVisible(true);
	    	        		timeout.put(p.getName(), 420);
	    	        		vil.put(p.getName(), v.getUniqueId());
	    	                int rat = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    		                @Override
	    		                public void run() {

	    		                	Random random=new Random();
	    		                	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	    		                	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	    		                	Location esl = spl.clone().add(number, 1, number2);
    		                    	
    		                    	int ri = random.nextInt(4);
    		                    	
    		                    	bossgen(esl,p, p.getName(), ri);
    		                    	
	    		                	heroes.get(p.getName()).forEach(pu -> {
	    		                		Player pa = Bukkit.getPlayer(pu);
	    								if(pa.getLocale().equalsIgnoreCase("ko_kr")) {
		    		                		pa.sendTitle(ChatColor.GRAY + "작전 시작", null, 5, 69, 5);
		    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
		    		                		pa.sendMessage("남은 목숨: " +ChatColor.BOLD + String.valueOf(lives.get(p.getName())));
	    								}
	    								else {
		    		                		pa.sendTitle(ChatColor.GRAY + "Raid Start", null, 5, 69, 5);
		    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
		    		                		pa.sendMessage(ChatColor.BOLD + String.valueOf(lives.get(p.getName())) + "lives Left");
	    								}
	    		                	});

	    	        	    		targeting(p.getName());

	    		        			String t = p.getLocale().equalsIgnoreCase("ko_kr") ? "남은시간 - ":"TimeLeft - ";
	    	        	    		BossBar	timeb = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), p.getName()+"OverworldRaidTime"),t + String.valueOf((int)timeout.get(p.getName())/20/60) + ":" + String.valueOf((int)(timeout.get(p.getName())/20)%60), BarColor.WHITE, BarStyle.SEGMENTED_6);
	    	        	    		timeb.setVisible(true);
	    	        	    		timebar.put(p.getName(), timeb);
	    	        	    		int timetask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	    	        	                @Override
	    	        	                public void run() 
	    	        	                {
	    	        	            		timebar.get(p.getName()).setProgress((double)timeout.get(p.getName())/420d);
	    	        	            		timebar.get(p.getName()).setTitle(t +"[" + String.valueOf((int)timeout.get(p.getName())/60) + ":" + String.valueOf((int)(timeout.get(p.getName()))%60)+"]");
	    	        	            		heroes.get(p.getName()).forEach(pu -> {
	    	    	            				Player p1 = (Player) Bukkit.getPlayer(pu);
	    	    	            				if(p1 != null) {
	    		    	            				timebar.get(p.getName()).addPlayer(p1);
	    	    	            				}
	    	        	            		});
	    	        	            		timeout.computeIfPresent(p.getName(), (k,v) -> v-1);
	    	        	            		if(timeout.get(p.getName()) <=0) {
	    	        	            			String rn = p.getName();

	    	    								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		    	        	            			RaidFinish(rn,"패배..", "시간초과",0);
	    	    								}
	    	    								else {
		    	        	            			RaidFinish(rn,"Defeated..", "TimeOut",0);
	    	    								}
	    	        	                    	
	    	        	                    	spl.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, spl, 1000,6,6,6);
	    	        	            		}
	    	        	                }
	    	        				}, 0, 20);
	    	        	    		timet.put(p.getName(), timetask);
	    		                }
	    		            }, DelayTime); 
	    	                raidt.put(p.getName(), rat);
	                }
	    		},5);
                return;
			
		}
	}

	public void Victory(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("finalboss") && raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
	        	RaidFinish(rn, "", "",1);
			}
		}
	}



	
	public void Defeat(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("raidvil")) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raidvil").get(0).asString();

			if(Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr")) {
				RaidFinish(rn, "패배..", "주민 보호 실패",0);
			}
			else {
				RaidFinish(rn, "Defeated..", "Fail to Protect the Villager",0);
			}
		}
	}
	
	
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
    				RaidFinish(rn, "패배..", "모든 목숨 소진",0);
    			}
    			else {
                	RaidFinish(rn, "Defeated..", "All Lives Exhausted", 0);
    			}
        	}
		}
	}
	
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
								RaidFinish(p.getName(), "항복","",0);
			    			}
			    			else {
								RaidFinish(p.getName(), "Surrender","",0);
			    			}
						}
						else {
			    			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.sendMessage("파티장만 가능합니다");
			    			}
			    			else {
								p.sendMessage("You Should Be Owner");
			    			}
							return;
						}
					}
					else {
		    			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							RaidFinish(p.getName(), "항복","",0);
		    			}
		    			else {
							RaidFinish(p.getName(), "Surrender","",0);
		    			}
					}
					
				}
				return;
			}
		}
	}

	
	public void OverworldRaidExit(PlayerJoinEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(!heroes.containsValue(p.getUniqueId()) && p.getWorld().getName().contains("Raid")) {
			p.teleport(p.getBedSpawnLocation());
		}
	}

	
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
        					p.teleport(raidloc.get(p.getName()));
        				}
        				else {
        					Holding.invur(p, 40l);
        					p.teleport(raidloc.get(Party.getOwner(Party.getParty(p)).getName()));
        				}
        			}
        			else {
    					Holding.invur(p, 40l);
        				p.teleport(raidloc.get(p.getName()));
        			}
                }
            }, 25); 
		}
	}
	

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
	public void BlockPlace(BlockPlaceEvent d) 
	{
		Player p = d.getPlayer();
		if(heroes.containsValue(p.getUniqueId())|| raider.containsKey(p.getName()) || beforepl.containsKey(p.getUniqueId())) {
			d.setCancelled(true);
		}
	}

	public void BlockBreak(BlockBreakEvent d) 
	{
		Player p = d.getPlayer();
		if(heroes.containsValue(p.getUniqueId())|| raider.containsKey(p.getName()) || beforepl.containsKey(p.getUniqueId())) {
			d.setCancelled(true);
		}
	}

	public void ElderGuardianRaidBoss(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("inhibitor") && inhibitor.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("inhibitor").get(0).asString();
			Location spl = le.getLocation().clone().add(0, -60, 0);
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
			Bukkit.getServer().getScheduler().cancelTask(raidbart.get(rn));
			Bukkit.getServer().getScheduler().cancelTask(raidt.get(rn));
			BossBar	newbar = raidbar.get(rn);
			newbar.setVisible(false);
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
	        		Bukkit.getPlayer(pu).sendTitle(ChatColor.BLUE + "억제기가 파괴되었습니다.", "엘더가디언이 깨어납니다.", 5, 69, 5);
				}
				else {
	        		Bukkit.getPlayer(pu).sendTitle(ChatColor.BLUE + "Inhibitor Destroyed", "ElderGuardian will Wake up", 5, 69, 5);
				}
	    		Bukkit.getPlayer(pu).teleport(spl.clone().add(0,2.5,0));
	    	});
	    	Bukkit.getEntity(vil.get(rn)).teleport(spl.clone().add(1,1,1));
	        int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	
	            	Random random=new Random();
	            	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	            	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	            	Location esl = spl.clone().add(number, -20, number2);
	
	        		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "엘더가디언":"ElderGuardian";
	        		ElderGuardian newmob = (ElderGuardian) MobspawnLoc(esl, ChatColor.BLUE+reg, 40000.0, null, null, null, null, null, null, EntityType.ELDER_GUARDIAN);
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
		    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +"ElderGuardian"),newmob.getName(), BarColor.BLUE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
		            newbar.setVisible(true);
		    		raidbar.put(rn, newbar);
		    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		    		newmob.setRemoveWhenFarAway(false);
		    		raider.put(rn, newmob.getUniqueId());
		    		
	
		    		
		    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
	
							if(Holding.ale(newmob)!=null) {
	    	                	raidbar.get(rn).setProgress(Holding.ale(newmob).getHealth()/40000d);
	    	                	raidbar.get(rn).setTitle(Holding.ale(newmob).getName());
	    	            		heroes.get(rn).forEach(pu -> {
		            				Player p = (Player) Bukkit.getPlayer(pu);
		            				raidbar.get(rn).addPlayer(p);
	    	            		});
							}
		                }
					}, 0, 1);
		    		raidbart.put(rn, task);
	        		
	            	heroes.get(rn).forEach(pu -> {
						if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
	                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.GRAY + "보스 단계"), null, 5, 69, 5);
			        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
				    		Bukkit.getPlayer(pu).sendMessage("남은 목숨 "+ ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)));
						}
						else {
	                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.GRAY + "BOSS WAVE"), null, 5, 69, 5);
			        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
	                		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)) + "lives Left");
						}
	            	});
		            newbar.setVisible(true);
	            }
	        }, DelayTime); 
	        raidt.put(rn, rat);
			
		}
	}

	public void ElderGuardianRaidBoss2(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("bosswave1") && d.getEntity().hasMetadata("oceanboss") &&raider.containsValue(d.getEntity().getUniqueId())) {
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
	        	spl.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.NAUTILUS, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 1, 0);
	        	Bukkit.getEntity(vil.get(rn)).teleport(spl.clone().add(1,2.5,1));
	            int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	
	                	Location esl = d.getEntity().getLocation().clone().add(0,0.5, 0);
	
	    	    		
		        		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "잠식된 엘더가디언":"Encroached ElderGuardian";
		        		ElderGuardian newmob = (ElderGuardian) MobspawnLoc(esl, ChatColor.BLUE+reg, 80000.0, null, null, null, null, null, null, EntityType.ELDER_GUARDIAN);
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
	    	    		
	    	    		
	    	    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(),rn +"Encroached_ElderGuardian"),newmob.getName(), BarColor.BLUE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
	    	            newbar.setVisible(true);
	    	    		raidbar.put(rn, newbar);
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		
	
	    	    		
	    	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() 
	    	                {
	    						if(Holding.ale(newmob)!=null) {
	        	                	raidbar.get(rn).setProgress(Holding.ale(newmob).getHealth()/80000d);
	        	                	raidbar.get(rn).setTitle(Holding.ale(newmob).getName());
	        	            		heroes.get(rn).forEach(pu -> {
	    	            				Player p = (Player) Bukkit.getPlayer(pu);
	    	            				raidbar.get(rn).addPlayer(p);
	        	            		});
	    						}
	    	                }
	    				}, 0, 1);
	    	    		raidbart.put(rn, task);
	
	            		
	                	heroes.get(rn).forEach(pu -> {
							if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.BLUE + "토벌작전 2단계"), null, 5, 69, 5);
				        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
					    		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + "남은 목숨 "+ String.valueOf(lives.getOrDefault(rn, 0)));
							}
							else {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.BLUE + "PHASE 2"), null, 5, 69, 5);
				        		Bukkit.getPlayer(pu).playSound(spl, Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1, 1);
		                		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)) + "lives Left");
							}
	                	});
	    	            newbar.setVisible(true);
	                }
	            }, 50); 
	            raidt.put(rn, rat);
			}
		}
	}

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
		        		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "더비스트":"TheBeast";
		        		Husk newmob = (Husk) MobspawnLoc(le.getLocation().add(0, -20, 0), ChatColor.DARK_PURPLE+reg, 120000.0,  head, null, null, null, main, null, EntityType.HUSK);
	    	    		newmob.setGlowing(true);
	    	    		newmob.setMetadata("hyperboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("ruined", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setLootTable(null);
	    	    		newmob.setAdult();
	    	    		newmob.setConversionTime(-1);
	    	    		
	    	    		
	    	    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(),rn +"TheBeast"),newmob.getName(), BarColor.PURPLE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
	    	            newbar.setVisible(true);
	    	    		raidbar.put(rn, newbar);
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		
	
	    	    		
	    	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() 
	    	                {
	    						if(Holding.ale(newmob)!=null) {
	        	                	raidbar.get(rn).setProgress(Holding.ale(newmob).getHealth()/120000d);
	        	                	raidbar.get(rn).setTitle(Holding.ale(newmob).getName());
	        	            		heroes.get(rn).forEach(pu -> {
	    	            				Player p = (Player) Bukkit.getPlayer(pu);
	    	        					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 50, 1, false, false));
	    	            				raidbar.get(rn).addPlayer(p);
	        	            		});
	    						}
	    	                }
	    				}, 0, 1);
	    	    		raidbart.put(rn, task);
	
	    	    		targeting(rn);
	            		
	                	heroes.get(rn).forEach(pu -> {
							if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.DARK_PURPLE + "토벌작전 2단계"), null, 5, 69, 5);
				        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
					    		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + "남은 목숨 "+ String.valueOf(lives.getOrDefault(rn, 0)));
							}
							else {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.DARK_PURPLE + "PHASE 2"), null, 5, 69, 5);
				        		Bukkit.getPlayer(pu).playSound(spl, Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1, 1);
		                		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)) + "lives Left");
							}
	                	});
	    	            newbar.setVisible(true);
	                }
	            }, 50); 
	            raidt.put(rn, rat);
			}
		}
	}

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
	
	    	    		
	    	    		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
	    				LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
	    				hem.setDisplayName("RedKnight Helmet");
	    				hem.setLocalizedName("RedKnight Helmet");
	    				hem.setCustomModelData(100);
	    				head.setItemMeta(hem);
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
		        		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "몰락한 붉은기사":"Ruined RedKnight";
	    	    		Stray newmob = (Stray) MobspawnLoc(esl, ChatColor.RED+reg, 100000.0, head, chest, leg, boots, main, main, EntityType.STRAY);
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
	    	    		
	    	    		
	    	    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(),rn +"Ruined_RedKnight"),newmob.getName(), BarColor.BLUE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
	    	            newbar.setVisible(true);
	    	    		raidbar.put(rn, newbar);
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		
	
	    	    		
	    	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	    	                @Override
	    	                public void run() 
	    	                {
	    						if(Holding.ale(newmob)!=null) {
	        	                	raidbar.get(rn).setProgress(Holding.ale(newmob).getHealth()/100000);
	        	                	raidbar.get(rn).setTitle(Holding.ale(newmob).getName());
	        	            		heroes.get(rn).forEach(pu -> {
	    	            				Player p = (Player) Bukkit.getPlayer(pu);
	    	            				raidbar.get(rn).addPlayer(p);
	        	            		});
	    						}
	    	                }
	    				}, 0, 1);
	    	    		raidbart.put(rn, task);
	
	    	    		targeting(rn);
	                	heroes.get(rn).forEach(pu -> {
							if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.RED + "토벌작전 2단계"), null, 5, 69, 5);
				        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
					    		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + "남은 목숨 "+ String.valueOf(lives.getOrDefault(rn, 0)));
							}
							else {
		                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.RED + "PHASE 2"), null, 5, 69, 5);
				        		Bukkit.getPlayer(pu).playSound(spl, Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1, 1);
		                		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)) + "lives Left");
							}
	                	});
	    	            newbar.setVisible(true);
	                }
	            }, 50); 
	            raidt.put(rn, rat);
			}
		}
	}
}
