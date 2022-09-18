package io.github.chw3021.classes.witherist;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;import io.github.chw3021.items.ScrollPoint;


public class WitSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -920821585426516464L;
	public final HashMap<UUID, Integer> WitherScythe;
	public final HashMap<UUID, Integer> Hover;
	public final HashMap<UUID, Integer> WitherSkull;
	public final HashMap<UUID, Integer> Curse;
	public final HashMap<UUID, Integer> ReapingHook;
	public final HashMap<UUID, Integer> Roses;
	public final HashMap<UUID, Integer> Witherize;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public WitSkillsData(HashMap<UUID, Integer> WitherScythe, HashMap<UUID, Integer> Hover, HashMap<UUID, Integer> WitherSkull, HashMap<UUID, Integer> Curse, HashMap<UUID, Integer> ReapingHook, HashMap<UUID, Integer> Roses, HashMap<UUID, Integer> Witherize, HashMap<UUID, Integer> SkillPoints) {
    	this.WitherScythe = WitherScythe;
    	this.Hover = Hover;
    	this.WitherSkull = WitherSkull;
    	this.Curse = Curse;
    	this.ReapingHook = ReapingHook;
    	this.Roses = Roses;
    	this.Witherize = Witherize;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public WitSkillsData(WitSkillsData loadedData) {
    	this.WitherScythe = loadedData.WitherScythe;
    	this.Hover = loadedData.Hover;
    	this.WitherSkull = loadedData.WitherSkull;
    	this.Curse = loadedData.Curse;
    	this.ReapingHook = loadedData.ReapingHook;
    	this.Roses = loadedData.Roses;
    	this.Witherize = loadedData.Witherize;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public WitSkillsData saveData(String filePath) {
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
    public static WitSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            WitSkillsData data = (WitSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException| NullPointerException e) {
            // TODO Auto-generated catch block

            String path = new File("").getAbsolutePath();
        	
            WitSkillsData data = new WitSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
            
            e.printStackTrace();
            return data;
        }
    }


	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> WitherScythe = getWitmandata().WitherScythe;
		HashMap<UUID, Integer> Hover = getWitmandata().Hover;
		HashMap<UUID, Integer> WitherSkull = getWitmandata().WitherSkull;
		HashMap<UUID, Integer> Curse = getWitmandata().Curse;
		HashMap<UUID, Integer> ReapingHook = getWitmandata().ReapingHook;
		HashMap<UUID, Integer> Roses = getWitmandata().Roses;
		HashMap<UUID, Integer> Witherize = getWitmandata().Witherize;
		HashMap<UUID, Integer> SkillPoints = getWitmandata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");


	}


	@EventHandler
	public void Witskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Witskills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getWitmandata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> WitherScythe = getWitmandata().WitherScythe;
					HashMap<UUID, Integer> Hover = getWitmandata().Hover;
					HashMap<UUID, Integer> WitherSkull = getWitmandata().WitherSkull;
					HashMap<UUID, Integer> Curse = getWitmandata().Curse;
					HashMap<UUID, Integer> ReapingHook = getWitmandata().ReapingHook;
					HashMap<UUID, Integer> Roses = getWitmandata().Roses;
					HashMap<UUID, Integer> Witherize = getWitmandata().Witherize;
					HashMap<UUID, Integer> SkillPoints = getWitmandata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					WitherScythe.put(p.getUniqueId(), 0);
					Hover.put(p.getUniqueId(), 0);
					WitherSkull.put(p.getUniqueId(), 0);
					Curse.put(p.getUniqueId(), 0);
					ReapingHook.put(p.getUniqueId(), 0);
					Roses.put(p.getUniqueId(), 0);
					Witherize.put(p.getUniqueId(), 0);
					new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");

					WitSkillsGui wsg = new WitSkillsGui();
					wsg.WitSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> WitherScythe = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Hover = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> WitherSkull = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Curse = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> ReapingHook = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Roses = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Witherize = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				WitherScythe.put(p.getUniqueId(), 0);
				Hover.put(p.getUniqueId(), 0);
				WitherSkull.put(p.getUniqueId(), 0);
				Curse.put(p.getUniqueId(), 0);
				ReapingHook.put(p.getUniqueId(), 0);
				Roses.put(p.getUniqueId(), 0);
				Witherize.put(p.getUniqueId(), 0);
				new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");

				WitSkillsGui wsg = new WitSkillsGui();
				wsg.WitSkillsinv(p);
			}
		}
	}
	
	
	@EventHandler
	public void Witskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		
		HashMap<UUID, Integer> WitherScythe = getWitmandata().WitherScythe;
		HashMap<UUID, Integer> Hover = getWitmandata().Hover;
		HashMap<UUID, Integer> WitherSkull = getWitmandata().WitherSkull;
		HashMap<UUID, Integer> Curse = getWitmandata().Curse;
		HashMap<UUID, Integer> ReapingHook = getWitmandata().ReapingHook;
		HashMap<UUID, Integer> Roses = getWitmandata().Roses;
		HashMap<UUID, Integer> Witherize = getWitmandata().Witherize;
		HashMap<UUID, Integer> SkillPoints = getWitmandata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
		        
			}
			else
			{	

				WitherScythe.put(p.getUniqueId(), 0);
				Hover.put(p.getUniqueId(), 0);
				WitherSkull.put(p.getUniqueId(), 0);
				Curse.put(p.getUniqueId(), 0);
				Roses.put(p.getUniqueId(), 0);
				ReapingHook.put(p.getUniqueId(), 0);
				Witherize.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Witskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Witskills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				WitSkillsGui wsg = new WitSkillsGui();
				HashMap<UUID, Integer> WitherScythe = getWitmandata().WitherScythe;
				HashMap<UUID, Integer> Hover = getWitmandata().Hover;
				HashMap<UUID, Integer> WitherSkull = getWitmandata().WitherSkull;
				HashMap<UUID, Integer> Curse = getWitmandata().Curse;
				HashMap<UUID, Integer> ReapingHook = getWitmandata().ReapingHook;
				HashMap<UUID, Integer> Roses = getWitmandata().Roses;
				HashMap<UUID, Integer> Witherize = getWitmandata().Witherize;
				HashMap<UUID, Integer> SkillPoints = getWitmandata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "WitherScythe":
					case "구속의낫":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && WitherScythe.get(p.getUniqueId())<50){
								WitherScythe.put(p.getUniqueId(), WitherScythe.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(WitherScythe.get(p.getUniqueId()) >= 1){
								WitherScythe.put(p.getUniqueId(), WitherScythe.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(WitherScythe.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(WitherScythe.get(p.getUniqueId())<50){
										final int a = 50 - WitherScythe.get(p.getUniqueId());
										WitherScythe.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									WitherScythe.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+WitherScythe.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(WitherScythe.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+WitherScythe.get(p.getUniqueId()));
								WitherScythe.put(p.getUniqueId(), 0);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						break;		}
						
					case "Hover":
					case "부유":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Hover.get(p.getUniqueId()) < 1){
								Hover.put(p.getUniqueId(), Hover.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Hover.get(p.getUniqueId()) >= 1){
								Hover.put(p.getUniqueId(), Hover.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Hover.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Hover.get(p.getUniqueId()) < 1) {
										Hover.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Hover.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Hover.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Hover.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Hover.get(p.getUniqueId()));
								Hover.put(p.getUniqueId(), 0);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						break;		}		
					case "WitherSkull":
					case "위더해골":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && WitherSkull.get(p.getUniqueId())<50){
								WitherSkull.put(p.getUniqueId(), WitherSkull.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(WitherSkull.get(p.getUniqueId()) >= 1){
								WitherSkull.put(p.getUniqueId(), WitherSkull.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(WitherSkull.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(WitherSkull.get(p.getUniqueId())<50){
										final int a = 50 - WitherSkull.get(p.getUniqueId());
										WitherSkull.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									WitherSkull.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+WitherSkull.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(WitherSkull.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+WitherSkull.get(p.getUniqueId()));
								WitherSkull.put(p.getUniqueId(), 0);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						break;		}
					case "ReapingHook":
					case "어둠의갈고리":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ReapingHook.get(p.getUniqueId()) < 1){
								ReapingHook.put(p.getUniqueId(), ReapingHook.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ReapingHook.get(p.getUniqueId()) >= 1){
								ReapingHook.put(p.getUniqueId(), ReapingHook.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ReapingHook.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(ReapingHook.get(p.getUniqueId()) < 1) {
										ReapingHook.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									ReapingHook.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ReapingHook.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ReapingHook.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ReapingHook.get(p.getUniqueId()));
								ReapingHook.put(p.getUniqueId(), 0);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						break;		}
					case "Roses":
					case "위더장미":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Roses.get(p.getUniqueId())<50){
								Roses.put(p.getUniqueId(), Roses.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Roses.get(p.getUniqueId()) >= 1){
								Roses.put(p.getUniqueId(), Roses.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Roses.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Roses.get(p.getUniqueId())<50){
										final int a = 50 - Roses.get(p.getUniqueId());
										Roses.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									Roses.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Roses.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Roses.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Roses.get(p.getUniqueId()));
								Roses.put(p.getUniqueId(), 0);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						break;		}				
					case "Witherize":
					case "위더화":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Witherize.put(p.getUniqueId(), Witherize.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Witherize.get(p.getUniqueId()) >= 1){
								Witherize.put(p.getUniqueId(), Witherize.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Witherize.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Witherize.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Witherize.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Witherize.get(p.getUniqueId()));
								Witherize.put(p.getUniqueId(), 0);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						break;		}
					case "Curse":
					case "저주":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Curse.get(p.getUniqueId())<50){
								Curse.put(p.getUniqueId(), Curse.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Curse.get(p.getUniqueId()) >= 1){
								Curse.put(p.getUniqueId(), Curse.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Curse.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Curse.get(p.getUniqueId())<50){
										final int a = 50 - Curse.get(p.getUniqueId());
										Curse.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									Curse.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Curse.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Curse.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Curse.get(p.getUniqueId()));
								Curse.put(p.getUniqueId(), 0);
								new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
						        wsg.WitSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						WitherScythe.put(p.getUniqueId(), 0);
						Hover.put(p.getUniqueId(), 0);
						WitherSkull.put(p.getUniqueId(), 0);
						Curse.put(p.getUniqueId(), 0);
						Roses.put(p.getUniqueId(), 0);
						ReapingHook.put(p.getUniqueId(), 0);
						WitherScythe.put(p.getUniqueId(), 0);
						Witherize.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new WitSkillsData(WitherScythe, Hover, WitherSkull, Curse, ReapingHook, Roses, Witherize, SkillPoints).saveData(path +"/plugins/RPGskills/WitSkillsData.data");
				        wsg.WitSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public static WitSkillsData getWitmandata(){
        String path = new File("").getAbsolutePath();
        WitSkillsData data = new WitSkillsData(WitSkillsData.loadData(path +"/plugins/RPGskills/WitSkillsData.data"));
		return data;
	}
}
