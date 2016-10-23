package com.company.domain;

import java.util.List;

/**
 * Created by Tomáš Rechtig on 10.10.2016.
 */
public interface Listener {
    void onAttack(Character character, int damage);
    void onEndGame(Player player);
    void onNewRound(Location location, Player player, List<Option> options);
    void onNewCombatRound(Player player, Character enemy, List<Option> options);
    void onNewCombat(Character character);
    void onCombatInitiative(Character character, int initiative);

    int listenForInt();
    int listenForIntWithin(int from, int to);

    boolean checkWithin(int from, int to, int actualNum);
}
