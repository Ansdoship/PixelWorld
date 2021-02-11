package com.ansdoship.pixelworld.world;

import com.ansdoship.pixelworld.util.Position;
import com.ansdoship.pixelworld.util.TileMap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;

public class Chunk {

    TileMap tilemap;
    public static int ChunkSize = 16;
    public static int tilesize = 16;
    public static int TileWidth = tilesize, TileHeight = tilesize;
    public int x, y;
    TextureRegion nullt = 
    new TextureRegion(new Texture("null.png"));

    public Chunk(int x, int y) {

        tilemap = new TileMap(ChunkSize, ChunkSize);
        this.x = x;
        this.y = y;

    }

    public TextureRegion get(int x, int y) {
        return tilemap.get(x, y);
    }

    public boolean set(int x, int y, TextureRegion TextureRegion) {
        return tilemap.set(x, y, TextureRegion);
    }

    public boolean set(Position pos, TextureRegion TextureRegion) {
        return tilemap.set(pos, TextureRegion);
    }

    public boolean fill(int x, int y, int w, int h, TextureRegion TextureRegion) {
        return tilemap.fill(x, y, w, h, TextureRegion);
    }

    public boolean fill(TextureRegion TextureRegion) {
        return fill(0, 0, ChunkSize, ChunkSize, TextureRegion);
    }
    
    public void copy(Chunk c){
        tilemap = c.tilemap;
    }

    public void draw(World world) {
        draw(world, 0, 0);
    }

    public void draw(World world, int x, int y) {
        for (int i = 0; i < tilemap.width; i++) {
            for (int j = 0; j < tilemap.height; j++) {
                int sx = i * TileWidth;
                int sy = j * TileHeight;
                if (tilemap.get(i, j) != null) {
                    world.draw(tilemap.get(i, j), sx + x, sy + y, TileWidth, TileHeight);
                    //Gdx.app.log("drawTile", String.format("x:%d y:%d", sx, sy));
                } else {
                    world.draw(nullt, x, y, TileWidth, TileHeight);
                }
            }
        }
    }

}
