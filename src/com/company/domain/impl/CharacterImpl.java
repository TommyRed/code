package com.company.domain.impl;

import com.company.domain.Character;
import com.company.domain.Item;
import com.company.domain.Option;
import com.company.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Tomáš Rechtig on 08.10.2016.
 */
public class CharacterImpl implements Player {

    private String name;
    private int HP;
    private int strength;
    private List<Item> items;
    private List<Option> options;
    private Item weapon;

    public CharacterImpl(String name) {
        this.name = name;
        this.HP = 100;
        this.strength = rollDiceK6() + rollDiceK6();
        this.items = new ArrayList<>();
        this.options = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHP() {
        return HP;
    }

    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getAttackNumber() {
        try {
            return weapon.getAttackNumber() + strength;
        } catch (NullPointerException err) {
            System.out.println("    " + this.name + " does not have a weapon");
        }
        return strength;
    }

    @Override
    public Item getWeapon() {
        try {
            return weapon;
        } catch (NullPointerException err) {
            System.out.println("    " + this.name + " does not have a weapon");
        }
        return null;
    }

    @Override
    public void setWeapon(Item item) {
        this.weapon = item;
    }

    @Override
    public List getItems() {
        return items;
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
        System.out.println("You now carry: " + item.getName());
    }

    @Override
    public void dropItem(Item item) {
        items.remove(item);
    }

    @Override
    public int rollDiceK6() {
        Random r = new Random();
        return r.nextInt(6 - 1) + 1;
    }

    @Override
    public int rollDiceK12() {
        return rollDiceK6() + rollDiceK6();
    }

    @Override
    public void printCharacterInfo() {
        System.out.println("\n  Character name: " + this.name + "\n   " +
                "Character strength: " + this.strength + "\n");
    }

    @Override
    public void printCharacterInventory() {
        //Check if inventory is empty
        if (this.items.isEmpty())
            System.out.println("\nThere are no items in " + this.name + "'s Inventory\n");
        else {
            System.out.println("\n  " + this.getName() + "'s Inventory: ");
            //For each item held by player print out its name
            items.forEach((item) -> System.out.println("        " + item.getName()));
        }
        System.out.println("\n");
    }

    @Override
    public void suicide() {
        this.HP = 0;
    }

    @Override
    public List<Option> getOptions() {
        return options;
    }

    @Override
    public void addOption(String text, int id, Item item) {
        addOption(new OptionImpl(text, id, item));
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
    public void removeOption(Option option) {
        options.remove(option);
    }

    @Override
    public void attack(Character enemy) {
        enemy.setHP(enemy.getHP() - getAttackNumber());
    }
}
