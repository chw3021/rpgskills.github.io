package io.github.chw3021.monsters.raids;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import io.github.chw3021.party.Party;


public class RaidDifficulties implements Serializable,Listener{
	/**
	 * 
	 */

	private transient static final long serialVersionUID = -1062684579942518891L;
	public final Table<UUID, RaidCategory, Integer> dif;
	static Table<UUID, RaidCategory, Integer> redif;
 
    // Can be used for saving
	
    public RaidDifficulties(Table<UUID, RaidCategory, Integer> dif) {
    	this.dif = dif;
    	}
    // Can be used for loading
    public RaidDifficulties(RaidDifficulties loadedData) {
    	this.dif = loadedData.dif;
    	}

	public static void saver(Player p, RaidCategory rc, Integer f) {

	    String path = new File("").getAbsolutePath();
	    
		Table<UUID, RaidCategory, Integer> dif = new RaidDifficulties(loadData(path +"/plugins/RPGskills/RaidDifficulties.data")).dif;
		dif.put(p.getUniqueId(), rc, f);
		redif = dif;
		
        new RaidDifficulties(dif).saveData(path +"/plugins/RPGskills/RaidDifficulties.data");
	}
	

	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
	    String path = new File("").getAbsolutePath();
		Table<UUID, RaidCategory, Integer> dif = new RaidDifficulties(loadData(path +"/plugins/RPGskills/RaidDifficulties.data")).dif;
		redif = dif;
	}
	
	
	public static Integer getMaxDifficulty(Player p, RaidCategory rc) {


		
		if(Party.hasParty(p)) {
		    Integer rd = Integer.MAX_VALUE;
			for(UUID m : Party.getMembers(Party.getParty(p)).toList()){
				if(redif.contains(m, rc)) {
					if(redif.get(m, rc) <= rd) {
						rd = redif.get(m, rc);
					}
				}
				else {
					if(0 <= rd) {
						rd = 0;
					}
				}
			}
			return rd;
		}
		else {
			if(redif.contains(p.getUniqueId(), rc)) {
				return redif.get(p.getUniqueId(), rc);
			}
			else {
				return 0;
			}
		}
	}
 
	public RaidDifficulties saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return this;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return this;
        }
    }
    public static RaidDifficulties loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            RaidDifficulties data = (RaidDifficulties) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException|ExceptionInInitializerError  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            RaidDifficulties data = new RaidDifficulties(HashBasedTable.create()).saveData(path +"/plugins/RPGskills/RaidDifficulties.data");
            
            //e.printStackTrace();
            return data;
        }
    }
}
