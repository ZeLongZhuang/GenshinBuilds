package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class HomeActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "KEY_USERNAME";

    Button btnLogout;
    ImageView ivEditProfile, ivFarmingSched, ivUserPic;
    FloatingActionButton fabAddBuild;

    TextView tvUsername;

    ArrayList<Build> dataBuilds;

    String currentUsername;

    RecyclerView recyclerView;
    BuildAdapter buildAdapter;

    GenshinDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.tvUsername = findViewById(R.id.tv_home_user_name);
        this.currentUsername = getIntent().getStringExtra("KEY_USERNAME");
        this.tvUsername.setText(currentUsername);

        this.ivUserPic = findViewById(R.id.iv_home_user_pic);
        this.btnLogout = findViewById(R.id.btn_home_logout);
        this.ivEditProfile = findViewById(R.id.iv_home_edit_profile);
        this.ivFarmingSched = findViewById(R.id.iv_home_farming_sched);
        this.fabAddBuild = findViewById(R.id.fab_add_build);

        this.myDB = new GenshinDatabaseHelper(HomeActivity.this);

        dataBuilds = new ArrayList<>();

        storeBuildsFromDatabase();

        initRecyclerView();

        this.ivUserPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileDetailsActivity.class);
                intent.putExtra(KEY_USERNAME, currentUsername);
                startActivity(intent);
                finish();
            }
        });

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
                intent.putExtra(KEY_USERNAME, currentUsername);
                startActivity(intent);
                finish();
            }
        });

        this.ivFarmingSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ViewFarmingSchedActivity.class);
                intent.putExtra(KEY_USERNAME, currentUsername);
                startActivity(intent);
                finish();
            }
        });

        this.fabAddBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddBuildActivity.class);
                intent.putExtra(KEY_USERNAME, currentUsername);
                startActivity(intent);
                finish();
            }
        });

    }

    private void storeBuildsFromDatabase() {
        Cursor cursor = myDB.readAllBuilds();

        if(cursor.getCount() == 0) { //if no data

        }
        else {
            while (cursor.moveToNext()) {

//                for(int i = 0; i < cursor.getColumnCount(); i++)
//                    Log.d("CursorTag", "Column " + i + " " + cursor.getString(i));

                Build newBuild = new Build( cursor.getString(1),    // username
                                            cursor.getString(3),    // character
                                            cursor.getInt(2),       // level
                                            cursor.getString(4),    // weapon
                                            cursor.getString(5),    // artifactSet
                                            cursor.getInt(6),       // hp
                                            cursor.getInt(7),       // atk
                                            cursor.getInt(8),       // def
                                            cursor.getInt(9),       // er
                                            cursor.getInt(10),       // critRate
                                            cursor.getInt(11)       // critDmg
                );

                dataBuilds.add(newBuild);
            }
        }
    }

    private void initRecyclerView() {
        this.recyclerView = findViewById(R.id.rv_home_builds);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Collections.reverse(this.dataBuilds);

        this.buildAdapter = new BuildAdapter(HomeActivity.this, HomeActivity.this, this.dataBuilds);
        this.recyclerView.setAdapter(this.buildAdapter);
    }
}