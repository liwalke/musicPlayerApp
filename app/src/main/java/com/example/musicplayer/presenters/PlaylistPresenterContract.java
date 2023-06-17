package com.example.musicplayer.presenters;

public interface PlaylistPresenterContract {
    interface View {
        public void inflateRecyclerView();
        public void setupPopup();
    }
    interface Presenter {
        public void getAllMusic();
    }
}
