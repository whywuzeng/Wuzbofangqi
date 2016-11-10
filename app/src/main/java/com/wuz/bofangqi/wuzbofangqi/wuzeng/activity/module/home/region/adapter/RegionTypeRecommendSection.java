package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.banner.BannerEntity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.banner.BannerView;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.sectioned.StatelessSection;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-11-10.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RegionTypeRecommendSection extends StatelessSection {

    private List<BannerEntity> mBannerEntities=new ArrayList<>();

    public RegionTypeRecommendSection(List<BannerEntity> mBannerEntities) {
        super(R.layout.layout_banner,R.layout.layout_home_recommend_empty );
        this.mBannerEntities=mBannerEntities;
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

        return new BannerHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        if (holder instanceof BannerHeaderViewHolder)
        {
            BannerHeaderViewHolder holder1 = (BannerHeaderViewHolder) holder;
            holder1.layoutBanner.delayTime(5).build(mBannerEntities);
        }
    }

    static class EmptyViewHolder extends RecyclerView.ViewHolder
    {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class BannerHeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.layout_banner)
        BannerView layoutBanner;

        BannerHeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
