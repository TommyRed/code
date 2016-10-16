package com.company.domain;

/**
 * Created by Tomáš Rechtig on 10.10.2016.
 */
public interface Listener {

    int onNewRound(Player player, Character enemy);

    void onAttack(Character character, int damage);

    int listenForInt();
    int listenForIntWithin(int from, int to);

    boolean checkWithin(int from, int to, int actualNum);
}
