package com.company.test;

import com.company.domain.Player;
import com.company.domain.impl.Character.PlayerImpl;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tomáš Rechtig on 15.10.2016.
 */
public class PlayerTest {

    @org.junit.Test
    public void playerTest() throws Exception{
        Player player = new PlayerImpl("Filip");

        String playerName = player.getName();

        assertEquals("Check player name", "Filip", playerName);
    }
}
