package com.codingtest.assignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchInterface {

    @GET("assetmanager-ws/api/v1/assets/getassets?asseType=STK&filter=&isCustom=false")
    Call<List<EquityInitialDetails>>getEquitySearchDetails(@Query("query") String id);
}
