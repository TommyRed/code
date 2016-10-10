package com.company.domain.impl;

import com.company.domain.*;
import com.company.domain.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by semanticer on 3. 10. 2016.
 */
public class LocationImpl implements Location {

    String name;
    boolean safeZone;
    List<Option> options;
    List<Direction> directions;
    List<Item> items;

    public LocationImpl(String name) {
        this.name = name;
        this.safeZone = false;
        this.directions = new ArrayList<>();
        this.items = new ArrayList<>();
        this.options = new ArrayList<>();
    }

    @Override
    public List<Option> getOptions() {
        return options;
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }

    @Override
    public String getText() {
        return name;
    }

    @Override
    public void addOption(String text, int id) {
        addOption(new OptionImpl(text, id));
    }

    @Override
    public void addOption(Option option) {
        options.add(option);
    }

    @Override
    public boolean getSafety() {
        return safeZone;
    }

    @Override
    public void setSafety(boolean safety) {
        System.out.println(this.name + " is safe location");
        this.safeZone = safety;
    }

    @Override
    public void addDirection(String name, Location targetedLocation) {
        directions.add(new DirectionImpl(name, targetedLocation));
    }

    @Override
    public List<Item> getItems() {
        return items;
    }
    /*
        Add new item to the location
     */
    @Override
    public void addItem(String name, int durability, int attackNumber, int weight) {
        addItem(new ItemImpl(name, durability, attackNumber, weight));
    }

    /*
        Add already existing item to the location
     */
    @Override
    public void addItem(Item item) {
        items.add(item);
        options.add(new OptionImpl("Pick " + item.getName(), 3, items.get(items.size() - 1)));
    }

    @Override
    public void removeItem(Item item, Option option) {
        items.remove(item);
        options.remove(option);
    }

    @Override
    public Character generateEnemy(Player player) {
        int playersLuck = player.rollDiceK12() + player.rollDiceK12();
        switch (playersLuck){
            case 2:
                return new BeastImpl(6);
            case 6:
                return new BeastImpl(5);
            case 10:
                return new BeastImpl(4);
            case 14:
                return new BeastImpl(3);
            case 18:
                return new BeastImpl(2);
            case 24:
                return new BeastImpl(1);
        }
        return null;
    }
}
