package com.androidsteroids.gymmemoreapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindInt;
import butterknife.BindView;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @BindView(R.id.name_signup_textinput_layout)
    TextInputLayout _nameTextInputLayout;
    @BindView(R.id.lastname_signup_textinput_layout)
    TextInputLayout _lastNameTextInputLayout;
    @BindView(R.id.email_signup_textinput_layout)
    TextInputLayout _emailTextInputLayout;
    @BindView(R.id.birthdate_signup_textinput_layout)
    TextInputLayout _birthDateTextInputLayout;
    @BindView(R.id.password_signup_textinput_layout)
    TextInputLayout _passwordTextInputLayout;
    @BindView(R.id.password_confirm_signup_textinput_layout)
    TextInputLayout _passwordConfirmTextInputLayout;

    @BindView(R.id.name_signup)
    TextInputEditText _nameText;
    @BindView(R.id.lastname_signup)
    TextInputEditText _lastameText;
    @BindView(R.id.email_signup)
    TextInputEditText _emailText;
    @BindView(R.id.birthdate_signup)
    TextInputEditText _birthdateText;
    @BindView(R.id.password_signup)
    TextInputEditText _passwordText;
    @BindView(R.id.password_confirm_signup)
    TextInputEditText _passwordConfirmText;

    @BindView(R.id.back_to_login_button)
    Button _backButton;
    @BindView(R.id.signup_button)
    Button _signupButton;
    @BindView(R.id.signup_video_view)
    VideoView _signupVideoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    signup();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void signup(){

    checkNetwork();

        Log.d(TAG,"Signup");

///Controlli su campi di input




    }



    private void startVideo(){

        _signupVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        MediaPlayer mediaPlayer = new MediaPlayer();


        _signupVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.flessioni));

        _signupVideoView.start();




    }

    private void checkNetwork(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();


        if (activeNetworkInfo == null) {
            Intent i = new Intent(getApplicationContext(), NoNetworkActivity.class);
            startActivity(i);
            finish();
        }

    }

}
