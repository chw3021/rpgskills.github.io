package io.github.chw3021.party;



import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Partycom implements CommandExecutor, Serializable {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 7984258972772433886L;
	public static HashMap<Player, Scoreboard> pb = new HashMap<>();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args)         
    {
		if(sender instanceof Player && alias.equals("party"))
		{
			if(args.length==0 || args[0].equals("help"))
			{
				Player p = (Player)sender;
				
				p.sendMessage("/party create <partyname>: Create Party and be owner");
				p.sendMessage("/party join <partyname>: Join to the party");
				p.sendMessage("/party invite <playername>: Invite player to your party(Only Owner can use)");
				p.sendMessage("/party kick <playername>: Teleport All members(Only Owner can use)");
				p.sendMessage("/party leave: Leave Party");
				p.sendMessage("/party assemble: Teleport All members(Only Owner can use)");
				p.sendMessage("/party list: Show Current Existing Parties & Owners");
				if(PartyData.hasParty(p)) {p.sendMessage("You belong to party: " + PartyData.getParty(p));}
			}
			else if(args.length > 0)
			{
				try{				
				if(args[0].equals("create"))
				{
					Player p = (Player)sender;
					String sbs = args[1];
					if(!PartyData.hasParty(p))
						if(PartyData.isPartyexist(sbs)) {p.sendMessage("Already existing party");}
						else {PartyData.addNewParty(p, sbs);
						p.sendMessage("party " + sbs + " created");
				        Scoreboard nboard = Bukkit.getScoreboardManager().getNewScoreboard();
				        Scoreboard board = p.getScoreboard();
				        pb.put(p, board);
						Objective heart = nboard.registerNewObjective(sbs ,"health", "[Health]", RenderType.HEARTS);
						heart.setDisplaySlot(DisplaySlot.BELOW_NAME);
				        Objective po = nboard.registerNewObjective(sbs+"p" ,"dummy", "Party: " + sbs);
				        po.setDisplaySlot(DisplaySlot.SIDEBAR);
						Team t = nboard.registerNewTeam(sbs);
						p.setScoreboard(nboard);
						t.addEntry(p.getName());
						t.setCanSeeFriendlyInvisibles(true);
						t.setAllowFriendlyFire(true);
				        po.getScore(p.getName()).setScore((int) p.getHealth());
						}
					else
					{p.sendMessage("You already have a party");}
				}
				else if(args[0].equals("join"))
				{
					Player p = (Player) sender;
					String sbs = args[1];
					
					if(PartyData.isPartyexist(sbs)) 
					{
						if(PartyData.hasParty(p))
							{p.sendMessage("You're already in a party");}
						else {
							Scoreboard oboard = PartyData.getOwner(sbs).getScoreboard();
							Scoreboard pboard = p.getScoreboard();
							Team t = oboard.getTeam(sbs);
					        pb.put(p, pboard);
							t.addEntry(p.getName());
							p.setScoreboard(oboard);
							PartyData.addNewPartyMember(p, sbs);
							p.sendMessage("joined party " + sbs);
							oboard.getObjective(sbs+"p").getScore(p.getName()).setScore((int) p.getHealth());
						}
					}
					else {p.sendMessage("invalid party");}
				}
				else if(args[0].equals("invite"))
				{
					Player p = (Player)sender;
					String sbs = args[1];
					
					if(PartyData.isOwner(p) && PartyData.hasParty(p)) 
					{
						if(PartyData.hasParty(Bukkit.getPlayer(sbs)))
							{p.sendMessage("The player is already in a party");}
						else {
							Scoreboard lboard = p.getScoreboard();
							Scoreboard pboard = Bukkit.getPlayer(sbs).getScoreboard();
							Team t = lboard.getEntryTeam(p.getName());
					        pb.put(Bukkit.getPlayer(sbs), pboard);
							t.addEntry(sbs);
							Bukkit.getPlayer(sbs).setScoreboard(lboard);
							PartyData.addNewPartyMember(Bukkit.getPlayer(sbs), PartyData.getParty(p));
							Bukkit.getPlayer(sbs).sendMessage("You just invited party " + PartyData.getParty(p));
							p.sendMessage(sbs+ " joined your party");
							lboard.getObjective(PartyData.getParty(p)+"p").getScore(Bukkit.getPlayer(sbs).getName()).setScore((int) Bukkit.getPlayer(sbs).getHealth());
							}
					}
					else {p.sendMessage("You should be owner of a party");}
						
				}
				else if(args[0].equals("kick"))
				{
					Player p = (Player)sender;
					String sbs = args[1];
					
					if(PartyData.isOwner(p) && PartyData.hasParty(p)) {
						if(PartyData.hasParty(Bukkit.getPlayer(sbs))) {
						p.sendMessage(sbs + " kicked from your party");
						Bukkit.getPlayer(sbs).sendMessage("You just kicked from party " + PartyData.getParty(p));
						Scoreboard lboard = p.getScoreboard();
						lboard.resetScores(sbs);
						Scoreboard pboard = pb.get(Bukkit.getPlayer(sbs));
						Team t = lboard.getEntryTeam(p.getName());
						t.removeEntry(sbs);
						Bukkit.getPlayer(sbs).setScoreboard(pboard);
						PartyData.removePartyMember(Bukkit.getPlayer(sbs), PartyData.getParty(p));
						}
						else {p.sendMessage("The player isn't in the party");}}
					else {p.sendMessage("You should be owner of a party");}
				}
				else if(args[0].equals("leave"))
				{					
					Player p = (Player)sender;
					
					if(PartyData.hasParty(p)) 
					{
						Scoreboard lboard = p.getScoreboard();
						Scoreboard pboard = pb.get(p);
						if(PartyData.getMembers(PartyData.getParty(p)).count()>1)
						{
							if(PartyData.isOwner(p)) {
								String par = PartyData.getParty(p);
								PartyData.removePartyMember(p, par);
								Player newowner = (Player) Bukkit.getPlayer(PartyData.getMembers(par).filter(o->(o!=p.getUniqueId())).findAny().get());
								PartyData.setNewOwner(newowner,par);
								newowner.sendMessage("You just took over owner of this party");
								p.sendMessage("You left your party");
								lboard.resetScores(p.getName());
								Team t = lboard.getEntryTeam(p.getName());
								t.removeEntry(p.getName());
								p.setScoreboard(pboard);
							}
							else
							{
								String par = PartyData.getParty(p);
								p.sendMessage("You left your party");
								lboard.resetScores(p.getName());
								Team t = lboard.getEntryTeam(p.getName());
								t.removeEntry(p.getName());
								p.setScoreboard(pboard);
								PartyData.removePartyMember(p, par);
							}
						}
						else {
							String par = PartyData.getParty(p);
							p.sendMessage("You left your party");
							lboard.resetScores(p.getName());
							Team t = lboard.getEntryTeam(p.getName());
							t.removeEntry(p.getName());
							p.setScoreboard(pboard);
							PartyData.removePartyMember(p, par);
							PartyData.removeParty(par);
						}
					}
					else {p.sendMessage("You aren't in any party");}
				}
				else if(args[0].equals("list"))
				{					
					Player p = (Player)sender;
					
					for(UUID o : PartyData.getOwners()) {
						p.sendMessage("Party Name: "+ PartyData.getParty((Player) Bukkit.getPlayer(o)) +" | Owner: " + Bukkit.getPlayer(o).getName());
					}
				}
				else if(args[0].equals("assemble"))
				{
					Player p = (Player)sender;
					
					if(PartyData.isOwner(p) && PartyData.hasParty(p)) 
					{
						for(UUID mem : PartyData.getMembers(PartyData.getParty(p)).collect(Collectors.toSet()))
						{
							Bukkit.getPlayer(mem).teleport(p.getLocation());
						}
					}
					else {p.sendMessage("You should be owner of a party");}
				}
				else {
					Player p = (Player)sender;p.sendMessage("invalid command");
					p.sendMessage("/party create <partyname>: Create Party and be owner");
					p.sendMessage("/party join <partyname>: Join to the party");
					p.sendMessage("/party invite <playername>: Invite player to your party(Only Owner can use)");
					p.sendMessage("/party kick <playername>: Teleport All members(Only Owner can use)");
					p.sendMessage("/party leave: Leave Party");
					p.sendMessage("/party assemble: Teleport All members(Only Owner can use)");
					p.sendMessage("/party list: Show Current Existing Parties & Owners");
					}
	            return true;}
				catch (NullPointerException | NoSuchMethodError e)
				{
					Player p = (Player)sender;
					p.sendMessage("invalid command");
					p.sendMessage("/party create <partyname>: Create Party and be owner");
					p.sendMessage("/party join <partyname>: Join to the party");
					p.sendMessage("/party invite <playername>: Invite player to your party(Only Owner can use)");
					p.sendMessage("/party kick <playername>: Teleport All members(Only Owner can use)");
					p.sendMessage("/party leave: Leave Party");
					p.sendMessage("/party assemble: Teleport All members(Only Owner can use)");
					p.sendMessage("/party list: Show Current Existing Parties & Owners");
		            e.printStackTrace();
				}
			}	
		}
		else
		{
			sender.sendMessage("invaild sender");
		}
		return false;
	}

}