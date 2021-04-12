package io.github.chw3021.illusionist;


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


public class IllSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 8076093416777617982L;
	public final HashMap<UUID, Integer> Switch;
	public final HashMap<UUID, Integer> Trick;
	public final HashMap<UUID, Integer> JackoLantern;
	public final HashMap<UUID, Integer> Distortion;
	public final HashMap<UUID, Integer> Paradox;
	public final HashMap<UUID, Integer> FakeDoll;
	public final HashMap<UUID, Integer> Surprise;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public IllSkillsData(HashMap<UUID, Integer> Switch, HashMap<UUID, Integer> Trick, HashMap<UUID, Integer> JackoLantern, HashMap<UUID, Integer> Distortion, HashMap<UUID, Integer> Paradox, HashMap<UUID, Integer> FakeDoll, HashMap<UUID, Integer> Surprise, HashMap<UUID, Integer> SkillPoints) {
    	this.Switch = Switch;
    	this.Trick = Trick;
    	this.JackoLantern = JackoLantern;
    	this.Distortion = Distortion;
    	this.Paradox = Paradox;
    	this.FakeDoll = FakeDoll;
    	this.Surprise = Surprise;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public IllSkillsData(IllSkillsData loadedData) {
    	this.Switch = loadedData.Switch;
    	this.Trick = loadedData.Trick;
    	this.JackoLantern = loadedData.JackoLantern;
    	this.Distortion = loadedData.Distortion;
    	this.Paradox = loadedData.Paradox;
    	this.FakeDoll = loadedData.FakeDoll;
    	this.Surprise = loadedData.Surprise;
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
    public static IllSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            IllSkillsData data = (IllSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block

            String path = new File("").getAbsolutePath();
        	
    		new IllSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
            
            e.printStackTrace();
            return null;
        }
    }
    

	@EventHandler
	public void Illskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Illskills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getIllmandata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> Switch = getIllmandata().Switch;
					HashMap<UUID, Integer> Trick = getIllmandata().Trick;
					HashMap<UUID, Integer> JackoLantern = getIllmandata().JackoLantern;
					HashMap<UUID, Integer> Distortion = getIllmandata().Distortion;
					HashMap<UUID, Integer> Paradox = getIllmandata().Paradox;
					HashMap<UUID, Integer> FakeDoll = getIllmandata().FakeDoll;
					HashMap<UUID, Integer> Surprise = getIllmandata().Surprise;
					HashMap<UUID, Integer> SkillPoints = getIllmandata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel());
					Switch.put(p.getUniqueId(), 0);
					Trick.put(p.getUniqueId(), 0);
					JackoLantern.put(p.getUniqueId(), 0);
					Distortion.put(p.getUniqueId(), 0);
					Paradox.put(p.getUniqueId(), 0);
					FakeDoll.put(p.getUniqueId(), 0);
					Surprise.put(p.getUniqueId(), 0);
					new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");

					IllSkillsGui ilg = new IllSkillsGui();
					ilg.IllSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Switch = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Trick = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> JackoLantern = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Distortion = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Paradox = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> FakeDoll = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Surprise = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel());
				Switch.put(p.getUniqueId(), 0);
				Trick.put(p.getUniqueId(), 0);
				JackoLantern.put(p.getUniqueId(), 0);
				Distortion.put(p.getUniqueId(), 0);
				Paradox.put(p.getUniqueId(), 0);
				FakeDoll.put(p.getUniqueId(), 0);
				Surprise.put(p.getUniqueId(), 0);
				new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");

				IllSkillsGui ilg = new IllSkillsGui();
				ilg.IllSkillsinv(p);
			}
		}
	}
	
    
	@EventHandler
	public void Illskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> Switch = getIllmandata().Switch;
		HashMap<UUID, Integer> Trick = getIllmandata().Trick;
		HashMap<UUID, Integer> JackoLantern = getIllmandata().JackoLantern;
		HashMap<UUID, Integer> Distortion = getIllmandata().Distortion;
		HashMap<UUID, Integer> Paradox = getIllmandata().Paradox;
		HashMap<UUID, Integer> FakeDoll = getIllmandata().FakeDoll;
		HashMap<UUID, Integer> Surprise = getIllmandata().Surprise;
		HashMap<UUID, Integer> SkillPoints = getIllmandata().SkillPoints;
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
		        
			}
			else
			{	

				Switch.put(p.getUniqueId(), 0);
				Trick.put(p.getUniqueId(), 0);
				JackoLantern.put(p.getUniqueId(), 0);
				Distortion.put(p.getUniqueId(), 0);
				FakeDoll.put(p.getUniqueId(), 0);
				Paradox.put(p.getUniqueId(), 0);
				Surprise.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel());
				new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Illskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Illskills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				IllSkillsGui ilg = new IllSkillsGui();
				

				HashMap<UUID, Integer> Switch = getIllmandata().Switch;
				HashMap<UUID, Integer> Trick = getIllmandata().Trick;
				HashMap<UUID, Integer> JackoLantern = getIllmandata().JackoLantern;
				HashMap<UUID, Integer> Distortion = getIllmandata().Distortion;
				HashMap<UUID, Integer> Paradox = getIllmandata().Paradox;
				HashMap<UUID, Integer> FakeDoll = getIllmandata().FakeDoll;
				HashMap<UUID, Integer> Surprise = getIllmandata().Surprise;
				HashMap<UUID, Integer> SkillPoints = getIllmandata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Switch":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Switch.get(p.getUniqueId())<50){
								Switch.put(p.getUniqueId(), Switch.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Switch.get(p.getUniqueId()) >= 1){
								Switch.put(p.getUniqueId(), Switch.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Switch.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Switch.get(p.getUniqueId())<50){
										Switch.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Switch.get(p.getUniqueId()));								
									}
								}
								else{
									Switch.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Switch.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Switch.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Switch.get(p.getUniqueId()));
								Switch.put(p.getUniqueId(), 0);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						break;		}
						
					case "Trick":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Trick.get(p.getUniqueId())<50){
								Trick.put(p.getUniqueId(), Trick.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Trick.get(p.getUniqueId()) >= 1){
								Trick.put(p.getUniqueId(), Trick.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Trick.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Trick.get(p.getUniqueId())<50){
										Trick.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Trick.get(p.getUniqueId()));								
									}
								}
								else{
									Trick.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Trick.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Trick.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Trick.get(p.getUniqueId()));
								Trick.put(p.getUniqueId(), 0);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						break;		}		
					case "JackoLantern":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && JackoLantern.get(p.getUniqueId())<50){
								JackoLantern.put(p.getUniqueId(), JackoLantern.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(JackoLantern.get(p.getUniqueId()) >= 1){
								JackoLantern.put(p.getUniqueId(), JackoLantern.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(JackoLantern.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(JackoLantern.get(p.getUniqueId())<50){
										JackoLantern.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - JackoLantern.get(p.getUniqueId()));								
									}
								}
								else{
									JackoLantern.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+JackoLantern.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(JackoLantern.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+JackoLantern.get(p.getUniqueId()));
								JackoLantern.put(p.getUniqueId(), 0);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						break;		}
					case "Paradox":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Paradox.get(p.getUniqueId()) < 1){
								Paradox.put(p.getUniqueId(), Paradox.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Paradox.get(p.getUniqueId()) >= 1){
								Paradox.put(p.getUniqueId(), Paradox.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Paradox.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Paradox.get(p.getUniqueId()) < 1) {
										Paradox.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Paradox.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Paradox.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Paradox.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Paradox.get(p.getUniqueId()));
								Paradox.put(p.getUniqueId(), 0);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						break;		}
					case "FakeDoll":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && FakeDoll.get(p.getUniqueId()) < 1){
								FakeDoll.put(p.getUniqueId(), FakeDoll.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(FakeDoll.get(p.getUniqueId()) >= 1){
								FakeDoll.put(p.getUniqueId(), FakeDoll.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(FakeDoll.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(FakeDoll.get(p.getUniqueId()) < 1) {
										FakeDoll.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									FakeDoll.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+FakeDoll.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(FakeDoll.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+FakeDoll.get(p.getUniqueId()));
								FakeDoll.put(p.getUniqueId(), 0);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						break;		}				
					case "Surprise":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Surprise.put(p.getUniqueId(), Surprise.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Surprise.get(p.getUniqueId()) >= 1){
								Surprise.put(p.getUniqueId(), Surprise.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Surprise.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Surprise.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Surprise.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Surprise.get(p.getUniqueId()));
								Surprise.put(p.getUniqueId(), 0);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						break;		}
					case "Distortion":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Distortion.get(p.getUniqueId())<50){
								Distortion.put(p.getUniqueId(), Distortion.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Distortion.get(p.getUniqueId()) >= 1){
								Distortion.put(p.getUniqueId(), Distortion.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Distortion.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Distortion.get(p.getUniqueId())<50){
										Distortion.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Distortion.get(p.getUniqueId()));								
									}
								}
								else{
									Distortion.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Distortion.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Distortion.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Distortion.get(p.getUniqueId()));
								Distortion.put(p.getUniqueId(), 0);
								new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
						        ilg.IllSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":		{			
						Switch.put(p.getUniqueId(), 0);
						Trick.put(p.getUniqueId(), 0);
						JackoLantern.put(p.getUniqueId(), 0);
						Distortion.put(p.getUniqueId(), 0);
						FakeDoll.put(p.getUniqueId(), 0);
						Paradox.put(p.getUniqueId(), 0);
						Switch.put(p.getUniqueId(), 0);
						Surprise.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel());
						new IllSkillsData(Switch, Trick, JackoLantern, Distortion, Paradox, FakeDoll, Surprise, SkillPoints).saveData(path +"/plugins/RPGskills/IllSkillsData.data");
				        ilg.IllSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public IllSkillsData getIllmandata(){
        String path = new File("").getAbsolutePath();
        IllSkillsData data = new IllSkillsData(IllSkillsData.loadData(path +"/plugins/RPGskills/IllSkillsData.data"));
		return data;
	}
}
