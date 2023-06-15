package com.example.musicplayer.presenters;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements LoginPresenterContract.Presenter{
    private LoginPresenterContract.View view;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public LoginPresenter(LoginPresenterContract.View view){
        this.view = view;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        FirebaseApp fApp = FirebaseApp.initializeApp((Activity) view);
    }

    @Override
    public void createLoginWithEmailAndPassword(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) view, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseUser = firebaseAuth.getCurrentUser();
                    view.performLogin();
                } else {
                    view.showMessage("Sign In failed.");
                }
            }
        });
    }

    @Override
    public boolean validateLoginWithEmailAndPassword(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) view, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseUser = firebaseAuth.getCurrentUser();
                    view.performLogin();
                } else {
                    view.showMessage("Wrong credentials.");
                }
            }
        });
        return true;
    }

    @Override
    public void signOut() {
        firebaseAuth.signOut();
        firebaseUser = null;
    }

    @Override
    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }
}
