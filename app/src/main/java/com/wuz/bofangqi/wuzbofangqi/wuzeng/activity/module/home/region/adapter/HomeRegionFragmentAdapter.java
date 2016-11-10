package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.AbsRecyclerViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-11-08.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class HomeRegionFragmentAdapter extends AbsRecyclerViewAdapter {

    private Context mContext;

    private String[] titles={    "直播直播", "番剧", "动画",
            "音乐", "舞蹈", "游戏",
            "科技", "生活", "鬼畜",
            "时尚", "广告", "娱乐",
            "电影", "电视剧", "游戏中心",};


    private int[] itemIcons=new int[]{
            R.drawable.ic_category_live, R.drawable.ic_category_t13,
            R.drawable.ic_category_t1, R.drawable.ic_category_t3,
            R.drawable.ic_category_t129, R.drawable.ic_category_t4,
            R.drawable.ic_category_t36, R.drawable.ic_category_t160,
            R.drawable.ic_category_t119, R.drawable.ic_category_t155,
            R.drawable.ic_category_t165, R.drawable.ic_category_t5,
            R.drawable.ic_category_t23, R.drawable.ic_category_t11,
            R.drawable.ic_category_game_center
    };



    public HomeRegionFragmentAdapter(RecyclerView recyclerView, Context context) {
        super(recyclerView);
        this.mContext = context;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.adapter_homeregionfragment, parent, false);
        return  new HomeRegionViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof HomeRegionViewHolder)
        {
            HomeRegionViewHolder holder1 = (HomeRegionViewHolder) holder;
            holder1.ivIcon.setImageResource(itemIcons[position]);
            holder1.tvTitle.setText(titles[position]);
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }


     class HomeRegionViewHolder extends ClickableViewHolder{
        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_title)
        TextView tvTitle;

        HomeRegionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
