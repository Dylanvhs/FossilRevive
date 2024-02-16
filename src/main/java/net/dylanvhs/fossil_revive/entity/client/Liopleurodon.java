package net.dylanvhs.fossil_revive.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dylanvhs.fossil_revive.entity.animations.ModAnimationDefinitions;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class Liopleurodon<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart Liopleurodon;
	private final ModelPart head;


	public Liopleurodon(ModelPart root) {
		this.Liopleurodon = root.getChild("Liopleurodon");
		this.head = Liopleurodon.getChild("body").getChild("neck").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Liopleurodon = partdefinition.addOrReplaceChild("Liopleurodon", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = Liopleurodon.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-10.5F, -9.0F, -20.5F, 21.0F, 18.0F, 41.0F, new CubeDeformation(0.0F))
		.texOffs(70, 59).addBox(-10.5F, -11.0F, -20.5F, 21.0F, 2.0F, 41.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition rfin = bone.addOrReplaceChild("rfin", CubeListBuilder.create().texOffs(83, 26).mirror().addBox(-21.0F, -0.5F, -7.0F, 22.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).mirror().addBox(-22.0F, -0.5F, -7.0F, 1.0F, 0.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-11.5F, 7.5F, -13.5F));

		PartDefinition lfin = bone.addOrReplaceChild("lfin", CubeListBuilder.create().texOffs(83, 26).addBox(-1.0F, -0.5F, -7.0F, 22.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(21.0F, -0.5F, -7.0F, 1.0F, 0.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(11.5F, 7.5F, -13.5F));

		PartDefinition rbackfin = bone.addOrReplaceChild("rbackfin", CubeListBuilder.create().texOffs(165, 28).mirror().addBox(-15.0F, -0.5F, -4.5F, 15.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(-1, 0).mirror().addBox(-16.0F, -0.5F, -4.5F, 1.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-10.5F, 7.5F, 16.0F));

		PartDefinition lbackfin = bone.addOrReplaceChild("lbackfin", CubeListBuilder.create().texOffs(165, 28).addBox(0.0F, -0.5F, -5.5F, 15.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(-1, 0).addBox(15.0F, -0.5F, -5.5F, 1.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(10.5F, 7.5F, 16.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 59).addBox(-6.5F, -5.0F, 0.0F, 13.0F, 11.0F, 44.0F, new CubeDeformation(0.0F))
		.texOffs(70, 102).addBox(-6.5F, -9.0F, 0.0F, 13.0F, 4.0F, 44.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 20.5F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 136).addBox(-7.5F, -5.0F, -10.0F, 15.0F, 13.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(54, 133).addBox(-7.5F, -7.0F, -10.0F, 15.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -20.5F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -10.0F));

		PartDefinition upperjaw = head.addOrReplaceChild("upperjaw", CubeListBuilder.create().texOffs(45, 114).addBox(-8.5F, -4.0F, -12.0F, 17.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(83, 0).addBox(-6.5F, -4.0F, -31.0F, 13.0F, 7.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(0, 59).addBox(-6.5F, -7.0F, -12.0F, 13.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-6.5F, -8.0F, -12.0F, 13.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition cube_r1 = upperjaw.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, 0.0F, -9.5F, 0.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5F, 3.0F, -21.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r2 = upperjaw.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.0F, -9.5F, 0.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, 3.0F, -21.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r3 = upperjaw.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(15, 0).addBox(-5.5F, 0.0F, 0.0F, 11.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -31.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition lowerjaw = head.addOrReplaceChild("lowerjaw", CubeListBuilder.create().texOffs(124, 41).addBox(-8.5F, 0.0F, -12.0F, 17.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 114).addBox(-6.5F, 0.0F, -31.0F, 13.0F, 3.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition cube_r4 = lowerjaw.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 15).addBox(-6.5F, -2.0F, 0.0F, 13.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -31.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r5 = lowerjaw.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, -9.5F, 0.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5F, 0.0F, -21.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r6 = lowerjaw.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, -2.0F, -9.5F, 0.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, 0.0F, -21.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition tongue = lowerjaw.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(163, 4).addBox(-2.5F, -1.0F, -22.0F, 5.0F, 2.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		float f = 1.0F;

		if (!entity.isInWater()) {
			f = 1.5F;
		}
		this.Liopleurodon.xRot = headPitch * ((float)Math.PI / 220F);
		this.Liopleurodon.yRot = netHeadYaw * ((float)Math.PI / 220F);


		this.animateWalk(ModAnimationDefinitions.LIOPLEURODON_SWIM, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((LiopleurodonEntity) entity).idleAnimationState, ModAnimationDefinitions.LIOPLEURODON_IDLE, ageInTicks, 1f);
		this.animate(LiopleurodonEntity.attackAnimationState, ModAnimationDefinitions.LIOPLEURODON_ATTACK, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Liopleurodon.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return Liopleurodon;
	}
}