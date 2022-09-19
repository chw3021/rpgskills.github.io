package io.github.chw3021.monsters.wild;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;



public class WildMobsSpawn extends Mobs implements Listener {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 6020250166279728279L;
	/**
	 * 
	 */
	
	private static final WildMobsSpawn instance = new WildMobsSpawn ();
	public static WildMobsSpawn getInstance()
	{
		return instance;
	}
	final private Zombie Mob1(LivingEntity le) {
		ItemStack head = new ItemStack(Material.MOSSY_COBBLESTONE);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		ItemStack main = new ItemStack(Material.POTION);
		ItemStack off = new ItemStack(Material.BREWING_STAND);
		off.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
		String reg = lang.contains("kr") ? "고대좀비":"AncientZombie";
		Zombie newmob = (Zombie) Mobspawn(le, reg, 35000.0, head, null, null, null, main, off, EntityType.ZOMBIE);
		newmob.setConversionTime(-1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("wild", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("DrugAddict", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Skeleton Mob2(LivingEntity le) {
		ItemStack head = new ItemStack(Material.JUNGLE_LEAVES);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		String reg = lang.contains("kr") ? "고대스켈레톤":"AncientSkeleton";
		Skeleton newmob = (Skeleton) Mobspawn(le, reg, 33000.0, head, null, null, null, null, null, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("wild", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("Rifleman", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Slime Mob3(LivingEntity le) {
		ItemStack head = new ItemStack(Material.MANGROVE_LEAVES);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		
		String reg = lang.contains("kr") ? "고대슬라임":"AncientSlime";
		Slime newmob = (Slime) Mobspawn(le, reg, 20000.0, head, null, null, null, null, null, EntityType.SLIME);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.33);
		newmob.setSize(12);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("wild", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("GiantSlime", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Vindicator Mob4(LivingEntity le) {
		ItemStack head = new ItemStack(Material.MOSSY_COBBLESTONE);
		String reg = lang.contains("kr") ? "도굴꾼":"GraveRobber";
		Vindicator newmob = (Vindicator) Mobspawn(le, reg, 32000.0, head, null, null, null, null, null, EntityType.VINDICATOR);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("wild", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Spider SpiderV(LivingEntity le) {
		String reg = lang.contains("kr") ? "고대거미":"AncientSpider";
		Spider newmob = (Spider) Mobspawn(le, reg, 27500.0, null, null, null, null, null, null, EntityType.CAVE_SPIDER);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.43);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("wild", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Creeper CreeperV(LivingEntity le) {
		String reg = lang.contains("kr") ? "고대크리퍼":"AncientCreeper";
		Creeper newmob = (Creeper) Mobspawn(le, reg, 28000.0, null, null, null, null, null, null, EntityType.CREEPER);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("wild", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("AutoNuker", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Enderman EndermanV(LivingEntity le) {
		String reg = lang.contains("kr") ? "고대엔더맨":"AncientEnderman";
		Enderman newmob = (Enderman) Mobspawn(le, reg, 38000.0, null, null, null, null, null, null, EntityType.ENDERMAN);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("wild", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private LivingEntity Default(LivingEntity le) {
		String reg = lang.contains("kr") ? "야생":"Wild";
		LivingEntity newmob = Mobspawn(le, reg + trans(le), 30000.0, le.getEquipment().getHelmet(),
				le.getEquipment().getChestplate(), le.getEquipment().getLeggings(),
				le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(),
				le.getEquipment().getItemInOffHand(), le.getType());
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("wild", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final public void Spawn(LivingEntity le,Biome b) {
		if (le.getLocation().getWorld().getEnvironment() != Environment.NORMAL || !b.name().contains("JUNGLE")) {
			return;
		}
		if (le.getCategory() == EntityCategory.UNDEAD
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
