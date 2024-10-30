package io.github.chw3021.obtains;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import io.github.chw3021.obtains.quests.BuriedTreasureQuest;
import io.github.chw3021.obtains.quests.DesertPyramidQuest;
import io.github.chw3021.obtains.quests.IglooQuest;
import io.github.chw3021.obtains.quests.JungleTempleQuest;
import io.github.chw3021.obtains.quests.MineshaftQuest;
import io.github.chw3021.obtains.quests.OceanMonumentQuest;
import io.github.chw3021.obtains.quests.OceanRuinsQuest;
import io.github.chw3021.obtains.quests.PillagerOutpostQuest;
import io.github.chw3021.obtains.quests.RuinedPortalQuest;
import io.github.chw3021.obtains.quests.ShipwreckQuest;
import io.github.chw3021.obtains.quests.WoodlandMansionQuest;

public class NPCcontact implements Listener{

	@EventHandler
	public void Start(PlayerInteractEntityEvent ev) 
	{
		MineshaftQuest.getInstance().QuestStart(ev);
		BuriedTreasureQuest.getInstance().QuestStart(ev);
		IglooQuest.getInstance().QuestStart(ev);
		OceanRuinsQuest.getInstance().QuestStart(ev);
		WoodlandMansionQuest.getInstance().QuestStart(ev);
		ShipwreckQuest.getInstance().QuestStart(ev);
		OceanMonumentQuest.getInstance().QuestStart(ev);
		JungleTempleQuest.getInstance().QuestStart(ev);
		PillagerOutpostQuest.getInstance().QuestStart(ev);
		DesertPyramidQuest.getInstance().QuestStart(ev);
		RuinedPortalQuest.getInstance().QuestStart(ev);
	}

	@EventHandler
	public void Clear(EntityDeathEvent d) 
	{
		MineshaftQuest.getInstance().QuestClear(d);
		BuriedTreasureQuest.getInstance().QuestClear(d);
		IglooQuest.getInstance().QuestClear(d);
		OceanRuinsQuest.getInstance().QuestClear(d);
		WoodlandMansionQuest.getInstance().QuestClear(d);
		ShipwreckQuest.getInstance().QuestClear(d);
		OceanMonumentQuest.getInstance().QuestClear(d);
		JungleTempleQuest.getInstance().QuestClear(d);
		
		DesertPyramidQuest.getInstance().QuestClear(d);
		RuinedPortalQuest.getInstance().QuestClear(d);
	}
	
	
	@EventHandler
	public void Quit(PlayerQuitEvent ev) 
	{
		MineshaftQuest.getInstance().GiveUp(ev);
		BuriedTreasureQuest.getInstance().GiveUp(ev);
		IglooQuest.getInstance().GiveUp(ev);
		OceanRuinsQuest.getInstance().GiveUp(ev);
		WoodlandMansionQuest.getInstance().GiveUp(ev);
		ShipwreckQuest.getInstance().GiveUp(ev);
		OceanMonumentQuest.getInstance().GiveUp(ev);
		JungleTempleQuest.getInstance().GiveUp(ev);
		PillagerOutpostQuest.getInstance().GiveUp(ev);
		DesertPyramidQuest.getInstance().GiveUp(ev);

		RuinedPortalQuest.getInstance().GiveUp(ev);
	}


	@EventHandler
	public void Die(PlayerDeathEvent ev) 
	{
		MineshaftQuest.getInstance().Failed(ev);
		BuriedTreasureQuest.getInstance().Failed(ev);
		IglooQuest.getInstance().Failed(ev);
		OceanRuinsQuest.getInstance().Failed(ev);
		WoodlandMansionQuest.getInstance().Failed(ev);
		ShipwreckQuest.getInstance().Failed(ev);
		OceanMonumentQuest.getInstance().Failed(ev);
		JungleTempleQuest.getInstance().Failed(ev);
		PillagerOutpostQuest.getInstance().Failed(ev);
		DesertPyramidQuest.getInstance().Failed(ev);
		RuinedPortalQuest.getInstance().Failed(ev);
	}
	

	//@EventHandler
	public void Off(PluginDisableEvent ev) 
	{
		BuriedTreasureQuest.getInstance().Reset(ev);
		IglooQuest.getInstance().Reset(ev);
		OceanRuinsQuest.getInstance().Reset(ev);
		WoodlandMansionQuest.getInstance().Reset(ev);
		ShipwreckQuest.getInstance().Reset(ev);
		OceanMonumentQuest.getInstance().Reset(ev);
		JungleTempleQuest.getInstance().Reset(ev);
		PillagerOutpostQuest.getInstance().Reset(ev);
		DesertPyramidQuest.getInstance().Reset(ev);
		RuinedPortalQuest.getInstance().Reset(ev);
	}


	@EventHandler
	public void Open(InventoryOpenEvent d) 
	{
		BuriedTreasureQuest.getInstance().Open(d);
		PillagerOutpostQuest.getInstance().QuestClear(d);
		
	}
	@EventHandler
	public void Close(InventoryCloseEvent d) 
	{
		BuriedTreasureQuest.getInstance().Close(d);
	}

	@EventHandler
	public void Place(BlockPlaceEvent d) 
	{
		BuriedTreasureQuest.getInstance().Place(d);
		PillagerOutpostQuest.getInstance().Place(d);
	}

	@EventHandler
	public void Break(BlockBreakEvent d) 
	{
		BuriedTreasureQuest.getInstance().Break(d);
		PillagerOutpostQuest.getInstance().Break(d);
	}
	
}
