package io.github.chw3021.rmain;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.chw3021.angler.AngSkillsData;
import io.github.chw3021.angler.Angskills;
import io.github.chw3021.archer.ArchSkillsData;
import io.github.chw3021.archer.Archskills;
import io.github.chw3021.berserker.BerSkillsData;
import io.github.chw3021.berserker.Berskills;
import io.github.chw3021.boxer.BoxSkillsData;
import io.github.chw3021.boxer.Boxskills;
import io.github.chw3021.chemist.CheSkillsData;
import io.github.chw3021.chemist.Cheskills;
import io.github.chw3021.classes.ClassData;
import io.github.chw3021.commons.Event;
import io.github.chw3021.commons.Rpgs;
import io.github.chw3021.cook.CookSkillsData;
import io.github.chw3021.cook.Cookskills;
import io.github.chw3021.engineer.EngSkillsData;
import io.github.chw3021.engineer.Engskills;
import io.github.chw3021.firemage.FireSkillsData;
import io.github.chw3021.firemage.Fireskills;
import io.github.chw3021.forger.ForSkillsData;
import io.github.chw3021.forger.Forskills;
import io.github.chw3021.frostman.FrostSkillsData;
import io.github.chw3021.frostman.Frostskills;
import io.github.chw3021.hunter.HunSkillsData;
import io.github.chw3021.hunter.Hunskills;
import io.github.chw3021.illusionist.IllSkillsData;
import io.github.chw3021.illusionist.Illskills;
import io.github.chw3021.launcher.LaunSkillsData;
import io.github.chw3021.launcher.Launskills;
import io.github.chw3021.medic.MedSkillsData;
import io.github.chw3021.medic.Medskills;
import io.github.chw3021.monsters.RedBoss;
import io.github.chw3021.monsters.SnowBoss;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.nobility.NobSkillsData;
import io.github.chw3021.nobility.Nobskills;
import io.github.chw3021.oceanknight.OceSkillsData;
import io.github.chw3021.oceanknight.Oceskills;
import io.github.chw3021.paladin.PalSkillsData;
import io.github.chw3021.paladin.Palskills;
import io.github.chw3021.party.PartyData;
import io.github.chw3021.party.PartyEvent;
import io.github.chw3021.party.Partycom;
import io.github.chw3021.sniper.SnipSkillsData;
import io.github.chw3021.sniper.Snipskills;
import io.github.chw3021.swordman.SwordSkillsData;
import io.github.chw3021.swordman.Swordskills;
import io.github.chw3021.tamer.TamSkillsData;
import io.github.chw3021.tamer.Tamskills;
import io.github.chw3021.taoist.TaoSkillsData;
import io.github.chw3021.taoist.Taoskills;
import io.github.chw3021.witchdoctor.WdcSkillsData;
import io.github.chw3021.witchdoctor.Wdcskills;
import io.github.chw3021.witherist.WitSkillsData;
import io.github.chw3021.witherist.Witskills;
import io.github.chw3021.wreltler.WreSkillsData;
import io.github.chw3021.wreltler.Wreskills;

public class RMain extends JavaPlugin{

	private static RMain instance;

    @Override
    public void onEnable() {
    	instance = this;
        System.out.println("Plugin is Activated");
        this.getDataFolder().mkdir();
        getServer().getPluginManager().registerEvents(new ClassData(new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new PartyData(new HashMap<UUID, String>(), new HashMap<UUID, String>()), this);
        this.getCommand("party").setExecutor(new Partycom());
        getServer().getPluginManager().registerEvents(new SwordSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new BerSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new HunSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new PalSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new LaunSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new MedSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new SnipSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new ArchSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new BoxSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new WreSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new TamSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new TaoSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new IllSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new FireSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new WitSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new WdcSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new CheSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new ForSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new EngSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new CookSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new OceSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new NobSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new FrostSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        getServer().getPluginManager().registerEvents(new AngSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);

        getServer().getPluginManager().registerEvents(new PartyEvent(), this);
        getServer().getPluginManager().registerEvents(new Wdcskills(), this);
        getServer().getPluginManager().registerEvents(new Medskills(), this);
        getServer().getPluginManager().registerEvents(new Cookskills(), this);
        getServer().getPluginManager().registerEvents(new Swordskills(), this);
        getServer().getPluginManager().registerEvents(new Hunskills(), this);
        getServer().getPluginManager().registerEvents(new Berskills(), this);
        getServer().getPluginManager().registerEvents(new Launskills(), this);
        getServer().getPluginManager().registerEvents(new Palskills(), this);
        getServer().getPluginManager().registerEvents(new Archskills(), this);
        getServer().getPluginManager().registerEvents(new Angskills(), this);
        getServer().getPluginManager().registerEvents(new Snipskills(), this);
        getServer().getPluginManager().registerEvents(new Boxskills(), this);
        getServer().getPluginManager().registerEvents(new Wreskills(), this);
        getServer().getPluginManager().registerEvents(new Tamskills(), this);
        getServer().getPluginManager().registerEvents(new Taoskills(), this);
        getServer().getPluginManager().registerEvents(new Illskills(), this);
        getServer().getPluginManager().registerEvents(new Fireskills(), this);
        getServer().getPluginManager().registerEvents(new Witskills(), this);
        getServer().getPluginManager().registerEvents(new Cheskills(), this);
        getServer().getPluginManager().registerEvents(new Forskills(), this);
        getServer().getPluginManager().registerEvents(new Engskills(), this);
        getServer().getPluginManager().registerEvents(new Oceskills(), this);
        getServer().getPluginManager().registerEvents(new Nobskills(), this);
        getServer().getPluginManager().registerEvents(new Frostskills(), this);
        getServer().getPluginManager().registerEvents(new Mobs(), this);
        getServer().getPluginManager().registerEvents(new RedBoss(), this);
        getServer().getPluginManager().registerEvents(new SnowBoss(), this);
        this.getCommand("rpg").setExecutor(new Rpgs());
        getServer().getPluginManager().registerEvents(new Event(), this);
    }
    
    public static RMain getInstance()
    {
    	return instance;
    }
    
    @Override
    public void onDisable() {
        System.out.println("Plugin is DisActivated");
    }
    

}

