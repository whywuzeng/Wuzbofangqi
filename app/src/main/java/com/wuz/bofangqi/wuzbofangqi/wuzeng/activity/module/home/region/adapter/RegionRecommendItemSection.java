package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionTypeRecommendinfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.sectioned.StatelessSection;

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
public class RegionRecommendItemSection extends StatelessSection {

    List<RegionTypeRecommendinfo.DataBean.RecommendBean> mAlldatas;
    private Context mContext;

    public RegionRecommendItemSection(List<RegionTypeRecommendinfo.DataBean.RecommendBean> alldatas,Context context) {
        super(R.layout.layout_region_recommend_item_section, R.layout.layout_region_recommend_item_section_item);
        this.mAlldatas=alldatas;
        this.mContext=context;
    }

    @Override
    public int getContentItemsTotal() {
        return mAlldatas.size();
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new RegionRecommendItemSectionHeadViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        if (holder instanceof RegionRecommendItemSectionHeadViewHolder)
        {
            RegionRecommendItemSectionHeadViewHolder holder1 = (RegionRecommendItemSectionHeadViewHolder) holder;


        }

    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new RegionRecommendItemSectionItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof RegionRecommendItemSectionItemViewHolder)
        {
            RegionRecommendItemSectionItemViewHolder holder1 = (RegionRecommendItemSectionItemViewHolder) holder;
            RegionTypeRecommendinfo.DataBean.RecommendBean recommendBean = mAlldatas.get(position);
            Glide.with(mContext)
                    .load( recommendBean.cover)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(holder1.ivIcon);
            holder1.tvTitle.setText(recommendBean.title);
            holder1.tvPaly.setText(recommendBean.play+"");
            holder1.tvTalk.setText(recommendBean.reply+"");

        }
    }




    static class RegionRecommendItemSectionHeadViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_left)
        TextView tvLeft;
        @Bind(R.id.tv_right)
        TextView tvRight;

        RegionRecommendItemSectionHeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class RegionRecommendItemSectionItemViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_paly)
        TextView tvPaly;
        @Bind(R.id.tv_talk)
        TextView tvTalk;
        @Bind(R.id.card_view)
        CardView cardView;

        RegionRecommendItemSectionItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
