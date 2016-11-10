package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016-11-10.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RegionTypeRecommendinfo {


    @SerializedName("code")
    public int code;


    @SerializedName("data")
    public DataBean data;
    @SerializedName("message")
    public String message;

    public static class DataBean {
        @SerializedName("banner")
        public BannerBean banner;
        /**
         * title : 【Undertale手书】骨兄弟 孩子气的战争
         * cover : http://i2.hdslb.com/bfs/archive/e7402ac56f2c45f445ed9710c5e3b6c0a242b100.jpg
         * uri : bilibili://video/7002824
         * param : 7002824
         * goto : av
         * name : 决心801
         * play : 8816
         * danmaku : 192
         * reply : 146
         * favourite : 1440
         */

        @SerializedName("recommend")
        public List<RecommendBean> recommend;
        @SerializedName("new")
        public List<RecommendBean> newX;
        @SerializedName("dynamic")
        public List<RecommendBean> dynamic;

        public static class BannerBean {
            /**
             * id : 17637
             * title : 创造，属于你的世界
             * image : http://i0.hdslb.com/bfs/archive/d5d8842836eb307c5c9ced71adf4bd684cbe6386.jpg
             * hash : f1e4ade3c81ff7556e48f0a8ac5ee275
             * uri : http://www.bilibili.com/blackboard/activity-2016MMD.html
             * is_ad : false
             */

            @SerializedName("top")
            public List<TopBean> top;

            public static class TopBean {
                @SerializedName("id")
                public int id;
                @SerializedName("title")
                public String title;
                @SerializedName("image")
                public String image;
                @SerializedName("hash")
                public String hash;
                @SerializedName("uri")
                public String uri;
                @SerializedName("is_ad")
                public boolean isAd;
            }
        }

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
