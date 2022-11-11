package com.teamtaigamodding.boreas.registries.forge;

import com.teamtaigamodding.boreas.registries.RegistryBuilder;
import com.teamtaigamodding.boreas.registries.RegistryValue;
import com.teamtaigamodding.boreas.registries.CoreRegistry;
import com.teamtaigamodding.boreas.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@ApiStatus.Internal
public final class DeferredRegisterImpl<T> extends DeferredRegister<T> {

    private final net.minecraftforge.registries.DeferredRegister<T> parent;

    public static <T> DeferredRegister<T> create(CoreRegistry<T> registry, String namespace) {
        return new DeferredRegisterImpl<>(registry.key(), namespace);
    }

    public static <T> DeferredRegister<T> create(ResourceKey<? extends Registry<T>> key, String namespace) {
        return new DeferredRegisterImpl<>(key, namespace);
    }

    private DeferredRegisterImpl(ResourceKey<? extends Registry<T>> registryKey, String namespace) {
        super(registryKey, namespace);
        this.parent = net.minecraftforge.registries.DeferredRegister.create(registryKey, namespace);
    }

    @Override
    public <R extends T> RegistryValue<R> register(String name, Supplier<R> object) {
        return new RegistryValueImpl<>(this.parent.register(name, object));
    }

    @Override
    public Supplier<CoreRegistry<T>> makeRegistry(Consumer<RegistryBuilder<T>> builder) {
        RegistryBuilderImpl<T> impl = new RegistryBuilderImpl<>(new net.minecraftforge.registries.RegistryBuilder<>());
        builder.accept(impl);
        return new CoreRegistryHolder<>(this.parent.makeRegistry(impl::getBuilder));
    }

    @Override
    public Collection<RegistryValue<T>> getEntries() {
        return this.parent.getEntries().stream().map(RegistryValueImpl::new).collect(Collectors.toList());
    }

    private static class CoreRegistryHolder<T> implements Supplier<CoreRegistry<T>> {

        private final Supplier<IForgeRegistry<T>> supplier;
        private CoreRegistry<T> registry = null;

        private CoreRegistryHolder(Supplier<IForgeRegistry<T>> supplier) {
            this.supplier = supplier;
        }

        @Override
        public CoreRegistry<T> get() {
            // Get the registry instance and store it to avoid duplicates
            if (this.registry == null)
                this.registry = new CoreRegistryImpl<>(supplier.get());
            return this.registry;
        }
    }
}
