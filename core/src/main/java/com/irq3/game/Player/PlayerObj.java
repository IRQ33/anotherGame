package com.irq3.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerObj {
    private final Sprite sprite;
    private final Movement movement;
    private int x = 0, y = 0, height = 32, width = 32;

    public PlayerObj() {
        Texture texture = new Texture(Gdx.files.internal("robot.png"));
        sprite = new Sprite(texture);
        sprite.setPosition(this.x,this.y);
        sprite.setSize(this.width,this.height);
        System.out.println("X: "+this.x+ " Y: "+this.y);
        movement = new Movement(this);
    }

    public void paint(SpriteBatch batch)
    {
        sprite.draw(batch);
    }

    public void tick()
    {
        movement.move();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.sprite.setX(x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.sprite.setY(y);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
