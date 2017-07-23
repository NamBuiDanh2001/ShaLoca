package com.example.hp_bdn.shaloca.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.hp_bdn.shaloca.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class WellcomActivity extends AppCompatActivity {
    private final String PROFILE_USER = "profile_user";
    private final String EMAIL = "email";
    private final String AutoLogin = "auto_login";
    private static final String PASS = "pass";
    private ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcom);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        AsyntaskWellcom asyntaskWellcom = new AsyntaskWellcom();
        asyntaskWellcom.execute();
    }
    private boolean Autologin() {
        SharedPreferences sharedPreferences = getSharedPreferences(PROFILE_USER , MODE_PRIVATE);
        String email =sharedPreferences.getString(EMAIL , null);
        String pass= sharedPreferences.getString(PASS, null);
        return sharedPreferences.getBoolean(AutoLogin , false);

    }
    class  AsyntaskWellcom extends AsyncTask<Void ,Void ,Boolean >{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            return Autologin();

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Intent intent ;
            super.onPostExecute(aBoolean);
            if(aBoolean){
                intent = new Intent(WellcomActivity.this , MainActivity.class);
            }
            else {
                intent = new Intent(WellcomActivity.this , LoginActivity.class);
            }
            startActivity(intent);
            finish();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
