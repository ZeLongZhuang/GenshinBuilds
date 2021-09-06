package com.mobdeve.group30.genshinbuilds;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class EditProfileActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "KEY_USERNAME";

    EditText etUsername, etPassword, etName, etBirthday, etUid;

    Button btnConfirm, btnCancel;

    ImageButton ibCamera; //camera

    ImageView ivProfile;

    String currentUsername;

    GenshinDatabaseHelper myDB;

    //FOR CAMERA
    private static int CAMERA_PERMISSION_CODE = 1;
    private static int CAMERA_REQUEST_CODE = 2;

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

        this.ibCamera = findViewById(R.id.ib_edit_profile_camera); //camera

        this.ivProfile = findViewById(R.id.iv_edit_profile_img);

        myDB = new GenshinDatabaseHelper(EditProfileActivity.this);

        initValues();

        this.ibCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }else{
                    ActivityCompat.requestPermissions((Activity) EditProfileActivity.this, new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
                }
            }
        });

        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = etPassword.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String birthday = etBirthday.getText().toString().trim();
                String uid = etUid.getText().toString().trim();

                if(myDB.updateUser(currentUsername, password, name, birthday, uid)) {
                    Toast.makeText(EditProfileActivity.this, "Update Profile successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditProfileActivity.this, "Update Profile Failed", Toast.LENGTH_SHORT).show();
                }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }else{
                Toast.makeText(this, "Permission was denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
            Bitmap picture = (Bitmap) data.getExtras().get("data");
            ivProfile.setImageBitmap(picture);
        }

        super.onActivityResult(requestCode, resultCode, data);
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