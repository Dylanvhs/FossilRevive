package net.dylanvhs.fossil_revive.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dylanvhs.fossil_revive.entity.animations.ModAnimationDefinitions;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.dylanvhs.fossil_revive.entity.custom.XenacanthusEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class Xenacanthus<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart Xenacanthus;

	public Xenacanthus(ModelPart root) {
		this.Xenacanthus = root.getChild("Xenacanthus");
	}

		public static LayerDefinition createBodyLayer () {
			MeshDefinition meshdefinition = new MeshDefinition();
			PartDefinition partdefinition = meshdefinition.getRoot();

			PartDefinition xenacanthus = partdefinition.addOrReplaceChild("xenacanthus", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

			PartDefinition body = xenacanthus.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

			PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 27).addBox(-2.0F, -2.5F, -6.0F, 4.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
					.texOffs(0, 0).addBox(0.0F, -4.5F, -5.0F, 0.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 0.0F));

			PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(18, 9).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 14.0F, new CubeDeformation(0.0F))
					.texOffs(0, 0).addBox(0.0F, -4.0F, 4.0F, 0.0F, 7.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 6.0F));

			PartDefinition backfin = tail.addOrReplaceChild("backfin", CubeListBuilder.create().texOffs(18, 6).addBox(0.0F, 1.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, 2.0F, 0.0F, 0.0F, 1.5708F));

			PartDefinition backfin2 = tail.addOrReplaceChild("backfin2", CubeListBuilder.create().texOffs(31, 2).addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 6.0F, 0.0F, 0.0F, 1.5708F));

			PartDefinition rfin = body.addOrReplaceChild("rfin", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-5.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, -3.5F, 0.0F, 0.0F, -0.3927F));

			PartDefinition lfin = body.addOrReplaceChild("lfin", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -3.5F, 0.0F, 0.0F, 0.3927F));

			PartDefinition rfin2 = body.addOrReplaceChild("rfin2", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-5.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 4.5F, 0.0F, 0.0F, -0.3927F));

			PartDefinition lfin2 = body.addOrReplaceChild("lfin2", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 4.5F, 0.0F, 0.0F, 0.3927F));

			PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(14, 0).addBox(-1.5F, 0.5F, -4.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
					.texOffs(0, 0).addBox(-2.0F, -2.5F, -6.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
					.texOffs(13, 5).addBox(0.0F, -6.5F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, -6.0F));

			return LayerDefinition.create(meshdefinition, 64, 64);
		}

		@Override
		public void setupAnim (T entity,float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
		float headPitch){
			this.root().getAllParts().forEach(ModelPart::resetPose);
			float f = 1.0F;

			if (!entity.isInWater()) {
				f = 1.5F;
			}

			this.animateWalk(ModAnimationDefinitions.XENACANTHUS_SWIM, limbSwing, limbSwingAmount, 2f, 2.5f);
			this.animate(((XenacanthusEntity) entity).idleAnimationState, ModAnimationDefinitions.XENACANTHUS_SWIM, ageInTicks, 1f);
		}

		@Override
		public void renderToBuffer (PoseStack poseStack, VertexConsumer vertexConsumer,int packedLight,
		int packedOverlay, float red, float green, float blue, float alpha){
			Xenacanthus.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}

		@Override
		public ModelPart root () {
			return Xenacanthus;
		}
	}
