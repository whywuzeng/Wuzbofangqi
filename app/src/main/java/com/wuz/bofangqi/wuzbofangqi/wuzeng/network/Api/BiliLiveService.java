package com.wuz.bofangqi.wuzbofangqi.wuzeng.network.Api;

import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.BangumiDetailRecommend;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.HDVideo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.LiveIndex;
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

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016-10-12.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.network.Api
 * Author:Administrator
 * Fuction: 直播请求模块
 * UpdateUser:
 * UpdateDate:
 */
public interface BiliLiveService {
    @GET("AppIndex/home?_device=android&_hwid=51e96f5f2f54d5f9&_ulv=10000&access_key=563d6046f06289cbdcb472601ce5a761&appkey=c1b107428d337928&build=410000&platform=android&scale=xxhdpi&sign=fbdcfe141853f7e2c84c4d401f6a8758")
    Observable<LiveIndex> getLiveIndex();

    /*http://live.bilibili.com/api/playurl?player=1&quality=0&cid=158153*/
    @GET("api/playurl?player=1&quality=0")
    Observable<ResponseBody> getLiveUrl(@Query("cid") int cid);

/*     发现页面热搜词标签请求
     http://s.search.bilibili.com/main/hotword?access_key=ec0f54fc369d8c104ee1068672975d6a&actionKey=appkey&appkey=27eb53fc9058f8c3*/
    @GET("http://s.search.bilibili.com/main/hotword?access_key=ec0f54fc369d8c104ee1068672975d6a&actionKey=appkey&appkey=27eb53fc9058f8c3")
    Observable<hotTagsSearch> getHotTagsSearch();

    //新的Search API
   /* http://app.bilibili.com/x/v2/search?actionKey=appkey&appkey=27eb53fc9058f8c3&build=3710&device=phone&duration=0&mobi_app=iphone&order=default&platform=ios&rid=0&keyword=fate&pn=1&ps=10*/


    @GET("x/v2/search?actionKey=appkey&appkey=27eb53fc9058f8c3&build=3710&device=phone&duration=0&mobi_app=iphone&order=default&platform=ios&rid=0")
    Observable<SearchArchiveInfo> searchArchive(@Query("keyword") String content, @Query("pn") int page, @Query("ps") int pagesize);



    @FormUrlEncoded
    @POST("search")
    Observable<SearchResult> getSearchResult(@Field("content") String content,
                                             @Field("page") int page,
                                             @Field("count") int count);

    /*GET http://bilibili-service.daoapp.io/view/6647445 */
    @GET("view/{aid}")
        Observable<VideoDetail> getVideoDetail(@Path("aid") int aid);

   /* http://api.bilibili.cn/feedback?aid=6647445&page=2&pagesize=20&ver=3*/
    @GET("feedback")
    Observable<VideoComment> getVideoComment(@Query("aid") int aid,
                                             @Query("page") int page,
                                             @Query("pagesize") int pagesize,
                                             @Query("ver") int ver
                                             );

    /*http://bilibili-service.daoapp.io/bangumiindex */
    @GET("bangumiindex")
    Observable<bangumiBannerAndRecy> getBangumiBannerAndRecy();

    /*http://app.bilibili.com/bangumi/operation_module?_device=android&_hwid=ac538400c68784bb&_ulv=10000&module=bangumi&platform=android&screen=xxhdpi*/
    @GET("bangumi/operation_module?_device=android&_hwid=ac538400c68784bb&_ulv=10000&module=bangumi&platform=android&screen=xxhdpi")
    Observable<SeasonNewBangumi> getSeasonNewBangumi();

    /*http://bilibili-service.daoapp.io/bangumi*/
    @GET("bangumi")
    Observable<SeasonBangumiSerial> getSeasonBangumiSerial();

    /*GET http://api.bilibili.cn/sp?spid=0&title=%E9%9D%92%E9%AC%BC*/
//    @GET("sp")
//    Observable<>

   /* http://bangumi.bilibili.com/api/bangumi_recommend?access_key=f5bd4e793b82fba5aaf5b91fb549910a&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3470&cursor=0&device=phone&mobi_app=iphone&pagesize=10&platform=ios&sign=56329a5709c401d4d7c0237f64f7943f&ts=1469613558 */
// bangumi detail recommend
    @GET("api/bangumi_recommend?access_key=f5bd4e793b82fba5aaf5b91fb549910a&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3470&cursor=0&device=phone&mobi_app=iphone&pagesize=10&platform=ios&sign=56329a5709c401d4d7c0237f64f7943f&ts=1469613558")
    Observable<BangumiDetailRecommend> getBangumiDetailRecommend();


/**
 * http://bilibili-service.daoapp.io/video/9253164?quality=2
 *
 */
    @GET("/video/{cid}")
    Observable<HDVideo> getHDVideo(@Path("cid") int cid,
                                    @Query("quailty") int quailty,
                                   @Query("type") String type);

    /**
     * <p>
     *  * 分区推荐页面请求api
     * </p>
     *  http://app.bilibili.com/x/v2/region/show?access_key=67cbf6a1e9ad7d7f11bfbd918e50c837&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3600&device=phone&mobi_app=iphone&plat=1&platform=ios&rid=1&sign=959d7b8c09c65e7a66f7e58b1a2bdab9&ts=1472310694
     */

    @GET("x/v2/region/show?access_key=67cbf6a1e9ad7d7f11bfbd918e50c837&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3600&device=phone&mobi_app=iphone&plat=1&platform=ios&sign=959d7b8c09c65e7a66f7e58b1a2bdab9&ts=1472310694")
    Observable<RegionRecommendInfo> getRegionRecommends(@Query("rid") int rid);

    /**
     *
     http://app.bilibili.com/x/v2/region?access_key=f5bd4e793b82fba5aaf5b91fb549910a&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3470&device=phone&mobi_app=iphone&platform=ios&sign=c76b9aa1fbcefcbd9d08b862c050d16e&ts=1469603650
     */

    @GET("x/v2/region?access_key=f5bd4e793b82fba5aaf5b91fb549910a&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3470&device=phone&mobi_app=iphone&platform=ios&sign=c76b9aa1fbcefcbd9d08b862c050d16e&ts=1469603650")
    Observable<RegionHomeInfo> getRegionHomeInfo();

    /**
     * <p>
     * 获取分区类型详情api
     * <p>
     * http://app.bilibili.com/x/v2/region/show/child?build=3600&rid=24
     */
        @GET("x/v2/region/show/child?build=3600")
    Observable<RegionChildInfo> getRegionChildInfo(@Query("rid") int rid);


    /**
     * 分区请求 recommend 推荐的API
     * http://app.bilibili.com/x/v2/region/show?access_key=67cbf6a1e9ad7d7f11bfbd918e50c837&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3600&device=phone&mobi_app=iphone&plat=1&platform=ios&rid=1&sign=959d7b8c09c65e7a66f7e58b1a2bdab9&ts=1472310694
     *
     */

    @GET("x/v2/region/show?access_key=67cbf6a1e9ad7d7f11bfbd918e50c837&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3600&device=phone&mobi_app=iphone&plat=1&platform=ios&sign=959d7b8c09c65e7a66f7e58b1a2bdab9&ts=1472310694")
    Observable<RegionTypeRecommendinfo> getRegionTypeRecommendInfo(@Query("rid") int rid);

}
