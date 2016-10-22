package com.company.domain.impl;

import com.company.domain.Item;

/**
 * Created by Tomáš Rechtig on 17.10.2016.
 */
public class ItemOptionImpl extends OptionImpl{

    private Item item;

    public ItemOptionImpl(String text, Item item) {
        super(text);
        this.item = item;
    }

    public Item getItem(){
        return item;
    }
}
