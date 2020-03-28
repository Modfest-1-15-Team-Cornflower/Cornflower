/*
 * This file is part of Cornflower
 * Copyright (C) 2020, Team Cornflower.
 *
 * This code is licensed under GNU General Public License v3.0, the full license text can be found in LICENSE
 */

package io.github.cornflower.block.entity;

import io.github.cornflower.block.CornflowerCauldronBlock;
import io.github.cornflower.util.CampfireUtil;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class CornflowerCauldronBlockEntity extends BlockEntity implements Tickable {

    public CornflowerCauldronBlockEntity() {
        super(CornflowerBlockEntities.CORNFLOWER_CAULDRON);
    }

    @Override
    public void tick() {
        if((this.world != null ? this.world.getBlockState(this.pos).get(CauldronBlock.LEVEL) : 0) > 0) {
            if(CampfireUtil.isCampfireLitUnder(this.world, this.pos)) {
                CornflowerCauldronBlock.spawnBubbleParticles(this.world, this.pos, this.world.getBlockState(this.pos).get(CauldronBlock.LEVEL));
            }
        }
    }

}
