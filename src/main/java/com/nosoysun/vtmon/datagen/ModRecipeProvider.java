package com.nosoysun.vtmon.datagen;

import com.cobblemon.mod.common.CobblemonItems;
import com.github.yajatkaul.mega_showdown.item.MegaShowdownItems;
import com.nosoysun.vtmon.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        // BYOHMITE
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BYOHMITE)
                .pattern(" # ")
                .pattern("OWO")
                .pattern(" S ")
                .input('#', CobblemonItems.PUNCHING_GLOVE)
                .input('O', Items.LIGHTNING_ROD)
                .input('W', (ItemConvertible) MegaShowdownItems.MEGA_STONE)
                .input('S', Items.EMERALD)
                .criterion(hasItem((ItemConvertible) MegaShowdownItems.MEGA_STONE),conditionsFromItem((ItemConvertible) MegaShowdownItems.MEGA_STONE))
                .offerTo(exporter);


        // KEIRIDOLITE
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.KEIRIDOLITE)
                .pattern(" # ")
                .pattern("OWO")
                .pattern(" S ")
                .input('#', Items.NOTE_BLOCK)
                .input('O', Items.BREAD)
                .input('W', (ItemConvertible) MegaShowdownItems.MEGA_STONE)
                .input('S', Items.DIAMOND)
                .criterion(hasItem((ItemConvertible) MegaShowdownItems.MEGA_STONE), conditionsFromItem((ItemConvertible) MegaShowdownItems.MEGA_STONE))
                .offerTo(exporter);


        // SCHLANGHITE (aquí viene lo especial 👇)
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SCHLANGHITE)
                .pattern(" # ")
                .pattern("OWK")
                .pattern(" S ")
                .input('#', Items.NETHER_STAR)
                .input('O', Items.GHAST_TEAR)
                .input('W', (ItemConvertible) MegaShowdownItems.MEGA_STONE)
                .input('K', Items.SOUL_SAND)
                .input('S', Items.NETHERITE_BLOCK)
                .criterion(hasItem((ItemConvertible) MegaShowdownItems.MEGA_STONE), conditionsFromItem((ItemConvertible) MegaShowdownItems.MEGA_STONE))
                .offerTo(exporter);
    }
}
