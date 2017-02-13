package com.moffcomm.supatem.data.local.model;

import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.Persistable;

/**
 * Created by jacobsFactory on 2016-08-29.
 */
@Entity
public interface User extends Parcelable, Persistable {

    @Key
    @Generated
    int getId();

    String getMemberNumber();

    String getEmail();

    String getNickName();

    String getPhone();

    String getLevel();

    String getBirthYear();

    String getGender();

    String getMarried();

    String getFavorites();

}