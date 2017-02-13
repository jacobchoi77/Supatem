package com.moffcomm.supatem.data.remote;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.moffcomm.supatem.Constant;
import com.moffcomm.supatem.data.callbacks.CallbackSupaDeals;
import com.moffcomm.supatem.data.callbacks.CallbackVersion;
import com.moffcomm.supatem.data.remote.mock.MockSupatemWebService;
import com.moffcomm.supatem.data.remote.model.AuthHashAdapter;
import com.moffcomm.supatem.data.remote.model.AuthMsgAdapter;
import com.moffcomm.supatem.data.remote.model.SupaDealsResponse;
import com.moffcomm.supatem.data.remote.model.VersionResponse;
import com.moffcomm.supatem.util.EtcUtil;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;
import timber.log.Timber;

import static com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES;

/**
 * Created by jacobsFactory on 2017-02-08.
 */

public class WebServiceManager {

    static final int DISK_CACHE_SIZE = (int) MEGABYTES.toBytes(50);

    private Application app;
    private Picasso picasso;
    private Moshi moshi;
    private OkHttpClient okHttpClient;
    private SupatemWebService supatemWebService;

    private static WebServiceManager INSTANCE;

    private WebServiceManager(Application app) {
        this.app = app;
    }

    public static WebServiceManager getInstance(Application app) {
        if (INSTANCE == null) {
            INSTANCE = new WebServiceManager(app);
        }
        return INSTANCE;
    }

    private SupatemWebService getWebService() {
        if (supatemWebService == null) {
            okHttpClient = getOkHttpClient();
            moshi = getMoshi();
            Retrofit retrofit = new Retrofit.Builder() //
                    .client(okHttpClient) //
                    .baseUrl(Constant.BASE_URL) //
                    .addConverterFactory(MoshiConverterFactory.create(moshi)) //
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //
                    .build();
            if (Constant.MOCK) {
                NetworkBehavior behavior = NetworkBehavior.create();
                MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                        .networkBehavior(behavior)
                        .build();
                BehaviorDelegate<SupatemWebService> delegate = mockRetrofit.create(SupatemWebService.class);
                supatemWebService = new MockSupatemWebService(delegate);
            } else {
                supatemWebService = retrofit.create(SupatemWebService.class);
            }
        }
        return supatemWebService;
    }

    public Picasso getPicasso() {
        if (picasso == null) {
            picasso = new Picasso.Builder(app)
                    .downloader(new OkHttp3Downloader(getOkHttpClient()))
                    .build();
        }
        return picasso;
    }

    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            File cacheDir = new File(app.getCacheDir(), "http");
            Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
            okHttpClient = new OkHttpClient.Builder().cache(cache).build();
        }
        return okHttpClient;
    }

    private Moshi getMoshi() {
        if (moshi == null) {
            moshi = new Moshi.Builder()
                    .add(new AuthMsgAdapter())
                    .add(new AuthHashAdapter())
                    .build();
        }
        return moshi;
    }

    public void getSupaDeals(String userId, int pageNum, final CallbackSupaDeals callback) {
        Call<SupaDealsResponse> call = getWebService().getSupaDeals(userId, pageNum, Sort.FORKS, Order.ASC);
        call.enqueue(new Callback<SupaDealsResponse>() {
            @Override
            public void onResponse(Call<SupaDealsResponse> call, Response<SupaDealsResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSupaDealsLoaded(response.body().items);
                } else {
                    callback.onDataNotAvailable(EtcUtil.getErrorMessage(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<SupaDealsResponse> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public void getVersion(final CallbackVersion callback) {
        Call<VersionResponse> call = getWebService().getVersion();
        call.enqueue(new Callback<VersionResponse>() {
            @Override
            public void onResponse(Call<VersionResponse> call, Response<VersionResponse> response) {
                if (response.isSuccessful()) {
                    callback.onVersionLoaded(response.body().version);
                } else {
                    callback.onDataNotAvailable(EtcUtil.getErrorMessage(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<VersionResponse> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

}
