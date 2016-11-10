package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.banner.BannerEntity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.banner.BannerView;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.sectioned.StatelessSection;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-11-08.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter.section
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RegionRecommendBannerSection extends StatelessSection {

    private List<BannerEntity> banners;

    public RegionRecommendBannerSection(List<BannerEntity> banners) {
        super(R.layout.layout_banner, R.layout.layout_home_recommend_empty);
        this.banners = banners;
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new EmptyViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {

        return new BannerViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {

        BannerViewHolder holder1 = (BannerViewHolder) holder;
        holder1.layoutBanner.delayTime(5).build(banners);
    }

    static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }


    static class BannerViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.layout_banner)
        BannerView layoutBanner;

       public BannerViewHolder(View itemView) {
           super(itemView);
           ButterKnife.bind(this, itemView);
        }
    }
}
