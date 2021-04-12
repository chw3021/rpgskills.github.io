package io.github.chw3021.wreltler;


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


public class WreSkillsData implements Serializable, Listener{
	


	/**
	 * 
	 */
	private static transient final long serialVersionUID = 4128358746416785054L;
	public final HashMap<UUID, Integer> TackleRush;
	public final HashMap<UUID, Integer> Chopping;
	public final HashMap<UUID, Integer> ChainingSuplex;
	public final HashMap<UUID, Integer> BodyPress;
	public final HashMap<UUID, Integer> LegDrop;
	public final HashMap<UUID, Integer> GiantSwing;
	public final HashMap<UUID, Integer> ChokeSlam;
	public final HashMap<UUID, Integer> ForeArmSmash;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public WreSkillsData(HashMap<UUID, Integer> TackleRush, HashMap<UUID, Integer> Chopping, HashMap<UUID, Integer> ChainingSuplex, HashMap<UUID, Integer> BodyPress, HashMap<UUID, Integer> LegDrop, HashMap<UUID, Integer> GiantSwing, HashMap<UUID, Integer> ChokeSlam, HashMap<UUID, Integer> ForeArmSmash, HashMap<UUID, Integer> SkillPoints) {
    	this.TackleRush = TackleRush;
    	this.Chopping = Chopping;
    	this.ChainingSuplex = ChainingSuplex;
    	this.BodyPress = BodyPress;
    	this.LegDrop = LegDrop;
    	this.GiantSwing = GiantSwing;
    	this.ChokeSlam = ChokeSlam;
    	this.ForeArmSmash = ForeArmSmash;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public WreSkillsData(WreSkillsData loadedData) {
    	this.TackleRush = loadedData.TackleRush;
    	this.Chopping = loadedData.Chopping;
    	this.ChainingSuplex = loadedData.ChainingSuplex;
    	this.BodyPress = loadedData.BodyPress;
    	this.LegDrop = loadedData.LegDrop;
    	this.GiantSwing = loadedData.GiantSwing;
    	this.ChokeSlam = loadedData.ChokeSlam;
    	this.ForeArmSmash = loadedData.ForeArmSmash;
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
    public static WreSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            WreSkillsData data = (WreSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
    		new WreSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
            
            e.printStackTrace();
            return null;
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
					HashMap<UUID, Integer> TackleRush = getWrestlerdata().TackleRush;
					HashMap<UUID, Integer> Chopping = getWrestlerdata().Chopping;
					HashMap<UUID, Integer> ChainingSuplex = getWrestlerdata().ChainingSuplex;
					HashMap<UUID, Integer> BodyPress = getWrestlerdata().BodyPress;
					HashMap<UUID, Integer> GiantSwing = getWrestlerdata().GiantSwing;
					HashMap<UUID, Integer> LegDrop = getWrestlerdata().LegDrop;
					HashMap<UUID, Integer> ChokeSlam = getWrestlerdata().ChokeSlam;
					HashMap<UUID, Integer> ForeArmSmash = getWrestlerdata().ForeArmSmash;
					HashMap<UUID, Integer> SkillPoints = getWrestlerdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel());
					TackleRush.put(p.getUniqueId(), 0);
					Chopping.put(p.getUniqueId(), 0);
					ChainingSuplex.put(p.getUniqueId(), 0);
					BodyPress.put(p.getUniqueId(), 0);
					LegDrop.put(p.getUniqueId(), 0);
					GiantSwing.put(p.getUniqueId(), 0);
					ChokeSlam.put(p.getUniqueId(), 0);
					ForeArmSmash.put(p.getUniqueId(), 0);
					new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");

					WreSkillsGui wrg = new WreSkillsGui();
					wrg.WreSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> TackleRush = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Chopping = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> ChainingSuplex = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> BodyPress = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> LegDrop = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> GiantSwing = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> ChokeSlam = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> ForeArmSmash = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel());
				TackleRush.put(p.getUniqueId(), 0);
				Chopping.put(p.getUniqueId(), 0);
				ChainingSuplex.put(p.getUniqueId(), 0);
				BodyPress.put(p.getUniqueId(), 0);
				LegDrop.put(p.getUniqueId(), 0);
				GiantSwing.put(p.getUniqueId(), 0);
				ChokeSlam.put(p.getUniqueId(), 0);
				ForeArmSmash.put(p.getUniqueId(), 0);
				new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");

				WreSkillsGui wrg = new WreSkillsGui();
				wrg.WreSkillsinv(p);
			}
		}
	}
	
    
	@EventHandler
	public void Wreskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> TackleRush = getWrestlerdata().TackleRush;
		HashMap<UUID, Integer> Chopping = getWrestlerdata().Chopping;
		HashMap<UUID, Integer> ChainingSuplex = getWrestlerdata().ChainingSuplex;
		HashMap<UUID, Integer> BodyPress = getWrestlerdata().BodyPress;
		HashMap<UUID, Integer> GiantSwing = getWrestlerdata().GiantSwing;
		HashMap<UUID, Integer> LegDrop = getWrestlerdata().LegDrop;
		HashMap<UUID, Integer> ChokeSlam = getWrestlerdata().ChokeSlam;
		HashMap<UUID, Integer> ForeArmSmash = getWrestlerdata().ForeArmSmash;
		HashMap<UUID, Integer> SkillPoints = getWrestlerdata().SkillPoints;
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
		        
			}
			else
			{	

				TackleRush.put(p.getUniqueId(), 0);
				Chopping.put(p.getUniqueId(), 0);
				ChainingSuplex.put(p.getUniqueId(), 0);
				BodyPress.put(p.getUniqueId(), 0);
				ChokeSlam.put(p.getUniqueId(), 0);
				LegDrop.put(p.getUniqueId(), 0);
				GiantSwing.put(p.getUniqueId(), 0);
				ForeArmSmash.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel());
				new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
		        
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
				

				HashMap<UUID, Integer> TackleRush = getWrestlerdata().TackleRush;
				HashMap<UUID, Integer> Chopping = getWrestlerdata().Chopping;
				HashMap<UUID, Integer> ChainingSuplex = getWrestlerdata().ChainingSuplex;
				HashMap<UUID, Integer> BodyPress = getWrestlerdata().BodyPress;
				HashMap<UUID, Integer> GiantSwing = getWrestlerdata().GiantSwing;
				HashMap<UUID, Integer> LegDrop = getWrestlerdata().LegDrop;
				HashMap<UUID, Integer> ChokeSlam = getWrestlerdata().ChokeSlam;
				HashMap<UUID, Integer> ForeArmSmash = getWrestlerdata().ForeArmSmash;
				HashMap<UUID, Integer> SkillPoints = getWrestlerdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "TackleRush":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && TackleRush.get(p.getUniqueId()) < 1){
								TackleRush.put(p.getUniqueId(), TackleRush.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(TackleRush.get(p.getUniqueId()) >= 1){
								TackleRush.put(p.getUniqueId(), TackleRush.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(TackleRush.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(TackleRush.get(p.getUniqueId()) < 1) {
										TackleRush.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									TackleRush.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+TackleRush.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(TackleRush.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+TackleRush.get(p.getUniqueId()));
								TackleRush.put(p.getUniqueId(), 0);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
						
					case "Chopping":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Chopping.put(p.getUniqueId(), Chopping.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Chopping.get(p.getUniqueId()) >= 1){
								Chopping.put(p.getUniqueId(), Chopping.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Chopping.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Chopping.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Chopping.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Chopping.get(p.getUniqueId()));
								Chopping.put(p.getUniqueId(), 0);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}		
					case "ChainingSuplex":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ChainingSuplex.get(p.getUniqueId())<50){
								ChainingSuplex.put(p.getUniqueId(), ChainingSuplex.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ChainingSuplex.get(p.getUniqueId()) >= 1){
								ChainingSuplex.put(p.getUniqueId(), ChainingSuplex.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ChainingSuplex.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(ChainingSuplex.get(p.getUniqueId())<50){
										ChainingSuplex.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - ChainingSuplex.get(p.getUniqueId()));								
									}
								}
								else{
									ChainingSuplex.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ChainingSuplex.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ChainingSuplex.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ChainingSuplex.get(p.getUniqueId()));
								ChainingSuplex.put(p.getUniqueId(), 0);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
					case "Lock":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && LegDrop.get(p.getUniqueId())<50){
								LegDrop.put(p.getUniqueId(), LegDrop.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(LegDrop.get(p.getUniqueId()) >= 1){
								LegDrop.put(p.getUniqueId(), LegDrop.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(LegDrop.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(LegDrop.get(p.getUniqueId())<50){
										LegDrop.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - LegDrop.get(p.getUniqueId()));								
									}
								}
								else{
									LegDrop.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+LegDrop.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(LegDrop.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+LegDrop.get(p.getUniqueId()));
								LegDrop.put(p.getUniqueId(), 0);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
					case "GiantSwing":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && GiantSwing.get(p.getUniqueId())<50){
								GiantSwing.put(p.getUniqueId(), GiantSwing.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(GiantSwing.get(p.getUniqueId()) >= 1){
								GiantSwing.put(p.getUniqueId(), GiantSwing.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(GiantSwing.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(GiantSwing.get(p.getUniqueId())<50){
										GiantSwing.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - GiantSwing.get(p.getUniqueId()));								
									}
								}
								else{
									GiantSwing.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GiantSwing.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(GiantSwing.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GiantSwing.get(p.getUniqueId()));
								GiantSwing.put(p.getUniqueId(), 0);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
					case "ChokeSlam":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ChokeSlam.get(p.getUniqueId())<50){
								ChokeSlam.put(p.getUniqueId(), ChokeSlam.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ChokeSlam.get(p.getUniqueId()) >= 1){
								ChokeSlam.put(p.getUniqueId(), ChokeSlam.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ChokeSlam.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(ChokeSlam.get(p.getUniqueId())<50){
										ChokeSlam.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - ChokeSlam.get(p.getUniqueId()));								
									}
								}
								else{
									ChokeSlam.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ChokeSlam.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ChokeSlam.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ChokeSlam.get(p.getUniqueId()));
								ChokeSlam.put(p.getUniqueId(), 0);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}				
					case "ForeArmSmash":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ForeArmSmash.get(p.getUniqueId()) < 1){
								ForeArmSmash.put(p.getUniqueId(), ForeArmSmash.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ForeArmSmash.get(p.getUniqueId()) >= 1){
								ForeArmSmash.put(p.getUniqueId(), ForeArmSmash.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ForeArmSmash.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(ForeArmSmash.get(p.getUniqueId()) < 1) {
										ForeArmSmash.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									ForeArmSmash.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ForeArmSmash.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ForeArmSmash.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ForeArmSmash.get(p.getUniqueId()));
								ForeArmSmash.put(p.getUniqueId(), 0);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
					case "BodyPress":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && BodyPress.get(p.getUniqueId())<50){
								BodyPress.put(p.getUniqueId(), BodyPress.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(BodyPress.get(p.getUniqueId()) >= 1){
								BodyPress.put(p.getUniqueId(), BodyPress.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(BodyPress.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(BodyPress.get(p.getUniqueId())<50){
										BodyPress.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - BodyPress.get(p.getUniqueId()));								
									}
								}
								else{
									BodyPress.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+BodyPress.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(BodyPress.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+BodyPress.get(p.getUniqueId()));
								BodyPress.put(p.getUniqueId(), 0);
								new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
						        wrg.WreSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":		{			
						TackleRush.put(p.getUniqueId(), 0);
						Chopping.put(p.getUniqueId(), 0);
						ChainingSuplex.put(p.getUniqueId(), 0);
						BodyPress.put(p.getUniqueId(), 0);
						ChokeSlam.put(p.getUniqueId(), 0);
						LegDrop.put(p.getUniqueId(), 0);
						TackleRush.put(p.getUniqueId(), 0);
						GiantSwing.put(p.getUniqueId(), 0);
						ForeArmSmash.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel());
						new WreSkillsData(TackleRush, Chopping, ChainingSuplex, BodyPress, LegDrop, GiantSwing, ChokeSlam, ForeArmSmash, SkillPoints).saveData(path +"/plugins/RPGskills/WreSkillsData.data");
				        wrg.WreSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public WreSkillsData getWrestlerdata(){
        String path = new File("").getAbsolutePath();
        WreSkillsData data = new WreSkillsData(WreSkillsData.loadData(path +"/plugins/RPGskills/WreSkillsData.data"));
		return data;
	}
}
