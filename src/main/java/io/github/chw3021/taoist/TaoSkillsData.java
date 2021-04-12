package io.github.chw3021.taoist;


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
import org.bukkit.util.io.BukkitObjectOutputStream;


public class TaoSkillsData implements Serializable, Listener{
	


	/**
	 * 
	 */
	private static transient final long serialVersionUID = -7268184397235005084L;
	public final HashMap<UUID, Integer> Charm;
	public final HashMap<UUID, Integer> Vibe;
	public final HashMap<UUID, Integer> Wave;
	public final HashMap<UUID, Integer> StanceChange;
	public final HashMap<UUID, Integer> Aura;
	public final HashMap<UUID, Integer> Flip;
	public final HashMap<UUID, Integer> CombustInside;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public TaoSkillsData(HashMap<UUID, Integer> Charm, HashMap<UUID, Integer> Vibe, HashMap<UUID, Integer> Wave, HashMap<UUID, Integer> StanceChange, HashMap<UUID, Integer> Aura, HashMap<UUID, Integer> Flip, HashMap<UUID, Integer> CombustInside, HashMap<UUID, Integer> SkillPoints) {
    	this.Charm = Charm;
    	this.Vibe = Vibe;
    	this.Wave = Wave;
    	this.StanceChange = StanceChange;
    	this.Aura = Aura;
    	this.Flip = Flip;
    	this.CombustInside = CombustInside;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public TaoSkillsData(TaoSkillsData loadedData) {
    	this.Charm = loadedData.Charm;
    	this.Vibe = loadedData.Vibe;
    	this.Wave = loadedData.Wave;
    	this.StanceChange = loadedData.StanceChange;
    	this.Aura = loadedData.Aura;
    	this.Flip = loadedData.Flip;
    	this.CombustInside = loadedData.CombustInside;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public static TaoSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            TaoSkillsData data = (TaoSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
    		new TaoSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
            
            e.printStackTrace();
            return null;
        }
    }
    

	@EventHandler
	public void Taoskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("TaoSkills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getTaoerdata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> Charm = getTaoerdata().Charm;
					HashMap<UUID, Integer> Vibe = getTaoerdata().Vibe;
					HashMap<UUID, Integer> Wave = getTaoerdata().Wave;
					HashMap<UUID, Integer> StanceChange = getTaoerdata().StanceChange;
					HashMap<UUID, Integer> Flip = getTaoerdata().Flip;
					HashMap<UUID, Integer> Aura = getTaoerdata().Aura;
					HashMap<UUID, Integer> CombustInside = getTaoerdata().CombustInside;
					HashMap<UUID, Integer> SkillPoints = getTaoerdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel());
					Charm.put(p.getUniqueId(), 0);
					Vibe.put(p.getUniqueId(), 0);
					Wave.put(p.getUniqueId(), 0);
					StanceChange.put(p.getUniqueId(), 0);
					Aura.put(p.getUniqueId(), 0);
					Flip.put(p.getUniqueId(), 0);
					CombustInside.put(p.getUniqueId(), 0);
					new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");

					TaoSkillsGui tag = new TaoSkillsGui();
					tag.TaoSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Charm = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Vibe = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Wave = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> StanceChange = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Aura = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Flip = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> CombustInside = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel());
				Charm.put(p.getUniqueId(), 0);
				Vibe.put(p.getUniqueId(), 0);
				Wave.put(p.getUniqueId(), 0);
				StanceChange.put(p.getUniqueId(), 0);
				Aura.put(p.getUniqueId(), 0);
				Flip.put(p.getUniqueId(), 0);
				StanceChange.put(p.getUniqueId(), 0);
				CombustInside.put(p.getUniqueId(), 0);
				new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");

				TaoSkillsGui tag = new TaoSkillsGui();
				tag.TaoSkillsinv(p);
			}
		}
	}
	
    
	@EventHandler
	public void Taoskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();

        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Charm = getTaoerdata().Charm;
		HashMap<UUID, Integer> Vibe = getTaoerdata().Vibe;
		HashMap<UUID, Integer> Wave = getTaoerdata().Wave;
		HashMap<UUID, Integer> StanceChange = getTaoerdata().StanceChange;
		HashMap<UUID, Integer> Flip = getTaoerdata().Flip;
		HashMap<UUID, Integer> Aura = getTaoerdata().Aura;
		HashMap<UUID, Integer> CombustInside = getTaoerdata().CombustInside;
		HashMap<UUID, Integer> SkillPoints = getTaoerdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
		        
			}
			else
			{	

				Charm.put(p.getUniqueId(), 0);
				Vibe.put(p.getUniqueId(), 0);
				Wave.put(p.getUniqueId(), 0);
				StanceChange.put(p.getUniqueId(), 0);
				Aura.put(p.getUniqueId(), 0);
				Flip.put(p.getUniqueId(), 0);
				CombustInside.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel());
				new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Taoskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("TaoSkills"))
		{
	        String path = new File("").getAbsolutePath();
			e.setCancelled(true);
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				TaoSkillsGui tag = new TaoSkillsGui();
				
				HashMap<UUID, Integer> Charm = getTaoerdata().Charm;
				HashMap<UUID, Integer> Vibe = getTaoerdata().Vibe;
				HashMap<UUID, Integer> Wave = getTaoerdata().Wave;
				HashMap<UUID, Integer> StanceChange = getTaoerdata().StanceChange;
				HashMap<UUID, Integer> Flip = getTaoerdata().Flip;
				HashMap<UUID, Integer> Aura = getTaoerdata().Aura;
				HashMap<UUID, Integer> CombustInside = getTaoerdata().CombustInside;
				HashMap<UUID, Integer> SkillPoints = getTaoerdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Charm":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Charm.get(p.getUniqueId())<50){
								Charm.put(p.getUniqueId(), Charm.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Charm.get(p.getUniqueId()) >= 1){
								Charm.put(p.getUniqueId(), Charm.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Charm.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Charm.get(p.getUniqueId())<50){
										Charm.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Charm.get(p.getUniqueId()));								
									}
								}
								else{
									Charm.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Charm.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Charm.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Charm.get(p.getUniqueId()));
								Charm.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}
						
					case "Vibe":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Vibe.put(p.getUniqueId(), Vibe.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Vibe.get(p.getUniqueId()) >= 1){
								Vibe.put(p.getUniqueId(), Vibe.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Vibe.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Vibe.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Vibe.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Vibe.get(p.getUniqueId()));
								Vibe.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}		
					case "Wave":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Wave.get(p.getUniqueId())<50){
								Wave.put(p.getUniqueId(), Wave.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Wave.get(p.getUniqueId()) >= 1){
								Wave.put(p.getUniqueId(), Wave.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Wave.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Wave.get(p.getUniqueId())<50){
										Wave.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Wave.get(p.getUniqueId()));								
									}
								}
								else{
									Wave.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Wave.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Wave.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Wave.get(p.getUniqueId()));
								Wave.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}
					case "Aura":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Aura.get(p.getUniqueId())<50){
								Aura.put(p.getUniqueId(), Aura.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Aura.get(p.getUniqueId()) >= 1){
								Aura.put(p.getUniqueId(), Aura.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Aura.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Aura.get(p.getUniqueId())<50){
										Aura.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Aura.get(p.getUniqueId()));								
									}
								}
								else{
									Aura.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Aura.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Aura.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Aura.get(p.getUniqueId()));
								Aura.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}
					case "Flip":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Flip.get(p.getUniqueId())<50){
								Flip.put(p.getUniqueId(), Flip.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Flip.get(p.getUniqueId()) >= 1){
								Flip.put(p.getUniqueId(), Flip.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Flip.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Flip.get(p.getUniqueId())<50){
										Flip.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Flip.get(p.getUniqueId()));								
									}
								}
								else{
									Flip.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Flip.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Flip.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Flip.get(p.getUniqueId()));
								Flip.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}	
					case "CombustInside":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && CombustInside.get(p.getUniqueId())<50){
								CombustInside.put(p.getUniqueId(), CombustInside.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(CombustInside.get(p.getUniqueId()) >= 1){
								CombustInside.put(p.getUniqueId(), CombustInside.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(CombustInside.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(CombustInside.get(p.getUniqueId())<50){
										CombustInside.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - CombustInside.get(p.getUniqueId()));								
									}
								}
								else{
									CombustInside.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CombustInside.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(CombustInside.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CombustInside.get(p.getUniqueId()));
								CombustInside.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}
					case "StanceChange":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && StanceChange.get(p.getUniqueId()) < 1){
								StanceChange.put(p.getUniqueId(), StanceChange.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(StanceChange.get(p.getUniqueId()) >= 1){
								StanceChange.put(p.getUniqueId(), StanceChange.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(StanceChange.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(StanceChange.get(p.getUniqueId()) < 1) {
										StanceChange.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									StanceChange.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+StanceChange.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(StanceChange.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+StanceChange.get(p.getUniqueId()));
								StanceChange.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":		{			
						Charm.put(p.getUniqueId(), 0);
						Vibe.put(p.getUniqueId(), 0);
						Wave.put(p.getUniqueId(), 0);
						StanceChange.put(p.getUniqueId(), 0);
						Aura.put(p.getUniqueId(), 0);
						Charm.put(p.getUniqueId(), 0);
						Flip.put(p.getUniqueId(), 0);
						CombustInside.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel());
						new TaoSkillsData(Charm, Vibe, Wave, StanceChange, Aura, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
				        tag.TaoSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public TaoSkillsData getTaoerdata(){
        String path = new File("").getAbsolutePath();
        TaoSkillsData data = new TaoSkillsData(TaoSkillsData.loadData(path +"/plugins/RPGskills/TaoSkillsData.data"));
		return data;
	}
}
