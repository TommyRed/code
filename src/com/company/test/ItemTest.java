package com.company.test;

import com.company.domain.Item;
import com.company.domain.ItemType;

import static org.junit.Assert.*;

/**
 * Created by Tomáš Rechtig on 15.10.2016.
 */
public class ItemTest  {

    @org.junit.Test
    public void itemName() throws Exception{
        Item item = ItemType.WEAPON.createItem("Spear", 10);

        String itemName = item.getName();

        assertEquals("Check name of item", "Spear", itemName);
    }
}
