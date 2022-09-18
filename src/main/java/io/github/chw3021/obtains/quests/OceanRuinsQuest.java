package io.github.chw3021.obtains.quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.StructureType;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
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
import io.github.chw3021.items.armors.Boots;
import io.github.chw3021.items.armors.Chestplate;
import io.github.chw3021.items.armors.Helmet;
import io.github.chw3021.items.armors.Leggings;
import io.github.chw3021.obtains.NPCsSpawn;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.obtains.TrophyLoc;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class OceanRuinsQuest implements Listener {

	private HashMap<UUID, Integer> asked = new HashMap<UUID, Integer>();
	private HashMap<UUID, Location> quested = new HashMap<UUID, Location>();
	private HashMap<UUID, Location> startloc = new HashMap<UUID, Location>();
	private HashMap<UUID, Integer> clearable = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> qt = new HashMap<UUID, Integer>();
	private static Multimap<String, UUID> qmobs = ArrayListMultimap.create();
	private HashMap<String, Integer> qmobskill = new HashMap<String, Integer>();

	private HashMap<String, BossBar> qbar = new HashMap<String, BossBar>();
	private HashMap<String, Integer> qbart = new HashMap<String, Integer>();
	private ItemStack map = null;

	private static final OceanRuinsQuest instance = new OceanRuinsQuest ();
	public static OceanRuinsQuest getInstance()
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

		if(qbart.containsKey(p.getName())) {
			Bukkit.getServer().getScheduler().cancelTask(qbart.get(p.getName()));
			qbart.remove(p.getName());
		}
		if(qbar.get(p.getName()) != null) {
			qbar.get(p.getName()).removeAll();
			qbar.get(p.getName()).setVisible(false);
		}
		if(qmobs.containsKey(p.getName())) {
			qmobs.get(p.getName()).forEach(re -> {
				if(Bukkit.getEntity(re) != null) {
					Bukkit.getEntity(re).remove();
	    		}
			});
			qmobs.removeAll(p.getName());
		}
		qmobskill.remove(p.getName());
		
		
		if(factor ==0) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendMessage("퀘스트가 취소되었습니다(거리)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Too Far Away)");
    		}
		}
		else if (factor == 2) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendMessage("퀘스트가 취소되었습니다(사망)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Death)");
    		}
		}
		else if (factor == 3) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendMessage("퀘스트가 취소되었습니다(포기)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Given Up)");
    		}
		}
		else if (factor == 4) {
    		p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendTitle(ChatColor.GOLD + "퀘스트 완료!",ChatColor.GOLD + "해저 폐허 전리품을 획득했습니다!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "아홀로틀 요원과 거래를 할 수 있습니다").create());
		    }
    		else {
    			p.sendTitle(ChatColor.GOLD + "Complete Quest!",ChatColor.GOLD + "You Just Obtained OceanRuins Trophy!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "Able To Trade With Agent Axolotl").create());
    		}
    		Obtained.saver(p, "ruin", 1);
    		TrophyLoc.saver(p, quested.get(p.getUniqueId()));
        	Elements.give(Material.LAPIS_LAZULI, 5, p);
        	Elements.give(Material.EMERALD, 5, p);
        	Elements.give(Elements.getel(7, p),5, p);
			p.giveExp(100);
			Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,100));
		}
		if(quested.containsKey(p.getUniqueId())) {
			quested.remove(p.getUniqueId());
		}
	}


	
	public void QuestStart(PlayerInteractEntityEvent d) 
	{	
		if(d.getRightClicked().hasMetadata("obnpc") && d.getRightClicked().hasMetadata("oceanruin")) {
			d.setCancelled(true);
				Axolotl le = (Axolotl)d.getRightClicked();
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
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 고마워요!").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Have A Nice Day!").create());
	        		}
	        		
	        		List<MerchantRecipe> mrl = new ArrayList<MerchantRecipe>();

	        		ArrayList<ItemStack> helia = new ArrayList<>();
	        		helia.add(new ItemStack(Material.EMERALD,15));
	        		helia.add(new ItemStack(Material.GOLD_INGOT,15));
	        		ArrayList<ItemStack> poia = new ArrayList<>();
	        		poia.add(new ItemStack(Material.EMERALD,5));
	        		poia.add(new ItemStack(Material.LAPIS_LAZULI,5));
	        		ArrayList<ItemStack> aa = new ArrayList<>();
	        		aa.add(new ItemStack(Material.EMERALD,3));

	        		ArrayList<ItemStack> poi = new ArrayList<>();
	        		poi.add(new ItemStack(Material.EMERALD,1));
	        		MerchantRecipe mr1 = new MerchantRecipe(Potions.get(2, p), 1,64,true);
	        		mr1.setIngredients(poi);
	        		mrl.add(mr1);
	        		
	        		MerchantRecipe mr2 = new MerchantRecipe(Elements.getel(7, p), 1,64,true);
	        		mr2.setIngredients(poia);
	        		mrl.add(mr2);
	        		
	        		MerchantRecipe mr3 = new MerchantRecipe(Chestplate.get(3, p), 1,64,true);
	        		mr3.setIngredients(helia);
	        		mrl.add(mr3);
	        		MerchantRecipe mr5 = new MerchantRecipe(Helmet.get(3, p), 1,64,true);
	        		mr5.setIngredients(helia);
	        		mrl.add(mr5);
	        		MerchantRecipe mr6 = new MerchantRecipe(Boots.get(3, p), 1,64,true);
	        		mr6.setIngredients(helia);
	        		mrl.add(mr6);
	        		MerchantRecipe mr7 = new MerchantRecipe(Leggings.get(3, p), 1,64,true);
	        		mr7.setIngredients(helia);
	        		mrl.add(mr7);

	        		if(map == null) {
		        		map = Bukkit.createExplorerMap(p.getWorld(), p.getLocation(),  StructureType.BURIED_TREASURE,200,true);
		        		ItemMeta mapm = map.getItemMeta();
		        		

		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        			mapm.setDisplayName(ChatColor.ITALIC+"보물 지도");
					    }
		        		else {
		        			mapm.setDisplayName(ChatColor.ITALIC+"Treasure Map");
		        		}
		        		map.setItemMeta(mapm);
		        		MerchantRecipe mr4 = new MerchantRecipe(map, 1,64,true);
		        		mr4.setIngredients(aa);
		        		mrl.add(mr4);
		        		
	        		}
	        		else {
		        		MerchantRecipe mr4 = new MerchantRecipe(map, 1,64,true);
		        		mr4.setIngredients(aa);
		        		mrl.add(mr4);
	        		}
	        		Merchant mi = Bukkit.createMerchant(le.getCustomName());
	        		mi.setRecipes(mrl);
	        		p.openMerchant(mi, true);
	        		
	        		p.setCooldown(Material.RAIL, 3);
					return;
				}
				if(Party.hasParty(p)) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "파티에 속한 상태로는 진행이 불가능합니다").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You should leave Your Party").create());
	        		}
					return;
				}
				if(p.getInventory().firstEmpty() == -1) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "인벤토리에 빈칸이 최소 한칸은 있어야 합니다").create());
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
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 감사합니다.. 랜턴맨 처지 작업을 해야되는데 밀린 서류가 많아서요..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (사망, 종료, 우클릭시 또는 너무 멀리 가면 퀘스트가 취소됩니다.)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Thank You.. I have to get rid of LanternMans").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": But I have a lot of documents to catch up with..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (Quest Will be Canceled If You Die, Quit, RightClick or go far away)").create());
	        		}
	        		quested.put(p.getUniqueId(), NPCsSpawn.npcloc.get(le.getUniqueId()));
	        		startloc.put(p.getUniqueId(), le.getLocation().clone());

    	    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), p.getName()+"oceanruinsquest"),"Killed Monsters: " + qmobskill.get(p.getName())  + "/" + 20, BarColor.BLUE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
    	            newbar.setVisible(true);
    	            qbar.put(p.getName(), newbar);
    	    		int btask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	            		qbar.get(p.getName()).setProgress((double)qmobskill.getOrDefault(p.getName(),0) / 15d);
    		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        	            		qbar.get(p.getName()).setTitle("처치 몬스터 수: " + qmobskill.getOrDefault(p.getName(),0) + "/" + 15);
    					    }
    		        		else {
        	            		qbar.get(p.getName()).setTitle("Killed Monsters: " + qmobskill.getOrDefault(p.getName(),0)  + "/" + 15);
    		        		}
    	            		qbar.get(p.getName()).addPlayer(p);
    	                }
    				}, 0, 5);
    	    		qbart.put(p.getName(), btask);

	        		
    	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	                	//MobsSpawn(p,p.getLocation());
    	                	Location sl = startloc.get(p.getUniqueId());
    	                	if(p.getWorld() != sl.getWorld() || p.getLocation().distance(sl) >70) {
    	                		QuestEnd(p,0);
    	                	}
    	                	
    	                }
    				}, 0, 20);
    	    		qt.put(p.getUniqueId(), task);
            	}
            	else {
    	        	p.setCooldown(Material.RAIL, 10);

	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 바쁘다 바빠..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "(우클릭시 수락)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Too much to do..").create());
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

	public void QuestClear(EntityDeathEvent d) 
	{
		/*if(d.getEntity().hasMetadata("oceanruinsquest")) {
			Player p = Bukkit.getPlayerExact(d.getEntity().getMetadata("oceanruinsquest").get(0).asString());
			qmobskill.computeIfPresent(p.getName(), (k,v) -> v+1);
			qmobskill.putIfAbsent(p.getName(), 1);
			if(qmobskill.getOrDefault(p.getName(), 0) >= 25) {
				QuestEnd(p,4);
			}
		}*/
		if(d.getEntity().hasMetadata("ocean") && d.getEntity().getKiller() != null) {
			Player p = d.getEntity().getKiller();
			if(quested.containsKey(p.getUniqueId())&& (d.getEntity().getName().contains("LanternMan") ||d.getEntity().getName().contains("랜턴맨")) ) {
        		p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
    			qmobskill.computeIfPresent(p.getName(), (k,v) -> v+1);
    			qmobskill.putIfAbsent(p.getName(), 1);
    			if(qmobskill.getOrDefault(p.getName(), 0) >= 15) {
    				QuestEnd(p,4);
    			}
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
