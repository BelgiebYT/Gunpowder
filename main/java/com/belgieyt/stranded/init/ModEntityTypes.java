package com.belgieyt.stranded.init;

import com.belgieyt.stranded.Stranded;
import com.belgieyt.stranded.entities.GunpowderEntity;
import com.belgieyt.stranded.entities.StrandedTraderEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Stranded.MOD_ID);


    public static final RegistryObject<EntityType<StrandedTraderEntity>> STRANDED_TRADER = ENTITY_TYPES.register("stranded_trader", ()->
            EntityType.Builder.create(StrandedTraderEntity::new, EntityClassification.CREATURE)
                    .size(0.6F, 1.95F).trackingRange(10).build(new ResourceLocation(Stranded.MOD_ID, "stranded_trader").toString()));

    public static final RegistryObject<EntityType<GunpowderEntity>> GUNPOWDER = ENTITY_TYPES.register("gunpowder_block",() -> EntityType.Builder.<GunpowderEntity>create(GunpowderEntity::new,
            EntityClassification.MISC).immuneToFire().size(0.98F, 0.98F).trackingRange(10).updateInterval(10).build(new ResourceLocation(Stranded.MOD_ID, "gunpowder_block").toString()));

}
