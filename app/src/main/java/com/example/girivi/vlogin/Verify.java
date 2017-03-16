package com.example.girivi.vlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Verify extends AppCompatActivity {
    EditText ver;
    Button veri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ver=(EditText)findViewById(R.id.etOTP);
        veri=(Button)findViewById(R.id.btnverify);
    }
    public void verify(View view){

        String otp = ver.getText().toString().trim();

        Bundle extras = getIntent().getExtras();
        String userOtp= extras.getString("OTP");
        String Email = extras.getString("EMAIL");

        if(otp.equals(userOtp)){
            Intent i = new Intent(getApplicationContext(),Reset.class);
            i.putExtra("KEY", Email);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "Incorrect OTP", Toast.LENGTH_LONG).show();
        }
    }
}
