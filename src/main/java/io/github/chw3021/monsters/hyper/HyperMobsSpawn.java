package io.github.chw3021.monsters.hyper;

import java.util.Random;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;


public class HyperMobsSpawn extends Mobs implements Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6665117211246203993L;
	private static final HyperMobsSpawn instance = new HyperMobsSpawn ();
	public static HyperMobsSpawn getInstance()
	{
		return instance;
	}
	
	final private Husk Stalker(LivingEntity le) {
		ItemStack head = new ItemStack(Material.LODESTONE);
		head.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 3);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.MAROON);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.FUCHSIA);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.BLACK);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.NETHERITE_AXE);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(4009);
		main.setItemMeta(mm);
		ItemStack off = new ItemStack(Material.END_ROD);
		off.addUnsafeEnchantment(Enchantment.SHARPNESS, 2);
		String reg = lang.contains("kr") ? "추적자":"Stalker";
		Husk newmob = (Husk) Mobspawn(le, reg, 12000.0, head, chest, leg, boots, main, off, EntityType.HUSK);
		newmob.setConversionTime(-1);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.33);
		newmob.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(20);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("hyper", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("Stalker", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private Skeleton RayArcher(LivingEntity le) {
		ItemStack head = new ItemStack(Material.PURPLE_STAINED_GLASS);
		head.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 3);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.FUCHSIA);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.PURPLE);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.BLACK);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.BOW);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(2009);
		main.setItemMeta(mm);
		ItemStack off = new ItemStack(Material.BEACON);
		off.addUnsafeEnchantment(Enchantment.SHARPNESS, 2);
		String reg = lang.contains("kr") ? "광선궁병":"RayArcher";
		Skeleton newmob = (Skeleton) Mobspawn(le, reg, 9000.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("hyper", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("RayArcher", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private Vindicator SecurityRobot(LivingEntity le) {
		ItemStack head = new ItemStack(Material.TARGET);
		head.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 3);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.TEAL);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.TEAL);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.TEAL);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.SHIELD);
		ItemStack off = new ItemStack(Material.SHIELD);
		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
		String reg = lang.contains("kr") ? "경비로봇":"SecurityRobot";
		Vindicator newmob = (Vindicator) Mobspawn(le, reg, 10000.0, head, null, null, null, main, off, EntityType.VINDICATOR);
		newmob.setSilent(true);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.33);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("hyper", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setLootTable(null);
		return newmob;
	}

	
	final private Witch Grenadier(LivingEntity le) {
		String reg = lang.contains("kr") ? "미친과학자":"MadScientist";
		Witch newmob = (Witch) Mobspawn(le, reg, 8500.0, null, null, null, null, null, null, EntityType.WITCH);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("hyper", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("MadScientist", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final private Spider ScoutSpider(LivingEntity le) {
		String reg = lang.contains("kr") ? "정찰용거미":"ScoutSpider";
		Spider newmob = (Spider) Mobspawn(le, reg, 9000.0, null, null, null, null, null, null, EntityType.SPIDER);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.43);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("hyper", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	
	final private Creeper CreepJumper(LivingEntity le) {
		String reg = lang.contains("kr") ? "크립점퍼":"CreepJumper";
		Creeper newmob = (Creeper) Mobspawn(le, reg, 6000.0, null, null, null, null, null, null, EntityType.CREEPER);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("hyper", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("CreepJumper", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final private Enderman Watcher(LivingEntity le) {
		String reg = lang.contains("kr") ? "감시자":"Watcher";
		Enderman newmob = (Enderman) Mobspawn(le, reg, 12000.0, null, null, null, null, null, null, EntityType.ENDERMAN);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("hyper", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private LivingEntity Default(LivingEntity le) {
		String reg = lang.contains("kr") ? "사막지대":"Desert";
		LivingEntity newmob = Mobspawn(le, reg + trans(le), 9000.0, le.getEquipment().getHelmet(),
				le.getEquipment().getChestplate(), le.getEquipment().getLeggings(),
				le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(),
				le.getEquipment().getItemInOffHand(), le.getType());
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), "hyper"));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("hyper", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final public void Spawn(LivingEntity le,Biome b) {
		if (!((b.name().contains("DESERT")
				|| b.name().contains("SAVANNA"))
				&& le.getLocation().getWorld().getEnvironment() == Environment.NORMAL)) {
			return;
		} 
		if (Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())
				&& le.getType() != EntityType.PHANTOM) {
			Random random = new Random();
			int ri = random.nextInt(5);
			if (ri == 0) {
				Stalker(le);
			} 
			else if (ri == 1) {
				RayArcher(le);
			} 
			else if (ri == 2) {
				SecurityRobot(le);
			} 
			else if (ri == 3) {
				Grenadier(le);
			} 
			else {
				Default(le);
			}
		} 
		else if (le.getType().name().contains("SPIDER")) {
			ScoutSpider(le);
		} 
		else if (le.getType() == EntityType.CREEPER) {
			CreepJumper(le);
		} 
		else if (le.getType() == EntityType.ENDERMAN) {
			Watcher(le);
		}
		else {
			Default(le);
		}
	}

}