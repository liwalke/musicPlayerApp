package com.example.musicplayer.presenters;

public interface HomePresenterContract {
    interface View{
        public void showMessage(String message);
        public void inflateRecyclerViews();
    }
    interface Presenter{
        public void getAllPlaylists();
    }
}
