package io.github.chw3021.items;


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
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import io.github.chw3021.classes.angler.AngSkillsData;
import io.github.chw3021.classes.archer.ArchSkillsData;
import io.github.chw3021.classes.berserker.BerSkillsData;
import io.github.chw3021.classes.boxer.BoxSkillsData;
import io.github.chw3021.classes.broiler.BroSkillsData;
import io.github.chw3021.classes.chemist.CheSkillsData;
import io.github.chw3021.classes.cook.CookSkillsData;
import io.github.chw3021.classes.engineer.EngSkillsData;
import io.github.chw3021.classes.firemage.FireSkillsData;
import io.github.chw3021.classes.forger.ForSkillsData;
import io.github.chw3021.classes.frostman.FrostSkillsData;
import io.github.chw3021.classes.hunter.HunSkillsData;
import io.github.chw3021.classes.illusionist.IllSkillsData;
import io.github.chw3021.classes.launcher.LaunSkillsData;
import io.github.chw3021.classes.medic.MedSkillsData;
import io.github.chw3021.classes.nobility.NobSkillsData;
import io.github.chw3021.classes.oceanknight.OceSkillsData;
import io.github.chw3021.classes.paladin.PalSkillsData;
import io.github.chw3021.classes.sniper.SnipSkillsData;
import io.github.chw3021.classes.swordman.SwordSkillsData;
import io.github.chw3021.classes.tamer.TamSkillsData;
import io.github.chw3021.classes.taoist.TaoSkillsData;
import io.github.chw3021.classes.witchdoctor.WdcSkillsData;
import io.github.chw3021.classes.witherist.WitSkillsData;
import io.github.chw3021.classes.wreltler.WreSkillsData;


public class ScrollPoint implements Serializable, Listener{
	
	/**
	 * 
	 */
	private transient static final long serialVersionUID = -3223627798968001478L;
	public final HashMap<UUID, Integer> scrollpoint;
	static public HashMap<UUID, Integer> sp = new HashMap<>();
    // Can be used for saving
	
	
    public ScrollPoint(HashMap<UUID, Integer> scroll) {
    	this.scrollpoint = scroll;
    	}
    // Can be used for loading
    public ScrollPoint(ScrollPoint loadedData) {
    	this.scrollpoint = loadedData.scrollpoint;
    	}
 
	public ScrollPoint saveData(String filePath) {
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
    public static ScrollPoint loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            ScrollPoint data = (ScrollPoint) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException|ExceptionInInitializerError  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            ScrollPoint data = new ScrollPoint(new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/ScrollPoint.data");
            
            e.printStackTrace();
            return data;
        }
    }

	@EventHandler	
	public void Using(PlayerInteractEvent ev) 
	{
		Player p = ev.getPlayer();
		Action ac = ev.getAction();
		
		
		if((ac == Action.RIGHT_CLICK_AIR || ac== Action.RIGHT_CLICK_BLOCK))
		{
			if(p.getInventory().getItemInMainHand().getType()==Material.MOJANG_BANNER_PATTERN)
			{
				final ItemStack is = p.getInventory().getItemInMainHand();
				if(is.hasItemMeta() && is.getItemMeta().hasLocalizedName() && is.getItemMeta().getLocalizedName().equals(ChatColor.GOLD +"Mysterious scroll")) {
					p.getInventory().getItemInMainHand().setAmount(0);

					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "두루마리를 사용했습니다"), ChatColor.BOLD +"스킬포인트가 1 상승했습니다", 5, 60, 5);
					}
					else {
		        		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "The Scroll Used"), ChatColor.BOLD +"Increase 1 Skill Point", 5, 60, 5);
					}
	        		p.playSound(p.getEyeLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.6f, 0);
	        		p.playSound(p.getEyeLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 0.3f, 0.3f);
	            	p.spawnParticle(Particle.FLAME, p.getEyeLocation(), 100,1,0.2,1,0.05);
	            	p.spawnParticle(Particle.SOUL, p.getEyeLocation(), 100,1,0.2,1,0.05);
	            	p.spawnParticle(Particle.SOUL_FIRE_FLAME, p.getEyeLocation(), 100,1,0.2,1,0.05);
	            	p.spawnParticle(Particle.COMPOSTER, p.getEyeLocation(), 100,1,0.2,1,0.05);
	        		
	        		
			        String path = new File("").getAbsolutePath();
					HashMap<UUID, Integer> scroll = getScrollPointdata();
					scroll.putIfAbsent(p.getUniqueId(),0);
					scroll.computeIfPresent(p.getUniqueId(), (k,v) -> v+1);
					sp =new ScrollPoint(scroll).saveData(path +"/plugins/RPGskills/ScrollPoint.data").scrollpoint;

		    		AngSkillsData.scroll(p);
		    		ArchSkillsData.scroll(p);
		    		BerSkillsData.scroll(p);
		    		BoxSkillsData.scroll(p);
		    		BroSkillsData.scroll(p);
		    		CheSkillsData.scroll(p);
		    		CookSkillsData.scroll(p);
		    		EngSkillsData.scroll(p);
		    		FireSkillsData.scroll(p);
		    		ForSkillsData.scroll(p);
		    		FrostSkillsData.scroll(p);
		    		HunSkillsData.scroll(p);
		    		IllSkillsData.scroll(p);
		    		LaunSkillsData.scroll(p);
		    		MedSkillsData.scroll(p);
		    		NobSkillsData.scroll(p);
		    		OceSkillsData.scroll(p);
		    		PalSkillsData.scroll(p);
		    		SnipSkillsData.scroll(p);
		    		SwordSkillsData.scroll(p);
		    		TamSkillsData.scroll(p);
		    		TaoSkillsData.scroll(p);
		    		WdcSkillsData.scroll(p);
		    		WitSkillsData.scroll(p);
		    		WreSkillsData.scroll(p);
				}
			}
		}
	}
	
	
	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
        String path = new File("").getAbsolutePath();
		ScrollPoint cdata = new ScrollPoint(ScrollPoint.loadData(path +"/plugins/RPGskills/ScrollPoint.data"));
		sp = cdata.scrollpoint;
	}
	
	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		Player p = ev.getPlayer();
        String path = new File("").getAbsolutePath();
		try 
		{
			if(!getScrollPointdata().containsKey(p.getUniqueId()))
			{
				HashMap<UUID, Integer> scroll = getScrollPointdata();
				scroll.put(p.getUniqueId(), 0);
	    		new ScrollPoint(scroll).saveData(path +"/plugins/RPGskills/ScrollPoint.data");
			}
		}
		catch(NullPointerException ne)
		{
			HashMap<UUID, Integer> scroll = new HashMap<UUID, Integer>();
			scroll.put(p.getUniqueId(), 0);
    		new ScrollPoint(scroll).saveData(path +"/plugins/RPGskills/ScrollPoint.data");
            
		}
		ScrollPoint cdata = new ScrollPoint(ScrollPoint.loadData(path +"/plugins/RPGskills/ScrollPoint.data"));
		sp = cdata.scrollpoint;
	}
	
	public HashMap<UUID, Integer> getScrollPointdata(){
        String path = new File("").getAbsolutePath();
        ScrollPoint data = new ScrollPoint(ScrollPoint.loadData(path +"/plugins/RPGskills/ScrollPoint.data"));
		return data.scrollpoint;
	}
}