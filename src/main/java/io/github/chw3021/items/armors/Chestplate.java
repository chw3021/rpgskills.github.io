package io.github.chw3021.items.armors;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Chestplate extends Armors {


	public static ItemStack get(Integer f, Player p) {

		ItemStack r = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta rm = (LeatherArmorMeta) r.getItemMeta();
		if(f == 0) {
			rm.setColor(Color.TEAL);
			rm.addEnchant(Enchantment.FIRE_PROTECTION, 5, true);
			rm.addEnchant(Enchantment.BLAST_PROTECTION, 5, true);
			rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.36, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 6, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.MINING_EFFICIENCY, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("작업복 상의");
				rm.setItemName("작업복 상의");
			}
			else {
				rm.setDisplayName("Miner Jacket");
				rm.setItemName("Miner Jacket");
			}
			rm.addEnchant(Enchantment.UNBREAKING, 255, true);
			r.setItemMeta(rm);
		}
		else if(f == 1) {
			rm.setColor(Color.WHITE);
			rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.3, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 6, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.15, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("설산 코트");
				rm.setItemName("설산 코트");
			}
			else {
				rm.setDisplayName("Snowy Coat");
				rm.setItemName("Snowy Coat");
			}
			rm.addEnchant(Enchantment.UNBREAKING, 255, true);
			r.setItemMeta(rm);
		}
		else if(f == 3) {
			rm.setColor(Color.AQUA);
			rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.48, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 7, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.SUBMERGED_MINING_SPEED, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.WATER_MOVEMENT_EFFICIENCY, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("프리즈머린 흉갑");
				rm.setItemName("프리즈머린 흉갑");
			}
			else {
				rm.setDisplayName("Prismarine ChestPlate");
				rm.setItemName("Prismarine ChestPlate");
			}
			rm.addEnchant(Enchantment.UNBREAKING, 255, true);
			r.setItemMeta(rm);
		}
		else if(f == 5) {
			rm.setColor(Color.BLACK);
			rm.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(getKey(), -0.08, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.65, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 8, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("방탄 야상");
				rm.setItemName("방탄 야상");
			}
			else {
				rm.setDisplayName("Flak jacket");
				rm.setItemName("Flak jacket");
			}
			rm.addEnchant(Enchantment.PROJECTILE_PROTECTION, 10, true);
			rm.addEnchant(Enchantment.UNBREAKING, 255, true);
			r.setItemMeta(rm);
		}
		else if(f == 6) {
			r.setType(Material.CHAINMAIL_CHESTPLATE);
			ItemMeta im = (ItemMeta) r.getItemMeta();
			im.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.05, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			im.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.67, Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST));
			im.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 8, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			im.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				im.setDisplayName("합금 흉갑");
				im.setItemName("합금 흉갑");
			}
			else {
				im.setDisplayName("Alloy Chestplate");
				im.setItemName("Alloy Chestplate");
			}
			im.addEnchant(Enchantment.PROTECTION, 3, true);
			im.addEnchant(Enchantment.UNBREAKING, 255, true);
			r.setItemMeta(im);
		}
		else if(f == 7) {
			rm.setColor(Color.PURPLE);
			rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 7, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.75, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.11, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.JUMP_STRENGTH, new AttributeModifier(getKey(), 0.1, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 5, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("영혼의 흉갑");
				rm.setItemName("영혼의 흉갑");
			}
			else {
				rm.setDisplayName("Soul Chestplate");
				rm.setItemName("Soul Chestplate");
			}
			rm.addEnchant(Enchantment.PROTECTION, 3, true);
			rm.addEnchant(Enchantment.BLAST_PROTECTION, 3, true);
			rm.addEnchant(Enchantment.PROJECTILE_PROTECTION, 3, true);
			rm.addEnchant(Enchantment.FIRE_PROTECTION, 3, true);
			rm.addEnchant(Enchantment.UNBREAKING, 255, true);
			r.setItemMeta(rm);
		}
		else if(f == 8) {
			rm.setColor(Color.TEAL);
			rm.addAttributeModifier(Attribute.GRAVITY, new AttributeModifier(getKey(), -0.1, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 7, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.4, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.3, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.MOVEMENT_EFFICIENCY, new AttributeModifier(getKey(), 0.28, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.JUMP_STRENGTH, new AttributeModifier(getKey(), 0.23, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("바람의 흉갑");
				rm.setItemName("바람의 흉갑");
			}
			else {
				rm.setDisplayName("Windy Chestplate");
				rm.setItemName("Windy Chestplate");
			}
			rm.addEnchant(Enchantment.PROTECTION, 3, true);
			rm.addEnchant(Enchantment.PROJECTILE_PROTECTION, 3, true);
			rm.addEnchant(Enchantment.SOUL_SPEED, 5, true);
			rm.addEnchant(Enchantment.FIRE_PROTECTION, 3, true);
			rm.addEnchant(Enchantment.UNBREAKING, 255, true);
			r.setItemMeta(rm);
		}
		return r;
	}
}