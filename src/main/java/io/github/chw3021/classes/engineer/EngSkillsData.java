package io.github.chw3021.classes.engineer;


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



public class EngSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 4834812353125242674L;
	public final HashMap<UUID, Integer> Magnetic;
	public final HashMap<UUID, Integer> Jetpack;
	public final HashMap<UUID, Integer> Graviton;
	public final HashMap<UUID, Integer> Electrostatic;
	public final HashMap<UUID, Integer> X_ray;
	public final HashMap<UUID, Integer> Dispenser;
	public final HashMap<UUID, Integer> CombatSuit;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public EngSkillsData(HashMap<UUID, Integer> Magnetic, HashMap<UUID, Integer> Jetpack, HashMap<UUID, Integer> Graviton, HashMap<UUID, Integer> Electrostatic, HashMap<UUID, Integer> X_ray, HashMap<UUID, Integer> Dispenser, HashMap<UUID, Integer> CombatSuit, HashMap<UUID, Integer> SkillPoints) {
    	this.Magnetic = Magnetic;
    	this.Jetpack = Jetpack;
    	this.Graviton = Graviton;
    	this.Electrostatic = Electrostatic;
    	this.X_ray = X_ray;
    	this.Dispenser = Dispenser;
    	this.CombatSuit = CombatSuit;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public EngSkillsData(EngSkillsData loadedData) {
    	this.Magnetic = loadedData.Magnetic;
    	this.Jetpack = loadedData.Jetpack;
    	this.Graviton = loadedData.Graviton;
    	this.Electrostatic = loadedData.Electrostatic;
    	this.X_ray = loadedData.X_ray;
    	this.Dispenser = loadedData.Dispenser;
    	this.CombatSuit = loadedData.CombatSuit;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public EngSkillsData saveData(String filePath) {
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
    public static EngSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            EngSkillsData data = (EngSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException  e) {
            // TODO Auto-generated catch block

            String path = new File("").getAbsolutePath();
        	
            EngSkillsData data = new EngSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
            
            return data;
        }
    }
    

	@EventHandler
	public void Engskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Engskills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getEngmandata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> Magnetic = getEngmandata().Magnetic;
					HashMap<UUID, Integer> Jetpack = getEngmandata().Jetpack;
					HashMap<UUID, Integer> Graviton = getEngmandata().Graviton;
					HashMap<UUID, Integer> Electrostatic = getEngmandata().Electrostatic;
					HashMap<UUID, Integer> X_ray = getEngmandata().X_ray;
					HashMap<UUID, Integer> Dispenser = getEngmandata().Dispenser;
					HashMap<UUID, Integer> CombatSuit = getEngmandata().CombatSuit;
					HashMap<UUID, Integer> SkillPoints = getEngmandata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					Magnetic.put(p.getUniqueId(), 0);
					Jetpack.put(p.getUniqueId(), 0);
					Graviton.put(p.getUniqueId(), 0);
					Electrostatic.put(p.getUniqueId(), 0);
					X_ray.put(p.getUniqueId(), 0);
					Dispenser.put(p.getUniqueId(), 0);
					CombatSuit.put(p.getUniqueId(), 0);
					new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");

					EngSkillsGui egg = new EngSkillsGui();
					egg.EngSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Magnetic = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Jetpack = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Graviton = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Electrostatic = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> X_ray = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Dispenser = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> CombatSuit = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				Magnetic.put(p.getUniqueId(), 0);
				Jetpack.put(p.getUniqueId(), 0);
				Graviton.put(p.getUniqueId(), 0);
				Electrostatic.put(p.getUniqueId(), 0);
				X_ray.put(p.getUniqueId(), 0);
				Dispenser.put(p.getUniqueId(), 0);
				CombatSuit.put(p.getUniqueId(), 0);
				new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");

				EngSkillsGui egg = new EngSkillsGui();
				egg.EngSkillsinv(p);
			}
		}
	}

	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> Magnetic = getEngmandata().Magnetic;
		HashMap<UUID, Integer> Jetpack = getEngmandata().Jetpack;
		HashMap<UUID, Integer> Graviton = getEngmandata().Graviton;
		HashMap<UUID, Integer> Electrostatic = getEngmandata().Electrostatic;
		HashMap<UUID, Integer> X_ray = getEngmandata().X_ray;
		HashMap<UUID, Integer> Dispenser = getEngmandata().Dispenser;
		HashMap<UUID, Integer> CombatSuit = getEngmandata().CombatSuit;
		HashMap<UUID, Integer> SkillPoints = getEngmandata().SkillPoints;
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		
		new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");

	}

    
	@EventHandler
	public void Engskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> Magnetic = getEngmandata().Magnetic;
		HashMap<UUID, Integer> Jetpack = getEngmandata().Jetpack;
		HashMap<UUID, Integer> Graviton = getEngmandata().Graviton;
		HashMap<UUID, Integer> Electrostatic = getEngmandata().Electrostatic;
		HashMap<UUID, Integer> X_ray = getEngmandata().X_ray;
		HashMap<UUID, Integer> Dispenser = getEngmandata().Dispenser;
		HashMap<UUID, Integer> CombatSuit = getEngmandata().CombatSuit;
		HashMap<UUID, Integer> SkillPoints = getEngmandata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
		        
			}
			else
			{	

				Magnetic.put(p.getUniqueId(), 0);
				Jetpack.put(p.getUniqueId(), 0);
				Graviton.put(p.getUniqueId(), 0);
				Electrostatic.put(p.getUniqueId(), 0);
				Dispenser.put(p.getUniqueId(), 0);
				X_ray.put(p.getUniqueId(), 0);
				CombatSuit.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Engskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Engskills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				EngSkillsGui egg = new EngSkillsGui();
				

				HashMap<UUID, Integer> Magnetic = getEngmandata().Magnetic;
				HashMap<UUID, Integer> Jetpack = getEngmandata().Jetpack;
				HashMap<UUID, Integer> Graviton = getEngmandata().Graviton;
				HashMap<UUID, Integer> Electrostatic = getEngmandata().Electrostatic;
				HashMap<UUID, Integer> X_ray = getEngmandata().X_ray;
				HashMap<UUID, Integer> Dispenser = getEngmandata().Dispenser;
				HashMap<UUID, Integer> CombatSuit = getEngmandata().CombatSuit;
				HashMap<UUID, Integer> SkillPoints = getEngmandata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Magnetic":
					case "자성":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Magnetic.get(p.getUniqueId()) < 1){
								Magnetic.put(p.getUniqueId(), Magnetic.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Magnetic.get(p.getUniqueId()) >= 1){
								Magnetic.put(p.getUniqueId(), Magnetic.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Magnetic.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Magnetic.get(p.getUniqueId()) < 1) {
										Magnetic.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Magnetic.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Magnetic.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Magnetic.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Magnetic.get(p.getUniqueId()));
								Magnetic.put(p.getUniqueId(), 0);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						break;		}
						
					case "Jetpack":
					case "제트팩":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Jetpack.get(p.getUniqueId()) < 1){
								Jetpack.put(p.getUniqueId(), Jetpack.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Jetpack.get(p.getUniqueId()) >= 1){
								Jetpack.put(p.getUniqueId(), Jetpack.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Jetpack.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Jetpack.get(p.getUniqueId()) < 1) {
										Jetpack.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Jetpack.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Jetpack.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Jetpack.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Jetpack.get(p.getUniqueId()));
								Jetpack.put(p.getUniqueId(), 0);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						break;		}		
					case "Graviton":
					case "중력자":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Graviton.get(p.getUniqueId())<50){
								Graviton.put(p.getUniqueId(), Graviton.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Graviton.get(p.getUniqueId()) >= 1){
								Graviton.put(p.getUniqueId(), Graviton.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Graviton.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Graviton.get(p.getUniqueId())<50){
										final int a = 50 - Graviton.get(p.getUniqueId());
										Graviton.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);								
									}
								}
								else{
									Graviton.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Graviton.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Graviton.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Graviton.get(p.getUniqueId()));
								Graviton.put(p.getUniqueId(), 0);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						break;		}
					case "X_ray":
					case "엑스선":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && X_ray.get(p.getUniqueId())<50){
								X_ray.put(p.getUniqueId(), X_ray.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(X_ray.get(p.getUniqueId()) >= 1){
								X_ray.put(p.getUniqueId(), X_ray.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(X_ray.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(X_ray.get(p.getUniqueId())<50){
										final int a = 50 - X_ray.get(p.getUniqueId());
										X_ray.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);									
									}
								}
								else{
									X_ray.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+X_ray.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(X_ray.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+X_ray.get(p.getUniqueId()));
								X_ray.put(p.getUniqueId(), 0);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						break;		}
					case "Dispenser":
					case "발사기":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Dispenser.get(p.getUniqueId())<50){
								Dispenser.put(p.getUniqueId(), Dispenser.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Dispenser.get(p.getUniqueId()) >= 1){
								Dispenser.put(p.getUniqueId(), Dispenser.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Dispenser.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Dispenser.get(p.getUniqueId())<50){
										final int a = 50 - Dispenser.get(p.getUniqueId());
										Dispenser.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);									
									}
								}
								else{
									Dispenser.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Dispenser.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Dispenser.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Dispenser.get(p.getUniqueId()));
								Dispenser.put(p.getUniqueId(), 0);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						break;		}				
					case "CombatSuit":
					case "전투슈트":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								CombatSuit.put(p.getUniqueId(), CombatSuit.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(CombatSuit.get(p.getUniqueId()) >= 1){
								CombatSuit.put(p.getUniqueId(), CombatSuit.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								CombatSuit.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CombatSuit.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(CombatSuit.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CombatSuit.get(p.getUniqueId()));
								CombatSuit.put(p.getUniqueId(), 0);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						break;		}
					case "Electrostatic":
					case "정전기장":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Electrostatic.get(p.getUniqueId())<50){
								Electrostatic.put(p.getUniqueId(), Electrostatic.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Electrostatic.get(p.getUniqueId()) >= 1){
								Electrostatic.put(p.getUniqueId(), Electrostatic.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Electrostatic.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Electrostatic.get(p.getUniqueId())<50){
										final int a = 50 - Electrostatic.get(p.getUniqueId());
										Electrostatic.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);					
									}
								}
								else{
									Electrostatic.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Electrostatic.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Electrostatic.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Electrostatic.get(p.getUniqueId()));
								Electrostatic.put(p.getUniqueId(), 0);
								new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
						        egg.EngSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						Magnetic.put(p.getUniqueId(), 0);
						Jetpack.put(p.getUniqueId(), 0);
						Graviton.put(p.getUniqueId(), 0);
						Electrostatic.put(p.getUniqueId(), 0);
						Dispenser.put(p.getUniqueId(), 0);
						X_ray.put(p.getUniqueId(), 0);
						Magnetic.put(p.getUniqueId(), 0);
						CombatSuit.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new EngSkillsData(Magnetic, Jetpack, Graviton, Electrostatic, X_ray, Dispenser, CombatSuit, SkillPoints).saveData(path +"/plugins/RPGskills/EngSkillsData.data");
				        egg.EngSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public static EngSkillsData getEngmandata(){
        String path = new File("").getAbsolutePath();
        EngSkillsData data = new EngSkillsData(EngSkillsData.loadData(path +"/plugins/RPGskills/EngSkillsData.data"));
		return data;
	}
}