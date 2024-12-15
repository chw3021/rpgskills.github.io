package io.github.chw3021.obtains;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import com.google.common.collect.HashMultimap;

public class TrophyLoc implements Serializable, Listener{


	/**
	 * 
	 */
	private static transient final long serialVersionUID = 6155038841005174693L;
	
	public HashMultimap<UUID, Location> Locs = HashMultimap.create();

    // Can be used for saving
    public TrophyLoc(HashMultimap<UUID, Location> Locs) {
    	this.Locs = Locs;
    	}
    // Can be used for loading
    public TrophyLoc(TrophyLoc loadedData) {
    	this.Locs = loadedData.Locs;
    	}
 
	public TrophyLoc saveData(String filePath) {
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
    public static TrophyLoc loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            TrophyLoc data = (TrophyLoc) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException |NullPointerException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            TrophyLoc data = new TrophyLoc(HashMultimap.create()).saveData(path +"/plugins/RPGskills/TrophyLoc.data");
            e.printStackTrace();
            return data;
        }
    }
    
    public static TrophyLoc getLocsdata(){
        String path = new File("").getAbsolutePath();
        TrophyLoc data = new TrophyLoc(TrophyLoc.loadData(path +"/plugins/RPGskills/TrophyLoc.data"));
		return data;
	}

	public static void saver(Player p,Location l) {

		HashMultimap<UUID, Location> Locs = getLocsdata().Locs;
		Locs.put(p.getUniqueId(), l);
        String path = new File("").getAbsolutePath();
		new TrophyLoc(Locs).saveData(path +"/plugins/RPGskills/TrophyLoc.data");
	}
	
}
