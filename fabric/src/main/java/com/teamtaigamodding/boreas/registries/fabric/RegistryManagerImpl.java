package com.teamtaigamodding.boreas.registries.fabric;

import com.teamtaigamodding.boreas.registries.CoreRegistry;
import com.teamtaigamodding.boreas.registries.RegistryManager;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class RegistryManagerImpl extends RegistryManager{
    private final Map<Registry<?>, CoreRegistry<?>> registries;

    private RegistryManagerImpl() {
        this.registries = new HashMap<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> CoreRegistry<T> getRegistry(ResourceLocation key) {
        Registry<?> registry = Registry.REGISTRY.get(key);
        if (registry == null) registry = BuiltinRegistries.REGISTRY.get(key);
        if (registry != null) {
            return (CoreRegistry<T>) registries.computeIfAbsent(registry, CoreRegistryImpl::new);
        }
        throw new IllegalArgumentException("Invalid registry: " + key + " does not exist!");
    }

    public static RegistryManager _get() {
        return new RegistryManagerImpl();
    }
}
