package com.teamtaigamodding.boreas.registries.fabric;

import com.mojang.serialization.Codec;
import com.teamtaigamodding.boreas.registries.CoreRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@ApiStatus.Internal
public class CoreRegistryImpl<T> implements CoreRegistry<T> {

    private final Registry<T> parent;

    protected CoreRegistryImpl(Registry<T> parent) {
        this.parent = parent;
    }

    @Override
    public ResourceLocation getKey(T value) {
        return this.parent.getKey(value);
    }

    @Override
    public int getId(@Nullable T value) {
        return this.parent.getId(value);
    }

    @Override
    public @Nullable T get(@Nullable ResourceLocation name) {
        return this.parent.get(name);
    }

    @Override
    public @Nullable T byId(int id) {
        return this.parent.byId(id);
    }

    @Override
    public ResourceKey<? extends Registry<T>> key() {
        return this.parent.key();
    }

    @Override
    public Set<ResourceLocation> keySet() {
        return this.parent.keySet();
    }

    @Override
    public Set<Map.Entry<ResourceKey<T>, T>> entrySet() {
        return this.parent.entrySet();
    }

    @Override
    public boolean containsKey(ResourceLocation name) {
        return this.parent.containsKey(name);
    }

    @Override
    public Codec<T> byNameCodec() {
        return this.parent.byNameCodec();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return this.parent.iterator();
    }

    public Registry<T> getRegistry() {
        return parent;
    }
}
