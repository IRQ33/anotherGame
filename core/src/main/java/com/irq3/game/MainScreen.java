package com.irq3.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.irq3.game.Gnrt.Generator;

public class MainScreen implements Screen {
    SpriteBatch batch;
    OrthographicCamera camera;
    Player player;
    Generator generator;
    MoveCamera Mcamera;
    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(640,480);
        camera.zoom = 0.8f;
        player = new Player();
        generator= new Generator(batch);
        Mcamera= new MoveCamera(camera);
        generator.Generate();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0,0,179,1);
        Mcamera.Move();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        generator.Paint();
        player.Paint(batch);
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
