package com.example.girivi.vlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Session extends AppCompatActivity {


    public Session(int v){
        this.temp =+1;
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

    }

    public Session(){}
    int temp=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if((temp%2)==0){
            temp =temp+1;
            Intent i = new Intent(getApplicationContext(), Splash.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(getApplicationContext(), Profile.class);
            startActivity(i);
        }



    }
}
