package com.github.heartblade.item;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModRecord {
    public static ItemOursRecord oursRecord;
    public static ItemOrangeRecord orangeRecord;
    public static ItemDustyRecord dustyRecord;
    public static ItemIcedRecord icedRecord;
    public static ItemCorruptRecord corruptRecord;
    public static ItemNullRecord nullRecord;
    public static ItemPRecord pRecord;
    public static ItemPRecord2 pRecord2;
    public static ItemChaosRecord chaosRecord;
    public static ItemOldRecord oldRecord;
    public static ItemOldRecord2 oldRecord2;
    public ModRecord() {
    }

    public static void init() {
        oursRecord = (ItemOursRecord)register(new ItemOursRecord("SweetTrack"), "record_SweetTrack");
        orangeRecord = (ItemOrangeRecord)register(new ItemOrangeRecord("Letter That Writing in the Wind"),"record_LetterThatWritingintheWind");
        dustyRecord = (ItemDustyRecord)register(new ItemDustyRecord("Please Dont Go"),"record_PleaseDontGo");
        icedRecord = (ItemIcedRecord)register(new ItemIcedRecord("Ice"),"record_AsuenoKizuna");
        corruptRecord = (ItemCorruptRecord)register(new ItemCorruptRecord("Corrupt"),"record_LongShotParty");
        nullRecord = (ItemNullRecord)register(new ItemNullRecord("Shine (Original Mix)"),"record_Shine");
        pRecord = (ItemPRecord)register(new ItemPRecord("Take a Walk [A Cappella]"),"record_TakeAWalk");
        pRecord2 = (ItemPRecord2)register(new ItemPRecord2("Feathers"),"record_Feathers");
        chaosRecord = (ItemChaosRecord)register(new ItemChaosRecord("Hopes And Dreams"),"record_HopesAndDreams");
        oldRecord = (ItemOldRecord)register(new ItemOldRecord("Old"),"record_SingRSing");
        oldRecord2 = (ItemOldRecord2)register(new ItemOldRecord2("Old2"),"record_FKH");
    }

    private static <T extends Item> T register(T item, String name) {
        GameRegistry.registerItem(item, name);
        if (item instanceof ItemOreDict) {
            ((ItemOreDict)item).registerOreDict();
        }

        return item;
    }
}
