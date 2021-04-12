package io.github.chw3021.party;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class PartyData implements Serializable, Listener{

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 3384762156083972776L;
	public final HashMap<UUID, String> Owner;
	public final HashMap<UUID, String> Party;


    // Can be used for saving
    public PartyData(HashMap<UUID, String> Party, HashMap<UUID, String> Owner) {
    	this.Party = Party;
    	this.Owner = Owner;
    	}
    // Can be used for loading
    public PartyData(PartyData loadedData) {
    	this.Party = loadedData.Party;
    	this.Owner = loadedData.Owner;
    	}
 
	public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public static PartyData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            PartyData data = (PartyData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
    		new PartyData(new HashMap<UUID, String>(), new HashMap<UUID, String>()).saveData(path +"/plugins/RPGskills/PartyData.data");
            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
            e.printStackTrace();
            return null;
        }
    }
    
    public static PartyData getPartydata(){
        String path = new File("").getAbsolutePath();
        PartyData data = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
		return data;
	}
    
    @EventHandler
 	public static void partyjoin(PlayerJoinEvent ev)
 	{
 		Player p = (Player) ev.getPlayer();
        String path = new File("").getAbsolutePath();
		try 
		{
			if(getPartydata().Party.containsKey(p.getUniqueId()))
			{
				HashMap<UUID, String> Party = getPartydata().Party;
				HashMap<UUID, String> Owner = getPartydata().Owner;
	    		new PartyData(Party, Owner).saveData(path +"/plugins/RPGskills/PartyData.data");
	            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			}
		}
		catch(NullPointerException ne)
		{
			HashMap<UUID, String> Party = new HashMap<UUID, String>();
			HashMap<UUID, String> Owner = new HashMap<UUID, String>();
    		new PartyData(Party, Owner).saveData(path +"/plugins/RPGskills/PartyData.data");
            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
		}
 	}
     
    
	public static void addNewParty(Player p, String name)
	{
		HashMap<UUID, String> Party = getPartydata().Party;
		HashMap<UUID, String> Owner = getPartydata().Owner;
		Party.put(p.getUniqueId(), name);
		Owner.put(p.getUniqueId(), name);
        String path = new File("").getAbsolutePath();
		new PartyData(Party, Owner).saveData(path +"/plugins/RPGskills/PartyData.data");
        Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
        
	}
	
	public static void setNewOwner(Player p2, String name)
	{
		HashMap<UUID, String> Party = getPartydata().Party;
		HashMap<UUID, String> Owner = getPartydata().Owner;
		Owner.put(p2.getUniqueId(), name);
        String path = new File("").getAbsolutePath();
		new PartyData(Party, Owner).saveData(path +"/plugins/RPGskills/PartyData.data");
	}
	
	public static void addNewPartyMember(Player p, String name)
	{
		HashMap<UUID, String> Party = getPartydata().Party;
		HashMap<UUID, String> Owner = getPartydata().Owner;
		Party.put(p.getUniqueId(), name);
        String path = new File("").getAbsolutePath();
		new PartyData(Party, Owner).saveData(path +"/plugins/RPGskills/PartyData.data");
        Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
	}
	
	public static void removePartyMember(Player p, String name)
	{
		HashMap<UUID, String> Party = getPartydata().Party;
		HashMap<UUID, String> Owner = getPartydata().Owner;
		Party.remove(p.getUniqueId(), name);
		if(Owner.containsKey(p.getUniqueId())) {
			Owner.remove(p.getUniqueId());}
        String path = new File("").getAbsolutePath();
		new PartyData(Party, Owner).saveData(path +"/plugins/RPGskills/PartyData.data");
        Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
	}
		
	public static final String getParty(Player p)
	{
		HashMap<UUID, String> Party = getPartydata().Party;
		return Party.get(p.getUniqueId()).trim();
	}
	
	public static Stream<UUID> getMembers(String name)
	{
		HashMap<UUID, String> Party = getPartydata().Party;
		HashMap<UUID, String> Owner = getPartydata().Owner;
		if(Party.containsValue(name)){
			Stream<UUID> keys = Party.entrySet().stream()
			    .filter(x -> name.equals(x.getValue()))
			    .map(Map.Entry::getKey);
			return keys;}
		else
			return null;
	}
	
	public static Set<UUID> getOwners()
	{
		HashMap<UUID, String> Party = getPartydata().Party;
		HashMap<UUID, String> Owner = getPartydata().Owner;
		return Owner.keySet();
	}

	public static Player getOwner(String name)
	{
		HashMap<UUID, String> Owner = getPartydata().Owner;
		return Bukkit.getPlayer(Owner.entrySet().stream().filter(entry -> name.equals(entry.getValue())).findAny().get().getKey());
	}
	
	public static void removeParty(String name)
	{
		HashMap<UUID, String> Party = getPartydata().Party;
		HashMap<UUID, String> Owner = getPartydata().Owner;
        Party.entrySet() 
            .removeIf( 
                entry -> (name.equals(entry.getValue()))); 
        String path = new File("").getAbsolutePath();
		new PartyData(Party, Owner).saveData(path +"/plugins/RPGskills/PartyData.data");
        Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
	}
	
	public static boolean hasParty(Player p)
	{
		HashMap<UUID, String> Party = getPartydata().Party;
		HashMap<UUID, String> Owner = getPartydata().Owner;
		return Party.containsKey(p.getUniqueId());
	}
	
	public static boolean isPartyexist(String name)
	{
		HashMap<UUID, String> Party = getPartydata().Party;
		HashMap<UUID, String> Owner = getPartydata().Owner;
		return Party.containsValue(name);
	}
	
	public static boolean isOwner(Player p)
	{
		HashMap<UUID, String> Party = getPartydata().Party;
		HashMap<UUID, String> Owner = getPartydata().Owner;
		return Owner.containsKey(p.getUniqueId());
	}

	
}
