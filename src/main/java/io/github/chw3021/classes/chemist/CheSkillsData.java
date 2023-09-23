package io.github.chw3021.classes.chemist;


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



public class CheSkillsData implements Serializable, Listener{
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 2394359067488248082L;
	public final HashMap<UUID, Integer> Extraction;
	public final HashMap<UUID, Integer> Charge;
	public final HashMap<UUID, Integer> Coagulation;
	public final HashMap<UUID, Integer> AcidCloud;
	public final HashMap<UUID, Integer> SlimeBall;
	public final HashMap<UUID, Integer> MolotovCocktail;
	public final HashMap<UUID, Integer> Poisonous;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public CheSkillsData(HashMap<UUID, Integer> Extraction, HashMap<UUID, Integer> Charge, HashMap<UUID, Integer> Coagulation, HashMap<UUID, Integer> AcidCloud, HashMap<UUID, Integer> SlimeBall, HashMap<UUID, Integer> MolotovCocktail, HashMap<UUID, Integer> Poisonous, HashMap<UUID, Integer> SkillPoints) {
    	this.Extraction = Extraction;
    	this.Charge = Charge;
    	this.Coagulation = Coagulation;
    	this.AcidCloud = AcidCloud;
    	this.SlimeBall = SlimeBall;
    	this.MolotovCocktail = MolotovCocktail;
    	this.Poisonous = Poisonous;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public CheSkillsData(CheSkillsData loadedData) {
    	this.Extraction = loadedData.Extraction;
    	this.Charge = loadedData.Charge;
    	this.Coagulation = loadedData.Coagulation;
    	this.AcidCloud = loadedData.AcidCloud;
    	this.SlimeBall = loadedData.SlimeBall;
    	this.MolotovCocktail = loadedData.MolotovCocktail;
    	this.Poisonous = loadedData.Poisonous;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public CheSkillsData saveData(String filePath) {
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
    public static CheSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            CheSkillsData data = (CheSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException |NullPointerException e) {
            // TODO Auto-generated catch block

            String path = new File("").getAbsolutePath();
        	
            CheSkillsData data = new CheSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
           
            return data;
        }
    }
    

	@EventHandler
	public void Cheskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Cheskills"))
		{
	        String path = new File("").getAbsolutePath();
			CheSkillsGui chg = new CheSkillsGui();
			try 
			{
				if(!getChemistdata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> Extraction = getChemistdata().Extraction;
					HashMap<UUID, Integer> Charge = getChemistdata().Charge;
					HashMap<UUID, Integer> Coagulation = getChemistdata().Coagulation;
					HashMap<UUID, Integer> AcidCloud = getChemistdata().AcidCloud;
					HashMap<UUID, Integer> SlimeBall = getChemistdata().SlimeBall;
					HashMap<UUID, Integer> MolotovCocktail = getChemistdata().MolotovCocktail;
					HashMap<UUID, Integer> Poisonous = getChemistdata().Poisonous;
					HashMap<UUID, Integer> SkillPoints = getChemistdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					Extraction.put(p.getUniqueId(), 0);
					Charge.put(p.getUniqueId(), 0);
					Coagulation.put(p.getUniqueId(), 0);
					AcidCloud.put(p.getUniqueId(), 0);
					SlimeBall.put(p.getUniqueId(), 0);
					MolotovCocktail.put(p.getUniqueId(), 0);
					Poisonous.put(p.getUniqueId(), 0);
					new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");

					chg.CheSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Extraction = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Charge = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Coagulation = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> AcidCloud = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SlimeBall = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> MolotovCocktail = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Poisonous = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				Extraction.put(p.getUniqueId(), 0);
				Charge.put(p.getUniqueId(), 0);
				Coagulation.put(p.getUniqueId(), 0);
				AcidCloud.put(p.getUniqueId(), 0);
				SlimeBall.put(p.getUniqueId(), 0);
				MolotovCocktail.put(p.getUniqueId(), 0);
				Poisonous.put(p.getUniqueId(), 0);
				new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");

				chg.CheSkillsinv(p);
			}
		}
	}
	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> Extraction = getChemistdata().Extraction;
		HashMap<UUID, Integer> Charge = getChemistdata().Charge;
		HashMap<UUID, Integer> Coagulation = getChemistdata().Coagulation;
		HashMap<UUID, Integer> AcidCloud = getChemistdata().AcidCloud;
		HashMap<UUID, Integer> SlimeBall = getChemistdata().SlimeBall;
		HashMap<UUID, Integer> MolotovCocktail = getChemistdata().MolotovCocktail;
		HashMap<UUID, Integer> Poisonous = getChemistdata().Poisonous;
		HashMap<UUID, Integer> SkillPoints = getChemistdata().SkillPoints;
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");


	}

	    
	@EventHandler
	public void Cheskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> Extraction = getChemistdata().Extraction;
		HashMap<UUID, Integer> Charge = getChemistdata().Charge;
		HashMap<UUID, Integer> Coagulation = getChemistdata().Coagulation;
		HashMap<UUID, Integer> AcidCloud = getChemistdata().AcidCloud;
		HashMap<UUID, Integer> SlimeBall = getChemistdata().SlimeBall;
		HashMap<UUID, Integer> MolotovCocktail = getChemistdata().MolotovCocktail;
		HashMap<UUID, Integer> Poisonous = getChemistdata().Poisonous;
		HashMap<UUID, Integer> SkillPoints = getChemistdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
		       
			}
			else
			{	

				Extraction.put(p.getUniqueId(), 0);
				Charge.put(p.getUniqueId(), 0);
				Coagulation.put(p.getUniqueId(), 0);
				AcidCloud.put(p.getUniqueId(), 0);
				MolotovCocktail.put(p.getUniqueId(), 0);
				SlimeBall.put(p.getUniqueId(), 0);
				Poisonous.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Cheskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Cheskills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			CheSkillsGui chg = new CheSkillsGui();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
				HashMap<UUID, Integer> Extraction = getChemistdata().Extraction;
				HashMap<UUID, Integer> Charge = getChemistdata().Charge;
				HashMap<UUID, Integer> Coagulation = getChemistdata().Coagulation;
				HashMap<UUID, Integer> AcidCloud = getChemistdata().AcidCloud;
				HashMap<UUID, Integer> SlimeBall = getChemistdata().SlimeBall;
				HashMap<UUID, Integer> MolotovCocktail = getChemistdata().MolotovCocktail;
				HashMap<UUID, Integer> Poisonous = getChemistdata().Poisonous;
				HashMap<UUID, Integer> SkillPoints = getChemistdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Extraction":
					case "추출":
						if(e.getClick().equals(ClickType.LEFT)) {
						if(SkillPoints.get(p.getUniqueId()) >= 1 && Extraction.get(p.getUniqueId()) < 1){
							Extraction.put(p.getUniqueId(), Extraction.get(p.getUniqueId()) +1);
							SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
							new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
					        chg.CheSkillsinv(p);					        
						}	}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Extraction.get(p.getUniqueId()) >= 1){
								Extraction.put(p.getUniqueId(), Extraction.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Extraction.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Extraction.get(p.getUniqueId()) < 1) {
										Extraction.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Extraction.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Extraction.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Extraction.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Extraction.get(p.getUniqueId()));
								Extraction.put(p.getUniqueId(), 0);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}
						}
						break;		
						
					case "Charge":
					case "돌진":{
						if(e.getClick().equals(ClickType.LEFT)) {
						if(SkillPoints.get(p.getUniqueId()) >= 1 && Charge.get(p.getUniqueId()) < 50){
							Charge.put(p.getUniqueId(), Charge.get(p.getUniqueId()) +1);
							SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
							new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
					        chg.CheSkillsinv(p);
						}	}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Charge.get(p.getUniqueId()) >= 1){
								Charge.put(p.getUniqueId(), Charge.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Charge.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Charge.get(p.getUniqueId()) < 50) {
										final int a = 50 - Charge.get(p.getUniqueId());
										Charge.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);	
									}
								}
								else {
									Charge.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Charge.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);							
								}
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Charge.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Charge.get(p.getUniqueId()));
								Charge.put(p.getUniqueId(), 0);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}
						}
						break;		}		
					case "Napalm":
					case "네이팜":{
						if(e.getClick().equals(ClickType.LEFT)) {
						if(SkillPoints.get(p.getUniqueId()) >= 1 && Coagulation.get(p.getUniqueId()) <50){
							Coagulation.put(p.getUniqueId(), Coagulation.get(p.getUniqueId()) +1);
							SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
							new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
					        chg.CheSkillsinv(p);
						}	}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Coagulation.get(p.getUniqueId()) >= 1){
								Coagulation.put(p.getUniqueId(), Coagulation.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Coagulation.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Coagulation.get(p.getUniqueId())<50){
										final int a = 50 - Coagulation.get(p.getUniqueId());
										Coagulation.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									Coagulation.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Coagulation.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
							new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
					        chg.CheSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Coagulation.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Coagulation.get(p.getUniqueId()));
								Coagulation.put(p.getUniqueId(), 0);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}
						}
						break;		}
					case "SlimeBall":
					case "슬라임볼":{
						if(e.getClick().equals(ClickType.LEFT)) {
						if(SkillPoints.get(p.getUniqueId()) >= 1 && SlimeBall.get(p.getUniqueId()) <50){
							SlimeBall.put(p.getUniqueId(), SlimeBall.get(p.getUniqueId()) +1);
							SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
							new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
					        chg.CheSkillsinv(p);
						}	}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(SlimeBall.get(p.getUniqueId()) >= 1){
								SlimeBall.put(p.getUniqueId(), SlimeBall.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(SlimeBall.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(SlimeBall.get(p.getUniqueId())<50){
										final int a = 50 - SlimeBall.get(p.getUniqueId());
										SlimeBall.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);								
									}
								}
								else{
									SlimeBall.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SlimeBall.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
							new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
					        chg.CheSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(SlimeBall.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SlimeBall.get(p.getUniqueId()));
								SlimeBall.put(p.getUniqueId(), 0);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}
						}
						break;		}
					case "MolotovCocktail":
					case "화염병":{
						if(e.getClick().equals(ClickType.LEFT)) {
						if(SkillPoints.get(p.getUniqueId()) >= 1 && MolotovCocktail.get(p.getUniqueId()) <50){
							MolotovCocktail.put(p.getUniqueId(), MolotovCocktail.get(p.getUniqueId()) +1);
							SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
							new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
					        chg.CheSkillsinv(p);
						}	}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(MolotovCocktail.get(p.getUniqueId()) >= 1){
								MolotovCocktail.put(p.getUniqueId(), MolotovCocktail.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(MolotovCocktail.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(MolotovCocktail.get(p.getUniqueId())<50){
										final int a = 50 - MolotovCocktail.get(p.getUniqueId());
										MolotovCocktail.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									MolotovCocktail.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MolotovCocktail.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
							new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
					        chg.CheSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(MolotovCocktail.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+MolotovCocktail.get(p.getUniqueId()));
								MolotovCocktail.put(p.getUniqueId(), 0);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}
						}
						break;		}				
					case "Poisonous":
					case "유독성":{
						if(e.getClick().equals(ClickType.LEFT)) {
						if(SkillPoints.get(p.getUniqueId()) >= 1){
							Poisonous.put(p.getUniqueId(), Poisonous.get(p.getUniqueId()) +1);
							SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
							new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
					        chg.CheSkillsinv(p);
						}	}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Poisonous.get(p.getUniqueId()) >= 1){
								Poisonous.put(p.getUniqueId(), Poisonous.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Poisonous.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Poisonous.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Poisonous.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Poisonous.get(p.getUniqueId()));
								Poisonous.put(p.getUniqueId(), 0);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}
						}
						break;		}
					case "AcidCloud":
					case "산성구름":{
						if(e.getClick().equals(ClickType.LEFT)) {
						if(SkillPoints.get(p.getUniqueId()) >= 1 && AcidCloud.get(p.getUniqueId()) <50){
							AcidCloud.put(p.getUniqueId(), AcidCloud.get(p.getUniqueId()) +1);
							SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
							new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
					        chg.CheSkillsinv(p);
						}	}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(AcidCloud.get(p.getUniqueId()) >= 1){
								AcidCloud.put(p.getUniqueId(), AcidCloud.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(AcidCloud.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(AcidCloud.get(p.getUniqueId())<50){
										final int a = 50 - AcidCloud.get(p.getUniqueId());
										AcidCloud.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);								
									}
								}
								else{
									AcidCloud.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+AcidCloud.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
							new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
					        chg.CheSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(AcidCloud.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+AcidCloud.get(p.getUniqueId()));
								AcidCloud.put(p.getUniqueId(), 0);
								new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
						        chg.CheSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						Extraction.put(p.getUniqueId(), 0);
						Charge.put(p.getUniqueId(), 0);
						Coagulation.put(p.getUniqueId(), 0);
						AcidCloud.put(p.getUniqueId(), 0);
						MolotovCocktail.put(p.getUniqueId(), 0);
						SlimeBall.put(p.getUniqueId(), 0);
						Poisonous.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new CheSkillsData(Extraction, Charge, Coagulation, AcidCloud, SlimeBall, MolotovCocktail, Poisonous, SkillPoints).saveData(path +"/plugins/RPGskills/CheSkillsData.data");
				        chg.CheSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public static CheSkillsData getChemistdata(){
        String path = new File("").getAbsolutePath();
        CheSkillsData data = new CheSkillsData(CheSkillsData.loadData(path +"/plugins/RPGskills/CheSkillsData.data"));
		return data;
	}
}