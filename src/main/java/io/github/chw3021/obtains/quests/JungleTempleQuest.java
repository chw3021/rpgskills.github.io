package io.github.chw3021.obtains.quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
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
import io.github.chw3021.obtains.NPCLoc;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.obtains.TrophyLoc;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class JungleTempleQuest {

	private HashMap<UUID, Integer> asked = new HashMap<UUID, Integer>();
	private HashMap<UUID, Location> quested = new HashMap<UUID, Location>();
	private HashMap<UUID, Location> startloc = new HashMap<UUID, Location>();
	private HashMap<UUID, Integer> clearable = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> qt = new HashMap<UUID, Integer>();


	private static final JungleTempleQuest instance = new JungleTempleQuest ();
	public static JungleTempleQuest getInstance()
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
    			p.sendTitle(ChatColor.GOLD + "퀘스트 완료!",ChatColor.GOLD + "정글 사원 전리품을 획득했습니다!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "고고학자와 거래를 할 수 있습니다").create());
		    }
    		else {
    			p.sendTitle(ChatColor.GOLD + "Complete Quest!",ChatColor.GOLD + "You Just Obtained Ocean Monument Trophy!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "Able To Trade With Archaeologist").create());
    		}
    		Obtained.saver(p, 7, 1);
    		TrophyLoc.saver(p, quested.get(p.getUniqueId()));
        	Elements.give(Material.LAPIS_LAZULI, 5, p);
        	Elements.give(Material.EMERALD, 5, p);
        	Elements.give(Elements.getel(12, p),5, p);
			p.giveExp(150);
			Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,150));
		}
		if(quested.containsKey(p.getUniqueId())) {
			quested.remove(p.getUniqueId());
		}
	}


	
	public void QuestStart(PlayerInteractEntityEvent d) 
	{	
		if(d.getRightClicked().hasMetadata("obnpc") && d.getRightClicked().hasMetadata("junglepyramid")) {
			d.setCancelled(true);
				Villager le = (Villager)d.getRightClicked();
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
	        		ItemStack stel = Elements.getstel(12, p);
	        		stel.setAmount(64);
	        		helia.add(stel);
	        		
	        		ArrayList<ItemStack> in1 = new ArrayList<>();
	        		in1.add(new ItemStack(Material.EMERALD,6));
	        		in1.add(new ItemStack(Material.LAPIS_LAZULI,6));
	        		
	        		ArrayList<ItemStack> poia = new ArrayList<>();
	        		poia.add(new ItemStack(Material.EMERALD,5));
	        		poia.add(new ItemStack(Material.LAPIS_LAZULI,5));

	        		ArrayList<ItemStack> po = new ArrayList<>();
	        		po.add(new ItemStack(Material.EMERALD,1));
	        		
	        		MerchantRecipe mr1 = new MerchantRecipe(Potions.get(6, p), 1,64,true);
	        		mr1.setIngredients(po);
	        		mrl.add(mr1);
	        		
	        		MerchantRecipe mr2 = new MerchantRecipe(Elements.getel(12, p), 1,64,true);
	        		mr2.setIngredients(poia);
	        		mrl.add(mr2);

	        		for(int i = 5; i<=14;i++) {
	        			if(i==12||i==13) {
	        				continue;
	        			}
		        		MerchantRecipe storm = new MerchantRecipe(Elements.getstel(i, p), 1,64,true);
		        		storm.setIngredients(helia);
		        		mrl.add(storm);
	        		}
	        		
	        		
	        		
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
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 고맙네! 사원을 조사중인데 아무래도 몬스터들이 유물을 죄다 가져간 모양이야.").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 근처 몬스터들한테서 유물을 가져와줄수 있겠나?").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (사망, 종료, 우클릭시 또는 너무 멀리 가면 퀘스트가 취소됩니다.)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Thank you! I'm researching the temple, but monsters seem to have taken all the Relics.").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Can you bring Relics from nearby monsters??").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (Quest Will be Canceled If You Die, Quit, RightClick or go far away)").create());
	        		}
	        		quested.put(p.getUniqueId(), NPCLoc.npcloc.get(le.getUniqueId()));
	        		startloc.put(p.getUniqueId(), le.getLocation().clone());

	        		
    	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	                	Location sl = startloc.get(p.getUniqueId());
    	                	if(p.getWorld() != sl.getWorld() || p.getLocation().distance(sl) >120) {
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
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 여기도 없고...저기도 없고..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "(우클릭시 수락)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Neither here nor there..").create());
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
		if(d.getEntity().hasMetadata("wild")) {
			LivingEntity le = d.getEntity();
			if(le.getKiller() != null) {
				Player p = le.getKiller();
				if(quested.containsKey(p.getUniqueId())) {
	        		p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
	        		clearable.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
	        		clearable.putIfAbsent(p.getUniqueId(), 1);
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        		p.sendTitle(ChatColor.BOLD + "유물", ChatColor.BOLD + "("+ clearable.get(p.getUniqueId())+ "/10)", 10, 20, 10);
				    }
	        		else {
		        		p.sendTitle(ChatColor.BOLD + "Relic", ChatColor.BOLD + "("+ clearable.get(p.getUniqueId())+ "/10)", 10, 20, 10);
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