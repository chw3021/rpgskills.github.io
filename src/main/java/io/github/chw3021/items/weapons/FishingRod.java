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
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import io.github.chw3021.commons.Pak;
import io.github.chw3021.rmain.RMain;

public class FishingRod extends Weapons implements Listener {

	/*final static private NamespacedKey nethercore = new NamespacedKey(RMain.getInstance(), "fishingrod_nether_core");
	final static private NamespacedKey endercore = new NamespacedKey(RMain.getInstance(), "fishingrod_ender_core");
	final static private NamespacedKey herocore = new NamespacedKey(RMain.getInstance(), "fishingrod_hero_core");*/
	
	final String lang = RMain.getInstance().getConfig().getString("Language");
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
			if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 12000 + 12
					&&i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				rm.setCustomModelData(cmdt + 12000);
				rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
						new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 26,
								Operation.ADD_NUMBER, EquipmentSlot.HAND));
	
				rm.addAttributeModifier(Attribute.GENERIC_LUCK,
						new AttributeModifier(UUID.randomUUID(), "generic.luck", 30,
								Operation.ADD_NUMBER, EquipmentSlot.HAND));
				if (cmdt == 14) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"바람의 낚시대");
						rm.setLocalizedName(ChatColor.GOLD +"바람의 낚시대");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Windy FishingRod");
						rm.setLocalizedName(ChatColor.GOLD +"Windy FishingRod");
					}
					NamespacedKey.fromString("windy", RMain.getInstance());
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				}
				else if (cmdt == 5) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"대지의 낚시대");
						rm.setLocalizedName(ChatColor.GOLD +"대지의 낚시대");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Earth FishingRod");
						rm.setLocalizedName(ChatColor.GOLD +"Earth FishingRod");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 6) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"서리의 낚시대");
						rm.setLocalizedName(ChatColor.GOLD +"서리의 낚시대");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Frost FishingRod");
						rm.setLocalizedName(ChatColor.GOLD +"Frost FishingRod");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 7) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"바다의 낚시대");
						rm.setLocalizedName(ChatColor.GOLD +"바다의 낚시대");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Ocean FishingRod");
						rm.setLocalizedName(ChatColor.GOLD +"Ocean FishingRod");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 8) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"어둠의 낚시대");
						rm.setLocalizedName(ChatColor.GOLD +"어둠의 낚시대");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Dark FishingRod");
						rm.setLocalizedName(ChatColor.GOLD +"Dark FishingRod");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 9) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"개조된 낚시대");
						rm.setLocalizedName(ChatColor.GOLD +"개조된 낚시대");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Hyper FishingRod");
						rm.setLocalizedName(ChatColor.GOLD +"Hyper FishingRod");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 10) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"작열하는 낚시대");
						rm.setLocalizedName(ChatColor.GOLD +"작열하는 낚시대");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Burning FishingRod");
						rm.setLocalizedName(ChatColor.GOLD +"Burning FishingRod");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 11) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"맹독 낚시대");
						rm.setLocalizedName(ChatColor.GOLD +"맹독 낚시대");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Poison FishingRod");
						rm.setLocalizedName(ChatColor.GOLD +"Poison FishingRod");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				}  else {
					return null;
				}
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 12000 + 100+8
					&&i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 12) {
					rm.setCustomModelData(cmdt + 12000);
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 26,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"자연의 낚시대");
						rm.setLocalizedName(ChatColor.GOLD +"자연의 낚시대");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Natural FishingRod");
						rm.setLocalizedName(ChatColor.GOLD +"Natural FishingRod");
					}
					r.setItemMeta(rm);
					return r;
				}
				return null;
			} 
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 12000 +60
					&&i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 14) {
					rm.setCustomModelData(1 + 12000+100);
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 9,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 1단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 1단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.1");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.1");
					}
					r.setItemMeta(rm);
					return r;
				}
				return null;
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() <= 12000 + 100+7 &&i0.getItemMeta().getCustomModelData() >= 12000 + 100+1
					&&i1.getItemMeta().getCustomModelData() == cmdt && i0.getItemMeta().getCustomModelData() == cmdt -3 + 12000 + 100 -1) {
	
				ItemStack r = i0.clone();
				////r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.setCustomModelData(cmdt -3 + 12000 + 100);
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 5) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 11,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 2단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 2단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.2");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.2");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 6) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 13,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 3단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 3단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.3");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.3");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 7) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 15,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 4단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 4단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.4");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.4");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 8) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 17,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 5단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 5단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.5");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.5");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 9) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 19,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 6단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 6단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.6");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.6");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 10) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 21,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 7단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 7단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.7");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.7");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 11) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 23,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 8단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 낚시대 - 8단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.8");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura FishingRod - Lv.8");
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

			if (inv.getItem(0).getType().name().contains("FISHING_ROD")
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

			if (inv.getItem(0).getType().name().contains("FISHING_ROD")
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

			if (inv.getItem(0).getType().name().contains("FISHING_ROD")
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
	public void StoneFishingRod(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 2, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.setCustomModelData(12000+1);
		if(lang.contains("kr")){
			meta.setDisplayName("돌 낚시대");
			meta.setLocalizedName("돌 낚시대");
		}
		else {
			meta.setDisplayName("Stone FishingRod");
			meta.setLocalizedName("Stone FishingRod");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "stone_fishingrod"), is);
		rc.shape("bba", "bax", "abx");
		rc.shape("abb", "xab", "xba");
		rc.setIngredient('x', Material.STRING);
		rc.setIngredient('b', Material.AIR);
		rc.setIngredient('a', Material.COBBLESTONE);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void GoldFishingRod(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 2, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.setCustomModelData(12000+2);
		if(lang.contains("kr")){
			meta.setDisplayName("금 낚시대");
			meta.setLocalizedName("금 낚시대");
		}
		else {
			meta.setDisplayName("Gold FishingRod");
			meta.setLocalizedName("Gold FishingRod");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "gold_fishingrod"), is);
		rc.shape("bba", "bax", "abx");
		rc.shape("abb", "xab", "xba");
		rc.setIngredient('x', Material.STRING);
		rc.setIngredient('b', Material.AIR);
		rc.setIngredient('a', Material.GOLD_INGOT);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void IronFishingRod(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 3, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.setCustomModelData(12000+3);
		if(lang.contains("kr")){
			meta.setDisplayName("철 낚시대");
			meta.setLocalizedName("철 낚시대");
		}
		else {
			meta.setDisplayName("Iron FishingRod");
			meta.setLocalizedName("Iron FishingRod");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "iron_fishingrod"), is);
		rc.shape("bba", "bax", "abx");
		rc.shape("abb", "xab", "xba");
		rc.setIngredient('x', Material.STRING);
		rc.setIngredient('b', Material.AIR);
		rc.setIngredient('a', Material.IRON_INGOT);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void DiamondFishingRod(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 5, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.setCustomModelData(12000+4);
		if(lang.contains("kr")){
			meta.setDisplayName("다이아몬드 낚시대");
			meta.setLocalizedName("다이아몬드 낚시대");
		}
		else {
			meta.setDisplayName("Diamond FishingRod");
			meta.setLocalizedName("Diamond FishingRod");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "diamond_fishingrod"), is);
		rc.shape("bba", "bax", "abx");
		rc.shape("abb", "xab", "xba");
		rc.setIngredient('x', Material.STRING);
		rc.setIngredient('b', Material.AIR);
		rc.setIngredient('a', Material.DIAMOND);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void NetheriteFishingRod(PrepareSmithingEvent d) {
		ItemStack r = new ItemStack(Material.FISHING_ROD);
		ItemMeta rm = r.getItemMeta();
		rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
				"generic.attackDamage", 7, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		rm.setCustomModelData(12000+60);
		
		Player p = (Player) d.getView().getPlayer();
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			rm.setDisplayName("네더라이트 낚시대");
			rm.setLocalizedName("네더라이트 낚시대");
		}
		else {
			rm.setDisplayName("Netherite FishingRod");
			rm.setLocalizedName("Netherite FishingRod");
		}

		if (d.getInventory().getItem(0) != null && d.getInventory().getItem(0).getAmount() >= 1
				&& d.getInventory().getItem(0).getItemMeta().hasCustomModelData() && d.getInventory().getItem(1) != null
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")) {
			if ((d.getInventory().getItem(0).getItemMeta().getCustomModelData() == 12000+4)
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
	public void NetheriteFishingRod(SmithItemEvent d) {
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
				if (d.getCurrentItem().getItemMeta().getCustomModelData() == 12000+60) {
					Player p = (Player) d.getWhoClicked();
					ItemStack r = new ItemStack(Material.FISHING_ROD);
					ItemMeta rm = r.getItemMeta();
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
							"generic.attackDamage", 7, Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.setCustomModelData(12000+60);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("네더라이트 낚시대");
						rm.setLocalizedName("네더라이트 낚시대");
					}
					else {
						rm.setDisplayName("Netherite FishingRod");
						rm.setLocalizedName("Netherite FishingRod");
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