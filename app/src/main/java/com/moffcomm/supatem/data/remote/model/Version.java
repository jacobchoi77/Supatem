package com.moffcomm.supatem.data.remote.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static com.moffcomm.supatem.util.Preconditions.checkNotNull;

public class Version {
    @Json(name = FIELD.APP_VERSION)
    String appVersion;

    public Version(String version) {
        appVersion = version;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
