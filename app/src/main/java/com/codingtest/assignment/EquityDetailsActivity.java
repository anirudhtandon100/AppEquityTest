package com.codingtest.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EquityDetailsActivity extends AppCompatActivity{

    EquityDetails eqtyDet;
     private Toolbar toolbar;
     int id;
    GraphDetails graph;

    LineChart chart;
    List<Entry> entries = new ArrayList<Entry>();



    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.equitydetails);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Detail");
        chart = findViewById(R.id.chart);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
        Intent intent = getIntent();
        eqtyDet = (EquityDetails) intent.getSerializableExtra("list");
        id = intent.getIntExtra("id",0);



        TextView name = findViewById(R.id.name);
        TextView symbol = findViewById(R.id.symbol);
        TextView exchange = findViewById(R.id.exchange);
        TextView country = findViewById(R.id.country);
        TextView cusip = findViewById(R.id.cusip);
        TextView issuer = findViewById(R.id.issuer);
        name.setText(eqtyDet.contractName);
        symbol.setText(eqtyDet.symbol);
        exchange.setText(eqtyDet.exchange);
        country.setText(eqtyDet.country);
        cusip.setText(eqtyDet.cusip);
        issuer.setText(eqtyDet.issuer);


//        chart = (LineChart) findViewById(R.id.chart);
//        chart.setOnChartGestureListener(this);
//        chart.setOnChartValueSelectedListener(this);
//        chart.setDragEnabled(true);
//        chart.setScaleEnabled(false);









        OkHttpClient okHttpClient= new OkHttpClient.Builder().addNetworkInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("User-ID","3200")
                        .addHeader("Access-Token","QVFYOGFFV0Z1VmxlSVpaZU5WZ3dEdnBCRDhXSVc3UzlSNnJycmIzRzNvRFJWWm1YQ0RjVUdOR3NuZldNNmFGel94Zk93UTNTdHJrbnlfTDJMVTZGVFpmLXNjRFZsY0I3TEVSQlRLOVdsVFpwby1maU01M01XaDV1dzJQSEFpaC1jelBFVXZBa05ya1p0TmxRdlhFQ1RtU3FkTEliRUJiLWhFaWt0SzZmZmlHM0x0WXpJT01SQndmRUNtTjJULWgyUHJHYUxfNDFlWUprRExseWJxM0d0d2JKdy1QRkU4OEZUY0M5QTBZOG5nMDJsano4eUhoYXRvNGZ3QVp0NENKU1BHWmpKUWpUbmo3RGhfRXExOHV0VzVTX3EzXy1iX2lRQzh5MjY1RzFCbjB4d3Y4dldVVFVqWm9Fal94NDR6MV9mZkZyQ1hlNmFfRmRIdXFGbV8xQzFnQjVBZGdOcVElVVNFUiUzMjAw")
                        .addHeader("User-IP","106.51.66.2")
                        .addHeader("Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/66.0.3359.181 Chrome/66.0.3359.181 Safari/537.36").build();
                return chain.proceed(request);
            }
        }).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://services.investo2o.com/").addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        GraphInterface graphInterface = retrofit.create(GraphInterface.class);
        Call<GraphDetails> call;
        call = graphInterface.getEquityGraphDetails(id);
        call.enqueue(new Callback<GraphDetails>() {
            @Override
            public void onResponse(Call<GraphDetails> call, Response<GraphDetails> response) {
                if(response.isSuccessful()) {
                    graph = response.body();
                    showGraph();
                }


            }

            @Override
            public void onFailure(Call<GraphDetails> call, Throwable t) {

            }
        });

    }



    public void showGraph(){
        for(int i =0; i<graph.priceData.size(); i++) {

            entries.add(new Entry(graph.priceData.get(i).get(0).floatValue(), graph.priceData.get(i).get(1).floatValue()));
        }


            LineDataSet dataSet = new LineDataSet(entries, "EquityGraph");
            dataSet.setColor(Color.RED);
            dataSet.setValueTextColor(Color.GREEN);
            dataSet.setDrawCircles(false);

            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);

        chart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Date date = new Date((long)value);

                SimpleDateFormat format = new SimpleDateFormat("dd MMM yy", Locale.US);
                String text =format.format(date);
                return text; // xVal is a string array
            }
        });

            chart.invalidate();// refresh

    }



}
