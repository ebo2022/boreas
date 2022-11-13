package com.teamtaigamodding.boreas.registries.fabric;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;

public class DataPackRegistryHooks {

    private DataPackRegistryHooks() {
    }

    private static final Map<ResourceKey<? extends Registry<?>>, RegistryAccess.RegistryData<?>> CUSTOM_REGISTRIES = new HashMap<>();

    public static <T> void register(RegistryAccess.RegistryData<T> data) {
        ResourceKey<? extends Registry<T>> resourceKey = data.key();
        CUSTOM_REGISTRIES.put(resourceKey, data);
    }

    @ApiStatus.Internal
    @SuppressWarnings("unchecked")
    private static <T> void injectInternal(Registry<T> reg, Registry<? extends Registry<?>> rootRegistry) {
        WritableRegistry<Registry<T>> registry = (WritableRegistry<Registry<T>>) rootRegistry;
        registry.register((ResourceKey<Registry<T>>) registry.key(), reg, Lifecycle.experimental());
    }

    public static Map<ResourceKey<? extends Registry<?>>, RegistryAccess.RegistryData<?>> getCustomRegistries() {
        return CUSTOM_REGISTRIES;
    }
}
