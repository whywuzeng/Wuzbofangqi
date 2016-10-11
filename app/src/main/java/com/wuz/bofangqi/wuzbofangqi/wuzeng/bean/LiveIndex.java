package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016-10-11.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class LiveIndex {


    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;


    @SerializedName("data")
    public DataEntity data;

    public static class DataEntity {


        @SerializedName("recommend_data")
        public RecommendDataEntity recommendData;
        /**
         * title : 万物皆可萌！省份拟人形象征集~
         * img : http://i0.hdslb.com/bfs/live/c4c45e1b7baa553820ee15ef7f60f8d2ad529796.jpg
         * remark : 十月绘画主题
         * link : http://live.bilibili.com/AppBanner/index?id=305
         */

        @SerializedName("banner")
        public List<BannerEntity> banner;
        /**
         * id : 11
         * name : 手机直播
         * entrance_icon : {"src":"http://static.hdslb.com/live-static/images/mobile/android/big/xxhdpi/11_big.png?2016101101","height":"132","width":"132"}
         */

        @SerializedName("entranceIcons")
        public List<EntranceIconsEntity> entranceIcons;
        @SerializedName("partitions")
        public List<RecommendDataEntity> partitions;

        public static class RecommendDataEntity {
            /**
             * id : 0
             * name : 推荐主播
             * area : hot
             * sub_icon : {"src":"http://static.hdslb.com/live-static/images/mobile/android/small/xxhdpi/-1.png?2016101101","height":"63","width":"63"}
             * count : 2401
             */

            @SerializedName("partition")
            public PartitionEntity partition;
            /**
             * owner : {"face":"http://i0.hdslb.com/bfs/face/80d61c0174448decf0b8faf4fcb1311a26df8922.jpg","mid":17896153,"name":"一币通关者阿水"}
             * cover : {"src":"http://i0.hdslb.com/bfs/live/aeb2da193ed0d7877b8632569594beb8b235dcc1.jpg","height":180,"width":320}
             * title : 很高兴你能来！
             * room_id : 50512
             * check_version : 0
             * online : 1553
             * area : 电子竞技
             * area_id : 4
             * playurl : http://live-play.acgvideo.com/live/323/live_17896153_5250718.flv?wsSecret=950298f7a26f84850872d2e303a8298e&wsTime=57d5150b
             * accept_quality : 4
             * broadcast_type : 0
             * is_tv : 0
             */

            @SerializedName("lives")
            public List<LivesEntity> lives;
            @SerializedName("banner_data")
            public List<List<LivesEntity>> bannerData;

            public static class PartitionEntity {
                @SerializedName("id")
                public int id;
                @SerializedName("name")
                public String name;
                @SerializedName("area")
                public String area;
                /**
                 * src : http://static.hdslb.com/live-static/images/mobile/android/small/xxhdpi/-1.png?2016101101
                 * height : 63
                 * width : 63
                 */

                @SerializedName("sub_icon")
                public SubIconEntity subIcon;
                @SerializedName("count")
                public int count;

                public static class SubIconEntity {
                    @SerializedName("src")
                    public String src;
                    @SerializedName("height")
                    public String height;
                    @SerializedName("width")
                    public String width;
                }
            }

            public static class LivesEntity {
                /**
                 * face : http://i0.hdslb.com/bfs/face/80d61c0174448decf0b8faf4fcb1311a26df8922.jpg
                 * mid : 17896153
                 * name : 一币通关者阿水
                 */

                @SerializedName("owner")
                public OwnerEntity owner;
                @SerializedName("cover")
                public PartitionEntity.SubIconEntity cover;
                @SerializedName("title")
                public String title;
                @SerializedName("room_id")
                public int roomId;
                @SerializedName("check_version")
                public int checkVersion;
                @SerializedName("online")
                public int online;
                @SerializedName("area")
                public String area;
                @SerializedName("area_id")
                public int areaId;
                @SerializedName("playurl")
                public String playurl;
                @SerializedName("accept_quality")
                public String acceptQuality;
                @SerializedName("broadcast_type")
                public int broadcastType;
                @SerializedName("is_tv")
                public int isTv;

                public static class OwnerEntity {
                    @SerializedName("face")
                    public String face;
                    @SerializedName("mid")
                    public int mid;
                    @SerializedName("name")
                    public String name;
                }
            }
        }

        public static class BannerEntity {
            @SerializedName("title")
            public String title;
            @SerializedName("img")
            public String img;
            @SerializedName("remark")
            public String remark;
            @SerializedName("link")
            public String link;
        }

        public static class EntranceIconsEntity {
            @SerializedName("id")
            public int id;
            @SerializedName("name")
            public String name;
            @SerializedName("entrance_icon")
            public RecommendDataEntity.PartitionEntity.SubIconEntity entranceIcon;
        }
    }
}
