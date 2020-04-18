package com.pscube.splashscreenwithsignupandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void SignOut(View view) {
        SharedPreferences prefs =getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",false );
        editor.apply();
        startActivity(new Intent(Home.this,SignIn.class));

    }
}
