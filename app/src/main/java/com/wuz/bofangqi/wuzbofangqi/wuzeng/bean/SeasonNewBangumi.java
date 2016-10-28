package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import android.os.Parcel;
import android.os.Parcelable;

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
public class SeasonNewBangumi implements Parcelable {


    @SerializedName("code")
    public int code;
    @SerializedName("ver")
    public String ver;
    @SerializedName("screen")
    public String screen;
    @SerializedName("count")
    public int count;
    /**
     * title : Re：从零开始的异世界生活
     * remark : Re：从零开始的异世界生活
     * remark2 :
     * style : medium
     * imagekey : a38fdd1a07a3d4ab21a6eaa251f2fd74
     * imageurl : http://i2.hdslb.com/320_452/u_user/a0a21991e1f7761642f5e950c898ba68.jpg
     * width : 320
     * height : 452
     * type : bangumi
     * spname : Re：从零开始的异世界生活
     * spid : 66139
     */

    @SerializedName("list")
    public List<ListBean> list;

    public static class ListBean implements Parcelable {

        @SerializedName("title")
        public String title;
        @SerializedName("remark")
        public String remark;
        @SerializedName("remark2")
        public String remark2;
        @SerializedName("style")
        public String style;
        @SerializedName("imagekey")
        public String imagekey;
        @SerializedName("imageurl")
        public String imageurl;
        @SerializedName("width")
        public int width;
        @SerializedName("height")
        public int height;
        @SerializedName("type")
        public String type;
        @SerializedName("spname")
        public String spname;
        @SerializedName("spid")
        public int spid;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.remark);
            dest.writeString(this.remark2);
            dest.writeString(this.style);
            dest.writeString(this.imagekey);
            dest.writeString(this.imageurl);
            dest.writeInt(this.width);
            dest.writeInt(this.height);
            dest.writeString(this.type);
            dest.writeString(this.spname);
            dest.writeInt(this.spid);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.title = in.readString();
            this.remark = in.readString();
            this.remark2 = in.readString();
            this.style = in.readString();
            this.imagekey = in.readString();
            this.imageurl = in.readString();
            this.width = in.readInt();
            this.height = in.readInt();
            this.type = in.readString();
            this.spname = in.readString();
            this.spid = in.readInt();
        }

        public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
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
        dest.writeInt(this.code);
        dest.writeString(this.ver);
        dest.writeString(this.screen);
        dest.writeInt(this.count);
        dest.writeTypedList(this.list);
    }

    public SeasonNewBangumi() {
    }

    protected SeasonNewBangumi(Parcel in) {
        this.code = in.readInt();
        this.ver = in.readString();
        this.screen = in.readString();
        this.count = in.readInt();
        this.list = in.createTypedArrayList(ListBean.CREATOR);
    }

    public static final Parcelable.Creator<SeasonNewBangumi> CREATOR = new Parcelable.Creator<SeasonNewBangumi>() {
        @Override
        public SeasonNewBangumi createFromParcel(Parcel source) {
            return new SeasonNewBangumi(source);
        }

        @Override
        public SeasonNewBangumi[] newArray(int size) {
            return new SeasonNewBangumi[size];
        }
    };
}
