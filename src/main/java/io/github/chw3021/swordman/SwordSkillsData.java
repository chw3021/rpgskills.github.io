package io.github.chw3021.swordman;


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


public class SwordSkillsData implements Serializable, Listener{
	
	private static transient final long serialVersionUID = -4014081835907447552L;


	public final HashMap<UUID, Integer> SwordDrive;
	public final HashMap<UUID, Integer> CriticalDraw;
	public final HashMap<UUID, Integer> FlashyRush;
	public final HashMap<UUID, Integer> Rising;
	public final HashMap<UUID, Integer> Strike;
	public final HashMap<UUID, Integer> Swoop;
	public final HashMap<UUID, Integer> Guard;
	public final HashMap<UUID, Integer> Dualbladed;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public SwordSkillsData(HashMap<UUID, Integer> SwordDrive, HashMap<UUID, Integer> CriticalDraw, HashMap<UUID, Integer> FlashyRush, HashMap<UUID, Integer> Rising, HashMap<UUID, Integer> Strike, HashMap<UUID, Integer> Swoop, HashMap<UUID, Integer> Guard, HashMap<UUID, Integer> Dualbladed, HashMap<UUID, Integer> SkillPoints) {
    	this.SwordDrive = SwordDrive;
    	this.CriticalDraw = CriticalDraw;
    	this.FlashyRush = FlashyRush;
    	this.Rising = Rising;
    	this.Strike = Strike;
    	this.Swoop = Swoop;
    	this.Guard = Guard;
    	this.Dualbladed = Dualbladed;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public SwordSkillsData(SwordSkillsData loadedData) {
    	this.SwordDrive = loadedData.SwordDrive;
    	this.CriticalDraw = loadedData.CriticalDraw;
    	this.FlashyRush = loadedData.FlashyRush;
    	this.Rising = loadedData.Rising;
    	this.Strike = loadedData.Strike;
    	this.Swoop = loadedData.Swoop;
    	this.Guard = loadedData.Guard;
    	this.Dualbladed = loadedData.Dualbladed;
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
    public static SwordSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            SwordSkillsData data = (SwordSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block

            String path = new File("").getAbsolutePath();
        	
    		new SwordSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
            
            e.printStackTrace();
            return null;
        }
    }
    

	@EventHandler
	public void Swordskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Swordskills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getSwordmandata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> SwordDrive = getSwordmandata().SwordDrive;
					HashMap<UUID, Integer> CriticalDraw = getSwordmandata().CriticalDraw;
					HashMap<UUID, Integer> FlashyRush = getSwordmandata().FlashyRush;
					HashMap<UUID, Integer> Rising = getSwordmandata().Rising;
					HashMap<UUID, Integer> Swoop = getSwordmandata().Swoop;
					HashMap<UUID, Integer> Strike = getSwordmandata().Strike;
					HashMap<UUID, Integer> Guard = getSwordmandata().Guard;
					HashMap<UUID, Integer> Dualbladed = getSwordmandata().Dualbladed;
					HashMap<UUID, Integer> SkillPoints = getSwordmandata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel());
					SwordDrive.put(p.getUniqueId(), 0);
					CriticalDraw.put(p.getUniqueId(), 0);
					FlashyRush.put(p.getUniqueId(), 0);
					Rising.put(p.getUniqueId(), 0);
					Strike.put(p.getUniqueId(), 0);
					Swoop.put(p.getUniqueId(), 0);
					Guard.put(p.getUniqueId(), 0);
					Dualbladed.put(p.getUniqueId(), 0);
					new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");

		            SwordSkillsGui ssg = new SwordSkillsGui();
					ssg.SwordSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> SwordDrive = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> CriticalDraw = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> FlashyRush = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Rising = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Strike = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Swoop = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Guard = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Dualbladed = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel());
				SwordDrive.put(p.getUniqueId(), 0);
				CriticalDraw.put(p.getUniqueId(), 0);
				FlashyRush.put(p.getUniqueId(), 0);
				Rising.put(p.getUniqueId(), 0);
				Strike.put(p.getUniqueId(), 0);
				Swoop.put(p.getUniqueId(), 0);
				Guard.put(p.getUniqueId(), 0);
				Dualbladed.put(p.getUniqueId(), 0);
				new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");

	            SwordSkillsGui ssg = new SwordSkillsGui();
				ssg.SwordSkillsinv(p);
			}
		}
	}
	
    
	@EventHandler
	public void Swordskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> SwordDrive = getSwordmandata().SwordDrive;
		HashMap<UUID, Integer> CriticalDraw = getSwordmandata().CriticalDraw;
		HashMap<UUID, Integer> FlashyRush = getSwordmandata().FlashyRush;
		HashMap<UUID, Integer> Rising = getSwordmandata().Rising;
		HashMap<UUID, Integer> Swoop = getSwordmandata().Swoop;
		HashMap<UUID, Integer> Strike = getSwordmandata().Strike;
		HashMap<UUID, Integer> Guard = getSwordmandata().Guard;
		HashMap<UUID, Integer> Dualbladed = getSwordmandata().Dualbladed;
		HashMap<UUID, Integer> SkillPoints = getSwordmandata().SkillPoints;
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
		        
			}
			else
			{	

				SwordDrive.put(p.getUniqueId(), 0);
				CriticalDraw.put(p.getUniqueId(), 0);
				FlashyRush.put(p.getUniqueId(), 0);
				Rising.put(p.getUniqueId(), 0);
				Guard.put(p.getUniqueId(), 0);
				Strike.put(p.getUniqueId(), 0);
				Swoop.put(p.getUniqueId(), 0);
				Dualbladed.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel());
				new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Swordskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Swordskills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				SwordSkillsGui ssg = new SwordSkillsGui();
				

				HashMap<UUID, Integer> SwordDrive = getSwordmandata().SwordDrive;
				HashMap<UUID, Integer> CriticalDraw = getSwordmandata().CriticalDraw;
				HashMap<UUID, Integer> FlashyRush = getSwordmandata().FlashyRush;
				HashMap<UUID, Integer> Rising = getSwordmandata().Rising;
				HashMap<UUID, Integer> Swoop = getSwordmandata().Swoop;
				HashMap<UUID, Integer> Strike = getSwordmandata().Strike;
				HashMap<UUID, Integer> Guard = getSwordmandata().Guard;
				HashMap<UUID, Integer> Dualbladed = getSwordmandata().Dualbladed;
				HashMap<UUID, Integer> SkillPoints = getSwordmandata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "SwordDrive":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && SwordDrive.get(p.getUniqueId())<50){
								SwordDrive.put(p.getUniqueId(), SwordDrive.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(SwordDrive.get(p.getUniqueId()) >= 1){
								SwordDrive.put(p.getUniqueId(), SwordDrive.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(SwordDrive.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(SwordDrive.get(p.getUniqueId())<50){
										SwordDrive.put(p.getUniqueId(), 50);								
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - SwordDrive.get(p.getUniqueId()));
									}
								}
								else{
									SwordDrive.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SwordDrive.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(SwordDrive.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SwordDrive.get(p.getUniqueId()));
								SwordDrive.put(p.getUniqueId(), 0);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						break;		}
						
					case "CriticalDraw":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && CriticalDraw.get(p.getUniqueId())<50){
								CriticalDraw.put(p.getUniqueId(), CriticalDraw.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(CriticalDraw.get(p.getUniqueId()) >= 1){
								CriticalDraw.put(p.getUniqueId(), CriticalDraw.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(CriticalDraw.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(CriticalDraw.get(p.getUniqueId())<50){
										CriticalDraw.put(p.getUniqueId(), 50);								
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - CriticalDraw.get(p.getUniqueId()));
									}
								}
								else{
									CriticalDraw.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CriticalDraw.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(CriticalDraw.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CriticalDraw.get(p.getUniqueId()));
								CriticalDraw.put(p.getUniqueId(), 0);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						break;		}		
					case "FlashyRush":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && FlashyRush.get(p.getUniqueId())<50){
								FlashyRush.put(p.getUniqueId(), FlashyRush.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(FlashyRush.get(p.getUniqueId()) >= 1){
								FlashyRush.put(p.getUniqueId(), FlashyRush.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(FlashyRush.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(FlashyRush.get(p.getUniqueId())<50){
										FlashyRush.put(p.getUniqueId(), 50);								
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - FlashyRush.get(p.getUniqueId()));
									}
								}
								else{
									FlashyRush.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+FlashyRush.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(FlashyRush.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+FlashyRush.get(p.getUniqueId()));
								FlashyRush.put(p.getUniqueId(), 0);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						break;		}
					case "Strike":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Strike.get(p.getUniqueId())<50){
								Strike.put(p.getUniqueId(), Strike.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Strike.get(p.getUniqueId()) >= 1){
								Strike.put(p.getUniqueId(), Strike.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Strike.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Strike.get(p.getUniqueId())<50){
										Strike.put(p.getUniqueId(), 50);								
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Strike.get(p.getUniqueId()));
									}
								}
								else{
									Strike.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Strike.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Strike.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Strike.get(p.getUniqueId()));
								Strike.put(p.getUniqueId(), 0);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						break;		}
					case "Swoop":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Swoop.get(p.getUniqueId())<50){
								Swoop.put(p.getUniqueId(), Swoop.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Swoop.get(p.getUniqueId()) >= 1){
								Swoop.put(p.getUniqueId(), Swoop.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Swoop.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Swoop.get(p.getUniqueId())<50){
										Swoop.put(p.getUniqueId(), 50);								
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Swoop.get(p.getUniqueId()));
									}
								}
								else{
									Swoop.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Swoop.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Swoop.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Swoop.get(p.getUniqueId()));
								Swoop.put(p.getUniqueId(), 0);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						break;		}
					case "Guard":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Guard.get(p.getUniqueId())<10){
								Guard.put(p.getUniqueId(), Guard.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Guard.get(p.getUniqueId()) >= 1){
								Guard.put(p.getUniqueId(), Guard.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Guard.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>10) {
									if(Guard.get(p.getUniqueId())<10){	
										Guard.put(p.getUniqueId(), 10);						
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Guard.get(p.getUniqueId()));	
									}
								}
								else{
									Guard.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Guard.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Guard.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Guard.get(p.getUniqueId()));
								Guard.put(p.getUniqueId(), 0);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						break;		}				
					case "Dualbladed":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Dualbladed.put(p.getUniqueId(), Dualbladed.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Dualbladed.get(p.getUniqueId()) >= 1){
								Dualbladed.put(p.getUniqueId(), Dualbladed.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Dualbladed.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Dualbladed.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Dualbladed.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Dualbladed.get(p.getUniqueId()));
								Dualbladed.put(p.getUniqueId(), 0);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						break;		}
					case "RisingBlades":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Rising.get(p.getUniqueId())<50){
								Rising.put(p.getUniqueId(), Rising.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Rising.get(p.getUniqueId()) >= 1){
								Rising.put(p.getUniqueId(), Rising.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Rising.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Rising.get(p.getUniqueId())<50){
										Rising.put(p.getUniqueId(), 50);								
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Rising.get(p.getUniqueId()));
									}
								}
								else{
									Rising.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Rising.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Rising.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Rising.get(p.getUniqueId()));
								Rising.put(p.getUniqueId(), 0);
								new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
						        ssg.SwordSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":		{			
						SwordDrive.put(p.getUniqueId(), 0);
						CriticalDraw.put(p.getUniqueId(), 0);
						FlashyRush.put(p.getUniqueId(), 0);
						Rising.put(p.getUniqueId(), 0);
						Guard.put(p.getUniqueId(), 0);
						Strike.put(p.getUniqueId(), 0);
						SwordDrive.put(p.getUniqueId(), 0);
						Swoop.put(p.getUniqueId(), 0);
						Dualbladed.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel());
						new SwordSkillsData(SwordDrive, CriticalDraw, FlashyRush, Rising, Strike, Swoop, Guard, Dualbladed, SkillPoints).saveData(path +"/plugins/RPGskills/SwordSkillsData.data");
				        ssg.SwordSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public SwordSkillsData getSwordmandata(){
        String path = new File("").getAbsolutePath();
        SwordSkillsData data = new SwordSkillsData(SwordSkillsData.loadData(path +"/plugins/RPGskills/SwordSkillsData.data"));
		return data;
	}
}
