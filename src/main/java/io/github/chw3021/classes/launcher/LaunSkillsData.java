package io.github.chw3021.classes.launcher;


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



public class LaunSkillsData implements Serializable, Listener{
	

	private static transient final long serialVersionUID = -7346160288683890782L;
	public final HashMap<UUID, Integer> ChargingShot;
	public final HashMap<UUID, Integer> Explosion;
	public final HashMap<UUID, Integer> GiantArrow;
	public final HashMap<UUID, Integer> ArrowChange;
	public final HashMap<UUID, Integer> Discharge;
	public final HashMap<UUID, Integer> ArrowRain;
	public final HashMap<UUID, Integer> MagicArrow;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public LaunSkillsData(HashMap<UUID, Integer> ChargingShot, HashMap<UUID, Integer> Explosion, HashMap<UUID, Integer> GiantArrow, HashMap<UUID, Integer> ArrowChange, HashMap<UUID, Integer> Discharge, HashMap<UUID, Integer> ArrowRain, HashMap<UUID, Integer> MagicArrow, HashMap<UUID, Integer> SkillPoints) {
    	this.ChargingShot = ChargingShot;
    	this.Explosion = Explosion;
    	this.GiantArrow = GiantArrow;
    	this.ArrowChange = ArrowChange;
    	this.Discharge = Discharge;
    	this.ArrowRain = ArrowRain;
    	this.MagicArrow = MagicArrow;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public LaunSkillsData(LaunSkillsData loadedData) {
    	this.ChargingShot = loadedData.ChargingShot;
    	this.Explosion = loadedData.Explosion;
    	this.GiantArrow = loadedData.GiantArrow;
    	this.ArrowChange = loadedData.ArrowChange;
    	this.Discharge = loadedData.Discharge;
    	this.ArrowRain = loadedData.ArrowRain;
    	this.MagicArrow = loadedData.MagicArrow;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public LaunSkillsData saveData(String filePath) {
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
    public static LaunSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            LaunSkillsData data = (LaunSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            LaunSkillsData data = new LaunSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),  new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
            
            //e.printStackTrace();
            return data;
        }
    }
    

	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
        
		HashMap<UUID, Integer> ChargingShot = getLauncherdata().ChargingShot;
		HashMap<UUID, Integer> Explosion = getLauncherdata().Explosion;
		HashMap<UUID, Integer> GiantArrow = getLauncherdata().GiantArrow;
		HashMap<UUID, Integer> ArrowChange = getLauncherdata().ArrowChange;
		HashMap<UUID, Integer> ArrowRain = getLauncherdata().ArrowRain;
		HashMap<UUID, Integer> Discharge = getLauncherdata().Discharge;
		HashMap<UUID, Integer> MagicArrow = getLauncherdata().MagicArrow;
		HashMap<UUID, Integer> SkillPoints = getLauncherdata().SkillPoints;
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),0) + 1);
		
		new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
	}
	
	
	@EventHandler
	public void Launskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();
		
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Launskills"))
		{
			try 
			{
				if(!getLauncherdata().SkillPoints.containsKey(p.getUniqueId()))
				{HashMap<UUID, Integer> ChargingShot = getLauncherdata().ChargingShot;
				HashMap<UUID, Integer> Explosion = getLauncherdata().Explosion;
				HashMap<UUID, Integer> GiantArrow = getLauncherdata().GiantArrow;
				HashMap<UUID, Integer> ArrowChange = getLauncherdata().ArrowChange;
				HashMap<UUID, Integer> ArrowRain = getLauncherdata().ArrowRain;
				HashMap<UUID, Integer> Discharge = getLauncherdata().Discharge;
				HashMap<UUID, Integer> MagicArrow = getLauncherdata().MagicArrow;
				HashMap<UUID, Integer> SkillPoints = getLauncherdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					ChargingShot.put(p.getUniqueId(), 0);
					Explosion.put(p.getUniqueId(), 0);
					GiantArrow.put(p.getUniqueId(), 0);
					ArrowChange.put(p.getUniqueId(), 0);
					Discharge.put(p.getUniqueId(), 0);
					ArrowRain.put(p.getUniqueId(), 0);
					MagicArrow.put(p.getUniqueId(), 0);
					new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");

		            LaunSkillsGui lsg = new LaunSkillsGui();
					lsg.Launskillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{HashMap<UUID, Integer> ChargingShot = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Explosion = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> GiantArrow = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> ArrowChange = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Discharge = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> ArrowRain = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> MagicArrow = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				ChargingShot.put(p.getUniqueId(), 0);
				Explosion.put(p.getUniqueId(), 0);
				GiantArrow.put(p.getUniqueId(), 0);
				ArrowChange.put(p.getUniqueId(), 0);
				Discharge.put(p.getUniqueId(), 0);
				ArrowRain.put(p.getUniqueId(), 0);
				MagicArrow.put(p.getUniqueId(), 0);
				new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");

	            LaunSkillsGui lsg = new LaunSkillsGui();
				lsg.Launskillsinv(p);
			}
		}
	}
	
    @EventHandler
	public void Launskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> ChargingShot = getLauncherdata().ChargingShot;
		HashMap<UUID, Integer> Explosion = getLauncherdata().Explosion;
		HashMap<UUID, Integer> GiantArrow = getLauncherdata().GiantArrow;
		HashMap<UUID, Integer> ArrowChange = getLauncherdata().ArrowChange;
		HashMap<UUID, Integer> ArrowRain = getLauncherdata().ArrowRain;
		HashMap<UUID, Integer> Discharge = getLauncherdata().Discharge;
		HashMap<UUID, Integer> MagicArrow = getLauncherdata().MagicArrow;
		HashMap<UUID, Integer> SkillPoints = getLauncherdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
		        
			}
			else
			{	

				ChargingShot.remove(p.getUniqueId());
				ChargingShot.put(p.getUniqueId(), 0);
				Explosion.remove(p.getUniqueId());
				Explosion.put(p.getUniqueId(), 0);
				GiantArrow.remove(p.getUniqueId());
				GiantArrow.put(p.getUniqueId(), 0);
				ArrowChange.remove(p.getUniqueId());
				ArrowChange.put(p.getUniqueId(), 0);
				MagicArrow.remove(p.getUniqueId());
				MagicArrow.put(p.getUniqueId(), 0);
				Discharge.remove(p.getUniqueId());
				Discharge.put(p.getUniqueId(), 0);
				ArrowRain.remove(p.getUniqueId());
				ArrowRain.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Launskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Launskills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				LaunSkillsGui l = new LaunSkillsGui();
				
				HashMap<UUID, Integer> ChargingShot = getLauncherdata().ChargingShot;
				HashMap<UUID, Integer> Explosion = getLauncherdata().Explosion;
				HashMap<UUID, Integer> GiantArrow = getLauncherdata().GiantArrow;
				HashMap<UUID, Integer> ArrowChange = getLauncherdata().ArrowChange;
				HashMap<UUID, Integer> ArrowRain = getLauncherdata().ArrowRain;
				HashMap<UUID, Integer> Discharge = getLauncherdata().Discharge;
				HashMap<UUID, Integer> MagicArrow = getLauncherdata().MagicArrow;
				HashMap<UUID, Integer> SkillPoints = getLauncherdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "ChargingShot":
					case "응집":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ChargingShot.get(p.getUniqueId())<50){
								ChargingShot.put(p.getUniqueId(), ChargingShot.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ChargingShot.get(p.getUniqueId()) >= 1){
								ChargingShot.put(p.getUniqueId(), ChargingShot.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ChargingShot.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(ChargingShot.get(p.getUniqueId())<50){
										final int a = 50 - ChargingShot.get(p.getUniqueId());
										ChargingShot.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);					
									}
								}
								else{
									ChargingShot.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ChargingShot.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ChargingShot.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ChargingShot.get(p.getUniqueId()));
								ChargingShot.put(p.getUniqueId(), 0);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						break;		}
						
					case "Explosion":
					case "폭발":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Explosion.get(p.getUniqueId())<50){
								Explosion.put(p.getUniqueId(), Explosion.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Explosion.get(p.getUniqueId()) >= 1){
								Explosion.put(p.getUniqueId(), Explosion.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Explosion.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Explosion.get(p.getUniqueId())<50){
										final int a = 50 - Explosion.get(p.getUniqueId());
										Explosion.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									Explosion.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Explosion.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Explosion.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Explosion.get(p.getUniqueId()));
								Explosion.put(p.getUniqueId(), 0);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						break;		}		
					case "GiantArrow":
					case "거대화살":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && GiantArrow.get(p.getUniqueId())<50){
								GiantArrow.put(p.getUniqueId(), GiantArrow.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(GiantArrow.get(p.getUniqueId()) >= 1){
								GiantArrow.put(p.getUniqueId(), GiantArrow.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(GiantArrow.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(GiantArrow.get(p.getUniqueId())<50){
										final int a = 50 - GiantArrow.get(p.getUniqueId());
										GiantArrow.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									GiantArrow.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GiantArrow.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(GiantArrow.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GiantArrow.get(p.getUniqueId()));
								GiantArrow.put(p.getUniqueId(), 0);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						break;		}
					case "Discharge":
					case "방출":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Discharge.get(p.getUniqueId())<50){
								Discharge.put(p.getUniqueId(), Discharge.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Discharge.get(p.getUniqueId()) >= 1){
								Discharge.put(p.getUniqueId(), Discharge.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Discharge.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Discharge.get(p.getUniqueId())<50){
										final int a = 50 - Discharge.get(p.getUniqueId());
										Discharge.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									Discharge.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Discharge.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Discharge.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Discharge.get(p.getUniqueId()));
								Discharge.put(p.getUniqueId(), 0);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						break;		}
					case "ArrowRain":
					case "화살세례":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ArrowRain.get(p.getUniqueId())<50){
								ArrowRain.put(p.getUniqueId(), ArrowRain.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ArrowRain.get(p.getUniqueId()) >= 1){
								ArrowRain.put(p.getUniqueId(), ArrowRain.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ArrowRain.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(ArrowRain.get(p.getUniqueId())<50){
										final int a = 50 - ArrowRain.get(p.getUniqueId());
										ArrowRain.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									ArrowRain.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ArrowRain.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ArrowRain.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ArrowRain.get(p.getUniqueId()));
								ArrowRain.put(p.getUniqueId(), 0);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						break;		}
					case "MagicArrow":
					case "마력강화":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								MagicArrow.put(p.getUniqueId(), MagicArrow.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(MagicArrow.get(p.getUniqueId()) >= 1){
								MagicArrow.put(p.getUniqueId(), MagicArrow.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								MagicArrow.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MagicArrow.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(MagicArrow.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MagicArrow.get(p.getUniqueId()));
								MagicArrow.put(p.getUniqueId(), 0);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						break;		}			
					case "ArrowChange":
					case "화살변경":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ArrowChange.get(p.getUniqueId()) < 1){
								ArrowChange.put(p.getUniqueId(), ArrowChange.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ArrowChange.get(p.getUniqueId()) >= 1){
								ArrowChange.put(p.getUniqueId(), ArrowChange.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ArrowChange.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(ArrowChange.get(p.getUniqueId()) < 1) {
										ArrowChange.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									ArrowChange.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ArrowChange.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ArrowChange.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ArrowChange.get(p.getUniqueId()));
								ArrowChange.put(p.getUniqueId(), 0);
								new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
						        l.Launskillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						ChargingShot.put(p.getUniqueId(), 0);
						Explosion.put(p.getUniqueId(), 0);
						GiantArrow.put(p.getUniqueId(), 0);
						ArrowChange.put(p.getUniqueId(), 0);
						MagicArrow.put(p.getUniqueId(), 0);
						Discharge.put(p.getUniqueId(), 0);
						ChargingShot.put(p.getUniqueId(), 0);
						ArrowRain.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new LaunSkillsData(ChargingShot, Explosion, GiantArrow, ArrowChange, Discharge, ArrowRain, MagicArrow, SkillPoints).saveData(path +"/plugins/RPGskills/LaunskillsData.data");
				        l.Launskillsinv(p);
				}break;	}
			
			}
			
		}
		
	}
    
    public static LaunSkillsData getLauncherdata(){
        String path = new File("").getAbsolutePath();
        LaunSkillsData data = new LaunSkillsData(LaunSkillsData.loadData(path +"/plugins/RPGskills/LaunskillsData.data"));
		return data;
	}
}