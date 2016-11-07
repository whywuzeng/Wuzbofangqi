package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016-11-02.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class HDVideo {

    /**
     * format : hdmp4
     * timelength : 1476160
     * accept_format : mp4,hdmp4
     * accept_quality : [2,1]
     * durl : [{"length":1476160,"size":206950377,"url":"http://cn-sdjn-cu-v-01.acgvideo.com/vg6/e/d5/9253164-1-hd.mp4?expires=1478083200&ssig=w9GLFoOsv41ZxZEhgglysQ&oi=1783312443&rate=210000","backup_url":["http://cn-sddz2-cu.acgvideo.com/vg1/3/6d/9253164-1-hd.mp4?expires=1478083200&ssig=j1hA7otXWVSV2WjSGswFPg&oi=1783312443&rate=210000","http://cn-sdyt-cu-v-03.acgvideo.com/vg7/8/47/9253164-1-hd.mp4?expires=1478083200&ssig=X_8BAee_hhoSVbG1vq3LYA&oi=1783312443&rate=210000"]}]
     */

    @SerializedName("format")
    public String format;
    @SerializedName("timelength")
    public int timelength;
    @SerializedName("accept_format")
    public String acceptFormat;
    @SerializedName("accept_quality")
    public List<Integer> acceptQuality;
    /**
     * length : 1476160
     * size : 206950377
     * url : http://cn-sdjn-cu-v-01.acgvideo.com/vg6/e/d5/9253164-1-hd.mp4?expires=1478083200&ssig=w9GLFoOsv41ZxZEhgglysQ&oi=1783312443&rate=210000
     * backup_url : ["http://cn-sddz2-cu.acgvideo.com/vg1/3/6d/9253164-1-hd.mp4?expires=1478083200&ssig=j1hA7otXWVSV2WjSGswFPg&oi=1783312443&rate=210000","http://cn-sdyt-cu-v-03.acgvideo.com/vg7/8/47/9253164-1-hd.mp4?expires=1478083200&ssig=X_8BAee_hhoSVbG1vq3LYA&oi=1783312443&rate=210000"]
     */

    @SerializedName("durl")
    public List<DurlBean> durl;

    public static class DurlBean {
        @SerializedName("length")
        public int length;
        @SerializedName("size")
        public int size;
        @SerializedName("url")
        public String url;
        @SerializedName("backup_url")
        public List<String> backupUrl;
    }
}
