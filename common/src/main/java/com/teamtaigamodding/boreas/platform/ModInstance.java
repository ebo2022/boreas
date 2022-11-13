package com.teamtaigamodding.boreas.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import org.jetbrains.annotations.ApiStatus;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * An abstracted mod instance to streamline mod initialization.
 */
public abstract class ModInstance {

    private final String modId;

    protected ModInstance(String modId) {
        this.modId = modId;
    }

    public static Builder builder(String modId) {
        return new Builder(modId);
    }

    public String getModId() {
        return modId;
    }

    public abstract void load();

    public interface ParallelDispatchHandler {

        CompletableFuture<Void> enqueueWork(Runnable work);

        <T> CompletableFuture<T> enqueueWork(Supplier<T> work);
    }

    public static class Builder {

        private final String modId;

        private Runnable commonInit = () -> {
        };
        private Supplier<Runnable> clientInit = () -> () -> {
        };
        private Supplier<Runnable> serverInit = () -> () -> {
        };
        private Consumer<ParallelDispatchHandler> commonPostInit = __ -> {
        };
        private Supplier<Consumer<ParallelDispatchHandler>> clientPostInit = () -> __ -> {
        };
        private Supplier<Consumer<ParallelDispatchHandler>> serverPostInit = () -> __ -> {
        };

        private Builder(String modId) {
            this.modId = modId;
        }

        @ApiStatus.Internal
        @ExpectPlatform
        public static Platform buildImpl(String modId, Runnable commonInit, Supplier<Runnable> clientInit, Supplier<Runnable> serverInit, Consumer<ParallelDispatchHandler> commonPostInit, Supplier<Consumer<ParallelDispatchHandler>> clientPostInit, Supplier<Consumer<ParallelDispatchHandler>> serverPostInit) {
            return Platform.error();
        }

        public Builder commonInit(Runnable onCommonInit) {
            this.commonInit = onCommonInit;
            return this;
        }

        public Builder clientInit(Supplier<Runnable> onClientInit) {
            this.clientInit = onClientInit;
            return this;
        }

        public Builder serverInit(Supplier<Runnable> onServerInit) {
            this.serverInit = onServerInit;
            return this;
        }

        public Builder commonPostInit(Consumer<ParallelDispatchHandler> onCommonPostInit) {
            this.commonPostInit = onCommonPostInit;
            return this;
        }

        public Builder clientPostInit(Supplier<Consumer<ParallelDispatchHandler>> onClientPostInit) {
            this.clientPostInit = onClientPostInit;
            return this;
        }

        public Builder serverPostInit(Supplier<Consumer<ParallelDispatchHandler>> onServerPostInit) {
            this.serverPostInit = onServerPostInit;
            return this;
        }

        public Platform build() {
            return buildImpl(this.modId, this.commonInit, this.clientInit, this.serverInit, this.commonPostInit, this.clientPostInit, this.serverPostInit, this.dataInit);
        }
    }
}
