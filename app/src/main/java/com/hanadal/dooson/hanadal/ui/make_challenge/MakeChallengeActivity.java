package com.hanadal.dooson.hanadal.ui.make_challenge;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.util.UtilClass;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.internal.Util;

import static android.Manifest.permission_group.CAMERA;
import static android.Manifest.permission_group.STORAGE;

public class MakeChallengeActivity extends AppCompatActivity
        implements View.OnClickListener, RadioButton.OnCheckedChangeListener {

    TextInputEditText editChallengeName;
    TextInputEditText editChallengeInfo;
    EditText editTag;
    RadioButton radioPublic;
    RadioButton radioNotPublic;
    RadioButton radioPerfect;
    RadioButton radioNotPerfect;
    Button btnStartChallenge;
    ImageView btnBack;
    ImageView imageSet;

    StringBuilder imgeUrl = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_challenge);

        editChallengeName = findViewById(R.id.edit_challenge_name);
        editChallengeInfo = findViewById(R.id.edit_challenge_info);
        editTag = findViewById(R.id.tag_edit);
        radioPublic = findViewById(R.id.radio_public);
        radioNotPublic = findViewById(R.id.radio_not_public);
        radioPerfect = findViewById(R.id.radio_perfect);
        radioNotPerfect = findViewById(R.id.radio_not_perfect);
        btnStartChallenge = findViewById(R.id.btn_start_challenge);
        btnBack = findViewById(R.id.btn_back);
        imageSet = findViewById(R.id.image);

        btnStartChallenge.setOnClickListener(this);
        radioPublic.setOnCheckedChangeListener(this);
        radioNotPublic.setOnCheckedChangeListener(this);
        radioPerfect.setOnCheckedChangeListener(this);
        radioNotPerfect.setOnCheckedChangeListener(this);
        btnBack.setOnClickListener(this);
        imageSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_challenge: {
                if(editChallengeName.length() > 0 &&
                editChallengeInfo.length() > 0 &&
                editTag.length() > 0) {
                    File file = new File(imgeUrl.toString());
                    RequestBody fileReqBody = imgeUrl.length() > 0 ? RequestBody.create(MediaType.parse("image/*"), file) : null;

                    Connector.api.makeMyChallenge(
                            UtilClass.getToken(getApplicationContext()),
                            editChallengeName.getText().toString(),
                            editChallengeInfo.getText().toString(),
                            radioPublic.isChecked(),
                            radioPerfect.isChecked(),
                            editTag.getText().toString(),
                            fileReqBody).enqueue(new Res<Gson>(getApplicationContext()) {
                        @Override
                        public void callback(int code, Gson body) {
                            if (code == 201) {
                                UtilClass.Toast(getApplicationContext(), "도전 추가 되었습니다.");
                                finish();
                            }
                        }
                    });
                }else {
                    UtilClass.Toast(getApplicationContext(), "올바르게 작성해 주세요.");
                }
                UtilClass.loadProgress(this);

                break;
            }
            case R.id.btn_back: {
                finish();
                break;
            }
            case R.id.image: {
                try {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(data != null) {
                Glide.with(getApplicationContext()).load(data.getData()).into(imageSet);
                imgeUrl.delete(0, imgeUrl.length());
                imgeUrl.append(getRealPathFromURI(data.getData()));
                Log.e("TEST", data.toUri(0));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 1);
                } else {
                    UtilClass.Toast(getApplicationContext(), "권한 설정이 정상적으로 되지 않았습니다.");
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.radio_public: {
                if (isChecked) radioNotPublic.setChecked(false);
                else radioNotPublic.setChecked(true);
                break;
            }
            case R.id.radio_not_public: {
                if (isChecked) radioPublic.setChecked(false);
                else radioPublic.setChecked(true);
                break;
            }
            case R.id.radio_perfect: {
                if (isChecked) radioNotPerfect.setChecked(false);
                else radioNotPerfect.setChecked(true);
                break;
            }
            case R.id.radio_not_perfect: {
                if (isChecked) radioPerfect.setChecked(false);
                else radioPerfect.setChecked(true);
                break;
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String filePath;
        Cursor cursor = this.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            filePath = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            filePath = cursor.getString(idx);
            cursor.close();
        }
        return filePath;
    }
}
