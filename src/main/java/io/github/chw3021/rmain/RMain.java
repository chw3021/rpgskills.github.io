package io.github.chw3021.rmain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.angler.AngSkillsData;
import io.github.chw3021.classes.archer.ArchSkillsData;
import io.github.chw3021.classes.berserker.BerSkillsData;
import io.github.chw3021.classes.boxer.BoxSkillsData;
import io.github.chw3021.classes.broiler.BroSkillsData;
import io.github.chw3021.classes.chemist.CheSkillsData;
import io.github.chw3021.classes.cook.CookSkillsData;
import io.github.chw3021.classes.engineer.EngSkillsData;
import io.github.chw3021.classes.firemage.FireSkillsData;
import io.github.chw3021.classes.forger.ForSkillsData;
import io.github.chw3021.classes.frostman.FrostSkillsData;
import io.github.chw3021.classes.hunter.HunSkillsData;
import io.github.chw3021.classes.illusionist.IllSkillsData;
import io.github.chw3021.classes.launcher.LaunSkillsData;
import io.github.chw3021.classes.medic.MedSkillsData;
import io.github.chw3021.classes.nobility.NobSkillsData;
import io.github.chw3021.classes.oceanknight.OceSkillsData;
import io.github.chw3021.classes.paladin.PalSkillsData;
import io.github.chw3021.classes.sniper.SnipSkillsData;
import io.github.chw3021.classes.swordman.SwordSkillsData;
import io.github.chw3021.classes.tamer.TamSkillsData;
import io.github.chw3021.classes.taoist.TaoSkillsData;
import io.github.chw3021.classes.witchdoctor.WdcSkillsData;
import io.github.chw3021.classes.witherist.WitSkillsData;
import io.github.chw3021.classes.wreltler.WreSkillsData;
import io.github.chw3021.commons.CommonEvents;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.commons.Rpgs;
import io.github.chw3021.commons.ShulkerBag;
import io.github.chw3021.commons.SkillUsing;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.items.Backpack;
import io.github.chw3021.items.Elements;
import io.github.chw3021.items.Enchanting;
import io.github.chw3021.items.NamingPrevent;
import io.github.chw3021.items.ScrollPoint;
import io.github.chw3021.items.armors.ArmorSet;
import io.github.chw3021.items.armors.ArmorSetEffects;
import io.github.chw3021.items.armors.Boots;
import io.github.chw3021.items.armors.Chestplate;
import io.github.chw3021.items.armors.Helmet;
import io.github.chw3021.items.armors.Leggings;
import io.github.chw3021.items.weapons.Bow;
import io.github.chw3021.items.weapons.CrossBow;
import io.github.chw3021.items.weapons.Fighter;
import io.github.chw3021.items.weapons.FishingRod;
import io.github.chw3021.items.weapons.Tridentnaming;
import io.github.chw3021.items.weapons.Wand;
import io.github.chw3021.monsters.worldgen.RaidWorldLoad;
import io.github.chw3021.monsters.Drops;
import io.github.chw3021.monsters.MobArmor;
import io.github.chw3021.monsters.MobsSkillsEvents;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.obtains.NPCLoc;
import io.github.chw3021.obtains.NPCcontact;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.obtains.TrophyLoc;

public class RMain extends JavaPlugin{

	private static RMain instance;
	
	@Override
    public void onEnable() {

    	getConfig().addDefault("Language", "ko_kr");
        getConfig().options().copyDefaults(true);
        getConfig().options().setHeader(Arrays.asList("English - en_us","ÇÑ±¹¾î - ko_kr"));
        saveConfig();
    	instance = this;
        System.out.println("Plugin is Activated");

        Bukkit.getWorlds().forEach(w -> {
        	if(w.getName().contains("Raid")) {
        		return;
        	}
        	w.setSpawnLimit(SpawnCategory.MONSTER, 300);
        	w.setSpawnLimit(SpawnCategory.WATER_UNDERGROUND_CREATURE, 20);
        	w.setTicksPerSpawns(SpawnCategory.AMBIENT, 1);
        	w.setTicksPerSpawns(SpawnCategory.AXOLOTL, 1);
        	w.setTicksPerSpawns(SpawnCategory.ANIMAL, 1);
        	w.setTicksPerSpawns(SpawnCategory.MONSTER, 1);
        	w.setTicksPerSpawns(SpawnCategory.WATER_AMBIENT, 1);
        	w.setTicksPerSpawns(SpawnCategory.WATER_ANIMAL, 1);
        	w.setTicksPerSpawns(SpawnCategory.WATER_UNDERGROUND_CREATURE, 1);
        });
        Bukkit.getPluginManager().registerEvents(new RaidWorldLoad(), this);
        
        this.getDataFolder().mkdir();
        Bukkit.getPluginManager().registerEvents(new ClassData(new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new Backpack(new HashMap<UUID, ItemStack[]>()), this);
        Bukkit.getPluginManager().registerEvents(new ScrollPoint(new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new Party(), this);
        this.getCommand("party").setExecutor(new Party());

        Bukkit.getPluginManager().registerEvents(new TrophyLoc(HashMultimap.create()), this);
        Bukkit.getPluginManager().registerEvents(new Obtained(new HashMap<UUID, Integer>(),new HashMap<UUID, Integer>(),new HashMap<UUID, Integer>(),new HashMap<UUID, Integer>(),new HashMap<UUID, Integer>(),new HashMap<UUID, Integer>(),new HashMap<UUID, Integer>(),new HashMap<UUID, Integer>(),new HashMap<UUID, Integer>(),new HashMap<UUID, Integer>(),new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        
        Bukkit.getPluginManager().registerEvents(new SwordSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new BerSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new HunSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new PalSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new LaunSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new MedSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new SnipSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new ArchSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new BoxSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new WreSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new TamSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new TaoSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new IllSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new FireSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new WitSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new WdcSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new CheSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new ForSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new EngSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new CookSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new OceSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new NobSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new FrostSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new AngSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);
        Bukkit.getPluginManager().registerEvents(new BroSkillsData(new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>(), new HashMap<UUID, Integer>()), this);

        Bukkit.getPluginManager().registerEvents(new Proficiency(HashBasedTable.create(), HashBasedTable.create()), this);

        Bukkit.getPluginManager().registerEvents(new Tridentnaming(), this);
        
        Bukkit.getPluginManager().registerEvents(new ArmorSetEffects(), this);

        Bukkit.getPluginManager().registerEvents(new NPCcontact(), this);

        Bukkit.getPluginManager().registerEvents(new SkillUsing(), this);
        Bukkit.getPluginManager().registerEvents(new ShulkerBag(), this);

        Bukkit.getPluginManager().registerEvents(new ArmorSet(), this);
        Bukkit.getPluginManager().registerEvents(new Enchanting(), this);
        Bukkit.getPluginManager().registerEvents(new NamingPrevent(), this);
        
        Bukkit.getPluginManager().registerEvents(new Elements(), this);
        Bukkit.getPluginManager().registerEvents(new Bow(), this);
        Bukkit.getPluginManager().registerEvents(new Fighter(), this);
        Bukkit.getPluginManager().registerEvents(new CrossBow(), this);
        Bukkit.getPluginManager().registerEvents(new Wand(), this);
        Bukkit.getPluginManager().registerEvents(new FishingRod(), this);
        
        Bukkit.getPluginManager().registerEvents(new Helmet(), this);
        Bukkit.getPluginManager().registerEvents(new Boots(), this);
        Bukkit.getPluginManager().registerEvents(new Chestplate(), this);
        Bukkit.getPluginManager().registerEvents(new Leggings(), this);

        
        Bukkit.getPluginManager().registerEvents(new Pak(), this);

        Bukkit.getPluginManager().registerEvents(new MobArmor(), this);

        
        Bukkit.getPluginManager().registerEvents(new MobsSkillsEvents(), this);

        
        
        this.getCommand("rpg").setExecutor(new Rpgs());
        this.getCommand("rpg").setExecutor(new Summoned());
        Bukkit.getPluginManager().registerEvents(new Drops(), this);
        Bukkit.getPluginManager().registerEvents(new CommonEvents(), this);
        Bukkit.getPluginManager().registerEvents(new Holding(), this);
        
        //Bukkit.getPluginManager().registerEvents(new NPCLoc(HashMultimap.create()), this);
    }
    
    public static RMain getInstance()
    {
    	return instance;
    }
    
    @Override
    public void onDisable() {
		List<World> worlds = Bukkit.getWorlds();

		worlds.forEach(w ->{
			w.getEntities().forEach(b -> {
				b.setCustomName(CommonEvents.damaged.get(b.getUniqueId()));
				if((b.hasMetadata("obnpc") || b.hasMetadata("rpgspawned") || b.hasMetadata("untargetable")|| b.hasMetadata("fake")) && !(b instanceof Player)) {
					b.setPersistent(false);
					if(b instanceof LivingEntity) {
						LivingEntity le = (LivingEntity) b;
						le.setRemoveWhenFarAway(true);
					}
					b.remove();
				}
			});
			}
		);
        System.out.println("Plugin is DisActivated");
    }
    

}



