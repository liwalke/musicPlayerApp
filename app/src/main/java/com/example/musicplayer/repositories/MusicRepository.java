package com.example.musicplayer.repositories;

import com.example.musicplayer.mediaPLayer.MediaPlayerSingleton;
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

    public int getSize(){
        return musicList.size();
    }

    public List<Music> getMusics(){ return musicList; }

    public Music getMusicById(String id){
        for (Music music : musicList){
            if (music.getId().equals(id)){
                return music;
            }
        }
        return null;
    }

    public void addMusic(int index, Music newMusic){
        if (newMusic != null){
            musicList.add(index, newMusic);
        }
    }

    public String getUrlForPreviousMusicIfExists(){
        String currentUrl = MediaPlayerSingleton.getInstance().getCurrentUrl();
        int i = 0;
        for (Music music : musicList){
            if(music.getUrl().equals(currentUrl)){
                if(i == 0){
                    return musicList.get(musicList.size()).getUrl(); //Return the last one
                }
                return musicList.get(i - 1).getUrl();
            }
            i++;
        }
        return "";
    }

    public String getUrlForNextMusicIfExists(){
        String currentUrl = MediaPlayerSingleton.getInstance().getCurrentUrl();
        int i = 0;
        for (Music music : musicList){
            if(music.getUrl().equals(currentUrl)){
                if(i >= musicList.size()){
                    return musicList.get(0).getUrl(); //Return the first one
                }
                return musicList.get(i + 1).getUrl();
            }
            i++;
        }
        return "";
    }

    public Music findMusicByUrl (String url){
        if (musicList.size() == 0) return null;

        for(Music music : musicList){
            if(music.getUrl().equals(url)){
                return music;
            }
        }
        return null;
    }

    public void clearList(){
        musicList.clear();
    }
}
