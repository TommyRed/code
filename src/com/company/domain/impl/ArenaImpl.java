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

    private boolean newRound() {
        int option = listener.onNewRound(player, enemy);

        if (option == 1){
            player.suicide();
        } else if (option == 2){
            int playerInitiative = player.rollDiceK6();
            int enemyInitiative = enemy.rollDiceK6();

            if (playerInitiative > enemyInitiative) {
                attackRound(enemy, player);
            } else {
                attackRound(player, enemy);
            }
        }
        if (player.getHP() <= 0) return false;
        else return enemy.getHP() <= 0 || newRound();
    }

    private void attackRound(Character attacked, Character attacker){
        attack(attacked, attacker.getAttackNumber());
        if (attacked.getHP() > 0) attack(attacker, attacked.getAttackNumber());
    }

    private void attack(Character character, int attackNum) {
        listener.onAttack(character, attackNum);
        character.setHP(character.getHP() - attackNum);
    }

    public boolean startCombat(){
        return newRound();
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Character getEnemy() {
        return enemy;
    }
}
