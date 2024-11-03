package io.github.chw3021.monsters.worldgen;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.chw3021.rmain.RMain;

public class RaidWorldLoad implements Listener {

	//@EventHandler
	public void raidworldload(PluginEnableEvent ev)   
    {
		if (Bukkit.getServer().getWorld("OverworldRaid") == null) {
			WorldCreator rwc = new WorldCreator("OverworldRaid");
			rwc.environment(Environment.NORMAL);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run() {
					rwc.generator(new OverworldRaidChunkGenerator());
					World rw = rwc.createWorld();
					rw.setMetadata("rpgraidworld", new FixedMetadataValue(RMain.getInstance(),true));
					rwc.environment(Environment.NORMAL);
					rw.setDifficulty(Difficulty.HARD);
					rw.setTime(12000);
					rw.setAutoSave(false);
					rw.setPVP(false);
					rw.setSpawnFlags(false, false);
					rw.setGameRule(GameRule.KEEP_INVENTORY, true);
					rw.setGameRule(GameRule.DO_INSOMNIA, false);
					rw.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, false);
					rw.setGameRule(GameRule.DISABLE_RAIDS, true);
					rw.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
					rw.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
					rw.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
					rw.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
					rw.setGameRule(GameRule.DO_TILE_DROPS, false);
					rw.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
					rw.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
					rw.setGameRule(GameRule.DO_ENTITY_DROPS, false);
					rw.setGameRule(GameRule.DO_MOB_LOOT, false);
					rw.setGameRule(GameRule.DO_MOB_SPAWNING, false);
					rw.setGameRule(GameRule.DROWNING_DAMAGE, false);
					rw.setGameRule(GameRule.MOB_GRIEFING, false);
					rw.setGameRule(GameRule.SPAWN_RADIUS, 0);
				}
			},50);
		}
		return;
    }
	
	@EventHandler
	public void raidworldload(WorldLoadEvent ev) {
		if(ev.getWorld().getName().equals("OverworldRaid")) {
			if (Bukkit.getServer().getWorld("NethercoreRaid") == null) {
				WorldCreator rwc = new WorldCreator("NethercoreRaid");
				rwc.environment(Environment.NETHER);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
					@Override
					public void run() {
						rwc.generator(new NetherRaidChunkGenerator());
						World rw = rwc.createWorld();
						rw.setMetadata("rpgraidworld", new FixedMetadataValue(RMain.getInstance(),true));
						rwc.environment(Environment.NETHER);
						rw.setDifficulty(Difficulty.HARD);
						rw.setTime(12000);
						rw.setAutoSave(false);
						rw.setPVP(false);
						rw.setSpawnFlags(false, false);
						rw.setGameRule(GameRule.KEEP_INVENTORY, true);
						rw.setGameRule(GameRule.DO_INSOMNIA, false);
						rw.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
						rw.setGameRule(GameRule.DISABLE_RAIDS, true);
						rw.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
						rw.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
						rw.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
						rw.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
						rw.setGameRule(GameRule.DO_TILE_DROPS, false);
						rw.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
						rw.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
						rw.setGameRule(GameRule.DO_ENTITY_DROPS, false);
						rw.setGameRule(GameRule.DO_MOB_LOOT, false);
						rw.setGameRule(GameRule.DO_MOB_SPAWNING, false);
						rw.setGameRule(GameRule.DROWNING_DAMAGE, false);
						rw.setGameRule(GameRule.MOB_GRIEFING, false);
						rw.setGameRule(GameRule.SPAWN_RADIUS, 0);
					}
				}, 50);
			}
		}
	}
	
	
	@EventHandler
	public void raidworldunload(PluginDisableEvent ev) {

		List<World> worlds = Bukkit.getServer().getWorlds();
		worlds.forEach(w -> {

			if (w.hasMetadata("rpgraidworld")) {
				w.getEntities().forEach(a -> {
					if(a instanceof Player) {
						Player p = (Player) a;
						p.kickPlayer("");
					}
					else {
						a.remove();
					}
				});
				Bukkit.unloadWorld(w, false);
			}
		});
	}

}
