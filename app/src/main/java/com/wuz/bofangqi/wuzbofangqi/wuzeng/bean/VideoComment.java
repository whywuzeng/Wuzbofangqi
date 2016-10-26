package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016-10-24.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class VideoComment {


    @SerializedName("results")
    public int results;
    @SerializedName("pages")
    public int pages;
    @SerializedName("isAdmin")
    public int isAdmin;
    @SerializedName("needCode")
    public boolean needCode;
    @SerializedName("owner")
    public int owner;
    @SerializedName("hotList")
    public List<HotListBean> hotList;
    @SerializedName("list")
    public List<ListBean> list;

    public static class HotListBean {
        @SerializedName("mid")
        public int mid;
        @SerializedName("lv")
        public int lv;
        @SerializedName("fbid")
        public String fbid;
        @SerializedName("ad_check")
        public int adCheck;
        @SerializedName("good")
        public int good;
        @SerializedName("isgood")
        public int isgood;
        @SerializedName("msg")
        public String msg;
        @SerializedName("device")
        public String device;
        @SerializedName("create")
        public int create;
        @SerializedName("create_at")
        public String createAt;
        @SerializedName("reply_count")
        public int replyCount;
        @SerializedName("face")
        public String face;
        @SerializedName("rank")
        public int rank;
        @SerializedName("nick")
        public String nick;
        @SerializedName("level_info")
        public LevelInfoBean levelInfo;
        @SerializedName("sex")
        public String sex;

        public static class LevelInfoBean {
            @SerializedName("current_exp")
            public int currentExp;
            @SerializedName("current_level")
            public int currentLevel;
            @SerializedName("current_min")
            public int currentMin;
            //这有可能为空 真郁闷
//            @SerializedName("next_exp")
//            public int nextExp;
        }
    }

    public static class ListBean {
        @SerializedName("mid")
        public int mid;
        @SerializedName("lv")
        public int lv;
        @SerializedName("fbid")
        public String fbid;
        @SerializedName("ad_check")
        public int adCheck;
        @SerializedName("good")
        public int good;
        @SerializedName("isgood")
        public int isgood;
        @SerializedName("msg")
        public String msg;
        @SerializedName("device")
        public String device;
        @SerializedName("create")
        public int create;
        @SerializedName("create_at")
        public String createAt;
        @SerializedName("reply_count")
        public int replyCount;
        @SerializedName("face")
        public String face;
        @SerializedName("rank")
        public int rank;
        @SerializedName("nick")
        public String nick;
        @SerializedName("level_info")
        public HotListBean.LevelInfoBean levelInfo;
        @SerializedName("sex")
        public String sex;
        @SerializedName("reply")
        public Object reply;
    }
}
