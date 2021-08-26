package com.mobdeve.group30.genshinbuilds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBuildActivity extends AppCompatActivity {

    EditText etCharacter, etLevel, etWeapon, etArtifactSet, etHp, etAtk, etDef, etEr, etCritRate, etCritDmg;

    Button btnConfirm, btnCancel;

    private GenshinDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_build);

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
//                    if(myDB.insertBuild()) {
//                        // Continue code below
//                    }

                }
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