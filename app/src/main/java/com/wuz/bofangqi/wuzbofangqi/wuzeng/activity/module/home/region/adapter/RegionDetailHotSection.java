package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionChildInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.sectioned.StatelessSection;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-11-09.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RegionDetailHotSection extends StatelessSection {

    private Context mContext;

    private List<RegionChildInfo.DataBean.RecommendBean> recommendBeanList;

    public RegionDetailHotSection(Context mContext, List<RegionChildInfo.DataBean.RecommendBean> recommendBeanList) {
        super(R.layout.layout_region_detail_hot_header, R.layout.layout_region_detail_hot_item);

        this.mContext = mContext;
        this.recommendBeanList = recommendBeanList;
    }

    @Override
    public int getContentItemsTotal() {
        return recommendBeanList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {

        return new ItemRecyViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemRecyViewHolder)
        {
            ItemRecyViewHolder holder1 = (ItemRecyViewHolder) holder;
            Glide.with(mContext)
                    .load(recommendBeanList.get(position).cover)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .dontAnimate()
                    .into(holder1.itemImg);
            holder1.itemTitle.setText(recommendBeanList.get(position).title);
            holder1.itemUserName.setText(recommendBeanList.get(position).name);
            holder1.itemPlay.setText(recommendBeanList.get(position).play+"");
            holder1.itemReview.setText(recommendBeanList.get(position).reply+"");

            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转

                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {

        return new HeaderRecyViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);

    }

    static class HeaderRecyViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.item_type_tv)
        TextView itemTypeTv;

        HeaderRecyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static  class ItemRecyViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.item_img)
        ImageView itemImg;
        @Bind(R.id.item_title)
        TextView itemTitle;
        @Bind(R.id.item_user_name)
        TextView itemUserName;
        @Bind(R.id.item_play)
        TextView itemPlay;
        @Bind(R.id.item_review)
        TextView itemReview;
        @Bind(R.id.layout_play)
        LinearLayout layoutPlay;
        @Bind(R.id.item_view)
        RelativeLayout itemView;

        ItemRecyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
