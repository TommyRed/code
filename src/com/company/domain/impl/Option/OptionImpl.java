package com.company.domain.impl.Option;

import com.company.domain.*;
import com.company.domain.impl.Option.Types.OptionTypes;

/**
 * Created by Tomáš Rechtig on 07.10.2016.
 */
public class OptionImpl implements Option {

    private String name;
    private OptionTypes type;

    public OptionImpl(String name, OptionTypes type) {
        this.name = name;
        this.type = type;
    }

    OptionImpl(String name) {
        this.name = name;
        this.type = null;
    }

    @Override
    public String getName() {
        return name;
    }

    public OptionTypes getType() {
        return type;
    }
}
