package com.irq3.game.Gnrt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.irq3.game.Utlis;

import java.util.HashSet;
import java.util.Set;

public class Generator {
    SpriteBatch  batch;
    Texture[] textures = new Texture[5];
    int width=32, height =32;
    int fov=320;
    int lastPosX=0;
    int lastPosY=0;
    public static final Set<Block> WORLD =new HashSet<>();
    PerlinNoise noise;
    float scale = 0.025f;
    public Generator(SpriteBatch batch) {
        this.batch = batch;
        init();


    }

    public void generate(Camera camera)
    {
        int posX =(int) camera.position.x;
        int posY = (int) camera.position.y;

        for (int i = posX-fov; i <=posX+fov ; i+=32) {
            for (int j = posY-fov; j <=posY+fov ; j+=32) {
                double generated = noise.noise(i*scale, j*scale)*2;
                WORLD.add(new Block(i,j, width,height, chooseBlock(generated)));
            }
        }
    }
    public void updateGeneration(Camera camera)
    {
        int posX = (int) camera.position.x;
        int posY = (int) camera.position.y;
        int realPosX = Utlis.realVal(posX);
        int realPosY = Utlis.realVal(posY);

        Set<Block> blockSet = new HashSet<>();
        if (posX == lastPosX && posY == lastPosY) {
            return;
        }

        lastPosX = posX;
        lastPosY = posY;
        for (int i = realPosX-fov; i <=realPosX+fov ; i+=32) {
            for (int j = realPosY-fov; j <=realPosY+fov ; j+=32) {
                double generated = noise.noise(i*scale, j*scale)*2;
                Block block = new Block(i,j, width,height, chooseBlock(generated));
                if(!WORLD.contains(block))
                {
                    blockSet.add(block);
                }
            }
        }
        WORLD.addAll(blockSet);
        WORLD.removeIf(block -> block.getX() >= posX + fov || block.getX() <= posX - fov ||
            block.getY() >= posY + fov || block.getY() <= posY - fov);


        System.out.println("X: "+posX+" Y: "+posY);
        System.out.println((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1024/1024+" MB");
    }

    public void paint(Camera camera)
    {

        for (Block block : WORLD)
        {
            if(block.getX()<=camera.position.x+fov&& block.getX()>= camera.position.x-fov)
            {
                if(block.getY()<=camera.position.y+fov && block.getY()>= camera.position.y-fov)
                {
                    batch.draw(textures[block.getNBlockType()], block.getX(),block.getY(),block.getWidth(),block.getHeight());
                }
            }
        }
    }
    private void init()
    {
        textures[0] = new Texture(Gdx.files.internal("stone.png"));
        textures[1] = new Texture(Gdx.files.internal("sand.png"));
        textures[2] = new Texture(Gdx.files.internal("water.png"));
        textures[3] = new Texture(Gdx.files.internal("dirt.png"));
        textures[4] = new Texture(Gdx.files.internal("grass.png"));

        noise = new PerlinNoise(21337);
    }
    public BlockType chooseBlock(double generated)
    {
        if (generated < -0.6f) {
            return BlockType.STONE;  //kamien
        } else if (generated < -0.2f) {
            return BlockType.DIRT; // dircik
        } else if (generated < 0.2f) {
            return BlockType.WATER; // łota
        } else if (generated < 0.4f) {
            return BlockType.SAND; // piasek
        } else {
            return BlockType.GRASS; // trawka
        }
    }
    public void dispose()
    {
        for (int i=0; i<=textures.length; i++)
        {
            textures[i].dispose();
        }
    }
    public int getGenSize()
    {
        return WORLD.size();
    }
}
