package io.github.chw3021.items.armors;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Boots extends Armors implements Listener {

	public static ItemStack get(Integer f, Player p) {

		ItemStack r = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta rm = (LeatherArmorMeta) r.getItemMeta();
		if(f == 0) {
			rm.setColor(Color.BLACK);
			rm.addEnchant(Enchantment.BLAST_PROTECTION, 3, true);
			rm.addEnchant(Enchantment.FIRE_PROTECTION, 3, true);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.23, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 1, Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("안전화");
				rm.setItemName("안전화");
			}
			else {
				rm.setDisplayName("Safety Shoes");
				rm.setItemName("Safety Shoes");
			}
			r.setItemMeta(rm);
		}
		else if(f == 1) {
			rm.setColor(Color.WHITE);
			rm.addEnchant(Enchantment.FROST_WALKER, 10, true);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.2, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.2, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 1, Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("설산 장화");
				rm.setItemName("설산 장화");
			}
			else {
				rm.setDisplayName("Snowy Boots");
				rm.setItemName("Snowy Boots");
			}
			r.setItemMeta(rm);
		}
		else if(f == 2) {
			rm.addEnchant(Enchantment.DEPTH_STRIDER, 10, true);
			rm.setColor(Color.YELLOW);
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(getKey(), -0.1, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.2, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("오리발");
				rm.setItemName("오리발");
			}
			else {
				rm.setDisplayName("Flippers");
				rm.setItemName("Flippers");
			}
			r.setItemMeta(rm);
		}
		else if(f == 3) {
			rm.setColor(Color.AQUA);
			rm.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.42, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("프리즈머린 신발");
				rm.setItemName("프리즈머린 신발");
			}
			else {
				rm.setDisplayName("Prismarine Boots");
				rm.setItemName("Prismarine Boots");
			}
			r.setItemMeta(rm);
		}
		else if(f == 5) {
			rm.setColor(Color.BLACK);
			rm.addEnchant(Enchantment.FEATHER_FALLING, 10, true);
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.1, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.51, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("전술화");
				rm.setItemName("전술화");
			}
			else {
				rm.setDisplayName("Combat Boots");
				rm.setItemName("Combat Boots");
			}
			r.setItemMeta(rm);
		}
		else if(f == 6) {
			r.setType(Material.CHAINMAIL_BOOTS);
			ItemMeta im = (ItemMeta) r.getItemMeta();
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.08, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
			im.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.56, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
			im.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
			im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				im.setDisplayName("합금화");
				im.setItemName("합금화");
			}
			else {
				im.setDisplayName("Alloy Boots");
				im.setItemName("Alloy Boots");
			}
			im.addEnchant(Enchantment.PROTECTION, 1, true);
			r.setItemMeta(im);
		}
		return r;
	}
}