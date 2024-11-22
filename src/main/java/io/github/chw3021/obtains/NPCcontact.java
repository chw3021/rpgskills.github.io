package io.github.chw3021.obtains;

import java.util.ArrayList;
import java.util.List;
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
import io.github.chw3021.obtains.quests.AncientCityQuest;
import io.github.chw3021.obtains.quests.BastionRemnantQuest;
import io.github.chw3021.obtains.quests.BuriedTreasureQuest;
import io.github.chw3021.obtains.quests.DesertPyramidQuest;
import io.github.chw3021.obtains.quests.EndCityQuest;
import io.github.chw3021.obtains.quests.IglooQuest;
import io.github.chw3021.obtains.quests.JungleTempleQuest;
import io.github.chw3021.obtains.quests.MineshaftQuest;
import io.github.chw3021.obtains.quests.NetherFortressQuest;
import io.github.chw3021.obtains.quests.OceanMonumentQuest;
import io.github.chw3021.obtains.quests.OceanRuinsQuest;
import io.github.chw3021.obtains.quests.PillagerOutpostQuest;
import io.github.chw3021.obtains.quests.Quest;
import io.github.chw3021.obtains.quests.RuinedPortalQuest;
import io.github.chw3021.obtains.quests.ShipwreckQuest;
import io.github.chw3021.obtains.quests.StrongholdQuest;
import io.github.chw3021.obtains.quests.WoodlandMansionQuest;

public class NPCcontact implements Listener{

    private final List<Quest> quests = new ArrayList<>();

    public NPCcontact() {
        // Quest 구현체를 등록
        quests.add(AncientCityQuest.getInstance());
        quests.add(BastionRemnantQuest.getInstance());
        quests.add(BuriedTreasureQuest.getInstance());
        quests.add(DesertPyramidQuest.getInstance());
        quests.add(EndCityQuest.getInstance());
        quests.add(IglooQuest.getInstance());
        quests.add(JungleTempleQuest.getInstance());
        quests.add(MineshaftQuest.getInstance());
        quests.add(NetherFortressQuest.getInstance());
        quests.add(OceanMonumentQuest.getInstance());
        quests.add(OceanRuinsQuest.getInstance());
        quests.add(PillagerOutpostQuest.getInstance());
        quests.add(RuinedPortalQuest.getInstance());
        quests.add(ShipwreckQuest.getInstance());
        quests.add(StrongholdQuest.getInstance());
        quests.add(WoodlandMansionQuest.getInstance());
    }

    // 예제: quests 사용
    public List<Quest> getQuests() {
        return quests;
    }
	@EventHandler
	public void Start(PlayerInteractEntityEvent ev) 
	{
	    for (Quest quest : getQuests()) {
	        quest.QuestStart(ev);
	    }
	}

	@EventHandler
	public void Clear(EntityDeathEvent d) 
	{
	    for (Quest quest : getQuests()) {
	        quest.QuestClear(d);
	    }
	}
	
	
	@EventHandler
	public void Quit(PlayerQuitEvent ev) 
	{
	    for (Quest quest : getQuests()) {
	        quest.GiveUp(ev);
	    }
	}


	@EventHandler
	public void Die(PlayerDeathEvent ev) 
	{
	    for (Quest quest : getQuests()) {
	        quest.Failed(ev);
	    }
	}
	

	//@EventHandler
	public void Off(PluginDisableEvent ev) 
	{
	    for (Quest quest : getQuests()) {
	        quest.Reset(ev);
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
