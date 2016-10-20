package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-10-19.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class SearchResult {


    @SerializedName("page")
    public int page;
    @SerializedName("pagesize")
    public int pagesize;
    @SerializedName("pageinfo")
    public PageinfoBean pageinfo;
    @SerializedName("result")
    public ResultBean result;

    public static class PageinfoBean {
        @SerializedName("bangumi")
        public BangumiBean bangumi;
        @SerializedName("movie")
        public BangumiBean movie;
        @SerializedName("pgc")
        public BangumiBean pgc;
        @SerializedName("special")
        public BangumiBean special;
        @SerializedName("topic")
        public BangumiBean topic;
        @SerializedName("tvplay")
        public BangumiBean tvplay;
        @SerializedName("upuser")
        public BangumiBean upuser;
        @SerializedName("video")
        public BangumiBean video;

        public static class BangumiBean {
            @SerializedName("total")
            public int total;
            @SerializedName("numResults")
            public int numResults;
            @SerializedName("pages")
            public int pages;
        }
    }

    public static class ResultBean implements Parcelable {

        @SerializedName("video")
        public List<Video1Bean> video1;
        @SerializedName("bangumi")
        public List<Bangumi1Bean> bangumi1;
        @SerializedName("topic")
        public List<Topic1Bean> topic1;

        public static class Video1Bean implements Parcelable {

            @SerializedName("aid")
            public String aid;
            @SerializedName("mid")
            public int mid;
            @SerializedName("copyright")
            public String copyright;
            @SerializedName("typeid")
            public int typeid;
            @SerializedName("typename")
            public String typename;
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
            @SerializedName("author")
            public String author;
            @SerializedName("description")
            public String description;
            @SerializedName("create")
            public String create;
            @SerializedName("pic")
            public String pic;
            @SerializedName("credit")
            public int credit;
            @SerializedName("coins")
            public int coins;
            @SerializedName("duration")
            public String duration;
            @SerializedName("comment")
            public int comment;
            @SerializedName("badgepay")
            public boolean badgepay;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.aid);
                dest.writeInt(this.mid);
                dest.writeString(this.copyright);
                dest.writeInt(this.typeid);
                dest.writeString(this.typename);
                dest.writeString(this.title);
                dest.writeString(this.subtitle);
                dest.writeInt(this.play);
                dest.writeInt(this.review);
                dest.writeInt(this.videoReview);
                dest.writeInt(this.favorites);
                dest.writeString(this.author);
                dest.writeString(this.description);
                dest.writeString(this.create);
                dest.writeString(this.pic);
                dest.writeInt(this.credit);
                dest.writeInt(this.coins);
                dest.writeString(this.duration);
                dest.writeInt(this.comment);
                dest.writeByte(this.badgepay ? (byte) 1 : (byte) 0);
            }

            public Video1Bean() {
            }

            protected Video1Bean(Parcel in) {
                this.aid = in.readString();
                this.mid = in.readInt();
                this.copyright = in.readString();
                this.typeid = in.readInt();
                this.typename = in.readString();
                this.title = in.readString();
                this.subtitle = in.readString();
                this.play = in.readInt();
                this.review = in.readInt();
                this.videoReview = in.readInt();
                this.favorites = in.readInt();
                this.author = in.readString();
                this.description = in.readString();
                this.create = in.readString();
                this.pic = in.readString();
                this.credit = in.readInt();
                this.coins = in.readInt();
                this.duration = in.readString();
                this.comment = in.readInt();
                this.badgepay = in.readByte() != 0;
            }

            public static final Creator<Video1Bean> CREATOR = new Creator<Video1Bean>() {
                @Override
                public Video1Bean createFromParcel(Parcel source) {
                    return new Video1Bean(source);
                }

                @Override
                public Video1Bean[] newArray(int size) {
                    return new Video1Bean[size];
                }
            };
        }

        public static class Bangumi1Bean implements Parcelable {

            @SerializedName("season_id")
            public int seasonId;
            @SerializedName("bangumi_id")
            public int bangumiId;
            @SerializedName("spid")
            public int spid;
            @SerializedName("title")
            public String title;
            @SerializedName("brief")
            public String brief;
            @SerializedName("styles")
            public String styles;
            @SerializedName("cv")
            public String cv;
            @SerializedName("staff")
            public String staff;
            @SerializedName("evaluate")
            public String evaluate;
            @SerializedName("cover")
            public String cover;
            @SerializedName("typeurl")
            public String typeurl;
            @SerializedName("favorites")
            public int favorites;
            @SerializedName("is_finish")
            public int isFinish;
            @SerializedName("play_count")
            public int playCount;
            @SerializedName("danmaku_count")
            public int danmakuCount;
            @SerializedName("total_count")
            public int totalCount;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.seasonId);
                dest.writeInt(this.bangumiId);
                dest.writeInt(this.spid);
                dest.writeString(this.title);
                dest.writeString(this.brief);
                dest.writeString(this.styles);
                dest.writeString(this.cv);
                dest.writeString(this.staff);
                dest.writeString(this.evaluate);
                dest.writeString(this.cover);
                dest.writeString(this.typeurl);
                dest.writeInt(this.favorites);
                dest.writeInt(this.isFinish);
                dest.writeInt(this.playCount);
                dest.writeInt(this.danmakuCount);
                dest.writeInt(this.totalCount);
            }

            public Bangumi1Bean() {
            }

            protected Bangumi1Bean(Parcel in) {
                this.seasonId = in.readInt();
                this.bangumiId = in.readInt();
                this.spid = in.readInt();
                this.title = in.readString();
                this.brief = in.readString();
                this.styles = in.readString();
                this.cv = in.readString();
                this.staff = in.readString();
                this.evaluate = in.readString();
                this.cover = in.readString();
                this.typeurl = in.readString();
                this.favorites = in.readInt();
                this.isFinish = in.readInt();
                this.playCount = in.readInt();
                this.danmakuCount = in.readInt();
                this.totalCount = in.readInt();
            }

            public static final Creator<Bangumi1Bean> CREATOR = new Creator<Bangumi1Bean>() {
                @Override
                public Bangumi1Bean createFromParcel(Parcel source) {
                    return new Bangumi1Bean(source);
                }

                @Override
                public Bangumi1Bean[] newArray(int size) {
                    return new Bangumi1Bean[size];
                }
            };
        }

        public static class Topic1Bean implements Parcelable {

            @SerializedName("tp_id")
            public int tpId;
            @SerializedName("tp_type")
            public int tpType;
            @SerializedName("mid")
            public int mid;
            @SerializedName("author")
            public String author;
            @SerializedName("title")
            public String title;
            @SerializedName("keyword")
            public String keyword;
            @SerializedName("arcurl")
            public String arcurl;
            @SerializedName("cover")
            public String cover;
            @SerializedName("description")
            public String description;
            @SerializedName("click")
            public int click;
            @SerializedName("review")
            public int review;
            @SerializedName("favourite")
            public int favourite;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.tpId);
                dest.writeInt(this.tpType);
                dest.writeInt(this.mid);
                dest.writeString(this.author);
                dest.writeString(this.title);
                dest.writeString(this.keyword);
                dest.writeString(this.arcurl);
                dest.writeString(this.cover);
                dest.writeString(this.description);
                dest.writeInt(this.click);
                dest.writeInt(this.review);
                dest.writeInt(this.favourite);
            }

            public Topic1Bean() {
            }

            protected Topic1Bean(Parcel in) {
                this.tpId = in.readInt();
                this.tpType = in.readInt();
                this.mid = in.readInt();
                this.author = in.readString();
                this.title = in.readString();
                this.keyword = in.readString();
                this.arcurl = in.readString();
                this.cover = in.readString();
                this.description = in.readString();
                this.click = in.readInt();
                this.review = in.readInt();
                this.favourite = in.readInt();
            }

            public static final Creator<Topic1Bean> CREATOR = new Creator<Topic1Bean>() {
                @Override
                public Topic1Bean createFromParcel(Parcel source) {
                    return new Topic1Bean(source);
                }

                @Override
                public Topic1Bean[] newArray(int size) {
                    return new Topic1Bean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.video1);
            dest.writeList(this.bangumi1);
            dest.writeList(this.topic1);
        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.video1 = new ArrayList<Video1Bean>();
            in.readList(this.video1, Video1Bean.class.getClassLoader());
            this.bangumi1 = new ArrayList<Bangumi1Bean>();
            in.readList(this.bangumi1, Bangumi1Bean.class.getClassLoader());
            this.topic1 = new ArrayList<Topic1Bean>();
            in.readList(this.topic1, Topic1Bean.class.getClassLoader());
        }

        public static final Parcelable.Creator<ResultBean> CREATOR = new Parcelable.Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel source) {
                return new ResultBean(source);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };
    }
}
