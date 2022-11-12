package io.github.chw3021.classes.boxer;


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



public class BoxSkillsData implements Serializable, Listener{
	

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 593538713978778542L;
	public final HashMap<UUID, Integer> DempseyRoll;
	public final HashMap<UUID, Integer> Training;
	public final HashMap<UUID, Integer> Straight;
	public final HashMap<UUID, Integer> Parrying;
	public final HashMap<UUID, Integer> BodyBlow;
	public final HashMap<UUID, Integer> Counter;
	public final HashMap<UUID, Integer> Jab;
	public final HashMap<UUID, Integer> Rest;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public BoxSkillsData(HashMap<UUID, Integer> DempseyRoll, HashMap<UUID, Integer> Training, HashMap<UUID, Integer> Straight, HashMap<UUID, Integer> Parrying, HashMap<UUID, Integer> BodyBlow, HashMap<UUID, Integer> Counter, HashMap<UUID, Integer> Jab, HashMap<UUID, Integer> Rest, HashMap<UUID, Integer> SkillPoints) {
    	this.DempseyRoll = DempseyRoll;
    	this.Training = Training;
    	this.Straight = Straight;
    	this.Parrying = Parrying;
    	this.BodyBlow = BodyBlow;
    	this.Counter = Counter;
    	this.Jab = Jab;
    	this.Rest = Rest;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public BoxSkillsData(BoxSkillsData loadedData) {
    	this.DempseyRoll = loadedData.DempseyRoll;
    	this.Training = loadedData.Training;
    	this.Straight = loadedData.Straight;
    	this.Parrying = loadedData.Parrying;
    	this.BodyBlow = loadedData.BodyBlow;
    	this.Counter = loadedData.Counter;
    	this.Jab = loadedData.Jab;
    	this.Rest = loadedData.Rest;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public BoxSkillsData saveData(String filePath) {
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
    public static BoxSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            BoxSkillsData data = (BoxSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException |NullPointerException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            BoxSkillsData data = new BoxSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
            
            return data;
        }
    }
    

	@EventHandler
	public void Boxskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("BoxSkills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getBoxerdata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> DempseyRoll = getBoxerdata().DempseyRoll;
					HashMap<UUID, Integer> Training = getBoxerdata().Training;
					HashMap<UUID, Integer> Straight = getBoxerdata().Straight;
					HashMap<UUID, Integer> Parrying = getBoxerdata().Parrying;
					HashMap<UUID, Integer> Counter = getBoxerdata().Counter;
					HashMap<UUID, Integer> BodyBlow = getBoxerdata().BodyBlow;
					HashMap<UUID, Integer> Jab = getBoxerdata().Jab;
					HashMap<UUID, Integer> Rest = getBoxerdata().Rest;
					HashMap<UUID, Integer> SkillPoints = getBoxerdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					DempseyRoll.put(p.getUniqueId(), 0);
					Training.put(p.getUniqueId(), 0);
					Straight.put(p.getUniqueId(), 0);
					Parrying.put(p.getUniqueId(), 0);
					BodyBlow.put(p.getUniqueId(), 0);
					Counter.put(p.getUniqueId(), 0);
					Jab.put(p.getUniqueId(), 0);
					Rest.put(p.getUniqueId(), 0);
					new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");

					BoxSkillsGui bxg = new BoxSkillsGui();
					bxg.BoxSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> DempseyRoll = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Training = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Straight = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Parrying = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> BodyBlow = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Counter = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Jab = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Rest = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				DempseyRoll.put(p.getUniqueId(), 0);
				Training.put(p.getUniqueId(), 0);
				Straight.put(p.getUniqueId(), 0);
				Parrying.put(p.getUniqueId(), 0);
				BodyBlow.put(p.getUniqueId(), 0);
				Counter.put(p.getUniqueId(), 0);
				Jab.put(p.getUniqueId(), 0);
				Rest.put(p.getUniqueId(), 0);
				new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");

				BoxSkillsGui bxg = new BoxSkillsGui();
				bxg.BoxSkillsinv(p);
			}
		}
	}
    
	@EventHandler
	public void Boxskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();

        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> DempseyRoll = getBoxerdata().DempseyRoll;
		HashMap<UUID, Integer> Training = getBoxerdata().Training;
		HashMap<UUID, Integer> Straight = getBoxerdata().Straight;
		HashMap<UUID, Integer> Parrying = getBoxerdata().Parrying;
		HashMap<UUID, Integer> Counter = getBoxerdata().Counter;
		HashMap<UUID, Integer> BodyBlow = getBoxerdata().BodyBlow;
		HashMap<UUID, Integer> Jab = getBoxerdata().Jab;
		HashMap<UUID, Integer> Rest = getBoxerdata().Rest;
		HashMap<UUID, Integer> SkillPoints = getBoxerdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
		        
			}
			else
			{	

				DempseyRoll.put(p.getUniqueId(), 0);
				Training.put(p.getUniqueId(), 0);
				Straight.put(p.getUniqueId(), 0);
				Parrying.put(p.getUniqueId(), 0);
				Jab.put(p.getUniqueId(), 0);
				BodyBlow.put(p.getUniqueId(), 0);
				Counter.put(p.getUniqueId(), 0);
				Rest.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
		        
			}
		}
	}

	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> DempseyRoll = getBoxerdata().DempseyRoll;
		HashMap<UUID, Integer> Training = getBoxerdata().Training;
		HashMap<UUID, Integer> Straight = getBoxerdata().Straight;
		HashMap<UUID, Integer> Parrying = getBoxerdata().Parrying;
		HashMap<UUID, Integer> Counter = getBoxerdata().Counter;
		HashMap<UUID, Integer> BodyBlow = getBoxerdata().BodyBlow;
		HashMap<UUID, Integer> Jab = getBoxerdata().Jab;
		HashMap<UUID, Integer> Rest = getBoxerdata().Rest;
		HashMap<UUID, Integer> SkillPoints = getBoxerdata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");


	}

	@EventHandler
	public void Boxskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("BoxSkills"))
		{
	        String path = new File("").getAbsolutePath();
			e.setCancelled(true);
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				BoxSkillsGui l = new BoxSkillsGui();
				HashMap<UUID, Integer> DempseyRoll = getBoxerdata().DempseyRoll;
				HashMap<UUID, Integer> Training = getBoxerdata().Training;
				HashMap<UUID, Integer> Straight = getBoxerdata().Straight;
				HashMap<UUID, Integer> Parrying = getBoxerdata().Parrying;
				HashMap<UUID, Integer> Counter = getBoxerdata().Counter;
				HashMap<UUID, Integer> BodyBlow = getBoxerdata().BodyBlow;
				HashMap<UUID, Integer> Jab = getBoxerdata().Jab;
				HashMap<UUID, Integer> Rest = getBoxerdata().Rest;
				HashMap<UUID, Integer> SkillPoints = getBoxerdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "DempseyRoll":
					case "뎀프시롤":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && DempseyRoll.get(p.getUniqueId())<50){
								DempseyRoll.put(p.getUniqueId(), DempseyRoll.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(DempseyRoll.get(p.getUniqueId()) >= 1){
								DempseyRoll.put(p.getUniqueId(), DempseyRoll.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(DempseyRoll.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(DempseyRoll.get(p.getUniqueId())<50){
										final int a = 50 - DempseyRoll.get(p.getUniqueId());
										DempseyRoll.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);					
									}
								}
								else{
									DempseyRoll.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DempseyRoll.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(DempseyRoll.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+DempseyRoll.get(p.getUniqueId()));
								DempseyRoll.put(p.getUniqueId(), 0);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						break;		
						
					case "Training":
					case "단련":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Training.put(p.getUniqueId(), Training.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Training.get(p.getUniqueId()) >= 1){
								Training.put(p.getUniqueId(), Training.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Training.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Training.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Training.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Training.get(p.getUniqueId()));
								Training.put(p.getUniqueId(), 0);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						break;			
					case "Straight":
					case "붕권":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Straight.get(p.getUniqueId())<50){
								Straight.put(p.getUniqueId(), Straight.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Straight.get(p.getUniqueId()) >= 1){
								Straight.put(p.getUniqueId(), Straight.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Straight.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Straight.get(p.getUniqueId())<50){
										final int a = 50 - Straight.get(p.getUniqueId());
										Straight.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
																
									}
								}
								else{
									Straight.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Straight.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Straight.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Straight.get(p.getUniqueId()));
								Straight.put(p.getUniqueId(), 0);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						break;		
					case "BodyBlow":
					case "철산고":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && BodyBlow.get(p.getUniqueId())<50){
								BodyBlow.put(p.getUniqueId(), BodyBlow.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(BodyBlow.get(p.getUniqueId()) >= 1){
								BodyBlow.put(p.getUniqueId(), BodyBlow.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(BodyBlow.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(BodyBlow.get(p.getUniqueId())<50){
										final int a = 50 - BodyBlow.get(p.getUniqueId());
										BodyBlow.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									BodyBlow.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+BodyBlow.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(BodyBlow.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+BodyBlow.get(p.getUniqueId()));
								BodyBlow.put(p.getUniqueId(), 0);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						break;		
					case "Counter":
					case "반격":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Counter.get(p.getUniqueId())<50){
								Counter.put(p.getUniqueId(), Counter.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Counter.get(p.getUniqueId()) >= 1){
								Counter.put(p.getUniqueId(), Counter.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Counter.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Counter.get(p.getUniqueId())<50){
										final int a = 50 - Counter.get(p.getUniqueId());
										Counter.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Counter.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Counter.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Counter.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Counter.get(p.getUniqueId()));
								Counter.put(p.getUniqueId(), 0);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						break;		
					case "FlikerJab":
					case "질풍권":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Jab.get(p.getUniqueId())<50){
								Jab.put(p.getUniqueId(), Jab.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Jab.get(p.getUniqueId()) >= 1){
								Jab.put(p.getUniqueId(), Jab.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Jab.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Jab.get(p.getUniqueId())<50){
										final int a = 50 - Jab.get(p.getUniqueId());
										Jab.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Jab.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Jab.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Jab.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Jab.get(p.getUniqueId()));
								Jab.put(p.getUniqueId(), 0);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						break;					
					case "Rest":
					case "심호흡":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Rest.get(p.getUniqueId()) < 1){
								Rest.put(p.getUniqueId(), Rest.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Rest.get(p.getUniqueId()) >= 1){
								Rest.put(p.getUniqueId(), Rest.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Rest.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Rest.get(p.getUniqueId()) < 1) {
										Rest.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Rest.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Rest.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Rest.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Rest.get(p.getUniqueId()));
								Rest.put(p.getUniqueId(), 0);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						break;		
					case "Adrenaline":
					case "아드레날린":
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Parrying.get(p.getUniqueId()) < 1){
								Parrying.put(p.getUniqueId(), Parrying.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Parrying.get(p.getUniqueId()) >= 1){
								Parrying.put(p.getUniqueId(), Parrying.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Parrying.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Parrying.get(p.getUniqueId()) < 1) {
										Parrying.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Parrying.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Parrying.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Parrying.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Parrying.get(p.getUniqueId()));
								Parrying.put(p.getUniqueId(), 0);
								new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
						        l.BoxSkillsinv(p);
							}
						}
						break;		
					case "SkillPoints":		
					case "스킬포인트":		
						DempseyRoll.put(p.getUniqueId(), 0);
						Training.put(p.getUniqueId(), 0);
						Straight.put(p.getUniqueId(), 0);
						Parrying.put(p.getUniqueId(), 0);
						Jab.put(p.getUniqueId(), 0);
						BodyBlow.put(p.getUniqueId(), 0);
						DempseyRoll.put(p.getUniqueId(), 0);
						Counter.put(p.getUniqueId(), 0);
						Rest.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new BoxSkillsData(DempseyRoll, Training, Straight, Parrying, BodyBlow, Counter, Jab, Rest, SkillPoints).saveData(path +"/plugins/RPGskills/BoxSkillsData.data");
				        l.BoxSkillsinv(p);
				        break;	
				}
			
			}
			
		}
		
	}
    
    public static BoxSkillsData getBoxerdata(){
        String path = new File("").getAbsolutePath();
        BoxSkillsData data = new BoxSkillsData(BoxSkillsData.loadData(path +"/plugins/RPGskills/BoxSkillsData.data"));
		return data;
	}
}
