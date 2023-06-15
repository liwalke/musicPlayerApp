package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.musicplayer.databinding.ActivityLoginBinding;
import com.example.musicplayer.presenters.LoginPresenter;
import com.example.musicplayer.presenters.LoginPresenterContract;

public class Login extends AppCompatActivity implements LoginPresenterContract.View{
    private ActivityLoginBinding binding;
    private LoginPresenterContract.Presenter presenter;
    private boolean isShowingLoginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.buttonLoginOrSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.editTextTextEmailAddress.getText().toString();
                String password = binding.editTextTextPassword.getText().toString();

                if (checkCredentials(email, password) && isShowingLoginLayout) {
                    presenter.validateLoginWithEmailAndPassword(email, password);
                } else if (checkCredentials(email, password) && !isShowingLoginLayout) {
                    presenter.createLoginWithEmailAndPassword(email, password);
                }
            }
        });

        binding.textViewChangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowingLoginLayout){
                    showSignUpLayout();
                } else {
                    showLoginLayout();
                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(presenter.getFirebaseUser() == null){
            showLoginLayout();
        } else {
            performLogin();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void performLogin() {
        if (presenter.getFirebaseUser() != null) {
            showMessage("User Logged In!");
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
        }
    }

    @Override
    public void performLogout(){
        showMessage("User Logged Out!");
        presenter.signOut();
    }

    @Override
    public void showLoginLayout() {
        binding.buttonLoginOrSignUp.setText("Login");
        binding.textViewLoginSignUp.setText("Login");
        binding.textViewChangeLayout.setText("New to Jucafy? Join now");
        isShowingLoginLayout = true;
    }

    @Override
    public void showSignUpLayout() {
        binding.buttonLoginOrSignUp.setText("Sign Up");
        binding.textViewLoginSignUp.setText("Sign Up");
        binding.textViewChangeLayout.setText("Already on Jucafy? Sign in");
        isShowingLoginLayout = false;
    }

    @Override
    public boolean checkCredentials(String email, String password){
        if (email.isEmpty() || password.isEmpty()) return false;
        return true;
    }
}