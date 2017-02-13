package com.moffcomm.supatem.data.remote.mock;

import com.moffcomm.supatem.data.remote.model.SupaDealsResponse;

import java.util.Arrays;
import java.util.Collections;

import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.BUTTERKNIFE;
import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.DAGGER;
import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.JAVAPOET;
import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.MOSHI;
import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.OKHTTP;
import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.OKIO;
import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.PICASSO;
import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.RETROFIT;
import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.SQLBRITE;
import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.TELESCOPE;
import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.U2020;
import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.WIRE;


public enum MockSupaDealsResponse {
    SUCCESS("Success", new SupaDealsResponse(Arrays.asList( //
            BUTTERKNIFE, //
            DAGGER, //
            JAVAPOET, //
            OKHTTP, //
            OKIO, //
            PICASSO, //
            RETROFIT, //
            SQLBRITE, //
            TELESCOPE, //
            U2020, //
            WIRE, //
            MOSHI))),
    ONE("One", new SupaDealsResponse(Collections.singletonList(DAGGER))),
    EMPTY("Empty", new SupaDealsResponse(null));

    public final String name;
    public final SupaDealsResponse response;

    MockSupaDealsResponse(String name, SupaDealsResponse response) {
        this.name = name;
        this.response = response;
    }

    @Override
    public String toString() {
        return name;
    }
}
