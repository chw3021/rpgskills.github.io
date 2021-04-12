package io.github.chw3021.commons;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.FluidCollisionMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.chw3021.archer.ArchSkillsGui;
import io.github.chw3021.berserker.BerSkillsGui;
import io.github.chw3021.boxer.BoxSkillsGui;
import io.github.chw3021.chemist.CheSkillsGui;
import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Classgui;
import io.github.chw3021.cook.CookSkillsGui;
import io.github.chw3021.engineer.EngSkillsGui;
import io.github.chw3021.firemage.FireSkillsGui;
import io.github.chw3021.forger.ForSkillsGui;
import io.github.chw3021.frostman.FrostSkillsGui;
import io.github.chw3021.hunter.HunSkillsGui;
import io.github.chw3021.illusionist.IllSkillsGui;
import io.github.chw3021.items.Fighter;
import io.github.chw3021.launcher.LaunSkillsGui;
import io.github.chw3021.medic.MedSkillsGui;
import io.github.chw3021.nobility.NobSkillsGui;
import io.github.chw3021.oceanknight.OceSkillsGui;
import io.github.chw3021.paladin.PalSkillsGui;
import io.github.chw3021.rmain.RMain;
import io.github.chw3021.sniper.SnipSkillsGui;
import io.github.chw3021.swordman.SwordSkillsGui;
import io.github.chw3021.tamer.TamSkillsGui;
import io.github.chw3021.taoist.TaoSkillsGui;
import io.github.chw3021.witchdoctor.WdcSkillsGui;
import io.github.chw3021.witherist.WitSkillsGui;
import io.github.chw3021.wreltler.WreSkillsGui;

public class Rpgs implements CommandExecutor, Serializable {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -712033656548621240L;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String [] args)         
    {
		if(sender instanceof Player)
		{
			Player p = (Player)sender;
			
			if(args.length==0)
			{
				p.sendMessage("made by chw3021");
			}
			else if(args.length > 0)
			{

				if(args[0].equalsIgnoreCase("item"))
				{
					Fighter.knuckle(p, 1);
				}
				if(args[0].equalsIgnoreCase("stand"))
				{
					Zombie stand = p.getWorld().spawn(p.getTargetBlock(null, 3).getLocation(), Zombie.class);
					stand.setMaxHealth(Double.parseDouble(args[1]));
					stand.setHealth(stand.getMaxHealth());
					stand.setAI(false);
				}
				if(args[0].equalsIgnoreCase("class"))
				{
					Classgui classgui = new Classgui();
					classgui.open(p);
				}
				if(args[0].equalsIgnoreCase("skill"))
				{
				    String path = new File("").getAbsolutePath();
					ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
					HashMap<UUID, Integer> playerclass = cdata.playerclass;
					if(playerclass.get(p.getUniqueId()) == 0)
					{
						SwordSkillsGui ssg = new SwordSkillsGui();
						ssg.SwordSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 1)
					{
						BerSkillsGui bsg = new BerSkillsGui();
						bsg.Berskillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 2)
					{
						HunSkillsGui hsg = new HunSkillsGui();
						hsg.Hunskillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 3)
					{
						PalSkillsGui psg = new PalSkillsGui();
						psg.PalSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 4)
					{
						SnipSkillsGui l = new SnipSkillsGui();
						l.Snipskillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 5)
					{
						LaunSkillsGui l = new LaunSkillsGui();
						l.Launskillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 6)
					{
						ArchSkillsGui l = new ArchSkillsGui();
						l.Archskillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 61)
					{
						MedSkillsGui l = new MedSkillsGui();
						l.Medskillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 7)
					{
						BoxSkillsGui l = new BoxSkillsGui();
						l.BoxSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 8)
					{
						WreSkillsGui wrg = new WreSkillsGui();
						wrg.WreSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 9)
					{
						TamSkillsGui tsg = new TamSkillsGui();
						tsg.TamSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 10)
					{
						TaoSkillsGui tag = new TaoSkillsGui();
						tag.TaoSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 11)
					{
						IllSkillsGui ilg = new IllSkillsGui();
						ilg.IllSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 12)
					{
						FireSkillsGui fsg = new FireSkillsGui();
						fsg.FIreSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 13)
					{
						WitSkillsGui wsg = new WitSkillsGui();
						wsg.WitSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 14)
					{
						WdcSkillsGui wdg = new WdcSkillsGui();
						wdg.WdcSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 15)
					{
						CheSkillsGui chg = new CheSkillsGui();
						chg.CheSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 16)
					{
						ForSkillsGui fgg = new ForSkillsGui();
						fgg.ForSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 17)
					{
						EngSkillsGui egg = new EngSkillsGui();
						egg.EngSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 18)
					{
						CookSkillsGui csg = new CookSkillsGui();
						csg.CookSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 19)
					{
						NobSkillsGui nsg = new NobSkillsGui();
						nsg.NobSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 20)
					{
						OceSkillsGui osg = new OceSkillsGui();
						osg.OceSkillsinv(p);
					}
					else if(playerclass.get(p.getUniqueId()) == 21)
					{
						FrostSkillsGui fsg = new FrostSkillsGui();
						fsg.FrostSkillsinv(p);
					}
				}
			}	
		}
		else
		{
			sender.sendMessage("invaild command");
		}
		return false;
	}

}
