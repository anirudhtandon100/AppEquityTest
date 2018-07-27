package com.codingtest.assignment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements EquitySearchAdapter.ItemClickListener {

    EditText editText;
    EquitySearchViewModel model;
    List<EquityInitialDetails> list = new ArrayList<>();
    EquitySearchAdapter adapter;
    RecyclerView rview;



    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.equitysearch);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        rview = findViewById(R.id.rViewEquities);
        adapter = new EquitySearchAdapter(this, list );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(linearLayoutManager);
        rview.setAdapter(adapter);
        adapter.setClickListener(this);


        editText = findViewById(R.id.editText);
        model = ViewModelProviders.of(this).get(EquitySearchViewModel.class);
        model.setEquityLiveData().observe(this, new Observer<List<EquityInitialDetails>>() {
            @Override
            public void onChanged(@Nullable List<EquityInitialDetails> equityInitialDetails) {
                list = equityInitialDetails;
                adapter.update(equityInitialDetails);

            }
        });




        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {

                model.loadEquitySearchdata(s.toString());

            }
        });

    }

    public void onItemClick(View view, int position){
        Intent data = new Intent();
        data.putExtra("EquityDetails", list.get(position).id);
        setResult(RESULT_OK, data);
        finish();


    }


}
