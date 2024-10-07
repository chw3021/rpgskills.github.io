package io.github.chw3021.items.armors;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.chw3021.rmain.RMain;

public class Armors {

	final public static NamespacedKey max_health = new NamespacedKey(RMain.getInstance(), "generic.max_health");
	final public static NamespacedKey armor = new NamespacedKey(RMain.getInstance(), "generic.armor");
	final public static NamespacedKey armor_toughness = new NamespacedKey(RMain.getInstance(), "generic.armor_toughness");
	final public static NamespacedKey movement_speed = new NamespacedKey(RMain.getInstance(), "generic.movement_speed");
	

	public static ItemMeta maxHealthAdd(final ItemMeta im, Double amount, Operation op, EquipmentSlotGroup esg) {
		
		im.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(max_health, amount, op, esg));
		
		return im;
	}

	public static ItemMeta armorAdd(final ItemMeta im, Double amount, Operation op, EquipmentSlotGroup esg) {
		
		im.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(armor, amount, op, esg));
		
		return im;
	}

	public static ItemMeta armorToughnessAdd(final ItemMeta im, Double amount, Operation op, EquipmentSlotGroup esg) {
		
		im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(armor_toughness, amount, op, esg));
		
		return im;
	}
	
	public static ItemMeta movementAdd(final ItemMeta im, Double amount, Operation op, EquipmentSlotGroup esg) {
		
		im.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(movement_speed, amount, op, esg));
		
		return im;
	}
	
	
	
}
