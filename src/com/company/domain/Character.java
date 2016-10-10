package com.company.domain;

import java.util.List;

/**
 * Created by Tomáš Rechtig on 08.10.2016.
 */
public interface Character {
    String getName();
    int getStrength();

    int getAttackNumber();

    Item getWeapon();
    void setWeapon(Item item);

    int getHP();
    void setHP(int HP);

    int rollDiceK6();
    int rollDiceK12();

    void printCharacterInfo();
    void printCharacterInventory();

    List<Item> getItems();
    void addItem(Item item);

    void suicide();

    void attack(Character enemy);
}