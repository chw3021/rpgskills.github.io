package io.github.chw3021.items;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
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

import io.github.chw3021.commons.CombatMode;


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
            String path = new File("").getAbsolutePath();
            new Backpack(new HashMap<UUID, ItemStack[]>()).saveData(path +"/plugins/RPGskills/Backpack.data");
            
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
    
    final private void save(Player p,Inventory inv, int page) {
        String path = new File("").getAbsolutePath();
        HashMap<UUID, ItemStack[]> chest = getdata();
        ItemStack[] con = Arrays.copyOfRange(chest.get(p.getUniqueId()), (page-1)*54, (page-1)*54+53);
        chest.put(p.getUniqueId(), con);
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
	


	public static void itemset(String display, ItemStack is, int data, int stack, List<String> Lore, int loc,
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

    final private void checkoff(Player p) {

		
		String name = null;
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			name = p.getName() + "의 배낭";
		}
		else {
			name = p.getName() + "'s Backpack";
		}
		Inventory ci = Bukkit.createInventory(p, 54, name);
		int pagen = 0;
		if(check(p)) {
			if(getinv(p).length >51) {
				pagen = page(p,ci,1);
			}
			else {
				pagen =page(p,ci,0);
			}
		}
		p.openInventory(ci);
		save(p,ci, pagen);
    }
    
    final private Integer page(Player p, Inventory pi, int op) {
    	if(op == 0) {
        	int current = (int) Math.ceil(getinv(p).length/54*1.0);
    		
    		String name = null;
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			name = p.getName() + "의 배낭";
    		}
    		else {
    			name = p.getName() + "'s Backpack";
    		}
    		Inventory ci = Bukkit.createInventory(p, 54, name);
    		
    		if(check(p)) {
		        ItemStack[] arr1 = Arrays.copyOfRange(getinv(p), (current-1)*54, (current-1)*54+51);
				ci.setContents(arr1);
    		}
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			itemset(ChatColor.GOLD + "이전 페이지", new ItemStack(Material.CHAIN_COMMAND_BLOCK), 0, 1,
    					Arrays.asList(ChatColor.GOLD + "" + (current)), 52, ci);
    			itemset(ChatColor.GOLD + "다음 페이지", new ItemStack(Material.CHAIN_COMMAND_BLOCK), 0, 1,
    					Arrays.asList(ChatColor.GOLD + "" + (current)), 53, ci);
    		}
    		else {
    			itemset(ChatColor.GOLD + "Prev", new ItemStack(Material.CHAIN_COMMAND_BLOCK), 0, 1,
    					Arrays.asList(ChatColor.GOLD + "" + (current)), 52, ci);
    			itemset(ChatColor.GOLD + "Next", new ItemStack(Material.CHAIN_COMMAND_BLOCK), 0, 1,
    					Arrays.asList(ChatColor.GOLD + "" + (current)), 53, ci);
    		}
    		return current;
    	}
    	else {
    		String name = null;
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			name = p.getName() + "의 배낭";
    		}
    		else {
    			name = p.getName() + "'s Backpack";
    		}
    		Inventory ci = Bukkit.createInventory(p, 54, name);

    		if(check(p)) {
				ci.setContents(getinv(p));
    		}
    		
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			itemset(ChatColor.GOLD + "이전 페이지", new ItemStack(Material.CHAIN_COMMAND_BLOCK), 0, 1,
    					Arrays.asList(ChatColor.GOLD + "1"), 52, ci);
    			itemset(ChatColor.GOLD + "다음 페이지", new ItemStack(Material.CHAIN_COMMAND_BLOCK), 0, 1,
    					Arrays.asList(ChatColor.GOLD + "1"), 53, ci);
    		}
    		else {
    			itemset(ChatColor.GOLD + "Prev", new ItemStack(Material.CHAIN_COMMAND_BLOCK), 0, 1,
    					Arrays.asList(ChatColor.GOLD + "1"), 52, ci);
    			itemset(ChatColor.GOLD + "Next", new ItemStack(Material.CHAIN_COMMAND_BLOCK), 0, 1,
    					Arrays.asList(ChatColor.GOLD + "1"), 53, ci);
    		}
    		return 1;
    	}
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
					checkoff(p);
				}
				else if(s.equals("Dumpster") || s.equals("쓰레기통")) {
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
		
		if(d.getView().getTitle().equals(p.getName() + "'s Backpack") || d.getView().getTitle().equals(p.getName() + "의 배낭")) {
			if(check(p)) {
				save(p,ci, Integer.parseInt(ci.getItem(52).getItemMeta().getLore().get(0)));
			}
		}
	}

    private static ItemStack[] Add(ItemStack[] inv, ItemStack val) {
    	List<ItemStack> iss = Arrays.asList(inv);
    	if(iss.stream().anyMatch(val::isSimilar)) {
    		for(ItemStack is : iss) {
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
    		}
    	}
    	return (ItemStack[]) iss.toArray();
    }
    
    public static void add(Player p, ItemStack is) {
        HashMap<UUID, ItemStack[]> chest = getdata();
        ItemStack[] nis = Add(chest.get(p.getUniqueId()), is);
        chest.put(p.getUniqueId(), nis);
        String path = new File("").getAbsolutePath();
		new Backpack(chest).saveData(path +"/plugins/RPGskills/BackPack.data");
    	
    }
    
	public static HashMap<UUID, ItemStack[]> getdata(){
        String path = new File("").getAbsolutePath();
        Backpack data = new Backpack(Backpack.loadData(path +"/plugins/RPGskills/Backpack.data"));
		return data.chest;
	}
}
