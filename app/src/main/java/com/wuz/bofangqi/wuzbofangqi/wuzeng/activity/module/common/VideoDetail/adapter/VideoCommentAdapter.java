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
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.VideoComment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-24.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class VideoCommentAdapter extends RecyclerView.Adapter<VideoCommentAdapter.MyViewHolder> {

    private Context mContext;
    private List<VideoComment.ListBean> mListBeans = new ArrayList<>();

    public VideoCommentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setAllData(List<VideoComment.ListBean> mListBeans) {
        this.mListBeans.clear();
        this.mListBeans.addAll(mListBeans);
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.video_comment_item, parent);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.imgUserAvatar
        Glide.with(mContext).load( mListBeans.get(position).face)
                .into(holder.imgUserAvatar);
        initCurrentLevel(mListBeans.get(position).levelInfo.currentLevel, holder.itemUserLevel);
        holder.tvName.setText(mListBeans.get(position).nick);
        holder.tvShi.setText(mListBeans.get(position).good + "");
        holder.tvUpNum.setText(mListBeans.get(position).replyCount+"");
        holder.tvFloorNum.setText(mListBeans.get(position).lv+"");
        holder.tvTime.setText(mListBeans.get(position).createAt);
        holder.tvComment.setText(mListBeans.get(position).msg);

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

    class MyViewHolder extends RecyclerView.ViewHolder {

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

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
