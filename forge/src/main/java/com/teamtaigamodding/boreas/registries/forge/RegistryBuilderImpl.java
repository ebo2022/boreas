package com.teamtaigamodding.boreas.registries.forge;

import com.mojang.serialization.Codec;
import com.teamtaigamodding.boreas.registries.RegistryBuilder;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Internal
public class RegistryBuilderImpl<T> implements RegistryBuilder<T> {

    private final net.minecraftforge.registries.RegistryBuilder<T> parent;

    protected RegistryBuilderImpl(net.minecraftforge.registries.RegistryBuilder<T> parent) {
        this.parent = parent;
    }

    @Override
    public RegistryBuilder<T> disableSaving() {
        this.parent.disableSaving();
        return this;
    }

    @Override
    public RegistryBuilder<T> disableSync() {
        this.parent.disableSync();
        return this;
    }

    @Override
    public RegistryBuilder<T> dataPackRegistry(Codec<T> codec, @Nullable Codec<T> networkCodec) {
        this.parent.dataPackRegistry(codec, networkCodec);
        return this;
    }

    public net.minecraftforge.registries.RegistryBuilder<T> getBuilder() {
        return this.parent;
    }
}
