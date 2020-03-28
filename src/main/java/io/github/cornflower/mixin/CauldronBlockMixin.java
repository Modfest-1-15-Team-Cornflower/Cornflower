/*
 * This file is part of Cornflower
 * Copyright (C) 2020, Team Cornflower.
 *
 * This code is licensed under GNU General Public License v3.0, the full license text can be found in LICENSE
 */

package io.github.cornflower.mixin;

import io.github.cornflower.block.CornflowerBlocks;
import io.github.cornflower.block.entity.CornflowerCauldronBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Clearable;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(CauldronBlock.class)
public class CauldronBlockMixin {

    //TODO replace with real crafting thing
    @Inject(method = "onUse", at = @At("TAIL"), cancellable = true)
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> ci) {
        if(world.isClient) return;
        if(world.getBlockState(pos).getBlock().equals(Blocks.CAULDRON)) {
            ItemStack itemStack = player.getStackInHand(hand);
            if(itemStack.getItem().equals(Items.CORNFLOWER)) {
                ServerWorld w = (ServerWorld)world;
                BlockEntity blockEntity = w.getBlockEntity(pos);
                Clearable.clear(blockEntity);
                world.setBlockEntity(pos, new CornflowerCauldronBlockEntity());
                w.setBlockState(pos, CornflowerBlocks.CORNFLOWER_CAULDRON.getDefaultState());
                w.updateNeighbors(pos, CornflowerBlocks.CORNFLOWER_CAULDRON);
                itemStack.decrement(1);
                ci.setReturnValue(ActionResult.SUCCESS);
                ci.cancel();
            }
        }
    }
}
