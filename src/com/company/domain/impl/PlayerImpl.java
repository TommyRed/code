package com.company.domain.impl;

import com.company.domain.*;
import com.company.domain.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Tomáš Rechtig on 08.10.2016.
 */
public class PlayerImpl implements Player {

    private String name;
    private int HP;
    private int strength;
    private List<Item> items;
    private List<Option> options;
    private WeaponImpl weapon;
    private ArmorImpl armor;

    public PlayerImpl(String name) {
        this.name = name;
        this.HP = 100;
        this.strength = rollDiceK6() + rollDiceK6();
        this.items = new ArrayList<>();
        this.options = new ArrayList<>();
    }

    @Override
    public void usePotion(PotionImpl potion){
        if (HP + potion.getPotionStrength() > 100) HP = 100;
        else HP += potion.getPotionStrength();
        dropItem(potion);

        Option desiredOption = null;

        for (Option option : options){
            if (option.getItem() == potion) {
                desiredOption = option;
                break;
            }
        }

        options.remove(desiredOption);
    }

    @Override
    public boolean hasPotion(){
        boolean flag = false;

        for (Item item : items){
            flag = item.getType() == ItemType.POTION;
            if (flag) break;
        }
        return flag;
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
            return weapon.getAttackNumber() + strength + rollDiceK6();
        } catch (NullPointerException err) {
            System.out.println("    " + this.name + " does not have a weapon");
        }
        return strength + rollDiceK6();
    }

    @Override
    public WeaponImpl getWeapon() {
        try {
            return weapon;
        } catch (NullPointerException err) {
            System.out.println("    " + this.name + " does not have a weapon");
        }
        return null;
    }

    @Override
    public ArmorImpl getArmor() {
        return armor;
    }

    @Override
    public void removeWeapon() {
        this.weapon = null;
    }

    @Override
    public void removeArmor() {
        this.armor = null;
    }

    @Override
    public void setArmor(ArmorImpl armor) {
        this.armor = armor;
    }

    @Override
    public void setWeapon(WeaponImpl weapon) {
        this.weapon = weapon;
    }

    @Override
    public List<Item> getItems() {
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
            printItems();
            Listener listener = new ListenerImpl();
            int option = listener.listenForIntWithin(-1, 4) - 1;

            System.out.println("@   Inventory option: " + option);

            if (option == -1) return;
            else if (items.get(option).getType().getDefaultTitle().equals("Weapon")) {
                System.out.println("Set weapon");
                setWeapon((WeaponImpl) items.get(option));
            }
            else if (items.get(option).getType().getDefaultTitle().equals("Armor")) {
                System.out.println("Set armor");
                setArmor((ArmorImpl) items.get(option));
            }
            else if (items.get(option).getType().getDefaultTitle().equals("Potion")) {
                System.out.println("Drink potion");
                usePotion((PotionImpl) items.get(option));
            }
        }
    }

    private int printItems(){
        int index = 1;
        System.out.println("Enter 0 for leaving inventory");

        if (weapon != null) System.out.println("Weapon: " + weapon.getName());
        if (armor != null) System.out.println("Armor: " + armor.getName());

        for (Item item : this.items){
            System.out.print("    - " + item.getName());
            if (item.getType() == ItemType.WEAPON) System.out.println("     " + index + ") equip weapon");
            else if (item.getType() == ItemType.ARMOR) System.out.println("     " + index + ") equip armor");
            else if (item.getType() == ItemType.POTION) System.out.println("     " + index + ") use potion");
            index++;
        }
        return index;
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
}
