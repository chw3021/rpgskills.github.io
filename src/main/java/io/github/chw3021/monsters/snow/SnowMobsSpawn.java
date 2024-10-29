package io.github.chw3021.monsters.snow;

import java.util.Random;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Stray;
import org.bukkit.entity.Witch;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;


public class SnowMobsSpawn extends Mobs implements Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6299011623941907681L;
	private static final SnowMobsSpawn instance = new SnowMobsSpawn ();
	public static SnowMobsSpawn getInstance()
	{
		return instance;
	}
	
	final private Drowned WalkingIce(LivingEntity le) {
		ItemStack head = new ItemStack(Material.ICE);
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
		String reg = lang.contains("kr") ? "워킹아이스":"WalkingIce";
		Drowned newmob = (Drowned) Mobspawn(le, reg, 900.0, head, chest, leg, boots, new ItemStack(Material.SNOWBALL), new ItemStack(Material.SNOWBALL), EntityType.DROWNED);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final private Stray FrostSkull(LivingEntity le) {
		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
		hem.setColor(Color.WHITE);
		head.setItemMeta(hem);
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
		ItemStack main = new ItemStack(Material.BOW);
		main.addUnsafeEnchantment(Enchantment.POWER, 3);
		String reg = lang.contains("kr") ? "서리해골":"FrostSkull";
		Stray newmob = (Stray) Mobspawn(le, reg, 800.0, head, chest, leg, boots, main, null, EntityType.STRAY);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private Stray FrostHunter(LivingEntity le) {
		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
		hem.setColor(Color.NAVY);
		head.setItemMeta(hem);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.NAVY);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.NAVY);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.NAVY);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.DIAMOND_AXE);
		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 2);
		String reg = lang.contains("kr") ? "서리사냥꾼":"FrostHunter";
		Stray newmob = (Stray) Mobspawn(le, reg, 650.0, head, null, null, boots, main,null, EntityType.STRAY);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("frosthunter", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Stray FrozenMage(LivingEntity le) {
		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
		hem.setColor(Color.WHITE);
		head.setItemMeta(hem);
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
		ItemStack main = new ItemStack(Material.BLUE_ICE);
		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
		String reg = lang.contains("kr") ? "서리마법사":"FrozenMage";
		Stray newmob = (Stray) Mobspawn(le, reg, 600.0, null, chest, leg, boots, main, main, EntityType.STRAY);
		newmob.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.2);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("frostmage", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private Witch FrostWitch(LivingEntity le) {
		ItemStack head = new ItemStack(Material.SMOKER);
		String reg = lang.contains("kr") ? "서리마녀":"FrostWitch";
		Witch newmob = (Witch) Mobspawn(le, reg, 650.0, head, null, null, null, null, null, EntityType.WITCH);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("FrostWitch", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final private Spider IceSpider(LivingEntity le) {
		String reg = lang.contains("kr") ? "얼음거미":"IceSpider";
		Spider newmob = (Spider) Mobspawn(le, reg, 580.0,null, null, null, null, null, null, EntityType.SPIDER);
		newmob.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.25);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	
	final private Creeper ColdCreeper(LivingEntity le) {

		String reg = lang.contains("kr") ? "냉기크리퍼":"ColdCreeper";
		Creeper newmob = (Creeper) Mobspawn(le, reg, 600.0, null, null, null, null, null, null, EntityType.CREEPER);
		newmob.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.18);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}

	final private Enderman IceMan(LivingEntity le) {
		String reg = lang.contains("kr") ? "아이스맨":"IceMan";
		Enderman newmob = (Enderman) Mobspawn(le, reg, 700.0, null, null, null, null, null, null, EntityType.ENDERMAN);
		
		newmob.setCarriedBlock(Material.ICE.createBlockData());
		newmob.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.18);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}
	final private Guardian IceGuardian(LivingEntity le) {
		String reg = lang.contains("kr") ? "아이스가디언":"IceGuardian";
		Guardian newmob = (Guardian) Mobspawn(le, reg, 1000.0, null, null, null, null, null, null, EntityType.GUARDIAN);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}


	final private LivingEntity Default(LivingEntity le) {
		String reg = lang.contains("kr") ? "설산":"Snowy";
		LivingEntity newmob = Mobspawn(le, reg + trans(le), 500.0, le.getEquipment().getHelmet(), le.getEquipment().getChestplate(), le.getEquipment().getLeggings(), le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(), le.getType());
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), "snowy"));
		newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("snowy", new FixedMetadataValue(RMain.getInstance(), true));
		return newmob;
	}



	
	final public void Spawn(LivingEntity le,Biome b) {

		if (!(b.name().contains("SNOWY")
				|| b.name().contains("ICE")
				|| b.name().contains("FROZEN")|| b.name().contains("GROVE")|| b.name().contains("JAG"))) {
			return;
		} 
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType()) && le.getType() != EntityType.PHANTOM ) {
    		Random random=new Random();
        	int ri = random.nextInt(5);
        	if(ri == 0) {
        		WalkingIce(le);
        	}
        	else if(ri == 1) {
        		FrostSkull(le);
        	}
        	else if(ri == 2) {
        		FrostHunter(le);
        	}
        	else if (ri == 3){
        		FrozenMage(le);            	
        	}
        	else if (ri == 4){
        		FrostWitch(le);            	
        	}
        	else {
        		Default(le);
        	}
    	}
		else if (le.getType().name().contains("SPIDER")) {
			IceSpider(le);
    	}
		else if(le.getType() == EntityType.CREEPER) {
        	ColdCreeper(le);
    	}
		else if(le.getType() == EntityType.ENDERMAN) {
			IceMan(le);
		}
		else if(le.getType() == EntityType.GUARDIAN) {
			IceGuardian(le);
		}
		else {
			Default(le);
		}
		
	}

}