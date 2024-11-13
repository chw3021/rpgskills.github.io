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
import org.reflections.scanners.Scanners;
import org.reflections.scanners.Scanners.*;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import io.github.chw3021.obtains.quests.BuriedTreasureQuest;
import io.github.chw3021.obtains.quests.PillagerOutpostQuest;
import io.github.chw3021.obtains.quests.Quest;

public class NPCcontact implements Listener{

    final private Reflections reflections = new Reflections(
            new ConfigurationBuilder()
            .forPackages("io.github.chw3021.obtains.quests") // 패키지 경로 설정
            .addScanners(Scanners.SubTypes) // 서브타입 스캐너 추가
    		);

	@EventHandler
	public void Start(PlayerInteractEntityEvent ev) 
	{
		Reflections reflections = new Reflections(
				  new ConfigurationBuilder()
				    .forPackage("io.github.chw3021.obtains.quests")
				    .filterInputsBy(new FilterBuilder().includePackage("io.github.chw3021.obtains.quests"))
				    .setScanners(Scanners.TypesAnnotated, Scanners.MethodsAnnotated, Scanners.MethodsReturn));
		
		Set<Class<?>> questClasses = 
				  reflections.get(Scanners.SubTypes.of(Quest.class).asClass());
        for (Class<?> questClass : questClasses) {
            try {
                Quest quest = (Quest) questClass.getDeclaredConstructor().newInstance();
                quest.QuestStart(ev);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
	}

	@EventHandler
	public void Clear(EntityDeathEvent d) 
	{
	    final Set<Class<? extends Quest>> questClasses = reflections.getSubTypesOf(Quest.class);
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
	    final Set<Class<? extends Quest>> questClasses = reflections.getSubTypesOf(Quest.class);
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
	    final Set<Class<? extends Quest>> questClasses = reflections.getSubTypesOf(Quest.class);
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
	    final Set<Class<? extends Quest>> questClasses = reflections.getSubTypesOf(Quest.class);
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
