package io.github.chw3021.items;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
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
    
    final private static Boolean check(Player p) {
		Table<UUID, Integer, ItemStack[]> chest = getdata();
		return chest.containsRow(p.getUniqueId());
    }

    final private static ItemStack[] getinv(Player p, Integer page) {
        Table<UUID, Integer, ItemStack[]> chest = getdata();
        if(!chest.contains(p.getUniqueId(), page)) {
        	chest.put(p.getUniqueId(), page, new ItemStack[54]);
        }
		return chest.get(p.getUniqueId(), page);
    }



	public static void itemset(String display, ItemStack is, int data, int stack, List<String> Lore, int loc,
			Inventory inv) {
		ItemStack item = is;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
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
			ci = page(p,0);
		}
		p.openInventory(ci);
		save(p,ci);
    }
    
    final private static Inventory page(Player p, int page){

		
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
		pagerm.setItemName("RpgBagpackPage");
		pager.setItemMeta(pagerm);
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			if(page>0) {
				itemset(ChatColor.GOLD + "이전 페이지", pager, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + (page)), 52, ci);
			}
			itemset(ChatColor.GOLD + "다음 페이지", pager, 0, 1,
					Arrays.asList(ChatColor.GOLD + "" + (page)), 53, ci);
		}
		else {
			if(page>0) {
				itemset(ChatColor.GOLD + "Prev", pager, 0, 1,
						Arrays.asList(ChatColor.GOLD + "" + (page)), 52, ci);
			}
			itemset(ChatColor.GOLD + "Next", pager, 0, 1,
					Arrays.asList(ChatColor.GOLD + "" + (page)), 53, ci);
		}
		return ci;
    }

    
    

    private int getCurrentPage(Inventory inv) {
        if (inv.getItem(53) != null && inv.getItem(53).hasItemMeta()) {
            List<String> lore = inv.getItem(53).getItemMeta().getLore();
            if (lore != null && !lore.isEmpty()) {
                try {
                	String pagenum = ChatColor.stripColor(lore.get(0));
                    return Integer.parseInt(pagenum); // 로어의 첫 번째 항목을 정수로 변환
                } catch (NumberFormatException e) {
                    // 예외 처리: 페이지가 숫자가 아닐 경우 기본값 0 반환
		            System.out.println(e.getMessage());
                    return 0;
                }
            }
        }
        return 0; // 기본값
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
					e.setResult(Result.DENY);
					try {
						checkoff(p);
					}
					catch(FileNotFoundException ex) {
						p.closeInventory();
					}
					p.setItemOnCursor(null);
				}
				else if(s.equals("Dumpster") || s.equals("쓰레기통")) {
					e.setCancelled(false);
					dumpster(p);
					p.setItemOnCursor(null);
				}
			}
		}
		
		if (e.getView().getTitle().contains(p.getName() + "'s Backpack") || e.getView().getTitle().contains(p.getName() + "의 배낭")) {
			 if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || !e.getCurrentItem().hasItemMeta()) {
		            return;
		        }

		        String displayName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());

		        // RpgBagpackPage 체크
		        if (!e.getCurrentItem().getItemMeta().getItemName().equals("RpgBagpackPage")) {
		            return;
		        }

		        int currentPage = getCurrentPage(e.getClickedInventory());

		        if (displayName.equals("Prev") || displayName.equals("이전 페이지")) {
		            e.setCancelled(true);
		            // 이전 페이지로 이동
		            if (currentPage > 0) {
		                Inventory newInventory = page(p, currentPage - 1);
		                p.openInventory(newInventory);
		            }
		        } else if (displayName.equals("Next") || displayName.equals("다음 페이지")) {
		            e.setCancelled(true);
		            // 다음 페이지로 이동
		            Inventory newInventory = page(p, currentPage + 1);
		            p.openInventory(newInventory);
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

	/*List<ItemStack> iss = new ArrayList<>(Arrays.asList(inv));
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
    	return iss.toArray(new ItemStack[iss.size()]);*/
	private static ItemStack[] Add(ItemStack[] inv, ItemStack val, int count, HashSet<Integer> indexes) {
	    for (Integer id : indexes) {
	        ItemStack currentSlotItem = inv[id];
	        int currentAmount = (currentSlotItem != null) ? currentSlotItem.getAmount() : 0;
	        int maxStackSize = val.getMaxStackSize();

	        // 현재 슬롯에 추가할 수 있는 양 계산
	        int spaceInSlot = maxStackSize - currentAmount;

	        if (count <= spaceInSlot) {
	            // 현재 슬롯에 모두 추가 가능
	            if (currentSlotItem == null) {
	                inv[id] = val.clone();
	                inv[id].setAmount(count);
	            } else {
	                currentSlotItem.setAmount(currentAmount + count);
	            }
	            break; // 아이템 추가 완료
	        } else {
	            // 현재 슬롯을 가득 채운 후, 남은 아이템은 다음 슬롯에 추가
	            if (currentSlotItem == null) {
	                inv[id] = val.clone();
	                inv[id].setAmount(maxStackSize);
	            } else {
	                currentSlotItem.setAmount(maxStackSize);
	            }
	            count -= spaceInSlot; // 슬롯에 추가한 양만큼 count 감소
	        }
	    }
	    return inv; // 갱신된 인벤토리 반환
	}
	
	public static void add(Player player, ItemStack item) {
	    try {
	        Table<UUID, Integer, ItemStack[]> chest = getdata();  // 가방 데이터 가져오기
	        int totalItems = item.getAmount();  // 아이템 총 개수
	        int stackSize = item.getMaxStackSize();  // 최대 스택 크기
	        int requiredSlots = (int) Math.ceil((double) totalItems / stackSize);  // 필요한 슬롯 수 계산
	        HashSet<Integer> emptyIndexes = new HashSet<>();  // 빈 슬롯 추적

	        ItemStack[] inventory = null;
	        

	        // 현재 페이지별로 빈 슬롯 탐색 (52, 53번 슬롯 제외)
	        for (int currentPage = 0; currentPage < chest.row(player.getUniqueId()).keySet().size(); currentPage++) {
	            ItemStack[] currentPageItems = chest.get(player.getUniqueId(), currentPage);

	            for (int slot = 0; slot < 52; slot++) {  // 52번과 53번 슬롯은 페이지 전환용이므로 제외
	                ItemStack currentItem = currentPageItems[slot];
	                if (currentItem == null || (currentItem.isSimilar(item) && currentItem.getAmount() < stackSize)) {
	                    emptyIndexes.add(slot);
	                    if (--requiredSlots <= 0) {
	                        inventory = currentPageItems;
	                        break;
	                    }
	                }
	            }

	            if (requiredSlots <= 0) break;
	        }

	        // 빈 슬롯을 찾지 못한 경우 새로운 페이지 생성
	        if (emptyIndexes.isEmpty()) {
	            int nextPage = chest.row(player.getUniqueId()).keySet().size();
	            inventory = new ItemStack[54];  // 새로운 페이지 생성
	            chest.put(player.getUniqueId(), nextPage, inventory);
	            
	            // 새로운 페이지에 "이전"과 "다음" 페이지 버튼 추가
	            Inventory newInventory = page(player, nextPage);  // 페이지 전환 아이템을 포함한 새 페이지 생성
	            chest.put(player.getUniqueId(), nextPage, newInventory.getContents());  // 새 페이지의 인벤토리 저장
	        }

	        // 아이템 추가
	        ItemStack[] updatedInventory = Add(inventory, item, totalItems, emptyIndexes);
	        chest.put(player.getUniqueId(), chest.row(player.getUniqueId()).keySet().size() - 1, updatedInventory);

	        // 데이터 저장
	        String path = new File("").getAbsolutePath();
	        new Backpack(chest).saveData(path + "/plugins/RPGskills/BackPack.data");

	    } catch (NullPointerException e) {
	        // 가방 데이터가 없을 경우 새 페이지 생성
	        Table<UUID, Integer, ItemStack[]> chest = HashBasedTable.create();
	        chest.put(player.getUniqueId(), 0, new ItemStack[54]);  // 첫 페이지 생성
	        String path = new File("").getAbsolutePath();
	        new Backpack(chest).saveData(path + "/plugins/RPGskills/BackPack.data");
	    }
	}

	public static Table<UUID, Integer, ItemStack[]> getdata() {
        String path = new File("").getAbsolutePath();
        Backpack data = new Backpack(Backpack.loadData(path +"/plugins/RPGskills/Backpack.data"));
		return data.chest;
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
}