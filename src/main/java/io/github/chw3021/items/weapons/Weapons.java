package io.github.chw3021.items.weapons;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import com.google.common.util.concurrent.AtomicDouble;

import io.github.chw3021.commons.ConfigManager;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;

public class Weapons {


	final String lang = ConfigManager.getInstance(RMain.getInstance()).getCustomConfig().getString("Language");
	private Pak pak = Pak.getInstance();

	final static protected NamespacedKey nethercore = new NamespacedKey(RMain.getInstance(), "weapon_nether_core");
	final static protected NamespacedKey endercore = new NamespacedKey(RMain.getInstance(), "weapon_ender_core");
	final static protected NamespacedKey herocore = new NamespacedKey(RMain.getInstance(), "weapon_hero_core");

    List<Material> planksMaterials = Arrays.stream(Material.values())
            .filter(material -> material.name().contains("PLANKS"))
            .collect(Collectors.toList());

    // RecipeChoice.MaterialChoice 생성
    final RecipeChoice rcc = new RecipeChoice.MaterialChoice(planksMaterials);

	
	final protected NamespacedKey getKey() {
		return new NamespacedKey(RMain.getInstance(), UUID.randomUUID().toString());
	}

	final protected ItemStack nethercore(NamespacedKey nethercore, Integer cmdt, ItemStack r, ItemMeta rm, Player p) {

		if(r.getType() != Material.TRIDENT && r.getType().name().contains("BANNER_PATTERN")) {
			if (cmdt == -2) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.5,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 15, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.WHITE + "[네더] 영혼 응축물 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.WHITE + "[네더] 영혼 응축물 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.WHITE + "[Nether] Condensate Soul Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.WHITE + "[Nether] Condensate Soul Applied"));
					}
				}
				rm.getPersistentDataContainer().set(nethercore, PersistentDataType.STRING, "netherCore");
				r.setItemMeta(pak.addelm(rm, 0, 0.25, p));
				return r;
			} else if (cmdt == -3) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.5,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 15, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.RED + "[네더] 진홍빛 핵 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.RED + "[네더] 진홍빛 핵 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.RED + "[Nether] Crimson Core Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.RED + "[Nether] Crimson Core Applied"));
					}
				}
				rm.getPersistentDataContainer().set(nethercore, PersistentDataType.STRING, "netherCore");
				r.setItemMeta(pak.addelm(rm, 1, 0.25, p));
				return r;
			} else if (cmdt == -4) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.25,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 50, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.AQUA + "[네더] 뒤틀린 핵 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.AQUA + "[네더] 뒤틀린 핵 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.AQUA + "[Nether] Warped Core Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.AQUA + "[Nether] Warped Core Applied"));
					}
				}
				rm.getPersistentDataContainer().set(nethercore, PersistentDataType.STRING, "netherCore");
				r.setItemMeta(pak.addelm(rm, 0, 0.25, p));
				return r;
			} else if (cmdt == -5) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.25,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 50, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.DARK_RED + "[네더] 화산의 핵 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.DARK_RED + "[네더] 화산의 핵 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.DARK_RED + "[Nether] Volcanic Core Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.DARK_RED + "[Nether] Volcanic Core Applied"));
					}
				}
				rm.getPersistentDataContainer().set(nethercore, PersistentDataType.STRING, "netherCore");
				r.setItemMeta(pak.addelm(rm, 1, 0.25, p));
				return r;
			} else {
				return null;
			}
		}
		else {
			if (cmdt == -2) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.5,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 15, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.5,
								Operation.ADD_SCALAR, EquipmentSlotGroup.OFFHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 15, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.WHITE + "[네더] 영혼 응축물 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.WHITE + "[네더] 영혼 응축물 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.WHITE + "[Nether] Condensate Soul Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.WHITE + "[Nether] Condensate Soul Applied"));
					}
				}
				rm.getPersistentDataContainer().set(nethercore, PersistentDataType.STRING, "netherCore");
				r.setItemMeta(pak.addelm(rm, 0, 0.25, p));
				return r;
			} else if (cmdt == -3) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.5,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 15, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.5,
								Operation.ADD_SCALAR, EquipmentSlotGroup.OFFHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 15, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.RED + "[네더] 진홍빛 핵 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.RED + "[네더] 진홍빛 핵 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.RED + "[Nether] Crimson Core Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.RED + "[Nether] Crimson Core Applied"));
					}
				}
				rm.getPersistentDataContainer().set(nethercore, PersistentDataType.STRING, "netherCore");
				r.setItemMeta(pak.addelm(rm, 1, 0.25, p));
				return r;
			} else if (cmdt == -4) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.25,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 50, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.25,
								Operation.ADD_SCALAR, EquipmentSlotGroup.OFFHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 50, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.AQUA + "[네더] 뒤틀린 핵 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.AQUA + "[네더] 뒤틀린 핵 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.AQUA + "[Nether] Warped Core Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.AQUA + "[Nether] Warped Core Applied"));
					}
				}
				rm.getPersistentDataContainer().set(nethercore, PersistentDataType.STRING, "netherCore");
				r.setItemMeta(pak.addelm(rm, 0, 0.25, p));
				return r;
			} else if (cmdt == -5) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.25,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 50, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.25,
								Operation.ADD_SCALAR, EquipmentSlotGroup.OFFHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 50, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.DARK_RED + "[네더] 화산의 핵 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.DARK_RED + "[네더] 화산의 핵 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.DARK_RED + "[Nether] Volcanic Core Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.DARK_RED + "[Nether] Volcanic Core Applied"));
					}
				}
				rm.getPersistentDataContainer().set(nethercore, PersistentDataType.STRING, "netherCore");
				r.setItemMeta(pak.addelm(rm, 1, 0.25, p));
				return r;
			} else {
				return null;
			}
		}
	}

	final protected ItemStack endercore(NamespacedKey endercore, Integer cmdt, ItemStack r, ItemMeta rm, Player p) {

		if(r.getType() != Material.TRIDENT && r.getType().name().contains("BANNER_PATTERN")) {
			if (cmdt == -6) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.8,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 0.3, Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.getPersistentDataContainer().set(endercore, PersistentDataType.STRING, "enderCore");
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
						lore.add(ChatColor.DARK_GRAY + "[Ender] Ender Core Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.DARK_GRAY + "[Ender] Ender Core Applied"));
					}
				}
				r.setItemMeta(pak.addelm(rm, 2, 0.25, p));
				return r;
			} else if (cmdt == -7) {

				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.4,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 0.6, Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.getPersistentDataContainer().set(endercore, PersistentDataType.STRING, "enderCore");
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.DARK_GRAY + "[엔더] 공허의 핵 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.DARK_GRAY + "[엔더] 공허의 핵 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.DARK_GRAY + "[Ender] Void Core Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.DARK_GRAY + "[Ender] Void Core Applied"));
					}
				}
				r.setItemMeta(pak.addelm(rm, 2, 0.25, p));
				return r;
			} else {
				return null;
			}
		}
		else {
			if (cmdt == -6) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.8,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 0.3, Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.8,
								Operation.ADD_SCALAR, EquipmentSlotGroup.OFFHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 0.3, Operation.ADD_SCALAR, EquipmentSlotGroup.OFFHAND));
				rm.getPersistentDataContainer().set(endercore, PersistentDataType.STRING, "enderCore");
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
						lore.add(ChatColor.DARK_GRAY + "[Ender] Ender Core Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.DARK_GRAY + "[Ender] Ender Core Applied"));
					}
				}
				r.setItemMeta(pak.addelm(rm, 2, 0.35, p));
				return r;
			} else if (cmdt == -7) {

				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.4,
								Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 0.6, Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 0.4,
								Operation.ADD_SCALAR, EquipmentSlotGroup.OFFHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(), 0.6, Operation.ADD_SCALAR, EquipmentSlotGroup.OFFHAND));
				rm.getPersistentDataContainer().set(endercore, PersistentDataType.STRING, "enderCore");
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.DARK_GRAY + "[엔더] 공허의 핵 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.DARK_GRAY + "[엔더] 공허의 핵 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.DARK_GRAY + "[Ender] Void Core Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.DARK_GRAY + "[Ender] Void Core Applied"));
					}
				}
				r.setItemMeta(pak.addelm(rm, 2, 0.35, p));
				return r;
			} else {
				return null;
			}
		}
	}
	
	final protected ItemStack herocore(NamespacedKey herocore, Integer cmdt, ItemStack r, ItemMeta rm, Player p) {

		if(r.getType() != Material.TRIDENT && r.getType().name().contains("BANNER_PATTERN")) {
			if (cmdt == -8) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 1.1,
								Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(),0.8, Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.MAINHAND));
				rm.getPersistentDataContainer().set(herocore, PersistentDataType.STRING, "heroCore");
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.GOLD + "[영웅] 영웅의 상징 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.GOLD + "[영웅] 영웅의 상징 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.GOLD + "[Heroic] Symbol of Hero Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.GOLD + "[Heroic] Symbol of Hero Applied"));
					}
				}
				r.setItemMeta(pak.addelm(rm, 2, 0.55, p));
				return r;
			} else {
				return null;
			}
		}
		else {
			if (cmdt == -8) {
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 1.1,
								Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(),0.8, Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.MAINHAND));
				rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
						new AttributeModifier(getKey(), 1.1,
								Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND));
				rm.addAttributeModifier(Attribute.LUCK, new AttributeModifier(getKey(),0.8, Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND));
				rm.getPersistentDataContainer().set(herocore, PersistentDataType.STRING, "heroCore");
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.GOLD + "[영웅] 영웅의 상징 부여");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.GOLD + "[영웅] 영웅의 상징 부여"));
					}
				}
				else {
					if (rm.hasLore()) {
						List<String> lore = rm.getLore();
						lore.add(ChatColor.GOLD + "[Heroic] Symbol of Hero Applied");
						rm.setLore(lore);
					} else {
						rm.setLore(Arrays.asList(ChatColor.GOLD + "[Heroic] Symbol of Hero Applied"));
					}
				}
				r.setItemMeta(pak.addelm(rm, 2, 0.55, p));
				return r;
			} else {
				return null;
			}
		}
	}
	
	final private Integer getlev(Integer cmdt) {
		if(cmdt == 14) {
			return 1;
		}
		else if(cmdt >=5 && cmdt <=12){
			return cmdt-3;
		}
		else {
			return 0;
		}
	}
	

	protected final Integer getwn(Material ma) {
		final String m = ma.name();
		if(m.contains("_BANNER")) {
			return 1000;
		}
		else if(m.equals("BOW")) {
			return 2000;
		}
		else if(m.contains("SWORD")) {
			return 3000;
		}
		else if(m.contains("_AXE")) {
			return 4000;
		}
		else if(m.contains("CROSSBOW")) {
			return 5000;
		}
		else if(m.contains("SHOVEL")) {
			return 6000;
		}
		else if(m.contains("PICKAXE")) {
			return 7000;
		}
		else if(m.contains("HOE")) {
			return 8000;
		}
		else if(m.contains("BLAZE_ROD")) {
			return 9000;
		}
		else if(m.contains("TRIDENT")) {
			return 10000;
		}
		else if(m.contains("SHEARS")) {
			return 11000;
		}
		else if(m.contains("FISHING_ROD")) {
			return 12000;
		}
		else if(m.contains("MACE")) {
			return 13000;
		}
		return 0;
	}
	
	public ItemStack giveElWeapon(Integer el, Player p) {
		
		Integer cmdt = el +200;
		ItemStack is = p.getInventory().getItemInMainHand().clone();
		ItemMeta im = is.getItemMeta();
		im.setCustomModelData(getwn(is.getType())+8+100);
		is.setItemMeta(im);
		
		
		ItemStack elW = elweapon(arweapon(is.clone(),12,p), cmdt , p);
		
		p.getInventory().addItem(elW);
		
		return elW;
	}
	
	
	//weaponNum + el +100   or   weaponNum + 12
	final protected ItemStack arweapon(ItemStack i0, Integer cmdt, Player p) {

		ItemStack r = i0.clone();
		final Integer lev = getlev(cmdt);
		
		if(lev == 0) {
			return null;
		}
		
		String kname = "";
		String ename = "";
		ItemMeta rm = r.getItemMeta();

		final Material m = r.getType();
		final Integer dm = 8 + lev*2;
		final Integer wn = getwn(m);


		if(lev == 1) {
			if(!(rm.hasCustomModelData() && rm.getCustomModelData() - wn == 60)) {
				if (!(!rm.hasCustomModelData() && (m.name().contains("NETHERITE") || m == Material.MACE || m == Material.TRIDENT ) )) {
					return null;
				}
			}
			
		}
		else if(lev != 1 && lev <= rm.getCustomModelData()-wn-100) {
			return null;
		}
		
		
		rm.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
		rm.setMaxStackSize(1);
		
		if(m == Material.GLOBE_BANNER_PATTERN) {
			kname = "너클";
			ename = "Knuckle";
			rm.addItemFlags(ItemFlag.HIDE_DYE);
			rm.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
					new AttributeModifier(getKey(), dm/2+1,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
					new AttributeModifier(getKey(), dm/2+1,
							Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 8,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 8,
							Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		}
		else if(m == Material.BOW) {
			kname = "활";
			ename = "Bow";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), dm,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.2,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m== Material.NETHERITE_SWORD) {
			kname = "검";
			ename = "Sword";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), dm,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.6,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m == Material.NETHERITE_AXE) {
			kname = "도끼";
			ename = "Axe";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), dm+3,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m == Material.CROSSBOW) {
			kname = "쇠뇌";
			ename = "Crossbow";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), dm+2,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.5,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m== Material.NETHERITE_SHOVEL) {
			kname = "삽";
			ename = "Shovel";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), dm-2,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.1,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m == Material.NETHERITE_PICKAXE) {
			kname = "곡괭이";
			ename = "Pickaxe";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), dm-2,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.2,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m == Material.NETHERITE_HOE) {
			kname = "괭이";
			ename = "Hoe";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
					new AttributeModifier(getKey(), dm-3,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));

			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 5,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m == Material.BLAZE_ROD) {
			kname = "완드";
			ename = "Wand";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), dm+2,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.5,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m == Material.TRIDENT) {
			kname = "삼지창";
			ename = "Trident";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
					new AttributeModifier(getKey(), dm,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
					new AttributeModifier(getKey(), dm,
							Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));

			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.1,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.1,
							Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		}
		else if(m == Material.SHEARS) {
			kname = "단검";
			ename = "Dagger";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), dm,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.2,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m == Material.FISHING_ROD) {
			kname = "낚시대";
			ename = "FishingRod";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), dm-1,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.2,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m == Material.MACE) {
			kname = "철퇴";
			ename = "Mace";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), dm,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 0.6,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		StringBuffer sbe = new StringBuffer();
		rm.setCustomModelData(lev + 100 + wn);
		
		
		
		if(cmdt == 12) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				sb.append("자연의 ");
				sb.append(kname);
				
				sbe.append("Natural ");
				sbe.append(ename);
			}
			else {
				sb.append("Natural ");
				sb.append(ename);
				
				sbe.append("Natural ");
				sbe.append(ename);
			}
			rm.setCustomModelData(12 + wn);
		}
		else {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				sb.append("원소기운의 ");
				sb.append(kname);
				sb.append(" - ");
				sb.append(lev);
				sb.append("단계");
				
				sbe.append("Aura ");
				sbe.append(ename);
				sbe.append(" - Lv.");
				sbe.append(lev);
			}
			else {
				sb.append("Aura ");
				sb.append(ename);
				sb.append(" - Lv.");
				sb.append(lev);
				
				sbe.append("Aura ");
				sbe.append(ename);
				sbe.append(" - Lv.");
				sbe.append(lev);
			}
		}

		rm.setDisplayName(ChatColor.LIGHT_PURPLE +sb.toString());
		rm.setItemName(ChatColor.LIGHT_PURPLE +sbe.toString());
		r.setItemMeta(rm);
		return r;
	}
	

	//{@link io.github.chw3021.items.Elements.getstel(int, Player)}
	final protected ItemStack netheriteCustomWeapon(ItemStack i0, Integer cmdt, Player p) {
	
		ItemStack r = i0.clone();
		String kname = "";
		String ename = "";
		ItemMeta rm = r.getItemMeta();
		rm.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
		rm.setMaxStackSize(1);
		
				
		final String m = r.getType().name();
		
		if(m.contains("_BANNER")) {
			kname = "너클";
			ename = "Knuckle";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 5, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
			
		}
		else if(m.equals("BOW")) {
			kname = "활";
			ename = "Bow";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 8, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));

		}
		else if(m.equals("CROSSBOW")) {
			kname = "쇠뇌";
			ename = "Crossbow";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 10, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));

		}
		else if(m.contains("BLAZE_ROD")) {
			kname = "완드";
			ename = "Wand";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), 29,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.6,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m.contains("SHEARS")) {
			kname = "단검";
			ename = "Dagger";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 7, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));

		}
		else if(m.contains("FISHING_ROD")) {
			kname = "낚시대";
			ename = "FishingRod";					
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 7, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));

		}
		else {
			return null;
		}
		
		
		rm.setCustomModelData(getwn(r.getType()) + 60);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			rm.setDisplayName(ChatColor.GOLD +"네더라이트 "+kname);
			rm.setItemName(ChatColor.GOLD +"Netherite "+ename);
		}
		else {
			rm.setDisplayName(ChatColor.GOLD +"Netherite "+ename);
			rm.setItemName(ChatColor.GOLD +"Netherite "+ename);
		}
		return r;
	}

	

	//{@link io.github.chw3021.items.Elements.getstel(int, Player)}
	final protected ItemStack elweapon(ItemStack i0, Integer cmdt, Player p) {
	
		ItemStack r = i0.clone();
		String kname = "";
		String ename = "";
		ItemMeta rm = r.getItemMeta();
		rm.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
	
		rm.addAttributeModifier(Attribute.LUCK,
				new AttributeModifier(getKey(), 30,
						Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		rm.setMaxStackSize(1);
		
				
		final String m = r.getType().name();
		final Integer wn = getwn(r.getType());
		
		if(m.contains("_BANNER")) {
			kname = "너클";
			ename = "Knuckle";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
					new AttributeModifier(getKey(), 15,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
					new AttributeModifier(getKey(), 14,
							Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 10,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 10,
							Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		}
		else if(m.equals("BOW")) {
			kname = "활";
			ename = "Bow";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), 27,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.6,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m.contains("SWORD")) {
			kname = "검";
			ename = "Sword";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), 27,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.6,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m.contains("_AXE")) {
			kname = "도끼";
			ename = "Axe";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), 30,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.2,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m.equals("CROSSBOW")) {
			kname = "쇠뇌";
			ename = "Crossbow";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), 29,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.2,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m.contains("SHOVEL")) {
			kname = "삽";
			ename = "Shovel";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), 25,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.2,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m.contains("PICKAXE")) {
			kname = "곡괭이";
			ename = "Pickaxe";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), 25,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.4,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m.contains("HOE")) {
			kname = "괭이";
			ename = "Hoe";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
					new AttributeModifier(getKey(), 24,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
	
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 5,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m.contains("BLAZE_ROD")) {
			kname = "완드";
			ename = "Wand";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), 29,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.6,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m.contains("TRIDENT")) {
			kname = "삼지창";
			ename = "Trident";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
					new AttributeModifier(getKey(), 27,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
					new AttributeModifier(getKey(), 27,
							Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
			
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.2,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.2,
							Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		}
		else if(m.contains("SHEARS")) {
			kname = "단검";
			ename = "Dagger";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), 27,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.2,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m.contains("FISHING_ROD")) {
			kname = "낚시대";
			ename = "FishingRod";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), 26,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
			rm.addAttributeModifier(Attribute.ATTACK_SPEED,
					new AttributeModifier(getKey(), 1.2,
							Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else if(m.contains("MACE")) {
			kname = "철퇴";
			ename = "Mace";
			rm.addAttributeModifier(Attribute.ATTACK_DAMAGE,
			new AttributeModifier(getKey(), 27,
					Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		else {
			return null;
		}
		
		
		//{@link io.github.chw3021.items.Elements.getstel(int, Player)}
		rm.setCustomModelData(cmdt -200 + wn);
		final int el = cmdt - 200;
	
		if (cmdt == 214) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				rm.setDisplayName(ChatColor.GOLD +"대지의 "+kname);
				rm.setItemName(ChatColor.GOLD +"Earth "+ename);
			}
			else {
				rm.setDisplayName(ChatColor.GOLD +"Earth "+ename);
				rm.setItemName(ChatColor.GOLD +"Earth "+ename);
			}
			
			r.setItemMeta(pak.putelm(rm, el, 0.25, p));
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
			r.setItemMeta(pak.putelm(rm, el, 0.25, p));
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
			r.setItemMeta(pak.putelm(rm, el, 0.25, p));
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
			r.setItemMeta(pak.putelm(rm, el, 0.25, p));
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
			r.setItemMeta(pak.putelm(rm, el, 0.25, p));
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
			r.setItemMeta(pak.putelm(rm, el, 0.25, p));
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
			r.setItemMeta(pak.putelm(rm, el, 0.25, p));
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
			r.setItemMeta(pak.putelm(rm, el, 0.25, p));
			return r;
		}  else {
			return null;
		}
	}

	protected ItemStack netheritesc(Inventory inv, Player p) {
	
		final ItemStack i0 = inv.getItem(0);
		final ItemStack i1 = inv.getItem(1);
		if (i0 != null && i0.getAmount() >= 1
				&& i1 != null
				&& i1.getType() == Material.NETHERITE_INGOT
				&& !i0.getItemMeta().getPersistentDataContainer()
						.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
				&& !i0.getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& !i0.getItemMeta().getPersistentDataContainer()
						.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")) {
			return netheritesc(inv, p);
		} else {
			return null;
		}
	}
	
	protected ItemStack csc(Inventory inv, Player p) {
	
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
			final Integer wn = getwn(i0.getType());
			
			if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == wn + 12) {
	
				return elweapon(i0, cmdt, p);
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == wn + 100+8 //wild
					&&i1.getItemMeta().getCustomModelData() == cmdt) {

				return arweapon(i0, cmdt, p);
			} 
			else if ((!i0.getItemMeta().hasCustomModelData())) { //normal
				System.out.println(i0);

				return arweapon(i0, cmdt, p);
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() == wn +60 //netherite
					&&i1.getItemMeta().getCustomModelData() == cmdt) {

				return arweapon(i0, cmdt, p);
			}
			else if (i0.getItemMeta().hasCustomModelData() && i0.getItemMeta().getCustomModelData() <= wn + 100+7 &&i0.getItemMeta().getCustomModelData() >= wn + 100+1
					&& i0.getItemMeta().getCustomModelData() == cmdt -3 + wn + 100 -1) { //aura

				return arweapon(i0, cmdt, p);
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
						&& !ci.getItemMeta().getPersistentDataContainer().has(herocore,
								PersistentDataType.STRING)
						&& !i2.getItemMeta().getPersistentDataContainer().has(nethercore,
								PersistentDataType.STRING)
						&& !i2.getItemMeta().getPersistentDataContainer().has(endercore,
								PersistentDataType.STRING)
						&& !i2.getItemMeta().getPersistentDataContainer().has(herocore,
								PersistentDataType.STRING)) {
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

	protected ItemStack nsc(Inventory inv, Player p) {

		final ItemStack i0 = inv.getItem(0);
		final ItemStack i1 = inv.getItem(1);
		if (i0 != null
				&& !i0.getItemMeta().getPersistentDataContainer().has(nethercore, PersistentDataType.STRING)
				&& !i0.getItemMeta().getPersistentDataContainer().has(endercore, PersistentDataType.STRING)
				&& !i0.getItemMeta().getPersistentDataContainer().has(herocore, PersistentDataType.STRING)
				&& i0.getAmount() >= 1 && i0.getItemMeta().hasCustomModelData()
				&& i1 != null && i1.getItemMeta().hasCustomModelData()) {

			final Integer cmdt = i1.getItemMeta().getCustomModelData();
			final Integer wn = getwn(i0.getType());
			if (((wn + 1 <= i0.getItemMeta().getCustomModelData()
					&& i0.getItemMeta().getCustomModelData() < wn + 1000))
					&& i1.getItemMeta().getCustomModelData() == cmdt) {
	
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

				if (p.getInventory().firstEmpty() != -1 && inv.getItem(1).getItemMeta().hasCustomModelData()) {
					final Integer cmdt = d.getClickedInventory().getItem(1).getItemMeta().getCustomModelData();
					if(cmdt >=-5 && cmdt <=-2&& d.getInventory().getItem(1).getItemMeta().getCustomModelData() == cmdt) {
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

	protected ItemStack esc(Inventory inv, Player p) {
	
		if (inv.getItem(0) != null
				&& inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")
				&& inv.getItem(0).getAmount() >= 1 && inv.getItem(0).getItemMeta().hasCustomModelData()
				&& inv.getItem(1) != null && inv.getItem(1).getItemMeta().hasCustomModelData()) {

			final Integer cmdt = inv.getItem(1).getItemMeta().getCustomModelData();
			final Integer wn = getwn(inv.getItem(0).getType());
			if (((wn + 1 <= inv.getItem(0).getItemMeta().getCustomModelData()
					&& inv.getItem(0).getItemMeta().getCustomModelData() < wn + 1000))
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

	private ItemStack ecc(SmithItemEvent d, Inventory inv) {
	
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

				if (p.getInventory().firstEmpty() != -1 && inv.getItem(1).getItemMeta().hasCustomModelData()) {
					final Integer cmdt = d.getClickedInventory().getItem(1).getItemMeta().getCustomModelData();
					if(cmdt >=-7 && cmdt <=-6&& d.getInventory().getItem(1).getItemMeta().getCustomModelData() == cmdt) {
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

	protected ItemStack hsc(Inventory inv, Player p) {
	
		if (inv.getItem(0) != null
				&& inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
				&& inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& !inv.getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")
				&& inv.getItem(0).getAmount() >= 1 && inv.getItem(0).getItemMeta().hasCustomModelData()
				&& inv.getItem(1) != null && inv.getItem(1).getItemMeta().hasCustomModelData()) {

			final Integer cmdt = inv.getItem(1).getItemMeta().getCustomModelData();
			final Integer wn = getwn(inv.getItem(0).getType());
			if (((wn + 1 <= inv.getItem(0).getItemMeta().getCustomModelData()
					&& inv.getItem(0).getItemMeta().getCustomModelData() < wn + 1000))
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

	private ItemStack hcc(SmithItemEvent d, Inventory inv) {
	
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

				if (p.getInventory().firstEmpty() != -1 && inv.getItem(1).getItemMeta().hasCustomModelData()) {
					final Integer cmdt = d.getClickedInventory().getItem(1).getItemMeta().getCustomModelData();
					if(cmdt ==-8&& d.getInventory().getItem(1).getItemMeta().getCustomModelData() == cmdt) {
						return herocore(herocore, cmdt, r, rm, p);
					}
				}
				return null;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	final public ItemStack Tridentenchlore(ItemStack is, Player p) {
		if(is == null) {
			return null;
		}
		ItemMeta im = is.getItemMeta();
		if (!im.hasCustomModelData() && im.getAttributeModifiers(EquipmentSlot.HAND).isEmpty()) {
			im.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 8, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
		}
		if (!im.hasCustomModelData() && im.getAttributeModifiers(EquipmentSlot.OFF_HAND).isEmpty()) {
			im.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 8, Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
		}
		is.setItemMeta(im);
		return is;
	}
	@EventHandler
	public void TridentAtchange(InventoryClickEvent ev) 
	{
		if(ev.getCurrentItem() != null && !ev.getView().getTitle().contains("skills") && !ev.getView().getTitle().contains("Classes")) {
			for(ItemStack is : ev.getView().getBottomInventory()) {
				if(is == null) {
					continue;
				}
				Material m = is.getType();
				if(m == Material.TRIDENT) {
					Player p = (Player) ev.getView().getPlayer();
					Tridentenchlore(is, p);
				}
			}
		}
	}
	

	@EventHandler
	public void PICE(PrepareSmithingEvent d) 
	{
		Player p = (Player) d.getView().getPlayer();
		if (csc(d.getInventory(), p) != null) {
			d.setResult(csc(d.getInventory(), p));
		}
		if (nsc(d.getInventory(), p) != null) {
			d.setResult(nsc(d.getInventory(), p));
		}
		if (esc(d.getInventory(), p) != null) {
			d.setResult(esc(d.getInventory(), p));
		}
		if (hsc(d.getInventory(), p) != null) {
			d.setResult(hsc(d.getInventory(), p));
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
		if (hcc(d, d.getClickedInventory()) != null) {
			ItemStack r = hcc(d, d.getClickedInventory());
			Player p = (Player) d.getWhoClicked();
			p.getInventory().addItem(r);
			d.getClickedInventory().getItem(0).setAmount(d.getInventory().getItem(0).getAmount() - 1);
			d.getClickedInventory().getItem(1).setAmount(d.getInventory().getItem(1).getAmount() - 1);
			d.getClickedInventory().getItem(2).setAmount(0);
		}
	}
	

	public void itemset(ItemStack is,int loc, Inventory inv)
	{
		ItemStack item = is;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(is.getItemMeta().getDisplayName());
		items.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.setLore(is.getItemMeta().getLore());
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void winv(Player p)
	{
		Inventory ElementsInv = Bukkit.createInventory(null, 54, "Elements");
		itemset(arweapon(new ItemStack(Material.GLOBE_BANNER_PATTERN), 12,p), 0, ElementsInv);
		itemset(arweapon(new ItemStack(Material.BOW), 12,p), 1, ElementsInv);
		itemset(arweapon(new ItemStack(Material.NETHERITE_AXE), 12,p), 2, ElementsInv);
		itemset(arweapon(new ItemStack(Material.NETHERITE_SWORD), 12,p), 3, ElementsInv);
		itemset(arweapon(new ItemStack(Material.NETHERITE_PICKAXE), 12,p), 4, ElementsInv);
		itemset(arweapon(new ItemStack(Material.NETHERITE_HOE), 12,p), 5, ElementsInv);
		itemset(arweapon(new ItemStack(Material.NETHERITE_SHOVEL), 12,p), 6, ElementsInv);
		itemset(arweapon(new ItemStack(Material.CROSSBOW), 12,p), 7, ElementsInv);
		itemset(arweapon(new ItemStack(Material.TRIDENT), 12,p), 8, ElementsInv);
		itemset(arweapon(new ItemStack(Material.SHEARS), 12,p), 9, ElementsInv);
		itemset(arweapon(new ItemStack(Material.BLAZE_ROD), 12,p), 10, ElementsInv);
		itemset(arweapon(new ItemStack(Material.FISHING_ROD), 12,p), 11, ElementsInv);

		
		p.openInventory(ElementsInv);
	}

	final private int ppa(Player p, PotionEffectType pt) {
		if(p.hasPotionEffect(pt)) {
			return p.getPotionEffect(pt).getAmplifier()+1;
		}
		else {
			return 0;
		}
	}
	
	final protected Double demical(Double d) {
		return BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}

	
	final public Double caculd(Collection<AttributeModifier> ca, ItemStack is, Integer op, Player p) {
		if(!is.getItemMeta().hasCustomModelData() && op == 0) {
			Material m =is.getType();
			if(m == Material.WOODEN_SWORD) {
				return 4d;
			}
			else if(m == Material.STONE_SWORD) {
				return 5d;
			}
			else if(m == Material.GOLDEN_SWORD) {
				return 4d;
			}
			else if(m == Material.IRON_SWORD) {
				return 6d;
			}
			else if(m == Material.DIAMOND_SWORD) {
				return 7d;
			}
			else if(m == Material.NETHERITE_SWORD) {
				return 8d;
			}
			
			else if(m == Material.WOODEN_SHOVEL) {
				return 2.5d;
			}
			else if(m == Material.STONE_SHOVEL) {
				return 3.5d;
			}
			else if(m == Material.GOLDEN_SHOVEL) {
				return 2.5d;
			}
			else if(m == Material.IRON_SHOVEL) {
				return 4.5d;
			}
			else if(m == Material.DIAMOND_SHOVEL) {
				return 5.5d;
			}
			else if(m == Material.NETHERITE_SHOVEL) {
				return 6.5d;
			}

			else if(m == Material.WOODEN_PICKAXE) {
				return 2.0d;
			}
			else if(m == Material.STONE_PICKAXE) {
				return 3.0d;
			}
			else if(m == Material.GOLDEN_PICKAXE) {
				return 2.0d;
			}
			else if(m == Material.IRON_PICKAXE) {
				return 4.0d;
			}
			else if(m == Material.DIAMOND_PICKAXE) {
				return 5.0d;
			}
			else if(m == Material.NETHERITE_PICKAXE) {
				return 6.0d;
			}

			else if(m == Material.WOODEN_AXE) {
				return 7.0d;
			}
			else if(m == Material.STONE_AXE) {
				return 9.0d;
			}
			else if(m == Material.GOLDEN_AXE) {
				return 7.0d;
			}
			else if(m == Material.IRON_AXE) {
				return 9.0d;
			}
			else if(m == Material.DIAMOND_AXE) {
				return 9.0d;
			}
			else if(m == Material.NETHERITE_AXE) {
				return 10.0d;
			}

			else if(m.name().contains("HOE")) {
				return 1.0d;
			}

			else if(m == Material.TRIDENT) {
				return 10d;
			}
			else if(m == Material.MACE) {
				return 6d;
			}
			
			
			else {
				return 0d;
			}
		}
		Double rd0 = 0d;
		Double rd1 = 1d;
		AtomicDouble d = new AtomicDouble(0);
		AtomicDouble s = new AtomicDouble(0);
		AtomicDouble m = new AtomicDouble(1);
		ca.forEach(a -> {
			if(a.getOperation() == Operation.ADD_NUMBER) {
				d.addAndGet(a.getAmount());
			}
			else if(a.getOperation() == Operation.ADD_SCALAR) {
				s.addAndGet(a.getAmount());
			}
			else if(a.getOperation() == Operation.MULTIPLY_SCALAR_1) {
				m.set(m.get()*(1+a.getAmount()));
			}
		});
		rd0 = 2 + rd0 + d.get() + (ppa(p,PotionEffectType.INSTANT_DAMAGE))*3;
		rd1 = rd1*(1 + s.get());
		rd1 = rd1*m.get();
		
		return demical(rd0*rd1);
		
	}

	final public Double caculs(Collection<AttributeModifier> ca, ItemStack is, Integer op, Player p) {
		Material mt =is.getType();
		if(op == 0) {
			if(!is.getItemMeta().hasCustomModelData() ) {

				if(mt.name().contains("SWORD")) {
					return 1.6d;
				}
				else if(mt.name().contains("SHOVEL")) {
					return 1.0d;
				}
				else if(mt.name().contains("PICKAXE")) {
					return 1.2d;
				}
				else if(mt == Material.TRIDENT) {
					return 1.1d;
				}
				else if(mt == Material.WOODEN_AXE) {
					return 0.8d;
				}
				else if(mt == Material.STONE_AXE) {
					return 0.8d;
				}
				else if(mt == Material.GOLDEN_AXE) {
					return 1d;
				}
				else if(mt == Material.IRON_AXE) {
					return 0.9d;
				}
				else if(mt == Material.DIAMOND_AXE) {
					return 1d;
				}
				else if(mt == Material.NETHERITE_AXE) {
					return 1d;
				}

				else if(mt == Material.WOODEN_HOE) {
					return 1d;
				}
				else if(mt == Material.STONE_HOE) {
					return 2d;
				}
				else if(mt == Material.GOLDEN_HOE) {
					return 1d;
				}
				else if(mt == Material.IRON_HOE) {
					return 3d;
				}
				else if(mt == Material.DIAMOND_HOE) {
					return 4d;
				}
				else if(mt == Material.NETHERITE_HOE) {
					return 4d;
				}
				else if(mt == Material.MACE) {
					return 0.6d;
				}
				
				
				else {
					return 0d;
				}
			}
		}
		Double rd0 = 0d;
		Double rd1 = 1d;
		AtomicDouble d = new AtomicDouble(0);
		AtomicDouble s = new AtomicDouble(0);
		AtomicDouble m = new AtomicDouble(1);
		ca.forEach(a -> {
			if(a.getOperation() == Operation.ADD_NUMBER) {
				d.addAndGet(a.getAmount());
			}
			else if(a.getOperation() == Operation.ADD_SCALAR) {
				s.addAndGet(a.getAmount());
			}
			else if(a.getOperation() == Operation.MULTIPLY_SCALAR_1) {
				m.set(m.get()*(1+a.getAmount()));
			}
		});
		rd0 = rd0 + d.get() + (ppa(p,PotionEffectType.HASTE))*0.4;
		rd1 = rd1*(1 + s.get());
		rd1 = rd1*m.get();

		return demical(rd0*rd1);
	}
	final public Double cacull(Collection<AttributeModifier> ca, ItemStack is, Integer op, Player p) {
		if(ca == null) {
			return 0d;
		}
		Double rd0 = 0d;
		Double rd1 = 1d;
		AtomicDouble d = new AtomicDouble(0);
		AtomicDouble s = new AtomicDouble(0);
		AtomicDouble m = new AtomicDouble(1);
		ca.forEach(a -> {
			if(a.getOperation() == Operation.ADD_NUMBER) {
				d.addAndGet(a.getAmount());
			}
			else if(a.getOperation() == Operation.ADD_SCALAR) {
				s.addAndGet(a.getAmount());
			}
			else if(a.getOperation() == Operation.MULTIPLY_SCALAR_1) {
				m.set(m.get()*(1+a.getAmount()));
			}
		});
		rd0 = rd0 + d.get() + (ppa(p,PotionEffectType.LUCK));
		rd1 = rd1*(1 + s.get());
		rd1 = rd1*m.get();

		return demical(rd0*rd1);
	}
	
	final public ItemStack wealore(ItemStack is, Player p) {
		if(is == null) {
			return null;
		}
		ItemMeta im = is.getItemMeta();
		if (im.hasLore()) {
			List<String> lore = im.getLore();
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				
				lore.removeIf(l -> l.contains("<[공격력]>"));
				lore.removeIf(l -> l.contains("<[Damage]>"));
				lore.add(ChatColor.RED + "<[공격력]> {주}[" + caculd(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.ATTACK_DAMAGE), is,0, p)+"] | "
						+ "{보조}[" + caculd(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.ATTACK_DAMAGE), is,1, p)+"]");

				lore.removeIf(l -> l.contains("<[행운]>"));
				lore.removeIf(l -> l.contains("<[Luck]>"));
				lore.add(ChatColor.GREEN + "<[행운]> {주}[" + cacull(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.LUCK), is,0, p)+"] | "
						 + "{보조}[" + cacull(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.LUCK), is,1, p)+"]");
				
				lore.removeIf(l -> l.contains("<[공격속도]>"));
				lore.removeIf(l -> l.contains("<[Haste]>"));
				lore.add(ChatColor.AQUA + "<[공격속도]> {주}[" + caculs(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.ATTACK_SPEED), is,0, p)+"] | "
						 + "{보조}[" + caculs(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.ATTACK_SPEED), is,1, p)+"]");
				
			}
			else {
				
				lore.removeIf(l -> l.contains("<[공격력]>"));
				lore.removeIf(l -> l.contains("<[Damage]>"));
				lore.add(ChatColor.RED + "<[Damage]> {Main}[" + caculd(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.ATTACK_DAMAGE), is,0, p)+"] | "
						+ "{Off}[" + caculd(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.ATTACK_DAMAGE), is,1, p)+"]");

				lore.removeIf(l -> l.contains("<[행운]>"));
				lore.removeIf(l -> l.contains("<[Luck]>"));
				lore.add(ChatColor.GREEN + "<[Luck]> {Main}[" + cacull(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.LUCK), is,0, p)+"] | "
						 + "{Off}[" + cacull(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.LUCK), is,1, p)+"]");
				
				lore.removeIf(l -> l.contains("<[공격속도]>"));
				lore.removeIf(l -> l.contains("<[Haste]>"));
				lore.add(ChatColor.AQUA + "<[Haste]> {Main}[" + caculs(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.ATTACK_SPEED), is,0, p)+"] | "
						 + "{Off}[" + caculs(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.ATTACK_SPEED), is,1, p)+"]");
				
			}
			im.setLore(lore);
		} else {
			List<String> lore = new ArrayList<>();
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				
				lore.removeIf(l -> l.contains("<[공격력]>"));
				lore.removeIf(l -> l.contains("<[Damage]>"));
				lore.add(ChatColor.RED + "<[공격력]> {주}[" + caculd(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.ATTACK_DAMAGE), is,0, p)+"] | "
						+ "{보조}[" + caculd(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.ATTACK_DAMAGE), is,1, p)+"]");

				lore.removeIf(l -> l.contains("<[행운]>"));
				lore.removeIf(l -> l.contains("<[Luck]>"));
				lore.add(ChatColor.GREEN + "<[행운]> {주}[" + cacull(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.LUCK), is,0, p)+"] | "
						 + "{보조}[" + cacull(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.LUCK), is,1, p)+"]");
				
				lore.removeIf(l -> l.contains("<[공격속도]>"));
				lore.removeIf(l -> l.contains("<[Haste]>"));
				lore.add(ChatColor.AQUA + "<[공격속도]> {주}[" + caculs(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.ATTACK_SPEED), is,0, p)+"] | "
						 + "{보조}[" + caculs(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.ATTACK_SPEED), is,1, p)+"]");
				
			}
			else {
				
				lore.removeIf(l -> l.contains("<[공격력]>"));
				lore.removeIf(l -> l.contains("<[Damage]>"));
				lore.add(ChatColor.RED + "<[Damage]> {Main}[" + caculd(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.ATTACK_DAMAGE), is,0, p)+"] | "
						+ "{Off}[" + caculd(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.ATTACK_DAMAGE), is,1, p)+"]");

				lore.removeIf(l -> l.contains("<[행운]>"));
				lore.removeIf(l -> l.contains("<[Luck]>"));
				lore.add(ChatColor.GREEN + "<[Luck]> {Main}[" + cacull(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.LUCK), is,0, p)+"] | "
						 + "{Off}[" + cacull(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.LUCK), is,1, p)+"]");
				
				lore.removeIf(l -> l.contains("<[공격속도]>"));
				lore.removeIf(l -> l.contains("<[Haste]>"));
				lore.add(ChatColor.AQUA + "<[Haste]> {Main}[" + caculs(im.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.ATTACK_SPEED), is,0, p)+"] | "
						 + "{Off}[" + caculs(im.getAttributeModifiers(EquipmentSlot.OFF_HAND).get(Attribute.ATTACK_SPEED), is,1, p)+"]");
				
			}
			im.setLore(lore);
		}
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		is.setItemMeta(im);
		return is;
	}

	final private boolean availableWeapons(Material m, ItemMeta im) {
		return (m == Material.BLAZE_ROD && im.hasCustomModelData())|| (m == Material.SHEARS && im.hasCustomModelData()) 
				|| m == Material.FISHING_ROD || m == Material.BOW || m == Material.CROSSBOW
			|| m == Material.TRIDENT || (m == Material.GLOBE_BANNER_PATTERN && im.hasCustomModelData()) || m.name().contains("SWORD") || m.name().contains("AXE")
			|| m.name().contains("HOE")	|| m.name().contains("SHOVEL") || m == Material.MACE;
	}
	
	public void AttributeChange(PrepareAnvilEvent ev) 
	{
		if(ev.getResult() != null) {
			ItemStack is = ev.getResult();
			ItemMeta im = is.getItemMeta();
			Material m = is.getType();
			if(availableWeapons(m,im)) {
				Player p = (Player) ev.getView().getPlayer();
				wealore(is, p);
			}
		}
	}
	public void AttributeChange(PrepareSmithingEvent ev) 
	{
		if(ev.getResult() != null) {
			ItemStack is = ev.getResult();
			ItemMeta im = is.getItemMeta();
			Material m = is.getType();
			if(availableWeapons(m,im)) {
				Player p = (Player) ev.getView().getPlayer();
				wealore(is, p);
			}
		}
	}

	public void AttributeChange(InventoryClickEvent ev) 
	{
		if(ev.getCurrentItem() != null && !ev.getView().getTitle().contains("skills") && !ev.getView().getTitle().contains("Classes")) {
			for(ItemStack is : ev.getView().getBottomInventory()) {
				if(is == null) {
					continue;
				}
				ItemMeta im = is.getItemMeta();
				Material m = is.getType();
				if(m == Material.SHEARS || (m == Material.BLAZE_ROD && im.hasCustomModelData())|| (m == Material.SHEARS && im.hasCustomModelData()) 
						|| m == Material.FISHING_ROD || m == Material.BOW || m == Material.CROSSBOW
					|| m == Material.TRIDENT || (m == Material.GLOBE_BANNER_PATTERN && im.hasCustomModelData()) || m.name().contains("SWORD") || m.name().contains("AXE")
					|| m.name().contains("HOE")	|| m.name().contains("SHOVEL")) {
					Player p = (Player) ev.getView().getPlayer();
					wealore(is, p);
				}
			}
		}
	}

	public void AttributeChange(EntityPickupItemEvent ev) 
	{
		if(ev.getEntity() instanceof Player p) {
			ItemStack is = ev.getItem().getItemStack();
			ItemMeta im = is.getItemMeta();
			Material m = is.getType();
			if(availableWeapons(m,im)) {
				wealore(is, p);
			}
		}
	}
	
	
}