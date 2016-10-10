package com.company.domain;

/**
 * Created by semanticer on 3. 10. 2016.
 */
public interface Option {
    String getText();
    int getID();

    void processOption(Location location, Player character);

    void printCharacterInfo(Character character);
    void printCharacterInventory(Character character);
    void pickItem(Player character, Location location, Item item);

    void dropItem(Player character, Location location, Item item);

    void doNothing(Player player);
}
