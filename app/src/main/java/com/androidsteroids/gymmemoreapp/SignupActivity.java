package com.androidsteroids.gymmemoreapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

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
    ImageButton _backButton;
    @BindView(R.id.signup_button)
    Button _signupButton;
    @BindView(R.id.signup_video_view)
    VideoView _signupVideoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        ButterKnife.bind(this);


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

        if(_nameText.getText().toString().isEmpty()){
            _nameTextInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
            _nameTextInputLayout.setHint("Inserisci il tuo nome!");
            _nameTextInputLayout.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
        }

        if(_lastameText.getText().toString().isEmpty()){
            _lastNameTextInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
            _lastNameTextInputLayout.setHint("Inserisci il tuo cognome!");
            _lastNameTextInputLayout.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
        }

        if(_emailText.getText().toString().isEmpty()){
            _emailTextInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
            _emailTextInputLayout.setHint("Inserisci la tua mail!");
            _emailTextInputLayout.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
        } else if (!validateMail(_emailText.getText().toString()) && !_emailText.getText().toString().isEmpty()){
            _emailTextInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
            _emailTextInputLayout.setHint("Formato non valido!");
            _emailTextInputLayout.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
        }

        if(_passwordText.getText().toString().isEmpty()){
            _passwordTextInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
            _passwordTextInputLayout.setHint("Inserisci la tua password!");
            _passwordTextInputLayout.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
        } else if (!_passwordText.getText().toString().isEmpty() && !validatePassword(_passwordText.getText().toString())){
            _passwordTextInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
            _passwordTextInputLayout.setHint("Password non valida!");
            _passwordTextInputLayout.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
            _passwordTextInputLayout.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
            _passwordTextInputLayout.setHelperText("La password deve contenere:\n"+ "\u2022 Un carattere maiusciolo e uno minuscolo\n"+
                    "\u2022 Un simbolo\n" + "\u2022 Almeno 8 caratteri\n" + "\u2022 Nessuno spazio");
        }


 // Manca il check per la data.

        if(_passwordConfirmText.getText().toString().isEmpty()){
            _passwordConfirmTextInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
            _passwordConfirmTextInputLayout.setHint("Inserisci di nuovo la tua password!");
            _passwordConfirmTextInputLayout.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
        } else if (_passwordConfirmText.getText().toString()!=_passwordText.getText().toString() && (validatePassword(_passwordText.getText().toString()))){
            _passwordConfirmTextInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
            _passwordConfirmTextInputLayout.setHint("Le due password sono diverse!");
            _passwordConfirmTextInputLayout.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
        }



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

    private boolean validateMail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private boolean validatePassword (String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

}
