package io.github.chw3021.monsters.mountains;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Vex;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;


public class MountainsRaids extends Summoned {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6536664363548624595L;
	
	private static final MountainsRaids instance = new MountainsRaids ();
	public static MountainsRaids getInstance()
	{
		return instance;
		
	}

	String META = "mountains";
	
	public void MountainsCombo(EntityDeathEvent d) 
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
	final private void Mob1(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = new ItemStack(Material.COBBLESTONE);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.GRAY);
		chest.setItemMeta(chm);
		chest.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
		chest.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.GRAY);
		leg.setItemMeta(lem);
		leg.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
		leg.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.GRAY);
		boots.setItemMeta(bom);
		boots.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
		boots.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
		ItemStack main = new ItemStack(Material.STONE_SWORD);
		ItemStack off = new ItemStack(Material.SHIELD);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "조약돌기사":"CobbleKnight";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 150.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		newmob.setLootTable(null);
		addraider(rn,META,newmob);
	
	}
	final private void Mob2(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
		ItemStack head = new ItemStack(Material.OAK_LEAVES);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.OLIVE);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		chm.setColor(Color.OLIVE);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		chm.setColor(Color.OLIVE);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.CROSSBOW);
		main.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 2);
		main.addUnsafeEnchantment(Enchantment.MULTISHOT, 1);

		String reg = lang.equalsIgnoreCase("ko_kr") ? "명사수":"SharpShooter";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 180.0, head, chest, leg, boots, main, new ItemStack(Material.SPYGLASS), EntityType.SKELETON);
		
		
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
    	ItemStack head = new ItemStack(Material.SMOOTH_STONE);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.GRAY);
		chest.setItemMeta(chm);
		chest.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 5);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION, 1);
		chest.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.fromRGB(101, 67, 33));
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
		ItemStack main = new ItemStack(Material.STICK);
		ItemStack off = new ItemStack(Material.DARK_OAK_SAPLING);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "바람요정":"WindFairy";
		Vex newmob = (Vex) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 100.0, head, chest, leg, boots, main, off, EntityType.VEX);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		newmob.setLootTable(null);
		newmob.setCharging(true);
		addraider(rn,META,newmob);
		
	}
	final private void Mob4(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = new ItemStack(Material.HONEYCOMB_BLOCK);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.YELLOW);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.YELLOW);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.YELLOW);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.HONEYCOMB);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "꿀중독자":"HoneyWalker";
		Husk newmob = (Husk) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 150.0, head, chest, leg, boots, main, main, EntityType.HUSK);
		newmob.setConversionTime(-1);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		newmob.setLootTable(null);
		addraider(rn,META,newmob);
		
	}
	final private void Mob5(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = new ItemStack(Material.HONEYCOMB_BLOCK);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.YELLOW);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.YELLOW);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.YELLOW);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.GOLDEN_AXE);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "벌꿀약탈꾼":"HoneyHunter";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 200.0, head, chest, leg, boots, main, null, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.setTarget(Bukkit.getPlayer(rn));
		newmob.setLootTable(null);
		
		addraider(rn,META,newmob);
	}
	final private void Mob6(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	
		ItemStack head = new ItemStack(Material.GRAVEL);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.OLIVE);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		chm.setColor(Color.OLIVE);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		chm.setColor(Color.OLIVE);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.CROSSBOW);
		main.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 2);
		main.addUnsafeEnchantment(Enchantment.MULTISHOT, 1);
		String reg = lang.contains("kr") ? "자갈저격수":"GravelSniper";
		Pillager newmob = (Pillager) Summon(esl, reg, 80.0, head, leg, chest, boots, main,
				null, EntityType.PILLAGER);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 999999, 1, false, false));
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
    	ItemStack head = new ItemStack(Material.STONECUTTER);
		ItemStack chest = new ItemStack(Material.ELYTRA);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.GREEN);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.OLIVE);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.BOW);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "윈드서퍼":"WindSurfer";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 150.0, head, chest, leg, boots, main, main, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.SPEED, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.SLOW_FALLING, 999999, 3, false, false));
		newmob.setGliding(true);
		

		newmob.setLootTable(null);
		addraider(rn,META,newmob);
		
	}

	final private void Boss(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);


		ItemStack main = new ItemStack(Material.STONE);
		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
		main.addUnsafeEnchantment(Enchantment.WIND_BURST, 3);
		ItemStack off = new ItemStack(Material.STONE);
		off.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "스톤골렘":"StoneGolem";
		IronGolem newmob = (IronGolem) Summon(esl, ChatColor.GRAY + reg, 1000.0, null,
				null, null, null, main, off, EntityType.IRON_GOLEM);
		
		newmob.setPlayerCreated(false);
		newmob.setLootTable(null);
		newmob.setGlowing(true);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4);
		
		addraider(rn,META,newmob);
		newmob.setMetadata("stoneboss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("summonedboss", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setRemoveWhenFarAway(false);
		
		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +"StoneGolem"),newmob.getName(), BarColor.GREEN, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
        newbar.setVisible(true);
		raidbar.put(rn, META, newbar);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		addraider(rn,META,newmob);

		bossBar(rn, META, newmob);
		
	}
	
	
}