package net.dylanvhs.fossil_revive.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dylanvhs.fossil_revive.entity.animations.ModAnimationDefinitions;
import net.dylanvhs.fossil_revive.entity.custom.DilophosaurusEntity;
import net.dylanvhs.fossil_revive.entity.custom.RanalophosaurusEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class Ranalophosaurus<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart Ranalophosaurus;
	private final ModelPart head;

	public Ranalophosaurus(ModelPart root) {
		this.Ranalophosaurus = root.getChild("Ranalophosaurus");
		this.head = Ranalophosaurus.getChild("body").getChild("head");

	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Ranalophosaurus = partdefinition.addOrReplaceChild("Ranalophosaurus", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = Ranalophosaurus.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -15.0F, 0.0F));

		PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -4.0F, -8.0F, 12.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(26, 25).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 6.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).addBox(0.0F, -5.0F, 6.0F, 0.0F, 10.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 7.0F));

		PartDefinition rarm = body.addOrReplaceChild("rarm", CubeListBuilder.create().texOffs(46, 65).mirror().addBox(-2.0F, -1.0F, -2.5F, 4.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(56, 13).mirror().addBox(0.0F, 1.0F, 1.5F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 4).mirror().addBox(-2.0F, 3.0F, -5.5F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 3.0F, -6.5F));

		PartDefinition larm = body.addOrReplaceChild("larm", CubeListBuilder.create().texOffs(46, 65).addBox(-2.0F, -1.0F, -2.5F, 4.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(56, 13).addBox(0.0F, 1.0F, 1.5F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(2.0F, 3.0F, -5.5F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 3.0F, -6.5F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -8.0F));

		PartDefinition upperjaw = head.addOrReplaceChild("upperjaw", CubeListBuilder.create().texOffs(40, 0).addBox(-5.0F, -4.0F, -6.5F, 10.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(12, 35).addBox(-5.0F, -2.0F, -6.5F, 10.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 5).addBox(3.0F, -6.0F, -6.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0F, -6.0F, -6.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(58, 17).addBox(-3.0F, -4.0F, -13.5F, 6.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(60, 0).addBox(-3.0F, -2.0F, -13.5F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 1.5F));

		PartDefinition rkam = upperjaw.addOrReplaceChild("rkam", CubeListBuilder.create().texOffs(28, 62).mirror().addBox(-0.5F, -9.0F, -4.5F, 1.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 32).mirror().addBox(0.0F, -4.0F, 3.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.5F, -4.0F, -8.0F));

		PartDefinition lkam = upperjaw.addOrReplaceChild("lkam", CubeListBuilder.create().texOffs(28, 62).addBox(-0.5F, -9.0F, -4.5F, 1.0F, 9.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(0.0F, -4.0F, 3.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -4.0F, -8.0F));

		PartDefinition lowerjaw = head.addOrReplaceChild("lowerjaw", CubeListBuilder.create().texOffs(28, 51).addBox(-5.0F, -1.0F, -5.5F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(50, 11).addBox(-5.0F, 1.0F, -5.5F, 10.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(52, 54).addBox(-3.0F, -1.0F, -13.5F, 6.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(50, 28).addBox(-3.0F, 1.0F, -13.5F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 1.5F));

		PartDefinition tongue = lowerjaw.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, -10.0F, 3.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.5F));

		PartDefinition rfrill = head.addOrReplaceChild("rfrill", CubeListBuilder.create().texOffs(0, 51).mirror().addBox(-13.0F, -13.0F, 0.0F, 14.0F, 25.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 76).mirror().addBox(-13.0F, -13.0F, 0.02F, 14.0F, 25.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -2.0F, -0.5F));

		PartDefinition lfrill = head.addOrReplaceChild("lfrill", CubeListBuilder.create().texOffs(0, 51).addBox(-1.0F, -13.0F, 0.0F, 14.0F, 25.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 76).addBox(-1.0F, -13.0F, 0.02F, 14.0F, 25.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -2.0F, -0.5F));

		PartDefinition rleg = Ranalophosaurus.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(0, 35).mirror().addBox(-3.0F, -1.5F, -3.5F, 6.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -13.5F, 5.5F));

		PartDefinition rleg2 = rleg.addOrReplaceChild("rleg2", CubeListBuilder.create().texOffs(64, 65).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 36).mirror().addBox(-4.0F, 8.0F, -5.0F, 8.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 5.5F, 3.5F));

		PartDefinition lleg = Ranalophosaurus.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(0, 35).addBox(-3.0F, -1.5F, -3.5F, 6.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -13.5F, 5.5F));

		PartDefinition lleg2 = lleg.addOrReplaceChild("lleg2", CubeListBuilder.create().texOffs(64, 65).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 36).addBox(-4.0F, 8.0F, -5.0F, 8.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.5F, 3.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);


		this.animateWalk(ModAnimationDefinitions.RANA_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((RanalophosaurusEntity) entity).idleAnimationState, ModAnimationDefinitions.RANA_IDLE, ageInTicks, 1f);
		this.animate(((RanalophosaurusEntity) entity).angryAnimationState, ModAnimationDefinitions.RANA_ANGRY, ageInTicks, 1f);
		this.animate(((RanalophosaurusEntity) entity).spitAnimationState, ModAnimationDefinitions.RANA_SPIT, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Ranalophosaurus.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return Ranalophosaurus;
	}
}