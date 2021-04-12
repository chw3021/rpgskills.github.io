package io.github.chw3021.hunter;


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

import io.github.chw3021.classes.ClassData;


public class HunSkillsData implements Serializable, Listener{
	
	private static transient final long serialVersionUID = -4014081835907447552L;


	public final HashMap<UUID, Integer> Chain;
	public final HashMap<UUID, Integer> Webthrow;
	public final HashMap<UUID, Integer> Dodge;
	public final HashMap<UUID, Integer> HuntingStart;
	public final HashMap<UUID, Integer> Daze;
	public final HashMap<UUID, Integer> SkullCrusher;
	public final HashMap<UUID, Integer> Backattack;
	public final HashMap<UUID, Integer> Atrocity;
	public final HashMap<UUID, Integer> Bleeding;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public HunSkillsData(HashMap<UUID, Integer> Chain, HashMap<UUID, Integer> Webthrow, HashMap<UUID, Integer> Dodge, HashMap<UUID, Integer> HuntingStart, HashMap<UUID, Integer> Daze, HashMap<UUID, Integer> SkullCrusher, HashMap<UUID, Integer> Backattack, HashMap<UUID, Integer> Atrocity, HashMap<UUID, Integer> Bleeding, HashMap<UUID, Integer> SkillPoints) {
    	this.Chain = Chain;
    	this.Webthrow = Webthrow;
    	this.Dodge = Dodge;
    	this.HuntingStart = HuntingStart;
    	this.Daze = Daze;
    	this.SkullCrusher = SkullCrusher;
    	this.Backattack = Backattack;
    	this.Atrocity = Atrocity;
    	this.Bleeding = Bleeding;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public HunSkillsData(HunSkillsData loadedData) {
    	this.Chain = loadedData.Chain;
    	this.Webthrow = loadedData.Webthrow;
    	this.Dodge = loadedData.Dodge;
    	this.HuntingStart = loadedData.HuntingStart;
    	this.Daze = loadedData.Daze;
    	this.SkullCrusher = loadedData.SkullCrusher;
    	this.Backattack = loadedData.Backattack;
    	this.Atrocity = loadedData.Atrocity;
    	this.Bleeding = loadedData.Bleeding;
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
    public static HunSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            HunSkillsData data = (HunSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
    		new HunSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
            
            e.printStackTrace();
            return null;
        }
    }
    

	@EventHandler
	public void Hunskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();

        String path = new File("").getAbsolutePath();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("HunSkills"))
		{
			try 
			{
				if(!getHunterdata().SkillPoints.containsKey(p.getUniqueId()))
				{HashMap<UUID, Integer> Chain = getHunterdata().Chain;
				HashMap<UUID, Integer> Webthrow = getHunterdata().Webthrow;
				HashMap<UUID, Integer> Dodge = getHunterdata().Dodge;
				HashMap<UUID, Integer> HuntingStart = getHunterdata().HuntingStart;
				HashMap<UUID, Integer> SkullCrusher = getHunterdata().SkullCrusher;
				HashMap<UUID, Integer> Daze = getHunterdata().Daze;
				HashMap<UUID, Integer> Backattack = getHunterdata().Backattack;
				HashMap<UUID, Integer> Atrocity = getHunterdata().Atrocity;
				HashMap<UUID, Integer> Bleeding = getHunterdata().Bleeding;
				HashMap<UUID, Integer> SkillPoints = getHunterdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel());
					Chain.put(p.getUniqueId(), 0);
					Webthrow.put(p.getUniqueId(), 0);
					Dodge.put(p.getUniqueId(), 0);
					HuntingStart.put(p.getUniqueId(), 0);
					Daze.put(p.getUniqueId(), 0);
					SkullCrusher.put(p.getUniqueId(), 0);
					Backattack.put(p.getUniqueId(), 0);
					Atrocity.put(p.getUniqueId(), 0);
					Bleeding.put(p.getUniqueId(), 0);
					new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");

		            HunSkillsGui hsg = new HunSkillsGui();
					hsg.Hunskillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{HashMap<UUID, Integer> Chain = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Webthrow = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Dodge = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> HuntingStart = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Daze = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> SkullCrusher = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Backattack = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Atrocity = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Bleeding = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel());
				Chain.put(p.getUniqueId(), 0);
				Webthrow.put(p.getUniqueId(), 0);
				Dodge.put(p.getUniqueId(), 0);
				HuntingStart.put(p.getUniqueId(), 0);
				Daze.put(p.getUniqueId(), 0);
				SkullCrusher.put(p.getUniqueId(), 0);
				Backattack.put(p.getUniqueId(), 0);
				Atrocity.put(p.getUniqueId(), 0);
				Bleeding.put(p.getUniqueId(), 0);
				new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");

	            HunSkillsGui hsg = new HunSkillsGui();
				hsg.Hunskillsinv(p);
			}
		}
	}
	
    
	@EventHandler
	public void Hunskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();

        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Chain = getHunterdata().Chain;
		HashMap<UUID, Integer> Webthrow = getHunterdata().Webthrow;
		HashMap<UUID, Integer> Dodge = getHunterdata().Dodge;
		HashMap<UUID, Integer> HuntingStart = getHunterdata().HuntingStart;
		HashMap<UUID, Integer> SkullCrusher = getHunterdata().SkullCrusher;
		HashMap<UUID, Integer> Daze = getHunterdata().Daze;
		HashMap<UUID, Integer> Backattack = getHunterdata().Backattack;
		HashMap<UUID, Integer> Atrocity = getHunterdata().Atrocity;
		HashMap<UUID, Integer> Bleeding = getHunterdata().Bleeding;
		HashMap<UUID, Integer> SkillPoints = getHunterdata().SkillPoints;
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
		        
			}
			else
			{	

				Chain.remove(p.getUniqueId());
				Chain.put(p.getUniqueId(), 0);
				Webthrow.remove(p.getUniqueId());
				Webthrow.put(p.getUniqueId(), 0);
				Dodge.remove(p.getUniqueId());
				Dodge.put(p.getUniqueId(), 0);
				HuntingStart.remove(p.getUniqueId());
				HuntingStart.put(p.getUniqueId(), 0);
				Backattack.remove(p.getUniqueId());
				Backattack.put(p.getUniqueId(), 0);
				Daze.remove(p.getUniqueId());
				Daze.put(p.getUniqueId(), 0);
				SkullCrusher.remove(p.getUniqueId());
				SkullCrusher.put(p.getUniqueId(), 0);
				Atrocity.remove(p.getUniqueId());
				Atrocity.put(p.getUniqueId(), 0);
				Bleeding.remove(p.getUniqueId());
				Bleeding.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel());
				new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Hunskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("HunSkills"))
		{
			e.setCancelled(true);

	        String path = new File("").getAbsolutePath();
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				HunSkillsGui hsg = new HunSkillsGui();
				
				HashMap<UUID, Integer> Chain = getHunterdata().Chain;
				HashMap<UUID, Integer> Webthrow = getHunterdata().Webthrow;
				HashMap<UUID, Integer> Dodge = getHunterdata().Dodge;
				HashMap<UUID, Integer> HuntingStart = getHunterdata().HuntingStart;
				HashMap<UUID, Integer> SkullCrusher = getHunterdata().SkullCrusher;
				HashMap<UUID, Integer> Daze = getHunterdata().Daze;
				HashMap<UUID, Integer> Backattack = getHunterdata().Backattack;
				HashMap<UUID, Integer> Atrocity = getHunterdata().Atrocity;
				HashMap<UUID, Integer> Bleeding = getHunterdata().Bleeding;
				HashMap<UUID, Integer> SkillPoints = getHunterdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Chain":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Chain.get(p.getUniqueId()) < 1){
								Chain.put(p.getUniqueId(), Chain.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Chain.get(p.getUniqueId()) >= 1){
								Chain.put(p.getUniqueId(), Chain.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Chain.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Chain.get(p.getUniqueId()) < 1) {
										Chain.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Chain.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Chain.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Chain.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Chain.get(p.getUniqueId()));
								Chain.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
						
					case "Webthrow":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Webthrow.get(p.getUniqueId())<1){
								Webthrow.put(p.getUniqueId(), Webthrow.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Webthrow.get(p.getUniqueId()) >= 1){
								Webthrow.put(p.getUniqueId(), Webthrow.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Webthrow.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Webthrow.get(p.getUniqueId())<1){
										Webthrow.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Webthrow.get(p.getUniqueId()));								
									}
								}
								else{
									Webthrow.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Webthrow.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Webthrow.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Webthrow.get(p.getUniqueId()));
								Webthrow.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}		
					case "Dodge":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Dodge.get(p.getUniqueId()) < 1){
								Dodge.put(p.getUniqueId(), Dodge.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Dodge.get(p.getUniqueId()) >= 1){
								Dodge.put(p.getUniqueId(), Dodge.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Dodge.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Dodge.get(p.getUniqueId()) < 1) {
										Dodge.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Dodge.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Dodge.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Dodge.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Dodge.get(p.getUniqueId()));
								Dodge.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
					case "Daze":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Daze.get(p.getUniqueId())<50){
								Daze.put(p.getUniqueId(), Daze.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Daze.get(p.getUniqueId()) >= 1){
								Daze.put(p.getUniqueId(), Daze.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Daze.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Daze.get(p.getUniqueId())<50){
										Daze.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Daze.get(p.getUniqueId()));								
									}
								}
								else{
									Daze.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Daze.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Daze.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Daze.get(p.getUniqueId()));
								Daze.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
					case "SkullCrusher":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && SkullCrusher.get(p.getUniqueId())<50){
								SkullCrusher.put(p.getUniqueId(), SkullCrusher.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(SkullCrusher.get(p.getUniqueId()) >= 1){
								SkullCrusher.put(p.getUniqueId(), SkullCrusher.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(SkullCrusher.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(SkullCrusher.get(p.getUniqueId())<50){
										SkullCrusher.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - SkullCrusher.get(p.getUniqueId()));								
									}
								}
								else{
									SkullCrusher.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SkullCrusher.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(SkullCrusher.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SkullCrusher.get(p.getUniqueId()));
								SkullCrusher.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
					case "Backattack":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Backattack.get(p.getUniqueId()) < 1){
								Backattack.put(p.getUniqueId(), Backattack.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Backattack.get(p.getUniqueId()) >= 1){
								Backattack.put(p.getUniqueId(), Backattack.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Backattack.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Backattack.get(p.getUniqueId()) < 1) {
										Backattack.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Backattack.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Backattack.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Backattack.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Backattack.get(p.getUniqueId()));
								Backattack.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;	}
					case "Bleeding":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Bleeding.get(p.getUniqueId()) < 1){
								Bleeding.put(p.getUniqueId(), Bleeding.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Bleeding.get(p.getUniqueId()) >= 1){
								Bleeding.put(p.getUniqueId(), Bleeding.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Bleeding.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Bleeding.get(p.getUniqueId()) < 1) {
										Bleeding.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Bleeding.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Bleeding.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Bleeding.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Bleeding.get(p.getUniqueId()));
								Bleeding.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}				
					case "Atrocity":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Atrocity.put(p.getUniqueId(), Atrocity.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Atrocity.get(p.getUniqueId()) >= 1){
								Atrocity.put(p.getUniqueId(), Atrocity.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Atrocity.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Atrocity.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Atrocity.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Atrocity.get(p.getUniqueId()));
								Atrocity.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
					case "HuntingStart":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && HuntingStart.get(p.getUniqueId())<50){
								HuntingStart.put(p.getUniqueId(), HuntingStart.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(HuntingStart.get(p.getUniqueId()) >= 1){
								HuntingStart.put(p.getUniqueId(), HuntingStart.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(HuntingStart.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(HuntingStart.get(p.getUniqueId())<50){
										HuntingStart.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - HuntingStart.get(p.getUniqueId()));								
									}
								}
								else{
									HuntingStart.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HuntingStart.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(HuntingStart.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HuntingStart.get(p.getUniqueId()));
								HuntingStart.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":		{			
						Chain.put(p.getUniqueId(), 0);
						Webthrow.put(p.getUniqueId(), 0);
						Dodge.put(p.getUniqueId(), 0);
						HuntingStart.put(p.getUniqueId(), 0);
						Backattack.put(p.getUniqueId(), 0);
						Bleeding.put(p.getUniqueId(), 0);
						Daze.put(p.getUniqueId(), 0);
						Chain.put(p.getUniqueId(), 0);
						SkullCrusher.put(p.getUniqueId(), 0);
						Atrocity.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel());
						new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Bleeding, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
				        hsg.Hunskillsinv(p);
				        break;	}}
			
			}
			
		}
		
	}
    
    public HunSkillsData getHunterdata(){
        String path = new File("").getAbsolutePath();
        HunSkillsData data = new HunSkillsData(HunSkillsData.loadData(path +"/plugins/RPGskills/HunSkillsData.data"));
		return data;
	}
}
