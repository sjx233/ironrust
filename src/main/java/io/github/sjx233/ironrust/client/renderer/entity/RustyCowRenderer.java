package io.github.sjx233.ironrust.client.renderer.entity;

import io.github.sjx233.ironrust.IronRustMod;
import io.github.sjx233.ironrust.world.entity.RustyCow;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RustyCowRenderer extends MobEntityRenderer<RustyCow, CowEntityModel<RustyCow>> {
  private static final Identifier TEXTURE = IronRustMod.makeId("textures/entity/rusty_cow.png");

  public RustyCowRenderer(EntityRenderDispatcher dispatcher) {
    super(dispatcher, new CowEntityModel<>(), 0.7f);
  }

  @Override
  public Identifier getTexture(RustyCow entity) {
    return TEXTURE;
  }
}
