package com.moffcomm.supatem.data.remote;

public enum Sort {
    STARS("watchers"),
    FORKS("forks");

    private final String value;

    Sort(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
