package com.mcs.luel.peopletracker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class account_entry extends AppCompatActivity  {

    EditText fullName;
    EditText username;
    EditText zipcode;
    Button datePicker;
    RadioButton maleBtn;
    RadioButton femaleBtn;
    TextView needMoreDataTV;
    Button addImg;

    Button saveAccData;
    ImageView pictureIV;

    public static final int CAMERA_REQUEST = 456;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_entry);
        fullName = findViewById(R.id.full_name_et);
        datePicker = findViewById(R.id.pick_dob_btn);
        username = findViewById(R.id.username_et);
        zipcode = findViewById(R.id.zipcode_et);
        saveAccData = findViewById(R.id.save_acc_data_btn);
        maleBtn = findViewById(R.id.male_rbtn);
        femaleBtn = findViewById(R.id.female_rbtn);
        needMoreDataTV = findViewById(R.id.more_data_tv);
        addImg = findViewById(R.id.add_picture_btn);
        pictureIV = findViewById(R.id.picture_iv);




        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        saveAccData.setOnClickListener(v -> {
            while (fullName.getText().toString().isEmpty() ||
                    username.getText().toString().isEmpty() ||
                    zipcode.getText().toString().isEmpty()) {

                Toast.makeText(account_entry.this, "please fill all required fields", Toast.LENGTH_LONG).show();
            }
        });

        addImg.setOnClickListener(v->{
            dispatchTakePictureIntent();
        });


        datePicker.setOnClickListener(v -> {
          //  datePicker();
        });
    }

//    private void datePicker() {
//
//        // Get Current Date
//        final Calendar c = Calendar.getInstance();
//        int mYear = c.get(Calendar.YEAR);
//       int  mMonth = c.get(Calendar.MONTH);
//        int mDay = c.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                new DatePickerDialog.OnDateSetListener() {
//
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//                        date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
//                        //*************Call Time Picker Here ********************
//                        tiemPicker();
//                    }
//                }, mYear, mMonth, mDay);
//        datePickerDialog.show();
//    }




    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST &&
                resultCode == RESULT_OK &&
                data != null) {
            Bundle cameraData = data.getExtras();
            Bitmap bitmap = (Bitmap) cameraData.get("data");
            pictureIV.setImageBitmap(bitmap);

        }
    }
}





