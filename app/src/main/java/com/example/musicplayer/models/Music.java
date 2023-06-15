package com.example.musicplayer.models;

public class Music {
    private String id;
    private String name;
    private String url;
    private String album;
    private String artist;
    private String genre;

    public Music(){}

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
}