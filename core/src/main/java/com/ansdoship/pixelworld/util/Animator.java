package com.ansdoship.pixelworld.util;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {
    
    public float width;
    public float height;

    public MyAnimation currentAnimation;

    private MyAnimation[] animations;
    private TextureRegion[][] animationFrames;

    public Animator(TextureRegion[][] sprites, int numFrames, int index, float delay) {
        TextureRegion[] frames = new TextureRegion[numFrames];

        width = sprites[index][0].getRegionWidth();
        height = sprites[index][0].getRegionHeight();

        for (int i = 0; i < numFrames; i++) {
            frames[i] = sprites[index][i];
        }

        currentAnimation = new MyAnimation(delay, frames);
    }

    public Animator(TextureRegion[][] sprites, int worldIndex, int startIndex, int numFrames, float delay) {
        TextureRegion[] frames = new TextureRegion[numFrames];

        width = sprites[worldIndex][startIndex].getRegionWidth();
        height = sprites[worldIndex][startIndex].getRegionHeight();

        for (int i = startIndex; i < startIndex + numFrames; i++) {
            frames[i - startIndex] = sprites[worldIndex][i];
        }

        currentAnimation = new MyAnimation(delay, frames);
    }

    public Animator(TextureRegion[][] sprites, int index, float delay) {
        animations = new MyAnimation[4];
        animationFrames = new TextureRegion[4][4];

        width = sprites[index][0].getRegionWidth();
        height = sprites[index][0].getRegionHeight();

        for (int i = 0; i < sprites[index].length / 4; i++) {
            for (int j = 0; j < animationFrames[0].length; j++) {
                animationFrames[i][j] = sprites[index][(j % 4) + (i * 4)];
            }
        }
        for (int i = 0; i < animations.length; i++) {
            animations[i] = new MyAnimation(delay, animationFrames[i]);
        }

        currentAnimation = animations[0];
    }

    public void update(float dt) {
        currentAnimation.update(dt);
        currentAnimation.play();
    }

    public void setAnimation(int index) {
        currentAnimation = animations[index];
    }

    public void stopAnimation() {
        currentAnimation.stop();
    }

    public TextureRegion getKeyFrame(boolean looping) {
        return currentAnimation.getKeyFrame(looping);
    }
    
}
