package io.github.chw3021.classes.hunter;


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


public class HunSkillsData implements Serializable, Listener{
	
	private static transient final long serialVersionUID = -4014081835907447552L;


	public final HashMap<UUID, Integer> Chain;
	public final HashMap<UUID, Integer> Webthrow;
	public final HashMap<UUID, Integer> Dodge;
	public final HashMap<UUID, Integer> HuntingStart;
	public final HashMap<UUID, Integer> Daze;
	public final HashMap<UUID, Integer> SkullCrusher;
	public final HashMap<UUID, Integer> Backattack;
	public final HashMap<UUID, Integer> Atrocity;
	public final HashMap<UUID, Integer> Climb;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public HunSkillsData(HashMap<UUID, Integer> Chain, HashMap<UUID, Integer> Webthrow, HashMap<UUID, Integer> Dodge, HashMap<UUID, Integer> HuntingStart, HashMap<UUID, Integer> Daze, HashMap<UUID, Integer> SkullCrusher, HashMap<UUID, Integer> Backattack, HashMap<UUID, Integer> Atrocity, HashMap<UUID, Integer> Climb, HashMap<UUID, Integer> SkillPoints) {
    	this.Chain = Chain;
    	this.Webthrow = Webthrow;
    	this.Dodge = Dodge;
    	this.HuntingStart = HuntingStart;
    	this.Daze = Daze;
    	this.SkullCrusher = SkullCrusher;
    	this.Backattack = Backattack;
    	this.Atrocity = Atrocity;
    	this.Climb = Climb;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public HunSkillsData(HunSkillsData loadedData) {
    	this.Chain = loadedData.Chain;
    	this.Webthrow = loadedData.Webthrow;
    	this.Dodge = loadedData.Dodge;
    	this.HuntingStart = loadedData.HuntingStart;
    	this.Daze = loadedData.Daze;
    	this.SkullCrusher = loadedData.SkullCrusher;
    	this.Backattack = loadedData.Backattack;
    	this.Atrocity = loadedData.Atrocity;
    	this.Climb = loadedData.Climb;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public HunSkillsData saveData(String filePath) {
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
    public static HunSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            HunSkillsData data = (HunSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            HunSkillsData data = new HunSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
            
            return data;
        }
    }
    

	@EventHandler
	public void Hunskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();

        String path = new File("").getAbsolutePath();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("HunSkills"))
		{
			try 
			{
				if(!getHunterdata().SkillPoints.containsKey(p.getUniqueId()))
				{HashMap<UUID, Integer> Chain = getHunterdata().Chain;
				HashMap<UUID, Integer> Webthrow = getHunterdata().Webthrow;
				HashMap<UUID, Integer> Dodge = getHunterdata().Dodge;
				HashMap<UUID, Integer> HuntingStart = getHunterdata().HuntingStart;
				HashMap<UUID, Integer> SkullCrusher = getHunterdata().SkullCrusher;
				HashMap<UUID, Integer> Daze = getHunterdata().Daze;
				HashMap<UUID, Integer> Backattack = getHunterdata().Backattack;
				HashMap<UUID, Integer> Atrocity = getHunterdata().Atrocity;
				HashMap<UUID, Integer> Climb = getHunterdata().Climb;
				HashMap<UUID, Integer> SkillPoints = getHunterdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					Chain.put(p.getUniqueId(), 0);
					Webthrow.put(p.getUniqueId(), 0);
					Dodge.put(p.getUniqueId(), 0);
					HuntingStart.put(p.getUniqueId(), 0);
					Daze.put(p.getUniqueId(), 0);
					SkullCrusher.put(p.getUniqueId(), 0);
					Backattack.put(p.getUniqueId(), 0);
					Atrocity.put(p.getUniqueId(), 0);
					Climb.put(p.getUniqueId(), 0);
					new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");

		            HunSkillsGui hsg = new HunSkillsGui();
					hsg.Hunskillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{HashMap<UUID, Integer> Chain = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Webthrow = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Dodge = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> HuntingStart = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Daze = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> SkullCrusher = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Backattack = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Atrocity = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> Climb = new HashMap<UUID, Integer>();
			HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				Chain.put(p.getUniqueId(), 0);
				Webthrow.put(p.getUniqueId(), 0);
				Dodge.put(p.getUniqueId(), 0);
				HuntingStart.put(p.getUniqueId(), 0);
				Daze.put(p.getUniqueId(), 0);
				SkullCrusher.put(p.getUniqueId(), 0);
				Backattack.put(p.getUniqueId(), 0);
				Atrocity.put(p.getUniqueId(), 0);
				Climb.put(p.getUniqueId(), 0);
				new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");

	            HunSkillsGui hsg = new HunSkillsGui();
				hsg.Hunskillsinv(p);
			}
		}
	}

	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Chain = getHunterdata().Chain;
		HashMap<UUID, Integer> Webthrow = getHunterdata().Webthrow;
		HashMap<UUID, Integer> Dodge = getHunterdata().Dodge;
		HashMap<UUID, Integer> HuntingStart = getHunterdata().HuntingStart;
		HashMap<UUID, Integer> SkullCrusher = getHunterdata().SkullCrusher;
		HashMap<UUID, Integer> Daze = getHunterdata().Daze;
		HashMap<UUID, Integer> Backattack = getHunterdata().Backattack;
		HashMap<UUID, Integer> Atrocity = getHunterdata().Atrocity;
		HashMap<UUID, Integer> Climb = getHunterdata().Climb;
		HashMap<UUID, Integer> SkillPoints = getHunterdata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");


	}

    
	@EventHandler
	public void Hunskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();

        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Chain = getHunterdata().Chain;
		HashMap<UUID, Integer> Webthrow = getHunterdata().Webthrow;
		HashMap<UUID, Integer> Dodge = getHunterdata().Dodge;
		HashMap<UUID, Integer> HuntingStart = getHunterdata().HuntingStart;
		HashMap<UUID, Integer> SkullCrusher = getHunterdata().SkullCrusher;
		HashMap<UUID, Integer> Daze = getHunterdata().Daze;
		HashMap<UUID, Integer> Backattack = getHunterdata().Backattack;
		HashMap<UUID, Integer> Atrocity = getHunterdata().Atrocity;
		HashMap<UUID, Integer> Climb = getHunterdata().Climb;
		HashMap<UUID, Integer> SkillPoints = getHunterdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
		        
			}
			else
			{	

				Chain.remove(p.getUniqueId());
				Chain.put(p.getUniqueId(), 0);
				Webthrow.remove(p.getUniqueId());
				Webthrow.put(p.getUniqueId(), 0);
				Dodge.remove(p.getUniqueId());
				Dodge.put(p.getUniqueId(), 0);
				HuntingStart.remove(p.getUniqueId());
				HuntingStart.put(p.getUniqueId(), 0);
				Backattack.remove(p.getUniqueId());
				Backattack.put(p.getUniqueId(), 0);
				Daze.remove(p.getUniqueId());
				Daze.put(p.getUniqueId(), 0);
				SkullCrusher.remove(p.getUniqueId());
				SkullCrusher.put(p.getUniqueId(), 0);
				Atrocity.remove(p.getUniqueId());
				Atrocity.put(p.getUniqueId(), 0);
				Climb.remove(p.getUniqueId());
				Climb.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Hunskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("HunSkills"))
		{
			e.setCancelled(true);

	        String path = new File("").getAbsolutePath();
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				HunSkillsGui hsg = new HunSkillsGui();
				
				HashMap<UUID, Integer> Chain = getHunterdata().Chain;
				HashMap<UUID, Integer> Webthrow = getHunterdata().Webthrow;
				HashMap<UUID, Integer> Dodge = getHunterdata().Dodge;
				HashMap<UUID, Integer> HuntingStart = getHunterdata().HuntingStart;
				HashMap<UUID, Integer> SkullCrusher = getHunterdata().SkullCrusher;
				HashMap<UUID, Integer> Daze = getHunterdata().Daze;
				HashMap<UUID, Integer> Backattack = getHunterdata().Backattack;
				HashMap<UUID, Integer> Atrocity = getHunterdata().Atrocity;
				HashMap<UUID, Integer> Climb = getHunterdata().Climb;
				HashMap<UUID, Integer> SkillPoints = getHunterdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "TurnOver":
					case "뒤집기":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Chain.get(p.getUniqueId()) < 1){
								Chain.put(p.getUniqueId(), Chain.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Chain.get(p.getUniqueId()) >= 1){
								Chain.put(p.getUniqueId(), Chain.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Chain.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Chain.get(p.getUniqueId()) < 1) {
										Chain.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Chain.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Chain.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Chain.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Chain.get(p.getUniqueId()));
								Chain.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
						
					case "Webthrow":
					case "그물투척":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Webthrow.get(p.getUniqueId())<1){
								Webthrow.put(p.getUniqueId(), Webthrow.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Webthrow.get(p.getUniqueId()) >= 1){
								Webthrow.put(p.getUniqueId(), Webthrow.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Webthrow.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Webthrow.get(p.getUniqueId())<1){
										Webthrow.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - Webthrow.get(p.getUniqueId()));								
									}
								}
								else{
									Webthrow.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Webthrow.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Webthrow.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Webthrow.get(p.getUniqueId()));
								Webthrow.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}		
					case "Dodge":
					case "회피":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Dodge.get(p.getUniqueId()) < 1){
								Dodge.put(p.getUniqueId(), Dodge.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Dodge.get(p.getUniqueId()) >= 1){
								Dodge.put(p.getUniqueId(), Dodge.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Dodge.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Dodge.get(p.getUniqueId()) < 1) {
										Dodge.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Dodge.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Dodge.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Dodge.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Dodge.get(p.getUniqueId()));
								Dodge.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
					case "Daze":
					case "참격":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Daze.get(p.getUniqueId())<50){
								Daze.put(p.getUniqueId(), Daze.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Daze.get(p.getUniqueId()) >= 1){
								Daze.put(p.getUniqueId(), Daze.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Daze.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Daze.get(p.getUniqueId())<50){
										final int a = 50 - Daze.get(p.getUniqueId());
										Daze.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
															
									}
								}
								else{
									Daze.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Daze.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Daze.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Daze.get(p.getUniqueId()));
								Daze.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
					case "SkullCrusher":
					case "두개골분쇄":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && SkullCrusher.get(p.getUniqueId())<50){
								SkullCrusher.put(p.getUniqueId(), SkullCrusher.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(SkullCrusher.get(p.getUniqueId()) >= 1){
								SkullCrusher.put(p.getUniqueId(), SkullCrusher.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(SkullCrusher.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(SkullCrusher.get(p.getUniqueId())<50){
										final int a = 50 - SkullCrusher.get(p.getUniqueId());
										SkullCrusher.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
															
									}
								}
								else{
									SkullCrusher.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SkullCrusher.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(SkullCrusher.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SkullCrusher.get(p.getUniqueId()));
								SkullCrusher.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
					case "Backattack":
					case "암습":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Backattack.get(p.getUniqueId()) < 1){
								Backattack.put(p.getUniqueId(), Backattack.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Backattack.get(p.getUniqueId()) >= 1){
								Backattack.put(p.getUniqueId(), Backattack.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Backattack.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Backattack.get(p.getUniqueId()) < 1) {
										Backattack.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Backattack.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Backattack.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Backattack.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Backattack.get(p.getUniqueId()));
								Backattack.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;	}
					case "Climbing":
					case "등반":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Climb.get(p.getUniqueId()) < 1){
								Climb.put(p.getUniqueId(), Climb.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Climb.get(p.getUniqueId()) >= 1){
								Climb.put(p.getUniqueId(), Climb.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Climb.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Climb.get(p.getUniqueId()) < 1) {
										Climb.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Climb.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Climb.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Climb.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Climb.get(p.getUniqueId()));
								Climb.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}				
					case "Atrocity":
					case "극악무도":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Atrocity.put(p.getUniqueId(), Atrocity.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Atrocity.get(p.getUniqueId()) >= 1){
								Atrocity.put(p.getUniqueId(), Atrocity.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Atrocity.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Atrocity.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Atrocity.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Atrocity.get(p.getUniqueId()));
								Atrocity.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
					case "Hunting":
					case "사냥":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && HuntingStart.get(p.getUniqueId())<50){
								HuntingStart.put(p.getUniqueId(), HuntingStart.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(HuntingStart.get(p.getUniqueId()) >= 1){
								HuntingStart.put(p.getUniqueId(), HuntingStart.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(HuntingStart.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(HuntingStart.get(p.getUniqueId())<50){
										final int a = 50 - HuntingStart.get(p.getUniqueId());
										HuntingStart.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
																
									}
								}
								else{
									HuntingStart.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HuntingStart.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(HuntingStart.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HuntingStart.get(p.getUniqueId()));
								HuntingStart.put(p.getUniqueId(), 0);
								new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
						        hsg.Hunskillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						Chain.put(p.getUniqueId(), 0);
						Webthrow.put(p.getUniqueId(), 0);
						Dodge.put(p.getUniqueId(), 0);
						HuntingStart.put(p.getUniqueId(), 0);
						Backattack.put(p.getUniqueId(), 0);
						Climb.put(p.getUniqueId(), 0);
						Daze.put(p.getUniqueId(), 0);
						Chain.put(p.getUniqueId(), 0);
						SkullCrusher.put(p.getUniqueId(), 0);
						Atrocity.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new HunSkillsData(Chain, Webthrow, Dodge, HuntingStart, Daze, SkullCrusher, Backattack, Atrocity, Climb, SkillPoints).saveData(path +"/plugins/RPGskills/HunSkillsData.data");
				        hsg.Hunskillsinv(p);
				        break;	}}
			
			}
			
		}
		
	}
    
    public static HunSkillsData getHunterdata(){
        String path = new File("").getAbsolutePath();
        HunSkillsData data = new HunSkillsData(HunSkillsData.loadData(path +"/plugins/RPGskills/HunSkillsData.data"));
		return data;
	}
}