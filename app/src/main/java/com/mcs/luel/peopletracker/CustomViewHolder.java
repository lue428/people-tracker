package com.mcs.luel.peopletracker;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CustomViewHolder extends RecyclerView.ViewHolder{

    public TextView fName;
    TextView country;
    TextView zipcode;
    TextView gender;
    ImageView picture;
    TextView dob;
    RelativeLayout parentLayout;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        fName = itemView.findViewById(R.id.tv_fName);
        country = itemView.findViewById(R.id.tv_country);
        zipcode = itemView.findViewById(R.id.tv_zip);
        picture = itemView.findViewById(R.id.iv_picture);
        dob = itemView.findViewById(R.id.tv_dob);
        gender = itemView.findViewById(R.id.tv_gender);
        parentLayout = itemView.findViewById(R.id.parent_layout);
    }
}