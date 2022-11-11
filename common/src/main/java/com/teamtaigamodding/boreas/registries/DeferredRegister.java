package com.teamtaigamodding.boreas.registries;

import com.teamtaigamodding.boreas.platform.Platform;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Used to register content to registries. On forge, this wraps a <code>DeferredRegister</code>, and on Fabric entries are registered directly.
 *
 * @param <T>
 */
public abstract class DeferredRegister<T> {

    protected ResourceKey<? extends Registry<T>> registryKey;
    protected String namespace;

    @ExpectPlatform
    public static <T> DeferredRegister<T> create(CoreRegistry<T> registry, String namespace) {
        return Platform.error();
    }

    @ExpectPlatform
    public static <T> DeferredRegister<T> create(ResourceKey<? extends Registry<T>> key, String namespace) {
        return Platform.error();
    }

    protected DeferredRegister(ResourceKey<? extends Registry<T>> registryKey, String namespace) {
        this.registryKey = registryKey;
        this.namespace = namespace;
    }

    public abstract <R extends T> RegistryValue<R> register(String name, Supplier<R> object);

    public abstract Supplier<CoreRegistry<T>> makeRegistry(Consumer<RegistryBuilder<T>> builder);

    public abstract Collection<RegistryValue<T>> getEntries();

    public ResourceKey<? extends Registry<T>> getRegistryKey() {
        return this.registryKey;
    }

    public ResourceLocation getRegistryName() {
        return this.registryKey.location();
    }

    public String getNamespace() {
        return this.namespace;
    }
}
