package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "KEY_USERNAME";

    EditText etUsername, etPassword, etName, etBirthday, etUid;

    Button btnConfirm, btnCancel;

    String currentUsername;

    GenshinDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.currentUsername = getIntent().getStringExtra("KEY_USERNAME");

        this.etUsername = findViewById(R.id.et_edit_profile_username);
        this.etPassword = findViewById(R.id.et_edit_profile_password);
        this.etName = findViewById(R.id.et_edit_profile_name);
        this.etBirthday = findViewById(R.id.et_edit_profile_bday);
        this.etUid = findViewById(R.id.et_edit_profile_uid);

        this.btnConfirm = findViewById(R.id.btn_edit_profile_confirm);
        this.btnCancel = findViewById(R.id.btn_edit_profile_cancel);

        myDB = new GenshinDatabaseHelper(EditProfileActivity.this);

        initValues();

        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add code here
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
                intent.putExtra(KEY_USERNAME, currentUsername);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initValues() {
        Cursor cursor = myDB.readUser(currentUsername);

        if(cursor.getCount() == 0) { //if no data
        }
        else {
            while (cursor.moveToNext()) {
                etUsername.setText(cursor.getString(1));
                etPassword.setText(cursor.getString(2));
                etName.setText(cursor.getString(3));
                etBirthday.setText(cursor.getString(4));
                etUid.setText(cursor.getString(5));
            }
        }
    }
}