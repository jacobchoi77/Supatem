package com.moffcomm.supatem.data.callbacks;

import com.moffcomm.supatem.data.remote.model.SupaDeal;

import java.util.List;

/**
 * Created by jacobsFactory on 2017-02-09.
 */

public interface CallbackSupaDeals {
    void onSupaDealsLoaded(List<SupaDeal> supaDeals);

    void onDataNotAvailable(String errorMsg);

    void onFailure();
}
