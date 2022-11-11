package dev.atlas.registries;

import com.mojang.serialization.Codec;
import org.jetbrains.annotations.Nullable;

/**
 * A builder to create a custom platform-specific registry. Includes methods that are valid only on both platforms.
 *
 * @param <T> The registry type
 */
public interface RegistryBuilder<T> {

    RegistryBuilder<T> disableSaving();

    RegistryBuilder<T> disableSync();

    RegistryBuilder<T> dataPackRegistry(Codec<T> codec, @Nullable Codec<T> networkCodec);

    default RegistryBuilder<T> dataPackRegistry(Codec<T> codec) {
        return this.dataPackRegistry(codec, null);
    }
}
