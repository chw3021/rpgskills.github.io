package io.github.chw3021.monsters.ender;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Stray;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;



public class EnderMobsSpawn extends Mobs {

	
	private static final EnderMobsSpawn instance = new EnderMobsSpawn ();
	public static EnderMobsSpawn getInstance()
	{
		return instance;
	}
	
	
	final private LivingEntity Default(LivingEntity le) {
		LivingEntity newmob = Mobspawn(le, trans(le), 80000.0, le.getEquipment().getHelmet(),
				le.getEquipment().getChestplate(), le.getEquipment().getLeggings(),
				le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(),
				le.getEquipment().getItemInOffHand(), le.getType());
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), "ender"));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ender", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	
	final private ItemStack mobhead() {
		ItemStack pe = new ItemStack(Material.DRAGON_HEAD);
		pe.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
		return pe;
	}
	final private ItemStack mobchest() {
		ItemStack pe = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ArmorMeta arm = (ArmorMeta) pe.getItemMeta();
		ArmorTrim t1 = new ArmorTrim(TrimMaterial.AMETHYST, TrimPattern.EYE);
		arm.setTrim(t1);
		pe.setItemMeta(arm);
		return pe;
	}
	final private ItemStack mobleg() {
		ItemStack pe = new ItemStack(Material.NETHERITE_LEGGINGS);
		ArmorMeta arm = (ArmorMeta) pe.getItemMeta();
		ArmorTrim t1 = new ArmorTrim(TrimMaterial.AMETHYST, TrimPattern.EYE);
		arm.setTrim(t1);
		pe.setItemMeta(arm);
		return pe;
	}
	final private ItemStack mobboots() {
		ItemStack pe = new ItemStack(Material.NETHERITE_BOOTS);
		ArmorMeta arm = (ArmorMeta) pe.getItemMeta();
		ArmorTrim t1 = new ArmorTrim(TrimMaterial.AMETHYST, TrimPattern.EYE);
		arm.setTrim(t1);
		pe.setItemMeta(arm);
		return pe;
	}
	
	
	final private Drowned DragonGuardian(LivingEntity le) {
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.MACE);
		main.addUnsafeEnchantment(Enchantment.DENSITY,3);
		main.addUnsafeEnchantment(Enchantment.WIND_BURST,5);
		ItemStack off = new ItemStack(Material.SHIELD);
		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 2);
		String reg = lang.contains("kr") ? "용수호자":"DragonGuardian";
		Drowned newmob = (Drowned) Mobspawn(le, reg, 100000.0, head, chest, leg, boots, main, off, EntityType.DROWNED);
		newmob.setConversionTime(-1);
		newmob.setSwimming(true);
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.JUMP_BOOST, 999999, 10, false, false));
		newmob.setMetadata("CreepJumper", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ender", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}


	final private Stray EnderMimic(LivingEntity le) {
		ItemStack head = new ItemStack(Material.ENDER_CHEST);
		String reg = lang.contains("kr") ? "엔더미믹":"EnderMimic";
		Stray newmob = (Stray) Mobspawn(le, reg, 75000.0, head,  new ItemStack(Material.VOID_AIR),  new ItemStack(Material.VOID_AIR),  new ItemStack(Material.VOID_AIR), new ItemStack(Material.VOID_AIR),  new ItemStack(Material.VOID_AIR), EntityType.STRAY);
		newmob.setSwimming(true);
		newmob.setGravity(true);
		newmob.setAI(false);
		newmob.setInvisible(true);
		newmob.getAttribute(Attribute.WATER_MOVEMENT_EFFICIENCY).setBaseValue(20);
		newmob.getAttribute(Attribute.OXYGEN_BONUS).setBaseValue(20);
		newmob.getAttribute(Attribute.GRAVITY).setBaseValue(10000);
		newmob.getAttribute(Attribute.STEP_HEIGHT).setBaseValue(30);
		newmob.getAttribute(Attribute.JUMP_STRENGTH).setBaseValue(30);
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ender", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("enderMimic", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}


	final private Shulker EnderShulker(LivingEntity le) {
		ItemStack head = new ItemStack(Material.SEAGRASS);
		head.addUnsafeEnchantment(Enchantment.AQUA_AFFINITY, 3);
		head.addUnsafeEnchantment(Enchantment.RESPIRATION, 3);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.TEAL);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.NAVY);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.GREEN);
		boots.setItemMeta(bom);
		boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
		ItemStack main = new ItemStack(Material.SEAGRASS);
		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
		String reg = lang.contains("kr") ? "엔더셜커":"EnderShulker";
		Shulker newmob = (Shulker) Mobspawn(le, reg, 75000.0, head, chest, leg, boots, main, main, EntityType.SHULKER);
		newmob.setPeek(0.3f);
		newmob.setColor(DyeColor.PURPLE);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ender", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}


	final private Husk DragonMinion(LivingEntity le) {
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.NETHERITE_AXE);
		ItemStack off = new ItemStack(Material.IRON_SWORD);
		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 2);
		String reg = lang.contains("kr") ? "용하수인":"DragonMinion";
		Husk newmob = (Husk) Mobspawn(le, reg, 95000.0, head, chest, leg, boots, off, main, EntityType.HUSK);
		newmob.setConversionTime(-1);
		newmob.setSwimming(true);
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
		newmob.setMetadata("Stalker", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ender", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}


	final private Stray DragonArcher(LivingEntity le) {
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.BOW);
		ItemStack off = new ItemStack(Material.ENDER_EYE);
		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 2);
		String reg = lang.contains("kr") ? "용수호자궁수":"DragonArcher";
		Stray newmob = (Stray) Mobspawn(le, reg, 90000.0, head, chest, leg, boots, off, main, EntityType.STRAY);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("enderRifleman", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ender", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final private Drowned DragonLancer(LivingEntity le) {
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.TRIDENT);
		ItemStack off = new ItemStack(Material.SHIELD);
		main.addUnsafeEnchantment(Enchantment.LOYALTY, 2);
		String reg = lang.contains("kr") ? "용수호자창기병":"DragonLancer";
		Drowned newmob = (Drowned) Mobspawn(le, reg, 100000.0, head, chest, leg, boots, main, off, EntityType.DROWNED);
		newmob.setConversionTime(-1);
		newmob.setSwimming(true);
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.SLOW_FALLING, 999999, 101, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ender", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("riptider", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}



	final public void Spawn(LivingEntity le,Biome b) {
		if (le.getLocation().getWorld().getEnvironment() != Environment.THE_END) {
			return;
		}
		Random random=new Random();
    	int ri = random.nextInt(30);
    	if(ri <= 3) {
    		for(int i = 0; i < 3; i++) {
    			DragonGuardian(le);
    		}
    	}
    	else if(ri <= 4) {
			EnderMimic(le);
    	}
    	else if(ri <= 8) {
			EnderShulker(le);
    	}
    	else if(ri <= 13) {
    		for(int i = 0; i < 3; i++) {
    			DragonMinion(le);
    		}
    	}
    	else if(ri <= 18) {
    		for(int i = 0; i < 3; i++) {
    			DragonArcher(le);
    		}
    	}
    	else if(ri <= 23) {
    		for(int i = 0; i < 3; i++) {
    			DragonLancer(le);
    		}
    	}
		else {
    		Default(le);
		}
	}
	

}