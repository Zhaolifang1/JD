package com.example.jd.app;

import android.os.Environment;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobApplication;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.io.File;

/**
 * @author 赵利芳
 *        类的作用：
 * @date
 */

public class App extends MobApplication {

    {
        PlatformConfig.setQQZone("1106036236","mjFCi0oxXZKZEWJs");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        UMShareAPI.get(this);
        //初始化ImageLoader
        initImageLoader();
    }
    //初始化ImageLoader
    private void initImageLoader() {
        File file = new File(Environment.getDownloadCacheDirectory().getParent() + "/JDImage");
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheExtraOptions(100, 100)
                .threadPoolSize(3)
                .threadPriority(100)
                .diskCache(new UnlimitedDiskCache(file))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(100 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(build);
    }

}
