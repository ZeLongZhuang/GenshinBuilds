package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ViewFarmingSchedActivity extends AppCompatActivity {

    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farming_sched);

        this.ibBack = findViewById(R.id.ib_farming_sched_back);

        this.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewFarmingSchedActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}