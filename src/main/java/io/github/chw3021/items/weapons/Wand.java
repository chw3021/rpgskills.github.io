package io.github.chw3021.items.weapons;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import io.github.chw3021.commons.Pak;
import io.github.chw3021.rmain.RMain;

public class Wand extends Weapons implements Listener {
/*
	final private NamespacedKey nethercore = new NamespacedKey(RMain.getInstance(), "wand_nether_core");
	final private NamespacedKey endercore = new NamespacedKey(RMain.getInstance(), "wand_ender_core");
	final private NamespacedKey herocore = new NamespacedKey(RMain.getInstance(), "wand_hero_core");*/

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
			if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 9000 + 12
					&&i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				rm.setCustomModelData(cmdt + 9000);
				rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
						new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 29,
								Operation.ADD_NUMBER, EquipmentSlot.HAND));
	
				rm.addAttributeModifier(Attribute.GENERIC_LUCK,
						new AttributeModifier(UUID.randomUUID(), "generic.luck", 30,
								Operation.ADD_NUMBER, EquipmentSlot.HAND));
				if (cmdt == 14) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"바람의 완드");
						rm.setLocalizedName(ChatColor.GOLD +"바람의 완드");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Windy Wand");
						rm.setLocalizedName(ChatColor.GOLD +"Windy Wand");
					}
					NamespacedKey.fromString("windy", RMain.getInstance());
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				}
				else if (cmdt == 5) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"대지의 완드");
						rm.setLocalizedName(ChatColor.GOLD +"대지의 완드");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Earth Wand");
						rm.setLocalizedName(ChatColor.GOLD +"Earth Wand");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 6) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"서리의 완드");
						rm.setLocalizedName(ChatColor.GOLD +"서리의 완드");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Frost Wand");
						rm.setLocalizedName(ChatColor.GOLD +"Frost Wand");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 7) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"바다의 완드");
						rm.setLocalizedName(ChatColor.GOLD +"바다의 완드");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Ocean Wand");
						rm.setLocalizedName(ChatColor.GOLD +"Ocean Wand");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 8) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"어둠의 완드");
						rm.setLocalizedName(ChatColor.GOLD +"어둠의 완드");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Dark Wand");
						rm.setLocalizedName(ChatColor.GOLD +"Dark Wand");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 9) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"개조된 완드");
						rm.setLocalizedName(ChatColor.GOLD +"개조된 완드");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Hyper Wand");
						rm.setLocalizedName(ChatColor.GOLD +"Hyper Wand");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 10) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"작열하는 완드");
						rm.setLocalizedName(ChatColor.GOLD +"작열하는 완드");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Burning Wand");
						rm.setLocalizedName(ChatColor.GOLD +"Burning Wand");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 11) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"맹독 완드");
						rm.setLocalizedName(ChatColor.GOLD +"맹독 완드");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Poison Wand");
						rm.setLocalizedName(ChatColor.GOLD +"Poison Wand");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				}  else {
					return null;
				}
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 9000 + 100+8
					&&i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 12) {
					rm.setCustomModelData(cmdt + 9000);
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 29,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"자연의 완드");
						rm.setLocalizedName(ChatColor.GOLD +"자연의 완드");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Natural Wand");
						rm.setLocalizedName(ChatColor.GOLD +"Natural Wand");
					}
					r.setItemMeta(rm);
					return r;
				}
				return null;
			} 
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 9000 +60
					&&i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 14) {
					rm.setCustomModelData(1 + 9000+100);
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 12,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 1단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 1단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.1");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.1");
					}
					r.setItemMeta(rm);
					return r;
				}
				return null;
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() <= 9000 + 100+7 &&i0.getItemMeta().getCustomModelData() >= 9000 + 100+1
					&&i1.getItemMeta().getCustomModelData() == cmdt && i0.getItemMeta().getCustomModelData() == cmdt -3 + 9000 + 100 -1) {
	
				ItemStack r = i0.clone();
				////r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.setCustomModelData(cmdt -3 + 9000 + 100);
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 5) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 14,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 2단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 2단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.2");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.2");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 6) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 16,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 3단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 3단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.3");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.3");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 7) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 18,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 4단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 4단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.4");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.4");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 8) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 20,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 5단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 5단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.5");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.5");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 9) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 22,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 6단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 6단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.6");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.6");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 10) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 24,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 7단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 7단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.7");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.7");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 11) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 26,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 8단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 완드 - 8단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.8");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Wand - Lv.8");
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

			if (((9001 <= inv.getItem(0).getItemMeta().getCustomModelData()
					&& inv.getItem(0).getItemMeta().getCustomModelData() <= 9011))
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

			if (((9001 <= inv.getItem(0).getItemMeta().getCustomModelData()
					&& inv.getItem(0).getItemMeta().getCustomModelData() <= 9011))
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

			if (((9001 <= inv.getItem(0).getItemMeta().getCustomModelData()
					&& inv.getItem(0).getItemMeta().getCustomModelData() <= 9011))
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

	public int eck(Inventory inv) 
	{

		if(inv.getItem(5) != null&& inv.getItem(5).getType() == Material.BLAZE_ROD && inv.getItem(5).hasItemMeta()&& inv.getItem(5).getItemMeta().hasCustomModelData()){
			return 5;
		}
		else if(inv.getItem(1) != null&& inv.getItem(1).getType() == Material.BLAZE_ROD&& inv.getItem(1).hasItemMeta() && inv.getItem(1).getItemMeta().hasCustomModelData()){
			return 1;
		}
		else if(inv.getItem(2) != null&& inv.getItem(2).getType() == Material.BLAZE_ROD&& inv.getItem(2).hasItemMeta() && inv.getItem(2).getItemMeta().hasCustomModelData()){
			return 2;
		}
		else if(inv.getItem(3) != null&& inv.getItem(3).getType() == Material.BLAZE_ROD&& inv.getItem(3).hasItemMeta() && inv.getItem(3).getItemMeta().hasCustomModelData()){
				return 3;
		}
		else if(inv.getItem(4) != null&& inv.getItem(4).getType() == Material.BLAZE_ROD&& inv.getItem(4).hasItemMeta() && inv.getItem(4).getItemMeta().hasCustomModelData()){
				return 4;
		}
		else if(inv.getItem(6) != null&& inv.getItem(6).getType() == Material.BLAZE_ROD&& inv.getItem(6).hasItemMeta() && inv.getItem(6).getItemMeta().hasCustomModelData()){
				return 6;
		}
		else if(inv.getItem(7) != null&& inv.getItem(7).getType() == Material.BLAZE_ROD&& inv.getItem(7).hasItemMeta() && inv.getItem(7).getItemMeta().hasCustomModelData()){
				return 7;
		}
		else if(inv.getItem(8) != null&& inv.getItem(8).getType() == Material.BLAZE_ROD&& inv.getItem(8).hasItemMeta() && inv.getItem(8).getItemMeta().hasCustomModelData()){
				return 8;
		}
		else if(inv.getItem(9) != null&& inv.getItem(9).getType() == Material.BLAZE_ROD&& inv.getItem(9).hasItemMeta() && inv.getItem(9).getItemMeta().hasCustomModelData()){
				return 9;
		}
		else {
			return -1;
		}
	}
	
	@EventHandler
	public void WandBlazePowderCancel(PrepareItemCraftEvent d) {

		if(eck(d.getInventory())>-1) {
			d.getInventory().setResult(null);
		}

	}

	@EventHandler
	public void WoodenWand(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 3, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.setCustomModelData(9000);
		if(lang.contains("kr")){
			meta.setDisplayName("나무 지팡이");
			meta.setLocalizedName("나무 지팡이");
		}
		else {
			meta.setDisplayName("Wooden Wand");
			meta.setLocalizedName("Wooden Wand");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "wooden_wand"), is);
		rc.shape("axx", "xsx", "xxs");
		rc.shape("xxa", "xsx", "sxx");
		RecipeChoice rcc =  new RecipeChoice.MaterialChoice(Material.ACACIA_PLANKS,Material.BIRCH_PLANKS,Material.CRIMSON_PLANKS,Material.DARK_OAK_PLANKS,Material.JUNGLE_PLANKS,Material.OAK_PLANKS,Material.SPRUCE_PLANKS,Material.WARPED_PLANKS);
		rc.setIngredient('a', rcc);
		rc.setIngredient('x', Material.AIR);
		rc.setIngredient('s', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}
	
	@EventHandler
	public void StoneWand(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 5, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.setCustomModelData(9000 + 1);
		if(lang.contains("kr")){
			meta.setDisplayName("돌 지팡이");
			meta.setLocalizedName("돌 지팡이");
		}
		else {
			meta.setDisplayName("Stone Wand");
			meta.setLocalizedName("Stone Wand");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "stone_wand"), is);
		rc.shape("axx", "xsx", "xxs");
		rc.shape("xxa", "xsx", "sxx");
		rc.setIngredient('s', Material.STICK);
		rc.setIngredient('x', Material.AIR);
		rc.setIngredient('a', Material.COBBLESTONE);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void GoldWand(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 4, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.setCustomModelData(9000 + 2);
		if(lang.contains("kr")){
			meta.setDisplayName("금 지팡이");
			meta.setLocalizedName("금 지팡이");
		}
		else {
			meta.setDisplayName("Gold Wand");
			meta.setLocalizedName("Gold Wand");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "gold_wand"), is);
		rc.shape("axx", "xsx", "xxs");
		rc.shape("xxa", "xsx", "sxx");
		rc.setIngredient('s', Material.STICK);
		rc.setIngredient('x', Material.AIR);
		rc.setIngredient('a', Material.GOLD_INGOT);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void IronWand(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 7, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.setCustomModelData(9000 + 3);
		if(lang.contains("kr")){
			meta.setDisplayName("철 지팡이");
			meta.setLocalizedName("철 지팡이");
		}
		else {
			meta.setDisplayName("Iron Wand");
			meta.setLocalizedName("Iron Wand");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "iron_wand"), is);
		rc.shape("axx", "xsx", "xxs");
		rc.shape("xxa", "xsx", "sxx");
		rc.setIngredient('s', Material.STICK);
		rc.setIngredient('x', Material.AIR);
		rc.setIngredient('a', Material.IRON_INGOT);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void DiamondWand(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 8, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.setCustomModelData(9000 + 4);
		if(lang.contains("kr")){
			meta.setDisplayName("다이아몬드 지팡이");
			meta.setLocalizedName("다이아몬드 지팡이");
		}
		else {
			meta.setDisplayName("Diamond Wand");
			meta.setLocalizedName("Diamond Wand");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "diamond_wand"), is);
		rc.shape("axx", "xsx", "xxs");
		rc.shape("xxa", "xsx", "sxx");
		rc.setIngredient('s', Material.STICK);
		rc.setIngredient('x', Material.AIR);
		rc.setIngredient('a', Material.DIAMOND);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void NetheriteWand(PrepareSmithingEvent d) {
		ItemStack r = new ItemStack(Material.BLAZE_ROD);
		ItemMeta rm = r.getItemMeta();
		rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 10, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		rm.setCustomModelData(9000 + 60);
		Player p = (Player) d.getView().getPlayer();
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			rm.setDisplayName("네더라이트 지팡이");
			rm.setLocalizedName("네더라이트 지팡이");
		}
		else {
			rm.setDisplayName("Netherite Wand");
			rm.setLocalizedName("Netherite Wand");
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
			if ((d.getInventory().getItem(0).getItemMeta().getCustomModelData() == 9000+4)
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
	public void NetheriteWand(SmithItemEvent d) {
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
				if (d.getCurrentItem().getItemMeta().getCustomModelData() == 9000 + 60) {
					Player p = (Player) d.getWhoClicked();
					ItemStack r = new ItemStack(Material.BLAZE_ROD);
					ItemMeta rm = r.getItemMeta();
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
							"generic.attackDamage", 10, Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.setCustomModelData(9000 + 60);
					
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("네더라이트 지팡이");
						rm.setLocalizedName("네더라이트 지팡이");
					}
					else {
						rm.setDisplayName("Netherite Wand");
						rm.setLocalizedName("Netherite Wand");
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

}