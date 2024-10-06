package io.github.chw3021.items.weapons;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import io.github.chw3021.commons.Pak;
import io.github.chw3021.rmain.RMain;

public class Sword extends Weapons {

	final static private NamespacedKey nethercore = new NamespacedKey(RMain.getInstance(), "sword_nether_core");
	final static private NamespacedKey endercore = new NamespacedKey(RMain.getInstance(), "sword_ender_core");
	final static private NamespacedKey herocore = new NamespacedKey(RMain.getInstance(), "sword_hero_core");
	Pak pak = new Pak();

	private ItemStack csc(Inventory inv, Player p) {
	
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
			final Integer cmdt = i1.getItemMeta().getCustomModelData();
			if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 3000 + 12) {
	
				return elweapon(i0, cmdt, p);
			}
			else {
				return arweapon(i0, cmdt, p);
			}

			/*	return arweapon(i0, cmdt, p);
			} 
			else if ((!i0.getItemMeta().hasCustomModelData())&&i0.getType() == Material.NETHERITE_SWORD) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 14) {
					rm.setCustomModelData(1 + 3000+100);
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 10,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 1단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 1단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.1");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.1");
					}
					r.setItemMeta(rm);
					return r;
				}
				return null;
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() <= 3000 + 100+7 &&i0.getItemMeta().getCustomModelData() >= 3000 + 100+1
					&& i0.getItemMeta().getCustomModelData() == cmdt -3 + 3000 + 100 -1) {
	
				ItemStack r = i0.clone();
				////r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.setCustomModelData(cmdt -3 + 3000 + 100);
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 5) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 12,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 2단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 2단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.2");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.2");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 6) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 14,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 3단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 3단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.3");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.3");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 7) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 16,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 4단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 4단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.4");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.4");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 8) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 18,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 5단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 5단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.5");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.5");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 9) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 20,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 6단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 6단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.6");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.6");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 10) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 22,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 7단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 7단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.7");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.7");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 11) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 24,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 8단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 검 - 8단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.8");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Sword - Lv.8");
					}
					r.setItemMeta(rm);
					return r;
				}
				return null;
			}
			else {
				return null;
			}*/
		} else {
			return null;
		}
	}

	private ItemStack ccc(SmithItemEvent d, Inventory inv) {
	
		if (inv.getType() == InventoryType.SMITHING) {
			if(d.getCurrentItem() == null || !d.getCurrentItem().hasItemMeta()) {
				return null;
			}
			final ItemStack i0 = inv.getItem(0);
			final ItemStack i1 = inv.getItem(1);
			final ItemStack i2 = inv.getItem(2);
			final ItemStack ci = d.getCurrentItem();
			if (i2 != null && i0 != null&& inv.getItem(1) != null && ci.getItemMeta().hasCustomModelData()) {
				Player p = (Player) d.getWhoClicked();
				if (p.getInventory().firstEmpty() != -1
						&& i2.getItemMeta().getCustomModelData() == ci.getItemMeta()
								.getCustomModelData()
								&& inv.getItem(1).getItemMeta().hasCustomModelData()
						&& (!i0.getItemMeta().hasCustomModelData() || i0.getItemMeta().getCustomModelData() != ci.getItemMeta()
								.getCustomModelData())
						&& !ci.getItemMeta().getPersistentDataContainer().has(nethercore,
								PersistentDataType.STRING)
						&& !ci.getItemMeta().getPersistentDataContainer().has(endercore,
								PersistentDataType.STRING)
						&& !ci.getItemMeta().getPersistentDataContainer().has(herocore,
								PersistentDataType.STRING)
						&& !i2.getItemMeta().getPersistentDataContainer().has(nethercore,
								PersistentDataType.STRING)
						&& !i2.getItemMeta().getPersistentDataContainer().has(endercore,
								PersistentDataType.STRING)
						&& !i2.getItemMeta().getPersistentDataContainer().has(herocore,
								PersistentDataType.STRING)) {
					final Integer cmdt = i1.getItemMeta().getCustomModelData();
					if(cmdt == 14 || (cmdt >=5 && cmdt <=12)) {
						return inv.getItem(2).clone();
					}
					return null;
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

	/*private ItemStack nsc(Inventory inv, Integer cmdt, Player p) {

		if (inv.getItem(0) != null
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer().has(nethercore, PersistentDataType.STRING)
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer().has(endercore, PersistentDataType.STRING)
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer().has(herocore, PersistentDataType.STRING)
				&& inv.getItem(0).getAmount() >= 1 && inv.getItem(0).getItemMeta().hasCustomModelData()
				&& inv.getItem(1) != null && inv.getItem(1).getItemMeta().hasCustomModelData()) {

			if ((inv.getItem(0).getType().name().contains("SWORD"))
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

			if ((inv.getItem(0).getType().name().contains("SWORD"))
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

			if ((inv.getItem(0).getType().name().contains("SWORD"))
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
	}*/

	public void PICE(PrepareSmithingEvent d) 
	{
		Player p = (Player) d.getView().getPlayer();
		if (csc(d.getInventory(), p) != null) {
			d.setResult(csc(d.getInventory(), p));
		}
	
	
	}

	public void ICE(SmithItemEvent d) 
	{
		if (d.getClickedInventory() == null) {
			return;
		}
		if (ccc(d, d.getClickedInventory()) != null) {
			ItemStack r = ccc(d, d.getClickedInventory());
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
	}

}