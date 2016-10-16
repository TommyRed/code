package com.company.domain.impl;

import com.company.domain.Character;
import com.company.domain.Listener;
import com.company.domain.Option;
import com.company.domain.Player;

import java.util.*;

/**
 * Created by Tomáš Rechtig on 16.10.2016.
 */
public class ListenerImpl implements Listener {

    private Scanner scanner;

    public ListenerImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int onNewRound(Player player, Character enemy) {
        System.out.println(player.getName() + "'s HP: " + player.getHP() + "hp\n" +
                enemy.getName() + "'s HP: " + enemy.getHP() + "hp");
        return roundOptions(player);
    }

    private int roundOptions(Player player){
        System.out.println("1) Suicide");
        System.out.println("2) Attack");
        if (player.hasPotion()) System.out.println("3) Drink potion");
        return listenForIntWithin(0, 4);
    }

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

    @Override
    public void onAttack(Character character, int damage) {
        System.out.println(character.getName() + "'s HP: " + character.getHP() + " | -" + damage);
    }
}
