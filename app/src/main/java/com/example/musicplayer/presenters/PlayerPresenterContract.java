package com.example.musicplayer.presenters;

public interface PlayerPresenterContract {
    interface View {
        public void setVisibilityToPlayingMode();
        public void setVisibilityToStoppedMode();
        public void setVisibilityToUnlikedMusic();
        public void setVisibilityToLikedMusic();
        public void updatesForNewMusic();
        public void openPlaylistScreen();
    }

    interface Presenter {

    }
}
