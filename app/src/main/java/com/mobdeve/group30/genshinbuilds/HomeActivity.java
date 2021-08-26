package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    Button btnLogout;
    ImageView ivEditProfile, ivFarmingSched;
    FloatingActionButton fabAddBuild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.btnLogout = findViewById(R.id.btn_home_logout);
        this.ivEditProfile = findViewById(R.id.iv_home_edit_profile);
        this.ivFarmingSched = findViewById(R.id.iv_home_farming_sched);
        this.fabAddBuild = findViewById(R.id.fab_add_build);

        this.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        this.ivEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EditProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        this.ivFarmingSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ViewFarmingSchedActivity.class);
                startActivity(intent);
                finish();
            }
        });

        this.fabAddBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddBuildActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}