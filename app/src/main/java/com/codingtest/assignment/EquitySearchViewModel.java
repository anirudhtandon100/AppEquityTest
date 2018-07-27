package com.codingtest.assignment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;

public class EquitySearchViewModel extends ViewModel {

    MutableLiveData<List<EquityInitialDetails>> equityLiveData;

    public MutableLiveData<List<EquityInitialDetails>> setEquityLiveData() {
        if(equityLiveData==null){
            equityLiveData = new MutableLiveData<List<EquityInitialDetails>>();
        }

        return equityLiveData;
    }

    public void loadEquitySearchdata(String s){
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
        SearchInterface searchInterface = retrofit.create(SearchInterface.class);
        Call<List<EquityInitialDetails>> call;
        call = searchInterface.getEquitySearchDetails(s);
        call.enqueue(new Callback<List<EquityInitialDetails>>() {
            @Override
            public void onResponse(Call<List<EquityInitialDetails>> call, Response<List<EquityInitialDetails>> response) {
                if(response.isSuccessful()) {
                    List<EquityInitialDetails> equities = response.body();
                    equityLiveData.setValue(equities);
                }


            }

            @Override
            public void onFailure(Call<List<EquityInitialDetails>> call, Throwable t) {

            }
        });
    }

}
