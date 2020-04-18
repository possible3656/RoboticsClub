package com.pscube.splashscreenwithsignupandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SignIn extends AppCompatActivity {
    EditText emailEditText , passwordEditText;
    TextView signUpTxt;
    Button signInButton;
    boolean firstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // lets do same stuff

        emailEditText= findViewById(R.id.editTextEmail);
        passwordEditText= findViewById(R.id.editTextPassword);
        signUpTxt= findViewById(R.id.textSignUp);
        signInButton= findViewById(R.id.buttonSignIn);
        firstStart=false;

        SharedPreferences prefs =  getSharedPreferences("prefs",MODE_PRIVATE);
        firstStart= prefs.getBoolean("firstStart",false);

        if (firstStart){
            startActivity(new Intent(this,Home.class));

        }




    }


    //when Sign in button pressed
    public void SignIn(View view) {


        startActivity(new Intent(this,Home.class));
        SharedPreferences prefs =getSharedPreferences("prefs",MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstStart",true );
            editor.apply();

    }


    //when Sign up clicked
    public void SignUp(View view) {
        startActivity(new Intent(SignIn.this,SignUp.class));
    }
}
