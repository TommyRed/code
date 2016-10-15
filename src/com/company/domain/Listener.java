package com.company.domain;

/**
 * Created by Tomáš Rechtig on 10.10.2016.
 */
public interface Listener {

    void onNewRound(Player player, Character enemy);

    void onAttack(Character character);
}
