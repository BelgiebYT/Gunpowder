package com.belgieyt.stranded.init;

import com.belgieyt.stranded.Stranded;
import com.belgieyt.stranded.objects.items.ItemBase;
import com.belgieyt.stranded.objects.items.WalkingStickItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Stranded.MOD_ID);

public static final RegistryObject<WalkingStickItem> WALKING_STICK = ITEMS.register("walking_stick", WalkingStickItem::new);
    public static final RegistryObject<Item> FIRE_CHARM = ITEMS.register("fire_charm", ItemBase::new);
    public static final RegistryObject<Item> SPEED_CHARM = ITEMS.register("speed_charm", ItemBase::new);
    public static final RegistryObject<Item> STRENGH_CHARM = ITEMS.register("strength_charm", ItemBase::new);
    public static final RegistryObject<Item> HASTE_CHARM = ITEMS.register("haste_charm", ItemBase::new);
    public static final RegistryObject<Item> REGENERATION_CHARM = ITEMS.register("regeneration_charm", ItemBase::new);
    public static final RegistryObject<Item> BLINDNESS_CHARM = ITEMS.register("blindness_charm", ItemBase::new);
    public static final RegistryObject<Item> INVISIBILTY_CHARM = ITEMS.register("invisibility_charm", ItemBase::new);
    public static final RegistryObject<Item> JUMP_CHARM = ITEMS.register("jump_charm", ItemBase::new);
    public static final RegistryObject<Item> NIGHT_VISION_CHARM = ITEMS.register("night_vision_charm", ItemBase::new);
    public static final RegistryObject<Item> SLOWNESS_CHARM = ITEMS.register("slowness_charm", ItemBase::new);
    public static final RegistryObject<Item> MINING_FATIGUE_CHARM = ITEMS.register("mining_fatigue_charm", ItemBase::new);
    public static final RegistryObject<Item> WEAKNESS_CHARM = ITEMS.register("weakness_charm", ItemBase::new);
    public static final RegistryObject<Item> NAUSEA_CHARM = ITEMS.register("nausea_charm", ItemBase::new);
    public static final RegistryObject<Item> WATER_BREATHING_CHARM = ITEMS.register("water_breathing_charm", ItemBase::new);
    public static final RegistryObject<Item> HUNGER_CHARM = ITEMS.register("hunger_charm", ItemBase::new);
    public static final RegistryObject<Item> POISON_CHARM = ITEMS.register("poison_charm", ItemBase::new);
    public static final RegistryObject<Item> WITHER_CHARM = ITEMS.register("wither_charm", ItemBase::new);
    public static final RegistryObject<Item> SLOW_FALLING_CHARM = ITEMS.register("slow_falling_charm", ItemBase::new);
    public static final RegistryObject<Item> FLY_CHARM = ITEMS.register("fly_charm", ItemBase::new);


}
