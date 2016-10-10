package com.company.ui;

import com.company.domain.*;
import com.company.domain.Character;
import com.company.domain.impl.BeastImpl;
import com.company.domain.impl.OptionImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by semanticer on 3. 10. 2016.
 */
public class GameUI {

    //Array used for splitting directions / location options / player options
    private int splitIndex[] = new int[2];

    public void play(Location location, Player player) {
        //Error more for programmer to know that he fooked up starting location
        if (location == null) {
            throw new IllegalArgumentException("startingLocation cannot be null");
        }

        //Print name of current location
        System.out.println("\n\n#-# Current location: " + location.getText() + " #-#");

        /*
            Check if location is safezone
                true    -> Do not generate enemy
                false   -> Generate enemy if player rolls preset values
        */
        if (!location.getSafety()) {

            //Player rolls dices and based on its outcome generate enemy
            Character enemy = location.generateEnemy(player);

            //Check if enemy were generated
            if (enemy != null) {

                //Call for combat function and wait for it's outcome
                if (combat(enemy, player)) {
                    //Print name of location after combat is done
                    System.out.println("\n\n#-# Current location: " + location.getText() + " #-#");
                }
            }
        }

        //Print options and direction only if player is alive
        if (player.getHP() > 0) {
            presentLocation(location, player);
        }

        //Setup default location
        Location nextLocation = location;

        //Setup default option
        Option option = player.getOptions().get(0);

        /*
            Check if player is alive
                true    -> offer options
                false   -> proceed
         */
        if (player.getHP() >= 0) {
            //Scan for user input
            Scanner sc = new Scanner(System.in);
            System.out.println("\nEnter option from above\n");

            //Scan user's input and subtract 1
            int inputUser = sc.nextInt() - 1;

            //Check if user entered number equal to one of direction idexes
            if (inputUser < splitIndex[0]) {
                //
                nextLocation = askForDirection(inputUser, location).getLocation();
            } else {
                option = askForOption(inputUser - splitIndex[0], location);

                //If option equals null => there is no option for current location
                if (option == null) {
                    option = askForOption(inputUser - splitIndex[1], player);
                }
                //Process preset options
                option.processOption(location, player);
            }
        }

        //Check whether player is dead or not
        if (endGame(player)) {
            //Player died -> inform about end
            System.out.println("\n\nThe End");
        } else {
            //player is still alive -> call for play function
            play(nextLocation, player);
        }
    }

    private void presentLocation(Location location, Player player) {

        // If any item is located in this location -> print it out
        if (!location.getItems().isEmpty()) {
            //Print each item
            location.getItems().forEach((item) -> System.out.println("\n......You see " + item.getName()));
        }

        //   Index is used for indexing both directions and options
        int index = 1;

        //Print header for directions
        System.out.println("\nAvailable directions from this room");

        //Loop through all directions available from location
        for (Direction direction : location.getDirections()) {
            //Print each direction option
            System.out.println(index + ") " + direction.getName());
            //Add 1 to index for indexing both direction and options
            index++;
        }

        //splitIndex[0] is used to split directions and options
        splitIndex[0] = index - 1;

        //Print header for options
        System.out.println("\nAvailable options for this room");

        //Check whether there are any options in player's location
        if (!location.getOptions().isEmpty()) {
            //Print options available for location
            index = printOptions(location.getOptions(), index);
        }

        //splitIndex[1] is used to split options for player and location
        splitIndex[1] = index - 1;

        //Check whether there are any options for player
        if (!player.getOptions().isEmpty()) {
            //Print options available for player
            printOptions(player.getOptions(), index);
        } else {
            System.out.println("    ...there are no options for this room");
        }
    }

    private int printOptions(List<Option> options, int index) {
        for (Option option : options) {
            //Print each option
            System.out.println(index + ") " + option.getText());
            index++;
        }
        return index;
    }

    //Ask for location options
    private Option askForOption(int index, Location location) {
        //If there is no available option return null
        try {
            return location.getOptions().get(index);
        } catch (IndexOutOfBoundsException err) {
            return null;
        }
    }

    private Option askForOption(int index, Player player) {
        //Return option for player based on chosen index
        return player.getOptions().get(index);
    }

    private Direction askForDirection(int index, Location location) {
        //Return direction based on chosen index
        return location.getDirections().get(index);
    }

    private boolean endGame(Character player) {
        if (player.getHP() <= 0)
            return true;

        return false;
    }

    private boolean combat(Character enemy, Player player) {
        //Setup scanner
        Scanner sc = new Scanner(System.in);
        //Setup value for indexing rounds
        int roundIndex = 1;

        System.out.println("\n\n    You were attacked by " + enemy.getName() + "\n\n");

        /*
            Call for recursive combat round and wait for it's outcome
                true    -> Player survived and killed enemy
                false   -> Player was succesfully killed by the enemy
         */
        if (combatRound(player, enemy, sc, roundIndex)) {
            System.out.println("\n    Well fought!\nyou survived with: " + player.getHP() + " hp");
            return true;
        } else {
            System.out.println("\nWell fought, but you died and " + enemy.getName() + " raped your wife!");
            return false;
        }
    }

    private boolean combatRound(Player player, Character enemy, Scanner sc, int roundIndex) {
        int selectedOption = combatOptions(roundIndex, sc);
        roundIndex++;

        /*
            Player and enemy are rolling for initiative
            one with higher initiative attacks first
         */
        int playerInitiative = player.rollDiceK6();
        int enemyInitiative = enemy.rollDiceK6();

        //If player selected suicide -> kill him
        if (selectedOption == 2)
            player.suicide();

        //If player selected to attack proceed inside of condition
        if (selectedOption == 1) {

            //Setup attack values for both characters
            int playersAtatck = enemy.rollDiceK6() - (player.getAttackNumber() + player.rollDiceK6());
            int enemysAttack = player.rollDiceK6() - (enemy.getAttackNumber() + enemy.rollDiceK6());

            //If player has higher initiative => player attacks first
            if (playerInitiative > enemyInitiative) {
                //Player attacks first
                System.out.println(player.getName() + " attacks " + enemy.getName() + " | " + playersAtatck + "hp");
                //Player is attacking
                enemy.setHP(enemy.getHP() + playersAtatck);

                //If enemy is still alive, then he attacks
                if (enemy.getHP() > 0) {
                    System.out.println(enemy.getName() + " attacks " + player.getName() + " |" + enemysAttack + "hp");

                    //Player is attacking
                    player.setHP(player.getHP() + enemysAttack);
                }
            /*
                If both have the same initiative then both attack simultaneously
             */
            } else if (playerInitiative == enemyInitiative) {
                //Player and enemy are attacking simultaneously
                System.out.println(enemy.getName() + " attacks " + player.getName() + " | " + enemysAttack + "hp");
                System.out.println(player.getName() + " attacks " + enemy.getName() + " | " + playersAtatck + "hp");

                //Enemy is attacking
                enemy.setHP(enemy.getHP() + playersAtatck);
                //Player is attacking
                player.setHP(player.getHP() + enemysAttack);

                //If enemy has higher initiative => enemy attacks first
            } else {
                //Enemy attacks first
                System.out.println(enemy.getName() + " attacks " + player.getName() + " |" + enemysAttack + "hp");

                //Player is attacking
                player.setHP(player.getHP() + enemysAttack);

                if (player.getHP() > 0) {
                    System.out.println(player.getName() + " attacks " + enemy.getName() + " | " + playersAtatck + "hp");

                    //Enemy is attacking
                    enemy.setHP(enemy.getHP() + playersAtatck);
                }
            }
        }

        //Print enemy's and player's HP statuses
        System.out.println("\n  " + player.getName() + "'s HP: " + player.getHP() + "\n   " + enemy.getName() + "'s Hp: " + enemy.getHP());

        //Check for kill
        if (enemy.getHP() <= 0 && player.getHP() > 0) {
            //If player is still alive and enemy is dead
            return true;
        } else if (enemy.getHP() > 0 && player.getHP() <= 0) {
            //If player died and monster is still alive
            return false;
        }

        //Call next round of combat
        return combatRound(player, enemy, sc, roundIndex);
    }

    private int combatOptions(int index, Scanner sc) {
        System.out.println("\n\nRound " + index);
        System.out.println("    1) Attack");
        System.out.println("    2) Suicide");
        int selected = sc.nextInt();
        if (0 < selected && selected < 3) {
            return selected;
        } else {
            //If numerous input is invalid -> ask again
            System.out.println("            invalid input");
            return combatOptions(index, sc);
        }
    }
}
