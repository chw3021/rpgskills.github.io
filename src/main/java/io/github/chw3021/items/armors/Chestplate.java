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

public class Chestplate extends Armors implements Listener {


	public static ItemStack get(Integer f, Player p) {

		ItemStack r = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta rm = (LeatherArmorMeta) r.getItemMeta();
		if(f == 0) {
			rm.setColor(Color.TEAL);
			rm.addEnchant(Enchantment.FIRE_PROTECTION, 5, true);
			rm.addEnchant(Enchantment.BLAST_PROTECTION, 5, true);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(max_health, 0.36, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(armor, 6, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(armor_toughness, 2, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("작업복 상의");
				rm.setItemName("작업복 상의");
			}
			else {
				rm.setDisplayName("Miner Jacket");
				rm.setItemName("Miner Jacket");
			}
			r.setItemMeta(rm);
		}
		else if(f == 1) {
			rm.setColor(Color.WHITE);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(max_health, 0.3, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(armor, 6, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(armor_toughness, 2, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(movement_speed, 0.15, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("설산 코트");
				rm.setItemName("설산 코트");
			}
			else {
				rm.setDisplayName("Snowy Coat");
				rm.setItemName("Snowy Coat");
			}
			r.setItemMeta(rm);
		}
		else if(f == 3) {
			rm.setColor(Color.AQUA);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(max_health, 0.48, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(armor, 7, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(armor_toughness, 2, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("프리즈머린 흉갑");
				rm.setItemName("프리즈머린 흉갑");
			}
			else {
				rm.setDisplayName("Prismarine ChestPlate");
				rm.setItemName("Prismarine ChestPlate");
			}
			r.setItemMeta(rm);
		}
		else if(f == 5) {
			rm.setColor(Color.BLACK);
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(movement_speed, -0.08, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(max_health, 0.65, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(armor, 8, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(armor_toughness, 3, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("방탄 야상");
				rm.setItemName("방탄 야상");
			}
			else {
				rm.setDisplayName("Flak jacket");
				rm.setItemName("Flak jacket");
			}
			rm.addEnchant(Enchantment.PROJECTILE_PROTECTION, 10, true);
			r.setItemMeta(rm);
		}
		else if(f == 6) {
			r.setType(Material.CHAINMAIL_CHESTPLATE);
			ItemMeta im = (ItemMeta) r.getItemMeta();
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(movement_speed, 0.05, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			im.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(max_health, 0.67, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			im.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(armor, 8, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(armor_toughness, 3, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				im.setDisplayName("합금 흉갑");
				im.setItemName("합금 흉갑");
			}
			else {
				im.setDisplayName("Alloy Chestplate");
				im.setItemName("Alloy Chestplate");
			}
			im.addEnchant(Enchantment.PROTECTION, 3, true);
			r.setItemMeta(im);
		}
		return r;
	}
}