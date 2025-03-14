package com.irq3.game.Collision;

import com.irq3.game.Gnrt.Block;
import com.irq3.game.Gnrt.BlockType;
import com.irq3.game.Gnrt.Generator;
import com.irq3.game.Player.PlayerObj;
import com.irq3.game.Utlis;

public class Collide {
    private final Block[] blocks = new Block[4];
    private final PlayerObj obj;

    public Collide(PlayerObj obj) {
        this.obj = obj;
    }
    public void update()
    {
        int posX = Utlis.realVal(obj.getX());
        int posY = Utlis.realVal(obj.getY());
       blocks[0] = Generator.WORLD.stream().filter(block -> block.getX()==posX&& block.getY()==posY).findFirst().get();
        blocks[1] = Generator.WORLD.stream().filter(block -> block.getX()+32==posX&& block.getY()==posY).findFirst().get();
        blocks[2] = Generator.WORLD.stream().filter(block -> block.getX()-32==posX&& block.getY()==posY).findFirst().get();
        blocks[3] = Generator.WORLD.stream().filter(block -> block.getX()==posX&& block.getY()+32==posY).findFirst().get();
        blocks[4] = Generator.WORLD.stream().filter(block -> block.getX()==posX&& block.getY()-32==posY).findFirst().get();
    }
    public boolean[] canColide()
    {
        boolean[] options = new boolean[4];
        for (int i =0; i<=blocks.length; i++)
        {
            if(blocks[i].getBlockType()!= BlockType.WATER){
                options[i] = true;
            }
        }
        return options;
    }
}
