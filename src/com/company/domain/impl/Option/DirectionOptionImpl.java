package com.company.domain.impl.Option;

import com.company.domain.Location;

/**
 * Created by Tomáš Rechtig on 07.10.2016.
 */
public class DirectionOptionImpl extends OptionImpl {

    private Location location;

    public DirectionOptionImpl(String text, Location location) {
        super(text);
        this.location = location;
    }

    public Location getLocation(){
        return location;
    }
}
