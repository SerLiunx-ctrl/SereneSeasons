/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package sereneseasons.init;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.*;
import sereneseasons.api.SSGameRules;
import sereneseasons.core.SereneSeasons;

import java.util.function.BiConsumer;
import java.util.function.ToIntFunction;

public class ModGameRules
{
    public static void registerGameRules(BiConsumer<Identifier, GameRule<?>> func)
    {
        SSGameRules.RULE_DOSEASONCYCLE = registerBoolean(func, "do_season_cycle", GameRuleCategory.UPDATES, true);
    }

    public static GameRule<Boolean> registerBoolean(BiConsumer<Identifier, GameRule<?>> func, String $$0, GameRuleCategory $$1, boolean $$2) {
        return register(func, $$0, $$1, GameRuleType.BOOL, BoolArgumentType.bool(), Codec.BOOL, $$2, FeatureFlagSet.of(), GameRuleTypeVisitor::visitBoolean, ($$0x) -> $$0x ? 1 : 0);
    }

    public static <T> GameRule<T> register(BiConsumer<Identifier, GameRule<?>> func, String p_461189_,
            GameRuleCategory p_460642_,
            GameRuleType p_460651_,
            ArgumentType<T> p_461259_,
            Codec<T> p_461238_,
            T p_460991_,
            FeatureFlagSet p_460959_,
            GameRules.VisitorCaller<T> p_460922_,
            ToIntFunction<T> p_461000_
    ) {
        return (GameRule<T>)register(func, p_461189_, new GameRule<>(p_460642_, p_460651_, p_461259_, p_460922_, p_461238_, p_461000_, p_460991_, p_460959_));
    }

    private static GameRule<?> register(BiConsumer<Identifier, GameRule<?>> func, String name, GameRule<?> rule)
    {
        func.accept(Identifier.fromNamespaceAndPath(SereneSeasons.MOD_ID, name), rule);
        return rule;
    }
}
