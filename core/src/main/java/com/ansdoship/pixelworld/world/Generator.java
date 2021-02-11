package com.ansdoship.pixelworld.world;

import com.ansdoship.pixelworld.util.PerlinNoise;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Generator {
    
    public PerlinNoise noise;
    public double persistence = 1.0;
    public double frequency = 2.0;
    
    public int octaves = 4;
    public int seed;
    TextureRegion t = new TextureRegion(new Texture("images/tiles/wall.png"));
    TextureRegion i = new TextureRegion(new Texture("images/tiles/ground.png"));

    public Generator(int seed) {
        this.seed = seed;
    }

    public Chunk generate(Chunk c) {
        noise = new PerlinNoise(seed, persistence, frequency, 1.0, octaves);
        Chunk Chunk = initChunk(c);
        return Chunk;
    }

    public Chunk initChunk(Chunk c) {
        c.fill(i);
        
        for (int i = 0; i < c.ChunkSize; i++) {
            for (int j = 0; j < c.ChunkSize; j++) {
                if(n((int)n(c.x, i), (int)n(c.y, j)) < n(i, j)){
                    c.tilemap.tiles[i][j] = t;
                }
            }
        }
        return c;
    }

    public double n(int x, int y) {
        double no = noise.getHeight(x, y);
        while (no > 1) { no -= 2; }
        while (no < -1) { no += 2; }
        return no;
    }
    
}
