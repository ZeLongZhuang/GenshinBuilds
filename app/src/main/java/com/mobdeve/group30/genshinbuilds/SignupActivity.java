package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etName, etBirthday, etUid;
    private Button btnCancel, btnConfirm;

    private GenshinDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.etUsername = findViewById(R.id.et_signup_username);
        this.etPassword = findViewById(R.id.et_signup_password);
        this.etName = findViewById(R.id.et_signup_name);
        this.etBirthday = findViewById(R.id.et_signup_date);
        this.etUid = findViewById(R.id.et_signup_uid);

        this.btnCancel = findViewById(R.id.btn_signup_cancel);
        this.btnConfirm = findViewById(R.id.btn_signup_confirm);

        myDB = new GenshinDatabaseHelper(this);

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String birthday = etBirthday.getText().toString().trim();
                String uid = etUid.getText().toString().trim();

                if(username.equals("") || password.equals("") || name.equals("") || birthday.equals("") || uid.equals("")){
                    Toast.makeText(SignupActivity.this, "Missing Fields detected. Please enter all fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(myDB.isUniqueUsername(username)) {
                        Boolean insertUser = myDB.insertUser(username, password, name, birthday, uid);

                        if(insertUser) {
                            Toast.makeText(SignupActivity.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignupActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}