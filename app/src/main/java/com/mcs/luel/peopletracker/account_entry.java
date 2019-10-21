package com.mcs.luel.peopletracker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class account_entry extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {

    EditText fullName;
    EditText username;
    EditText zipcode;
    public static Button datePicker;
    RadioButton maleBtn;
    RadioButton femaleBtn;
    TextView needMoreDataTV;

    Button addImg;
    Button saveAccData;
    Spinner chooseCountry;
    ImageView pictureIV;

    ArrayAdapter<String> adapter;

    Drawable errorBackground;
    public Pojo pojo = new Pojo();

    public static final int CAMERA_REQUEST = 456;


    @RequiresApi(api = Build.VERSION_CODES.N)
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
        chooseCountry = findViewById(R.id.choose_country_btn);
        errorBackground = getDrawable(R.drawable.border_shape);

        final Calendar c = Calendar.getInstance();
        int startYear = c.get(Calendar.YEAR);
        int starthMonth = c.get(Calendar.MONTH);
        int startDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                account_entry.this, this, startYear, starthMonth, startDay);
        datePicker.setOnClickListener(v->{

            datePickerDialog.show();

        });


        saveAccData.setOnClickListener(v -> {
            if (fullName.getText().toString().isEmpty() ||
                    username.getText().toString().isEmpty() ||
                    zipcode.getText().toString().isEmpty()) {

                Toast.makeText(account_entry.this, "please fill all required fields", Toast.LENGTH_LONG).show();
            }

            else{

                Intent intent = new Intent(account_entry.this, rv_people_list.class);


                saveData();
                intent.putExtra("pojo instance", pojo);
                startActivity(intent);
            }
        });



        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item);
        getCountries();
        chooseCountry.setAdapter(adapter);



        chooseCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String country  = adapter.getItem(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                chooseCountry.setBackground(errorBackground);
            }
        });

        addImg.setOnClickListener(v->{
            dispatchTakePictureIntent();
        });
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST);
        }
    }

    private void saveData(){
        pojo = new Pojo();
        pojo.setfName(fullName.getText().toString());
        pojo.setUsername(username.getText().toString());
        pojo.setZipcode(zipcode.getText().toString());

        if(maleBtn.hasSelection()) pojo.setGender(1);
        else pojo.setGender(0);
        String country = adapter.getItem(chooseCountry.getSelectedItemPosition());
        pojo.setCountry(country);


    }

    private void getCountries(){
        String[] isoCountryCodes = Locale.getISOCountries();
        adapter.add(" ");
        for (String countryCode : isoCountryCodes) {
            Locale locale = new Locale("", countryCode);
            String countryName = locale.getDisplayCountry();
            adapter.add(countryName);
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

    public boolean saveImageToInternalStorage(Bitmap image) {

        try {
            // Use the compress method on the Bitmap object to write image to
            // the OutputStream
            FileOutputStream fos =  openFileOutput("userImage.png", Context.MODE_PRIVATE);

            // Writing the bitmap to the output stream
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

            return true;
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", e.getMessage());
            return false;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        datePicker.setText( "Date of Birth: " + month +"/" + dayOfMonth +"/" + year);

    }
}
