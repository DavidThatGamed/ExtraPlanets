package com.mjr.extraplanets.client.render.entities.rockets;

import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.client.model.rockets.ItemModelRocketT10New;
import com.mjr.extraplanets.entities.rockets.EntityTier10Rocket;

@SideOnly(Side.CLIENT)
public class RenderTier10RocketNew extends Render<EntityTier10Rocket> {
	private ItemModelRocketT10New rocketModel;

	public RenderTier10RocketNew(RenderManager manager) {
		super(manager);
		this.shadowSize = 2F;
	}

	private void updateModel() {
		if (this.rocketModel == null) {
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(Constants.TEXTURE_PREFIX + "rocket_t10_new", "inventory");
			this.rocketModel = (ItemModelRocketT10New) FMLClientHandler.instance().getClient().getRenderItem().getItemModelMesher().getModelManager().getModel(modelResourceLocation);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTier10Rocket par1Entity) {
		return TextureMap.locationBlocksTexture;
	}

	@Override
	public void doRender(EntityTier10Rocket entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.disableRescaleNormal();
		GlStateManager.pushMatrix();
		final float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks + 180;

		GlStateManager.translate((float) x, (float) y - 0.2, (float) z);
		GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-pitch, 0.0F, 0.0F, 1.0F);
		GlStateManager.translate(0.0F, entity.getRenderOffsetY(), 0.0F);
		final float var28 = entity.rollAmplitude / 3 - partialTicks;

		if (var28 > 0.0F) {
			final float i = entity.getLaunched() ? (5 - MathHelper.floor_double(entity.timeUntilLaunch / 85)) / 10F : 0.3F;
			GlStateManager.rotate(MathHelper.sin(var28) * var28 * i * partialTicks, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(MathHelper.sin(var28) * var28 * i * partialTicks, 1.0F, 0.0F, 1.0F);
		}

		updateModel();
		this.bindTexture(TextureMap.locationBlocksTexture);


		if (Minecraft.isAmbientOcclusionEnabled()) {
			GlStateManager.shadeModel(GL11.GL_SMOOTH);
		} else {
			GlStateManager.shadeModel(GL11.GL_FLAT);
		}

		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		GlStateManager.scale(0.7F, 0.7F, 0.7F);

		ClientUtil.drawBakedModel(this.rocketModel);
		GlStateManager.popMatrix();

		RenderHelper.enableStandardItemLighting();
	}
}