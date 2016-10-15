package com.company.domain;

/**
 * Created by Tomáš Rechtig on 10.10.2016.
 */
public interface Arena {

    Player getPlayer();
    Character getEnemy();

    void newRound();

}
