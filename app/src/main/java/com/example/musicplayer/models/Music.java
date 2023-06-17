package com.example.musicplayer.models;

import com.google.firebase.firestore.DocumentSnapshot;

public class Music {
    private String id;
    private String name;
    private String url;
    private String album;
    private String artist;
    private String genre;

    public Music(){}

    public Music(DocumentSnapshot document){
        this.id = document.getId();
        this.name = document.getString("name");
        this.url = document.getString("url");
        this.album = document.getString("album");
        this.artist = document.getString("artist");
        this.genre = document.getString("genre");
    }

    public Music(String id, String url, String name, String album, String artist, String genre) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.genre = genre;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}