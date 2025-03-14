package com.irq3.game;

import com.badlogic.gdx.graphics.Camera;
import com.irq3.game.Player.PlayerObj;

public class MoveCamera  {
    Camera camera;
    PlayerObj obj;

    public MoveCamera(PlayerObj obj, Camera camera)
    {
        this.camera= camera;
        this.obj = obj;
    }

    public void move()
    {
        camera.position.set(obj.getX(),obj.getY(),0);

    }

}
