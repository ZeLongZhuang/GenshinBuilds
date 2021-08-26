package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddBuildActivity extends AppCompatActivity {

    Button btnConfirm, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_build);

        this.btnConfirm = findViewById(R.id.btn_add_build_confirm);
        this.btnCancel = findViewById(R.id.btn_add_build_cancel);

        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add code here
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBuildActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}