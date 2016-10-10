package com.company.domain.impl;

import com.company.domain.Item;

/**
 * Created by Tomáš Rechtig on 08.10.2016.
 */
public class ItemImpl implements Item {

    private String name;
    private int durability;
    private int attackNumber;
    private int weight;

    public ItemImpl(String name, int durability, int attackNumber, int weight) {
        this.name = name;
        this.durability = durability;
        this.attackNumber = attackNumber;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAttackNumber() {
        return attackNumber;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
