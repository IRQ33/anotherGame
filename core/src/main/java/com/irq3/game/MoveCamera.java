package com.irq3.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;

import java.util.HashSet;
import java.util.Set;

public class MoveCamera implements InputProcessor {
    Camera camera;
    Set<Integer> keys = new HashSet<>();
    int x=0, y=0;

    public MoveCamera(Camera camera)
    {
        Gdx.input.setInputProcessor(this);
        this.camera= camera;
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
        if(!keys.isEmpty())
        {
            if(keys.contains(Input.Keys.W))
            {
                camera.position.add(0,5,0);
            }
            if(keys.contains(Input.Keys.S))
            {
                camera.position.add(0,-5,0);
            }
            if(keys.contains(Input.Keys.D))
            {
                camera.position.add(5,0,0);
            }
            if(keys.contains(Input.Keys.A))
            {
                camera.position.add(-5,0,0);
            }

        }
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
