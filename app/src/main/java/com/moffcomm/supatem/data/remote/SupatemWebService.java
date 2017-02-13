package com.moffcomm.supatem.data.remote;

import com.moffcomm.supatem.data.remote.model.SupaDealsResponse;
import com.moffcomm.supatem.data.remote.model.VersionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SupatemWebService {
    @GET("supadeals")
    Call<SupaDealsResponse> getSupaDeals(
            @Query("userId") String userId,
            @Query("pageNum") Integer pageNum,
            @Query("sort") Sort sort,
            @Query("order") Order order);

    @GET("common_rest/env_info")
    Call<VersionResponse> getVersion();

}
