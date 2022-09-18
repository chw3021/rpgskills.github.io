package io.github.chw3021.items;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import io.github.chw3021.rmain.RMain;


public class NamingPrevent implements Listener {
	
	final static private NamespacedKey repaired = new NamespacedKey(RMain.getInstance(), "repaired");
	
	@EventHandler
	public void AnvilPr(PrepareAnvilEvent d) 
	{
		AnvilInventory ai = (AnvilInventory) d.getInventory();
		if(d.getInventory().getItem(0)!=null && d.getInventory().getItem(0).hasItemMeta() && !d.getInventory().getRenameText().equalsIgnoreCase(d.getInventory().getItem(0).getItemMeta().getDisplayName())) {
			Material type = d.getInventory().getItem(0).getType();
			
			if(type == Material.NETHERITE_HELMET || type == Material.NETHERITE_LEGGINGS || type == Material.NETHERITE_CHESTPLATE|| type == Material.NETHERITE_BOOTS) {
				if(d.getInventory().getItem(0).getItemMeta().getDisplayName().contains("RedKnight")) {
					d.setResult(null);
					d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
					return;
				}
				if(d.getInventory().getItem(0).getItemMeta().getDisplayName().contains("Ocean")) {
					d.setResult(null);
					d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
					return;
				}
				if(d.getInventory().getItem(0).getItemMeta().getDisplayName().contains("Dark")) {
					d.setResult(null);
					d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
					return;
				}
				if(d.getInventory().getItem(0).getItemMeta().getDisplayName().contains("Frost")) {
					d.setResult(null);
					d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
					return;
				}
				if(d.getInventory().getItem(0).getItemMeta().getDisplayName().contains("Burning")) {
					d.setResult(null);
					d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
					return;
				}
				if(d.getInventory().getItem(0).getItemMeta().getDisplayName().contains("Hyper")) {
					d.setResult(null);
					d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
					return;
				}
				if(d.getInventory().getItem(0).getItemMeta().getDisplayName().contains("Earth")) {
					d.setResult(null);
					d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
					return;
				}
			}
		}
		if(ai.getItem(0) !=null && ai.getItem(1) !=null) {
			try {
				if(ai.getItem(0).getType().name().split("_")[1].equals(ai.getItem(1).getType().name().split("_")[1]) ) {
					ItemStack is0 = ai.getItem(0).clone();
					ItemStack is1 = ai.getItem(1).clone();
					ItemMeta im0 = is0.getItemMeta();
					ItemMeta im1 = is1.getItemMeta();
					if(im0 instanceof Damageable && im1 instanceof Damageable) {
						Damageable dim0 = (Damageable) im0;
						dim0.setDamage(0);
					}
					im0.getPersistentDataContainer().set(repaired, PersistentDataType.INTEGER, ai.getRepairCost());
					is0.setItemMeta(im0);
					HashMap<Enchantment, Integer> enh = new HashMap<Enchantment, Integer>();
					enh.putAll(is1.getEnchantments());
					is0.getEnchantments().keySet().forEach(en -> {
						if(enh.getOrDefault(en, 0) + is0.getEnchantmentLevel(en) >256){
							enh.put(en, 256);
						}
						else {
							enh.computeIfPresent(en, (k,v) -> v + is0.getEnchantmentLevel(en));
							enh.putIfAbsent(en, is0.getEnchantmentLevel(en));
						}
					});
					enh.keySet().forEach(en -> {
						is0.addUnsafeEnchantment(en, enh.get(en));
					});
					d.setResult(is0);
				}
			}
			catch(ArrayIndexOutOfBoundsException e) {
				if(ai.getItem(0).getType().name().equals(ai.getItem(1).getType().name()) ) {
					ItemStack is0 = ai.getItem(0).clone();
					ItemStack is1 = ai.getItem(1).clone();
					ItemMeta im0 = is0.getItemMeta();
					ItemMeta im1 = is1.getItemMeta();
					if(im0 instanceof Damageable && im1 instanceof Damageable) {
						Damageable dim0 = (Damageable) im0;
						dim0.setDamage(0);
					}
					im0.getPersistentDataContainer().set(repaired, PersistentDataType.INTEGER, ai.getRepairCost());
					is0.setItemMeta(im0);
					HashMap<Enchantment, Integer> enh = new HashMap<Enchantment, Integer>();
					enh.putAll(is1.getEnchantments());
					is0.getEnchantments().keySet().forEach(en -> {
						if(enh.getOrDefault(en, 0) + is0.getEnchantmentLevel(en) >256){
							enh.put(en, 256);
						}
						else {
							enh.computeIfPresent(en, (k,v) -> v + is0.getEnchantmentLevel(en));
							enh.putIfAbsent(en, is0.getEnchantmentLevel(en));
						}
					});
					enh.keySet().forEach(en -> {
						is0.addUnsafeEnchantment(en, enh.get(en));
					});
					d.setResult(is0);
				}
			}
		}
	}

	@EventHandler
	public void AnvilClick(InventoryClickEvent d) 
	{
		if (d.getClickedInventory() == null) {
			return;
		}
		else if(d.getClickedInventory().getType() == InventoryType.ANVIL) {
			AnvilInventory ai = (AnvilInventory) d.getClickedInventory();
			if(ai.getItem(2) != null && d.getCurrentItem() != null) {
				Material type = d.getInventory().getItem(2).getType();
				if(type == Material.NETHERITE_HELMET || type == Material.NETHERITE_LEGGINGS || type == Material.NETHERITE_CHESTPLATE|| type == Material.NETHERITE_BOOTS) {
					if(d.getCurrentItem().getItemMeta().getDisplayName().contains("RedKnight")) {
						d.setCancelled(true);
						ai.setRepairCost(0);
						d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
						return;
					}
					else if(d.getCurrentItem().getItemMeta().getDisplayName().contains("Ocean")) {
						d.setCancelled(true);
						ai.setRepairCost(0);
						d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
						return;
					}
					else if(d.getCurrentItem().getItemMeta().getDisplayName().contains("Dark")) {
						d.setCancelled(true);
						ai.setRepairCost(0);
						d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
						return;
					}
					else if(d.getCurrentItem().getItemMeta().getDisplayName().contains("Frost")) {
						d.setCancelled(true);
						ai.setRepairCost(0);
						d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
						return;
					}
					else if(d.getCurrentItem().getItemMeta().getDisplayName().contains("Burning")) {
						d.setCancelled(true);
						ai.setRepairCost(0);
						d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
						return;
					}
					else if(d.getCurrentItem().getItemMeta().getDisplayName().contains("Hyper")) {
						d.setCancelled(true);
						ai.setRepairCost(0);
						d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
						return;
					}
					else if(d.getCurrentItem().getItemMeta().getDisplayName().contains("Earth")) {
						d.setCancelled(true);
						ai.setRepairCost(0);
						d.getViewers().forEach(p -> p.sendMessage("You Can't Change Name"));
						return;
					}
				}
				Player p = (Player) d.getWhoClicked();
				if(p.getInventory().firstEmpty() != -1 && d.getCurrentItem().equals(ai.getItem(2)) && d.getCurrentItem().getItemMeta().getPersistentDataContainer().has(repaired, PersistentDataType.INTEGER)  ) {
					
					d.setCancelled(true);
					ItemStack ci = d.getCurrentItem();
					ItemMeta cm = ci.getItemMeta();
					p.giveExp(cm.getPersistentDataContainer().get(repaired, PersistentDataType.INTEGER));
					cm.getPersistentDataContainer().remove(repaired);
					ci.setItemMeta(cm);
					p.getInventory().addItem(ci);
					ai.clear();
				}
			}
		}
	}
	
}
