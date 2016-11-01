package com.wuz.bofangqi.wuzbofangqi.wuzeng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016-11-01.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.bean
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class BangumiDetailRecommend {


    /**
     * code : 0
     * message : success
     * result : [{"cover":"http://i0.hdslb.com/bfs/bangumi/3bb9d699bdb5be085a5b58fe737eaa51548a88b4.jpg","cursor":1.477908000325E12,"desc":"出场就撒币，唱歌也撒币\n没错，是真的撒\n说的就是ARCAREAEACT","id":2177,"is_new":1,"link":"http://bangumi.bilibili.com/anime/5520","onDt":"2016-10-31 18:00:00","title":"SHOW BY ROCK!!第二季 05"},{"cover":"http://i0.hdslb.com/bfs/bangumi/0e1f0e3fcab660e382c387f20173ebb4aedb96aa.jpg","cursor":1.477821600885E12,"desc":"总有人在为你战斗，你永远不孤独","id":2176,"is_new":1,"link":"http://www.bilibili.com/html/activity-20161028madoka.html","onDt":"2016-10-30 18:00:00","title":"【番箱子 vol.02】名为希望的绝望童话"},{"cover":"http://i0.hdslb.com/bfs/bangumi/22d4a7edff820c6c42ab22f81606efc7b268bdf1.jpg","cursor":1.477735200969E12,"desc":"活动出岔子\n声优救场子\n正片捅娄子_(:з」∠)_","id":2163,"is_new":1,"link":"http://bangumi.bilibili.com/anime/5532","onDt":"2016-10-29 18:00:00","title":"少女编号 04"},{"cover":"http://i0.hdslb.com/bfs/bangumi/4229afaf36094b07384dc630b3bff18b90481580.jpg","cursor":1.477648800409E12,"desc":"少年守护乌森与重要之人的执着，最终令他成长\n总之，不再来回味一遍吗","id":2151,"link":"http://bangumi.bilibili.com/anime/1405","onDt":"2016-10-28 18:00:00","title":"结界师"},{"cover":"http://i0.hdslb.com/bfs/bangumi/790c62dc1d7752620e486480230bc01f4886cd46.jpg","cursor":1.477562400317E12,"desc":"看Mizotaku是辣～～么可爱\n话说咱这是美食节目吗？为啥每期都在吃东西\n这次的奖品是徽章组～","id":2150,"link":"http://bangumi.bilibili.com/anime/5061","onDt":"2016-10-27 18:00:00","title":"梦之祭！研究室 03"},{"cover":"http://i0.hdslb.com/bfs/bangumi/51791263b05b9ce4cf49ee30fdb47a8144f04104.jpg","cursor":1.477476000326E12,"desc":"朱丽叶张开了女神的翅膀\n罗密欧抱紧了朱丽叶","id":2112,"link":"http://bangumi.bilibili.com/anime/4779","onDt":"2016-10-26 18:00:00","title":"罗密欧与朱丽叶"},{"cover":"http://i0.hdslb.com/bfs/bangumi/162a05064919a5784a2859956b819d3dfe754355.jpg","cursor":1.477389600728E12,"desc":"是的，兼桑又帅又强又潮\n可是\u2026\u2026自我介绍没有一次是说完的呀","id":2110,"link":"http://bangumi.bilibili.com/anime/5515","onDt":"2016-10-25 18:00:00","title":"刀剑乱舞-花丸- 04"},{"cover":"http://i0.hdslb.com/bfs/bangumi/1621a7135c73a90edceca912013beb4a4df623c7.jpg","cursor":1.477362840157E12,"desc":"完结报告在这里\n所以当初都奶对了吗（收割毒奶中","id":2127,"link":"http://www.bilibili.com/html/activity-20161019bangumi.html","onDt":"2016-10-25 10:34:00","title":"2016年7月新番完结报告"},{"cover":"http://i0.hdslb.com/bfs/bangumi/ec3a0cc4086ebdd327b06666d4786f5091997021.jpg","cursor":1.47704400075E12,"desc":"从游戏的另一个人物身上展开故事\n总之，零的后宫就变成了苍真的后宫\n(￣︶￣)没错吧","id":2089,"link":"http://bangumi.bilibili.com/anime/1307","onDt":"2016-10-21 18:00:00","title":"光明之泪x风"},{"cover":"http://i0.hdslb.com/bfs/bangumi/216123fb68c33f52e06332eb58a91dc2c521259a.jpg","cursor":1.4768712003E12,"desc":"那个曾经背叛ETU的男人回来了\n带回了改变以及一句\u201c弱小的队伍也能打败强大的家伙\u201d","id":2061,"link":"http://bangumi.bilibili.com/anime/1020","onDt":"2016-10-19 18:00:00","title":"逆转监督"}]
     */

    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;
    /**
     * cover : http://i0.hdslb.com/bfs/bangumi/3bb9d699bdb5be085a5b58fe737eaa51548a88b4.jpg
     * cursor : 1.477908000325E12
     * desc : 出场就撒币，唱歌也撒币
     没错，是真的撒
     说的就是ARCAREAEACT
     * id : 2177
     * is_new : 1
     * link : http://bangumi.bilibili.com/anime/5520
     * onDt : 2016-10-31 18:00:00
     * title : SHOW BY ROCK!!第二季 05
     */

    @SerializedName("result")
    public List<ResultBean> result;

    public static class ResultBean {
        @SerializedName("cover")
        public String cover;
        @SerializedName("cursor")
        public double cursor;
        @SerializedName("desc")
        public String desc;
        @SerializedName("id")
        public int id;
        @SerializedName("is_new")
        public int isNew;
        @SerializedName("link")
        public String link;
        @SerializedName("onDt")
        public String onDt;
        @SerializedName("title")
        public String title;
    }
}
