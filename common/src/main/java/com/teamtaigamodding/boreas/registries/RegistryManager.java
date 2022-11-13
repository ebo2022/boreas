package com.teamtaigamodding.boreas.registries;

import com.teamtaigamodding.boreas.platform.Platform;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;

/**
 * Manages all {@link CoreRegistry core registries}.
 *
 * @author ebo2022
 * @since 1.0.0
 */
public abstract class RegistryManager {

    /**
     * The main {@link RegistryManager} instance.
     */
    public static final RegistryManager INSTANCE = _get();

    /**
     * Gets a registry from the specified location if it exists.
     *
     * @param key The location
     * @return The registry if it exists, or <code>null</code> if not
     */
    public abstract <T> CoreRegistry<T> getRegistry(ResourceLocation key);

    /**
     * Gets a registry from the specified {@link ResourceKey} if it exists.
     *
     * @param key The key to use to find the registry
     * @return The registry if it exists, or <code>null</code> if not
     */
    public <T> CoreRegistry<T> getRegistry(ResourceKey<? extends Registry<T>> key) {
        return this.getRegistry(key.location());
    }

    @ApiStatus.Internal
    @ExpectPlatform
    private static RegistryManager _get() {
        return Platform.error();
    }
}
