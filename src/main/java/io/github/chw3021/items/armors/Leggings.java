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

public class Leggings extends Armors {

	final public static ItemStack get(Integer f, Player p) {

		ItemStack r = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta rm = (LeatherArmorMeta) r.getItemMeta();
		rm.addEnchant(Enchantment.UNBREAKING, 255, true);
		if(f == 0) {
			rm.setColor(Color.TEAL);
			rm.addEnchant(Enchantment.FIRE_PROTECTION, 3, true);
			rm.addEnchant(Enchantment.BLAST_PROTECTION, 3, true);
			rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.33, Operation.ADD_SCALAR, EquipmentSlotGroup.LEGS));
			rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
			rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("작업용 바지");
				rm.setItemName("작업용 바지");
			}
			else {
				rm.setDisplayName("Miner Pants");
				rm.setItemName("Miner Pants");
			}
			r.setItemMeta(rm);
		}
		else if(f == 1) {
			rm.setColor(Color.WHITE);
			rm.addEnchant(Enchantment.BLAST_PROTECTION, 5, true);
			rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.28, Operation.ADD_SCALAR, EquipmentSlotGroup.LEGS));
			rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
			rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 1, Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
			rm.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.15, Operation.ADD_SCALAR, EquipmentSlotGroup.LEGS));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("설산 바지");
				rm.setItemName("설산 바지");
			}
			else {
				rm.setDisplayName("Snowy Pants");
				rm.setItemName("Snowy Pants");
			}
			r.setItemMeta(rm);
		}
		else if(f == 3) {
			rm.setColor(Color.AQUA);
			rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.48, Operation.ADD_SCALAR, EquipmentSlotGroup.LEGS));
			rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 5, Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
			rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("프리즈머린 레깅스");
				rm.setItemName("프리즈머린 레깅스");
			}
			else {
				rm.setDisplayName("Prismarine Leggings");
				rm.setItemName("Prismarine Leggings");
			}
			r.setItemMeta(rm);
		}
		else if(f == 5) {
			rm.setColor(Color.BLACK);
			rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.55, Operation.ADD_SCALAR, EquipmentSlotGroup.LEGS));
			rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 6, Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
			rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
			rm.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(getKey(), -0.05, Operation.ADD_SCALAR, EquipmentSlotGroup.LEGS));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("방탄 레깅스");
				rm.setItemName("방탄 레깅스");
			}
			else {
				rm.setDisplayName("Bulletproof Leggings");
				rm.setItemName("Bulletproof Leggings");
			}
			rm.addEnchant(Enchantment.PROJECTILE_PROTECTION, 10, true);
			r.setItemMeta(rm);
		}
		else if(f == 6) {
			r.setType(Material.CHAINMAIL_LEGGINGS);
			ItemMeta im = (ItemMeta) r.getItemMeta();
			im.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.03, Operation.ADD_SCALAR, EquipmentSlotGroup.LEGS));
			im.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.68, Operation.ADD_SCALAR, EquipmentSlotGroup.LEGS));
			im.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 6, Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
			im.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				im.setDisplayName("합금 레깅스");
				im.setItemName("합금 레깅스");
			}
			else {
				im.setDisplayName("Alloy Leggings");
				im.setItemName("Alloy Leggings");
			}
			im.addEnchant(Enchantment.PROTECTION, 1, true);
			im.addEnchant(Enchantment.UNBREAKING, 255, true);
			r.setItemMeta(im);
		}
		else if(f == 7) {
			rm.setColor(Color.PURPLE);
			rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 7, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.75, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.11, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.MOVEMENT_EFFICIENCY, new AttributeModifier(getKey(), 0.16, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.JUMP_STRENGTH, new AttributeModifier(getKey(), 0.1, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 5, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("영혼의 각반");
				rm.setItemName("영혼의 각반");
			}
			else {
				rm.setDisplayName("Soul Leggings");
				rm.setItemName("Soul Leggings");
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
				rm.setDisplayName("바람의 각반");
				rm.setItemName("바람의 각반");
			}
			else {
				rm.setDisplayName("Windy Leggings");
				rm.setItemName("Windy Leggings");
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