package com.company.domain;

import com.company.domain.impl.Item.ArmorImpl;
import com.company.domain.impl.Item.PotionImpl;
import com.company.domain.impl.Item.WeaponImpl;

/**
 * Created by Tomáš Rechtig on 15.10.2016.
 */
public enum ItemType {
    WEAPON("Weapon"){
        public Item createItem(String name, int attackNumber){
            return new WeaponImpl(name, attackNumber, this);
        }
    },
    ARMOR("Armor"){
        public Item createItem(String name, int defenceNumber){
            return new ArmorImpl(name, defenceNumber, this);
        }
    },
    POTION("Potion"){
        public Item createItem(String name, int potionStrength){
            return new PotionImpl(name, potionStrength, this);
        }
    };

    private final String defaultTitle;

    ItemType(String defaultTitle){
        this.defaultTitle = defaultTitle;
    }

    public abstract Item createItem(String name, int effectiveNumber);

    public String getDefaultTitle() {
        return defaultTitle;
    }
}