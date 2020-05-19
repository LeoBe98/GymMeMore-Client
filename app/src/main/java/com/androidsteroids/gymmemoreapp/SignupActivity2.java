package com.androidsteroids.gymmemoreapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidsteroids.gymmemoreapp.utils.Fx;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity2 extends AppCompatActivity {

    @BindView(R.id.back_to_signup1_button)
    ImageButton _back_to_signup1_button;

    @BindView(R.id.dropdown_button_gym_user)
    ImageButton _dropdown_button_gym_user;
    @BindView(R.id.signup_gym_user_info)
    LinearLayout _signup_gym_user_info;
    @BindView(R.id.signup_button_gym_user)
    Button _signup_button_gym_user;

    @BindView(R.id.dropdown_button_gym_trainer)
    ImageButton _dropdown_button_gym_trainer;
    @BindView(R.id.signup_gym_trainer_info)
    LinearLayout _signup_gym_trainer_info;
    @BindView(R.id.signup_button_gym_trainer)
    Button _signup_button_gym_trainer;

    @BindView(R.id.dropdown_button_gym_nutritionist)
    ImageButton _dropdown_button_gym_nutritionist;
    @BindView(R.id.signup_gym_nutritionist_info)
    LinearLayout _signup_gym_nutritionist_info;
    @BindView(R.id.signup_button_gym_nutritionist)
    Button _signup_button_gym_nutritionist;

    @BindView(R.id.dropdown_button_gym_owner)
    ImageButton _dropdown_button_gym_owner;
    @BindView(R.id.signup_gym_owner_info)
    LinearLayout _signup_gym_owner_info;
    @BindView(R.id.signup_button_gym_owner)
    Button _signup_button_gym_owner;

    private String email;
    private String name;
    private String lastname;
    private String birthdate;
    private String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.signup_activity2);
        ButterKnife.bind(this);


        _back_to_signup1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        name = getIntent().getStringExtra("name");
        lastname = getIntent().getStringExtra("lastname");
        email = getIntent().getStringExtra("email");
        birthdate = getIntent().getStringExtra("birthdate");
        password = getIntent().getStringExtra("password");

        _signup_gym_user_info.setVisibility(View.GONE);
        _signup_gym_trainer_info.setVisibility(View.GONE);
        _signup_gym_nutritionist_info.setVisibility(View.GONE);
        _signup_gym_owner_info.setVisibility(View.GONE);


        _dropdown_button_gym_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownGymUser();
            }
        });


        _dropdown_button_gym_trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownGymTrainer();
            }
        });

        _dropdown_button_gym_nutritionist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownNutritionist();
            }
        });

        _dropdown_button_gym_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownGymOwner();
            }
        });



    }



    private void dropdownGymUser() {
        if (_signup_gym_user_info.isShown()) {
            Fx.slide_up(getApplicationContext(), _signup_gym_user_info);
            _signup_gym_user_info.setVisibility(View.GONE);
            _dropdown_button_gym_user.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
        } else {
            Fx.slide_down(getApplicationContext(), _signup_gym_user_info);
            _signup_gym_user_info.setVisibility(View.VISIBLE);
            _dropdown_button_gym_user.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp);

            if (_signup_gym_trainer_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_trainer_info);
                _signup_gym_trainer_info.setVisibility(View.GONE);
                _dropdown_button_gym_trainer.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }

            if (_signup_gym_nutritionist_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_nutritionist_info);
                _signup_gym_nutritionist_info.setVisibility(View.GONE);
                _dropdown_button_gym_nutritionist.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }

            if (_signup_gym_owner_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_owner_info);
                _signup_gym_owner_info.setVisibility(View.GONE);
                _dropdown_button_gym_owner.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }

        }
    }

    private void dropdownGymTrainer() {
        if (_signup_gym_trainer_info.isShown()) {
            Fx.slide_up(getApplicationContext(), _signup_gym_trainer_info);
            _signup_gym_trainer_info.setVisibility(View.GONE);
            _dropdown_button_gym_trainer.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
        } else {

            if (_signup_gym_user_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_user_info);
                _signup_gym_user_info.setVisibility(View.GONE);
                _dropdown_button_gym_user.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }

            Fx.slide_down(getApplicationContext(), _signup_gym_trainer_info);
            _signup_gym_trainer_info.setVisibility(View.VISIBLE);
            _dropdown_button_gym_trainer.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp);

            if (_signup_gym_nutritionist_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_nutritionist_info);
                _signup_gym_nutritionist_info.setVisibility(View.GONE);
                _dropdown_button_gym_nutritionist.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }

            if (_signup_gym_owner_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_owner_info);
                _signup_gym_owner_info.setVisibility(View.GONE);
                _dropdown_button_gym_owner.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }

        }
    }

    private void dropdownNutritionist() {
        if (_signup_gym_nutritionist_info.isShown()) {
            Fx.slide_up(getApplicationContext(), _signup_gym_nutritionist_info);
            _signup_gym_nutritionist_info.setVisibility(View.GONE);
            _dropdown_button_gym_nutritionist.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
        } else {

            if (_signup_gym_user_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_user_info);
                _signup_gym_user_info.setVisibility(View.GONE);
                _dropdown_button_gym_user.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }

            if (_signup_gym_trainer_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_trainer_info);
                _signup_gym_trainer_info.setVisibility(View.GONE);
                _dropdown_button_gym_trainer.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }

            Fx.slide_down(getApplicationContext(), _signup_gym_nutritionist_info);
            _signup_gym_nutritionist_info.setVisibility(View.VISIBLE);
            _dropdown_button_gym_nutritionist.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp);

            if (_signup_gym_owner_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_owner_info);
                _signup_gym_owner_info.setVisibility(View.GONE);
                _dropdown_button_gym_owner.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }

        }
    }

    private void dropdownGymOwner() {
        if (_signup_gym_owner_info.isShown()) {
            Fx.slide_up(getApplicationContext(), _signup_gym_owner_info);
            _signup_gym_owner_info.setVisibility(View.GONE);
            _dropdown_button_gym_owner.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
        } else {

            if (_signup_gym_user_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_user_info);
                _signup_gym_user_info.setVisibility(View.GONE);
                _dropdown_button_gym_user.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }

            if (_signup_gym_trainer_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_trainer_info);
                _signup_gym_trainer_info.setVisibility(View.GONE);
                _dropdown_button_gym_trainer.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }

            if (_signup_gym_nutritionist_info.isShown()) {
                Fx.slide_up(getApplicationContext(), _signup_gym_nutritionist_info);
                _signup_gym_nutritionist_info.setVisibility(View.GONE);
                _dropdown_button_gym_nutritionist.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            }


            Fx.slide_down(getApplicationContext(), _signup_gym_owner_info);
            _signup_gym_owner_info.setVisibility(View.VISIBLE);
            _dropdown_button_gym_owner.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp);
        }
    }










}



