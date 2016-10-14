package com.wuz.bofangqi.wuzbofangqi.wuzeng.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.App.OhMyWuzZhibo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.Api.BiliLiveService;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016-10-12.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.network
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RetrofitHelper {

    private static OkHttpClient mOkHttpClient;

    private static final String LIVE_BASE_URL = "http://live.bilibili.com/";

    public static final String USERAGENT="Ohjiushigan Android Client/2.1 (jiushiqiangone@sina.com)";

    static {
        initOkHttpClient();
    }

    public static BiliLiveService getBiliLiveServiceApi()
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(LIVE_BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return build.create(BiliLiveService.class);

    }

    public static BiliLiveService getBiliLiveUrlApi()
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(LIVE_BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return build.create(BiliLiveService.class);
    }

    private static void initOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//全部都显示了
        if (mOkHttpClient==null)
        {
            synchronized (RetrofitHelper.class)
            {
                if (mOkHttpClient==null)
                {
                    File wuCache = new File(OhMyWuzZhibo.getmInstance().getCacheDir(), "wuCache");
                    Cache cache = new Cache(wuCache, 1024 * 1024 * 100);

                    mOkHttpClient=new OkHttpClient.Builder().cache(cache)
                            .addInterceptor(httpLoggingInterceptor)
                            .addNetworkInterceptor(new StethoInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20,TimeUnit.SECONDS)
                            .readTimeout(20,TimeUnit.SECONDS)
                            .addInterceptor(new UserAgentInterceptor())
                            .build();
                }
            }
        }
    }

   static class UserAgentInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request Originrequest = chain.request();
            Request build = Originrequest.newBuilder().removeHeader("User-Agent")
                    .addHeader("User-Agent", USERAGENT).build();

            Response proceed = chain.proceed(build);
            return proceed;
        }
    }
}
