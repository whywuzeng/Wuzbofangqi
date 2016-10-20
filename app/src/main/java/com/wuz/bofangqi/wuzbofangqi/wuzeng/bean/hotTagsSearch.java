package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016-10-18.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class hotTagsSearch  {


    /**
     * code : 0
     * seid : 11111327413318962794
     * message : success
     * timestamp : 1476694308
     * list : [{"keyword":"极乐净土","status":"up"},{"keyword":"ppap","status":"up"},{"keyword":"法医秦明","status":"down"},{"keyword":"一年生","status":"keep"},{"keyword":"污咚采访间","status":"up"},{"keyword":"吃货木下","status":"down"},{"keyword":"不可抗力","status":"up"},{"keyword":"暴走大事件","status":"down"},{"keyword":"守望先锋","status":"down"},{"keyword":"夏目友人帐","status":"down"},{"keyword":"你的名字","status":"up"},{"keyword":"张继科","status":"down"},{"keyword":"蓝瘦香菇","status":"up"},{"keyword":"主播炸了","status":"up"},{"keyword":"主播真会玩","status":"up"},{"keyword":"西部世界","status":"up"},{"keyword":"蜡笔小新","status":"down"},{"keyword":"刺客列传","status":"down"},{"keyword":"麻雀","status":"down"},{"keyword":"阴阳师","status":"up"},{"keyword":"大胃王密子君","status":"down"},{"keyword":"敖厂长","status":"down"},{"keyword":"谷阿莫","status":"up"},{"keyword":"起小点","status":"down"},{"keyword":"釜山行","status":"up"},{"keyword":"火影忍者","status":"up"},{"keyword":"污咚","status":"up"},{"keyword":"逗鱼时刻","status":"down"},{"keyword":"镇魂街","status":"up"},{"keyword":"日剧","status":"up"},{"keyword":"snh48","status":"down"},{"keyword":"杨洋","status":"up"},{"keyword":"错生","status":"down"},{"keyword":"老e","status":"up"},{"keyword":"fate","status":"up"},{"keyword":"徐老师来巡山","status":"up"},{"keyword":"齐木楠雄的灾难","status":"down"},{"keyword":"许昕","status":"down"},{"keyword":"抗韩中年人","status":"down"},{"keyword":"双程","status":"up"},{"keyword":"海贼王","status":"up"},{"keyword":"ioi","status":"up"},{"keyword":"王博文","status":"down"},{"keyword":"papi酱","status":"up"},{"keyword":"胡歌","status":"up"},{"keyword":"爸爸去哪儿4","status":"up"},{"keyword":"张若昀","status":"up"},{"keyword":"亚人","status":"up"},{"keyword":"湄公河行动","status":"up"},{"keyword":"赵丽颖","status":"keep"}]
     * cost : {"timer":"hotword","total":"0.000206","read file":"0.000121"}
     */

    @SerializedName("code")
    public int code;
    @SerializedName("seid")
    public String seid;
    @SerializedName("message")
    public String message;
    @SerializedName("timestamp")
    public int timestamp;
    /**
     * timer : hotword
     * total : 0.000206
     * read file : 0.000121
     */

    @SerializedName("cost")
    public CostEntity cost;
    /**
     * keyword : 极乐净土
     * status : up
     */

    @SerializedName("list")
    public List<ListEntity> list;

    public static class CostEntity {
        @SerializedName("timer")
        public String timer;
        @SerializedName("total")
        public String total;
    }

    public static class ListEntity {
        @SerializedName("keyword")
        public String keyword;
        @SerializedName("status")
        public String status;
    }
}
