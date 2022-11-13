package com.teamtaigamodding.boreas.registries;

import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Bootstrap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.StatType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.material.Fluid;

public final class CoreRegistries {

    private CoreRegistries() {
    }

    static {
        init();
    }

    public static final CoreRegistry<Block> BLOCKS = RegistryManager.INSTANCE.getRegistry(Keys.BLOCKS);
    public static final CoreRegistry<Fluid> FLUIDS = RegistryManager.INSTANCE.getRegistry(Keys.FLUIDS);
    public static final CoreRegistry<Item> ITEMS = RegistryManager.INSTANCE.getRegistry(Keys.ITEMS);
    public static final CoreRegistry<MobEffect> MOB_EFFECTS = RegistryManager.INSTANCE.getRegistry(Keys.MOB_EFFECTS);
    public static final CoreRegistry<SoundEvent> SOUND_EVENTS = RegistryManager.INSTANCE.getRegistry(Keys.SOUND_EVENTS);
    public static final CoreRegistry<Potion> POTIONS = RegistryManager.INSTANCE.getRegistry(Keys.POTIONS);
    public static final CoreRegistry<Enchantment> ENCHANTMENTS = RegistryManager.INSTANCE.getRegistry(Keys.ENCHANTMENTS);
    public static final CoreRegistry<EntityType<?>> ENTITY_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.ENTITY_TYPES);
    public static final CoreRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.BLOCK_ENTITY_TYPES);
    public static final CoreRegistry<ParticleType<?>> PARTICLE_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.PARTICLE_TYPES);
    public static final CoreRegistry<MenuType<?>> MENU_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.MENU_TYPES);
    public static final CoreRegistry<PaintingVariant> PAINTING_VARIANTS = RegistryManager.INSTANCE.getRegistry(Keys.PAINTING_VARIANTS);
    public static final CoreRegistry<RecipeType<?>> RECIPE_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.RECIPE_TYPES);
    public static final CoreRegistry<RecipeSerializer<?>> RECIPE_SERIALIZERS = RegistryManager.INSTANCE.getRegistry(Keys.RECIPE_SERIALIZERS);
    public static final CoreRegistry<Attribute> ATTRIBUTES = RegistryManager.INSTANCE.getRegistry(Keys.ATTRIBUTES);
    public static final CoreRegistry<StatType<?>> STAT_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.STAT_TYPES);
    public static final CoreRegistry<ArgumentTypeInfo<?, ?>> COMMAND_ARGUMENT_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.COMMAND_ARGUMENT_TYPES);

    public static final CoreRegistry<VillagerProfession> VILLAGER_PROFESSIONS = RegistryManager.INSTANCE.getRegistry(Keys.VILLAGER_PROFESSIONS);
    public static final CoreRegistry<PoiType> POI_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.POI_TYPES);
    public static final CoreRegistry<MemoryModuleType<?>> MEMORY_MODULE_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.MEMORY_MODULE_TYPES);
    public static final CoreRegistry<SensorType<?>> SENSOR_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.SENSOR_TYPES);
    public static final CoreRegistry<Schedule> SCHEDULES = RegistryManager.INSTANCE.getRegistry(Keys.SCHEDULES);
    public static final CoreRegistry<Activity> ACTIVITIES = RegistryManager.INSTANCE.getRegistry(Keys.ACTIVITIES);

    public static final CoreRegistry<WorldCarver<?>> WORLD_CARVERS = RegistryManager.INSTANCE.getRegistry(Keys.WORLD_CARVERS);
    public static final CoreRegistry<Feature<?>> FEATURES = RegistryManager.INSTANCE.getRegistry(Keys.FEATURES);
    public static final CoreRegistry<ChunkStatus> CHUNK_STATUS = RegistryManager.INSTANCE.getRegistry(Keys.CHUNK_STATUS);
    public static final CoreRegistry<BlockStateProviderType<?>> BLOCK_STATE_PROVIDER_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.BLOCK_STATE_PROVIDER_TYPES);
    public static final CoreRegistry<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.FOLIAGE_PLACER_TYPES);
    public static final CoreRegistry<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = RegistryManager.INSTANCE.getRegistry(Keys.TREE_DECORATOR_TYPES);

    public static final CoreRegistry<Biome> BIOMES = RegistryManager.INSTANCE.getRegistry(Keys.BIOMES);

    public static final class Keys {

        private Keys() {
        }

        public static final ResourceKey<Registry<Block>>  BLOCKS  = key("block");
        public static final ResourceKey<Registry<Fluid>>  FLUIDS  = key("fluid");
        public static final ResourceKey<Registry<Item>>   ITEMS   = key("item");
        public static final ResourceKey<Registry<MobEffect>> MOB_EFFECTS = key("mob_effect");
        public static final ResourceKey<Registry<Potion>> POTIONS = key("potion");
        public static final ResourceKey<Registry<Attribute>> ATTRIBUTES = key("attribute");
        public static final ResourceKey<Registry<StatType<?>>> STAT_TYPES = key("stat_type");
        public static final ResourceKey<Registry<ArgumentTypeInfo<?, ?>>> COMMAND_ARGUMENT_TYPES = key("command_argument_type");
        public static final ResourceKey<Registry<SoundEvent>> SOUND_EVENTS = key("sound_event");
        public static final ResourceKey<Registry<Enchantment>> ENCHANTMENTS = key("enchantment");
        public static final ResourceKey<Registry<EntityType<?>>> ENTITY_TYPES = key("entity_type");
        public static final ResourceKey<Registry<PaintingVariant>> PAINTING_VARIANTS = key("painting_variant");
        public static final ResourceKey<Registry<ParticleType<?>>> PARTICLE_TYPES = key("particle_type");
        public static final ResourceKey<Registry<MenuType<?>>> MENU_TYPES = key("menu");
        public static final ResourceKey<Registry<BlockEntityType<?>>> BLOCK_ENTITY_TYPES = key("block_entity_type");
        public static final ResourceKey<Registry<RecipeType<?>>> RECIPE_TYPES = key("recipe_type");
        public static final ResourceKey<Registry<RecipeSerializer<?>>> RECIPE_SERIALIZERS = key("recipe_serializer");
        public static final ResourceKey<Registry<VillagerProfession>> VILLAGER_PROFESSIONS = key("villager_profession");
        public static final ResourceKey<Registry<PoiType>> POI_TYPES = key("point_of_interest_type");
        public static final ResourceKey<Registry<MemoryModuleType<?>>> MEMORY_MODULE_TYPES = key("memory_module_type");
        public static final ResourceKey<Registry<SensorType<?>>> SENSOR_TYPES = key("sensor_type");
        public static final ResourceKey<Registry<Schedule>> SCHEDULES = key("schedule");
        public static final ResourceKey<Registry<Activity>> ACTIVITIES = key("activity");
        public static final ResourceKey<Registry<WorldCarver<?>>> WORLD_CARVERS = key("worldgen/carver");
        public static final ResourceKey<Registry<Feature<?>>> FEATURES = key("worldgen/feature");
        public static final ResourceKey<Registry<ChunkStatus>> CHUNK_STATUS = key("chunk_status");
        public static final ResourceKey<Registry<BlockStateProviderType<?>>> BLOCK_STATE_PROVIDER_TYPES = key("worldgen/block_state_provider_type");
        public static final ResourceKey<Registry<FoliagePlacerType<?>>> FOLIAGE_PLACER_TYPES = key("worldgen/foliage_placer_type");
        public static final ResourceKey<Registry<TreeDecoratorType<?>>> TREE_DECORATOR_TYPES = key("worldgen/tree_decorator_type");

        public static final ResourceKey<Registry<Biome>> BIOMES = key("worldgen/biome");

        private static <T> ResourceKey<Registry<T>> key(String name) {
            return ResourceKey.createRegistryKey(new ResourceLocation(name));
        }

        private static void init() {
        }
    }

    /**
     * This function is just to make sure static initializers in other classes have run and setup their registries before we query them.
     */
    private static void init() {
        Keys.init();
        Bootstrap.bootStrap();
    }
}
