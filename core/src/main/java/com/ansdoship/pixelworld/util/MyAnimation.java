package com.ansdoship.pixelworld.util;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class MyAnimation extends Animation {

    private float stateTime;
    private boolean playing;

    public MyAnimation(float frameDuration, Array<? extends TextureRegion> keyFrames) {
        super(frameDuration, keyFrames);
    }

    public MyAnimation(float frameDuration, Array<? extends TextureRegion> keyFrames, PlayMode playMode) {
        super(frameDuration, keyFrames, playMode);
    }

    public MyAnimation(float frameDuration, TextureRegion... keyFrames) {
        super(frameDuration, keyFrames);
    }

    public void play() {
        if (!playing) {
            playing = true;
        }
    }

    public void pause() {
        if (playing) {
            playing = false;
        }
    }

    public void stop() {
        if (stateTime != 0f) {
            stateTime = 0f;
        }
        if (playing) {
            playing = false;
        }
    }

    public void update(float delta) {
        if (playing) {
            stateTime += delta;
        }
    }

    public void reset() {
        if (stateTime != 0) stateTime = 0;
    }

    public TextureRegion getKeyFrame(boolean looping) {
        return (TextureRegion)getKeyFrame(stateTime, looping);
    }

    public TextureRegion getKeyFrame() {
        return (TextureRegion)getKeyFrame(stateTime);
    }

    public int getKeyFrameIndex() {
        return getKeyFrameIndex(stateTime);
    }

    public boolean isAnimationFinished() {
        return isAnimationFinished(stateTime);
    }

    public boolean isPlaying() {
        return playing;
    }

}
