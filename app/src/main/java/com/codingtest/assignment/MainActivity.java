package com.codingtest.assignment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements EquityAdapter.ItemClickListener{

    private static List<EquityDetails> equityDetail = new ArrayList<>();
    private EquityDetailsViewModel detailsViewModel;
    private RecyclerView rEquityList;
    EquityAdapter adapter;
    EditText editText;
    int id;
    Button nameSort;
    Button symbolSort;
    Button currencySort;
    Button pricingDateSort;
    private boolean isFirstTime = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Equity App");

        editText = findViewById(R.id.editText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , SearchActivity.class);
                startActivityForResult(intent,200);
            }
        });

        nameSort = findViewById(R.id.nameSort);
        symbolSort = findViewById(R.id.symbolSort);
        currencySort = findViewById(R.id.currencySort);
        pricingDateSort = findViewById(R.id.pricingDateSort);

        nameSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(equityDetail, EquityDetails.equityDetailsNameComparator);
                adapter.notifyDataSetChanged();
            }
        });
        symbolSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(equityDetail, EquityDetails.equityDetailsSymbolComparator);
                adapter.notifyDataSetChanged();
            }
        });
        currencySort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(equityDetail, EquityDetails.equityDetailsSymbolComparator);
                adapter.notifyDataSetChanged();
            }
        });
        pricingDateSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(equityDetail, EquityDetails.equityDetailsPricingDateComparator);
                adapter.notifyDataSetChanged();
            }
        });







        rEquityList = findViewById(R.id.rViewEquities);
        adapter = new EquityAdapter(getApplicationContext(), equityDetail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rEquityList.setLayoutManager(linearLayoutManager);
        rEquityList.setAdapter(adapter);
        adapter.setClickListener(this);

        detailsViewModel = ViewModelProviders.of(this).get(EquityDetailsViewModel.class);
        detailsViewModel.setEquityLiveData().observe(this, new Observer<EquityDetails>() {
            @Override
            public void onChanged(@Nullable EquityDetails equityDetails) {
                    EquityDetails equityDetailser = equityDetails;
                    equityDetail.add(equityDetailser);
                    adapter.notifyDataSetChanged();



            }
        });



    }

    public void onItemClick(View view, int position){

        Intent intent = new Intent(this, EquityDetailsActivity.class );
        intent.putExtra("list", equityDetail.get(position));
        intent.putExtra("id", id);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 200) {
            if (data.hasExtra("EquityDetails")) {
              Integer id = data.getIntExtra("EquityDetails",0);
              detailsViewModel.loadEquitySearchdata(id);
              this.id=id;
            }
        }
    }



}















