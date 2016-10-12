package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.LiveIndex;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CircleImageView;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.banner.BannerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-11.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class LiveRecyclerViewAdpter extends RecyclerView.Adapter {

    private Context mContext;

    private LiveIndex mLiveIndex;

    public static final int ENTRANCEICONS_TYPE = 0x1111;

    public static final int PARTITION_TITLE_TYPE = 0x1111 << 1;

    public static final int LIVES_PARTITION_TYPE = 0x1111 << 2;

    public static final int BANNER_TYPE = 0x1111 << 3;

    public LiveRecyclerViewAdpter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case ENTRANCEICONS_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_live_entrance, parent, false);
                return new EntranceViewHolder(view);
            case PARTITION_TITLE_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_live_patition_title, parent, false);
                return new PatitionTitleViewHolder(view);
            case LIVES_PARTITION_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.live_item_partition, parent, false);
                return new PartitionViewHolder(view);
            case BANNER_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_live_banner, parent, false);
                return new BannerViewHolder(view);
        }

        return null;
    }

    public void setmLiveIndex(LiveIndex liveIndex)
    {
        this.mLiveIndex=liveIndex;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder)
        {


        }
        if (holder instanceof EntranceViewHolder)
        {
            Glide.with(mContext).load(mLiveIndex.data.banner.get(position).img)
                    .into( ((EntranceViewHolder) holder).liveEntranceImage);
            ((EntranceViewHolder) holder).liveEntranceTitle.setText(mLiveIndex.data.banner.get(position).title);
        }
        if (holder instanceof PatitionTitleViewHolder)
        {
            position-=2;
            mLiveIndex.data.partitions.get(position)
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {

        if (position==0)
        {
            return BANNER_TYPE;
        }
        if (position==1)
        {
            return ENTRANCEICONS_TYPE;
        }
        position-=2;

        if (position%5==0) {
           return PARTITION_TITLE_TYPE;
        }else {
            return LIVES_PARTITION_TYPE;
        }
    }

    public int getSpanSize(int pos)
    {
        return 0;
    }

    class EntranceViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.live_entrance_image)
        ImageView liveEntranceImage;
        @Bind(R.id.live_entrance_title)
        TextView liveEntranceTitle;

        public EntranceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class PatitionTitleViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_live_patition_icon)
        ImageView itemLivePatitionIcon;
        @Bind(R.id.item_live_patition_title)
        TextView itemLivePatitionTitle;
        @Bind(R.id.item_live_patition_count)
        TextView itemLivePatitionCount;

        public PatitionTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    class PartitionViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.circle_image)
        CircleImageView circleImage;
        @Bind(R.id.tv_partition_context)
        TextView tvPartitionContext;
        @Bind(R.id.item_live_user_name)
        TextView itemLiveUserName;
        @Bind(R.id.item_live_count)
        TextView itemLiveCount;
        @Bind(R.id.item_live_layout)
        CardView itemLiveLayout;

        public PartitionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


     class BannerViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.item_live_banner)
        BannerView itemLiveBanner;

         public BannerViewHolder(View itemView) {
             super(itemView);
             ButterKnife.bind(this, itemView);
         }

    }
}
