package io.github.chw3021.forger;


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


public class ForSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 4834812353125242674L;
	public final HashMap<UUID, Integer> DoubleBarrel;
	public final HashMap<UUID, Integer> HoneyMissile;
	public final HashMap<UUID, Integer> TNTLauncher;
	public final HashMap<UUID, Integer> RailSMG;
	public final HashMap<UUID, Integer> LightningCannon;
	public final HashMap<UUID, Integer> MachineGun;
	public final HashMap<UUID, Integer> Development;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public ForSkillsData(HashMap<UUID, Integer> DoubleBarrel, HashMap<UUID, Integer> HoneyMissile, HashMap<UUID, Integer> TNTLauncher, HashMap<UUID, Integer> RailSMG, HashMap<UUID, Integer> LightningCannon, HashMap<UUID, Integer> MachineGun, HashMap<UUID, Integer> Development, HashMap<UUID, Integer> SkillPoints) {
    	this.DoubleBarrel = DoubleBarrel;
    	this.HoneyMissile = HoneyMissile;
    	this.TNTLauncher = TNTLauncher;
    	this.RailSMG = RailSMG;
    	this.LightningCannon = LightningCannon;
    	this.MachineGun = MachineGun;
    	this.Development = Development;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public ForSkillsData(ForSkillsData loadedData) {
    	this.DoubleBarrel = loadedData.DoubleBarrel;
    	this.HoneyMissile = loadedData.HoneyMissile;
    	this.TNTLauncher = loadedData.TNTLauncher;
    	this.RailSMG = loadedData.RailSMG;
    	this.LightningCannon = loadedData.LightningCannon;
    	this.MachineGun = loadedData.MachineGun;
    	this.Development = loadedData.Development;
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
    public static ForSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            ForSkillsData data = (ForSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block

            String path = new File("").getAbsolutePath();
        	
    		new ForSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
            
            e.printStackTrace();
            return null;
        }
    }
    

	@EventHandler
	public void Forskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Forskills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getForgerdata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> DoubleBarrel = getForgerdata().DoubleBarrel;
					HashMap<UUID, Integer> HoneyMissile = getForgerdata().HoneyMissile;
					HashMap<UUID, Integer> TNTLauncher = getForgerdata().TNTLauncher;
					HashMap<UUID, Integer> RailSMG = getForgerdata().RailSMG;
					HashMap<UUID, Integer> LightningCannon = getForgerdata().LightningCannon;
					HashMap<UUID, Integer> MachineGun = getForgerdata().MachineGun;
					HashMap<UUID, Integer> Development = getForgerdata().Development;
					HashMap<UUID, Integer> SkillPoints = getForgerdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel());
					DoubleBarrel.put(p.getUniqueId(), 0);
					HoneyMissile.put(p.getUniqueId(), 0);
					TNTLauncher.put(p.getUniqueId(), 0);
					RailSMG.put(p.getUniqueId(), 0);
					LightningCannon.put(p.getUniqueId(), 0);
					MachineGun.put(p.getUniqueId(), 0);
					Development.put(p.getUniqueId(), 0);
					new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");

					ForSkillsGui fgg = new ForSkillsGui();
					fgg.ForSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> DoubleBarrel = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> HoneyMissile = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> TNTLauncher = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> RailSMG = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> LightningCannon = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> MachineGun = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Development = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel());
				DoubleBarrel.put(p.getUniqueId(), 0);
				HoneyMissile.put(p.getUniqueId(), 0);
				TNTLauncher.put(p.getUniqueId(), 0);
				RailSMG.put(p.getUniqueId(), 0);
				LightningCannon.put(p.getUniqueId(), 0);
				MachineGun.put(p.getUniqueId(), 0);
				Development.put(p.getUniqueId(), 0);
				new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");

				ForSkillsGui fgg = new ForSkillsGui();
				fgg.ForSkillsinv(p);
			}
		}
	}
	
    
	@EventHandler
	public void Forskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> DoubleBarrel = getForgerdata().DoubleBarrel;
		HashMap<UUID, Integer> HoneyMissile = getForgerdata().HoneyMissile;
		HashMap<UUID, Integer> TNTLauncher = getForgerdata().TNTLauncher;
		HashMap<UUID, Integer> RailSMG = getForgerdata().RailSMG;
		HashMap<UUID, Integer> LightningCannon = getForgerdata().LightningCannon;
		HashMap<UUID, Integer> MachineGun = getForgerdata().MachineGun;
		HashMap<UUID, Integer> Development = getForgerdata().Development;
		HashMap<UUID, Integer> SkillPoints = getForgerdata().SkillPoints;
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
		        
			}
			else
			{	

				DoubleBarrel.put(p.getUniqueId(), 0);
				HoneyMissile.put(p.getUniqueId(), 0);
				TNTLauncher.put(p.getUniqueId(), 0);
				RailSMG.put(p.getUniqueId(), 0);
				MachineGun.put(p.getUniqueId(), 0);
				LightningCannon.put(p.getUniqueId(), 0);
				Development.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel());
				new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Forskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Forskills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				ForSkillsGui fgg = new ForSkillsGui();
				

				HashMap<UUID, Integer> DoubleBarrel = getForgerdata().DoubleBarrel;
				HashMap<UUID, Integer> HoneyMissile = getForgerdata().HoneyMissile;
				HashMap<UUID, Integer> TNTLauncher = getForgerdata().TNTLauncher;
				HashMap<UUID, Integer> RailSMG = getForgerdata().RailSMG;
				HashMap<UUID, Integer> LightningCannon = getForgerdata().LightningCannon;
				HashMap<UUID, Integer> MachineGun = getForgerdata().MachineGun;
				HashMap<UUID, Integer> Development = getForgerdata().Development;
				HashMap<UUID, Integer> SkillPoints = getForgerdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Shockwave":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && DoubleBarrel.get(p.getUniqueId()) < 1){
								DoubleBarrel.put(p.getUniqueId(), DoubleBarrel.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(DoubleBarrel.get(p.getUniqueId()) >= 1){
								DoubleBarrel.put(p.getUniqueId(), DoubleBarrel.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(DoubleBarrel.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(DoubleBarrel.get(p.getUniqueId()) < 1) {
										DoubleBarrel.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									DoubleBarrel.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DoubleBarrel.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(DoubleBarrel.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DoubleBarrel.get(p.getUniqueId()));
								DoubleBarrel.put(p.getUniqueId(), 0);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						break;		}
						
					case "HoneyMissile":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && HoneyMissile.get(p.getUniqueId())<50){
								HoneyMissile.put(p.getUniqueId(), HoneyMissile.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(HoneyMissile.get(p.getUniqueId()) >= 1){
								HoneyMissile.put(p.getUniqueId(), HoneyMissile.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(HoneyMissile.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(HoneyMissile.get(p.getUniqueId())<50){
										HoneyMissile.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - HoneyMissile.get(p.getUniqueId()));								
									}
								}
								else{
									HoneyMissile.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HoneyMissile.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(HoneyMissile.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HoneyMissile.get(p.getUniqueId()));
								HoneyMissile.put(p.getUniqueId(), 0);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						break;		}		
					case "TNTLauncher":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && TNTLauncher.get(p.getUniqueId())<50){
								TNTLauncher.put(p.getUniqueId(), TNTLauncher.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(TNTLauncher.get(p.getUniqueId()) >= 1){
								TNTLauncher.put(p.getUniqueId(), TNTLauncher.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(TNTLauncher.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(TNTLauncher.get(p.getUniqueId())<50){
										TNTLauncher.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - TNTLauncher.get(p.getUniqueId()));								
									}
								}
								else{
									TNTLauncher.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+TNTLauncher.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(TNTLauncher.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+TNTLauncher.get(p.getUniqueId()));
								TNTLauncher.put(p.getUniqueId(), 0);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						break;		}
					case "LightningCannon":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && LightningCannon.get(p.getUniqueId())<50){
								LightningCannon.put(p.getUniqueId(), LightningCannon.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(LightningCannon.get(p.getUniqueId()) >= 1){
								LightningCannon.put(p.getUniqueId(), LightningCannon.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(LightningCannon.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(LightningCannon.get(p.getUniqueId())<50){
										LightningCannon.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - LightningCannon.get(p.getUniqueId()));								
									}
								}
								else{
									LightningCannon.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+LightningCannon.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(LightningCannon.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+LightningCannon.get(p.getUniqueId()));
								LightningCannon.put(p.getUniqueId(), 0);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						break;		}
					case "MachineGun":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && MachineGun.get(p.getUniqueId())<50){
								MachineGun.put(p.getUniqueId(), MachineGun.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(MachineGun.get(p.getUniqueId()) >= 1){
								MachineGun.put(p.getUniqueId(), MachineGun.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(MachineGun.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(MachineGun.get(p.getUniqueId())<50){
										MachineGun.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - MachineGun.get(p.getUniqueId()));								
									}
								}
								else{
									MachineGun.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MachineGun.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(MachineGun.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MachineGun.get(p.getUniqueId()));
								MachineGun.put(p.getUniqueId(), 0);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						break;		}				
					case "Development":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Development.put(p.getUniqueId(), Development.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Development.get(p.getUniqueId()) >= 1){
								Development.put(p.getUniqueId(), Development.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Development.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Development.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Development.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Development.get(p.getUniqueId()));
								Development.put(p.getUniqueId(), 0);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						break;		}
					case "RailSMG":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && RailSMG.get(p.getUniqueId())<50){
								RailSMG.put(p.getUniqueId(), RailSMG.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(RailSMG.get(p.getUniqueId()) >= 1){
								RailSMG.put(p.getUniqueId(), RailSMG.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(RailSMG.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(RailSMG.get(p.getUniqueId())<50){
										RailSMG.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - RailSMG.get(p.getUniqueId()));								
									}
								}
								else{
									RailSMG.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+RailSMG.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(RailSMG.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+RailSMG.get(p.getUniqueId()));
								RailSMG.put(p.getUniqueId(), 0);
								new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
						        fgg.ForSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":		{			
						DoubleBarrel.put(p.getUniqueId(), 0);
						HoneyMissile.put(p.getUniqueId(), 0);
						TNTLauncher.put(p.getUniqueId(), 0);
						RailSMG.put(p.getUniqueId(), 0);
						MachineGun.put(p.getUniqueId(), 0);
						LightningCannon.put(p.getUniqueId(), 0);
						DoubleBarrel.put(p.getUniqueId(), 0);
						Development.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel());
						new ForSkillsData(DoubleBarrel, HoneyMissile, TNTLauncher, RailSMG, LightningCannon, MachineGun, Development, SkillPoints).saveData(path +"/plugins/RPGskills/ForSkillsData.data");
				        fgg.ForSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public ForSkillsData getForgerdata(){
        String path = new File("").getAbsolutePath();
        ForSkillsData data = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
		return data;
	}
}
