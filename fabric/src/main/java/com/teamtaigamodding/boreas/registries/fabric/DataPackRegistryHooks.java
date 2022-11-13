package com.teamtaigamodding.boreas.registries.fabric;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;

public final class DataPackRegistryHooks {

    private DataPackRegistryHooks() {
    }


    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<ResourceKey<? extends Registry<?>>, ExtendedRegistryData<?>> CUSTOM_REGISTRIES = new HashMap<>();
    private static final Set<ResourceLocation> REGISTRY_KEYS = new HashSet<>();

    public static <T> void register(RegistryAccess.RegistryData<T> data, Registry<T> registry) {
        ResourceKey<? extends Registry<T>> resourceKey = data.key();
        CUSTOM_REGISTRIES.put(resourceKey, new ExtendedRegistryData<>(data, registry));
        REGISTRY_KEYS.add(resourceKey.location());
    }

    public static <T> void addBuiltinRegistry(Registry<T> registry) {
        injectRegistry(registry, BuiltinRegistries.REGISTRY);
        LOGGER.info("Added a custom data registry: " + registry.key().location().toString());
    }

    @SuppressWarnings("unchecked")
    private static <T> void injectRegistry(Registry<T> reg, Registry<? extends Registry<?>> rootRegistry) {
        WritableRegistry<Registry<T>> registry = (WritableRegistry<Registry<T>>) rootRegistry;
        registry.register((ResourceKey<Registry<T>>) reg.key(), reg, Lifecycle.experimental());
    }

    public static Map<ResourceKey<? extends Registry<?>>, ExtendedRegistryData<?>> registryData() {
        return CUSTOM_REGISTRIES;
    }

    public static boolean validKey(ResourceLocation key) {
        return REGISTRY_KEYS.contains(key);
    }

    public record ExtendedRegistryData<T>(RegistryAccess.RegistryData<T> data, Registry<T> registry) {
    }
}
