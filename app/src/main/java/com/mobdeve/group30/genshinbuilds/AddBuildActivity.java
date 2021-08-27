package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBuildActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "KEY_USERNAME";

    EditText etCharacter, etLevel, etWeapon, etArtifactSet, etHp, etAtk, etDef, etEr, etCritRate, etCritDmg;

    Button btnConfirm, btnCancel;

    String currentUsername;

    private GenshinDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_build);

        this.currentUsername = getIntent().getStringExtra("KEY_USERNAME");

        this.etCharacter = findViewById(R.id.et_add_build_chara);
        this.etLevel = findViewById(R.id.et_add_build_level);
        this.etWeapon = findViewById(R.id.et_add_build_weapon);
        this.etArtifactSet = findViewById(R.id.et_add_build_artifact_set);
        this.etHp = findViewById(R.id.et_add_build_hp);
        this.etAtk = findViewById(R.id.et_add_build_atk);
        this.etDef = findViewById(R.id.et_add_build_def);
        this.etEr = findViewById(R.id.et_add_build_er);
        this.etCritRate = findViewById(R.id.et_add_build_crit_rate);
        this.etCritDmg = findViewById(R.id.et_add_build_crit_dmg);

        this.btnConfirm = findViewById(R.id.btn_add_build_confirm);
        this.btnCancel = findViewById(R.id.btn_add_build_cancel);

        myDB = new GenshinDatabaseHelper(this);


        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String character = etCharacter.getText().toString().trim();
                String level = etLevel.getText().toString().trim();
                String weapon = etWeapon.getText().toString().trim();
                String artifactSet = etArtifactSet.getText().toString().trim();
                String hp = etHp.getText().toString().trim();
                String atk = etAtk.getText().toString().trim();
                String def = etDef.getText().toString().trim();
                String er = etEr.getText().toString().trim();
                String critRate = etCritRate.getText().toString().trim();
                String critDmg = etCritDmg.getText().toString().trim();

                if(     character.equals("") || level.equals("") || weapon.equals("") || artifactSet.equals("") ||
                        hp.equals("") || atk.equals("") || def.equals("") || er.equals("") || critRate.equals("") || critDmg.equals("")){

                    Toast.makeText(AddBuildActivity.this, "Missing Fields detected. Please enter all fields.", Toast.LENGTH_SHORT).show();
                }
                else {

                    int iLevel = Integer.parseInt(level);
                    int iHp = Integer.parseInt(hp);
                    int iAtk = Integer.parseInt(atk);
                    int iDef = Integer.parseInt(def);
                    int iEr = Integer.parseInt(er);
                    int iCritRate = Integer.parseInt(critRate);
                    int iCritDmg = Integer.parseInt(critDmg);

                    if(myDB.insertBuild(currentUsername, character, iLevel, weapon, artifactSet, iHp, iAtk, iDef, iEr, iCritRate, iCritDmg)) {
                        Toast.makeText(AddBuildActivity.this, "Build added successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddBuildActivity.this, HomeActivity.class);
                        intent.putExtra(KEY_USERNAME, currentUsername);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(AddBuildActivity.this, "Add Build failed", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBuildActivity.this, HomeActivity.class);
                intent.putExtra(KEY_USERNAME, currentUsername);
                startActivity(intent);
                finish();
            }
        });
    }
}