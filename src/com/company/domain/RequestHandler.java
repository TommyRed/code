package com.company.domain;

/**
 * Created by Tomáš Rechtig on 23.10.2016.
 */
public interface RequestHandler {

    void processOption(Option option);

    void setLocation(Location location);
    Listener getListener();
    Player getPlayer();
    Location getLocation();
    Location direction(Option option);
    boolean isDirectionOption(Option option);
}
