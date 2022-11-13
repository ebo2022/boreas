package com.teamtaigamodding.boreas.registries;

/**
 * A marker for classes with the purpose of housing {@link DeferredRegister}s; more specifically, custom registries.
 * <p>Implementor classes will be loaded earlier during runtime on Fabric to set up datapack registries.
 */
public interface ModRegistryHolder {
}
