package com.irq3.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.irq3.game.Gnrt.Generator;

public class MainScreen implements Screen {
    SpriteBatch batch;
    OrthographicCamera camera;
    Player player;
    Generator generator;
    MoveCamera Mcamera;
    BitmapFont font;
    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        camera = new OrthographicCamera(640,480);
        camera.zoom = 0.8f;
        player = new Player();
        generator= new Generator(batch);
        Mcamera= new MoveCamera(camera);
        generator.Generate(camera);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0,0,179,1);
        Mcamera.Move();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        generator.UpdateGeneration(camera);
        generator.Paint(camera);
        player.Paint(batch);
        font.draw(batch,"FPS: "+Gdx.graphics.getFramesPerSecond(), camera.position.x+160,camera.position.y+100);
        font.draw(batch,"Loaded: "+generator.blocks.size(), camera.position.x+160,camera.position.y+60);
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
