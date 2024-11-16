package com.github.heartblade.blade;

import com.github.heartblade.specialeffect.VOID;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class vanity {
    String name = "flammpfeil.slashblade.named.vanity";
    public static ISpecialEffect VOID = SpecialEffects.register(new VOID());

    public vanity() {
    }

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 40);
        ItemSlashBlade.setBaseAttackModifier(tag, 1000.0F);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.SpecialAttackType.set(tag, 1);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/dark");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/golden");
        ItemSlashBlade.SummonedSwordColor.set(tag, 0);
        ItemSlashBlade.KillCount.set(tag, 6666);
        ItemSlashBlade.ProudSoul.set(tag, 131313);
        customblade.addEnchantment(Enchantment.power, 13);
        customblade.getTagCompound().setBoolean("Unbreakable", true);
        NBTTagCompound displayTag = new NBTTagCompound();
        SpecialEffects.addEffect(customblade, VOID);
        customblade.setTagInfo("display", displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString("§4十八星淬炼保护"));
        loreList.appendTag(new NBTTagString("§c§m§l  §6§m§l  §e§m§l  §a§m§l  §b§m§l  §e§l宝石镶嵌§b§m§l  §a§m§l  §e§m§l  §6§m§l  §c§m§l  "));
        loreList.appendTag(new NBTTagString("§3§2§1§b§l五级青鳞石§7  -  §a附加伤害+§6100"));
        loreList.appendTag(new NBTTagString("§8§m§l=====§8§l【 §4§l终极品质 §8§l】§m====="));
        loreList.appendTag(new NBTTagString("§e§l稀有度：§4荣耀"));
        loreList.appendTag(new NBTTagString("§8§l吾即此体系的顶端"));
        loreList.appendTag(new NBTTagString("§8§l去挥舞吾吧，尽情屠杀那群蝼蚁"));
        loreList.appendTag(new NBTTagString("§8§l吾将与你共同坐上王座"));
        loreList.appendTag(new NBTTagString("§8§l俯视这个天下"));
        loreList.appendTag(new NBTTagString("§8§l吾将为你带来威望与财富"));
        loreList.appendTag(new NBTTagString("§8§l让所有人都望尘莫及"));
        loreList.appendTag(new NBTTagString("§8§l但，请记住"));
        loreList.appendTag(new NBTTagString("§8§l吾就是你的§4§l一切..."));
        loreList.appendTag(new NBTTagString("§8§m§l=====§8§l【 §e§l装备属性 §8§l】§m====="));
        loreList.appendTag(new NBTTagString("§b§l+500 §d§l基础伤害"));
        loreList.appendTag(new NBTTagString("§8§m§l=====§8§l【 §e§l完美附加 §8§l】§m====="));
        loreList.appendTag(new NBTTagString("§4§l狂暴几率§f§l：§a100%"));
        loreList.appendTag(new NBTTagString("§c§l狂暴伤害§f§l：§a131313x"));
        loreList.appendTag(new NBTTagString("§4§l❂❂❂十八星淬炼"));
        loreList.appendTag(new NBTTagString("§e§l淬炼属性:§a附加伤害+400"));
        displayTag.setTag("Lore", loreList);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
    }
}
