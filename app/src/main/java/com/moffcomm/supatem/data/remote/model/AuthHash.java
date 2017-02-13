package com.moffcomm.supatem.data.remote.model;

import com.squareup.moshi.JsonQualifier;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by jacobsFactory on 2017-02-09.
 */

@Retention(RUNTIME)
@JsonQualifier
public @interface AuthHash {
}
