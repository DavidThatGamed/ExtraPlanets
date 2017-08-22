package com.mjr.extraplanets.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelEvolvedFireBatBoss extends ModelBase {
	private final ModelRenderer batHead;
	/** The body box of the bat model. */
	private final ModelRenderer batBody;
	/** The inner right wing box of the bat model. */
	private final ModelRenderer batRightWing;
	/** The inner left wing box of the bat model. */
	private final ModelRenderer batLeftWing;
	/** The outer right wing box of the bat model. */
	private final ModelRenderer batOuterRightWing;
	/** The outer left wing box of the bat model. */
	private final ModelRenderer batOuterLeftWing;

	public ModelEvolvedFireBatBoss() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.batHead = new ModelRenderer(this, 0, 0);
		this.batHead.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
		ModelRenderer modelrenderer = new ModelRenderer(this, 24, 0);
		modelrenderer.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
		this.batHead.addChild(modelrenderer);
		ModelRenderer modelrenderer1 = new ModelRenderer(this, 24, 0);
		modelrenderer1.mirror = true;
		modelrenderer1.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
		this.batHead.addChild(modelrenderer1);
		this.batBody = new ModelRenderer(this, 0, 16);
		this.batBody.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
		this.batBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);
		this.batRightWing = new ModelRenderer(this, 42, 0);
		this.batRightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);
		this.batOuterRightWing = new ModelRenderer(this, 24, 16);
		this.batOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
		this.batOuterRightWing.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);
		this.batLeftWing = new ModelRenderer(this, 42, 0);
		this.batLeftWing.mirror = true;
		this.batLeftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);
		this.batOuterLeftWing = new ModelRenderer(this, 24, 16);
		this.batOuterLeftWing.mirror = true;
		this.batOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
		this.batOuterLeftWing.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);
		this.batBody.addChild(this.batRightWing);
		this.batBody.addChild(this.batLeftWing);
		this.batRightWing.addChild(this.batOuterRightWing);
		this.batLeftWing.addChild(this.batOuterLeftWing);
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		this.batHead.render(scale);
		this.batBody.render(scale);
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how "far" arms and legs
	 * can swing at most.
	 */
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		this.batHead.rotateAngleX = headPitch * 0.017453292F;
		this.batHead.rotateAngleY = netHeadYaw * 0.017453292F;
		this.batHead.rotateAngleZ = 0.0F;
		this.batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.batRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.batLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.batBody.rotateAngleX = ((float) Math.PI / 4F) + MathHelper.cos(ageInTicks * 0.1F) * 0.15F;
		this.batBody.rotateAngleY = 0.0F;
		this.batRightWing.rotateAngleY = MathHelper.cos(ageInTicks * 1.3F) * (float) Math.PI * 0.25F;
		this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
		this.batOuterRightWing.rotateAngleY = this.batRightWing.rotateAngleY * 0.5F;
		this.batOuterLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY * 0.5F;

	}
}