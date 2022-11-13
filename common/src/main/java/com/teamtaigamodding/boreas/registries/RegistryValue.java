package com.teamtaigamodding.boreas.registries;

import com.teamtaigamodding.boreas.platform.Platform;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
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
    public static <T, R extends T> RegistryValue<R> create(CoreRegistry<T> registry, ResourceLocation name) {
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
    public static <T, R extends T> RegistryValue<R> create(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation name, String mod) {
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
    public static <T, R extends T> RegistryValue<R> create(ResourceLocation registryName, ResourceLocation name, String mod) {
        return Platform.error();
    }

    /**
     * @return The wrapped value in the registry
     */
    @Override
    public abstract T get();

    /**
     * @return The wrapped value in the registry as an {@link Optional} based on whether it's present
     */
    public Optional<T> getOptional() {
        return this.isPresent() ? Optional.of(this.get()) : Optional.empty();
    }

    /**
     * @return The name of the wrapped value
     */
    public abstract ResourceLocation getId();

    /**
     * @return A {@link ResourceKey} pointing to the wrapped value
     */
    public abstract ResourceKey<T> getKey();

    /**
     * @return A singleton stream of the wrapped value if it's present
     */
    public Stream<T> stream() {
        return this.isPresent() ? Stream.of(this.get()) : Stream.of();
    }

    /**
     * @return Whether the registry value is present
     */
    public abstract boolean isPresent();

    /**
     * Accepts the specified consumer if the mod object is present.
     *
     * @param consumer The consumer to be executed if the value is present
     */
    public void ifPresent(Consumer<? super T> consumer) {
        if (isPresent())
            consumer.accept(this.get());
    }

    /**
     * Maps the held value if it is present, then wraps it in an {@link Optional}.
     *
     * @param mapper The mapping function
     * @return An {@link Optional} with the applied function if the value was present
     */
    public <R> Optional<R> map(Function<? super T, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return Optional.empty();
        else {
            return Optional.ofNullable(mapper.apply(get()));
        }
    }

    /**
     * Maps the value if it's present using a function that directly converts to an {@link Optional}.
     *
     * @param mapper The mapping function
     * @return An {@link Optional} with the applied function if the value was present
     */
    public <R> Optional<R> flatMap(Function<? super T, Optional<R>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return Optional.empty();
        else {
            return Objects.requireNonNull(mapper.apply(get()));
        }
    }

    /**
     * Lazily applies the specified mapping function if the value is present.
     *
     * @param mapper The mapping function
     * @return A {@link Supplier} of the applied mapping function if the value was present
     */
    public <R> Supplier<R> lazyMap(Function<? super T, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return () -> isPresent() ? mapper.apply(get()) : null;
    }

    /**
     *
     * @param other the mod object to be returned if there is no mod object present, may
     * be null
     * @return The held value if present, otherwise {@code other}
     */
    public T orElse(T other) {
        return isPresent() ? get() : other;
    }

    /**
     * Return the mod object if present, otherwise invoke {@code other} and return
     * the result of that invocation.
     *
     * @param other a {@code Supplier} whose result is returned if no mod object
     * is present
     * @return the mod object if present otherwise the result of {@code other.get()}
     * @throws NullPointerException if mod object is not present and {@code other} is
     * null
     */
    public T orElseGet(Supplier<? extends T> other) {
        return isPresent() ? get() : other.get();
    }

    /**
     * Return the contained mod object, if present, otherwise throw an exception
     * to be created by the provided supplier.
     *
     * @apiNote A method reference to the exception constructor with an empty
     * argument list can be used as the supplier. For example,
     * {@code IllegalStateException::new}
     *
     * @param <X> Type of the exception to be thrown
     * @param exceptionSupplier The supplier which will return the exception to
     * be thrown
     * @return the present mod object
     * @throws X if there is no mod object present
     * @throws NullPointerException if no mod object is present and
     * {@code exceptionSupplier} is null
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (isPresent()) {
            return get();
        } else {
            throw exceptionSupplier.get();
        }
    }

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
