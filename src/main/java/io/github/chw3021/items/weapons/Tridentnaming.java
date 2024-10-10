package io.github.chw3021.items.weapons;

import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public class Tridentnaming extends Weapons implements Listener {

	/*@EventHandler
	public void Naming(PlayerItemHeldEvent e) 
	{
		Player p = e.getPlayer();

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
        		if(p.getEquipment().getItemInMainHand().getType() == Material.TRIDENT)
        		{
        			ItemStack mi = p.getEquipment().getItemInMainHand();
        			if(mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData()) {
        				ItemStack ni = new ItemStack(Material.DIAMOND_SWORD);
        				ItemMeta nm = ni.getItemMeta();
        				nm.setCustomModelData(mi.getItemMeta().getCustomModelData());
        				ni.setItemMeta(nm);
        				ni.addUnsafeEnchantments(mi.getEnchantments());
        				
        				p.sendEquipmentChange(p, EquipmentSlot.HAND, ni);
        			}
        			
        		}
        		else if(p.getEquipment().getItemInOffHand().getType() == Material.TRIDENT)
        		{
        			ItemStack mi = p.getEquipment().getItemInOffHand();
        			if(mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData()) {
        				ItemStack ni = new ItemStack(Material.DIAMOND_SWORD);
        				ItemMeta nm = ni.getItemMeta();
        				nm.setCustomModelData(mi.getItemMeta().getCustomModelData());
        				ni.setItemMeta(nm);
        				ni.addUnsafeEnchantments(mi.getEnchantments());
        				
        				p.sendEquipmentChange(p, EquipmentSlot.OFF_HAND, ni);
        			}
        			
        		}
            }
        }, 2); 
	}*/

	@EventHandler
	public void Naming(ProjectileLaunchEvent e) 
	{

		if(e.getEntity() instanceof Trident)
		{
			Trident t = (Trident) e.getEntity();
			ItemStack is = t.getItem();
		
			if(is.hasItemMeta() && is.getItemMeta().hasCustomModelData()) {
				t.setCustomName(is.getItemMeta().getItemName());
				t.setCustomNameVisible(false);
			}
		}
	}

}
