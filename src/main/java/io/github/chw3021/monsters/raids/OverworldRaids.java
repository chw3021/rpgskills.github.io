package io.github.chw3021.monsters.raids;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
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
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.items.Elements;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class OverworldRaids extends Summoned implements Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8118960217265734839L;
	private static HashMap<UUID, Location> beforepl = new HashMap<UUID, Location>();
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
	

	private HashMap<String, String> language = new HashMap<String, String>();
	
	Integer DelayTime =  100;
	Integer LIVES = 5;
	Double BOSSHP = 100000d;
	
	Integer BOSSNUM = 6;
	
	
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
		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("stuff"+rn)).forEach(e -> e.remove());

		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
		Bukkit.getWorld("OverworldRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightmagma"+rn)).forEach(e -> e.remove());

		
		ordt.get(rn).forEach(t -> Bukkit.getScheduler().cancelTask(t));
		ordt.removeAll(rn);
		
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
		
		Location spl = raidloc.get(rn).clone();
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
		
		language.remove(rn);
		
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
            	p.spawnParticle(Particle.ANGRY_VILLAGER, spl, 1000,6,6,6);
    		}
    		else {
    			if(RaidDifficulties.getMaxDifficulty(p, RaidCategory.OVERWORLD) <= difen.get(rn)) {
        			RaidDifficulties.saver(p, RaidCategory.OVERWORLD, difen.get(rn)+2);
    			}
    			
        		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "승리":"Victory!";
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
					final double pr = Holding.ale(newmob).getHealth()/Holding.ale(newmob).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
					if(pr>=0 && pr<=1) {
	                	raidbar.get(rn).setProgress(pr);
					}
					else if(pr>1){
	                	raidbar.get(rn).setProgress(1d);
					}
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
	
	final private LivingEntity bossgen(Location spl, Player pm, String rn, Integer in, Double dif) {
		
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	for(;!esl.getBlock().isPassable(); esl.add(1, 0, 0));
    	
		targeting(rn);
		double armor = 1;
		double dif1 = dif;
		
		final Double maxh = Bukkit.spigot().getConfig().getDouble("settings.attribute.maxHealth.max");
		if(dif1 >= maxh) {
			dif1 = maxh;
			armor = (dif-maxh)/BOSSHP;
		}
		
		
		//Double dif = 100000 * BigDecimal.valueOf(1 + 0.1*RaidDifficulties.getPlayerDifficulty(p, RaidCategory.OVERWORLD)).setScale(1, RoundingMode.HALF_EVEN).doubleValue();

		
		if(in == 0) {
			ItemStack main = new ItemStack(Material.STONE);
			main.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
			main.addUnsafeEnchantment(Enchantment.WIND_BURST, 3);
			ItemStack off = new ItemStack(Material.STONE);
			off.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
    		String reg = pm.getLocale().equalsIgnoreCase("ko_kr") ? "스톤골렘":"StoneGolem";
			IronGolem newmob = (IronGolem) MobspawnLoc(esl, ChatColor.GRAY + reg, dif1, null,
					null, null, null, main, off, EntityType.IRON_GOLEM);
			newmob.setGlowing(true);
			newmob.setPlayerCreated(false);
    		

    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		newmob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4);
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
			newmob.setMetadata("stoneboss", new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), armor));
			newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
			
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		bossbargen("stoneboss", rn, newmob);

    		return newmob;
		}
		else if(in == 1) {
			ItemStack head = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
			head.setItemMeta(hem);
			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
			chm.setColor(Color.RED);
			chest.setItemMeta(chm);
			chest.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
			chest.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
			lem.setColor(Color.RED);
			leg.setItemMeta(lem);
			leg.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
			leg.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
			bom.setColor(Color.WHITE);
			boots.setItemMeta(bom);
			boots.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
			boots.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
    		ItemStack main = new ItemStack(Material.STICK);
    		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
    		ItemStack off = new ItemStack(Material.ICE);
    		off.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
    		String reg = pm.getLocale().equalsIgnoreCase("ko_kr") ? "설산마녀":"SnowWitch";
    		Witch newmob = (Witch) MobspawnLoc(esl, ChatColor.BLUE+reg, dif1, null, null, null, boots, main, off, EntityType.WITCH);
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
    		
    		newmob.setMetadata("snowyboss", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), armor));
    		
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());

    		bossbargen("snowboss", rn, newmob);


    		return newmob;
		}
		else if(in == 2) {
    		ArmorStand inhb = (ArmorStand) spl.getWorld().spawn(spl.clone().add(20, 0,20), ArmorStand.class);
    		inhb.setMetadata("portal", new FixedMetadataValue(RMain.getInstance(), true));
    		inhb.setMetadata("inhibitor", new FixedMetadataValue(RMain.getInstance(), rn));
    		inhb.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		inhb.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), armor));
    		inhb.setRemoveWhenFarAway(false);
    		inhb.setGlowing(true);
    		inhb.getEquipment().setHelmet(new ItemStack(Material.BEACON));
    		inhb.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(dif1);;
			if(pm.getLocale().equalsIgnoreCase("ko_kr")) {
        		inhb.setCustomName(rn + " 엘더가디언 억제기");
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
		else if(in ==3) {

			ItemStack pe = new ItemStack(Material.BLACK_BANNER);
			BannerMeta pem = (BannerMeta) pe.getItemMeta();
			pem.addPattern(new Pattern(DyeColor.BLACK, PatternType.BASE));
			pem.addPattern(new Pattern(DyeColor.GRAY, PatternType.SMALL_STRIPES));
			pem.addPattern(new Pattern(DyeColor.BLACK, PatternType.CROSS));
			pem.addPattern(new Pattern(DyeColor.LIGHT_GRAY, PatternType.TRIANGLES_TOP));
			pem.addPattern(new Pattern(DyeColor.LIGHT_GRAY, PatternType.TRIANGLES_BOTTOM));
			pem.addPattern(new Pattern(DyeColor.BLACK, PatternType.BORDER));
			pem.addPattern(new Pattern(DyeColor.YELLOW, PatternType.MOJANG));
			pe.setItemMeta(pem);
			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
			chm.setColor(Color.BLACK);
			chest.setItemMeta(chm);
			chest.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
			chest.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
			lem.setColor(Color.BLACK);
			leg.setItemMeta(lem);
			leg.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
			leg.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
			bom.setColor(Color.BLACK);
			boots.setItemMeta(bom);
			boots.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
			boots.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
			ItemStack main = new ItemStack(Material.BOW);
			ItemMeta mm = main.getItemMeta();
			mm.setCustomModelData(4008);
			main.setItemMeta(mm);
			
    		String reg = pm.getLocale().equalsIgnoreCase("ko_kr") ? "밤의군단장":"NightCorpsCommander";
    		Skeleton newmob = (Skeleton) MobspawnLoc(esl, ChatColor.GRAY+reg, dif1, pe, chest, leg, boots, main, null, EntityType.SKELETON);

    		newmob.setConversionTime(-1);
    		
    		newmob.setGlowing(true);
    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		newmob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4);
    		
    		newmob.setMetadata("darkboss", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), armor));
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("dark", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
    		

    		bossbargen("darkboss", rn, newmob);
    		

    		final Object ht = getherotype(rn);

    		ItemStack mainf = new ItemStack(Material.NETHERITE_HOE);
    		ItemMeta mmf = mainf.getItemMeta();
    		mmf.setCustomModelData(8008);
    		mainf.setItemMeta(mmf);

    		Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
    		    @Override
    		    public void run() {
    				if(ht instanceof Player) {
    					Player p = (Player) ht;
    					p.sendEquipmentChange(newmob, EquipmentSlot.HAND, mainf);
    				}
    				else if(getherotype(rn) instanceof HashSet){
    					@SuppressWarnings("unchecked")
    					HashSet<Player> par = (HashSet<Player>) ht;
    		    		par.forEach(p -> {
    		    			p.sendEquipmentChange(newmob, EquipmentSlot.HAND, mainf);
    		    		});
    				}
    		    }
    		}, 2L); 

    		return newmob;
		}
		else if(in ==4) {
			ItemStack main = new ItemStack(Material.BOW);
			ItemMeta mm = main.getItemMeta();
			mm.setCustomModelData(2009);
			main.setItemMeta(mm);

    		String reg = pm.getLocale().equalsIgnoreCase("ko_kr") ? "닥터B":"Dr.B";
    		Illusioner newmob = (Illusioner) MobspawnLoc(esl, ChatColor.DARK_PURPLE+reg, dif1, new ItemStack(Material.BLACK_STAINED_GLASS), null, null, null, main, null, EntityType.ILLUSIONER);
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

    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), armor));
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
    		

    		bossbargen("hyperboss", rn, newmob);

    		return newmob;
		}
		else if(in == 5) {
    		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
			hem.setLore(Arrays.asList("RedKnight Helmet"));
			hem.setCustomModelData(100);
			head.setItemMeta(hem);
			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
			chm.setColor(Color.RED);
			chest.setItemMeta(chm);
			chest.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
			chest.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
			lem.setColor(Color.RED);
			leg.setItemMeta(lem);
			leg.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
			leg.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
			bom.setColor(Color.RED);
			boots.setItemMeta(bom);
			boots.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
			boots.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
			boots.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
			boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER,5);
			
			ItemStack main = new ItemStack(Material.BOW);//Bow
			ItemMeta mm = main.getItemMeta();
			mm.setCustomModelData(2010);
			main.setItemMeta(mm);
			
    		ItemStack off = new ItemStack(Material.SHIELD);
    		off.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
    		off.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
    		String reg = pm.getLocale().equalsIgnoreCase("ko_kr") ? "붉은기사":"RedKnight";
    		Skeleton newmob = (Skeleton) MobspawnLoc(esl, ChatColor.RED+reg, dif1, head, chest, leg, boots, main, off, EntityType.SKELETON);
    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    		newmob.setGlowing(true);
    		newmob.getEquipment().setBootsDropChance(0);
    		newmob.getEquipment().setChestplateDropChance(0);
    		newmob.getEquipment().setHelmetDropChance(0);
    		newmob.getEquipment().setItemInMainHandDropChance(0);
    		newmob.getEquipment().setItemInOffHandDropChance(0);
    		newmob.getEquipment().setLeggingsDropChance(0);
    		newmob.setMetadata("redboss", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), armor));
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		
    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
    		

    		bossbargen("redboss", rn, newmob);
    		
    		final Object ht = getherotype(rn);

    		ItemStack mainf = new ItemStack(Material.NETHERITE_AXE);
    		ItemMeta offm = mainf.getItemMeta();
    		offm.setCustomModelData(4010);
    		mainf.setItemMeta(offm);
    		mainf.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
    		

    		Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
    		    @Override
    		    public void run() {
    				if(ht instanceof Player) {
    					Player p = (Player) ht;
    					p.sendEquipmentChange(newmob, EquipmentSlot.HAND, mainf);
    				}
    				else if(getherotype(rn) instanceof HashSet){
    					@SuppressWarnings("unchecked")
    					HashSet<Player> par = (HashSet<Player>) ht;
    		    		par.forEach(p -> {
    		    			p.sendEquipmentChange(newmob, EquipmentSlot.HAND, mainf);
    		    		});
    				}
    		    }
    		}, 2L); 

    		return newmob;
		}
		else {

			ItemStack pe = new ItemStack(Material.NETHERITE_HELMET);
			ItemMeta im = pe.getItemMeta();
			im.setLore(Arrays.asList("Poison Boss"));
			pe.setItemMeta(im);
			ItemStack pe1 = new ItemStack(Material.NETHERITE_CHESTPLATE);
			ItemMeta im1 = pe1.getItemMeta();
			im1.setLore(Arrays.asList("Poison Boss"));
			pe1.setItemMeta(im1);
			pe1.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
			pe1.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
			pe1.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
			ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
			ItemStack pe11 = new ItemStack(Material.NETHERITE_BOOTS);
			ItemMeta im11 = pe11.getItemMeta();
			im11.setLore(Arrays.asList("Poison Boss"));
			pe11.setItemMeta(im11);
			ItemStack main = new ItemStack(Material.BOW);
			ItemMeta mm = main.getItemMeta();
			mm.setCustomModelData(2011);
			main.setItemMeta(mm);
			
    		String reg = pm.getLocale().equalsIgnoreCase("ko_kr") ? "테러리스트":"Terrorist";
    		Skeleton newmob = (Skeleton) MobspawnLoc(esl, ChatColor.DARK_GREEN+reg, dif1, pe, pe1, leg, pe11, main, null, EntityType.SKELETON);

    		newmob.setConversionTime(-1);
    		
    		newmob.setGlowing(true);
    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		newmob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4);
    		
    		newmob.setMetadata("poisonboss", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), armor));
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setMetadata("poison", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
    		
    		newmob.setRemoveWhenFarAway(false);
    		raider.put(rn, newmob.getUniqueId());
    		

    		bossbargen("darkboss", rn, newmob);

    		return newmob;
		}
		
	}
	
	final private void bossphase2(LivingEntity le) {
		String rn = le.getMetadata("raid").get(0).asString();
		raider.remove(rn, le.getUniqueId());
		Bukkit.getServer().getScheduler().cancelTask(raidbart.get(rn));
		Bukkit.getServer().getScheduler().cancelTask(raidt.get(rn));
		BossBar	newbar = raidbar.get(rn);
		newbar.setVisible(false);
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
        
		return;
	}

	
	
	@EventHandler
	public void Escape(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();
		if(heroes.containsValue(p.getUniqueId())) {
	
			if(Party.hasParty(p)) {
				if(Party.isOwner(p)) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						OverworldRaidFinish(getheroname(p), "탈주","파티장이 떠났습니다",0);
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
					OverworldRaidFinish(getheroname(p), "탈주","파티장이 떠났습니다",0);
				}
				else {
					OverworldRaidFinish(getheroname(p), "Escaped","Party Owner Left",0);
				}
			}
			
		}
	}
	
	final private void playerTP(Player p, Location spl, String rn) {
		int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
        		final Location pl = p.getLocation();
        		beforepl.put(p.getUniqueId(), pl);
        		Holding.invur(p, 100l);
        		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20,1,false,false));
        		p.teleport(spl.clone().add(0,0.5,0));
        		Holding.invur(p, 100l);
            }
		}, 25); 
		ordt.put(rn, task);
	}

	final private void diffGetter(Player p, String rn) {
		int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
        		if(Party.hasParty(p)) {
        			if(Party.isOwner(p)) {
        				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "난이도를 입력하세요 (최소: 0, 최대: "+RaidDifficulties.getMaxDifficulty(p, RaidCategory.OVERWORLD)+")").create());
        	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "5초안에 미입력 또는 올바른 입력이 아닐 시 도전 가능한 가장 높은 난이도로 자동 설정됩니다").create());
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
        						Bukkit.getPlayer(pu).spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "파티장이 난이도를 선택중입니다").create());
        					}
        					else {
        						Bukkit.getPlayer(pu).spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "Owner is selecting difficulty now").create());
        					}
        				});
        			}
        		}
        		else {
        			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
                    	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.GOLD + "난이도를 입력하세요 (최소: 0, 최대: "+RaidDifficulties.getMaxDifficulty(p, RaidCategory.OVERWORLD)+")").create());
                    	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BLUE + "5초안에 미입력 또는 올바른 입력이 아닐 시 도전 가능한 가장 높은 난이도로 자동 설정됩니다").create());
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
		}, 70); 
		ordt.put(rn, task);
	}
	
	@EventHandler
	public void OverworldRaidStart(PlayerInteractEvent d)
	{
		if(d.getClickedBlock() != null && d.getClickedBlock().hasMetadata("OverworldRaidPortal") && d.getAction() == Action.RIGHT_CLICK_BLOCK && d.getAction() != Action.LEFT_CLICK_BLOCK) {

			Player p = (Player) d.getPlayer();
			if(!p.getInventory().getItemInMainHand().getType().isAir() || heroes.containsValue(p.getUniqueId())|| raider.containsKey(getheroname(p)) || beforepl.containsKey(p.getUniqueId()) || p.hasCooldown(Material.RAIL)) {
				return;
			}
			if(raidcool.containsKey(p.getUniqueId())) {

	            double timer = (raidcool.get(p.getUniqueId())/1000 + 5) - System.currentTimeMillis()/1000; 
	            if(!(timer < 0))
	            {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("다음 보스 침공작전 입장까지 " + timer + "초 기다려야 됩니다").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("You have to wait for " + timer + " seconds to Start Next Boss Raid").create());
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
    		String rn = d.getClickedBlock().getMetadata("OverworldRaidPortal").get(0).asString();
    		World rw = Bukkit.getServer().getWorld("OverworldRaid");
    		int fix = p.getEntityId()*100-29999984;
    		int fiz = p.getEntityId()*100-29999984;
    		if(fix >= 29999984) {
    			fix = fix - 29999984*2;
    			fiz = 29999984*2 - fiz;
    		}
        	p.setCooldown(Material.RAIL, 100);
    		Location spl = rw.getHighestBlockAt(fix, fiz).getLocation().clone().add(0, 1, 0);
    		
    		
    		if(Party.hasParty(p)) {
				if(Party.isOwner(p)) {
					Party.getMembers(Party.getParty(p)).forEach(pu -> {
						if(Bukkit.getPlayer(pu).getInventory().firstEmpty() == -1) {
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("인벤토리에 빈칸이 없는 파티원이 있습니다").create());
							}
							else {
				            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("Players should empty inventory least one space").create());
							}
							return;
						}
		        		heroes.put(rn, pu);
		        		
		        		playerTP(Bukkit.getPlayer(pu), spl, rn);
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
				if(p.getInventory().firstEmpty() == -1) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("인벤토리에 빈칸이 없습니다").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("You should empty inventory least one space").create());
					}
					return;
				}
        		heroes.put(rn, p.getUniqueId());
        		
        		playerTP(p, spl, rn);
			}
    		RaidFinish(rn,"","",0);
			d.getClickedBlock().setType(Material.VOID_AIR);
    		ArmorStand portal = (ArmorStand) spl.getWorld().spawn(spl, ArmorStand.class);
    		portal.setMetadata("portal", new FixedMetadataValue(RMain.getInstance(), true));
    		portal.setMetadata("OverworldRaidExit", new FixedMetadataValue(RMain.getInstance(), true));
    		portal.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		portal.setRemoveWhenFarAway(false);
    		portal.setGlowing(true);
    		portal.setGravity(false);
    		
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		portal.setCustomName(rn + "파티의 출구 (웅크린상태에서 맨손으로 가격)");
        		language.put(rn, p.getLocale());
			}
			else {
        		portal.setCustomName(rn + "'s Party Exit (Sneaking + Hit with Fist)");
        		language.put(rn, p.getLocale());
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
    		v.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(20);
    		v.getAttribute(Attribute.GENERIC_OXYGEN_BONUS).setBaseValue(10000);
    		v.getAttribute(Attribute.GENERIC_GRAVITY).setBaseValue(10000);
    		v.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10000);
    		v.setHealth(10000);
    		v.setRemoveWhenFarAway(false);
    		v.setGlowing(true);
    		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? rn + " 고고학자":rn + "'s Archaeologist";
    		v.setVillagerType(Type.JUNGLE);
    		v.setProfession(Profession.LIBRARIAN);
    		v.setCustomName(reg);
    		v.setCustomNameVisible(true);
    		timeout.put(rn, 420);
    		vil.put(rn, v.getUniqueId());
    		
    		diffGetter(p,rn);
    		
		}
	}
	
	final private void OverworldRaidStart(Player p, String rn, Integer endif) {

		
		final Location spl = raidloc.get(rn).clone();

		if(Party.hasParty(p)) {
			if(Party.isOwner(p)) {
				Party.getMembers(Party.getParty(p)).forEach(pu -> {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        		Bukkit.getPlayer(pu).sendMessage(ChatColor.AQUA + "난이도가 "+endif+"로 설정 되었습니다");
					}
					else {
		        		Bukkit.getPlayer(pu).sendMessage(ChatColor.AQUA + "Difficulty level set to "+endif);
					}
				});
			}
		}
		else {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		p.sendMessage(ChatColor.AQUA + "난이도가 "+endif+"로 설정 되었습니다");
			}
			else {
        		p.sendMessage(ChatColor.AQUA + "Difficulty level set to "+endif);
			}
		}
		
		Double dif = BOSSHP * BigDecimal.valueOf(1 + 0.1*endif).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
		if(Party.hasParty(p)) {
			if(Party.isOwner(p)) {
				Party.getMembers(Party.getParty(p)).forEach(pu -> {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GRAY + "작전이 시작됩니다", "주민을 보호하고 적을 모두 섬멸하십시오", 5, 69, 5);
					}
					else {
		        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GRAY + "Raid Will Start", "Protect Villager & Sweep All Enemies", 5, 69, 5);
					}
				});
			}
		}
		else {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        		p.sendTitle(ChatColor.GRAY + "작전이 시작됩니다", "주민을 보호하고 적을 모두 섬멸하십시오", 5, 69, 5);
			}
			else {
        		p.sendTitle(ChatColor.GRAY + "Raid Will Start", "Protect Villager & Sweep All Enemies", 5, 69, 5);
			}
		}
		lives.put(rn, LIVES+heroes.get(rn).size());
		
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
	                    	
		                	/*
	                    	int ri = random.nextInt(7);
	                    	bossgen(esl,p, rn, ri,dif);*/
		                	
	                    	bossgen(esl,p, rn, BOSSNUM,dif);
	                    	
		                	heroes.get(rn).forEach(pu -> {
		                		Player pa = Bukkit.getPlayer(pu);
								if(pa.getLocale().equalsIgnoreCase("ko_kr")) {
    		                		pa.sendTitle(ChatColor.GRAY + "작전 시작", null, 5, 69, 5);
    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
    		                		pa.sendMessage("남은 목숨: " +ChatColor.BOLD + String.valueOf(lives.get(rn)));
								}
								else {
    		                		pa.sendTitle(ChatColor.GRAY + "Raid Start", null, 5, 69, 5);
    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
    		                		pa.sendMessage(ChatColor.BOLD + String.valueOf(lives.get(rn)) + "lives Left");
								}
		                	});

	        	    		targeting(rn);

		        			String t = p.getLocale().equalsIgnoreCase("ko_kr") ? "남은시간 - ":"TimeLeft - ";
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
    	        	            			OverworldRaidFinish(rn,"패배..", "시간초과",0);
	    								}
	    								else {
    	        	            			OverworldRaidFinish(rn,"Defeated..", "TimeOut",0);
	    								}
	        	                    	
	        	                    	spl.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, spl, 1000,6,6,6);
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

			if(!difent.containsKey(rn)) {
				return;
			}
			else {
				Bukkit.getScheduler().cancelTask(difent.remove(rn));
			}
			Integer endif = 0;
			d.setCancelled(true);
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
			Location lel = le.getLocation();
        	for(int i =0; i<30; i++) {
          	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
 		                @Override
 		                public void run() 
 		                {

 		        			le.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, lel, 300,1,1,1);
 		        			le.getWorld().spawnParticle(Particle.CRIMSON_SPORE, lel, 100,1,1,1);
 		        			le.getWorld().spawnParticle(Particle.REVERSE_PORTAL, lel, 300,1,1,1);
 		        			le.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, lel, 300,1,1,1);
 		        			le.getWorld().spawnParticle(Particle.FLAME, lel, 100,1,1,1);
 		        			le.getWorld().spawnParticle(Particle.SOUL, lel, 100,1,1,1);
 		                }
          	   }, i*3); 
 			}
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

			if(language.get(rn).equalsIgnoreCase("ko_kr")) {
				OverworldRaidFinish(rn, "패배..", "주민 보호 실패",0);
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

    			if(language.get(rn).equalsIgnoreCase("ko_kr")) {
    				OverworldRaidFinish(rn, "패배..", "모든 목숨 소진",0);
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
								OverworldRaidFinish(getheroname(p), "항복","",0);
			    			}
			    			else {
								OverworldRaidFinish(getheroname(p), "Surrender","",0);
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
							OverworldRaidFinish(getheroname(p), "항복","",0);
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
			p.teleport(p.getRespawnLocation(), TeleportCause.PLUGIN);
		}
	}


	@EventHandler
	public void OverworldRaidExit(PluginEnableEvent ev) 
	{
		List<World> worlds = Bukkit.getServer().getWorlds();
		worlds.forEach(w -> w.getPlayers().forEach(b -> {
				Player p = (Player) b;
				if(!heroes.containsValue(p.getUniqueId()) && p.getWorld().getName().contains("Raid")) {
					p.teleport(p.getRespawnLocation());
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
		if(d.getEntity().hasMetadata("raid") && d.getEntity().hasMetadata("boss") && !d.isCancelled()) {

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

			Holding.invur(p, 60l);
            Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
    				p.teleport(raidloc.get(getheroname(p)));
    				Holding.invur(p, 60l);
    				Holding.untouchable(p, 60l);
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
	public void StoneGolemBoss2(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("bosswave1") && d.getEntity().hasMetadata("stoneboss") && raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raid").get(0).asString();
			
			
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
				bossphase2(le);
				Location spl = raidloc.get(rn).clone();
	        	spl.getWorld().spawnParticle(Particle.BLOCK_MARKER, d.getEntity().getLocation(), 1000,1,1,1, Material.STONE.createBlockData());
	        	spl.getWorld().spawnParticle(Particle.BLOCK_MARKER, d.getEntity().getLocation(), 1000,1,1,1, Material.GRANITE.createBlockData());
	        	spl.getWorld().spawnParticle(Particle.CLOUD, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_IRON_GOLEM_REPAIR, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.BLOCK_STONE_BREAK, 1f, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_IRON_GOLEM_DEATH, 0.5f, 0);
	        	
	            int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	
	                	Location esl = d.getEntity().getLocation().clone().add(0,0.5, 0);
	
	    	    		
		        		String reg = language.get(rn).equalsIgnoreCase("ko_kr") ? "분노한골렘":"OutragedGolem";
		        		IronGolem newmob = (IronGolem) MobspawnLoc(esl, ChatColor.RED+reg, le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*1.2, null, null, null, null, null, null, EntityType.IRON_GOLEM);
		        		newmob.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999, 1, false, false));
	    	    		newmob.setGlowing(true);
	    	    		newmob.getEquipment().setBootsDropChance(0);
	    	    		newmob.getEquipment().setChestplateDropChance(0);
	    	    		newmob.getEquipment().setHelmetDropChance(0);
	    	    		newmob.getEquipment().setItemInMainHandDropChance(0);
	    	    		newmob.getEquipment().setItemInOffHandDropChance(0);
	    	    		newmob.getEquipment().setLeggingsDropChance(0);
	    	    		newmob.setMetadata("stoneboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("ruined", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), le.getMetadata("boss").get(0).asDouble()));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		
	    	    		
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		


	    	    		bossbargen("RagedStoneGolem", rn, newmob);
	    	    		
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
	    	    		
	                }
	            }, 50); 
	            raidt.put(rn, rat);
			}
		}
	}

	@EventHandler
	public void SnowWitchBoss2(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("bosswave1") && d.getEntity().hasMetadata("snowyboss") && raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raid").get(0).asString();
			
			
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
				bossphase2(le);
				Location spl = raidloc.get(rn).clone();
	        	spl.getWorld().spawnParticle(Particle.SOUL, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.SNOWFLAKE, d.getEntity().getLocation(), 500,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.WHITE_ASH, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_SKELETON_AMBIENT, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_PLAYER_HURT_FREEZE, 0.2f, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ITEM_BUCKET_FILL_POWDER_SNOW, 1, 0);
	        	
	            int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	
	                	Location esl = d.getEntity().getLocation().clone().add(0,0.5, 0);
	
	    	    		
		        		String reg = language.get(rn).equalsIgnoreCase("ko_kr") ? "차가운심장의마녀":"FrozenHeart";
		        		Witch newmob = (Witch) MobspawnLoc(esl, ChatColor.RED+reg, le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*1.2, null, null, null, null, null, null, EntityType.WITCH);
	    	    		newmob.setGlowing(true);
	    	    		newmob.getEquipment().setBootsDropChance(0);
	    	    		newmob.getEquipment().setChestplateDropChance(0);
	    	    		newmob.getEquipment().setHelmetDropChance(0);
	    	    		newmob.getEquipment().setItemInMainHandDropChance(0);
	    	    		newmob.getEquipment().setItemInOffHandDropChance(0);
	    	    		newmob.getEquipment().setLeggingsDropChance(0);
	    	    		newmob.setMetadata("snowyboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("ruined", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), le.getMetadata("boss").get(0).asDouble()));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		
	    	    		
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		
	
	
	    	    		bossbargen("FrozenHeart", rn, newmob);
	    	    		
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
	    	    		
	                }
	            }, 50); 
	            raidt.put(rn, rat);
			}
		}
	}

	@EventHandler
	public void Inhibitordamage(EntityDamageByEntityEvent d) 
	{	
	
		if(d.getEntity().hasMetadata("inhibitor") && inhibitor.containsValue(d.getEntity().getUniqueId())) {
			Entity le = d.getEntity();
			String rn = le.getMetadata("inhibitor").get(0).asString();
			Bukkit.getPluginManager().callEvent(new EntityDeathEvent((LivingEntity) Bukkit.getEntity(inhibitor.get(rn)), d.getDamageSource(), new ArrayList<ItemStack>()));
		}
	}

	@EventHandler
	public void ElderGuardianRaidBoss(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("inhibitor") && inhibitor.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("inhibitor").get(0).asString();
			Location spl = le.getLocation().clone().add(0, -70, 0);
			spl.setY(15);
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
	    			if(is != null && is.getItemMeta().hasItemName() && is.getItemMeta().getItemName().equals(rn + "Cursed shard"));
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
	    	Bukkit.getEntity(raidpor.get(rn)).teleport(spl.clone().add(1,1,1));
	        int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() {
	
	            	Random random=new Random();
	            	double number = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	            	double number2 = (random.nextDouble()+1.5) * 5 * (random.nextBoolean() ? -1 : 1);
	            	Location esl = spl.clone().add(number, -5, number2);
	
	        		String reg = lang.equalsIgnoreCase("ko_kr") ? "엘더가디언":"ElderGuardian";
	        		ElderGuardian newmob = (ElderGuardian) MobspawnLoc(esl, ChatColor.BLUE+reg, le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*1.2, null, null, null, null, null, null, EntityType.ELDER_GUARDIAN);
		    		newmob.setGlowing(true);
		    		newmob.getEquipment().setBootsDropChance(0);
		    		newmob.getEquipment().setChestplateDropChance(0);
		    		newmob.getEquipment().setHelmetDropChance(0);
		    		newmob.getEquipment().setItemInMainHandDropChance(0);
		    		newmob.getEquipment().setItemInOffHandDropChance(0);
		    		newmob.getEquipment().setLeggingsDropChance(0);
		    		newmob.setMetadata("oceanboss", new FixedMetadataValue(RMain.getInstance(), true));
	
		    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), le.getMetadata("boss").get(0).asDouble()));
		    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
		    		newmob.setMetadata("bosswave1", new FixedMetadataValue(RMain.getInstance(), true));
		    		
		    		
		    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		    		newmob.setRemoveWhenFarAway(false);
		    		raider.put(rn, newmob.getUniqueId());
		    		
	
		    		bossbargen("ElderGuardian", rn, newmob);
	        		
	            	heroes.get(rn).forEach(pu -> {
						if(Bukkit.getPlayer(pu).getLocale().equalsIgnoreCase("ko_kr")) {
	                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.GRAY + "보스 단계"), null, 5, 69, 5);
			        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
				    		Bukkit.getPlayer(pu).sendMessage("남은 목숨 "+ ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)));
				    		Bukkit.getPlayer(pu).teleport(spl.clone().add(0,2.5,0));
						}
						else {
	                		Bukkit.getPlayer(pu).sendTitle(ChatColor.BOLD+(ChatColor.GRAY + "BOSS WAVE"), null, 5, 69, 5);
			        		Bukkit.getPlayer(pu).playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
	                		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)) + "lives Left");
	        	    		Bukkit.getPlayer(pu).teleport(spl.clone().add(0,2.5,0));
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
			Location lel = le.getLocation().clone();
			lel.setY(20);
			
			String rn = le.getMetadata("raid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
				bossphase2(le);
				Location spl = raidloc.get(rn).clone();
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
	
	    	    		
		        		String reg = language.get(rn).equalsIgnoreCase("ko_kr") ? "잠식된 엘더가디언":"Encroached ElderGuardian";
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
	
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), le.getMetadata("boss").get(0).asDouble()));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		
	    	    		
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		
	
	    	    		bossbargen("EncroachedElderGuardian", rn, newmob);
	            		
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
	                }
	            }, 50); 
	            raidt.put(rn, rat);
			}
		}
	}

	@EventHandler
	public void NightBoss2(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("bosswave1") && d.getEntity().hasMetadata("darkboss") && raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
				bossphase2(le);
				Location spl = raidloc.get(rn).clone();
	        	spl.getWorld().spawnParticle(Particle.SOUL, spl, 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, spl, 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.WITCH, spl, 1000,1,1,1);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_PHANTOM_AMBIENT, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_SKELETON_CONVERTED_TO_STRAY, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_WITHER_DEATH, 0.1f, 0);
	            int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	

	        			ItemStack pe = new ItemStack(Material.BLACK_BANNER);
	        			BannerMeta pem = (BannerMeta) pe.getItemMeta();
	        			pem.addPattern(new Pattern(DyeColor.BLACK, PatternType.BASE));
	        			pem.addPattern(new Pattern(DyeColor.GRAY, PatternType.SMALL_STRIPES));
	        			pem.addPattern(new Pattern(DyeColor.BLACK, PatternType.CROSS));
	        			pem.addPattern(new Pattern(DyeColor.LIGHT_GRAY, PatternType.TRIANGLES_TOP));
	        			pem.addPattern(new Pattern(DyeColor.LIGHT_GRAY, PatternType.TRIANGLES_BOTTOM));
	        			pem.addPattern(new Pattern(DyeColor.BLACK, PatternType.BORDER));
	        			pem.addPattern(new Pattern(DyeColor.YELLOW, PatternType.MOJANG));
	        			pe.setItemMeta(pem);
	        			ItemStack main = new ItemStack(Material.BOW);
	        			ItemMeta mm = main.getItemMeta();
	        			mm.setCustomModelData(4008);
	        			main.setItemMeta(mm);

		        		String reg = language.get(rn).equalsIgnoreCase("ko_kr") ? "악몽의형상":"NightMare";
	        			
	            		Skeleton newmob = (Skeleton) MobspawnLoc(le.getLocation(), ChatColor.GRAY+reg, le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*1.2, pe, null, null, null, main, null, EntityType.SKELETON);
	            		
	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 999999, 1, false, false));
	    	    		
	    	    		newmob.setGlowing(true);
	    	    		newmob.setMetadata("darkboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("ruined", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("dark", new FixedMetadataValue(RMain.getInstance(), true));
	
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), le.getMetadata("boss").get(0).asDouble()));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		newmob.setConversionTime(-1);
	    	    		
	    	    		
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		
	
	
	    	    		bossbargen("NightMare", rn, newmob);

	    	    		final Object ht = getherotype(rn);

	    	    		ItemStack mainf = new ItemStack(Material.NETHERITE_HOE);
	    	    		ItemMeta mmf = mainf.getItemMeta();
	    	    		mmf.setCustomModelData(8008);
	    	    		mainf.setItemMeta(mmf);

	    	    		Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
	    	    		    @Override
	    	    		    public void run() {
	    	    				if(ht instanceof Player) {
	    	    					Player p = (Player) ht;
	    	    					p.sendEquipmentChange(newmob, EquipmentSlot.HAND, mainf);
	    	    				}
	    	    				else if(getherotype(rn) instanceof HashSet){
	    	    					@SuppressWarnings("unchecked")
	    	    					HashSet<Player> par = (HashSet<Player>) ht;
	    	    		    		par.forEach(p -> {
	    	    		    			p.sendEquipmentChange(newmob, EquipmentSlot.HAND, mainf);
	    	    		    		});
	    	    				}
	    	    		    }
	    	    		}, 2L); 
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
				bossphase2(le);
				Location spl = raidloc.get(rn).clone();
	        	spl.getWorld().spawnParticle(Particle.BLOCK_MARKER, d.getEntity().getLocation(), 1000,1,1,1, Material.BARRIER.createBlockData());
	        	spl.getWorld().spawnParticle(Particle.INSTANT_EFFECT, d.getEntity().getLocation(), 1000,1,1,1);
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
		        		String reg = language.get(rn).equalsIgnoreCase("ko_kr") ? "더비스트":"TheBeast";
		        		Husk newmob = (Husk) MobspawnLoc(le.getLocation().clone().add(0, 0.5, 0), ChatColor.DARK_PURPLE+reg, le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*1.2,  head, null, null, null, main, null, EntityType.HUSK);
	    	    		newmob.setGlowing(true);
	    	    		newmob.setMetadata("hyperboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("ruined", new FixedMetadataValue(RMain.getInstance(), true));
	
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), le.getMetadata("boss").get(0).asDouble()));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
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
				bossphase2(le);
				Location spl = raidloc.get(rn).clone();
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
	    				chest.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
	    				ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
	    				leg.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
	    				ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
	    				boots.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
	    				boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER,5);
	    				ItemStack main = new ItemStack(Material.BOW);//Bow
	    				ItemMeta mm = main.getItemMeta();
	    				mm.setCustomModelData(2010);
	    				main.setItemMeta(mm);
	    	    		ItemStack off = new ItemStack(Material.SHIELD);
	    	    		off.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		        		String reg = language.get(rn).equalsIgnoreCase("ko_kr") ? "몰락한 붉은기사":"Ruined RedKnight";
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
	    	    		
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), le.getMetadata("boss").get(0).asDouble()));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		
	    	    		
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		
	
	    	    		bossbargen("RuinedRedKnight", rn, newmob);

	    	    		final Object ht = getherotype(rn);

	    	    		ItemStack mainf = new ItemStack(Material.NETHERITE_AXE);
	    	    		ItemMeta offm = mainf.getItemMeta();
	    	    		offm.setCustomModelData(4010);
	    	    		mainf.setItemMeta(offm);
	    	    		mainf.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);

	    	    		Bukkit.getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
	    	    		    @Override
	    	    		    public void run() {
	    	    				if(ht instanceof Player) {
	    	    					Player p = (Player) ht;
	    	    					p.sendEquipmentChange(newmob, EquipmentSlot.HAND, mainf);
	    	    				}
	    	    				else if(getherotype(rn) instanceof HashSet){
	    	    					@SuppressWarnings("unchecked")
	    	    					HashSet<Player> par = (HashSet<Player>) ht;
	    	    		    		par.forEach(p -> {
	    	    		    			p.sendEquipmentChange(newmob, EquipmentSlot.HAND, mainf);
	    	    		    		});
	    	    				}
	    	    		    }
	    	    		}, 2L); 
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
	                }
	            }, 50); 
	            raidt.put(rn, rat);
			}
		}
	}
	

	@EventHandler
	public void PoisonBoss2(EntityDeathEvent d) 
	{	
		if(d.getEntity().hasMetadata("bosswave1") && d.getEntity().hasMetadata("poisonboss") && raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("raid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
				bossphase2(le);
				Location spl = raidloc.get(rn).clone();
	        	spl.getWorld().spawnParticle(Particle.BLOCK_MARKER, d.getEntity().getLocation(), 1000,1,1,1, Material.BARRIER.createBlockData());
	        	spl.getWorld().spawnParticle(Particle.INSTANT_EFFECT, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().spawnParticle(Particle.CLOUD, d.getEntity().getLocation(), 1000,1,1,1);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_GOAT_SCREAMING_AMBIENT, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_HUSK_CONVERTED_TO_ZOMBIE, 1, 0);
	        	spl.getWorld().playSound(d.getEntity().getLocation(), Sound.ENTITY_HUSK_AMBIENT, 1, 0);
	            int rat =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {
	
	                	ItemStack pe = new ItemStack(Material.NETHERITE_HELMET);
	        			ItemMeta im = pe.getItemMeta();
	        			im.setLore(Arrays.asList("Poison Boss2"));
	        			pe.setItemMeta(im);
	        			ItemStack pe1 = new ItemStack(Material.NETHERITE_CHESTPLATE);
	        			ItemMeta im1 = pe1.getItemMeta();
	        			im1.setLore(Arrays.asList("Poison Boss2"));
	        			pe1.setItemMeta(im1);
	        			pe1.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
	        			pe1.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
	        			pe1.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
	        			ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
	        			ItemStack pe11 = new ItemStack(Material.NETHERITE_BOOTS);
	        			ItemMeta im11 = pe11.getItemMeta();
	        			im11.setLore(Arrays.asList("Poison Boss2"));
	        			pe11.setItemMeta(im11);
	        			ItemStack main = new ItemStack(Material.BOW);
	        			ItemMeta mm = main.getItemMeta();
	        			mm.setCustomModelData(2011);
	        			main.setItemMeta(mm);
	        			
	            		String reg = language.get(rn).equalsIgnoreCase("ko_kr") ? "종말론자":"TheApocalyptic";
	            		Skeleton newmob = (Skeleton) MobspawnLoc(le.getLocation().clone().add(0, 0.5, 0), ChatColor.DARK_GREEN+reg, le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()*1.2, pe, pe1, leg, pe11, main, null, EntityType.SKELETON);
	            		
	    	    		newmob.setGlowing(true);
	    	    		newmob.setMetadata("poisonboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("ruined", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setMetadata("poison", new FixedMetadataValue(RMain.getInstance(), true));
	
	    	    		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), le.getMetadata("boss").get(0).asDouble()));
	    	    		newmob.setMetadata("raid", new FixedMetadataValue(RMain.getInstance(), rn));
	    	    		newmob.setMetadata("finalboss", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		
	    	    		newmob.setConversionTime(-1);
	    	    		
	    	    		
	    	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
	    	    		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
	    	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	    		newmob.setRemoveWhenFarAway(false);
	    	    		raider.put(rn, newmob.getUniqueId());
	    	    		
	
	
	    	    		bossbargen("TheApocalyptic", rn, newmob);
	    	    		
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
	                }
	            }, 50); 
	            raidt.put(rn, rat);
			}
		}
	}
}