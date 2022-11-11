package dev.atlas.registries.fabric;

import dev.atlas.registries.CoreRegistry;
import dev.atlas.registries.RegistryValue;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class RegistryValueImpl {
    public static RegistryValue<T> create(CoreRegistry<T> registry, ResourceLocation name) {
    }

    public static RegistryValue<T> create(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation name, String mod) {
    }

    public static RegistryValue<T> create(ResourceLocation registryName, ResourceLocation name, String mod) {
    }
}
