package com.google.android.exoplayer2.demo;

import android.content.Context;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class Utils {


    public static HttpDataSource.Factory buildHttpDataSourceFactory(Context context) {
        String userAgent = Util.getUserAgent(context, "ExoPlayerDemo");
        return new DefaultHttpDataSourceFactory(userAgent);
    }

    public static DataSource.Factory buildDataSourceFactory(Context context, Cache downloadCache) {
        DefaultDataSourceFactory upstreamFactory =
                new DefaultDataSourceFactory(context, buildHttpDataSourceFactory(context));
        return buildReadOnlyCacheDataSource(upstreamFactory, downloadCache);
    }


    private static CacheDataSourceFactory buildReadOnlyCacheDataSource(
            DataSource.Factory upstreamFactory, Cache cache) {
        return new CacheDataSourceFactory(
                cache,
                upstreamFactory,
                new FileDataSource.Factory(),
                null,
                CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR,
                null);
    }
}
