package com.wuz.bofangqi.wuzbofangqi.wuzeng.network.Api;

import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.LiveIndex;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
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
}
