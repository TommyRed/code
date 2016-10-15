package com.company.domain.impl;

import com.company.domain.Arena;
import com.company.domain.Character;
import com.company.domain.Listener;
import com.company.domain.Player;

/**
 * Created by Tomáš Rechtig on 10.10.2016.
 */
public class ArenaImpl implements Arena {

    private Player player;
    private Character enemy;
    private Listener listener;

    public ArenaImpl(Player player, Character enemy, Listener listener) {
        this.player = player;
        this.enemy = enemy;
        this.listener = listener;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Character getEnemy() {
        return enemy;
    }

    @Override
    public void newRound() {
        listener.onNewRound(player, enemy);

        int playerInitiative = player.rollDiceK6();
        int enemyInitiative = enemy.rollDiceK6();

        if (playerInitiative > enemyInitiative) {
            //Player attacks first

            attack(enemy, player.getAttackNumber());
            if (enemy.getHP() > 0)
                attack(player, enemy.getAttackNumber());

        } else if (enemyInitiative < playerInitiative) {
            //Enemy attacks first

            attack(player, enemy.getAttackNumber());
            if (player.getHP() > 0)
                attack(enemy, player.getAttackNumber());

        } else {
            //Player and Enemy are attacking simultaneously
            attack(player, enemy.getAttackNumber());
            attack(enemy, player.getAttackNumber());
        }
    }

    private void attack(Character character, int attackNum) {
        character.setHP(character.getHP() + attackNum);
    }
}
