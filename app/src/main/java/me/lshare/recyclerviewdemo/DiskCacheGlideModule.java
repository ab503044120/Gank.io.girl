package me.lshare.recyclerviewdemo;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by Lshare on 2016/6/26.
 */
public class DiskCacheGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int cacheSize50MegaBytes = 52428800;

        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, cacheSize50MegaBytes)
        );
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}
