package com.company.domain.impl.Option;

import com.company.domain.Player;

/**
 * Created by Tomáš Rechtig on 22.10.2016.
 */
public class PlayerOptionImpl extends OptionImpl {

    private Player player;
    private PlayerOptionTypes optionType;

    public PlayerOptionImpl(String text, Player player, PlayerOptionTypes optionType) {
        super(text);
        this.player = player;
        this.optionType = optionType;
    }

    public Player getPlayer(){
        return player;
    }

    public PlayerOptionTypes getOptionType() {
        return optionType;
    }
}
