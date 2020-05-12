package com.androidsteroids.gymmemoreapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidsteroids.gymmemoreapp.database.dbmanager.DBManagerStatus;
import com.androidsteroids.gymmemoreapp.database.dbmanager.DBManagerUser;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import butterknife.BindView;
import butterknife.ButterKnife;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private DBManagerStatus dbManagerStatus = null;
    private DBManagerUser dbManagerUser = null;

    private CursorAdapter adapter;



    @BindView(R.id.email_login_textinput_layout)
    TextInputLayout _emailTextinputLayout;
    @BindView(R.id.password_login_textinput_layout)
    TextInputLayout _passwordLoginTextinput_layout;
    @BindView(R.id.email_login)
    EditText _emailText;
    @BindView(R.id.password_login)
    EditText _passwordText;
    @BindView(R.id.button_login)
    Button _loginButton;
    @BindView(R.id.signup_link)
    TextView _signup_link;
    @BindView(R.id.progressBar)
    ProgressBar _progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        dbManagerStatus = new DBManagerStatus(this);
        dbManagerStatus.open();
        dbManagerUser = new DBManagerUser(this);
        dbManagerUser.open();
        ButterKnife.bind(this);

        _signup_link.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                         startActivityForResult(intent, REQUEST_SIGNUP);
                     }
                 });


        _loginButton.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         try {
                             login();
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     }
                 });
             }





    public void login() throws IOException {

        Log.d(TAG, "Login");

        if(_emailText.getText().toString().isEmpty() || _passwordText.getText().toString().isEmpty()) {

            if(_emailText.getText().toString().isEmpty()){
                _emailTextinputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
                _emailTextinputLayout.setHint("Inserisci l'email!");
                _emailTextinputLayout.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));

            }

            if(_passwordText.getText().toString().isEmpty()){
                _passwordLoginTextinput_layout.setDefaultHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));
                _passwordLoginTextinput_layout.setHint("Inserisci la password!");
                _passwordLoginTextinput_layout.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#fa8282")));



            }

        } else {

            String email = _emailText.getText().toString();
            String password = _passwordText.getText().toString();
            new Connection().execute(email, password);
        }
    }


    private class Connection extends AsyncTask <String, String, JsonObject> {

        String toastMessage = null;
        @Override
        protected void onPreExecute() {

            _loginButton.setEnabled(false);
            _loginButton.setVisibility(View.GONE);
            _signup_link.setVisibility(View.GONE);
            _progressBar.setVisibility(View.VISIBLE);
            _emailText.setEnabled(false);
            _passwordText.setEnabled(false);

        }





        @Override
        protected JsonObject doInBackground(String... params) {
            URL url;
            HttpURLConnection urlConnection = null;
            JsonObject user =null;

            try {

                url = new URL("http://10.0.2.2:8080/GymMeMoreServer_war/user/login?email=" + params[0] + "&password=" + params[1]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(5000);


                int responseCode = urlConnection.getResponseCode();
                String responseMessage = urlConnection.getResponseMessage();

                if (responseCode == HttpURLConnection.HTTP_OK) {

                    String responseString = readStream(urlConnection.getInputStream());
                    Log.v("Server response", "User found: " + responseString);
                    user = JsonParser.parseString(responseString).getAsJsonObject();

                    int type = user.get("type").getAsInt();
                    int id = user.get("id").getAsInt();
                    String name =  user.get("name").getAsString();
                    String lastname = user.get("lastName").getAsString();
                    String email = user.get("email").getAsString();
                    String birthdate = user.get("birthDate").getAsString();

                    dbManagerStatus.update(1);
                    Log.e("Status","Status updated to logged");

                    dbManagerUser.insert(id, name, lastname, email,birthdate,type);
                    Log.e("User","User updated");

                    dbManagerStatus.close();
                    dbManagerUser.close();

                    if(type==0){
                        Intent i = new Intent(getApplicationContext(), UserHomeActivity.class);
                        startActivity(i);
                        finish();
                    } else if(type==1){

                        //Trainer

                    } else if(type==2){

                        //Proprietario palestra

                    } else if(type==3){

                        //Nutrizionista

                    }


                } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {

                    Log.e("Server response", "User not found!");
                    toastMessage="Utente non trovato!";
                } else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    Log.e("Server response", "Wrong password!");
                    toastMessage="Password errata!";
                }

            } catch (IOException e) {
                e.printStackTrace();
                toastMessage="Impossibile connettersi!";
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return user;
        }

        @Override
        protected void onPostExecute(JsonObject user) {
            _progressBar.setVisibility(View.GONE);
            _loginButton.setVisibility(View.VISIBLE);
            _signup_link.setVisibility(View.VISIBLE);
            _emailText.setEnabled(true);
            _passwordText.setEnabled(true);

            _loginButton.setEnabled(true);
            Toast responseToast = Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG);
            if(user==null)
                 responseToast.show();
        }
    }



    private String readStream(InputStream in) throws UnsupportedEncodingException {

            BufferedReader reader = null;
            StringBuffer response = new StringBuffer();

            try{

                reader= new BufferedReader(new InputStreamReader(in));
                String line ="";
                while ((line =  reader.readLine())!=null){
                    response.append(line);
                }

            } catch (IOException e){

                e.printStackTrace();

            } finally {

                if(reader!=null){


                 try{

                     reader.close();

                 } catch (IOException e){
                     e.printStackTrace();
                 }

                }
            }

            return response.toString();

        }

}