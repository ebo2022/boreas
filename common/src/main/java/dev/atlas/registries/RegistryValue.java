package dev.atlas.registries;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.atlas.platform.Platform;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A lazy reference to a registered object.
 *
 * @param <T> The object type
 */
public abstract class RegistryValue<T> implements Supplier<T> {

    /**
     * Creates a {@link RegistryValue} for cases in which the registry being searched is known.
     * @param registry The registry to look in
     * @param name     The name of the object to reference
     * @return A new {@link RegistryValue}
     */
    @ExpectPlatform
    static <T> RegistryValue<T> create(CoreRegistry<T> registry, ResourceLocation name) {
        return Platform.error();
    }

    /**
     * Creates a {@link RegistryValue} in which the parent registry is not defined. It will be populated once the registry is ready if it exists.
     *
     * @param registryKey A {@link ResourceKey} pointing to the registry
     * @param name        The name of the object to reference
     * @param mod         The mod calling context (used on Forge only)
     * @return A new {@link RegistryValue}
     */
    @ExpectPlatform
    public static <T> RegistryValue<T> create(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation name, String mod) {
        return Platform.error();
    }

    /**
     * Creates a {@link RegistryValue} in which the parent registry is not defined. It will be populated once the registry is ready if it exists.
     *
     * @param registryName A {@link ResourceLocation} pointing to the registry
     * @param name        The name of the object to reference
     * @param mod         The mod calling context (used on Forge only)
     * @return A new {@link RegistryValue}
     */
    @ExpectPlatform
    public static <T> RegistryValue<T> create(ResourceLocation registryName, ResourceLocation name, String mod) {
        return Platform.error();
    }

    public Stream<T> stream() {
        return this.isPresent() ? Stream.of(this.get()) : Stream.of();
    }

    public Optional<T> optional() {
        return this.isPresent() ? Optional.of(this.get()) : Optional.empty();
    }

    public abstract boolean isPresent();

    public abstract ResourceLocation getId();

    public abstract Optional<Holder<T>> getHolder();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj instanceof RegistryValue<?> && Objects.equals(((RegistryValue<?>) obj).getId(), this.getId());
        }
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
