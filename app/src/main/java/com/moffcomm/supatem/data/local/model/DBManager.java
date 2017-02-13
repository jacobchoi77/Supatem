package com.moffcomm.supatem.data.local.model;

import android.content.Context;

import com.moffcomm.supatem.BuildConfig;

import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.rx.RxSupport;
import io.requery.rx.SingleEntityStore;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;

/**
 * Created by jacobsFactory on 2017-02-10.
 */

public class DBManager {

    private static DBManager INSTANCE;
    private SingleEntityStore<Persistable> dataStore;

    private DBManager(Context context) {
        if (dataStore == null) {
            DatabaseSource source = new DatabaseSource(context, Models.DEFAULT, 1);
            if (BuildConfig.DEBUG) {
                source.setTableCreationMode(TableCreationMode.DROP_CREATE);
            }
            Configuration configuration = source.getConfiguration();
            dataStore = RxSupport.toReactiveStore(
                    new EntityDataStore<Persistable>(configuration));
        }
    }

    public static DBManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DBManager(context);
        }
        return INSTANCE;
    }

    public UserEntity getUser() {
        return dataStore.select(UserEntity.class).get().firstOrNull();
    }

}
