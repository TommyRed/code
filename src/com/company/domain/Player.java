package com.company.domain;

import java.util.List;

/**
 * Created by Tomáš Rechtig on 09.10.2016.
 */
public interface Player extends Character {
    List<Option> getOptions();
    void addOption(String text, int id, Item item);
    void addOption(String text, int id);
    void addOption(Option option);
    void removeOption(Option option);

    void dropItem(Item item);
}
