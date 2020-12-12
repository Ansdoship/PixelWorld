package com.ansdoship.pixelworld.world;

import com.ansdoship.pixelworld.util.TileMap;
import com.badlogic.gdx.graphics.Texture;
import com.ansdoship.pixelworld.util.Position;
import com.badlogic.gdx.Gdx;

public class Chunk {

    TileMap tilemap;
    public static int ChunkSize = 16;
    public static int tilesize = 16;
    public static int TileWidth = tilesize, TileHeight = tilesize;

    public Chunk() {

        tilemap = new TileMap(ChunkSize, ChunkSize);
//        fill(new Texture("images/tiles/ground.png"));

    }

    public Texture get(int x, int y) {
        return tilemap.get(x, y);
    }

    public boolean set(int x, int y, Texture texture) {
        return tilemap.set(x, y, texture);
    }

    public boolean set(Position pos, Texture texture) {
        return tilemap.set(pos, texture);
    }

    public boolean fill(int x, int y, int w, int h, Texture texture) {
        return tilemap.fill(x, y, w, h, texture);
    }

    public boolean fill(Texture texture) {
        return fill(0, 0, ChunkSize, ChunkSize, texture);
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
                    world.draw(new Texture("ic_launcher_128.png"), x, y, TileWidth, TileHeight);
                }
            }
        }
    }

}
