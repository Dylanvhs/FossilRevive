package net.dylanvhs.fossil_revive.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dylanvhs.fossil_revive.entity.animations.ModAnimationDefinitions;
import net.dylanvhs.fossil_revive.entity.custom.QuetzalcoatlusEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class Quetzalcoatlus<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "quetzalcoatlus"), "main");
	private final ModelPart Quetzalcoatlus;

	private final ModelPart head;

	public Quetzalcoatlus(ModelPart root) {
		this.Quetzalcoatlus = root.getChild("Quetzalcoatlus");
		this.head = Quetzalcoatlus.getChild("body").getChild("neck").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Quetzalcoatlus = partdefinition.addOrReplaceChild("Quetzalcoatlus", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = Quetzalcoatlus.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 60).addBox(-8.0F, -34.0F, -15.0F, 16.0F, 17.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(62, 60).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 15.0F));

		PartDefinition rwing = body.addOrReplaceChild("rwing", CubeListBuilder.create().texOffs(112, 75).mirror().addBox(-20.0F, -4.0F, -4.0F, 20.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 111).mirror().addBox(-20.0F, -4.0F, -24.0F, 6.0F, 6.0F, 20.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(48, 20).mirror().addBox(-48.0F, -0.5F, -20.0F, 48.0F, 0.0F, 24.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(84, 111).mirror().addBox(-48.0F, -4.0F, -24.0F, 28.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, -26.0F, -3.0F));

		PartDefinition rwing2 = rwing.addOrReplaceChild("rwing2", CubeListBuilder.create().texOffs(0, 111).mirror().addBox(-32.0F, -3.0F, -1.0F, 32.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 0).mirror().addBox(-50.0F, -0.5F, 0.0F, 50.0F, 0.0F, 20.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-48.0F, 0.0F, -20.0F));

		PartDefinition lwing = body.addOrReplaceChild("lwing", CubeListBuilder.create().texOffs(112, 75).addBox(0.0F, -4.0F, -4.0F, 20.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(52, 111).addBox(14.0F, -4.0F, -24.0F, 6.0F, 6.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(48, 20).addBox(0.0F, -0.5F, -20.0F, 48.0F, 0.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(84, 111).addBox(20.0F, -4.0F, -24.0F, 28.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -26.0F, -3.0F));

		PartDefinition lwing2 = lwing.addOrReplaceChild("lwing2", CubeListBuilder.create().texOffs(0, 111).addBox(0.0F, -3.0F, -1.0F, 32.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 0).addBox(0.0F, -0.5F, 0.0F, 50.0F, 0.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(48.0F, 0.0F, -20.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -16.0F, -7.0F, 12.0F, 22.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -29.0F, -17.0F));

		PartDefinition neck2 = neck.addOrReplaceChild("neck2", CubeListBuilder.create().texOffs(0, 119).addBox(-4.5F, -21.0F, -4.0F, 9.0F, 23.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 5.0F));

		PartDefinition head = neck2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(66, 154).addBox(-4.5F, -1.0F, -6.0F, 9.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, -2.0F));

		PartDefinition upperjaw = head.addOrReplaceChild("upperjaw", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -6.0F, -52.0F, 10.0F, 8.0F, 52.0F, new CubeDeformation(0.0F))
		.texOffs(77, 138).addBox(-5.0F, 0.0F, -52.0F, 10.0F, 0.0F, 52.0F, new CubeDeformation(0.0F))
		.texOffs(175, 22).addBox(5.0F, -6.0F, -7.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(175, 22).mirror().addBox(-6.0F, -6.0F, -7.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 17).addBox(0.0F, -19.0F, -24.0F, 0.0F, 13.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 1.0F));

		PartDefinition lowerjaw = head.addOrReplaceChild("lowerjaw", CubeListBuilder.create().texOffs(100, 44).addBox(-5.0F, 0.0F, -24.0F, 10.0F, 7.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(191, 94).addBox(-5.0F, 3.0F, -24.0F, 10.0F, 0.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(173, 157).addBox(-5.0F, 3.0F, -51.0F, 10.0F, 0.0F, 27.0F, new CubeDeformation(0.0F))
		.texOffs(64, 79).addBox(-5.0F, 0.0F, -52.0F, 10.0F, 4.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(194, 47).addBox(0.0F, 7.0F, -18.0F, 0.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 1.0F));

		PartDefinition rleg = Quetzalcoatlus.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(0, 60).mirror().addBox(-3.5F, -2.0F, -3.5F, 7.0F, 16.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.5F, -24.0F, 8.5F));

		PartDefinition rleg2 = rleg.addOrReplaceChild("rleg2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 14.0F, 2.0F));

		PartDefinition rfoot = rleg2.addOrReplaceChild("rfoot", CubeListBuilder.create().texOffs(64, 74).mirror().addBox(-2.5F, 0.0F, -5.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(163, 115).mirror().addBox(-5.5F, 2.0F, -13.0F, 11.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 8.0F, 0.5F));

		PartDefinition lleg = Quetzalcoatlus.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(0, 60).addBox(-3.5F, -2.0F, -3.5F, 7.0F, 16.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -24.0F, 8.5F));

		PartDefinition lleg2 = lleg.addOrReplaceChild("lleg2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 2.0F));

		PartDefinition lfoot = lleg2.addOrReplaceChild("lfoot", CubeListBuilder.create().texOffs(64, 74).addBox(-2.5F, 0.0F, -5.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(163, 115).addBox(-5.5F, 2.0F, -13.0F, 11.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.5F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);


		this.animateWalk(ModAnimationDefinitions.QUETZALCOATLUS_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((QuetzalcoatlusEntity) entity).idleAnimationState, ModAnimationDefinitions.QUETZALCOATLUS_IDLE, ageInTicks, 1f);
		this.animate(QuetzalcoatlusEntity.attackAnimationState, ModAnimationDefinitions.LIOPLEURODON_ATTACK, ageInTicks, 1f);
		this.animate(((QuetzalcoatlusEntity) entity).flyAnimationState, ModAnimationDefinitions.QUETZALCOATLUS_FLY, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Quetzalcoatlus.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return Quetzalcoatlus;
	}
}