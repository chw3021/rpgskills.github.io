package io.github.chw3021.classes.firemage;


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


public class FireSkillsData implements Serializable, Listener{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -353355589904236766L;
	public final HashMap<UUID, Integer> FlowingLava;
	public final HashMap<UUID, Integer> Ring;
	public final HashMap<UUID, Integer> Fireball;
	public final HashMap<UUID, Integer> Spread;
	public final HashMap<UUID, Integer> Breath;
	public final HashMap<UUID, Integer> AliveFlame;
	public final HashMap<UUID, Integer> HotBody;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public FireSkillsData(HashMap<UUID, Integer> FlowingLava, HashMap<UUID, Integer> Ring, HashMap<UUID, Integer> Fireball, HashMap<UUID, Integer> Spread, HashMap<UUID, Integer> Breath, HashMap<UUID, Integer> AliveFlame, HashMap<UUID, Integer> HotBody, HashMap<UUID, Integer> SkillPoints) {
    	this.FlowingLava = FlowingLava;
    	this.Ring = Ring;
    	this.Fireball = Fireball;
    	this.Spread = Spread;
    	this.Breath = Breath;
    	this.AliveFlame = AliveFlame;
    	this.HotBody = HotBody;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for lomageg
    public FireSkillsData(FireSkillsData loadedData) {
    	this.FlowingLava = loadedData.FlowingLava;
    	this.Ring = loadedData.Ring;
    	this.Fireball = loadedData.Fireball;
    	this.Spread = loadedData.Spread;
    	this.Breath = loadedData.Breath;
    	this.AliveFlame = loadedData.AliveFlame;
    	this.HotBody = loadedData.HotBody;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public FireSkillsData saveData(String filePath) {
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
    public static FireSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            FireSkillsData data = (FireSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            FireSkillsData data = new FireSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(),  new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
            
            return data;
        }
    }
    

	@EventHandler
	public void FIreskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("FIreSkills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getFIremagedata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> FlowingLava = getFIremagedata().FlowingLava;
					HashMap<UUID, Integer> Ring = getFIremagedata().Ring;
					HashMap<UUID, Integer> Fireball = getFIremagedata().Fireball;
					HashMap<UUID, Integer> Spread = getFIremagedata().Spread;
					HashMap<UUID, Integer> AliveFlame = getFIremagedata().AliveFlame;
					HashMap<UUID, Integer> Breath = getFIremagedata().Breath;
					HashMap<UUID, Integer> HotBody = getFIremagedata().HotBody;
					HashMap<UUID, Integer> SkillPoints = getFIremagedata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					FlowingLava.put(p.getUniqueId(), 0);
					Ring.put(p.getUniqueId(), 0);
					Fireball.put(p.getUniqueId(), 0);
					Spread.put(p.getUniqueId(), 0);
					Breath.put(p.getUniqueId(), 0);
					AliveFlame.put(p.getUniqueId(), 0);
					HotBody.put(p.getUniqueId(), 0);
					new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");

					FireSkillsGui fsg = new FireSkillsGui();
					fsg.FIreSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> FlowingLava = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Ring = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Fireball = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Spread = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Breath = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> AliveFlame = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> HotBody = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				FlowingLava.put(p.getUniqueId(), 0);
				Ring.put(p.getUniqueId(), 0);
				Fireball.put(p.getUniqueId(), 0);
				Spread.put(p.getUniqueId(), 0);
				Breath.put(p.getUniqueId(), 0);
				AliveFlame.put(p.getUniqueId(), 0);
				HotBody.put(p.getUniqueId(), 0);
				new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");

				FireSkillsGui fsg = new FireSkillsGui();
				fsg.FIreSkillsinv(p);
			}
		}
	}

	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> FlowingLava = getFIremagedata().FlowingLava;
		HashMap<UUID, Integer> Ring = getFIremagedata().Ring;
		HashMap<UUID, Integer> Fireball = getFIremagedata().Fireball;
		HashMap<UUID, Integer> Spread = getFIremagedata().Spread;
		HashMap<UUID, Integer> AliveFlame = getFIremagedata().AliveFlame;
		HashMap<UUID, Integer> Breath = getFIremagedata().Breath;
		HashMap<UUID, Integer> HotBody = getFIremagedata().HotBody;
		HashMap<UUID, Integer> SkillPoints = getFIremagedata().SkillPoints;
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		
		new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");

	}

	@EventHandler
	public void FIreskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();
        String path = new File("").getAbsolutePath();

		HashMap<UUID, Integer> FlowingLava = getFIremagedata().FlowingLava;
		HashMap<UUID, Integer> Ring = getFIremagedata().Ring;
		HashMap<UUID, Integer> Fireball = getFIremagedata().Fireball;
		HashMap<UUID, Integer> Spread = getFIremagedata().Spread;
		HashMap<UUID, Integer> AliveFlame = getFIremagedata().AliveFlame;
		HashMap<UUID, Integer> Breath = getFIremagedata().Breath;
		HashMap<UUID, Integer> HotBody = getFIremagedata().HotBody;
		HashMap<UUID, Integer> SkillPoints = getFIremagedata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
		        
			}
			else
			{	

				FlowingLava.remove(p.getUniqueId());
				FlowingLava.put(p.getUniqueId(), 0);
				Ring.remove(p.getUniqueId());
				Ring.put(p.getUniqueId(), 0);
				Fireball.remove(p.getUniqueId());
				Fireball.put(p.getUniqueId(), 0);
				Spread.remove(p.getUniqueId());
				Spread.put(p.getUniqueId(), 0);
				HotBody.remove(p.getUniqueId());
				HotBody.put(p.getUniqueId(), 0);
				Breath.remove(p.getUniqueId());
				Breath.put(p.getUniqueId(), 0);
				AliveFlame.remove(p.getUniqueId());
				AliveFlame.put(p.getUniqueId(), 0);
				SkillPoints.remove(p.getUniqueId());
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void FIreskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("FIreSkills"))
		{
			e.setCancelled(true);
	        String path = new File("").getAbsolutePath();
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				FireSkillsGui fsg = new FireSkillsGui();
				
				HashMap<UUID, Integer> FlowingLava = getFIremagedata().FlowingLava;
				HashMap<UUID, Integer> Ring = getFIremagedata().Ring;
				HashMap<UUID, Integer> Fireball = getFIremagedata().Fireball;
				HashMap<UUID, Integer> Spread = getFIremagedata().Spread;
				HashMap<UUID, Integer> AliveFlame = getFIremagedata().AliveFlame;
				HashMap<UUID, Integer> Breath = getFIremagedata().Breath;
				HashMap<UUID, Integer> HotBody = getFIremagedata().HotBody;
				HashMap<UUID, Integer> SkillPoints = getFIremagedata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Eruption":
					case "분화구":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && FlowingLava.get(p.getUniqueId())<50){
								FlowingLava.put(p.getUniqueId(), FlowingLava.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(FlowingLava.get(p.getUniqueId()) >= 1){
								FlowingLava.put(p.getUniqueId(), FlowingLava.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(FlowingLava.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(FlowingLava.get(p.getUniqueId())<50){
										final int a = 50 - FlowingLava.get(p.getUniqueId());
										FlowingLava.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									FlowingLava.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+FlowingLava.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(FlowingLava.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+FlowingLava.get(p.getUniqueId()));
								FlowingLava.put(p.getUniqueId(), 0);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						break;		}
						
					case "Ring":
					case "불의고리":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Ring.get(p.getUniqueId())<50){
								Ring.put(p.getUniqueId(), Ring.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Ring.get(p.getUniqueId()) >= 1){
								Ring.put(p.getUniqueId(), Ring.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Ring.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Ring.get(p.getUniqueId())<50){
										final int a = 50 - Ring.get(p.getUniqueId());
										Ring.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Ring.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Ring.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Ring.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Ring.get(p.getUniqueId()));
								Ring.put(p.getUniqueId(), 0);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						break;		}		
					case "Fireball":
					case "화염구":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Fireball.get(p.getUniqueId())<50){
								Fireball.put(p.getUniqueId(), Fireball.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Fireball.get(p.getUniqueId()) >= 1){
								Fireball.put(p.getUniqueId(), Fireball.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Fireball.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Fireball.get(p.getUniqueId())<50){
										final int a = 50 - Fireball.get(p.getUniqueId());
										Fireball.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);						
									}
								}
								else{
									Fireball.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Fireball.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Fireball.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Fireball.get(p.getUniqueId()));
								Fireball.put(p.getUniqueId(), 0);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						break;		}
					case "Breath":
					case "화염의숨결":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Breath.get(p.getUniqueId())<50){
								Breath.put(p.getUniqueId(), Breath.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Breath.get(p.getUniqueId()) >= 1){
								Breath.put(p.getUniqueId(), Breath.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Breath.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Breath.get(p.getUniqueId())<50){
										final int a = 50 - Breath.get(p.getUniqueId());
										Breath.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									Breath.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Breath.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Breath.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Breath.get(p.getUniqueId()));
								Breath.put(p.getUniqueId(), 0);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						break;		}
					case "AliveFlame":
					case "살아있는불꽃":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && AliveFlame.get(p.getUniqueId())<50){
								AliveFlame.put(p.getUniqueId(), AliveFlame.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(AliveFlame.get(p.getUniqueId()) >= 1){
								AliveFlame.put(p.getUniqueId(), AliveFlame.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(AliveFlame.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(AliveFlame.get(p.getUniqueId())<50){
										final int a = 50 - AliveFlame.get(p.getUniqueId());
										AliveFlame.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);							
									}
								}
								else{
									AliveFlame.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+AliveFlame.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(AliveFlame.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+AliveFlame.get(p.getUniqueId()));
								AliveFlame.put(p.getUniqueId(), 0);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						break;		}
					case "HotBody":
					case "열기":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								HotBody.put(p.getUniqueId(), HotBody.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(HotBody.get(p.getUniqueId()) >= 1){
								HotBody.put(p.getUniqueId(), HotBody.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								HotBody.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HotBody.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(HotBody.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+HotBody.get(p.getUniqueId()));
								HotBody.put(p.getUniqueId(), 0);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						break;		}			
					case "Spread":
					case "확산":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Spread.get(p.getUniqueId())<50){
								Spread.put(p.getUniqueId(), Spread.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Spread.get(p.getUniqueId()) >= 1){
								Spread.put(p.getUniqueId(), Spread.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Spread.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Spread.get(p.getUniqueId())<50){
										final int a = 50 - Spread.get(p.getUniqueId());
										Spread.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);					
									}
								}
								else{
									Spread.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Spread.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Spread.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Spread.get(p.getUniqueId()));
								Spread.put(p.getUniqueId(), 0);
								new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
						        fsg.FIreSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						FlowingLava.put(p.getUniqueId(), 0);
						Ring.put(p.getUniqueId(), 0);
						Fireball.put(p.getUniqueId(), 0);
						Spread.put(p.getUniqueId(), 0);
						HotBody.put(p.getUniqueId(), 0);
						Breath.put(p.getUniqueId(), 0);
						FlowingLava.put(p.getUniqueId(), 0);
						AliveFlame.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new FireSkillsData(FlowingLava, Ring, Fireball, Spread, Breath, AliveFlame, HotBody, SkillPoints).saveData(path +"/plugins/RPGskills/FIreSkillsData.data");
				        fsg.FIreSkillsinv(p);
				}break;	}
			
			}
			
		}
		
	}
    
    public static FireSkillsData getFIremagedata(){
        String path = new File("").getAbsolutePath();
        FireSkillsData data = new FireSkillsData(FireSkillsData.loadData(path +"/plugins/RPGskills/FIreSkillsData.data"));
		return data;
	}
}
