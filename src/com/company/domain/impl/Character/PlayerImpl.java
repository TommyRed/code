package com.company.domain.impl.Character;

import com.company.domain.*;
import com.company.domain.impl.Item.ArmorImpl;
import com.company.domain.impl.Item.PotionImpl;
import com.company.domain.impl.Item.WeaponImpl;
import com.company.domain.impl.ListenerImpl;
import com.company.domain.impl.Option.*;
import com.company.domain.impl.Option.Types.ItemOptionTypes;
import com.company.domain.impl.Option.Types.PlayerOptionTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        this.HP = 60;
        this.strength = rollDiceK6() + rollDiceK6();
        this.items = new ArrayList<>();
        this.options = new ArrayList<>();
    }

    @Override
    public void usePotion(PotionImpl potion){
        if (HP + potion.getPotionStrength() > 100) HP = 100;
        else HP += potion.getPotionStrength();
        removeItem(potion);

        for (Option option : options){
            if (option instanceof ItemOptionImpl && ((ItemOptionImpl) option).getItem() == potion) {
                options.remove(option);
                break;
            }
        }
    }

    private int printItems(){
        int index = 1;
        System.out.println("Enter 0 for leaving inventory");

        if (weapon != null) System.out.println("     Weapon: " + weapon.getName());
        if (armor != null) System.out.println("     Armor: " + armor.getName());

        for (Item item : this.items){
            System.out.print("    - " + item.getName());
            if (item.getType() == ItemType.WEAPON && item != weapon) System.out.println("     " + index + ") equip weapon");
            else if (item.getType() == ItemType.ARMOR && item != armor) System.out.println("     " + index + ") equip armor");
            else if (item.getType() == ItemType.POTION) System.out.println("     " + index + ") use potion");
            index++;
        }
        return index;
    }

    @Override
    public int rollDiceK6() {
        Random r = new Random();
        return r.nextInt(6 - 1) + 1;
    }

    @Override
    public void printCharacterInfo() {
        System.out.println(this.name + "\n   " +
                this.name + "'s strength: " + this.strength + "\n   " +
                this.name + "'s HP: " + this.HP);
    }

    @Override
    public void printCharacterInventory() {
        //Check if inventory is empty
        if (this.items.isEmpty())
            System.out.println("\nThere are no items in " + this.name + "'s Inventory\n");
        else {
            System.out.println("\n  " + this.getName() + "'s Inventory: ");
            printItems();
            int option = new ListenerImpl().listenForIntWithin(-1, 4) - 1;

            System.out.println("@   Inventory option: " + option);

            if (option == -1) return;
            else if (items.get(option).getType() == ItemType.WEAPON) {
                System.out.println("Set weapon");
                setWeapon((WeaponImpl) items.get(option));
            }
            else if (items.get(option).getType() == ItemType.ARMOR) {
                System.out.println("Set armor");
                setArmor((ArmorImpl) items.get(option));
            }
            else if (items.get(option).getType() == ItemType.POTION) {
                System.out.println("Drink potion");
                usePotion((PotionImpl) items.get(option));
            }
        }
    }

    @Override
    public void suicide() {
        this.HP = 0;
    }

    /*
     *  Removes
     */
    @Override
    public void removeOption(Option option) {
        options.remove(option);
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
    public void removeItem(Item item, Option option) {
        removeItem(item);
        removeOption(option);
    }

    @Override
    public void removeItem(Item item) {
        items.remove(item);
        if (item == weapon) removeWeapon();
        else if (item == armor) removeArmor();
    }

    /*
     *  Setters + Adders
     */
    @Override
    public void setArmor(ArmorImpl armor) {
        this.armor = armor;
    }

    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public void setWeapon(WeaponImpl weapon) {
        this.weapon = weapon;
    }

    @Override
    public void addOption(String text, Item item, ItemOptionTypes type) {
        addOption(new ItemOptionImpl(text, item, type));
    }

    @Override
    public void addOption(String text, PlayerOptionTypes type) {
        addOption(new PlayerOptionImpl(text, this, type));
    }

    @Override
    public void addOption(Option option) {
        options.add(option);
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
        System.out.println("You now carry: " + item.getName());
        addOption("Drop " + item.getName(), item, ItemOptionTypes.DROPITEM);
    }

    /*
     *  Getters
     */
    @Override
    public boolean isAlive() {
        return HP > 0;
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
    public List<Item> getItems() {
        return items;
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
    public List<Option> getOptions() {
        return options;
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
    public PotionImpl getPotion(){
        for (Item item : items) {
            if (item instanceof PotionImpl) return (PotionImpl) item;
        }
        return null;
    }
}
