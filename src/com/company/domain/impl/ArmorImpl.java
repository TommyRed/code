package com.company.domain.impl;

import com.company.domain.ItemType;

/**
 * Created by Tomáš Rechtig on 15.10.2016.
 */
public class ArmorImpl extends ItemImpl {

    private final int defenceNumber;

    public ArmorImpl(String name, int defenceNumber, ItemType type) {
        super(name, type);
        System.out.println("@   Create new Armor");
        this.defenceNumber = defenceNumber;
    }

    public int getDefenceNumber() {
        return defenceNumber;
    }
}
