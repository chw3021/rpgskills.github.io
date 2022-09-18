package io.github.chw3021.classes.sniper;


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



public class SnipSkillsData implements Serializable, Listener{
	
	private static transient final long serialVersionUID = -2216291279280796922L;
	public final HashMap<UUID, Integer> Camouflage;
	public final HashMap<UUID, Integer> AirStrike;
	public final HashMap<UUID, Integer> HeadShot;
	public final HashMap<UUID, Integer> Flare;
	public final HashMap<UUID, Integer> Rope;
	public final HashMap<UUID, Integer> FlashBomb;
	public final HashMap<UUID, Integer> ArmourPiercingArrow;
	public final HashMap<UUID, Integer> Remodeling;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public SnipSkillsData(HashMap<UUID, Integer> Camouflage, HashMap<UUID, Integer> AirStrike, HashMap<UUID, Integer> HeadShot, HashMap<UUID, Integer> Flare, HashMap<UUID, Integer> Rope, HashMap<UUID, Integer> FlashBomb, HashMap<UUID, Integer> ArmourPiercingArrow, HashMap<UUID, Integer> Remodeling, HashMap<UUID, Integer> SkillPoints) {
    	this.Camouflage = Camouflage;
    	this.AirStrike = AirStrike;
    	this.HeadShot = HeadShot;
    	this.Flare = Flare;
    	this.Rope = Rope;
    	this.FlashBomb = FlashBomb;
    	this.ArmourPiercingArrow = ArmourPiercingArrow;
    	this.Remodeling = Remodeling;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public SnipSkillsData(SnipSkillsData loadedData) {
    	this.Camouflage = loadedData.Camouflage;
    	this.AirStrike = loadedData.AirStrike;
    	this.HeadShot = loadedData.HeadShot;
    	this.Flare = loadedData.Flare;
    	this.Rope = loadedData.Rope;
    	this.FlashBomb = loadedData.FlashBomb;
    	this.ArmourPiercingArrow = loadedData.ArmourPiercingArrow;
    	this.Remodeling = loadedData.Remodeling;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public SnipSkillsData saveData(String filePath) {
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
    public static SnipSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            SnipSkillsData data = (SnipSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            SnipSkillsData data = new SnipSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),  new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
            
            return data;
        }
    }
    

	@EventHandler
	public void Snipskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Snipskills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getSnipcherdata().SkillPoints.containsKey(p.getUniqueId()))
				{HashMap<UUID, Integer> Camouflage = getSnipcherdata().Camouflage;
				HashMap<UUID, Integer> AirStrike = getSnipcherdata().AirStrike;
				HashMap<UUID, Integer> HeadShot = getSnipcherdata().HeadShot;
				HashMap<UUID, Integer> Flare = getSnipcherdata().Flare;
				HashMap<UUID, Integer> Rope = getSnipcherdata().Rope;
				HashMap<UUID, Integer> ArmourPiercingArrow = getSnipcherdata().ArmourPiercingArrow;
				HashMap<UUID, Integer> FlashBomb = getSnipcherdata().FlashBomb;
				HashMap<UUID, Integer> Remodeling = getSnipcherdata().Remodeling;
				HashMap<UUID, Integer> SkillPoints = getSnipcherdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					Camouflage.put(p.getUniqueId(), 0);
					AirStrike.put(p.getUniqueId(), 0);
					HeadShot.put(p.getUniqueId(), 0);
					Flare.put(p.getUniqueId(), 0);
					Rope.put(p.getUniqueId(), 0);
					FlashBomb.put(p.getUniqueId(), 0);
					ArmourPiercingArrow.put(p.getUniqueId(), 0);
					Remodeling.put(p.getUniqueId(), 0);
					new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");

					SnipSkillsGui l = new SnipSkillsGui();
					l.Snipskillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{HashMap<UUID, Integer> Camouflage = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> AirStrike = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> HeadShot = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Flare = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Rope = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> FlashBomb = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> ArmourPiercingArrow = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Remodeling = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				Camouflage.put(p.getUniqueId(), 0);
				AirStrike.put(p.getUniqueId(), 0);
				HeadShot.put(p.getUniqueId(), 0);
				Flare.put(p.getUniqueId(), 0);
				Rope.put(p.getUniqueId(), 0);
				FlashBomb.put(p.getUniqueId(), 0);
				ArmourPiercingArrow.put(p.getUniqueId(), 0);
				Remodeling.put(p.getUniqueId(), 0);
				new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");

				SnipSkillsGui l = new SnipSkillsGui();
				l.Snipskillsinv(p);
			}
		}
	}

	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Camouflage = getSnipcherdata().Camouflage;
		HashMap<UUID, Integer> AirStrike = getSnipcherdata().AirStrike;
		HashMap<UUID, Integer> HeadShot = getSnipcherdata().HeadShot;
		HashMap<UUID, Integer> Flare = getSnipcherdata().Flare;
		HashMap<UUID, Integer> Rope = getSnipcherdata().Rope;
		HashMap<UUID, Integer> ArmourPiercingArrow = getSnipcherdata().ArmourPiercingArrow;
		HashMap<UUID, Integer> FlashBomb = getSnipcherdata().FlashBomb;
		HashMap<UUID, Integer> Remodeling = getSnipcherdata().Remodeling;
		HashMap<UUID, Integer> SkillPoints = getSnipcherdata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");


	}

	
    
	@EventHandler
	public void Snipskilllev(PlayerLevelChangeEvent e)
	{

        String path = new File("").getAbsolutePath();
		Player p = (Player) e.getPlayer();

		HashMap<UUID, Integer> Camouflage = getSnipcherdata().Camouflage;
		HashMap<UUID, Integer> AirStrike = getSnipcherdata().AirStrike;
		HashMap<UUID, Integer> HeadShot = getSnipcherdata().HeadShot;
		HashMap<UUID, Integer> Flare = getSnipcherdata().Flare;
		HashMap<UUID, Integer> Rope = getSnipcherdata().Rope;
		HashMap<UUID, Integer> ArmourPiercingArrow = getSnipcherdata().ArmourPiercingArrow;
		HashMap<UUID, Integer> FlashBomb = getSnipcherdata().FlashBomb;
		HashMap<UUID, Integer> Remodeling = getSnipcherdata().Remodeling;
		HashMap<UUID, Integer> SkillPoints = getSnipcherdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
		        
			}
			else
			{	

				Camouflage.remove(p.getUniqueId());
				Camouflage.put(p.getUniqueId(), 0);
				AirStrike.remove(p.getUniqueId());
				AirStrike.put(p.getUniqueId(), 0);
				HeadShot.remove(p.getUniqueId());
				HeadShot.put(p.getUniqueId(), 0);
				Flare.remove(p.getUniqueId());
				Flare.put(p.getUniqueId(), 0);
				Rope.remove(p.getUniqueId());
				Rope.put(p.getUniqueId(), 0);
				Remodeling.remove(p.getUniqueId());
				Remodeling.put(p.getUniqueId(), 0);
				FlashBomb.remove(p.getUniqueId());
				FlashBomb.put(p.getUniqueId(), 0);
				ArmourPiercingArrow.remove(p.getUniqueId());
				ArmourPiercingArrow.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Snipskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Snipskills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				SnipSkillsGui l = new SnipSkillsGui();
				
				HashMap<UUID, Integer> Camouflage = getSnipcherdata().Camouflage;
				HashMap<UUID, Integer> AirStrike = getSnipcherdata().AirStrike;
				HashMap<UUID, Integer> HeadShot = getSnipcherdata().HeadShot;
				HashMap<UUID, Integer> Flare = getSnipcherdata().Flare;
				HashMap<UUID, Integer> Rope = getSnipcherdata().Rope;
				HashMap<UUID, Integer> ArmourPiercingArrow = getSnipcherdata().ArmourPiercingArrow;
				HashMap<UUID, Integer> FlashBomb = getSnipcherdata().FlashBomb;
				HashMap<UUID, Integer> Remodeling = getSnipcherdata().Remodeling;
				HashMap<UUID, Integer> SkillPoints = getSnipcherdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Camouflage":
					case "ÀºÆó":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Camouflage.get(p.getUniqueId()) < 1){
								Camouflage.put(p.getUniqueId(), Camouflage.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Camouflage.get(p.getUniqueId()) >= 1){
								Camouflage.put(p.getUniqueId(), Camouflage.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Camouflage.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Camouflage.get(p.getUniqueId()) < 1) {
										Camouflage.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Camouflage.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Camouflage.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Camouflage.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Camouflage.get(p.getUniqueId()));
								Camouflage.put(p.getUniqueId(), 0);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						break;		}
						
					case "HeadShot":
					case "Çìµå¼¦":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								HeadShot.put(p.getUniqueId(), HeadShot.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(HeadShot.get(p.getUniqueId()) >= 1){
								HeadShot.put(p.getUniqueId(), HeadShot.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								HeadShot.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HeadShot.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(HeadShot.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HeadShot.get(p.getUniqueId()));
								HeadShot.put(p.getUniqueId(), 0);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						break;		}		
					case "Flare":
					case "Á¶¸íÁö·Ú":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Flare.get(p.getUniqueId()) < 1){
								Flare.put(p.getUniqueId(), Flare.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Flare.get(p.getUniqueId()) >= 1){
								Flare.put(p.getUniqueId(), Flare.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Flare.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Flare.get(p.getUniqueId()) < 1) {
										Flare.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Flare.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Flare.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Flare.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Flare.get(p.getUniqueId()));
								Flare.put(p.getUniqueId(), 0);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						break;		}
					case "FlashBomb":
					case "¼¶±¤Åº":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && FlashBomb.get(p.getUniqueId())<50){
								FlashBomb.put(p.getUniqueId(), FlashBomb.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(FlashBomb.get(p.getUniqueId()) >= 1){
								FlashBomb.put(p.getUniqueId(), FlashBomb.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(FlashBomb.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(FlashBomb.get(p.getUniqueId())<50){
										final int a = 50 - FlashBomb.get(p.getUniqueId());
										FlashBomb.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									FlashBomb.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+FlashBomb.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(FlashBomb.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+FlashBomb.get(p.getUniqueId()));
								FlashBomb.put(p.getUniqueId(), 0);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						break;		}
					case "AirStrike":
					case "°ø½À":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && AirStrike.get(p.getUniqueId())<50){
								AirStrike.put(p.getUniqueId(), AirStrike.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(AirStrike.get(p.getUniqueId()) >= 1){
								AirStrike.put(p.getUniqueId(), AirStrike.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(AirStrike.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(AirStrike.get(p.getUniqueId())<50){
										final int a = 50 - AirStrike.get(p.getUniqueId());
										AirStrike.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									AirStrike.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+AirStrike.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(AirStrike.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+AirStrike.get(p.getUniqueId()));
								AirStrike.put(p.getUniqueId(), 0);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						break;		}
					case "ArmourPiercingArrow":
					case "Ã¶°©È­»ì":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && ArmourPiercingArrow.get(p.getUniqueId())<50){
								ArmourPiercingArrow.put(p.getUniqueId(), ArmourPiercingArrow.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(ArmourPiercingArrow.get(p.getUniqueId()) >= 1){
								ArmourPiercingArrow.put(p.getUniqueId(), ArmourPiercingArrow.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(ArmourPiercingArrow.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(ArmourPiercingArrow.get(p.getUniqueId())<50){
										final int a = 50 - ArmourPiercingArrow.get(p.getUniqueId());
										ArmourPiercingArrow.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									ArmourPiercingArrow.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ArmourPiercingArrow.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(ArmourPiercingArrow.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+ArmourPiercingArrow.get(p.getUniqueId()));
								ArmourPiercingArrow.put(p.getUniqueId(), 0);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						break;		}
					case "Remodeling":
					case "°³Á¶":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Remodeling.get(p.getUniqueId())<50){
								Remodeling.put(p.getUniqueId(), Remodeling.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Remodeling.get(p.getUniqueId()) >= 1){
								Remodeling.put(p.getUniqueId(), Remodeling.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Remodeling.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Remodeling.get(p.getUniqueId())<50){
										final int a = 50 - Remodeling.get(p.getUniqueId());
										Remodeling.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Remodeling.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Remodeling.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Remodeling.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Remodeling.get(p.getUniqueId()));
								Remodeling.put(p.getUniqueId(), 0);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						break;		}			
					case "Rope":
					case "¹åÁÙÅ¸±â":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Rope.get(p.getUniqueId()) < 1){
								Rope.put(p.getUniqueId(), Rope.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Rope.get(p.getUniqueId()) >= 1){
								Rope.put(p.getUniqueId(), Rope.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Rope.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Rope.get(p.getUniqueId()) < 1) {
										Rope.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Rope.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Rope.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Rope.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Rope.get(p.getUniqueId()));
								Rope.put(p.getUniqueId(), 0);
								new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
						        l.Snipskillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":	
					case "½ºÅ³Æ÷ÀÎÆ®":	{			
						Camouflage.put(p.getUniqueId(), 0);
						AirStrike.put(p.getUniqueId(), 0);
						HeadShot.put(p.getUniqueId(), 0);
						Flare.put(p.getUniqueId(), 0);
						Rope.put(p.getUniqueId(), 0);
						Remodeling.put(p.getUniqueId(), 0);
						FlashBomb.put(p.getUniqueId(), 0);
						Camouflage.put(p.getUniqueId(), 0);
						ArmourPiercingArrow.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new SnipSkillsData(Camouflage, AirStrike, HeadShot, Flare, Rope, FlashBomb, ArmourPiercingArrow, Remodeling, SkillPoints).saveData(path +"/plugins/RPGskills/SnipskillsData.data");
				        l.Snipskillsinv(p);
				}break;	}
			
			}
			
		}
		
	}
    
    public static SnipSkillsData getSnipcherdata(){
        String path = new File("").getAbsolutePath();
        SnipSkillsData data = new SnipSkillsData(SnipSkillsData.loadData(path +"/plugins/RPGskills/SnipskillsData.data"));
		return data;
	}
}
