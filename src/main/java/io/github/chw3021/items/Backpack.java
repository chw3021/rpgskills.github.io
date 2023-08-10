package io.github.chw3021.items;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import io.github.chw3021.commons.CombatMode;


public class Backpack implements Serializable, Listener{
	
	
	
	
	/**
	 * 
	 */
	private transient static final long serialVersionUID = 6810233339680505251L;
	/**
	 * 
	 */


	public final Table<UUID, Integer, ItemStack[]> chest;
    // Can be used for saving
	
	
    public Backpack(Table<UUID, Integer, ItemStack[]> chest) {
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
            String path = new File("").getAbsolutePath();
            new Backpack(HashBasedTable.create()).saveData(path +"/plugins/RPGskills/Backpack.data");
            
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
            Backpack data = new Backpack(HashBasedTable.create()).saveData(path +"/plugins/RPGskills/Backpack.data");
            
            e.printStackTrace();
            return data;
        }
    }
    
    final private void save(Player p,Inventory inv) {
        String path = new File("").getAbsolutePath();
        
        if(inv.getItem(53)!=null) {
            chest.put(p.getUniqueId(),Integer.valueOf(ChatColor.stripColor(inv.getItem(53).getItemMeta().getLore().get(0))) , inv.getContents());
    		new Backpack(chest).saveData(path +"/plugins/RPGskills/BackPack.data");
        }
        return;
    }
    
    final private Boolean check(Player p) {
		try {
			Table<UUID, Integer, ItemStack[]> chest = getdata();
			return chest.containsRow(p.getUniqueId());
		}
		catch(FileNotFoundException e) {
			p.closeInventory();
		}
		return null;
    }

    final private static ItemStack[] getinv(Player p, Integer page) throws FileNotFoundException {
        Table<UUID, Integer, ItemStack[]> chest = getdata();
        if(!chest.contains(p.getUniqueId(), page)) {
        	chest.put(p.getUniqueId(), page, new ItemStack[54]);
        }
		return chest.get(p.getUniqueId(), page);
    }



	public void itemset(String display, ItemStack is, int data, int stack, List<String> Lore, int loc,
			Inventory inv) {
		ItemStack item = is;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}

    final private void checkoff(Player p) throws FileNotFoundException {

		
		String name = null;
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			name = p.getName() + "의 배낭";
		}
		else {
			name = p.getName() + "'s Backpack";
		}
		Inventory ci = Bukkit.createInventory(p, 54, name);
		if(check(p)) {
			ci = page(p,ci,0);
		}
		p.openInventory(ci);
		save(p,ci);
    }
    
    final private Inventory page(Player p, Inventory pi, int page) throws FileNotFoundException {

		
		String name = null;
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			name = p.getName() + "의 배낭";
		}
		else {
			name = p.getName() + "'s Backpack";
		}
		Inventory ci = Bukkit.createInventory(p, 54, name);
		
		if(check(p)) {
			ci.setContents(getinv(p, page));
		}
		ItemStack pager = new ItemStack(Material.CHAIN_COMMAND_BLOCK);
		ItemMeta pagerm = pager.getItemMeta();
		pagerm.setLocalizedName("RpgBagpackPage");
		pager.setItemMeta(pagerm);
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset(ChatColor.GOLD + "이전 페이지", pager, 0, 1,
					Arrays.asList(ChatColor.GOLD + "" + (page)), 52, ci);
			itemset(ChatColor.GOLD + "다음 페이지", pager, 0, 1,
					Arrays.asList(ChatColor.GOLD + "" + (page)), 53, ci);
		}
		else {
			itemset(ChatColor.GOLD + "Prev", pager, 0, 1,
					Arrays.asList(ChatColor.GOLD + "" + (page)), 52, ci);
			itemset(ChatColor.GOLD + "Next", pager, 0, 1,
					Arrays.asList(ChatColor.GOLD + "" + (page)), 53, ci);
		}
		return ci;
    }

    final private void dumpster(Player p) {

		
		String name = null;
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			name = "쓰레기통";
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
		if(ChatColor.stripColor(e.getView().getTitle()).contains("Skills") || ChatColor.stripColor(e.getView().getTitle()).contains("skills") || CombatMode.getInstance().isCombat(p))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				String s = ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName()));
				if(s.equals("Backpack") || s.equals("배낭")) {
					try {
						checkoff(p);
					}
					catch(FileNotFoundException ex) {
						p.closeInventory();
					}
					p.setItemOnCursor(null);
				}
				else if(s.equals("Dumpster") || s.equals("쓰레기통")) {
					dumpster(p);
					p.setItemOnCursor(null);
				}
			}
		}
	}
	
	

    @EventHandler
	public void Close(InventoryCloseEvent d) 
	{
    	
		Inventory ci = d.getInventory();
		Player p = (Player) d.getPlayer();
		
		if(d.getView().getTitle().equals(p.getName() + "'s Backpack") || d.getView().getTitle().equals(p.getName() + "의 배낭")) {
			if(check(p)) {
				save(p,ci);
			}
		}
	}

    private static ItemStack[] Add(ItemStack[] inv, ItemStack val) {
    	if(val.getAmount() > is.getMaxStackSize()){
		
		int amount = val.getAmount();
		for(i=0; i<Math.ceil((amount*1.0)/is.getMaxStackSize()*1.0); i++) {
		}
	}
	List<ItemStack> iss = new ArrayList<>(Arrays.asList(inv));
    	if(val==null) {
    		return inv;
    	}
    	if(iss.stream().anyMatch(v -> v!=null && v.isSimilar(val))) {
    		for(ItemStack is : iss) {
        		if(iss==null) {
        			continue;
        		}
    			if(is.isSimilar(val)) {
    				final int fa = is.getAmount();
    				int amount = fa+val.getAmount();
        			if(amount > is.getMaxStackSize()) {
        				int i =0; 
        				for(i=0; i<(amount*1.0)/is.getMaxStackSize()*1.0; i++) {
        					ItemStack newis = is.clone();
        					newis.setAmount(is.getMaxStackSize());
        					iss.add(newis);
        				}
        				if(amount%is.getMaxStackSize()+val.getAmount() > is.getMaxStackSize()) {
        					ItemStack newis = is.clone();
        					newis.setAmount(is.getMaxStackSize());
        					iss.add(newis);
            				is.setAmount(amount%is.getMaxStackSize()+fa-is.getMaxStackSize());
        				}
        			}
        			else {
        				is.setAmount(amount);
        			}
    			}
    			else {
            		iss.add(val);
    			}
    		}
    	}
    	else {
    		iss.add(val);
    	}
    	return iss.toArray(new ItemStack[iss.size()]);
    }
    
    public static void add(Player p, ItemStack is) {

        Table<UUID, Integer, ItemStack[]> chest;
		try {
			chest = getdata();
			int count = 1;
		    	if(is.getAmount() > is.getMaxStackSize()){
				count = Math.ceil((is.getAmount()*1.0)/is.getMaxStackSize()*1.0);
			}
			ItemStack[] input = null;
			int i = 0; 
			for(i++; i< chest.row(p.getUniqueId()).keySet().size()){
					
				for(ItemStack elis : chest.get(p.getUniqueId(),i){
					if(elis == null){
						count--;
						if(count
					}
				}
			}
		        ItemStack[] nis = Add(getinv(p, chest.row(p.getUniqueId()).keySet().size()-1), is);
		        chest.put(p.getUniqueId(), chest.row(p.getUniqueId()).keySet().size()-1, nis);
		        String path = new File("").getAbsolutePath();
				new Backpack(chest).saveData(path +"/plugins/RPGskills/BackPack.data");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			p.closeInventory();
		}
    }
    
	public static Table<UUID, Integer, ItemStack[]> getdata() throws FileNotFoundException{
        String path = new File("").getAbsolutePath();
        Backpack data = new Backpack(Backpack.loadData(path +"/plugins/RPGskills/Backpack.data"));
		return data.chest;
	}
}
