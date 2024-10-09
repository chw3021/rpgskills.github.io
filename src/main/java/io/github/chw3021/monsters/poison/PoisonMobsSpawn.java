package io.github.chw3021.monsters.poison;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Bogged;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;



public class PoisonMobsSpawn extends Mobs implements Listener {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 6020250166279728279L;
	/**
	 * 
	 */
	
	private static final PoisonMobsSpawn instance = new PoisonMobsSpawn ();
	public static PoisonMobsSpawn getInstance()
	{
		return instance;
	}
	final private Zombie Mob1(LivingEntity le) {
		ItemStack head = new ItemStack(Material.GREEN_GLAZED_TERRACOTTA);
		head.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 3);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.GREEN);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.GREEN);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.BLACK);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.POTION);
		ItemStack off = new ItemStack(Material.BREWING_STAND);
		off.addUnsafeEnchantment(Enchantment.SHARPNESS, 2);
		String reg = lang.contains("kr") ? "약물중독자":"DrugAddict";
		Zombie newmob = (Zombie) Mobspawn(le, reg, 25000.0, head, chest, leg, boots, main, off, EntityType.ZOMBIE);
		newmob.setConversionTime(-1);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.43);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("poison", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("DrugAddict", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Bogged Mob2(LivingEntity le) {
		ItemStack head = new ItemStack(Material.MANGROVE_LEAVES);
		head.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 3);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.GREEN);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.GREEN);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.BLACK);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.BOW);
		String reg = lang.contains("kr") ? "보초병":"Patrol";
		Bogged newmob = (Bogged) Mobspawn(le, reg, 23000.0, head, chest, leg, boots, main, null, EntityType.BOGGED);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("poison", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("Rifleman", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Slime Mob3(LivingEntity le) {
		ItemStack head = new ItemStack(Material.MANGROVE_LEAVES);
		head.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 3);
		
		String reg = lang.contains("kr") ? "거대슬라임":"GiantSlime";
		Slime newmob = (Slime) Mobspawn(le, reg, 20000.0, head, null, null, null, null, null, EntityType.SLIME);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.33);
		newmob.setSize(10);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("poison", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("GiantSlime", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Witch Mob4(LivingEntity le) {
		String reg = lang.contains("kr") ? "척탄병":"Grenadier";
		Witch newmob = (Witch) Mobspawn(le, reg, 20000.0, null, null, null, null, null, null, EntityType.WITCH);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("poison", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("Grenadier", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Spider SpiderV(LivingEntity le) {
		String reg = lang.contains("kr") ? "맹독거미":"VenomSpider";
		Spider newmob = (Spider) Mobspawn(le, reg, 18000.0, null, null, null, null, null, null, EntityType.CAVE_SPIDER);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.43);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("poison", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Creeper CreeperV(LivingEntity le) {
		String reg = lang.contains("kr") ? "자폭기":"AutoNuker";
		Creeper newmob = (Creeper) Mobspawn(le, reg, 20000.0, null, null, null, null, null, null, EntityType.CREEPER);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("poison", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("AutoNuker", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Enderman EndermanV(LivingEntity le) {
		String reg = lang.contains("kr") ? "척후병":"Scout";
		Enderman newmob = (Enderman) Mobspawn(le, reg, 25000.0, null, null, null, null, null, null, EntityType.ENDERMAN);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("poison", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private LivingEntity Default(LivingEntity le) {
		String reg = lang.contains("kr") ? "늪지대":"Swamp";
		LivingEntity newmob = Mobspawn(le, reg + trans(le), 24000.0, le.getEquipment().getHelmet(),
				le.getEquipment().getChestplate(), le.getEquipment().getLeggings(),
				le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(),
				le.getEquipment().getItemInOffHand(), le.getType());
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("poison", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final public void Spawn(LivingEntity le,Biome b) {
		if (le.getLocation().getWorld().getEnvironment() != Environment.NORMAL || !(b.name().contains("SWAMP") || b.name().contains("MUSHROOM"))
				) {
			return;
		} 
		if (Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())
				&& le.getType() != EntityType.PHANTOM) {
			Random random = new Random();
			int ri = random.nextInt(5);
			if (ri == 0) {
				Mob1(le);
			} 
			else if (ri == 1) {
				Mob2(le);
			} 
			else if (ri == 2) {
				Mob3(le);
			} 
			else if (ri == 3) {
				Mob4(le);
			} 
			else {
				Default(le);
			}
		} 
		else if (le.getType().name().contains("SPIDER")) {
			SpiderV(le);
		} 
		else if (le.getType() == EntityType.CREEPER) {
			CreeperV(le);
		} 
		else if (le.getType() == EntityType.ENDERMAN) {
			EndermanV(le);
		}
		else {
			Default(le);
		}
	}
	

}