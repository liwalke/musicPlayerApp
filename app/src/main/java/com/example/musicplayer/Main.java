package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.musicplayer.databinding.ActivityMainBinding;
import com.example.musicplayer.mediaPLayer.MediaPlayerSingleton;
import com.example.musicplayer.models.Music;
import com.example.musicplayer.repositories.MusicRepository;

public class Main extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        replaceFragment(new HomeFragment());

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.nav_explore:
                    replaceFragment(new ExploreFragment());
                    break;
                case R.id.nav_profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });

        binding.imageViewPlayIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibilityToStoppedMode();
                MediaPlayerSingleton.getInstance().playMusic();
            }
        });
        binding.imageViewPauseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibilityToPlayingMode();
                MediaPlayerSingleton.getInstance().pauseMusic();
            }
        });
        binding.imageViewNextIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nextMusicUrl = MusicRepository.getInstance().getUrlForNextMusicIfExists();
                MediaPlayerSingleton.getInstance().prepareMusic(nextMusicUrl, ()->{});
                updatesForNewMusic();
            }
        });
        binding.textViewMusicName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlayerActivity();
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(MediaPlayerSingleton.getInstance().isPrepared()){
            updatesForNewMusic();
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

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
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
        Music music = MusicRepository.getInstance().findMusicByUrl(MediaPlayerSingleton.getInstance().getCurrentUrl());
        binding.textViewMusicName.setText(music.getName());
    }

    private void openPlayerActivity(){
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }
}