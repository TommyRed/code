package com.company.ui;

import com.company.domain.*;
import com.company.domain.Character;
import com.company.domain.impl.ArenaImpl;
import com.company.domain.impl.DirectionOptionImpl;
import com.company.domain.impl.ItemOptionImpl;
import com.company.domain.impl.ListenerImpl;

import java.util.List;

/**
 * Created by semanticer on 3. 10. 2016.
 */
public class GameUI {

    private Listener listener;
    private Location location;
    private Player player;

    public GameUI(Location location, Player player){
        if (location == null) throw new IllegalArgumentException("StartingLocation cannot be null");

        this.listener = new ListenerImpl();
        this.location = location;
        this.player = player;
    }

    public void play() {
        if (location == null) throw new IllegalArgumentException("Location cannot be null");

        if (!location.isSafe()) {
            Character enemy = location.generateEnemy(player);

            if (enemy != null) {
//                combat(enemy);
            }
        }

        if (player.isAlive()) {
            List<Option> options = mergeOptions(location.getDirections(), location.getOptions(), player.getOptions());
            presentLocation(options);

            int inputUser = listener.listenForIntWithin(0, options.size() + 1) - 1;

            Option selectedOption = options.get(inputUser);

            if (selectedOption instanceof DirectionOptionImpl) location = ((DirectionOptionImpl) selectedOption).getLocation();
            else processOption(selectedOption);

            play();
        } else {
//            listener.onEndGame(player);
            endGame();
        }
    }

    private void processOption(Option option){
        System.out.println(option.getName());
    }

    private void presentLocation(List<Option> options) {
        listener.onNewRound(location, player, options);
    }

    private boolean combat(Character enemy) {
        listener.onNewCombat(enemy);
        return new ArenaImpl(player, enemy, listener).startCombat();
    }

    private List<Option> mergeOptions(List<Option> base, List<Option> firstAppended, List<Option> secondAppended) {
        base.addAll(firstAppended);
        base.addAll(secondAppended);
        return base;
    }

    private void endGame() {
        listener.onEndGame(player);
    }
}