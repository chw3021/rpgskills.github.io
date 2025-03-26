package io.github.chw3021.classes.broiler;


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

import io.github.chw3021.classes.ClassData;


public class BroSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 4714643530002365800L;
	public final HashMap<UUID, Integer> GlassBreak;
	public final HashMap<UUID, Integer> Crack;
	public final HashMap<UUID, Integer> TimeBomb;
	public final HashMap<UUID, Integer> CactusTrap;
	public final HashMap<UUID, Integer> ChainHook;
	public final HashMap<UUID, Integer> DustEyes;
	public final HashMap<UUID, Integer> OneOnly;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public BroSkillsData(HashMap<UUID, Integer> GlassBreak, HashMap<UUID, Integer> Crack, HashMap<UUID, Integer> TimeBomb, HashMap<UUID, Integer> CactusTrap, HashMap<UUID, Integer> ChainHook, HashMap<UUID, Integer> DustEyes, HashMap<UUID, Integer> OneOnly, HashMap<UUID, Integer> SkillPoints) {
    	this.GlassBreak = GlassBreak;
    	this.Crack = Crack;
    	this.TimeBomb = TimeBomb;
    	this.CactusTrap = CactusTrap;
    	this.ChainHook = ChainHook;
    	this.DustEyes = DustEyes;
    	this.OneOnly = OneOnly;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public BroSkillsData(BroSkillsData loadedData) {
    	this.GlassBreak = loadedData.GlassBreak;
    	this.Crack = loadedData.Crack;
    	this.TimeBomb = loadedData.TimeBomb;
    	this.CactusTrap = loadedData.CactusTrap;
    	this.ChainHook = loadedData.ChainHook;
    	this.DustEyes = loadedData.DustEyes;
    	this.OneOnly = loadedData.OneOnly;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public BroSkillsData saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return this;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return this;
        }
    }
    public static BroSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            BroSkillsData data = (BroSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException |NullPointerException e) {
            // TODO Auto-generated catch block

            String path = new File("").getAbsolutePath();
        	
            BroSkillsData data = new BroSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
            
            return data;
        }
    }
    

	@EventHandler
	public void Broskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Broskills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getBromandata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> GlassBreak = getBromandata().GlassBreak;
					HashMap<UUID, Integer> Crack = getBromandata().Crack;
					HashMap<UUID, Integer> TimeBomb = getBromandata().TimeBomb;
					HashMap<UUID, Integer> CactusTrap = getBromandata().CactusTrap;
					HashMap<UUID, Integer> ChainHook = getBromandata().ChainHook;
					HashMap<UUID, Integer> DustEyes = getBromandata().DustEyes;
					HashMap<UUID, Integer> OneOnly = getBromandata().OneOnly;
					HashMap<UUID, Integer> SkillPoints = getBromandata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					GlassBreak.put(p.getUniqueId(), 0);
					Crack.put(p.getUniqueId(), 0);
					TimeBomb.put(p.getUniqueId(), 0);
					CactusTrap.put(p.getUniqueId(), 0);
					ChainHook.put(p.getUniqueId(), 0);
					DustEyes.put(p.getUniqueId(), 0);
					OneOnly.put(p.getUniqueId(), 0);
					new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");

					BroSkillsGui wsg = new BroSkillsGui();
					wsg.BroSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> GlassBreak = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Crack = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> TimeBomb = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> CactusTrap = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> ChainHook = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> DustEyes = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> OneOnly = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				GlassBreak.put(p.getUniqueId(), 0);
				Crack.put(p.getUniqueId(), 0);
				TimeBomb.put(p.getUniqueId(), 0);
				CactusTrap.put(p.getUniqueId(), 0);
				ChainHook.put(p.getUniqueId(), 0);
				DustEyes.put(p.getUniqueId(), 0);
				OneOnly.put(p.getUniqueId(), 0);
				new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");

				BroSkillsGui wsg = new BroSkillsGui();
				wsg.BroSkillsinv(p);
			}
		}
	}
	
	
	@EventHandler
	public void Broskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> GlassBreak = getBromandata().GlassBreak;
		HashMap<UUID, Integer> Crack = getBromandata().Crack;
		HashMap<UUID, Integer> TimeBomb = getBromandata().TimeBomb;
		HashMap<UUID, Integer> CactusTrap = getBromandata().CactusTrap;
		HashMap<UUID, Integer> ChainHook = getBromandata().ChainHook;
		HashMap<UUID, Integer> DustEyes = getBromandata().DustEyes;
		HashMap<UUID, Integer> OneOnly = getBromandata().OneOnly;
		HashMap<UUID, Integer> SkillPoints = getBromandata().SkillPoints;
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
		        
			}
			else
			{	

				GlassBreak.put(p.getUniqueId(), 0);
				Crack.put(p.getUniqueId(), 0);
				TimeBomb.put(p.getUniqueId(), 0);
				CactusTrap.put(p.getUniqueId(), 0);
				DustEyes.put(p.getUniqueId(), 0);
				ChainHook.put(p.getUniqueId(), 0);
				OneOnly.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
		        
			}
		}
	}

	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> GlassBreak = getBromandata().GlassBreak;
		HashMap<UUID, Integer> Crack = getBromandata().Crack;
		HashMap<UUID, Integer> TimeBomb = getBromandata().TimeBomb;
		HashMap<UUID, Integer> CactusTrap = getBromandata().CactusTrap;
		HashMap<UUID, Integer> ChainHook = getBromandata().ChainHook;
		HashMap<UUID, Integer> DustEyes = getBromandata().DustEyes;
		HashMap<UUID, Integer> OneOnly = getBromandata().OneOnly;
		HashMap<UUID, Integer> SkillPoints = getBromandata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");


	}

	@EventHandler
	public void Broskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Broskills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				BroSkillsGui wsg = new BroSkillsGui();
				HashMap<UUID, Integer> GlassBreak = getBromandata().GlassBreak;
				HashMap<UUID, Integer> Crack = getBromandata().Crack;
				HashMap<UUID, Integer> TimeBomb = getBromandata().TimeBomb;
				HashMap<UUID, Integer> CactusTrap = getBromandata().CactusTrap;
				HashMap<UUID, Integer> ChainHook = getBromandata().ChainHook;
				HashMap<UUID, Integer> DustEyes = getBromandata().DustEyes;
				HashMap<UUID, Integer> OneOnly = getBromandata().OneOnly;
				HashMap<UUID, Integer> SkillPoints = getBromandata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "GlassBreak":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && GlassBreak.get(p.getUniqueId())<50){
								GlassBreak.put(p.getUniqueId(), GlassBreak.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(GlassBreak.get(p.getUniqueId()) >= 1){
								GlassBreak.put(p.getUniqueId(), GlassBreak.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(GlassBreak.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(GlassBreak.get(p.getUniqueId())<50){
										final int a = 50 - GlassBreak.get(p.getUniqueId());
										GlassBreak.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);												
									}
								}
								else{
									GlassBreak.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GlassBreak.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(GlassBreak.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GlassBreak.get(p.getUniqueId()));
								GlassBreak.put(p.getUniqueId(), 0);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						break;		}
						
					case "Crack":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Crack.get(p.getUniqueId()) < 50){
								Crack.put(p.getUniqueId(), Crack.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Crack.get(p.getUniqueId()) >= 1){
								Crack.put(p.getUniqueId(), Crack.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Crack.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Crack.get(p.getUniqueId()) < 50) {
										final int a = 50 - Crack.get(p.getUniqueId());
										Crack.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);														
									}							
								}
								else {
									Crack.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Crack.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Crack.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Crack.get(p.getUniqueId()));
								Crack.put(p.getUniqueId(), 0);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						break;		}		
					case "TimeBomb":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && TimeBomb.get(p.getUniqueId())<50){
								TimeBomb.put(p.getUniqueId(), TimeBomb.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(TimeBomb.get(p.getUniqueId()) >= 1){
								TimeBomb.put(p.getUniqueId(), TimeBomb.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(TimeBomb.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(TimeBomb.get(p.getUniqueId())<50){
										final int a = 50 - TimeBomb.get(p.getUniqueId());
										TimeBomb.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);											
									}
								}
								else{
									TimeBomb.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+TimeBomb.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(TimeBomb.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+TimeBomb.get(p.getUniqueId()));
								TimeBomb.put(p.getUniqueId(), 0);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						break;		}
					case "ChainHook":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ChainHook.get(p.getUniqueId()) < 1){
								ChainHook.put(p.getUniqueId(), ChainHook.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ChainHook.get(p.getUniqueId()) >= 1){
								ChainHook.put(p.getUniqueId(), ChainHook.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ChainHook.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(ChainHook.get(p.getUniqueId()) < 1) {
										ChainHook.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									ChainHook.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ChainHook.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ChainHook.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ChainHook.get(p.getUniqueId()));
								ChainHook.put(p.getUniqueId(), 0);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						break;		}
					case "DustEyes":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && DustEyes.get(p.getUniqueId())<50){
								DustEyes.put(p.getUniqueId(), DustEyes.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(DustEyes.get(p.getUniqueId()) >= 1){
								DustEyes.put(p.getUniqueId(), DustEyes.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(DustEyes.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(DustEyes.get(p.getUniqueId())<50){
										final int a = 50 - DustEyes.get(p.getUniqueId());
										DustEyes.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);												
									}
								}
								else{
									DustEyes.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DustEyes.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(DustEyes.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DustEyes.get(p.getUniqueId()));
								DustEyes.put(p.getUniqueId(), 0);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						break;		}				
					case "OneOnly":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								OneOnly.put(p.getUniqueId(), OneOnly.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(OneOnly.get(p.getUniqueId()) >= 1){
								OneOnly.put(p.getUniqueId(), OneOnly.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								OneOnly.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+OneOnly.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(OneOnly.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+OneOnly.get(p.getUniqueId()));
								OneOnly.put(p.getUniqueId(), 0);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						break;		}
					case "CactusTrap":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && CactusTrap.get(p.getUniqueId())<50){
								CactusTrap.put(p.getUniqueId(), CactusTrap.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(CactusTrap.get(p.getUniqueId()) >= 1){
								CactusTrap.put(p.getUniqueId(), CactusTrap.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(CactusTrap.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(CactusTrap.get(p.getUniqueId())<50){
										final int a = 50 - CactusTrap.get(p.getUniqueId());
										CactusTrap.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);												
									}
								}
								else{
									CactusTrap.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CactusTrap.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(CactusTrap.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CactusTrap.get(p.getUniqueId()));
								CactusTrap.put(p.getUniqueId(), 0);
								new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
						        wsg.BroSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":		{			
						GlassBreak.put(p.getUniqueId(), 0);
						Crack.put(p.getUniqueId(), 0);
						TimeBomb.put(p.getUniqueId(), 0);
						CactusTrap.put(p.getUniqueId(), 0);
						DustEyes.put(p.getUniqueId(), 0);
						ChainHook.put(p.getUniqueId(), 0);
						GlassBreak.put(p.getUniqueId(), 0);
						OneOnly.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new BroSkillsData(GlassBreak, Crack, TimeBomb, CactusTrap, ChainHook, DustEyes, OneOnly, SkillPoints).saveData(path +"/plugins/RPGskills/BroSkillsData.data");
				        wsg.BroSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public static BroSkillsData getBromandata(){
        String path = new File("").getAbsolutePath();
        BroSkillsData data = new BroSkillsData(BroSkillsData.loadData(path +"/plugins/RPGskills/BroSkillsData.data"));
		return data;
	}
}
