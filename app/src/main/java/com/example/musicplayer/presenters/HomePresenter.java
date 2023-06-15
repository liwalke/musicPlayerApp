package com.example.musicplayer.presenters;

import android.content.Context;

import com.example.musicplayer.services.PlaylistService;

public class HomePresenter implements HomePresenterContract.Presenter {
    private HomePresenterContract.View view;
    private Context mContext;

    public HomePresenter(HomePresenterContract.View view, Context context) {
        this.view = view;
        this.mContext = context;
    }

    @Override
    public void getAllPlaylists() {
        PlaylistService.getAllPlaylists(mContext, ()->{
            view.inflateRecyclerViews();
        });
    }
}
