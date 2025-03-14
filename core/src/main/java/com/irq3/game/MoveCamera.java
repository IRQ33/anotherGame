package com.irq3.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.irq3.game.Player.PlayerObj;

import java.util.HashSet;
import java.util.Set;

public class MoveCamera  {
    Camera camera;
    PlayerObj obj;

    public MoveCamera(PlayerObj obj, Camera camera)
    {
        this.camera= camera;
        this.obj = obj;
    }

    public void Move()
    {
        camera.position.set(obj.getX(),obj.getY(),0);

    }

}
