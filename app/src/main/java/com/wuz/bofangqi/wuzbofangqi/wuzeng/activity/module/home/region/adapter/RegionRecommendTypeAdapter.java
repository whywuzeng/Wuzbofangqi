package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter;

import android.content.Context;
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
public class RegionRecommendTypeAdapter extends AbsRecyclerViewAdapter {

    private Context mContext;
    private List<String> IdsList=new ArrayList<>();
    private List<String> titlesList=new ArrayList<>();

    public RegionRecommendTypeAdapter(RecyclerView recyclerView, Context context,List<String> IdsList,List<String> titlesList) {
        super(recyclerView);
        this.mContext = context;
        this.IdsList=IdsList;
        this.titlesList=titlesList;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_adapet_region_recommend_type, parent, false);

        return new TypeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (holder instanceof TypeViewHolder)
        {
            TypeViewHolder holder1 = (TypeViewHolder) holder;
            Glide.with(mContext)
                    .load(IdsList.get(position))
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(holder1.ivIcon);

            holder1.tvTitle.setText(titlesList.get(position));
        }
    }

    @Override
    public int getItemCount() {

        return titlesList.size();
    }

     class TypeViewHolder extends ClickableViewHolder {

        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_title)
        TextView tvTitle;

        public TypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
