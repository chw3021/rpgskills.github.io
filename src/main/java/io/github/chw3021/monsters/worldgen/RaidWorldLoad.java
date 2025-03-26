package io.github.chw3021.monsters.worldgen;

import java.io.File;
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

    private boolean worldExists(String worldName) {
        // 서버의 월드 컨테이너에서 해당 월드 폴더가 존재하는지 확인
        File worldFolder = new File(Bukkit.getWorldContainer(), worldName);
        return worldFolder.exists() && worldFolder.isDirectory();
    }
    @EventHandler
    public void raidWorldLoad(PluginEnableEvent ev) {
        if (Bukkit.getServer().getWorld("OverworldRaid") == null) {
            if (worldExists("OverworldRaid")) {
                // 월드가 존재하면 불러오기 (청크 생성기 강제 적용)
                World rw = Bukkit.getServer().createWorld(new WorldCreator("OverworldRaid").environment(Environment.NORMAL).generator(new OverworldRaidChunkGenerator()));
                configureRaidWorld(rw, Environment.NORMAL); // 월드 설정
            } else {
                // 월드가 존재하지 않으면 새로 생성
                WorldCreator rwc = new WorldCreator("OverworldRaid");
                rwc.environment(Environment.NORMAL);
                rwc.generator(new OverworldRaidChunkGenerator());

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), () -> {
                    World rw = rwc.createWorld();
                    configureRaidWorld(rw, Environment.NORMAL);
                }, 50);
            }
        }
    }

    @EventHandler
    public void netherRaidWorldLoad(WorldLoadEvent ev) {
        if (ev.getWorld().getName().equals("OverworldRaid")) {
            if (Bukkit.getServer().getWorld("NethercoreRaid") == null) {
                if (worldExists("NethercoreRaid")) {
                    // 월드가 존재하면 불러오기 (청크 생성기 강제 적용)
                    World rw = Bukkit.getServer().createWorld(new WorldCreator("NethercoreRaid").environment(Environment.NETHER).generator(new NetherRaidChunkGenerator()));
                    configureRaidWorld(rw, Environment.NETHER);
                } else {
                    // 월드가 존재하지 않으면 새로 생성
                    WorldCreator rwc = new WorldCreator("NethercoreRaid");
                    rwc.environment(Environment.NETHER);
                    rwc.generator(new NetherRaidChunkGenerator());

                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), () -> {
                        World rw = rwc.createWorld();
                        configureRaidWorld(rw, Environment.NETHER);
                    }, 50);
                }
            }
        }
    }

    @EventHandler
    public void enderRaidWorldLoad(WorldLoadEvent ev) {
        if (ev.getWorld().getName().equals("NethercoreRaid")) {
            if (Bukkit.getServer().getWorld("EndercoreRaid") == null) {
                if (worldExists("EndercoreRaid")) {
                    // 월드가 존재하면 불러오기 (청크 생성기 강제 적용)
                    World rw = Bukkit.getServer().createWorld(new WorldCreator("EndercoreRaid").environment(Environment.THE_END).generator(new EnderRaidChunkGenerator()));
                    configureRaidWorld(rw, Environment.THE_END);
                } else {
                    // 월드가 존재하지 않으면 새로 생성
                    WorldCreator rwc = new WorldCreator("EndercoreRaid");
                    rwc.environment(Environment.THE_END);
                    rwc.generator(new EnderRaidChunkGenerator());

                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), () -> {
                        World rw = rwc.createWorld();
                        configureRaidWorld(rw, Environment.THE_END);
                    }, 50);
                }
            }
        }
    }

    @EventHandler
    public void witherRaidWorldLoad(WorldLoadEvent ev) {
        if (ev.getWorld().getName().equals("EndercoreRaid")) {
            if (Bukkit.getServer().getWorld("WitherRaid") == null) {
                if (worldExists("WitherRaid")) {
                    // 월드가 존재하면 불러오기 (청크 생성기 강제 적용)
                    World rw = Bukkit.getServer().createWorld(new WorldCreator("WitherRaid").environment(Environment.NETHER).generator(new WitherRaidChunkGenerator()));
                    configureRaidWorld(rw, Environment.NETHER);
                } else {
                    // 월드가 존재하지 않으면 새로 생성
                    WorldCreator rwc = new WorldCreator("WitherRaid");
                    rwc.environment(Environment.NETHER);
                    rwc.generator(new WitherRaidChunkGenerator());

                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), () -> {
                        World rw = rwc.createWorld();
                        configureRaidWorld(rw, Environment.NETHER);
                    }, 50);
                }
            }
        }
    }
    
    private void configureRaidWorld(World world, Environment environment) {
        world.setMetadata("rpgraidworld", new FixedMetadataValue(RMain.getInstance(), true));
        world.setDifficulty(Difficulty.HARD);
        world.setTime(12000);
        world.setPVP(false);
        world.setSpawnFlags(false, false);
        world.setAutoSave(false);

        // 게임 규칙 설정
        world.setGameRule(GameRule.KEEP_INVENTORY, true);
        world.setGameRule(GameRule.DO_INSOMNIA, false);
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        world.setGameRule(GameRule.DISABLE_RAIDS, true);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        world.setGameRule(GameRule.DO_TILE_DROPS, false);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
        world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
        world.setGameRule(GameRule.DO_MOB_LOOT, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DROWNING_DAMAGE, false);
        world.setGameRule(GameRule.MOB_GRIEFING, true);
        world.setGameRule(GameRule.SPAWN_RADIUS, 0);
        world.setGameRule(GameRule.DO_FIRE_TICK, false);
        world.setGameRule(GameRule.DISABLE_PLAYER_MOVEMENT_CHECK, true);
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
				//Bukkit.unloadWorld(w, false);
			}
		});
	}

}
