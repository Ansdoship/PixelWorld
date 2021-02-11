package com.ansdoship.pixelworld.util;

import com.badlogic.gdx.graphics.Texture;
import com.ansdoship.pixelworld.world.World;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileMap implements Poolable{
    public TextureRegion[][] tiles;
	public int width, height;
    
    public TileMap(){}

    public TileMap(int w, int h) {
        CreateTileMap(w, h);
    }

    public TileMap(int w, int h, TextureRegion texture) {
        this(w, h);
        fill(0, 0, w, h, texture);
    }
    
    public void CreateTileMap(int w, int h){
        width = w;
        height = h;
		tiles = new TextureRegion[w][h];
    }

    public TextureRegion get(int x, int y) {
        return tiles[x][y];
    }

    public boolean set(int x, int y, TextureRegion texture) {
        if (texture != null) {
            tiles[x][y] = texture;
            return true;
        }
        return false;
    }

    public boolean set(Position pos, TextureRegion texture) {
        return set(pos.x, pos.y, texture);
    }

    public boolean fill(int x, int y, int w, int h, TextureRegion texture) {
        if (texture != null) {
            for (int i = x; i < w; i++) {
                for (int j = y; j < h; j++) {
                    tiles[i][j] = texture;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void reset() {
        for(int x = 0; x < width; x ++){
            for(int y = 0; y < height; y ++){
                Pools.free(tiles[x][y]);
                tiles[x][y] = null;
            }
		}
    }

}
