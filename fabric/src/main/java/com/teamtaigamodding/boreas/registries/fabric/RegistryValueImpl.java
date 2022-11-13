package com.teamtaigamodding.boreas.registries.fabric;

import com.teamtaigamodding.boreas.registries.RegistryValue;
import com.teamtaigamodding.boreas.registries.CoreRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class RegistryValueImpl<T> extends RegistryValue<T> {

    @Nullable
    private final ResourceLocation name;
    @Nullable
    private ResourceKey<T> key;
    @Nullable
    protected T value;
    @Nullable
    private Holder<T> holder;

    public static <T, R extends T> RegistryValue<R> create(CoreRegistry<T> registry, ResourceLocation name) {
        return new RegistryValueImpl<R>(name, registry);
    }

    public static <T, R extends T> RegistryValue<R> create(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation name, String mod) {
        return new RegistryValueImpl<>(name, registryKey.location());
    }

    public static <T, R extends T> RegistryValue<R> create(ResourceLocation registryName, ResourceLocation name, String mod) {
        return new RegistryValueImpl<>(name, registryName);
    }

    @SuppressWarnings("unchecked")
    private RegistryValueImpl(ResourceLocation name, CoreRegistry<?> registry) {
        if (registry == null)
            throw new IllegalArgumentException("Invalid registry argument, must not be null");
        this.name = name;
        this.key = (ResourceKey<T>) ResourceKey.create(registry.key(), name);
        this.updateReference(((CoreRegistryImpl<? extends T>) registry).getRegistry());
    }

    private RegistryValueImpl(final ResourceLocation name, final ResourceLocation registryName) {
        this.name = name;
        this.key = ResourceKey.create(ResourceKey.createRegistryKey(registryName), name);
        this.updateReference(registryName);
    }

    private static boolean registryExists(ResourceLocation registryName) {
        return Registry.REGISTRY.containsKey(registryName) || BuiltinRegistries.REGISTRY.containsKey(registryName);
    }

    @Override
    public boolean isPresent() {
        return this.value != null;
    }

    @Override
    public ResourceLocation getId() {
        return this.name;
    }

    @Override
    public ResourceKey<T> getKey() {
        return this.key;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Holder<T>> getHolder() {
        if (this.holder == null && this.key != null && registryExists(this.key.registry())) {
            ResourceLocation registryName = this.key.registry();
            Registry<T> registry = (Registry<T>) Registry.REGISTRY.get(registryName);
            if (registry == null)
                registry = (Registry<T>) BuiltinRegistries.REGISTRY.get(registryName);
            if (registry != null)
                this.holder = registry.getOrCreateHolder(this.key).result().get();
        }
        return Optional.ofNullable(this.holder);
    }

    @Override
    public T get() {
        T ret = this.value;
        Objects.requireNonNull(ret, () -> "Registry Object not present: " + this.name);
        return ret;
    }

    @SuppressWarnings("unchecked")
    void updateReference(Registry<? extends T> registry) {
        if (this.name == null || this.key == null)
            return;
        if (registry.containsKey(this.name)) {
            this.value = registry.get(this.name);
            this.holder = ((Registry<T>) registry).getHolder(this.key).orElse(null);
        } else {
            this.value = null;
            this.holder = null;
        }
    }

    @SuppressWarnings("unchecked")
    void updateReference(ResourceLocation registryName) {
        if (this.name == null)
            return;
        Registry<? extends T> vanillaRegistry = (Registry<? extends T>) Registry.REGISTRY.get(registryName);
        if (vanillaRegistry != null) {
            updateReference(vanillaRegistry);
            return;
        }
        Registry<? extends T> builtinRegistry = (Registry<? extends T>) BuiltinRegistries.REGISTRY.get(registryName);
        if (builtinRegistry != null) {
            updateReference(builtinRegistry);
            return;
        }
        this.value = null;
        this.holder = null;
    }
}
