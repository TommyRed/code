package com.company.domain;

import com.company.domain.impl.ArmorImpl;
import com.company.domain.impl.PotionImpl;
import com.company.domain.impl.WeaponImpl;

import java.util.List;

/**
 * Created by Tomáš Rechtig on 08.10.2016.
 */
public interface Character {
    String getName();
    int getStrength();
    int getHP();
    int getAttackNumber();
    List<Item> getItems();
    WeaponImpl getWeapon();
    ArmorImpl getArmor();

    void removeWeapon();
    void removeArmor();

    void setArmor(ArmorImpl armor);
    void setWeapon(WeaponImpl weapon);
    void setHP(int HP);

    int rollDiceK6();
    int rollDiceK12();

    void printCharacterInfo();
    void printCharacterInventory();

    void addItem(Item item);

    void suicide();
}