package io.github.chw3021.items.armors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.md_5.bungee.api.ChatColor;

public class Chestplate implements Listener {


	public static ItemStack get(Integer f, Player p) {

		ItemStack r = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta rm = (LeatherArmorMeta) r.getItemMeta();
		if(f == 0) {
			rm.setColor(Color.TEAL);
			rm.addEnchant(Enchantment.PROTECTION_FIRE, 5, true);
			rm.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 5, true);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 0.36, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.armor", 6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
					"generic.armor_toughness", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("¿€æ˜∫π ªÛ¿«");
				rm.setLocalizedName("¿€æ˜∫π ªÛ¿«");
			}
			else {
				rm.setDisplayName("Miner Jacket");
				rm.setLocalizedName("Miner Jacket");
			}
			r.setItemMeta(rm);
		}
		else if(f == 1) {
			rm.setColor(Color.WHITE);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 0.3, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.armor", 6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
					"generic.armor_toughness", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", 0.15, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("º≥ªÍ ƒ⁄∆Æ");
				rm.setLocalizedName("º≥ªÍ ƒ⁄∆Æ");
			}
			else {
				rm.setDisplayName("Snowy Coat");
				rm.setLocalizedName("Snowy Coat");
			}
			r.setItemMeta(rm);
		}
		else if(f == 3) {
			rm.setColor(Color.AQUA);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 0.48, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.armor", 7, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
					"generic.armor_toughness", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("«¡∏Æ¡Ó∏”∏∞ »‰∞©");
				rm.setLocalizedName("«¡∏Æ¡Ó∏”∏∞ »‰∞©");
			}
			else {
				rm.setDisplayName("Prismarine ChestPlate");
				rm.setLocalizedName("Prismarine ChestPlate");
			}
			r.setItemMeta(rm);
		}
		else if(f == 5) {
			rm.setColor(Color.BLACK);
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.08, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 0.65, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
					"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("πÊ≈∫ æﬂªÛ");
				rm.setLocalizedName("πÊ≈∫ æﬂªÛ");
			}
			else {
				rm.setDisplayName("Flak jacket");
				rm.setLocalizedName("Flak jacket");
			}
			rm.addEnchant(Enchantment.PROTECTION_PROJECTILE, 10, true);
			r.setItemMeta(rm);
		}
		else if(f == 6) {
			r.setType(Material.CHAINMAIL_CHESTPLATE);
			ItemMeta im = (ItemMeta) r.getItemMeta();
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", 0.05, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
			im.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 0.67, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
			im.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
					"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				im.setDisplayName("«’±› »‰∞©");
				im.setLocalizedName("«’±› »‰∞©");
			}
			else {
				im.setDisplayName("Alloy Chestplate");
				im.setLocalizedName("Alloy Chestplate");
			}
			im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
			r.setItemMeta(im);
		}
		return r;
	}

	private ItemStack csc(Inventory inv, Integer cmdt, Player p) {

		if (inv.getItem(0) != null && inv.getItem(0).getAmount() >= 1
				&& inv.getItem(1) != null
				&& inv.getItem(1).getItemMeta().hasCustomModelData() ) {
			if (inv.getItem(1).getItemMeta().getCustomModelData() == cmdt) {

				ItemStack r = new ItemStack(Material.NETHERITE_CHESTPLATE);
				r.addUnsafeEnchantments(inv.getItem(0).getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.setCustomModelData(cmdt + 21000);
				if (cmdt == 5 && (inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 21000 +12)) {
					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.8, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(),
							"generic.knockback_resistance", 0.6, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("¥Î¡ˆ¿« »‰∞©");
						rm.setLocalizedName("¥Î¡ˆ¿« »‰∞©");
					}
					else {
						rm.setDisplayName("Earth Chestplate");
						rm.setLocalizedName("Earth Chestplate");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.GREEN + "Earth Set Chestplate");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.GREEN + "Earth Set Chestplate");
						rm.setLore(lore);
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 6 && ((inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 21000 +12))) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.6, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
							"generic.movement_speed", 0.1, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("º≠∏Æ »‰∞©");
						rm.setLocalizedName("º≠∏Æ »‰∞©");
					}
					else {
						rm.setDisplayName("Frost Chestplate");
						rm.setLocalizedName("Frost Chestplate");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.AQUA + "Frost Set Chestplate");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.AQUA + "Frost Set Chestplate");
						rm.setLore(lore);
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 7 && (inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 21000 +12)) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("πŸ¥Ÿ¿« »‰∞©");
						rm.setLocalizedName("πŸ¥Ÿ¿« »‰∞©");
					}
					else {
						rm.setDisplayName("Ocean Chestplate");
						rm.setLocalizedName("Ocean Chestplate");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.BLUE + "Ocean Set Chestplate");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.BLUE + "Ocean Set Chestplate");
						rm.setLore(lore);
					}
					return r;
				} else if (cmdt == 8 && (inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 21000 +12)) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 0.3, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("æÓµ“ ¿¸≈ı∫π ªÛ¿«");
						rm.setLocalizedName("æÓµ“ ¿¸≈ı∫π ªÛ¿«");
					}
					else {
						rm.setDisplayName("Dark Chestplate");
						rm.setLocalizedName("Dark Chestplate");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.DARK_GREEN + "Dark Set Chestplate");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.DARK_GREEN + "Dark Set Chestplate");
						rm.setLore(lore);
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 9 && (inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 21000 +12)) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(),
							"generic.luck", 10, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("∞≥¡∂µ» »‰∞©");
						rm.setLocalizedName("∞≥¡∂µ» »‰∞©");
					}
					else {
						rm.setDisplayName("Hyper Chestplate");
						rm.setLocalizedName("Hyper Chestplate");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.LIGHT_PURPLE + "Hyper Set Chestplate");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.LIGHT_PURPLE + "Hyper Set Chestplate");
						rm.setLore(lore);
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 10 && (inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 21000 +12)) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
							"generic.attack_damage", 10, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("¿€ø≠«œ¥¬ »‰∞©");
						rm.setLocalizedName("¿€ø≠«œ¥¬ »‰∞©");
					}
					else {
						rm.setDisplayName("Burning Chestplate");
						rm.setLocalizedName("Burning Chestplate");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.RED + "Burning Set Chestplate");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.RED + "Burning Set Chestplate");
						rm.setLore(lore);
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 12&& !inv.getItem(0).getItemMeta().hasCustomModelData()  && (inv.getItem(0).getType() == Material.NETHERITE_CHESTPLATE)) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.5, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("¿⁄ø¨¿« »‰∞©");
						rm.setLocalizedName("¿⁄ø¨¿« »‰∞©");
					}
					else {
						rm.setDisplayName("Natural Chestplate");
						rm.setLocalizedName("Natural Chestplate");
					}
					r.setItemMeta(rm);
					return r;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private ItemStack ccc(InventoryClickEvent d, Inventory inv, Integer cmdt) {

		if (inv.getType() == InventoryType.SMITHING) {
			if (inv.getItem(2) != null && d.getCurrentItem().getItemMeta().hasCustomModelData()) {
				if (d.getCurrentItem().getItemMeta().getCustomModelData() == cmdt + 21000) {
					Player p = (Player) d.getWhoClicked();
					if (p.getInventory().firstEmpty() != -1
							&& inv.getItem(2).getItemMeta().getCustomModelData() == d.getCurrentItem().getItemMeta()
									.getCustomModelData()) {

						ItemStack r = new ItemStack(Material.NETHERITE_CHESTPLATE);
						r.addUnsafeEnchantments(inv.getItem(0).getEnchantments());
						ItemMeta rm = r.getItemMeta();
						rm.setCustomModelData(cmdt + 21000);
						if (cmdt == 5) {
							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.8, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(),
									"generic.knockback_resistance", 0.6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("¥Î¡ˆ¿« »‰∞©");
								rm.setLocalizedName("¥Î¡ˆ¿« »‰∞©");
							}
							else {
								rm.setDisplayName("Earth Chestplate");
								rm.setLocalizedName("Earth Chestplate");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.GREEN + "Earth Set Chestplate");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.GREEN + "Earth Set Chestplate");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;
						} else if (cmdt == 6 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.6, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
									"generic.movement_speed", 0.1, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("º≠∏Æ »‰∞©");
								rm.setLocalizedName("º≠∏Æ »‰∞©");
							}
							else {
								rm.setDisplayName("Frost Chestplate");
								rm.setLocalizedName("Frost Chestplate");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.AQUA + "Frost Set Chestplate");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.AQUA + "Frost Set Chestplate");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;
						} else if (cmdt == 7 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("πŸ¥Ÿ¿« »‰∞©");
								rm.setLocalizedName("πŸ¥Ÿ¿« »‰∞©");
							}
							else {
								rm.setDisplayName("Ocean Chestplate");
								rm.setLocalizedName("Ocean Chestplate");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.BLUE + "Ocean Set Chestplate");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.BLUE + "Ocean Set Chestplate");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;
						} else if (cmdt == 8 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 0.3, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("¿¸≈ı∫π ªÛ¿«");
								rm.setLocalizedName("¿¸≈ı∫π ªÛ¿«");
							}
							else {
								rm.setDisplayName("Dark Chestplate");
								rm.setLocalizedName("Dark Chestplate");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.DARK_GREEN + "Dark Set Chestplate");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.DARK_GREEN + "Dark Set Chestplate");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;
						} else if (cmdt == 9 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(),
									"generic.luck", 10, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("∞≥¡∂µ» »‰∞©");
								rm.setLocalizedName("∞≥¡∂µ» »‰∞©");
							}
							else {
								rm.setDisplayName("Hyper Chestplate");
								rm.setLocalizedName("Hyper Chestplate");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.LIGHT_PURPLE + "Hyper Set Chestplate");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.LIGHT_PURPLE + "Hyper Set Chestplate");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;
						} else if (cmdt == 10 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
									"generic.attack_damage", 10, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("¿€ø≠«œ¥¬ »‰∞©");
								rm.setLocalizedName("¿€ø≠«œ¥¬ »‰∞©");
							}
							else {
								rm.setDisplayName("Burning Chestplate");
								rm.setLocalizedName("Burning Chestplate");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.RED + "Burning Set Chestplate");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.RED + "Burning Set Chestplate");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;

						} else if (cmdt == 12 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.5, Operation.ADD_SCALAR, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("¿⁄ø¨¿« »‰∞©");
								rm.setLocalizedName("¿⁄ø¨¿« »‰∞©");
							}
							else {
								rm.setDisplayName("Natural Chestplate");
								rm.setLocalizedName("Natural Chestplate");
							}
							r.setItemMeta(rm);
							return r;
						} 
						else {
							return null;
						}
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}


	@EventHandler
	public void PICE(PrepareSmithingEvent d) 
	{
		Player p = (Player)	d.getView().getPlayer();
		if (csc(d.getInventory(), 5, p) != null) {
			d.setResult(csc(d.getInventory(), 5, p));
		}
		if (csc(d.getInventory(), 6,p) != null) {
			d.setResult(csc(d.getInventory(), 6,p));
		}
		if (csc(d.getInventory(), 7,p) != null) {
			d.setResult(csc(d.getInventory(), 7,p));
		}
		if (csc(d.getInventory(), 8,p) != null) {
			d.setResult(csc(d.getInventory(), 8,p));
		}
		if (csc(d.getInventory(), 9,p) != null) {
			d.setResult(csc(d.getInventory(), 9,p));
		}
		if (csc(d.getInventory(), 10,p) != null) {
			d.setResult(csc(d.getInventory(), 10,p));
		}
		if (csc(d.getInventory(), 12,p) != null) {
			d.setResult(csc(d.getInventory(), 12,p));
		}
	
	}


	@EventHandler
	public void ICE(SmithItemEvent d) 
	{
		if (d.getClickedInventory() == null) {
			return;
		}
		if (ccc(d, d.getClickedInventory(), 5) != null) {
			ItemStack r = ccc(d, d.getClickedInventory(), 5);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ccc(d, d.getClickedInventory(), 6) != null) {
			ItemStack r = ccc(d, d.getClickedInventory(), 6);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ccc(d, d.getClickedInventory(), 7) != null) {
			ItemStack r = ccc(d, d.getClickedInventory(), 7);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ccc(d, d.getClickedInventory(), 8) != null) {
			ItemStack r = ccc(d, d.getClickedInventory(), 8);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ccc(d, d.getClickedInventory(), 9) != null) {
			ItemStack r = ccc(d, d.getClickedInventory(), 9);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ccc(d, d.getClickedInventory(), 10) != null) {
			ItemStack r = ccc(d, d.getClickedInventory(), 10);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ccc(d, d.getClickedInventory(), 12) != null) {
			ItemStack r = ccc(d, d.getClickedInventory(), 12);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
	}
}
