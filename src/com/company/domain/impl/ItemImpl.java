package com.company.domain.impl;

import com.company.domain.Item;
import com.company.domain.ItemType;

/**
 * @author Tomáš Rechtig on 08.10.2016.
 */
public class ItemImpl implements Item {
    private String name;
    private ItemType type;

    public ItemImpl(String customName, ItemType type) {
        this.name = customName;
        this.type = type;
    }

    public ItemType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
