package com.company.domain;

import com.company.domain.impl.Option.ItemOptionTypes;

import java.util.List;

/**
 * Created by semanticer on 3. 10. 2016.
 */
public interface Location {
    List<Option> getOptions();
    List<Option> getDirections();
    List<Item> getItems();

    String getText();
    boolean isSafe();
    void setSafety(boolean safety);

    void addOption(String name, Item item, ItemOptionTypes type);
    void addOption(String name, Location location);
    void addOption(Option option);
    void removeOption(Option option);

    void addDirection(String name, Location targetedLocation);

    void addItem(Item item);
    void removeItem(Item item, Option option);

    Character generateEnemy(Player player);
}
