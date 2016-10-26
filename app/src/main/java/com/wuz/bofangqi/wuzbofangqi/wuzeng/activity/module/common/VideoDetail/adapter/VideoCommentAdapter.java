package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.AbsRecyclerViewAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.VideoComment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016-10-24.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class VideoCommentAdapter extends AbsRecyclerViewAdapter {

    private Context mContext;
    private List<VideoComment.ListBean> mListBeans = new ArrayList<>();

    public VideoCommentAdapter(Context mContext,RecyclerView mRecyclerView) {
        super(mRecyclerView);
        this.mContext = mContext;
    }

    public void setAllData(List<VideoComment.ListBean> ListBeans) {
        this.mListBeans.clear();
        this.mListBeans.addAll(ListBeans);
        notifyDataSetChanged();
    }


    @Override
    public VideoCommentAdapter.MyCLickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.video_comment_item, parent,false);
        MyCLickViewHolder myViewHolder = new MyCLickViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(ClickableViewHolder holder , int position) {
//        holder.imgUserAvatar
        if (holder instanceof MyCLickViewHolder) {
            MyCLickViewHolder  mholder= (MyCLickViewHolder)holder;
            Glide.with(mContext).load(mListBeans.get(position).face)
                    .into(mholder.imgUserAvatar);
            initCurrentLevel(mListBeans.get(position).levelInfo.currentLevel, mholder.itemUserLevel);
            mholder.tvName.setText(mListBeans.get(position).nick);
            mholder.tvShi.setText(mListBeans.get(position).good + "");
            mholder.tvUpNum.setText(mListBeans.get(position).replyCount + "");
            mholder.tvFloorNum.setText(mListBeans.get(position).lv + "");
            mholder.tvTime.setText(mListBeans.get(position).createAt);
            mholder.tvComment.setText(mListBeans.get(position).msg);
        }

        super.onBindViewHolder(holder, position);
    }

    private void initCurrentLevel(int currentLevel, ImageView itemUserLevel) {
        switch (currentLevel){
            case 1:
                itemUserLevel.setImageResource(R.drawable.ic_lv1);
                break;
            case 2:
                itemUserLevel.setImageResource(R.drawable.ic_lv2);
                break;
            case 3:
                itemUserLevel.setImageResource(R.drawable.ic_lv3);
                break;
            case 4:
                itemUserLevel.setImageResource(R.drawable.ic_lv4);
                break;
            case 5:
                itemUserLevel.setImageResource(R.drawable.ic_lv5);
                break;
            default:
                itemUserLevel.setImageResource(R.drawable.ic_lv0);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    class MyCLickViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

        @Bind(R.id.img_user_avatar)
        ImageView imgUserAvatar;
        @Bind(R.id.item_user_level)
        ImageView itemUserLevel;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_shi)
        TextView tvShi;
        @Bind(R.id.tv_up_num)
        TextView tvUpNum;
        @Bind(R.id.tv_floor_num)
        TextView tvFloorNum;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_comment)
        TextView tvComment;

        public MyCLickViewHolder(View itemView) {
            super(itemView);
            imgUserAvatar=$(R.id.img_user_avatar);
            itemUserLevel=$(R.id.item_user_level);
            tvName=$(R.id.tv_name);
            tvShi=$(R.id.tv_shi);
            tvUpNum=$(R.id.tv_up_num);
            tvFloorNum=$(R.id.tv_floor_num);
            tvTime=$(R.id.tv_time);
            tvComment=$(R.id.tv_comment);
        }
    }

}
