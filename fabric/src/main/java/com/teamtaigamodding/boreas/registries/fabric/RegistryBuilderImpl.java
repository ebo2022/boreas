package com.teamtaigamodding.boreas.registries.fabric;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.teamtaigamodding.boreas.registries.RegistryBuilder;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Internal
public class RegistryBuilderImpl<T> implements RegistryBuilder<T> {

    private boolean save;
    private boolean sync;
    private Pair<Codec<T>, @Nullable Codec<T>> dataCodecs = null;
    private final FabricRegistryBuilder<T, MappedRegistry<T>> builder;
    private final ResourceLocation name;

    protected RegistryBuilderImpl(ResourceLocation name) {
        this.name = name;
        this.builder = FabricRegistryBuilder.from(new MappedRegistry<>(ResourceKey.createRegistryKey(name), Lifecycle.stable(), null));
    }


    @Override
    public RegistryBuilder<T> disableSaving() {
        this.save = false;
        return this;
    }

    @Override
    public RegistryBuilder<T> disableSync() {
        this.sync = false;
        return this;
    }

    @Override
    public RegistryBuilder<T> dataPackRegistry(Codec<T> codec, @Nullable Codec<T> networkCodec) {
        this.dataCodecs = Pair.of(codec, networkCodec);
        return this;
    }

    @Nullable
    public Pair<Codec<T>, @Nullable Codec<T>> getDataCodecs() {
        return this.dataCodecs;
    }

    public Registry<T> assemble() {
        if (this.sync)
            this.builder.attribute(RegistryAttribute.SYNCED);
        if (this.save)
            this.builder.attribute(RegistryAttribute.PERSISTED);
        return this.builder.buildAndRegister();
    }
}
