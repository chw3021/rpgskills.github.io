package io.github.chw3021.items.weapons;

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
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import io.github.chw3021.rmain.RMain;

public class Wand extends Weapons implements Listener {

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
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		meta.setCustomModelData(9000);
		meta.setMaxStackSize(1);
		if(lang.contains("kr")){
			meta.setDisplayName("나무 지팡이");
			meta.setItemName("나무 지팡이");
		}
		else {
			meta.setDisplayName("Wooden Wand");
			meta.setItemName("Wooden Wand");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "wooden_wand"), is);
		rc.shape("a  ", " s ", "  s");
		rc.shape("  a", " s ", "s  ");
		rc.setIngredient('a', rcc);
		rc.setIngredient('s', Material.STICK);
		Bukkit.getServer().addRecipe(rc);
	}
	
	@EventHandler
	public void StoneWand(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 5, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		meta.setCustomModelData(9000 + 1);
		meta.setMaxStackSize(1);
		if(lang.contains("kr")){
			meta.setDisplayName("돌 지팡이");
			meta.setItemName("돌 지팡이");
		}
		else {
			meta.setDisplayName("Stone Wand");
			meta.setItemName("Stone Wand");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "stone_wand"), is);
		rc.shape("a  ", " s ", "  s");
		rc.shape("  a", " s ", "s  ");
		rc.setIngredient('s', Material.STICK);
		rc.setIngredient('a', Material.COBBLESTONE);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void GoldWand(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 4, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		meta.setCustomModelData(9000 + 2);
		meta.setMaxStackSize(1);
		if(lang.contains("kr")){
			meta.setDisplayName("금 지팡이");
			meta.setItemName("금 지팡이");
		}
		else {
			meta.setDisplayName("Gold Wand");
			meta.setItemName("Gold Wand");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "gold_wand"), is);
		rc.shape("a  ", " s ", "  s");
		rc.shape("  a", " s ", "s  ");
		rc.setIngredient('s', Material.STICK);
		rc.setIngredient('a', Material.GOLD_INGOT);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void IronWand(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 7, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		meta.setCustomModelData(9000 + 3);
		meta.setMaxStackSize(1);
		if(lang.contains("kr")){
			meta.setDisplayName("철 지팡이");
			meta.setItemName("철 지팡이");
		}
		else {
			meta.setDisplayName("Iron Wand");
			meta.setItemName("Iron Wand");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "iron_wand"), is);
		rc.shape("a  ", " s ", "  s");
		rc.shape("  a", " s ", "s  ");
		rc.setIngredient('s', Material.STICK);
		rc.setIngredient('a', Material.IRON_INGOT);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void DiamondWand(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 8, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		meta.setCustomModelData(9000 + 4);
		meta.setMaxStackSize(1);
		if(lang.contains("kr")){
			meta.setDisplayName("다이아몬드 지팡이");
			meta.setItemName("다이아몬드 지팡이");
		}
		else {
			meta.setDisplayName("Diamond Wand");
			meta.setItemName("Diamond Wand");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "diamond_wand"), is);
		rc.shape("a  ", " s ", "  s");
		rc.shape("  a", " s ", "s  ");
		rc.setIngredient('s', Material.STICK);
		rc.setIngredient('a', Material.DIAMOND);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void NetheriteWand(PrepareSmithingEvent d) {
		ItemStack r = new ItemStack(Material.BLAZE_ROD);
		ItemMeta rm = r.getItemMeta();
		rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 10, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		rm.setCustomModelData(9000 + 60);
		rm.setMaxStackSize(1);
		Player p = (Player) d.getView().getPlayer();
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			rm.setDisplayName("네더라이트 지팡이");
			rm.setItemName("네더라이트 지팡이");
		}
		else {
			rm.setDisplayName("Netherite Wand");
			rm.setItemName("Netherite Wand");
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
				if(!rm0.getDisplayName().equals(rm0.getItemName())) {
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
					rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 10, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
					rm.setCustomModelData(9000 + 60);
					rm.setMaxStackSize(1);
					
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("네더라이트 지팡이");
						rm.setItemName("네더라이트 지팡이");
					}
					else {
						rm.setDisplayName("Netherite Wand");
						rm.setItemName("Netherite Wand");
					}

					ItemMeta rm0 = d.getInventory().getItem(0).getItemMeta();
					if(!rm0.getDisplayName().equals(rm0.getItemName())) {
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