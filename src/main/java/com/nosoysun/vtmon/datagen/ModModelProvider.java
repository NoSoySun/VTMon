package com.nosoysun.vtmon.datagen;

import com.nosoysun.vtmon.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BYOHMITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.KEIRIDOLITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCHLANGHITE, Models.GENERATED);
    }
}
