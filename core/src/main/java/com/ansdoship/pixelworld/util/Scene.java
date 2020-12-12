package com.ansdoship.pixelworld.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Scene {


    Sprite sprite;
    protected Texture texture;
    Texture texturePassed;
    private Position position;
    int rayon = Gdx.graphics.getWidth()/45;
    boolean passed;
    boolean actual;
    int coeff = 2;
    private Scene rightScene;
    private Scene leftScene;
    private Scene uniqueScene;
    
    public Scene(Position pos){
        position = pos;
        sprite = new Sprite();
        passed = false;
        actual = false;
    }
    
    public abstract void draw(SpriteBatch spriteBatch);

    public Position getPosition() {
        return position;
    }

    public int getRayon() {
        return rayon;
    }

    public void setRightScene(Scene nextScene) {
        this.rightScene = nextScene;
    }

    public Scene getRightScene() {
        return rightScene;
    }

    public void setLeftScene(Scene nextScene) {
        this.leftScene = nextScene;
    }

    public Scene getLeftScene() {
        return leftScene;
    }

    public Scene getUniqueScene() {
        return uniqueScene;
    }

    public void setUniqueScene(Scene uniqueScene) {
        this.uniqueScene = uniqueScene;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setActual() {
        if(!actual) {
            this.actual = true;
            sprite.setSize((float) (sprite.getWidth() * 1.5), (float) (sprite.getHeight() * 1.5));
            rayon = (int) (rayon * 1.5);
            sprite.setPosition(this.getPosition().x - getRayon(), this.getPosition().y - getRayon());
        }else{
            this.actual = false;
            sprite.setSize((float) (sprite.getWidth() / 1.5), (float) (sprite.getHeight() / 1.5));
            rayon = (int) (rayon / 1.5);
            sprite.setPosition(this.getPosition().x - getRayon(), this.getPosition().y - getRayon());
        }
    }

    public boolean isActual() {
        return actual;
    }

    public boolean isNext(Scene Scene){
        boolean res = false;
        if(Scene.equals(uniqueScene) || Scene.equals(rightScene) || Scene.equals(leftScene)){
            res = true;
        }
        return res;
    }

    public void setPassed() {
        passed = true;
        sprite.setTexture(texturePassed);
    }
}

