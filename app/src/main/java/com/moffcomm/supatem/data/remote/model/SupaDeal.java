package com.moffcomm.supatem.data.remote.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.moffcomm.supatem.util.Preconditions.checkNotNull;

public final class SupaDeal {
    @NonNull
    public final String name;
    @Nullable
    public final String description;

    public final long watchers;
    public final long forks;

    public final String html_url;

    private SupaDeal(Builder builder) {
        this.name = checkNotNull(builder.name, "name == null");
        this.description = builder.description;
        this.watchers = builder.stars;
        this.forks = builder.forks;
        this.html_url = checkNotNull(builder.htmlUrl, "html_url == null");
    }

    @Override
    public String toString() {
        return "SupaDeal{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", watchers=" + watchers +
                ", forks=" + forks +
                ", html_url='" + html_url + '\'' +
                '}';
    }

    public static final class Builder {
        private String name;
        private String description;
        private long stars;
        private long forks;
        private String htmlUrl;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder stars(long stars) {
            this.stars = stars;
            return this;
        }

        public Builder forks(long forks) {
            this.forks = forks;
            return this;
        }

        public Builder htmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
            return this;
        }

        public SupaDeal build() {
            return new SupaDeal(this);
        }
    }
}
