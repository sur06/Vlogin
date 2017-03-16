package com.example.girivi.vlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class expence extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expence);
        Spinner dropdown=(Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{"Food","Entertainment","Fuel","Lodging","Phone","Transportaion","Others"};
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items);
        dropdown.setAdapter(adapter);
    }
}
