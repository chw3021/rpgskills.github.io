package io.github.chw3021.commons;



import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ShulkerBag implements Listener {
	
	static private HashMap<Inventory,ShulkerBox> sisave = new HashMap<>();

	@EventHandler
	static public void ShulkerOpen(BlockPlaceEvent d) 
	{
		if(d.getPlayer().isSneaking() && d.getBlock().getType().name().contains("SHULKER_BOX")) {
			ShulkerBox skb = (ShulkerBox) d.getBlock().getState();
			Player p = d.getPlayer();
			ItemStack key = new ItemStack(Material.SHULKER_BOX);
			ItemMeta im =  key.getItemMeta();
			im.setDisplayName(p.getUniqueId().toString());
			im.setLocalizedName(p.getUniqueId().toString());
			key.setItemMeta(im);
			p.getEquipment().setItemInMainHand(key);
			skb.setLock(p.getUniqueId().toString());
			Inventory si = skb.getInventory();
			d.getPlayer().openInventory(si);
			sisave.put(si,skb);
		}
	}

	@EventHandler
	public void BlockBreak(InventoryClickEvent d) 
	{
		Player p = (Player) d.getWhoClicked();
		if(d.getCurrentItem() != null) {
			ItemStack ci = d.getCurrentItem();
			if(ci.hasItemMeta() && ci.getItemMeta().hasLocalizedName() && ci.getItemMeta().getLocalizedName().equals(p.getUniqueId().toString())) {
				d.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void BlockBreak(BlockBreakEvent d) 
	{
		if(sisave.containsValue(d.getBlock().getState())) {
			d.setCancelled(true);
		}
	}
	
	
	@EventHandler
	static public void Close(InventoryCloseEvent d) 
	{
		Inventory ci = d.getInventory();
		if(sisave.containsKey(ci)) {
			Player p = (Player) d.getPlayer();
			ShulkerBox skb = (ShulkerBox)sisave.get(ci);
			p.getEquipment().setItemInMainHand(skb.getBlock().getDrops().stream().findFirst().get());
			for(ItemStack is : p.getInventory().getContents()) {
				if(is != null && is.hasItemMeta() && is.getItemMeta().hasLocalizedName() && is.getItemMeta().getLocalizedName().equals(p.getUniqueId().toString())) {
					is.setAmount(0);
				}
			}
			
			skb.getBlock().setType(Material.VOID_AIR);
			sisave.remove(ci);
		}
	}
}



