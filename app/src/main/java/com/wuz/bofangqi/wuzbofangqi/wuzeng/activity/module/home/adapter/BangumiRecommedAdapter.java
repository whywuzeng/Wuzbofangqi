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
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.AbsRecyclerViewAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.bangumiBannerAndRecy;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-27.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter
 * Author:Administrator
 * Fuction:Bangumi recommend 适配器
 * UpdateUser:
 * UpdateDate:
 */
public class BangumiRecommedAdapter extends AbsRecyclerViewAdapter {

    private List<bangumiBannerAndRecy.RecommendsBean> recommendsBeanList = new ArrayList<>();
    private Context mContext;

    public BangumiRecommedAdapter(RecyclerView recyclerView, Context context) {
        super(recyclerView);
        this.mContext = context;
    }

    public void SetAllData(List<bangumiBannerAndRecy.RecommendsBean> recommendsBeanList) {
        this.recommendsBeanList.clear();
        this.recommendsBeanList.addAll(recommendsBeanList);
        notifyDataSetChanged();
    }


    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bangumirecommedadpteritem, parent, false);

        return new RecommedClickViewholder(view);
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof RecommedClickViewholder)
        {
           RecommedClickViewholder viewholder=(RecommedClickViewholder)holder;
            Glide.with(mContext).load(recommendsBeanList.get(position).pic)
                    .into(viewholder.imgRecommed);
            viewholder.tvBangumiTexttitle.setText(recommendsBeanList.get(position).title);
            viewholder.tvBangumiContexttitle.setText(recommendsBeanList.get(position).description);
        }
    }

    @Override
    public int getItemCount() {
        return recommendsBeanList.size();
    }

    class RecommedClickViewholder extends ClickableViewHolder {

        @Bind(R.id.img_recommed)
        ImageView imgRecommed;
        @Bind(R.id.tv_bangumi_texttitle)
        TextView tvBangumiTexttitle;
        @Bind(R.id.tv_bangumi_contexttitle)
        TextView tvBangumiContexttitle;
        @Bind(R.id.card_view)
        CardView cardView;

        public RecommedClickViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
