package io.github.chw3021.classes.wreltler;


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



public class WreSkillsData implements Serializable, Listener{
	


	/**
	 * 
	 */
	private static transient final long serialVersionUID = -3510364976678355846L;
	public final HashMap<UUID, Integer> ChokeSlam;
	public final HashMap<UUID, Integer> TakeDown;
	public final HashMap<UUID, Integer> Suplex;
	public final HashMap<UUID, Integer> GuillotineChoke;
	public final HashMap<UUID, Integer> Tackle;
	public final HashMap<UUID, Integer> GiantSwing;
	public final HashMap<UUID, Integer> ArmThrow;
	public final HashMap<UUID, Integer> ForeArmSmash;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public WreSkillsData(HashMap<UUID, Integer> ChokeSlam, HashMap<UUID, Integer> TakeDown, HashMap<UUID, Integer> Suplex, HashMap<UUID, Integer> GuillotineChoke, HashMap<UUID, Integer> Tackle, HashMap<UUID, Integer> GiantSwing, HashMap<UUID, Integer> ArmThrow, HashMap<UUID, Integer> ForeArmSmash, HashMap<UUID, Integer> SkillPoints) {
    	this.ChokeSlam = ChokeSlam;
    	this.TakeDown = TakeDown;
    	this.Suplex = Suplex;
    	this.GuillotineChoke = GuillotineChoke;
    	this.Tackle = Tackle;
    	this.GiantSwing = GiantSwing;
    	this.ArmThrow = ArmThrow;
    	this.ForeArmSmash = ForeArmSmash;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public WreSkillsData(WreSkillsData loadedData) {
    	this.ChokeSlam = loadedData.ChokeSlam;
    	this.TakeDown = loadedData.TakeDown;
    	this.Suplex = loadedData.Suplex;
    	this.GuillotineChoke = loadedData.GuillotineChoke;
    	this.Tackle = loadedData.Tackle;
    	this.GiantSwing = loadedData.GiantSwing;
    	this.ArmThrow = loadedData.ArmThrow;
    	this.ForeArmSmash = loadedData.ForeArmSmash;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public WreSkillsData saveData(String filePath) {
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
    public static WreSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            WreSkillsData data = (WreSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            WreSkillsData data = new WreSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
            
            //e.printStackTrace();
            return data;
        }
    }
    

	@EventHandler
	public void Wreskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("WreSkills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getWrestlerdata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> ChokeSlam = getWrestlerdata().ChokeSlam;
					HashMap<UUID, Integer> TakeDown = getWrestlerdata().TakeDown;
					HashMap<UUID, Integer> Suplex = getWrestlerdata().Suplex;
					HashMap<UUID, Integer> GuillotineChoke = getWrestlerdata().GuillotineChoke;
					HashMap<UUID, Integer> GiantSwing = getWrestlerdata().GiantSwing;
					HashMap<UUID, Integer> Tackle = getWrestlerdata().Tackle;
					HashMap<UUID, Integer> ArmThrow = getWrestlerdata().ArmThrow;
					HashMap<UUID, Integer> ForeArmSmash = getWrestlerdata().ForeArmSmash;
					HashMap<UUID, Integer> SkillPoints = getWrestlerdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					ChokeSlam.put(p.getUniqueId(), 0);
					TakeDown.put(p.getUniqueId(), 0);
					Suplex.put(p.getUniqueId(), 0);
					GuillotineChoke.put(p.getUniqueId(), 0);
					Tackle.put(p.getUniqueId(), 0);
					GiantSwing.put(p.getUniqueId(), 0);
					ArmThrow.put(p.getUniqueId(), 0);
					ForeArmSmash.put(p.getUniqueId(), 0);
					new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");

					WreSkillsGui wrg = new WreSkillsGui();
					wrg.WreSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> ChokeSlam = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> TakeDown = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Suplex = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> GuillotineChoke = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Tackle = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> GiantSwing = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> ArmThrow = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> ForeArmSmash = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				ChokeSlam.put(p.getUniqueId(), 0);
				TakeDown.put(p.getUniqueId(), 0);
				Suplex.put(p.getUniqueId(), 0);
				GuillotineChoke.put(p.getUniqueId(), 0);
				Tackle.put(p.getUniqueId(), 0);
				GiantSwing.put(p.getUniqueId(), 0);
				ArmThrow.put(p.getUniqueId(), 0);
				ForeArmSmash.put(p.getUniqueId(), 0);
				new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");

				WreSkillsGui wrg = new WreSkillsGui();
				wrg.WreSkillsinv(p);
			}
		}
	}


	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> ChokeSlam = getWrestlerdata().ChokeSlam;
		HashMap<UUID, Integer> TakeDown = getWrestlerdata().TakeDown;
		HashMap<UUID, Integer> Suplex = getWrestlerdata().Suplex;
		HashMap<UUID, Integer> GuillotineChoke = getWrestlerdata().GuillotineChoke;
		HashMap<UUID, Integer> GiantSwing = getWrestlerdata().GiantSwing;
		HashMap<UUID, Integer> Tackle = getWrestlerdata().Tackle;
		HashMap<UUID, Integer> ArmThrow = getWrestlerdata().ArmThrow;
		HashMap<UUID, Integer> ForeArmSmash = getWrestlerdata().ForeArmSmash;
		HashMap<UUID, Integer> SkillPoints = getWrestlerdata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");


	}

    
	@EventHandler
	public void Wreskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> ChokeSlam = getWrestlerdata().ChokeSlam;
		HashMap<UUID, Integer> TakeDown = getWrestlerdata().TakeDown;
		HashMap<UUID, Integer> Suplex = getWrestlerdata().Suplex;
		HashMap<UUID, Integer> GuillotineChoke = getWrestlerdata().GuillotineChoke;
		HashMap<UUID, Integer> GiantSwing = getWrestlerdata().GiantSwing;
		HashMap<UUID, Integer> Tackle = getWrestlerdata().Tackle;
		HashMap<UUID, Integer> ArmThrow = getWrestlerdata().ArmThrow;
		HashMap<UUID, Integer> ForeArmSmash = getWrestlerdata().ForeArmSmash;
		HashMap<UUID, Integer> SkillPoints = getWrestlerdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
		        
			}
			else
			{	

				ChokeSlam.put(p.getUniqueId(), 0);
				TakeDown.put(p.getUniqueId(), 0);
				Suplex.put(p.getUniqueId(), 0);
				GuillotineChoke.put(p.getUniqueId(), 0);
				ArmThrow.put(p.getUniqueId(), 0);
				Tackle.put(p.getUniqueId(), 0);
				GiantSwing.put(p.getUniqueId(), 0);
				ForeArmSmash.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Wreskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("WreSkills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				WreSkillsGui wrg = new WreSkillsGui();
				

				HashMap<UUID, Integer> ChokeSlam = getWrestlerdata().ChokeSlam;
				HashMap<UUID, Integer> TakeDown = getWrestlerdata().TakeDown;
				HashMap<UUID, Integer> Suplex = getWrestlerdata().Suplex;
				HashMap<UUID, Integer> GuillotineChoke = getWrestlerdata().GuillotineChoke;
				HashMap<UUID, Integer> GiantSwing = getWrestlerdata().GiantSwing;
				HashMap<UUID, Integer> Tackle = getWrestlerdata().Tackle;
				HashMap<UUID, Integer> ArmThrow = getWrestlerdata().ArmThrow;
				HashMap<UUID, Integer> ForeArmSmash = getWrestlerdata().ForeArmSmash;
				HashMap<UUID, Integer> SkillPoints = getWrestlerdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "ChokeSlam":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ChokeSlam.get(p.getUniqueId())<50){
								ChokeSlam.put(p.getUniqueId(), ChokeSlam.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ChokeSlam.get(p.getUniqueId()) >= 1){
								ChokeSlam.put(p.getUniqueId(), ChokeSlam.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ChokeSlam.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(ChokeSlam.get(p.getUniqueId())<50){
										final int a = 50 - ChokeSlam.get(p.getUniqueId());
										ChokeSlam.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);				
									}
								}
								else{
									ChokeSlam.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ChokeSlam.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ChokeSlam.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ChokeSlam.get(p.getUniqueId()));
								ChokeSlam.put(p.getUniqueId(), 0);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
						
					case "TakeDown":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								TakeDown.put(p.getUniqueId(), TakeDown.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(TakeDown.get(p.getUniqueId()) >= 1){
								TakeDown.put(p.getUniqueId(), TakeDown.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								TakeDown.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+TakeDown.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(TakeDown.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+TakeDown.get(p.getUniqueId()));
								TakeDown.put(p.getUniqueId(), 0);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}		
					case "Suplex":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Suplex.get(p.getUniqueId())<50){
								Suplex.put(p.getUniqueId(), Suplex.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Suplex.get(p.getUniqueId()) >= 1){
								Suplex.put(p.getUniqueId(), Suplex.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Suplex.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Suplex.get(p.getUniqueId())<50){
										final int a = 50 - Suplex.get(p.getUniqueId());
										Suplex.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);				
									}
								}
								else{
									Suplex.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Suplex.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Suplex.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Suplex.get(p.getUniqueId()));
								Suplex.put(p.getUniqueId(), 0);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
					case "Tackle":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Tackle.get(p.getUniqueId())<1){
								Tackle.put(p.getUniqueId(), Tackle.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Tackle.get(p.getUniqueId()) >= 1){
								Tackle.put(p.getUniqueId(), Tackle.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Tackle.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Tackle.get(p.getUniqueId())<1){
										final int a = 1 - Tackle.get(p.getUniqueId());
										Tackle.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Tackle.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Tackle.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Tackle.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Tackle.get(p.getUniqueId()));
								Tackle.put(p.getUniqueId(), 0);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
					case "GiantSwing":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && GiantSwing.get(p.getUniqueId())<50){
								GiantSwing.put(p.getUniqueId(), GiantSwing.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(GiantSwing.get(p.getUniqueId()) >= 1){
								GiantSwing.put(p.getUniqueId(), GiantSwing.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(GiantSwing.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(GiantSwing.get(p.getUniqueId())<50){
										final int a = 50 - GiantSwing.get(p.getUniqueId());
										GiantSwing.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									GiantSwing.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GiantSwing.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(GiantSwing.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GiantSwing.get(p.getUniqueId()));
								GiantSwing.put(p.getUniqueId(), 0);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
					case "ArmThrow":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ArmThrow.get(p.getUniqueId())<50){
								ArmThrow.put(p.getUniqueId(), ArmThrow.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ArmThrow.get(p.getUniqueId()) >= 1){
								ArmThrow.put(p.getUniqueId(), ArmThrow.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ArmThrow.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(ArmThrow.get(p.getUniqueId())<50){
										final int a = 50 - ArmThrow.get(p.getUniqueId());
										ArmThrow.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
									}
								}
								else{
									ArmThrow.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ArmThrow.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ArmThrow.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ArmThrow.get(p.getUniqueId()));
								ArmThrow.put(p.getUniqueId(), 0);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}				
					case "ForeArmSmash":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ForeArmSmash.get(p.getUniqueId())<50){
								ForeArmSmash.put(p.getUniqueId(), ForeArmSmash.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ForeArmSmash.get(p.getUniqueId()) >= 1){
								ForeArmSmash.put(p.getUniqueId(), ForeArmSmash.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ForeArmSmash.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(ForeArmSmash.get(p.getUniqueId())<50){
										final int a = 50 - ForeArmSmash.get(p.getUniqueId());
										ForeArmSmash.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);	
									}
								}
								else{
									ForeArmSmash.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ForeArmSmash.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ForeArmSmash.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ForeArmSmash.get(p.getUniqueId()));
								ForeArmSmash.put(p.getUniqueId(), 0);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
					case "GuillotineChoke":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && GuillotineChoke.get(p.getUniqueId())<50){
								GuillotineChoke.put(p.getUniqueId(), GuillotineChoke.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(GuillotineChoke.get(p.getUniqueId()) >= 1){
								GuillotineChoke.put(p.getUniqueId(), GuillotineChoke.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(GuillotineChoke.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(GuillotineChoke.get(p.getUniqueId())<50){
										final int a = 50 - GuillotineChoke.get(p.getUniqueId());
										GuillotineChoke.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);	
									}
								}
								else{
									GuillotineChoke.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GuillotineChoke.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(GuillotineChoke.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GuillotineChoke.get(p.getUniqueId()));
								GuillotineChoke.put(p.getUniqueId(), 0);
								new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":		{			
						ChokeSlam.put(p.getUniqueId(), 0);
						TakeDown.put(p.getUniqueId(), 0);
						Suplex.put(p.getUniqueId(), 0);
						GuillotineChoke.put(p.getUniqueId(), 0);
						ArmThrow.put(p.getUniqueId(), 0);
						Tackle.put(p.getUniqueId(), 0);
						GiantSwing.put(p.getUniqueId(), 0);
						ForeArmSmash.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new WreSkillsData(ChokeSlam, TakeDown, Suplex, GuillotineChoke, Tackle, GiantSwing, ArmThrow, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
				        wrg.WreSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public static WreSkillsData getWrestlerdata(){
        String path = new File("").getAbsolutePath();
        WreSkillsData data = new WreSkillsData(WreSkillsData.loadData(path +"/plugins/RPGskills/WreSkillsData.data"));
		return data;
	}
}
