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
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import io.github.chw3021.rmain.RMain;

public class Dagger extends Weapons implements Listener {

	
	private ShapedRecipe getDaggerRecipe(ItemStack is, Material mainIng, String key) {
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), key), is);
		rc.shape("a ", " x");
		rc.shape(" a", "x ");
		rc.setIngredient('x', Material.STICK);
		rc.setIngredient('a', mainIng);
		
		return rc;
	}
	
	@EventHandler
	public void WoodDagger(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.SHEARS);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		meta.setCustomModelData(11000+1);
		if(lang.contains("kr")){
			meta.setDisplayName("나무 단검");
			meta.setItemName("나무 단검");
		}
		else {
			meta.setDisplayName("Wooden Dagger");
			meta.setItemName("Wooden Dagger");
		}
		is.setItemMeta(meta);
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(RMain.getInstance(), "wooden_dagger"), is);
		rc.shape("a ", " x");
		rc.shape(" a", "x ");
		rc.setIngredient('x', Material.STICK);
		rc.setIngredient('a', rcc);
		Bukkit.getServer().addRecipe(rc);
	}

	@EventHandler
	public void StoneDagger(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.SHEARS);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		meta.setCustomModelData(11000+1);
		if(lang.contains("kr")){
			meta.setDisplayName("돌 단검");
			meta.setItemName("돌 단검");
		}
		else {
			meta.setDisplayName("Stone Dagger");
			meta.setItemName("Stone Dagger");
		}
		is.setItemMeta(meta);
		Bukkit.getServer().addRecipe(getDaggerRecipe(is, Material.COBBLESTONE, "stone_dagger"));
	}

	@EventHandler
	public void GoldDagger(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.SHEARS);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 2, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		meta.setCustomModelData(11000+2);
		if(lang.contains("kr")){
			meta.setDisplayName("금 단검");
			meta.setItemName("금 단검");
		}
		else {
			meta.setDisplayName("Gold Dagger");
			meta.setItemName("Gold Dagger");
		}
		is.setItemMeta(meta);
		Bukkit.getServer().addRecipe(getDaggerRecipe(is, Material.GOLD_INGOT, "gold_dagger"));
	}

	@EventHandler
	public void IronDagger(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.SHEARS);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		meta.setCustomModelData(11000+3);
		if(lang.contains("kr")){
			meta.setDisplayName("철 단검");
			meta.setItemName("철 단검");
		}
		else {
			meta.setDisplayName("Iron Dagger");
			meta.setItemName("Iron Dagger");
		}
		is.setItemMeta(meta);
		Bukkit.getServer().addRecipe(getDaggerRecipe(is, Material.IRON_INGOT, "iron_dagger"));
	}

	@EventHandler
	public void DiamondDagger(PluginEnableEvent d) {
		ItemStack is = new ItemStack(Material.SHEARS);
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 5, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		meta.setCustomModelData(11000+4);
		if(lang.contains("kr")){
			meta.setDisplayName("다이아몬드 단검");
			meta.setItemName("다이아몬드 단검");
		}
		else {
			meta.setDisplayName("Diamond Dagger");
			meta.setItemName("Diamond Dagger");
		}
		is.setItemMeta(meta);
		Bukkit.getServer().addRecipe(getDaggerRecipe(is, Material.DIAMOND, "diamond_dagger"));
	}

	@EventHandler
	public void NetheriteDagger(PrepareSmithingEvent d) {
		ItemStack r = new ItemStack(Material.SHEARS);
		ItemMeta rm = r.getItemMeta();
		rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 7, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
		rm.setCustomModelData(11000+60);
		
		Player p = (Player) d.getView().getPlayer();
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			rm.setDisplayName("네더라이트 단검");
			rm.setItemName("네더라이트 단검");
		}
		else {
			rm.setDisplayName("Netherite Dagger");
			rm.setItemName("Netherite Dagger");
		}

		if (d.getInventory().getItem(0) != null && d.getInventory().getItem(0).getAmount() >= 1
				&& d.getInventory().getItem(0).getItemMeta().hasCustomModelData() && d.getInventory().getItem(1) != null
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(nethercore, PersistentDataType.STRING, "none").equals("netherCore")
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(endercore, PersistentDataType.STRING, "none").equals("enderCore")
				&& !d.getInventory().getItem(0).getItemMeta().getPersistentDataContainer()
						.getOrDefault(herocore, PersistentDataType.STRING, "none").equals("heroCore")) {
			if ((d.getInventory().getItem(0).getItemMeta().getCustomModelData() == 11000+4)
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
	public void NetheriteDagger(SmithItemEvent d) {
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
				if (d.getCurrentItem().getItemMeta().getCustomModelData() == 11000+60) {
					Player p = (Player) d.getWhoClicked();
					ItemStack r = new ItemStack(Material.SHEARS);
					ItemMeta rm = r.getItemMeta();
					rm.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(getKey(), 7, Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
					rm.setCustomModelData(11000+60);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						rm.setDisplayName("네더라이트 단검");
						rm.setItemName("네더라이트 단검");
					}
					else {
						rm.setDisplayName("Netherite Dagger");
						rm.setItemName("Netherite Dagger");
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