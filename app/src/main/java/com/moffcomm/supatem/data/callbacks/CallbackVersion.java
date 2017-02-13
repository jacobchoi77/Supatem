package com.moffcomm.supatem.data.callbacks;

import com.moffcomm.supatem.data.remote.model.SupaDeal;
import com.moffcomm.supatem.data.remote.model.Version;

import java.util.List;

/**
 * Created by jacobsFactory on 2017-02-09.
 */

public interface CallbackVersion {
    void onVersionLoaded(Version version);

    void onDataNotAvailable(String errorMsg);

    void onFailure();
}
