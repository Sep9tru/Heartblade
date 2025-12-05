package com.github.heartblade;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

public class HbConfig
{
    private static Configuration config;

    private static Logger logger;

    public static float dguoblade_baseAttackModifier;

    public static float iceblade_baseAttackModifier;

    public static float matongcblade_baseAttackModifier;

    public static float nobleblade_baseAttackModifier;

    public static float xiaochaoblade_baseAttackModifier;

    public static float xiaohuangOrArui_baseAttackModifier;

    public static float yunxingheblade_baseAttackModifier;

    public static float ziminblade_baseAttackModifier;

    public static float YamatoPower_baseAttackModifier;

    public HbConfig(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());

//实例化了一个Configuration类,括号中填的是Forge推荐的配置文件位置,这个位置在游戏根目录的config文件夹下，
//名为<modid>.cfg，这里就是bm.cfg。

        config.load();//读取配置
        load();
    }

    public static void load()
    {
        logger.info("Started loading config. ");
        String comment;

        comment = "自定义 魔剑「帝国」 的基础攻击伤害。";
        dguoblade_baseAttackModifier = config.get(Configuration.CATEGORY_GENERAL, "dguoblade_base_atk", 20, comment).getInt();

        comment = "自定义 聚魂大剑「寒冰」 的基础攻击伤害。";
        iceblade_baseAttackModifier = config.get(Configuration.CATEGORY_GENERAL, "iceblade_base_atk", 30, comment).getInt();

        comment = "自定义 惊涛宝剑「閁统」 的基础攻击伤害。";
        matongcblade_baseAttackModifier = config.get(Configuration.CATEGORY_GENERAL, "matongcblade_base_atk", 34, comment).getInt();

        comment = "自定义 卓越刀 的基础攻击伤害。";
        nobleblade_baseAttackModifier = config.get(Configuration.CATEGORY_GENERAL, "nobleblade_base_atk", 1, comment).getInt();

        comment = "自定义 圣晓剑「超越」 的基础攻击伤害。";
        xiaochaoblade_baseAttackModifier = config.get(Configuration.CATEGORY_GENERAL, "xiaochaoblade_base_atk", 49, comment).getInt();

        comment = "自定义 日刀「晓煌」 的基础攻击伤害。";
        xiaohuangOrArui_baseAttackModifier = config.get(Configuration.CATEGORY_GENERAL, "xiaohuangorarui_base_atk", 34, comment).getInt();

        comment = "自定义 云刀「星河」 的基础攻击伤害。";
        yunxingheblade_baseAttackModifier = config.get(Configuration.CATEGORY_GENERAL, "yunxingheblade_base_atk", 32, comment).getInt();

        comment = "自定义 冥刀「自然之心」 的基础攻击伤害。";
        ziminblade_baseAttackModifier = config.get(Configuration.CATEGORY_GENERAL, "ziminblade_base_atk", 32, comment).getInt();

        comment = "自定义 魔剑「阎魔刀」(该附属派生) 的基础攻击伤害。";
        YamatoPower_baseAttackModifier = config.get(Configuration.CATEGORY_GENERAL, "yamatopower_base_atk", 15, comment).getInt();

        //forge配置文件中会有多个类别，forge提供了general(Configuration.CATEGORY_GENERAL)
        //get函数的第一个参数就是表示general类型
        //get的第二个参数就是配置文件中的键的名称(难以看懂)
        //get的第三个参数就是键的默认值(默认640),如果该键不存在，返回默认值
        //get的第四个参数是该键的注释，就是获取diamondBurnTime相对应的值,getInt函数的作用就是获取整数（配置文件里面键的值一定是字符串）
        //从这里阔以看出get就是为了获取diamondBurnTime的值



        config.save();//保存配置
        //至于为什么要保存配置呢？这是因为当配置缺失（最常见的原因就是配置文件没有创建，
        //这常常发生在你第一次使用Mod的时候）的时候，这一句会将默认的配置保存下来。
        logger.info("Finished loading config. ");//输出完成加载配置文件
    }

    public static Logger logger()
    {
        return logger;
    }
}