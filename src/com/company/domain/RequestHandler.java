package com.company.domain;

import com.company.domain.impl.Option.ItemOptionImpl;
import com.company.domain.impl.Option.PlayerOptionImpl;

/**
 * Created by Tomáš Rechtig on 23.10.2016.
 */
public interface RequestHandler {

    void handlePlayerOptions(PlayerOptionImpl option);
    void handleItemOptions(ItemOptionImpl option);

    void processOption(Option option);

    void setLocation(Location location);

    Listener getListener();
    Player getPlayer();
    Location getLocation();
    Location direction(Option option);
    boolean isDirectionOption(Option option);
}
