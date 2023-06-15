package com.example.musicplayer.presenters;

import com.google.firebase.auth.FirebaseUser;

public interface LoginPresenterContract {
    interface View {
        public void showMessage(String message);
        public void performLogin();
        public void performLogout();
        public void showLoginLayout();
        public void showSignUpLayout();
        public boolean checkCredentials(String email, String password);
    }
    interface Presenter {
        public void createLoginWithEmailAndPassword(String email, String password);
        public boolean validateLoginWithEmailAndPassword(String email, String password);
        public void signOut();
        public FirebaseUser getFirebaseUser();
    }
}
