package io.github.chw3021.classes.forger;



import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class ForSkillsGui{
	


	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID,stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void ForSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		Inventory Forskillsinv = Bukkit.createInventory(null, 54, "Forskills");
		Obtained.itemset(p, Forskillsinv);
		
		ForSkillsData fsd = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("TNT�߻��", Material.WOODEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","�չٲٱ�","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.35*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.0312)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 0, Forskillsinv);
			itemset("���ڱ������", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.RailSMG.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","��Ŭ��","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.073*(1+fsd.RailSMG.getOrDefault(p.getUniqueId(),0)*0.012)).setScale(2, RoundingMode.HALF_EVEN) +"D","Master LV.50"), 1, Forskillsinv);
			itemset("�����", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.DoubleBarrel.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","�������� + ��ũ����","",ChatColor.BOLD+"2 X 0.135D", "Master LV.1"), 2, Forskillsinv);
			itemset("õ����", Material.IRON_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + ��Ŭ��","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.9*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, Forskillsinv);
			itemset("�����̻���", Material.GOLDEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","��Ŭ�� + ����", "������ ���� �޴����ذ� 20ȸ���� �����մϴ�", "20ȸ�� �ǰ� �Ǵ� 15�ʰ� ������ �����մϴ�","",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.085*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D",ChatColor.BOLD+"X"+BigDecimal.valueOf(0.53*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.042)*(Proficiency.getpro(p)>=1 ? 2:1)).setScale(4, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, Forskillsinv);
			itemset("�����", Material.DIAMOND_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.MachineGun.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[���� �迭]","�չٲٱ� + ��ũ����","���콺 ��ũ�ѷ� �߻�ü�� �ٲܼ��ֽ��ϴ�","ȭ��: ����, �����巡�� ���ݰ���","ź��: ���� ���ط�, ���� ���� ����","",
					ChatColor.BOLD+"ź��: X "+BigDecimal.valueOf(0.0658*(1+fsd.MachineGun.getOrDefault(p.getUniqueId(),0)*0.064)).setScale(2, RoundingMode.HALF_EVEN)+"D"+","
				+ " ȭ��: "+BigDecimal.valueOf(0.0013*(1+fsd.MachineGun.getOrDefault(p.getUniqueId(),0)*0.0125)).setScale(4, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 5, Forskillsinv);
			itemset("����", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Development.getOrDefault(p.getUniqueId(),0),"","���ݷ��� �����մϴ�","����� �������ð��� �����մϴ�","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1+fsd.Development.getOrDefault(p.getUniqueId(),0)*0.057).setScale(2, RoundingMode.HALF_EVEN)), 7, Forskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("�������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Forskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Forskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Forskillsinv);
				itemset("�б�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Forskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Forskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Forskillsinv);
				itemset("÷�ܱ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Forskillsinv);
				itemset("���Ǽ���߻��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Forskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("�������", Material.PISTON, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"[���� �迭]","���Է½� ������⸦ �߻��� ���� �ö󰩴ϴ�", "(���ط��� TNT�߻�� ������ ����մϴ�)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Forskillsinv);
				itemset("����", Material.CYAN_DYE, 0, 1, Arrays.asList("���� Ƚ���� �����ϰ� ���ط��� ����մϴ�","",ChatColor.BOLD+"3 X"+0.45*(1+fsd.RailSMG.getOrDefault(p.getUniqueId(),0)*0.05)+"D"), 10, Forskillsinv);
				itemset("���", Material.CREEPER_HEAD, 0, 1, Arrays.asList("���� ������ŵ�ϴ�"), 11, Forskillsinv);
				itemset("�б���", Material.TINTED_GLASS, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"[���� �迭]","���Է½� �б��⸦ �����", "������ ��ġ�� �������մϴ�", "(���ط��� õ���� ������ ����մϴ�)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Forskillsinv);
				itemset("����", Material.FIREWORK_STAR, 0, 1, Arrays.asList("���Է½� �̻��ϵ��� ��� ���߽�ŵ�ϴ�","���� ���ط��� �����մϴ�","������ �̿��ؼ� ���߽�", "������ ���ظ� �����ϴ�","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.1*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.0789)*(Proficiency.getpro(p))).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Forskillsinv);
				itemset("����", Material.REDSTONE_ORE, 0, 1, Arrays.asList("���ط��� ������� ���� ����մϴ�", "(�ִ� �ι�)"), 14, Forskillsinv);
				itemset("÷�ܱ��", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("��ü ���ط��� ����մϴ�","��� ��ų���� ����,���� ���ظ� �߰��� �����ϴ�"), 16, Forskillsinv);
				itemset("���Ǽ���߻��", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+"30 X 1.05D"), 17, Forskillsinv);
				
				itemset("�ö����ź(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Forskillsinv);
				itemset("���ڳ���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, Forskillsinv);
				itemset("����Į���߻��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Forskillsinv);
				itemset("�������溮(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 23, Forskillsinv);
				itemset("â��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Forskillsinv);
				itemset("��ȥ���ܱ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Forskillsinv);
			}
			else {
				itemset("�������", Material.PISTON, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"[���� �迭]","���Է½� ������⸦ �߻��� ���� �ö󰩴ϴ�", "(���ط��� TNT�߻�� ������ ����մϴ�)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Forskillsinv);
				itemset("����", Material.CYAN_DYE, 0, 1, Arrays.asList("���� Ƚ���� �����ϰ� ���ط��� ����մϴ�","",ChatColor.BOLD+"3 X"+0.45*(1+fsd.RailSMG.getOrDefault(p.getUniqueId(),0)*0.05)+"D"), 10, Forskillsinv);
				itemset("���", Material.CREEPER_HEAD, 0, 1, Arrays.asList("���� ������ŵ�ϴ�"), 11, Forskillsinv);
				itemset("�б���", Material.TINTED_GLASS, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"[���� �迭]","���Է½� �б��⸦ �����", "������ ��ġ�� �������մϴ�", "(���ط��� õ���� ������ ����մϴ�)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Forskillsinv);
				itemset("����", Material.FIREWORK_STAR, 0, 1, Arrays.asList("���Է½� �̻��ϵ��� ��� ���߽�ŵ�ϴ�","���� ���ط��� �����մϴ�","������ �̿��ؼ� ���߽�", "������ ���ظ� �����ϴ�","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.1*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.0789)*(Proficiency.getpro(p))).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Forskillsinv);
				itemset("����", Material.REDSTONE_ORE, 0, 1, Arrays.asList("���ط��� ������� ���� ����մϴ�", "(�ִ� �ι�)"), 14, Forskillsinv);
				itemset("÷�ܱ��", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("��ü ���ط��� ����մϴ�","��� ��ų���� ����,���� ���ظ� �߰��� �����ϴ�"), 16, Forskillsinv);
				itemset("���Ǽ���߻��", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+"30 X 1.05D"), 17, Forskillsinv);
				
				itemset("�ö����ź", Material.CYAN_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"[���� �迭]","���Է½� �ö����ź�� �߻��մϴ�", "(���ط��� TNT�߻�� ������ ����մϴ�)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.1)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, Forskillsinv);
				itemset("���ڳ���", Material.WARPED_ROOTS, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"[���� �迭]","���Է½� ���ڳ����� ����մϴ�", "(���ط��� ���ڱ������ ������ ����մϴ�)","",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.32*(1+fsd.RailSMG.getOrDefault(p.getUniqueId(),0)*0.092)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Forskillsinv);
				itemset("����Į���߻��", Material.SOUL_TORCH, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"[���� �迭]","���Է½� ����Į���߻�⸦ ����մϴ�", "(���ط��� õ���� ������ ����մϴ�)","",ChatColor.BOLD+"45 X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, Forskillsinv);
				itemset("�������溮", Material.CYAN_STAINED_GLASS, 0, 1, Arrays.asList("����� ���濡�� �޴� ���ظ� 65% ���ҽ�ŵ�ϴ�"), 23, Forskillsinv);
				itemset("â��", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","���Ǽ���߻�� ������ð��� �����մϴ�"), 25, Forskillsinv);
				itemset("��ȥ���ܱ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("�޸��� + �����۴�����", ChatColor.UNDERLINE+"[���� �迭]","",ChatColor.BOLD+"X 26.5D"), 26, Forskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Forskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Forskillsinv);
		
		}
		else {
			itemset("TNTLauncher", Material.WOODEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","SwapHand","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.35*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.0312)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 0, Forskillsinv);
			itemset("RailSMG", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.RailSMG.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Lightning]","Rightclick","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.073*(1+fsd.RailSMG.getOrDefault(p.getUniqueId(),0)*0.012)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 1, Forskillsinv);
			itemset("Shockwave", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.DoubleBarrel.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Lightning]","Hit + Sneaking","",ChatColor.BOLD+"2 X 0.135", "Master LV.1"), 2, Forskillsinv);
			itemset("LightningCannon", Material.IRON_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Lightning]","Sneaking + Rightclick","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.9*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, Forskillsinv);
			itemset("HoneyMissile", Material.GOLDEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Lightning]","LeftClick + Jump", "Stick to hit enemies","Give additional damage for 20 hits", "Explode after 20 Hits or 15secs",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.085*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D",ChatColor.BOLD+"X"+BigDecimal.valueOf(0.53*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.042)*(Proficiency.getpro(p)>=1 ? 2:1)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, Forskillsinv);
			itemset("MachineGun", Material.DIAMOND_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.MachineGun.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Earth]","SwapHand + Sneaking","Can Change Projectile Using Mouse Scroll","Arrow: Piercing, Able to Hit EnderDragon","Bullet: More Damage, Able to Hit Wither","",ChatColor.BOLD+
					"Bullet: X "+BigDecimal.valueOf(0.0658*(1+fsd.MachineGun.getOrDefault(p.getUniqueId(),0)*0.064)).setScale(2, RoundingMode.HALF_EVEN)+"D"+
					", Arrow: "+BigDecimal.valueOf(0.0013*(1+fsd.MachineGun.getOrDefault(p.getUniqueId(),0)*0.0125)).setScale(4, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 5, Forskillsinv);
			itemset("Development", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Development.getOrDefault(p.getUniqueId(),0),"","Increases Weapon damage","Reduce MachineGun Reloading Duration","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1+fsd.Development.getOrDefault(p.getUniqueId(),0)*0.057).setScale(2, RoundingMode.HALF_EVEN)), 7, Forskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Compressor(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Forskillsinv);
				itemset("Condensation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Forskillsinv);
				itemset("Impulse(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Forskillsinv);
				itemset("Spectral(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Forskillsinv);
				itemset("Detonator(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Forskillsinv);
				itemset("OverHeat(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Forskillsinv);
				itemset("High Tech(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Forskillsinv);
				itemset("DragonBreather(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Forskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Compressor", Material.PISTON, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Compressor When Use Once More", "(Damage Affected By TNTLauncher)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Forskillsinv);
				itemset("Condensation", Material.CYAN_DYE, 0, 1, Arrays.asList("Increases Damage & Decrease Hit Counts","",ChatColor.BOLD+"3 X"+0.45*(1+fsd.RailSMG.getOrDefault(p.getUniqueId(),0)*0.05)+"D"), 10, Forskillsinv);
				itemset("Impulse", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Stuns Hit Enemies"), 11, Forskillsinv);
				itemset("Spectral", Material.TINTED_GLASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Use Spectral When Use Once More", "(Damage Affected By LightningCannon)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Forskillsinv);
				itemset("Detonator", Material.FIREWORK_STAR, 0, 1, Arrays.asList("Explode Instantly When Use Once More","Increases Explode Damage","Higher Explode Damage By Detonator","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.1*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.0789)*(Proficiency.getpro(p))).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Forskillsinv);
				itemset("OverHeat", Material.REDSTONE_ORE, 0, 1, Arrays.asList("Damage & FireRate Increases When You Hold", "(Max X2)"), 14, Forskillsinv);
				itemset("High Tech", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","Add Lightning, Earth Power to All skills"), 16, Forskillsinv);
				itemset("DragonBreather", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem","",ChatColor.BOLD+"30 X 1.05D"), 17, Forskillsinv);
				
				itemset("PlazmaGrenade(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Forskillsinv);
				itemset("RailScrew(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Forskillsinv);
				itemset("BeamSlash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Forskillsinv);
				itemset("EnergyBarrier(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Forskillsinv);
				itemset("Creation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Forskillsinv);
				itemset("SoulDivider(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Forskillsinv);
			}
			else {
				itemset("Compressor", Material.PISTON, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Use Compressor When Use Once More", "(Damage Affected By TNTLauncher)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Forskillsinv);
				itemset("Condensation", Material.CYAN_DYE, 0, 1, Arrays.asList("Increases Damage & Decrease Hit Counts","",ChatColor.BOLD+"3 X"+0.45*(1+fsd.RailSMG.getOrDefault(p.getUniqueId(),0)*0.05)+"D"), 10, Forskillsinv);
				itemset("Impulse", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Stuns Hit Enemies"), 11, Forskillsinv);
				itemset("Spectral", Material.TINTED_GLASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Use Spectral When Use Once More", "(Damage Affected By LightningCannon)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Forskillsinv);
				itemset("Detonator", Material.FIREWORK_STAR, 0, 1, Arrays.asList("Explode Instantly When Use Once More","Increases Explode Damage","Higher Explode Damage By Detonator","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.1*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.0789)*(Proficiency.getpro(p))).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Forskillsinv);
				itemset("OverHeat", Material.REDSTONE_ORE, 0, 1, Arrays.asList("Damage & FireRate Increases When You Hold", "(Max X2)"), 14, Forskillsinv);
				itemset("High Tech", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","Add Lightning, Earth Power to All skills"), 16, Forskillsinv);
				itemset("DragonBreather", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem","",ChatColor.BOLD+"30 X 1.05D"), 17, Forskillsinv);
				
				itemset("PlazmaGrenade", Material.CYAN_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Use PlazmaGrenade When Use Once More", "(Damage Affected By TNTLauncher)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.1)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, Forskillsinv);
				itemset("RailScrew", Material.WARPED_ROOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Use RailScrew When Use Once More", "(Damage Affected By RailSMG)","",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.32*(1+fsd.RailSMG.getOrDefault(p.getUniqueId(),0)*0.092)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Forskillsinv);
				itemset("BeamWave", Material.SOUL_TORCH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning]","Use BeamSlash When Use Once More", "(Damage Affected By LightningCannon)","",ChatColor.BOLD+"45 X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, Forskillsinv);
				itemset("EnergyBarrier", Material.CYAN_STAINED_GLASS, 0, 1, Arrays.asList("Reduce Damage 65% From Foward While Shooting"), 23, Forskillsinv);
				itemset("Creation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease DragonBreather Cooldown"), 25, Forskillsinv);
				itemset("SoulDivider", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Lightning, Earth]","Sprinting + ThrowItem","",ChatColor.BOLD+"X 26.5D"), 26, Forskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Forskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Forskillsinv);
		
		}
		
		
		p.openInventory(Forskillsinv);
	}


}
