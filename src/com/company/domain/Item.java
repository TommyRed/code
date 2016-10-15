package com.company.domain;

/**
 * Created by Tomáš Rechtig on 08.10.2016.
 */
public interface Item {
    String getName();
    ItemType getType();

    void setName(String name);
}
