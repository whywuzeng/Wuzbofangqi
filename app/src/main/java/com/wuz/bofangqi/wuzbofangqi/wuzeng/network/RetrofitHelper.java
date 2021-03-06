package com.wuz.bofangqi.wuzbofangqi.wuzeng.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.App.OhMyWuzZhibo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.BangumiDetailRecommend;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.HDVideo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionChildInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionHomeInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionRecommendInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionTypeRecommendinfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SearchArchiveInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SearchResult;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SeasonBangumiSerial;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SeasonNewBangumi;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.VideoComment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.VideoDetail;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.bangumiBannerAndRecy;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.hotTagsSearch;
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
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    public static final String BASE_APP_BILI="http://app.bilibili.com/";

    public static final String BASE_API_URL="http://api.bilibili.cn/";

    public static final String BASE_SEARCH_URL="http://bilibili-service.daoapp.io/";

    private static final String LIVE_BASE_URL = "http://live.bilibili.com/";

    public static final String HOT_SEARCH="http://s.search.bilibili.com/";

    public static final String BANGUMI_API="http://bangumi.bilibili.com/";

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

    public static Observable<hotTagsSearch> getHotTagsSearch()
    {
        Retrofit build = new Retrofit.Builder().baseUrl(HOT_SEARCH)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<hotTagsSearch> hotTagsSearchObservable = build.create(BiliLiveService.class).getHotTagsSearch();

        return hotTagsSearchObservable;
    }

    public static Observable<SearchResult> getSearchResult(String content,int page,int count)
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_SEARCH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<SearchResult> searchResultObservable = build.create(BiliLiveService.class).getSearchResult(content, page, count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return searchResultObservable;
    }

    public static Observable<SearchArchiveInfo> getSearchArchiveInfo(String content,int page,int pagesize)
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_APP_BILI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<SearchArchiveInfo> searchArchiveInfoObservable = build.create(BiliLiveService.class).searchArchive(content, page, pagesize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return searchArchiveInfoObservable;
    }



    public static Observable<VideoDetail> getVideoDetail(int sid)
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_SEARCH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<VideoDetail> videoDetailObservable = build.create(BiliLiveService.class).getVideoDetail(sid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return videoDetailObservable;
    }

    /**
     * @Query("aid") int aid,
     @Query("page") int page,
     @Query("pagesize") int pagesize,
     @Query("ver") int ver
     );
     * @return
     */
    public static Observable<VideoComment> getVideoComment(int aid,int page,int pagesize,int ver)
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<VideoComment> videoCommentObservable = build.create(BiliLiveService.class).getVideoComment(aid, page, pagesize, ver)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return videoCommentObservable;
    }

    public static Observable<bangumiBannerAndRecy> getBangumiBannerAndRecy()
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_SEARCH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<bangumiBannerAndRecy> bangumiBannerAndRecy = build.create(BiliLiveService.class).getBangumiBannerAndRecy()
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        return bangumiBannerAndRecy;
    }

    public static Observable<SeasonNewBangumi> getSeasonNewBangumi()
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_APP_BILI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<SeasonNewBangumi> seasonNewBangumiObservable = build.create(BiliLiveService.class).getSeasonNewBangumi()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return  seasonNewBangumiObservable;
    }

    public static Observable<SeasonBangumiSerial> getSeasonBangumiSerial()
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_SEARCH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<SeasonBangumiSerial> seasonBangumiSerialObservable = build.create(BiliLiveService.class).getSeasonBangumiSerial()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return seasonBangumiSerialObservable;
    }

    public static Observable<BangumiDetailRecommend> getBangumiDetailRecommend()
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BANGUMI_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<BangumiDetailRecommend> bangumiDetailRecommendObservable = build.create(BiliLiveService.class).getBangumiDetailRecommend()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return bangumiDetailRecommendObservable;

    }

    /**
     *  http://bilibili-service.daoapp.io/video/9253164?quality=2
     * @return
     */

    public static Observable<HDVideo> getHDVideoApi(int cid,int quailty,String type)
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_SEARCH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<HDVideo> hdVideoObservable = build.create(BiliLiveService.class).getHDVideo(cid, quailty, type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return hdVideoObservable;
    }

    public static Observable<RegionRecommendInfo> getRegionRecommend(int rid)
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_APP_BILI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<RegionRecommendInfo> regionRecommendInfoObservable = build.create(BiliLiveService.class)
                .getRegionRecommends(rid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return regionRecommendInfoObservable;

    }

    public static Observable<RegionHomeInfo> getRegionHomeInfo()
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_APP_BILI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<RegionHomeInfo> regionHomeInfoObservable = build.create(BiliLiveService.class)
                .getRegionHomeInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

        return regionHomeInfoObservable;
    }

    public static Observable<RegionChildInfo> getRegionChildInfo(int rid)
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_APP_BILI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<RegionChildInfo> regionChildInfoObservable = build.create(BiliLiveService.class)
                .getRegionChildInfo(rid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return regionChildInfoObservable;
    }

    public static Observable<RegionTypeRecommendinfo> getRegionTypeRecommendInfo(int rid)
    {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_APP_BILI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<RegionTypeRecommendinfo> regionTypeRecommendinfoObservable = build.create(BiliLiveService.class).getRegionTypeRecommendInfo(rid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return regionTypeRecommendinfoObservable;
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
