package io.github.chw3021.classes.medic;


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



public class MedSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 5847259885332575034L;
	public final HashMap<UUID, Integer> AED;
	public final HashMap<UUID, Integer> ArrowClinic;
	public final HashMap<UUID, Integer> Hideout;
	public final HashMap<UUID, Integer> RemedyingRocket;
	public final HashMap<UUID, Integer> SupplyCart;
	public final HashMap<UUID, Integer> Decontamination;
	public final HashMap<UUID, Integer> Medicine;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public MedSkillsData(HashMap<UUID, Integer> AED, HashMap<UUID, Integer> ArrowClinic, HashMap<UUID, Integer> Hideout, HashMap<UUID, Integer> RemedyingRocket, HashMap<UUID, Integer> SupplyCart, HashMap<UUID, Integer> Decontamination, HashMap<UUID, Integer> Medicine, HashMap<UUID, Integer> SkillPoints) {
    	this.AED = AED;
    	this.ArrowClinic = ArrowClinic;
    	this.Hideout = Hideout;
    	this.RemedyingRocket = RemedyingRocket;
    	this.SupplyCart = SupplyCart;
    	this.Decontamination = Decontamination;
    	this.Medicine = Medicine;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public MedSkillsData(MedSkillsData loadedData) {
    	this.AED = loadedData.AED;
    	this.ArrowClinic = loadedData.ArrowClinic;
    	this.Hideout = loadedData.Hideout;
    	this.RemedyingRocket = loadedData.RemedyingRocket;
    	this.SupplyCart = loadedData.SupplyCart;
    	this.Decontamination = loadedData.Decontamination;
    	this.Medicine = loadedData.Medicine;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public MedSkillsData saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return this;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return this;
        }
    }
    public static MedSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            MedSkillsData data = (MedSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException | NullPointerException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            MedSkillsData data = new MedSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),  new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/MedskillsData.data");
            
            return data;
        }
    }
    

	@EventHandler
	public void Medskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Medskills"))
		{
	        String path = new File("").getAbsolutePath();
			MedSkillsGui l = new MedSkillsGui();
			try 
			{
				if(!getMedicdata().SkillPoints.containsKey(p.getUniqueId()))
				{HashMap<UUID, Integer> AED = getMedicdata().AED;
				HashMap<UUID, Integer> ArrowClinic = getMedicdata().ArrowClinic;
				HashMap<UUID, Integer> Hideout = getMedicdata().Hideout;
				HashMap<UUID, Integer> RemedyingRocket = getMedicdata().RemedyingRocket;
				HashMap<UUID, Integer> Decontamination = getMedicdata().Decontamination;
				HashMap<UUID, Integer> SupplyCart = getMedicdata().SupplyCart;
				HashMap<UUID, Integer> Medicine = getMedicdata().Medicine;
				HashMap<UUID, Integer> SkillPoints = getMedicdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					AED.put(p.getUniqueId(), 0);
					ArrowClinic.put(p.getUniqueId(), 0);
					Hideout.put(p.getUniqueId(), 0);
					RemedyingRocket.put(p.getUniqueId(), 0);
					SupplyCart.put(p.getUniqueId(), 0);
					Decontamination.put(p.getUniqueId(), 0);
					Medicine.put(p.getUniqueId(), 0);
					new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
					l.Medskillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{HashMap<UUID, Integer> AED = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> ArrowClinic = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Hideout = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> RemedyingRocket = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> SupplyCart = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Decontamination = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Medicine = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				AED.put(p.getUniqueId(), 0);
				ArrowClinic.put(p.getUniqueId(), 0);
				Hideout.put(p.getUniqueId(), 0);
				RemedyingRocket.put(p.getUniqueId(), 0);
				SupplyCart.put(p.getUniqueId(), 0);
				Decontamination.put(p.getUniqueId(), 0);
				Medicine.put(p.getUniqueId(), 0);
				new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
				l.Medskillsinv(p);
			}
		}
	}

	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> AED = getMedicdata().AED;
		HashMap<UUID, Integer> ArrowClinic = getMedicdata().ArrowClinic;
		HashMap<UUID, Integer> Hideout = getMedicdata().Hideout;
		HashMap<UUID, Integer> RemedyingRocket = getMedicdata().RemedyingRocket;
		HashMap<UUID, Integer> Decontamination = getMedicdata().Decontamination;
		HashMap<UUID, Integer> SupplyCart = getMedicdata().SupplyCart;
		HashMap<UUID, Integer> Medicine = getMedicdata().Medicine;
		HashMap<UUID, Integer> SkillPoints = getMedicdata().SkillPoints;
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");


	}

    
	@EventHandler
	public void Medskilllev(PlayerLevelChangeEvent e)
	{

        String path = new File("").getAbsolutePath();
		Player p = (Player) e.getPlayer();

		HashMap<UUID, Integer> AED = getMedicdata().AED;
		HashMap<UUID, Integer> ArrowClinic = getMedicdata().ArrowClinic;
		HashMap<UUID, Integer> Hideout = getMedicdata().Hideout;
		HashMap<UUID, Integer> RemedyingRocket = getMedicdata().RemedyingRocket;
		HashMap<UUID, Integer> Decontamination = getMedicdata().Decontamination;
		HashMap<UUID, Integer> SupplyCart = getMedicdata().SupplyCart;
		HashMap<UUID, Integer> Medicine = getMedicdata().Medicine;
		HashMap<UUID, Integer> SkillPoints = getMedicdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
		        
			}
			else
			{	

				AED.remove(p.getUniqueId());
				AED.put(p.getUniqueId(), 0);
				ArrowClinic.remove(p.getUniqueId());
				ArrowClinic.put(p.getUniqueId(), 0);
				Hideout.remove(p.getUniqueId());
				Hideout.put(p.getUniqueId(), 0);
				RemedyingRocket.remove(p.getUniqueId());
				RemedyingRocket.put(p.getUniqueId(), 0);
				Medicine.remove(p.getUniqueId());
				Medicine.put(p.getUniqueId(), 0);
				SupplyCart.remove(p.getUniqueId());
				SupplyCart.put(p.getUniqueId(), 0);
				Decontamination.remove(p.getUniqueId());
				Decontamination.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Medskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Medskills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				MedSkillsGui l = new MedSkillsGui();
				
				HashMap<UUID, Integer> AED = getMedicdata().AED;
				HashMap<UUID, Integer> ArrowClinic = getMedicdata().ArrowClinic;
				HashMap<UUID, Integer> Hideout = getMedicdata().Hideout;
				HashMap<UUID, Integer> RemedyingRocket = getMedicdata().RemedyingRocket;
				HashMap<UUID, Integer> Decontamination = getMedicdata().Decontamination;
				HashMap<UUID, Integer> SupplyCart = getMedicdata().SupplyCart;
				HashMap<UUID, Integer> Medicine = getMedicdata().Medicine;
				HashMap<UUID, Integer> SkillPoints = getMedicdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{

					case "ArrowClinic":
					case "화살치료":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ArrowClinic.get(p.getUniqueId()) < 30){
								ArrowClinic.put(p.getUniqueId(), ArrowClinic.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ArrowClinic.get(p.getUniqueId()) >= 1){
								ArrowClinic.put(p.getUniqueId(), ArrowClinic.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ArrowClinic.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>30) {
									if(ArrowClinic.get(p.getUniqueId()) < 30) {
										final int a = 30 - ArrowClinic.get(p.getUniqueId());
										ArrowClinic.put(p.getUniqueId(), 30);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);								
									}							
								}
								else {
									ArrowClinic.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ArrowClinic.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ArrowClinic.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ArrowClinic.get(p.getUniqueId()));
								ArrowClinic.put(p.getUniqueId(), 0);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						break;		}
						
					case "AED":
					case "자동제세동기":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && AED.get(p.getUniqueId())<50){
								AED.put(p.getUniqueId(), AED.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(AED.get(p.getUniqueId()) >= 1){
								AED.put(p.getUniqueId(), AED.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(AED.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(AED.get(p.getUniqueId())<50){
										final int a = 50 - AED.get(p.getUniqueId());
										AED.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);					
									}
								}
								else{
									AED.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+AED.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(AED.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+AED.get(p.getUniqueId()));
								AED.put(p.getUniqueId(), 0);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						break;		}		
					case "Hideout":
					case "은신처":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Hideout.get(p.getUniqueId()) < 1){
								Hideout.put(p.getUniqueId(), Hideout.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Hideout.get(p.getUniqueId()) >= 1){
								Hideout.put(p.getUniqueId(), Hideout.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Hideout.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Hideout.get(p.getUniqueId()) < 1) {
										Hideout.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Hideout.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Hideout.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Hideout.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Hideout.get(p.getUniqueId()));
								Hideout.put(p.getUniqueId(), 0);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						break;		}
					case "SupplyCart":
					case "보급카트":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && SupplyCart.get(p.getUniqueId()) < 5){
								SupplyCart.put(p.getUniqueId(), SupplyCart.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(SupplyCart.get(p.getUniqueId()) >= 1){
								SupplyCart.put(p.getUniqueId(), SupplyCart.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(SupplyCart.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>5) {
									if(SupplyCart.get(p.getUniqueId()) < 5) {
										final int a = 5 - SupplyCart.get(p.getUniqueId());
										SupplyCart.put(p.getUniqueId(), 5);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}							
								}
								else {
									SupplyCart.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SupplyCart.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(SupplyCart.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SupplyCart.get(p.getUniqueId()));
								SupplyCart.put(p.getUniqueId(), 0);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						break;		}
					case "Decontamination":
					case "제독":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Decontamination.get(p.getUniqueId())<50){
								Decontamination.put(p.getUniqueId(), Decontamination.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Decontamination.get(p.getUniqueId()) >= 1){
								Decontamination.put(p.getUniqueId(), Decontamination.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Decontamination.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Decontamination.get(p.getUniqueId())<50){
										final int a = 50 - Decontamination.get(p.getUniqueId());
										Decontamination.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Decontamination.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Decontamination.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Decontamination.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Decontamination.get(p.getUniqueId()));
								Decontamination.put(p.getUniqueId(), 0);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						break;		}
					case "Medicine":
					case "의학":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Medicine.put(p.getUniqueId(), Medicine.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Medicine.get(p.getUniqueId()) >= 1){
								Medicine.put(p.getUniqueId(), Medicine.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Medicine.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Medicine.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Medicine.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Medicine.get(p.getUniqueId()));
								Medicine.put(p.getUniqueId(), 0);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						break;		}			
					case "RemedyingRocket":
					case "치유로켓":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && RemedyingRocket.get(p.getUniqueId())<50){
								RemedyingRocket.put(p.getUniqueId(), RemedyingRocket.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(RemedyingRocket.get(p.getUniqueId()) >= 1){
								RemedyingRocket.put(p.getUniqueId(), RemedyingRocket.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(RemedyingRocket.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(RemedyingRocket.get(p.getUniqueId())<50){
										final int a = 50 - RemedyingRocket.get(p.getUniqueId());
										RemedyingRocket.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);					
									}
								}
								else{
									RemedyingRocket.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+RemedyingRocket.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(RemedyingRocket.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+RemedyingRocket.get(p.getUniqueId()));
								RemedyingRocket.put(p.getUniqueId(), 0);
								new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
						        l.Medskillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						AED.put(p.getUniqueId(), 0);
						ArrowClinic.put(p.getUniqueId(), 0);
						Hideout.put(p.getUniqueId(), 0);
						RemedyingRocket.put(p.getUniqueId(), 0);
						Medicine.put(p.getUniqueId(), 0);
						SupplyCart.put(p.getUniqueId(), 0);
						AED.put(p.getUniqueId(), 0);
						Decontamination.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new MedSkillsData(AED, ArrowClinic, Hideout, RemedyingRocket, SupplyCart, Decontamination, Medicine, SkillPoints).saveData(path +"/plugins/RPGskills/MedskillsData.data");
				        l.Medskillsinv(p);
				}break;	}
			
			}
			
		}
		
	}
    
    public static MedSkillsData getMedicdata(){
        String path = new File("").getAbsolutePath();
        MedSkillsData data = new MedSkillsData(MedSkillsData.loadData(path +"/plugins/RPGskills/MedskillsData.data"));
		return data;
	}
}
