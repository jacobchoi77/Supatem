package com.moffcomm.supatem.data.remote.mock;

import com.moffcomm.supatem.data.remote.model.SupaDeal;

final class MockSupaDeals {
    static final SupaDeal BUTTERKNIFE = new SupaDeal.Builder() //
            .name("butterknife") //
            .description("View \"injection\" library for Android.") //
            .forks(626) //
            .stars(3136) //
            .htmlUrl("https://github.com/JakeWharton/butterknife") //
            .build();
    static final SupaDeal DAGGER = new SupaDeal.Builder() //
            .name("dagger") //
            .description("A fast dependency injector for Android and Java.") //
            .forks(574) //
            .stars(3085) //
            .htmlUrl("https://github.com/square/dagger") //
            .build();
    static final SupaDeal JAVAPOET = new SupaDeal.Builder() //
            .name("javapoet") //
            .description("A Java API for generating .java source files.") //
            .forks(137) //
            .stars(809) //
            .htmlUrl("https://github.com/square/javapoet") //
            .build();
    static final SupaDeal OKHTTP = new SupaDeal.Builder() //
            .name("okhttp") //
            .description("An HTTP+SPDY client for Android and Java applications.") //
            .forks(828) //
            .stars(3663) //
            .htmlUrl("https://github.com/square/okhttp") //
            .build();
    static final SupaDeal OKIO = new SupaDeal.Builder() //
            .name("okio") //
            .description("A modern I/O API for Java") //
            .forks(126) //
            .stars(954) //
            .htmlUrl("https://github.com/square/okio") //
            .build();
    static final SupaDeal PICASSO = new SupaDeal.Builder() //
            .name("picasso") //
            .description("A powerful image downloading and caching library for Android") //
            .forks(1513) //
            .stars(5279) //
            .htmlUrl("https://github.com/square/picasso") //
            .build();
    static final SupaDeal RETROFIT = new SupaDeal.Builder() //
            .name("retrofit") //
            .description("Type-safe REST client for Android and Java by Square, Inc.") //
            .forks(775) //
            .stars(4583) //
            .htmlUrl("https://github.com/square/retrofit") //
            .build();
    static final SupaDeal SQLBRITE = new SupaDeal.Builder() //
            .name("sqlbrite") //
            .description("A lightweight wrapper around SQLiteOpenHelper which introduces reactive stream"
                    + " semantics to SQL operations.") //
            .forks(63) //
            .stars(987) //
            .htmlUrl("https://github.com/square/sqlbrite") //
            .build();
    static final SupaDeal TELESCOPE = new SupaDeal.Builder() //
            .name("telescope") //
            .description("A simple tool to allow easy bug report capturing within your app.") //
            .forks(31) //
            .stars(399) //
            .htmlUrl("https://github.com/mattprecious/telescope") //
            .build();
    static final SupaDeal U2020 = new SupaDeal.Builder() //
            .name("u2020") //
            .description("A sample Android app which showcases advanced usage of Dagger among other"
                    + " open source libraries.") //
            .forks(260) //
            .stars(1487) //
            .htmlUrl("https://github.com/JakeWharton/u2020") //
            .build();
    static final SupaDeal WIRE = new SupaDeal.Builder() //
            .name("wire") //
            .description("Clean, lightweight protocol buffers for Android and Java.") //
            .forks(100) //
            .stars(616) //
            .htmlUrl("https://github.com/square/wire") //
            .build();
    static final SupaDeal MOSHI = new SupaDeal.Builder() //
            .name("moshi") //
            .description("") // Intentionally empty description.
            .forks(19) //
            .stars(465) //
            .htmlUrl("https://github.com/square/moshi") //
            .build();

    private MockSupaDeals() {
        throw new AssertionError("No instances.");
    }
}
