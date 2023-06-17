package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.musicplayer.databinding.ActivityPlayerBinding;
import com.example.musicplayer.mediaPLayer.MediaPlayerSingleton;
import com.example.musicplayer.models.Music;
import com.example.musicplayer.presenters.PlayerPresenterContract;
import com.example.musicplayer.repositories.MusicRepository;

public class PlayerActivity extends AppCompatActivity implements PlayerPresenterContract.View {
    private ActivityPlayerBinding binding;
    private String currentUrl;
    private PlayerPresenterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player);

        SharedPreferences prefs = getSharedPreferences("generalKey-Xml", MODE_PRIVATE);
        String playlistTitle = prefs.getString("currentPlaylistName", "");

        binding.playerTextViewPlaylistTitle.setText(playlistTitle);
        updatesForNewMusic();

        if (MediaPlayerSingleton.getInstance().isPlaying()){
            setVisibilityToStoppedMode();
        } else {
            setVisibilityToPlayingMode();
        }

        setVisibilityToUnlikedMusic();

        //Listeners
        //PLAY
        binding.playerImagePlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayerSingleton.getInstance().playMusic();
                setVisibilityToStoppedMode();
            }
        });
        //STOP
        binding.playerImagePauseMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayerSingleton.getInstance().pauseMusic();
                setVisibilityToPlayingMode();
            }
        });
        //NEXT
        binding.playerImageViewSkipNextMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nextMusicUrl = MusicRepository.getInstance().getUrlForNextMusicIfExists();
                MediaPlayerSingleton.getInstance().prepareMusic(nextMusicUrl, ()->{});
                updatesForNewMusic();
            }
        });
        //PREVIOUS
        binding.playerImageViewSkipPreviousMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String previousMusicUrl = MusicRepository.getInstance().getUrlForPreviousMusicIfExists();
                MediaPlayerSingleton.getInstance().prepareMusic(previousMusicUrl, ()->{});
                updatesForNewMusic();
            }
        });
        //LIKE
        binding.playerImageViewLikeMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibilityToLikedMusic();
            }
        });
        //UNLIKE
        binding.playerImageViewMusicLiked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibilityToUnlikedMusic();
            }
        });
        //BACK SCREEN
        binding.playerImageViewBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlaylistScreen();
            }
        });
    }

    @Override
    public void setVisibilityToPlayingMode() {
        binding.playerImagePauseMusic.setVisibility(View.GONE);
        binding.playerImagePlayMusic.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibilityToStoppedMode() {
        binding.playerImagePauseMusic.setVisibility(View.VISIBLE);
        binding.playerImagePlayMusic.setVisibility(View.GONE);
    }

    @Override
    public void setVisibilityToUnlikedMusic() {
        binding.playerImageViewLikeMusic.setVisibility(View.VISIBLE);
        binding.playerImageViewMusicLiked.setVisibility(View.GONE);
    }

    @Override
    public void setVisibilityToLikedMusic() {
        binding.playerImageViewLikeMusic.setVisibility(View.GONE);
        binding.playerImageViewMusicLiked.setVisibility(View.VISIBLE);
    }

    @Override
    public void updatesForNewMusic() {
        currentUrl = MediaPlayerSingleton.getInstance().getCurrentUrl();

        Music currentMusic = MusicRepository.getInstance().findMusicByUrl(currentUrl);

        binding.playerTextViewMusicName.setText(currentMusic.getName());
        binding.playerTextViewMusicArtist.setText(currentMusic.getArtist());
    }

    @Override
    public void openPlaylistScreen() {
        Intent intent = new Intent(this, PlaylistActivity.class);
        startActivity(intent);
    }
}