package net.dylanvhs.fossil_revive.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dylanvhs.fossil_revive.entity.animations.ModAnimationDefinitions;
import net.dylanvhs.fossil_revive.entity.custom.DilophosaurusEntity;
import net.dylanvhs.fossil_revive.entity.custom.DodoEntity;
import net.dylanvhs.fossil_revive.entity.custom.MicroraptorEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class Dodo<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart Dodo;
	private final ModelPart head;

	public Dodo(ModelPart root) {
		this.Dodo = root.getChild("Dodo");
		this.head = Dodo.getChild("body").getChild("neck").getChild("head");
	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Dodo = partdefinition.addOrReplaceChild("Dodo", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = Dodo.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.0F, -5.5F, 10.0F, 10.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(23, 28).addBox(-3.5F, -4.0F, -1.5F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 5.0F));

		PartDefinition rwing = body.addOrReplaceChild("rwing", CubeListBuilder.create().texOffs(36, 15).mirror().addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.5F, 1.0F, -4.5F));

		PartDefinition lwing = body.addOrReplaceChild("lwing", CubeListBuilder.create().texOffs(36, 15).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 1.0F, -4.5F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 35).addBox(-2.0F, -4.0F, -2.5F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -5.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 21).addBox(-4.0F, -7.0F, -5.5F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-1.0F, -4.0F, -7.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(31, 0).addBox(-2.0F, -5.0F, -12.5F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 1.0F));

		PartDefinition rleg = Dodo.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(19, 21).mirror().addBox(-3.0F, 4.0F, -3.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -4.0F, 1.5F));

		PartDefinition lleg = Dodo.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(19, 21).addBox(-3.0F, 4.0F, -3.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -4.0F, 1.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);


		this.animateWalk(ModAnimationDefinitions.DODO_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((DodoEntity) entity).idleAnimationState, ModAnimationDefinitions.DODO_IDLE, ageInTicks, 1f);
		this.animate(((DodoEntity) entity).flapAnimationState, ModAnimationDefinitions.DODO_FLAP, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Dodo.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return Dodo;
	}
}