package io.github.sjx233.ironrust;

import io.github.sjx233.ironrust.client.renderer.entity.RustyCowRenderer;
import io.github.sjx233.ironrust.world.entity.IronRustEntityType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class IronRustClientMod implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    EntityRendererRegistry.INSTANCE.register(IronRustEntityType.RUSTY_COW, (dispatcher, context) -> new RustyCowRenderer(dispatcher));
    EntityRendererRegistry.INSTANCE.register(IronRustEntityType.RUST_POWDER, (dispatcher, context) -> new FlyingItemEntityRenderer<>(dispatcher, context.getItemRenderer()));
  }
}
