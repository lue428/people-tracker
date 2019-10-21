package com.mcs.luel.peopletracker;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<CustomViewHolder>{

    private Context  mContext;
    private Pojo mPojo;
    private int itemCount;

    AdapterClass(Context context, Pojo pojo){
        mContext = context;
        mPojo = pojo;
        itemCount = itemCount+1;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CustomViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item_rv
                , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.fName.setText(mPojo.getfName());
        holder.country.setText(mPojo.getCountry());
        holder.dob.setText(mPojo.getDob());
        holder.gender.setText(mPojo.getDob());
        holder.zipcode.setText(mPojo.getZipcode());
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

}
