package com.moffcomm.supatem.data.remote.model;

import com.squareup.moshi.Json;

/**
 * Created by jacobsFactory on 2017-02-09.
 */

public class Device extends Auth {
    @Json(name = FIELD.MEMBER_NUMBER)
    String memberNumber;
    @Json(name = FIELD.DEVICE_CODE)
    String deviceCode;
    @Json(name = FIELD.DEVICE_TOKEN)
    String token;
    @Json(name = FIELD.DEVICE_INFO)
    String deviceInfo;
}
