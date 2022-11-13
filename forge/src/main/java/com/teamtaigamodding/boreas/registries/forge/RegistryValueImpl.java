package com.teamtaigamodding.boreas.registries.forge;

import com.teamtaigamodding.boreas.registries.RegistryValue;
import com.teamtaigamodding.boreas.registries.CoreRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.ApiStatus;

import java.util.Optional;

@ApiStatus.Internal
public class RegistryValueImpl<T> extends RegistryValue<T> {

    private final RegistryObject<T> parent;

    protected RegistryValueImpl(RegistryObject<T> parent) {
        this.parent = parent;
    }

    public static <T, R extends T> RegistryValue<R> create(CoreRegistry<T> registry, ResourceLocation name) {
        return new RegistryValueImpl<>(RegistryObject.create(name, ((CoreRegistryImpl<T>) registry).getRegistry()));
    }

    public static <T, R extends T> RegistryValue<R> create(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation name, String mod) {
        return new RegistryValueImpl<>(RegistryObject.create(name, registryKey, mod));
    }

    public static <T, R extends T> RegistryValue<R> create(ResourceLocation registryName, ResourceLocation name, String mod) {
        return new RegistryValueImpl<>(RegistryObject.create(name, registryName, mod));
    }

    @Override
    public boolean isPresent() {
        return this.parent.isPresent();
    }

    @Override
    public ResourceLocation getId() {
        return this.parent.getId();
    }

    @Override
    public ResourceKey<T> getKey() {
        return this.parent.getKey();
    }

    @Override
    public Optional<Holder<T>> getHolder() {
        return this.parent.getHolder();
    }

    @Override
    public T get() {
        return this.parent.get();
    }


}
