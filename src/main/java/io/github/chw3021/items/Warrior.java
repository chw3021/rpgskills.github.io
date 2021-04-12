package io.github.chw3021.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class Warrior {
	
	public static ItemStack knuckle(Player p, Integer count) {
		ItemStack is = new ItemStack(Material.IRON_NUGGET);
		ItemMeta meta = is.getItemMeta();
		meta.setCustomModelData(123);
		is.setItemMeta(meta);
		is.setAmount(count);
		p.getInventory().addItem(is);
		return is;
	}
}
