package com.irq3.game.Gnrt;

public class Block {
    int x,y;
    int width,height;
    int blockType;

    public Block(int x, int y, int width, int height, int blockType) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.blockType = blockType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBlockType() {
        return blockType;
    }

    public void setBlockType(int blockType) {
        this.blockType = blockType;
    }
}
