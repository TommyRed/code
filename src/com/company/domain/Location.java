package com.company.domain;

import java.util.List;

/**
 * Created by semanticer on 3. 10. 2016.
 */
public interface Location {
    List<Option> getOptions();
    List<Direction> getDirections();
    List<Item> getItems();

    String getText();
    boolean getSafety();
    void setSafety(boolean safety);

    void addOption(String text, int id);
    void addOption(Option option);

    void addDirection(String name, Location targetedLocation);

    void addItem(Item item);
    void addItem(String name, int durability, int attackNumber, int weight);

    void removeItem(Item item, Option option);

    Character generateEnemy(Player player);
}
