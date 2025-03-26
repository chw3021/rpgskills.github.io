package io.github.chw3021.classes.cook;


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



public class CookSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 7496921834249479766L;
	public final HashMap<UUID, Integer> BerrySalad;
	public final HashMap<UUID, Integer> MelonWall;
	public final HashMap<UUID, Integer> DessertRain;
	public final HashMap<UUID, Integer> MushSpa;
	public final HashMap<UUID, Integer> GrilledDishes;
	public final HashMap<UUID, Integer> Saturation;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public CookSkillsData(HashMap<UUID, Integer> BerrySalad, HashMap<UUID, Integer> MelonWall, HashMap<UUID, Integer> DessertRain, HashMap<UUID, Integer> MushSpa, HashMap<UUID, Integer> GrilledDishes, HashMap<UUID, Integer> Saturation, HashMap<UUID, Integer> SkillPoints) {
    	this.BerrySalad = BerrySalad;
    	this.MelonWall = MelonWall;
    	this.DessertRain = DessertRain;
    	this.MushSpa = MushSpa;
    	this.GrilledDishes = GrilledDishes;
    	this.Saturation = Saturation;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public CookSkillsData(CookSkillsData loadedData) {
    	this.BerrySalad = loadedData.BerrySalad;
    	this.MelonWall = loadedData.MelonWall;
    	this.DessertRain = loadedData.DessertRain;
    	this.MushSpa = loadedData.MushSpa;
    	this.GrilledDishes = loadedData.GrilledDishes;
    	this.Saturation = loadedData.Saturation;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public CookSkillsData saveData(String filePath) {
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
    public static CookSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            CookSkillsData data = (CookSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException  e) {
            // TODO Auto-generated catch block

            String path = new File("").getAbsolutePath();
        	
            CookSkillsData data = new CookSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
            
            return data;
        }
    }
    

	@EventHandler
	public void Cookskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Cookskills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getCookdata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> BerrySalad = getCookdata().BerrySalad;
					HashMap<UUID, Integer> MelonWall = getCookdata().MelonWall;
					HashMap<UUID, Integer> DessertRain = getCookdata().DessertRain;
					HashMap<UUID, Integer> MushSpa = getCookdata().MushSpa;
					HashMap<UUID, Integer> GrilledDishes = getCookdata().GrilledDishes;
					HashMap<UUID, Integer> Saturation = getCookdata().Saturation;
					HashMap<UUID, Integer> SkillPoints = getCookdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					BerrySalad.put(p.getUniqueId(), 0);
					MelonWall.put(p.getUniqueId(), 0);
					DessertRain.put(p.getUniqueId(), 0);
					BerrySalad.put(p.getUniqueId(), 0);
					MushSpa.put(p.getUniqueId(), 0);
					GrilledDishes.put(p.getUniqueId(), 0);
					Saturation.put(p.getUniqueId(), 0);
					new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
					CookSkillsGui egg = new CookSkillsGui();
					egg.CookSkillsinv(p);
			        
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> BerrySalad = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> MelonWall = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> DessertRain = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> MushSpa = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> GrilledDishes = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Saturation = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				BerrySalad.put(p.getUniqueId(), 0);
				MelonWall.put(p.getUniqueId(), 0);
				DessertRain.put(p.getUniqueId(), 0);
				BerrySalad.put(p.getUniqueId(), 0);
				MushSpa.put(p.getUniqueId(), 0);
				GrilledDishes.put(p.getUniqueId(), 0);
				Saturation.put(p.getUniqueId(), 0);
				new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
				CookSkillsGui egg = new CookSkillsGui();
				egg.CookSkillsinv(p);
			}
		}
	}

	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> BerrySalad = getCookdata().BerrySalad;
		HashMap<UUID, Integer> MelonWall = getCookdata().MelonWall;
		HashMap<UUID, Integer> DessertRain = getCookdata().DessertRain;
		HashMap<UUID, Integer> MushSpa = getCookdata().MushSpa;
		HashMap<UUID, Integer> GrilledDishes = getCookdata().GrilledDishes;
		HashMap<UUID, Integer> Saturation = getCookdata().Saturation;
		HashMap<UUID, Integer> SkillPoints = getCookdata().SkillPoints;
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");


	}

    
	@EventHandler
	public void Cookskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> BerrySalad = getCookdata().BerrySalad;
		HashMap<UUID, Integer> MelonWall = getCookdata().MelonWall;
		HashMap<UUID, Integer> DessertRain = getCookdata().DessertRain;
		HashMap<UUID, Integer> MushSpa = getCookdata().MushSpa;
		HashMap<UUID, Integer> GrilledDishes = getCookdata().GrilledDishes;
		HashMap<UUID, Integer> Saturation = getCookdata().Saturation;
		HashMap<UUID, Integer> SkillPoints = getCookdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
		        
			}
			else
			{	

				BerrySalad.put(p.getUniqueId(), 0);
				MelonWall.put(p.getUniqueId(), 0);
				DessertRain.put(p.getUniqueId(), 0);
				BerrySalad.put(p.getUniqueId(), 0);
				GrilledDishes.put(p.getUniqueId(), 0);
				MushSpa.put(p.getUniqueId(), 0);
				Saturation.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Cookskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Cookskills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				CookSkillsGui egg = new CookSkillsGui();
				

				HashMap<UUID, Integer> BerrySalad = getCookdata().BerrySalad;
				HashMap<UUID, Integer> MelonWall = getCookdata().MelonWall;
				HashMap<UUID, Integer> DessertRain = getCookdata().DessertRain;
				HashMap<UUID, Integer> MushSpa = getCookdata().MushSpa;
				HashMap<UUID, Integer> GrilledDishes = getCookdata().GrilledDishes;
				HashMap<UUID, Integer> Saturation = getCookdata().Saturation;
				HashMap<UUID, Integer> SkillPoints = getCookdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "BerrySalad":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && BerrySalad.get(p.getUniqueId())<50){
								BerrySalad.put(p.getUniqueId(), BerrySalad.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(BerrySalad.get(p.getUniqueId()) >= 1){
								BerrySalad.put(p.getUniqueId(), BerrySalad.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(BerrySalad.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(BerrySalad.get(p.getUniqueId())<50){
										final int a = 50 - BerrySalad.get(p.getUniqueId());
										BerrySalad.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									BerrySalad.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+BerrySalad.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(BerrySalad.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+BerrySalad.get(p.getUniqueId()));
								BerrySalad.put(p.getUniqueId(), 0);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						break;		}
						
					case "MelonWall":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && MelonWall.get(p.getUniqueId()) < 1){
								MelonWall.put(p.getUniqueId(), MelonWall.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(MelonWall.get(p.getUniqueId()) >= 1){
								MelonWall.put(p.getUniqueId(), MelonWall.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(MelonWall.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(MelonWall.get(p.getUniqueId()) < 1) {
										MelonWall.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									MelonWall.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MelonWall.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(MelonWall.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MelonWall.get(p.getUniqueId()));
								MelonWall.put(p.getUniqueId(), 0);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						break;		}		
					case "DessertRain":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && DessertRain.get(p.getUniqueId())<50){
								DessertRain.put(p.getUniqueId(), DessertRain.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(DessertRain.get(p.getUniqueId()) >= 1){
								DessertRain.put(p.getUniqueId(), DessertRain.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(DessertRain.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(DessertRain.get(p.getUniqueId())<50){
										final int a = 50 - DessertRain.get(p.getUniqueId());
										DessertRain.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									DessertRain.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DessertRain.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(DessertRain.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DessertRain.get(p.getUniqueId()));
								DessertRain.put(p.getUniqueId(), 0);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						break;		}
					case "MushSpa":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && MushSpa.get(p.getUniqueId())<50){
								MushSpa.put(p.getUniqueId(), MushSpa.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(MushSpa.get(p.getUniqueId()) >= 1){
								MushSpa.put(p.getUniqueId(), MushSpa.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(MushSpa.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(MushSpa.get(p.getUniqueId())<50){
										final int a = 50 - MushSpa.get(p.getUniqueId());
										MushSpa.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									MushSpa.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MushSpa.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(MushSpa.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MushSpa.get(p.getUniqueId()));
								MushSpa.put(p.getUniqueId(), 0);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						break;		}
					case "GrilledDishes":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && GrilledDishes.get(p.getUniqueId())<50){
								GrilledDishes.put(p.getUniqueId(), GrilledDishes.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(GrilledDishes.get(p.getUniqueId()) >= 1){
								GrilledDishes.put(p.getUniqueId(), GrilledDishes.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(GrilledDishes.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(GrilledDishes.get(p.getUniqueId())<50){
										final int a = 50 - GrilledDishes.get(p.getUniqueId());
										GrilledDishes.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);								
									}
								}
								else{
									GrilledDishes.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GrilledDishes.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(GrilledDishes.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GrilledDishes.get(p.getUniqueId()));
								GrilledDishes.put(p.getUniqueId(), 0);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						break;		}				
					case "Saturation":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Saturation.put(p.getUniqueId(), Saturation.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Saturation.get(p.getUniqueId()) >= 1){
								Saturation.put(p.getUniqueId(), Saturation.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Saturation.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Saturation.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Saturation.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Saturation.get(p.getUniqueId()));
								Saturation.put(p.getUniqueId(), 0);
								new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
						        egg.CookSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":		{			
						BerrySalad.put(p.getUniqueId(), 0);
						MelonWall.put(p.getUniqueId(), 0);
						DessertRain.put(p.getUniqueId(), 0);
						BerrySalad.put(p.getUniqueId(), 0);
						GrilledDishes.put(p.getUniqueId(), 0);
						MushSpa.put(p.getUniqueId(), 0);
						Saturation.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new CookSkillsData(BerrySalad, MelonWall, DessertRain, MushSpa, GrilledDishes, Saturation, SkillPoints).saveData(path +"/plugins/RPGskills/CookSkillsData.data");
				        egg.CookSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public static CookSkillsData getCookdata(){
        String path = new File("").getAbsolutePath();
        CookSkillsData data = new CookSkillsData(CookSkillsData.loadData(path +"/plugins/RPGskills/CookSkillsData.data"));
		return data;
	}
}
