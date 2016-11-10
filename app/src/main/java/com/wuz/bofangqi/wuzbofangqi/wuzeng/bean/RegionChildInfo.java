package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016-11-09.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RegionChildInfo {


    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public DataBean data;
    @SerializedName("message")
    public String message;

    public static class DataBean {
        /**
         * title : 【闪现流AMV】↑↑↓↓←→←→BABA
         * cover : http://i1.hdslb.com/bfs/archive/15b794801d13e9da2e156a5381108edb071758f7.jpg
         * uri : bilibili://video/7003869
         * param : 7003869
         * goto : av
         * name : 科学超电磁炮F
         * play : 45833
         * danmaku : 605
         * reply : 514
         * favourite : 2447
         */

        @SerializedName("recommend")
        public List<RecommendBean> recommend;
        @SerializedName("new")
        public List<RecommendBean> newX;

        public static class RecommendBean {
            @SerializedName("title")
            public String title;
            @SerializedName("cover")
            public String cover;
            @SerializedName("uri")
            public String uri;
            @SerializedName("param")
            public String param;
            @SerializedName("goto")
            public String gotoX;
            @SerializedName("name")
            public String name;
            @SerializedName("play")
            public int play;
            @SerializedName("danmaku")
            public int danmaku;
            @SerializedName("reply")
            public int reply;
            @SerializedName("favourite")
            public int favourite;
        }
    }
}
