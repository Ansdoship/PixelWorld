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
                chunks[i][j] = new Chunk();
            }
		}
    }

    public Chunk getChunk(int x, int y) {
        return chunks[x][y];//chunks.get(y).get(x);
    }

    public void draw() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int sx = i * Chunk.TileWidth * Chunk.ChunkSize;
                int sy = j * Chunk.TileHeight * Chunk.ChunkSize;
                getChunk(i, j).draw(this, sx, sy);
                Gdx.app.log("drawChunk", String.format("x:%d y:%d", sx, sy));
            }
        }
    }

}
