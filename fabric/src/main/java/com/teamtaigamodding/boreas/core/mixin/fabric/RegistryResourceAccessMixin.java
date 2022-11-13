package com.teamtaigamodding.boreas.core.mixin.fabric;

import com.teamtaigamodding.boreas.registries.fabric.DataPackRegistryHooks;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/resources/RegistryResourceAccess$1")
public class RegistryResourceAccessMixin {

    // Allow data registries to use the same file structure as Forge. Avoids having to do it separately on each platform
    @Inject(method = "registryDirPath(Lnet/minecraft/resources/ResourceLocation;)Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
    private static void dataRegistryDirPath(ResourceLocation location, CallbackInfoReturnable<String> cir) {
        if (!"minecraft".equals(location.getNamespace()) && DataPackRegistryHooks.validKey(location))
            cir.setReturnValue(location.getNamespace() + "/" + location.getPath());
    }
}