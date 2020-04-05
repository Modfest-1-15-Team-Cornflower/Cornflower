package io.github.cornflower.item;

import io.github.cornflower.group.CornflowerGroup;
import io.github.cornflower.util.ModConstants;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CornflowerItems {

    public static final Item CORNFLOWER_WAND = Registry.register(Registry.ITEM, new Identifier(ModConstants.MOD_ID, "wand_cornflower"), new CornflowerWand());
    public static final Item CORNFLOWER_ESSENCE = Registry.register(Registry.ITEM, new Identifier(ModConstants.MOD_ID,"cornflower_essence"), new Item(new Item.Settings().group(CornflowerGroup.CORNFLOWER_GROUP)));

    public static void init() {}
}
