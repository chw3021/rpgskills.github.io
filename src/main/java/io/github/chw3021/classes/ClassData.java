package io.github.chw3021.classes;


import io.github.chw3021.archer.ArchSkillsGui;
import io.github.chw3021.berserker.BerSkillsGui;
import io.github.chw3021.boxer.BoxSkillsGui;
import io.github.chw3021.chemist.CheSkillsGui;
import io.github.chw3021.cook.CookSkillsGui;
import io.github.chw3021.engineer.EngSkillsGui;
import io.github.chw3021.firemage.FireSkillsGui;
import io.github.chw3021.forger.ForSkillsGui;
import io.github.chw3021.frostman.FrostSkillsGui;
import io.github.chw3021.hunter.HunSkillsGui;
import io.github.chw3021.illusionist.IllSkillsGui;
import io.github.chw3021.launcher.LaunSkillsGui;
import io.github.chw3021.medic.MedSkillsGui;
import io.github.chw3021.nobility.NobSkillsGui;
import io.github.chw3021.oceanknight.OceSkillsGui;
import io.github.chw3021.paladin.PalSkillsGui;
import io.github.chw3021.sniper.SnipSkillsGui;
import io.github.chw3021.swordman.SwordSkillsGui;
import io.github.chw3021.tamer.TamSkillsGui;
import io.github.chw3021.taoist.TaoSkillsGui;
import io.github.chw3021.witchdoctor.WdcSkillsGui;
import io.github.chw3021.witherist.WitSkillsGui;
import io.github.chw3021.wreltler.WreSkillsGui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;


public class ClassData implements Serializable, Listener{
	private static transient final long serialVersionUID = -3472632196999457137L;
	public final HashMap<UUID, Integer> playerclass;
 
    // Can be used for saving
	
    public ClassData(HashMap<UUID, Integer> playerclass) {
    	this.playerclass = playerclass;
    	}
    // Can be used for loading
    public ClassData(ClassData loadedData) {
    	this.playerclass = loadedData.playerclass;
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
    public static ClassData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            ClassData data = (ClassData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
    		new ClassData(new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/ClassData.data");
            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
            e.printStackTrace();
            return null;
        }
    }

	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		Player p = ev.getPlayer();
        String path = new File("").getAbsolutePath();
		try 
		{
			if(!getPlayerclassdata().containsKey(p.getUniqueId()))
			{
				HashMap<UUID, Integer> playerclass = getPlayerclassdata();
				playerclass.put(p.getUniqueId(), -1);
	    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
	            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			}
		}
		catch(NullPointerException ne)
		{
			HashMap<UUID, Integer> playerclass = new HashMap<UUID, Integer>();
			playerclass.put(p.getUniqueId(), -1);
    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
		}
	}
    @EventHandler
	public void classopen(InventoryOpenEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Classes"))
		{
            String path = new File("").getAbsolutePath();
			try 
			{
				if(!getPlayerclassdata().containsKey(p.getUniqueId()))
				{
					HashMap<UUID, Integer> playerclass = getPlayerclassdata();
					playerclass.put(p.getUniqueId(), -1);
		    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
		            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> playerclass = new HashMap<UUID, Integer>();
				playerclass.put(p.getUniqueId(), -1);
	    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
	            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			}
		}
	}
    
	@EventHandler
	public void classinv(InventoryClickEvent e)
	{
        Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Classes"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{				

	            String path = new File("").getAbsolutePath();
	            HashMap<UUID, Integer> playerclass = getPlayerclassdata();
	            p.setDisplayName(p.getName());
	            p.setPlayerListName(p.getName());
					switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
					{
					case"SwordMan":
						playerclass.put(p.getUniqueId(), 0);
						p.sendMessage("Now you're SwordMan");
						p.addScoreboardTag(ChatColor.AQUA + "SwordMan");
						p.setDisplayName(ChatColor.AQUA + "[SwordMan]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.AQUA + "[SwordMan]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(30);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
			            SwordSkillsGui ssg = new SwordSkillsGui();
						ssg.SwordSkillsinv(p);
						ssg.SwordSkillsinv(p);
						break;
					case"Berserker":
						playerclass.put(p.getUniqueId(), 1);
						p.sendMessage("Now you're Berserker");
						p.addScoreboardTag(ChatColor.DARK_RED + "Berserker");
						p.setDisplayName(ChatColor.DARK_RED + "[Berserker]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.DARK_RED + "[Berserker]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(50);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
			            BerSkillsGui bsg = new BerSkillsGui();
						bsg.Berskillsinv(p);
						bsg.Berskillsinv(p);
						break;
					case"Hunter":
						playerclass.put(p.getUniqueId(), 2);
						p.sendMessage("Now you're Hunter");
						p.addScoreboardTag(ChatColor.GREEN + "Hunter");
						p.setDisplayName(ChatColor.GREEN + "[Hunter]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.GREEN + "[Hunter]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(18);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
			            HunSkillsGui hsg = new HunSkillsGui();
						hsg.Hunskillsinv(p);
						hsg.Hunskillsinv(p);
						break;
					case"Paladin":
						playerclass.put(p.getUniqueId(), 3);
						p.sendMessage("Now you're Paladin");
						p.addScoreboardTag(ChatColor.WHITE + "Paladin");
						p.setDisplayName(ChatColor.WHITE + "[Paladin]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.WHITE + "[Paladin]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(50);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
					    p.setHealth(p.getMaxHealth());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
			            PalSkillsGui psg = new PalSkillsGui();
						psg.PalSkillsinv(p);
						psg.PalSkillsinv(p);
						break;
					case"Sniper":
						playerclass.put(p.getUniqueId(), 4);
						p.sendMessage("Now you're Sniper");
						p.addScoreboardTag(ChatColor.getByChar("336600") + "Sniper");
						p.setDisplayName(ChatColor.getByChar("336600") + "[Sniper]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.getByChar("336600") + "[Sniper]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(16);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						SnipSkillsGui l = new SnipSkillsGui();
						l.Snipskillsinv(p);
						l.Snipskillsinv(p);
						break;
					case"Launcher":
						playerclass.put(p.getUniqueId(), 5);
						p.sendMessage("Now you're Launcher");
						p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Launcher");
						p.setDisplayName(ChatColor.LIGHT_PURPLE + "[Launcher]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[Launcher]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(20);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
			            LaunSkillsGui lsg = new LaunSkillsGui();
						lsg.Launskillsinv(p);
						lsg.Launskillsinv(p);
						break;
					case"Archer":
						playerclass.put(p.getUniqueId(), 6);
						p.sendMessage("Now you're Archer");
						p.addScoreboardTag(ChatColor.BLUE + "Archer");
						p.setDisplayName(ChatColor.BLUE + "[Archer]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.BLUE + "[Archer]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(30);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
			            ArchSkillsGui asg = new ArchSkillsGui();
						asg.Archskillsinv(p);
						asg.Archskillsinv(p);
						break;
					case"Medic":
						playerclass.put(p.getUniqueId(), 61);
						p.sendMessage("Now you're Medic");
						p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Medic");
						p.setDisplayName(ChatColor.LIGHT_PURPLE + "[Medic]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[Medic]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(30);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						MedSkillsGui msg = new MedSkillsGui();
						msg.Medskillsinv(p);
						msg.Medskillsinv(p);
						break;
					case"Boxer":
						playerclass.put(p.getUniqueId(), 7);
						p.sendMessage("Now you're Boxer");
						p.addScoreboardTag(ChatColor.GRAY + "Boxer");
						p.setDisplayName(ChatColor.GRAY + "[Boxer]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.GRAY + "[Boxer]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(50);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						BoxSkillsGui bxg = new BoxSkillsGui();
						bxg.BoxSkillsinv(p);
						bxg.BoxSkillsinv(p);
						break;
					case"Wrestler":
						playerclass.put(p.getUniqueId(), 8);
						p.sendMessage("Now you're Wrestler");
						p.addScoreboardTag(ChatColor.GOLD + "Wrestler");
						p.setDisplayName(ChatColor.GOLD + "[Wrestler]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.GOLD + "[Wrestler]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(50);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						WreSkillsGui wrg = new WreSkillsGui();
						wrg.WreSkillsinv(p);
						wrg.WreSkillsinv(p);
						break;
					case"Tamer":
						playerclass.put(p.getUniqueId(), 9);
						p.sendMessage("Now you're Tamer");
						p.addScoreboardTag(ChatColor.DARK_GRAY + "Tamer");
						p.setDisplayName(ChatColor.DARK_GRAY + "[Tamer]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.DARK_GRAY + "[Tamer]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(30);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						TamSkillsGui tsg = new TamSkillsGui();
						tsg.TamSkillsinv(p);
						tsg.TamSkillsinv(p);
						break;
					case"Taoist":
						playerclass.put(p.getUniqueId(), 10);
						p.sendMessage("Now you're Taoist");
						p.addScoreboardTag(ChatColor.YELLOW + "Taoist");
						p.setDisplayName(ChatColor.YELLOW + "[Taoist]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.YELLOW + "[Taoist]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(24);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						TaoSkillsGui tag = new TaoSkillsGui();
						tag.TaoSkillsinv(p);
						tag.TaoSkillsinv(p);
						break;
					case"Illusionist":
						playerclass.put(p.getUniqueId(), 11);
						p.sendMessage("Now you're Illusionist");
						p.addScoreboardTag(ChatColor.DARK_PURPLE + "Illusionist");
						p.setDisplayName(ChatColor.DARK_PURPLE + "[Illusionist]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.DARK_PURPLE + "[Illusionist]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(18);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						IllSkillsGui ilg = new IllSkillsGui();
						ilg.IllSkillsinv(p);
						ilg.IllSkillsinv(p);
						break;
					case"FireMage":
						playerclass.put(p.getUniqueId(), 12);
						p.sendMessage("Now you're FireMage");
						p.addScoreboardTag(ChatColor.RED + "FireMage");
						p.setDisplayName(ChatColor.RED + "[FireMage]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.RED + "[FireMage]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(32);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						FireSkillsGui fsg = new FireSkillsGui();
						fsg.FIreSkillsinv(p);
						fsg.FIreSkillsinv(p);
						break;
					case"Witherist":
						playerclass.put(p.getUniqueId(), 13);
						p.sendMessage("Now you're Witherist");
						p.addScoreboardTag(ChatColor.BLACK + "Witherist");
						p.setDisplayName(ChatColor.BLACK + "[Witherist]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.BLACK+ "[Witherist]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(28);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						WitSkillsGui wsg = new WitSkillsGui();
						wsg.WitSkillsinv(p);
						wsg.WitSkillsinv(p);
						break;
					case"WitchDoctor":
						playerclass.put(p.getUniqueId(), 14);
						p.sendMessage("Now you're WitchDoctor");
						p.addScoreboardTag(ChatColor.DARK_GREEN + "WitchDoctor");
						p.setDisplayName(ChatColor.DARK_GREEN + "[WitchDorcor]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.DARK_GREEN+ "[WitchDoctor]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(34);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						WdcSkillsGui wdg = new WdcSkillsGui();
						wdg.WdcSkillsinv(p);
						wdg.WdcSkillsinv(p);
						break;
					case"Chemist":
						playerclass.put(p.getUniqueId(), 15);
						p.sendMessage("Now you're Chemist");
						p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Chemist");
						p.setDisplayName(ChatColor.LIGHT_PURPLE + "[Chemist]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.LIGHT_PURPLE+ "[Chemist]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(46);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						CheSkillsGui chg = new CheSkillsGui();
						chg.CheSkillsinv(p);
						chg.CheSkillsinv(p);
						break;
					case"Forger":
						playerclass.put(p.getUniqueId(), 16);
						p.sendMessage("Now you're Forger");
						p.addScoreboardTag(ChatColor.BLUE + "Forger");
						p.setDisplayName(ChatColor.BLUE+ "[Forger]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.BLUE+ "[Forger]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(20);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						ForSkillsGui fgg = new ForSkillsGui();
						fgg.ForSkillsinv(p);
						fgg.ForSkillsinv(p);
						break;
					case"Engineer":
						playerclass.put(p.getUniqueId(), 17);
						p.sendMessage("Now you're Engineer");
						p.addScoreboardTag(ChatColor.AQUA + "Engineer");
						p.setDisplayName(ChatColor.AQUA  + "[Engineer]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.AQUA + "[Engineer]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(28);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						EngSkillsGui egg = new EngSkillsGui();
						egg.EngSkillsinv(p);
						egg.EngSkillsinv(p);
						break;
					case"Cook":
						playerclass.put(p.getUniqueId(), 18);
						p.sendMessage("Now you're Cook");
						p.addScoreboardTag(ChatColor.GOLD + "Cook");
						p.setDisplayName(ChatColor.GOLD + "[Cook]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.GOLD+ "[Cook]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(22);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						CookSkillsGui csg = new CookSkillsGui();
						csg.CookSkillsinv(p);
						csg.CookSkillsinv(p);
						break;
					case"Nobility":
						playerclass.put(p.getUniqueId(), 19);
						p.sendMessage("Now you're Nobility");
						p.addScoreboardTag(ChatColor.BLUE+ "Nobility");
						p.setDisplayName(ChatColor.BLUE + "[Nobility]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.BLUE+ "[Nobility]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(22);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						NobSkillsGui nsg = new NobSkillsGui();
						nsg.NobSkillsinv(p);
						nsg.NobSkillsinv(p);
						break;
					case"OceanKnight":
						playerclass.put(p.getUniqueId(), 20);
						p.sendMessage("Now you're OceanKnight");
						p.addScoreboardTag(ChatColor.DARK_AQUA + "OceanKnight");
						p.setDisplayName(ChatColor.DARK_AQUA + "[OceanKnight]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.DARK_AQUA + "[OceanKnight]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(46);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						OceSkillsGui osg = new OceSkillsGui();
						osg.OceSkillsinv(p);
						osg.OceSkillsinv(p);
						break;
					case"Frostman":
						playerclass.put(p.getUniqueId(), 21);
						p.sendMessage("Now you're Frostman");
						p.addScoreboardTag(ChatColor.AQUA + "Frostman");
						p.setDisplayName(ChatColor.AQUA + "[Frostman]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.AQUA + "[Frostman]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(26);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						FrostSkillsGui frg = new FrostSkillsGui();
						frg.FrostSkillsinv(p);
						frg.FrostSkillsinv(p);
						break;
					case"Angler":
						playerclass.put(p.getUniqueId(), 22);
						p.sendMessage("Now you're Angler");
						p.addScoreboardTag(ChatColor.AQUA + "Angler");
						p.setDisplayName(ChatColor.AQUA + "[Angler]" + p.getDisplayName());
					    p.setPlayerListName(ChatColor.AQUA + "[Angler]" + p.getPlayerListName());
					    p.resetMaxHealth();
					    p.setMaxHealth(30);
						p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
			    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
			            p.closeInventory();
						break;
					}
				}
			
			
		}
		
	}
    
    public HashMap<UUID, Integer> getPlayerclassdata(){
        String path = new File("").getAbsolutePath();
        ClassData data = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		return data.playerclass;
	}
}
