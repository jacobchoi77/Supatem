package com.moffcomm.supatem.data.remote.mock;

import com.moffcomm.supatem.data.remote.model.SupaDealsResponse;
import com.moffcomm.supatem.data.remote.model.Version;
import com.moffcomm.supatem.data.remote.model.VersionResponse;

import java.util.Collections;

import static com.moffcomm.supatem.data.remote.mock.MockSupaDeals.DAGGER;


public enum MockVersionResponse {
    SUCCESS("Success", new VersionResponse(new Version("1.0"))),
    EMPTY("Empty", new VersionResponse(null));

    public final String name;
    public final VersionResponse response;

    MockVersionResponse(String name, VersionResponse response) {
        this.name = name;
        this.response = response;
    }

    @Override
    public String toString() {
        return name;
    }
}
