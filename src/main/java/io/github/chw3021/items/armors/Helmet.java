package io.github.chw3021.items.armors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
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

public class Helmet implements Listener {


	public static ItemStack get(Integer f, Player p) {

		ItemStack r = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta rm = (LeatherArmorMeta) r.getItemMeta();
		if(f == 0) {
			rm.setColor(Color.YELLOW);
			rm.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 5, true);
			rm.addEnchant(Enchantment.PROTECTION_PROJECTILE, 5, true);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 0.25, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
					"generic.armor_toughness", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("광부의 모자");
				rm.setLocalizedName("광부의 모자");
			}
			else {
				rm.setDisplayName("Miner Helmet");
				rm.setLocalizedName("Miner Helmet");
			}
			r.setItemMeta(rm);
		}
		else if(f == 1) {
			rm.setColor(Color.WHITE);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 0.23, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
					"generic.armor_toughness", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", 0.11, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("설산 헬멧");
				rm.setLocalizedName("설산 헬멧");
			}
			else {
				rm.setDisplayName("Snowy Helmet");
				rm.setLocalizedName("Snowy Helmet");
			}
			r.setItemMeta(rm);
		}
		else if(f == 2) {
			rm.addEnchant(Enchantment.WATER_WORKER, 10, true);
			rm.addEnchant(Enchantment.OXYGEN, 10, true);
			rm.setColor(Color.NAVY);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 0.20, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
					"generic.armor_toughness", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("잠수헬멧");
				rm.setLocalizedName("잠수헬멧");
			}
			else {
				rm.setDisplayName("Scuba");
				rm.setLocalizedName("Scuba");
			}
			r.setItemMeta(rm);
		}
		else if(f == 3) {
			rm.addEnchant(Enchantment.WATER_WORKER, 3, true);
			rm.addEnchant(Enchantment.OXYGEN, 3, true);
			rm.setColor(Color.AQUA);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 0.42, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
					"generic.armor_toughness", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("프리즈머린 투구");
				rm.setLocalizedName("프리즈머린 투구");
			}
			else {
				rm.setDisplayName("Prismarine Helmet");
				rm.setLocalizedName("Prismarine Helmet");
			}
			r.setItemMeta(rm);
		}
		else if(f == 5) {
			rm.addEnchant(Enchantment.PROTECTION_PROJECTILE, 5, true);
			rm.setColor(Color.BLACK);
			rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 0.5, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
					"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName("방탄모");
				rm.setLocalizedName("방탄모");
			}
			else {
				rm.setDisplayName("Bulletproof Helmet");
				rm.setLocalizedName("Bulletproof Helmet");
			}
			r.setItemMeta(rm);
		}
		else if(f == 6) {
			r.setType(Material.CHAINMAIL_HELMET);
			ItemMeta im = (ItemMeta) r.getItemMeta();
			im.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(),
					"generic.luck", 5, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			im.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 0.56, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
			im.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
					"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				im.setDisplayName("합금 헬멧");
				im.setLocalizedName("합금 헬멧");
			}
			else {
				im.setDisplayName("Alloy Helmet");
				im.setLocalizedName("Alloy Helmet");
			}
			im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
			r.setItemMeta(im);
		}
		return r;
	}
	
	
	private ItemStack csc(Inventory inv, Integer cmdt, Player p) {

		if (inv.getItem(0) != null && inv.getItem(0).getAmount() >= 1
				&& inv.getItem(1) != null
				&& inv.getItem(1).getItemMeta().hasCustomModelData() ) {
			if (inv.getItem(1).getItemMeta().getCustomModelData() == cmdt) {

				ItemStack r = new ItemStack(Material.NETHERITE_HELMET);
				r.addUnsafeEnchantments(inv.getItem(0).getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.setCustomModelData(cmdt + 20000);
				if (cmdt == 5 &&(inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 20000 +12)) {
					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.8, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(),
							"generic.knockback_resistance", 0.6, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("대지의 투구");
						rm.setLocalizedName("대지의 투구");
					}
					else {
						rm.setDisplayName("Earth Helmet");
						rm.setLocalizedName("Earth Helmet");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.GREEN + "Earth Set Helmet");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.GREEN + "Earth Set Helmet");
						rm.setLore(lore);
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 6 && (inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 20000 +12)) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.6, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
							"generic.movement_speed", 0.1, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("서리 투구");
						rm.setLocalizedName("서리 투구");
					}
					else {
						rm.setDisplayName("Frost Helmet");
						rm.setLocalizedName("Frost Helmet");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.AQUA + "Frost Set Helmet");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.AQUA + "Frost Set Helmet");
						rm.setLore(lore);
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 7 && (inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 20000 +12)) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("바다의 투구");
						rm.setLocalizedName("바다의 투구");
					}
					else {
						rm.setDisplayName("Ocean Helmet");
						rm.setLocalizedName("Ocean Helmet");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.BLUE + "Ocean Set Helmet");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.BLUE + "Ocean Set Helmet");
						rm.setLore(lore);
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 8 && (inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 20000 +12)) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 0.3, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("어둠의 방탄모");
						rm.setLocalizedName("어둠의 방탄모");
					}
					else {
						rm.setDisplayName("Dark Helmet");
						rm.setLocalizedName("Dark Helmet");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.DARK_GREEN + "Dark Set Helmet");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.DARK_GREEN + "Dark Set Helmet");
						rm.setLore(lore);
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 9 && (inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 20000 +12)) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(),
							"generic.luck", 5, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("개조된 투구");
						rm.setLocalizedName("개조된 투구");
					}
					else {
						rm.setDisplayName("Hyper Helmet");
						rm.setLocalizedName("Hyper Helmet");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.LIGHT_PURPLE + "Hyper Set Helmet");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.LIGHT_PURPLE + "Hyper Set Helmet");
						rm.setLore(lore);
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 10 && (inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == 20000 +12)) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
							"generic.attack_damage", 7, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("작열하는 투구");
						rm.setLocalizedName("작열하는 투구");
					}
					else {
						rm.setDisplayName("Burning Helmet");
						rm.setLocalizedName("Burning Helmet");
					}
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.removeIf(l -> l.contains("Set"));
						lore.add(ChatColor.RED + "Burning Set Helmet");
						rm.setLore(lore);
					} else {
						List<String> lore = new ArrayList<>();
						lore.add(ChatColor.RED + "Burning Set Helmet");
						rm.setLore(lore);
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 12 && !inv.getItem(0).getItemMeta().hasCustomModelData() && (inv.getItem(0).getType() == Material.NETHERITE_HELMET)) {

					rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
							"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
							"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
							"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("자연의 투구");
						rm.setLocalizedName("자연의 투구");
					}
					else {
						rm.setDisplayName("Natural Helmet");
						rm.setLocalizedName("Natural Helmet");
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
				if (d.getCurrentItem().getItemMeta().getCustomModelData() == cmdt + 20000) {
					Player p = (Player) d.getWhoClicked();
					if (p.getInventory().firstEmpty() != -1
							&& inv.getItem(2).getItemMeta().getCustomModelData() == d.getCurrentItem().getItemMeta()
									.getCustomModelData()) {

						ItemStack r = new ItemStack(Material.NETHERITE_HELMET);
						r.addUnsafeEnchantments(inv.getItem(0).getEnchantments());
						ItemMeta rm = r.getItemMeta();
						rm.setCustomModelData(cmdt + 20000);
						if (cmdt == 5) {
							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.8, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(),
									"generic.knockback_resistance", 0.6, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("대지의 투구");
								rm.setLocalizedName("대지의 투구");
							}
							else {
								rm.setDisplayName("Earth Helmet");
								rm.setLocalizedName("Earth Helmet");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.GREEN + "Earth Set Helmet");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.GREEN + "Earth Set Helmet");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;
						} else if (cmdt == 6 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.6, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
									"generic.movement_speed", 0.1, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("서리 투구");
								rm.setLocalizedName("서리 투구");
							}
							else {
								rm.setDisplayName("Frost Helmet");
								rm.setLocalizedName("Frost Helmet");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.AQUA + "Frost Set Helmet");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.AQUA + "Frost Set Helmet");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;
						} else if (cmdt == 7 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("바다의 투구");
								rm.setLocalizedName("바다의 투구");
							}
							else {
								rm.setDisplayName("Ocean Helmet");
								rm.setLocalizedName("Ocean Helmet");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.BLUE + "Ocean Set Helmet");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.BLUE + "Ocean Set Helmet");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;
						} else if (cmdt == 8 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 0.3, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("어둠의 방탄모");
								rm.setLocalizedName("어둠의 방탄모");
							}
							else {
								rm.setDisplayName("Dark Helmet");
								rm.setLocalizedName("Dark Helmet");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.DARK_GREEN + "Dark Set Helmet");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.DARK_GREEN + "Dark Set Helmet");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;
						} else if (cmdt == 9 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(),
									"generic.luck", 5, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("개조된 투구");
								rm.setLocalizedName("개조된 투구");
							}
							else {
								rm.setDisplayName("Hyper Helmet");
								rm.setLocalizedName("Hyper Helmet");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.LIGHT_PURPLE + "Hyper Set Helmet");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.LIGHT_PURPLE + "Hyper Set Helmet");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;
						} else if (cmdt == 10 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
									"generic.attack_damage", 7, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("작열하는 투구");
								rm.setLocalizedName("작열하는 투구");
							}
							else {
								rm.setDisplayName("Burning Helmet");
								rm.setLocalizedName("Burning Helmet");
							}
							if (rm.hasLore()) {
								List<String> lore = rm.getLore();
								lore.removeIf(l -> l.contains("Set"));
								lore.add(ChatColor.RED + "Burning Set Helmet");
								rm.setLore(lore);
							} else {
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.RED + "Burning Set Helmet");
								rm.setLore(lore);
							}
							r.setItemMeta(rm);
							return r;
						} else if (cmdt == 12 ) {

							rm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
									"generic.max_health", 0.7, Operation.ADD_SCALAR, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
									"generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							rm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
									"generic.armor_toughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								rm.setDisplayName("자연의 투구");
								rm.setLocalizedName("자연의 투구");
							}
							else {
								rm.setDisplayName("Natural Helmet");
								rm.setLocalizedName("Natural Helmet");
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
