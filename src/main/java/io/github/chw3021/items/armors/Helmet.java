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

public class Helmet extends Armors implements Listener {


	public static ItemStack get(Integer f, Player p) {

		ItemStack r = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta rm = (LeatherArmorMeta) r.getItemMeta();
		if(f == 0) {
			rm.setColor(Color.YELLOW);
			rm.addEnchant(Enchantment.BLAST_PROTECTION, 5, true);
			rm.addEnchant(Enchantment.PROJECTILE_PROTECTION, 5, true);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.25, Operation.ADD_SCALAR, EquipmentSlotGroup.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 1, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("광부의 모자");
				rm.setItemName("광부의 모자");
			}
			else {
				rm.setDisplayName("Miner Helmet");
				rm.setItemName("Miner Helmet");
			}
			r.setItemMeta(rm);
		}
		else if(f == 1) {
			rm.setColor(Color.WHITE);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.23, Operation.ADD_SCALAR, EquipmentSlotGroup.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 1, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.11, Operation.ADD_SCALAR, EquipmentSlotGroup.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("설산 헬멧");
				rm.setItemName("설산 헬멧");
			}
			else {
				rm.setDisplayName("Snowy Helmet");
				rm.setItemName("Snowy Helmet");
			}
			r.setItemMeta(rm);
		}
		else if(f == 2) {
			rm.addEnchant(Enchantment.AQUA_AFFINITY, 10, true);
			rm.addEnchant(Enchantment.RESPIRATION, 10, true);
			rm.setColor(Color.NAVY);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.20, Operation.ADD_SCALAR, EquipmentSlotGroup.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 1, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("잠수헬멧");
				rm.setItemName("잠수헬멧");
			}
			else {
				rm.setDisplayName("Scuba");
				rm.setItemName("Scuba");
			}
			r.setItemMeta(rm);
		}
		else if(f == 3) {
			rm.addEnchant(Enchantment.AQUA_AFFINITY, 3, true);
			rm.addEnchant(Enchantment.RESPIRATION, 3, true);
			rm.setColor(Color.AQUA);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.42, Operation.ADD_SCALAR, EquipmentSlotGroup.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("프리즈머린 투구");
				rm.setItemName("프리즈머린 투구");
			}
			else {
				rm.setDisplayName("Prismarine Helmet");
				rm.setItemName("Prismarine Helmet");
			}
			r.setItemMeta(rm);
		}
		else if(f == 5) {
			rm.addEnchant(Enchantment.PROJECTILE_PROTECTION, 5, true);
			rm.setColor(Color.BLACK);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.5, Operation.ADD_SCALAR, EquipmentSlotGroup.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("방탄모");
				rm.setItemName("방탄모");
			}
			else {
				rm.setDisplayName("Bulletproof Helmet");
				rm.setItemName("Bulletproof Helmet");
			}
			r.setItemMeta(rm);
		}
		else if(f == 6) {
			r.setType(Material.CHAINMAIL_HELMET);
			ItemMeta im = (ItemMeta) r.getItemMeta();
			im.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(getKey(), 5, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			im.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(getKey(), 0.56, Operation.ADD_SCALAR, EquipmentSlotGroup.HEAD));
			im.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				im.setDisplayName("합금 헬멧");
				im.setItemName("합금 헬멧");
			}
			else {
				im.setDisplayName("Alloy Helmet");
				im.setItemName("Alloy Helmet");
			}
			im.addEnchant(Enchantment.PROTECTION, 1, true);
			r.setItemMeta(im);
		}
		return r;
	}


}