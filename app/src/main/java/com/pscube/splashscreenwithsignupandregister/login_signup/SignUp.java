package com.pscube.splashscreenwithsignupandregister.login_signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.pscube.splashscreenwithsignupandregister.R;

public class SignUp extends AppCompatActivity {
    EditText  emailEditText , passwordEditText;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailEditText=findViewById(R.id.editTextEmailSU);
        passwordEditText=findViewById(R.id.editTextPasswordSU);
        progressBar=findViewById(R.id.progressbarsignup);
        mAuth = FirebaseAuth.getInstance();


    }

    public void CreateAccount(View view) {


        registeruser();



    }

    private void sharedpref(String username, String password) {
        Intent intent = new Intent(SignUp.this, AccountInformation.class);



        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        SharedPreferences prefs =getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",true );
        editor.apply();
    }


    private void registeruser() {
        final String name , username , password;

        username=emailEditText.getText().toString().trim();
        password=passwordEditText.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        if ( username.isEmpty() | password.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            }
            if (password.length()<8){
                Toast.makeText(this, "Minimum length of password is 8", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }


        else{



             mAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()){
                         Toast.makeText(SignUp.this, "Welcome to our club", Toast.LENGTH_SHORT).show();
                         progressBar.setVisibility(View.GONE);
                         sharedpref(username,password);

                     }

                     else {
                         if (task.getException() instanceof FirebaseAuthUserCollisionException){

                             Toast.makeText(SignUp.this,"Email is already registered",Toast.LENGTH_SHORT).show();
                             progressBar.setVisibility(View.GONE);
                         }

                        else {
                             Toast.makeText(SignUp.this, "hmmm... Something is wrong", Toast.LENGTH_SHORT).show();
                             progressBar.setVisibility(View.GONE);
                         }

                     }
                 }
             });



        }



    }

    public void SighIN(View view) {
        startActivity(new Intent(SignUp.this,SignIn.class));
    }

    @Override
    public void onBackPressed() {

    }
}
