package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.musicplayer.models.Playlist;
import com.example.musicplayer.repositories.PlaylistRepository;

public class PlaylistActivity extends AppCompatActivity {
    private int playlistId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        //Get extras
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null){
            playlistId = (int)b.get("playlistId");

            Playlist currentPlaylist = PlaylistRepository.getInstance().getPlaylist(playlistId);
        }
    }
}