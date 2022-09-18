package io.github.chw3021.classes.paladin;


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



public class PalSkillsData implements Serializable, Listener{
	

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -8140068910016571145L;
	public final HashMap<UUID, Integer> Thrust;
	public final HashMap<UUID, Integer> Restraint;
	public final HashMap<UUID, Integer> Judgement;
	public final HashMap<UUID, Integer> Punish;
	public final HashMap<UUID, Integer> Encourge;
	public final HashMap<UUID, Integer> Pray;
	public final HashMap<UUID, Integer> Protection;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public PalSkillsData(HashMap<UUID, Integer> Thrust, HashMap<UUID, Integer> Restraint, HashMap<UUID, Integer> Judgement, HashMap<UUID, Integer> Punish, HashMap<UUID, Integer> Encourge, HashMap<UUID, Integer> Pray, HashMap<UUID, Integer> Protection, HashMap<UUID, Integer> SkillPoints) {
    	this.Thrust = Thrust;
    	this.Restraint = Restraint;
    	this.Judgement = Judgement;
    	this.Punish = Punish;
    	this.Encourge = Encourge;
    	this.Pray = Pray;
    	this.Protection = Protection;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public PalSkillsData(PalSkillsData loadedData) {
    	this.Thrust = loadedData.Thrust;
    	this.Restraint = loadedData.Restraint;
    	this.Judgement = loadedData.Judgement;
    	this.Punish = loadedData.Punish;
    	this.Encourge = loadedData.Encourge;
    	this.Pray = loadedData.Pray;
    	this.Protection = loadedData.Protection;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public PalSkillsData saveData(String filePath) {
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
    public static PalSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            PalSkillsData data = (PalSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException | NullPointerException  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            PalSkillsData data = new PalSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),  new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
            
            return data;
        }
    }
    

	@EventHandler
	public void Palskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("PalSkills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getPaladindata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> Thrust = getPaladindata().Thrust;
					HashMap<UUID, Integer> Restraint = getPaladindata().Restraint;
					HashMap<UUID, Integer> Judgement = getPaladindata().Judgement;
					HashMap<UUID, Integer> Punish = getPaladindata().Punish;
					HashMap<UUID, Integer> Pray = getPaladindata().Pray;
					HashMap<UUID, Integer> Encourge = getPaladindata().Encourge;
					HashMap<UUID, Integer> Protection = getPaladindata().Protection;
					HashMap<UUID, Integer> SkillPoints = getPaladindata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					Thrust.put(p.getUniqueId(), 0);
					Restraint.put(p.getUniqueId(), 0);
					Judgement.put(p.getUniqueId(), 0);
					Punish.put(p.getUniqueId(), 0);
					Encourge.put(p.getUniqueId(), 0);
					Pray.put(p.getUniqueId(), 0);
					Protection.put(p.getUniqueId(), 0);
					new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");

		            PalSkillsGui psg = new PalSkillsGui();
					psg.PalSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Thrust = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Restraint = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Judgement = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Punish = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Encourge = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Pray = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Protection = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				Thrust.put(p.getUniqueId(), 0);
				Restraint.put(p.getUniqueId(), 0);
				Judgement.put(p.getUniqueId(), 0);
				Punish.put(p.getUniqueId(), 0);
				Encourge.put(p.getUniqueId(), 0);
				Pray.put(p.getUniqueId(), 0);
				Protection.put(p.getUniqueId(), 0);
				new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");

	            PalSkillsGui psg = new PalSkillsGui();
				psg.PalSkillsinv(p);
			}
		}
	}


	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Thrust = getPaladindata().Thrust;
		HashMap<UUID, Integer> Restraint = getPaladindata().Restraint;
		HashMap<UUID, Integer> Judgement = getPaladindata().Judgement;
		HashMap<UUID, Integer> Punish = getPaladindata().Punish;
		HashMap<UUID, Integer> Pray = getPaladindata().Pray;
		HashMap<UUID, Integer> Encourge = getPaladindata().Encourge;
		HashMap<UUID, Integer> Protection = getPaladindata().Protection;
		HashMap<UUID, Integer> SkillPoints = getPaladindata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");


	}

    
	@EventHandler
	public void Palskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> Thrust = getPaladindata().Thrust;
		HashMap<UUID, Integer> Restraint = getPaladindata().Restraint;
		HashMap<UUID, Integer> Judgement = getPaladindata().Judgement;
		HashMap<UUID, Integer> Punish = getPaladindata().Punish;
		HashMap<UUID, Integer> Pray = getPaladindata().Pray;
		HashMap<UUID, Integer> Encourge = getPaladindata().Encourge;
		HashMap<UUID, Integer> Protection = getPaladindata().Protection;
		HashMap<UUID, Integer> SkillPoints = getPaladindata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
		        
			}
			else
			{	

				Thrust.remove(p.getUniqueId());
				Thrust.put(p.getUniqueId(), 0);
				Restraint.remove(p.getUniqueId());
				Restraint.put(p.getUniqueId(), 0);
				Judgement.remove(p.getUniqueId());
				Judgement.put(p.getUniqueId(), 0);
				Punish.remove(p.getUniqueId());
				Punish.put(p.getUniqueId(), 0);
				Protection.remove(p.getUniqueId());
				Protection.put(p.getUniqueId(), 0);
				Encourge.remove(p.getUniqueId());
				Encourge.put(p.getUniqueId(), 0);
				Pray.remove(p.getUniqueId());
				Pray.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Palskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("PalSkills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				PalSkillsGui psg = new PalSkillsGui();
				

				HashMap<UUID, Integer> Thrust = getPaladindata().Thrust;
				HashMap<UUID, Integer> Restraint = getPaladindata().Restraint;
				HashMap<UUID, Integer> Judgement = getPaladindata().Judgement;
				HashMap<UUID, Integer> Punish = getPaladindata().Punish;
				HashMap<UUID, Integer> Pray = getPaladindata().Pray;
				HashMap<UUID, Integer> Encourge = getPaladindata().Encourge;
				HashMap<UUID, Integer> Protection = getPaladindata().Protection;
				HashMap<UUID, Integer> SkillPoints = getPaladindata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Thrust":
					case "진압":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Thrust.get(p.getUniqueId())<50){
								Thrust.put(p.getUniqueId(), Thrust.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Thrust.get(p.getUniqueId()) >= 1){
								Thrust.put(p.getUniqueId(), Thrust.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Thrust.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Thrust.get(p.getUniqueId())<50){
										final int a = 50 - Thrust.get(p.getUniqueId());
										Thrust.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);					
									}
								}
								else{
									Thrust.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Thrust.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Thrust.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Thrust.get(p.getUniqueId()));
								Thrust.put(p.getUniqueId(), 0);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						break;		}
						
					case "Restraint":
					case "결박":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Restraint.get(p.getUniqueId())<50){
								Restraint.put(p.getUniqueId(), Restraint.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Restraint.get(p.getUniqueId()) >= 1){
								Restraint.put(p.getUniqueId(), Restraint.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Restraint.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Restraint.get(p.getUniqueId())<50){
										final int a = 50 - Restraint.get(p.getUniqueId());
										Restraint.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									Restraint.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Restraint.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Restraint.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Restraint.get(p.getUniqueId()));
								Restraint.put(p.getUniqueId(), 0);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						break;		}		
					case "Judgement":
					case "심판":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Judgement.get(p.getUniqueId())<50){
								Judgement.put(p.getUniqueId(), Judgement.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Judgement.get(p.getUniqueId()) >= 1){
								Judgement.put(p.getUniqueId(), Judgement.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Judgement.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Judgement.get(p.getUniqueId())<50){
										final int a = 50 - Judgement.get(p.getUniqueId());
										Judgement.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									Judgement.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Judgement.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Judgement.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Judgement.get(p.getUniqueId()));
								Judgement.put(p.getUniqueId(), 0);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						break;		}
					case "Encourge":
					case "격려":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Encourge.get(p.getUniqueId())<50){
								Encourge.put(p.getUniqueId(), Encourge.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Encourge.get(p.getUniqueId()) >= 1){
								Encourge.put(p.getUniqueId(), Encourge.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Encourge.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Encourge.get(p.getUniqueId())<50){
										final int a = 50 - Encourge.get(p.getUniqueId());
										Encourge.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									Encourge.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Encourge.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Encourge.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Encourge.get(p.getUniqueId()));
								Encourge.put(p.getUniqueId(), 0);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						break;		}
					case "Pray":
					case "기도":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Pray.get(p.getUniqueId())<10){
								Pray.put(p.getUniqueId(), Pray.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Pray.get(p.getUniqueId()) >= 1){
								Pray.put(p.getUniqueId(), Pray.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Pray.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>10) {
									if(Pray.get(p.getUniqueId())<10){
										final int a = 10 - Pray.get(p.getUniqueId());
										Pray.put(p.getUniqueId(), 10);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Pray.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Pray.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Pray.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Pray.get(p.getUniqueId()));
								Pray.put(p.getUniqueId(), 0);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						break;		}
					case "Faith":
					case "신앙":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Protection.put(p.getUniqueId(), Protection.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Protection.get(p.getUniqueId()) >= 1){
								Protection.put(p.getUniqueId(), Protection.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Protection.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Protection.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Protection.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Protection.get(p.getUniqueId()));
								Protection.put(p.getUniqueId(), 0);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						break;		}			
					case "Punish":
					case "징벌":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Punish.get(p.getUniqueId())<50){
								Punish.put(p.getUniqueId(), Punish.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Punish.get(p.getUniqueId()) >= 1){
								Punish.put(p.getUniqueId(), Punish.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Punish.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Punish.get(p.getUniqueId())<50){
										final int a = 50 - Punish.get(p.getUniqueId());
										Punish.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);					
									}
								}
								else{
									Punish.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Punish.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Punish.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Punish.get(p.getUniqueId()));
								Punish.put(p.getUniqueId(), 0);
								new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
						        psg.PalSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						Thrust.put(p.getUniqueId(), 0);
						Restraint.put(p.getUniqueId(), 0);
						Judgement.put(p.getUniqueId(), 0);
						Punish.put(p.getUniqueId(), 0);
						Protection.put(p.getUniqueId(), 0);
						Encourge.put(p.getUniqueId(), 0);
						Thrust.put(p.getUniqueId(), 0);
						Pray.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new PalSkillsData(Thrust, Restraint, Judgement, Punish, Encourge, Pray, Protection, SkillPoints).saveData(path +"/plugins/RPGskills/PalSkillsData.data");
				        psg.PalSkillsinv(p);
				}break;	}
			
			}
			
		}
		
	}
    
    public static PalSkillsData getPaladindata(){
        String path = new File("").getAbsolutePath();
        PalSkillsData data = new PalSkillsData(PalSkillsData.loadData(path +"/plugins/RPGskills/PalSkillsData.data"));
		return data;
	}
}
