package io.github.chw3021.monsters.ocean;

import java.util.Random;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Stray;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;


public class OceanMobsSpawn extends Mobs implements Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 111231948843036438L;
	private static final OceanMobsSpawn instance = new OceanMobsSpawn ();
	public static OceanMobsSpawn getInstance()
	{
		return instance;
	}
	
	final private Drowned LanternMan(LivingEntity le) {
		ItemStack head = new ItemStack(Material.SEA_LANTERN);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.WHITE);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.WHITE);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.WHITE);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.FISHING_ROD);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
		String reg = lang.contains("kr") ? "랜턴맨":"LanternMan";
		Drowned newmob = (Drowned) Mobspawn(le, reg, 2500.0, head, chest, leg, boots, main, main, EntityType.DROWNED);
		newmob.setConversionTime(-1);
		newmob.setSwimming(true);
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private Stray Mimic(LivingEntity le) {
		ItemStack head = new ItemStack(Material.TRAPPED_CHEST);
		String reg = lang.contains("kr") ? "미믹":"Mimic";
		Stray newmob = (Stray) Mobspawn(le, reg, 3000.0, head,  new ItemStack(Material.VOID_AIR),  new ItemStack(Material.VOID_AIR),  new ItemStack(Material.VOID_AIR), new ItemStack(Material.VOID_AIR),  new ItemStack(Material.VOID_AIR), EntityType.STRAY);
		newmob.setSwimming(true);
		newmob.setGravity(true);
		newmob.setAI(false);
		newmob.setInvisible(true);
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mimic", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private Shulker SeaShulker(LivingEntity le) {
		ItemStack head = new ItemStack(Material.SEAGRASS);
		head.addUnsafeEnchantment(Enchantment.WATER_WORKER, 3);
		head.addUnsafeEnchantment(Enchantment.OXYGEN, 3);
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
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		String reg = lang.contains("kr") ? "바다셜커":"SeaShulker";
		Shulker newmob = (Shulker) Mobspawn(le, reg, 2000.0, head, chest, leg, boots, main, main, EntityType.SHULKER);
		newmob.setPeek(0.3f);
		newmob.setColor(DyeColor.BLUE);
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 3, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	
	final private Drowned DrownedPirate(LivingEntity le) {
		ItemStack head = new ItemStack(Material.BRAIN_CORAL_BLOCK);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.RED);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.NAVY);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.YELLOW);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.BONE);
		ItemStack off = new ItemStack(Material.IRON_SWORD);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
		String reg = lang.contains("kr") ? "떠도는해적":"DrownedPirate";
		Drowned newmob = (Drowned) Mobspawn(le, reg, 1800.0, head, chest, leg, boots, off, main, EntityType.DROWNED);
		newmob.setConversionTime(-1);
		newmob.setSwimming(true);
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final private Drowned DrownedCaptain(LivingEntity le) {
		ItemStack head = new ItemStack(Material.BUBBLE_CORAL_BLOCK);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.BLACK);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.NAVY);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.BLACK);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack off = new ItemStack(Material.FLINT_AND_STEEL);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
		String reg = lang.contains("kr") ? "해적선장의원혼":"DrownedCaptain";
		Drowned newmob = (Drowned) Mobspawn(le, reg, 2600.0, head, chest, leg, boots, off, main, EntityType.DROWNED);
		newmob.setConversionTime(-1);
		newmob.setSwimming(true);
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	
	final private Drowned DrownedLancer(LivingEntity le) {
		ItemStack head = new ItemStack(Material.PRISMARINE);
		ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
		ItemStack main = new ItemStack(Material.TRIDENT);
		ItemStack off = new ItemStack(Material.SHIELD);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
		String reg = lang.contains("kr") ? "떠도는창기병":"DrownedLancer";
		Drowned newmob = (Drowned) Mobspawn(le, reg, 2000.0, head, chest, leg, boots, main, off, EntityType.DROWNED);
		newmob.setConversionTime(-1);
		newmob.setSwimming(true);
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final private Phantom OceanPhantom(LivingEntity le) {
		String reg = lang.contains("kr") ? "바다망령":"OceanPhantom";
		Phantom newmob = (Phantom) Mobspawn(le, reg, 3000.0, null, null, null, null, null, null, EntityType.PHANTOM);
		newmob.setSize(20);
		newmob.setVisualFire(false);
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 3, false, false));
		newmob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private LivingEntity Default(LivingEntity le) {
		String reg = lang.contains("kr") ? "바다":"Ocean";
		LivingEntity newmob = Mobspawn(le, reg + trans(le), 2000.0, le.getEquipment().getHelmet(),
				le.getEquipment().getChestplate(), le.getEquipment().getLeggings(),
				le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(),
				le.getEquipment().getItemInOffHand(), le.getType());
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 255, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 3, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 3, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ocean", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final public void Spawn(LivingEntity le,Biome b) {
		if (!b.name().contains("OCEAN") || b.name().contains("FROZEN")) {
			return;
		}

		if(le.getWorld().getNearbyEntities(le.getLocation(), 20, 20, 20).stream().anyMatch(e -> e.hasMetadata("oceanruin"))) {
			LanternMan(le);
			return;
		}
		if(le.getType().name().contains("SQUID")) {
    		Random random=new Random();
        	int ri = random.nextInt(25);
        	if(ri <= 3) {
				LanternMan(le);
        	}
        	else if(ri <= 5) {
				Mimic(le);
        	}
        	else if(ri <= 6) {
				OceanPhantom(le);
        	}
        	else if(ri <= 7) {
				SeaShulker(le);
        	}
        	else if(ri <= 9) {
				DrownedPirate(le);
        	}
        	else if(ri <= 10) {
				DrownedCaptain(le);
        	}
        	else if(ri <= 11) {
        		DrownedLancer(le);
        	}
    		else {
        		Default(le);
    		}
		}
		else {
			if(le.getWorld().getTime()>=12000 && le.getWorld().getTime()<=24000 && !le.getType().name().contains("FISH") && !le.getType().name().contains("COD") && !le.getType().name().contains("SALMON")) {
        		Random random=new Random();
            	int ri = random.nextInt(45);
            	if(ri <= 3) {
    				LanternMan(le);
            	}
            	else if(ri <= 5) {
    				Mimic(le);
            	}
            	else if(ri <= 6) {
    				OceanPhantom(le);
            	}
            	else if(ri <= 7) {
    				SeaShulker(le);
            	}
            	else if(ri <= 9) {
    				DrownedPirate(le);
            	}
            	else if(ri <= 10) {
    				DrownedCaptain(le);
            	}
            	else if(ri <= 11) {
            		DrownedLancer(le);
            	}
        		else {
            		Default(le);
        		}
			}
			else {
        		Default(le);
			}
		}
		
	}

}
