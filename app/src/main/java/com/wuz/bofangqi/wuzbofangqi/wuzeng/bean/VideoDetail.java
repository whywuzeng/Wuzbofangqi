package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-10-21.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class VideoDetail implements Parcelable {

    @com.google.gson.annotations.SerializedName("tid")
    public int tid;
    @com.google.gson.annotations.SerializedName("typename")
    public String typename;
    @com.google.gson.annotations.SerializedName("arctype")
    public String arctype;
    @com.google.gson.annotations.SerializedName("play")
    public String play;
    @com.google.gson.annotations.SerializedName("review")
    public String review;
    @com.google.gson.annotations.SerializedName("video_review")
    public String videoReview;
    @com.google.gson.annotations.SerializedName("favorites")
    public String favorites;
    @com.google.gson.annotations.SerializedName("title")
    public String title;
    @com.google.gson.annotations.SerializedName("description")
    public String description;
    @com.google.gson.annotations.SerializedName("tag")
    public String tag;
    @com.google.gson.annotations.SerializedName("pic")
    public String pic;
    @com.google.gson.annotations.SerializedName("author")
    public String author;
    @com.google.gson.annotations.SerializedName("mid")
    public String mid;
    @com.google.gson.annotations.SerializedName("face")
    public String face;
    @com.google.gson.annotations.SerializedName("pages")
    public int pages;
    @com.google.gson.annotations.SerializedName("created_at")
    public String createdAt;
    @com.google.gson.annotations.SerializedName("coins")
    public String coins;

    @com.google.gson.annotations.SerializedName("list")
    public listbean list;
    public static class listbean implements Parcelable {

        @com.google.gson.annotations.SerializedName("0")
        public VideoAdditional videoAdditional;

        public static class VideoAdditional implements Parcelable {

            @com.google.gson.annotations.SerializedName("cid")
            public int cid;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.cid);
            }

            public VideoAdditional() {
            }

            protected VideoAdditional(Parcel in) {
                this.cid = in.readInt();
            }

            public static final Creator<VideoAdditional> CREATOR = new Creator<VideoAdditional>() {
                @Override
                public VideoAdditional createFromParcel(Parcel source) {
                    return new VideoAdditional(source);
                }

                @Override
                public VideoAdditional[] newArray(int size) {
                    return new VideoAdditional[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.videoAdditional, flags);
        }

        public listbean() {
        }

        protected listbean(Parcel in) {
            this.videoAdditional = in.readParcelable(VideoAdditional.class.getClassLoader());
        }

        public static final Creator<listbean> CREATOR = new Creator<listbean>() {
            @Override
            public listbean createFromParcel(Parcel source) {
                return new listbean(source);
            }

            @Override
            public listbean[] newArray(int size) {
                return new listbean[size];
            }
        };
    }

 /*   list : {"0":{"page":1,"type":"vupload","part":"","cid":8444834,"vid":0}}*/


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.tid);
        dest.writeString(this.typename);
        dest.writeString(this.arctype);
        dest.writeString(this.play);
        dest.writeString(this.review);
        dest.writeString(this.videoReview);
        dest.writeString(this.favorites);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.tag);
        dest.writeString(this.pic);
        dest.writeString(this.author);
        dest.writeString(this.mid);
        dest.writeString(this.face);
        dest.writeInt(this.pages);
        dest.writeString(this.createdAt);
        dest.writeString(this.coins);
    }

    public VideoDetail() {
    }

    protected VideoDetail(Parcel in) {
        this.tid = in.readInt();
        this.typename = in.readString();
        this.arctype = in.readString();
        this.play = in.readString();
        this.review = in.readString();
        this.videoReview = in.readString();
        this.favorites = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.tag = in.readString();
        this.pic = in.readString();
        this.author = in.readString();
        this.mid = in.readString();
        this.face = in.readString();
        this.pages = in.readInt();
        this.createdAt = in.readString();
        this.coins = in.readString();
    }

    public static final Parcelable.Creator<VideoDetail> CREATOR = new Parcelable.Creator<VideoDetail>() {
        @Override
        public VideoDetail createFromParcel(Parcel source) {
            return new VideoDetail(source);
        }

        @Override
        public VideoDetail[] newArray(int size) {
            return new VideoDetail[size];
        }
    };
}
