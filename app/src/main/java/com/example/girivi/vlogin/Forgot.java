package com.example.girivi.vlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Forgot extends AppCompatActivity {
    EditText forgot;
    Button Submit;
    private static final String Forgot_URL = "http://triphpe.16mb.com/forgot.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        forgot=(EditText)findViewById(R.id.etemail);
        Submit=(Button)findViewById(R.id.btnsubmit);

    }
}
