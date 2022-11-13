package com.teamtaigamodding.boreas.registries.fabric;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.teamtaigamodding.boreas.platform.ModInstance;
import com.teamtaigamodding.boreas.registries.*;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DeferredRegisterImpl<T> extends DeferredRegister<T> {

    private final Map<RegistryValueImpl<T>, Supplier<? extends T>> entries = new LinkedHashMap<>();
    private final Set<RegistryValue<T>> entriesView = Collections.unmodifiableSet(entries.keySet());

    @Nullable
    private RegistryBuilderImpl<T> factory = null;

    private DeferredRegisterImpl(ResourceKey<? extends Registry<T>> key, String namespace) {
        super(key, namespace);
    }

    public static <T> DeferredRegister<T> create(CoreRegistry<T> registry, String namespace) {
        return new DeferredRegisterImpl<>(registry.key(), namespace);
    }

    public static <T> DeferredRegister<T> create(ResourceKey<? extends Registry<T>> key, String namespace) {
        return new DeferredRegisterImpl<>(key, namespace);
    }

    @Override
    public <R extends T> RegistryValue<R> register(String name, Supplier<R> object) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(object);
        final ResourceLocation key = new ResourceLocation(this.namespace, name);
        RegistryValue<R> ret;
        if (this.registryKey != null)
            ret = RegistryValue.create(this.registryKey, key, this.namespace);
        else
            throw new IllegalStateException("Could not create RegistryObject in DeferredRegister");
        if (entries.putIfAbsent((RegistryValueImpl<T>) ret, object) != null) {
            throw new IllegalArgumentException("Duplicate registration " + name);
        }
        return ret;
    }

    @Override
    public Supplier<CoreRegistry<T>> makeRegistry(Consumer<RegistryBuilder<T>> builder) {
        RegistryBuilderImpl<T> implBuilder = new RegistryBuilderImpl<>(this.getRegistryName());
        builder.accept(implBuilder);
        this.factory = implBuilder;
        return new RegistryHolder<>(this.registryKey);
    }

    @Override
    public Collection<RegistryValue<T>> getEntries() {
        return this.entriesView;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onRegister(ModInstance modInstance) {
        Registry<T> registry = (Registry<T>) Registry.REGISTRY.get(this.getRegistryName());
        if (registry == null) registry = (Registry<T>) BuiltinRegistries.REGISTRY.get(this.getRegistryName());
        if (this.factory != null) {
            registry = this.factory.assemble();
            Pair<Codec<T>, @Nullable Codec<T>> codecs = this.factory.getDataCodecs();
            if (codecs != null)
                DataPackRegistryHooks.register(new RegistryAccess.RegistryData<>(this.registryKey, codecs.getFirst(), codecs.getSecond()), registry);
        }
        if (registry != null) {
            Registry<T> finalRegistry = registry;
            this.entries.forEach((key, value) -> {
                Registry.register(finalRegistry, key.getId(), value.get());
                key.updateReference(finalRegistry);
            });
        } else {
            throw new IllegalArgumentException("Invalid registry:" + this.getRegistryName());
        }
    }

    private static class RegistryHolder<T> implements Supplier<CoreRegistry<T>> {
        private final ResourceKey<? extends Registry<T>> registryKey;
        private CoreRegistry<T> registry = null;

        private RegistryHolder(ResourceKey<? extends Registry<T>> registryKey) {
            this.registryKey = registryKey;
        }

        @Override
        public CoreRegistry<T> get() {
            // Keep looking up the registry until it's not null
            if (this.registry == null)
                this.registry = RegistryManager.INSTANCE.getRegistry(this.registryKey);
            return this.registry;
        }
    }
}
