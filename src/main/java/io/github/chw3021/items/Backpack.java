package io.github.chw3021.items;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;


public class Backpack implements Serializable, Listener{
	
	
	
	
	/**
	 * 
	 */
	private transient static final long serialVersionUID = 6810233339680505251L;
	/**
	 * 
	 */


	public final HashMap<UUID, ItemStack[]> chest;
    // Can be used for saving
	
	
    public Backpack(HashMap<UUID, ItemStack[]> chest) {
    	this.chest = chest;
    	}
    // Can be used for loading
    public Backpack(Backpack loadedData) {
    	this.chest = loadedData.chest;
    	}
 
	public Backpack saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return this;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return this;
        }
    }
    public static Backpack loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            Backpack data = (Backpack) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException|ExceptionInInitializerError  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            Backpack data = new Backpack(new HashMap<UUID, ItemStack[]>()).saveData(path +"/plugins/RPGskills/Backpack.data");
            
            e.printStackTrace();
            return data;
        }
    }
    
    final private void save(Player p,Inventory inv) {
        String path = new File("").getAbsolutePath();
        HashMap<UUID, ItemStack[]> chest = getdata();
        chest.put(p.getUniqueId(), inv.getContents());
		new Backpack(chest).saveData(path +"/plugins/RPGskills/BackPack.data");
        return;
    }
    
    final private Boolean check(Player p) {
    	HashMap<UUID, ItemStack[]> chest = getdata();
        return chest.containsKey(p.getUniqueId());
    }

    final private ItemStack[] getinv(Player p) {
        HashMap<UUID, ItemStack[]> chest = getdata();
		return chest.get(p.getUniqueId());
    }
	



    final private void checkoff(Player p) {

		
		String name = null;
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			name = p.getName() + "¿« πË≥∂";
		}
		else {
			name = p.getName() + "'s Backpack";
		}
		Inventory ci = Bukkit.createInventory(p, 54, name);
		
		if(check(p)) {
			ci.setContents(getinv(p));
		}
		p.openInventory(ci);
		save(p,ci);
    }

    final private void dumpster(Player p) {

		
		String name = null;
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			name = "æ≤∑π±‚≈Î";
		}
		else {
			name = "Dumpster";
		}
		Inventory ci = Bukkit.createInventory(p, 54, name);
		
		p.openInventory(ci);
    }

	@EventHandler
	public void Bag(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).contains("Skills") || ChatColor.stripColor(e.getView().getTitle()).contains("skills"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				String s = ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName()));
				if(s.equals("Backpack") || s.equals("πË≥∂")) {
					checkoff(p);
				}
				else if(s.equals("Dumpster") || s.equals("æ≤∑π±‚≈Î")) {
					dumpster(p);
				}
			}
		}
	}

    @EventHandler
	public void Close(InventoryCloseEvent d) 
	{
    	
		Inventory ci = d.getInventory();
		Player p = (Player) d.getPlayer();
		
		if(d.getView().getTitle().equals(p.getName() + "'s Backpack") || d.getView().getTitle().equals(p.getName() + "¿« πË≥∂")) {
			if(check(p)) {
				save(p,ci);
			}
		}
	}
	
	public HashMap<UUID, ItemStack[]> getdata(){
        String path = new File("").getAbsolutePath();
        Backpack data = new Backpack(Backpack.loadData(path +"/plugins/RPGskills/Backpack.data"));
		return data.chest;
	}
}
