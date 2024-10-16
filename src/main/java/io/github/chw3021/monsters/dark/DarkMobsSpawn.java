package io.github.chw3021.monsters.dark;

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
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;



public class DarkMobsSpawn extends Mobs implements Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -781915509020560604L;
	private static final DarkMobsSpawn instance = new DarkMobsSpawn ();
	public static DarkMobsSpawn getInstance()
	{
		return instance;
	}
	final private Zombie Mob1(LivingEntity le) {
		ItemStack head = new ItemStack(Material.STRIPPED_DARK_OAK_LOG);
		head.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 3);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.BLACK);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.BLACK);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.BLACK);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(3008);
		main.setItemMeta(mm);
		ItemStack off = new ItemStack(Material.SHIELD);
		off.addUnsafeEnchantment(Enchantment.SHARPNESS, 2);
		String reg = lang.contains("kr") ? "잠식된기사":"ErodedKnight";
		Zombie newmob = (Zombie) Mobspawn(le, reg, 6000.0, head, chest, leg, boots, main, off, EntityType.ZOMBIE);
		newmob.setConversionTime(-1);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.33);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("dark", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("ErodedKnight", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Skeleton Mob2(LivingEntity le) {
		ItemStack head = new ItemStack(Material.STRIPPED_DARK_OAK_LOG);
		head.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 3);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.BLACK);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.BLACK);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.BLACK);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.BOW);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(2008);
		main.setItemMeta(mm);
		String reg = lang.contains("kr") ? "밤의사수":"NightArcher";
		Skeleton newmob = (Skeleton) Mobspawn(le, reg, 5000.0, head, chest, leg, boots, main, null, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("dark", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("NightArcher", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Pillager Mob3(LivingEntity le) {
		ItemStack head = new ItemStack(Material.STRIPPED_DARK_OAK_LOG);
		head.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 3);
		
		String reg = lang.contains("kr") ? "잠식된약탈자":"ErodedPillager";
		Pillager newmob = (Pillager) Mobspawn(le, reg, 5000.0, head, null, null, null, null, null, EntityType.PILLAGER);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.33);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("dark", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Witch Mob4(LivingEntity le) {
		String reg = lang.contains("kr") ? "밤의마녀":"NightWitch";
		Witch newmob = (Witch) Mobspawn(le, reg, 5000.0, null, null, null, null, null, null, EntityType.WITCH);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("dark", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("DarkWitch", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Spider SpiderV(LivingEntity le) {
		String reg = lang.contains("kr") ? "밤거미":"NightSpider";
		Spider newmob = (Spider) Mobspawn(le, reg, 4000.0, null, null, null, null, null, null, EntityType.CAVE_SPIDER);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.43);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("dark", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Creeper CreeperV(LivingEntity le) {
		String reg = lang.contains("kr") ? "다크크리퍼":"DarkCreeper";
		Creeper newmob = (Creeper) Mobspawn(le, reg, 4500.0, null, null, null, null, null, null, EntityType.CREEPER);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("dark", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("DarkCreeper", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Enderman EndermanV(LivingEntity le) {
		String reg = lang.contains("kr") ? "글루미맨":"GloomyMan";
		Enderman newmob = (Enderman) Mobspawn(le, reg, 6000.0, null, null, null, null, null, null, EntityType.ENDERMAN);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("dark", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private LivingEntity Default(LivingEntity le) {
		String reg = lang.contains("kr") ? "암흑지대":"DarkField";
		LivingEntity newmob = Mobspawn(le, reg + trans(le), 5000.0, le.getEquipment().getHelmet(),
				le.getEquipment().getChestplate(), le.getEquipment().getLeggings(),
				le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(),
				le.getEquipment().getItemInOffHand(), le.getType());
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), "dark"));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("dark", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final public void Spawn(LivingEntity le,Biome b) {
		if (le.getLocation().getWorld().getEnvironment() != Environment.NORMAL || !(b.name().contains("CAVE")
				|| b.name().contains("FOREST")|| b==Biome.DEEP_DARK)
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