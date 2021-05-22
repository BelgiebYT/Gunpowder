package com.belgieyt.stranded.util;

import com.belgieyt.stranded.Stranded;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Config extends WorldSavedData {

    private static final String DATA_NAME = Stranded.MOD_ID;
    public static final String CATEGORY_GENERAL = "general";
    public static ForgeConfigSpec.BooleanValue GIVE_FIRST_JOIN;
    public List<UUID> givenBook = new ArrayList<UUID>();

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec SPEC;


    static {

        BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        GIVE_FIRST_JOIN = BUILDER.comment("Enables giving everyone a book the first time they join").define("giveFirstJoin", true);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    private Config() {
        super(DATA_NAME);
    }



    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync().autosave().writingMode(WritingMode.REPLACE).build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final net.minecraftforge.fml.config.ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }
    public static Config get(ServerWorld world) {
        DimensionSavedDataManager dataManager = world.getSavedData();
        Config instance = (Config) dataManager.getOrCreate(Config::new, DATA_NAME);


        if (instance == null) {
            instance = new Config();
            dataManager.set(instance);
        }

        return instance;
    }


    @Override
    public void read(CompoundNBT nbt) {
        this.givenBook.clear();
        ListNBT nbtGivenBook = nbt.getList("givenBook", 8);
        for(int i=0;i < nbtGivenBook.size();i++) {
            this.givenBook.add(UUID.fromString(nbtGivenBook.getString(i)));
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
                }
            }
        }}
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT nbtGivenBook = new ListNBT();
        for(UUID uuid : this.givenBook) {
            nbtGivenBook.add(StringNBT.valueOf(uuid.toString()));
        }
        compound.put("givenBook", nbtGivenBook);
        return compound;
    }
}

