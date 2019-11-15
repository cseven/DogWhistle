package com.c777.dogwhistle.item;

import com.c777.dogwhistle.item.WhistleItem;

public class DiamondWhistleItem extends WhistleItem {

    private int WOLF_RANGE = 32;

    public DiamondWhistleItem(Properties properties) {
        super(properties);
    }

    @Override
    protected int getRange() {
        return this.WOLF_RANGE;
    }
}
