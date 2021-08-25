package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etName, etBirthday, etUid;
    private Button btnCancel, btnConfirm;

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

            }
        });
    }
}