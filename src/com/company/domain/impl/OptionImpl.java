package com.company.domain.impl;

import com.company.domain.*;

/**
 * Created by Tomáš Rechtig on 07.10.2016.
 */
public class OptionImpl implements Option {

    private String name;

    public OptionImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
