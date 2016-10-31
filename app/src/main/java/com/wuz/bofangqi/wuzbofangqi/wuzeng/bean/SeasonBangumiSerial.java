package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-10-27.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class SeasonBangumiSerial implements Parcelable {


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

    public static class ListBean implements Parcelable {

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.area);
            dest.writeInt(this.arealimit);
            dest.writeInt(this.attention);
            dest.writeInt(this.bangumiId);
            dest.writeString(this.bgmcount);
            dest.writeString(this.cover);
            dest.writeString(this.squareCover);
            dest.writeInt(this.danmakuCount);
            dest.writeInt(this.favorites);
            dest.writeInt(this.isFinish);
            dest.writeString(this.lastupdateAt);
            dest.writeByte(this.newX ? (byte) 1 : (byte) 0);
            dest.writeInt(this.playCount);
            dest.writeInt(this.seasonId);
            dest.writeInt(this.spid);
            dest.writeString(this.url);
            dest.writeInt(this.viewRank);
            dest.writeInt(this.weekday);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.title = in.readString();
            this.area = in.readString();
            this.arealimit = in.readInt();
            this.attention = in.readInt();
            this.bangumiId = in.readInt();
            this.bgmcount = in.readString();
            this.cover = in.readString();
            this.squareCover = in.readString();
            this.danmakuCount = in.readInt();
            this.favorites = in.readInt();
            this.isFinish = in.readInt();
            this.lastupdateAt = in.readString();
            this.newX = in.readByte() != 0;
            this.playCount = in.readInt();
            this.seasonId = in.readInt();
            this.spid = in.readInt();
            this.url = in.readString();
            this.viewRank = in.readInt();
            this.weekday = in.readInt();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.count);
        dest.writeList(this.list);
    }

    public SeasonBangumiSerial() {
    }

    protected SeasonBangumiSerial(Parcel in) {
        this.count = in.readString();
        this.list = new ArrayList<ListBean>();
        in.readList(this.list, ListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SeasonBangumiSerial> CREATOR = new Parcelable.Creator<SeasonBangumiSerial>() {
        @Override
        public SeasonBangumiSerial createFromParcel(Parcel source) {
            return new SeasonBangumiSerial(source);
        }

        @Override
        public SeasonBangumiSerial[] newArray(int size) {
            return new SeasonBangumiSerial[size];
        }
    };
}
