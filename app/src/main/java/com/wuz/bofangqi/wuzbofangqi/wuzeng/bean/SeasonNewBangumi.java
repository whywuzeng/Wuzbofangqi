package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

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
public class SeasonNewBangumi {


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

    public static class ListBean {
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
    }
}
