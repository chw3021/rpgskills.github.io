package io.github.chw3021.classes.tamer;


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

import io.github.chw3021.items.ScrollPoint;



public class TamSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -3897832633978031556L;
	public final HashMap<UUID, Integer> Taming;
	public final HashMap<UUID, Integer> Spidey;
	public final HashMap<UUID, Integer> Pets;
	public final HashMap<UUID, Integer> BeeHive;
	public final HashMap<UUID, Integer> CreepBomb;
	public final HashMap<UUID, Integer> PandaSweep;
	public final HashMap<UUID, Integer> PressTheAttack;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public TamSkillsData(HashMap<UUID, Integer> Taming, HashMap<UUID, Integer> Spidey, HashMap<UUID, Integer> Pets, HashMap<UUID, Integer> BeeHive, HashMap<UUID, Integer> CreepBomb, HashMap<UUID, Integer> PandaSweep, HashMap<UUID, Integer> PressTheAttack, HashMap<UUID, Integer> SkillPoints) {
    	this.Taming = Taming;
    	this.Spidey = Spidey;
    	this.Pets = Pets;
    	this.BeeHive = BeeHive;
    	this.CreepBomb = CreepBomb;
    	this.PandaSweep = PandaSweep;
    	this.PressTheAttack = PressTheAttack;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public TamSkillsData(TamSkillsData loadedData) {
    	this.Taming = loadedData.Taming;
    	this.Spidey = loadedData.Spidey;
    	this.Pets = loadedData.Pets;
    	this.BeeHive = loadedData.BeeHive;
    	this.CreepBomb = loadedData.CreepBomb;
    	this.PandaSweep = loadedData.PandaSweep;
    	this.PressTheAttack = loadedData.PressTheAttack;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public TamSkillsData saveData(String filePath) {
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
    public static TamSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            TamSkillsData data = (TamSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            TamSkillsData data = new TamSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
            
            return data;
        }
    }


	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Taming = getTamstlerdata().Taming;
		HashMap<UUID, Integer> Spidey = getTamstlerdata().Spidey;
		HashMap<UUID, Integer> Pets = getTamstlerdata().Pets;
		HashMap<UUID, Integer> CreepBomb = getTamstlerdata().CreepBomb;
		HashMap<UUID, Integer> BeeHive = getTamstlerdata().BeeHive;
		HashMap<UUID, Integer> PandaSweep = getTamstlerdata().PandaSweep;
		HashMap<UUID, Integer> PressTheAttack = getTamstlerdata().PressTheAttack;
		HashMap<UUID, Integer> SkillPoints = getTamstlerdata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		
		new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");

	}

	@EventHandler
	public void Tamskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("TamSkills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getTamstlerdata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> Taming = getTamstlerdata().Taming;
					HashMap<UUID, Integer> Spidey = getTamstlerdata().Spidey;
					HashMap<UUID, Integer> Pets = getTamstlerdata().Pets;
					HashMap<UUID, Integer> CreepBomb = getTamstlerdata().CreepBomb;
					HashMap<UUID, Integer> BeeHive = getTamstlerdata().BeeHive;
					HashMap<UUID, Integer> PandaSweep = getTamstlerdata().PandaSweep;
					HashMap<UUID, Integer> PressTheAttack = getTamstlerdata().PressTheAttack;
					HashMap<UUID, Integer> SkillPoints = getTamstlerdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					Taming.put(p.getUniqueId(), 0);
					Spidey.put(p.getUniqueId(), 0);
					Pets.put(p.getUniqueId(), 0);
					BeeHive.put(p.getUniqueId(), 0);
					CreepBomb.put(p.getUniqueId(), 0);
					PandaSweep.put(p.getUniqueId(), 0);
					PressTheAttack.put(p.getUniqueId(), 0);
					new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");

					TamSkillsGui tsg = new TamSkillsGui();
					tsg.TamSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Taming = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Spidey = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Pets = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> BeeHive = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> CreepBomb = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> PandaSweep = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> PressTheAttack = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				Taming.put(p.getUniqueId(), 0);
				Spidey.put(p.getUniqueId(), 0);
				Pets.put(p.getUniqueId(), 0);
				BeeHive.put(p.getUniqueId(), 0);
				CreepBomb.put(p.getUniqueId(), 0);
				PandaSweep.put(p.getUniqueId(), 0);
				PressTheAttack.put(p.getUniqueId(), 0);
				new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");

				TamSkillsGui tsg = new TamSkillsGui();
				tsg.TamSkillsinv(p);
			}
		}
	}
	
	@EventHandler
	public void Tamskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> Taming = getTamstlerdata().Taming;
		HashMap<UUID, Integer> Spidey = getTamstlerdata().Spidey;
		HashMap<UUID, Integer> Pets = getTamstlerdata().Pets;
		HashMap<UUID, Integer> CreepBomb = getTamstlerdata().CreepBomb;
		HashMap<UUID, Integer> BeeHive = getTamstlerdata().BeeHive;
		HashMap<UUID, Integer> PandaSweep = getTamstlerdata().PandaSweep;
		HashMap<UUID, Integer> PressTheAttack = getTamstlerdata().PressTheAttack;
		HashMap<UUID, Integer> SkillPoints = getTamstlerdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
		        
			}
			else
			{	
				Taming.put(p.getUniqueId(), 0);
				Spidey.put(p.getUniqueId(), 0);
				Pets.put(p.getUniqueId(), 0);
				PandaSweep.put(p.getUniqueId(), 0);
				BeeHive.put(p.getUniqueId(), 0);
				CreepBomb.put(p.getUniqueId(), 0);
				PressTheAttack.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Tamskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("TamSkills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				TamSkillsGui tsg = new TamSkillsGui();
				
				HashMap<UUID, Integer> Taming = getTamstlerdata().Taming;
				HashMap<UUID, Integer> Spidey = getTamstlerdata().Spidey;
				HashMap<UUID, Integer> Pets = getTamstlerdata().Pets;
				HashMap<UUID, Integer> CreepBomb = getTamstlerdata().CreepBomb;
				HashMap<UUID, Integer> BeeHive = getTamstlerdata().BeeHive;
				HashMap<UUID, Integer> PandaSweep = getTamstlerdata().PandaSweep;
				HashMap<UUID, Integer> PressTheAttack = getTamstlerdata().PressTheAttack;
				HashMap<UUID, Integer> SkillPoints = getTamstlerdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					case "Taming":
					case "조련":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Taming.put(p.getUniqueId(), Taming.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Taming.get(p.getUniqueId()) >= 1){
								Taming.put(p.getUniqueId(), Taming.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Taming.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Taming.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Taming.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Taming.get(p.getUniqueId()));
								Taming.put(p.getUniqueId(), 0);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						break;		}		
					case "Spidey":
					case "스파이디":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Spidey.get(p.getUniqueId())<50){
								Spidey.put(p.getUniqueId(), Spidey.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Spidey.get(p.getUniqueId()) >= 1){
								Spidey.put(p.getUniqueId(), Spidey.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Spidey.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Spidey.get(p.getUniqueId())<50){
										final int a = 50 - Spidey.get(p.getUniqueId());
										Spidey.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);					
									}
								}
								else{
									Spidey.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Spidey.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Spidey.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Spidey.get(p.getUniqueId()));
								Spidey.put(p.getUniqueId(), 0);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						break;		}
					case "BeeHive":
					case "벌집":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && BeeHive.get(p.getUniqueId())<50){
								BeeHive.put(p.getUniqueId(), BeeHive.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(BeeHive.get(p.getUniqueId()) >= 1){
								BeeHive.put(p.getUniqueId(), BeeHive.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(BeeHive.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(BeeHive.get(p.getUniqueId())<50){
										final int a = 50 - BeeHive.get(p.getUniqueId());
										BeeHive.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									BeeHive.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+BeeHive.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(BeeHive.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+BeeHive.get(p.getUniqueId()));
								BeeHive.put(p.getUniqueId(), 0);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						break;		}
					case "CreepBomb":
					case "크리퍼폭탄":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && CreepBomb.get(p.getUniqueId())<50){
								CreepBomb.put(p.getUniqueId(), CreepBomb.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(CreepBomb.get(p.getUniqueId()) >= 1){
								CreepBomb.put(p.getUniqueId(), CreepBomb.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(CreepBomb.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(CreepBomb.get(p.getUniqueId())<50){
										final int a = 50 - CreepBomb.get(p.getUniqueId());
										CreepBomb.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									CreepBomb.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CreepBomb.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(CreepBomb.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CreepBomb.get(p.getUniqueId()));
								CreepBomb.put(p.getUniqueId(), 0);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						break;		}
					case "Panda":
					case "판다":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && PandaSweep.get(p.getUniqueId())<50){
								PandaSweep.put(p.getUniqueId(), PandaSweep.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(PandaSweep.get(p.getUniqueId()) >= 1){
								PandaSweep.put(p.getUniqueId(), PandaSweep.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(PandaSweep.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(PandaSweep.get(p.getUniqueId())<50){
										final int a = 50 - PandaSweep.get(p.getUniqueId());
										PandaSweep.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									PandaSweep.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+PandaSweep.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(PandaSweep.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+PandaSweep.get(p.getUniqueId()));
								PandaSweep.put(p.getUniqueId(), 0);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						break;		}				
					case "PressTheAttack":
					case "집중공격":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && PressTheAttack.get(p.getUniqueId()) < 1){
								PressTheAttack.put(p.getUniqueId(), PressTheAttack.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(PressTheAttack.get(p.getUniqueId()) >= 1){
								PressTheAttack.put(p.getUniqueId(), PressTheAttack.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(PressTheAttack.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(PressTheAttack.get(p.getUniqueId()) < 1) {
										PressTheAttack.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									PressTheAttack.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+PressTheAttack.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(PressTheAttack.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+PressTheAttack.get(p.getUniqueId()));
								PressTheAttack.put(p.getUniqueId(), 0);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						break;		}
					case "Pets":
					case "반려동물":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Pets.get(p.getUniqueId())<50){
								Pets.put(p.getUniqueId(), Pets.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Pets.get(p.getUniqueId()) >= 1){
								Pets.put(p.getUniqueId(), Pets.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Pets.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Pets.get(p.getUniqueId())<50){
										final int a = 50 - Pets.get(p.getUniqueId());
										Pets.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Pets.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Pets.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Pets.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Pets.get(p.getUniqueId()));
								Pets.put(p.getUniqueId(), 0);
								new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
						        tsg.TamSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						Taming.put(p.getUniqueId(), 0);
						Spidey.put(p.getUniqueId(), 0);
						Pets.put(p.getUniqueId(), 0);
						PandaSweep.put(p.getUniqueId(), 0);
						BeeHive.put(p.getUniqueId(), 0);
						CreepBomb.put(p.getUniqueId(), 0);
						PressTheAttack.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new TamSkillsData(Taming, Spidey, Pets, BeeHive, CreepBomb, PandaSweep, PressTheAttack, SkillPoints).saveData(path +"/plugins/RPGskills/TamSkillsData.data");
				        tsg.TamSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public static TamSkillsData getTamstlerdata(){
        String path = new File("").getAbsolutePath();
        TamSkillsData data = new TamSkillsData(TamSkillsData.loadData(path +"/plugins/RPGskills/TamSkillsData.data"));
		return data;
	}
}
