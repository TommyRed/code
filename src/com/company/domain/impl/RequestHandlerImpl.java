package com.company.domain.impl;

import com.company.domain.*;
import com.company.domain.impl.Option.DirectionOptionImpl;
import com.company.domain.impl.Option.ItemOptionImpl;
import com.company.domain.impl.Option.PlayerOptionImpl;


/**
 * Created by Tomáš Rechtig on 23.10.2016.
 */
public class RequestHandlerImpl implements RequestHandler {

    private Location location;
    private Player player;
    private Listener listener;

    public RequestHandlerImpl(Location location, Player player, Listener listener) {
        this.location = location;
        this.player = player;
        this.listener = listener;
    }

    @Override
    public void processOption(Option option) {
        System.out.println("@   -" + option.getName());
        if (option instanceof ItemOptionImpl) handleItemOptions((ItemOptionImpl) option);
        else if (option instanceof PlayerOptionImpl) handlePlayerOptions((PlayerOptionImpl) option);
    }

    @Override
    public void handlePlayerOptions(PlayerOptionImpl option) {
        System.out.println("@   Handle Player option");
        switch (option.getOptionType()) {
            case SUICIDE:
                player.suicide();
                break;
            case VIEWCHARACTERINFO:
                player.printCharacterInfo();
                break;
            case VIEWINVENTORY:
                player.printCharacterInventory();
                break;
        }
    }

    @Override
    public void handleItemOptions(ItemOptionImpl option) {
        System.out.println("@   Handle Item option");
        switch (option.getType()) {
            case PICKITEM:
                player.addItem(option.getItem());
                location.removeItem(option.getItem(), option);
                break;
            case DROPITEM:
                player.removeItem(option.getItem(), option);
                location.addItem(option.getItem());
                break;
        }
    }

    /*
     *  Setters
     */
    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    /*
     *  Getters
     */

    @Override
    public Location direction(Option option) {
        return ((DirectionOptionImpl) option).getLocation();
    }

    @Override
    public boolean isDirectionOption(Option option) {
        return option instanceof DirectionOptionImpl;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Listener getListener() {
        return listener;
    }
}
