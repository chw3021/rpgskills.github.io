package io.github.chw3021.obtains;

import java.util.Set;

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
import org.reflections.Reflections;

import io.github.chw3021.obtains.quests.BuriedTreasureQuest;
import io.github.chw3021.obtains.quests.DesertPyramidQuest;
import io.github.chw3021.obtains.quests.IglooQuest;
import io.github.chw3021.obtains.quests.JungleTempleQuest;
import io.github.chw3021.obtains.quests.MineshaftQuest;
import io.github.chw3021.obtains.quests.OceanMonumentQuest;
import io.github.chw3021.obtains.quests.OceanRuinsQuest;
import io.github.chw3021.obtains.quests.PillagerOutpostQuest;
import io.github.chw3021.obtains.quests.Quest;
import io.github.chw3021.obtains.quests.RuinedPortalQuest;
import io.github.chw3021.obtains.quests.ShipwreckQuest;
import io.github.chw3021.obtains.quests.StrongholdQuest;
import io.github.chw3021.obtains.quests.WoodlandMansionQuest;

public class NPCcontact implements Listener{

    final private Reflections reflections = new Reflections("io.github.chw3021.obtains.quests"); // Replace with your package name
    final private Set<Class<? extends Quest>> questClasses = reflections.getSubTypesOf(Quest.class);

	@EventHandler
	public void Start(PlayerInteractEntityEvent ev) 
	{
        for (Class<? extends Quest> questClass : questClasses) {
            try {
                Quest quest = questClass.getDeclaredConstructor().newInstance();
                quest.QuestStart(ev);
            } catch (Exception e) {
            }
        }
	}

	@EventHandler
	public void Clear(EntityDeathEvent d) 
	{
        for (Class<? extends Quest> questClass : questClasses) {
            try {
                Quest quest = questClass.getDeclaredConstructor().newInstance();
                quest.QuestClear(d);
            } catch (Exception e) {
            }
        }
	}
	
	
	@EventHandler
	public void Quit(PlayerQuitEvent ev) 
	{
        for (Class<? extends Quest> questClass : questClasses) {
            try {
                Quest quest = questClass.getDeclaredConstructor().newInstance();
                quest.GiveUp(ev);
            } catch (Exception e) {
            }
        }
	}


	@EventHandler
	public void Die(PlayerDeathEvent ev) 
	{
        for (Class<? extends Quest> questClass : questClasses) {
            try {
                Quest quest = questClass.getDeclaredConstructor().newInstance();
                quest.Failed(ev);
            } catch (Exception e) {
            }
        }
	}
	

	//@EventHandler
	public void Off(PluginDisableEvent ev) 
	{
        for (Class<? extends Quest> questClass : questClasses) {
            try {
                Quest quest = questClass.getDeclaredConstructor().newInstance();
                quest.Reset(ev);
            } catch (Exception e) {
            }
        }
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
