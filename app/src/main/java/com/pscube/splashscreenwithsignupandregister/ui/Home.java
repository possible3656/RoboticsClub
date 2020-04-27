package com.pscube.splashscreenwithsignupandregister.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pscube.splashscreenwithsignupandregister.R;
import com.pscube.splashscreenwithsignupandregister.login_signup.SignIn;

public class Home extends AppCompatActivity  {

    //Image button
    ImageView bellIcon ,menuIcon;

    HomeFragment homeFragment;

    String openClose="close";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeFragment = new HomeFragment();
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.MainContainer,homeFragment,null);


        fragmentTransaction.commit();

        bellIcon=findViewById(R.id.bellIcon);
        menuIcon=findViewById(R.id.menuIcon);


        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (openClose){

                    case "close":
                        openClose ="open";

                        DrawerFragment drawerFragment = new DrawerFragment();
                           getSupportFragmentManager().beginTransaction()
                                .add(R.id.MainContainer,drawerFragment,"drawerFragment")
                                   .setCustomAnimations(R.anim.right_to_left,R.anim.left_to_right)
                                .addToBackStack(null)
                                .commit();

                        Log.d("open", "onClick: open");
                        break;
                    case "open":
                        Home.super.onBackPressed();

                       DrawerFragment drawerFragment1 = (DrawerFragment) getSupportFragmentManager().findFragmentByTag("drawerFragment");
                        if (drawerFragment1 != null && drawerFragment1.isVisible()) {
                            // add your code here
                            Home.super.onBackPressed();
                        }
                        Log.d("close", "onClick:close ");

                        openClose="close";

                        break;



                }
            }
        });



        bellIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (openClose){

                    case "close":

                        NoticeFragment noticeFragment = new NoticeFragment();
                        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction()
                                .add(R.id.MainContainer,noticeFragment,null);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        openClose ="open";
                        break;
                    case "open":
                        Home.super.onBackPressed();

                        openClose="close";

                        break;



                }
            }
        });
    }


    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {

        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();

            return;
        }
        openClose="close"; }


}
