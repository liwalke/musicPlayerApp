package com.example.musicplayer.mediaPLayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import com.example.musicplayer.services.ServiceDone;
import java.io.IOException;

public class MediaPlayerSingleton {
    private static MediaPlayerSingleton instance = null;
    private MediaPlayer mediaPlayer;
    private String currentUrl;
    private boolean isPrepared;

    private MediaPlayerSingleton(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        isPrepared = false;
    }

    public static MediaPlayerSingleton getInstance(){
        if(instance == null){
            instance = new MediaPlayerSingleton();
        }
        return instance;
    }

    public void prepareMusic(String url, ServiceDone cb){
        if (currentUrl == url){
            return;
        }

        mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource(url);
            currentUrl = url;
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    isPrepared = true;
                    playMusic();
                    if(cb != null){
                        cb.onServiceDone();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playMusic(){
        if(mediaPlayer.isPlaying() == false){
            mediaPlayer.start();
        }
    }

    public void pauseMusic(){
        if(mediaPlayer.isPlaying() == true){
            mediaPlayer.pause();
        }
    }

    public void stopMusic(){
        if(mediaPlayer.isPlaying() == true){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    public boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }

    public boolean isPrepared(){
        return isPrepared;
    }

    public String getCurrentUrl(){
        return currentUrl;
    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }
}
