package com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.banner;

/**
 * Created by hcc on 16/8/24 21:37
 * 100332338@qq.com
 * <p>
 * Banner模型类
 */
public class BannerEntity
{

    public String title;

    public BannerEntity()
    {

    }

    public BannerEntity(String title, String img, String link) {
        this.title = title;
        this.img = img;
        this.link = link;
    }

    public String img;


    public String link;
}
