package com.moffcomm.supatem.data.remote.model;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/**
 * Created by jacobsFactory on 2017-02-09.
 */

public class AuthMsgAdapter {
    @ToJson
    String toJson(@AuthMsg long currentTimeInMillis) {
        return String.valueOf(currentTimeInMillis / 1000);
    }

    @FromJson
    @AuthMsg
    String fromJson(String s) {
        return "";
    }
}
