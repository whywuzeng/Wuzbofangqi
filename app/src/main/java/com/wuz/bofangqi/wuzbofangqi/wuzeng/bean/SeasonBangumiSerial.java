package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class SeasonBangumiSerial {


    @SerializedName("count")
    public String count;
    /**
     * title : 齐木楠雄的灾难（日播版）
     * area : 日本
     * arealimit : 30
     * attention : 664557
     * bangumi_id : 2544
     * bgmcount : 84
     * cover : http://i0.hdslb.com/bfs/bangumi/eb4f17335f48951945fb9da47e6ee0bc65fa2fbb.jpg
     * square_cover : http://i0.hdslb.com/bfs/bangumi/4d9f90bb20c19bcc94985937a17b38cd245ea7c0.jpg
     * danmaku_count : 573161
     * favorites : 664557
     * is_finish : 0
     * lastupdate_at : 2016-10-27 08:00:10
     * new : true
     * play_count : 88363774
     * season_id : 5070
     * spid : 0
     * url : /bangumi/i/5070/
     * viewRank : 0
     * weekday : -1
     */

    @SerializedName("list")
    public List<ListBean> list;

    public static class ListBean {
        @SerializedName("title")
        public String title;
        @SerializedName("area")
        public String area;
        @SerializedName("arealimit")
        public int arealimit;
        @SerializedName("attention")
        public int attention;
        @SerializedName("bangumi_id")
        public int bangumiId;
        @SerializedName("bgmcount")
        public String bgmcount;
        @SerializedName("cover")
        public String cover;
        @SerializedName("square_cover")
        public String squareCover;
        @SerializedName("danmaku_count")
        public int danmakuCount;
        @SerializedName("favorites")
        public int favorites;
        @SerializedName("is_finish")
        public int isFinish;
        @SerializedName("lastupdate_at")
        public String lastupdateAt;
        @SerializedName("new")
        public boolean newX;
        @SerializedName("play_count")
        public int playCount;
        @SerializedName("season_id")
        public int seasonId;
        @SerializedName("spid")
        public int spid;
        @SerializedName("url")
        public String url;
        @SerializedName("viewRank")
        public int viewRank;
        @SerializedName("weekday")
        public int weekday;
    }
}
