package net.dylanvhs.fossil_revive.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dylanvhs.fossil_revive.entity.animations.ModAnimationDefinitions;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.dylanvhs.fossil_revive.entity.custom.MicroraptorEntity;
import net.dylanvhs.fossil_revive.entity.custom.QuetzalcoatlusEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Microraptor<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart Microraptor;
	private final ModelPart head;

	public Microraptor(ModelPart root) {
		this.Microraptor = root.getChild("Microraptor");
		this.head = Microraptor.getChild("body").getChild("neck").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Microraptor = partdefinition.addOrReplaceChild("Microraptor", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = Microraptor.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(13, 9).addBox(-2.0F, -2.0F, -2.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.5F));

		PartDefinition rwing = body.addOrReplaceChild("rwing", CubeListBuilder.create().texOffs(18, 6).mirror().addBox(-5.0F, -0.25F, -1.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 8).mirror().addBox(-6.0F, -0.25F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.25F, -0.5F));

		PartDefinition lwing = body.addOrReplaceChild("lwing", CubeListBuilder.create().texOffs(18, 6).addBox(0.0F, -0.25F, -1.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(0.0F, -0.25F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.25F, -0.5F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(16, 18).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -1.5F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(-1.5F, -3.0F, -4.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 2).addBox(0.0F, -4.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition rleg = Microraptor.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 5).mirror().addBox(1.0F, -1.0F, 1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(16, 0).mirror().addBox(-2.0F, 3.0F, -3.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -3.0F, 1.5F));

		PartDefinition lleg = Microraptor.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(-1.0F, -1.0F, 1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-2.0F, 3.0F, -3.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -3.0F, 1.5F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);


		this.animateWalk(ModAnimationDefinitions.MICRORAPTOR_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((LiopleurodonEntity) entity).idleAnimationState, ModAnimationDefinitions.MICRORAPTOR_IDLE, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Microraptor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return Microraptor;
	}

}