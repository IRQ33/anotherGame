package com.irq3.game.Gnrt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashSet;
import java.util.Set;

public class Generator {
    SpriteBatch  batch;
    Texture[] textures = new Texture[5];
    int width=32, height =32;
    int fov=320;
    int lastPosX=0;
    int lastPosY=0;
   public Set<Block> blocks =new HashSet<>();
   PerlinNoise noise;
    float scale = 0.075f;
    public Generator(SpriteBatch batch) {
        this.batch = batch;
        Init();

    }

    public void Generate(Camera camera)
    {
        int posx =(int) camera.position.x;
        int posy = (int) camera.position.y;

        for (int i = posx-fov; i <=posx+fov ; i+=32) {
            for (int j = posy-fov; j <=posy+fov ; j+=32) {
                double generated = noise.noise(i*scale, j*scale)*2;
                blocks.add(new Block(i,j, width,height, ChooseBlock(generated)));
            }
        }
    }
    public void UpdateGeneration(Camera camera)
    {
        int posx = (int) camera.position.x;
        int posy = (int) camera.position.y;

        Set<Block> blockSet = new HashSet<>();
        if (posx == lastPosX && posy == lastPosY) {
            return;
        }

        lastPosX = posx;
        lastPosY = posy;
        for (int i = posx-fov; i <=posx+fov ; i+=32) {
            for (int j = posy-fov; j <=posy+fov ; j+=32) {
                double generated = noise.noise(i*scale, j*scale)*2;
                if(!blocks.contains(new Block(i,j, width,height, ChooseBlock(generated))))
                {
                    Block block = new Block(i,j, width,height, ChooseBlock(generated));
                    blockSet.add(block);
                }
            }
        }
        blocks.removeIf(block -> !blockSet.contains(block));
        blocks.addAll(blockSet);
    }

    public void Paint(Camera camera)
    {

        for (Block block : blocks)
        {
            if(block.getX()<=camera.position.x+1000 && block.getX()>= camera.position.x-1000)
            {
                if(block.getY()<=camera.position.y+1000 && block.getY()>= camera.position.y-1000)
                {
                    batch.draw(textures[block.getBlockType()], block.getX(),block.getY(),block.getWidth(),block.getHeight());
                }
            }
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
