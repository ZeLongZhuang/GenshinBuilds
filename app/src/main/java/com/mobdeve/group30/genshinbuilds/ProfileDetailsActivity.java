package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ProfileDetailsActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "KEY_USERNAME";
    public static final String KEY_USERNAME_CLICKED = "KEY_USERNAME_CLICKED";

    TextView tvUsername, tvUid, tvName;

    ImageButton ibBack;
    ImageButton ibDelete;

    String currentUsername;
    String usernameClicked;

    RecyclerView recyclerView;
    BuildAdapter buildAdapter;

    ArrayList<Build> dataBuilds;

    GenshinDatabaseHelper myDB = new GenshinDatabaseHelper(ProfileDetailsActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.currentUsername = getIntent().getStringExtra("KEY_USERNAME");
        this.usernameClicked = getIntent().getStringExtra("KEY_USERNAME_CLICKED");

        this.tvUsername = findViewById(R.id.tv_profile_username);
        this.tvName = findViewById(R.id.tv_profile_name);
        this.tvUid = findViewById(R.id.tv_profile_uid);

        this.ibBack = findViewById(R.id.ib_profile_back);
        this.ibDelete = findViewById(R.id.ib_item_delete);

        dataBuilds = new ArrayList<>();

        initValues();

        storeProfileBuildsFromDatabase();

        initRecyclerView();

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

    private void initValues() {
        Cursor cursor = myDB.readUser(usernameClicked);

        if(cursor.getCount() == 0) { //if no data
        }
        else {
            while (cursor.moveToNext()) {
                tvUsername.setText(cursor.getString(1));
                tvName.setText(cursor.getString(3));
                tvUid.setText(cursor.getString(5));
            }
        }
    }

    private void storeProfileBuildsFromDatabase() {
        Cursor cursor = myDB.readProfileBuilds(usernameClicked);

        if(cursor.getCount() == 0) { //if no data

        }
        else {
            while (cursor.moveToNext()) {

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
        this.recyclerView = findViewById(R.id.rv_profile_builds);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Collections.reverse(this.dataBuilds);

        this.buildAdapter = new BuildAdapter(ProfileDetailsActivity.this, ProfileDetailsActivity.this, this.dataBuilds);
        this.recyclerView.setAdapter(this.buildAdapter);
    }

}