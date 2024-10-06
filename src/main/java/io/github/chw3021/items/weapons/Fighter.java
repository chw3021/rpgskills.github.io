package io.github.chw3021.items.weapons;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import io.github.chw3021.commons.Pak;
import io.github.chw3021.rmain.RMain;

public class Fighter extends Weapons implements Listener {

	
	final private String lang = RMain.getInstance().getConfig().getString("Language");
	Pak pak = new Pak();
/*
	private ItemStack csc(Inventory inv, Integer cmdt, Player p) {
	
		final ItemStack i0 = inv.getItem(0);
		final ItemStack i1 = inv.getItem(1);
		if (i0 != null && i0.getAmount() >= 1
				&& i1 != null
				&& i1.getItemMeta().hasCustomModelData() 
				&& !i0.getItemMeta().getPersistentDataContainer()
						.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
				&& !i0.getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& !i0.getItemMeta().getPersistentDataContainer()
						.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")) {
			if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 1000 + 12
					&&i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				rm.setCustomModelData(cmdt + 1000);
				rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
						new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 15,
								Operation.ADD_NUMBER, EquipmentSlot.HAND));
				rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
						new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 14,
								Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
	
				rm.addAttributeModifier(Attribute.GENERIC_LUCK,
						new AttributeModifier(UUID.randomUUID(), "generic.luck", 30,
								Operation.ADD_NUMBER, EquipmentSlot.HAND));
				if (cmdt == 14) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"바람의 너클");
						rm.setLocalizedName(ChatColor.GOLD +"바람의 너클");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Windy Knuckle");
						rm.setLocalizedName(ChatColor.GOLD +"Windy Knuckle");
					}
					NamespacedKey.fromString("windy", RMain.getInstance());
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				}
				else if (cmdt == 5) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"대지의 너클");
						rm.setLocalizedName(ChatColor.GOLD +"대지의 너클");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Earth Knuckle");
						rm.setLocalizedName(ChatColor.GOLD +"Earth Knuckle");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 6) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"서리의 너클");
						rm.setLocalizedName(ChatColor.GOLD +"서리의 너클");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Frost Knuckle");
						rm.setLocalizedName(ChatColor.GOLD +"Frost Knuckle");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 7) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"바다의 너클");
						rm.setLocalizedName(ChatColor.GOLD +"바다의 너클");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Ocean Knuckle");
						rm.setLocalizedName(ChatColor.GOLD +"Ocean Knuckle");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 8) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"어둠의 너클");
						rm.setLocalizedName(ChatColor.GOLD +"어둠의 너클");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Dark Knuckle");
						rm.setLocalizedName(ChatColor.GOLD +"Dark Knuckle");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 9) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"개조된 너클");
						rm.setLocalizedName(ChatColor.GOLD +"개조된 너클");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Hyper Knuckle");
						rm.setLocalizedName(ChatColor.GOLD +"Hyper Knuckle");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 10) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"작열하는 너클");
						rm.setLocalizedName(ChatColor.GOLD +"작열하는 너클");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Burning Knuckle");
						rm.setLocalizedName(ChatColor.GOLD +"Burning Knuckle");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 11) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"맹독 너클");
						rm.setLocalizedName(ChatColor.GOLD +"맹독 너클");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Poison Knuckle");
						rm.setLocalizedName(ChatColor.GOLD +"Poison Knuckle");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				}  else {
					return null;
				}
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 1000 + 100+8
					&&i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 12) {
					rm.setCustomModelData(cmdt + 1000);
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 15,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 14,
									Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"자연의 너클");
						rm.setLocalizedName(ChatColor.GOLD +"자연의 너클");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Natural Knuckle");
						rm.setLocalizedName(ChatColor.GOLD +"Natural Knuckle");
					}
					r.setItemMeta(rm);
					return r;
				}
				return null;
			} 
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 1000 +60
					&&i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 14) {
					rm.setCustomModelData(1 + 1000+100);
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 6,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 6,
									Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 1단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 1단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.1");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.1");
					}
					r.setItemMeta(rm);
					return r;
				}
				return null;
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() <= 1000 + 100+7 &&i0.getItemMeta().getCustomModelData() >= 1000 + 100+1
					&&i1.getItemMeta().getCustomModelData() == cmdt && i0.getItemMeta().getCustomModelData() == cmdt -3 + 1000 + 100 -1) {
	
				ItemStack r = i0.clone();
				////r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.setCustomModelData(cmdt -3 + 1000 + 100);
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 5) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 7,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 7,
									Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 2단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 2단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.2");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.2");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 6) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 8,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 8,
									Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 3단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 3단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.3");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.3");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 7) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 9,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 9,
									Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 4단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 4단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.4");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.4");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 8) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 10,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 10,
									Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 5단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 5단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.5");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.5");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 9) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 11,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 11,
									Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 6단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 6단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.6");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.6");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 10) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 12,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 12,
									Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 7단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 7단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.7");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.7");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 11) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 13,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 13,
									Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 8단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 너클 - 8단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.8");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Knuckle - Lv.8");
					}
					r.setItemMeta(rm);
					return r;
				}
				return null;
			}
			else {
				return null;
			}
		} else {
			return null;
		}
	}

	private ItemStack ccc(SmithItemEvent d, Inventory inv, Integer cmdt) {
	
		if (inv.getType() == InventoryType.SMITHING) {
			if(d.getCurrentItem() == null || !d.getCurrentItem().hasItemMeta()) {
				return null;
			}
			if (inv.getItem(2) != null && inv.getItem(0) != null&& inv.getItem(1) != null && d.getCurrentItem().getItemMeta().hasCustomModelData()) {
				Player p = (Player) d.getWhoClicked();
				if (p.getInventory().firstEmpty() != -1
						&& inv.getItem(2).getItemMeta().getCustomModelData() == d.getCurrentItem().getItemMeta()
								.getCustomModelData()
								&& inv.getItem(1).getItemMeta().hasCustomModelData()
						&& inv.getItem(1).getItemMeta().getCustomModelData() == cmdt
						&& (!inv.getItem(0).getItemMeta().hasCustomModelData() || inv.getItem(0).getItemMeta().getCustomModelData() != d.getCurrentItem().getItemMeta()
								.getCustomModelData())
						&& !d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(nethercore,
								PersistentDataType.STRING)
						&& !d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(endercore,
								PersistentDataType.STRING)
						&& !d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(herocore,
								PersistentDataType.STRING)
						&& !inv.getItem(2).getItemMeta().getPersistentDataContainer().has(nethercore,
								PersistentDataType.STRING)
						&& !inv.getItem(2).getItemMeta().getPersistentDataContainer().has(endercore,
								PersistentDataType.STRING)
						&& !inv.getItem(2).getItemMeta().getPersistentDataContainer().has(herocore,
								PersistentDataType.STRING)) {
					return inv.getItem(2).clone();
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

	private ItemStack nsc(Inventory inv, Integer cmdt, Player p) {

		if (inv.getItem(0) != null
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer().has(nethercore, PersistentDataType.STRING)
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer().has(endercore, PersistentDataType.STRING)
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer().has(herocore, PersistentDataType.STRING)
				&& inv.getItem(0).getAmount() >= 1 && inv.getItem(0).getItemMeta().hasCustomModelData()
				&& inv.getItem(1) != null && inv.getItem(1).getItemMeta().hasCustomModelData()) {

			if (((1000 + 1 <= inv.getItem(0).getItemMeta().getCustomModelData()
					&& inv.getItem(0).getItemMeta().getCustomModelData() <= 1000 + 11))
					&& inv.getItem(1).getItemMeta().getCustomModelData() == cmdt) {

				ItemStack r = inv.getItem(0).clone();
				ItemMeta rm = r.getItemMeta();
				return nethercore(nethercore, cmdt, r, rm, p);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private ItemStack ncc(SmithItemEvent d, Inventory inv, Integer cmdt) {

		if (d.getClickedInventory().getType() == InventoryType.SMITHING) {
			if (d.getClickedInventory().getItem(2) != null && d.getCurrentItem().getItemMeta().hasCustomModelData()
					&& d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(nethercore,
							PersistentDataType.STRING)
					&& !d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(endercore,
							PersistentDataType.STRING)
					&& !d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(herocore,
							PersistentDataType.STRING)
					&& inv.getItem(2).getItemMeta().getPersistentDataContainer().has(nethercore,
							PersistentDataType.STRING)
					&& !inv.getItem(2).getItemMeta().getPersistentDataContainer().has(endercore,
							PersistentDataType.STRING)
					&& !inv.getItem(2).getItemMeta().getPersistentDataContainer().has(herocore,
							PersistentDataType.STRING)) {

				Player p = (Player) d.getWhoClicked();
				ItemStack r = d.getInventory().getItem(0).clone();
				ItemMeta rm = r.getItemMeta();

				if (p.getInventory().firstEmpty() != -1 && inv.getItem(1).getItemMeta().hasCustomModelData()
						&& d.getInventory().getItem(1).getItemMeta().getCustomModelData() == cmdt) {
					return nethercore(nethercore, cmdt, r, rm, p);
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

	private ItemStack esc(Inventory inv, Integer cmdt, Player p) {

		if (inv.getItem(0) != null
				&& inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")
				&& inv.getItem(0).getAmount() >= 1 && inv.getItem(0).getItemMeta().hasCustomModelData()
				&& inv.getItem(1) != null && inv.getItem(1).getItemMeta().hasCustomModelData()) {

			if (((1001 <= inv.getItem(0).getItemMeta().getCustomModelData()
					&& inv.getItem(0).getItemMeta().getCustomModelData() <= 1011))
					&& inv.getItem(1).getItemMeta().getCustomModelData() == cmdt) {

				ItemStack r = inv.getItem(0).clone();
				ItemMeta rm = r.getItemMeta();
				return endercore(endercore, cmdt, r, rm, p);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private ItemStack ecc(SmithItemEvent d, Inventory inv, Integer cmdt) {

		if (d.getClickedInventory().getType() == InventoryType.SMITHING) {
			if (d.getClickedInventory().getItem(2) != null && d.getCurrentItem().getItemMeta().hasCustomModelData()
					&& d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(nethercore,
							PersistentDataType.STRING)
					&& d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(endercore,
							PersistentDataType.STRING)
					&& !d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(herocore,
							PersistentDataType.STRING)
					&& inv.getItem(2).getItemMeta().getPersistentDataContainer().has(nethercore,
							PersistentDataType.STRING)
					&& inv.getItem(2).getItemMeta().getPersistentDataContainer().has(endercore,
							PersistentDataType.STRING)
					&& !inv.getItem(2).getItemMeta().getPersistentDataContainer().has(herocore,
							PersistentDataType.STRING)) {

				Player p = (Player) d.getWhoClicked();
				ItemStack r = d.getInventory().getItem(0).clone();
				ItemMeta rm = r.getItemMeta();

				if (p.getInventory().firstEmpty() != -1 && inv.getItem(1).getItemMeta().hasCustomModelData()
						&& d.getInventory().getItem(1).getItemMeta().getCustomModelData() == cmdt) {
					return endercore(endercore, cmdt, r, rm, p);
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

	private ItemStack hsc(Inventory inv, Integer cmdt, Player p) {

		if (inv.getItem(0) != null
				&& inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
				&& inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")
				&& inv.getItem(0).getAmount() >= 1 && inv.getItem(0).getItemMeta().hasCustomModelData()
				&& inv.getItem(1) != null && inv.getItem(1).getItemMeta().hasCustomModelData()) {

			if (((1001 <= inv.getItem(0).getItemMeta().getCustomModelData()
					&& inv.getItem(0).getItemMeta().getCustomModelData() <= 1011))
					&& inv.getItem(1).getItemMeta().getCustomModelData() == cmdt) {

				ItemStack r = inv.getItem(0).clone();
				ItemMeta rm = r.getItemMeta();
				return herocore(herocore, cmdt, r, rm, p);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private ItemStack hcc(SmithItemEvent d, Inventory inv, Integer cmdt) {

		if (d.getClickedInventory().getType() == InventoryType.SMITHING) {
			if (d.getClickedInventory().getItem(2) != null && d.getCurrentItem().getItemMeta().hasCustomModelData()
					&& d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(nethercore,
							PersistentDataType.STRING)
					&& d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(endercore,
							PersistentDataType.STRING)
					&& d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(herocore,
							PersistentDataType.STRING)
					&& inv.getItem(2).getItemMeta().getPersistentDataContainer().has(nethercore,
							PersistentDataType.STRING)
					&& inv.getItem(2).getItemMeta().getPersistentDataContainer().has(endercore,
							PersistentDataType.STRING)
					&& inv.getItem(2).getItemMeta().getPersistentDataContainer().has(herocore,
							PersistentDataType.STRING)) {

				Player p = (Player) d.getWhoClicked();
				ItemStack r = d.getInventory().getItem(0).clone();
				ItemMeta rm = r.getItemMeta();

				if (p.getInventory().firstEmpty() != -1 && inv.getItem(1).getItemMeta().hasCustomModelData()
						&& d.getInventory().getItem(1).getItemMeta().getCustomModelData() == cmdt) {
					return herocore(herocore, cmdt, r, rm, p);
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
		Player p = (Player) d.getView().getPlayer();
		if (csc(d.getInventory(), 14, p) != null) {
			d.setResult(csc(d.getInventory(), 14, p));
		}
		if (csc(d.getInventory(), 5, p) != null) {
			d.setResult(csc(d.getInventory(), 5, p));
		}
		if (csc(d.getInventory(), 6, p) != null) {
			d.setResult(csc(d.getInventory(), 6, p));
		}
		if (csc(d.getInventory(), 7, p) != null) {
			d.setResult(csc(d.getInventory(), 7, p));
		}
		if (csc(d.getInventory(), 8, p) != null) {
			d.setResult(csc(d.getInventory(), 8, p));
		}
		if (csc(d.getInventory(), 9, p) != null) {
			d.setResult(csc(d.getInventory(), 9, p));
		}
		if (csc(d.getInventory(), 10, p) != null) {
			d.setResult(csc(d.getInventory(), 10, p));
		}
		if (csc(d.getInventory(), 11, p) != null) {
			d.setResult(csc(d.getInventory(), 11, p));
		}
		if (csc(d.getInventory(), 12, p) != null) {
			d.setResult(csc(d.getInventory(), 12, p));
		}
		if (nsc(d.getInventory(), -2, p) != null) {
			d.setResult(nsc(d.getInventory(), -2,p));
		}
		if (nsc(d.getInventory(), -3,p) != null) {
			d.setResult(nsc(d.getInventory(), -3,p));
		}
	
		if (nsc(d.getInventory(), -4,p) != null) {
			d.setResult(nsc(d.getInventory(), -4,p));
		}
		if (nsc(d.getInventory(), -5,p) != null) {
			d.setResult(nsc(d.getInventory(), -5,p));
		}
		if (esc(d.getInventory(), -6,p) != null) {
			d.setResult(esc(d.getInventory(), -6,p));
		}
		if (esc(d.getInventory(), -7,p) != null) {
			d.setResult(esc(d.getInventory(), -7,p));
		}
		if (hsc(d.getInventory(), -8,p) != null) {
			d.setResult(hsc(d.getInventory(), -8,p));
		}
	
	
	}

	@EventHandler
	public void ICE(SmithItemEvent d) 
	{
		if (d.getClickedInventory() == null) {
			return;
		}
		if (ccc(d, d.getClickedInventory(), 14) != null) {
			ItemStack r = ccc(d, d.getClickedInventory(), 14);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		else if (ccc(d, d.getClickedInventory(), 5) != null) {
			ItemStack r = ccc(d, d.getClickedInventory(), 5);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		else if (ccc(d, d.getClickedInventory(), 6) != null) {
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
		if (ccc(d, d.getClickedInventory(), 11) != null) {
			ItemStack r = ccc(d, d.getClickedInventory(), 11);
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
		if (ncc(d, d.getClickedInventory(), -2) != null) {
			ItemStack r = ncc(d, d.getClickedInventory(), -2);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ncc(d, d.getClickedInventory(), -3) != null) {
			ItemStack r = ncc(d, d.getClickedInventory(), -3);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ncc(d, d.getClickedInventory(), -4) != null) {
			ItemStack r = ncc(d, d.getClickedInventory(), -4);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ncc(d, d.getClickedInventory(), -5) != null) {
			ItemStack r = ncc(d, d.getClickedInventory(), -5);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ecc(d, d.getClickedInventory(), -6) != null) {
			ItemStack r = ecc(d, d.getClickedInventory(), -6);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ecc(d, d.getClickedInventory(), -7) != null) {
			ItemStack r = ecc(d, d.getClickedInventory(), -7);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (hcc(d, d.getClickedInventory(), -8) != null) {
			ItemStack r = hcc(d, d.getClickedInventory(), -8);
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
	}*/

	@EventHandler
	public void KnuckleEquip(PlayerSwapHandItemsEvent ev) {
		Player p = ev.getPlayer();
		ItemStack pis = p.getInventory().getItemInMainHand();
		ItemStack pos = p.getInventory().getItemInOffHand();
		if (pis.getAmount() >= 1 && pis.hasItemMeta() && pis.getItemMeta().hasCustomModelData()
				&& pis.getType().name().contains("BANNER_PATTERN") && !pis.getItemMeta().getLocalizedName().contains("CopiedKnuckle") && pos.getType() == Material.AIR) {
			ev.setCancelled(true);
			ItemStack swap = pis.clone();
			ItemMeta sm = swap.getItemMeta();
			sm.setLocalizedName("CopiedKnuckle");
			sm.getPersistentDataContainer().remove(nethercore);
			sm.getPersistentDataContainer().remove(endercore);
			sm.getPersistentDataContainer().remove(herocore);
			sm =pak.remelm(sm);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				sm.setLore(Arrays.asList("보조 너클"));
			}
			else {
				sm.setLore(Arrays.asList("Second Knuckle"));
			}
			swap.setItemMeta(sm);
			swap.setAmount(1);
			p.getInventory().setItemInOffHand(swap);
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, 1, 1.36f);
		}

	}

	@EventHandler
	public void KnuckleUnEquip(InventoryClickEvent ev) {
		Player p =(Player) ev.getView().getPlayer();
		if(ev.getCurrentItem() != null && ev.getCurrentItem().hasItemMeta()) {
			ItemMeta im = ev.getCurrentItem().getItemMeta();
			if(im.hasLocalizedName() && im.getLocalizedName().equals("CopiedKnuckle")) {
				ev.setCancelled(true);
				ev.getCurrentItem().setAmount(0);
			}
		}
		for(ItemStack is : p.getInventory().getContents()) {
			if(is != null && is.hasItemMeta()) {
				ItemMeta im = is.getItemMeta();
				if(im.hasLocalizedName() && im.getLocalizedName().equals("CopiedKnuckle")) {
					is.setAmount(0);
				}
			}
		}

	}

	@EventHandler
	public void KnuckleUnEquip(PlayerDropItemEvent ev) {
		if(ev.getItemDrop().getItemStack() != null && !ev.isCancelled()) {
			Player p =(Player) ev.getPlayer();
			ItemStack pis = ev.getItemDrop().getItemStack();
			if(pis.getItemMeta().hasLocalizedName() && pis.getItemMeta().getLocalizedName().equals("CopiedKnuckle")) {
				pis.setAmount(0);
			}
			for(ItemStack is : p.getInventory().getContents()) {
				if(is != null && is.hasItemMeta()) {
					ItemMeta im = is.getItemMeta();
					if(im.hasLocalizedName() && im.getLocalizedName().equals("CopiedKnuckle")) {
						is.setAmount(0);
					}
				}
			}
		}
	}

	@EventHandler
	public void KnuckleUnEquip(PlayerDeathEvent ev) 
	{
		if(!ev.getKeepInventory()) {

	       	 List<ItemStack> di = ev.getDrops();
	       	 di.forEach(is -> {
	       		 ItemMeta im = is.getItemMeta();
	       		 if(im.hasLocalizedName() && im.getLocalizedName().equals("CopiedKnuckle")) {
	       			 is.setAmount(0);
	       		 }
	       	 });
		}
	}
	
	@EventHandler
	public void WoodenKnuckle(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 1, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 1, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		meta.setCustomModelData(1000);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		if(lang.contains("kr")){
			meta.setLore(Arrays.asList("손교체시 장착"));
			meta.setDisplayName("나무 너클");
			meta.setLocalizedName("나무 너클");
		}
		else {
			meta.setDisplayName("Wooden Knuckle");
			meta.setLocalizedName("Wooden Knuckle");
			meta.setLore(Arrays.asList("SwapHands To Equip"));
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "wooden_knuckle"), is);
		rc.shape("xax", "bxb");
		RecipeChoice rcc =  new RecipeChoice.MaterialChoice(Material.ACACIA_PLANKS,Material.BIRCH_PLANKS,Material.CRIMSON_PLANKS,Material.DARK_OAK_PLANKS,Material.JUNGLE_PLANKS,Material.OAK_PLANKS,Material.SPRUCE_PLANKS,Material.WARPED_PLANKS);
		rc.setIngredient('x', rcc);
		rc.setIngredient('b', Material.AIR);
		rc.setIngredient('a', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}
	
	@EventHandler
	public void StoneKnuckle(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 2, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 1, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		meta.setCustomModelData(1000 +1);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		if(lang.contains("kr")){
			meta.setLore(Arrays.asList("손교체시 장착"));
			meta.setDisplayName("돌 너클");
			meta.setLocalizedName("돌 너클");
		}
		else {
			meta.setDisplayName("Stone Knuckle");
			meta.setLocalizedName("Stone Knuckle");
			meta.setLore(Arrays.asList("SwapHands To Equip"));
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "stone_knuckle"), is);
		rc.shape("xax", "bxb");
		rc.setIngredient('x', Material.COBBLESTONE);
		rc.setIngredient('b', Material.AIR);
		rc.setIngredient('a', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void GoldKnuckle(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 2, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 1, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		meta.setCustomModelData(1000 + 2);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		if(lang.contains("kr")){
			meta.setLore(Arrays.asList("손교체시 장착"));
			meta.setDisplayName("금 너클");
			meta.setLocalizedName("금 너클");
		}
		else {
			meta.setDisplayName("Gold Knuckle");
			meta.setLocalizedName("Gold Knuckle");
			meta.setLore(Arrays.asList("SwapHands To Equip"));
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "gold_knuckle"), is);
		rc.shape("xax", "bxb");
		rc.setIngredient('x', Material.GOLD_INGOT);
		rc.setIngredient('b', Material.AIR);
		rc.setIngredient('a', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void IronKnuckle(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 3, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 2, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		meta.setCustomModelData(1000 + 3);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		if(lang.contains("kr")){
			meta.setLore(Arrays.asList("손교체시 장착"));
			meta.setDisplayName("철 너클");
			meta.setLocalizedName("철 너클");
		}
		else {
			meta.setDisplayName("Iron Knuckle");
			meta.setLocalizedName("Iron Knuckle");
			meta.setLore(Arrays.asList("SwapHands To Equip"));
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "iron_knuckle"), is);
		rc.shape("xax", "bxb");
		rc.setIngredient('x', Material.IRON_INGOT);
		rc.setIngredient('b', Material.AIR);
		rc.setIngredient('a', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void DiamondKnuckle(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 4, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 3, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		meta.setCustomModelData(1000 + 4);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		if(lang.contains("kr")){
			meta.setLore(Arrays.asList("손교체시 장착"));
			meta.setDisplayName("다이아몬드 너클");
			meta.setLocalizedName("다이아몬드 너클");
		}
		else {
			meta.setDisplayName("Diamond Knuckle");
			meta.setLocalizedName("Diamond Knuckle");
			meta.setLore(Arrays.asList("SwapHands To Equip"));
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "diamond_knuckle"), is);
		rc.shape("xax", "bxb");
		rc.setIngredient('x', Material.DIAMOND);
		rc.setIngredient('b', Material.AIR);
		rc.setIngredient('a', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void NetheriteKnuckle(PrepareSmithingEvent d) {
		ItemStack r = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
		ItemMeta rm = r.getItemMeta();
		rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 5, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 4, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.HAND));
		rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
				new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 8,
						Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		rm.setCustomModelData(1000 + 60);
		rm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		Player p = (Player) d.getView().getPlayer();
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			rm.setLore(Arrays.asList("손교체시 장착"));
			rm.setDisplayName("네더라이트 너클");
			rm.setLocalizedName("네더라이트 너클");
		}
		else {
			rm.setDisplayName("Netherite Knuckle");
			rm.setLocalizedName("Netherite Knuckle");
			rm.setLore(Arrays.asList("SwapHands To Equip"));
		}

		if (d.getInventory().getItem(0) != null && d.getInventory().getItem(0).getAmount() >= 1
				&& d.getInventory().getItem(0).getItemMeta().hasCustomModelData()
				&& d.getInventory().getItem(1) != null
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")) {
			if ((d.getInventory().getItem(0).getItemMeta().getCustomModelData() == 1000 + 4)
					&& d.getInventory().getItem(1).getType() == Material.NETHERITE_INGOT) {
				ItemMeta rm0 = d.getInventory().getItem(0).getItemMeta();
				if(!rm0.getDisplayName().equals(rm0.getLocalizedName())) {
					rm.setDisplayName(rm0.getDisplayName());
				}
				r.setItemMeta(rm);
				r.addUnsafeEnchantments(d.getInventory().getItem(0).getEnchantments());
				d.setResult(r);
			}
		}

	}

	@EventHandler
	public void NetheriteKnuckle(SmithItemEvent d) {
		if (d.getClickedInventory() == null) {
			return;
		}
		if (d.getClickedInventory().getType() == InventoryType.SMITHING) {
			if (d.getClickedInventory().getItem(2) != null && d.getCurrentItem().getItemMeta().hasCustomModelData()
					&& d.getInventory().getItem(0).getItemMeta().hasCustomModelData()
					&& d.getInventory().getItem(1) != null
					&& !d.getInventory().getItem(2).getItemMeta().getPersistentDataContainer()
							.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
					&& !d.getInventory().getItem(2).getItemMeta().getPersistentDataContainer()
							.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
					&& !d.getInventory().getItem(2).getItemMeta().getPersistentDataContainer()
							.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")) {
				if (d.getCurrentItem().getItemMeta().getCustomModelData() == 1000 + 60) {
					Player p = (Player) d.getWhoClicked();
					ItemStack r = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
					ItemMeta rm = r.getItemMeta();
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
							"generic.attackDamage", 5, Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
							"generic.attackDamage", 4, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
					rm.setCustomModelData(1000 + 60);
					rm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setLore(Arrays.asList("손교체시 장착"));
						rm.setDisplayName("네더라이트 너클");
						rm.setLocalizedName("네더라이트 너클");
					}
					else {
						rm.setDisplayName("Netherite Knuckle");
						rm.setLocalizedName("Netherite Knuckle");
						rm.setLore(Arrays.asList("SwapHands To Equip"));
					}

					ItemMeta rm0 = d.getInventory().getItem(0).getItemMeta();
					if(!rm0.getDisplayName().equals(rm0.getLocalizedName())) {
						rm.setDisplayName(rm0.getDisplayName());
					}
					r.setItemMeta(rm);
					if (p.getInventory().firstEmpty() != -1 && d.getClickedInventory().getItem(2).getItemMeta()
							.getCustomModelData() == d.getCurrentItem().getItemMeta().getCustomModelData()) {
						r.addUnsafeEnchantments(d.getInventory().getItem(0).getEnchantments());
						p.getInventory().addItem(r);
						d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
						d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
						d.getClickedInventory().getItem(2).setAmount(0);
					}
				}
			}
		}

	}

	

	

	/*@EventHandler
	public void NetheriteKnuckle(SmithItemEvent d) {
		if (d.getClickedInventory() == null) {
			return;
		}
		if (d.getClickedInventory().getType() == InventoryType.SMITHING) {
			if (d.getClickedInventory().getItem(2) != null && d.getCurrentItem().getItemMeta().hasCustomModelData()
					&& d.getInventory().getItem(0).getItemMeta().hasCustomModelData()
					&& d.getInventory().getItem(1) != null
					&& !d.getInventory().getItem(2).getItemMeta().getPersistentDataContainer()
							.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
					&& !d.getInventory().getItem(2).getItemMeta().getPersistentDataContainer()
							.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
					&& !d.getInventory().getItem(2).getItemMeta().getPersistentDataContainer()
							.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")) {
				if (d.getCurrentItem().getItemMeta().getCustomModelData() == 2011) {
					Player p = (Player) d.getWhoClicked();
					ItemStack r = new ItemStack(Material.BOW);
					ItemMeta rm = r.getItemMeta();
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
							"generic.attackDamage", 11, Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.setCustomModelData(1000 +11);
					rm.setDisplayName("Netherite Knuckle");
					rm.setLocalizedName("Netherite Knuckle");
					r.setItemMeta(rm);
					if (p.getInventory().firstEmpty() != -1 && d.getClickedInventory().getItem(2).getItemMeta()
							.getCustomModelData() == d.getCurrentItem().getItemMeta().getCustomModelData()) {
						p.getInventory().addItem(r);
						d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
						d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
						d.getClickedInventory().getItem(2).setAmount(0);
					}
				}
			}
		}
	
	}*/

}