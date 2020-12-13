package com.ansdoship.pixelworld.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;

public class World extends SpriteBatch {
    public int ChunkRenderingDistance;
    public Chunk[][] chunks;
//    public ArrayList<ArrayList<Chunk>> chunks = new ArrayList<>();
    public int width, height;

    /*
     *      向外延长n 2n+1为边长
     *                    □ □ □ □ □
     *       □ □ □       □ □ □ □ □
     * □ +1  □ □ □  +1   □ □ □ □ □
     *       □ □ □       □ □ □ □ □
     *                    □ □ □ □ □
     */

    public World(int ChunkRenderingDistance) {
        super();
        this.ChunkRenderingDistance = ChunkRenderingDistance;
        width = height = ChunkRenderingDistance * 2 + 1;
//        for (int j = 0; j < height; j++) {
//            ArrayList<Chunk> chunkline = new ArrayList<>();
//            for (int i = 0; i < width; i++) {
//                chunkline.add(new Chunk());
//            }
//            chunks.add(chunkline);
//        }

        chunks = new Chunk [width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                chunks[i][j] = new Chunk(i, j);
            }
		}
    }

    public Chunk getChunk(int x, int y) {
        return chunks[x][y];//chunks.get(y).get(x);
    }

    public void draw() {
//        getChunk(0, 0).draw(this, 0, 0);
//        getChunk(0, 1).draw(this, 0, 256);
//        getChunk(0, 2).draw(this, 0, 512);
//        getChunk(1, 0).draw(this, 256, 0);
//        getChunk(1, 1).draw(this, 256, 256);
//        getChunk(1, 2).draw(this, 256, 512);
//        getChunk(2, 0).draw(this, 512, 0);
//        getChunk(2, 1).draw(this, 512, 256);
//        getChunk(2, 2).draw(this, 512, 512);
        for (int i = 0; i < width; i++) {
            int sx = i * Chunk.TileWidth * Chunk.ChunkSize;
            for (int j = 0; j < height; j++) {
                int sy = j * Chunk.TileHeight * Chunk.ChunkSize;
                getChunk(i, j).draw(this, sx, sy);
                Gdx.app.log("drawChunk", String.format("x:%d y:%d", sx, sy));
            }
        }
    }

}
