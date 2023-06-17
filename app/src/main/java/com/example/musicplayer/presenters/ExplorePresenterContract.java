package com.example.musicplayer.presenters;

public interface ExplorePresenterContract {
    interface View{
        public void inflateRecyclerView();

    }
    interface Presenter{
        public void getAllPlaylists();
    }
}
