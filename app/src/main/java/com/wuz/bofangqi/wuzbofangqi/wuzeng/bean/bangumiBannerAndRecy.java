package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction: bangumi_fragment_banner和recy的数据后台
 * UpdateUser:
 * UpdateDate:
 */
public class bangumiBannerAndRecy {

    /**
     * title : 夏目
     * link : http://bangumi.bilibili.com/anime/5550
     * img : http://i0.hdslb.com/bfs/bangumi/eef4d3e7f71c59630f13e67925aa85da1d4caf36.jpg
     * simg :
     * aid : 0
     * type : link
     * platform : 0
     * pid : 0
     */

    @SerializedName("banners")
    public List<BannersBean> banners;
    /**
     * aid : 6825343
     * title : 【1080P】我的青春恋爱物语果然有问题。続 OVA【生肉】
     * subtitle :
     * play : 29434
     * review : 16
     * video_review : 2174
     * favorites : 1781
     * mid : 20107571
     * author : aragakiyukino
     * description : BDRip 『やはりゲームでも俺の青春ラブコメはまちがっている。続』同梱OVA
     原作10.5巻 - ｢きっと、女の子はお砂糖とスパイスと素敵な何かでできている。｣
     * create : 2016-10-25 21:06
     * pic : http://i2.hdslb.com/bfs/archive/3b6c2ce37a98012f353f05af60bb37706dd88508.jpg_320x200.jpg
     * coins : 852
     * duration : 23:43
     */

    @SerializedName("recommends")
    public List<RecommendsBean> recommends;

    public static class BannersBean {
        @SerializedName("title")
        public String title;
        @SerializedName("link")
        public String link;
        @SerializedName("img")
        public String img;
        @SerializedName("simg")
        public String simg;
        @SerializedName("aid")
        public int aid;
        @SerializedName("type")
        public String type;
        @SerializedName("platform")
        public int platform;
        @SerializedName("pid")
        public int pid;
    }

    public static class RecommendsBean {
        @SerializedName("aid")
        public String aid;
        @SerializedName("title")
        public String title;
        @SerializedName("subtitle")
        public String subtitle;
        @SerializedName("play")
        public int play;
        @SerializedName("review")
        public int review;
        @SerializedName("video_review")
        public int videoReview;
        @SerializedName("favorites")
        public int favorites;
        @SerializedName("mid")
        public int mid;
        @SerializedName("author")
        public String author;
        @SerializedName("description")
        public String description;
        @SerializedName("create")
        public String create;
        @SerializedName("pic")
        public String pic;
        @SerializedName("coins")
        public int coins;
        @SerializedName("duration")
        public String duration;
    }
}
