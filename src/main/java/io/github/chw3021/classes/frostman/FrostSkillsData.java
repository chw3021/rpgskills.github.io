package io.github.chw3021.classes.frostman;


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



public class FrostSkillsData implements Serializable, Listener{
	


	/**
	 * 
	 */
	private static transient final long serialVersionUID = -7268184397235005084L;
	public final HashMap<UUID, Integer> Crack;
	public final HashMap<UUID, Integer> Frostbite;
	public final HashMap<UUID, Integer> IcicleShot;
	public final HashMap<UUID, Integer> FrozenCrystal;
	public final HashMap<UUID, Integer> IceSpikes;
	public final HashMap<UUID, Integer> SnowBreeze;
	public final HashMap<UUID, Integer> Hailstones;
	public final HashMap<UUID, Integer> SkillPoints;
    // Can be used for saving
	
    public FrostSkillsData(HashMap<UUID, Integer> Crack, HashMap<UUID, Integer> Frostbite, HashMap<UUID, Integer> IcicleShot, HashMap<UUID, Integer> FrozenCrystal, HashMap<UUID, Integer> IceSpikes, HashMap<UUID, Integer> SnowBreeze, HashMap<UUID, Integer> Hailstones, HashMap<UUID, Integer> SkillPoints) {
    	this.Crack = Crack;
    	this.Frostbite = Frostbite;
    	this.IcicleShot = IcicleShot;
    	this.FrozenCrystal = FrozenCrystal;
    	this.IceSpikes = IceSpikes;
    	this.SnowBreeze = SnowBreeze;
    	this.Hailstones = Hailstones;
    	this.SkillPoints = SkillPoints;
    	}
    // Can be used for loading
    public FrostSkillsData(FrostSkillsData loadedData) {
    	this.Crack = loadedData.Crack;
    	this.Frostbite = loadedData.Frostbite;
    	this.IcicleShot = loadedData.IcicleShot;
    	this.FrozenCrystal = loadedData.FrozenCrystal;
    	this.IceSpikes = loadedData.IceSpikes;
    	this.SnowBreeze = loadedData.SnowBreeze;
    	this.Hailstones = loadedData.Hailstones;
    	this.SkillPoints = loadedData.SkillPoints;
    	}
    
 
	public FrostSkillsData saveData(String filePath) {
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
    public static FrostSkillsData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            FrostSkillsData data = (FrostSkillsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            FrostSkillsData data = new FrostSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
            
            return data;
        }
    }
    

	@EventHandler
	public void Frostskillopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("FrostSkills"))
		{
	        String path = new File("").getAbsolutePath();
			try 
			{
				if(!getFrostmandata().SkillPoints.containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> Crack = getFrostmandata().Crack;
					HashMap<UUID, Integer> Frostbite = getFrostmandata().Frostbite;
					HashMap<UUID, Integer> IcicleShot = getFrostmandata().IcicleShot;
					HashMap<UUID, Integer> FrozenCrystal = getFrostmandata().FrozenCrystal;
					HashMap<UUID, Integer> SnowBreeze = getFrostmandata().SnowBreeze;
					HashMap<UUID, Integer> IceSpikes = getFrostmandata().IceSpikes;
					HashMap<UUID, Integer> Hailstones = getFrostmandata().Hailstones;
					HashMap<UUID, Integer> SkillPoints = getFrostmandata().SkillPoints;
					SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
					Crack.put(p.getUniqueId(), 0);
					Frostbite.put(p.getUniqueId(), 0);
					IcicleShot.put(p.getUniqueId(), 0);
					FrozenCrystal.put(p.getUniqueId(), 0);
					IceSpikes.put(p.getUniqueId(), 0);
					SnowBreeze.put(p.getUniqueId(), 0);
					Hailstones.put(p.getUniqueId(), 0);
					new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");

					FrostSkillsGui bxg = new FrostSkillsGui();
					bxg.FrostSkillsinv(p);
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> Crack = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Frostbite = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> IcicleShot = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> FrozenCrystal = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> IceSpikes = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SnowBreeze = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> Hailstones = new HashMap<UUID, Integer>();
				HashMap<UUID, Integer> SkillPoints = new HashMap<UUID, Integer>();
				SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				Crack.put(p.getUniqueId(), 0);
				Frostbite.put(p.getUniqueId(), 0);
				IcicleShot.put(p.getUniqueId(), 0);
				FrozenCrystal.put(p.getUniqueId(), 0);
				IceSpikes.put(p.getUniqueId(), 0);
				SnowBreeze.put(p.getUniqueId(), 0);
				Hailstones.put(p.getUniqueId(), 0);
				new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");

				FrostSkillsGui bxg = new FrostSkillsGui();
				bxg.FrostSkillsinv(p);
			}
		}
	}
	public static void scroll(Player p) {
        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Crack = getFrostmandata().Crack;
		HashMap<UUID, Integer> Frostbite = getFrostmandata().Frostbite;
		HashMap<UUID, Integer> IcicleShot = getFrostmandata().IcicleShot;
		HashMap<UUID, Integer> FrozenCrystal = getFrostmandata().FrozenCrystal;
		HashMap<UUID, Integer> SnowBreeze = getFrostmandata().SnowBreeze;
		HashMap<UUID, Integer> IceSpikes = getFrostmandata().IceSpikes;
		HashMap<UUID, Integer> Hailstones = getFrostmandata().Hailstones;
		HashMap<UUID, Integer> SkillPoints = getFrostmandata().SkillPoints;
		
		SkillPoints.put(p.getUniqueId(), SkillPoints.getOrDefault(p.getUniqueId(),1) + 1);
		new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");


	}

    
	@EventHandler
	public void Frostskilllev(PlayerLevelChangeEvent e)
	{

		Player p = (Player) e.getPlayer();

        String path = new File("").getAbsolutePath();
		HashMap<UUID, Integer> Crack = getFrostmandata().Crack;
		HashMap<UUID, Integer> Frostbite = getFrostmandata().Frostbite;
		HashMap<UUID, Integer> IcicleShot = getFrostmandata().IcicleShot;
		HashMap<UUID, Integer> FrozenCrystal = getFrostmandata().FrozenCrystal;
		HashMap<UUID, Integer> SnowBreeze = getFrostmandata().SnowBreeze;
		HashMap<UUID, Integer> IceSpikes = getFrostmandata().IceSpikes;
		HashMap<UUID, Integer> Hailstones = getFrostmandata().Hailstones;
		HashMap<UUID, Integer> SkillPoints = getFrostmandata().SkillPoints;
		if(SkillPoints.containsKey(p.getUniqueId())) {
			if(e.getNewLevel() > e.getOldLevel())
			{
				SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) + e.getNewLevel() - e.getOldLevel());
				new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
		        
			}
			else
			{	

				Crack.put(p.getUniqueId(), 0);
				Frostbite.put(p.getUniqueId(), 0);
				IcicleShot.put(p.getUniqueId(), 0);
				FrozenCrystal.put(p.getUniqueId(), 0);
				Hailstones.put(p.getUniqueId(), 0);
				IceSpikes.put(p.getUniqueId(), 0);
				SnowBreeze.put(p.getUniqueId(), 0);
				SkillPoints.put(p.getUniqueId(), e.getNewLevel() + ScrollPoint.sp.get(p.getUniqueId()));
				new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
		        
			}
		}
	}
	
	@EventHandler
	public void Frostskillinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("FrostSkills"))
		{
	        String path = new File("").getAbsolutePath();
			e.setCancelled(true);
			
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{

				FrostSkillsGui l = new FrostSkillsGui();
				HashMap<UUID, Integer> Crack = getFrostmandata().Crack;
				HashMap<UUID, Integer> Frostbite = getFrostmandata().Frostbite;
				HashMap<UUID, Integer> IcicleShot = getFrostmandata().IcicleShot;
				HashMap<UUID, Integer> FrozenCrystal = getFrostmandata().FrozenCrystal;
				HashMap<UUID, Integer> SnowBreeze = getFrostmandata().SnowBreeze;
				HashMap<UUID, Integer> IceSpikes = getFrostmandata().IceSpikes;
				HashMap<UUID, Integer> Hailstones = getFrostmandata().Hailstones;
				HashMap<UUID, Integer> SkillPoints = getFrostmandata().SkillPoints;
				switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					
					case "Crack":
					case "균열":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Crack.get(p.getUniqueId())<50){
								Crack.put(p.getUniqueId(), Crack.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Crack.get(p.getUniqueId()) >= 1){
								Crack.put(p.getUniqueId(), Crack.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Crack.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Crack.get(p.getUniqueId())<50){
										final int a = 50 - Crack.get(p.getUniqueId());
										Crack.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
																	
									}
								}
								else{
									Crack.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Crack.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Crack.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Crack.get(p.getUniqueId()));
								Crack.put(p.getUniqueId(), 0);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						break;		}
						
					case "Frostbite":
					case "동상":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1){
								Frostbite.put(p.getUniqueId(), Frostbite.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Frostbite.get(p.getUniqueId()) >= 1){
								Frostbite.put(p.getUniqueId(), Frostbite.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								Frostbite.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Frostbite.get(p.getUniqueId()));
								SkillPoints.put(p.getUniqueId(), 0);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Frostbite.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Frostbite.get(p.getUniqueId()));
								Frostbite.put(p.getUniqueId(), 0);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						break;		}		
					case "IcicleShot":
					case "고드름화살":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && IcicleShot.get(p.getUniqueId())<50){
								IcicleShot.put(p.getUniqueId(), IcicleShot.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(IcicleShot.get(p.getUniqueId()) >= 1){
								IcicleShot.put(p.getUniqueId(), IcicleShot.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(IcicleShot.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(IcicleShot.get(p.getUniqueId())<50){
										final int a = 50 - IcicleShot.get(p.getUniqueId());
										IcicleShot.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
																
									}
								}
								else{
									IcicleShot.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+IcicleShot.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(IcicleShot.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+IcicleShot.get(p.getUniqueId()));
								IcicleShot.put(p.getUniqueId(), 0);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						break;		}
					case "IceSpikes":
					case "거대고드름":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && IceSpikes.get(p.getUniqueId())<50){
								IceSpikes.put(p.getUniqueId(), IceSpikes.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(IceSpikes.get(p.getUniqueId()) >= 1){
								IceSpikes.put(p.getUniqueId(), IceSpikes.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(IceSpikes.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(IceSpikes.get(p.getUniqueId())<50){
										final int a = 50 - IceSpikes.get(p.getUniqueId());
										IceSpikes.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
																
									}
								}
								else{
									IceSpikes.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+IceSpikes.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(IceSpikes.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+IceSpikes.get(p.getUniqueId()));
								IceSpikes.put(p.getUniqueId(), 0);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						break;		}
					case "SnowBreeze":
					case "눈바람":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && SnowBreeze.get(p.getUniqueId())<1){
								SnowBreeze.put(p.getUniqueId(), SnowBreeze.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(SnowBreeze.get(p.getUniqueId()) >= 1){
								SnowBreeze.put(p.getUniqueId(), SnowBreeze.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(SnowBreeze.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>1) {
									if(SnowBreeze.get(p.getUniqueId())<1){
										SnowBreeze.put(p.getUniqueId(), 1);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) - SnowBreeze.get(p.getUniqueId()));								
									}
								}
								else{
									SnowBreeze.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SnowBreeze.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(SnowBreeze.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+SnowBreeze.get(p.getUniqueId()));
								SnowBreeze.put(p.getUniqueId(), 0);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						break;		}
					case "Hailstones":
					case "우박":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && Hailstones.get(p.getUniqueId())<50){
								Hailstones.put(p.getUniqueId(), Hailstones.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(Hailstones.get(p.getUniqueId()) >= 1){
								Hailstones.put(p.getUniqueId(), Hailstones.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(Hailstones.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(Hailstones.get(p.getUniqueId())<50){
										final int a = 50 - Hailstones.get(p.getUniqueId());
										Hailstones.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
															
									}
								}
								else{
									Hailstones.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Hailstones.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(Hailstones.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+Hailstones.get(p.getUniqueId()));
								Hailstones.put(p.getUniqueId(), 0);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						break;		}
					case "FrozenCrystal":
					case "얼음수정":{
						if(e.getClick().equals(ClickType.LEFT)) {
							if(SkillPoints.get(p.getUniqueId()) >= 1 && FrozenCrystal.get(p.getUniqueId())<50){
								FrozenCrystal.put(p.getUniqueId(), FrozenCrystal.get(p.getUniqueId()) +1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.RIGHT)) {
							if(FrozenCrystal.get(p.getUniqueId()) >= 1){
								FrozenCrystal.put(p.getUniqueId(), FrozenCrystal.get(p.getUniqueId()) -1);
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) +1);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}	
						}
						else if(e.getClick().equals(ClickType.SHIFT_LEFT)) {
							if(SkillPoints.get(p.getUniqueId())>0) {
								if(FrozenCrystal.get(p.getUniqueId())+SkillPoints.get(p.getUniqueId())>50) {
									if(FrozenCrystal.get(p.getUniqueId())<50){
										final int a = 50 - FrozenCrystal.get(p.getUniqueId());
										FrozenCrystal.put(p.getUniqueId(), 50);
										SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId()) -  a);
																	
									}
								}
								else{
									FrozenCrystal.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+FrozenCrystal.get(p.getUniqueId()));
									SkillPoints.put(p.getUniqueId(), 0);						
								}
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						else if(e.getClick().equals(ClickType.SHIFT_RIGHT)) {
							if(FrozenCrystal.get(p.getUniqueId())>0) {
								SkillPoints.put(p.getUniqueId(), SkillPoints.get(p.getUniqueId())+FrozenCrystal.get(p.getUniqueId()));
								FrozenCrystal.put(p.getUniqueId(), 0);
								new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
						        l.FrostSkillsinv(p);
							}
						}
						break;		}
					case "SkillPoints":
					case "스킬포인트":		{			
						Crack.put(p.getUniqueId(), 0);
						Frostbite.put(p.getUniqueId(), 0);
						IcicleShot.put(p.getUniqueId(), 0);
						FrozenCrystal.put(p.getUniqueId(), 0);
						Hailstones.put(p.getUniqueId(), 0);
						IceSpikes.put(p.getUniqueId(), 0);
						Crack.put(p.getUniqueId(), 0);
						SnowBreeze.put(p.getUniqueId(), 0);
						SkillPoints.put(p.getUniqueId(), p.getLevel() + ScrollPoint.sp.get(p.getUniqueId()));
						new FrostSkillsData(Crack, Frostbite, IcicleShot, FrozenCrystal, IceSpikes, SnowBreeze, Hailstones, SkillPoints).saveData(path +"/plugins/RPGskills/FrostSkillsData.data");
				        l.FrostSkillsinv(p);
				        break;	
				}}
			
			}
			
		}
		
	}
    
    public static FrostSkillsData getFrostmandata(){
        String path = new File("").getAbsolutePath();
        FrostSkillsData data = new FrostSkillsData(FrostSkillsData.loadData(path +"/plugins/RPGskills/FrostSkillsData.data"));
		return data;
	}
}