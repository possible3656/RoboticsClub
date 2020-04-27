package com.pscube.splashscreenwithsignupandregister.login_signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pscube.splashscreenwithsignupandregister.R;
import com.pscube.splashscreenwithsignupandregister.ui.Home;

import java.util.Objects;

public class SignIn extends AppCompatActivity {
    EditText emailEditText , passwordEditText;
    TextView signUpTxt;
    Button signInButton;
    boolean firstStart;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // lets do same stuff

        emailEditText= findViewById(R.id.editTextEmail);
        passwordEditText= findViewById(R.id.editTextPassword);
        signUpTxt= findViewById(R.id.textSignUp);
        signInButton= findViewById(R.id.buttonSignIn);
        progressBar=findViewById(R.id.progressbarsignin);
        mAuth = FirebaseAuth.getInstance();


        firstStart=false;

        SharedPreferences prefs =  getSharedPreferences("prefs",MODE_PRIVATE);
        firstStart= prefs.getBoolean("firstStart",false);

        if (firstStart){
           Intent intent = new Intent(this,Home.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();

        }
    }


    //when Sign in button pressed
    public void Signin(View view) {

            Loginuser();




    }

    private void Loginuser() {
        String username , password;

        username=emailEditText.getText().toString().trim();
        password=passwordEditText.getText().toString().trim();

        if (username.isEmpty() | password.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();

            if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()){

                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            }
            if (password.length()<8){
                Toast.makeText(this, "Minimum length of password is 8", Toast.LENGTH_SHORT).show();

            }
        }

        if (password.length()<8){
            Toast.makeText(this, "Minimum length of password is 8", Toast.LENGTH_SHORT).show();

        }
        else{
            progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignIn.this, "Welcome back", Toast.LENGTH_SHORT).show();
                   progressBar.setVisibility(View.GONE);
                    sharedpref();
                }else {
                    Toast.makeText(SignIn.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }


            }
        });




    }}


    private void sharedpref() {

        Intent intent= new Intent(SignIn.this,Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish();

        SharedPreferences prefs =getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",true );
        editor.apply();
    }


    //when Sign up clicked
    public void SignUp(View view) {
        startActivity(new Intent(SignIn.this,SignUp.class));
    }


    @Override
    public void onBackPressed() {

    }

    public void forgetPassword(View view) {
        startActivity(new Intent(SignIn.this,ForgetPassword.class));
    }
}
