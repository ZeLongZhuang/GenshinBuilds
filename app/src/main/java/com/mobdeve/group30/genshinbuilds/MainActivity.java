package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnSignup;

    private GenshinDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.etUsername = findViewById(R.id.et_login_username);
        this.etPassword = findViewById(R.id.et_login_password);

        this.btnLogin = findViewById(R.id.btn_login_confirm);
        this.btnSignup = findViewById(R.id.btn_login_signup);

        this.myDB = new GenshinDatabaseHelper(this);

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(username.equals("") || password.equals("")) {
                    Toast.makeText(MainActivity.this, "Missing Fields detected. Please enter all fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(myDB.verifyUsernamePassword(username, password)) {   // if username and password is a match
                        Toast.makeText(MainActivity.this, "Login successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Username and password does not match. Please try again", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        this.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}