package io.github.chw3021.monsters.mountains;

import java.util.Random;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Vex;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;



public class MountainsMobsSpawn extends Mobs implements Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -781915509020560604L;
	private static final MountainsMobsSpawn instance = new MountainsMobsSpawn ();
	public static MountainsMobsSpawn getInstance()
	{
		return instance;
	}
	final private Zombie WalkingStone(LivingEntity le) {
		ItemStack head = new ItemStack(Material.STONE);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.GRAY);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.GRAY);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.GRAY);
		boots.setItemMeta(bom);
		String reg = lang.contains("kr") ? "워킹스톤":"WalkingStone";
		Zombie newmob = (Zombie) Mobspawn(le, reg, 160.0, head, chest, leg, boots,
				new ItemStack(Material.STONE), new ItemStack(Material.STONE), EntityType.ZOMBIE);
		newmob.setConversionTime(-1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mountains", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final private Skeleton GravelArcher(LivingEntity le) {
		ItemStack head = new ItemStack(Material.GRAVEL);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.GRAY);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.GRAY);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.GRAY);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.BOW);
		main.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
		String reg = lang.contains("kr") ? "자갈궁수":"GravelArcher";
		Skeleton newmob = (Skeleton) Mobspawn(le, reg, 100.0, head, chest, leg, boots, main,
				null, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mountains", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private Pillager MountainSniper(LivingEntity le) {
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
		String reg = lang.contains("kr") ? "산악저격수":"MountainSniper";
		Pillager newmob = (Pillager) Mobspawn(le, reg, 80.0, head, leg, chest, boots, main,
				null, EntityType.PILLAGER);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999, 1, false, false));
		newmob.addPotionEffect(
				new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1, false, false));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mountains", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private Vex FlowerFairy(LivingEntity le) {
    	ItemStack head = new ItemStack(Material.AZALEA_LEAVES);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.GREEN);
		chest.setItemMeta(chm);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.FUCHSIA);
		leg.setItemMeta(lem);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.MAROON);
		boots.setItemMeta(bom);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack main = new ItemStack(Material.DANDELION);
		ItemStack off = new ItemStack(Material.SUNFLOWER);
		String reg = lang.contains("kr") ? "꽃요정":"FlowerFairy";
		Vex newmob = (Vex) Mobspawn(le,reg, 45.0, head, chest, leg, boots, main, off, EntityType.VEX);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mountains", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private Spider StoneSpider(LivingEntity le) {
		String reg = lang.contains("kr") ? "돌거미":"StoneSpider";
		Spider newmob = (Spider) Mobspawn(le, reg, 100.0, null, null, null, null, null, null,
				EntityType.SPIDER);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25);
		
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mountains", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final private Creeper WoodCreeper(LivingEntity le) {
		String reg = lang.contains("kr") ? "나무크리퍼":"WoodCreeper";
		Creeper newmob = (Creeper) Mobspawn(le, reg, 100.0, null, null, null, null, null, null,
				EntityType.CREEPER);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.18);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mountains", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private Enderman TreeMan(LivingEntity le) {
		String reg = lang.contains("kr") ? "트리맨":"TreeMan";
		Enderman newmob = (Enderman) Mobspawn(le,  reg, 150.0, null, null, null, null, null, null,
				EntityType.ENDERMAN);

		newmob.setCarriedBlock(Material.ACACIA_SAPLING.createBlockData());
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.18);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mountains", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}


	final private LivingEntity Default(LivingEntity le) {
		String reg = lang.contains("kr") ? "산악지대":"Mountains";
		LivingEntity newmob = Mobspawn(le, reg + trans(le), 120.0,
				le.getEquipment().getHelmet(), le.getEquipment().getChestplate(),
				le.getEquipment().getLeggings(), le.getEquipment().getBoots(),
				le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(),
				le.getType());
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mountains", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final public void Spawn(LivingEntity le,Biome b) {
		if (!((b.name().contains("PEAKS") ||b.name().contains("MEADOW")||b.name().contains("HILLS") || b.name().contains("STONY") || b.name().contains("TAIGA")))
				|| b.name().contains("SNOWY")|| b.name().contains("FROZEN")|| b.name().contains("JAG")) {
			return;
		}

		if (le.getCategory() == EntityCategory.UNDEAD
				&& le.getType() != EntityType.PHANTOM) {
			Random random = new Random();
			int ri = random.nextInt(5);
			if (ri == 0) {
				WalkingStone(le);
			} 
			else if (ri == 1) {
				GravelArcher(le);
			} 
			else if (ri == 2) {
				MountainSniper(le);
			} 
			else if (ri == 3) {
				FlowerFairy(le);
			} 
			else {
				Default(le);
			}
		} 
		else if (le.getType().name().contains("SPIDER")) {
			StoneSpider(le);
		} 
		else if (le.getType() == EntityType.CREEPER) {
			WoodCreeper(le);
		} 
		else if (le.getType() == EntityType.ENDERMAN) {
			TreeMan(le);
		}
		else {
			Default(le);
		}
	}
	

}
