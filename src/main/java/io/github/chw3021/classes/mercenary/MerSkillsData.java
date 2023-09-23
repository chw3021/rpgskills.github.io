package io.github.chw3021.classes.mercenary;


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


public class MerSkillsData implements Serializable, Listener{
	


	/**
	 * 
	 */
	private static transient final long serialVersionUID = -8332939386579173581L;
	public final HashMap<UUID, Integer> Swipe;
	public final HashMap<UUID, Integer> Drain;
	public final HashMap<UUID, Integer> Spray;
	public final HashMap<UUID, Integer> Conversion;
	public final HashMap<UUID, Integer> Inhale;
	public final HashMap<UUID, Integer> Flurry;
	public final HashMap<UUID, Integer> Crimpson;
	public final HashMap<UUID, Integer> Lunacy;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public MerSkillsData(HashMap<UUID, Integer> Swipe, HashMap<UUID, Integer> Drain, HashMap<UUID, Integer> Spray, HashMap<UUID, Integer> Dread, HashMap<UUID, Integer> Inhale, HashMap<UUID, Integer> Burstout, HashMap<UUID, Integer> Undying, HashMap<UUID, Integer> Lunacy, HashMap<UUID, Integer> SkillPoints) {
    	this.Swipe = Swipe;
    	this.Drain = Drain;
    	this.Spray = Spray;
    	this.Conversion = Dread;
    	this.Inhale = Inhale;
    	this.Flurry = Burstout;
    	this.Crimpson = Undying;
    	this.Lunacy = Lunacy;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public MerSkillsData(MerSkillsData loadedData) {
    	this.Swipe = loadedData.Swipe;
    	this.Drain = loadedData.Drain;
    	this.Spray = loadedData.Spray;
    	this.Conversion = loadedData.Conversion;
    	this.Inhale = loadedData.Inhale;
    	this.Flurry = loadedData.Flurry;
    	this.Crimpson = loadedData.Crimpson;
    	this.Lunacy = loadedData.Lunacy;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public MerSkillsData saveData(String filePath) {
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
    public static MerSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            MerSkillsData data = (MerSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException |NullPointerException e) {
            // TODO Auto-generated catch block

            String path = new File("").getAbsolutePath();
        	
            MerSkillsData data = new MerSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/BerskillsData.data");
           
            e.printStackTrace();
            return data;
        }
    }
    

	@EventHandler
	public void Berskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Berskills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getBerserkerdata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> Swipe = getBerserkerdata().Swipe;
					HashMap<UUID, Integer> Drain = getBerserkerdata().Drain;
					HashMap<UUID, Integer> Spray = getBerserkerdata().Spray;
					HashMap<UUID, Integer> Dread = getBerserkerdata().Conversion;
					HashMap<UUID, Integer> Burstout = getBerserkerdata().Flurry;
					HashMap<UUID, Integer> Inhale = getBerserkerdata().Inhale;
					HashMap<UUID, Integer> Undying = getBerserkerdata().Crimpson;
					HashMap<UUID, Integer> Lunacy = getBerserkerdata().Lunacy;
					HashMap<UUID, Integer> SkillPoints = getBerserkerdata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					Swipe.put(p.getUniqueId(), 0);
					Drain.put(p.getUniqueId(), 0);
					Spray.put(p.getUniqueId(), 0);
					Dread.put(p.getUniqueId(), 0);
					Inhale.put(p.getUniqueId(), 0);
					Burstout.put(p.getUniqueId(), 0);
					Undying.put(p.getUniqueId(), 0);
					Lunacy.put(p.getUniqueId(), 0);
					new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");

		            MerSkillsGui bsg = new MerSkillsGui();
					bsg.Berskillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Swipe = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Drain = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Spray = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Dread = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Inhale = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Burstout = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Undying = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Lunacy = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				Swipe.put(p.getUniqueId(), 0);
				Drain.put(p.getUniqueId(), 0);
				Spray.put(p.getUniqueId(), 0);
				Dread.put(p.getUniqueId(), 0);
				Inhale.put(p.getUniqueId(), 0);
				Burstout.put(p.getUniqueId(), 0);
				Undying.put(p.getUniqueId(), 0);
				Lunacy.put(p.getUniqueId(), 0);
				new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");

	            MerSkillsGui bsg = new MerSkillsGui();
				bsg.Berskillsinv(p);
			}
		}
	}
	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Swipe = getBerserkerdata().Swipe;
		HashMap<UUID, Integer> Drain = getBerserkerdata().Drain;
		HashMap<UUID, Integer> Spray = getBerserkerdata().Spray;
		HashMap<UUID, Integer> Dread = getBerserkerdata().Conversion;
		HashMap<UUID, Integer> Burstout = getBerserkerdata().Flurry;
		HashMap<UUID, Integer> Inhale = getBerserkerdata().Inhale;
		HashMap<UUID, Integer> Undying = getBerserkerdata().Crimpson;
		HashMap<UUID, Integer> Lunacy = getBerserkerdata().Lunacy;
		HashMap<UUID, Integer> SkillPoints = getBerserkerdata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");


	}

	
    
	@EventHandler
	public void Berskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();

        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Swipe = getBerserkerdata().Swipe;
		HashMap<UUID, Integer> Drain = getBerserkerdata().Drain;
		HashMap<UUID, Integer> Spray = getBerserkerdata().Spray;
		HashMap<UUID, Integer> Dread = getBerserkerdata().Conversion;
		HashMap<UUID, Integer> Burstout = getBerserkerdata().Flurry;
		HashMap<UUID, Integer> Inhale = getBerserkerdata().Inhale;
		HashMap<UUID, Integer> Undying = getBerserkerdata().Crimpson;
		HashMap<UUID, Integer> Lunacy = getBerserkerdata().Lunacy;
		HashMap<UUID, Integer> SkillPoints = getBerserkerdata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.replace(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
		  
			}
			else
			{	

				Swipe.remove(p.getUniqueId());
				Swipe.put(p.getUniqueId(), 0);
				Drain.remove(p.getUniqueId());
				Drain.put(p.getUniqueId(), 0);
				Spray.remove(p.getUniqueId());
				Spray.put(p.getUniqueId(), 0);
				Dread.remove(p.getUniqueId());
				Dread.put(p.getUniqueId(), 0);
				Undying.remove(p.getUniqueId());
				Undying.put(p.getUniqueId(), 0);
				Inhale.remove(p.getUniqueId());
				Inhale.put(p.getUniqueId(), 0);
				Burstout.remove(p.getUniqueId());
				Burstout.put(p.getUniqueId(), 0);
				Lunacy.remove(p.getUniqueId());
				Lunacy.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
		     
		}
		}
	}
	
	@EventHandler
	public void Berskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Berskills"))
		{
			e.setCancelled(true);
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{
		        String path = new File("").getAbsolutePath();

				MerSkillsGui bsg = new MerSkillsGui();
				
				HashMap<UUID, Integer> Swipe = getBerserkerdata().Swipe;
				HashMap<UUID, Integer> Drain = getBerserkerdata().Drain;
				HashMap<UUID, Integer> Spray = getBerserkerdata().Spray;
				HashMap<UUID, Integer> Dread = getBerserkerdata().Conversion;
				HashMap<UUID, Integer> Burstout = getBerserkerdata().Flurry;
				HashMap<UUID, Integer> Inhale = getBerserkerdata().Inhale;
				HashMap<UUID, Integer> Undying = getBerserkerdata().Crimpson;
				HashMap<UUID, Integer> Lunacy = getBerserkerdata().Lunacy;
				HashMap<UUID, Integer> SkillPoints = getBerserkerdata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Swipe":
					case "휩쓸기":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Swipe.get(p.getUniqueId())<50){
								Swipe.put(p.getUniqueId(), Swipe.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Swipe.get(p.getUniqueId()) >= 1){
								Swipe.put(p.getUniqueId(), Swipe.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Swipe.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Swipe.get(p.getUniqueId())<50){	
										final int a = 50 - Swipe.get(p.getUniqueId());
										Swipe.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);													
									}
								}
								else{
									Swipe.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Swipe.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Swipe.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Swipe.get(p.getUniqueId()));
								Swipe.put(p.getUniqueId(), 0);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						break;		}
						
					/*case "Drain":
					case "배출":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Drain.get(p.getUniqueId())<50){
								Drain.put(p.getUniqueId(), Drain.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new BerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Drain.get(p.getUniqueId()) >= 1){
								Drain.put(p.getUniqueId(), Drain.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new BerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Drain.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Drain.get(p.getUniqueId())<50){		
										final int a = 50 - Drain.get(p.getUniqueId());
										Drain.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);													
									}
								}
								else{
									Drain.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Drain.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new BerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Drain.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Drain.get(p.getUniqueId()));
								Drain.put(p.getUniqueId(), 0);
								new BerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						break;		}		*/
					case "Spray":
					case "분사":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Spray.get(p.getUniqueId())<50){
								Spray.put(p.getUniqueId(), Spray.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Spray.get(p.getUniqueId()) >= 1){
								Spray.put(p.getUniqueId(), Spray.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Spray.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Spray.get(p.getUniqueId())<50){		
										final int a = 50 - Spray.get(p.getUniqueId());
										Spray.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);													
									}
								}
								else{
									Spray.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Spray.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Spray.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Spray.get(p.getUniqueId()));
								Spray.put(p.getUniqueId(), 0);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						break;		}
					case "Inhale":
					case "흡입":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Inhale.get(p.getUniqueId())<50){
								Inhale.put(p.getUniqueId(), Inhale.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Inhale.get(p.getUniqueId()) >= 1){
								Inhale.put(p.getUniqueId(), Inhale.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Inhale.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Inhale.get(p.getUniqueId())<50){		
										final int a = 50 - Inhale.get(p.getUniqueId());
										Inhale.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);													
									}
								}
								else{
									Inhale.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Inhale.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Inhale.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Inhale.get(p.getUniqueId()));
								Inhale.put(p.getUniqueId(), 0);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						break;		}
					case "Flurry":
					case "난무":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Burstout.get(p.getUniqueId())<50){
								Burstout.put(p.getUniqueId(), Burstout.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Burstout.get(p.getUniqueId()) >= 1){
								Burstout.put(p.getUniqueId(), Burstout.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Burstout.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Burstout.get(p.getUniqueId())<50){		
										final int a = 50 - Burstout.get(p.getUniqueId());
										Burstout.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);												
									}
								}
								else{
									Burstout.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Burstout.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Burstout.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Burstout.get(p.getUniqueId()));
								Burstout.put(p.getUniqueId(), 0);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						break;		}
					case "CrimsonAdvance":
					case "진홍빛전진":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Undying.get(p.getUniqueId())<50){
								Undying.put(p.getUniqueId(), Undying.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Undying.get(p.getUniqueId()) >= 1){
								Undying.put(p.getUniqueId(), Undying.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Undying.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Undying.get(p.getUniqueId())<50){
										final int a = 50 - Undying.get(p.getUniqueId());
										Undying.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);												
									}
								}
								else{
									Undying.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Undying.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Undying.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Undying.get(p.getUniqueId()));
								Undying.put(p.getUniqueId(), 0);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						break;		}				
					case "Lunacy":
					case "광기":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Lunacy.put(p.getUniqueId(), Lunacy.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Lunacy.get(p.getUniqueId()) >= 1){
								Lunacy.put(p.getUniqueId(), Lunacy.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Lunacy.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Lunacy.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Lunacy.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Lunacy.get(p.getUniqueId()));
								Lunacy.put(p.getUniqueId(), 0);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						break;		}
					case "Conversion":
					case "변신":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Dread.get(p.getUniqueId()) < 1){
								Dread.put(p.getUniqueId(), Dread.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Dread.get(p.getUniqueId()) >= 1){
								Dread.put(p.getUniqueId(), Dread.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Dread.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(Dread.get(p.getUniqueId()) < 1) {
										Dread.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - 1);										
									}							
								}
								else {
									Dread.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Dread.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);									
								}
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Dread.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Dread.get(p.getUniqueId()));
								Dread.put(p.getUniqueId(), 0);
								new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
						        bsg.Berskillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						Swipe.put(p.getUniqueId(), 0);
						Drain.put(p.getUniqueId(), 0);
						Spray.put(p.getUniqueId(), 0);
						Dread.put(p.getUniqueId(), 0);
						Undying.put(p.getUniqueId(), 0);
						Inhale.put(p.getUniqueId(), 0);
						Swipe.put(p.getUniqueId(), 0);
						Burstout.put(p.getUniqueId(), 0);
						Lunacy.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new MerSkillsData(Swipe, Drain, Spray, Dread, Inhale, Burstout, Undying, Lunacy, SkillPoints).saveData(path +"/plugins/RPGskills/BerskillsData.data");
				        bsg.Berskillsinv(p);
				}break;	}
			
			}
			
		}
		
	}
    
    public static MerSkillsData getBerserkerdata(){
        String path = new File("").getAbsolutePath();
        MerSkillsData data = new MerSkillsData(MerSkillsData.loadData(path +"/plugins/RPGskills/BerskillsData.data"));
		return data;
	}
}