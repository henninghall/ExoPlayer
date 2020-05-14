package com.google.android.exoplayer2.demo;

import android.content.Context;
import android.media.MediaDrm;
import android.net.Uri;

import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.offline.Download;
import com.google.android.exoplayer2.offline.DownloadHelper;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;

import java.io.IOException;


class OurMediaSourceFactory {

    private final Context context;
    private final String licenceUrl;
    private final String jwt;
    private final Uri uri;
    private final String slug;
    private DataSource.Factory dataSourceFactory;

    public OurMediaSourceFactory(String slug, String licenceUrl, String jwt, String src, Context context, Cache cache) {
        this.slug = slug;
        this.uri = Uri.parse(src);
        this.licenceUrl = licenceUrl;
        this.jwt = jwt;
        this.context = context;
        this.dataSourceFactory = Utils.buildDataSourceFactory(context, cache);
    }

    public MediaSource get() throws Exception {
        return createTrailerMediaSource();
    }

    private MediaSource createTrailerMediaSource() {
        return new DashMediaSource.Factory(dataSourceFactory)
                       .createMediaSource(uri);
    }

}




