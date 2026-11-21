package com.clocken.roguewright.registry;

import com.clocken.roguewright.Roguewright;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RWItems {
    public static final Item MUTATED_EYE = register("mutated_eye", new Item(new Item.Settings().food(FoodComponents.COOKIE)));

    public static Item register(String id, Item item) {
        Identifier itemID = Identifier.of(Roguewright.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static void initialize() {
    }
}
