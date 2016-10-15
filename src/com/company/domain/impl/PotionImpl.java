package com.company.domain.impl;

import com.company.domain.ItemType;

/**
 * Created by Tomáš Rechtig on 15.10.2016.
 */
public class PotionImpl extends ItemImpl {

    private final int potionStrength;

    public PotionImpl(String name, int potionStrength, ItemType type) {
        super(name, type);
        System.out.println("@   Create new Potion");
        this.potionStrength = potionStrength;
    }

    public int getPotionStrength() {
        return potionStrength;
    }
}
