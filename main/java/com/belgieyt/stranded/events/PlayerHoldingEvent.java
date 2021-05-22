package com.belgieyt.stranded.events;

import com.belgieyt.stranded.Stranded;
import com.belgieyt.stranded.init.ItemInit;
import com.belgieyt.stranded.util.Config;
import com.belgieyt.stranded.util.StrandedBook;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Stranded.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerHoldingEvent {

    @SubscribeEvent
    public static void doHeal(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        if (player.getHeldItemOffhand().getItem() == ItemInit.WALKING_STICK.get()) {
            player.abilities.setWalkSpeed(-3.0f);
        }else {
                player.abilities.setWalkSpeed(0.1F);
            }
        }

    @SubscribeEvent
    public static void doFly(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        if (player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL && player.isCreative()) {
            player.abilities.allowFlying = true;
        } else {
                player.abilities.allowFlying = false;
            }
        }
}

