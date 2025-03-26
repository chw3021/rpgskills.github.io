package io.github.chw3021.classes.angler;


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


public class AngSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -353355589904236766L;
	public final HashMap<UUID, Integer> Bait;
	public final HashMap<UUID, Integer> Fishing;
	public final HashMap<UUID, Integer> Whipping;
	public final HashMap<UUID, Integer> CoralLiquor;
	public final HashMap<UUID, Integer> CoralRoots;
	public final HashMap<UUID, Integer> DrunkenDance;
	public final HashMap<UUID, Integer> Technic;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public AngSkillsData(HashMap<UUID, Integer> Bait, HashMap<UUID, Integer> Fishing, HashMap<UUID, Integer> Whipping, HashMap<UUID, Integer> CoralLiquor, HashMap<UUID, Integer> CoralRoots, HashMap<UUID, Integer> DrunkenDance, HashMap<UUID, Integer> Technic, HashMap<UUID, Integer> SkillPoints) {
    	this.Bait = Bait;
    	this.Fishing = Fishing;
    	this.Whipping = Whipping;
    	this.CoralLiquor = CoralLiquor;
    	this.CoralRoots = CoralRoots;
    	this.DrunkenDance = DrunkenDance;
    	this.Technic = Technic;
    	this.SkillPoints = SkillPoints;
    	}
    public AngSkillsData(AngSkillsData loadedData) {
    	this.Bait = loadedData.Bait;
    	this.Fishing = loadedData.Fishing;
    	this.Whipping = loadedData.Whipping;
    	this.CoralLiquor = loadedData.CoralLiquor;
    	this.CoralRoots = loadedData.CoralRoots;
    	this.DrunkenDance = loadedData.DrunkenDance;
    	this.Technic = loadedData.Technic;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public AngSkillsData saveData(String filePath) {
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
    public static AngSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            AngSkillsData data = (AngSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException |NullPointerException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            AngSkillsData data = new AngSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),  new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
            
            return data;
        }
    }


	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Bait = getAnglerdata().Bait;
		HashMap<UUID, Integer> Fishing = getAnglerdata().Fishing;
		HashMap<UUID, Integer> Whipping = getAnglerdata().Whipping;
		HashMap<UUID, Integer> CoralLiquor = getAnglerdata().CoralLiquor;
		HashMap<UUID, Integer> DrunkenDance = getAnglerdata().DrunkenDance;
		HashMap<UUID, Integer> CoralRoots = getAnglerdata().CoralRoots;
		HashMap<UUID, Integer> Technic = getAnglerdata().Technic;
		HashMap<UUID, Integer> SkillPoints = getAnglerdata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		
		new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");

	}


	@EventHandler
	public void Angskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("AngSkills"))
		{
	        String path = new File("").getAbsolutePath();
			AngSkillsGui osg = new AngSkillsGui();
			try 
			{
				if(!getAnglerdata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> Bait = getAnglerdata().Bait;
					HashMap<UUID, Integer> Fishing = getAnglerdata().Fishing;
					HashMap<UUID, Integer> Whipping = getAnglerdata().Whipping;
					HashMap<UUID, Integer> CoralLiquor = getAnglerdata().CoralLiquor;
					HashMap<UUID, Integer> DrunkenDance = getAnglerdata().DrunkenDance;
					HashMap<UUID, Integer> CoralRoots = getAnglerdata().CoralRoots;
					HashMap<UUID, Integer> Technic = getAnglerdata().Technic;
					HashMap<UUID, Integer> SkillPoints = getAnglerdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					Bait.put(p.getUniqueId(), 0);
					Fishing.put(p.getUniqueId(), 0);
					Whipping.put(p.getUniqueId(), 0);
					CoralLiquor.put(p.getUniqueId(), 0);
					CoralRoots.put(p.getUniqueId(), 0);
					DrunkenDance.put(p.getUniqueId(), 0);
					Technic.put(p.getUniqueId(), 0);
					new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");

					osg.AngSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Bait = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Fishing = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Whipping = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> CoralLiquor = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> CoralRoots = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> DrunkenDance = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Technic = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				Bait.put(p.getUniqueId(), 0);
				Fishing.put(p.getUniqueId(), 0);
				Whipping.put(p.getUniqueId(), 0);
				CoralLiquor.put(p.getUniqueId(), 0);
				CoralRoots.put(p.getUniqueId(), 0);
				DrunkenDance.put(p.getUniqueId(), 0);
				Technic.put(p.getUniqueId(), 0);
				new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");

				osg.AngSkillsinv(p);
			}
		}
	}
    
	@EventHandler
	public void Angskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> Bait = getAnglerdata().Bait;
		HashMap<UUID, Integer> Fishing = getAnglerdata().Fishing;
		HashMap<UUID, Integer> Whipping = getAnglerdata().Whipping;
		HashMap<UUID, Integer> CoralLiquor = getAnglerdata().CoralLiquor;
		HashMap<UUID, Integer> DrunkenDance = getAnglerdata().DrunkenDance;
		HashMap<UUID, Integer> CoralRoots = getAnglerdata().CoralRoots;
		HashMap<UUID, Integer> Technic = getAnglerdata().Technic;
		HashMap<UUID, Integer> SkillPoints = getAnglerdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
		        
			}
			else
			{	

				Bait.remove(p.getUniqueId());
				Bait.put(p.getUniqueId(), 0);
				Fishing.remove(p.getUniqueId());
				Fishing.put(p.getUniqueId(), 0);
				Whipping.remove(p.getUniqueId());
				Whipping.put(p.getUniqueId(), 0);
				CoralLiquor.remove(p.getUniqueId());
				CoralLiquor.put(p.getUniqueId(), 0);
				Technic.remove(p.getUniqueId());
				Technic.put(p.getUniqueId(), 0);
				CoralRoots.remove(p.getUniqueId());
				CoralRoots.put(p.getUniqueId(), 0);
				DrunkenDance.remove(p.getUniqueId());
				DrunkenDance.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Angskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("AngSkills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				AngSkillsGui fsg = new AngSkillsGui();
				
				HashMap<UUID, Integer> Bait = getAnglerdata().Bait;
				HashMap<UUID, Integer> Fishing = getAnglerdata().Fishing;
				HashMap<UUID, Integer> Whipping = getAnglerdata().Whipping;
				HashMap<UUID, Integer> CoralLiquor = getAnglerdata().CoralLiquor;
				HashMap<UUID, Integer> DrunkenDance = getAnglerdata().DrunkenDance;
				HashMap<UUID, Integer> CoralRoots = getAnglerdata().CoralRoots;
				HashMap<UUID, Integer> Technic = getAnglerdata().Technic;
				HashMap<UUID, Integer> SkillPoints = getAnglerdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Bait":
					case "미끼":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Bait.get(p.getUniqueId())<50){
								Bait.put(p.getUniqueId(), Bait.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Bait.get(p.getUniqueId()) >= 1){
								Bait.put(p.getUniqueId(), Bait.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Bait.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Bait.get(p.getUniqueId())<50){
										final int a = 50 - Bait.get(p.getUniqueId());
										Bait.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
										

									}
								}
								else{
									Bait.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Bait.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Bait.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Bait.get(p.getUniqueId()));
								Bait.put(p.getUniqueId(), 0);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						break;		
						
					case "Fishing":
					case "낚시":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Fishing.get(p.getUniqueId()) < 1){
								Fishing.put(p.getUniqueId(), Fishing.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Fishing.get(p.getUniqueId()) >= 1){
								Fishing.put(p.getUniqueId(), Fishing.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Fishing.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Fishing.get(p.getUniqueId()) < 1) {
										Fishing.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Fishing.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Fishing.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Fishing.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Fishing.get(p.getUniqueId()));
								Fishing.put(p.getUniqueId(), 0);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						break;		
						
					case "Whipping":
					case "낚시대법":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Whipping.get(p.getUniqueId())<50){
								Whipping.put(p.getUniqueId(), Whipping.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Whipping.get(p.getUniqueId()) >= 1){
								Whipping.put(p.getUniqueId(), Whipping.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Whipping.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Whipping.get(p.getUniqueId())<50){
										final int a = 50 - Whipping.get(p.getUniqueId());
										Whipping.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Whipping.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Whipping.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Whipping.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Whipping.get(p.getUniqueId()));
								Whipping.put(p.getUniqueId(), 0);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						break;		
						
					case "CoralRoots":
					case "산호뿌리":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && CoralRoots.get(p.getUniqueId())<50){
								CoralRoots.put(p.getUniqueId(), CoralRoots.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(CoralRoots.get(p.getUniqueId()) >= 1){
								CoralRoots.put(p.getUniqueId(), CoralRoots.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(CoralRoots.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(CoralRoots.get(p.getUniqueId())<50){
										final int a = 50 - CoralRoots.get(p.getUniqueId());
										CoralRoots.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									CoralRoots.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CoralRoots.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(CoralRoots.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CoralRoots.get(p.getUniqueId()));
								CoralRoots.put(p.getUniqueId(), 0);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						break;		
						
					case "DrunkenDance":
					case "음주가무":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && DrunkenDance.get(p.getUniqueId())<50){
								DrunkenDance.put(p.getUniqueId(), DrunkenDance.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(DrunkenDance.get(p.getUniqueId()) >= 1){
								DrunkenDance.put(p.getUniqueId(), DrunkenDance.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(DrunkenDance.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(DrunkenDance.get(p.getUniqueId())<50){
										final int a = 50 - DrunkenDance.get(p.getUniqueId());
										DrunkenDance.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);		
									
									}
								}
								else{
									DrunkenDance.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DrunkenDance.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(DrunkenDance.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DrunkenDance.get(p.getUniqueId()));
								DrunkenDance.put(p.getUniqueId(), 0);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						break;		
						
					case "Technic":
					case "노련함":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Technic.put(p.getUniqueId(), Technic.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Technic.get(p.getUniqueId()) >= 1){
								Technic.put(p.getUniqueId(), Technic.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Technic.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Technic.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Technic.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Technic.get(p.getUniqueId()));
								Technic.put(p.getUniqueId(), 0);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						break;			
					case "CoralLiquor":
					case "산호주":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && CoralLiquor.get(p.getUniqueId()) < 20){
								CoralLiquor.put(p.getUniqueId(), CoralLiquor.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(CoralLiquor.get(p.getUniqueId()) >= 1){
								CoralLiquor.put(p.getUniqueId(), CoralLiquor.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(CoralLiquor.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>20) {
									if(CoralLiquor.get(p.getUniqueId()) < 20) {
										final int a = 50 - CoralLiquor.get(p.getUniqueId());
										CoralLiquor.put(p.getUniqueId(), 20);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);		
																	
									}							
								}
								else {
									CoralLiquor.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CoralLiquor.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(CoralLiquor.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+CoralLiquor.get(p.getUniqueId()));
								CoralLiquor.put(p.getUniqueId(), 0);
								new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
						        fsg.AngSkillsinv(p);
							}
						}
						break;		
					case "SkillPoints":	
					case "스킬포인트":	
						Bait.put(p.getUniqueId(), 0);
						Fishing.put(p.getUniqueId(), 0);
						Whipping.put(p.getUniqueId(), 0);
						CoralLiquor.put(p.getUniqueId(), 0);
						Technic.put(p.getUniqueId(), 0);
						CoralRoots.put(p.getUniqueId(), 0);
						DrunkenDance.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId())+ ScrollPoint.sp.get(p.getUniqueId()));
						new AngSkillsData(Bait, Fishing, Whipping, CoralLiquor, CoralRoots, DrunkenDance, Technic, SkillPoints).saveData(path +"/plugins/RPGskills/AngSkillsData.data");
				        fsg.AngSkillsinv(p);
				        break;
				}
			
			}
			
		}
		
	}
    
    public static AngSkillsData getAnglerdata(){
        String path = new File("").getAbsolutePath();
        AngSkillsData data = new AngSkillsData(AngSkillsData.loadData(path +"/plugins/RPGskills/AngSkillsData.data"));
		return data;
	}
}