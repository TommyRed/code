package com.company.domain.impl;

import com.company.domain.*;
import com.company.domain.Character;
import com.company.domain.impl.Option.ItemOptionImpl;
import com.company.domain.impl.Option.OptionImpl;
import com.company.domain.impl.Option.PlayerOptionImpl;
import com.company.domain.impl.Option.Types.ItemOptionTypes;
import com.company.domain.impl.Option.Types.OptionTypes;
import com.company.domain.impl.Option.Types.PlayerOptionTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomáš Rechtig on 10.10.2016.
 */
public class ArenaImpl implements Arena {

    private Player player;
    private Character enemy;
    private Listener listener;
    private RequestHandler requestHandler;
    private List<Option> options;

    public ArenaImpl(Player player, Character enemy, Listener listener, RequestHandler requestHandler) {
        this.player = player;
        this.enemy = enemy;
        this.listener = listener;
        this.options = prepareOptions();
        this.requestHandler = requestHandler;
    }

    @Override
    public void startCombat(){
        listener.onNewCombat(enemy);
        newRound();

        if (player.isAlive()) System.out.println("cg u won");
        else System.out.println("u rekt");
    }

    private void newRound() {
        int playerInitiative = rollInitiative(player);
        int enemyInitiative = rollInitiative(enemy);

        listener.onNewCombatRound(player, enemy, options);

        Option selectedOption = options.get(listener.listenForIntWithin(0, options.size() + 1) - 1);

        if (selectedOption instanceof PlayerOptionImpl) {
            requestHandler.processOption(selectedOption);

            if (player.isAlive()) attack(player, enemy.getAttackNumber());

        } else if (selectedOption.getType() == OptionTypes.ATTACK) {

            if (playerInitiative > enemyInitiative) {
                attackRound(player, enemy);
            } else {
                attackRound(enemy, player);
            }
        }

        if (player.isAlive() && enemy.getHP() > 0) newRound();
    }

    private void attackRound(Character attacker, Character attacked){
        attack(attacked, attacker.getAttackNumber());
        if (attacked.getHP() > 0) attack(attacker, attacked.getAttackNumber());
    }

    private int rollInitiative(Character character){
        int initiative = character.rollDiceK6();
        listener.onCombatInitiative(character, initiative);
        return initiative;
    }

    private void attack(Character character, int attackNum) {
        listener.onAttack(character, attackNum);
        character.setHP(character.getHP() - attackNum);
    }

    // TODO
    private List<Option> prepareOptions(){
        List<Option> optionList = new ArrayList<>();

        optionList.add(new PlayerOptionImpl("Suicide", player, PlayerOptionTypes.SUICIDE));
        optionList.add(new OptionImpl("Attack", OptionTypes.ATTACK));
        if (player.hasPotion() && player.getPotion() != null)
            optionList.add(new ItemOptionImpl("Use potion", player.getPotion(), ItemOptionTypes.USEPOTION));

        return optionList;
    }

    /*
     *  Getters
     */
    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Character getEnemy() {
        return enemy;
    }
}
