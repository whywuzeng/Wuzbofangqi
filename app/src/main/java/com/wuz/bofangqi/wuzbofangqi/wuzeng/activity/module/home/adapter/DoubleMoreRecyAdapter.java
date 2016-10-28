package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter;

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
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SeasonBangumiSerial;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-28.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class DoubleMoreRecyAdapter extends AbsRecyclerViewAdapter {

    private Context mContext;
    private List<SeasonBangumiSerial.ListBean> mListBeans=new ArrayList<>();

    public DoubleMoreRecyAdapter(RecyclerView recyclerView, Context context) {
        super(recyclerView);
        this.mContext = context;
    }

    public void SetAllData( List<SeasonBangumiSerial.ListBean> listBeans)
    {
        mListBeans.clear();
        mListBeans.addAll(listBeans);
        notifyDataSetChanged();
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recy_double_more_adapter, parent, false);

        return new DoubleMoreClickHolder(view);
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof DoubleMoreClickHolder)
        {
            SeasonBangumiSerial.ListBean listBean = mListBeans.get(position);
            DoubleMoreClickHolder clickHolder = (DoubleMoreClickHolder) holder;
            Glide.with(mContext)
                    .load(listBean.cover)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(clickHolder.imgCardcenter);

            clickHolder.tvFollewnum.setText("" + listBean.attention + "在关注");
            clickHolder.tvTitle.setText(listBean.title);
            clickHolder.tvSubtitle.setText("更新至第"+listBean.bgmcount+"话");
        }
    }

    @Override
    public int getItemCount() {

        return mListBeans.size()>0?6:mListBeans.size();
    }

    class DoubleMoreClickHolder extends ClickableViewHolder {
        @Bind(R.id.img_cardcenter)
        ImageView imgCardcenter;
        @Bind(R.id.tv_follewnum)
        TextView tvFollewnum;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_subtitle)
        TextView tvSubtitle;

        public DoubleMoreClickHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
