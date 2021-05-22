package com.belgieyt.stranded.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StrandedBook {


   public static BookData[] book_array = {
            new BookData(){{
                author = "STRANDED TRADER";
                title = "You are stuck";
                pages = new String[] {
                        "You wake up one day stuck in this world you now need to explore the deep depths and see what you find, you will be able to find more books from other sadly lost people :(",
                        "The books are most common in structures like shipwrecks and woodlands, you need to get the the ESCAPED dimension to escape but it won't be easy as you need 9 different blocks",
                        "each block is NOT easy to craft and you will also find me in different places around the map, i will trade some really good items that will help escape quicker"
                        };
            }},
           new BookData(){{
               author = "THE OLD DEAD";
               title = "one clue";
               pages = new String[] {
                       "",
                       "",
                       ""
               };
           }},
    };

}

