package io.github.chw3021.monsters.red;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.chw3021.commons.Pak;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;


public class RedMobsSpawn extends Mobs implements Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2293637895898323806L;
	private static final RedMobsSpawn instance = new RedMobsSpawn ();
	public static RedMobsSpawn getInstance()
	{
		return instance;
	}
	
	final private Husk WalkingRedSand(LivingEntity le) {
		ItemStack head = new ItemStack(Material.RED_SAND);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.RED);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.RED);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.RED);
		boots.setItemMeta(bom);
		String reg = lang.contains("kr") ? "워킹레드샌드":"WalkingRedSand";
		Husk newmob = (Husk) Mobspawn(le, reg, 15000.0, head, chest, leg, boots, new ItemStack(Material.CAMPFIRE), new ItemStack(Material.CAMPFIRE), EntityType.HUSK);
		newmob.setConversionTime(-1);
		newmob.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(20);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("red", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("headthrower", new FixedMetadataValue(RMain.getInstance(), true));
		Pak.flamer.put(newmob.getUniqueId(), 0.3);
		return newmob;
	}
	
	final private Skeleton BurnedSkull(LivingEntity le) {
		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
		hem.setColor(Color.RED);
		head.setItemMeta(hem);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.RED);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.RED);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.RED);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.BOW);
		main.addUnsafeEnchantment(Enchantment.FLAME, 2);
		main.addUnsafeEnchantment(Enchantment.POWER, 3);
		String reg = lang.contains("kr") ? "그을린해골":"BurnedSkull";
		Skeleton newmob = (Skeleton) Mobspawn(le, reg, 12000.0, null, chest, leg, boots, main, null, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("red", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("arch", new FixedMetadataValue(RMain.getInstance(), true));
		Pak.flamer.put(newmob.getUniqueId(), 0.3);
		return newmob;
	}

	final private Illusioner BurnedMage(LivingEntity le) {
		ItemStack head = new ItemStack(Material.CUT_RED_SANDSTONE_SLAB);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.RED);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.RED);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.RED);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.FIRE_CHARGE);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 5);
		String reg = lang.contains("kr") ? "그을린마법사":"BurnedMage";
		Illusioner newmob = (Illusioner) Mobspawn(le, reg, 10500.0, head, chest, leg, boots, main,main, EntityType.ILLUSIONER);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.08);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("red", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mage", new FixedMetadataValue(RMain.getInstance(), true));
		Pak.flamer.put(newmob.getUniqueId(), 0.3);
		return newmob;
	}

	final private Skeleton RedBruise(LivingEntity le) {
		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
		hem.setColor(Color.RED);
		head.setItemMeta(hem);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.RED);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.RED);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.RED);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.IRON_SWORD);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
		String reg = lang.contains("kr") ? "붉은전사":"RedBruise";
		Skeleton newmob = (Skeleton) Mobspawn(le, reg, 12000.0, head, chest, leg, boots, main, main, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("jumper", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("red", new FixedMetadataValue(RMain.getInstance(), true));
		Pak.flamer.put(newmob.getUniqueId(), 0.3);
		return newmob;
	}

	final private Spider BurningSpider(LivingEntity le) {
		String reg = lang.contains("kr") ? "불거미":"BurningSpider";
		Spider newmob = (Spider) Mobspawn(le, reg, 10000.0,null, null, null, null, new ItemStack(Material.CAMPFIRE), new ItemStack(Material.CAMPFIRE), EntityType.SPIDER);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.26);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("red", new FixedMetadataValue(RMain.getInstance(), true));
		Pak.flamer.put(newmob.getUniqueId(), 0.3);
		return newmob;
	}
	
	final private Creeper HotCreeper(LivingEntity le) {
		String reg = lang.contains("kr") ? "열기크리퍼":"HotCreeper";
		Creeper newmob = (Creeper) Mobspawn(le, reg, 10000.0, null, null, null, null, new ItemStack(Material.CAMPFIRE), new ItemStack(Material.CAMPFIRE), EntityType.CREEPER);
		newmob.setPowered(true);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.18);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("red", new FixedMetadataValue(RMain.getInstance(), true));
		Pak.flamer.put(newmob.getUniqueId(), 0.3);
		return newmob;
	}

	final private Enderman RedMan(LivingEntity le) {
		String reg = lang.contains("kr") ? "레드맨":"RedMan";
		Enderman newmob = (Enderman) Mobspawn(le, reg, 15000.0, new ItemStack(Material.MAGMA_BLOCK), null, null, null, new ItemStack(Material.MAGMA_BLOCK), new ItemStack(Material.MAGMA_BLOCK), EntityType.ENDERMAN);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		newmob.setCarriedBlock(Material.MAGMA_BLOCK.createBlockData());
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.2);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("red", new FixedMetadataValue(RMain.getInstance(), true));
		Pak.flamer.put(newmob.getUniqueId(), 0.3);
		return newmob;
	}

	final private LivingEntity Default(LivingEntity le) {
		String reg = lang.contains("kr") ? "악지":"BadLands";
		LivingEntity newmob = Mobspawn(le, reg +trans(le), 12000.0, le.getEquipment().getHelmet(), le.getEquipment().getChestplate(), le.getEquipment().getLeggings(), le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(), le.getType());
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), "red"));
		newmob.setMetadata("red", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		Pak.flamer.put(newmob.getUniqueId(), 0.3);
		return newmob;
	}



	final public void Spawn(LivingEntity le,Biome b) {
		if (!b.name().contains("BADLANDS")) {
			return;
		} 
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType()) && le.getType() != EntityType.PHANTOM) {
    		Random random=new Random();
        	int ri = random.nextInt(5);
        	if(ri == 0) {
        		WalkingRedSand(le);
        	}
        	else if(ri == 1) {
        		BurnedSkull(le);
        	}
        	else if(ri == 2) {
        		BurnedMage(le);
        	}
        	else if (ri == 3){
        		RedBruise(le);
        	}
        	else {
        		Default(le);
        	}
    	}
		else if (le.getType().name().contains("SPIDER")) {
    		BurningSpider(le);
    	}
		else if(le.getType() == EntityType.CREEPER) {
    		HotCreeper(le);
    	}
		else if(le.getType() == EntityType.ENDERMAN) {
    		RedMan(le);
    	}
		else {
    		Default(le);
		}   
		
	}

}