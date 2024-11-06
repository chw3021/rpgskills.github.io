package io.github.chw3021.obtains.quests;

import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;

public interface Quest {
    void QuestStart(PlayerInteractEntityEvent ev);
    void QuestClear(EntityDeathEvent ev);
    void GiveUp(PlayerQuitEvent ev);
    void Failed(PlayerDeathEvent ev);
    void Reset(PluginDisableEvent ev);
}
