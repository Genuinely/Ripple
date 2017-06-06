package genuinely.ripple;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

/**
 * Created by neilyeung on 5/22/17.
 */
public class AudioManager {

    //Fields
    private MediaPlayer mp = null;
    private String musicFile;
    private State state;

    //Gather the states as Constants
    public enum State {
        PLAYING,
        STOPPED,
        PAUSED
    }

    public AudioManager(MediaPlayer in) {

        state = State.STOPPED;
        mp = in;

    }

    /*
    public void openFile(String name) {

        if (mp == null) return;
        musicFile = name;

        mp.reset();
        AssetFileDescriptor afd;
        afd = getAssets().openFd(musicFile);
        mp.setData

    } */

    public void start() {
        if (state == State.STOPPED) {
            state = State.PLAYING;
            mp.start();
        }
    }

    public void stop() {
        if (state == State.PLAYING) {
            state = State.STOPPED;
            mp.stop();
        }
    }

    public void resume() {

        if (state == State.PAUSED) {
            state = State.PLAYING;
            mp.start();
        }
    }

    public void pause() {

        if (state == State.PLAYING) {
            state = State.PAUSED;
            mp.pause();
        }
    }

    public boolean isPlaying() {

        if (state == State.PLAYING) {
            return true;
        }

        else {

            return false;
        }
    }


    public boolean isStopped() {

        if (state == State.STOPPED) {
            return true;
        }

        else {

            return false;
        }
    }

    public boolean isPaused() {

        if (state == State.PAUSED) {
            return true;
        }

        else {

            return false;
        }
    }
}

