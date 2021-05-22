package com.belgieyt.stranded;

import com.belgieyt.stranded.entities.StrandedTraderEntity;
import com.belgieyt.stranded.events.PlayerHoldingEvent;
import com.belgieyt.stranded.init.*;
import com.belgieyt.stranded.util.BookData;
import com.belgieyt.stranded.util.StrandedBook;
import javafx.util.Builder;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.SetNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.ForgeWorldType;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("stranded")
@Mod.EventBusSubscriber(modid = Stranded.MOD_ID)
public class Stranded {
    public static final String MOD_ID = "stranded";
    private static final Logger LOGGER = LogManager.getLogger();
    public static final RegistryObject<Item> WRITTENBOOK = RegistryObject.of(new ResourceLocation("minecraft:written_book"), ForgeRegistries.ITEMS);


    public Stranded() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        BlockInit.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BiomeInit.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModTileentityTypes.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntityTypes.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new PlayerHoldingEvent());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        DeferredWorkQueue.runLater(() -> {

            GlobalEntityTypeAttributes.put(ModEntityTypes.STRANDED_TRADER.get(), StrandedTraderEntity.setCustomAttributes().create());


        });
    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        if (name.equals(new ResourceLocation("minecraft:chests/igloo_chest")) ||
                name.equals(new ResourceLocation("minecraft:chests/simple_dungeon")) ||
                name.equals(new ResourceLocation("minecraft:chests/wooldland_mansion")) ||
                name.equals(new ResourceLocation("minecraft:chests/village_blacksmith")) ||
                name.equals(new ResourceLocation("minecraft:chests/stronghold_library")) ||
                name.equals(new ResourceLocation("minecraft:chests/stronghold_corridor")) ||
                name.equals(new ResourceLocation("minecraft:chests/stronghold_crossing")) ||
                name.equals(new ResourceLocation("minecraft:chests/abandoned_mineshaft"))) {
            LootPool.Builder poolBuilder = LootPool.builder();
            poolBuilder.name("lore");

            for (BookData book : StrandedBook.book_array) {
                for (int generation = 0; generation < 4; generation++) {
                    CompoundNBT tag = new CompoundNBT();

                    String title = "title";
                    tag.putString("author", book.author);
                    if (book.title.equals("You are stuck"))
                        tag.putString(title, "You are stuck");
                    else tag.putString(title, book.title);
                    tag.putInt("generation", generation);

                    ListNBT pages = new ListNBT();
                    pages.add(StringNBT.valueOf(String.format("{\"text\":\"%s\"}", book.title + "\n\n" + book.author)));
                    for (String bookpage : book.pages) {
                        pages.add(StringNBT.valueOf(String.format("{\"text\":\"%s\"}", bookpage)));
                    }
                    tag.put("pages", pages);

                    StandaloneLootEntry.Builder<?> item = ItemLootEntry.builder(WRITTENBOOK.get());
                    if (generation == 0) item.weight(3);
                    if (generation == 1) item.weight(30);
                    if (generation == 2) item.weight(300);
                    if (generation == 3) item.weight(666);
                    item.quality(0);
                    item.acceptFunction(SetNBT.builder(tag));

                    poolBuilder.addEntry(item);
                }
            }

            if (name.equals(new ResourceLocation("minecraft:chests/stronghold_library"))) {
                poolBuilder.rolls(new RandomValueRange(8, 16));
                poolBuilder.bonusRolls(0, 1);
                event.getTable().addPool(poolBuilder.build());
            } else {
                poolBuilder.rolls(new RandomValueRange(1));
                poolBuilder.bonusRolls(0, 1);
                event.getTable().addPool(poolBuilder.build());
            }
        }

    }
    public static ResourceLocation id(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

}