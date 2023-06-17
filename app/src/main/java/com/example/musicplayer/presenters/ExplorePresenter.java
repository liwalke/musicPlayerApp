package com.example.musicplayer.presenters;

import android.content.Context;

import com.example.musicplayer.services.PlaylistService;

public class ExplorePresenter implements ExplorePresenterContract.Presenter {
    private ExplorePresenterContract.View view;
    private Context mContext;

    public ExplorePresenter(ExplorePresenterContract.View view, Context context){
        this.view = view;
        this.mContext = context;
    }

    @Override
    public void getAllPlaylists() {
        PlaylistService.getAllPlaylists(mContext, () -> {
            view.inflateRecyclerView();
        });
    }
}
