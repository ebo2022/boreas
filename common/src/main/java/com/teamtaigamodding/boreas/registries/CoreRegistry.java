package com.teamtaigamodding.boreas.registries;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Keyable;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A "read-only" wrapper for platform-specific registries.
 *
 * @param <T> The registry type
 * @see CommonRegistries
 * @see DeferredRegister
 */
public interface CoreRegistry<T> extends Iterable<T>, Keyable {

    ResourceLocation getKey(T value);

    int getId(@Nullable T value);

    @Nullable
    T get(@Nullable ResourceLocation name);

    @Nullable
    T byId(int id);

    default Optional<T> getOptional(@Nullable ResourceLocation name) {
        return Optional.ofNullable(this.get(name));
    }

    ResourceKey<? extends Registry<T>> key();

    default Optional<T> byIdOptional(int id) {
        return Optional.ofNullable(this.byId(id));
    }

    Set<ResourceLocation> keySet();

    Set<Map.Entry<ResourceKey<T>, T>> entrySet();

    default Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    boolean containsKey(ResourceLocation name);

    Codec<T> byNameCodec();

    @Override
    default <T1> Stream<T1> keys(DynamicOps<T1> ops) {
        return this.keySet().stream().map(rl -> ops.createString(rl.toString()));
    }


}
