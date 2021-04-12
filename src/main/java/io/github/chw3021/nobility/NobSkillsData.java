package io.github.chw3021.nobility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import io.github.chw3021.boxer.BoxSkillsData;
import io.github.chw3021.classes.ClassData;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class NobSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -353355589904236766L;
	public final HashMap<UUID, Integer> Assault;
	public final HashMap<UUID, Integer> Owner;
	public final HashMap<UUID, Integer> Storm;
	public final HashMap<UUID, Integer> GuardianSupport;
	public final HashMap<UUID, Integer> Transition;
	public final HashMap<UUID, Integer> DolphinSurf;
	public final HashMap<UUID, Integer> MarkOfSea;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public NobSkillsData(HashMap<UUID, Integer> Assault, HashMap<UUID, Integer> Owner, HashMap<UUID, Integer> Storm, HashMap<UUID, Integer> GuardianSupport, HashMap<UUID, Integer> Transition, HashMap<UUID, Integer> DolphinSurf, HashMap<UUID, Integer> MarkOfSea, HashMap<UUID, Integer> SkillPoints) {
    	this.Assault = Assault;
    	this.Owner = Owner;
    	this.Storm = Storm;
    	this.GuardianSupport = GuardianSupport;
    	this.Transition = Transition;
    	this.DolphinSurf = DolphinSurf;
    	this.MarkOfSea = MarkOfSea;
    	this.SkillPoints = SkillPoints;
    	}
    public NobSkillsData(NobSkillsData loadedData) {
    	this.Assault = loadedData.Assault;
    	this.Owner = loadedData.Owner;
    	this.Storm = loadedData.Storm;
    	this.GuardianSupport = loadedData.GuardianSupport;
    	this.Transition = loadedData.Transition;
    	this.DolphinSurf = loadedData.DolphinSurf;
    	this.MarkOfSea = loadedData.MarkOfSea;
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
    public static NobSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            NobSkillsData data = (NobSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
    		new NobSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),  new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
            
            e.printStackTrace();
            return null;
        }
    }
    

	@EventHandler
	public void Nobskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("NobSkills"))
		{
	        String path = new File("").getAbsolutePath();
			NobSkillsGui osg = new NobSkillsGui();
			try 
			{
				if(!getNobilitydata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> Assault = getNobilitydata().Assault;
					HashMap<UUID, Integer> Owner = getNobilitydata().Owner;
					HashMap<UUID, Integer> Storm = getNobilitydata().Storm;
					HashMap<UUID, Integer> GuardianSupport = getNobilitydata().GuardianSupport;
					HashMap<UUID, Integer> DolphinSurf = getNobilitydata().DolphinSurf;
					HashMap<UUID, Integer> Transition = getNobilitydata().Transition;
					HashMap<UUID, Integer> MarkOfSea = getNobilitydata().MarkOfSea;
					HashMap<UUID, Integer> SkillPoints = getNobilitydata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel());
					Assault.put(p.getUniqueId(), 0);
					Owner.put(p.getUniqueId(), 0);
					Storm.put(p.getUniqueId(), 0);
					GuardianSupport.put(p.getUniqueId(), 0);
					Transition.put(p.getUniqueId(), 0);
					DolphinSurf.put(p.getUniqueId(), 0);
					MarkOfSea.put(p.getUniqueId(), 0);
					new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");

					osg.NobSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Assault = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Owner = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Storm = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> GuardianSupport = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Transition = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> DolphinSurf = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> MarkOfSea = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel());
				Assault.put(p.getUniqueId(), 0);
				Owner.put(p.getUniqueId(), 0);
				Storm.put(p.getUniqueId(), 0);
				GuardianSupport.put(p.getUniqueId(), 0);
				Transition.put(p.getUniqueId(), 0);
				DolphinSurf.put(p.getUniqueId(), 0);
				MarkOfSea.put(p.getUniqueId(), 0);
				new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");

				osg.NobSkillsinv(p);
			}
		}
	}
    
	@EventHandler
	public void Nobskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> Assault = getNobilitydata().Assault;
		HashMap<UUID, Integer> Owner = getNobilitydata().Owner;
		HashMap<UUID, Integer> Storm = getNobilitydata().Storm;
		HashMap<UUID, Integer> GuardianSupport = getNobilitydata().GuardianSupport;
		HashMap<UUID, Integer> DolphinSurf = getNobilitydata().DolphinSurf;
		HashMap<UUID, Integer> Transition = getNobilitydata().Transition;
		HashMap<UUID, Integer> MarkOfSea = getNobilitydata().MarkOfSea;
		HashMap<UUID, Integer> SkillPoints = getNobilitydata().SkillPoints;
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
		        
			}
			else
			{	

				Assault.remove(p.getUniqueId());
				Assault.put(p.getUniqueId(), 0);
				Owner.remove(p.getUniqueId());
				Owner.put(p.getUniqueId(), 0);
				Storm.remove(p.getUniqueId());
				Storm.put(p.getUniqueId(), 0);
				GuardianSupport.remove(p.getUniqueId());
				GuardianSupport.put(p.getUniqueId(), 0);
				MarkOfSea.remove(p.getUniqueId());
				MarkOfSea.put(p.getUniqueId(), 0);
				Transition.remove(p.getUniqueId());
				Transition.put(p.getUniqueId(), 0);
				DolphinSurf.remove(p.getUniqueId());
				DolphinSurf.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel());
				new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Nobskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("NobSkills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				NobSkillsGui fsg = new NobSkillsGui();
				
				HashMap<UUID, Integer> Assault = getNobilitydata().Assault;
				HashMap<UUID, Integer> Owner = getNobilitydata().Owner;
				HashMap<UUID, Integer> Storm = getNobilitydata().Storm;
				HashMap<UUID, Integer> GuardianSupport = getNobilitydata().GuardianSupport;
				HashMap<UUID, Integer> DolphinSurf = getNobilitydata().DolphinSurf;
				HashMap<UUID, Integer> Transition = getNobilitydata().Transition;
				HashMap<UUID, Integer> MarkOfSea = getNobilitydata().MarkOfSea;
				HashMap<UUID, Integer> SkillPoints = getNobilitydata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Assault":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Assault.get(p.getUniqueId())<50){
								Assault.put(p.getUniqueId(), Assault.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Assault.get(p.getUniqueId()) >= 1){
								Assault.put(p.getUniqueId(), Assault.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Assault.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Assault.get(p.getUniqueId())<50){
										Assault.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  Assault.get(p.getUniqueId()));								
									}
								}
								else{
									Assault.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Assault.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Assault.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Assault.get(p.getUniqueId()));
								Assault.put(p.getUniqueId(), 0);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						break;		}
						
					case "Owner":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Owner.get(p.getUniqueId()) < 1){
								Owner.put(p.getUniqueId(), Owner.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Owner.get(p.getUniqueId()) >= 1){
								Owner.put(p.getUniqueId(), Owner.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Owner.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Owner.get(p.getUniqueId()) < 1) {
										Owner.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Owner.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Owner.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Owner.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Owner.get(p.getUniqueId()));
								Owner.put(p.getUniqueId(), 0);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						break;		}		
					case "Storm":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Storm.get(p.getUniqueId())<50){
								Storm.put(p.getUniqueId(), Storm.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Storm.get(p.getUniqueId()) >= 1){
								Storm.put(p.getUniqueId(), Storm.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Storm.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Storm.get(p.getUniqueId())<50){
										Storm.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  Storm.get(p.getUniqueId()));								
									}
								}
								else{
									Storm.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Storm.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Storm.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Storm.get(p.getUniqueId()));
								Storm.put(p.getUniqueId(), 0);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						break;		}
					case "Transition":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Transition.get(p.getUniqueId())<50){
								Transition.put(p.getUniqueId(), Transition.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Transition.get(p.getUniqueId()) >= 1){
								Transition.put(p.getUniqueId(), Transition.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Transition.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Transition.get(p.getUniqueId())<50){
										Transition.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  Transition.get(p.getUniqueId()));								
									}
								}
								else{
									Transition.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Transition.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Transition.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Transition.get(p.getUniqueId()));
								Transition.put(p.getUniqueId(), 0);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						break;		}
					case "DolphinSurf":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && DolphinSurf.get(p.getUniqueId())<50){
								DolphinSurf.put(p.getUniqueId(), DolphinSurf.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(DolphinSurf.get(p.getUniqueId()) >= 1){
								DolphinSurf.put(p.getUniqueId(), DolphinSurf.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(DolphinSurf.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(DolphinSurf.get(p.getUniqueId())<50){
										DolphinSurf.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  DolphinSurf.get(p.getUniqueId()));								
									}
								}
								else{
									DolphinSurf.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DolphinSurf.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(DolphinSurf.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DolphinSurf.get(p.getUniqueId()));
								DolphinSurf.put(p.getUniqueId(), 0);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						break;		}
					case "MarkOfSea":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								MarkOfSea.put(p.getUniqueId(), MarkOfSea.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(MarkOfSea.get(p.getUniqueId()) >= 1){
								MarkOfSea.put(p.getUniqueId(), MarkOfSea.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								MarkOfSea.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MarkOfSea.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(MarkOfSea.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MarkOfSea.get(p.getUniqueId()));
								MarkOfSea.put(p.getUniqueId(), 0);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						break;		}			
					case "GuardianSupport":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && GuardianSupport.get(p.getUniqueId()) < 20){
								GuardianSupport.put(p.getUniqueId(), GuardianSupport.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(GuardianSupport.get(p.getUniqueId()) >= 1){
								GuardianSupport.put(p.getUniqueId(), GuardianSupport.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(GuardianSupport.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>20) {
									if(GuardianSupport.get(p.getUniqueId()) < 20) {
										GuardianSupport.put(p.getUniqueId(), 20);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - GuardianSupport.get(p.getUniqueId()));										
									}							
								}
								else {
									GuardianSupport.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GuardianSupport.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(GuardianSupport.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+GuardianSupport.get(p.getUniqueId()));
								GuardianSupport.put(p.getUniqueId(), 0);
								new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
						        fsg.NobSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":		{			
						Assault.put(p.getUniqueId(), 0);
						Owner.put(p.getUniqueId(), 0);
						Storm.put(p.getUniqueId(), 0);
						GuardianSupport.put(p.getUniqueId(), 0);
						MarkOfSea.put(p.getUniqueId(), 0);
						Transition.put(p.getUniqueId(), 0);
						DolphinSurf.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel());
						new NobSkillsData(Assault, Owner, Storm, GuardianSupport, Transition, DolphinSurf, MarkOfSea, SkillPoints).saveData(path +"/plugins/RPGskills/NobSkillsData.data");
				        fsg.NobSkillsinv(p);
				}break;	}
			
			}
			
		}
		
	}
    
    public NobSkillsData getNobilitydata(){
        String path = new File("").getAbsolutePath();
        NobSkillsData data = new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));
		return data;
	}
}
