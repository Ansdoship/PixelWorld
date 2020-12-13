package com.ansdoship.pixelworld.audio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SoundPlayer {
    Music player;

    private FileHandle lastPlayed;

    private boolean enabled = true;
    private boolean looping;

    private float volume = 1f;

    public synchronized void play(FileHandle file, boolean looping) {

        if (isPlaying() && lastPlayed != null && lastPlayed.equals(file)) {
            return;
        }

        stop();

        lastPlayed = file;
        this.looping = looping;

        if (!enabled || file == null) {
            return;
        }

        try {
            player = Gdx.audio.newMusic(file);
            player.setLooping(looping);
            player.setVolume(volume);
            player.play();
        } catch (Exception e) {
            Gdx.app.log("audio", e.toString());
            player = null;
        }

    }

    public synchronized void mute() {
        lastPlayed = null;
        stop();
    }

    public synchronized void pause() {
        if (player != null) {
            player.pause();
        }
    }

    public synchronized void resume() {
        if (player != null) {
            player.play();
            player.setLooping(looping);
        }
    }

    public synchronized void stop() {
        if (player != null) {
            player.stop();
            player.dispose();
            player = null;
        }
    }

    public synchronized void volume(float value) {
        volume = value;
        if (player != null) {
            player.setVolume(value);
        }
    }

    public synchronized boolean isPlaying() {
        return player != null && player.isPlaying();
    }

    public synchronized void enable(boolean value) {
        enabled = value;
        if (isPlaying() && !value) {
            stop();
        } else
        if (!isPlaying() && value) {
            play(lastPlayed, looping);
        }
    }

    public synchronized boolean isEnabled() {
        return enabled;
	}

//    public enum Sample {
//
//        INSTANCE;
//
//        protected HashMap<Object, Sound> ids = new HashMap<>();
//
//        private boolean enabled = true;
//        private float globalVolume = 1f;
//
//        public synchronized void reset() {
//
//            for (Sound sound : ids.values()){
//                sound.dispose();
//            }
//
//            ids.clear();
//            delayedSFX.clear();
//
//        }
//
//        public synchronized void pause() {
//            for (Sound sound : ids.values()) {
//                sound.pause();
//            }
//        }
//
//        public synchronized void resume() {
//            for (Sound sound : ids.values()) {
//                sound.resume();
//            }
//        }
//
//        public synchronized void load( final String... assets ) {
//
//            final ArrayList<String> toLoad = new ArrayList<>();
//
//            for (String asset : assets){
//                if (!ids.containsKey(asset)){
//                    toLoad.add(asset);
//                }
//            }
//
//            //don't make a new thread of all assets are already loaded
//            if (toLoad.isEmpty()) return;
//
//            //load in a separate thread to prevent this blocking the UI
//            new Thread(){
//                @Override
//                public void run() {
//                    for (String asset : toLoad) {
//                        Sound newSound = Gdx.audio.newSound(Gdx.files.internal(asset));
//                        synchronized (INSTANCE) {
//                            ids.put(asset, newSound);
//                        }
//                    }
//                }
//            }.start();
//
//        }
//
//        public synchronized void unload( Object src ) {
//            if (ids.containsKey( src )) {
//                ids.get( src ).dispose();
//                ids.remove( src );
//            }
//        }
//
//        public long play( Object id ) {
//            return play( id, 1 );
//        }
//
//        public long play( Object id, float volume ) {
//            return play( id, volume, volume, 1 );
//        }
//
//        public long play( Object id, float volume, float pitch ) {
//            return play( id, volume, volume, pitch );
//        }
//
//        public synchronized long play( Object id, float leftVolume, float rightVolume, float pitch ) {
//            float volume = Math.max(leftVolume, rightVolume);
//            float pan = rightVolume - leftVolume;
//            if (enabled && ids.containsKey( id )) {
//                return ids.get(id).play( globalVolume*volume, pitch, pan );
//            } else {
//                return -1;
//            }
//        }
//
//        private class DelayedSoundEffect{
//            Object id;
//            float delay;
//
//            float leftVol;
//            float rightVol;
//            float pitch;
//        }
//
//        private static final HashSet<DelayedSoundEffect> delayedSFX = new HashSet<>();
//
//        public void playDelayed( Object id, float delay ){
//            playDelayed( id, delay, 1 );
//        }
//
//        public void playDelayed( Object id, float delay, float volume ) {
//            playDelayed( id, delay, volume, volume, 1 );
//        }
//
//        public void playDelayed( Object id, float delay, float volume, float pitch ) {
//            playDelayed( id, delay, volume, volume, pitch );
//        }
//
//        public void playDelayed( Object id, float delay, float leftVolume, float rightVolume, float pitch ) {
//            if (delay <= 0) {
//                play(id, leftVolume, rightVolume, pitch);
//                return;
//            }
//            DelayedSoundEffect sfx = new DelayedSoundEffect();
//            sfx.id = id;
//            sfx.delay = delay;
//            sfx.leftVol = leftVolume;
//            sfx.rightVol = rightVolume;
//            sfx.pitch = pitch;
//            synchronized (delayedSFX) {
//                delayedSFX.add(sfx);
//            }
//        }
//
//        public void update(){
//            synchronized (delayedSFX) {
//                if (delayedSFX.isEmpty()) return;
//                for (DelayedSoundEffect sfx : delayedSFX.toArray(new DelayedSoundEffect[0])) {
//                    sfx.delay -= Game.elapsed;
//                    if (sfx.delay <= 0) {
//                        delayedSFX.remove(sfx);
//                        play(sfx.id, sfx.leftVol, sfx.rightVol, sfx.pitch);
//                    }
//                }
//            }
//        }
//
//        public void enable( boolean value ) {
//            enabled = value;
//        }
//
//        public void volume( float value ) {
//            globalVolume = value;
//        }
//
//        public boolean isEnabled() {
//            return enabled;
//        }
//    }


}
