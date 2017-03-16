package com.example.girivi.vlogin;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.String;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.girivi.vlogin.Appconfig.REGISTER_URL;
import static com.example.girivi.vlogin.R.id.eddob;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextName;
    private EditText editTextDob;
    private EditText editTextfname;
    private EditText editTextgen;
    private EditText editTextPassword;
    private EditText editTextEmail;
    DatePickerDialog datePickerDialog;

    private Button Reg;

    //private static final String REGISTER_URL = "http://192.168.43.40:1080/source/include/dbregister.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextDob = (EditText) findViewById(R.id.eddob);
        editTextDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(Register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                editTextDob.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        editTextName = (EditText) findViewById(R.id.edname);
        editTextPassword = (EditText) findViewById(R.id.edpassword);
        editTextEmail = (EditText) findViewById(R.id.edemail);
        editTextgen = (EditText) findViewById(R.id.edgen);
        editTextfname = (EditText) findViewById(R.id.edfname);

        Reg = (Button) findViewById(R.id.btregister);

        Reg.setOnClickListener(this);
    }
    public void log(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        if(v == Reg){
            registerUser();
        }
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim();
        String dob = editTextDob.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String fname = editTextfname.getText().toString().trim().toLowerCase();
        String gender = editTextgen.getText().toString().trim();

        register(name,email,password,fname,dob,gender);
    }
    private void register(String name, String email, String password, String fname,String dob,String gender) {
        String urlSuffix = "?name="+name+"&email="+email+"&password="+password+"&fname="+fname+"&dob="+dob+"&gender="+gender;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this, "Please Wait",null, true, true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(Appconfig.REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }
}



