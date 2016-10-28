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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.AbsRecyclerViewAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SeasonNewBangumi;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-27.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class SeasonNewBangumiAdapter extends AbsRecyclerViewAdapter {

    private Context mContext;
    private List<SeasonNewBangumi.ListBean> listBeanList=new ArrayList<>();
    private String[] Look_num={"20万人正在追番","30万人正在追番","40万人正在追番"};

    public boolean isAllShow() {
        return isAllShow;
    }

    public void setIsAllShow(boolean isAllShow) {
        this.isAllShow = isAllShow;
    }

    private boolean isAllShow=false;

    public SeasonNewBangumiAdapter(RecyclerView recyclerView, Context Context) {
        super(recyclerView);
        this.mContext = Context;
    }

    public void SetAllData(List<SeasonNewBangumi.ListBean> listBeanList)
    {
        this.listBeanList.clear();
        this.listBeanList.addAll(listBeanList);
        notifyDataSetChanged();
    }


    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_new_season_bangumi, parent, false);
        return new SeasonClickViewholder(view);
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if(listBeanList.size()<=0)
            return;
        if (holder instanceof SeasonClickViewholder)
        {
           SeasonClickViewholder viewholder=((SeasonClickViewholder) holder);
            SeasonNewBangumi.ListBean listBean = listBeanList.get(position);
            Glide.with(mContext).load(listBeanList.get(position).imageurl)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewholder.ivSeasonCenter);
            int v = (int)Math.random() * 3;
            viewholder.tvSeasonBanguminum.setText(Look_num[v]);

            viewholder.tvSeasonTitle.setText(listBeanList.get(position).title);
        }
    }

    @Override
    public int getItemCount() {
        return isAllShow?listBeanList.size():Look_num.length;
    }

    class SeasonClickViewholder extends ClickableViewHolder {

        @Bind(R.id.iv_season_center)
        ImageView ivSeasonCenter;
        @Bind(R.id.tv_season_banguminum)
        TextView tvSeasonBanguminum;
        @Bind(R.id.season_card_view)
        CardView seasonCardView;
        @Bind(R.id.tv_season_title)
        TextView tvSeasonTitle;

        public SeasonClickViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
