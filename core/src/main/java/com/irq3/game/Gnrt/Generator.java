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
    private Set<Block> blocks =new HashSet<>();
    PerlinNoise noise;
    float scale = 0.025f;
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
        int posX = (int) camera.position.x;
        int posY = (int) camera.position.y;
        int realPosX = 32*Math.round(posX/32f);
        int realPosY = 32*Math.round(posY/32f);

        Set<Block> blockSet = new HashSet<>();
        if (posX == lastPosX && posY == lastPosY) {
            return;
        }

        lastPosX = posX;
        lastPosY = posY;
        for (int i = realPosX-fov; i <=realPosX+fov ; i+=32) {
            for (int j = realPosY-fov; j <=realPosY+fov ; j+=32) {
                double generated = noise.noise(i*scale, j*scale)*2;
                Block block = new Block(i,j, width,height, ChooseBlock(generated));
                if(!blocks.contains(block))
                {
                    blockSet.add(block);
                }
            }
        }
        blocks.addAll(blockSet);
        blocks.removeIf(block -> block.getX() >= posX + fov || block.getX() <= posX - fov ||
            block.getY() >= posY + fov || block.getY() <= posY - fov);


        System.out.println("X: "+posX+" Y: "+posY);
        System.out.println((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1024/1024+" MB");
    }

    public void Paint(Camera camera)
    {

        for (Block block : blocks)
        {
            if(block.getX()<=camera.position.x+fov&& block.getX()>= camera.position.x-fov)
            {
                if(block.getY()<=camera.position.y+fov && block.getY()>= camera.position.y-fov)
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
        textures[3] = new Texture(Gdx.files.internal("dirt.png"));
        textures[4] = new Texture(Gdx.files.internal("grass.png"));

        noise = new PerlinNoise(21337);
    }
    public int ChooseBlock(double generated)
    {
        if (generated < -0.6f) {
            return 0;  //kamien
        } else if (generated < -0.2f) {
            return 3; // dircik
        } else if (generated < 0.2f) {
            return 2; // łota
        } else if (generated < 0.4f) {
            return 1; // piasek
        } else {
            return 4; // trawka
        }
    }
    public void Dispose()
    {
        for (int i=0; i<=textures.length; i++)
        {
            textures[i].dispose();
        }
    }
    public int getGenSize()
    {
        return blocks.size();
    }
}
