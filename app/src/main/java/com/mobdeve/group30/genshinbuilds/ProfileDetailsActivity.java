package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ProfileDetailsActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "KEY_USERNAME";

    ImageButton ibBack;

    String currentUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.currentUsername = getIntent().getStringExtra("KEY_USERNAME");

        this.ibBack = findViewById(R.id.ib_profile_back);

        this.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileDetailsActivity.this, HomeActivity.class);
                intent.putExtra(KEY_USERNAME, currentUsername);
                startActivity(intent);
                finish();
            }
        });
    }
}