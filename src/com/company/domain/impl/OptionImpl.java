package com.company.domain.impl;

import com.company.domain.*;
import com.company.domain.Character;

/**
 * Created by Tomáš Rechtig on 07.10.2016.
 */
public class OptionImpl implements Option {

    private String text;
    private int idOfOption;
    private Item referToItem;

    /*
        Some options depends on preset constants which are compared to id of called option
     */
    private static final int DONOTHING = 0;
    private static final int CHARACTERINFO = 1;
    private static final int CHARACTERINVENTORY = 2;
    private static final int PICK = 3;
    private static final int DROP = 4;
    private static final int SUICIDE = 66;

    /*
        Constructor used for options that do not refer to item
     */
    public OptionImpl(String text, int id) {
        this.text = text;
        this.idOfOption = id;
    }

    /*
        Constructor used for options that are refering to item
     */
    public OptionImpl(String text, int id, Item item) {
        this.text = text;
        this.idOfOption = id;
        this.referToItem = item;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int getID() {
        return idOfOption;
    }

    @Override
    public void printCharacterInfo(Character character) {
        character.printCharacterInfo();
    }

    @Override
    public void printCharacterInventory(Character character) {
        character.printCharacterInventory();
    }

    /*
        Remove item from location
        Add item to players inventory
     */
    @Override
    public void pickItem(Player player, Location location, Item item) {
        if (item.getWeight() > player.getStrength()){
            System.out.println("This item is too heavy for you : ((");
        } else{
            player.addItem(item);
            location.removeItem(item, this);
            player.addOption("Drop " + item.getName(), 4, item);
        }
    }
    /*
        Remove item from players inventory
        Add item to location
     */
    @Override
    public void dropItem(Player player, Location location, Item item) {
        player.dropItem(item);
        player.removeOption(this);
        location.addItem(item);
    }

    @Override
    public void doNothing(Player player) {
        if (player.getHP() < 90)
            player.setHP(player.getHP() + 10);
        else
            player.setHP(100);
    }

    /*
            Processing preset options
         */
    @Override
    public void processOption(Location location, Player player) {
        switch(this.idOfOption){
            case SUICIDE:
                player.suicide();
                break;
            case CHARACTERINFO:
                player.printCharacterInfo();
                break;
            case CHARACTERINVENTORY:
                player.printCharacterInventory();
                break;
            case PICK:
                pickItem(player, location, referToItem);
                break;
            case DROP:
                dropItem(player, location, referToItem);
                break;
            case DONOTHING:
                doNothing(player);
                break;
            default:
                break;
        }
    }
}
