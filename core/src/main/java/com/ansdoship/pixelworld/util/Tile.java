package com.ansdoship.pixelworld.util;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.ansdoship.pixelworld.world.Chunk;

public class Tile implements Poolable{
    public float light = 10f;
    public int top = 0;
    public Material[] layers;
    public transient Position pos;

    public Tile(){
        
    }
    
    public void setPos(Position pos) {
        this.pos = pos;
    }
    
    public void setPos(int x, int y) {
        this.pos.set(x, y);
    }

    public Position getPos() {
        return pos;
    }

    public float worldx(){
        return pos.x * Chunk.tilesize;
    }

    public float worldy(){
        return pos.y * Chunk.tilesize;
    }

    public float light(){
        return light;
    }

    public void setLight(float f){
        light = f;
    }
    
    @Override
    public void reset() {
    }
    
}

