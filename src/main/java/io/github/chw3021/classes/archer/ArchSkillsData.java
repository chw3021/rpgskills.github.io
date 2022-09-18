package io.github.chw3021.classes.archer;


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



public class ArchSkillsData implements Serializable, Listener{
	

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 3660531768996993923L;
	public final HashMap<UUID, Integer> TripleShot;
	public final HashMap<UUID, Integer> HookAndShot;
	public final HashMap<UUID, Integer> MultiShot;
	public final HashMap<UUID, Integer> SpreadingArrows;
	public final HashMap<UUID, Integer> RapidFire;
	public final HashMap<UUID, Integer> Retrieve;
	public final HashMap<UUID, Integer> Archery;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public ArchSkillsData(HashMap<UUID, Integer> TripleShot, HashMap<UUID, Integer> HookAndShot, HashMap<UUID, Integer> MultiShot, HashMap<UUID, Integer> SpreadingArrows, HashMap<UUID, Integer> RapidFire, HashMap<UUID, Integer> Retrieve, HashMap<UUID, Integer> Archery, HashMap<UUID, Integer> SkillPoints) {
    	this.TripleShot = TripleShot;
    	this.HookAndShot = HookAndShot;
    	this.MultiShot = MultiShot;
    	this.SpreadingArrows = SpreadingArrows;
    	this.RapidFire = RapidFire;
    	this.Retrieve = Retrieve;
    	this.Archery = Archery;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public ArchSkillsData(ArchSkillsData loadedData) {
    	this.TripleShot = loadedData.TripleShot;
    	this.HookAndShot = loadedData.HookAndShot;
    	this.MultiShot = loadedData.MultiShot;
    	this.SpreadingArrows = loadedData.SpreadingArrows;
    	this.RapidFire = loadedData.RapidFire;
    	this.Retrieve = loadedData.Retrieve;
    	this.Archery = loadedData.Archery;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public ArchSkillsData saveData(String filePath) {
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
    public static ArchSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            ArchSkillsData data = (ArchSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException |NullPointerException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            ArchSkillsData data = new ArchSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),  new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
            
            e.printStackTrace();
            return data;
        }
    }
    
	@EventHandler
	public void Archskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Archskills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{HashMap<UUID, Integer> TripleShot = getArcherdata().TripleShot;
			HashMap<UUID, Integer> HookAndShot = getArcherdata().HookAndShot;
			HashMap<UUID, Integer> MultiShot = getArcherdata().MultiShot;
			HashMap<UUID, Integer> SpreadingArrows = getArcherdata().SpreadingArrows;
			HashMap<UUID, Integer> Retrieve = getArcherdata().Retrieve;
			HashMap<UUID, Integer> RapidFire = getArcherdata().RapidFire;
			HashMap<UUID, Integer> Archery = getArcherdata().Archery;
			HashMap<UUID, Integer> SkillPoints = getArcherdata().SkillPoints;
				if(!getArcherdata().SkillPoints.containsKey(p.getUniqueId()))
				{
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					TripleShot.put(p.getUniqueId(), 0);
					HookAndShot.put(p.getUniqueId(), 0);
					MultiShot.put(p.getUniqueId(), 0);
					SpreadingArrows.put(p.getUniqueId(), 0);
					RapidFire.put(p.getUniqueId(), 0);
					Retrieve.put(p.getUniqueId(), 0);
					Archery.put(p.getUniqueId(), 0);
					new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");

		            ArchSkillsGui asg = new ArchSkillsGui();
					asg.Archskillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{

				HashMap<UUID, Integer> TripleShot = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> HookAndShot = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> MultiShot = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SpreadingArrows = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> RapidFire = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Retrieve = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Archery = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				TripleShot.put(p.getUniqueId(), 0);
				HookAndShot.put(p.getUniqueId(), 0);
				MultiShot.put(p.getUniqueId(), 0);
				SpreadingArrows.put(p.getUniqueId(), 0);
				RapidFire.put(p.getUniqueId(), 0);
				Retrieve.put(p.getUniqueId(), 0);
				Archery.put(p.getUniqueId(), 0);
				new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");

	            ArchSkillsGui asg = new ArchSkillsGui();
				asg.Archskillsinv(p);
			}
		}
	}


	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> TripleShot = getArcherdata().TripleShot;
		HashMap<UUID, Integer> HookAndShot = getArcherdata().HookAndShot;
		HashMap<UUID, Integer> MultiShot = getArcherdata().MultiShot;
		HashMap<UUID, Integer> SpreadingArrows = getArcherdata().SpreadingArrows;
		HashMap<UUID, Integer> Retrieve = getArcherdata().Retrieve;
		HashMap<UUID, Integer> RapidFire = getArcherdata().RapidFire;
		HashMap<UUID, Integer> Archery = getArcherdata().Archery;
		HashMap<UUID, Integer> SkillPoints = getArcherdata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");


	}
    
	@EventHandler
	public void Archskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();

        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> TripleShot = getArcherdata().TripleShot;
		HashMap<UUID, Integer> HookAndShot = getArcherdata().HookAndShot;
		HashMap<UUID, Integer> MultiShot = getArcherdata().MultiShot;
		HashMap<UUID, Integer> SpreadingArrows = getArcherdata().SpreadingArrows;
		HashMap<UUID, Integer> Retrieve = getArcherdata().Retrieve;
		HashMap<UUID, Integer> RapidFire = getArcherdata().RapidFire;
		HashMap<UUID, Integer> Archery = getArcherdata().Archery;
		HashMap<UUID, Integer> SkillPoints = getArcherdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
		        
			}
			else
			{	

				TripleShot.remove(p.getUniqueId());
				TripleShot.put(p.getUniqueId(), 0);
				HookAndShot.remove(p.getUniqueId());
				HookAndShot.put(p.getUniqueId(), 0);
				MultiShot.remove(p.getUniqueId());
				MultiShot.put(p.getUniqueId(), 0);
				SpreadingArrows.remove(p.getUniqueId());
				SpreadingArrows.put(p.getUniqueId(), 0);
				Archery.remove(p.getUniqueId());
				Archery.put(p.getUniqueId(), 0);
				RapidFire.remove(p.getUniqueId());
				RapidFire.put(p.getUniqueId(), 0);
				Retrieve.remove(p.getUniqueId());
				Retrieve.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
		       
			}
		}
	}
	
	@EventHandler
	public void Archskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Archskills"))
		{
			e.setCancelled(true);

	        String path = new File("").getAbsolutePath();
			ArchSkillsGui l = new ArchSkillsGui();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				HashMap<UUID, Integer> TripleShot = getArcherdata().TripleShot;
				HashMap<UUID, Integer> HookAndShot = getArcherdata().HookAndShot;
				HashMap<UUID, Integer> MultiShot = getArcherdata().MultiShot;
				HashMap<UUID, Integer> SpreadingArrows = getArcherdata().SpreadingArrows;
				HashMap<UUID, Integer> Retrieve = getArcherdata().Retrieve;
				HashMap<UUID, Integer> RapidFire = getArcherdata().RapidFire;
				HashMap<UUID, Integer> Archery = getArcherdata().Archery;
				HashMap<UUID, Integer> SkillPoints = getArcherdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "TripleShot":
					case "Æ®¸®ÇÃ¼¦":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && TripleShot.get(p.getUniqueId())<50){
								TripleShot.put(p.getUniqueId(), TripleShot.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}	}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(TripleShot.get(p.getUniqueId()) >= 1){
								TripleShot.put(p.getUniqueId(), TripleShot.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(TripleShot.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(TripleShot.get(p.getUniqueId()) < 50) {
										final int a = 50 - TripleShot.get(p.getUniqueId());
										TripleShot.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
																	
									}							
								}
								else {
									TripleShot.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+TripleShot.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(TripleShot.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+TripleShot.get(p.getUniqueId()));
								TripleShot.put(p.getUniqueId(), 0);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						break;		}
						
					case "HookAndShot":
					case "ÈÅ¾Ø¼¦":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && HookAndShot.get(p.getUniqueId())<1){
								HookAndShot.put(p.getUniqueId(), HookAndShot.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}	}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(HookAndShot.get(p.getUniqueId()) >= 1){
								HookAndShot.put(p.getUniqueId(), HookAndShot.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(HookAndShot.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(HookAndShot.get(p.getUniqueId()) < 1) {
										HookAndShot.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - HookAndShot.get(p.getUniqueId()));										
									}							
								}
								else {
									HookAndShot.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HookAndShot.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(HookAndShot.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HookAndShot.get(p.getUniqueId()));
								HookAndShot.put(p.getUniqueId(), 0);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						break;		}		
					case "MultiShot":
					case "´ÙÁß»ç°Ý":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && MultiShot.get(p.getUniqueId())<50){
								MultiShot.put(p.getUniqueId(), MultiShot.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}	}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(MultiShot.get(p.getUniqueId()) >= 1){
								MultiShot.put(p.getUniqueId(), MultiShot.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(MultiShot.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(MultiShot.get(p.getUniqueId()) < 50) {
										final int a = 50 - MultiShot.get(p.getUniqueId());
										MultiShot.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
																		
									}							
								}
								else {
									MultiShot.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MultiShot.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(MultiShot.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MultiShot.get(p.getUniqueId()));
								MultiShot.put(p.getUniqueId(), 0);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						break;		}
					case "RapidFire":
					case "¼Ó»ç":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && RapidFire.get(p.getUniqueId())<50){
								RapidFire.put(p.getUniqueId(), RapidFire.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}	}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(RapidFire.get(p.getUniqueId()) >= 1){
								RapidFire.put(p.getUniqueId(), RapidFire.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(RapidFire.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(RapidFire.get(p.getUniqueId()) < 50) {
										final int a = 50 - RapidFire.get(p.getUniqueId());
										RapidFire.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);									
									}							
								}
								else {
									RapidFire.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+RapidFire.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(RapidFire.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+RapidFire.get(p.getUniqueId()));
								RapidFire.put(p.getUniqueId(), 0);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						break;		}
					case "Retrieve":
					case "È¸¼ö":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Retrieve.get(p.getUniqueId()) < 1){
								Retrieve.put(p.getUniqueId(), Retrieve.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Retrieve.get(p.getUniqueId()) >= 1){
								Retrieve.put(p.getUniqueId(), Retrieve.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Retrieve.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Retrieve.get(p.getUniqueId()) < 1) {
										Retrieve.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Retrieve.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Retrieve.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Retrieve.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Retrieve.get(p.getUniqueId()));
								Retrieve.put(p.getUniqueId(), 0);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						break;		}
					case "Archery":
					case "±Ã¼ú":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Archery.put(p.getUniqueId(), Archery.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Archery.get(p.getUniqueId()) >= 1){
								Archery.put(p.getUniqueId(), Archery.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Archery.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Archery.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Archery.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Archery.get(p.getUniqueId()));
								Archery.put(p.getUniqueId(), 0);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						break;		}			
					case "SpreadingArrows":
					case "È­»ì»Ñ¸®±â":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && SpreadingArrows.get(p.getUniqueId()) < 1){
								SpreadingArrows.put(p.getUniqueId(), SpreadingArrows.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(SpreadingArrows.get(p.getUniqueId()) >= 1){
								SpreadingArrows.put(p.getUniqueId(), SpreadingArrows.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(SpreadingArrows.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(SpreadingArrows.get(p.getUniqueId()) < 1) {
										SpreadingArrows.put(p.getUniqueId(), 1);
									SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);
									}
								}
								else {
									SpreadingArrows.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SpreadingArrows.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);							
								}
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(SpreadingArrows.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SpreadingArrows.get(p.getUniqueId()));
								SpreadingArrows.put(p.getUniqueId(), 0);
								new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
						        l.Archskillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":	
					case "½ºÅ³Æ÷ÀÎÆ®":	{			
						TripleShot.put(p.getUniqueId(), 0);
						HookAndShot.put(p.getUniqueId(), 0);
						MultiShot.put(p.getUniqueId(), 0);
						SpreadingArrows.put(p.getUniqueId(), 0);
						Archery.put(p.getUniqueId(), 0);
						RapidFire.put(p.getUniqueId(), 0);
						TripleShot.put(p.getUniqueId(), 0);
						Retrieve.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new ArchSkillsData(TripleShot, HookAndShot, MultiShot, SpreadingArrows, RapidFire, Retrieve, Archery, SkillPoints).saveData(path +"/plugins/RPGskills/ArchskillsData.data");
				        l.Archskillsinv(p);
				}break;	}
			
			}
			
		}
		
	}
    
    public static ArchSkillsData getArcherdata(){
        String path = new File("").getAbsolutePath();
        ArchSkillsData data = new ArchSkillsData(ArchSkillsData.loadData(path +"/plugins/RPGskills/ArchskillsData.data"));
		return data;
	}
}
