package com.belgieyt.stranded.clients.model;

import com.belgieyt.stranded.entities.StrandedTraderEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class StrandedTraderModel<S extends AbstractVillagerEntity> extends EntityModel<StrandedTraderEntity> {
    private final ModelRenderer head;
    private final ModelRenderer cube_r1;
    private final ModelRenderer head_r1;
    private final ModelRenderer body;
    private final ModelRenderer body2_r1;
    private final ModelRenderer leftarm;
    private final ModelRenderer leftarm_r1;
    private final ModelRenderer rightarm;
    private final ModelRenderer rightarm_r1;
    private final ModelRenderer leftleg;
    private final ModelRenderer rightleg;

    public StrandedTraderModel() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 24.0F, 0.0F);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.3491F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(0, 33).addBox(-4.0F, -25.0F, -8.0F, 8.0F, 4.0F, 5.0F, 0.0F, false);
        cube_r1.setTextureOffset(21, 20).addBox(-5.0F, -21.0F, -9.0F, 10.0F, 0.0F, 7.0F, 0.0F, false);
        cube_r1.setTextureOffset(21, 33).addBox(-1.0F, -17.0F, -9.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        head_r1 = new ModelRenderer(this);
        head_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(head_r1);
        setRotationAngle(head_r1, 0.3491F, 0.0F, 0.0F);
        head_r1.setTextureOffset(31, 28).addBox(-4.0F, -21.0F, -8.0F, 8.0F, 7.0F, 5.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);


        body2_r1 = new ModelRenderer(this);
        body2_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(body2_r1);
        setRotationAngle(body2_r1, 0.3491F, 0.0F, 0.0F);
        body2_r1.setTextureOffset(0, 20).addBox(-5.0F, -9.0F, -2.0F, 10.0F, 5.0F, 8.0F, 0.0F, false);
        body2_r1.setTextureOffset(0, 0).addBox(-6.0F, -19.0F, -3.0F, 12.0F, 10.0F, 10.0F, 0.0F, false);

        leftarm = new ModelRenderer(this);
        leftarm.setRotationPoint(0.0F, 24.0F, 0.0F);
        leftarm.setTextureOffset(0, 42).addBox(-8.0F, -11.0F, -10.0F, 1.0F, 11.0F, 1.0F, 0.0F, false);

        leftarm_r1 = new ModelRenderer(this);
        leftarm_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftarm.addChild(leftarm_r1);
        setRotationAngle(leftarm_r1, -0.829F, 0.0F, 0.0F);
        leftarm_r1.setTextureOffset(35, 40).addBox(-9.0F, -9.0F, -14.0F, 3.0F, 11.0F, 3.0F, 0.0F, false);

        rightarm = new ModelRenderer(this);
        rightarm.setRotationPoint(0.0F, 24.0F, 0.0F);


        rightarm_r1 = new ModelRenderer(this);
        rightarm_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightarm.addChild(rightarm_r1);
        setRotationAngle(rightarm_r1, -0.8727F, 0.0F, 0.0F);
        rightarm_r1.setTextureOffset(23, 40).addBox(6.0F, -9.0F, -14.0F, 3.0F, 11.0F, 3.0F, 0.0F, false);

        leftleg = new ModelRenderer(this);
        leftleg.setRotationPoint(0.0F, 24.0F, 0.0F);
        leftleg.setTextureOffset(0, 20).addBox(-4.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        rightleg = new ModelRenderer(this);
        rightleg.setRotationPoint(0.0F, 24.0F, 0.0F);
        rightleg.setTextureOffset(0, 0).addBox(2.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(StrandedTraderEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }


    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY= y;
        modelRenderer.rotateAngleZ = z;

    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        leftarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        rightarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        leftleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        rightleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }
}