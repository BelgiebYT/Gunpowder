package com.belgieyt.stranded.clients.renderer;

import com.belgieyt.stranded.Stranded;
import com.belgieyt.stranded.clients.model.StrandedTraderModel;
import com.belgieyt.stranded.entities.StrandedTraderEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.VillagerLevelPendantLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrandedTraderRenderer extends MobRenderer<StrandedTraderEntity, StrandedTraderModel<StrandedTraderEntity>> {
    private static final ResourceLocation COW_TEXTURES = new ResourceLocation(Stranded.MOD_ID,"textures/entity/stranded.png");
     private BipedModel model;

    public StrandedTraderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new StrandedTraderModel<>(), 0.7F);
        this.addLayer(new BipedArmorLayer(this,model,model));
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(StrandedTraderEntity entity) {
        return COW_TEXTURES;
    }
}
