package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.musicplayer.adapters.MusicAdapter;
import com.example.musicplayer.databinding.ActivityPlaylistBinding;
import com.example.musicplayer.mediaPLayer.MediaPlayerSingleton;
import com.example.musicplayer.models.Music;
import com.example.musicplayer.models.Playlist;
import com.example.musicplayer.presenters.PlaylistPresenter;
import com.example.musicplayer.presenters.PlaylistPresenterContract;
import com.example.musicplayer.repositories.MusicRepository;
import com.example.musicplayer.repositories.PlaylistRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class PlaylistActivity extends AppCompatActivity implements PlaylistPresenterContract.View, MusicAdapter.OnMusicListener {
    private String playlistId;
    private PlaylistPresenterContract.Presenter presenter;
    private List<Music> musicList;
    private ActivityPlaylistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_playlist);

        SharedPreferences prefs = getSharedPreferences("generalKey-Xml", MODE_PRIVATE);
        playlistId = prefs.getString("playlistId", "");

        System.out.println("Playlist ID: "+ playlistId);

        presenter = new PlaylistPresenter(this, playlistId, this);

        if(MediaPlayerSingleton.getInstance().isPrepared()){
            updatesForNewMusic();
        }

        presenter.getAllMusic();

        Playlist currentPlaylist = PlaylistRepository.getInstance().getPlaylistById(playlistId);
        binding.textViewTittle.setText(currentPlaylist.getName());
        binding.textViewDescription.setText(currentPlaylist.getDescription());
        Glide.with(this).load(currentPlaylist.getImageUrl()).into(binding.imageViewPlaylistCover);

        //PLAY
        binding.imageViewPlayIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibilityToStoppedMode();
                MediaPlayerSingleton.getInstance().playMusic();
            }
        });
        //PAUSE
        binding.imageViewPauseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibilityToPlayingMode();
                MediaPlayerSingleton.getInstance().pauseMusic();
            }
        });
        //NEXT
        binding.imageViewNextIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nextMusicUrl = MusicRepository.getInstance().getUrlForNextMusicIfExists();
                MediaPlayerSingleton.getInstance().prepareMusic(nextMusicUrl, ()->{});
                updatesForNewMusic();
            }
        });
        //OPEN PLAYER
        binding.textViewMusicName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlayerActivity();
            }
        });
        //BACK SCREEN
        binding.imageViewBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainScreen();
            }
        });
    }

    @Override
    public void inflateRecyclerView() {
        musicList = MusicRepository.getInstance().getMusics();

        MusicAdapter musicAdapter = new MusicAdapter(musicList, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);

        binding.recyclerViewMusic.setLayoutManager(llm);
        binding.recyclerViewMusic.setAdapter(musicAdapter);

        setupPopup();
    }

    @Override
    public void setupPopup() {
        if(MediaPlayerSingleton.getInstance().isPrepared()){
            binding.cardView.setVisibility(View.VISIBLE);
        } else {
            binding.cardView.setVisibility(View.GONE);
        }
        if(MediaPlayerSingleton.getInstance().isPlaying()){
            setVisibilityToStoppedMode();
        } else {
            setVisibilityToPlayingMode();
        }
    }

    @Override
    public void onMusicClick(int position) {
        Music music = musicList.get(position);

        System.out.println(playlistId);

        SharedPreferences prefs = getSharedPreferences("generalKey-Xml", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("currentPlaylistName", PlaylistRepository.getInstance().getPlaylistById(playlistId).getName());
        editor.commit();

        MediaPlayerSingleton.getInstance().prepareMusic(music.getUrl(), () -> {
            Intent intent = new Intent(this, PlayerActivity.class);
            startActivity(intent);
        });
    }
    private void setVisibilityToPlayingMode(){
        binding.imageViewPlayIcon.setVisibility(View.VISIBLE);
        binding.imageViewPauseIcon.setVisibility(View.GONE);
    }
    private void setVisibilityToStoppedMode(){
        binding.imageViewPlayIcon.setVisibility(View.GONE);
        binding.imageViewPauseIcon.setVisibility(View.VISIBLE);
    }
    private void updatesForNewMusic(){
        String currentUrl = MediaPlayerSingleton.getInstance().getCurrentUrl();
        System.out.println("Current URL: "+currentUrl);
        Music music = MusicRepository.getInstance().findMusicByUrl(currentUrl);
        binding.textViewMusicName.setText(music.getName());
    }

    private void openPlayerActivity(){
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }
    private void openMainScreen(){
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }
}