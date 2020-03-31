/*
 * This file is part of Cornflower
 * Copyright (C) 2020, Team Cornflower.
 *
 * This code is licensed under GNU General Public License v3.0, the full license text can be found in LICENSE
 */

package io.github.cornflower.client;


import io.github.cornflower.Cornflower;
import io.github.cornflower.block.CornflowerBlocks;
import io.github.cornflower.block.CornflowerCauldronBlock;
import io.github.cornflower.block.entity.CornflowerCauldronBlockEntity;
import io.github.cornflower.entity.CornflowerEntities;
import io.github.cornflower.entity.FeyEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;

@Environment(EnvType.CLIENT)
public class CornflowerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        long startTime = System.currentTimeMillis();
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            if(view != null) {
                BlockEntity be = view.getBlockEntity(pos);
                if(be instanceof CornflowerCauldronBlockEntity) {
                    switch (((CornflowerCauldronBlockEntity) be).getCraftingStage()) {
                        case CRAFTING:
                            return MaterialColor.MAGENTA.color;
                        case DONE:
                            return MaterialColor.LIME.color;
                        default:
                            break;
                    }
                }
            }
            return view != null && pos != null ? BiomeColors.getWaterColor(view, pos) : -1;
        }, CornflowerBlocks.CORNFLOWER_CAULDRON);

        EntityRendererRegistry.INSTANCE.register(CornflowerEntities.FEY, (entityRenderDispatcher, context) -> new FeyEntityRenderer(entityRenderDispatcher));

        Cornflower.LOGGER.info("[Cornflower] Client initialization complete. (Took {}ms)", System.currentTimeMillis()-startTime);
    }
}
