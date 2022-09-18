package io.github.chw3021.obtains.quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.StructureType;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.party.Party;
import io.github.chw3021.items.Elements;
import io.github.chw3021.items.Potions;
import io.github.chw3021.obtains.NPCsSpawn;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.obtains.TrophyLoc;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class PillagerOutpostQuest implements Listener {

	private HashMap<UUID, Integer> asked = new HashMap<UUID, Integer>();
	private HashMap<UUID, Location> quested = new HashMap<UUID, Location>();
	private HashMap<UUID, Location> startloc = new HashMap<UUID, Location>();
	private HashMap<UUID, Integer> clearable = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> qt = new HashMap<UUID, Integer>();
	private static Multimap<String, UUID> qmobs = ArrayListMultimap.create();

	private ItemStack map = null;


	private static final PillagerOutpostQuest instance = new PillagerOutpostQuest ();
	public static PillagerOutpostQuest getInstance()
	{
		return instance;
	}
	
	private void QuestEnd(Player p, Integer factor) {
		if(qt.containsKey(p.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(qt.get(p.getUniqueId()));
			qt.remove(p.getUniqueId());
		}
		if(clearable.containsKey(p.getUniqueId())) {
			clearable.remove(p.getUniqueId());
		}
		if(asked.containsKey(p.getUniqueId())) {
			asked.remove(p.getUniqueId());
		}
		if(startloc.containsKey(p.getUniqueId())) {
			startloc.remove(p.getUniqueId());
		}
		if(qmobs.containsKey(p.getName())) {
			qmobs.get(p.getName()).forEach(re -> {
				if(Bukkit.getEntity(re) != null) {
					Bukkit.getEntity(re).remove();
	    		}
			});
			qmobs.removeAll(p.getName());
		}
		
		if(factor ==0) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendMessage("����Ʈ�� ��ҵǾ����ϴ�(�Ÿ�)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Too Far Away)");
    		}
		}
		else if (factor == 2) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendMessage("����Ʈ�� ��ҵǾ����ϴ�(���)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Death)");
    		}
		}
		else if (factor == 3) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendMessage("����Ʈ�� ��ҵǾ����ϴ�(����)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Given Up)");
    		}
		}
		else if (factor == 4) {
    		p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendTitle(ChatColor.GOLD + "����Ʈ �Ϸ�!",ChatColor.GOLD + "��Ż�� ���ʱ��� ����ǰ�� ȹ���߽��ϴ�!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "��Ż���� ���ΰ� �ŷ��� �� �� �ֽ��ϴ�").create());
		    }
    		else {
    			p.sendTitle(ChatColor.GOLD + "Complete Quest!",ChatColor.GOLD + "You Just Obtained PillagerOutpost Trophy!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "Able To Trade With Looted Trader").create());
    		}
    		Obtained.saver(p, 8, 1);
    		TrophyLoc.saver(p, quested.get(p.getUniqueId()));
        	Elements.give(Material.LAPIS_LAZULI, 5, p);
        	Elements.give(Material.EMERALD, 5, p);
        	Elements.give(Elements.getel(8, p),5, p);
			p.giveExp(100);
			Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,100));
		}
		if(quested.containsKey(p.getUniqueId())) {
			quested.remove(p.getUniqueId());
		}
	}

	
	public void QuestStart(PlayerInteractEntityEvent d) 
	{	
		if(d.getRightClicked().hasMetadata("obnpc") && d.getRightClicked().hasMetadata("pillageroutpost")) {
			d.setCancelled(true);
				WanderingTrader le = (WanderingTrader)d.getRightClicked();
				Player p = (Player) d.getPlayer();
				if(p.hasCooldown(Material.RAIL)) {
					return;
				}
        		p.setCooldown(Material.RAIL, 10);
				if(quested.containsKey(p.getUniqueId())) {
		    		QuestEnd(p,3);
					return;
				}
				if(TrophyLoc.getLocsdata().Locs.containsEntry(p.getUniqueId(), NPCsSpawn.npcloc.get(le.getUniqueId()))) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": ������!").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Have A Nice Day!").create());
	        		}
	        		List<MerchantRecipe> mrl = new ArrayList<MerchantRecipe>();
	        		
	        		MerchantRecipe mr1 = new MerchantRecipe(new ItemStack(Material.EXPERIENCE_BOTTLE), 1,64,true);
	        		ArrayList<ItemStack> in1 = new ArrayList<>();
	        		in1.add(new ItemStack(Material.EMERALD,3));
	        		mr1.setIngredients(in1);
	        		mrl.add(mr1);
	        		
	        		MerchantRecipe mr2 = new MerchantRecipe(Potions.get(5, p), 1,64,true);
	        		ArrayList<ItemStack> in2 = new ArrayList<>();
	        		in2.add(new ItemStack(Material.EMERALD,1));
	        		mr2.setIngredients(in2);
	        		mrl.add(mr2);

	        		MerchantRecipe mr3 = new MerchantRecipe(Elements.getpor(8,p), 1,64,true);
	        		ArrayList<ItemStack> in3 = new ArrayList<>();
	        		in3.add(new ItemStack(Material.EMERALD,6));
	        		in3.add(new ItemStack(Material.LAPIS_LAZULI,6));
	        		mr3.setIngredients(in3);
	        		mrl.add(mr3);

	        		MerchantRecipe mr4 = new MerchantRecipe(Elements.getpor(9,p), 1,64,true);
	        		mr4.setIngredients(in3);
	        		mrl.add(mr4);

	        		MerchantRecipe mr6 = new MerchantRecipe(Elements.getpor(10,p), 1,64,true);
	        		mr6.setIngredients(in3);
	        		mrl.add(mr6);
	        		
	        		MerchantRecipe mr7 = new MerchantRecipe(new ItemStack(Material.SADDLE), 1,64,true);
	        		ArrayList<ItemStack> in4 = new ArrayList<>();
	        		in4.add(new ItemStack(Material.EMERALD,3));
	        		mr7.setIngredients(in4);
	        		mrl.add(mr7);
	        		
	        		ArrayList<ItemStack> in5 = new ArrayList<>();
	        		in5.add(new ItemStack(Material.EMERALD,3));
	        		if(map == null) {
		        		map = Bukkit.createExplorerMap(p.getWorld(), p.getLocation(),  StructureType.WOODLAND_MANSION,200,true);
		        		ItemMeta mapm = map.getItemMeta();
		        		

		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        			mapm.setDisplayName(ChatColor.ITALIC+"�긲 ������ ���� ����");
					    }
		        		else {
		        			mapm.setDisplayName(ChatColor.ITALIC+"Woodland Mansion Map");
		        		}
		        		map.setItemMeta(mapm);
		        		MerchantRecipe mr5 = new MerchantRecipe(map, 1,64,true);
		        		mr5.setIngredients(in5);
		        		mrl.add(mr5);
		        		
	        		}
	        		else {
		        		MerchantRecipe mr5 = new MerchantRecipe(map, 1,64,true);
		        		mr5.setIngredients(in5);
		        		mrl.add(mr5);
	        		}
	        		
	        		Merchant mi = Bukkit.createMerchant(le.getCustomName());
	        		mi.setRecipes(mrl);
	        		p.openMerchant(mi, true);
	        		
	        		p.setCooldown(Material.RAIL, 3);
					return;
				}
				if(Party.hasParty(p)) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "��Ƽ�� ���� ���·δ� ������ �Ұ����մϴ�").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You should leave Your Party").create());
	        		}
					return;
				}
				if(p.getInventory().firstEmpty() == -1) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�κ��丮�� ��ĭ�� �ּ� ��ĭ�� �־�� �մϴ�").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You should empty inventory least one space").create());
	        		}
					return;
				}
				
				
            	if(asked.containsKey(p.getUniqueId())) {
            		if(qt.containsKey(p.getUniqueId())) {
            			Bukkit.getScheduler().cancelTask(qt.get(p.getUniqueId()));
            			qt.remove(p.getUniqueId());
            		}
            		asked.remove(p.getUniqueId());
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": �����մϴ�.. ���� �����մϴ�.. ��Ż�ڵ��� �� ������̳� �ٸ����� ������ ���������..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": �����ȿ� â���� �������ΰ� ������..Ȥ�� ���ش� �ֽǼ��ֳ���?").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (���, ����� �Ǵ� �ʹ� �ָ� ���� ����Ʈ�� ��ҵ˴ϴ�.)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": I thank you very much. The Pillagers took the same bag as my entire fortune.").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": I think it's stored in a Chest inside the Outpost..Could you get it for me?").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (Quest Will be Canceled If You Die, Quit or go far away)").create());
	        		}
	        		Location nl = NPCsSpawn.npcloc.get(le.getUniqueId());
	        		quested.put(p.getUniqueId(), nl);
	        		startloc.put(p.getUniqueId(), le.getLocation().clone());
	        		
    	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	                	Location sl = startloc.get(p.getUniqueId());
    	                	if(p.getWorld() != sl.getWorld() || p.getLocation().distance(sl) >180) {
    	                		QuestEnd(p,0);
    	                	}
    	                	for(Entity e : sl.getWorld().getNearbyEntities(sl.clone(), 3,3,3)) {
    	                		if(e == p && clearable.containsKey(p.getUniqueId())) {
    	                			QuestEnd(p,4);
    	                		}
    	                	}

    	                	
    	                }
    				}, 20, 20);
    	    		qt.put(p.getUniqueId(), task);
            	}
            	else {
    	        	p.setCooldown(Material.RAIL, 10);

	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": ��..���� ���԰� ����..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "(��Ŭ���� ����)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": They stole everything...").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "(RightClick To Accept)").create());
	        		}
                	asked.put(p.getUniqueId(), 1);
                	
                	int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	                	asked.remove(p.getUniqueId());
    	                }
    				}, 100);
    	    		qt.put(p.getUniqueId(), task);
            	}
            	
			
		}
	}

	public void QuestClear(InventoryOpenEvent d) 
	{
		if(d.getInventory().getLocation() != null && d.getPlayer() !=null) {
			Player p = (Player) d.getPlayer();
			Location l = d.getInventory().getLocation();
			if(!NPCsSpawn.pil.containsValue(l) || !quested.containsKey(p.getUniqueId()) || clearable.containsKey(p.getUniqueId())) {
				return;
			}
			if(NPCsSpawn.pil.get(quested.get(p.getUniqueId())).equals(l)) {
	    		p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
	    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	        		p.sendTitle(ChatColor.BOLD + "������ ����", ChatColor.BOLD + "(1/1)", 10, 20, 10);
	    		}
	    		else {
	        		p.sendTitle(ChatColor.BOLD + "Trader's Bag", ChatColor.BOLD + "(1/1)", 10, 20, 10);
	    		}
	    		clearable.put(p.getUniqueId(), 1);
			}
		}
	}

	public void Place(BlockPlaceEvent d) 
	{
		if(d.getBlock().getState() instanceof Chest) {
			Chest c = (Chest) d.getBlock().getState();
			Player p = (Player) d.getPlayer();
			NPCsSpawn.pil.keySet().forEach(k -> {
				if(c.getLocation().distance(k)<60) {
					d.setCancelled(true);
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "���ʱ��� ��ó���� â�� ��ġ �� �� �����ϴ�").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You can't Place Chest Near By PillagerOutPost").create());
	        		}
				}
			});
		}
	}

	public void Break(BlockBreakEvent d) 
	{
		if(d.getBlock().getState() instanceof Chest) {
			Chest c = (Chest) d.getBlock().getState();
			if(NPCsSpawn.pil.containsValue(c.getLocation())) {
				d.setCancelled(true);
			}
		}
	}
	
	public void GiveUp(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();
		if(quested.containsKey(p.getUniqueId())) {
    		QuestEnd(p,1);
		}
	}

	
	public void Failed(PlayerDeathEvent ev) 
	{
		Player p = ev.getEntity();
		if(quested.containsKey(p.getUniqueId())) {
    		QuestEnd(p,2);
		}
	}
	
	
	public void Reset(PluginDisableEvent ev) 
	{
		Bukkit.getOnlinePlayers().forEach(p -> {

			if(quested.containsKey(p.getUniqueId())) {
	    		QuestEnd(p,3);
			}
		});
	}



	
}
