package com.moffcomm.supatem.data.remote.model;

import java.util.List;

public final class SupaDealsResponse {
  public final List<SupaDeal> items;

  public SupaDealsResponse(List<SupaDeal> items) {
    this.items = items;
  }
}
