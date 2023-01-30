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

import io.github.chw3021.items.Elements;
import io.github.chw3021.items.Potions;
import io.github.chw3021.obtains.NPCsSpawn;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.obtains.TrophyLoc;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class JungleTempleQuest implements Listener {

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
    			p.sendTitle(ChatColor.GOLD + "����Ʈ �Ϸ�!",ChatColor.GOLD + "���� ��� ����ǰ�� ȹ���߽��ϴ�!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "������ڿ� �ŷ��� �� �� �ֽ��ϴ�").create());
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
				if(TrophyLoc.getLocsdata().Locs.containsEntry(p.getUniqueId(), NPCsSpawn.npcloc.get(le.getUniqueId()))) {
					
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": �����մϴ�.").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Thank you.").create());
	        		}
	        		List<MerchantRecipe> mrl = new ArrayList<MerchantRecipe>();

	        		ArrayList<ItemStack> helia = new ArrayList<>();
	        		helia.add(new ItemStack(Material.EMERALD,16));
	        		helia.add(new ItemStack(Material.GOLD_INGOT,16));
	        		
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
	        		
	        		MerchantRecipe mr3 = new MerchantRecipe(Elements.getpor(12,p), 1,64,true);
	        		mr3.setIngredients(in1);
	        		mrl.add(mr3);

	        		Merchant mi = Bukkit.createMerchant(le.getCustomName());
	        		mi.setRecipes(mrl);
	        		p.openMerchant(mi, true);
	        		
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
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": ����! ����� �������ε� �ƹ����� ���͵��� ������ �˴� ������ ����̾�.").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": ��ó ���͵����׼� ������ �������ټ� �ְڳ�?").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (���, ����, ��Ŭ���� �Ǵ� �ʹ� �ָ� ���� ����Ʈ�� ��ҵ˴ϴ�.)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Thank you! I'm researching the temple, but monsters seem to have taken all the Relics.").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Can you bring Relics from nearby monsters??").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (Quest Will be Canceled If You Die, Quit, RightClick or go far away)").create());
	        		}
	        		quested.put(p.getUniqueId(), NPCsSpawn.npcloc.get(le.getUniqueId()));
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
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": ���⵵ ����...���⵵ ����..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "(��Ŭ���� ����)").create());
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
		        		p.sendTitle(ChatColor.BOLD + "����", ChatColor.BOLD + "("+ clearable.get(p.getUniqueId())+ "/10)", 10, 20, 10);
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
