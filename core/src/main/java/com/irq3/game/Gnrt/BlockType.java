package com.irq3.game.Gnrt;

public enum BlockType {
    STONE(0),SAND(1),WATER(2), DIRT(3), GRASS(4);

    private final int numVal;

    BlockType(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
