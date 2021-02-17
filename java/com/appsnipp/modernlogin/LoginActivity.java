package com.appsnipp.modernlogin;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText emailet, passet;
    boolean isEmailValid, isPasswordValid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);

        emailet=(EditText)findViewById(R.id.editTextEmail);
        passet=(EditText)findViewById(R.id.editTextPassword);
        login=(Button)findViewById(R.id.cirLoginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SetValidate();
            }
        });
    }


    public void onLoginClick(View View){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }

    public void SetValidate() {
        // Check for a valid email address.
        if (emailet.getText().toString().isEmpty()) {
            emailet.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailet.getText().toString()).matches()) {
            emailet.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        // Check for a valid password.
        if (passet.getText().toString().isEmpty()) {
            passet.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (passet.getText().length() < 6) {
            passet.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }

        if (isEmailValid && isPasswordValid) {
            Toast.makeText(getApplicationContext(), "Successfully login", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(LoginActivity.this,DashBoardActivity.class);
            startActivity(i);
        }
    }

    
}
