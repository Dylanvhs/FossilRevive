package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dylanvhs.fossil_revive.entity.animations.ModAnimationDefinitions;
import net.dylanvhs.fossil_revive.entity.custom.DilophosaurusEntity;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class Dilophosaurus<T extends Entity> extends HierarchicalModel<T> {
		private final ModelPart Dilophosaurus;
		private final ModelPart head;


	public Dilophosaurus(ModelPart root) {
		this.Dilophosaurus = root.getChild("Dilophosaurus");
		this.head = Dilophosaurus.getChild("body").getChild("neck").getChild("neck2").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Dilophosaurus = partdefinition.addOrReplaceChild("Dilophosaurus", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = Dilophosaurus.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -15.0F, 4.0F));

		PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -6.0F, -8.5F, 9.0F, 12.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -4.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(27, 29).addBox(-2.5F, -3.0F, 0.5F, 5.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 4.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 29).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(3, 74).addBox(0.0F, -4.5F, 12.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 11.5F));

		PartDefinition larm = body.addOrReplaceChild("larm", CubeListBuilder.create().texOffs(59, 36).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(61, 84).addBox(0.05F, 3.5F, -2.5F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, -1.5F, -10.0F));

		PartDefinition larm2 = larm.addOrReplaceChild("larm2", CubeListBuilder.create().texOffs(57, 0).addBox(-1.5F, -1.0F, -6.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(34, 49).addBox(-1.5F, -1.0F, -7.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(26, 49).addBox(0.0F, -1.0F, -7.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(27, 30).addBox(1.5F, -1.0F, -7.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.5F, -1.5F));

		PartDefinition rarm = body.addOrReplaceChild("rarm", CubeListBuilder.create().texOffs(59, 36).mirror().addBox(-1.5F, -0.5F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(61, 84).mirror().addBox(-0.05F, 3.5F, -2.5F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.5F, -1.5F, -10.0F));

		PartDefinition rarm2 = rarm.addOrReplaceChild("rarm2", CubeListBuilder.create().texOffs(57, 0).mirror().addBox(-1.5F, -1.0F, -6.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(34, 49).mirror().addBox(1.5F, -1.0F, -7.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(26, 49).mirror().addBox(0.0F, -1.0F, -7.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(27, 30).mirror().addBox(-1.5F, -1.0F, -7.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 6.5F, -1.5F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 29).addBox(-2.5F, -8.5F, -3.5F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, -12.0F));

		PartDefinition neck2 = neck.addOrReplaceChild("neck2", CubeListBuilder.create().texOffs(29, 59).addBox(-1.5F, -13.0F, -1.0F, 3.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(58, 58).addBox(0.0F, -14.0F, 2.0F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5F, 1.5F));

		PartDefinition head = neck2.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 0.5F));

		PartDefinition upperjaw = head.addOrReplaceChild("upperjaw", CubeListBuilder.create().texOffs(42, 47).addBox(-3.5F, -3.55F, -4.5F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(92, 5).addBox(-3.5F, -1.55F, -4.5F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(48, 22).addBox(-2.5F, -3.55F, -11.5F, 5.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(75, 14).addBox(-2.5F, -1.55F, -11.5F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(82, 62).addBox(2.5F, -4.5F, -5.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(82, 62).mirror().addBox(-3.5F, -4.5F, -5.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -0.5F, -1.0F));

		PartDefinition rightkam = upperjaw.addOrReplaceChild("rightkam", CubeListBuilder.create().texOffs(81, 99).mirror().addBox(-0.5F, -7.5F, -4.5F, 1.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, -1).mirror().addBox(0.0F, -3.5F, 3.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -4.0F, -6.0F));

		PartDefinition leftkam = upperjaw.addOrReplaceChild("leftkam", CubeListBuilder.create().texOffs(81, 99).addBox(-0.5F, -7.5F, -4.5F, 1.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, -1).addBox(0.0F, -3.5F, 3.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -4.0F, -6.0F));

		PartDefinition lowerjaw = head.addOrReplaceChild("lowerjaw", CubeListBuilder.create().texOffs(52, 11).addBox(-3.0F, -0.7F, -4.5F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(97, 15).addBox(-3.0F, 1.3F, -4.5F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(96, 29).addBox(-2.0F, 0.3F, -11.45F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(4, 64).addBox(-2.0F, -2.7F, -11.45F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 38).addBox(0.0F, 1.3F, -7.5F, 0.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.65F, -1.0F));

		PartDefinition tongue = lowerjaw.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.95F, -8.45F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.65F, -2.0F));

		PartDefinition rleg = Dilophosaurus.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(35, 0).mirror().addBox(-2.5F, -0.5F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -18.5F, 4.5F));

		PartDefinition rleg2 = rleg.addOrReplaceChild("rleg2", CubeListBuilder.create().texOffs(43, 59).mirror().addBox(-1.5F, -0.5F, -0.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(82, 41).mirror().addBox(-4.5F, 8.5F, -5.5F, 9.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.0F, 1.5F));

		PartDefinition lleg = Dilophosaurus.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(35, 0).addBox(-2.5F, -0.5F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -18.5F, 4.5F));

		PartDefinition lleg2 = lleg.addOrReplaceChild("lleg2", CubeListBuilder.create().texOffs(43, 59).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(82, 41).addBox(-4.5F, 8.5F, -5.5F, 9.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 1.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);


		this.animateWalk(ModAnimationDefinitions.DILOPHOSAURUS_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((DilophosaurusEntity) entity).idleAnimationState, ModAnimationDefinitions.DILOPHOSAURUS_IDLE, ageInTicks, 1f);
		this.animate(((DilophosaurusEntity) entity).diloattackAnimationState, ModAnimationDefinitions.DILOPHOSAURUS_ATTACK, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Dilophosaurus.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return Dilophosaurus;
	}
}