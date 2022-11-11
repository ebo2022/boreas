package com.teamtaigamodding.boreas.registries.forge;

import com.mojang.serialization.Codec;
import com.teamtaigamodding.boreas.registries.CoreRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@ApiStatus.Internal
public class CoreRegistryImpl<T> implements CoreRegistry<T> {

    private final IForgeRegistry<T> parent;

    public CoreRegistryImpl(IForgeRegistry<T> parent) {
        this.parent = parent;
    }

    @Override
    public ResourceLocation getKey(T value) {
        return this.parent.getKey(value);
    }

    @Override
    public int getId(@Nullable T value) {
        return ((ForgeRegistry<T>) this.parent).getID(value);
    }

    @Override
    public @Nullable T get(@Nullable ResourceLocation name) {
        return this.parent.getValue(name);
    }

    @Override
    public @Nullable T byId(int id) {
        return ((ForgeRegistry<T>) this.parent).getValue(id);
    }

    @Override
    public ResourceKey<? extends Registry<T>> key() {
        return this.parent.getRegistryKey();
    }

    @Override
    public Set<ResourceLocation> keySet() {
        return this.parent.getKeys();
    }

    @Override
    public Set<Map.Entry<ResourceKey<T>, T>> entrySet() {
        return this.parent.getEntries();
    }

    @Override
    public boolean containsKey(ResourceLocation name) {
        return this.parent.containsKey(name);
    }

    @Override
    public Codec<T> byNameCodec() {
        return this.parent.getCodec();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return this.parent.iterator();
    }

    public IForgeRegistry<T> getRegistry() {
        return this.parent;
    }
}
