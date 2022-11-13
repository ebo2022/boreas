package com.teamtaigamodding.boreas.registries.forge;

import com.teamtaigamodding.boreas.registries.CoreRegistry;
import com.teamtaigamodding.boreas.registries.RegistryManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;

@ApiStatus.Internal
public class RegistryManagerImpl extends RegistryManager {

    private final Map<IForgeRegistry<?>, CoreRegistry<?>> registries;

    private RegistryManagerImpl() {
        this.registries = new HashMap<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> CoreRegistry<T> getRegistry(ResourceLocation key) {
        return (CoreRegistry<T>) this.registries.computeIfAbsent(net.minecraftforge.registries.RegistryManager.ACTIVE.getRegistry(key), CoreRegistryImpl::new);
    }

    public static RegistryManager _get() {
        return new RegistryManagerImpl();
    }
}
