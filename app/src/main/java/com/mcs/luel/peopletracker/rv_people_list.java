package com.mcs.luel.peopletracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class rv_people_list extends AppCompatActivity {
    public ArrayList<Pojo> pojoArr= new ArrayList<>();
    public Pojo pojo;
    public AdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_people_list);
        initPojo();
        initRecycleView();
    }

    private void initRecycleView(){
        adapter = new AdapterClass(this, pojo);
        pojoArr.add(pojo);
        RecyclerView recyclerView = findViewById(R.id.rv_people_list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void initPojo(){

        Pojo pojo = (Pojo) getIntent().getSerializableExtra("pojo instance");
        this.pojo = pojo;
    }
}
