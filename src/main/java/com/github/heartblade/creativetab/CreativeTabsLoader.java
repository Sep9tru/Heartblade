package com.github.heartblade.creativetab;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.stats.StatBase;


public class CreativeTabsLoader
{
    public static CreativeTabs tabFMLTutor;
    public static StatBase achievementDisc;

    public CreativeTabsLoader(FMLPreInitializationEvent event)
    {
        tabFMLTutor = new CreativeTabsFMLTutor();
    }
}