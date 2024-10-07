package io.github.chw3021.monsters.dark;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;


public class DarkRaids extends Summoned {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6536664363548624595L;
	
	private static final DarkRaids instance = new DarkRaids ();
	public static DarkRaids getInstance()
	{
		return instance;
		
	}
	String META = "dark";
	
	
	public void DarkCombo(EntityDeathEvent d) 
	{	
		final Object com = Combo(d,META);
	
		if(com == null) {
			return;
		}
	
		final Location l = d.getEntity().getLocation().clone();
		
		if(com instanceof String) {
			String rn = (String) com;
			MobSpawn(l, rn);
		}
		else if(com instanceof HashSet) {
			@SuppressWarnings("unchecked")
			HashSet<String> hs = (HashSet<String>) com;
			hs.forEach(rn -> MobSpawn(l,rn));
		}
	}
	final protected void Boss(String rn, String meta, Location esl) {
		super.Boss(rn, meta, esl);
		if(meta.equals(META)) {
			Boss(esl,rn);
		}
	}
	final private void MobSpawn(final Location spl, final String rn) {

        for(int i = 0; i<SUMMONCOUNT; i++) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	if(combot.contains(rn, META)) {
            			Mob1(spl,rn);
            			Mob2(spl,rn);
            			Mob3(spl,rn);
            			Mob4(spl,rn);
            			Mob5(spl,rn);
            			Mob6(spl,rn);
            			Mob7(spl,rn);
                	}
                }
    	   }, i*20);
        }
		
	}
	
	final private ItemStack headflag() {

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
		
		return pe;
	}
	
	final private void Mob1(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = headflag();
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
		mm.setCustomModelData(2008);
		main.setItemMeta(mm);
		ItemStack off = new ItemStack(Material.OBSIDIAN);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "밤의군단궁수":"NightCorpsArcher";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 6000.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("NightArcher", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		newmob.setLootTable(null);
		addraider(rn,META,newmob);
	
	}
	final private void Mob2(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
		ItemStack head = headflag();
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
		ItemStack main = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(3008);
		main.setItemMeta(mm);

		ItemStack off = new ItemStack(Material.SHIELD);

		String reg = lang.equalsIgnoreCase("ko_kr") ? "밤의군단기사":"NightCorpsKnight";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 6500.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		newmob.setLootTable(null);
		
		addraider(rn,META,newmob);
		
		
	}
	final private void Mob3(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = headflag();
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
		ItemStack main = new ItemStack(Material.BLAZE_ROD);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(9008);
		main.setItemMeta(mm);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "밤의군단소환사":"NightCorpsEvoker";
		Evoker newmob = (Evoker) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 7000.0, head, chest, leg, boots, main, null, EntityType.EVOKER);

		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		newmob.setLootTable(null);
		addraider(rn,META,newmob);
		
	}
	final private void Mob4(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "밤의군단마녀":"NightCorpsWitch";
		Witch newmob = (Witch) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 6000.0, null, null, null, null, null, null, EntityType.WITCH);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("DarkWitch", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		newmob.setLootTable(null);
		addraider(rn,META,newmob);
		
	}
	final private void Mob5(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = headflag();
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
		ItemStack main = new ItemStack(Material.NETHERITE_AXE);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(4008);
		main.setItemMeta(mm);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "밤의군단사냥꾼":"NightCorpsHunter";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 5500.0, head, chest, leg, boots, main, null, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.setLootTable(null);
		
		addraider(rn,META,newmob);
	}
	final private void Mob6(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	
		ItemStack head = headflag();
		
		String reg = lang.contains("kr") ? "밤의망령":"NightPhantom";
		Phantom newmob = (Phantom) Summon(esl, reg, 6500.0, head, null, null, null, null,
				null, EntityType.PHANTOM);
		newmob.setFireTicks(0);
		newmob.setSize(6);
		newmob.setVisualFire(false);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 1, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1, false, false));
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		
		addraider(rn,META,newmob);
		
	}
	final private void Mob7(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = headflag();
    	
		ItemStack main = new ItemStack(Material.GLOBE_BANNER_PATTERN);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(1008);
		main.setItemMeta(mm);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "밤의군단돌격병":"NightCorpsAssault";
		Vindicator newmob = (Vindicator) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 7000.0, head, null, null, null, main, main, EntityType.VINDICATOR);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.setLootTable(null);
		addraider(rn,META,newmob);
		
	}
	@SuppressWarnings("unchecked")
	final private void Boss(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 1 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 1 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 1.1, number2);

    	ItemStack head = headflag();
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
		ItemStack main = new ItemStack(Material.NETHERITE_HOE);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(8008);
		main.setItemMeta(mm);

		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "밤의군단장":"NightCorpsCommander";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.DARK_GRAY + reg+ "<"+rn+">", 25000.0, head,
				chest, leg, boots, main, null, EntityType.SKELETON);

		newmob.setConversionTime(-1);
		newmob.setLootTable(null);
		newmob.setGlowing(true);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4);
		
		newmob.setMetadata(META+"boss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("summonedboss", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setRemoveWhenFarAway(false);
		
		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +"Nightmare"),newmob.getName(), BarColor.PURPLE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
        newbar.setVisible(true);
		raidbar.put(rn, META, newbar);
		
		addraider(rn,META,newmob);
		
	
		final Object ht = getherotype(rn);
	
		if(ht instanceof Player) {
			Player p = (Player) ht;
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	
					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/25000d);
	                	raidbar.get(rn, META).setTitle(Holding.ale(newmob).getName());
	    				raidbar.get(rn, META).addPlayer(p);
					}
	            }
			}, 0, 1);
			raidbart.put(rn, META, task);
		}
		else if(getherotype(rn) instanceof HashSet){
			HashSet<Player> par = (HashSet<Player>) ht;
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/25000d);
	                	raidbar.get(rn, META).setTitle(Holding.ale(newmob).getName());
	            		par.forEach(p -> {
	        				raidbar.get(rn, META).addPlayer(p);
	            		});
					}
	            }
			}, 0, 1);
			raidbart.put(rn, META, task);
		}
		
	}
	
	
}