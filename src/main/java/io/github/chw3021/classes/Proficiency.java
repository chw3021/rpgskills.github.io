package io.github.chw3021.classes;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import net.md_5.bungee.api.ChatColor;


public class Proficiency implements Serializable, Listener{
	/**
	 * 
	 */
	private transient static final long serialVersionUID = 6286917226085011558L;
	public final Table<UUID, Integer, Integer> proficiencyexp;
	public final Table<UUID, Integer, Integer> limit;

	private String path = new File("").getAbsolutePath();
	private static HashMap<UUID, Integer> playerclass;
	private static HashMap<UUID, Integer> proint = new HashMap<UUID, Integer>();

	Classgui Classgui = new Classgui();

	@EventHandler
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;

		Bukkit.getOnlinePlayers().forEach(p ->{
			if(getproexp(p) >= 29315 && getproexp(p) < 155015) {
		        proint.put(p.getUniqueId(), 1);
			}
			else if(getproexp(p) >= 155015) {
		        proint.put(p.getUniqueId(), 2);
			}
			else {
		        proint.put(p.getUniqueId(), 0);
			}
		});
	}
	

	@EventHandler
	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Classes"))
		{
			ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
			playerclass = cdata.playerclass;
			
			Player p = (Player) e.getWhoClicked();
			if(getproexp(p) >= 29315 && getproexp(p) < 155015) {
		        proint.put(p.getUniqueId(), 1);
			}
			else if(getproexp(p) >= 155015) {
		        proint.put(p.getUniqueId(), 2);
			}
			else {
		        proint.put(p.getUniqueId(), 0);
			}
			Classgui.LimitBreak(p);
			e.setCancelled(true);
			
		}
	}

	@EventHandler
	public void classinv(InventoryCloseEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).contains("Classes"))
		{
			ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
			playerclass = cdata.playerclass;
			
			Player p = (Player) e.getPlayer();
			if(getproexp(p) >= 29315 && getproexp(p) < 155015) {
		        proint.put(p.getUniqueId(), 1);
			}
			else if(getproexp(p) >= 155015) {
		        proint.put(p.getUniqueId(), 2);
			}
			else {
		        proint.put(p.getUniqueId(), 0);
			}
			Classgui.LimitBreak(p);
		}
	}

	@EventHandler
	public void classinv(InventoryOpenEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).contains("skills"))
		{
			ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
			playerclass = cdata.playerclass;
			
			Player p = (Player) e.getPlayer();
			if(getproexp(p) >= 29315 && getproexp(p) < 155015) {
		        proint.put(p.getUniqueId(), 1);
			}
			else if(getproexp(p) >= 155015) {
		        proint.put(p.getUniqueId(), 2);
			}
			else {
		        proint.put(p.getUniqueId(), 0);
			}
			Classgui.LimitBreak(p);
		}
	}
	@EventHandler
	public void nepreventer(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		Player p = ev.getPlayer();
		if(getproexp(p) >= 29315 && getproexp(p) < 155015) {
	        proint.put(p.getUniqueId(), 1);
		}
		else if(getproexp(p) >= 155015) {
	        proint.put(p.getUniqueId(), 2);
		}
		else {
	        proint.put(p.getUniqueId(), 0);
		}
		
	}
    // Can be used for saving
	
    public Proficiency(Table<UUID, Integer, Integer> proficiencyexp,Table<UUID, Integer, Integer> limit) {
    	this.proficiencyexp = proficiencyexp;
    	this.limit = limit;
    	}
    // Can be used for loading
    public Proficiency(Proficiency loadedData) {
    	this.proficiencyexp = loadedData.proficiencyexp;
    	this.limit = loadedData.limit;
   	}
 
    final static public int getproexp(Player p) {
        Table<UUID, Integer, Integer> proficiencyexp = getProficiencydata().proficiencyexp;
		if(proficiencyexp.contains(p.getUniqueId(), playerclass.get(p.getUniqueId()))) {
	        return proficiencyexp.get(p.getUniqueId(), playerclass.get(p.getUniqueId()));
		}
		else {
			return 0;
		}
    }
    final static public void prorank(Player p) {
        Table<UUID, Integer, Integer> proficiencyexp = getProficiencydata().proficiencyexp;
		if(proficiencyexp.containsColumn(playerclass.getOrDefault(p.getUniqueId() , -1))){
			Integer pc = playerclass.getOrDefault(p.getUniqueId() , -1);

			Map<UUID, Integer> allmap = proficiencyexp.columnMap().get(pc);
			List<Entry<UUID, Integer>> list = new ArrayList<>(allmap.entrySet());
			list.sort(Entry.comparingByValue());
			Collections.reverse(list);
			list.forEach(e ->{
				Integer rank = list.indexOf(e)+1;
				if(rank>10) {
					return;
				}
				String pn = Bukkit.getPlayer(e.getKey()).getName();
				Integer exp = e.getValue();
				p.sendMessage(ChatColor.AQUA +""+  rank + ". " + ChatColor.GOLD +pn + ": " + ChatColor.GREEN +exp);
			});
		}
    }
    
    final static public int getpro(Player p) {
		return proint.getOrDefault(p.getUniqueId(), 0);
    }
    
    
	public Proficiency saveData(String filePath) {
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
    public static Proficiency loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            Proficiency data = (Proficiency) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException | NullPointerException e ) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            Proficiency data = new Proficiency(HashBasedTable.create() ,HashBasedTable.create()).saveData(path +"/plugins/RPGskills/Proficiency.data");
            e.printStackTrace();
            return data;
        }
    }

	@EventHandler
	public void ProficiencyExp(PlayerExpChangeEvent e)
	{

		Player p = (Player) e.getPlayer();

        Table<UUID, Integer, Integer> proficiencyexp = getProficiencydata().proficiencyexp;
        Table<UUID, Integer, Integer> limit = getProficiencydata().limit;
		if(proficiencyexp.contains(p.getUniqueId(), playerclass.get(p.getUniqueId()))) {
			proficiencyexp.put(p.getUniqueId(), playerclass.get(p.getUniqueId()), e.getAmount() + proficiencyexp.get(p.getUniqueId(), playerclass.get(p.getUniqueId())));
		}
		else {
			proficiencyexp.put(p.getUniqueId(), playerclass.get(p.getUniqueId()), e.getAmount());
		}
		new Proficiency(proficiencyexp, limit).saveData(path +"/plugins/RPGskills/Proficiency.data");
		
		if(e.getAmount() <0) {
			if(getproexp(p) >= 29315 && getproexp(p) < 155015) {
		        proint.put(p.getUniqueId(), 1);
				limit.put(p.getUniqueId(), playerclass.get(p.getUniqueId()), 1);
			}
			else if(getproexp(p) >= 155015) {
		        proint.put(p.getUniqueId(), 2);
				limit.put(p.getUniqueId(), playerclass.get(p.getUniqueId()), 2);
			}
			else {
		        proint.put(p.getUniqueId(), 0);
				limit.put(p.getUniqueId(), playerclass.get(p.getUniqueId()), 0);
			}
			new Proficiency(proficiencyexp, limit).saveData(path +"/plugins/RPGskills/Proficiency.data");
			Classgui.LimitBreak(p);
			return;
		}
		if(!limit.contains(p.getUniqueId(), playerclass.get(p.getUniqueId()))) {
			limit.put(p.getUniqueId(), playerclass.get(p.getUniqueId()), 0);
			new Proficiency(proficiencyexp, limit).saveData(path +"/plugins/RPGskills/Proficiency.data");
		}
		if(getproexp(p) >= 29315 && getproexp(p) < 155015 && limit.get(p.getUniqueId(), playerclass.get(p.getUniqueId())) <1) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "첫번째 한계 돌파") , ChatColor.BOLD +(ChatColor.GOLD +"스킬들이 향상되었습니다"), 20, 60, 20);
			}
			else {
				p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "First Limit Broken") , ChatColor.BOLD +(ChatColor.GOLD +"Your skills have improved"), 20, 60, 20);
			}
			p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
			limit.put(p.getUniqueId(), playerclass.get(p.getUniqueId()), 1);
		    proint.put(p.getUniqueId(), 1);
			new Proficiency(proficiencyexp, limit).saveData(path +"/plugins/RPGskills/Proficiency.data");
		}
		if(getproexp(p) >= 155015 && limit.get(p.getUniqueId(), playerclass.get(p.getUniqueId())) <2) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "두번째 한계 돌파") , ChatColor.BOLD +(ChatColor.GOLD +"스킬들이 향상되었습니다"), 20, 60, 20);
			}
			else {
				p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "Second Limit Broken") , ChatColor.BOLD +(ChatColor.GOLD +"Your skills have improved"), 20, 60, 20);
			}
			p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
			limit.put(p.getUniqueId(), playerclass.get(p.getUniqueId()), 2);
	        proint.put(p.getUniqueId(), 2);
			new Proficiency(proficiencyexp, limit).saveData(path +"/plugins/RPGskills/Proficiency.data");
		}
		Classgui.LimitBreak(p);
	}
	
    
    final public static Proficiency getProficiencydata(){
        String path = new File("").getAbsolutePath();
        Proficiency data = new Proficiency(Proficiency.loadData(path +"/plugins/RPGskills/Proficiency.data"));
		return data;
	}
}