package com.mcs.luel.peopletracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class login_activiy extends AppCompatActivity {
    Button log_reg;
    EditText emailEt;
    EditText password;
    EditText rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activiy);

        log_reg = findViewById(R.id.login_register_btn);
        emailEt = findViewById(R.id.email_et);
        password = findViewById(R.id.password_et);
        rePassword = findViewById(R.id.re_password_et);

        emailEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                properEmail();
            }
        });

        log_reg.setOnClickListener(v ->{

            if(!checkEmpty()){
                Toast.makeText(getApplicationContext(), "make sure that all fields are filled out", Toast.LENGTH_LONG).show();
            }
            else if(!properEmail()){
                Toast.makeText(getApplicationContext(), "please enter a proper email address", Toast.LENGTH_LONG).show();
            }
            else if (!sameTextview(password, rePassword)){
                Toast.makeText(getApplicationContext(), "please make sure passwords are the same", Toast.LENGTH_LONG).show();
            }

            else if(!validatePass(password)){

            }
            else{
                startActivity(new Intent(login_activiy.this, account_entry.class));
            }

        });

        emailEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus && !properEmail()) {

                        emailEt.setBackgroundColor(new Color().RED);
                } else if (!hasFocus && properEmail()) {
                    emailEt.setBackgroundColor(new Color().TRANSPARENT);
                }
            }
        });
    }


    private boolean properEmail() {
        {
            String email = emailEt.getText().toString();
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);
            if (email == null)
                return false;
            return pat.matcher(email).matches();
        }
    }

    public boolean sameTextview(TextView tv1, TextView tv2){

        if(tv1.getText().toString().equals(tv2.getText().toString())) return true;
        else return false;
    }


    public boolean checkEmpty() {
        if(emailEt.getText().toString().isEmpty()
                || password.getText().toString().isEmpty()
                || rePassword.getText().toString().isEmpty())
            return false;
        else return true;
    }

    public boolean validatePass(TextView password) {
        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern upperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        String pass = password.getText().toString();

        if(pass.length() < 8){
            Toast.makeText(login_activiy.this, "password must be at least 8 digits long", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!upperCasePatten.matcher(pass).find()){
            Toast.makeText(login_activiy.this, "password must contain at least one upper character", Toast.LENGTH_LONG).show();
            return false;
        }

        else if(!lowerCasePatten.matcher(pass).find()){
            Toast.makeText(login_activiy.this, "password must contain at least one lowercase character", Toast.LENGTH_LONG).show();
            return false;
        }

        else if(!specailCharPatten.matcher(pass).find()){
            Toast.makeText(login_activiy.this, "password must contain at least one special character", Toast.LENGTH_LONG).show();
            return false;
        }

        else if(!digitCasePatten.matcher(pass).find()){
            Toast.makeText(login_activiy.this, "password must contain at least one number", Toast.LENGTH_LONG).show();
            return false;
        }

        else return true;
    }
}
