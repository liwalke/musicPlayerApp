package com.example.musicplayer.presenters;

public interface HomePresenterContract {
    interface View{
        public void showMessage(String message);
        public void inflateRecyclerViews();
        //public void onPlaylistClick(int position);
    }
    interface Presenter{
        public void getAllPlaylists();
    }
}
