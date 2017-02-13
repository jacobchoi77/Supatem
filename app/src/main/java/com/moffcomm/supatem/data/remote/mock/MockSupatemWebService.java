package com.moffcomm.supatem.data.remote.mock;

import com.moffcomm.supatem.data.remote.Order;
import com.moffcomm.supatem.data.remote.Sort;
import com.moffcomm.supatem.data.remote.SupatemWebService;
import com.moffcomm.supatem.data.remote.model.SupaDealsResponse;
import com.moffcomm.supatem.data.remote.model.VersionResponse;

import retrofit2.Call;
import retrofit2.http.Query;
import retrofit2.mock.BehaviorDelegate;

/**
 * Created by jacobsFactory on 2017-02-07.
 */

public class MockSupatemWebService implements SupatemWebService {

    private final BehaviorDelegate<SupatemWebService> delegate;

    public MockSupatemWebService(BehaviorDelegate<SupatemWebService> service) {
        this.delegate = service;
    }

    @Override
    public Call<SupaDealsResponse> getSupaDeals(
            @Query("userId") String userId,
            @Query("pageNum") Integer pageNum,
            @Query("sort") Sort sort,
            @Query("order") Order order) {
        SupaDealsResponse supaDealsResponse = new SupaDealsResponse(MockSupaDealsResponse.SUCCESS.response.items);
        return delegate.returningResponse(supaDealsResponse).getSupaDeals(userId, pageNum, sort, order);
    }

    @Override
    public Call<VersionResponse> getVersion() {
        VersionResponse supaDealsResponse = new VersionResponse(MockVersionResponse.SUCCESS.response.version);
        return delegate.returningResponse(supaDealsResponse).getVersion();
    }
}
