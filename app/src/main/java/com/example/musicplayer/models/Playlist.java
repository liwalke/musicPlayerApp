package com.example.musicplayer.models;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.List;

public class Playlist {
    private String id;
    private String name;
    private String idCreator;
    private String description;
    private List<String> idMusicList;
    private String imageUrl;

    public Playlist(){}

    public Playlist(String id, String name, String idCreator, String description, List<String> idMusicList, String imageUrl) {
        this.id = id;
        this.name = name;
        this.idCreator = idCreator;
        this.description = description;
        this.idMusicList = idMusicList;
        this.imageUrl = imageUrl;
    }

    public Playlist(QueryDocumentSnapshot document){
        this.id = document.getId();
        this.name = document.getString("name");
        this.idCreator = document.getString("idCreator");
        this.description = document.getString("description");
        this.idMusicList = (List<String>) document.get("idMusicList");
        this.imageUrl = document.getString("imageUrl");
    }

    /*public Playlist(JSONObject json){
        try {
            this.id = json.getString("id");
            this.name = json.getString("name");
            this.idCreator = json.getString("idCreator");
            this.description = json.getString("description");
            this.idMusicList = null; //Pendência
            this.imageUrl = json.getString("imageUrl");;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }*/

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIdCreator() {
        return idCreator;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getIdMusicList() {
        return idMusicList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", idCreator='" + idCreator + '\'' +
                ", description='" + description + '\'' +
                ", idMusicList=" + idMusicList +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
