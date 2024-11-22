package io.github.chw3021.commons;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.chw3021.commons.Pak.SkillUse;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class SkillBuilder {
    private SkillUse skillUse;
    private Player player;
    private double cooldown;
    private String kname;
    private String ename;
    private HashMap<String, Long> hm;
    private int slot=-1;

    /*
                SkillBuilder bd = new SkillBuilder()
                        .player(p)
                        .cooldown(sec)
                        .kname("")
                        .ename("")
                        .slot(2)
                        .hm(prcooldown)
                        .skillUse(() -> {
                        });
                bd.execute();
                */
    public SkillBuilder() {}

    public SkillBuilder skillUse(SkillUse skillUse) {
        this.skillUse = skillUse;
        return this;
    }

    public SkillBuilder player(Player player) {
        this.player = player;
        return this;
    }

    public SkillBuilder cooldown(double cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    public SkillBuilder kname(String kname) {
        this.kname = kname;
        return this;
    }

    public SkillBuilder ename(String ename) {
        this.ename = ename;
        return this;
    }

    public SkillBuilder hm(HashMap<String, Long> hm) {
        this.hm = hm;
        return this;
    }


    public SkillBuilder slot(int slot) {
        this.slot = slot;
        return this;
    }


    public void execute() {
        if(hm.containsKey(player.getName())) {
            double timer = (hm.get(player.getName())/1000d + cooldown) - System.currentTimeMillis()/1000d;
            if(!(timer < 0)) {
                if(slot >-1) {
                    Bukkit.getPluginManager().callEvent(new SkillUseEvent(player,timer,slot,kname,ename));
                }
                if(player.getLocale().equalsIgnoreCase("ko_kr")) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(kname+" 재사용 대기시간이 " + String.valueOf(Math.round(timer*10)/10.0) + "초 남았습니다").create());
                } else {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("You have to wait for " + String.valueOf(Math.round(timer*10)/10.0) + " seconds to use "+ename).create());
                }
            } else {
                if(slot >-1) {
                    SkillUseEvent event = new SkillUseEvent(player, cooldown, slot, kname, ename,hm);
                    Bukkit.getPluginManager().callEvent(event);
                    if (event.isCancelled()) return;
                }
                hm.remove(player.getName());
                skillUse.skilluse();
                hm.put(player.getName(), System.currentTimeMillis());
            }
        } else {
            if(slot >-1) {
                SkillUseEvent event = new SkillUseEvent(player, cooldown, slot, kname, ename,hm);
                Bukkit.getPluginManager().callEvent(event);
                if (event.isCancelled()) return;
            }
            skillUse.skilluse();
            hm.put(player.getName(), System.currentTimeMillis());
        }
    }
}