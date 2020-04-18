package com.pscube.splashscreenwithsignupandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    EditText nameEditText , emailEditText , passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameEditText=findViewById(R.id.editTextNameSU);
        emailEditText=findViewById(R.id.editTextEmailSU);
        passwordEditText=findViewById(R.id.editTextPasswordSU);




    }

    public void CreateAccount(View view) {




        startActivity(new Intent(this,Home.class));
        SharedPreferences prefs =getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",true );
        editor.apply();
    }

    public void SighIN(View view) {
        startActivity(new Intent(SignUp.this,SignIn.class));
    }
}
