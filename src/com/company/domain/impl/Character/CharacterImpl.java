package com.company.domain.impl.Character;

import com.company.domain.*;
import com.company.domain.Character;
import com.company.domain.impl.Item.ArmorImpl;
import com.company.domain.impl.Item.WeaponImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Tomáš Rechtig on 09.10.2016.
 */
public class CharacterImpl implements Character{

    private String name;
    private int HP;
    private int strength;
    private List<Item> items;
    private WeaponImpl weapon;
    private ArmorImpl armor;

    private static final int DRAKE = 1;
    private static final int ORC = 2;
    private static final int GHOST = 3;
    private static final int WOLF = 4;
    private static final int GOBLIN = 5;
    private static final int CHICKEN = 6;

    public CharacterImpl(int monsterType) {
        this.items = new ArrayList<>();
        createMonster(monsterType);
    }

    private void createMonster(int monsterType){
        switch (monsterType){
            case DRAKE:
                this.name = "Drake";
                this.HP = 80;
                this.strength = 15;
                this.weapon = (WeaponImpl) ItemType.WEAPON.createItem("Dragon's breath", 10);
                break;
            case ORC:
                this.name = "Orc";
                this.HP = 100;
                this.strength = 10;
                this.weapon = (WeaponImpl) ItemType.WEAPON.createItem("Orcish sword", 10);
                break;
            case GHOST:
                this.name = "Ghost";
                this.HP = 40;
                this.strength = 6;
                this.weapon = (WeaponImpl) ItemType.WEAPON.createItem("Ghost's claw", 10);
                break;
            case WOLF:
                this.name = "Wolf";
                this.HP = 50;
                this.strength = 8;
                this.weapon = (WeaponImpl) ItemType.WEAPON.createItem("Wolf's claw", 10);
                break;
            case GOBLIN:
                this.name = "Goblin";
                this.HP = 35;
                this.strength = 4;
                this.weapon = (WeaponImpl) ItemType.WEAPON.createItem("Goblin dagger", 10);
                break;
            case CHICKEN:
                this.name = "Epic-Chicken";
                this.HP = 10;
                this.strength = 30;
                this.weapon = (WeaponImpl) ItemType.WEAPON.createItem("Epic-Ultra Chicken's beak", 10);
                break;
        }
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
    public int getStrength() {
        return strength;
    }

    @Override
    public int getAttackNumber(){
        try {
            return weapon.getAttackNumber() + strength + rollDiceK6();
        }catch (NullPointerException err){
            System.out.println("    " + this.name + " does not have a weapon");
        }
        return strength + rollDiceK6();
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public WeaponImpl getWeapon(){
        try {
            return weapon;
        }catch (NullPointerException err){
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
    public void setWeapon(WeaponImpl weapon){
        this.weapon = weapon;
    }

    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public int rollDiceK6() {
        Random r = new Random();
        return r.nextInt(6 - 1) + 1;
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
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void suicide() {
        this.HP = 0;
    }
}
