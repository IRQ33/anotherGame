package com.irq3.game.Gnrt;

import java.util.Objects;

public class Block {
    private int x,y;
    private int width,height;
    private int blockType;


    public Block(int x, int y, int width, int height, BlockType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.blockType = type.getNumVal();
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

    public int getNBlockType() {
       return blockType;
    }

    public BlockType getBlockType() {
        for (BlockType type : BlockType.values())
        {
            if(type.getNumVal()==blockType) return type;
        }
        return null;

    }

    public void setBlockType(int blockType) {
        this.blockType = blockType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Block block = (Block) obj;
        return x == block.x && y == block.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
