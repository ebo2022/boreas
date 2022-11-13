package com.teamtaigamodding.boreas.core.mixin.fabric;

import com.google.common.collect.ImmutableMap;
import com.teamtaigamodding.boreas.registries.ModRegistryHolder;
import com.teamtaigamodding.boreas.registries.fabric.DataPackRegistryHooks;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ServiceLoader;

@Mixin(RegistryAccess.class)
public interface RegistryAccessMixin {

    @SuppressWarnings("ALL")
    @Inject(method = "method_30531", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void addDataRegistries(CallbackInfoReturnable<ImmutableMap<ResourceKey<? extends Registry<?>>, RegistryAccess.RegistryData<?>>> cir, ImmutableMap.Builder<ResourceKey<? extends Registry<?>>, RegistryAccess.RegistryData<?>> builder) {
        // Hacky method to load classes in early. This allows for for data pack registry data to be present earlier during runtime without the need of an entrypoint
        ServiceLoader.load(ModRegistryHolder.class).forEach(clz -> clz.getClass().getName());

        builder.putAll(DataPackRegistryHooks.registryData());
    }
}
