package com.example.priya.cardsmaterialdesign.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.priya.cardsmaterialdesign.R;
import com.example.priya.cardsmaterialdesign.model.ReminderDetailsModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;

public class ReminderTextActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    @InjectViews({R.id.add_title, R.id.add_details})
    List<EditText> addDetails;
    ReminderDetailsModel reminderDetails;
    @InjectView(R.id.image_switch)
    Switch imageSwitch;
    @InjectView(R.id.load_image)
    ImageView loadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        ButterKnife.inject(this);
        imageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    loadImage.setVisibility(View.VISIBLE);
                    loadImageFromMemory();
                } else{
                    loadImage.setVisibility(View.GONE);
                }
            }
        });
    }

    private void loadImageFromMemory() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                String selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                loadImage.setImageURI(selectedImageUri);
            }
        }
    }

    public void openAddNext(View view){
        Intent intent = new Intent(this, ReminderTimeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Title", addDetails.get(0).getText().toString());
        bundle.putString("Details", addDetails.get(1).getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
