package com.github.heartblade.init;

import com.github.heartblade.heartblade;
import com.github.heartblade.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import static com.github.heartblade.heartblade.MODID;

public class HbEntitys {
    public HbEntitys(){
        loadEntity();
    }

    public void loadEntity(){
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "entitydriveex"),
                EntityDriveEx.class,
                "EntityDriveEx",
                1,
                heartblade.instance,
                64,
                1,
                false
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "entityphantomswordexbase"),
                EntityPhantomSwordExBase.class,
                "EntityPhantomSwordexbase",
                2,
                heartblade.instance,
                64,
                1,
                false
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "entityphantomswordex"),
                EntityPhantomSwordEx.class,
                "EntityPhantomSwordEx",
                3,
                heartblade.instance,
                64,
                1,
                false
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "entityflake"),
                EntityFlake.class,
                "EntityFlake",
                554,
                heartblade.instance,
                64,
                1,
                false
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation("flammpfeil.slashblade", "SlashDimensionP"),
                EntitySlashDimensionP.class,
                "SlashDimensionP",
                555,
                heartblade.instance,
                250,
                200,
                true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation("flammpfeil.slashblade", "SlashDimensionP_c"),
                EntitySlashDimensionP_core.class,
                "SlashDimensionP_c",
                556,
                heartblade.instance,
                250,
                200,
                true
        );
    }
}
