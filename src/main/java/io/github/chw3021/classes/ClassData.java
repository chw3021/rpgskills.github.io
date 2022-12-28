package io.github.chw3021.classes;


import io.github.chw3021.classes.angler.AngSkillsGui;
import io.github.chw3021.classes.archer.ArchSkillsGui;
import io.github.chw3021.classes.berserker.BerSkillsGui;
import io.github.chw3021.classes.boxer.BoxSkillsGui;
import io.github.chw3021.classes.broiler.BroSkillsGui;
import io.github.chw3021.classes.chemist.CheSkillsGui;
import io.github.chw3021.classes.cook.CookSkillsGui;
import io.github.chw3021.classes.engineer.EngSkillsGui;
import io.github.chw3021.classes.firemage.FireSkillsGui;
import io.github.chw3021.classes.forger.ForSkillsGui;
import io.github.chw3021.classes.frostman.FrostSkillsGui;
import io.github.chw3021.classes.hunter.HunSkillsGui;
import io.github.chw3021.classes.illusionist.IllSkillsGui;
import io.github.chw3021.classes.launcher.LaunSkillsGui;
import io.github.chw3021.classes.medic.MedSkillsGui;
import io.github.chw3021.classes.nobility.NobSkillsGui;
import io.github.chw3021.classes.oceanknight.OceSkillsGui;
import io.github.chw3021.classes.paladin.PalSkillsGui;
import io.github.chw3021.classes.sniper.SnipSkillsGui;
import io.github.chw3021.classes.swordman.SwordSkillsGui;
import io.github.chw3021.classes.tamer.TamSkillsGui;
import io.github.chw3021.classes.taoist.TaoSkillsGui;
import io.github.chw3021.classes.witchdoctor.WdcSkillsGui;
import io.github.chw3021.classes.witherist.WitSkillsGui;
import io.github.chw3021.classes.wreltler.WreSkillsGui;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
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
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;


public class ClassData implements Serializable, Listener{
	private static transient final long serialVersionUID = -3472632196999457137L;
	public final HashMap<UUID, Integer> playerclass;
	static public HashMap<UUID, Integer> pc;
	Classgui Classgui = new Classgui();
	Pak pak = new Pak();
 
    // Can be used for saving
	
    public ClassData(HashMap<UUID, Integer> playerclass) {
    	this.playerclass = playerclass;
    	}
    // Can be used for loading
    public ClassData(ClassData loadedData) {
    	this.playerclass = loadedData.playerclass;
    	}
 
	public ClassData saveData(String filePath) {
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
    public static ClassData loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            ClassData data = (ClassData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException|NullPointerException|ExceptionInInitializerError  e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            ClassData data = new ClassData(new HashMap<UUID, Integer>()).saveData(path +"/plugins/RPGskills/ClassData.data");
            
            e.printStackTrace();
            return data;
        }
    }

	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
        String path = new File("").getAbsolutePath();
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		pc = cdata.playerclass;
	}
	
	final private void classanounce(Player p) {

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        			p.sendMessage(ChatColor.GREEN +"/rpg c 또는 /rpg class 입력시 직업선택이 가능합니다");
        			p.sendMessage(ChatColor.GREEN +"/rpg ? 또는 /rpg help 입력시 명령어들이 나옵니다");
        		}
        		else {
        			p.sendMessage(ChatColor.GREEN +"You Can select your class by typing /rpg c Or /rpg class ");
        			p.sendMessage(ChatColor.GREEN +"If you type /rpg ? or /rpg help, the instructions will come out.");
        		}
            }
        }, 3); 
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
	    		classanounce(p);
			}
		}
		catch(NullPointerException ne)
		{
			HashMap<UUID, Integer> playerclass = new HashMap<UUID, Integer>();
			playerclass.put(p.getUniqueId(), -1);
    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
    		classanounce(p);
            
		}
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		pc = cdata.playerclass;
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
		            
				}
			}
			catch(NullPointerException ne)
			{
				HashMap<UUID, Integer> playerclass = new HashMap<UUID, Integer>();
				playerclass.put(p.getUniqueId(), -1);
	    		new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
	            
			}
		}
	}
    
	@SuppressWarnings("deprecation")
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
				if(p.getWorld().getName().contains("Raid")) {
		            p.closeInventory();
		    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage(ChatColor.BOLD+"작전중에는 전직변경이 불가능합니다");
		    		}
		    		else {
						p.sendMessage(ChatColor.BOLD+"You Can't Change Class While Raiding");
		    		}
					return;
				}	
				if(Party.hasParty(p)) {
		            p.closeInventory();
		    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage(ChatColor.BOLD+"파티중에는 전직변경이 불가능합니다");
		    		}
		    		else {
						p.sendMessage(ChatColor.BOLD+"You Can't Change Class While you're in party");
		    		}
					return;
				}
				else if(Summoned.combo.containsRow(p.getName())) {
		            p.closeInventory();
		    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage(ChatColor.BOLD+"전투중에는 전직변경이 불가능합니다");
						p.sendMessage("/rpg escape 또는 /rpg es 명령어로 전투를 종료할수 있습니다.");
		    		}
		    		else {
						p.sendMessage(ChatColor.BOLD+"You Can't Change Class While Fighting");
						p.sendMessage("You Can escape from fighting by" +ChatColor.BLUE+ "/rpg escape(es) "  +ChatColor.RESET+  "command");
		    		}
					return;
				}
	            String path = new File("").getAbsolutePath();
	            HashMap<UUID, Integer> playerclass = getPlayerclassdata();
				Bukkit.getWorlds().forEach(w -> w.getEntities().stream().filter(en -> en.hasMetadata("rob"+p.getName())).forEach(en-> en.remove()));
				Bukkit.getWorlds().forEach(w -> {
					w.getEntities().forEach(en -> {
						if(en.hasMetadata("din of "+p.getName())) {
							en.remove();
						}
					});
				});
	            p.setDisplayName(p.getName());
	            p.setPlayerListName(p.getName());
				pak.remelmr(p);
				p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
				p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getDefaultValue());
				p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());
					switch (ChatColor.stripColor((e.getCurrentItem().getItemMeta().getDisplayName())))
					{
					case"SwordMan":
					case"검사":
						playerclass.put(p.getUniqueId(), 0);
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(36);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            SwordSkillsGui ssg = new SwordSkillsGui();
						ssg.SwordSkillsinv(p);
						ssg.SwordSkillsinv(p);
						break;
					case"Berserker":
					case"광전사":
						playerclass.put(p.getUniqueId(), 1);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);

						p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);
						p.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            BerSkillsGui bsg = new BerSkillsGui();
						bsg.Berskillsinv(p);
						bsg.Berskillsinv(p);
						break;
					case"Hunter":
					case"사냥꾼":
						playerclass.put(p.getUniqueId(), 2);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(18);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            HunSkillsGui hsg = new HunSkillsGui();
						hsg.Hunskillsinv(p);
						hsg.Hunskillsinv(p);
						break;
					case"Paladin":
					case"성기사":
						playerclass.put(p.getUniqueId(), 3);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
					    pak.putelmr(p, 9, 0.1);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            PalSkillsGui psg = new PalSkillsGui();
						psg.PalSkillsinv(p);
						psg.PalSkillsinv(p);
						break;
					case"Sniper":
					case"저격수":
						playerclass.put(p.getUniqueId(), 4);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(16);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						SnipSkillsGui l = new SnipSkillsGui();
						l.Snipskillsinv(p);
						l.Snipskillsinv(p);
						break;
					case"Launcher":
					case"원소술사":
						playerclass.put(p.getUniqueId(), 5);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            LaunSkillsGui lsg = new LaunSkillsGui();
						lsg.Launskillsinv(p);
						lsg.Launskillsinv(p);
						break;
					case"Archer":
					case"궁수":
						playerclass.put(p.getUniqueId(), 6);
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(36);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
			            ArchSkillsGui asg = new ArchSkillsGui();
						asg.Archskillsinv(p);
						asg.Archskillsinv(p);
						break;
					case"Medic":
					case"의궁":
						playerclass.put(p.getUniqueId(), 61);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(30);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						MedSkillsGui msg = new MedSkillsGui();
						msg.Medskillsinv(p);
						msg.Medskillsinv(p);
						break;
					case"Boxer":
					case"권사":
						playerclass.put(p.getUniqueId(), 7);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(50);
					    pak.putelmr(p, 5, 0.1);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						BoxSkillsGui bxg = new BoxSkillsGui();
						bxg.BoxSkillsinv(p);
						bxg.BoxSkillsinv(p);
						break;
					case"Wrestler":
					case"유술가":
						playerclass.put(p.getUniqueId(), 8);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(46);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						WreSkillsGui wrg = new WreSkillsGui();
						wrg.WreSkillsinv(p);
						wrg.WreSkillsinv(p);
						break;
					case"Tamer":
					case"조련사":
						playerclass.put(p.getUniqueId(), 9);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(30);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						TamSkillsGui tsg = new TamSkillsGui();
						tsg.TamSkillsinv(p);
						tsg.TamSkillsinv(p);
						break;
					case"Taoist":
					case"도사":
						playerclass.put(p.getUniqueId(), 10);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(40);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						TaoSkillsGui tag = new TaoSkillsGui();
						tag.TaoSkillsinv(p);
						tag.TaoSkillsinv(p);
						break;
					case"Illusionist":
					case"환술사":
						playerclass.put(p.getUniqueId(), 11);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(18);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						IllSkillsGui ilg = new IllSkillsGui();
						ilg.IllSkillsinv(p);
						ilg.IllSkillsinv(p);
						break;
					case"FireMage":
					case"화염술사":
						playerclass.put(p.getUniqueId(), 12);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(34);
					    pak.putelmr(p, 10, 0.1);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						FireSkillsGui fsg = new FireSkillsGui();
						fsg.FIreSkillsinv(p);
						fsg.FIreSkillsinv(p);
						break;
					case"Witherist":
					case"위더리스트":
						playerclass.put(p.getUniqueId(), 13);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(28);
					    pak.putelmr(p, 8, 0.1);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						WitSkillsGui wsg = new WitSkillsGui();
						wsg.WitSkillsinv(p);
						wsg.WitSkillsinv(p);
						break;
					case"WitchDoctor":
					case"부두술사":
						playerclass.put(p.getUniqueId(), 14);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(30);
					    pak.putelmr(p, 14, 0.1);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						WdcSkillsGui wdg = new WdcSkillsGui();
						wdg.WdcSkillsinv(p);
						wdg.WdcSkillsinv(p);
						break;
					case"Chemist":
					case"화학자":
						playerclass.put(p.getUniqueId(), 15);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(46);
					    pak.putelmr(p, 11, 0.1);
					    
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						CheSkillsGui chg = new CheSkillsGui();
						chg.CheSkillsinv(p);
						chg.CheSkillsinv(p);
						break;
					case"Forger":
					case"무기공":
						playerclass.put(p.getUniqueId(), 16);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(20);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						ForSkillsGui fgg = new ForSkillsGui();
						fgg.ForSkillsinv(p);
						fgg.ForSkillsinv(p);
						break;
					case"Engineer":
					case"공학자":
						playerclass.put(p.getUniqueId(), 17);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(30);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						EngSkillsGui egg = new EngSkillsGui();
						egg.EngSkillsinv(p);
						egg.EngSkillsinv(p);
						break;
					case"Cook":
					case"요리사":
						playerclass.put(p.getUniqueId(), 18);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(24);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						CookSkillsGui csg = new CookSkillsGui();
						csg.CookSkillsinv(p);
						csg.CookSkillsinv(p);
						break;
					case"Nobility":
					case"귀족":
						playerclass.put(p.getUniqueId(), 19);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(22);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						NobSkillsGui nsg = new NobSkillsGui();
						nsg.NobSkillsinv(p);
						nsg.NobSkillsinv(p);
						break;
					case"OceanKnight":
					case"바다기사":
						playerclass.put(p.getUniqueId(), 20);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(40);
					    pak.putelmr(p, 7, 0.1);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						OceSkillsGui osg = new OceSkillsGui();
						osg.OceSkillsinv(p);
						osg.OceSkillsinv(p);
						break;
					case"Frostman":
					case"빙술사":
						playerclass.put(p.getUniqueId(), 21);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(30);
					    pak.putelmr(p, 6, 0.1);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						FrostSkillsGui frg = new FrostSkillsGui();
						frg.FrostSkillsinv(p);
						frg.FrostSkillsinv(p);
						break;
					case"Angler":
					case"낚시꾼":
						playerclass.put(p.getUniqueId(), 22);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(24);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						AngSkillsGui arg = new AngSkillsGui();
						arg.AngSkillsinv(p);
						arg.AngSkillsinv(p);
						break;
					case"Mercenary":
					case"용병":
						playerclass.put(p.getUniqueId(), 23);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(36);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						break;
					case"Daggerist":
					case"단검사":
						playerclass.put(p.getUniqueId(), 24);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(20);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						break;
					case"Broiler":
					case"투사":
						playerclass.put(p.getUniqueId(), 25);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(30);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						BroSkillsGui brg = new BroSkillsGui();
						brg.BroSkillsinv(p);
						brg.BroSkillsinv(p);
						break;
					case"Musician":
					case"예술가":
						playerclass.put(p.getUniqueId(), 26);
					    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					    p.setMaxHealth(18);
			            
			            p.closeInventory();new ClassData(playerclass).saveData(path +"/plugins/RPGskills/ClassData.data");
						break;
					}

					pc = playerclass;
		    		
					Classgui.LimitBreak(p);
					
                	if(p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
						p.removePotionEffect(PotionEffectType.ABSORPTION);
                	}
                	if(p.hasPotionEffect(PotionEffectType.CONDUIT_POWER))
	        		{
	        			p.removePotionEffect(PotionEffectType.CONDUIT_POWER);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE))
	        		{
	        			p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING))
	        		{
	        			p.removePotionEffect(PotionEffectType.FAST_DIGGING);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE))
	        		{
	        			p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE))
	        		{
	        			p.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.HEALTH_BOOST))
	        		{
	        			p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE))
	        		{
	        			p.removePotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
	        		{
	        			p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.INVISIBILITY))
	        		{
	        			p.removePotionEffect(PotionEffectType.INVISIBILITY);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.JUMP))
	        		{
	        			p.removePotionEffect(PotionEffectType.JUMP);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.LUCK))
	        		{
	        			p.removePotionEffect(PotionEffectType.LUCK);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.NIGHT_VISION))
	        		{
	        			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.REGENERATION))
	        		{
	        			p.removePotionEffect(PotionEffectType.REGENERATION);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.SATURATION))
	        		{
	        			p.removePotionEffect(PotionEffectType.SATURATION);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.SLOW_FALLING))
	        		{
	        			p.removePotionEffect(PotionEffectType.SLOW_FALLING);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.SPEED))
	        		{
	        			p.removePotionEffect(PotionEffectType.SPEED);
	        		}
	        		if(p.hasPotionEffect(PotionEffectType.WATER_BREATHING))
	        		{
	        			p.removePotionEffect(PotionEffectType.WATER_BREATHING);
	        		}
				}
			
			
		}
		
	}
    
    final public HashMap<UUID, Integer> getPlayerclassdata(){
        String path = new File("").getAbsolutePath();
        ClassData data = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		return data.playerclass;
	}
}