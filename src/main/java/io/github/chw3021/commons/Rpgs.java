package io.github.chw3021.commons;

import java.io.Serializable;
import java.util.HashSet;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Classgui;
import io.github.chw3021.classes.Proficiency;
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
import io.github.chw3021.items.Elements;
import io.github.chw3021.items.armors.Boots;
import io.github.chw3021.items.armors.Chestplate;
import io.github.chw3021.items.armors.Helmet;
import io.github.chw3021.items.armors.Leggings;
import io.github.chw3021.items.weapons.Weapons;
import io.github.chw3021.monsters.raids.Summoned;
import net.md_5.bungee.api.ChatColor;

public class Rpgs extends Summoned implements CommandExecutor, Serializable {
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = -712033656548621240L;

	final private void help(Player p) {

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			p.sendMessage(ChatColor.GREEN +"[Made By chw3021]");
			p.sendMessage(ChatColor.GREEN +"---Rpg ��ɾ�---");
			p.sendMessage(ChatColor.GREEN +"/rpg dam (/rpg d, /rpg damage) -> ���� �������� ǥ���մϴ�");
			p.sendMessage(ChatColor.GREEN +"/rpg element (/rpg el) -> ���� ���� �迭 ���ݷ�, ���׷��� ǥ���մϴ�");
			p.sendMessage(ChatColor.GREEN +"/rpg escape (/rpg es) -> ���� �������� ������ �����մϴ�");
			p.sendMessage(ChatColor.GREEN +"/rpg skill (/rpg s) -> ��ųâ�� ���ϴ�");
			p.sendMessage(ChatColor.GREEN +"/rpg class (/rpg c) -> ���� ����â�� ���ϴ�");
			p.sendMessage(ChatColor.GREEN +"/rpg rank (/rpg r) -> �ڽ��� ������ ���õ� 10������ ǥ���մϴ�");
			if(p.isOp())
			{
				p.sendMessage(ChatColor.RED +"/rpg exp [amounts]-> give player amounts of exp");
				p.sendMessage(ChatColor.RED +"/rpg pro [amounts]-> give player amounts of proficiency");
				p.sendMessage(ChatColor.RED +"/rpg enchant(/rpg ench) [enchantment] [level] -> enchant item in main hand");
				p.sendMessage(ChatColor.RED +"/rpg enchant(/rpg ench) clear -> remove enchantment of item in main hand");
				p.sendMessage(ChatColor.RED +"/rpg elements(/rpg elm) -> open Elements Inventory");
			}
			p.sendMessage(ChatColor.GREEN +" ");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"---��Ƽ ��ɾ�---");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party create <��Ƽ��>: ��Ƽ�� �����ϰ� ��Ƽ���� �˴ϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party join <��Ƽ��>: �ش� ��Ƽ�� �����մϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party password <��й�ȣ>: ��Ƽ�� �ش� ��й�ȣ�� ��ż�, ��й�ȣ�� �Է��ؾ� ��Ƽ�� �����Ҽ� �ְ��մϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party invite <��Ƽ��>: (��Ƽ�常 ����) �ش� �÷��̾ �ʴ��մϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party kick <�÷��̾� �̸�>: (��Ƽ�常 ����) �ش� �÷��̾ �����մϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party leave: ��Ƽ�� �����ϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party assemble: (��Ƽ�常 ����) ��Ƽ������ �ڽ��� ��ġ�� �̵���ŵ�ϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party list: ���� �����Ǿ��ִ� ��Ƽ���� ����� ǥ���մϴ�");
		}
		else {
			p.sendMessage(ChatColor.GREEN +"[Made By chw3021]");
			p.sendMessage(ChatColor.GREEN +"---Rpg Commands---");
			p.sendMessage(ChatColor.GREEN +"/rpg dam (/rpg d, /rpg damage) -> show player's current attack damage");
			p.sendMessage(ChatColor.GREEN +"/rpg element (/rpg el) -> show player's element power & resistance");
			p.sendMessage(ChatColor.GREEN +"/rpg skill (/rpg s) -> show player's skills gui");
			p.sendMessage(ChatColor.GREEN +"/rpg escape (/rpg es) -> end current fighting");
			p.sendMessage(ChatColor.GREEN +"/rpg class (/rpg c) -> show player's classes gui");
			p.sendMessage(ChatColor.GREEN +"/rpg rank (/rpg r) -> show Top 10 ranking of proficiency of your class");
			if(p.isOp())
			{
				p.sendMessage(ChatColor.RED +"/rpg exp [amounts]-> give player amounts of exp");
				p.sendMessage(ChatColor.RED +"/rpg pro [amounts]-> give player amounts of proficiency");
				p.sendMessage(ChatColor.RED +"/rpg enchant(/rpg ench) [enchantment] [level] -> enchant item in main hand");
				p.sendMessage(ChatColor.RED +"/rpg enchant(/rpg ench) clear -> remove enchantment of item in main hand");
				p.sendMessage(ChatColor.RED +"/rpg elements(/rpg elm) -> open Elements Inventory");
			}
			p.sendMessage(ChatColor.GREEN +" ");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"---Party Commands---");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party create <partyname>: Create Party and be owner");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party join <partyname>: Join to the party");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party password <password>: Lock the Party With the Password. Player Should Enter the Password To Join This Party");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party invite <playername>: Invite player to your party(Only Owner can use)");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party kick <playername>: Kick the Player(Only Owner can use)");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party leave: Leave Party");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party assemble: Teleport All members(Only Owner can use)");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party list: Show Current Existing Parties & Owners");
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String [] args)         
    {
		if(!(sender instanceof Player) &&alias.equals("rpg"))
		{
		}
		if(sender instanceof Player && alias.equals("rpg"))
		{
			Player p = (Player)sender;
			
			if(args.length==0 || args[0].equals("help") || args[0].equals("?"))
			{
				help(p);
			}

			if(args[0].equalsIgnoreCase("water") && p.isOp())
			{
				HashSet<Location> lhs = new HashSet<>();
				final Location rl = p.getLocation().clone();
				for(int ix = -10; ix<10; ix++) {
					for(int iy = -10; iy<10; iy++) {
						for(int iz = -10; iz<10; iz++) {
							if(rl.clone().add(ix, iy, iz).getBlock().getType().name().contains("WATER"))
							lhs.add(rl.clone().add(ix, iy, iz));
						}
					}
				}
				lhs.forEach(l -> l.getBlock().setType(Material.VOID_AIR));
				
			}
			else if(args.length > 0)
			{
				if(args[0].equalsIgnoreCase("bm")  || args[0].equalsIgnoreCase("bi"))
				{
					p.sendRawMessage(p.getLocation().getBlock().getBiome().toString());
				}

				else if(args[0].equalsIgnoreCase("near"))
				{
					p.sendRawMessage(p.getNearbyEntities(10,5, 10).toString());
				}

				else if(args[0].equalsIgnoreCase("escape")||args[0].equalsIgnoreCase("es"))
				{
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						RaidFinish(getheroname(p), "Ż��","���͵��� �������ϴ�",0);
					}
					else {
						RaidFinish(getheroname(p), "Escaped","Monsters Left",0);
					}
				}

				else if(args[0].equalsIgnoreCase("element")||args[0].equalsIgnoreCase("el"))
				{
					Pak pak = new Pak();
					pak.eldmes(p);
				}
				else if(args[0].equalsIgnoreCase("exp") && p.isOp()&& !args[1].isEmpty())
				{
					p.giveExp(Integer.parseInt(args[1]));
					Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,Integer.parseInt(args[1])));
				}
				else if(args[0].equalsIgnoreCase("class") || args[0].equalsIgnoreCase("c"))
				{
					Classgui classgui = new Classgui();
					classgui.open(p);
				}
				else if(args[0].equalsIgnoreCase("rank") || args[0].equalsIgnoreCase("r"))
				{
					Proficiency.prorank(p);
				}
				else if(args[0].equalsIgnoreCase("pro") && p.isOp()&& !args[1].isEmpty())
				{
					Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,Integer.parseInt(args[1])));
				}
				else if((args[0].equalsIgnoreCase("elements")||args[0].equalsIgnoreCase("elm")) && p.isOp())
				{
					Elements.ElementsInv(p);
				}
				else if((args[0].equalsIgnoreCase("armor")||args[0].equalsIgnoreCase("ar")) && p.isOp())
				{
					Elements.give(Boots.get(Integer.parseInt(args[1]), p), 1, p);
					Elements.give(Helmet.get(Integer.parseInt(args[1]), p), 1, p);
					Elements.give(Leggings.get(Integer.parseInt(args[1]), p), 1, p);
					Elements.give(Chestplate.get(Integer.parseInt(args[1]), p), 1, p);
				}
				else if((args[0].equalsIgnoreCase("scroll")||args[0].equalsIgnoreCase("scroll")) && p.isOp())
				{
					Elements.give(Elements.getscroll(p), 1, p);
				}
				else if((args[0].equalsIgnoreCase("weapons")||args[0].equalsIgnoreCase("w")) && p.isOp())
				{
					Weapons w = new Weapons();
					w.winv(p);
				}
				else if((args[0].equalsIgnoreCase("custom")) && p.isOp())
				{
					p.sendMessage(p.getEquipment().getItemInMainHand().getItemMeta().getLocalizedName()+"");
					
				}
				else if(args[0].equalsIgnoreCase("god") && p.isOp()&& !args[1].isEmpty())
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 999999, Integer.parseInt(args[1]), false, false));
				}
				else if(args[0].equalsIgnoreCase("speed") && p.isOp()&& !args[1].isEmpty())
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, Integer.parseInt(args[1]), false, false));
				}
				else if((args[0].equalsIgnoreCase("portal")||args[0].equalsIgnoreCase("por")) && p.isOp())
				{
					Elements.BedInv(p);
				}
				else if((args[0].equalsIgnoreCase("gm")||args[0].equalsIgnoreCase("g")) && p.isOp() && !args[1].isEmpty())
				{
					if(args[1].equals("0") || args[1].equalsIgnoreCase("s")) {
						p.setGameMode(GameMode.SURVIVAL);
					}
					else if(args[1].equals("1") || args[1].equalsIgnoreCase("c")) {
						p.setGameMode(GameMode.CREATIVE);
					}
					else if(args[1].equals("2") || args[1].equalsIgnoreCase("a")) {
						p.setGameMode(GameMode.ADVENTURE);
					}
					else if(args[1].equals("3") || args[1].equalsIgnoreCase("t")) {
						p.setGameMode(GameMode.SPECTATOR);
					}
				}
				else if((args[0].equalsIgnoreCase("enchant")||args[0].equalsIgnoreCase("ench")) && p.isOp() && !args[1].isEmpty())
				{
					if(args[1].equalsIgnoreCase("clear")) {
						p.getEquipment().getItemInMainHand().getEnchantments().keySet().forEach(en -> {
							p.getEquipment().getItemInMainHand().removeEnchantment(en);
						});
						return true;
					}
					else if(!args[2].isEmpty()) {
						p.getEquipment().getItemInMainHand().addUnsafeEnchantment(Enchantment.getByKey(NamespacedKey.fromString(args[1])), Integer.parseInt(args[2]));
					}
				}
				else if(args[0].equalsIgnoreCase("dam") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("damage"))
				{
					p.sendMessage(String.valueOf(Pak.player_damage.get(p.getName())) );
				}
				else if(args[0].equalsIgnoreCase("ad") || args[0].equalsIgnoreCase("atd") || args[0].equalsIgnoreCase("atdamage"))
				{
					p.sendMessage(String.valueOf(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue()) );
				}
				else if(args[0].equalsIgnoreCase("skill") || args[0].equalsIgnoreCase("s"))
				{
					if(ClassData.pc.get(p.getUniqueId()) == 0)
					{
						SwordSkillsGui ssg = new SwordSkillsGui();
						ssg.SwordSkillsinv(p);
						ssg.SwordSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 1)
					{
						BerSkillsGui bsg = new BerSkillsGui();
						bsg.Berskillsinv(p);
						bsg.Berskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 2)
					{
						HunSkillsGui hsg = new HunSkillsGui();
						hsg.Hunskillsinv(p);
						hsg.Hunskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 3)
					{
						PalSkillsGui psg = new PalSkillsGui();
						psg.PalSkillsinv(p);
						psg.PalSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 4)
					{
						SnipSkillsGui l = new SnipSkillsGui();
						l.Snipskillsinv(p);
						l.Snipskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 5)
					{
						LaunSkillsGui l = new LaunSkillsGui();
						l.Launskillsinv(p);
						l.Launskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 6)
					{
						ArchSkillsGui l = new ArchSkillsGui();
						l.Archskillsinv(p);
						l.Archskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 61)
					{
						MedSkillsGui l = new MedSkillsGui();
						l.Medskillsinv(p);
						l.Medskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 7)
					{
						BoxSkillsGui l = new BoxSkillsGui();
						l.BoxSkillsinv(p);
						l.BoxSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 8)
					{
						WreSkillsGui wrg = new WreSkillsGui();
						wrg.WreSkillsinv(p);
						wrg.WreSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 9)
					{
						TamSkillsGui tsg = new TamSkillsGui();
						tsg.TamSkillsinv(p);
						tsg.TamSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 10)
					{
						TaoSkillsGui tag = new TaoSkillsGui();
						tag.TaoSkillsinv(p);
						tag.TaoSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 11)
					{
						IllSkillsGui ilg = new IllSkillsGui();
						ilg.IllSkillsinv(p);
						ilg.IllSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 12)
					{
						FireSkillsGui fsg = new FireSkillsGui();
						fsg.FIreSkillsinv(p);
						fsg.FIreSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 13)
					{
						WitSkillsGui wsg = new WitSkillsGui();
						wsg.WitSkillsinv(p);
						wsg.WitSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 14)
					{
						WdcSkillsGui wdg = new WdcSkillsGui();
						wdg.WdcSkillsinv(p);
						wdg.WdcSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 15)
					{
						CheSkillsGui chg = new CheSkillsGui();
						chg.CheSkillsinv(p);
						chg.CheSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 16)
					{
						ForSkillsGui fgg = new ForSkillsGui();
						fgg.ForSkillsinv(p);
						fgg.ForSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 17)
					{
						EngSkillsGui egg = new EngSkillsGui();
						egg.EngSkillsinv(p);
						egg.EngSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 18)
					{
						CookSkillsGui csg = new CookSkillsGui();
						csg.CookSkillsinv(p);
						csg.CookSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 19)
					{
						NobSkillsGui nsg = new NobSkillsGui();
						nsg.NobSkillsinv(p);
						nsg.NobSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 20)
					{
						OceSkillsGui osg = new OceSkillsGui();
						osg.OceSkillsinv(p);
						osg.OceSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 21)
					{
						FrostSkillsGui fsg = new FrostSkillsGui();
						fsg.FrostSkillsinv(p);
						fsg.FrostSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 22)
					{
						AngSkillsGui fsg = new AngSkillsGui();
						fsg.AngSkillsinv(p);
						fsg.AngSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 25)
					{
						BroSkillsGui bsg = new BroSkillsGui();
						bsg.BroSkillsinv(p);
						bsg.BroSkillsinv(p);
					}
				}
				else
				{
					sender.sendMessage("invaild command");
					help(p);
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
