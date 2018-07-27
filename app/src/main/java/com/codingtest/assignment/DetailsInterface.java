package com.codingtest.assignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DetailsInterface {
        @Headers({
                "User-ID : 3200",
                "Access-Token: QVFYOGFFV0Z1VmxlSVpaZU5WZ3dEdnBCRDhXSVc3UzlSNnJycmIzRzNvRFJWWm1YQ0RjVUdOR3NuZldNNmFGel94Zk93UTNTdHJrbnlfTDJMVTZGVFpmLXNjRFZsY0I3TEVSQlRLOVdsVFpwby1maU01M01XaDV1dzJQSEFpaC1jelBFVXZBa05ya1p0TmxRdlhFQ1RtU3FkTEliRUJiLWhFaWt0SzZmZmlHM0x0WXpJT01SQndmRUNtTjJULWgyUHJHYUxfNDFlWUprRExseWJxM0d0d2JKdy1QRkU4OEZUY0M5QTBZOG5nMDJsano4eUhoYXRvNGZ3QVp0NENKU1BHWmpKUWpUbmo3RGhfRXExOHV0VzVTX3EzXy1iX2lRQzh5MjY1RzFCbjB4d3Y4dldVVFVqWm9Fal94NDR6MV9mZkZyQ1hlNmFfRmRIdXFGbV8xQzFnQjVBZGdOcVElVVNFUiUzMjAw",
                "User-IP: 106.51.66.2",
                "Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/66.0.3359.181 Chrome/66.0.3359.181 Safari/537.36"
        })
        @GET("assetmanager-ws/api/v3/getassetdetails?type=STK&isCustom=false&date=")
        Call<EquityDetails> getEquityDetails(@Query("asset") int id);
}
