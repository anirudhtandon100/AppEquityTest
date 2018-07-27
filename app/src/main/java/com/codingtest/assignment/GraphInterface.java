package com.codingtest.assignment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GraphInterface {

    @GET("assetmanager-ws/api/v3/favourites/assets/trends?")
    Call<GraphDetails> getEquityGraphDetails(@Query("assetId") int id);

}
