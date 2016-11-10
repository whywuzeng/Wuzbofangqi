package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-11-09.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RegionHomeInfo implements Parcelable {


    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;
    @SerializedName("ver")
    public String ver;
    /**
     * tid : 65537
     * reid : 0
     * name : 直播
     * logo :
     * goto :
     * param :
     */

    @SerializedName("data")
    public List<DataBean> data;

    public static class DataBean implements Parcelable {

        @SerializedName("tid")
        public int tid;
        @SerializedName("reid")
        public int reid;
        @SerializedName("name")
        public String name;
        @SerializedName("logo")
        public String logo;
        @SerializedName("goto")
        public String gotoX;
        @SerializedName("param")
        public String param;

        @SerializedName("children")
        public List<ChildrenBean> childlist;

        public static class ChildrenBean implements Parcelable {

            @SerializedName("tid")
            public int tid;
            @SerializedName("reid")
            public int reid;
            @SerializedName("name")
            public String name;
            @SerializedName("logo")
            public String logo;
            @SerializedName("goto")
            public String gotoX;
            @SerializedName("param")
            public String param;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.tid);
                dest.writeInt(this.reid);
                dest.writeString(this.name);
                dest.writeString(this.logo);
                dest.writeString(this.gotoX);
                dest.writeString(this.param);
            }

            public ChildrenBean() {
            }

            protected ChildrenBean(Parcel in) {
                this.tid = in.readInt();
                this.reid = in.readInt();
                this.name = in.readString();
                this.logo = in.readString();
                this.gotoX = in.readString();
                this.param = in.readString();
            }

            public static final Creator<ChildrenBean> CREATOR = new Creator<ChildrenBean>() {
                @Override
                public ChildrenBean createFromParcel(Parcel source) {
                    return new ChildrenBean(source);
                }

                @Override
                public ChildrenBean[] newArray(int size) {
                    return new ChildrenBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.tid);
            dest.writeInt(this.reid);
            dest.writeString(this.name);
            dest.writeString(this.logo);
            dest.writeString(this.gotoX);
            dest.writeString(this.param);
            dest.writeList(this.childlist);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.tid = in.readInt();
            this.reid = in.readInt();
            this.name = in.readString();
            this.logo = in.readString();
            this.gotoX = in.readString();
            this.param = in.readString();
            this.childlist = new ArrayList<ChildrenBean>();
            in.readList(this.childlist, ChildrenBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.message);
        dest.writeString(this.ver);
        dest.writeTypedList(this.data);
    }

    public RegionHomeInfo() {
    }

    protected RegionHomeInfo(Parcel in) {
        this.code = in.readInt();
        this.message = in.readString();
        this.ver = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<RegionHomeInfo> CREATOR = new Parcelable.Creator<RegionHomeInfo>() {
        @Override
        public RegionHomeInfo createFromParcel(Parcel source) {
            return new RegionHomeInfo(source);
        }

        @Override
        public RegionHomeInfo[] newArray(int size) {
            return new RegionHomeInfo[size];
        }
    };
}
