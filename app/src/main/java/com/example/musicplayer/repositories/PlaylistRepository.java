package com.example.musicplayer.repositories;

import com.example.musicplayer.models.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {
    private List<Playlist> playlistList;

    private static PlaylistRepository instance = null;

    private PlaylistRepository(){ this.playlistList = new ArrayList<>(); }

    public static PlaylistRepository getInstance(){
        if (instance == null){
            instance = new PlaylistRepository();
        }
        return instance;
    }

    public int getSize(){
        return playlistList.size();
    }

    public List<Playlist> getPlaylists(){ return playlistList; }

    public Playlist getPlaylistById(String id){
        for (Playlist playlist : playlistList){
            if (playlist.getId().equals(id)){
                return playlist;
            }
        }
        return null;
    }

    public void addPlaylist(int index, Playlist newPlaylist){
        if (newPlaylist != null){
            playlistList.add(index, newPlaylist);
        }
    }
}
