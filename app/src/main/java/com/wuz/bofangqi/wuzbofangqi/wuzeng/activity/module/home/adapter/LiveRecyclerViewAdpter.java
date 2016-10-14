package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter;

import android.app.Activity;
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
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.Live.LivePlayActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.LiveIndex;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CircleImageView;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.banner.BannerEntity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

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

    private  List<BannerEntity> bannerlist;

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

    public void setmLiveIndex(LiveIndex liveIndex) {
        this.mLiveIndex = liveIndex;

       bannerlist=new ArrayList<>();
      for (int i=0;i<mLiveIndex.data.banner.size();i++)
      {
          BannerEntity mBannerEntity=new BannerEntity();
          mBannerEntity.title=mLiveIndex.data.banner.get(i).title;
          mBannerEntity.img=mLiveIndex.data.banner.get(i).img;
          mBannerEntity.link=mLiveIndex.data.banner.get(i).link;
          bannerlist.add(mBannerEntity);
      }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            ((BannerViewHolder)holder).itemLiveBanner.delayTime(5).build(bannerlist);
        }
        if (holder instanceof EntranceViewHolder) {
            Glide.with(mContext).load(mLiveIndex.data.banner.get(position).img)
                    .into(((EntranceViewHolder) holder).liveEntranceImage);
            ((EntranceViewHolder) holder).liveEntranceTitle.setText(mLiveIndex.data.banner.get(position).title);
        }
        if (holder instanceof PatitionTitleViewHolder) {
        /*    @Bind(R.id.item_live_patition_icon)
            ImageView itemLivePatitionIcon;
            @Bind(R.id.item_live_patition_title)
            TextView itemLivePatitionTitle;
            @Bind(R.id.item_live_patition_count)
            TextView itemLivePatitionCount;*/
            position -= 2;

            Glide.with(mContext).load(mLiveIndex.data.partitions.get(Math.abs(position / 5)).partition.subIcon.src)
                    .into(((PatitionTitleViewHolder) holder).itemLivePatitionIcon);
            ((PatitionTitleViewHolder) holder).itemLivePatitionTitle.setText(mLiveIndex.data.partitions.get(Math.abs(position / 5)).partition.name);
            ((PatitionTitleViewHolder) holder).itemLivePatitionCount.setText(mLiveIndex.data.partitions.get(Math.abs(position / 5)).partition.count+"");
        }

        if (holder instanceof PartitionViewHolder) {
            /*@Bind(R.id.circle_image)
            CircleImageView circleImage;
            @Bind(R.id.tv_partition_context)
            TextView tvPartitionContext;
            @Bind(R.id.item_live_user_name)
            TextView itemLiveUserName;
            @Bind(R.id.item_live_count)
            TextView itemLiveCount;
            @Bind(R.id.item_live_layout)
            CardView itemLiveLayout;*/

            position -= 2;
            Glide.with(mContext).load(mLiveIndex.data.partitions.get(Math.abs(position / 5)).lives.get(position % 5).cover.src)
                    .into(((PartitionViewHolder) holder).circleImage);
            ((PartitionViewHolder) holder).tvPartitionContext.setText(mLiveIndex.data.partitions.get(Math.abs(position / 5)).lives.get(position % 5).title);

            ((PartitionViewHolder) holder).itemLiveUserName.setText(mLiveIndex.data.partitions.get(Math.abs(position / 5)).lives.get(position % 5).owner.name);

            ((PartitionViewHolder) holder).itemLiveCount.setText(mLiveIndex.data.partitions.get(Math.abs(position / 5)).lives.get(position % 5).online + "");

            Glide.with(mContext).load(mLiveIndex.data.partitions.get(Math.abs(position / 5)).lives.get(position % 5).owner.face)
                    .into(((PartitionViewHolder) holder).imageLivePartitionMiddle);
          final int cid=  mLiveIndex.data.partitions.get(Math.abs(position / 5)).lives.get(position % 5).roomId;

            final LiveIndex.DataEntity.RecommendDataEntity.LivesEntity livesEntity = mLiveIndex.data.partitions.get(Math.abs(position / 5)).lives.get(position % 5);

            ((PartitionViewHolder) holder).itemLiveLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LivePlayActivity.Launcher((Activity)mContext,cid,livesEntity.title,livesEntity.cover.src,livesEntity.owner.name,livesEntity.online);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int size=0;
        if (mLiveIndex!=null)
        {
            if (mLiveIndex.data.banner!=null&&mLiveIndex.data.banner.size()>0)
            {
                size++;
            }

            if (mLiveIndex.data.entranceIcons!=null&&mLiveIndex.data.entranceIcons.size()>0)
            {
                size++;
            }

            size=size+5*(mLiveIndex.data.partitions.size());
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return BANNER_TYPE;
        }
        if (position == 1) {
            return ENTRANCEICONS_TYPE;
        }
        if (position>=2) {
            position -= 2;

            if (position % 5 == 0) {
                return PARTITION_TITLE_TYPE;
            }
            else {
                return LIVES_PARTITION_TYPE;
            }
        }else {
            return 0;
        }
    }

    public int getSpanSize(int pos) {
        int itemViewType = getItemViewType(pos);

       switch (itemViewType)
       {
           case BANNER_TYPE:
               return 12;
           case ENTRANCEICONS_TYPE:
               return 3;
           case PARTITION_TITLE_TYPE:
               return 12;
           case LIVES_PARTITION_TYPE:
               return 6;
       }
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
        @Bind(R.id.image_live_partition_middle)
        ImageView imageLivePartitionMiddle;
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


    class BannerViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_live_banner)
        BannerView itemLiveBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
