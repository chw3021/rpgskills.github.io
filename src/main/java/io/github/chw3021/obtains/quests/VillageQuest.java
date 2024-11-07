package io.github.chw3021.obtains.quests;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.raid.RaidFinishEvent;

import io.github.chw3021.items.Elements;
import io.github.chw3021.obtains.Obtained;

public class VillageQuest implements Listener{
	
	@EventHandler
	public void Clear(RaidFinishEvent e) {
		
		e.getWinners().forEach(p ->{

			p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.sendTitle(ChatColor.BOLD + (ChatColor.GOLD + "퀘스트 완료!"),ChatColor.BOLD + (ChatColor.GOLD + "마을 전리품을 획득했습니다!"),15,35,15);
		    }
			else {
				p.sendTitle(ChatColor.GOLD + "Complete Quest!",ChatColor.GOLD + "You Just Obtained Village Trophy!",15,35,15);
			}
			Obtained.saver(p, 12, 1);
	    	Elements.give(Material.LAPIS_LAZULI, 15, p);
	    	Elements.give(Material.EMERALD, 15, p);
	    	Elements.give(Material.GOLD_INGOT, 15, p);
			p.giveExp(200);
			Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,200));
		});
	
	}
}
