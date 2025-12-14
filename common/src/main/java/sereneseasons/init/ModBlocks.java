package sereneseasons.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import sereneseasons.api.SSBlocks;
import sereneseasons.block.SeasonSensorBlock;
import sereneseasons.core.SereneSeasons;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class ModBlocks
{
    public static void registerBlocks(BiConsumer<Identifier, Block> func)
    {
        SSBlocks.SEASON_SENSOR = register(func, "season_sensor", SeasonSensorBlock::new, Block.Properties.of().strength(0.2F).sound(SoundType.STONE));
    }

    private static Block register(BiConsumer<Identifier, Block> func, ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties)
    {
        Block block = factory.apply(properties.setId(key));
        func.accept(key.identifier(), block);
        return block;
    }

    private static Block register(BiConsumer<Identifier, Block> func, String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties)
    {
        return register(func, blockId(name), factory, properties);
    }

    private static ResourceKey<Block> blockId(String name)
    {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SereneSeasons.MOD_ID, name));
    }
}
