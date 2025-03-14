package com.irq3.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.HashSet;
import java.util.Set;

public class Movement implements InputProcessor {


    private final Set<Integer> keys = new HashSet<>();
    private final PlayerObj playerObj;
    private boolean canMove=true;

    public Movement(PlayerObj obj)
    {
        this.playerObj = obj;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int i) {
        keys.add(i);

        return false;
    }
    @Override
    public boolean keyUp(int i) {
        keys.remove(i);
        return false;
    }
    public void Move()
    {
        if (!canMove) return;
        if(!keys.isEmpty())
        {
            if(keys.contains(Input.Keys.W))
            {
                playerObj.setY(playerObj.getY()+5);
            }
            if(keys.contains(Input.Keys.S))
            {
                playerObj.setY(playerObj.getY()-5);
            }
            if(keys.contains(Input.Keys.D))
            {
                playerObj.setX(playerObj.getX()+5);
            }
            if(keys.contains(Input.Keys.A))
            {
                playerObj.setX(playerObj.getX()-5);
            }

        }
    }
    public boolean tryMove()
    {

        return true;
    }
    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
