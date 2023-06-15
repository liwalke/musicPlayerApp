package com.example.musicplayer.repositories;

import com.example.musicplayer.models.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicRepository {
    private List<Music> musicList;

    private static MusicRepository instance = null;

    private MusicRepository(){ this.musicList = new ArrayList<>(); }

    public static MusicRepository getInstance(){
        if(instance == null){
            instance = new MusicRepository();
        }
        return instance;
    }

    public List<Music> getMusics(){ return musicList; }

    public Music getMusic(int id){ return musicList.get(id); }

    public void addMusic(int index, Music newMusic){
        if (newMusic != null){
            musicList.add(index, newMusic);
        }
    }
}
