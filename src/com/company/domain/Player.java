package com.company.domain;

import com.company.domain.impl.Item.PotionImpl;
import com.company.domain.impl.Option.ItemOptionTypes;
import com.company.domain.impl.Option.PlayerOptionTypes;

import java.util.List;

/**
 * Created by Tomáš Rechtig on 09.10.2016.
 */
public interface Player extends Character {
    List<Option> getOptions();
    void addOption(String text, Item item, ItemOptionTypes type);
    void addOption(String text, PlayerOptionTypes type);
    void addOption(Option option);
    void removeOption(Option option);

    void removeItem(Item item);
    void removeItem(Item item, Option option);

    boolean hasPotion();
    boolean isAlive();
    void usePotion(PotionImpl potion);
}
