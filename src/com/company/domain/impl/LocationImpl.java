package com.company.domain.impl;

import com.company.domain.*;
import com.company.domain.Character;
import com.company.domain.impl.Character.CharacterImpl;
import com.company.domain.impl.Option.DirectionOptionImpl;
import com.company.domain.impl.Option.ItemOptionImpl;
import com.company.domain.impl.Option.Types.ItemOptionTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by semanticer on 3. 10. 2016.
 */
public class LocationImpl implements Location {

    private String name;
    private boolean safeZone;
    private List<Option> options;
    private List<Option> directions;
    private List<Item> items;

    public LocationImpl(String name) {
        this.name = name;
        this.safeZone = false;
        this.directions = new ArrayList<>();
        this.items = new ArrayList<>();
        this.options = new ArrayList<>();
    }

    @Override
    public Character generateEnemy(Player player) {
        int playersLuck = player.rollDiceK6() + player.rollDiceK6() + player.rollDiceK6() + player.rollDiceK6();
        switch (playersLuck){
            case 2:
                return new CharacterImpl(6);
            case 6:
                return new CharacterImpl(5);
            case 10:
                return new CharacterImpl(4);
            case 14:
                return new CharacterImpl(3);
            case 18:
                return new CharacterImpl(2);
            case 24:
                return new CharacterImpl(1);
        }
        return null;
    }

    /*
     * Setters
     */
    @Override
    public void addItem(Item item) {
        items.add(item);
        options.add(new ItemOptionImpl("Pick " + item.getName(), items.get(items.size() - 1), ItemOptionTypes.PICKITEM));
    }

    @Override
    public void addDirection(String name, Location targetedLocation) {
        directions.add(new DirectionOptionImpl(name, targetedLocation));
    }

    @Override
    public void addOption(String text, Item item, ItemOptionTypes type) {
        addOption(new ItemOptionImpl(text, item, type));
    }

    @Override
    public void addOption(String text, Location location) {
        addOption(new DirectionOptionImpl(text, location));
    }

    @Override
    public void addOption(Option option) {
        options.add(option);
    }

    @Override
    public void removeOption(Option option) {
        options.remove(option);
    }

    @Override
    public void removeItem(Item item, Option option) {
        items.remove(item);
        removeOption(option);
    }

    @Override
    public void setSafety(boolean safety) {
        System.out.println("@   " + this.name + " is safe location");
        this.safeZone = safety;
    }

    /*
     *  Getters
     */
    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public List<Option> getOptions() {
        return options;
    }

    @Override
    public List<Option> getDirections() {
        return directions;
    }

    @Override
    public String getText() {
        return name;
    }

    @Override
    public boolean isSafe() {
        return safeZone;
    }

}
