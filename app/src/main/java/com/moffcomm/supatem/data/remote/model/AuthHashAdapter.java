package com.moffcomm.supatem.data.remote.model;

import com.moffcomm.supatem.util.AuthUtil;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/**
 * Created by jacobsFactory on 2017-02-09.
 */

public class AuthHashAdapter {
    @ToJson
    String toJson(@AuthHash long currentTimeInMillis) {
        return AuthUtil.getHash(currentTimeInMillis);
    }

    @FromJson
    @AuthHash
    String fromJson(String s) {
        return "";
    }
}
