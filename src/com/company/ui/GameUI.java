package com.company.ui;

import com.company.domain.*;
import com.company.domain.Character;
import com.company.domain.impl.ArenaImpl;
import com.company.domain.impl.ListenerImpl;
import com.company.domain.impl.RequestHandlerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by semanticer on 3. 10. 2016.
 */
public class GameUI {

    private Listener listener;
    private Location location;
    private Player player;
    private RequestHandler request;

    public GameUI(Location location, Player player) {
        if (location == null) throw new IllegalArgumentException("StartingLocation cannot be null");

        this.listener = new ListenerImpl();
        this.location = location;
        this.player = player;

        this.request = new RequestHandlerImpl(location, player, listener);
    }

    public void play() {
        if (location == null) throw new IllegalArgumentException("Location cannot be null");

        if (!location.isSafe()) {
            Character enemy = location.generateEnemy(player);

            if (enemy != null) {
                combat(enemy);
            }
        }

        if (player.isAlive()) {

            List<Option> options = mergeOptions();

            presentLocation(options);

            Option selectedOption = options.get(listener.listenForIntWithin(0, options.size() + 1) - 1);

            if (request.isDirectionOption(selectedOption))
                location = request.direction(selectedOption);
            else request.processOption(selectedOption);

            play();
        } else {
            endGame();
        }
    }

    private void presentLocation(List<Option> options) {
        listener.onNewRound(location, player, options);
        request.setLocation(location);
    }

    private boolean combat(Character enemy) {
        listener.onNewCombat(enemy);
        return new ArenaImpl(player, enemy, listener).startCombat();
    }

    private List<Option> mergeOptions() {
        List<Option> base = new ArrayList<>();
        base.addAll(location.getDirections());
        base.addAll(location.getOptions());
        base.addAll(player.getOptions());
        return base;
    }

    private void endGame() {
        listener.onEndGame(player);
    }
}