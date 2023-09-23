package io.github.chw3021.monsters.ocean;

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
import org.bukkit.entity.Drowned;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;


public class OceanRaids extends Summoned {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6536664363548624595L;
	
	private static final OceanRaids instance = new OceanRaids ();
	public static OceanRaids getInstance()
	{
		return instance;
		
	}

	String META = "ocean";
	
	public void OceanCombo(EntityDeathEvent d) 
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
    	Location esl = spl.clone().add(number, 0, number2);
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
		String reg = lang.equalsIgnoreCase("ko_kr") ? "잠식된 해적":"Encroached Pirate";
		Drowned newmob = (Drowned) Summon(esl, ChatColor.DARK_BLUE+reg + "<"+rn+">", 3000.0, head, chest, leg, boots, main, null, EntityType.DROWNED);
		newmob.setAdult();
		newmob.setConversionTime(-1);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		addraider(rn,META,newmob);
	
	}
	final private void Mob2(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);
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
		String reg = lang.equalsIgnoreCase("ko_kr") ? "잠식된 선장":"Encroached Captain";
		
		Drowned newmob = (Drowned) Summon(esl, ChatColor.DARK_BLUE+reg + "<"+rn+">", 3500.0, head, chest, leg, boots, main, off, EntityType.DROWNED);
		newmob.setAdult();
		newmob.setConversionTime(-1);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		addraider(rn,META,newmob);
		
		
	}
	final private void Mob3(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);
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
		String reg = lang.equalsIgnoreCase("ko_kr") ? "잠식된 바다기사":"Encroached OceanKnight";
		Drowned newmob = (Drowned) Summon(esl, ChatColor.DARK_BLUE+reg + "<"+rn+">", 2500.0, head, chest, leg, boots, main, off, EntityType.DROWNED);
		newmob.setAdult();
		newmob.setConversionTime(-1);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		addraider(rn,META,newmob);
		
	}
	final private void Mob4(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);
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
		String reg = lang.equalsIgnoreCase("ko_kr") ? "잠식된 가디언":"Encroached Guardian";
		Guardian newmob = (Guardian) Summon(esl, ChatColor.DARK_BLUE+reg + "<"+rn+">", 3000.0, head, chest, leg, boots, main, null, EntityType.GUARDIAN);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		
		
		addraider(rn,META,newmob);
		
	}
	final private void Mob5(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "잠식된 바다망령":"Encroached OceanPhantom";
		Phantom newmob = (Phantom) Summon(esl, ChatColor.DARK_BLUE+reg + "<"+rn+">", 3000.0, null, null, null, null, null, null, EntityType.PHANTOM);
		newmob.setSize(5);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		addraider(rn,META,newmob);
	}
	final private void Mob6(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);
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
		String reg = lang.equalsIgnoreCase("ko_kr") ? "잠식된 미믹":"Encroached Mimic";
		Drowned newmob = (Drowned) Summon(esl, ChatColor.DARK_BLUE+reg + "<"+rn+">", 3600.0, head, chest, leg, boots, main, off, EntityType.DROWNED);
		newmob.setAdult();
		newmob.setConversionTime(-1);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 255, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		
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
    	Location esl = spl.clone().add(number, 0, number2);
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
		String reg = lang.equalsIgnoreCase("ko_kr") ? "잠식된 가디언":"Encroached Guardian";
		Guardian newmob = (Guardian) Summon(esl, ChatColor.DARK_BLUE+reg + "<"+rn+">", 2500.0, head, chest, leg, boots, main, off, EntityType.GUARDIAN);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		addraider(rn,META,newmob);
		
	}
	@SuppressWarnings("unchecked")
	final private void Boss(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);


		String reg = Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr") ? "엘더가디언":"ElderGuardian";
		ElderGuardian newmob = (ElderGuardian) Summon(esl, ChatColor.BLUE+reg + "<"+rn+">", 10000.0, null, null, null, null, null, null, EntityType.ELDER_GUARDIAN);
		newmob.setGlowing(true);
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setMetadata("oceanboss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("summonedboss", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setRemoveWhenFarAway(false);
		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +"ElderGuardian"),newmob.getName(), BarColor.BLUE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
        newbar.setVisible(true);
		raidbar.put(rn, META, newbar);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		addraider(rn,META,newmob);
		
	
		final Object ht = getherotype(rn);
	
		if(ht instanceof Player) {
			Player p = (Player) ht;
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	
					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/10000d);
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
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/10000d);
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