package dev.atlas.registries.forge;

import dev.atlas.registries.CoreRegistry;
import dev.atlas.registries.RegistryValue;
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

    public static <T> RegistryValue<T> create(CoreRegistry<T> registry, ResourceLocation name) {
        return new RegistryValueImpl<>(RegistryObject.create(name, ((CoreRegistryImpl<T>) registry).getRegistry()));
    }

    public static <T> RegistryValue<T> create(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation name, String mod) {
        return new RegistryValueImpl<>(RegistryObject.create(name, registryKey, mod));
    }

    public static <T> RegistryValue<T> create(ResourceLocation registryName, ResourceLocation name, String mod) {
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
    public Optional<Holder<T>> getHolder() {
        return this.parent.getHolder();
    }

    @Override
    public T get() {
        return this.parent.get();
    }
}
