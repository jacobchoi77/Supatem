package com.moffcomm.supatem.data.remote.model;

import com.squareup.moshi.Json;

/**
 * Created by jacobsFactory on 2016-09-07.
 */
public class Auth {

    @Json(name = FIELD.AUTH_MESSAGE)
    @AuthMsg
    long authMessage;

    @Json(name = FIELD.AUTH_HASH)
    @AuthHash
    long authHash;

}
