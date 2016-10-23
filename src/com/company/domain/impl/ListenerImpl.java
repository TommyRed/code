package com.company.domain.impl;

import com.company.domain.*;
import com.company.domain.Character;

import java.util.*;

/**
 * Created by Tomáš Rechtig on 16.10.2016.
 */
public class ListenerImpl implements Listener {

    private Scanner scanner;

    public ListenerImpl() {
        this.scanner = new Scanner(System.in);
    }

    /*
     *  "on" event listener functions
     */

    //  Game related functions
    @Override
    public void onEndGame(Player player) {
        System.out.println("@   EndGame");
        System.out.println("Great game " + player.getName());
        System.out.println("    Cya next time!");
    }

    @Override
    public void onNewRound(Location location, Player player, List<Option> options) {
        System.out.println("\n    <- " + location.getText() + " ->");
        printItems(location);
        printOptions(options);
    }

    //  Combat related functions
    @Override
    public void onNewCombat(Character character) {
        System.out.println("\n\n    You were attacked by " + character.getName());
    }

    @Override
    public void onNewCombatRound(Player player, Character enemy, List<Option> options) {
        System.out.println(player.getName() + "'s HP: " + player.getHP() + "hp\n" +
                enemy.getName() + "'s HP: " + enemy.getHP() + "hp");
        int index = 1;
        for (Option option : options) {
            System.out.println(index + ") " + option.getName());
            index++;
        }
        System.out.println("Select option");
    }

    @Override
    public void onCombatInitiative(Character character, int initiative){
        System.out.println(character.getName() + "'s initiative: " + initiative);
    }

    @Override
    public void onAttack(Character character, int damage) {
        System.out.println(character.getName() + "'s HP: " + character.getHP() + " | -" + damage);
    }

    /*
     *  Utility listener functions
     */
    @Override
    public int listenForInt() {
        int userInput;
        try{
            userInput = scanner.nextInt();
        }catch(InputMismatchException err){
            System.out.println("Invalid input!");
            scanner.next();
            return listenForInt();
        }
        return userInput;
    }

    @Override
    public int listenForIntWithin(int from, int to) {
        int userInput;
        try{
            userInput = scanner.nextInt();
        }catch(InputMismatchException err){
            System.out.println("Invalid input!");
            scanner.next();
            return listenForIntWithin(from, to);
        }

        if (checkWithin(from, to, userInput)) return userInput;
        else System.out.println("Invalid input!"); return listenForIntWithin(from, to);
    }

    @Override
    public boolean checkWithin(int from, int to, int actualNum) {
        return (from < actualNum && actualNum < to);
    }


    private int roundOptions(Player player){
        System.out.println("1) Suicide");
        System.out.println("2) Attack");
        if (player.hasPotion()) System.out.println("3) Drink potion");
        return listenForIntWithin(0, 4);
    }

    private void printItems(Location location) {
        if (!location.getItems().isEmpty()) {
            location.getItems().forEach((item) -> System.out.println("\n...You see " + item.getName()));
        }
    }

    private int printOptions(List<Option> options) {
        int index = 1;
        System.out.println("\nAvailable options");
        for (Option option : options) {
            System.out.println("    " + index + ") " + option.getName());
            index++;
        }
        return index;
    }
}