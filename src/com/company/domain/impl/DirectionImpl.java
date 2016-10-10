package com.company.domain.impl;

import com.company.domain.Direction;
import com.company.domain.Location;

/**
 * Created by Tomáš Rechtig on 07.10.2016.
 */
public class DirectionImpl implements Direction {

    String name;
    Location location;

    public DirectionImpl(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
