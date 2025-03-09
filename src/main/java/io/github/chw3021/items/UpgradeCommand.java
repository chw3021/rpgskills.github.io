package io.github.chw3021.items;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import io.github.chw3021.items.weapons.Weapons;

public class UpgradeCommand extends Weapons implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "플레이어만 사용 가능합니다.");
            return false;
        }

        Player p = (Player) sender;
        PlayerInventory inventory = p.getInventory();

        ItemStack firstItem = inventory.getItem(0);
        ItemStack secondItem = inventory.getItem(1);

        if (firstItem == null || secondItem == null) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            p.sendMessage(ChatColor.RED + "강화할 아이템과 재료를 첫 번째, 두 번째 슬롯에 배치하세요.");
			}
			else {
	            p.sendMessage(ChatColor.RED + "Place Equipment and Ingredient on first, second slot");
			}
            return false;
        }

        // 기존 강화 메서드(csc) 실행
        ItemStack upgradedItem = csc(inventory, p);
        ItemStack upgradedItem2 = nsc(inventory, p);
        ItemStack upgradedItem3 = esc(inventory, p);
        ItemStack upgradedItem4 = hsc(inventory, p);

        if (upgradedItem != null) {
        	Elements.give(upgradedItem, 1, p);
            firstItem.setAmount(firstItem.getAmount() - 1);
            secondItem.setAmount(secondItem.getAmount() - 1);
        }
        else if (upgradedItem2 != null) {
        	Elements.give(upgradedItem2, 1, p);
            firstItem.setAmount(firstItem.getAmount() - 1);
            secondItem.setAmount(secondItem.getAmount() - 1);
        }
        else if (upgradedItem3 != null) {
        	Elements.give(upgradedItem3, 1, p);
            firstItem.setAmount(firstItem.getAmount() - 1);
            secondItem.setAmount(secondItem.getAmount() - 1);
        }
        else if (upgradedItem4 != null) {
        	Elements.give(upgradedItem4, 1, p);
            firstItem.setAmount(firstItem.getAmount() - 1);
            secondItem.setAmount(secondItem.getAmount() - 1);
        } else {
        }

        return true;
    }


	
}
