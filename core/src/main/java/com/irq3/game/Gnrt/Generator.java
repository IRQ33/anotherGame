package com.irq3.game.Gnrt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    SpriteBatch  batch;
    Texture[] textures = new Texture[5];
    int xStart=-100,yStart=-100;
    int width=32, height =32;
    int xSize = 3200, ySize = 3200;
   List<Block> blocks =new ArrayList<>();
   PerlinNoise noise;
    float scale = 0.05f;
    public Generator(SpriteBatch batch) {
        this.batch = batch;
        Init();

    }

    public void Generate()
    {
        for (int i =xStart; i<=xSize; i+=width) {
            for (int j = yStart; j <= ySize; j += height) {

                double generated = noise.noise(i*scale, j*scale);

                blocks.add(new Block(i,j, width,height, ChooseBlock(generated)));
                System.out.println("generated in: " + i + " " + j);
            }
        }
    }
    public void Paint()
    {
        for(Block block : blocks)
        {
            batch.draw(textures[block.blockType], block.getX(),block.getY(),block.getWidth(),block.getHeight());
        }
    }
    private void Init()
    {
        textures[0] = new Texture(Gdx.files.internal("stone.png"));
        textures[1] = new Texture(Gdx.files.internal("sand.png"));
        textures[2] = new Texture(Gdx.files.internal("water.png"));
        noise = new PerlinNoise(21337);
    }
    public int ChooseBlock(double generated)
    {
        if(generated<-0.2f)
        {
            return 0;
        } else if (generated<0.2f) {
            return 1;
        }
        else {
            return 2;
        }
    }
}
