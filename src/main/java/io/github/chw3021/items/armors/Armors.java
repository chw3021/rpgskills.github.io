package io.github.chw3021.items.armors;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import io.github.chw3021.commons.Pak;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;

public class Armors {
	
	final protected static NamespacedKey getKey() {
		return new NamespacedKey(RMain.getInstance(), UUID.randomUUID().toString());
	}
	
	private Pak pak = Pak.getInstance();


	final static protected NamespacedKey nethercore = new NamespacedKey(RMain.getInstance(), "armor_nether_core");
	final static protected NamespacedKey endercore = new NamespacedKey(RMain.getInstance(), "armor_ender_core");
	
	
	final protected ItemStack nethercore(NamespacedKey nethercore, Integer cmdt, ItemStack r, ItemMeta rm, Player p) {

		if(!(r.getType().name().contains("HELMET")||r.getType().name().contains("CHESTPLATE"))) {
			return null;
		}
		
		if (cmdt == -202) {
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 0.15, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 10, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				if (rm.hasLore()) {
					List<String> lore = rm.getLore();
					lore.add(ChatColor.RED + "[네더] 융합된 네더의 핵 부여");
					rm.setLore(lore);
				} else {
					rm.setLore(Arrays.asList(ChatColor.RED + "[네더] 융합된 네더의 핵 부여"));
				}
			}
			else {
				if (rm.hasLore()) {
					List<String> lore = rm.getLore();
					lore.add(ChatColor.RED + "[Nether] Converged NetherCore Applied");
					rm.setLore(lore);
				} else {
					rm.setLore(Arrays.asList(ChatColor.RED + "[Nether] Converged NetherCore Applied"));
				}
			}
			rm.getPersistentDataContainer().set(nethercore, PersistentDataType.STRING, "netherCore");
			return r;
		} else {
			return null;
		}
	}

	final protected ItemStack endercore(NamespacedKey endercore, Integer cmdt, ItemStack r, ItemMeta rm, Player p) {

		if(!(r.getType().name().contains("LEGGINGS")||r.getType().name().contains("BOOTS"))) {
			return null;
		}
		if (cmdt == -206) {
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 0.2, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 15, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
			rm.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(getKey(), 0.2, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				if (rm.hasLore()) {
					List<String> lore = rm.getLore();
					lore.add(ChatColor.DARK_GRAY + "[엔더] 엔더 핵 부여");
					rm.setLore(lore);
				} else {
					rm.setLore(Arrays.asList(ChatColor.DARK_GRAY + "[엔더] 엔더 핵 부여"));
				}
			}
			else {
				if (rm.hasLore()) {
					List<String> lore = rm.getLore();
					lore.add(ChatColor.DARK_GRAY + "[Ender] Converged EnderCore Applied");
					rm.setLore(lore);
				} else {
					rm.setLore(Arrays.asList(ChatColor.DARK_GRAY + "[Ender] Converged EnderCore Applied"));
				}
			}
			return r;
		} 
		else {
			return null;
		}
		
	}

	public static ItemMeta maxHealthAdd(final ItemMeta im, Double amount, Operation op, EquipmentSlotGroup esg) {
		
		im.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), amount, op, esg));
		
		return im;
	}

	public static ItemMeta armorAdd(final ItemMeta im, Double amount, Operation op, EquipmentSlotGroup esg) {
		
		im.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), amount, op, esg));
		
		return im;
	}

	public static ItemMeta armorToughnessAdd(final ItemMeta im, Double amount, Operation op, EquipmentSlotGroup esg) {
		
		im.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), amount, op, esg));
		
		return im;
	}
	
	public static ItemMeta movementAdd(final ItemMeta im, Double amount, Operation op, EquipmentSlotGroup esg) {
		
		im.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(getKey(), amount, op, esg));
		
		return im;
	}

	
	public ItemStack[] giveElArmors(Integer el, Player p) {
		ItemStack[] elArmors = new ItemStack[4];
		Integer cmdt = el +200;
		for(int i = 0; i <4; i++) {
			elArmors[i] = elArmor(acArmor(i,p), cmdt , p);
		}
		p.getInventory().addItem(elArmors);
		
		return elArmors;
	}


	final private static Integer getan(Material ma) {
		final String m = ma.name();
		if(m.contains("BOOTS")) {
			return 23000;
		}
		else if(m.contains("CHESTPLATE")) {
			return 21000;
		}
		else if(m.contains("HELMET")) {
			return 20000;
		}
		else if(m.contains("LEGGINGS")) {
			return 22000;
		}
		return 0;
	}

	public static ItemStack acArmor(Integer slot, Player p) { //ruined portal quest 에서 습득

		ItemStack r = new ItemStack(Material.NETHERITE_BOOTS);
		String kname = "";
		String ename = "";
		ItemMeta rm = r.getItemMeta();
		rm.removeAttributeModifier(Attribute.ARMOR);
		rm.removeAttributeModifier(Attribute.ARMOR_TOUGHNESS);

		rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.6, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
		rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
		rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));

		final Integer an = getan(r.getType());
		
		if(slot == 0) {
			kname = "부츠";
			ename = "Boots";
			rm.addAttributeModifier(Attribute.MOVEMENT_SPEED,
					new AttributeModifier(getKey(), 0.06, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
		}
		else if(slot == 1) {
			kname = "흉갑";
			ename = "Chestplate";
			r.setType(Material.NETHERITE_CHESTPLATE);
		}
		else if(slot == 2) {
			kname = "투구";
			ename = "Helmet";
			r.setType(Material.NETHERITE_HELMET);
		}
		else if(slot == 3) {
			kname = "각반";
			ename = "Leggings";
			r.setType(Material.NETHERITE_LEGGINGS);
		}
		else {
			return null;
		}
		
		rm.setCustomModelData(12 + an);
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			rm.setDisplayName(ChatColor.GOLD +"고대의 "+kname);
			rm.setItemName(ChatColor.GOLD +"Ancient "+ename);
		}
		else {
			rm.setDisplayName(ChatColor.GOLD +"Ancient "+ename);
			rm.setItemName(ChatColor.GOLD +"Ancient "+ename);
		}
		r.setItemMeta(rm);
		
		return r;
		
	}
	
	
	//{@link io.github.chw3021.items.Elements.getstel(int, Player)}
	final protected ItemStack elArmor(ItemStack i0, Integer cmdt, Player p) {
	
		ItemStack r = i0.clone();
		String kname = "";
		String ename = "";
		ItemMeta rm = r.getItemMeta();
		rm.removeAttributeModifier(Attribute.ARMOR);
		rm.removeAttributeModifier(Attribute.ARMOR_TOUGHNESS);

		rm.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(getKey(), 0.7, Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR));
		rm.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(getKey(), 7, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
		rm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(getKey(), 7, Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
		
				
		final String m = r.getType().name();
		final Integer an = getan(r.getType());
		
		if(m.contains("BOOTS")) {
			kname = "부츠";
			ename = "Boots";
			rm.addAttributeModifier(Attribute.MOVEMENT_SPEED,
					new AttributeModifier(getKey(), 0.1, Operation.ADD_SCALAR, EquipmentSlotGroup.FEET));
		}
		else if(m.contains("CHESTPLATE")) {
			kname = "흉갑";
			ename = "Chestplate";
		}
		else if(m.contains("HELMET")) {
			kname = "투구";
			ename = "Helmet";
		}
		else if(m.contains("LEGGINGS")) {
			kname = "각반";
			ename = "Leggings";
		}
		else {
			return null;
		}
		
		rm.setCustomModelData(cmdt -200 + an);
		final int el = cmdt - 200;
		
		rm.setLore(ArmorSet.setLore(p, el, false));
	
		if (cmdt == 214) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName(ChatColor.GOLD +"대지의 "+kname);
				rm.setItemName(ChatColor.GOLD +"Earth "+ename);
			}
			else {
				rm.setDisplayName(ChatColor.GOLD +"Earth "+ename);
				rm.setItemName(ChatColor.GOLD +"Earth "+ename);
			}
			
			r.setItemMeta(pak.addelmr(rm, el, 0.25, p));
			return r;
		}
		else if (cmdt == 205) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName(ChatColor.GOLD +"바람의 "+kname);
				rm.setItemName(ChatColor.GOLD +"Windy "+ename);
			}
			else {
				rm.setDisplayName(ChatColor.GOLD +"Windy "+ename);
				rm.setItemName(ChatColor.GOLD +"Windy "+ename);
			}
			r.setItemMeta(pak.addelmr(rm, el, 0.25, p));
			return r;
		} else if (cmdt == 206) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName(ChatColor.GOLD +"서리의 "+kname);
				rm.setItemName(ChatColor.GOLD +"Frost "+ename);
			}
			else {
				rm.setDisplayName(ChatColor.GOLD +"Frost "+ename);
				rm.setItemName(ChatColor.GOLD +"Frost "+ename);
			}
			r.setItemMeta(pak.addelmr(rm, el, 0.25, p));
			return r;
		} else if (cmdt == 207) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName(ChatColor.GOLD +"바다의 "+kname);
				rm.setItemName(ChatColor.GOLD +"Ocean "+ename);
			}
			else {
				rm.setDisplayName(ChatColor.GOLD +"Ocean "+ename);
				rm.setItemName(ChatColor.GOLD +"Ocean "+ename);
			}
			r.setItemMeta(pak.addelmr(rm, el, 0.25, p));
			return r;
		} else if (cmdt == 208) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName(ChatColor.GOLD +"어둠의 "+kname);
				rm.setItemName(ChatColor.GOLD +"Dark "+ename);
			}
			else {
				rm.setDisplayName(ChatColor.GOLD +"Dark "+ename);
				rm.setItemName(ChatColor.GOLD +"Dark "+ename);
			}
			r.setItemMeta(pak.addelmr(rm, el, 0.25, p));
			return r;
		} else if (cmdt == 209) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName(ChatColor.GOLD +"개조된 "+kname);
				rm.setItemName(ChatColor.GOLD +"Hyper "+ename);
			}
			else {
				rm.setDisplayName(ChatColor.GOLD +"Hyper "+ename);
				rm.setItemName(ChatColor.GOLD +"Hyper "+ename);
			}
			r.setItemMeta(pak.addelmr(rm, el, 0.25, p));
			return r;
		} else if (cmdt == 210) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName(ChatColor.GOLD +"작열하는 "+kname);
				rm.setItemName(ChatColor.GOLD +"Burning "+ename);
			}
			else {
				rm.setDisplayName(ChatColor.GOLD +"Burning "+ename);
				rm.setItemName(ChatColor.GOLD +"Burning "+ename);
			}
			r.setItemMeta(pak.addelmr(rm, el, 0.25, p));
			return r;
		} else if (cmdt == 211) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName(ChatColor.GOLD +"맹독 "+kname);
				rm.setItemName(ChatColor.GOLD +"Poison "+ename);
			}
			else {
				rm.setDisplayName(ChatColor.GOLD +"Poison "+ename);
				rm.setItemName(ChatColor.GOLD +"Poison "+ename);
			}
			r.setItemMeta(pak.addelmr(rm, el, 0.25, p));
			return r;
		}  else {
			return null;
		}
	}


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
				) {
			final Integer cmdt = i1.getItemMeta().getCustomModelData();
			final Integer an = getan(i0.getType());
			
			if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == an + 12) {
	
				return elArmor(i0, cmdt, p);
			}
			else if ((!i0.getItemMeta().hasCustomModelData())) { //normal
	
				return elArmor(i0, cmdt, p);
			}
			else {
				return null;
			}
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
						&& !i2.getItemMeta().getPersistentDataContainer().has(nethercore,
								PersistentDataType.STRING)
						&& !i2.getItemMeta().getPersistentDataContainer().has(endercore,
								PersistentDataType.STRING)
						) {
					final Integer cmdt = i1.getItemMeta().getCustomModelData();
					if((cmdt >=5 && cmdt <=14) || (cmdt >=205 && cmdt <=214)) {
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
	

	private ItemStack nsc(Inventory inv, Player p) {

		final ItemStack i0 = inv.getItem(0);
		final ItemStack i1 = inv.getItem(1);
		if (i0 != null
				&& !i0.getItemMeta().getPersistentDataContainer().has(nethercore, PersistentDataType.STRING)
				&& i0.getAmount() >= 1 && i0.getItemMeta().hasCustomModelData()
				&& i1 != null && i1.getItemMeta().hasCustomModelData()) {

			final Integer cmdt = i1.getItemMeta().getCustomModelData();
			if (i1.getItemMeta().getCustomModelData() == cmdt) {
	
				ItemStack r = i0.clone();
				ItemMeta rm = r.getItemMeta();
				return nethercore(nethercore, cmdt, r, rm, p);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private ItemStack ncc(SmithItemEvent d, Inventory inv) {
	
		if (d.getClickedInventory().getType() == InventoryType.SMITHING) {
			if (d.getClickedInventory().getItem(2) != null && d.getCurrentItem().getItemMeta().hasCustomModelData()
					&& d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(nethercore,
							PersistentDataType.STRING)
					&& inv.getItem(2).getItemMeta().getPersistentDataContainer().has(nethercore,
							PersistentDataType.STRING)) {
	
				Player p = (Player) d.getWhoClicked();
				ItemStack r = d.getInventory().getItem(0).clone();
				ItemMeta rm = r.getItemMeta();

				if (p.getInventory().firstEmpty() != -1 && inv.getItem(1).getItemMeta().hasCustomModelData()) {
					final Integer cmdt = d.getClickedInventory().getItem(1).getItemMeta().getCustomModelData();
					if(cmdt ==-202&& d.getInventory().getItem(1).getItemMeta().getCustomModelData() == cmdt) {
						return nethercore(nethercore, cmdt, r, rm, p);
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
	}

	private ItemStack esc(Inventory inv, Player p) {
	
		if (inv.getItem(0) != null
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& inv.getItem(0).getAmount() >= 1 && inv.getItem(0).getItemMeta().hasCustomModelData()
				&& inv.getItem(1) != null && inv.getItem(1).getItemMeta().hasCustomModelData()) {

			final Integer cmdt = inv.getItem(1).getItemMeta().getCustomModelData();
			if (inv.getItem(1).getItemMeta().getCustomModelData() == cmdt) {
	
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

	private ItemStack ecc(SmithItemEvent d, Inventory inv) {
	
		if (d.getClickedInventory().getType() == InventoryType.SMITHING) {
			if (d.getClickedInventory().getItem(2) != null && d.getCurrentItem().getItemMeta().hasCustomModelData()
					&& d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(endercore,
							PersistentDataType.STRING)
					&& inv.getItem(2).getItemMeta().getPersistentDataContainer().has(endercore,
							PersistentDataType.STRING)) {
	
				Player p = (Player) d.getWhoClicked();
				ItemStack r = d.getInventory().getItem(0).clone();
				ItemMeta rm = r.getItemMeta();

				if (p.getInventory().firstEmpty() != -1 && inv.getItem(1).getItemMeta().hasCustomModelData()) {
					final Integer cmdt = d.getClickedInventory().getItem(1).getItemMeta().getCustomModelData();
					if(cmdt ==-206&& d.getInventory().getItem(1).getItemMeta().getCustomModelData() == cmdt) {
						return endercore(endercore, cmdt, r, rm, p);
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
	}
	
	
	
	
	@EventHandler
	public void PICE(PrepareSmithingEvent d) 
	{
		Player p = (Player)	d.getView().getPlayer();
		if (csc(d.getInventory(), p) != null) {
			d.setResult(csc(d.getInventory(), p));
		}
		if (nsc(d.getInventory(), p) != null) {
			d.setResult(nsc(d.getInventory(), p));
		}
		if (esc(d.getInventory(), p) != null) {
			d.setResult(esc(d.getInventory(), p));
		}
	
	}

	@EventHandler
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
		if (ncc(d, d.getClickedInventory()) != null) {
			ItemStack r = ncc(d, d.getClickedInventory());
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
		if (ecc(d, d.getClickedInventory()) != null) {
			ItemStack r = ecc(d, d.getClickedInventory());
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
	}
	
	
	
}
