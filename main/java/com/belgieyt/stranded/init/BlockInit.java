package com.belgieyt.stranded.init;

import com.belgieyt.stranded.Stranded;
import com.belgieyt.stranded.objects.block.GunpowderBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Stranded.MOD_ID);

    public static final RegistryObject<Block> GUNPOWDER_BLOCK = BLOCKS.register("gunpowder_block", () -> new GunpowderBlock(AbstractBlock.Properties.from(Blocks.TNT)));




}
