package io.github.chw3021.classes.taoist;


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


public class TaoSkillsData implements Serializable, Listener{
	


	/**
	 * 
	 */
	private static transient final long serialVersionUID = 8677989568617145637L;
	public final HashMap<UUID, Integer> Charm;
	public final HashMap<UUID, Integer> Imagery;
	public final HashMap<UUID, Integer> Vibe;
	public final HashMap<UUID, Integer> Wave;
	public final HashMap<UUID, Integer> Aura;
	public final HashMap<UUID, Integer> Amplify;
	public final HashMap<UUID, Integer> Flip;
	public final HashMap<UUID, Integer> CombustInside;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public TaoSkillsData(HashMap<UUID, Integer> Charm,HashMap<UUID, Integer> Imagery, HashMap<UUID, Integer> Vibe, HashMap<UUID, Integer> Wave, HashMap<UUID, Integer> Aura, HashMap<UUID, Integer> Amplify, HashMap<UUID, Integer> Flip, HashMap<UUID, Integer> CombustInside, HashMap<UUID, Integer> SkillPoints) {
    	this.Charm = Charm;
    	this.Imagery = Imagery;
    	this.Vibe = Vibe;
    	this.Wave = Wave;
    	this.Aura = Aura;
    	this.Amplify = Amplify;
    	this.Flip = Flip;
    	this.CombustInside = CombustInside;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public TaoSkillsData(TaoSkillsData loadedData) {
    	this.Charm = loadedData.Charm;
    	this.Imagery = loadedData.Imagery;
    	this.Vibe = loadedData.Vibe;
    	this.Wave = loadedData.Wave;
    	this.Aura = loadedData.Aura;
    	this.Amplify = loadedData.Amplify;
    	this.Flip = loadedData.Flip;
    	this.CombustInside = loadedData.CombustInside;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public TaoSkillsData saveData(String filePath) {
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
    public static TaoSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            TaoSkillsData data = (TaoSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException | NullPointerException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            TaoSkillsData data = new TaoSkillsData(new HashMap<UUID, Integer>(),new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");

    		return data;
        }
    }


	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> Charm = getTaoerdata().Charm;
		HashMap<UUID, Integer> Imagery = getTaoerdata().Imagery;
		HashMap<UUID, Integer> Vibe = getTaoerdata().Vibe;
		HashMap<UUID, Integer> Wave = getTaoerdata().Wave;
		HashMap<UUID, Integer> Aura = getTaoerdata().Aura;
		HashMap<UUID, Integer> Flip = getTaoerdata().Flip;
		HashMap<UUID, Integer> Amplify = getTaoerdata().Amplify;
		HashMap<UUID, Integer> CombustInside = getTaoerdata().CombustInside;
		HashMap<UUID, Integer> SkillPoints = getTaoerdata().SkillPoints;
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");


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
					HashMap<UUID, Integer> Imagery = getTaoerdata().Imagery;
					HashMap<UUID, Integer> Vibe = getTaoerdata().Vibe;
					HashMap<UUID, Integer> Wave = getTaoerdata().Wave;
					HashMap<UUID, Integer> Aura = getTaoerdata().Aura;
					HashMap<UUID, Integer> Flip = getTaoerdata().Flip;
					HashMap<UUID, Integer> Amplify = getTaoerdata().Amplify;
					HashMap<UUID, Integer> CombustInside = getTaoerdata().CombustInside;
					HashMap<UUID, Integer> SkillPoints = getTaoerdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					Charm.put(p.getUniqueId(), 0);
					Vibe.put(p.getUniqueId(), 0);
					Imagery.put(p.getUniqueId(), 0);
					Wave.put(p.getUniqueId(), 0);
					Aura.put(p.getUniqueId(), 0);
					Amplify.put(p.getUniqueId(), 0);
					Flip.put(p.getUniqueId(), 0);
					CombustInside.put(p.getUniqueId(), 0);
					new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");

					TaoSkillsGui tag = new TaoSkillsGui();
					tag.TaoSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Charm = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Imagery = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Vibe = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Wave = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Aura = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Amplify = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Flip = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> CombustInside = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				Charm.put(p.getUniqueId(), 0);
				Vibe.put(p.getUniqueId(), 0);
				Imagery.put(p.getUniqueId(), 0);
				Wave.put(p.getUniqueId(), 0);
				Aura.put(p.getUniqueId(), 0);
				Amplify.put(p.getUniqueId(), 0);
				Flip.put(p.getUniqueId(), 0);
				Aura.put(p.getUniqueId(), 0);
				CombustInside.put(p.getUniqueId(), 0);
				new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");

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
		HashMap<UUID, Integer> Imagery = getTaoerdata().Imagery;
		HashMap<UUID, Integer> Vibe = getTaoerdata().Vibe;
		HashMap<UUID, Integer> Wave = getTaoerdata().Wave;
		HashMap<UUID, Integer> Aura = getTaoerdata().Aura;
		HashMap<UUID, Integer> Flip = getTaoerdata().Flip;
		HashMap<UUID, Integer> Amplify = getTaoerdata().Amplify;
		HashMap<UUID, Integer> CombustInside = getTaoerdata().CombustInside;
		HashMap<UUID, Integer> SkillPoints = getTaoerdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
		        
			}
			else
			{	

				Charm.put(p.getUniqueId(), 0);
				Imagery.put(p.getUniqueId(), 0);
				Vibe.put(p.getUniqueId(), 0);
				Wave.put(p.getUniqueId(), 0);
				Aura.put(p.getUniqueId(), 0);
				Amplify.put(p.getUniqueId(), 0);
				Flip.put(p.getUniqueId(), 0);
				CombustInside.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
		        
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
				HashMap<UUID, Integer> Imagery = getTaoerdata().Imagery;
				HashMap<UUID, Integer> Vibe = getTaoerdata().Vibe;
				HashMap<UUID, Integer> Wave = getTaoerdata().Wave;
				HashMap<UUID, Integer> Aura = getTaoerdata().Aura;
				HashMap<UUID, Integer> Flip = getTaoerdata().Flip;
				HashMap<UUID, Integer> Amplify = getTaoerdata().Amplify;
				HashMap<UUID, Integer> CombustInside = getTaoerdata().CombustInside;
				HashMap<UUID, Integer> SkillPoints = getTaoerdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Charm":
					case "부적":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Charm.get(p.getUniqueId())<50){
								Charm.put(p.getUniqueId(), Charm.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Charm.get(p.getUniqueId()) >= 1){
								Charm.put(p.getUniqueId(), Charm.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Charm.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Charm.get(p.getUniqueId())<50){
										final int a = 50 - Charm.get(p.getUniqueId());
										Charm.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Charm.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Charm.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Charm.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Charm.get(p.getUniqueId()));
								Charm.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}
						
					case "Vibe":
					case "내공":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Vibe.put(p.getUniqueId(), Vibe.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Vibe.get(p.getUniqueId()) >= 1){
								Vibe.put(p.getUniqueId(), Vibe.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Vibe.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Vibe.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Vibe.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Vibe.get(p.getUniqueId()));
								Vibe.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}		
					case "Wave":
					case "기공권":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Wave.get(p.getUniqueId())<50){
								Wave.put(p.getUniqueId(), Wave.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Wave.get(p.getUniqueId()) >= 1){
								Wave.put(p.getUniqueId(), Wave.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Wave.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Wave.get(p.getUniqueId())<50){
										final int a = 50 - Wave.get(p.getUniqueId());
										Wave.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Wave.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Wave.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Wave.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Wave.get(p.getUniqueId()));
								Wave.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}
					case "Amplify":
					case "증폭":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Amplify.get(p.getUniqueId())<50){
								Amplify.put(p.getUniqueId(), Amplify.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Amplify.get(p.getUniqueId()) >= 1){
								Amplify.put(p.getUniqueId(), Amplify.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Amplify.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Amplify.get(p.getUniqueId())<50){
										final int a = 50 - Amplify.get(p.getUniqueId());
										Amplify.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Amplify.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Amplify.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Amplify.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Amplify.get(p.getUniqueId()));
								Amplify.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}
					case "Flip":
					case "공중제비":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Flip.get(p.getUniqueId())<50){
								Flip.put(p.getUniqueId(), Flip.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Flip.get(p.getUniqueId()) >= 1){
								Flip.put(p.getUniqueId(), Flip.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Flip.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Flip.get(p.getUniqueId())<50){
										final int a = 50 - Flip.get(p.getUniqueId());
										Flip.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Flip.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Flip.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Flip.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Flip.get(p.getUniqueId()));
								Flip.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}	
					case "CombustInside":
					case "잠해소비":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && CombustInside.get(p.getUniqueId())<50){
								CombustInside.put(p.getUniqueId(), CombustInside.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(CombustInside.get(p.getUniqueId()) >= 1){
								CombustInside.put(p.getUniqueId(), CombustInside.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(CombustInside.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(CombustInside.get(p.getUniqueId())<50){
										final int a = 50 - CombustInside.get(p.getUniqueId());
										CombustInside.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									CombustInside.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CombustInside.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(CombustInside.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CombustInside.get(p.getUniqueId()));
								CombustInside.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}
					case "Imagery":
					case "연상":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Imagery.get(p.getUniqueId())<50){
								Imagery.put(p.getUniqueId(), Imagery.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Imagery.get(p.getUniqueId()) >= 1){
								Imagery.put(p.getUniqueId(), Imagery.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Imagery.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Imagery.get(p.getUniqueId())<50){
										final int a = 50 - Imagery.get(p.getUniqueId());
										Imagery.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Imagery.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Imagery.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Imagery.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Imagery.get(p.getUniqueId()));
								Imagery.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}
					case "Aura":
					case "기운":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Aura.get(p.getUniqueId()) < 1){
								Aura.put(p.getUniqueId(), Aura.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Aura.get(p.getUniqueId()) >= 1){
								Aura.put(p.getUniqueId(), Aura.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Aura.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Aura.get(p.getUniqueId()) < 1) {
										Aura.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Aura.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Aura.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Aura.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Aura.get(p.getUniqueId()));
								Aura.put(p.getUniqueId(), 0);
								new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
						        tag.TaoSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						Charm.put(p.getUniqueId(), 0);
						Imagery.put(p.getUniqueId(), 0);
						Vibe.put(p.getUniqueId(), 0);
						Wave.put(p.getUniqueId(), 0);
						Aura.put(p.getUniqueId(), 0);
						Amplify.put(p.getUniqueId(), 0);
						Charm.put(p.getUniqueId(), 0);
						Flip.put(p.getUniqueId(), 0);
						CombustInside.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new TaoSkillsData(Charm,Imagery, Vibe, Wave, Aura, Amplify, Flip, CombustInside, SkillPoints).saveData(path +"/plugins/RPGskills/TaoSkillsData.data");
				        tag.TaoSkillsinv(p);
				        break;	
					}
				}
			
			}
			
		}
		
	}
    
    public static TaoSkillsData getTaoerdata(){
        String path = new File("").getAbsolutePath();
        TaoSkillsData data = new TaoSkillsData(TaoSkillsData.loadData(path +"/plugins/RPGskills/TaoSkillsData.data"));
		return data;
	}
}
