package com.ansdoship.pixelworld.util;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Material {
    private static ArrayList<Material> materials = new ArrayList<Material>();

    protected final int id;
    protected final String name;
    protected Color color = Color.CLEAR;
    protected Texture texture;

    private static int maxid;

    protected Material(Texture texture, String name) {
        this.id = maxid++;
        this.name = name;
        this.texture = texture;
        materials.add(this);
    }

    public static Material getMaterial(int id) {
        return materials.get(id);
	}

    public static Iterable<Material> getAll() {
        return materials;
	}

    public int id() {
        return id;
    }

    public String name() {
        return name;
	}
    
    public Texture texture(){
        return texture;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }





}
