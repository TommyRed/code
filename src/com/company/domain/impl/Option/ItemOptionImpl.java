package com.company.domain.impl.Option;

import com.company.domain.Item;
import com.company.domain.impl.Option.Types.ItemOptionTypes;

/**
 * Created by Tomáš Rechtig on 17.10.2016.
 */
public class ItemOptionImpl extends OptionImpl {

    private Item item;
    private ItemOptionTypes type;

    public ItemOptionImpl(String text, Item item, ItemOptionTypes type) {
        super(text);
        this.item = item;
        this.type = type;
    }

    public Item getItem(){
        return item;
    }

    public ItemOptionTypes getOptionType() {
        return type;
    }
}
