package io.github.chw3021.monsters.raids;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

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
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Axolotl.Variant;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
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
import io.github.chw3021.items.Elements;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class OceanRaids extends Mobs implements Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7436519755475378459L;
	private HashMap<UUID, Location> beforepl = new HashMap<UUID, Location>();
	private Multimap<String, UUID> raider = ArrayListMultimap.create();
	private static Multimap<String, UUID> heroes = ArrayListMultimap.create();
	private static HashMap<String, Location> raidloc = new HashMap<String, Location>();
	private HashMap<String, UUID> raidpor = new HashMap<String, UUID>();
	private HashMap<String, Integer> raidt = new HashMap<String, Integer>();
	private HashMap<String, BossBar> raidbar = new HashMap<String, BossBar>();
	private HashMap<String, Integer> raidbart = new HashMap<String, Integer>();
	private Multimap<UUID, UUID> leader = ArrayListMultimap.create();
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

	private HashMap<String, Integer> spawnt = new HashMap<String, Integer>();

	Integer DelayTime =  200;
	
	
	private static final OceanRaids instance = new OceanRaids ();
	public static OceanRaids getInstance()
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
        			if(rm!=null && rm.getType() != EntityType.ELDER_GUARDIAN && rm.getType() != EntityType.GUARDIAN) {
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
	
	
	
	private void RaidFinish(String rn, String title, String sub, Integer factor) {


		Bukkit.getWorld("ElderGuardianRaid").getEntities().stream().forEach(e ->{
			if(e.hasMetadata("cursedshard"+rn)) {
				e.remove();
			}
			if(e instanceof Item) {
				Item ie = (Item) e;
				if(ie.getItemStack().getItemMeta() != null && ie.getItemStack().getItemMeta().getLocalizedName().equals(rn + "Cursed shard")) {
					e.remove();
				}
			}
		});
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
		if(spawnt.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(spawnt.get(rn));
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
            	
            	Elements.give(Elements.getel(7,p), 30/(heroes.get(rn).size()), p);
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

	
	final private void MobSpawn(Location spl, Player p, Integer factor) {

		if(factor%3 == 1) {
			for(int i =0; i <19; i++) {

	        	Random random=new Random();
	        	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	        	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	        	Location esl = spl.clone().add(number, -20, number2);
	        	ItemStack head = new ItemStack(Material.DARK_PRISMARINE);
				ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
				chm.setColor(Color.NAVY);
				chest.setItemMeta(chm);
				chest.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
				chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
				ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
				lem.setColor(Color.NAVY);
				leg.setItemMeta(lem);
				leg.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
				leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
				ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
				bom.setColor(Color.BLACK);
				boots.setItemMeta(bom);
				boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
				boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    		ItemStack main = new ItemStack(Material.NETHERITE_SWORD);
	    		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "잠식된 해적":"Encroached Pirate";
	    		Drowned newmob = (Drowned) MobspawnLoc(esl, ChatColor.DARK_BLUE+reg, 3000.0, head, chest, leg, boots, main, null, EntityType.DROWNED);
	    		newmob.setAdult();
	    		newmob.setConversionTime(-1);
	    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
	    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
	    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), p.getName()));
	    		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    		newmob.setRemoveWhenFarAway(false);
	    		newmob.setLootTable(null);
	    		raider.put(p.getName(), newmob.getUniqueId());
			}

	    	Random random=new Random();
	    	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	    	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	    	Location esl = spl.clone().add(number, -20, number2);
	    	ItemStack head = new ItemStack(Material.DARK_PRISMARINE);
			ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
			ItemMeta chm = (ItemMeta) chest.getItemMeta();
			chest.setItemMeta(chm);
			chest.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
			lem.setColor(Color.NAVY);
			leg.setItemMeta(lem);
			leg.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
			ItemMeta bom = (ItemMeta) boots.getItemMeta();
			boots.setItemMeta(bom);
			boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack main = new ItemStack(Material.TRIDENT);
			ItemStack off = new ItemStack(Material.FLINT_AND_STEEL);
			String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "잠식된 선장":"Encroached Captain";
			Drowned newmob = (Drowned) MobspawnLoc(esl, ChatColor.DARK_BLUE+reg, 3500.0, head, chest, leg, boots, main, off, EntityType.DROWNED);
			newmob.setAdult();
			newmob.setConversionTime(-1);
			newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
			newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
			newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
			newmob.setGlowing(true);
			newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), p.getName()));
			newmob.setMetadata("leader", new FixedMetadataValue(RMain.getInstance(),true));
			newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
			newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
			newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
			newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setRemoveWhenFarAway(false);
			newmob.setLootTable(null);
			raider.get(p.getName()).forEach(le -> leader.put(newmob.getUniqueId(), le));
			raider.put(p.getName(), newmob.getUniqueId());
			
		}

		else if(factor%3 == 2) {
			for(int i =0; i <19; i++) {

		    	Random random=new Random();
		    	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
		    	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
		    	Location esl = spl.clone().add(number, -20, number2);
		    	ItemStack head = new ItemStack(Material.DARK_PRISMARINE);
				ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
				ItemMeta chm = (ItemMeta) chest.getItemMeta();
				chest.setItemMeta(chm);
				chest.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
				chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
				ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS);
				ItemMeta lem = (ItemMeta) leg.getItemMeta();
				leg.setItemMeta(lem);
				leg.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
				leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
				ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
				ItemMeta bom = (ItemMeta) boots.getItemMeta();
				boots.setItemMeta(bom);
				boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
				boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
				ItemStack main = new ItemStack(Material.DIAMOND_AXE);
				ItemStack off = new ItemStack(Material.SHIELD);
				String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "잠식된 바다기사":"Encroached OceanKnight";
				Drowned newmob = (Drowned) MobspawnLoc(esl, ChatColor.DARK_BLUE+reg, 2500.0, head, chest, leg, boots, main, off, EntityType.DROWNED);
				newmob.setAdult();
				newmob.setConversionTime(-1);
				newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
				newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
				newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
				newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), p.getName()));
				newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
				newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
				newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
				newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
				newmob.setRemoveWhenFarAway(false);
				newmob.setLootTable(null);
	    		raider.put(p.getName(), newmob.getUniqueId());
			}

        	Random random=new Random();
        	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
        	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
        	Location esl = spl.clone().add(number, -20, number2);
        	ItemStack head = new ItemStack(Material.DARK_PRISMARINE);
			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
			chm.setColor(Color.NAVY);
			chest.setItemMeta(chm);
			chest.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
			lem.setColor(Color.NAVY);
			leg.setItemMeta(lem);
			leg.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
			bom.setColor(Color.BLACK);
			boots.setItemMeta(bom);
			boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    		ItemStack main = new ItemStack(Material.NETHERITE_SWORD);
    		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "잠식된 가디언":"Encroached Guardian";
    		Guardian newmob = (Guardian) MobspawnLoc(esl, ChatColor.DARK_BLUE+reg, 3000.0, head, chest, leg, boots, main, null, EntityType.GUARDIAN);
    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), p.getName()));
			newmob.setMetadata("leader", new FixedMetadataValue(RMain.getInstance(),true));
    		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(false);
			newmob.setGlowing(true);
    		newmob.setLootTable(null);
			raider.get(p.getName()).forEach(le -> leader.put(newmob.getUniqueId(), le));
			raider.put(p.getName(), newmob.getUniqueId());
			
		}
		else {
			for(int i =0; i <19; i++) {

	        	Random random=new Random();
	        	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	        	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	        	Location esl = spl.clone().add(number, -20, number2);
	    		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "잠식된 바다망령":"Encroached OceanPhantom";
	    		Phantom newmob = (Phantom) MobspawnLoc(esl, ChatColor.DARK_BLUE+reg, 3000.0, null, null, null, null, null, null, EntityType.PHANTOM);
	    		newmob.setSize(5);
	    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
	    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
	    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), p.getName()));
	    		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    		newmob.setRemoveWhenFarAway(false);
	    		newmob.setLootTable(null);
	    		raider.put(p.getName(), newmob.getUniqueId());
			}

	    	Random random=new Random();
	    	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	    	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	    	Location esl = spl.clone().add(number, -20, number2);
	    	ItemStack head = new ItemStack(Material.BLACK_SHULKER_BOX);
			ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
			ItemMeta chm = (ItemMeta) chest.getItemMeta();
			chest.setItemMeta(chm);
			chest.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
			ItemMeta lem = (ItemMeta) leg.getItemMeta();
			leg.setItemMeta(lem);
			leg.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
			ItemMeta bom = (ItemMeta) boots.getItemMeta();
			boots.setItemMeta(bom);
			boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
			ItemStack main = new ItemStack(Material.CHEST);
			ItemStack off = new ItemStack(Material.CHEST);
			String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "잠식된 미믹":"Encroached Mimic";
			Drowned newmob = (Drowned) MobspawnLoc(esl, ChatColor.DARK_BLUE+reg, 3600.0, head, chest, leg, boots, main, off, EntityType.DROWNED);
			newmob.setAdult();
			newmob.setConversionTime(-1);
			newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
			newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
			newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
			newmob.setGlowing(true);
			newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), p.getName()));
			newmob.setMetadata("leader", new FixedMetadataValue(RMain.getInstance(),true));
			newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
			newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
			newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
			newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setRemoveWhenFarAway(false);
			newmob.setLootTable(null);
			raider.get(p.getName()).forEach(le -> leader.put(newmob.getUniqueId(), le));
			raider.put(p.getName(), newmob.getUniqueId());
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
	public void ElderGuardianRaidStart(PlayerInteractEvent d) 
	{	
		if(d.getClickedBlock() != null && d.getClickedBlock().hasMetadata("ElderGuardianRaidPortal") && d.getAction() == Action.RIGHT_CLICK_BLOCK && d.getAction() != Action.LEFT_CLICK_BLOCK) {

				Player p = (Player) d.getPlayer();
				d.setCancelled(true);
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
			            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("엘더 가디언 침공작전 입장까지 " + timer + "초 기다려야 됩니다").create());
						}
						else {
			            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("You have to wait for " + timer + " seconds to Start ElderGuardian Raid").create());
						}
		            	return;
		            }
		            else { 
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("이제 엘더가디언 침공작전에 입장하실수 있습니다").create());
						}
						else {
			            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("Now You Can Start ElderGuardianRaid").create());
						}
		            	raidcool.remove(p.getUniqueId());
		            }
				}
	    		World rw = Bukkit.getServer().getWorld("ElderGuardianRaid");
	    		int fix = p.getEntityId()*50-29999900;
	    		int fiz = p.getEntityId()*50-29999900;
	    		if(fix >= 29999900) {
	    			fix = fix - 29999900*2;
	    			fiz = 29999900*2 - fiz;
	    		}
	        	p.setCooldown(Material.RAIL, 100);
	    		Location spl = rw.getHighestBlockAt(fix, fiz).getLocation().clone().add(0, 1, 0);
	    		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @SuppressWarnings("deprecation")
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
	    				        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GRAY + "작전이 시작됩니다", "아홀로틀을 보호하고 억제기를 파괴하십시오", 5, 69, 5);
	    				        		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + "억제기는 몬스터 처치시 내구도가 깎입니다.");
	    				        		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + "리더몹을 처치하면 [저주받은 비늘]이 드랍됩니다. [저주받은 비늘]을 억제기 주변에다 투척하면 억제기의 체력이 대폭 깎입니다.");
    								}
    								else {
	    				        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GRAY + "Raid Will Start", "Protect Axolotl and Destroy the Inhibitor", 5, 69, 5);
	    				        		Bukkit.getPlayer(pu).sendMessage(ChatColor.GRAY + "Inhibitor's Durabity will Decrease When You Kill the Monsters");
	    				        		Bukkit.getPlayer(pu).sendMessage(ChatColor.GRAY + "[Cursed Shard] Drops From Leader Monster.");
	    				        		Bukkit.getPlayer(pu).sendMessage(ChatColor.GRAY + "If You drop [Cursed Shard] near by the Inhibitor, Durabity will decrease significantly.");
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
				        		p.sendTitle(ChatColor.GRAY + "작전이 시작됩니다", "아홀로틀을 보호하고 억제기를 파괴하십시오", 5, 69, 5);
				        		p.sendMessage(ChatColor.BOLD + "억제기는 몬스터 처치시 내구도가 깎입니다.");
				        		p.sendMessage(ChatColor.BOLD + "일정확률로 몹한테서 [저주받은 비늘]이 드랍됩니다. [저주받은 비늘]을 억제기 주변에다 투척하면 억제기의 체력이 대폭 깎입니다.");
							}
							else {
				        		p.sendTitle(ChatColor.GRAY + "Raid Will Start", "Protect Axolotl and Destroy the Inhibitor", 5, 69, 5);
				        		p.sendMessage(ChatColor.GRAY + "Inhibitor's Durabity will Decrease When You Kill the Monsters");
				        		p.sendMessage(ChatColor.GRAY + "Sometimes [Cursed Shard] Drops From the Monster.");
				        		p.sendMessage(ChatColor.GRAY + "If You drop [Cursed Shard] near by the Inhibitor, Durabity will decrease significantly.");
							}
    		        		p.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
    					}
	    					d.getClickedBlock().setType(Material.VOID_AIR);
	    		        	p.setCooldown(Material.RAIL, 100);
	    	        		ArmorStand portal = (ArmorStand) spl.getWorld().spawn(spl, ArmorStand.class);
	    	        		portal.setMetadata("portal", new FixedMetadataValue(RMain.getInstance(), true));
	    	        		portal.setMetadata("ElderGuardianRaidExit", new FixedMetadataValue(RMain.getInstance(), true));
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
	    	        		inhibitorhp.put(p.getName(), 200);
	    	        		
	        	    		lives.put(p.getName(), 5);
	        	    		Axolotl v = (Axolotl) spl.getWorld().spawn(spl.clone().add(1,1,0), Axolotl.class);
	    	        		v.setMetadata("raidvil", new FixedMetadataValue(RMain.getInstance(), p.getName()));
	    	        		v.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), p.getName()));
	    	        		v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
	    	        		v.setAdult();
	    	        		v.setAgeLock(true);
	    	        		v.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
	    	        		v.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	        		v.setGravity(true);
	    	        		v.setNoDamageTicks(0);
	    	        		v.setVariant(Variant.BLUE);
	    	        		v.setMaxHealth(4000);
	    	        		v.setHealth(4000);
	    	        		v.setRemoveWhenFarAway(false);
	    	        		v.setGlowing(true);
	    	        		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? p.getName() + " 아홀로틀":p.getName() + "'s Axolotl";
	    	        		v.setCustomName(reg);
	    	        		v.setCustomNameVisible(true);
	    	        		timeout.put(p.getName(), 420);
	    	        		vil.put(p.getName(), v.getUniqueId());
	    	        		
	    	        		
	    	                int rat = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    		                @Override
	    		                public void run() {


	    		                	heroes.get(p.getName()).forEach(pu -> {
	    		                		Player pa = Bukkit.getPlayer(pu);
	    								if(pa.getLocale().equalsIgnoreCase("ko_kr")) {
		    		                		pa.sendTitle(ChatColor.GRAY + "작전 시작", null, 5, 69, 5);
		    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
		    		                		pa.sendMessage("남은 목숨: " +ChatColor.BOLD + String.valueOf(lives.get(p.getName())));
	    								}
	    								else {
		    		                		pa.sendTitle(ChatColor.GRAY + "Raid Starts", null, 5, 69, 5);
		    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
		    		                		pa.sendMessage(ChatColor.BOLD + String.valueOf(lives.get(p.getName())) + "lives Left");
	    								}
	    		                	});
	    		        			String w = p.getLocale().equalsIgnoreCase("ko_kr") ? "억제기 내구도 ":"Inhibitor Durabity";
	    	        	    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), p.getName()+"ElderGuardianRaid"),w, BarColor.BLUE, BarStyle.SEGMENTED_20, BarFlag.PLAY_BOSS_MUSIC);
	    	        	            newbar.setVisible(true);
	    	        	    		raidbar.put(p.getName(), newbar);
	    	        	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	    	        	                @Override
	    	        	                public void run() 
	    	        	                {
	    	        	            		raidbar.get(p.getName()).setProgress((double)inhibitorhp.get(p.getName()) / 200d);
	    	        	            		raidbar.get(p.getName()).setTitle(w + (double)inhibitorhp.get(p.getName()) + "/" + 200);
	    	        	            		heroes.get(p.getName()).forEach(pu -> {
	    	    	            				Player p1 = (Player) Bukkit.getPlayer(pu);
	    	    	            				if(p1 != null) {
	    	    	            					raidbar.get(p.getName()).addPlayer(p1);
	    	    	            				}
	    	        	            		});
	    	        	                }
	    	        				}, 0, 1);
	    	        	    		raidbart.put(p.getName(), task);

	    	        	    		AtomicInteger j = new AtomicInteger();
	    	        	    		int spt = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	    	        	                @Override
	    	        	                public void run() 
	    	        	                {
	    	        	            		MobSpawn(Holding.ale(inhb).getLocation(), p, j.incrementAndGet());
	    	    		                	heroes.get(p.getName()).forEach(pu -> {
	    	    		                		Player pa = Bukkit.getPlayer(pu);
	    	    								if(pa.getLocale().equalsIgnoreCase("ko_kr")) {
	    		    		                		pa.sendTitle(ChatColor.GRAY + "적 출현", "30초뒤에 다음 방어소대가 출현합니다", 5, 69, 5);
	    		    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
	    		    		                		pa.sendMessage("남은 목숨: " +ChatColor.BOLD + String.valueOf(lives.get(p.getName())));
	    	    								}
	    	    								else {
	    		    		                		pa.sendTitle(ChatColor.GRAY + "Enemies appear.", "Next will appear after 30seconds", 5, 69, 5);
	    		    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
	    		    		                		pa.sendMessage(ChatColor.BOLD + String.valueOf(lives.get(p.getName())) + "lives Left");
	    	    								}
	    	    		                	});
	    	        	                }
	    	        				}, 20, 600);
	    	        	    		spawnt.put(p.getName(), spt);

	    	        	    		targeting(p.getName());
	    	        	    		
	    		        			String t = p.getLocale().equalsIgnoreCase("ko_kr") ? "남은시간 - ":"TimeLeft - ";
	    	        	    		BossBar	timeb = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), p.getName()+"ElderGuardianRaidTime"),t + String.valueOf((int)timeout.get(p.getName())/20/60) + ":" + String.valueOf((int)(timeout.get(p.getName())/20)%60), BarColor.WHITE, BarStyle.SEGMENTED_6);
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
	public void Inhibitordamage(EntityDeathEvent d) 
	{	

		if(raider.containsValue(d.getEntity().getUniqueId()) &&d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("ocean") && !d.getEntity().hasMetadata("boss")) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			inhibitorhp.computeIfPresent(rn, (k,v) -> v-1);
			if(inhibitorhp.getOrDefault(rn, 1)<=0) {
				Bukkit.getPluginManager().callEvent(new EntityDeathEvent((LivingEntity) Bukkit.getEntity(inhibitor.get(rn)) , null));
				return;
			}
		}
	}
	
	public void ElderGuardianRaidBoss(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("inhibitor") && inhibitor.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("inhibitor").get(0).asString();
			Location spl = le.getLocation();
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
			Bukkit.getServer().getScheduler().cancelTask(spawnt.get(rn));
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
			if(spawnt.containsKey(rn)) {
				Bukkit.getServer().getScheduler().cancelTask(spawnt.get(rn));
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
	        		ElderGuardian newmob = (ElderGuardian) MobspawnLoc(esl, ChatColor.BLUE+reg, 10000.0, null, null, null, null, null, null, EntityType.ELDER_GUARDIAN);
    	    		newmob.setGlowing(true);
    	    		newmob.getEquipment().setBootsDropChance(0);
    	    		newmob.getEquipment().setChestplateDropChance(0);
    	    		newmob.getEquipment().setHelmetDropChance(0);
    	    		newmob.getEquipment().setItemInMainHandDropChance(0);
    	    		newmob.getEquipment().setItemInOffHandDropChance(0);
    	    		newmob.getEquipment().setLeggingsDropChance(0);
    	    		newmob.setMetadata("oceanboss", new FixedMetadataValue(RMain.getInstance(), true));
    	    		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
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
        	                	raidbar.get(rn).setProgress(Holding.ale(newmob).getHealth()/10000d);
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
		if(d.getEntity().hasMetadata("bosswave1") && raider.containsValue(d.getEntity().getUniqueId())) {
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
    	        		ElderGuardian newmob = (ElderGuardian) MobspawnLoc(esl, ChatColor.BLUE+reg, 13000.0, null, null, null, null, null, null, EntityType.ELDER_GUARDIAN);
        	    		newmob.setGlowing(true);
        	    		newmob.getEquipment().setBootsDropChance(0);
        	    		newmob.getEquipment().setChestplateDropChance(0);
        	    		newmob.getEquipment().setHelmetDropChance(0);
        	    		newmob.getEquipment().setItemInMainHandDropChance(0);
        	    		newmob.getEquipment().setItemInOffHandDropChance(0);
        	    		newmob.getEquipment().setLeggingsDropChance(0);
        	    		newmob.setMetadata("oceanboss", new FixedMetadataValue(RMain.getInstance(), true));
        	    		newmob.setMetadata("ruined", new FixedMetadataValue(RMain.getInstance(), true));
        	    		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
        	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
        	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
        	    		newmob.setMetadata("bosswave2", new FixedMetadataValue(RMain.getInstance(), true));
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
            	                	raidbar.get(rn).setProgress(Holding.ale(newmob).getHealth()/13000d);
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
	
	
	public void Victory(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("bosswave2") && raider.containsValue(d.getEntity().getUniqueId())) {
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
	
	
	public void ElderGuardianRaidExit(EntityDamageByEntityEvent d) 
	{	
		if(d.getEntity().hasMetadata("ElderGuardianRaidExit")) {
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

	
	public void ElderGuardianRaidExit(PlayerJoinEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(!heroes.containsValue(p.getUniqueId()) && p.getWorld().getName().contains("Raid")) {
			p.teleport(p.getBedSpawnLocation());
		}
	}

	
	public void ElderGuardianRaidExit(PluginEnableEvent ev) 
	{
		List<World> worlds = Bukkit.getServer().getWorlds();
		worlds.forEach(w -> w.getPlayers().forEach(b -> {
				Player p = (Player) b;
				if(!heroes.containsValue(p.getUniqueId()) && p.getWorld().getName().contains("Raid")) {
					p.teleport(p.getBedSpawnLocation());
				}
		}));
	}
	
	public void ElderGuardianRaidExit(PluginDisableEvent ev) 
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

	
	public void LeaderKilled(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("leader") && leader.containsKey(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raid").get(0).asString();
			ItemStack sh = new ItemStack(Material.PRISMARINE_CRYSTALS);
			ItemMeta im = sh.getItemMeta();
			if(Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr")) {
				im.setDisplayName("저주받은 비늘");
				im.setLocalizedName(rn + "Cursed shard");
			}
			else {
				im.setDisplayName("Cursed shard");
				im.setLocalizedName(rn + "Cursed shard");
			}
			sh.setItemMeta(im);
			
			Item si =le.getWorld().dropItemNaturally(le.getLocation(),sh);
			si.setMetadata("cursedshard", new FixedMetadataValue(RMain.getInstance(), rn));
			si.setMetadata("cursedshard"+rn, new FixedMetadataValue(RMain.getInstance(), rn));
			si.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			si.setPickupDelay(0);
			
			if(raider.get(rn).size()>=1) {
	        	heroes.get(rn).forEach(pu -> {
	    			if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
		        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GOLD +"리더몹이 쓰러졌습니다!" , ChatColor.GOLD +"몬스터 방어력 감소, 파티장만 공격", 5, 40, 5);
	    			}
	    			else {
		        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GOLD +"Leader Fell down!" , ChatColor.GOLD +"Decrease Monsters' Armor & attack Owner", 5, 40, 5);
	    			}
	        	});
	        	leader.get(le.getUniqueId()).forEach(lee -> {
	        		if(Bukkit.getEntity(lee) != null){
		        		Bukkit.getEntity(lee).setMetadata("lostleader", new FixedMetadataValue(RMain.getInstance(), true));
		        		Bukkit.getEntity(lee).getWorld().spawnParticle(Particle.VILLAGER_ANGRY, Bukkit.getEntity(lee).getLocation(), 10, 1,1,1);
		        		Bukkit.getEntity(lee).setGlowing(true);
	        		}
	        	});
        		raider.get(rn).forEach(re -> {
        			if(Bukkit.getEntity(re) != null) {
            			Mob rm = (Mob)Bukkit.getEntity(re);
            			rm.setTarget(Bukkit.getPlayer(rn));
        			}
        		});
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
	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	            		raider.get(rn).forEach(re -> {
	            			if(Bukkit.getEntity(re) != null) {
	            				Mob rm = (Mob)Bukkit.getEntity(re);
	            				if(rm.getType() != EntityType.ELDER_GUARDIAN && rm.getType() != EntityType.GUARDIAN) {
	    	            			rm.setTarget((LivingEntity) Bukkit.getPlayer(rn));
	            				}
	            			}
	            		});
	                }
				}, 0, 1);
	    		leadert.put(rn, task);
			}
		}
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
		if(d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("ocean") && !d.isCancelled()) {

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
    	            			if(rm!=null && rm.getType() != EntityType.ELDER_GUARDIAN && rm.getType() != EntityType.GUARDIAN) {
    	            				rm.setTarget(p);
    	            			}
		            		});
	            			if(p.getEquipment().getItemInMainHand().getType() == Material.AIR || p.isDead()) {
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
		    	            			if(rm!=null && rm.getType() != EntityType.ELDER_GUARDIAN && rm.getType() != EntityType.GUARDIAN) {
		    	            				rm.setTarget(p);
		    	            			}
				            		});
			            			if(p.getEquipment().getItemInMainHand().getType() == Material.AIR || p.isDead()) {
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
		if(e.getTo().getWorld().getName().contains("ElderGuardianRaid")) {
			if(!heroes.containsValue(p.getUniqueId())) {
				e.setCancelled(true);
			}
		}
		if(e.getFrom().getWorld().getName().contains("ElderGuardianRaid") && !e.getTo().getWorld().getName().contains("ElderGuardianRaid") && !e.getTo().getWorld().getName().contains("FakeDimension")) {
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
}
