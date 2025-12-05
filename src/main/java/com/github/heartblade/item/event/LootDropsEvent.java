package com.github.heartblade.item.event;

import com.github.heartblade.item.ItemLoader;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootDropsEvent {

    public LootDropsEvent(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPassiveDrop(LivingDropsEvent event) {
        if (event.getSource().getDamageType().equals("player") && event.getEntityLiving().world.rand.nextFloat() < 0.01F) {
            if (event.getEntityLiving() instanceof EntityWither) {
                event.getEntityLiving().dropItem(ItemLoader.CHAOS_RECORD, 1);
            }
            if (event.getEntityLiving() instanceof EntityCaveSpider) {
                event.getEntityLiving().dropItem(ItemLoader.CORRUPT_RECORD, 1);
            }
            if (event.getEntityLiving() instanceof EntityWitherSkeleton) {
                event.getEntityLiving().dropItem(ItemLoader.BUTTERFLY_HEAD, 1);
            }
            if (event.getEntityLiving() instanceof EntitySkeleton) {
                event.getEntityLiving().dropItem(ItemLoader.DUSTY_RECORD, 1);
            }
            if (event.getEntityLiving() instanceof EntityGuardian) {
                event.getEntityLiving().dropItem(ItemLoader.ICED_RECORD, 1);
            }
            if (event.getEntityLiving() instanceof EntityWitch) {
                event.getEntityLiving().dropItem(ItemLoader.OLD_RECORD, 1);
            }
            if (event.getEntityLiving() instanceof EntityIronGolem) {
                event.getEntityLiving().dropItem(ItemLoader.OLD_RECORD_1, 1);
            }
            if (event.getEntityLiving() instanceof EntityBlaze) {
                event.getEntityLiving().dropItem(ItemLoader.ORANGE_RECORD, 1);
            }
            if (event.getEntityLiving() instanceof EntityCreeper) {
                event.getEntityLiving().dropItem(ItemLoader.OURS_RECORD, 1);
            }
            if (event.getEntityLiving() instanceof EntityEnderman) {
                event.getEntityLiving().dropItem(ItemLoader.PRECORD, 1);
            }
            if (event.getEntityLiving() instanceof EntityEndermite) {
                event.getEntityLiving().dropItem(ItemLoader.PRECORD_1, 1);
            }
            if (event.getEntityLiving() instanceof EntityZombie) {
                event.getEntityLiving().dropItem(ItemLoader.SUNRISE_RECORD, 1);
            }
        }

    }

}
