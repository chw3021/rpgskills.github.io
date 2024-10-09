package io.github.chw3021.classes.oceanknight;


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


public class OceSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -353355589904236766L;
	public final HashMap<UUID, Integer> WaterSpear;
	public final HashMap<UUID, Integer> WaterBarrier;
	public final HashMap<UUID, Integer> Javelin;
	public final HashMap<UUID, Integer> RipCurrent;
	public final HashMap<UUID, Integer> OceanCharge;
	public final HashMap<UUID, Integer> WetSwing;
	public final HashMap<UUID, Integer> Splash;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public OceSkillsData(HashMap<UUID, Integer> WaterSpear, HashMap<UUID, Integer> WaterBarrier, HashMap<UUID, Integer> Javelin, HashMap<UUID, Integer> RipCurrent, HashMap<UUID, Integer> OceanCharge, HashMap<UUID, Integer> WetSwing, HashMap<UUID, Integer> Splash, HashMap<UUID, Integer> SkillPoints) {
    	this.WaterSpear = WaterSpear;
    	this.WaterBarrier = WaterBarrier;
    	this.Javelin = Javelin;
    	this.RipCurrent = RipCurrent;
    	this.OceanCharge = OceanCharge;
    	this.WetSwing = WetSwing;
    	this.Splash = Splash;
    	this.SkillPoints = SkillPoints;
    	}
    public OceSkillsData(OceSkillsData loadedData) {
    	this.WaterSpear = loadedData.WaterSpear;
    	this.WaterBarrier = loadedData.WaterBarrier;
    	this.Javelin = loadedData.Javelin;
    	this.RipCurrent = loadedData.RipCurrent;
    	this.OceanCharge = loadedData.OceanCharge;
    	this.WetSwing = loadedData.WetSwing;
    	this.Splash = loadedData.Splash;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public OceSkillsData saveData(String filePath) {
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
    public static OceSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            OceSkillsData data = (OceSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            OceSkillsData data = new OceSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),  new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
            
            return data;
        }
    }
    

	@EventHandler
	public void Oceskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("OceSkills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getOceanKnightdata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> WaterSpear = getOceanKnightdata().WaterSpear;
					HashMap<UUID, Integer> WaterBarrier = getOceanKnightdata().WaterBarrier;
					HashMap<UUID, Integer> Javelin = getOceanKnightdata().Javelin;
					HashMap<UUID, Integer> RipCurrent = getOceanKnightdata().RipCurrent;
					HashMap<UUID, Integer> WetSwing = getOceanKnightdata().WetSwing;
					HashMap<UUID, Integer> OceanCharge = getOceanKnightdata().OceanCharge;
					HashMap<UUID, Integer> Splash = getOceanKnightdata().Splash;
					HashMap<UUID, Integer> SkillPoints = getOceanKnightdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					WaterSpear.put(p.getUniqueId(), 0);
					WaterBarrier.put(p.getUniqueId(), 0);
					Javelin.put(p.getUniqueId(), 0);
					RipCurrent.put(p.getUniqueId(), 0);
					OceanCharge.put(p.getUniqueId(), 0);
					WetSwing.put(p.getUniqueId(), 0);
					Splash.put(p.getUniqueId(), 0);
					new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");

					OceSkillsGui osg = new OceSkillsGui();
					osg.OceSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> WaterSpear = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> WaterBarrier = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Javelin = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> RipCurrent = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> OceanCharge = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> WetSwing = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Splash = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				WaterSpear.put(p.getUniqueId(), 0);
				WaterBarrier.put(p.getUniqueId(), 0);
				Javelin.put(p.getUniqueId(), 0);
				RipCurrent.put(p.getUniqueId(), 0);
				OceanCharge.put(p.getUniqueId(), 0);
				WetSwing.put(p.getUniqueId(), 0);
				Splash.put(p.getUniqueId(), 0);
				new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");

				OceSkillsGui osg = new OceSkillsGui();
				osg.OceSkillsinv(p);
			}
		}
	}

	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> WaterSpear = getOceanKnightdata().WaterSpear;
		HashMap<UUID, Integer> WaterBarrier = getOceanKnightdata().WaterBarrier;
		HashMap<UUID, Integer> Javelin = getOceanKnightdata().Javelin;
		HashMap<UUID, Integer> RipCurrent = getOceanKnightdata().RipCurrent;
		HashMap<UUID, Integer> WetSwing = getOceanKnightdata().WetSwing;
		HashMap<UUID, Integer> OceanCharge = getOceanKnightdata().OceanCharge;
		HashMap<UUID, Integer> Splash = getOceanKnightdata().Splash;
		HashMap<UUID, Integer> SkillPoints = getOceanKnightdata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");


	}

    
	@EventHandler
	public void Oceskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> WaterSpear = getOceanKnightdata().WaterSpear;
		HashMap<UUID, Integer> WaterBarrier = getOceanKnightdata().WaterBarrier;
		HashMap<UUID, Integer> Javelin = getOceanKnightdata().Javelin;
		HashMap<UUID, Integer> RipCurrent = getOceanKnightdata().RipCurrent;
		HashMap<UUID, Integer> WetSwing = getOceanKnightdata().WetSwing;
		HashMap<UUID, Integer> OceanCharge = getOceanKnightdata().OceanCharge;
		HashMap<UUID, Integer> Splash = getOceanKnightdata().Splash;
		HashMap<UUID, Integer> SkillPoints = getOceanKnightdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
		        
			}
			else
			{	

				WaterSpear.remove(p.getUniqueId());
				WaterSpear.put(p.getUniqueId(), 0);
				WaterBarrier.remove(p.getUniqueId());
				WaterBarrier.put(p.getUniqueId(), 0);
				Javelin.remove(p.getUniqueId());
				Javelin.put(p.getUniqueId(), 0);
				RipCurrent.remove(p.getUniqueId());
				RipCurrent.put(p.getUniqueId(), 0);
				Splash.remove(p.getUniqueId());
				Splash.put(p.getUniqueId(), 0);
				OceanCharge.remove(p.getUniqueId());
				OceanCharge.put(p.getUniqueId(), 0);
				WetSwing.remove(p.getUniqueId());
				WetSwing.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Oceskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("OceSkills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				OceSkillsGui fsg = new OceSkillsGui();
				
				HashMap<UUID, Integer> WaterSpear = getOceanKnightdata().WaterSpear;
				HashMap<UUID, Integer> WaterBarrier = getOceanKnightdata().WaterBarrier;
				HashMap<UUID, Integer> Javelin = getOceanKnightdata().Javelin;
				HashMap<UUID, Integer> RipCurrent = getOceanKnightdata().RipCurrent;
				HashMap<UUID, Integer> WetSwing = getOceanKnightdata().WetSwing;
				HashMap<UUID, Integer> OceanCharge = getOceanKnightdata().OceanCharge;
				HashMap<UUID, Integer> Splash = getOceanKnightdata().Splash;
				HashMap<UUID, Integer> SkillPoints = getOceanKnightdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "WaterSpear":
					case "바다창술":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && WaterSpear.get(p.getUniqueId())<50){
								WaterSpear.put(p.getUniqueId(), WaterSpear.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(WaterSpear.get(p.getUniqueId()) >= 1){
								WaterSpear.put(p.getUniqueId(), WaterSpear.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(WaterSpear.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(WaterSpear.get(p.getUniqueId())<50){
										final int a = 50 - WaterSpear.get(p.getUniqueId());
										WaterSpear.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									WaterSpear.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+WaterSpear.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(WaterSpear.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+WaterSpear.get(p.getUniqueId()));
								WaterSpear.put(p.getUniqueId(), 0);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						break;		}
						
					case "WaterBarrier":
					case "물의방벽":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && WaterBarrier.get(p.getUniqueId())<50){
								WaterBarrier.put(p.getUniqueId(), WaterBarrier.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(WaterBarrier.get(p.getUniqueId()) >= 1){
								WaterBarrier.put(p.getUniqueId(), WaterBarrier.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(WaterBarrier.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(WaterBarrier.get(p.getUniqueId())<50){
										final int a = 50 - WaterBarrier.get(p.getUniqueId());
										WaterBarrier.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									WaterBarrier.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+WaterBarrier.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(WaterBarrier.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+WaterBarrier.get(p.getUniqueId()));
								WaterBarrier.put(p.getUniqueId(), 0);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						break;		}		
					case "Javelin":
					case "투창":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Javelin.get(p.getUniqueId())<50){
								Javelin.put(p.getUniqueId(), Javelin.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Javelin.get(p.getUniqueId()) >= 1){
								Javelin.put(p.getUniqueId(), Javelin.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Javelin.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Javelin.get(p.getUniqueId())<50){
										final int a = 50 - Javelin.get(p.getUniqueId());
										Javelin.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									Javelin.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Javelin.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Javelin.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Javelin.get(p.getUniqueId()));
								Javelin.put(p.getUniqueId(), 0);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						break;		}
					case "OceanCharge":
					case "삼지창돌격":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && OceanCharge.get(p.getUniqueId())<50){
								OceanCharge.put(p.getUniqueId(), OceanCharge.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(OceanCharge.get(p.getUniqueId()) >= 1){
								OceanCharge.put(p.getUniqueId(), OceanCharge.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(OceanCharge.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(OceanCharge.get(p.getUniqueId())<50){
										final int a = 50 - OceanCharge.get(p.getUniqueId());
										OceanCharge.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									OceanCharge.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+OceanCharge.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(OceanCharge.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+OceanCharge.get(p.getUniqueId()));
								OceanCharge.put(p.getUniqueId(), 0);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						break;		}
					case "WetSwing":
					case "해풍참":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && WetSwing.get(p.getUniqueId())<50){
								WetSwing.put(p.getUniqueId(), WetSwing.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(WetSwing.get(p.getUniqueId()) >= 1){
								WetSwing.put(p.getUniqueId(), WetSwing.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(WetSwing.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(WetSwing.get(p.getUniqueId())<50){
										final int a = 50 - WetSwing.get(p.getUniqueId());
										WetSwing.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									WetSwing.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+WetSwing.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(WetSwing.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+WetSwing.get(p.getUniqueId()));
								WetSwing.put(p.getUniqueId(), 0);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						break;		}
					case "Splash":
					case "물보라":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Splash.put(p.getUniqueId(), Splash.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Splash.get(p.getUniqueId()) >= 1){
								Splash.put(p.getUniqueId(), Splash.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Splash.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Splash.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Splash.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Splash.get(p.getUniqueId()));
								Splash.put(p.getUniqueId(), 0);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						break;		}			
					case "RipCurrent":
					case "역조":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && RipCurrent.get(p.getUniqueId()) < 1){
								RipCurrent.put(p.getUniqueId(), RipCurrent.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(RipCurrent.get(p.getUniqueId()) >= 1){
								RipCurrent.put(p.getUniqueId(), RipCurrent.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(RipCurrent.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(RipCurrent.get(p.getUniqueId()) < 1) {
										RipCurrent.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									RipCurrent.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+RipCurrent.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(RipCurrent.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+RipCurrent.get(p.getUniqueId()));
								RipCurrent.put(p.getUniqueId(), 0);
								new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
						        fsg.OceSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						WaterSpear.put(p.getUniqueId(), 0);
						WaterBarrier.put(p.getUniqueId(), 0);
						Javelin.put(p.getUniqueId(), 0);
						RipCurrent.put(p.getUniqueId(), 0);
						Splash.put(p.getUniqueId(), 0);
						OceanCharge.put(p.getUniqueId(), 0);
						WetSwing.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new OceSkillsData(WaterSpear, WaterBarrier, Javelin, RipCurrent, OceanCharge, WetSwing, Splash, SkillPoints).saveData(path +"/plugins/RPGskills/OceSkillsData.data");
				        fsg.OceSkillsinv(p);
				}break;	}
			
			}
			
		}
		
	}
    
    public static OceSkillsData getOceanKnightdata(){
        String path = new File("").getAbsolutePath();
        OceSkillsData data = new OceSkillsData(OceSkillsData.loadData(path +"/plugins/RPGskills/OceSkillsData.data"));
		return data;
	}
}