package io.github.chw3021.items.weapons;

import org.bukkit.event.Listener;

public class Axe extends Weapons implements Listener {
/*
	final private NamespacedKey nethercore = new NamespacedKey(RMain.getInstance(), "axe_nether_core");
	final private NamespacedKey endercore = new NamespacedKey(RMain.getInstance(), "axe_ender_core");
	final private NamespacedKey herocore = new NamespacedKey(RMain.getInstance(), "axe_hero_core");
	Pak pak = new Pak();

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
			if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 4000 + 12
					&&i1.getItemMeta().getCustomModelData() == cmdt + 200) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				rm.setCustomModelData(cmdt + 4000);
				rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
						new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 30,
								Operation.ADD_NUMBER, EquipmentSlot.HAND));
	
				rm.addAttributeModifier(Attribute.GENERIC_LUCK,
						new AttributeModifier(UUID.randomUUID(), "generic.luck", 30,
								Operation.ADD_NUMBER, EquipmentSlot.HAND));
				rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
						new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
								Operation.ADD_NUMBER, EquipmentSlot.HAND));
				if (cmdt == 14) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"바람의 도끼");
						rm.setLocalizedName(ChatColor.GOLD +"바람의 도끼");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Windy Axe");
						rm.setLocalizedName(ChatColor.GOLD +"Windy Axe");
					}
					NamespacedKey.fromString("windy", RMain.getInstance());
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				}
				else if (cmdt == 5) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"대지의 도끼");
						rm.setLocalizedName(ChatColor.GOLD +"대지의 도끼");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Earth Axe");
						rm.setLocalizedName(ChatColor.GOLD +"Earth Axe");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 6) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"서리의 도끼");
						rm.setLocalizedName(ChatColor.GOLD +"서리의 도끼");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Frost Axe");
						rm.setLocalizedName(ChatColor.GOLD +"Frost Axe");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 7) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"바다의 도끼");
						rm.setLocalizedName(ChatColor.GOLD +"바다의 도끼");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Ocean Axe");
						rm.setLocalizedName(ChatColor.GOLD +"Ocean Axe");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 8) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"어둠의 도끼");
						rm.setLocalizedName(ChatColor.GOLD +"어둠의 도끼");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Dark Axe");
						rm.setLocalizedName(ChatColor.GOLD +"Dark Axe");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 9) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"개조된 도끼");
						rm.setLocalizedName(ChatColor.GOLD +"개조된 도끼");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Hyper Axe");
						rm.setLocalizedName(ChatColor.GOLD +"Hyper Axe");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 10) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"작열하는 도끼");
						rm.setLocalizedName(ChatColor.GOLD +"작열하는 도끼");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Burning Axe");
						rm.setLocalizedName(ChatColor.GOLD +"Burning Axe");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				} else if (cmdt == 11) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"맹독 도끼");
						rm.setLocalizedName(ChatColor.GOLD +"맹독 도끼");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Poison Axe");
						rm.setLocalizedName(ChatColor.GOLD +"Poison Axe");
					}
					r.setItemMeta(pak.putelm(rm, cmdt, 0.25, p));
					return r;
				}  else {
					return null;
				}
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == 4000 + 100+8
					&&i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 12) {
					rm.setCustomModelData(cmdt + 4000);
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 30,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.GOLD +"자연의 도끼");
						rm.setLocalizedName(ChatColor.GOLD +"자연의 도끼");
					}
					else {
						rm.setDisplayName(ChatColor.GOLD +"Natural Axe");
						rm.setLocalizedName(ChatColor.GOLD +"Natural Axe");
					}
					r.setItemMeta(rm);
					return r;
				}
				return null;
			} 
			else if ((!i0.getItemMeta().hasCustomModelData())&&i0.getType() == Material.DIAMOND
					&&i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				//r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 14) {
					rm.setCustomModelData(1 + 4000+100);
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 12,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 1단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 1단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.1");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.1");
					}
					r.setItemMeta(rm);
					return r;
				}
				return null;
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() <= 4000 + 100+7 &&i0.getItemMeta().getCustomModelData() >= 4000 + 100+1
					&&i1.getItemMeta().getCustomModelData() == cmdt && i0.getItemMeta().getCustomModelData() == cmdt -3 + 4000 + 100 -1) {
	
				ItemStack r = i0.clone();
				////r.addUnsafeEnchantments(i0.getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.setCustomModelData(cmdt -3 + 4000 + 100);
				rm.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
				if (cmdt == 5) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 14,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 2단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 2단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.2");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.2");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 6) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 16,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 3단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 3단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.3");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.3");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 7) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 18,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 4단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 4단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.4");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.4");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 8) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 20,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 5단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 5단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.5");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.5");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 9) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 22,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 6단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 6단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.6");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.6");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 10) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 24,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 7단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 7단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.7");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.7");
					}
					r.setItemMeta(rm);
					return r;
				}
				else if (cmdt == 11) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 26,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 8단계");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"원소기운의 도끼 - 8단계");
					}
					else {
						rm.setDisplayName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.8");
						rm.setLocalizedName(ChatColor.LIGHT_PURPLE +"Aura Axe - Lv.8");
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
/*
	private ItemStack csc(Inventory inv, Integer cmdt, Player p) {

		if (inv.getItem(0) != null && inv.getItem(0).getAmount() >= 1
				&& inv.getItem(1) != null
				&& inv.getItem(1).getItemMeta().hasCustomModelData() 
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")) {
			if ((inv.getItem(0).getItemMeta().getCustomModelData() == 4000 + 12)
					&&inv.getItem(1).getItemMeta().getCustomModelData() == cmdt) {

				ItemStack r = new ItemStack(Material.NETHERITE_AXE);
				r.addUnsafeEnchantments(inv.getItem(0).getEnchantments());
				ItemMeta rm = r.getItemMeta();
				rm.setCustomModelData(cmdt + 4000);
				if (cmdt == 5 && (inv.getItem(0).getType() == Material.DIAMOND_AXE || (inv.getItem(0).getType() == Material.NETHERITE_AXE) && !inv.getItem(0).getItemMeta().hasCustomModelData())) {
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
							"generic.attackDamage", 20, Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("대지의 도끼");
						rm.setLocalizedName("대지의 도끼");
					}
					else {
						rm.setDisplayName("Earth Axe");
						rm.setLocalizedName("Earth Axe");
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 6 && ((inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == cmdt + 4000 -1) || (inv.getItem(0).getType() == Material.NETHERITE_AXE) && !inv.getItem(0).getItemMeta().hasCustomModelData())) {

					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
							"generic.attackDamage", 26, Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("서리의 도끼");
						rm.setLocalizedName("서리의 도끼");
					}
					else {
						rm.setDisplayName("Frost Axe");
						rm.setLocalizedName("Frost Axe");
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 7 && ((inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == cmdt + 4000 -1) || (inv.getItem(0).getType() == Material.NETHERITE_AXE) && !inv.getItem(0).getItemMeta().hasCustomModelData())) {

					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
							"generic.attackDamage", 33, Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("바다의 도끼");
						rm.setLocalizedName("바다의 도끼");
					}
					else {
						rm.setDisplayName("Ocean Axe");
						rm.setLocalizedName("Ocean Axe");
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 8 && ((inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == cmdt + 4000 -1) || (inv.getItem(0).getType() == Material.NETHERITE_AXE) && !inv.getItem(0).getItemMeta().hasCustomModelData())) {

					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
							"generic.attackDamage", 40, Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("어둠의 도끼");
						rm.setLocalizedName("어둠의 도끼");
					}
					else {
						rm.setDisplayName("Dark Axe");
						rm.setLocalizedName("Dark Axe");
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 9 && ((inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == cmdt + 4000 -1) || (inv.getItem(0).getType() == Material.NETHERITE_AXE) && !inv.getItem(0).getItemMeta().hasCustomModelData())) {

					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 51,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));

					rm.addAttributeModifier(Attribute.GENERIC_LUCK,
							new AttributeModifier(UUID.randomUUID(), "generic.luck", 35,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 3,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("개조된 도끼");
						rm.setLocalizedName("개조된 도끼");
					}
					else {
						rm.setDisplayName("Hyper Axe");
						rm.setLocalizedName("Hyper Axe");
					}
					r.setItemMeta(rm);
					return r;
				} else if (cmdt == 10 && ((inv.getItem(0).getItemMeta().hasCustomModelData() && inv.getItem(0).getItemMeta().getCustomModelData() == cmdt + 4000 -1) || (inv.getItem(0).getType() == Material.NETHERITE_AXE) && !inv.getItem(0).getItemMeta().hasCustomModelData())) {

					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
							new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 60,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					rm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
							new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2,
									Operation.ADD_NUMBER, EquipmentSlot.HAND));
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("작열하는 도끼");
						rm.setLocalizedName("작열하는 도끼");
					}
					else {
						rm.setDisplayName("Burning Axe");
						rm.setLocalizedName("Burning Axe");
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

	private ItemStack nsc(Inventory inv, Integer cmdt, Player p) {

		if (inv.getItem(0) != null
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer().has(nethercore, PersistentDataType.STRING)
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer().has(endercore, PersistentDataType.STRING)
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer().has(herocore, PersistentDataType.STRING)
				&& inv.getItem(0).getAmount() >= 1 && inv.getItem(0).getItemMeta().hasCustomModelData()
				&& inv.getItem(1) != null && inv.getItem(1).getItemMeta().hasCustomModelData()) {

			if (inv.getItem(0).getType().name().contains("_AXE")
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

			if ((inv.getItem(0).getType().name().contains("_AXE"))
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

			if ((inv.getItem(0).getType().name().contains("_AXE"))
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
		
	}
	*/


}