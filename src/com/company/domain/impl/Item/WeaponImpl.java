package com.company.domain.impl.Item;


import com.company.domain.ItemType;

/**
 * Created by Tomáš Rechtig on 15.10.2016.
 */
public class WeaponImpl extends ItemImpl {

    private final int attackNumber;

    public WeaponImpl(String name, int attackNumber, ItemType type) {
        super(name, type);
        System.out.println("@   Create new Weapon");
        this.attackNumber = attackNumber;
    }

    public int getAttackNumber() {
        return attackNumber;
    }
}
