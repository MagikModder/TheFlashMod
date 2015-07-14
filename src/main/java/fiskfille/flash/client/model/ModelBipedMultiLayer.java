package fiskfille.flash.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.MathHelper;

public class ModelBipedMultiLayer extends ModelBiped
{
	public ModelRenderer bipedBodyL2;
	public ModelRenderer bipedRightArmL2;
	public ModelRenderer bipedLeftArmL2;
	public ModelRenderer bipedRightLegL2;
	public ModelRenderer bipedLeftLegL2;

	public ModelBipedMultiLayer()
    {
        this(0.0F, 0.5F);
    }

    public ModelBipedMultiLayer(float scale, float scaleL2)
    {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.bipedLeftArm = new ModelRenderer(this, 32, 48);
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, -0.0F);
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, scale);
		this.setRotateAngle(bipedLeftArm, 0.0F, 0.0F, -0.10000736613927509F);
		this.bipedBody = new ModelRenderer(this, 16, 16);
		this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scale);
		this.bipedLeftArmL2 = new ModelRenderer(this, 48, 48);
		this.bipedLeftArmL2.setRotationPoint(5.0F, 2.0F, -0.0F);
		this.bipedLeftArmL2.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, scaleL2);
		this.setRotateAngle(bipedLeftArmL2, 0.0F, 0.0F, -0.10000736613927509F);
		this.bipedHeadwear = new ModelRenderer(this, 32, 0);
		this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scaleL2);
		this.bipedEars = new ModelRenderer(this, 24, 0);
		this.bipedEars.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, scale);
		this.bipedRightArmL2 = new ModelRenderer(this, 40, 32);
		this.bipedRightArmL2.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.bipedRightArmL2.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, scaleL2);
		this.setRotateAngle(bipedRightArmL2, 0.0F, 0.0F, 0.10000736613927509F);
		this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
		this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.1F);
		this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
		this.bipedRightArm = new ModelRenderer(this, 40, 16);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, scale);
		this.setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.10000000149011613F);
		this.bipedLeftLegL2 = new ModelRenderer(this, 0, 48);
		this.bipedLeftLegL2.setRotationPoint(1.9F, 12.0F, 0.1F);
		this.bipedLeftLegL2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleL2);
		this.bipedRightLeg = new ModelRenderer(this, 0, 16);
		this.bipedRightLeg.setRotationPoint(-1.899999976158142F, 12.0F, 0.10000000149011612F);
		this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
		this.bipedBodyL2 = new ModelRenderer(this, 16, 32);
		this.bipedBodyL2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedBodyL2.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scaleL2);
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale);
		this.bipedRightLegL2 = new ModelRenderer(this, 0, 32);
		this.bipedRightLegL2.setRotationPoint(-1.9F, 12.0F, 0.1F);
		this.bipedRightLegL2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleL2);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{ 
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.bipedLeftArm.render(f5);
		this.bipedBody.render(f5);
		this.bipedLeftArmL2.render(f5);
		this.bipedHeadwear.render(f5);
		this.bipedEars.render(f5);
		this.bipedRightArmL2.render(f5);
		this.bipedLeftLeg.render(f5);
		this.bipedRightArm.render(f5);
		this.bipedLeftLegL2.render(f5);
		this.bipedRightLeg.render(f5);
		this.bipedBodyL2.render(f5);
		this.bipedHead.render(f5);
		this.bipedRightLegL2.render(f5);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		sync(bipedBody, bipedBodyL2);
		sync(bipedRightArm, bipedRightArmL2);
		sync(bipedLeftArm, bipedLeftArmL2);
		sync(bipedRightLeg, bipedRightLegL2);
		sync(bipedLeftLeg, bipedLeftLegL2);
		
		if (entity instanceof EntitySkeleton || entity instanceof EntityZombie)
		{
			float f6 = MathHelper.sin(this.onGround * (float)Math.PI);
	        float f7 = MathHelper.sin((1.0F - (1.0F - this.onGround) * (1.0F - this.onGround)) * (float)Math.PI);
	        this.bipedRightArm.rotateAngleZ = 0.0F;
	        this.bipedLeftArm.rotateAngleZ = 0.0F;
	        this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F);
	        this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F;
	        this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F);
	        this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F);
	        this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
	        this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
	        this.bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
	        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
	        this.bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
	        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
	        sync(bipedRightArm, bipedRightArmL2);
			sync(bipedLeftArm, bipedLeftArmL2);
		}
	}
	
	public void sync(ModelRenderer parent, ModelRenderer child)
	{
		child.rotateAngleX = parent.rotateAngleX;
		child.rotateAngleY = parent.rotateAngleY;
		child.rotateAngleZ = parent.rotateAngleZ;
		
		child.rotationPointX = parent.rotationPointX;
		child.rotationPointY = parent.rotationPointY;
		child.rotationPointZ = parent.rotationPointZ;
		
		child.showModel = parent.showModel;
		child.isHidden = parent.isHidden;
		child.mirror = parent.mirror;
		
		child.offsetX = parent.offsetX;
		child.offsetY = parent.offsetY;
		child.offsetZ = parent.offsetZ;
	}
}
