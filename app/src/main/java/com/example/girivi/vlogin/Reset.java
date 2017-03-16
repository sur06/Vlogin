package com.example.girivi.vlogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Reset extends AppCompatActivity implements View.OnClickListener{

    public static final String Forgot_URL = "http://triphpe.16mb.com/reset.php";

    public static final String KEY_EMAIL="email";
    public static final String KEY_PASS="pass";

    private EditText enter;
    private EditText confirm;
    private Button submit;

    private String Enter;
    private String Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        enter = (EditText) findViewById(R.id.etentered);
        confirm = (EditText) findViewById(R.id.etconfirm);
        submit= (Button) findViewById(R.id.reset);


        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {


        Enter = enter.getText().toString().trim().toLowerCase();
        Pass = confirm.getText().toString().trim().toLowerCase();


        if(Enter!=""||Pass!=""){
            if(Enter.equals(Pass)){

                reset();
            }
            else{
                Toast.makeText(getApplicationContext(),"password didn't match",Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(getApplicationContext(),"Please fill both fields",Toast.LENGTH_LONG).show();
        }



    }


    private void reset() {
        Enter = enter.getText().toString().trim().toLowerCase();
        Pass = confirm.getText().toString().trim().toLowerCase();
        Bundle extras = getIntent().getExtras();
        final String userEmail= extras.getString("KEY");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Forgot_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(Reset.this, response, Toast.LENGTH_LONG).show();
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Reset.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_EMAIL,userEmail);
                map.put(KEY_PASS,Pass);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
