package io.github.chw3021.witchdoctor;


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


public class WdcSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -4594335875178051570L;
	public final HashMap<UUID, Integer> Fangs;
	public final HashMap<UUID, Integer> Bosou;
	public final HashMap<UUID, Integer> Harvest;
	public final HashMap<UUID, Integer> AstralProjection;
	public final HashMap<UUID, Integer> Incantation;
	public final HashMap<UUID, Integer> Wraith;
	public final HashMap<UUID, Integer> Legba;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public WdcSkillsData(HashMap<UUID, Integer> Fangs, HashMap<UUID, Integer> Bosou, HashMap<UUID, Integer> Harvest, HashMap<UUID, Integer> AstralProjection, HashMap<UUID, Integer> Incantation, HashMap<UUID, Integer> Wraith, HashMap<UUID, Integer> Legba, HashMap<UUID, Integer> SkillPoints) {
    	this.Fangs = Fangs;
    	this.Bosou = Bosou;
    	this.Harvest = Harvest;
    	this.AstralProjection = AstralProjection;
    	this.Incantation = Incantation;
    	this.Wraith = Wraith;
    	this.Legba = Legba;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for lomageg
    public WdcSkillsData(WdcSkillsData loadedData) {
    	this.Fangs = loadedData.Fangs;
    	this.Bosou = loadedData.Bosou;
    	this.Harvest = loadedData.Harvest;
    	this.AstralProjection = loadedData.AstralProjection;
    	this.Incantation = loadedData.Incantation;
    	this.Wraith = loadedData.Wraith;
    	this.Legba = loadedData.Legba;
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
    public static WdcSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            WdcSkillsData data = (WdcSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
    		new WdcSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),  new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
            
            e.printStackTrace();
            return null;
        }
    }
    

	@EventHandler
	public void Wdcskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("WdcSkills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getWitchDoctordata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> Fangs = getWitchDoctordata().Fangs;
					HashMap<UUID, Integer> Bosou = getWitchDoctordata().Bosou;
					HashMap<UUID, Integer> Harvest = getWitchDoctordata().Harvest;
					HashMap<UUID, Integer> AstralProjection = getWitchDoctordata().AstralProjection;
					HashMap<UUID, Integer> Wraith = getWitchDoctordata().Wraith;
					HashMap<UUID, Integer> Incantation = getWitchDoctordata().Incantation;
					HashMap<UUID, Integer> Legba = getWitchDoctordata().Legba;
					HashMap<UUID, Integer> SkillPoints = getWitchDoctordata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel());
					Fangs.put(p.getUniqueId(), 0);
					Bosou.put(p.getUniqueId(), 0);
					Harvest.put(p.getUniqueId(), 0);
					AstralProjection.put(p.getUniqueId(), 0);
					Incantation.put(p.getUniqueId(), 0);
					Wraith.put(p.getUniqueId(), 0);
					Legba.put(p.getUniqueId(), 0);
					new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");

					WdcSkillsGui wdg = new WdcSkillsGui();
					wdg.WdcSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Fangs = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Bosou = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Harvest = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> AstralProjection = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Incantation = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Wraith = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Legba = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel());
				Fangs.put(p.getUniqueId(), 0);
				Bosou.put(p.getUniqueId(), 0);
				Harvest.put(p.getUniqueId(), 0);
				AstralProjection.put(p.getUniqueId(), 0);
				Incantation.put(p.getUniqueId(), 0);
				Wraith.put(p.getUniqueId(), 0);
				Legba.put(p.getUniqueId(), 0);
				new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");

				WdcSkillsGui wdg = new WdcSkillsGui();
				wdg.WdcSkillsinv(p);
			}
		}
	}
	
    
	@EventHandler
	public void Wdcskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> Fangs = getWitchDoctordata().Fangs;
		HashMap<UUID, Integer> Bosou = getWitchDoctordata().Bosou;
		HashMap<UUID, Integer> Harvest = getWitchDoctordata().Harvest;
		HashMap<UUID, Integer> AstralProjection = getWitchDoctordata().AstralProjection;
		HashMap<UUID, Integer> Wraith = getWitchDoctordata().Wraith;
		HashMap<UUID, Integer> Incantation = getWitchDoctordata().Incantation;
		HashMap<UUID, Integer> Legba = getWitchDoctordata().Legba;
		HashMap<UUID, Integer> SkillPoints = getWitchDoctordata().SkillPoints;
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
		        
			}
			else
			{	

				Fangs.remove(p.getUniqueId());
				Fangs.put(p.getUniqueId(), 0);
				Bosou.remove(p.getUniqueId());
				Bosou.put(p.getUniqueId(), 0);
				Harvest.remove(p.getUniqueId());
				Harvest.put(p.getUniqueId(), 0);
				AstralProjection.remove(p.getUniqueId());
				AstralProjection.put(p.getUniqueId(), 0);
				Legba.remove(p.getUniqueId());
				Legba.put(p.getUniqueId(), 0);
				Incantation.remove(p.getUniqueId());
				Incantation.put(p.getUniqueId(), 0);
				Wraith.remove(p.getUniqueId());
				Wraith.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel());
				new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
			}
		}
	}
	
	@EventHandler
	public void Wdcskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("WdcSkills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				WdcSkillsGui wdg = new WdcSkillsGui();
				
				HashMap<UUID, Integer> Fangs = getWitchDoctordata().Fangs;
				HashMap<UUID, Integer> Bosou = getWitchDoctordata().Bosou;
				HashMap<UUID, Integer> Harvest = getWitchDoctordata().Harvest;
				HashMap<UUID, Integer> AstralProjection = getWitchDoctordata().AstralProjection;
				HashMap<UUID, Integer> Wraith = getWitchDoctordata().Wraith;
				HashMap<UUID, Integer> Incantation = getWitchDoctordata().Incantation;
				HashMap<UUID, Integer> Legba = getWitchDoctordata().Legba;
				HashMap<UUID, Integer> SkillPoints = getWitchDoctordata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Fangs":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Fangs.get(p.getUniqueId())<50){
								Fangs.put(p.getUniqueId(), Fangs.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Fangs.get(p.getUniqueId()) >= 1){
								Fangs.put(p.getUniqueId(), Fangs.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Fangs.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Fangs.get(p.getUniqueId())<50){
										Fangs.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Fangs.get(p.getUniqueId()));								
									}
								}
								else{
									Fangs.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Fangs.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Fangs.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Fangs.get(p.getUniqueId()));
								Fangs.put(p.getUniqueId(), 0);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						break;		}
						
					case "Bosou":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Bosou.get(p.getUniqueId())<10){
								Bosou.put(p.getUniqueId(), Bosou.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Bosou.get(p.getUniqueId()) >= 1){
								Bosou.put(p.getUniqueId(), Bosou.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Bosou.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>10) {
									if(Bosou.get(p.getUniqueId())<10){
										Bosou.put(p.getUniqueId(), 10);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Bosou.get(p.getUniqueId()));								
									}
								}
								else{
									Bosou.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Bosou.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Bosou.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Bosou.get(p.getUniqueId()));
								Bosou.put(p.getUniqueId(), 0);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						break;		}		
					case "Harvest":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Harvest.get(p.getUniqueId())<50){
								Harvest.put(p.getUniqueId(), Harvest.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Harvest.get(p.getUniqueId()) >= 1){
								Harvest.put(p.getUniqueId(), Harvest.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Harvest.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Harvest.get(p.getUniqueId())<50){
										Harvest.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Harvest.get(p.getUniqueId()));								
									}
								}
								else{
									Harvest.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Harvest.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Harvest.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Harvest.get(p.getUniqueId()));
								Harvest.put(p.getUniqueId(), 0);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						break;		}
					case "Incantation":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Incantation.get(p.getUniqueId()) < 1){
								Incantation.put(p.getUniqueId(), Incantation.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Incantation.get(p.getUniqueId()) >= 1){
								Incantation.put(p.getUniqueId(), Incantation.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Incantation.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Incantation.get(p.getUniqueId()) < 1) {
										Incantation.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Incantation.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Incantation.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Incantation.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Incantation.get(p.getUniqueId()));
								Incantation.put(p.getUniqueId(), 0);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						break;		}
					case "Wraith":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Wraith.get(p.getUniqueId())<50){
								Wraith.put(p.getUniqueId(), Wraith.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Wraith.get(p.getUniqueId()) >= 1){
								Wraith.put(p.getUniqueId(), Wraith.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Wraith.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Wraith.get(p.getUniqueId())<50){
										Wraith.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Wraith.get(p.getUniqueId()));								
									}
								}
								else{
									Wraith.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Wraith.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Wraith.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Wraith.get(p.getUniqueId()));
								Wraith.put(p.getUniqueId(), 0);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						break;		}
					case "Legba":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Legba.put(p.getUniqueId(), Legba.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Legba.get(p.getUniqueId()) >= 1){
								Legba.put(p.getUniqueId(), Legba.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Legba.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Legba.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Legba.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Legba.get(p.getUniqueId()));
								Legba.put(p.getUniqueId(), 0);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						break;		}			
					case "AstralProjection":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && AstralProjection.get(p.getUniqueId()) < 1){
								AstralProjection.put(p.getUniqueId(), AstralProjection.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(AstralProjection.get(p.getUniqueId()) >= 1){
								AstralProjection.put(p.getUniqueId(), AstralProjection.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(AstralProjection.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(AstralProjection.get(p.getUniqueId()) < 1) {
										AstralProjection.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									AstralProjection.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+AstralProjection.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(AstralProjection.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+AstralProjection.get(p.getUniqueId()));
								AstralProjection.put(p.getUniqueId(), 0);
								new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
						        wdg.WdcSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":		{			
						Fangs.put(p.getUniqueId(), 0);
						Bosou.put(p.getUniqueId(), 0);
						Harvest.put(p.getUniqueId(), 0);
						AstralProjection.put(p.getUniqueId(), 0);
						Legba.put(p.getUniqueId(), 0);
						Incantation.put(p.getUniqueId(), 0);
						Fangs.put(p.getUniqueId(), 0);
						Wraith.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel());
						new WdcSkillsData(Fangs, Bosou, Harvest, AstralProjection, Incantation, Wraith, Legba, SkillPoints).saveData(path +"/plugins/RPGskills/WdcSkillsData.data");
				        wdg.WdcSkillsinv(p);
				}break;	}
			
			}
			
		}
		
	}
    
    public WdcSkillsData getWitchDoctordata(){
        String path = new File("").getAbsolutePath();
        WdcSkillsData data = new WdcSkillsData(WdcSkillsData.loadData(path +"/plugins/RPGskills/WdcSkillsData.data"));
		return data;
	}
}
