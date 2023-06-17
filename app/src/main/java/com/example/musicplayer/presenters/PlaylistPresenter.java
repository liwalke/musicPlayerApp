package com.example.musicplayer.presenters;

import android.content.Context;

import com.example.musicplayer.services.MusicService;

public class PlaylistPresenter implements PlaylistPresenterContract.Presenter {
    private PlaylistPresenterContract.View view;
    private Context mContext;
    private String playlistId;

    public PlaylistPresenter(PlaylistPresenterContract.View view, String playlistId, Context context) {
        this.view = view;
        this.mContext = context;
        this.playlistId = playlistId;
    }

    @Override
    public void getAllMusic() {
        MusicService.getAllMusics(mContext, playlistId, ()->{
            view.inflateRecyclerView();
        });
    }
}
