package io.github.chw3021.obtains.quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import io.github.chw3021.items.Elements;
import io.github.chw3021.items.Potions;
import io.github.chw3021.items.armors.Boots;
import io.github.chw3021.items.armors.Chestplate;
import io.github.chw3021.items.armors.Helmet;
import io.github.chw3021.items.armors.Leggings;
import io.github.chw3021.obtains.NPCLoc;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.obtains.TrophyLoc;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class DesertPyramidQuest implements Quest{

	private HashMap<UUID, Integer> asked = new HashMap<UUID, Integer>();
	private HashMap<UUID, Location> quested = new HashMap<UUID, Location>();
	private HashMap<UUID, Location> startloc = new HashMap<UUID, Location>();
	private HashMap<UUID, Integer> clearable = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> qt = new HashMap<UUID, Integer>();


	private static final DesertPyramidQuest instance = new DesertPyramidQuest ();
	public static DesertPyramidQuest getInstance()
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
    			p.sendTitle(ChatColor.GOLD + "퀘스트 완료!",ChatColor.GOLD + "사막 피라미드 전리품을 획득했습니다!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "탈출한 실험체와 거래를 할 수 있습니다").create());
		    }
    		else {
    			p.sendTitle(ChatColor.GOLD + "Complete Quest!",ChatColor.GOLD + "You Just Obtained Ocean Desert Pyramid!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "Able To Trade With Escaped Specimen").create());
    		}
    		Obtained.saver(p, "desert", 1);
    		TrophyLoc.saver(p, quested.get(p.getUniqueId()));
        	Elements.give(Material.LAPIS_LAZULI, 5, p);
        	Elements.give(Material.EMERALD, 5, p);
        	Elements.give(Elements.getel(9, p),5, p);
        	Elements.give(Elements.getel(10, p),5, p);
			p.giveExp(100);
			Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,100));
		}
		if(quested.containsKey(p.getUniqueId())) {
			quested.remove(p.getUniqueId());
		}
	}


	
	public void QuestStart(PlayerInteractEntityEvent d) 
	{	
		if(d.getRightClicked().hasMetadata("obnpc") && d.getRightClicked().hasMetadata("desertpyramid")) {
			d.setCancelled(true);
				Husk le = (Husk)d.getRightClicked();
				Player p = (Player) d.getPlayer();
				if(p.hasCooldown(Material.RAIL)) {
					return;
				}
        		p.setCooldown(Material.RAIL, 10);
				if(quested.containsKey(p.getUniqueId())) {
		    		QuestEnd(p,3);
					return;
				}
				if(TrophyLoc.getLocsdata().Locs.containsEntry(p.getUniqueId(), NPCLoc.npcloc.get(le.getUniqueId()))) {
					
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 감사합니다.").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Thank you.").create());
	        		}
	        		List<MerchantRecipe> mrl = new ArrayList<MerchantRecipe>();

	        		ArrayList<ItemStack> helia = new ArrayList<>();
	        		helia.add(new ItemStack(Material.EMERALD_BLOCK,3));
	        		helia.add(new ItemStack(Material.GOLD_BLOCK,3));
	        		
	        		ArrayList<ItemStack> poia = new ArrayList<>();
	        		poia.add(new ItemStack(Material.EMERALD,5));
	        		poia.add(new ItemStack(Material.LAPIS_LAZULI,5));

	        		ArrayList<ItemStack> aa = new ArrayList<>();
	        		aa.add(new ItemStack(Material.EMERALD,3));

	        		ArrayList<ItemStack> poi = new ArrayList<>();
	        		poi.add(new ItemStack(Material.EMERALD,1));
	        		MerchantRecipe mr1 = new MerchantRecipe(Potions.get(4, p), 1,64,true);
	        		mr1.setIngredients(poi);
	        		mrl.add(mr1);
	        		
	        		MerchantRecipe mr2 = new MerchantRecipe(Elements.getel(9, p), 1,64,true);
	        		mr2.setIngredients(poia);
	        		mrl.add(mr2);
	        		MerchantRecipe mr23 = new MerchantRecipe(Elements.getel(10, p), 1,64,true);
	        		mr23.setIngredients(poia);
	        		mrl.add(mr23);

	        		
	        		MerchantRecipe mr3 = new MerchantRecipe(Chestplate.get(6, p), 1,64,true);
	        		mr3.setIngredients(helia);
	        		mrl.add(mr3);
	        		MerchantRecipe mr5 = new MerchantRecipe(Helmet.get(6, p), 1,64,true);
	        		mr5.setIngredients(helia);
	        		mrl.add(mr5);
	        		MerchantRecipe mr6 = new MerchantRecipe(Boots.get(6, p), 1,64,true);
	        		mr6.setIngredients(helia);
	        		mrl.add(mr6);
	        		MerchantRecipe mr7 = new MerchantRecipe(Leggings.get(6, p), 1,64,true);
	        		mr7.setIngredients(helia);
	        		mrl.add(mr7);
	        		
	        		

	        		Merchant mi = Bukkit.createMerchant(le.getCustomName());
	        		mi.setRecipes(mrl);
	        		p.openMerchant(mi, true);
	        		
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
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 감사합니다. 저는 온갖 괴랄한 실험을 당하면서 주변에 보이는 몬스터들처럼 되기전에 탈출을 성공했었습니다.").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 근데 제가 탈출 과정중 손상을 입어 부품이 몇개 사라졌습니다..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 주변 몬스터들한테서 부품을 몇개 구해다 주실수 있나요?").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (사망, 종료, 우클릭시 또는 너무 멀리 가면 퀘스트가 취소됩니다.)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Thanks. I succeeded in escaping before I became like the monsters around me through all kinds of painful experiments.").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": But I was damaged during the escape process and some parts disappeared.").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Could you get some parts from the monsters around you?").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (Quest Will be Canceled If You Die, Quit, RightClick or go far away)").create());
	        		}
	        		quested.put(p.getUniqueId(), NPCLoc.npcloc.get(le.getUniqueId()));
	        		startloc.put(p.getUniqueId(), le.getLocation().clone());

	        		
    	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	                	Location sl = startloc.get(p.getUniqueId());
    	                	if(p.getWorld() != sl.getWorld() || p.getLocation().distance(sl) >150) {
    	                		QuestEnd(p,0);
    	                	}
    	                	for(Entity e : sl.getWorld().getNearbyEntities(sl.clone(), 3,3,3)) {
    	                		if(e == p && clearable.getOrDefault(p.getUniqueId(), 0) >=10) {
    	                			QuestEnd(p,4);
    	                		}
    	                	}
    	                	
    	                }
    				}, 0, 20);
    	    		qt.put(p.getUniqueId(), task);
            	}
            	else {
    	        	p.setCooldown(Material.RAIL, 10);

	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 부품이 부족해...").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "(우클릭시 수락)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Not Enough Part..").create());
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
		if(d.getEntity().hasMetadata("hyper")) {
			LivingEntity le = d.getEntity();
			if(le.getKiller() != null) {
				Player p = le.getKiller();
				if(quested.containsKey(p.getUniqueId())) {
		    		Random random=new Random();
		        	int ri = random.nextInt(90);
		        	if(ri <= 80) {
		        		p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
		        		clearable.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
		        		clearable.putIfAbsent(p.getUniqueId(), 1);
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			        		p.sendTitle(ChatColor.BOLD + "부품", ChatColor.BOLD + "("+ clearable.get(p.getUniqueId())+ "/10)", 10, 20, 10);
					    }
		        		else {
			        		p.sendTitle(ChatColor.BOLD + "Parts", ChatColor.BOLD + "("+ clearable.get(p.getUniqueId())+ "/10)", 10, 20, 10);
		        		}
		        	}
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